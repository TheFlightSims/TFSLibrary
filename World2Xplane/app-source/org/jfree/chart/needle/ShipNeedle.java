/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ShipNeedle extends MeterNeedle implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 149554868169435612L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle) {
/*  79 */     GeneralPath shape = new GeneralPath();
/*  80 */     shape.append(new Arc2D.Double(-9.0D, -7.0D, 10.0D, 14.0D, 0.0D, 25.5D, 0), true);
/*  83 */     shape.append(new Arc2D.Double(0.0D, -7.0D, 10.0D, 14.0D, 154.5D, 25.5D, 0), true);
/*  86 */     shape.closePath();
/*  87 */     getTransform().setToTranslation(plotArea.getMinX(), plotArea.getMaxY());
/*  88 */     getTransform().scale(plotArea.getWidth(), plotArea.getHeight() / 3.0D);
/*  89 */     shape.transform(getTransform());
/*  91 */     if (rotate != null && angle != 0.0D) {
/*  93 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/*  94 */       shape.transform(getTransform());
/*     */     } 
/*  97 */     defaultDisplay(g2, shape);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 108 */     if (object == null)
/* 109 */       return false; 
/* 111 */     if (object == this)
/* 112 */       return true; 
/* 114 */     if (super.equals(object) && object instanceof ShipNeedle)
/* 115 */       return true; 
/* 117 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 126 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\needle\ShipNeedle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */