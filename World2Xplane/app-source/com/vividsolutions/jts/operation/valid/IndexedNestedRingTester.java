/*     */ package com.vividsolutions.jts.operation.valid;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*     */ import com.vividsolutions.jts.index.SpatialIndex;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class IndexedNestedRingTester {
/*     */   private GeometryGraph graph;
/*     */   
/*  53 */   private List rings = new ArrayList();
/*     */   
/*  54 */   private Envelope totalEnv = new Envelope();
/*     */   
/*     */   private SpatialIndex index;
/*     */   
/*     */   private Coordinate nestedPt;
/*     */   
/*     */   public IndexedNestedRingTester(GeometryGraph graph) {
/*  60 */     this.graph = graph;
/*     */   }
/*     */   
/*     */   public Coordinate getNestedPoint() {
/*  63 */     return this.nestedPt;
/*     */   }
/*     */   
/*     */   public void add(LinearRing ring) {
/*  67 */     this.rings.add(ring);
/*  68 */     this.totalEnv.expandToInclude(ring.getEnvelopeInternal());
/*     */   }
/*     */   
/*     */   public boolean isNonNested() {
/*  73 */     buildIndex();
/*  75 */     for (int i = 0; i < this.rings.size(); i++) {
/*  76 */       LinearRing innerRing = this.rings.get(i);
/*  77 */       Coordinate[] innerRingPts = innerRing.getCoordinates();
/*  79 */       List<LinearRing> results = this.index.query(innerRing.getEnvelopeInternal());
/*  81 */       for (int j = 0; j < results.size(); j++) {
/*  82 */         LinearRing searchRing = results.get(j);
/*  83 */         Coordinate[] searchRingPts = searchRing.getCoordinates();
/*  85 */         if (innerRing != searchRing)
/*  88 */           if (innerRing.getEnvelopeInternal().intersects(searchRing.getEnvelopeInternal())) {
/*  91 */             Coordinate innerRingPt = IsValidOp.findPtNotNode(innerRingPts, searchRing, this.graph);
/* 103 */             if (innerRingPt != null) {
/* 106 */               boolean isInside = CGAlgorithms.isPointInRing(innerRingPt, searchRingPts);
/* 107 */               if (isInside) {
/* 108 */                 this.nestedPt = innerRingPt;
/* 109 */                 return false;
/*     */               } 
/*     */             } 
/*     */           }  
/*     */       } 
/*     */     } 
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   private void buildIndex() {
/* 118 */     this.index = (SpatialIndex)new STRtree();
/* 120 */     for (int i = 0; i < this.rings.size(); i++) {
/* 121 */       LinearRing ring = this.rings.get(i);
/* 122 */       Envelope env = ring.getEnvelopeInternal();
/* 123 */       this.index.insert(env, ring);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\valid\IndexedNestedRingTester.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */