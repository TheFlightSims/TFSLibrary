/*      */ package org.apache.commons.math3.geometry.euclidean.threed;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ 
/*      */ public class Rotation implements Serializable {
/*   98 */   public static final Rotation IDENTITY = new Rotation(1.0D, 0.0D, 0.0D, 0.0D, false);
/*      */   
/*      */   private static final long serialVersionUID = -2153622329907944313L;
/*      */   
/*      */   private final double q0;
/*      */   
/*      */   private final double q1;
/*      */   
/*      */   private final double q2;
/*      */   
/*      */   private final double q3;
/*      */   
/*      */   public Rotation(double q0, double q1, double q2, double q3, boolean needsNormalization) {
/*  136 */     if (needsNormalization) {
/*  138 */       double inv = 1.0D / FastMath.sqrt(q0 * q0 + q1 * q1 + q2 * q2 + q3 * q3);
/*  139 */       q0 *= inv;
/*  140 */       q1 *= inv;
/*  141 */       q2 *= inv;
/*  142 */       q3 *= inv;
/*      */     } 
/*  145 */     this.q0 = q0;
/*  146 */     this.q1 = q1;
/*  147 */     this.q2 = q2;
/*  148 */     this.q3 = q3;
/*      */   }
/*      */   
/*      */   public Rotation(Vector3D axis, double angle) {
/*  175 */     double norm = axis.getNorm();
/*  176 */     if (norm == 0.0D)
/*  177 */       throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_AXIS, new Object[0]); 
/*  180 */     double halfAngle = -0.5D * angle;
/*  181 */     double coeff = FastMath.sin(halfAngle) / norm;
/*  183 */     this.q0 = FastMath.cos(halfAngle);
/*  184 */     this.q1 = coeff * axis.getX();
/*  185 */     this.q2 = coeff * axis.getY();
/*  186 */     this.q3 = coeff * axis.getZ();
/*      */   }
/*      */   
/*      */   public Rotation(double[][] m, double threshold) throws NotARotationMatrixException {
/*  224 */     if (m.length != 3 || (m[0]).length != 3 || (m[1]).length != 3 || (m[2]).length != 3)
/*  226 */       throw new NotARotationMatrixException(LocalizedFormats.ROTATION_MATRIX_DIMENSIONS, new Object[] { Integer.valueOf(m.length), Integer.valueOf((m[0]).length) }); 
/*  232 */     double[][] ort = orthogonalizeMatrix(m, threshold);
/*  235 */     double det = ort[0][0] * (ort[1][1] * ort[2][2] - ort[2][1] * ort[1][2]) - ort[1][0] * (ort[0][1] * ort[2][2] - ort[2][1] * ort[0][2]) + ort[2][0] * (ort[0][1] * ort[1][2] - ort[1][1] * ort[0][2]);
/*  238 */     if (det < 0.0D)
/*  239 */       throw new NotARotationMatrixException(LocalizedFormats.CLOSEST_ORTHOGONAL_MATRIX_HAS_NEGATIVE_DETERMINANT, new Object[] { Double.valueOf(det) }); 
/*  255 */     double s = ort[0][0] + ort[1][1] + ort[2][2];
/*  256 */     if (s > -0.19D) {
/*  258 */       this.q0 = 0.5D * FastMath.sqrt(s + 1.0D);
/*  259 */       double inv = 0.25D / this.q0;
/*  260 */       this.q1 = inv * (ort[1][2] - ort[2][1]);
/*  261 */       this.q2 = inv * (ort[2][0] - ort[0][2]);
/*  262 */       this.q3 = inv * (ort[0][1] - ort[1][0]);
/*      */     } else {
/*  264 */       s = ort[0][0] - ort[1][1] - ort[2][2];
/*  265 */       if (s > -0.19D) {
/*  267 */         this.q1 = 0.5D * FastMath.sqrt(s + 1.0D);
/*  268 */         double inv = 0.25D / this.q1;
/*  269 */         this.q0 = inv * (ort[1][2] - ort[2][1]);
/*  270 */         this.q2 = inv * (ort[0][1] + ort[1][0]);
/*  271 */         this.q3 = inv * (ort[0][2] + ort[2][0]);
/*      */       } else {
/*  273 */         s = ort[1][1] - ort[0][0] - ort[2][2];
/*  274 */         if (s > -0.19D) {
/*  276 */           this.q2 = 0.5D * FastMath.sqrt(s + 1.0D);
/*  277 */           double inv = 0.25D / this.q2;
/*  278 */           this.q0 = inv * (ort[2][0] - ort[0][2]);
/*  279 */           this.q1 = inv * (ort[0][1] + ort[1][0]);
/*  280 */           this.q3 = inv * (ort[2][1] + ort[1][2]);
/*      */         } else {
/*  283 */           s = ort[2][2] - ort[0][0] - ort[1][1];
/*  284 */           this.q3 = 0.5D * FastMath.sqrt(s + 1.0D);
/*  285 */           double inv = 0.25D / this.q3;
/*  286 */           this.q0 = inv * (ort[0][1] - ort[1][0]);
/*  287 */           this.q1 = inv * (ort[0][2] + ort[2][0]);
/*  288 */           this.q2 = inv * (ort[2][1] + ort[1][2]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Rotation(Vector3D u1, Vector3D u2, Vector3D v1, Vector3D v2) {
/*  316 */     double u1u1 = u1.getNormSq();
/*  317 */     double u2u2 = u2.getNormSq();
/*  318 */     double v1v1 = v1.getNormSq();
/*  319 */     double v2v2 = v2.getNormSq();
/*  320 */     if (u1u1 == 0.0D || u2u2 == 0.0D || v1v1 == 0.0D || v2v2 == 0.0D)
/*  321 */       throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR, new Object[0]); 
/*  325 */     v1 = new Vector3D(FastMath.sqrt(u1u1 / v1v1), v1);
/*  328 */     double u1u2 = u1.dotProduct(u2);
/*  329 */     double v1v2 = v1.dotProduct(v2);
/*  330 */     double coeffU = u1u2 / u1u1;
/*  331 */     double coeffV = v1v2 / u1u1;
/*  332 */     double beta = FastMath.sqrt((u2u2 - u1u2 * coeffU) / (v2v2 - v1v2 * coeffV));
/*  333 */     double alpha = coeffU - beta * coeffV;
/*  334 */     v2 = new Vector3D(alpha, v1, beta, v2);
/*  337 */     Vector3D uRef = u1;
/*  338 */     Vector3D vRef = v1;
/*  339 */     Vector3D v1Su1 = v1.subtract(u1);
/*  340 */     Vector3D v2Su2 = v2.subtract(u2);
/*  341 */     Vector3D k = v1Su1.crossProduct(v2Su2);
/*  342 */     Vector3D u3 = u1.crossProduct(u2);
/*  343 */     double c = k.dotProduct(u3);
/*  344 */     double inPlaneThreshold = 0.001D;
/*  345 */     if (c <= 0.001D * k.getNorm() * u3.getNorm()) {
/*  348 */       Vector3D v3 = Vector3D.crossProduct(v1, v2);
/*  349 */       Vector3D v3Su3 = v3.subtract(u3);
/*  350 */       k = v1Su1.crossProduct(v3Su3);
/*  351 */       Vector3D u2Prime = u1.crossProduct(u3);
/*  352 */       c = k.dotProduct(u2Prime);
/*  354 */       if (c <= 0.001D * k.getNorm() * u2Prime.getNorm()) {
/*  357 */         k = v2Su2.crossProduct(v3Su3);
/*  358 */         c = k.dotProduct(u2.crossProduct(u3));
/*  360 */         if (c <= 0.0D) {
/*  363 */           this.q0 = 1.0D;
/*  364 */           this.q1 = 0.0D;
/*  365 */           this.q2 = 0.0D;
/*  366 */           this.q3 = 0.0D;
/*      */           return;
/*      */         } 
/*  371 */         uRef = u2;
/*  372 */         vRef = v2;
/*      */       } 
/*      */     } 
/*  379 */     c = FastMath.sqrt(c);
/*  380 */     double inv = 1.0D / (c + c);
/*  381 */     this.q1 = inv * k.getX();
/*  382 */     this.q2 = inv * k.getY();
/*  383 */     this.q3 = inv * k.getZ();
/*  386 */     k = new Vector3D(uRef.getY() * this.q3 - uRef.getZ() * this.q2, uRef.getZ() * this.q1 - uRef.getX() * this.q3, uRef.getX() * this.q2 - uRef.getY() * this.q1);
/*  389 */     this.q0 = vRef.dotProduct(k) / 2.0D * k.getNormSq();
/*      */   }
/*      */   
/*      */   public Rotation(Vector3D u, Vector3D v) {
/*  408 */     double normProduct = u.getNorm() * v.getNorm();
/*  409 */     if (normProduct == 0.0D)
/*  410 */       throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR, new Object[0]); 
/*  413 */     double dot = u.dotProduct(v);
/*  415 */     if (dot < -0.999999999999998D * normProduct) {
/*  418 */       Vector3D w = u.orthogonal();
/*  419 */       this.q0 = 0.0D;
/*  420 */       this.q1 = -w.getX();
/*  421 */       this.q2 = -w.getY();
/*  422 */       this.q3 = -w.getZ();
/*      */     } else {
/*  426 */       this.q0 = FastMath.sqrt(0.5D * (1.0D + dot / normProduct));
/*  427 */       double coeff = 1.0D / 2.0D * this.q0 * normProduct;
/*  428 */       Vector3D q = v.crossProduct(u);
/*  429 */       this.q1 = coeff * q.getX();
/*  430 */       this.q2 = coeff * q.getY();
/*  431 */       this.q3 = coeff * q.getZ();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Rotation(RotationOrder order, double alpha1, double alpha2, double alpha3) {
/*  457 */     Rotation r1 = new Rotation(order.getA1(), alpha1);
/*  458 */     Rotation r2 = new Rotation(order.getA2(), alpha2);
/*  459 */     Rotation r3 = new Rotation(order.getA3(), alpha3);
/*  460 */     Rotation composed = r1.applyTo(r2.applyTo(r3));
/*  461 */     this.q0 = composed.q0;
/*  462 */     this.q1 = composed.q1;
/*  463 */     this.q2 = composed.q2;
/*  464 */     this.q3 = composed.q3;
/*      */   }
/*      */   
/*      */   public Rotation revert() {
/*  475 */     return new Rotation(-this.q0, this.q1, this.q2, this.q3, false);
/*      */   }
/*      */   
/*      */   public double getQ0() {
/*  482 */     return this.q0;
/*      */   }
/*      */   
/*      */   public double getQ1() {
/*  489 */     return this.q1;
/*      */   }
/*      */   
/*      */   public double getQ2() {
/*  496 */     return this.q2;
/*      */   }
/*      */   
/*      */   public double getQ3() {
/*  503 */     return this.q3;
/*      */   }
/*      */   
/*      */   public Vector3D getAxis() {
/*  511 */     double squaredSine = this.q1 * this.q1 + this.q2 * this.q2 + this.q3 * this.q3;
/*  512 */     if (squaredSine == 0.0D)
/*  513 */       return new Vector3D(1.0D, 0.0D, 0.0D); 
/*  514 */     if (this.q0 < 0.0D) {
/*  515 */       double d = 1.0D / FastMath.sqrt(squaredSine);
/*  516 */       return new Vector3D(this.q1 * d, this.q2 * d, this.q3 * d);
/*      */     } 
/*  518 */     double inverse = -1.0D / FastMath.sqrt(squaredSine);
/*  519 */     return new Vector3D(this.q1 * inverse, this.q2 * inverse, this.q3 * inverse);
/*      */   }
/*      */   
/*      */   public double getAngle() {
/*  527 */     if (this.q0 < -0.1D || this.q0 > 0.1D)
/*  528 */       return 2.0D * FastMath.asin(FastMath.sqrt(this.q1 * this.q1 + this.q2 * this.q2 + this.q3 * this.q3)); 
/*  529 */     if (this.q0 < 0.0D)
/*  530 */       return 2.0D * FastMath.acos(-this.q0); 
/*  532 */     return 2.0D * FastMath.acos(this.q0);
/*      */   }
/*      */   
/*      */   public double[] getAngles(RotationOrder order) throws CardanEulerSingularityException {
/*  573 */     if (order == RotationOrder.XYZ) {
/*  580 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_K);
/*  581 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_I);
/*  582 */       if (vector3D2.getZ() < -0.9999999999D || vector3D2.getZ() > 0.9999999999D)
/*  583 */         throw new CardanEulerSingularityException(true); 
/*  585 */       return new double[] { FastMath.atan2(-vector3D1.getY(), vector3D1.getZ()), FastMath.asin(vector3D2.getZ()), FastMath.atan2(-vector3D2.getY(), vector3D2.getX()) };
/*      */     } 
/*  591 */     if (order == RotationOrder.XZY) {
/*  598 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_J);
/*  599 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_I);
/*  600 */       if (vector3D2.getY() < -0.9999999999D || vector3D2.getY() > 0.9999999999D)
/*  601 */         throw new CardanEulerSingularityException(true); 
/*  603 */       return new double[] { FastMath.atan2(vector3D1.getZ(), vector3D1.getY()), -FastMath.asin(vector3D2.getY()), FastMath.atan2(vector3D2.getZ(), vector3D2.getX()) };
/*      */     } 
/*  609 */     if (order == RotationOrder.YXZ) {
/*  616 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_K);
/*  617 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_J);
/*  618 */       if (vector3D2.getZ() < -0.9999999999D || vector3D2.getZ() > 0.9999999999D)
/*  619 */         throw new CardanEulerSingularityException(true); 
/*  621 */       return new double[] { FastMath.atan2(vector3D1.getX(), vector3D1.getZ()), -FastMath.asin(vector3D2.getZ()), FastMath.atan2(vector3D2.getX(), vector3D2.getY()) };
/*      */     } 
/*  627 */     if (order == RotationOrder.YZX) {
/*  634 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_I);
/*  635 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_J);
/*  636 */       if (vector3D2.getX() < -0.9999999999D || vector3D2.getX() > 0.9999999999D)
/*  637 */         throw new CardanEulerSingularityException(true); 
/*  639 */       return new double[] { FastMath.atan2(-vector3D1.getZ(), vector3D1.getX()), FastMath.asin(vector3D2.getX()), FastMath.atan2(-vector3D2.getZ(), vector3D2.getY()) };
/*      */     } 
/*  645 */     if (order == RotationOrder.ZXY) {
/*  652 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_J);
/*  653 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_K);
/*  654 */       if (vector3D2.getY() < -0.9999999999D || vector3D2.getY() > 0.9999999999D)
/*  655 */         throw new CardanEulerSingularityException(true); 
/*  657 */       return new double[] { FastMath.atan2(-vector3D1.getX(), vector3D1.getY()), FastMath.asin(vector3D2.getY()), FastMath.atan2(-vector3D2.getX(), vector3D2.getZ()) };
/*      */     } 
/*  663 */     if (order == RotationOrder.ZYX) {
/*  670 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_I);
/*  671 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_K);
/*  672 */       if (vector3D2.getX() < -0.9999999999D || vector3D2.getX() > 0.9999999999D)
/*  673 */         throw new CardanEulerSingularityException(true); 
/*  675 */       return new double[] { FastMath.atan2(vector3D1.getY(), vector3D1.getX()), -FastMath.asin(vector3D2.getX()), FastMath.atan2(vector3D2.getY(), vector3D2.getZ()) };
/*      */     } 
/*  681 */     if (order == RotationOrder.XYX) {
/*  688 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_I);
/*  689 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_I);
/*  690 */       if (vector3D2.getX() < -0.9999999999D || vector3D2.getX() > 0.9999999999D)
/*  691 */         throw new CardanEulerSingularityException(false); 
/*  693 */       return new double[] { FastMath.atan2(vector3D1.getY(), -vector3D1.getZ()), FastMath.acos(vector3D2.getX()), FastMath.atan2(vector3D2.getY(), vector3D2.getZ()) };
/*      */     } 
/*  699 */     if (order == RotationOrder.XZX) {
/*  706 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_I);
/*  707 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_I);
/*  708 */       if (vector3D2.getX() < -0.9999999999D || vector3D2.getX() > 0.9999999999D)
/*  709 */         throw new CardanEulerSingularityException(false); 
/*  711 */       return new double[] { FastMath.atan2(vector3D1.getZ(), vector3D1.getY()), FastMath.acos(vector3D2.getX()), FastMath.atan2(vector3D2.getZ(), -vector3D2.getY()) };
/*      */     } 
/*  717 */     if (order == RotationOrder.YXY) {
/*  724 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_J);
/*  725 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_J);
/*  726 */       if (vector3D2.getY() < -0.9999999999D || vector3D2.getY() > 0.9999999999D)
/*  727 */         throw new CardanEulerSingularityException(false); 
/*  729 */       return new double[] { FastMath.atan2(vector3D1.getX(), vector3D1.getZ()), FastMath.acos(vector3D2.getY()), FastMath.atan2(vector3D2.getX(), -vector3D2.getZ()) };
/*      */     } 
/*  735 */     if (order == RotationOrder.YZY) {
/*  742 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_J);
/*  743 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_J);
/*  744 */       if (vector3D2.getY() < -0.9999999999D || vector3D2.getY() > 0.9999999999D)
/*  745 */         throw new CardanEulerSingularityException(false); 
/*  747 */       return new double[] { FastMath.atan2(vector3D1.getZ(), -vector3D1.getX()), FastMath.acos(vector3D2.getY()), FastMath.atan2(vector3D2.getZ(), vector3D2.getX()) };
/*      */     } 
/*  753 */     if (order == RotationOrder.ZXZ) {
/*  760 */       Vector3D vector3D1 = applyTo(Vector3D.PLUS_K);
/*  761 */       Vector3D vector3D2 = applyInverseTo(Vector3D.PLUS_K);
/*  762 */       if (vector3D2.getZ() < -0.9999999999D || vector3D2.getZ() > 0.9999999999D)
/*  763 */         throw new CardanEulerSingularityException(false); 
/*  765 */       return new double[] { FastMath.atan2(vector3D1.getX(), -vector3D1.getY()), FastMath.acos(vector3D2.getZ()), FastMath.atan2(vector3D2.getX(), vector3D2.getY()) };
/*      */     } 
/*  778 */     Vector3D v1 = applyTo(Vector3D.PLUS_K);
/*  779 */     Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
/*  780 */     if (v2.getZ() < -0.9999999999D || v2.getZ() > 0.9999999999D)
/*  781 */       throw new CardanEulerSingularityException(false); 
/*  783 */     return new double[] { FastMath.atan2(v1.getY(), v1.getX()), FastMath.acos(v2.getZ()), FastMath.atan2(v2.getY(), -v2.getX()) };
/*      */   }
/*      */   
/*      */   public double[][] getMatrix() {
/*  799 */     double q0q0 = this.q0 * this.q0;
/*  800 */     double q0q1 = this.q0 * this.q1;
/*  801 */     double q0q2 = this.q0 * this.q2;
/*  802 */     double q0q3 = this.q0 * this.q3;
/*  803 */     double q1q1 = this.q1 * this.q1;
/*  804 */     double q1q2 = this.q1 * this.q2;
/*  805 */     double q1q3 = this.q1 * this.q3;
/*  806 */     double q2q2 = this.q2 * this.q2;
/*  807 */     double q2q3 = this.q2 * this.q3;
/*  808 */     double q3q3 = this.q3 * this.q3;
/*  811 */     double[][] m = new double[3][];
/*  812 */     m[0] = new double[3];
/*  813 */     m[1] = new double[3];
/*  814 */     m[2] = new double[3];
/*  816 */     m[0][0] = 2.0D * (q0q0 + q1q1) - 1.0D;
/*  817 */     m[1][0] = 2.0D * (q1q2 - q0q3);
/*  818 */     m[2][0] = 2.0D * (q1q3 + q0q2);
/*  820 */     m[0][1] = 2.0D * (q1q2 + q0q3);
/*  821 */     m[1][1] = 2.0D * (q0q0 + q2q2) - 1.0D;
/*  822 */     m[2][1] = 2.0D * (q2q3 - q0q1);
/*  824 */     m[0][2] = 2.0D * (q1q3 - q0q2);
/*  825 */     m[1][2] = 2.0D * (q2q3 + q0q1);
/*  826 */     m[2][2] = 2.0D * (q0q0 + q3q3) - 1.0D;
/*  828 */     return m;
/*      */   }
/*      */   
/*      */   public Vector3D applyTo(Vector3D u) {
/*  838 */     double x = u.getX();
/*  839 */     double y = u.getY();
/*  840 */     double z = u.getZ();
/*  842 */     double s = this.q1 * x + this.q2 * y + this.q3 * z;
/*  844 */     return new Vector3D(2.0D * (this.q0 * (x * this.q0 - this.q2 * z - this.q3 * y) + s * this.q1) - x, 2.0D * (this.q0 * (y * this.q0 - this.q3 * x - this.q1 * z) + s * this.q2) - y, 2.0D * (this.q0 * (z * this.q0 - this.q1 * y - this.q2 * x) + s * this.q3) - z);
/*      */   }
/*      */   
/*      */   public void applyTo(double[] in, double[] out) {
/*  857 */     double x = in[0];
/*  858 */     double y = in[1];
/*  859 */     double z = in[2];
/*  861 */     double s = this.q1 * x + this.q2 * y + this.q3 * z;
/*  863 */     out[0] = 2.0D * (this.q0 * (x * this.q0 - this.q2 * z - this.q3 * y) + s * this.q1) - x;
/*  864 */     out[1] = 2.0D * (this.q0 * (y * this.q0 - this.q3 * x - this.q1 * z) + s * this.q2) - y;
/*  865 */     out[2] = 2.0D * (this.q0 * (z * this.q0 - this.q1 * y - this.q2 * x) + s * this.q3) - z;
/*      */   }
/*      */   
/*      */   public Vector3D applyInverseTo(Vector3D u) {
/*  875 */     double x = u.getX();
/*  876 */     double y = u.getY();
/*  877 */     double z = u.getZ();
/*  879 */     double s = this.q1 * x + this.q2 * y + this.q3 * z;
/*  880 */     double m0 = -this.q0;
/*  882 */     return new Vector3D(2.0D * (m0 * (x * m0 - this.q2 * z - this.q3 * y) + s * this.q1) - x, 2.0D * (m0 * (y * m0 - this.q3 * x - this.q1 * z) + s * this.q2) - y, 2.0D * (m0 * (z * m0 - this.q1 * y - this.q2 * x) + s * this.q3) - z);
/*      */   }
/*      */   
/*      */   public void applyInverseTo(double[] in, double[] out) {
/*  895 */     double x = in[0];
/*  896 */     double y = in[1];
/*  897 */     double z = in[2];
/*  899 */     double s = this.q1 * x + this.q2 * y + this.q3 * z;
/*  900 */     double m0 = -this.q0;
/*  902 */     out[0] = 2.0D * (m0 * (x * m0 - this.q2 * z - this.q3 * y) + s * this.q1) - x;
/*  903 */     out[1] = 2.0D * (m0 * (y * m0 - this.q3 * x - this.q1 * z) + s * this.q2) - y;
/*  904 */     out[2] = 2.0D * (m0 * (z * m0 - this.q1 * y - this.q2 * x) + s * this.q3) - z;
/*      */   }
/*      */   
/*      */   public Rotation applyTo(Rotation r) {
/*  918 */     return new Rotation(r.q0 * this.q0 - r.q1 * this.q1 + r.q2 * this.q2 + r.q3 * this.q3, r.q1 * this.q0 + r.q0 * this.q1 + r.q2 * this.q3 - r.q3 * this.q2, r.q2 * this.q0 + r.q0 * this.q2 + r.q3 * this.q1 - r.q1 * this.q3, r.q3 * this.q0 + r.q0 * this.q3 + r.q1 * this.q2 - r.q2 * this.q1, false);
/*      */   }
/*      */   
/*      */   public Rotation applyInverseTo(Rotation r) {
/*  937 */     return new Rotation(-r.q0 * this.q0 - r.q1 * this.q1 + r.q2 * this.q2 + r.q3 * this.q3, -r.q1 * this.q0 + r.q0 * this.q1 + r.q2 * this.q3 - r.q3 * this.q2, -r.q2 * this.q0 + r.q0 * this.q2 + r.q3 * this.q1 - r.q1 * this.q3, -r.q3 * this.q0 + r.q0 * this.q3 + r.q1 * this.q2 - r.q2 * this.q1, false);
/*      */   }
/*      */   
/*      */   private double[][] orthogonalizeMatrix(double[][] m, double threshold) throws NotARotationMatrixException {
/*  956 */     double[] m0 = m[0];
/*  957 */     double[] m1 = m[1];
/*  958 */     double[] m2 = m[2];
/*  959 */     double x00 = m0[0];
/*  960 */     double x01 = m0[1];
/*  961 */     double x02 = m0[2];
/*  962 */     double x10 = m1[0];
/*  963 */     double x11 = m1[1];
/*  964 */     double x12 = m1[2];
/*  965 */     double x20 = m2[0];
/*  966 */     double x21 = m2[1];
/*  967 */     double x22 = m2[2];
/*  968 */     double fn = 0.0D;
/*  971 */     double[][] o = new double[3][3];
/*  972 */     double[] o0 = o[0];
/*  973 */     double[] o1 = o[1];
/*  974 */     double[] o2 = o[2];
/*  977 */     int i = 0;
/*  978 */     while (++i < 11) {
/*  981 */       double mx00 = m0[0] * x00 + m1[0] * x10 + m2[0] * x20;
/*  982 */       double mx10 = m0[1] * x00 + m1[1] * x10 + m2[1] * x20;
/*  983 */       double mx20 = m0[2] * x00 + m1[2] * x10 + m2[2] * x20;
/*  984 */       double mx01 = m0[0] * x01 + m1[0] * x11 + m2[0] * x21;
/*  985 */       double mx11 = m0[1] * x01 + m1[1] * x11 + m2[1] * x21;
/*  986 */       double mx21 = m0[2] * x01 + m1[2] * x11 + m2[2] * x21;
/*  987 */       double mx02 = m0[0] * x02 + m1[0] * x12 + m2[0] * x22;
/*  988 */       double mx12 = m0[1] * x02 + m1[1] * x12 + m2[1] * x22;
/*  989 */       double mx22 = m0[2] * x02 + m1[2] * x12 + m2[2] * x22;
/*  992 */       o0[0] = x00 - 0.5D * (x00 * mx00 + x01 * mx10 + x02 * mx20 - m0[0]);
/*  993 */       o0[1] = x01 - 0.5D * (x00 * mx01 + x01 * mx11 + x02 * mx21 - m0[1]);
/*  994 */       o0[2] = x02 - 0.5D * (x00 * mx02 + x01 * mx12 + x02 * mx22 - m0[2]);
/*  995 */       o1[0] = x10 - 0.5D * (x10 * mx00 + x11 * mx10 + x12 * mx20 - m1[0]);
/*  996 */       o1[1] = x11 - 0.5D * (x10 * mx01 + x11 * mx11 + x12 * mx21 - m1[1]);
/*  997 */       o1[2] = x12 - 0.5D * (x10 * mx02 + x11 * mx12 + x12 * mx22 - m1[2]);
/*  998 */       o2[0] = x20 - 0.5D * (x20 * mx00 + x21 * mx10 + x22 * mx20 - m2[0]);
/*  999 */       o2[1] = x21 - 0.5D * (x20 * mx01 + x21 * mx11 + x22 * mx21 - m2[1]);
/* 1000 */       o2[2] = x22 - 0.5D * (x20 * mx02 + x21 * mx12 + x22 * mx22 - m2[2]);
/* 1003 */       double corr00 = o0[0] - m0[0];
/* 1004 */       double corr01 = o0[1] - m0[1];
/* 1005 */       double corr02 = o0[2] - m0[2];
/* 1006 */       double corr10 = o1[0] - m1[0];
/* 1007 */       double corr11 = o1[1] - m1[1];
/* 1008 */       double corr12 = o1[2] - m1[2];
/* 1009 */       double corr20 = o2[0] - m2[0];
/* 1010 */       double corr21 = o2[1] - m2[1];
/* 1011 */       double corr22 = o2[2] - m2[2];
/* 1014 */       double fn1 = corr00 * corr00 + corr01 * corr01 + corr02 * corr02 + corr10 * corr10 + corr11 * corr11 + corr12 * corr12 + corr20 * corr20 + corr21 * corr21 + corr22 * corr22;
/* 1019 */       if (FastMath.abs(fn1 - fn) <= threshold)
/* 1020 */         return o; 
/* 1024 */       x00 = o0[0];
/* 1025 */       x01 = o0[1];
/* 1026 */       x02 = o0[2];
/* 1027 */       x10 = o1[0];
/* 1028 */       x11 = o1[1];
/* 1029 */       x12 = o1[2];
/* 1030 */       x20 = o2[0];
/* 1031 */       x21 = o2[1];
/* 1032 */       x22 = o2[2];
/* 1033 */       fn = fn1;
/*      */     } 
/* 1038 */     throw new NotARotationMatrixException(LocalizedFormats.UNABLE_TO_ORTHOGONOLIZE_MATRIX, new Object[] { Integer.valueOf(i - 1) });
/*      */   }
/*      */   
/*      */   public static double distance(Rotation r1, Rotation r2) {
/* 1068 */     return r1.applyInverseTo(r2).getAngle();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\Rotation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */