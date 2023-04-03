/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.time.RegularTimePeriod;
/*     */ import org.jfree.data.time.TimeSeriesCollection;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XisSymbolic;
/*     */ import org.jfree.data.xy.YisSymbolic;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class SymbolicXYItemLabelGenerator implements XYItemLabelGenerator, XYToolTipGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 3963400354475494395L;
/*     */   
/*     */   public String generateToolTip(XYDataset data, int series, int item) {
/*     */     String xStr;
/*     */     String yStr;
/*  88 */     if (data instanceof YisSymbolic) {
/*  89 */       yStr = ((YisSymbolic)data).getYSymbolicValue(series, item);
/*     */     } else {
/*  92 */       double y = data.getYValue(series, item);
/*  93 */       yStr = Double.toString(round(y, 2));
/*     */     } 
/*  95 */     if (data instanceof XisSymbolic) {
/*  96 */       xStr = ((XisSymbolic)data).getXSymbolicValue(series, item);
/*  98 */     } else if (data instanceof TimeSeriesCollection) {
/*  99 */       RegularTimePeriod p = ((TimeSeriesCollection)data).getSeries(series).getTimePeriod(item);
/* 102 */       xStr = p.toString();
/*     */     } else {
/* 105 */       double x = data.getXValue(series, item);
/* 106 */       xStr = Double.toString(round(x, 2));
/*     */     } 
/* 108 */     return "X: " + xStr + ", Y: " + yStr;
/*     */   }
/*     */   
/*     */   public String generateLabel(XYDataset dataset, int series, int category) {
/* 122 */     return null;
/*     */   }
/*     */   
/*     */   private static double round(double value, int nb) {
/* 134 */     if (nb <= 0)
/* 135 */       return Math.floor(value + 0.5D); 
/* 137 */     double p = Math.pow(10.0D, nb);
/* 138 */     double tempval = Math.floor(value * p + 0.5D);
/* 139 */     return tempval / p;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 150 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 161 */     if (obj == this)
/* 162 */       return true; 
/* 164 */     if (obj instanceof SymbolicXYItemLabelGenerator)
/* 165 */       return true; 
/* 167 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\SymbolicXYItemLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */