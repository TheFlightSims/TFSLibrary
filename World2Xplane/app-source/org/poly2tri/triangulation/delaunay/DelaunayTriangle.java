/*     */ package org.poly2tri.triangulation.delaunay;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.poly2tri.triangulation.TriangulationPoint;
/*     */ import org.poly2tri.triangulation.delaunay.sweep.DTSweepConstraint;
/*     */ import org.poly2tri.triangulation.point.TPoint;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class DelaunayTriangle {
/*  36 */   private static final Logger logger = LoggerFactory.getLogger(DelaunayTriangle.class);
/*     */   
/*  39 */   public final DelaunayTriangle[] neighbors = new DelaunayTriangle[3];
/*     */   
/*  41 */   public final boolean[] cEdge = new boolean[] { false, false, false };
/*     */   
/*  43 */   public final boolean[] dEdge = new boolean[] { false, false, false };
/*     */   
/*     */   protected boolean interior = false;
/*     */   
/*  47 */   public final TriangulationPoint[] points = new TriangulationPoint[3];
/*     */   
/*     */   public DelaunayTriangle(TriangulationPoint p1, TriangulationPoint p2, TriangulationPoint p3) {
/*  51 */     this.points[0] = p1;
/*  52 */     this.points[1] = p2;
/*  53 */     this.points[2] = p3;
/*     */   }
/*     */   
/*     */   public int index(TriangulationPoint p) {
/*  58 */     if (p == this.points[0])
/*  60 */       return 0; 
/*  62 */     if (p == this.points[1])
/*  64 */       return 1; 
/*  66 */     if (p == this.points[2])
/*  68 */       return 2; 
/*  70 */     throw new RuntimeException("Calling index with a point that doesn't exist in triangle");
/*     */   }
/*     */   
/*     */   public int indexCW(TriangulationPoint p) {
/*  75 */     int index = index(p);
/*  76 */     switch (index) {
/*     */       case 0:
/*  78 */         return 2;
/*     */       case 1:
/*  79 */         return 0;
/*     */     } 
/*  80 */     return 1;
/*     */   }
/*     */   
/*     */   public int indexCCW(TriangulationPoint p) {
/*  86 */     int index = index(p);
/*  87 */     switch (index) {
/*     */       case 0:
/*  89 */         return 1;
/*     */       case 1:
/*  90 */         return 2;
/*     */     } 
/*  91 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean contains(TriangulationPoint p) {
/*  97 */     return (p == this.points[0] || p == this.points[1] || p == this.points[2]);
/*     */   }
/*     */   
/*     */   public boolean contains(DTSweepConstraint e) {
/* 102 */     return (contains(e.p) && contains(e.q));
/*     */   }
/*     */   
/*     */   public boolean contains(TriangulationPoint p, TriangulationPoint q) {
/* 107 */     return (contains(p) && contains(q));
/*     */   }
/*     */   
/*     */   private void markNeighbor(TriangulationPoint p1, TriangulationPoint p2, DelaunayTriangle t) {
/* 115 */     if ((p1 == this.points[2] && p2 == this.points[1]) || (p1 == this.points[1] && p2 == this.points[2])) {
/* 117 */       this.neighbors[0] = t;
/* 119 */     } else if ((p1 == this.points[0] && p2 == this.points[2]) || (p1 == this.points[2] && p2 == this.points[0])) {
/* 121 */       this.neighbors[1] = t;
/* 123 */     } else if ((p1 == this.points[0] && p2 == this.points[1]) || (p1 == this.points[1] && p2 == this.points[0])) {
/* 125 */       this.neighbors[2] = t;
/*     */     } else {
/* 129 */       logger.error("Neighbor error, please report!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void markNeighbor(DelaunayTriangle t) {
/* 137 */     if (t.contains(this.points[1], this.points[2])) {
/* 139 */       this.neighbors[0] = t;
/* 140 */       t.markNeighbor(this.points[1], this.points[2], this);
/* 142 */     } else if (t.contains(this.points[0], this.points[2])) {
/* 144 */       this.neighbors[1] = t;
/* 145 */       t.markNeighbor(this.points[0], this.points[2], this);
/* 147 */     } else if (t.contains(this.points[0], this.points[1])) {
/* 149 */       this.neighbors[2] = t;
/* 150 */       t.markNeighbor(this.points[0], this.points[1], this);
/*     */     } else {
/* 154 */       logger.error("markNeighbor failed");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearNeighbors() {
/* 160 */     this.neighbors[2] = null;
/* 160 */     this.neighbors[1] = null;
/* 160 */     this.neighbors[0] = null;
/*     */   }
/*     */   
/*     */   public void clearNeighbor(DelaunayTriangle triangle) {
/* 165 */     if (this.neighbors[0] == triangle) {
/* 167 */       this.neighbors[0] = null;
/* 169 */     } else if (this.neighbors[1] == triangle) {
/* 171 */       this.neighbors[1] = null;
/*     */     } else {
/* 175 */       this.neighbors[2] = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 185 */     for (int i = 0; i < 3; i++) {
/* 187 */       DelaunayTriangle t = this.neighbors[i];
/* 188 */       if (t != null)
/* 190 */         t.clearNeighbor(this); 
/*     */     } 
/* 193 */     clearNeighbors();
/* 194 */     this.points[2] = null;
/* 194 */     this.points[1] = null;
/* 194 */     this.points[0] = null;
/*     */   }
/*     */   
/*     */   public TriangulationPoint oppositePoint(DelaunayTriangle t, TriangulationPoint p) {
/* 203 */     assert t != this : "self-pointer error";
/* 204 */     return pointCW(t.pointCW(p));
/*     */   }
/*     */   
/*     */   public DelaunayTriangle neighborCW(TriangulationPoint point) {
/* 210 */     if (point == this.points[0])
/* 212 */       return this.neighbors[1]; 
/* 214 */     if (point == this.points[1])
/* 216 */       return this.neighbors[2]; 
/* 218 */     return this.neighbors[0];
/*     */   }
/*     */   
/*     */   public DelaunayTriangle neighborCCW(TriangulationPoint point) {
/* 224 */     if (point == this.points[0])
/* 226 */       return this.neighbors[2]; 
/* 228 */     if (point == this.points[1])
/* 230 */       return this.neighbors[0]; 
/* 232 */     return this.neighbors[1];
/*     */   }
/*     */   
/*     */   public DelaunayTriangle neighborAcross(TriangulationPoint opoint) {
/* 238 */     if (opoint == this.points[0])
/* 240 */       return this.neighbors[0]; 
/* 242 */     if (opoint == this.points[1])
/* 244 */       return this.neighbors[1]; 
/* 246 */     return this.neighbors[2];
/*     */   }
/*     */   
/*     */   public TriangulationPoint pointCCW(TriangulationPoint point) {
/* 252 */     if (point == this.points[0])
/* 254 */       return this.points[1]; 
/* 256 */     if (point == this.points[1])
/* 258 */       return this.points[2]; 
/* 260 */     if (point == this.points[2])
/* 262 */       return this.points[0]; 
/* 264 */     logger.error("point location error");
/* 265 */     throw new RuntimeException("[FIXME] point location error");
/*     */   }
/*     */   
/*     */   public TriangulationPoint pointCW(TriangulationPoint point) {
/* 271 */     if (point == this.points[0])
/* 273 */       return this.points[2]; 
/* 275 */     if (point == this.points[1])
/* 277 */       return this.points[0]; 
/* 279 */     if (point == this.points[2])
/* 281 */       return this.points[1]; 
/* 283 */     logger.error("point location error");
/* 284 */     throw new RuntimeException("[FIXME] point location error");
/*     */   }
/*     */   
/*     */   public void legalize(TriangulationPoint oPoint, TriangulationPoint nPoint) {
/* 290 */     if (oPoint == this.points[0]) {
/* 292 */       this.points[1] = this.points[0];
/* 293 */       this.points[0] = this.points[2];
/* 294 */       this.points[2] = nPoint;
/* 296 */     } else if (oPoint == this.points[1]) {
/* 298 */       this.points[2] = this.points[1];
/* 299 */       this.points[1] = this.points[0];
/* 300 */       this.points[0] = nPoint;
/* 302 */     } else if (oPoint == this.points[2]) {
/* 304 */       this.points[0] = this.points[2];
/* 305 */       this.points[2] = this.points[1];
/* 306 */       this.points[1] = nPoint;
/*     */     } else {
/* 310 */       logger.error("legalization error");
/* 311 */       throw new RuntimeException("legalization bug");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printDebug() {
/* 317 */     System.out.println(this.points[0] + "," + this.points[1] + "," + this.points[2]);
/*     */   }
/*     */   
/*     */   public void markNeighborEdges() {
/* 323 */     for (int i = 0; i < 3; i++) {
/* 325 */       if (this.cEdge[i])
/* 327 */         switch (i) {
/*     */           case 0:
/* 330 */             if (this.neighbors[0] != null)
/* 331 */               this.neighbors[0].markConstrainedEdge(this.points[1], this.points[2]); 
/*     */             break;
/*     */           case 1:
/* 334 */             if (this.neighbors[1] != null)
/* 335 */               this.neighbors[1].markConstrainedEdge(this.points[0], this.points[2]); 
/*     */             break;
/*     */           case 2:
/* 338 */             if (this.neighbors[2] != null)
/* 339 */               this.neighbors[2].markConstrainedEdge(this.points[0], this.points[1]); 
/*     */             break;
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public void markEdge(DelaunayTriangle triangle) {
/* 348 */     for (int i = 0; i < 3; i++) {
/* 350 */       if (this.cEdge[i])
/* 352 */         switch (i) {
/*     */           case 0:
/* 355 */             triangle.markConstrainedEdge(this.points[1], this.points[2]);
/*     */             break;
/*     */           case 1:
/* 358 */             triangle.markConstrainedEdge(this.points[0], this.points[2]);
/*     */             break;
/*     */           case 2:
/* 361 */             triangle.markConstrainedEdge(this.points[0], this.points[1]);
/*     */             break;
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public void markEdge(ArrayList<DelaunayTriangle> tList) {
/* 371 */     for (DelaunayTriangle t : tList) {
/* 373 */       for (int i = 0; i < 3; i++) {
/* 375 */         if (t.cEdge[i])
/* 377 */           switch (i) {
/*     */             case 0:
/* 380 */               markConstrainedEdge(t.points[1], t.points[2]);
/*     */               break;
/*     */             case 1:
/* 383 */               markConstrainedEdge(t.points[0], t.points[2]);
/*     */               break;
/*     */             case 2:
/* 386 */               markConstrainedEdge(t.points[0], t.points[1]);
/*     */               break;
/*     */           }  
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void markConstrainedEdge(int index) {
/* 396 */     this.cEdge[index] = true;
/*     */   }
/*     */   
/*     */   public void markConstrainedEdge(DTSweepConstraint edge) {
/* 401 */     markConstrainedEdge(edge.p, edge.q);
/* 402 */     if ((edge.q == this.points[0] && edge.p == this.points[1]) || (edge.q == this.points[1] && edge.p == this.points[0])) {
/* 405 */       this.cEdge[2] = true;
/* 407 */     } else if ((edge.q == this.points[0] && edge.p == this.points[2]) || (edge.q == this.points[2] && edge.p == this.points[0])) {
/* 410 */       this.cEdge[1] = true;
/* 412 */     } else if ((edge.q == this.points[1] && edge.p == this.points[2]) || (edge.q == this.points[2] && edge.p == this.points[1])) {
/* 415 */       this.cEdge[0] = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void markConstrainedEdge(TriangulationPoint p, TriangulationPoint q) {
/* 422 */     if ((q == this.points[0] && p == this.points[1]) || (q == this.points[1] && p == this.points[0])) {
/* 424 */       this.cEdge[2] = true;
/* 426 */     } else if ((q == this.points[0] && p == this.points[2]) || (q == this.points[2] && p == this.points[0])) {
/* 428 */       this.cEdge[1] = true;
/* 430 */     } else if ((q == this.points[1] && p == this.points[2]) || (q == this.points[2] && p == this.points[1])) {
/* 432 */       this.cEdge[0] = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double area() {
/* 438 */     double a = (this.points[0].getX() - this.points[2].getX()) * (this.points[1].getY() - this.points[0].getY());
/* 439 */     double b = (this.points[0].getX() - this.points[1].getX()) * (this.points[2].getY() - this.points[0].getY());
/* 441 */     return 0.5D * FastMath.abs(a - b);
/*     */   }
/*     */   
/*     */   public TPoint centroid() {
/* 446 */     double cx = (this.points[0].getX() + this.points[1].getX() + this.points[2].getX()) / 3.0D;
/* 447 */     double cy = (this.points[0].getY() + this.points[1].getY() + this.points[2].getY()) / 3.0D;
/* 448 */     return new TPoint(cx, cy);
/*     */   }
/*     */   
/*     */   public int edgeIndex(TriangulationPoint p1, TriangulationPoint p2) {
/* 454 */     if (this.points[0] == p1) {
/* 456 */       if (this.points[1] == p2)
/* 458 */         return 2; 
/* 460 */       if (this.points[2] == p2)
/* 462 */         return 1; 
/* 465 */     } else if (this.points[1] == p1) {
/* 467 */       if (this.points[2] == p2)
/* 469 */         return 0; 
/* 471 */       if (this.points[0] == p2)
/* 473 */         return 2; 
/* 476 */     } else if (this.points[2] == p1) {
/* 478 */       if (this.points[0] == p2)
/* 480 */         return 1; 
/* 482 */       if (this.points[1] == p2)
/* 484 */         return 0; 
/*     */     } 
/* 487 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean getConstrainedEdgeCCW(TriangulationPoint p) {
/* 492 */     if (p == this.points[0])
/* 494 */       return this.cEdge[2]; 
/* 496 */     if (p == this.points[1])
/* 498 */       return this.cEdge[0]; 
/* 500 */     return this.cEdge[1];
/*     */   }
/*     */   
/*     */   public boolean getConstrainedEdgeCW(TriangulationPoint p) {
/* 505 */     if (p == this.points[0])
/* 507 */       return this.cEdge[1]; 
/* 509 */     if (p == this.points[1])
/* 511 */       return this.cEdge[2]; 
/* 513 */     return this.cEdge[0];
/*     */   }
/*     */   
/*     */   public boolean getConstrainedEdgeAcross(TriangulationPoint p) {
/* 518 */     if (p == this.points[0])
/* 520 */       return this.cEdge[0]; 
/* 522 */     if (p == this.points[1])
/* 524 */       return this.cEdge[1]; 
/* 526 */     return this.cEdge[2];
/*     */   }
/*     */   
/*     */   public void setConstrainedEdgeCCW(TriangulationPoint p, boolean ce) {
/* 531 */     if (p == this.points[0]) {
/* 533 */       this.cEdge[2] = ce;
/* 535 */     } else if (p == this.points[1]) {
/* 537 */       this.cEdge[0] = ce;
/*     */     } else {
/* 541 */       this.cEdge[1] = ce;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setConstrainedEdgeCW(TriangulationPoint p, boolean ce) {
/* 547 */     if (p == this.points[0]) {
/* 549 */       this.cEdge[1] = ce;
/* 551 */     } else if (p == this.points[1]) {
/* 553 */       this.cEdge[2] = ce;
/*     */     } else {
/* 557 */       this.cEdge[0] = ce;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setConstrainedEdgeAcross(TriangulationPoint p, boolean ce) {
/* 563 */     if (p == this.points[0]) {
/* 565 */       this.cEdge[0] = ce;
/* 567 */     } else if (p == this.points[1]) {
/* 569 */       this.cEdge[1] = ce;
/*     */     } else {
/* 573 */       this.cEdge[2] = ce;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getDelunayEdgeCCW(TriangulationPoint p) {
/* 579 */     if (p == this.points[0])
/* 581 */       return this.dEdge[2]; 
/* 583 */     if (p == this.points[1])
/* 585 */       return this.dEdge[0]; 
/* 587 */     return this.dEdge[1];
/*     */   }
/*     */   
/*     */   public boolean getDelunayEdgeCW(TriangulationPoint p) {
/* 592 */     if (p == this.points[0])
/* 594 */       return this.dEdge[1]; 
/* 596 */     if (p == this.points[1])
/* 598 */       return this.dEdge[2]; 
/* 600 */     return this.dEdge[0];
/*     */   }
/*     */   
/*     */   public boolean getDelunayEdgeAcross(TriangulationPoint p) {
/* 605 */     if (p == this.points[0])
/* 607 */       return this.dEdge[0]; 
/* 609 */     if (p == this.points[1])
/* 611 */       return this.dEdge[1]; 
/* 613 */     return this.dEdge[2];
/*     */   }
/*     */   
/*     */   public void setDelunayEdgeCCW(TriangulationPoint p, boolean e) {
/* 618 */     if (p == this.points[0]) {
/* 620 */       this.dEdge[2] = e;
/* 622 */     } else if (p == this.points[1]) {
/* 624 */       this.dEdge[0] = e;
/*     */     } else {
/* 628 */       this.dEdge[1] = e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDelunayEdgeCW(TriangulationPoint p, boolean e) {
/* 634 */     if (p == this.points[0]) {
/* 636 */       this.dEdge[1] = e;
/* 638 */     } else if (p == this.points[1]) {
/* 640 */       this.dEdge[2] = e;
/*     */     } else {
/* 644 */       this.dEdge[0] = e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDelunayEdgeAcross(TriangulationPoint p, boolean e) {
/* 650 */     if (p == this.points[0]) {
/* 652 */       this.dEdge[0] = e;
/* 654 */     } else if (p == this.points[1]) {
/* 656 */       this.dEdge[1] = e;
/*     */     } else {
/* 660 */       this.dEdge[2] = e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearDelunayEdges() {
/* 666 */     this.dEdge[0] = false;
/* 667 */     this.dEdge[1] = false;
/* 668 */     this.dEdge[2] = false;
/*     */   }
/*     */   
/*     */   public boolean isInterior() {
/* 673 */     return this.interior;
/*     */   }
/*     */   
/*     */   public void isInterior(boolean b) {
/* 678 */     this.interior = b;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\delaunay\DelaunayTriangle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */