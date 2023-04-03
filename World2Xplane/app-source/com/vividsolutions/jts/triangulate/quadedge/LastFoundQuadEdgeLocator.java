/*    */ package com.vividsolutions.jts.triangulate.quadedge;
/*    */ 
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class LastFoundQuadEdgeLocator implements QuadEdgeLocator {
/*    */   private QuadEdgeSubdivision subdiv;
/*    */   
/* 47 */   private QuadEdge lastEdge = null;
/*    */   
/*    */   public LastFoundQuadEdgeLocator(QuadEdgeSubdivision subdiv) {
/* 50 */     this.subdiv = subdiv;
/* 51 */     init();
/*    */   }
/*    */   
/*    */   private void init() {
/* 55 */     this.lastEdge = findEdge();
/*    */   }
/*    */   
/*    */   private QuadEdge findEdge() {
/* 59 */     Collection<QuadEdge> edges = this.subdiv.getEdges();
/* 61 */     return edges.iterator().next();
/*    */   }
/*    */   
/*    */   public QuadEdge locate(Vertex v) {
/* 69 */     if (!this.lastEdge.isLive())
/* 70 */       init(); 
/* 73 */     QuadEdge e = this.subdiv.locateFromEdge(v, this.lastEdge);
/* 74 */     this.lastEdge = e;
/* 75 */     return e;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\quadedge\LastFoundQuadEdgeLocator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */