/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.SeriesChangeListener;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class MatrixSeriesCollection extends AbstractXYZDataset implements XYZDataset, Serializable {
/*     */   private static final long serialVersionUID = -3197705779242543945L;
/*     */   
/*     */   public MatrixSeriesCollection() {
/*  73 */     this(null);
/*     */   }
/*     */   
/*  83 */   private List seriesList = new ArrayList();
/*     */   
/*     */   public MatrixSeriesCollection(MatrixSeries series) {
/*  85 */     if (series != null) {
/*  86 */       this.seriesList.add(series);
/*  87 */       series.addChangeListener((SeriesChangeListener)this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getItemCount(int seriesIndex) {
/*  99 */     return getSeries(seriesIndex).getItemCount();
/*     */   }
/*     */   
/*     */   public MatrixSeries getSeries(int seriesIndex) {
/* 113 */     if (seriesIndex < 0 || seriesIndex > getSeriesCount())
/* 114 */       throw new IllegalArgumentException("Index outside valid range."); 
/* 117 */     MatrixSeries series = this.seriesList.get(seriesIndex);
/* 119 */     return series;
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 129 */     return this.seriesList.size();
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int seriesIndex) {
/* 141 */     return getSeries(seriesIndex).getKey();
/*     */   }
/*     */   
/*     */   public Number getX(int seriesIndex, int itemIndex) {
/* 157 */     MatrixSeries series = this.seriesList.get(seriesIndex);
/* 158 */     int x = series.getItemColumn(itemIndex);
/* 160 */     return new Integer(x);
/*     */   }
/*     */   
/*     */   public Number getY(int seriesIndex, int itemIndex) {
/* 176 */     MatrixSeries series = this.seriesList.get(seriesIndex);
/* 177 */     int y = series.getItemRow(itemIndex);
/* 179 */     return new Integer(y);
/*     */   }
/*     */   
/*     */   public Number getZ(int seriesIndex, int itemIndex) {
/* 195 */     MatrixSeries series = this.seriesList.get(seriesIndex);
/* 196 */     Number z = series.getItem(itemIndex);
/* 197 */     return z;
/*     */   }
/*     */   
/*     */   public void addSeries(MatrixSeries series) {
/* 213 */     if (series == null)
/* 214 */       throw new IllegalArgumentException("Cannot add null series."); 
/* 218 */     this.seriesList.add(series);
/* 219 */     series.addChangeListener((SeriesChangeListener)this);
/* 220 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 232 */     if (obj == null)
/* 233 */       return false; 
/* 236 */     if (obj == this)
/* 237 */       return true; 
/* 240 */     if (obj instanceof MatrixSeriesCollection) {
/* 241 */       MatrixSeriesCollection c = (MatrixSeriesCollection)obj;
/* 243 */       return ObjectUtilities.equal(this.seriesList, c.seriesList);
/*     */     } 
/* 246 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 255 */     return (this.seriesList != null) ? this.seriesList.hashCode() : 0;
/*     */   }
/*     */   
/*     */   public void removeAllSeries() {
/* 267 */     for (int i = 0; i < this.seriesList.size(); i++) {
/* 268 */       MatrixSeries series = this.seriesList.get(i);
/* 269 */       series.removeChangeListener((SeriesChangeListener)this);
/*     */     } 
/* 273 */     this.seriesList.clear();
/* 274 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeSeries(MatrixSeries series) {
/* 290 */     if (series == null)
/* 291 */       throw new IllegalArgumentException("Cannot remove null series."); 
/* 295 */     if (this.seriesList.contains(series)) {
/* 296 */       series.removeChangeListener((SeriesChangeListener)this);
/* 297 */       this.seriesList.remove(series);
/* 298 */       fireDatasetChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeSeries(int seriesIndex) {
/* 314 */     if (seriesIndex < 0 || seriesIndex > getSeriesCount())
/* 315 */       throw new IllegalArgumentException("Index outside valid range."); 
/* 319 */     MatrixSeries series = this.seriesList.get(seriesIndex);
/* 320 */     series.removeChangeListener((SeriesChangeListener)this);
/* 321 */     this.seriesList.remove(seriesIndex);
/* 322 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\MatrixSeriesCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */