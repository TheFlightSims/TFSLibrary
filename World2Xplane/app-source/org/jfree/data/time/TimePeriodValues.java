/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.Series;
/*     */ import org.jfree.data.general.SeriesException;
/*     */ 
/*     */ public class TimePeriodValues extends Series implements Serializable {
/*     */   static final long serialVersionUID = -2210593619794989709L;
/*     */   
/*     */   protected static final String DEFAULT_DOMAIN_DESCRIPTION = "Time";
/*     */   
/*     */   protected static final String DEFAULT_RANGE_DESCRIPTION = "Value";
/*     */   
/*     */   private String domain;
/*     */   
/*     */   private String range;
/*     */   
/*     */   private List data;
/*     */   
/*  84 */   private int minStartIndex = -1;
/*     */   
/*  87 */   private int maxStartIndex = -1;
/*     */   
/*  90 */   private int minMiddleIndex = -1;
/*     */   
/*  93 */   private int maxMiddleIndex = -1;
/*     */   
/*  96 */   private int minEndIndex = -1;
/*     */   
/*  99 */   private int maxEndIndex = -1;
/*     */   
/*     */   public TimePeriodValues(String name) {
/* 107 */     this(name, "Time", "Value");
/*     */   }
/*     */   
/*     */   public TimePeriodValues(String name, String domain, String range) {
/* 122 */     super(name);
/* 123 */     this.domain = domain;
/* 124 */     this.range = range;
/* 125 */     this.data = new ArrayList();
/*     */   }
/*     */   
/*     */   public String getDomainDescription() {
/* 134 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomainDescription(String description) {
/* 143 */     String old = this.domain;
/* 144 */     this.domain = description;
/* 145 */     firePropertyChange("Domain", old, description);
/*     */   }
/*     */   
/*     */   public String getRangeDescription() {
/* 154 */     return this.range;
/*     */   }
/*     */   
/*     */   public void setRangeDescription(String description) {
/* 163 */     String old = this.range;
/* 164 */     this.range = description;
/* 165 */     firePropertyChange("Range", old, description);
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 174 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public TimePeriodValue getDataItem(int index) {
/* 185 */     return this.data.get(index);
/*     */   }
/*     */   
/*     */   public TimePeriod getTimePeriod(int index) {
/* 196 */     return getDataItem(index).getPeriod();
/*     */   }
/*     */   
/*     */   public Number getValue(int index) {
/* 207 */     return getDataItem(index).getValue();
/*     */   }
/*     */   
/*     */   public void add(TimePeriodValue item) {
/* 218 */     if (item == null)
/* 219 */       throw new IllegalArgumentException("Null item not allowed."); 
/* 223 */     this.data.add(item);
/* 224 */     updateBounds(item.getPeriod(), this.data.size() - 1);
/*     */   }
/*     */   
/*     */   private void updateBounds(TimePeriod period, int index) {
/* 236 */     long start = period.getStart().getTime();
/* 237 */     long end = period.getEnd().getTime();
/* 238 */     long middle = start + (end - start) / 2L;
/* 240 */     if (this.minStartIndex >= 0) {
/* 241 */       long minStart = getDataItem(this.minStartIndex).getPeriod().getStart().getTime();
/* 243 */       if (start < minStart)
/* 244 */         this.minStartIndex = index; 
/*     */     } else {
/* 248 */       this.minStartIndex = index;
/*     */     } 
/* 251 */     if (this.maxStartIndex >= 0) {
/* 252 */       long maxStart = getDataItem(this.maxStartIndex).getPeriod().getStart().getTime();
/* 254 */       if (start > maxStart)
/* 255 */         this.maxStartIndex = index; 
/*     */     } else {
/* 259 */       this.maxStartIndex = index;
/*     */     } 
/* 262 */     if (this.minMiddleIndex >= 0) {
/* 263 */       long s = getDataItem(this.minMiddleIndex).getPeriod().getStart().getTime();
/* 265 */       long e = getDataItem(this.minMiddleIndex).getPeriod().getEnd().getTime();
/* 267 */       long minMiddle = s + (e - s) / 2L;
/* 268 */       if (middle < minMiddle)
/* 269 */         this.minMiddleIndex = index; 
/*     */     } else {
/* 273 */       this.minMiddleIndex = index;
/*     */     } 
/* 276 */     if (this.maxMiddleIndex >= 0) {
/* 277 */       long s = getDataItem(this.minMiddleIndex).getPeriod().getStart().getTime();
/* 279 */       long e = getDataItem(this.minMiddleIndex).getPeriod().getEnd().getTime();
/* 281 */       long maxMiddle = s + (e - s) / 2L;
/* 282 */       if (middle > maxMiddle)
/* 283 */         this.maxMiddleIndex = index; 
/*     */     } else {
/* 287 */       this.maxMiddleIndex = index;
/*     */     } 
/* 290 */     if (this.minEndIndex >= 0) {
/* 291 */       long minEnd = getDataItem(this.minEndIndex).getPeriod().getEnd().getTime();
/* 293 */       if (end < minEnd)
/* 294 */         this.minEndIndex = index; 
/*     */     } else {
/* 298 */       this.minEndIndex = index;
/*     */     } 
/* 301 */     if (this.maxEndIndex >= 0) {
/* 302 */       long maxEnd = getDataItem(this.maxEndIndex).getPeriod().getEnd().getTime();
/* 304 */       if (end > maxEnd)
/* 305 */         this.maxEndIndex = index; 
/*     */     } else {
/* 309 */       this.maxEndIndex = index;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void recalculateBounds() {
/* 318 */     this.minStartIndex = -1;
/* 319 */     this.minMiddleIndex = -1;
/* 320 */     this.minEndIndex = -1;
/* 321 */     this.maxStartIndex = -1;
/* 322 */     this.maxMiddleIndex = -1;
/* 323 */     this.maxEndIndex = -1;
/* 324 */     for (int i = 0; i < this.data.size(); i++) {
/* 325 */       TimePeriodValue tpv = this.data.get(i);
/* 326 */       updateBounds(tpv.getPeriod(), i);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(TimePeriod period, double value) {
/* 337 */     TimePeriodValue item = new TimePeriodValue(period, value);
/* 338 */     add(item);
/*     */   }
/*     */   
/*     */   public void add(TimePeriod period, Number value) {
/* 348 */     TimePeriodValue item = new TimePeriodValue(period, value);
/* 349 */     add(item);
/*     */   }
/*     */   
/*     */   public void update(int index, Number value) {
/* 359 */     TimePeriodValue item = getDataItem(index);
/* 360 */     item.setValue(value);
/* 361 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public void delete(int start, int end) {
/* 371 */     for (int i = 0; i <= end - start; i++)
/* 372 */       this.data.remove(start); 
/* 374 */     recalculateBounds();
/* 375 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 387 */     if (obj == this)
/* 388 */       return true; 
/* 391 */     if (!(obj instanceof TimePeriodValues))
/* 392 */       return false; 
/* 395 */     if (!super.equals(obj))
/* 396 */       return false; 
/* 399 */     TimePeriodValues that = (TimePeriodValues)obj;
/* 400 */     if (!getDomainDescription().equals(that.getDomainDescription()))
/* 401 */       return false; 
/* 403 */     if (!getRangeDescription().equals(that.getRangeDescription()))
/* 404 */       return false; 
/* 407 */     int count = getItemCount();
/* 408 */     if (count != that.getItemCount())
/* 409 */       return false; 
/* 411 */     for (int i = 0; i < count; i++) {
/* 412 */       if (!getDataItem(i).equals(that.getDataItem(i)))
/* 413 */         return false; 
/*     */     } 
/* 416 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 427 */     int result = (this.domain != null) ? this.domain.hashCode() : 0;
/* 428 */     result = 29 * result + ((this.range != null) ? this.range.hashCode() : 0);
/* 429 */     result = 29 * result + this.data.hashCode();
/* 430 */     result = 29 * result + this.minStartIndex;
/* 431 */     result = 29 * result + this.maxStartIndex;
/* 432 */     result = 29 * result + this.minMiddleIndex;
/* 433 */     result = 29 * result + this.maxMiddleIndex;
/* 434 */     result = 29 * result + this.minEndIndex;
/* 435 */     result = 29 * result + this.maxEndIndex;
/* 436 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 455 */     Object clone = createCopy(0, getItemCount() - 1);
/* 456 */     return clone;
/*     */   }
/*     */   
/*     */   public TimePeriodValues createCopy(int start, int end) throws CloneNotSupportedException {
/* 473 */     TimePeriodValues copy = (TimePeriodValues)super.clone();
/* 475 */     copy.data = new ArrayList();
/* 476 */     if (this.data.size() > 0)
/* 477 */       for (int index = start; index <= end; index++) {
/* 478 */         TimePeriodValue item = this.data.get(index);
/* 479 */         TimePeriodValue clone = (TimePeriodValue)item.clone();
/*     */         try {
/* 481 */           copy.add(clone);
/* 483 */         } catch (SeriesException e) {
/* 484 */           System.err.println("Failed to add cloned item.");
/*     */         } 
/*     */       }  
/* 488 */     return copy;
/*     */   }
/*     */   
/*     */   public int getMinStartIndex() {
/* 498 */     return this.minStartIndex;
/*     */   }
/*     */   
/*     */   public int getMaxStartIndex() {
/* 507 */     return this.maxStartIndex;
/*     */   }
/*     */   
/*     */   public int getMinMiddleIndex() {
/* 517 */     return this.minMiddleIndex;
/*     */   }
/*     */   
/*     */   public int getMaxMiddleIndex() {
/* 527 */     return this.maxMiddleIndex;
/*     */   }
/*     */   
/*     */   public int getMinEndIndex() {
/* 536 */     return this.minEndIndex;
/*     */   }
/*     */   
/*     */   public int getMaxEndIndex() {
/* 545 */     return this.maxEndIndex;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\TimePeriodValues.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */