/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
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
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class XYLineAnnotation extends AbstractXYAnnotation implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -80535465244091334L;
/*     */   
/*     */   private double x1;
/*     */   
/*     */   private double y1;
/*     */   
/*     */   private double x2;
/*     */   
/*     */   private double y2;
/*     */   
/*     */   private transient Stroke stroke;
/*     */   
/*     */   private transient Paint paint;
/*     */   
/*     */   public XYLineAnnotation(double x1, double y1, double x2, double y2) {
/* 116 */     this(x1, y1, x2, y2, new BasicStroke(1.0F), Color.black);
/*     */   }
/*     */   
/*     */   public XYLineAnnotation(double x1, double y1, double x2, double y2, Stroke stroke, Paint paint) {
/* 134 */     if (stroke == null)
/* 135 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 137 */     if (paint == null)
/* 138 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 140 */     this.x1 = x1;
/* 141 */     this.y1 = y1;
/* 142 */     this.x2 = x2;
/* 143 */     this.y2 = y2;
/* 144 */     this.stroke = stroke;
/* 145 */     this.paint = paint;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info) {
/* 167 */     PlotOrientation orientation = plot.getOrientation();
/* 168 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/* 171 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/* 174 */     float j2DX1 = 0.0F;
/* 175 */     float j2DX2 = 0.0F;
/* 176 */     float j2DY1 = 0.0F;
/* 177 */     float j2DY2 = 0.0F;
/* 178 */     if (orientation == PlotOrientation.VERTICAL) {
/* 179 */       j2DX1 = (float)domainAxis.valueToJava2D(this.x1, dataArea, domainEdge);
/* 182 */       j2DY1 = (float)rangeAxis.valueToJava2D(this.y1, dataArea, rangeEdge);
/* 185 */       j2DX2 = (float)domainAxis.valueToJava2D(this.x2, dataArea, domainEdge);
/* 188 */       j2DY2 = (float)rangeAxis.valueToJava2D(this.y2, dataArea, rangeEdge);
/* 192 */     } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 193 */       j2DY1 = (float)domainAxis.valueToJava2D(this.x1, dataArea, domainEdge);
/* 196 */       j2DX1 = (float)rangeAxis.valueToJava2D(this.y1, dataArea, rangeEdge);
/* 199 */       j2DY2 = (float)domainAxis.valueToJava2D(this.x2, dataArea, domainEdge);
/* 202 */       j2DX2 = (float)rangeAxis.valueToJava2D(this.y2, dataArea, rangeEdge);
/*     */     } 
/* 206 */     g2.setPaint(this.paint);
/* 207 */     g2.setStroke(this.stroke);
/* 208 */     Line2D line = new Line2D.Float(j2DX1, j2DY1, j2DX2, j2DY2);
/* 209 */     g2.draw(line);
/* 211 */     String toolTip = getToolTipText();
/* 212 */     String url = getURL();
/* 213 */     if (toolTip != null || url != null)
/* 214 */       addEntity(info, ShapeUtilities.createLineRegion(line, 1.0F), rendererIndex, toolTip, url); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 229 */     if (obj == this)
/* 230 */       return true; 
/* 232 */     if (!super.equals(obj))
/* 233 */       return false; 
/* 235 */     if (!(obj instanceof XYLineAnnotation))
/* 236 */       return false; 
/* 238 */     XYLineAnnotation that = (XYLineAnnotation)obj;
/* 239 */     if (this.x1 != that.x1)
/* 240 */       return false; 
/* 242 */     if (this.y1 != that.y1)
/* 243 */       return false; 
/* 245 */     if (this.x2 != that.x2)
/* 246 */       return false; 
/* 248 */     if (this.y2 != that.y2)
/* 249 */       return false; 
/* 251 */     if (!PaintUtilities.equal(this.paint, that.paint))
/* 252 */       return false; 
/* 254 */     if (!ObjectUtilities.equal(this.stroke, that.stroke))
/* 255 */       return false; 
/* 258 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 269 */     long temp = Double.doubleToLongBits(this.x1);
/* 270 */     int result = (int)(temp ^ temp >>> 32L);
/* 271 */     temp = Double.doubleToLongBits(this.x2);
/* 272 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 273 */     temp = Double.doubleToLongBits(this.y1);
/* 274 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 275 */     temp = Double.doubleToLongBits(this.y2);
/* 276 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 277 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 288 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 300 */     stream.defaultWriteObject();
/* 301 */     SerialUtilities.writePaint(this.paint, stream);
/* 302 */     SerialUtilities.writeStroke(this.stroke, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 316 */     stream.defaultReadObject();
/* 317 */     this.paint = SerialUtilities.readPaint(stream);
/* 318 */     this.stroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\XYLineAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */