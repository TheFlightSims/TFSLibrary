/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.math.MathUtil;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class CoordinateArrays {
/*  48 */   private static final Coordinate[] coordArrayType = new Coordinate[0];
/*     */   
/*     */   public static boolean isRing(Coordinate[] pts) {
/*  60 */     if (pts.length < 4)
/*  60 */       return false; 
/*  61 */     if (!pts[0].equals2D(pts[pts.length - 1]))
/*  61 */       return false; 
/*  62 */     return true;
/*     */   }
/*     */   
/*     */   public static Coordinate ptNotInList(Coordinate[] testPts, Coordinate[] pts) {
/*  74 */     for (int i = 0; i < testPts.length; i++) {
/*  75 */       Coordinate testPt = testPts[i];
/*  76 */       if (indexOf(testPt, pts) < 0)
/*  77 */         return testPt; 
/*     */     } 
/*  79 */     return null;
/*     */   }
/*     */   
/*     */   public static int compare(Coordinate[] pts1, Coordinate[] pts2) {
/*  92 */     int i = 0;
/*  93 */     while (i < pts1.length && i < pts2.length) {
/*  94 */       int compare = pts1[i].compareTo(pts2[i]);
/*  95 */       if (compare != 0)
/*  96 */         return compare; 
/*  97 */       i++;
/*     */     } 
/* 100 */     if (i < pts2.length)
/* 100 */       return -1; 
/* 101 */     if (i < pts1.length)
/* 101 */       return 1; 
/* 103 */     return 0;
/*     */   }
/*     */   
/*     */   public static class ForwardComparator implements Comparator {
/*     */     public int compare(Object o1, Object o2) {
/* 115 */       Coordinate[] pts1 = (Coordinate[])o1;
/* 116 */       Coordinate[] pts2 = (Coordinate[])o2;
/* 118 */       return CoordinateArrays.compare(pts1, pts2);
/*     */     }
/*     */   }
/*     */   
/*     */   public static int increasingDirection(Coordinate[] pts) {
/* 138 */     for (int i = 0; i < pts.length / 2; i++) {
/* 139 */       int j = pts.length - 1 - i;
/* 141 */       int comp = pts[i].compareTo(pts[j]);
/* 142 */       if (comp != 0)
/* 143 */         return comp; 
/*     */     } 
/* 146 */     return 1;
/*     */   }
/*     */   
/*     */   private static boolean isEqualReversed(Coordinate[] pts1, Coordinate[] pts2) {
/* 159 */     for (int i = 0; i < pts1.length; i++) {
/* 160 */       Coordinate p1 = pts1[i];
/* 161 */       Coordinate p2 = pts2[pts1.length - i - 1];
/* 162 */       if (p1.compareTo(p2) != 0)
/* 163 */         return false; 
/*     */     } 
/* 165 */     return true;
/*     */   }
/*     */   
/*     */   public static class BidirectionalComparator implements Comparator {
/*     */     public int compare(Object o1, Object o2) {
/* 181 */       Coordinate[] pts1 = (Coordinate[])o1;
/* 182 */       Coordinate[] pts2 = (Coordinate[])o2;
/* 184 */       if (pts1.length < pts2.length)
/* 184 */         return -1; 
/* 185 */       if (pts1.length > pts2.length)
/* 185 */         return 1; 
/* 187 */       if (pts1.length == 0)
/* 187 */         return 0; 
/* 189 */       int forwardComp = CoordinateArrays.compare(pts1, pts2);
/* 190 */       boolean isEqualRev = CoordinateArrays.isEqualReversed(pts1, pts2);
/* 191 */       if (isEqualRev)
/* 192 */         return 0; 
/* 193 */       return forwardComp;
/*     */     }
/*     */     
/*     */     public int OLDcompare(Object o1, Object o2) {
/* 197 */       Coordinate[] pts1 = (Coordinate[])o1;
/* 198 */       Coordinate[] pts2 = (Coordinate[])o2;
/* 200 */       if (pts1.length < pts2.length)
/* 200 */         return -1; 
/* 201 */       if (pts1.length > pts2.length)
/* 201 */         return 1; 
/* 203 */       if (pts1.length == 0)
/* 203 */         return 0; 
/* 205 */       int dir1 = CoordinateArrays.increasingDirection(pts1);
/* 206 */       int dir2 = CoordinateArrays.increasingDirection(pts2);
/* 208 */       int i1 = (dir1 > 0) ? 0 : (pts1.length - 1);
/* 209 */       int i2 = (dir2 > 0) ? 0 : (pts1.length - 1);
/* 211 */       for (int i = 0; i < pts1.length; i++) {
/* 212 */         int comparePt = pts1[i1].compareTo(pts2[i2]);
/* 213 */         if (comparePt != 0)
/* 214 */           return comparePt; 
/* 215 */         i1 += dir1;
/* 216 */         i2 += dir2;
/*     */       } 
/* 218 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public static Coordinate[] copyDeep(Coordinate[] coordinates) {
/* 230 */     Coordinate[] copy = new Coordinate[coordinates.length];
/* 231 */     for (int i = 0; i < coordinates.length; i++)
/* 232 */       copy[i] = new Coordinate(coordinates[i]); 
/* 234 */     return copy;
/*     */   }
/*     */   
/*     */   public static void copyDeep(Coordinate[] src, int srcStart, Coordinate[] dest, int destStart, int length) {
/* 250 */     for (int i = 0; i < length; i++)
/* 251 */       dest[destStart + i] = new Coordinate(src[srcStart + i]); 
/*     */   }
/*     */   
/*     */   public static Coordinate[] toCoordinateArray(Collection coordList) {
/* 260 */     return (Coordinate[])coordList.toArray((Object[])coordArrayType);
/*     */   }
/*     */   
/*     */   public static boolean hasRepeatedPoints(Coordinate[] coord) {
/* 269 */     for (int i = 1; i < coord.length; i++) {
/* 270 */       if (coord[i - 1].equals(coord[i]))
/* 271 */         return true; 
/*     */     } 
/* 274 */     return false;
/*     */   }
/*     */   
/*     */   public static Coordinate[] atLeastNCoordinatesOrNothing(int n, Coordinate[] c) {
/* 282 */     return (c.length >= n) ? c : new Coordinate[0];
/*     */   }
/*     */   
/*     */   public static Coordinate[] removeRepeatedPoints(Coordinate[] coord) {
/* 293 */     if (!hasRepeatedPoints(coord))
/* 293 */       return coord; 
/* 294 */     CoordinateList coordList = new CoordinateList(coord, false);
/* 295 */     return coordList.toCoordinateArray();
/*     */   }
/*     */   
/*     */   public static Coordinate[] removeNull(Coordinate[] coord) {
/* 306 */     int nonNull = 0;
/* 307 */     for (int i = 0; i < coord.length; i++) {
/* 308 */       if (coord[i] != null)
/* 308 */         nonNull++; 
/*     */     } 
/* 310 */     Coordinate[] newCoord = new Coordinate[nonNull];
/* 312 */     if (nonNull == 0)
/* 312 */       return newCoord; 
/* 314 */     int j = 0;
/* 315 */     for (int k = 0; k < coord.length; k++) {
/* 316 */       if (coord[k] != null)
/* 316 */         newCoord[j++] = coord[k]; 
/*     */     } 
/* 318 */     return newCoord;
/*     */   }
/*     */   
/*     */   public static void reverse(Coordinate[] coord) {
/* 326 */     int last = coord.length - 1;
/* 327 */     int mid = last / 2;
/* 328 */     for (int i = 0; i <= mid; i++) {
/* 329 */       Coordinate tmp = coord[i];
/* 330 */       coord[i] = coord[last - i];
/* 331 */       coord[last - i] = tmp;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean equals(Coordinate[] coord1, Coordinate[] coord2) {
/* 344 */     if (coord1 == coord2)
/* 344 */       return true; 
/* 345 */     if (coord1 == null || coord2 == null)
/* 345 */       return false; 
/* 346 */     if (coord1.length != coord2.length)
/* 346 */       return false; 
/* 347 */     for (int i = 0; i < coord1.length; i++) {
/* 348 */       if (!coord1[i].equals(coord2[i]))
/* 348 */         return false; 
/*     */     } 
/* 350 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean equals(Coordinate[] coord1, Coordinate[] coord2, Comparator<Coordinate> coordinateComparator) {
/* 366 */     if (coord1 == coord2)
/* 366 */       return true; 
/* 367 */     if (coord1 == null || coord2 == null)
/* 367 */       return false; 
/* 368 */     if (coord1.length != coord2.length)
/* 368 */       return false; 
/* 369 */     for (int i = 0; i < coord1.length; i++) {
/* 370 */       if (coordinateComparator.compare(coord1[i], coord2[i]) != 0)
/* 371 */         return false; 
/*     */     } 
/* 373 */     return true;
/*     */   }
/*     */   
/*     */   public static Coordinate minCoordinate(Coordinate[] coordinates) {
/* 385 */     Coordinate minCoord = null;
/* 386 */     for (int i = 0; i < coordinates.length; i++) {
/* 387 */       if (minCoord == null || minCoord.compareTo(coordinates[i]) > 0)
/* 388 */         minCoord = coordinates[i]; 
/*     */     } 
/* 391 */     return minCoord;
/*     */   }
/*     */   
/*     */   public static void scroll(Coordinate[] coordinates, Coordinate firstCoordinate) {
/* 401 */     int i = indexOf(firstCoordinate, coordinates);
/* 402 */     if (i < 0)
/*     */       return; 
/* 403 */     Coordinate[] newCoordinates = new Coordinate[coordinates.length];
/* 404 */     System.arraycopy(coordinates, i, newCoordinates, 0, coordinates.length - i);
/* 405 */     System.arraycopy(coordinates, 0, newCoordinates, coordinates.length - i, i);
/* 406 */     System.arraycopy(newCoordinates, 0, coordinates, 0, coordinates.length);
/*     */   }
/*     */   
/*     */   public static int indexOf(Coordinate coordinate, Coordinate[] coordinates) {
/* 419 */     for (int i = 0; i < coordinates.length; i++) {
/* 420 */       if (coordinate.equals(coordinates[i]))
/* 421 */         return i; 
/*     */     } 
/* 424 */     return -1;
/*     */   }
/*     */   
/*     */   public static Coordinate[] extract(Coordinate[] pts, int start, int end) {
/* 442 */     start = MathUtil.clamp(start, 0, pts.length);
/* 443 */     end = MathUtil.clamp(end, -1, pts.length);
/* 445 */     int npts = end - start + 1;
/* 446 */     if (end < 0)
/* 446 */       npts = 0; 
/* 447 */     if (start >= pts.length)
/* 447 */       npts = 0; 
/* 448 */     if (end < start)
/* 448 */       npts = 0; 
/* 450 */     Coordinate[] extractPts = new Coordinate[npts];
/* 451 */     if (npts == 0)
/* 451 */       return extractPts; 
/* 453 */     int iPts = 0;
/* 454 */     for (int i = start; i <= end; i++)
/* 455 */       extractPts[iPts++] = pts[i]; 
/* 457 */     return extractPts;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\CoordinateArrays.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */