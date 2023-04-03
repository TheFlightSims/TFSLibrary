/*    */ package com.vividsolutions.jts.operation.valid;
/*    */ 
/*    */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.LinearRing;
/*    */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*    */ import com.vividsolutions.jts.util.Assert;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SimpleNestedRingTester {
/*    */   private GeometryGraph graph;
/*    */   
/* 53 */   private List rings = new ArrayList();
/*    */   
/*    */   private Coordinate nestedPt;
/*    */   
/*    */   public SimpleNestedRingTester(GeometryGraph graph) {
/* 58 */     this.graph = graph;
/*    */   }
/*    */   
/*    */   public void add(LinearRing ring) {
/* 63 */     this.rings.add(ring);
/*    */   }
/*    */   
/*    */   public Coordinate getNestedPoint() {
/* 66 */     return this.nestedPt;
/*    */   }
/*    */   
/*    */   public boolean isNonNested() {
/* 70 */     for (int i = 0; i < this.rings.size(); i++) {
/* 71 */       LinearRing innerRing = this.rings.get(i);
/* 72 */       Coordinate[] innerRingPts = innerRing.getCoordinates();
/* 74 */       for (int j = 0; j < this.rings.size(); j++) {
/* 75 */         LinearRing searchRing = this.rings.get(j);
/* 76 */         Coordinate[] searchRingPts = searchRing.getCoordinates();
/* 78 */         if (innerRing != searchRing)
/* 81 */           if (innerRing.getEnvelopeInternal().intersects(searchRing.getEnvelopeInternal())) {
/* 84 */             Coordinate innerRingPt = IsValidOp.findPtNotNode(innerRingPts, searchRing, this.graph);
/* 85 */             Assert.isTrue((innerRingPt != null), "Unable to find a ring point not a node of the search ring");
/* 88 */             boolean isInside = CGAlgorithms.isPointInRing(innerRingPt, searchRingPts);
/* 89 */             if (isInside) {
/* 90 */               this.nestedPt = innerRingPt;
/* 91 */               return false;
/*    */             } 
/*    */           }  
/*    */       } 
/*    */     } 
/* 95 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\valid\SimpleNestedRingTester.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */