/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ 
/*     */ public class PolarOrthographic extends Orthographic {
/*     */   private static final long serialVersionUID = 3281503361127178484L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   private final boolean northPole;
/*     */   
/*     */   protected PolarOrthographic(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  68 */     super(parameters);
/*  69 */     ensureLatitudeEquals(Orthographic.Provider.LATITUDE_OF_ORIGIN, this.latitudeOfOrigin, 1.5707963267948966D);
/*  70 */     this.northPole = (this.latitudeOfOrigin > 0.0D);
/*  71 */     this.latitudeOfOrigin = this.northPole ? 1.5707963267948966D : -1.5707963267948966D;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*  82 */     if (Math.abs(y - this.latitudeOfOrigin) - 1.0E-6D > 1.5707963267948966D)
/*  83 */       throw new ProjectionException(158); 
/*  85 */     double cosphi = Math.cos(y);
/*  86 */     double coslam = Math.cos(x);
/*  87 */     if (this.northPole)
/*  88 */       coslam = -coslam; 
/*  90 */     y = cosphi * coslam;
/*  91 */     x = cosphi * Math.sin(x);
/*  93 */     if (ptDst != null) {
/*  94 */       ptDst.setLocation(x, y);
/*  95 */       return ptDst;
/*     */     } 
/*  97 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 107 */     double rho = Math.hypot(x, y);
/* 108 */     double sinc = rho;
/* 109 */     if (sinc > 1.0D) {
/* 110 */       if (sinc - 1.0D > 1.0E-6D)
/* 111 */         throw new ProjectionException(158); 
/* 113 */       sinc = 1.0D;
/*     */     } 
/* 115 */     if (rho <= 1.0E-6D) {
/* 116 */       y = this.latitudeOfOrigin;
/* 117 */       x = 0.0D;
/*     */     } else {
/*     */       double phi;
/* 120 */       if (this.northPole) {
/* 121 */         y = -y;
/* 122 */         phi = Math.acos(sinc);
/*     */       } else {
/* 124 */         phi = -Math.acos(sinc);
/*     */       } 
/* 126 */       x = Math.atan2(x, y);
/* 127 */       y = phi;
/*     */     } 
/* 129 */     if (ptDst != null) {
/* 130 */       ptDst.setLocation(x, y);
/* 131 */       return ptDst;
/*     */     } 
/* 133 */     return new Point2D.Double(x, y);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\PolarOrthographic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */