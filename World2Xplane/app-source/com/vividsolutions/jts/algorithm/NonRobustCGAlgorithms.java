/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class NonRobustCGAlgorithms extends CGAlgorithms {
/*     */   public static boolean isPointInRing(Coordinate p, Coordinate[] ring) {
/*  64 */     int crossings = 0;
/*  66 */     int nPts = ring.length;
/*  69 */     for (int i = 1; i < nPts; i++) {
/*  70 */       int i1 = i - 1;
/*  71 */       Coordinate p1 = ring[i];
/*  72 */       Coordinate p2 = ring[i1];
/*  73 */       double x1 = p1.x - p.x;
/*  74 */       double y1 = p1.y - p.y;
/*  75 */       double x2 = p2.x - p.x;
/*  76 */       double y2 = p2.y - p.y;
/*  78 */       if ((y1 > 0.0D && y2 <= 0.0D) || (y2 > 0.0D && y1 <= 0.0D)) {
/*  81 */         double xInt = (x1 * y2 - x2 * y1) / (y2 - y1);
/*  84 */         if (0.0D < xInt)
/*  85 */           crossings++; 
/*     */       } 
/*     */     } 
/*  89 */     if (crossings % 2 == 1)
/*  90 */       return true; 
/*  92 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isCCW(Coordinate[] ring) {
/* 108 */     int nPts = ring.length - 1;
/* 111 */     if (nPts < 4)
/* 111 */       return false; 
/* 115 */     Coordinate hip = ring[0];
/* 116 */     int hii = 0;
/* 117 */     for (int i = 1; i <= nPts; i++) {
/* 118 */       Coordinate p = ring[i];
/* 119 */       if (p.y > hip.y) {
/* 120 */         hip = p;
/* 121 */         hii = i;
/*     */       } 
/*     */     } 
/* 126 */     int iPrev = hii;
/*     */     do {
/* 128 */       iPrev = (iPrev - 1) % nPts;
/* 129 */     } while (ring[iPrev].equals(hip) && iPrev != hii);
/* 132 */     int iNext = hii;
/*     */     do {
/* 134 */       iNext = (iNext + 1) % nPts;
/* 135 */     } while (ring[iNext].equals(hip) && iNext != hii);
/* 137 */     Coordinate prev = ring[iPrev];
/* 138 */     Coordinate next = ring[iNext];
/* 140 */     if (prev.equals(hip) || next.equals(hip) || prev.equals(next))
/* 141 */       throw new IllegalArgumentException("degenerate ring (does not contain 3 different points)"); 
/* 147 */     double prev2x = prev.x - hip.x;
/* 148 */     double prev2y = prev.y - hip.y;
/* 149 */     double next2x = next.x - hip.x;
/* 150 */     double next2y = next.y - hip.y;
/* 153 */     double disc = next2x * prev2y - next2y * prev2x;
/* 161 */     if (disc == 0.0D)
/* 163 */       return (prev.x > next.x); 
/* 167 */     return (disc > 0.0D);
/*     */   }
/*     */   
/*     */   public static int computeOrientation(Coordinate p1, Coordinate p2, Coordinate q) {
/* 181 */     return orientationIndex(p1, p2, q);
/*     */   }
/*     */   
/*     */   public static int orientationIndex(Coordinate p1, Coordinate p2, Coordinate q) {
/* 201 */     double dx1 = p2.x - p1.x;
/* 202 */     double dy1 = p2.y - p1.y;
/* 203 */     double dx2 = q.x - p2.x;
/* 204 */     double dy2 = q.y - p2.y;
/* 205 */     double det = dx1 * dy2 - dx2 * dy1;
/* 206 */     if (det > 0.0D)
/* 206 */       return 1; 
/* 207 */     if (det < 0.0D)
/* 207 */       return -1; 
/* 208 */     return 0;
/*     */   }
/*     */   
/*     */   public static double distanceLineLine(Coordinate A, Coordinate B, Coordinate C, Coordinate D) {
/* 229 */     if (A.equals(B))
/* 230 */       return distancePointLine(A, C, D); 
/* 231 */     if (C.equals(D))
/* 232 */       return distancePointLine(D, A, B); 
/* 250 */     double r_top = (A.y - C.y) * (D.x - C.x) - (A.x - C.x) * (D.y - C.y);
/* 251 */     double r_bot = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);
/* 253 */     double s_top = (A.y - C.y) * (B.x - A.x) - (A.x - C.x) * (B.y - A.y);
/* 254 */     double s_bot = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);
/* 256 */     if (r_bot == 0.0D || s_bot == 0.0D)
/* 257 */       return Math.min(distancePointLine(A, C, D), Math.min(distancePointLine(B, C, D), Math.min(distancePointLine(C, A, B), distancePointLine(D, A, B)))); 
/* 266 */     double s = s_top / s_bot;
/* 267 */     double r = r_top / r_bot;
/* 269 */     if (r < 0.0D || r > 1.0D || s < 0.0D || s > 1.0D)
/* 271 */       return Math.min(distancePointLine(A, C, D), Math.min(distancePointLine(B, C, D), Math.min(distancePointLine(C, A, B), distancePointLine(D, A, B)))); 
/* 279 */     return 0.0D;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\NonRobustCGAlgorithms.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */