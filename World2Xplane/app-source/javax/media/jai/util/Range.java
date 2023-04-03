/*     */ package javax.media.jai.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Range implements Serializable {
/*     */   private Class elementClass;
/*     */   
/*     */   private Comparable minValue;
/*     */   
/*     */   private Comparable maxValue;
/*     */   
/*     */   private boolean isMinIncluded = true, isMaxIncluded = true;
/*     */   
/*     */   public Range(Class elementClass, Comparable minValue, Comparable maxValue) {
/*  85 */     if (minValue == null && maxValue == null) {
/*  86 */       Class c = null;
/*     */       try {
/*  88 */         c = Class.forName("java.lang.Comparable");
/*  90 */       } catch (ClassNotFoundException e) {}
/*  93 */       if (!c.isAssignableFrom(elementClass))
/*  94 */         throw new IllegalArgumentException(JaiI18N.getString("Range0")); 
/*     */     } 
/*  97 */     this.elementClass = elementClass;
/*  99 */     if (minValue != null && minValue.getClass() != this.elementClass)
/* 100 */       throw new IllegalArgumentException(JaiI18N.getString("Range1")); 
/* 103 */     this.minValue = minValue;
/* 105 */     if (maxValue != null && maxValue.getClass() != this.elementClass)
/* 106 */       throw new IllegalArgumentException(JaiI18N.getString("Range2")); 
/* 109 */     this.maxValue = maxValue;
/*     */   }
/*     */   
/*     */   public Range(Class elementClass, Comparable minValue, boolean isMinIncluded, Comparable maxValue, boolean isMaxIncluded) {
/* 148 */     this(elementClass, minValue, maxValue);
/* 149 */     this.isMinIncluded = isMinIncluded;
/* 150 */     this.isMaxIncluded = isMaxIncluded;
/*     */   }
/*     */   
/*     */   public boolean isMinIncluded() {
/* 159 */     if (this.minValue == null)
/* 160 */       return true; 
/* 162 */     return this.isMinIncluded;
/*     */   }
/*     */   
/*     */   public boolean isMaxIncluded() {
/* 171 */     if (this.maxValue == null)
/* 172 */       return true; 
/* 174 */     return this.isMaxIncluded;
/*     */   }
/*     */   
/*     */   public Class getElementClass() {
/* 181 */     return this.elementClass;
/*     */   }
/*     */   
/*     */   public Comparable getMinValue() {
/* 189 */     return this.minValue;
/*     */   }
/*     */   
/*     */   public Comparable getMaxValue() {
/* 197 */     return this.maxValue;
/*     */   }
/*     */   
/*     */   public boolean contains(Comparable value) {
/* 213 */     if (value != null && value.getClass() != this.elementClass)
/* 214 */       throw new IllegalArgumentException(JaiI18N.getString("Range3")); 
/* 218 */     if (isEmpty() == true)
/* 219 */       return false; 
/* 222 */     return (isUnderUpperBound(value) && isOverLowerBound(value));
/*     */   }
/*     */   
/*     */   private boolean isUnderUpperBound(Comparable value) {
/* 233 */     if (this.maxValue == null)
/* 234 */       return true; 
/* 238 */     if (value == null)
/* 239 */       return false; 
/* 241 */     if (this.isMaxIncluded)
/* 242 */       return (this.maxValue.compareTo(value) >= 0); 
/* 243 */     return (this.maxValue.compareTo(value) > 0);
/*     */   }
/*     */   
/*     */   private boolean isOverLowerBound(Comparable value) {
/* 252 */     if (this.minValue == null)
/* 253 */       return true; 
/* 257 */     if (value == null)
/* 258 */       return false; 
/* 260 */     if (this.isMinIncluded)
/* 261 */       return (this.minValue.compareTo(value) <= 0); 
/* 262 */     return (this.minValue.compareTo(value) < 0);
/*     */   }
/*     */   
/*     */   public boolean contains(Range range) {
/*     */     boolean maxSide, minSide;
/* 278 */     if (range == null)
/* 279 */       throw new IllegalArgumentException(JaiI18N.getString("Range5")); 
/* 280 */     if (this.elementClass != range.getElementClass())
/* 281 */       throw new IllegalArgumentException(JaiI18N.getString("Range4")); 
/* 283 */     if (range.isEmpty())
/* 284 */       return true; 
/* 286 */     Comparable min = range.getMinValue();
/* 287 */     Comparable max = range.getMaxValue();
/* 290 */     if (max == null) {
/* 291 */       maxSide = (this.maxValue == null);
/*     */     } else {
/* 293 */       maxSide = (isUnderUpperBound(max) || (this.isMaxIncluded == range.isMaxIncluded() && max.equals(this.maxValue)));
/*     */     } 
/* 297 */     if (min == null) {
/* 298 */       minSide = (this.minValue == null);
/*     */     } else {
/* 300 */       minSide = (isOverLowerBound(min) || (this.isMinIncluded == range.isMinIncluded() && min.equals(this.minValue)));
/*     */     } 
/* 303 */     return (minSide && maxSide);
/*     */   }
/*     */   
/*     */   public boolean intersects(Range range) {
/* 316 */     if (range == null)
/* 317 */       throw new IllegalArgumentException(JaiI18N.getString("Range5")); 
/* 318 */     if (this.elementClass != range.getElementClass())
/* 319 */       throw new IllegalArgumentException(JaiI18N.getString("Range4")); 
/* 321 */     return !intersect(range).isEmpty();
/*     */   }
/*     */   
/*     */   public Range union(Range range) {
/* 340 */     if (range == null)
/* 341 */       throw new IllegalArgumentException(JaiI18N.getString("Range5")); 
/* 342 */     if (this.elementClass != range.getElementClass())
/* 343 */       throw new IllegalArgumentException(JaiI18N.getString("Range4")); 
/* 345 */     if (isEmpty())
/* 346 */       return new Range(this.elementClass, range.getMinValue(), range.isMinIncluded(), range.getMaxValue(), range.isMaxIncluded()); 
/* 350 */     if (range.isEmpty())
/* 351 */       return new Range(this.elementClass, this.minValue, this.isMinIncluded, this.maxValue, this.isMaxIncluded); 
/* 354 */     boolean containMin = !isOverLowerBound(range.getMinValue());
/* 355 */     boolean containMax = !isUnderUpperBound(range.getMaxValue());
/* 361 */     Comparable minValue = containMin ? range.getMinValue() : this.minValue;
/* 362 */     Comparable maxValue = containMax ? range.getMaxValue() : this.maxValue;
/* 363 */     boolean isMinIncluded = containMin ? range.isMinIncluded() : this.isMinIncluded;
/* 365 */     boolean isMaxIncluded = containMax ? range.isMaxIncluded() : this.isMaxIncluded;
/* 367 */     return new Range(this.elementClass, minValue, isMinIncluded, maxValue, isMaxIncluded);
/*     */   }
/*     */   
/*     */   public Range intersect(Range range) {
/* 382 */     if (range == null)
/* 383 */       throw new IllegalArgumentException(JaiI18N.getString("Range5")); 
/* 384 */     if (this.elementClass != range.getElementClass())
/* 385 */       throw new IllegalArgumentException(JaiI18N.getString("Range4")); 
/* 387 */     if (isEmpty()) {
/* 388 */       Comparable temp = this.minValue;
/* 389 */       if (temp == null)
/* 390 */         temp = this.maxValue; 
/* 395 */       return new Range(this.elementClass, temp, false, temp, false);
/*     */     } 
/* 398 */     if (range.isEmpty()) {
/* 399 */       Comparable temp = range.getMinValue();
/* 400 */       if (temp == null)
/* 401 */         temp = range.getMaxValue(); 
/* 406 */       return new Range(this.elementClass, temp, false, temp, false);
/*     */     } 
/* 409 */     boolean containMin = !isOverLowerBound(range.getMinValue());
/* 410 */     boolean containMax = !isUnderUpperBound(range.getMaxValue());
/* 415 */     Comparable minValue = containMin ? this.minValue : range.getMinValue();
/* 416 */     Comparable maxValue = containMax ? this.maxValue : range.getMaxValue();
/* 417 */     boolean isMinIncluded = containMin ? this.isMinIncluded : range.isMinIncluded();
/* 419 */     boolean isMaxIncluded = containMax ? this.isMaxIncluded : range.isMaxIncluded();
/* 421 */     return new Range(this.elementClass, minValue, isMinIncluded, maxValue, isMaxIncluded);
/*     */   }
/*     */   
/*     */   public Range[] subtract(Range range) {
/* 447 */     if (range == null)
/* 448 */       throw new IllegalArgumentException(JaiI18N.getString("Range5")); 
/* 449 */     if (this.elementClass != range.getElementClass())
/* 450 */       throw new IllegalArgumentException(JaiI18N.getString("Range4")); 
/* 454 */     if (isEmpty() || range.isEmpty()) {
/* 455 */       Range[] arrayOfRange = { new Range(this.elementClass, this.minValue, this.isMinIncluded, this.maxValue, this.isMaxIncluded) };
/* 457 */       return arrayOfRange;
/*     */     } 
/* 460 */     Comparable min = range.getMinValue();
/* 461 */     Comparable max = range.getMaxValue();
/* 462 */     boolean minIn = range.isMinIncluded();
/* 463 */     boolean maxIn = range.isMaxIncluded();
/* 465 */     if (this.minValue == null && this.maxValue == null && min == null && max == null) {
/* 467 */       Range[] arrayOfRange = { null };
/* 468 */       return arrayOfRange;
/*     */     } 
/* 471 */     boolean containMin = contains(min);
/* 472 */     boolean containMax = contains(max);
/* 475 */     if (containMin && containMax) {
/* 476 */       Range r1 = new Range(this.elementClass, this.minValue, this.isMinIncluded, min, !minIn);
/* 478 */       Range r2 = new Range(this.elementClass, max, !maxIn, this.maxValue, this.isMaxIncluded);
/* 486 */       if (r1.isEmpty() || (this.minValue == null && min == null)) {
/* 487 */         Range[] arrayOfRange1 = { r2 };
/* 488 */         return arrayOfRange1;
/*     */       } 
/* 491 */       if (r2.isEmpty() || (this.maxValue == null && max == null)) {
/* 492 */         Range[] arrayOfRange1 = { r1 };
/* 493 */         return arrayOfRange1;
/*     */       } 
/* 495 */       Range[] arrayOfRange = { r1, r2 };
/* 496 */       return arrayOfRange;
/*     */     } 
/* 500 */     if (containMax) {
/* 501 */       Range[] arrayOfRange = { new Range(this.elementClass, max, !maxIn, this.maxValue, this.isMaxIncluded) };
/* 503 */       return arrayOfRange;
/*     */     } 
/* 507 */     if (containMin) {
/* 508 */       Range[] arrayOfRange = { new Range(this.elementClass, this.minValue, this.isMinIncluded, min, !minIn) };
/* 510 */       return arrayOfRange;
/*     */     } 
/* 514 */     if ((min != null && !isUnderUpperBound(min)) || (max != null && !isOverLowerBound(max))) {
/* 516 */       Range[] arrayOfRange = { new Range(this.elementClass, this.minValue, this.isMinIncluded, this.maxValue, this.isMaxIncluded) };
/* 518 */       return arrayOfRange;
/*     */     } 
/* 522 */     min = (this.minValue == null) ? this.maxValue : this.minValue;
/* 523 */     Range[] ra = { new Range(this.elementClass, min, false, min, false) };
/* 524 */     return ra;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 535 */     int code = this.elementClass.hashCode();
/* 537 */     if (isEmpty())
/* 538 */       return code; 
/* 540 */     code ^= Integer.MAX_VALUE;
/* 542 */     if (this.minValue != null) {
/* 543 */       code ^= this.minValue.hashCode();
/* 544 */       if (this.isMinIncluded)
/* 545 */         code ^= 0xFFFF0000; 
/*     */     } 
/* 548 */     if (this.maxValue != null) {
/* 549 */       code ^= this.maxValue.hashCode() * 31;
/* 550 */       if (this.isMaxIncluded)
/* 551 */         code ^= 0xFFFF; 
/*     */     } 
/* 554 */     return code;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 571 */     if (other == null)
/* 572 */       return false; 
/* 575 */     if (!(other instanceof Range))
/* 576 */       return false; 
/* 578 */     Range r = (Range)other;
/* 581 */     if (this.elementClass != r.getElementClass())
/* 582 */       return false; 
/* 585 */     if (isEmpty() && r.isEmpty())
/* 586 */       return true; 
/* 588 */     Comparable min = r.getMinValue();
/* 590 */     if (this.minValue != null) {
/* 591 */       if (!this.minValue.equals(min))
/* 592 */         return false; 
/* 594 */       if (this.isMinIncluded != r.isMinIncluded())
/* 595 */         return false; 
/* 599 */     } else if (min != null) {
/* 600 */       return false;
/*     */     } 
/* 602 */     Comparable max = r.getMaxValue();
/* 604 */     if (this.maxValue != null) {
/* 605 */       if (!this.maxValue.equals(max))
/* 606 */         return false; 
/* 608 */       if (this.isMaxIncluded != r.isMaxIncluded())
/* 609 */         return false; 
/* 613 */     } else if (max != null) {
/* 614 */       return false;
/*     */     } 
/* 616 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 628 */     if (this.minValue == null || this.maxValue == null)
/* 629 */       return false; 
/* 631 */     int cmp = this.minValue.compareTo(this.maxValue);
/* 634 */     if (cmp > 0)
/* 635 */       return true; 
/* 639 */     if (cmp == 0)
/* 640 */       return ((this.isMinIncluded & this.isMaxIncluded) == 0); 
/* 643 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 652 */     char c1 = this.isMinIncluded ? '[' : '(';
/* 653 */     char c2 = this.isMaxIncluded ? ']' : ')';
/* 656 */     if (this.minValue != null && this.maxValue != null)
/* 657 */       return new String(c1 + this.minValue.toString() + ", " + this.maxValue.toString() + c2); 
/* 661 */     if (this.maxValue != null)
/* 662 */       return new String(c1 + "---, " + this.maxValue.toString() + c2); 
/* 665 */     if (this.minValue != null)
/* 666 */       return new String(c1 + this.minValue.toString() + ", " + "---" + c2); 
/* 669 */     return new String(c1 + "---, ---" + c2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\ja\\util\Range.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */