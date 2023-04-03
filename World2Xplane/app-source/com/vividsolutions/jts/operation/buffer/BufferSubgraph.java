/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdgeStar;
/*     */ import com.vividsolutions.jts.geomgraph.Label;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.Stack;
/*     */ 
/*     */ class BufferSubgraph implements Comparable {
/*     */   private RightmostEdgeFinder finder;
/*     */   
/*  64 */   private List dirEdgeList = new ArrayList();
/*     */   
/*  65 */   private List nodes = new ArrayList();
/*     */   
/*  66 */   private Coordinate rightMostCoord = null;
/*     */   
/*  67 */   private Envelope env = null;
/*     */   
/*     */   public BufferSubgraph() {
/*  71 */     this.finder = new RightmostEdgeFinder();
/*     */   }
/*     */   
/*     */   public List getDirectedEdges() {
/*  74 */     return this.dirEdgeList;
/*     */   }
/*     */   
/*     */   public List getNodes() {
/*  75 */     return this.nodes;
/*     */   }
/*     */   
/*     */   public Envelope getEnvelope() {
/*  85 */     if (this.env == null) {
/*  86 */       Envelope edgeEnv = new Envelope();
/*  87 */       for (Iterator<DirectedEdge> it = this.dirEdgeList.iterator(); it.hasNext(); ) {
/*  88 */         DirectedEdge dirEdge = it.next();
/*  89 */         Coordinate[] pts = dirEdge.getEdge().getCoordinates();
/*  90 */         for (int i = 0; i < pts.length - 1; i++)
/*  91 */           edgeEnv.expandToInclude(pts[i]); 
/*     */       } 
/*  94 */       this.env = edgeEnv;
/*     */     } 
/*  96 */     return this.env;
/*     */   }
/*     */   
/*     */   public Coordinate getRightmostCoordinate() {
/* 104 */     return this.rightMostCoord;
/*     */   }
/*     */   
/*     */   public void create(Node node) {
/* 115 */     addReachable(node);
/* 116 */     this.finder.findEdge(this.dirEdgeList);
/* 117 */     this.rightMostCoord = this.finder.getCoordinate();
/*     */   }
/*     */   
/*     */   private void addReachable(Node startNode) {
/* 128 */     Stack<Node> nodeStack = new Stack();
/* 129 */     nodeStack.add(startNode);
/* 130 */     while (!nodeStack.empty()) {
/* 131 */       Node node = nodeStack.pop();
/* 132 */       add(node, nodeStack);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void add(Node node, Stack<Node> nodeStack) {
/* 143 */     node.setVisited(true);
/* 144 */     this.nodes.add(node);
/* 145 */     for (Iterator<DirectedEdge> i = ((DirectedEdgeStar)node.getEdges()).iterator(); i.hasNext(); ) {
/* 146 */       DirectedEdge de = i.next();
/* 147 */       this.dirEdgeList.add(de);
/* 148 */       DirectedEdge sym = de.getSym();
/* 149 */       Node symNode = sym.getNode();
/* 155 */       if (!symNode.isVisited())
/* 155 */         nodeStack.push(symNode); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void clearVisitedEdges() {
/* 161 */     for (Iterator<DirectedEdge> it = this.dirEdgeList.iterator(); it.hasNext(); ) {
/* 162 */       DirectedEdge de = it.next();
/* 163 */       de.setVisited(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void computeDepth(int outsideDepth) {
/* 169 */     clearVisitedEdges();
/* 171 */     DirectedEdge de = this.finder.getEdge();
/* 172 */     Node n = de.getNode();
/* 173 */     Label label = de.getLabel();
/* 175 */     de.setEdgeDepths(2, outsideDepth);
/* 176 */     copySymDepths(de);
/* 179 */     computeDepths(de);
/*     */   }
/*     */   
/*     */   private void computeDepths(DirectedEdge startEdge) {
/* 189 */     Set<Node> nodesVisited = new HashSet();
/* 190 */     LinkedList<Node> nodeQueue = new LinkedList();
/* 192 */     Node startNode = startEdge.getNode();
/* 193 */     nodeQueue.addLast(startNode);
/* 194 */     nodesVisited.add(startNode);
/* 195 */     startEdge.setVisited(true);
/* 197 */     while (!nodeQueue.isEmpty()) {
/* 199 */       Node n = nodeQueue.removeFirst();
/* 200 */       nodesVisited.add(n);
/* 202 */       computeNodeDepth(n);
/* 206 */       for (Iterator<DirectedEdge> i = ((DirectedEdgeStar)n.getEdges()).iterator(); i.hasNext(); ) {
/* 207 */         DirectedEdge de = i.next();
/* 208 */         DirectedEdge sym = de.getSym();
/* 209 */         if (sym.isVisited())
/*     */           continue; 
/* 210 */         Node adjNode = sym.getNode();
/* 211 */         if (!nodesVisited.contains(adjNode)) {
/* 212 */           nodeQueue.addLast(adjNode);
/* 213 */           nodesVisited.add(adjNode);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeNodeDepth(Node n) {
/* 222 */     DirectedEdge startEdge = null;
/* 223 */     for (Iterator<DirectedEdge> iterator1 = ((DirectedEdgeStar)n.getEdges()).iterator(); iterator1.hasNext(); ) {
/* 224 */       DirectedEdge de = iterator1.next();
/* 225 */       if (de.isVisited() || de.getSym().isVisited()) {
/* 226 */         startEdge = de;
/*     */         break;
/*     */       } 
/*     */     } 
/* 234 */     if (startEdge == null)
/* 235 */       throw new TopologyException("unable to find edge to compute depths at " + n.getCoordinate()); 
/* 237 */     ((DirectedEdgeStar)n.getEdges()).computeDepths(startEdge);
/* 240 */     for (Iterator<DirectedEdge> i = ((DirectedEdgeStar)n.getEdges()).iterator(); i.hasNext(); ) {
/* 241 */       DirectedEdge de = i.next();
/* 242 */       de.setVisited(true);
/* 243 */       copySymDepths(de);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void copySymDepths(DirectedEdge de) {
/* 249 */     DirectedEdge sym = de.getSym();
/* 250 */     sym.setDepth(1, de.getDepth(2));
/* 251 */     sym.setDepth(2, de.getDepth(1));
/*     */   }
/*     */   
/*     */   public void findResultEdges() {
/* 264 */     for (Iterator<DirectedEdge> it = this.dirEdgeList.iterator(); it.hasNext(); ) {
/* 265 */       DirectedEdge de = it.next();
/* 274 */       if (de.getDepth(2) >= 1 && de.getDepth(1) <= 0 && !de.isInteriorAreaEdge())
/* 277 */         de.setInResult(true); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int compareTo(Object o) {
/* 295 */     BufferSubgraph graph = (BufferSubgraph)o;
/* 296 */     if (this.rightMostCoord.x < graph.rightMostCoord.x)
/* 297 */       return -1; 
/* 299 */     if (this.rightMostCoord.x > graph.rightMostCoord.x)
/* 300 */       return 1; 
/* 302 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\BufferSubgraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */