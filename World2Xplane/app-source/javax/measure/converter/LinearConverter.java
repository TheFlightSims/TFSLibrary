/*     */ package javax.measure.converter;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ 
/*     */ public abstract class LinearConverter extends UnitConverter {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public abstract LinearConverter inverse();
/*     */   
/*     */   public UnitConverter concatenate(UnitConverter converter) {
/*  53 */     if (converter == IDENTITY)
/*  54 */       return this; 
/*  55 */     if (!(converter instanceof LinearConverter))
/*  56 */       return super.concatenate(converter); 
/*  58 */     return new CompoundLinear(this, (LinearConverter)converter);
/*     */   }
/*     */   
/*     */   private static class CompoundLinear extends LinearConverter {
/*     */     private final LinearConverter _first;
/*     */     
/*     */     private final LinearConverter _second;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private CompoundLinear(LinearConverter first, LinearConverter second) {
/*  87 */       this._first = first;
/*  88 */       this._second = second;
/*     */     }
/*     */     
/*     */     public LinearConverter inverse() {
/*  93 */       return new CompoundLinear(this._second.inverse(), this._first.inverse());
/*     */     }
/*     */     
/*     */     public double convert(double value) {
/*  98 */       return this._second.convert(this._first.convert(value));
/*     */     }
/*     */     
/*     */     public BigDecimal convert(BigDecimal value, MathContext ctx) {
/* 103 */       return this._second.convert(this._first.convert(value, ctx), ctx);
/*     */     }
/*     */     
/*     */     public boolean equals(Object cvtr) {
/* 108 */       if (this == cvtr)
/* 109 */         return true; 
/* 110 */       if (!(cvtr instanceof CompoundLinear))
/* 111 */         return false; 
/* 112 */       CompoundLinear that = (CompoundLinear)cvtr;
/* 113 */       return (this._first.equals(that._first) && this._second.equals(that._second));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 119 */       return this._first.hashCode() + this._second.hashCode();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\converter\LinearConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */