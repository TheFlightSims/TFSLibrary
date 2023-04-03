/*     */ package com.vividsolutions.jts.operation.valid;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*     */ import com.vividsolutions.jts.index.sweepline.SweepLineIndex;
/*     */ import com.vividsolutions.jts.index.sweepline.SweepLineInterval;
/*     */ import com.vividsolutions.jts.index.sweepline.SweepLineOverlapAction;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SweeplineNestedRingTester {
/*     */   private GeometryGraph graph;
/*     */   
/*  54 */   private List rings = new ArrayList();
/*     */   
/*     */   private SweepLineIndex sweepLine;
/*     */   
/*  57 */   private Coordinate nestedPt = null;
/*     */   
/*     */   public SweeplineNestedRingTester(GeometryGraph graph) {
/*  61 */     this.graph = graph;
/*     */   }
/*     */   
/*     */   public Coordinate getNestedPoint() {
/*  64 */     return this.nestedPt;
/*     */   }
/*     */   
/*     */   public void add(LinearRing ring) {
/*  68 */     this.rings.add(ring);
/*     */   }
/*     */   
/*     */   public boolean isNonNested() {
/*  73 */     buildIndex();
/*  75 */     OverlapAction action = new OverlapAction();
/*  77 */     this.sweepLine.computeOverlaps(action);
/*  78 */     return action.isNonNested;
/*     */   }
/*     */   
/*     */   private void buildIndex() {
/*  83 */     this.sweepLine = new SweepLineIndex();
/*  85 */     for (int i = 0; i < this.rings.size(); i++) {
/*  86 */       LinearRing ring = this.rings.get(i);
/*  87 */       Envelope env = ring.getEnvelopeInternal();
/*  88 */       SweepLineInterval sweepInt = new SweepLineInterval(env.getMinX(), env.getMaxX(), ring);
/*  89 */       this.sweepLine.add(sweepInt);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isInside(LinearRing innerRing, LinearRing searchRing) {
/*  95 */     Coordinate[] innerRingPts = innerRing.getCoordinates();
/*  96 */     Coordinate[] searchRingPts = searchRing.getCoordinates();
/*  98 */     if (!innerRing.getEnvelopeInternal().intersects(searchRing.getEnvelopeInternal()))
/*  99 */       return false; 
/* 101 */     Coordinate innerRingPt = IsValidOp.findPtNotNode(innerRingPts, searchRing, this.graph);
/* 102 */     Assert.isTrue((innerRingPt != null), "Unable to find a ring point not a node of the search ring");
/* 104 */     boolean isInside = CGAlgorithms.isPointInRing(innerRingPt, searchRingPts);
/* 105 */     if (isInside) {
/* 106 */       this.nestedPt = innerRingPt;
/* 107 */       return true;
/*     */     } 
/* 109 */     return false;
/*     */   }
/*     */   
/*     */   class OverlapAction implements SweepLineOverlapAction {
/*     */     boolean isNonNested = true;
/*     */     
/*     */     public void overlap(SweepLineInterval s0, SweepLineInterval s1) {
/* 120 */       LinearRing innerRing = (LinearRing)s0.getItem();
/* 121 */       LinearRing searchRing = (LinearRing)s1.getItem();
/* 122 */       if (innerRing == searchRing)
/*     */         return; 
/* 124 */       if (SweeplineNestedRingTester.this.isInside(innerRing, searchRing))
/* 125 */         this.isNonNested = false; 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\valid\SweeplineNestedRingTester.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */