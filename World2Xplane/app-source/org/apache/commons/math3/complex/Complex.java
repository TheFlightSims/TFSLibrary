/*      */ package org.apache.commons.math3.complex;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import org.apache.commons.math3.Field;
/*      */ import org.apache.commons.math3.FieldElement;
/*      */ import org.apache.commons.math3.exception.NotPositiveException;
/*      */ import org.apache.commons.math3.exception.NullArgumentException;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.apache.commons.math3.util.MathUtils;
/*      */ 
/*      */ public class Complex implements FieldElement<Complex>, Serializable {
/*   59 */   public static final Complex I = new Complex(0.0D, 1.0D);
/*      */   
/*   62 */   public static final Complex NaN = new Complex(Double.NaN, Double.NaN);
/*      */   
/*   65 */   public static final Complex INF = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*      */   
/*   67 */   public static final Complex ONE = new Complex(1.0D, 0.0D);
/*      */   
/*   69 */   public static final Complex ZERO = new Complex(0.0D, 0.0D);
/*      */   
/*      */   private static final long serialVersionUID = -6195664516687396620L;
/*      */   
/*      */   private final double imaginary;
/*      */   
/*      */   private final double real;
/*      */   
/*      */   private final transient boolean isNaN;
/*      */   
/*      */   private final transient boolean isInfinite;
/*      */   
/*      */   public Complex(double real) {
/*   89 */     this(real, 0.0D);
/*      */   }
/*      */   
/*      */   public Complex(double real, double imaginary) {
/*   99 */     this.real = real;
/*  100 */     this.imaginary = imaginary;
/*  102 */     this.isNaN = (Double.isNaN(real) || Double.isNaN(imaginary));
/*  103 */     this.isInfinite = (!this.isNaN && (Double.isInfinite(real) || Double.isInfinite(imaginary)));
/*      */   }
/*      */   
/*      */   public double abs() {
/*  116 */     if (this.isNaN)
/*  117 */       return Double.NaN; 
/*  119 */     if (isInfinite())
/*  120 */       return Double.POSITIVE_INFINITY; 
/*  122 */     if (FastMath.abs(this.real) < FastMath.abs(this.imaginary)) {
/*  123 */       if (this.imaginary == 0.0D)
/*  124 */         return FastMath.abs(this.real); 
/*  126 */       double d = this.real / this.imaginary;
/*  127 */       return FastMath.abs(this.imaginary) * FastMath.sqrt(1.0D + d * d);
/*      */     } 
/*  129 */     if (this.real == 0.0D)
/*  130 */       return FastMath.abs(this.imaginary); 
/*  132 */     double q = this.imaginary / this.real;
/*  133 */     return FastMath.abs(this.real) * FastMath.sqrt(1.0D + q * q);
/*      */   }
/*      */   
/*      */   public Complex add(Complex addend) throws NullArgumentException {
/*  157 */     MathUtils.checkNotNull(addend);
/*  158 */     if (this.isNaN || addend.isNaN)
/*  159 */       return NaN; 
/*  162 */     return createComplex(this.real + addend.getReal(), this.imaginary + addend.getImaginary());
/*      */   }
/*      */   
/*      */   public Complex add(double addend) {
/*  175 */     if (this.isNaN || Double.isNaN(addend))
/*  176 */       return NaN; 
/*  179 */     return createComplex(this.real + addend, this.imaginary);
/*      */   }
/*      */   
/*      */   public Complex conjugate() {
/*  197 */     if (this.isNaN)
/*  198 */       return NaN; 
/*  201 */     return createComplex(this.real, -this.imaginary);
/*      */   }
/*      */   
/*      */   public Complex divide(Complex divisor) throws NullArgumentException {
/*  248 */     MathUtils.checkNotNull(divisor);
/*  249 */     if (this.isNaN || divisor.isNaN)
/*  250 */       return NaN; 
/*  253 */     double c = divisor.getReal();
/*  254 */     double d = divisor.getImaginary();
/*  255 */     if (c == 0.0D && d == 0.0D)
/*  256 */       return NaN; 
/*  259 */     if (divisor.isInfinite() && !isInfinite())
/*  260 */       return ZERO; 
/*  263 */     if (FastMath.abs(c) < FastMath.abs(d)) {
/*  264 */       double d1 = c / d;
/*  265 */       double d2 = c * d1 + d;
/*  266 */       return createComplex((this.real * d1 + this.imaginary) / d2, (this.imaginary * d1 - this.real) / d2);
/*      */     } 
/*  269 */     double q = d / c;
/*  270 */     double denominator = d * q + c;
/*  271 */     return createComplex((this.imaginary * q + this.real) / denominator, (this.imaginary - this.real * q) / denominator);
/*      */   }
/*      */   
/*      */   public Complex divide(double divisor) {
/*  285 */     if (this.isNaN || Double.isNaN(divisor))
/*  286 */       return NaN; 
/*  288 */     if (divisor == 0.0D)
/*  289 */       return NaN; 
/*  291 */     if (Double.isInfinite(divisor))
/*  292 */       return !isInfinite() ? ZERO : NaN; 
/*  294 */     return createComplex(this.real / divisor, this.imaginary / divisor);
/*      */   }
/*      */   
/*      */   public Complex reciprocal() {
/*  300 */     if (this.isNaN)
/*  301 */       return NaN; 
/*  304 */     if (this.real == 0.0D && this.imaginary == 0.0D)
/*  305 */       return NaN; 
/*  308 */     if (this.isInfinite)
/*  309 */       return ZERO; 
/*  312 */     if (FastMath.abs(this.real) < FastMath.abs(this.imaginary)) {
/*  313 */       double d1 = this.real / this.imaginary;
/*  314 */       double d2 = 1.0D / (this.real * d1 + this.imaginary);
/*  315 */       return createComplex(d2 * d1, -d2);
/*      */     } 
/*  317 */     double q = this.imaginary / this.real;
/*  318 */     double scale = 1.0D / (this.imaginary * q + this.real);
/*  319 */     return createComplex(scale, -scale * q);
/*      */   }
/*      */   
/*      */   public boolean equals(Object other) {
/*  340 */     if (this == other)
/*  341 */       return true; 
/*  343 */     if (other instanceof Complex) {
/*  344 */       Complex c = (Complex)other;
/*  345 */       if (c.isNaN)
/*  346 */         return this.isNaN; 
/*  348 */       return (this.real == c.real && this.imaginary == c.imaginary);
/*      */     } 
/*  351 */     return false;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  363 */     if (this.isNaN)
/*  364 */       return 7; 
/*  366 */     return 37 * (17 * MathUtils.hash(this.imaginary) + MathUtils.hash(this.real));
/*      */   }
/*      */   
/*      */   public double getImaginary() {
/*  376 */     return this.imaginary;
/*      */   }
/*      */   
/*      */   public double getReal() {
/*  385 */     return this.real;
/*      */   }
/*      */   
/*      */   public boolean isNaN() {
/*  396 */     return this.isNaN;
/*      */   }
/*      */   
/*      */   public boolean isInfinite() {
/*  409 */     return this.isInfinite;
/*      */   }
/*      */   
/*      */   public Complex multiply(Complex factor) throws NullArgumentException {
/*  438 */     MathUtils.checkNotNull(factor);
/*  439 */     if (this.isNaN || factor.isNaN)
/*  440 */       return NaN; 
/*  442 */     if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary) || Double.isInfinite(factor.real) || Double.isInfinite(factor.imaginary))
/*  447 */       return INF; 
/*  449 */     return createComplex(this.real * factor.real - this.imaginary * factor.imaginary, this.real * factor.imaginary + this.imaginary * factor.real);
/*      */   }
/*      */   
/*      */   public Complex multiply(int factor) {
/*  462 */     if (this.isNaN)
/*  463 */       return NaN; 
/*  465 */     if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary))
/*  467 */       return INF; 
/*  469 */     return createComplex(this.real * factor, this.imaginary * factor);
/*      */   }
/*      */   
/*      */   public Complex multiply(double factor) {
/*  481 */     if (this.isNaN || Double.isNaN(factor))
/*  482 */       return NaN; 
/*  484 */     if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary) || Double.isInfinite(factor))
/*  488 */       return INF; 
/*  490 */     return createComplex(this.real * factor, this.imaginary * factor);
/*      */   }
/*      */   
/*      */   public Complex negate() {
/*  501 */     if (this.isNaN)
/*  502 */       return NaN; 
/*  505 */     return createComplex(-this.real, -this.imaginary);
/*      */   }
/*      */   
/*      */   public Complex subtract(Complex subtrahend) throws NullArgumentException {
/*  528 */     MathUtils.checkNotNull(subtrahend);
/*  529 */     if (this.isNaN || subtrahend.isNaN)
/*  530 */       return NaN; 
/*  533 */     return createComplex(this.real - subtrahend.getReal(), this.imaginary - subtrahend.getImaginary());
/*      */   }
/*      */   
/*      */   public Complex subtract(double subtrahend) {
/*  546 */     if (this.isNaN || Double.isNaN(subtrahend))
/*  547 */       return NaN; 
/*  549 */     return createComplex(this.real - subtrahend, this.imaginary);
/*      */   }
/*      */   
/*      */   public Complex acos() {
/*  569 */     if (this.isNaN)
/*  570 */       return NaN; 
/*  573 */     return add(sqrt1z().multiply(I)).log().multiply(I.negate());
/*      */   }
/*      */   
/*      */   public Complex asin() {
/*  594 */     if (this.isNaN)
/*  595 */       return NaN; 
/*  598 */     return sqrt1z().add(multiply(I)).log().multiply(I.negate());
/*      */   }
/*      */   
/*      */   public Complex atan() {
/*  619 */     if (this.isNaN)
/*  620 */       return NaN; 
/*  623 */     return add(I).divide(I.subtract(this)).log().multiply(I.divide(createComplex(2.0D, 0.0D)));
/*      */   }
/*      */   
/*      */   public Complex cos() {
/*  660 */     if (this.isNaN)
/*  661 */       return NaN; 
/*  664 */     return createComplex(FastMath.cos(this.real) * FastMath.cosh(this.imaginary), -FastMath.sin(this.real) * FastMath.sinh(this.imaginary));
/*      */   }
/*      */   
/*      */   public Complex cosh() {
/*  700 */     if (this.isNaN)
/*  701 */       return NaN; 
/*  704 */     return createComplex(FastMath.cosh(this.real) * FastMath.cos(this.imaginary), FastMath.sinh(this.real) * FastMath.sin(this.imaginary));
/*      */   }
/*      */   
/*      */   public Complex exp() {
/*  741 */     if (this.isNaN)
/*  742 */       return NaN; 
/*  745 */     double expReal = FastMath.exp(this.real);
/*  746 */     return createComplex(expReal * FastMath.cos(this.imaginary), expReal * FastMath.sin(this.imaginary));
/*      */   }
/*      */   
/*      */   public Complex log() {
/*  786 */     if (this.isNaN)
/*  787 */       return NaN; 
/*  790 */     return createComplex(FastMath.log(abs()), FastMath.atan2(this.imaginary, this.real));
/*      */   }
/*      */   
/*      */   public Complex pow(Complex x) throws NullArgumentException {
/*  816 */     MathUtils.checkNotNull(x);
/*  817 */     return log().multiply(x).exp();
/*      */   }
/*      */   
/*      */   public Complex pow(double x) {
/*  828 */     return log().multiply(x).exp();
/*      */   }
/*      */   
/*      */   public Complex sin() {
/*  864 */     if (this.isNaN)
/*  865 */       return NaN; 
/*  868 */     return createComplex(FastMath.sin(this.real) * FastMath.cosh(this.imaginary), FastMath.cos(this.real) * FastMath.sinh(this.imaginary));
/*      */   }
/*      */   
/*      */   public Complex sinh() {
/*  904 */     if (this.isNaN)
/*  905 */       return NaN; 
/*  908 */     return createComplex(FastMath.sinh(this.real) * FastMath.cos(this.imaginary), FastMath.cosh(this.real) * FastMath.sin(this.imaginary));
/*      */   }
/*      */   
/*      */   public Complex sqrt() {
/*  947 */     if (this.isNaN)
/*  948 */       return NaN; 
/*  951 */     if (this.real == 0.0D && this.imaginary == 0.0D)
/*  952 */       return createComplex(0.0D, 0.0D); 
/*  955 */     double t = FastMath.sqrt((FastMath.abs(this.real) + abs()) / 2.0D);
/*  956 */     if (this.real >= 0.0D)
/*  957 */       return createComplex(t, this.imaginary / 2.0D * t); 
/*  959 */     return createComplex(FastMath.abs(this.imaginary) / 2.0D * t, FastMath.copySign(1.0D, this.imaginary) * t);
/*      */   }
/*      */   
/*      */   public Complex sqrt1z() {
/*  982 */     return createComplex(1.0D, 0.0D).subtract(multiply(this)).sqrt();
/*      */   }
/*      */   
/*      */   public Complex tan() {
/* 1018 */     if (this.isNaN || Double.isInfinite(this.real))
/* 1019 */       return NaN; 
/* 1021 */     if (this.imaginary > 20.0D)
/* 1022 */       return createComplex(0.0D, 1.0D); 
/* 1024 */     if (this.imaginary < -20.0D)
/* 1025 */       return createComplex(0.0D, -1.0D); 
/* 1028 */     double real2 = 2.0D * this.real;
/* 1029 */     double imaginary2 = 2.0D * this.imaginary;
/* 1030 */     double d = FastMath.cos(real2) + FastMath.cosh(imaginary2);
/* 1032 */     return createComplex(FastMath.sin(real2) / d, FastMath.sinh(imaginary2) / d);
/*      */   }
/*      */   
/*      */   public Complex tanh() {
/* 1069 */     if (this.isNaN || Double.isInfinite(this.imaginary))
/* 1070 */       return NaN; 
/* 1072 */     if (this.real > 20.0D)
/* 1073 */       return createComplex(1.0D, 0.0D); 
/* 1075 */     if (this.real < -20.0D)
/* 1076 */       return createComplex(-1.0D, 0.0D); 
/* 1078 */     double real2 = 2.0D * this.real;
/* 1079 */     double imaginary2 = 2.0D * this.imaginary;
/* 1080 */     double d = FastMath.cosh(real2) + FastMath.cos(imaginary2);
/* 1082 */     return createComplex(FastMath.sinh(real2) / d, FastMath.sin(imaginary2) / d);
/*      */   }
/*      */   
/*      */   public double getArgument() {
/* 1106 */     return FastMath.atan2(getImaginary(), getReal());
/*      */   }
/*      */   
/*      */   public List<Complex> nthRoot(int n) {
/* 1133 */     if (n <= 0)
/* 1134 */       throw new NotPositiveException(LocalizedFormats.CANNOT_COMPUTE_NTH_ROOT_FOR_NEGATIVE_N, Integer.valueOf(n)); 
/* 1138 */     List<Complex> result = new ArrayList<Complex>();
/* 1140 */     if (this.isNaN) {
/* 1141 */       result.add(NaN);
/* 1142 */       return result;
/*      */     } 
/* 1144 */     if (isInfinite()) {
/* 1145 */       result.add(INF);
/* 1146 */       return result;
/*      */     } 
/* 1150 */     double nthRootOfAbs = FastMath.pow(abs(), 1.0D / n);
/* 1153 */     double nthPhi = getArgument() / n;
/* 1154 */     double slice = 6.283185307179586D / n;
/* 1155 */     double innerPart = nthPhi;
/* 1156 */     for (int k = 0; k < n; k++) {
/* 1158 */       double realPart = nthRootOfAbs * FastMath.cos(innerPart);
/* 1159 */       double imaginaryPart = nthRootOfAbs * FastMath.sin(innerPart);
/* 1160 */       result.add(createComplex(realPart, imaginaryPart));
/* 1161 */       innerPart += slice;
/*      */     } 
/* 1164 */     return result;
/*      */   }
/*      */   
/*      */   protected Complex createComplex(double realPart, double imaginaryPart) {
/* 1178 */     return new Complex(realPart, imaginaryPart);
/*      */   }
/*      */   
/*      */   public static Complex valueOf(double realPart, double imaginaryPart) {
/* 1190 */     if (Double.isNaN(realPart) || Double.isNaN(imaginaryPart))
/* 1192 */       return NaN; 
/* 1194 */     return new Complex(realPart, imaginaryPart);
/*      */   }
/*      */   
/*      */   public static Complex valueOf(double realPart) {
/* 1204 */     if (Double.isNaN(realPart))
/* 1205 */       return NaN; 
/* 1207 */     return new Complex(realPart);
/*      */   }
/*      */   
/*      */   protected final Object readResolve() {
/* 1219 */     return createComplex(this.real, this.imaginary);
/*      */   }
/*      */   
/*      */   public ComplexField getField() {
/* 1224 */     return ComplexField.getInstance();
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1230 */     return "(" + this.real + ", " + this.imaginary + ")";
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\complex\Complex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */