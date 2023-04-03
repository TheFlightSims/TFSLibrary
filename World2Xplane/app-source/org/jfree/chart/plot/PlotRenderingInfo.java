/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class PlotRenderingInfo implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 8446720134379617220L;
/*     */   
/*     */   private ChartRenderingInfo owner;
/*     */   
/*     */   private transient Rectangle2D plotArea;
/*     */   
/*     */   private transient Rectangle2D dataArea;
/*     */   
/*     */   private List subplotInfo;
/*     */   
/*     */   public PlotRenderingInfo(ChartRenderingInfo owner) {
/*  88 */     this.owner = owner;
/*  89 */     this.dataArea = new Rectangle2D.Double();
/*  90 */     this.subplotInfo = new ArrayList();
/*     */   }
/*     */   
/*     */   public ChartRenderingInfo getOwner() {
/*  99 */     return this.owner;
/*     */   }
/*     */   
/*     */   public Rectangle2D getPlotArea() {
/* 108 */     return this.plotArea;
/*     */   }
/*     */   
/*     */   public void setPlotArea(Rectangle2D area) {
/* 117 */     this.plotArea = area;
/*     */   }
/*     */   
/*     */   public Rectangle2D getDataArea() {
/* 126 */     return this.dataArea;
/*     */   }
/*     */   
/*     */   public void setDataArea(Rectangle2D area) {
/* 135 */     this.dataArea = area;
/*     */   }
/*     */   
/*     */   public int getSubplotCount() {
/* 144 */     return this.subplotInfo.size();
/*     */   }
/*     */   
/*     */   public void addSubplotInfo(PlotRenderingInfo info) {
/* 153 */     this.subplotInfo.add(info);
/*     */   }
/*     */   
/*     */   public PlotRenderingInfo getSubplotInfo(int index) {
/* 164 */     return this.subplotInfo.get(index);
/*     */   }
/*     */   
/*     */   public int getSubplotIndex(Point2D source) {
/* 179 */     int subplotCount = getSubplotCount();
/* 180 */     for (int i = 0; i < subplotCount; i++) {
/* 181 */       PlotRenderingInfo info = getSubplotInfo(i);
/* 182 */       Rectangle2D area = info.getDataArea();
/* 183 */       if (area.contains(source))
/* 184 */         return i; 
/*     */     } 
/* 187 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 198 */     if (this == obj)
/* 199 */       return true; 
/* 201 */     if (!(obj instanceof PlotRenderingInfo))
/* 202 */       return false; 
/* 204 */     PlotRenderingInfo that = (PlotRenderingInfo)obj;
/* 205 */     if (!ObjectUtilities.equal(this.dataArea, that.dataArea))
/* 206 */       return false; 
/* 208 */     if (!ObjectUtilities.equal(this.plotArea, that.plotArea))
/* 209 */       return false; 
/* 211 */     if (!ObjectUtilities.equal(this.subplotInfo, that.subplotInfo))
/* 212 */       return false; 
/* 214 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 225 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 236 */     stream.defaultWriteObject();
/* 237 */     SerialUtilities.writeShape(this.dataArea, stream);
/* 238 */     SerialUtilities.writeShape(this.plotArea, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 251 */     stream.defaultReadObject();
/* 252 */     this.dataArea = (Rectangle2D)SerialUtilities.readShape(stream);
/* 253 */     this.plotArea = (Rectangle2D)SerialUtilities.readShape(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\PlotRenderingInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */