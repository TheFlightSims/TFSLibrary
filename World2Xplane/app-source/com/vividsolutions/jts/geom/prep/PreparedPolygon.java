/*     */ package com.vividsolutions.jts.geom.prep;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.locate.IndexedPointInAreaLocator;
/*     */ import com.vividsolutions.jts.algorithm.locate.PointOnGeometryLocator;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.Polygonal;
/*     */ import com.vividsolutions.jts.noding.FastSegmentSetIntersectionFinder;
/*     */ import com.vividsolutions.jts.noding.SegmentStringUtil;
/*     */ import com.vividsolutions.jts.operation.predicate.RectangleContains;
/*     */ import com.vividsolutions.jts.operation.predicate.RectangleIntersects;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PreparedPolygon extends BasicPreparedGeometry {
/*     */   private final boolean isRectangle;
/*     */   
/*  58 */   private FastSegmentSetIntersectionFinder segIntFinder = null;
/*     */   
/*  59 */   private PointOnGeometryLocator pia = null;
/*     */   
/*     */   public PreparedPolygon(Polygonal poly) {
/*  62 */     super((Geometry)poly);
/*  63 */     this.isRectangle = getGeometry().isRectangle();
/*     */   }
/*     */   
/*     */   public synchronized FastSegmentSetIntersectionFinder getIntersectionFinder() {
/*  79 */     if (this.segIntFinder == null)
/*  80 */       this.segIntFinder = new FastSegmentSetIntersectionFinder(SegmentStringUtil.extractSegmentStrings(getGeometry())); 
/*  81 */     return this.segIntFinder;
/*     */   }
/*     */   
/*     */   public synchronized PointOnGeometryLocator getPointLocator() {
/*  86 */     if (this.pia == null)
/*  87 */       this.pia = (PointOnGeometryLocator)new IndexedPointInAreaLocator(getGeometry()); 
/*  89 */     return this.pia;
/*     */   }
/*     */   
/*     */   public boolean intersects(Geometry g) {
/*  95 */     if (!envelopesIntersect(g))
/*  95 */       return false; 
/*  98 */     if (this.isRectangle)
/*  99 */       return RectangleIntersects.intersects((Polygon)getGeometry(), g); 
/* 102 */     return PreparedPolygonIntersects.intersects(this, g);
/*     */   }
/*     */   
/*     */   public boolean contains(Geometry g) {
/* 108 */     if (!envelopeCovers(g))
/* 109 */       return false; 
/* 112 */     if (this.isRectangle)
/* 113 */       return RectangleContains.contains((Polygon)getGeometry(), g); 
/* 116 */     return PreparedPolygonContains.contains(this, g);
/*     */   }
/*     */   
/*     */   public boolean containsProperly(Geometry g) {
/* 122 */     if (!envelopeCovers(g))
/* 123 */       return false; 
/* 124 */     return PreparedPolygonContainsProperly.containsProperly(this, g);
/*     */   }
/*     */   
/*     */   public boolean covers(Geometry g) {
/* 130 */     if (!envelopeCovers(g))
/* 131 */       return false; 
/* 133 */     if (this.isRectangle)
/* 134 */       return true; 
/* 136 */     return PreparedPolygonCovers.covers(this, g);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedPolygon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */