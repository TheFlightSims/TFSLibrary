/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ 
/*     */ public class ObliqueOrthographic extends Orthographic {
/*     */   private static final long serialVersionUID = -2306183438166607066L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   private final double sinphi0;
/*     */   
/*     */   private final double cosphi0;
/*     */   
/*     */   protected ObliqueOrthographic(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  72 */     super(parameters);
/*  73 */     this.sinphi0 = Math.sin(this.latitudeOfOrigin);
/*  74 */     this.cosphi0 = Math.cos(this.latitudeOfOrigin);
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*  85 */     double cosphi = Math.cos(y);
/*  86 */     double coslam = Math.cos(x);
/*  87 */     double sinphi = Math.sin(y);
/*  88 */     if (this.sinphi0 * sinphi + this.cosphi0 * cosphi * coslam < -1.0E-6D)
/*  89 */       throw new ProjectionException(158); 
/*  91 */     y = this.cosphi0 * sinphi - this.sinphi0 * cosphi * coslam;
/*  92 */     x = cosphi * Math.sin(x);
/*  94 */     if (ptDst != null) {
/*  95 */       ptDst.setLocation(x, y);
/*  96 */       return ptDst;
/*     */     } 
/*  98 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
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
/* 121 */       double phi = cosc * this.sinphi0 + y * sinc * this.cosphi0 / rho;
/* 122 */       y = (cosc - this.sinphi0 * phi) * rho;
/* 123 */       x *= sinc * this.cosphi0;
/* 126 */       if (Math.abs(phi) >= 1.0D) {
/* 127 */         phi = (phi < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */       } else {
/* 130 */         phi = Math.asin(phi);
/*     */       } 
/* 134 */       if (y == 0.0D) {
/* 135 */         if (x == 0.0D) {
/* 136 */           x = 0.0D;
/*     */         } else {
/* 138 */           x = (x < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */         } 
/*     */       } else {
/* 141 */         x = Math.atan2(x, y);
/*     */       } 
/* 143 */       y = phi;
/*     */     } 
/* 145 */     if (ptDst != null) {
/* 146 */       ptDst.setLocation(x, y);
/* 147 */       return ptDst;
/*     */     } 
/* 149 */     return new Point2D.Double(x, y);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\ObliqueOrthographic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */