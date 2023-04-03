/*     */ package com.vividsolutions.jts.operation.relate;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.PointLocator;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.IntersectionMatrix;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeEnd;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeIntersection;
/*     */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*     */ import com.vividsolutions.jts.geomgraph.Label;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import com.vividsolutions.jts.geomgraph.NodeMap;
/*     */ import com.vividsolutions.jts.geomgraph.index.SegmentIntersector;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class RelateComputer {
/*  65 */   private LineIntersector li = (LineIntersector)new RobustLineIntersector();
/*     */   
/*  66 */   private PointLocator ptLocator = new PointLocator();
/*     */   
/*     */   private GeometryGraph[] arg;
/*     */   
/*  68 */   private NodeMap nodes = new NodeMap(new RelateNodeFactory());
/*     */   
/*  70 */   private IntersectionMatrix im = null;
/*     */   
/*  71 */   private ArrayList isolatedEdges = new ArrayList();
/*     */   
/*     */   private Coordinate invalidPoint;
/*     */   
/*     */   public RelateComputer(GeometryGraph[] arg) {
/*  77 */     this.arg = arg;
/*     */   }
/*     */   
/*     */   public IntersectionMatrix computeIM() {
/*  82 */     IntersectionMatrix im = new IntersectionMatrix();
/*  84 */     im.set(2, 2, 2);
/*  87 */     if (!this.arg[0].getGeometry().getEnvelopeInternal().intersects(this.arg[1].getGeometry().getEnvelopeInternal())) {
/*  89 */       computeDisjointIM(im);
/*  90 */       return im;
/*     */     } 
/*  92 */     this.arg[0].computeSelfNodes(this.li, false);
/*  93 */     this.arg[1].computeSelfNodes(this.li, false);
/*  96 */     SegmentIntersector intersector = this.arg[0].computeEdgeIntersections(this.arg[1], this.li, false);
/*  98 */     computeIntersectionNodes(0);
/*  99 */     computeIntersectionNodes(1);
/* 104 */     copyNodesAndLabels(0);
/* 105 */     copyNodesAndLabels(1);
/* 110 */     labelIsolatedNodes();
/* 114 */     computeProperIntersectionIM(intersector, im);
/* 123 */     EdgeEndBuilder eeBuilder = new EdgeEndBuilder();
/* 124 */     List ee0 = eeBuilder.computeEdgeEnds(this.arg[0].getEdgeIterator());
/* 125 */     insertEdgeEnds(ee0);
/* 126 */     List ee1 = eeBuilder.computeEdgeEnds(this.arg[1].getEdgeIterator());
/* 127 */     insertEdgeEnds(ee1);
/* 132 */     labelNodeEdges();
/* 144 */     labelIsolatedEdges(0, 1);
/* 146 */     labelIsolatedEdges(1, 0);
/* 149 */     updateIM(im);
/* 150 */     return im;
/*     */   }
/*     */   
/*     */   private void insertEdgeEnds(List ee) {
/* 155 */     for (Iterator<EdgeEnd> i = ee.iterator(); i.hasNext(); ) {
/* 156 */       EdgeEnd e = i.next();
/* 157 */       this.nodes.add(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeProperIntersectionIM(SegmentIntersector intersector, IntersectionMatrix im) {
/* 164 */     int dimA = this.arg[0].getGeometry().getDimension();
/* 165 */     int dimB = this.arg[1].getGeometry().getDimension();
/* 166 */     boolean hasProper = intersector.hasProperIntersection();
/* 167 */     boolean hasProperInterior = intersector.hasProperInteriorIntersection();
/* 174 */     if (dimA == 2 && dimB == 2) {
/* 175 */       if (hasProper)
/* 175 */         im.setAtLeast("212101212"); 
/* 185 */     } else if (dimA == 2 && dimB == 1) {
/* 186 */       if (hasProper)
/* 186 */         im.setAtLeast("FFF0FFFF2"); 
/* 187 */       if (hasProperInterior)
/* 187 */         im.setAtLeast("1FFFFF1FF"); 
/* 189 */     } else if (dimA == 1 && dimB == 2) {
/* 190 */       if (hasProper)
/* 190 */         im.setAtLeast("F0FFFFFF2"); 
/* 191 */       if (hasProperInterior)
/* 191 */         im.setAtLeast("1F1FFFFFF"); 
/* 202 */     } else if (dimA == 1 && dimB == 1 && 
/* 203 */       hasProperInterior) {
/* 203 */       im.setAtLeast("0FFFFFFFF");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void copyNodesAndLabels(int argIndex) {
/* 218 */     for (Iterator<Node> i = this.arg[argIndex].getNodeIterator(); i.hasNext(); ) {
/* 219 */       Node graphNode = i.next();
/* 220 */       Node newNode = this.nodes.addNode(graphNode.getCoordinate());
/* 221 */       newNode.setLabel(argIndex, graphNode.getLabel().getLocation(argIndex));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeIntersectionNodes(int argIndex) {
/* 234 */     for (Iterator<Edge> i = this.arg[argIndex].getEdgeIterator(); i.hasNext(); ) {
/* 235 */       Edge e = i.next();
/* 236 */       int eLoc = e.getLabel().getLocation(argIndex);
/* 237 */       for (Iterator<EdgeIntersection> eiIt = e.getEdgeIntersectionList().iterator(); eiIt.hasNext(); ) {
/* 238 */         EdgeIntersection ei = eiIt.next();
/* 239 */         RelateNode n = (RelateNode)this.nodes.addNode(ei.coord);
/* 240 */         if (eLoc == 1) {
/* 241 */           n.setLabelBoundary(argIndex);
/*     */           continue;
/*     */         } 
/* 243 */         if (n.getLabel().isNull(argIndex))
/* 244 */           n.setLabel(argIndex, 0); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void labelIntersectionNodes(int argIndex) {
/* 259 */     for (Iterator<Edge> i = this.arg[argIndex].getEdgeIterator(); i.hasNext(); ) {
/* 260 */       Edge e = i.next();
/* 261 */       int eLoc = e.getLabel().getLocation(argIndex);
/* 262 */       for (Iterator<EdgeIntersection> eiIt = e.getEdgeIntersectionList().iterator(); eiIt.hasNext(); ) {
/* 263 */         EdgeIntersection ei = eiIt.next();
/* 264 */         RelateNode n = (RelateNode)this.nodes.find(ei.coord);
/* 265 */         if (n.getLabel().isNull(argIndex)) {
/* 266 */           if (eLoc == 1) {
/* 267 */             n.setLabelBoundary(argIndex);
/*     */             continue;
/*     */           } 
/* 269 */           n.setLabel(argIndex, 0);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeDisjointIM(IntersectionMatrix im) {
/* 281 */     Geometry ga = this.arg[0].getGeometry();
/* 282 */     if (!ga.isEmpty()) {
/* 283 */       im.set(0, 2, ga.getDimension());
/* 284 */       im.set(1, 2, ga.getBoundaryDimension());
/*     */     } 
/* 286 */     Geometry gb = this.arg[1].getGeometry();
/* 287 */     if (!gb.isEmpty()) {
/* 288 */       im.set(2, 0, gb.getDimension());
/* 289 */       im.set(2, 1, gb.getBoundaryDimension());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void labelNodeEdges() {
/* 295 */     for (Iterator<RelateNode> ni = this.nodes.iterator(); ni.hasNext(); ) {
/* 296 */       RelateNode node = ni.next();
/* 297 */       node.getEdges().computeLabelling(this.arg);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateIM(IntersectionMatrix im) {
/* 309 */     for (Iterator<Edge> ei = this.isolatedEdges.iterator(); ei.hasNext(); ) {
/* 310 */       Edge e = ei.next();
/* 311 */       e.updateIM(im);
/*     */     } 
/* 314 */     for (Iterator<RelateNode> ni = this.nodes.iterator(); ni.hasNext(); ) {
/* 315 */       RelateNode node = ni.next();
/* 316 */       node.updateIM(im);
/* 318 */       node.updateIMFromEdges(im);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void labelIsolatedEdges(int thisIndex, int targetIndex) {
/* 333 */     for (Iterator<Edge> ei = this.arg[thisIndex].getEdgeIterator(); ei.hasNext(); ) {
/* 334 */       Edge e = ei.next();
/* 335 */       if (e.isIsolated()) {
/* 336 */         labelIsolatedEdge(e, targetIndex, this.arg[targetIndex].getGeometry());
/* 337 */         this.isolatedEdges.add(e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void labelIsolatedEdge(Edge e, int targetIndex, Geometry target) {
/* 349 */     if (target.getDimension() > 0) {
/* 353 */       int loc = this.ptLocator.locate(e.getCoordinate(), target);
/* 354 */       e.getLabel().setAllLocations(targetIndex, loc);
/*     */     } else {
/* 357 */       e.getLabel().setAllLocations(targetIndex, 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void labelIsolatedNodes() {
/* 373 */     for (Iterator<Node> ni = this.nodes.iterator(); ni.hasNext(); ) {
/* 374 */       Node n = ni.next();
/* 375 */       Label label = n.getLabel();
/* 377 */       Assert.isTrue((label.getGeometryCount() > 0), "node with empty label found");
/* 378 */       if (n.isIsolated()) {
/* 379 */         if (label.isNull(0)) {
/* 380 */           labelIsolatedNode(n, 0);
/*     */           continue;
/*     */         } 
/* 382 */         labelIsolatedNode(n, 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void labelIsolatedNode(Node n, int targetIndex) {
/* 392 */     int loc = this.ptLocator.locate(n.getCoordinate(), this.arg[targetIndex].getGeometry());
/* 393 */     n.getLabel().setAllLocations(targetIndex, loc);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\relate\RelateComputer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */