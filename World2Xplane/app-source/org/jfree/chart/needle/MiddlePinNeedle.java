/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class MiddlePinNeedle extends MeterNeedle implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 6237073996403125310L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle) {
/*  80 */     GeneralPath pointer = new GeneralPath();
/*  82 */     int minY = (int)plotArea.getMinY();
/*  84 */     int maxY = (int)plotArea.getMaxY();
/*  85 */     int midY = (maxY - minY) / 2 + minY;
/*  87 */     int midX = (int)(plotArea.getMinX() + plotArea.getWidth() / 2.0D);
/*  89 */     int lenX = (int)(plotArea.getWidth() / 10.0D);
/*  90 */     if (lenX < 2)
/*  91 */       lenX = 2; 
/*  94 */     pointer.moveTo((midX - lenX), (midY - lenX));
/*  95 */     pointer.lineTo((midX + lenX), (midY - lenX));
/*  96 */     pointer.lineTo(midX, minY);
/*  97 */     pointer.closePath();
/*  99 */     lenX = 4 * lenX;
/* 100 */     Ellipse2D circle = new Ellipse2D.Double((midX - lenX / 2), (midY - lenX), lenX, lenX);
/* 103 */     Area shape = new Area(circle);
/* 104 */     shape.add(new Area(pointer));
/* 105 */     if (rotate != null && angle != 0.0D) {
/* 107 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/* 108 */       shape.transform(getTransform());
/*     */     } 
/* 111 */     defaultDisplay(g2, shape);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 123 */     if (object == null)
/* 124 */       return false; 
/* 126 */     if (object == this)
/* 127 */       return true; 
/* 129 */     if (super.equals(object) && object instanceof MiddlePinNeedle)
/* 130 */       return true; 
/* 132 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 141 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\needle\MiddlePinNeedle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */