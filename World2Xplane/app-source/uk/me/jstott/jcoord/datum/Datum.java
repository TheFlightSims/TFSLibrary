/*     */ package uk.me.jstott.jcoord.datum;
/*     */ 
/*     */ import uk.me.jstott.jcoord.ellipsoid.Ellipsoid;
/*     */ 
/*     */ public abstract class Datum {
/*     */   protected String name;
/*     */   
/*     */   protected Ellipsoid ellipsoid;
/*     */   
/*     */   protected double dx;
/*     */   
/*     */   protected double dy;
/*     */   
/*     */   protected double dz;
/*     */   
/*     */   protected double ds;
/*     */   
/*     */   protected double rx;
/*     */   
/*     */   protected double ry;
/*     */   
/*     */   protected double rz;
/*     */   
/*     */   public String getName() {
/*  94 */     return this.name;
/*     */   }
/*     */   
/*     */   public Ellipsoid getReferenceEllipsoid() {
/* 105 */     return this.ellipsoid;
/*     */   }
/*     */   
/*     */   public double getDs() {
/* 116 */     return this.ds;
/*     */   }
/*     */   
/*     */   public double getDx() {
/* 127 */     return this.dx;
/*     */   }
/*     */   
/*     */   public double getDy() {
/* 138 */     return this.dy;
/*     */   }
/*     */   
/*     */   public double getDz() {
/* 149 */     return this.dz;
/*     */   }
/*     */   
/*     */   public double getRx() {
/* 160 */     return this.rx;
/*     */   }
/*     */   
/*     */   public double getRy() {
/* 171 */     return this.ry;
/*     */   }
/*     */   
/*     */   public double getRz() {
/* 182 */     return this.rz;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 193 */     return getName() + " " + this.ellipsoid.toString() + " dx=" + this.dx + " dy=" + this.dy + " dz=" + this.dz + " ds=" + this.ds + " rx=" + this.rx + " ry=" + this.ry + " rz=" + this.rz;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\datum\Datum.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */