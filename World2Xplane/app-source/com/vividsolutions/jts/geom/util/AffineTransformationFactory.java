/*     */ package com.vividsolutions.jts.geom.util;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.Angle;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class AffineTransformationFactory {
/*     */   public static AffineTransformation createFromControlVectors(Coordinate src0, Coordinate src1, Coordinate src2, Coordinate dest0, Coordinate dest1, Coordinate dest2) {
/*  64 */     AffineTransformationBuilder builder = new AffineTransformationBuilder(src0, src1, src2, dest0, dest1, dest2);
/*  66 */     return builder.getTransformation();
/*     */   }
/*     */   
/*     */   public static AffineTransformation createFromControlVectors(Coordinate src0, Coordinate src1, Coordinate dest0, Coordinate dest1) {
/*  86 */     Coordinate rotPt = new Coordinate(dest1.x - dest0.x, dest1.y - dest0.y);
/*  88 */     double ang = Angle.angleBetweenOriented(src1, src0, rotPt);
/*  90 */     double srcDist = src1.distance(src0);
/*  91 */     double destDist = dest1.distance(dest0);
/*  93 */     if (srcDist == 0.0D)
/*  94 */       return null; 
/*  96 */     double scale = destDist / srcDist;
/*  98 */     AffineTransformation trans = AffineTransformation.translationInstance(-src0.x, -src0.y);
/* 100 */     trans.rotate(ang);
/* 101 */     trans.scale(scale, scale);
/* 102 */     trans.translate(dest0.x, dest0.y);
/* 103 */     return trans;
/*     */   }
/*     */   
/*     */   public static AffineTransformation createFromControlVectors(Coordinate src0, Coordinate dest0) {
/* 120 */     double dx = dest0.x - src0.x;
/* 121 */     double dy = dest0.y - src0.y;
/* 122 */     return AffineTransformation.translationInstance(dx, dy);
/*     */   }
/*     */   
/*     */   public static AffineTransformation createFromControlVectors(Coordinate[] src, Coordinate[] dest) {
/* 140 */     if (src.length != dest.length)
/* 141 */       throw new IllegalArgumentException("Src and Dest arrays are not the same length"); 
/* 143 */     if (src.length <= 0)
/* 144 */       throw new IllegalArgumentException("Too few control points"); 
/* 145 */     if (src.length > 3)
/* 146 */       throw new IllegalArgumentException("Too many control points"); 
/* 148 */     if (src.length == 1)
/* 149 */       return createFromControlVectors(src[0], dest[0]); 
/* 150 */     if (src.length == 2)
/* 151 */       return createFromControlVectors(src[0], src[1], dest[0], dest[1]); 
/* 153 */     return createFromControlVectors(src[0], src[1], src[2], dest[0], dest[1], dest[2]);
/*     */   }
/*     */   
/*     */   public static AffineTransformation createFromBaseLines(Coordinate src0, Coordinate src1, Coordinate dest0, Coordinate dest1) {
/* 178 */     Coordinate rotPt = new Coordinate(src0.x + dest1.x - dest0.x, src0.y + dest1.y - dest0.y);
/* 180 */     double ang = Angle.angleBetweenOriented(src1, src0, rotPt);
/* 182 */     double srcDist = src1.distance(src0);
/* 183 */     double destDist = dest1.distance(dest0);
/* 186 */     if (srcDist == 0.0D)
/* 187 */       return new AffineTransformation(); 
/* 189 */     double scale = destDist / srcDist;
/* 191 */     AffineTransformation trans = AffineTransformation.translationInstance(-src0.x, -src0.y);
/* 193 */     trans.rotate(ang);
/* 194 */     trans.scale(scale, scale);
/* 195 */     trans.translate(dest0.x, dest0.y);
/* 196 */     return trans;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\AffineTransformationFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */