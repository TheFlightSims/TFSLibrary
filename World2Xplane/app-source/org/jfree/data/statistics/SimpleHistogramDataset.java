/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DomainOrder;
/*     */ import org.jfree.data.general.Dataset;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class SimpleHistogramDataset extends AbstractIntervalXYDataset implements IntervalXYDataset, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 7997996479768018443L;
/*     */   
/*     */   private Comparable key;
/*     */   
/*     */   private List bins;
/*     */   
/*     */   private boolean adjustForBinSize;
/*     */   
/*     */   public SimpleHistogramDataset(Comparable key) {
/*  89 */     this.key = key;
/*  90 */     this.bins = new ArrayList();
/*  91 */     this.adjustForBinSize = true;
/*     */   }
/*     */   
/*     */   public boolean getAdjustForBinSize() {
/* 101 */     return this.adjustForBinSize;
/*     */   }
/*     */   
/*     */   public void setAdjustForBinSize(boolean adjust) {
/* 111 */     this.adjustForBinSize = adjust;
/* 112 */     notifyListeners(new DatasetChangeEvent(this, (Dataset)this));
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 121 */     return 1;
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 132 */     return this.key;
/*     */   }
/*     */   
/*     */   public DomainOrder getDomainOrder() {
/* 141 */     return DomainOrder.ASCENDING;
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 152 */     return this.bins.size();
/*     */   }
/*     */   
/*     */   public void addBin(SimpleHistogramBin bin) {
/* 163 */     Iterator iterator = this.bins.iterator();
/* 164 */     while (iterator.hasNext()) {
/* 165 */       SimpleHistogramBin existingBin = iterator.next();
/* 167 */       if (bin.overlapsWith(existingBin))
/* 168 */         throw new RuntimeException("Overlapping bin"); 
/*     */     } 
/* 171 */     this.bins.add(bin);
/* 172 */     Collections.sort(this.bins);
/*     */   }
/*     */   
/*     */   public void addObservation(double value) {
/* 183 */     addObservation(value, true);
/*     */   }
/*     */   
/*     */   public void addObservation(double value, boolean notify) {
/* 195 */     boolean placed = false;
/* 196 */     Iterator iterator = this.bins.iterator();
/* 197 */     while (iterator.hasNext() && !placed) {
/* 198 */       SimpleHistogramBin bin = iterator.next();
/* 199 */       if (bin.accepts(value)) {
/* 200 */         bin.setItemCount(bin.getItemCount() + 1);
/* 201 */         placed = true;
/*     */       } 
/*     */     } 
/* 204 */     if (!placed)
/* 205 */       throw new RuntimeException("No bin."); 
/* 207 */     if (notify)
/* 208 */       notifyListeners(new DatasetChangeEvent(this, (Dataset)this)); 
/*     */   }
/*     */   
/*     */   public void addObservations(double[] values) {
/* 218 */     for (int i = 0; i < values.length; i++)
/* 219 */       addObservation(values[i], false); 
/* 221 */     notifyListeners(new DatasetChangeEvent(this, (Dataset)this));
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 235 */     return new Double(getXValue(series, item));
/*     */   }
/*     */   
/*     */   public double getXValue(int series, int item) {
/* 247 */     SimpleHistogramBin bin = this.bins.get(item);
/* 248 */     return (bin.getLowerBound() + bin.getUpperBound()) / 2.0D;
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 260 */     return new Double(getYValue(series, item));
/*     */   }
/*     */   
/*     */   public double getYValue(int series, int item) {
/* 272 */     SimpleHistogramBin bin = this.bins.get(item);
/* 273 */     if (this.adjustForBinSize)
/* 274 */       return bin.getItemCount() / (bin.getUpperBound() - bin.getLowerBound()); 
/* 278 */     return bin.getItemCount();
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 291 */     return new Double(getStartXValue(series, item));
/*     */   }
/*     */   
/*     */   public double getStartXValue(int series, int item) {
/* 304 */     SimpleHistogramBin bin = this.bins.get(item);
/* 305 */     return bin.getLowerBound();
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 317 */     return new Double(getEndXValue(series, item));
/*     */   }
/*     */   
/*     */   public double getEndXValue(int series, int item) {
/* 330 */     SimpleHistogramBin bin = this.bins.get(item);
/* 331 */     return bin.getUpperBound();
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 343 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public double getStartYValue(int series, int item) {
/* 356 */     return getYValue(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 368 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public double getEndYValue(int series, int item) {
/* 381 */     return getYValue(series, item);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 392 */     if (obj == this)
/* 393 */       return true; 
/* 395 */     if (!(obj instanceof SimpleHistogramDataset))
/* 396 */       return false; 
/* 398 */     SimpleHistogramDataset that = (SimpleHistogramDataset)obj;
/* 399 */     if (!this.key.equals(that.key))
/* 400 */       return false; 
/* 402 */     if (this.adjustForBinSize != that.adjustForBinSize)
/* 403 */       return false; 
/* 405 */     if (!this.bins.equals(that.bins))
/* 406 */       return false; 
/* 408 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 420 */     SimpleHistogramDataset clone = (SimpleHistogramDataset)super.clone();
/* 421 */     clone.bins = (List)ObjectUtilities.deepClone(this.bins);
/* 422 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\SimpleHistogramDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */