/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.IntervalCategoryDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class IntervalCategoryItemLabelGenerator extends StandardCategoryItemLabelGenerator implements CategoryItemLabelGenerator, PublicCloneable, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 5056909225610630529L;
/*     */   
/*     */   public static final String DEFAULT_LABEL_FORMAT_STRING = "({0}, {1}) = {3} - {4}";
/*     */   
/*     */   public IntervalCategoryItemLabelGenerator() {
/*  73 */     super("({0}, {1}) = {3} - {4}", NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */   public IntervalCategoryItemLabelGenerator(String labelFormat, NumberFormat formatter) {
/*  85 */     super(labelFormat, formatter);
/*     */   }
/*     */   
/*     */   public IntervalCategoryItemLabelGenerator(String labelFormat, DateFormat formatter) {
/*  97 */     super(labelFormat, formatter);
/*     */   }
/*     */   
/*     */   protected Object[] createItemArray(CategoryDataset dataset, int row, int column) {
/* 112 */     Object[] result = new Object[5];
/* 113 */     result[0] = dataset.getRowKey(row).toString();
/* 114 */     result[1] = dataset.getColumnKey(column).toString();
/* 115 */     Number value = dataset.getValue(row, column);
/* 116 */     if (getNumberFormat() != null) {
/* 117 */       result[2] = getNumberFormat().format(value);
/* 119 */     } else if (getDateFormat() != null) {
/* 120 */       result[2] = getDateFormat().format(value);
/*     */     } 
/* 123 */     if (dataset instanceof IntervalCategoryDataset) {
/* 124 */       IntervalCategoryDataset icd = (IntervalCategoryDataset)dataset;
/* 125 */       Number start = icd.getStartValue(row, column);
/* 126 */       Number end = icd.getEndValue(row, column);
/* 127 */       if (getNumberFormat() != null) {
/* 128 */         result[3] = getNumberFormat().format(start);
/* 129 */         result[4] = getNumberFormat().format(end);
/* 131 */       } else if (getDateFormat() != null) {
/* 132 */         result[3] = getDateFormat().format(start);
/* 133 */         result[4] = getDateFormat().format(end);
/*     */       } 
/*     */     } 
/* 136 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\IntervalCategoryItemLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */