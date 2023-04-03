/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class Octant {
/*     */   public static int octant(double dx, double dy) {
/*  60 */     if (dx == 0.0D && dy == 0.0D)
/*  61 */       throw new IllegalArgumentException("Cannot compute the octant for point ( " + dx + ", " + dy + " )"); 
/*  63 */     double adx = Math.abs(dx);
/*  64 */     double ady = Math.abs(dy);
/*  66 */     if (dx >= 0.0D) {
/*  67 */       if (dy >= 0.0D) {
/*  68 */         if (adx >= ady)
/*  69 */           return 0; 
/*  71 */         return 1;
/*     */       } 
/*  74 */       if (adx >= ady)
/*  75 */         return 7; 
/*  77 */       return 6;
/*     */     } 
/*  81 */     if (dy >= 0.0D) {
/*  82 */       if (adx >= ady)
/*  83 */         return 3; 
/*  85 */       return 2;
/*     */     } 
/*  88 */     if (adx >= ady)
/*  89 */       return 4; 
/*  91 */     return 5;
/*     */   }
/*     */   
/*     */   public static int octant(Coordinate p0, Coordinate p1) {
/* 101 */     double dx = p1.x - p0.x;
/* 102 */     double dy = p1.y - p0.y;
/* 103 */     if (dx == 0.0D && dy == 0.0D)
/* 104 */       throw new IllegalArgumentException("Cannot compute the octant for two identical points " + p0); 
/* 105 */     return octant(dx, dy);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\Octant.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */