/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class ClipPath implements Cloneable {
/*  72 */   private double[] xValue = null;
/*     */   
/*  75 */   private double[] yValue = null;
/*     */   
/*     */   private boolean clip = true;
/*     */   
/*     */   private boolean drawPath = false;
/*     */   
/*     */   private boolean fillPath = false;
/*     */   
/*  88 */   private Paint fillPaint = null;
/*     */   
/*  91 */   private Paint drawPaint = null;
/*     */   
/*  94 */   private Stroke drawStroke = null;
/*     */   
/*  97 */   private Composite composite = null;
/*     */   
/*     */   public ClipPath() {}
/*     */   
/*     */   public ClipPath(double[] xValue, double[] yValue) {
/* 117 */     this(xValue, yValue, true, false, true);
/*     */   }
/*     */   
/*     */   public ClipPath(double[] xValue, double[] yValue, boolean clip, boolean fillPath, boolean drawPath) {
/* 134 */     this.xValue = xValue;
/* 135 */     this.yValue = yValue;
/* 137 */     this.clip = clip;
/* 138 */     this.fillPath = fillPath;
/* 139 */     this.drawPath = drawPath;
/* 141 */     this.fillPaint = Color.gray;
/* 142 */     this.drawPaint = Color.blue;
/* 143 */     this.drawStroke = new BasicStroke(1.0F);
/* 144 */     this.composite = AlphaComposite.Src;
/*     */   }
/*     */   
/*     */   public ClipPath(double[] xValue, double[] yValue, boolean fillPath, boolean drawPath, Paint fillPaint, Paint drawPaint, Stroke drawStroke, Composite composite) {
/* 163 */     this.xValue = xValue;
/* 164 */     this.yValue = yValue;
/* 166 */     this.fillPath = fillPath;
/* 167 */     this.drawPath = drawPath;
/* 169 */     this.fillPaint = fillPaint;
/* 170 */     this.drawPaint = drawPaint;
/* 171 */     this.drawStroke = drawStroke;
/* 172 */     this.composite = composite;
/*     */   }
/*     */   
/*     */   public GeneralPath draw(Graphics2D g2, Rectangle2D dataArea, ValueAxis horizontalAxis, ValueAxis verticalAxis) {
/* 190 */     GeneralPath generalPath = generateClipPath(dataArea, horizontalAxis, verticalAxis);
/* 193 */     if (this.fillPath || this.drawPath) {
/* 194 */       Composite saveComposite = g2.getComposite();
/* 195 */       Paint savePaint = g2.getPaint();
/* 196 */       Stroke saveStroke = g2.getStroke();
/* 198 */       if (this.fillPaint != null)
/* 199 */         g2.setPaint(this.fillPaint); 
/* 201 */       if (this.composite != null)
/* 202 */         g2.setComposite(this.composite); 
/* 204 */       if (this.fillPath)
/* 205 */         g2.fill(generalPath); 
/* 208 */       if (this.drawStroke != null)
/* 209 */         g2.setStroke(this.drawStroke); 
/* 211 */       if (this.drawPath)
/* 212 */         g2.draw(generalPath); 
/* 214 */       g2.setPaint(savePaint);
/* 215 */       g2.setComposite(saveComposite);
/* 216 */       g2.setStroke(saveStroke);
/*     */     } 
/* 218 */     return generalPath;
/*     */   }
/*     */   
/*     */   public GeneralPath generateClipPath(Rectangle2D dataArea, ValueAxis horizontalAxis, ValueAxis verticalAxis) {
/* 235 */     GeneralPath generalPath = new GeneralPath();
/* 236 */     double transX = horizontalAxis.valueToJava2D(this.xValue[0], dataArea, RectangleEdge.BOTTOM);
/* 239 */     double transY = verticalAxis.valueToJava2D(this.yValue[0], dataArea, RectangleEdge.LEFT);
/* 242 */     generalPath.moveTo((float)transX, (float)transY);
/* 243 */     for (int k = 0; k < this.yValue.length; k++) {
/* 244 */       transX = horizontalAxis.valueToJava2D(this.xValue[k], dataArea, RectangleEdge.BOTTOM);
/* 247 */       transY = verticalAxis.valueToJava2D(this.yValue[k], dataArea, RectangleEdge.LEFT);
/* 250 */       generalPath.lineTo((float)transX, (float)transY);
/*     */     } 
/* 252 */     generalPath.closePath();
/* 254 */     return generalPath;
/*     */   }
/*     */   
/*     */   public Composite getComposite() {
/* 264 */     return this.composite;
/*     */   }
/*     */   
/*     */   public Paint getDrawPaint() {
/* 273 */     return this.drawPaint;
/*     */   }
/*     */   
/*     */   public boolean isDrawPath() {
/* 282 */     return this.drawPath;
/*     */   }
/*     */   
/*     */   public Stroke getDrawStroke() {
/* 291 */     return this.drawStroke;
/*     */   }
/*     */   
/*     */   public Paint getFillPaint() {
/* 300 */     return this.fillPaint;
/*     */   }
/*     */   
/*     */   public boolean isFillPath() {
/* 309 */     return this.fillPath;
/*     */   }
/*     */   
/*     */   public double[] getXValue() {
/* 318 */     return this.xValue;
/*     */   }
/*     */   
/*     */   public double[] getYValue() {
/* 327 */     return this.yValue;
/*     */   }
/*     */   
/*     */   public void setComposite(Composite composite) {
/* 336 */     this.composite = composite;
/*     */   }
/*     */   
/*     */   public void setDrawPaint(Paint drawPaint) {
/* 345 */     this.drawPaint = drawPaint;
/*     */   }
/*     */   
/*     */   public void setDrawPath(boolean drawPath) {
/* 354 */     this.drawPath = drawPath;
/*     */   }
/*     */   
/*     */   public void setDrawStroke(Stroke drawStroke) {
/* 363 */     this.drawStroke = drawStroke;
/*     */   }
/*     */   
/*     */   public void setFillPaint(Paint fillPaint) {
/* 372 */     this.fillPaint = fillPaint;
/*     */   }
/*     */   
/*     */   public void setFillPath(boolean fillPath) {
/* 381 */     this.fillPath = fillPath;
/*     */   }
/*     */   
/*     */   public void setXValue(double[] xValue) {
/* 390 */     this.xValue = xValue;
/*     */   }
/*     */   
/*     */   public void setYValue(double[] yValue) {
/* 399 */     this.yValue = yValue;
/*     */   }
/*     */   
/*     */   public boolean isClip() {
/* 408 */     return this.clip;
/*     */   }
/*     */   
/*     */   public void setClip(boolean clip) {
/* 417 */     this.clip = clip;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 429 */     ClipPath clone = (ClipPath)super.clone();
/* 430 */     clone.xValue = (double[])this.xValue.clone();
/* 431 */     clone.yValue = (double[])this.yValue.clone();
/* 432 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\ClipPath.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */