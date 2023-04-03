/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.ui.Drawable;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYDrawableAnnotation extends AbstractXYAnnotation implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -6540812859722691020L;
/*     */   
/*     */   private double x;
/*     */   
/*     */   private double y;
/*     */   
/*     */   private double width;
/*     */   
/*     */   private double height;
/*     */   
/*     */   private Drawable drawable;
/*     */   
/*     */   public XYDrawableAnnotation(double x, double y, double width, double height, Drawable drawable) {
/*  99 */     if (drawable == null)
/* 100 */       throw new IllegalArgumentException("Null 'drawable' argument."); 
/* 102 */     this.x = x;
/* 103 */     this.y = y;
/* 104 */     this.width = width;
/* 105 */     this.height = height;
/* 106 */     this.drawable = drawable;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info) {
/* 127 */     PlotOrientation orientation = plot.getOrientation();
/* 128 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/* 131 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/* 134 */     float j2DX = (float)domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
/* 137 */     float j2DY = (float)rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
/* 140 */     Rectangle2D area = new Rectangle2D.Double(j2DX - this.width / 2.0D, j2DY - this.height / 2.0D, this.width, this.height);
/* 144 */     this.drawable.draw(g2, area);
/* 145 */     String toolTip = getToolTipText();
/* 146 */     String url = getURL();
/* 147 */     if (toolTip != null || url != null)
/* 148 */       addEntity(info, area, rendererIndex, toolTip, url); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 162 */     if (obj == this)
/* 163 */       return true; 
/* 166 */     if (!super.equals(obj))
/* 167 */       return false; 
/* 169 */     if (!(obj instanceof XYDrawableAnnotation))
/* 170 */       return false; 
/* 172 */     XYDrawableAnnotation that = (XYDrawableAnnotation)obj;
/* 173 */     if (this.x != that.x)
/* 174 */       return false; 
/* 176 */     if (this.y != that.y)
/* 177 */       return false; 
/* 179 */     if (this.width != that.width)
/* 180 */       return false; 
/* 182 */     if (this.height != that.height)
/* 183 */       return false; 
/* 185 */     if (!ObjectUtilities.equal(this.drawable, that.drawable))
/* 186 */       return false; 
/* 189 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 201 */     long temp = Double.doubleToLongBits(this.x);
/* 202 */     int result = (int)(temp ^ temp >>> 32L);
/* 203 */     temp = Double.doubleToLongBits(this.y);
/* 204 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 205 */     temp = Double.doubleToLongBits(this.width);
/* 206 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 207 */     temp = Double.doubleToLongBits(this.height);
/* 208 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 209 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 220 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\XYDrawableAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */