/*     */ package com.vividsolutions.jts.operation.overlay;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdgeStar;
/*     */ import com.vividsolutions.jts.geomgraph.Label;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import com.vividsolutions.jts.geomgraph.PlanarGraph;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ConsistentPolygonRingChecker {
/*     */   private PlanarGraph graph;
/*     */   
/*     */   private final int SCANNING_FOR_INCOMING = 1;
/*     */   
/*     */   private final int LINKING_TO_OUTGOING = 2;
/*     */   
/*     */   public ConsistentPolygonRingChecker(PlanarGraph graph) {
/* 110 */     this.SCANNING_FOR_INCOMING = 1;
/* 111 */     this.LINKING_TO_OUTGOING = 2;
/*     */     this.graph = graph;
/*     */   }
/*     */   
/*     */   public void checkAll() {
/*     */     check(1);
/*     */     check(3);
/*     */     check(2);
/*     */     check(4);
/*     */   }
/*     */   
/*     */   public void check(int opCode) {
/*     */     for (Iterator<Node> nodeit = this.graph.getNodeIterator(); nodeit.hasNext(); ) {
/*     */       Node node = nodeit.next();
/*     */       testLinkResultDirectedEdges((DirectedEdgeStar)node.getEdges(), opCode);
/*     */     } 
/*     */   }
/*     */   
/*     */   private List getPotentialResultAreaEdges(DirectedEdgeStar deStar, int opCode) {
/*     */     List<DirectedEdge> resultAreaEdgeList = new ArrayList();
/*     */     for (Iterator<DirectedEdge> it = deStar.iterator(); it.hasNext(); ) {
/*     */       DirectedEdge de = it.next();
/*     */       if (isPotentialResultAreaEdge(de, opCode) || isPotentialResultAreaEdge(de.getSym(), opCode))
/*     */         resultAreaEdgeList.add(de); 
/*     */     } 
/*     */     return resultAreaEdgeList;
/*     */   }
/*     */   
/*     */   private boolean isPotentialResultAreaEdge(DirectedEdge de, int opCode) {
/*     */     Label label = de.getLabel();
/*     */     if (label.isArea() && !de.isInteriorAreaEdge() && OverlayOp.isResultOfOp(label.getLocation(0, 2), label.getLocation(1, 2), opCode))
/*     */       return true; 
/*     */     return false;
/*     */   }
/*     */   
/*     */   private void testLinkResultDirectedEdges(DirectedEdgeStar deStar, int opCode) {
/* 116 */     List<DirectedEdge> ringEdges = getPotentialResultAreaEdges(deStar, opCode);
/* 118 */     DirectedEdge firstOut = null;
/* 119 */     DirectedEdge incoming = null;
/* 120 */     int state = 1;
/* 122 */     for (int i = 0; i < ringEdges.size(); i++) {
/* 123 */       DirectedEdge nextOut = ringEdges.get(i);
/* 124 */       DirectedEdge nextIn = nextOut.getSym();
/* 127 */       if (nextOut.getLabel().isArea()) {
/* 130 */         if (firstOut == null && isPotentialResultAreaEdge(nextOut, opCode))
/* 132 */           firstOut = nextOut; 
/* 135 */         switch (state) {
/*     */           case 1:
/* 137 */             if (!isPotentialResultAreaEdge(nextIn, opCode))
/*     */               break; 
/* 138 */             incoming = nextIn;
/* 139 */             state = 2;
/*     */             break;
/*     */           case 2:
/* 142 */             if (!isPotentialResultAreaEdge(nextOut, opCode))
/*     */               break; 
/* 144 */             state = 1;
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 149 */     if (state == 2)
/* 151 */       if (firstOut == null)
/* 152 */         throw new TopologyException("no outgoing dirEdge found", deStar.getCoordinate());  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\ConsistentPolygonRingChecker.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */