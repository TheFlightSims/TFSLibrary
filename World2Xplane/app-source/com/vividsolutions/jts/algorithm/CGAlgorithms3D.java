/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.math.Vector3D;
/*     */ 
/*     */ public class CGAlgorithms3D {
/*     */   public static double distance(Coordinate p0, Coordinate p1) {
/*  51 */     if (Double.isNaN(p0.z) || Double.isNaN(p1.z))
/*  52 */       return p0.distance(p1); 
/*  54 */     double dx = p0.x - p1.x;
/*  55 */     double dy = p0.y - p1.y;
/*  56 */     double dz = p0.z - p1.z;
/*  57 */     return Math.sqrt(dx * dx + dy * dy + dz * dz);
/*     */   }
/*     */   
/*     */   public static double distancePointSegment(Coordinate p, Coordinate A, Coordinate B) {
/*  63 */     if (A.equals3D(B))
/*  64 */       return distance(p, A); 
/*  80 */     double len2 = (B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y) + (B.z - A.z) * (B.z - A.z);
/*  81 */     if (Double.isNaN(len2))
/*  82 */       throw new IllegalArgumentException("Ordinates must not be NaN"); 
/*  83 */     double r = ((p.x - A.x) * (B.x - A.x) + (p.y - A.y) * (B.y - A.y) + (p.z - A.z) * (B.z - A.z)) / len2;
/*  86 */     if (r <= 0.0D)
/*  87 */       return distance(p, A); 
/*  88 */     if (r >= 1.0D)
/*  89 */       return distance(p, B); 
/*  92 */     double qx = A.x + r * (B.x - A.x);
/*  93 */     double qy = A.y + r * (B.y - A.y);
/*  94 */     double qz = A.z + r * (B.z - A.z);
/*  96 */     double dx = p.x - qx;
/*  97 */     double dy = p.y - qy;
/*  98 */     double dz = p.z - qz;
/*  99 */     return Math.sqrt(dx * dx + dy * dy + dz * dz);
/*     */   }
/*     */   
/*     */   public static double distanceSegmentSegment(Coordinate A, Coordinate B, Coordinate C, Coordinate D) {
/*     */     double s, t;
/* 120 */     if (A.equals3D(B))
/* 121 */       return distancePointSegment(A, C, D); 
/* 122 */     if (C.equals3D(B))
/* 123 */       return distancePointSegment(C, A, B); 
/* 128 */     double a = Vector3D.dot(A, B, A, B);
/* 129 */     double b = Vector3D.dot(A, B, C, D);
/* 130 */     double c = Vector3D.dot(C, D, C, D);
/* 131 */     double d = Vector3D.dot(A, B, C, A);
/* 132 */     double e = Vector3D.dot(C, D, C, A);
/* 134 */     double denom = a * c - b * b;
/* 135 */     if (Double.isNaN(denom))
/* 136 */       throw new IllegalArgumentException("Ordinates must not be NaN"); 
/* 140 */     if (denom <= 0.0D) {
/* 145 */       s = 0.0D;
/* 147 */       if (b > c) {
/* 148 */         t = d / b;
/*     */       } else {
/* 150 */         t = e / c;
/*     */       } 
/*     */     } else {
/* 153 */       s = (b * e - c * d) / denom;
/* 154 */       t = (a * e - b * d) / denom;
/*     */     } 
/* 156 */     if (s < 0.0D)
/* 157 */       return distancePointSegment(A, C, D); 
/* 158 */     if (s > 1.0D)
/* 159 */       return distancePointSegment(B, C, D); 
/* 160 */     if (t < 0.0D)
/* 161 */       return distancePointSegment(C, A, B); 
/* 162 */     if (t > 1.0D)
/* 163 */       return distancePointSegment(D, A, B); 
/* 169 */     double x1 = A.x + s * (B.x - A.x);
/* 170 */     double y1 = A.y + s * (B.y - A.y);
/* 171 */     double z1 = A.z + s * (B.z - A.z);
/* 173 */     double x2 = C.x + t * (D.x - C.x);
/* 174 */     double y2 = C.y + t * (D.y - C.y);
/* 175 */     double z2 = C.z + t * (D.z - C.z);
/* 178 */     return distance(new Coordinate(x1, y1, z1), new Coordinate(x2, y2, z2));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\CGAlgorithms3D.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */