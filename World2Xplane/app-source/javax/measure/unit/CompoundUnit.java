/*     */ package javax.measure.unit;
/*     */ 
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Quantity;
/*     */ 
/*     */ public final class CompoundUnit<Q extends Quantity> extends DerivedUnit<Q> {
/*     */   private final Unit<Q> _high;
/*     */   
/*     */   private final Unit<Q> _low;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   CompoundUnit(Unit<Q> high, Unit<Q> low) {
/*  48 */     if (!high.getStandardUnit().equals(low.getStandardUnit()))
/*  49 */       throw new IllegalArgumentException("Both units do not have the same system unit"); 
/*  51 */     this._high = high;
/*  52 */     this._low = low;
/*     */   }
/*     */   
/*     */   public Unit<Q> getLower() {
/*  62 */     return this._low;
/*     */   }
/*     */   
/*     */   public Unit<Q> getHigher() {
/*  71 */     return this._high;
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  84 */     if (this == that)
/*  85 */       return true; 
/*  86 */     if (!(that instanceof CompoundUnit))
/*  87 */       return false; 
/*  88 */     CompoundUnit<?> thatUnit = (CompoundUnit)that;
/*  89 */     return (this._high.equals(thatUnit._high) && this._low.equals(thatUnit._low));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  95 */     return this._high.hashCode() ^ this._low.hashCode();
/*     */   }
/*     */   
/*     */   public Unit<? super Q> getStandardUnit() {
/* 100 */     return this._low.getStandardUnit();
/*     */   }
/*     */   
/*     */   public UnitConverter toStandardUnit() {
/* 105 */     return this._low.toStandardUnit();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\CompoundUnit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */