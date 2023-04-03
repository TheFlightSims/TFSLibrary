/*     */ package org.geotools.referencing.datum;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.geotools.referencing.operation.matrix.Matrix4;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.referencing.wkt.Formattable;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class BursaWolfParameters extends Formattable implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 754825592343010900L;
/*     */   
/*     */   public double dx;
/*     */   
/*     */   public double dy;
/*     */   
/*     */   public double dz;
/*     */   
/*     */   public double ex;
/*     */   
/*     */   public double ey;
/*     */   
/*     */   public double ez;
/*     */   
/*     */   public double ppm;
/*     */   
/*     */   public final GeodeticDatum targetDatum;
/*     */   
/*     */   public BursaWolfParameters(GeodeticDatum target) {
/*  90 */     this.targetDatum = target;
/*     */   }
/*     */   
/*     */   public boolean isIdentity() {
/* 100 */     return (this.dx == 0.0D && this.dy == 0.0D && this.dz == 0.0D && this.ex == 0.0D && this.ey == 0.0D && this.ez == 0.0D && this.ppm == 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isTranslation() {
/* 109 */     return (this.ex == 0.0D && this.ey == 0.0D && this.ez == 0.0D && this.ppm == 0.0D);
/*     */   }
/*     */   
/*     */   public XMatrix getAffineTransform() {
/* 135 */     double S = 1.0D + this.ppm / 1000000.0D;
/* 136 */     double RS = 4.84813681109536E-6D * S;
/* 137 */     return (XMatrix)new Matrix4(S, -this.ez * RS, this.ey * RS, this.dx, this.ez * RS, S, -this.ex * RS, this.dy, -this.ey * RS, this.ex * RS, S, this.dz, 0.0D, 0.0D, 0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public void setAffineTransform(Matrix matrix, double eps) throws IllegalArgumentException {
/* 159 */     if (matrix.getNumCol() != 4 || matrix.getNumRow() != 4)
/* 161 */       throw new IllegalArgumentException("Illegal matrix size."); 
/* 163 */     for (int i = 0; i < 4; i++) {
/* 164 */       if (matrix.getElement(3, i) != ((i == 3) ? true : false))
/* 165 */         throw new IllegalArgumentException(Errors.format(106)); 
/*     */     } 
/* 168 */     this.dx = matrix.getElement(0, 3);
/* 169 */     this.dy = matrix.getElement(1, 3);
/* 170 */     this.dz = matrix.getElement(2, 3);
/* 171 */     double S = (matrix.getElement(0, 0) + matrix.getElement(1, 1) + matrix.getElement(2, 2)) / 3.0D;
/* 174 */     double RS = 4.84813681109536E-6D * S;
/* 175 */     this.ppm = (S - 1.0D) * 1000000.0D;
/* 176 */     for (int j = 0; j < 2; j++) {
/* 177 */       double eltS = (matrix.getElement(j, j) - 1.0D) * 1000000.0D;
/* 178 */       if (Math.abs(eltS - this.ppm) > eps)
/* 180 */         throw new IllegalArgumentException("Scale is not uniform."); 
/* 182 */       for (int k = j + 1; k < 3; ) {
/* 183 */         double elt1 = matrix.getElement(j, k) / RS;
/* 184 */         double elt2 = matrix.getElement(k, j) / RS;
/* 186 */         if (Math.abs(elt1 + elt2) > eps)
/* 188 */           throw new IllegalArgumentException("Matrix is not antisymmetric."); 
/* 190 */         double value = 0.5D * (elt1 - elt2);
/* 191 */         if (j == 0) {
/* 191 */           switch (k) {
/*     */             case 1:
/* 192 */               this.ez = -value;
/*     */               break;
/*     */             case 2:
/* 193 */               this.ey = value;
/*     */               break;
/*     */             default:
/* 195 */               assert j == 1 && k == 2;
/* 196 */               this.ex = -value;
/*     */               break;
/*     */           } 
/*     */           k++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 199 */     assert getAffineTransform().equals(matrix, eps * RS);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 210 */     long code = 754825592343010900L;
/* 211 */     code = code * 37L + Double.doubleToLongBits(this.dx);
/* 212 */     code = code * 37L + Double.doubleToLongBits(this.dy);
/* 213 */     code = code * 37L + Double.doubleToLongBits(this.dz);
/* 214 */     code = code * 37L + Double.doubleToLongBits(this.ex);
/* 215 */     code = code * 37L + Double.doubleToLongBits(this.ey);
/* 216 */     code = code * 37L + Double.doubleToLongBits(this.ez);
/* 217 */     code = code * 37L + Double.doubleToLongBits(this.ppm);
/* 218 */     return (int)(code >>> 32L) ^ (int)code;
/*     */   }
/*     */   
/*     */   public BursaWolfParameters clone() {
/*     */     try {
/* 229 */       return (BursaWolfParameters)super.clone();
/* 230 */     } catch (CloneNotSupportedException exception) {
/* 232 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 244 */     if (object instanceof BursaWolfParameters) {
/* 245 */       BursaWolfParameters that = (BursaWolfParameters)object;
/* 246 */       return (Utilities.equals(this.dx, that.dx) && Utilities.equals(this.dy, that.dy) && Utilities.equals(this.dz, that.dz) && Utilities.equals(this.ex, that.ex) && Utilities.equals(this.ey, that.ey) && Utilities.equals(this.ez, that.ez) && Utilities.equals(this.ppm, that.ppm) && Utilities.equals(this.targetDatum, that.targetDatum));
/*     */     } 
/* 255 */     return false;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 271 */     formatter.append(this.dx);
/* 272 */     formatter.append(this.dy);
/* 273 */     formatter.append(this.dz);
/* 274 */     formatter.append(this.ex);
/* 275 */     formatter.append(this.ey);
/* 276 */     formatter.append(this.ez);
/* 277 */     formatter.append(this.ppm);
/* 278 */     if (!DefaultGeodeticDatum.isWGS84((Datum)this.targetDatum)) {
/* 279 */       if (this.targetDatum != null)
/* 280 */         formatter.append(this.targetDatum.getName().getCode()); 
/* 282 */       return super.formatWKT(formatter);
/*     */     } 
/* 284 */     return "TOWGS84";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\datum\BursaWolfParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */