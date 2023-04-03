/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
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
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYPointerAnnotation extends XYTextAnnotation implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -4031161445009858551L;
/*     */   
/*     */   public static final double DEFAULT_TIP_RADIUS = 10.0D;
/*     */   
/*     */   public static final double DEFAULT_BASE_RADIUS = 30.0D;
/*     */   
/*     */   public static final double DEFAULT_LABEL_OFFSET = 3.0D;
/*     */   
/*     */   public static final double DEFAULT_ARROW_LENGTH = 5.0D;
/*     */   
/*     */   public static final double DEFAULT_ARROW_WIDTH = 3.0D;
/*     */   
/*     */   private double angle;
/*     */   
/*     */   private double tipRadius;
/*     */   
/*     */   private double baseRadius;
/*     */   
/*     */   private double arrowLength;
/*     */   
/*     */   private double arrowWidth;
/*     */   
/*     */   private transient Stroke arrowStroke;
/*     */   
/*     */   private transient Paint arrowPaint;
/*     */   
/*     */   private double labelOffset;
/*     */   
/*     */   public XYPointerAnnotation(String label, double x, double y, double angle) {
/* 153 */     super(label, x, y);
/* 154 */     this.angle = angle;
/* 155 */     this.tipRadius = 10.0D;
/* 156 */     this.baseRadius = 30.0D;
/* 157 */     this.arrowLength = 5.0D;
/* 158 */     this.arrowWidth = 3.0D;
/* 159 */     this.labelOffset = 3.0D;
/* 160 */     this.arrowStroke = new BasicStroke(1.0F);
/* 161 */     this.arrowPaint = Color.black;
/*     */   }
/*     */   
/*     */   public double getAngle() {
/* 171 */     return this.angle;
/*     */   }
/*     */   
/*     */   public void setAngle(double angle) {
/* 180 */     this.angle = angle;
/*     */   }
/*     */   
/*     */   public double getTipRadius() {
/* 189 */     return this.tipRadius;
/*     */   }
/*     */   
/*     */   public void setTipRadius(double radius) {
/* 198 */     this.tipRadius = radius;
/*     */   }
/*     */   
/*     */   public double getBaseRadius() {
/* 207 */     return this.baseRadius;
/*     */   }
/*     */   
/*     */   public void setBaseRadius(double radius) {
/* 216 */     this.baseRadius = radius;
/*     */   }
/*     */   
/*     */   public double getLabelOffset() {
/* 225 */     return this.labelOffset;
/*     */   }
/*     */   
/*     */   public void setLabelOffset(double offset) {
/* 235 */     this.labelOffset = offset;
/*     */   }
/*     */   
/*     */   public double getArrowLength() {
/* 244 */     return this.arrowLength;
/*     */   }
/*     */   
/*     */   public void setArrowLength(double length) {
/* 253 */     this.arrowLength = length;
/*     */   }
/*     */   
/*     */   public double getArrowWidth() {
/* 262 */     return this.arrowWidth;
/*     */   }
/*     */   
/*     */   public void setArrowWidth(double width) {
/* 271 */     this.arrowWidth = width;
/*     */   }
/*     */   
/*     */   public Stroke getArrowStroke() {
/* 280 */     return this.arrowStroke;
/*     */   }
/*     */   
/*     */   public void setArrowStroke(Stroke stroke) {
/* 289 */     if (stroke == null)
/* 290 */       throw new IllegalArgumentException("Null 'stroke' not permitted."); 
/* 292 */     this.arrowStroke = stroke;
/*     */   }
/*     */   
/*     */   public Paint getArrowPaint() {
/* 301 */     return this.arrowPaint;
/*     */   }
/*     */   
/*     */   public void setArrowPaint(Paint paint) {
/* 310 */     this.arrowPaint = paint;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info) {
/* 329 */     PlotOrientation orientation = plot.getOrientation();
/* 330 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/* 333 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/* 336 */     double j2DX = domainAxis.valueToJava2D(getX(), dataArea, domainEdge);
/* 337 */     double j2DY = rangeAxis.valueToJava2D(getY(), dataArea, rangeEdge);
/* 339 */     double startX = j2DX + Math.cos(this.angle) * this.baseRadius;
/* 340 */     double startY = j2DY + Math.sin(this.angle) * this.baseRadius;
/* 342 */     double endX = j2DX + Math.cos(this.angle) * this.tipRadius;
/* 343 */     double endY = j2DY + Math.sin(this.angle) * this.tipRadius;
/* 345 */     double arrowBaseX = endX + Math.cos(this.angle) * this.arrowLength;
/* 346 */     double arrowBaseY = endY + Math.sin(this.angle) * this.arrowLength;
/* 348 */     double arrowLeftX = arrowBaseX + Math.cos(this.angle + 1.5707963267948966D) * this.arrowWidth;
/* 350 */     double arrowLeftY = arrowBaseY + Math.sin(this.angle + 1.5707963267948966D) * this.arrowWidth;
/* 353 */     double arrowRightX = arrowBaseX - Math.cos(this.angle + 1.5707963267948966D) * this.arrowWidth;
/* 355 */     double arrowRightY = arrowBaseY - Math.sin(this.angle + 1.5707963267948966D) * this.arrowWidth;
/* 358 */     GeneralPath arrow = new GeneralPath();
/* 359 */     arrow.moveTo((float)endX, (float)endY);
/* 360 */     arrow.lineTo((float)arrowLeftX, (float)arrowLeftY);
/* 361 */     arrow.lineTo((float)arrowRightX, (float)arrowRightY);
/* 362 */     arrow.closePath();
/* 364 */     g2.setStroke(this.arrowStroke);
/* 365 */     g2.setPaint(this.arrowPaint);
/* 366 */     Line2D line = new Line2D.Double(startX, startY, endX, endY);
/* 367 */     g2.draw(line);
/* 368 */     g2.fill(arrow);
/* 371 */     g2.setFont(getFont());
/* 372 */     g2.setPaint(getPaint());
/* 373 */     double labelX = j2DX + Math.cos(this.angle) * (this.baseRadius + this.labelOffset);
/* 375 */     double labelY = j2DY + Math.sin(this.angle) * (this.baseRadius + this.labelOffset);
/* 377 */     Rectangle2D hotspot = TextUtilities.drawAlignedString(getText(), g2, (float)labelX, (float)labelY, getTextAnchor());
/* 385 */     String toolTip = getToolTipText();
/* 386 */     String url = getURL();
/* 387 */     if (toolTip != null || url != null)
/* 388 */       addEntity(info, hotspot, rendererIndex, toolTip, url); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 402 */     if (object == null)
/* 403 */       return false; 
/* 406 */     if (object == this)
/* 407 */       return true; 
/* 410 */     if (object instanceof XYPointerAnnotation) {
/* 412 */       XYPointerAnnotation a = (XYPointerAnnotation)object;
/* 413 */       boolean b0 = (this.angle == a.angle);
/* 414 */       boolean b1 = (this.tipRadius == a.tipRadius);
/* 415 */       boolean b2 = (this.baseRadius == a.baseRadius);
/* 416 */       boolean b3 = (this.arrowLength == a.arrowLength);
/* 417 */       boolean b4 = (this.arrowWidth == a.arrowWidth);
/* 418 */       boolean b5 = this.arrowPaint.equals(a.arrowPaint);
/* 419 */       boolean b6 = this.arrowStroke.equals(a.arrowStroke);
/* 420 */       boolean b7 = (this.labelOffset == a.labelOffset);
/* 421 */       return (b0 && b1 && b2 && b3 && b4 && b5 && b6 && b7);
/*     */     } 
/* 424 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 436 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 447 */     stream.defaultWriteObject();
/* 448 */     SerialUtilities.writePaint(this.arrowPaint, stream);
/* 449 */     SerialUtilities.writeStroke(this.arrowStroke, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 462 */     stream.defaultReadObject();
/* 463 */     this.arrowPaint = SerialUtilities.readPaint(stream);
/* 464 */     this.arrowStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\XYPointerAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */