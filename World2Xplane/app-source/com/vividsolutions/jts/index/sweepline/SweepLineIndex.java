/*     */ package com.vividsolutions.jts.index.sweepline;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SweepLineIndex {
/*  47 */   List events = new ArrayList();
/*     */   
/*     */   private boolean indexBuilt;
/*     */   
/*     */   private int nOverlaps;
/*     */   
/*     */   public void add(SweepLineInterval sweepInt) {
/*  57 */     SweepLineEvent insertEvent = new SweepLineEvent(sweepInt.getMin(), null, sweepInt);
/*  58 */     this.events.add(insertEvent);
/*  59 */     this.events.add(new SweepLineEvent(sweepInt.getMax(), insertEvent, sweepInt));
/*     */   }
/*     */   
/*     */   private void buildIndex() {
/*  69 */     if (this.indexBuilt)
/*     */       return; 
/*  70 */     Collections.sort(this.events);
/*  71 */     for (int i = 0; i < this.events.size(); i++) {
/*  73 */       SweepLineEvent ev = this.events.get(i);
/*  74 */       if (ev.isDelete())
/*  75 */         ev.getInsertEvent().setDeleteEventIndex(i); 
/*     */     } 
/*  78 */     this.indexBuilt = true;
/*     */   }
/*     */   
/*     */   public void computeOverlaps(SweepLineOverlapAction action) {
/*  83 */     this.nOverlaps = 0;
/*  84 */     buildIndex();
/*  86 */     for (int i = 0; i < this.events.size(); i++) {
/*  88 */       SweepLineEvent ev = this.events.get(i);
/*  89 */       if (ev.isInsert())
/*  90 */         processOverlaps(i, ev.getDeleteEventIndex(), ev.getInterval(), action); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processOverlaps(int start, int end, SweepLineInterval s0, SweepLineOverlapAction action) {
/* 102 */     for (int i = start; i < end; i++) {
/* 103 */       SweepLineEvent ev = this.events.get(i);
/* 104 */       if (ev.isInsert()) {
/* 105 */         SweepLineInterval s1 = ev.getInterval();
/* 106 */         action.overlap(s0, s1);
/* 107 */         this.nOverlaps++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\sweepline\SweepLineIndex.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */