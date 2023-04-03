/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.operation.transform.AffineTransform2D;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.ConicProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class Krovak extends MapProjection {
/*     */   private static final long serialVersionUID = -8359105634355342212L;
/*     */   
/*     */   private static final int MAXIMUM_ITERATIONS = 15;
/*     */   
/*     */   private static final double ITERATION_TOLERANCE = 1.0E-11D;
/*     */   
/*     */   protected final double azimuth;
/*     */   
/*     */   protected double x_scale;
/*     */   
/*     */   protected double y_scale;
/*     */   
/*     */   protected double xy_plane_rotation;
/*     */   
/*     */   boolean esriDefinition;
/*     */   
/* 148 */   private MathTransform axisTransform = null;
/*     */   
/*     */   protected final double pseudoStandardParallel;
/*     */   
/*     */   private final double sinAzim;
/*     */   
/*     */   private final double cosAzim;
/*     */   
/*     */   private final double n;
/*     */   
/*     */   private final double tanS2;
/*     */   
/*     */   private final double alfa;
/*     */   
/*     */   private final double hae;
/*     */   
/*     */   private final double k1;
/*     */   
/*     */   private final double ka;
/*     */   
/*     */   private final double ro0;
/*     */   
/*     */   private final double rop;
/*     */   
/*     */   private static final double s45 = 0.785398163397448D;
/*     */   
/*     */   protected Krovak(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 171 */     this(parameters, true);
/*     */   }
/*     */   
/*     */   protected Krovak(ParameterValueGroup parameters, boolean esriDefinition) throws ParameterNotFoundException {
/* 181 */     super(parameters);
/* 182 */     this.esriDefinition = true;
/* 183 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 185 */     this.latitudeOfOrigin = doubleValue(expected, Provider.LATITUDE_OF_CENTER, parameters);
/* 186 */     this.centralMeridian = doubleValue(expected, Provider.LONGITUDE_OF_CENTER, parameters);
/* 187 */     this.azimuth = doubleValue(expected, Provider.AZIMUTH, parameters);
/* 188 */     this.pseudoStandardParallel = doubleValue(expected, Provider.PSEUDO_STANDARD_PARALLEL, parameters);
/* 189 */     this.scaleFactor = doubleValue(expected, Provider.SCALE_FACTOR, parameters);
/* 190 */     this.x_scale = doubleValue(expected, Provider.X_SCALE, parameters);
/* 191 */     this.y_scale = doubleValue(expected, Provider.Y_SCALE, parameters);
/* 192 */     this.xy_plane_rotation = doubleValue(expected, Provider.XY_PLANE_ROTATION, parameters);
/* 199 */     if (Double.isNaN(doubleValue(expected, Provider.X_SCALE, parameters)) && Double.isNaN(doubleValue(expected, Provider.Y_SCALE, parameters)) && Double.isNaN(doubleValue(expected, Provider.XY_PLANE_ROTATION, parameters))) {
/* 202 */       this.esriDefinition = false;
/*     */     } else {
/* 205 */       this.axisTransform = createAffineTransform(this.x_scale, this.y_scale, this.xy_plane_rotation);
/*     */     } 
/* 207 */     ensureLatitudeInRange(Provider.LATITUDE_OF_CENTER, this.latitudeOfOrigin, false);
/* 208 */     ensureLongitudeInRange(Provider.LONGITUDE_OF_CENTER, this.centralMeridian, false);
/* 211 */     this.sinAzim = Math.sin(this.azimuth);
/* 212 */     this.cosAzim = Math.cos(this.azimuth);
/* 213 */     this.n = Math.sin(this.pseudoStandardParallel);
/* 214 */     this.tanS2 = Math.tan(this.pseudoStandardParallel / 2.0D + 0.785398163397448D);
/* 217 */     double sinLat = Math.sin(this.latitudeOfOrigin);
/* 218 */     double cosLat = Math.cos(this.latitudeOfOrigin);
/* 219 */     double cosL2 = cosLat * cosLat;
/* 220 */     this.alfa = Math.sqrt(1.0D + this.excentricitySquared * cosL2 * cosL2 / (1.0D - this.excentricitySquared));
/* 221 */     this.hae = this.alfa * this.excentricity / 2.0D;
/* 222 */     double u0 = Math.asin(sinLat / this.alfa);
/* 225 */     double esl = this.excentricity * sinLat;
/* 226 */     double g = Math.pow((1.0D - esl) / (1.0D + esl), this.alfa * this.excentricity / 2.0D);
/* 227 */     this.k1 = Math.pow(Math.tan(this.latitudeOfOrigin / 2.0D + 0.785398163397448D), this.alfa) * g / Math.tan(u0 / 2.0D + 0.785398163397448D);
/* 228 */     this.ka = Math.pow(1.0D / this.k1, -1.0D / this.alfa);
/* 231 */     double radius = Math.sqrt(1.0D - this.excentricitySquared) / (1.0D - this.excentricitySquared * sinLat * sinLat);
/* 233 */     this.ro0 = this.scaleFactor * radius / Math.tan(this.pseudoStandardParallel);
/* 234 */     this.rop = this.ro0 * Math.pow(this.tanS2, this.n);
/*     */   }
/*     */   
/*     */   private MathTransform createAffineTransform(double x_scale, double y_scale, double xy_plane_rotation) {
/* 241 */     double a00 = x_scale * Math.cos(xy_plane_rotation);
/* 242 */     double a01 = -y_scale * Math.sin(xy_plane_rotation);
/* 243 */     double a10 = x_scale * Math.sin(xy_plane_rotation);
/* 244 */     double a11 = y_scale * Math.cos(xy_plane_rotation);
/* 245 */     AffineTransform at = new AffineTransform(a00, a10, a01, a11, 0.0D, 0.0D);
/* 246 */     return (MathTransform)new AffineTransform2D(at);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 253 */     return this.esriDefinition ? Provider.PARAMETERS_ESRI : Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 262 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 263 */     ParameterValueGroup values = super.getParameterValues();
/* 264 */     set(expected, Provider.LATITUDE_OF_CENTER, values, this.latitudeOfOrigin);
/* 265 */     set(expected, Provider.LONGITUDE_OF_CENTER, values, this.centralMeridian);
/* 266 */     set(expected, Provider.AZIMUTH, values, this.azimuth);
/* 267 */     set(expected, Provider.PSEUDO_STANDARD_PARALLEL, values, this.pseudoStandardParallel);
/* 268 */     set(expected, Provider.SCALE_FACTOR, values, this.scaleFactor);
/* 270 */     set(expected, Provider.X_SCALE, values, this.x_scale);
/* 271 */     set(expected, Provider.Y_SCALE, values, this.y_scale);
/* 272 */     set(expected, Provider.XY_PLANE_ROTATION, values, this.xy_plane_rotation);
/* 273 */     return values;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double lambda, double phi, Point2D ptDst) throws ProjectionException {
/* 284 */     double esp = this.excentricity * Math.sin(phi);
/* 285 */     double gfi = Math.pow((1.0D - esp) / (1.0D + esp), this.hae);
/* 286 */     double u = 2.0D * (Math.atan(Math.pow(Math.tan(phi / 2.0D + 0.785398163397448D), this.alfa) / this.k1 * gfi) - 0.785398163397448D);
/* 287 */     double deltav = -lambda * this.alfa;
/* 288 */     double cosU = Math.cos(u);
/* 289 */     double s = Math.asin(this.cosAzim * Math.sin(u) + this.sinAzim * cosU * Math.cos(deltav));
/* 290 */     double d = Math.asin(cosU * Math.sin(deltav) / Math.cos(s));
/* 291 */     double eps = this.n * d;
/* 292 */     double ro = this.rop / Math.pow(Math.tan(s / 2.0D + 0.785398163397448D), this.n);
/* 295 */     double y = -(ro * Math.cos(eps));
/* 296 */     double x = -(ro * Math.sin(eps));
/* 298 */     double[] result = { x, y };
/* 302 */     if (this.axisTransform != null)
/*     */       try {
/* 304 */         this.axisTransform.transform(new double[] { x, y }, 0, result, 0, 1);
/* 305 */       } catch (TransformException e) {
/* 306 */         throw new ProjectionException(e);
/*     */       }  
/* 310 */     if (ptDst != null) {
/* 311 */       ptDst.setLocation(result[0], result[1]);
/* 312 */       return ptDst;
/*     */     } 
/* 314 */     return new Point2D.Double(result[0], result[1]);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 325 */     double[] result = { x, y };
/* 329 */     if (this.axisTransform != null)
/*     */       try {
/* 331 */         this.axisTransform.transform(new double[] { x, y }, 0, result, 0, 1);
/* 332 */       } catch (TransformException e) {
/* 333 */         throw new ProjectionException(e);
/*     */       }  
/* 337 */     double ro = Math.hypot(result[0], result[1]);
/* 338 */     double eps = Math.atan2(-result[0], -result[1]);
/* 339 */     double d = eps / this.n;
/* 340 */     double s = 2.0D * (Math.atan(Math.pow(this.ro0 / ro, 1.0D / this.n) * this.tanS2) - 0.785398163397448D);
/* 341 */     double cs = Math.cos(s);
/* 342 */     double u = Math.asin(this.cosAzim * Math.sin(s) - this.sinAzim * cs * Math.cos(d));
/* 343 */     double kau = this.ka * Math.pow(Math.tan(u / 2.0D + 0.785398163397448D), 1.0D / this.alfa);
/* 344 */     double deltav = Math.asin(cs * Math.sin(d) / Math.cos(u));
/* 345 */     double lambda = -deltav / this.alfa;
/* 346 */     double phi = 0.0D;
/* 347 */     double fi1 = u;
/* 350 */     int i = 15;
/*     */     while (true) {
/* 351 */       fi1 = phi;
/* 352 */       double esf = this.excentricity * Math.sin(fi1);
/* 353 */       phi = 2.0D * (Math.atan(kau * Math.pow((1.0D + esf) / (1.0D - esf), this.excentricity / 2.0D)) - 0.785398163397448D);
/* 354 */       if (Math.abs(fi1 - phi) <= 1.0E-11D)
/*     */         break; 
/* 357 */       if (--i < 0)
/* 358 */         throw new ProjectionException(129); 
/*     */     } 
/* 361 */     if (ptDst != null) {
/* 362 */       ptDst.setLocation(lambda, phi);
/* 363 */       return ptDst;
/*     */     } 
/* 365 */     return new Point2D.Double(lambda, phi);
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -278392856661204734L;
/*     */     
/* 400 */     public static final ParameterDescriptor LATITUDE_OF_CENTER = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "latitude_of_center"), new NamedIdentifier(Citations.EPSG, "Latitude of projection centre"), new NamedIdentifier(Citations.EPSG, "Latitude of origin"), new NamedIdentifier(Citations.GEOTIFF, "CenterLat") }49.5D, -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 413 */     public static final ParameterDescriptor LONGITUDE_OF_CENTER = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "longitude_of_center"), new NamedIdentifier(Citations.EPSG, "Longitude of projection centre"), new NamedIdentifier(Citations.EPSG, "Longitude of origin"), new NamedIdentifier(Citations.GEOTIFF, "CenterLong") }24.83333333333333D, -180.0D, 180.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 425 */     public static final ParameterDescriptor AZIMUTH = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "azimuth"), new NamedIdentifier(Citations.EPSG, "Azimuth of initial line"), new NamedIdentifier(Citations.EPSG, "Co-latitude of cone axis"), new NamedIdentifier(Citations.GEOTIFF, "AzimuthAngle"), new NamedIdentifier(Citations.ESRI, "Azimuth") }30.28813972222222D, 0.0D, 360.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 439 */     public static final ParameterDescriptor PSEUDO_STANDARD_PARALLEL = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "pseudo_standard_parallel_1"), new NamedIdentifier(Citations.EPSG, "Latitude of Pseudo Standard Parallel"), new NamedIdentifier(Citations.ESRI, "Pseudo_Standard_Parallel_1") }78.5D, -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 450 */     public static final ParameterDescriptor SCALE_FACTOR = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "scale_factor"), new NamedIdentifier(Citations.EPSG, "Scale factor on pseudo standard parallel"), new NamedIdentifier(Citations.GEOTIFF, "ScaleAtCenter"), new NamedIdentifier(Citations.OGC, "Scale_Factor") }0.9999D, 0.0D, Double.POSITIVE_INFINITY, Unit.ONE);
/*     */     
/* 461 */     public static final ParameterDescriptor X_SCALE = createOptionalDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "X_Scale") }Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Unit.ONE);
/*     */     
/* 470 */     public static final ParameterDescriptor Y_SCALE = createOptionalDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Y_Scale") }Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Unit.ONE);
/*     */     
/* 479 */     public static final ParameterDescriptor XY_PLANE_ROTATION = createOptionalDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "XY_Plane_Rotation") }-360.0D, 360.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 488 */     static final ParameterDescriptorGroup PARAMETERS_ESRI = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Krovak"), new NamedIdentifier(Citations.GEOTIFF, "Krovak"), new NamedIdentifier(Citations.EPSG, "Krovak Oblique Conformal Conic"), new NamedIdentifier(Citations.EPSG, "Krovak Oblique Conic Conformal"), new NamedIdentifier(Citations.EPSG, "9819"), new NamedIdentifier(Citations.ESRI, "Krovak") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { 
/* 488 */           SEMI_MAJOR, SEMI_MINOR, LATITUDE_OF_CENTER, LONGITUDE_OF_CENTER, AZIMUTH, PSEUDO_STANDARD_PARALLEL, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING, X_SCALE, 
/* 488 */           Y_SCALE, XY_PLANE_ROTATION });
/*     */     
/* 504 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Krovak"), new NamedIdentifier(Citations.GEOTIFF, "Krovak"), new NamedIdentifier(Citations.EPSG, "Krovak Oblique Conformal Conic"), new NamedIdentifier(Citations.EPSG, "Krovak Oblique Conic Conformal"), new NamedIdentifier(Citations.EPSG, "9819"), new NamedIdentifier(Citations.ESRI, "Krovak") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, LATITUDE_OF_CENTER, LONGITUDE_OF_CENTER, AZIMUTH, PSEUDO_STANDARD_PARALLEL, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 523 */       super(PARAMETERS_ESRI);
/*     */     }
/*     */     
/*     */     public Provider(ParameterDescriptorGroup params) {
/* 530 */       super(params);
/*     */     }
/*     */     
/*     */     public Class<ConicProjection> getOperationType() {
/* 538 */       return ConicProjection.class;
/*     */     }
/*     */     
/*     */     public MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 551 */       return (MathTransform)new Krovak(parameters, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 560 */     long code = Double.doubleToLongBits(this.azimuth) ^ Double.doubleToLongBits(this.pseudoStandardParallel);
/* 562 */     return ((int)code ^ (int)(code >>> 32L)) + 37 * super.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 570 */     if (object == this)
/* 572 */       return true; 
/* 574 */     if (super.equals(object)) {
/* 575 */       Krovak that = (Krovak)object;
/* 576 */       return (equals(this.azimuth, that.azimuth) && equals(this.pseudoStandardParallel, that.pseudoStandardParallel));
/*     */     } 
/* 579 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\Krovak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */