/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.noding.OrientedCoordinateArray;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class EdgeList {
/*  54 */   private List edges = new ArrayList();
/*     */   
/*  59 */   private Map ocaMap = new TreeMap<>();
/*     */   
/*     */   public void add(Edge e) {
/*  69 */     this.edges.add(e);
/*  70 */     OrientedCoordinateArray oca = new OrientedCoordinateArray(e.getCoordinates());
/*  71 */     this.ocaMap.put(oca, e);
/*     */   }
/*     */   
/*     */   public void addAll(Collection edgeColl) {
/*  76 */     for (Iterator<Edge> i = edgeColl.iterator(); i.hasNext();)
/*  77 */       add(i.next()); 
/*     */   }
/*     */   
/*     */   public List getEdges() {
/*  81 */     return this.edges;
/*     */   }
/*     */   
/*     */   public Edge findEqualEdge(Edge e) {
/*  91 */     OrientedCoordinateArray oca = new OrientedCoordinateArray(e.getCoordinates());
/*  93 */     Edge matchEdge = (Edge)this.ocaMap.get(oca);
/*  94 */     return matchEdge;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  97 */     return this.edges.iterator();
/*     */   }
/*     */   
/*     */   public Edge get(int i) {
/*  99 */     return this.edges.get(i);
/*     */   }
/*     */   
/*     */   public int findEdgeIndex(Edge e) {
/* 108 */     for (int i = 0; i < this.edges.size(); i++) {
/* 109 */       if (((Edge)this.edges.get(i)).equals(e))
/* 109 */         return i; 
/*     */     } 
/* 111 */     return -1;
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 116 */     out.print("MULTILINESTRING ( ");
/* 117 */     for (int j = 0; j < this.edges.size(); j++) {
/* 118 */       Edge e = this.edges.get(j);
/* 119 */       if (j > 0)
/* 119 */         out.print(","); 
/* 120 */       out.print("(");
/* 121 */       Coordinate[] pts = e.getCoordinates();
/* 122 */       for (int i = 0; i < pts.length; i++) {
/* 123 */         if (i > 0)
/* 123 */           out.print(","); 
/* 124 */         out.print((pts[i]).x + " " + (pts[i]).y);
/*     */       } 
/* 126 */       out.println(")");
/*     */     } 
/* 128 */     out.print(")  ");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\EdgeList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */