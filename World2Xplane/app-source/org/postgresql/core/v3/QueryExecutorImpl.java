/*      */ package org.postgresql.core.v3;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.lang.ref.PhantomReference;
/*      */ import java.lang.ref.ReferenceQueue;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Properties;
/*      */ import java.util.Vector;
/*      */ import org.postgresql.PGNotification;
/*      */ import org.postgresql.copy.CopyOperation;
/*      */ import org.postgresql.core.Field;
/*      */ import org.postgresql.core.Logger;
/*      */ import org.postgresql.core.Notification;
/*      */ import org.postgresql.core.PGBindException;
/*      */ import org.postgresql.core.PGStream;
/*      */ import org.postgresql.core.ParameterList;
/*      */ import org.postgresql.core.Parser;
/*      */ import org.postgresql.core.Query;
/*      */ import org.postgresql.core.QueryExecutor;
/*      */ import org.postgresql.core.ResultCursor;
/*      */ import org.postgresql.core.ResultHandler;
/*      */ import org.postgresql.core.Utils;
/*      */ import org.postgresql.util.GT;
/*      */ import org.postgresql.util.PSQLException;
/*      */ import org.postgresql.util.PSQLState;
/*      */ import org.postgresql.util.PSQLWarning;
/*      */ import org.postgresql.util.ServerErrorMessage;
/*      */ 
/*      */ public class QueryExecutorImpl implements QueryExecutor {
/*      */   private Object lockedFor;
/*      */   
/*      */   private static final int MAX_BUFFERED_QUERIES = 256;
/*      */   
/*      */   private final HashMap parsedQueryMap;
/*      */   
/*      */   private final ReferenceQueue parsedQueryCleanupQueue;
/*      */   
/*      */   private final HashMap openPortalMap;
/*      */   
/*      */   private final ReferenceQueue openPortalCleanupQueue;
/*      */   
/*      */   private final ArrayList pendingParseQueue;
/*      */   
/*      */   private final ArrayList pendingBindQueue;
/*      */   
/*      */   private final ArrayList pendingExecuteQueue;
/*      */   
/*      */   private final ArrayList pendingDescribeStatementQueue;
/*      */   
/*      */   private final ArrayList pendingDescribePortalQueue;
/*      */   
/*      */   private long nextUniqueID;
/*      */   
/*      */   private final ProtocolConnectionImpl protoConnection;
/*      */   
/*      */   private final PGStream pgStream;
/*      */   
/*      */   private final Logger logger;
/*      */   
/*      */   private final boolean allowEncodingChanges;
/*      */   
/*      */   private int queryCount;
/*      */   
/*      */   private final SimpleQuery beginTransactionQuery;
/*      */   
/*      */   public QueryExecutorImpl(ProtocolConnectionImpl protoConnection, PGStream pgStream, Properties info, Logger logger) {
/*   59 */     this.lockedFor = null;
/* 1582 */     this.parsedQueryMap = new HashMap<Object, Object>();
/* 1583 */     this.parsedQueryCleanupQueue = new ReferenceQueue();
/* 1613 */     this.openPortalMap = new HashMap<Object, Object>();
/* 1614 */     this.openPortalCleanupQueue = new ReferenceQueue();
/* 2178 */     this.pendingParseQueue = new ArrayList();
/* 2179 */     this.pendingBindQueue = new ArrayList();
/* 2180 */     this.pendingExecuteQueue = new ArrayList();
/* 2181 */     this.pendingDescribeStatementQueue = new ArrayList();
/* 2182 */     this.pendingDescribePortalQueue = new ArrayList();
/* 2184 */     this.nextUniqueID = 1L;
/* 2196 */     this.beginTransactionQuery = new SimpleQuery(new String[] { "BEGIN" }, null);
/*      */     this.protoConnection = protoConnection;
/*      */     this.pgStream = pgStream;
/*      */     this.logger = logger;
/*      */     if (info.getProperty("allowEncodingChanges") != null) {
/*      */       this.allowEncodingChanges = Boolean.valueOf(info.getProperty("allowEncodingChanges")).booleanValue();
/*      */     } else {
/*      */       this.allowEncodingChanges = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void lock(Object obtainer) throws PSQLException {
/*      */     if (this.lockedFor == obtainer)
/*      */       throw new PSQLException(GT.tr("Tried to obtain lock while already holding it"), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */     waitOnLock();
/*      */     this.lockedFor = obtainer;
/*      */   }
/*      */   
/*      */   private void unlock(Object holder) throws PSQLException {
/*      */     if (this.lockedFor != holder)
/*      */       throw new PSQLException(GT.tr("Tried to break lock on database connection"), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */     this.lockedFor = null;
/*      */     notify();
/*      */   }
/*      */   
/*      */   private void waitOnLock() throws PSQLException {
/*      */     while (this.lockedFor != null) {
/*      */       try {
/*      */         wait();
/*      */       } catch (InterruptedException ie) {
/*      */         throw new PSQLException(GT.tr("Interrupted while waiting to obtain lock on database connection"), PSQLState.OBJECT_NOT_IN_STATE, ie);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   boolean hasLock(Object holder) {
/*      */     return (this.lockedFor == holder);
/*      */   }
/*      */   
/*      */   public Query createSimpleQuery(String sql) {
/*      */     return parseQuery(sql, false);
/*      */   }
/*      */   
/*      */   public Query createParameterizedQuery(String sql) {
/*      */     return parseQuery(sql, true);
/*      */   }
/*      */   
/*      */   private Query parseQuery(String query, boolean withParameters) {
/*      */     ArrayList<String[]> statementList = new ArrayList();
/*      */     ArrayList<String> fragmentList = new ArrayList(15);
/*      */     int fragmentStart = 0;
/*      */     int inParen = 0;
/*      */     boolean standardConformingStrings = this.protoConnection.getStandardConformingStrings();
/*      */     char[] aChars = query.toCharArray();
/*      */     for (int i = 0; i < aChars.length; i++) {
/*      */       switch (aChars[i]) {
/*      */         case '\'':
/*      */           i = Parser.parseSingleQuotes(aChars, i, standardConformingStrings);
/*      */           break;
/*      */         case '"':
/*      */           i = Parser.parseDoubleQuotes(aChars, i);
/*      */           break;
/*      */         case '-':
/*      */           i = Parser.parseLineComment(aChars, i);
/*      */           break;
/*      */         case '/':
/*      */           i = Parser.parseBlockComment(aChars, i);
/*      */           break;
/*      */         case '$':
/*      */           i = Parser.parseDollarQuotes(aChars, i);
/*      */           break;
/*      */         case '(':
/*      */           inParen++;
/*      */           break;
/*      */         case ')':
/*      */           inParen--;
/*      */           break;
/*      */         case '?':
/*      */           if (withParameters) {
/*      */             fragmentList.add(query.substring(fragmentStart, i));
/*      */             fragmentStart = i + 1;
/*      */           } 
/*      */           break;
/*      */         case ';':
/*      */           if (inParen == 0) {
/*      */             fragmentList.add(query.substring(fragmentStart, i));
/*      */             fragmentStart = i + 1;
/*      */             if (fragmentList.size() > 1 || ((String)fragmentList.get(0)).trim().length() > 0)
/*      */               statementList.add(fragmentList.toArray(new String[fragmentList.size()])); 
/*      */             fragmentList.clear();
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } 
/*      */     fragmentList.add(query.substring(fragmentStart));
/*      */     if (fragmentList.size() > 1 || ((String)fragmentList.get(0)).trim().length() > 0)
/*      */       statementList.add(fragmentList.toArray(new String[fragmentList.size()])); 
/*      */     if (statementList.isEmpty())
/*      */       return EMPTY_QUERY; 
/*      */     if (statementList.size() == 1)
/*      */       return new SimpleQuery(statementList.get(0), this.protoConnection); 
/*      */     SimpleQuery[] subqueries = new SimpleQuery[statementList.size()];
/*      */     int[] offsets = new int[statementList.size()];
/*      */     int offset = 0;
/*      */     for (int j = 0; j < statementList.size(); j++) {
/*      */       String[] fragments = statementList.get(j);
/*      */       offsets[j] = offset;
/*      */       subqueries[j] = new SimpleQuery(fragments, this.protoConnection);
/*      */       offset += fragments.length - 1;
/*      */     } 
/*      */     return new CompositeQuery(subqueries, offsets);
/*      */   }
/*      */   
/*      */   public synchronized void execute(Query query, ParameterList parameters, ResultHandler handler, int maxRows, int fetchSize, int flags) throws SQLException {
/*      */     waitOnLock();
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug("simple execute, handler=" + handler + ", maxRows=" + maxRows + ", fetchSize=" + fetchSize + ", flags=" + flags); 
/*      */     if (parameters == null)
/*      */       parameters = SimpleQuery.NO_PARAMETERS; 
/*      */     boolean describeOnly = ((0x20 & flags) != 0);
/*      */     ((V3ParameterList)parameters).convertFunctionOutParameters();
/*      */     if (!describeOnly)
/*      */       ((V3ParameterList)parameters).checkAllParametersSet(); 
/*      */     try {
/*      */       try {
/*      */         handler = sendQueryPreamble(handler, flags);
/*      */         ErrorTrackingResultHandler trackingHandler = new ErrorTrackingResultHandler(handler);
/*      */         this.queryCount = 0;
/*      */         sendQuery((V3Query)query, (V3ParameterList)parameters, maxRows, fetchSize, flags, trackingHandler);
/*      */         sendSync();
/*      */         processResults(handler, flags);
/*      */       } catch (PGBindException se) {
/*      */         sendSync();
/*      */         processResults(handler, flags);
/*      */         handler.handleError((SQLException)new PSQLException(GT.tr("Unable to bind parameter values for statement."), PSQLState.INVALID_PARAMETER_VALUE, se.getIOException()));
/*      */       } 
/*      */     } catch (IOException e) {
/*      */       this.protoConnection.close();
/*      */       handler.handleError((SQLException)new PSQLException(GT.tr("An I/O error occured while sending to the backend."), PSQLState.CONNECTION_FAILURE, e));
/*      */     } 
/*      */     handler.handleCompletion();
/*      */   }
/*      */   
/*      */   private static class ErrorTrackingResultHandler implements ResultHandler {
/*      */     private final ResultHandler delegateHandler;
/*      */     
/*      */     private boolean sawError = false;
/*      */     
/*      */     ErrorTrackingResultHandler(ResultHandler delegateHandler) {
/*      */       this.delegateHandler = delegateHandler;
/*      */     }
/*      */     
/*      */     public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {
/*      */       this.delegateHandler.handleResultRows(fromQuery, fields, tuples, cursor);
/*      */     }
/*      */     
/*      */     public void handleCommandStatus(String status, int updateCount, long insertOID) {
/*      */       this.delegateHandler.handleCommandStatus(status, updateCount, insertOID);
/*      */     }
/*      */     
/*      */     public void handleWarning(SQLWarning warning) {
/*      */       this.delegateHandler.handleWarning(warning);
/*      */     }
/*      */     
/*      */     public void handleError(SQLException error) {
/*      */       this.sawError = true;
/*      */       this.delegateHandler.handleError(error);
/*      */     }
/*      */     
/*      */     public void handleCompletion() throws SQLException {
/*      */       this.delegateHandler.handleCompletion();
/*      */     }
/*      */     
/*      */     boolean hasErrors() {
/*      */       return this.sawError;
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized void execute(Query[] queries, ParameterList[] parameterLists, ResultHandler handler, int maxRows, int fetchSize, int flags) throws SQLException {
/*      */     waitOnLock();
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug("batch execute " + queries.length + " queries, handler=" + handler + ", maxRows=" + maxRows + ", fetchSize=" + fetchSize + ", flags=" + flags); 
/*      */     boolean describeOnly = ((0x20 & flags) != 0);
/*      */     if (!describeOnly)
/*      */       for (int i = 0; i < parameterLists.length; i++) {
/*      */         if (parameterLists[i] != null)
/*      */           ((V3ParameterList)parameterLists[i]).checkAllParametersSet(); 
/*      */       }  
/*      */     try {
/*      */       handler = sendQueryPreamble(handler, flags);
/*      */       ErrorTrackingResultHandler trackingHandler = new ErrorTrackingResultHandler(handler);
/*      */       this.queryCount = 0;
/*      */       for (int i = 0; i < queries.length; i++) {
/*      */         V3Query query = (V3Query)queries[i];
/*      */         V3ParameterList parameters = (V3ParameterList)parameterLists[i];
/*      */         if (parameters == null)
/*      */           parameters = SimpleQuery.NO_PARAMETERS; 
/*      */         sendQuery(query, parameters, maxRows, fetchSize, flags, trackingHandler);
/*      */         if (trackingHandler.hasErrors())
/*      */           break; 
/*      */       } 
/*      */       if (!trackingHandler.hasErrors()) {
/*      */         sendSync();
/*      */         processResults(handler, flags);
/*      */       } 
/*      */     } catch (IOException e) {
/*      */       this.protoConnection.close();
/*      */       handler.handleError((SQLException)new PSQLException(GT.tr("An I/O error occured while sending to the backend."), PSQLState.CONNECTION_FAILURE, e));
/*      */     } 
/*      */     handler.handleCompletion();
/*      */   }
/*      */   
/*      */   private ResultHandler sendQueryPreamble(final ResultHandler delegateHandler, int flags) throws IOException {
/*      */     processDeadParsedQueries();
/*      */     processDeadPortals();
/*      */     if ((flags & 0x10) != 0 || this.protoConnection.getTransactionState() != 0)
/*      */       return delegateHandler; 
/*      */     sendOneQuery(this.beginTransactionQuery, SimpleQuery.NO_PARAMETERS, 0, 0, 2);
/*      */     return new ResultHandler() {
/*      */         private boolean sawBegin = false;
/*      */         
/*      */         public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {
/*      */           if (this.sawBegin)
/*      */             delegateHandler.handleResultRows(fromQuery, fields, tuples, cursor); 
/*      */         }
/*      */         
/*      */         public void handleCommandStatus(String status, int updateCount, long insertOID) {
/*      */           if (!this.sawBegin) {
/*      */             this.sawBegin = true;
/*      */             if (!status.equals("BEGIN"))
/*      */               handleError((SQLException)new PSQLException(GT.tr("Expected command status BEGIN, got {0}.", status), PSQLState.PROTOCOL_VIOLATION)); 
/*      */           } else {
/*      */             delegateHandler.handleCommandStatus(status, updateCount, insertOID);
/*      */           } 
/*      */         }
/*      */         
/*      */         public void handleWarning(SQLWarning warning) {
/*      */           delegateHandler.handleWarning(warning);
/*      */         }
/*      */         
/*      */         public void handleError(SQLException error) {
/*      */           delegateHandler.handleError(error);
/*      */         }
/*      */         
/*      */         public void handleCompletion() throws SQLException {
/*      */           delegateHandler.handleCompletion();
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public synchronized byte[] fastpathCall(int fnid, ParameterList parameters, boolean suppressBegin) throws SQLException {
/*      */     waitOnLock();
/*      */     if (!suppressBegin)
/*      */       doSubprotocolBegin(); 
/*      */     try {
/*      */       sendFastpathCall(fnid, (SimpleParameterList)parameters);
/*      */       return receiveFastpathResult();
/*      */     } catch (IOException ioe) {
/*      */       this.protoConnection.close();
/*      */       throw new PSQLException(GT.tr("An I/O error occured while sending to the backend."), PSQLState.CONNECTION_FAILURE, ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void doSubprotocolBegin() throws SQLException {
/*      */     if (this.protoConnection.getTransactionState() == 0) {
/*      */       if (this.logger.logDebug())
/*      */         this.logger.debug("Issuing BEGIN before fastpath or copy call."); 
/*      */       ResultHandler handler = new ResultHandler() {
/*      */           private boolean sawBegin = false;
/*      */           
/*      */           private SQLException sqle = null;
/*      */           
/*      */           public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {}
/*      */           
/*      */           public void handleCommandStatus(String status, int updateCount, long insertOID) {
/*      */             if (!this.sawBegin) {
/*      */               if (!status.equals("BEGIN"))
/*      */                 handleError((SQLException)new PSQLException(GT.tr("Expected command status BEGIN, got {0}.", status), PSQLState.PROTOCOL_VIOLATION)); 
/*      */               this.sawBegin = true;
/*      */             } else {
/*      */               handleError((SQLException)new PSQLException(GT.tr("Unexpected command status: {0}.", status), PSQLState.PROTOCOL_VIOLATION));
/*      */             } 
/*      */           }
/*      */           
/*      */           public void handleWarning(SQLWarning warning) {
/*      */             handleError(warning);
/*      */           }
/*      */           
/*      */           public void handleError(SQLException error) {
/*      */             if (this.sqle == null) {
/*      */               this.sqle = error;
/*      */             } else {
/*      */               this.sqle.setNextException(error);
/*      */             } 
/*      */           }
/*      */           
/*      */           public void handleCompletion() throws SQLException {
/*      */             if (this.sqle != null)
/*      */               throw this.sqle; 
/*      */           }
/*      */         };
/*      */       try {
/*      */         sendOneQuery(this.beginTransactionQuery, SimpleQuery.NO_PARAMETERS, 0, 0, 2);
/*      */         sendSync();
/*      */         processResults(handler, 0);
/*      */       } catch (IOException ioe) {
/*      */         throw new PSQLException(GT.tr("An I/O error occured while sending to the backend."), PSQLState.CONNECTION_FAILURE, ioe);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public ParameterList createFastpathParameters(int count) {
/*      */     return new SimpleParameterList(count, this.protoConnection);
/*      */   }
/*      */   
/*      */   private void sendFastpathCall(int fnid, SimpleParameterList params) throws SQLException, IOException {
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" FE=> FunctionCall(" + fnid + ", " + params.getParameterCount() + " params)"); 
/*      */     int paramCount = params.getParameterCount();
/*      */     int encodedSize = 0;
/*      */     int i;
/*      */     for (i = 1; i <= paramCount; i++) {
/*      */       if (params.isNull(i)) {
/*      */         encodedSize += 4;
/*      */       } else {
/*      */         encodedSize += 4 + params.getV3Length(i);
/*      */       } 
/*      */     } 
/*      */     this.pgStream.SendChar(70);
/*      */     this.pgStream.SendInteger4(10 + 2 * paramCount + 2 + encodedSize + 2);
/*      */     this.pgStream.SendInteger4(fnid);
/*      */     this.pgStream.SendInteger2(paramCount);
/*      */     for (i = 1; i <= paramCount; i++)
/*      */       this.pgStream.SendInteger2(params.isBinary(i) ? 1 : 0); 
/*      */     this.pgStream.SendInteger2(paramCount);
/*      */     for (i = 1; i <= paramCount; i++) {
/*      */       if (params.isNull(i)) {
/*      */         this.pgStream.SendInteger4(-1);
/*      */       } else {
/*      */         this.pgStream.SendInteger4(params.getV3Length(i));
/*      */         params.writeV3Value(i, this.pgStream);
/*      */       } 
/*      */     } 
/*      */     this.pgStream.SendInteger2(1);
/*      */     this.pgStream.flush();
/*      */   }
/*      */   
/*      */   public synchronized void processNotifies() throws SQLException {
/*      */     waitOnLock();
/*      */     if (this.protoConnection.getTransactionState() != 0)
/*      */       return; 
/*      */     try {
/*      */       while (this.pgStream.hasMessagePending()) {
/*      */         SQLWarning warning;
/*      */         int c = this.pgStream.ReceiveChar();
/*      */         switch (c) {
/*      */           case 65:
/*      */             receiveAsyncNotify();
/*      */             continue;
/*      */           case 69:
/*      */             throw receiveErrorResponse();
/*      */           case 78:
/*      */             warning = receiveNoticeResponse();
/*      */             this.protoConnection.addWarning(warning);
/*      */             continue;
/*      */         } 
/*      */         throw new PSQLException(GT.tr("Unknown Response Type {0}.", new Character((char)c)), PSQLState.CONNECTION_FAILURE);
/*      */       } 
/*      */     } catch (IOException ioe) {
/*      */       throw new PSQLException(GT.tr("An I/O error occured while sending to the backend."), PSQLState.CONNECTION_FAILURE, ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   private byte[] receiveFastpathResult() throws IOException, SQLException {
/*      */     boolean endQuery = false;
/*      */     SQLException error = null;
/*      */     byte[] returnValue = null;
/*      */     while (!endQuery) {
/*      */       SQLException newError;
/*      */       SQLWarning warning;
/*      */       int msgLen, valueLen, c = this.pgStream.ReceiveChar();
/*      */       switch (c) {
/*      */         case 65:
/*      */           receiveAsyncNotify();
/*      */           continue;
/*      */         case 69:
/*      */           newError = receiveErrorResponse();
/*      */           if (error == null) {
/*      */             error = newError;
/*      */             continue;
/*      */           } 
/*      */           error.setNextException(newError);
/*      */           continue;
/*      */         case 78:
/*      */           warning = receiveNoticeResponse();
/*      */           this.protoConnection.addWarning(warning);
/*      */           continue;
/*      */         case 90:
/*      */           receiveRFQ();
/*      */           endQuery = true;
/*      */           continue;
/*      */         case 86:
/*      */           msgLen = this.pgStream.ReceiveInteger4();
/*      */           valueLen = this.pgStream.ReceiveInteger4();
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE FunctionCallResponse(" + valueLen + " bytes)"); 
/*      */           if (valueLen != -1) {
/*      */             byte[] buf = new byte[valueLen];
/*      */             this.pgStream.Receive(buf, 0, valueLen);
/*      */             returnValue = buf;
/*      */           } 
/*      */           continue;
/*      */       } 
/*      */       throw new PSQLException(GT.tr("Unknown Response Type {0}.", new Character((char)c)), PSQLState.CONNECTION_FAILURE);
/*      */     } 
/*      */     if (error != null)
/*      */       throw error; 
/*      */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized CopyOperation startCopy(String sql, boolean suppressBegin) throws SQLException {
/*      */     waitOnLock();
/*      */     if (!suppressBegin)
/*      */       doSubprotocolBegin(); 
/*      */     byte[] buf = Utils.encodeUTF8(sql);
/*      */     try {
/*      */       if (this.logger.logDebug())
/*      */         this.logger.debug(" FE=> Query(CopyStart)"); 
/*      */       this.pgStream.SendChar(81);
/*      */       this.pgStream.SendInteger4(buf.length + 4 + 1);
/*      */       this.pgStream.Send(buf);
/*      */       this.pgStream.SendChar(0);
/*      */       this.pgStream.flush();
/*      */       return processCopyResults(null, true);
/*      */     } catch (IOException ioe) {
/*      */       throw new PSQLException(GT.tr("Database connection failed when starting copy"), PSQLState.CONNECTION_FAILURE, ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   private synchronized void initCopy(CopyOperationImpl op) throws SQLException, IOException {
/*      */     this.pgStream.ReceiveInteger4();
/*      */     int rowFormat = this.pgStream.ReceiveChar();
/*      */     int numFields = this.pgStream.ReceiveInteger2();
/*      */     int[] fieldFormats = new int[numFields];
/*      */     for (int i = 0; i < numFields; i++)
/*      */       fieldFormats[i] = this.pgStream.ReceiveInteger2(); 
/*      */     lock(op);
/*      */     op.init(this, rowFormat, fieldFormats);
/*      */   }
/*      */   
/*      */   public void cancelCopy(CopyOperationImpl op) throws SQLException {
/*      */     if (!hasLock(op))
/*      */       throw new PSQLException(GT.tr("Tried to cancel an inactive copy operation"), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */     SQLException error = null;
/*      */     int errors = 0;
/*      */     try {
/*      */       if (op instanceof CopyInImpl)
/*      */         synchronized (this) {
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug("FE => CopyFail"); 
/*      */           byte[] msg = Utils.encodeUTF8("Copy cancel requested");
/*      */           this.pgStream.SendChar(102);
/*      */           this.pgStream.SendInteger4(5 + msg.length);
/*      */           this.pgStream.Send(msg);
/*      */           this.pgStream.SendChar(0);
/*      */           this.pgStream.flush();
/*      */           while (true) {
/*      */             try {
/*      */               processCopyResults(op, true);
/*      */             } catch (SQLException se) {
/*      */               errors++;
/*      */               if (error != null) {
/*      */                 SQLException e = se;
/*      */                 SQLException next;
/*      */                 while ((next = e.getNextException()) != null)
/*      */                   e = next; 
/*      */                 e.setNextException(error);
/*      */               } 
/*      */               error = se;
/*      */             } 
/*      */             if (!hasLock(op)) {
/*      */             
/*      */             } else {
/*      */               continue;
/*      */             } 
/*      */             if (op instanceof CopyInImpl) {
/*      */               if (errors < 1)
/*      */                 throw new PSQLException(GT.tr("Missing expected error response to copy cancel request"), PSQLState.COMMUNICATION_ERROR); 
/*      */               if (errors > 1)
/*      */                 throw new PSQLException(GT.tr("Got {0} error responses to single copy cancel request", String.valueOf(errors)), PSQLState.COMMUNICATION_ERROR, error); 
/*      */             } 
/*      */             return;
/*      */           } 
/*      */         }  
/*      */       if (op instanceof CopyOutImpl)
/*      */         this.protoConnection.sendQueryCancel(); 
/*      */     } catch (IOException ioe) {
/*      */       throw new PSQLException(GT.tr("Database connection failed when canceling copy operation"), PSQLState.CONNECTION_FAILURE, ioe);
/*      */     } 
/*      */     if (op instanceof CopyInImpl) {
/*      */       if (errors < 1)
/*      */         throw new PSQLException(GT.tr("Missing expected error response to copy cancel request"), PSQLState.COMMUNICATION_ERROR); 
/*      */       if (errors > 1)
/*      */         throw new PSQLException(GT.tr("Got {0} error responses to single copy cancel request", String.valueOf(errors)), PSQLState.COMMUNICATION_ERROR, error); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized long endCopy(CopyInImpl op) throws SQLException {
/*      */     if (!hasLock(op))
/*      */       throw new PSQLException(GT.tr("Tried to end inactive copy"), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */     try {
/*      */       if (this.logger.logDebug())
/*      */         this.logger.debug(" FE=> CopyDone"); 
/*      */       this.pgStream.SendChar(99);
/*      */       this.pgStream.SendInteger4(4);
/*      */       this.pgStream.flush();
/*      */       processCopyResults(op, true);
/*      */       return op.getHandledRowCount();
/*      */     } catch (IOException ioe) {
/*      */       throw new PSQLException(GT.tr("Database connection failed when ending copy"), PSQLState.CONNECTION_FAILURE, ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void writeToCopy(CopyInImpl op, byte[] data, int off, int siz) throws SQLException {
/*      */     if (!hasLock(op))
/*      */       throw new PSQLException(GT.tr("Tried to write to an inactive copy operation"), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" FE=> CopyData(" + siz + ")"); 
/*      */     try {
/*      */       this.pgStream.SendChar(100);
/*      */       this.pgStream.SendInteger4(siz + 4);
/*      */       this.pgStream.Send(data, off, siz);
/*      */       processCopyResults(op, false);
/*      */     } catch (IOException ioe) {
/*      */       throw new PSQLException(GT.tr("Database connection failed when writing to copy"), PSQLState.CONNECTION_FAILURE, ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void flushCopy(CopyInImpl op) throws SQLException {
/*      */     if (!hasLock(op))
/*      */       throw new PSQLException(GT.tr("Tried to write to an inactive copy operation"), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */     try {
/*      */       this.pgStream.flush();
/*      */       processCopyResults(op, false);
/*      */     } catch (IOException ioe) {
/*      */       throw new PSQLException(GT.tr("Database connection failed when writing to copy"), PSQLState.CONNECTION_FAILURE, ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   synchronized void readFromCopy(CopyOutImpl op) throws SQLException {
/*      */     if (!hasLock(op))
/*      */       throw new PSQLException(GT.tr("Tried to read from inactive copy"), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */     try {
/*      */       processCopyResults(op, true);
/*      */     } catch (IOException ioe) {
/*      */       throw new PSQLException(GT.tr("Database connection failed when reading from copy"), PSQLState.CONNECTION_FAILURE, ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   CopyOperationImpl processCopyResults(CopyOperationImpl op, boolean block) throws SQLException, IOException {
/*      */     PSQLException pSQLException;
/*      */     boolean endReceiving = false;
/*      */     SQLException error = null, errors = null;
/*      */     while (!endReceiving && (block || this.pgStream.hasMessagePending())) {
/*      */       PSQLException pSQLException1;
/*      */       int len;
/*      */       String status;
/*      */       byte[] buf;
/*      */       if (!block) {
/*      */         int i = this.pgStream.PeekChar();
/*      */         if (i == 67) {
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE CommandStatus, Ignored until CopyDone"); 
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       int c = this.pgStream.ReceiveChar();
/*      */       switch (c) {
/*      */         case 65:
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE Asynchronous Notification while copying"); 
/*      */           receiveAsyncNotify();
/*      */           break;
/*      */         case 78:
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE Notification while copying"); 
/*      */           this.protoConnection.addWarning(receiveNoticeResponse());
/*      */           break;
/*      */         case 67:
/*      */           status = receiveCommandStatus();
/*      */           try {
/*      */             if (op == null)
/*      */               throw new PSQLException(GT.tr("Received CommandComplete ''{0}'' without an active copy operation", status), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */             op.handleCommandStatus(status);
/*      */           } catch (SQLException se) {
/*      */             error = se;
/*      */           } 
/*      */           block = true;
/*      */           break;
/*      */         case 69:
/*      */           error = receiveErrorResponse();
/*      */           block = true;
/*      */           break;
/*      */         case 71:
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE CopyInResponse"); 
/*      */           if (op != null)
/*      */             pSQLException1 = new PSQLException(GT.tr("Got CopyInResponse from server during an active {0}", op.getClass().getName()), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */           op = new CopyInImpl();
/*      */           initCopy(op);
/*      */           endReceiving = true;
/*      */           break;
/*      */         case 72:
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE CopyOutResponse"); 
/*      */           if (op != null)
/*      */             pSQLException1 = new PSQLException(GT.tr("Got CopyOutResponse from server during an active {0}", op.getClass().getName()), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */           op = new CopyOutImpl();
/*      */           initCopy(op);
/*      */           endReceiving = true;
/*      */           break;
/*      */         case 100:
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE CopyData"); 
/*      */           len = this.pgStream.ReceiveInteger4() - 4;
/*      */           buf = this.pgStream.Receive(len);
/*      */           if (op == null) {
/*      */             pSQLException1 = new PSQLException(GT.tr("Got CopyData without an active copy operation"), PSQLState.OBJECT_NOT_IN_STATE);
/*      */           } else if (!(op instanceof CopyOutImpl)) {
/*      */             pSQLException1 = new PSQLException(GT.tr("Unexpected copydata from server for {0}", op.getClass().getName()), PSQLState.COMMUNICATION_ERROR);
/*      */           } else {
/*      */             ((CopyOutImpl)op).handleCopydata(buf);
/*      */           } 
/*      */           endReceiving = true;
/*      */           break;
/*      */         case 99:
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE CopyDone"); 
/*      */           len = this.pgStream.ReceiveInteger4() - 4;
/*      */           if (len > 0)
/*      */             this.pgStream.Receive(len); 
/*      */           if (!(op instanceof CopyOutImpl))
/*      */             pSQLException1 = new PSQLException("Got CopyDone while not copying from server", PSQLState.OBJECT_NOT_IN_STATE); 
/*      */           block = true;
/*      */           break;
/*      */         case 90:
/*      */           receiveRFQ();
/*      */           if (hasLock(op))
/*      */             unlock(op); 
/*      */           op = null;
/*      */           endReceiving = true;
/*      */           break;
/*      */         case 84:
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE RowDescription (during copy ignored)"); 
/*      */           skipMessage();
/*      */           break;
/*      */         case 68:
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE DataRow (during copy ignored)"); 
/*      */           skipMessage();
/*      */           break;
/*      */         default:
/*      */           throw new IOException(GT.tr("Unexpected packet type during copy: {0}", Integer.toString(c)));
/*      */       } 
/*      */       if (pSQLException1 != null) {
/*      */         if (errors != null)
/*      */           pSQLException1.setNextException(errors); 
/*      */         pSQLException = pSQLException1;
/*      */         pSQLException1 = null;
/*      */       } 
/*      */     } 
/*      */     if (pSQLException != null)
/*      */       throw pSQLException; 
/*      */     return op;
/*      */   }
/*      */   
/*      */   private void sendQuery(V3Query query, V3ParameterList parameters, int maxRows, int fetchSize, int flags, ErrorTrackingResultHandler trackingHandler) throws IOException, SQLException {
/*      */     SimpleQuery[] subqueries = query.getSubqueries();
/*      */     SimpleParameterList[] subparams = parameters.getSubparams();
/*      */     boolean disallowBatching = ((flags & 0x80) != 0);
/*      */     if (subqueries == null) {
/*      */       this.queryCount++;
/*      */       if (disallowBatching || this.queryCount >= 256) {
/*      */         sendSync();
/*      */         processResults(trackingHandler, flags);
/*      */         this.queryCount = 0;
/*      */       } 
/*      */       if (!trackingHandler.hasErrors())
/*      */         sendOneQuery((SimpleQuery)query, (SimpleParameterList)parameters, maxRows, fetchSize, flags); 
/*      */     } else {
/*      */       for (int i = 0; i < subqueries.length; i++) {
/*      */         this.queryCount++;
/*      */         if (disallowBatching || this.queryCount >= 256) {
/*      */           sendSync();
/*      */           processResults(trackingHandler, flags);
/*      */           if (trackingHandler.hasErrors())
/*      */             break; 
/*      */           this.queryCount = 0;
/*      */         } 
/*      */         SimpleParameterList subparam = SimpleQuery.NO_PARAMETERS;
/*      */         if (subparams != null)
/*      */           subparam = subparams[i]; 
/*      */         sendOneQuery(subqueries[i], subparam, maxRows, fetchSize, flags);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void sendSync() throws IOException {
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" FE=> Sync"); 
/*      */     this.pgStream.SendChar(83);
/*      */     this.pgStream.SendInteger4(4);
/*      */     this.pgStream.flush();
/*      */   }
/*      */   
/*      */   private void sendParse(SimpleQuery query, SimpleParameterList params, boolean oneShot) throws IOException {
/*      */     int[] typeOIDs = params.getTypeOIDs();
/*      */     if (query.isPreparedFor(typeOIDs))
/*      */       return; 
/*      */     query.unprepare();
/*      */     processDeadParsedQueries();
/*      */     String statementName = null;
/*      */     if (!oneShot) {
/*      */       statementName = "S_" + this.nextUniqueID++;
/*      */       query.setStatementName(statementName);
/*      */       query.setStatementTypes((int[])typeOIDs.clone());
/*      */     } 
/*      */     byte[] encodedStatementName = query.getEncodedStatementName();
/*      */     String[] fragments = query.getFragments();
/*      */     if (this.logger.logDebug()) {
/*      */       StringBuffer sbuf = new StringBuffer(" FE=> Parse(stmt=" + statementName + ",query=\"");
/*      */       int k;
/*      */       for (k = 0; k < fragments.length; k++) {
/*      */         if (k > 0)
/*      */           sbuf.append("$" + k); 
/*      */         sbuf.append(fragments[k]);
/*      */       } 
/*      */       sbuf.append("\",oids={");
/*      */       for (k = 1; k <= params.getParameterCount(); k++) {
/*      */         if (k != 1)
/*      */           sbuf.append(","); 
/*      */         sbuf.append("" + params.getTypeOID(k));
/*      */       } 
/*      */       sbuf.append("})");
/*      */       this.logger.debug(sbuf.toString());
/*      */     } 
/*      */     byte[][] parts = new byte[fragments.length * 2 - 1][];
/*      */     int j = 0;
/*      */     int encodedSize = 0;
/*      */     int i;
/*      */     for (i = 0; i < fragments.length; i++) {
/*      */       if (i != 0) {
/*      */         parts[j] = Utils.encodeUTF8("$" + i);
/*      */         encodedSize += (parts[j]).length;
/*      */         j++;
/*      */       } 
/*      */       parts[j] = Utils.encodeUTF8(fragments[i]);
/*      */       encodedSize += (parts[j]).length;
/*      */       j++;
/*      */     } 
/*      */     encodedSize = 4 + ((encodedStatementName == null) ? 0 : encodedStatementName.length) + 1 + encodedSize + 1 + 2 + 4 * params.getParameterCount();
/*      */     this.pgStream.SendChar(80);
/*      */     this.pgStream.SendInteger4(encodedSize);
/*      */     if (encodedStatementName != null)
/*      */       this.pgStream.Send(encodedStatementName); 
/*      */     this.pgStream.SendChar(0);
/*      */     for (i = 0; i < parts.length; i++)
/*      */       this.pgStream.Send(parts[i]); 
/*      */     this.pgStream.SendChar(0);
/*      */     this.pgStream.SendInteger2(params.getParameterCount());
/*      */     for (i = 1; i <= params.getParameterCount(); i++)
/*      */       this.pgStream.SendInteger4(params.getTypeOID(i)); 
/*      */     this.pendingParseQueue.add(new Object[] { query, query.getStatementName() });
/*      */   }
/*      */   
/*      */   private void sendBind(SimpleQuery query, SimpleParameterList params, Portal portal) throws IOException {
/*      */     String statementName = query.getStatementName();
/*      */     byte[] encodedStatementName = query.getEncodedStatementName();
/*      */     byte[] encodedPortalName = (portal == null) ? null : portal.getEncodedPortalName();
/*      */     if (this.logger.logDebug()) {
/*      */       StringBuffer sbuf = new StringBuffer(" FE=> Bind(stmt=" + statementName + ",portal=" + portal);
/*      */       for (int k = 1; k <= params.getParameterCount(); k++)
/*      */         sbuf.append(",$" + k + "=<" + params.toString(k) + ">"); 
/*      */       sbuf.append(")");
/*      */       this.logger.debug(sbuf.toString());
/*      */     } 
/*      */     long encodedSize = 0L;
/*      */     int i;
/*      */     for (i = 1; i <= params.getParameterCount(); i++) {
/*      */       if (params.isNull(i)) {
/*      */         encodedSize += 4L;
/*      */       } else {
/*      */         encodedSize += 4L + params.getV3Length(i);
/*      */       } 
/*      */     } 
/*      */     encodedSize = (4 + ((encodedPortalName == null) ? 0 : encodedPortalName.length) + 1 + ((encodedStatementName == null) ? 0 : encodedStatementName.length) + 1 + 2 + params.getParameterCount() * 2 + 2) + encodedSize + 2L;
/*      */     if (encodedSize > 1073741823L)
/*      */       throw new PGBindException(new IOException(GT.tr("Bind message length {0} too long.  This can be caused by very large or incorrect length specifications on InputStream parameters.", new Long(encodedSize)))); 
/*      */     this.pgStream.SendChar(66);
/*      */     this.pgStream.SendInteger4((int)encodedSize);
/*      */     if (encodedPortalName != null)
/*      */       this.pgStream.Send(encodedPortalName); 
/*      */     this.pgStream.SendChar(0);
/*      */     if (encodedStatementName != null)
/*      */       this.pgStream.Send(encodedStatementName); 
/*      */     this.pgStream.SendChar(0);
/*      */     this.pgStream.SendInteger2(params.getParameterCount());
/*      */     for (i = 1; i <= params.getParameterCount(); i++)
/*      */       this.pgStream.SendInteger2(params.isBinary(i) ? 1 : 0); 
/*      */     this.pgStream.SendInteger2(params.getParameterCount());
/*      */     PGBindException bindException = null;
/*      */     for (int j = 1; j <= params.getParameterCount(); j++) {
/*      */       if (params.isNull(j)) {
/*      */         this.pgStream.SendInteger4(-1);
/*      */       } else {
/*      */         this.pgStream.SendInteger4(params.getV3Length(j));
/*      */         try {
/*      */           params.writeV3Value(j, this.pgStream);
/*      */         } catch (PGBindException be) {
/*      */           bindException = be;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     this.pgStream.SendInteger2(0);
/*      */     this.pendingBindQueue.add(portal);
/*      */     if (bindException != null)
/*      */       throw bindException; 
/*      */   }
/*      */   
/*      */   private void sendDescribePortal(SimpleQuery query, Portal portal) throws IOException {
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" FE=> Describe(portal=" + portal + ")"); 
/*      */     byte[] encodedPortalName = (portal == null) ? null : portal.getEncodedPortalName();
/*      */     int encodedSize = 5 + ((encodedPortalName == null) ? 0 : encodedPortalName.length) + 1;
/*      */     this.pgStream.SendChar(68);
/*      */     this.pgStream.SendInteger4(encodedSize);
/*      */     this.pgStream.SendChar(80);
/*      */     if (encodedPortalName != null)
/*      */       this.pgStream.Send(encodedPortalName); 
/*      */     this.pgStream.SendChar(0);
/*      */     this.pendingDescribePortalQueue.add(query);
/*      */     query.setPortalDescribed(true);
/*      */   }
/*      */   
/*      */   private void sendDescribeStatement(SimpleQuery query, SimpleParameterList params, boolean describeOnly) throws IOException {
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" FE=> Describe(statement=" + query.getStatementName() + ")"); 
/*      */     byte[] encodedStatementName = query.getEncodedStatementName();
/*      */     int encodedSize = 5 + ((encodedStatementName == null) ? 0 : encodedStatementName.length) + 1;
/*      */     this.pgStream.SendChar(68);
/*      */     this.pgStream.SendInteger4(encodedSize);
/*      */     this.pgStream.SendChar(83);
/*      */     if (encodedStatementName != null)
/*      */       this.pgStream.Send(encodedStatementName); 
/*      */     this.pgStream.SendChar(0);
/*      */     this.pendingDescribeStatementQueue.add(new Object[] { query, params, new Boolean(describeOnly), query.getStatementName() });
/*      */     this.pendingDescribePortalQueue.add(query);
/*      */     query.setStatementDescribed(true);
/*      */     query.setPortalDescribed(true);
/*      */   }
/*      */   
/*      */   private void sendExecute(SimpleQuery query, Portal portal, int limit) throws IOException {
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" FE=> Execute(portal=" + portal + ",limit=" + limit + ")"); 
/*      */     byte[] encodedPortalName = (portal == null) ? null : portal.getEncodedPortalName();
/*      */     int encodedSize = (encodedPortalName == null) ? 0 : encodedPortalName.length;
/*      */     this.pgStream.SendChar(69);
/*      */     this.pgStream.SendInteger4(5 + encodedSize + 4);
/*      */     if (encodedPortalName != null)
/*      */       this.pgStream.Send(encodedPortalName); 
/*      */     this.pgStream.SendChar(0);
/*      */     this.pgStream.SendInteger4(limit);
/*      */     this.pendingExecuteQueue.add(new Object[] { query, portal });
/*      */   }
/*      */   
/*      */   private void sendClosePortal(String portalName) throws IOException {
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" FE=> ClosePortal(" + portalName + ")"); 
/*      */     byte[] encodedPortalName = (portalName == null) ? null : Utils.encodeUTF8(portalName);
/*      */     int encodedSize = (encodedPortalName == null) ? 0 : encodedPortalName.length;
/*      */     this.pgStream.SendChar(67);
/*      */     this.pgStream.SendInteger4(6 + encodedSize);
/*      */     this.pgStream.SendChar(80);
/*      */     if (encodedPortalName != null)
/*      */       this.pgStream.Send(encodedPortalName); 
/*      */     this.pgStream.SendChar(0);
/*      */   }
/*      */   
/*      */   private void sendCloseStatement(String statementName) throws IOException {
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" FE=> CloseStatement(" + statementName + ")"); 
/*      */     byte[] encodedStatementName = Utils.encodeUTF8(statementName);
/*      */     this.pgStream.SendChar(67);
/*      */     this.pgStream.SendInteger4(5 + encodedStatementName.length + 1);
/*      */     this.pgStream.SendChar(83);
/*      */     this.pgStream.Send(encodedStatementName);
/*      */     this.pgStream.SendChar(0);
/*      */   }
/*      */   
/*      */   private void sendOneQuery(SimpleQuery query, SimpleParameterList params, int maxRows, int fetchSize, int flags) throws IOException {
/*      */     int rows;
/*      */     boolean noResults = ((flags & 0x4) != 0);
/*      */     boolean noMeta = ((flags & 0x2) != 0);
/*      */     boolean describeOnly = ((flags & 0x20) != 0);
/*      */     boolean usePortal = ((flags & 0x8) != 0 && !noResults && !noMeta && fetchSize > 0 && !describeOnly);
/*      */     boolean oneShot = ((flags & 0x1) != 0 && !usePortal);
/*      */     if (noResults) {
/*      */       rows = 1;
/*      */     } else if (!usePortal) {
/*      */       rows = maxRows;
/*      */     } else if (maxRows != 0 && fetchSize > maxRows) {
/*      */       rows = maxRows;
/*      */     } else {
/*      */       rows = fetchSize;
/*      */     } 
/*      */     sendParse(query, params, oneShot);
/*      */     boolean queryHasUnknown = query.hasUnresolvedTypes();
/*      */     boolean paramsHasUnknown = params.hasUnresolvedTypes();
/*      */     boolean describeStatement = (describeOnly || (!oneShot && paramsHasUnknown && queryHasUnknown && !query.isStatementDescribed()));
/*      */     if (!describeStatement && paramsHasUnknown && !queryHasUnknown) {
/*      */       int[] queryOIDs = query.getStatementTypes();
/*      */       int[] paramOIDs = params.getTypeOIDs();
/*      */       for (int i = 0; i < paramOIDs.length; i++) {
/*      */         if (paramOIDs[i] == 0)
/*      */           params.setResolvedType(i + 1, queryOIDs[i]); 
/*      */       } 
/*      */     } 
/*      */     if (describeStatement) {
/*      */       sendDescribeStatement(query, params, describeOnly);
/*      */       if (describeOnly)
/*      */         return; 
/*      */     } 
/*      */     Portal portal = null;
/*      */     if (usePortal) {
/*      */       String portalName = "C_" + this.nextUniqueID++;
/*      */       portal = new Portal(query, portalName);
/*      */     } 
/*      */     sendBind(query, params, portal);
/*      */     if (!noMeta && !describeStatement && !query.isPortalDescribed())
/*      */       sendDescribePortal(query, portal); 
/*      */     sendExecute(query, portal, rows);
/*      */   }
/*      */   
/*      */   private void registerParsedQuery(SimpleQuery query, String statementName) {
/*      */     if (statementName == null)
/*      */       return; 
/*      */     PhantomReference<SimpleQuery> cleanupRef = new PhantomReference<SimpleQuery>(query, this.parsedQueryCleanupQueue);
/*      */     this.parsedQueryMap.put(cleanupRef, statementName);
/*      */     query.setCleanupRef(cleanupRef);
/*      */   }
/*      */   
/*      */   private void processDeadParsedQueries() throws IOException {
/*      */     PhantomReference deadQuery;
/*      */     while ((deadQuery = (PhantomReference)this.parsedQueryCleanupQueue.poll()) != null) {
/*      */       String statementName = (String)this.parsedQueryMap.remove(deadQuery);
/*      */       sendCloseStatement(statementName);
/*      */       deadQuery.clear();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void registerOpenPortal(Portal portal) {
/*      */     if (portal == null)
/*      */       return; 
/*      */     String portalName = portal.getPortalName();
/*      */     PhantomReference<Portal> cleanupRef = new PhantomReference<Portal>(portal, this.openPortalCleanupQueue);
/*      */     this.openPortalMap.put(cleanupRef, portalName);
/*      */     portal.setCleanupRef(cleanupRef);
/*      */   }
/*      */   
/*      */   private void processDeadPortals() throws IOException {
/*      */     PhantomReference deadPortal;
/*      */     while ((deadPortal = (PhantomReference)this.openPortalCleanupQueue.poll()) != null) {
/*      */       String portalName = (String)this.openPortalMap.remove(deadPortal);
/*      */       sendClosePortal(portalName);
/*      */       deadPortal.clear();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void processResults(ResultHandler handler, int flags) throws IOException {
/*      */     boolean noResults = ((flags & 0x4) != 0);
/*      */     boolean bothRowsAndStatus = ((flags & 0x40) != 0);
/*      */     Vector<Object> tuples = null;
/*      */     boolean endQuery = false;
/*      */     boolean doneAfterRowDescNoData = false;
/*      */     int parseIndex = 0;
/*      */     int describeIndex = 0;
/*      */     int describePortalIndex = 0;
/*      */     int bindIndex = 0;
/*      */     int executeIndex = 0;
/*      */     while (!endQuery) {
/*      */       Object[] parsedQueryAndStatement;
/*      */       SimpleQuery parsedQuery;
/*      */       String parsedStatementName;
/*      */       Object[] describeData;
/*      */       Portal boundPortal;
/*      */       SimpleQuery query;
/*      */       Object[] executeData;
/*      */       String status;
/*      */       SimpleParameterList params;
/*      */       SimpleQuery currentQuery;
/*      */       Object arrayOfObject1[], tuple;
/*      */       boolean describeOnly;
/*      */       Portal currentPortal;
/*      */       SimpleQuery simpleQuery1;
/*      */       SQLException error;
/*      */       String origStatementName;
/*      */       Field[] fields;
/*      */       Portal portal1;
/*      */       Object[] arrayOfObject2;
/*      */       SQLWarning warning;
/*      */       int numParams;
/*      */       Field[] arrayOfField2;
/*      */       Query query1;
/*      */       int l_len;
/*      */       Field[] arrayOfField1;
/*      */       int i;
/*      */       Portal portal2;
/*      */       String name;
/*      */       SimpleQuery simpleQuery2;
/*      */       String value;
/*      */       byte[] buf;
/*      */       int c = this.pgStream.ReceiveChar();
/*      */       switch (c) {
/*      */         case 65:
/*      */           receiveAsyncNotify();
/*      */           continue;
/*      */         case 49:
/*      */           this.pgStream.ReceiveInteger4();
/*      */           parsedQueryAndStatement = this.pendingParseQueue.get(parseIndex++);
/*      */           parsedQuery = (SimpleQuery)parsedQueryAndStatement[0];
/*      */           parsedStatementName = (String)parsedQueryAndStatement[1];
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE ParseComplete [" + parsedStatementName + "]"); 
/*      */           registerParsedQuery(parsedQuery, parsedStatementName);
/*      */           continue;
/*      */         case 116:
/*      */           this.pgStream.ReceiveInteger4();
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE ParameterDescription"); 
/*      */           describeData = this.pendingDescribeStatementQueue.get(describeIndex);
/*      */           query = (SimpleQuery)describeData[0];
/*      */           params = (SimpleParameterList)describeData[1];
/*      */           describeOnly = ((Boolean)describeData[2]).booleanValue();
/*      */           origStatementName = (String)describeData[3];
/*      */           numParams = this.pgStream.ReceiveInteger2();
/*      */           for (i = 1; i <= numParams; i++) {
/*      */             int typeOid = this.pgStream.ReceiveInteger4();
/*      */             params.setResolvedType(i, typeOid);
/*      */           } 
/*      */           if ((origStatementName == null && query.getStatementName() == null) || (origStatementName != null && origStatementName.equals(query.getStatementName())))
/*      */             query.setStatementTypes((int[])params.getTypeOIDs().clone()); 
/*      */           if (describeOnly) {
/*      */             doneAfterRowDescNoData = true;
/*      */             continue;
/*      */           } 
/*      */           describeIndex++;
/*      */           continue;
/*      */         case 50:
/*      */           this.pgStream.ReceiveInteger4();
/*      */           boundPortal = this.pendingBindQueue.get(bindIndex++);
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE BindComplete [" + boundPortal + "]"); 
/*      */           registerOpenPortal(boundPortal);
/*      */           continue;
/*      */         case 51:
/*      */           this.pgStream.ReceiveInteger4();
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE CloseComplete"); 
/*      */           continue;
/*      */         case 110:
/*      */           this.pgStream.ReceiveInteger4();
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE NoData"); 
/*      */           describePortalIndex++;
/*      */           if (doneAfterRowDescNoData) {
/*      */             Object[] arrayOfObject = this.pendingDescribeStatementQueue.get(describeIndex++);
/*      */             SimpleQuery simpleQuery = (SimpleQuery)arrayOfObject[0];
/*      */             Field[] arrayOfField = simpleQuery.getFields();
/*      */             if (arrayOfField != null) {
/*      */               tuples = new Vector();
/*      */               handler.handleResultRows(simpleQuery, arrayOfField, tuples, null);
/*      */               tuples = null;
/*      */             } 
/*      */           } 
/*      */           continue;
/*      */         case 115:
/*      */           this.pgStream.ReceiveInteger4();
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE PortalSuspended"); 
/*      */           executeData = this.pendingExecuteQueue.get(executeIndex++);
/*      */           currentQuery = (SimpleQuery)executeData[0];
/*      */           currentPortal = (Portal)executeData[1];
/*      */           fields = currentQuery.getFields();
/*      */           if (fields != null && !noResults && tuples == null)
/*      */             tuples = new Vector(); 
/*      */           handler.handleResultRows(currentQuery, fields, tuples, currentPortal);
/*      */           tuples = null;
/*      */           continue;
/*      */         case 67:
/*      */           status = receiveCommandStatus();
/*      */           doneAfterRowDescNoData = false;
/*      */           arrayOfObject1 = this.pendingExecuteQueue.get(executeIndex++);
/*      */           simpleQuery1 = (SimpleQuery)arrayOfObject1[0];
/*      */           portal1 = (Portal)arrayOfObject1[1];
/*      */           arrayOfField2 = simpleQuery1.getFields();
/*      */           if (arrayOfField2 != null && !noResults && tuples == null)
/*      */             tuples = new Vector(); 
/*      */           if (arrayOfField2 != null || tuples != null) {
/*      */             handler.handleResultRows(simpleQuery1, arrayOfField2, tuples, null);
/*      */             tuples = null;
/*      */             if (bothRowsAndStatus)
/*      */               interpretCommandStatus(status, handler); 
/*      */           } else {
/*      */             interpretCommandStatus(status, handler);
/*      */           } 
/*      */           if (portal1 != null)
/*      */             portal1.close(); 
/*      */           continue;
/*      */         case 68:
/*      */           tuple = null;
/*      */           try {
/*      */             tuple = this.pgStream.ReceiveTupleV3();
/*      */           } catch (OutOfMemoryError oome) {
/*      */             if (!noResults)
/*      */               handler.handleError((SQLException)new PSQLException(GT.tr("Ran out of memory retrieving query results."), PSQLState.OUT_OF_MEMORY, oome)); 
/*      */           } 
/*      */           if (!noResults) {
/*      */             if (tuples == null)
/*      */               tuples = new Vector(); 
/*      */             tuples.addElement(tuple);
/*      */           } 
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE DataRow"); 
/*      */           continue;
/*      */         case 69:
/*      */           error = receiveErrorResponse();
/*      */           handler.handleError(error);
/*      */           continue;
/*      */         case 73:
/*      */           this.pgStream.ReceiveInteger4();
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE EmptyQuery"); 
/*      */           arrayOfObject2 = this.pendingExecuteQueue.get(executeIndex++);
/*      */           query1 = (Query)arrayOfObject2[0];
/*      */           portal2 = (Portal)arrayOfObject2[1];
/*      */           handler.handleCommandStatus("EMPTY", 0, 0L);
/*      */           if (portal2 != null)
/*      */             portal2.close(); 
/*      */           continue;
/*      */         case 78:
/*      */           warning = receiveNoticeResponse();
/*      */           handler.handleWarning(warning);
/*      */           continue;
/*      */         case 83:
/*      */           l_len = this.pgStream.ReceiveInteger4();
/*      */           name = this.pgStream.ReceiveString();
/*      */           value = this.pgStream.ReceiveString();
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE ParameterStatus(" + name + " = " + value + ")"); 
/*      */           if (name.equals("client_encoding") && !value.equalsIgnoreCase("UTF8") && !this.allowEncodingChanges) {
/*      */             this.protoConnection.close();
/*      */             handler.handleError((SQLException)new PSQLException(GT.tr("The server''s client_encoding parameter was changed to {0}. The JDBC driver requires client_encoding to be UTF8 for correct operation.", value), PSQLState.CONNECTION_FAILURE));
/*      */             endQuery = true;
/*      */           } 
/*      */           if (name.equals("DateStyle") && !value.startsWith("ISO,")) {
/*      */             this.protoConnection.close();
/*      */             handler.handleError((SQLException)new PSQLException(GT.tr("The server''s DateStyle parameter was changed to {0}. The JDBC driver requires DateStyle to begin with ISO for correct operation.", value), PSQLState.CONNECTION_FAILURE));
/*      */             endQuery = true;
/*      */           } 
/*      */           if (name.equals("standard_conforming_strings")) {
/*      */             if (value.equals("on")) {
/*      */               this.protoConnection.setStandardConformingStrings(true);
/*      */               continue;
/*      */             } 
/*      */             if (value.equals("off")) {
/*      */               this.protoConnection.setStandardConformingStrings(false);
/*      */               continue;
/*      */             } 
/*      */             this.protoConnection.close();
/*      */             handler.handleError((SQLException)new PSQLException(GT.tr("The server''s standard_conforming_strings parameter was reported as {0}. The JDBC driver expected on or off.", value), PSQLState.CONNECTION_FAILURE));
/*      */             endQuery = true;
/*      */           } 
/*      */           continue;
/*      */         case 84:
/*      */           arrayOfField1 = receiveFields();
/*      */           tuples = new Vector();
/*      */           simpleQuery2 = this.pendingDescribePortalQueue.get(describePortalIndex++);
/*      */           simpleQuery2.setFields(arrayOfField1);
/*      */           if (doneAfterRowDescNoData) {
/*      */             Object[] arrayOfObject = this.pendingDescribeStatementQueue.get(describeIndex++);
/*      */             Query query2 = (Query)arrayOfObject[0];
/*      */             handler.handleResultRows(query2, arrayOfField1, tuples, null);
/*      */             tuples = null;
/*      */           } 
/*      */           continue;
/*      */         case 90:
/*      */           receiveRFQ();
/*      */           endQuery = true;
/*      */           while (parseIndex < this.pendingParseQueue.size()) {
/*      */             Object[] failedQueryAndStatement = this.pendingParseQueue.get(parseIndex++);
/*      */             SimpleQuery failedQuery = (SimpleQuery)failedQueryAndStatement[0];
/*      */             failedQuery.unprepare();
/*      */           } 
/*      */           this.pendingParseQueue.clear();
/*      */           this.pendingDescribeStatementQueue.clear();
/*      */           this.pendingDescribePortalQueue.clear();
/*      */           this.pendingBindQueue.clear();
/*      */           this.pendingExecuteQueue.clear();
/*      */           continue;
/*      */         case 71:
/*      */           if (this.logger.logDebug()) {
/*      */             this.logger.debug(" <=BE CopyInResponse");
/*      */             this.logger.debug(" FE=> CopyFail");
/*      */           } 
/*      */           buf = Utils.encodeUTF8("The JDBC driver currently does not support COPY operations.");
/*      */           this.pgStream.SendChar(102);
/*      */           this.pgStream.SendInteger4(buf.length + 4 + 1);
/*      */           this.pgStream.Send(buf);
/*      */           this.pgStream.SendChar(0);
/*      */           this.pgStream.flush();
/*      */           sendSync();
/*      */           skipMessage();
/*      */           continue;
/*      */         case 72:
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE CopyOutResponse"); 
/*      */           skipMessage();
/*      */           handler.handleError((SQLException)new PSQLException(GT.tr("The driver currently does not support COPY operations."), PSQLState.NOT_IMPLEMENTED));
/*      */           continue;
/*      */         case 99:
/*      */           skipMessage();
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE CopyDone"); 
/*      */           continue;
/*      */         case 100:
/*      */           skipMessage();
/*      */           if (this.logger.logDebug())
/*      */             this.logger.debug(" <=BE CopyData"); 
/*      */           continue;
/*      */       } 
/*      */       throw new IOException("Unexpected packet type: " + c);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void skipMessage() throws IOException {
/*      */     int l_len = this.pgStream.ReceiveInteger4();
/*      */     this.pgStream.Skip(l_len - 4);
/*      */   }
/*      */   
/*      */   public synchronized void fetch(ResultCursor cursor, ResultHandler handler, int fetchSize) throws SQLException {
/*      */     waitOnLock();
/*      */     final Portal portal = (Portal)cursor;
/*      */     final ResultHandler delegateHandler = handler;
/*      */     handler = new ResultHandler() {
/*      */         public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {
/*      */           delegateHandler.handleResultRows(fromQuery, fields, tuples, cursor);
/*      */         }
/*      */         
/*      */         public void handleCommandStatus(String status, int updateCount, long insertOID) {
/*      */           handleResultRows(portal.getQuery(), null, new Vector(), null);
/*      */         }
/*      */         
/*      */         public void handleWarning(SQLWarning warning) {
/*      */           delegateHandler.handleWarning(warning);
/*      */         }
/*      */         
/*      */         public void handleError(SQLException error) {
/*      */           delegateHandler.handleError(error);
/*      */         }
/*      */         
/*      */         public void handleCompletion() throws SQLException {
/*      */           delegateHandler.handleCompletion();
/*      */         }
/*      */       };
/*      */     try {
/*      */       processDeadParsedQueries();
/*      */       processDeadPortals();
/*      */       sendExecute(portal.getQuery(), portal, fetchSize);
/*      */       sendSync();
/*      */       processResults(handler, 0);
/*      */     } catch (IOException e) {
/*      */       this.protoConnection.close();
/*      */       handler.handleError((SQLException)new PSQLException(GT.tr("An I/O error occured while sending to the backend."), PSQLState.CONNECTION_FAILURE, e));
/*      */     } 
/*      */     handler.handleCompletion();
/*      */   }
/*      */   
/*      */   private Field[] receiveFields() throws IOException {
/*      */     int l_msgSize = this.pgStream.ReceiveInteger4();
/*      */     int size = this.pgStream.ReceiveInteger2();
/*      */     Field[] fields = new Field[size];
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" <=BE RowDescription(" + size + ")"); 
/*      */     for (int i = 0; i < fields.length; i++) {
/*      */       String columnLabel = this.pgStream.ReceiveString();
/*      */       int tableOid = this.pgStream.ReceiveInteger4();
/*      */       short positionInTable = (short)this.pgStream.ReceiveInteger2();
/*      */       int typeOid = this.pgStream.ReceiveInteger4();
/*      */       int typeLength = this.pgStream.ReceiveInteger2();
/*      */       int typeModifier = this.pgStream.ReceiveInteger4();
/*      */       int formatType = this.pgStream.ReceiveInteger2();
/*      */       fields[i] = new Field(columnLabel, "", typeOid, typeLength, typeModifier, tableOid, positionInTable);
/*      */       fields[i].setFormat(formatType);
/*      */     } 
/*      */     return fields;
/*      */   }
/*      */   
/*      */   private void receiveAsyncNotify() throws IOException {
/*      */     int msglen = this.pgStream.ReceiveInteger4();
/*      */     int pid = this.pgStream.ReceiveInteger4();
/*      */     String msg = this.pgStream.ReceiveString();
/*      */     String param = this.pgStream.ReceiveString();
/*      */     this.protoConnection.addNotification((PGNotification)new Notification(msg, pid, param));
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" <=BE AsyncNotify(" + pid + "," + msg + "," + param + ")"); 
/*      */   }
/*      */   
/*      */   private SQLException receiveErrorResponse() throws IOException {
/*      */     int elen = this.pgStream.ReceiveInteger4();
/*      */     String totalMessage = this.pgStream.ReceiveString(elen - 4);
/*      */     ServerErrorMessage errorMsg = new ServerErrorMessage(totalMessage, this.logger.getLogLevel());
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" <=BE ErrorMessage(" + errorMsg.toString() + ")"); 
/*      */     return (SQLException)new PSQLException(errorMsg);
/*      */   }
/*      */   
/*      */   private SQLWarning receiveNoticeResponse() throws IOException {
/*      */     int nlen = this.pgStream.ReceiveInteger4();
/*      */     ServerErrorMessage warnMsg = new ServerErrorMessage(this.pgStream.ReceiveString(nlen - 4), this.logger.getLogLevel());
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" <=BE NoticeResponse(" + warnMsg.toString() + ")"); 
/*      */     return (SQLWarning)new PSQLWarning(warnMsg);
/*      */   }
/*      */   
/*      */   private String receiveCommandStatus() throws IOException {
/*      */     int l_len = this.pgStream.ReceiveInteger4();
/*      */     String status = this.pgStream.ReceiveString(l_len - 5);
/*      */     this.pgStream.Receive(1);
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" <=BE CommandStatus(" + status + ")"); 
/*      */     return status;
/*      */   }
/*      */   
/*      */   private void interpretCommandStatus(String status, ResultHandler handler) {
/*      */     int update_count = 0;
/*      */     long insert_oid = 0L;
/*      */     if (status.startsWith("INSERT") || status.startsWith("UPDATE") || status.startsWith("DELETE") || status.startsWith("MOVE"))
/*      */       try {
/*      */         update_count = Integer.parseInt(status.substring(1 + status.lastIndexOf(' ')));
/*      */         if (status.startsWith("INSERT"))
/*      */           insert_oid = Long.parseLong(status.substring(1 + status.indexOf(' '), status.lastIndexOf(' '))); 
/*      */       } catch (NumberFormatException nfe) {
/*      */         handler.handleError((SQLException)new PSQLException(GT.tr("Unable to interpret the update count in command completion tag: {0}.", status), PSQLState.CONNECTION_FAILURE));
/*      */         return;
/*      */       }  
/*      */     handler.handleCommandStatus(status, update_count, insert_oid);
/*      */   }
/*      */   
/*      */   private void receiveRFQ() throws IOException {
/*      */     if (this.pgStream.ReceiveInteger4() != 5)
/*      */       throw new IOException("unexpected length of ReadyForQuery message"); 
/*      */     char tStatus = (char)this.pgStream.ReceiveChar();
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug(" <=BE ReadyForQuery(" + tStatus + ")"); 
/*      */     switch (tStatus) {
/*      */       case 'I':
/*      */         this.protoConnection.setTransactionState(0);
/*      */         return;
/*      */       case 'T':
/*      */         this.protoConnection.setTransactionState(1);
/*      */         return;
/*      */       case 'E':
/*      */         this.protoConnection.setTransactionState(2);
/*      */         return;
/*      */     } 
/*      */     throw new IOException("unexpected transaction state in ReadyForQuery message: " + tStatus);
/*      */   }
/*      */   
/* 2198 */   private static final SimpleQuery EMPTY_QUERY = new SimpleQuery(new String[] { "" }, null);
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\QueryExecutorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */