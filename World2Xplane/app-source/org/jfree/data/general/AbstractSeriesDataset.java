/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractSeriesDataset extends AbstractDataset implements SeriesDataset, SeriesChangeListener, Serializable {
/*     */   private static final long serialVersionUID = -6074996219705033171L;
/*     */   
/*     */   public abstract int getSeriesCount();
/*     */   
/*     */   public abstract Comparable getSeriesKey(int paramInt);
/*     */   
/*     */   public int indexOf(Comparable seriesKey) {
/*  94 */     int seriesCount = getSeriesCount();
/*  95 */     for (int s = 0; s < seriesCount; s++) {
/*  96 */       if (getSeriesKey(s).equals(seriesKey))
/*  97 */         return s; 
/*     */     } 
/* 100 */     return -1;
/*     */   }
/*     */   
/*     */   public void seriesChanged(SeriesChangeEvent event) {
/* 109 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\AbstractSeriesDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */