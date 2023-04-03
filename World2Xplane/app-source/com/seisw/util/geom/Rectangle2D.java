/*    */ package com.seisw.util.geom;
/*    */ 
/*    */ public class Rectangle2D {
/*    */   double x;
/*    */   
/*    */   double y;
/*    */   
/*    */   double width;
/*    */   
/*    */   double height;
/*    */   
/*    */   public Rectangle2D(double x, double y, double width, double height) {
/* 18 */     this.x = x;
/* 19 */     this.y = y;
/* 20 */     this.width = width;
/* 21 */     this.height = height;
/*    */   }
/*    */   
/*    */   public Rectangle2D() {
/* 25 */     this(0.0D, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */   
/*    */   public double getX() {
/* 29 */     return this.x;
/*    */   }
/*    */   
/*    */   public double getY() {
/* 33 */     return this.y;
/*    */   }
/*    */   
/*    */   public double getWidth() {
/* 40 */     return this.width;
/*    */   }
/*    */   
/*    */   public double getHeight() {
/* 47 */     return this.height;
/*    */   }
/*    */   
/*    */   public double getMinX() {
/* 51 */     return this.x;
/*    */   }
/*    */   
/*    */   public double getMaxX() {
/* 55 */     return this.x + this.width;
/*    */   }
/*    */   
/*    */   public double getMinY() {
/* 59 */     return this.y;
/*    */   }
/*    */   
/*    */   public double getMaxY() {
/* 63 */     return this.y + this.height;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\seis\\util\geom\Rectangle2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */