/*    */ package com.vividsolutions.jtsexample.geom;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ 
/*    */ public class ExtendedCoordinate extends Coordinate {
/*    */   private static final long serialVersionUID = 8527484784733305576L;
/*    */   
/*    */   private double m;
/*    */   
/*    */   public ExtendedCoordinate() {
/* 54 */     this.m = 0.0D;
/*    */   }
/*    */   
/*    */   public ExtendedCoordinate(double x, double y, double z, double m) {
/* 59 */     super(x, y, z);
/* 60 */     this.m = m;
/*    */   }
/*    */   
/*    */   public ExtendedCoordinate(Coordinate coord) {
/* 65 */     super(coord);
/* 66 */     if (coord instanceof ExtendedCoordinate) {
/* 67 */       this.m = ((ExtendedCoordinate)coord).m;
/*    */     } else {
/* 69 */       this.m = Double.NaN;
/*    */     } 
/*    */   }
/*    */   
/*    */   public ExtendedCoordinate(ExtendedCoordinate coord) {
/* 74 */     super(coord);
/* 75 */     this.m = coord.m;
/*    */   }
/*    */   
/*    */   public double getM() {
/* 85 */     return this.m;
/*    */   }
/*    */   
/*    */   public void setM(double m) {
/* 86 */     this.m = m;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 90 */     String stringRep = this.x + " " + this.y + " m=" + this.m;
/* 91 */     return stringRep;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\geom\ExtendedCoordinate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */