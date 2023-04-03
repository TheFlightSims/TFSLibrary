/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class PointerNeedle extends MeterNeedle implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -4744677345334729606L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle) {
/*  78 */     GeneralPath shape1 = new GeneralPath();
/*  79 */     GeneralPath shape2 = new GeneralPath();
/*  80 */     float minX = (float)plotArea.getMinX();
/*  81 */     float minY = (float)plotArea.getMinY();
/*  82 */     float maxX = (float)plotArea.getMaxX();
/*  83 */     float maxY = (float)plotArea.getMaxY();
/*  84 */     float midX = (float)(minX + plotArea.getWidth() / 2.0D);
/*  85 */     float midY = (float)(minY + plotArea.getHeight() / 2.0D);
/*  87 */     shape1.moveTo(minX, midY);
/*  88 */     shape1.lineTo(midX, minY);
/*  89 */     shape1.lineTo(maxX, midY);
/*  90 */     shape1.closePath();
/*  92 */     shape2.moveTo(minX, midY);
/*  93 */     shape2.lineTo(midX, maxY);
/*  94 */     shape2.lineTo(maxX, midY);
/*  95 */     shape2.closePath();
/*  97 */     if (rotate != null && angle != 0.0D) {
/*  99 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/* 100 */       shape1.transform(getTransform());
/* 101 */       shape2.transform(getTransform());
/*     */     } 
/* 104 */     if (getFillPaint() != null) {
/* 105 */       g2.setPaint(getFillPaint());
/* 106 */       g2.fill(shape1);
/*     */     } 
/* 109 */     if (getHighlightPaint() != null) {
/* 110 */       g2.setPaint(getHighlightPaint());
/* 111 */       g2.fill(shape2);
/*     */     } 
/* 114 */     if (getOutlinePaint() != null) {
/* 115 */       g2.setStroke(getOutlineStroke());
/* 116 */       g2.setPaint(getOutlinePaint());
/* 117 */       g2.draw(shape1);
/* 118 */       g2.draw(shape2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 130 */     if (obj == this)
/* 131 */       return true; 
/* 133 */     if (!(obj instanceof PointerNeedle))
/* 134 */       return false; 
/* 136 */     if (!super.equals(obj))
/* 137 */       return false; 
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 148 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\needle\PointerNeedle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */