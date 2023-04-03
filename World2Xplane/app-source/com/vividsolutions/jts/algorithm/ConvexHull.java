/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.CoordinateFilter;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import com.vividsolutions.jts.util.UniqueCoordinateArrayFilter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Stack;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class ConvexHull {
/*     */   private GeometryFactory geomFactory;
/*     */   
/*     */   private Coordinate[] inputPts;
/*     */   
/*     */   public ConvexHull(Geometry geometry) {
/*  61 */     this(extractCoordinates(geometry), geometry.getFactory());
/*     */   }
/*     */   
/*     */   public ConvexHull(Coordinate[] pts, GeometryFactory geomFactory) {
/*  68 */     this.inputPts = UniqueCoordinateArrayFilter.filterCoordinates(pts);
/*  70 */     this.geomFactory = geomFactory;
/*     */   }
/*     */   
/*     */   private static Coordinate[] extractCoordinates(Geometry geom) {
/*  75 */     UniqueCoordinateArrayFilter filter = new UniqueCoordinateArrayFilter();
/*  76 */     geom.apply((CoordinateFilter)filter);
/*  77 */     return filter.getCoordinates();
/*     */   }
/*     */   
/*     */   public Geometry getConvexHull() {
/*  94 */     if (this.inputPts.length == 0)
/*  95 */       return (Geometry)this.geomFactory.createGeometryCollection(null); 
/*  97 */     if (this.inputPts.length == 1)
/*  98 */       return (Geometry)this.geomFactory.createPoint(this.inputPts[0]); 
/* 100 */     if (this.inputPts.length == 2)
/* 101 */       return (Geometry)this.geomFactory.createLineString(this.inputPts); 
/* 104 */     Coordinate[] reducedPts = this.inputPts;
/* 106 */     if (this.inputPts.length > 50)
/* 107 */       reducedPts = reduce(this.inputPts); 
/* 110 */     Coordinate[] sortedPts = preSort(reducedPts);
/* 113 */     Stack cHS = grahamScan(sortedPts);
/* 116 */     Coordinate[] cH = toCoordinateArray(cHS);
/* 119 */     return lineOrPolygon(cH);
/*     */   }
/*     */   
/*     */   protected Coordinate[] toCoordinateArray(Stack<Coordinate> stack) {
/* 127 */     Coordinate[] coordinates = new Coordinate[stack.size()];
/* 128 */     for (int i = 0; i < stack.size(); i++) {
/* 129 */       Coordinate coordinate = stack.get(i);
/* 130 */       coordinates[i] = coordinate;
/*     */     } 
/* 132 */     return coordinates;
/*     */   }
/*     */   
/*     */   private Coordinate[] reduce(Coordinate[] inputPts) {
/* 157 */     Coordinate[] polyPts = computeOctRing(inputPts);
/* 161 */     if (polyPts == null)
/* 162 */       return inputPts; 
/* 168 */     TreeSet<Coordinate> reducedSet = new TreeSet();
/*     */     int i;
/* 169 */     for (i = 0; i < polyPts.length; i++)
/* 170 */       reducedSet.add(polyPts[i]); 
/* 178 */     for (i = 0; i < inputPts.length; i++) {
/* 179 */       if (!CGAlgorithms.isPointInRing(inputPts[i], polyPts))
/* 180 */         reducedSet.add(inputPts[i]); 
/*     */     } 
/* 183 */     Coordinate[] reducedPts = CoordinateArrays.toCoordinateArray(reducedSet);
/* 186 */     if (reducedPts.length < 3)
/* 187 */       return padArray3(reducedPts); 
/* 188 */     return reducedPts;
/*     */   }
/*     */   
/*     */   private Coordinate[] padArray3(Coordinate[] pts) {
/* 193 */     Coordinate[] pad = new Coordinate[3];
/* 194 */     for (int i = 0; i < pad.length; i++) {
/* 195 */       if (i < pts.length) {
/* 196 */         pad[i] = pts[i];
/*     */       } else {
/* 199 */         pad[i] = pts[0];
/*     */       } 
/*     */     } 
/* 201 */     return pad;
/*     */   }
/*     */   
/*     */   private Coordinate[] preSort(Coordinate[] pts) {
/* 210 */     for (int i = 1; i < pts.length; i++) {
/* 211 */       if ((pts[i]).y < (pts[0]).y || ((pts[i]).y == (pts[0]).y && (pts[i]).x < (pts[0]).x)) {
/* 212 */         Coordinate t = pts[0];
/* 213 */         pts[0] = pts[i];
/* 214 */         pts[i] = t;
/*     */       } 
/*     */     } 
/* 219 */     Arrays.sort(pts, 1, pts.length, new RadialComparator(pts[0]));
/* 222 */     return pts;
/*     */   }
/*     */   
/*     */   private Stack grahamScan(Coordinate[] c) {
/* 233 */     Stack<Coordinate> ps = new Stack();
/* 234 */     Coordinate p = ps.push(c[0]);
/* 235 */     p = ps.push(c[1]);
/* 236 */     p = ps.push(c[2]);
/* 237 */     for (int i = 3; i < c.length; i++) {
/* 238 */       p = ps.pop();
/* 241 */       while (!ps.empty() && CGAlgorithms.computeOrientation(ps.peek(), p, c[i]) > 0)
/* 243 */         p = ps.pop(); 
/* 245 */       p = ps.push(p);
/* 246 */       p = ps.push(c[i]);
/*     */     } 
/* 248 */     p = ps.push(c[0]);
/* 249 */     return ps;
/*     */   }
/*     */   
/*     */   private boolean isBetween(Coordinate c1, Coordinate c2, Coordinate c3) {
/* 257 */     if (CGAlgorithms.computeOrientation(c1, c2, c3) != 0)
/* 258 */       return false; 
/* 260 */     if (c1.x != c3.x) {
/* 261 */       if (c1.x <= c2.x && c2.x <= c3.x)
/* 262 */         return true; 
/* 264 */       if (c3.x <= c2.x && c2.x <= c1.x)
/* 265 */         return true; 
/*     */     } 
/* 268 */     if (c1.y != c3.y) {
/* 269 */       if (c1.y <= c2.y && c2.y <= c3.y)
/* 270 */         return true; 
/* 272 */       if (c3.y <= c2.y && c2.y <= c1.y)
/* 273 */         return true; 
/*     */     } 
/* 276 */     return false;
/*     */   }
/*     */   
/*     */   private Coordinate[] computeOctRing(Coordinate[] inputPts) {
/* 280 */     Coordinate[] octPts = computeOctPts(inputPts);
/* 281 */     CoordinateList coordList = new CoordinateList();
/* 282 */     coordList.add(octPts, false);
/* 285 */     if (coordList.size() < 3)
/* 286 */       return null; 
/* 288 */     coordList.closeRing();
/* 289 */     return coordList.toCoordinateArray();
/*     */   }
/*     */   
/*     */   private Coordinate[] computeOctPts(Coordinate[] inputPts) {
/* 294 */     Coordinate[] pts = new Coordinate[8];
/* 295 */     for (int j = 0; j < pts.length; j++)
/* 296 */       pts[j] = inputPts[0]; 
/* 298 */     for (int i = 1; i < inputPts.length; i++) {
/* 299 */       if ((inputPts[i]).x < (pts[0]).x)
/* 300 */         pts[0] = inputPts[i]; 
/* 302 */       if ((inputPts[i]).x - (inputPts[i]).y < (pts[1]).x - (pts[1]).y)
/* 303 */         pts[1] = inputPts[i]; 
/* 305 */       if ((inputPts[i]).y > (pts[2]).y)
/* 306 */         pts[2] = inputPts[i]; 
/* 308 */       if ((inputPts[i]).x + (inputPts[i]).y > (pts[3]).x + (pts[3]).y)
/* 309 */         pts[3] = inputPts[i]; 
/* 311 */       if ((inputPts[i]).x > (pts[4]).x)
/* 312 */         pts[4] = inputPts[i]; 
/* 314 */       if ((inputPts[i]).x - (inputPts[i]).y > (pts[5]).x - (pts[5]).y)
/* 315 */         pts[5] = inputPts[i]; 
/* 317 */       if ((inputPts[i]).y < (pts[6]).y)
/* 318 */         pts[6] = inputPts[i]; 
/* 320 */       if ((inputPts[i]).x + (inputPts[i]).y < (pts[7]).x + (pts[7]).y)
/* 321 */         pts[7] = inputPts[i]; 
/*     */     } 
/* 324 */     return pts;
/*     */   }
/*     */   
/*     */   private Geometry lineOrPolygon(Coordinate[] coordinates) {
/* 397 */     coordinates = cleanRing(coordinates);
/* 398 */     if (coordinates.length == 3)
/* 399 */       return (Geometry)this.geomFactory.createLineString(new Coordinate[] { coordinates[0], coordinates[1] }); 
/* 403 */     LinearRing linearRing = this.geomFactory.createLinearRing(coordinates);
/* 404 */     return (Geometry)this.geomFactory.createPolygon(linearRing, null);
/*     */   }
/*     */   
/*     */   private Coordinate[] cleanRing(Coordinate[] original) {
/* 414 */     Assert.equals(original[0], original[original.length - 1]);
/* 415 */     ArrayList<Coordinate> cleanedRing = new ArrayList();
/* 416 */     Coordinate previousDistinctCoordinate = null;
/* 417 */     for (int i = 0; i <= original.length - 2; i++) {
/* 418 */       Coordinate currentCoordinate = original[i];
/* 419 */       Coordinate nextCoordinate = original[i + 1];
/* 420 */       if (!currentCoordinate.equals(nextCoordinate))
/* 423 */         if (previousDistinctCoordinate == null || !isBetween(previousDistinctCoordinate, currentCoordinate, nextCoordinate)) {
/* 427 */           cleanedRing.add(currentCoordinate);
/* 428 */           previousDistinctCoordinate = currentCoordinate;
/*     */         }  
/*     */     } 
/* 430 */     cleanedRing.add(original[original.length - 1]);
/* 431 */     Coordinate[] cleanedRingCoordinates = new Coordinate[cleanedRing.size()];
/* 432 */     return cleanedRing.<Coordinate>toArray(cleanedRingCoordinates);
/*     */   }
/*     */   
/*     */   private static class RadialComparator implements Comparator {
/*     */     private Coordinate origin;
/*     */     
/*     */     public RadialComparator(Coordinate origin) {
/* 450 */       this.origin = origin;
/*     */     }
/*     */     
/*     */     public int compare(Object o1, Object o2) {
/* 454 */       Coordinate p1 = (Coordinate)o1;
/* 455 */       Coordinate p2 = (Coordinate)o2;
/* 456 */       return polarCompare(this.origin, p1, p2);
/*     */     }
/*     */     
/*     */     private static int polarCompare(Coordinate o, Coordinate p, Coordinate q) {
/* 479 */       double dxp = p.x - o.x;
/* 480 */       double dyp = p.y - o.y;
/* 481 */       double dxq = q.x - o.x;
/* 482 */       double dyq = q.y - o.y;
/* 498 */       int orient = CGAlgorithms.computeOrientation(o, p, q);
/* 500 */       if (orient == 1)
/* 500 */         return 1; 
/* 501 */       if (orient == -1)
/* 501 */         return -1; 
/* 504 */       double op = dxp * dxp + dyp * dyp;
/* 505 */       double oq = dxq * dxq + dyq * dyq;
/* 506 */       if (op < oq)
/* 507 */         return -1; 
/* 509 */       if (op > oq)
/* 510 */         return 1; 
/* 512 */       return 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\ConvexHull.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */