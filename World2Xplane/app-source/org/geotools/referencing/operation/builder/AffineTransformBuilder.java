/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.vecmath.MismatchedSizeException;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*     */ 
/*     */ public class AffineTransformBuilder extends ProjectiveTransformBuilder {
/*     */   protected AffineTransformBuilder() {}
/*     */   
/*     */   public AffineTransformBuilder(List<MappedPosition> vectors) throws MismatchedSizeException, MismatchedDimensionException, MismatchedReferenceSystemException {
/*  73 */     setMappedPositions(vectors);
/*     */   }
/*     */   
/*     */   public int getMinimumPointCount() {
/*  84 */     return 3;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getProjectiveMatrix() {
/* 101 */     GeneralMatrix M = new GeneralMatrix(3, 3);
/* 102 */     double[] param = calculateLSM();
/* 103 */     double[] m0 = { param[0], param[1], param[2] };
/* 104 */     double[] m1 = { param[3], param[4], param[5] };
/* 105 */     double[] m2 = { 0.0D, 0.0D, 1.0D };
/* 106 */     M.setRow(0, m0);
/* 107 */     M.setRow(1, m1);
/* 108 */     M.setRow(2, m2);
/* 110 */     return M;
/*     */   }
/*     */   
/*     */   protected void fillAMatrix() {
/* 115 */     this.A = new GeneralMatrix(2 * (getSourcePoints()).length, 6);
/* 117 */     int numRow = (getSourcePoints()).length * 2;
/*     */     int j;
/* 120 */     for (j = 0; j < numRow / 2; j++) {
/* 121 */       this.A.setRow(j, new double[] { getSourcePoints()[j].getCoordinate()[0], getSourcePoints()[j].getCoordinate()[1], 1.0D, 0.0D, 0.0D, 0.0D });
/*     */     } 
/* 128 */     for (j = numRow / 2; j < numRow; j++) {
/* 129 */       this.A.setRow(j, new double[] { 0.0D, 0.0D, 0.0D, getSourcePoints()[j - numRow / 2].getCoordinate()[0], getSourcePoints()[j - numRow / 2].getCoordinate()[1], 1.0D });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\AffineTransformBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */