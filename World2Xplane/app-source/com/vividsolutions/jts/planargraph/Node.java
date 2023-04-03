/*     */ package com.vividsolutions.jts.planargraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Node extends GraphComponent {
/*     */   protected Coordinate pt;
/*     */   
/*     */   protected DirectedEdgeStar deStar;
/*     */   
/*     */   public static Collection getEdgesBetween(Node node0, Node node1) {
/*  58 */     List<?> edges0 = DirectedEdge.toEdges(node0.getOutEdges().getEdges());
/*  59 */     Set commonEdges = new HashSet(edges0);
/*  60 */     List<?> edges1 = DirectedEdge.toEdges(node1.getOutEdges().getEdges());
/*  61 */     commonEdges.retainAll(edges1);
/*  62 */     return commonEdges;
/*     */   }
/*     */   
/*     */   public Node(Coordinate pt) {
/*  76 */     this(pt, new DirectedEdgeStar());
/*     */   }
/*     */   
/*     */   public Node(Coordinate pt, DirectedEdgeStar deStar) {
/*  84 */     this.pt = pt;
/*  85 */     this.deStar = deStar;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  91 */     return this.pt;
/*     */   }
/*     */   
/*     */   public void addOutEdge(DirectedEdge de) {
/*  98 */     this.deStar.add(de);
/*     */   }
/*     */   
/*     */   public DirectedEdgeStar getOutEdges() {
/* 104 */     return this.deStar;
/*     */   }
/*     */   
/*     */   public int getDegree() {
/* 108 */     return this.deStar.getDegree();
/*     */   }
/*     */   
/*     */   public int getIndex(Edge edge) {
/* 115 */     return this.deStar.getIndex(edge);
/*     */   }
/*     */   
/*     */   public void remove(DirectedEdge de) {
/* 124 */     this.deStar.remove(de);
/*     */   }
/*     */   
/*     */   void remove() {
/* 131 */     this.pt = null;
/*     */   }
/*     */   
/*     */   public boolean isRemoved() {
/* 142 */     return (this.pt == null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\planargraph\Node.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */