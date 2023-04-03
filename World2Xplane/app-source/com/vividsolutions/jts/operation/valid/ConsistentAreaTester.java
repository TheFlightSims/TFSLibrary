/*     */ package com.vividsolutions.jts.operation.valid;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*     */ import com.vividsolutions.jts.geomgraph.index.SegmentIntersector;
/*     */ import com.vividsolutions.jts.operation.relate.EdgeEndBundle;
/*     */ import com.vividsolutions.jts.operation.relate.RelateNode;
/*     */ import com.vividsolutions.jts.operation.relate.RelateNodeGraph;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class ConsistentAreaTester {
/*  68 */   private final LineIntersector li = (LineIntersector)new RobustLineIntersector();
/*     */   
/*     */   private GeometryGraph geomGraph;
/*     */   
/*  70 */   private RelateNodeGraph nodeGraph = new RelateNodeGraph();
/*     */   
/*     */   private Coordinate invalidPoint;
/*     */   
/*     */   public ConsistentAreaTester(GeometryGraph geomGraph) {
/*  82 */     this.geomGraph = geomGraph;
/*     */   }
/*     */   
/*     */   public Coordinate getInvalidPoint() {
/*  88 */     return this.invalidPoint;
/*     */   }
/*     */   
/*     */   public boolean isNodeConsistentArea() {
/* 101 */     SegmentIntersector intersector = this.geomGraph.computeSelfNodes(this.li, true);
/* 102 */     if (intersector.hasProperIntersection()) {
/* 103 */       this.invalidPoint = intersector.getProperIntersectionPoint();
/* 104 */       return false;
/*     */     } 
/* 107 */     this.nodeGraph.build(this.geomGraph);
/* 109 */     return isNodeEdgeAreaLabelsConsistent();
/*     */   }
/*     */   
/*     */   private boolean isNodeEdgeAreaLabelsConsistent() {
/* 120 */     for (Iterator<RelateNode> nodeIt = this.nodeGraph.getNodeIterator(); nodeIt.hasNext(); ) {
/* 121 */       RelateNode node = nodeIt.next();
/* 122 */       if (!node.getEdges().isAreaLabelsConsistent(this.geomGraph)) {
/* 123 */         this.invalidPoint = (Coordinate)node.getCoordinate().clone();
/* 124 */         return false;
/*     */       } 
/*     */     } 
/* 127 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasDuplicateRings() {
/* 147 */     for (Iterator<RelateNode> nodeIt = this.nodeGraph.getNodeIterator(); nodeIt.hasNext(); ) {
/* 148 */       RelateNode node = nodeIt.next();
/* 149 */       for (Iterator<EdgeEndBundle> i = node.getEdges().iterator(); i.hasNext(); ) {
/* 150 */         EdgeEndBundle eeb = i.next();
/* 151 */         if (eeb.getEdgeEnds().size() > 1) {
/* 152 */           this.invalidPoint = eeb.getEdge().getCoordinate(0);
/* 153 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 157 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\valid\ConsistentAreaTester.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */