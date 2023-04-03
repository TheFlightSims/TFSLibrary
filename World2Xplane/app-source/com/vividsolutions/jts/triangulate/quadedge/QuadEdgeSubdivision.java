/*     */ package com.vividsolutions.jts.triangulate.quadedge;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.Triangle;
/*     */ import com.vividsolutions.jts.io.WKTWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.Stack;
/*     */ 
/*     */ public class QuadEdgeSubdivision {
/*     */   private static final double EDGE_COINCIDENCE_TOL_FACTOR = 1000.0D;
/*     */   
/*     */   public static void getTriangleEdges(QuadEdge startQE, QuadEdge[] triEdge) {
/*  77 */     triEdge[0] = startQE;
/*  78 */     triEdge[1] = triEdge[0].lNext();
/*  79 */     triEdge[2] = triEdge[1].lNext();
/*  80 */     if (triEdge[2].lNext() != triEdge[0])
/*  81 */       throw new IllegalArgumentException("Edges do not form a triangle"); 
/*     */   }
/*     */   
/*  90 */   private int visitedKey = 0;
/*     */   
/*  92 */   private List quadEdges = new ArrayList();
/*     */   
/*     */   private QuadEdge startingEdge;
/*     */   
/*     */   private double tolerance;
/*     */   
/*     */   private double edgeCoincidenceTolerance;
/*     */   
/*  96 */   private Vertex[] frameVertex = new Vertex[3];
/*     */   
/*     */   private Envelope frameEnv;
/*     */   
/*  98 */   private QuadEdgeLocator locator = null;
/*     */   
/*     */   private LineSegment seg;
/*     */   
/*     */   private QuadEdge[] triEdges;
/*     */   
/*     */   private void createFrame(Envelope env) {
/* 123 */     double deltaX = env.getWidth();
/* 124 */     double deltaY = env.getHeight();
/* 125 */     double offset = 0.0D;
/* 126 */     if (deltaX > deltaY) {
/* 127 */       offset = deltaX * 10.0D;
/*     */     } else {
/* 129 */       offset = deltaY * 10.0D;
/*     */     } 
/* 132 */     this.frameVertex[0] = new Vertex((env.getMaxX() + env.getMinX()) / 2.0D, env.getMaxY() + offset);
/* 135 */     this.frameVertex[1] = new Vertex(env.getMinX() - offset, env.getMinY() - offset);
/* 136 */     this.frameVertex[2] = new Vertex(env.getMaxX() + offset, env.getMinY() - offset);
/* 138 */     this.frameEnv = new Envelope(this.frameVertex[0].getCoordinate(), this.frameVertex[1].getCoordinate());
/* 140 */     this.frameEnv.expandToInclude(this.frameVertex[2].getCoordinate());
/*     */   }
/*     */   
/*     */   private QuadEdge initSubdiv() {
/* 146 */     QuadEdge ea = makeEdge(this.frameVertex[0], this.frameVertex[1]);
/* 147 */     QuadEdge eb = makeEdge(this.frameVertex[1], this.frameVertex[2]);
/* 148 */     QuadEdge.splice(ea.sym(), eb);
/* 149 */     QuadEdge ec = makeEdge(this.frameVertex[2], this.frameVertex[0]);
/* 150 */     QuadEdge.splice(eb.sym(), ec);
/* 151 */     QuadEdge.splice(ec.sym(), ea);
/* 152 */     return ea;
/*     */   }
/*     */   
/*     */   public double getTolerance() {
/* 162 */     return this.tolerance;
/*     */   }
/*     */   
/*     */   public Envelope getEnvelope() {
/* 171 */     return new Envelope(this.frameEnv);
/*     */   }
/*     */   
/*     */   public Collection getEdges() {
/* 181 */     return this.quadEdges;
/*     */   }
/*     */   
/*     */   public void setLocator(QuadEdgeLocator locator) {
/* 192 */     this.locator = locator;
/*     */   }
/*     */   
/*     */   public QuadEdge makeEdge(Vertex o, Vertex d) {
/* 203 */     QuadEdge q = QuadEdge.makeEdge(o, d);
/* 204 */     this.quadEdges.add(q);
/* 205 */     return q;
/*     */   }
/*     */   
/*     */   public QuadEdge connect(QuadEdge a, QuadEdge b) {
/* 218 */     QuadEdge q = QuadEdge.connect(a, b);
/* 219 */     this.quadEdges.add(q);
/* 220 */     return q;
/*     */   }
/*     */   
/*     */   public void delete(QuadEdge e) {
/* 231 */     QuadEdge.splice(e, e.oPrev());
/* 232 */     QuadEdge.splice(e.sym(), e.sym().oPrev());
/* 234 */     QuadEdge eSym = e.sym();
/* 235 */     QuadEdge eRot = e.rot();
/* 236 */     QuadEdge eRotSym = e.rot().sym();
/* 239 */     this.quadEdges.remove(e);
/* 240 */     this.quadEdges.remove(eSym);
/* 241 */     this.quadEdges.remove(eRot);
/* 242 */     this.quadEdges.remove(eRotSym);
/* 244 */     e.delete();
/* 245 */     eSym.delete();
/* 246 */     eRot.delete();
/* 247 */     eRotSym.delete();
/*     */   }
/*     */   
/*     */   public QuadEdge locateFromEdge(Vertex v, QuadEdge startEdge) {
/* 268 */     int iter = 0;
/* 269 */     int maxIter = this.quadEdges.size();
/* 271 */     QuadEdge e = startEdge;
/*     */     while (true) {
/* 274 */       iter++;
/* 285 */       if (iter > maxIter)
/* 286 */         throw new LocateFailureException(e.toLineSegment()); 
/* 294 */       if (v.equals(e.orig()) || v.equals(e.dest()))
/*     */         break; 
/* 296 */       if (v.rightOf(e)) {
/* 297 */         e = e.sym();
/*     */         continue;
/*     */       } 
/* 298 */       if (!v.rightOf(e.oNext())) {
/* 299 */         e = e.oNext();
/*     */         continue;
/*     */       } 
/* 300 */       if (!v.rightOf(e.dPrev())) {
/* 301 */         e = e.dPrev();
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 308 */     return e;
/*     */   }
/*     */   
/*     */   public QuadEdge locate(Vertex v) {
/* 320 */     return this.locator.locate(v);
/*     */   }
/*     */   
/*     */   public QuadEdge locate(Coordinate p) {
/* 332 */     return this.locator.locate(new Vertex(p));
/*     */   }
/*     */   
/*     */   public QuadEdge locate(Coordinate p0, Coordinate p1) {
/* 346 */     QuadEdge e = this.locator.locate(new Vertex(p0));
/* 347 */     if (e == null)
/* 348 */       return null; 
/* 351 */     QuadEdge base = e;
/* 352 */     if (e.dest().getCoordinate().equals2D(p0))
/* 353 */       base = e.sym(); 
/* 355 */     QuadEdge locEdge = base;
/*     */     while (true) {
/* 357 */       if (locEdge.dest().getCoordinate().equals2D(p1))
/* 358 */         return locEdge; 
/* 359 */       locEdge = locEdge.oNext();
/* 360 */       if (locEdge == base)
/* 361 */         return null; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public QuadEdge insertSite(Vertex v) {
/* 381 */     QuadEdge e = locate(v);
/* 383 */     if (v.equals(e.orig(), this.tolerance) || v.equals(e.dest(), this.tolerance))
/* 384 */       return e; 
/* 390 */     QuadEdge base = makeEdge(e.orig(), v);
/* 391 */     QuadEdge.splice(base, e);
/* 392 */     QuadEdge startEdge = base;
/*     */     do {
/* 394 */       base = connect(e, base.sym());
/* 395 */       e = base.oPrev();
/* 396 */     } while (e.lNext() != startEdge);
/* 398 */     return startEdge;
/*     */   }
/*     */   
/*     */   public boolean isFrameEdge(QuadEdge e) {
/* 409 */     if (isFrameVertex(e.orig()) || isFrameVertex(e.dest()))
/* 410 */       return true; 
/* 411 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isFrameBorderEdge(QuadEdge e) {
/* 425 */     QuadEdge[] leftTri = new QuadEdge[3];
/* 426 */     getTriangleEdges(e, leftTri);
/* 428 */     QuadEdge[] rightTri = new QuadEdge[3];
/* 429 */     getTriangleEdges(e.sym(), rightTri);
/* 433 */     Vertex vLeftTriOther = e.lNext().dest();
/* 434 */     if (isFrameVertex(vLeftTriOther))
/* 435 */       return true; 
/* 437 */     Vertex vRightTriOther = e.sym().lNext().dest();
/* 438 */     if (isFrameVertex(vRightTriOther))
/* 439 */       return true; 
/* 441 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isFrameVertex(Vertex v) {
/* 452 */     if (v.equals(this.frameVertex[0]))
/* 453 */       return true; 
/* 454 */     if (v.equals(this.frameVertex[1]))
/* 455 */       return true; 
/* 456 */     if (v.equals(this.frameVertex[2]))
/* 457 */       return true; 
/* 458 */     return false;
/*     */   }
/*     */   
/*     */   public QuadEdgeSubdivision(Envelope env, double tolerance) {
/* 461 */     this.seg = new LineSegment();
/* 674 */     this.triEdges = new QuadEdge[3];
/*     */     this.tolerance = tolerance;
/*     */     this.edgeCoincidenceTolerance = tolerance / 1000.0D;
/*     */     createFrame(env);
/*     */     this.startingEdge = initSubdiv();
/*     */     this.locator = new LastFoundQuadEdgeLocator(this);
/*     */   }
/*     */   
/*     */   public boolean isOnEdge(QuadEdge e, Coordinate p) {
/*     */     this.seg.setCoordinates(e.orig().getCoordinate(), e.dest().getCoordinate());
/*     */     double dist = this.seg.distance(p);
/*     */     return (dist < this.edgeCoincidenceTolerance);
/*     */   }
/*     */   
/*     */   public boolean isVertexOfEdge(QuadEdge e, Vertex v) {
/*     */     if (v.equals(e.orig(), this.tolerance) || v.equals(e.dest(), this.tolerance))
/*     */       return true; 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public Collection getVertices(boolean includeFrame) {
/*     */     Set<Vertex> vertices = new HashSet();
/*     */     for (Iterator<QuadEdge> i = this.quadEdges.iterator(); i.hasNext(); ) {
/*     */       QuadEdge qe = i.next();
/*     */       Vertex v = qe.orig();
/*     */       if (includeFrame || !isFrameVertex(v))
/*     */         vertices.add(v); 
/*     */       Vertex vd = qe.dest();
/*     */       if (includeFrame || !isFrameVertex(vd))
/*     */         vertices.add(vd); 
/*     */     } 
/*     */     return vertices;
/*     */   }
/*     */   
/*     */   public List getVertexUniqueEdges(boolean includeFrame) {
/*     */     List<QuadEdge> edges = new ArrayList();
/*     */     Set<Vertex> visitedVertices = new HashSet();
/*     */     for (Iterator<QuadEdge> i = this.quadEdges.iterator(); i.hasNext(); ) {
/*     */       QuadEdge qe = i.next();
/*     */       Vertex v = qe.orig();
/*     */       if (!visitedVertices.contains(v)) {
/*     */         visitedVertices.add(v);
/*     */         if (includeFrame || !isFrameVertex(v))
/*     */           edges.add(qe); 
/*     */       } 
/*     */       QuadEdge qd = qe.sym();
/*     */       Vertex vd = qd.orig();
/*     */       if (!visitedVertices.contains(vd)) {
/*     */         visitedVertices.add(vd);
/*     */         if (includeFrame || !isFrameVertex(vd))
/*     */           edges.add(qd); 
/*     */       } 
/*     */     } 
/*     */     return edges;
/*     */   }
/*     */   
/*     */   public List getPrimaryEdges(boolean includeFrame) {
/*     */     this.visitedKey++;
/*     */     List<QuadEdge> edges = new ArrayList();
/*     */     Stack<QuadEdge> edgeStack = new Stack();
/*     */     edgeStack.push(this.startingEdge);
/*     */     Set<QuadEdge> visitedEdges = new HashSet();
/*     */     while (!edgeStack.empty()) {
/*     */       QuadEdge edge = edgeStack.pop();
/*     */       if (!visitedEdges.contains(edge)) {
/*     */         QuadEdge priQE = edge.getPrimary();
/*     */         if (includeFrame || !isFrameEdge(priQE))
/*     */           edges.add(priQE); 
/*     */         edgeStack.push(edge.oNext());
/*     */         edgeStack.push(edge.sym().oNext());
/*     */         visitedEdges.add(edge);
/*     */         visitedEdges.add(edge.sym());
/*     */       } 
/*     */     } 
/*     */     return edges;
/*     */   }
/*     */   
/*     */   private static class TriangleCircumcentreVisitor implements TriangleVisitor {
/*     */     public void visit(QuadEdge[] triEdges) {
/*     */       Coordinate a = triEdges[0].orig().getCoordinate();
/*     */       Coordinate b = triEdges[1].orig().getCoordinate();
/*     */       Coordinate c = triEdges[2].orig().getCoordinate();
/*     */       Coordinate cc = Triangle.circumcentre(a, b, c);
/*     */       Vertex ccVertex = new Vertex(cc);
/*     */       for (int i = 0; i < 3; i++)
/*     */         triEdges[i].rot().setOrig(ccVertex); 
/*     */     }
/*     */   }
/*     */   
/*     */   public void visitTriangles(TriangleVisitor triVisitor, boolean includeFrame) {
/*     */     this.visitedKey++;
/*     */     Stack<QuadEdge> edgeStack = new Stack();
/*     */     edgeStack.push(this.startingEdge);
/*     */     Set visitedEdges = new HashSet();
/*     */     while (!edgeStack.empty()) {
/*     */       QuadEdge edge = edgeStack.pop();
/*     */       if (!visitedEdges.contains(edge)) {
/*     */         QuadEdge[] triEdges = fetchTriangleToVisit(edge, edgeStack, includeFrame, visitedEdges);
/*     */         if (triEdges != null)
/*     */           triVisitor.visit(triEdges); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private QuadEdge[] fetchTriangleToVisit(QuadEdge edge, Stack<QuadEdge> edgeStack, boolean includeFrame, Set<QuadEdge> visitedEdges) {
/* 689 */     QuadEdge curr = edge;
/* 690 */     int edgeCount = 0;
/* 691 */     boolean isFrame = false;
/*     */     do {
/* 693 */       this.triEdges[edgeCount] = curr;
/* 695 */       if (isFrameEdge(curr))
/* 696 */         isFrame = true; 
/* 699 */       QuadEdge sym = curr.sym();
/* 700 */       if (!visitedEdges.contains(sym))
/* 701 */         edgeStack.push(sym); 
/* 704 */       visitedEdges.add(curr);
/* 706 */       edgeCount++;
/* 707 */       curr = curr.lNext();
/* 708 */     } while (curr != edge);
/* 710 */     if (isFrame && !includeFrame)
/* 711 */       return null; 
/* 712 */     return this.triEdges;
/*     */   }
/*     */   
/*     */   public List getTriangleEdges(boolean includeFrame) {
/* 725 */     TriangleEdgesListVisitor visitor = new TriangleEdgesListVisitor();
/* 726 */     visitTriangles(visitor, includeFrame);
/* 727 */     return visitor.getTriangleEdges();
/*     */   }
/*     */   
/*     */   private static class TriangleEdgesListVisitor implements TriangleVisitor {
/* 731 */     private List triList = new ArrayList();
/*     */     
/*     */     public void visit(QuadEdge[] triEdges) {
/* 734 */       this.triList.add(triEdges.clone());
/*     */     }
/*     */     
/*     */     public List getTriangleEdges() {
/* 738 */       return this.triList;
/*     */     }
/*     */     
/*     */     private TriangleEdgesListVisitor() {}
/*     */   }
/*     */   
/*     */   public List getTriangleVertices(boolean includeFrame) {
/* 751 */     TriangleVertexListVisitor visitor = new TriangleVertexListVisitor();
/* 752 */     visitTriangles(visitor, includeFrame);
/* 753 */     return visitor.getTriangleVertices();
/*     */   }
/*     */   
/*     */   private static class TriangleVertexListVisitor implements TriangleVisitor {
/* 757 */     private List triList = new ArrayList();
/*     */     
/*     */     public void visit(QuadEdge[] triEdges) {
/* 760 */       this.triList.add(new Vertex[] { triEdges[0].orig(), triEdges[1].orig(), triEdges[2].orig() });
/*     */     }
/*     */     
/*     */     public List getTriangleVertices() {
/* 765 */       return this.triList;
/*     */     }
/*     */     
/*     */     private TriangleVertexListVisitor() {}
/*     */   }
/*     */   
/*     */   public List getTriangleCoordinates(boolean includeFrame) {
/* 777 */     TriangleCoordinatesVisitor visitor = new TriangleCoordinatesVisitor();
/* 778 */     visitTriangles(visitor, includeFrame);
/* 779 */     return visitor.getTriangles();
/*     */   }
/*     */   
/*     */   private static class TriangleCoordinatesVisitor implements TriangleVisitor {
/* 783 */     private CoordinateList coordList = new CoordinateList();
/*     */     
/* 785 */     private List triCoords = new ArrayList();
/*     */     
/*     */     public void visit(QuadEdge[] triEdges) {
/* 791 */       this.coordList.clear();
/* 792 */       for (int i = 0; i < 3; i++) {
/* 793 */         Vertex v = triEdges[i].orig();
/* 794 */         this.coordList.add(v.getCoordinate());
/*     */       } 
/* 796 */       if (this.coordList.size() > 0) {
/* 797 */         this.coordList.closeRing();
/* 798 */         Coordinate[] pts = this.coordList.toCoordinateArray();
/* 799 */         if (pts.length != 4)
/*     */           return; 
/* 804 */         this.triCoords.add(pts);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void checkTriangleSize(Coordinate[] pts) {
/* 810 */       String loc = "";
/* 811 */       if (pts.length >= 2) {
/* 812 */         loc = WKTWriter.toLineString(pts[0], pts[1]);
/* 814 */       } else if (pts.length >= 1) {
/* 815 */         loc = WKTWriter.toPoint(pts[0]);
/*     */       } 
/*     */     }
/*     */     
/*     */     public List getTriangles() {
/* 822 */       return this.triCoords;
/*     */     }
/*     */   }
/*     */   
/*     */   public Geometry getEdges(GeometryFactory geomFact) {
/* 834 */     List quadEdges = getPrimaryEdges(false);
/* 835 */     LineString[] edges = new LineString[quadEdges.size()];
/* 836 */     int i = 0;
/* 837 */     for (Iterator<QuadEdge> it = quadEdges.iterator(); it.hasNext(); ) {
/* 838 */       QuadEdge qe = it.next();
/* 839 */       edges[i++] = geomFact.createLineString(new Coordinate[] { qe.orig().getCoordinate(), qe.dest().getCoordinate() });
/*     */     } 
/* 842 */     return (Geometry)geomFact.createMultiLineString(edges);
/*     */   }
/*     */   
/*     */   public Geometry getTriangles(GeometryFactory geomFact) {
/* 853 */     List triPtsList = getTriangleCoordinates(false);
/* 854 */     Polygon[] tris = new Polygon[triPtsList.size()];
/* 855 */     int i = 0;
/* 856 */     for (Iterator<Coordinate[]> it = triPtsList.iterator(); it.hasNext(); ) {
/* 857 */       Coordinate[] triPt = it.next();
/* 858 */       tris[i++] = geomFact.createPolygon(geomFact.createLinearRing(triPt), null);
/*     */     } 
/* 861 */     return (Geometry)geomFact.createGeometryCollection((Geometry[])tris);
/*     */   }
/*     */   
/*     */   public Geometry getVoronoiDiagram(GeometryFactory geomFact) {
/* 877 */     List vorCells = getVoronoiCellPolygons(geomFact);
/* 878 */     return (Geometry)geomFact.createGeometryCollection(GeometryFactory.toGeometryArray(vorCells));
/*     */   }
/*     */   
/*     */   public List getVoronoiCellPolygons(GeometryFactory geomFact) {
/* 900 */     visitTriangles(new TriangleCircumcentreVisitor(), true);
/* 902 */     List<Polygon> cells = new ArrayList();
/* 903 */     Collection edges = getVertexUniqueEdges(false);
/* 904 */     for (Iterator<QuadEdge> i = edges.iterator(); i.hasNext(); ) {
/* 905 */       QuadEdge qe = i.next();
/* 906 */       cells.add(getVoronoiCellPolygon(qe, geomFact));
/*     */     } 
/* 908 */     return cells;
/*     */   }
/*     */   
/*     */   public Polygon getVoronoiCellPolygon(QuadEdge qe, GeometryFactory geomFact) {
/* 925 */     List<Coordinate> cellPts = new ArrayList();
/* 926 */     QuadEdge startQE = qe;
/*     */     do {
/* 930 */       Coordinate cc = qe.rot().orig().getCoordinate();
/* 931 */       cellPts.add(cc);
/* 934 */       qe = qe.oPrev();
/* 935 */     } while (qe != startQE);
/* 937 */     CoordinateList coordList = new CoordinateList();
/* 938 */     coordList.addAll(cellPts, false);
/* 939 */     coordList.closeRing();
/* 941 */     if (coordList.size() < 4) {
/* 942 */       System.out.println(coordList);
/* 943 */       coordList.add(coordList.get(coordList.size() - 1), true);
/*     */     } 
/* 946 */     Coordinate[] pts = coordList.toCoordinateArray();
/* 947 */     Polygon cellPoly = geomFact.createPolygon(geomFact.createLinearRing(pts), null);
/* 949 */     Vertex v = startQE.orig();
/* 950 */     cellPoly.setUserData(v.getCoordinate());
/* 951 */     return cellPoly;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\quadedge\QuadEdgeSubdivision.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */