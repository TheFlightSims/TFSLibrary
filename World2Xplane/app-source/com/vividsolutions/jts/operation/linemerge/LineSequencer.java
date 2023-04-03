/*     */ package com.vividsolutions.jts.operation.linemerge;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.planargraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.planargraph.GraphComponent;
/*     */ import com.vividsolutions.jts.planargraph.Node;
/*     */ import com.vividsolutions.jts.planargraph.Subgraph;
/*     */ import com.vividsolutions.jts.planargraph.algorithm.ConnectedSubgraphFinder;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class LineSequencer {
/*     */   public static Geometry sequence(Geometry geom) {
/*  88 */     LineSequencer sequencer = new LineSequencer();
/*  89 */     sequencer.add(geom);
/*  90 */     return sequencer.getSequencedLineStrings();
/*     */   }
/*     */   
/*     */   public static boolean isSequenced(Geometry geom) {
/* 105 */     if (!(geom instanceof MultiLineString))
/* 106 */       return true; 
/* 109 */     MultiLineString mls = (MultiLineString)geom;
/* 111 */     Set prevSubgraphNodes = new TreeSet();
/* 113 */     Coordinate lastNode = null;
/* 114 */     List<Coordinate> currNodes = new ArrayList();
/* 115 */     for (int i = 0; i < mls.getNumGeometries(); i++) {
/* 116 */       LineString line = (LineString)mls.getGeometryN(i);
/* 117 */       Coordinate startNode = line.getCoordinateN(0);
/* 118 */       Coordinate endNode = line.getCoordinateN(line.getNumPoints() - 1);
/* 123 */       if (prevSubgraphNodes.contains(startNode))
/* 123 */         return false; 
/* 124 */       if (prevSubgraphNodes.contains(endNode))
/* 124 */         return false; 
/* 126 */       if (lastNode != null && 
/* 127 */         !startNode.equals(lastNode)) {
/* 129 */         prevSubgraphNodes.addAll(currNodes);
/* 130 */         currNodes.clear();
/*     */       } 
/* 133 */       currNodes.add(startNode);
/* 134 */       currNodes.add(endNode);
/* 135 */       lastNode = endNode;
/*     */     } 
/* 137 */     return true;
/*     */   }
/*     */   
/* 140 */   private LineMergeGraph graph = new LineMergeGraph();
/*     */   
/* 142 */   private GeometryFactory factory = new GeometryFactory();
/*     */   
/* 143 */   private int lineCount = 0;
/*     */   
/*     */   private boolean isRun = false;
/*     */   
/* 146 */   private Geometry sequencedGeometry = null;
/*     */   
/*     */   private boolean isSequenceable = false;
/*     */   
/*     */   public void add(Collection geometries) {
/* 158 */     for (Iterator<Geometry> i = geometries.iterator(); i.hasNext(); ) {
/* 159 */       Geometry geometry = i.next();
/* 160 */       add(geometry);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(Geometry geometry) {
/* 172 */     geometry.apply(new GeometryComponentFilter() {
/*     */           public void filter(Geometry component) {
/* 174 */             if (component instanceof LineString)
/* 175 */               LineSequencer.this.addLine((LineString)component); 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void addLine(LineString lineString) {
/* 182 */     if (this.factory == null)
/* 183 */       this.factory = lineString.getFactory(); 
/* 185 */     this.graph.addEdge(lineString);
/* 186 */     this.lineCount++;
/*     */   }
/*     */   
/*     */   public boolean isSequenceable() {
/* 197 */     computeSequence();
/* 198 */     return this.isSequenceable;
/*     */   }
/*     */   
/*     */   public Geometry getSequencedLineStrings() {
/* 208 */     computeSequence();
/* 209 */     return this.sequencedGeometry;
/*     */   }
/*     */   
/*     */   private void computeSequence() {
/* 213 */     if (this.isRun)
/*     */       return; 
/* 214 */     this.isRun = true;
/* 216 */     List sequences = findSequences();
/* 217 */     if (sequences == null)
/*     */       return; 
/* 220 */     this.sequencedGeometry = buildSequencedGeometry(sequences);
/* 221 */     this.isSequenceable = true;
/* 223 */     int finalLineCount = this.sequencedGeometry.getNumGeometries();
/* 224 */     Assert.isTrue((this.lineCount == finalLineCount), "Lines were missing from result");
/* 225 */     Assert.isTrue((this.sequencedGeometry instanceof LineString || this.sequencedGeometry instanceof MultiLineString), "Result is not lineal");
/*     */   }
/*     */   
/*     */   private List findSequences() {
/* 232 */     List<List> sequences = new ArrayList();
/* 233 */     ConnectedSubgraphFinder csFinder = new ConnectedSubgraphFinder(this.graph);
/* 234 */     List subgraphs = csFinder.getConnectedSubgraphs();
/* 235 */     for (Iterator<Subgraph> i = subgraphs.iterator(); i.hasNext(); ) {
/* 236 */       Subgraph subgraph = i.next();
/* 237 */       if (hasSequence(subgraph)) {
/* 238 */         List seq = findSequence(subgraph);
/* 239 */         sequences.add(seq);
/*     */         continue;
/*     */       } 
/* 243 */       return null;
/*     */     } 
/* 246 */     return sequences;
/*     */   }
/*     */   
/*     */   private boolean hasSequence(Subgraph graph) {
/* 258 */     int oddDegreeCount = 0;
/* 259 */     for (Iterator<Node> i = graph.nodeIterator(); i.hasNext(); ) {
/* 260 */       Node node = i.next();
/* 261 */       if (node.getDegree() % 2 == 1)
/* 262 */         oddDegreeCount++; 
/*     */     } 
/* 264 */     return (oddDegreeCount <= 2);
/*     */   }
/*     */   
/*     */   private List findSequence(Subgraph graph) {
/* 269 */     GraphComponent.setVisited(graph.edgeIterator(), false);
/* 271 */     Node startNode = findLowestDegreeNode(graph);
/* 272 */     DirectedEdge startDE = startNode.getOutEdges().iterator().next();
/* 273 */     DirectedEdge startDESym = startDE.getSym();
/* 275 */     List seq = new LinkedList();
/* 276 */     ListIterator<DirectedEdge> lit = seq.listIterator();
/* 277 */     addReverseSubpath(startDESym, lit, false);
/* 278 */     while (lit.hasPrevious()) {
/* 279 */       DirectedEdge prev = lit.previous();
/* 280 */       DirectedEdge unvisitedOutDE = findUnvisitedBestOrientedDE(prev.getFromNode());
/* 281 */       if (unvisitedOutDE != null)
/* 282 */         addReverseSubpath(unvisitedOutDE.getSym(), lit, true); 
/*     */     } 
/* 290 */     List orientedSeq = orient(seq);
/* 291 */     return orientedSeq;
/*     */   }
/*     */   
/*     */   private static DirectedEdge findUnvisitedBestOrientedDE(Node node) {
/* 303 */     DirectedEdge wellOrientedDE = null;
/* 304 */     DirectedEdge unvisitedDE = null;
/* 305 */     for (Iterator<DirectedEdge> i = node.getOutEdges().iterator(); i.hasNext(); ) {
/* 306 */       DirectedEdge de = i.next();
/* 307 */       if (!de.getEdge().isVisited()) {
/* 308 */         unvisitedDE = de;
/* 309 */         if (de.getEdgeDirection())
/* 310 */           wellOrientedDE = de; 
/*     */       } 
/*     */     } 
/* 313 */     if (wellOrientedDE != null)
/* 314 */       return wellOrientedDE; 
/* 315 */     return unvisitedDE;
/*     */   }
/*     */   
/*     */   private void addReverseSubpath(DirectedEdge de, ListIterator<DirectedEdge> lit, boolean expectedClosed) {
/* 321 */     Node endNode = de.getToNode();
/* 323 */     Node fromNode = null;
/*     */     while (true) {
/* 325 */       lit.add(de.getSym());
/* 326 */       de.getEdge().setVisited(true);
/* 327 */       fromNode = de.getFromNode();
/* 328 */       DirectedEdge unvisitedOutDE = findUnvisitedBestOrientedDE(fromNode);
/* 330 */       if (unvisitedOutDE == null)
/*     */         break; 
/* 332 */       de = unvisitedOutDE.getSym();
/*     */     } 
/* 334 */     if (expectedClosed)
/* 336 */       Assert.isTrue((fromNode == endNode), "path not contiguous"); 
/*     */   }
/*     */   
/*     */   private static Node findLowestDegreeNode(Subgraph graph) {
/* 342 */     int minDegree = Integer.MAX_VALUE;
/* 343 */     Node minDegreeNode = null;
/* 344 */     for (Iterator<Node> i = graph.nodeIterator(); i.hasNext(); ) {
/* 345 */       Node node = i.next();
/* 346 */       if (minDegreeNode == null || node.getDegree() < minDegree) {
/* 347 */         minDegree = node.getDegree();
/* 348 */         minDegreeNode = node;
/*     */       } 
/*     */     } 
/* 351 */     return minDegreeNode;
/*     */   }
/*     */   
/*     */   private List orient(List<DirectedEdge> seq) {
/* 374 */     DirectedEdge startEdge = seq.get(0);
/* 375 */     DirectedEdge endEdge = seq.get(seq.size() - 1);
/* 376 */     Node startNode = startEdge.getFromNode();
/* 377 */     Node endNode = endEdge.getToNode();
/* 379 */     boolean flipSeq = false;
/* 380 */     boolean hasDegree1Node = (startNode.getDegree() == 1 || endNode.getDegree() == 1);
/* 383 */     if (hasDegree1Node) {
/* 384 */       boolean hasObviousStartNode = false;
/* 388 */       if (endEdge.getToNode().getDegree() == 1 && !endEdge.getEdgeDirection()) {
/* 389 */         hasObviousStartNode = true;
/* 390 */         flipSeq = true;
/*     */       } 
/* 392 */       if (startEdge.getFromNode().getDegree() == 1 && startEdge.getEdgeDirection() == true) {
/* 393 */         hasObviousStartNode = true;
/* 394 */         flipSeq = false;
/*     */       } 
/* 398 */       if (!hasObviousStartNode)
/* 400 */         if (startEdge.getFromNode().getDegree() == 1)
/* 401 */           flipSeq = true;  
/*     */     } 
/* 411 */     if (flipSeq)
/* 412 */       return reverse(seq); 
/* 413 */     return seq;
/*     */   }
/*     */   
/*     */   private List reverse(List seq) {
/* 426 */     LinkedList<DirectedEdge> newSeq = new LinkedList();
/* 427 */     for (Iterator<DirectedEdge> i = seq.iterator(); i.hasNext(); ) {
/* 428 */       DirectedEdge de = i.next();
/* 429 */       newSeq.addFirst(de.getSym());
/*     */     } 
/* 431 */     return newSeq;
/*     */   }
/*     */   
/*     */   private Geometry buildSequencedGeometry(List sequences) {
/* 444 */     List<LineString> lines = new ArrayList();
/* 446 */     for (Iterator<List> i1 = sequences.iterator(); i1.hasNext(); ) {
/* 447 */       List seq = i1.next();
/* 448 */       for (Iterator<DirectedEdge> i2 = seq.iterator(); i2.hasNext(); ) {
/* 449 */         DirectedEdge de = i2.next();
/* 450 */         LineMergeEdge e = (LineMergeEdge)de.getEdge();
/* 451 */         LineString line = e.getLine();
/* 453 */         LineString lineToAdd = line;
/* 454 */         if (!de.getEdgeDirection() && !line.isClosed())
/* 455 */           lineToAdd = reverse(line); 
/* 457 */         lines.add(lineToAdd);
/*     */       } 
/*     */     } 
/* 460 */     if (lines.size() == 0)
/* 461 */       return (Geometry)this.factory.createMultiLineString(new LineString[0]); 
/* 462 */     return this.factory.buildGeometry(lines);
/*     */   }
/*     */   
/*     */   private static LineString reverse(LineString line) {
/* 467 */     Coordinate[] pts = line.getCoordinates();
/* 468 */     Coordinate[] revPts = new Coordinate[pts.length];
/* 469 */     int len = pts.length;
/* 470 */     for (int i = 0; i < len; i++)
/* 471 */       revPts[len - 1 - i] = new Coordinate(pts[i]); 
/* 473 */     return line.getFactory().createLineString(revPts);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\linemerge\LineSequencer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */