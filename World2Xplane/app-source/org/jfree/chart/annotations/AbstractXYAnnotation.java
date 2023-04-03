/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYAnnotationEntity;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public abstract class AbstractXYAnnotation implements XYAnnotation {
/*  72 */   private String toolTipText = null;
/*     */   
/*  73 */   private String url = null;
/*     */   
/*     */   public String getToolTipText() {
/*  84 */     return this.toolTipText;
/*     */   }
/*     */   
/*     */   public void setToolTipText(String text) {
/*  93 */     this.toolTipText = text;
/*     */   }
/*     */   
/*     */   public String getURL() {
/* 103 */     return this.url;
/*     */   }
/*     */   
/*     */   public void setURL(String url) {
/* 112 */     this.url = url;
/*     */   }
/*     */   
/*     */   public abstract void draw(Graphics2D paramGraphics2D, XYPlot paramXYPlot, Rectangle2D paramRectangle2D, ValueAxis paramValueAxis1, ValueAxis paramValueAxis2, int paramInt, PlotRenderingInfo paramPlotRenderingInfo);
/*     */   
/*     */   protected void addEntity(PlotRenderingInfo info, Shape hotspot, int rendererIndex, String toolTipText, String urlText) {
/* 145 */     if (info == null)
/*     */       return; 
/* 148 */     EntityCollection entities = info.getOwner().getEntityCollection();
/* 149 */     if (entities == null)
/*     */       return; 
/* 152 */     XYAnnotationEntity entity = new XYAnnotationEntity(hotspot, rendererIndex, toolTipText, urlText);
/* 155 */     entities.add((ChartEntity)entity);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 166 */     if (obj == this)
/* 167 */       return true; 
/* 169 */     if (!(obj instanceof AbstractXYAnnotation))
/* 170 */       return false; 
/* 172 */     AbstractXYAnnotation that = (AbstractXYAnnotation)obj;
/* 173 */     if (!ObjectUtilities.equal(this.toolTipText, that.toolTipText))
/* 174 */       return false; 
/* 176 */     if (!ObjectUtilities.equal(this.url, that.url))
/* 177 */       return false; 
/* 179 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\AbstractXYAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */