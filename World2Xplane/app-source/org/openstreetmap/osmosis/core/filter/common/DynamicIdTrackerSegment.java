/*     */ package org.openstreetmap.osmosis.core.filter.common;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class DynamicIdTrackerSegment implements Comparable<DynamicIdTrackerSegment>, Iterable<Long> {
/*     */   private static final int BITSET_THRESHOLD = 32;
/*     */   
/*     */   private long base;
/*     */   
/*     */   private char setCount;
/*     */   
/*     */   private IdTracker idTracker;
/*     */   
/*     */   private boolean bitsetEnabled;
/*     */   
/*     */   public DynamicIdTrackerSegment(long base) {
/*  29 */     this.base = base;
/*  31 */     this.idTracker = new ListIdTracker();
/*  32 */     this.bitsetEnabled = false;
/*     */   }
/*     */   
/*     */   public long getBase() {
/*  42 */     return this.base;
/*     */   }
/*     */   
/*     */   public long getSetCount() {
/*  52 */     return this.setCount;
/*     */   }
/*     */   
/*     */   public boolean get(long id) {
/*  64 */     return this.idTracker.get(id);
/*     */   }
/*     */   
/*     */   public void set(long id) {
/*  75 */     if (!this.idTracker.get(id)) {
/*  76 */       this.idTracker.set(id);
/*  77 */       this.setCount = (char)(this.setCount + 1);
/*  80 */       if (!this.bitsetEnabled && this.setCount > ' ') {
/*  83 */         IdTracker bitsetIdTracker = new BitSetIdTracker();
/*  84 */         bitsetIdTracker.setAll(this.idTracker);
/*  86 */         this.idTracker = bitsetIdTracker;
/*  87 */         this.bitsetEnabled = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int compareTo(DynamicIdTrackerSegment o) {
/* 100 */     long result = this.base - o.base;
/* 102 */     if (result == 0L)
/* 103 */       return 0; 
/* 104 */     if (result > 0L)
/* 105 */       return 1; 
/* 107 */     return -1;
/*     */   }
/*     */   
/*     */   public Iterator<Long> iterator() {
/* 117 */     return this.idTracker.iterator();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\filter\common\DynamicIdTrackerSegment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */