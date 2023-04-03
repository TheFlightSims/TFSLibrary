/*    */ package com.world2xplane.Geom;
/*    */ 
/*    */ import math.geom2d.Point2D;
/*    */ 
/*    */ public class RangeDistance {
/*    */   Point2D startPoint;
/*    */   
/*    */   Point2D endPoint;
/*    */   
/*    */   Double distance;
/*    */   
/*    */   Double from;
/*    */   
/*    */   Double to;
/*    */   
/*    */   Double angle;
/*    */   
/*    */   public Point2D getStartPoint() {
/* 40 */     return this.startPoint;
/*    */   }
/*    */   
/*    */   public void setStartPoint(Point2D startPoint) {
/* 44 */     this.startPoint = startPoint;
/*    */   }
/*    */   
/*    */   public Point2D getEndPoint() {
/* 48 */     return this.endPoint;
/*    */   }
/*    */   
/*    */   public void setEndPoint(Point2D endPoint) {
/* 52 */     this.endPoint = endPoint;
/*    */   }
/*    */   
/*    */   public Double getDistance() {
/* 56 */     return this.distance;
/*    */   }
/*    */   
/*    */   public void setDistance(Double distance) {
/* 60 */     this.distance = distance;
/*    */   }
/*    */   
/*    */   public Double getFrom() {
/* 64 */     return this.from;
/*    */   }
/*    */   
/*    */   public void setFrom(Double from) {
/* 68 */     this.from = from;
/*    */   }
/*    */   
/*    */   public Double getTo() {
/* 72 */     return this.to;
/*    */   }
/*    */   
/*    */   public void setTo(Double to) {
/* 76 */     this.to = to;
/*    */   }
/*    */   
/*    */   public boolean isInRange(double distance) {
/* 80 */     return (distance >= this.from.doubleValue() && distance <= this.to.doubleValue());
/*    */   }
/*    */   
/*    */   public Double getAngle() {
/* 84 */     return this.angle;
/*    */   }
/*    */   
/*    */   public void setAngle(Double angle) {
/* 88 */     this.angle = angle;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\RangeDistance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */