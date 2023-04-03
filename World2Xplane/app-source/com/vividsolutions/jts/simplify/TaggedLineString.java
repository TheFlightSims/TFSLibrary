/*     */ package com.vividsolutions.jts.simplify;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ class TaggedLineString {
/*     */   private LineString parentLine;
/*     */   
/*     */   private TaggedLineSegment[] segs;
/*     */   
/*  51 */   private List resultSegs = new ArrayList();
/*     */   
/*     */   private int minimumSize;
/*     */   
/*     */   public TaggedLineString(LineString parentLine) {
/*  55 */     this(parentLine, 2);
/*     */   }
/*     */   
/*     */   public TaggedLineString(LineString parentLine, int minimumSize) {
/*  59 */     this.parentLine = parentLine;
/*  60 */     this.minimumSize = minimumSize;
/*  61 */     init();
/*     */   }
/*     */   
/*     */   public int getMinimumSize() {
/*  64 */     return this.minimumSize;
/*     */   }
/*     */   
/*     */   public LineString getParent() {
/*  65 */     return this.parentLine;
/*     */   }
/*     */   
/*     */   public Coordinate[] getParentCoordinates() {
/*  66 */     return this.parentLine.getCoordinates();
/*     */   }
/*     */   
/*     */   public Coordinate[] getResultCoordinates() {
/*  67 */     return extractCoordinates(this.resultSegs);
/*     */   }
/*     */   
/*     */   public int getResultSize() {
/*  71 */     int resultSegsSize = this.resultSegs.size();
/*  72 */     return (resultSegsSize == 0) ? 0 : (resultSegsSize + 1);
/*     */   }
/*     */   
/*     */   public TaggedLineSegment getSegment(int i) {
/*  75 */     return this.segs[i];
/*     */   }
/*     */   
/*     */   private void init() {
/*  79 */     Coordinate[] pts = this.parentLine.getCoordinates();
/*  80 */     this.segs = new TaggedLineSegment[pts.length - 1];
/*  81 */     for (int i = 0; i < pts.length - 1; i++) {
/*  82 */       TaggedLineSegment seg = new TaggedLineSegment(pts[i], pts[i + 1], (Geometry)this.parentLine, i);
/*  84 */       this.segs[i] = seg;
/*     */     } 
/*     */   }
/*     */   
/*     */   public TaggedLineSegment[] getSegments() {
/*  88 */     return this.segs;
/*     */   }
/*     */   
/*     */   public void addToResult(LineSegment seg) {
/*  92 */     this.resultSegs.add(seg);
/*     */   }
/*     */   
/*     */   public LineString asLineString() {
/*  97 */     return this.parentLine.getFactory().createLineString(extractCoordinates(this.resultSegs));
/*     */   }
/*     */   
/*     */   public LinearRing asLinearRing() {
/* 101 */     return this.parentLine.getFactory().createLinearRing(extractCoordinates(this.resultSegs));
/*     */   }
/*     */   
/*     */   private static Coordinate[] extractCoordinates(List<LineSegment> segs) {
/* 106 */     Coordinate[] pts = new Coordinate[segs.size() + 1];
/* 107 */     LineSegment seg = null;
/* 108 */     for (int i = 0; i < segs.size(); i++) {
/* 109 */       seg = segs.get(i);
/* 110 */       pts[i] = seg.p0;
/*     */     } 
/* 113 */     pts[pts.length - 1] = seg.p1;
/* 114 */     return pts;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\TaggedLineString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */