/*     */ package org.openstreetmap.osmosis.core.filter.common;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.openstreetmap.osmosis.core.util.LongAsInt;
/*     */ 
/*     */ public class BitSetIdTracker implements IdTracker {
/*  38 */   BitSet positiveSet = new BitSet();
/*     */   
/*  39 */   ListIdTracker negativeSet = new ListIdTracker();
/*     */   
/*     */   public void set(long id) {
/*  49 */     int intId = LongAsInt.longToInt(id);
/*  51 */     if (intId >= 0) {
/*  52 */       this.positiveSet.set(intId);
/*     */     } else {
/*  54 */       this.negativeSet.set(intId);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean get(long id) {
/*     */     boolean result;
/*  66 */     int intId = LongAsInt.longToInt(id);
/*  68 */     if (intId >= 0) {
/*  69 */       result = this.positiveSet.get(intId);
/*     */     } else {
/*  71 */       result = this.negativeSet.get(intId);
/*     */     } 
/*  74 */     return result;
/*     */   }
/*     */   
/*     */   public Iterator<Long> iterator() {
/*  83 */     return new IdIterator();
/*     */   }
/*     */   
/*     */   public void setAll(IdTracker idTracker) {
/*  92 */     for (Long id : idTracker)
/*  93 */       set(id.longValue()); 
/*     */   }
/*     */   
/*     */   private class IdIterator implements Iterator<Long> {
/*     */     private boolean readingPositive = false;
/*     */     
/*     */     private long nextId;
/*     */     
/*     */     private boolean nextIdAvailable = false;
/*     */     
/*     */     private Iterator<Long> negativeIterator;
/*     */     
/* 125 */     private int positiveOffset = 0;
/*     */     
/*     */     public boolean hasNext() {
/* 134 */       if (!this.nextIdAvailable) {
/* 135 */         if (!this.readingPositive) {
/* 137 */           if (this.negativeIterator == null)
/* 138 */             this.negativeIterator = BitSetIdTracker.this.negativeSet.iterator(); 
/* 143 */           if (this.negativeIterator.hasNext()) {
/* 144 */             this.nextId = ((Long)this.negativeIterator.next()).longValue();
/* 145 */             this.nextIdAvailable = true;
/*     */           } else {
/* 147 */             this.negativeIterator = null;
/* 148 */             this.readingPositive = true;
/*     */           } 
/*     */         } 
/* 152 */         if (this.readingPositive) {
/* 155 */           int nextBitOffset = BitSetIdTracker.this.positiveSet.nextSetBit(this.positiveOffset);
/* 157 */           if (nextBitOffset >= 0) {
/* 158 */             this.nextId = nextBitOffset;
/* 159 */             this.nextIdAvailable = true;
/* 160 */             this.positiveOffset = nextBitOffset + 1;
/*     */           } 
/*     */         } 
/*     */       } 
/* 165 */       return this.nextIdAvailable;
/*     */     }
/*     */     
/*     */     public Long next() {
/* 174 */       if (!hasNext())
/* 175 */         throw new NoSuchElementException(); 
/* 178 */       this.nextIdAvailable = false;
/* 180 */       return Long.valueOf(this.nextId);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 189 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\filter\common\BitSetIdTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */