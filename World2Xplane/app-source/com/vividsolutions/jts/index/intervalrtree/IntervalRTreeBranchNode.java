/*    */ package com.vividsolutions.jts.index.intervalrtree;
/*    */ 
/*    */ import com.vividsolutions.jts.index.ItemVisitor;
/*    */ 
/*    */ public class IntervalRTreeBranchNode extends IntervalRTreeNode {
/*    */   private IntervalRTreeNode node1;
/*    */   
/*    */   private IntervalRTreeNode node2;
/*    */   
/*    */   public IntervalRTreeBranchNode(IntervalRTreeNode n1, IntervalRTreeNode n2) {
/* 45 */     this.node1 = n1;
/* 46 */     this.node2 = n2;
/* 47 */     buildExtent(this.node1, this.node2);
/*    */   }
/*    */   
/*    */   private void buildExtent(IntervalRTreeNode n1, IntervalRTreeNode n2) {
/* 52 */     this.min = Math.min(n1.min, n2.min);
/* 53 */     this.max = Math.max(n1.max, n2.max);
/*    */   }
/*    */   
/*    */   public void query(double queryMin, double queryMax, ItemVisitor visitor) {
/* 58 */     if (!intersects(queryMin, queryMax))
/*    */       return; 
/* 63 */     if (this.node1 != null)
/* 63 */       this.node1.query(queryMin, queryMax, visitor); 
/* 64 */     if (this.node2 != null)
/* 64 */       this.node2.query(queryMin, queryMax, visitor); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\intervalrtree\IntervalRTreeBranchNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */