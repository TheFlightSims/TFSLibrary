/*     */ package com.vividsolutions.jts.operation;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.BoundaryNodeRule;
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.util.LinearComponentExtracter;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeIntersection;
/*     */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*     */ import com.vividsolutions.jts.geomgraph.index.SegmentIntersector;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class IsSimpleOp {
/*     */   private Geometry inputGeom;
/*     */   
/*     */   private boolean isClosedEndpointsInInterior = true;
/*     */   
/*  89 */   private Coordinate nonSimpleLocation = null;
/*     */   
/*     */   public IsSimpleOp() {}
/*     */   
/*     */   public IsSimpleOp(Geometry geom) {
/* 105 */     this.inputGeom = geom;
/*     */   }
/*     */   
/*     */   public IsSimpleOp(Geometry geom, BoundaryNodeRule boundaryNodeRule) {
/* 116 */     this.inputGeom = geom;
/* 117 */     this.isClosedEndpointsInInterior = !boundaryNodeRule.isInBoundary(2);
/*     */   }
/*     */   
/*     */   public boolean isSimple() {
/* 127 */     this.nonSimpleLocation = null;
/* 128 */     return computeSimple(this.inputGeom);
/*     */   }
/*     */   
/*     */   private boolean computeSimple(Geometry geom) {
/* 133 */     this.nonSimpleLocation = null;
/* 134 */     if (geom.isEmpty())
/* 134 */       return true; 
/* 135 */     if (geom instanceof LineString)
/* 135 */       return isSimpleLinearGeometry(geom); 
/* 136 */     if (geom instanceof MultiLineString)
/* 136 */       return isSimpleLinearGeometry(geom); 
/* 137 */     if (geom instanceof MultiPoint)
/* 137 */       return isSimpleMultiPoint((MultiPoint)geom); 
/* 138 */     if (geom instanceof com.vividsolutions.jts.geom.Polygonal)
/* 138 */       return isSimplePolygonal(geom); 
/* 139 */     if (geom instanceof com.vividsolutions.jts.geom.GeometryCollection)
/* 139 */       return isSimpleGeometryCollection(geom); 
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   public Coordinate getNonSimpleLocation() {
/* 155 */     return this.nonSimpleLocation;
/*     */   }
/*     */   
/*     */   public boolean isSimple(LineString geom) {
/* 167 */     return isSimpleLinearGeometry((Geometry)geom);
/*     */   }
/*     */   
/*     */   public boolean isSimple(MultiLineString geom) {
/* 179 */     return isSimpleLinearGeometry((Geometry)geom);
/*     */   }
/*     */   
/*     */   public boolean isSimple(MultiPoint mp) {
/* 188 */     return isSimpleMultiPoint(mp);
/*     */   }
/*     */   
/*     */   private boolean isSimpleMultiPoint(MultiPoint mp) {
/* 193 */     if (mp.isEmpty())
/* 193 */       return true; 
/* 194 */     Set<Coordinate> points = new TreeSet();
/* 195 */     for (int i = 0; i < mp.getNumGeometries(); i++) {
/* 196 */       Point pt = (Point)mp.getGeometryN(i);
/* 197 */       Coordinate p = pt.getCoordinate();
/* 198 */       if (points.contains(p)) {
/* 199 */         this.nonSimpleLocation = p;
/* 200 */         return false;
/*     */       } 
/* 202 */       points.add(p);
/*     */     } 
/* 204 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isSimplePolygonal(Geometry geom) {
/* 217 */     List rings = LinearComponentExtracter.getLines(geom);
/* 218 */     for (Iterator<LinearRing> i = rings.iterator(); i.hasNext(); ) {
/* 219 */       LinearRing ring = i.next();
/* 220 */       if (!isSimpleLinearGeometry((Geometry)ring))
/* 221 */         return false; 
/*     */     } 
/* 223 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isSimpleGeometryCollection(Geometry geom) {
/* 235 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 236 */       Geometry comp = geom.getGeometryN(i);
/* 237 */       if (!computeSimple(comp))
/* 238 */         return false; 
/*     */     } 
/* 240 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isSimpleLinearGeometry(Geometry geom) {
/* 245 */     if (geom.isEmpty())
/* 245 */       return true; 
/* 246 */     GeometryGraph graph = new GeometryGraph(0, geom);
/* 247 */     RobustLineIntersector robustLineIntersector = new RobustLineIntersector();
/* 248 */     SegmentIntersector si = graph.computeSelfNodes((LineIntersector)robustLineIntersector, true);
/* 250 */     if (!si.hasIntersection())
/* 250 */       return true; 
/* 251 */     if (si.hasProperIntersection()) {
/* 252 */       this.nonSimpleLocation = si.getProperIntersectionPoint();
/* 253 */       return false;
/*     */     } 
/* 255 */     if (hasNonEndpointIntersection(graph))
/* 255 */       return false; 
/* 256 */     if (this.isClosedEndpointsInInterior && 
/* 257 */       hasClosedEndpointIntersection(graph))
/* 257 */       return false; 
/* 259 */     return true;
/*     */   }
/*     */   
/*     */   private boolean hasNonEndpointIntersection(GeometryGraph graph) {
/* 268 */     for (Iterator<Edge> i = graph.getEdgeIterator(); i.hasNext(); ) {
/* 269 */       Edge e = i.next();
/* 270 */       int maxSegmentIndex = e.getMaximumSegmentIndex();
/* 271 */       for (Iterator<EdgeIntersection> eiIt = e.getEdgeIntersectionList().iterator(); eiIt.hasNext(); ) {
/* 272 */         EdgeIntersection ei = eiIt.next();
/* 273 */         if (!ei.isEndPoint(maxSegmentIndex)) {
/* 274 */           this.nonSimpleLocation = ei.getCoordinate();
/* 275 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 279 */     return false;
/*     */   }
/*     */   
/*     */   private static class EndpointInfo {
/*     */     Coordinate pt;
/*     */     
/*     */     boolean isClosed;
/*     */     
/*     */     int degree;
/*     */     
/*     */     public EndpointInfo(Coordinate pt) {
/* 289 */       this.pt = pt;
/* 290 */       this.isClosed = false;
/* 291 */       this.degree = 0;
/*     */     }
/*     */     
/*     */     public Coordinate getCoordinate() {
/* 294 */       return this.pt;
/*     */     }
/*     */     
/*     */     public void addEndpoint(boolean isClosed) {
/* 298 */       this.degree++;
/* 299 */       this.isClosed |= isClosed;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean hasClosedEndpointIntersection(GeometryGraph graph) {
/* 313 */     Map<Object, Object> endPoints = new TreeMap<>();
/* 314 */     for (Iterator<Edge> iterator = graph.getEdgeIterator(); iterator.hasNext(); ) {
/* 315 */       Edge e = iterator.next();
/* 316 */       int maxSegmentIndex = e.getMaximumSegmentIndex();
/* 317 */       boolean isClosed = e.isClosed();
/* 318 */       Coordinate p0 = e.getCoordinate(0);
/* 319 */       addEndpoint(endPoints, p0, isClosed);
/* 320 */       Coordinate p1 = e.getCoordinate(e.getNumPoints() - 1);
/* 321 */       addEndpoint(endPoints, p1, isClosed);
/*     */     } 
/* 324 */     for (Iterator<EndpointInfo> i = endPoints.values().iterator(); i.hasNext(); ) {
/* 325 */       EndpointInfo eiInfo = i.next();
/* 326 */       if (eiInfo.isClosed && eiInfo.degree != 2) {
/* 327 */         this.nonSimpleLocation = eiInfo.getCoordinate();
/* 328 */         return true;
/*     */       } 
/*     */     } 
/* 331 */     return false;
/*     */   }
/*     */   
/*     */   private void addEndpoint(Map<Coordinate, EndpointInfo> endPoints, Coordinate p, boolean isClosed) {
/* 339 */     EndpointInfo eiInfo = (EndpointInfo)endPoints.get(p);
/* 340 */     if (eiInfo == null) {
/* 341 */       eiInfo = new EndpointInfo(p);
/* 342 */       endPoints.put(p, eiInfo);
/*     */     } 
/* 344 */     eiInfo.addEndpoint(isClosed);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\IsSimpleOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */