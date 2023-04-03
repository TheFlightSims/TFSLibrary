/*    */ package org.poly2tri.triangulation.delaunay.sweep;
/*    */ 
/*    */ public class AdvancingFrontIndex<A> {
/*    */   double _min;
/*    */   
/*    */   double _max;
/*    */   
/*    */   IndexNode<A> _root;
/*    */   
/*    */   public AdvancingFrontIndex(double min, double max, int depth) {
/* 32 */     if (depth > 5)
/* 32 */       depth = 5; 
/* 33 */     this._root = createIndex(depth);
/*    */   }
/*    */   
/*    */   private IndexNode<A> createIndex(int n) {
/* 38 */     IndexNode<A> node = null;
/* 39 */     if (n > 0) {
/* 41 */       node = new IndexNode<>();
/* 42 */       node.bigger = createIndex(n - 1);
/* 43 */       node.smaller = createIndex(n - 1);
/*    */     } 
/* 45 */     return node;
/*    */   }
/*    */   
/*    */   public A fetchAndRemoveIndex(A key) {
/* 50 */     return null;
/*    */   }
/*    */   
/*    */   public A fetchAndInsertIndex(A key) {
/* 55 */     return null;
/*    */   }
/*    */   
/*    */   class IndexNode<A> {
/*    */     A value;
/*    */     
/*    */     IndexNode<A> smaller;
/*    */     
/*    */     IndexNode<A> bigger;
/*    */     
/*    */     double range;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\delaunay\sweep\AdvancingFrontIndex.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */