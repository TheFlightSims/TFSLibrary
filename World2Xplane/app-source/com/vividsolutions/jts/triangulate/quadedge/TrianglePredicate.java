/*     */ package com.vividsolutions.jts.triangulate.quadedge;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Triangle;
/*     */ import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
/*     */ import com.vividsolutions.jts.io.WKTWriter;
/*     */ import com.vividsolutions.jts.math.DD;
/*     */ 
/*     */ public class TrianglePredicate {
/*     */   public static boolean isInCircleNonRobust(Coordinate a, Coordinate b, Coordinate c, Coordinate p) {
/*  72 */     boolean isInCircle = ((a.x * a.x + a.y * a.y) * triArea(b, c, p) - (b.x * b.x + b.y * b.y) * triArea(a, c, p) + (c.x * c.x + c.y * c.y) * triArea(a, b, p) - (p.x * p.x + p.y * p.y) * triArea(a, b, c) > 0.0D);
/*  78 */     return isInCircle;
/*     */   }
/*     */   
/*     */   public static boolean isInCircleNormalized(Coordinate a, Coordinate b, Coordinate c, Coordinate p) {
/* 101 */     double adx = a.x - p.x;
/* 102 */     double ady = a.y - p.y;
/* 103 */     double bdx = b.x - p.x;
/* 104 */     double bdy = b.y - p.y;
/* 105 */     double cdx = c.x - p.x;
/* 106 */     double cdy = c.y - p.y;
/* 108 */     double abdet = adx * bdy - bdx * ady;
/* 109 */     double bcdet = bdx * cdy - cdx * bdy;
/* 110 */     double cadet = cdx * ady - adx * cdy;
/* 111 */     double alift = adx * adx + ady * ady;
/* 112 */     double blift = bdx * bdx + bdy * bdy;
/* 113 */     double clift = cdx * cdx + cdy * cdy;
/* 115 */     double disc = alift * bcdet + blift * cadet + clift * abdet;
/* 116 */     return (disc > 0.0D);
/*     */   }
/*     */   
/*     */   private static double triArea(Coordinate a, Coordinate b, Coordinate c) {
/* 128 */     return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
/*     */   }
/*     */   
/*     */   public static boolean isInCircleRobust(Coordinate a, Coordinate b, Coordinate c, Coordinate p) {
/* 149 */     return isInCircleNormalized(a, b, c, p);
/*     */   }
/*     */   
/*     */   public static boolean isInCircleDDSlow(Coordinate a, Coordinate b, Coordinate c, Coordinate p) {
/* 166 */     DD px = DD.valueOf(p.x);
/* 167 */     DD py = DD.valueOf(p.y);
/* 168 */     DD ax = DD.valueOf(a.x);
/* 169 */     DD ay = DD.valueOf(a.y);
/* 170 */     DD bx = DD.valueOf(b.x);
/* 171 */     DD by = DD.valueOf(b.y);
/* 172 */     DD cx = DD.valueOf(c.x);
/* 173 */     DD cy = DD.valueOf(c.y);
/* 175 */     DD aTerm = ax.multiply(ax).add(ay.multiply(ay)).multiply(triAreaDDSlow(bx, by, cx, cy, px, py));
/* 177 */     DD bTerm = bx.multiply(bx).add(by.multiply(by)).multiply(triAreaDDSlow(ax, ay, cx, cy, px, py));
/* 179 */     DD cTerm = cx.multiply(cx).add(cy.multiply(cy)).multiply(triAreaDDSlow(ax, ay, bx, by, px, py));
/* 181 */     DD pTerm = px.multiply(px).add(py.multiply(py)).multiply(triAreaDDSlow(ax, ay, bx, by, cx, cy));
/* 184 */     DD sum = aTerm.subtract(bTerm).add(cTerm).subtract(pTerm);
/* 185 */     boolean isInCircle = (sum.doubleValue() > 0.0D);
/* 187 */     return isInCircle;
/*     */   }
/*     */   
/*     */   public static DD triAreaDDSlow(DD ax, DD ay, DD bx, DD by, DD cx, DD cy) {
/* 204 */     return bx.subtract(ax).multiply(cy.subtract(ay)).subtract(by.subtract(ay).multiply(cx.subtract(ax)));
/*     */   }
/*     */   
/*     */   public static boolean isInCircleDDFast(Coordinate a, Coordinate b, Coordinate c, Coordinate p) {
/* 211 */     DD aTerm = DD.sqr(a.x).selfAdd(DD.sqr(a.y)).selfMultiply(triAreaDDFast(b, c, p));
/* 213 */     DD bTerm = DD.sqr(b.x).selfAdd(DD.sqr(b.y)).selfMultiply(triAreaDDFast(a, c, p));
/* 215 */     DD cTerm = DD.sqr(c.x).selfAdd(DD.sqr(c.y)).selfMultiply(triAreaDDFast(a, b, p));
/* 217 */     DD pTerm = DD.sqr(p.x).selfAdd(DD.sqr(p.y)).selfMultiply(triAreaDDFast(a, b, c));
/* 220 */     DD sum = aTerm.selfSubtract(bTerm).selfAdd(cTerm).selfSubtract(pTerm);
/* 221 */     boolean isInCircle = (sum.doubleValue() > 0.0D);
/* 223 */     return isInCircle;
/*     */   }
/*     */   
/*     */   public static DD triAreaDDFast(Coordinate a, Coordinate b, Coordinate c) {
/* 229 */     DD t1 = DD.valueOf(b.x).selfSubtract(a.x).selfMultiply(DD.valueOf(c.y).selfSubtract(a.y));
/* 233 */     DD t2 = DD.valueOf(b.y).selfSubtract(a.y).selfMultiply(DD.valueOf(c.x).selfSubtract(a.x));
/* 237 */     return t1.selfSubtract(t2);
/*     */   }
/*     */   
/*     */   public static boolean isInCircleDDNormalized(Coordinate a, Coordinate b, Coordinate c, Coordinate p) {
/* 243 */     DD adx = DD.valueOf(a.x).selfSubtract(p.x);
/* 244 */     DD ady = DD.valueOf(a.y).selfSubtract(p.y);
/* 245 */     DD bdx = DD.valueOf(b.x).selfSubtract(p.x);
/* 246 */     DD bdy = DD.valueOf(b.y).selfSubtract(p.y);
/* 247 */     DD cdx = DD.valueOf(c.x).selfSubtract(p.x);
/* 248 */     DD cdy = DD.valueOf(c.y).selfSubtract(p.y);
/* 250 */     DD abdet = adx.multiply(bdy).selfSubtract(bdx.multiply(ady));
/* 251 */     DD bcdet = bdx.multiply(cdy).selfSubtract(cdx.multiply(bdy));
/* 252 */     DD cadet = cdx.multiply(ady).selfSubtract(adx.multiply(cdy));
/* 253 */     DD alift = adx.multiply(adx).selfAdd(ady.multiply(ady));
/* 254 */     DD blift = bdx.multiply(bdx).selfAdd(bdy.multiply(bdy));
/* 255 */     DD clift = cdx.multiply(cdx).selfAdd(cdy.multiply(cdy));
/* 257 */     DD sum = alift.selfMultiply(bcdet).selfAdd(blift.selfMultiply(cadet)).selfAdd(clift.selfMultiply(abdet));
/* 261 */     boolean isInCircle = (sum.doubleValue() > 0.0D);
/* 263 */     return isInCircle;
/*     */   }
/*     */   
/*     */   public static boolean isInCircleCC(Coordinate a, Coordinate b, Coordinate c, Coordinate p) {
/* 288 */     Coordinate cc = Triangle.circumcentre(a, b, c);
/* 289 */     double ccRadius = a.distance(cc);
/* 290 */     double pRadiusDiff = p.distance(cc) - ccRadius;
/* 291 */     return (pRadiusDiff <= 0.0D);
/*     */   }
/*     */   
/*     */   private static void checkRobustInCircle(Coordinate a, Coordinate b, Coordinate c, Coordinate p) {
/* 306 */     boolean nonRobustInCircle = isInCircleNonRobust(a, b, c, p);
/* 307 */     boolean isInCircleDD = isInCircleDDSlow(a, b, c, p);
/* 308 */     boolean isInCircleCC = isInCircleCC(a, b, c, p);
/* 310 */     Coordinate circumCentre = Triangle.circumcentre(a, b, c);
/* 311 */     System.out.println("p radius diff a = " + (Math.abs(p.distance(circumCentre) - a.distance(circumCentre)) / a.distance(circumCentre)));
/* 315 */     if (nonRobustInCircle != isInCircleDD || nonRobustInCircle != isInCircleCC) {
/* 316 */       System.out.println("inCircle robustness failure (double result = " + nonRobustInCircle + ", DD result = " + isInCircleDD + ", CC result = " + isInCircleCC + ")");
/* 320 */       System.out.println(WKTWriter.toLineString((CoordinateSequence)new CoordinateArraySequence(new Coordinate[] { a, b, c, p })));
/* 322 */       System.out.println("Circumcentre = " + WKTWriter.toPoint(circumCentre) + " radius = " + a.distance(circumCentre));
/* 324 */       System.out.println("p radius diff a = " + Math.abs(p.distance(circumCentre) / a.distance(circumCentre) - 1.0D));
/* 326 */       System.out.println("p radius diff b = " + Math.abs(p.distance(circumCentre) / b.distance(circumCentre) - 1.0D));
/* 328 */       System.out.println("p radius diff c = " + Math.abs(p.distance(circumCentre) / c.distance(circumCentre) - 1.0D));
/* 330 */       System.out.println();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\quadedge\TrianglePredicate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */