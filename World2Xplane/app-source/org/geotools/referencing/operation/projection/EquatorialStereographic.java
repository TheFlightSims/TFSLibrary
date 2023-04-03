/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ 
/*     */ public class EquatorialStereographic extends StereographicUSGS {
/*     */   private static final long serialVersionUID = -5098015759558831875L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   static final double k0 = 2.0D;
/*     */   
/*     */   protected EquatorialStereographic(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  72 */     this(parameters, Stereographic.Provider.PARAMETERS);
/*     */   }
/*     */   
/*     */   EquatorialStereographic(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor) throws ParameterNotFoundException {
/*  86 */     super(parameters, descriptor);
/*  87 */     assert super.k0 == 2.0D : super.k0;
/*  88 */     this.latitudeOfOrigin = 0.0D;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 101 */     assert (ptDst = super.transformNormalized(x, y, ptDst)) != null;
/* 103 */     double chi = 2.0D * Math.atan(ssfn(y, Math.sin(y))) - 1.5707963267948966D;
/* 104 */     double cosChi = Math.cos(chi);
/* 105 */     double A = 2.0D / (1.0D + cosChi * Math.cos(x));
/* 106 */     x = A * cosChi * Math.sin(x);
/* 107 */     y = A * Math.sin(chi);
/* 109 */     assert checkTransform(x, y, ptDst);
/* 110 */     if (ptDst != null) {
/* 111 */       ptDst.setLocation(x, y);
/* 112 */       return ptDst;
/*     */     } 
/* 114 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   static final class Spherical extends EquatorialStereographic {
/*     */     private static final long serialVersionUID = -4790138052004333003L;
/*     */     
/*     */     Spherical(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor) throws ParameterNotFoundException {
/* 142 */       super(parameters, descriptor);
/* 143 */       ensureSpherical();
/*     */     }
/*     */     
/*     */     protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 156 */       assert (ptDst = super.transformNormalized(x, y, ptDst)) != null;
/* 158 */       double coslat = Math.cos(y);
/* 159 */       double f = 1.0D + coslat * Math.cos(x);
/* 160 */       if (f < 1.0E-6D)
/* 161 */         throw new ProjectionException(202); 
/* 163 */       f = 2.0D / f;
/* 164 */       x = f * coslat * Math.sin(x);
/* 165 */       y = f * Math.sin(y);
/* 167 */       assert checkTransform(x, y, ptDst);
/* 168 */       if (ptDst != null) {
/* 169 */         ptDst.setLocation(x, y);
/* 170 */         return ptDst;
/*     */       } 
/* 172 */       return new Point2D.Double(x, y);
/*     */     }
/*     */     
/*     */     protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 184 */       assert (ptDst = super.inverseTransformNormalized(x, y, ptDst)) != null;
/* 186 */       double rho = Math.hypot(x, y);
/* 187 */       if (Math.abs(rho) < 1.0E-6D) {
/* 188 */         y = 0.0D;
/* 189 */         x = 0.0D;
/*     */       } else {
/* 191 */         double c = 2.0D * Math.atan(rho / 2.0D);
/* 192 */         double cosc = Math.cos(c);
/* 193 */         double sinc = Math.sin(c);
/* 194 */         y = Math.asin(y * sinc / rho);
/* 195 */         double t = x * sinc;
/* 196 */         double ct = rho * cosc;
/* 197 */         x = (Math.abs(t) < 1.0E-6D && Math.abs(ct) < 1.0E-6D) ? 0.0D : Math.atan2(t, ct);
/*     */       } 
/* 199 */       assert checkInverseTransform(x, y, ptDst);
/* 200 */       if (ptDst != null) {
/* 201 */         ptDst.setLocation(x, y);
/* 202 */         return ptDst;
/*     */       } 
/* 204 */       return new Point2D.Double(x, y);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\EquatorialStereographic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */