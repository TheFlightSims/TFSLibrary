/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class LegendItemEntity extends ChartEntity implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -7435683933545666702L;
/*     */   
/*     */   private int seriesIndex;
/*     */   
/*     */   public LegendItemEntity(Shape area) {
/*  68 */     super(area);
/*     */   }
/*     */   
/*     */   public int getSeriesIndex() {
/*  77 */     return this.seriesIndex;
/*     */   }
/*     */   
/*     */   public void setSeriesIndex(int index) {
/*  86 */     this.seriesIndex = index;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  97 */     if (obj == this)
/*  98 */       return true; 
/* 100 */     if (obj instanceof LegendItemEntity && super.equals(obj)) {
/* 101 */       LegendItemEntity e = (LegendItemEntity)obj;
/* 102 */       if (this.seriesIndex != e.seriesIndex)
/* 103 */         return false; 
/* 105 */       return true;
/*     */     } 
/* 107 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 119 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\entity\LegendItemEntity.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */