/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.RangeInfo;
/*     */ import org.jfree.data.xy.AbstractXYDataset;
/*     */ 
/*     */ public class DefaultBoxAndWhiskerXYDataset extends AbstractXYDataset implements BoxAndWhiskerXYDataset, RangeInfo {
/*     */   private Comparable seriesKey;
/*     */   
/*     */   private List dates;
/*     */   
/*     */   private List items;
/*     */   
/*     */   private Number minimumRangeValue;
/*     */   
/*     */   private Number maximumRangeValue;
/*     */   
/*     */   private Range rangeBounds;
/*     */   
/* 104 */   private double outlierCoefficient = 1.5D;
/*     */   
/* 112 */   private double faroutCoefficient = 2.0D;
/*     */   
/*     */   public DefaultBoxAndWhiskerXYDataset(Comparable seriesKey) {
/* 123 */     this.seriesKey = seriesKey;
/* 124 */     this.dates = new ArrayList();
/* 125 */     this.items = new ArrayList();
/* 126 */     this.minimumRangeValue = null;
/* 127 */     this.maximumRangeValue = null;
/* 128 */     this.rangeBounds = null;
/*     */   }
/*     */   
/*     */   public void add(Date date, BoxAndWhiskerItem item) {
/* 138 */     this.dates.add(date);
/* 139 */     this.items.add(item);
/* 140 */     if (this.minimumRangeValue == null) {
/* 141 */       this.minimumRangeValue = item.getMinRegularValue();
/* 144 */     } else if (item.getMinRegularValue().doubleValue() < this.minimumRangeValue.doubleValue()) {
/* 146 */       this.minimumRangeValue = item.getMinRegularValue();
/*     */     } 
/* 149 */     if (this.maximumRangeValue == null) {
/* 150 */       this.maximumRangeValue = item.getMaxRegularValue();
/* 153 */     } else if (item.getMaxRegularValue().doubleValue() > this.maximumRangeValue.doubleValue()) {
/* 155 */       this.maximumRangeValue = item.getMaxRegularValue();
/*     */     } 
/* 158 */     this.rangeBounds = new Range(this.minimumRangeValue.doubleValue(), this.maximumRangeValue.doubleValue());
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int i) {
/* 172 */     return this.seriesKey;
/*     */   }
/*     */   
/*     */   public BoxAndWhiskerItem getItem(int series, int item) {
/* 185 */     return this.items.get(item);
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 200 */     return new Long(((Date)this.dates.get(item)).getTime());
/*     */   }
/*     */   
/*     */   public Date getXDate(int series, int item) {
/* 214 */     return this.dates.get(item);
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 229 */     return new Double(getMeanValue(series, item).doubleValue());
/*     */   }
/*     */   
/*     */   public Number getMeanValue(int series, int item) {
/* 241 */     Number result = null;
/* 242 */     BoxAndWhiskerItem stats = this.items.get(item);
/* 243 */     if (stats != null)
/* 244 */       result = stats.getMean(); 
/* 246 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMedianValue(int series, int item) {
/* 258 */     Number result = null;
/* 259 */     BoxAndWhiskerItem stats = this.items.get(item);
/* 260 */     if (stats != null)
/* 261 */       result = stats.getMedian(); 
/* 263 */     return result;
/*     */   }
/*     */   
/*     */   public Number getQ1Value(int series, int item) {
/* 275 */     Number result = null;
/* 276 */     BoxAndWhiskerItem stats = this.items.get(item);
/* 277 */     if (stats != null)
/* 278 */       result = stats.getQ1(); 
/* 280 */     return result;
/*     */   }
/*     */   
/*     */   public Number getQ3Value(int series, int item) {
/* 292 */     Number result = null;
/* 293 */     BoxAndWhiskerItem stats = this.items.get(item);
/* 294 */     if (stats != null)
/* 295 */       result = stats.getQ3(); 
/* 297 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMinRegularValue(int series, int item) {
/* 309 */     Number result = null;
/* 310 */     BoxAndWhiskerItem stats = this.items.get(item);
/* 311 */     if (stats != null)
/* 312 */       result = stats.getMinRegularValue(); 
/* 314 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMaxRegularValue(int series, int item) {
/* 326 */     Number result = null;
/* 327 */     BoxAndWhiskerItem stats = this.items.get(item);
/* 328 */     if (stats != null)
/* 329 */       result = stats.getMaxRegularValue(); 
/* 331 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMinOutlier(int series, int item) {
/* 342 */     Number result = null;
/* 343 */     BoxAndWhiskerItem stats = this.items.get(item);
/* 344 */     if (stats != null)
/* 345 */       result = stats.getMinOutlier(); 
/* 347 */     return result;
/*     */   }
/*     */   
/*     */   public Number getMaxOutlier(int series, int item) {
/* 360 */     Number result = null;
/* 361 */     BoxAndWhiskerItem stats = this.items.get(item);
/* 362 */     if (stats != null)
/* 363 */       result = stats.getMaxOutlier(); 
/* 365 */     return result;
/*     */   }
/*     */   
/*     */   public List getOutliers(int series, int item) {
/* 377 */     List result = null;
/* 378 */     BoxAndWhiskerItem stats = this.items.get(item);
/* 379 */     if (stats != null)
/* 380 */       result = stats.getOutliers(); 
/* 382 */     return result;
/*     */   }
/*     */   
/*     */   public double getOutlierCoefficient() {
/* 396 */     return this.outlierCoefficient;
/*     */   }
/*     */   
/*     */   public double getFaroutCoefficient() {
/* 407 */     return this.faroutCoefficient;
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 418 */     return 1;
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 429 */     return this.dates.size();
/*     */   }
/*     */   
/*     */   public void setOutlierCoefficient(double outlierCoefficient) {
/* 439 */     this.outlierCoefficient = outlierCoefficient;
/*     */   }
/*     */   
/*     */   public void setFaroutCoefficient(double faroutCoefficient) {
/* 451 */     if (faroutCoefficient > getOutlierCoefficient()) {
/* 452 */       this.faroutCoefficient = faroutCoefficient;
/*     */     } else {
/* 455 */       throw new IllegalArgumentException("Farout value must be greater than the outlier value, which is currently set at: (" + getOutlierCoefficient() + ")");
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getRangeLowerBound(boolean includeInterval) {
/* 470 */     double result = Double.NaN;
/* 471 */     if (this.minimumRangeValue != null)
/* 472 */       result = this.minimumRangeValue.doubleValue(); 
/* 474 */     return result;
/*     */   }
/*     */   
/*     */   public double getRangeUpperBound(boolean includeInterval) {
/* 486 */     double result = Double.NaN;
/* 487 */     if (this.maximumRangeValue != null)
/* 488 */       result = this.maximumRangeValue.doubleValue(); 
/* 490 */     return result;
/*     */   }
/*     */   
/*     */   public Range getRangeBounds(boolean includeInterval) {
/* 502 */     return this.rangeBounds;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\DefaultBoxAndWhiskerXYDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */