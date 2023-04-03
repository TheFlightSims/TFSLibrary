/*      */ package org.geotools.referencing.operation.projection;
/*      */ 
/*      */ import java.awt.geom.Point2D;
/*      */ import java.io.Serializable;
/*      */ import java.util.Collection;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.LogRecord;
/*      */ import java.util.logging.Logger;
/*      */ import javax.measure.unit.NonSI;
/*      */ import javax.measure.unit.SI;
/*      */ import javax.measure.unit.Unit;
/*      */ import org.geotools.math.XMath;
/*      */ import org.geotools.measure.Latitude;
/*      */ import org.geotools.measure.Longitude;
/*      */ import org.geotools.metadata.iso.citation.Citations;
/*      */ import org.geotools.referencing.NamedIdentifier;
/*      */ import org.geotools.referencing.operation.MathTransformProvider;
/*      */ import org.geotools.referencing.operation.transform.AbstractMathTransform;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.util.Utilities;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.parameter.GeneralParameterDescriptor;
/*      */ import org.opengis.parameter.InvalidParameterValueException;
/*      */ import org.opengis.parameter.ParameterDescriptor;
/*      */ import org.opengis.parameter.ParameterDescriptorGroup;
/*      */ import org.opengis.parameter.ParameterNotFoundException;
/*      */ import org.opengis.parameter.ParameterValueGroup;
/*      */ import org.opengis.referencing.ReferenceIdentifier;
/*      */ import org.opengis.referencing.operation.MathTransform;
/*      */ import org.opengis.referencing.operation.MathTransform2D;
/*      */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*      */ import org.opengis.referencing.operation.Projection;
/*      */ import org.opengis.referencing.operation.TransformException;
/*      */ 
/*      */ public abstract class MapProjection extends AbstractMathTransform implements MathTransform2D, Serializable {
/*      */   private static final long serialVersionUID = -406751619777246914L;
/*      */   
/*   98 */   protected static final Logger LOGGER = Logging.getLogger(MapProjection.class);
/*      */   
/*      */   private static final double EPSILON = 1.0E-6D;
/*      */   
/*      */   private static final double ANGLE_TOLERANCE = 1.0E-4D;
/*      */   
/*      */   private static final double ITERATION_TOLERANCE = 1.0E-10D;
/*      */   
/*      */   private static final double MLFN_TOL = 1.0E-11D;
/*      */   
/*      */   private static final int MAXIMUM_ITERATIONS = 15;
/*      */   
/*      */   private static final double C00 = 1.0D;
/*      */   
/*      */   private static final double C02 = 0.25D;
/*      */   
/*      */   private static final double C04 = 0.046875D;
/*      */   
/*      */   private static final double C06 = 0.01953125D;
/*      */   
/*      */   private static final double C08 = 0.01068115234375D;
/*      */   
/*      */   private static final double C22 = 0.75D;
/*      */   
/*      */   private static final double C44 = 0.46875D;
/*      */   
/*      */   private static final double C46 = 0.013020833333333334D;
/*      */   
/*      */   private static final double C48 = 0.007120768229166667D;
/*      */   
/*      */   private static final double C66 = 0.3645833333333333D;
/*      */   
/*      */   private static final double C68 = 0.005696614583333333D;
/*      */   
/*      */   private static final double C88 = 0.3076171875D;
/*      */   
/*      */   protected final double excentricity;
/*      */   
/*      */   protected final double excentricitySquared;
/*      */   
/*      */   protected final boolean isSpherical;
/*      */   
/*      */   protected final double semiMajor;
/*      */   
/*      */   protected final double semiMinor;
/*      */   
/*      */   protected double centralMeridian;
/*      */   
/*      */   protected double latitudeOfOrigin;
/*      */   
/*      */   protected double scaleFactor;
/*      */   
/*      */   protected final double falseEasting;
/*      */   
/*      */   protected final double falseNorthing;
/*      */   
/*      */   protected double globalScale;
/*      */   
/*      */   private transient MathTransform2D inverse;
/*      */   
/*      */   protected double en0;
/*      */   
/*      */   protected double en1;
/*      */   
/*      */   protected double en2;
/*      */   
/*      */   protected double en3;
/*      */   
/*      */   protected double en4;
/*      */   
/*      */   private transient int rangeCheckSemaphore;
/*      */   
/*  266 */   private static int globalRangeCheckSemaphore = 1;
/*      */   
/*      */   protected boolean invertible = true;
/*      */   
/*      */   protected MapProjection(ParameterValueGroup values) throws ParameterNotFoundException {
/*  290 */     this(values, (Collection<GeneralParameterDescriptor>)null);
/*      */   }
/*      */   
/*      */   MapProjection(ParameterValueGroup values, Collection<GeneralParameterDescriptor> expected) throws ParameterNotFoundException {
/*  307 */     if (expected == null)
/*  308 */       expected = getParameterDescriptors().descriptors(); 
/*  310 */     this.semiMajor = doubleValue(expected, AbstractProvider.SEMI_MAJOR, values);
/*  311 */     this.semiMinor = doubleValue(expected, AbstractProvider.SEMI_MINOR, values);
/*  312 */     this.centralMeridian = doubleValue(expected, AbstractProvider.CENTRAL_MERIDIAN, values);
/*  313 */     this.latitudeOfOrigin = doubleValue(expected, AbstractProvider.LATITUDE_OF_ORIGIN, values);
/*  314 */     this.scaleFactor = doubleValue(expected, AbstractProvider.SCALE_FACTOR, values);
/*  315 */     this.falseEasting = doubleValue(expected, AbstractProvider.FALSE_EASTING, values);
/*  316 */     this.falseNorthing = doubleValue(expected, AbstractProvider.FALSE_NORTHING, values);
/*  317 */     this.isSpherical = (this.semiMajor == this.semiMinor);
/*  318 */     this.excentricitySquared = 1.0D - this.semiMinor * this.semiMinor / this.semiMajor * this.semiMajor;
/*  319 */     this.excentricity = Math.sqrt(this.excentricitySquared);
/*  320 */     this.globalScale = this.scaleFactor * this.semiMajor;
/*  321 */     ensureLongitudeInRange(AbstractProvider.CENTRAL_MERIDIAN, this.centralMeridian, true);
/*  322 */     ensureLatitudeInRange(AbstractProvider.LATITUDE_OF_ORIGIN, this.latitudeOfOrigin, true);
/*  326 */     this.en0 = 1.0D - this.excentricitySquared * (0.25D + this.excentricitySquared * (0.046875D + this.excentricitySquared * (0.01953125D + this.excentricitySquared * 0.01068115234375D)));
/*  328 */     this.en1 = this.excentricitySquared * (0.75D - this.excentricitySquared * (0.046875D + this.excentricitySquared * (0.01953125D + this.excentricitySquared * 0.01068115234375D)));
/*      */     double t;
/*  330 */     this.en2 = (t = this.excentricitySquared * this.excentricitySquared) * (0.46875D - this.excentricitySquared * (0.013020833333333334D + this.excentricitySquared * 0.007120768229166667D));
/*  332 */     this.en3 = (t *= this.excentricitySquared) * (0.3645833333333333D - this.excentricitySquared * 0.005696614583333333D);
/*  333 */     this.en4 = t * this.excentricitySquared * 0.3076171875D;
/*      */   }
/*      */   
/*      */   boolean isExpectedParameter(Collection<GeneralParameterDescriptor> expected, ParameterDescriptor param) {
/*  348 */     return expected.contains(param);
/*      */   }
/*      */   
/*      */   final double doubleValue(Collection<GeneralParameterDescriptor> expected, ParameterDescriptor param, ParameterValueGroup group) throws ParameterNotFoundException {
/*      */     double v;
/*  372 */     if (isExpectedParameter(expected, param))
/*  377 */       return AbstractProvider.doubleValue(param, group); 
/*  385 */     Object value = param.getDefaultValue();
/*  386 */     if (value instanceof Number) {
/*  387 */       v = ((Number)value).doubleValue();
/*  388 */       if (NonSI.DEGREE_ANGLE.equals(param.getUnit()))
/*  389 */         v = Math.toRadians(v); 
/*      */     } else {
/*  392 */       v = Double.NaN;
/*      */     } 
/*  394 */     return v;
/*      */   }
/*      */   
/*      */   final void ensureSpherical() throws IllegalArgumentException {
/*  402 */     if (!this.isSpherical)
/*  403 */       throw new IllegalArgumentException(Errors.format(45)); 
/*      */   }
/*      */   
/*      */   static void ensureLatitudeEquals(ParameterDescriptor name, double y, double expected) throws IllegalArgumentException {
/*  419 */     if (Math.abs(Math.abs(y) - expected) >= 1.0E-6D) {
/*  420 */       y = Math.toDegrees(y);
/*  421 */       String n = name.getName().getCode();
/*  422 */       throw new InvalidParameterValueException(Errors.format(58, n, new Latitude(y)), n, y);
/*      */     } 
/*      */   }
/*      */   
/*      */   static void ensureLatitudeInRange(ParameterDescriptor name, double y, boolean edge) throws IllegalArgumentException {
/*  439 */     if (edge ? (y >= -1.5707963267948966D && y <= 1.5707963267948966D) : (y > -1.5707963267948966D && y < 1.5707963267948966D))
/*      */       return; 
/*  444 */     y = Math.toDegrees(y);
/*  445 */     throw new InvalidParameterValueException(Errors.format(85, new Latitude(y)), name.getName().getCode(), y);
/*      */   }
/*      */   
/*      */   static void ensureLongitudeInRange(ParameterDescriptor name, double x, boolean edge) throws IllegalArgumentException {
/*  461 */     if (edge ? (x >= -3.141592653589793D && x <= Math.PI) : (x > -3.141592653589793D && x < Math.PI))
/*      */       return; 
/*  466 */     x = Math.toDegrees(x);
/*  467 */     throw new InvalidParameterValueException(Errors.format(88, new Longitude(x)), name.getName().getCode(), x);
/*      */   }
/*      */   
/*      */   private static boolean verifyGeographicRanges(AbstractMathTransform tr, double x, double y) {
/*      */     String classe;
/*  487 */     boolean xOut = (x < -180.0001D || x > 180.0001D);
/*  488 */     boolean yOut = (y < -90.0001D || y > 90.0001D);
/*  489 */     if (!xOut && !yOut)
/*  490 */       return false; 
/*  492 */     String lineSeparator = System.getProperty("line.separator", "\n");
/*  493 */     StringBuilder buffer = new StringBuilder();
/*  494 */     buffer.append(Errors.format(153, tr.getName()));
/*  495 */     if (xOut) {
/*  496 */       buffer.append(lineSeparator);
/*  497 */       buffer.append(Errors.format(88, new Longitude(x)));
/*      */     } 
/*  499 */     if (yOut) {
/*  500 */       buffer.append(lineSeparator);
/*  501 */       buffer.append(Errors.format(85, new Latitude(y)));
/*      */     } 
/*  503 */     LogRecord record = new LogRecord(Level.WARNING, buffer.toString());
/*  505 */     if (tr instanceof Inverse) {
/*  506 */       classe = ((Inverse)tr).inverse().getClass().getName() + ".Inverse";
/*      */     } else {
/*  508 */       classe = tr.getClass().getName();
/*      */     } 
/*  510 */     record.setSourceClassName(classe);
/*  511 */     record.setSourceMethodName("transform");
/*  512 */     record.setLoggerName(LOGGER.getName());
/*  513 */     LOGGER.log(record);
/*  514 */     return true;
/*      */   }
/*      */   
/*      */   final void set(Collection<GeneralParameterDescriptor> expected, ParameterDescriptor<?> param, ParameterValueGroup group, double value) {
/*  532 */     if (isExpectedParameter(expected, param)) {
/*  533 */       if (NonSI.DEGREE_ANGLE.equals(param.getUnit())) {
/*  540 */         value = Math.toDegrees(value);
/*  541 */         double old = value;
/*  542 */         value = XMath.trimDecimalFractionDigits(value, 4, 12);
/*  543 */         if (value == old) {
/*  550 */           old *= 3.0D;
/*  551 */           double test = XMath.trimDecimalFractionDigits(old, 4, 12);
/*  552 */           if (test != old)
/*  553 */             value = test / 3.0D; 
/*      */         } 
/*      */       } 
/*  557 */       group.parameter(param.getName().getCode()).setValue(value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public ParameterValueGroup getParameterValues() {
/*  576 */     ParameterDescriptorGroup descriptor = getParameterDescriptors();
/*  577 */     Collection<GeneralParameterDescriptor> expected = descriptor.descriptors();
/*  578 */     ParameterValueGroup values = descriptor.createValue();
/*  579 */     set(expected, AbstractProvider.SEMI_MAJOR, values, this.semiMajor);
/*  580 */     set(expected, AbstractProvider.SEMI_MINOR, values, this.semiMinor);
/*  581 */     set(expected, AbstractProvider.CENTRAL_MERIDIAN, values, this.centralMeridian);
/*  582 */     set(expected, AbstractProvider.LATITUDE_OF_ORIGIN, values, this.latitudeOfOrigin);
/*  583 */     set(expected, AbstractProvider.SCALE_FACTOR, values, this.scaleFactor);
/*  584 */     set(expected, AbstractProvider.FALSE_EASTING, values, this.falseEasting);
/*  585 */     set(expected, AbstractProvider.FALSE_NORTHING, values, this.falseNorthing);
/*  586 */     return values;
/*      */   }
/*      */   
/*      */   public final int getSourceDimensions() {
/*  593 */     return 2;
/*      */   }
/*      */   
/*      */   public final int getTargetDimensions() {
/*  600 */     return 2;
/*      */   }
/*      */   
/*      */   protected double orthodromicDistance(Point2D source, Point2D target) {
/*  620 */     if (source.distanceSq(target) > 1.0D) {
/*  621 */       double y1 = Math.toRadians(source.getY());
/*  622 */       double y2 = Math.toRadians(target.getY());
/*  623 */       double dx = Math.toRadians(Math.abs(target.getX() - source.getX()) % 360.0D);
/*  624 */       double rho = Math.sin(y1) * Math.sin(y2) + Math.cos(y1) * Math.cos(y2) * Math.cos(dx);
/*  625 */       if (rho > 1.0D) {
/*  625 */         assert rho <= 1.000001D : rho;
/*  625 */         rho = 1.0D;
/*      */       } 
/*  626 */       if (rho < -1.0D) {
/*  626 */         assert rho >= -1.000001D : rho;
/*  626 */         rho = -1.0D;
/*      */       } 
/*  627 */       return Math.acos(rho) * this.semiMajor;
/*      */     } 
/*  633 */     double lat1 = Math.toRadians(source.getY());
/*  634 */     double lat2 = Math.toRadians(target.getY());
/*  635 */     double lng1 = Math.toRadians(source.getX());
/*  636 */     double lng2 = Math.toRadians(target.getX());
/*  637 */     double dlat = Math.sin(0.5D * (lat2 - lat1));
/*  638 */     double dlng = Math.sin(0.5D * (lng2 - lng1));
/*  639 */     double x = dlat * dlat + dlng * dlng * Math.cos(lat1) * Math.cos(lat2);
/*  640 */     double arcRadians = 2.0D * Math.atan2(Math.sqrt(x), Math.sqrt(Math.max(0.0D, 1.0D - x)));
/*  641 */     return arcRadians * this.semiMajor;
/*      */   }
/*      */   
/*      */   private static final class CheckPoint extends Point2D.Double {
/*      */     public CheckPoint(Point2D point) {
/*  654 */       super(point.getX(), point.getY());
/*      */     }
/*      */   }
/*      */   
/*      */   protected boolean checkReciprocal(Point2D point, Point2D target, boolean inverse) throws ProjectionException {
/*  671 */     if (!(point instanceof CheckPoint))
/*      */       try {
/*      */         double longitude, latitude, distance;
/*  672 */         point = new CheckPoint(point);
/*  676 */         if (inverse) {
/*  678 */           point = inverse().transform(point, point);
/*  679 */           distance = orthodromicDistance(point, target);
/*  680 */           longitude = point.getX();
/*  681 */           latitude = point.getY();
/*      */         } else {
/*  684 */           longitude = point.getX();
/*  685 */           latitude = point.getY();
/*  686 */           point = transform(point, point);
/*  687 */           distance = point.distance(target);
/*      */         } 
/*  689 */         if (distance > getToleranceForAssertions(longitude, latitude))
/*  697 */           throw new ProjectionException(Errors.format(161, Double.valueOf(distance), new Longitude(longitude - Math.toDegrees(this.centralMeridian)), new Latitude(latitude - Math.toDegrees(this.latitudeOfOrigin)), getName())); 
/*  703 */       } catch (ProjectionException exception) {
/*  704 */         throw exception;
/*  705 */       } catch (TransformException exception) {
/*  706 */         throw new ProjectionException(exception);
/*      */       }  
/*  708 */     return true;
/*      */   }
/*      */   
/*      */   static boolean checkTransform(double x, double y, Point2D expected, double tolerance) {
/*  723 */     compare("x", expected.getX(), x, tolerance);
/*  724 */     compare("y", expected.getY(), y, tolerance);
/*  725 */     return (tolerance < Double.POSITIVE_INFINITY);
/*      */   }
/*      */   
/*      */   static boolean checkTransform(double x, double y, Point2D expected) {
/*  732 */     return checkTransform(x, y, expected, 1.0E-6D);
/*      */   }
/*      */   
/*      */   static boolean checkInverseTransform(double longitude, double latitude, Point2D expected, double tolerance) {
/*  750 */     compare("latitude", expected.getY(), latitude, tolerance);
/*  751 */     if (Math.abs(1.5707963267948966D - Math.abs(latitude)) > 1.0E-6D)
/*  752 */       compare("longitude", expected.getX(), longitude, tolerance); 
/*  754 */     return (tolerance < Double.POSITIVE_INFINITY);
/*      */   }
/*      */   
/*      */   static boolean checkInverseTransform(double longitude, double latitude, Point2D expected) {
/*  761 */     return checkInverseTransform(longitude, latitude, expected, 1.0E-6D);
/*      */   }
/*      */   
/*      */   private static void compare(String variable, double expected, double actual, double tolerance) {
/*  774 */     if (Math.abs(expected - actual) > tolerance) {
/*  775 */       if (variable.charAt(0) == 'l') {
/*  776 */         actual = Math.toDegrees(actual);
/*  777 */         expected = Math.toDegrees(expected);
/*      */       } 
/*  779 */       throw new AssertionError(Errors.format(167, variable, Double.valueOf(expected), Double.valueOf(actual)));
/*      */     } 
/*      */   }
/*      */   
/*      */   public final Point2D transform(Point2D ptSrc, Point2D ptDst) throws ProjectionException {
/*  867 */     double x = ptSrc.getX();
/*  868 */     double y = ptSrc.getY();
/*  869 */     if (verifyCoordinateRanges() && 
/*  870 */       verifyGeographicRanges(this, x, y))
/*  871 */       warningLogged(); 
/*  883 */     ptDst = transformNormalized((this.centralMeridian != 0.0D) ? rollLongitude(Math.toRadians(x) - this.centralMeridian) : Math.toRadians(x), Math.toRadians(y), ptDst);
/*  885 */     ptDst.setLocation(this.globalScale * ptDst.getX() + this.falseEasting, this.globalScale * ptDst.getY() + this.falseNorthing);
/*  888 */     if (this.invertible && 
/*  889 */       !$assertionsDisabled && !checkReciprocal(ptDst, (ptSrc != ptDst) ? ptSrc : new Point2D.Double(x, y), true))
/*  889 */       throw new AssertionError(); 
/*  891 */     return ptDst;
/*      */   }
/*      */   
/*      */   public final void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws ProjectionException {
/*  912 */     boolean reverse = (srcPts == dstPts && srcOff < dstOff && srcOff + 2 * numPts > dstOff);
/*  914 */     if (reverse) {
/*  915 */       srcOff += 2 * numPts;
/*  916 */       dstOff += 2 * numPts;
/*      */     } 
/*  918 */     Point2D.Double point = new Point2D.Double();
/*  919 */     ProjectionException firstException = null;
/*  920 */     while (--numPts >= 0) {
/*      */       try {
/*  922 */         point.x = srcPts[srcOff++];
/*  923 */         point.y = srcPts[srcOff++];
/*  924 */         transform(point, point);
/*  925 */         dstPts[dstOff++] = point.x;
/*  926 */         dstPts[dstOff++] = point.y;
/*  927 */       } catch (ProjectionException exception) {
/*  928 */         dstPts[dstOff++] = Double.NaN;
/*  929 */         dstPts[dstOff++] = Double.NaN;
/*  930 */         if (firstException == null)
/*  931 */           firstException = exception; 
/*      */       } 
/*  934 */       if (reverse) {
/*  935 */         srcOff -= 4;
/*  936 */         dstOff -= 4;
/*      */       } 
/*      */     } 
/*  939 */     if (firstException != null)
/*  940 */       throw firstException; 
/*      */   }
/*      */   
/*      */   public final void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws ProjectionException {
/*  958 */     boolean reverse = (srcPts == dstPts && srcOff < dstOff && srcOff + 2 * numPts > dstOff);
/*  960 */     if (reverse) {
/*  961 */       srcOff += 2 * numPts;
/*  962 */       dstOff += 2 * numPts;
/*      */     } 
/*  964 */     Point2D.Double point = new Point2D.Double();
/*  965 */     ProjectionException firstException = null;
/*  966 */     while (--numPts >= 0) {
/*      */       try {
/*  968 */         point.x = srcPts[srcOff++];
/*  969 */         point.y = srcPts[srcOff++];
/*  970 */         transform(point, point);
/*  971 */         dstPts[dstOff++] = (float)point.x;
/*  972 */         dstPts[dstOff++] = (float)point.y;
/*  973 */       } catch (ProjectionException exception) {
/*  974 */         dstPts[dstOff++] = Float.NaN;
/*  975 */         dstPts[dstOff++] = Float.NaN;
/*  976 */         if (firstException == null)
/*  977 */           firstException = exception; 
/*      */       } 
/*  980 */       if (reverse) {
/*  981 */         srcOff -= 4;
/*  982 */         dstOff -= 4;
/*      */       } 
/*      */     } 
/*  985 */     if (firstException != null)
/*  986 */       throw firstException; 
/*      */   }
/*      */   
/*      */   private final class Inverse extends AbstractMathTransform.Inverse implements MathTransform2D {
/*      */     private static final long serialVersionUID = -9138242780765956870L;
/*      */     
/*      */     public Inverse() {
/* 1010 */       super(MapProjection.this);
/*      */     }
/*      */     
/*      */     public final Point2D transform(Point2D ptSrc, Point2D ptDst) throws ProjectionException {
/* 1034 */       double x0 = ptSrc.getX();
/* 1035 */       double y0 = ptSrc.getY();
/* 1036 */       ptDst = MapProjection.this.inverseTransformNormalized((x0 - MapProjection.this.falseEasting) / MapProjection.this.globalScale, (y0 - MapProjection.this.falseNorthing) / MapProjection.this.globalScale, ptDst);
/* 1047 */       double x = Math.toDegrees((MapProjection.this.centralMeridian != 0.0D) ? rollLongitude(ptDst.getX() + MapProjection.this.centralMeridian) : ptDst.getX());
/* 1049 */       double y = Math.toDegrees(ptDst.getY());
/* 1050 */       ptDst.setLocation(x, y);
/* 1051 */       if (MapProjection.this.verifyCoordinateRanges() && MapProjection
/* 1052 */         .verifyGeographicRanges((AbstractMathTransform)this, x, y))
/* 1053 */         MapProjection.this.warningLogged(); 
/* 1056 */       assert MapProjection.this.checkReciprocal(ptDst, (ptSrc != ptDst) ? ptSrc : new Point2D.Double(x0, y0), false);
/* 1057 */       return ptDst;
/*      */     }
/*      */     
/*      */     public final void transform(double[] src, int srcOffset, double[] dest, int dstOffset, int numPts) throws TransformException {
/* 1079 */       boolean reverse = (src == dest && srcOffset < dstOffset && srcOffset + 2 * numPts > dstOffset);
/* 1081 */       if (reverse) {
/* 1082 */         srcOffset += 2 * numPts;
/* 1083 */         dstOffset += 2 * numPts;
/*      */       } 
/* 1085 */       Point2D.Double point = new Point2D.Double();
/* 1086 */       ProjectionException firstException = null;
/* 1087 */       while (--numPts >= 0) {
/*      */         try {
/* 1089 */           point.x = src[srcOffset++];
/* 1090 */           point.y = src[srcOffset++];
/* 1091 */           transform(point, point);
/* 1092 */           dest[dstOffset++] = point.x;
/* 1093 */           dest[dstOffset++] = point.y;
/* 1094 */         } catch (ProjectionException exception) {
/* 1095 */           dest[dstOffset++] = Double.NaN;
/* 1096 */           dest[dstOffset++] = Double.NaN;
/* 1097 */           if (firstException == null)
/* 1098 */             firstException = exception; 
/*      */         } 
/* 1101 */         if (reverse) {
/* 1102 */           srcOffset -= 4;
/* 1103 */           dstOffset -= 4;
/*      */         } 
/*      */       } 
/* 1106 */       if (firstException != null)
/* 1107 */         throw firstException; 
/*      */     }
/*      */     
/*      */     public final void transform(float[] src, int srcOffset, float[] dest, int dstOffset, int numPts) throws ProjectionException {
/* 1126 */       boolean reverse = (src == dest && srcOffset < dstOffset && srcOffset + 2 * numPts > dstOffset);
/* 1128 */       if (reverse) {
/* 1129 */         srcOffset += 2 * numPts;
/* 1130 */         dstOffset += 2 * numPts;
/*      */       } 
/* 1132 */       Point2D.Double point = new Point2D.Double();
/* 1133 */       ProjectionException firstException = null;
/* 1134 */       while (--numPts >= 0) {
/*      */         try {
/* 1136 */           point.x = src[srcOffset++];
/* 1137 */           point.y = src[srcOffset++];
/* 1138 */           transform(point, point);
/* 1139 */           dest[dstOffset++] = (float)point.x;
/* 1140 */           dest[dstOffset++] = (float)point.y;
/* 1141 */         } catch (ProjectionException exception) {
/* 1142 */           dest[dstOffset++] = Float.NaN;
/* 1143 */           dest[dstOffset++] = Float.NaN;
/* 1144 */           if (firstException == null)
/* 1145 */             firstException = exception; 
/*      */         } 
/* 1148 */         if (reverse) {
/* 1149 */           srcOffset -= 4;
/* 1150 */           dstOffset -= 4;
/*      */         } 
/*      */       } 
/* 1153 */       if (firstException != null)
/* 1154 */         throw firstException; 
/*      */     }
/*      */     
/*      */     public MathTransform2D inverse() {
/* 1163 */       return (MathTransform2D)super.inverse();
/*      */     }
/*      */   }
/*      */   
/*      */   public final MathTransform2D inverse() throws NoninvertibleTransformException {
/* 1172 */     if (!this.invertible)
/* 1173 */       throw new NoninvertibleTransformException(Errors.format(105)); 
/* 1179 */     if (this.inverse == null)
/* 1180 */       this.inverse = new Inverse(); 
/* 1182 */     return this.inverse;
/*      */   }
/*      */   
/*      */   protected double getToleranceForAssertions(double longitude, double latitude) {
/* 1197 */     double delta = Math.abs(longitude - this.centralMeridian) / 2.0D + Math.abs(latitude - this.latitudeOfOrigin);
/* 1199 */     if (delta > 40.0D)
/* 1201 */       return 1.0D; 
/* 1204 */     return (Math.abs(longitude) > 179.0D || Math.abs(latitude) > 89.0D) ? 0.1D : 0.003D;
/*      */   }
/*      */   
/*      */   final boolean verifyCoordinateRanges() {
/* 1222 */     return (this.rangeCheckSemaphore != globalRangeCheckSemaphore);
/*      */   }
/*      */   
/*      */   final void warningLogged() {
/* 1229 */     synchronized (MapProjection.class) {
/* 1230 */       this.rangeCheckSemaphore = globalRangeCheckSemaphore;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static synchronized void resetWarnings() {
/* 1251 */     globalRangeCheckSemaphore++;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1268 */     long code = Double.doubleToLongBits(this.semiMajor);
/* 1269 */     code = code * 37L + Double.doubleToLongBits(this.semiMinor);
/* 1270 */     code = code * 37L + Double.doubleToLongBits(this.centralMeridian);
/* 1271 */     code = code * 37L + Double.doubleToLongBits(this.latitudeOfOrigin);
/* 1272 */     return (int)code ^ (int)(code >>> 32L);
/*      */   }
/*      */   
/*      */   public boolean equals(Object object) {
/* 1282 */     if (super.equals(object)) {
/* 1283 */       MapProjection that = (MapProjection)object;
/* 1284 */       return (equals(this.semiMajor, that.semiMajor) && equals(this.semiMinor, that.semiMinor) && equals(this.centralMeridian, that.centralMeridian) && equals(this.latitudeOfOrigin, that.latitudeOfOrigin) && equals(this.scaleFactor, that.scaleFactor) && equals(this.falseEasting, that.falseEasting) && equals(this.falseNorthing, that.falseNorthing));
/*      */     } 
/* 1292 */     return false;
/*      */   }
/*      */   
/*      */   static boolean equals(double value1, double value2) {
/* 1300 */     return Utilities.equals(value1, value2);
/*      */   }
/*      */   
/*      */   final double cphi2(double ts) throws ProjectionException {
/* 1316 */     double eccnth = 0.5D * this.excentricity;
/* 1317 */     double phi = 1.5707963267948966D - 2.0D * Math.atan(ts);
/* 1318 */     for (int i = 0; i < 15; i++) {
/* 1319 */       double con = this.excentricity * Math.sin(phi);
/* 1320 */       double dphi = 1.5707963267948966D - 2.0D * Math.atan(ts * Math.pow((1.0D - con) / (1.0D + con), eccnth)) - phi;
/* 1321 */       phi += dphi;
/* 1322 */       if (Math.abs(dphi) <= 1.0E-10D)
/* 1323 */         return phi; 
/*      */     } 
/* 1326 */     throw new ProjectionException(129);
/*      */   }
/*      */   
/*      */   final double msfn(double s, double c) {
/* 1336 */     return c / Math.sqrt(1.0D - s * s * this.excentricitySquared);
/*      */   }
/*      */   
/*      */   final double tsfn(double phi, double sinphi) {
/* 1344 */     sinphi *= this.excentricity;
/* 1348 */     return Math.tan(0.5D * (1.5707963267948966D - phi)) / Math.pow((1.0D - sinphi) / (1.0D + sinphi), 0.5D * this.excentricity);
/*      */   }
/*      */   
/*      */   protected final double mlfn(double phi, double sphi, double cphi) {
/* 1362 */     cphi *= sphi;
/* 1363 */     sphi *= sphi;
/* 1364 */     return this.en0 * phi - cphi * (this.en1 + sphi * (this.en2 + sphi * (this.en3 + sphi * this.en4)));
/*      */   }
/*      */   
/*      */   protected final double inv_mlfn(double arg) throws ProjectionException {
/* 1380 */     double k = 1.0D / (1.0D - this.excentricitySquared);
/* 1382 */     double phi = arg;
/* 1383 */     int i = 15;
/*      */     while (true) {
/* 1384 */       if (--i < 0)
/* 1385 */         throw new ProjectionException(Errors.format(129)); 
/* 1387 */       double s = Math.sin(phi);
/* 1388 */       double t = 1.0D - this.excentricitySquared * s * s;
/* 1389 */       t = (mlfn(phi, s, Math.cos(phi)) - arg) * t * Math.sqrt(t) * k;
/* 1390 */       phi -= t;
/* 1391 */       if (Math.abs(t) < 1.0E-11D)
/* 1392 */         return phi; 
/*      */     } 
/*      */   }
/*      */   
/*      */   double aasin(double v) {
/* 1403 */     double av = Math.abs(v);
/* 1404 */     if (av >= 1.0D)
/* 1405 */       return (v < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D; 
/* 1407 */     return Math.asin(v);
/*      */   }
/*      */   
/*      */   public abstract ParameterDescriptorGroup getParameterDescriptors();
/*      */   
/*      */   protected abstract Point2D inverseTransformNormalized(double paramDouble1, double paramDouble2, Point2D paramPoint2D) throws ProjectionException;
/*      */   
/*      */   protected abstract Point2D transformNormalized(double paramDouble1, double paramDouble2, Point2D paramPoint2D) throws ProjectionException;
/*      */   
/*      */   public static abstract class AbstractProvider extends MathTransformProvider {
/*      */     private static final long serialVersionUID = 6280666068007678702L;
/*      */     
/* 1435 */     public static final ParameterDescriptor SEMI_MAJOR = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "semi_major"), new NamedIdentifier(Citations.EPSG, "semi-major axis") }Double.NaN, 0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*      */     
/* 1449 */     public static final ParameterDescriptor SEMI_MINOR = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "semi_minor"), new NamedIdentifier(Citations.EPSG, "semi-minor axis") }Double.NaN, 0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*      */     
/* 1461 */     public static final ParameterDescriptor CENTRAL_MERIDIAN = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "central_meridian"), new NamedIdentifier(Citations.EPSG, "Longitude of natural origin"), new NamedIdentifier(Citations.EPSG, "Longitude of false origin"), new NamedIdentifier(Citations.EPSG, "Longitude of origin"), new NamedIdentifier(Citations.ESRI, "Longitude_Of_Center"), new NamedIdentifier(Citations.ESRI, "longitude_of_center"), new NamedIdentifier(Citations.ESRI, "Longitude_Of_Origin"), new NamedIdentifier(Citations.ESRI, "longitude_of_origin"), new NamedIdentifier(Citations.GEOTIFF, "NatOriginLong") }0.0D, -180.0D, 180.0D, NonSI.DEGREE_ANGLE);
/*      */     
/* 1481 */     public static final ParameterDescriptor LATITUDE_OF_ORIGIN = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "latitude_of_origin"), new NamedIdentifier(Citations.EPSG, "Latitude of false origin"), new NamedIdentifier(Citations.EPSG, "Latitude of natural origin"), new NamedIdentifier(Citations.ESRI, "Latitude_Of_Origin"), new NamedIdentifier(Citations.ESRI, "latitude_of_origin"), new NamedIdentifier(Citations.ESRI, "Latitude_Of_Center"), new NamedIdentifier(Citations.ESRI, "latitude_of_center"), new NamedIdentifier(Citations.GEOTIFF, "NatOriginLat") }0.0D, -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*      */     
/* 1499 */     public static final ParameterDescriptor STANDARD_PARALLEL_1 = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "standard_parallel_1"), new NamedIdentifier(Citations.EPSG, "Latitude of 1st standard parallel"), new NamedIdentifier(Citations.ESRI, "Standard_Parallel_1"), new NamedIdentifier(Citations.ESRI, "standard_parallel_1"), new NamedIdentifier(Citations.GEOTIFF, "StdParallel1") }0.0D, -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*      */     
/* 1513 */     public static final ParameterDescriptor STANDARD_PARALLEL_2 = createOptionalDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "standard_parallel_2"), new NamedIdentifier(Citations.EPSG, "Latitude of 2nd standard parallel"), new NamedIdentifier(Citations.ESRI, "Standard_Parallel_2"), new NamedIdentifier(Citations.ESRI, "standard_parallel_2"), new NamedIdentifier(Citations.GEOTIFF, "StdParallel2") }-90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*      */     
/* 1529 */     public static final ParameterDescriptor SCALE_FACTOR = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "scale_factor"), new NamedIdentifier(Citations.EPSG, "Scale factor at natural origin"), new NamedIdentifier(Citations.EPSG, "Scale factor on initial line"), new NamedIdentifier(Citations.GEOTIFF, "ScaleAtNatOrigin"), new NamedIdentifier(Citations.GEOTIFF, "ScaleAtCenter"), new NamedIdentifier(Citations.ESRI, "Scale_Factor"), new NamedIdentifier(Citations.ESRI, "scale_factor") }1.0D, 0.0D, Double.POSITIVE_INFINITY, Unit.ONE);
/*      */     
/* 1545 */     public static final ParameterDescriptor FALSE_EASTING = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "false_easting"), new NamedIdentifier(Citations.EPSG, "False easting"), new NamedIdentifier(Citations.EPSG, "Easting at false origin"), new NamedIdentifier(Citations.EPSG, "Easting at projection centre"), new NamedIdentifier(Citations.GEOTIFF, "FalseEasting"), new NamedIdentifier(Citations.ESRI, "False_Easting") }0.0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, SI.METER);
/*      */     
/* 1560 */     public static final ParameterDescriptor FALSE_NORTHING = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "false_northing"), new NamedIdentifier(Citations.EPSG, "False northing"), new NamedIdentifier(Citations.EPSG, "Northing at false origin"), new NamedIdentifier(Citations.EPSG, "Northing at projection centre"), new NamedIdentifier(Citations.GEOTIFF, "FalseNorthing"), new NamedIdentifier(Citations.ESRI, "False_Northing"), new NamedIdentifier(Citations.ESRI, "false_northing") }0.0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, SI.METER);
/*      */     
/*      */     public AbstractProvider(ParameterDescriptorGroup parameters) {
/* 1580 */       super(2, 2, parameters);
/*      */     }
/*      */     
/*      */     public Class<? extends Projection> getOperationType() {
/* 1588 */       return Projection.class;
/*      */     }
/*      */     
/*      */     static boolean isSpherical(ParameterValueGroup values) {
/*      */       try {
/* 1596 */         return (MathTransformProvider.doubleValue(SEMI_MAJOR, values) == MathTransformProvider.doubleValue(SEMI_MINOR, values));
/* 1597 */       } catch (IllegalStateException exception) {
/* 1601 */         return false;
/*      */       } 
/*      */     }
/*      */     
/*      */     protected static double doubleValue(ParameterDescriptor param, ParameterValueGroup group) throws ParameterNotFoundException {
/* 1624 */       double v = MathTransformProvider.doubleValue(param, group);
/* 1625 */       if (NonSI.DEGREE_ANGLE.equals(param.getUnit()))
/* 1626 */         v = Math.toRadians(v); 
/* 1628 */       return v;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\MapProjection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */