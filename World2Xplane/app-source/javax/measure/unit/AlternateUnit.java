/*     */ package javax.measure.unit;
/*     */ 
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Quantity;
/*     */ 
/*     */ public final class AlternateUnit<Q extends Quantity> extends DerivedUnit<Q> {
/*     */   private final String _symbol;
/*     */   
/*     */   private final Unit<?> _parent;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   AlternateUnit(String symbol, Unit<?> parent) {
/*  49 */     if (!parent.isStandardUnit())
/*  50 */       throw new UnsupportedOperationException(this + " is not a standard unit"); 
/*  52 */     this._symbol = symbol;
/*  53 */     this._parent = parent;
/*  55 */     synchronized (Unit.SYMBOL_TO_UNIT) {
/*  56 */       Unit<?> unit = Unit.SYMBOL_TO_UNIT.get(symbol);
/*  57 */       if (unit == null) {
/*  58 */         Unit.SYMBOL_TO_UNIT.put(symbol, this);
/*     */         return;
/*     */       } 
/*  61 */       if (unit instanceof AlternateUnit) {
/*  62 */         AlternateUnit<?> existingUnit = (AlternateUnit)unit;
/*  63 */         if (symbol.equals(existingUnit._symbol) && this._parent.equals(existingUnit._parent))
/*     */           return; 
/*     */       } 
/*  67 */       throw new IllegalArgumentException("Symbol " + symbol + " is associated to a different unit");
/*     */     } 
/*     */   }
/*     */   
/*     */   public final String getSymbol() {
/*  78 */     return this._symbol;
/*     */   }
/*     */   
/*     */   public final Unit<? super Q> getParent() {
/*  89 */     return (Unit)this._parent;
/*     */   }
/*     */   
/*     */   public final Unit<? super Q> getStandardUnit() {
/*  94 */     return this;
/*     */   }
/*     */   
/*     */   public final UnitConverter toStandardUnit() {
/*  99 */     return UnitConverter.IDENTITY;
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/* 112 */     if (this == that)
/* 113 */       return true; 
/* 114 */     if (!(that instanceof AlternateUnit))
/* 115 */       return false; 
/* 116 */     AlternateUnit<?> thatUnit = (AlternateUnit)that;
/* 117 */     return this._symbol.equals(thatUnit._symbol);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 122 */     return this._symbol.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\AlternateUnit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */