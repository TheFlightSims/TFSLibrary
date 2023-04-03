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
/*     */ public class ExponentialTransform1D extends AbstractMathTransform implements MathTransform1D, Serializable {
/*     */   private static final long serialVersionUID = 5331178990358868947L;
/*     */   
/*     */   public final double base;
/*     */   
/*     */   final double lnBase;
/*     */   
/*     */   public final double scale;
/*     */   
/*     */   private MathTransform1D inverse;
/*     */   
/*     */   ExponentialTransform1D(LogarithmicTransform1D inverse) {
/*  99 */     this.base = inverse.base;
/* 100 */     this.lnBase = inverse.lnBase;
/* 101 */     this.scale = Math.pow(this.base, -inverse.offset);
/* 102 */     this.inverse = inverse;
/*     */   }
/*     */   
/*     */   protected ExponentialTransform1D(double base, double scale) {
/* 114 */     this.base = base;
/* 115 */     this.scale = scale;
/* 116 */     this.lnBase = Math.log(base);
/*     */   }
/*     */   
/*     */   public static MathTransform1D create(double base, double scale) {
/* 127 */     if (base == 0.0D || scale == 0.0D)
/* 128 */       return LinearTransform1D.create(0.0D, 0.0D); 
/* 130 */     if (base == 1.0D)
/* 131 */       return LinearTransform1D.create(0.0D, scale); 
/* 133 */     return new ExponentialTransform1D(base, scale);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 141 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 151 */     return (ParameterValueGroup)new ParameterGroup(getParameterDescriptors(), (GeneralParameterValue[])new ParameterValue[] { (ParameterValue)new FloatParameter(Provider.BASE, this.base), (ParameterValue)new FloatParameter(Provider.SCALE, this.scale) });
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 161 */     return 1;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 168 */     return 1;
/*     */   }
/*     */   
/*     */   public MathTransform1D inverse() {
/* 176 */     if (this.inverse == null)
/* 177 */       this.inverse = LogarithmicTransform1D.create(this); 
/* 179 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public double derivative(double value) {
/* 186 */     return this.lnBase * transform(value);
/*     */   }
/*     */   
/*     */   public double transform(double value) {
/* 193 */     return this.scale * Math.pow(this.base, value);
/*     */   }
/*     */   
/*     */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 203 */     if (srcPts != dstPts || srcOff >= dstOff) {
/* 204 */       while (--numPts >= 0)
/* 205 */         dstPts[dstOff++] = (float)(this.scale * Math.pow(this.base, srcPts[srcOff++])); 
/*     */     } else {
/* 208 */       srcOff += numPts;
/* 209 */       dstOff += numPts;
/* 210 */       while (--numPts >= 0)
/* 211 */         dstPts[--dstOff] = (float)(this.scale * Math.pow(this.base, srcPts[--srcOff])); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 222 */     if (srcPts != dstPts || srcOff >= dstOff) {
/* 223 */       while (--numPts >= 0)
/* 224 */         dstPts[dstOff++] = this.scale * Math.pow(this.base, srcPts[srcOff++]); 
/*     */     } else {
/* 227 */       srcOff += numPts;
/* 228 */       dstOff += numPts;
/* 229 */       while (--numPts >= 0)
/* 230 */         dstPts[--dstOff] = this.scale * Math.pow(this.base, srcPts[--srcOff]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   MathTransform concatenate(MathTransform other, boolean applyOtherFirst) {
/* 249 */     if (other instanceof org.geotools.referencing.operation.LinearTransform) {
/* 250 */       LinearTransform1D linear = (LinearTransform1D)other;
/* 251 */       if (applyOtherFirst) {
/* 252 */         double newBase = Math.pow(this.base, linear.scale);
/* 253 */         double newScale = Math.pow(this.base, linear.offset) * this.scale;
/* 254 */         if (!Double.isNaN(newBase) && !Double.isNaN(newScale))
/* 255 */           return (MathTransform)create(newBase, newScale); 
/* 258 */       } else if (linear.offset == 0.0D) {
/* 259 */         return (MathTransform)create(this.base, this.scale * linear.scale);
/*     */       } 
/* 262 */     } else if (other instanceof LogarithmicTransform1D) {
/* 263 */       return concatenateLog((LogarithmicTransform1D)other, applyOtherFirst);
/*     */     } 
/* 265 */     return super.concatenate(other, applyOtherFirst);
/*     */   }
/*     */   
/*     */   MathTransform concatenateLog(LogarithmicTransform1D other, boolean applyOtherFirst) {
/* 280 */     if (applyOtherFirst) {
/* 281 */       double newScale = this.scale * Math.pow(this.base, other.offset);
/* 282 */       double newPower = this.lnBase / other.lnBase;
/* 283 */       if (!Double.isNaN(newScale) && 
/* 284 */         newPower == 1.0D)
/* 285 */         return LinearTransform1D.create(newScale, 0.0D); 
/* 291 */     } else if (this.scale > 0.0D) {
/* 292 */       return LinearTransform1D.create(this.lnBase / other.lnBase, Math.log(this.scale) / other.lnBase + other.offset);
/*     */     } 
/* 295 */     return null;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 306 */     long code = 5331178990358868947L + Double.doubleToLongBits(this.base);
/* 307 */     code = code * 37L + Double.doubleToLongBits(this.scale);
/* 308 */     return (int)(code >>> 32L) ^ (int)code;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 316 */     if (object == this)
/* 318 */       return true; 
/* 320 */     if (super.equals(object)) {
/* 321 */       ExponentialTransform1D that = (ExponentialTransform1D)object;
/* 322 */       return (Double.doubleToLongBits(this.base) == Double.doubleToLongBits(that.base) && Double.doubleToLongBits(this.scale) == Double.doubleToLongBits(that.scale));
/*     */     } 
/* 325 */     return false;
/*     */   }
/*     */   
/*     */   public static class Provider extends MathTransformProvider {
/*     */     private static final long serialVersionUID = -5838840021166379987L;
/*     */     
/* 344 */     public static final ParameterDescriptor BASE = LogarithmicTransform1D.Provider.BASE;
/*     */     
/* 350 */     public static final ParameterDescriptor SCALE = (ParameterDescriptor)DefaultParameterDescriptor.create("scale", 1.0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Unit.ONE);
/*     */     
/* 356 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(68)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { BASE, SCALE });
/*     */     
/*     */     public Provider() {
/* 367 */       super(1, 1, PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<Conversion> getOperationType() {
/* 375 */       return Conversion.class;
/*     */     }
/*     */     
/*     */     protected MathTransform1D createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException {
/* 388 */       return ExponentialTransform1D.create(doubleValue(BASE, values), doubleValue(SCALE, values));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\ExponentialTransform1D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */