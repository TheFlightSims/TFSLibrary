/*     */ package uk.me.jstott.jcoord.ellipsoid;
/*     */ 
/*     */ public abstract class Ellipsoid {
/*     */   protected double semiMajorAxis;
/*     */   
/*     */   protected double semiMinorAxis;
/*     */   
/*     */   protected double eccentricitySquared;
/*     */   
/*     */   protected double flattening;
/*     */   
/*     */   public Ellipsoid(double semiMajorAxis, double semiMinorAxis) {
/*  59 */     this.semiMajorAxis = semiMajorAxis;
/*  60 */     this.semiMinorAxis = semiMinorAxis;
/*  61 */     double semiMajorAxisSquared = semiMajorAxis * semiMajorAxis;
/*  62 */     double semiMinorAxisSquared = semiMinorAxis * semiMinorAxis;
/*  63 */     this.flattening = (semiMajorAxis - semiMinorAxis) / semiMajorAxis;
/*  64 */     this.eccentricitySquared = (semiMajorAxisSquared - semiMinorAxisSquared) / semiMajorAxisSquared;
/*     */   }
/*     */   
/*     */   public Ellipsoid(double semiMajorAxis, double semiMinorAxis, double eccentricitySquared) throws IllegalArgumentException {
/*  90 */     if (Double.isNaN(semiMinorAxis) && Double.isNaN(eccentricitySquared))
/*  91 */       throw new IllegalArgumentException("At least one of semiMinorAxis and eccentricitySquared must be defined"); 
/*  95 */     this.semiMajorAxis = semiMajorAxis;
/*  96 */     double semiMajorAxisSquared = semiMajorAxis * semiMajorAxis;
/*  98 */     if (Double.isNaN(semiMinorAxis)) {
/*  99 */       this.semiMinorAxis = Math.sqrt(semiMajorAxisSquared * (1.0D - eccentricitySquared));
/*     */     } else {
/* 102 */       this.semiMinorAxis = semiMinorAxis;
/*     */     } 
/* 105 */     double semiMinorAxisSquared = this.semiMinorAxis * this.semiMinorAxis;
/* 107 */     this.flattening = (this.semiMajorAxis - this.semiMinorAxis) / this.semiMajorAxis;
/* 109 */     if (Double.isNaN(eccentricitySquared)) {
/* 110 */       this.eccentricitySquared = (semiMajorAxisSquared - semiMinorAxisSquared) / semiMajorAxisSquared;
/*     */     } else {
/* 113 */       this.eccentricitySquared = eccentricitySquared;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 125 */     return "[semi-major axis = " + getSemiMajorAxis() + ", semi-minor axis = " + getSemiMinorAxis() + "]";
/*     */   }
/*     */   
/*     */   public double getEccentricitySquared() {
/* 137 */     return this.eccentricitySquared;
/*     */   }
/*     */   
/*     */   public double getFlattening() {
/* 148 */     return this.flattening;
/*     */   }
/*     */   
/*     */   public double getSemiMajorAxis() {
/* 159 */     return this.semiMajorAxis;
/*     */   }
/*     */   
/*     */   public double getSemiMinorAxis() {
/* 170 */     return this.semiMinorAxis;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */