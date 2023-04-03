/*     */ package com.vividsolutions.jts.geom.util;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.math.Matrix;
/*     */ 
/*     */ public class AffineTransformationBuilder {
/*     */   private Coordinate src0;
/*     */   
/*     */   private Coordinate src1;
/*     */   
/*     */   private Coordinate src2;
/*     */   
/*     */   private Coordinate dest0;
/*     */   
/*     */   private Coordinate dest1;
/*     */   
/*     */   private Coordinate dest2;
/*     */   
/*     */   private double m00;
/*     */   
/*     */   private double m01;
/*     */   
/*     */   private double m02;
/*     */   
/*     */   private double m10;
/*     */   
/*     */   private double m11;
/*     */   
/*     */   private double m12;
/*     */   
/*     */   public AffineTransformationBuilder(Coordinate src0, Coordinate src1, Coordinate src2, Coordinate dest0, Coordinate dest1, Coordinate dest2) {
/*  97 */     this.src0 = src0;
/*  98 */     this.src1 = src1;
/*  99 */     this.src2 = src2;
/* 100 */     this.dest0 = dest0;
/* 101 */     this.dest1 = dest1;
/* 102 */     this.dest2 = dest2;
/*     */   }
/*     */   
/*     */   public AffineTransformation getTransformation() {
/* 116 */     boolean isSolvable = compute();
/* 117 */     if (isSolvable)
/* 118 */       return new AffineTransformation(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12); 
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   private boolean compute() {
/* 132 */     double[] bx = { this.dest0.x, this.dest1.x, this.dest2.x };
/* 133 */     double[] row0 = solve(bx);
/* 134 */     if (row0 == null)
/* 134 */       return false; 
/* 135 */     this.m00 = row0[0];
/* 136 */     this.m01 = row0[1];
/* 137 */     this.m02 = row0[2];
/* 139 */     double[] by = { this.dest0.y, this.dest1.y, this.dest2.y };
/* 140 */     double[] row1 = solve(by);
/* 141 */     if (row1 == null)
/* 141 */       return false; 
/* 142 */     this.m10 = row1[0];
/* 143 */     this.m11 = row1[1];
/* 144 */     this.m12 = row1[2];
/* 145 */     return true;
/*     */   }
/*     */   
/*     */   private double[] solve(double[] b) {
/* 158 */     double[][] a = { { this.src0.x, this.src0.y, 1.0D }, { this.src1.x, this.src1.y, 1.0D }, { this.src2.x, this.src2.y, 1.0D } };
/* 163 */     return Matrix.solve(a, b);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\AffineTransformationBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */