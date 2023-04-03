/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ import java.sql.Array;
/*    */ import java.sql.Blob;
/*    */ import java.sql.Clob;
/*    */ import java.sql.NClob;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.ResultSetMetaData;
/*    */ import java.sql.RowId;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.SQLXML;
/*    */ import java.util.Map;
/*    */ import java.util.Vector;
/*    */ import org.postgresql.core.BaseStatement;
/*    */ import org.postgresql.core.Field;
/*    */ import org.postgresql.core.Query;
/*    */ import org.postgresql.core.ResultCursor;
/*    */ 
/*    */ public class Jdbc4ResultSet extends AbstractJdbc4ResultSet implements ResultSet {
/*    */   Jdbc4ResultSet(Query originalQuery, BaseStatement statement, Field[] fields, Vector tuples, ResultCursor cursor, int maxRows, int maxFieldSize, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/* 28 */     super(originalQuery, statement, fields, tuples, cursor, maxRows, maxFieldSize, rsType, rsConcurrency, rsHoldability);
/*    */   }
/*    */   
/*    */   public ResultSetMetaData getMetaData() throws SQLException {
/* 33 */     checkClosed();
/* 34 */     return new Jdbc4ResultSetMetaData(this.connection, this.fields);
/*    */   }
/*    */   
/*    */   public Clob getClob(int i) throws SQLException {
/* 39 */     checkResultSet(i);
/* 40 */     if (this.wasNullFlag)
/* 41 */       return null; 
/* 43 */     return new Jdbc4Clob(this.connection, getLong(i));
/*    */   }
/*    */   
/*    */   public Blob getBlob(int i) throws SQLException {
/* 48 */     checkResultSet(i);
/* 49 */     if (this.wasNullFlag)
/* 50 */       return null; 
/* 52 */     return new Jdbc4Blob(this.connection, getLong(i));
/*    */   }
/*    */   
/*    */   public Array createArray(int i) throws SQLException {
/* 57 */     checkResultSet(i);
/* 58 */     int oid = this.fields[i - 1].getOID();
/* 59 */     String value = getFixedString(i);
/* 60 */     return new Jdbc4Array(this.connection, oid, value);
/*    */   }
/*    */   
/*    */   public Object getObject(String s, Map<String, Class<?>> map) throws SQLException {
/* 65 */     return getObjectImpl(s, map);
/*    */   }
/*    */   
/*    */   public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
/* 70 */     return getObjectImpl(i, map);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4ResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */