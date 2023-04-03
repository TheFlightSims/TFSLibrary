/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ 
/*    */ public class AzimuthalProjection extends Projection {
/*    */   public static final int NORTH_POLE = 1;
/*    */   
/*    */   public static final int SOUTH_POLE = 2;
/*    */   
/*    */   public static final int EQUATOR = 3;
/*    */   
/*    */   public static final int OBLIQUE = 4;
/*    */   
/*    */   protected int mode;
/*    */   
/*    */   protected double trueScaleLatitude;
/*    */   
/*    */   protected double sinphi0;
/*    */   
/*    */   protected double cosphi0;
/*    */   
/* 36 */   private double mapRadius = 90.0D;
/*    */   
/*    */   public AzimuthalProjection() {
/* 39 */     this(Math.toRadians(45.0D), Math.toRadians(45.0D));
/*    */   }
/*    */   
/*    */   public AzimuthalProjection(double projectionLatitude, double projectionLongitude) {
/* 43 */     this.projectionLatitude = projectionLatitude;
/* 44 */     this.projectionLongitude = projectionLongitude;
/* 45 */     initialize();
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 49 */     super.initialize();
/* 50 */     if (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966D) < 1.0E-10D) {
/* 51 */       this.mode = (this.projectionLatitude < 0.0D) ? 2 : 1;
/* 52 */     } else if (Math.abs(this.projectionLatitude) > 1.0E-10D) {
/* 53 */       this.mode = 4;
/* 54 */       this.sinphi0 = Math.sin(this.projectionLatitude);
/* 55 */       this.cosphi0 = Math.cos(this.projectionLatitude);
/*    */     } else {
/* 57 */       this.mode = 3;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean inside(double lon, double lat) {
/* 61 */     return (MapMath.greatCircleDistance(Math.toRadians(lon), Math.toRadians(lat), this.projectionLongitude, this.projectionLatitude) < Math.toRadians(this.mapRadius));
/*    */   }
/*    */   
/*    */   public void setMapRadius(double mapRadius) {
/* 68 */     this.mapRadius = mapRadius;
/*    */   }
/*    */   
/*    */   public double getMapRadius() {
/* 72 */     return this.mapRadius;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\AzimuthalProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */