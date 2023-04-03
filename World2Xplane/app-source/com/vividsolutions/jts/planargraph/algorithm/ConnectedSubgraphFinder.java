/*     */ package com.vividsolutions.jts.planargraph.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.planargraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.planargraph.Edge;
/*     */ import com.vividsolutions.jts.planargraph.GraphComponent;
/*     */ import com.vividsolutions.jts.planargraph.Node;
/*     */ import com.vividsolutions.jts.planargraph.PlanarGraph;
/*     */ import com.vividsolutions.jts.planargraph.Subgraph;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Stack;
/*     */ 
/*     */ public class ConnectedSubgraphFinder {
/*     */   private PlanarGraph graph;
/*     */   
/*     */   public ConnectedSubgraphFinder(PlanarGraph graph) {
/*  50 */     this.graph = graph;
/*     */   }
/*     */   
/*     */   public List getConnectedSubgraphs() {
/*  55 */     List<Subgraph> subgraphs = new ArrayList();
/*  57 */     GraphComponent.setVisited(this.graph.nodeIterator(), false);
/*  58 */     for (Iterator<Edge> i = this.graph.edgeIterator(); i.hasNext(); ) {
/*  59 */       Edge e = i.next();
/*  60 */       Node node = e.getDirEdge(0).getFromNode();
/*  61 */       if (!node.isVisited())
/*  62 */         subgraphs.add(findSubgraph(node)); 
/*     */     } 
/*  65 */     return subgraphs;
/*     */   }
/*     */   
/*     */   private Subgraph findSubgraph(Node node) {
/*  70 */     Subgraph subgraph = new Subgraph(this.graph);
/*  71 */     addReachable(node, subgraph);
/*  72 */     return subgraph;
/*     */   }
/*     */   
/*     */   private void addReachable(Node startNode, Subgraph subgraph) {
/*  83 */     Stack<Node> nodeStack = new Stack();
/*  84 */     nodeStack.add(startNode);
/*  85 */     while (!nodeStack.empty()) {
/*  86 */       Node node = nodeStack.pop();
/*  87 */       addEdges(node, nodeStack, subgraph);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addEdges(Node node, Stack<Node> nodeStack, Subgraph subgraph) {
/*  98 */     node.setVisited(true);
/*  99 */     for (Iterator<DirectedEdge> i = node.getOutEdges().iterator(); i.hasNext(); ) {
/* 100 */       DirectedEdge de = i.next();
/* 101 */       subgraph.add(de.getEdge());
/* 102 */       Node toNode = de.getToNode();
/* 103 */       if (!toNode.isVisited())
/* 103 */         nodeStack.push(toNode); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\planargraph\algorithm\ConnectedSubgraphFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */