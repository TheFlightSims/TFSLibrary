/*     */ package org.geotools.metadata.iso.identification;
/*     */ 
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.metadata.identification.RepresentativeFraction;
/*     */ 
/*     */ public class RepresentativeFractionImpl extends Number implements RepresentativeFraction {
/*     */   private static final long serialVersionUID = 7228422109144637537L;
/*     */   
/*     */   private long denominator;
/*     */   
/*     */   public RepresentativeFractionImpl() {}
/*     */   
/*     */   public RepresentativeFractionImpl(long denominator) {
/*  62 */     this.denominator = denominator;
/*     */   }
/*     */   
/*     */   public static RepresentativeFraction fromScale(double scale) throws IllegalArgumentException {
/*  76 */     if (Math.abs(scale) <= 1.0D || scale == Double.POSITIVE_INFINITY)
/*  79 */       return new RepresentativeFractionImpl(Math.round(1.0D / scale)); 
/*  81 */     throw new IllegalArgumentException(Errors.format(58, "scale", Double.valueOf(scale)));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double toScale() {
/*  90 */     return doubleValue();
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/*  99 */     return 1.0D / this.denominator;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 106 */     return 1.0F / (float)this.denominator;
/*     */   }
/*     */   
/*     */   public long longValue() throws ArithmeticException {
/* 116 */     return intValue();
/*     */   }
/*     */   
/*     */   public int intValue() throws ArithmeticException {
/* 129 */     if (this.denominator == 1L)
/* 130 */       return 1; 
/* 131 */     if (this.denominator != 0L)
/* 132 */       return 0; 
/* 134 */     throw new ArithmeticException();
/*     */   }
/*     */   
/*     */   public long getDenominator() {
/* 142 */     return this.denominator;
/*     */   }
/*     */   
/*     */   public void setDenominator(long denominator) {
/* 151 */     this.denominator = denominator;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 169 */     if (object instanceof RepresentativeFraction) {
/* 170 */       RepresentativeFraction that = (RepresentativeFraction)object;
/* 171 */       return (this.denominator == that.getDenominator());
/*     */     } 
/* 173 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 181 */     return (int)this.denominator;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\identification\RepresentativeFractionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */