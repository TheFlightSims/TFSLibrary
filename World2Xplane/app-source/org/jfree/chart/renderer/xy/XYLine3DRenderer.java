/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.Effect3D;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ 
/*     */ public class XYLine3DRenderer extends XYLineAndShapeRenderer implements Effect3D, Serializable {
/*  70 */   public static final Paint DEFAULT_WALL_PAINT = new Color(221, 221, 221);
/*     */   
/*  85 */   private transient Paint wallPaint = DEFAULT_WALL_PAINT;
/*     */   
/*  86 */   private double xOffset = 12.0D;
/*     */   
/*  87 */   private double yOffset = 8.0D;
/*     */   
/*     */   private static final long serialVersionUID = 588933208243446087L;
/*     */   
/*     */   public static final double DEFAULT_X_OFFSET = 12.0D;
/*     */   
/*     */   public static final double DEFAULT_Y_OFFSET = 8.0D;
/*     */   
/*     */   public double getXOffset() {
/*  96 */     return this.xOffset;
/*     */   }
/*     */   
/*     */   public double getYOffset() {
/* 105 */     return this.yOffset;
/*     */   }
/*     */   
/*     */   public void setXOffset(double xOffset) {
/* 115 */     this.xOffset = xOffset;
/* 116 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void setYOffset(double yOffset) {
/* 126 */     this.yOffset = yOffset;
/* 127 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getWallPaint() {
/* 137 */     return this.wallPaint;
/*     */   }
/*     */   
/*     */   public void setWallPaint(Paint paint) {
/* 147 */     this.wallPaint = paint;
/* 148 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public int getPassCount() {
/* 159 */     return 3;
/*     */   }
/*     */   
/*     */   protected boolean isLinePass(int pass) {
/* 170 */     return (pass == 0 || pass == 1);
/*     */   }
/*     */   
/*     */   protected boolean isItemPass(int pass) {
/* 181 */     return (pass == 2);
/*     */   }
/*     */   
/*     */   protected boolean isShadowPass(int pass) {
/* 192 */     return (pass == 0);
/*     */   }
/*     */   
/*     */   protected void drawFirstPassShape(Graphics2D g2, int pass, int series, int item, Shape shape) {
/* 209 */     if (isShadowPass(pass)) {
/* 210 */       if (getWallPaint() != null) {
/* 211 */         g2.setStroke(getItemStroke(series, item));
/* 212 */         g2.setPaint(getWallPaint());
/* 213 */         g2.translate(getXOffset(), getYOffset());
/* 214 */         g2.draw(shape);
/* 215 */         g2.translate(-getXOffset(), -getYOffset());
/*     */       } 
/*     */     } else {
/* 220 */       super.drawFirstPassShape(g2, pass, series, item, shape);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYLine3DRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */