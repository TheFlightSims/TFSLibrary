/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.math.DD;
/*     */ 
/*     */ public class CGAlgorithmsDD {
/*     */   private static final double DP_SAFE_EPSILON = 1.0E-15D;
/*     */   
/*     */   public static int orientationIndex(Coordinate p1, Coordinate p2, Coordinate q) {
/*  62 */     int index = orientationIndexFilter(p1, p2, q);
/*  63 */     if (index <= 1)
/*  63 */       return index; 
/*  66 */     DD dx1 = DD.valueOf(p2.x).selfAdd(-p1.x);
/*  67 */     DD dy1 = DD.valueOf(p2.y).selfAdd(-p1.y);
/*  68 */     DD dx2 = DD.valueOf(q.x).selfAdd(-p2.x);
/*  69 */     DD dy2 = DD.valueOf(q.y).selfAdd(-p2.y);
/*  72 */     return dx1.selfMultiply(dy2).selfSubtract(dy1.selfMultiply(dx2)).signum();
/*     */   }
/*     */   
/*     */   public static int signOfDet2x2(DD x1, DD y1, DD x2, DD y2) {
/*  85 */     DD det = x1.multiply(y2).selfSubtract(y1.multiply(x2));
/*  86 */     return det.signum();
/*     */   }
/*     */   
/*     */   private static int orientationIndexFilter(Coordinate pa, Coordinate pb, Coordinate pc) {
/* 119 */     double detsum, detleft = (pa.x - pc.x) * (pb.y - pc.y);
/* 120 */     double detright = (pa.y - pc.y) * (pb.x - pc.x);
/* 121 */     double det = detleft - detright;
/* 123 */     if (detleft > 0.0D) {
/* 124 */       if (detright <= 0.0D)
/* 125 */         return signum(det); 
/* 128 */       detsum = detleft + detright;
/* 131 */     } else if (detleft < 0.0D) {
/* 132 */       if (detright >= 0.0D)
/* 133 */         return signum(det); 
/* 136 */       detsum = -detleft - detright;
/*     */     } else {
/* 140 */       return signum(det);
/*     */     } 
/* 143 */     double errbound = 1.0E-15D * detsum;
/* 144 */     if (det >= errbound || -det >= errbound)
/* 145 */       return signum(det); 
/* 148 */     return 2;
/*     */   }
/*     */   
/*     */   private static int signum(double x) {
/* 153 */     if (x > 0.0D)
/* 153 */       return 1; 
/* 154 */     if (x < 0.0D)
/* 154 */       return -1; 
/* 155 */     return 0;
/*     */   }
/*     */   
/*     */   public static Coordinate intersection(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) {
/* 173 */     DD denom1 = DD.valueOf(q2.y).selfSubtract(q1.y).selfMultiply(DD.valueOf(p2.x).selfSubtract(p1.x));
/* 175 */     DD denom2 = DD.valueOf(q2.x).selfSubtract(q1.x).selfMultiply(DD.valueOf(p2.y).selfSubtract(p1.y));
/* 177 */     DD denom = denom1.subtract(denom2);
/* 186 */     DD numx1 = DD.valueOf(q2.x).selfSubtract(q1.x).selfMultiply(DD.valueOf(p1.y).selfSubtract(q1.y));
/* 188 */     DD numx2 = DD.valueOf(q2.y).selfSubtract(q1.y).selfMultiply(DD.valueOf(p1.x).selfSubtract(q1.x));
/* 190 */     DD numx = numx1.subtract(numx2);
/* 191 */     double fracP = numx.selfDivide(denom).doubleValue();
/* 193 */     double x = DD.valueOf(p1.x).selfAdd(DD.valueOf(p2.x).selfSubtract(p1.x).selfMultiply(fracP)).doubleValue();
/* 195 */     DD numy1 = DD.valueOf(p2.x).selfSubtract(p1.x).selfMultiply(DD.valueOf(p1.y).selfSubtract(q1.y));
/* 197 */     DD numy2 = DD.valueOf(p2.y).selfSubtract(p1.y).selfMultiply(DD.valueOf(p1.x).selfSubtract(q1.x));
/* 199 */     DD numy = numy1.subtract(numy2);
/* 200 */     double fracQ = numy.selfDivide(denom).doubleValue();
/* 202 */     double y = DD.valueOf(q1.y).selfAdd(DD.valueOf(q2.y).selfSubtract(q1.y).selfMultiply(fracQ)).doubleValue();
/* 204 */     return new Coordinate(x, y);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\CGAlgorithmsDD.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */