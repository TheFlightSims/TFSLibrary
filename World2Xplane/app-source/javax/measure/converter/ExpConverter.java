/*     */ package javax.measure.converter;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ 
/*     */ public final class ExpConverter extends UnitConverter {
/*     */   private final double _base;
/*     */   
/*     */   private final double _logBase;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public ExpConverter(double base) {
/*  61 */     this._base = base;
/*  62 */     this._logBase = Math.log(base);
/*     */   }
/*     */   
/*     */   public double getBase() {
/*  72 */     return this._base;
/*     */   }
/*     */   
/*     */   public UnitConverter inverse() {
/*  77 */     return new LogConverter(this._base);
/*     */   }
/*     */   
/*     */   public final String toString() {
/*  82 */     return "ExpConverter(" + this._base + ")";
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  87 */     if (!(obj instanceof ExpConverter))
/*  88 */       return false; 
/*  89 */     ExpConverter that = (ExpConverter)obj;
/*  90 */     return (this._base == that._base);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  95 */     long bits = Double.doubleToLongBits(this._base);
/*  96 */     return (int)(bits ^ bits >>> 32L);
/*     */   }
/*     */   
/*     */   public double convert(double amount) {
/* 101 */     return Math.exp(this._logBase * amount);
/*     */   }
/*     */   
/*     */   public BigDecimal convert(BigDecimal value, MathContext ctx) throws ArithmeticException {
/* 106 */     return BigDecimal.valueOf(convert(value.doubleValue()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\converter\ExpConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */