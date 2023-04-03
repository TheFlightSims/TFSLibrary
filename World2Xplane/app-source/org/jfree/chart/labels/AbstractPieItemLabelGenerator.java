/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ 
/*     */ public class AbstractPieItemLabelGenerator implements Serializable {
/*     */   private static final long serialVersionUID = 7347703325267846275L;
/*     */   
/*     */   private String labelFormat;
/*     */   
/*     */   private NumberFormat numberFormat;
/*     */   
/*     */   private NumberFormat percentFormat;
/*     */   
/*     */   protected AbstractPieItemLabelGenerator(String labelFormat, NumberFormat numberFormat, NumberFormat percentFormat) {
/*  84 */     if (labelFormat == null)
/*  85 */       throw new IllegalArgumentException("Null 'labelFormat' argument."); 
/*  87 */     if (numberFormat == null)
/*  88 */       throw new IllegalArgumentException("Null 'numberFormat' argument."); 
/*  90 */     if (percentFormat == null)
/*  91 */       throw new IllegalArgumentException("Null 'percentFormat' argument."); 
/*  95 */     this.labelFormat = labelFormat;
/*  96 */     this.numberFormat = numberFormat;
/*  97 */     this.percentFormat = percentFormat;
/*     */   }
/*     */   
/*     */   public String getLabelFormat() {
/* 107 */     return this.labelFormat;
/*     */   }
/*     */   
/*     */   public NumberFormat getNumberFormat() {
/* 116 */     return this.numberFormat;
/*     */   }
/*     */   
/*     */   public NumberFormat getPercentFormat() {
/* 125 */     return this.percentFormat;
/*     */   }
/*     */   
/*     */   protected Object[] createItemArray(PieDataset dataset, Comparable key) {
/* 145 */     Object[] result = new Object[4];
/* 146 */     double total = DatasetUtilities.calculatePieDatasetTotal(dataset);
/* 147 */     result[0] = key.toString();
/* 148 */     Number value = dataset.getValue(key);
/* 149 */     if (value != null) {
/* 150 */       result[1] = this.numberFormat.format(value);
/*     */     } else {
/* 153 */       result[1] = "null";
/*     */     } 
/* 155 */     double percent = 0.0D;
/* 156 */     if (value != null) {
/* 157 */       double v = value.doubleValue();
/* 158 */       if (v > 0.0D)
/* 159 */         percent = v / total; 
/*     */     } 
/* 162 */     result[2] = this.percentFormat.format(percent);
/* 163 */     result[3] = this.numberFormat.format(total);
/* 164 */     return result;
/*     */   }
/*     */   
/*     */   protected String generateSectionLabel(PieDataset dataset, Comparable key) {
/* 176 */     String result = null;
/* 177 */     if (dataset != null) {
/* 178 */       Object[] items = createItemArray(dataset, key);
/* 179 */       result = MessageFormat.format(this.labelFormat, items);
/*     */     } 
/* 181 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 192 */     if (obj == this)
/* 193 */       return true; 
/* 195 */     if (!(obj instanceof AbstractPieItemLabelGenerator))
/* 196 */       return false; 
/* 199 */     AbstractPieItemLabelGenerator that = (AbstractPieItemLabelGenerator)obj;
/* 201 */     if (!this.labelFormat.equals(that.labelFormat))
/* 202 */       return false; 
/* 204 */     if (!this.numberFormat.equals(that.numberFormat))
/* 205 */       return false; 
/* 207 */     if (!this.percentFormat.equals(that.percentFormat))
/* 208 */       return false; 
/* 210 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 222 */     AbstractPieItemLabelGenerator clone = (AbstractPieItemLabelGenerator)super.clone();
/* 224 */     if (this.numberFormat != null)
/* 225 */       clone.numberFormat = (NumberFormat)this.numberFormat.clone(); 
/* 227 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\AbstractPieItemLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */