/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import javax.measure.unit.NonSI;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class LambertAzimuthalEqualArea extends MapProjection {
/*     */   private static final long serialVersionUID = 1639914708790574760L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-7D;
/*     */   
/*     */   private static final double FINE_EPSILON = 1.0E-10D;
/*     */   
/*     */   private static final double EPSILON_LATITUDE = 1.0E-10D;
/*     */   
/*     */   private static final double P00 = 0.3333333333333333D;
/*     */   
/*     */   private static final double P01 = 0.17222222222222222D;
/*     */   
/*     */   private static final double P02 = 0.10257936507936508D;
/*     */   
/*     */   private static final double P10 = 0.06388888888888888D;
/*     */   
/*     */   private static final double P11 = 0.0664021164021164D;
/*     */   
/*     */   private static final double P20 = 0.016415012942191543D;
/*     */   
/*     */   static final int OBLIQUE = 0;
/*     */   
/*     */   static final int EQUATORIAL = 1;
/*     */   
/*     */   static final int NORTH_POLE = 2;
/*     */   
/*     */   static final int SOUTH_POLE = 3;
/*     */   
/*     */   final int mode;
/*     */   
/*     */   final double sinb1;
/*     */   
/*     */   final double cosb1;
/*     */   
/*     */   final double xmf;
/*     */   
/*     */   final double ymf;
/*     */   
/*     */   final double mmf;
/*     */   
/*     */   final double qp;
/*     */   
/*     */   final double dd;
/*     */   
/*     */   final double rq;
/*     */   
/*     */   private final double APA0;
/*     */   
/*     */   private final double APA1;
/*     */   
/*     */   private final double APA2;
/*     */   
/*     */   protected LambertAzimuthalEqualArea(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 104 */     super(parameters);
/* 105 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 106 */     this.latitudeOfOrigin = doubleValue(expected, Provider.LATITUDE_OF_CENTRE, parameters);
/* 107 */     this.centralMeridian = doubleValue(expected, Provider.LONGITUDE_OF_CENTRE, parameters);
/* 108 */     ensureLatitudeInRange(Provider.LATITUDE_OF_CENTRE, this.latitudeOfOrigin, true);
/* 109 */     ensureLongitudeInRange(Provider.LONGITUDE_OF_CENTRE, this.centralMeridian, true);
/* 113 */     double t = Math.abs(this.latitudeOfOrigin);
/* 114 */     if (Math.abs(t - 1.5707963267948966D) < 1.0E-10D) {
/* 115 */       this.mode = (this.latitudeOfOrigin < 0.0D) ? 3 : 2;
/* 116 */     } else if (Math.abs(t) < 1.0E-10D) {
/* 117 */       this.mode = 1;
/*     */     } else {
/* 119 */       this.mode = 0;
/*     */     } 
/* 124 */     double es2 = this.excentricitySquared * this.excentricitySquared;
/* 125 */     double es3 = this.excentricitySquared * es2;
/* 126 */     this.APA0 = 0.10257936507936508D * es3 + 0.17222222222222222D * es2 + 0.3333333333333333D * this.excentricitySquared;
/* 127 */     this.APA1 = 0.0664021164021164D * es3 + 0.06388888888888888D * es2;
/* 128 */     this.APA2 = 0.016415012942191543D * es3;
/* 131 */     this.qp = qsfn(1.0D);
/* 132 */     this.rq = Math.sqrt(0.5D * this.qp);
/* 133 */     this.mmf = 0.5D / (1.0D - this.excentricitySquared);
/* 134 */     double sinphi = Math.sin(this.latitudeOfOrigin);
/* 135 */     if (this.isSpherical) {
/* 136 */       this.sinb1 = Math.sin(this.latitudeOfOrigin);
/* 137 */       this.cosb1 = Math.cos(this.latitudeOfOrigin);
/*     */     } else {
/* 139 */       this.sinb1 = qsfn(sinphi) / this.qp;
/* 140 */       this.cosb1 = Math.sqrt(1.0D - this.sinb1 * this.sinb1);
/*     */     } 
/* 142 */     switch (this.mode) {
/*     */       case 2:
/*     */       case 3:
/* 145 */         this.dd = 1.0D;
/* 146 */         this.xmf = this.ymf = this.rq;
/*     */       case 1:
/* 150 */         this.dd = 1.0D / this.rq;
/* 151 */         this.xmf = 1.0D;
/* 152 */         this.ymf = 0.5D * this.qp;
/*     */         return;
/*     */       case 0:
/* 156 */         this.dd = Math.cos(this.latitudeOfOrigin) / Math.sqrt(1.0D - this.excentricitySquared * sinphi * sinphi) * this.rq * this.cosb1;
/* 158 */         this.xmf = this.rq * this.dd;
/* 159 */         this.ymf = this.rq / this.dd;
/*     */         return;
/*     */     } 
/* 163 */     throw new AssertionError(this.mode);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 172 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 180 */     ParameterValueGroup values = super.getParameterValues();
/* 181 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 182 */     set(expected, Provider.LATITUDE_OF_CENTRE, values, this.latitudeOfOrigin);
/* 183 */     set(expected, Provider.LONGITUDE_OF_CENTRE, values, this.centralMeridian);
/* 184 */     return values;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double lambda, double phi, Point2D ptDst) throws ProjectionException {
/* 195 */     double sinb, cosb, b, c, x, y, coslam = Math.cos(lambda);
/* 196 */     double sinlam = Math.sin(lambda);
/* 197 */     double sinphi = Math.sin(phi);
/* 198 */     double q = qsfn(sinphi);
/* 200 */     switch (this.mode) {
/*     */       case 0:
/* 202 */         sinb = q / this.qp;
/* 203 */         cosb = Math.sqrt(1.0D - sinb * sinb);
/* 204 */         c = 1.0D + this.sinb1 * sinb + this.cosb1 * cosb * coslam;
/* 205 */         b = Math.sqrt(2.0D / c);
/* 206 */         y = this.ymf * b * (this.cosb1 * sinb - this.sinb1 * cosb * coslam);
/* 207 */         x = this.xmf * b * cosb * sinlam;
/*     */         break;
/*     */       case 1:
/* 211 */         sinb = q / this.qp;
/* 212 */         cosb = Math.sqrt(1.0D - sinb * sinb);
/* 213 */         c = 1.0D + cosb * coslam;
/* 214 */         b = Math.sqrt(2.0D / c);
/* 215 */         y = this.ymf * b * sinb;
/* 216 */         x = this.xmf * b * cosb * sinlam;
/*     */         break;
/*     */       case 2:
/* 220 */         c = 1.5707963267948966D + phi;
/* 221 */         q = this.qp - q;
/* 222 */         if (q >= 0.0D) {
/* 223 */           b = Math.sqrt(q);
/* 224 */           x = b * sinlam;
/* 225 */           y = coslam * -b;
/*     */           break;
/*     */         } 
/* 227 */         x = y = 0.0D;
/*     */       case 3:
/* 232 */         c = phi - 1.5707963267948966D;
/* 233 */         q = this.qp + q;
/* 234 */         if (q >= 0.0D) {
/* 235 */           b = Math.sqrt(q);
/* 236 */           x = b * sinlam;
/* 237 */           y = coslam * b;
/*     */           break;
/*     */         } 
/* 239 */         x = y = 0.0D;
/*     */       default:
/* 244 */         throw new AssertionError(this.mode);
/*     */     } 
/* 247 */     if (Math.abs(c) < 1.0E-10D)
/* 248 */       throw new ProjectionException(168); 
/* 250 */     if (ptDst != null) {
/* 251 */       ptDst.setLocation(x, y);
/* 252 */       return ptDst;
/*     */     } 
/* 254 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*     */     double lambda;
/*     */     double phi;
/*     */     double rho;
/*     */     double q;
/*     */     double sCe;
/*     */     double ab;
/*     */     double cCe;
/*     */     double d1;
/* 267 */     switch (this.mode) {
/*     */       case 0:
/*     */       case 1:
/* 270 */         x /= this.dd;
/* 271 */         y *= this.dd;
/* 272 */         rho = Math.hypot(x, y);
/* 273 */         if (rho < 1.0E-10D) {
/* 274 */           double d2 = 0.0D;
/* 275 */           double d3 = this.latitudeOfOrigin;
/*     */           break;
/*     */         } 
/* 278 */         sCe = 2.0D * Math.asin(0.5D * rho / this.rq);
/* 279 */         cCe = Math.cos(sCe);
/* 280 */         sCe = Math.sin(sCe);
/* 281 */         x *= sCe;
/* 282 */         if (this.mode == 0) {
/* 283 */           d1 = cCe * this.sinb1 + y * sCe * this.cosb1 / rho;
/* 284 */           double d = this.qp * d1;
/* 285 */           y = rho * this.cosb1 * cCe - y * this.sinb1 * sCe;
/*     */         } else {
/* 287 */           d1 = y * sCe / rho;
/* 288 */           double d = this.qp * d1;
/* 289 */           y = rho * cCe;
/*     */         } 
/* 291 */         lambda = Math.atan2(x, y);
/* 292 */         phi = authlat(Math.asin(d1));
/*     */         break;
/*     */       case 2:
/* 297 */         y = -y;
/*     */       case 3:
/* 301 */         q = x * x + y * y;
/* 302 */         if (q == 0.0D) {
/* 303 */           lambda = 0.0D;
/* 304 */           phi = this.latitudeOfOrigin;
/*     */           break;
/*     */         } 
/* 306 */         ab = 1.0D - q / this.qp;
/* 307 */         if (this.mode == 3)
/* 308 */           ab = -ab; 
/* 310 */         lambda = Math.atan2(x, y);
/* 311 */         phi = authlat(Math.asin(ab));
/*     */         break;
/*     */       default:
/* 316 */         throw new AssertionError(this.mode);
/*     */     } 
/* 319 */     if (ptDst != null) {
/* 320 */       ptDst.setLocation(lambda, phi);
/* 321 */       return ptDst;
/*     */     } 
/* 323 */     return new Point2D.Double(lambda, phi);
/*     */   }
/*     */   
/*     */   private static final class Spherical extends LambertAzimuthalEqualArea {
/*     */     private static final long serialVersionUID = 2091431369806844342L;
/*     */     
/*     */     protected Spherical(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 348 */       super(parameters);
/* 349 */       ensureSpherical();
/*     */     }
/*     */     
/*     */     protected Point2D transformNormalized(double lambda, double phi, Point2D ptDst) throws ProjectionException {
/*     */       double x, y;
/* 362 */       assert (ptDst = super.transformNormalized(lambda, phi, ptDst)) != null;
/* 364 */       double sinphi = Math.sin(phi);
/* 365 */       double cosphi = Math.cos(phi);
/* 366 */       double coslam = Math.cos(lambda);
/* 368 */       switch (this.mode) {
/*     */         case 1:
/* 370 */           y = 1.0D + cosphi * coslam;
/* 371 */           if (y <= 1.0E-10D)
/* 372 */             throw new ProjectionException(168); 
/* 374 */           y = Math.sqrt(2.0D / y);
/* 375 */           x = y * cosphi * Math.sin(lambda);
/* 376 */           y *= sinphi;
/*     */           break;
/*     */         case 0:
/* 380 */           y = 1.0D + this.sinb1 * sinphi + this.cosb1 * cosphi * coslam;
/* 381 */           if (y <= 1.0E-10D)
/* 382 */             throw new ProjectionException(168); 
/* 384 */           y = Math.sqrt(2.0D / y);
/* 385 */           x = y * cosphi * Math.sin(lambda);
/* 386 */           y *= this.cosb1 * sinphi - this.sinb1 * cosphi * coslam;
/*     */           break;
/*     */         case 2:
/* 390 */           if (Math.abs(phi + this.latitudeOfOrigin) < 1.0E-10D)
/* 391 */             throw new ProjectionException(168); 
/* 393 */           y = 0.7853981633974483D - phi * 0.5D;
/* 394 */           y = 2.0D * Math.sin(y);
/* 395 */           x = y * Math.sin(lambda);
/* 396 */           y *= -coslam;
/*     */           break;
/*     */         case 3:
/* 400 */           if (Math.abs(phi + this.latitudeOfOrigin) < 1.0E-10D)
/* 401 */             throw new ProjectionException(168); 
/* 403 */           y = 0.7853981633974483D - phi * 0.5D;
/* 404 */           y = 2.0D * Math.cos(y);
/* 405 */           x = y * Math.sin(lambda);
/* 406 */           y *= coslam;
/*     */           break;
/*     */         default:
/* 410 */           throw new AssertionError(this.mode);
/*     */       } 
/* 413 */       assert checkTransform(x, y, ptDst);
/* 414 */       if (ptDst != null) {
/* 415 */         ptDst.setLocation(x, y);
/* 416 */         return ptDst;
/*     */       } 
/* 418 */       return new Point2D.Double(x, y);
/*     */     }
/*     */     
/*     */     protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*     */       double lambda, sinz, cosz;
/* 430 */       assert (ptDst = super.inverseTransformNormalized(x, y, ptDst)) != null;
/* 433 */       double rh = Math.hypot(x, y);
/* 434 */       double phi = rh * 0.5D;
/* 435 */       if (phi > 1.0D)
/* 436 */         throw new ProjectionException(168); 
/* 438 */       phi = 2.0D * Math.asin(phi);
/* 439 */       switch (this.mode) {
/*     */         case 1:
/* 441 */           sinz = Math.sin(phi);
/* 442 */           cosz = Math.cos(phi);
/* 443 */           phi = (Math.abs(rh) <= 1.0E-10D) ? 0.0D : Math.asin(y * sinz / rh);
/* 444 */           x *= sinz;
/* 445 */           y = cosz * rh;
/* 446 */           lambda = (y == 0.0D) ? 0.0D : Math.atan2(x, y);
/*     */           break;
/*     */         case 0:
/* 450 */           sinz = Math.sin(phi);
/* 451 */           cosz = Math.cos(phi);
/* 452 */           phi = (Math.abs(rh) <= 1.0E-10D) ? this.latitudeOfOrigin : Math.asin(cosz * this.sinb1 + y * sinz * this.cosb1 / rh);
/* 454 */           x *= sinz * this.cosb1;
/* 455 */           y = (cosz - Math.sin(phi) * this.sinb1) * rh;
/* 456 */           lambda = (y == 0.0D) ? 0.0D : Math.atan2(x, y);
/*     */           break;
/*     */         case 2:
/* 460 */           phi = 1.5707963267948966D - phi;
/* 461 */           lambda = Math.atan2(x, -y);
/*     */           break;
/*     */         case 3:
/* 465 */           phi -= 1.5707963267948966D;
/* 466 */           lambda = Math.atan2(x, y);
/*     */           break;
/*     */         default:
/* 470 */           throw new AssertionError(this.mode);
/*     */       } 
/* 473 */       assert checkInverseTransform(lambda, phi, ptDst);
/* 474 */       if (ptDst != null) {
/* 475 */         ptDst.setLocation(lambda, phi);
/* 476 */         return ptDst;
/*     */       } 
/* 478 */       return new Point2D.Double(lambda, phi);
/*     */     }
/*     */   }
/*     */   
/*     */   private double qsfn(double sinphi) {
/* 489 */     if (this.excentricity >= 1.0E-7D) {
/* 490 */       double con = this.excentricity * sinphi;
/* 491 */       return (1.0D - this.excentricitySquared) * (sinphi / (1.0D - con * con) - 0.5D / this.excentricity * Math.log((1.0D - con) / (1.0D + con)));
/*     */     } 
/* 494 */     return sinphi + sinphi;
/*     */   }
/*     */   
/*     */   private double authlat(double beta) {
/* 502 */     double t = beta + beta;
/* 503 */     return beta + this.APA0 * Math.sin(t) + this.APA1 * Math.sin(t + t) + this.APA2 * Math.sin(t + t + t);
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 3877793025552244132L;
/*     */     
/* 538 */     public static final ParameterDescriptor LATITUDE_OF_CENTRE = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "latitude_of_center"), new NamedIdentifier(Citations.EPSG, "Latitude of natural origin"), new NamedIdentifier(Citations.EPSG, "Spherical latitude of origin"), new NamedIdentifier(Citations.ESRI, "Latitude_Of_Origin"), new NamedIdentifier(Citations.GEOTIFF, "ProjCenterLat") }0.0D, -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 552 */     public static final ParameterDescriptor LONGITUDE_OF_CENTRE = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "longitude_of_center"), new NamedIdentifier(Citations.EPSG, "Longitude of natural origin"), new NamedIdentifier(Citations.EPSG, "Spherical longitude of origin"), new NamedIdentifier(Citations.ESRI, "Central_Meridian"), new NamedIdentifier(Citations.GEOTIFF, "ProjCenterLong") }0.0D, -180.0D, 180.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 565 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Lambert_Azimuthal_Equal_Area"), new NamedIdentifier(Citations.EPSG, "Lambert Azimuthal Equal Area"), new NamedIdentifier(Citations.EPSG, "Lambert Azimuthal Equal Area (Spherical)"), new NamedIdentifier(Citations.GEOTIFF, "CT_LambertAzimEqualArea"), new NamedIdentifier(Citations.EPSG, "9820") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, LATITUDE_OF_CENTRE, LONGITUDE_OF_CENTRE, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 581 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 594 */       return isSpherical(parameters) ? (MathTransform)new LambertAzimuthalEqualArea.Spherical(parameters) : (MathTransform)new LambertAzimuthalEqualArea(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\LambertAzimuthalEqualArea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */