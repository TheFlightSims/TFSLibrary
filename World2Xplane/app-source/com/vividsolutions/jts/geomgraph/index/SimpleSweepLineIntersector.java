/*     */ package com.vividsolutions.jts.geomgraph.index;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SimpleSweepLineIntersector extends EdgeSetIntersector {
/*  57 */   List events = new ArrayList();
/*     */   
/*     */   int nOverlaps;
/*     */   
/*     */   public void computeIntersections(List edges, SegmentIntersector si, boolean testAllSegments) {
/*  66 */     if (testAllSegments) {
/*  67 */       add(edges, (Object)null);
/*     */     } else {
/*  69 */       add(edges);
/*     */     } 
/*  70 */     computeIntersections(si);
/*     */   }
/*     */   
/*     */   public void computeIntersections(List edges0, List edges1, SegmentIntersector si) {
/*  75 */     add(edges0, edges0);
/*  76 */     add(edges1, edges1);
/*  77 */     computeIntersections(si);
/*     */   }
/*     */   
/*     */   private void add(List edges) {
/*  82 */     for (Iterator<Edge> i = edges.iterator(); i.hasNext(); ) {
/*  83 */       Edge edge = i.next();
/*  85 */       add(edge, edge);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void add(List edges, Object edgeSet) {
/*  90 */     for (Iterator<Edge> i = edges.iterator(); i.hasNext(); ) {
/*  91 */       Edge edge = i.next();
/*  92 */       add(edge, edgeSet);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void add(Edge edge, Object edgeSet) {
/*  99 */     Coordinate[] pts = edge.getCoordinates();
/* 100 */     for (int i = 0; i < pts.length - 1; i++) {
/* 101 */       SweepLineSegment ss = new SweepLineSegment(edge, i);
/* 102 */       SweepLineEvent insertEvent = new SweepLineEvent(edgeSet, ss.getMinX(), null);
/* 103 */       this.events.add(insertEvent);
/* 104 */       this.events.add(new SweepLineEvent(ss.getMaxX(), insertEvent));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void prepareEvents() {
/* 115 */     Collections.sort(this.events);
/* 117 */     for (int i = 0; i < this.events.size(); i++) {
/* 119 */       SweepLineEvent ev = this.events.get(i);
/* 120 */       if (ev.isDelete())
/* 121 */         ev.getInsertEvent().setDeleteEventIndex(i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeIntersections(SegmentIntersector si) {
/* 128 */     this.nOverlaps = 0;
/* 129 */     prepareEvents();
/* 131 */     for (int i = 0; i < this.events.size(); i++) {
/* 133 */       SweepLineEvent ev = this.events.get(i);
/* 134 */       if (ev.isInsert())
/* 135 */         processOverlaps(i, ev.getDeleteEventIndex(), ev, si); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processOverlaps(int start, int end, SweepLineEvent ev0, SegmentIntersector si) {
/* 142 */     SweepLineSegment ss0 = (SweepLineSegment)ev0.getObject();
/* 148 */     for (int i = start; i < end; i++) {
/* 149 */       SweepLineEvent ev1 = this.events.get(i);
/* 150 */       if (ev1.isInsert()) {
/* 151 */         SweepLineSegment ss1 = (SweepLineSegment)ev1.getObject();
/* 153 */         if (!ev0.isSameLabel(ev1)) {
/* 154 */           ss0.computeIntersections(ss1, si);
/* 155 */           this.nOverlaps++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\index\SimpleSweepLineIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */