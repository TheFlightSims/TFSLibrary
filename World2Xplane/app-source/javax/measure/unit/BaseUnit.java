/*     */ package javax.measure.unit;
/*     */ 
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Quantity;
/*     */ 
/*     */ public class BaseUnit<Q extends Quantity> extends Unit<Q> {
/*     */   private final String _symbol;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public BaseUnit(String symbol) {
/*  55 */     this._symbol = symbol;
/*  57 */     synchronized (Unit.SYMBOL_TO_UNIT) {
/*  58 */       Unit<?> unit = Unit.SYMBOL_TO_UNIT.get(symbol);
/*  59 */       if (unit == null) {
/*  60 */         Unit.SYMBOL_TO_UNIT.put(symbol, this);
/*     */         return;
/*     */       } 
/*  63 */       if (!(unit instanceof BaseUnit))
/*  64 */         throw new IllegalArgumentException("Symbol " + symbol + " is associated to a different unit"); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final String getSymbol() {
/*  75 */     return this._symbol;
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  88 */     if (this == that)
/*  89 */       return true; 
/*  90 */     if (!(that instanceof BaseUnit))
/*  91 */       return false; 
/*  92 */     BaseUnit<?> thatUnit = (BaseUnit)that;
/*  93 */     return this._symbol.equals(thatUnit._symbol);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  98 */     return this._symbol.hashCode();
/*     */   }
/*     */   
/*     */   public Unit<? super Q> getStandardUnit() {
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   public UnitConverter toStandardUnit() {
/* 108 */     return UnitConverter.IDENTITY;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\BaseUnit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */