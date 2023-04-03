/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StandardCategorySeriesLabelGenerator implements CategorySeriesLabelGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 4630760091523940820L;
/*     */   
/*     */   public static final String DEFAULT_LABEL_FORMAT = "{0}";
/*     */   
/*     */   private String formatPattern;
/*     */   
/*     */   public StandardCategorySeriesLabelGenerator() {
/*  72 */     this("{0}");
/*     */   }
/*     */   
/*     */   public StandardCategorySeriesLabelGenerator(String format) {
/*  81 */     if (format == null)
/*  82 */       throw new IllegalArgumentException("Null 'format' argument."); 
/*  84 */     this.formatPattern = format;
/*     */   }
/*     */   
/*     */   public String generateLabel(CategoryDataset dataset, int series) {
/*  96 */     if (dataset == null)
/*  97 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/*  99 */     String label = MessageFormat.format(this.formatPattern, createItemArray(dataset, series));
/* 102 */     return label;
/*     */   }
/*     */   
/*     */   protected Object[] createItemArray(CategoryDataset dataset, int series) {
/* 115 */     Object[] result = new Object[1];
/* 116 */     result[0] = dataset.getRowKey(series).toString();
/* 117 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 128 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 139 */     if (obj == this)
/* 140 */       return true; 
/* 142 */     if (!(obj instanceof StandardCategorySeriesLabelGenerator))
/* 143 */       return false; 
/* 145 */     if (!super.equals(obj))
/* 146 */       return false; 
/* 148 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\StandardCategorySeriesLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */