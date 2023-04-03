/*     */ package com.vividsolutions.jts.planargraph;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geomgraph.Quadrant;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DirectedEdge extends GraphComponent implements Comparable {
/*     */   protected Edge parentEdge;
/*     */   
/*     */   protected Node from;
/*     */   
/*     */   protected Node to;
/*     */   
/*     */   protected Coordinate p0;
/*     */   
/*     */   protected Coordinate p1;
/*     */   
/*     */   public static List toEdges(Collection dirEdges) {
/*  61 */     List<Edge> edges = new ArrayList();
/*  62 */     for (Iterator i = dirEdges.iterator(); i.hasNext();)
/*  63 */       edges.add(((DirectedEdge)i.next()).parentEdge); 
/*  65 */     return edges;
/*     */   }
/*     */   
/*  72 */   protected DirectedEdge sym = null;
/*     */   
/*     */   protected boolean edgeDirection;
/*     */   
/*     */   protected int quadrant;
/*     */   
/*     */   protected double angle;
/*     */   
/*     */   public DirectedEdge(Node from, Node to, Coordinate directionPt, boolean edgeDirection) {
/*  91 */     this.from = from;
/*  92 */     this.to = to;
/*  93 */     this.edgeDirection = edgeDirection;
/*  94 */     this.p0 = from.getCoordinate();
/*  95 */     this.p1 = directionPt;
/*  96 */     double dx = this.p1.x - this.p0.x;
/*  97 */     double dy = this.p1.y - this.p0.y;
/*  98 */     this.quadrant = Quadrant.quadrant(dx, dy);
/*  99 */     this.angle = Math.atan2(dy, dx);
/*     */   }
/*     */   
/*     */   public Edge getEdge() {
/* 106 */     return this.parentEdge;
/*     */   }
/*     */   
/*     */   public void setEdge(Edge parentEdge) {
/* 111 */     this.parentEdge = parentEdge;
/*     */   }
/*     */   
/*     */   public int getQuadrant() {
/* 116 */     return this.quadrant;
/*     */   }
/*     */   
/*     */   public Coordinate getDirectionPt() {
/* 121 */     return this.p1;
/*     */   }
/*     */   
/*     */   public boolean getEdgeDirection() {
/* 126 */     return this.edgeDirection;
/*     */   }
/*     */   
/*     */   public Node getFromNode() {
/* 130 */     return this.from;
/*     */   }
/*     */   
/*     */   public Node getToNode() {
/* 134 */     return this.to;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/* 138 */     return this.from.getCoordinate();
/*     */   }
/*     */   
/*     */   public double getAngle() {
/* 143 */     return this.angle;
/*     */   }
/*     */   
/*     */   public DirectedEdge getSym() {
/* 148 */     return this.sym;
/*     */   }
/*     */   
/*     */   public void setSym(DirectedEdge sym) {
/* 153 */     this.sym = sym;
/*     */   }
/*     */   
/*     */   void remove() {
/* 159 */     this.sym = null;
/* 160 */     this.parentEdge = null;
/*     */   }
/*     */   
/*     */   public boolean isRemoved() {
/* 170 */     return (this.parentEdge == null);
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 190 */     DirectedEdge de = (DirectedEdge)obj;
/* 191 */     return compareDirection(de);
/*     */   }
/*     */   
/*     */   public int compareDirection(DirectedEdge e) {
/* 212 */     if (this.quadrant > e.quadrant)
/* 212 */       return 1; 
/* 213 */     if (this.quadrant < e.quadrant)
/* 213 */       return -1; 
/* 216 */     return CGAlgorithms.computeOrientation(e.p0, e.p1, this.p1);
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 224 */     String className = getClass().getName();
/* 225 */     int lastDotPos = className.lastIndexOf('.');
/* 226 */     String name = className.substring(lastDotPos + 1);
/* 227 */     out.print("  " + name + ": " + this.p0 + " - " + this.p1 + " " + this.quadrant + ":" + this.angle);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\planargraph\DirectedEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */