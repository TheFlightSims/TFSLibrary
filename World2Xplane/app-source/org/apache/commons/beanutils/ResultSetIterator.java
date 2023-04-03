/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ResultSetIterator implements DynaBean, Iterator {
/*     */   protected boolean current;
/*     */   
/*     */   protected ResultSetDynaClass dynaClass;
/*     */   
/*     */   protected boolean eof;
/*     */   
/*     */   ResultSetIterator(ResultSetDynaClass dynaClass) {
/*  65 */     this.current = false;
/*  71 */     this.dynaClass = null;
/*  78 */     this.eof = false;
/*     */     this.dynaClass = dynaClass;
/*     */   }
/*     */   
/*     */   public boolean contains(String name, String key) {
/*  98 */     throw new UnsupportedOperationException("FIXME - mapped properties not currently supported");
/*     */   }
/*     */   
/*     */   public Object get(String name) {
/* 115 */     if (this.dynaClass.getDynaProperty(name) == null)
/* 116 */       throw new IllegalArgumentException(name); 
/*     */     try {
/* 119 */       return this.dynaClass.getObjectFromResultSet(name);
/* 120 */     } catch (SQLException e) {
/* 121 */       throw new RuntimeException("get(" + name + "): SQLException: " + e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get(String name, int index) {
/* 146 */     throw new UnsupportedOperationException("FIXME - indexed properties not currently supported");
/*     */   }
/*     */   
/*     */   public Object get(String name, String key) {
/* 167 */     throw new UnsupportedOperationException("FIXME - mapped properties not currently supported");
/*     */   }
/*     */   
/*     */   public DynaClass getDynaClass() {
/* 181 */     return this.dynaClass;
/*     */   }
/*     */   
/*     */   public void remove(String name, String key) {
/* 199 */     throw new UnsupportedOperationException("FIXME - mapped operations not currently supported");
/*     */   }
/*     */   
/*     */   public void set(String name, Object value) {
/* 220 */     if (this.dynaClass.getDynaProperty(name) == null)
/* 221 */       throw new IllegalArgumentException(name); 
/*     */     try {
/* 224 */       this.dynaClass.getResultSet().updateObject(name, value);
/* 225 */     } catch (SQLException e) {
/* 226 */       throw new RuntimeException("set(" + name + "): SQLException: " + e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(String name, int index, Object value) {
/* 251 */     throw new UnsupportedOperationException("FIXME - indexed properties not currently supported");
/*     */   }
/*     */   
/*     */   public void set(String name, String key, Object value) {
/* 273 */     throw new UnsupportedOperationException("FIXME - mapped properties not currently supported");
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*     */     try {
/* 291 */       advance();
/* 292 */       return !this.eof;
/* 293 */     } catch (SQLException e) {
/* 294 */       throw new RuntimeException("hasNext():  SQLException:  " + e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object next() {
/*     */     try {
/* 308 */       advance();
/* 309 */       if (this.eof)
/* 310 */         throw new NoSuchElementException(); 
/* 312 */       this.current = false;
/* 313 */       return this;
/* 314 */     } catch (SQLException e) {
/* 315 */       throw new RuntimeException("next():  SQLException:  " + e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove() {
/* 327 */     throw new UnsupportedOperationException("remove()");
/*     */   }
/*     */   
/*     */   protected void advance() throws SQLException {
/* 343 */     if (!this.current && !this.eof)
/* 344 */       if (this.dynaClass.getResultSet().next()) {
/* 345 */         this.current = true;
/* 346 */         this.eof = false;
/*     */       } else {
/* 348 */         this.current = false;
/* 349 */         this.eof = true;
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\ResultSetIterator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */