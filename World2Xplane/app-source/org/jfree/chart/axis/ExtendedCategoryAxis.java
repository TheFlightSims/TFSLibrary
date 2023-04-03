/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.jfree.text.TextBlock;
/*     */ import org.jfree.text.TextFragment;
/*     */ import org.jfree.text.TextLine;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class ExtendedCategoryAxis extends CategoryAxis {
/*     */   private Map sublabels;
/*     */   
/*     */   private Font sublabelFont;
/*     */   
/*     */   private Paint sublabelPaint;
/*     */   
/*     */   public ExtendedCategoryAxis(String label) {
/*  80 */     super(label);
/*  81 */     this.sublabels = new HashMap();
/*  82 */     this.sublabelFont = new Font("SansSerif", 0, 10);
/*  83 */     this.sublabelPaint = Color.black;
/*     */   }
/*     */   
/*     */   public Font getSubLabelFont() {
/*  92 */     return this.sublabelFont;
/*     */   }
/*     */   
/*     */   public void setSubLabelFont(Font font) {
/* 101 */     this.sublabelFont = font;
/*     */   }
/*     */   
/*     */   public Paint getSubLabelPaint() {
/* 110 */     return this.sublabelPaint;
/*     */   }
/*     */   
/*     */   public void setSubLabelPaint(Paint paint) {
/* 119 */     this.sublabelPaint = paint;
/*     */   }
/*     */   
/*     */   public void addSubLabel(Comparable category, String label) {
/* 129 */     this.sublabels.put(category, label);
/*     */   }
/*     */   
/*     */   protected TextBlock createLabel(Comparable category, float width, RectangleEdge edge, Graphics2D g2) {
/* 145 */     TextBlock label = super.createLabel(category, width, edge, g2);
/* 146 */     String s = (String)this.sublabels.get(category);
/* 147 */     if (s != null)
/* 148 */       if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
/* 149 */         TextLine line = new TextLine(s, this.sublabelFont, this.sublabelPaint);
/* 152 */         label.addLine(line);
/* 154 */       } else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
/* 156 */         TextLine line = label.getLastLine();
/* 157 */         if (line != null)
/* 158 */           line.addFragment(new TextFragment("  " + s, this.sublabelFont, this.sublabelPaint)); 
/*     */       }  
/* 166 */     return label;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\ExtendedCategoryAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */