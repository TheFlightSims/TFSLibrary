/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ 
/*     */ public abstract class RealLinearOperator {
/*     */   public abstract int getRowDimension();
/*     */   
/*     */   public abstract int getColumnDimension();
/*     */   
/*     */   public abstract RealVector operate(RealVector paramRealVector);
/*     */   
/*     */   public RealVector operateTranspose(RealVector x) throws DimensionMismatchException, UnsupportedOperationException {
/*  94 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean isTransposable() {
/* 107 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\RealLinearOperator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */