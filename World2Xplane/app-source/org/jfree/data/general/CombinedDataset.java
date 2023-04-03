/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.OHLCDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ public class CombinedDataset extends AbstractIntervalXYDataset implements XYDataset, OHLCDataset, IntervalXYDataset, CombinationDataset {
/*  79 */   private List datasetInfo = new ArrayList();
/*     */   
/*     */   public CombinedDataset() {}
/*     */   
/*     */   public CombinedDataset(SeriesDataset[] data) {
/*  95 */     add(data);
/*     */   }
/*     */   
/*     */   public void add(SeriesDataset data) {
/* 105 */     fastAdd(data);
/* 106 */     DatasetChangeEvent event = new DatasetChangeEvent(this, (Dataset)this);
/* 107 */     notifyListeners(event);
/*     */   }
/*     */   
/*     */   public void add(SeriesDataset[] data) {
/* 118 */     for (int i = 0; i < data.length; i++)
/* 119 */       fastAdd(data[i]); 
/* 121 */     DatasetChangeEvent event = new DatasetChangeEvent(this, (Dataset)this);
/* 122 */     notifyListeners(event);
/*     */   }
/*     */   
/*     */   public void add(SeriesDataset data, int series) {
/* 134 */     add((SeriesDataset)new SubSeriesDataset(data, series));
/*     */   }
/*     */   
/*     */   private void fastAdd(SeriesDataset data) {
/* 143 */     for (int i = 0; i < data.getSeriesCount(); i++)
/* 144 */       this.datasetInfo.add(new DatasetInfo(this, data, i)); 
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 158 */     return this.datasetInfo.size();
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 169 */     DatasetInfo di = getDatasetInfo(series);
/* 170 */     return di.data.getSeriesKey(di.series);
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 189 */     DatasetInfo di = getDatasetInfo(series);
/* 190 */     return ((XYDataset)di.data).getX(di.series, item);
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 205 */     DatasetInfo di = getDatasetInfo(series);
/* 206 */     return ((XYDataset)di.data).getY(di.series, item);
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 220 */     DatasetInfo di = getDatasetInfo(series);
/* 221 */     return ((XYDataset)di.data).getItemCount(di.series);
/*     */   }
/*     */   
/*     */   public Number getHigh(int series, int item) {
/* 240 */     DatasetInfo di = getDatasetInfo(series);
/* 241 */     return ((OHLCDataset)di.data).getHigh(di.series, item);
/*     */   }
/*     */   
/*     */   public double getHighValue(int series, int item) {
/* 254 */     double result = Double.NaN;
/* 255 */     Number high = getHigh(series, item);
/* 256 */     if (high != null)
/* 257 */       result = high.doubleValue(); 
/* 259 */     return result;
/*     */   }
/*     */   
/*     */   public Number getLow(int series, int item) {
/* 274 */     DatasetInfo di = getDatasetInfo(series);
/* 275 */     return ((OHLCDataset)di.data).getLow(di.series, item);
/*     */   }
/*     */   
/*     */   public double getLowValue(int series, int item) {
/* 288 */     double result = Double.NaN;
/* 289 */     Number low = getLow(series, item);
/* 290 */     if (low != null)
/* 291 */       result = low.doubleValue(); 
/* 293 */     return result;
/*     */   }
/*     */   
/*     */   public Number getOpen(int series, int item) {
/* 308 */     DatasetInfo di = getDatasetInfo(series);
/* 309 */     return ((OHLCDataset)di.data).getOpen(di.series, item);
/*     */   }
/*     */   
/*     */   public double getOpenValue(int series, int item) {
/* 322 */     double result = Double.NaN;
/* 323 */     Number open = getOpen(series, item);
/* 324 */     if (open != null)
/* 325 */       result = open.doubleValue(); 
/* 327 */     return result;
/*     */   }
/*     */   
/*     */   public Number getClose(int series, int item) {
/* 342 */     DatasetInfo di = getDatasetInfo(series);
/* 343 */     return ((OHLCDataset)di.data).getClose(di.series, item);
/*     */   }
/*     */   
/*     */   public double getCloseValue(int series, int item) {
/* 356 */     double result = Double.NaN;
/* 357 */     Number close = getClose(series, item);
/* 358 */     if (close != null)
/* 359 */       result = close.doubleValue(); 
/* 361 */     return result;
/*     */   }
/*     */   
/*     */   public Number getVolume(int series, int item) {
/* 376 */     DatasetInfo di = getDatasetInfo(series);
/* 377 */     return ((OHLCDataset)di.data).getVolume(di.series, item);
/*     */   }
/*     */   
/*     */   public double getVolumeValue(int series, int item) {
/* 390 */     double result = Double.NaN;
/* 391 */     Number volume = getVolume(series, item);
/* 392 */     if (volume != null)
/* 393 */       result = volume.doubleValue(); 
/* 395 */     return result;
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 411 */     DatasetInfo di = getDatasetInfo(series);
/* 412 */     if (di.data instanceof IntervalXYDataset)
/* 413 */       return ((IntervalXYDataset)di.data).getStartX(di.series, item); 
/* 416 */     return getX(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 429 */     DatasetInfo di = getDatasetInfo(series);
/* 430 */     if (di.data instanceof IntervalXYDataset)
/* 431 */       return ((IntervalXYDataset)di.data).getEndX(di.series, item); 
/* 434 */     return getX(series, item);
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 447 */     DatasetInfo di = getDatasetInfo(series);
/* 448 */     if (di.data instanceof IntervalXYDataset)
/* 449 */       return ((IntervalXYDataset)di.data).getStartY(di.series, item); 
/* 452 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 465 */     DatasetInfo di = getDatasetInfo(series);
/* 466 */     if (di.data instanceof IntervalXYDataset)
/* 467 */       return ((IntervalXYDataset)di.data).getEndY(di.series, item); 
/* 470 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public SeriesDataset getParent() {
/* 487 */     SeriesDataset parent = null;
/* 488 */     for (int i = 0; i < this.datasetInfo.size(); i++) {
/* 489 */       SeriesDataset child = (getDatasetInfo(i)).data;
/* 490 */       if (child instanceof CombinationDataset) {
/* 491 */         SeriesDataset childParent = ((CombinationDataset)child).getParent();
/* 493 */         if (parent == null) {
/* 494 */           parent = childParent;
/* 496 */         } else if (parent != childParent) {
/* 497 */           return null;
/*     */         } 
/*     */       } else {
/* 501 */         return null;
/*     */       } 
/*     */     } 
/* 504 */     return parent;
/*     */   }
/*     */   
/*     */   public int[] getMap() {
/* 520 */     int[] map = null;
/* 521 */     for (int i = 0; i < this.datasetInfo.size(); i++) {
/* 522 */       SeriesDataset child = (getDatasetInfo(i)).data;
/* 523 */       if (child instanceof CombinationDataset) {
/* 524 */         int[] childMap = ((CombinationDataset)child).getMap();
/* 525 */         if (childMap == null)
/* 526 */           return null; 
/* 528 */         map = joinMap(map, childMap);
/*     */       } else {
/* 531 */         return null;
/*     */       } 
/*     */     } 
/* 534 */     return map;
/*     */   }
/*     */   
/*     */   public int getChildPosition(Dataset child) {
/* 550 */     int n = 0;
/* 551 */     for (int i = 0; i < this.datasetInfo.size(); i++) {
/* 552 */       SeriesDataset childDataset = (getDatasetInfo(i)).data;
/* 553 */       if (childDataset instanceof CombinedDataset) {
/* 554 */         int m = ((CombinedDataset)childDataset).getChildPosition(child);
/* 556 */         if (m >= 0)
/* 557 */           return n + m; 
/* 559 */         n++;
/*     */       } else {
/* 562 */         if (child == childDataset)
/* 563 */           return n; 
/* 565 */         n++;
/*     */       } 
/*     */     } 
/* 568 */     return -1;
/*     */   }
/*     */   
/*     */   private DatasetInfo getDatasetInfo(int series) {
/* 583 */     return this.datasetInfo.get(series);
/*     */   }
/*     */   
/*     */   private int[] joinMap(int[] a, int[] b) {
/* 595 */     if (a == null)
/* 596 */       return b; 
/* 598 */     if (b == null)
/* 599 */       return a; 
/* 601 */     int[] result = new int[a.length + b.length];
/* 602 */     System.arraycopy(a, 0, result, 0, a.length);
/* 603 */     System.arraycopy(b, 0, result, a.length, b.length);
/* 604 */     return result;
/*     */   }
/*     */   
/*     */   private class DatasetInfo {
/*     */     private SeriesDataset data;
/*     */     
/*     */     private int series;
/*     */     
/*     */     private final CombinedDataset this$0;
/*     */     
/*     */     DatasetInfo(CombinedDataset this$0, SeriesDataset data, int series) {
/* 625 */       this.this$0 = this$0;
/* 626 */       this.data = data;
/* 627 */       this.series = series;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\CombinedDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */