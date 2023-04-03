/*     */ package com.vividsolutions.jts.edgegraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class EdgeGraph {
/*  27 */   private Map vertexMap = new HashMap<>();
/*     */   
/*     */   protected HalfEdge createEdge(Coordinate orig) {
/*  41 */     return new HalfEdge(orig);
/*     */   }
/*     */   
/*     */   private HalfEdge create(Coordinate p0, Coordinate p1) {
/*  46 */     HalfEdge e0 = createEdge(p0);
/*  47 */     HalfEdge e1 = createEdge(p1);
/*  48 */     HalfEdge.init(e0, e1);
/*  49 */     return e0;
/*     */   }
/*     */   
/*     */   public HalfEdge addEdge(Coordinate orig, Coordinate dest) {
/*  61 */     int cmp = dest.compareTo(orig);
/*  63 */     if (cmp == 0)
/*  63 */       return null; 
/*  70 */     HalfEdge eAdj = (HalfEdge)this.vertexMap.get(orig);
/*  71 */     HalfEdge eSame = null;
/*  72 */     if (eAdj != null)
/*  73 */       eSame = eAdj.find(dest); 
/*  75 */     if (eSame != null)
/*  76 */       return eSame; 
/*  79 */     HalfEdge e = insert(orig, dest, eAdj);
/*  80 */     return e;
/*     */   }
/*     */   
/*     */   private HalfEdge insert(Coordinate orig, Coordinate dest, HalfEdge eAdj) {
/*  93 */     HalfEdge e = create(orig, dest);
/*  94 */     if (eAdj != null) {
/*  95 */       eAdj.insert(e);
/*     */     } else {
/*  99 */       this.vertexMap.put(orig, e);
/*     */     } 
/* 102 */     HalfEdge eAdjDest = (HalfEdge)this.vertexMap.get(dest);
/* 103 */     if (eAdjDest != null) {
/* 104 */       eAdjDest.insert(e.sym());
/*     */     } else {
/* 107 */       this.vertexMap.put(dest, e.sym());
/*     */     } 
/* 109 */     return e;
/*     */   }
/*     */   
/*     */   public Collection getVertexEdges() {
/* 114 */     return this.vertexMap.values();
/*     */   }
/*     */   
/*     */   public HalfEdge findEdge(Coordinate orig, Coordinate dest) {
/* 126 */     HalfEdge e = (HalfEdge)this.vertexMap.get(orig);
/* 127 */     if (e == null)
/* 127 */       return null; 
/* 128 */     return e.find(dest);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\edgegraph\EdgeGraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */