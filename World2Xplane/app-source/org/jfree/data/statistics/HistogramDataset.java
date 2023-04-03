/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jfree.data.general.Dataset;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class HistogramDataset extends AbstractIntervalXYDataset implements IntervalXYDataset, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -6341668077370231153L;
/*     */   
/*  95 */   private List list = new ArrayList();
/*     */   
/*  96 */   private HistogramType type = HistogramType.FREQUENCY;
/*     */   
/*     */   public HistogramType getType() {
/* 105 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(HistogramType type) {
/* 115 */     if (type == null)
/* 116 */       throw new IllegalArgumentException("Null 'type' argument"); 
/* 118 */     this.type = type;
/* 119 */     notifyListeners(new DatasetChangeEvent(this, (Dataset)this));
/*     */   }
/*     */   
/*     */   public void addSeries(Comparable key, double[] values, int bins) {
/* 131 */     double minimum = getMinimum(values);
/* 132 */     double maximum = getMaximum(values);
/* 133 */     addSeries(key, values, bins, minimum, maximum);
/*     */   }
/*     */   
/*     */   public void addSeries(Comparable key, double[] values, int bins, double minimum, double maximum) {
/* 153 */     if (key == null)
/* 154 */       throw new IllegalArgumentException("Null 'key' argument."); 
/* 156 */     if (values == null)
/* 157 */       throw new IllegalArgumentException("Null 'values' argument."); 
/* 159 */     if (bins < 1)
/* 160 */       throw new IllegalArgumentException("The 'bins' value must be at least 1."); 
/* 164 */     double binWidth = (maximum - minimum) / bins;
/* 166 */     double tmp = minimum;
/* 167 */     List binList = new ArrayList(bins);
/*     */     int i;
/* 168 */     for (i = 0; i < bins; i++) {
/*     */       HistogramBin bin;
/* 173 */       if (i == bins - 1) {
/* 174 */         bin = new HistogramBin(tmp, maximum);
/*     */       } else {
/* 177 */         bin = new HistogramBin(tmp, tmp + binWidth);
/*     */       } 
/* 179 */       tmp += binWidth;
/* 180 */       binList.add(bin);
/*     */     } 
/* 183 */     for (i = 0; i < values.length; i++) {
/* 184 */       int binIndex = bins - 1;
/* 185 */       if (values[i] < maximum) {
/* 186 */         double fraction = (values[i] - minimum) / (maximum - minimum);
/* 187 */         if (fraction < 0.0D)
/* 188 */           fraction = 0.0D; 
/* 190 */         binIndex = (int)(fraction * bins);
/*     */       } 
/* 192 */       HistogramBin bin = binList.get(binIndex);
/* 193 */       bin.incrementCount();
/*     */     } 
/* 196 */     Map map = new HashMap();
/* 197 */     map.put("key", key);
/* 198 */     map.put("bins", binList);
/* 199 */     map.put("values.length", new Integer(values.length));
/* 200 */     map.put("bin width", new Double(binWidth));
/* 201 */     this.list.add(map);
/*     */   }
/*     */   
/*     */   private double getMinimum(double[] values) {
/* 213 */     if (values == null || values.length < 1)
/* 214 */       throw new IllegalArgumentException("Null or zero length 'values' argument."); 
/* 218 */     double min = Double.MAX_VALUE;
/* 219 */     for (int i = 0; i < values.length; i++) {
/* 220 */       if (values[i] < min)
/* 221 */         min = values[i]; 
/*     */     } 
/* 224 */     return min;
/*     */   }
/*     */   
/*     */   private double getMaximum(double[] values) {
/* 236 */     if (values == null || values.length < 1)
/* 237 */       throw new IllegalArgumentException("Null or zero length 'values' argument."); 
/* 241 */     double max = -1.7976931348623157E308D;
/* 242 */     for (int i = 0; i < values.length; i++) {
/* 243 */       if (values[i] > max)
/* 244 */         max = values[i]; 
/*     */     } 
/* 247 */     return max;
/*     */   }
/*     */   
/*     */   List getBins(int series) {
/* 258 */     Map map = this.list.get(series);
/* 259 */     return (List)map.get("bins");
/*     */   }
/*     */   
/*     */   private int getTotal(int series) {
/* 270 */     Map map = this.list.get(series);
/* 271 */     return ((Integer)map.get("values.length")).intValue();
/*     */   }
/*     */   
/*     */   private double getBinWidth(int series) {
/* 282 */     Map map = this.list.get(series);
/* 283 */     return ((Double)map.get("bin width")).doubleValue();
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 292 */     return this.list.size();
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 303 */     Map map = this.list.get(series);
/* 304 */     return (Comparable)map.get("key");
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 315 */     return getBins(series).size();
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 330 */     List bins = getBins(series);
/* 331 */     HistogramBin bin = bins.get(item);
/* 332 */     double x = (bin.getStartBoundary() + bin.getEndBoundary()) / 2.0D;
/* 333 */     return new Double(x);
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 346 */     List bins = getBins(series);
/* 347 */     HistogramBin bin = bins.get(item);
/* 348 */     double total = getTotal(series);
/* 349 */     double binWidth = getBinWidth(series);
/* 351 */     if (this.type == HistogramType.FREQUENCY)
/* 352 */       return new Double(bin.getCount()); 
/* 354 */     if (this.type == HistogramType.RELATIVE_FREQUENCY)
/* 355 */       return new Double(bin.getCount() / total); 
/* 357 */     if (this.type == HistogramType.SCALE_AREA_TO_1)
/* 358 */       return new Double(bin.getCount() / binWidth * total); 
/* 361 */     throw new IllegalStateException();
/*     */   }
/*     */   
/*     */   public Number getStartX(int series, int item) {
/* 374 */     List bins = getBins(series);
/* 375 */     HistogramBin bin = bins.get(item);
/* 376 */     return new Double(bin.getStartBoundary());
/*     */   }
/*     */   
/*     */   public Number getEndX(int series, int item) {
/* 388 */     List bins = getBins(series);
/* 389 */     HistogramBin bin = bins.get(item);
/* 390 */     return new Double(bin.getEndBoundary());
/*     */   }
/*     */   
/*     */   public Number getStartY(int series, int item) {
/* 404 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public Number getEndY(int series, int item) {
/* 418 */     return getY(series, item);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 429 */     if (obj == this)
/* 430 */       return true; 
/* 432 */     if (!(obj instanceof HistogramDataset))
/* 433 */       return false; 
/* 435 */     HistogramDataset that = (HistogramDataset)obj;
/* 436 */     if (!ObjectUtilities.equal(this.type, that.type))
/* 437 */       return false; 
/* 439 */     if (!ObjectUtilities.equal(this.list, that.list))
/* 440 */       return false; 
/* 442 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 453 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\HistogramDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */