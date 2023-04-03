/*     */ package com.vividsolutions.jts.math;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class Plane3D {
/*     */   public static final int XY_PLANE = 1;
/*     */   
/*     */   public static final int YZ_PLANE = 2;
/*     */   
/*     */   public static final int XZ_PLANE = 3;
/*     */   
/*     */   private Vector3D normal;
/*     */   
/*     */   private Coordinate basePt;
/*     */   
/*     */   public Plane3D(Vector3D normal, Coordinate basePt) {
/*  58 */     this.normal = normal;
/*  59 */     this.basePt = basePt;
/*     */   }
/*     */   
/*     */   public double orientedDistance(Coordinate p) {
/*  75 */     Vector3D pb = new Vector3D(p, this.basePt);
/*  76 */     double pbdDotNormal = pb.dot(this.normal);
/*  77 */     if (Double.isNaN(pbdDotNormal))
/*  78 */       throw new IllegalArgumentException("3D Coordinate has NaN ordinate"); 
/*  79 */     double d = pbdDotNormal / this.normal.length();
/*  80 */     return d;
/*     */   }
/*     */   
/*     */   public int closestAxisPlane() {
/*  95 */     double xmag = Math.abs(this.normal.getX());
/*  96 */     double ymag = Math.abs(this.normal.getY());
/*  97 */     double zmag = Math.abs(this.normal.getZ());
/*  98 */     if (xmag > ymag) {
/*  99 */       if (xmag > zmag)
/* 100 */         return 2; 
/* 102 */       return 1;
/*     */     } 
/* 105 */     if (zmag > ymag)
/* 106 */       return 1; 
/* 109 */     return 3;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\math\Plane3D.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */