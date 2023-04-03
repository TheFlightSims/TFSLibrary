/*     */ package org.postgresql.jdbc4;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.sql.NClob;
/*     */ import java.sql.RowId;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLXML;
/*     */ import java.util.Vector;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.core.BaseStatement;
/*     */ import org.postgresql.core.Field;
/*     */ import org.postgresql.core.Query;
/*     */ import org.postgresql.core.ResultCursor;
/*     */ import org.postgresql.jdbc3g.AbstractJdbc3gResultSet;
/*     */ 
/*     */ abstract class AbstractJdbc4ResultSet extends AbstractJdbc3gResultSet {
/*     */   AbstractJdbc4ResultSet(Query originalQuery, BaseStatement statement, Field[] fields, Vector tuples, ResultCursor cursor, int maxRows, int maxFieldSize, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/*  24 */     super(originalQuery, statement, fields, tuples, cursor, maxRows, maxFieldSize, rsType, rsConcurrency, rsHoldability);
/*     */   }
/*     */   
/*     */   public RowId getRowId(int columnIndex) throws SQLException {
/*  29 */     throw Driver.notImplemented(getClass(), "getRowId(int)");
/*     */   }
/*     */   
/*     */   public RowId getRowId(String columnName) throws SQLException {
/*  34 */     return getRowId(findColumn(columnName));
/*     */   }
/*     */   
/*     */   public void updateRowId(int columnIndex, RowId x) throws SQLException {
/*  39 */     throw Driver.notImplemented(getClass(), "updateRowId(int, RowId)");
/*     */   }
/*     */   
/*     */   public void updateRowId(String columnName, RowId x) throws SQLException {
/*  44 */     updateRowId(findColumn(columnName), x);
/*     */   }
/*     */   
/*     */   public int getHoldability() throws SQLException {
/*  49 */     throw Driver.notImplemented(getClass(), "getHoldability()");
/*     */   }
/*     */   
/*     */   public boolean isClosed() throws SQLException {
/*  54 */     return (this.rows == null);
/*     */   }
/*     */   
/*     */   public void updateNString(int columnIndex, String nString) throws SQLException {
/*  59 */     throw Driver.notImplemented(getClass(), "updateNString(int, String)");
/*     */   }
/*     */   
/*     */   public void updateNString(String columnName, String nString) throws SQLException {
/*  64 */     updateNString(findColumn(columnName), nString);
/*     */   }
/*     */   
/*     */   public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
/*  69 */     throw Driver.notImplemented(getClass(), "updateNClob(int, NClob)");
/*     */   }
/*     */   
/*     */   public void updateNClob(String columnName, NClob nClob) throws SQLException {
/*  74 */     updateNClob(findColumn(columnName), nClob);
/*     */   }
/*     */   
/*     */   public void updateNClob(int columnIndex, Reader reader) throws SQLException {
/*  79 */     throw Driver.notImplemented(getClass(), "updateNClob(int, Reader)");
/*     */   }
/*     */   
/*     */   public void updateNClob(String columnName, Reader reader) throws SQLException {
/*  84 */     updateNClob(findColumn(columnName), reader);
/*     */   }
/*     */   
/*     */   public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
/*  89 */     throw Driver.notImplemented(getClass(), "updateNClob(int, Reader, long)");
/*     */   }
/*     */   
/*     */   public void updateNClob(String columnName, Reader reader, long length) throws SQLException {
/*  94 */     updateNClob(findColumn(columnName), reader, length);
/*     */   }
/*     */   
/*     */   public NClob getNClob(int columnIndex) throws SQLException {
/*  99 */     throw Driver.notImplemented(getClass(), "getNClob(int)");
/*     */   }
/*     */   
/*     */   public NClob getNClob(String columnName) throws SQLException {
/* 104 */     return getNClob(findColumn(columnName));
/*     */   }
/*     */   
/*     */   public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
/* 109 */     throw Driver.notImplemented(getClass(), "updateBlob(int, InputStream, long)");
/*     */   }
/*     */   
/*     */   public void updateBlob(String columnName, InputStream inputStream, long length) throws SQLException {
/* 114 */     updateBlob(findColumn(columnName), inputStream, length);
/*     */   }
/*     */   
/*     */   public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
/* 119 */     throw Driver.notImplemented(getClass(), "updateBlob(int, InputStream)");
/*     */   }
/*     */   
/*     */   public void updateBlob(String columnName, InputStream inputStream) throws SQLException {
/* 124 */     updateBlob(findColumn(columnName), inputStream);
/*     */   }
/*     */   
/*     */   public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
/* 129 */     throw Driver.notImplemented(getClass(), "updateClob(int, Reader, long)");
/*     */   }
/*     */   
/*     */   public void updateClob(String columnName, Reader reader, long length) throws SQLException {
/* 134 */     updateClob(findColumn(columnName), reader, length);
/*     */   }
/*     */   
/*     */   public void updateClob(int columnIndex, Reader reader) throws SQLException {
/* 139 */     throw Driver.notImplemented(getClass(), "updateClob(int, Reader)");
/*     */   }
/*     */   
/*     */   public void updateClob(String columnName, Reader reader) throws SQLException {
/* 144 */     updateClob(findColumn(columnName), reader);
/*     */   }
/*     */   
/*     */   public SQLXML getSQLXML(int columnIndex) throws SQLException {
/* 149 */     String data = getString(columnIndex);
/* 150 */     if (data == null)
/* 151 */       return null; 
/* 153 */     return new Jdbc4SQLXML(this.connection, data);
/*     */   }
/*     */   
/*     */   public SQLXML getSQLXML(String columnName) throws SQLException {
/* 158 */     return getSQLXML(findColumn(columnName));
/*     */   }
/*     */   
/*     */   public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
/* 163 */     updateValue(columnIndex, xmlObject);
/*     */   }
/*     */   
/*     */   public void updateSQLXML(String columnName, SQLXML xmlObject) throws SQLException {
/* 168 */     updateSQLXML(findColumn(columnName), xmlObject);
/*     */   }
/*     */   
/*     */   public String getNString(int columnIndex) throws SQLException {
/* 173 */     throw Driver.notImplemented(getClass(), "getNString(int)");
/*     */   }
/*     */   
/*     */   public String getNString(String columnName) throws SQLException {
/* 178 */     return getNString(findColumn(columnName));
/*     */   }
/*     */   
/*     */   public Reader getNCharacterStream(int columnIndex) throws SQLException {
/* 183 */     throw Driver.notImplemented(getClass(), "getNCharacterStream(int)");
/*     */   }
/*     */   
/*     */   public Reader getNCharacterStream(String columnName) throws SQLException {
/* 188 */     return getNCharacterStream(findColumn(columnName));
/*     */   }
/*     */   
/*     */   public void updateNCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
/* 193 */     throw Driver.notImplemented(getClass(), "updateNCharacterStream(int, Reader, int)");
/*     */   }
/*     */   
/*     */   public void updateNCharacterStream(String columnName, Reader x, int length) throws SQLException {
/* 198 */     updateNCharacterStream(findColumn(columnName), x, length);
/*     */   }
/*     */   
/*     */   public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
/* 203 */     throw Driver.notImplemented(getClass(), "updateNCharacterStream(int, Reader)");
/*     */   }
/*     */   
/*     */   public void updateNCharacterStream(String columnName, Reader x) throws SQLException {
/* 208 */     updateNCharacterStream(findColumn(columnName), x);
/*     */   }
/*     */   
/*     */   public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
/* 213 */     throw Driver.notImplemented(getClass(), "updateNCharacterStream(int, Reader, long)");
/*     */   }
/*     */   
/*     */   public void updateNCharacterStream(String columnName, Reader x, long length) throws SQLException {
/* 218 */     updateNCharacterStream(findColumn(columnName), x, length);
/*     */   }
/*     */   
/*     */   public void updateCharacterStream(int columnIndex, Reader reader, long length) throws SQLException {
/* 223 */     throw Driver.notImplemented(getClass(), "updateCharaceterStream(int, Reader, long)");
/*     */   }
/*     */   
/*     */   public void updateCharacterStream(String columnName, Reader reader, long length) throws SQLException {
/* 228 */     updateCharacterStream(findColumn(columnName), reader, length);
/*     */   }
/*     */   
/*     */   public void updateCharacterStream(int columnIndex, Reader reader) throws SQLException {
/* 233 */     throw Driver.notImplemented(getClass(), "updateCharaceterStream(int, Reader)");
/*     */   }
/*     */   
/*     */   public void updateCharacterStream(String columnName, Reader reader) throws SQLException {
/* 238 */     updateCharacterStream(findColumn(columnName), reader);
/*     */   }
/*     */   
/*     */   public void updateBinaryStream(int columnIndex, InputStream inputStream, long length) throws SQLException {
/* 243 */     throw Driver.notImplemented(getClass(), "updateBinaryStream(int, InputStream, long)");
/*     */   }
/*     */   
/*     */   public void updateBinaryStream(String columnName, InputStream inputStream, long length) throws SQLException {
/* 248 */     updateBinaryStream(findColumn(columnName), inputStream, length);
/*     */   }
/*     */   
/*     */   public void updateBinaryStream(int columnIndex, InputStream inputStream) throws SQLException {
/* 253 */     throw Driver.notImplemented(getClass(), "updateBinaryStream(int, InputStream)");
/*     */   }
/*     */   
/*     */   public void updateBinaryStream(String columnName, InputStream inputStream) throws SQLException {
/* 258 */     updateBinaryStream(findColumn(columnName), inputStream);
/*     */   }
/*     */   
/*     */   public void updateAsciiStream(int columnIndex, InputStream inputStream, long length) throws SQLException {
/* 263 */     throw Driver.notImplemented(getClass(), "updateAsciiStream(int, InputStream, long)");
/*     */   }
/*     */   
/*     */   public void updateAsciiStream(String columnName, InputStream inputStream, long length) throws SQLException {
/* 268 */     updateAsciiStream(findColumn(columnName), inputStream, length);
/*     */   }
/*     */   
/*     */   public void updateAsciiStream(int columnIndex, InputStream inputStream) throws SQLException {
/* 273 */     throw Driver.notImplemented(getClass(), "updateAsciiStream(int, InputStream)");
/*     */   }
/*     */   
/*     */   public void updateAsciiStream(String columnName, InputStream inputStream) throws SQLException {
/* 278 */     updateAsciiStream(findColumn(columnName), inputStream);
/*     */   }
/*     */   
/*     */   public boolean isWrapperFor(Class<?> iface) throws SQLException {
/* 283 */     throw Driver.notImplemented(getClass(), "isWrapperFor(Class<?>)");
/*     */   }
/*     */   
/*     */   public <T> T unwrap(Class<T> iface) throws SQLException {
/* 288 */     throw Driver.notImplemented(getClass(), "unwrap(Class<T>)");
/*     */   }
/*     */   
/*     */   protected Object internalGetObject(int columnIndex, Field field) throws SQLException {
/* 293 */     switch (getSQLType(columnIndex)) {
/*     */       case 2009:
/* 296 */         return getSQLXML(columnIndex);
/*     */     } 
/* 298 */     return super.internalGetObject(columnIndex, field);
/*     */   }
/*     */   
/*     */   public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
/* 303 */     throw Driver.notImplemented(getClass(), "getObject(int, Class<T>)");
/*     */   }
/*     */   
/*     */   public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
/* 308 */     return getObject(findColumn(columnLabel), type);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\AbstractJdbc4ResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */