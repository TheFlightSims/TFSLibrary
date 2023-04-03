/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYTextAnnotation extends AbstractXYAnnotation implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -2946063342782506328L;
/*     */   
/*  89 */   public static final Font DEFAULT_FONT = new Font("SansSerif", 0, 10);
/*     */   
/*  93 */   public static final Paint DEFAULT_PAINT = Color.black;
/*     */   
/*  96 */   public static final TextAnchor DEFAULT_TEXT_ANCHOR = TextAnchor.CENTER;
/*     */   
/*  99 */   public static final TextAnchor DEFAULT_ROTATION_ANCHOR = TextAnchor.CENTER;
/*     */   
/*     */   public static final double DEFAULT_ROTATION_ANGLE = 0.0D;
/*     */   
/*     */   private String text;
/*     */   
/*     */   private Font font;
/*     */   
/*     */   private transient Paint paint;
/*     */   
/*     */   private double x;
/*     */   
/*     */   private double y;
/*     */   
/*     */   private TextAnchor textAnchor;
/*     */   
/*     */   private TextAnchor rotationAnchor;
/*     */   
/*     */   private double rotationAngle;
/*     */   
/*     */   public XYTextAnnotation(String text, double x, double y) {
/* 138 */     if (text == null)
/* 139 */       throw new IllegalArgumentException("Null 'text' argument."); 
/* 141 */     this.text = text;
/* 142 */     this.font = DEFAULT_FONT;
/* 143 */     this.paint = DEFAULT_PAINT;
/* 144 */     this.x = x;
/* 145 */     this.y = y;
/* 146 */     this.textAnchor = DEFAULT_TEXT_ANCHOR;
/* 147 */     this.rotationAnchor = DEFAULT_ROTATION_ANCHOR;
/* 148 */     this.rotationAngle = 0.0D;
/*     */   }
/*     */   
/*     */   public String getText() {
/* 157 */     return this.text;
/*     */   }
/*     */   
/*     */   public void setText(String text) {
/* 166 */     this.text = text;
/*     */   }
/*     */   
/*     */   public Font getFont() {
/* 175 */     return this.font;
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 184 */     this.font = font;
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 193 */     return this.paint;
/*     */   }
/*     */   
/*     */   public void setPaint(Paint paint) {
/* 202 */     this.paint = paint;
/*     */   }
/*     */   
/*     */   public TextAnchor getTextAnchor() {
/* 211 */     return this.textAnchor;
/*     */   }
/*     */   
/*     */   public void setTextAnchor(TextAnchor anchor) {
/* 221 */     this.textAnchor = anchor;
/*     */   }
/*     */   
/*     */   public TextAnchor getRotationAnchor() {
/* 230 */     return this.rotationAnchor;
/*     */   }
/*     */   
/*     */   public void setRotationAnchor(TextAnchor anchor) {
/* 239 */     this.rotationAnchor = anchor;
/*     */   }
/*     */   
/*     */   public double getRotationAngle() {
/* 248 */     return this.rotationAngle;
/*     */   }
/*     */   
/*     */   public void setRotationAngle(double angle) {
/* 259 */     this.rotationAngle = angle;
/*     */   }
/*     */   
/*     */   public double getX() {
/* 269 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(double x) {
/* 279 */     this.x = x;
/*     */   }
/*     */   
/*     */   public double getY() {
/* 289 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(double y) {
/* 299 */     this.y = y;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info) {
/* 319 */     PlotOrientation orientation = plot.getOrientation();
/* 320 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/* 323 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/* 327 */     float anchorX = (float)domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
/* 330 */     float anchorY = (float)rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
/* 334 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 335 */       float tempAnchor = anchorX;
/* 336 */       anchorX = anchorY;
/* 337 */       anchorY = tempAnchor;
/*     */     } 
/* 340 */     g2.setFont(getFont());
/* 341 */     g2.setPaint(getPaint());
/* 342 */     TextUtilities.drawRotatedString(getText(), g2, anchorX, anchorY, getTextAnchor(), getRotationAngle(), getRotationAnchor());
/* 351 */     Shape hotspot = TextUtilities.calculateRotatedStringBounds(getText(), g2, anchorX, anchorY, getTextAnchor(), getRotationAngle(), getRotationAnchor());
/* 361 */     String toolTip = getToolTipText();
/* 362 */     String url = getURL();
/* 363 */     if (toolTip != null || url != null)
/* 364 */       addEntity(info, hotspot, rendererIndex, toolTip, url); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 377 */     if (obj == this)
/* 378 */       return true; 
/* 380 */     if (!(obj instanceof XYTextAnnotation))
/* 381 */       return false; 
/* 383 */     if (!super.equals(obj))
/* 384 */       return false; 
/* 386 */     XYTextAnnotation that = (XYTextAnnotation)obj;
/* 387 */     if (!this.text.equals(that.text))
/* 388 */       return false; 
/* 390 */     if (!this.font.equals(that.font))
/* 391 */       return false; 
/* 393 */     if (!PaintUtilities.equal(this.paint, that.paint))
/* 394 */       return false; 
/* 396 */     if (!this.rotationAnchor.equals(that.rotationAnchor))
/* 397 */       return false; 
/* 399 */     if (this.rotationAngle != that.rotationAngle)
/* 400 */       return false; 
/* 402 */     if (!this.textAnchor.equals(that.textAnchor))
/* 403 */       return false; 
/* 405 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 415 */     return this.text.hashCode();
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 426 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 437 */     stream.defaultWriteObject();
/* 438 */     SerialUtilities.writePaint(this.paint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 451 */     stream.defaultReadObject();
/* 452 */     this.paint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\XYTextAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */