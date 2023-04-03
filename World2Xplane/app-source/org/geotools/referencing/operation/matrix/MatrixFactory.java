/*    */ package org.geotools.referencing.operation.matrix;
/*    */ 
/*    */ import org.opengis.referencing.operation.Matrix;
/*    */ 
/*    */ public final class MatrixFactory {
/*    */   public static XMatrix create(int size) {
/* 49 */     switch (size) {
/*    */       case 1:
/* 50 */         return new Matrix1();
/*    */       case 2:
/* 51 */         return new Matrix2();
/*    */       case 3:
/* 52 */         return new Matrix3();
/*    */       case 4:
/* 53 */         return new Matrix4();
/*    */     } 
/* 54 */     return new GeneralMatrix(size);
/*    */   }
/*    */   
/*    */   public static XMatrix create(int numRow, int numCol) {
/* 71 */     if (numRow == numCol)
/* 72 */       return create(numRow); 
/* 74 */     return new GeneralMatrix(numRow, numCol);
/*    */   }
/*    */   
/*    */   public static XMatrix create(Matrix matrix) {
/* 82 */     int size = matrix.getNumRow();
/* 83 */     if (size == matrix.getNumCol())
/* 84 */       switch (size) {
/*    */         case 1:
/* 85 */           return new Matrix1(matrix);
/*    */         case 2:
/* 86 */           return new Matrix2(matrix);
/*    */         case 3:
/* 87 */           return new Matrix3(matrix);
/*    */         case 4:
/* 88 */           return new Matrix4(matrix);
/*    */       }  
/* 91 */     return new GeneralMatrix(matrix);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\matrix\MatrixFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */