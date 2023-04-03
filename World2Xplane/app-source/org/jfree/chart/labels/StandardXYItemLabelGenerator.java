/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StandardXYItemLabelGenerator extends AbstractXYItemLabelGenerator implements XYItemLabelGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 7807668053171837925L;
/*     */   
/*     */   public static final String DEFAULT_ITEM_LABEL_FORMAT = "{2}";
/*     */   
/*     */   public StandardXYItemLabelGenerator() {
/*  89 */     this("{2}", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
/*     */   }
/*     */   
/*     */   public StandardXYItemLabelGenerator(String formatString, NumberFormat xFormat, NumberFormat yFormat) {
/* 110 */     super(formatString, xFormat, yFormat);
/*     */   }
/*     */   
/*     */   public StandardXYItemLabelGenerator(String formatString, DateFormat xFormat, NumberFormat yFormat) {
/* 128 */     super(formatString, xFormat, yFormat);
/*     */   }
/*     */   
/*     */   public StandardXYItemLabelGenerator(String formatString, DateFormat xFormat, DateFormat yFormat) {
/* 146 */     super(formatString, xFormat, yFormat);
/*     */   }
/*     */   
/*     */   public String generateLabel(XYDataset dataset, int series, int item) {
/* 160 */     return generateLabelString(dataset, series, item);
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 171 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 182 */     if (obj == this)
/* 183 */       return true; 
/* 185 */     if (obj instanceof StandardXYItemLabelGenerator)
/* 186 */       return super.equals(obj); 
/* 188 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\StandardXYItemLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */