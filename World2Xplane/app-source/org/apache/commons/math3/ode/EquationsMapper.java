/*    */ package org.apache.commons.math3.ode;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*    */ 
/*    */ public class EquationsMapper implements Serializable {
/*    */   private static final long serialVersionUID = 20110925L;
/*    */   
/*    */   private final int firstIndex;
/*    */   
/*    */   private final int dimension;
/*    */   
/*    */   public EquationsMapper(int firstIndex, int dimension) {
/* 50 */     this.firstIndex = firstIndex;
/* 51 */     this.dimension = dimension;
/*    */   }
/*    */   
/*    */   public int getFirstIndex() {
/* 58 */     return this.firstIndex;
/*    */   }
/*    */   
/*    */   public int getDimension() {
/* 65 */     return this.dimension;
/*    */   }
/*    */   
/*    */   public void extractEquationData(double[] complete, double[] equationData) throws DimensionMismatchException {
/* 77 */     if (equationData.length != this.dimension)
/* 78 */       throw new DimensionMismatchException(equationData.length, this.dimension); 
/* 80 */     System.arraycopy(complete, this.firstIndex, equationData, 0, this.dimension);
/*    */   }
/*    */   
/*    */   public void insertEquationData(double[] equationData, double[] complete) throws DimensionMismatchException {
/* 92 */     if (equationData.length != this.dimension)
/* 93 */       throw new DimensionMismatchException(equationData.length, this.dimension); 
/* 95 */     System.arraycopy(equationData, 0, complete, this.firstIndex, this.dimension);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\EquationsMapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */