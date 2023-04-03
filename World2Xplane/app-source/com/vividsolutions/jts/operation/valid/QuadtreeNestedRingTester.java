/*     */ package com.vividsolutions.jts.operation.valid;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*     */ import com.vividsolutions.jts.index.quadtree.Quadtree;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class QuadtreeNestedRingTester {
/*     */   private GeometryGraph graph;
/*     */   
/*  54 */   private List rings = new ArrayList();
/*     */   
/*  55 */   private Envelope totalEnv = new Envelope();
/*     */   
/*     */   private Quadtree quadtree;
/*     */   
/*     */   private Coordinate nestedPt;
/*     */   
/*     */   public QuadtreeNestedRingTester(GeometryGraph graph) {
/*  61 */     this.graph = graph;
/*     */   }
/*     */   
/*     */   public Coordinate getNestedPoint() {
/*  64 */     return this.nestedPt;
/*     */   }
/*     */   
/*     */   public void add(LinearRing ring) {
/*  68 */     this.rings.add(ring);
/*  69 */     this.totalEnv.expandToInclude(ring.getEnvelopeInternal());
/*     */   }
/*     */   
/*     */   public boolean isNonNested() {
/*  74 */     buildQuadtree();
/*  76 */     for (int i = 0; i < this.rings.size(); i++) {
/*  77 */       LinearRing innerRing = this.rings.get(i);
/*  78 */       Coordinate[] innerRingPts = innerRing.getCoordinates();
/*  80 */       List<LinearRing> results = this.quadtree.query(innerRing.getEnvelopeInternal());
/*  82 */       for (int j = 0; j < results.size(); j++) {
/*  83 */         LinearRing searchRing = results.get(j);
/*  84 */         Coordinate[] searchRingPts = searchRing.getCoordinates();
/*  86 */         if (innerRing != searchRing)
/*  89 */           if (innerRing.getEnvelopeInternal().intersects(searchRing.getEnvelopeInternal())) {
/*  92 */             Coordinate innerRingPt = IsValidOp.findPtNotNode(innerRingPts, searchRing, this.graph);
/*  93 */             Assert.isTrue((innerRingPt != null), "Unable to find a ring point not a node of the search ring");
/*  96 */             boolean isInside = CGAlgorithms.isPointInRing(innerRingPt, searchRingPts);
/*  97 */             if (isInside) {
/*  98 */               this.nestedPt = innerRingPt;
/*  99 */               return false;
/*     */             } 
/*     */           }  
/*     */       } 
/*     */     } 
/* 103 */     return true;
/*     */   }
/*     */   
/*     */   private void buildQuadtree() {
/* 108 */     this.quadtree = new Quadtree();
/* 110 */     for (int i = 0; i < this.rings.size(); i++) {
/* 111 */       LinearRing ring = this.rings.get(i);
/* 112 */       Envelope env = ring.getEnvelopeInternal();
/* 113 */       this.quadtree.insert(env, ring);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\valid\QuadtreeNestedRingTester.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */