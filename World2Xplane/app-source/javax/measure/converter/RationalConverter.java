/*     */ package javax.measure.converter;
/*     */ 
/*     */ public final class RationalConverter extends UnitConverter {
/*     */   private final long _dividend;
/*     */   
/*     */   private final long _divisor;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public RationalConverter(long dividend, long divisor) {
/*  43 */     if (divisor < 0L)
/*  44 */       throw new IllegalArgumentException("Negative divisor"); 
/*  45 */     if (dividend == divisor)
/*  46 */       throw new IllegalArgumentException("Identity converter not allowed"); 
/*  47 */     this._dividend = dividend;
/*  48 */     this._divisor = divisor;
/*     */   }
/*     */   
/*     */   public long getDividend() {
/*  57 */     return this._dividend;
/*     */   }
/*     */   
/*     */   public long getDivisor() {
/*  66 */     return this._divisor;
/*     */   }
/*     */   
/*     */   public UnitConverter inverse() {
/*  71 */     return (this._dividend < 0L) ? new RationalConverter(-this._divisor, -this._dividend) : new RationalConverter(this._divisor, this._dividend);
/*     */   }
/*     */   
/*     */   public double convert(double amount) {
/*  77 */     return amount * this._dividend / this._divisor;
/*     */   }
/*     */   
/*     */   public boolean isLinear() {
/*  82 */     return true;
/*     */   }
/*     */   
/*     */   public UnitConverter concatenate(UnitConverter converter) {
/*  87 */     if (converter instanceof RationalConverter) {
/*  88 */       RationalConverter that = (RationalConverter)converter;
/*  89 */       long dividendLong = this._dividend * that._dividend;
/*  90 */       long divisorLong = this._divisor * that._divisor;
/*  91 */       double dividendDouble = this._dividend * that._dividend;
/*  92 */       double divisorDouble = this._divisor * that._divisor;
/*  93 */       if (dividendLong != dividendDouble || divisorLong != divisorDouble)
/*  95 */         return new MultiplyConverter(dividendDouble / divisorDouble); 
/*  97 */       long gcd = gcd(dividendLong, divisorLong);
/*  98 */       return valueOf(dividendLong / gcd, divisorLong / gcd);
/*     */     } 
/*  99 */     if (converter instanceof MultiplyConverter)
/* 100 */       return converter.concatenate(this); 
/* 102 */     return super.concatenate(converter);
/*     */   }
/*     */   
/*     */   private static UnitConverter valueOf(long dividend, long divisor) {
/* 107 */     return (dividend == 1L && divisor == 1L) ? UnitConverter.IDENTITY : new RationalConverter(dividend, divisor);
/*     */   }
/*     */   
/*     */   private static long gcd(long m, long n) {
/* 119 */     if (n == 0L)
/* 120 */       return m; 
/* 122 */     return gcd(n, m % n);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\converter\RationalConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */