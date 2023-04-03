/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class FloatDimension extends Dimension2D implements Serializable {
/*     */   private static final long serialVersionUID = 5367882923248086744L;
/*     */   
/*     */   private float width;
/*     */   
/*     */   private float height;
/*     */   
/*     */   public FloatDimension() {
/*  70 */     this.width = 0.0F;
/*  71 */     this.height = 0.0F;
/*     */   }
/*     */   
/*     */   public FloatDimension(FloatDimension fd) {
/*  80 */     this.width = fd.width;
/*  81 */     this.height = fd.height;
/*     */   }
/*     */   
/*     */   public FloatDimension(float width, float height) {
/*  91 */     this.width = width;
/*  92 */     this.height = height;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/* 101 */     return this.width;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/* 110 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setWidth(double width) {
/* 119 */     this.width = (float)width;
/*     */   }
/*     */   
/*     */   public void setHeight(double height) {
/* 128 */     this.height = (float)height;
/*     */   }
/*     */   
/*     */   public void setSize(double width, double height) {
/* 141 */     setHeight((float)height);
/* 142 */     setWidth((float)width);
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 152 */     return super.clone();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 166 */     return getClass().getName() + ":={width=" + getWidth() + ", height=" + getHeight() + "}";
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 178 */     if (this == o)
/* 179 */       return true; 
/* 181 */     if (!(o instanceof FloatDimension))
/* 182 */       return false; 
/* 185 */     FloatDimension floatDimension = (FloatDimension)o;
/* 187 */     if (this.height != floatDimension.height)
/* 188 */       return false; 
/* 190 */     if (this.width != floatDimension.width)
/* 191 */       return false; 
/* 194 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 204 */     int result = Float.floatToIntBits(this.width);
/* 205 */     result = 29 * result + Float.floatToIntBits(this.height);
/* 206 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\FloatDimension.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */