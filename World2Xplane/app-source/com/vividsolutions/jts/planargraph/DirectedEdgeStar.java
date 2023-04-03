/*     */ package com.vividsolutions.jts.planargraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DirectedEdgeStar {
/*  53 */   protected List outEdges = new ArrayList();
/*     */   
/*     */   private boolean sorted = false;
/*     */   
/*     */   public void add(DirectedEdge de) {
/*  66 */     this.outEdges.add(de);
/*  67 */     this.sorted = false;
/*     */   }
/*     */   
/*     */   public void remove(DirectedEdge de) {
/*  74 */     this.outEdges.remove(de);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  81 */     sortEdges();
/*  82 */     return this.outEdges.iterator();
/*     */   }
/*     */   
/*     */   public int getDegree() {
/*  88 */     return this.outEdges.size();
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  95 */     Iterator<DirectedEdge> it = iterator();
/*  96 */     if (!it.hasNext())
/*  96 */       return null; 
/*  97 */     DirectedEdge e = it.next();
/*  98 */     return e.getCoordinate();
/*     */   }
/*     */   
/*     */   public List getEdges() {
/* 106 */     sortEdges();
/* 107 */     return this.outEdges;
/*     */   }
/*     */   
/*     */   private void sortEdges() {
/* 112 */     if (!this.sorted) {
/* 113 */       Collections.sort(this.outEdges);
/* 114 */       this.sorted = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getIndex(Edge edge) {
/* 123 */     sortEdges();
/* 124 */     for (int i = 0; i < this.outEdges.size(); i++) {
/* 125 */       DirectedEdge de = this.outEdges.get(i);
/* 126 */       if (de.getEdge() == edge)
/* 127 */         return i; 
/*     */     } 
/* 129 */     return -1;
/*     */   }
/*     */   
/*     */   public int getIndex(DirectedEdge dirEdge) {
/* 137 */     sortEdges();
/* 138 */     for (int i = 0; i < this.outEdges.size(); i++) {
/* 139 */       DirectedEdge de = this.outEdges.get(i);
/* 140 */       if (de == dirEdge)
/* 141 */         return i; 
/*     */     } 
/* 143 */     return -1;
/*     */   }
/*     */   
/*     */   public int getIndex(int i) {
/* 153 */     int modi = i % this.outEdges.size();
/* 155 */     if (modi < 0)
/* 155 */       modi += this.outEdges.size(); 
/* 156 */     return modi;
/*     */   }
/*     */   
/*     */   public DirectedEdge getNextEdge(DirectedEdge dirEdge) {
/* 166 */     int i = getIndex(dirEdge);
/* 167 */     return this.outEdges.get(getIndex(i + 1));
/*     */   }
/*     */   
/*     */   public DirectedEdge getNextCWEdge(DirectedEdge dirEdge) {
/* 177 */     int i = getIndex(dirEdge);
/* 178 */     return this.outEdges.get(getIndex(i - 1));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\planargraph\DirectedEdgeStar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */