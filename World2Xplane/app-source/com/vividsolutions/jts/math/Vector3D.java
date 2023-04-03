/*     */ package com.vividsolutions.jts.math;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class Vector3D {
/*     */   private double x;
/*     */   
/*     */   private double y;
/*     */   
/*     */   private double z;
/*     */   
/*     */   public static double dot(Coordinate A, Coordinate B, Coordinate C, Coordinate D) {
/*  57 */     double ABx = B.x - A.x;
/*  58 */     double ABy = B.y - A.y;
/*  59 */     double ABz = B.z - A.z;
/*  60 */     double CDx = D.x - C.x;
/*  61 */     double CDy = D.y - C.y;
/*  62 */     double CDz = D.z - C.z;
/*  63 */     return ABx * CDx + ABy * CDy + ABz * CDz;
/*     */   }
/*     */   
/*     */   public static Vector3D create(double x, double y, double z) {
/*  78 */     return new Vector3D(x, y, z);
/*     */   }
/*     */   
/*     */   public static Vector3D create(Coordinate coord) {
/*  89 */     return new Vector3D(coord);
/*     */   }
/*     */   
/*     */   public Vector3D(Coordinate v) {
/*  93 */     this.x = v.x;
/*  94 */     this.y = v.y;
/*  95 */     this.z = v.z;
/*     */   }
/*     */   
/*     */   public static double dot(Coordinate v1, Coordinate v2) {
/* 106 */     return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
/*     */   }
/*     */   
/*     */   public Vector3D(Coordinate from, Coordinate to) {
/* 114 */     this.x = to.x - from.x;
/* 115 */     this.y = to.y - from.y;
/* 116 */     this.z = to.z - from.z;
/*     */   }
/*     */   
/*     */   public Vector3D(double x, double y, double z) {
/* 120 */     this.x = x;
/* 121 */     this.y = y;
/* 122 */     this.z = z;
/*     */   }
/*     */   
/*     */   public double getX() {
/* 126 */     return this.x;
/*     */   }
/*     */   
/*     */   public double getY() {
/* 130 */     return this.y;
/*     */   }
/*     */   
/*     */   public double getZ() {
/* 134 */     return this.z;
/*     */   }
/*     */   
/*     */   public double dot(Vector3D v) {
/* 146 */     return this.x * v.x + this.y * v.y + this.z * v.z;
/*     */   }
/*     */   
/*     */   public double length() {
/* 150 */     return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */   }
/*     */   
/*     */   public static double length(Coordinate v) {
/* 154 */     return Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
/*     */   }
/*     */   
/*     */   public Vector3D normalize() {
/* 158 */     double length = length();
/* 159 */     if (length > 0.0D)
/* 160 */       return divide(length()); 
/* 161 */     return create(0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   private Vector3D divide(double d) {
/* 165 */     return create(this.x / d, this.y / d, this.z / d);
/*     */   }
/*     */   
/*     */   public static Coordinate normalize(Coordinate v) {
/* 169 */     double len = length(v);
/* 170 */     return new Coordinate(v.x / len, v.y / len, v.z / len);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 178 */     return "[" + this.x + ", " + this.y + ", " + this.z + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\math\Vector3D.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */