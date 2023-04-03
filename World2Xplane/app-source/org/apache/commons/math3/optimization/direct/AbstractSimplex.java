/*     */ package org.apache.commons.math3.optimization.direct;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.ZeroException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ 
/*     */ public abstract class AbstractSimplex {
/*     */   private PointValuePair[] simplex;
/*     */   
/*     */   private double[][] startConfiguration;
/*     */   
/*     */   private final int dimension;
/*     */   
/*     */   protected AbstractSimplex(int n) {
/*  64 */     this(n, 1.0D);
/*     */   }
/*     */   
/*     */   protected AbstractSimplex(int n, double sideLength) {
/*  75 */     this(createHypercubeSteps(n, sideLength));
/*     */   }
/*     */   
/*     */   protected AbstractSimplex(double[] steps) {
/*  97 */     if (steps == null)
/*  98 */       throw new NullArgumentException(); 
/* 100 */     if (steps.length == 0)
/* 101 */       throw new ZeroException(); 
/* 103 */     this.dimension = steps.length;
/* 107 */     this.startConfiguration = new double[this.dimension][this.dimension];
/* 108 */     for (int i = 0; i < this.dimension; i++) {
/* 109 */       double[] vertexI = this.startConfiguration[i];
/* 110 */       for (int j = 0; j < i + 1; j++) {
/* 111 */         if (steps[j] == 0.0D)
/* 112 */           throw new ZeroException(LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX, new Object[0]); 
/* 114 */         System.arraycopy(steps, 0, vertexI, 0, j + 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected AbstractSimplex(double[][] referenceSimplex) {
/* 132 */     if (referenceSimplex.length <= 0)
/* 133 */       throw new NotStrictlyPositiveException(LocalizedFormats.SIMPLEX_NEED_ONE_POINT, Integer.valueOf(referenceSimplex.length)); 
/* 136 */     this.dimension = referenceSimplex.length - 1;
/* 140 */     this.startConfiguration = new double[this.dimension][this.dimension];
/* 141 */     double[] ref0 = referenceSimplex[0];
/* 144 */     for (int i = 0; i < referenceSimplex.length; i++) {
/* 145 */       double[] refI = referenceSimplex[i];
/* 148 */       if (refI.length != this.dimension)
/* 149 */         throw new DimensionMismatchException(refI.length, this.dimension); 
/* 151 */       for (int j = 0; j < i; j++) {
/* 152 */         double[] refJ = referenceSimplex[j];
/* 153 */         boolean allEquals = true;
/* 154 */         for (int k = 0; k < this.dimension; k++) {
/* 155 */           if (refI[k] != refJ[k]) {
/* 156 */             allEquals = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 160 */         if (allEquals)
/* 161 */           throw new MathIllegalArgumentException(LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX, new Object[] { Integer.valueOf(i), Integer.valueOf(j) }); 
/*     */       } 
/* 167 */       if (i > 0) {
/* 168 */         double[] confI = this.startConfiguration[i - 1];
/* 169 */         for (int k = 0; k < this.dimension; k++)
/* 170 */           confI[k] = refI[k] - ref0[k]; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 182 */     return this.dimension;
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 193 */     return this.simplex.length;
/*     */   }
/*     */   
/*     */   public abstract void iterate(MultivariateFunction paramMultivariateFunction, Comparator<PointValuePair> paramComparator);
/*     */   
/*     */   public void build(double[] startPoint) {
/* 216 */     if (this.dimension != startPoint.length)
/* 217 */       throw new DimensionMismatchException(this.dimension, startPoint.length); 
/* 221 */     this.simplex = new PointValuePair[this.dimension + 1];
/* 222 */     this.simplex[0] = new PointValuePair(startPoint, Double.NaN);
/* 225 */     for (int i = 0; i < this.dimension; i++) {
/* 226 */       double[] confI = this.startConfiguration[i];
/* 227 */       double[] vertexI = new double[this.dimension];
/* 228 */       for (int k = 0; k < this.dimension; k++)
/* 229 */         vertexI[k] = startPoint[k] + confI[k]; 
/* 231 */       this.simplex[i + 1] = new PointValuePair(vertexI, Double.NaN);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void evaluate(MultivariateFunction evaluationFunction, Comparator<PointValuePair> comparator) {
/* 246 */     for (int i = 0; i < this.simplex.length; i++) {
/* 247 */       PointValuePair vertex = this.simplex[i];
/* 248 */       double[] point = vertex.getPointRef();
/* 249 */       if (Double.isNaN(((Double)vertex.getValue()).doubleValue()))
/* 250 */         this.simplex[i] = new PointValuePair(point, evaluationFunction.value(point), false); 
/*     */     } 
/* 255 */     Arrays.sort(this.simplex, comparator);
/*     */   }
/*     */   
/*     */   protected void replaceWorstPoint(PointValuePair pointValuePair, Comparator<PointValuePair> comparator) {
/* 267 */     for (int i = 0; i < this.dimension; i++) {
/* 268 */       if (comparator.compare(this.simplex[i], pointValuePair) > 0) {
/* 269 */         PointValuePair tmp = this.simplex[i];
/* 270 */         this.simplex[i] = pointValuePair;
/* 271 */         pointValuePair = tmp;
/*     */       } 
/*     */     } 
/* 274 */     this.simplex[this.dimension] = pointValuePair;
/*     */   }
/*     */   
/*     */   public PointValuePair[] getPoints() {
/* 283 */     PointValuePair[] copy = new PointValuePair[this.simplex.length];
/* 284 */     System.arraycopy(this.simplex, 0, copy, 0, this.simplex.length);
/* 285 */     return copy;
/*     */   }
/*     */   
/*     */   public PointValuePair getPoint(int index) {
/* 295 */     if (index < 0 || index >= this.simplex.length)
/* 297 */       throw new OutOfRangeException(Integer.valueOf(index), Integer.valueOf(0), Integer.valueOf(this.simplex.length - 1)); 
/* 299 */     return this.simplex[index];
/*     */   }
/*     */   
/*     */   protected void setPoint(int index, PointValuePair point) {
/* 310 */     if (index < 0 || index >= this.simplex.length)
/* 312 */       throw new OutOfRangeException(Integer.valueOf(index), Integer.valueOf(0), Integer.valueOf(this.simplex.length - 1)); 
/* 314 */     this.simplex[index] = point;
/*     */   }
/*     */   
/*     */   protected void setPoints(PointValuePair[] points) {
/* 324 */     if (points.length != this.simplex.length)
/* 325 */       throw new DimensionMismatchException(points.length, this.simplex.length); 
/* 327 */     this.simplex = points;
/*     */   }
/*     */   
/*     */   private static double[] createHypercubeSteps(int n, double sideLength) {
/* 339 */     double[] steps = new double[n];
/* 340 */     for (int i = 0; i < n; i++)
/* 341 */       steps[i] = sideLength; 
/* 343 */     return steps;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\AbstractSimplex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */