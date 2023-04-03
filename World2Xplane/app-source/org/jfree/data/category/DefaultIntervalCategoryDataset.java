/*     */ package org.jfree.data.category;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import org.jfree.data.DataUtilities;
/*     */ import org.jfree.data.general.AbstractSeriesDataset;
/*     */ 
/*     */ public class DefaultIntervalCategoryDataset extends AbstractSeriesDataset implements IntervalCategoryDataset {
/*     */   private Comparable[] seriesKeys;
/*     */   
/*     */   private Comparable[] categoryKeys;
/*     */   
/*     */   private Number[][] startData;
/*     */   
/*     */   private Number[][] endData;
/*     */   
/*     */   public DefaultIntervalCategoryDataset(double[][] starts, double[][] ends) {
/*  86 */     this(DataUtilities.createNumberArray2D(starts), DataUtilities.createNumberArray2D(ends));
/*     */   }
/*     */   
/*     */   public DefaultIntervalCategoryDataset(Number[][] starts, Number[][] ends) {
/* 104 */     this(null, null, starts, ends);
/*     */   }
/*     */   
/*     */   public DefaultIntervalCategoryDataset(String[] seriesNames, Number[][] starts, Number[][] ends) {
/* 122 */     this((Comparable[])seriesNames, null, starts, ends);
/*     */   }
/*     */   
/*     */   public DefaultIntervalCategoryDataset(Comparable[] seriesKeys, Comparable[] categoryKeys, Number[][] starts, Number[][] ends) {
/* 141 */     this.startData = starts;
/* 142 */     this.endData = ends;
/* 144 */     if (starts != null && ends != null) {
/* 146 */       String baseName = "org.jfree.data.resources.DataPackageResources";
/* 147 */       ResourceBundle resources = ResourceBundle.getBundle(baseName);
/* 149 */       int seriesCount = starts.length;
/* 150 */       if (seriesCount != ends.length) {
/* 151 */         String errMsg = "DefaultIntervalCategoryDataset: the number of series in the start value dataset does not match the number of series in the end value dataset.";
/* 155 */         throw new IllegalArgumentException(errMsg);
/*     */       } 
/* 157 */       if (seriesCount > 0) {
/* 160 */         if (seriesKeys != null) {
/* 162 */           if (seriesKeys.length != seriesCount)
/* 163 */             throw new IllegalArgumentException("The number of series keys does not match the number of series in the data."); 
/* 169 */           this.seriesKeys = seriesKeys;
/*     */         } else {
/* 172 */           String prefix = resources.getString("series.default-prefix") + " ";
/* 174 */           this.seriesKeys = generateKeys(seriesCount, prefix);
/*     */         } 
/* 178 */         int categoryCount = (starts[0]).length;
/* 179 */         if (categoryCount != (ends[0]).length) {
/* 180 */           String errMsg = "DefaultIntervalCategoryDataset: the number of categories in the start value dataset does not match the number of categories in the end value dataset.";
/* 184 */           throw new IllegalArgumentException(errMsg);
/*     */         } 
/* 186 */         if (categoryKeys != null) {
/* 187 */           if (categoryKeys.length != categoryCount)
/* 188 */             throw new IllegalArgumentException("The number of category keys does not match the number of categories in the data."); 
/* 193 */           this.categoryKeys = categoryKeys;
/*     */         } else {
/* 196 */           String prefix = resources.getString("categories.default-prefix") + " ";
/* 199 */           this.categoryKeys = generateKeys(categoryCount, prefix);
/*     */         } 
/*     */       } else {
/* 204 */         this.seriesKeys = null;
/* 205 */         this.categoryKeys = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 217 */     int result = 0;
/* 218 */     if (this.startData != null)
/* 219 */       result = this.startData.length; 
/* 221 */     return result;
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 230 */     return this.categoryKeys.length;
/*     */   }
/*     */   
/*     */   public int getSeriesIndex(Comparable series) {
/* 241 */     List seriesKeys = getSeries();
/* 242 */     return seriesKeys.indexOf(series);
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 253 */     if (series >= getSeriesCount() || series < 0)
/* 254 */       throw new IllegalArgumentException("No such series : " + series); 
/* 256 */     return this.seriesKeys[series];
/*     */   }
/*     */   
/*     */   public void setSeriesKeys(Comparable[] seriesKeys) {
/* 267 */     if (seriesKeys == null)
/* 268 */       throw new IllegalArgumentException("Null 'seriesKeys' argument."); 
/* 271 */     if (seriesKeys.length != getSeriesCount())
/* 272 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setSeriesKeys(): the number of series keys does not match the data."); 
/* 279 */     this.seriesKeys = seriesKeys;
/* 280 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public int getCategoryCount() {
/* 292 */     int result = 0;
/* 293 */     if (this.startData != null && 
/* 294 */       getSeriesCount() > 0)
/* 295 */       result = (this.startData[0]).length; 
/* 298 */     return result;
/*     */   }
/*     */   
/*     */   public List getSeries() {
/* 312 */     if (this.seriesKeys == null)
/* 313 */       return new ArrayList(); 
/* 316 */     return Collections.unmodifiableList(Arrays.asList((Object[])this.seriesKeys));
/*     */   }
/*     */   
/*     */   public List getCategories() {
/* 329 */     return getColumnKeys();
/*     */   }
/*     */   
/*     */   public List getColumnKeys() {
/* 343 */     if (this.categoryKeys == null)
/* 344 */       return new ArrayList(); 
/* 347 */     return Collections.unmodifiableList(Arrays.asList((Object[])this.categoryKeys));
/*     */   }
/*     */   
/*     */   public void setCategoryKeys(Comparable[] categoryKeys) {
/* 363 */     if (categoryKeys == null)
/* 364 */       throw new IllegalArgumentException("Null 'categoryKeys' argument."); 
/* 367 */     if (categoryKeys.length != (this.startData[0]).length)
/* 368 */       throw new IllegalArgumentException("The number of categories does not match the data."); 
/* 373 */     for (int i = 0; i < categoryKeys.length; i++) {
/* 374 */       if (categoryKeys[i] == null)
/* 375 */         throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setCategoryKeys(): null category not permitted."); 
/*     */     } 
/* 382 */     this.categoryKeys = categoryKeys;
/* 383 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public Number getValue(Comparable series, Comparable category) {
/* 397 */     int seriesIndex = getSeriesIndex(series);
/* 398 */     int itemIndex = getColumnIndex(category);
/* 399 */     return getValue(seriesIndex, itemIndex);
/*     */   }
/*     */   
/*     */   public Number getValue(int series, int category) {
/* 414 */     return getEndValue(series, category);
/*     */   }
/*     */   
/*     */   public Number getStartValue(Comparable series, Comparable category) {
/* 427 */     int seriesIndex = getSeriesIndex(series);
/* 428 */     int itemIndex = getColumnIndex(category);
/* 429 */     return getStartValue(seriesIndex, itemIndex);
/*     */   }
/*     */   
/*     */   public Number getStartValue(int series, int category) {
/* 444 */     if (series < 0 || series >= getSeriesCount())
/* 445 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.getValue(): series index out of range."); 
/* 450 */     if (category < 0 || category >= getCategoryCount())
/* 451 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.getValue(): category index out of range."); 
/* 457 */     return this.startData[series][category];
/*     */   }
/*     */   
/*     */   public Number getEndValue(Comparable series, Comparable category) {
/* 470 */     int seriesIndex = getSeriesIndex(series);
/* 471 */     int itemIndex = getColumnIndex(category);
/* 472 */     return getEndValue(seriesIndex, itemIndex);
/*     */   }
/*     */   
/*     */   public Number getEndValue(int series, int category) {
/* 486 */     if (series < 0 || series >= getSeriesCount())
/* 487 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.getValue(): series index out of range."); 
/* 492 */     if (category < 0 || category >= getCategoryCount())
/* 493 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.getValue(): category index out of range."); 
/* 499 */     return this.endData[series][category];
/*     */   }
/*     */   
/*     */   public void setStartValue(int series, Comparable category, Number value) {
/* 514 */     if (series < 0 || series > getSeriesCount())
/* 515 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setValue: series outside valid range."); 
/* 521 */     int categoryIndex = getCategoryIndex(category);
/* 522 */     if (categoryIndex < 0)
/* 523 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setValue: unrecognised category."); 
/* 529 */     this.startData[series][categoryIndex] = value;
/* 530 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void setEndValue(int series, Comparable category, Number value) {
/* 545 */     if (series < 0 || series > getSeriesCount())
/* 546 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setValue: series outside valid range."); 
/* 552 */     int categoryIndex = getCategoryIndex(category);
/* 553 */     if (categoryIndex < 0)
/* 554 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setValue: unrecognised category."); 
/* 560 */     this.endData[series][categoryIndex] = value;
/* 561 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   private int getCategoryIndex(Comparable category) {
/* 573 */     int result = -1;
/* 574 */     for (int i = 0; i < this.categoryKeys.length; i++) {
/* 575 */       if (category.equals(this.categoryKeys[i])) {
/* 576 */         result = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 580 */     return result;
/*     */   }
/*     */   
/*     */   private Comparable[] generateKeys(int count, String prefix) {
/* 593 */     Comparable[] result = new Comparable[count];
/* 595 */     for (int i = 0; i < count; i++) {
/* 596 */       String name = prefix + (i + 1);
/* 597 */       result[i] = name;
/*     */     } 
/* 599 */     return result;
/*     */   }
/*     */   
/*     */   public Comparable getColumnKey(int column) {
/* 610 */     return this.categoryKeys[column];
/*     */   }
/*     */   
/*     */   public int getColumnIndex(Comparable columnKey) {
/* 621 */     List categories = getCategories();
/* 622 */     return categories.indexOf(columnKey);
/*     */   }
/*     */   
/*     */   public int getRowIndex(Comparable rowKey) {
/* 633 */     List seriesKeys = getSeries();
/* 634 */     return seriesKeys.indexOf(rowKey);
/*     */   }
/*     */   
/*     */   public List getRowKeys() {
/* 647 */     if (this.seriesKeys == null)
/* 648 */       return new ArrayList(); 
/* 651 */     return Collections.unmodifiableList(Arrays.asList((Object[])this.seriesKeys));
/*     */   }
/*     */   
/*     */   public Comparable getRowKey(int row) {
/* 663 */     if (row >= getRowCount() || row < 0)
/* 664 */       throw new IllegalArgumentException("The 'row' argument is out of bounds."); 
/* 667 */     return this.seriesKeys[row];
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 677 */     int result = 0;
/* 678 */     if (this.startData != null && 
/* 679 */       getSeriesCount() > 0)
/* 680 */       result = (this.startData[0]).length; 
/* 683 */     return result;
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 692 */     int result = 0;
/* 693 */     if (this.startData != null)
/* 694 */       result = this.startData.length; 
/* 696 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\category\DefaultIntervalCategoryDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */