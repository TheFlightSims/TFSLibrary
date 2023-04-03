/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class LongNeedle extends MeterNeedle implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -4319985779783688159L;
/*     */   
/*     */   public LongNeedle() {
/*  71 */     setRotateY(0.8D);
/*     */   }
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle) {
/*  85 */     GeneralPath shape1 = new GeneralPath();
/*  86 */     GeneralPath shape2 = new GeneralPath();
/*  87 */     GeneralPath shape3 = new GeneralPath();
/*  89 */     float minX = (float)plotArea.getMinX();
/*  90 */     float minY = (float)plotArea.getMinY();
/*  91 */     float maxX = (float)plotArea.getMaxX();
/*  92 */     float maxY = (float)plotArea.getMaxY();
/*  95 */     float midX = (float)(minX + plotArea.getWidth() * 0.5D);
/*  96 */     float midY = (float)(minY + plotArea.getHeight() * 0.8D);
/*  97 */     float y = maxY - 2.0F * (maxY - midY);
/*  98 */     if (y < minY)
/*  99 */       y = minY; 
/* 101 */     shape1.moveTo(minX, midY);
/* 102 */     shape1.lineTo(midX, minY);
/* 103 */     shape1.lineTo(midX, y);
/* 104 */     shape1.closePath();
/* 106 */     shape2.moveTo(maxX, midY);
/* 107 */     shape2.lineTo(midX, minY);
/* 108 */     shape2.lineTo(midX, y);
/* 109 */     shape2.closePath();
/* 111 */     shape3.moveTo(minX, midY);
/* 112 */     shape3.lineTo(midX, maxY);
/* 113 */     shape3.lineTo(maxX, midY);
/* 114 */     shape3.lineTo(midX, y);
/* 115 */     shape3.closePath();
/* 117 */     Shape s1 = shape1;
/* 118 */     Shape s2 = shape2;
/* 119 */     Shape s3 = shape3;
/* 121 */     if (rotate != null && angle != 0.0D) {
/* 123 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/* 124 */       s1 = shape1.createTransformedShape(transform);
/* 125 */       s2 = shape2.createTransformedShape(transform);
/* 126 */       s3 = shape3.createTransformedShape(transform);
/*     */     } 
/* 130 */     if (getHighlightPaint() != null) {
/* 131 */       g2.setPaint(getHighlightPaint());
/* 132 */       g2.fill(s3);
/*     */     } 
/* 135 */     if (getFillPaint() != null) {
/* 136 */       g2.setPaint(getFillPaint());
/* 137 */       g2.fill(s1);
/* 138 */       g2.fill(s2);
/*     */     } 
/* 142 */     if (getOutlinePaint() != null) {
/* 143 */       g2.setStroke(getOutlineStroke());
/* 144 */       g2.setPaint(getOutlinePaint());
/* 145 */       g2.draw(s1);
/* 146 */       g2.draw(s2);
/* 147 */       g2.draw(s3);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 159 */     if (obj == this)
/* 160 */       return true; 
/* 162 */     if (!(obj instanceof LongNeedle))
/* 163 */       return false; 
/* 165 */     if (!super.equals(obj))
/* 166 */       return false; 
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 177 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\needle\LongNeedle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */