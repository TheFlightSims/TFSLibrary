/*     */ package com.vividsolutions.jts.triangulate;
/*     */ 
/*     */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdge;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.QuadEdgeSubdivision;
/*     */ import com.vividsolutions.jts.triangulate.quadedge.Vertex;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class IncrementalDelaunayTriangulator {
/*     */   private QuadEdgeSubdivision subdiv;
/*     */   
/*     */   private boolean isUsingTolerance = false;
/*     */   
/*     */   public IncrementalDelaunayTriangulator(QuadEdgeSubdivision subdiv) {
/*  64 */     this.subdiv = subdiv;
/*  65 */     this.isUsingTolerance = (subdiv.getTolerance() > 0.0D);
/*     */   }
/*     */   
/*     */   public void insertSites(Collection vertices) {
/*  80 */     for (Iterator<Vertex> i = vertices.iterator(); i.hasNext(); ) {
/*  81 */       Vertex v = i.next();
/*  82 */       insertSite(v);
/*     */     } 
/*     */   }
/*     */   
/*     */   public QuadEdge insertSite(Vertex v) {
/* 103 */     QuadEdge e = this.subdiv.locate(v);
/* 105 */     if (this.subdiv.isVertexOfEdge(e, v))
/* 107 */       return e; 
/* 109 */     if (this.subdiv.isOnEdge(e, v.getCoordinate())) {
/* 112 */       e = e.oPrev();
/* 113 */       this.subdiv.delete(e.oNext());
/*     */     } 
/* 120 */     QuadEdge base = this.subdiv.makeEdge(e.orig(), v);
/* 121 */     QuadEdge.splice(base, e);
/* 122 */     QuadEdge startEdge = base;
/*     */     do {
/* 124 */       base = this.subdiv.connect(e, base.sym());
/* 125 */       e = base.oPrev();
/* 126 */     } while (e.lNext() != startEdge);
/*     */     while (true) {
/* 131 */       QuadEdge t = e.oPrev();
/* 132 */       if (t.dest().rightOf(e) && v.isInCircle(e.orig(), t.dest(), e.dest())) {
/* 133 */         QuadEdge.swap(e);
/* 134 */         e = e.oPrev();
/*     */         continue;
/*     */       } 
/* 135 */       if (e.oNext() == startEdge)
/* 136 */         return base; 
/* 138 */       e = e.oNext().lPrev();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\IncrementalDelaunayTriangulator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */