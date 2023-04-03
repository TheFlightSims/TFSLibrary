/*     */ package org.postgresql.jdbc3;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.sql.Array;
/*     */ import java.sql.Blob;
/*     */ import java.sql.Clob;
/*     */ import java.sql.Ref;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Vector;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.core.BaseStatement;
/*     */ import org.postgresql.core.Field;
/*     */ import org.postgresql.core.Query;
/*     */ import org.postgresql.core.ResultCursor;
/*     */ import org.postgresql.jdbc2.AbstractJdbc2ResultSet;
/*     */ 
/*     */ public abstract class AbstractJdbc3ResultSet extends AbstractJdbc2ResultSet {
/*     */   public AbstractJdbc3ResultSet(Query originalQuery, BaseStatement statement, Field[] fields, Vector tuples, ResultCursor cursor, int maxRows, int maxFieldSize, int rsType, int rsConcurrency, int rsHoldability) throws SQLException {
/*  28 */     super(originalQuery, statement, fields, tuples, cursor, maxRows, maxFieldSize, rsType, rsConcurrency);
/*     */   }
/*     */   
/*     */   protected Object internalGetObject(int columnIndex, Field field) throws SQLException {
/*  34 */     switch (getSQLType(columnIndex)) {
/*     */       case 16:
/*  36 */         return new Boolean(getBoolean(columnIndex));
/*     */     } 
/*  38 */     return super.internalGetObject(columnIndex, field);
/*     */   }
/*     */   
/*     */   public URL getURL(int columnIndex) throws SQLException {
/*  57 */     throw Driver.notImplemented(getClass(), "getURL(int)");
/*     */   }
/*     */   
/*     */   public URL getURL(String columnName) throws SQLException {
/*  75 */     throw Driver.notImplemented(getClass(), "getURL(String)");
/*     */   }
/*     */   
/*     */   public void updateRef(int columnIndex, Ref x) throws SQLException {
/*  92 */     throw Driver.notImplemented(getClass(), "updateRef(int,Ref)");
/*     */   }
/*     */   
/*     */   public void updateRef(String columnName, Ref x) throws SQLException {
/* 109 */     throw Driver.notImplemented(getClass(), "updateRef(String,Ref)");
/*     */   }
/*     */   
/*     */   public void updateBlob(int columnIndex, Blob x) throws SQLException {
/* 126 */     throw Driver.notImplemented(getClass(), "updateBlob(int,Blob)");
/*     */   }
/*     */   
/*     */   public void updateBlob(String columnName, Blob x) throws SQLException {
/* 143 */     throw Driver.notImplemented(getClass(), "updateBlob(String,Blob)");
/*     */   }
/*     */   
/*     */   public void updateClob(int columnIndex, Clob x) throws SQLException {
/* 160 */     throw Driver.notImplemented(getClass(), "updateClob(int,Clob)");
/*     */   }
/*     */   
/*     */   public void updateClob(String columnName, Clob x) throws SQLException {
/* 177 */     throw Driver.notImplemented(getClass(), "updateClob(String,Clob)");
/*     */   }
/*     */   
/*     */   public void updateArray(int columnIndex, Array x) throws SQLException {
/* 194 */     updateObject(columnIndex, x);
/*     */   }
/*     */   
/*     */   public void updateArray(String columnName, Array x) throws SQLException {
/* 211 */     updateArray(findColumn(columnName), x);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc3\AbstractJdbc3ResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */