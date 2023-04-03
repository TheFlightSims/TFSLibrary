/*    */ package com.world2xplane.Geom.Quadtree;
/*    */ 
/*    */ public class QuadTreePoint implements Comparable {
/*    */   private double x;
/*    */   
/*    */   private double y;
/*    */   
/*    */   private Object opt_value;
/*    */   
/*    */   public QuadTreePoint(double x, double y, Object opt_value) {
/* 39 */     this.x = x;
/* 40 */     this.y = y;
/* 41 */     this.opt_value = opt_value;
/*    */   }
/*    */   
/*    */   public double getX() {
/* 45 */     return this.x;
/*    */   }
/*    */   
/*    */   public void setX(double x) {
/* 49 */     this.x = x;
/*    */   }
/*    */   
/*    */   public double getY() {
/* 53 */     return this.y;
/*    */   }
/*    */   
/*    */   public void setY(double y) {
/* 57 */     this.y = y;
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 61 */     return this.opt_value;
/*    */   }
/*    */   
/*    */   public void setValue(Object opt_value) {
/* 65 */     this.opt_value = opt_value;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 70 */     return "(" + this.x + ", " + this.y + ")";
/*    */   }
/*    */   
/*    */   public int compareTo(Object o) {
/* 74 */     QuadTreePoint tmp = (QuadTreePoint)o;
/* 75 */     if (this.x < tmp.x)
/* 76 */       return -1; 
/* 77 */     if (this.x > tmp.x)
/* 78 */       return 1; 
/* 80 */     if (this.y < tmp.y)
/* 81 */       return -1; 
/* 82 */     if (this.y > tmp.y)
/* 83 */       return 1; 
/* 85 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Quadtree\QuadTreePoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */