/*     */ package com.vividsolutions.jts.linearref;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ 
/*     */ public class LinearLocation implements Comparable {
/*     */   public static LinearLocation getEndLocation(Geometry linear) {
/*  56 */     LinearLocation loc = new LinearLocation();
/*  57 */     loc.setToEnd(linear);
/*  58 */     return loc;
/*     */   }
/*     */   
/*     */   public static Coordinate pointAlongSegmentByFraction(Coordinate p0, Coordinate p1, double frac) {
/*  78 */     if (frac <= 0.0D)
/*  78 */       return p0; 
/*  79 */     if (frac >= 1.0D)
/*  79 */       return p1; 
/*  81 */     double x = (p1.x - p0.x) * frac + p0.x;
/*  82 */     double y = (p1.y - p0.y) * frac + p0.y;
/*  84 */     double z = (p1.z - p0.z) * frac + p0.z;
/*  85 */     return new Coordinate(x, y, z);
/*     */   }
/*     */   
/*  88 */   private int componentIndex = 0;
/*     */   
/*  89 */   private int segmentIndex = 0;
/*     */   
/*  90 */   private double segmentFraction = 0.0D;
/*     */   
/*     */   public LinearLocation() {}
/*     */   
/*     */   public LinearLocation(int segmentIndex, double segmentFraction) {
/* 100 */     this(0, segmentIndex, segmentFraction);
/*     */   }
/*     */   
/*     */   public LinearLocation(int componentIndex, int segmentIndex, double segmentFraction) {
/* 105 */     this.componentIndex = componentIndex;
/* 106 */     this.segmentIndex = segmentIndex;
/* 107 */     this.segmentFraction = segmentFraction;
/* 108 */     normalize();
/*     */   }
/*     */   
/*     */   private LinearLocation(int componentIndex, int segmentIndex, double segmentFraction, boolean doNormalize) {
/* 113 */     this.componentIndex = componentIndex;
/* 114 */     this.segmentIndex = segmentIndex;
/* 115 */     this.segmentFraction = segmentFraction;
/* 116 */     if (doNormalize)
/* 117 */       normalize(); 
/*     */   }
/*     */   
/*     */   public LinearLocation(LinearLocation loc) {
/* 127 */     this.componentIndex = loc.componentIndex;
/* 128 */     this.segmentIndex = loc.segmentIndex;
/* 129 */     this.segmentFraction = loc.segmentFraction;
/*     */   }
/*     */   
/*     */   private void normalize() {
/* 141 */     if (this.segmentFraction < 0.0D)
/* 142 */       this.segmentFraction = 0.0D; 
/* 144 */     if (this.segmentFraction > 1.0D)
/* 145 */       this.segmentFraction = 1.0D; 
/* 148 */     if (this.componentIndex < 0) {
/* 149 */       this.componentIndex = 0;
/* 150 */       this.segmentIndex = 0;
/* 151 */       this.segmentFraction = 0.0D;
/*     */     } 
/* 153 */     if (this.segmentIndex < 0) {
/* 154 */       this.segmentIndex = 0;
/* 155 */       this.segmentFraction = 0.0D;
/*     */     } 
/* 157 */     if (this.segmentFraction == 1.0D) {
/* 158 */       this.segmentFraction = 0.0D;
/* 159 */       this.segmentIndex++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clamp(Geometry linear) {
/* 171 */     if (this.componentIndex >= linear.getNumGeometries()) {
/* 172 */       setToEnd(linear);
/*     */       return;
/*     */     } 
/* 175 */     if (this.segmentIndex >= linear.getNumPoints()) {
/* 176 */       LineString line = (LineString)linear.getGeometryN(this.componentIndex);
/* 177 */       this.segmentIndex = line.getNumPoints() - 1;
/* 178 */       this.segmentFraction = 1.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void snapToVertex(Geometry linearGeom, double minDistance) {
/* 191 */     if (this.segmentFraction <= 0.0D || this.segmentFraction >= 1.0D)
/*     */       return; 
/* 193 */     double segLen = getSegmentLength(linearGeom);
/* 194 */     double lenToStart = this.segmentFraction * segLen;
/* 195 */     double lenToEnd = segLen - lenToStart;
/* 196 */     if (lenToStart <= lenToEnd && lenToStart < minDistance) {
/* 197 */       this.segmentFraction = 0.0D;
/* 199 */     } else if (lenToEnd <= lenToStart && lenToEnd < minDistance) {
/* 200 */       this.segmentFraction = 1.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getSegmentLength(Geometry linearGeom) {
/* 213 */     LineString lineComp = (LineString)linearGeom.getGeometryN(this.componentIndex);
/* 216 */     int segIndex = this.segmentIndex;
/* 217 */     if (this.segmentIndex >= lineComp.getNumPoints() - 1)
/* 218 */       segIndex = lineComp.getNumPoints() - 2; 
/* 220 */     Coordinate p0 = lineComp.getCoordinateN(segIndex);
/* 221 */     Coordinate p1 = lineComp.getCoordinateN(segIndex + 1);
/* 222 */     return p0.distance(p1);
/*     */   }
/*     */   
/*     */   public void setToEnd(Geometry linear) {
/* 233 */     this.componentIndex = linear.getNumGeometries() - 1;
/* 234 */     LineString lastLine = (LineString)linear.getGeometryN(this.componentIndex);
/* 235 */     this.segmentIndex = lastLine.getNumPoints() - 1;
/* 236 */     this.segmentFraction = 1.0D;
/*     */   }
/*     */   
/*     */   public int getComponentIndex() {
/* 244 */     return this.componentIndex;
/*     */   }
/*     */   
/*     */   public int getSegmentIndex() {
/* 251 */     return this.segmentIndex;
/*     */   }
/*     */   
/*     */   public double getSegmentFraction() {
/* 258 */     return this.segmentFraction;
/*     */   }
/*     */   
/*     */   public boolean isVertex() {
/* 267 */     return (this.segmentFraction <= 0.0D || this.segmentFraction >= 1.0D);
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(Geometry linearGeom) {
/* 280 */     LineString lineComp = (LineString)linearGeom.getGeometryN(this.componentIndex);
/* 281 */     Coordinate p0 = lineComp.getCoordinateN(this.segmentIndex);
/* 282 */     if (this.segmentIndex >= lineComp.getNumPoints() - 1)
/* 283 */       return p0; 
/* 284 */     Coordinate p1 = lineComp.getCoordinateN(this.segmentIndex + 1);
/* 285 */     return pointAlongSegmentByFraction(p0, p1, this.segmentFraction);
/*     */   }
/*     */   
/*     */   public LineSegment getSegment(Geometry linearGeom) {
/* 297 */     LineString lineComp = (LineString)linearGeom.getGeometryN(this.componentIndex);
/* 298 */     Coordinate p0 = lineComp.getCoordinateN(this.segmentIndex);
/* 300 */     if (this.segmentIndex >= lineComp.getNumPoints() - 1) {
/* 301 */       Coordinate prev = lineComp.getCoordinateN(lineComp.getNumPoints() - 2);
/* 302 */       return new LineSegment(prev, p0);
/*     */     } 
/* 304 */     Coordinate p1 = lineComp.getCoordinateN(this.segmentIndex + 1);
/* 305 */     return new LineSegment(p0, p1);
/*     */   }
/*     */   
/*     */   public boolean isValid(Geometry linearGeom) {
/* 317 */     if (this.componentIndex < 0 || this.componentIndex >= linearGeom.getNumGeometries())
/* 318 */       return false; 
/* 320 */     LineString lineComp = (LineString)linearGeom.getGeometryN(this.componentIndex);
/* 321 */     if (this.segmentIndex < 0 || this.segmentIndex > lineComp.getNumPoints())
/* 322 */       return false; 
/* 323 */     if (this.segmentIndex == lineComp.getNumPoints() && this.segmentFraction != 0.0D)
/* 324 */       return false; 
/* 326 */     if (this.segmentFraction < 0.0D || this.segmentFraction > 1.0D)
/* 327 */       return false; 
/* 328 */     return true;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o) {
/* 340 */     LinearLocation other = (LinearLocation)o;
/* 342 */     if (this.componentIndex < other.componentIndex)
/* 342 */       return -1; 
/* 343 */     if (this.componentIndex > other.componentIndex)
/* 343 */       return 1; 
/* 345 */     if (this.segmentIndex < other.segmentIndex)
/* 345 */       return -1; 
/* 346 */     if (this.segmentIndex > other.segmentIndex)
/* 346 */       return 1; 
/* 348 */     if (this.segmentFraction < other.segmentFraction)
/* 348 */       return -1; 
/* 349 */     if (this.segmentFraction > other.segmentFraction)
/* 349 */       return 1; 
/* 351 */     return 0;
/*     */   }
/*     */   
/*     */   public int compareLocationValues(int componentIndex1, int segmentIndex1, double segmentFraction1) {
/* 365 */     if (this.componentIndex < componentIndex1)
/* 365 */       return -1; 
/* 366 */     if (this.componentIndex > componentIndex1)
/* 366 */       return 1; 
/* 368 */     if (this.segmentIndex < segmentIndex1)
/* 368 */       return -1; 
/* 369 */     if (this.segmentIndex > segmentIndex1)
/* 369 */       return 1; 
/* 371 */     if (this.segmentFraction < segmentFraction1)
/* 371 */       return -1; 
/* 372 */     if (this.segmentFraction > segmentFraction1)
/* 372 */       return 1; 
/* 374 */     return 0;
/*     */   }
/*     */   
/*     */   public static int compareLocationValues(int componentIndex0, int segmentIndex0, double segmentFraction0, int componentIndex1, int segmentIndex1, double segmentFraction1) {
/* 395 */     if (componentIndex0 < componentIndex1)
/* 395 */       return -1; 
/* 396 */     if (componentIndex0 > componentIndex1)
/* 396 */       return 1; 
/* 398 */     if (segmentIndex0 < segmentIndex1)
/* 398 */       return -1; 
/* 399 */     if (segmentIndex0 > segmentIndex1)
/* 399 */       return 1; 
/* 401 */     if (segmentFraction0 < segmentFraction1)
/* 401 */       return -1; 
/* 402 */     if (segmentFraction0 > segmentFraction1)
/* 402 */       return 1; 
/* 404 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isOnSameSegment(LinearLocation loc) {
/* 416 */     if (this.componentIndex != loc.componentIndex)
/* 416 */       return false; 
/* 417 */     if (this.segmentIndex == loc.segmentIndex)
/* 417 */       return true; 
/* 418 */     if (loc.segmentIndex - this.segmentIndex == 1 && loc.segmentFraction == 0.0D)
/* 420 */       return true; 
/* 421 */     if (this.segmentIndex - loc.segmentIndex == 1 && this.segmentFraction == 0.0D)
/* 423 */       return true; 
/* 424 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEndpoint(Geometry linearGeom) {
/* 436 */     LineString lineComp = (LineString)linearGeom.getGeometryN(this.componentIndex);
/* 438 */     int nseg = lineComp.getNumPoints() - 1;
/* 439 */     return (this.segmentIndex >= nseg || (this.segmentIndex == nseg && this.segmentFraction >= 1.0D));
/*     */   }
/*     */   
/*     */   public LinearLocation toLowest(Geometry linearGeom) {
/* 460 */     LineString lineComp = (LineString)linearGeom.getGeometryN(this.componentIndex);
/* 461 */     int nseg = lineComp.getNumPoints() - 1;
/* 463 */     if (this.segmentIndex < nseg)
/* 463 */       return this; 
/* 464 */     return new LinearLocation(this.componentIndex, nseg, 1.0D, false);
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 474 */     return new LinearLocation(this.componentIndex, this.segmentIndex, this.segmentFraction);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 479 */     return "LinearLoc[" + this.componentIndex + ", " + this.segmentIndex + ", " + this.segmentFraction + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\linearref\LinearLocation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */