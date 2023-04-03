/*    */ package org.poly2tri.triangulation.delaunay.sweep;
/*    */ 
/*    */ import org.poly2tri.triangulation.TriangulationPoint;
/*    */ import org.poly2tri.triangulation.delaunay.DelaunayTriangle;
/*    */ 
/*    */ public class AdvancingFrontNode {
/* 31 */   protected AdvancingFrontNode next = null;
/*    */   
/* 32 */   protected AdvancingFrontNode prev = null;
/*    */   
/*    */   protected final Double key;
/*    */   
/*    */   protected final double value;
/*    */   
/*    */   protected final TriangulationPoint point;
/*    */   
/*    */   protected DelaunayTriangle triangle;
/*    */   
/*    */   public AdvancingFrontNode(TriangulationPoint point) {
/* 41 */     this.point = point;
/* 42 */     this.value = point.getX();
/* 43 */     this.key = Double.valueOf(this.value);
/*    */   }
/*    */   
/*    */   public AdvancingFrontNode getNext() {
/* 48 */     return this.next;
/*    */   }
/*    */   
/*    */   public AdvancingFrontNode getPrevious() {
/* 53 */     return this.prev;
/*    */   }
/*    */   
/*    */   public TriangulationPoint getPoint() {
/* 58 */     return this.point;
/*    */   }
/*    */   
/*    */   public DelaunayTriangle getTriangle() {
/* 63 */     return this.triangle;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 68 */     return (this.next != null);
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 73 */     return (this.prev != null);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\delaunay\sweep\AdvancingFrontNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */