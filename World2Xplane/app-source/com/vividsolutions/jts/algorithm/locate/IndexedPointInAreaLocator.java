/*     */ package com.vividsolutions.jts.algorithm.locate;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.RayCrossingCounter;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.util.LinearComponentExtracter;
/*     */ import com.vividsolutions.jts.index.ArrayListVisitor;
/*     */ import com.vividsolutions.jts.index.ItemVisitor;
/*     */ import com.vividsolutions.jts.index.intervalrtree.SortedPackedIntervalRTree;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class IndexedPointInAreaLocator implements PointOnGeometryLocator {
/*     */   private final IntervalIndexedGeometry index;
/*     */   
/*     */   public IndexedPointInAreaLocator(Geometry g) {
/*  65 */     if (!(g instanceof com.vividsolutions.jts.geom.Polygonal))
/*  66 */       throw new IllegalArgumentException("Argument must be Polygonal"); 
/*  67 */     this.index = new IntervalIndexedGeometry(g);
/*     */   }
/*     */   
/*     */   public int locate(Coordinate p) {
/*  78 */     RayCrossingCounter rcc = new RayCrossingCounter(p);
/*  80 */     SegmentVisitor visitor = new SegmentVisitor(rcc);
/*  81 */     this.index.query(p.y, p.y, visitor);
/*  89 */     return rcc.getLocation();
/*     */   }
/*     */   
/*     */   private static class SegmentVisitor implements ItemVisitor {
/*     */     private RayCrossingCounter counter;
/*     */     
/*     */     public SegmentVisitor(RayCrossingCounter counter) {
/*  99 */       this.counter = counter;
/*     */     }
/*     */     
/*     */     public void visitItem(Object item) {
/* 104 */       LineSegment seg = (LineSegment)item;
/* 105 */       this.counter.countSegment(seg.getCoordinate(0), seg.getCoordinate(1));
/*     */     }
/*     */   }
/*     */   
/*     */   private static class IntervalIndexedGeometry {
/* 111 */     private final SortedPackedIntervalRTree index = new SortedPackedIntervalRTree();
/*     */     
/*     */     public IntervalIndexedGeometry(Geometry geom) {
/* 115 */       init(geom);
/*     */     }
/*     */     
/*     */     private void init(Geometry geom) {
/* 120 */       List lines = LinearComponentExtracter.getLines(geom);
/* 121 */       for (Iterator<LineString> i = lines.iterator(); i.hasNext(); ) {
/* 122 */         LineString line = i.next();
/* 123 */         Coordinate[] pts = line.getCoordinates();
/* 124 */         addLine(pts);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void addLine(Coordinate[] pts) {
/* 130 */       for (int i = 1; i < pts.length; i++) {
/* 131 */         LineSegment seg = new LineSegment(pts[i - 1], pts[i]);
/* 132 */         double min = Math.min(seg.p0.y, seg.p1.y);
/* 133 */         double max = Math.max(seg.p0.y, seg.p1.y);
/* 134 */         this.index.insert(min, max, seg);
/*     */       } 
/*     */     }
/*     */     
/*     */     public List query(double min, double max) {
/* 140 */       ArrayListVisitor visitor = new ArrayListVisitor();
/* 141 */       this.index.query(min, max, (ItemVisitor)visitor);
/* 142 */       return visitor.getItems();
/*     */     }
/*     */     
/*     */     public void query(double min, double max, ItemVisitor visitor) {
/* 147 */       this.index.query(min, max, visitor);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\locate\IndexedPointInAreaLocator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */