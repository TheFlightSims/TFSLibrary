/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import org.geotools.geometry.DirectPosition2D;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ 
/*     */ class Circle {
/*     */   private DirectPosition2D center;
/*     */   
/*     */   private double radius;
/*     */   
/*  39 */   private double tolerance = 1.0E-4D;
/*     */   
/*     */   protected Circle() {
/*  46 */     this((DirectPosition)new DirectPosition2D(0.0D, 0.0D), 0.0D);
/*     */   }
/*     */   
/*     */   protected Circle(DirectPosition center, double radius) {
/*  55 */     this.center = new DirectPosition2D(center);
/*  56 */     this.radius = radius;
/*     */   }
/*     */   
/*     */   protected void setCenter(DirectPosition center) {
/*  65 */     this.center = new DirectPosition2D(center);
/*     */   }
/*     */   
/*     */   protected void setRadius(double radius) {
/*  74 */     this.radius = radius;
/*     */   }
/*     */   
/*     */   protected DirectPosition getCenter() {
/*  83 */     return (DirectPosition)this.center;
/*     */   }
/*     */   
/*     */   protected double getRadius() {
/*  92 */     return this.radius;
/*     */   }
/*     */   
/*     */   protected void setTolerance(double tolerance) {
/* 101 */     this.tolerance = tolerance;
/*     */   }
/*     */   
/*     */   protected double getTolerance() {
/* 110 */     return this.tolerance;
/*     */   }
/*     */   
/*     */   protected boolean contains(DirectPosition p) {
/* 125 */     if (this.center.distance((Point2D)new DirectPosition2D(p)) < this.radius - this.tolerance)
/* 126 */       return true; 
/* 128 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\Circle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */