/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jfree.data.KeyedObjects2D;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.RangeInfo;
/*     */ import org.jfree.data.general.AbstractDataset;
/*     */ 
/*     */ public class DefaultStatisticalCategoryDataset extends AbstractDataset implements StatisticalCategoryDataset, RangeInfo {
/*  87 */   private KeyedObjects2D data = new KeyedObjects2D();
/*     */   
/*  88 */   private double minimumRangeValue = 0.0D;
/*     */   
/*  89 */   private double maximumRangeValue = 0.0D;
/*     */   
/*  90 */   private Range rangeBounds = new Range(0.0D, 0.0D);
/*     */   
/*     */   public Number getMeanValue(int row, int column) {
/* 102 */     Number result = null;
/* 103 */     MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(row, column);
/* 105 */     if (masd != null)
/* 106 */       result = masd.getMean(); 
/* 108 */     return result;
/*     */   }
/*     */   
/*     */   public Number getValue(int row, int column) {
/* 121 */     return getMeanValue(row, column);
/*     */   }
/*     */   
/*     */   public Number getValue(Comparable rowKey, Comparable columnKey) {
/* 134 */     return getMeanValue(rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public Number getMeanValue(Comparable rowKey, Comparable columnKey) {
/* 146 */     Number result = null;
/* 147 */     MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(rowKey, columnKey);
/* 149 */     if (masd != null)
/* 150 */       result = masd.getMean(); 
/* 152 */     return result;
/*     */   }
/*     */   
/*     */   public Number getStdDevValue(int row, int column) {
/* 164 */     Number result = null;
/* 165 */     MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(row, column);
/* 167 */     if (masd != null)
/* 168 */       result = masd.getStandardDeviation(); 
/* 170 */     return result;
/*     */   }
/*     */   
/*     */   public Number getStdDevValue(Comparable rowKey, Comparable columnKey) {
/* 182 */     Number result = null;
/* 183 */     MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(rowKey, columnKey);
/* 185 */     if (masd != null)
/* 186 */       result = masd.getStandardDeviation(); 
/* 188 */     return result;
/*     */   }
/*     */   
/*     */   public int getColumnIndex(Comparable key) {
/* 199 */     return this.data.getColumnIndex(key);
/*     */   }
/*     */   
/*     */   public Comparable getColumnKey(int column) {
/* 210 */     return this.data.getColumnKey(column);
/*     */   }
/*     */   
/*     */   public List getColumnKeys() {
/* 219 */     return this.data.getColumnKeys();
/*     */   }
/*     */   
/*     */   public int getRowIndex(Comparable key) {
/* 230 */     return this.data.getRowIndex(key);
/*     */   }
/*     */   
/*     */   public Comparable getRowKey(int row) {
/* 241 */     return this.data.getRowKey(row);
/*     */   }
/*     */   
/*     */   public List getRowKeys() {
/* 250 */     return this.data.getRowKeys();
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 259 */     return this.data.getRowCount();
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 268 */     return this.data.getColumnCount();
/*     */   }
/*     */   
/*     */   public void add(double mean, double standardDeviation, Comparable rowKey, Comparable columnKey) {
/* 281 */     add(new Double(mean), new Double(standardDeviation), rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public void add(Number mean, Number standardDeviation, Comparable rowKey, Comparable columnKey) {
/* 294 */     MeanAndStandardDeviation item = new MeanAndStandardDeviation(mean, standardDeviation);
/* 297 */     this.data.addObject(item, rowKey, columnKey);
/* 298 */     double m = 0.0D;
/* 299 */     double sd = 0.0D;
/* 300 */     if (mean != null)
/* 301 */       m = mean.doubleValue(); 
/* 303 */     if (standardDeviation != null)
/* 304 */       sd = standardDeviation.doubleValue(); 
/* 307 */     if (m + sd > this.maximumRangeValue) {
/* 308 */       this.maximumRangeValue = m + sd;
/* 309 */       this.rangeBounds = new Range(this.minimumRangeValue, this.maximumRangeValue);
/*     */     } 
/* 313 */     if (m - sd < this.minimumRangeValue) {
/* 314 */       this.minimumRangeValue = m - sd;
/* 315 */       this.rangeBounds = new Range(this.minimumRangeValue, this.maximumRangeValue);
/*     */     } 
/* 319 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public double getRangeLowerBound(boolean includeInterval) {
/* 332 */     return this.minimumRangeValue;
/*     */   }
/*     */   
/*     */   public double getRangeUpperBound(boolean includeInterval) {
/* 345 */     return this.maximumRangeValue;
/*     */   }
/*     */   
/*     */   public Range getRangeBounds(boolean includeInterval) {
/* 357 */     return this.rangeBounds;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 368 */     if (obj == this)
/* 369 */       return true; 
/* 371 */     if (!(obj instanceof DefaultStatisticalCategoryDataset))
/* 372 */       return false; 
/* 374 */     DefaultStatisticalCategoryDataset that = (DefaultStatisticalCategoryDataset)obj;
/* 376 */     if (!this.data.equals(that.data))
/* 377 */       return false; 
/* 379 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\DefaultStatisticalCategoryDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */