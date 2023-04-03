/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class Quadrant {
/*     */   public static final int NE = 0;
/*     */   
/*     */   public static final int NW = 1;
/*     */   
/*     */   public static final int SW = 2;
/*     */   
/*     */   public static final int SE = 3;
/*     */   
/*     */   public static int quadrant(double dx, double dy) {
/*  68 */     if (dx == 0.0D && dy == 0.0D)
/*  69 */       throw new IllegalArgumentException("Cannot compute the quadrant for point ( " + dx + ", " + dy + " )"); 
/*  70 */     if (dx >= 0.0D) {
/*  71 */       if (dy >= 0.0D)
/*  72 */         return 0; 
/*  74 */       return 3;
/*     */     } 
/*  77 */     if (dy >= 0.0D)
/*  78 */       return 1; 
/*  80 */     return 2;
/*     */   }
/*     */   
/*     */   public static int quadrant(Coordinate p0, Coordinate p1) {
/*  91 */     if (p1.x == p0.x && p1.y == p0.y)
/*  92 */       throw new IllegalArgumentException("Cannot compute the quadrant for two identical points " + p0); 
/*  94 */     if (p1.x >= p0.x) {
/*  95 */       if (p1.y >= p0.y)
/*  96 */         return 0; 
/*  98 */       return 3;
/*     */     } 
/* 101 */     if (p1.y >= p0.y)
/* 102 */       return 1; 
/* 104 */     return 2;
/*     */   }
/*     */   
/*     */   public static boolean isOpposite(int quad1, int quad2) {
/* 113 */     if (quad1 == quad2)
/* 113 */       return false; 
/* 114 */     int diff = (quad1 - quad2 + 4) % 4;
/* 116 */     if (diff == 2)
/* 116 */       return true; 
/* 117 */     return false;
/*     */   }
/*     */   
/*     */   public static int commonHalfPlane(int quad1, int quad2) {
/* 128 */     if (quad1 == quad2)
/* 128 */       return quad1; 
/* 129 */     int diff = (quad1 - quad2 + 4) % 4;
/* 131 */     if (diff == 2)
/* 131 */       return -1; 
/* 133 */     int min = (quad1 < quad2) ? quad1 : quad2;
/* 134 */     int max = (quad1 > quad2) ? quad1 : quad2;
/* 136 */     if (min == 0 && max == 3)
/* 136 */       return 3; 
/* 138 */     return min;
/*     */   }
/*     */   
/*     */   public static boolean isInHalfPlane(int quad, int halfPlane) {
/* 147 */     if (halfPlane == 3)
/* 148 */       return (quad == 3 || quad == 2); 
/* 150 */     return (quad == halfPlane || quad == halfPlane + 1);
/*     */   }
/*     */   
/*     */   public static boolean isNorthern(int quad) {
/* 158 */     return (quad == 0 || quad == 1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\Quadrant.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */