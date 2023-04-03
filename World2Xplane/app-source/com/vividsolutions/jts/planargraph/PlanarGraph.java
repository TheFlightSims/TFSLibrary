/*     */ package com.vividsolutions.jts.planargraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class PlanarGraph {
/*  55 */   protected Set edges = new HashSet();
/*     */   
/*  56 */   protected Set dirEdges = new HashSet();
/*     */   
/*  57 */   protected NodeMap nodeMap = new NodeMap();
/*     */   
/*     */   public Node findNode(Coordinate pt) {
/*  76 */     return this.nodeMap.find(pt);
/*     */   }
/*     */   
/*     */   protected void add(Node node) {
/*  87 */     this.nodeMap.add(node);
/*     */   }
/*     */   
/*     */   protected void add(Edge edge) {
/*  97 */     this.edges.add(edge);
/*  98 */     add(edge.getDirEdge(0));
/*  99 */     add(edge.getDirEdge(1));
/*     */   }
/*     */   
/*     */   protected void add(DirectedEdge dirEdge) {
/* 108 */     this.dirEdges.add(dirEdge);
/*     */   }
/*     */   
/*     */   public Iterator nodeIterator() {
/* 113 */     return this.nodeMap.iterator();
/*     */   }
/*     */   
/*     */   public boolean contains(Edge e) {
/* 126 */     return this.edges.contains(e);
/*     */   }
/*     */   
/*     */   public boolean contains(DirectedEdge de) {
/* 137 */     return this.dirEdges.contains(de);
/*     */   }
/*     */   
/*     */   public Collection getNodes() {
/* 140 */     return this.nodeMap.values();
/*     */   }
/*     */   
/*     */   public Iterator dirEdgeIterator() {
/* 149 */     return this.dirEdges.iterator();
/*     */   }
/*     */   
/*     */   public Iterator edgeIterator() {
/* 156 */     return this.edges.iterator();
/*     */   }
/*     */   
/*     */   public Collection getEdges() {
/* 162 */     return this.edges;
/*     */   }
/*     */   
/*     */   public void remove(Edge edge) {
/* 173 */     remove(edge.getDirEdge(0));
/* 174 */     remove(edge.getDirEdge(1));
/* 175 */     this.edges.remove(edge);
/* 176 */     edge.remove();
/*     */   }
/*     */   
/*     */   public void remove(DirectedEdge de) {
/* 186 */     DirectedEdge sym = de.getSym();
/* 187 */     if (sym != null)
/* 187 */       sym.setSym(null); 
/* 189 */     de.getFromNode().remove(de);
/* 190 */     de.remove();
/* 191 */     this.dirEdges.remove(de);
/*     */   }
/*     */   
/*     */   public void remove(Node node) {
/* 201 */     List outEdges = node.getOutEdges().getEdges();
/* 202 */     for (Iterator<DirectedEdge> i = outEdges.iterator(); i.hasNext(); ) {
/* 203 */       DirectedEdge de = i.next();
/* 204 */       DirectedEdge sym = de.getSym();
/* 206 */       if (sym != null)
/* 206 */         remove(sym); 
/* 208 */       this.dirEdges.remove(de);
/* 210 */       Edge edge = de.getEdge();
/* 211 */       if (edge != null)
/* 212 */         this.edges.remove(edge); 
/*     */     } 
/* 217 */     this.nodeMap.remove(node.getCoordinate());
/* 218 */     node.remove();
/*     */   }
/*     */   
/*     */   public List findNodesOfDegree(int degree) {
/* 226 */     List<Node> nodesFound = new ArrayList();
/* 227 */     for (Iterator<Node> i = nodeIterator(); i.hasNext(); ) {
/* 228 */       Node node = i.next();
/* 229 */       if (node.getDegree() == degree)
/* 230 */         nodesFound.add(node); 
/*     */     } 
/* 232 */     return nodesFound;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\planargraph\PlanarGraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */