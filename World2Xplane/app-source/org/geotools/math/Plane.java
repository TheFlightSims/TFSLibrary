/*     */ package org.geotools.math;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.vecmath.MismatchedSizeException;
/*     */ import javax.vecmath.Point3d;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class Plane implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 2956201711131316723L;
/*     */   
/*     */   public double c;
/*     */   
/*     */   public double cx;
/*     */   
/*     */   public double cy;
/*     */   
/*     */   public final double z(double x, double y) {
/*  90 */     return this.c + this.cx * x + this.cy * y;
/*     */   }
/*     */   
/*     */   public final double y(double x, double z) {
/* 106 */     return (z - this.c + this.cx * x) / this.cy;
/*     */   }
/*     */   
/*     */   public final double x(double y, double z) {
/* 122 */     return (z - this.c + this.cy * y) / this.cx;
/*     */   }
/*     */   
/*     */   public void setPlane(Point3d P1, Point3d P2, Point3d P3) throws ArithmeticException {
/* 135 */     double m00 = P2.x * P3.y - P3.x * P2.y;
/* 136 */     double m01 = P3.x * P1.y - P1.x * P3.y;
/* 137 */     double m02 = P1.x * P2.y - P2.x * P1.y;
/* 138 */     double det = m00 + m01 + m02;
/* 139 */     if (det == 0.0D)
/* 140 */       throw new ArithmeticException("Points are colinear"); 
/* 142 */     this.c = (m00 * P1.z + m01 * P2.z + m02 * P3.z) / det;
/* 143 */     this.cx = ((P2.y - P3.y) * P1.z + (P3.y - P1.y) * P2.z + (P1.y - P2.y) * P3.z) / det;
/* 144 */     this.cy = ((P3.x - P2.x) * P1.z + (P1.x - P3.x) * P2.z + (P2.x - P1.x) * P3.z) / det;
/*     */   }
/*     */   
/*     */   public void setPlane(double[] x, double[] y, double[] z) throws MismatchedSizeException {
/* 162 */     int N = x.length;
/* 163 */     if (N != y.length || N != z.length)
/* 164 */       throw new MismatchedSizeException(); 
/* 166 */     double sum_x = 0.0D;
/* 167 */     double sum_y = 0.0D;
/* 168 */     double sum_z = 0.0D;
/* 169 */     double sum_xx = 0.0D;
/* 170 */     double sum_yy = 0.0D;
/* 171 */     double sum_xy = 0.0D;
/* 172 */     double sum_zx = 0.0D;
/* 173 */     double sum_zy = 0.0D;
/* 174 */     for (int i = 0; i < N; i++) {
/* 175 */       double xi = x[i];
/* 176 */       double yi = y[i];
/* 177 */       double zi = z[i];
/* 178 */       sum_x += xi;
/* 179 */       sum_y += yi;
/* 180 */       sum_z += zi;
/* 181 */       sum_xx += xi * xi;
/* 182 */       sum_yy += yi * yi;
/* 183 */       sum_xy += xi * yi;
/* 184 */       sum_zx += zi * xi;
/* 185 */       sum_zy += zi * yi;
/*     */     } 
/* 191 */     double ZX = sum_zx - sum_z * sum_x / N;
/* 192 */     double ZY = sum_zy - sum_z * sum_y / N;
/* 193 */     double XX = sum_xx - sum_x * sum_x / N;
/* 194 */     double XY = sum_xy - sum_x * sum_y / N;
/* 195 */     double YY = sum_yy - sum_y * sum_y / N;
/* 196 */     double den = XY * XY - XX * YY;
/* 198 */     this.cy = (ZX * XY - ZY * XX) / den;
/* 199 */     this.cx = (ZY * XY - ZX * YY) / den;
/* 200 */     this.c = (sum_z - this.cx * sum_x + this.cy * sum_y) / N;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 214 */     StringBuilder buffer = new StringBuilder("z(x,y)= ");
/* 215 */     if (this.c == 0.0D && this.cx == 0.0D && this.cy == 0.0D) {
/* 216 */       buffer.append(0);
/*     */     } else {
/* 218 */       if (this.c != 0.0D)
/* 219 */         buffer.append(this.c).append(" + "); 
/* 221 */       if (this.cx != 0.0D) {
/* 222 */         buffer.append(this.cx).append("*x");
/* 223 */         if (this.cy != 0.0D)
/* 224 */           buffer.append(" + "); 
/*     */       } 
/* 227 */       if (this.cy != 0.0D)
/* 228 */         buffer.append(this.cy).append("*y"); 
/*     */     } 
/* 231 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 239 */     if (object != null && getClass().equals(object.getClass())) {
/* 240 */       Plane that = (Plane)object;
/* 241 */       return (Double.doubleToLongBits(this.c) == Double.doubleToLongBits(that.c) && Double.doubleToLongBits(this.cx) == Double.doubleToLongBits(that.cx) && Double.doubleToLongBits(this.cy) == Double.doubleToLongBits(that.cy));
/*     */     } 
/* 245 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 254 */     long code = Double.doubleToLongBits(this.c) + 37L * (Double.doubleToLongBits(this.cx) + 37L * Double.doubleToLongBits(this.cy));
/* 257 */     return (int)code ^ (int)(code >>> 32L);
/*     */   }
/*     */   
/*     */   public Plane clone() {
/*     */     try {
/* 266 */       return (Plane)super.clone();
/* 267 */     } catch (CloneNotSupportedException exception) {
/* 268 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\math\Plane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */