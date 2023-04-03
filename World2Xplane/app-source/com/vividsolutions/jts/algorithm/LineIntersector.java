/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.io.WKTWriter;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public abstract class LineIntersector {
/*     */   public static final int DONT_INTERSECT = 0;
/*     */   
/*     */   public static final int DO_INTERSECT = 1;
/*     */   
/*     */   public static final int COLLINEAR = 2;
/*     */   
/*     */   public static final int NO_INTERSECTION = 0;
/*     */   
/*     */   public static final int POINT_INTERSECTION = 1;
/*     */   
/*     */   public static final int COLLINEAR_INTERSECTION = 2;
/*     */   
/*     */   protected int result;
/*     */   
/*     */   public static double computeEdgeDistance(Coordinate p, Coordinate p0, Coordinate p1) {
/* 116 */     double dx = Math.abs(p1.x - p0.x);
/* 117 */     double dy = Math.abs(p1.y - p0.y);
/* 119 */     double dist = -1.0D;
/* 120 */     if (p.equals(p0)) {
/* 121 */       dist = 0.0D;
/* 123 */     } else if (p.equals(p1)) {
/* 124 */       if (dx > dy) {
/* 125 */         dist = dx;
/*     */       } else {
/* 127 */         dist = dy;
/*     */       } 
/*     */     } else {
/* 130 */       double pdx = Math.abs(p.x - p0.x);
/* 131 */       double pdy = Math.abs(p.y - p0.y);
/* 132 */       if (dx > dy) {
/* 133 */         dist = pdx;
/*     */       } else {
/* 135 */         dist = pdy;
/*     */       } 
/* 138 */       if (dist == 0.0D && !p.equals(p0))
/* 140 */         dist = Math.max(pdx, pdy); 
/*     */     } 
/* 143 */     Assert.isTrue((dist != 0.0D || p.equals(p0)), "Bad distance calculation");
/* 144 */     return dist;
/*     */   }
/*     */   
/*     */   public static double nonRobustComputeEdgeDistance(Coordinate p, Coordinate p1, Coordinate p2) {
/* 156 */     double dx = p.x - p1.x;
/* 157 */     double dy = p.y - p1.y;
/* 158 */     double dist = Math.sqrt(dx * dx + dy * dy);
/* 159 */     Assert.isTrue((dist != 0.0D || p.equals(p1)), "Invalid distance calculation");
/* 160 */     return dist;
/*     */   }
/*     */   
/* 164 */   protected Coordinate[][] inputLines = new Coordinate[2][2];
/*     */   
/* 165 */   protected Coordinate[] intPt = new Coordinate[2];
/*     */   
/*     */   protected int[][] intLineIndex;
/*     */   
/*     */   protected boolean isProper;
/*     */   
/*     */   protected Coordinate pa;
/*     */   
/*     */   protected Coordinate pb;
/*     */   
/* 178 */   protected PrecisionModel precisionModel = null;
/*     */   
/*     */   public LineIntersector() {
/* 182 */     this.intPt[0] = new Coordinate();
/* 183 */     this.intPt[1] = new Coordinate();
/* 185 */     this.pa = this.intPt[0];
/* 186 */     this.pb = this.intPt[1];
/* 187 */     this.result = 0;
/*     */   }
/*     */   
/*     */   public void setMakePrecise(PrecisionModel precisionModel) {
/* 197 */     this.precisionModel = precisionModel;
/*     */   }
/*     */   
/*     */   public void setPrecisionModel(PrecisionModel precisionModel) {
/* 207 */     this.precisionModel = precisionModel;
/*     */   }
/*     */   
/*     */   public Coordinate getEndpoint(int segmentIndex, int ptIndex) {
/* 219 */     return this.inputLines[segmentIndex][ptIndex];
/*     */   }
/*     */   
/*     */   public abstract void computeIntersection(Coordinate paramCoordinate1, Coordinate paramCoordinate2, Coordinate paramCoordinate3);
/*     */   
/*     */   protected boolean isCollinear() {
/* 233 */     return (this.result == 2);
/*     */   }
/*     */   
/*     */   public void computeIntersection(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate p4) {
/* 244 */     this.inputLines[0][0] = p1;
/* 245 */     this.inputLines[0][1] = p2;
/* 246 */     this.inputLines[1][0] = p3;
/* 247 */     this.inputLines[1][1] = p4;
/* 248 */     this.result = computeIntersect(p1, p2, p3, p4);
/*     */   }
/*     */   
/*     */   protected abstract int computeIntersect(Coordinate paramCoordinate1, Coordinate paramCoordinate2, Coordinate paramCoordinate3, Coordinate paramCoordinate4);
/*     */   
/*     */   public String toString() {
/* 268 */     return WKTWriter.toLineString(this.inputLines[0][0], this.inputLines[0][1]) + " - " + WKTWriter.toLineString(this.inputLines[1][0], this.inputLines[1][1]) + getTopologySummary();
/*     */   }
/*     */   
/*     */   private String getTopologySummary() {
/* 275 */     StringBuffer catBuf = new StringBuffer();
/* 276 */     if (isEndPoint())
/* 276 */       catBuf.append(" endpoint"); 
/* 277 */     if (this.isProper)
/* 277 */       catBuf.append(" proper"); 
/* 278 */     if (isCollinear())
/* 278 */       catBuf.append(" collinear"); 
/* 279 */     return catBuf.toString();
/*     */   }
/*     */   
/*     */   protected boolean isEndPoint() {
/* 283 */     return (hasIntersection() && !this.isProper);
/*     */   }
/*     */   
/*     */   public boolean hasIntersection() {
/* 292 */     return (this.result != 0);
/*     */   }
/*     */   
/*     */   public int getIntersectionNum() {
/* 300 */     return this.result;
/*     */   }
/*     */   
/*     */   public Coordinate getIntersection(int intIndex) {
/* 309 */     return this.intPt[intIndex];
/*     */   }
/*     */   
/*     */   protected void computeIntLineIndex() {
/* 312 */     if (this.intLineIndex == null) {
/* 313 */       this.intLineIndex = new int[2][2];
/* 314 */       computeIntLineIndex(0);
/* 315 */       computeIntLineIndex(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isIntersection(Coordinate pt) {
/* 329 */     for (int i = 0; i < this.result; i++) {
/* 330 */       if (this.intPt[i].equals2D(pt))
/* 331 */         return true; 
/*     */     } 
/* 334 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isInteriorIntersection() {
/* 344 */     if (isInteriorIntersection(0))
/* 344 */       return true; 
/* 345 */     if (isInteriorIntersection(1))
/* 345 */       return true; 
/* 346 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isInteriorIntersection(int inputLineIndex) {
/* 356 */     for (int i = 0; i < this.result; i++) {
/* 357 */       if (!this.intPt[i].equals2D(this.inputLines[inputLineIndex][0]) && !this.intPt[i].equals2D(this.inputLines[inputLineIndex][1]))
/* 359 */         return true; 
/*     */     } 
/* 362 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isProper() {
/* 380 */     return (hasIntersection() && this.isProper);
/*     */   }
/*     */   
/*     */   public Coordinate getIntersectionAlongSegment(int segmentIndex, int intIndex) {
/* 394 */     computeIntLineIndex();
/* 395 */     return this.intPt[this.intLineIndex[segmentIndex][intIndex]];
/*     */   }
/*     */   
/*     */   public int getIndexAlongSegment(int segmentIndex, int intIndex) {
/* 408 */     computeIntLineIndex();
/* 409 */     return this.intLineIndex[segmentIndex][intIndex];
/*     */   }
/*     */   
/*     */   protected void computeIntLineIndex(int segmentIndex) {
/* 413 */     double dist0 = getEdgeDistance(segmentIndex, 0);
/* 414 */     double dist1 = getEdgeDistance(segmentIndex, 1);
/* 415 */     if (dist0 > dist1) {
/* 416 */       this.intLineIndex[segmentIndex][0] = 0;
/* 417 */       this.intLineIndex[segmentIndex][1] = 1;
/*     */     } else {
/* 420 */       this.intLineIndex[segmentIndex][0] = 1;
/* 421 */       this.intLineIndex[segmentIndex][1] = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getEdgeDistance(int segmentIndex, int intIndex) {
/* 434 */     double dist = computeEdgeDistance(this.intPt[intIndex], this.inputLines[segmentIndex][0], this.inputLines[segmentIndex][1]);
/* 436 */     return dist;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\LineIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */