/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class Size2D implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 2558191683786418168L;
/*     */   
/*     */   public double width;
/*     */   
/*     */   public double height;
/*     */   
/*     */   public Size2D() {
/*  77 */     this(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Size2D(double width, double height) {
/*  87 */     this.width = width;
/*  88 */     this.height = height;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/*  97 */     return this.width;
/*     */   }
/*     */   
/*     */   public void setWidth(double width) {
/* 106 */     this.width = width;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/* 115 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setHeight(double height) {
/* 124 */     this.height = height;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 134 */     return "Size2D[width=" + this.width + ", height=" + this.height + "]";
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 145 */     if (this == obj)
/* 146 */       return true; 
/* 148 */     if (!(obj instanceof Size2D))
/* 149 */       return false; 
/* 151 */     Size2D that = (Size2D)obj;
/* 152 */     if (this.width != that.width)
/* 153 */       return false; 
/* 155 */     if (this.height != that.height)
/* 156 */       return false; 
/* 158 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 169 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\Size2D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */