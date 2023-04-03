/*    */ package com.vividsolutions.jts.triangulate.quadedge;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedList;
/*    */ 
/*    */ public class EdgeConnectedTriangleTraversal {
/* 46 */   private LinkedList triQueue = new LinkedList();
/*    */   
/*    */   public void init(QuadEdgeTriangle tri) {
/* 51 */     this.triQueue.addLast(tri);
/*    */   }
/*    */   
/*    */   public void init(Collection tris) {
/* 60 */     this.triQueue.addAll(tris);
/*    */   }
/*    */   
/*    */   public void visitAll(TraversalVisitor visitor) {
/* 77 */     while (!this.triQueue.isEmpty()) {
/* 78 */       QuadEdgeTriangle tri = this.triQueue.removeFirst();
/* 79 */       process(tri, visitor);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void process(QuadEdgeTriangle currTri, TraversalVisitor visitor) {
/* 84 */     currTri.getNeighbours();
/* 85 */     for (int i = 0; i < 3; i++) {
/* 86 */       QuadEdgeTriangle neighTri = (QuadEdgeTriangle)currTri.getEdge(i).sym().getData();
/* 87 */       if (neighTri != null)
/* 89 */         if (visitor.visit(currTri, i, neighTri))
/* 90 */           this.triQueue.addLast(neighTri);  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\quadedge\EdgeConnectedTriangleTraversal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */