/*     */ package com.vividsolutions.jts.geomgraph.index;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ 
/*     */ public class MonotoneChainEdge {
/*     */   Edge e;
/*     */   
/*     */   Coordinate[] pts;
/*     */   
/*     */   int[] startIndex;
/*     */   
/*  70 */   Envelope env1 = new Envelope();
/*     */   
/*  71 */   Envelope env2 = new Envelope();
/*     */   
/*     */   public MonotoneChainEdge(Edge e) {
/*  74 */     this.e = e;
/*  75 */     this.pts = e.getCoordinates();
/*  76 */     MonotoneChainIndexer mcb = new MonotoneChainIndexer();
/*  77 */     this.startIndex = mcb.getChainStartIndices(this.pts);
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/*  80 */     return this.pts;
/*     */   }
/*     */   
/*     */   public int[] getStartIndexes() {
/*  81 */     return this.startIndex;
/*     */   }
/*     */   
/*     */   public double getMinX(int chainIndex) {
/*  85 */     double x1 = (this.pts[this.startIndex[chainIndex]]).x;
/*  86 */     double x2 = (this.pts[this.startIndex[chainIndex + 1]]).x;
/*  87 */     return (x1 < x2) ? x1 : x2;
/*     */   }
/*     */   
/*     */   public double getMaxX(int chainIndex) {
/*  91 */     double x1 = (this.pts[this.startIndex[chainIndex]]).x;
/*  92 */     double x2 = (this.pts[this.startIndex[chainIndex + 1]]).x;
/*  93 */     return (x1 > x2) ? x1 : x2;
/*     */   }
/*     */   
/*     */   public void computeIntersects(MonotoneChainEdge mce, SegmentIntersector si) {
/*  98 */     for (int i = 0; i < this.startIndex.length - 1; i++) {
/*  99 */       for (int j = 0; j < mce.startIndex.length - 1; j++)
/* 100 */         computeIntersectsForChain(i, mce, j, si); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void computeIntersectsForChain(int chainIndex0, MonotoneChainEdge mce, int chainIndex1, SegmentIntersector si) {
/* 112 */     computeIntersectsForChain(this.startIndex[chainIndex0], this.startIndex[chainIndex0 + 1], mce, mce.startIndex[chainIndex1], mce.startIndex[chainIndex1 + 1], si);
/*     */   }
/*     */   
/*     */   private void computeIntersectsForChain(int start0, int end0, MonotoneChainEdge mce, int start1, int end1, SegmentIntersector ei) {
/* 124 */     Coordinate p00 = this.pts[start0];
/* 125 */     Coordinate p01 = this.pts[end0];
/* 126 */     Coordinate p10 = mce.pts[start1];
/* 127 */     Coordinate p11 = mce.pts[end1];
/* 130 */     if (end0 - start0 == 1 && end1 - start1 == 1) {
/* 131 */       ei.addIntersections(this.e, start0, mce.e, start1);
/*     */       return;
/*     */     } 
/* 135 */     this.env1.init(p00, p01);
/* 136 */     this.env2.init(p10, p11);
/* 137 */     if (!this.env1.intersects(this.env2))
/*     */       return; 
/* 140 */     int mid0 = (start0 + end0) / 2;
/* 141 */     int mid1 = (start1 + end1) / 2;
/* 145 */     if (start0 < mid0) {
/* 146 */       if (start1 < mid1)
/* 146 */         computeIntersectsForChain(start0, mid0, mce, start1, mid1, ei); 
/* 147 */       if (mid1 < end1)
/* 147 */         computeIntersectsForChain(start0, mid0, mce, mid1, end1, ei); 
/*     */     } 
/* 149 */     if (mid0 < end0) {
/* 150 */       if (start1 < mid1)
/* 150 */         computeIntersectsForChain(mid0, end0, mce, start1, mid1, ei); 
/* 151 */       if (mid1 < end1)
/* 151 */         computeIntersectsForChain(mid0, end0, mce, mid1, end1, ei); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\index\MonotoneChainEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */