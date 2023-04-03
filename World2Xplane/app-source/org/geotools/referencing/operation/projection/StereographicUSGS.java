/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ 
/*     */ class StereographicUSGS extends Stereographic {
/*     */   private static final long serialVersionUID = 948619442800459871L;
/*     */   
/*     */   private static final int MAXIMUM_ITERATIONS = 15;
/*     */   
/*     */   private static final double ITERATION_TOLERANCE = 1.0E-10D;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   final double k0;
/*     */   
/*     */   final double sinphi0;
/*     */   
/*     */   final double cosphi0;
/*     */   
/*     */   final double chi1;
/*     */   
/*     */   final double sinChi1;
/*     */   
/*     */   final double cosChi1;
/*     */   
/*     */   protected StereographicUSGS(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  86 */     this(parameters, Stereographic.Provider.PARAMETERS);
/*     */   }
/*     */   
/*     */   StereographicUSGS(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor) throws ParameterNotFoundException {
/* 100 */     super(parameters, descriptor);
/* 101 */     if (Math.abs(this.latitudeOfOrigin) < 1.0E-6D) {
/* 102 */       this.latitudeOfOrigin = 0.0D;
/* 103 */       this.cosphi0 = 1.0D;
/* 104 */       this.sinphi0 = 0.0D;
/* 105 */       this.chi1 = 0.0D;
/* 106 */       this.cosChi1 = 1.0D;
/* 107 */       this.sinChi1 = 0.0D;
/*     */     } else {
/* 109 */       this.cosphi0 = Math.cos(this.latitudeOfOrigin);
/* 110 */       this.sinphi0 = Math.sin(this.latitudeOfOrigin);
/* 111 */       this.chi1 = 2.0D * Math.atan(ssfn(this.latitudeOfOrigin, this.sinphi0)) - 1.5707963267948966D;
/* 112 */       this.cosChi1 = Math.cos(this.chi1);
/* 113 */       this.sinChi1 = Math.sin(this.chi1);
/*     */     } 
/* 116 */     this.k0 = 2.0D * msfn(this.sinphi0, this.cosphi0);
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 127 */     double chi = 2.0D * Math.atan(ssfn(y, Math.sin(y))) - 1.5707963267948966D;
/* 128 */     double sinChi = Math.sin(chi);
/* 129 */     double cosChi = Math.cos(chi);
/* 130 */     double cosChi_cosLon = cosChi * Math.cos(x);
/* 131 */     double A = this.k0 / this.cosChi1 / (1.0D + this.sinChi1 * sinChi + this.cosChi1 * cosChi_cosLon);
/* 132 */     x = A * cosChi * Math.sin(x);
/* 133 */     y = A * (this.cosChi1 * sinChi - this.sinChi1 * cosChi_cosLon);
/* 135 */     if (ptDst != null) {
/* 136 */       ptDst.setLocation(x, y);
/* 137 */       return ptDst;
/*     */     } 
/* 139 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 149 */     double rho = Math.hypot(x, y);
/* 150 */     double ce = 2.0D * Math.atan2(rho * this.cosChi1, this.k0);
/* 151 */     double cosce = Math.cos(ce);
/* 152 */     double since = Math.sin(ce);
/* 153 */     boolean rhoIs0 = (Math.abs(rho) < 1.0E-6D);
/* 154 */     double chi = rhoIs0 ? this.chi1 : Math.asin(cosce * this.sinChi1 + y * since * this.cosChi1 / rho);
/* 155 */     double tp = Math.tan(0.7853981633974483D + chi / 2.0D);
/* 158 */     double t = x * since;
/* 159 */     double ct = rho * this.cosChi1 * cosce - y * this.sinChi1 * since;
/* 162 */     double halfe = this.excentricity / 2.0D;
/* 163 */     double phi0 = chi;
/* 164 */     int i = 15;
/*     */     while (true) {
/* 165 */       double esinphi = this.excentricity * Math.sin(phi0);
/* 166 */       double phi = 2.0D * Math.atan(tp * Math.pow((1.0D + esinphi) / (1.0D - esinphi), halfe)) - 1.5707963267948966D;
/* 167 */       if (Math.abs(phi - phi0) < 1.0E-10D) {
/* 169 */         x = (rhoIs0 || (Math.abs(t) < 1.0E-6D && Math.abs(ct) < 1.0E-6D)) ? 0.0D : Math.atan2(t, ct);
/* 170 */         y = phi;
/*     */         break;
/*     */       } 
/* 173 */       phi0 = phi;
/* 174 */       if (--i < 0)
/* 175 */         throw new ProjectionException(129); 
/*     */     } 
/* 178 */     if (ptDst != null) {
/* 179 */       ptDst.setLocation(x, y);
/* 180 */       return ptDst;
/*     */     } 
/* 182 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected double getToleranceForAssertions(double longitude, double latitude) {
/* 190 */     double delta = Math.abs(longitude - this.centralMeridian) / 2.0D + Math.abs(latitude - this.latitudeOfOrigin);
/* 192 */     if (delta > 40.0D)
/* 193 */       return 0.5D; 
/* 195 */     if (delta > 15.0D)
/* 196 */       return 0.1D; 
/* 198 */     return super.getToleranceForAssertions(longitude, latitude);
/*     */   }
/*     */   
/*     */   final double ssfn(double phi, double sinphi) {
/* 205 */     sinphi *= this.excentricity;
/* 206 */     return Math.tan(0.7853981633974483D + phi / 2.0D) * Math.pow((1.0D - sinphi) / (1.0D + sinphi), this.excentricity / 2.0D);
/*     */   }
/*     */   
/*     */   static final class Spherical extends StereographicUSGS {
/*     */     private static final long serialVersionUID = -8558594307755820783L;
/*     */     
/*     */     private static final double k0 = 2.0D;
/*     */     
/*     */     Spherical(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor) throws ParameterNotFoundException {
/* 242 */       super(parameters, descriptor);
/* 243 */       ensureSpherical();
/*     */     }
/*     */     
/*     */     protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 256 */       assert (ptDst = super.transformNormalized(x, y, ptDst)) != null;
/* 258 */       double coslat = Math.cos(y);
/* 259 */       double sinlat = Math.sin(y);
/* 260 */       double coslon = Math.cos(x);
/* 261 */       double f = 1.0D + this.sinphi0 * sinlat + this.cosphi0 * coslat * coslon;
/* 262 */       if (f < 1.0E-6D)
/* 263 */         throw new ProjectionException(202); 
/* 265 */       f = 2.0D / f;
/* 266 */       x = f * coslat * Math.sin(x);
/* 267 */       y = f * (this.cosphi0 * sinlat - this.sinphi0 * coslat * coslon);
/* 269 */       assert checkTransform(x, y, ptDst);
/* 270 */       if (ptDst != null) {
/* 271 */         ptDst.setLocation(x, y);
/* 272 */         return ptDst;
/*     */       } 
/* 274 */       return new Point2D.Double(x, y);
/*     */     }
/*     */     
/*     */     protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 286 */       assert (ptDst = super.inverseTransformNormalized(x, y, ptDst)) != null;
/* 288 */       double rho = Math.hypot(x, y);
/* 289 */       if (Math.abs(rho) < 1.0E-6D) {
/* 290 */         y = this.latitudeOfOrigin;
/* 291 */         x = 0.0D;
/*     */       } else {
/* 293 */         double c = 2.0D * Math.atan(rho / 2.0D);
/* 294 */         double cosc = Math.cos(c);
/* 295 */         double sinc = Math.sin(c);
/* 296 */         double ct = rho * this.cosphi0 * cosc - y * this.sinphi0 * sinc;
/* 297 */         double t = x * sinc;
/* 298 */         y = Math.asin(cosc * this.sinphi0 + y * sinc * this.cosphi0 / rho);
/* 299 */         x = (Math.abs(ct) < 1.0E-6D && Math.abs(t) < 1.0E-6D) ? 0.0D : Math.atan2(t, ct);
/*     */       } 
/* 301 */       assert checkInverseTransform(x, y, ptDst);
/* 302 */       if (ptDst != null) {
/* 303 */         ptDst.setLocation(x, y);
/* 304 */         return ptDst;
/*     */       } 
/* 306 */       return new Point2D.Double(x, y);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\StereographicUSGS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */