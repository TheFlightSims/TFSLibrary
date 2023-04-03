/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ public class PaintSample extends JComponent {
/*     */   private Paint paint;
/*     */   
/*     */   private Dimension preferredSize;
/*     */   
/*     */   public PaintSample(Paint paint) {
/*  75 */     this.paint = paint;
/*  76 */     this.preferredSize = new Dimension(80, 12);
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/*  85 */     return this.paint;
/*     */   }
/*     */   
/*     */   public void setPaint(Paint paint) {
/*  94 */     this.paint = paint;
/*  95 */     repaint();
/*     */   }
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 104 */     return this.preferredSize;
/*     */   }
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 114 */     Graphics2D g2 = (Graphics2D)g;
/* 115 */     Dimension size = getSize();
/* 116 */     Insets insets = getInsets();
/* 117 */     double xx = insets.left;
/* 118 */     double yy = insets.top;
/* 119 */     double ww = size.getWidth() - insets.left - insets.right - 1.0D;
/* 120 */     double hh = size.getHeight() - insets.top - insets.bottom - 1.0D;
/* 121 */     Rectangle2D area = new Rectangle2D.Double(xx, yy, ww, hh);
/* 122 */     g2.setPaint(this.paint);
/* 123 */     g2.fill(area);
/* 124 */     g2.setPaint(Color.black);
/* 125 */     g2.draw(area);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\PaintSample.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */