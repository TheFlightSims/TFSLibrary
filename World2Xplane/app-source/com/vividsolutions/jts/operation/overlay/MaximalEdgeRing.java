/*     */ package com.vividsolutions.jts.operation.overlay;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdgeStar;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeRing;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MaximalEdgeRing extends EdgeRing {
/*     */   public MaximalEdgeRing(DirectedEdge start, GeometryFactory geometryFactory) {
/*  65 */     super(start, geometryFactory);
/*     */   }
/*     */   
/*     */   public DirectedEdge getNext(DirectedEdge de) {
/*  70 */     return de.getNext();
/*     */   }
/*     */   
/*     */   public void setEdgeRing(DirectedEdge de, EdgeRing er) {
/*  74 */     de.setEdgeRing(er);
/*     */   }
/*     */   
/*     */   public void linkDirectedEdgesForMinimalEdgeRings() {
/*  83 */     DirectedEdge de = this.startDe;
/*     */     do {
/*  85 */       Node node = de.getNode();
/*  86 */       ((DirectedEdgeStar)node.getEdges()).linkMinimalDirectedEdges(this);
/*  87 */       de = de.getNext();
/*  88 */     } while (de != this.startDe);
/*     */   }
/*     */   
/*     */   public List buildMinimalRings() {
/*  93 */     List<EdgeRing> minEdgeRings = new ArrayList();
/*  94 */     DirectedEdge de = this.startDe;
/*     */     while (true) {
/*  96 */       if (de.getMinEdgeRing() == null) {
/*  97 */         EdgeRing minEr = new MinimalEdgeRing(de, this.geometryFactory);
/*  98 */         minEdgeRings.add(minEr);
/*     */       } 
/* 100 */       de = de.getNext();
/* 101 */       if (de == this.startDe)
/* 102 */         return minEdgeRings; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\MaximalEdgeRing.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */