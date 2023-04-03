/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StandardXYSeriesLabelGenerator implements XYSeriesLabelGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 1916017081848400024L;
/*     */   
/*     */   public static final String DEFAULT_LABEL_FORMAT = "{0}";
/*     */   
/*     */   private String formatPattern;
/*     */   
/*     */   public StandardXYSeriesLabelGenerator() {
/*  74 */     this("{0}");
/*     */   }
/*     */   
/*     */   public StandardXYSeriesLabelGenerator(String format) {
/*  83 */     if (format == null)
/*  84 */       throw new IllegalArgumentException("Null 'format' argument."); 
/*  86 */     this.formatPattern = format;
/*     */   }
/*     */   
/*     */   public String generateLabel(XYDataset dataset, int series) {
/*  99 */     if (dataset == null)
/* 100 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 102 */     String label = MessageFormat.format(this.formatPattern, createItemArray(dataset, series));
/* 105 */     return label;
/*     */   }
/*     */   
/*     */   protected Object[] createItemArray(XYDataset dataset, int series) {
/* 118 */     Object[] result = new Object[1];
/* 119 */     result[0] = dataset.getSeriesKey(series).toString();
/* 120 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 131 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 142 */     if (obj == this)
/* 143 */       return true; 
/* 145 */     if (!(obj instanceof StandardXYSeriesLabelGenerator))
/* 146 */       return false; 
/* 148 */     if (!super.equals(obj))
/* 149 */       return false; 
/* 151 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\StandardXYSeriesLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */