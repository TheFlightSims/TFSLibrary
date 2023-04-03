/*     */ package com.vividsolutions.jts.operation.distance;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ 
/*     */ public class GeometryLocation {
/*     */   public static final int INSIDE_AREA = -1;
/*     */   
/*  59 */   private Geometry component = null;
/*     */   
/*     */   private int segIndex;
/*     */   
/*  61 */   private Coordinate pt = null;
/*     */   
/*     */   public GeometryLocation(Geometry component, int segIndex, Coordinate pt) {
/*  74 */     this.component = component;
/*  75 */     this.segIndex = segIndex;
/*  76 */     this.pt = pt;
/*     */   }
/*     */   
/*     */   public GeometryLocation(Geometry component, Coordinate pt) {
/*  87 */     this(component, -1, pt);
/*     */   }
/*     */   
/*     */   public Geometry getGeometryComponent() {
/*  93 */     return this.component;
/*     */   }
/*     */   
/*     */   public int getSegmentIndex() {
/* 101 */     return this.segIndex;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/* 106 */     return this.pt;
/*     */   }
/*     */   
/*     */   public boolean isInsideArea() {
/* 111 */     return (this.segIndex == -1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\distance\GeometryLocation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */