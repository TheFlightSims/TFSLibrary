/*     */ package javax.measure;
/*     */ 
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Quantity;
/*     */ import javax.measure.unit.Unit;
/*     */ 
/*     */ public abstract class VectorMeasure<Q extends Quantity> extends Measure<double[], Q> {
/*     */   public static <Q extends Quantity> VectorMeasure<Q> valueOf(double x, double y, Unit<Q> unit) {
/*  62 */     return new TwoDimensional<Q>(x, y, unit);
/*     */   }
/*     */   
/*     */   public static <Q extends Quantity> VectorMeasure<Q> valueOf(double x, double y, double z, Unit<Q> unit) {
/*  75 */     return new ThreeDimensional<Q>(x, y, z, unit);
/*     */   }
/*     */   
/*     */   public static <Q extends Quantity> VectorMeasure<Q> valueOf(double[] components, Unit<Q> unit) {
/*  86 */     return new MultiDimensional<Q>(components, unit);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 114 */     double[] values = getValue();
/* 115 */     Unit<Q> unit = getUnit();
/* 116 */     StringBuffer tmp = new StringBuffer();
/* 117 */     tmp.append('[');
/* 118 */     for (double v : values) {
/* 119 */       if (tmp.length() > 1)
/* 120 */         tmp.append(", "); 
/* 122 */       if (unit instanceof javax.measure.unit.CompoundUnit) {
/* 123 */         MeasureFormat.DEFAULT.formatCompound(v, unit, tmp, null);
/*     */       } else {
/* 125 */         tmp.append(v).append(" ").append(unit);
/*     */       } 
/*     */     } 
/* 128 */     tmp.append("] ");
/* 129 */     return tmp.toString();
/*     */   }
/*     */   
/*     */   public abstract VectorMeasure<Q> to(Unit<Q> paramUnit);
/*     */   
/*     */   public abstract double doubleValue(Unit<Q> paramUnit);
/*     */   
/*     */   private static class TwoDimensional<Q extends Quantity> extends VectorMeasure<Q> {
/*     */     private final double _x;
/*     */     
/*     */     private final double _y;
/*     */     
/*     */     private final Unit<Q> _unit;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private TwoDimensional(double x, double y, Unit<Q> unit) {
/* 142 */       this._x = x;
/* 143 */       this._y = y;
/* 144 */       this._unit = unit;
/*     */     }
/*     */     
/*     */     public double doubleValue(Unit<Q> unit) {
/* 149 */       double norm = Math.sqrt(this._x * this._x + this._y * this._y);
/* 150 */       if (unit == this._unit || unit.equals(this._unit))
/* 151 */         return norm; 
/* 152 */       return this._unit.getConverterTo(unit).convert(norm);
/*     */     }
/*     */     
/*     */     public Unit<Q> getUnit() {
/* 157 */       return this._unit;
/*     */     }
/*     */     
/*     */     public double[] getValue() {
/* 162 */       return new double[] { this._x, this._y };
/*     */     }
/*     */     
/*     */     public TwoDimensional<Q> to(Unit<Q> unit) {
/* 167 */       if (unit == this._unit || unit.equals(this._unit))
/* 168 */         return this; 
/* 169 */       UnitConverter cvtr = this._unit.getConverterTo(unit);
/* 170 */       return new TwoDimensional(cvtr.convert(this._x), cvtr.convert(this._y), unit);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class ThreeDimensional<Q extends Quantity> extends VectorMeasure<Q> {
/*     */     private final double _x;
/*     */     
/*     */     private final double _y;
/*     */     
/*     */     private final double _z;
/*     */     
/*     */     private final Unit<Q> _unit;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private ThreeDimensional(double x, double y, double z, Unit<Q> unit) {
/* 189 */       this._x = x;
/* 190 */       this._y = y;
/* 191 */       this._z = z;
/* 192 */       this._unit = unit;
/*     */     }
/*     */     
/*     */     public double doubleValue(Unit<Q> unit) {
/* 197 */       double norm = Math.sqrt(this._x * this._x + this._y * this._y + this._z * this._z);
/* 198 */       if (unit == this._unit || unit.equals(this._unit))
/* 199 */         return norm; 
/* 200 */       return this._unit.getConverterTo(unit).convert(norm);
/*     */     }
/*     */     
/*     */     public Unit<Q> getUnit() {
/* 205 */       return this._unit;
/*     */     }
/*     */     
/*     */     public double[] getValue() {
/* 210 */       return new double[] { this._x, this._y, this._z };
/*     */     }
/*     */     
/*     */     public ThreeDimensional<Q> to(Unit<Q> unit) {
/* 215 */       if (unit == this._unit || unit.equals(this._unit))
/* 216 */         return this; 
/* 217 */       UnitConverter cvtr = this._unit.getConverterTo(unit);
/* 218 */       return new ThreeDimensional(cvtr.convert(this._x), cvtr.convert(this._y), cvtr.convert(this._z), unit);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class MultiDimensional<Q extends Quantity> extends VectorMeasure<Q> {
/*     */     private final double[] _components;
/*     */     
/*     */     private final Unit<Q> _unit;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private MultiDimensional(double[] components, Unit<Q> unit) {
/* 232 */       this._components = (double[])components.clone();
/* 233 */       this._unit = unit;
/*     */     }
/*     */     
/*     */     public double doubleValue(Unit<Q> unit) {
/* 238 */       double normSquare = this._components[0] * this._components[0];
/* 239 */       for (int i = 1, n = this._components.length; i < n; ) {
/* 240 */         double d = this._components[i++];
/* 241 */         normSquare += d * d;
/*     */       } 
/* 243 */       if (unit == this._unit || unit.equals(this._unit))
/* 244 */         return Math.sqrt(normSquare); 
/* 245 */       return this._unit.getConverterTo(unit).convert(Math.sqrt(normSquare));
/*     */     }
/*     */     
/*     */     public Unit<Q> getUnit() {
/* 250 */       return this._unit;
/*     */     }
/*     */     
/*     */     public double[] getValue() {
/* 255 */       return (double[])this._components.clone();
/*     */     }
/*     */     
/*     */     public MultiDimensional<Q> to(Unit<Q> unit) {
/* 260 */       if (unit == this._unit || unit.equals(this._unit))
/* 261 */         return this; 
/* 262 */       UnitConverter cvtr = this._unit.getConverterTo(unit);
/* 263 */       double[] newValues = new double[this._components.length];
/* 264 */       for (int i = 0; i < this._components.length; i++)
/* 265 */         newValues[i] = cvtr.convert(this._components[i]); 
/* 267 */       return new MultiDimensional(newValues, unit);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\VectorMeasure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */