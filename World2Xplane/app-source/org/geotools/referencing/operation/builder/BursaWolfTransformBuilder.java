/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.vecmath.GMatrix;
/*     */ import org.geotools.referencing.datum.BursaWolfParameters;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.geotools.referencing.operation.transform.GeocentricTranslation;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class BursaWolfTransformBuilder extends MathTransformBuilder {
/*     */   private GeodeticDatum targetDatum;
/*     */   
/*     */   GeneralMatrix x;
/*     */   
/*     */   GeneralMatrix X;
/*     */   
/*  68 */   private double alfa = 0.0D;
/*     */   
/*  71 */   private double beta = 0.0D;
/*     */   
/*  74 */   private double gamma = 0.0D;
/*     */   
/*  77 */   private double dx = 0.0D;
/*     */   
/*  80 */   private double dy = 0.0D;
/*     */   
/*  83 */   private double dz = 0.0D;
/*     */   
/*  86 */   private double q = 1.0D;
/*     */   
/*     */   public BursaWolfTransformBuilder(List<MappedPosition> vectors) {
/*  94 */     setMappedPositions(vectors);
/*  96 */     this.x = new GeneralMatrix(vectors.size(), 3);
/*  97 */     this.X = new GeneralMatrix(vectors.size(), 3);
/*  98 */     this.x = getx();
/*  99 */     this.X = getX();
/* 100 */     getDxMatrix();
/*     */   }
/*     */   
/*     */   public int getMinimumPointCount() {
/* 111 */     return 3;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 123 */     return 3;
/*     */   }
/*     */   
/*     */   public Class<? extends CartesianCS> getCoordinateSystemType() {
/* 134 */     return CartesianCS.class;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getx() {
/* 143 */     DirectPosition[] sourcePoints = getSourcePoints();
/* 144 */     GeneralMatrix x = new GeneralMatrix(3 * sourcePoints.length, 1);
/* 146 */     for (int j = 0; j < x.getNumRow(); j += 3) {
/* 147 */       x.setElement(j, 0, sourcePoints[j / 3].getCoordinate()[0]);
/* 148 */       x.setElement(j + 1, 0, sourcePoints[j / 3].getCoordinate()[1]);
/* 149 */       x.setElement(j + 2, 0, sourcePoints[j / 3].getCoordinate()[2]);
/*     */     } 
/* 152 */     return x;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getX() {
/* 161 */     DirectPosition[] sourcePoints = getSourcePoints();
/* 162 */     DirectPosition[] targetPoints = getTargetPoints();
/* 163 */     GeneralMatrix X = new GeneralMatrix(3 * sourcePoints.length, 1);
/*     */     int j;
/* 165 */     for (j = 0; j < X.getNumRow(); j += 3) {
/* 166 */       X.setElement(j, 0, targetPoints[j / 3].getCoordinate()[0]);
/* 167 */       X.setElement(j + 1, 0, targetPoints[j / 3].getCoordinate()[1]);
/* 168 */       X.setElement(j + 2, 0, targetPoints[j / 3].getCoordinate()[2]);
/*     */     } 
/* 171 */     return X;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getRalfa() {
/* 180 */     GeneralMatrix Ralfa = new GeneralMatrix(3, 3);
/* 181 */     double[] m0 = { 1.0D, 0.0D, 0.0D };
/* 182 */     double[] m1 = { 0.0D, Math.cos(this.alfa), Math.sin(this.alfa) };
/* 183 */     double[] m2 = { 0.0D, -Math.sin(this.alfa), Math.cos(this.alfa) };
/* 184 */     Ralfa.setRow(0, m0);
/* 185 */     Ralfa.setRow(1, m1);
/* 186 */     Ralfa.setRow(2, m2);
/* 188 */     return Ralfa;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getRbeta() {
/* 197 */     GeneralMatrix Rbeta = new GeneralMatrix(3, 3);
/* 198 */     double[] m0 = { Math.cos(this.beta), 0.0D, -Math.sin(this.beta) };
/* 199 */     double[] m1 = { 0.0D, 1.0D, 0.0D };
/* 200 */     double[] m2 = { Math.sin(this.beta), 0.0D, Math.cos(this.beta) };
/* 201 */     Rbeta.setRow(0, m0);
/* 202 */     Rbeta.setRow(1, m1);
/* 203 */     Rbeta.setRow(2, m2);
/* 205 */     return Rbeta;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getRgamma() {
/* 214 */     GeneralMatrix Rgamma = new GeneralMatrix(3, 3);
/* 215 */     double[] m0 = { Math.cos(this.gamma), Math.sin(this.gamma), 0.0D };
/* 216 */     double[] m1 = { -Math.sin(this.gamma), Math.cos(this.gamma), 0.0D };
/* 217 */     double[] m2 = { 0.0D, 0.0D, 1.0D };
/* 218 */     Rgamma.setRow(0, m0);
/* 219 */     Rgamma.setRow(1, m1);
/* 220 */     Rgamma.setRow(2, m2);
/* 222 */     return Rgamma;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getDRalfa() {
/* 232 */     GeneralMatrix dRa = new GeneralMatrix(3, 3);
/* 234 */     double[] m0 = { 0.0D, 0.0D, 0.0D };
/* 235 */     double[] m1 = { 0.0D, -Math.sin(this.alfa), Math.cos(this.alfa) };
/* 236 */     double[] m2 = { 0.0D, -Math.cos(this.alfa), -Math.sin(this.alfa) };
/* 238 */     dRa.setRow(0, m0);
/* 239 */     dRa.setRow(1, m1);
/* 240 */     dRa.setRow(2, m2);
/* 242 */     dRa.mul((GMatrix)dRa, (GMatrix)getRbeta());
/* 243 */     dRa.mul((GMatrix)dRa, (GMatrix)getRgamma());
/* 245 */     return specialMul(dRa, this.x);
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getDRbeta() {
/* 256 */     GeneralMatrix dRb = new GeneralMatrix(3, 3);
/* 257 */     double[] m0 = { -Math.sin(this.beta), 0.0D, -Math.cos(this.beta) };
/* 258 */     double[] m1 = { 0.0D, 0.0D, 0.0D };
/* 259 */     double[] m2 = { Math.cos(this.beta), 0.0D, -Math.sin(this.beta) };
/* 260 */     dRb.setRow(0, m0);
/* 261 */     dRb.setRow(1, m1);
/* 262 */     dRb.setRow(2, m2);
/* 264 */     dRb.mul((GMatrix)getRalfa(), (GMatrix)dRb);
/* 265 */     dRb.mul((GMatrix)dRb, (GMatrix)getRgamma());
/* 267 */     return specialMul(dRb, this.x);
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getDRgamma() {
/* 278 */     GeneralMatrix dRg = new GeneralMatrix(3, 3);
/* 279 */     GeneralMatrix pom = new GeneralMatrix(3, 3);
/* 280 */     double[] m0 = { -Math.sin(this.gamma), Math.cos(this.gamma), 0.0D };
/* 281 */     double[] m1 = { -Math.cos(this.gamma), -Math.sin(this.gamma), 0.0D };
/* 282 */     double[] m2 = { 0.0D, 0.0D, 0.0D };
/* 283 */     dRg.setRow(0, m0);
/* 284 */     dRg.setRow(1, m1);
/* 285 */     dRg.setRow(2, m2);
/* 287 */     pom.mul((GMatrix)getRalfa(), (GMatrix)getRbeta());
/* 288 */     dRg.mul((GMatrix)pom, (GMatrix)dRg);
/* 290 */     return specialMul(dRg, this.x);
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getDq() {
/* 300 */     GeneralMatrix R = new GeneralMatrix(3, 3);
/* 301 */     R.mul((GMatrix)getRalfa(), (GMatrix)getRbeta());
/* 302 */     R.mul((GMatrix)R, (GMatrix)getRgamma());
/* 304 */     return specialMul(R, this.x);
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getl() {
/* 314 */     GeneralMatrix l = new GeneralMatrix(3 * getMappedPositions().size(), 1);
/* 315 */     GeneralMatrix R = new GeneralMatrix(3, 3);
/* 316 */     GeneralMatrix T = new GeneralMatrix(3, 1, new double[] { -this.dx, -this.dy, -this.dz });
/* 317 */     GeneralMatrix qMatrix = new GeneralMatrix(1, 1, new double[] { this.q });
/* 318 */     GeneralMatrix qx = new GeneralMatrix(this.X.getNumRow(), this.X.getNumCol());
/* 319 */     qx.mul((GMatrix)this.x, (GMatrix)qMatrix);
/* 320 */     R.mul((GMatrix)getRalfa(), (GMatrix)getRbeta());
/* 321 */     R.mul((GMatrix)getRgamma());
/* 323 */     l.sub((GMatrix)specialMul(R, qx), (GMatrix)this.X);
/* 324 */     l = specialSub(T, l);
/* 326 */     return l;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix specialMul(GeneralMatrix R, GeneralMatrix x) {
/* 339 */     GeneralMatrix dRx = new GeneralMatrix(3 * getMappedPositions().size(), 1);
/*     */     int i;
/* 341 */     for (i = 0; i < x.getNumRow(); i += 3) {
/* 342 */       GeneralMatrix subMatrix = new GeneralMatrix(3, 1);
/* 343 */       x.copySubMatrix(i, 0, 3, 1, 0, 0, (GMatrix)subMatrix);
/* 344 */       subMatrix.mul((GMatrix)R, (GMatrix)subMatrix);
/* 345 */       subMatrix.copySubMatrix(0, 0, 3, 1, i, 0, (GMatrix)dRx);
/*     */     } 
/* 348 */     return dRx;
/*     */   }
/*     */   
/*     */   private GeneralMatrix specialSub(GeneralMatrix R, GeneralMatrix x) {
/* 361 */     GeneralMatrix dRx = new GeneralMatrix(3 * getMappedPositions().size(), 1);
/*     */     int i;
/* 363 */     for (i = 0; i < x.getNumRow(); i += 3) {
/* 364 */       GeneralMatrix subMatrix = new GeneralMatrix(3, 1);
/* 365 */       x.copySubMatrix(i, 0, 3, 1, 0, 0, (GMatrix)subMatrix);
/* 366 */       subMatrix.sub((GMatrix)R, (GMatrix)subMatrix);
/* 367 */       subMatrix.copySubMatrix(0, 0, 3, 1, i, 0, (GMatrix)dRx);
/*     */     } 
/* 370 */     return dRx;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getA() {
/* 379 */     int size = getMappedPositions().size();
/* 380 */     GeneralMatrix A = new GeneralMatrix(3 * size, 7);
/* 381 */     GeneralMatrix DT = new GeneralMatrix(3, 3);
/* 384 */     double[] m0 = { 1.0D, 0.0D, 0.0D };
/* 385 */     double[] m1 = { 0.0D, 1.0D, 0.0D };
/* 386 */     double[] m2 = { 0.0D, 0.0D, 1.0D };
/* 387 */     DT.setRow(0, m0);
/* 388 */     DT.setRow(1, m1);
/* 389 */     DT.setRow(2, m2);
/*     */     int i;
/* 391 */     for (i = 0; i < A.getNumRow(); i += 3)
/* 392 */       DT.copySubMatrix(0, 0, 3, 3, i, 0, (GMatrix)A); 
/* 395 */     getDRalfa().copySubMatrix(0, 0, 3 * size, 1, 0, 3, (GMatrix)A);
/* 396 */     getDRbeta().copySubMatrix(0, 0, 3 * size, 1, 0, 4, (GMatrix)A);
/* 397 */     getDRgamma().copySubMatrix(0, 0, 3 * size, 1, 0, 5, (GMatrix)A);
/* 398 */     getDq().copySubMatrix(0, 0, 3 * size, 1, 0, 6, (GMatrix)A);
/* 400 */     return A;
/*     */   }
/*     */   
/*     */   protected double[] getParameters() {
/* 411 */     return getDxMatrix().getElements()[0];
/*     */   }
/*     */   
/*     */   public GeneralMatrix getDxMatrix() {
/* 422 */     return getDxMatrix(1.0E-8D, 20);
/*     */   }
/*     */   
/*     */   private GeneralMatrix getDxMatrix(double tolerance, int maxSteps) {
/* 436 */     GeneralMatrix xNew = new GeneralMatrix(7, 1);
/* 439 */     GeneralMatrix xOld = new GeneralMatrix(7, 1);
/* 442 */     GeneralMatrix dxMatrix = new GeneralMatrix(7, 1);
/* 444 */     GeneralMatrix zero = new GeneralMatrix(7, 1);
/* 445 */     zero.setZero();
/* 448 */     int i = 0;
/*     */     do {
/* 452 */       xOld.set(new double[] { this.dx, this.dy, this.dz, this.alfa, this.beta, this.gamma, this.q });
/* 454 */       GeneralMatrix A = getA();
/* 455 */       GeneralMatrix l = getl();
/* 457 */       GeneralMatrix AT = A.clone();
/* 458 */       AT.transpose();
/* 460 */       GeneralMatrix ATA = new GeneralMatrix(7, 7);
/* 461 */       GeneralMatrix ATl = new GeneralMatrix(7, 1);
/* 464 */       ATA.mul((GMatrix)AT, (GMatrix)A);
/* 465 */       ATA.invert();
/* 466 */       ATl.mul((GMatrix)AT, (GMatrix)l);
/* 468 */       dxMatrix.mul((GMatrix)ATA, (GMatrix)ATl);
/* 471 */       xOld.negate();
/* 472 */       xNew.sub((GMatrix)dxMatrix, (GMatrix)xOld);
/* 475 */       this.dx = xNew.getElement(0, 0);
/* 476 */       this.dy = xNew.getElement(1, 0);
/* 477 */       this.dz = xNew.getElement(2, 0);
/* 478 */       this.alfa = xNew.getElement(3, 0);
/* 479 */       this.beta = xNew.getElement(4, 0);
/* 480 */       this.gamma = xNew.getElement(5, 0);
/* 481 */       this.q = xNew.getElement(6, 0);
/* 483 */       i++;
/* 484 */     } while (((!dxMatrix.equals((Matrix)zero, tolerance) ? 1 : 0) & ((i < maxSteps) ? 1 : 0)) != 0);
/* 486 */     xNew.transpose();
/* 488 */     return xNew;
/*     */   }
/*     */   
/*     */   private static double radiansToSeconds(double rad) {
/* 499 */     return rad * 57.29577951308232D * 3600.0D;
/*     */   }
/*     */   
/*     */   public BursaWolfParameters getBursaWolfParameters(GeodeticDatum Datum) {
/* 510 */     BursaWolfParameters parameters = new BursaWolfParameters(Datum);
/* 511 */     parameters.dx = this.dx;
/* 512 */     parameters.dy = this.dy;
/* 513 */     parameters.dz = this.dz;
/* 514 */     parameters.ex = -radiansToSeconds(this.alfa);
/* 515 */     parameters.ey = -radiansToSeconds(this.beta);
/* 516 */     parameters.ez = -radiansToSeconds(this.gamma);
/* 517 */     parameters.ppm = (this.q - 1.0D) * 1000000.0D;
/* 519 */     return parameters;
/*     */   }
/*     */   
/*     */   public void setTargetGeodeticDatum(GeodeticDatum gd) {
/* 523 */     this.targetDatum = gd;
/*     */   }
/*     */   
/*     */   protected MathTransform computeMathTransform() throws FactoryException {
/* 536 */     return (MathTransform)new GeocentricTranslation(getBursaWolfParameters(this.targetDatum));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\BursaWolfTransformBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */