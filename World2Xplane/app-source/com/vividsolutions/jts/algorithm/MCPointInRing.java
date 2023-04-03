/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.index.bintree.Bintree;
/*     */ import com.vividsolutions.jts.index.bintree.Interval;
/*     */ import com.vividsolutions.jts.index.chain.MonotoneChain;
/*     */ import com.vividsolutions.jts.index.chain.MonotoneChainBuilder;
/*     */ import com.vividsolutions.jts.index.chain.MonotoneChainSelectAction;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MCPointInRing implements PointInRing {
/*     */   private LinearRing ring;
/*     */   
/*     */   private Bintree tree;
/*     */   
/*     */   class MCSelecter extends MonotoneChainSelectAction {
/*     */     Coordinate p;
/*     */     
/*     */     public MCSelecter(Coordinate p) {
/*  60 */       this.p = p;
/*     */     }
/*     */     
/*     */     public void select(LineSegment ls) {
/*  65 */       MCPointInRing.this.testLineSegment(this.p, ls);
/*     */     }
/*     */   }
/*     */   
/*  71 */   private int crossings = 0;
/*     */   
/*     */   private Interval interval;
/*     */   
/*     */   private void buildIndex() {
/*  82 */     this.tree = new Bintree();
/*  84 */     Coordinate[] pts = CoordinateArrays.removeRepeatedPoints(this.ring.getCoordinates());
/*  85 */     List<MonotoneChain> mcList = MonotoneChainBuilder.getChains(pts);
/*  87 */     for (int i = 0; i < mcList.size(); i++) {
/*  88 */       MonotoneChain mc = mcList.get(i);
/*  89 */       Envelope mcEnv = mc.getEnvelope();
/*  90 */       this.interval.min = mcEnv.getMinY();
/*  91 */       this.interval.max = mcEnv.getMaxY();
/*  92 */       this.tree.insert(this.interval, mc);
/*     */     } 
/*     */   }
/*     */   
/*     */   public MCPointInRing(LinearRing ring) {
/*  96 */     this.interval = new Interval();
/*     */     this.ring = ring;
/*     */     buildIndex();
/*     */   }
/*     */   
/*     */   public boolean isInside(Coordinate pt) {
/* 100 */     this.crossings = 0;
/* 103 */     Envelope rayEnv = new Envelope(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pt.y, pt.y);
/* 105 */     this.interval.min = pt.y;
/* 106 */     this.interval.max = pt.y;
/* 107 */     List segs = this.tree.query(this.interval);
/* 110 */     MCSelecter mcSelecter = new MCSelecter(pt);
/* 111 */     for (Iterator<MonotoneChain> i = segs.iterator(); i.hasNext(); ) {
/* 112 */       MonotoneChain mc = i.next();
/* 113 */       testMonotoneChain(rayEnv, mcSelecter, mc);
/*     */     } 
/* 119 */     if (this.crossings % 2 == 1)
/* 120 */       return true; 
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   private void testMonotoneChain(Envelope rayEnv, MCSelecter mcSelecter, MonotoneChain mc) {
/* 128 */     mc.select(rayEnv, mcSelecter);
/*     */   }
/*     */   
/*     */   private void testLineSegment(Coordinate p, LineSegment seg) {
/* 141 */     Coordinate p1 = seg.p0;
/* 142 */     Coordinate p2 = seg.p1;
/* 143 */     double x1 = p1.x - p.x;
/* 144 */     double y1 = p1.y - p.y;
/* 145 */     double x2 = p2.x - p.x;
/* 146 */     double y2 = p2.y - p.y;
/* 148 */     if ((y1 > 0.0D && y2 <= 0.0D) || (y2 > 0.0D && y1 <= 0.0D)) {
/* 153 */       double xInt = RobustDeterminant.signOfDet2x2(x1, y1, x2, y2) / (y2 - y1);
/* 158 */       if (0.0D < xInt)
/* 159 */         this.crossings++; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\MCPointInRing.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */