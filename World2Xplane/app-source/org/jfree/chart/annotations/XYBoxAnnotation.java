/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
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
/*     */ public class XYBoxAnnotation extends AbstractXYAnnotation implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 6764703772526757457L;
/*     */   
/*     */   private double x0;
/*     */   
/*     */   private double y0;
/*     */   
/*     */   private double x1;
/*     */   
/*     */   private double y1;
/*     */   
/*     */   private transient Stroke stroke;
/*     */   
/*     */   private transient Paint outlinePaint;
/*     */   
/*     */   private transient Paint fillPaint;
/*     */   
/*     */   public XYBoxAnnotation(double x0, double y0, double x1, double y1) {
/* 111 */     this(x0, y0, x1, y1, new BasicStroke(1.0F), Color.black);
/*     */   }
/*     */   
/*     */   public XYBoxAnnotation(double x0, double y0, double x1, double y1, Stroke stroke, Paint outlinePaint) {
/* 127 */     this(x0, y0, x1, y1, stroke, outlinePaint, (Paint)null);
/*     */   }
/*     */   
/*     */   public XYBoxAnnotation(double x0, double y0, double x1, double y1, Stroke stroke, Paint outlinePaint, Paint fillPaint) {
/* 144 */     this.x0 = x0;
/* 145 */     this.y0 = y0;
/* 146 */     this.x1 = x1;
/* 147 */     this.y1 = y1;
/* 148 */     this.stroke = stroke;
/* 149 */     this.outlinePaint = outlinePaint;
/* 150 */     this.fillPaint = fillPaint;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info) {
/* 169 */     PlotOrientation orientation = plot.getOrientation();
/* 170 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/* 173 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/* 177 */     double transX0 = domainAxis.valueToJava2D(this.x0, dataArea, domainEdge);
/* 180 */     double transY0 = rangeAxis.valueToJava2D(this.y0, dataArea, rangeEdge);
/* 181 */     double transX1 = domainAxis.valueToJava2D(this.x1, dataArea, domainEdge);
/* 184 */     double transY1 = rangeAxis.valueToJava2D(this.y1, dataArea, rangeEdge);
/* 186 */     Rectangle2D box = null;
/* 187 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 188 */       box = new Rectangle2D.Double(transY0, transX1, transY1 - transY0, transX0 - transX1);
/* 192 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 193 */       box = new Rectangle2D.Double(transX0, transY1, transX1 - transX0, transY0 - transY1);
/*     */     } 
/* 198 */     if (this.fillPaint != null) {
/* 199 */       g2.setPaint(this.fillPaint);
/* 200 */       g2.fill(box);
/*     */     } 
/* 203 */     if (this.stroke != null && this.outlinePaint != null) {
/* 204 */       g2.setPaint(this.outlinePaint);
/* 205 */       g2.setStroke(this.stroke);
/* 206 */       g2.draw(box);
/*     */     } 
/* 208 */     addEntity(info, box, rendererIndex, getToolTipText(), getURL());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 220 */     if (obj == this)
/* 221 */       return true; 
/* 224 */     if (!super.equals(obj))
/* 225 */       return false; 
/* 227 */     if (!(obj instanceof XYBoxAnnotation))
/* 228 */       return false; 
/* 230 */     XYBoxAnnotation that = (XYBoxAnnotation)obj;
/* 231 */     if (this.x0 != that.x0)
/* 232 */       return false; 
/* 234 */     if (this.y0 != that.y0)
/* 235 */       return false; 
/* 237 */     if (this.x1 != that.x1)
/* 238 */       return false; 
/* 240 */     if (this.y1 != that.y1)
/* 241 */       return false; 
/* 243 */     if (!ObjectUtilities.equal(this.stroke, that.stroke))
/* 244 */       return false; 
/* 246 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint))
/* 247 */       return false; 
/* 249 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint))
/* 250 */       return false; 
/* 253 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 264 */     long temp = Double.doubleToLongBits(this.x0);
/* 265 */     int result = (int)(temp ^ temp >>> 32L);
/* 266 */     temp = Double.doubleToLongBits(this.x1);
/* 267 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 268 */     temp = Double.doubleToLongBits(this.y0);
/* 269 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 270 */     temp = Double.doubleToLongBits(this.y1);
/* 271 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 272 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 284 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 295 */     stream.defaultWriteObject();
/* 296 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 297 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 298 */     SerialUtilities.writePaint(this.fillPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 312 */     stream.defaultReadObject();
/* 313 */     this.stroke = SerialUtilities.readStroke(stream);
/* 314 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 315 */     this.fillPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\XYBoxAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */