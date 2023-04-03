/*     */ package org.jfree.data.category;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.AbstractDataset;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetChangeListener;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.util.TableOrder;
/*     */ 
/*     */ public class CategoryToPieDataset extends AbstractDataset implements PieDataset, DatasetChangeListener {
/*     */   private CategoryDataset source;
/*     */   
/*     */   private TableOrder extract;
/*     */   
/*     */   private int index;
/*     */   
/*     */   public CategoryToPieDataset(CategoryDataset source, TableOrder extract, int index) {
/*  86 */     if (extract == null)
/*  87 */       throw new IllegalArgumentException("Null 'extract' argument."); 
/*  89 */     this.source = source;
/*  90 */     this.source.addChangeListener(this);
/*  91 */     this.extract = extract;
/*  92 */     this.index = index;
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 102 */     int result = 0;
/* 103 */     if (this.source != null)
/* 104 */       if (this.extract == TableOrder.BY_ROW) {
/* 105 */         result = this.source.getColumnCount();
/* 107 */       } else if (this.extract == TableOrder.BY_COLUMN) {
/* 108 */         result = this.source.getRowCount();
/*     */       }  
/* 111 */     return result;
/*     */   }
/*     */   
/*     */   public Number getValue(int item) {
/* 122 */     Number result = null;
/* 123 */     if (this.source != null)
/* 124 */       if (this.extract == TableOrder.BY_ROW) {
/* 125 */         result = this.source.getValue(this.index, item);
/* 127 */       } else if (this.extract == TableOrder.BY_COLUMN) {
/* 128 */         result = this.source.getValue(item, this.index);
/*     */       }  
/* 131 */     return result;
/*     */   }
/*     */   
/*     */   public Comparable getKey(int index) {
/* 142 */     Comparable result = null;
/* 143 */     if (this.extract == TableOrder.BY_ROW) {
/* 144 */       result = this.source.getColumnKey(index);
/* 146 */     } else if (this.extract == TableOrder.BY_COLUMN) {
/* 147 */       result = this.source.getRowKey(index);
/*     */     } 
/* 149 */     return result;
/*     */   }
/*     */   
/*     */   public int getIndex(Comparable key) {
/* 160 */     int result = -1;
/* 161 */     if (this.extract == TableOrder.BY_ROW) {
/* 162 */       result = this.source.getColumnIndex(key);
/* 164 */     } else if (this.extract == TableOrder.BY_COLUMN) {
/* 165 */       result = this.source.getRowIndex(key);
/*     */     } 
/* 167 */     return result;
/*     */   }
/*     */   
/*     */   public List getKeys() {
/* 176 */     List result = null;
/* 177 */     if (this.extract == TableOrder.BY_ROW) {
/* 178 */       result = this.source.getColumnKeys();
/* 180 */     } else if (this.extract == TableOrder.BY_COLUMN) {
/* 181 */       result = this.source.getRowKeys();
/*     */     } 
/* 183 */     return result;
/*     */   }
/*     */   
/*     */   public Number getValue(Comparable key) {
/* 196 */     Number result = null;
/* 197 */     int keyIndex = getIndex(key);
/* 198 */     if (this.extract == TableOrder.BY_ROW) {
/* 199 */       result = this.source.getValue(this.index, keyIndex);
/* 201 */     } else if (this.extract == TableOrder.BY_COLUMN) {
/* 202 */       result = this.source.getValue(keyIndex, this.index);
/*     */     } 
/* 204 */     return result;
/*     */   }
/*     */   
/*     */   public void datasetChanged(DatasetChangeEvent event) {
/* 213 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\category\CategoryToPieDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */