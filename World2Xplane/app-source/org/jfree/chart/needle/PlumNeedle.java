/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class PlumNeedle extends MeterNeedle implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -3082660488660600718L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle) {
/*  78 */     Arc2D shape = new Arc2D.Double(2);
/*  79 */     double radius = plotArea.getHeight();
/*  80 */     double halfX = plotArea.getWidth() / 2.0D;
/*  81 */     double diameter = 2.0D * radius;
/*  83 */     shape.setFrame(plotArea.getMinX() + halfX - radius, plotArea.getMinY() - radius, diameter, diameter);
/*  86 */     radius = Math.toDegrees(Math.asin(halfX / radius));
/*  87 */     shape.setAngleStart(270.0D - radius);
/*  88 */     shape.setAngleExtent(2.0D * radius);
/*  90 */     Area s = new Area(shape);
/*  92 */     if (rotate != null && angle != 0.0D) {
/*  94 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/*  95 */       s.transform(getTransform());
/*     */     } 
/*  98 */     defaultDisplay(g2, s);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 109 */     if (obj == this)
/* 110 */       return true; 
/* 112 */     if (!(obj instanceof PlumNeedle))
/* 113 */       return false; 
/* 115 */     if (!super.equals(obj))
/* 116 */       return false; 
/* 118 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 127 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\needle\PlumNeedle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */