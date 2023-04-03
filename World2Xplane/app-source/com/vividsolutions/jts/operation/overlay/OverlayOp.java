/*     */ package com.vividsolutions.jts.operation.overlay;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.PointLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geomgraph.Depth;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdgeStar;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeList;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeNodingValidator;
/*     */ import com.vividsolutions.jts.geomgraph.Label;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import com.vividsolutions.jts.geomgraph.PlanarGraph;
/*     */ import com.vividsolutions.jts.operation.GeometryGraphOperation;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class OverlayOp extends GeometryGraphOperation {
/*     */   public static final int INTERSECTION = 1;
/*     */   
/*     */   public static final int UNION = 2;
/*     */   
/*     */   public static final int DIFFERENCE = 3;
/*     */   
/*     */   public static final int SYMDIFFERENCE = 4;
/*     */   
/*     */   public static Geometry overlayOp(Geometry geom0, Geometry geom1, int opCode) {
/*  91 */     OverlayOp gov = new OverlayOp(geom0, geom1);
/*  92 */     Geometry geomOv = gov.getResultGeometry(opCode);
/*  93 */     return geomOv;
/*     */   }
/*     */   
/*     */   public static boolean isResultOfOp(Label label, int opCode) {
/* 110 */     int loc0 = label.getLocation(0);
/* 111 */     int loc1 = label.getLocation(1);
/* 112 */     return isResultOfOp(loc0, loc1, opCode);
/*     */   }
/*     */   
/*     */   public static boolean isResultOfOp(int loc0, int loc1, int overlayOpCode) {
/* 130 */     if (loc0 == 1)
/* 130 */       loc0 = 0; 
/* 131 */     if (loc1 == 1)
/* 131 */       loc1 = 0; 
/* 132 */     switch (overlayOpCode) {
/*     */       case 1:
/* 134 */         return (loc0 == 0 && loc1 == 0);
/*     */       case 2:
/* 137 */         return (loc0 == 0 || loc1 == 0);
/*     */       case 3:
/* 140 */         return (loc0 == 0 && loc1 != 0);
/*     */       case 4:
/* 143 */         return ((loc0 == 0 && loc1 != 0) || (loc0 != 0 && loc1 == 0));
/*     */     } 
/* 146 */     return false;
/*     */   }
/*     */   
/* 149 */   private final PointLocator ptLocator = new PointLocator();
/*     */   
/*     */   private GeometryFactory geomFact;
/*     */   
/*     */   private Geometry resultGeom;
/*     */   
/*     */   private PlanarGraph graph;
/*     */   
/* 154 */   private EdgeList edgeList = new EdgeList();
/*     */   
/* 156 */   private List resultPolyList = new ArrayList();
/*     */   
/* 157 */   private List resultLineList = new ArrayList();
/*     */   
/* 158 */   private List resultPointList = new ArrayList();
/*     */   
/*     */   public OverlayOp(Geometry g0, Geometry g1) {
/* 168 */     super(g0, g1);
/* 169 */     this.graph = new PlanarGraph(new OverlayNodeFactory());
/* 175 */     this.geomFact = g0.getFactory();
/*     */   }
/*     */   
/*     */   public Geometry getResultGeometry(int overlayOpCode) {
/* 189 */     computeOverlay(overlayOpCode);
/* 190 */     return this.resultGeom;
/*     */   }
/*     */   
/*     */   public PlanarGraph getGraph() {
/* 198 */     return this.graph;
/*     */   }
/*     */   
/*     */   private void computeOverlay(int opCode) {
/* 205 */     copyPoints(0);
/* 206 */     copyPoints(1);
/* 209 */     this.arg[0].computeSelfNodes(this.li, false);
/* 210 */     this.arg[1].computeSelfNodes(this.li, false);
/* 213 */     this.arg[0].computeEdgeIntersections(this.arg[1], this.li, true);
/* 215 */     List baseSplitEdges = new ArrayList();
/* 216 */     this.arg[0].computeSplitEdges(baseSplitEdges);
/* 217 */     this.arg[1].computeSplitEdges(baseSplitEdges);
/* 218 */     List splitEdges = baseSplitEdges;
/* 220 */     insertUniqueEdges(baseSplitEdges);
/* 222 */     computeLabelsFromDepths();
/* 223 */     replaceCollapsedEdges();
/* 237 */     EdgeNodingValidator.checkValid(this.edgeList.getEdges());
/* 239 */     this.graph.addEdges(this.edgeList.getEdges());
/* 240 */     computeLabelling();
/* 242 */     labelIncompleteNodes();
/* 252 */     findResultAreaEdges(opCode);
/* 253 */     cancelDuplicateResultEdges();
/* 255 */     PolygonBuilder polyBuilder = new PolygonBuilder(this.geomFact);
/* 256 */     polyBuilder.add(this.graph);
/* 257 */     this.resultPolyList = polyBuilder.getPolygons();
/* 259 */     LineBuilder lineBuilder = new LineBuilder(this, this.geomFact, this.ptLocator);
/* 260 */     this.resultLineList = lineBuilder.build(opCode);
/* 262 */     PointBuilder pointBuilder = new PointBuilder(this, this.geomFact, this.ptLocator);
/* 263 */     this.resultPointList = pointBuilder.build(opCode);
/* 266 */     this.resultGeom = computeGeometry(this.resultPointList, this.resultLineList, this.resultPolyList, opCode);
/*     */   }
/*     */   
/*     */   private void insertUniqueEdges(List edges) {
/* 271 */     for (Iterator<Edge> i = edges.iterator(); i.hasNext(); ) {
/* 272 */       Edge e = i.next();
/* 273 */       insertUniqueEdge(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void insertUniqueEdge(Edge e) {
/* 287 */     Edge existingEdge = this.edgeList.findEqualEdge(e);
/* 290 */     if (existingEdge != null) {
/* 291 */       Label existingLabel = existingEdge.getLabel();
/* 293 */       Label labelToMerge = e.getLabel();
/* 296 */       if (!existingEdge.isPointwiseEqual(e)) {
/* 297 */         labelToMerge = new Label(e.getLabel());
/* 298 */         labelToMerge.flip();
/*     */       } 
/* 300 */       Depth depth = existingEdge.getDepth();
/* 303 */       if (depth.isNull())
/* 304 */         depth.add(existingLabel); 
/* 307 */       depth.add(labelToMerge);
/* 308 */       existingLabel.merge(labelToMerge);
/*     */     } else {
/* 317 */       this.edgeList.add(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeLabelsFromDepths() {
/* 354 */     for (Iterator<Edge> it = this.edgeList.iterator(); it.hasNext(); ) {
/* 355 */       Edge e = it.next();
/* 356 */       Label lbl = e.getLabel();
/* 357 */       Depth depth = e.getDepth();
/* 363 */       if (!depth.isNull()) {
/* 364 */         depth.normalize();
/* 365 */         for (int i = 0; i < 2; i++) {
/* 366 */           if (!lbl.isNull(i) && lbl.isArea() && !depth.isNull(i))
/* 373 */             if (depth.getDelta(i) == 0) {
/* 374 */               lbl.toLine(i);
/*     */             } else {
/* 383 */               Assert.isTrue(!depth.isNull(i, 1), "depth of LEFT side has not been initialized");
/* 384 */               lbl.setLocation(i, 1, depth.getLocation(i, 1));
/* 385 */               Assert.isTrue(!depth.isNull(i, 2), "depth of RIGHT side has not been initialized");
/* 386 */               lbl.setLocation(i, 2, depth.getLocation(i, 2));
/*     */             }  
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void replaceCollapsedEdges() {
/* 399 */     List<Edge> newEdges = new ArrayList();
/* 400 */     for (Iterator<Edge> it = this.edgeList.iterator(); it.hasNext(); ) {
/* 401 */       Edge e = it.next();
/* 402 */       if (e.isCollapsed()) {
/* 404 */         it.remove();
/* 405 */         newEdges.add(e.getCollapsedEdge());
/*     */       } 
/*     */     } 
/* 408 */     this.edgeList.addAll(newEdges);
/*     */   }
/*     */   
/*     */   private void copyPoints(int argIndex) {
/* 421 */     for (Iterator<Node> i = this.arg[argIndex].getNodeIterator(); i.hasNext(); ) {
/* 422 */       Node graphNode = i.next();
/* 423 */       Node newNode = this.graph.addNode(graphNode.getCoordinate());
/* 424 */       newNode.setLabel(argIndex, graphNode.getLabel().getLocation(argIndex));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeLabelling() {
/* 437 */     for (Iterator<Node> nodeit = this.graph.getNodes().iterator(); nodeit.hasNext(); ) {
/* 438 */       Node node = nodeit.next();
/* 440 */       node.getEdges().computeLabelling(this.arg);
/*     */     } 
/* 442 */     mergeSymLabels();
/* 443 */     updateNodeLabelling();
/*     */   }
/*     */   
/*     */   private void mergeSymLabels() {
/* 453 */     for (Iterator<Node> nodeit = this.graph.getNodes().iterator(); nodeit.hasNext(); ) {
/* 454 */       Node node = nodeit.next();
/* 455 */       ((DirectedEdgeStar)node.getEdges()).mergeSymLabels();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateNodeLabelling() {
/* 465 */     for (Iterator<Node> nodeit = this.graph.getNodes().iterator(); nodeit.hasNext(); ) {
/* 466 */       Node node = nodeit.next();
/* 467 */       Label lbl = ((DirectedEdgeStar)node.getEdges()).getLabel();
/* 468 */       node.getLabel().merge(lbl);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void labelIncompleteNodes() {
/* 489 */     int nodeCount = 0;
/* 490 */     for (Iterator<Node> ni = this.graph.getNodes().iterator(); ni.hasNext(); ) {
/* 491 */       Node n = ni.next();
/* 492 */       Label label = n.getLabel();
/* 493 */       if (n.isIsolated()) {
/* 494 */         nodeCount++;
/* 495 */         if (label.isNull(0)) {
/* 496 */           labelIncompleteNode(n, 0);
/*     */         } else {
/* 498 */           labelIncompleteNode(n, 1);
/*     */         } 
/*     */       } 
/* 501 */       ((DirectedEdgeStar)n.getEdges()).updateLabelling(label);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void labelIncompleteNode(Node n, int targetIndex) {
/* 518 */     int loc = this.ptLocator.locate(n.getCoordinate(), this.arg[targetIndex].getGeometry());
/* 522 */     n.getLabel().setLocation(targetIndex, loc);
/*     */   }
/*     */   
/*     */   private void findResultAreaEdges(int opCode) {
/* 535 */     for (Iterator<DirectedEdge> it = this.graph.getEdgeEnds().iterator(); it.hasNext(); ) {
/* 536 */       DirectedEdge de = it.next();
/* 538 */       Label label = de.getLabel();
/* 539 */       if (label.isArea() && !de.isInteriorAreaEdge() && isResultOfOp(label.getLocation(0, 2), label.getLocation(1, 2), opCode))
/* 545 */         de.setInResult(true); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void cancelDuplicateResultEdges() {
/* 558 */     for (Iterator<DirectedEdge> it = this.graph.getEdgeEnds().iterator(); it.hasNext(); ) {
/* 559 */       DirectedEdge de = it.next();
/* 560 */       DirectedEdge sym = de.getSym();
/* 561 */       if (de.isInResult() && sym.isInResult()) {
/* 562 */         de.setInResult(false);
/* 563 */         sym.setInResult(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isCoveredByLA(Coordinate coord) {
/* 576 */     if (isCovered(coord, this.resultLineList))
/* 576 */       return true; 
/* 577 */     if (isCovered(coord, this.resultPolyList))
/* 577 */       return true; 
/* 578 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isCoveredByA(Coordinate coord) {
/* 588 */     if (isCovered(coord, this.resultPolyList))
/* 588 */       return true; 
/* 589 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isCovered(Coordinate coord, List geomList) {
/* 597 */     for (Iterator<Geometry> it = geomList.iterator(); it.hasNext(); ) {
/* 598 */       Geometry geom = it.next();
/* 599 */       int loc = this.ptLocator.locate(coord, geom);
/* 600 */       if (loc != 2)
/* 600 */         return true; 
/*     */     } 
/* 602 */     return false;
/*     */   }
/*     */   
/*     */   private Geometry computeGeometry(List resultPointList, List resultLineList, List resultPolyList, int opcode) {
/* 610 */     List geomList = new ArrayList();
/* 612 */     geomList.addAll(resultPointList);
/* 613 */     geomList.addAll(resultLineList);
/* 614 */     geomList.addAll(resultPolyList);
/* 617 */     if (geomList.isEmpty())
/* 618 */       return createEmptyResult(opcode, this.arg[0].getGeometry(), this.arg[1].getGeometry(), this.geomFact); 
/* 622 */     return this.geomFact.buildGeometry(geomList);
/*     */   }
/*     */   
/*     */   public static Geometry createEmptyResult(int overlayOpCode, Geometry a, Geometry b, GeometryFactory geomFact) {
/*     */     GeometryCollection geometryCollection;
/*     */     Point point;
/*     */     LineString lineString;
/*     */     Polygon polygon;
/* 648 */     Geometry result = null;
/* 649 */     switch (resultDimension(overlayOpCode, a, b)) {
/*     */       case -1:
/* 651 */         geometryCollection = geomFact.createGeometryCollection(new Geometry[0]);
/*     */         break;
/*     */       case 0:
/* 654 */         point = geomFact.createPoint((Coordinate)null);
/*     */         break;
/*     */       case 1:
/* 657 */         lineString = geomFact.createLineString((Coordinate[])null);
/*     */         break;
/*     */       case 2:
/* 660 */         polygon = geomFact.createPolygon(null, null);
/*     */         break;
/*     */     } 
/* 663 */     return (Geometry)polygon;
/*     */   }
/*     */   
/*     */   private static int resultDimension(int opCode, Geometry g0, Geometry g1) {
/* 668 */     int dim0 = g0.getDimension();
/* 669 */     int dim1 = g1.getDimension();
/* 671 */     int resultDimension = -1;
/* 672 */     switch (opCode) {
/*     */       case 1:
/* 674 */         resultDimension = Math.min(dim0, dim1);
/*     */         break;
/*     */       case 2:
/* 677 */         resultDimension = Math.max(dim0, dim1);
/*     */         break;
/*     */       case 3:
/* 680 */         resultDimension = dim0;
/*     */         break;
/*     */       case 4:
/* 690 */         resultDimension = Math.max(dim0, dim1);
/*     */         break;
/*     */     } 
/* 693 */     return resultDimension;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\OverlayOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */