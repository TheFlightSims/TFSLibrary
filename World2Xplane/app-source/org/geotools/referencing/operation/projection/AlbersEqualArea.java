/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import org.geotools.measure.Latitude;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.ConicProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class AlbersEqualArea extends MapProjection {
/*     */   private static final long serialVersionUID = -3024658742514888646L;
/*     */   
/*     */   private static final int MAXIMUM_ITERATIONS = 15;
/*     */   
/*     */   private static final double ITERATION_TOLERANCE = 1.0E-10D;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   private final double n;
/*     */   
/*     */   private final double c;
/*     */   
/*     */   private final double rho0;
/*     */   
/*     */   private final double ec;
/*     */   
/*     */   private final double phi1;
/*     */   
/*     */   private double phi2;
/*     */   
/*     */   protected AlbersEqualArea(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 130 */     super(parameters);
/* 131 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 132 */     this.phi1 = doubleValue(expected, Provider.STANDARD_PARALLEL_1, parameters);
/* 133 */     ensureLatitudeInRange(Provider.STANDARD_PARALLEL_1, this.phi1, true);
/* 134 */     this.phi2 = doubleValue(expected, Provider.STANDARD_PARALLEL_2, parameters);
/* 135 */     if (Double.isNaN(this.phi2))
/* 136 */       this.phi2 = this.phi1; 
/* 138 */     ensureLatitudeInRange(Provider.STANDARD_PARALLEL_2, this.phi2, true);
/* 141 */     if (Math.abs(this.phi1 + this.phi2) < 1.0E-6D)
/* 142 */       throw new IllegalArgumentException(Errors.format(5, new Latitude(Math.toDegrees(this.phi1)), new Latitude(Math.toDegrees(this.phi2)))); 
/* 145 */     double sinphi = Math.sin(this.phi1);
/* 146 */     double cosphi = Math.cos(this.phi1);
/* 147 */     double n = sinphi;
/* 148 */     boolean secant = (Math.abs(this.phi1 - this.phi2) >= 1.0E-6D);
/* 149 */     if (this.isSpherical) {
/* 150 */       if (secant)
/* 151 */         n = 0.5D * (n + Math.sin(this.phi2)); 
/* 153 */       this.c = cosphi * cosphi + n * 2.0D * sinphi;
/* 154 */       this.rho0 = Math.sqrt(this.c - n * 2.0D * Math.sin(this.latitudeOfOrigin)) / n;
/* 155 */       this.ec = Double.NaN;
/*     */     } else {
/* 157 */       double m1 = msfn(sinphi, cosphi);
/* 158 */       double q1 = qsfn(sinphi);
/* 159 */       if (secant) {
/* 160 */         sinphi = Math.sin(this.phi2);
/* 161 */         cosphi = Math.cos(this.phi2);
/* 162 */         double m2 = msfn(sinphi, cosphi);
/* 163 */         double q2 = qsfn(sinphi);
/* 164 */         n = (m1 * m1 - m2 * m2) / (q2 - q1);
/*     */       } 
/* 166 */       this.c = m1 * m1 + n * q1;
/* 167 */       this.rho0 = Math.sqrt(this.c - n * qsfn(Math.sin(this.latitudeOfOrigin))) / n;
/* 168 */       this.ec = 1.0D - 0.5D * (1.0D - this.excentricitySquared) * Math.log((1.0D - this.excentricity) / (1.0D + this.excentricity)) / this.excentricity;
/*     */     } 
/* 171 */     this.n = n;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 178 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 186 */     ParameterValueGroup values = super.getParameterValues();
/* 187 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 188 */     set(expected, Provider.STANDARD_PARALLEL_1, values, this.phi1);
/* 189 */     set(expected, Provider.STANDARD_PARALLEL_2, values, this.phi2);
/* 190 */     return values;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 201 */     x *= this.n;
/* 203 */     if (this.isSpherical) {
/* 204 */       rho = this.c - this.n * 2.0D * Math.sin(y);
/*     */     } else {
/* 206 */       rho = this.c - this.n * qsfn(Math.sin(y));
/*     */     } 
/* 208 */     if (rho < 0.0D)
/* 209 */       if (rho > -1.0E-6D) {
/* 210 */         rho = 0.0D;
/*     */       } else {
/* 212 */         throw new ProjectionException(168);
/*     */       }  
/* 215 */     double rho = Math.sqrt(rho) / this.n;
/* 216 */     y = this.rho0 - rho * Math.cos(x);
/* 217 */     x = rho * Math.sin(x);
/* 219 */     if (ptDst != null) {
/* 220 */       ptDst.setLocation(x, y);
/* 221 */       return ptDst;
/*     */     } 
/* 223 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 233 */     y = this.rho0 - y;
/* 234 */     double rho = Math.hypot(x, y);
/* 235 */     if (rho > 1.0E-6D) {
/* 236 */       if (this.n < 0.0D) {
/* 237 */         rho = -rho;
/* 238 */         x = -x;
/* 239 */         y = -y;
/*     */       } 
/* 241 */       x = Math.atan2(x, y) / this.n;
/* 242 */       y = rho * this.n;
/* 243 */       if (this.isSpherical) {
/* 244 */         y = (this.c - y * y) / this.n * 2.0D;
/* 245 */         if (Math.abs(y) <= 1.0D) {
/* 246 */           y = Math.asin(y);
/*     */         } else {
/* 249 */           y = (y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */         } 
/*     */       } else {
/* 252 */         y = (this.c - y * y) / this.n;
/* 253 */         if (Math.abs(this.ec - Math.abs(y)) > 1.0E-6D) {
/* 254 */           y = phi1(y);
/*     */         } else {
/* 256 */           y = (y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 260 */       x = 0.0D;
/* 261 */       y = (this.n > 0.0D) ? 1.5707963267948966D : -1.5707963267948966D;
/*     */     } 
/* 263 */     if (ptDst != null) {
/* 264 */       ptDst.setLocation(x, y);
/* 265 */       return ptDst;
/*     */     } 
/* 267 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   private double phi1(double qs) throws ProjectionException {
/* 277 */     double tone_es = 1.0D - this.excentricitySquared;
/* 278 */     double phi = Math.asin(0.5D * qs);
/* 279 */     if (this.excentricity < 1.0E-6D)
/* 280 */       return phi; 
/* 282 */     for (int i = 0; i < 15; i++) {
/* 283 */       double sinpi = Math.sin(phi);
/* 284 */       double cospi = Math.cos(phi);
/* 285 */       double con = this.excentricity * sinpi;
/* 286 */       double com = 1.0D - con * con;
/* 287 */       double dphi = 0.5D * com * com / cospi * (qs / tone_es - sinpi / com + 0.5D / this.excentricity * Math.log((1.0D - con) / (1.0D + con)));
/* 289 */       phi += dphi;
/* 290 */       if (Math.abs(dphi) <= 1.0E-10D)
/* 291 */         return phi; 
/*     */     } 
/* 294 */     throw new ProjectionException(129);
/*     */   }
/*     */   
/*     */   private double qsfn(double sinphi) {
/* 304 */     double one_es = 1.0D - this.excentricitySquared;
/* 305 */     if (this.excentricity >= 1.0E-6D) {
/* 306 */       double con = this.excentricity * sinphi;
/* 307 */       return one_es * (sinphi / (1.0D - con * con) - 0.5D / this.excentricity * Math.log((1.0D - con) / (1.0D + con)));
/*     */     } 
/* 310 */     return sinphi + sinphi;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 319 */     long code = Double.doubleToLongBits(this.c);
/* 320 */     return ((int)code ^ (int)(code >>> 32L)) + 37 * super.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 328 */     if (object == this)
/* 330 */       return true; 
/* 332 */     if (super.equals(object)) {
/* 333 */       AlbersEqualArea that = (AlbersEqualArea)object;
/* 334 */       return (equals(this.n, that.n) && equals(this.c, that.c) && equals(this.rho0, that.rho0) && equals(this.phi1, that.phi1) && equals(this.phi2, that.phi2));
/*     */     } 
/* 340 */     return false;
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -7489679528438418778L;
/*     */     
/* 372 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Albers_Conic_Equal_Area"), new NamedIdentifier(Citations.EPSG, "Albers Equal Area"), new NamedIdentifier(Citations.EPSG, "9822"), new NamedIdentifier(Citations.GEOTIFF, "CT_AlbersEqualArea"), new NamedIdentifier(Citations.ESRI, "Albers"), new NamedIdentifier(Citations.ESRI, "Albers Equal Area Conic"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(3)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, STANDARD_PARALLEL_1, STANDARD_PARALLEL_2, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 392 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<ConicProjection> getOperationType() {
/* 400 */       return ConicProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 413 */       return (MathTransform)new AlbersEqualArea(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\AlbersEqualArea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */