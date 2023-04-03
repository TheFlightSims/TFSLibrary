/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.MapMath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class EquidistantConicProjection extends ConicProjection {
/*     */   private double standardLatitude1;
/*     */   
/*     */   private double standardLatitude2;
/*     */   
/*  34 */   private double eccentricity = 0.822719D;
/*     */   
/*  35 */   private double eccentricity2 = this.eccentricity * this.eccentricity;
/*     */   
/*  36 */   private double eccentricity4 = this.eccentricity2 * this.eccentricity2;
/*     */   
/*  37 */   private double eccentricity6 = this.eccentricity2 * this.eccentricity4;
/*     */   
/*  38 */   private double radius = 1.0D;
/*     */   
/*     */   private boolean northPole;
/*     */   
/*     */   private double f;
/*     */   
/*     */   private double n;
/*     */   
/*     */   private double rho0;
/*     */   
/*     */   public EquidistantConicProjection() {
/*  44 */     this.minLatitude = MapMath.degToRad(10.0D);
/*  45 */     this.maxLatitude = MapMath.degToRad(70.0D);
/*  46 */     this.minLongitude = MapMath.degToRad(-90.0D);
/*  47 */     this.maxLongitude = MapMath.degToRad(90.0D);
/*  48 */     this.standardLatitude1 = Math.toDegrees(60.0D);
/*  49 */     this.standardLatitude2 = Math.toDegrees(20.0D);
/*  51 */     initialize(MapMath.degToRad(0.0D), MapMath.degToRad(37.5D), this.standardLatitude1, this.standardLatitude2);
/*     */   }
/*     */   
/*     */   public Point2D.Double transform(Point2D.Double in, Point2D.Double out) {
/*  55 */     double lon = MapMath.normalizeLongitude(in.x - this.projectionLongitude);
/*  56 */     double lat = in.y;
/*  59 */     double hold2 = Math.pow((1.0D - this.eccentricity * Math.sin(lat)) / (1.0D + this.eccentricity * Math.sin(lat)), 0.5D * this.eccentricity);
/*  60 */     double hold3 = Math.tan(0.7853981633974483D - 0.5D * lat);
/*  61 */     double hold1 = (hold3 == 0.0D) ? 0.0D : Math.pow(hold3 / hold2, this.n);
/*  62 */     double rho = this.radius * this.f * hold1;
/*  63 */     double theta = this.n * lon;
/*  65 */     out.x = rho * Math.sin(theta);
/*  66 */     out.y = this.rho0 - rho * Math.cos(theta);
/*  67 */     return out;
/*     */   }
/*     */   
/*     */   public Point2D.Double inverseTransform(Point2D.Double in, Point2D.Double out) {
/*  71 */     double phi = 0.0D;
/*  73 */     double theta = Math.atan(in.x / (this.rho0 - in.y));
/*  74 */     out.x = theta / this.n + this.projectionLongitude;
/*  76 */     double temp = in.x * in.x + (this.rho0 - in.y) * (this.rho0 - in.y);
/*  77 */     double rho = Math.sqrt(temp);
/*  78 */     if (this.n < 0.0D)
/*  79 */       rho = -rho; 
/*  80 */     double t = Math.pow(rho / this.radius * this.f, 1.0D / this.n);
/*  81 */     double tphi = 1.5707963267948966D - 2.0D * Math.atan(t);
/*  82 */     double delta = 1.0D;
/*  83 */     for (int i = 0; i < 100 && delta > 1.0E-8D; i++) {
/*  84 */       temp = (1.0D - this.eccentricity * Math.sin(tphi)) / (1.0D + this.eccentricity * Math.sin(tphi));
/*  85 */       phi = 1.5707963267948966D - 2.0D * Math.atan(t * Math.pow(temp, 0.5D * this.eccentricity));
/*  86 */       delta = Math.abs(Math.abs(tphi) - Math.abs(phi));
/*  87 */       tphi = phi;
/*     */     } 
/*  89 */     out.y = phi;
/*  90 */     return out;
/*     */   }
/*     */   
/*     */   private void initialize(double rlong0, double rlat0, double standardLatitude1, double standardLatitude2) {
/*  94 */     initialize();
/*  97 */     this.northPole = (rlat0 > 0.0D);
/*  98 */     this.projectionLatitude = this.northPole ? 1.5707963267948966D : -1.5707963267948966D;
/* 100 */     double t_standardLatitude1 = Math.tan(0.7853981633974483D - 0.5D * standardLatitude1) / Math.pow((1.0D - this.eccentricity * Math.sin(standardLatitude1)) / (1.0D + this.eccentricity * Math.sin(standardLatitude1)), 0.5D * this.eccentricity);
/* 102 */     double m_standardLatitude1 = Math.cos(standardLatitude1) / Math.sqrt(1.0D - this.eccentricity2 * Math.pow(Math.sin(standardLatitude1), 2.0D));
/* 104 */     double t_standardLatitude2 = Math.tan(0.7853981633974483D - 0.5D * standardLatitude2) / Math.pow((1.0D - this.eccentricity * Math.sin(standardLatitude2)) / (1.0D + this.eccentricity * Math.sin(standardLatitude2)), 0.5D * this.eccentricity);
/* 106 */     double m_standardLatitude2 = Math.cos(standardLatitude2) / Math.sqrt(1.0D - this.eccentricity2 * Math.pow(Math.sin(standardLatitude2), 2.0D));
/* 108 */     double t_rlat0 = Math.tan(0.7853981633974483D - 0.5D * rlat0) / Math.pow((1.0D - this.eccentricity * Math.sin(rlat0)) / (1.0D + this.eccentricity * Math.sin(rlat0)), 0.5D * this.eccentricity);
/* 112 */     if (standardLatitude1 != standardLatitude2) {
/* 113 */       this.n = (Math.log(m_standardLatitude1) - Math.log(m_standardLatitude2)) / (Math.log(t_standardLatitude1) - Math.log(t_standardLatitude2));
/*     */     } else {
/* 115 */       this.n = Math.sin(standardLatitude1);
/*     */     } 
/* 117 */     this.f = m_standardLatitude1 / this.n * Math.pow(t_standardLatitude1, this.n);
/* 118 */     this.projectionLongitude = rlong0;
/* 119 */     this.rho0 = this.radius * this.f * Math.pow(t_rlat0, this.n);
/*     */   }
/*     */   
/*     */   public boolean hasInverse() {
/* 123 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 127 */     return "Equidistant Conic";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\EquidistantConicProjection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */