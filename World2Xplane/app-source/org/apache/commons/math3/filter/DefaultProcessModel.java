/*     */ package org.apache.commons.math3.filter;
/*     */ 
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.linear.ArrayRealVector;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.linear.RealVector;
/*     */ 
/*     */ public class DefaultProcessModel implements ProcessModel {
/*     */   private RealMatrix stateTransitionMatrix;
/*     */   
/*     */   private RealMatrix controlMatrix;
/*     */   
/*     */   private RealMatrix processNoiseCovMatrix;
/*     */   
/*     */   private RealVector initialStateEstimateVector;
/*     */   
/*     */   private RealMatrix initialErrorCovMatrix;
/*     */   
/*     */   public DefaultProcessModel(double[][] stateTransition, double[][] control, double[][] processNoise, double[] initialStateEstimate, double[][] initialErrorCovariance) {
/*  68 */     this((RealMatrix)new Array2DRowRealMatrix(stateTransition), (RealMatrix)new Array2DRowRealMatrix(control), (RealMatrix)new Array2DRowRealMatrix(processNoise), (RealVector)new ArrayRealVector(initialStateEstimate), (RealMatrix)new Array2DRowRealMatrix(initialErrorCovariance));
/*     */   }
/*     */   
/*     */   public DefaultProcessModel(double[][] stateTransition, double[][] control, double[][] processNoise) {
/*  87 */     this((RealMatrix)new Array2DRowRealMatrix(stateTransition), (RealMatrix)new Array2DRowRealMatrix(control), (RealMatrix)new Array2DRowRealMatrix(processNoise), (RealVector)null, (RealMatrix)null);
/*     */   }
/*     */   
/*     */   public DefaultProcessModel(RealMatrix stateTransition, RealMatrix control, RealMatrix processNoise, RealVector initialStateEstimate, RealMatrix initialErrorCovariance) {
/* 107 */     this.stateTransitionMatrix = stateTransition;
/* 108 */     this.controlMatrix = control;
/* 109 */     this.processNoiseCovMatrix = processNoise;
/* 110 */     this.initialStateEstimateVector = initialStateEstimate;
/* 111 */     this.initialErrorCovMatrix = initialErrorCovariance;
/*     */   }
/*     */   
/*     */   public RealMatrix getStateTransitionMatrix() {
/* 116 */     return this.stateTransitionMatrix;
/*     */   }
/*     */   
/*     */   public RealMatrix getControlMatrix() {
/* 121 */     return this.controlMatrix;
/*     */   }
/*     */   
/*     */   public RealMatrix getProcessNoise() {
/* 126 */     return this.processNoiseCovMatrix;
/*     */   }
/*     */   
/*     */   public RealVector getInitialStateEstimate() {
/* 131 */     return this.initialStateEstimateVector;
/*     */   }
/*     */   
/*     */   public RealMatrix getInitialErrorCovariance() {
/* 136 */     return this.initialErrorCovMatrix;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\filter\DefaultProcessModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */