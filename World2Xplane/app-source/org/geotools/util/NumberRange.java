/*     */ package org.geotools.util;
/*     */ 
/*     */ import org.geotools.resources.ClassChanger;
/*     */ import org.geotools.resources.XMath;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public class NumberRange<T extends Number & Comparable<? super T>> extends Range<T> {
/*     */   private static final long serialVersionUID = -818167965963008231L;
/*     */   
/*     */   public static NumberRange<Byte> create(byte minimum, byte maximum) {
/*  60 */     return create(minimum, true, maximum, true);
/*     */   }
/*     */   
/*     */   public static NumberRange<Byte> create(byte minimum, boolean isMinIncluded, byte maximum, boolean isMaxIncluded) {
/*  76 */     return new NumberRange<Byte>(Byte.class, Byte.valueOf(minimum), isMinIncluded, Byte.valueOf(maximum), isMaxIncluded);
/*     */   }
/*     */   
/*     */   public static NumberRange<Short> create(short minimum, short maximum) {
/*  90 */     return create(minimum, true, maximum, true);
/*     */   }
/*     */   
/*     */   public static NumberRange<Short> create(short minimum, boolean isMinIncluded, short maximum, boolean isMaxIncluded) {
/* 106 */     return new NumberRange<Short>(Short.class, Short.valueOf(minimum), isMinIncluded, Short.valueOf(maximum), isMaxIncluded);
/*     */   }
/*     */   
/*     */   public static NumberRange<Integer> create(int minimum, int maximum) {
/* 120 */     return create(minimum, true, maximum, true);
/*     */   }
/*     */   
/*     */   public static NumberRange<Integer> create(int minimum, boolean isMinIncluded, int maximum, boolean isMaxIncluded) {
/* 136 */     return new NumberRange<Integer>(Integer.class, Integer.valueOf(minimum), isMinIncluded, Integer.valueOf(maximum), isMaxIncluded);
/*     */   }
/*     */   
/*     */   public static NumberRange<Long> create(long minimum, long maximum) {
/* 150 */     return create(minimum, true, maximum, true);
/*     */   }
/*     */   
/*     */   public static NumberRange<Long> create(long minimum, boolean isMinIncluded, long maximum, boolean isMaxIncluded) {
/* 166 */     return new NumberRange<Long>(Long.class, Long.valueOf(minimum), isMinIncluded, Long.valueOf(maximum), isMaxIncluded);
/*     */   }
/*     */   
/*     */   public static NumberRange<Float> create(float minimum, float maximum) {
/* 180 */     return create(minimum, true, maximum, true);
/*     */   }
/*     */   
/*     */   public static NumberRange<Float> create(float minimum, boolean isMinIncluded, float maximum, boolean isMaxIncluded) {
/* 196 */     return new NumberRange<Float>(Float.class, Float.valueOf(minimum), isMinIncluded, Float.valueOf(maximum), isMaxIncluded);
/*     */   }
/*     */   
/*     */   public static NumberRange<Double> create(double minimum, double maximum) {
/* 210 */     return create(minimum, true, maximum, true);
/*     */   }
/*     */   
/*     */   public static NumberRange<Double> create(double minimum, boolean isMinIncluded, double maximum, boolean isMaxIncluded) {
/* 226 */     return new NumberRange<Double>(Double.class, Double.valueOf(minimum), isMinIncluded, Double.valueOf(maximum), isMaxIncluded);
/*     */   }
/*     */   
/*     */   NumberRange(Class<T> type, Comparable<T> minimum, Comparable<T> maximum) throws IllegalArgumentException {
/* 245 */     super(type, (T)minimum, (T)maximum);
/*     */   }
/*     */   
/*     */   public NumberRange(Class<T> type, T minimum, T maximum) {
/* 257 */     super(type, minimum, maximum);
/*     */   }
/*     */   
/*     */   public NumberRange(Class<T> type, T minimum, boolean isMinIncluded, T maximum, boolean isMaxIncluded) {
/* 274 */     super(type, minimum, isMinIncluded, maximum, isMaxIncluded);
/*     */   }
/*     */   
/*     */   NumberRange(Class<T> type, Range<? extends Number> range) throws IllegalArgumentException {
/* 290 */     this(type, (T)ClassChanger.cast(range.getMinValue(), type), range.isMinIncluded(), (T)ClassChanger.cast(range.getMaxValue(), type), range.isMaxIncluded());
/*     */   }
/*     */   
/*     */   public NumberRange(Range<T> range) {
/* 303 */     super(range.getElementClass(), range.getMinValue(), range.isMinIncluded(), range.getMaxValue(), range.isMaxIncluded());
/*     */   }
/*     */   
/*     */   NumberRange<T> create(T minValue, boolean isMinIncluded, T maxValue, boolean isMaxIncluded) {
/* 316 */     return new NumberRange(this.elementClass, minValue, isMinIncluded, maxValue, isMaxIncluded);
/*     */   }
/*     */   
/*     */   void checkElementClass() throws IllegalArgumentException {
/* 325 */     ensureNumberClass(this.elementClass);
/*     */   }
/*     */   
/*     */   private static Class<? extends Number> getElementClass(Range<?> range) throws IllegalArgumentException {
/* 334 */     ensureNonNull("range", range);
/* 335 */     Class<?> type = range.elementClass;
/* 336 */     ensureNumberClass(type);
/* 343 */     Class<? extends Number> result = (Class)type;
/* 344 */     return result;
/*     */   }
/*     */   
/*     */   private static void ensureNumberClass(Class<?> type) throws IllegalArgumentException {
/* 351 */     if (!Number.class.isAssignableFrom(type))
/* 352 */       throw new IllegalArgumentException(Errors.format(61, type, Number.class)); 
/*     */   }
/*     */   
/*     */   public static <N extends Number & Comparable<? super N>> NumberRange<N> wrap(Range<N> range) {
/* 368 */     if (range instanceof NumberRange) {
/* 370 */       NumberRange<N> cast = (NumberRange<N>)range;
/* 371 */       return cast;
/*     */     } 
/* 374 */     return new NumberRange<N>(range);
/*     */   }
/*     */   
/*     */   <N extends Number & Comparable<? super N>> NumberRange<N> convertAndCast(Range<? extends Number> range, Class<N> type) throws IllegalArgumentException {
/* 392 */     if (type.equals(range.getElementClass())) {
/* 394 */       NumberRange<N> cast = wrap((Range)range);
/* 395 */       return cast;
/*     */     } 
/* 398 */     return new NumberRange(type, range);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   final NumberRange damnJava5(Range<? extends Number> range, Class<Number> type) {
/* 408 */     return convertAndCast(range, type);
/*     */   }
/*     */   
/*     */   public <N extends Number & Comparable<? super N>> NumberRange<N> castTo(Class<N> type) throws IllegalArgumentException {
/* 423 */     return convertAndCast(this, type);
/*     */   }
/*     */   
/*     */   NumberRange<T>[] newArray(int length) {
/* 432 */     return (NumberRange<T>[])new NumberRange[length];
/*     */   }
/*     */   
/*     */   public boolean contains(Number value) throws IllegalArgumentException {
/* 443 */     if (value != null && !(value instanceof Comparable))
/* 444 */       throw new IllegalArgumentException(Errors.format(123, value.getClass())); 
/* 447 */     return contains((Comparable)value);
/*     */   }
/*     */   
/*     */   public boolean contains(Comparable<?> value) throws IllegalArgumentException {
/* 458 */     if (value == null)
/* 459 */       return false; 
/* 461 */     ensureNumberClass(value.getClass());
/* 468 */     Number number = (Number)value;
/* 469 */     Class<? extends Number> type = ClassChanger.getWidestClass(this.elementClass, number.getClass());
/* 470 */     number = ClassChanger.cast(number, type);
/* 477 */     boolean contains = castTo(type).containsNC((Comparable)number);
/* 478 */     return contains;
/*     */   }
/*     */   
/*     */   public boolean contains(Range<?> range) {
/* 487 */     Class<? extends Number> type = ClassChanger.getWidestClass(this.elementClass, getElementClass(range));
/* 494 */     range = convertAndCast((Range)range, type);
/* 495 */     return castTo(type).containsNC(range);
/*     */   }
/*     */   
/*     */   public boolean intersects(Range<?> range) {
/* 504 */     Class<? extends Number> type = ClassChanger.getWidestClass(this.elementClass, getElementClass(range));
/* 511 */     range = convertAndCast((Range)range, type);
/* 512 */     return castTo(type).intersectsNC(range);
/*     */   }
/*     */   
/*     */   public NumberRange<?> union(Range<?> range) {
/* 522 */     Class<? extends Number> type = ClassChanger.getWidestClass(this.elementClass, getElementClass(range));
/* 523 */     range = convertAndCast((Range)range, type);
/* 524 */     return (NumberRange)castTo(type).unionNC(range);
/*     */   }
/*     */   
/*     */   public NumberRange<?> intersect(Range<?> range) {
/* 534 */     Class<? extends Number> rangeType = getElementClass(range);
/* 535 */     Class<? extends Number> type = ClassChanger.getWidestClass(this.elementClass, rangeType);
/* 536 */     range = castTo(type).intersectNC(convertAndCast((Range)range, type));
/* 542 */     type = ClassChanger.getFinestClass(this.elementClass, rangeType);
/* 543 */     if (range.minValue != null)
/* 544 */       type = ClassChanger.getWidestClass(type, ClassChanger.getFinestClass(((Number)range.minValue).doubleValue())); 
/* 546 */     if (range.maxValue != null)
/* 547 */       type = ClassChanger.getWidestClass(type, ClassChanger.getFinestClass(((Number)range.maxValue).doubleValue())); 
/* 549 */     return convertAndCast((Range)range, type);
/*     */   }
/*     */   
/*     */   public NumberRange<?>[] subtract(Range<?> range) {
/* 558 */     Class<? extends Number> type = ClassChanger.getWidestClass(this.elementClass, getElementClass(range));
/* 559 */     return (NumberRange<?>[])castTo(type).subtractNC(convertAndCast((Range)range, type));
/*     */   }
/*     */   
/*     */   public double getMinimum() {
/* 571 */     Number value = (Number)getMinValue();
/* 572 */     return (value != null) ? value.doubleValue() : Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getMinimum(boolean inclusive) {
/* 586 */     double value = getMinimum();
/* 587 */     if (inclusive != isMinIncluded())
/* 588 */       value = XMath.rool(getElementClass(), value, inclusive ? 1 : -1); 
/* 590 */     return value;
/*     */   }
/*     */   
/*     */   public double getMaximum() {
/* 601 */     Number value = (Number)getMaxValue();
/* 602 */     return (value != null) ? value.doubleValue() : Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getMaximum(boolean inclusive) {
/* 616 */     double value = getMaximum();
/* 617 */     if (inclusive != isMaxIncluded())
/* 618 */       value = XMath.rool(getElementClass(), value, inclusive ? -1 : 1); 
/* 620 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\NumberRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */