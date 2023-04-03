/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.linear.ArrayRealVector;
/*     */ import org.apache.commons.math3.linear.RealVector;
/*     */ import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class MicrosphereInterpolatingFunction implements MultivariateFunction {
/*     */   private final int dimension;
/*     */   
/*     */   private final List<MicrosphereSurfaceElement> microsphere;
/*     */   
/*     */   private final double brightnessExponent;
/*     */   
/*     */   private final Map<RealVector, Double> samples;
/*     */   
/*     */   private static class MicrosphereSurfaceElement {
/*     */     private final RealVector normal;
/*     */     
/*     */     private double brightestIllumination;
/*     */     
/*     */     private Map.Entry<RealVector, Double> brightestSample;
/*     */     
/*     */     MicrosphereSurfaceElement(double[] n) {
/*  78 */       this.normal = (RealVector)new ArrayRealVector(n);
/*     */     }
/*     */     
/*     */     RealVector normal() {
/*  86 */       return this.normal;
/*     */     }
/*     */     
/*     */     void reset() {
/*  93 */       this.brightestIllumination = 0.0D;
/*  94 */       this.brightestSample = null;
/*     */     }
/*     */     
/*     */     void store(double illuminationFromSample, Map.Entry<RealVector, Double> sample) {
/* 104 */       if (illuminationFromSample > this.brightestIllumination) {
/* 105 */         this.brightestIllumination = illuminationFromSample;
/* 106 */         this.brightestSample = sample;
/*     */       } 
/*     */     }
/*     */     
/*     */     double illumination() {
/* 115 */       return this.brightestIllumination;
/*     */     }
/*     */     
/*     */     Map.Entry<RealVector, Double> sample() {
/* 123 */       return this.brightestSample;
/*     */     }
/*     */   }
/*     */   
/*     */   public MicrosphereInterpolatingFunction(double[][] xval, double[] yval, int brightnessExponent, int microsphereElements, UnitSphereRandomVectorGenerator rand) {
/* 151 */     if (xval == null || yval == null)
/* 153 */       throw new NullArgumentException(); 
/* 155 */     if (xval.length == 0)
/* 156 */       throw new NoDataException(); 
/* 158 */     if (xval.length != yval.length)
/* 159 */       throw new DimensionMismatchException(xval.length, yval.length); 
/* 161 */     if (xval[0] == null)
/* 162 */       throw new NullArgumentException(); 
/* 165 */     this.dimension = (xval[0]).length;
/* 166 */     this.brightnessExponent = brightnessExponent;
/* 169 */     this.samples = new HashMap<RealVector, Double>(yval.length);
/*     */     int i;
/* 170 */     for (i = 0; i < xval.length; i++) {
/* 171 */       double[] xvalI = xval[i];
/* 172 */       if (xvalI == null)
/* 173 */         throw new NullArgumentException(); 
/* 175 */       if (xvalI.length != this.dimension)
/* 176 */         throw new DimensionMismatchException(xvalI.length, this.dimension); 
/* 179 */       this.samples.put(new ArrayRealVector(xvalI), Double.valueOf(yval[i]));
/*     */     } 
/* 182 */     this.microsphere = new ArrayList<MicrosphereSurfaceElement>(microsphereElements);
/* 185 */     for (i = 0; i < microsphereElements; i++)
/* 186 */       this.microsphere.add(new MicrosphereSurfaceElement(rand.nextVector())); 
/*     */   }
/*     */   
/*     */   public double value(double[] point) {
/* 195 */     ArrayRealVector arrayRealVector = new ArrayRealVector(point);
/* 198 */     for (MicrosphereSurfaceElement md : this.microsphere)
/* 199 */       md.reset(); 
/* 203 */     for (Map.Entry<RealVector, Double> sd : this.samples.entrySet()) {
/* 206 */       RealVector diff = ((RealVector)sd.getKey()).subtract((RealVector)arrayRealVector);
/* 207 */       double diffNorm = diff.getNorm();
/* 209 */       if (FastMath.abs(diffNorm) < FastMath.ulp(1.0D))
/* 212 */         return ((Double)sd.getValue()).doubleValue(); 
/* 215 */       for (MicrosphereSurfaceElement md : this.microsphere) {
/* 216 */         double w = FastMath.pow(diffNorm, -this.brightnessExponent);
/* 217 */         md.store(cosAngle(diff, md.normal()) * w, sd);
/*     */       } 
/*     */     } 
/* 223 */     double value = 0.0D;
/* 224 */     double totalWeight = 0.0D;
/* 225 */     for (MicrosphereSurfaceElement md : this.microsphere) {
/* 226 */       double iV = md.illumination();
/* 227 */       Map.Entry<RealVector, Double> sd = md.sample();
/* 228 */       if (sd != null) {
/* 229 */         value += iV * ((Double)sd.getValue()).doubleValue();
/* 230 */         totalWeight += iV;
/*     */       } 
/*     */     } 
/* 234 */     return value / totalWeight;
/*     */   }
/*     */   
/*     */   private double cosAngle(RealVector v, RealVector w) {
/* 245 */     return v.dotProduct(w) / v.getNorm() * w.getNorm();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\MicrosphereInterpolatingFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */