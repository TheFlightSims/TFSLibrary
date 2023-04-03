/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
/*     */ import com.vividsolutions.jts.io.WKTWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class NodedSegmentString implements NodableSegmentString {
/*     */   public static List getNodedSubstrings(Collection segStrings) {
/*  65 */     List resultEdgelist = new ArrayList();
/*  66 */     getNodedSubstrings(segStrings, resultEdgelist);
/*  67 */     return resultEdgelist;
/*     */   }
/*     */   
/*     */   public static void getNodedSubstrings(Collection segStrings, Collection resultEdgelist) {
/*  78 */     for (Iterator<NodedSegmentString> i = segStrings.iterator(); i.hasNext(); ) {
/*  79 */       NodedSegmentString ss = i.next();
/*  80 */       ss.getNodeList().addSplitEdges(resultEdgelist);
/*     */     } 
/*     */   }
/*     */   
/*  84 */   private SegmentNodeList nodeList = new SegmentNodeList(this);
/*     */   
/*     */   private Coordinate[] pts;
/*     */   
/*     */   private Object data;
/*     */   
/*     */   public NodedSegmentString(Coordinate[] pts, Object data) {
/*  96 */     this.pts = pts;
/*  97 */     this.data = data;
/*     */   }
/*     */   
/*     */   public Object getData() {
/* 105 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setData(Object data) {
/* 112 */     this.data = data;
/*     */   }
/*     */   
/*     */   public SegmentNodeList getNodeList() {
/* 114 */     return this.nodeList;
/*     */   }
/*     */   
/*     */   public int size() {
/* 115 */     return this.pts.length;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/* 116 */     return this.pts[i];
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/* 117 */     return this.pts;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 121 */     return this.pts[0].equals(this.pts[this.pts.length - 1]);
/*     */   }
/*     */   
/*     */   public int getSegmentOctant(int index) {
/* 133 */     if (index == this.pts.length - 1)
/* 133 */       return -1; 
/* 134 */     return safeOctant(getCoordinate(index), getCoordinate(index + 1));
/*     */   }
/*     */   
/*     */   private int safeOctant(Coordinate p0, Coordinate p1) {
/* 140 */     if (p0.equals2D(p1))
/* 140 */       return 0; 
/* 141 */     return Octant.octant(p0, p1);
/*     */   }
/*     */   
/*     */   public void addIntersections(LineIntersector li, int segmentIndex, int geomIndex) {
/* 150 */     for (int i = 0; i < li.getIntersectionNum(); i++)
/* 151 */       addIntersection(li, segmentIndex, geomIndex, i); 
/*     */   }
/*     */   
/*     */   public void addIntersection(LineIntersector li, int segmentIndex, int geomIndex, int intIndex) {
/* 162 */     Coordinate intPt = new Coordinate(li.getIntersection(intIndex));
/* 163 */     addIntersection(intPt, segmentIndex);
/*     */   }
/*     */   
/*     */   public void addIntersection(Coordinate intPt, int segmentIndex) {
/* 173 */     addIntersectionNode(intPt, segmentIndex);
/*     */   }
/*     */   
/*     */   public SegmentNode addIntersectionNode(Coordinate intPt, int segmentIndex) {
/* 186 */     int normalizedSegmentIndex = segmentIndex;
/* 189 */     int nextSegIndex = normalizedSegmentIndex + 1;
/* 190 */     if (nextSegIndex < this.pts.length) {
/* 191 */       Coordinate nextPt = this.pts[nextSegIndex];
/* 196 */       if (intPt.equals2D(nextPt))
/* 198 */         normalizedSegmentIndex = nextSegIndex; 
/*     */     } 
/* 204 */     SegmentNode ei = this.nodeList.add(intPt, normalizedSegmentIndex);
/* 205 */     return ei;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 210 */     return WKTWriter.toLineString((CoordinateSequence)new CoordinateArraySequence(this.pts));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\NodedSegmentString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */