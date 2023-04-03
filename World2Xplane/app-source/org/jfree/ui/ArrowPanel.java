/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class ArrowPanel extends JPanel {
/*     */   public static final int UP = 0;
/*     */   
/*     */   public static final int DOWN = 1;
/*     */   
/*  70 */   private int type = 0;
/*     */   
/*  73 */   private Rectangle2D available = new Rectangle2D.Float();
/*     */   
/*     */   public ArrowPanel(int type) {
/*  81 */     this.type = type;
/*  82 */     setPreferredSize(new Dimension(14, 9));
/*     */   }
/*     */   
/*     */   public void paintComponent(Graphics g) {
/*  92 */     super.paintComponent(g);
/*  93 */     Graphics2D g2 = (Graphics2D)g;
/*  96 */     Dimension size = getSize();
/*  97 */     Insets insets = getInsets();
/*  98 */     this.available.setRect(insets.left, insets.top, size.getWidth() - insets.left - insets.right, size.getHeight() - insets.top - insets.bottom);
/* 101 */     g2.translate(insets.left, insets.top);
/* 102 */     g2.fill(getArrow(this.type));
/*     */   }
/*     */   
/*     */   private Shape getArrow(int t) {
/* 114 */     switch (t) {
/*     */       case 0:
/* 115 */         return getUpArrow();
/*     */       case 1:
/* 116 */         return getDownArrow();
/*     */     } 
/* 117 */     return getUpArrow();
/*     */   }
/*     */   
/*     */   private Shape getUpArrow() {
/* 127 */     Polygon result = new Polygon();
/* 128 */     result.addPoint(7, 2);
/* 129 */     result.addPoint(2, 7);
/* 130 */     result.addPoint(12, 7);
/* 131 */     return result;
/*     */   }
/*     */   
/*     */   private Shape getDownArrow() {
/* 140 */     Polygon result = new Polygon();
/* 141 */     result.addPoint(7, 7);
/* 142 */     result.addPoint(2, 2);
/* 143 */     result.addPoint(12, 2);
/* 144 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\ArrowPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */