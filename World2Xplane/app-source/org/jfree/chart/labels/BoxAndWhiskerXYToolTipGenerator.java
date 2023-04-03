/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ public class BoxAndWhiskerXYToolTipGenerator extends StandardXYToolTipGenerator implements XYToolTipGenerator, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -2648775791161459710L;
/*     */   
/*     */   public static final String DEFAULT_TOOL_TIP_FORMAT = "X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ";
/*     */   
/*     */   public BoxAndWhiskerXYToolTipGenerator() {
/*  98 */     super("X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ", NumberFormat.getInstance(), NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */   public BoxAndWhiskerXYToolTipGenerator(String toolTipFormat, DateFormat dateFormat, NumberFormat numberFormat) {
/* 118 */     super(toolTipFormat, dateFormat, numberFormat);
/*     */   }
/*     */   
/*     */   protected Object[] createItemArray(XYDataset dataset, int series, int item) {
/* 134 */     Object[] result = new Object[8];
/* 135 */     result[0] = dataset.getSeriesKey(series).toString();
/* 136 */     Number x = dataset.getX(series, item);
/* 137 */     if (getXDateFormat() != null) {
/* 138 */       result[1] = getXDateFormat().format(new Date(x.longValue()));
/*     */     } else {
/* 141 */       result[1] = getXFormat().format(x);
/*     */     } 
/* 143 */     NumberFormat formatter = getYFormat();
/* 145 */     if (dataset instanceof BoxAndWhiskerXYDataset) {
/* 146 */       BoxAndWhiskerXYDataset d = (BoxAndWhiskerXYDataset)dataset;
/* 147 */       result[2] = formatter.format(d.getMeanValue(series, item));
/* 148 */       result[3] = formatter.format(d.getMedianValue(series, item));
/* 149 */       result[4] = formatter.format(d.getMinRegularValue(series, item));
/* 150 */       result[5] = formatter.format(d.getMaxRegularValue(series, item));
/* 151 */       result[6] = formatter.format(d.getQ1Value(series, item));
/* 152 */       result[7] = formatter.format(d.getQ3Value(series, item));
/*     */     } 
/* 154 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 165 */     if (obj == this)
/* 166 */       return true; 
/* 168 */     if (!(obj instanceof BoxAndWhiskerXYToolTipGenerator))
/* 169 */       return false; 
/* 171 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\BoxAndWhiskerXYToolTipGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */