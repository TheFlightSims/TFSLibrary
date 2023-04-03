/*     */ package com.vividsolutions.jts.simplify;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TaggedLineStringSimplifier {
/*  51 */   private LineIntersector li = (LineIntersector)new RobustLineIntersector();
/*     */   
/*  52 */   private LineSegmentIndex inputIndex = new LineSegmentIndex();
/*     */   
/*  53 */   private LineSegmentIndex outputIndex = new LineSegmentIndex();
/*     */   
/*     */   private TaggedLineString line;
/*     */   
/*     */   private Coordinate[] linePts;
/*     */   
/*  56 */   private double distanceTolerance = 0.0D;
/*     */   
/*     */   public TaggedLineStringSimplifier(LineSegmentIndex inputIndex, LineSegmentIndex outputIndex) {
/*  61 */     this.inputIndex = inputIndex;
/*  62 */     this.outputIndex = outputIndex;
/*     */   }
/*     */   
/*     */   public void setDistanceTolerance(double distanceTolerance) {
/*  73 */     this.distanceTolerance = distanceTolerance;
/*     */   }
/*     */   
/*     */   void simplify(TaggedLineString line) {
/*  84 */     this.line = line;
/*  85 */     this.linePts = line.getParentCoordinates();
/*  86 */     simplifySection(0, this.linePts.length - 1, 0);
/*     */   }
/*     */   
/*     */   private void simplifySection(int i, int j, int depth) {
/*  91 */     depth++;
/*  92 */     int[] sectionIndex = new int[2];
/*  93 */     if (i + 1 == j) {
/*  94 */       LineSegment newSeg = this.line.getSegment(i);
/*  95 */       this.line.addToResult(newSeg);
/*     */       return;
/*     */     } 
/* 100 */     boolean isValidToSimplify = true;
/* 108 */     if (this.line.getResultSize() < this.line.getMinimumSize()) {
/* 109 */       int worstCaseSize = depth + 1;
/* 110 */       if (worstCaseSize < this.line.getMinimumSize())
/* 111 */         isValidToSimplify = false; 
/*     */     } 
/* 114 */     double[] distance = new double[1];
/* 115 */     int furthestPtIndex = findFurthestPoint(this.linePts, i, j, distance);
/* 117 */     if (distance[0] > this.distanceTolerance)
/* 117 */       isValidToSimplify = false; 
/* 119 */     LineSegment candidateSeg = new LineSegment();
/* 120 */     candidateSeg.p0 = this.linePts[i];
/* 121 */     candidateSeg.p1 = this.linePts[j];
/* 122 */     sectionIndex[0] = i;
/* 123 */     sectionIndex[1] = j;
/* 124 */     if (hasBadIntersection(this.line, sectionIndex, candidateSeg))
/* 124 */       isValidToSimplify = false; 
/* 126 */     if (isValidToSimplify) {
/* 127 */       LineSegment newSeg = flatten(i, j);
/* 128 */       this.line.addToResult(newSeg);
/*     */       return;
/*     */     } 
/* 131 */     simplifySection(i, furthestPtIndex, depth);
/* 132 */     simplifySection(furthestPtIndex, j, depth);
/*     */   }
/*     */   
/*     */   private int findFurthestPoint(Coordinate[] pts, int i, int j, double[] maxDistance) {
/* 137 */     LineSegment seg = new LineSegment();
/* 138 */     seg.p0 = pts[i];
/* 139 */     seg.p1 = pts[j];
/* 140 */     double maxDist = -1.0D;
/* 141 */     int maxIndex = i;
/* 142 */     for (int k = i + 1; k < j; k++) {
/* 143 */       Coordinate midPt = pts[k];
/* 144 */       double distance = seg.distance(midPt);
/* 145 */       if (distance > maxDist) {
/* 146 */         maxDist = distance;
/* 147 */         maxIndex = k;
/*     */       } 
/*     */     } 
/* 150 */     maxDistance[0] = maxDist;
/* 151 */     return maxIndex;
/*     */   }
/*     */   
/*     */   private LineSegment flatten(int start, int end) {
/* 168 */     Coordinate p0 = this.linePts[start];
/* 169 */     Coordinate p1 = this.linePts[end];
/* 170 */     LineSegment newSeg = new LineSegment(p0, p1);
/* 172 */     remove(this.line, start, end);
/* 173 */     this.outputIndex.add(newSeg);
/* 174 */     return newSeg;
/*     */   }
/*     */   
/*     */   private boolean hasBadIntersection(TaggedLineString parentLine, int[] sectionIndex, LineSegment candidateSeg) {
/* 181 */     if (hasBadOutputIntersection(candidateSeg))
/* 181 */       return true; 
/* 182 */     if (hasBadInputIntersection(parentLine, sectionIndex, candidateSeg))
/* 182 */       return true; 
/* 183 */     return false;
/*     */   }
/*     */   
/*     */   private boolean hasBadOutputIntersection(LineSegment candidateSeg) {
/* 188 */     List querySegs = this.outputIndex.query(candidateSeg);
/* 189 */     for (Iterator<LineSegment> i = querySegs.iterator(); i.hasNext(); ) {
/* 190 */       LineSegment querySeg = i.next();
/* 191 */       if (hasInteriorIntersection(querySeg, candidateSeg))
/* 192 */         return true; 
/*     */     } 
/* 195 */     return false;
/*     */   }
/*     */   
/*     */   private boolean hasBadInputIntersection(TaggedLineString parentLine, int[] sectionIndex, LineSegment candidateSeg) {
/* 202 */     List querySegs = this.inputIndex.query(candidateSeg);
/* 203 */     for (Iterator<TaggedLineSegment> i = querySegs.iterator(); i.hasNext(); ) {
/* 204 */       TaggedLineSegment querySeg = i.next();
/* 205 */       if (!hasInteriorIntersection(querySeg, candidateSeg) || 
/* 206 */         isInLineSection(parentLine, sectionIndex, querySeg))
/*     */         continue; 
/* 208 */       return true;
/*     */     } 
/* 211 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean isInLineSection(TaggedLineString line, int[] sectionIndex, TaggedLineSegment seg) {
/* 227 */     if (seg.getParent() != line.getParent())
/* 228 */       return false; 
/* 229 */     int segIndex = seg.getIndex();
/* 230 */     if (segIndex >= sectionIndex[0] && segIndex < sectionIndex[1])
/* 231 */       return true; 
/* 232 */     return false;
/*     */   }
/*     */   
/*     */   private boolean hasInteriorIntersection(LineSegment seg0, LineSegment seg1) {
/* 237 */     this.li.computeIntersection(seg0.p0, seg0.p1, seg1.p0, seg1.p1);
/* 238 */     return this.li.isInteriorIntersection();
/*     */   }
/*     */   
/*     */   private void remove(TaggedLineString line, int start, int end) {
/* 251 */     for (int i = start; i < end; i++) {
/* 252 */       TaggedLineSegment seg = line.getSegment(i);
/* 253 */       this.inputIndex.remove(seg);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\TaggedLineStringSimplifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */