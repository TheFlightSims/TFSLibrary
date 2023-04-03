/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jfree.data.KeyedObjects2D;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.RangeInfo;
/*     */ import org.jfree.data.general.AbstractDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class DefaultBoxAndWhiskerCategoryDataset extends AbstractDataset implements BoxAndWhiskerCategoryDataset, RangeInfo {
/*  86 */   protected KeyedObjects2D data = new KeyedObjects2D();
/*     */   
/*  87 */   private Number minimumRangeValue = null;
/*     */   
/*  88 */   private Number maximumRangeValue = null;
/*     */   
/*  89 */   private Range rangeBounds = new Range(0.0D, 0.0D);
/*     */   
/*     */   public void add(List list, Comparable rowKey, Comparable columnKey) {
/* 102 */     BoxAndWhiskerItem item = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(list);
/* 104 */     add(item, rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public void add(BoxAndWhiskerItem item, Comparable rowKey, Comparable columnKey) {
/* 119 */     this.data.addObject(item, rowKey, columnKey);
/* 120 */     double minval = item.getMinOutlier().doubleValue();
/* 121 */     double maxval = item.getMaxOutlier().doubleValue();
/* 123 */     if (this.maximumRangeValue == null) {
/* 124 */       this.maximumRangeValue = new Double(maxval);
/* 126 */     } else if (maxval > this.maximumRangeValue.doubleValue()) {
/* 127 */       this.maximumRangeValue = new Double(maxval);
/*     */     } 
/* 130 */     if (this.minimumRangeValue == null) {
/* 131 */       this.minimumRangeValue = new Double(minval);
/* 133 */     } else if (minval < this.minimumRangeValue.doubleValue()) {
/* 134 */       this.minimumRangeValue = new Double(minval);
/*     */     } 
/* 137 */     this.rangeBounds = new Range(this.minimumRangeValue.doubleValue(), this.maximumRangeValue.doubleValue());
/* 142 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public BoxAndWhiskerItem getItem(int row, int column) {
/* 155 */     return (BoxAndWhiskerItem)this.data.getObject(row, column);
/*     */   }
/*     */   
/*     */   public Number getValue(int row, int column) {
/* 167 */     return getMedianValue(row, column);
/*     */   }
/*     */   
/*     */   public Number getValue(Comparable rowKey, Comparable columnKey) {
/* 179 */     return getMedianValue(rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public Number getMeanValue(int row, int column) {
/* 192 */     Number result = null;
/* 193 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/* 196 */     if (item != null)
/* 197 */       result = item.getMean(); 
/* 199 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMeanValue(Comparable rowKey, Comparable columnKey) {
/* 213 */     Number result = null;
/* 214 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/* 217 */     if (item != null)
/* 218 */       result = item.getMean(); 
/* 220 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMedianValue(int row, int column) {
/* 233 */     Number result = null;
/* 234 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/* 237 */     if (item != null)
/* 238 */       result = item.getMedian(); 
/* 240 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMedianValue(Comparable rowKey, Comparable columnKey) {
/* 252 */     Number result = null;
/* 253 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/* 256 */     if (item != null)
/* 257 */       result = item.getMedian(); 
/* 259 */     return result;
/*     */   }
/*     */   
/*     */   public Number getQ1Value(int row, int column) {
/* 271 */     Number result = null;
/* 272 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/* 275 */     if (item != null)
/* 276 */       result = item.getQ1(); 
/* 278 */     return result;
/*     */   }
/*     */   
/*     */   public Number getQ1Value(Comparable rowKey, Comparable columnKey) {
/* 290 */     Number result = null;
/* 291 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/* 294 */     if (item != null)
/* 295 */       result = item.getQ1(); 
/* 297 */     return result;
/*     */   }
/*     */   
/*     */   public Number getQ3Value(int row, int column) {
/* 309 */     Number result = null;
/* 310 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/* 313 */     if (item != null)
/* 314 */       result = item.getQ3(); 
/* 316 */     return result;
/*     */   }
/*     */   
/*     */   public Number getQ3Value(Comparable rowKey, Comparable columnKey) {
/* 328 */     Number result = null;
/* 329 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/* 332 */     if (item != null)
/* 333 */       result = item.getQ3(); 
/* 335 */     return result;
/*     */   }
/*     */   
/*     */   public int getColumnIndex(Comparable key) {
/* 346 */     return this.data.getColumnIndex(key);
/*     */   }
/*     */   
/*     */   public Comparable getColumnKey(int column) {
/* 357 */     return this.data.getColumnKey(column);
/*     */   }
/*     */   
/*     */   public List getColumnKeys() {
/* 366 */     return this.data.getColumnKeys();
/*     */   }
/*     */   
/*     */   public int getRowIndex(Comparable key) {
/* 377 */     return this.data.getRowIndex(key);
/*     */   }
/*     */   
/*     */   public Comparable getRowKey(int row) {
/* 388 */     return this.data.getRowKey(row);
/*     */   }
/*     */   
/*     */   public List getRowKeys() {
/* 397 */     return this.data.getRowKeys();
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 406 */     return this.data.getRowCount();
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 415 */     return this.data.getColumnCount();
/*     */   }
/*     */   
/*     */   public double getRangeLowerBound(boolean includeInterval) {
/* 427 */     double result = Double.NaN;
/* 428 */     if (this.minimumRangeValue != null)
/* 429 */       result = this.minimumRangeValue.doubleValue(); 
/* 431 */     return result;
/*     */   }
/*     */   
/*     */   public double getRangeUpperBound(boolean includeInterval) {
/* 443 */     double result = Double.NaN;
/* 444 */     if (this.maximumRangeValue != null)
/* 445 */       result = this.maximumRangeValue.doubleValue(); 
/* 447 */     return result;
/*     */   }
/*     */   
/*     */   public Range getRangeBounds(boolean includeInterval) {
/* 459 */     return this.rangeBounds;
/*     */   }
/*     */   
/*     */   public Number getMinRegularValue(int row, int column) {
/* 472 */     Number result = null;
/* 473 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/* 476 */     if (item != null)
/* 477 */       result = item.getMinRegularValue(); 
/* 479 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMinRegularValue(Comparable rowKey, Comparable columnKey) {
/* 493 */     Number result = null;
/* 494 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/* 497 */     if (item != null)
/* 498 */       result = item.getMinRegularValue(); 
/* 500 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMaxRegularValue(int row, int column) {
/* 514 */     Number result = null;
/* 515 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/* 518 */     if (item != null)
/* 519 */       result = item.getMaxRegularValue(); 
/* 521 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMaxRegularValue(Comparable rowKey, Comparable columnKey) {
/* 535 */     Number result = null;
/* 536 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/* 539 */     if (item != null)
/* 540 */       result = item.getMaxRegularValue(); 
/* 542 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMinOutlier(int row, int column) {
/* 556 */     Number result = null;
/* 557 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/* 560 */     if (item != null)
/* 561 */       result = item.getMinOutlier(); 
/* 563 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMinOutlier(Comparable rowKey, Comparable columnKey) {
/* 577 */     Number result = null;
/* 578 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/* 581 */     if (item != null)
/* 582 */       result = item.getMinOutlier(); 
/* 584 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMaxOutlier(int row, int column) {
/* 598 */     Number result = null;
/* 599 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/* 602 */     if (item != null)
/* 603 */       result = item.getMaxOutlier(); 
/* 605 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMaxOutlier(Comparable rowKey, Comparable columnKey) {
/* 619 */     Number result = null;
/* 620 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/* 623 */     if (item != null)
/* 624 */       result = item.getMaxOutlier(); 
/* 626 */     return result;
/*     */   }
/*     */   
/*     */   public List getOutliers(int row, int column) {
/* 640 */     List result = null;
/* 641 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/* 644 */     if (item != null)
/* 645 */       result = item.getOutliers(); 
/* 647 */     return result;
/*     */   }
/*     */   
/*     */   public List getOutliers(Comparable rowKey, Comparable columnKey) {
/* 661 */     List result = null;
/* 662 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/* 665 */     if (item != null)
/* 666 */       result = item.getOutliers(); 
/* 668 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 681 */     if (obj == null)
/* 682 */       return false; 
/* 685 */     if (obj == this)
/* 686 */       return true; 
/* 689 */     if (obj instanceof DefaultBoxAndWhiskerCategoryDataset) {
/* 690 */       DefaultBoxAndWhiskerCategoryDataset dataset = (DefaultBoxAndWhiskerCategoryDataset)obj;
/* 692 */       return ObjectUtilities.equal(this.data, dataset.data);
/*     */     } 
/* 695 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\DefaultBoxAndWhiskerCategoryDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */