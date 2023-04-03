/*     */ package org.jfree.util;
/*     */ 
/*     */ public class BooleanList extends AbstractObjectList {
/*     */   private static final long serialVersionUID = -8543170333219422042L;
/*     */   
/*     */   public Boolean getBoolean(int index) {
/*  73 */     return (Boolean)get(index);
/*     */   }
/*     */   
/*     */   public void setBoolean(int index, Boolean b) {
/*  84 */     set(index, b);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  96 */     if (o instanceof BooleanList)
/*  97 */       return super.equals(o); 
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 108 */     return super.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\BooleanList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */