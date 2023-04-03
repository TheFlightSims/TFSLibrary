/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class MultipleXYSeriesLabelGenerator implements XYSeriesLabelGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 138976236941898560L;
/*     */   
/*     */   public static final String DEFAULT_LABEL_FORMAT = "{0}";
/*     */   
/*     */   private String formatPattern;
/*     */   
/*     */   private String additionalFormatPattern;
/*     */   
/*     */   private Map seriesLabelLists;
/*     */   
/*     */   public MultipleXYSeriesLabelGenerator() {
/*  83 */     this("{0}");
/*     */   }
/*     */   
/*     */   public MultipleXYSeriesLabelGenerator(String format) {
/*  92 */     if (format == null)
/*  93 */       throw new IllegalArgumentException("Null 'format' argument."); 
/*  95 */     this.formatPattern = format;
/*  96 */     this.additionalFormatPattern = "\n{0}";
/*  97 */     this.seriesLabelLists = new HashMap();
/*     */   }
/*     */   
/*     */   public void addSeriesLabel(int series, String label) {
/* 107 */     Integer key = new Integer(series);
/* 108 */     List labelList = (List)this.seriesLabelLists.get(key);
/* 109 */     if (labelList == null) {
/* 110 */       labelList = new ArrayList();
/* 111 */       this.seriesLabelLists.put(key, labelList);
/*     */     } 
/* 113 */     labelList.add(label);
/*     */   }
/*     */   
/*     */   public void clearSeriesLabels(int series) {
/* 122 */     Integer key = new Integer(series);
/* 123 */     this.seriesLabelLists.put(key, null);
/*     */   }
/*     */   
/*     */   public String generateLabel(XYDataset dataset, int series) {
/* 136 */     if (dataset == null)
/* 137 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 139 */     StringBuffer label = new StringBuffer();
/* 140 */     label.append(MessageFormat.format(this.formatPattern, createItemArray(dataset, series)));
/* 145 */     Integer key = new Integer(series);
/* 146 */     List extraLabels = (List)this.seriesLabelLists.get(key);
/* 147 */     if (extraLabels != null) {
/* 148 */       Object[] temp = new Object[1];
/* 149 */       for (int i = 0; i < extraLabels.size(); i++) {
/* 150 */         temp[0] = extraLabels.get(i);
/* 151 */         String labelAddition = MessageFormat.format(this.additionalFormatPattern, temp);
/* 154 */         label.append(labelAddition);
/*     */       } 
/*     */     } 
/* 157 */     return label.toString();
/*     */   }
/*     */   
/*     */   protected Object[] createItemArray(XYDataset dataset, int series) {
/* 170 */     Object[] result = new Object[1];
/* 171 */     result[0] = dataset.getSeriesKey(series).toString();
/* 172 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 183 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 194 */     if (obj == this)
/* 195 */       return true; 
/* 197 */     if (!(obj instanceof StandardXYSeriesLabelGenerator))
/* 198 */       return false; 
/* 200 */     if (!super.equals(obj))
/* 201 */       return false; 
/* 203 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\MultipleXYSeriesLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */