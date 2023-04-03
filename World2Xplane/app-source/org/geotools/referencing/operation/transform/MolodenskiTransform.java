/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import javax.measure.unit.SI;
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
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.Transformation;
/*     */ import org.opengis.util.GenericName;
/*     */ 
/*     */ public class MolodenskiTransform extends AbstractMathTransform implements Serializable {
/*     */   private static final long serialVersionUID = 7536566033885338422L;
/*     */   
/*     */   private static final float EPS = 1.0E-5F;
/*     */   
/*     */   private final boolean abridged;
/*     */   
/*     */   private final boolean source3D;
/*     */   
/*     */   private final boolean target3D;
/*     */   
/*     */   private final double dx;
/*     */   
/*     */   private final double dy;
/*     */   
/*     */   private final double dz;
/*     */   
/*     */   private final double a;
/*     */   
/*     */   private final double b;
/*     */   
/*     */   private final double da;
/*     */   
/*     */   private final double db;
/*     */   
/*     */   private final double df;
/*     */   
/*     */   private final double b_a;
/*     */   
/*     */   private final double a_b;
/*     */   
/*     */   private final double daa;
/*     */   
/*     */   private final double da_a;
/*     */   
/*     */   private final double e2;
/*     */   
/*     */   private final double adf;
/*     */   
/*     */   private transient MolodenskiTransform inverse;
/*     */   
/*     */   public MolodenskiTransform(boolean abridged, double a, double b, boolean source3D, double ta, double tb, boolean target3D, double dx, double dy, double dz) {
/* 173 */     this.abridged = abridged;
/* 174 */     this.source3D = source3D;
/* 175 */     this.target3D = target3D;
/* 176 */     this.dx = dx;
/* 177 */     this.dy = dy;
/* 178 */     this.dz = dz;
/* 179 */     this.a = a;
/* 180 */     this.b = b;
/* 182 */     this.da = ta - a;
/* 183 */     this.db = tb - b;
/* 184 */     this.a_b = a / b;
/* 185 */     this.b_a = b / a;
/* 186 */     this.daa = this.da * a;
/* 187 */     this.da_a = this.da / a;
/* 188 */     this.df = (ta - tb) / ta - (a - b) / a;
/* 189 */     this.e2 = 1.0D - b * b / a * a;
/* 190 */     this.adf = a * this.df + (a - b) * this.da / a;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 198 */     return this.abridged ? ProviderAbridged.PARAMETERS : Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 208 */     Parameter parameter = new Parameter(Provider.DIM);
/* 209 */     parameter.setValue(getSourceDimensions());
/* 210 */     return (ParameterValueGroup)new ParameterGroup(getParameterDescriptors(), (GeneralParameterValue[])new ParameterValue[] { (ParameterValue)parameter, (ParameterValue)new FloatParameter(Provider.DX, this.dx), (ParameterValue)new FloatParameter(Provider.DY, this.dy), (ParameterValue)new FloatParameter(Provider.DZ, this.dz), (ParameterValue)new FloatParameter(Provider.SRC_SEMI_MAJOR, this.a), (ParameterValue)new FloatParameter(Provider.SRC_SEMI_MINOR, this.b), (ParameterValue)new FloatParameter(Provider.TGT_SEMI_MAJOR, this.a + this.da), (ParameterValue)new FloatParameter(Provider.TGT_SEMI_MINOR, this.b + this.db) });
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 227 */     return this.source3D ? 3 : 2;
/*     */   }
/*     */   
/*     */   public final int getTargetDimensions() {
/* 234 */     return this.target3D ? 3 : 2;
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 261 */     transform(null, srcPts, srcOff, null, dstPts, dstOff, numPts);
/* 272 */     assert !this.target3D || srcPts == dstPts || maxError(null, srcPts, srcOff, null, dstPts, dstOff, numPts) <= 1.0E-5F;
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 301 */     transform(srcPts, null, srcOff, dstPts, null, dstOff, numPts);
/* 312 */     assert !this.target3D || srcPts == dstPts || maxError(srcPts, null, srcOff, dstPts, null, dstOff, numPts) <= 1.0E-5F;
/*     */   }
/*     */   
/*     */   private void transform(float[] srcPts1, double[] srcPts2, int srcOff, float[] dstPts1, double[] dstPts2, int dstOff, int numPts) {
/* 322 */     int step = 0;
/* 323 */     if (((srcPts2 != null) ? (srcPts2 == dstPts2) : (srcPts1 == dstPts1)) && srcOff < dstOff && srcOff + numPts * getSourceDimensions() > dstOff) {
/* 326 */       if (this.source3D != this.target3D)
/* 331 */         throw new UnsupportedOperationException("Not yet implemented."); 
/* 333 */       step = getSourceDimensions();
/* 334 */       srcOff += (numPts - 1) * step;
/* 335 */       dstOff += (numPts - 1) * step;
/* 336 */       step *= -2;
/*     */     } 
/* 338 */     while (--numPts >= 0) {
/*     */       double z;
/* 340 */       if (srcPts2 != null) {
/* 341 */         x = srcPts2[srcOff++];
/* 342 */         y = srcPts2[srcOff++];
/* 343 */         z = this.source3D ? srcPts2[srcOff++] : 0.0D;
/*     */       } else {
/* 345 */         x = srcPts1[srcOff++];
/* 346 */         y = srcPts1[srcOff++];
/* 347 */         z = this.source3D ? srcPts1[srcOff++] : 0.0D;
/*     */       } 
/* 349 */       double x = Math.toRadians(x);
/* 350 */       double y = Math.toRadians(y);
/* 351 */       double sinX = Math.sin(x);
/* 352 */       double cosX = Math.cos(x);
/* 353 */       double sinY = Math.sin(y);
/* 354 */       double cosY = Math.cos(y);
/* 355 */       double sin2Y = sinY * sinY;
/* 356 */       double Rn = this.a / Math.sqrt(1.0D - this.e2 * sin2Y);
/* 357 */       double Rm = Rn * (1.0D - this.e2) / (1.0D - this.e2 * sin2Y);
/* 364 */       if (this.abridged) {
/* 365 */         y += (this.dz * cosY - sinY * (this.dy * sinX + this.dx * cosX) + this.adf * Math.sin(2.0D * y)) / Rm;
/* 366 */         x += (this.dy * cosX - this.dx * sinX) / Rn * cosY;
/*     */       } else {
/* 368 */         y += (this.dz * cosY - sinY * (this.dy * sinX + this.dx * cosX) + this.da_a * Rn * this.e2 * sinY * cosY + this.df * (Rm * this.a_b + Rn * this.b_a) * sinY * cosY) / (Rm + z);
/* 370 */         x += (this.dy * cosX - this.dx * sinX) / (Rn + z) * cosY;
/*     */       } 
/* 373 */       if (Math.abs(y) > 1.5707963267948966D) {
/* 374 */         if (dstPts2 != null) {
/* 375 */           dstPts2[dstOff++] = 0.0D;
/* 376 */           dstPts2[dstOff++] = (y > 0.0D) ? 90.0D : -90.0D;
/*     */         } else {
/* 378 */           dstPts1[dstOff++] = 0.0F;
/* 379 */           dstPts1[dstOff++] = (y > 0.0D) ? 90.0F : -90.0F;
/*     */         } 
/*     */       } else {
/* 382 */         x = Math.toDegrees(rollLongitude(x));
/* 383 */         y = Math.toDegrees(y);
/* 384 */         if (dstPts2 != null) {
/* 385 */           dstPts2[dstOff++] = x;
/* 386 */           dstPts2[dstOff++] = y;
/*     */         } else {
/* 388 */           dstPts1[dstOff++] = (float)x;
/* 389 */           dstPts1[dstOff++] = (float)y;
/*     */         } 
/*     */       } 
/* 392 */       if (this.target3D) {
/* 393 */         if (this.abridged) {
/* 394 */           z += this.dx * cosY * cosX + this.dy * cosY * sinX + this.dz * sinY + this.adf * sin2Y - this.da;
/*     */         } else {
/* 396 */           z += this.dx * cosY * cosX + this.dy * cosY * sinX + this.dz * sinY + this.df * this.b_a * Rn * sin2Y - this.daa / Rn;
/*     */         } 
/* 398 */         if (dstPts2 != null) {
/* 399 */           dstPts2[dstOff++] = z;
/*     */         } else {
/* 401 */           dstPts1[dstOff++] = (float)z;
/*     */         } 
/*     */       } 
/* 404 */       srcOff += step;
/* 405 */       dstOff += step;
/*     */     } 
/*     */   }
/*     */   
/*     */   private float maxError(float[] srcPts1, double[] srcPts2, int srcOff, float[] dstPts1, double[] dstPts2, int dstOff, int numPts) {
/* 417 */     float max = 0.0F;
/* 418 */     if (this.inverse == null) {
/* 419 */       inverse();
/* 420 */       if (this.inverse == null)
/* 421 */         return max; 
/*     */     } 
/* 424 */     int sourceDim = getSourceDimensions();
/* 425 */     float[] tmp = new float[numPts * sourceDim];
/* 426 */     this.inverse.transform(dstPts1, dstPts2, dstOff, tmp, (double[])null, 0, numPts);
/* 427 */     for (int i = 0; i < tmp.length; i++, srcOff++) {
/* 428 */       float expected = (srcPts2 != null) ? (float)srcPts2[srcOff] : srcPts1[srcOff];
/* 429 */       float error = Math.abs(tmp[i] - expected);
/* 430 */       switch (i % sourceDim) {
/*     */         case 0:
/* 431 */           error = (float)(error - 360.0D * Math.floor((error / 360.0F)));
/*     */         case 2:
/*     */           break;
/*     */         default:
/* 434 */           if (error > max)
/* 435 */             max = error; 
/*     */           break;
/*     */       } 
/*     */     } 
/* 438 */     return max;
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 455 */     return (this.dx == 0.0D && this.dy == 0.0D && this.dz == 0.0D && this.da == 0.0D && this.db == 0.0D && this.source3D == this.target3D);
/*     */   }
/*     */   
/*     */   public MathTransform inverse() {
/* 463 */     if (this.inverse == null) {
/* 464 */       this.inverse = new MolodenskiTransform(this.abridged, this.a + this.da, this.b + this.db, this.target3D, this.a, this.b, this.source3D, -this.dx, -this.dy, -this.dz);
/* 466 */       this.inverse.inverse = this;
/*     */     } 
/* 468 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 476 */     long code = Double.doubleToLongBits(this.dx) + 37L * (Double.doubleToLongBits(this.dy) + 37L * (Double.doubleToLongBits(this.dz) + 37L * (Double.doubleToLongBits(this.a) + 37L * (Double.doubleToLongBits(this.b) + 37L * (Double.doubleToLongBits(this.da) + 37L * Double.doubleToLongBits(this.db))))));
/* 483 */     int c = (int)code ^ (int)(code >>> 32L) ^ 0x9C029B36;
/* 484 */     if (this.abridged)
/* 484 */       c ^= 0xFFFFFFFF; 
/* 485 */     return c;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object object) {
/* 493 */     if (object == this)
/* 495 */       return true; 
/* 497 */     if (super.equals(object)) {
/* 498 */       MolodenskiTransform that = (MolodenskiTransform)object;
/* 499 */       return (this.abridged == that.abridged && this.source3D == that.source3D && this.target3D == that.target3D && Double.doubleToLongBits(this.dx) == Double.doubleToLongBits(that.dx) && Double.doubleToLongBits(this.dy) == Double.doubleToLongBits(that.dy) && Double.doubleToLongBits(this.dz) == Double.doubleToLongBits(that.dz) && Double.doubleToLongBits(this.a) == Double.doubleToLongBits(that.a) && Double.doubleToLongBits(this.b) == Double.doubleToLongBits(that.b) && Double.doubleToLongBits(this.da) == Double.doubleToLongBits(that.da) && Double.doubleToLongBits(this.db) == Double.doubleToLongBits(that.db));
/*     */     } 
/* 510 */     return false;
/*     */   }
/*     */   
/*     */   private static final class As2D extends MolodenskiTransform implements MathTransform2D {
/*     */     private static final long serialVersionUID = 8098439371246167474L;
/*     */     
/*     */     public As2D(boolean abridged, double a, double b, double ta, double tb, double dx, double dy, double dz) {
/* 527 */       super(abridged, a, b, false, ta, tb, false, dx, dy, dz);
/*     */     }
/*     */     
/*     */     public MathTransform2D inverse() {
/* 533 */       if (this.inverse == null) {
/* 534 */         this.inverse = new As2D(this.abridged, this.a + this.da, this.b + this.db, this.a, this.b, -this.dx, -this.dy, -this.dz);
/* 537 */         this.inverse.inverse = this;
/*     */       } 
/* 539 */       return (MathTransform2D)this.inverse;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider extends MathTransformProvider {
/*     */     private static final long serialVersionUID = -5332126871499059030L;
/*     */     
/*     */     static final int DEFAULT_DIMENSION = 2;
/*     */     
/* 573 */     public static final ParameterDescriptor<Integer> DIM = (ParameterDescriptor<Integer>)DefaultParameterDescriptor.create(Collections.singletonMap("name", new NamedIdentifier(Citations.OGC, "dim")), 2, 2, 3, false);
/*     */     
/* 584 */     public static final ParameterDescriptor<Integer> SRC_DIM = GeocentricTranslation.Provider.SRC_DIM;
/*     */     
/* 593 */     public static final ParameterDescriptor<Integer> TGT_DIM = GeocentricTranslation.Provider.TGT_DIM;
/*     */     
/* 600 */     public static final ParameterDescriptor<Double> DX = GeocentricTranslation.Provider.DX;
/*     */     
/* 607 */     public static final ParameterDescriptor<Double> DY = GeocentricTranslation.Provider.DY;
/*     */     
/* 614 */     public static final ParameterDescriptor<Double> DZ = GeocentricTranslation.Provider.DZ;
/*     */     
/* 621 */     public static final ParameterDescriptor<Double> SRC_SEMI_MAJOR = createDescriptor((ReferenceIdentifier[])identifiers(GeocentricTranslation.Provider.SRC_SEMI_MAJOR), Double.NaN, 0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 629 */     public static final ParameterDescriptor<Double> SRC_SEMI_MINOR = createDescriptor((ReferenceIdentifier[])identifiers(GeocentricTranslation.Provider.SRC_SEMI_MINOR), Double.NaN, 0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 637 */     public static final ParameterDescriptor<Double> TGT_SEMI_MAJOR = createDescriptor((ReferenceIdentifier[])identifiers(GeocentricTranslation.Provider.TGT_SEMI_MAJOR), Double.NaN, 0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 645 */     public static final ParameterDescriptor<Double> TGT_SEMI_MINOR = createDescriptor((ReferenceIdentifier[])identifiers(GeocentricTranslation.Provider.TGT_SEMI_MINOR), Double.NaN, 0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/*     */     private static final NamedIdentifier[] identifiers(ParameterDescriptor parameter) {
/* 651 */       Collection<GenericName> id = parameter.getAlias();
/* 652 */       return id.<NamedIdentifier>toArray(new NamedIdentifier[id.size()]);
/*     */     }
/*     */     
/* 658 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Molodenski"), new NamedIdentifier(Citations.EPSG, "Molodenski"), new NamedIdentifier(Citations.EPSG, "9604"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(143)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { DIM, DX, DY, DZ, SRC_SEMI_MAJOR, SRC_SEMI_MINOR, TGT_SEMI_MAJOR, TGT_SEMI_MINOR });
/*     */     
/*     */     private transient Provider withHeight;
/*     */     
/*     */     public Provider() {
/* 680 */       super(2, 2, PARAMETERS);
/*     */     }
/*     */     
/*     */     Provider(int sourceDimensions, int targetDimensions, ParameterDescriptorGroup parameters) {
/* 694 */       super(sourceDimensions, targetDimensions, parameters);
/*     */     }
/*     */     
/*     */     public Class<Transformation> getOperationType() {
/* 702 */       return Transformation.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException {
/*     */       boolean hasHeight;
/* 716 */       int dim = intValue(DIM, values);
/* 717 */       switch (dim) {
/*     */         case 0:
/*     */         case 2:
/* 720 */           hasHeight = false;
/*     */           break;
/*     */         case 3:
/* 724 */           hasHeight = true;
/* 725 */           if (this.withHeight == null)
/* 726 */             this.withHeight = create3D(); 
/*     */           break;
/*     */         default:
/* 731 */           throw new IllegalArgumentException(Errors.format(58, "dim", Integer.valueOf(dim)));
/*     */       } 
/* 735 */       double a = doubleValue(SRC_SEMI_MAJOR, values);
/* 736 */       double b = doubleValue(SRC_SEMI_MINOR, values);
/* 737 */       double ta = doubleValue(TGT_SEMI_MAJOR, values);
/* 738 */       double tb = doubleValue(TGT_SEMI_MINOR, values);
/* 739 */       double dx = doubleValue(DX, values);
/* 740 */       double dy = doubleValue(DY, values);
/* 741 */       double dz = doubleValue(DZ, values);
/* 742 */       boolean abridged = isAbridged();
/* 743 */       if (!hasHeight)
/* 744 */         return new MolodenskiTransform.As2D(abridged, a, b, ta, tb, dx, dy, dz); 
/* 746 */       return (MathTransform)new MathTransformProvider.Delegate(new MolodenskiTransform(abridged, a, b, hasHeight, ta, tb, hasHeight, dx, dy, dz), (OperationMethod)this.withHeight);
/*     */     }
/*     */     
/*     */     Provider create3D() {
/* 756 */       return new Provider(3, 3, PARAMETERS);
/*     */     }
/*     */     
/*     */     boolean isAbridged() {
/* 764 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ProviderAbridged extends Provider {
/*     */     private static final long serialVersionUID = 9148242601566635131L;
/*     */     
/* 791 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Abridged_Molodenski"), new NamedIdentifier(Citations.EPSG, "Abridged Molodenski"), new NamedIdentifier(Citations.EPSG, "9605"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.format(1)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { DIM, DX, DY, DZ, SRC_SEMI_MAJOR, SRC_SEMI_MINOR, TGT_SEMI_MAJOR, TGT_SEMI_MINOR });
/*     */     
/*     */     public ProviderAbridged() {
/* 807 */       super(2, 2, PARAMETERS);
/*     */     }
/*     */     
/*     */     private ProviderAbridged(int sourceDimensions, int targetDimensions, ParameterDescriptorGroup parameters) {
/* 821 */       super(sourceDimensions, targetDimensions, parameters);
/*     */     }
/*     */     
/*     */     MolodenskiTransform.Provider create3D() {
/* 829 */       return new ProviderAbridged(3, 3, PARAMETERS);
/*     */     }
/*     */     
/*     */     boolean isAbridged() {
/* 837 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\MolodenskiTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */