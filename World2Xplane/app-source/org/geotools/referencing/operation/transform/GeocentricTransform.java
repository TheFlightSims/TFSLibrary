/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.parameter.DefaultParameterDescriptor;
/*     */ import org.geotools.parameter.FloatParameter;
/*     */ import org.geotools.parameter.Parameter;
/*     */ import org.geotools.parameter.ParameterGroup;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.operation.MathTransformProvider;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ 
/*     */ public class GeocentricTransform extends AbstractMathTransform implements Serializable {
/*     */   private static final long serialVersionUID = -3352045463953828140L;
/*     */   
/*     */   private static final double MAX_ERROR = 0.01D;
/*     */   
/*     */   private static final double COS_67P5 = 0.3826834323650898D;
/*     */   
/*     */   private static final double AD_C = 1.0026D;
/*     */   
/*     */   private final double a;
/*     */   
/*     */   private final double b;
/*     */   
/*     */   private final double a2;
/*     */   
/*     */   private final double b2;
/*     */   
/*     */   private final double e2;
/*     */   
/*     */   private final double ep2;
/*     */   
/*     */   private final boolean hasHeight;
/*     */   
/*     */   private transient MathTransform inverse;
/*     */   
/*     */   public GeocentricTransform(Ellipsoid ellipsoid, boolean hasHeight) {
/* 139 */     this(ellipsoid.getSemiMajorAxis(), ellipsoid.getSemiMinorAxis(), ellipsoid.getAxisUnit(), hasHeight);
/*     */   }
/*     */   
/*     */   public GeocentricTransform(double semiMajor, double semiMinor, Unit<Length> units, boolean hasHeight) {
/* 159 */     this.hasHeight = hasHeight;
/* 160 */     UnitConverter converter = units.getConverterTo(SI.METER);
/* 161 */     this.a = converter.convert(semiMajor);
/* 162 */     this.b = converter.convert(semiMinor);
/* 163 */     this.a2 = this.a * this.a;
/* 164 */     this.b2 = this.b * this.b;
/* 165 */     this.e2 = (this.a2 - this.b2) / this.a2;
/* 166 */     this.ep2 = (this.a2 - this.b2) / this.b2;
/* 167 */     checkArgument("a", this.a, Double.MAX_VALUE);
/* 168 */     checkArgument("b", this.b, this.a);
/*     */   }
/*     */   
/*     */   private static void checkArgument(String name, double value, double max) throws IllegalArgumentException {
/* 183 */     if (value < 0.0D || value > max)
/* 185 */       throw new IllegalArgumentException(Errors.format(58, name, Double.valueOf(value))); 
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 194 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 204 */     return getParameterValues(getParameterDescriptors());
/*     */   }
/*     */   
/*     */   private ParameterValueGroup getParameterValues(ParameterDescriptorGroup descriptor) {
/* 214 */     ParameterValue[] parameters = new ParameterValue[this.hasHeight ? 2 : 3];
/* 215 */     int index = 0;
/* 216 */     if (!this.hasHeight) {
/* 217 */       Parameter parameter = new Parameter(Provider.DIM);
/* 218 */       parameter.setValue(2);
/* 219 */       parameters[index++] = (ParameterValue)parameter;
/*     */     } 
/* 221 */     parameters[index++] = (ParameterValue)new FloatParameter(Provider.SEMI_MAJOR, this.a);
/* 222 */     parameters[index++] = (ParameterValue)new FloatParameter(Provider.SEMI_MINOR, this.b);
/* 223 */     return (ParameterValueGroup)new ParameterGroup(descriptor, (GeneralParameterValue[])parameters);
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 230 */     return this.hasHeight ? 3 : 2;
/*     */   }
/*     */   
/*     */   public final int getTargetDimensions() {
/* 237 */     return 3;
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 245 */     transform(srcPts, srcOff, dstPts, dstOff, numPts, false);
/*     */   }
/*     */   
/*     */   private void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts, boolean hasHeight) {
/* 256 */     int dimSource = getSourceDimensions();
/* 257 */     int i = hasHeight | ((dimSource >= 3) ? 1 : 0);
/* 258 */     if (srcPts == dstPts && needCopy(srcOff, dimSource, dstOff, 3, numPts)) {
/* 260 */       double[] old = srcPts;
/* 261 */       srcPts = new double[numPts * ((i != 0) ? 3 : 2)];
/* 262 */       System.arraycopy(old, srcOff, srcPts, 0, srcPts.length);
/* 263 */       srcOff = 0;
/*     */     } 
/* 265 */     while (--numPts >= 0) {
/* 266 */       double L = Math.toRadians(srcPts[srcOff++]);
/* 267 */       double P = Math.toRadians(srcPts[srcOff++]);
/* 268 */       double h = (i != 0) ? srcPts[srcOff++] : 0.0D;
/* 270 */       double cosLat = Math.cos(P);
/* 271 */       double sinLat = Math.sin(P);
/* 272 */       double rn = this.a / Math.sqrt(1.0D - this.e2 * sinLat * sinLat);
/* 274 */       dstPts[dstOff++] = (rn + h) * cosLat * Math.cos(L);
/* 275 */       dstPts[dstOff++] = (rn + h) * cosLat * Math.sin(L);
/* 276 */       dstPts[dstOff++] = (rn * (1.0D - this.e2) + h) * sinLat;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 288 */     int dimSource = getSourceDimensions();
/* 289 */     boolean hasHeight = (dimSource >= 3);
/* 290 */     if (srcPts == dstPts && needCopy(srcOff, dimSource, dstOff, 3, numPts)) {
/* 292 */       float[] old = srcPts;
/* 293 */       srcPts = new float[numPts * dimSource];
/* 294 */       System.arraycopy(old, srcOff, srcPts, 0, srcPts.length);
/* 295 */       srcOff = 0;
/*     */     } 
/* 297 */     while (--numPts >= 0) {
/* 298 */       double L = Math.toRadians(srcPts[srcOff++]);
/* 299 */       double P = Math.toRadians(srcPts[srcOff++]);
/* 300 */       double h = hasHeight ? srcPts[srcOff++] : 0.0D;
/* 302 */       double cosLat = Math.cos(P);
/* 303 */       double sinLat = Math.sin(P);
/* 304 */       double rn = this.a / Math.sqrt(1.0D - this.e2 * sinLat * sinLat);
/* 306 */       dstPts[dstOff++] = (float)((rn + h) * cosLat * Math.cos(L));
/* 307 */       dstPts[dstOff++] = (float)((rn + h) * cosLat * Math.sin(L));
/* 308 */       dstPts[dstOff++] = (float)((rn * (1.0D - this.e2) + h) * sinLat);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void inverseTransform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 330 */     int dimTarget = getSourceDimensions();
/* 331 */     if (srcPts == dstPts && needCopy(srcOff, 3, dstOff, dimTarget, numPts)) {
/* 333 */       double[] old = srcPts;
/* 334 */       srcPts = new double[numPts * 3];
/* 335 */       System.arraycopy(old, srcOff, srcPts, 0, srcPts.length);
/* 336 */       srcOff = 0;
/*     */     } 
/* 338 */     inverseTransform((float[])null, srcPts, srcOff, (float[])null, dstPts, dstOff, numPts, dimTarget);
/*     */   }
/*     */   
/*     */   public void inverseTransform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 359 */     int dimTarget = getSourceDimensions();
/* 360 */     if (srcPts == dstPts && needCopy(srcOff, 3, dstOff, dimTarget, numPts)) {
/* 362 */       float[] old = srcPts;
/* 363 */       srcPts = new float[numPts * 3];
/* 364 */       System.arraycopy(old, srcOff, srcPts, 0, srcPts.length);
/* 365 */       srcOff = 0;
/*     */     } 
/* 367 */     inverseTransform(srcPts, (double[])null, srcOff, dstPts, (double[])null, dstOff, numPts, dimTarget);
/*     */   }
/*     */   
/*     */   private void inverseTransform(float[] srcPts1, double[] srcPts2, int srcOff, float[] dstPts1, double[] dstPts2, int dstOff, int numPts, int dimTarget) {
/* 377 */     boolean hasHeight = (dimTarget >= 3);
/* 378 */     boolean computeHeight = hasHeight;
/* 379 */     assert (computeHeight = true) == true;
/* 380 */     while (--numPts >= 0) {
/*     */       double x, y, z;
/* 382 */       if (srcPts2 != null) {
/* 383 */         x = srcPts2[srcOff++];
/* 384 */         y = srcPts2[srcOff++];
/* 385 */         z = srcPts2[srcOff++];
/*     */       } else {
/* 387 */         x = srcPts1[srcOff++];
/* 388 */         y = srcPts1[srcOff++];
/* 389 */         z = srcPts1[srcOff++];
/*     */       } 
/* 398 */       double W2 = x * x + y * y;
/* 399 */       double W = Math.sqrt(W2);
/* 400 */       double T0 = z * 1.0026D;
/* 401 */       double S0 = Math.sqrt(T0 * T0 + W2);
/* 402 */       double sin_B0 = T0 / S0;
/* 403 */       double cos_B0 = W / S0;
/* 404 */       double sin3_B0 = sin_B0 * sin_B0 * sin_B0;
/* 405 */       double T1 = z + this.b * this.ep2 * sin3_B0;
/* 406 */       double sum = W - this.a * this.e2 * cos_B0 * cos_B0 * cos_B0;
/* 407 */       double S1 = Math.sqrt(T1 * T1 + sum * sum);
/* 408 */       double sin_p1 = T1 / S1;
/* 409 */       double cos_p1 = sum / S1;
/* 411 */       double longitude = Math.toDegrees(Math.atan2(y, x));
/* 412 */       double latitude = Math.toDegrees(Math.atan(sin_p1 / cos_p1));
/* 415 */       if (dstPts2 != null) {
/* 416 */         dstPts2[dstOff++] = longitude;
/* 417 */         dstPts2[dstOff++] = latitude;
/*     */       } else {
/* 419 */         dstPts1[dstOff++] = (float)longitude;
/* 420 */         dstPts1[dstOff++] = (float)latitude;
/*     */       } 
/* 422 */       if (computeHeight) {
/* 423 */         double height, rn = this.a / Math.sqrt(1.0D - this.e2 * sin_p1 * sin_p1);
/* 424 */         if (cos_p1 >= 0.3826834323650898D) {
/* 424 */           height = W / cos_p1 - rn;
/* 425 */         } else if (cos_p1 <= -0.3826834323650898D) {
/* 425 */           height = W / -cos_p1 - rn;
/*     */         } else {
/* 426 */           height = z / sin_p1 + rn * (this.e2 - 1.0D);
/*     */         } 
/* 427 */         if (hasHeight)
/* 428 */           if (dstPts2 != null) {
/* 429 */             dstPts2[dstOff++] = height;
/*     */           } else {
/* 431 */             dstPts1[dstOff++] = (float)height;
/*     */           }  
/*     */         double distance;
/* 438 */         assert 0.01D > (distance = checkTransform(new double[] { x, y, z, longitude, latitude, height })) : distance;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private double checkTransform(double[] points) {
/* 449 */     transform(points, 3, points, 3, 1, true);
/* 450 */     double dx = points[0] - points[3];
/* 451 */     double dy = points[1] - points[4];
/* 452 */     double dz = points[2] - points[5];
/* 453 */     return Math.sqrt(dx * dx + dy * dy + dz * dz);
/*     */   }
/*     */   
/*     */   public synchronized MathTransform inverse() {
/* 461 */     if (this.inverse == null)
/* 462 */       this.inverse = new Inverse(); 
/* 464 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 472 */     long code = Double.doubleToLongBits(this.a) + 37L * (Double.doubleToLongBits(this.b) + 37L * (Double.doubleToLongBits(this.a2) + 37L * (Double.doubleToLongBits(this.b2) + 37L * (Double.doubleToLongBits(this.e2) + 37L * Double.doubleToLongBits(this.ep2)))));
/* 478 */     return (int)code ^ (int)(code >>> 32L) ^ 0x121296D4;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 486 */     if (object == this)
/* 488 */       return true; 
/* 490 */     if (super.equals(object)) {
/* 491 */       GeocentricTransform that = (GeocentricTransform)object;
/* 492 */       return (Double.doubleToLongBits(this.a) == Double.doubleToLongBits(that.a) && Double.doubleToLongBits(this.b) == Double.doubleToLongBits(that.b) && Double.doubleToLongBits(this.a2) == Double.doubleToLongBits(that.a2) && Double.doubleToLongBits(this.b2) == Double.doubleToLongBits(that.b2) && Double.doubleToLongBits(this.e2) == Double.doubleToLongBits(that.e2) && Double.doubleToLongBits(this.ep2) == Double.doubleToLongBits(that.ep2) && this.hasHeight == that.hasHeight);
/*     */     } 
/* 500 */     return false;
/*     */   }
/*     */   
/*     */   private final class Inverse extends AbstractMathTransform.Inverse implements Serializable {
/*     */     private static final long serialVersionUID = 6942084702259211803L;
/*     */     
/*     */     public ParameterDescriptorGroup getParameterDescriptors() {
/* 527 */       return GeocentricTransform.ProviderInverse.PARAMETERS;
/*     */     }
/*     */     
/*     */     public ParameterValueGroup getParameterValues() {
/* 537 */       return GeocentricTransform.this.getParameterValues(getParameterDescriptors());
/*     */     }
/*     */     
/*     */     public void transform(double[] source, int srcOffset, double[] dest, int dstOffset, int length) {
/* 546 */       GeocentricTransform.this.inverseTransform(source, srcOffset, dest, dstOffset, length);
/*     */     }
/*     */     
/*     */     public void transform(float[] source, int srcOffset, float[] dest, int dstOffset, int length) {
/* 556 */       GeocentricTransform.this.inverseTransform(source, srcOffset, dest, dstOffset, length);
/*     */     }
/*     */     
/*     */     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 563 */       in.defaultReadObject();
/* 564 */       GeocentricTransform.this.inverse = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider extends MathTransformProvider {
/*     */     private static final long serialVersionUID = 7043216580786030251L;
/*     */     
/* 587 */     public static final ParameterDescriptor<Double> SEMI_MAJOR = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "semi_major"), new NamedIdentifier(Citations.EPSG, "semi-major axis") }Double.NaN, 0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 598 */     public static final ParameterDescriptor<Double> SEMI_MINOR = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "semi_minor"), new NamedIdentifier(Citations.EPSG, "semi-minor axis") }Double.NaN, 0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 609 */     static final ParameterDescriptor<Integer> DIM = (ParameterDescriptor<Integer>)DefaultParameterDescriptor.create(Collections.singletonMap("name", new NamedIdentifier(Citations.GEOTOOLS, "dim")), 3, 2, 3, false);
/*     */     
/* 616 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup("Ellipsoid_To_Geocentric", "Geographic/geocentric conversions", "9602", 79);
/*     */     
/*     */     transient Provider noHeight;
/*     */     
/*     */     static ParameterDescriptorGroup createDescriptorGroup(String ogc, String epsgName, String epsgCode, int geotools) {
/* 630 */       return createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, ogc), new NamedIdentifier(Citations.EPSG, epsgName), new NamedIdentifier(Citations.EPSG, epsgCode), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(geotools)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, DIM });
/*     */     }
/*     */     
/*     */     public Provider() {
/* 649 */       super(3, 3, PARAMETERS);
/*     */     }
/*     */     
/*     */     Provider(int sourceDimensions, int targetDimensions, ParameterDescriptorGroup parameters) {
/* 663 */       super(sourceDimensions, targetDimensions, parameters);
/*     */     }
/*     */     
/*     */     public Class<Conversion> getOperationType() {
/* 671 */       return Conversion.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException {
/*     */       MathTransformProvider.Delegate delegate;
/* 684 */       int dimGeographic = intValue(DIM, values);
/* 685 */       double semiMajor = doubleValue(SEMI_MAJOR, values);
/* 686 */       double semiMinor = doubleValue(SEMI_MINOR, values);
/* 687 */       boolean hasHeight = (dimGeographic != 2);
/* 688 */       MathTransform transform = new GeocentricTransform(semiMajor, semiMinor, SI.METER, hasHeight);
/* 689 */       if (!hasHeight) {
/* 690 */         if (this.noHeight == null)
/* 691 */           this.noHeight = new Provider(2, 3, PARAMETERS); 
/* 693 */         delegate = new MathTransformProvider.Delegate(transform, (OperationMethod)this.noHeight);
/*     */       } 
/* 695 */       return (MathTransform)delegate;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ProviderInverse extends Provider {
/*     */     private static final long serialVersionUID = -7356791540110076789L;
/*     */     
/* 719 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup("Geocentric_To_Ellipsoid", "Geographic/geocentric conversions", "9602", 79);
/*     */     
/*     */     public ProviderInverse() {
/* 729 */       super(3, 3, PARAMETERS);
/*     */     }
/*     */     
/*     */     ProviderInverse(int sourceDimensions, int targetDimensions, ParameterDescriptorGroup parameters) {
/* 743 */       super(sourceDimensions, targetDimensions, parameters);
/*     */     }
/*     */     
/*     */     public MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException {
/*     */       MathTransformProvider.Delegate delegate;
/* 757 */       int dimGeographic = intValue(DIM, values);
/* 758 */       double semiMajor = doubleValue(SEMI_MAJOR, values);
/* 759 */       double semiMinor = doubleValue(SEMI_MINOR, values);
/* 760 */       boolean hasHeight = (dimGeographic != 2);
/* 761 */       MathTransform transform = (new GeocentricTransform(semiMajor, semiMinor, SI.METER, hasHeight)).inverse();
/* 762 */       if (!hasHeight) {
/* 763 */         if (this.noHeight == null)
/* 764 */           this.noHeight = new ProviderInverse(3, 2, PARAMETERS); 
/* 766 */         delegate = new MathTransformProvider.Delegate(transform, (OperationMethod)this.noHeight);
/*     */       } 
/* 768 */       return (MathTransform)delegate;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\GeocentricTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */