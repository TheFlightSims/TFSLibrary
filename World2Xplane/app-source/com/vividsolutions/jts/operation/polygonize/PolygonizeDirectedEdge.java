/*     */ package com.vividsolutions.jts.operation.polygonize;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.planargraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.planargraph.Node;
/*     */ 
/*     */ class PolygonizeDirectedEdge extends DirectedEdge {
/*  51 */   private EdgeRing edgeRing = null;
/*     */   
/*  52 */   private PolygonizeDirectedEdge next = null;
/*     */   
/*  53 */   private long label = -1L;
/*     */   
/*     */   public PolygonizeDirectedEdge(Node from, Node to, Coordinate directionPt, boolean edgeDirection) {
/*  69 */     super(from, to, directionPt, edgeDirection);
/*     */   }
/*     */   
/*     */   public long getLabel() {
/*  75 */     return this.label;
/*     */   }
/*     */   
/*     */   public void setLabel(long label) {
/*  79 */     this.label = label;
/*     */   }
/*     */   
/*     */   public PolygonizeDirectedEdge getNext() {
/*  84 */     return this.next;
/*     */   }
/*     */   
/*     */   public void setNext(PolygonizeDirectedEdge next) {
/*  89 */     this.next = next;
/*     */   }
/*     */   
/*     */   public boolean isInRing() {
/*  95 */     return (this.edgeRing != null);
/*     */   }
/*     */   
/*     */   public void setRing(EdgeRing edgeRing) {
/* 102 */     this.edgeRing = edgeRing;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\polygonize\PolygonizeDirectedEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */