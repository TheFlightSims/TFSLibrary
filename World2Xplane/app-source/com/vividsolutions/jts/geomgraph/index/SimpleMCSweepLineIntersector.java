/*     */ package com.vividsolutions.jts.geomgraph.index;
/*     */ 
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SimpleMCSweepLineIntersector extends EdgeSetIntersector {
/*  58 */   List events = new ArrayList();
/*     */   
/*     */   int nOverlaps;
/*     */   
/*     */   public void computeIntersections(List edges, SegmentIntersector si, boolean testAllSegments) {
/*  71 */     if (testAllSegments) {
/*  72 */       add(edges, (Object)null);
/*     */     } else {
/*  74 */       add(edges);
/*     */     } 
/*  75 */     computeIntersections(si);
/*     */   }
/*     */   
/*     */   public void computeIntersections(List edges0, List edges1, SegmentIntersector si) {
/*  80 */     add(edges0, edges0);
/*  81 */     add(edges1, edges1);
/*  82 */     computeIntersections(si);
/*     */   }
/*     */   
/*     */   private void add(List edges) {
/*  87 */     for (Iterator<Edge> i = edges.iterator(); i.hasNext(); ) {
/*  88 */       Edge edge = i.next();
/*  90 */       add(edge, edge);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void add(List edges, Object edgeSet) {
/*  95 */     for (Iterator<Edge> i = edges.iterator(); i.hasNext(); ) {
/*  96 */       Edge edge = i.next();
/*  97 */       add(edge, edgeSet);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void add(Edge edge, Object edgeSet) {
/* 103 */     MonotoneChainEdge mce = edge.getMonotoneChainEdge();
/* 104 */     int[] startIndex = mce.getStartIndexes();
/* 105 */     for (int i = 0; i < startIndex.length - 1; i++) {
/* 106 */       MonotoneChain mc = new MonotoneChain(mce, i);
/* 107 */       SweepLineEvent insertEvent = new SweepLineEvent(edgeSet, mce.getMinX(i), mc);
/* 108 */       this.events.add(insertEvent);
/* 109 */       this.events.add(new SweepLineEvent(mce.getMaxX(i), insertEvent));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void prepareEvents() {
/* 120 */     Collections.sort(this.events);
/* 122 */     for (int i = 0; i < this.events.size(); i++) {
/* 124 */       SweepLineEvent ev = this.events.get(i);
/* 125 */       if (ev.isDelete())
/* 126 */         ev.getInsertEvent().setDeleteEventIndex(i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeIntersections(SegmentIntersector si) {
/* 133 */     this.nOverlaps = 0;
/* 134 */     prepareEvents();
/* 136 */     for (int i = 0; i < this.events.size(); i++) {
/* 138 */       SweepLineEvent ev = this.events.get(i);
/* 139 */       if (ev.isInsert())
/* 140 */         processOverlaps(i, ev.getDeleteEventIndex(), ev, si); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processOverlaps(int start, int end, SweepLineEvent ev0, SegmentIntersector si) {
/* 147 */     MonotoneChain mc0 = (MonotoneChain)ev0.getObject();
/* 153 */     for (int i = start; i < end; i++) {
/* 154 */       SweepLineEvent ev1 = this.events.get(i);
/* 155 */       if (ev1.isInsert()) {
/* 156 */         MonotoneChain mc1 = (MonotoneChain)ev1.getObject();
/* 158 */         if (!ev0.isSameLabel(ev1)) {
/* 159 */           mc0.computeIntersections(mc1, si);
/* 160 */           this.nOverlaps++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\index\SimpleMCSweepLineIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */