/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ 
/*     */ public class InteriorPointLine {
/*     */   private Coordinate centroid;
/*     */   
/*  53 */   private double minDistance = Double.MAX_VALUE;
/*     */   
/*  55 */   private Coordinate interiorPoint = null;
/*     */   
/*     */   public InteriorPointLine(Geometry g) {
/*  59 */     this.centroid = g.getCentroid().getCoordinate();
/*  60 */     addInterior(g);
/*  61 */     if (this.interiorPoint == null)
/*  62 */       addEndpoints(g); 
/*     */   }
/*     */   
/*     */   public Coordinate getInteriorPoint() {
/*  67 */     return this.interiorPoint;
/*     */   }
/*     */   
/*     */   private void addInterior(Geometry geom) {
/*  78 */     if (geom instanceof com.vividsolutions.jts.geom.LineString) {
/*  79 */       addInterior(geom.getCoordinates());
/*  81 */     } else if (geom instanceof GeometryCollection) {
/*  82 */       GeometryCollection gc = (GeometryCollection)geom;
/*  83 */       for (int i = 0; i < gc.getNumGeometries(); i++)
/*  84 */         addInterior(gc.getGeometryN(i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addInterior(Coordinate[] pts) {
/*  90 */     for (int i = 1; i < pts.length - 1; i++)
/*  91 */       add(pts[i]); 
/*     */   }
/*     */   
/*     */   private void addEndpoints(Geometry geom) {
/* 102 */     if (geom instanceof com.vividsolutions.jts.geom.LineString) {
/* 103 */       addEndpoints(geom.getCoordinates());
/* 105 */     } else if (geom instanceof GeometryCollection) {
/* 106 */       GeometryCollection gc = (GeometryCollection)geom;
/* 107 */       for (int i = 0; i < gc.getNumGeometries(); i++)
/* 108 */         addEndpoints(gc.getGeometryN(i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addEndpoints(Coordinate[] pts) {
/* 114 */     add(pts[0]);
/* 115 */     add(pts[pts.length - 1]);
/*     */   }
/*     */   
/*     */   private void add(Coordinate point) {
/* 120 */     double dist = point.distance(this.centroid);
/* 121 */     if (dist < this.minDistance) {
/* 122 */       this.interiorPoint = new Coordinate(point);
/* 123 */       this.minDistance = dist;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\InteriorPointLine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */