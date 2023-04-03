/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.IntersectionMatrix;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Node extends GraphComponent {
/*     */   protected Coordinate coord;
/*     */   
/*     */   protected EdgeEndStar edges;
/*     */   
/*     */   public Node(Coordinate coord, EdgeEndStar edges) {
/*  59 */     this.coord = coord;
/*  60 */     this.edges = edges;
/*  61 */     this.label = new Label(0, -1);
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  64 */     return this.coord;
/*     */   }
/*     */   
/*     */   public EdgeEndStar getEdges() {
/*  65 */     return this.edges;
/*     */   }
/*     */   
/*     */   public boolean isIncidentEdgeInResult() {
/*  77 */     for (Iterator<DirectedEdge> it = getEdges().getEdges().iterator(); it.hasNext(); ) {
/*  78 */       DirectedEdge de = it.next();
/*  79 */       if (de.getEdge().isInResult())
/*  80 */         return true; 
/*     */     } 
/*  82 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isIsolated() {
/*  87 */     return (this.label.getGeometryCount() == 1);
/*     */   }
/*     */   
/*     */   protected void computeIM(IntersectionMatrix im) {}
/*     */   
/*     */   public void add(EdgeEnd e) {
/*  99 */     this.edges.insert(e);
/* 100 */     e.setNode(this);
/*     */   }
/*     */   
/*     */   public void mergeLabel(Node n) {
/* 105 */     mergeLabel(n.label);
/*     */   }
/*     */   
/*     */   public void mergeLabel(Label label2) {
/* 117 */     for (int i = 0; i < 2; i++) {
/* 118 */       int loc = computeMergedLocation(label2, i);
/* 119 */       int thisLoc = this.label.getLocation(i);
/* 120 */       if (thisLoc == -1)
/* 120 */         this.label.setLocation(i, loc); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLabel(int argIndex, int onLocation) {
/* 126 */     if (this.label == null) {
/* 127 */       this.label = new Label(argIndex, onLocation);
/*     */     } else {
/* 130 */       this.label.setLocation(argIndex, onLocation);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLabelBoundary(int argIndex) {
/*     */     int newLoc;
/* 139 */     if (this.label == null)
/*     */       return; 
/* 142 */     int loc = -1;
/* 143 */     if (this.label != null)
/* 144 */       loc = this.label.getLocation(argIndex); 
/* 147 */     switch (loc) {
/*     */       case 1:
/* 148 */         newLoc = 0;
/*     */         break;
/*     */       case 0:
/* 149 */         newLoc = 1;
/*     */         break;
/*     */       default:
/* 150 */         newLoc = 1;
/*     */         break;
/*     */     } 
/* 152 */     this.label.setLocation(argIndex, newLoc);
/*     */   }
/*     */   
/*     */   int computeMergedLocation(Label label2, int eltIndex) {
/* 164 */     int loc = -1;
/* 165 */     loc = this.label.getLocation(eltIndex);
/* 166 */     if (!label2.isNull(eltIndex)) {
/* 167 */       int nLoc = label2.getLocation(eltIndex);
/* 168 */       if (loc != 1)
/* 168 */         loc = nLoc; 
/*     */     } 
/* 170 */     return loc;
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 175 */     out.println("node " + this.coord + " lbl: " + this.label);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\Node.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */