/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import org.geotools.referencing.operation.transform.AffineTransform2D;
/*     */ 
/*     */ public class AffineToGeometric {
/*     */   private double tx;
/*     */   
/*     */   private double ty;
/*     */   
/*     */   private double sx;
/*     */   
/*     */   private double sy;
/*     */   
/*     */   private double phix;
/*     */   
/*     */   private double phiy;
/*     */   
/*     */   private double sxy;
/*     */   
/*     */   public AffineToGeometric(AffineTransform2D trans) {
/*  61 */     this.sx = Math.pow(Math.pow(trans.getShearY(), 2.0D) + Math.pow(trans.getScaleX(), 2.0D), 0.5D);
/*  62 */     this.sy = Math.pow(Math.pow(trans.getScaleY(), 2.0D) + Math.pow(trans.getShearX(), 2.0D), 0.5D);
/*  63 */     this.phix = Math.acos(Math.signum(trans.getShearY()) * trans.getScaleX() / this.sx);
/*  64 */     this.phiy = Math.acos(Math.signum(-trans.getShearX()) * trans.getScaleY() / this.sy);
/*  65 */     this.sxy = this.phix - this.phiy;
/*  66 */     this.tx = trans.getTranslateX();
/*  67 */     this.ty = trans.getTranslateY();
/*     */   }
/*     */   
/*     */   public double getXScale() {
/*  76 */     return this.sx;
/*     */   }
/*     */   
/*     */   public double getYScale() {
/*  84 */     return this.sy;
/*     */   }
/*     */   
/*     */   public double getSkew() {
/*  92 */     return this.sxy;
/*     */   }
/*     */   
/*     */   public double getXTranslate() {
/* 100 */     return this.tx;
/*     */   }
/*     */   
/*     */   public double getYTranslate() {
/* 108 */     return this.ty;
/*     */   }
/*     */   
/*     */   public double getXRotation() {
/* 116 */     return this.phix;
/*     */   }
/*     */   
/*     */   public double getYRotation() {
/* 120 */     return this.phiy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\AffineToGeometric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */