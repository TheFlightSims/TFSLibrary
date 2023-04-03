/*     */ package org.opengis.util;
/*     */ 
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ 
/*     */ @UML(identifier = "UnlimitedInteger", specification = Specification.ISO_19103)
/*     */ public final class UnlimitedInteger extends Number implements Comparable<UnlimitedInteger> {
/*     */   private static final long serialVersionUID = 4748246369364771836L;
/*     */   
/*  40 */   public static final UnlimitedInteger NEGATIVE_INFINITY = new UnlimitedInteger(-2147483648);
/*     */   
/*  45 */   public static final UnlimitedInteger POSITIVE_INFINITY = new UnlimitedInteger(2147483647);
/*     */   
/*     */   public static final int MIN_VALUE = -2147483647;
/*     */   
/*     */   public static final int MAX_VALUE = 2147483646;
/*     */   
/*     */   private final int value;
/*     */   
/*     */   public UnlimitedInteger(int value) {
/*  68 */     this.value = value;
/*     */   }
/*     */   
/*     */   public boolean isInfinite() {
/*  75 */     return (this.value == Integer.MAX_VALUE || this.value == Integer.MIN_VALUE);
/*     */   }
/*     */   
/*     */   public int intValue() {
/*  85 */     return this.value;
/*     */   }
/*     */   
/*     */   public long longValue() {
/*  94 */     return this.value;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 104 */     switch (this.value) {
/*     */       case 2147483647:
/* 105 */         return Float.POSITIVE_INFINITY;
/*     */       case -2147483648:
/* 106 */         return Float.NEGATIVE_INFINITY;
/*     */     } 
/* 107 */     return this.value;
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 118 */     switch (this.value) {
/*     */       case 2147483647:
/* 119 */         return Double.POSITIVE_INFINITY;
/*     */       case -2147483648:
/* 120 */         return Double.NEGATIVE_INFINITY;
/*     */     } 
/* 121 */     return this.value;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 130 */     switch (this.value) {
/*     */       case 2147483647:
/* 131 */         return "∞";
/*     */       case -2147483648:
/* 132 */         return "-∞";
/*     */     } 
/* 133 */     return Integer.toString(this.value);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 142 */     return this.value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 150 */     return (object instanceof UnlimitedInteger && ((UnlimitedInteger)object).value == this.value);
/*     */   }
/*     */   
/*     */   public int compareTo(UnlimitedInteger other) {
/* 162 */     if (this.value < other.value)
/* 162 */       return -1; 
/* 163 */     if (this.value > other.value)
/* 163 */       return 1; 
/* 164 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\UnlimitedInteger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */