/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class LineNeedle extends MeterNeedle implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 6215321387896748945L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle) {
/*  78 */     Line2D shape = new Line2D.Double();
/*  80 */     double x = plotArea.getMinX() + plotArea.getWidth() / 2.0D;
/*  81 */     shape.setLine(x, plotArea.getMinY(), x, plotArea.getMaxY());
/*  83 */     Shape s = shape;
/*  85 */     if (rotate != null && angle != 0.0D) {
/*  87 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/*  88 */       s = getTransform().createTransformedShape(s);
/*     */     } 
/*  91 */     defaultDisplay(g2, s);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 103 */     if (obj == this)
/* 104 */       return true; 
/* 106 */     if (!(obj instanceof LineNeedle))
/* 107 */       return false; 
/* 109 */     if (!super.equals(obj))
/* 110 */       return false; 
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 121 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\needle\LineNeedle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */