/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.vecmath.MismatchedSizeException;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*     */ 
/*     */ public class SimilarTransformBuilder extends ProjectiveTransformBuilder {
/*     */   public SimilarTransformBuilder(List<MappedPosition> vectors) throws MismatchedSizeException, MismatchedDimensionException, MismatchedReferenceSystemException {
/*  70 */     setMappedPositions(vectors);
/*     */   }
/*     */   
/*     */   protected void fillAMatrix() {
/*  74 */     this.A = new GeneralMatrix(2 * (getSourcePoints()).length, 4);
/*  76 */     int numRow = (getSourcePoints()).length * 2;
/*     */     int j;
/*  79 */     for (j = 0; j < numRow / 2; j++) {
/*  80 */       this.A.setRow(j, new double[] { getSourcePoints()[j].getCoordinate()[0], -getSourcePoints()[j].getCoordinate()[1], 1.0D, 0.0D });
/*     */     } 
/*  87 */     for (j = numRow / 2; j < numRow; j++) {
/*  88 */       this.A.setRow(j, new double[] { getSourcePoints()[j - numRow / 2].getCoordinate()[1], getSourcePoints()[j - numRow / 2].getCoordinate()[0], 0.0D, 1.0D });
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getMinimumPointCount() {
/* 105 */     return 2;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getProjectiveMatrix() {
/* 120 */     GeneralMatrix M = new GeneralMatrix(3, 3);
/* 121 */     double[] param = calculateLSM();
/* 122 */     double[] m0 = { param[0], -param[1], param[2] };
/* 123 */     double[] m1 = { param[1], param[0], param[3] };
/* 124 */     double[] m2 = { 0.0D, 0.0D, 1.0D };
/* 125 */     M.setRow(0, m0);
/* 126 */     M.setRow(1, m1);
/* 127 */     M.setRow(2, m2);
/* 129 */     return M;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\SimilarTransformBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */