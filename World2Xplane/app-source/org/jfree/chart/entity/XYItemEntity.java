/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ public class XYItemEntity extends ChartEntity {
/*     */   private static final long serialVersionUID = -3870862224880283771L;
/*     */   
/*     */   private transient XYDataset dataset;
/*     */   
/*     */   private int series;
/*     */   
/*     */   private int item;
/*     */   
/*     */   public XYItemEntity(Shape area, XYDataset dataset, int series, int item, String toolTipText, String urlText) {
/*  90 */     super(area, toolTipText, urlText);
/*  91 */     this.dataset = dataset;
/*  92 */     this.series = series;
/*  93 */     this.item = item;
/*     */   }
/*     */   
/*     */   public XYDataset getDataset() {
/* 102 */     return this.dataset;
/*     */   }
/*     */   
/*     */   public void setDataset(XYDataset dataset) {
/* 111 */     this.dataset = dataset;
/*     */   }
/*     */   
/*     */   public int getSeriesIndex() {
/* 120 */     return this.series;
/*     */   }
/*     */   
/*     */   public void setSeriesIndex(int series) {
/* 129 */     this.series = series;
/*     */   }
/*     */   
/*     */   public int getItem() {
/* 138 */     return this.item;
/*     */   }
/*     */   
/*     */   public void setItem(int item) {
/* 147 */     this.item = item;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 158 */     if (obj == this)
/* 159 */       return true; 
/* 161 */     if (obj instanceof XYItemEntity && super.equals(obj)) {
/* 162 */       XYItemEntity ie = (XYItemEntity)obj;
/* 163 */       if (this.series != ie.series)
/* 164 */         return false; 
/* 166 */       if (this.item != ie.item)
/* 167 */         return false; 
/* 169 */       return true;
/*     */     } 
/* 171 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 181 */     return "XYItemEntity: series = " + getSeriesIndex() + ", item = " + getItem() + ", dataset = " + getDataset();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\entity\XYItemEntity.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */