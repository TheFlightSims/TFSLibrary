/*     */ package com.vividsolutions.jts.geom.prep;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.PointLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.util.ComponentCoordinateExtracter;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ class BasicPreparedGeometry implements PreparedGeometry {
/*     */   private final Geometry baseGeom;
/*     */   
/*     */   private final List representativePts;
/*     */   
/*     */   public BasicPreparedGeometry(Geometry geom) {
/*  61 */     this.baseGeom = geom;
/*  62 */     this.representativePts = ComponentCoordinateExtracter.getCoordinates(geom);
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/*  65 */     return this.baseGeom;
/*     */   }
/*     */   
/*     */   public List getRepresentativePoints() {
/*  79 */     return this.representativePts;
/*     */   }
/*     */   
/*     */   public boolean isAnyTargetComponentInTest(Geometry testGeom) {
/*  93 */     PointLocator locator = new PointLocator();
/*  94 */     for (Iterator<Coordinate> i = this.representativePts.iterator(); i.hasNext(); ) {
/*  95 */       Coordinate p = i.next();
/*  96 */       if (locator.intersects(p, testGeom))
/*  97 */         return true; 
/*     */     } 
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean envelopesIntersect(Geometry g) {
/* 111 */     if (!this.baseGeom.getEnvelopeInternal().intersects(g.getEnvelopeInternal()))
/* 112 */       return false; 
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean envelopeCovers(Geometry g) {
/* 126 */     if (!this.baseGeom.getEnvelopeInternal().covers(g.getEnvelopeInternal()))
/* 127 */       return false; 
/* 128 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(Geometry g) {
/* 136 */     return this.baseGeom.contains(g);
/*     */   }
/*     */   
/*     */   public boolean containsProperly(Geometry g) {
/* 147 */     if (!this.baseGeom.getEnvelopeInternal().contains(g.getEnvelopeInternal()))
/* 148 */       return false; 
/* 151 */     return this.baseGeom.relate(g, "T**FF*FF*");
/*     */   }
/*     */   
/*     */   public boolean coveredBy(Geometry g) {
/* 159 */     return this.baseGeom.coveredBy(g);
/*     */   }
/*     */   
/*     */   public boolean covers(Geometry g) {
/* 167 */     return this.baseGeom.covers(g);
/*     */   }
/*     */   
/*     */   public boolean crosses(Geometry g) {
/* 175 */     return this.baseGeom.crosses(g);
/*     */   }
/*     */   
/*     */   public boolean disjoint(Geometry g) {
/* 184 */     return !intersects(g);
/*     */   }
/*     */   
/*     */   public boolean intersects(Geometry g) {
/* 192 */     return this.baseGeom.intersects(g);
/*     */   }
/*     */   
/*     */   public boolean overlaps(Geometry g) {
/* 200 */     return this.baseGeom.overlaps(g);
/*     */   }
/*     */   
/*     */   public boolean touches(Geometry g) {
/* 208 */     return this.baseGeom.touches(g);
/*     */   }
/*     */   
/*     */   public boolean within(Geometry g) {
/* 216 */     return this.baseGeom.within(g);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 221 */     return this.baseGeom.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\BasicPreparedGeometry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */