/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYPolygonAnnotation extends AbstractXYAnnotation implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -6984203651995900036L;
/*     */   
/*     */   private double[] polygon;
/*     */   
/*     */   private transient Stroke stroke;
/*     */   
/*     */   private transient Paint outlinePaint;
/*     */   
/*     */   private transient Paint fillPaint;
/*     */   
/*     */   public XYPolygonAnnotation(double[] polygon) {
/*  99 */     this(polygon, new BasicStroke(1.0F), Color.black);
/*     */   }
/*     */   
/*     */   public XYPolygonAnnotation(double[] polygon, Stroke stroke, Paint outlinePaint) {
/* 112 */     this(polygon, stroke, outlinePaint, (Paint)null);
/*     */   }
/*     */   
/*     */   public XYPolygonAnnotation(double[] polygon, Stroke stroke, Paint outlinePaint, Paint fillPaint) {
/* 127 */     this.polygon = polygon;
/* 128 */     this.stroke = stroke;
/* 129 */     this.outlinePaint = outlinePaint;
/* 130 */     this.fillPaint = fillPaint;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info) {
/* 149 */     PlotOrientation orientation = plot.getOrientation();
/* 150 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/* 153 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/* 157 */     GeneralPath area = new GeneralPath();
/* 158 */     double x = domainAxis.valueToJava2D(this.polygon[0], dataArea, domainEdge);
/* 161 */     double y = rangeAxis.valueToJava2D(this.polygon[1], dataArea, rangeEdge);
/* 164 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 165 */       area.moveTo((float)y, (float)x);
/* 166 */       for (int i = 2; i < this.polygon.length; i += 2) {
/* 167 */         x = domainAxis.valueToJava2D(this.polygon[i], dataArea, domainEdge);
/* 170 */         y = rangeAxis.valueToJava2D(this.polygon[i + 1], dataArea, rangeEdge);
/* 173 */         area.lineTo((float)y, (float)x);
/*     */       } 
/* 175 */       area.closePath();
/* 177 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 178 */       area.moveTo((float)x, (float)y);
/* 179 */       for (int i = 2; i < this.polygon.length; i += 2) {
/* 180 */         x = domainAxis.valueToJava2D(this.polygon[i], dataArea, domainEdge);
/* 183 */         y = rangeAxis.valueToJava2D(this.polygon[i + 1], dataArea, rangeEdge);
/* 186 */         area.lineTo((float)x, (float)y);
/*     */       } 
/* 188 */       area.closePath();
/*     */     } 
/* 192 */     if (this.fillPaint != null) {
/* 193 */       g2.setPaint(this.fillPaint);
/* 194 */       g2.fill(area);
/*     */     } 
/* 197 */     if (this.stroke != null && this.outlinePaint != null) {
/* 198 */       g2.setPaint(this.outlinePaint);
/* 199 */       g2.setStroke(this.stroke);
/* 200 */       g2.draw(area);
/*     */     } 
/* 202 */     addEntity(info, area, rendererIndex, getToolTipText(), getURL());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 214 */     if (obj == this)
/* 215 */       return true; 
/* 218 */     if (!super.equals(obj))
/* 219 */       return false; 
/* 221 */     if (!(obj instanceof XYPolygonAnnotation))
/* 222 */       return false; 
/* 224 */     XYPolygonAnnotation that = (XYPolygonAnnotation)obj;
/* 225 */     if (!Arrays.equals(this.polygon, that.polygon))
/* 226 */       return false; 
/* 228 */     if (!ObjectUtilities.equal(this.stroke, that.stroke))
/* 229 */       return false; 
/* 231 */     if (!ObjectUtilities.equal(this.outlinePaint, that.outlinePaint))
/* 232 */       return false; 
/* 234 */     if (!ObjectUtilities.equal(this.fillPaint, that.fillPaint))
/* 235 */       return false; 
/* 238 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 250 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 261 */     stream.defaultWriteObject();
/* 262 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 263 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 264 */     SerialUtilities.writePaint(this.fillPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 278 */     stream.defaultReadObject();
/* 279 */     this.stroke = SerialUtilities.readStroke(stream);
/* 280 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 281 */     this.fillPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\XYPolygonAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */