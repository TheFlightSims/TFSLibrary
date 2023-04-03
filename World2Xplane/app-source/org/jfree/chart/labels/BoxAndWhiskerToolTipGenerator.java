/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class BoxAndWhiskerToolTipGenerator extends StandardCategoryToolTipGenerator implements CategoryToolTipGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -6076837753823076334L;
/*     */   
/*     */   public static final String DEFAULT_TOOL_TIP_FORMAT = "X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ";
/*     */   
/*     */   public BoxAndWhiskerToolTipGenerator() {
/*  88 */     super("X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ", NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */   public BoxAndWhiskerToolTipGenerator(String format, NumberFormat formatter) {
/*  99 */     super(format, formatter);
/*     */   }
/*     */   
/*     */   protected Object[] createItemArray(CategoryDataset dataset, int series, int item) {
/* 114 */     Object[] result = new Object[8];
/* 115 */     result[0] = dataset.getRowKey(series);
/* 116 */     Number y = dataset.getValue(series, item);
/* 117 */     NumberFormat formatter = getNumberFormat();
/* 118 */     result[1] = formatter.format(y);
/* 119 */     if (dataset instanceof BoxAndWhiskerCategoryDataset) {
/* 120 */       BoxAndWhiskerCategoryDataset d = (BoxAndWhiskerCategoryDataset)dataset;
/* 122 */       result[2] = formatter.format(d.getMeanValue(series, item));
/* 123 */       result[3] = formatter.format(d.getMedianValue(series, item));
/* 124 */       result[4] = formatter.format(d.getMinRegularValue(series, item));
/* 125 */       result[5] = formatter.format(d.getMaxRegularValue(series, item));
/* 126 */       result[6] = formatter.format(d.getQ1Value(series, item));
/* 127 */       result[7] = formatter.format(d.getQ3Value(series, item));
/*     */     } 
/* 129 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 140 */     if (obj == this)
/* 141 */       return true; 
/* 143 */     if (obj instanceof BoxAndWhiskerToolTipGenerator)
/* 144 */       return super.equals(obj); 
/* 146 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\BoxAndWhiskerToolTipGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */