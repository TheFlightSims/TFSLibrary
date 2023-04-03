/*     */ package org.postgresql.jdbc4;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.sql.Blob;
/*     */ import java.sql.Clob;
/*     */ import java.sql.NClob;
/*     */ import java.sql.RowId;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLFeatureNotSupportedException;
/*     */ import java.sql.SQLXML;
/*     */ import java.util.logging.Logger;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.jdbc3.AbstractJdbc3Connection;
/*     */ import org.postgresql.jdbc3g.AbstractJdbc3gStatement;
/*     */ 
/*     */ abstract class AbstractJdbc4Statement extends AbstractJdbc3gStatement {
/*     */   private boolean poolable;
/*     */   
/*     */   AbstractJdbc4Statement(Jdbc4Connection c, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/*  26 */     super((AbstractJdbc3Connection)c, rsType, rsConcurrency, rsHoldability);
/*  27 */     this.poolable = true;
/*     */   }
/*     */   
/*     */   public AbstractJdbc4Statement(Jdbc4Connection connection, String sql, boolean isCallable, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/*  32 */     super((AbstractJdbc3Connection)connection, sql, isCallable, rsType, rsConcurrency, rsHoldability);
/*     */   }
/*     */   
/*     */   public boolean isClosed() throws SQLException {
/*  37 */     return this.isClosed;
/*     */   }
/*     */   
/*     */   public void setObject(int parameterIndex, Object x) throws SQLException {
/*  42 */     if (x instanceof SQLXML) {
/*  44 */       setSQLXML(parameterIndex, (SQLXML)x);
/*     */     } else {
/*  46 */       super.setObject(parameterIndex, x);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
/*  52 */     checkClosed();
/*  54 */     if (x == null) {
/*  56 */       setNull(parameterIndex, targetSqlType);
/*     */       return;
/*     */     } 
/*  60 */     switch (targetSqlType) {
/*     */       case 2009:
/*  62 */         if (x instanceof SQLXML) {
/*  63 */           setSQLXML(parameterIndex, (SQLXML)x);
/*     */         } else {
/*  65 */           setSQLXML(parameterIndex, new Jdbc4SQLXML(this.connection, x.toString()));
/*     */         } 
/*     */         return;
/*     */     } 
/*  69 */     super.setObject(parameterIndex, x, targetSqlType, scale);
/*     */   }
/*     */   
/*     */   public void setNull(int parameterIndex, int targetSqlType) throws SQLException {
/*     */     int oid;
/*  75 */     checkClosed();
/*  77 */     switch (targetSqlType) {
/*     */       case 2009:
/*  80 */         oid = 142;
/*     */         break;
/*     */       default:
/*  83 */         super.setNull(parameterIndex, targetSqlType);
/*     */         return;
/*     */     } 
/*  87 */     if (this.adjustIndex)
/*  88 */       parameterIndex--; 
/*  89 */     this.preparedParameters.setNull(parameterIndex, oid);
/*     */   }
/*     */   
/*     */   public void setRowId(int parameterIndex, RowId x) throws SQLException {
/*  94 */     throw Driver.notImplemented(getClass(), "setRowId(int, RowId)");
/*     */   }
/*     */   
/*     */   public void setNString(int parameterIndex, String value) throws SQLException {
/*  99 */     throw Driver.notImplemented(getClass(), "setNString(int, String)");
/*     */   }
/*     */   
/*     */   public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
/* 104 */     throw Driver.notImplemented(getClass(), "setNCharacterStream(int, Reader, long)");
/*     */   }
/*     */   
/*     */   public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
/* 109 */     throw Driver.notImplemented(getClass(), "setNCharacterStream(int, Reader)");
/*     */   }
/*     */   
/*     */   public void setCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
/* 114 */     throw Driver.notImplemented(getClass(), "setCharacterStream(int, Reader, long)");
/*     */   }
/*     */   
/*     */   public void setCharacterStream(int parameterIndex, Reader value) throws SQLException {
/* 119 */     throw Driver.notImplemented(getClass(), "setCharacterStream(int, Reader)");
/*     */   }
/*     */   
/*     */   public void setBinaryStream(int parameterIndex, InputStream value, long length) throws SQLException {
/* 124 */     throw Driver.notImplemented(getClass(), "setBinaryStream(int, InputStream, long)");
/*     */   }
/*     */   
/*     */   public void setBinaryStream(int parameterIndex, InputStream value) throws SQLException {
/* 129 */     throw Driver.notImplemented(getClass(), "setBinaryStream(int, InputStream)");
/*     */   }
/*     */   
/*     */   public void setAsciiStream(int parameterIndex, InputStream value, long length) throws SQLException {
/* 134 */     throw Driver.notImplemented(getClass(), "setAsciiStream(int, InputStream, long)");
/*     */   }
/*     */   
/*     */   public void setAsciiStream(int parameterIndex, InputStream value) throws SQLException {
/* 139 */     throw Driver.notImplemented(getClass(), "setAsciiStream(int, InputStream)");
/*     */   }
/*     */   
/*     */   public void setNClob(int parameterIndex, NClob value) throws SQLException {
/* 144 */     throw Driver.notImplemented(getClass(), "setNClob(int, NClob)");
/*     */   }
/*     */   
/*     */   public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
/* 149 */     throw Driver.notImplemented(getClass(), "setClob(int, Reader, long)");
/*     */   }
/*     */   
/*     */   public void setClob(int parameterIndex, Reader reader) throws SQLException {
/* 154 */     throw Driver.notImplemented(getClass(), "setClob(int, Reader)");
/*     */   }
/*     */   
/*     */   public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
/* 159 */     throw Driver.notImplemented(getClass(), "setBlob(int, InputStream, long)");
/*     */   }
/*     */   
/*     */   public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
/* 164 */     throw Driver.notImplemented(getClass(), "setBlob(int, InputStream)");
/*     */   }
/*     */   
/*     */   public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
/* 169 */     throw Driver.notImplemented(getClass(), "setNClob(int, Reader, long)");
/*     */   }
/*     */   
/*     */   public void setNClob(int parameterIndex, Reader reader) throws SQLException {
/* 174 */     throw Driver.notImplemented(getClass(), "setNClob(int, Reader)");
/*     */   }
/*     */   
/*     */   public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
/* 179 */     checkClosed();
/* 180 */     if (xmlObject == null || xmlObject.getString() == null) {
/* 181 */       setNull(parameterIndex, 2009);
/*     */     } else {
/* 183 */       setString(parameterIndex, xmlObject.getString(), 142);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPoolable(boolean poolable) throws SQLException {
/* 188 */     checkClosed();
/* 189 */     this.poolable = poolable;
/*     */   }
/*     */   
/*     */   public boolean isPoolable() throws SQLException {
/* 194 */     checkClosed();
/* 195 */     return this.poolable;
/*     */   }
/*     */   
/*     */   public RowId getRowId(int parameterIndex) throws SQLException {
/* 200 */     throw Driver.notImplemented(getClass(), "getRowId(int)");
/*     */   }
/*     */   
/*     */   public RowId getRowId(String parameterName) throws SQLException {
/* 205 */     throw Driver.notImplemented(getClass(), "getRowId(String)");
/*     */   }
/*     */   
/*     */   public void setRowId(String parameterName, RowId x) throws SQLException {
/* 210 */     throw Driver.notImplemented(getClass(), "setRowId(String, RowId)");
/*     */   }
/*     */   
/*     */   public void setNString(String parameterName, String value) throws SQLException {
/* 215 */     throw Driver.notImplemented(getClass(), "setNString(String, String)");
/*     */   }
/*     */   
/*     */   public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
/* 220 */     throw Driver.notImplemented(getClass(), "setNCharacterStream(String, Reader, long)");
/*     */   }
/*     */   
/*     */   public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
/* 225 */     throw Driver.notImplemented(getClass(), "setNCharacterStream(String, Reader)");
/*     */   }
/*     */   
/*     */   public void setCharacterStream(String parameterName, Reader value, long length) throws SQLException {
/* 230 */     throw Driver.notImplemented(getClass(), "setCharacterStream(String, Reader, long)");
/*     */   }
/*     */   
/*     */   public void setCharacterStream(String parameterName, Reader value) throws SQLException {
/* 235 */     throw Driver.notImplemented(getClass(), "setCharacterStream(String, Reader)");
/*     */   }
/*     */   
/*     */   public void setBinaryStream(String parameterName, InputStream value, long length) throws SQLException {
/* 240 */     throw Driver.notImplemented(getClass(), "setBinaryStream(String, InputStream, long)");
/*     */   }
/*     */   
/*     */   public void setBinaryStream(String parameterName, InputStream value) throws SQLException {
/* 245 */     throw Driver.notImplemented(getClass(), "setBinaryStream(String, InputStream)");
/*     */   }
/*     */   
/*     */   public void setAsciiStream(String parameterName, InputStream value, long length) throws SQLException {
/* 250 */     throw Driver.notImplemented(getClass(), "setAsciiStream(String, InputStream, long)");
/*     */   }
/*     */   
/*     */   public void setAsciiStream(String parameterName, InputStream value) throws SQLException {
/* 255 */     throw Driver.notImplemented(getClass(), "setAsciiStream(String, InputStream)");
/*     */   }
/*     */   
/*     */   public void setNClob(String parameterName, NClob value) throws SQLException {
/* 260 */     throw Driver.notImplemented(getClass(), "setNClob(String, NClob)");
/*     */   }
/*     */   
/*     */   public void setClob(String parameterName, Reader reader, long length) throws SQLException {
/* 265 */     throw Driver.notImplemented(getClass(), "setClob(String, Reader, long)");
/*     */   }
/*     */   
/*     */   public void setClob(String parameterName, Reader reader) throws SQLException {
/* 270 */     throw Driver.notImplemented(getClass(), "setClob(String, Reader)");
/*     */   }
/*     */   
/*     */   public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
/* 275 */     throw Driver.notImplemented(getClass(), "setBlob(String, InputStream, long)");
/*     */   }
/*     */   
/*     */   public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
/* 280 */     throw Driver.notImplemented(getClass(), "setBlob(String, InputStream)");
/*     */   }
/*     */   
/*     */   public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
/* 285 */     throw Driver.notImplemented(getClass(), "setNClob(String, Reader, long)");
/*     */   }
/*     */   
/*     */   public void setNClob(String parameterName, Reader reader) throws SQLException {
/* 290 */     throw Driver.notImplemented(getClass(), "setNClob(String, Reader)");
/*     */   }
/*     */   
/*     */   public NClob getNClob(int parameterIndex) throws SQLException {
/* 295 */     throw Driver.notImplemented(getClass(), "getNClob(int)");
/*     */   }
/*     */   
/*     */   public NClob getNClob(String parameterName) throws SQLException {
/* 300 */     throw Driver.notImplemented(getClass(), "getNClob(String)");
/*     */   }
/*     */   
/*     */   public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
/* 305 */     throw Driver.notImplemented(getClass(), "setSQLXML(String, SQLXML)");
/*     */   }
/*     */   
/*     */   public SQLXML getSQLXML(int parameterIndex) throws SQLException {
/* 310 */     checkClosed();
/* 311 */     checkIndex(parameterIndex, 2009, "SQLXML");
/* 312 */     return (SQLXML)this.callResult[parameterIndex - 1];
/*     */   }
/*     */   
/*     */   public SQLXML getSQLXML(String parameterIndex) throws SQLException {
/* 317 */     throw Driver.notImplemented(getClass(), "getSQLXML(String)");
/*     */   }
/*     */   
/*     */   public String getNString(int parameterIndex) throws SQLException {
/* 322 */     throw Driver.notImplemented(getClass(), "getNString(int)");
/*     */   }
/*     */   
/*     */   public String getNString(String parameterName) throws SQLException {
/* 327 */     throw Driver.notImplemented(getClass(), "getNString(String)");
/*     */   }
/*     */   
/*     */   public Reader getNCharacterStream(int parameterIndex) throws SQLException {
/* 332 */     throw Driver.notImplemented(getClass(), "getNCharacterStream(int)");
/*     */   }
/*     */   
/*     */   public Reader getNCharacterStream(String parameterName) throws SQLException {
/* 337 */     throw Driver.notImplemented(getClass(), "getNCharacterStream(String)");
/*     */   }
/*     */   
/*     */   public Reader getCharacterStream(int parameterIndex) throws SQLException {
/* 342 */     throw Driver.notImplemented(getClass(), "getCharacterStream(int)");
/*     */   }
/*     */   
/*     */   public Reader getCharacterStream(String parameterName) throws SQLException {
/* 347 */     throw Driver.notImplemented(getClass(), "getCharacterStream(String)");
/*     */   }
/*     */   
/*     */   public void setBlob(String parameterName, Blob x) throws SQLException {
/* 352 */     throw Driver.notImplemented(getClass(), "setBlob(String, Blob)");
/*     */   }
/*     */   
/*     */   public void setClob(String parameterName, Clob x) throws SQLException {
/* 357 */     throw Driver.notImplemented(getClass(), "setClob(String, Clob)");
/*     */   }
/*     */   
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 362 */     throw Driver.notImplemented(getClass(), "isWrapperFor(Class<?>)");
/*     */   }
/*     */   
/*     */   public <T> T unwrap(Class<T> iface) throws SQLException {
/* 367 */     throw Driver.notImplemented(getClass(), "unwrap(Class<T>)");
/*     */   }
/*     */   
/*     */   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
/* 372 */     throw Driver.notImplemented(getClass(), "getParentLogger()");
/*     */   }
/*     */   
/*     */   public void closeOnCompletion() throws SQLException {
/* 377 */     throw Driver.notImplemented(getClass(), "closeOnCompletion()");
/*     */   }
/*     */   
/*     */   public boolean isCloseOnCompletion() throws SQLException {
/* 382 */     throw Driver.notImplemented(getClass(), "isCloseOnCompletion()");
/*     */   }
/*     */   
/*     */   public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
/* 387 */     throw Driver.notImplemented(getClass(), "getObject(int, Class<T>)");
/*     */   }
/*     */   
/*     */   public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
/* 392 */     throw Driver.notImplemented(getClass(), "getObject(String, Class<T>)");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\AbstractJdbc4Statement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */