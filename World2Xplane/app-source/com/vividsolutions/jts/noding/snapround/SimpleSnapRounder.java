/*     */ package com.vividsolutions.jts.noding.snapround;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.noding.InteriorIntersectionFinderAdder;
/*     */ import com.vividsolutions.jts.noding.MCIndexNoder;
/*     */ import com.vividsolutions.jts.noding.NodedSegmentString;
/*     */ import com.vividsolutions.jts.noding.Noder;
/*     */ import com.vividsolutions.jts.noding.NodingValidator;
/*     */ import com.vividsolutions.jts.noding.SegmentIntersector;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SimpleSnapRounder implements Noder {
/*     */   private final PrecisionModel pm;
/*     */   
/*     */   private LineIntersector li;
/*     */   
/*     */   private final double scaleFactor;
/*     */   
/*     */   private Collection nodedSegStrings;
/*     */   
/*     */   public SimpleSnapRounder(PrecisionModel pm) {
/*  68 */     this.pm = pm;
/*  69 */     this.li = (LineIntersector)new RobustLineIntersector();
/*  70 */     this.li.setPrecisionModel(pm);
/*  71 */     this.scaleFactor = pm.getScale();
/*     */   }
/*     */   
/*     */   public Collection getNodedSubstrings() {
/*  80 */     return NodedSegmentString.getNodedSubstrings(this.nodedSegStrings);
/*     */   }
/*     */   
/*     */   public void computeNodes(Collection inputSegmentStrings) {
/*  88 */     this.nodedSegStrings = inputSegmentStrings;
/*  89 */     snapRound(inputSegmentStrings, this.li);
/*     */   }
/*     */   
/*     */   private void checkCorrectness(Collection inputSegmentStrings) {
/*  97 */     Collection resultSegStrings = NodedSegmentString.getNodedSubstrings(inputSegmentStrings);
/*  98 */     NodingValidator nv = new NodingValidator(resultSegStrings);
/*     */     try {
/* 100 */       nv.checkValid();
/* 101 */     } catch (Exception ex) {
/* 102 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void snapRound(Collection segStrings, LineIntersector li) {
/* 107 */     List intersections = findInteriorIntersections(segStrings, li);
/* 108 */     computeSnaps(segStrings, intersections);
/* 109 */     computeVertexSnaps(segStrings);
/*     */   }
/*     */   
/*     */   private List findInteriorIntersections(Collection segStrings, LineIntersector li) {
/* 122 */     InteriorIntersectionFinderAdder intFinderAdder = new InteriorIntersectionFinderAdder(li);
/* 123 */     MCIndexNoder mCIndexNoder = new MCIndexNoder();
/* 124 */     mCIndexNoder.setSegmentIntersector((SegmentIntersector)intFinderAdder);
/* 125 */     mCIndexNoder.computeNodes(segStrings);
/* 126 */     return intFinderAdder.getInteriorIntersections();
/*     */   }
/*     */   
/*     */   private void computeSnaps(Collection segStrings, Collection snapPts) {
/* 136 */     for (Iterator<NodedSegmentString> i0 = segStrings.iterator(); i0.hasNext(); ) {
/* 137 */       NodedSegmentString ss = i0.next();
/* 138 */       computeSnaps(ss, snapPts);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeSnaps(NodedSegmentString ss, Collection snapPts) {
/* 144 */     for (Iterator<Coordinate> it = snapPts.iterator(); it.hasNext(); ) {
/* 145 */       Coordinate snapPt = it.next();
/* 146 */       HotPixel hotPixel = new HotPixel(snapPt, this.scaleFactor, this.li);
/* 147 */       for (int i = 0; i < ss.size() - 1; i++)
/* 148 */         hotPixel.addSnappedNode(ss, i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void computeVertexSnaps(Collection edges) {
/* 161 */     for (Iterator<NodedSegmentString> i0 = edges.iterator(); i0.hasNext(); ) {
/* 162 */       NodedSegmentString edge0 = i0.next();
/* 163 */       for (Iterator<NodedSegmentString> i1 = edges.iterator(); i1.hasNext(); ) {
/* 164 */         NodedSegmentString edge1 = i1.next();
/* 165 */         computeVertexSnaps(edge0, edge1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeVertexSnaps(NodedSegmentString e0, NodedSegmentString e1) {
/* 176 */     Coordinate[] pts0 = e0.getCoordinates();
/* 177 */     Coordinate[] pts1 = e1.getCoordinates();
/* 178 */     for (int i0 = 0; i0 < pts0.length - 1; i0++) {
/* 179 */       HotPixel hotPixel = new HotPixel(pts0[i0], this.scaleFactor, this.li);
/* 180 */       for (int i1 = 0; i1 < pts1.length - 1; i1++) {
/* 182 */         if (e0 != e1 || 
/* 183 */           i0 != i1) {
/* 186 */           boolean isNodeAdded = hotPixel.addSnappedNode(e1, i1);
/* 188 */           if (isNodeAdded)
/* 189 */             e0.addIntersection(pts0[i0], i0); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\snapround\SimpleSnapRounder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */