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
/*     */ public class PinNeedle extends MeterNeedle implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -3787089953079863373L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle) {
/*  80 */     GeneralPath pointer = new GeneralPath();
/*  82 */     int minY = (int)plotArea.getMinY();
/*  84 */     int maxY = (int)plotArea.getMaxY();
/*  85 */     int midX = (int)(plotArea.getMinX() + plotArea.getWidth() / 2.0D);
/*  87 */     int lenX = (int)(plotArea.getWidth() / 10.0D);
/*  88 */     if (lenX < 2)
/*  89 */       lenX = 2; 
/*  92 */     pointer.moveTo((midX - lenX), (maxY - lenX));
/*  93 */     pointer.lineTo((midX + lenX), (maxY - lenX));
/*  94 */     pointer.lineTo(midX, (minY + lenX));
/*  95 */     pointer.closePath();
/*  97 */     lenX = 4 * lenX;
/*  98 */     Ellipse2D circle = new Ellipse2D.Double((midX - lenX / 2), plotArea.getMaxY() - lenX, lenX, lenX);
/* 102 */     Area shape = new Area(circle);
/* 103 */     shape.add(new Area(pointer));
/* 104 */     if (rotate != null && angle != 0.0D) {
/* 106 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/* 107 */       shape.transform(getTransform());
/*     */     } 
/* 110 */     defaultDisplay(g2, shape);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 122 */     if (obj == this)
/* 123 */       return true; 
/* 125 */     if (!(obj instanceof PinNeedle))
/* 126 */       return false; 
/* 128 */     if (!super.equals(obj))
/* 129 */       return false; 
/* 131 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 140 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\needle\PinNeedle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */