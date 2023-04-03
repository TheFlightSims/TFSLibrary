/*    */ package com.vividsolutions.jts.geomgraph.index;
/*    */ 
/*    */ public class MonotoneChain {
/*    */   MonotoneChainEdge mce;
/*    */   
/*    */   int chainIndex;
/*    */   
/*    */   public MonotoneChain(MonotoneChainEdge mce, int chainIndex) {
/* 47 */     this.mce = mce;
/* 48 */     this.chainIndex = chainIndex;
/*    */   }
/*    */   
/*    */   public void computeIntersections(MonotoneChain mc, SegmentIntersector si) {
/* 53 */     this.mce.computeIntersectsForChain(this.chainIndex, mc.mce, mc.chainIndex, si);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\index\MonotoneChain.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */