/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class HCoordinate {
/*     */   public double x;
/*     */   
/*     */   public double y;
/*     */   
/*     */   public double w;
/*     */   
/*     */   public static Coordinate intersection(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) throws NotRepresentableException {
/*  64 */     double px = p1.y - p2.y;
/*  65 */     double py = p2.x - p1.x;
/*  66 */     double pw = p1.x * p2.y - p2.x * p1.y;
/*  68 */     double qx = q1.y - q2.y;
/*  69 */     double qy = q2.x - q1.x;
/*  70 */     double qw = q1.x * q2.y - q2.x * q1.y;
/*  72 */     double x = py * qw - qy * pw;
/*  73 */     double y = qx * pw - px * qw;
/*  74 */     double w = px * qy - qx * py;
/*  76 */     double xInt = x / w;
/*  77 */     double yInt = y / w;
/*  79 */     if (Double.isNaN(xInt) || Double.isInfinite(xInt) || Double.isNaN(yInt) || Double.isInfinite(yInt))
/*  81 */       throw new NotRepresentableException(); 
/*  84 */     return new Coordinate(xInt, yInt);
/*     */   }
/*     */   
/*     */   public HCoordinate() {
/* 104 */     this.x = 0.0D;
/* 105 */     this.y = 0.0D;
/* 106 */     this.w = 1.0D;
/*     */   }
/*     */   
/*     */   public HCoordinate(double _x, double _y, double _w) {
/* 110 */     this.x = _x;
/* 111 */     this.y = _y;
/* 112 */     this.w = _w;
/*     */   }
/*     */   
/*     */   public HCoordinate(double _x, double _y) {
/* 116 */     this.x = _x;
/* 117 */     this.y = _y;
/* 118 */     this.w = 1.0D;
/*     */   }
/*     */   
/*     */   public HCoordinate(Coordinate p) {
/* 122 */     this.x = p.x;
/* 123 */     this.y = p.y;
/* 124 */     this.w = 1.0D;
/*     */   }
/*     */   
/*     */   public HCoordinate(HCoordinate p1, HCoordinate p2) {
/* 129 */     this.x = p1.y * p2.w - p2.y * p1.w;
/* 130 */     this.y = p2.x * p1.w - p1.x * p2.w;
/* 131 */     this.w = p1.x * p2.y - p2.x * p1.y;
/*     */   }
/*     */   
/*     */   public HCoordinate(Coordinate p1, Coordinate p2) {
/* 145 */     this.x = p1.y - p2.y;
/* 146 */     this.y = p2.x - p1.x;
/* 147 */     this.w = p1.x * p2.y - p2.x * p1.y;
/*     */   }
/*     */   
/*     */   public HCoordinate(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) {
/* 153 */     double px = p1.y - p2.y;
/* 154 */     double py = p2.x - p1.x;
/* 155 */     double pw = p1.x * p2.y - p2.x * p1.y;
/* 157 */     double qx = q1.y - q2.y;
/* 158 */     double qy = q2.x - q1.x;
/* 159 */     double qw = q1.x * q2.y - q2.x * q1.y;
/* 161 */     this.x = py * qw - qy * pw;
/* 162 */     this.y = qx * pw - px * qw;
/* 163 */     this.w = px * qy - qx * py;
/*     */   }
/*     */   
/*     */   public double getX() throws NotRepresentableException {
/* 167 */     double a = this.x / this.w;
/* 168 */     if (Double.isNaN(a) || Double.isInfinite(a))
/* 169 */       throw new NotRepresentableException(); 
/* 171 */     return a;
/*     */   }
/*     */   
/*     */   public double getY() throws NotRepresentableException {
/* 175 */     double a = this.y / this.w;
/* 176 */     if (Double.isNaN(a) || Double.isInfinite(a))
/* 177 */       throw new NotRepresentableException(); 
/* 179 */     return a;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() throws NotRepresentableException {
/* 183 */     Coordinate p = new Coordinate();
/* 184 */     p.x = getX();
/* 185 */     p.y = getY();
/* 186 */     return p;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\HCoordinate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */