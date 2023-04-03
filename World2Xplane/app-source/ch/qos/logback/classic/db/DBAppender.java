/*     */ package ch.qos.logback.classic.db;
/*     */ 
/*     */ import ch.qos.logback.classic.db.names.DBNameResolver;
/*     */ import ch.qos.logback.classic.db.names.DefaultDBNameResolver;
/*     */ import ch.qos.logback.classic.spi.CallerData;
/*     */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*     */ import ch.qos.logback.classic.spi.IThrowableProxy;
/*     */ import ch.qos.logback.classic.spi.StackTraceElementProxy;
/*     */ import ch.qos.logback.classic.spi.ThrowableProxyUtil;
/*     */ import ch.qos.logback.core.db.DBAppenderBase;
/*     */ import java.lang.reflect.Method;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DBAppender extends DBAppenderBase<ILoggingEvent> {
/*     */   protected String insertPropertiesSQL;
/*     */   
/*     */   protected String insertExceptionSQL;
/*     */   
/*     */   protected String insertSQL;
/*     */   
/*     */   protected static final Method GET_GENERATED_KEYS_METHOD;
/*     */   
/*     */   private DBNameResolver dbNameResolver;
/*     */   
/*     */   static final int TIMESTMP_INDEX = 1;
/*     */   
/*     */   static final int FORMATTED_MESSAGE_INDEX = 2;
/*     */   
/*     */   static final int LOGGER_NAME_INDEX = 3;
/*     */   
/*     */   static final int LEVEL_STRING_INDEX = 4;
/*     */   
/*     */   static final int THREAD_NAME_INDEX = 5;
/*     */   
/*     */   static final int REFERENCE_FLAG_INDEX = 6;
/*     */   
/*     */   static final int ARG0_INDEX = 7;
/*     */   
/*     */   static final int ARG1_INDEX = 8;
/*     */   
/*     */   static final int ARG2_INDEX = 9;
/*     */   
/*     */   static final int ARG3_INDEX = 10;
/*     */   
/*     */   static final int CALLER_FILENAME_INDEX = 11;
/*     */   
/*     */   static final int CALLER_CLASS_INDEX = 12;
/*     */   
/*     */   static final int CALLER_METHOD_INDEX = 13;
/*     */   
/*     */   static final int CALLER_LINE_INDEX = 14;
/*     */   
/*     */   static final int EVENT_ID_INDEX = 15;
/*     */   
/*     */   static {
/*     */     Method method;
/*     */   }
/*     */   
/*  66 */   static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();
/*     */   
/*     */   static {
/*     */     try {
/*  73 */       method = PreparedStatement.class.getMethod("getGeneratedKeys", (Class[])null);
/*  75 */     } catch (Exception ex) {
/*  76 */       method = null;
/*     */     } 
/*  78 */     GET_GENERATED_KEYS_METHOD = method;
/*     */   }
/*     */   
/*     */   public void setDbNameResolver(DBNameResolver dbNameResolver) {
/*  82 */     this.dbNameResolver = dbNameResolver;
/*     */   }
/*     */   
/*     */   public void start() {
/*  87 */     if (this.dbNameResolver == null)
/*  88 */       this.dbNameResolver = (DBNameResolver)new DefaultDBNameResolver(); 
/*  89 */     this.insertExceptionSQL = SQLBuilder.buildInsertExceptionSQL(this.dbNameResolver);
/*  90 */     this.insertPropertiesSQL = SQLBuilder.buildInsertPropertiesSQL(this.dbNameResolver);
/*  91 */     this.insertSQL = SQLBuilder.buildInsertSQL(this.dbNameResolver);
/*  92 */     super.start();
/*     */   }
/*     */   
/*     */   protected void subAppend(ILoggingEvent event, Connection connection, PreparedStatement insertStatement) throws Throwable {
/*  99 */     bindLoggingEventWithInsertStatement(insertStatement, event);
/* 100 */     bindLoggingEventArgumentsWithPreparedStatement(insertStatement, event.getArgumentArray());
/* 103 */     bindCallerDataWithPreparedStatement(insertStatement, event.getCallerData());
/* 105 */     int updateCount = insertStatement.executeUpdate();
/* 106 */     if (updateCount != 1)
/* 107 */       addWarn("Failed to insert loggingEvent"); 
/*     */   }
/*     */   
/*     */   protected void secondarySubAppend(ILoggingEvent event, Connection connection, long eventId) throws Throwable {
/* 113 */     Map<String, String> mergedMap = mergePropertyMaps(event);
/* 114 */     insertProperties(mergedMap, connection, eventId);
/* 116 */     if (event.getThrowableProxy() != null)
/* 117 */       insertThrowable(event.getThrowableProxy(), connection, eventId); 
/*     */   }
/*     */   
/*     */   void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
/* 123 */     stmt.setLong(1, event.getTimeStamp());
/* 124 */     stmt.setString(2, event.getFormattedMessage());
/* 125 */     stmt.setString(3, event.getLoggerName());
/* 126 */     stmt.setString(4, event.getLevel().toString());
/* 127 */     stmt.setString(5, event.getThreadName());
/* 128 */     stmt.setShort(6, DBHelper.computeReferenceMask(event));
/*     */   }
/*     */   
/*     */   void bindLoggingEventArgumentsWithPreparedStatement(PreparedStatement stmt, Object[] argArray) throws SQLException {
/* 134 */     int arrayLen = (argArray != null) ? argArray.length : 0;
/*     */     int i;
/* 136 */     for (i = 0; i < arrayLen && i < 4; i++)
/* 137 */       stmt.setString(7 + i, asStringTruncatedTo254(argArray[i])); 
/* 139 */     if (arrayLen < 4)
/* 140 */       for (i = arrayLen; i < 4; i++)
/* 141 */         stmt.setString(7 + i, (String)null);  
/*     */   }
/*     */   
/*     */   String asStringTruncatedTo254(Object o) {
/* 147 */     String s = null;
/* 148 */     if (o != null)
/* 149 */       s = o.toString(); 
/* 152 */     if (s == null)
/* 153 */       return null; 
/* 155 */     if (s.length() <= 254)
/* 156 */       return s; 
/* 158 */     return s.substring(0, 254);
/*     */   }
/*     */   
/*     */   void bindCallerDataWithPreparedStatement(PreparedStatement stmt, StackTraceElement[] callerDataArray) throws SQLException {
/* 165 */     StackTraceElement caller = extractFirstCaller(callerDataArray);
/* 167 */     stmt.setString(11, caller.getFileName());
/* 168 */     stmt.setString(12, caller.getClassName());
/* 169 */     stmt.setString(13, caller.getMethodName());
/* 170 */     stmt.setString(14, Integer.toString(caller.getLineNumber()));
/*     */   }
/*     */   
/*     */   private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {
/* 174 */     StackTraceElement caller = EMPTY_CALLER_DATA;
/* 175 */     if (hasAtLeastOneNonNullElement(callerDataArray))
/* 176 */       caller = callerDataArray[0]; 
/* 177 */     return caller;
/*     */   }
/*     */   
/*     */   private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
/* 181 */     return (callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null);
/*     */   }
/*     */   
/*     */   Map<String, String> mergePropertyMaps(ILoggingEvent event) {
/* 185 */     Map<String, String> mergedMap = new HashMap<String, String>();
/* 189 */     Map<String, String> loggerContextMap = event.getLoggerContextVO().getPropertyMap();
/* 191 */     Map<String, String> mdcMap = event.getMDCPropertyMap();
/* 192 */     if (loggerContextMap != null)
/* 193 */       mergedMap.putAll(loggerContextMap); 
/* 195 */     if (mdcMap != null)
/* 196 */       mergedMap.putAll(mdcMap); 
/* 199 */     return mergedMap;
/*     */   }
/*     */   
/*     */   protected Method getGeneratedKeysMethod() {
/* 204 */     return GET_GENERATED_KEYS_METHOD;
/*     */   }
/*     */   
/*     */   protected String getInsertSQL() {
/* 209 */     return this.insertSQL;
/*     */   }
/*     */   
/*     */   protected void insertProperties(Map<String, String> mergedMap, Connection connection, long eventId) throws SQLException {
/* 214 */     Set<String> propertiesKeys = mergedMap.keySet();
/* 215 */     if (propertiesKeys.size() > 0) {
/* 216 */       PreparedStatement insertPropertiesStatement = connection.prepareStatement(this.insertPropertiesSQL);
/* 219 */       for (Iterator<String> i = propertiesKeys.iterator(); i.hasNext(); ) {
/* 220 */         String key = i.next();
/* 221 */         String value = mergedMap.get(key);
/* 223 */         insertPropertiesStatement.setLong(1, eventId);
/* 224 */         insertPropertiesStatement.setString(2, key);
/* 225 */         insertPropertiesStatement.setString(3, value);
/* 227 */         if (this.cnxSupportsBatchUpdates) {
/* 228 */           insertPropertiesStatement.addBatch();
/*     */           continue;
/*     */         } 
/* 230 */         insertPropertiesStatement.execute();
/*     */       } 
/* 234 */       if (this.cnxSupportsBatchUpdates)
/* 235 */         insertPropertiesStatement.executeBatch(); 
/* 238 */       insertPropertiesStatement.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   void updateExceptionStatement(PreparedStatement exceptionStatement, String txt, short i, long eventId) throws SQLException {
/* 248 */     exceptionStatement.setLong(1, eventId);
/* 249 */     exceptionStatement.setShort(2, i);
/* 250 */     exceptionStatement.setString(3, txt);
/* 251 */     if (this.cnxSupportsBatchUpdates) {
/* 252 */       exceptionStatement.addBatch();
/*     */     } else {
/* 254 */       exceptionStatement.execute();
/*     */     } 
/*     */   }
/*     */   
/*     */   short buildExceptionStatement(IThrowableProxy tp, short baseIndex, PreparedStatement insertExceptionStatement, long eventId) throws SQLException {
/* 262 */     StringBuilder buf = new StringBuilder();
/* 263 */     ThrowableProxyUtil.subjoinFirstLine(buf, tp);
/* 264 */     baseIndex = (short)(baseIndex + 1);
/* 264 */     updateExceptionStatement(insertExceptionStatement, buf.toString(), baseIndex, eventId);
/* 267 */     int commonFrames = tp.getCommonFrames();
/* 268 */     StackTraceElementProxy[] stepArray = tp.getStackTraceElementProxyArray();
/* 269 */     for (int i = 0; i < stepArray.length - commonFrames; i++) {
/* 270 */       StringBuilder sb = new StringBuilder();
/* 271 */       sb.append('\t');
/* 272 */       ThrowableProxyUtil.subjoinSTEP(sb, stepArray[i]);
/* 273 */       baseIndex = (short)(baseIndex + 1);
/* 273 */       updateExceptionStatement(insertExceptionStatement, sb.toString(), baseIndex, eventId);
/*     */     } 
/* 277 */     if (commonFrames > 0) {
/* 278 */       StringBuilder sb = new StringBuilder();
/* 279 */       sb.append('\t').append("... ").append(commonFrames).append(" common frames omitted");
/* 281 */       baseIndex = (short)(baseIndex + 1);
/* 281 */       updateExceptionStatement(insertExceptionStatement, sb.toString(), baseIndex, eventId);
/*     */     } 
/* 285 */     return baseIndex;
/*     */   }
/*     */   
/*     */   protected void insertThrowable(IThrowableProxy tp, Connection connection, long eventId) throws SQLException {
/* 291 */     PreparedStatement exceptionStatement = connection.prepareStatement(this.insertExceptionSQL);
/* 294 */     short baseIndex = 0;
/* 295 */     while (tp != null) {
/* 296 */       baseIndex = buildExceptionStatement(tp, baseIndex, exceptionStatement, eventId);
/* 298 */       tp = tp.getCause();
/*     */     } 
/* 301 */     if (this.cnxSupportsBatchUpdates)
/* 302 */       exceptionStatement.executeBatch(); 
/* 304 */     exceptionStatement.close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\db\DBAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */