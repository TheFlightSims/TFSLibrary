/*     */ package javax.measure;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import javax.measure.quantity.Quantity;
/*     */ import javax.measure.unit.Unit;
/*     */ 
/*     */ public abstract class Measure<V, Q extends Quantity> implements Measurable<Q>, Serializable {
/*     */   public static <Q extends Quantity> Measure<Double, Q> valueOf(double doubleValue, Unit<Q> unit) {
/*  48 */     return new Double<Q>(doubleValue, unit);
/*     */   }
/*     */   
/*     */   public static <Q extends Quantity> Measure<Long, Q> valueOf(long longValue, Unit<Q> unit) {
/*  60 */     return new Long<Q>(longValue, unit);
/*     */   }
/*     */   
/*     */   public static <Q extends Quantity> Measure<Float, Q> valueOf(float floatValue, Unit<Q> unit) {
/*  72 */     return new Float<Q>(floatValue, unit);
/*     */   }
/*     */   
/*     */   public static <Q extends Quantity> Measure<Integer, Q> valueOf(int intValue, Unit<Q> unit) {
/*  84 */     return new Integer<Q>(intValue, unit);
/*     */   }
/*     */   
/*     */   public long longValue(Unit<Q> unit) throws ArithmeticException {
/* 138 */     double doubleValue = doubleValue(unit);
/* 139 */     if (Double.isNaN(doubleValue) || doubleValue < -9.223372036854776E18D || doubleValue > 9.223372036854776E18D)
/* 142 */       throw new ArithmeticException(doubleValue + " " + unit + " cannot be represented as long"); 
/* 144 */     return Math.round(doubleValue);
/*     */   }
/*     */   
/*     */   public float floatValue(Unit<Q> unit) {
/* 158 */     return (float)doubleValue(unit);
/*     */   }
/*     */   
/*     */   public int intValue(Unit<Q> unit) {
/* 176 */     long longValue = longValue(unit);
/* 177 */     if (longValue > 2147483647L || longValue < -2147483648L)
/* 179 */       throw new ArithmeticException("Overflow"); 
/* 180 */     return (int)longValue;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 195 */     if (!(obj instanceof Measure))
/* 196 */       return false; 
/* 197 */     Measure that = (Measure)obj;
/* 198 */     return (getUnit().equals(that.getUnit()) && getValue().equals(that.getValue()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 208 */     return getUnit().hashCode() + getValue().hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 223 */     if (getUnit() instanceof javax.measure.unit.CompoundUnit)
/* 224 */       return MeasureFormat.DEFAULT.formatCompound(doubleValue(getUnit()), getUnit(), new StringBuffer(), null).toString(); 
/* 226 */     return (new StringBuilder()).append(getValue()).append(" ").append(getUnit()).toString();
/*     */   }
/*     */   
/*     */   public int compareTo(Measurable<Q> that) {
/* 242 */     return Double.compare(doubleValue(getUnit()), that.doubleValue(getUnit()));
/*     */   }
/*     */   
/*     */   private static final class Double<Q extends Quantity> extends Measure<Double, Q> {
/*     */     private final double _value;
/*     */     
/*     */     private final Unit<Q> _unit;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Double(double value, Unit<Q> unit) {
/* 257 */       this._value = value;
/* 258 */       this._unit = unit;
/*     */     }
/*     */     
/*     */     public Unit<Q> getUnit() {
/* 263 */       return this._unit;
/*     */     }
/*     */     
/*     */     public Double getValue() {
/* 268 */       return Double.valueOf(this._value);
/*     */     }
/*     */     
/*     */     public Measure<Double, Q> to(Unit<Q> unit) {
/* 273 */       if (unit == this._unit || unit.equals(this._unit))
/* 274 */         return this; 
/* 275 */       return new Double(doubleValue(unit), unit);
/*     */     }
/*     */     
/*     */     public double doubleValue(Unit<Q> unit) {
/* 279 */       if (unit == this._unit || unit.equals(this._unit))
/* 280 */         return this._value; 
/* 281 */       return this._unit.getConverterTo(unit).convert(this._value);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Long<Q extends Quantity> extends Measure<Long, Q> {
/*     */     private final long _value;
/*     */     
/*     */     private final Unit<Q> _unit;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Long(long value, Unit<Q> unit) {
/* 298 */       this._value = value;
/* 299 */       this._unit = unit;
/*     */     }
/*     */     
/*     */     public Unit<Q> getUnit() {
/* 304 */       return this._unit;
/*     */     }
/*     */     
/*     */     public Long getValue() {
/* 309 */       return Long.valueOf(this._value);
/*     */     }
/*     */     
/*     */     public Measure<Long, Q> to(Unit<Q> unit) {
/* 314 */       if (unit == this._unit || unit.equals(this._unit))
/* 315 */         return this; 
/* 316 */       return new Long(longValue(unit), unit);
/*     */     }
/*     */     
/*     */     public double doubleValue(Unit<Q> unit) {
/* 320 */       if (unit == this._unit || unit.equals(this._unit))
/* 321 */         return this._value; 
/* 322 */       return this._unit.getConverterTo(unit).convert(this._value);
/*     */     }
/*     */     
/*     */     public long longValue(Unit<Q> unit) throws ArithmeticException {
/* 326 */       if (unit == this._unit || unit.equals(this._unit))
/* 327 */         return this._value; 
/* 328 */       return super.longValue(unit);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Float<Q extends Quantity> extends Measure<Float, Q> {
/*     */     private final float _value;
/*     */     
/*     */     private final Unit<Q> _unit;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Float(float value, Unit<Q> unit) {
/* 346 */       this._value = value;
/* 347 */       this._unit = unit;
/*     */     }
/*     */     
/*     */     public Unit<Q> getUnit() {
/* 352 */       return this._unit;
/*     */     }
/*     */     
/*     */     public Float getValue() {
/* 357 */       return Float.valueOf(this._value);
/*     */     }
/*     */     
/*     */     public Measure<Float, Q> to(Unit<Q> unit) {
/* 362 */       if (unit == this._unit || unit.equals(this._unit))
/* 363 */         return this; 
/* 364 */       return new Float(floatValue(unit), unit);
/*     */     }
/*     */     
/*     */     public double doubleValue(Unit<Q> unit) {
/* 368 */       if (unit == this._unit || unit.equals(this._unit))
/* 369 */         return this._value; 
/* 370 */       return this._unit.getConverterTo(unit).convert(this._value);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Integer<Q extends Quantity> extends Measure<Integer, Q> {
/*     */     private final int _value;
/*     */     
/*     */     private final Unit<Q> _unit;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Integer(int value, Unit<Q> unit) {
/* 387 */       this._value = value;
/* 388 */       this._unit = unit;
/*     */     }
/*     */     
/*     */     public Unit<Q> getUnit() {
/* 393 */       return this._unit;
/*     */     }
/*     */     
/*     */     public Integer getValue() {
/* 398 */       return Integer.valueOf(this._value);
/*     */     }
/*     */     
/*     */     public Measure<Integer, Q> to(Unit<Q> unit) {
/* 403 */       if (unit == this._unit || unit.equals(this._unit))
/* 404 */         return this; 
/* 405 */       return new Integer(intValue(unit), unit);
/*     */     }
/*     */     
/*     */     public double doubleValue(Unit<Q> unit) {
/* 409 */       if (unit == this._unit || unit.equals(this._unit))
/* 410 */         return this._value; 
/* 411 */       return this._unit.getConverterTo(unit).convert(this._value);
/*     */     }
/*     */     
/*     */     public long longValue(Unit<Q> unit) throws ArithmeticException {
/* 415 */       if (unit == this._unit || unit.equals(this._unit))
/* 416 */         return this._value; 
/* 417 */       return super.longValue(unit);
/*     */     }
/*     */   }
/*     */   
/*     */   public static <Q extends Quantity> Measure<BigDecimal, Q> valueOf(BigDecimal decimal, Unit<Q> unit) {
/* 429 */     return DecimalMeasure.valueOf(decimal, unit);
/*     */   }
/*     */   
/*     */   public static <Q extends Quantity> Measure<BigDecimal, Q> valueOf(BigDecimal decimal, Unit<Q> unit, MathContext mathContext) {
/* 439 */     return DecimalMeasure.valueOf(decimal, unit);
/*     */   }
/*     */   
/*     */   public static <Q extends Quantity> Measure<double[], Q> valueOf(double[] components, Unit<Q> unit) {
/* 447 */     return VectorMeasure.valueOf(components, unit);
/*     */   }
/*     */   
/*     */   public abstract V getValue();
/*     */   
/*     */   public abstract Unit<Q> getUnit();
/*     */   
/*     */   public abstract Measure<V, Q> to(Unit<Q> paramUnit);
/*     */   
/*     */   public abstract double doubleValue(Unit<Q> paramUnit);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\Measure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */