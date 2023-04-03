/*     */ package org.geotools.util;
/*     */ 
/*     */ import javax.measure.converter.ConversionException;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.resources.ClassChanger;
/*     */ 
/*     */ public class MeasurementRange<T extends Number & Comparable<? super T>> extends NumberRange<T> {
/*     */   private static final long serialVersionUID = 3980319420337513745L;
/*     */   
/*     */   private final Unit<?> units;
/*     */   
/*     */   public static MeasurementRange<Float> create(float minimum, float maximum, Unit<?> units) {
/*  61 */     return create(minimum, true, maximum, true, units);
/*     */   }
/*     */   
/*     */   public static MeasurementRange<Float> create(float minimum, boolean isMinIncluded, float maximum, boolean isMaxIncluded, Unit<?> units) {
/*  79 */     return new MeasurementRange<Float>(Float.class, Float.valueOf(minimum), isMinIncluded, Float.valueOf(maximum), isMaxIncluded, units);
/*     */   }
/*     */   
/*     */   public static MeasurementRange<Double> create(double minimum, double maximum, Unit<?> units) {
/*  92 */     return create(minimum, true, maximum, true, units);
/*     */   }
/*     */   
/*     */   public static MeasurementRange<Double> create(double minimum, boolean isMinIncluded, double maximum, boolean isMaxIncluded, Unit<?> units) {
/* 108 */     return new MeasurementRange<Double>(Double.class, Double.valueOf(minimum), isMinIncluded, Double.valueOf(maximum), isMaxIncluded, units);
/*     */   }
/*     */   
/*     */   public MeasurementRange(Class<T> type, T minimum, boolean isMinIncluded, T maximum, boolean isMaxIncluded, Unit<?> units) {
/* 128 */     super(type, minimum, isMinIncluded, maximum, isMaxIncluded);
/* 129 */     this.units = units;
/*     */   }
/*     */   
/*     */   public MeasurementRange(Range<T> range, Unit<?> units) {
/* 139 */     super(range);
/* 140 */     this.units = units;
/*     */   }
/*     */   
/*     */   private MeasurementRange(Class<T> type, Range<? extends Number> range, Unit<?> units) {
/* 154 */     super(type, range);
/* 155 */     this.units = units;
/*     */   }
/*     */   
/*     */   MeasurementRange<T> create(T minValue, boolean isMinIncluded, T maxValue, boolean isMaxIncluded) {
/* 165 */     return new MeasurementRange(this.elementClass, minValue, isMinIncluded, maxValue, isMaxIncluded, this.units);
/*     */   }
/*     */   
/*     */   public Unit<?> getUnits() {
/* 175 */     return this.units;
/*     */   }
/*     */   
/*     */   public MeasurementRange convertTo(Unit<?> targetUnits) throws ConversionException {
/* 189 */     return convertAndCast(this.elementClass, targetUnits);
/*     */   }
/*     */   
/*     */   public <N extends Number & Comparable<? super N>> MeasurementRange<N> castTo(Class<N> type) {
/* 202 */     return (MeasurementRange<N>)damnJava5(this, type);
/*     */   }
/*     */   
/*     */   <N extends Number & Comparable<? super N>> MeasurementRange<N> convertAndCast(Range<? extends Number> range, Class<N> type) throws IllegalArgumentException {
/* 219 */     if (range instanceof MeasurementRange) {
/* 220 */       MeasurementRange<?> casted = (MeasurementRange)range;
/* 221 */       return casted.convertAndCast(type, this.units);
/*     */     } 
/* 224 */     return new MeasurementRange(type, range, this.units);
/*     */   }
/*     */   
/*     */   private <N extends Number & Comparable<? super N>> MeasurementRange<N> convertAndCast(Class<N> type, Unit<?> targetUnits) throws ConversionException {
/* 240 */     if (targetUnits == null || targetUnits.equals(this.units)) {
/* 241 */       if (type.equals(this.elementClass)) {
/* 243 */         MeasurementRange<N> result = this;
/* 244 */         return result;
/*     */       } 
/* 246 */       return new MeasurementRange(type, this, this.units);
/*     */     } 
/* 249 */     if (this.units == null)
/* 250 */       return new MeasurementRange(type, this, targetUnits); 
/* 252 */     UnitConverter converter = this.units.getConverterTo(targetUnits);
/* 253 */     if (converter.equals(UnitConverter.IDENTITY))
/* 254 */       return new MeasurementRange(type, this, targetUnits); 
/* 256 */     boolean isMinIncluded = isMinIncluded();
/* 257 */     boolean isMaxIncluded = isMaxIncluded();
/* 258 */     Double minimum = Double.valueOf(converter.convert(getMinimum()));
/* 259 */     Double maximum = Double.valueOf(converter.convert(getMaximum()));
/* 260 */     if (minimum.compareTo(maximum) > 0) {
/* 261 */       Double td = minimum;
/* 262 */       minimum = maximum;
/* 263 */       maximum = td;
/* 264 */       boolean tb = isMinIncluded;
/* 265 */       isMinIncluded = isMaxIncluded;
/* 266 */       isMaxIncluded = tb;
/*     */     } 
/* 268 */     return new MeasurementRange(type, (T)ClassChanger.cast(minimum, type), isMinIncluded, (T)ClassChanger.cast(maximum, type), isMaxIncluded, targetUnits);
/*     */   }
/*     */   
/*     */   MeasurementRange<T>[] newArray(int length) {
/* 279 */     return (MeasurementRange<T>[])new MeasurementRange[length];
/*     */   }
/*     */   
/*     */   public MeasurementRange union(Range<?> range) {
/* 287 */     return (MeasurementRange)super.union(range);
/*     */   }
/*     */   
/*     */   public MeasurementRange intersect(Range<?> range) {
/* 297 */     return (MeasurementRange)super.intersect(range);
/*     */   }
/*     */   
/*     */   public MeasurementRange[] subtract(Range<?> range) {
/* 308 */     return (MeasurementRange[])super.subtract(range);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 318 */     if (super.equals(object)) {
/* 319 */       if (object instanceof MeasurementRange) {
/* 320 */         MeasurementRange that = (MeasurementRange)object;
/* 321 */         return Utilities.equals(this.units, that.units);
/*     */       } 
/* 323 */       return true;
/*     */     } 
/* 325 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\MeasurementRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */