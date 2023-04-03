/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class MolleweideProjection extends PseudoCylindricalProjection {
/*     */   public static final int MOLLEWEIDE = 0;
/*     */   
/*     */   public static final int WAGNER4 = 1;
/*     */   
/*     */   public static final int WAGNER5 = 2;
/*     */   
/*     */   private static final int MAX_ITER = 10;
/*     */   
/*     */   private static final double TOLERANCE = 1.0E-7D;
/*     */   
/*  35 */   private int type = 0;
/*     */   
/*     */   private double cx;
/*     */   
/*     */   private double cy;
/*     */   
/*     */   private double cp;
/*     */   
/*     */   public MolleweideProjection() {
/*  39 */     this(1.5707963267948966D);
/*     */   }
/*     */   
/*     */   public MolleweideProjection(int type) {
/*  43 */     this.type = type;
/*  44 */     switch (type) {
/*     */       case 0:
/*  46 */         init(1.5707963267948966D);
/*     */         break;
/*     */       case 1:
/*  49 */         init(1.0471975511965976D);
/*     */         break;
/*     */       case 2:
/*  52 */         init(1.5707963267948966D);
/*  53 */         this.cx = 0.90977D;
/*  54 */         this.cy = 1.65014D;
/*  55 */         this.cp = 3.00896D;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public MolleweideProjection(double p) {
/*  61 */     init(p);
/*     */   }
/*     */   
/*     */   public void init(double p) {
/*  65 */     double p2 = p + p;
/*  67 */     double sp = Math.sin(p);
/*  68 */     double r = Math.sqrt(6.283185307179586D * sp / (p2 + Math.sin(p2)));
/*  69 */     this.cx = 2.0D * r / Math.PI;
/*  70 */     this.cy = r / sp;
/*  71 */     this.cp = p2 + Math.sin(p2);
/*     */   }
/*     */   
/*     */   public MolleweideProjection(double cx, double cy, double cp) {
/*  75 */     this.cx = cx;
/*  76 */     this.cy = cy;
/*  77 */     this.cp = cp;
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double xy) {
/*  84 */     double k = this.cp * Math.sin(lpphi);
/*  85 */     int i = 10;
/*     */     double v;
/*  86 */     lpphi -= v = (lpphi + Math.sin(lpphi) - k) / (1.0D + Math.cos(lpphi));
/*  87 */     for (; i != 0 && Math.abs(v) >= 1.0E-7D; i--);
/*  90 */     if (i == 0) {
/*  91 */       lpphi = (lpphi < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */     } else {
/*  93 */       lpphi *= 0.5D;
/*     */     } 
/*  94 */     xy.x = this.cx * lplam * Math.cos(lpphi);
/*  95 */     xy.y = this.cy * Math.sin(lpphi);
/*  96 */     return xy;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double x, double y, Point2D.Double lp) {
/* 102 */     double lat = Math.asin(y / this.cy);
/* 103 */     double lon = x / this.cx * Math.cos(lat);
/* 104 */     lat += lat;
/* 105 */     lat = Math.asin((lat + Math.sin(lat)) / this.cp);
/* 106 */     lp.x = lon;
/* 107 */     lp.y = lat;
/* 108 */     return lp;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 116 */     this.type = this.type;
/* 117 */     switch (this.type) {
/*     */       case 1:
/* 119 */         return "Wagner IV";
/*     */       case 2:
/* 121 */         return "Wagner V";
/*     */     } 
/* 123 */     return "Molleweide";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\MolleweideProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */