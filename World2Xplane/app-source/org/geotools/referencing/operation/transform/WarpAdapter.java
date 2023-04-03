/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.RasterFormatException;
/*     */ import javax.media.jai.Warp;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ final class WarpAdapter extends Warp {
/*     */   private static final long serialVersionUID = -8679060848877065181L;
/*     */   
/*     */   private final CharSequence name;
/*     */   
/*     */   private final MathTransform2D inverse;
/*     */   
/*     */   public WarpAdapter(CharSequence name, MathTransform2D inverse) {
/*  65 */     this.name = name;
/*  66 */     this.inverse = inverse;
/*     */   }
/*     */   
/*     */   public MathTransform2D getTransform() {
/*  73 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public float[] warpSparseRect(int xmin, int ymin, int width, int height, int periodX, int periodY, float[] destRect) {
/*  84 */     if (periodX < 1)
/*  84 */       throw new IllegalArgumentException(String.valueOf(periodX)); 
/*  85 */     if (periodY < 1)
/*  85 */       throw new IllegalArgumentException(String.valueOf(periodY)); 
/*  87 */     int xmax = xmin + width;
/*  88 */     int ymax = ymin + height;
/*  89 */     int count = (width + periodX - 1) / periodX * (height + periodY - 1) / periodY;
/*  90 */     if (destRect == null)
/*  91 */       destRect = new float[2 * count]; 
/*  93 */     int index = 0;
/*     */     int y;
/*  94 */     for (y = ymin; y < ymax; y += periodY) {
/*     */       int x;
/*  95 */       for (x = xmin; x < xmax; x += periodX) {
/*  96 */         destRect[index++] = x + 0.5F;
/*  97 */         destRect[index++] = y + 0.5F;
/*     */       } 
/*     */     } 
/*     */     try {
/* 101 */       this.inverse.transform(destRect, 0, destRect, 0, count);
/* 102 */     } catch (TransformException exception) {
/* 105 */       RasterFormatException e = new RasterFormatException(Errors.format(30, this.name));
/* 107 */       e.initCause((Throwable)exception);
/* 108 */       throw e;
/*     */     } 
/* 110 */     while (--index >= 0)
/* 111 */       destRect[index] = destRect[index] - 0.5F; 
/* 113 */     return destRect;
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 124 */     Point2D result = new Point2D.Double(destPt.getX() + 0.5D, destPt.getY() + 0.5D);
/*     */     try {
/* 126 */       result = this.inverse.transform(result, result);
/* 127 */     } catch (TransformException exception) {
/* 128 */       throw new IllegalArgumentException(Errors.format(12, "destPt", destPt), exception);
/*     */     } 
/* 131 */     result.setLocation(result.getX() - 0.5D, result.getY() - 0.5D);
/* 132 */     return result;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 143 */     Point2D result = new Point2D.Double(sourcePt.getX() + 0.5D, sourcePt.getY() + 0.5D);
/*     */     try {
/* 145 */       result = this.inverse.inverse().transform(result, result);
/* 146 */     } catch (TransformException exception) {
/* 147 */       throw new IllegalArgumentException(Errors.format(12, "sourcePt", sourcePt), exception);
/*     */     } 
/* 150 */     result.setLocation(result.getX() - 0.5D, result.getY() - 0.5D);
/* 151 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\WarpAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */