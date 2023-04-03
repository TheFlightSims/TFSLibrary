/*     */ package com.world2xplane.Geom.ConcaveHull;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class ConcaveHullBuilder {
/*  37 */   private static GeometryFactory gf = new GeometryFactory();
/*     */   
/*  38 */   private static Random rand = new Random();
/*     */   
/*  40 */   private static double alpha = 50.0D;
/*     */   
/*     */   public static Geometry getConcaveHullGeo(Point[] points, double alpha) throws RuntimeException {
/*  88 */     if (points.length <= 2)
/*  88 */       return (Geometry)(new GeometryFactory()).createMultiPoint(points); 
/*  89 */     List<Point> l = new ArrayList<>();
/*  90 */     for (Point p : points)
/*  90 */       l.add(p.getCentroid()); 
/*  91 */     List<LineString> edges = getConcaveHull(l.<Point>toArray(new Point[0]), alpha);
/*  92 */     List<Coordinate> cl = new ArrayList<>();
/*  93 */     for (LineString ls : edges) {
/*  94 */       Point p0 = ls.getStartPoint();
/*  95 */       Point p1 = ls.getEndPoint();
/*  96 */       cl.add(p0.getCoordinate());
/*  97 */       cl.add(p1.getCoordinate());
/*     */     } 
/*     */     try {
/* 100 */       if (edges.size() > 0)
/* 100 */         cl.add(((LineString)edges.get(0)).getStartPoint().getCoordinate()); 
/* 101 */       GeometryFactory fact = new GeometryFactory();
/* 102 */       LinearRing ring = fact.createLinearRing(cl.<Coordinate>toArray(new Coordinate[0]));
/* 103 */       return (Geometry)fact.createPolygon(ring, null);
/* 104 */     } catch (Exception e) {
/* 105 */       return (Geometry)(new GeometryFactory()).createMultiPoint(points);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Geometry getConcaveHull(Coordinate[] points, double alpha) {
/* 110 */     Point[] pointArray = new Point[points.length];
/* 111 */     for (int x = 0; x < points.length; x++)
/* 112 */       pointArray[x] = (new GeometryFactory()).createPoint(points[x]); 
/* 114 */     return getConcaveHullGeo(pointArray, alpha);
/*     */   }
/*     */   
/*     */   public static List<LineString> getConcaveHull(Point[] points, double alpha) {
/* 129 */     double alpha2 = 2.0D * alpha;
/* 130 */     STRtree index = new STRtree();
/* 131 */     for (Point p : points)
/* 131 */       index.insert(p.getEnvelopeInternal(), p); 
/* 132 */     index.build();
/* 133 */     List<LineString> edges = new ArrayList<>();
/* 134 */     List<Point> candidates = new ArrayList<>();
/* 135 */     candidates.addAll(Arrays.asList(points));
/* 136 */     while (!candidates.isEmpty()) {
/* 137 */       Point p1 = candidates.remove(0);
/* 138 */       Envelope qEnv = new Envelope(p1.getX() - alpha2, p1.getX() + alpha2, p1.getY() - alpha2, p1.getY() + alpha2);
/* 139 */       PointVisitor visitor = new PointVisitor(p1, alpha2);
/* 140 */       index.query(qEnv, visitor);
/* 141 */       if (visitor.plist.size() < 2)
/*     */         break; 
/* 142 */       visitor.plist.remove(p1);
/* 143 */       boolean[] used = new boolean[visitor.plist.size()];
/* 144 */       Arrays.fill(used, false);
/* 145 */       int numPts = visitor.plist.size();
/* 146 */       while (numPts > 0) {
/*     */         Point p2;
/*     */         boolean onBoundary;
/*     */         while (true) {
/* 149 */           int pindex = rand.nextInt(visitor.plist.size());
/* 150 */           if (!used[pindex]) {
/* 151 */             p2 = visitor.plist.get(pindex);
/* 152 */             used[pindex] = true;
/* 153 */             numPts--;
/* 157 */             Point pcentre = createCircle(p1, p2, alpha);
/* 158 */             onBoundary = true;
/* 159 */             for (Point vp : visitor.plist) {
/* 160 */               if (vp != p2 && 
/* 161 */                 pcentre.distance((Geometry)vp) <= alpha) {
/* 162 */                 onBoundary = false;
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             break;
/*     */           } 
/*     */         } 
/* 167 */         if (onBoundary)
/* 168 */           edges.add(gf.createLineString(new Coordinate[] { p1.getCoordinate(), p2.getCoordinate() })); 
/*     */       } 
/*     */     } 
/* 172 */     return edges;
/*     */   }
/*     */   
/*     */   private static Point createCircle(Point p1, Point p2, double alpha) {
/* 185 */     Coordinate centre = new Coordinate();
/* 187 */     double dx = p2.getX() - p1.getX();
/* 188 */     double dy = p2.getY() - p1.getY();
/* 189 */     double s2 = dx * dx + dy * dy;
/* 191 */     double h = FastMath.sqrt(alpha * alpha / s2 - 0.25D);
/* 193 */     centre.x = p1.getX() + dx / 2.0D + h * dy;
/* 194 */     centre.y = p1.getY() + dy / 2.0D + h * (p1.getX() - p2.getX());
/* 196 */     return gf.createPoint(centre);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\ConcaveHull\ConcaveHullBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */