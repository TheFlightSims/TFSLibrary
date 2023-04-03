/*    */ package javax.measure.converter;
/*    */ 
/*    */ public final class MultiplyConverter extends UnitConverter {
/*    */   private final double _factor;
/*    */   
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public MultiplyConverter(double factor) {
/* 35 */     if ((float)factor == 1.0D)
/* 36 */       throw new IllegalArgumentException("Identity converter not allowed"); 
/* 37 */     this._factor = factor;
/*    */   }
/*    */   
/*    */   public double getFactor() {
/* 46 */     return this._factor;
/*    */   }
/*    */   
/*    */   public UnitConverter inverse() {
/* 51 */     return new MultiplyConverter(1.0D / this._factor);
/*    */   }
/*    */   
/*    */   public double convert(double amount) {
/* 56 */     return this._factor * amount;
/*    */   }
/*    */   
/*    */   public boolean isLinear() {
/* 61 */     return true;
/*    */   }
/*    */   
/*    */   public UnitConverter concatenate(UnitConverter converter) {
/* 66 */     if (converter instanceof MultiplyConverter) {
/* 67 */       double factor = this._factor * ((MultiplyConverter)converter)._factor;
/* 68 */       return valueOf(factor);
/*    */     } 
/* 69 */     if (converter instanceof RationalConverter) {
/* 70 */       double factor = this._factor * ((RationalConverter)converter).getDividend() / ((RationalConverter)converter).getDivisor();
/* 73 */       return valueOf(factor);
/*    */     } 
/* 75 */     return super.concatenate(converter);
/*    */   }
/*    */   
/*    */   private static UnitConverter valueOf(double factor) {
/* 80 */     float asFloat = (float)factor;
/* 81 */     return (asFloat == 1.0F) ? UnitConverter.IDENTITY : new MultiplyConverter(factor);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\converter\MultiplyConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */