/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.AxisLocation;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYImageAnnotation extends AbstractXYAnnotation implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -4364694501921559958L;
/*     */   
/*     */   private double x;
/*     */   
/*     */   private double y;
/*     */   
/*     */   private transient Image image;
/*     */   
/*     */   public XYImageAnnotation(double x, double y, Image image) {
/*  98 */     if (image == null)
/*  99 */       throw new IllegalArgumentException("Null 'image' argument."); 
/* 101 */     this.x = x;
/* 102 */     this.y = y;
/* 103 */     this.image = image;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info) {
/* 125 */     PlotOrientation orientation = plot.getOrientation();
/* 126 */     AxisLocation domainAxisLocation = plot.getDomainAxisLocation();
/* 127 */     AxisLocation rangeAxisLocation = plot.getRangeAxisLocation();
/* 128 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(domainAxisLocation, orientation);
/* 130 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(rangeAxisLocation, orientation);
/* 132 */     float j2DX = (float)domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
/* 134 */     float j2DY = (float)rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
/* 136 */     float xx = 0.0F;
/* 137 */     float yy = 0.0F;
/* 138 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 139 */       xx = j2DY;
/* 140 */       yy = j2DX;
/* 142 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 143 */       xx = j2DX;
/* 144 */       yy = j2DY;
/*     */     } 
/* 146 */     int w = this.image.getWidth(null);
/* 147 */     int h = this.image.getHeight(null);
/* 148 */     xx -= w / 2.0F;
/* 149 */     yy -= h / 2.0F;
/* 150 */     g2.drawImage(this.image, (int)xx, (int)yy, null);
/* 152 */     String toolTip = getToolTipText();
/* 153 */     String url = getURL();
/* 154 */     if (toolTip != null || url != null)
/* 155 */       addEntity(info, new Rectangle2D.Float(xx, yy, w, h), rendererIndex, toolTip, url); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 170 */     if (obj == this)
/* 171 */       return true; 
/* 174 */     if (!super.equals(obj))
/* 175 */       return false; 
/* 177 */     if (!(obj instanceof XYImageAnnotation))
/* 178 */       return false; 
/* 180 */     XYImageAnnotation that = (XYImageAnnotation)obj;
/* 181 */     if (this.x != that.x)
/* 182 */       return false; 
/* 184 */     if (this.y != that.y)
/* 185 */       return false; 
/* 187 */     if (!ObjectUtilities.equal(this.image, that.image))
/* 188 */       return false; 
/* 191 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 200 */     return this.image.hashCode();
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 211 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 222 */     stream.defaultWriteObject();
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 236 */     stream.defaultReadObject();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\XYImageAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */