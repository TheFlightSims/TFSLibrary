/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ 
/*     */ public class EquatorialOrthographic extends ObliqueOrthographic {
/*     */   private static final long serialVersionUID = 1093901743907259987L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   protected EquatorialOrthographic(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  66 */     super(parameters);
/*  67 */     ensureLatitudeEquals(Orthographic.Provider.LATITUDE_OF_ORIGIN, this.latitudeOfOrigin, 0.0D);
/*  68 */     this.latitudeOfOrigin = 0.0D;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*  81 */     assert (ptDst = super.transformNormalized(x, y, ptDst)) != null;
/*  82 */     double cosphi = Math.cos(y);
/*  83 */     double coslam = Math.cos(x);
/*  84 */     if (cosphi * coslam < -1.0E-6D)
/*  85 */       throw new ProjectionException(158); 
/*  87 */     y = Math.sin(y);
/*  88 */     x = cosphi * Math.sin(x);
/*  90 */     assert checkTransform(x, y, ptDst);
/*  91 */     if (ptDst != null) {
/*  92 */       ptDst.setLocation(x, y);
/*  93 */       return ptDst;
/*     */     } 
/*  95 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 107 */     assert (ptDst = super.inverseTransformNormalized(x, y, ptDst)) != null;
/* 108 */     double rho = Math.hypot(x, y);
/* 109 */     double sinc = rho;
/* 110 */     if (sinc > 1.0D) {
/* 111 */       if (sinc - 1.0D > 1.0E-6D)
/* 112 */         throw new ProjectionException(158); 
/* 114 */       sinc = 1.0D;
/*     */     } 
/* 116 */     double cosc = Math.sqrt(1.0D - sinc * sinc);
/* 117 */     if (rho <= 1.0E-6D) {
/* 118 */       y = this.latitudeOfOrigin;
/* 119 */       x = 0.0D;
/*     */     } else {
/* 121 */       double phi = y * sinc / rho;
/* 122 */       x *= sinc;
/* 123 */       y = cosc * rho;
/* 126 */       if (Math.abs(phi) >= 1.0D) {
/* 127 */         phi = (phi < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */       } else {
/* 129 */         phi = Math.asin(phi);
/*     */       } 
/* 133 */       if (y == 0.0D) {
/* 134 */         if (x == 0.0D) {
/* 135 */           x = 0.0D;
/*     */         } else {
/* 137 */           x = (x < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */         } 
/*     */       } else {
/* 140 */         x = Math.atan2(x, y);
/*     */       } 
/* 142 */       y = phi;
/*     */     } 
/* 144 */     assert checkInverseTransform(x, y, ptDst);
/* 145 */     if (ptDst != null) {
/* 146 */       ptDst.setLocation(x, y);
/* 147 */       return ptDst;
/*     */     } 
/* 149 */     return new Point2D.Double(x, y);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\EquatorialOrthographic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */