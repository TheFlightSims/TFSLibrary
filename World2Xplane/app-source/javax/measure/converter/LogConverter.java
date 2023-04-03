/*     */ package javax.measure.converter;
/*     */ 
/*     */ public final class LogConverter extends UnitConverter {
/*     */   private final double _base;
/*     */   
/*     */   private final double _logBase;
/*     */   
/*     */   private final double _invLogBase;
/*     */   
/*  42 */   private final Inverse _inverse = new Inverse();
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public LogConverter(double base) {
/*  51 */     this._base = base;
/*  52 */     this._logBase = Math.log(base);
/*  53 */     this._invLogBase = 1.0D / this._logBase;
/*     */   }
/*     */   
/*     */   public double getBase() {
/*  63 */     return this._base;
/*     */   }
/*     */   
/*     */   public UnitConverter inverse() {
/*  68 */     return this._inverse;
/*     */   }
/*     */   
/*     */   public double convert(double amount) {
/*  73 */     return this._invLogBase * Math.log(amount);
/*     */   }
/*     */   
/*     */   public boolean isLinear() {
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   private class Inverse extends UnitConverter {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private Inverse() {}
/*     */     
/*     */     public UnitConverter inverse() {
/*  90 */       return LogConverter.this;
/*     */     }
/*     */     
/*     */     public double convert(double amount) {
/*  95 */       return Math.exp(LogConverter.this._logBase * amount);
/*     */     }
/*     */     
/*     */     public boolean isLinear() {
/* 100 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\converter\LogConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */