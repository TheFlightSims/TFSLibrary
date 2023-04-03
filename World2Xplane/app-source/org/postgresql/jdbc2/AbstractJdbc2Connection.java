/*      */ package org.postgresql.jdbc2;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.PrintWriter;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.DriverManager;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Statement;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import org.postgresql.Driver;
/*      */ import org.postgresql.PGNotification;
/*      */ import org.postgresql.copy.CopyManager;
/*      */ import org.postgresql.core.BaseConnection;
/*      */ import org.postgresql.core.BaseStatement;
/*      */ import org.postgresql.core.ConnectionFactory;
/*      */ import org.postgresql.core.Encoding;
/*      */ import org.postgresql.core.Field;
/*      */ import org.postgresql.core.Logger;
/*      */ import org.postgresql.core.ProtocolConnection;
/*      */ import org.postgresql.core.Query;
/*      */ import org.postgresql.core.QueryExecutor;
/*      */ import org.postgresql.core.ResultCursor;
/*      */ import org.postgresql.core.ResultHandler;
/*      */ import org.postgresql.core.TypeInfo;
/*      */ import org.postgresql.core.Utils;
/*      */ import org.postgresql.fastpath.Fastpath;
/*      */ import org.postgresql.geometric.PGbox;
/*      */ import org.postgresql.geometric.PGcircle;
/*      */ import org.postgresql.geometric.PGline;
/*      */ import org.postgresql.geometric.PGlseg;
/*      */ import org.postgresql.geometric.PGpath;
/*      */ import org.postgresql.geometric.PGpoint;
/*      */ import org.postgresql.geometric.PGpolygon;
/*      */ import org.postgresql.largeobject.LargeObjectManager;
/*      */ import org.postgresql.util.GT;
/*      */ import org.postgresql.util.PGInterval;
/*      */ import org.postgresql.util.PGmoney;
/*      */ import org.postgresql.util.PGobject;
/*      */ import org.postgresql.util.PSQLException;
/*      */ import org.postgresql.util.PSQLState;
/*      */ 
/*      */ public abstract class AbstractJdbc2Connection implements BaseConnection {
/*   36 */   private static int nextConnectionID = 1;
/*      */   
/*      */   private final Logger logger;
/*      */   
/*      */   private final String creatingURL;
/*      */   
/*      */   private Throwable openStackTrace;
/*      */   
/*      */   private final ProtocolConnection protoConnection;
/*      */   
/*      */   private final String compatible;
/*      */   
/*      */   private final String dbVersionNumber;
/*      */   
/*      */   private final Query commitQuery;
/*      */   
/*      */   private final Query rollbackQuery;
/*      */   
/*      */   private TypeInfo _typeCache;
/*      */   
/*      */   protected int prepareThreshold;
/*      */   
/*      */   public boolean autoCommit = true;
/*      */   
/*      */   public boolean readOnly = false;
/*      */   
/*      */   public final boolean bindStringAsVarchar;
/*      */   
/*   75 */   public SQLWarning firstWarning = null;
/*      */   
/*      */   private final TimestampUtils timestampUtils;
/*      */   
/*      */   protected Map typemap;
/*      */   
/*      */   private Fastpath fastpath;
/*      */   
/*      */   private LargeObjectManager largeobject;
/*      */   
/*      */   protected DatabaseMetaData metadata;
/*      */   
/*      */   private CopyManager copyManager;
/*      */   
/*      */   public TimestampUtils getTimestampUtils() {
/*  181 */     return this.timestampUtils;
/*      */   }
/*      */   
/*      */   public Statement createStatement() throws SQLException {
/*  191 */     return createStatement(1003, 1007);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String sql) throws SQLException {
/*  198 */     return prepareStatement(sql, 1003, 1007);
/*      */   }
/*      */   
/*      */   public CallableStatement prepareCall(String sql) throws SQLException {
/*  205 */     return prepareCall(sql, 1003, 1007);
/*      */   }
/*      */   
/*      */   public Map getTypeMap() throws SQLException {
/*  212 */     checkClosed();
/*  213 */     return this.typemap;
/*      */   }
/*      */   
/*      */   public QueryExecutor getQueryExecutor() {
/*  218 */     return this.protoConnection.getQueryExecutor();
/*      */   }
/*      */   
/*      */   public void addWarning(SQLWarning warn) {
/*  228 */     if (this.firstWarning != null) {
/*  229 */       this.firstWarning.setNextWarning(warn);
/*      */     } else {
/*  231 */       this.firstWarning = warn;
/*      */     } 
/*      */   }
/*      */   
/*      */   public ResultSet execSQLQuery(String s) throws SQLException {
/*  236 */     return execSQLQuery(s, 1003, 1007);
/*      */   }
/*      */   
/*      */   public ResultSet execSQLQuery(String s, int resultSetType, int resultSetConcurrency) throws SQLException {
/*  243 */     BaseStatement stat = (BaseStatement)createStatement(resultSetType, resultSetConcurrency);
/*  244 */     boolean hasResultSet = stat.executeWithFlags(s, 16);
/*  246 */     while (!hasResultSet && stat.getUpdateCount() != -1)
/*  247 */       hasResultSet = stat.getMoreResults(); 
/*  249 */     if (!hasResultSet)
/*  250 */       throw new PSQLException(GT.tr("No results were returned by the query."), PSQLState.NO_DATA); 
/*  254 */     SQLWarning warnings = stat.getWarnings();
/*  255 */     if (warnings != null)
/*  256 */       addWarning(warnings); 
/*  258 */     return stat.getResultSet();
/*      */   }
/*      */   
/*      */   public void execSQLUpdate(String s) throws SQLException {
/*  262 */     BaseStatement stmt = (BaseStatement)createStatement();
/*  263 */     if (stmt.executeWithFlags(s, 22))
/*  264 */       throw new PSQLException(GT.tr("A result was returned when none was expected."), PSQLState.TOO_MANY_RESULTS); 
/*  269 */     SQLWarning warnings = stmt.getWarnings();
/*  270 */     if (warnings != null)
/*  271 */       addWarning(warnings); 
/*  273 */     stmt.close();
/*      */   }
/*      */   
/*      */   public void setCursorName(String cursor) throws SQLException {
/*  289 */     checkClosed();
/*      */   }
/*      */   
/*      */   public String getCursorName() throws SQLException {
/*  301 */     checkClosed();
/*  302 */     return null;
/*      */   }
/*      */   
/*      */   public String getURL() throws SQLException {
/*  316 */     return this.creatingURL;
/*      */   }
/*      */   
/*      */   public String getUserName() throws SQLException {
/*  328 */     return this.protoConnection.getUser();
/*      */   }
/*      */   
/*      */   public Fastpath getFastpathAPI() throws SQLException {
/*  355 */     checkClosed();
/*  356 */     if (this.fastpath == null)
/*  357 */       this.fastpath = new Fastpath(this); 
/*  358 */     return this.fastpath;
/*      */   }
/*      */   
/*      */   protected AbstractJdbc2Connection(String host, int port, String user, String database, Properties info, String url) throws SQLException {
/*  362 */     this.fastpath = null;
/*  392 */     this.largeobject = null;
/* 1106 */     this.copyManager = null;
/*      */     this.creatingURL = url;
/*      */     int logLevel = Driver.getLogLevel();
/*      */     String connectionLogLevel = info.getProperty("loglevel");
/*      */     if (connectionLogLevel != null)
/*      */       try {
/*      */         logLevel = Integer.parseInt(connectionLogLevel);
/*      */       } catch (Exception l_e) {} 
/*      */     synchronized (AbstractJdbc2Connection.class) {
/*      */       this.logger = new Logger(nextConnectionID++);
/*      */       this.logger.setLogLevel(logLevel);
/*      */     } 
/*      */     if (logLevel > 0)
/*      */       enableDriverManagerLogging(); 
/*      */     this.prepareThreshold = 5;
/*      */     try {
/*      */       this.prepareThreshold = Integer.parseInt(info.getProperty("prepareThreshold", "5"));
/*      */       if (this.prepareThreshold < 0)
/*      */         this.prepareThreshold = 0; 
/*      */     } catch (Exception e) {}
/*      */     if (this.logger.logInfo())
/*      */       this.logger.info(Driver.getVersion()); 
/*      */     this.protoConnection = ConnectionFactory.openConnection(host, port, user, database, info, this.logger);
/*      */     this.dbVersionNumber = this.protoConnection.getServerVersion();
/*      */     this.compatible = info.getProperty("compatible", "9.1");
/*      */     if (this.logger.logDebug()) {
/*      */       this.logger.debug("    compatible = " + this.compatible);
/*      */       this.logger.debug("    loglevel = " + logLevel);
/*      */       this.logger.debug("    prepare threshold = " + this.prepareThreshold);
/*      */     } 
/*      */     String stringType = info.getProperty("stringtype");
/*      */     if (stringType != null) {
/*      */       if (stringType.equalsIgnoreCase("unspecified")) {
/*      */         this.bindStringAsVarchar = false;
/*      */       } else if (stringType.equalsIgnoreCase("varchar")) {
/*      */         this.bindStringAsVarchar = true;
/*      */       } else {
/*      */         throw new PSQLException(GT.tr("Unsupported value for stringtype parameter: {0}", stringType), PSQLState.INVALID_PARAMETER_VALUE);
/*      */       } 
/*      */     } else {
/*      */       this.bindStringAsVarchar = haveMinimumCompatibleVersion("8.0");
/*      */     } 
/*      */     this.timestampUtils = new TimestampUtils(haveMinimumServerVersion("7.4"), haveMinimumServerVersion("8.2"));
/*      */     this.commitQuery = getQueryExecutor().createSimpleQuery("COMMIT");
/*      */     this.rollbackQuery = getQueryExecutor().createSimpleQuery("ROLLBACK");
/*      */     int unknownLength = Integer.MAX_VALUE;
/*      */     String strLength = info.getProperty("unknownLength");
/*      */     if (strLength != null)
/*      */       try {
/*      */         unknownLength = Integer.parseInt(strLength);
/*      */       } catch (NumberFormatException nfe) {
/*      */         throw new PSQLException(GT.tr("unknownLength parameter value must be an integer"), PSQLState.INVALID_PARAMETER_VALUE, nfe);
/*      */       }  
/*      */     this._typeCache = createTypeInfo(this, unknownLength);
/*      */     initObjectTypes(info);
/*      */     if (Boolean.valueOf(info.getProperty("logUnclosedConnections")).booleanValue()) {
/*      */       this.openStackTrace = new Throwable("Connection was created at this point:");
/*      */       enableDriverManagerLogging();
/*      */     } 
/*      */   }
/*      */   
/*      */   public LargeObjectManager getLargeObjectAPI() throws SQLException {
/*      */     checkClosed();
/*      */     if (this.largeobject == null)
/*      */       this.largeobject = new LargeObjectManager(this); 
/*      */     return this.largeobject;
/*      */   }
/*      */   
/*      */   public Object getObject(String type, String value) throws SQLException {
/*      */     if (this.typemap != null) {
/*      */       Class c = (Class)this.typemap.get(type);
/*      */       if (c != null)
/*      */         throw new PSQLException(GT.tr("Custom type maps are not supported."), PSQLState.NOT_IMPLEMENTED); 
/*      */     } 
/*      */     PGobject obj = null;
/*      */     if (this.logger.logDebug())
/*      */       this.logger.debug("Constructing object from type=" + type + " value=<" + value + ">"); 
/*      */     try {
/*      */       Class<PGobject> klass = this._typeCache.getPGobject(type);
/*      */       if (klass != null) {
/*      */         obj = klass.newInstance();
/*      */         obj.setType(type);
/*      */         obj.setValue(value);
/*      */       } else {
/*      */         obj = new PGobject();
/*      */         obj.setType(type);
/*      */         obj.setValue(value);
/*      */       } 
/*      */       return obj;
/*      */     } catch (SQLException sx) {
/*      */       throw sx;
/*      */     } catch (Exception ex) {
/*      */       throw new PSQLException(GT.tr("Failed to create object for: {0}.", type), PSQLState.CONNECTION_FAILURE, ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected TypeInfo createTypeInfo(BaseConnection conn, int unknownLength) {
/*      */     return new TypeInfoCache(conn, unknownLength);
/*      */   }
/*      */   
/*      */   public TypeInfo getTypeInfo() {
/*      */     return this._typeCache;
/*      */   }
/*      */   
/*      */   public void addDataType(String type, String name) {
/*      */     try {
/*      */       addDataType(type, Class.forName(name));
/*      */     } catch (Exception e) {
/*      */       throw new RuntimeException("Cannot register new type: " + e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addDataType(String type, Class klass) throws SQLException {
/*      */     checkClosed();
/*      */     this._typeCache.addDataType(type, klass);
/*      */   }
/*      */   
/*      */   private void initObjectTypes(Properties info) throws SQLException {
/*      */     addDataType("box", PGbox.class);
/*      */     addDataType("circle", PGcircle.class);
/*      */     addDataType("line", PGline.class);
/*      */     addDataType("lseg", PGlseg.class);
/*      */     addDataType("path", PGpath.class);
/*      */     addDataType("point", PGpoint.class);
/*      */     addDataType("polygon", PGpolygon.class);
/*      */     addDataType("money", PGmoney.class);
/*      */     addDataType("interval", PGInterval.class);
/*      */     for (Enumeration<?> e = info.propertyNames(); e.hasMoreElements(); ) {
/*      */       String propertyName = (String)e.nextElement();
/*      */       if (propertyName.startsWith("datatype.")) {
/*      */         Class<?> klass;
/*      */         String typeName = propertyName.substring(9);
/*      */         String className = info.getProperty(propertyName);
/*      */         try {
/*      */           klass = Class.forName(className);
/*      */         } catch (ClassNotFoundException cnfe) {
/*      */           throw new PSQLException(GT.tr("Unable to load the class {0} responsible for the datatype {1}", new Object[] { className, typeName }), PSQLState.SYSTEM_ERROR, cnfe);
/*      */         } 
/*      */         addDataType(typeName, klass);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void close() {
/*      */     this.protoConnection.close();
/*      */     this.openStackTrace = null;
/*      */   }
/*      */   
/*      */   public String nativeSQL(String sql) throws SQLException {
/*      */     checkClosed();
/*      */     StringBuffer buf = new StringBuffer(sql.length());
/*      */     AbstractJdbc2Statement.parseSql(sql, 0, buf, false, getStandardConformingStrings());
/*      */     return buf.toString();
/*      */   }
/*      */   
/*      */   public synchronized SQLWarning getWarnings() throws SQLException {
/*      */     checkClosed();
/*      */     SQLWarning newWarnings = this.protoConnection.getWarnings();
/*      */     if (this.firstWarning == null) {
/*      */       this.firstWarning = newWarnings;
/*      */     } else {
/*      */       this.firstWarning.setNextWarning(newWarnings);
/*      */     } 
/*      */     return this.firstWarning;
/*      */   }
/*      */   
/*      */   public synchronized void clearWarnings() throws SQLException {
/*      */     checkClosed();
/*      */     this.protoConnection.getWarnings();
/*      */     this.firstWarning = null;
/*      */   }
/*      */   
/*      */   public void setReadOnly(boolean readOnly) throws SQLException {
/*      */     checkClosed();
/*      */     if (this.protoConnection.getTransactionState() != 0)
/*      */       throw new PSQLException(GT.tr("Cannot change transaction read-only property in the middle of a transaction."), PSQLState.ACTIVE_SQL_TRANSACTION); 
/*      */     if (haveMinimumServerVersion("7.4") && readOnly != this.readOnly) {
/*      */       String readOnlySql = "SET SESSION CHARACTERISTICS AS TRANSACTION " + (readOnly ? "READ ONLY" : "READ WRITE");
/*      */       execSQLUpdate(readOnlySql);
/*      */     } 
/*      */     this.readOnly = readOnly;
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() throws SQLException {
/*      */     checkClosed();
/*      */     return this.readOnly;
/*      */   }
/*      */   
/*      */   public void setAutoCommit(boolean autoCommit) throws SQLException {
/*      */     checkClosed();
/*      */     if (this.autoCommit == autoCommit)
/*      */       return; 
/*      */     if (!this.autoCommit)
/*      */       commit(); 
/*      */     this.autoCommit = autoCommit;
/*      */   }
/*      */   
/*      */   public boolean getAutoCommit() throws SQLException {
/*      */     checkClosed();
/*      */     return this.autoCommit;
/*      */   }
/*      */   
/*      */   private void executeTransactionCommand(Query query) throws SQLException {
/*      */     getQueryExecutor().execute(query, null, new TransactionCommandHandler(), 0, 0, 22);
/*      */   }
/*      */   
/*      */   public void commit() throws SQLException {
/*      */     checkClosed();
/*      */     if (this.autoCommit)
/*      */       throw new PSQLException(GT.tr("Cannot commit when autoCommit is enabled."), PSQLState.NO_ACTIVE_SQL_TRANSACTION); 
/*      */     if (this.protoConnection.getTransactionState() != 0)
/*      */       executeTransactionCommand(this.commitQuery); 
/*      */   }
/*      */   
/*      */   protected void checkClosed() throws SQLException {
/*      */     if (isClosed())
/*      */       throw new PSQLException(GT.tr("This connection has been closed."), PSQLState.CONNECTION_DOES_NOT_EXIST); 
/*      */   }
/*      */   
/*      */   public void rollback() throws SQLException {
/*      */     checkClosed();
/*      */     if (this.autoCommit)
/*      */       throw new PSQLException(GT.tr("Cannot rollback when autoCommit is enabled."), PSQLState.NO_ACTIVE_SQL_TRANSACTION); 
/*      */     if (this.protoConnection.getTransactionState() != 0)
/*      */       executeTransactionCommand(this.rollbackQuery); 
/*      */   }
/*      */   
/*      */   public int getTransactionState() {
/*      */     return this.protoConnection.getTransactionState();
/*      */   }
/*      */   
/*      */   public int getTransactionIsolation() throws SQLException {
/*      */     checkClosed();
/*      */     String level = null;
/*      */     if (haveMinimumServerVersion("7.3")) {
/*      */       ResultSet rs = execSQLQuery("SHOW TRANSACTION ISOLATION LEVEL");
/*      */       if (rs.next())
/*      */         level = rs.getString(1); 
/*      */       rs.close();
/*      */     } else {
/*      */       SQLWarning saveWarnings = getWarnings();
/*      */       clearWarnings();
/*      */       execSQLUpdate("SHOW TRANSACTION ISOLATION LEVEL");
/*      */       SQLWarning warning = getWarnings();
/*      */       if (warning != null)
/*      */         level = warning.getMessage(); 
/*      */       clearWarnings();
/*      */       if (saveWarnings != null)
/*      */         addWarning(saveWarnings); 
/*      */     } 
/*      */     if (level == null)
/*      */       return 2; 
/*      */     level = level.toUpperCase(Locale.US);
/*      */     if (level.indexOf("READ COMMITTED") != -1)
/*      */       return 2; 
/*      */     if (level.indexOf("READ UNCOMMITTED") != -1)
/*      */       return 1; 
/*      */     if (level.indexOf("REPEATABLE READ") != -1)
/*      */       return 4; 
/*      */     if (level.indexOf("SERIALIZABLE") != -1)
/*      */       return 8; 
/*      */     return 2;
/*      */   }
/*      */   
/*      */   public void setTransactionIsolation(int level) throws SQLException {
/*      */     checkClosed();
/*      */     if (this.protoConnection.getTransactionState() != 0)
/*      */       throw new PSQLException(GT.tr("Cannot change transaction isolation level in the middle of a transaction."), PSQLState.ACTIVE_SQL_TRANSACTION); 
/*      */     String isolationLevelName = getIsolationLevelName(level);
/*      */     if (isolationLevelName == null)
/*      */       throw new PSQLException(GT.tr("Transaction isolation level {0} not supported.", new Integer(level)), PSQLState.NOT_IMPLEMENTED); 
/*      */     String isolationLevelSQL = "SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL " + isolationLevelName;
/*      */     execSQLUpdate(isolationLevelSQL);
/*      */   }
/*      */   
/*      */   protected String getIsolationLevelName(int level) {
/*      */     boolean pg80 = haveMinimumServerVersion("8.0");
/*      */     if (level == 2)
/*      */       return "READ COMMITTED"; 
/*      */     if (level == 8)
/*      */       return "SERIALIZABLE"; 
/*      */     if (pg80 && level == 1)
/*      */       return "READ UNCOMMITTED"; 
/*      */     if (pg80 && level == 4)
/*      */       return "REPEATABLE READ"; 
/*      */     return null;
/*      */   }
/*      */   
/*      */   public void setCatalog(String catalog) throws SQLException {
/*      */     checkClosed();
/*      */   }
/*      */   
/*      */   public String getCatalog() throws SQLException {
/*      */     checkClosed();
/*      */     return this.protoConnection.getDatabase();
/*      */   }
/*      */   
/*      */   protected void finalize() throws Throwable {
/*      */     if (this.openStackTrace != null)
/*      */       this.logger.log(GT.tr("Finalizing a Connection that was never closed:"), this.openStackTrace); 
/*      */     close();
/*      */   }
/*      */   
/*      */   public String getDBVersionNumber() {
/*      */     return this.dbVersionNumber;
/*      */   }
/*      */   
/*      */   private static int integerPart(String dirtyString) {
/*      */     int start;
/*      */     for (start = 0; start < dirtyString.length() && !Character.isDigit(dirtyString.charAt(start)); start++);
/*      */     int end;
/*      */     for (end = start; end < dirtyString.length() && Character.isDigit(dirtyString.charAt(end)); end++);
/*      */     if (start == end)
/*      */       return 0; 
/*      */     return Integer.parseInt(dirtyString.substring(start, end));
/*      */   }
/*      */   
/*      */   public int getServerMajorVersion() {
/*      */     try {
/*      */       StringTokenizer versionTokens = new StringTokenizer(this.dbVersionNumber, ".");
/*      */       return integerPart(versionTokens.nextToken());
/*      */     } catch (NoSuchElementException e) {
/*      */       return 0;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getServerMinorVersion() {
/*      */     try {
/*      */       StringTokenizer versionTokens = new StringTokenizer(this.dbVersionNumber, ".");
/*      */       versionTokens.nextToken();
/*      */       return integerPart(versionTokens.nextToken());
/*      */     } catch (NoSuchElementException e) {
/*      */       return 0;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean haveMinimumServerVersion(String ver) {
/*      */     return (this.dbVersionNumber.compareTo(ver) >= 0);
/*      */   }
/*      */   
/*      */   public boolean haveMinimumCompatibleVersion(String ver) {
/*      */     return (this.compatible.compareTo(ver) >= 0);
/*      */   }
/*      */   
/*      */   public Encoding getEncoding() {
/*      */     return this.protoConnection.getEncoding();
/*      */   }
/*      */   
/*      */   public byte[] encodeString(String str) throws SQLException {
/*      */     try {
/*      */       return getEncoding().encode(str);
/*      */     } catch (IOException ioe) {
/*      */       throw new PSQLException(GT.tr("Unable to translate data into the desired encoding."), PSQLState.DATA_ERROR, ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String escapeString(String str) throws SQLException {
/*      */     return Utils.appendEscapedLiteral(null, str, this.protoConnection.getStandardConformingStrings()).toString();
/*      */   }
/*      */   
/*      */   public boolean getStandardConformingStrings() {
/*      */     return this.protoConnection.getStandardConformingStrings();
/*      */   }
/*      */   
/*      */   public boolean isClosed() throws SQLException {
/*      */     return this.protoConnection.isClosed();
/*      */   }
/*      */   
/*      */   public void cancelQuery() throws SQLException {
/*      */     checkClosed();
/*      */     this.protoConnection.sendQueryCancel();
/*      */   }
/*      */   
/*      */   public PGNotification[] getNotifications() throws SQLException {
/*      */     checkClosed();
/*      */     getQueryExecutor().processNotifies();
/*      */     PGNotification[] notifications = this.protoConnection.getNotifications();
/*      */     return (notifications.length == 0) ? null : notifications;
/*      */   }
/*      */   
/*      */   private class TransactionCommandHandler implements ResultHandler {
/*      */     private SQLException error;
/*      */     
/*      */     private TransactionCommandHandler() {}
/*      */     
/*      */     public void handleResultRows(Query fromQuery, Field[] fields, Vector tuples, ResultCursor cursor) {}
/*      */     
/*      */     public void handleCommandStatus(String status, int updateCount, long insertOID) {}
/*      */     
/*      */     public void handleWarning(SQLWarning warning) {
/*      */       AbstractJdbc2Connection.this.addWarning(warning);
/*      */     }
/*      */     
/*      */     public void handleError(SQLException newError) {
/*      */       if (this.error == null) {
/*      */         this.error = newError;
/*      */       } else {
/*      */         this.error.setNextException(newError);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void handleCompletion() throws SQLException {
/*      */       if (this.error != null)
/*      */         throw this.error; 
/*      */     }
/*      */   }
/*      */   
/*      */   public int getPrepareThreshold() {
/*      */     return this.prepareThreshold;
/*      */   }
/*      */   
/*      */   public void setPrepareThreshold(int newThreshold) {
/*      */     this.prepareThreshold = (newThreshold <= 0) ? 0 : newThreshold;
/*      */   }
/*      */   
/*      */   public void setTypeMapImpl(Map map) throws SQLException {
/*      */     this.typemap = map;
/*      */   }
/*      */   
/*      */   public Logger getLogger() {
/*      */     return this.logger;
/*      */   }
/*      */   
/*      */   protected void enableDriverManagerLogging() {
/*      */     if (DriverManager.getLogWriter() == null)
/*      */       DriverManager.setLogWriter(new PrintWriter(System.out, true)); 
/*      */   }
/*      */   
/*      */   public int getProtocolVersion() {
/*      */     return this.protoConnection.getProtocolVersion();
/*      */   }
/*      */   
/*      */   public boolean getStringVarcharFlag() {
/*      */     return this.bindStringAsVarchar;
/*      */   }
/*      */   
/*      */   public CopyManager getCopyAPI() throws SQLException {
/* 1109 */     checkClosed();
/* 1110 */     if (this.copyManager == null)
/* 1111 */       this.copyManager = new CopyManager(this); 
/* 1112 */     return this.copyManager;
/*      */   }
/*      */   
/*      */   public abstract DatabaseMetaData getMetaData() throws SQLException;
/*      */   
/*      */   public abstract Statement createStatement(int paramInt1, int paramInt2) throws SQLException;
/*      */   
/*      */   public abstract PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2) throws SQLException;
/*      */   
/*      */   public abstract CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2) throws SQLException;
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\AbstractJdbc2Connection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */