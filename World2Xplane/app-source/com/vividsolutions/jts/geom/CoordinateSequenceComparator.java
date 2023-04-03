/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class CoordinateSequenceComparator implements Comparator {
/*     */   protected int dimensionLimit;
/*     */   
/*     */   public static int compare(double a, double b) {
/*  62 */     if (a < b)
/*  62 */       return -1; 
/*  63 */     if (a > b)
/*  63 */       return 1; 
/*  65 */     if (Double.isNaN(a)) {
/*  66 */       if (Double.isNaN(b))
/*  66 */         return 0; 
/*  67 */       return -1;
/*     */     } 
/*  70 */     if (Double.isNaN(b))
/*  70 */       return 1; 
/*  71 */     return 0;
/*     */   }
/*     */   
/*     */   public CoordinateSequenceComparator() {
/*  84 */     this.dimensionLimit = Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public CoordinateSequenceComparator(int dimensionLimit) {
/*  94 */     this.dimensionLimit = dimensionLimit;
/*     */   }
/*     */   
/*     */   public int compare(Object o1, Object o2) {
/* 106 */     CoordinateSequence s1 = (CoordinateSequence)o1;
/* 107 */     CoordinateSequence s2 = (CoordinateSequence)o2;
/* 109 */     int size1 = s1.size();
/* 110 */     int size2 = s2.size();
/* 112 */     int dim1 = s1.getDimension();
/* 113 */     int dim2 = s2.getDimension();
/* 115 */     int minDim = dim1;
/* 116 */     if (dim2 < minDim)
/* 117 */       minDim = dim2; 
/* 118 */     boolean dimLimited = false;
/* 119 */     if (this.dimensionLimit <= minDim) {
/* 120 */       minDim = this.dimensionLimit;
/* 121 */       dimLimited = true;
/*     */     } 
/* 125 */     if (!dimLimited) {
/* 126 */       if (dim1 < dim2)
/* 126 */         return -1; 
/* 127 */       if (dim1 > dim2)
/* 127 */         return 1; 
/*     */     } 
/* 131 */     int i = 0;
/* 132 */     while (i < size1 && i < size2) {
/* 133 */       int ptComp = compareCoordinate(s1, s2, i, minDim);
/* 134 */       if (ptComp != 0)
/* 134 */         return ptComp; 
/* 135 */       i++;
/*     */     } 
/* 137 */     if (i < size1)
/* 137 */       return 1; 
/* 138 */     if (i < size2)
/* 138 */       return -1; 
/* 140 */     return 0;
/*     */   }
/*     */   
/*     */   protected int compareCoordinate(CoordinateSequence s1, CoordinateSequence s2, int i, int dimension) {
/* 155 */     for (int d = 0; d < dimension; d++) {
/* 156 */       double ord1 = s1.getOrdinate(i, d);
/* 157 */       double ord2 = s2.getOrdinate(i, d);
/* 158 */       int comp = compare(ord1, ord2);
/* 159 */       if (comp != 0)
/* 159 */         return comp; 
/*     */     } 
/* 161 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\CoordinateSequenceComparator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */