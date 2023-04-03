/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class SimpleConicProjection extends ConicProjection {
/*     */   private double n;
/*     */   
/*     */   private double rho_c;
/*     */   
/*     */   private double rho_0;
/*     */   
/*     */   private double sig;
/*     */   
/*     */   private double c1;
/*     */   
/*     */   private double c2;
/*     */   
/*     */   private int type;
/*     */   
/*     */   public static final int EULER = 0;
/*     */   
/*     */   public static final int MURD1 = 1;
/*     */   
/*     */   public static final int MURD2 = 2;
/*     */   
/*     */   public static final int MURD3 = 3;
/*     */   
/*     */   public static final int PCONIC = 4;
/*     */   
/*     */   public static final int TISSOT = 5;
/*     */   
/*     */   public static final int VITK1 = 6;
/*     */   
/*     */   private static final double EPS10 = 1.0E-10D;
/*     */   
/*     */   private static final double EPS = 1.0E-10D;
/*     */   
/*     */   public SimpleConicProjection() {
/*  44 */     this(0);
/*     */   }
/*     */   
/*     */   public SimpleConicProjection(int type) {
/*  48 */     this.type = type;
/*  49 */     this.minLatitude = Math.toRadians(0.0D);
/*  50 */     this.maxLatitude = Math.toRadians(80.0D);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  54 */     return "Simple Conic";
/*     */   }
/*     */   
/*     */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/*  60 */     switch (this.type) {
/*     */       case 2:
/*  62 */         rho = this.rho_c + Math.tan(this.sig - lpphi);
/*  71 */         out.x = rho * Math.sin(lplam *= this.n);
/*  72 */         out.y = this.rho_0 - rho * Math.cos(lplam);
/*  73 */         return out;
/*     */       case 4:
/*     */         rho = this.c2 * (this.c1 - Math.tan(lpphi));
/*     */         out.x = rho * Math.sin(lplam *= this.n);
/*     */         out.y = this.rho_0 - rho * Math.cos(lplam);
/*  73 */         return out;
/*     */     } 
/*     */     double rho = this.rho_c - lpphi;
/*     */     out.x = rho * Math.sin(lplam *= this.n);
/*     */     out.y = this.rho_0 - rho * Math.cos(lplam);
/*  73 */     return out;
/*     */   }
/*     */   
/*     */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/*  79 */     double rho = MapMath.distance(xyx, out.y = this.rho_0 - xyy);
/*  80 */     if (this.n < 0.0D) {
/*  81 */       rho = -rho;
/*  82 */       out.x = -xyx;
/*  83 */       out.y = -xyy;
/*     */     } 
/*  85 */     out.x = Math.atan2(xyx, xyy) / this.n;
/*  86 */     switch (this.type) {
/*     */       case 4:
/*  88 */         out.y = Math.atan(this.c1 - rho / this.c2) + this.sig;
/*  96 */         return out;
/*     */       case 2:
/*     */         out.y = this.sig - Math.atan(rho - this.rho_c);
/*  96 */         return out;
/*     */     } 
/*     */     out.y = this.rho_c - rho;
/*  96 */     return out;
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 100 */     return true;
/*     */   }
/*     */   
/*     */   public void initialize() {
/*     */     double cs;
/* 104 */     super.initialize();
/* 109 */     int err = 0;
/* 124 */     double p1 = Math.toRadians(30.0D);
/* 125 */     double p2 = Math.toRadians(60.0D);
/* 126 */     double del = 0.5D * (p2 - p1);
/* 127 */     this.sig = 0.5D * (p2 + p1);
/* 128 */     err = (Math.abs(del) < 1.0E-10D || Math.abs(this.sig) < 1.0E-10D) ? -42 : 0;
/* 129 */     del = del;
/* 131 */     if (err != 0)
/* 132 */       throw new ProjectionException("Error " + err); 
/* 134 */     switch (this.type) {
/*     */       case 5:
/* 136 */         this.n = Math.sin(this.sig);
/* 137 */         cs = Math.cos(del);
/* 138 */         this.rho_c = this.n / cs + cs / this.n;
/* 139 */         this.rho_0 = Math.sqrt((this.rho_c - 2.0D * Math.sin(this.projectionLatitude)) / this.n);
/*     */         break;
/*     */       case 1:
/* 142 */         this.rho_c = Math.sin(del) / del * Math.tan(this.sig) + this.sig;
/* 143 */         this.rho_0 = this.rho_c - this.projectionLatitude;
/* 144 */         this.n = Math.sin(this.sig);
/*     */         break;
/*     */       case 2:
/* 147 */         this.rho_c = (cs = Math.sqrt(Math.cos(del))) / Math.tan(this.sig);
/* 148 */         this.rho_0 = this.rho_c + Math.tan(this.sig - this.projectionLatitude);
/* 149 */         this.n = Math.sin(this.sig) * cs;
/*     */       case 3:
/* 152 */         this.rho_c = del / Math.tan(this.sig) * Math.tan(del) + this.sig;
/* 153 */         this.rho_0 = this.rho_c - this.projectionLatitude;
/* 154 */         this.n = Math.sin(this.sig) * Math.sin(del) * Math.tan(del) / del * del;
/*     */         break;
/*     */       case 0:
/* 157 */         this.n = Math.sin(this.sig) * Math.sin(del) / del;
/* 158 */         del *= 0.5D;
/* 159 */         this.rho_c = del / Math.tan(del) * Math.tan(this.sig) + this.sig;
/* 160 */         this.rho_0 = this.rho_c - this.projectionLatitude;
/*     */         break;
/*     */       case 4:
/* 163 */         this.n = Math.sin(this.sig);
/* 164 */         this.c2 = Math.cos(del);
/* 165 */         this.c1 = 1.0D / Math.tan(this.sig);
/* 166 */         if (Math.abs(del = this.projectionLatitude - this.sig) - 1.0E-10D >= 1.5707963267948966D)
/* 167 */           throw new ProjectionException("-43"); 
/* 168 */         this.rho_0 = this.c2 * (this.c1 - Math.tan(del));
/* 169 */         this.maxLatitude = Math.toRadians(60.0D);
/*     */         break;
/*     */       case 6:
/* 172 */         this.n = (cs = Math.tan(del)) * Math.sin(this.sig) / del;
/* 173 */         this.rho_c = del / cs * Math.tan(this.sig) + this.sig;
/* 174 */         this.rho_0 = this.rho_c - this.projectionLatitude;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\SimpleConicProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */