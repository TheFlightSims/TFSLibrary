/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ArrowNeedle extends MeterNeedle implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -5334056511213782357L;
/*     */   
/*     */   private boolean isArrowAtTop = true;
/*     */   
/*     */   public ArrowNeedle(boolean isArrowAtTop) {
/*  80 */     this.isArrowAtTop = isArrowAtTop;
/*     */   }
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle) {
/*  94 */     Line2D shape = new Line2D.Float();
/*  95 */     Shape d = null;
/*  97 */     float x = (float)(plotArea.getMinX() + plotArea.getWidth() / 2.0D);
/*  98 */     float minY = (float)plotArea.getMinY();
/*  99 */     float maxY = (float)plotArea.getMaxY();
/* 100 */     shape.setLine(x, minY, x, maxY);
/* 102 */     GeneralPath shape1 = new GeneralPath();
/* 103 */     if (this.isArrowAtTop) {
/* 104 */       shape1.moveTo(x, minY);
/* 105 */       minY += (4 * getSize());
/*     */     } else {
/* 108 */       shape1.moveTo(x, maxY);
/* 109 */       minY = maxY - (4 * getSize());
/*     */     } 
/* 111 */     shape1.lineTo(x + getSize(), minY);
/* 112 */     shape1.lineTo(x - getSize(), minY);
/* 113 */     shape1.closePath();
/* 115 */     if (rotate != null && angle != 0.0D) {
/* 116 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/* 117 */       d = getTransform().createTransformedShape(shape);
/*     */     } else {
/* 120 */       d = shape;
/*     */     } 
/* 122 */     defaultDisplay(g2, d);
/* 124 */     if (rotate != null && angle != 0.0D) {
/* 125 */       d = getTransform().createTransformedShape(shape1);
/*     */     } else {
/* 128 */       d = shape1;
/*     */     } 
/* 130 */     defaultDisplay(g2, d);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 142 */     if (obj == this)
/* 143 */       return true; 
/* 145 */     if (!(obj instanceof ArrowNeedle))
/* 146 */       return false; 
/* 148 */     if (!super.equals(obj))
/* 149 */       return false; 
/* 151 */     ArrowNeedle that = (ArrowNeedle)obj;
/* 152 */     if (this.isArrowAtTop != that.isArrowAtTop)
/* 153 */       return false; 
/* 155 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 164 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\needle\ArrowNeedle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */