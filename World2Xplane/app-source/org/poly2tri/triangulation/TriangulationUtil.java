/*     */ package org.poly2tri.triangulation;
/*     */ 
/*     */ public class TriangulationUtil {
/*     */   public static final double EPSILON = 1.0E-12D;
/*     */   
/*     */   public static boolean smartIncircle(TriangulationPoint pa, TriangulationPoint pb, TriangulationPoint pc, TriangulationPoint pd) {
/*  89 */     double pdx = pd.getX();
/*  90 */     double pdy = pd.getY();
/*  91 */     double adx = pa.getX() - pdx;
/*  92 */     double ady = pa.getY() - pdy;
/*  93 */     double bdx = pb.getX() - pdx;
/*  94 */     double bdy = pb.getY() - pdy;
/*  96 */     double adxbdy = adx * bdy;
/*  97 */     double bdxady = bdx * ady;
/*  98 */     double oabd = adxbdy - bdxady;
/* 100 */     if (oabd <= 0.0D)
/* 102 */       return false; 
/* 105 */     double cdx = pc.getX() - pdx;
/* 106 */     double cdy = pc.getY() - pdy;
/* 108 */     double cdxady = cdx * ady;
/* 109 */     double adxcdy = adx * cdy;
/* 110 */     double ocad = cdxady - adxcdy;
/* 112 */     if (ocad <= 0.0D)
/* 114 */       return false; 
/* 117 */     double bdxcdy = bdx * cdy;
/* 118 */     double cdxbdy = cdx * bdy;
/* 120 */     double alift = adx * adx + ady * ady;
/* 121 */     double blift = bdx * bdx + bdy * bdy;
/* 122 */     double clift = cdx * cdx + cdy * cdy;
/* 124 */     double det = alift * (bdxcdy - cdxbdy) + blift * ocad + clift * oabd;
/* 126 */     return (det > 0.0D);
/*     */   }
/*     */   
/*     */   public static boolean inScanArea(TriangulationPoint pa, TriangulationPoint pb, TriangulationPoint pc, TriangulationPoint pd) {
/* 142 */     double pdx = pd.getX();
/* 143 */     double pdy = pd.getY();
/* 144 */     double adx = pa.getX() - pdx;
/* 145 */     double ady = pa.getY() - pdy;
/* 146 */     double bdx = pb.getX() - pdx;
/* 147 */     double bdy = pb.getY() - pdy;
/* 149 */     double adxbdy = adx * bdy;
/* 150 */     double bdxady = bdx * ady;
/* 151 */     double oabd = adxbdy - bdxady;
/* 153 */     if (oabd <= 0.0D)
/* 155 */       return false; 
/* 158 */     double cdx = pc.getX() - pdx;
/* 159 */     double cdy = pc.getY() - pdy;
/* 161 */     double cdxady = cdx * ady;
/* 162 */     double adxcdy = adx * cdy;
/* 163 */     double ocad = cdxady - adxcdy;
/* 165 */     if (ocad <= 0.0D)
/* 167 */       return false; 
/* 169 */     return true;
/*     */   }
/*     */   
/*     */   public static Orientation orient2d(TriangulationPoint pa, TriangulationPoint pb, TriangulationPoint pc) {
/* 186 */     double detleft = (pa.getX() - pc.getX()) * (pb.getY() - pc.getY());
/* 187 */     double detright = (pa.getY() - pc.getY()) * (pb.getX() - pc.getX());
/* 188 */     double val = detleft - detright;
/* 189 */     if (val > -1.0E-12D && val < 1.0E-12D)
/* 191 */       return Orientation.Collinear; 
/* 193 */     if (val > 0.0D)
/* 195 */       return Orientation.CCW; 
/* 197 */     return Orientation.CW;
/*     */   }
/*     */   
/*     */   public enum Orientation {
/* 202 */     CW, CCW, Collinear;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\TriangulationUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */