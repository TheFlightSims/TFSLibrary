/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.Series;
/*     */ import org.jfree.data.general.SeriesException;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class XYSeries extends Series implements Cloneable, Serializable {
/*     */   static final long serialVersionUID = -5908509288197150436L;
/*     */   
/*     */   protected List data;
/*     */   
/* 101 */   private int maximumItemCount = Integer.MAX_VALUE;
/*     */   
/*     */   private boolean autoSort;
/*     */   
/*     */   private boolean allowDuplicateXValues;
/*     */   
/*     */   public XYSeries(Comparable key) {
/* 117 */     this(key, true, true);
/*     */   }
/*     */   
/*     */   public XYSeries(Comparable key, boolean autoSort) {
/* 129 */     this(key, autoSort, true);
/*     */   }
/*     */   
/*     */   public XYSeries(Comparable key, boolean autoSort, boolean allowDuplicateXValues) {
/* 145 */     super(key);
/* 146 */     this.data = new ArrayList();
/* 147 */     this.autoSort = autoSort;
/* 148 */     this.allowDuplicateXValues = allowDuplicateXValues;
/*     */   }
/*     */   
/*     */   public boolean getAutoSort() {
/* 159 */     return this.autoSort;
/*     */   }
/*     */   
/*     */   public boolean getAllowDuplicateXValues() {
/* 169 */     return this.allowDuplicateXValues;
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 178 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public List getItems() {
/* 188 */     return Collections.unmodifiableList(this.data);
/*     */   }
/*     */   
/*     */   public int getMaximumItemCount() {
/* 199 */     return this.maximumItemCount;
/*     */   }
/*     */   
/*     */   public void setMaximumItemCount(int maximum) {
/* 217 */     this.maximumItemCount = maximum;
/* 218 */     boolean dataRemoved = false;
/* 219 */     while (this.data.size() > maximum) {
/* 220 */       this.data.remove(0);
/* 221 */       dataRemoved = true;
/*     */     } 
/* 223 */     if (dataRemoved)
/* 224 */       fireSeriesChanged(); 
/*     */   }
/*     */   
/*     */   public void add(XYDataItem item) {
/* 236 */     add(item, true);
/*     */   }
/*     */   
/*     */   public void add(double x, double y) {
/* 247 */     add(new Double(x), new Double(y), true);
/*     */   }
/*     */   
/*     */   public void add(double x, double y, boolean notify) {
/* 261 */     add(new Double(x), new Double(y), notify);
/*     */   }
/*     */   
/*     */   public void add(double x, Number y) {
/* 273 */     add(new Double(x), y);
/*     */   }
/*     */   
/*     */   public void add(double x, Number y, boolean notify) {
/* 288 */     add(new Double(x), y, notify);
/*     */   }
/*     */   
/*     */   public void add(Number x, Number y) {
/* 303 */     add(x, y, true);
/*     */   }
/*     */   
/*     */   public void add(Number x, Number y, boolean notify) {
/* 320 */     if (x == null)
/* 321 */       throw new IllegalArgumentException("Null 'x' argument."); 
/* 323 */     XYDataItem item = new XYDataItem(x, y);
/* 324 */     add(item, notify);
/*     */   }
/*     */   
/*     */   public void add(XYDataItem item, boolean notify) {
/* 338 */     if (item == null)
/* 339 */       throw new IllegalArgumentException("Null 'item' argument."); 
/* 342 */     if (this.autoSort) {
/* 343 */       int index = Collections.binarySearch(this.data, item);
/* 344 */       if (index < 0) {
/* 345 */         this.data.add(-index - 1, item);
/* 348 */       } else if (this.allowDuplicateXValues) {
/* 350 */         int size = this.data.size();
/* 352 */         while (index < size && item.compareTo(this.data.get(index)) == 0)
/* 353 */           index++; 
/* 355 */         if (index < this.data.size()) {
/* 356 */           this.data.add(index, item);
/*     */         } else {
/* 359 */           this.data.add(item);
/*     */         } 
/*     */       } else {
/* 363 */         throw new SeriesException("X-value already exists.");
/*     */       } 
/*     */     } else {
/* 368 */       if (!this.allowDuplicateXValues) {
/* 371 */         int index = indexOf(item.getX());
/* 372 */         if (index >= 0)
/* 373 */           throw new SeriesException("X-value already exists."); 
/*     */       } 
/* 376 */       this.data.add(item);
/*     */     } 
/* 378 */     if (getItemCount() > this.maximumItemCount)
/* 379 */       this.data.remove(0); 
/* 381 */     if (notify)
/* 382 */       fireSeriesChanged(); 
/*     */   }
/*     */   
/*     */   public void delete(int start, int end) {
/* 394 */     for (int i = start; i <= end; i++)
/* 395 */       this.data.remove(start); 
/* 397 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public XYDataItem remove(int index) {
/* 409 */     XYDataItem result = this.data.remove(index);
/* 410 */     fireSeriesChanged();
/* 411 */     return result;
/*     */   }
/*     */   
/*     */   public XYDataItem remove(Number x) {
/* 423 */     return remove(indexOf(x));
/*     */   }
/*     */   
/*     */   public void clear() {
/* 430 */     if (this.data.size() > 0) {
/* 431 */       this.data.clear();
/* 432 */       fireSeriesChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public XYDataItem getDataItem(int index) {
/* 444 */     return this.data.get(index);
/*     */   }
/*     */   
/*     */   public Number getX(int index) {
/* 455 */     return getDataItem(index).getX();
/*     */   }
/*     */   
/*     */   public Number getY(int index) {
/* 466 */     return getDataItem(index).getY();
/*     */   }
/*     */   
/*     */   public void update(int index, Number y) {
/* 477 */     XYDataItem item = getDataItem(index);
/* 478 */     item.setY(y);
/* 479 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public void update(Number x, Number y) {
/* 492 */     int index = indexOf(x);
/* 493 */     if (index < 0)
/* 494 */       throw new SeriesException("No observation for x = " + x); 
/* 497 */     XYDataItem item = getDataItem(index);
/* 498 */     item.setY(y);
/* 499 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public XYDataItem addOrUpdate(Number x, Number y) {
/* 515 */     if (x == null)
/* 516 */       throw new IllegalArgumentException("Null 'x' argument."); 
/* 518 */     XYDataItem overwritten = null;
/* 519 */     int index = indexOf(x);
/* 520 */     if (index >= 0) {
/* 521 */       XYDataItem existing = this.data.get(index);
/*     */       try {
/* 523 */         overwritten = (XYDataItem)existing.clone();
/* 525 */       } catch (CloneNotSupportedException e) {
/* 526 */         throw new SeriesException("Couldn't clone XYDataItem!");
/*     */       } 
/* 528 */       existing.setY(y);
/*     */     } else {
/* 535 */       if (this.autoSort) {
/* 536 */         this.data.add(-index - 1, new XYDataItem(x, y));
/*     */       } else {
/* 539 */         this.data.add(new XYDataItem(x, y));
/*     */       } 
/* 542 */       if (getItemCount() > this.maximumItemCount)
/* 543 */         this.data.remove(0); 
/*     */     } 
/* 546 */     fireSeriesChanged();
/* 547 */     return overwritten;
/*     */   }
/*     */   
/*     */   public int indexOf(Number x) {
/* 561 */     if (this.autoSort)
/* 562 */       return Collections.binarySearch(this.data, new XYDataItem(x, null)); 
/* 565 */     for (int i = 0; i < this.data.size(); i++) {
/* 566 */       XYDataItem item = this.data.get(i);
/* 567 */       if (item.getX().equals(x))
/* 568 */         return i; 
/*     */     } 
/* 571 */     return -1;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 583 */     Object clone = createCopy(0, getItemCount() - 1);
/* 584 */     return clone;
/*     */   }
/*     */   
/*     */   public XYSeries createCopy(int start, int end) throws CloneNotSupportedException {
/* 600 */     XYSeries copy = (XYSeries)super.clone();
/* 601 */     copy.data = new ArrayList();
/* 602 */     if (this.data.size() > 0)
/* 603 */       for (int index = start; index <= end; index++) {
/* 604 */         XYDataItem item = this.data.get(index);
/* 605 */         XYDataItem clone = (XYDataItem)item.clone();
/*     */         try {
/* 607 */           copy.add(clone);
/* 609 */         } catch (SeriesException e) {
/* 610 */           System.err.println("Unable to add cloned data item.");
/*     */         } 
/*     */       }  
/* 614 */     return copy;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 627 */     if (obj == this)
/* 628 */       return true; 
/* 630 */     if (!(obj instanceof XYSeries))
/* 631 */       return false; 
/* 633 */     if (!super.equals(obj))
/* 634 */       return false; 
/* 636 */     XYSeries that = (XYSeries)obj;
/* 637 */     if (this.maximumItemCount != that.maximumItemCount)
/* 638 */       return false; 
/* 640 */     if (this.autoSort != that.autoSort)
/* 641 */       return false; 
/* 643 */     if (this.allowDuplicateXValues != that.allowDuplicateXValues)
/* 644 */       return false; 
/* 646 */     if (!ObjectUtilities.equal(this.data, that.data))
/* 647 */       return false; 
/* 649 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 658 */     int result = super.hashCode();
/* 659 */     result = 29 * result + ((this.data != null) ? this.data.hashCode() : 0);
/* 660 */     result = 29 * result + this.maximumItemCount;
/* 661 */     result = 29 * result + (this.autoSort ? 1 : 0);
/* 662 */     result = 29 * result + (this.allowDuplicateXValues ? 1 : 0);
/* 663 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\XYSeries.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */