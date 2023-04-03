/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathUnsupportedOperationException;
/*     */ 
/*     */ public class DefaultIterativeLinearSolverEvent extends IterativeLinearSolverEvent {
/*     */   private static final long serialVersionUID = 20120129L;
/*     */   
/*     */   private final RealVector b;
/*     */   
/*     */   private final RealVector r;
/*     */   
/*     */   private final double rnorm;
/*     */   
/*     */   private final RealVector x;
/*     */   
/*     */   public DefaultIterativeLinearSolverEvent(Object source, int iterations, RealVector x, RealVector b, RealVector r, double rnorm) {
/*  66 */     super(source, iterations);
/*  67 */     this.x = x;
/*  68 */     this.b = b;
/*  69 */     this.r = r;
/*  70 */     this.rnorm = rnorm;
/*     */   }
/*     */   
/*     */   public DefaultIterativeLinearSolverEvent(Object source, int iterations, RealVector x, RealVector b, double rnorm) {
/*  93 */     super(source, iterations);
/*  94 */     this.x = x;
/*  95 */     this.b = b;
/*  96 */     this.r = null;
/*  97 */     this.rnorm = rnorm;
/*     */   }
/*     */   
/*     */   public double getNormOfResidual() {
/* 103 */     return this.rnorm;
/*     */   }
/*     */   
/*     */   public RealVector getResidual() {
/* 113 */     if (this.r != null)
/* 114 */       return this.r; 
/* 116 */     throw new MathUnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public RealVector getRightHandSideVector() {
/* 122 */     return this.b;
/*     */   }
/*     */   
/*     */   public RealVector getSolution() {
/* 128 */     return this.x;
/*     */   }
/*     */   
/*     */   public boolean providesResidual() {
/* 141 */     return (this.r != null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\DefaultIterativeLinearSolverEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */