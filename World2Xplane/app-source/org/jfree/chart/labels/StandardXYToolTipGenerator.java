/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StandardXYToolTipGenerator extends AbstractXYItemLabelGenerator implements XYToolTipGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -3564164459039540784L;
/*     */   
/*     */   public static final String DEFAULT_TOOL_TIP_FORMAT = "{0}: ({1}, {2})";
/*     */   
/*     */   public static StandardXYToolTipGenerator getTimeSeriesInstance() {
/*  75 */     return new StandardXYToolTipGenerator("{0}: ({1}, {2})", DateFormat.getInstance(), NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */   public StandardXYToolTipGenerator() {
/*  85 */     this("{0}: ({1}, {2})", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
/*     */   }
/*     */   
/*     */   public StandardXYToolTipGenerator(String formatString, NumberFormat xFormat, NumberFormat yFormat) {
/* 105 */     super(formatString, xFormat, yFormat);
/*     */   }
/*     */   
/*     */   public StandardXYToolTipGenerator(String formatString, DateFormat xFormat, NumberFormat yFormat) {
/* 123 */     super(formatString, xFormat, yFormat);
/*     */   }
/*     */   
/*     */   public StandardXYToolTipGenerator(String formatString, DateFormat xFormat, DateFormat yFormat) {
/* 141 */     super(formatString, xFormat, yFormat);
/*     */   }
/*     */   
/*     */   public String generateToolTip(XYDataset dataset, int series, int item) {
/* 155 */     return generateLabelString(dataset, series, item);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 166 */     if (obj == this)
/* 167 */       return true; 
/* 169 */     if (obj instanceof StandardXYToolTipGenerator)
/* 170 */       return super.equals(obj); 
/* 172 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 183 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\StandardXYToolTipGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */