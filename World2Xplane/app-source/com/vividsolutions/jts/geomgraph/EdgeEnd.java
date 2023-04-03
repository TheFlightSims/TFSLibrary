/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.BoundaryNodeRule;
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class EdgeEnd implements Comparable {
/*     */   protected Edge edge;
/*     */   
/*     */   protected Label label;
/*     */   
/*     */   private Node node;
/*     */   
/*     */   private Coordinate p0;
/*     */   
/*     */   private Coordinate p1;
/*     */   
/*     */   private double dx;
/*     */   
/*     */   private double dy;
/*     */   
/*     */   private int quadrant;
/*     */   
/*     */   protected EdgeEnd(Edge edge) {
/*  68 */     this.edge = edge;
/*     */   }
/*     */   
/*     */   public EdgeEnd(Edge edge, Coordinate p0, Coordinate p1) {
/*  71 */     this(edge, p0, p1, null);
/*     */   }
/*     */   
/*     */   public EdgeEnd(Edge edge, Coordinate p0, Coordinate p1, Label label) {
/*  74 */     this(edge);
/*  75 */     init(p0, p1);
/*  76 */     this.label = label;
/*     */   }
/*     */   
/*     */   protected void init(Coordinate p0, Coordinate p1) {
/*  81 */     this.p0 = p0;
/*  82 */     this.p1 = p1;
/*  83 */     this.dx = p1.x - p0.x;
/*  84 */     this.dy = p1.y - p0.y;
/*  85 */     this.quadrant = Quadrant.quadrant(this.dx, this.dy);
/*  86 */     Assert.isTrue((this.dx != 0.0D || this.dy != 0.0D), "EdgeEnd with identical endpoints found");
/*     */   }
/*     */   
/*     */   public Edge getEdge() {
/*  89 */     return this.edge;
/*     */   }
/*     */   
/*     */   public Label getLabel() {
/*  90 */     return this.label;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  91 */     return this.p0;
/*     */   }
/*     */   
/*     */   public Coordinate getDirectedCoordinate() {
/*  92 */     return this.p1;
/*     */   }
/*     */   
/*     */   public int getQuadrant() {
/*  93 */     return this.quadrant;
/*     */   }
/*     */   
/*     */   public double getDx() {
/*  94 */     return this.dx;
/*     */   }
/*     */   
/*     */   public double getDy() {
/*  95 */     return this.dy;
/*     */   }
/*     */   
/*     */   public void setNode(Node node) {
/*  97 */     this.node = node;
/*     */   }
/*     */   
/*     */   public Node getNode() {
/*  98 */     return this.node;
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 102 */     EdgeEnd e = (EdgeEnd)obj;
/* 103 */     return compareDirection(e);
/*     */   }
/*     */   
/*     */   public int compareDirection(EdgeEnd e) {
/* 120 */     if (this.dx == e.dx && this.dy == e.dy)
/* 121 */       return 0; 
/* 123 */     if (this.quadrant > e.quadrant)
/* 123 */       return 1; 
/* 124 */     if (this.quadrant < e.quadrant)
/* 124 */       return -1; 
/* 127 */     return CGAlgorithms.computeOrientation(e.p0, e.p1, this.p1);
/*     */   }
/*     */   
/*     */   public void computeLabel(BoundaryNodeRule boundaryNodeRule) {}
/*     */   
/*     */   public void print(PrintStream out) {
/* 136 */     double angle = Math.atan2(this.dy, this.dx);
/* 137 */     String className = getClass().getName();
/* 138 */     int lastDotPos = className.lastIndexOf('.');
/* 139 */     String name = className.substring(lastDotPos + 1);
/* 140 */     out.print("  " + name + ": " + this.p0 + " - " + this.p1 + " " + this.quadrant + ":" + angle + "   " + this.label);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 144 */     double angle = Math.atan2(this.dy, this.dx);
/* 145 */     String className = getClass().getName();
/* 146 */     int lastDotPos = className.lastIndexOf('.');
/* 147 */     String name = className.substring(lastDotPos + 1);
/* 148 */     return "  " + name + ": " + this.p0 + " - " + this.p1 + " " + this.quadrant + ":" + angle + "   " + this.label;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\EdgeEnd.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */