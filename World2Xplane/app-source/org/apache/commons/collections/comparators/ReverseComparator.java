/*     */ package org.apache.commons.collections.comparators;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class ReverseComparator implements Comparator, Serializable {
/*     */   private static final long serialVersionUID = 2858887242028539265L;
/*     */   
/*     */   private Comparator comparator;
/*     */   
/*     */   public ReverseComparator() {
/*  52 */     this(null);
/*     */   }
/*     */   
/*     */   public ReverseComparator(Comparator comparator) {
/*  65 */     if (comparator != null) {
/*  66 */       this.comparator = comparator;
/*     */     } else {
/*  68 */       this.comparator = ComparableComparator.getInstance();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int compare(Object obj1, Object obj2) {
/*  81 */     return this.comparator.compare(obj2, obj1);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  93 */     return "ReverseComparator".hashCode() ^ this.comparator.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 113 */     if (this == object)
/* 114 */       return true; 
/* 115 */     if (null == object)
/* 116 */       return false; 
/* 117 */     if (object.getClass().equals(getClass())) {
/* 118 */       ReverseComparator thatrc = (ReverseComparator)object;
/* 119 */       return this.comparator.equals(thatrc.comparator);
/*     */     } 
/* 121 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\comparators\ReverseComparator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */