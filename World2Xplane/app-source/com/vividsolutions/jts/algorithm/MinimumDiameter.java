/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class MinimumDiameter {
/*     */   private final Geometry inputGeom;
/*     */   
/*     */   private final boolean isConvex;
/*     */   
/*  68 */   private Coordinate[] convexHullPts = null;
/*     */   
/*  69 */   private LineSegment minBaseSeg = new LineSegment();
/*     */   
/*  70 */   private Coordinate minWidthPt = null;
/*     */   
/*     */   private int minPtIndex;
/*     */   
/*  72 */   private double minWidth = 0.0D;
/*     */   
/*     */   public MinimumDiameter(Geometry inputGeom) {
/*  81 */     this(inputGeom, false);
/*     */   }
/*     */   
/*     */   public MinimumDiameter(Geometry inputGeom, boolean isConvex) {
/*  96 */     this.inputGeom = inputGeom;
/*  97 */     this.isConvex = isConvex;
/*     */   }
/*     */   
/*     */   public double getLength() {
/* 107 */     computeMinimumDiameter();
/* 108 */     return this.minWidth;
/*     */   }
/*     */   
/*     */   public Coordinate getWidthCoordinate() {
/* 118 */     computeMinimumDiameter();
/* 119 */     return this.minWidthPt;
/*     */   }
/*     */   
/*     */   public LineString getSupportingSegment() {
/* 129 */     computeMinimumDiameter();
/* 130 */     return this.inputGeom.getFactory().createLineString(new Coordinate[] { this.minBaseSeg.p0, this.minBaseSeg.p1 });
/*     */   }
/*     */   
/*     */   public LineString getDiameter() {
/* 140 */     computeMinimumDiameter();
/* 143 */     if (this.minWidthPt == null)
/* 144 */       return this.inputGeom.getFactory().createLineString((Coordinate[])null); 
/* 146 */     Coordinate basePt = this.minBaseSeg.project(this.minWidthPt);
/* 147 */     return this.inputGeom.getFactory().createLineString(new Coordinate[] { basePt, this.minWidthPt });
/*     */   }
/*     */   
/*     */   private void computeMinimumDiameter() {
/* 153 */     if (this.minWidthPt != null)
/*     */       return; 
/* 156 */     if (this.isConvex) {
/* 157 */       computeWidthConvex(this.inputGeom);
/*     */     } else {
/* 159 */       Geometry convexGeom = (new ConvexHull(this.inputGeom)).getConvexHull();
/* 160 */       computeWidthConvex(convexGeom);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeWidthConvex(Geometry convexGeom) {
/* 167 */     if (convexGeom instanceof Polygon) {
/* 168 */       this.convexHullPts = ((Polygon)convexGeom).getExteriorRing().getCoordinates();
/*     */     } else {
/* 170 */       this.convexHullPts = convexGeom.getCoordinates();
/*     */     } 
/* 173 */     if (this.convexHullPts.length == 0) {
/* 174 */       this.minWidth = 0.0D;
/* 175 */       this.minWidthPt = null;
/* 176 */       this.minBaseSeg = null;
/* 178 */     } else if (this.convexHullPts.length == 1) {
/* 179 */       this.minWidth = 0.0D;
/* 180 */       this.minWidthPt = this.convexHullPts[0];
/* 181 */       this.minBaseSeg.p0 = this.convexHullPts[0];
/* 182 */       this.minBaseSeg.p1 = this.convexHullPts[0];
/* 184 */     } else if (this.convexHullPts.length == 2 || this.convexHullPts.length == 3) {
/* 185 */       this.minWidth = 0.0D;
/* 186 */       this.minWidthPt = this.convexHullPts[0];
/* 187 */       this.minBaseSeg.p0 = this.convexHullPts[0];
/* 188 */       this.minBaseSeg.p1 = this.convexHullPts[1];
/*     */     } else {
/* 191 */       computeConvexRingMinDiameter(this.convexHullPts);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeConvexRingMinDiameter(Coordinate[] pts) {
/* 203 */     this.minWidth = Double.MAX_VALUE;
/* 204 */     int currMaxIndex = 1;
/* 206 */     LineSegment seg = new LineSegment();
/* 208 */     for (int i = 0; i < pts.length - 1; i++) {
/* 209 */       seg.p0 = pts[i];
/* 210 */       seg.p1 = pts[i + 1];
/* 211 */       currMaxIndex = findMaxPerpDistance(pts, seg, currMaxIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int findMaxPerpDistance(Coordinate[] pts, LineSegment seg, int startIndex) {
/* 217 */     double maxPerpDistance = seg.distancePerpendicular(pts[startIndex]);
/* 218 */     double nextPerpDistance = maxPerpDistance;
/* 219 */     int maxIndex = startIndex;
/* 220 */     int nextIndex = maxIndex;
/* 221 */     while (nextPerpDistance >= maxPerpDistance) {
/* 222 */       maxPerpDistance = nextPerpDistance;
/* 223 */       maxIndex = nextIndex;
/* 225 */       nextIndex = nextIndex(pts, maxIndex);
/* 226 */       nextPerpDistance = seg.distancePerpendicular(pts[nextIndex]);
/*     */     } 
/* 229 */     if (maxPerpDistance < this.minWidth) {
/* 230 */       this.minPtIndex = maxIndex;
/* 231 */       this.minWidth = maxPerpDistance;
/* 232 */       this.minWidthPt = pts[this.minPtIndex];
/* 233 */       this.minBaseSeg = new LineSegment(seg);
/*     */     } 
/* 237 */     return maxIndex;
/*     */   }
/*     */   
/*     */   private static int nextIndex(Coordinate[] pts, int index) {
/* 242 */     index++;
/* 243 */     if (index >= pts.length)
/* 243 */       index = 0; 
/* 244 */     return index;
/*     */   }
/*     */   
/*     */   public Geometry getMinimumRectangle() {
/* 261 */     computeMinimumDiameter();
/* 264 */     if (this.minWidth == 0.0D) {
/* 265 */       if (this.minBaseSeg.p0.equals2D(this.minBaseSeg.p1))
/* 266 */         return (Geometry)this.inputGeom.getFactory().createPoint(this.minBaseSeg.p0); 
/* 268 */       return (Geometry)this.minBaseSeg.toGeometry(this.inputGeom.getFactory());
/*     */     } 
/* 272 */     double dx = this.minBaseSeg.p1.x - this.minBaseSeg.p0.x;
/* 273 */     double dy = this.minBaseSeg.p1.y - this.minBaseSeg.p0.y;
/* 280 */     double minPara = Double.MAX_VALUE;
/* 281 */     double maxPara = -1.7976931348623157E308D;
/* 282 */     double minPerp = Double.MAX_VALUE;
/* 283 */     double maxPerp = -1.7976931348623157E308D;
/* 286 */     for (int i = 0; i < this.convexHullPts.length; i++) {
/* 288 */       double paraC = computeC(dx, dy, this.convexHullPts[i]);
/* 289 */       if (paraC > maxPara)
/* 289 */         maxPara = paraC; 
/* 290 */       if (paraC < minPara)
/* 290 */         minPara = paraC; 
/* 292 */       double perpC = computeC(-dy, dx, this.convexHullPts[i]);
/* 293 */       if (perpC > maxPerp)
/* 293 */         maxPerp = perpC; 
/* 294 */       if (perpC < minPerp)
/* 294 */         minPerp = perpC; 
/*     */     } 
/* 298 */     LineSegment maxPerpLine = computeSegmentForLine(-dx, -dy, maxPerp);
/* 299 */     LineSegment minPerpLine = computeSegmentForLine(-dx, -dy, minPerp);
/* 300 */     LineSegment maxParaLine = computeSegmentForLine(-dy, dx, maxPara);
/* 301 */     LineSegment minParaLine = computeSegmentForLine(-dy, dx, minPara);
/* 304 */     Coordinate p0 = maxParaLine.lineIntersection(maxPerpLine);
/* 305 */     Coordinate p1 = minParaLine.lineIntersection(maxPerpLine);
/* 306 */     Coordinate p2 = minParaLine.lineIntersection(minPerpLine);
/* 307 */     Coordinate p3 = maxParaLine.lineIntersection(minPerpLine);
/* 309 */     LinearRing shell = this.inputGeom.getFactory().createLinearRing(new Coordinate[] { p0, p1, p2, p3, p0 });
/* 311 */     return (Geometry)this.inputGeom.getFactory().createPolygon(shell, null);
/*     */   }
/*     */   
/*     */   private static double computeC(double a, double b, Coordinate p) {
/* 317 */     return a * p.y - b * p.x;
/*     */   }
/*     */   
/*     */   private static LineSegment computeSegmentForLine(double a, double b, double c) {
/*     */     Coordinate p0;
/*     */     Coordinate p1;
/* 329 */     if (Math.abs(b) > Math.abs(a)) {
/* 330 */       p0 = new Coordinate(0.0D, c / b);
/* 331 */       p1 = new Coordinate(1.0D, c / b - a / b);
/*     */     } else {
/* 334 */       p0 = new Coordinate(c / a, 0.0D);
/* 335 */       p1 = new Coordinate(c / a - b / a, 1.0D);
/*     */     } 
/* 337 */     return new LineSegment(p0, p1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\MinimumDiameter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */