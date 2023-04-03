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
/*     */ public class PolarStereographic extends Stereographic {
/*     */   private static final long serialVersionUID = -6635298308431138524L;
/*     */   
/*     */   private static final int MAXIMUM_ITERATIONS = 15;
/*     */   
/*     */   private static final double ITERATION_TOLERANCE = 1.0E-10D;
/*     */   
/*     */   private static final double EPSILON = 1.0E-8D;
/*     */   
/*     */   private final double k0;
/*     */   
/*     */   final double standardParallel;
/*     */   
/*     */   final boolean southPole;
/*     */   
/*     */   private final boolean poleForced;
/*     */   
/*     */   PolarStereographic(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor, Boolean forceSouthPole) throws ParameterNotFoundException {
/* 111 */     super(parameters, descriptor);
/* 129 */     ParameterDescriptor trueScaleDescriptor = Boolean.TRUE.equals(forceSouthPole) ? ProviderSouth.STANDARD_PARALLEL : ProviderNorth.STANDARD_PARALLEL;
/* 131 */     Collection<GeneralParameterDescriptor> expected = descriptor.descriptors();
/* 133 */     if (isExpectedParameter(expected, trueScaleDescriptor)) {
/* 135 */       latitudeTrueScale = doubleValue(expected, trueScaleDescriptor, parameters);
/*     */     } else {
/* 138 */       latitudeTrueScale = (this.latitudeOfOrigin < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */     } 
/* 140 */     ensureLatitudeInRange(trueScaleDescriptor, latitudeTrueScale, true);
/* 145 */     this.poleForced = (forceSouthPole != null);
/* 146 */     if (this.poleForced) {
/* 147 */       this.southPole = forceSouthPole.booleanValue();
/* 148 */       latitudeTrueScale = Math.abs(latitudeTrueScale);
/* 149 */       if (this.southPole)
/* 150 */         latitudeTrueScale = -latitudeTrueScale; 
/*     */     } else {
/* 153 */       this.southPole = (latitudeTrueScale < 0.0D);
/*     */     } 
/* 155 */     this.latitudeOfOrigin = this.southPole ? -1.5707963267948966D : 1.5707963267948966D;
/* 156 */     this.standardParallel = latitudeTrueScale;
/* 160 */     double latitudeTrueScale = Math.abs(latitudeTrueScale);
/* 161 */     if (Math.abs(latitudeTrueScale - 1.5707963267948966D) >= 1.0E-8D) {
/* 162 */       double t = Math.sin(latitudeTrueScale);
/* 163 */       this.k0 = msfn(t, Math.cos(latitudeTrueScale)) / tsfn(latitudeTrueScale, t);
/*     */     } else {
/* 167 */       this.k0 = 2.0D / Math.sqrt(Math.pow(1.0D + this.excentricity, 1.0D + this.excentricity) * Math.pow(1.0D - this.excentricity, 1.0D - this.excentricity));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 183 */     double sinlat = Math.sin(y);
/* 184 */     double coslon = Math.cos(x);
/* 185 */     double sinlon = Math.sin(x);
/* 186 */     if (this.southPole) {
/* 187 */       double rho = this.k0 * tsfn(-y, -sinlat);
/* 188 */       x = rho * sinlon;
/* 189 */       y = rho * coslon;
/*     */     } else {
/* 191 */       double rho = this.k0 * tsfn(y, sinlat);
/* 192 */       x = rho * sinlon;
/* 193 */       y = -rho * coslon;
/*     */     } 
/* 195 */     if (ptDst != null) {
/* 196 */       ptDst.setLocation(x, y);
/* 197 */       return ptDst;
/*     */     } 
/* 199 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 209 */     double rho = Math.hypot(x, y);
/* 210 */     if (this.southPole)
/* 211 */       y = -y; 
/* 216 */     double t = rho / this.k0;
/* 217 */     double halfe = this.excentricity / 2.0D;
/* 218 */     double phi0 = 0.0D;
/* 219 */     int i = 15;
/*     */     while (true) {
/* 220 */       double esinphi = this.excentricity * Math.sin(phi0);
/* 221 */       double phi = 1.5707963267948966D - 2.0D * Math.atan(t * Math.pow((1.0D - esinphi) / (1.0D + esinphi), halfe));
/* 222 */       if (Math.abs(phi - phi0) < 1.0E-10D) {
/* 223 */         x = (Math.abs(rho) < 1.0E-8D) ? 0.0D : Math.atan2(x, -y);
/* 224 */         y = this.southPole ? -phi : phi;
/*     */         break;
/*     */       } 
/* 227 */       phi0 = phi;
/* 228 */       if (--i < 0)
/* 229 */         throw new ProjectionException(129); 
/*     */     } 
/* 232 */     if (ptDst != null) {
/* 233 */       ptDst.setLocation(x, y);
/* 234 */       return ptDst;
/*     */     } 
/* 236 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 244 */     ParameterDescriptor<?> trueScaleDescriptor = this.poleForced ? (this.southPole ? ProviderSouth.STANDARD_PARALLEL : ProviderNorth.STANDARD_PARALLEL) : ProviderB.STANDARD_PARALLEL;
/* 248 */     ParameterValueGroup values = super.getParameterValues();
/* 249 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 250 */     set(expected, trueScaleDescriptor, values, this.standardParallel);
/* 251 */     return values;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 259 */     long code = Double.doubleToLongBits(this.k0);
/* 260 */     return ((int)code ^ (int)(code >>> 32L)) + 37 * super.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 268 */     if (object == this)
/* 270 */       return true; 
/* 272 */     if (super.equals(object)) {
/* 273 */       PolarStereographic that = (PolarStereographic)object;
/* 274 */       return (this.southPole == that.southPole && equals(this.k0, that.k0) && equals(this.standardParallel, that.standardParallel));
/*     */     } 
/* 278 */     return false;
/*     */   }
/*     */   
/*     */   static final class Spherical extends PolarStereographic {
/*     */     private static final long serialVersionUID = 1655096575897215547L;
/*     */     
/*     */     private final double k0;
/*     */     
/*     */     Spherical(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor, Boolean forceSouthPole) throws ParameterNotFoundException {
/* 319 */       super(parameters, descriptor, forceSouthPole);
/* 320 */       ensureSpherical();
/* 321 */       double phi = Math.abs(this.standardParallel);
/* 322 */       if (Math.abs(phi - 1.5707963267948966D) >= 1.0E-8D) {
/* 323 */         this.k0 = 1.0D + Math.sin(phi);
/*     */       } else {
/* 325 */         this.k0 = 2.0D;
/*     */       } 
/*     */     }
/*     */     
/*     */     protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 339 */       assert (ptDst = super.transformNormalized(x, y, ptDst)) != null;
/* 340 */       double coslat = Math.cos(y);
/* 341 */       double sinlat = Math.sin(y);
/* 342 */       double coslon = Math.cos(x);
/* 343 */       double sinlon = Math.sin(x);
/* 344 */       if (this.southPole) {
/* 345 */         if (Math.abs(1.0D - sinlat) < 1.0E-8D)
/* 346 */           throw new ProjectionException(202); 
/* 349 */         double f = this.k0 * coslat / (1.0D - sinlat);
/* 350 */         x = f * sinlon;
/* 351 */         y = f * coslon;
/*     */       } else {
/* 353 */         if (Math.abs(1.0D + sinlat) < 1.0E-8D)
/* 354 */           throw new ProjectionException(202); 
/* 357 */         double f = this.k0 * coslat / (1.0D + sinlat);
/* 358 */         x = f * sinlon;
/* 359 */         y = -f * coslon;
/*     */       } 
/* 361 */       assert checkTransform(x, y, ptDst);
/* 362 */       if (ptDst != null) {
/* 363 */         ptDst.setLocation(x, y);
/* 364 */         return ptDst;
/*     */       } 
/* 366 */       return new Point2D.Double(x, y);
/*     */     }
/*     */     
/*     */     protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 378 */       assert (ptDst = super.inverseTransformNormalized(x, y, ptDst)) != null;
/* 379 */       double rho = Math.hypot(x, y);
/* 380 */       if (!this.southPole)
/* 381 */         y = -y; 
/* 384 */       x = (Math.abs(x) < 1.0E-8D && Math.abs(y) < 1.0E-8D) ? 0.0D : Math.atan2(x, y);
/* 385 */       if (Math.abs(rho) < 1.0E-8D) {
/* 386 */         y = this.latitudeOfOrigin;
/*     */       } else {
/* 388 */         double c = 2.0D * Math.atan(rho / this.k0);
/* 389 */         double cosc = Math.cos(c);
/* 390 */         y = this.southPole ? Math.asin(-cosc) : Math.asin(cosc);
/*     */       } 
/* 393 */       assert checkInverseTransform(x, y, ptDst);
/* 394 */       if (ptDst != null) {
/* 395 */         ptDst.setLocation(x, y);
/* 396 */         return ptDst;
/*     */       } 
/* 398 */       return new Point2D.Double(x, y);
/*     */     }
/*     */   }
/*     */   
/*     */   static final class Series extends PolarStereographic {
/*     */     private static final long serialVersionUID = 2795404156883313290L;
/*     */     
/*     */     private final double A;
/*     */     
/*     */     private final double B;
/*     */     
/*     */     private double C;
/*     */     
/*     */     private double D;
/*     */     
/*     */     private final double k0;
/*     */     
/*     */     Series(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor, Boolean forceSouthPole) throws ParameterNotFoundException {
/* 457 */       super(parameters, descriptor, forceSouthPole);
/* 459 */       double e4 = this.excentricitySquared * this.excentricitySquared;
/* 460 */       double e6 = e4 * this.excentricitySquared;
/* 461 */       double e8 = e4 * e4;
/* 462 */       this.C = 0.058333333333333334D * e6 + 0.07232142857142858D * e8;
/* 463 */       this.D = 0.026531498015873015D * e8;
/* 464 */       this.A = this.excentricitySquared / 2.0D + 0.20833333333333334D * e4 + e6 / 12.0D + 0.03611111111111111D * e8 - this.C;
/* 465 */       this.B = 2.0D * (0.14583333333333334D * e4 + 0.12083333333333333D * e6 + 0.07039930555555556D * e8) - 4.0D * this.D;
/* 466 */       this.C *= 4.0D;
/* 467 */       this.D *= 8.0D;
/* 468 */       double latTrueScale = Math.abs(this.standardParallel);
/* 469 */       if (Math.abs(latTrueScale - 1.5707963267948966D) >= 1.0E-8D) {
/* 470 */         double t = Math.sin(latTrueScale);
/* 471 */         this.k0 = msfn(t, Math.cos(latTrueScale)) * Math.sqrt(Math.pow(1.0D + this.excentricity, 1.0D + this.excentricity) * Math.pow(1.0D - this.excentricity, 1.0D - this.excentricity)) / 2.0D * tsfn(latTrueScale, t);
/*     */       } else {
/* 476 */         this.k0 = 1.0D;
/*     */       } 
/*     */     }
/*     */     
/*     */     protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 489 */       assert (ptDst = super.inverseTransformNormalized(x, y, ptDst)) != null;
/* 490 */       double rho = Math.hypot(x, y);
/* 491 */       if (this.southPole)
/* 492 */         y = -y; 
/* 495 */       double t = rho / this.k0 * Math.sqrt(Math.pow(1.0D + this.excentricity, 1.0D + this.excentricity) * Math.pow(1.0D - this.excentricity, 1.0D - this.excentricity)) / 2.0D;
/* 497 */       double chi = 1.5707963267948966D - 2.0D * Math.atan(t);
/* 499 */       x = (Math.abs(rho) < 1.0E-8D) ? 0.0D : Math.atan2(x, -y);
/* 502 */       double sin2chi = Math.sin(2.0D * chi);
/* 503 */       double cos2chi = Math.cos(2.0D * chi);
/* 504 */       y = chi + sin2chi * (this.A + cos2chi * (this.B + cos2chi * (this.C + this.D * cos2chi)));
/* 505 */       y = this.southPole ? -y : y;
/* 507 */       assert checkInverseTransform(x, y, ptDst);
/* 508 */       if (ptDst != null) {
/* 509 */         ptDst.setLocation(x, y);
/* 510 */         return ptDst;
/*     */       } 
/* 512 */       return new Point2D.Double(x, y);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class ProviderA extends Stereographic.Provider {
/*     */     private static final long serialVersionUID = 9124091259039220308L;
/*     */     
/* 547 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Polar_Stereographic"), new NamedIdentifier(Citations.EPSG, "Polar Stereographic (variant A)"), new NamedIdentifier(Citations.EPSG, "9810"), new NamedIdentifier(Citations.GEOTIFF, "CT_PolarStereographic"), new NamedIdentifier(Citations.GEOTOOLS, Stereographic.Provider.NAME) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public ProviderA() {
/* 564 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 578 */       if (isSpherical(parameters))
/* 579 */         return (MathTransform)new PolarStereographic.Spherical(parameters, PARAMETERS, null); 
/* 581 */       return (MathTransform)new PolarStereographic.Series(parameters, PARAMETERS, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class ProviderB extends Stereographic.Provider {
/*     */     private static final long serialVersionUID = 5188231050523249971L;
/*     */     
/* 610 */     public static final ParameterDescriptor STANDARD_PARALLEL = PolarStereographic.ProviderNorth.STANDARD_PARALLEL;
/*     */     
/* 615 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Polar Stereographic (variant B)"), new NamedIdentifier(Citations.EPSG, "9829"), new NamedIdentifier(Citations.GEOTOOLS, Stereographic.Provider.NAME) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, STANDARD_PARALLEL, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public ProviderB() {
/* 629 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 643 */       if (isSpherical(parameters))
/* 644 */         return (MathTransform)new PolarStereographic.Spherical(parameters, PARAMETERS, null); 
/* 646 */       return (MathTransform)new PolarStereographic.Series(parameters, PARAMETERS, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class ProviderNorth extends Stereographic.Provider {
/*     */     private static final long serialVersionUID = 657493908431273866L;
/*     */     
/* 674 */     public static final ParameterDescriptor STANDARD_PARALLEL = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Standard_Parallel_1"), new NamedIdentifier(Citations.EPSG, "Latitude of standard parallel") }90.0D, -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 684 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Stereographic_North_Pole"), new NamedIdentifier(Citations.GEOTOOLS, Stereographic.Provider.NAME) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, STANDARD_PARALLEL, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public ProviderNorth() {
/* 697 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 711 */       if (isSpherical(parameters))
/* 712 */         return (MathTransform)new PolarStereographic.Spherical(parameters, PARAMETERS, Boolean.FALSE); 
/* 714 */       return (MathTransform)new PolarStereographic(parameters, PARAMETERS, Boolean.FALSE);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class ProviderSouth extends Stereographic.Provider {
/*     */     private static final long serialVersionUID = 6537800238416448564L;
/*     */     
/* 742 */     public static final ParameterDescriptor STANDARD_PARALLEL = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Standard_Parallel_1"), new NamedIdentifier(Citations.EPSG, "Latitude of standard parallel") }-90.0D, -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 752 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Stereographic_South_Pole"), new NamedIdentifier(Citations.GEOTOOLS, Stereographic.Provider.NAME) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, STANDARD_PARALLEL, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public ProviderSouth() {
/* 765 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 779 */       if (isSpherical(parameters))
/* 780 */         return (MathTransform)new PolarStereographic.Spherical(parameters, PARAMETERS, Boolean.TRUE); 
/* 782 */       return (MathTransform)new PolarStereographic(parameters, PARAMETERS, Boolean.TRUE);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\PolarStereographic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */