/*    */ package com.vividsolutions.jts.index.intervalrtree;
/*    */ 
/*    */ import com.vividsolutions.jts.index.ItemVisitor;
/*    */ 
/*    */ public class IntervalRTreeLeafNode extends IntervalRTreeNode {
/*    */   private Object item;
/*    */   
/*    */   public IntervalRTreeLeafNode(double min, double max, Object item) {
/* 44 */     this.min = min;
/* 45 */     this.max = max;
/* 46 */     this.item = item;
/*    */   }
/*    */   
/*    */   public void query(double queryMin, double queryMax, ItemVisitor visitor) {
/* 51 */     if (!intersects(queryMin, queryMax))
/*    */       return; 
/* 54 */     visitor.visitItem(this.item);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\intervalrtree\IntervalRTreeLeafNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */