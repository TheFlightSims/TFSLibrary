/*     */ package org.geotools.resources.geometry;
/*     */ 
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class XDimension2D {
/*     */   public static final class Float extends Dimension2D implements Serializable {
/*     */     private static final long serialVersionUID = 4011566975974105082L;
/*     */     
/*     */     public float width;
/*     */     
/*     */     public float height;
/*     */     
/*     */     public Float() {}
/*     */     
/*     */     public Float(float w, float h) {
/*  79 */       this.width = w;
/*  80 */       this.height = h;
/*     */     }
/*     */     
/*     */     public void setSize(double w, double h) {
/*  90 */       this.width = (float)w;
/*  91 */       this.height = (float)h;
/*     */     }
/*     */     
/*     */     public double getWidth() {
/*  98 */       return this.width;
/*     */     }
/*     */     
/*     */     public double getHeight() {
/* 105 */       return this.height;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 113 */       return "Dimension2D[" + this.width + ',' + this.height + ']';
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Double extends Dimension2D implements Serializable {
/*     */     private static final long serialVersionUID = 3603763914115376884L;
/*     */     
/*     */     public double width;
/*     */     
/*     */     public double height;
/*     */     
/*     */     public Double() {}
/*     */     
/*     */     public Double(double w, double h) {
/* 154 */       this.width = w;
/* 155 */       this.height = h;
/*     */     }
/*     */     
/*     */     public void setSize(double w, double h) {
/* 165 */       this.width = w;
/* 166 */       this.height = h;
/*     */     }
/*     */     
/*     */     public double getWidth() {
/* 173 */       return this.width;
/*     */     }
/*     */     
/*     */     public double getHeight() {
/* 180 */       return this.height;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 188 */       return "Dimension2D[" + this.width + ',' + this.height + ']';
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\geometry\XDimension2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */