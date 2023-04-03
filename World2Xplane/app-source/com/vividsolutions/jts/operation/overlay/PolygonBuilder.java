/*     */ package com.vividsolutions.jts.operation.overlay;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeRing;
/*     */ import com.vividsolutions.jts.geomgraph.PlanarGraph;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PolygonBuilder {
/*     */   private GeometryFactory geometryFactory;
/*     */   
/*  53 */   private List shellList = new ArrayList();
/*     */   
/*     */   public PolygonBuilder(GeometryFactory geometryFactory) {
/*  57 */     this.geometryFactory = geometryFactory;
/*     */   }
/*     */   
/*     */   public void add(PlanarGraph graph) {
/*  67 */     add(graph.getEdgeEnds(), graph.getNodes());
/*     */   }
/*     */   
/*     */   public void add(Collection dirEdges, Collection nodes) {
/*  77 */     PlanarGraph.linkResultDirectedEdges(nodes);
/*  78 */     List maxEdgeRings = buildMaximalEdgeRings(dirEdges);
/*  79 */     List freeHoleList = new ArrayList();
/*  80 */     List edgeRings = buildMinimalEdgeRings(maxEdgeRings, this.shellList, freeHoleList);
/*  81 */     sortShellsAndHoles(edgeRings, this.shellList, freeHoleList);
/*  82 */     placeFreeHoles(this.shellList, freeHoleList);
/*     */   }
/*     */   
/*     */   public List getPolygons() {
/*  88 */     List resultPolyList = computePolygons(this.shellList);
/*  89 */     return resultPolyList;
/*     */   }
/*     */   
/*     */   private List buildMaximalEdgeRings(Collection dirEdges) {
/*  98 */     List<MaximalEdgeRing> maxEdgeRings = new ArrayList();
/*  99 */     for (Iterator<DirectedEdge> it = dirEdges.iterator(); it.hasNext(); ) {
/* 100 */       DirectedEdge de = it.next();
/* 101 */       if (de.isInResult() && de.getLabel().isArea())
/* 103 */         if (de.getEdgeRing() == null) {
/* 104 */           MaximalEdgeRing er = new MaximalEdgeRing(de, this.geometryFactory);
/* 105 */           maxEdgeRings.add(er);
/* 106 */           er.setInResult();
/*     */         }  
/*     */     } 
/* 111 */     return maxEdgeRings;
/*     */   }
/*     */   
/*     */   private List buildMinimalEdgeRings(List maxEdgeRings, List<EdgeRing> shellList, List freeHoleList) {
/* 116 */     List<MaximalEdgeRing> edgeRings = new ArrayList();
/* 117 */     for (Iterator<MaximalEdgeRing> it = maxEdgeRings.iterator(); it.hasNext(); ) {
/* 118 */       MaximalEdgeRing er = it.next();
/* 119 */       if (er.getMaxNodeDegree() > 2) {
/* 120 */         er.linkDirectedEdgesForMinimalEdgeRings();
/* 121 */         List minEdgeRings = er.buildMinimalRings();
/* 123 */         EdgeRing shell = findShell(minEdgeRings);
/* 124 */         if (shell != null) {
/* 125 */           placePolygonHoles(shell, minEdgeRings);
/* 126 */           shellList.add(shell);
/*     */           continue;
/*     */         } 
/* 129 */         freeHoleList.addAll(minEdgeRings);
/*     */         continue;
/*     */       } 
/* 133 */       edgeRings.add(er);
/*     */     } 
/* 136 */     return edgeRings;
/*     */   }
/*     */   
/*     */   private EdgeRing findShell(List minEdgeRings) {
/* 151 */     int shellCount = 0;
/* 152 */     EdgeRing shell = null;
/* 153 */     for (Iterator<MinimalEdgeRing> it = minEdgeRings.iterator(); it.hasNext(); ) {
/* 154 */       EdgeRing er = it.next();
/* 155 */       if (!er.isHole()) {
/* 156 */         shell = er;
/* 157 */         shellCount++;
/*     */       } 
/*     */     } 
/* 160 */     Assert.isTrue((shellCount <= 1), "found two shells in MinimalEdgeRing list");
/* 161 */     return shell;
/*     */   }
/*     */   
/*     */   private void placePolygonHoles(EdgeRing shell, List minEdgeRings) {
/* 176 */     for (Iterator<MinimalEdgeRing> it = minEdgeRings.iterator(); it.hasNext(); ) {
/* 177 */       MinimalEdgeRing er = it.next();
/* 178 */       if (er.isHole())
/* 179 */         er.setShell(shell); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sortShellsAndHoles(List edgeRings, List<EdgeRing> shellList, List<EdgeRing> freeHoleList) {
/* 192 */     for (Iterator<EdgeRing> it = edgeRings.iterator(); it.hasNext(); ) {
/* 193 */       EdgeRing er = it.next();
/* 195 */       if (er.isHole()) {
/* 196 */         freeHoleList.add(er);
/*     */         continue;
/*     */       } 
/* 199 */       shellList.add(er);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void placeFreeHoles(List shellList, List freeHoleList) {
/* 218 */     for (Iterator<EdgeRing> it = freeHoleList.iterator(); it.hasNext(); ) {
/* 219 */       EdgeRing hole = it.next();
/* 221 */       if (hole.getShell() == null) {
/* 222 */         EdgeRing shell = findEdgeRingContaining(hole, shellList);
/* 223 */         if (shell == null)
/* 224 */           throw new TopologyException("unable to assign hole to a shell", hole.getCoordinate(0)); 
/* 226 */         hole.setShell(shell);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private EdgeRing findEdgeRingContaining(EdgeRing testEr, List shellList) {
/* 247 */     LinearRing testRing = testEr.getLinearRing();
/* 248 */     Envelope testEnv = testRing.getEnvelopeInternal();
/* 249 */     Coordinate testPt = testRing.getCoordinateN(0);
/* 251 */     EdgeRing minShell = null;
/* 252 */     Envelope minEnv = null;
/* 253 */     for (Iterator<EdgeRing> it = shellList.iterator(); it.hasNext(); ) {
/* 254 */       EdgeRing tryShell = it.next();
/* 255 */       LinearRing tryRing = tryShell.getLinearRing();
/* 256 */       Envelope tryEnv = tryRing.getEnvelopeInternal();
/* 257 */       if (minShell != null)
/* 257 */         minEnv = minShell.getLinearRing().getEnvelopeInternal(); 
/* 258 */       boolean isContained = false;
/* 259 */       if (tryEnv.contains(testEnv) && CGAlgorithms.isPointInRing(testPt, tryRing.getCoordinates()))
/* 261 */         isContained = true; 
/* 263 */       if (isContained && (
/* 264 */         minShell == null || minEnv.contains(tryEnv)))
/* 266 */         minShell = tryShell; 
/*     */     } 
/* 270 */     return minShell;
/*     */   }
/*     */   
/*     */   private List computePolygons(List shellList) {
/* 274 */     List<Polygon> resultPolyList = new ArrayList();
/* 276 */     for (Iterator<EdgeRing> it = shellList.iterator(); it.hasNext(); ) {
/* 277 */       EdgeRing er = it.next();
/* 278 */       Polygon poly = er.toPolygon(this.geometryFactory);
/* 279 */       resultPolyList.add(poly);
/*     */     } 
/* 281 */     return resultPolyList;
/*     */   }
/*     */   
/*     */   public boolean containsPoint(Coordinate p) {
/* 290 */     for (Iterator<EdgeRing> it = this.shellList.iterator(); it.hasNext(); ) {
/* 291 */       EdgeRing er = it.next();
/* 292 */       if (er.containsPoint(p))
/* 293 */         return true; 
/*     */     } 
/* 295 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\PolygonBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */