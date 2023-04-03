/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ 
/*     */ public class RobustLineIntersector extends LineIntersector {
/*     */   public void computeIntersection(Coordinate p, Coordinate p1, Coordinate p2) {
/*  58 */     this.isProper = false;
/*  60 */     if (Envelope.intersects(p1, p2, p) && 
/*  61 */       CGAlgorithms.orientationIndex(p1, p2, p) == 0 && CGAlgorithms.orientationIndex(p2, p1, p) == 0) {
/*  63 */       this.isProper = true;
/*  64 */       if (p.equals(p1) || p.equals(p2))
/*  65 */         this.isProper = false; 
/*  67 */       this.result = 1;
/*     */       return;
/*     */     } 
/*  71 */     this.result = 0;
/*     */   }
/*     */   
/*     */   protected int computeIntersect(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) {
/*  77 */     this.isProper = false;
/*  80 */     if (!Envelope.intersects(p1, p2, q1, q2))
/*  81 */       return 0; 
/*  86 */     int Pq1 = CGAlgorithms.orientationIndex(p1, p2, q1);
/*  87 */     int Pq2 = CGAlgorithms.orientationIndex(p1, p2, q2);
/*  89 */     if ((Pq1 > 0 && Pq2 > 0) || (Pq1 < 0 && Pq2 < 0))
/*  90 */       return 0; 
/*  93 */     int Qp1 = CGAlgorithms.orientationIndex(q1, q2, p1);
/*  94 */     int Qp2 = CGAlgorithms.orientationIndex(q1, q2, p2);
/*  96 */     if ((Qp1 > 0 && Qp2 > 0) || (Qp1 < 0 && Qp2 < 0))
/*  97 */       return 0; 
/* 100 */     boolean collinear = (Pq1 == 0 && Pq2 == 0 && Qp1 == 0 && Qp2 == 0);
/* 104 */     if (collinear)
/* 105 */       return computeCollinearIntersection(p1, p2, q1, q2); 
/* 121 */     if (Pq1 == 0 || Pq2 == 0 || Qp1 == 0 || Qp2 == 0) {
/* 122 */       this.isProper = false;
/* 140 */       if (p1.equals2D(q1) || p1.equals2D(q2)) {
/* 142 */         this.intPt[0] = p1;
/* 144 */       } else if (p2.equals2D(q1) || p2.equals2D(q2)) {
/* 146 */         this.intPt[0] = p2;
/* 152 */       } else if (Pq1 == 0) {
/* 153 */         this.intPt[0] = new Coordinate(q1);
/* 155 */       } else if (Pq2 == 0) {
/* 156 */         this.intPt[0] = new Coordinate(q2);
/* 158 */       } else if (Qp1 == 0) {
/* 159 */         this.intPt[0] = new Coordinate(p1);
/* 161 */       } else if (Qp2 == 0) {
/* 162 */         this.intPt[0] = new Coordinate(p2);
/*     */       } 
/*     */     } else {
/* 166 */       this.isProper = true;
/* 167 */       this.intPt[0] = intersection(p1, p2, q1, q2);
/*     */     } 
/* 169 */     return 1;
/*     */   }
/*     */   
/*     */   private int computeCollinearIntersection(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) {
/* 174 */     boolean p1q1p2 = Envelope.intersects(p1, p2, q1);
/* 175 */     boolean p1q2p2 = Envelope.intersects(p1, p2, q2);
/* 176 */     boolean q1p1q2 = Envelope.intersects(q1, q2, p1);
/* 177 */     boolean q1p2q2 = Envelope.intersects(q1, q2, p2);
/* 179 */     if (p1q1p2 && p1q2p2) {
/* 180 */       this.intPt[0] = q1;
/* 181 */       this.intPt[1] = q2;
/* 182 */       return 2;
/*     */     } 
/* 184 */     if (q1p1q2 && q1p2q2) {
/* 185 */       this.intPt[0] = p1;
/* 186 */       this.intPt[1] = p2;
/* 187 */       return 2;
/*     */     } 
/* 189 */     if (p1q1p2 && q1p1q2) {
/* 190 */       this.intPt[0] = q1;
/* 191 */       this.intPt[1] = p1;
/* 192 */       return (q1.equals(p1) && !p1q2p2 && !q1p2q2) ? 1 : 2;
/*     */     } 
/* 194 */     if (p1q1p2 && q1p2q2) {
/* 195 */       this.intPt[0] = q1;
/* 196 */       this.intPt[1] = p2;
/* 197 */       return (q1.equals(p2) && !p1q2p2 && !q1p1q2) ? 1 : 2;
/*     */     } 
/* 199 */     if (p1q2p2 && q1p1q2) {
/* 200 */       this.intPt[0] = q2;
/* 201 */       this.intPt[1] = p1;
/* 202 */       return (q2.equals(p1) && !p1q1p2 && !q1p2q2) ? 1 : 2;
/*     */     } 
/* 204 */     if (p1q2p2 && q1p2q2) {
/* 205 */       this.intPt[0] = q2;
/* 206 */       this.intPt[1] = p2;
/* 207 */       return (q2.equals(p2) && !p1q1p2 && !q1p1q2) ? 1 : 2;
/*     */     } 
/* 209 */     return 0;
/*     */   }
/*     */   
/*     */   private Coordinate intersection(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) {
/* 223 */     Coordinate intPt = intersectionWithNormalization(p1, p2, q1, q2);
/* 247 */     if (!isInSegmentEnvelopes(intPt))
/* 252 */       intPt = new Coordinate(nearestEndpoint(p1, p2, q1, q2)); 
/* 259 */     if (this.precisionModel != null)
/* 260 */       this.precisionModel.makePrecise(intPt); 
/* 262 */     return intPt;
/*     */   }
/*     */   
/*     */   private void checkDD(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2, Coordinate intPt) {
/* 268 */     Coordinate intPtDD = CGAlgorithmsDD.intersection(p1, p2, q1, q2);
/* 269 */     boolean isIn = isInSegmentEnvelopes(intPtDD);
/* 270 */     System.out.println("DD in env = " + isIn + "  --------------------- " + intPtDD);
/* 271 */     if (intPt.distance(intPtDD) > 1.0E-4D)
/* 272 */       System.out.println("Distance = " + intPt.distance(intPtDD)); 
/*     */   }
/*     */   
/*     */   private Coordinate intersectionWithNormalization(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) {
/* 279 */     Coordinate n1 = new Coordinate(p1);
/* 280 */     Coordinate n2 = new Coordinate(p2);
/* 281 */     Coordinate n3 = new Coordinate(q1);
/* 282 */     Coordinate n4 = new Coordinate(q2);
/* 283 */     Coordinate normPt = new Coordinate();
/* 284 */     normalizeToEnvCentre(n1, n2, n3, n4, normPt);
/* 286 */     Coordinate intPt = safeHCoordinateIntersection(n1, n2, n3, n4);
/* 288 */     intPt.x += normPt.x;
/* 289 */     intPt.y += normPt.y;
/* 291 */     return intPt;
/*     */   }
/*     */   
/*     */   private Coordinate safeHCoordinateIntersection(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) {
/* 308 */     Coordinate intPt = null;
/*     */     try {
/* 310 */       intPt = HCoordinate.intersection(p1, p2, q1, q2);
/* 312 */     } catch (NotRepresentableException e) {
/* 316 */       intPt = nearestEndpoint(p1, p2, q1, q2);
/*     */     } 
/* 319 */     return intPt;
/*     */   }
/*     */   
/*     */   private void normalizeToMinimum(Coordinate n1, Coordinate n2, Coordinate n3, Coordinate n4, Coordinate normPt) {
/* 341 */     normPt.x = smallestInAbsValue(n1.x, n2.x, n3.x, n4.x);
/* 342 */     normPt.y = smallestInAbsValue(n1.y, n2.y, n3.y, n4.y);
/* 343 */     n1.x -= normPt.x;
/* 343 */     n1.y -= normPt.y;
/* 344 */     n2.x -= normPt.x;
/* 344 */     n2.y -= normPt.y;
/* 345 */     n3.x -= normPt.x;
/* 345 */     n3.y -= normPt.y;
/* 346 */     n4.x -= normPt.x;
/* 346 */     n4.y -= normPt.y;
/*     */   }
/*     */   
/*     */   private void normalizeToEnvCentre(Coordinate n00, Coordinate n01, Coordinate n10, Coordinate n11, Coordinate normPt) {
/* 367 */     double minX0 = (n00.x < n01.x) ? n00.x : n01.x;
/* 368 */     double minY0 = (n00.y < n01.y) ? n00.y : n01.y;
/* 369 */     double maxX0 = (n00.x > n01.x) ? n00.x : n01.x;
/* 370 */     double maxY0 = (n00.y > n01.y) ? n00.y : n01.y;
/* 372 */     double minX1 = (n10.x < n11.x) ? n10.x : n11.x;
/* 373 */     double minY1 = (n10.y < n11.y) ? n10.y : n11.y;
/* 374 */     double maxX1 = (n10.x > n11.x) ? n10.x : n11.x;
/* 375 */     double maxY1 = (n10.y > n11.y) ? n10.y : n11.y;
/* 377 */     double intMinX = (minX0 > minX1) ? minX0 : minX1;
/* 378 */     double intMaxX = (maxX0 < maxX1) ? maxX0 : maxX1;
/* 379 */     double intMinY = (minY0 > minY1) ? minY0 : minY1;
/* 380 */     double intMaxY = (maxY0 < maxY1) ? maxY0 : maxY1;
/* 382 */     double intMidX = (intMinX + intMaxX) / 2.0D;
/* 383 */     double intMidY = (intMinY + intMaxY) / 2.0D;
/* 384 */     normPt.x = intMidX;
/* 385 */     normPt.y = intMidY;
/* 398 */     n00.x -= normPt.x;
/* 398 */     n00.y -= normPt.y;
/* 399 */     n01.x -= normPt.x;
/* 399 */     n01.y -= normPt.y;
/* 400 */     n10.x -= normPt.x;
/* 400 */     n10.y -= normPt.y;
/* 401 */     n11.x -= normPt.x;
/* 401 */     n11.y -= normPt.y;
/*     */   }
/*     */   
/*     */   private double smallestInAbsValue(double x1, double x2, double x3, double x4) {
/* 406 */     double x = x1;
/* 407 */     double xabs = Math.abs(x);
/* 408 */     if (Math.abs(x2) < xabs) {
/* 409 */       x = x2;
/* 410 */       xabs = Math.abs(x2);
/*     */     } 
/* 412 */     if (Math.abs(x3) < xabs) {
/* 413 */       x = x3;
/* 414 */       xabs = Math.abs(x3);
/*     */     } 
/* 416 */     if (Math.abs(x4) < xabs)
/* 417 */       x = x4; 
/* 419 */     return x;
/*     */   }
/*     */   
/*     */   private boolean isInSegmentEnvelopes(Coordinate intPt) {
/* 433 */     Envelope env0 = new Envelope(this.inputLines[0][0], this.inputLines[0][1]);
/* 434 */     Envelope env1 = new Envelope(this.inputLines[1][0], this.inputLines[1][1]);
/* 435 */     return (env0.contains(intPt) && env1.contains(intPt));
/*     */   }
/*     */   
/*     */   private static Coordinate nearestEndpoint(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) {
/* 460 */     Coordinate nearestPt = p1;
/* 461 */     double minDist = CGAlgorithms.distancePointLine(p1, q1, q2);
/* 463 */     double dist = CGAlgorithms.distancePointLine(p2, q1, q2);
/* 464 */     if (dist < minDist) {
/* 465 */       minDist = dist;
/* 466 */       nearestPt = p2;
/*     */     } 
/* 468 */     dist = CGAlgorithms.distancePointLine(q1, p1, p2);
/* 469 */     if (dist < minDist) {
/* 470 */       minDist = dist;
/* 471 */       nearestPt = q1;
/*     */     } 
/* 473 */     dist = CGAlgorithms.distancePointLine(q2, p1, p2);
/* 474 */     if (dist < minDist) {
/* 475 */       minDist = dist;
/* 476 */       nearestPt = q2;
/*     */     } 
/* 478 */     return nearestPt;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\RobustLineIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */