/*     */ package org.postgresql.jdbc4;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.sql.Array;
/*     */ import java.sql.Blob;
/*     */ import java.sql.ClientInfoStatus;
/*     */ import java.sql.Clob;
/*     */ import java.sql.NClob;
/*     */ import java.sql.SQLClientInfoException;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLFeatureNotSupportedException;
/*     */ import java.sql.SQLXML;
/*     */ import java.sql.Struct;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.logging.Logger;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.core.TypeInfo;
/*     */ import org.postgresql.core.Utils;
/*     */ import org.postgresql.jdbc2.AbstractJdbc2Array;
/*     */ import org.postgresql.jdbc3g.AbstractJdbc3gConnection;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ abstract class AbstractJdbc4Connection extends AbstractJdbc3gConnection {
/*     */   private final Properties _clientInfo;
/*     */   
/*     */   public AbstractJdbc4Connection(String host, int port, String user, String database, Properties info, String url) throws SQLException {
/*  32 */     super(host, port, user, database, info, url);
/*  34 */     TypeInfo types = getTypeInfo();
/*  35 */     if (haveMinimumServerVersion("8.3"))
/*  36 */       types.addCoreType("xml", Integer.valueOf(142), Integer.valueOf(2009), "java.sql.SQLXML", Integer.valueOf(143)); 
/*  39 */     this._clientInfo = new Properties();
/*  40 */     if (haveMinimumServerVersion("9.0")) {
/*  41 */       String appName = info.getProperty("ApplicationName");
/*  42 */       if (appName == null)
/*  43 */         appName = ""; 
/*  45 */       this._clientInfo.put("ApplicationName", appName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Clob createClob() throws SQLException {
/*  51 */     checkClosed();
/*  52 */     throw Driver.notImplemented(getClass(), "createClob()");
/*     */   }
/*     */   
/*     */   public Blob createBlob() throws SQLException {
/*  57 */     checkClosed();
/*  58 */     throw Driver.notImplemented(getClass(), "createBlob()");
/*     */   }
/*     */   
/*     */   public NClob createNClob() throws SQLException {
/*  63 */     checkClosed();
/*  64 */     throw Driver.notImplemented(getClass(), "createNClob()");
/*     */   }
/*     */   
/*     */   public SQLXML createSQLXML() throws SQLException {
/*  69 */     checkClosed();
/*  70 */     return new Jdbc4SQLXML((BaseConnection)this);
/*     */   }
/*     */   
/*     */   public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
/*  75 */     checkClosed();
/*  76 */     throw Driver.notImplemented(getClass(), "createStruct(String, Object[])");
/*     */   }
/*     */   
/*     */   public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
/*  81 */     checkClosed();
/*  82 */     int oid = getTypeInfo().getPGArrayType(typeName);
/*  83 */     if (oid == 0)
/*  84 */       throw new PSQLException(GT.tr("Unable to find server array type for provided name {0}.", typeName), PSQLState.INVALID_NAME); 
/*  86 */     StringBuffer sb = new StringBuffer();
/*  87 */     appendArray(sb, elements);
/*  91 */     return new Jdbc4Array((BaseConnection)this, oid, sb.toString());
/*     */   }
/*     */   
/*     */   private static void appendArray(StringBuffer sb, Object elements) {
/*  96 */     sb.append('{');
/*  98 */     int nElements = Array.getLength(elements);
/*  99 */     for (int i = 0; i < nElements; i++) {
/* 100 */       if (i > 0)
/* 101 */         sb.append(','); 
/* 104 */       Object o = Array.get(elements, i);
/* 105 */       if (o == null) {
/* 106 */         sb.append("NULL");
/* 107 */       } else if (o.getClass().isArray()) {
/* 108 */         appendArray(sb, o);
/*     */       } else {
/* 110 */         String s = o.toString();
/* 111 */         AbstractJdbc2Array.escapeArrayElement(sb, s);
/*     */       } 
/*     */     } 
/* 114 */     sb.append('}');
/*     */   }
/*     */   
/*     */   public boolean isValid(int timeout) throws SQLException {
/* 119 */     checkClosed();
/* 120 */     throw Driver.notImplemented(getClass(), "isValid(int)");
/*     */   }
/*     */   
/*     */   public void setClientInfo(String name, String value) throws SQLClientInfoException {
/* 125 */     if (haveMinimumServerVersion("9.0") && "ApplicationName".equals(name)) {
/* 126 */       if (value == null)
/* 127 */         value = ""; 
/*     */       try {
/* 130 */         StringBuffer sql = new StringBuffer("SET application_name = '");
/* 131 */         Utils.appendEscapedLiteral(sql, value, getStandardConformingStrings());
/* 132 */         sql.append("'");
/* 133 */         execSQLUpdate(sql.toString());
/* 134 */       } catch (SQLException sqle) {
/* 135 */         Map<String, ClientInfoStatus> map = new HashMap<String, ClientInfoStatus>();
/* 136 */         map.put(name, ClientInfoStatus.REASON_UNKNOWN);
/* 137 */         throw new SQLClientInfoException(GT.tr("Failed to set ClientInfo property: {0}", "ApplicationName"), sqle.getSQLState(), map, sqle);
/*     */       } 
/* 140 */       this._clientInfo.put(name, value);
/*     */       return;
/*     */     } 
/* 144 */     Map<String, ClientInfoStatus> failures = new HashMap<String, ClientInfoStatus>();
/* 145 */     failures.put(name, ClientInfoStatus.REASON_UNKNOWN_PROPERTY);
/* 146 */     throw new SQLClientInfoException(GT.tr("ClientInfo property not supported."), PSQLState.NOT_IMPLEMENTED.getState(), failures);
/*     */   }
/*     */   
/*     */   public void setClientInfo(Properties properties) throws SQLClientInfoException {
/* 151 */     if (properties == null || properties.size() == 0)
/*     */       return; 
/* 154 */     Map<String, ClientInfoStatus> failures = new HashMap<String, ClientInfoStatus>();
/* 156 */     Iterator<String> i = properties.stringPropertyNames().iterator();
/* 157 */     while (i.hasNext()) {
/* 158 */       String name = i.next();
/* 159 */       if (haveMinimumServerVersion("9.0") && "ApplicationName".equals(name)) {
/* 160 */         String value = properties.getProperty(name);
/* 161 */         setClientInfo(name, value);
/*     */         continue;
/*     */       } 
/* 163 */       failures.put(i.next(), ClientInfoStatus.REASON_UNKNOWN_PROPERTY);
/*     */     } 
/* 167 */     if (!failures.isEmpty())
/* 168 */       throw new SQLClientInfoException(GT.tr("ClientInfo property not supported."), PSQLState.NOT_IMPLEMENTED.getState(), failures); 
/*     */   }
/*     */   
/*     */   public String getClientInfo(String name) throws SQLException {
/* 173 */     checkClosed();
/* 174 */     return this._clientInfo.getProperty(name);
/*     */   }
/*     */   
/*     */   public Properties getClientInfo() throws SQLException {
/* 179 */     checkClosed();
/* 180 */     return this._clientInfo;
/*     */   }
/*     */   
/*     */   public <T> T createQueryObject(Class<T> ifc) throws SQLException {
/* 185 */     checkClosed();
/* 186 */     throw Driver.notImplemented(getClass(), "createQueryObject(Class<T>)");
/*     */   }
/*     */   
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 191 */     checkClosed();
/* 192 */     throw Driver.notImplemented(getClass(), "isWrapperFor(Class<?>)");
/*     */   }
/*     */   
/*     */   public <T> T unwrap(Class<T> iface) throws SQLException {
/* 197 */     checkClosed();
/* 198 */     throw Driver.notImplemented(getClass(), "unwrap(Class<T>)");
/*     */   }
/*     */   
/*     */   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
/* 203 */     throw Driver.notImplemented(getClass(), "getParentLogger()");
/*     */   }
/*     */   
/*     */   public void setSchema(String schema) throws SQLException {
/* 208 */     throw Driver.notImplemented(getClass(), "setSchema(String)");
/*     */   }
/*     */   
/*     */   public String getSchema() throws SQLException {
/* 213 */     throw Driver.notImplemented(getClass(), "getSchema()");
/*     */   }
/*     */   
/*     */   public void abort(Executor executor) throws SQLException {
/* 218 */     throw Driver.notImplemented(getClass(), "abort(Executor)");
/*     */   }
/*     */   
/*     */   public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
/* 222 */     throw Driver.notImplemented(getClass(), "setNetworkTimeout(Executor, int)");
/*     */   }
/*     */   
/*     */   public int getNetworkTimeout() throws SQLException {
/* 226 */     throw Driver.notImplemented(getClass(), "getNetworkTimeout()");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\AbstractJdbc4Connection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */