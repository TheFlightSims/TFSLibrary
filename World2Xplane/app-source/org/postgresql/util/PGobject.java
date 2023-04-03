/*     */ package org.postgresql.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ public class PGobject implements Serializable, Cloneable {
/*     */   protected String type;
/*     */   
/*     */   protected String value;
/*     */   
/*     */   public final void setType(String type) {
/*  41 */     this.type = type;
/*     */   }
/*     */   
/*     */   public void setValue(String value) throws SQLException {
/*  52 */     this.value = value;
/*     */   }
/*     */   
/*     */   public final String getType() {
/*  61 */     return this.type;
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  71 */     return this.value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  81 */     if (obj instanceof PGobject)
/*  82 */       return ((PGobject)obj).getValue().equals(getValue()); 
/*  83 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  91 */     return super.clone();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 100 */     return getValue();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\PGobject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */