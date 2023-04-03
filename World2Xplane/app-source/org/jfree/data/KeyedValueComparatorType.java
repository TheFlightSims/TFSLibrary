/*     */ package org.jfree.data;
/*     */ 
/*     */ public final class KeyedValueComparatorType {
/*  53 */   public static final KeyedValueComparatorType BY_KEY = new KeyedValueComparatorType("KeyedValueComparatorType.BY_KEY");
/*     */   
/*  57 */   public static final KeyedValueComparatorType BY_VALUE = new KeyedValueComparatorType("KeyedValueComparatorType.BY_VALUE");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private KeyedValueComparatorType(String name) {
/*  69 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  78 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  90 */     if (this == o)
/*  91 */       return true; 
/*  93 */     if (!(o instanceof KeyedValueComparatorType))
/*  94 */       return false; 
/*  97 */     KeyedValueComparatorType type = (KeyedValueComparatorType)o;
/*  98 */     if (!this.name.equals(type.name))
/*  99 */       return false; 
/* 102 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 111 */     return this.name.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\KeyedValueComparatorType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */