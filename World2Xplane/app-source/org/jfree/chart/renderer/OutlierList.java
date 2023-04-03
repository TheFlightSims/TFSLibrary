/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class OutlierList {
/*     */   private List outliers;
/*     */   
/*     */   private Outlier averagedOutlier;
/*     */   
/*     */   private boolean multiple = false;
/*     */   
/*     */   public OutlierList(Outlier outlier) {
/*  87 */     this.outliers = new ArrayList();
/*  88 */     setAveragedOutlier(outlier);
/*     */   }
/*     */   
/*     */   public boolean add(Outlier outlier) {
/*  99 */     return this.outliers.add(outlier);
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 108 */     return this.outliers.size();
/*     */   }
/*     */   
/*     */   public Outlier getAveragedOutlier() {
/* 117 */     return this.averagedOutlier;
/*     */   }
/*     */   
/*     */   public void setAveragedOutlier(Outlier averagedOutlier) {
/* 126 */     this.averagedOutlier = averagedOutlier;
/*     */   }
/*     */   
/*     */   public boolean isMultiple() {
/* 136 */     return this.multiple;
/*     */   }
/*     */   
/*     */   public void setMultiple(boolean multiple) {
/* 146 */     this.multiple = multiple;
/*     */   }
/*     */   
/*     */   public boolean isOverlapped(Outlier other) {
/* 159 */     if (other == null)
/* 160 */       return false; 
/* 163 */     boolean result = other.overlaps(getAveragedOutlier());
/* 164 */     return result;
/*     */   }
/*     */   
/*     */   public void updateAveragedOutlier() {
/* 173 */     double totalXCoords = 0.0D;
/* 174 */     double totalYCoords = 0.0D;
/* 175 */     int size = getItemCount();
/* 176 */     Iterator iterator = this.outliers.iterator();
/* 177 */     while (iterator.hasNext()) {
/* 178 */       Outlier o = iterator.next();
/* 179 */       totalXCoords += o.getX();
/* 180 */       totalYCoords += o.getY();
/*     */     } 
/* 182 */     getAveragedOutlier().getPoint().setLocation(new Point2D.Double(totalXCoords / size, totalYCoords / size));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\OutlierList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */