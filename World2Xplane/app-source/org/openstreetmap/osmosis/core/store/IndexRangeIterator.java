/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class IndexRangeIterator<K, T extends IndexElement<K>> implements Iterator<T> {
/*     */   private Iterator<T> source;
/*     */   
/*     */   private K beginKey;
/*     */   
/*     */   private K endKey;
/*     */   
/*     */   private Comparator<K> ordering;
/*     */   
/*     */   private boolean nextRecordAvailable;
/*     */   
/*     */   private T nextRecord;
/*     */   
/*     */   public IndexRangeIterator(Iterator<T> source, K beginKey, K endKey, Comparator<K> ordering) {
/*  42 */     this.source = source;
/*  43 */     this.beginKey = beginKey;
/*  44 */     this.endKey = endKey;
/*  45 */     this.ordering = ordering;
/*  47 */     this.nextRecordAvailable = false;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  56 */     while (!this.nextRecordAvailable) {
/*  59 */       if (!this.source.hasNext())
/*     */         break; 
/*  64 */       this.nextRecord = this.source.next();
/*  65 */       K key = this.nextRecord.getKey();
/*  68 */       if (this.ordering.compare(key, this.beginKey) >= 0) {
/*  70 */         if (this.ordering.compare(this.nextRecord.getKey(), this.endKey) > 0)
/*     */           break; 
/*  74 */         this.nextRecordAvailable = true;
/*     */       } 
/*     */     } 
/*  78 */     return this.nextRecordAvailable;
/*     */   }
/*     */   
/*     */   public T next() {
/*  87 */     if (!hasNext())
/*  88 */       throw new NoSuchElementException(); 
/*  91 */     this.nextRecordAvailable = false;
/*  93 */     return this.nextRecord;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 102 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\IndexRangeIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */