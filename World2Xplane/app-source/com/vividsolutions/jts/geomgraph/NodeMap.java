/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class NodeMap {
/*  53 */   Map nodeMap = new TreeMap<>();
/*     */   
/*     */   NodeFactory nodeFact;
/*     */   
/*     */   public NodeMap(NodeFactory nodeFact) {
/*  57 */     this.nodeFact = nodeFact;
/*     */   }
/*     */   
/*     */   public Node addNode(Coordinate coord) {
/*  74 */     Node node = (Node)this.nodeMap.get(coord);
/*  75 */     if (node == null) {
/*  76 */       node = this.nodeFact.createNode(coord);
/*  77 */       this.nodeMap.put(coord, node);
/*     */     } 
/*  79 */     return node;
/*     */   }
/*     */   
/*     */   public Node addNode(Node n) {
/*  84 */     Node node = (Node)this.nodeMap.get(n.getCoordinate());
/*  85 */     if (node == null) {
/*  86 */       this.nodeMap.put(n.getCoordinate(), n);
/*  87 */       return n;
/*     */     } 
/*  89 */     node.mergeLabel(n);
/*  90 */     return node;
/*     */   }
/*     */   
/*     */   public void add(EdgeEnd e) {
/* 100 */     Coordinate p = e.getCoordinate();
/* 101 */     Node n = addNode(p);
/* 102 */     n.add(e);
/*     */   }
/*     */   
/*     */   public Node find(Coordinate coord) {
/* 107 */     return (Node)this.nodeMap.get(coord);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 111 */     return this.nodeMap.values().iterator();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 115 */     return this.nodeMap.values();
/*     */   }
/*     */   
/*     */   public Collection getBoundaryNodes(int geomIndex) {
/* 120 */     Collection<Node> bdyNodes = new ArrayList();
/* 121 */     for (Iterator<Node> i = iterator(); i.hasNext(); ) {
/* 122 */       Node node = i.next();
/* 123 */       if (node.getLabel().getLocation(geomIndex) == 1)
/* 124 */         bdyNodes.add(node); 
/*     */     } 
/* 126 */     return bdyNodes;
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 131 */     for (Iterator<Node> it = iterator(); it.hasNext(); ) {
/* 133 */       Node n = it.next();
/* 134 */       n.print(out);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\NodeMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */