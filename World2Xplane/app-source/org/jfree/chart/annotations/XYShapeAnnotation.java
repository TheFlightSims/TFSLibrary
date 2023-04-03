/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
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
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYShapeAnnotation extends AbstractXYAnnotation implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -8553218317600684041L;
/*     */   
/*     */   private transient Shape shape;
/*     */   
/*     */   private transient Stroke stroke;
/*     */   
/*     */   private transient Paint outlinePaint;
/*     */   
/*     */   private transient Paint fillPaint;
/*     */   
/*     */   public XYShapeAnnotation(Shape shape) {
/* 110 */     this(shape, new BasicStroke(1.0F), Color.black);
/*     */   }
/*     */   
/*     */   public XYShapeAnnotation(Shape shape, Stroke stroke, Paint outlinePaint) {
/* 122 */     this(shape, stroke, outlinePaint, (Paint)null);
/*     */   }
/*     */   
/*     */   public XYShapeAnnotation(Shape shape, Stroke stroke, Paint outlinePaint, Paint fillPaint) {
/* 136 */     if (shape == null)
/* 137 */       throw new IllegalArgumentException("Null 'shape' argument."); 
/* 139 */     this.shape = shape;
/* 140 */     this.stroke = stroke;
/* 141 */     this.outlinePaint = outlinePaint;
/* 142 */     this.fillPaint = fillPaint;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info) {
/* 162 */     PlotOrientation orientation = plot.getOrientation();
/* 163 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/* 166 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/* 173 */     double m02 = domainAxis.valueToJava2D(0.0D, dataArea, domainEdge);
/* 175 */     double m12 = rangeAxis.valueToJava2D(0.0D, dataArea, rangeEdge);
/* 177 */     double m00 = domainAxis.valueToJava2D(1.0D, dataArea, domainEdge) - m02;
/* 179 */     double m11 = rangeAxis.valueToJava2D(1.0D, dataArea, rangeEdge) - m12;
/* 182 */     Shape s = null;
/* 183 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 184 */       AffineTransform t1 = new AffineTransform(0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
/* 187 */       AffineTransform t2 = new AffineTransform(m11, 0.0D, 0.0D, m00, m12, m02);
/* 190 */       s = t1.createTransformedShape(this.shape);
/* 191 */       s = t2.createTransformedShape(s);
/* 193 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 194 */       AffineTransform t = new AffineTransform(m00, 0.0D, 0.0D, m11, m02, m12);
/* 195 */       s = t.createTransformedShape(this.shape);
/*     */     } 
/* 198 */     if (this.fillPaint != null) {
/* 199 */       g2.setPaint(this.fillPaint);
/* 200 */       g2.fill(s);
/*     */     } 
/* 203 */     if (this.stroke != null && this.outlinePaint != null) {
/* 204 */       g2.setPaint(this.outlinePaint);
/* 205 */       g2.setStroke(this.stroke);
/* 206 */       g2.draw(s);
/*     */     } 
/* 208 */     addEntity(info, s, rendererIndex, getToolTipText(), getURL());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 220 */     if (obj == this)
/* 221 */       return true; 
/* 224 */     if (!super.equals(obj))
/* 225 */       return false; 
/* 227 */     if (!(obj instanceof XYShapeAnnotation))
/* 228 */       return false; 
/* 230 */     XYShapeAnnotation that = (XYShapeAnnotation)obj;
/* 231 */     if (!this.shape.equals(that.shape))
/* 232 */       return false; 
/* 234 */     if (!ObjectUtilities.equal(this.stroke, that.stroke))
/* 235 */       return false; 
/* 237 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint))
/* 238 */       return false; 
/* 240 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint))
/* 241 */       return false; 
/* 244 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 254 */     return this.shape.hashCode();
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 265 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 276 */     stream.defaultWriteObject();
/* 277 */     SerialUtilities.writeShape(this.shape, stream);
/* 278 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 279 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 280 */     SerialUtilities.writePaint(this.fillPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 293 */     stream.defaultReadObject();
/* 294 */     this.shape = SerialUtilities.readShape(stream);
/* 295 */     this.stroke = SerialUtilities.readStroke(stream);
/* 296 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 297 */     this.fillPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\XYShapeAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */