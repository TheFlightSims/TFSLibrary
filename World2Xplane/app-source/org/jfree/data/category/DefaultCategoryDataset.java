/*     */ package org.jfree.data.category;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DefaultKeyedValues2D;
/*     */ import org.jfree.data.general.AbstractDataset;
/*     */ 
/*     */ public class DefaultCategoryDataset extends AbstractDataset implements CategoryDataset, Serializable {
/*     */   private static final long serialVersionUID = -8168173757291644622L;
/*     */   
/*  73 */   private DefaultKeyedValues2D data = new DefaultKeyedValues2D();
/*     */   
/*     */   public int getRowCount() {
/*  82 */     return this.data.getRowCount();
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/*  91 */     return this.data.getColumnCount();
/*     */   }
/*     */   
/*     */   public Number getValue(int row, int column) {
/* 103 */     return this.data.getValue(row, column);
/*     */   }
/*     */   
/*     */   public Comparable getRowKey(int row) {
/* 114 */     return this.data.getRowKey(row);
/*     */   }
/*     */   
/*     */   public int getRowIndex(Comparable key) {
/* 125 */     return this.data.getRowIndex(key);
/*     */   }
/*     */   
/*     */   public List getRowKeys() {
/* 134 */     return this.data.getRowKeys();
/*     */   }
/*     */   
/*     */   public Comparable getColumnKey(int column) {
/* 145 */     return this.data.getColumnKey(column);
/*     */   }
/*     */   
/*     */   public int getColumnIndex(Comparable key) {
/* 156 */     return this.data.getColumnIndex(key);
/*     */   }
/*     */   
/*     */   public List getColumnKeys() {
/* 165 */     return this.data.getColumnKeys();
/*     */   }
/*     */   
/*     */   public Number getValue(Comparable rowKey, Comparable columnKey) {
/* 179 */     return this.data.getValue(rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public void addValue(Number value, Comparable rowKey, Comparable columnKey) {
/* 191 */     this.data.addValue(value, rowKey, columnKey);
/* 192 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void addValue(double value, Comparable rowKey, Comparable columnKey) {
/* 204 */     addValue(new Double(value), rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public void setValue(Number value, Comparable rowKey, Comparable columnKey) {
/* 217 */     this.data.setValue(value, rowKey, columnKey);
/* 218 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void setValue(double value, Comparable rowKey, Comparable columnKey) {
/* 231 */     setValue(new Double(value), rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public void incrementValue(double value, Comparable rowKey, Comparable columnKey) {
/* 247 */     double existing = 0.0D;
/* 248 */     Number n = getValue(rowKey, columnKey);
/* 249 */     if (n != null)
/* 250 */       existing = n.doubleValue(); 
/* 252 */     setValue(existing + value, rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public void removeValue(Comparable rowKey, Comparable columnKey) {
/* 262 */     this.data.removeValue(rowKey, columnKey);
/* 263 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeRow(int rowIndex) {
/* 272 */     this.data.removeRow(rowIndex);
/* 273 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeRow(Comparable rowKey) {
/* 282 */     this.data.removeRow(rowKey);
/* 283 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeColumn(int columnIndex) {
/* 292 */     this.data.removeColumn(columnIndex);
/* 293 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void removeColumn(Comparable columnKey) {
/* 302 */     this.data.removeColumn(columnKey);
/* 303 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 311 */     this.data.clear();
/* 312 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 324 */     if (obj == this)
/* 325 */       return true; 
/* 328 */     if (!(obj instanceof CategoryDataset))
/* 329 */       return false; 
/* 332 */     CategoryDataset that = (CategoryDataset)obj;
/* 333 */     if (!getRowKeys().equals(that.getRowKeys()))
/* 334 */       return false; 
/* 337 */     if (!getColumnKeys().equals(that.getColumnKeys()))
/* 338 */       return false; 
/* 341 */     int rowCount = getRowCount();
/* 342 */     int colCount = getColumnCount();
/* 343 */     for (int r = 0; r < rowCount; r++) {
/* 344 */       for (int c = 0; c < colCount; c++) {
/* 345 */         Number v1 = getValue(r, c);
/* 346 */         Number v2 = that.getValue(r, c);
/* 347 */         if (v1 == null) {
/* 348 */           if (v2 != null)
/* 349 */             return false; 
/* 352 */         } else if (!v1.equals(v2)) {
/* 353 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 357 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 366 */     return this.data.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\category\DefaultCategoryDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */