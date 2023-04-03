/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetChangeListener;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class IntervalXYDelegate implements DatasetChangeListener, DomainInfo, Serializable, Cloneable, PublicCloneable {
/*     */   private static final long serialVersionUID = -685166711639592857L;
/*     */   
/*     */   private XYDataset dataset;
/*     */   
/*     */   private boolean autoWidth;
/*     */   
/*     */   private double intervalPositionFactor;
/*     */   
/*     */   private double fixedIntervalWidth;
/*     */   
/*     */   private double autoIntervalWidth;
/*     */   
/*     */   public IntervalXYDelegate(XYDataset dataset) {
/* 123 */     this(dataset, true);
/*     */   }
/*     */   
/*     */   public IntervalXYDelegate(XYDataset dataset, boolean autoWidth) {
/* 134 */     if (dataset == null)
/* 135 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 137 */     this.dataset = dataset;
/* 138 */     this.autoWidth = autoWidth;
/* 139 */     this.intervalPositionFactor = 0.5D;
/* 140 */     this.autoIntervalWidth = Double.POSITIVE_INFINITY;
/* 141 */     this.fixedIntervalWidth = 1.0D;
/*     */   }
/*     */   
/*     */   public boolean isAutoWidth() {
/* 151 */     return this.autoWidth;
/*     */   }
/*     */   
/*     */   public void setAutoWidth(boolean b) {
/* 166 */     this.autoWidth = b;
/* 167 */     if (b)
/* 168 */       this.autoIntervalWidth = recalculateInterval(); 
/*     */   }
/*     */   
/*     */   public double getIntervalPositionFactor() {
/* 178 */     return this.intervalPositionFactor;
/*     */   }
/*     */   
/*     */   public void setIntervalPositionFactor(double d) {
/* 198 */     if (d < 0.0D || 1.0D < d)
/* 199 */       throw new IllegalArgumentException("Argument 'd' outside valid range."); 
/* 202 */     this.intervalPositionFactor = d;
/*     */   }
/*     */   
/*     */   public double getFixedIntervalWidth() {
/* 211 */     return this.fixedIntervalWidth;
/*     */   }
/*     */   
/*     */   public void setFixedIntervalWidth(double w) {
/* 226 */     if (w < 0.0D)
/* 227 */       throw new IllegalArgumentException("Negative 'w' argument."); 
/* 229 */     this.fixedIntervalWidth = w;
/* 230 */     this.autoWidth = false;
/*     */   }
/*     */   
/*     */   public double getIntervalWidth() {
/* 241 */     if (isAutoWidth() && !Double.isInfinite(this.autoIntervalWidth))
/* 244 */       return this.autoIntervalWidth; 
/* 248 */     return this.fixedIntervalWidth;
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 263 */     Number startX = null;
/* 264 */     Number x = this.dataset.getX(series, item);
/* 265 */     if (x != null)
/* 266 */       startX = new Double(x.doubleValue() - getIntervalPositionFactor() * getIntervalWidth()); 
/* 269 */     return startX;
/*     */   }
/*     */   
/*     */   public double getStartXValue(int series, int item) {
/* 283 */     return this.dataset.getXValue(series, item) - getIntervalPositionFactor() * getIntervalWidth();
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 298 */     Number endX = null;
/* 299 */     Number x = this.dataset.getX(series, item);
/* 300 */     if (x != null)
/* 301 */       endX = new Double(x.doubleValue() + (1.0D - getIntervalPositionFactor()) * getIntervalWidth()); 
/* 304 */     return endX;
/*     */   }
/*     */   
/*     */   public double getEndXValue(int series, int item) {
/* 318 */     return this.dataset.getXValue(series, item) + (1.0D - getIntervalPositionFactor()) * getIntervalWidth();
/*     */   }
/*     */   
/*     */   public double getDomainLowerBound(boolean includeInterval) {
/* 331 */     double result = Double.NaN;
/* 332 */     Range r = getDomainBounds(includeInterval);
/* 333 */     if (r != null)
/* 334 */       result = r.getLowerBound(); 
/* 336 */     return result;
/*     */   }
/*     */   
/*     */   public double getDomainUpperBound(boolean includeInterval) {
/* 348 */     double result = Double.NaN;
/* 349 */     Range r = getDomainBounds(includeInterval);
/* 350 */     if (r != null)
/* 351 */       result = r.getUpperBound(); 
/* 353 */     return result;
/*     */   }
/*     */   
/*     */   public Range getDomainBounds(boolean includeInterval) {
/* 368 */     Range range = DatasetUtilities.findDomainBounds(this.dataset, false);
/* 369 */     if (includeInterval && range != null) {
/* 370 */       double lowerAdj = getIntervalWidth() * getIntervalPositionFactor();
/* 371 */       double upperAdj = getIntervalWidth() - lowerAdj;
/* 372 */       range = new Range(range.getLowerBound() - lowerAdj, range.getUpperBound() + upperAdj);
/*     */     } 
/* 375 */     return range;
/*     */   }
/*     */   
/*     */   public void datasetChanged(DatasetChangeEvent e) {
/* 388 */     if (this.autoWidth)
/* 389 */       this.autoIntervalWidth = recalculateInterval(); 
/*     */   }
/*     */   
/*     */   private double recalculateInterval() {
/* 397 */     double result = Double.POSITIVE_INFINITY;
/* 398 */     int seriesCount = this.dataset.getSeriesCount();
/* 399 */     for (int series = 0; series < seriesCount; series++)
/* 400 */       result = Math.min(result, calculateIntervalForSeries(series)); 
/* 402 */     return result;
/*     */   }
/*     */   
/*     */   private double calculateIntervalForSeries(int series) {
/* 411 */     double result = Double.POSITIVE_INFINITY;
/* 412 */     int itemCount = this.dataset.getItemCount(series);
/* 413 */     if (itemCount > 1) {
/* 414 */       double prev = this.dataset.getXValue(series, 0);
/* 415 */       for (int item = 1; item < itemCount; item++) {
/* 416 */         double x = this.dataset.getXValue(series, item);
/* 417 */         result = Math.min(result, x - prev);
/* 418 */         prev = x;
/*     */       } 
/*     */     } 
/* 421 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 432 */     if (obj == this)
/* 433 */       return true; 
/* 435 */     if (!(obj instanceof IntervalXYDelegate))
/* 436 */       return false; 
/* 438 */     IntervalXYDelegate that = (IntervalXYDelegate)obj;
/* 439 */     if (this.autoWidth != that.autoWidth)
/* 440 */       return false; 
/* 442 */     if (this.intervalPositionFactor != that.intervalPositionFactor)
/* 443 */       return false; 
/* 445 */     if (this.fixedIntervalWidth != that.fixedIntervalWidth)
/* 446 */       return false; 
/* 448 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 457 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\IntervalXYDelegate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */