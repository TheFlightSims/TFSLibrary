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
/*     */ import com.vividsolutions.jts.noding.SegmentString;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MCIndexSnapRounder implements Noder {
/*     */   private final PrecisionModel pm;
/*     */   
/*     */   private LineIntersector li;
/*     */   
/*     */   private final double scaleFactor;
/*     */   
/*     */   private MCIndexNoder noder;
/*     */   
/*     */   private MCIndexPointSnapper pointSnapper;
/*     */   
/*     */   private Collection nodedSegStrings;
/*     */   
/*     */   public MCIndexSnapRounder(PrecisionModel pm) {
/*  70 */     this.pm = pm;
/*  71 */     this.li = (LineIntersector)new RobustLineIntersector();
/*  72 */     this.li.setPrecisionModel(pm);
/*  73 */     this.scaleFactor = pm.getScale();
/*     */   }
/*     */   
/*     */   public Collection getNodedSubstrings() {
/*  78 */     return NodedSegmentString.getNodedSubstrings(this.nodedSegStrings);
/*     */   }
/*     */   
/*     */   public void computeNodes(Collection inputSegmentStrings) {
/*  83 */     this.nodedSegStrings = inputSegmentStrings;
/*  84 */     this.noder = new MCIndexNoder();
/*  85 */     this.pointSnapper = new MCIndexPointSnapper(this.noder.getIndex());
/*  86 */     snapRound(inputSegmentStrings, this.li);
/*     */   }
/*     */   
/*     */   private void checkCorrectness(Collection inputSegmentStrings) {
/*  94 */     Collection resultSegStrings = NodedSegmentString.getNodedSubstrings(inputSegmentStrings);
/*  95 */     NodingValidator nv = new NodingValidator(resultSegStrings);
/*     */     try {
/*  97 */       nv.checkValid();
/*  98 */     } catch (Exception ex) {
/*  99 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void snapRound(Collection segStrings, LineIntersector li) {
/* 105 */     List intersections = findInteriorIntersections(segStrings, li);
/* 106 */     computeIntersectionSnaps(intersections);
/* 107 */     computeVertexSnaps(segStrings);
/*     */   }
/*     */   
/*     */   private List findInteriorIntersections(Collection segStrings, LineIntersector li) {
/* 120 */     InteriorIntersectionFinderAdder intFinderAdder = new InteriorIntersectionFinderAdder(li);
/* 121 */     this.noder.setSegmentIntersector((SegmentIntersector)intFinderAdder);
/* 122 */     this.noder.computeNodes(segStrings);
/* 123 */     return intFinderAdder.getInteriorIntersections();
/*     */   }
/*     */   
/*     */   private void computeIntersectionSnaps(Collection snapPts) {
/* 131 */     for (Iterator<Coordinate> it = snapPts.iterator(); it.hasNext(); ) {
/* 132 */       Coordinate snapPt = it.next();
/* 133 */       HotPixel hotPixel = new HotPixel(snapPt, this.scaleFactor, this.li);
/* 134 */       this.pointSnapper.snap(hotPixel);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void computeVertexSnaps(Collection edges) {
/* 145 */     for (Iterator<NodedSegmentString> i0 = edges.iterator(); i0.hasNext(); ) {
/* 146 */       NodedSegmentString edge0 = i0.next();
/* 147 */       computeVertexSnaps(edge0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeVertexSnaps(NodedSegmentString e) {
/* 156 */     Coordinate[] pts0 = e.getCoordinates();
/* 157 */     for (int i = 0; i < pts0.length; i++) {
/* 158 */       HotPixel hotPixel = new HotPixel(pts0[i], this.scaleFactor, this.li);
/* 159 */       boolean isNodeAdded = this.pointSnapper.snap(hotPixel, (SegmentString)e, i);
/* 161 */       if (isNodeAdded)
/* 162 */         e.addIntersection(pts0[i], i); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\snapround\MCIndexSnapRounder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */