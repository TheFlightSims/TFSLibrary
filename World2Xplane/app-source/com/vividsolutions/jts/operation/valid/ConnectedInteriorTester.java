/*     */ package com.vividsolutions.jts.operation.valid;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeRing;
/*     */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*     */ import com.vividsolutions.jts.geomgraph.NodeFactory;
/*     */ import com.vividsolutions.jts.geomgraph.PlanarGraph;
/*     */ import com.vividsolutions.jts.operation.overlay.MaximalEdgeRing;
/*     */ import com.vividsolutions.jts.operation.overlay.OverlayNodeFactory;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ConnectedInteriorTester {
/*     */   public static Coordinate findDifferentPoint(Coordinate[] coord, Coordinate pt) {
/*  62 */     for (int i = 0; i < coord.length; i++) {
/*  63 */       if (!coord[i].equals(pt))
/*  64 */         return coord[i]; 
/*     */     } 
/*  66 */     return null;
/*     */   }
/*     */   
/*  69 */   private GeometryFactory geometryFactory = new GeometryFactory();
/*     */   
/*     */   private GeometryGraph geomGraph;
/*     */   
/*     */   private Coordinate disconnectedRingcoord;
/*     */   
/*     */   public ConnectedInteriorTester(GeometryGraph geomGraph) {
/*  78 */     this.geomGraph = geomGraph;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  81 */     return this.disconnectedRingcoord;
/*     */   }
/*     */   
/*     */   public boolean isInteriorsConnected() {
/*  86 */     List splitEdges = new ArrayList();
/*  87 */     this.geomGraph.computeSplitEdges(splitEdges);
/*  90 */     PlanarGraph graph = new PlanarGraph((NodeFactory)new OverlayNodeFactory());
/*  91 */     graph.addEdges(splitEdges);
/*  92 */     setInteriorEdgesInResult(graph);
/*  93 */     graph.linkResultDirectedEdges();
/*  94 */     List edgeRings = buildEdgeRings(graph.getEdgeEnds());
/* 100 */     visitShellInteriors(this.geomGraph.getGeometry(), graph);
/* 109 */     return !hasUnvisitedShellEdge(edgeRings);
/*     */   }
/*     */   
/*     */   private void setInteriorEdgesInResult(PlanarGraph graph) {
/* 114 */     for (Iterator<DirectedEdge> it = graph.getEdgeEnds().iterator(); it.hasNext(); ) {
/* 115 */       DirectedEdge de = it.next();
/* 116 */       if (de.getLabel().getLocation(0, 2) == 0)
/* 117 */         de.setInResult(true); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private List buildEdgeRings(Collection dirEdges) {
/* 129 */     List edgeRings = new ArrayList();
/* 130 */     for (Iterator<DirectedEdge> it = dirEdges.iterator(); it.hasNext(); ) {
/* 131 */       DirectedEdge de = it.next();
/* 133 */       if (de.isInResult() && de.getEdgeRing() == null) {
/* 135 */         MaximalEdgeRing er = new MaximalEdgeRing(de, this.geometryFactory);
/* 137 */         er.linkDirectedEdgesForMinimalEdgeRings();
/* 138 */         List minEdgeRings = er.buildMinimalRings();
/* 139 */         edgeRings.addAll(minEdgeRings);
/*     */       } 
/*     */     } 
/* 142 */     return edgeRings;
/*     */   }
/*     */   
/*     */   private void visitShellInteriors(Geometry g, PlanarGraph graph) {
/* 153 */     if (g instanceof Polygon) {
/* 154 */       Polygon p = (Polygon)g;
/* 155 */       visitInteriorRing(p.getExteriorRing(), graph);
/*     */     } 
/* 157 */     if (g instanceof MultiPolygon) {
/* 158 */       MultiPolygon mp = (MultiPolygon)g;
/* 159 */       for (int i = 0; i < mp.getNumGeometries(); i++) {
/* 160 */         Polygon p = (Polygon)mp.getGeometryN(i);
/* 161 */         visitInteriorRing(p.getExteriorRing(), graph);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void visitInteriorRing(LineString ring, PlanarGraph graph) {
/* 168 */     Coordinate[] pts = ring.getCoordinates();
/* 169 */     Coordinate pt0 = pts[0];
/* 174 */     Coordinate pt1 = findDifferentPoint(pts, pt0);
/* 175 */     Edge e = graph.findEdgeInSameDirection(pt0, pt1);
/* 176 */     DirectedEdge de = (DirectedEdge)graph.findEdgeEnd(e);
/* 177 */     DirectedEdge intDe = null;
/* 178 */     if (de.getLabel().getLocation(0, 2) == 0) {
/* 179 */       intDe = de;
/* 181 */     } else if (de.getSym().getLabel().getLocation(0, 2) == 0) {
/* 182 */       intDe = de.getSym();
/*     */     } 
/* 184 */     Assert.isTrue((intDe != null), "unable to find dirEdge with Interior on RHS");
/* 186 */     visitLinkedDirectedEdges(intDe);
/*     */   }
/*     */   
/*     */   protected void visitLinkedDirectedEdges(DirectedEdge start) {
/* 191 */     DirectedEdge startDe = start;
/* 192 */     DirectedEdge de = start;
/*     */     do {
/* 194 */       Assert.isTrue((de != null), "found null Directed Edge");
/* 195 */       de.setVisited(true);
/* 196 */       de = de.getNext();
/* 197 */     } while (de != startDe);
/*     */   }
/*     */   
/*     */   private boolean hasUnvisitedShellEdge(List<EdgeRing> edgeRings) {
/* 212 */     for (int i = 0; i < edgeRings.size(); i++) {
/* 213 */       EdgeRing er = edgeRings.get(i);
/* 215 */       if (!er.isHole()) {
/* 217 */         List<DirectedEdge> edges = er.getEdges();
/* 218 */         DirectedEdge de = edges.get(0);
/* 221 */         if (de.getLabel().getLocation(0, 2) == 0)
/* 227 */           for (int j = 0; j < edges.size(); j++) {
/* 228 */             de = edges.get(j);
/* 230 */             if (!de.isVisited()) {
/* 232 */               this.disconnectedRingcoord = de.getCoordinate();
/* 233 */               return true;
/*     */             } 
/*     */           }  
/*     */       } 
/*     */     } 
/* 237 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\valid\ConnectedInteriorTester.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */