/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class RowSetDynaClass extends JDBCDynaClass implements DynaClass, Serializable {
/*  79 */   protected int limit = -1;
/*     */   
/*  86 */   protected List rows = new ArrayList();
/*     */   
/*     */   public RowSetDynaClass(ResultSet resultSet) throws SQLException {
/* 105 */     this(resultSet, true, -1);
/*     */   }
/*     */   
/*     */   public RowSetDynaClass(ResultSet resultSet, int limit) throws SQLException {
/* 127 */     this(resultSet, true, limit);
/*     */   }
/*     */   
/*     */   public RowSetDynaClass(ResultSet resultSet, boolean lowerCase) throws SQLException {
/* 152 */     this(resultSet, lowerCase, -1);
/*     */   }
/*     */   
/*     */   public RowSetDynaClass(ResultSet resultSet, boolean lowerCase, int limit) throws SQLException {
/* 181 */     this(resultSet, lowerCase, limit, false);
/*     */   }
/*     */   
/*     */   public RowSetDynaClass(ResultSet resultSet, boolean lowerCase, boolean useColumnLabel) throws SQLException {
/* 210 */     this(resultSet, lowerCase, -1, useColumnLabel);
/*     */   }
/*     */   
/*     */   public RowSetDynaClass(ResultSet resultSet, boolean lowerCase, int limit, boolean useColumnLabel) throws SQLException {
/* 241 */     if (resultSet == null)
/* 242 */       throw new NullPointerException(); 
/* 244 */     this.lowerCase = lowerCase;
/* 245 */     this.limit = limit;
/* 246 */     setUseColumnLabel(useColumnLabel);
/* 247 */     introspect(resultSet);
/* 248 */     copy(resultSet);
/*     */   }
/*     */   
/*     */   public List getRows() {
/* 267 */     return this.rows;
/*     */   }
/*     */   
/*     */   protected void copy(ResultSet resultSet) throws SQLException {
/* 288 */     int cnt = 0;
/* 289 */     while (resultSet.next() && (this.limit < 0 || cnt++ < this.limit)) {
/* 290 */       DynaBean bean = createDynaBean();
/* 291 */       for (int i = 0; i < this.properties.length; i++) {
/* 292 */         String name = this.properties[i].getName();
/* 293 */         Object value = getObject(resultSet, name);
/* 294 */         bean.set(name, value);
/*     */       } 
/* 296 */       this.rows.add(bean);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected DynaBean createDynaBean() {
/* 310 */     return new BasicDynaBean(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\RowSetDynaClass.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */