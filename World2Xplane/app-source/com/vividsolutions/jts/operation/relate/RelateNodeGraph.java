/*     */ package com.vividsolutions.jts.operation.relate;
/*     */ 
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeEnd;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeIntersection;
/*     */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import com.vividsolutions.jts.geomgraph.NodeMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class RelateNodeGraph {
/*  66 */   private NodeMap nodes = new NodeMap(new RelateNodeFactory());
/*     */   
/*     */   public Iterator getNodeIterator() {
/*  71 */     return this.nodes.iterator();
/*     */   }
/*     */   
/*     */   public void build(GeometryGraph geomGraph) {
/*  76 */     computeIntersectionNodes(geomGraph, 0);
/*  81 */     copyNodesAndLabels(geomGraph, 0);
/*  86 */     EdgeEndBuilder eeBuilder = new EdgeEndBuilder();
/*  87 */     List eeList = eeBuilder.computeEdgeEnds(geomGraph.getEdgeIterator());
/*  88 */     insertEdgeEnds(eeList);
/*     */   }
/*     */   
/*     */   public void computeIntersectionNodes(GeometryGraph geomGraph, int argIndex) {
/* 105 */     for (Iterator<Edge> edgeIt = geomGraph.getEdgeIterator(); edgeIt.hasNext(); ) {
/* 106 */       Edge e = edgeIt.next();
/* 107 */       int eLoc = e.getLabel().getLocation(argIndex);
/* 108 */       for (Iterator<EdgeIntersection> eiIt = e.getEdgeIntersectionList().iterator(); eiIt.hasNext(); ) {
/* 109 */         EdgeIntersection ei = eiIt.next();
/* 110 */         RelateNode n = (RelateNode)this.nodes.addNode(ei.coord);
/* 111 */         if (eLoc == 1) {
/* 112 */           n.setLabelBoundary(argIndex);
/*     */           continue;
/*     */         } 
/* 114 */         if (n.getLabel().isNull(argIndex))
/* 115 */           n.setLabel(argIndex, 0); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void copyNodesAndLabels(GeometryGraph geomGraph, int argIndex) {
/* 133 */     for (Iterator<Node> nodeIt = geomGraph.getNodeIterator(); nodeIt.hasNext(); ) {
/* 134 */       Node graphNode = nodeIt.next();
/* 135 */       Node newNode = this.nodes.addNode(graphNode.getCoordinate());
/* 136 */       newNode.setLabel(argIndex, graphNode.getLabel().getLocation(argIndex));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void insertEdgeEnds(List ee) {
/* 143 */     for (Iterator<EdgeEnd> i = ee.iterator(); i.hasNext(); ) {
/* 144 */       EdgeEnd e = i.next();
/* 145 */       this.nodes.add(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\relate\RelateNodeGraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */