/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathUnsupportedOperationException;
/*     */ import org.apache.commons.math3.util.IterationEvent;
/*     */ 
/*     */ public abstract class IterativeLinearSolverEvent extends IterationEvent {
/*     */   private static final long serialVersionUID = 20120129L;
/*     */   
/*     */   public IterativeLinearSolverEvent(Object source, int iterations) {
/*  43 */     super(source, iterations);
/*     */   }
/*     */   
/*     */   public abstract RealVector getRightHandSideVector();
/*     */   
/*     */   public abstract double getNormOfResidual();
/*     */   
/*     */   public RealVector getResidual() {
/*  93 */     throw new MathUnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public abstract RealVector getSolution();
/*     */   
/*     */   public boolean providesResidual() {
/* 114 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\IterativeLinearSolverEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */