/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Triangle;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public class MinimumBoundingCircle {
/*     */   private Geometry input;
/*     */   
/*  79 */   private Coordinate[] extremalPts = null;
/*     */   
/*  80 */   private Coordinate centre = null;
/*     */   
/*  81 */   private double radius = 0.0D;
/*     */   
/*     */   public MinimumBoundingCircle(Geometry geom) {
/*  91 */     this.input = geom;
/*     */   }
/*     */   
/*     */   public Geometry getCircle() {
/* 110 */     compute();
/* 111 */     if (this.centre == null)
/* 112 */       return (Geometry)this.input.getFactory().createPolygon(null, null); 
/* 113 */     Point centrePoint = this.input.getFactory().createPoint(this.centre);
/* 114 */     if (this.radius == 0.0D)
/* 115 */       return (Geometry)centrePoint; 
/* 116 */     return centrePoint.buffer(this.radius);
/*     */   }
/*     */   
/*     */   public Coordinate[] getExtremalPoints() {
/* 129 */     compute();
/* 130 */     return this.extremalPts;
/*     */   }
/*     */   
/*     */   public Coordinate getCentre() {
/* 141 */     compute();
/* 142 */     return this.centre;
/*     */   }
/*     */   
/*     */   public double getRadius() {
/* 152 */     compute();
/* 153 */     return this.radius;
/*     */   }
/*     */   
/*     */   private void computeCentre() {
/* 158 */     switch (this.extremalPts.length) {
/*     */       case 0:
/* 160 */         this.centre = null;
/*     */         break;
/*     */       case 1:
/* 163 */         this.centre = this.extremalPts[0];
/*     */         break;
/*     */       case 2:
/* 166 */         this.centre = new Coordinate(((this.extremalPts[0]).x + (this.extremalPts[1]).x) / 2.0D, ((this.extremalPts[0]).y + (this.extremalPts[1]).y) / 2.0D);
/*     */         break;
/*     */       case 3:
/* 172 */         this.centre = Triangle.circumcentre(this.extremalPts[0], this.extremalPts[1], this.extremalPts[2]);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void compute() {
/* 179 */     if (this.extremalPts != null)
/*     */       return; 
/* 181 */     computeCirclePoints();
/* 182 */     computeCentre();
/* 183 */     if (this.centre != null)
/* 184 */       this.radius = this.centre.distance(this.extremalPts[0]); 
/*     */   }
/*     */   
/*     */   private void computeCirclePoints() {
/* 190 */     if (this.input.isEmpty()) {
/* 191 */       this.extremalPts = new Coordinate[0];
/*     */       return;
/*     */     } 
/* 194 */     if (this.input.getNumPoints() == 1) {
/* 195 */       Coordinate[] arrayOfCoordinate = this.input.getCoordinates();
/* 196 */       this.extremalPts = new Coordinate[] { new Coordinate(arrayOfCoordinate[0]) };
/*     */       return;
/*     */     } 
/* 204 */     Geometry convexHull = this.input.convexHull();
/* 206 */     Coordinate[] hullPts = convexHull.getCoordinates();
/* 209 */     Coordinate[] pts = hullPts;
/* 210 */     if (hullPts[0].equals2D(hullPts[hullPts.length - 1])) {
/* 211 */       pts = new Coordinate[hullPts.length - 1];
/* 212 */       CoordinateArrays.copyDeep(hullPts, 0, pts, 0, hullPts.length - 1);
/*     */     } 
/* 218 */     if (pts.length <= 2) {
/* 219 */       this.extremalPts = CoordinateArrays.copyDeep(pts);
/*     */       return;
/*     */     } 
/* 224 */     Coordinate P = lowestPoint(pts);
/* 227 */     Coordinate Q = pointWitMinAngleWithX(pts, P);
/* 236 */     for (int i = 0; i < pts.length; i++) {
/* 237 */       Coordinate R = pointWithMinAngleWithSegment(pts, P, Q);
/* 240 */       if (Angle.isObtuse(P, R, Q)) {
/* 241 */         this.extremalPts = new Coordinate[] { new Coordinate(P), new Coordinate(Q) };
/*     */         return;
/*     */       } 
/* 245 */       if (Angle.isObtuse(R, P, Q)) {
/* 246 */         P = R;
/* 250 */       } else if (Angle.isObtuse(R, Q, P)) {
/* 251 */         Q = R;
/*     */       } else {
/* 255 */         this.extremalPts = new Coordinate[] { new Coordinate(P), new Coordinate(Q), new Coordinate(R) };
/*     */         return;
/*     */       } 
/*     */     } 
/* 258 */     Assert.shouldNeverReachHere("Logic failure in Minimum Bounding Circle algorithm!");
/*     */   }
/*     */   
/*     */   private static Coordinate lowestPoint(Coordinate[] pts) {
/* 263 */     Coordinate min = pts[0];
/* 264 */     for (int i = 1; i < pts.length; i++) {
/* 265 */       if ((pts[i]).y < min.y)
/* 266 */         min = pts[i]; 
/*     */     } 
/* 268 */     return min;
/*     */   }
/*     */   
/*     */   private static Coordinate pointWitMinAngleWithX(Coordinate[] pts, Coordinate P) {
/* 273 */     double minSin = Double.MAX_VALUE;
/* 274 */     Coordinate minAngPt = null;
/* 275 */     for (int i = 0; i < pts.length; i++) {
/* 277 */       Coordinate p = pts[i];
/* 278 */       if (p != P) {
/* 283 */         double dx = p.x - P.x;
/* 284 */         double dy = p.y - P.y;
/* 285 */         if (dy < 0.0D)
/* 285 */           dy = -dy; 
/* 286 */         double len = Math.sqrt(dx * dx + dy * dy);
/* 287 */         double sin = dy / len;
/* 289 */         if (sin < minSin) {
/* 290 */           minSin = sin;
/* 291 */           minAngPt = p;
/*     */         } 
/*     */       } 
/*     */     } 
/* 294 */     return minAngPt;
/*     */   }
/*     */   
/*     */   private static Coordinate pointWithMinAngleWithSegment(Coordinate[] pts, Coordinate P, Coordinate Q) {
/* 299 */     double minAng = Double.MAX_VALUE;
/* 300 */     Coordinate minAngPt = null;
/* 301 */     for (int i = 0; i < pts.length; i++) {
/* 303 */       Coordinate p = pts[i];
/* 304 */       if (p != P && 
/* 305 */         p != Q) {
/* 307 */         double ang = Angle.angleBetween(P, p, Q);
/* 308 */         if (ang < minAng) {
/* 309 */           minAng = ang;
/* 310 */           minAngPt = p;
/*     */         } 
/*     */       } 
/*     */     } 
/* 313 */     return minAngPt;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\MinimumBoundingCircle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */