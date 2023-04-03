/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class OutlierListCollection {
/*     */   private List outlierLists;
/*     */   
/*     */   private boolean highFarOut = false;
/*     */   
/*     */   private boolean lowFarOut = false;
/*     */   
/*     */   public OutlierListCollection() {
/*  84 */     this.outlierLists = new ArrayList();
/*     */   }
/*     */   
/*     */   public boolean isHighFarOut() {
/*  94 */     return this.highFarOut;
/*     */   }
/*     */   
/*     */   public void setHighFarOut(boolean farOut) {
/* 104 */     this.highFarOut = farOut;
/*     */   }
/*     */   
/*     */   public boolean isLowFarOut() {
/* 114 */     return this.lowFarOut;
/*     */   }
/*     */   
/*     */   public void setLowFarOut(boolean farOut) {
/* 124 */     this.lowFarOut = farOut;
/*     */   }
/*     */   
/*     */   public boolean add(Outlier outlier) {
/* 139 */     if (this.outlierLists.isEmpty())
/* 140 */       return this.outlierLists.add(new OutlierList(outlier)); 
/* 143 */     boolean updated = false;
/* 144 */     Iterator iterator = this.outlierLists.iterator();
/* 145 */     while (iterator.hasNext()) {
/* 146 */       OutlierList list = iterator.next();
/* 147 */       if (list.isOverlapped(outlier))
/* 148 */         updated = updateOutlierList(list, outlier); 
/*     */     } 
/* 151 */     if (!updated)
/* 153 */       updated = this.outlierLists.add(new OutlierList(outlier)); 
/* 155 */     return updated;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 166 */     return this.outlierLists.iterator();
/*     */   }
/*     */   
/*     */   private boolean updateOutlierList(OutlierList list, Outlier outlier) {
/* 181 */     boolean result = false;
/* 182 */     result = list.add(outlier);
/* 183 */     list.updateAveragedOutlier();
/* 184 */     list.setMultiple(true);
/* 185 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\OutlierListCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */