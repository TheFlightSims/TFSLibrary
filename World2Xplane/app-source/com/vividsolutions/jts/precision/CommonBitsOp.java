/*     */ package com.vividsolutions.jts.precision;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ 
/*     */ public class CommonBitsOp {
/*     */   private boolean returnToOriginalPrecision = true;
/*     */   
/*     */   private CommonBitsRemover cbr;
/*     */   
/*     */   public CommonBitsOp() {
/*  59 */     this(true);
/*     */   }
/*     */   
/*     */   public CommonBitsOp(boolean returnToOriginalPrecision) {
/*  70 */     this.returnToOriginalPrecision = returnToOriginalPrecision;
/*     */   }
/*     */   
/*     */   public Geometry intersection(Geometry geom0, Geometry geom1) {
/*  81 */     Geometry[] geom = removeCommonBits(geom0, geom1);
/*  82 */     return computeResultPrecision(geom[0].intersection(geom[1]));
/*     */   }
/*     */   
/*     */   public Geometry union(Geometry geom0, Geometry geom1) {
/*  93 */     Geometry[] geom = removeCommonBits(geom0, geom1);
/*  94 */     return computeResultPrecision(geom[0].union(geom[1]));
/*     */   }
/*     */   
/*     */   public Geometry difference(Geometry geom0, Geometry geom1) {
/* 105 */     Geometry[] geom = removeCommonBits(geom0, geom1);
/* 106 */     return computeResultPrecision(geom[0].difference(geom[1]));
/*     */   }
/*     */   
/*     */   public Geometry symDifference(Geometry geom0, Geometry geom1) {
/* 118 */     Geometry[] geom = removeCommonBits(geom0, geom1);
/* 119 */     return computeResultPrecision(geom[0].symDifference(geom[1]));
/*     */   }
/*     */   
/*     */   public Geometry buffer(Geometry geom0, double distance) {
/* 131 */     Geometry geom = removeCommonBits(geom0);
/* 132 */     return computeResultPrecision(geom.buffer(distance));
/*     */   }
/*     */   
/*     */   private Geometry computeResultPrecision(Geometry result) {
/* 147 */     if (this.returnToOriginalPrecision)
/* 148 */       this.cbr.addCommonBits(result); 
/* 149 */     return result;
/*     */   }
/*     */   
/*     */   private Geometry removeCommonBits(Geometry geom0) {
/* 160 */     this.cbr = new CommonBitsRemover();
/* 161 */     this.cbr.add(geom0);
/* 162 */     Geometry geom = this.cbr.removeCommonBits((Geometry)geom0.clone());
/* 163 */     return geom;
/*     */   }
/*     */   
/*     */   private Geometry[] removeCommonBits(Geometry geom0, Geometry geom1) {
/* 176 */     this.cbr = new CommonBitsRemover();
/* 177 */     this.cbr.add(geom0);
/* 178 */     this.cbr.add(geom1);
/* 179 */     Geometry[] geom = new Geometry[2];
/* 180 */     geom[0] = this.cbr.removeCommonBits((Geometry)geom0.clone());
/* 181 */     geom[1] = this.cbr.removeCommonBits((Geometry)geom1.clone());
/* 182 */     return geom;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\precision\CommonBitsOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */