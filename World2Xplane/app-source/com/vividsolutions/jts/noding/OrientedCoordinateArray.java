/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ 
/*     */ public class OrientedCoordinateArray implements Comparable {
/*     */   private Coordinate[] pts;
/*     */   
/*     */   private boolean orientation;
/*     */   
/*     */   public OrientedCoordinateArray(Coordinate[] pts) {
/*  59 */     this.pts = pts;
/*  60 */     this.orientation = orientation(pts);
/*     */   }
/*     */   
/*     */   private static boolean orientation(Coordinate[] pts) {
/*  72 */     return (CoordinateArrays.increasingDirection(pts) == 1);
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*  84 */     OrientedCoordinateArray oca = (OrientedCoordinateArray)o1;
/*  85 */     int comp = compareOriented(this.pts, this.orientation, oca.pts, oca.orientation);
/* 100 */     return comp;
/*     */   }
/*     */   
/*     */   private static int compareOriented(Coordinate[] pts1, boolean orientation1, Coordinate[] pts2, boolean orientation2) {
/* 108 */     int dir1 = orientation1 ? 1 : -1;
/* 109 */     int dir2 = orientation2 ? 1 : -1;
/* 110 */     int limit1 = orientation1 ? pts1.length : -1;
/* 111 */     int limit2 = orientation2 ? pts2.length : -1;
/* 113 */     int i1 = orientation1 ? 0 : (pts1.length - 1);
/* 114 */     int i2 = orientation2 ? 0 : (pts2.length - 1);
/* 115 */     int comp = 0;
/*     */     while (true) {
/* 117 */       int compPt = pts1[i1].compareTo(pts2[i2]);
/* 118 */       if (compPt != 0)
/* 119 */         return compPt; 
/* 120 */       i1 += dir1;
/* 121 */       i2 += dir2;
/* 122 */       boolean done1 = (i1 == limit1);
/* 123 */       boolean done2 = (i2 == limit2);
/* 124 */       if (done1 && !done2)
/* 124 */         return -1; 
/* 125 */       if (!done1 && done2)
/* 125 */         return 1; 
/* 126 */       if (done1 && done2)
/* 126 */         return 0; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\OrientedCoordinateArray.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */