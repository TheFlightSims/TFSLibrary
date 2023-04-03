/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.vecmath.GMatrix;
/*     */ import javax.vecmath.MismatchedSizeException;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.geotools.referencing.operation.transform.AffineTransform2D;
/*     */ import org.geotools.referencing.operation.transform.ProjectiveTransform;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class AdvancedAffineBuilder extends MathTransformBuilder {
/*     */   public static final String SX = "sx";
/*     */   
/*     */   public static final String SY = "sy";
/*     */   
/*     */   public static final String SXY = "sxy";
/*     */   
/*     */   public static final String PHIX = "phix";
/*     */   
/*     */   public static final String PHIY = "phiy";
/*     */   
/*     */   public static final String TX = "tx";
/*     */   
/*     */   public static final String TY = "ty";
/*     */   
/*     */   private double tx;
/*     */   
/*     */   private double ty;
/*     */   
/*     */   private double sx;
/*     */   
/*     */   private double sy;
/*     */   
/*     */   private double phix;
/*     */   
/*     */   private double phiy;
/*     */   
/*  87 */   private double dif = 1.0E-7D;
/*     */   
/*  90 */   private int steps = 100;
/*     */   
/*  93 */   private Map<String, Double> valueConstrain = new HashMap<String, Double>();
/*     */   
/*  96 */   private Map<String, String> equalConstrain = new HashMap<String, String>();
/*     */   
/*     */   private final AffineTransform2D affineTrans;
/*     */   
/*     */   public AdvancedAffineBuilder(List<MappedPosition> vectors) throws MismatchedSizeException, MismatchedDimensionException, MismatchedReferenceSystemException, FactoryException {
/* 112 */     this(vectors, (AffineTransform2D)(new AffineTransformBuilder(vectors)).getMathTransform());
/*     */   }
/*     */   
/*     */   public AdvancedAffineBuilder(List<MappedPosition> vectors, AffineTransform2D affineTrans) throws MismatchedSizeException, MismatchedDimensionException, MismatchedReferenceSystemException, FactoryException {
/* 128 */     setMappedPositions(vectors);
/* 133 */     this.affineTrans = affineTrans;
/* 135 */     AffineToGeometric a2g = new AffineToGeometric(affineTrans);
/* 137 */     this.sx = a2g.getXScale();
/* 138 */     this.sy = a2g.getYScale();
/* 140 */     this.phix = a2g.getXRotation();
/* 141 */     this.phiy = a2g.getYRotation();
/* 142 */     this.tx = a2g.getXTranslate();
/* 143 */     this.ty = a2g.getYTranslate();
/*     */   }
/*     */   
/*     */   public void setConstrain(String param, double value) {
/* 155 */     this.valueConstrain.put(param, Double.valueOf(value));
/*     */   }
/*     */   
/*     */   public void clearConstrains() {
/* 162 */     this.valueConstrain.clear();
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getA() {
/* 172 */     GeneralMatrix A = new GeneralMatrix(2 * getMappedPositions().size(), 6);
/* 174 */     double cosphix = Math.cos(this.phix);
/* 175 */     double sinphix = Math.sin(this.phix);
/* 177 */     double cosphiy = Math.cos(this.phiy);
/* 178 */     double sinphiy = Math.sin(this.phiy);
/* 183 */     for (int j = 0; j < A.getNumRow() / 2; j++) {
/* 184 */       double x = getSourcePoints()[j].getOrdinate(0);
/* 185 */       double y = getSourcePoints()[j].getOrdinate(1);
/* 192 */       double dxsx = cosphix * x;
/* 194 */       double dxsy = -sinphiy * y;
/* 196 */       double dxphix = -this.sx * sinphix * x;
/* 198 */       double dxphiy = -this.sy * cosphiy * y;
/* 200 */       double dxtx = 1.0D;
/* 202 */       double dxty = 0.0D;
/* 209 */       double dysx = sinphix * x;
/* 211 */       double dysy = cosphiy * y;
/* 213 */       double dyphix = this.sx * cosphix * x;
/* 215 */       double dyphiy = -this.sy * sinphiy * y;
/* 217 */       double dytx = 0.0D;
/* 219 */       double dyty = 1.0D;
/* 221 */       A.setRow(j, new double[] { dxsx, dxsy, dxphix, dxphiy, dxtx, dxty });
/* 222 */       A.setRow(A.getNumRow() / 2 + j, new double[] { dysx, dysy, dyphix, dyphiy, dytx, dyty });
/*     */     } 
/* 225 */     return A;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getL() {
/* 234 */     GeneralMatrix l = new GeneralMatrix(2 * getMappedPositions().size(), 1);
/* 236 */     double cosphix = Math.cos(this.phix);
/* 237 */     double sinphix = Math.sin(this.phix);
/* 238 */     double cosphiy = Math.cos(this.phiy);
/* 239 */     double sinphiy = Math.sin(this.phiy);
/* 241 */     for (int j = 0; j < l.getNumRow() / 2; j++) {
/* 242 */       double x = getSourcePoints()[j].getOrdinate(0);
/* 243 */       double y = getSourcePoints()[j].getOrdinate(1);
/* 246 */       double dx = getTargetPoints()[j].getOrdinate(0) - this.sx * cosphix * x - this.sy * sinphiy * y + this.tx;
/* 248 */       double dy = getTargetPoints()[j].getOrdinate(1) - this.sx * sinphix * x + this.sy * cosphiy * y + this.ty;
/* 251 */       l.setElement(j, 0, dx);
/* 252 */       l.setElement(l.getNumRow() / 2 + j, 0, dy);
/*     */     } 
/* 255 */     return l;
/*     */   }
/*     */   
/*     */   private GeneralMatrix getDxMatrix() throws FactoryException {
/* 264 */     return getDxMatrix(this.dif, this.steps);
/*     */   }
/*     */   
/*     */   private GeneralMatrix getDxMatrix(double tolerance, int maxSteps) throws FactoryException {
/* 279 */     GeneralMatrix xNew = new GeneralMatrix(6, 1);
/* 284 */     GeneralMatrix xOld = new GeneralMatrix(6, 1);
/* 289 */     GeneralMatrix dxMatrix = new GeneralMatrix(6, 1);
/* 294 */     GeneralMatrix zero = new GeneralMatrix(6, 1);
/* 295 */     zero.setZero();
/* 300 */     GeneralMatrix xk = new GeneralMatrix(6 + this.valueConstrain.size(), 1);
/* 303 */     int i = 0;
/*     */     do {
/* 307 */       xOld.set(new double[] { this.sx, this.sy, this.phix, this.phiy, this.tx, this.ty });
/* 309 */       GeneralMatrix A = getA();
/* 310 */       GeneralMatrix l = getL();
/* 312 */       GeneralMatrix AT = A.clone();
/* 313 */       AT.transpose();
/* 315 */       GeneralMatrix ATA = new GeneralMatrix(6, 6);
/* 316 */       GeneralMatrix ATl = new GeneralMatrix(6, 1);
/* 318 */       ATA.mul((GMatrix)AT, (GMatrix)A);
/* 319 */       ATl.mul((GMatrix)AT, (GMatrix)l);
/* 322 */       GeneralMatrix AB = createAB(ATA, getB());
/* 324 */       AB.invert();
/* 325 */       AB.negate();
/* 327 */       GeneralMatrix AU = createAU(ATl, getU());
/* 328 */       xk.mul((GMatrix)AB, (GMatrix)AU);
/* 330 */       xk.copySubMatrix(0, 0, 6, xk.getNumCol(), 0, 0, (GMatrix)dxMatrix);
/* 331 */       dxMatrix.negate();
/* 334 */       xOld.negate();
/* 335 */       xNew.sub((GMatrix)dxMatrix, (GMatrix)xOld);
/* 338 */       this.sx = xNew.getElement(0, 0);
/* 339 */       this.sy = xNew.getElement(1, 0);
/* 340 */       this.phix = xNew.getElement(2, 0);
/* 341 */       this.phiy = xNew.getElement(3, 0);
/* 342 */       this.tx = xNew.getElement(4, 0);
/* 343 */       this.ty = xNew.getElement(5, 0);
/* 345 */       i++;
/* 347 */       if (i > maxSteps)
/* 348 */         throw new FactoryException("Calculation of transformation is divergating - try to set proper aproximate values"); 
/* 350 */     } while (!dxMatrix.equals((Matrix)zero, tolerance));
/* 352 */     xNew.transpose();
/* 354 */     return xNew;
/*     */   }
/*     */   
/*     */   public int getMinimumPointCount() {
/* 359 */     return 3;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getB() {
/* 367 */     GeneralMatrix B = new GeneralMatrix(this.valueConstrain.size(), 6);
/* 368 */     int i = 0;
/* 370 */     if (this.valueConstrain.containsKey("sx")) {
/* 371 */       B.setRow(i, new double[] { 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D });
/* 372 */       i++;
/*     */     } 
/* 375 */     if (this.valueConstrain.containsKey("sy")) {
/* 376 */       B.setRow(i, new double[] { 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D });
/* 377 */       i++;
/*     */     } 
/* 380 */     if (this.valueConstrain.containsKey("phix")) {
/* 381 */       B.setRow(i, new double[] { 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D });
/* 382 */       i++;
/*     */     } 
/* 385 */     if (this.valueConstrain.containsKey("phiy")) {
/* 386 */       B.setRow(i, new double[] { 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D });
/* 387 */       i++;
/*     */     } 
/* 390 */     if (this.valueConstrain.containsKey("tx")) {
/* 391 */       B.setRow(i, new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D });
/* 392 */       i++;
/*     */     } 
/* 395 */     if (this.valueConstrain.containsKey("ty")) {
/* 396 */       B.setRow(i, new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D });
/* 397 */       i++;
/*     */     } 
/* 399 */     if (this.valueConstrain.containsKey("sxy")) {
/* 400 */       B.setRow(i, new double[] { 0.0D, 0.0D, -1.0D, 1.0D, 0.0D, 0.0D });
/* 401 */       i++;
/*     */     } 
/* 404 */     return B;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getU() {
/* 412 */     GeneralMatrix U = new GeneralMatrix(this.valueConstrain.size(), 1);
/* 413 */     int i = 0;
/* 415 */     if (this.valueConstrain.containsKey("sx")) {
/* 416 */       U.setRow(i, new double[] { -this.sx + ((Double)this.valueConstrain.get("sx")).doubleValue() });
/* 417 */       i++;
/*     */     } 
/* 420 */     if (this.valueConstrain.containsKey("sy")) {
/* 421 */       U.setRow(i, new double[] { -this.sy + ((Double)this.valueConstrain.get("sy")).doubleValue() });
/* 422 */       i++;
/*     */     } 
/* 425 */     if (this.valueConstrain.containsKey("phix")) {
/* 426 */       U.setRow(i, new double[] { -this.phix + ((Double)this.valueConstrain.get("phix")).doubleValue() });
/* 427 */       i++;
/*     */     } 
/* 430 */     if (this.valueConstrain.containsKey("phiy")) {
/* 431 */       U.setRow(i, new double[] { -this.phiy + ((Double)this.valueConstrain.get("phiy")).doubleValue() });
/* 432 */       i++;
/*     */     } 
/* 435 */     if (this.valueConstrain.containsKey("tx")) {
/* 436 */       U.setRow(i, new double[] { -this.tx + ((Double)this.valueConstrain.get("tx")).doubleValue() });
/* 437 */       i++;
/*     */     } 
/* 439 */     if (this.valueConstrain.containsKey("sxy")) {
/* 440 */       U.setRow(i, new double[] { this.phix - this.phiy + ((Double)this.valueConstrain.get("sxy")).doubleValue() });
/* 441 */       i++;
/* 442 */     } else if (this.valueConstrain.containsKey("ty")) {
/* 443 */       U.setRow(i, new double[] { -this.ty + ((Double)this.valueConstrain.get("ty")).doubleValue() });
/* 444 */       i++;
/*     */     } 
/* 447 */     return U;
/*     */   }
/*     */   
/*     */   private GeneralMatrix createAU(GeneralMatrix ATl, GeneralMatrix U) {
/* 457 */     GeneralMatrix AU = new GeneralMatrix(ATl.getNumRow() + U.getNumRow(), ATl.getNumCol());
/* 459 */     ATl.copySubMatrix(0, 0, ATl.getNumRow(), ATl.getNumCol(), 0, 0, (GMatrix)AU);
/* 460 */     U.copySubMatrix(0, 0, U.getNumRow(), U.getNumCol(), ATl.getNumRow(), 0, (GMatrix)AU);
/* 462 */     return AU;
/*     */   }
/*     */   
/*     */   private GeneralMatrix createAB(GeneralMatrix ATA, GeneralMatrix B) {
/* 476 */     GeneralMatrix BT = B.clone();
/* 477 */     BT.transpose();
/* 479 */     GeneralMatrix AAB = new GeneralMatrix(ATA.getNumRow() + B.getNumRow(), ATA.getNumCol() + BT.getNumCol());
/* 482 */     ATA.copySubMatrix(0, 0, ATA.getNumRow(), ATA.getNumCol(), 0, 0, (GMatrix)AAB);
/* 483 */     B.copySubMatrix(0, 0, B.getNumRow(), B.getNumCol(), ATA.getNumRow(), 0, (GMatrix)AAB);
/* 484 */     BT.copySubMatrix(0, 0, BT.getNumRow(), BT.getNumCol(), 0, ATA.getNumCol(), (GMatrix)AAB);
/* 486 */     GeneralMatrix zero = new GeneralMatrix(B.getNumRow(), B.getNumRow());
/* 487 */     zero.setZero();
/* 488 */     zero.copySubMatrix(0, 0, zero.getNumRow(), zero.getNumCol(), B.getNumCol(), B.getNumCol(), (GMatrix)AAB);
/* 491 */     return AAB;
/*     */   }
/*     */   
/*     */   protected GeneralMatrix getProjectiveMatrix() throws FactoryException {
/* 501 */     GeneralMatrix M = new GeneralMatrix(3, 3);
/* 506 */     double[] param = getDxMatrix().getElements()[0];
/* 511 */     double a11 = this.sx * Math.cos(this.phix);
/* 512 */     double a12 = -this.sy * Math.sin(this.phiy);
/* 513 */     double a21 = this.sx * Math.sin(this.phix);
/* 514 */     double a22 = this.sy * Math.cos(this.phiy);
/* 519 */     double[] m0 = { a11, a12, param[4] };
/* 520 */     double[] m1 = { a21, a22, param[5] };
/* 521 */     double[] m2 = { 0.0D, 0.0D, 1.0D };
/* 522 */     M.setRow(0, m0);
/* 523 */     M.setRow(1, m1);
/* 524 */     M.setRow(2, m2);
/* 526 */     return M;
/*     */   }
/*     */   
/*     */   protected MathTransform computeMathTransform() throws FactoryException {
/* 531 */     if (this.valueConstrain.size() == 0)
/* 532 */       return (MathTransform)this.affineTrans; 
/* 535 */     return (MathTransform)ProjectiveTransform.create((Matrix)getProjectiveMatrix());
/*     */   }
/*     */   
/*     */   public double getMaxIterationDifference() {
/* 543 */     return this.dif;
/*     */   }
/*     */   
/*     */   public void setMaxIterationDifference(double dif) {
/* 551 */     this.dif = dif;
/*     */   }
/*     */   
/*     */   public int getNumberOfIterationSteps() {
/* 561 */     return this.steps;
/*     */   }
/*     */   
/*     */   public void setNumberOfIterationSteps(int steps) {
/* 570 */     this.steps = steps;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\AdvancedAffineBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */