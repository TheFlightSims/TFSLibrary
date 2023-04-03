/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.parameter.DefaultParameterDescriptor;
/*     */ import org.geotools.parameter.FloatParameter;
/*     */ import org.geotools.parameter.ParameterGroup;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.operation.MathTransformProvider;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterNameException;
/*     */ import org.opengis.parameter.InvalidParameterValueException;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform1D;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ 
/*     */ public class LogarithmicTransform1D extends AbstractMathTransform implements MathTransform1D, Serializable {
/*     */   private static final long serialVersionUID = 1535101265352133948L;
/*     */   
/*     */   private static final double EPS = 1.0E-8D;
/*     */   
/*     */   public final double base;
/*     */   
/*     */   final double lnBase;
/*     */   
/*     */   public final double offset;
/*     */   
/*     */   private MathTransform1D inverse;
/*     */   
/*     */   private LogarithmicTransform1D(ExponentialTransform1D inverse) {
/* 103 */     this.base = inverse.base;
/* 104 */     this.lnBase = inverse.lnBase;
/* 105 */     this.offset = -Math.log(inverse.scale) / this.lnBase;
/* 106 */     this.inverse = inverse;
/*     */   }
/*     */   
/*     */   protected LogarithmicTransform1D(double base, double offset) {
/* 118 */     this.base = base;
/* 119 */     this.offset = offset;
/* 120 */     this.lnBase = Math.log(base);
/*     */   }
/*     */   
/*     */   static LogarithmicTransform1D create(ExponentialTransform1D inverse) {
/* 128 */     if (Math.abs(inverse.base - 10.0D) < 1.0E-8D)
/* 129 */       return new Base10(inverse); 
/* 131 */     return new LogarithmicTransform1D(inverse);
/*     */   }
/*     */   
/*     */   public static MathTransform1D create(double base, double offset) {
/* 142 */     if (base == Double.POSITIVE_INFINITY || Math.abs(base) <= 1.0E-8D)
/* 143 */       return LinearTransform1D.create(0.0D, offset); 
/* 145 */     if (Math.abs(base - 10.0D) < 1.0E-8D)
/* 146 */       return new Base10(offset); 
/* 148 */     return new LogarithmicTransform1D(base, offset);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 156 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 166 */     return (ParameterValueGroup)new ParameterGroup(getParameterDescriptors(), (GeneralParameterValue[])new ParameterValue[] { (ParameterValue)new FloatParameter(Provider.BASE, this.base), (ParameterValue)new FloatParameter(Provider.OFFSET, this.offset) });
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 176 */     return 1;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 183 */     return 1;
/*     */   }
/*     */   
/*     */   public MathTransform1D inverse() {
/* 191 */     if (this.inverse == null)
/* 192 */       this.inverse = new ExponentialTransform1D(this); 
/* 194 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public double derivative(double value) {
/* 201 */     return 1.0D / this.lnBase * value;
/*     */   }
/*     */   
/*     */   public double transform(double value) {
/* 208 */     return Math.log(value) / this.lnBase + this.offset;
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 218 */     if (srcPts != dstPts || srcOff >= dstOff) {
/* 219 */       while (--numPts >= 0)
/* 220 */         dstPts[dstOff++] = (float)(Math.log(srcPts[srcOff++]) / this.lnBase + this.offset); 
/*     */     } else {
/* 223 */       srcOff += numPts;
/* 224 */       dstOff += numPts;
/* 225 */       while (--numPts >= 0)
/* 226 */         dstPts[--dstOff] = (float)(Math.log(srcPts[srcOff++]) / this.lnBase + this.offset); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 237 */     if (srcPts != dstPts || srcOff >= dstOff) {
/* 238 */       while (--numPts >= 0)
/* 239 */         dstPts[dstOff++] = Math.log(srcPts[srcOff++]) / this.lnBase + this.offset; 
/*     */     } else {
/* 242 */       srcOff += numPts;
/* 243 */       dstOff += numPts;
/* 244 */       while (--numPts >= 0)
/* 245 */         dstPts[--dstOff] = Math.log(srcPts[srcOff++]) / this.lnBase + this.offset; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class Base10 extends LogarithmicTransform1D {
/*     */     private static final long serialVersionUID = -5435804027536647558L;
/*     */     
/*     */     Base10(ExponentialTransform1D inverse) {
/* 259 */       super(inverse);
/*     */     }
/*     */     
/*     */     protected Base10(double offset) {
/* 264 */       super(10.0D, offset);
/*     */     }
/*     */     
/*     */     public double transform(double value) {
/* 270 */       return Math.log10(value) + this.offset;
/*     */     }
/*     */     
/*     */     public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 278 */       if (srcPts != dstPts || srcOff >= dstOff) {
/* 279 */         while (--numPts >= 0)
/* 280 */           dstPts[dstOff++] = (float)(Math.log10(srcPts[srcOff++]) + this.offset); 
/*     */       } else {
/* 283 */         srcOff += numPts;
/* 284 */         dstOff += numPts;
/* 285 */         while (--numPts >= 0)
/* 286 */           dstPts[--dstOff] = (float)(Math.log10(srcPts[srcOff++]) + this.offset); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 296 */       if (srcPts != dstPts || srcOff >= dstOff) {
/* 297 */         while (--numPts >= 0)
/* 298 */           dstPts[dstOff++] = Math.log10(srcPts[srcOff++]) + this.offset; 
/*     */       } else {
/* 301 */         srcOff += numPts;
/* 302 */         dstOff += numPts;
/* 303 */         while (--numPts >= 0)
/* 304 */           dstPts[--dstOff] = Math.log10(srcPts[srcOff++]) + this.offset; 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   MathTransform concatenate(MathTransform other, boolean applyOtherFirst) {
/* 324 */     if (other instanceof org.geotools.referencing.operation.LinearTransform) {
/* 325 */       LinearTransform1D linear = (LinearTransform1D)other;
/* 326 */       if (applyOtherFirst) {
/* 327 */         if (linear.offset == 0.0D && linear.scale > 0.0D)
/* 328 */           return (MathTransform)create(this.base, Math.log(linear.scale) / this.lnBase + this.offset); 
/*     */       } else {
/* 331 */         double newBase = Math.pow(this.base, 1.0D / linear.scale);
/* 332 */         if (!Double.isNaN(newBase))
/* 333 */           return (MathTransform)create(newBase, linear.scale * this.offset + linear.offset); 
/*     */       } 
/* 336 */     } else if (other instanceof ExponentialTransform1D) {
/* 337 */       return ((ExponentialTransform1D)other).concatenateLog(this, !applyOtherFirst);
/*     */     } 
/* 339 */     return super.concatenate(other, applyOtherFirst);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 350 */     long code = 1535101265352133948L + Double.doubleToLongBits(this.base);
/* 351 */     code = code * 37L + Double.doubleToLongBits(this.offset);
/* 352 */     return (int)(code >>> 32L) ^ (int)code;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 360 */     if (object == this)
/* 362 */       return true; 
/* 364 */     if (super.equals(object)) {
/* 365 */       LogarithmicTransform1D that = (LogarithmicTransform1D)object;
/* 366 */       return (Double.doubleToLongBits(this.base) == Double.doubleToLongBits(that.base) && Double.doubleToLongBits(this.offset) == Double.doubleToLongBits(that.offset));
/*     */     } 
/* 369 */     return false;
/*     */   }
/*     */   
/*     */   public static class Provider extends MathTransformProvider {
/*     */     private static final long serialVersionUID = -7235097164208708484L;
/*     */     
/* 388 */     public static final ParameterDescriptor<Double> BASE = (ParameterDescriptor<Double>)DefaultParameterDescriptor.create("base", 10.0D, 0.0D, Double.POSITIVE_INFINITY, Unit.ONE);
/*     */     
/* 395 */     public static final ParameterDescriptor<Double> OFFSET = (ParameterDescriptor<Double>)DefaultParameterDescriptor.create("offset", 0.0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Unit.ONE);
/*     */     
/* 401 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(130)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { BASE, OFFSET });
/*     */     
/*     */     public Provider() {
/* 412 */       super(1, 1, PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<Conversion> getOperationType() {
/* 420 */       return Conversion.class;
/*     */     }
/*     */     
/*     */     protected MathTransform1D createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException {
/* 433 */       return LogarithmicTransform1D.create(doubleValue(BASE, values), doubleValue(OFFSET, values));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\LogarithmicTransform1D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */