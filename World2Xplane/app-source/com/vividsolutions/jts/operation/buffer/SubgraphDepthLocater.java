/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geomgraph.DirectedEdge;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ class SubgraphDepthLocater {
/*     */   private Collection subgraphs;
/*     */   
/*  52 */   private LineSegment seg = new LineSegment();
/*     */   
/*  53 */   private CGAlgorithms cga = new CGAlgorithms();
/*     */   
/*     */   public SubgraphDepthLocater(List subgraphs) {
/*  57 */     this.subgraphs = subgraphs;
/*     */   }
/*     */   
/*     */   public int getDepth(Coordinate p) {
/*  62 */     List<Comparable> stabbedSegments = findStabbedSegments(p);
/*  64 */     if (stabbedSegments.size() == 0)
/*  65 */       return 0; 
/*  66 */     Collections.sort(stabbedSegments);
/*  67 */     DepthSegment ds = (DepthSegment)stabbedSegments.get(0);
/*  68 */     return ds.leftDepth;
/*     */   }
/*     */   
/*     */   private List findStabbedSegments(Coordinate stabbingRayLeftPt) {
/*  80 */     List stabbedSegments = new ArrayList();
/*  81 */     for (Iterator<BufferSubgraph> i = this.subgraphs.iterator(); i.hasNext(); ) {
/*  82 */       BufferSubgraph bsg = i.next();
/*  85 */       Envelope env = bsg.getEnvelope();
/*  86 */       if (stabbingRayLeftPt.y < env.getMinY() || stabbingRayLeftPt.y > env.getMaxY())
/*     */         continue; 
/*  90 */       findStabbedSegments(stabbingRayLeftPt, bsg.getDirectedEdges(), stabbedSegments);
/*     */     } 
/*  92 */     return stabbedSegments;
/*     */   }
/*     */   
/*     */   private void findStabbedSegments(Coordinate stabbingRayLeftPt, List dirEdges, List stabbedSegments) {
/* 111 */     for (Iterator<DirectedEdge> i = dirEdges.iterator(); i.hasNext(); ) {
/* 112 */       DirectedEdge de = i.next();
/* 113 */       if (!de.isForward())
/*     */         continue; 
/* 115 */       findStabbedSegments(stabbingRayLeftPt, de, stabbedSegments);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void findStabbedSegments(Coordinate stabbingRayLeftPt, DirectedEdge dirEdge, List<DepthSegment> stabbedSegments) {
/* 131 */     Coordinate[] pts = dirEdge.getEdge().getCoordinates();
/* 132 */     for (int i = 0; i < pts.length - 1; i++) {
/* 133 */       this.seg.p0 = pts[i];
/* 134 */       this.seg.p1 = pts[i + 1];
/* 136 */       if (this.seg.p0.y > this.seg.p1.y)
/* 137 */         this.seg.reverse(); 
/* 140 */       double maxx = Math.max(this.seg.p0.x, this.seg.p1.x);
/* 141 */       if (maxx >= stabbingRayLeftPt.x)
/* 145 */         if (!this.seg.isHorizontal())
/* 149 */           if (stabbingRayLeftPt.y >= this.seg.p0.y && stabbingRayLeftPt.y <= this.seg.p1.y)
/* 153 */             if (CGAlgorithms.computeOrientation(this.seg.p0, this.seg.p1, stabbingRayLeftPt) != -1) {
/* 158 */               int depth = dirEdge.getDepth(1);
/* 160 */               if (!this.seg.p0.equals(pts[i]))
/* 161 */                 depth = dirEdge.getDepth(2); 
/* 162 */               DepthSegment ds = new DepthSegment(this.seg, depth);
/* 163 */               stabbedSegments.add(ds);
/*     */             }    
/*     */     } 
/*     */   }
/*     */   
/*     */   private class DepthSegment implements Comparable {
/*     */     private LineSegment upwardSeg;
/*     */     
/*     */     private int leftDepth;
/*     */     
/*     */     public DepthSegment(LineSegment seg, int depth) {
/* 181 */       this.upwardSeg = new LineSegment(seg);
/* 183 */       this.leftDepth = depth;
/*     */     }
/*     */     
/*     */     public int compareTo(Object obj) {
/* 199 */       DepthSegment other = (DepthSegment)obj;
/* 204 */       int orientIndex = this.upwardSeg.orientationIndex(other.upwardSeg);
/* 213 */       if (orientIndex == 0)
/* 214 */         orientIndex = -1 * other.upwardSeg.orientationIndex(this.upwardSeg); 
/* 217 */       if (orientIndex != 0)
/* 218 */         return orientIndex; 
/* 221 */       return compareX(this.upwardSeg, other.upwardSeg);
/*     */     }
/*     */     
/*     */     private int compareX(LineSegment seg0, LineSegment seg1) {
/* 237 */       int compare0 = seg0.p0.compareTo(seg1.p0);
/* 238 */       if (compare0 != 0)
/* 239 */         return compare0; 
/* 240 */       return seg0.p1.compareTo(seg1.p1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\SubgraphDepthLocater.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */