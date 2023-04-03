/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.StandardEntityCollection;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class ChartRenderingInfo implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 2751952018173406822L;
/*     */   
/*     */   private transient Rectangle2D chartArea;
/*     */   
/*     */   private PlotRenderingInfo plotInfo;
/*     */   
/*     */   private EntityCollection entities;
/*     */   
/*     */   public ChartRenderingInfo() {
/* 101 */     this((EntityCollection)new StandardEntityCollection());
/*     */   }
/*     */   
/*     */   public ChartRenderingInfo(EntityCollection entities) {
/* 113 */     this.chartArea = new Rectangle2D.Double();
/* 114 */     this.plotInfo = new PlotRenderingInfo(this);
/* 115 */     this.entities = entities;
/*     */   }
/*     */   
/*     */   public Rectangle2D getChartArea() {
/* 124 */     return this.chartArea;
/*     */   }
/*     */   
/*     */   public void setChartArea(Rectangle2D area) {
/* 133 */     this.chartArea.setRect(area);
/*     */   }
/*     */   
/*     */   public EntityCollection getEntityCollection() {
/* 142 */     return this.entities;
/*     */   }
/*     */   
/*     */   public void setEntityCollection(EntityCollection entities) {
/* 151 */     this.entities = entities;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 158 */     this.chartArea.setRect(0.0D, 0.0D, 0.0D, 0.0D);
/* 159 */     this.plotInfo = new PlotRenderingInfo(this);
/* 160 */     if (this.entities != null)
/* 161 */       this.entities.clear(); 
/*     */   }
/*     */   
/*     */   public PlotRenderingInfo getPlotInfo() {
/* 171 */     return this.plotInfo;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 182 */     if (obj == this)
/* 183 */       return true; 
/* 185 */     if (!(obj instanceof ChartRenderingInfo))
/* 186 */       return false; 
/* 188 */     ChartRenderingInfo that = (ChartRenderingInfo)obj;
/* 189 */     if (!ObjectUtilities.equal(this.chartArea, that.chartArea))
/* 190 */       return false; 
/* 192 */     if (!ObjectUtilities.equal(this.plotInfo, that.plotInfo))
/* 193 */       return false; 
/* 195 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 206 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 217 */     stream.defaultWriteObject();
/* 218 */     SerialUtilities.writeShape(this.chartArea, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 231 */     stream.defaultReadObject();
/* 232 */     this.chartArea = (Rectangle2D)SerialUtilities.readShape(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\ChartRenderingInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */