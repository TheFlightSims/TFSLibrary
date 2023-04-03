/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdge;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdgeStar;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ class RightmostEdgeFinder {
/*  56 */   private int minIndex = -1;
/*     */   
/*  57 */   private Coordinate minCoord = null;
/*     */   
/*  58 */   private DirectedEdge minDe = null;
/*     */   
/*  59 */   private DirectedEdge orientedDe = null;
/*     */   
/*     */   public DirectedEdge getEdge() {
/*  68 */     return this.orientedDe;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  69 */     return this.minCoord;
/*     */   }
/*     */   
/*     */   public void findEdge(List dirEdgeList) {
/*  77 */     for (Iterator<DirectedEdge> i = dirEdgeList.iterator(); i.hasNext(); ) {
/*  78 */       DirectedEdge de = i.next();
/*  79 */       if (!de.isForward())
/*     */         continue; 
/*  81 */       checkForRightmostCoordinate(de);
/*     */     } 
/*  88 */     Assert.isTrue((this.minIndex != 0 || this.minCoord.equals(this.minDe.getCoordinate())), "inconsistency in rightmost processing");
/*  89 */     if (this.minIndex == 0) {
/*  90 */       findRightmostEdgeAtNode();
/*     */     } else {
/*  93 */       findRightmostEdgeAtVertex();
/*     */     } 
/*  99 */     this.orientedDe = this.minDe;
/* 100 */     int rightmostSide = getRightmostSide(this.minDe, this.minIndex);
/* 101 */     if (rightmostSide == 1)
/* 102 */       this.orientedDe = this.minDe.getSym(); 
/*     */   }
/*     */   
/*     */   private void findRightmostEdgeAtNode() {
/* 107 */     Node node = this.minDe.getNode();
/* 108 */     DirectedEdgeStar star = (DirectedEdgeStar)node.getEdges();
/* 109 */     this.minDe = star.getRightmostEdge();
/* 112 */     if (!this.minDe.isForward()) {
/* 113 */       this.minDe = this.minDe.getSym();
/* 114 */       this.minIndex = (this.minDe.getEdge().getCoordinates()).length - 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void findRightmostEdgeAtVertex() {
/* 124 */     Coordinate[] pts = this.minDe.getEdge().getCoordinates();
/* 125 */     Assert.isTrue((this.minIndex > 0 && this.minIndex < pts.length), "rightmost point expected to be interior vertex of edge");
/* 126 */     Coordinate pPrev = pts[this.minIndex - 1];
/* 127 */     Coordinate pNext = pts[this.minIndex + 1];
/* 128 */     int orientation = CGAlgorithms.computeOrientation(this.minCoord, pNext, pPrev);
/* 129 */     boolean usePrev = false;
/* 131 */     if (pPrev.y < this.minCoord.y && pNext.y < this.minCoord.y && orientation == 1) {
/* 133 */       usePrev = true;
/* 135 */     } else if (pPrev.y > this.minCoord.y && pNext.y > this.minCoord.y && orientation == -1) {
/* 137 */       usePrev = true;
/*     */     } 
/* 141 */     if (usePrev)
/* 142 */       this.minIndex--; 
/*     */   }
/*     */   
/*     */   private void checkForRightmostCoordinate(DirectedEdge de) {
/* 147 */     Coordinate[] coord = de.getEdge().getCoordinates();
/* 148 */     for (int i = 0; i < coord.length - 1; i++) {
/* 151 */       if (this.minCoord == null || (coord[i]).x > this.minCoord.x) {
/* 152 */         this.minDe = de;
/* 153 */         this.minIndex = i;
/* 154 */         this.minCoord = coord[i];
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getRightmostSide(DirectedEdge de, int index) {
/* 162 */     int side = getRightmostSideOfSegment(de, index);
/* 163 */     if (side < 0)
/* 164 */       side = getRightmostSideOfSegment(de, index - 1); 
/* 165 */     if (side < 0) {
/* 169 */       this.minCoord = null;
/* 170 */       checkForRightmostCoordinate(de);
/*     */     } 
/* 172 */     return side;
/*     */   }
/*     */   
/*     */   private int getRightmostSideOfSegment(DirectedEdge de, int i) {
/* 177 */     Edge e = de.getEdge();
/* 178 */     Coordinate[] coord = e.getCoordinates();
/* 180 */     if (i < 0 || i + 1 >= coord.length)
/* 180 */       return -1; 
/* 181 */     if ((coord[i]).y == (coord[i + 1]).y)
/* 181 */       return -1; 
/* 183 */     int pos = 1;
/* 184 */     if ((coord[i]).y < (coord[i + 1]).y)
/* 184 */       pos = 2; 
/* 185 */     return pos;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\RightmostEdgeFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */