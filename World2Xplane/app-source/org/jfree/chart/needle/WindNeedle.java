/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class WindNeedle extends ArrowNeedle implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -2861061368907167834L;
/*     */   
/*     */   public WindNeedle() {
/*  68 */     super(false);
/*     */   }
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle) {
/*  82 */     super.drawNeedle(g2, plotArea, rotate, angle);
/*  83 */     if (rotate != null && plotArea != null) {
/*  85 */       int spacing = getSize() * 3;
/*  86 */       Rectangle2D newArea = new Rectangle2D.Double();
/*  88 */       Point2D newRotate = rotate;
/*  89 */       newArea.setRect(plotArea.getMinX() - spacing, plotArea.getMinY(), plotArea.getWidth(), plotArea.getHeight());
/*  93 */       super.drawNeedle(g2, newArea, newRotate, angle);
/*  95 */       newArea.setRect(plotArea.getMinX() + spacing, plotArea.getMinY(), plotArea.getWidth(), plotArea.getHeight());
/*  99 */       super.drawNeedle(g2, newArea, newRotate, angle);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 112 */     if (object == null)
/* 113 */       return false; 
/* 115 */     if (object == this)
/* 116 */       return true; 
/* 118 */     if (super.equals(object) && object instanceof WindNeedle)
/* 119 */       return true; 
/* 121 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\needle\WindNeedle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */