/*     */ package org.apache.commons.collections.comparators;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class NullComparator implements Comparator, Serializable {
/*     */   private static final long serialVersionUID = -5820772575483504339L;
/*     */   
/*     */   private Comparator nonNullComparator;
/*     */   
/*     */   private boolean nullsAreHigh;
/*     */   
/*     */   public NullComparator() {
/*  55 */     this(ComparableComparator.getInstance(), true);
/*     */   }
/*     */   
/*     */   public NullComparator(Comparator nonNullComparator) {
/*  72 */     this(nonNullComparator, true);
/*     */   }
/*     */   
/*     */   public NullComparator(boolean nullsAreHigh) {
/*  88 */     this(ComparableComparator.getInstance(), nullsAreHigh);
/*     */   }
/*     */   
/*     */   public NullComparator(Comparator nonNullComparator, boolean nullsAreHigh) {
/* 111 */     this.nonNullComparator = nonNullComparator;
/* 112 */     this.nullsAreHigh = nullsAreHigh;
/* 114 */     if (nonNullComparator == null)
/* 115 */       throw new NullPointerException("null nonNullComparator"); 
/*     */   }
/*     */   
/*     */   public int compare(Object o1, Object o2) {
/* 137 */     if (o1 == o2)
/* 137 */       return 0; 
/* 138 */     if (o1 == null)
/* 138 */       return this.nullsAreHigh ? 1 : -1; 
/* 139 */     if (o2 == null)
/* 139 */       return this.nullsAreHigh ? -1 : 1; 
/* 140 */     return this.nonNullComparator.compare(o1, o2);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 151 */     return (this.nullsAreHigh ? -1 : 1) * this.nonNullComparator.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 166 */     if (obj == null)
/* 166 */       return false; 
/* 167 */     if (obj == this)
/* 167 */       return true; 
/* 168 */     if (!obj.getClass().equals(getClass()))
/* 168 */       return false; 
/* 170 */     NullComparator other = (NullComparator)obj;
/* 172 */     return (this.nullsAreHigh == other.nullsAreHigh && this.nonNullComparator.equals(other.nonNullComparator));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\comparators\NullComparator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */