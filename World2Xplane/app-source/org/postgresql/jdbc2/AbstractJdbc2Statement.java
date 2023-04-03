/*      */ package org.postgresql.jdbc2;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Array;
/*      */ import java.sql.BatchUpdateException;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Connection;
/*      */ import java.sql.Date;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ import org.postgresql.Driver;
/*      */ import org.postgresql.core.BaseConnection;
/*      */ import org.postgresql.core.BaseStatement;
/*      */ import org.postgresql.core.Field;
/*      */ import org.postgresql.core.ParameterList;
/*      */ import org.postgresql.core.Query;
/*      */ import org.postgresql.core.ResultCursor;
/*      */ import org.postgresql.core.ResultHandler;
/*      */ import org.postgresql.core.types.PGBigDecimal;
/*      */ import org.postgresql.core.types.PGBoolean;
/*      */ import org.postgresql.core.types.PGByte;
/*      */ import org.postgresql.core.types.PGDouble;
/*      */ import org.postgresql.core.types.PGFloat;
/*      */ import org.postgresql.core.types.PGInteger;
/*      */ import org.postgresql.core.types.PGLong;
/*      */ import org.postgresql.core.types.PGNumber;
/*      */ import org.postgresql.core.types.PGShort;
/*      */ import org.postgresql.core.types.PGType;
/*      */ import org.postgresql.core.types.PGUnknown;
/*      */ import org.postgresql.largeobject.LargeObject;
/*      */ import org.postgresql.largeobject.LargeObjectManager;
/*      */ import org.postgresql.util.GT;
/*      */ import org.postgresql.util.PGobject;
/*      */ import org.postgresql.util.PSQLException;
/*      */ import org.postgresql.util.PSQLState;
/*      */ 
/*      */ public abstract class AbstractJdbc2Statement implements BaseStatement {
/*   37 */   protected ArrayList batchStatements = null;
/*      */   
/*   38 */   protected ArrayList batchParameters = null;
/*      */   
/*      */   protected final int resultsettype;
/*      */   
/*      */   protected final int concurrency;
/*      */   
/*   41 */   protected int fetchdirection = 1000;
/*      */   
/*      */   protected boolean wantsGeneratedKeysOnce = false;
/*      */   
/*      */   public boolean wantsGeneratedKeysAlways = false;
/*      */   
/*      */   protected BaseConnection connection;
/*      */   
/*   61 */   protected SQLWarning warnings = null;
/*      */   
/*   63 */   protected SQLWarning lastWarning = null;
/*      */   
/*   66 */   protected int maxrows = 0;
/*      */   
/*   69 */   protected int fetchSize = 0;
/*      */   
/*   72 */   protected int timeout = 0;
/*      */   
/*      */   protected boolean replaceProcessingEnabled = true;
/*      */   
/*   77 */   protected ResultWrapper result = null;
/*      */   
/*   80 */   protected ResultWrapper firstUnclosedResult = null;
/*      */   
/*   83 */   protected ResultWrapper generatedKeys = null;
/*      */   
/*      */   protected boolean adjustIndex = false;
/*      */   
/*      */   protected boolean outParmBeforeFunc = false;
/*      */   
/*      */   private static final short IN_SQLCODE = 0;
/*      */   
/*      */   private static final short IN_STRING = 1;
/*      */   
/*      */   private static final short IN_IDENTIFIER = 6;
/*      */   
/*      */   private static final short BACKSLASH = 2;
/*      */   
/*      */   private static final short ESC_TIMEDATE = 3;
/*      */   
/*      */   private static final short ESC_FUNCTION = 4;
/*      */   
/*      */   private static final short ESC_OUTERJOIN = 5;
/*      */   
/*      */   private static final short ESC_ESCAPECHAR = 7;
/*      */   
/*      */   protected final Query preparedQuery;
/*      */   
/*      */   protected final ParameterList preparedParameters;
/*      */   
/*      */   protected Query lastSimpleQuery;
/*      */   
/*      */   protected int m_prepareThreshold;
/*      */   
/*  114 */   protected int m_useCount = 0;
/*      */   
/*      */   private boolean isFunction;
/*      */   
/*      */   private int[] functionReturnType;
/*      */   
/*      */   private int[] testReturn;
/*      */   
/*      */   private boolean returnTypeSet;
/*      */   
/*      */   protected Object[] callResult;
/*      */   
/*  126 */   protected int maxfieldSize = 0;
/*      */   
/*      */   protected boolean isClosed;
/*      */   
/*      */   private int lastIndex;
/*      */   
/*      */   public ResultSet createDriverResultSet(Field[] fields, Vector tuples) throws SQLException {
/*  131 */     return createResultSet(null, fields, tuples, null);
/*      */   }
/*      */   
/*      */   public BaseConnection getPGConnection() {
/*  170 */     return this.connection;
/*      */   }
/*      */   
/*      */   public String getFetchingCursorName() {
/*  174 */     return null;
/*      */   }
/*      */   
/*      */   public int getFetchSize() {
/*  178 */     return this.fetchSize;
/*      */   }
/*      */   
/*      */   protected boolean wantsScrollableResultSet() {
/*  182 */     return (this.resultsettype != 1003);
/*      */   }
/*      */   
/*      */   protected boolean wantsHoldableResultSet() {
/*  186 */     return false;
/*      */   }
/*      */   
/*      */   public class StatementResultHandler implements ResultHandler {
/*      */     private SQLException error;
/*      */     
/*      */     private ResultWrapper results;
/*      */     
/*      */     ResultWrapper getResults() {
/*  198 */       return this.results;
/*      */     }
/*      */     
/*      */     private void append(ResultWrapper newResult) {
/*  202 */       if (this.results == null) {
/*  203 */         this.results = newResult;
/*      */       } else {
/*  205 */         this.results.append(newResult);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {
/*      */       try {
/*  211 */         ResultSet rs = AbstractJdbc2Statement.this.createResultSet(fromQuery, fields, tuples, cursor);
/*  212 */         append(new ResultWrapper(rs));
/*  214 */       } catch (SQLException e) {
/*  216 */         handleError(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void handleCommandStatus(String status, int updateCount, long insertOID) {
/*  221 */       append(new ResultWrapper(updateCount, insertOID));
/*      */     }
/*      */     
/*      */     public void handleWarning(SQLWarning warning) {
/*  225 */       AbstractJdbc2Statement.this.addWarning(warning);
/*      */     }
/*      */     
/*      */     public void handleError(SQLException newError) {
/*  229 */       if (this.error == null) {
/*  230 */         this.error = newError;
/*      */       } else {
/*  232 */         this.error.setNextException(newError);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void handleCompletion() throws SQLException {
/*  236 */       if (this.error != null)
/*  237 */         throw this.error; 
/*      */     }
/*      */   }
/*      */   
/*      */   public ResultSet executeQuery(String p_sql) throws SQLException {
/*  250 */     if (this.preparedQuery != null)
/*  251 */       throw new PSQLException(GT.tr("Can''t use query methods that take a query string on a PreparedStatement."), PSQLState.WRONG_OBJECT_TYPE); 
/*  254 */     if (!executeWithFlags(p_sql, 0))
/*  255 */       throw new PSQLException(GT.tr("No results were returned by the query."), PSQLState.NO_DATA); 
/*  257 */     if (this.result.getNext() != null)
/*  258 */       throw new PSQLException(GT.tr("Multiple ResultSets were returned by the query."), PSQLState.TOO_MANY_RESULTS); 
/*  261 */     return this.result.getResultSet();
/*      */   }
/*      */   
/*      */   public ResultSet executeQuery() throws SQLException {
/*  273 */     if (!executeWithFlags(0))
/*  274 */       throw new PSQLException(GT.tr("No results were returned by the query."), PSQLState.NO_DATA); 
/*  276 */     if (this.result.getNext() != null)
/*  277 */       throw new PSQLException(GT.tr("Multiple ResultSets were returned by the query."), PSQLState.TOO_MANY_RESULTS); 
/*  279 */     return this.result.getResultSet();
/*      */   }
/*      */   
/*      */   public int executeUpdate(String p_sql) throws SQLException {
/*  293 */     if (this.preparedQuery != null)
/*  294 */       throw new PSQLException(GT.tr("Can''t use query methods that take a query string on a PreparedStatement."), PSQLState.WRONG_OBJECT_TYPE); 
/*  296 */     if (this.isFunction) {
/*  298 */       executeWithFlags(p_sql, 0);
/*  299 */       return 0;
/*      */     } 
/*  302 */     executeWithFlags(p_sql, 4);
/*  304 */     ResultWrapper iter = this.result;
/*  305 */     while (iter != null) {
/*  306 */       if (iter.getResultSet() != null)
/*  307 */         throw new PSQLException(GT.tr("A result was returned when none was expected."), PSQLState.TOO_MANY_RESULTS); 
/*  311 */       iter = iter.getNext();
/*      */     } 
/*  314 */     return getUpdateCount();
/*      */   }
/*      */   
/*      */   public int executeUpdate() throws SQLException {
/*  328 */     if (this.isFunction) {
/*  330 */       executeWithFlags(0);
/*  331 */       return 0;
/*      */     } 
/*  334 */     executeWithFlags(4);
/*  336 */     ResultWrapper iter = this.result;
/*  337 */     while (iter != null) {
/*  338 */       if (iter.getResultSet() != null)
/*  339 */         throw new PSQLException(GT.tr("A result was returned when none was expected."), PSQLState.TOO_MANY_RESULTS); 
/*  343 */       iter = iter.getNext();
/*      */     } 
/*  346 */     return getUpdateCount();
/*      */   }
/*      */   
/*      */   public boolean execute(String p_sql) throws SQLException {
/*  362 */     if (this.preparedQuery != null)
/*  363 */       throw new PSQLException(GT.tr("Can''t use query methods that take a query string on a PreparedStatement."), PSQLState.WRONG_OBJECT_TYPE); 
/*  366 */     return executeWithFlags(p_sql, 0);
/*      */   }
/*      */   
/*      */   public boolean executeWithFlags(String p_sql, int flags) throws SQLException {
/*  371 */     checkClosed();
/*  372 */     p_sql = replaceProcessing(p_sql);
/*  373 */     Query simpleQuery = this.connection.getQueryExecutor().createSimpleQuery(p_sql);
/*  374 */     execute(simpleQuery, null, 0x1 | flags);
/*  375 */     this.lastSimpleQuery = simpleQuery;
/*  376 */     return (this.result != null && this.result.getResultSet() != null);
/*      */   }
/*      */   
/*      */   public boolean execute() throws SQLException {
/*  381 */     return executeWithFlags(0);
/*      */   }
/*      */   
/*      */   public boolean executeWithFlags(int flags) throws SQLException {
/*  386 */     checkClosed();
/*  388 */     execute(this.preparedQuery, this.preparedParameters, flags);
/*  393 */     if (this.isFunction && this.returnTypeSet) {
/*  395 */       if (this.result == null || this.result.getResultSet() == null)
/*  396 */         throw new PSQLException(GT.tr("A CallableStatement was executed with nothing returned."), PSQLState.NO_DATA); 
/*  398 */       ResultSet rs = this.result.getResultSet();
/*  399 */       if (!rs.next())
/*  400 */         throw new PSQLException(GT.tr("A CallableStatement was executed with nothing returned."), PSQLState.NO_DATA); 
/*  403 */       int cols = rs.getMetaData().getColumnCount();
/*  405 */       int outParameterCount = this.preparedParameters.getOutParameterCount();
/*  407 */       if (cols != outParameterCount)
/*  408 */         throw new PSQLException(GT.tr("A CallableStatement was executed with an invalid number of parameters"), PSQLState.SYNTAX_ERROR); 
/*  411 */       this.lastIndex = 0;
/*  414 */       this.callResult = new Object[this.preparedParameters.getParameterCount() + 1];
/*  417 */       for (int i = 0, j = 0; i < cols; i++, j++) {
/*  423 */         for (; j < this.functionReturnType.length && this.functionReturnType[j] == 0; j++);
/*  425 */         this.callResult[j] = rs.getObject(i + 1);
/*  426 */         int columnType = rs.getMetaData().getColumnType(i + 1);
/*  428 */         if (columnType != this.functionReturnType[j])
/*  431 */           if (columnType == 8 && this.functionReturnType[j] == 7) {
/*  434 */             if (this.callResult[j] != null)
/*  435 */               this.callResult[j] = new Float(((Double)this.callResult[j]).floatValue()); 
/*      */           } else {
/*  439 */             throw new PSQLException(GT.tr("A CallableStatement function was executed and the out parameter {0} was of type {1} however type {2} was registered.", new Object[] { new Integer(i + 1), "java.sql.Types=" + columnType, "java.sql.Types=" + this.functionReturnType[j] }), PSQLState.DATA_TYPE_MISMATCH);
/*      */           }  
/*      */       } 
/*  447 */       rs.close();
/*  448 */       this.result = null;
/*  449 */       return false;
/*      */     } 
/*  452 */     return (this.result != null && this.result.getResultSet() != null);
/*      */   }
/*      */   
/*      */   protected void closeForNextExecution() throws SQLException {
/*  457 */     clearWarnings();
/*  460 */     while (this.firstUnclosedResult != null) {
/*  462 */       if (this.firstUnclosedResult.getResultSet() != null)
/*  463 */         this.firstUnclosedResult.getResultSet().close(); 
/*  464 */       this.firstUnclosedResult = this.firstUnclosedResult.getNext();
/*      */     } 
/*  466 */     this.result = null;
/*  468 */     if (this.lastSimpleQuery != null) {
/*  469 */       this.lastSimpleQuery.close();
/*  470 */       this.lastSimpleQuery = null;
/*      */     } 
/*  473 */     if (this.generatedKeys != null) {
/*  474 */       if (this.generatedKeys.getResultSet() != null)
/*  475 */         this.generatedKeys.getResultSet().close(); 
/*  477 */       this.generatedKeys = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void execute(Query queryToExecute, ParameterList queryParameters, int flags) throws SQLException {
/*  482 */     closeForNextExecution();
/*  485 */     if (this.fetchSize > 0 && !wantsScrollableResultSet() && !this.connection.getAutoCommit() && !wantsHoldableResultSet())
/*  486 */       flags |= 0x8; 
/*  488 */     if (this.wantsGeneratedKeysOnce || this.wantsGeneratedKeysAlways) {
/*  490 */       flags |= 0x40;
/*  495 */       if ((flags & 0x4) != 0)
/*  496 */         flags &= 0xFFFFFFFB; 
/*      */     } 
/*  500 */     if (this.preparedQuery != null) {
/*  502 */       this.m_useCount++;
/*  503 */       if (this.m_prepareThreshold == 0 || this.m_useCount < this.m_prepareThreshold)
/*  504 */         flags |= 0x1; 
/*      */     } 
/*  507 */     if (this.connection.getAutoCommit())
/*  508 */       flags |= 0x10; 
/*  510 */     StatementResultHandler handler = new StatementResultHandler();
/*  511 */     this.result = null;
/*  512 */     this.connection.getQueryExecutor().execute(queryToExecute, queryParameters, handler, this.maxrows, this.fetchSize, flags);
/*  518 */     this.result = this.firstUnclosedResult = handler.getResults();
/*  520 */     if (this.wantsGeneratedKeysOnce || this.wantsGeneratedKeysAlways) {
/*  522 */       this.generatedKeys = this.result;
/*  523 */       this.result = this.result.getNext();
/*  525 */       if (this.wantsGeneratedKeysOnce)
/*  526 */         this.wantsGeneratedKeysOnce = false; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setCursorName(String name) throws SQLException {
/*  549 */     checkClosed();
/*      */   }
/*      */   
/*      */   public AbstractJdbc2Statement(AbstractJdbc2Connection c, int rsType, int rsConcurrency) throws SQLException {
/*  553 */     this.isClosed = false;
/*  554 */     this.lastIndex = 0;
/*      */     this.connection = c;
/*      */     this.preparedQuery = null;
/*      */     this.preparedParameters = null;
/*      */     this.lastSimpleQuery = null;
/*      */     this.resultsettype = rsType;
/*      */     this.concurrency = rsConcurrency;
/*      */   }
/*      */   
/*      */   public AbstractJdbc2Statement(AbstractJdbc2Connection connection, String sql, boolean isCallable, int rsType, int rsConcurrency) throws SQLException {
/*      */     this.isClosed = false;
/*  554 */     this.lastIndex = 0;
/*      */     this.connection = connection;
/*      */     this.lastSimpleQuery = null;
/*      */     String parsed_sql = replaceProcessing(sql);
/*      */     if (isCallable)
/*      */       parsed_sql = modifyJdbcCall(parsed_sql); 
/*      */     this.preparedQuery = connection.getQueryExecutor().createParameterizedQuery(parsed_sql);
/*      */     this.preparedParameters = this.preparedQuery.createParameterList();
/*      */     int inParamCount = this.preparedParameters.getInParameterCount() + 1;
/*      */     this.testReturn = new int[inParamCount];
/*      */     this.functionReturnType = new int[inParamCount];
/*      */     this.resultsettype = rsType;
/*      */     this.concurrency = rsConcurrency;
/*      */   }
/*      */   
/*      */   public int getUpdateCount() throws SQLException {
/*  565 */     checkClosed();
/*  566 */     if (this.result == null || this.result.getResultSet() != null)
/*  567 */       return -1; 
/*  569 */     return this.result.getUpdateCount();
/*      */   }
/*      */   
/*      */   public boolean getMoreResults() throws SQLException {
/*  581 */     if (this.result == null)
/*  582 */       return false; 
/*  584 */     this.result = this.result.getNext();
/*  587 */     while (this.firstUnclosedResult != this.result) {
/*  589 */       if (this.firstUnclosedResult.getResultSet() != null)
/*  590 */         this.firstUnclosedResult.getResultSet().close(); 
/*  591 */       this.firstUnclosedResult = this.firstUnclosedResult.getNext();
/*      */     } 
/*  594 */     return (this.result != null && this.result.getResultSet() != null);
/*      */   }
/*      */   
/*      */   public int getMaxRows() throws SQLException {
/*  607 */     checkClosed();
/*  608 */     return this.maxrows;
/*      */   }
/*      */   
/*      */   public void setMaxRows(int max) throws SQLException {
/*  620 */     checkClosed();
/*  621 */     if (max < 0)
/*  622 */       throw new PSQLException(GT.tr("Maximum number of rows must be a value grater than or equal to 0."), PSQLState.INVALID_PARAMETER_VALUE); 
/*  624 */     this.maxrows = max;
/*      */   }
/*      */   
/*      */   public void setEscapeProcessing(boolean enable) throws SQLException {
/*  636 */     checkClosed();
/*  637 */     this.replaceProcessingEnabled = enable;
/*      */   }
/*      */   
/*      */   public int getQueryTimeout() throws SQLException {
/*  650 */     checkClosed();
/*  651 */     return this.timeout;
/*      */   }
/*      */   
/*      */   public void setQueryTimeout(int seconds) throws SQLException {
/*  662 */     checkClosed();
/*  663 */     if (seconds < 0)
/*  664 */       throw new PSQLException(GT.tr("Query timeout must be a value greater than or equals to 0."), PSQLState.INVALID_PARAMETER_VALUE); 
/*  667 */     if (seconds > 0)
/*  668 */       throw Driver.notImplemented(getClass(), "setQueryTimeout(int)"); 
/*  670 */     this.timeout = seconds;
/*      */   }
/*      */   
/*      */   public void addWarning(SQLWarning warn) {
/*  683 */     if (this.warnings == null) {
/*  684 */       this.warnings = warn;
/*  685 */       this.lastWarning = warn;
/*      */     } else {
/*  687 */       this.lastWarning.setNextWarning(warn);
/*  688 */       this.lastWarning = warn;
/*      */     } 
/*      */   }
/*      */   
/*      */   public SQLWarning getWarnings() throws SQLException {
/*  710 */     checkClosed();
/*  711 */     return this.warnings;
/*      */   }
/*      */   
/*      */   public int getMaxFieldSize() throws SQLException {
/*  726 */     return this.maxfieldSize;
/*      */   }
/*      */   
/*      */   public void setMaxFieldSize(int max) throws SQLException {
/*  737 */     checkClosed();
/*  738 */     if (max < 0)
/*  739 */       throw new PSQLException(GT.tr("The maximum field size must be a value greater than or equal to 0."), PSQLState.INVALID_PARAMETER_VALUE); 
/*  741 */     this.maxfieldSize = max;
/*      */   }
/*      */   
/*      */   public void clearWarnings() throws SQLException {
/*  752 */     this.warnings = null;
/*  753 */     this.lastWarning = null;
/*      */   }
/*      */   
/*      */   public ResultSet getResultSet() throws SQLException {
/*  765 */     checkClosed();
/*  767 */     if (this.result == null)
/*  768 */       return null; 
/*  770 */     return this.result.getResultSet();
/*      */   }
/*      */   
/*      */   public void close() throws SQLException {
/*  788 */     if (this.isClosed)
/*      */       return; 
/*  791 */     closeForNextExecution();
/*  793 */     if (this.preparedQuery != null)
/*  794 */       this.preparedQuery.close(); 
/*  796 */     this.isClosed = true;
/*      */   }
/*      */   
/*      */   protected void finalize() {
/*      */     try {
/*  806 */       close();
/*  808 */     } catch (SQLException e) {}
/*      */   }
/*      */   
/*      */   protected String replaceProcessing(String p_sql) throws SQLException {
/*  826 */     if (this.replaceProcessingEnabled) {
/*  830 */       int len = p_sql.length();
/*  831 */       StringBuffer newsql = new StringBuffer(len);
/*  832 */       int i = 0;
/*  833 */       while (i < len) {
/*  834 */         i = parseSql(p_sql, i, newsql, false, this.connection.getStandardConformingStrings());
/*  840 */         if (i < len) {
/*  841 */           newsql.append(p_sql.charAt(i));
/*  842 */           i++;
/*      */         } 
/*      */       } 
/*  845 */       return newsql.toString();
/*      */     } 
/*  849 */     return p_sql;
/*      */   }
/*      */   
/*      */   protected static int parseSql(String p_sql, int i, StringBuffer newsql, boolean stopOnComma, boolean stdStrings) throws SQLException {
/*  868 */     short state = 0;
/*  869 */     int len = p_sql.length();
/*  870 */     int nestedParenthesis = 0;
/*  871 */     boolean endOfNested = false;
/*  874 */     i--;
/*  875 */     while (!endOfNested && ++i < len) {
/*      */       int posArgs;
/*  877 */       char c = p_sql.charAt(i);
/*  878 */       switch (state) {
/*      */         case 0:
/*  881 */           if (c == '\'') {
/*  882 */             state = 1;
/*  883 */           } else if (c == '"') {
/*  884 */             state = 6;
/*  885 */           } else if (c == '(') {
/*  886 */             nestedParenthesis++;
/*  887 */           } else if (c == ')') {
/*  888 */             nestedParenthesis--;
/*  889 */             if (nestedParenthesis < 0) {
/*  890 */               endOfNested = true;
/*      */               continue;
/*      */             } 
/*      */           } else {
/*  893 */             if (stopOnComma && c == ',' && nestedParenthesis == 0) {
/*  894 */               endOfNested = true;
/*      */               continue;
/*      */             } 
/*  896 */             if (c == '{' && 
/*  897 */               i + 1 < len) {
/*  899 */               char next = p_sql.charAt(i + 1);
/*  900 */               char nextnext = (i + 2 < len) ? p_sql.charAt(i + 2) : Character.MIN_VALUE;
/*  901 */               if (next == 'd' || next == 'D') {
/*  903 */                 state = 3;
/*  904 */                 i++;
/*  905 */                 newsql.append("DATE ");
/*      */                 continue;
/*      */               } 
/*  908 */               if (next == 't' || next == 'T') {
/*  910 */                 state = 3;
/*  911 */                 if (nextnext == 's' || nextnext == 'S') {
/*  913 */                   i += 2;
/*  914 */                   newsql.append("TIMESTAMP ");
/*      */                   continue;
/*      */                 } 
/*  917 */                 i++;
/*  918 */                 newsql.append("TIME ");
/*      */                 continue;
/*      */               } 
/*  922 */               if (next == 'f' || next == 'F') {
/*  924 */                 state = 4;
/*  925 */                 i += (nextnext == 'n' || nextnext == 'N') ? 2 : 1;
/*      */                 continue;
/*      */               } 
/*  928 */               if (next == 'o' || next == 'O') {
/*  930 */                 state = 5;
/*  931 */                 i += (nextnext == 'j' || nextnext == 'J') ? 2 : 1;
/*      */                 continue;
/*      */               } 
/*  934 */               if (next == 'e' || next == 'E') {
/*  936 */                 state = 7;
/*      */                 continue;
/*      */               } 
/*      */             } 
/*      */           } 
/*  941 */           newsql.append(c);
/*      */         case 1:
/*  945 */           if (c == '\'') {
/*  946 */             state = 0;
/*  947 */           } else if (c == '\\' && !stdStrings) {
/*  948 */             state = 2;
/*      */           } 
/*  950 */           newsql.append(c);
/*      */         case 6:
/*  954 */           if (c == '"')
/*  955 */             state = 0; 
/*  956 */           newsql.append(c);
/*      */         case 2:
/*  960 */           state = 1;
/*  962 */           newsql.append(c);
/*      */         case 4:
/*  968 */           posArgs = p_sql.indexOf('(', i);
/*  969 */           if (posArgs != -1) {
/*  970 */             String functionName = p_sql.substring(i, posArgs).trim();
/*  972 */             i = posArgs + 1;
/*  973 */             StringBuffer args = new StringBuffer();
/*  974 */             i = parseSql(p_sql, i, args, false, stdStrings);
/*  976 */             newsql.append(escapeFunction(functionName, args.toString(), stdStrings));
/*      */           } 
/*  979 */           i++;
/*  980 */           while (i < len && p_sql.charAt(i) != '}')
/*  981 */             newsql.append(p_sql.charAt(i++)); 
/*  982 */           state = 0;
/*      */         case 3:
/*      */         case 5:
/*      */         case 7:
/*  987 */           if (c == '}') {
/*  988 */             state = 0;
/*      */             continue;
/*      */           } 
/*  990 */           newsql.append(c);
/*      */       } 
/*      */     } 
/*  994 */     return i;
/*      */   }
/*      */   
/*      */   protected static String escapeFunction(String functionName, String args, boolean stdStrings) throws SQLException {
/* 1006 */     int len = args.length();
/* 1007 */     int i = 0;
/* 1008 */     ArrayList<StringBuffer> parsedArgs = new ArrayList();
/* 1009 */     while (i < len) {
/* 1010 */       StringBuffer arg = new StringBuffer();
/* 1011 */       int lastPos = i;
/* 1012 */       i = parseSql(args, i, arg, true, stdStrings);
/* 1013 */       if (lastPos != i)
/* 1014 */         parsedArgs.add(arg); 
/* 1016 */       i++;
/*      */     } 
/*      */     try {
/* 1020 */       Method escapeMethod = EscapedFunctions.getFunction(functionName);
/* 1021 */       return (String)escapeMethod.invoke(null, new Object[] { parsedArgs });
/* 1022 */     } catch (InvocationTargetException e) {
/* 1023 */       if (e.getTargetException() instanceof SQLException)
/* 1024 */         throw (SQLException)e.getTargetException(); 
/* 1026 */       throw new PSQLException(e.getTargetException().getMessage(), PSQLState.SYSTEM_ERROR);
/* 1028 */     } catch (Exception e) {
/* 1030 */       StringBuffer buf = new StringBuffer();
/* 1031 */       buf.append(functionName).append('(');
/* 1032 */       for (int iArg = 0; iArg < parsedArgs.size(); iArg++) {
/* 1033 */         buf.append(parsedArgs.get(iArg));
/* 1034 */         if (iArg != parsedArgs.size() - 1)
/* 1035 */           buf.append(','); 
/*      */       } 
/* 1037 */       buf.append(')');
/* 1038 */       return buf.toString();
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getInsertedOID() throws SQLException {
/* 1056 */     checkClosed();
/* 1057 */     if (this.result == null)
/* 1058 */       return 0; 
/* 1059 */     return (int)this.result.getInsertOID();
/*      */   }
/*      */   
/*      */   public long getLastOID() throws SQLException {
/* 1069 */     checkClosed();
/* 1070 */     if (this.result == null)
/* 1071 */       return 0L; 
/* 1072 */     return this.result.getInsertOID();
/*      */   }
/*      */   
/*      */   public void setNull(int parameterIndex, int sqlType) throws SQLException {
/*      */     int oid;
/* 1086 */     checkClosed();
/* 1089 */     switch (sqlType) {
/*      */       case 4:
/* 1092 */         oid = 23;
/*      */         break;
/*      */       case -6:
/*      */       case 5:
/* 1096 */         oid = 21;
/*      */         break;
/*      */       case -5:
/* 1099 */         oid = 20;
/*      */         break;
/*      */       case 7:
/* 1102 */         oid = 700;
/*      */         break;
/*      */       case 6:
/*      */       case 8:
/* 1106 */         oid = 701;
/*      */         break;
/*      */       case 2:
/*      */       case 3:
/* 1110 */         oid = 1700;
/*      */         break;
/*      */       case 1:
/* 1113 */         oid = 1042;
/*      */         break;
/*      */       case -1:
/*      */       case 12:
/* 1117 */         oid = 1043;
/*      */         break;
/*      */       case 91:
/* 1120 */         oid = 1082;
/*      */         break;
/*      */       case 92:
/*      */       case 93:
/* 1124 */         oid = 0;
/*      */         break;
/*      */       case -7:
/* 1127 */         oid = 16;
/*      */         break;
/*      */       case -4:
/*      */       case -3:
/*      */       case -2:
/* 1132 */         if (this.connection.haveMinimumCompatibleVersion("7.2")) {
/* 1134 */           oid = 17;
/*      */           break;
/*      */         } 
/* 1138 */         oid = 26;
/*      */         break;
/*      */       case 2004:
/*      */       case 2005:
/* 1143 */         oid = 26;
/*      */         break;
/*      */       case 0:
/*      */       case 1111:
/*      */       case 2001:
/*      */       case 2002:
/*      */       case 2003:
/* 1150 */         oid = 0;
/*      */         break;
/*      */       default:
/* 1154 */         throw new PSQLException(GT.tr("Unknown Types value."), PSQLState.INVALID_PARAMETER_TYPE);
/*      */     } 
/* 1156 */     if (this.adjustIndex)
/* 1157 */       parameterIndex--; 
/* 1158 */     this.preparedParameters.setNull(parameterIndex, oid);
/*      */   }
/*      */   
/*      */   public void setBoolean(int parameterIndex, boolean x) throws SQLException {
/* 1171 */     checkClosed();
/* 1172 */     bindString(parameterIndex, x ? "1" : "0", 16);
/*      */   }
/*      */   
/*      */   public void setByte(int parameterIndex, byte x) throws SQLException {
/* 1185 */     checkClosed();
/* 1186 */     bindLiteral(parameterIndex, Integer.toString(x), 21);
/*      */   }
/*      */   
/*      */   public void setShort(int parameterIndex, short x) throws SQLException {
/* 1199 */     checkClosed();
/* 1200 */     bindLiteral(parameterIndex, Integer.toString(x), 21);
/*      */   }
/*      */   
/*      */   public void setInt(int parameterIndex, int x) throws SQLException {
/* 1213 */     checkClosed();
/* 1214 */     bindLiteral(parameterIndex, Integer.toString(x), 23);
/*      */   }
/*      */   
/*      */   public void setLong(int parameterIndex, long x) throws SQLException {
/* 1227 */     checkClosed();
/* 1228 */     bindLiteral(parameterIndex, Long.toString(x), 20);
/*      */   }
/*      */   
/*      */   public void setFloat(int parameterIndex, float x) throws SQLException {
/* 1241 */     checkClosed();
/* 1242 */     bindLiteral(parameterIndex, Float.toString(x), 701);
/*      */   }
/*      */   
/*      */   public void setDouble(int parameterIndex, double x) throws SQLException {
/* 1255 */     checkClosed();
/* 1256 */     bindLiteral(parameterIndex, Double.toString(x), 701);
/*      */   }
/*      */   
/*      */   public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
/* 1270 */     checkClosed();
/* 1271 */     if (x == null) {
/* 1272 */       setNull(parameterIndex, 3);
/*      */     } else {
/* 1274 */       bindLiteral(parameterIndex, x.toString(), 1700);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setString(int parameterIndex, String x) throws SQLException {
/* 1289 */     checkClosed();
/* 1290 */     setString(parameterIndex, x, this.connection.getStringVarcharFlag() ? 1043 : 0);
/*      */   }
/*      */   
/*      */   protected void setString(int parameterIndex, String x, int oid) throws SQLException {
/* 1296 */     checkClosed();
/* 1297 */     if (x == null) {
/* 1299 */       if (this.adjustIndex)
/* 1300 */         parameterIndex--; 
/* 1301 */       this.preparedParameters.setNull(parameterIndex, oid);
/*      */     } else {
/* 1304 */       bindString(parameterIndex, x, oid);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setBytes(int parameterIndex, byte[] x) throws SQLException {
/* 1323 */     checkClosed();
/* 1325 */     if (null == x) {
/* 1327 */       setNull(parameterIndex, -3);
/*      */       return;
/*      */     } 
/* 1331 */     if (this.connection.haveMinimumCompatibleVersion("7.2")) {
/* 1334 */       byte[] copy = new byte[x.length];
/* 1335 */       System.arraycopy(x, 0, copy, 0, x.length);
/* 1336 */       this.preparedParameters.setBytea(parameterIndex, copy, 0, x.length);
/*      */     } else {
/* 1341 */       LargeObjectManager lom = this.connection.getLargeObjectAPI();
/* 1342 */       long oid = lom.createLO();
/* 1343 */       LargeObject lob = lom.open(oid);
/* 1344 */       lob.write(x);
/* 1345 */       lob.close();
/* 1346 */       setLong(parameterIndex, oid);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setDate(int parameterIndex, Date x) throws SQLException {
/* 1360 */     setDate(parameterIndex, x, null);
/*      */   }
/*      */   
/*      */   public void setTime(int parameterIndex, Time x) throws SQLException {
/* 1373 */     setTime(parameterIndex, x, null);
/*      */   }
/*      */   
/*      */   public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
/* 1386 */     setTimestamp(parameterIndex, x, null);
/*      */   }
/*      */   
/*      */   private void setCharacterStreamPost71(int parameterIndex, InputStream x, int length, String encoding) throws SQLException {
/* 1392 */     if (x == null) {
/* 1394 */       setNull(parameterIndex, 12);
/*      */       return;
/*      */     } 
/* 1397 */     if (length < 0)
/* 1398 */       throw new PSQLException(GT.tr("Invalid stream length {0}.", new Integer(length)), PSQLState.INVALID_PARAMETER_VALUE); 
/*      */     try {
/* 1410 */       InputStreamReader l_inStream = new InputStreamReader(x, encoding);
/* 1411 */       char[] l_chars = new char[length];
/* 1412 */       int l_charsRead = 0;
/*      */       do {
/* 1415 */         int n = l_inStream.read(l_chars, l_charsRead, length - l_charsRead);
/* 1416 */         if (n == -1)
/*      */           break; 
/* 1419 */         l_charsRead += n;
/* 1421 */       } while (l_charsRead != length);
/* 1425 */       setString(parameterIndex, new String(l_chars, 0, l_charsRead), 1043);
/* 1427 */     } catch (UnsupportedEncodingException l_uee) {
/* 1429 */       throw new PSQLException(GT.tr("The JVM claims not to support the {0} encoding.", encoding), PSQLState.UNEXPECTED_ERROR, l_uee);
/* 1431 */     } catch (IOException l_ioe) {
/* 1433 */       throw new PSQLException(GT.tr("Provided InputStream failed."), PSQLState.UNEXPECTED_ERROR, l_ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
/* 1455 */     checkClosed();
/* 1456 */     if (this.connection.haveMinimumCompatibleVersion("7.2")) {
/* 1458 */       setCharacterStreamPost71(parameterIndex, x, length, "ASCII");
/*      */     } else {
/* 1464 */       setBinaryStream(parameterIndex, x, length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
/* 1485 */     checkClosed();
/* 1486 */     if (this.connection.haveMinimumCompatibleVersion("7.2")) {
/* 1488 */       setCharacterStreamPost71(parameterIndex, x, length, "UTF-8");
/*      */     } else {
/* 1494 */       setBinaryStream(parameterIndex, x, length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
/* 1514 */     checkClosed();
/* 1516 */     if (x == null) {
/* 1518 */       setNull(parameterIndex, -3);
/*      */       return;
/*      */     } 
/* 1522 */     if (length < 0)
/* 1523 */       throw new PSQLException(GT.tr("Invalid stream length {0}.", new Integer(length)), PSQLState.INVALID_PARAMETER_VALUE); 
/* 1526 */     if (this.connection.haveMinimumCompatibleVersion("7.2")) {
/* 1534 */       this.preparedParameters.setBytea(parameterIndex, x, length);
/*      */     } else {
/* 1541 */       LargeObjectManager lom = this.connection.getLargeObjectAPI();
/* 1542 */       long oid = lom.createLO();
/* 1543 */       LargeObject lob = lom.open(oid);
/* 1544 */       OutputStream los = lob.getOutputStream();
/*      */       try {
/* 1550 */         int c = x.read();
/* 1551 */         int p = 0;
/* 1552 */         while (c > -1 && p < length) {
/* 1554 */           los.write(c);
/* 1555 */           c = x.read();
/* 1556 */           p++;
/*      */         } 
/* 1558 */         los.close();
/* 1560 */       } catch (IOException se) {
/* 1562 */         throw new PSQLException(GT.tr("Provided InputStream failed."), PSQLState.UNEXPECTED_ERROR, se);
/*      */       } 
/* 1565 */       setLong(parameterIndex, oid);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clearParameters() throws SQLException {
/* 1581 */     this.preparedParameters.clear();
/*      */   }
/*      */   
/*      */   private PGType createInternalType(Object x, int targetType) throws PSQLException {
/* 1586 */     if (x instanceof Byte)
/* 1586 */       return PGByte.castToServerType((Byte)x, targetType); 
/* 1587 */     if (x instanceof Short)
/* 1587 */       return PGShort.castToServerType((Short)x, targetType); 
/* 1588 */     if (x instanceof Integer)
/* 1588 */       return PGInteger.castToServerType((Integer)x, targetType); 
/* 1589 */     if (x instanceof Long)
/* 1589 */       return PGLong.castToServerType((Long)x, targetType); 
/* 1590 */     if (x instanceof Double)
/* 1590 */       return PGDouble.castToServerType((Double)x, targetType); 
/* 1591 */     if (x instanceof Float)
/* 1591 */       return PGFloat.castToServerType((Float)x, targetType); 
/* 1592 */     if (x instanceof BigDecimal)
/* 1592 */       return PGBigDecimal.castToServerType((BigDecimal)x, targetType); 
/* 1594 */     if (x instanceof Number)
/* 1594 */       return PGNumber.castToServerType((Number)x, targetType); 
/* 1595 */     if (x instanceof Boolean)
/* 1595 */       return PGBoolean.castToServerType((Boolean)x, targetType); 
/* 1596 */     return (PGType)new PGUnknown(x);
/*      */   }
/*      */   
/*      */   private void setPGobject(int parameterIndex, PGobject x) throws SQLException {
/* 1601 */     String typename = x.getType();
/* 1602 */     int oid = this.connection.getTypeInfo().getPGType(typename);
/* 1603 */     if (oid == 0)
/* 1604 */       throw new PSQLException(GT.tr("Unknown type {0}.", typename), PSQLState.INVALID_PARAMETER_TYPE); 
/* 1606 */     setString(parameterIndex, x.getValue(), oid);
/*      */   }
/*      */   
/*      */   public void setObject(int parameterIndex, Object in, int targetSqlType, int scale) throws SQLException {
/* 1630 */     checkClosed();
/* 1632 */     if (in == null) {
/* 1634 */       setNull(parameterIndex, targetSqlType);
/*      */       return;
/*      */     } 
/* 1638 */     Object pgType = createInternalType(in, targetSqlType);
/* 1639 */     switch (targetSqlType) {
/*      */       case 4:
/* 1642 */         bindLiteral(parameterIndex, pgType.toString(), 23);
/*      */         return;
/*      */       case -6:
/*      */       case 5:
/* 1646 */         bindLiteral(parameterIndex, pgType.toString(), 21);
/*      */         return;
/*      */       case -5:
/* 1649 */         bindLiteral(parameterIndex, pgType.toString(), 20);
/*      */         return;
/*      */       case 7:
/* 1654 */         bindLiteral(parameterIndex, pgType.toString(), 700);
/*      */         return;
/*      */       case 6:
/*      */       case 8:
/* 1658 */         bindLiteral(parameterIndex, pgType.toString(), 701);
/*      */         return;
/*      */       case 2:
/*      */       case 3:
/* 1662 */         bindLiteral(parameterIndex, pgType.toString(), 1700);
/*      */         return;
/*      */       case 1:
/* 1665 */         setString(parameterIndex, pgType.toString(), 1042);
/*      */         return;
/*      */       case -1:
/*      */       case 12:
/* 1669 */         setString(parameterIndex, pgType.toString(), 1043);
/*      */         return;
/*      */       case 91:
/* 1672 */         if (in instanceof Date) {
/* 1673 */           setDate(parameterIndex, (Date)in);
/*      */         } else {
/*      */           Date tmpd;
/* 1677 */           if (in instanceof Date) {
/* 1678 */             tmpd = new Date(((Date)in).getTime());
/*      */           } else {
/* 1680 */             tmpd = this.connection.getTimestampUtils().toDate(null, in.toString());
/*      */           } 
/* 1682 */           setDate(parameterIndex, tmpd);
/*      */         } 
/*      */         return;
/*      */       case 92:
/* 1686 */         if (in instanceof Time) {
/* 1687 */           setTime(parameterIndex, (Time)in);
/*      */         } else {
/*      */           Time tmpt;
/* 1691 */           if (in instanceof Date) {
/* 1692 */             tmpt = new Time(((Date)in).getTime());
/*      */           } else {
/* 1694 */             tmpt = this.connection.getTimestampUtils().toTime(null, in.toString());
/*      */           } 
/* 1696 */           setTime(parameterIndex, tmpt);
/*      */         } 
/*      */         return;
/*      */       case 93:
/* 1700 */         if (in instanceof Timestamp) {
/* 1701 */           setTimestamp(parameterIndex, (Timestamp)in);
/*      */         } else {
/*      */           Timestamp tmpts;
/* 1705 */           if (in instanceof Date) {
/* 1706 */             tmpts = new Timestamp(((Date)in).getTime());
/*      */           } else {
/* 1708 */             tmpts = this.connection.getTimestampUtils().toTimestamp(null, in.toString());
/*      */           } 
/* 1710 */           setTimestamp(parameterIndex, tmpts);
/*      */         } 
/*      */         return;
/*      */       case -7:
/* 1714 */         bindLiteral(parameterIndex, pgType.toString(), 16);
/*      */         return;
/*      */       case -4:
/*      */       case -3:
/*      */       case -2:
/* 1719 */         setObject(parameterIndex, in);
/*      */         return;
/*      */       case 2004:
/* 1722 */         if (in instanceof Blob) {
/* 1723 */           setBlob(parameterIndex, (Blob)in);
/*      */         } else {
/* 1725 */           throw new PSQLException(GT.tr("Cannot cast an instance of {0} to type {1}", new Object[] { in.getClass().getName(), "Types.BLOB" }), PSQLState.INVALID_PARAMETER_TYPE);
/*      */         } 
/*      */         return;
/*      */       case 2005:
/* 1728 */         if (in instanceof Clob) {
/* 1729 */           setClob(parameterIndex, (Clob)in);
/*      */         } else {
/* 1731 */           throw new PSQLException(GT.tr("Cannot cast an instance of {0} to type {1}", new Object[] { in.getClass().getName(), "Types.CLOB" }), PSQLState.INVALID_PARAMETER_TYPE);
/*      */         } 
/*      */         return;
/*      */       case 2003:
/* 1734 */         if (in instanceof Array) {
/* 1735 */           setArray(parameterIndex, (Array)in);
/*      */         } else {
/* 1737 */           throw new PSQLException(GT.tr("Cannot cast an instance of {0} to type {1}", new Object[] { in.getClass().getName(), "Types.ARRAY" }), PSQLState.INVALID_PARAMETER_TYPE);
/*      */         } 
/*      */         return;
/*      */       case 2001:
/* 1740 */         bindString(parameterIndex, in.toString(), 0);
/*      */         return;
/*      */       case 1111:
/* 1743 */         if (in instanceof PGobject) {
/* 1744 */           setPGobject(parameterIndex, (PGobject)in);
/*      */         } else {
/* 1746 */           bindString(parameterIndex, in.toString(), 0);
/*      */         } 
/*      */         return;
/*      */     } 
/* 1749 */     throw new PSQLException(GT.tr("Unsupported Types value: {0}", new Integer(targetSqlType)), PSQLState.INVALID_PARAMETER_TYPE);
/*      */   }
/*      */   
/*      */   public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
/* 1755 */     setObject(parameterIndex, x, targetSqlType, 0);
/*      */   }
/*      */   
/*      */   public void setObject(int parameterIndex, Object x) throws SQLException {
/* 1763 */     checkClosed();
/* 1764 */     if (x == null) {
/* 1765 */       setNull(parameterIndex, 1111);
/* 1766 */     } else if (x instanceof String) {
/* 1767 */       setString(parameterIndex, (String)x);
/* 1768 */     } else if (x instanceof BigDecimal) {
/* 1769 */       setBigDecimal(parameterIndex, (BigDecimal)x);
/* 1770 */     } else if (x instanceof Short) {
/* 1771 */       setShort(parameterIndex, ((Short)x).shortValue());
/* 1772 */     } else if (x instanceof Integer) {
/* 1773 */       setInt(parameterIndex, ((Integer)x).intValue());
/* 1774 */     } else if (x instanceof Long) {
/* 1775 */       setLong(parameterIndex, ((Long)x).longValue());
/* 1776 */     } else if (x instanceof Float) {
/* 1777 */       setFloat(parameterIndex, ((Float)x).floatValue());
/* 1778 */     } else if (x instanceof Double) {
/* 1779 */       setDouble(parameterIndex, ((Double)x).doubleValue());
/* 1780 */     } else if (x instanceof byte[]) {
/* 1781 */       setBytes(parameterIndex, (byte[])x);
/* 1782 */     } else if (x instanceof Date) {
/* 1783 */       setDate(parameterIndex, (Date)x);
/* 1784 */     } else if (x instanceof Time) {
/* 1785 */       setTime(parameterIndex, (Time)x);
/* 1786 */     } else if (x instanceof Timestamp) {
/* 1787 */       setTimestamp(parameterIndex, (Timestamp)x);
/* 1788 */     } else if (x instanceof Boolean) {
/* 1789 */       setBoolean(parameterIndex, ((Boolean)x).booleanValue());
/* 1790 */     } else if (x instanceof Byte) {
/* 1791 */       setByte(parameterIndex, ((Byte)x).byteValue());
/* 1792 */     } else if (x instanceof Blob) {
/* 1793 */       setBlob(parameterIndex, (Blob)x);
/* 1794 */     } else if (x instanceof Clob) {
/* 1795 */       setClob(parameterIndex, (Clob)x);
/* 1796 */     } else if (x instanceof Array) {
/* 1797 */       setArray(parameterIndex, (Array)x);
/* 1798 */     } else if (x instanceof PGobject) {
/* 1799 */       setPGobject(parameterIndex, (PGobject)x);
/* 1800 */     } else if (x instanceof Character) {
/* 1801 */       setString(parameterIndex, ((Character)x).toString());
/*      */     } else {
/* 1805 */       throw new PSQLException(GT.tr("Can''t infer the SQL type to use for an instance of {0}. Use setObject() with an explicit Types value to specify the type to use.", x.getClass().getName()), PSQLState.INVALID_PARAMETER_TYPE);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void registerOutParameter(int parameterIndex, int sqlType, boolean setPreparedParameters) throws SQLException {
/* 1828 */     checkClosed();
/* 1829 */     switch (sqlType) {
/*      */       case -6:
/* 1833 */         sqlType = 5;
/*      */         break;
/*      */       case -1:
/* 1836 */         sqlType = 12;
/*      */         break;
/*      */       case 3:
/* 1839 */         sqlType = 2;
/*      */         break;
/*      */       case 6:
/* 1843 */         sqlType = 8;
/*      */         break;
/*      */       case -4:
/*      */       case -3:
/* 1847 */         sqlType = -2;
/*      */         break;
/*      */     } 
/* 1852 */     if (!this.isFunction)
/* 1853 */       throw new PSQLException(GT.tr("This statement does not declare an OUT parameter.  Use '{' ?= call ... '}' to declare one."), PSQLState.STATEMENT_NOT_ALLOWED_IN_FUNCTION_CALL); 
/* 1854 */     checkIndex(parameterIndex, false);
/* 1856 */     if (setPreparedParameters)
/* 1857 */       this.preparedParameters.registerOutParameter(parameterIndex, sqlType); 
/* 1861 */     this.functionReturnType[parameterIndex - 1] = sqlType;
/* 1862 */     this.testReturn[parameterIndex - 1] = sqlType;
/* 1864 */     if (this.functionReturnType[parameterIndex - 1] == 1 || this.functionReturnType[parameterIndex - 1] == -1) {
/* 1866 */       this.testReturn[parameterIndex - 1] = 12;
/* 1867 */     } else if (this.functionReturnType[parameterIndex - 1] == 6) {
/* 1868 */       this.testReturn[parameterIndex - 1] = 7;
/*      */     } 
/* 1869 */     this.returnTypeSet = true;
/*      */   }
/*      */   
/*      */   public void registerOutParameter(int parameterIndex, int sqlType, int scale, boolean setPreparedParameters) throws SQLException {
/* 1888 */     registerOutParameter(parameterIndex, sqlType, setPreparedParameters);
/*      */   }
/*      */   
/*      */   public boolean wasNull() throws SQLException {
/* 1902 */     if (this.lastIndex == 0)
/* 1903 */       throw new PSQLException(GT.tr("wasNull cannot be call before fetching a result."), PSQLState.OBJECT_NOT_IN_STATE); 
/* 1906 */     return (this.callResult[this.lastIndex - 1] == null);
/*      */   }
/*      */   
/*      */   public String getString(int parameterIndex) throws SQLException {
/* 1919 */     checkClosed();
/* 1920 */     checkIndex(parameterIndex, 12, "String");
/* 1921 */     return (String)this.callResult[parameterIndex - 1];
/*      */   }
/*      */   
/*      */   public boolean getBoolean(int parameterIndex) throws SQLException {
/* 1934 */     checkClosed();
/* 1935 */     checkIndex(parameterIndex, -7, "Boolean");
/* 1936 */     if (this.callResult[parameterIndex - 1] == null)
/* 1937 */       return false; 
/* 1939 */     return ((Boolean)this.callResult[parameterIndex - 1]).booleanValue();
/*      */   }
/*      */   
/*      */   public byte getByte(int parameterIndex) throws SQLException {
/* 1951 */     checkClosed();
/* 1953 */     checkIndex(parameterIndex, 5, "Byte");
/* 1955 */     if (this.callResult[parameterIndex - 1] == null)
/* 1956 */       return 0; 
/* 1958 */     return ((Integer)this.callResult[parameterIndex - 1]).byteValue();
/*      */   }
/*      */   
/*      */   public short getShort(int parameterIndex) throws SQLException {
/* 1971 */     checkClosed();
/* 1972 */     checkIndex(parameterIndex, 5, "Short");
/* 1973 */     if (this.callResult[parameterIndex - 1] == null)
/* 1974 */       return 0; 
/* 1975 */     return ((Integer)this.callResult[parameterIndex - 1]).shortValue();
/*      */   }
/*      */   
/*      */   public int getInt(int parameterIndex) throws SQLException {
/* 1988 */     checkClosed();
/* 1989 */     checkIndex(parameterIndex, 4, "Int");
/* 1990 */     if (this.callResult[parameterIndex - 1] == null)
/* 1991 */       return 0; 
/* 1993 */     return ((Integer)this.callResult[parameterIndex - 1]).intValue();
/*      */   }
/*      */   
/*      */   public long getLong(int parameterIndex) throws SQLException {
/* 2005 */     checkClosed();
/* 2006 */     checkIndex(parameterIndex, -5, "Long");
/* 2007 */     if (this.callResult[parameterIndex - 1] == null)
/* 2008 */       return 0L; 
/* 2010 */     return ((Long)this.callResult[parameterIndex - 1]).longValue();
/*      */   }
/*      */   
/*      */   public float getFloat(int parameterIndex) throws SQLException {
/* 2022 */     checkClosed();
/* 2023 */     checkIndex(parameterIndex, 7, "Float");
/* 2024 */     if (this.callResult[parameterIndex - 1] == null)
/* 2025 */       return 0.0F; 
/* 2027 */     return ((Float)this.callResult[parameterIndex - 1]).floatValue();
/*      */   }
/*      */   
/*      */   public double getDouble(int parameterIndex) throws SQLException {
/* 2039 */     checkClosed();
/* 2040 */     checkIndex(parameterIndex, 8, "Double");
/* 2041 */     if (this.callResult[parameterIndex - 1] == null)
/* 2042 */       return 0.0D; 
/* 2044 */     return ((Double)this.callResult[parameterIndex - 1]).doubleValue();
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
/* 2061 */     checkClosed();
/* 2062 */     checkIndex(parameterIndex, 2, "BigDecimal");
/* 2063 */     return (BigDecimal)this.callResult[parameterIndex - 1];
/*      */   }
/*      */   
/*      */   public byte[] getBytes(int parameterIndex) throws SQLException {
/* 2076 */     checkClosed();
/* 2077 */     checkIndex(parameterIndex, -3, -2, "Bytes");
/* 2078 */     return (byte[])this.callResult[parameterIndex - 1];
/*      */   }
/*      */   
/*      */   public Date getDate(int parameterIndex) throws SQLException {
/* 2091 */     checkClosed();
/* 2092 */     checkIndex(parameterIndex, 91, "Date");
/* 2093 */     return (Date)this.callResult[parameterIndex - 1];
/*      */   }
/*      */   
/*      */   public Time getTime(int parameterIndex) throws SQLException {
/* 2105 */     checkClosed();
/* 2106 */     checkIndex(parameterIndex, 92, "Time");
/* 2107 */     return (Time)this.callResult[parameterIndex - 1];
/*      */   }
/*      */   
/*      */   public Timestamp getTimestamp(int parameterIndex) throws SQLException {
/* 2120 */     checkClosed();
/* 2121 */     checkIndex(parameterIndex, 93, "Timestamp");
/* 2122 */     return (Timestamp)this.callResult[parameterIndex - 1];
/*      */   }
/*      */   
/*      */   public Object getObject(int parameterIndex) throws SQLException {
/* 2148 */     checkClosed();
/* 2149 */     checkIndex(parameterIndex);
/* 2150 */     return this.callResult[parameterIndex - 1];
/*      */   }
/*      */   
/*      */   public String toString() {
/* 2159 */     if (this.preparedQuery == null)
/* 2160 */       return super.toString(); 
/* 2162 */     return this.preparedQuery.toString(this.preparedParameters);
/*      */   }
/*      */   
/*      */   protected void bindLiteral(int paramIndex, String s, int oid) throws SQLException {
/* 2174 */     if (this.adjustIndex)
/* 2175 */       paramIndex--; 
/* 2176 */     this.preparedParameters.setLiteralParameter(paramIndex, s, oid);
/*      */   }
/*      */   
/*      */   private void bindString(int paramIndex, String s, int oid) throws SQLException {
/* 2186 */     if (this.adjustIndex)
/* 2187 */       paramIndex--; 
/* 2188 */     this.preparedParameters.setStringParameter(paramIndex, s, oid);
/*      */   }
/*      */   
/*      */   private String modifyJdbcCall(String p_sql) throws SQLException {
/* 2200 */     checkClosed();
/* 2206 */     this.isFunction = false;
/* 2208 */     boolean stdStrings = this.connection.getStandardConformingStrings();
/* 2210 */     int len = p_sql.length();
/* 2211 */     int state = 1;
/* 2212 */     boolean inQuotes = false, inEscape = false;
/* 2213 */     this.outParmBeforeFunc = false;
/* 2214 */     int startIndex = -1, endIndex = -1;
/* 2215 */     boolean syntaxError = false;
/* 2216 */     int i = 0;
/* 2218 */     while (i < len && !syntaxError) {
/* 2220 */       char ch = p_sql.charAt(i);
/* 2222 */       switch (state) {
/*      */         case 1:
/* 2225 */           if (ch == '{') {
/* 2227 */             i++;
/* 2228 */             state++;
/*      */             continue;
/*      */           } 
/* 2230 */           if (Character.isWhitespace(ch)) {
/* 2232 */             i++;
/*      */             continue;
/*      */           } 
/* 2237 */           i = len;
/*      */           continue;
/*      */         case 2:
/* 2242 */           if (ch == '?') {
/* 2244 */             this.outParmBeforeFunc = this.isFunction = true;
/* 2245 */             i++;
/* 2246 */             state++;
/*      */             continue;
/*      */           } 
/* 2248 */           if (ch == 'c') {
/* 2250 */             state += 3;
/*      */             continue;
/*      */           } 
/* 2252 */           if (Character.isWhitespace(ch)) {
/* 2254 */             i++;
/*      */             continue;
/*      */           } 
/* 2259 */           syntaxError = true;
/*      */           continue;
/*      */         case 3:
/* 2264 */           if (ch == '=') {
/* 2266 */             i++;
/* 2267 */             state++;
/*      */             continue;
/*      */           } 
/* 2269 */           if (Character.isWhitespace(ch)) {
/* 2271 */             i++;
/*      */             continue;
/*      */           } 
/* 2275 */           syntaxError = true;
/*      */           continue;
/*      */         case 4:
/* 2280 */           if (ch == 'c' || ch == 'C') {
/* 2282 */             state++;
/*      */             continue;
/*      */           } 
/* 2284 */           if (Character.isWhitespace(ch)) {
/* 2286 */             i++;
/*      */             continue;
/*      */           } 
/* 2290 */           syntaxError = true;
/*      */           continue;
/*      */         case 5:
/* 2295 */           if ((ch == 'c' || ch == 'C') && i + 4 <= len && p_sql.substring(i, i + 4).equalsIgnoreCase("call")) {
/* 2297 */             this.isFunction = true;
/* 2298 */             i += 4;
/* 2299 */             state++;
/*      */             continue;
/*      */           } 
/* 2301 */           if (Character.isWhitespace(ch)) {
/* 2303 */             i++;
/*      */             continue;
/*      */           } 
/* 2307 */           syntaxError = true;
/*      */           continue;
/*      */         case 6:
/* 2312 */           if (Character.isWhitespace(ch)) {
/* 2315 */             i++;
/* 2316 */             state++;
/* 2317 */             startIndex = i;
/*      */             continue;
/*      */           } 
/* 2321 */           syntaxError = true;
/*      */           continue;
/*      */         case 7:
/* 2326 */           if (ch == '\'') {
/* 2328 */             inQuotes = !inQuotes;
/* 2329 */             i++;
/*      */             continue;
/*      */           } 
/* 2331 */           if (inQuotes && ch == '\\' && !stdStrings) {
/* 2334 */             i += 2;
/*      */             continue;
/*      */           } 
/* 2336 */           if (!inQuotes && ch == '{') {
/* 2338 */             inEscape = !inEscape;
/* 2339 */             i++;
/*      */             continue;
/*      */           } 
/* 2341 */           if (!inQuotes && ch == '}') {
/* 2343 */             if (!inEscape) {
/* 2346 */               endIndex = i;
/* 2347 */               i++;
/* 2348 */               state++;
/*      */               continue;
/*      */             } 
/* 2352 */             inEscape = false;
/*      */             continue;
/*      */           } 
/* 2355 */           if (!inQuotes && ch == ';') {
/* 2357 */             syntaxError = true;
/*      */             continue;
/*      */           } 
/* 2362 */           i++;
/*      */           continue;
/*      */         case 8:
/* 2367 */           if (Character.isWhitespace(ch)) {
/* 2369 */             i++;
/*      */             continue;
/*      */           } 
/* 2373 */           syntaxError = true;
/*      */           continue;
/*      */       } 
/* 2378 */       throw new IllegalStateException("somehow got into bad state " + state);
/*      */     } 
/* 2383 */     if (i == len && !syntaxError) {
/* 2385 */       if (state == 1)
/* 2386 */         return p_sql; 
/* 2387 */       if (state != 8)
/* 2388 */         syntaxError = true; 
/*      */     } 
/* 2391 */     if (syntaxError)
/* 2392 */       throw new PSQLException(GT.tr("Malformed function or procedure escape syntax at offset {0}.", new Integer(i)), PSQLState.STATEMENT_NOT_ALLOWED_IN_FUNCTION_CALL); 
/* 2395 */     if (this.connection.haveMinimumServerVersion("8.1") && ((AbstractJdbc2Connection)this.connection).getProtocolVersion() == 3) {
/* 2397 */       String s = p_sql.substring(startIndex, endIndex);
/* 2398 */       StringBuffer sb = new StringBuffer(s);
/* 2399 */       if (this.outParmBeforeFunc) {
/* 2403 */         boolean needComma = false;
/* 2406 */         int opening = s.indexOf('(') + 1;
/* 2407 */         int closing = s.indexOf(')');
/* 2408 */         for (int j = opening; j < closing; j++) {
/* 2410 */           if (!Character.isWhitespace(sb.charAt(j))) {
/* 2412 */             needComma = true;
/*      */             break;
/*      */           } 
/*      */         } 
/* 2416 */         if (needComma) {
/* 2418 */           sb.insert(opening, "?,");
/*      */         } else {
/* 2422 */           sb.insert(opening, "?");
/*      */         } 
/*      */       } 
/* 2426 */       return "select * from " + sb.toString() + " as result";
/*      */     } 
/* 2430 */     return "select " + p_sql.substring(startIndex, endIndex) + " as result";
/*      */   }
/*      */   
/*      */   protected void checkIndex(int parameterIndex, int type1, int type2, String getName) throws SQLException {
/* 2440 */     checkIndex(parameterIndex);
/* 2441 */     if (type1 != this.testReturn[parameterIndex - 1] && type2 != this.testReturn[parameterIndex - 1])
/* 2442 */       throw new PSQLException(GT.tr("Parameter of type {0} was registered, but call to get{1} (sqltype={2}) was made.", new Object[] { "java.sql.Types=" + this.testReturn[parameterIndex - 1], getName, "java.sql.Types=" + type1 }), PSQLState.MOST_SPECIFIC_TYPE_DOES_NOT_MATCH); 
/*      */   }
/*      */   
/*      */   protected void checkIndex(int parameterIndex, int type, String getName) throws SQLException {
/* 2454 */     checkIndex(parameterIndex);
/* 2455 */     if (type != this.testReturn[parameterIndex - 1])
/* 2456 */       throw new PSQLException(GT.tr("Parameter of type {0} was registered, but call to get{1} (sqltype={2}) was made.", new Object[] { "java.sql.Types=" + this.testReturn[parameterIndex - 1], getName, "java.sql.Types=" + type }), PSQLState.MOST_SPECIFIC_TYPE_DOES_NOT_MATCH); 
/*      */   }
/*      */   
/*      */   private void checkIndex(int parameterIndex) throws SQLException {
/* 2465 */     checkIndex(parameterIndex, true);
/*      */   }
/*      */   
/*      */   private void checkIndex(int parameterIndex, boolean fetchingData) throws SQLException {
/* 2474 */     if (!this.isFunction)
/* 2475 */       throw new PSQLException(GT.tr("A CallableStatement was declared, but no call to registerOutParameter(1, <some type>) was made."), PSQLState.STATEMENT_NOT_ALLOWED_IN_FUNCTION_CALL); 
/* 2477 */     if (fetchingData) {
/* 2478 */       if (!this.returnTypeSet)
/* 2479 */         throw new PSQLException(GT.tr("No function outputs were registered."), PSQLState.OBJECT_NOT_IN_STATE); 
/* 2481 */       if (this.callResult == null)
/* 2482 */         throw new PSQLException(GT.tr("Results cannot be retrieved from a CallableStatement before it is executed."), PSQLState.NO_DATA); 
/* 2484 */       this.lastIndex = parameterIndex;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setPrepareThreshold(int newThreshold) throws SQLException {
/* 2489 */     checkClosed();
/* 2491 */     if (newThreshold < 0)
/* 2492 */       newThreshold = 0; 
/* 2494 */     this.m_prepareThreshold = newThreshold;
/*      */   }
/*      */   
/*      */   public int getPrepareThreshold() {
/* 2498 */     return this.m_prepareThreshold;
/*      */   }
/*      */   
/*      */   public void setUseServerPrepare(boolean flag) throws SQLException {
/* 2502 */     setPrepareThreshold(flag ? 1 : 0);
/*      */   }
/*      */   
/*      */   public boolean isUseServerPrepare() {
/* 2506 */     return (this.preparedQuery != null && this.m_prepareThreshold != 0 && this.m_useCount + 1 >= this.m_prepareThreshold);
/*      */   }
/*      */   
/*      */   protected void checkClosed() throws SQLException {
/* 2511 */     if (this.isClosed)
/* 2512 */       throw new PSQLException(GT.tr("This statement has been closed."), PSQLState.OBJECT_NOT_IN_STATE); 
/*      */   }
/*      */   
/*      */   public void addBatch(String p_sql) throws SQLException {
/* 2520 */     checkClosed();
/* 2522 */     if (this.preparedQuery != null)
/* 2523 */       throw new PSQLException(GT.tr("Can''t use query methods that take a query string on a PreparedStatement."), PSQLState.WRONG_OBJECT_TYPE); 
/* 2526 */     if (this.batchStatements == null) {
/* 2528 */       this.batchStatements = new ArrayList();
/* 2529 */       this.batchParameters = new ArrayList();
/*      */     } 
/* 2532 */     p_sql = replaceProcessing(p_sql);
/* 2534 */     this.batchStatements.add(this.connection.getQueryExecutor().createSimpleQuery(p_sql));
/* 2535 */     this.batchParameters.add(null);
/*      */   }
/*      */   
/*      */   public void clearBatch() throws SQLException {
/* 2540 */     if (this.batchStatements != null) {
/* 2542 */       this.batchStatements.clear();
/* 2543 */       this.batchParameters.clear();
/*      */     } 
/*      */   }
/*      */   
/*      */   private class BatchResultHandler implements ResultHandler {
/* 2552 */     private BatchUpdateException batchException = null;
/*      */     
/* 2553 */     private int resultIndex = 0;
/*      */     
/*      */     private final Query[] queries;
/*      */     
/*      */     private final ParameterList[] parameterLists;
/*      */     
/*      */     private final int[] updateCounts;
/*      */     
/*      */     private final boolean expectGeneratedKeys;
/*      */     
/*      */     private ResultSet generatedKeys;
/*      */     
/*      */     BatchResultHandler(Query[] queries, ParameterList[] parameterLists, int[] updateCounts, boolean expectGeneratedKeys) {
/* 2562 */       this.queries = queries;
/* 2563 */       this.parameterLists = parameterLists;
/* 2564 */       this.updateCounts = updateCounts;
/* 2565 */       this.expectGeneratedKeys = expectGeneratedKeys;
/*      */     }
/*      */     
/*      */     public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {
/* 2569 */       if (!this.expectGeneratedKeys) {
/* 2570 */         handleError((SQLException)new PSQLException(GT.tr("A result was returned when none was expected."), PSQLState.TOO_MANY_RESULTS));
/* 2573 */       } else if (this.generatedKeys == null) {
/*      */         try {
/* 2576 */           this.generatedKeys = AbstractJdbc2Statement.this.createResultSet(fromQuery, fields, tuples, cursor);
/* 2578 */         } catch (SQLException e) {
/* 2580 */           handleError(e);
/*      */         } 
/*      */       } else {
/* 2584 */         ((AbstractJdbc2ResultSet)this.generatedKeys).addRows(tuples);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void handleCommandStatus(String status, int updateCount, long insertOID) {
/* 2590 */       if (this.resultIndex >= this.updateCounts.length) {
/* 2592 */         handleError((SQLException)new PSQLException(GT.tr("Too many update results were returned."), PSQLState.TOO_MANY_RESULTS));
/*      */         return;
/*      */       } 
/* 2597 */       this.updateCounts[this.resultIndex++] = updateCount;
/*      */     }
/*      */     
/*      */     public void handleWarning(SQLWarning warning) {
/* 2601 */       AbstractJdbc2Statement.this.addWarning(warning);
/*      */     }
/*      */     
/*      */     public void handleError(SQLException newError) {
/* 2605 */       if (this.batchException == null) {
/*      */         int[] successCounts;
/* 2609 */         if (this.resultIndex >= this.updateCounts.length) {
/* 2610 */           successCounts = this.updateCounts;
/*      */         } else {
/* 2613 */           successCounts = new int[this.resultIndex];
/* 2614 */           System.arraycopy(this.updateCounts, 0, successCounts, 0, this.resultIndex);
/*      */         } 
/* 2617 */         String queryString = "<unknown>";
/* 2618 */         if (this.resultIndex < this.queries.length)
/* 2619 */           queryString = this.queries[this.resultIndex].toString(this.parameterLists[this.resultIndex]); 
/* 2621 */         this.batchException = new BatchUpdateException(GT.tr("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", new Object[] { new Integer(this.resultIndex), queryString }), newError.getSQLState(), successCounts);
/*      */       } 
/* 2628 */       this.batchException.setNextException(newError);
/*      */     }
/*      */     
/*      */     public void handleCompletion() throws SQLException {
/* 2632 */       if (this.batchException != null)
/* 2633 */         throw this.batchException; 
/*      */     }
/*      */     
/*      */     public ResultSet getGeneratedKeys() {
/* 2637 */       return this.generatedKeys;
/*      */     }
/*      */   }
/*      */   
/*      */   private class CallableBatchResultHandler implements ResultHandler {
/* 2641 */     private BatchUpdateException batchException = null;
/*      */     
/* 2642 */     private int resultIndex = 0;
/*      */     
/*      */     private final Query[] queries;
/*      */     
/*      */     private final ParameterList[] parameterLists;
/*      */     
/*      */     private final int[] updateCounts;
/*      */     
/*      */     CallableBatchResultHandler(Query[] queries, ParameterList[] parameterLists, int[] updateCounts) {
/* 2649 */       this.queries = queries;
/* 2650 */       this.parameterLists = parameterLists;
/* 2651 */       this.updateCounts = updateCounts;
/*      */     }
/*      */     
/*      */     public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {}
/*      */     
/*      */     public void handleCommandStatus(String status, int updateCount, long insertOID) {
/* 2660 */       if (this.resultIndex >= this.updateCounts.length) {
/* 2662 */         handleError((SQLException)new PSQLException(GT.tr("Too many update results were returned."), PSQLState.TOO_MANY_RESULTS));
/*      */         return;
/*      */       } 
/* 2667 */       this.updateCounts[this.resultIndex++] = updateCount;
/*      */     }
/*      */     
/*      */     public void handleWarning(SQLWarning warning) {
/* 2671 */       AbstractJdbc2Statement.this.addWarning(warning);
/*      */     }
/*      */     
/*      */     public void handleError(SQLException newError) {
/* 2675 */       if (this.batchException == null) {
/*      */         int[] successCounts;
/* 2679 */         if (this.resultIndex >= this.updateCounts.length) {
/* 2680 */           successCounts = this.updateCounts;
/*      */         } else {
/* 2683 */           successCounts = new int[this.resultIndex];
/* 2684 */           System.arraycopy(this.updateCounts, 0, successCounts, 0, this.resultIndex);
/*      */         } 
/* 2687 */         String queryString = "<unknown>";
/* 2688 */         if (this.resultIndex < this.queries.length)
/* 2689 */           queryString = this.queries[this.resultIndex].toString(this.parameterLists[this.resultIndex]); 
/* 2691 */         this.batchException = new BatchUpdateException(GT.tr("Batch entry {0} {1} was aborted.  Call getNextException to see the cause.", new Object[] { new Integer(this.resultIndex), queryString }), newError.getSQLState(), successCounts);
/*      */       } 
/* 2698 */       this.batchException.setNextException(newError);
/*      */     }
/*      */     
/*      */     public void handleCompletion() throws SQLException {
/* 2702 */       if (this.batchException != null)
/* 2703 */         throw this.batchException; 
/*      */     }
/*      */   }
/*      */   
/*      */   public int[] executeBatch() throws SQLException {
/*      */     int flags;
/*      */     ResultHandler handler;
/* 2710 */     checkClosed();
/* 2712 */     closeForNextExecution();
/* 2714 */     if (this.batchStatements == null || this.batchStatements.isEmpty())
/* 2715 */       return new int[0]; 
/* 2717 */     int size = this.batchStatements.size();
/* 2718 */     int[] updateCounts = new int[size];
/* 2721 */     Query[] queries = (Query[])this.batchStatements.toArray((Object[])new Query[this.batchStatements.size()]);
/* 2722 */     ParameterList[] parameterLists = (ParameterList[])this.batchParameters.toArray((Object[])new ParameterList[this.batchParameters.size()]);
/* 2723 */     this.batchStatements.clear();
/* 2724 */     this.batchParameters.clear();
/* 2728 */     if (this.wantsGeneratedKeysAlways) {
/* 2729 */       flags = 192;
/*      */     } else {
/* 2731 */       flags = 4;
/*      */     } 
/* 2735 */     if (this.preparedQuery != null)
/* 2737 */       this.m_useCount += queries.length; 
/* 2739 */     if (this.m_prepareThreshold == 0 || this.m_useCount < this.m_prepareThreshold)
/* 2740 */       flags |= 0x1; 
/* 2742 */     if (this.connection.getAutoCommit())
/* 2743 */       flags |= 0x10; 
/* 2745 */     this.result = null;
/* 2748 */     if (this.isFunction) {
/* 2749 */       handler = new CallableBatchResultHandler(queries, parameterLists, updateCounts);
/*      */     } else {
/* 2751 */       handler = new BatchResultHandler(queries, parameterLists, updateCounts, this.wantsGeneratedKeysAlways);
/*      */     } 
/* 2754 */     this.connection.getQueryExecutor().execute(queries, parameterLists, handler, this.maxrows, this.fetchSize, flags);
/* 2761 */     if (this.wantsGeneratedKeysAlways)
/* 2762 */       this.generatedKeys = new ResultWrapper(((BatchResultHandler)handler).getGeneratedKeys()); 
/* 2765 */     return updateCounts;
/*      */   }
/*      */   
/*      */   public void cancel() throws SQLException {
/* 2777 */     this.connection.cancelQuery();
/*      */   }
/*      */   
/*      */   public Connection getConnection() throws SQLException {
/* 2782 */     return (Connection)this.connection;
/*      */   }
/*      */   
/*      */   public int getFetchDirection() {
/* 2787 */     return this.fetchdirection;
/*      */   }
/*      */   
/*      */   public int getResultSetConcurrency() {
/* 2792 */     return this.concurrency;
/*      */   }
/*      */   
/*      */   public int getResultSetType() {
/* 2797 */     return this.resultsettype;
/*      */   }
/*      */   
/*      */   public void setFetchDirection(int direction) throws SQLException {
/* 2802 */     switch (direction) {
/*      */       case 1000:
/*      */       case 1001:
/*      */       case 1002:
/* 2807 */         this.fetchdirection = direction;
/*      */         return;
/*      */     } 
/* 2810 */     throw new PSQLException(GT.tr("Invalid fetch direction constant: {0}.", new Integer(direction)), PSQLState.INVALID_PARAMETER_VALUE);
/*      */   }
/*      */   
/*      */   public void setFetchSize(int rows) throws SQLException {
/* 2817 */     checkClosed();
/* 2818 */     if (rows < 0)
/* 2819 */       throw new PSQLException(GT.tr("Fetch size must be a value greater to or equal to 0."), PSQLState.INVALID_PARAMETER_VALUE); 
/* 2821 */     this.fetchSize = rows;
/*      */   }
/*      */   
/*      */   public void addBatch() throws SQLException {
/* 2826 */     checkClosed();
/* 2828 */     if (this.batchStatements == null) {
/* 2830 */       this.batchStatements = new ArrayList();
/* 2831 */       this.batchParameters = new ArrayList();
/*      */     } 
/* 2835 */     this.batchStatements.add(this.preparedQuery);
/* 2836 */     this.batchParameters.add(this.preparedParameters.copy());
/*      */   }
/*      */   
/*      */   public ResultSetMetaData getMetaData() throws SQLException {
/* 2841 */     checkClosed();
/* 2842 */     ResultSet rs = getResultSet();
/* 2844 */     if (rs == null) {
/* 2849 */       int flags = 49;
/* 2850 */       StatementResultHandler handler = new StatementResultHandler();
/* 2851 */       this.connection.getQueryExecutor().execute(this.preparedQuery, this.preparedParameters, handler, 0, 0, flags);
/* 2852 */       ResultWrapper wrapper = handler.getResults();
/* 2853 */       if (wrapper != null)
/* 2854 */         rs = wrapper.getResultSet(); 
/*      */     } 
/* 2858 */     if (rs != null)
/* 2859 */       return rs.getMetaData(); 
/* 2861 */     return null;
/*      */   }
/*      */   
/*      */   public void setArray(int i, Array x) throws SQLException {
/* 2866 */     checkClosed();
/* 2868 */     if (null == x) {
/* 2870 */       setNull(i, 2003);
/*      */       return;
/*      */     } 
/* 2881 */     String typename = "_" + x.getBaseTypeName();
/* 2882 */     int oid = this.connection.getTypeInfo().getPGType(typename);
/* 2883 */     if (oid == 0)
/* 2884 */       throw new PSQLException(GT.tr("Unknown type {0}.", typename), PSQLState.INVALID_PARAMETER_TYPE); 
/* 2886 */     setString(i, x.toString(), oid);
/*      */   }
/*      */   
/*      */   public void setBlob(int i, Blob x) throws SQLException {
/* 2891 */     checkClosed();
/* 2893 */     if (x == null) {
/* 2895 */       setNull(i, 2004);
/*      */       return;
/*      */     } 
/* 2899 */     InputStream l_inStream = x.getBinaryStream();
/* 2900 */     LargeObjectManager lom = this.connection.getLargeObjectAPI();
/* 2901 */     long oid = lom.createLO();
/* 2902 */     LargeObject lob = lom.open(oid);
/* 2903 */     OutputStream los = lob.getOutputStream();
/* 2904 */     byte[] buf = new byte[4096];
/*      */     try {
/* 2910 */       int bytesRemaining = (int)x.length();
/* 2911 */       int numRead = l_inStream.read(buf, 0, Math.min(buf.length, bytesRemaining));
/* 2912 */       while (numRead != -1 && bytesRemaining > 0) {
/* 2914 */         bytesRemaining -= numRead;
/* 2915 */         if (numRead == buf.length) {
/* 2916 */           los.write(buf);
/*      */         } else {
/* 2918 */           los.write(buf, 0, numRead);
/*      */         } 
/* 2919 */         numRead = l_inStream.read(buf, 0, Math.min(buf.length, bytesRemaining));
/*      */       } 
/* 2922 */     } catch (IOException se) {
/* 2924 */       throw new PSQLException(GT.tr("Unexpected error writing large object to database."), PSQLState.UNEXPECTED_ERROR, se);
/*      */     } finally {
/*      */       try {
/* 2930 */         los.close();
/* 2931 */         l_inStream.close();
/* 2933 */       } catch (Exception e) {}
/*      */     } 
/* 2937 */     setLong(i, oid);
/*      */   }
/*      */   
/*      */   public void setCharacterStream(int i, Reader x, int length) throws SQLException {
/* 2942 */     checkClosed();
/* 2944 */     if (x == null) {
/* 2945 */       if (this.connection.haveMinimumServerVersion("7.2")) {
/* 2946 */         setNull(i, 12);
/*      */       } else {
/* 2948 */         setNull(i, 2005);
/*      */       } 
/*      */       return;
/*      */     } 
/* 2953 */     if (length < 0)
/* 2954 */       throw new PSQLException(GT.tr("Invalid stream length {0}.", new Integer(length)), PSQLState.INVALID_PARAMETER_VALUE); 
/* 2957 */     if (this.connection.haveMinimumCompatibleVersion("7.2")) {
/* 2965 */       char[] l_chars = new char[length];
/* 2966 */       int l_charsRead = 0;
/*      */       try {
/*      */         do {
/* 2971 */           int n = x.read(l_chars, l_charsRead, length - l_charsRead);
/* 2972 */           if (n == -1)
/*      */             break; 
/* 2975 */           l_charsRead += n;
/* 2977 */         } while (l_charsRead != length);
/* 2981 */       } catch (IOException l_ioe) {
/* 2983 */         throw new PSQLException(GT.tr("Provided Reader failed."), PSQLState.UNEXPECTED_ERROR, l_ioe);
/*      */       } 
/* 2985 */       setString(i, new String(l_chars, 0, l_charsRead));
/*      */     } else {
/* 2992 */       LargeObjectManager lom = this.connection.getLargeObjectAPI();
/* 2993 */       long oid = lom.createLO();
/* 2994 */       LargeObject lob = lom.open(oid);
/* 2995 */       OutputStream los = lob.getOutputStream();
/*      */       try {
/* 3001 */         int c = x.read();
/* 3002 */         int p = 0;
/* 3003 */         while (c > -1 && p < length) {
/* 3005 */           los.write(c);
/* 3006 */           c = x.read();
/* 3007 */           p++;
/*      */         } 
/* 3009 */         los.close();
/* 3011 */       } catch (IOException se) {
/* 3013 */         throw new PSQLException(GT.tr("Unexpected error writing large object to database."), PSQLState.UNEXPECTED_ERROR, se);
/*      */       } 
/* 3016 */       setLong(i, oid);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setClob(int i, Clob x) throws SQLException {
/* 3022 */     checkClosed();
/* 3024 */     if (x == null) {
/* 3026 */       setNull(i, 2005);
/*      */       return;
/*      */     } 
/* 3030 */     InputStream l_inStream = x.getAsciiStream();
/* 3031 */     int l_length = (int)x.length();
/* 3032 */     LargeObjectManager lom = this.connection.getLargeObjectAPI();
/* 3033 */     long oid = lom.createLO();
/* 3034 */     LargeObject lob = lom.open(oid);
/* 3035 */     OutputStream los = lob.getOutputStream();
/*      */     try {
/* 3041 */       int c = l_inStream.read();
/* 3042 */       int p = 0;
/* 3043 */       while (c > -1 && p < l_length) {
/* 3045 */         los.write(c);
/* 3046 */         c = l_inStream.read();
/* 3047 */         p++;
/*      */       } 
/* 3049 */       los.close();
/* 3051 */     } catch (IOException se) {
/* 3053 */       throw new PSQLException(GT.tr("Unexpected error writing large object to database."), PSQLState.UNEXPECTED_ERROR, se);
/*      */     } 
/* 3056 */     setLong(i, oid);
/*      */   }
/*      */   
/*      */   public void setNull(int i, int t, String s) throws SQLException {
/* 3061 */     checkClosed();
/* 3062 */     setNull(i, t);
/*      */   }
/*      */   
/*      */   public void setRef(int i, Ref x) throws SQLException {
/* 3067 */     throw Driver.notImplemented(getClass(), "setRef(int,Ref)");
/*      */   }
/*      */   
/*      */   public void setDate(int i, Date d, Calendar cal) throws SQLException {
/* 3072 */     checkClosed();
/* 3074 */     if (d == null) {
/* 3076 */       setNull(i, 91);
/*      */       return;
/*      */     } 
/* 3081 */     if (cal != null)
/* 3082 */       cal = (Calendar)cal.clone(); 
/* 3103 */     bindString(i, this.connection.getTimestampUtils().toString(cal, d), 0);
/*      */   }
/*      */   
/*      */   public void setTime(int i, Time t, Calendar cal) throws SQLException {
/* 3108 */     checkClosed();
/* 3110 */     if (t == null) {
/* 3112 */       setNull(i, 92);
/*      */       return;
/*      */     } 
/* 3116 */     if (cal != null)
/* 3117 */       cal = (Calendar)cal.clone(); 
/* 3119 */     bindString(i, this.connection.getTimestampUtils().toString(cal, t), 0);
/*      */   }
/*      */   
/*      */   public void setTimestamp(int i, Timestamp t, Calendar cal) throws SQLException {
/* 3124 */     checkClosed();
/* 3126 */     if (t == null) {
/* 3127 */       setNull(i, 93);
/*      */       return;
/*      */     } 
/* 3131 */     if (cal != null)
/* 3132 */       cal = (Calendar)cal.clone(); 
/* 3164 */     bindString(i, this.connection.getTimestampUtils().toString(cal, t), 0);
/*      */   }
/*      */   
/*      */   public Array getArray(int i) throws SQLException {
/* 3171 */     checkClosed();
/* 3172 */     checkIndex(i, 2003, "Array");
/* 3173 */     return (Array)this.callResult[i - 1];
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
/* 3178 */     checkClosed();
/* 3179 */     checkIndex(parameterIndex, 2, "BigDecimal");
/* 3180 */     return (BigDecimal)this.callResult[parameterIndex - 1];
/*      */   }
/*      */   
/*      */   public Blob getBlob(int i) throws SQLException {
/* 3185 */     throw Driver.notImplemented(getClass(), "getBlob(int)");
/*      */   }
/*      */   
/*      */   public Clob getClob(int i) throws SQLException {
/* 3190 */     throw Driver.notImplemented(getClass(), "getClob(int)");
/*      */   }
/*      */   
/*      */   public Object getObjectImpl(int i, Map map) throws SQLException {
/* 3195 */     if (map == null || map.isEmpty())
/* 3196 */       return getObject(i); 
/* 3198 */     throw Driver.notImplemented(getClass(), "getObjectImpl(int,Map)");
/*      */   }
/*      */   
/*      */   public Ref getRef(int i) throws SQLException {
/* 3203 */     throw Driver.notImplemented(getClass(), "getRef(int)");
/*      */   }
/*      */   
/*      */   public Date getDate(int i, Calendar cal) throws SQLException {
/* 3208 */     checkClosed();
/* 3209 */     checkIndex(i, 91, "Date");
/* 3211 */     if (this.callResult[i - 1] == null)
/* 3212 */       return null; 
/* 3214 */     if (cal != null)
/* 3215 */       cal = (Calendar)cal.clone(); 
/* 3217 */     String value = this.callResult[i - 1].toString();
/* 3218 */     return this.connection.getTimestampUtils().toDate(cal, value);
/*      */   }
/*      */   
/*      */   public Time getTime(int i, Calendar cal) throws SQLException {
/* 3223 */     checkClosed();
/* 3224 */     checkIndex(i, 92, "Time");
/* 3226 */     if (this.callResult[i - 1] == null)
/* 3227 */       return null; 
/* 3229 */     if (cal != null)
/* 3230 */       cal = (Calendar)cal.clone(); 
/* 3232 */     String value = this.callResult[i - 1].toString();
/* 3233 */     return this.connection.getTimestampUtils().toTime(cal, value);
/*      */   }
/*      */   
/*      */   public Timestamp getTimestamp(int i, Calendar cal) throws SQLException {
/* 3238 */     checkClosed();
/* 3239 */     checkIndex(i, 93, "Timestamp");
/* 3241 */     if (this.callResult[i - 1] == null)
/* 3242 */       return null; 
/* 3244 */     if (cal != null)
/* 3245 */       cal = (Calendar)cal.clone(); 
/* 3247 */     String value = this.callResult[i - 1].toString();
/* 3248 */     return this.connection.getTimestampUtils().toTimestamp(cal, value);
/*      */   }
/*      */   
/*      */   public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
/* 3254 */     throw Driver.notImplemented(getClass(), "registerOutParameter(int,int,String)");
/*      */   }
/*      */   
/*      */   public abstract ResultSet createResultSet(Query paramQuery, Field[] paramArrayOfField, Vector paramVector, ResultCursor paramResultCursor) throws SQLException;
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\AbstractJdbc2Statement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */