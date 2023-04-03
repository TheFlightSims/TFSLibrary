/*     */ package org.apache.commons.collections.comparators;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public final class BooleanComparator implements Comparator, Serializable {
/*     */   private static final long serialVersionUID = 1830042991606340609L;
/*     */   
/*  41 */   private static final BooleanComparator TRUE_FIRST = new BooleanComparator(true);
/*     */   
/*  44 */   private static final BooleanComparator FALSE_FIRST = new BooleanComparator(false);
/*     */   
/*     */   private boolean trueFirst = false;
/*     */   
/*     */   public static BooleanComparator getTrueFirstComparator() {
/*  63 */     return TRUE_FIRST;
/*     */   }
/*     */   
/*     */   public static BooleanComparator getFalseFirstComparator() {
/*  79 */     return FALSE_FIRST;
/*     */   }
/*     */   
/*     */   public static BooleanComparator getBooleanComparator(boolean trueFirst) {
/*  98 */     return trueFirst ? TRUE_FIRST : FALSE_FIRST;
/*     */   }
/*     */   
/*     */   public BooleanComparator() {
/* 111 */     this(false);
/*     */   }
/*     */   
/*     */   public BooleanComparator(boolean trueFirst) {
/* 125 */     this.trueFirst = trueFirst;
/*     */   }
/*     */   
/*     */   public int compare(Object obj1, Object obj2) {
/* 142 */     return compare((Boolean)obj1, (Boolean)obj2);
/*     */   }
/*     */   
/*     */   public int compare(Boolean b1, Boolean b2) {
/* 155 */     boolean v1 = b1.booleanValue();
/* 156 */     boolean v2 = b2.booleanValue();
/* 158 */     return (v1 ^ v2) ? ((v1 ^ this.trueFirst) ? 1 : -1) : 0;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 169 */     int hash = "BooleanComparator".hashCode();
/* 170 */     return this.trueFirst ? (-1 * hash) : hash;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 186 */     return (this == object || (object instanceof BooleanComparator && this.trueFirst == ((BooleanComparator)object).trueFirst));
/*     */   }
/*     */   
/*     */   public boolean sortsTrueFirst() {
/* 203 */     return this.trueFirst;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\comparators\BooleanComparator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */