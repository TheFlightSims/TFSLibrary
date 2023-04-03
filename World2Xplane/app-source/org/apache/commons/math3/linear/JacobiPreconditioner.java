/*    */ package org.apache.commons.math3.linear;
/*    */ 
/*    */ public class JacobiPreconditioner extends RealLinearOperator {
/*    */   private final ArrayRealVector diag;
/*    */   
/*    */   public JacobiPreconditioner(double[] diag, boolean deep) {
/* 44 */     this.diag = new ArrayRealVector(diag, deep);
/*    */   }
/*    */   
/*    */   public static JacobiPreconditioner create(RealLinearOperator a) throws NonSquareOperatorException {
/* 62 */     int n = a.getColumnDimension();
/* 63 */     if (a.getRowDimension() != n)
/* 64 */       throw new NonSquareOperatorException(a.getRowDimension(), n); 
/* 66 */     double[] diag = new double[n];
/* 67 */     if (a instanceof AbstractRealMatrix) {
/* 68 */       AbstractRealMatrix m = (AbstractRealMatrix)a;
/* 69 */       for (int i = 0; i < n; i++)
/* 70 */         diag[i] = m.getEntry(i, i); 
/*    */     } else {
/* 73 */       ArrayRealVector x = new ArrayRealVector(n);
/* 74 */       for (int i = 0; i < n; i++) {
/* 75 */         x.set(0.0D);
/* 76 */         x.setEntry(i, 1.0D);
/* 77 */         diag[i] = a.operate(x).getEntry(i);
/*    */       } 
/*    */     } 
/* 80 */     return new JacobiPreconditioner(diag, false);
/*    */   }
/*    */   
/*    */   public int getColumnDimension() {
/* 86 */     return this.diag.getDimension();
/*    */   }
/*    */   
/*    */   public int getRowDimension() {
/* 92 */     return this.diag.getDimension();
/*    */   }
/*    */   
/*    */   public RealVector operate(RealVector x) {
/* 99 */     return x.ebeDivide(this.diag);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\JacobiPreconditioner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */