/*     */ package com.vividsolutions.jts.noding.snapround;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.noding.NodedSegmentString;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public class HotPixel {
/*     */   private LineIntersector li;
/*     */   
/*     */   private Coordinate pt;
/*     */   
/*     */   private Coordinate originalPt;
/*     */   
/*     */   private Coordinate ptScaled;
/*     */   
/*     */   private Coordinate p0Scaled;
/*     */   
/*     */   private Coordinate p1Scaled;
/*     */   
/*     */   private double scaleFactor;
/*     */   
/*     */   private double minx;
/*     */   
/*     */   private double maxx;
/*     */   
/*     */   private double miny;
/*     */   
/*     */   private double maxy;
/*     */   
/*  78 */   private Coordinate[] corner = new Coordinate[4];
/*     */   
/*  80 */   private Envelope safeEnv = null;
/*     */   
/*     */   private static final double SAFE_ENV_EXPANSION_FACTOR = 0.75D;
/*     */   
/*     */   public HotPixel(Coordinate pt, double scaleFactor, LineIntersector li) {
/*  92 */     this.originalPt = pt;
/*  93 */     this.pt = pt;
/*  94 */     this.scaleFactor = scaleFactor;
/*  95 */     this.li = li;
/*  97 */     if (scaleFactor <= 0.0D)
/*  98 */       throw new IllegalArgumentException("Scale factor must be non-zero"); 
/*  99 */     if (scaleFactor != 1.0D) {
/* 100 */       this.pt = new Coordinate(scale(pt.x), scale(pt.y));
/* 101 */       this.p0Scaled = new Coordinate();
/* 102 */       this.p1Scaled = new Coordinate();
/*     */     } 
/* 104 */     initCorners(this.pt);
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/* 112 */     return this.originalPt;
/*     */   }
/*     */   
/*     */   public Envelope getSafeEnvelope() {
/* 125 */     if (this.safeEnv == null) {
/* 126 */       double safeTolerance = 0.75D / this.scaleFactor;
/* 127 */       this.safeEnv = new Envelope(this.originalPt.x - safeTolerance, this.originalPt.x + safeTolerance, this.originalPt.y - safeTolerance, this.originalPt.y + safeTolerance);
/*     */     } 
/* 133 */     return this.safeEnv;
/*     */   }
/*     */   
/*     */   private void initCorners(Coordinate pt) {
/* 138 */     double tolerance = 0.5D;
/* 139 */     this.minx = pt.x - tolerance;
/* 140 */     this.maxx = pt.x + tolerance;
/* 141 */     this.miny = pt.y - tolerance;
/* 142 */     this.maxy = pt.y + tolerance;
/* 144 */     this.corner[0] = new Coordinate(this.maxx, this.maxy);
/* 145 */     this.corner[1] = new Coordinate(this.minx, this.maxy);
/* 146 */     this.corner[2] = new Coordinate(this.minx, this.miny);
/* 147 */     this.corner[3] = new Coordinate(this.maxx, this.miny);
/*     */   }
/*     */   
/*     */   private double scale(double val) {
/* 152 */     return Math.round(val * this.scaleFactor);
/*     */   }
/*     */   
/*     */   public boolean intersects(Coordinate p0, Coordinate p1) {
/* 165 */     if (this.scaleFactor == 1.0D)
/* 166 */       return intersectsScaled(p0, p1); 
/* 168 */     copyScaled(p0, this.p0Scaled);
/* 169 */     copyScaled(p1, this.p1Scaled);
/* 170 */     return intersectsScaled(this.p0Scaled, this.p1Scaled);
/*     */   }
/*     */   
/*     */   private void copyScaled(Coordinate p, Coordinate pScaled) {
/* 175 */     pScaled.x = scale(p.x);
/* 176 */     pScaled.y = scale(p.y);
/*     */   }
/*     */   
/*     */   private boolean intersectsScaled(Coordinate p0, Coordinate p1) {
/* 181 */     double segMinx = Math.min(p0.x, p1.x);
/* 182 */     double segMaxx = Math.max(p0.x, p1.x);
/* 183 */     double segMiny = Math.min(p0.y, p1.y);
/* 184 */     double segMaxy = Math.max(p0.y, p1.y);
/* 186 */     boolean isOutsidePixelEnv = (this.maxx < segMinx || this.minx > segMaxx || this.maxy < segMiny || this.miny > segMaxy);
/* 190 */     if (isOutsidePixelEnv)
/* 191 */       return false; 
/* 192 */     boolean intersects = intersectsToleranceSquare(p0, p1);
/* 212 */     Assert.isTrue((!isOutsidePixelEnv || !intersects), "Found bad envelope test");
/* 217 */     return intersects;
/*     */   }
/*     */   
/*     */   private boolean intersectsToleranceSquare(Coordinate p0, Coordinate p1) {
/* 242 */     boolean intersectsLeft = false;
/* 243 */     boolean intersectsBottom = false;
/* 245 */     this.li.computeIntersection(p0, p1, this.corner[0], this.corner[1]);
/* 246 */     if (this.li.isProper())
/* 246 */       return true; 
/* 248 */     this.li.computeIntersection(p0, p1, this.corner[1], this.corner[2]);
/* 249 */     if (this.li.isProper())
/* 249 */       return true; 
/* 250 */     if (this.li.hasIntersection())
/* 250 */       intersectsLeft = true; 
/* 252 */     this.li.computeIntersection(p0, p1, this.corner[2], this.corner[3]);
/* 253 */     if (this.li.isProper())
/* 253 */       return true; 
/* 254 */     if (this.li.hasIntersection())
/* 254 */       intersectsBottom = true; 
/* 256 */     this.li.computeIntersection(p0, p1, this.corner[3], this.corner[0]);
/* 257 */     if (this.li.isProper())
/* 257 */       return true; 
/* 259 */     if (intersectsLeft && intersectsBottom)
/* 259 */       return true; 
/* 261 */     if (p0.equals(this.pt))
/* 261 */       return true; 
/* 262 */     if (p1.equals(this.pt))
/* 262 */       return true; 
/* 264 */     return false;
/*     */   }
/*     */   
/*     */   private boolean intersectsPixelClosure(Coordinate p0, Coordinate p1) {
/* 280 */     this.li.computeIntersection(p0, p1, this.corner[0], this.corner[1]);
/* 281 */     if (this.li.hasIntersection())
/* 281 */       return true; 
/* 282 */     this.li.computeIntersection(p0, p1, this.corner[1], this.corner[2]);
/* 283 */     if (this.li.hasIntersection())
/* 283 */       return true; 
/* 284 */     this.li.computeIntersection(p0, p1, this.corner[2], this.corner[3]);
/* 285 */     if (this.li.hasIntersection())
/* 285 */       return true; 
/* 286 */     this.li.computeIntersection(p0, p1, this.corner[3], this.corner[0]);
/* 287 */     if (this.li.hasIntersection())
/* 287 */       return true; 
/* 289 */     return false;
/*     */   }
/*     */   
/*     */   public boolean addSnappedNode(NodedSegmentString segStr, int segIndex) {
/* 305 */     Coordinate p0 = segStr.getCoordinate(segIndex);
/* 306 */     Coordinate p1 = segStr.getCoordinate(segIndex + 1);
/* 308 */     if (intersects(p0, p1)) {
/* 311 */       segStr.addIntersection(getCoordinate(), segIndex);
/* 313 */       return true;
/*     */     } 
/* 315 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\snapround\HotPixel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */