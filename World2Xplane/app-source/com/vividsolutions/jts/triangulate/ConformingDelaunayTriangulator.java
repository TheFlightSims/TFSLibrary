/*     */ package com.vividsolutions.jts.triangulate;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.ConvexHull;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.index.kdtree.KdNode;
/*     */ import com.vividsolutions.jts.index.kdtree.KdTree;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.LastFoundQuadEdgeLocator;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdgeLocator;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdgeSubdivision;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.Vertex;
/*     */ import com.vividsolutions.jts.util.Debug;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ConformingDelaunayTriangulator {
/*     */   private List initialVertices;
/*     */   
/*     */   private List segVertices;
/*     */   
/*     */   private static Envelope computeVertexEnvelope(Collection vertices) {
/*  91 */     Envelope env = new Envelope();
/*  92 */     for (Iterator<Vertex> i = vertices.iterator(); i.hasNext(); ) {
/*  93 */       Vertex v = i.next();
/*  94 */       env.expandToInclude(v.getCoordinate());
/*     */     } 
/*  96 */     return env;
/*     */   }
/*     */   
/* 104 */   private List segments = new ArrayList();
/*     */   
/* 105 */   private QuadEdgeSubdivision subdiv = null;
/*     */   
/*     */   private IncrementalDelaunayTriangulator incDel;
/*     */   
/*     */   private Geometry convexHull;
/*     */   
/* 108 */   private ConstraintSplitPointFinder splitFinder = new NonEncroachingSplitPointFinder();
/*     */   
/* 109 */   private KdTree kdt = null;
/*     */   
/* 110 */   private ConstraintVertexFactory vertexFactory = null;
/*     */   
/*     */   private Envelope computeAreaEnv;
/*     */   
/* 115 */   private Coordinate splitPt = null;
/*     */   
/*     */   private double tolerance;
/*     */   
/*     */   private static final int MAX_SPLIT_ITER = 99;
/*     */   
/*     */   public ConformingDelaunayTriangulator(Collection<?> initialVertices, double tolerance) {
/* 131 */     this.initialVertices = new ArrayList(initialVertices);
/* 132 */     this.tolerance = tolerance;
/* 133 */     this.kdt = new KdTree(tolerance);
/*     */   }
/*     */   
/*     */   public void setConstraints(List segments, List segVertices) {
/* 149 */     this.segments = segments;
/* 150 */     this.segVertices = segVertices;
/*     */   }
/*     */   
/*     */   public void setSplitPointFinder(ConstraintSplitPointFinder splitFinder) {
/* 162 */     this.splitFinder = splitFinder;
/*     */   }
/*     */   
/*     */   public double getTolerance() {
/* 172 */     return this.tolerance;
/*     */   }
/*     */   
/*     */   public ConstraintVertexFactory getVertexFactory() {
/* 181 */     return this.vertexFactory;
/*     */   }
/*     */   
/*     */   public void setVertexFactory(ConstraintVertexFactory vertexFactory) {
/* 191 */     this.vertexFactory = vertexFactory;
/*     */   }
/*     */   
/*     */   public QuadEdgeSubdivision getSubdivision() {
/* 200 */     return this.subdiv;
/*     */   }
/*     */   
/*     */   public KdTree getKDT() {
/* 209 */     return this.kdt;
/*     */   }
/*     */   
/*     */   public List getInitialVertices() {
/* 218 */     return this.initialVertices;
/*     */   }
/*     */   
/*     */   public Collection getConstraintSegments() {
/* 227 */     return this.segments;
/*     */   }
/*     */   
/*     */   public Geometry getConvexHull() {
/* 238 */     return this.convexHull;
/*     */   }
/*     */   
/*     */   private void computeBoundingBox() {
/* 244 */     Envelope vertexEnv = computeVertexEnvelope(this.initialVertices);
/* 245 */     Envelope segEnv = computeVertexEnvelope(this.segVertices);
/* 247 */     Envelope allPointsEnv = new Envelope(vertexEnv);
/* 248 */     allPointsEnv.expandToInclude(segEnv);
/* 250 */     double deltaX = allPointsEnv.getWidth() * 0.2D;
/* 251 */     double deltaY = allPointsEnv.getHeight() * 0.2D;
/* 253 */     double delta = Math.max(deltaX, deltaY);
/* 255 */     this.computeAreaEnv = new Envelope(allPointsEnv);
/* 256 */     this.computeAreaEnv.expandBy(delta);
/*     */   }
/*     */   
/*     */   private void computeConvexHull() {
/* 260 */     GeometryFactory fact = new GeometryFactory();
/* 261 */     Coordinate[] coords = getPointArray();
/* 262 */     ConvexHull hull = new ConvexHull(coords, fact);
/* 263 */     this.convexHull = hull.getConvexHull();
/*     */   }
/*     */   
/*     */   private Coordinate[] getPointArray() {
/* 301 */     Coordinate[] pts = new Coordinate[this.initialVertices.size() + this.segVertices.size()];
/* 303 */     int index = 0;
/* 304 */     for (Iterator<Vertex> i = this.initialVertices.iterator(); i.hasNext(); ) {
/* 305 */       Vertex v = i.next();
/* 306 */       pts[index++] = v.getCoordinate();
/*     */     } 
/* 308 */     for (Iterator<Vertex> i2 = this.segVertices.iterator(); i2.hasNext(); ) {
/* 309 */       Vertex v = i2.next();
/* 310 */       pts[index++] = v.getCoordinate();
/*     */     } 
/* 312 */     return pts;
/*     */   }
/*     */   
/*     */   private ConstraintVertex createVertex(Coordinate p) {
/* 316 */     ConstraintVertex v = null;
/* 317 */     if (this.vertexFactory != null) {
/* 318 */       v = this.vertexFactory.createVertex(p, null);
/*     */     } else {
/* 320 */       v = new ConstraintVertex(p);
/*     */     } 
/* 321 */     return v;
/*     */   }
/*     */   
/*     */   private ConstraintVertex createVertex(Coordinate p, Segment seg) {
/* 332 */     ConstraintVertex v = null;
/* 333 */     if (this.vertexFactory != null) {
/* 334 */       v = this.vertexFactory.createVertex(p, seg);
/*     */     } else {
/* 336 */       v = new ConstraintVertex(p);
/*     */     } 
/* 337 */     v.setOnConstraint(true);
/* 338 */     return v;
/*     */   }
/*     */   
/*     */   private void insertSites(Collection vertices) {
/* 347 */     Debug.println("Adding sites: " + vertices.size());
/* 348 */     for (Iterator<ConstraintVertex> i = vertices.iterator(); i.hasNext(); ) {
/* 349 */       ConstraintVertex v = i.next();
/* 350 */       insertSite(v);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ConstraintVertex insertSite(ConstraintVertex v) {
/* 355 */     KdNode kdnode = this.kdt.insert(v.getCoordinate(), v);
/* 356 */     if (!kdnode.isRepeated()) {
/* 357 */       this.incDel.insertSite(v);
/*     */     } else {
/* 359 */       ConstraintVertex snappedV = (ConstraintVertex)kdnode.getData();
/* 360 */       snappedV.merge(v);
/* 361 */       return snappedV;
/*     */     } 
/* 367 */     return v;
/*     */   }
/*     */   
/*     */   public void insertSite(Coordinate p) {
/* 379 */     insertSite(createVertex(p));
/*     */   }
/*     */   
/*     */   public void formInitialDelaunay() {
/* 388 */     computeBoundingBox();
/* 389 */     this.subdiv = new QuadEdgeSubdivision(this.computeAreaEnv, this.tolerance);
/* 390 */     this.subdiv.setLocator((QuadEdgeLocator)new LastFoundQuadEdgeLocator(this.subdiv));
/* 391 */     this.incDel = new IncrementalDelaunayTriangulator(this.subdiv);
/* 392 */     insertSites(this.initialVertices);
/*     */   }
/*     */   
/*     */   public void enforceConstraints() {
/* 406 */     addConstraintVertices();
/* 409 */     int count = 0;
/* 410 */     int splits = 0;
/*     */     do {
/* 412 */       splits = enforceGabriel(this.segments);
/* 414 */       count++;
/* 415 */       Debug.println("Iter: " + count + "   Splits: " + splits + "   Current # segments = " + this.segments.size());
/* 417 */     } while (splits > 0 && count < 99);
/* 418 */     if (count == 99) {
/* 419 */       Debug.println("ABORTED! Too many iterations while enforcing constraints");
/* 420 */       if (!Debug.isDebugging())
/* 421 */         throw new ConstraintEnforcementException("Too many splitting iterations while enforcing constraints.  Last split point was at: ", this.splitPt); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addConstraintVertices() {
/* 428 */     computeConvexHull();
/* 430 */     insertSites(this.segVertices);
/*     */   }
/*     */   
/*     */   private int enforceGabriel(Collection<Segment> segsToInsert) {
/* 441 */     List<Segment> newSegments = new ArrayList();
/* 442 */     int splits = 0;
/* 443 */     List<Segment> segsToRemove = new ArrayList();
/* 451 */     for (Iterator<Segment> i = segsToInsert.iterator(); i.hasNext(); ) {
/* 452 */       Segment seg = i.next();
/* 455 */       Coordinate encroachPt = findNonGabrielPoint(seg);
/* 457 */       if (encroachPt == null)
/*     */         continue; 
/* 461 */       this.splitPt = this.splitFinder.findSplitPoint(seg, encroachPt);
/* 462 */       ConstraintVertex splitVertex = createVertex(this.splitPt, seg);
/* 482 */       ConstraintVertex insertedVertex = insertSite(splitVertex);
/* 483 */       if (!insertedVertex.getCoordinate().equals2D(this.splitPt))
/* 484 */         Debug.println("Split pt snapped to: " + insertedVertex); 
/* 492 */       Segment s1 = new Segment(seg.getStartX(), seg.getStartY(), seg.getStartZ(), splitVertex.getX(), splitVertex.getY(), splitVertex.getZ(), seg.getData());
/* 495 */       Segment s2 = new Segment(splitVertex.getX(), splitVertex.getY(), splitVertex.getZ(), seg.getEndX(), seg.getEndY(), seg.getEndZ(), seg.getData());
/* 498 */       newSegments.add(s1);
/* 499 */       newSegments.add(s2);
/* 500 */       segsToRemove.add(seg);
/* 502 */       splits++;
/*     */     } 
/* 504 */     segsToInsert.removeAll(segsToRemove);
/* 505 */     segsToInsert.addAll(newSegments);
/* 507 */     return splits;
/*     */   }
/*     */   
/*     */   private Coordinate findNonGabrielPoint(Segment seg) {
/* 528 */     Coordinate p = seg.getStart();
/* 529 */     Coordinate q = seg.getEnd();
/* 531 */     Coordinate midPt = new Coordinate((p.x + q.x) / 2.0D, (p.y + q.y) / 2.0D);
/* 532 */     double segRadius = p.distance(midPt);
/* 535 */     Envelope env = new Envelope(midPt);
/* 536 */     env.expandBy(segRadius);
/* 538 */     List result = this.kdt.query(env);
/* 542 */     Coordinate closestNonGabriel = null;
/* 543 */     double minDist = Double.MAX_VALUE;
/* 544 */     for (Iterator<KdNode> i = result.iterator(); i.hasNext(); ) {
/* 545 */       KdNode nextNode = i.next();
/* 546 */       Coordinate testPt = nextNode.getCoordinate();
/* 548 */       if (testPt.equals2D(p) || testPt.equals2D(q))
/*     */         continue; 
/* 551 */       double testRadius = midPt.distance(testPt);
/* 552 */       if (testRadius < segRadius) {
/* 554 */         double testDist = testRadius;
/* 555 */         if (closestNonGabriel == null || testDist < minDist) {
/* 556 */           closestNonGabriel = testPt;
/* 557 */           minDist = testDist;
/*     */         } 
/*     */       } 
/*     */     } 
/* 561 */     return closestNonGabriel;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\ConformingDelaunayTriangulator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */