/*     */ package org.apache.commons.lang.math;
/*     */ 
/*     */ import org.apache.commons.lang.text.StrBuilder;
/*     */ 
/*     */ public abstract class Range {
/*     */   public abstract Number getMinimumNumber();
/*     */   
/*     */   public long getMinimumLong() {
/*  60 */     return getMinimumNumber().longValue();
/*     */   }
/*     */   
/*     */   public int getMinimumInteger() {
/*  72 */     return getMinimumNumber().intValue();
/*     */   }
/*     */   
/*     */   public double getMinimumDouble() {
/*  84 */     return getMinimumNumber().doubleValue();
/*     */   }
/*     */   
/*     */   public float getMinimumFloat() {
/*  96 */     return getMinimumNumber().floatValue();
/*     */   }
/*     */   
/*     */   public abstract Number getMaximumNumber();
/*     */   
/*     */   public long getMaximumLong() {
/* 115 */     return getMaximumNumber().longValue();
/*     */   }
/*     */   
/*     */   public int getMaximumInteger() {
/* 127 */     return getMaximumNumber().intValue();
/*     */   }
/*     */   
/*     */   public double getMaximumDouble() {
/* 139 */     return getMaximumNumber().doubleValue();
/*     */   }
/*     */   
/*     */   public float getMaximumFloat() {
/* 151 */     return getMaximumNumber().floatValue();
/*     */   }
/*     */   
/*     */   public abstract boolean containsNumber(Number paramNumber);
/*     */   
/*     */   public boolean containsLong(Number value) {
/* 186 */     if (value == null)
/* 187 */       return false; 
/* 189 */     return containsLong(value.longValue());
/*     */   }
/*     */   
/*     */   public boolean containsLong(long value) {
/* 204 */     return (value >= getMinimumLong() && value <= getMaximumLong());
/*     */   }
/*     */   
/*     */   public boolean containsInteger(Number value) {
/* 220 */     if (value == null)
/* 221 */       return false; 
/* 223 */     return containsInteger(value.intValue());
/*     */   }
/*     */   
/*     */   public boolean containsInteger(int value) {
/* 238 */     return (value >= getMinimumInteger() && value <= getMaximumInteger());
/*     */   }
/*     */   
/*     */   public boolean containsDouble(Number value) {
/* 254 */     if (value == null)
/* 255 */       return false; 
/* 257 */     return containsDouble(value.doubleValue());
/*     */   }
/*     */   
/*     */   public boolean containsDouble(double value) {
/* 272 */     int compareMin = NumberUtils.compare(getMinimumDouble(), value);
/* 273 */     int compareMax = NumberUtils.compare(getMaximumDouble(), value);
/* 274 */     return (compareMin <= 0 && compareMax >= 0);
/*     */   }
/*     */   
/*     */   public boolean containsFloat(Number value) {
/* 290 */     if (value == null)
/* 291 */       return false; 
/* 293 */     return containsFloat(value.floatValue());
/*     */   }
/*     */   
/*     */   public boolean containsFloat(float value) {
/* 308 */     int compareMin = NumberUtils.compare(getMinimumFloat(), value);
/* 309 */     int compareMax = NumberUtils.compare(getMaximumFloat(), value);
/* 310 */     return (compareMin <= 0 && compareMax >= 0);
/*     */   }
/*     */   
/*     */   public boolean containsRange(Range range) {
/* 334 */     if (range == null)
/* 335 */       return false; 
/* 337 */     return (containsNumber(range.getMinimumNumber()) && containsNumber(range.getMaximumNumber()));
/*     */   }
/*     */   
/*     */   public boolean overlapsRange(Range range) {
/* 360 */     if (range == null)
/* 361 */       return false; 
/* 363 */     return (range.containsNumber(getMinimumNumber()) || range.containsNumber(getMaximumNumber()) || containsNumber(range.getMinimumNumber()));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 384 */     if (obj == this)
/* 385 */       return true; 
/* 386 */     if (obj == null || obj.getClass() != getClass())
/* 387 */       return false; 
/* 389 */     Range range = (Range)obj;
/* 390 */     return (getMinimumNumber().equals(range.getMinimumNumber()) && getMaximumNumber().equals(range.getMaximumNumber()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 405 */     int result = 17;
/* 406 */     result = 37 * result + getClass().hashCode();
/* 407 */     result = 37 * result + getMinimumNumber().hashCode();
/* 408 */     result = 37 * result + getMaximumNumber().hashCode();
/* 409 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 424 */     StrBuilder buf = new StrBuilder(32);
/* 425 */     buf.append("Range[");
/* 426 */     buf.append(getMinimumNumber());
/* 427 */     buf.append(',');
/* 428 */     buf.append(getMaximumNumber());
/* 429 */     buf.append(']');
/* 430 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\Range.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */