/*     */ package javax.measure.unit;
/*     */ 
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Quantity;
/*     */ 
/*     */ public final class TransformedUnit<Q extends Quantity> extends DerivedUnit<Q> {
/*     */   private final Unit<Q> _parentUnit;
/*     */   
/*     */   private final UnitConverter _toParentUnit;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   TransformedUnit(Unit<Q> parentUnit, UnitConverter toParentUnit) {
/*  62 */     if (toParentUnit == UnitConverter.IDENTITY)
/*  63 */       throw new IllegalArgumentException("Identity not allowed"); 
/*  64 */     this._parentUnit = parentUnit;
/*  65 */     this._toParentUnit = toParentUnit;
/*     */   }
/*     */   
/*     */   public Unit<Q> getParentUnit() {
/*  75 */     return this._parentUnit;
/*     */   }
/*     */   
/*     */   public UnitConverter toParentUnit() {
/*  84 */     return this._toParentUnit;
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  97 */     if (this == that)
/*  97 */       return true; 
/*  98 */     if (!(that instanceof TransformedUnit))
/*  98 */       return false; 
/*  99 */     TransformedUnit<?> thatUnit = (TransformedUnit)that;
/* 100 */     return (this._parentUnit.equals(thatUnit._parentUnit) && this._toParentUnit.equals(thatUnit._toParentUnit));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 106 */     return this._parentUnit.hashCode() + this._toParentUnit.hashCode();
/*     */   }
/*     */   
/*     */   public Unit<? super Q> getStandardUnit() {
/* 111 */     return this._parentUnit.getStandardUnit();
/*     */   }
/*     */   
/*     */   public UnitConverter toStandardUnit() {
/* 116 */     return this._parentUnit.toStandardUnit().concatenate(this._toParentUnit);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\TransformedUnit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */