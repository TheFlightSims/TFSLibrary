/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.math.MathUtil;
/*     */ 
/*     */ public class CGAlgorithms {
/*     */   public static final int CLOCKWISE = -1;
/*     */   
/*     */   public static final int RIGHT = -1;
/*     */   
/*     */   public static final int COUNTERCLOCKWISE = 1;
/*     */   
/*     */   public static final int LEFT = 1;
/*     */   
/*     */   public static final int COLLINEAR = 0;
/*     */   
/*     */   public static final int STRAIGHT = 0;
/*     */   
/*     */   public static int orientationIndex(Coordinate p1, Coordinate p2, Coordinate q) {
/* 117 */     return CGAlgorithmsDD.orientationIndex(p1, p2, q);
/*     */   }
/*     */   
/*     */   public static boolean isPointInRing(Coordinate p, Coordinate[] ring) {
/* 148 */     return (locatePointInRing(p, ring) != 2);
/*     */   }
/*     */   
/*     */   public static int locatePointInRing(Coordinate p, Coordinate[] ring) {
/* 167 */     return RayCrossingCounter.locatePointInRing(p, ring);
/*     */   }
/*     */   
/*     */   public static boolean isOnLine(Coordinate p, Coordinate[] pt) {
/* 179 */     LineIntersector lineIntersector = new RobustLineIntersector();
/* 180 */     for (int i = 1; i < pt.length; i++) {
/* 181 */       Coordinate p0 = pt[i - 1];
/* 182 */       Coordinate p1 = pt[i];
/* 183 */       lineIntersector.computeIntersection(p, p0, p1);
/* 184 */       if (lineIntersector.hasIntersection())
/* 185 */         return true; 
/*     */     } 
/* 188 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isCCW(Coordinate[] ring) {
/* 211 */     int nPts = ring.length - 1;
/* 213 */     if (nPts < 3)
/* 214 */       throw new IllegalArgumentException("Ring has fewer than 4 points, so orientation cannot be determined"); 
/* 218 */     Coordinate hiPt = ring[0];
/* 219 */     int hiIndex = 0;
/* 220 */     for (int i = 1; i <= nPts; i++) {
/* 221 */       Coordinate p = ring[i];
/* 222 */       if (p.y > hiPt.y) {
/* 223 */         hiPt = p;
/* 224 */         hiIndex = i;
/*     */       } 
/*     */     } 
/* 229 */     int iPrev = hiIndex;
/*     */     do {
/* 231 */       iPrev--;
/* 232 */       if (iPrev >= 0)
/*     */         continue; 
/* 233 */       iPrev = nPts;
/* 234 */     } while (ring[iPrev].equals2D(hiPt) && iPrev != hiIndex);
/* 237 */     int iNext = hiIndex;
/*     */     do {
/* 239 */       iNext = (iNext + 1) % nPts;
/* 240 */     } while (ring[iNext].equals2D(hiPt) && iNext != hiIndex);
/* 242 */     Coordinate prev = ring[iPrev];
/* 243 */     Coordinate next = ring[iNext];
/* 251 */     if (prev.equals2D(hiPt) || next.equals2D(hiPt) || prev.equals2D(next))
/* 252 */       return false; 
/* 254 */     int disc = computeOrientation(prev, hiPt, next);
/* 265 */     boolean isCCW = false;
/* 266 */     if (disc == 0) {
/* 268 */       isCCW = (prev.x > next.x);
/*     */     } else {
/* 272 */       isCCW = (disc > 0);
/*     */     } 
/* 274 */     return isCCW;
/*     */   }
/*     */   
/*     */   public static int computeOrientation(Coordinate p1, Coordinate p2, Coordinate q) {
/* 292 */     return orientationIndex(p1, p2, q);
/*     */   }
/*     */   
/*     */   public static double distancePointLine(Coordinate p, Coordinate A, Coordinate B) {
/* 312 */     if (A.x == B.x && A.y == B.y)
/* 313 */       return p.distance(A); 
/* 329 */     double len2 = (B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y);
/* 330 */     double r = ((p.x - A.x) * (B.x - A.x) + (p.y - A.y) * (B.y - A.y)) / len2;
/* 333 */     if (r <= 0.0D)
/* 334 */       return p.distance(A); 
/* 335 */     if (r >= 1.0D)
/* 336 */       return p.distance(B); 
/* 348 */     double s = ((A.y - p.y) * (B.x - A.x) - (A.x - p.x) * (B.y - A.y)) / len2;
/* 350 */     return Math.abs(s) * Math.sqrt(len2);
/*     */   }
/*     */   
/*     */   public static double distancePointLinePerpendicular(Coordinate p, Coordinate A, Coordinate B) {
/* 376 */     double len2 = (B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y);
/* 377 */     double s = ((A.y - p.y) * (B.x - A.x) - (A.x - p.x) * (B.y - A.y)) / len2;
/* 380 */     return Math.abs(s) * Math.sqrt(len2);
/*     */   }
/*     */   
/*     */   public static double distancePointLine(Coordinate p, Coordinate[] line) {
/* 394 */     if (line.length == 0)
/* 395 */       throw new IllegalArgumentException("Line array must contain at least one vertex"); 
/* 398 */     double minDistance = p.distance(line[0]);
/* 399 */     for (int i = 0; i < line.length - 1; i++) {
/* 400 */       double dist = distancePointLine(p, line[i], line[i + 1]);
/* 401 */       if (dist < minDistance)
/* 402 */         minDistance = dist; 
/*     */     } 
/* 405 */     return minDistance;
/*     */   }
/*     */   
/*     */   public static double distanceLineLine(Coordinate A, Coordinate B, Coordinate C, Coordinate D) {
/* 426 */     if (A.equals(B))
/* 427 */       return distancePointLine(A, C, D); 
/* 428 */     if (C.equals(D))
/* 429 */       return distancePointLine(D, A, B); 
/* 458 */     boolean noIntersection = false;
/* 459 */     if (!Envelope.intersects(A, B, C, D)) {
/* 460 */       noIntersection = true;
/*     */     } else {
/* 463 */       double denom = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);
/* 465 */       if (denom == 0.0D) {
/* 466 */         noIntersection = true;
/*     */       } else {
/* 469 */         double r_num = (A.y - C.y) * (D.x - C.x) - (A.x - C.x) * (D.y - C.y);
/* 470 */         double s_num = (A.y - C.y) * (B.x - A.x) - (A.x - C.x) * (B.y - A.y);
/* 472 */         double s = s_num / denom;
/* 473 */         double r = r_num / denom;
/* 475 */         if (r < 0.0D || r > 1.0D || s < 0.0D || s > 1.0D)
/* 476 */           noIntersection = true; 
/*     */       } 
/*     */     } 
/* 480 */     if (noIntersection)
/* 481 */       return MathUtil.min(distancePointLine(A, C, D), distancePointLine(B, C, D), distancePointLine(C, A, B), distancePointLine(D, A, B)); 
/* 488 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public static double signedArea(Coordinate[] ring) {
/* 502 */     if (ring.length < 3)
/* 503 */       return 0.0D; 
/* 504 */     double sum = 0.0D;
/* 509 */     double x0 = (ring[0]).x;
/* 510 */     for (int i = 1; i < ring.length - 1; i++) {
/* 511 */       double x = (ring[i]).x - x0;
/* 512 */       double y1 = (ring[i + 1]).y;
/* 513 */       double y2 = (ring[i - 1]).y;
/* 514 */       sum += x * (y2 - y1);
/*     */     } 
/* 516 */     return sum / 2.0D;
/*     */   }
/*     */   
/*     */   public static double signedArea(CoordinateSequence ring) {
/* 533 */     int n = ring.size();
/* 534 */     if (n < 3)
/* 535 */       return 0.0D; 
/* 540 */     Coordinate p0 = new Coordinate();
/* 541 */     Coordinate p1 = new Coordinate();
/* 542 */     Coordinate p2 = new Coordinate();
/* 543 */     ring.getCoordinate(0, p1);
/* 544 */     ring.getCoordinate(1, p2);
/* 545 */     double x0 = p1.x;
/* 546 */     p2.x -= x0;
/* 547 */     double sum = 0.0D;
/* 548 */     for (int i = 1; i < n - 1; i++) {
/* 549 */       p0.y = p1.y;
/* 550 */       p1.x = p2.x;
/* 551 */       p1.y = p2.y;
/* 552 */       ring.getCoordinate(i + 1, p2);
/* 553 */       p2.x -= x0;
/* 554 */       sum += p1.x * (p0.y - p2.y);
/*     */     } 
/* 556 */     return sum / 2.0D;
/*     */   }
/*     */   
/*     */   public static double length(CoordinateSequence pts) {
/* 569 */     int n = pts.size();
/* 570 */     if (n <= 1)
/* 571 */       return 0.0D; 
/* 573 */     double len = 0.0D;
/* 575 */     Coordinate p = new Coordinate();
/* 576 */     pts.getCoordinate(0, p);
/* 577 */     double x0 = p.x;
/* 578 */     double y0 = p.y;
/* 580 */     for (int i = 1; i < n; i++) {
/* 581 */       pts.getCoordinate(i, p);
/* 582 */       double x1 = p.x;
/* 583 */       double y1 = p.y;
/* 584 */       double dx = x1 - x0;
/* 585 */       double dy = y1 - y0;
/* 587 */       len += Math.sqrt(dx * dx + dy * dy);
/* 589 */       x0 = x1;
/* 590 */       y0 = y1;
/*     */     } 
/* 592 */     return len;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\CGAlgorithms.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */