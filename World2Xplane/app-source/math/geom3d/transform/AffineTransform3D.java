/*     */ package math.geom3d.transform;
/*     */ 
/*     */ import math.geom3d.Point3D;
/*     */ import math.geom3d.Vector3D;
/*     */ 
/*     */ public class AffineTransform3D implements Bijection3D {
/*     */   protected double m00;
/*     */   
/*     */   protected double m01;
/*     */   
/*     */   protected double m02;
/*     */   
/*     */   protected double m03;
/*     */   
/*     */   protected double m10;
/*     */   
/*     */   protected double m11;
/*     */   
/*     */   protected double m12;
/*     */   
/*     */   protected double m13;
/*     */   
/*     */   protected double m20;
/*     */   
/*     */   protected double m21;
/*     */   
/*     */   protected double m22;
/*     */   
/*     */   protected double m23;
/*     */   
/*     */   public static final AffineTransform3D createTranslation(Vector3D vec) {
/*  51 */     return createTranslation(vec.getX(), vec.getY(), vec.getZ());
/*     */   }
/*     */   
/*     */   public static final AffineTransform3D createTranslation(double x, double y, double z) {
/*  56 */     return new AffineTransform3D(1.0D, 0.0D, 0.0D, x, 0.0D, 1.0D, 0.0D, y, 0.0D, 0.0D, 1.0D, z);
/*     */   }
/*     */   
/*     */   public static final AffineTransform3D createRotationOx(double theta) {
/*  60 */     double cot = Math.cos(theta);
/*  61 */     double sit = Math.sin(theta);
/*  62 */     return new AffineTransform3D(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, cot, -sit, 0.0D, 0.0D, sit, cot, 
/*  63 */         0.0D);
/*     */   }
/*     */   
/*     */   public static final AffineTransform3D createRotationOy(double theta) {
/*  67 */     double cot = Math.cos(theta);
/*  68 */     double sit = Math.sin(theta);
/*  69 */     return new AffineTransform3D(cot, 0.0D, sit, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, -sit, 0.0D, cot, 
/*  70 */         0.0D);
/*     */   }
/*     */   
/*     */   public static final AffineTransform3D createRotationOz(double theta) {
/*  74 */     double cot = Math.cos(theta);
/*  75 */     double sit = Math.sin(theta);
/*  76 */     return new AffineTransform3D(cot, -sit, 0.0D, 0.0D, sit, cot, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 
/*  77 */         0.0D);
/*     */   }
/*     */   
/*     */   public static final AffineTransform3D createScaling(double s) {
/*  81 */     return createScaling(s, s, s);
/*     */   }
/*     */   
/*     */   public static final AffineTransform3D createScaling(double sx, double sy, double sz) {
/*  85 */     return new AffineTransform3D(sx, 0.0D, 0.0D, 0.0D, 0.0D, sy, 0.0D, 0.0D, 0.0D, 0.0D, sz, 0.0D);
/*     */   }
/*     */   
/*     */   public AffineTransform3D() {
/*  94 */     this.m00 = this.m11 = this.m22 = 1.0D;
/*  95 */     this.m01 = this.m02 = this.m03 = 0.0D;
/*  96 */     this.m10 = this.m12 = this.m13 = 0.0D;
/*  97 */     this.m20 = this.m21 = this.m23 = 0.0D;
/*     */   }
/*     */   
/*     */   public AffineTransform3D(double[] coefs) {
/* 101 */     if (coefs.length == 9) {
/* 102 */       this.m00 = coefs[0];
/* 103 */       this.m01 = coefs[1];
/* 104 */       this.m02 = coefs[2];
/* 105 */       this.m10 = coefs[3];
/* 106 */       this.m11 = coefs[4];
/* 107 */       this.m12 = coefs[5];
/* 108 */       this.m20 = coefs[6];
/* 109 */       this.m21 = coefs[7];
/* 110 */       this.m22 = coefs[8];
/* 111 */     } else if (coefs.length == 12) {
/* 112 */       this.m00 = coefs[0];
/* 113 */       this.m01 = coefs[1];
/* 114 */       this.m02 = coefs[2];
/* 115 */       this.m03 = coefs[3];
/* 116 */       this.m10 = coefs[4];
/* 117 */       this.m11 = coefs[5];
/* 118 */       this.m12 = coefs[6];
/* 119 */       this.m13 = coefs[7];
/* 120 */       this.m20 = coefs[8];
/* 121 */       this.m21 = coefs[9];
/* 122 */       this.m22 = coefs[10];
/* 123 */       this.m23 = coefs[11];
/*     */     } 
/*     */   }
/*     */   
/*     */   public AffineTransform3D(double xx, double yx, double zx, double tx, double xy, double yy, double zy, double ty, double xz, double yz, double zz, double tz) {
/* 130 */     this.m00 = xx;
/* 131 */     this.m01 = yx;
/* 132 */     this.m02 = zx;
/* 133 */     this.m03 = tx;
/* 134 */     this.m10 = xy;
/* 135 */     this.m11 = yy;
/* 136 */     this.m12 = zy;
/* 137 */     this.m13 = ty;
/* 138 */     this.m20 = xz;
/* 139 */     this.m21 = yz;
/* 140 */     this.m22 = zz;
/* 141 */     this.m23 = tz;
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 148 */     if (this.m00 != 1.0D)
/* 149 */       return false; 
/* 150 */     if (this.m11 != 1.0D)
/* 151 */       return false; 
/* 152 */     if (this.m22 != 0.0D)
/* 153 */       return false; 
/* 154 */     if (this.m01 != 0.0D)
/* 155 */       return false; 
/* 156 */     if (this.m02 != 0.0D)
/* 157 */       return false; 
/* 158 */     if (this.m03 != 0.0D)
/* 159 */       return false; 
/* 160 */     if (this.m10 != 0.0D)
/* 161 */       return false; 
/* 162 */     if (this.m12 != 0.0D)
/* 163 */       return false; 
/* 164 */     if (this.m13 != 0.0D)
/* 165 */       return false; 
/* 166 */     if (this.m20 != 0.0D)
/* 167 */       return false; 
/* 168 */     if (this.m21 != 0.0D)
/* 169 */       return false; 
/* 170 */     if (this.m23 != 0.0D)
/* 171 */       return false; 
/* 172 */     return true;
/*     */   }
/*     */   
/*     */   public double[] coefficients() {
/* 180 */     double[] tab = { 
/* 180 */         this.m00, this.m01, this.m02, this.m03, this.m10, this.m11, this.m12, this.m13, this.m20, this.m21, 
/* 180 */         this.m22, this.m23 };
/* 181 */     return tab;
/*     */   }
/*     */   
/*     */   private double determinant() {
/* 190 */     return this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) - this.m01 * (this.m10 * this.m22 - this.m20 * this.m12) + 
/* 191 */       this.m02 * (this.m10 * this.m21 - this.m20 * this.m11);
/*     */   }
/*     */   
/*     */   public AffineTransform3D inverse() {
/* 198 */     double det = determinant();
/* 199 */     return new AffineTransform3D((
/* 200 */         this.m11 * this.m22 - this.m21 * this.m12) / det, (
/* 201 */         this.m21 * this.m01 - this.m01 * this.m22) / det, (
/* 202 */         this.m01 * this.m12 - this.m11 * this.m02) / det, (
/* 203 */         this.m01 * (this.m22 * this.m13 - this.m12 * this.m23) + this.m02 * (this.m11 * this.m23 - this.m21 * this.m13) - 
/* 204 */         this.m03 * (this.m11 * this.m22 - this.m21 * this.m12)) / det, (
/* 205 */         this.m20 * this.m12 - this.m10 * this.m22) / det, (
/* 206 */         this.m00 * this.m22 - this.m20 * this.m02) / det, (
/* 207 */         this.m10 * this.m02 - this.m00 * this.m12) / det, (
/* 208 */         this.m00 * (this.m12 * this.m23 - this.m22 * this.m13) - this.m02 * (this.m10 * this.m23 - this.m20 * this.m13) + 
/* 209 */         this.m03 * (this.m10 * this.m22 - this.m20 * this.m12)) / det, (
/* 210 */         this.m10 * this.m21 - this.m20 * this.m11) / det, (
/* 211 */         this.m20 * this.m01 - this.m00 * this.m21) / det, (
/* 212 */         this.m00 * this.m11 - this.m10 * this.m01) / det, (
/* 213 */         this.m00 * (this.m21 * this.m13 - this.m11 * this.m23) + this.m01 * (this.m10 * this.m23 - this.m20 * this.m13) - 
/* 214 */         this.m03 * (this.m10 * this.m21 - this.m20 * this.m11)) / det);
/*     */   }
/*     */   
/*     */   public void transform(AffineTransform3D trans) {
/* 226 */     double n00 = this.m00 * trans.m00 + this.m10 * trans.m01;
/* 227 */     double n10 = this.m00 * trans.m10 + this.m10 * trans.m11;
/* 228 */     double n01 = this.m01 * trans.m00 + this.m11 * trans.m01;
/* 229 */     double n11 = this.m01 * trans.m10 + this.m11 * trans.m11;
/* 230 */     double n02 = this.m02 * trans.m00 + this.m12 * trans.m01 + trans.m02;
/* 231 */     double n12 = this.m02 * trans.m10 + this.m12 * trans.m11 + trans.m12;
/* 232 */     this.m00 = n00;
/* 233 */     this.m01 = n01;
/* 234 */     this.m02 = n02;
/* 235 */     this.m10 = n10;
/* 236 */     this.m11 = n11;
/* 237 */     this.m12 = n12;
/*     */   }
/*     */   
/*     */   public void preConcatenate(AffineTransform3D trans) {
/* 244 */     double n00 = trans.m00 * this.m00 + trans.m10 * this.m01;
/* 245 */     double n10 = trans.m00 * this.m10 + trans.m10 * this.m11;
/* 246 */     double n01 = trans.m01 * this.m00 + trans.m11 * this.m01;
/* 247 */     double n11 = trans.m01 * this.m10 + trans.m11 * this.m11;
/* 248 */     double n02 = trans.m02 * this.m00 + trans.m12 * this.m01 + this.m02;
/* 249 */     double n12 = trans.m02 * this.m10 + trans.m12 * this.m11 + this.m12;
/* 250 */     this.m00 = n00;
/* 251 */     this.m01 = n01;
/* 252 */     this.m02 = n02;
/* 253 */     this.m10 = n10;
/* 254 */     this.m11 = n11;
/* 255 */     this.m12 = n12;
/*     */   }
/*     */   
/*     */   public Point3D[] transformPoints(Point3D[] src, Point3D[] dst) {
/* 259 */     if (dst == null)
/* 260 */       dst = new Point3D[src.length]; 
/* 261 */     if (dst[0] == null)
/* 262 */       for (int j = 0; j < src.length; j++)
/* 263 */         dst[j] = new Point3D();  
/* 265 */     double[] coef = coefficients();
/* 267 */     for (int i = 0; i < src.length; i++)
/* 268 */       dst[i] = new Point3D(
/* 269 */           src[i].getX() * coef[0] + src[i].getY() * coef[1] + src[i].getZ() * coef[2] + coef[3], 
/* 270 */           src[i].getX() * coef[4] + src[i].getY() * coef[5] + src[i].getZ() * coef[6] + coef[7], 
/* 271 */           src[i].getX() * coef[8] + src[i].getY() * coef[9] + src[i].getZ() * coef[10] + coef[12]); 
/* 273 */     return dst;
/*     */   }
/*     */   
/*     */   public Point3D transformPoint(Point3D src) {
/* 277 */     double[] coef = coefficients();
/* 278 */     return new Point3D(src.getX() * coef[0] + src.getY() * coef[1] + 
/* 279 */         src.getZ() * coef[2] + coef[3], src.getX() * coef[4] + src.getY() * 
/* 280 */         coef[5] + src.getZ() * coef[6] + coef[7], src.getX() * coef[8] + 
/* 281 */         src.getY() * coef[9] + src.getZ() * coef[10] + coef[12]);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 290 */     if (!(obj instanceof AffineTransform3D))
/* 291 */       return false; 
/* 293 */     double[] tab = ((AffineTransform3D)obj).coefficients();
/* 295 */     if (Math.abs(tab[0] - this.m00) > 1.0E-12D)
/* 296 */       return false; 
/* 297 */     if (Math.abs(tab[1] - this.m01) > 1.0E-12D)
/* 298 */       return false; 
/* 299 */     if (Math.abs(tab[2] - this.m02) > 1.0E-12D)
/* 300 */       return false; 
/* 301 */     if (Math.abs(tab[3] - this.m03) > 1.0E-12D)
/* 302 */       return false; 
/* 303 */     if (Math.abs(tab[4] - this.m10) > 1.0E-12D)
/* 304 */       return false; 
/* 305 */     if (Math.abs(tab[5] - this.m11) > 1.0E-12D)
/* 306 */       return false; 
/* 307 */     if (Math.abs(tab[6] - this.m12) > 1.0E-12D)
/* 308 */       return false; 
/* 309 */     if (Math.abs(tab[7] - this.m13) > 1.0E-12D)
/* 310 */       return false; 
/* 311 */     if (Math.abs(tab[8] - this.m20) > 1.0E-12D)
/* 312 */       return false; 
/* 313 */     if (Math.abs(tab[9] - this.m21) > 1.0E-12D)
/* 314 */       return false; 
/* 315 */     if (Math.abs(tab[10] - this.m22) > 1.0E-12D)
/* 316 */       return false; 
/* 317 */     if (Math.abs(tab[11] - this.m23) > 1.0E-12D)
/* 318 */       return false; 
/* 319 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\transform\AffineTransform3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */