/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public class Range<T extends Comparable<? super T>> implements Serializable {
/*     */   private static final long serialVersionUID = -5393896130562660517L;
/*     */   
/*     */   final Class<T> elementClass;
/*     */   
/*     */   final T minValue;
/*     */   
/*     */   final T maxValue;
/*     */   
/*     */   private final boolean isMinIncluded;
/*     */   
/*     */   private final boolean isMaxIncluded;
/*     */   
/*     */   public Range(Class<T> elementClass, T value) {
/*  93 */     this(elementClass, value, true, value, true);
/*     */   }
/*     */   
/*     */   public Range(Class<T> elementClass, T minValue, T maxValue) {
/* 104 */     this(elementClass, minValue, true, maxValue, true);
/*     */   }
/*     */   
/*     */   public Range(Class<T> elementClass, T minValue, boolean isMinIncluded, T maxValue, boolean isMaxIncluded) {
/* 125 */     ensureNonNull("elementClass", elementClass);
/* 126 */     this.elementClass = elementClass;
/* 131 */     this.minValue = minValue;
/* 132 */     this.maxValue = maxValue;
/* 133 */     this.isMinIncluded = (isMinIncluded && minValue != null);
/* 134 */     this.isMaxIncluded = (isMaxIncluded && maxValue != null);
/* 135 */     checkElementClass();
/* 136 */     if (minValue != null)
/* 136 */       ensureCompatible(minValue.getClass()); 
/* 137 */     if (maxValue != null)
/* 137 */       ensureCompatible(maxValue.getClass()); 
/*     */   }
/*     */   
/*     */   Range<T> create(T minValue, boolean isMinIncluded, T maxValue, boolean isMaxIncluded) {
/* 147 */     return new Range(this.elementClass, minValue, isMinIncluded, maxValue, isMaxIncluded);
/*     */   }
/*     */   
/*     */   static void ensureNonNull(String name, Object value) throws IllegalArgumentException {
/* 154 */     if (value == null)
/* 155 */       throw new IllegalArgumentException(Errors.format(143, name)); 
/*     */   }
/*     */   
/*     */   private Range<? extends T> ensureCompatible(Range<?> range) throws IllegalArgumentException {
/* 164 */     ensureNonNull("range", range);
/* 165 */     ensureCompatible(range.elementClass);
/* 166 */     return (Range)range;
/*     */   }
/*     */   
/*     */   private void ensureCompatible(Class<?> type) throws IllegalArgumentException {
/* 173 */     if (!this.elementClass.isAssignableFrom(type))
/* 174 */       throw new IllegalArgumentException(Errors.format(61, type, this.elementClass)); 
/*     */   }
/*     */   
/*     */   void checkElementClass() throws IllegalArgumentException {}
/*     */   
/*     */   Range<T>[] newArray(int length) {
/* 192 */     return (Range<T>[])new Range[length];
/*     */   }
/*     */   
/*     */   public Class<T> getElementClass() {
/* 203 */     return this.elementClass;
/*     */   }
/*     */   
/*     */   public T getMinValue() {
/* 216 */     return this.minValue;
/*     */   }
/*     */   
/*     */   public boolean isMinIncluded() {
/* 227 */     return this.isMinIncluded;
/*     */   }
/*     */   
/*     */   public T getMaxValue() {
/* 240 */     return this.maxValue;
/*     */   }
/*     */   
/*     */   public boolean isMaxIncluded() {
/* 251 */     return this.isMaxIncluded;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 265 */     if (this.minValue == null || this.maxValue == null)
/* 266 */       return false; 
/* 268 */     int c = this.minValue.compareTo(this.maxValue);
/* 269 */     if (c < 0)
/* 270 */       return false; 
/* 273 */     return (c != 0 || !this.isMinIncluded || !this.isMaxIncluded);
/*     */   }
/*     */   
/*     */   public boolean contains(Comparable<?> value) throws IllegalArgumentException {
/* 288 */     if (value == null)
/* 289 */       return false; 
/* 291 */     ensureCompatible(value.getClass());
/* 293 */     Comparable<?> comparable = value;
/* 294 */     return containsNC(comparable);
/*     */   }
/*     */   
/*     */   final boolean containsNC(Comparable value) {
/* 302 */     if (this.minValue != null) {
/* 303 */       int c = this.minValue.compareTo(value);
/* 304 */       if (c >= 0 && (
/* 305 */         c != 0 || !this.isMinIncluded))
/* 306 */         return false; 
/*     */     } 
/* 310 */     if (this.maxValue != null) {
/* 311 */       int c = this.maxValue.compareTo(value);
/* 312 */       if (c <= 0 && (
/* 313 */         c != 0 || !this.isMaxIncluded))
/* 314 */         return false; 
/*     */     } 
/* 318 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(Range<?> range) throws IllegalArgumentException {
/* 330 */     return containsNC(ensureCompatible(range));
/*     */   }
/*     */   
/*     */   final boolean containsNC(Range<? extends T> range) {
/* 338 */     return ((this.minValue == null || compareMinTo(range.minValue, range.isMinIncluded ? 0 : 1) <= 0) && (this.maxValue == null || compareMaxTo(range.maxValue, range.isMaxIncluded ? 0 : -1) >= 0));
/*     */   }
/*     */   
/*     */   public boolean intersects(Range<?> range) throws IllegalArgumentException {
/* 353 */     return intersectsNC(ensureCompatible(range));
/*     */   }
/*     */   
/*     */   final boolean intersectsNC(Range<? extends T> range) {
/* 361 */     return (compareMinTo(range.maxValue, range.isMaxIncluded ? 0 : -1) <= 0 && compareMaxTo(range.minValue, range.isMinIncluded ? 0 : 1) >= 0);
/*     */   }
/*     */   
/*     */   public Range<?> intersect(Range<?> range) throws IllegalArgumentException {
/* 376 */     return intersectNC(ensureCompatible(range));
/*     */   }
/*     */   
/*     */   final Range<? extends T> intersectNC(Range<? extends T> range) throws IllegalArgumentException {
/* 387 */     Range<? extends T> intersect, min = (compareMinTo(range.minValue, range.isMinIncluded ? 0 : 1) < 0) ? range : this;
/* 388 */     Range<? extends T> max = (compareMaxTo(range.maxValue, range.isMaxIncluded ? 0 : -1) > 0) ? range : this;
/* 389 */     if (min == max) {
/* 390 */       intersect = min;
/*     */     } else {
/* 392 */       intersect = create(min.minValue, min.isMinIncluded, max.maxValue, max.isMaxIncluded);
/*     */     } 
/* 394 */     assert intersect.isEmpty() == (!intersects(range)) : intersect;
/* 395 */     return intersect;
/*     */   }
/*     */   
/*     */   public Range<?>[] subtract(Range<?> range) throws IllegalArgumentException {
/* 417 */     return (Range<?>[])subtractNC(ensureCompatible(range));
/*     */   }
/*     */   
/*     */   final Range<T>[] subtractNC(Range<? extends T> range) throws IllegalArgumentException {
/*     */     Range<T> subtract;
/* 426 */     if (!intersects(range)) {
/* 427 */       subtract = this;
/*     */     } else {
/* 429 */       boolean clipMin = (compareMinTo(range.minValue, range.isMinIncluded ? 0 : 1) >= 0);
/* 430 */       boolean clipMax = (compareMaxTo(range.maxValue, range.isMaxIncluded ? 0 : -1) <= 0);
/* 431 */       if (clipMin) {
/* 432 */         if (clipMax) {
/* 434 */           assert range.contains(this) : range;
/* 435 */           return newArray(0);
/*     */         } 
/* 437 */         subtract = create(range.maxValue, !range.isMaxIncluded, this.maxValue, this.isMaxIncluded);
/*     */       } else {
/* 439 */         if (!clipMax) {
/* 440 */           Range[] arrayOfRange1 = (Range[])newArray(2);
/* 441 */           arrayOfRange1[0] = create(this.minValue, this.isMinIncluded, range.minValue, !range.isMinIncluded);
/* 442 */           arrayOfRange1[1] = create(range.maxValue, !range.isMaxIncluded, this.maxValue, this.isMaxIncluded);
/* 443 */           return (Range<T>[])arrayOfRange1;
/*     */         } 
/* 445 */         subtract = create(this.minValue, this.isMinIncluded, range.minValue, !range.isMinIncluded);
/*     */       } 
/*     */     } 
/* 448 */     assert contains(subtract) : subtract;
/* 449 */     assert !subtract.intersects(range) : subtract;
/* 450 */     Range[] arrayOfRange = (Range[])newArray(1);
/* 451 */     arrayOfRange[0] = subtract;
/* 452 */     return (Range<T>[])arrayOfRange;
/*     */   }
/*     */   
/*     */   public Range<?> union(Range<?> range) throws IllegalArgumentException {
/* 466 */     return unionNC(ensureCompatible(range));
/*     */   }
/*     */   
/*     */   final Range<?> unionNC(Range<? extends T> range) throws IllegalArgumentException {
/* 475 */     Range<? extends T> union, min = (compareMinTo(range.minValue, range.isMinIncluded ? 0 : 1) > 0) ? range : this;
/* 476 */     Range<? extends T> max = (compareMaxTo(range.maxValue, range.isMaxIncluded ? 0 : -1) < 0) ? range : this;
/* 477 */     if (min == max) {
/* 478 */       union = min;
/*     */     } else {
/* 480 */       union = create(min.minValue, min.isMinIncluded, max.maxValue, max.isMaxIncluded);
/*     */     } 
/* 482 */     assert union.contains(min) : min;
/* 483 */     assert union.contains(max) : max;
/* 484 */     return union;
/*     */   }
/*     */   
/*     */   final int compareMinTo(T value, int delta) {
/* 498 */     if (value == null)
/* 504 */       return delta; 
/* 506 */     if (this.minValue == null)
/* 513 */       return -1; 
/* 515 */     int c = this.minValue.compareTo(value);
/* 516 */     if (c == 0) {
/* 517 */       if (this.isMinIncluded)
/* 522 */         return -delta; 
/* 524 */       if (delta <= 0)
/* 530 */         return 1; 
/*     */     } 
/* 533 */     return c;
/*     */   }
/*     */   
/*     */   final int compareMaxTo(T value, int delta) {
/* 548 */     if (value == null)
/* 549 */       return delta; 
/* 551 */     if (this.maxValue == null)
/* 552 */       return 1; 
/* 554 */     int c = this.maxValue.compareTo(value);
/* 555 */     if (c == 0) {
/* 556 */       if (this.isMaxIncluded)
/* 557 */         return -delta; 
/* 559 */       if (delta >= 0)
/* 560 */         return -1; 
/*     */     } 
/* 563 */     return c;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 574 */     if (object == this)
/* 575 */       return true; 
/* 577 */     if (object != null && object.getClass().equals(getClass())) {
/* 578 */       Range<?> other = (Range)object;
/* 579 */       if (Utilities.equals(this.elementClass, other.elementClass)) {
/* 580 */         if (isEmpty())
/* 581 */           return other.isEmpty(); 
/* 583 */         return (Utilities.equals(this.minValue, other.minValue) && Utilities.equals(this.maxValue, other.maxValue) && this.isMinIncluded == other.isMinIncluded && this.isMaxIncluded == other.isMaxIncluded);
/*     */       } 
/*     */     } 
/* 589 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 597 */     int result = 511010651;
/* 598 */     if (!isEmpty()) {
/* 599 */       result += this.elementClass.hashCode();
/* 600 */       result = Utilities.hash(this.isMaxIncluded, result);
/* 601 */       result = Utilities.hash(this.isMinIncluded, result);
/* 602 */       result = Utilities.hash(this.maxValue, result);
/* 603 */       result = Utilities.hash(this.minValue, result);
/*     */     } 
/* 605 */     return result;
/*     */   }
/*     */   
/*     */   Unit<?> getUnits() {
/* 612 */     return null;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 620 */     if (isEmpty())
/* 621 */       return "[]"; 
/* 623 */     StringBuilder buffer = new StringBuilder();
/* 624 */     buffer.append(this.isMinIncluded ? 91 : 40);
/* 625 */     if (this.minValue == null) {
/* 626 */       buffer.append("-∞");
/*     */     } else {
/* 628 */       buffer.append(this.minValue);
/*     */     } 
/* 630 */     buffer.append(", ");
/* 631 */     if (this.maxValue == null) {
/* 632 */       buffer.append('∞');
/*     */     } else {
/* 634 */       buffer.append(this.maxValue);
/*     */     } 
/* 636 */     buffer.append(this.isMaxIncluded ? 93 : 41);
/* 637 */     Unit<?> units = getUnits();
/* 638 */     if (units != null)
/* 639 */       buffer.append(' ').append(units); 
/* 641 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\Range.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */