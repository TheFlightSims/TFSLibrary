/*     */ package com.vividsolutions.jts.operation.polygonize;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.planargraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.planargraph.DirectedEdgeStar;
/*     */ import com.vividsolutions.jts.planargraph.Edge;
/*     */ import com.vividsolutions.jts.planargraph.Node;
/*     */ import com.vividsolutions.jts.planargraph.PlanarGraph;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.Stack;
/*     */ 
/*     */ class PolygonizeGraph extends PlanarGraph {
/*     */   private GeometryFactory factory;
/*     */   
/*     */   private static int getDegreeNonDeleted(Node node) {
/*  59 */     List edges = node.getOutEdges().getEdges();
/*  60 */     int degree = 0;
/*  61 */     for (Iterator<PolygonizeDirectedEdge> i = edges.iterator(); i.hasNext(); ) {
/*  62 */       PolygonizeDirectedEdge de = i.next();
/*  63 */       if (!de.isMarked())
/*  63 */         degree++; 
/*     */     } 
/*  65 */     return degree;
/*     */   }
/*     */   
/*     */   private static int getDegree(Node node, long label) {
/*  70 */     List edges = node.getOutEdges().getEdges();
/*  71 */     int degree = 0;
/*  72 */     for (Iterator<PolygonizeDirectedEdge> i = edges.iterator(); i.hasNext(); ) {
/*  73 */       PolygonizeDirectedEdge de = i.next();
/*  74 */       if (de.getLabel() == label)
/*  74 */         degree++; 
/*     */     } 
/*  76 */     return degree;
/*     */   }
/*     */   
/*     */   public static void deleteAllEdges(Node node) {
/*  84 */     List edges = node.getOutEdges().getEdges();
/*  85 */     for (Iterator<PolygonizeDirectedEdge> i = edges.iterator(); i.hasNext(); ) {
/*  86 */       PolygonizeDirectedEdge de = i.next();
/*  87 */       de.setMarked(true);
/*  88 */       PolygonizeDirectedEdge sym = (PolygonizeDirectedEdge)de.getSym();
/*  89 */       if (sym != null)
/*  90 */         sym.setMarked(true); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public PolygonizeGraph(GeometryFactory factory) {
/* 103 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public void addEdge(LineString line) {
/* 112 */     if (line.isEmpty())
/*     */       return; 
/* 113 */     Coordinate[] linePts = CoordinateArrays.removeRepeatedPoints(line.getCoordinates());
/* 115 */     if (linePts.length < 2)
/*     */       return; 
/* 117 */     Coordinate startPt = linePts[0];
/* 118 */     Coordinate endPt = linePts[linePts.length - 1];
/* 120 */     Node nStart = getNode(startPt);
/* 121 */     Node nEnd = getNode(endPt);
/* 123 */     DirectedEdge de0 = new PolygonizeDirectedEdge(nStart, nEnd, linePts[1], true);
/* 124 */     DirectedEdge de1 = new PolygonizeDirectedEdge(nEnd, nStart, linePts[linePts.length - 2], false);
/* 125 */     Edge edge = new PolygonizeEdge(line);
/* 126 */     edge.setDirectedEdges(de0, de1);
/* 127 */     add(edge);
/*     */   }
/*     */   
/*     */   private Node getNode(Coordinate pt) {
/* 132 */     Node node = findNode(pt);
/* 133 */     if (node == null) {
/* 134 */       node = new Node(pt);
/* 136 */       add(node);
/*     */     } 
/* 138 */     return node;
/*     */   }
/*     */   
/*     */   private void computeNextCWEdges() {
/* 144 */     for (Iterator<Node> iNode = nodeIterator(); iNode.hasNext(); ) {
/* 145 */       Node node = iNode.next();
/* 146 */       computeNextCWEdges(node);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void convertMaximalToMinimalEdgeRings(List ringEdges) {
/* 158 */     for (Iterator<PolygonizeDirectedEdge> i = ringEdges.iterator(); i.hasNext(); ) {
/* 159 */       PolygonizeDirectedEdge de = i.next();
/* 160 */       long label = de.getLabel();
/* 161 */       List intNodes = findIntersectionNodes(de, label);
/* 163 */       if (intNodes == null)
/*     */         continue; 
/* 165 */       for (Iterator<Node> iNode = intNodes.iterator(); iNode.hasNext(); ) {
/* 166 */         Node node = iNode.next();
/* 167 */         computeNextCCWEdges(node, label);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static List findIntersectionNodes(PolygonizeDirectedEdge startDE, long label) {
/* 181 */     PolygonizeDirectedEdge de = startDE;
/* 182 */     List<Node> intNodes = null;
/*     */     do {
/* 184 */       Node node = de.getFromNode();
/* 185 */       if (getDegree(node, label) > 1) {
/* 186 */         if (intNodes == null)
/* 187 */           intNodes = new ArrayList(); 
/* 188 */         intNodes.add(node);
/*     */       } 
/* 191 */       de = de.getNext();
/* 192 */       Assert.isTrue((de != null), "found null DE in ring");
/* 193 */       Assert.isTrue((de == startDE || !de.isInRing()), "found DE already in ring");
/* 194 */     } while (de != startDE);
/* 196 */     return intNodes;
/*     */   }
/*     */   
/*     */   public List getEdgeRings() {
/* 207 */     computeNextCWEdges();
/* 209 */     label(this.dirEdges, -1L);
/* 210 */     List maximalRings = findLabeledEdgeRings(this.dirEdges);
/* 211 */     convertMaximalToMinimalEdgeRings(maximalRings);
/* 214 */     List<EdgeRing> edgeRingList = new ArrayList();
/* 215 */     for (Iterator<PolygonizeDirectedEdge> i = this.dirEdges.iterator(); i.hasNext(); ) {
/* 216 */       PolygonizeDirectedEdge de = i.next();
/* 217 */       if (de.isMarked() || 
/* 218 */         de.isInRing())
/*     */         continue; 
/* 220 */       EdgeRing er = findEdgeRing(de);
/* 221 */       edgeRingList.add(er);
/*     */     } 
/* 223 */     return edgeRingList;
/*     */   }
/*     */   
/*     */   private static List findLabeledEdgeRings(Collection dirEdges) {
/* 236 */     List<PolygonizeDirectedEdge> edgeRingStarts = new ArrayList();
/* 238 */     long currLabel = 1L;
/* 239 */     for (Iterator<PolygonizeDirectedEdge> i = dirEdges.iterator(); i.hasNext(); ) {
/* 240 */       PolygonizeDirectedEdge de = i.next();
/* 241 */       if (de.isMarked() || 
/* 242 */         de.getLabel() >= 0L)
/*     */         continue; 
/* 244 */       edgeRingStarts.add(de);
/* 245 */       List edges = findDirEdgesInRing(de);
/* 247 */       label(edges, currLabel);
/* 248 */       currLabel++;
/*     */     } 
/* 250 */     return edgeRingStarts;
/*     */   }
/*     */   
/*     */   public List deleteCutEdges() {
/* 259 */     computeNextCWEdges();
/* 261 */     findLabeledEdgeRings(this.dirEdges);
/* 267 */     List<LineString> cutLines = new ArrayList();
/* 268 */     for (Iterator<PolygonizeDirectedEdge> i = this.dirEdges.iterator(); i.hasNext(); ) {
/* 269 */       PolygonizeDirectedEdge de = i.next();
/* 270 */       if (de.isMarked())
/*     */         continue; 
/* 272 */       PolygonizeDirectedEdge sym = (PolygonizeDirectedEdge)de.getSym();
/* 274 */       if (de.getLabel() == sym.getLabel()) {
/* 275 */         de.setMarked(true);
/* 276 */         sym.setMarked(true);
/* 279 */         PolygonizeEdge e = (PolygonizeEdge)de.getEdge();
/* 280 */         cutLines.add(e.getLine());
/*     */       } 
/*     */     } 
/* 283 */     return cutLines;
/*     */   }
/*     */   
/*     */   private static void label(Collection dirEdges, long label) {
/* 288 */     for (Iterator<PolygonizeDirectedEdge> i = dirEdges.iterator(); i.hasNext(); ) {
/* 289 */       PolygonizeDirectedEdge de = i.next();
/* 290 */       de.setLabel(label);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void computeNextCWEdges(Node node) {
/* 295 */     DirectedEdgeStar deStar = node.getOutEdges();
/* 296 */     PolygonizeDirectedEdge startDE = null;
/* 297 */     PolygonizeDirectedEdge prevDE = null;
/* 300 */     for (Iterator<PolygonizeDirectedEdge> i = deStar.getEdges().iterator(); i.hasNext(); ) {
/* 301 */       PolygonizeDirectedEdge outDE = i.next();
/* 302 */       if (outDE.isMarked())
/*     */         continue; 
/* 304 */       if (startDE == null)
/* 305 */         startDE = outDE; 
/* 306 */       if (prevDE != null) {
/* 307 */         PolygonizeDirectedEdge sym = (PolygonizeDirectedEdge)prevDE.getSym();
/* 308 */         sym.setNext(outDE);
/*     */       } 
/* 310 */       prevDE = outDE;
/*     */     } 
/* 312 */     if (prevDE != null) {
/* 313 */       PolygonizeDirectedEdge sym = (PolygonizeDirectedEdge)prevDE.getSym();
/* 314 */       sym.setNext(startDE);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void computeNextCCWEdges(Node node, long label) {
/* 324 */     DirectedEdgeStar deStar = node.getOutEdges();
/* 326 */     PolygonizeDirectedEdge firstOutDE = null;
/* 327 */     PolygonizeDirectedEdge prevInDE = null;
/* 330 */     List<PolygonizeDirectedEdge> edges = deStar.getEdges();
/* 332 */     for (int i = edges.size() - 1; i >= 0; i--) {
/* 333 */       PolygonizeDirectedEdge de = edges.get(i);
/* 334 */       PolygonizeDirectedEdge sym = (PolygonizeDirectedEdge)de.getSym();
/* 336 */       PolygonizeDirectedEdge outDE = null;
/* 337 */       if (de.getLabel() == label)
/* 337 */         outDE = de; 
/* 338 */       PolygonizeDirectedEdge inDE = null;
/* 339 */       if (sym.getLabel() == label)
/* 339 */         inDE = sym; 
/* 341 */       if (outDE != null || inDE != null) {
/* 343 */         if (inDE != null)
/* 344 */           prevInDE = inDE; 
/* 347 */         if (outDE != null) {
/* 348 */           if (prevInDE != null) {
/* 349 */             prevInDE.setNext(outDE);
/* 350 */             prevInDE = null;
/*     */           } 
/* 352 */           if (firstOutDE == null)
/* 353 */             firstOutDE = outDE; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 356 */     if (prevInDE != null) {
/* 357 */       Assert.isTrue((firstOutDE != null));
/* 358 */       prevInDE.setNext(firstOutDE);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static List findDirEdgesInRing(PolygonizeDirectedEdge startDE) {
/* 372 */     PolygonizeDirectedEdge de = startDE;
/* 373 */     List<PolygonizeDirectedEdge> edges = new ArrayList();
/*     */     do {
/* 375 */       edges.add(de);
/* 376 */       de = de.getNext();
/* 377 */       Assert.isTrue((de != null), "found null DE in ring");
/* 378 */       Assert.isTrue((de == startDE || !de.isInRing()), "found DE already in ring");
/* 379 */     } while (de != startDE);
/* 381 */     return edges;
/*     */   }
/*     */   
/*     */   private EdgeRing findEdgeRing(PolygonizeDirectedEdge startDE) {
/* 386 */     PolygonizeDirectedEdge de = startDE;
/* 387 */     EdgeRing er = new EdgeRing(this.factory);
/*     */     do {
/* 389 */       er.add(de);
/* 390 */       de.setRing(er);
/* 391 */       de = de.getNext();
/* 392 */       Assert.isTrue((de != null), "found null DE in ring");
/* 393 */       Assert.isTrue((de == startDE || !de.isInRing()), "found DE already in ring");
/* 394 */     } while (de != startDE);
/* 396 */     return er;
/*     */   }
/*     */   
/*     */   public Collection deleteDangles() {
/* 411 */     List nodesToRemove = findNodesOfDegree(1);
/* 412 */     Set<LineString> dangleLines = new HashSet();
/* 414 */     Stack<Node> nodeStack = new Stack();
/* 415 */     for (Iterator i = nodesToRemove.iterator(); i.hasNext();)
/* 416 */       nodeStack.push(i.next()); 
/* 419 */     while (!nodeStack.isEmpty()) {
/* 420 */       Node node = nodeStack.pop();
/* 422 */       deleteAllEdges(node);
/* 423 */       List nodeOutEdges = node.getOutEdges().getEdges();
/* 424 */       for (Iterator<PolygonizeDirectedEdge> iterator = nodeOutEdges.iterator(); iterator.hasNext(); ) {
/* 425 */         PolygonizeDirectedEdge de = iterator.next();
/* 427 */         de.setMarked(true);
/* 428 */         PolygonizeDirectedEdge sym = (PolygonizeDirectedEdge)de.getSym();
/* 429 */         if (sym != null)
/* 430 */           sym.setMarked(true); 
/* 433 */         PolygonizeEdge e = (PolygonizeEdge)de.getEdge();
/* 434 */         dangleLines.add(e.getLine());
/* 436 */         Node toNode = de.getToNode();
/* 438 */         if (getDegreeNonDeleted(toNode) == 1)
/* 439 */           nodeStack.push(toNode); 
/*     */       } 
/*     */     } 
/* 442 */     return dangleLines;
/*     */   }
/*     */   
/*     */   public void computeDepthParity() {
/*     */     while (true) {
/* 456 */       PolygonizeDirectedEdge de = null;
/* 457 */       if (de == null)
/*     */         return; 
/* 459 */       computeDepthParity(de);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeDepthParity(PolygonizeDirectedEdge de) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\polygonize\PolygonizeGraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */