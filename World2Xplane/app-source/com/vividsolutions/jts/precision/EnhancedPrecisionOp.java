/*     */ package com.vividsolutions.jts.precision;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ 
/*     */ public class EnhancedPrecisionOp {
/*     */   public static Geometry intersection(Geometry geom0, Geometry geom1) {
/*     */     try {
/*  57 */       Geometry result = geom0.intersection(geom1);
/*  58 */       return result;
/*  60 */     } catch (RuntimeException ex) {
/*  62 */       RuntimeException originalEx = ex;
/*     */       try {
/*  70 */         CommonBitsOp cbo = new CommonBitsOp(true);
/*  71 */         Geometry resultEP = cbo.intersection(geom0, geom1);
/*  73 */         if (!resultEP.isValid())
/*  74 */           throw originalEx; 
/*  75 */         return resultEP;
/*  77 */       } catch (RuntimeException ex2) {
/*  79 */         throw originalEx;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Geometry union(Geometry geom0, Geometry geom1) {
/*     */     try {
/*  92 */       Geometry result = geom0.union(geom1);
/*  93 */       return result;
/*  95 */     } catch (RuntimeException ex) {
/*  97 */       RuntimeException originalEx = ex;
/*     */       try {
/* 105 */         CommonBitsOp cbo = new CommonBitsOp(true);
/* 106 */         Geometry resultEP = cbo.union(geom0, geom1);
/* 108 */         if (!resultEP.isValid())
/* 109 */           throw originalEx; 
/* 110 */         return resultEP;
/* 112 */       } catch (RuntimeException ex2) {
/* 114 */         throw originalEx;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Geometry difference(Geometry geom0, Geometry geom1) {
/*     */     try {
/* 127 */       Geometry result = geom0.difference(geom1);
/* 128 */       return result;
/* 130 */     } catch (RuntimeException ex) {
/* 132 */       RuntimeException originalEx = ex;
/*     */       try {
/* 140 */         CommonBitsOp cbo = new CommonBitsOp(true);
/* 141 */         Geometry resultEP = cbo.difference(geom0, geom1);
/* 143 */         if (!resultEP.isValid())
/* 144 */           throw originalEx; 
/* 145 */         return resultEP;
/* 147 */       } catch (RuntimeException ex2) {
/* 149 */         throw originalEx;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Geometry symDifference(Geometry geom0, Geometry geom1) {
/*     */     try {
/* 162 */       Geometry result = geom0.symDifference(geom1);
/* 163 */       return result;
/* 165 */     } catch (RuntimeException ex) {
/* 167 */       RuntimeException originalEx = ex;
/*     */       try {
/* 175 */         CommonBitsOp cbo = new CommonBitsOp(true);
/* 176 */         Geometry resultEP = cbo.symDifference(geom0, geom1);
/* 178 */         if (!resultEP.isValid())
/* 179 */           throw originalEx; 
/* 180 */         return resultEP;
/* 182 */       } catch (RuntimeException ex2) {
/* 184 */         throw originalEx;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Geometry buffer(Geometry geom, double distance) {
/*     */     try {
/* 200 */       Geometry result = geom.buffer(distance);
/* 201 */       return result;
/* 203 */     } catch (RuntimeException ex) {
/* 205 */       RuntimeException originalEx = ex;
/*     */       try {
/* 213 */         CommonBitsOp cbo = new CommonBitsOp(true);
/* 214 */         Geometry resultEP = cbo.buffer(geom, distance);
/* 216 */         if (!resultEP.isValid())
/* 217 */           throw originalEx; 
/* 218 */         return resultEP;
/* 220 */       } catch (RuntimeException ex2) {
/* 222 */         throw originalEx;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\precision\EnhancedPrecisionOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */