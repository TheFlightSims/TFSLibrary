/*     */ package javax.measure.unit;
/*     */ 
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Quantity;
/*     */ 
/*     */ public class AnnotatedUnit<Q extends Quantity> extends DerivedUnit<Q> {
/*     */   private final String _annotation;
/*     */   
/*     */   private final Unit<Q> _realUnit;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public AnnotatedUnit(Unit<Q> realUnit, String annotation) {
/*  74 */     this._realUnit = (realUnit instanceof AnnotatedUnit) ? ((AnnotatedUnit<Q>)realUnit).getRealUnit() : realUnit;
/*  76 */     this._annotation = annotation;
/*     */   }
/*     */   
/*     */   public String getAnnotation() {
/*  85 */     return this._annotation;
/*     */   }
/*     */   
/*     */   public final Unit<Q> getRealUnit() {
/*  94 */     return this._realUnit;
/*     */   }
/*     */   
/*     */   public final Unit<Q> toSI() {
/*  99 */     return this._realUnit.toSI();
/*     */   }
/*     */   
/*     */   public final UnitConverter getConverterTo(Unit<Q> unit) {
/* 104 */     return this._realUnit.getConverterTo(unit);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/* 109 */     if (this == that)
/* 110 */       return true; 
/* 111 */     if (!(that instanceof AnnotatedUnit))
/* 112 */       return false; 
/* 113 */     AnnotatedUnit<?> thatUnit = (AnnotatedUnit)that;
/* 114 */     return (this._realUnit.equals(thatUnit._realUnit) && this._annotation.equals(thatUnit._annotation));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 120 */     return this._realUnit.hashCode() + this._annotation.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\AnnotatedUnit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */