/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class IteratedNoder implements Noder {
/*     */   public static final int MAX_ITER = 5;
/*     */   
/*     */   private PrecisionModel pm;
/*     */   
/*     */   private LineIntersector li;
/*     */   
/*     */   private Collection nodedSegStrings;
/*     */   
/*  61 */   private int maxIter = 5;
/*     */   
/*     */   public IteratedNoder(PrecisionModel pm) {
/*  65 */     this.li = (LineIntersector)new RobustLineIntersector();
/*  66 */     this.pm = pm;
/*  67 */     this.li.setPrecisionModel(pm);
/*     */   }
/*     */   
/*     */   public void setMaximumIterations(int maxIter) {
/*  81 */     this.maxIter = maxIter;
/*     */   }
/*     */   
/*     */   public Collection getNodedSubstrings() {
/*  84 */     return this.nodedSegStrings;
/*     */   }
/*     */   
/*     */   public void computeNodes(Collection segStrings) throws TopologyException {
/*  98 */     int[] numInteriorIntersections = new int[1];
/*  99 */     this.nodedSegStrings = segStrings;
/* 100 */     int nodingIterationCount = 0;
/* 101 */     int lastNodesCreated = -1;
/*     */     do {
/* 103 */       node(this.nodedSegStrings, numInteriorIntersections);
/* 104 */       nodingIterationCount++;
/* 105 */       int nodesCreated = numInteriorIntersections[0];
/* 112 */       if (lastNodesCreated > 0 && nodesCreated >= lastNodesCreated && nodingIterationCount > this.maxIter)
/* 115 */         throw new TopologyException("Iterated noding failed to converge after " + nodingIterationCount + " iterations"); 
/* 118 */       lastNodesCreated = nodesCreated;
/* 120 */     } while (lastNodesCreated > 0);
/*     */   }
/*     */   
/*     */   private void node(Collection segStrings, int[] numInteriorIntersections) {
/* 131 */     IntersectionAdder si = new IntersectionAdder(this.li);
/* 132 */     MCIndexNoder noder = new MCIndexNoder();
/* 133 */     noder.setSegmentIntersector(si);
/* 134 */     noder.computeNodes(segStrings);
/* 135 */     this.nodedSegStrings = noder.getNodedSubstrings();
/* 136 */     numInteriorIntersections[0] = si.numInteriorIntersections;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\IteratedNoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */