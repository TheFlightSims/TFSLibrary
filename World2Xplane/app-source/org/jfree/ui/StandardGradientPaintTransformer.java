/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StandardGradientPaintTransformer implements GradientPaintTransformer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -8155025776964678320L;
/*     */   
/*     */   private GradientPaintTransformType type;
/*     */   
/*     */   public StandardGradientPaintTransformer() {
/*  72 */     this(GradientPaintTransformType.VERTICAL);
/*     */   }
/*     */   
/*     */   public StandardGradientPaintTransformer(GradientPaintTransformType type) {
/*  82 */     this.type = type;
/*     */   }
/*     */   
/*     */   public GradientPaint transform(GradientPaint paint, Shape target) {
/*  96 */     GradientPaint result = paint;
/*  97 */     Rectangle2D bounds = target.getBounds2D();
/*  99 */     if (this.type.equals(GradientPaintTransformType.VERTICAL)) {
/* 100 */       result = new GradientPaint((float)bounds.getCenterX(), (float)bounds.getMinY(), paint.getColor1(), (float)bounds.getCenterX(), (float)bounds.getMaxY(), paint.getColor2());
/* 106 */     } else if (this.type.equals(GradientPaintTransformType.HORIZONTAL)) {
/* 107 */       result = new GradientPaint((float)bounds.getMinX(), (float)bounds.getCenterY(), paint.getColor1(), (float)bounds.getMaxX(), (float)bounds.getCenterY(), paint.getColor2());
/* 113 */     } else if (this.type.equals(GradientPaintTransformType.CENTER_HORIZONTAL)) {
/* 115 */       result = new GradientPaint((float)bounds.getCenterX(), (float)bounds.getCenterY(), paint.getColor1(), (float)bounds.getMaxX(), (float)bounds.getCenterY(), paint.getColor2(), true);
/* 121 */     } else if (this.type.equals(GradientPaintTransformType.CENTER_VERTICAL)) {
/* 122 */       result = new GradientPaint((float)bounds.getCenterX(), (float)bounds.getMinY(), paint.getColor1(), (float)bounds.getCenterX(), (float)bounds.getCenterY(), paint.getColor2(), true);
/*     */     } 
/* 129 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 140 */     if (obj == this)
/* 141 */       return true; 
/* 143 */     if (!(obj instanceof StandardGradientPaintTransformer))
/* 144 */       return false; 
/* 146 */     StandardGradientPaintTransformer that = (StandardGradientPaintTransformer)obj;
/* 148 */     if (this.type != that.type)
/* 149 */       return false; 
/* 151 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 163 */     return super.clone();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 172 */     return (this.type != null) ? this.type.hashCode() : 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\StandardGradientPaintTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */