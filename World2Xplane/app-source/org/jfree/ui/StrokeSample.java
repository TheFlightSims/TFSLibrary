/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListCellRenderer;
/*     */ 
/*     */ public class StrokeSample extends JComponent implements ListCellRenderer {
/*     */   private Stroke stroke;
/*     */   
/*     */   private Dimension preferredSize;
/*     */   
/*     */   public StrokeSample(Stroke stroke) {
/*  82 */     this.stroke = stroke;
/*  83 */     this.preferredSize = new Dimension(80, 18);
/*     */   }
/*     */   
/*     */   public Stroke getStroke() {
/*  92 */     return this.stroke;
/*     */   }
/*     */   
/*     */   public void setStroke(Stroke stroke) {
/* 101 */     this.stroke = stroke;
/* 102 */     repaint();
/*     */   }
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 111 */     return this.preferredSize;
/*     */   }
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 121 */     Graphics2D g2 = (Graphics2D)g;
/* 122 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 123 */     Dimension size = getSize();
/* 124 */     Insets insets = getInsets();
/* 125 */     double xx = insets.left;
/* 126 */     double yy = insets.top;
/* 127 */     double ww = size.getWidth() - insets.left - insets.right;
/* 128 */     double hh = size.getHeight() - insets.top - insets.bottom;
/* 131 */     Point2D one = new Point2D.Double(xx + 6.0D, yy + hh / 2.0D);
/* 133 */     Point2D two = new Point2D.Double(xx + ww - 6.0D, yy + hh / 2.0D);
/* 135 */     Ellipse2D circle1 = new Ellipse2D.Double(one.getX() - 5.0D, one.getY() - 5.0D, 10.0D, 10.0D);
/* 136 */     Ellipse2D circle2 = new Ellipse2D.Double(two.getX() - 6.0D, two.getY() - 5.0D, 10.0D, 10.0D);
/* 139 */     g2.draw(circle1);
/* 140 */     g2.fill(circle1);
/* 141 */     g2.draw(circle2);
/* 142 */     g2.fill(circle2);
/* 145 */     Line2D line = new Line2D.Double(one, two);
/* 146 */     if (this.stroke != null) {
/* 147 */       g2.setStroke(this.stroke);
/*     */     } else {
/* 150 */       g2.setStroke(new BasicStroke(0.0F));
/*     */     } 
/* 152 */     g2.draw(line);
/*     */   }
/*     */   
/*     */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 170 */     if (value instanceof StrokeSample) {
/* 171 */       StrokeSample in = (StrokeSample)value;
/* 172 */       setStroke(in.getStroke());
/*     */     } 
/* 174 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\StrokeSample.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */