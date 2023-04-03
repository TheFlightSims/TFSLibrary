/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.DataUtilities;
/*     */ import org.jfree.data.Values2D;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public abstract class AbstractCategoryItemLabelGenerator implements PublicCloneable, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -7108591260223293197L;
/*     */   
/*     */   private String labelFormat;
/*     */   
/*     */   private String nullValueString;
/*     */   
/*     */   private NumberFormat numberFormat;
/*     */   
/*     */   private DateFormat dateFormat;
/*     */   
/*     */   private NumberFormat percentFormat;
/*     */   
/*     */   protected AbstractCategoryItemLabelGenerator(String labelFormat, NumberFormat formatter) {
/* 105 */     if (labelFormat == null)
/* 106 */       throw new IllegalArgumentException("Null 'labelFormat' argument."); 
/* 108 */     if (formatter == null)
/* 109 */       throw new IllegalArgumentException("Null 'formatter' argument."); 
/* 111 */     this.labelFormat = labelFormat;
/* 112 */     this.numberFormat = formatter;
/* 113 */     this.percentFormat = NumberFormat.getPercentInstance();
/* 114 */     this.dateFormat = null;
/* 115 */     this.nullValueString = "-";
/*     */   }
/*     */   
/*     */   protected AbstractCategoryItemLabelGenerator(String labelFormat, DateFormat formatter) {
/* 127 */     if (labelFormat == null)
/* 128 */       throw new IllegalArgumentException("Null 'labelFormat' argument."); 
/* 130 */     if (formatter == null)
/* 131 */       throw new IllegalArgumentException("Null 'formatter' argument."); 
/* 133 */     this.labelFormat = labelFormat;
/* 134 */     this.numberFormat = null;
/* 135 */     this.percentFormat = NumberFormat.getPercentInstance();
/* 136 */     this.dateFormat = formatter;
/* 137 */     this.nullValueString = "-";
/*     */   }
/*     */   
/*     */   public String generateRowLabel(CategoryDataset dataset, int row) {
/* 149 */     return dataset.getRowKey(row).toString();
/*     */   }
/*     */   
/*     */   public String generateColumnLabel(CategoryDataset dataset, int column) {
/* 161 */     return dataset.getColumnKey(column).toString();
/*     */   }
/*     */   
/*     */   public String getLabelFormat() {
/* 170 */     return this.labelFormat;
/*     */   }
/*     */   
/*     */   public NumberFormat getNumberFormat() {
/* 179 */     return this.numberFormat;
/*     */   }
/*     */   
/*     */   public DateFormat getDateFormat() {
/* 188 */     return this.dateFormat;
/*     */   }
/*     */   
/*     */   protected String generateLabelString(CategoryDataset dataset, int row, int column) {
/* 202 */     if (dataset == null)
/* 203 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 205 */     String result = null;
/* 206 */     Object[] items = createItemArray(dataset, row, column);
/* 207 */     result = MessageFormat.format(this.labelFormat, items);
/* 208 */     return result;
/*     */   }
/*     */   
/*     */   protected Object[] createItemArray(CategoryDataset dataset, int row, int column) {
/* 224 */     Object[] result = new Object[4];
/* 225 */     result[0] = dataset.getRowKey(row).toString();
/* 226 */     result[1] = dataset.getColumnKey(column).toString();
/* 227 */     Number value = dataset.getValue(row, column);
/* 228 */     if (value != null) {
/* 229 */       if (this.numberFormat != null) {
/* 230 */         result[2] = this.numberFormat.format(value);
/* 232 */       } else if (this.dateFormat != null) {
/* 233 */         result[2] = this.dateFormat.format(value);
/*     */       } 
/*     */     } else {
/* 237 */       result[2] = this.nullValueString;
/*     */     } 
/* 239 */     if (value != null) {
/* 240 */       double total = DataUtilities.calculateColumnTotal((Values2D)dataset, column);
/* 241 */       double percent = value.doubleValue() / total;
/* 242 */       result[3] = this.percentFormat.format(percent);
/*     */     } 
/* 245 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 256 */     if (obj == this)
/* 257 */       return true; 
/* 259 */     if (!(obj instanceof AbstractCategoryItemLabelGenerator))
/* 260 */       return false; 
/* 263 */     AbstractCategoryItemLabelGenerator that = (AbstractCategoryItemLabelGenerator)obj;
/* 265 */     if (!this.labelFormat.equals(that.labelFormat))
/* 266 */       return false; 
/* 268 */     if (!ObjectUtilities.equal(this.dateFormat, that.dateFormat))
/* 269 */       return false; 
/* 271 */     if (!ObjectUtilities.equal(this.numberFormat, that.numberFormat))
/* 272 */       return false; 
/* 274 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 285 */     AbstractCategoryItemLabelGenerator clone = (AbstractCategoryItemLabelGenerator)super.clone();
/* 287 */     if (this.numberFormat != null)
/* 288 */       clone.numberFormat = (NumberFormat)this.numberFormat.clone(); 
/* 290 */     if (this.dateFormat != null)
/* 291 */       clone.dateFormat = (DateFormat)this.dateFormat.clone(); 
/* 293 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\AbstractCategoryItemLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */