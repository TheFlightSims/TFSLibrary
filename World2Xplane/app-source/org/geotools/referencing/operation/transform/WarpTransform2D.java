/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.Warp;
/*     */ import javax.media.jai.WarpAffine;
/*     */ import javax.media.jai.WarpCubic;
/*     */ import javax.media.jai.WarpGeneralPolynomial;
/*     */ import javax.media.jai.WarpPolynomial;
/*     */ import javax.media.jai.WarpQuadratic;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.parameter.DefaultParameterDescriptor;
/*     */ import org.geotools.parameter.Parameter;
/*     */ import org.geotools.parameter.ParameterGroup;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.operation.MathTransformProvider;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.Transformation;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class WarpTransform2D extends AbstractMathTransform implements MathTransform2D, Serializable {
/*     */   private static final long serialVersionUID = -7949539694656719923L;
/*     */   
/*     */   private static final boolean USE_HACK;
/*     */   
/*     */   public static final int MAX_DEGREE = 7;
/*     */   
/*     */   private final Warp warp;
/*     */   
/*     */   private final WarpTransform2D inverse;
/*     */   
/*     */   static {
/*  98 */     String buildVersion = JAI.getBuildVersion();
/*  99 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'hh:mm:ss.SSSZ");
/* 100 */     TimeZone tz = TimeZone.getTimeZone("UTC");
/* 101 */     df.setTimeZone(tz);
/* 102 */     boolean hack = false;
/*     */     try {
/* 104 */       Date date_ = (buildVersion != null) ? df.parse(buildVersion) : new Date();
/* 106 */       GregorianCalendar tempCal = new GregorianCalendar(tz);
/* 107 */       tempCal.setTime(date_);
/* 108 */       tempCal.set(11, 0);
/* 109 */       tempCal.set(12, 0);
/* 110 */       tempCal.set(13, 0);
/* 111 */       tempCal.set(14, 0);
/* 112 */       Date date = tempCal.getTime();
/* 115 */       GregorianCalendar version113 = new GregorianCalendar(tz);
/* 116 */       version113.set(2006, 8, 12, 0, 0, 0);
/* 117 */       version113.set(14, 0);
/* 119 */       hack = !date.after(version113.getTime());
/* 120 */     } catch (ParseException e) {
/* 121 */       hack = false;
/*     */     } 
/* 123 */     USE_HACK = hack;
/*     */   }
/*     */   
/*     */   public WarpTransform2D(Point2D[] srcCoords, Point2D[] dstCoords, int degree) {
/* 166 */     this((Rectangle2D)null, srcCoords, 0, (Rectangle2D)null, dstCoords, 0, Math.min(srcCoords.length, dstCoords.length), degree);
/*     */   }
/*     */   
/*     */   public WarpTransform2D(Rectangle2D srcBounds, Point2D[] srcCoords, int srcOffset, Rectangle2D dstBounds, Point2D[] dstCoords, int dstOffset, int numCoords, int degree) {
/* 188 */     this(srcBounds, toFloat(srcCoords, srcOffset, numCoords), 0, dstBounds, toFloat(dstCoords, dstOffset, numCoords), 0, numCoords, degree, false);
/*     */   }
/*     */   
/*     */   private static float[] toFloat(Point2D[] points, int offset, int numCoords) {
/* 197 */     float[] array = new float[numCoords * 2];
/* 198 */     for (int i = 0; i < array.length; ) {
/* 199 */       Point2D point = points[offset++];
/* 200 */       array[i++] = (float)point.getX();
/* 201 */       array[i++] = (float)point.getY();
/*     */     } 
/* 203 */     return array;
/*     */   }
/*     */   
/*     */   public WarpTransform2D(Rectangle2D srcBounds, float[] srcCoords, int srcOffset, Rectangle2D dstBounds, float[] dstCoords, int dstOffset, int numCoords, int degree) {
/* 225 */     this(srcBounds, srcCoords, srcOffset, dstBounds, dstCoords, dstOffset, numCoords, degree, true);
/*     */   }
/*     */   
/*     */   private WarpTransform2D(Rectangle2D srcBounds, float[] srcCoords, int srcOffset, Rectangle2D dstBounds, float[] dstCoords, int dstOffset, int numCoords, int degree, boolean cloneCoords) {
/*     */     float preScaleX, preScaleY, postScaleX, postScaleY;
/* 240 */     if (srcBounds != null) {
/* 241 */       preScaleX = (float)srcBounds.getWidth();
/* 242 */       preScaleY = (float)srcBounds.getHeight();
/*     */     } else {
/* 244 */       preScaleX = getWidth(srcCoords, srcOffset, numCoords);
/* 245 */       preScaleY = getWidth(srcCoords, srcOffset + 1, numCoords);
/*     */     } 
/* 247 */     if (dstBounds != null) {
/* 248 */       postScaleX = (float)dstBounds.getWidth();
/* 249 */       postScaleY = (float)dstBounds.getHeight();
/*     */     } else {
/* 251 */       postScaleX = getWidth(dstCoords, dstOffset, numCoords);
/* 252 */       postScaleY = getWidth(dstCoords, dstOffset + 1, numCoords);
/*     */     } 
/* 259 */     if (USE_HACK) {
/* 260 */       double scaleX = (preScaleX / postScaleX);
/* 261 */       double scaleY = (preScaleY / postScaleY);
/* 262 */       if (scaleX != 1.0D || scaleY != 1.0D) {
/* 263 */         int n = numCoords * 2;
/* 264 */         if (cloneCoords) {
/* 266 */           float[] o = srcCoords;
/* 266 */           srcCoords = new float[n];
/* 266 */           System.arraycopy(o, srcOffset, srcCoords, 0, n);
/* 266 */           srcOffset = 0;
/* 267 */           o = dstCoords;
/* 267 */           dstCoords = new float[n];
/* 267 */           System.arraycopy(o, dstOffset, dstCoords, 0, n);
/* 267 */           dstOffset = 0;
/*     */         } 
/* 269 */         for (int i = 0; i < n; ) {
/* 270 */           srcCoords[srcOffset + i] = (float)(srcCoords[srcOffset + i] / scaleX);
/* 271 */           dstCoords[dstOffset + i++] = (float)(dstCoords[dstOffset + i++] * scaleX);
/* 272 */           srcCoords[srcOffset + i] = (float)(srcCoords[srcOffset + i] / scaleY);
/* 273 */           dstCoords[dstOffset + i++] = (float)(dstCoords[dstOffset + i++] * scaleY);
/*     */         } 
/*     */       } 
/*     */     } 
/* 283 */     this.warp = (Warp)WarpPolynomial.createWarp(dstCoords, dstOffset, srcCoords, srcOffset, numCoords, 1.0F / preScaleX, 1.0F / preScaleY, postScaleX, postScaleY, degree);
/* 285 */     this.inverse = new WarpTransform2D((Warp)WarpPolynomial.createWarp(srcCoords, srcOffset, dstCoords, dstOffset, numCoords, 1.0F / postScaleX, 1.0F / postScaleY, preScaleX, preScaleY, degree), this);
/*     */   }
/*     */   
/*     */   private static float getWidth(float[] array, int offset, int num) {
/* 295 */     float min = Float.POSITIVE_INFINITY;
/* 296 */     float max = Float.NEGATIVE_INFINITY;
/* 297 */     while (--num >= 0) {
/* 298 */       float value = array[offset];
/* 299 */       if (value < min)
/* 299 */         min = value; 
/* 300 */       if (value > max)
/* 300 */         max = value; 
/* 301 */       offset += 2;
/*     */     } 
/* 303 */     return max - min;
/*     */   }
/*     */   
/*     */   protected WarpTransform2D(Warp warp, Warp inverse) {
/* 315 */     ensureNonNull("warp", warp);
/* 316 */     this.warp = warp;
/* 317 */     this.inverse = (inverse != null) ? new WarpTransform2D(inverse, this) : null;
/*     */   }
/*     */   
/*     */   private WarpTransform2D(Warp warp, WarpTransform2D inverse) {
/* 325 */     this.warp = warp;
/* 326 */     this.inverse = inverse;
/*     */   }
/*     */   
/*     */   public static MathTransform2D create(Warp warp) {
/* 337 */     if (warp instanceof WarpAdapter)
/* 338 */       return ((WarpAdapter)warp).getTransform(); 
/* 340 */     return new WarpTransform2D(warp, (Warp)null);
/*     */   }
/*     */   
/*     */   public static Warp getWarp(CharSequence name, MathTransform2D transform) {
/*     */     InternationalString internationalString;
/* 371 */     if (transform instanceof WarpTransform2D)
/* 372 */       return ((WarpTransform2D)transform).getWarp(); 
/* 374 */     if (name == null)
/* 375 */       internationalString = Vocabulary.formatInternational(252); 
/* 377 */     return new WarpAdapter((CharSequence)internationalString, transform);
/*     */   }
/*     */   
/*     */   public Warp getWarp() {
/* 390 */     return this.warp;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 398 */     if (this.warp instanceof WarpPolynomial)
/* 399 */       return Provider.PARAMETERS; 
/* 401 */     return super.getParameterDescriptors();
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 410 */     if (this.warp instanceof WarpPolynomial) {
/* 411 */       WarpPolynomial poly = (WarpPolynomial)this.warp;
/* 412 */       ParameterValue[] p = new ParameterValue[7];
/* 413 */       int c = 0;
/* 414 */       p[c++] = (ParameterValue)new Parameter(Provider.DEGREE, Integer.valueOf(poly.getDegree()));
/* 415 */       p[c++] = (ParameterValue)new Parameter(Provider.X_COEFFS, poly.getXCoeffs());
/* 416 */       p[c++] = (ParameterValue)new Parameter(Provider.Y_COEFFS, poly.getYCoeffs());
/*     */       float s;
/* 418 */       if ((s = poly.getPreScaleX()) != 1.0F)
/* 418 */         p[c++] = (ParameterValue)new Parameter(Provider.PRE_SCALE_X, Float.valueOf(s)); 
/* 419 */       if ((s = poly.getPreScaleY()) != 1.0F)
/* 419 */         p[c++] = (ParameterValue)new Parameter(Provider.PRE_SCALE_Y, Float.valueOf(s)); 
/* 420 */       if ((s = poly.getPostScaleX()) != 1.0F)
/* 420 */         p[c++] = (ParameterValue)new Parameter(Provider.POST_SCALE_X, Float.valueOf(s)); 
/* 421 */       if ((s = poly.getPostScaleY()) != 1.0F)
/* 421 */         p[c++] = (ParameterValue)new Parameter(Provider.POST_SCALE_Y, Float.valueOf(s)); 
/* 422 */       return (ParameterValueGroup)new ParameterGroup(getParameterDescriptors(), (GeneralParameterValue[])XArray.resize((Object[])p, c));
/*     */     } 
/* 424 */     return super.getParameterValues();
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 432 */     return 2;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 439 */     return 2;
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 447 */     return false;
/*     */   }
/*     */   
/*     */   public Point2D transform(Point2D ptSrc, Point2D ptDst) {
/* 474 */     ptSrc = new PointDouble(ptSrc.getX() - 0.5D, ptSrc.getY() - 0.5D);
/* 475 */     Point2D result = this.warp.mapDestPoint(ptSrc);
/* 476 */     result.setLocation(result.getX() + 0.5D, result.getY() + 0.5D);
/* 477 */     if (ptDst == null)
/* 479 */       ptDst = new Point2D.Float(); 
/* 481 */     ptDst.setLocation(result);
/* 482 */     return ptDst;
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/*     */     int postIncrement;
/* 494 */     if (srcPts == dstPts && srcOff < dstOff) {
/* 495 */       srcOff += (numPts - 1) * 2;
/* 496 */       dstOff += (numPts - 1) * 2;
/* 497 */       postIncrement = -4;
/*     */     } else {
/* 499 */       postIncrement = 0;
/*     */     } 
/* 501 */     Point2D.Float ptSrc = new PointFloat();
/* 502 */     float[] ptDst = new float[2];
/* 503 */     while (--numPts >= 0) {
/* 504 */       ptSrc.x = srcPts[srcOff++] - 0.5F;
/* 505 */       ptSrc.y = srcPts[srcOff++] - 0.5F;
/* 506 */       Point2D result = this.warp.mapDestPoint(ptSrc);
/* 507 */       dstPts[dstOff++] = (float)(result.getX() + 0.5D);
/* 508 */       dstPts[dstOff++] = (float)(result.getY() + 0.5D);
/* 509 */       dstOff += postIncrement;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/*     */     int postIncrement;
/* 521 */     if (srcPts == dstPts && srcOff < dstOff) {
/* 522 */       srcOff += (numPts - 1) * 2;
/* 523 */       dstOff += (numPts - 1) * 2;
/* 524 */       postIncrement = -4;
/*     */     } else {
/* 526 */       postIncrement = 0;
/*     */     } 
/* 528 */     Point2D.Double ptSrc = new PointDouble();
/* 529 */     float[] ptDst = new float[2];
/* 530 */     while (--numPts >= 0) {
/* 531 */       ptSrc.x = srcPts[srcOff++] - 0.5D;
/* 532 */       ptSrc.y = srcPts[srcOff++] - 0.5D;
/* 533 */       Point2D result = this.warp.mapDestPoint(ptSrc);
/* 534 */       dstPts[dstOff++] = result.getX() + 0.5D;
/* 535 */       dstPts[dstOff++] = result.getY() + 0.5D;
/* 536 */       dstOff += postIncrement;
/*     */     } 
/*     */   }
/*     */   
/*     */   public MathTransform2D inverse() throws NoninvertibleTransformException {
/* 547 */     if (this.inverse != null)
/* 548 */       return this.inverse; 
/* 550 */     return (MathTransform2D)super.inverse();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 559 */     return 0x241E73CD ^ super.hashCode() ^ this.warp.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 567 */     if (super.equals(object)) {
/* 568 */       WarpTransform2D that = (WarpTransform2D)object;
/* 569 */       return Utilities.equals(this.warp, that.warp);
/*     */     } 
/* 571 */     return false;
/*     */   }
/*     */   
/*     */   private static final class PointFloat extends Point2D.Float {
/*     */     private PointFloat() {}
/*     */     
/*     */     public PointFloat clone() {
/* 582 */       return this;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class PointDouble extends Point2D.Double {
/*     */     public PointDouble() {}
/*     */     
/*     */     public PointDouble(double x, double y) {
/* 598 */       super(x, y);
/*     */     }
/*     */     
/*     */     public PointDouble clone() {
/* 603 */       return this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider extends MathTransformProvider {
/*     */     private static final long serialVersionUID = -7949539694656719923L;
/*     */     
/* 620 */     public static final ParameterDescriptor<Integer> DEGREE = (ParameterDescriptor<Integer>)DefaultParameterDescriptor.create("degree", 2, 1, 7);
/*     */     
/* 624 */     public static final ParameterDescriptor X_COEFFS = (ParameterDescriptor)new DefaultParameterDescriptor("xCoeffs", float[].class, null, null);
/*     */     
/* 628 */     public static final ParameterDescriptor Y_COEFFS = (ParameterDescriptor)new DefaultParameterDescriptor("yCoeffs", float[].class, null, null);
/*     */     
/*     */     public static final ParameterDescriptor PRE_SCALE_X;
/*     */     
/*     */     public static final ParameterDescriptor PRE_SCALE_Y;
/*     */     
/*     */     public static final ParameterDescriptor POST_SCALE_X;
/*     */     
/*     */     public static final ParameterDescriptor<Float> POST_SCALE_Y;
/*     */     
/*     */     static {
/* 643 */       Float ONE = Float.valueOf(1.0F);
/* 644 */       PRE_SCALE_X = (ParameterDescriptor)DefaultParameterDescriptor.create("preScaleX", null, Float.class, ONE, false);
/* 645 */       PRE_SCALE_Y = (ParameterDescriptor)DefaultParameterDescriptor.create("preScaleY", null, Float.class, ONE, false);
/* 646 */       POST_SCALE_X = (ParameterDescriptor)DefaultParameterDescriptor.create("postScaleX", null, Float.class, ONE, false);
/* 647 */       POST_SCALE_Y = (ParameterDescriptor<Float>)DefaultParameterDescriptor.create("postScaleY", null, Float.class, ONE, false);
/*     */     }
/*     */     
/* 653 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.GEOTOOLS, "WarpPolynomial") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { DEGREE, X_COEFFS, Y_COEFFS, PRE_SCALE_X, PRE_SCALE_Y, POST_SCALE_X, POST_SCALE_Y });
/*     */     
/*     */     public Provider() {
/* 663 */       super(2, 2, PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<Transformation> getOperationType() {
/* 671 */       return Transformation.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException {
/*     */       WarpAffine warpAffine;
/*     */       WarpQuadratic warpQuadratic;
/*     */       WarpCubic warpCubic;
/* 684 */       int degree = intValue(DEGREE, values);
/* 685 */       float[] xCoeffs = (float[])value(X_COEFFS, values);
/* 686 */       float[] yCoeffs = (float[])value(Y_COEFFS, values);
/* 687 */       float preScaleX = scale(PRE_SCALE_X, values);
/* 688 */       float preScaleY = scale(PRE_SCALE_Y, values);
/* 689 */       float postScaleX = scale(POST_SCALE_X, values);
/* 690 */       float postScaleY = scale(POST_SCALE_Y, values);
/* 692 */       switch (degree) {
/*     */         case 1:
/* 693 */           warpAffine = new WarpAffine(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY);
/* 698 */           return new WarpTransform2D((Warp)warpAffine, (Warp)null);
/*     */         case 2:
/*     */           warpQuadratic = new WarpQuadratic(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY);
/* 698 */           return new WarpTransform2D((Warp)warpQuadratic, (Warp)null);
/*     */         case 3:
/*     */           warpCubic = new WarpCubic(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY);
/* 698 */           return new WarpTransform2D((Warp)warpCubic, (Warp)null);
/*     */       } 
/*     */       WarpGeneralPolynomial warpGeneralPolynomial = new WarpGeneralPolynomial(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY);
/* 698 */       return new WarpTransform2D((Warp)warpGeneralPolynomial, (Warp)null);
/*     */     }
/*     */     
/*     */     private static float scale(ParameterDescriptor param, ParameterValueGroup group) throws ParameterNotFoundException {
/* 712 */       Object value = value(param, group);
/* 713 */       return (value != null) ? ((Number)value).floatValue() : 1.0F;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\WarpTransform2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */