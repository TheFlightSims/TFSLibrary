/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class CategoryItemEntity extends ChartEntity implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -8657249457902337349L;
/*     */   
/*     */   private transient CategoryDataset dataset;
/*     */   
/*     */   private int series;
/*     */   
/*     */   private Object category;
/*     */   
/*     */   private int categoryIndex;
/*     */   
/*     */   public CategoryItemEntity(Shape area, String toolTipText, String urlText, CategoryDataset dataset, int series, Object category, int categoryIndex) {
/*  97 */     super(area, toolTipText, urlText);
/*  98 */     this.dataset = dataset;
/*  99 */     this.series = series;
/* 100 */     this.category = category;
/* 101 */     this.categoryIndex = categoryIndex;
/*     */   }
/*     */   
/*     */   public CategoryDataset getDataset() {
/* 111 */     return this.dataset;
/*     */   }
/*     */   
/*     */   public void setDataset(CategoryDataset dataset) {
/* 120 */     this.dataset = dataset;
/*     */   }
/*     */   
/*     */   public int getSeries() {
/* 129 */     return this.series;
/*     */   }
/*     */   
/*     */   public void setSeries(int series) {
/* 138 */     this.series = series;
/*     */   }
/*     */   
/*     */   public Object getCategory() {
/* 147 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(Object category) {
/* 156 */     this.category = category;
/*     */   }
/*     */   
/*     */   public int getCategoryIndex() {
/* 165 */     return this.categoryIndex;
/*     */   }
/*     */   
/*     */   public void setCategoryIndex(int index) {
/* 174 */     this.categoryIndex = index;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 184 */     return "Category Item: series=" + this.series + ", category=" + this.category.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 196 */     if (obj == this)
/* 197 */       return true; 
/* 199 */     if (obj instanceof CategoryItemEntity && super.equals(obj)) {
/* 200 */       CategoryItemEntity cie = (CategoryItemEntity)obj;
/* 201 */       if (this.categoryIndex != cie.categoryIndex)
/* 202 */         return false; 
/* 204 */       if (this.series != cie.series)
/* 205 */         return false; 
/* 207 */       if (!ObjectUtilities.equal(this.category, cie.category))
/* 208 */         return false; 
/* 210 */       return true;
/*     */     } 
/* 212 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\entity\CategoryItemEntity.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */