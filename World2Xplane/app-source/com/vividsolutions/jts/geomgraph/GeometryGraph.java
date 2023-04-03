/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.BoundaryNodeRule;
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.PointLocator;
/*     */ import com.vividsolutions.jts.algorithm.locate.IndexedPointInAreaLocator;
/*     */ import com.vividsolutions.jts.algorithm.locate.PointOnGeometryLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geomgraph.index.EdgeSetIntersector;
/*     */ import com.vividsolutions.jts.geomgraph.index.SegmentIntersector;
/*     */ import com.vividsolutions.jts.geomgraph.index.SimpleMCSweepLineIntersector;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GeometryGraph extends PlanarGraph {
/*     */   private Geometry parentGeom;
/*     */   
/*     */   public static int determineBoundary(BoundaryNodeRule boundaryNodeRule, int boundaryCount) {
/*  78 */     return boundaryNodeRule.isInBoundary(boundaryCount) ? 1 : 0;
/*     */   }
/*     */   
/*  89 */   private Map lineEdgeMap = new HashMap<>();
/*     */   
/*  91 */   private BoundaryNodeRule boundaryNodeRule = null;
/*     */   
/*     */   private boolean useBoundaryDeterminationRule = true;
/*     */   
/*     */   private int argIndex;
/*     */   
/*     */   private Collection boundaryNodes;
/*     */   
/*     */   private boolean hasTooFewPoints = false;
/*     */   
/* 101 */   private Coordinate invalidPoint = null;
/*     */   
/* 103 */   private PointOnGeometryLocator areaPtLocator = null;
/*     */   
/* 105 */   private final PointLocator ptLocator = new PointLocator();
/*     */   
/*     */   private EdgeSetIntersector createEdgeSetIntersector() {
/* 118 */     return (EdgeSetIntersector)new SimpleMCSweepLineIntersector();
/*     */   }
/*     */   
/*     */   public GeometryGraph(int argIndex, Geometry parentGeom) {
/* 123 */     this(argIndex, parentGeom, BoundaryNodeRule.OGC_SFS_BOUNDARY_RULE);
/*     */   }
/*     */   
/*     */   public GeometryGraph(int argIndex, Geometry parentGeom, BoundaryNodeRule boundaryNodeRule) {
/* 129 */     this.argIndex = argIndex;
/* 130 */     this.parentGeom = parentGeom;
/* 131 */     this.boundaryNodeRule = boundaryNodeRule;
/* 132 */     if (parentGeom != null)
/* 135 */       add(parentGeom); 
/*     */   }
/*     */   
/*     */   public boolean hasTooFewPoints() {
/* 155 */     return this.hasTooFewPoints;
/*     */   }
/*     */   
/*     */   public Coordinate getInvalidPoint() {
/* 157 */     return this.invalidPoint;
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/* 159 */     return this.parentGeom;
/*     */   }
/*     */   
/*     */   public BoundaryNodeRule getBoundaryNodeRule() {
/* 161 */     return this.boundaryNodeRule;
/*     */   }
/*     */   
/*     */   public Collection getBoundaryNodes() {
/* 165 */     if (this.boundaryNodes == null)
/* 166 */       this.boundaryNodes = this.nodes.getBoundaryNodes(this.argIndex); 
/* 167 */     return this.boundaryNodes;
/*     */   }
/*     */   
/*     */   public Coordinate[] getBoundaryPoints() {
/* 172 */     Collection coll = getBoundaryNodes();
/* 173 */     Coordinate[] pts = new Coordinate[coll.size()];
/* 174 */     int i = 0;
/* 175 */     for (Iterator<Node> it = coll.iterator(); it.hasNext(); ) {
/* 176 */       Node node = it.next();
/* 177 */       pts[i++] = (Coordinate)node.getCoordinate().clone();
/*     */     } 
/* 179 */     return pts;
/*     */   }
/*     */   
/*     */   public Edge findEdge(LineString line) {
/* 184 */     return (Edge)this.lineEdgeMap.get(line);
/*     */   }
/*     */   
/*     */   public void computeSplitEdges(List edgelist) {
/* 189 */     for (Iterator<Edge> i = this.edges.iterator(); i.hasNext(); ) {
/* 190 */       Edge e = i.next();
/* 191 */       e.eiList.addSplitEdges(edgelist);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void add(Geometry g) {
/* 196 */     if (g.isEmpty())
/*     */       return; 
/* 200 */     if (g instanceof com.vividsolutions.jts.geom.MultiPolygon)
/* 201 */       this.useBoundaryDeterminationRule = false; 
/* 203 */     if (g instanceof Polygon) {
/* 203 */       addPolygon((Polygon)g);
/* 205 */     } else if (g instanceof LineString) {
/* 205 */       addLineString((LineString)g);
/* 206 */     } else if (g instanceof Point) {
/* 206 */       addPoint((Point)g);
/* 207 */     } else if (g instanceof com.vividsolutions.jts.geom.MultiPoint) {
/* 207 */       addCollection((GeometryCollection)g);
/* 208 */     } else if (g instanceof com.vividsolutions.jts.geom.MultiLineString) {
/* 208 */       addCollection((GeometryCollection)g);
/* 209 */     } else if (g instanceof com.vividsolutions.jts.geom.MultiPolygon) {
/* 209 */       addCollection((GeometryCollection)g);
/* 210 */     } else if (g instanceof GeometryCollection) {
/* 210 */       addCollection((GeometryCollection)g);
/*     */     } else {
/* 211 */       throw new UnsupportedOperationException(g.getClass().getName());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addCollection(GeometryCollection gc) {
/* 216 */     for (int i = 0; i < gc.getNumGeometries(); i++) {
/* 217 */       Geometry g = gc.getGeometryN(i);
/* 218 */       add(g);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addPoint(Point p) {
/* 226 */     Coordinate coord = p.getCoordinate();
/* 227 */     insertPoint(this.argIndex, coord, 0);
/*     */   }
/*     */   
/*     */   private void addPolygonRing(LinearRing lr, int cwLeft, int cwRight) {
/* 241 */     if (lr.isEmpty())
/*     */       return; 
/* 243 */     Coordinate[] coord = CoordinateArrays.removeRepeatedPoints(lr.getCoordinates());
/* 245 */     if (coord.length < 4) {
/* 246 */       this.hasTooFewPoints = true;
/* 247 */       this.invalidPoint = coord[0];
/*     */       return;
/*     */     } 
/* 251 */     int left = cwLeft;
/* 252 */     int right = cwRight;
/* 253 */     if (CGAlgorithms.isCCW(coord)) {
/* 254 */       left = cwRight;
/* 255 */       right = cwLeft;
/*     */     } 
/* 257 */     Edge e = new Edge(coord, new Label(this.argIndex, 1, left, right));
/* 259 */     this.lineEdgeMap.put(lr, e);
/* 261 */     insertEdge(e);
/* 263 */     insertPoint(this.argIndex, coord[0], 1);
/*     */   }
/*     */   
/*     */   private void addPolygon(Polygon p) {
/* 268 */     addPolygonRing((LinearRing)p.getExteriorRing(), 2, 0);
/* 273 */     for (int i = 0; i < p.getNumInteriorRing(); i++) {
/* 274 */       LinearRing hole = (LinearRing)p.getInteriorRingN(i);
/* 279 */       addPolygonRing(hole, 0, 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addLineString(LineString line) {
/* 288 */     Coordinate[] coord = CoordinateArrays.removeRepeatedPoints(line.getCoordinates());
/* 290 */     if (coord.length < 2) {
/* 291 */       this.hasTooFewPoints = true;
/* 292 */       this.invalidPoint = coord[0];
/*     */       return;
/*     */     } 
/* 298 */     Edge e = new Edge(coord, new Label(this.argIndex, 0));
/* 299 */     this.lineEdgeMap.put(line, e);
/* 300 */     insertEdge(e);
/* 306 */     Assert.isTrue((coord.length >= 2), "found LineString with single point");
/* 307 */     insertBoundaryPoint(this.argIndex, coord[0]);
/* 308 */     insertBoundaryPoint(this.argIndex, coord[coord.length - 1]);
/*     */   }
/*     */   
/*     */   public void addEdge(Edge e) {
/* 318 */     insertEdge(e);
/* 319 */     Coordinate[] coord = e.getCoordinates();
/* 321 */     insertPoint(this.argIndex, coord[0], 1);
/* 322 */     insertPoint(this.argIndex, coord[coord.length - 1], 1);
/*     */   }
/*     */   
/*     */   public void addPoint(Coordinate pt) {
/* 331 */     insertPoint(this.argIndex, pt, 0);
/*     */   }
/*     */   
/*     */   public SegmentIntersector computeSelfNodes(LineIntersector li, boolean computeRingSelfNodes) {
/* 344 */     SegmentIntersector si = new SegmentIntersector(li, true, false);
/* 345 */     EdgeSetIntersector esi = createEdgeSetIntersector();
/* 347 */     if (!computeRingSelfNodes && (this.parentGeom instanceof LinearRing || this.parentGeom instanceof Polygon || this.parentGeom instanceof com.vividsolutions.jts.geom.MultiPolygon)) {
/* 351 */       esi.computeIntersections(this.edges, si, false);
/*     */     } else {
/* 354 */       esi.computeIntersections(this.edges, si, true);
/*     */     } 
/* 357 */     addSelfIntersectionNodes(this.argIndex);
/* 358 */     return si;
/*     */   }
/*     */   
/*     */   public SegmentIntersector computeEdgeIntersections(GeometryGraph g, LineIntersector li, boolean includeProper) {
/* 366 */     SegmentIntersector si = new SegmentIntersector(li, includeProper, true);
/* 367 */     si.setBoundaryNodes(getBoundaryNodes(), g.getBoundaryNodes());
/* 369 */     EdgeSetIntersector esi = createEdgeSetIntersector();
/* 370 */     esi.computeIntersections(this.edges, g.edges, si);
/* 377 */     return si;
/*     */   }
/*     */   
/*     */   private void insertPoint(int argIndex, Coordinate coord, int onLocation) {
/* 382 */     Node n = this.nodes.addNode(coord);
/* 383 */     Label lbl = n.getLabel();
/* 384 */     if (lbl == null) {
/* 385 */       n.label = new Label(argIndex, onLocation);
/*     */     } else {
/* 388 */       lbl.setLocation(argIndex, onLocation);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void insertBoundaryPoint(int argIndex, Coordinate coord) {
/* 398 */     Node n = this.nodes.addNode(coord);
/* 400 */     Label lbl = n.getLabel();
/* 402 */     int boundaryCount = 1;
/* 404 */     int loc = -1;
/* 405 */     loc = lbl.getLocation(argIndex, 0);
/* 406 */     if (loc == 1)
/* 406 */       boundaryCount++; 
/* 409 */     int newLoc = determineBoundary(this.boundaryNodeRule, boundaryCount);
/* 410 */     lbl.setLocation(argIndex, newLoc);
/*     */   }
/*     */   
/*     */   private void addSelfIntersectionNodes(int argIndex) {
/* 415 */     for (Iterator<Edge> i = this.edges.iterator(); i.hasNext(); ) {
/* 416 */       Edge e = i.next();
/* 417 */       int eLoc = e.getLabel().getLocation(argIndex);
/* 418 */       for (Iterator<EdgeIntersection> eiIt = e.eiList.iterator(); eiIt.hasNext(); ) {
/* 419 */         EdgeIntersection ei = eiIt.next();
/* 420 */         addSelfIntersectionNode(argIndex, ei.coord, eLoc);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addSelfIntersectionNode(int argIndex, Coordinate coord, int loc) {
/* 433 */     if (isBoundaryNode(argIndex, coord))
/*     */       return; 
/* 434 */     if (loc == 1 && this.useBoundaryDeterminationRule) {
/* 435 */       insertBoundaryPoint(argIndex, coord);
/*     */     } else {
/* 437 */       insertPoint(argIndex, coord, loc);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int locate(Coordinate pt) {
/* 450 */     if (this.parentGeom instanceof com.vividsolutions.jts.geom.Polygonal && this.parentGeom.getNumGeometries() > 50) {
/* 452 */       if (this.areaPtLocator == null)
/* 453 */         this.areaPtLocator = (PointOnGeometryLocator)new IndexedPointInAreaLocator(this.parentGeom); 
/* 455 */       return this.areaPtLocator.locate(pt);
/*     */     } 
/* 457 */     return this.ptLocator.locate(pt, this.parentGeom);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\GeometryGraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */