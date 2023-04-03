/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Vector3D {
/*     */   double[] xyz;
/*     */   
/*     */   public Vector3D() {
/* 130 */     this.xyz = new double[3];
/*     */     this.xyz[0] = 0.0D;
/*     */     this.xyz[1] = 0.0D;
/*     */     this.xyz[2] = 0.0D;
/*     */   }
/*     */   
/*     */   public Vector3D(double x, double y, double z) {
/* 130 */     this.xyz = new double[3];
/*     */     this.xyz[0] = x;
/*     */     this.xyz[1] = y;
/*     */     this.xyz[2] = z;
/*     */   }
/*     */   
/*     */   public Vector3D(double[] array) {
/* 130 */     this.xyz = new double[3];
/*     */     if (array.length != 3)
/*     */       throw new RuntimeException("Must create vector with 3 element array"); 
/*     */     this.xyz[0] = array[0];
/*     */     this.xyz[1] = array[1];
/*     */     this.xyz[2] = array[2];
/*     */   }
/*     */   
/*     */   public double[] array() {
/*     */     return (double[])this.xyz.clone();
/*     */   }
/*     */   
/*     */   public Vector3D add(Vector3D rhs) {
/*     */     return new Vector3D(this.xyz[0] + rhs.xyz[0], this.xyz[1] + rhs.xyz[1], this.xyz[2] + rhs.xyz[2]);
/*     */   }
/*     */   
/*     */   public Vector3D sub(Vector3D rhs) {
/*     */     return new Vector3D(this.xyz[0] - rhs.xyz[0], this.xyz[1] - rhs.xyz[1], this.xyz[2] - rhs.xyz[2]);
/*     */   }
/*     */   
/*     */   public Vector3D neg() {
/*     */     return new Vector3D(-this.xyz[0], -this.xyz[1], -this.xyz[2]);
/*     */   }
/*     */   
/*     */   public Vector3D mul(double c) {
/*     */     return new Vector3D(c * this.xyz[0], c * this.xyz[1], c * this.xyz[2]);
/*     */   }
/*     */   
/*     */   public Vector3D div(double c) {
/*     */     return new Vector3D(this.xyz[0] / c, this.xyz[1] / c, this.xyz[2] / c);
/*     */   }
/*     */   
/*     */   public double dot(Vector3D rhs) {
/*     */     return this.xyz[0] * rhs.xyz[0] + this.xyz[1] * rhs.xyz[1] + this.xyz[2] * rhs.xyz[2];
/*     */   }
/*     */   
/*     */   public Vector3D cross(Vector3D rhs) {
/*     */     return new Vector3D(this.xyz[1] * rhs.xyz[2] - this.xyz[2] * rhs.xyz[1], this.xyz[0] * rhs.xyz[2] - this.xyz[2] * rhs.xyz[0], this.xyz[0] * rhs.xyz[1] - this.xyz[1] * rhs.xyz[0]);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*     */     if (obj instanceof Vector3D) {
/*     */       Vector3D rhs = (Vector3D)obj;
/*     */       return (this.xyz[0] == rhs.xyz[0] && this.xyz[1] == rhs.xyz[1] && this.xyz[2] == rhs.xyz[2]);
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public double norm() {
/*     */     return FastMath.sqrt(dot(this));
/*     */   }
/*     */   
/*     */   public Vector3D normalize() {
/*     */     return div(norm());
/*     */   }
/*     */   
/*     */   public double x() {
/*     */     return this.xyz[0];
/*     */   }
/*     */   
/*     */   public double y() {
/*     */     return this.xyz[1];
/*     */   }
/*     */   
/*     */   public double z() {
/*     */     return this.xyz[2];
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return "( " + this.xyz[0] + " " + this.xyz[1] + " " + this.xyz[2] + " )";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Vector3D.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */