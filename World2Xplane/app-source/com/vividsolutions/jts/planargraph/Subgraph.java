/*     */ package com.vividsolutions.jts.planargraph;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Subgraph {
/*     */   protected PlanarGraph parentGraph;
/*     */   
/*  50 */   protected Set edges = new HashSet();
/*     */   
/*  51 */   protected List dirEdges = new ArrayList();
/*     */   
/*  52 */   protected NodeMap nodeMap = new NodeMap();
/*     */   
/*     */   public Subgraph(PlanarGraph parentGraph) {
/*  60 */     this.parentGraph = parentGraph;
/*     */   }
/*     */   
/*     */   public PlanarGraph getParent() {
/*  71 */     return this.parentGraph;
/*     */   }
/*     */   
/*     */   public void add(Edge e) {
/*  82 */     if (this.edges.contains(e))
/*     */       return; 
/*  84 */     this.edges.add(e);
/*  85 */     this.dirEdges.add(e.getDirEdge(0));
/*  86 */     this.dirEdges.add(e.getDirEdge(1));
/*  87 */     this.nodeMap.add(e.getDirEdge(0).getFromNode());
/*  88 */     this.nodeMap.add(e.getDirEdge(1).getFromNode());
/*     */   }
/*     */   
/*     */   public Iterator dirEdgeIterator() {
/*  99 */     return this.dirEdges.iterator();
/*     */   }
/*     */   
/*     */   public Iterator edgeIterator() {
/* 109 */     return this.edges.iterator();
/*     */   }
/*     */   
/*     */   public Iterator nodeIterator() {
/* 115 */     return this.nodeMap.iterator();
/*     */   }
/*     */   
/*     */   public boolean contains(Edge e) {
/* 122 */     return this.edges.contains(e);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\planargraph\Subgraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */