/*     */ package org.postgresql.core.v2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLWarning;
/*     */ import java.util.Vector;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.PGNotification;
/*     */ import org.postgresql.copy.CopyOperation;
/*     */ import org.postgresql.core.Field;
/*     */ import org.postgresql.core.Logger;
/*     */ import org.postgresql.core.Notification;
/*     */ import org.postgresql.core.PGStream;
/*     */ import org.postgresql.core.ParameterList;
/*     */ import org.postgresql.core.Query;
/*     */ import org.postgresql.core.QueryExecutor;
/*     */ import org.postgresql.core.ResultCursor;
/*     */ import org.postgresql.core.ResultHandler;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class QueryExecutorImpl implements QueryExecutor {
/*     */   private final ProtocolConnectionImpl protoConnection;
/*     */   
/*     */   private final PGStream pgStream;
/*     */   
/*     */   private final Logger logger;
/*     */   
/*     */   public QueryExecutorImpl(ProtocolConnectionImpl protoConnection, PGStream pgStream, Logger logger) {
/*  29 */     this.protoConnection = protoConnection;
/*  30 */     this.pgStream = pgStream;
/*  31 */     this.logger = logger;
/*     */   }
/*     */   
/*     */   public Query createSimpleQuery(String sql) {
/*  39 */     return new V2Query(sql, false, this.protoConnection);
/*     */   }
/*     */   
/*     */   public Query createParameterizedQuery(String sql) {
/*  43 */     return new V2Query(sql, true, this.protoConnection);
/*     */   }
/*     */   
/*     */   public ParameterList createFastpathParameters(int count) {
/*  51 */     return new FastpathParameterList(count);
/*     */   }
/*     */   
/*     */   public synchronized byte[] fastpathCall(int fnid, ParameterList parameters, boolean suppressBegin) throws SQLException {
/*  56 */     if (this.protoConnection.getTransactionState() == 0 && !suppressBegin) {
/*  59 */       if (this.logger.logDebug())
/*  60 */         this.logger.debug("Issuing BEGIN before fastpath call."); 
/*  62 */       ResultHandler handler = new ResultHandler() {
/*     */           private boolean sawBegin = false;
/*     */           
/*  64 */           private SQLException sqle = null;
/*     */           
/*     */           public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {}
/*     */           
/*     */           public void handleCommandStatus(String status, int updateCount, long insertOID) {
/*  70 */             if (!this.sawBegin) {
/*  72 */               if (!status.equals("BEGIN"))
/*  73 */                 handleError((SQLException)new PSQLException(GT.tr("Expected command status BEGIN, got {0}.", status), PSQLState.PROTOCOL_VIOLATION)); 
/*  75 */               this.sawBegin = true;
/*     */             } else {
/*  79 */               handleError((SQLException)new PSQLException(GT.tr("Unexpected command status: {0}.", status), PSQLState.PROTOCOL_VIOLATION));
/*     */             } 
/*     */           }
/*     */           
/*     */           public void handleWarning(SQLWarning warning) {
/*  89 */             handleError(warning);
/*     */           }
/*     */           
/*     */           public void handleError(SQLException error) {
/*  93 */             if (this.sqle == null) {
/*  95 */               this.sqle = error;
/*     */             } else {
/*  99 */               this.sqle.setNextException(error);
/*     */             } 
/*     */           }
/*     */           
/*     */           public void handleCompletion() throws SQLException {
/* 104 */             if (this.sqle != null)
/* 105 */               throw this.sqle; 
/*     */           }
/*     */         };
/*     */       try {
/* 112 */         V2Query query = (V2Query)createSimpleQuery("");
/* 113 */         SimpleParameterList params = (SimpleParameterList)query.createParameterList();
/* 114 */         sendQuery(query, params, "BEGIN");
/* 115 */         processResults(query, handler, 0, 0);
/* 117 */       } catch (IOException ioe) {
/* 119 */         throw new PSQLException(GT.tr("An I/O error occured while sending to the backend."), PSQLState.CONNECTION_FAILURE, ioe);
/*     */       } 
/*     */     } 
/*     */     try {
/* 125 */       sendFastpathCall(fnid, (FastpathParameterList)parameters);
/* 126 */       return receiveFastpathResult();
/* 128 */     } catch (IOException ioe) {
/* 130 */       throw new PSQLException(GT.tr("An I/O error occured while sending to the backend."), PSQLState.CONNECTION_FAILURE, ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sendFastpathCall(int fnid, FastpathParameterList params) throws IOException {
/* 136 */     int count = params.getParameterCount();
/* 138 */     if (this.logger.logDebug())
/* 139 */       this.logger.debug(" FE=> FastpathCall(fnid=" + fnid + ",paramCount=" + count + ")"); 
/* 141 */     this.pgStream.SendChar(70);
/* 142 */     this.pgStream.SendChar(0);
/* 143 */     this.pgStream.SendInteger4(fnid);
/* 144 */     this.pgStream.SendInteger4(count);
/* 146 */     for (int i = 1; i <= count; i++)
/* 147 */       params.writeV2FastpathValue(i, this.pgStream); 
/* 149 */     this.pgStream.flush();
/*     */   }
/*     */   
/*     */   public synchronized void processNotifies() throws SQLException {
/* 154 */     if (this.protoConnection.getTransactionState() != 0)
/*     */       return; 
/*     */     try {
/* 158 */       while (this.pgStream.hasMessagePending()) {
/* 159 */         int c = this.pgStream.ReceiveChar();
/* 160 */         switch (c) {
/*     */           case 65:
/* 162 */             receiveAsyncNotify();
/*     */             continue;
/*     */           case 69:
/* 165 */             throw receiveErrorMessage();
/*     */           case 78:
/* 168 */             this.protoConnection.addWarning(receiveNotification());
/*     */             continue;
/*     */         } 
/* 171 */         throw new PSQLException(GT.tr("Unknown Response Type {0}.", new Character((char)c)), PSQLState.CONNECTION_FAILURE);
/*     */       } 
/* 174 */     } catch (IOException ioe) {
/* 175 */       throw new PSQLException(GT.tr("An I/O error occured while sending to the backend."), PSQLState.CONNECTION_FAILURE, ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   private byte[] receiveFastpathResult() throws IOException, SQLException {
/* 180 */     SQLException error = null;
/* 181 */     boolean endQuery = false;
/* 182 */     byte[] result = null;
/* 184 */     while (!endQuery) {
/*     */       SQLException newError;
/* 186 */       int c = this.pgStream.ReceiveChar();
/* 188 */       switch (c) {
/*     */         case 65:
/* 191 */           receiveAsyncNotify();
/*     */           continue;
/*     */         case 69:
/* 195 */           newError = receiveErrorMessage();
/* 196 */           if (error == null) {
/* 197 */             error = newError;
/*     */             continue;
/*     */           } 
/* 199 */           error.setNextException(newError);
/*     */           continue;
/*     */         case 78:
/* 204 */           this.protoConnection.addWarning(receiveNotification());
/*     */           continue;
/*     */         case 86:
/* 208 */           c = this.pgStream.ReceiveChar();
/* 209 */           if (c == 71) {
/* 211 */             if (this.logger.logDebug())
/* 212 */               this.logger.debug(" <=BE FastpathResult"); 
/* 215 */             int len = this.pgStream.ReceiveInteger4();
/* 216 */             result = this.pgStream.Receive(len);
/* 217 */             c = this.pgStream.ReceiveChar();
/* 221 */           } else if (this.logger.logDebug()) {
/* 222 */             this.logger.debug(" <=BE FastpathVoidResult");
/*     */           } 
/* 225 */           if (c != 48)
/* 226 */             throw new PSQLException(GT.tr("Unknown Response Type {0}.", new Character((char)c)), PSQLState.CONNECTION_FAILURE); 
/*     */           continue;
/*     */         case 90:
/* 231 */           if (this.logger.logDebug())
/* 232 */             this.logger.debug(" <=BE ReadyForQuery"); 
/* 233 */           endQuery = true;
/*     */           continue;
/*     */       } 
/* 237 */       throw new PSQLException(GT.tr("Unknown Response Type {0}.", new Character((char)c)), PSQLState.CONNECTION_FAILURE);
/*     */     } 
/* 243 */     if (error != null)
/* 244 */       throw error; 
/* 246 */     return result;
/*     */   }
/*     */   
/*     */   public synchronized void execute(Query query, ParameterList parameters, ResultHandler handler, int maxRows, int fetchSize, int flags) throws SQLException {
/* 259 */     execute((V2Query)query, (SimpleParameterList)parameters, handler, maxRows, flags);
/*     */   }
/*     */   
/*     */   public synchronized void execute(Query[] queries, ParameterList[] parameters, ResultHandler handler, int maxRows, int fetchSize, int flags) throws SQLException {
/* 269 */     final ResultHandler delegateHandler = handler;
/* 270 */     handler = new ResultHandler() {
/*     */         public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {
/* 272 */           delegateHandler.handleResultRows(fromQuery, fields, tuples, cursor);
/*     */         }
/*     */         
/*     */         public void handleCommandStatus(String status, int updateCount, long insertOID) {
/* 276 */           delegateHandler.handleCommandStatus(status, updateCount, insertOID);
/*     */         }
/*     */         
/*     */         public void handleWarning(SQLWarning warning) {
/* 280 */           delegateHandler.handleWarning(warning);
/*     */         }
/*     */         
/*     */         public void handleError(SQLException error) {
/* 284 */           delegateHandler.handleError(error);
/*     */         }
/*     */         
/*     */         public void handleCompletion() throws SQLException {}
/*     */       };
/* 291 */     for (int i = 0; i < queries.length; i++)
/* 292 */       execute((V2Query)queries[i], (SimpleParameterList)parameters[i], handler, maxRows, flags); 
/* 294 */     delegateHandler.handleCompletion();
/*     */   }
/*     */   
/*     */   public void fetch(ResultCursor cursor, ResultHandler handler, int rows) throws SQLException {
/* 298 */     throw Driver.notImplemented(getClass(), "fetch(ResultCursor,ResultHandler,int)");
/*     */   }
/*     */   
/*     */   private void execute(V2Query query, SimpleParameterList parameters, ResultHandler handler, int maxRows, int flags) throws SQLException {
/* 309 */     if ((flags & 0x20) != 0)
/*     */       return; 
/* 312 */     if (parameters == null)
/* 313 */       parameters = (SimpleParameterList)query.createParameterList(); 
/* 315 */     parameters.checkAllParametersSet();
/* 317 */     String queryPrefix = null;
/* 318 */     if (this.protoConnection.getTransactionState() == 0 && (flags & 0x10) == 0) {
/* 322 */       queryPrefix = "BEGIN;";
/* 325 */       final ResultHandler delegateHandler = handler;
/* 326 */       handler = new ResultHandler() {
/*     */           private boolean sawBegin = false;
/*     */           
/*     */           public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {
/* 330 */             if (this.sawBegin)
/* 331 */               delegateHandler.handleResultRows(fromQuery, fields, tuples, cursor); 
/*     */           }
/*     */           
/*     */           public void handleCommandStatus(String status, int updateCount, long insertOID) {
/* 335 */             if (!this.sawBegin) {
/* 337 */               if (!status.equals("BEGIN"))
/* 338 */                 handleError((SQLException)new PSQLException(GT.tr("Expected command status BEGIN, got {0}.", status), PSQLState.PROTOCOL_VIOLATION)); 
/* 340 */               this.sawBegin = true;
/*     */             } else {
/* 344 */               delegateHandler.handleCommandStatus(status, updateCount, insertOID);
/*     */             } 
/*     */           }
/*     */           
/*     */           public void handleWarning(SQLWarning warning) {
/* 349 */             delegateHandler.handleWarning(warning);
/*     */           }
/*     */           
/*     */           public void handleError(SQLException error) {
/* 353 */             delegateHandler.handleError(error);
/*     */           }
/*     */           
/*     */           public void handleCompletion() throws SQLException {
/* 357 */             delegateHandler.handleCompletion();
/*     */           }
/*     */         };
/*     */     } 
/*     */     try {
/* 364 */       sendQuery(query, parameters, queryPrefix);
/* 365 */       processResults(query, handler, maxRows, flags);
/* 367 */     } catch (IOException e) {
/* 369 */       this.protoConnection.close();
/* 370 */       handler.handleError((SQLException)new PSQLException(GT.tr("An I/O error occured while sending to the backend."), PSQLState.CONNECTION_FAILURE, e));
/*     */     } 
/* 373 */     handler.handleCompletion();
/*     */   }
/*     */   
/*     */   protected void sendQuery(V2Query query, SimpleParameterList params, String queryPrefix) throws IOException {
/* 380 */     if (this.logger.logDebug())
/* 381 */       this.logger.debug(" FE=> Query(\"" + ((queryPrefix == null) ? "" : queryPrefix) + query.toString(params) + "\")"); 
/* 383 */     this.pgStream.SendChar(81);
/* 385 */     Writer encodingWriter = this.pgStream.getEncodingWriter();
/* 387 */     if (queryPrefix != null)
/* 388 */       encodingWriter.write(queryPrefix); 
/* 390 */     String[] fragments = query.getFragments();
/* 391 */     for (int i = 0; i < fragments.length; i++) {
/* 393 */       encodingWriter.write(fragments[i]);
/* 394 */       if (i < params.getParameterCount())
/* 395 */         params.writeV2Value(i + 1, encodingWriter); 
/*     */     } 
/* 398 */     encodingWriter.write(0);
/* 399 */     this.pgStream.flush();
/*     */   }
/*     */   
/*     */   protected void processResults(Query originalQuery, ResultHandler handler, int maxRows, int flags) throws IOException {
/* 403 */     boolean bothRowsAndStatus = ((flags & 0x40) != 0);
/* 404 */     Field[] fields = null;
/* 405 */     Vector<Object> tuples = null;
/* 407 */     boolean endQuery = false;
/* 408 */     while (!endQuery) {
/*     */       Object tuple;
/*     */       String status;
/*     */       int i;
/*     */       Object object1;
/*     */       String portalName;
/* 410 */       int c = this.pgStream.ReceiveChar();
/* 412 */       switch (c) {
/*     */         case 65:
/* 415 */           receiveAsyncNotify();
/*     */           continue;
/*     */         case 66:
/* 420 */           if (fields == null)
/* 421 */             throw new IOException("Data transfer before field metadata"); 
/* 423 */           if (this.logger.logDebug())
/* 424 */             this.logger.debug(" <=BE BinaryRow"); 
/* 426 */           tuple = null;
/*     */           try {
/* 428 */             tuple = this.pgStream.ReceiveTupleV2(fields.length, true);
/* 429 */           } catch (OutOfMemoryError oome) {
/* 430 */             if (maxRows == 0 || tuples.size() < maxRows)
/* 431 */               handler.handleError((SQLException)new PSQLException(GT.tr("Ran out of memory retrieving query results."), PSQLState.OUT_OF_MEMORY, oome)); 
/*     */           } 
/* 435 */           for (i = 0; i < fields.length; i++)
/* 436 */             fields[i].setFormat(1); 
/* 437 */           if (maxRows == 0 || tuples.size() < maxRows)
/* 438 */             tuples.addElement(tuple); 
/*     */           continue;
/*     */         case 67:
/* 443 */           status = this.pgStream.ReceiveString();
/* 445 */           if (this.logger.logDebug())
/* 446 */             this.logger.debug(" <=BE CommandStatus(" + status + ")"); 
/* 448 */           if (fields != null) {
/* 450 */             handler.handleResultRows(originalQuery, fields, tuples, null);
/* 451 */             fields = null;
/* 453 */             if (bothRowsAndStatus)
/* 454 */               interpretCommandStatus(status, handler); 
/*     */             continue;
/*     */           } 
/* 458 */           interpretCommandStatus(status, handler);
/*     */           continue;
/*     */         case 68:
/* 465 */           if (fields == null)
/* 466 */             throw new IOException("Data transfer before field metadata"); 
/* 468 */           if (this.logger.logDebug())
/* 469 */             this.logger.debug(" <=BE DataRow"); 
/* 471 */           object1 = null;
/*     */           try {
/* 473 */             object1 = this.pgStream.ReceiveTupleV2(fields.length, false);
/* 474 */           } catch (OutOfMemoryError oome) {
/* 475 */             if (maxRows == 0 || tuples.size() < maxRows)
/* 476 */               handler.handleError((SQLException)new PSQLException(GT.tr("Ran out of memory retrieving query results."), PSQLState.OUT_OF_MEMORY, oome)); 
/*     */           } 
/* 478 */           if (maxRows == 0 || tuples.size() < maxRows)
/* 479 */             tuples.addElement(object1); 
/*     */           continue;
/*     */         case 69:
/* 485 */           handler.handleError(receiveErrorMessage());
/*     */           continue;
/*     */         case 73:
/* 490 */           if (this.logger.logDebug())
/* 491 */             this.logger.debug(" <=BE EmptyQuery"); 
/* 492 */           c = this.pgStream.ReceiveChar();
/* 493 */           if (c != 0)
/* 494 */             throw new IOException("Expected \\0 after EmptyQuery, got: " + c); 
/*     */           continue;
/*     */         case 78:
/* 498 */           handler.handleWarning(receiveNotification());
/*     */           continue;
/*     */         case 80:
/* 502 */           portalName = this.pgStream.ReceiveString();
/* 503 */           if (this.logger.logDebug())
/* 504 */             this.logger.debug(" <=BE PortalName(" + portalName + ")"); 
/*     */           continue;
/*     */         case 84:
/* 508 */           fields = receiveFields();
/* 509 */           tuples = new Vector();
/*     */           continue;
/*     */         case 90:
/* 513 */           if (this.logger.logDebug())
/* 514 */             this.logger.debug(" <=BE ReadyForQuery"); 
/* 515 */           endQuery = true;
/*     */           continue;
/*     */       } 
/* 519 */       throw new IOException("Unexpected packet type: " + c);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Field[] receiveFields() throws IOException {
/* 530 */     int size = this.pgStream.ReceiveInteger2();
/* 531 */     Field[] fields = new Field[size];
/* 533 */     if (this.logger.logDebug())
/* 534 */       this.logger.debug(" <=BE RowDescription(" + fields.length + ")"); 
/* 536 */     for (int i = 0; i < fields.length; i++) {
/* 538 */       String columnLabel = this.pgStream.ReceiveString();
/* 539 */       int typeOid = this.pgStream.ReceiveInteger4();
/* 540 */       int typeLength = this.pgStream.ReceiveInteger2();
/* 541 */       int typeModifier = this.pgStream.ReceiveInteger4();
/* 542 */       fields[i] = new Field(columnLabel, columnLabel, typeOid, typeLength, typeModifier, 0, 0);
/*     */     } 
/* 545 */     return fields;
/*     */   }
/*     */   
/*     */   private void receiveAsyncNotify() throws IOException {
/* 549 */     int pid = this.pgStream.ReceiveInteger4();
/* 550 */     String msg = this.pgStream.ReceiveString();
/* 552 */     if (this.logger.logDebug())
/* 553 */       this.logger.debug(" <=BE AsyncNotify(pid=" + pid + ",msg=" + msg + ")"); 
/* 555 */     this.protoConnection.addNotification((PGNotification)new Notification(msg, pid));
/*     */   }
/*     */   
/*     */   private SQLException receiveErrorMessage() throws IOException {
/* 559 */     String errorMsg = this.pgStream.ReceiveString().trim();
/* 560 */     if (this.logger.logDebug())
/* 561 */       this.logger.debug(" <=BE ErrorResponse(" + errorMsg + ")"); 
/* 562 */     return (SQLException)new PSQLException(errorMsg, PSQLState.UNKNOWN_STATE);
/*     */   }
/*     */   
/*     */   private SQLWarning receiveNotification() throws IOException {
/* 566 */     String warnMsg = this.pgStream.ReceiveString();
/* 572 */     int severityMark = warnMsg.indexOf(":");
/* 573 */     warnMsg = warnMsg.substring(severityMark + 1).trim();
/* 574 */     if (this.logger.logDebug())
/* 575 */       this.logger.debug(" <=BE NoticeResponse(" + warnMsg + ")"); 
/* 576 */     return new SQLWarning(warnMsg);
/*     */   }
/*     */   
/*     */   private void interpretCommandStatus(String status, ResultHandler handler) throws IOException {
/* 580 */     int update_count = 0;
/* 581 */     long insert_oid = 0L;
/* 583 */     if (status.equals("BEGIN")) {
/* 584 */       this.protoConnection.setTransactionState(1);
/* 585 */     } else if (status.equals("COMMIT") || status.equals("ROLLBACK")) {
/* 586 */       this.protoConnection.setTransactionState(0);
/* 587 */     } else if (status.startsWith("INSERT") || status.startsWith("UPDATE") || status.startsWith("DELETE") || status.startsWith("MOVE")) {
/*     */       try {
/* 591 */         update_count = Integer.parseInt(status.substring(1 + status.lastIndexOf(' ')));
/* 592 */         if (status.startsWith("INSERT"))
/* 593 */           insert_oid = Long.parseLong(status.substring(1 + status.indexOf(' '), status.lastIndexOf(' '))); 
/* 596 */       } catch (NumberFormatException nfe) {
/* 598 */         handler.handleError((SQLException)new PSQLException(GT.tr("Unable to interpret the update count in command completion tag: {0}.", status), PSQLState.CONNECTION_FAILURE));
/*     */         return;
/*     */       } 
/*     */     } 
/* 603 */     handler.handleCommandStatus(status, update_count, insert_oid);
/*     */   }
/*     */   
/*     */   public CopyOperation startCopy(String sql, boolean suppressBegin) throws SQLException {
/* 611 */     throw new PSQLException(GT.tr("Copy not implemented for protocol version 2"), PSQLState.NOT_IMPLEMENTED);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v2\QueryExecutorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */