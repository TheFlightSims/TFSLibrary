/*    */ package com.vividsolutions.jts.index.intervalrtree;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.index.ItemVisitor;
/*    */ import com.vividsolutions.jts.io.WKTWriter;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public abstract class IntervalRTreeNode {
/* 43 */   protected double min = Double.POSITIVE_INFINITY;
/*    */   
/* 44 */   protected double max = Double.NEGATIVE_INFINITY;
/*    */   
/*    */   public double getMin() {
/* 46 */     return this.min;
/*    */   }
/*    */   
/*    */   public double getMax() {
/* 47 */     return this.max;
/*    */   }
/*    */   
/*    */   public abstract void query(double paramDouble1, double paramDouble2, ItemVisitor paramItemVisitor);
/*    */   
/*    */   protected boolean intersects(double queryMin, double queryMax) {
/* 53 */     if (this.min > queryMax || this.max < queryMin)
/* 55 */       return false; 
/* 56 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 61 */     return WKTWriter.toLineString(new Coordinate(this.min, 0.0D), new Coordinate(this.max, 0.0D));
/*    */   }
/*    */   
/*    */   public static class NodeComparator implements Comparator {
/*    */     public int compare(Object o1, Object o2) {
/* 68 */       IntervalRTreeNode n1 = (IntervalRTreeNode)o1;
/* 69 */       IntervalRTreeNode n2 = (IntervalRTreeNode)o2;
/* 70 */       double mid1 = (n1.min + n1.max) / 2.0D;
/* 71 */       double mid2 = (n2.min + n2.max) / 2.0D;
/* 72 */       if (mid1 < mid2)
/* 72 */         return -1; 
/* 73 */       if (mid1 > mid2)
/* 73 */         return 1; 
/* 74 */       return 0;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\intervalrtree\IntervalRTreeNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */