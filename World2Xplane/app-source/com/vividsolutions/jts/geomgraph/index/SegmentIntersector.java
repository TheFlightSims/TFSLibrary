/*     */ package com.vividsolutions.jts.geomgraph.index;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class SegmentIntersector {
/*     */   public static boolean isAdjacentSegments(int i1, int i2) {
/*  56 */     return (Math.abs(i1 - i2) == 1);
/*     */   }
/*     */   
/*     */   private boolean hasIntersection = false;
/*     */   
/*     */   private boolean hasProper = false;
/*     */   
/*     */   private boolean hasProperInterior = false;
/*     */   
/*  67 */   private Coordinate properIntersectionPoint = null;
/*     */   
/*     */   private LineIntersector li;
/*     */   
/*     */   private boolean includeProper;
/*     */   
/*     */   private boolean recordIsolated;
/*     */   
/*     */   private boolean isSelfIntersection;
/*     */   
/*  74 */   private int numIntersections = 0;
/*     */   
/*  77 */   public int numTests = 0;
/*     */   
/*     */   private Collection[] bdyNodes;
/*     */   
/*     */   public SegmentIntersector(LineIntersector li, boolean includeProper, boolean recordIsolated) {
/*  87 */     this.li = li;
/*  88 */     this.includeProper = includeProper;
/*  89 */     this.recordIsolated = recordIsolated;
/*     */   }
/*     */   
/*     */   public void setBoundaryNodes(Collection bdyNodes0, Collection bdyNodes1) {
/*  95 */     this.bdyNodes = new Collection[2];
/*  96 */     this.bdyNodes[0] = bdyNodes0;
/*  97 */     this.bdyNodes[1] = bdyNodes1;
/*     */   }
/*     */   
/*     */   public Coordinate getProperIntersectionPoint() {
/* 103 */     return this.properIntersectionPoint;
/*     */   }
/*     */   
/*     */   public boolean hasIntersection() {
/* 105 */     return this.hasIntersection;
/*     */   }
/*     */   
/*     */   public boolean hasProperIntersection() {
/* 113 */     return this.hasProper;
/*     */   }
/*     */   
/*     */   public boolean hasProperInteriorIntersection() {
/* 118 */     return this.hasProperInterior;
/*     */   }
/*     */   
/*     */   private boolean isTrivialIntersection(Edge e0, int segIndex0, Edge e1, int segIndex1) {
/* 129 */     if (e0 == e1 && 
/* 130 */       this.li.getIntersectionNum() == 1) {
/* 131 */       if (isAdjacentSegments(segIndex0, segIndex1))
/* 132 */         return true; 
/* 133 */       if (e0.isClosed()) {
/* 134 */         int maxSegIndex = e0.getNumPoints() - 1;
/* 135 */         if ((segIndex0 == 0 && segIndex1 == maxSegIndex) || (segIndex1 == 0 && segIndex0 == maxSegIndex))
/* 137 */           return true; 
/*     */       } 
/*     */     } 
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   public void addIntersections(Edge e0, int segIndex0, Edge e1, int segIndex1) {
/* 156 */     if (e0 == e1 && segIndex0 == segIndex1)
/*     */       return; 
/* 157 */     this.numTests++;
/* 158 */     Coordinate p00 = e0.getCoordinates()[segIndex0];
/* 159 */     Coordinate p01 = e0.getCoordinates()[segIndex0 + 1];
/* 160 */     Coordinate p10 = e1.getCoordinates()[segIndex1];
/* 161 */     Coordinate p11 = e1.getCoordinates()[segIndex1 + 1];
/* 163 */     this.li.computeIntersection(p00, p01, p10, p11);
/* 169 */     if (this.li.hasIntersection()) {
/* 170 */       if (this.recordIsolated) {
/* 171 */         e0.setIsolated(false);
/* 172 */         e1.setIsolated(false);
/*     */       } 
/* 175 */       this.numIntersections++;
/* 179 */       if (!isTrivialIntersection(e0, segIndex0, e1, segIndex1)) {
/* 180 */         this.hasIntersection = true;
/* 181 */         if (this.includeProper || !this.li.isProper()) {
/* 183 */           e0.addIntersections(this.li, segIndex0, 0);
/* 184 */           e1.addIntersections(this.li, segIndex1, 1);
/*     */         } 
/* 186 */         if (this.li.isProper()) {
/* 187 */           this.properIntersectionPoint = (Coordinate)this.li.getIntersection(0).clone();
/* 188 */           this.hasProper = true;
/* 189 */           if (!isBoundaryPoint(this.li, this.bdyNodes))
/* 190 */             this.hasProperInterior = true; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isBoundaryPoint(LineIntersector li, Collection[] bdyNodes) {
/* 200 */     if (bdyNodes == null)
/* 200 */       return false; 
/* 201 */     if (isBoundaryPoint(li, bdyNodes[0]))
/* 201 */       return true; 
/* 202 */     if (isBoundaryPoint(li, bdyNodes[1]))
/* 202 */       return true; 
/* 203 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isBoundaryPoint(LineIntersector li, Collection bdyNodes) {
/* 208 */     for (Iterator<Node> i = bdyNodes.iterator(); i.hasNext(); ) {
/* 209 */       Node node = i.next();
/* 210 */       Coordinate pt = node.getCoordinate();
/* 211 */       if (li.isIntersection(pt))
/* 211 */         return true; 
/*     */     } 
/* 213 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\index\SegmentIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */