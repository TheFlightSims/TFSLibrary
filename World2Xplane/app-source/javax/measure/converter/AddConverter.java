/*    */ package javax.measure.converter;
/*    */ 
/*    */ public final class AddConverter extends UnitConverter {
/*    */   private final double _offset;
/*    */   
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public AddConverter(double offset) {
/* 35 */     if ((float)offset == 0.0D)
/* 36 */       throw new IllegalArgumentException("Identity converter not allowed"); 
/* 37 */     this._offset = offset;
/*    */   }
/*    */   
/*    */   public double getOffset() {
/* 46 */     return this._offset;
/*    */   }
/*    */   
/*    */   public UnitConverter inverse() {
/* 51 */     return new AddConverter(-this._offset);
/*    */   }
/*    */   
/*    */   public double convert(double amount) {
/* 56 */     return amount + this._offset;
/*    */   }
/*    */   
/*    */   public boolean isLinear() {
/* 61 */     return false;
/*    */   }
/*    */   
/*    */   public UnitConverter concatenate(UnitConverter converter) {
/* 66 */     if (converter instanceof AddConverter) {
/* 67 */       double offset = this._offset + ((AddConverter)converter)._offset;
/* 68 */       return valueOf(offset);
/*    */     } 
/* 70 */     return super.concatenate(converter);
/*    */   }
/*    */   
/*    */   private static UnitConverter valueOf(double offset) {
/* 75 */     float asFloat = (float)offset;
/* 76 */     return (asFloat == 0.0F) ? UnitConverter.IDENTITY : new AddConverter(offset);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\converter\AddConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */