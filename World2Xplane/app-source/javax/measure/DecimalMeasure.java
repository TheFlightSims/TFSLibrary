/*     */ package javax.measure;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import javax.measure.converter.AddConverter;
/*     */ import javax.measure.converter.RationalConverter;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Quantity;
/*     */ import javax.measure.unit.Unit;
/*     */ 
/*     */ public class DecimalMeasure<Q extends Quantity> extends Measure<BigDecimal, Q> {
/*     */   private final BigDecimal _value;
/*     */   
/*     */   private final Unit<Q> _unit;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public DecimalMeasure(BigDecimal value, Unit<Q> unit) {
/*  53 */     this._value = value;
/*  54 */     this._unit = unit;
/*     */   }
/*     */   
/*     */   public static <Q extends Quantity> DecimalMeasure<Q> valueOf(BigDecimal decimal, Unit<Q> unit) {
/*  66 */     return new DecimalMeasure<Q>(decimal, unit);
/*     */   }
/*     */   
/*     */   public static <Q extends Quantity> DecimalMeasure<Q> valueOf(CharSequence csq) {
/*  80 */     String str = csq.toString();
/*  81 */     int numberLength = str.length();
/*  82 */     int unitStartIndex = -1;
/*  83 */     for (int i = 0; i < str.length(); i++) {
/*  84 */       if (Character.isWhitespace(str.charAt(i))) {
/*  85 */         for (int j = i + 1; j < str.length(); j++) {
/*  86 */           if (!Character.isWhitespace(str.charAt(j))) {
/*  87 */             unitStartIndex = j;
/*     */             break;
/*     */           } 
/*     */         } 
/*  91 */         numberLength = i;
/*     */         break;
/*     */       } 
/*     */     } 
/*  95 */     BigDecimal decimal = new BigDecimal(str.substring(0, numberLength));
/*  96 */     Unit<Q> unit = Unit.ONE;
/*  97 */     if (unitStartIndex > 0)
/*  98 */       unit = Unit.valueOf(str.substring(unitStartIndex)); 
/* 100 */     return new DecimalMeasure<Q>(decimal, unit);
/*     */   }
/*     */   
/*     */   public Unit<Q> getUnit() {
/* 105 */     return this._unit;
/*     */   }
/*     */   
/*     */   public BigDecimal getValue() {
/* 110 */     return this._value;
/*     */   }
/*     */   
/*     */   public DecimalMeasure<Q> to(Unit<Q> unit) {
/* 126 */     return to(unit, null);
/*     */   }
/*     */   
/*     */   public DecimalMeasure<Q> to(Unit<Q> unit, MathContext mathContext) {
/* 144 */     if (unit == this._unit || unit.equals(this._unit))
/* 145 */       return this; 
/* 146 */     UnitConverter cvtr = this._unit.getConverterTo(unit);
/* 147 */     if (cvtr instanceof RationalConverter) {
/* 148 */       RationalConverter factor = (RationalConverter)cvtr;
/* 149 */       BigDecimal dividend = BigDecimal.valueOf(factor.getDividend());
/* 150 */       BigDecimal divisor = BigDecimal.valueOf(factor.getDivisor());
/* 151 */       BigDecimal bigDecimal1 = (mathContext == null) ? this._value.multiply(dividend).divide(divisor) : this._value.multiply(dividend, mathContext).divide(divisor, mathContext);
/* 154 */       return new DecimalMeasure(bigDecimal1, unit);
/*     */     } 
/* 155 */     if (cvtr.isLinear()) {
/* 156 */       BigDecimal factor = BigDecimal.valueOf(cvtr.convert(1.0D));
/* 157 */       BigDecimal bigDecimal1 = (mathContext == null) ? this._value.multiply(factor) : this._value.multiply(factor, mathContext);
/* 159 */       return new DecimalMeasure(bigDecimal1, unit);
/*     */     } 
/* 160 */     if (cvtr instanceof AddConverter) {
/* 161 */       BigDecimal offset = BigDecimal.valueOf(((AddConverter)cvtr).getOffset());
/* 162 */       BigDecimal bigDecimal1 = (mathContext == null) ? this._value.add(offset) : this._value.add(offset, mathContext);
/* 164 */       return new DecimalMeasure(bigDecimal1, unit);
/*     */     } 
/* 166 */     BigDecimal result = BigDecimal.valueOf(cvtr.convert(this._value.doubleValue()));
/* 167 */     return new DecimalMeasure(result, unit);
/*     */   }
/*     */   
/*     */   public double doubleValue(Unit<Q> unit) {
/* 172 */     if (unit == this._unit || unit.equals(this._unit))
/* 173 */       return this._value.doubleValue(); 
/* 174 */     return this._unit.getConverterTo(unit).convert(this._value.doubleValue());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\DecimalMeasure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */