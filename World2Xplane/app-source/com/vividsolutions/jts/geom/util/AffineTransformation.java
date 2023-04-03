/*      */ package com.vividsolutions.jts.geom.util;
/*      */ 
/*      */ import com.vividsolutions.jts.geom.Coordinate;
/*      */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*      */ import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
/*      */ import com.vividsolutions.jts.geom.Geometry;
/*      */ import com.vividsolutions.jts.util.Assert;
/*      */ 
/*      */ public class AffineTransformation implements Cloneable, CoordinateSequenceFilter {
/*      */   private double m00;
/*      */   
/*      */   private double m01;
/*      */   
/*      */   private double m02;
/*      */   
/*      */   private double m10;
/*      */   
/*      */   private double m11;
/*      */   
/*      */   private double m12;
/*      */   
/*      */   public static AffineTransformation reflectionInstance(double x0, double y0, double x1, double y1) {
/*  109 */     AffineTransformation trans = new AffineTransformation();
/*  110 */     trans.setToReflection(x0, y0, x1, y1);
/*  111 */     return trans;
/*      */   }
/*      */   
/*      */   public static AffineTransformation reflectionInstance(double x, double y) {
/*  124 */     AffineTransformation trans = new AffineTransformation();
/*  125 */     trans.setToReflection(x, y);
/*  126 */     return trans;
/*      */   }
/*      */   
/*      */   public static AffineTransformation rotationInstance(double theta) {
/*  141 */     return rotationInstance(Math.sin(theta), Math.cos(theta));
/*      */   }
/*      */   
/*      */   public static AffineTransformation rotationInstance(double sinTheta, double cosTheta) {
/*  157 */     AffineTransformation trans = new AffineTransformation();
/*  158 */     trans.setToRotation(sinTheta, cosTheta);
/*  159 */     return trans;
/*      */   }
/*      */   
/*      */   public static AffineTransformation rotationInstance(double theta, double x, double y) {
/*  175 */     return rotationInstance(Math.sin(theta), Math.cos(theta), x, y);
/*      */   }
/*      */   
/*      */   public static AffineTransformation rotationInstance(double sinTheta, double cosTheta, double x, double y) {
/*  193 */     AffineTransformation trans = new AffineTransformation();
/*  194 */     trans.setToRotation(sinTheta, cosTheta, x, y);
/*  195 */     return trans;
/*      */   }
/*      */   
/*      */   public static AffineTransformation scaleInstance(double xScale, double yScale) {
/*  207 */     AffineTransformation trans = new AffineTransformation();
/*  208 */     trans.setToScale(xScale, yScale);
/*  209 */     return trans;
/*      */   }
/*      */   
/*      */   public static AffineTransformation scaleInstance(double xScale, double yScale, double x, double y) {
/*  223 */     AffineTransformation trans = new AffineTransformation();
/*  224 */     trans.translate(-x, -y);
/*  225 */     trans.scale(xScale, yScale);
/*  226 */     trans.translate(x, y);
/*  227 */     return trans;
/*      */   }
/*      */   
/*      */   public static AffineTransformation shearInstance(double xShear, double yShear) {
/*  240 */     AffineTransformation trans = new AffineTransformation();
/*  241 */     trans.setToShear(xShear, yShear);
/*  242 */     return trans;
/*      */   }
/*      */   
/*      */   public static AffineTransformation translationInstance(double x, double y) {
/*  254 */     AffineTransformation trans = new AffineTransformation();
/*  255 */     trans.setToTranslation(x, y);
/*  256 */     return trans;
/*      */   }
/*      */   
/*      */   public AffineTransformation() {
/*  273 */     setToIdentity();
/*      */   }
/*      */   
/*      */   public AffineTransformation(double[] matrix) {
/*  286 */     this.m00 = matrix[0];
/*  287 */     this.m01 = matrix[1];
/*  288 */     this.m02 = matrix[2];
/*  289 */     this.m10 = matrix[3];
/*  290 */     this.m11 = matrix[4];
/*  291 */     this.m12 = matrix[5];
/*      */   }
/*      */   
/*      */   public AffineTransformation(double m00, double m01, double m02, double m10, double m11, double m12) {
/*  312 */     setTransformation(m00, m01, m02, m10, m11, m12);
/*      */   }
/*      */   
/*      */   public AffineTransformation(AffineTransformation trans) {
/*  323 */     setTransformation(trans);
/*      */   }
/*      */   
/*      */   public AffineTransformation(Coordinate src0, Coordinate src1, Coordinate src2, Coordinate dest0, Coordinate dest1, Coordinate dest2) {}
/*      */   
/*      */   public AffineTransformation setToIdentity() {
/*  360 */     this.m00 = 1.0D;
/*  360 */     this.m01 = 0.0D;
/*  360 */     this.m02 = 0.0D;
/*  361 */     this.m10 = 0.0D;
/*  361 */     this.m11 = 1.0D;
/*  361 */     this.m12 = 0.0D;
/*  362 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setTransformation(double m00, double m01, double m02, double m10, double m11, double m12) {
/*  383 */     this.m00 = m00;
/*  384 */     this.m01 = m01;
/*  385 */     this.m02 = m02;
/*  386 */     this.m10 = m10;
/*  387 */     this.m11 = m11;
/*  388 */     this.m12 = m12;
/*  389 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setTransformation(AffineTransformation trans) {
/*  400 */     this.m00 = trans.m00;
/*  400 */     this.m01 = trans.m01;
/*  400 */     this.m02 = trans.m02;
/*  401 */     this.m10 = trans.m10;
/*  401 */     this.m11 = trans.m11;
/*  401 */     this.m12 = trans.m12;
/*  402 */     return this;
/*      */   }
/*      */   
/*      */   public double[] getMatrixEntries() {
/*  418 */     return new double[] { this.m00, this.m01, this.m02, this.m10, this.m11, this.m12 };
/*      */   }
/*      */   
/*      */   public double getDeterminant() {
/*  439 */     return this.m00 * this.m11 - this.m01 * this.m10;
/*      */   }
/*      */   
/*      */   public AffineTransformation getInverse() throws NoninvertibleTransformationException {
/*  484 */     double det = getDeterminant();
/*  485 */     if (det == 0.0D)
/*  486 */       throw new NoninvertibleTransformationException("Transformation is non-invertible"); 
/*  488 */     double im00 = this.m11 / det;
/*  489 */     double im10 = -this.m10 / det;
/*  490 */     double im01 = -this.m01 / det;
/*  491 */     double im11 = this.m00 / det;
/*  492 */     double im02 = (this.m01 * this.m12 - this.m02 * this.m11) / det;
/*  493 */     double im12 = (-this.m00 * this.m12 + this.m10 * this.m02) / det;
/*  495 */     return new AffineTransformation(im00, im01, im02, im10, im11, im12);
/*      */   }
/*      */   
/*      */   public AffineTransformation setToReflectionBasic(double x0, double y0, double x1, double y1) {
/*  508 */     if (x0 == x1 && y0 == y1)
/*  509 */       throw new IllegalArgumentException("Reflection line points must be distinct"); 
/*  511 */     double dx = x1 - x0;
/*  512 */     double dy = y1 - y0;
/*  513 */     double d = Math.sqrt(dx * dx + dy * dy);
/*  514 */     double sin = dy / d;
/*  515 */     double cos = dx / d;
/*  516 */     double cs2 = 2.0D * sin * cos;
/*  517 */     double c2s2 = cos * cos - sin * sin;
/*  518 */     this.m00 = c2s2;
/*  518 */     this.m01 = cs2;
/*  518 */     this.m02 = 0.0D;
/*  519 */     this.m10 = cs2;
/*  519 */     this.m11 = -c2s2;
/*  519 */     this.m12 = 0.0D;
/*  520 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setToReflection(double x0, double y0, double x1, double y1) {
/*  535 */     if (x0 == x1 && y0 == y1)
/*  536 */       throw new IllegalArgumentException("Reflection line points must be distinct"); 
/*  539 */     setToTranslation(-x0, -y0);
/*  542 */     double dx = x1 - x0;
/*  543 */     double dy = y1 - y0;
/*  544 */     double d = Math.sqrt(dx * dx + dy * dy);
/*  545 */     double sin = dy / d;
/*  546 */     double cos = dx / d;
/*  547 */     rotate(-sin, cos);
/*  549 */     scale(1.0D, -1.0D);
/*  551 */     rotate(sin, cos);
/*  553 */     translate(x0, y0);
/*  554 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setToReflection(double x, double y) {
/*  576 */     if (x == 0.0D && y == 0.0D)
/*  577 */       throw new IllegalArgumentException("Reflection vector must be non-zero"); 
/*  584 */     if (x == y) {
/*  585 */       this.m00 = 0.0D;
/*  586 */       this.m01 = 1.0D;
/*  587 */       this.m02 = 0.0D;
/*  588 */       this.m10 = 1.0D;
/*  589 */       this.m11 = 0.0D;
/*  590 */       this.m12 = 0.0D;
/*  591 */       return this;
/*      */     } 
/*  595 */     double d = Math.sqrt(x * x + y * y);
/*  596 */     double sin = y / d;
/*  597 */     double cos = x / d;
/*  598 */     rotate(-sin, cos);
/*  600 */     scale(1.0D, -1.0D);
/*  602 */     rotate(sin, cos);
/*  603 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setToRotation(double theta) {
/*  624 */     setToRotation(Math.sin(theta), Math.cos(theta));
/*  625 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setToRotation(double sinTheta, double cosTheta) {
/*  645 */     this.m00 = cosTheta;
/*  645 */     this.m01 = -sinTheta;
/*  645 */     this.m02 = 0.0D;
/*  646 */     this.m10 = sinTheta;
/*  646 */     this.m11 = cosTheta;
/*  646 */     this.m12 = 0.0D;
/*  647 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setToRotation(double theta, double x, double y) {
/*  671 */     setToRotation(Math.sin(theta), Math.cos(theta), x, y);
/*  672 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setToRotation(double sinTheta, double cosTheta, double x, double y) {
/*  696 */     this.m00 = cosTheta;
/*  696 */     this.m01 = -sinTheta;
/*  696 */     this.m02 = x - x * cosTheta + y * sinTheta;
/*  697 */     this.m10 = sinTheta;
/*  697 */     this.m11 = cosTheta;
/*  697 */     this.m12 = y - x * sinTheta - y * cosTheta;
/*  698 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setToScale(double xScale, double yScale) {
/*  717 */     this.m00 = xScale;
/*  717 */     this.m01 = 0.0D;
/*  717 */     this.m02 = 0.0D;
/*  718 */     this.m10 = 0.0D;
/*  718 */     this.m11 = yScale;
/*  718 */     this.m12 = 0.0D;
/*  719 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setToShear(double xShear, double yShear) {
/*  742 */     this.m00 = 1.0D;
/*  742 */     this.m01 = xShear;
/*  742 */     this.m02 = 0.0D;
/*  743 */     this.m10 = yShear;
/*  743 */     this.m11 = 1.0D;
/*  743 */     this.m12 = 0.0D;
/*  744 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation setToTranslation(double dx, double dy) {
/*  762 */     this.m00 = 1.0D;
/*  762 */     this.m01 = 0.0D;
/*  762 */     this.m02 = dx;
/*  763 */     this.m10 = 0.0D;
/*  763 */     this.m11 = 1.0D;
/*  763 */     this.m12 = dy;
/*  764 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation reflect(double x0, double y0, double x1, double y1) {
/*  780 */     compose(reflectionInstance(x0, y0, x1, y1));
/*  781 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation reflect(double x, double y) {
/*  795 */     compose(reflectionInstance(x, y));
/*  796 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation rotate(double theta) {
/*  811 */     compose(rotationInstance(theta));
/*  812 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation rotate(double sinTheta, double cosTheta) {
/*  827 */     compose(rotationInstance(sinTheta, cosTheta));
/*  828 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation rotate(double theta, double x, double y) {
/*  845 */     compose(rotationInstance(theta, x, y));
/*  846 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation rotate(double sinTheta, double cosTheta, double x, double y) {
/*  863 */     compose(rotationInstance(sinTheta, cosTheta));
/*  864 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation scale(double xScale, double yScale) {
/*  878 */     compose(scaleInstance(xScale, yScale));
/*  879 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation shear(double xShear, double yShear) {
/*  893 */     compose(shearInstance(xShear, yShear));
/*  894 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation translate(double x, double y) {
/*  908 */     compose(translationInstance(x, y));
/*  909 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation compose(AffineTransformation trans) {
/*  929 */     double mp00 = trans.m00 * this.m00 + trans.m01 * this.m10;
/*  930 */     double mp01 = trans.m00 * this.m01 + trans.m01 * this.m11;
/*  931 */     double mp02 = trans.m00 * this.m02 + trans.m01 * this.m12 + trans.m02;
/*  932 */     double mp10 = trans.m10 * this.m00 + trans.m11 * this.m10;
/*  933 */     double mp11 = trans.m10 * this.m01 + trans.m11 * this.m11;
/*  934 */     double mp12 = trans.m10 * this.m02 + trans.m11 * this.m12 + trans.m12;
/*  935 */     this.m00 = mp00;
/*  936 */     this.m01 = mp01;
/*  937 */     this.m02 = mp02;
/*  938 */     this.m10 = mp10;
/*  939 */     this.m11 = mp11;
/*  940 */     this.m12 = mp12;
/*  941 */     return this;
/*      */   }
/*      */   
/*      */   public AffineTransformation composeBefore(AffineTransformation trans) {
/*  960 */     double mp00 = this.m00 * trans.m00 + this.m01 * trans.m10;
/*  961 */     double mp01 = this.m00 * trans.m01 + this.m01 * trans.m11;
/*  962 */     double mp02 = this.m00 * trans.m02 + this.m01 * trans.m12 + this.m02;
/*  963 */     double mp10 = this.m10 * trans.m00 + this.m11 * trans.m10;
/*  964 */     double mp11 = this.m10 * trans.m01 + this.m11 * trans.m11;
/*  965 */     double mp12 = this.m10 * trans.m02 + this.m11 * trans.m12 + this.m12;
/*  966 */     this.m00 = mp00;
/*  967 */     this.m01 = mp01;
/*  968 */     this.m02 = mp02;
/*  969 */     this.m10 = mp10;
/*  970 */     this.m11 = mp11;
/*  971 */     this.m12 = mp12;
/*  972 */     return this;
/*      */   }
/*      */   
/*      */   public Coordinate transform(Coordinate src, Coordinate dest) {
/*  986 */     double xp = this.m00 * src.x + this.m01 * src.y + this.m02;
/*  987 */     double yp = this.m10 * src.x + this.m11 * src.y + this.m12;
/*  988 */     dest.x = xp;
/*  989 */     dest.y = yp;
/*  990 */     return dest;
/*      */   }
/*      */   
/*      */   public Geometry transform(Geometry g) {
/* 1002 */     Geometry g2 = (Geometry)g.clone();
/* 1003 */     g2.apply(this);
/* 1004 */     return g2;
/*      */   }
/*      */   
/*      */   public void transform(CoordinateSequence seq, int i) {
/* 1016 */     double xp = this.m00 * seq.getOrdinate(i, 0) + this.m01 * seq.getOrdinate(i, 1) + this.m02;
/* 1017 */     double yp = this.m10 * seq.getOrdinate(i, 0) + this.m11 * seq.getOrdinate(i, 1) + this.m12;
/* 1018 */     seq.setOrdinate(i, 0, xp);
/* 1019 */     seq.setOrdinate(i, 1, yp);
/*      */   }
/*      */   
/*      */   public void filter(CoordinateSequence seq, int i) {
/* 1030 */     transform(seq, i);
/*      */   }
/*      */   
/*      */   public boolean isGeometryChanged() {
/* 1035 */     return true;
/*      */   }
/*      */   
/*      */   public boolean isDone() {
/* 1046 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isIdentity() {
/* 1056 */     return (this.m00 == 1.0D && this.m01 == 0.0D && this.m02 == 0.0D && this.m10 == 0.0D && this.m11 == 1.0D && this.m12 == 0.0D);
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1071 */     if (obj == null)
/* 1071 */       return false; 
/* 1072 */     if (!(obj instanceof AffineTransformation))
/* 1073 */       return false; 
/* 1075 */     AffineTransformation trans = (AffineTransformation)obj;
/* 1076 */     return (this.m00 == trans.m00 && this.m01 == trans.m01 && this.m02 == trans.m02 && this.m10 == trans.m10 && this.m11 == trans.m11 && this.m12 == trans.m12);
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1096 */     return "AffineTransformation[[" + this.m00 + ", " + this.m01 + ", " + this.m02 + "], [" + this.m10 + ", " + this.m11 + ", " + this.m12 + "]]";
/*      */   }
/*      */   
/*      */   public Object clone() {
/*      */     try {
/* 1109 */       return super.clone();
/* 1110 */     } catch (Exception ex) {
/* 1111 */       Assert.shouldNeverReachHere();
/* 1113 */       return null;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\AffineTransformation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */