/*      */ package javax.media.jai;
/*      */ 
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.NoninvertibleTransformException;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.io.Serializable;
/*      */ 
/*      */ public final class PerspectiveTransform implements Cloneable, Serializable {
/*      */   private static final double PERSPECTIVE_DIVIDE_EPSILON = 1.0E-10D;
/*      */   
/*      */   double m00;
/*      */   
/*      */   double m01;
/*      */   
/*      */   double m02;
/*      */   
/*      */   double m10;
/*      */   
/*      */   double m11;
/*      */   
/*      */   double m12;
/*      */   
/*      */   double m20;
/*      */   
/*      */   double m21;
/*      */   
/*      */   double m22;
/*      */   
/*      */   public PerspectiveTransform() {
/*   58 */     this.m00 = this.m11 = this.m22 = 1.0D;
/*   59 */     this.m01 = this.m02 = this.m10 = this.m12 = this.m20 = this.m21 = 0.0D;
/*      */   }
/*      */   
/*      */   public PerspectiveTransform(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
/*   69 */     this.m00 = m00;
/*   70 */     this.m01 = m01;
/*   71 */     this.m02 = m02;
/*   72 */     this.m10 = m10;
/*   73 */     this.m11 = m11;
/*   74 */     this.m12 = m12;
/*   75 */     this.m20 = m20;
/*   76 */     this.m21 = m21;
/*   77 */     this.m22 = m22;
/*      */   }
/*      */   
/*      */   public PerspectiveTransform(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
/*   87 */     this.m00 = m00;
/*   88 */     this.m01 = m01;
/*   89 */     this.m02 = m02;
/*   90 */     this.m10 = m10;
/*   91 */     this.m11 = m11;
/*   92 */     this.m12 = m12;
/*   93 */     this.m20 = m20;
/*   94 */     this.m21 = m21;
/*   95 */     this.m22 = m22;
/*      */   }
/*      */   
/*      */   public PerspectiveTransform(float[] flatmatrix) {
/*  108 */     if (flatmatrix == null)
/*  109 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  112 */     this.m00 = flatmatrix[0];
/*  113 */     this.m01 = flatmatrix[1];
/*  114 */     this.m02 = flatmatrix[2];
/*  115 */     this.m10 = flatmatrix[3];
/*  116 */     this.m11 = flatmatrix[4];
/*  117 */     this.m12 = flatmatrix[5];
/*  118 */     this.m20 = flatmatrix[6];
/*  119 */     this.m21 = flatmatrix[7];
/*  120 */     this.m22 = flatmatrix[8];
/*      */   }
/*      */   
/*      */   public PerspectiveTransform(float[][] matrix) {
/*  132 */     if (matrix == null)
/*  133 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  136 */     this.m00 = matrix[0][0];
/*  137 */     this.m01 = matrix[0][1];
/*  138 */     this.m02 = matrix[0][2];
/*  139 */     this.m10 = matrix[1][0];
/*  140 */     this.m11 = matrix[1][1];
/*  141 */     this.m12 = matrix[1][2];
/*  142 */     this.m20 = matrix[2][0];
/*  143 */     this.m21 = matrix[2][1];
/*  144 */     this.m22 = matrix[2][2];
/*      */   }
/*      */   
/*      */   public PerspectiveTransform(double[] flatmatrix) {
/*  158 */     if (flatmatrix == null)
/*  159 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  162 */     this.m00 = flatmatrix[0];
/*  163 */     this.m01 = flatmatrix[1];
/*  164 */     this.m02 = flatmatrix[2];
/*  165 */     this.m10 = flatmatrix[3];
/*  166 */     this.m11 = flatmatrix[4];
/*  167 */     this.m12 = flatmatrix[5];
/*  168 */     this.m20 = flatmatrix[6];
/*  169 */     this.m21 = flatmatrix[7];
/*  170 */     this.m22 = flatmatrix[8];
/*      */   }
/*      */   
/*      */   public PerspectiveTransform(double[][] matrix) {
/*  180 */     if (matrix == null)
/*  181 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  184 */     this.m00 = matrix[0][0];
/*  185 */     this.m01 = matrix[0][1];
/*  186 */     this.m02 = matrix[0][2];
/*  187 */     this.m10 = matrix[1][0];
/*  188 */     this.m11 = matrix[1][1];
/*  189 */     this.m12 = matrix[1][2];
/*  190 */     this.m20 = matrix[2][0];
/*  191 */     this.m21 = matrix[2][1];
/*  192 */     this.m22 = matrix[2][2];
/*      */   }
/*      */   
/*      */   public PerspectiveTransform(AffineTransform transform) {
/*  201 */     if (transform == null)
/*  202 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  205 */     this.m00 = transform.getScaleX();
/*  206 */     this.m01 = transform.getShearX();
/*  207 */     this.m02 = transform.getTranslateX();
/*  208 */     this.m10 = transform.getShearY();
/*  209 */     this.m11 = transform.getScaleY();
/*  210 */     this.m12 = transform.getTranslateY();
/*  211 */     this.m20 = 0.0D;
/*  212 */     this.m21 = 0.0D;
/*  213 */     this.m22 = 1.0D;
/*      */   }
/*      */   
/*      */   private final void makeAdjoint() {
/*  220 */     double m00p = this.m11 * this.m22 - this.m12 * this.m21;
/*  221 */     double m01p = this.m12 * this.m20 - this.m10 * this.m22;
/*  222 */     double m02p = this.m10 * this.m21 - this.m11 * this.m20;
/*  223 */     double m10p = this.m02 * this.m21 - this.m01 * this.m22;
/*  224 */     double m11p = this.m00 * this.m22 - this.m02 * this.m20;
/*  225 */     double m12p = this.m01 * this.m20 - this.m00 * this.m21;
/*  226 */     double m20p = this.m01 * this.m12 - this.m02 * this.m11;
/*  227 */     double m21p = this.m02 * this.m10 - this.m00 * this.m12;
/*  228 */     double m22p = this.m00 * this.m11 - this.m01 * this.m10;
/*  231 */     this.m00 = m00p;
/*  232 */     this.m01 = m10p;
/*  233 */     this.m02 = m20p;
/*  234 */     this.m10 = m01p;
/*  235 */     this.m11 = m11p;
/*  236 */     this.m12 = m21p;
/*  237 */     this.m20 = m02p;
/*  238 */     this.m21 = m12p;
/*  239 */     this.m22 = m22p;
/*      */   }
/*      */   
/*      */   private final void normalize() {
/*  247 */     double invscale = 1.0D / this.m22;
/*  248 */     this.m00 *= invscale;
/*  249 */     this.m01 *= invscale;
/*  250 */     this.m02 *= invscale;
/*  251 */     this.m10 *= invscale;
/*  252 */     this.m11 *= invscale;
/*  253 */     this.m12 *= invscale;
/*  254 */     this.m20 *= invscale;
/*  255 */     this.m21 *= invscale;
/*  256 */     this.m22 = 1.0D;
/*      */   }
/*      */   
/*      */   private static final void getSquareToQuad(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3, PerspectiveTransform tx) {
/*  264 */     double dx3 = x0 - x1 + x2 - x3;
/*  265 */     double dy3 = y0 - y1 + y2 - y3;
/*  267 */     tx.m22 = 1.0D;
/*  269 */     if (dx3 == 0.0D && dy3 == 0.0D) {
/*  270 */       tx.m00 = x1 - x0;
/*  271 */       tx.m01 = x2 - x1;
/*  272 */       tx.m02 = x0;
/*  273 */       tx.m10 = y1 - y0;
/*  274 */       tx.m11 = y2 - y1;
/*  275 */       tx.m12 = y0;
/*  276 */       tx.m20 = 0.0D;
/*  277 */       tx.m21 = 0.0D;
/*      */     } else {
/*  279 */       double dx1 = x1 - x2;
/*  280 */       double dy1 = y1 - y2;
/*  281 */       double dx2 = x3 - x2;
/*  282 */       double dy2 = y3 - y2;
/*  284 */       double invdet = 1.0D / (dx1 * dy2 - dx2 * dy1);
/*  285 */       tx.m20 = (dx3 * dy2 - dx2 * dy3) * invdet;
/*  286 */       tx.m21 = (dx1 * dy3 - dx3 * dy1) * invdet;
/*  287 */       tx.m00 = x1 - x0 + tx.m20 * x1;
/*  288 */       tx.m01 = x3 - x0 + tx.m21 * x3;
/*  289 */       tx.m02 = x0;
/*  290 */       tx.m10 = y1 - y0 + tx.m20 * y1;
/*  291 */       tx.m11 = y3 - y0 + tx.m21 * y3;
/*  292 */       tx.m12 = y0;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static PerspectiveTransform getSquareToQuad(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3) {
/*  311 */     PerspectiveTransform tx = new PerspectiveTransform();
/*  312 */     getSquareToQuad(x0, y0, x1, y1, x2, y2, x3, y3, tx);
/*  313 */     return tx;
/*      */   }
/*      */   
/*      */   public static PerspectiveTransform getSquareToQuad(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
/*  332 */     return getSquareToQuad(x0, y0, x1, y1, x2, y2, x3, y3);
/*      */   }
/*      */   
/*      */   public static PerspectiveTransform getQuadToSquare(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3) {
/*  354 */     PerspectiveTransform tx = new PerspectiveTransform();
/*  355 */     getSquareToQuad(x0, y0, x1, y1, x2, y2, x3, y3, tx);
/*  356 */     tx.makeAdjoint();
/*  357 */     return tx;
/*      */   }
/*      */   
/*      */   public static PerspectiveTransform getQuadToSquare(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
/*  375 */     return getQuadToSquare(x0, y0, x1, y1, x2, y2, x3, y3);
/*      */   }
/*      */   
/*      */   public static PerspectiveTransform getQuadToQuad(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3, double x0p, double y0p, double x1p, double y1p, double x2p, double y2p, double x3p, double y3p) {
/*  400 */     PerspectiveTransform tx1 = getQuadToSquare(x0, y0, x1, y1, x2, y2, x3, y3);
/*  403 */     PerspectiveTransform tx2 = getSquareToQuad(x0p, y0p, x1p, y1p, x2p, y2p, x3p, y3p);
/*  406 */     tx1.concatenate(tx2);
/*  407 */     return tx1;
/*      */   }
/*      */   
/*      */   public static PerspectiveTransform getQuadToQuad(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3, float x0p, float y0p, float x1p, float y1p, float x2p, float y2p, float x3p, float y3p) {
/*  430 */     return getQuadToQuad(x0, y0, x1, y1, x2, y2, x3, y3, x0p, y0p, x1p, y1p, x2p, y2p, x3p, y3p);
/*      */   }
/*      */   
/*      */   public double getDeterminant() {
/*  445 */     return this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) - this.m01 * (this.m10 * this.m22 - this.m12 * this.m20) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20);
/*      */   }
/*      */   
/*      */   public double[] getMatrix(double[] flatmatrix) {
/*  464 */     if (flatmatrix == null)
/*  465 */       flatmatrix = new double[9]; 
/*  468 */     flatmatrix[0] = this.m00;
/*  469 */     flatmatrix[1] = this.m01;
/*  470 */     flatmatrix[2] = this.m02;
/*  471 */     flatmatrix[3] = this.m10;
/*  472 */     flatmatrix[4] = this.m11;
/*  473 */     flatmatrix[5] = this.m12;
/*  474 */     flatmatrix[6] = this.m20;
/*  475 */     flatmatrix[7] = this.m21;
/*  476 */     flatmatrix[8] = this.m22;
/*  478 */     return flatmatrix;
/*      */   }
/*      */   
/*      */   public double[][] getMatrix(double[][] matrix) {
/*  493 */     if (matrix == null)
/*  494 */       matrix = new double[3][3]; 
/*  497 */     matrix[0][0] = this.m00;
/*  498 */     matrix[0][1] = this.m01;
/*  499 */     matrix[0][2] = this.m02;
/*  500 */     matrix[1][0] = this.m10;
/*  501 */     matrix[1][1] = this.m11;
/*  502 */     matrix[1][2] = this.m12;
/*  503 */     matrix[2][0] = this.m20;
/*  504 */     matrix[2][1] = this.m21;
/*  505 */     matrix[2][2] = this.m22;
/*  507 */     return matrix;
/*      */   }
/*      */   
/*      */   public void translate(double tx, double ty) {
/*  521 */     PerspectiveTransform Tx = new PerspectiveTransform();
/*  522 */     Tx.setToTranslation(tx, ty);
/*  523 */     concatenate(Tx);
/*      */   }
/*      */   
/*      */   public void rotate(double theta) {
/*  541 */     PerspectiveTransform Tx = new PerspectiveTransform();
/*  542 */     Tx.setToRotation(theta);
/*  543 */     concatenate(Tx);
/*      */   }
/*      */   
/*      */   public void rotate(double theta, double x, double y) {
/*  562 */     PerspectiveTransform Tx = new PerspectiveTransform();
/*  563 */     Tx.setToRotation(theta, x, y);
/*  564 */     concatenate(Tx);
/*      */   }
/*      */   
/*      */   public void scale(double sx, double sy) {
/*  581 */     PerspectiveTransform Tx = new PerspectiveTransform();
/*  582 */     Tx.setToScale(sx, sy);
/*  583 */     concatenate(Tx);
/*      */   }
/*      */   
/*      */   public void shear(double shx, double shy) {
/*  604 */     PerspectiveTransform Tx = new PerspectiveTransform();
/*  605 */     Tx.setToShear(shx, shy);
/*  606 */     concatenate(Tx);
/*      */   }
/*      */   
/*      */   public void setToIdentity() {
/*  613 */     this.m00 = this.m11 = this.m22 = 1.0D;
/*  614 */     this.m01 = this.m10 = this.m02 = this.m20 = this.m12 = this.m21 = 0.0D;
/*      */   }
/*      */   
/*      */   public void setToTranslation(double tx, double ty) {
/*  631 */     this.m00 = 1.0D;
/*  632 */     this.m01 = 0.0D;
/*  633 */     this.m02 = tx;
/*  634 */     this.m10 = 0.0D;
/*  635 */     this.m11 = 1.0D;
/*  636 */     this.m12 = ty;
/*  637 */     this.m20 = 0.0D;
/*  638 */     this.m21 = 0.0D;
/*  639 */     this.m22 = 1.0D;
/*      */   }
/*      */   
/*      */   public void setToRotation(double theta) {
/*  655 */     this.m00 = Math.cos(theta);
/*  656 */     this.m01 = -Math.sin(theta);
/*  657 */     this.m02 = 0.0D;
/*  658 */     this.m10 = -this.m01;
/*  659 */     this.m11 = this.m00;
/*  660 */     this.m12 = 0.0D;
/*  661 */     this.m20 = 0.0D;
/*  662 */     this.m21 = 0.0D;
/*  663 */     this.m22 = 1.0D;
/*      */   }
/*      */   
/*      */   public void setToRotation(double theta, double x, double y) {
/*  685 */     setToRotation(theta);
/*  686 */     double sin = this.m10;
/*  687 */     double oneMinusCos = 1.0D - this.m00;
/*  688 */     this.m02 = x * oneMinusCos + y * sin;
/*  689 */     this.m12 = y * oneMinusCos - x * sin;
/*      */   }
/*      */   
/*      */   public void setToScale(double sx, double sy) {
/*  706 */     this.m00 = sx;
/*  707 */     this.m01 = 0.0D;
/*  708 */     this.m02 = 0.0D;
/*  709 */     this.m10 = 0.0D;
/*  710 */     this.m11 = sy;
/*  711 */     this.m12 = 0.0D;
/*  712 */     this.m20 = 0.0D;
/*  713 */     this.m21 = 0.0D;
/*  714 */     this.m22 = 1.0D;
/*      */   }
/*      */   
/*      */   public void setToShear(double shx, double shy) {
/*  735 */     this.m00 = 1.0D;
/*  736 */     this.m01 = shx;
/*  737 */     this.m02 = 0.0D;
/*  738 */     this.m10 = shy;
/*  739 */     this.m11 = 1.0D;
/*  740 */     this.m12 = 0.0D;
/*  741 */     this.m20 = 0.0D;
/*  742 */     this.m21 = 0.0D;
/*  743 */     this.m22 = 1.0D;
/*      */   }
/*      */   
/*      */   public void setTransform(AffineTransform Tx) {
/*  751 */     if (Tx == null)
/*  752 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  755 */     this.m00 = Tx.getScaleX();
/*  756 */     this.m01 = Tx.getShearX();
/*  757 */     this.m02 = Tx.getTranslateX();
/*  758 */     this.m10 = Tx.getShearY();
/*  759 */     this.m11 = Tx.getScaleY();
/*  760 */     this.m12 = Tx.getTranslateY();
/*  761 */     this.m20 = 0.0D;
/*  762 */     this.m21 = 0.0D;
/*  763 */     this.m22 = 1.0D;
/*      */   }
/*      */   
/*      */   public void setTransform(PerspectiveTransform Tx) {
/*  771 */     if (Tx == null)
/*  772 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  775 */     this.m00 = Tx.m00;
/*  776 */     this.m01 = Tx.m01;
/*  777 */     this.m02 = Tx.m02;
/*  778 */     this.m10 = Tx.m10;
/*  779 */     this.m11 = Tx.m11;
/*  780 */     this.m12 = Tx.m12;
/*  781 */     this.m20 = Tx.m20;
/*  782 */     this.m21 = Tx.m21;
/*  783 */     this.m22 = Tx.m22;
/*      */   }
/*      */   
/*      */   public void setTransform(float m00, float m10, float m20, float m01, float m11, float m21, float m02, float m12, float m22) {
/*  796 */     this.m00 = m00;
/*  797 */     this.m01 = m01;
/*  798 */     this.m02 = m02;
/*  799 */     this.m10 = m10;
/*  800 */     this.m11 = m11;
/*  801 */     this.m12 = m12;
/*  802 */     this.m20 = m20;
/*  803 */     this.m21 = m21;
/*  804 */     this.m22 = m22;
/*      */   }
/*      */   
/*      */   public void setTransform(double[][] matrix) {
/*  818 */     if (matrix == null)
/*  819 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  822 */     this.m00 = matrix[0][0];
/*  823 */     this.m01 = matrix[0][1];
/*  824 */     this.m02 = matrix[0][2];
/*  825 */     this.m10 = matrix[1][0];
/*  826 */     this.m11 = matrix[1][1];
/*  827 */     this.m12 = matrix[1][2];
/*  828 */     this.m20 = matrix[2][0];
/*  829 */     this.m21 = matrix[2][1];
/*  830 */     this.m22 = matrix[2][2];
/*      */   }
/*      */   
/*      */   public void concatenate(AffineTransform Tx) {
/*  838 */     if (Tx == null)
/*  839 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  844 */     double tx_m00 = Tx.getScaleX();
/*  845 */     double tx_m01 = Tx.getShearX();
/*  846 */     double tx_m02 = Tx.getTranslateX();
/*  847 */     double tx_m10 = Tx.getShearY();
/*  848 */     double tx_m11 = Tx.getScaleY();
/*  849 */     double tx_m12 = Tx.getTranslateY();
/*  851 */     double m00p = this.m00 * tx_m00 + this.m10 * tx_m01 + this.m20 * tx_m02;
/*  852 */     double m01p = this.m01 * tx_m00 + this.m11 * tx_m01 + this.m21 * tx_m02;
/*  853 */     double m02p = this.m02 * tx_m00 + this.m12 * tx_m01 + this.m22 * tx_m02;
/*  854 */     double m10p = this.m00 * tx_m10 + this.m10 * tx_m11 + this.m20 * tx_m12;
/*  855 */     double m11p = this.m01 * tx_m10 + this.m11 * tx_m11 + this.m21 * tx_m12;
/*  856 */     double m12p = this.m02 * tx_m10 + this.m12 * tx_m11 + this.m22 * tx_m12;
/*  857 */     double m20p = this.m20;
/*  858 */     double m21p = this.m21;
/*  859 */     double m22p = this.m22;
/*  861 */     this.m00 = m00p;
/*  862 */     this.m10 = m10p;
/*  863 */     this.m20 = m20p;
/*  864 */     this.m01 = m01p;
/*  865 */     this.m11 = m11p;
/*  866 */     this.m21 = m21p;
/*  867 */     this.m02 = m02p;
/*  868 */     this.m12 = m12p;
/*  869 */     this.m22 = m22p;
/*      */   }
/*      */   
/*      */   public void concatenate(PerspectiveTransform Tx) {
/*  877 */     if (Tx == null)
/*  878 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  881 */     double m00p = this.m00 * Tx.m00 + this.m10 * Tx.m01 + this.m20 * Tx.m02;
/*  882 */     double m10p = this.m00 * Tx.m10 + this.m10 * Tx.m11 + this.m20 * Tx.m12;
/*  883 */     double m20p = this.m00 * Tx.m20 + this.m10 * Tx.m21 + this.m20 * Tx.m22;
/*  884 */     double m01p = this.m01 * Tx.m00 + this.m11 * Tx.m01 + this.m21 * Tx.m02;
/*  885 */     double m11p = this.m01 * Tx.m10 + this.m11 * Tx.m11 + this.m21 * Tx.m12;
/*  886 */     double m21p = this.m01 * Tx.m20 + this.m11 * Tx.m21 + this.m21 * Tx.m22;
/*  887 */     double m02p = this.m02 * Tx.m00 + this.m12 * Tx.m01 + this.m22 * Tx.m02;
/*  888 */     double m12p = this.m02 * Tx.m10 + this.m12 * Tx.m11 + this.m22 * Tx.m12;
/*  889 */     double m22p = this.m02 * Tx.m20 + this.m12 * Tx.m21 + this.m22 * Tx.m22;
/*  891 */     this.m00 = m00p;
/*  892 */     this.m10 = m10p;
/*  893 */     this.m20 = m20p;
/*  894 */     this.m01 = m01p;
/*  895 */     this.m11 = m11p;
/*  896 */     this.m21 = m21p;
/*  897 */     this.m02 = m02p;
/*  898 */     this.m12 = m12p;
/*  899 */     this.m22 = m22p;
/*      */   }
/*      */   
/*      */   public void preConcatenate(AffineTransform Tx) {
/*  907 */     if (Tx == null)
/*  908 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  913 */     double tx_m00 = Tx.getScaleX();
/*  914 */     double tx_m01 = Tx.getShearX();
/*  915 */     double tx_m02 = Tx.getTranslateX();
/*  916 */     double tx_m10 = Tx.getShearY();
/*  917 */     double tx_m11 = Tx.getScaleY();
/*  918 */     double tx_m12 = Tx.getTranslateY();
/*  920 */     double m00p = tx_m00 * this.m00 + tx_m10 * this.m01;
/*  921 */     double m01p = tx_m01 * this.m00 + tx_m11 * this.m01;
/*  922 */     double m02p = tx_m02 * this.m00 + tx_m12 * this.m01 + this.m02;
/*  923 */     double m10p = tx_m00 * this.m10 + tx_m10 * this.m11;
/*  924 */     double m11p = tx_m01 * this.m10 + tx_m11 * this.m11;
/*  925 */     double m12p = tx_m02 * this.m10 + tx_m12 * this.m11 + this.m12;
/*  926 */     double m20p = tx_m00 * this.m20 + tx_m10 * this.m21;
/*  927 */     double m21p = tx_m01 * this.m20 + tx_m11 * this.m21;
/*  928 */     double m22p = tx_m02 * this.m20 + tx_m12 * this.m21 + this.m22;
/*  930 */     this.m00 = m00p;
/*  931 */     this.m10 = m10p;
/*  932 */     this.m20 = m20p;
/*  933 */     this.m01 = m01p;
/*  934 */     this.m11 = m11p;
/*  935 */     this.m21 = m21p;
/*  936 */     this.m02 = m02p;
/*  937 */     this.m12 = m12p;
/*  938 */     this.m22 = m22p;
/*      */   }
/*      */   
/*      */   public void preConcatenate(PerspectiveTransform Tx) {
/*  946 */     if (Tx == null)
/*  947 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  950 */     double m00p = Tx.m00 * this.m00 + Tx.m10 * this.m01 + Tx.m20 * this.m02;
/*  951 */     double m10p = Tx.m00 * this.m10 + Tx.m10 * this.m11 + Tx.m20 * this.m12;
/*  952 */     double m20p = Tx.m00 * this.m20 + Tx.m10 * this.m21 + Tx.m20 * this.m22;
/*  953 */     double m01p = Tx.m01 * this.m00 + Tx.m11 * this.m01 + Tx.m21 * this.m02;
/*  954 */     double m11p = Tx.m01 * this.m10 + Tx.m11 * this.m11 + Tx.m21 * this.m12;
/*  955 */     double m21p = Tx.m01 * this.m20 + Tx.m11 * this.m21 + Tx.m21 * this.m22;
/*  956 */     double m02p = Tx.m02 * this.m00 + Tx.m12 * this.m01 + Tx.m22 * this.m02;
/*  957 */     double m12p = Tx.m02 * this.m10 + Tx.m12 * this.m11 + Tx.m22 * this.m12;
/*  958 */     double m22p = Tx.m02 * this.m20 + Tx.m12 * this.m21 + Tx.m22 * this.m22;
/*  960 */     this.m00 = m00p;
/*  961 */     this.m10 = m10p;
/*  962 */     this.m20 = m20p;
/*  963 */     this.m01 = m01p;
/*  964 */     this.m11 = m11p;
/*  965 */     this.m21 = m21p;
/*  966 */     this.m02 = m02p;
/*  967 */     this.m12 = m12p;
/*  968 */     this.m22 = m22p;
/*      */   }
/*      */   
/*      */   public PerspectiveTransform createInverse() throws NoninvertibleTransformException, CloneNotSupportedException {
/*  979 */     PerspectiveTransform tx = (PerspectiveTransform)clone();
/*  980 */     tx.makeAdjoint();
/*  981 */     if (Math.abs(tx.m22) < 1.0E-10D)
/*  982 */       throw new NoninvertibleTransformException(JaiI18N.getString("PerspectiveTransform0")); 
/*  984 */     tx.normalize();
/*  985 */     return tx;
/*      */   }
/*      */   
/*      */   public PerspectiveTransform createAdjoint() throws CloneNotSupportedException {
/* 1005 */     PerspectiveTransform tx = (PerspectiveTransform)clone();
/* 1006 */     tx.makeAdjoint();
/* 1007 */     return tx;
/*      */   }
/*      */   
/*      */   public Point2D transform(Point2D ptSrc, Point2D ptDst) {
/* 1023 */     if (ptSrc == null)
/* 1024 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1027 */     if (ptDst == null)
/* 1028 */       if (ptSrc instanceof Point2D.Double) {
/* 1029 */         ptDst = new Point2D.Double();
/*      */       } else {
/* 1031 */         ptDst = new Point2D.Float();
/*      */       }  
/* 1035 */     double x = ptSrc.getX();
/* 1036 */     double y = ptSrc.getY();
/* 1037 */     double w = this.m20 * x + this.m21 * y + this.m22;
/* 1038 */     ptDst.setLocation((this.m00 * x + this.m01 * y + this.m02) / w, (this.m10 * x + this.m11 * y + this.m12) / w);
/* 1041 */     return ptDst;
/*      */   }
/*      */   
/*      */   public void transform(Point2D[] ptSrc, int srcOff, Point2D[] ptDst, int dstOff, int numPts) {
/* 1061 */     if (ptSrc == null || ptDst == null)
/* 1062 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1065 */     while (numPts-- > 0) {
/* 1067 */       Point2D src = ptSrc[srcOff++];
/* 1068 */       Point2D dst = ptDst[dstOff++];
/* 1069 */       if (dst == null) {
/* 1070 */         if (src instanceof Point2D.Double) {
/* 1071 */           dst = new Point2D.Double();
/*      */         } else {
/* 1073 */           dst = new Point2D.Float();
/*      */         } 
/* 1075 */         ptDst[dstOff - 1] = dst;
/*      */       } 
/* 1078 */       double x = src.getX();
/* 1079 */       double y = src.getY();
/* 1080 */       double w = this.m20 * x + this.m21 * y + this.m22;
/* 1082 */       if (w == 0.0D) {
/* 1083 */         dst.setLocation(x, y);
/*      */         continue;
/*      */       } 
/* 1085 */       dst.setLocation((this.m00 * x + this.m01 * y + this.m02) / w, (this.m10 * x + this.m11 * y + this.m12) / w);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 1109 */     if (srcPts == null)
/* 1110 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1113 */     if (dstPts == null)
/* 1114 */       dstPts = new float[numPts * 2 + dstOff]; 
/* 1117 */     while (numPts-- > 0) {
/* 1118 */       float x = srcPts[srcOff++];
/* 1119 */       float y = srcPts[srcOff++];
/* 1120 */       double w = this.m20 * x + this.m21 * y + this.m22;
/* 1122 */       if (w == 0.0D) {
/* 1123 */         dstPts[dstOff++] = x;
/* 1124 */         dstPts[dstOff++] = y;
/*      */         continue;
/*      */       } 
/* 1126 */       dstPts[dstOff++] = (float)((this.m00 * x + this.m01 * y + this.m02) / w);
/* 1127 */       dstPts[dstOff++] = (float)((this.m10 * x + this.m11 * y + this.m12) / w);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 1150 */     if (srcPts == null)
/* 1151 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1154 */     if (dstPts == null)
/* 1155 */       dstPts = new double[numPts * 2 + dstOff]; 
/* 1158 */     while (numPts-- > 0) {
/* 1159 */       double x = srcPts[srcOff++];
/* 1160 */       double y = srcPts[srcOff++];
/* 1161 */       double w = this.m20 * x + this.m21 * y + this.m22;
/* 1163 */       if (w == 0.0D) {
/* 1164 */         dstPts[dstOff++] = x;
/* 1165 */         dstPts[dstOff++] = y;
/*      */         continue;
/*      */       } 
/* 1167 */       dstPts[dstOff++] = (this.m00 * x + this.m01 * y + this.m02) / w;
/* 1168 */       dstPts[dstOff++] = (this.m10 * x + this.m11 * y + this.m12) / w;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void transform(float[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
/* 1192 */     if (srcPts == null)
/* 1193 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1196 */     if (dstPts == null)
/* 1197 */       dstPts = new double[numPts * 2 + dstOff]; 
/* 1200 */     while (numPts-- > 0) {
/* 1201 */       float x = srcPts[srcOff++];
/* 1202 */       float y = srcPts[srcOff++];
/* 1203 */       double w = this.m20 * x + this.m21 * y + this.m22;
/* 1205 */       if (w == 0.0D) {
/* 1206 */         dstPts[dstOff++] = x;
/* 1207 */         dstPts[dstOff++] = y;
/*      */         continue;
/*      */       } 
/* 1209 */       dstPts[dstOff++] = (this.m00 * x + this.m01 * y + this.m02) / w;
/* 1210 */       dstPts[dstOff++] = (this.m10 * x + this.m11 * y + this.m12) / w;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void transform(double[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
/* 1234 */     if (srcPts == null)
/* 1235 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1238 */     if (dstPts == null)
/* 1239 */       dstPts = new float[numPts * 2 + dstOff]; 
/* 1242 */     while (numPts-- > 0) {
/* 1243 */       double x = srcPts[srcOff++];
/* 1244 */       double y = srcPts[srcOff++];
/* 1245 */       double w = this.m20 * x + this.m21 * y + this.m22;
/* 1247 */       if (w == 0.0D) {
/* 1248 */         dstPts[dstOff++] = (float)x;
/* 1249 */         dstPts[dstOff++] = (float)y;
/*      */         continue;
/*      */       } 
/* 1251 */       dstPts[dstOff++] = (float)((this.m00 * x + this.m01 * y + this.m02) / w);
/* 1252 */       dstPts[dstOff++] = (float)((this.m10 * x + this.m11 * y + this.m12) / w);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Point2D inverseTransform(Point2D ptSrc, Point2D ptDst) throws NoninvertibleTransformException {
/* 1273 */     if (ptSrc == null)
/* 1274 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1277 */     if (ptDst == null)
/* 1278 */       if (ptSrc instanceof Point2D.Double) {
/* 1279 */         ptDst = new Point2D.Double();
/*      */       } else {
/* 1281 */         ptDst = new Point2D.Float();
/*      */       }  
/* 1285 */     double x = ptSrc.getX();
/* 1286 */     double y = ptSrc.getY();
/* 1288 */     double tmp_x = (this.m11 * this.m22 - this.m12 * this.m21) * x + (this.m02 * this.m21 - this.m01 * this.m22) * y + this.m01 * this.m12 - this.m02 * this.m11;
/* 1291 */     double tmp_y = (this.m12 * this.m20 - this.m10 * this.m22) * x + (this.m00 * this.m22 - this.m02 * this.m20) * y + this.m02 * this.m10 - this.m00 * this.m12;
/* 1294 */     double w = (this.m10 * this.m21 - this.m11 * this.m20) * x + (this.m01 * this.m20 - this.m00 * this.m21) * y + this.m00 * this.m11 - this.m01 * this.m10;
/* 1298 */     double wabs = w;
/* 1299 */     if (w < 0.0D)
/* 1300 */       wabs = -w; 
/* 1302 */     if (wabs < 1.0E-10D)
/* 1303 */       throw new NoninvertibleTransformException(JaiI18N.getString("PerspectiveTransform1")); 
/* 1308 */     ptDst.setLocation(tmp_x / w, tmp_y / w);
/* 1310 */     return ptDst;
/*      */   }
/*      */   
/*      */   public void inverseTransform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws NoninvertibleTransformException {
/* 1336 */     if (srcPts == null)
/* 1337 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1340 */     if (dstPts == null)
/* 1341 */       dstPts = new double[numPts * 2 + dstOff]; 
/* 1344 */     while (numPts-- > 0) {
/* 1345 */       double x = srcPts[srcOff++];
/* 1346 */       double y = srcPts[srcOff++];
/* 1348 */       double tmp_x = (this.m11 * this.m22 - this.m12 * this.m21) * x + (this.m02 * this.m21 - this.m01 * this.m22) * y + this.m01 * this.m12 - this.m02 * this.m11;
/* 1351 */       double tmp_y = (this.m12 * this.m20 - this.m10 * this.m22) * x + (this.m00 * this.m22 - this.m02 * this.m20) * y + this.m02 * this.m10 - this.m00 * this.m12;
/* 1354 */       double w = (this.m10 * this.m21 - this.m11 * this.m20) * x + (this.m01 * this.m20 - this.m00 * this.m21) * y + this.m00 * this.m11 - this.m01 * this.m10;
/* 1358 */       double wabs = w;
/* 1359 */       if (w < 0.0D)
/* 1360 */         wabs = -w; 
/* 1362 */       if (wabs < 1.0E-10D)
/* 1363 */         throw new NoninvertibleTransformException(JaiI18N.getString("PerspectiveTransform1")); 
/* 1367 */       dstPts[dstOff++] = tmp_x / w;
/* 1368 */       dstPts[dstOff++] = tmp_y / w;
/*      */     } 
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1376 */     StringBuffer sb = new StringBuffer();
/* 1377 */     sb.append("Perspective transform matrix\n");
/* 1378 */     sb.append(this.m00);
/* 1379 */     sb.append("\t");
/* 1380 */     sb.append(this.m01);
/* 1381 */     sb.append("\t");
/* 1382 */     sb.append(this.m02);
/* 1383 */     sb.append("\n");
/* 1384 */     sb.append(this.m10);
/* 1385 */     sb.append("\t");
/* 1386 */     sb.append(this.m11);
/* 1387 */     sb.append("\t");
/* 1388 */     sb.append(this.m12);
/* 1389 */     sb.append("\n");
/* 1390 */     sb.append(this.m20);
/* 1391 */     sb.append("\t");
/* 1392 */     sb.append(this.m21);
/* 1393 */     sb.append("\t");
/* 1394 */     sb.append(this.m22);
/* 1395 */     sb.append("\n");
/* 1396 */     return new String(sb);
/*      */   }
/*      */   
/*      */   public boolean isIdentity() {
/* 1404 */     return (this.m01 == 0.0D && this.m02 == 0.0D && this.m10 == 0.0D && this.m12 == 0.0D && this.m20 == 0.0D && this.m21 == 0.0D && this.m22 != 0.0D && this.m00 / this.m22 == 1.0D && this.m11 / this.m22 == 1.0D);
/*      */   }
/*      */   
/*      */   public Object clone() {
/*      */     try {
/* 1415 */       return super.clone();
/* 1416 */     } catch (CloneNotSupportedException e) {
/* 1418 */       throw new InternalError();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1429 */     if (!(obj instanceof PerspectiveTransform))
/* 1430 */       return false; 
/* 1433 */     PerspectiveTransform a = (PerspectiveTransform)obj;
/* 1435 */     return (this.m00 == a.m00 && this.m10 == a.m10 && this.m20 == a.m20 && this.m01 == a.m01 && this.m11 == a.m11 && this.m21 == a.m21 && this.m02 == a.m02 && this.m12 == a.m12 && this.m22 == a.m22);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PerspectiveTransform.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */