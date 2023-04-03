/*     */ package org.apache.commons.collections.comparators;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class ComparableComparator implements Comparator, Serializable {
/*     */   private static final long serialVersionUID = -291439688585137865L;
/*     */   
/*  51 */   private static final ComparableComparator instance = new ComparableComparator();
/*     */   
/*     */   public static ComparableComparator getInstance() {
/*  64 */     return instance;
/*     */   }
/*     */   
/*     */   public int compare(Object obj1, Object obj2) {
/*  92 */     return ((Comparable)obj1).compareTo(obj2);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 104 */     return "ComparableComparator".hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 123 */     return (this == object || (null != object && object.getClass().equals(getClass())));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\comparators\ComparableComparator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */