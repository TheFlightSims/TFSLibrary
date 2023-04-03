/*     */ package org.openstreetmap.osmosis.core.sort.common;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*     */ 
/*     */ public class MergingIterator<DataType> implements ReleasableIterator<DataType> {
/*     */   private List<ReleasableIterator<DataType>> sources;
/*     */   
/*     */   private Comparator<DataType> comparator;
/*     */   
/*     */   private List<DataType> sourceData;
/*     */   
/*     */   public MergingIterator(List<ReleasableIterator<DataType>> sources, Comparator<DataType> comparator) {
/*  35 */     this.sources = new ArrayList<ReleasableIterator<DataType>>(sources);
/*  36 */     this.comparator = comparator;
/*     */   }
/*     */   
/*     */   private void initialize() {
/*  44 */     if (this.sourceData == null) {
/*  46 */       this.sourceData = new ArrayList<DataType>(this.sources.size());
/*  47 */       for (int sourceIndex = 0; sourceIndex < this.sources.size(); ) {
/*  50 */         ReleasableIterator<DataType> source = this.sources.get(sourceIndex);
/*  52 */         if (source.hasNext()) {
/*  53 */           this.sourceData.add((DataType)source.next());
/*  54 */           sourceIndex++;
/*     */           continue;
/*     */         } 
/*  56 */         ((ReleasableIterator)this.sources.remove(sourceIndex)).release();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  67 */     initialize();
/*  69 */     return (this.sourceData.size() > 0);
/*     */   }
/*     */   
/*     */   public DataType next() {
/*  81 */     initialize();
/*  83 */     if (!hasNext())
/*  84 */       throw new NoSuchElementException(); 
/*  87 */     DataType dataMinimum = this.sourceData.get(0);
/*  88 */     int indexMinimum = 0;
/*  91 */     for (int indexCurrent = 1; indexCurrent < this.sources.size(); indexCurrent++) {
/*  92 */       DataType dataCurrent = this.sourceData.get(indexCurrent);
/*  95 */       if (this.comparator.compare(dataMinimum, dataCurrent) > 0) {
/*  96 */         dataMinimum = dataCurrent;
/*  97 */         indexMinimum = indexCurrent;
/*     */       } 
/*     */     } 
/* 103 */     ReleasableIterator<DataType> source = this.sources.get(indexMinimum);
/* 104 */     if (source.hasNext()) {
/* 105 */       this.sourceData.set(indexMinimum, (DataType)source.next());
/*     */     } else {
/* 107 */       ((ReleasableIterator)this.sources.remove(indexMinimum)).release();
/* 108 */       this.sourceData.remove(indexMinimum);
/*     */     } 
/* 111 */     return dataMinimum;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void release() {
/* 127 */     for (ReleasableIterator<DataType> source : this.sources)
/* 128 */       source.release(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\common\MergingIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */