/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Line2D;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import org.jfree.chart.plot.ColorPalette;
/*     */ 
/*     */ public class PaletteSample extends JComponent implements ListCellRenderer {
/*     */   private ColorPalette palette;
/*     */   
/*     */   private Dimension preferredSize;
/*     */   
/*     */   public PaletteSample(ColorPalette palette) {
/*  80 */     this.palette = palette;
/*  81 */     this.preferredSize = new Dimension(80, 18);
/*     */   }
/*     */   
/*     */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 102 */     if (value instanceof PaletteSample) {
/* 103 */       PaletteSample in = (PaletteSample)value;
/* 104 */       setPalette(in.getPalette());
/*     */     } 
/* 106 */     return this;
/*     */   }
/*     */   
/*     */   public ColorPalette getPalette() {
/* 115 */     return this.palette;
/*     */   }
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 124 */     return this.preferredSize;
/*     */   }
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 134 */     Graphics2D g2 = (Graphics2D)g;
/* 135 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/* 138 */     Dimension size = getSize();
/* 139 */     Insets insets = getInsets();
/* 140 */     double ww = size.getWidth() - insets.left - insets.right;
/* 141 */     double hh = size.getHeight() - insets.top - insets.bottom;
/* 143 */     g2.setStroke(new BasicStroke(1.0F));
/* 145 */     double y1 = insets.top;
/* 146 */     double y2 = y1 + hh;
/* 147 */     double xx = insets.left;
/* 148 */     Line2D line = new Line2D.Double();
/* 149 */     int count = 0;
/* 150 */     while (xx <= insets.left + ww) {
/* 151 */       count++;
/* 152 */       line.setLine(xx, y1, xx, y2);
/* 153 */       g2.setPaint(this.palette.getColor(count));
/* 154 */       g2.draw(line);
/* 155 */       xx++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPalette(ColorPalette palette) {
/* 165 */     this.palette = palette;
/* 166 */     repaint();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\editor\PaletteSample.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */