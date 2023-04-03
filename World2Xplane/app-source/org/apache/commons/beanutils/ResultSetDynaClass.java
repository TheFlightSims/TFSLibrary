/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class ResultSetDynaClass extends JDBCDynaClass implements DynaClass {
/*     */   public ResultSetDynaClass(ResultSet resultSet) throws SQLException {
/* 104 */     this(resultSet, true);
/*     */   }
/*     */   
/*     */   public ResultSetDynaClass(ResultSet resultSet, boolean lowerCase) throws SQLException {
/* 133 */     this(resultSet, lowerCase, false);
/*     */   }
/*     */   
/*     */   public ResultSetDynaClass(ResultSet resultSet, boolean lowerCase, boolean useColumnLabel) throws SQLException {
/* 164 */     if (resultSet == null)
/* 165 */       throw new NullPointerException(); 
/* 167 */     this.resultSet = resultSet;
/* 168 */     this.lowerCase = lowerCase;
/* 169 */     setUseColumnLabel(useColumnLabel);
/* 170 */     introspect(resultSet);
/*     */   }
/*     */   
/* 181 */   protected ResultSet resultSet = null;
/*     */   
/*     */   public Iterator iterator() {
/* 196 */     return new ResultSetIterator(this);
/*     */   }
/*     */   
/*     */   public Object getObjectFromResultSet(String name) throws SQLException {
/* 211 */     return getObject(getResultSet(), name);
/*     */   }
/*     */   
/*     */   ResultSet getResultSet() {
/* 222 */     return this.resultSet;
/*     */   }
/*     */   
/*     */   protected Class loadClass(String className) throws SQLException {
/*     */     try {
/* 242 */       return getClass().getClassLoader().loadClass(className);
/* 244 */     } catch (Exception e) {
/* 245 */       throw new SQLException("Cannot load column class '" + className + "': " + e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\ResultSetDynaClass.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */