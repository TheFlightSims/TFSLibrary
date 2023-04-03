/*      */ package org.poly2tri.triangulation.delaunay.sweep;
/*      */ 
/*      */ import java.util.List;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.poly2tri.triangulation.TriangulationMode;
/*      */ import org.poly2tri.triangulation.TriangulationPoint;
/*      */ import org.poly2tri.triangulation.TriangulationUtil;
/*      */ import org.poly2tri.triangulation.delaunay.DelaunayTriangle;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ 
/*      */ public class DTSweep {
/*   51 */   private static final Logger logger = LoggerFactory.getLogger(DTSweep.class);
/*      */   
/*      */   private static final double PI_div2 = 1.5707963267948966D;
/*      */   
/*      */   private static final double PI_3div4 = 2.356194490192345D;
/*      */   
/*      */   public static void triangulate(DTSweepContext tcx) throws Exception {
/*   61 */     tcx.createAdvancingFront();
/*   63 */     sweep(tcx);
/*   65 */     if (tcx.getTriangulationMode() == TriangulationMode.POLYGON) {
/*   67 */       finalizationPolygon(tcx);
/*      */     } else {
/*   71 */       finalizationConvexHull(tcx);
/*      */     } 
/*   74 */     tcx.done();
/*      */   }
/*      */   
/*      */   private static void sweep(DTSweepContext tcx) throws Exception {
/*   87 */     List<TriangulationPoint> points = tcx.getPoints();
/*   89 */     for (int i = 1; i < points.size(); i++) {
/*   91 */       TriangulationPoint point = points.get(i);
/*   93 */       AdvancingFrontNode node = pointEvent(tcx, point);
/*   95 */       if (point.hasEdges())
/*   97 */         for (DTSweepConstraint e : point.getEdges()) {
/*   99 */           if (tcx.isDebugEnabled())
/*   99 */             ((DTSweepDebugContext)tcx.getDebugContext()).setActiveConstraint(e); 
/*  100 */           edgeEvent(tcx, e, node);
/*      */         }  
/*  103 */       tcx.update(null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void finalizationConvexHull(DTSweepContext tcx) {
/*  118 */     AdvancingFrontNode n1 = tcx.aFront.head.next;
/*  119 */     AdvancingFrontNode n2 = n1.next;
/*  120 */     TriangulationPoint first = n1.point;
/*  122 */     turnAdvancingFrontConvex(tcx, n1, n2);
/*  133 */     n1 = tcx.aFront.tail.prev;
/*  134 */     if (n1.triangle.contains(n1.next.point) && n1.triangle.contains(n1.prev.point)) {
/*  136 */       DelaunayTriangle delaunayTriangle = n1.triangle.neighborAcross(n1.point);
/*  137 */       rotateTrianglePair(n1.triangle, n1.point, delaunayTriangle, delaunayTriangle.oppositePoint(n1.triangle, n1.point));
/*  138 */       tcx.mapTriangleToNodes(n1.triangle);
/*  139 */       tcx.mapTriangleToNodes(delaunayTriangle);
/*      */     } 
/*  141 */     n1 = tcx.aFront.head.next;
/*  142 */     if (n1.triangle.contains(n1.prev.point) && n1.triangle.contains(n1.next.point)) {
/*  144 */       DelaunayTriangle delaunayTriangle = n1.triangle.neighborAcross(n1.point);
/*  145 */       rotateTrianglePair(n1.triangle, n1.point, delaunayTriangle, delaunayTriangle.oppositePoint(n1.triangle, n1.point));
/*  146 */       tcx.mapTriangleToNodes(n1.triangle);
/*  147 */       tcx.mapTriangleToNodes(delaunayTriangle);
/*      */     } 
/*  151 */     first = tcx.aFront.head.point;
/*  152 */     n2 = tcx.aFront.tail.prev;
/*  153 */     DelaunayTriangle t1 = n2.triangle;
/*  154 */     TriangulationPoint p1 = n2.point;
/*  155 */     n2.triangle = null;
/*      */     while (true) {
/*  158 */       tcx.removeFromList(t1);
/*  159 */       p1 = t1.pointCCW(p1);
/*  160 */       if (p1 == first)
/*      */         break; 
/*  161 */       DelaunayTriangle delaunayTriangle = t1.neighborCCW(p1);
/*  162 */       t1.clear();
/*  163 */       t1 = delaunayTriangle;
/*      */     } 
/*  167 */     first = tcx.aFront.head.next.point;
/*  168 */     p1 = t1.pointCW(tcx.aFront.head.point);
/*  169 */     DelaunayTriangle t2 = t1.neighborCW(tcx.aFront.head.point);
/*  170 */     t1.clear();
/*  171 */     t1 = t2;
/*  172 */     while (p1 != first) {
/*  174 */       tcx.removeFromList(t1);
/*  175 */       p1 = t1.pointCCW(p1);
/*  176 */       t2 = t1.neighborCCW(p1);
/*  177 */       t1.clear();
/*  178 */       t1 = t2;
/*      */     } 
/*  183 */     tcx.aFront.head = tcx.aFront.head.next;
/*  184 */     tcx.aFront.head.prev = null;
/*  185 */     tcx.aFront.tail = tcx.aFront.tail.prev;
/*  186 */     tcx.aFront.tail.next = null;
/*  188 */     tcx.finalizeTriangulation();
/*      */   }
/*      */   
/*      */   private static void turnAdvancingFrontConvex(DTSweepContext tcx, AdvancingFrontNode b, AdvancingFrontNode c) {
/*  199 */     AdvancingFrontNode first = b;
/*  200 */     while (c != tcx.aFront.tail) {
/*  202 */       if (tcx.isDebugEnabled())
/*  202 */         ((DTSweepDebugContext)tcx.getDebugContext()).setActiveNode(c); 
/*  204 */       if (TriangulationUtil.orient2d(b.point, c.point, c.next.point) == TriangulationUtil.Orientation.CCW) {
/*  207 */         fill(tcx, c);
/*  208 */         c = c.next;
/*      */         continue;
/*      */       } 
/*  213 */       if (b != first && TriangulationUtil.orient2d(b.prev.point, b.point, c.point) == TriangulationUtil.Orientation.CCW) {
/*  216 */         fill(tcx, b);
/*  217 */         b = b.prev;
/*      */         continue;
/*      */       } 
/*  222 */       b = c;
/*  223 */       c = c.next;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void finalizationPolygon(DTSweepContext tcx) {
/*  232 */     DelaunayTriangle t = tcx.aFront.head.next.triangle;
/*  233 */     TriangulationPoint p = tcx.aFront.head.next.point;
/*  234 */     while (!t.getConstrainedEdgeCW(p))
/*  236 */       t = t.neighborCCW(p); 
/*  240 */     tcx.meshClean(t);
/*      */   }
/*      */   
/*      */   private static AdvancingFrontNode pointEvent(DTSweepContext tcx, TriangulationPoint point) {
/*  257 */     AdvancingFrontNode node = tcx.locateNode(point);
/*  258 */     if (tcx.isDebugEnabled())
/*  258 */       ((DTSweepDebugContext)tcx.getDebugContext()).setActiveNode(node); 
/*  259 */     AdvancingFrontNode newNode = newFrontTriangle(tcx, point, node);
/*  263 */     if (point.getX() <= node.point.getX() + 1.0E-12D)
/*  265 */       fill(tcx, node); 
/*  267 */     tcx.addNode(newNode);
/*  269 */     fillAdvancingFront(tcx, newNode);
/*  270 */     return newNode;
/*      */   }
/*      */   
/*      */   private static AdvancingFrontNode newFrontTriangle(DTSweepContext tcx, TriangulationPoint point, AdvancingFrontNode node) {
/*  288 */     DelaunayTriangle triangle = new DelaunayTriangle(point, node.point, node.next.point);
/*  289 */     triangle.markNeighbor(node.triangle);
/*  290 */     tcx.addToList(triangle);
/*  292 */     AdvancingFrontNode newNode = new AdvancingFrontNode(point);
/*  293 */     newNode.next = node.next;
/*  294 */     newNode.prev = node;
/*  295 */     node.next.prev = newNode;
/*  296 */     node.next = newNode;
/*  298 */     tcx.addNode(newNode);
/*  300 */     if (tcx.isDebugEnabled())
/*  300 */       ((DTSweepDebugContext)tcx.getDebugContext()).setActiveNode(newNode); 
/*  302 */     if (!legalize(tcx, triangle))
/*  304 */       tcx.mapTriangleToNodes(triangle); 
/*  307 */     return newNode;
/*      */   }
/*      */   
/*      */   private static void edgeEvent(DTSweepContext tcx, DTSweepConstraint edge, AdvancingFrontNode node) throws Exception {
/*      */     try {
/*  322 */       tcx.edgeEvent.constrainedEdge = edge;
/*  323 */       tcx.edgeEvent.right = (edge.p.getX() > edge.q.getX());
/*  325 */       if (tcx.isDebugEnabled())
/*  325 */         ((DTSweepDebugContext)tcx.getDebugContext()).setPrimaryTriangle(node.triangle); 
/*  327 */       if (isEdgeSideOfTriangle(node.triangle, edge.p, edge.q))
/*      */         return; 
/*  335 */       fillEdgeEvent(tcx, edge, node);
/*  337 */       edgeEvent(tcx, edge.p, edge.q, node.triangle, edge.q, 0);
/*  339 */     } catch (PointOnEdgeException e) {
/*  341 */       logger.warn("Skipping edge: {}", e.getMessage());
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void fillEdgeEvent(DTSweepContext tcx, DTSweepConstraint edge, AdvancingFrontNode node) {
/*  347 */     if (tcx.edgeEvent.right) {
/*  349 */       fillRightAboveEdgeEvent(tcx, edge, node);
/*      */     } else {
/*  353 */       fillLeftAboveEdgeEvent(tcx, edge, node);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void fillRightConcaveEdgeEvent(DTSweepContext tcx, DTSweepConstraint edge, AdvancingFrontNode node) {
/*  359 */     fill(tcx, node.next);
/*  360 */     if (node.next.point != edge.p)
/*  363 */       if (TriangulationUtil.orient2d(edge.q, node.next.point, edge.p) == TriangulationUtil.Orientation.CCW)
/*  366 */         if (TriangulationUtil.orient2d(node.point, node.next.point, node.next.next.point) == TriangulationUtil.Orientation.CCW)
/*  369 */           fillRightConcaveEdgeEvent(tcx, edge, node);   
/*      */   }
/*      */   
/*      */   private static void fillRightConvexEdgeEvent(DTSweepContext tcx, DTSweepConstraint edge, AdvancingFrontNode node) {
/*  382 */     if (TriangulationUtil.orient2d(node.next.point, node.next.next.point, node.next.next.next.point) == TriangulationUtil.Orientation.CCW) {
/*  385 */       fillRightConcaveEdgeEvent(tcx, edge, node.next);
/*  391 */     } else if (TriangulationUtil.orient2d(edge.q, node.next.next.point, edge.p) == TriangulationUtil.Orientation.CCW) {
/*  394 */       fillRightConvexEdgeEvent(tcx, edge, node.next);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void fillRightBelowEdgeEvent(DTSweepContext tcx, DTSweepConstraint edge, AdvancingFrontNode node) {
/*  405 */     if (tcx.isDebugEnabled())
/*  405 */       ((DTSweepDebugContext)tcx.getDebugContext()).setActiveNode(node); 
/*  406 */     if (node.point.getX() < edge.p.getX())
/*  408 */       if (TriangulationUtil.orient2d(node.point, node.next.point, node.next.next.point) == TriangulationUtil.Orientation.CCW) {
/*  411 */         fillRightConcaveEdgeEvent(tcx, edge, node);
/*      */       } else {
/*  416 */         fillRightConvexEdgeEvent(tcx, edge, node);
/*  418 */         fillRightBelowEdgeEvent(tcx, edge, node);
/*      */       }  
/*      */   }
/*      */   
/*      */   private static void fillRightAboveEdgeEvent(DTSweepContext tcx, DTSweepConstraint edge, AdvancingFrontNode node) {
/*  426 */     while (node.next.point.getX() < edge.p.getX()) {
/*  428 */       if (tcx.isDebugEnabled())
/*  428 */         ((DTSweepDebugContext)tcx.getDebugContext()).setActiveNode(node); 
/*  430 */       TriangulationUtil.Orientation o1 = TriangulationUtil.orient2d(edge.q, node.next.point, edge.p);
/*  431 */       if (o1 == TriangulationUtil.Orientation.CCW) {
/*  433 */         fillRightBelowEdgeEvent(tcx, edge, node);
/*      */         continue;
/*      */       } 
/*  437 */       node = node.next;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void fillLeftConvexEdgeEvent(DTSweepContext tcx, DTSweepConstraint edge, AdvancingFrontNode node) {
/*  445 */     if (TriangulationUtil.orient2d(node.prev.point, node.prev.prev.point, node.prev.prev.prev.point) == TriangulationUtil.Orientation.CW) {
/*  448 */       fillLeftConcaveEdgeEvent(tcx, edge, node.prev);
/*  454 */     } else if (TriangulationUtil.orient2d(edge.q, node.prev.prev.point, edge.p) == TriangulationUtil.Orientation.CW) {
/*  457 */       fillLeftConvexEdgeEvent(tcx, edge, node.prev);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void fillLeftConcaveEdgeEvent(DTSweepContext tcx, DTSweepConstraint edge, AdvancingFrontNode node) {
/*  468 */     fill(tcx, node.prev);
/*  469 */     if (node.prev.point != edge.p)
/*  472 */       if (TriangulationUtil.orient2d(edge.q, node.prev.point, edge.p) == TriangulationUtil.Orientation.CW)
/*  475 */         if (TriangulationUtil.orient2d(node.point, node.prev.point, node.prev.prev.point) == TriangulationUtil.Orientation.CW)
/*  478 */           fillLeftConcaveEdgeEvent(tcx, edge, node);   
/*      */   }
/*      */   
/*      */   private static void fillLeftBelowEdgeEvent(DTSweepContext tcx, DTSweepConstraint edge, AdvancingFrontNode node) {
/*  490 */     if (tcx.isDebugEnabled())
/*  490 */       ((DTSweepDebugContext)tcx.getDebugContext()).setActiveNode(node); 
/*  491 */     if (node.point.getX() > edge.p.getX())
/*  493 */       if (TriangulationUtil.orient2d(node.point, node.prev.point, node.prev.prev.point) == TriangulationUtil.Orientation.CW) {
/*  496 */         fillLeftConcaveEdgeEvent(tcx, edge, node);
/*      */       } else {
/*  501 */         fillLeftConvexEdgeEvent(tcx, edge, node);
/*  503 */         fillLeftBelowEdgeEvent(tcx, edge, node);
/*      */       }  
/*      */   }
/*      */   
/*      */   private static void fillLeftAboveEdgeEvent(DTSweepContext tcx, DTSweepConstraint edge, AdvancingFrontNode node) {
/*  511 */     while (node.prev.point.getX() > edge.p.getX()) {
/*  513 */       if (tcx.isDebugEnabled())
/*  513 */         ((DTSweepDebugContext)tcx.getDebugContext()).setActiveNode(node); 
/*  515 */       TriangulationUtil.Orientation o1 = TriangulationUtil.orient2d(edge.q, node.prev.point, edge.p);
/*  516 */       if (o1 == TriangulationUtil.Orientation.CW) {
/*  518 */         fillLeftBelowEdgeEvent(tcx, edge, node);
/*      */         continue;
/*      */       } 
/*  522 */       node = node.prev;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static boolean isEdgeSideOfTriangle(DelaunayTriangle triangle, TriangulationPoint ep, TriangulationPoint eq) {
/*  532 */     Integer index = Integer.valueOf(triangle.edgeIndex(ep, eq));
/*  533 */     if (index == null)
/*  534 */       return false; 
/*  536 */     if (index.intValue() != -1) {
/*  538 */       triangle.markConstrainedEdge(index.intValue());
/*  539 */       triangle = triangle.neighbors[index.intValue()];
/*  540 */       if (triangle != null)
/*  542 */         triangle.markConstrainedEdge(ep, eq); 
/*  544 */       return true;
/*      */     } 
/*  546 */     return false;
/*      */   }
/*      */   
/*      */   private static void edgeEvent(DTSweepContext tcx, TriangulationPoint ep, TriangulationPoint eq, DelaunayTriangle triangle, TriangulationPoint point, int index) throws Exception {
/*  554 */     if (index > 200)
/*  555 */       throw new Exception("Loop reached"); 
/*  559 */     if (tcx.isDebugEnabled())
/*  559 */       ((DTSweepDebugContext)tcx.getDebugContext()).setPrimaryTriangle(triangle); 
/*  561 */     if (isEdgeSideOfTriangle(triangle, ep, eq))
/*      */       return; 
/*  566 */     TriangulationPoint p1 = triangle.pointCCW(point);
/*  567 */     TriangulationUtil.Orientation o1 = TriangulationUtil.orient2d(eq, p1, ep);
/*  568 */     if (o1 == TriangulationUtil.Orientation.Collinear) {
/*  570 */       if (triangle.contains(eq, p1)) {
/*  572 */         triangle.markConstrainedEdge(eq, p1);
/*  575 */         tcx.edgeEvent.constrainedEdge.q = p1;
/*  576 */         triangle = triangle.neighborAcross(point);
/*  577 */         edgeEvent(tcx, ep, p1, triangle, p1, index + 1);
/*      */       } else {
/*  581 */         throw new PointOnEdgeException("EdgeEvent - Point on constrained edge not supported yet");
/*      */       } 
/*  583 */       if (tcx.isDebugEnabled())
/*  583 */         logger.info("EdgeEvent - Point on constrained edge"); 
/*      */       return;
/*      */     } 
/*  587 */     TriangulationPoint p2 = triangle.pointCW(point);
/*  588 */     TriangulationUtil.Orientation o2 = TriangulationUtil.orient2d(eq, p2, ep);
/*  589 */     if (o2 == TriangulationUtil.Orientation.Collinear) {
/*  591 */       if (triangle.contains(eq, p2)) {
/*  593 */         triangle.markConstrainedEdge(eq, p2);
/*  596 */         tcx.edgeEvent.constrainedEdge.q = p2;
/*  597 */         triangle = triangle.neighborAcross(point);
/*  598 */         edgeEvent(tcx, ep, p2, triangle, p2, index + 1);
/*      */       } else {
/*  602 */         throw new PointOnEdgeException("EdgeEvent - Point on constrained edge not supported yet");
/*      */       } 
/*  604 */       if (tcx.isDebugEnabled())
/*  604 */         logger.info("EdgeEvent - Point on constrained edge"); 
/*      */       return;
/*      */     } 
/*  608 */     if (o1 == o2) {
/*  612 */       if (o1 == TriangulationUtil.Orientation.CW) {
/*  614 */         triangle = triangle.neighborCCW(point);
/*      */       } else {
/*  618 */         triangle = triangle.neighborCW(point);
/*      */       } 
/*  620 */       edgeEvent(tcx, ep, eq, triangle, point, index + 1);
/*      */     } else {
/*  625 */       flipEdgeEvent(tcx, ep, eq, triangle, point);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void flipEdgeEvent(DTSweepContext tcx, TriangulationPoint ep, TriangulationPoint eq, DelaunayTriangle t, TriangulationPoint p) throws Exception {
/*  638 */     DelaunayTriangle ot = t.neighborAcross(p);
/*  639 */     TriangulationPoint op = ot.oppositePoint(t, p);
/*  641 */     if (ot == null)
/*  645 */       throw new RuntimeException("[BUG:FIXME] FLIP failed due to missing triangle"); 
/*  648 */     if (t.getConstrainedEdgeAcross(p))
/*  650 */       throw new RuntimeException("Intersecting Constraints"); 
/*  653 */     if (tcx.isDebugEnabled()) {
/*  655 */       ((DTSweepDebugContext)tcx.getDebugContext()).setPrimaryTriangle(t);
/*  656 */       ((DTSweepDebugContext)tcx.getDebugContext()).setSecondaryTriangle(ot);
/*      */     } 
/*  659 */     boolean inScanArea = TriangulationUtil.inScanArea(p, t.pointCCW(p), t.pointCW(p), op);
/*  663 */     if (inScanArea) {
/*  666 */       rotateTrianglePair(t, p, ot, op);
/*  667 */       tcx.mapTriangleToNodes(t);
/*  668 */       tcx.mapTriangleToNodes(ot);
/*  670 */       if (p == eq && op == ep) {
/*  672 */         if (eq == tcx.edgeEvent.constrainedEdge.q && ep == tcx.edgeEvent.constrainedEdge.p) {
/*  675 */           if (tcx.isDebugEnabled())
/*  675 */             System.out.println("[FLIP] - constrained edge done"); 
/*  676 */           t.markConstrainedEdge(ep, eq);
/*  677 */           ot.markConstrainedEdge(ep, eq);
/*  678 */           legalize(tcx, t);
/*  679 */           legalize(tcx, ot);
/*  683 */         } else if (tcx.isDebugEnabled()) {
/*  683 */           System.out.println("[FLIP] - subedge done");
/*      */         } 
/*      */       } else {
/*  689 */         if (tcx.isDebugEnabled())
/*  689 */           System.out.println("[FLIP] - flipping and continuing with triangle still crossing edge"); 
/*  690 */         TriangulationUtil.Orientation o = TriangulationUtil.orient2d(eq, op, ep);
/*  691 */         t = nextFlipTriangle(tcx, o, t, ot, p, op);
/*  692 */         flipEdgeEvent(tcx, ep, eq, t, p);
/*      */       } 
/*      */     } else {
/*  697 */       TriangulationPoint newP = nextFlipPoint(ep, eq, ot, op);
/*  698 */       flipScanEdgeEvent(tcx, ep, eq, t, ot, newP);
/*  699 */       edgeEvent(tcx, ep, eq, t, p, 0);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static TriangulationPoint nextFlipPoint(TriangulationPoint ep, TriangulationPoint eq, DelaunayTriangle ot, TriangulationPoint op) {
/*  719 */     TriangulationUtil.Orientation o2d = TriangulationUtil.orient2d(eq, op, ep);
/*  720 */     if (o2d == TriangulationUtil.Orientation.CW)
/*  723 */       return ot.pointCCW(op); 
/*  725 */     if (o2d == TriangulationUtil.Orientation.CCW)
/*  728 */       return ot.pointCW(op); 
/*  733 */     throw new PointOnEdgeException("Point on constrained edge not supported yet");
/*      */   }
/*      */   
/*      */   private static DelaunayTriangle nextFlipTriangle(DTSweepContext tcx, TriangulationUtil.Orientation o, DelaunayTriangle t, DelaunayTriangle ot, TriangulationPoint p, TriangulationPoint op) {
/*  757 */     if (o == TriangulationUtil.Orientation.CCW) {
/*  760 */       int i = ot.edgeIndex(p, op);
/*  761 */       ot.dEdge[i] = true;
/*  762 */       legalize(tcx, ot);
/*  763 */       ot.clearDelunayEdges();
/*  764 */       return t;
/*      */     } 
/*  767 */     int edgeIndex = t.edgeIndex(p, op);
/*  768 */     t.dEdge[edgeIndex] = true;
/*  769 */     legalize(tcx, t);
/*  770 */     t.clearDelunayEdges();
/*  771 */     return ot;
/*      */   }
/*      */   
/*      */   private static void flipScanEdgeEvent(DTSweepContext tcx, TriangulationPoint ep, TriangulationPoint eq, DelaunayTriangle flipTriangle, DelaunayTriangle t, TriangulationPoint p) throws Exception {
/*  797 */     DelaunayTriangle ot = t.neighborAcross(p);
/*  798 */     TriangulationPoint op = ot.oppositePoint(t, p);
/*  800 */     if (ot == null)
/*  804 */       throw new RuntimeException("[BUG:FIXME] FLIP failed due to missing triangle"); 
/*  807 */     if (tcx.isDebugEnabled()) {
/*  809 */       System.out.println("[FLIP:SCAN] - scan next point");
/*  810 */       ((DTSweepDebugContext)tcx.getDebugContext()).setPrimaryTriangle(t);
/*  811 */       ((DTSweepDebugContext)tcx.getDebugContext()).setSecondaryTriangle(ot);
/*      */     } 
/*  814 */     boolean inScanArea = TriangulationUtil.inScanArea(eq, flipTriangle.pointCCW(eq), flipTriangle.pointCW(eq), op);
/*  818 */     if (inScanArea) {
/*  821 */       flipEdgeEvent(tcx, eq, op, ot, op);
/*      */     } else {
/*  832 */       TriangulationPoint newP = nextFlipPoint(ep, eq, ot, op);
/*  833 */       flipScanEdgeEvent(tcx, ep, eq, flipTriangle, ot, newP);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void fillAdvancingFront(DTSweepContext tcx, AdvancingFrontNode n) {
/*  850 */     AdvancingFrontNode node = n.next;
/*  851 */     while (node.hasNext()) {
/*  853 */       if (isLargeHole(node))
/*      */         break; 
/*  857 */       fill(tcx, node);
/*  858 */       node = node.next;
/*      */     } 
/*  862 */     node = n.prev;
/*  863 */     while (node.hasPrevious()) {
/*  865 */       if (isLargeHole(node))
/*      */         break; 
/*  869 */       fill(tcx, node);
/*  870 */       node = node.prev;
/*      */     } 
/*  874 */     if (n.hasNext() && n.next.hasNext()) {
/*  876 */       double angle = basinAngle(n);
/*  877 */       if (angle < 2.356194490192345D)
/*  879 */         fillBasin(tcx, n); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static boolean isLargeHole(AdvancingFrontNode node) {
/*  890 */     double angle = angle(node.point, node.next.point, node.prev.point);
/*  893 */     return (angle > 1.5707963267948966D || angle < 0.0D);
/*      */   }
/*      */   
/*      */   private static void fillBasin(DTSweepContext tcx, AdvancingFrontNode node) {
/*  968 */     if (TriangulationUtil.orient2d(node.point, node.next.point, node.next.next.point) == TriangulationUtil.Orientation.CCW) {
/*  970 */       tcx.basin.leftNode = node;
/*      */     } else {
/*  974 */       tcx.basin.leftNode = node.next;
/*      */     } 
/*  978 */     tcx.basin.bottomNode = tcx.basin.leftNode;
/*  980 */     while (tcx.basin.bottomNode.hasNext() && tcx.basin.bottomNode.point.getY() >= tcx.basin.bottomNode.next.point.getY())
/*  982 */       tcx.basin.bottomNode = tcx.basin.bottomNode.next; 
/*  984 */     if (tcx.basin.bottomNode == tcx.basin.leftNode)
/*      */       return; 
/*  990 */     tcx.basin.rightNode = tcx.basin.bottomNode;
/*  992 */     while (tcx.basin.rightNode.hasNext() && tcx.basin.rightNode.point.getY() < tcx.basin.rightNode.next.point.getY())
/*  994 */       tcx.basin.rightNode = tcx.basin.rightNode.next; 
/*  996 */     if (tcx.basin.rightNode == tcx.basin.bottomNode)
/*      */       return; 
/* 1002 */     tcx.basin.width = tcx.basin.rightNode.getPoint().getX() - tcx.basin.leftNode.getPoint().getX();
/* 1003 */     tcx.basin.leftHighest = (tcx.basin.leftNode.getPoint().getY() > tcx.basin.rightNode.getPoint().getY());
/* 1005 */     fillBasinReq(tcx, tcx.basin.bottomNode);
/*      */   }
/*      */   
/*      */   private static void fillBasinReq(DTSweepContext tcx, AdvancingFrontNode node) {
/* 1017 */     if (isShallow(tcx, node))
/*      */       return; 
/* 1022 */     fill(tcx, node);
/* 1023 */     if (node.prev == tcx.basin.leftNode && node.next == tcx.basin.rightNode)
/*      */       return; 
/* 1027 */     if (node.prev == tcx.basin.leftNode) {
/* 1029 */       TriangulationUtil.Orientation o = TriangulationUtil.orient2d(node.point, node.next.point, node.next.next.point);
/* 1030 */       if (o == TriangulationUtil.Orientation.CW)
/*      */         return; 
/* 1034 */       node = node.next;
/* 1036 */     } else if (node.next == tcx.basin.rightNode) {
/* 1038 */       TriangulationUtil.Orientation o = TriangulationUtil.orient2d(node.point, node.prev.point, node.prev.prev.point);
/* 1039 */       if (o == TriangulationUtil.Orientation.CCW)
/*      */         return; 
/* 1043 */       node = node.prev;
/* 1048 */     } else if (node.prev.point.getY() < node.next.point.getY()) {
/* 1050 */       node = node.prev;
/*      */     } else {
/* 1054 */       node = node.next;
/*      */     } 
/* 1057 */     fillBasinReq(tcx, node);
/*      */   }
/*      */   
/*      */   private static boolean isShallow(DTSweepContext tcx, AdvancingFrontNode node) {
/*      */     double height;
/* 1064 */     if (tcx.basin.leftHighest) {
/* 1066 */       height = tcx.basin.leftNode.getPoint().getY() - node.getPoint().getY();
/*      */     } else {
/* 1070 */       height = tcx.basin.rightNode.getPoint().getY() - node.getPoint().getY();
/*      */     } 
/* 1072 */     if (tcx.basin.width > height)
/* 1074 */       return true; 
/* 1076 */     return false;
/*      */   }
/*      */   
/*      */   private static double angle(TriangulationPoint p, TriangulationPoint a, TriangulationPoint b) {
/* 1094 */     double px = p.getX();
/* 1095 */     double py = p.getY();
/* 1096 */     double ax = a.getX() - px;
/* 1097 */     double ay = a.getY() - py;
/* 1098 */     double bx = b.getX() - px;
/* 1099 */     double by = b.getY() - py;
/* 1100 */     return FastMath.atan2(ax * by - ay * bx, ax * bx + ay * by);
/*      */   }
/*      */   
/*      */   private static double basinAngle(AdvancingFrontNode node) {
/* 1108 */     double ax = node.point.getX() - node.next.next.point.getX();
/* 1109 */     double ay = node.point.getY() - node.next.next.point.getY();
/* 1110 */     return FastMath.atan2(ay, ax);
/*      */   }
/*      */   
/*      */   private static void fill(DTSweepContext tcx, AdvancingFrontNode node) {
/* 1120 */     DelaunayTriangle triangle = new DelaunayTriangle(node.prev.point, node.point, node.next.point);
/* 1125 */     triangle.markNeighbor(node.prev.triangle);
/* 1126 */     triangle.markNeighbor(node.triangle);
/* 1127 */     tcx.addToList(triangle);
/* 1130 */     node.prev.next = node.next;
/* 1131 */     node.next.prev = node.prev;
/* 1132 */     tcx.removeNode(node);
/* 1135 */     if (!legalize(tcx, triangle))
/* 1137 */       tcx.mapTriangleToNodes(triangle); 
/*      */   }
/*      */   
/*      */   private static boolean legalize(DTSweepContext tcx, DelaunayTriangle t) {
/* 1153 */     for (int i = 0; i < 3; i++) {
/* 1157 */       if (!t.dEdge[i]) {
/* 1161 */         DelaunayTriangle ot = t.neighbors[i];
/* 1162 */         if (ot != null) {
/* 1164 */           TriangulationPoint p = t.points[i];
/* 1165 */           TriangulationPoint op = ot.oppositePoint(t, p);
/* 1166 */           int oi = ot.index(op);
/* 1169 */           if (ot.cEdge[oi] || ot.dEdge[oi]) {
/* 1171 */             t.cEdge[i] = ot.cEdge[oi];
/*      */           } else {
/* 1174 */             boolean inside = TriangulationUtil.smartIncircle(p, t.pointCCW(p), t.pointCW(p), op);
/* 1178 */             if (inside) {
/* 1183 */               t.dEdge[i] = true;
/* 1184 */               ot.dEdge[oi] = true;
/* 1187 */               rotateTrianglePair(t, p, ot, op);
/* 1193 */               boolean notLegalized = !legalize(tcx, t);
/* 1194 */               if (notLegalized)
/* 1196 */                 tcx.mapTriangleToNodes(t); 
/* 1198 */               notLegalized = !legalize(tcx, ot);
/* 1199 */               if (notLegalized)
/* 1201 */                 tcx.mapTriangleToNodes(ot); 
/* 1208 */               t.dEdge[i] = false;
/* 1209 */               ot.dEdge[oi] = false;
/* 1213 */               return true;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1217 */     return false;
/*      */   }
/*      */   
/*      */   private static void rotateTrianglePair(DelaunayTriangle t, TriangulationPoint p, DelaunayTriangle ot, TriangulationPoint op) {
/* 1240 */     DelaunayTriangle n1 = t.neighborCCW(p);
/* 1241 */     DelaunayTriangle n2 = t.neighborCW(p);
/* 1242 */     DelaunayTriangle n3 = ot.neighborCCW(op);
/* 1243 */     DelaunayTriangle n4 = ot.neighborCW(op);
/* 1246 */     boolean ce1 = t.getConstrainedEdgeCCW(p);
/* 1247 */     boolean ce2 = t.getConstrainedEdgeCW(p);
/* 1248 */     boolean ce3 = ot.getConstrainedEdgeCCW(op);
/* 1249 */     boolean ce4 = ot.getConstrainedEdgeCW(op);
/* 1252 */     boolean de1 = t.getDelunayEdgeCCW(p);
/* 1253 */     boolean de2 = t.getDelunayEdgeCW(p);
/* 1254 */     boolean de3 = ot.getDelunayEdgeCCW(op);
/* 1255 */     boolean de4 = ot.getDelunayEdgeCW(op);
/* 1257 */     t.legalize(p, op);
/* 1258 */     ot.legalize(op, p);
/* 1261 */     ot.setDelunayEdgeCCW(p, de1);
/* 1262 */     t.setDelunayEdgeCW(p, de2);
/* 1263 */     t.setDelunayEdgeCCW(op, de3);
/* 1264 */     ot.setDelunayEdgeCW(op, de4);
/* 1267 */     ot.setConstrainedEdgeCCW(p, ce1);
/* 1268 */     t.setConstrainedEdgeCW(p, ce2);
/* 1269 */     t.setConstrainedEdgeCCW(op, ce3);
/* 1270 */     ot.setConstrainedEdgeCW(op, ce4);
/* 1277 */     t.clearNeighbors();
/* 1278 */     ot.clearNeighbors();
/* 1279 */     if (n1 != null)
/* 1279 */       ot.markNeighbor(n1); 
/* 1280 */     if (n2 != null)
/* 1280 */       t.markNeighbor(n2); 
/* 1281 */     if (n3 != null)
/* 1281 */       t.markNeighbor(n3); 
/* 1282 */     if (n4 != null)
/* 1282 */       ot.markNeighbor(n4); 
/* 1283 */     t.markNeighbor(ot);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\delaunay\sweep\DTSweep.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */