/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.vecmath.GMatrix;
/*     */ import javax.vecmath.MismatchedSizeException;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.geotools.referencing.operation.transform.ProjectiveTransform;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class ProjectiveTransformBuilder extends MathTransformBuilder {
/*     */   protected GeneralMatrix A;
/*     */   
/*  68 */   protected GeneralMatrix P = null;
/*     */   
/*     */   protected GeneralMatrix X;
/*     */   
/*     */   protected ProjectiveTransformBuilder() {}
/*     */   
/*     */   public ProjectiveTransformBuilder(List<MappedPosition> vectors) throws MismatchedSizeException, MismatchedDimensionException, MismatchedReferenceSystemException {
/*  93 */     setMappedPositions(vectors);
/*     */   }
/*     */   
/*     */   public int getMinimumPointCount() {
/* 105 */     return 4;
/*     */   }
/*     */   
/*     */   public Class<? extends CartesianCS> getCoordinateSystemType() {
/* 115 */     return CartesianCS.class;
/*     */   }
/*     */   
/*     */   protected void fillPMatrix() throws MissingInfoException {
/* 125 */     this.P = new GeneralMatrix(getMappedPositions().size() * 2, getMappedPositions().size() * 2);
/* 128 */     for (int i = 0; i < getMappedPositions().size(); i += 2) {
/* 129 */       if (Double.compare(((MappedPosition)getMappedPositions().get(i)).getAccuracy(), Double.NaN) == 0)
/* 132 */         throw new MissingInfoException("Accuracy has to be defined for all points"); 
/* 137 */       this.P.setElement(i, i, 1.0D / ((MappedPosition)getMappedPositions().get(i)).getAccuracy());
/* 140 */       this.P.setElement(i + 1, i + 1, 1.0D / ((MappedPosition)getMappedPositions().get(i)).getAccuracy());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void fillAMatrix() {
/* 150 */     DirectPosition[] sourcePoints = getSourcePoints();
/* 151 */     DirectPosition[] targetPoints = getTargetPoints();
/* 152 */     this.A = new GeneralMatrix(2 * sourcePoints.length, 8);
/* 154 */     int numRow = 2 * sourcePoints.length;
/*     */     int j;
/* 157 */     for (j = 0; j < 2 * sourcePoints.length / 2; j++) {
/* 158 */       double xs = sourcePoints[j].getCoordinate()[0];
/* 159 */       double ys = sourcePoints[j].getCoordinate()[1];
/* 160 */       double xd = targetPoints[j].getCoordinate()[0];
/* 162 */       this.A.setRow(j, new double[] { xs, ys, 1.0D, 0.0D, 0.0D, 0.0D, -xd * xs, -xd * ys });
/*     */     } 
/* 166 */     for (j = numRow / 2; j < numRow; j++) {
/* 167 */       double xs = sourcePoints[j - numRow / 2].getCoordinate()[0];
/* 168 */       double ys = sourcePoints[j - numRow / 2].getCoordinate()[1];
/* 169 */       double yd = targetPoints[j - numRow / 2].getCoordinate()[1];
/* 171 */       this.A.setRow(j, new double[] { 0.0D, 0.0D, 0.0D, xs, ys, 1.0D, -yd * xs, -yd * ys });
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void fillXMatrix() {
/* 180 */     this.X = new GeneralMatrix(2 * (getTargetPoints()).length, 1);
/* 182 */     int numRow = this.X.getNumRow();
/*     */     int j;
/* 185 */     for (j = 0; j < numRow / 2; j++)
/* 186 */       this.X.setElement(j, 0, getTargetPoints()[j].getCoordinate()[0]); 
/* 189 */     for (j = numRow / 2; j < numRow; j++)
/* 190 */       this.X.setElement(j, 0, getTargetPoints()[j - numRow / 2].getCoordinate()[1]); 
/*     */   }
/*     */   
/*     */   public void includeWeights(boolean include) throws MissingInfoException {
/* 202 */     this.P = new GeneralMatrix(getMappedPositions().size() * 2, getMappedPositions().size() * 2);
/* 205 */     if (include) {
/* 206 */       fillPMatrix();
/*     */     } else {
/* 208 */       for (int j = 0; j < getMappedPositions().size(); j++)
/* 209 */         this.P.setElement(j, j, 1.0D); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected double[] calculateLSM() {
/* 224 */     fillAMatrix();
/* 226 */     fillXMatrix();
/* 228 */     if (this.P == null)
/*     */       try {
/* 230 */         includeWeights(false);
/* 231 */       } catch (FactoryException e) {} 
/* 236 */     GeneralMatrix AT = this.A.clone();
/* 237 */     AT.transpose();
/* 239 */     GeneralMatrix ATP = new GeneralMatrix(AT.getNumRow(), this.P.getNumCol());
/* 240 */     GeneralMatrix ATPA = new GeneralMatrix(AT.getNumRow(), this.A.getNumCol());
/* 241 */     GeneralMatrix ATPX = new GeneralMatrix(AT.getNumRow(), 1);
/* 242 */     GeneralMatrix x = new GeneralMatrix(this.A.getNumCol(), 1);
/* 243 */     ATP.mul((GMatrix)AT, (GMatrix)this.P);
/* 244 */     ATPA.mul((GMatrix)ATP, (GMatrix)this.A);
/* 245 */     ATPX.mul((GMatrix)ATP, (GMatrix)this.X);
/* 246 */     ATPA.invert();
/* 247 */     x.mul((GMatrix)ATPA, (GMatrix)ATPX);
/* 248 */     ATPA.invert();
/* 250 */     x.transpose();
/* 252 */     return x.getElements()[0];
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getProjectiveMatrix() {
/* 268 */     GeneralMatrix M = new GeneralMatrix(3, 3);
/* 271 */     double[] param = calculateLSM();
/* 272 */     double[] m0 = { param[0], param[1], param[2] };
/* 273 */     double[] m1 = { param[3], param[4], param[5] };
/* 274 */     double[] m2 = { param[6], param[7], 1.0D };
/* 275 */     M.setRow(0, m0);
/* 276 */     M.setRow(1, m1);
/* 277 */     M.setRow(2, m2);
/* 279 */     return M;
/*     */   }
/*     */   
/*     */   protected MathTransform computeMathTransform() {
/* 283 */     return (MathTransform)ProjectiveTransform.create((Matrix)getProjectiveMatrix());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\ProjectiveTransformBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */