/*     */ package org.jfree.data.gantt;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.AbstractSeriesDataset;
/*     */ import org.jfree.data.general.SeriesChangeEvent;
/*     */ import org.jfree.data.general.SeriesChangeListener;
/*     */ import org.jfree.data.time.TimePeriod;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class TaskSeriesCollection extends AbstractSeriesDataset implements GanttCategoryDataset, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -2065799050738449903L;
/*     */   
/*  87 */   private List keys = new ArrayList();
/*     */   
/*  88 */   private List data = new ArrayList();
/*     */   
/*     */   public int getSeriesCount() {
/*  97 */     return getRowCount();
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 108 */     TaskSeries ts = this.data.get(series);
/* 109 */     return ts.getKey();
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 118 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public List getRowKeys() {
/* 127 */     return this.data;
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 136 */     return this.keys.size();
/*     */   }
/*     */   
/*     */   public List getColumnKeys() {
/* 145 */     return this.keys;
/*     */   }
/*     */   
/*     */   public Comparable getColumnKey(int index) {
/* 156 */     return this.keys.get(index);
/*     */   }
/*     */   
/*     */   public int getColumnIndex(Comparable columnKey) {
/* 167 */     return this.keys.indexOf(columnKey);
/*     */   }
/*     */   
/*     */   public int getRowIndex(Comparable rowKey) {
/* 178 */     int result = -1;
/* 179 */     int count = this.data.size();
/* 180 */     for (int i = 0; i < count; i++) {
/* 181 */       TaskSeries s = this.data.get(i);
/* 182 */       if (s.getKey().equals(rowKey)) {
/* 183 */         result = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 187 */     return result;
/*     */   }
/*     */   
/*     */   public Comparable getRowKey(int index) {
/* 198 */     TaskSeries series = this.data.get(index);
/* 199 */     return series.getKey();
/*     */   }
/*     */   
/*     */   public void add(TaskSeries series) {
/* 210 */     if (series == null)
/* 211 */       throw new IllegalArgumentException("Null 'series' argument."); 
/* 213 */     this.data.add(series);
/* 214 */     series.addChangeListener((SeriesChangeListener)this);
/* 217 */     Iterator iterator = series.getTasks().iterator();
/* 218 */     while (iterator.hasNext()) {
/* 219 */       Task task = iterator.next();
/* 220 */       String key = task.getDescription();
/* 221 */       int index = this.keys.indexOf(key);
/* 222 */       if (index < 0)
/* 223 */         this.keys.add(key); 
/*     */     } 
/* 226 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void remove(TaskSeries series) {
/* 237 */     if (series == null)
/* 238 */       throw new IllegalArgumentException("Null 'series' argument."); 
/* 240 */     if (this.data.contains(series)) {
/* 241 */       series.removeChangeListener((SeriesChangeListener)this);
/* 242 */       this.data.remove(series);
/* 243 */       fireDatasetChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove(int series) {
/* 255 */     if (series < 0 || series > getSeriesCount())
/* 256 */       throw new IllegalArgumentException("TaskSeriesCollection.remove(): index outside valid range."); 
/* 261 */     TaskSeries ts = this.data.get(series);
/* 262 */     ts.removeChangeListener((SeriesChangeListener)this);
/* 263 */     this.data.remove(series);
/* 264 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeAll() {
/* 277 */     Iterator iterator = this.data.iterator();
/* 278 */     while (iterator.hasNext()) {
/* 279 */       TaskSeries series = iterator.next();
/* 280 */       series.removeChangeListener((SeriesChangeListener)this);
/*     */     } 
/* 284 */     this.data.clear();
/* 285 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public Number getValue(Comparable rowKey, Comparable columnKey) {
/* 298 */     return getStartValue(rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public Number getValue(int row, int column) {
/* 310 */     return getStartValue(row, column);
/*     */   }
/*     */   
/*     */   public Number getStartValue(Comparable rowKey, Comparable columnKey) {
/* 323 */     Number result = null;
/* 324 */     int row = getRowIndex(rowKey);
/* 325 */     TaskSeries series = this.data.get(row);
/* 326 */     Task task = series.get(columnKey.toString());
/* 327 */     if (task != null) {
/* 328 */       TimePeriod duration = task.getDuration();
/* 329 */       if (duration != null)
/* 330 */         result = new Long(duration.getStart().getTime()); 
/*     */     } 
/* 333 */     return result;
/*     */   }
/*     */   
/*     */   public Number getStartValue(int row, int column) {
/* 345 */     Comparable rowKey = getRowKey(row);
/* 346 */     Comparable columnKey = getColumnKey(column);
/* 347 */     return getStartValue(rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public Number getEndValue(Comparable rowKey, Comparable columnKey) {
/* 360 */     Number result = null;
/* 361 */     int row = getRowIndex(rowKey);
/* 362 */     TaskSeries series = this.data.get(row);
/* 363 */     Task task = series.get(columnKey.toString());
/* 364 */     if (task != null) {
/* 365 */       TimePeriod duration = task.getDuration();
/* 366 */       if (duration != null)
/* 367 */         result = new Long(duration.getEnd().getTime()); 
/*     */     } 
/* 370 */     return result;
/*     */   }
/*     */   
/*     */   public Number getEndValue(int row, int column) {
/* 382 */     Comparable rowKey = getRowKey(row);
/* 383 */     Comparable columnKey = getColumnKey(column);
/* 384 */     return getEndValue(rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public Number getPercentComplete(int row, int column) {
/* 396 */     Comparable rowKey = getRowKey(row);
/* 397 */     Comparable columnKey = getColumnKey(column);
/* 398 */     return getPercentComplete(rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public Number getPercentComplete(Comparable rowKey, Comparable columnKey) {
/* 410 */     Number result = null;
/* 411 */     int row = getRowIndex(rowKey);
/* 412 */     TaskSeries series = this.data.get(row);
/* 413 */     Task task = series.get(columnKey.toString());
/* 414 */     if (task != null)
/* 415 */       result = task.getPercentComplete(); 
/* 417 */     return result;
/*     */   }
/*     */   
/*     */   public int getSubIntervalCount(int row, int column) {
/* 429 */     Comparable rowKey = getRowKey(row);
/* 430 */     Comparable columnKey = getColumnKey(column);
/* 431 */     return getSubIntervalCount(rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public int getSubIntervalCount(Comparable rowKey, Comparable columnKey) {
/* 443 */     int result = 0;
/* 444 */     int row = getRowIndex(rowKey);
/* 445 */     TaskSeries series = this.data.get(row);
/* 446 */     Task task = series.get(columnKey.toString());
/* 447 */     if (task != null)
/* 448 */       result = task.getSubtaskCount(); 
/* 450 */     return result;
/*     */   }
/*     */   
/*     */   public Number getStartValue(int row, int column, int subinterval) {
/* 463 */     Comparable rowKey = getRowKey(row);
/* 464 */     Comparable columnKey = getColumnKey(column);
/* 465 */     return getStartValue(rowKey, columnKey, subinterval);
/*     */   }
/*     */   
/*     */   public Number getStartValue(Comparable rowKey, Comparable columnKey, int subinterval) {
/* 479 */     Number result = null;
/* 480 */     int row = getRowIndex(rowKey);
/* 481 */     TaskSeries series = this.data.get(row);
/* 482 */     Task task = series.get(columnKey.toString());
/* 483 */     if (task != null) {
/* 484 */       Task sub = task.getSubtask(subinterval);
/* 485 */       if (sub != null) {
/* 486 */         TimePeriod duration = sub.getDuration();
/* 487 */         result = new Long(duration.getStart().getTime());
/*     */       } 
/*     */     } 
/* 490 */     return result;
/*     */   }
/*     */   
/*     */   public Number getEndValue(int row, int column, int subinterval) {
/* 503 */     Comparable rowKey = getRowKey(row);
/* 504 */     Comparable columnKey = getColumnKey(column);
/* 505 */     return getEndValue(rowKey, columnKey, subinterval);
/*     */   }
/*     */   
/*     */   public Number getEndValue(Comparable rowKey, Comparable columnKey, int subinterval) {
/* 519 */     Number result = null;
/* 520 */     int row = getRowIndex(rowKey);
/* 521 */     TaskSeries series = this.data.get(row);
/* 522 */     Task task = series.get(columnKey.toString());
/* 523 */     if (task != null) {
/* 524 */       Task sub = task.getSubtask(subinterval);
/* 525 */       if (sub != null) {
/* 526 */         TimePeriod duration = sub.getDuration();
/* 527 */         result = new Long(duration.getEnd().getTime());
/*     */       } 
/*     */     } 
/* 530 */     return result;
/*     */   }
/*     */   
/*     */   public Number getPercentComplete(int row, int column, int subinterval) {
/* 543 */     Comparable rowKey = getRowKey(row);
/* 544 */     Comparable columnKey = getColumnKey(column);
/* 545 */     return getPercentComplete(rowKey, columnKey, subinterval);
/*     */   }
/*     */   
/*     */   public Number getPercentComplete(Comparable rowKey, Comparable columnKey, int subinterval) {
/* 559 */     Number result = null;
/* 560 */     int row = getRowIndex(rowKey);
/* 561 */     TaskSeries series = this.data.get(row);
/* 562 */     Task task = series.get(columnKey.toString());
/* 563 */     if (task != null) {
/* 564 */       Task sub = task.getSubtask(subinterval);
/* 565 */       if (sub != null)
/* 566 */         result = sub.getPercentComplete(); 
/*     */     } 
/* 569 */     return result;
/*     */   }
/*     */   
/*     */   public void seriesChanged(SeriesChangeEvent event) {
/* 578 */     refreshKeys();
/* 579 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   private void refreshKeys() {
/* 587 */     this.keys.clear();
/* 588 */     for (int i = 0; i < getSeriesCount(); i++) {
/* 589 */       TaskSeries series = this.data.get(i);
/* 591 */       Iterator iterator = series.getTasks().iterator();
/* 592 */       while (iterator.hasNext()) {
/* 593 */         Task task = iterator.next();
/* 594 */         String key = task.getDescription();
/* 595 */         int index = this.keys.indexOf(key);
/* 596 */         if (index < 0)
/* 597 */           this.keys.add(key); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 612 */     if (obj == this)
/* 613 */       return true; 
/* 615 */     if (!(obj instanceof TaskSeriesCollection))
/* 616 */       return false; 
/* 618 */     TaskSeriesCollection that = (TaskSeriesCollection)obj;
/* 619 */     if (!ObjectUtilities.equal(this.data, that.data))
/* 620 */       return false; 
/* 622 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\gantt\TaskSeriesCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */