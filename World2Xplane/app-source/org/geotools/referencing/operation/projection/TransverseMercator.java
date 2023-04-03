/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.operation.transform.ConcatenatedTransform;
/*     */ import org.geotools.referencing.operation.transform.ProjectiveTransform;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.CylindricalProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class TransverseMercator extends MapProjection {
/*     */   private static final int MAXIMUM_ITERATIONS = 15;
/*     */   
/*     */   private static final double ITERATION_TOLERANCE = 1.0E-11D;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   private static final double EPSILON_LATITUDE = 1.0E-10D;
/*     */   
/*     */   private final double esp;
/*     */   
/*     */   private final double ml0;
/*     */   
/*     */   private static final double FC1 = 1.0D;
/*     */   
/*     */   private static final double FC2 = 0.5D;
/*     */   
/*     */   private static final double FC3 = 0.16666666666666666D;
/*     */   
/*     */   private static final double FC4 = 0.08333333333333333D;
/*     */   
/*     */   private static final double FC5 = 0.05D;
/*     */   
/*     */   private static final double FC6 = 0.03333333333333333D;
/*     */   
/*     */   private static final double FC7 = 0.023809523809523808D;
/*     */   
/*     */   private static final double FC8 = 0.017857142857142856D;
/*     */   
/*     */   protected TransverseMercator(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 159 */     super(parameters);
/* 162 */     this.esp = this.excentricitySquared / (1.0D - this.excentricitySquared);
/* 163 */     this.ml0 = mlfn(this.latitudeOfOrigin, Math.sin(this.latitudeOfOrigin), Math.cos(this.latitudeOfOrigin));
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 170 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 181 */     double sinphi = Math.sin(y);
/* 182 */     double cosphi = Math.cos(y);
/* 184 */     double t = (Math.abs(cosphi) > 1.0E-6D) ? (sinphi / cosphi) : 0.0D;
/* 185 */     t *= t;
/* 186 */     double al = cosphi * x;
/* 187 */     double als = al * al;
/* 188 */     al /= Math.sqrt(1.0D - this.excentricitySquared * sinphi * sinphi);
/* 189 */     double n = this.esp * cosphi * cosphi;
/* 192 */     y = mlfn(y, sinphi, cosphi) - this.ml0 + sinphi * al * x * 0.5D * (1.0D + 0.08333333333333333D * als * (5.0D - t + n * (9.0D + 4.0D * n) + 0.03333333333333333D * als * (61.0D + t * (t - 58.0D) + n * (270.0D - 330.0D * t) + 0.017857142857142856D * als * (1385.0D + t * (t * (543.0D - t) - 3111.0D)))));
/* 199 */     x = al * (1.0D + 0.16666666666666666D * als * (1.0D - t + n + 0.05D * als * (5.0D + t * (t - 18.0D) + n * (14.0D - 58.0D * t) + 0.023809523809523808D * als * (61.0D + t * (t * (179.0D - t) - 479.0D)))));
/* 203 */     if (ptDst != null) {
/* 204 */       ptDst.setLocation(x, y);
/* 205 */       return ptDst;
/*     */     } 
/* 207 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 217 */     double phi = inv_mlfn(this.ml0 + y);
/* 219 */     if (Math.abs(phi) >= 1.5707963267948966D) {
/* 220 */       y = (y < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/* 221 */       x = 0.0D;
/*     */     } else {
/* 223 */       double sinphi = Math.sin(phi);
/* 224 */       double cosphi = Math.cos(phi);
/* 225 */       double t = (Math.abs(cosphi) > 1.0E-6D) ? (sinphi / cosphi) : 0.0D;
/* 226 */       double n = this.esp * cosphi * cosphi;
/* 227 */       double con = 1.0D - this.excentricitySquared * sinphi * sinphi;
/* 228 */       double d = x * Math.sqrt(con);
/* 229 */       con *= t;
/* 230 */       t *= t;
/* 231 */       double ds = d * d;
/* 233 */       y = phi - con * ds / (1.0D - this.excentricitySquared) * 0.5D * (1.0D - ds * 0.08333333333333333D * (5.0D + t * (3.0D - 9.0D * n) + n * (1.0D - 4.0D * n) - ds * 0.03333333333333333D * (61.0D + t * (90.0D - 252.0D * n + 45.0D * t) + 46.0D * n - ds * 0.017857142857142856D * (1385.0D + t * (3633.0D + t * (4095.0D + 1574.0D * t))))));
/* 239 */       x = d * (1.0D - ds * 0.16666666666666666D * (1.0D + 2.0D * t + n - ds * 0.05D * (5.0D + t * (28.0D + 24.0D * t + 8.0D * n) + 6.0D * n - ds * 0.023809523809523808D * (61.0D + t * (662.0D + t * (1320.0D + 720.0D * t)))))) / cosphi;
/*     */     } 
/* 244 */     if (ptDst != null) {
/* 245 */       ptDst.setLocation(x, y);
/* 246 */       return ptDst;
/*     */     } 
/* 248 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected double getToleranceForAssertions(double longitude, double latitude) {
/* 256 */     if (Math.abs(longitude - this.centralMeridian) > 0.26D)
/* 258 */       return 2.5D; 
/* 259 */     if (Math.abs(longitude - this.centralMeridian) > 0.22D)
/* 260 */       return 1.0D; 
/* 261 */     if (Math.abs(longitude - this.centralMeridian) > 0.17D)
/* 262 */       return 0.5D; 
/* 263 */     if (Math.abs(latitude - this.latitudeOfOrigin) < 1.0E-5D)
/* 266 */       return 0.01D; 
/* 270 */     return 1.0E-6D;
/*     */   }
/*     */   
/*     */   private static final class Spherical extends TransverseMercator {
/*     */     protected Spherical(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 293 */       super(parameters);
/* 294 */       ensureSpherical();
/*     */     }
/*     */     
/*     */     protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 305 */       double normalizedLongitude = x;
/* 306 */       assert (ptDst = super.transformNormalized(x, y, ptDst)) != null;
/* 308 */       double cosphi = Math.cos(y);
/* 309 */       double b = cosphi * Math.sin(x);
/* 310 */       if (Math.abs(Math.abs(b) - 1.0D) <= 1.0E-6D)
/* 311 */         throw new ProjectionException(202); 
/* 316 */       y = Math.atan2(Math.tan(y), Math.cos(x)) - this.latitudeOfOrigin;
/* 317 */       x = 0.5D * Math.log((1.0D + b) / (1.0D - b));
/* 319 */       assert checkTransform(x, y, ptDst, getToleranceForSphereAssertions(normalizedLongitude));
/* 320 */       if (ptDst != null) {
/* 321 */         ptDst.setLocation(x, y);
/* 322 */         return ptDst;
/*     */       } 
/* 324 */       return new Point2D.Double(x, y);
/*     */     }
/*     */     
/*     */     protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 335 */       assert (ptDst = super.inverseTransformNormalized(x, y, ptDst)) != null;
/* 337 */       double sinhX = Math.sinh(x);
/* 338 */       double cosD = Math.cos(this.latitudeOfOrigin + y);
/* 339 */       double phi = Math.asin(Math.sqrt((1.0D - cosD * cosD) / (1.0D + sinhX * sinhX)));
/* 341 */       y = (y + this.latitudeOfOrigin < 0.0D) ? -phi : phi;
/* 342 */       x = (Math.abs(sinhX) <= 1.0E-6D && Math.abs(cosD) <= 1.0E-6D) ? 0.0D : Math.atan2(sinhX, cosD);
/* 344 */       assert checkInverseTransform(x, y, ptDst, getToleranceForSphereAssertions(x));
/* 345 */       if (ptDst != null) {
/* 346 */         ptDst.setLocation(x, y);
/* 347 */         return ptDst;
/*     */       } 
/* 349 */       return new Point2D.Double(x, y);
/*     */     }
/*     */     
/*     */     protected double getToleranceForSphereAssertions(double longitude) {
/* 368 */       if (Math.abs(Math.abs(longitude) - 1.5707963267948966D) < 1.0E-10D)
/* 370 */         return 1.0E18D; 
/* 372 */       if (Math.abs(longitude) > 0.26D)
/* 374 */         return 1000000.0D; 
/* 377 */       return 1.0E-6D;
/*     */     }
/*     */   }
/*     */   
/*     */   private int getZone(double centralLongitudeZone1, double zoneWidth) {
/* 406 */     double zoneCount = Math.abs(360.0D / zoneWidth);
/* 408 */     double t = centralLongitudeZone1 - 0.5D * zoneWidth;
/* 409 */     t = Math.toDegrees(this.centralMeridian) - t;
/* 410 */     t = Math.floor(t / zoneWidth + 1.0E-6D);
/* 411 */     t -= zoneCount * Math.floor(t / zoneCount);
/* 412 */     return (int)t + 1;
/*     */   }
/*     */   
/*     */   private double getCentralMedirian(double centralLongitudeZone1, double zoneWidth) {
/* 431 */     double t = centralLongitudeZone1 + (getZone(centralLongitudeZone1, zoneWidth) - 1) * zoneWidth;
/* 432 */     t -= 360.0D * Math.floor((t + 180.0D) / 360.0D);
/* 433 */     return t;
/*     */   }
/*     */   
/*     */   public int getZone() {
/* 445 */     if (this.scaleFactor == 0.9996D && this.falseEasting == 500000.0D)
/* 446 */       return getZone(-177.0D, 6.0D); 
/* 449 */     if (this.scaleFactor == 0.9999D && this.falseEasting == 304800.0D)
/* 450 */       return getZone(-52.5D, -3.0D); 
/* 453 */     throw new IllegalStateException(Errors.format(186));
/*     */   }
/*     */   
/*     */   public double getCentralMeridian() {
/* 467 */     if (this.scaleFactor == 0.9996D && this.falseEasting == 500000.0D)
/* 468 */       return getCentralMedirian(-177.0D, 6.0D); 
/* 471 */     if (this.scaleFactor == 0.9999D && this.falseEasting == 304800.0D)
/* 472 */       return getCentralMedirian(-52.5D, -3.0D); 
/* 475 */     throw new IllegalStateException(Errors.format(186));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 483 */     long code = Double.doubleToLongBits(this.ml0);
/* 484 */     return ((int)code ^ (int)(code >>> 32L)) + 37 * super.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 492 */     if (object == this)
/* 494 */       return true; 
/* 497 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     static ParameterDescriptorGroup createDescriptorGroup(ReferenceIdentifier[] identifiers) {
/* 528 */       return createDescriptorGroup(identifiers, (GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     }
/*     */     
/* 539 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Transverse_Mercator"), new NamedIdentifier(Citations.EPSG, "Transverse Mercator"), new NamedIdentifier(Citations.EPSG, "Gauss-Kruger"), new NamedIdentifier(Citations.EPSG, "9807"), new NamedIdentifier(Citations.GEOTIFF, "CT_TransverseMercator"), new NamedIdentifier(Citations.ESRI, "Transverse_Mercator"), new NamedIdentifier(Citations.ESRI, "Gauss_Kruger"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(225)) });
/*     */     
/*     */     public Provider() {
/* 555 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     Provider(ParameterDescriptorGroup descriptor) {
/* 562 */       super(descriptor);
/*     */     }
/*     */     
/*     */     public Class<CylindricalProjection> getOperationType() {
/* 570 */       return CylindricalProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 583 */       if (isSpherical(parameters))
/* 584 */         return (MathTransform)new TransverseMercator.Spherical(parameters); 
/* 586 */       return (MathTransform)new TransverseMercator(parameters);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider_SouthOrientated extends Provider {
/*     */     public Provider_SouthOrientated() {
/* 635 */       super(createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Transverse Mercator (South Orientated)"), new NamedIdentifier(Citations.EPSG, "9808") }));
/*     */     }
/*     */     
/*     */     public MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 652 */       MapProjection projection = (MapProjection)super.createMathTransform(parameters);
/* 653 */       if (projection.falseEasting == 0.0D && projection.falseNorthing == 0.0D)
/* 654 */         return (MathTransform)projection; 
/* 656 */       AffineTransform step = AffineTransform.getTranslateInstance(-2.0D * projection.falseEasting, -2.0D * projection.falseNorthing);
/* 658 */       return ConcatenatedTransform.create((MathTransform)ProjectiveTransform.create(step), (MathTransform)projection);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\TransverseMercator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */