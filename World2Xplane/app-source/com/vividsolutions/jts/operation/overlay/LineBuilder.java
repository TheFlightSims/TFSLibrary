/*     */ package com.vividsolutions.jts.operation.overlay;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.PointLocator;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdgeStar;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.Label;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class LineBuilder {
/*     */   private OverlayOp op;
/*     */   
/*     */   private GeometryFactory geometryFactory;
/*     */   
/*     */   private PointLocator ptLocator;
/*     */   
/*  52 */   private List lineEdgesList = new ArrayList();
/*     */   
/*  53 */   private List resultLineList = new ArrayList();
/*     */   
/*     */   public LineBuilder(OverlayOp op, GeometryFactory geometryFactory, PointLocator ptLocator) {
/*  56 */     this.op = op;
/*  57 */     this.geometryFactory = geometryFactory;
/*  58 */     this.ptLocator = ptLocator;
/*     */   }
/*     */   
/*     */   public List build(int opCode) {
/*  65 */     findCoveredLineEdges();
/*  66 */     collectLines(opCode);
/*  68 */     buildLines(opCode);
/*  69 */     return this.resultLineList;
/*     */   }
/*     */   
/*     */   private void findCoveredLineEdges() {
/*  81 */     for (Iterator<Node> nodeit = this.op.getGraph().getNodes().iterator(); nodeit.hasNext(); ) {
/*  82 */       Node node = nodeit.next();
/*  84 */       ((DirectedEdgeStar)node.getEdges()).findCoveredLineEdges();
/*     */     } 
/*  91 */     for (Iterator<DirectedEdge> it = this.op.getGraph().getEdgeEnds().iterator(); it.hasNext(); ) {
/*  92 */       DirectedEdge de = it.next();
/*  93 */       Edge e = de.getEdge();
/*  94 */       if (de.isLineEdge() && !e.isCoveredSet()) {
/*  95 */         boolean isCovered = this.op.isCoveredByA(de.getCoordinate());
/*  96 */         e.setCovered(isCovered);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void collectLines(int opCode) {
/* 103 */     for (Iterator<DirectedEdge> it = this.op.getGraph().getEdgeEnds().iterator(); it.hasNext(); ) {
/* 104 */       DirectedEdge de = it.next();
/* 105 */       collectLineEdge(de, opCode, this.lineEdgesList);
/* 106 */       collectBoundaryTouchEdge(de, opCode, this.lineEdgesList);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void collectLineEdge(DirectedEdge de, int opCode, List<Edge> edges) {
/* 122 */     Label label = de.getLabel();
/* 123 */     Edge e = de.getEdge();
/* 125 */     if (de.isLineEdge() && 
/* 126 */       !de.isVisited() && OverlayOp.isResultOfOp(label, opCode) && !e.isCovered()) {
/* 130 */       edges.add(e);
/* 131 */       de.setVisitedEdge(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void collectBoundaryTouchEdge(DirectedEdge de, int opCode, List<Edge> edges) {
/* 148 */     Label label = de.getLabel();
/* 149 */     if (de.isLineEdge())
/*     */       return; 
/* 150 */     if (de.isVisited())
/*     */       return; 
/* 151 */     if (de.isInteriorAreaEdge())
/*     */       return; 
/* 152 */     if (de.getEdge().isInResult())
/*     */       return; 
/* 155 */     Assert.isTrue(((!de.isInResult() && !de.getSym().isInResult()) || !de.getEdge().isInResult()));
/* 158 */     if (OverlayOp.isResultOfOp(label, opCode) && opCode == 1) {
/* 161 */       edges.add(de.getEdge());
/* 162 */       de.setVisitedEdge(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void buildLines(int opCode) {
/* 168 */     for (Iterator<Edge> it = this.lineEdgesList.iterator(); it.hasNext(); ) {
/* 169 */       Edge e = it.next();
/* 170 */       Label label = e.getLabel();
/* 171 */       LineString line = this.geometryFactory.createLineString(e.getCoordinates());
/* 172 */       this.resultLineList.add(line);
/* 173 */       e.setInResult(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void labelIsolatedLines(List edgesList) {
/* 179 */     for (Iterator<Edge> it = edgesList.iterator(); it.hasNext(); ) {
/* 180 */       Edge e = it.next();
/* 181 */       Label label = e.getLabel();
/* 183 */       if (e.isIsolated()) {
/* 184 */         if (label.isNull(0)) {
/* 185 */           labelIsolatedLine(e, 0);
/*     */           continue;
/*     */         } 
/* 187 */         labelIsolatedLine(e, 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void labelIsolatedLine(Edge e, int targetIndex) {
/* 196 */     int loc = this.ptLocator.locate(e.getCoordinate(), this.op.getArgGeometry(targetIndex));
/* 197 */     e.getLabel().setLocation(targetIndex, loc);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\LineBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */