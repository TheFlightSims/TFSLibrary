/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ public class TimeSeriesURLGenerator implements XYURLGenerator, Serializable {
/*     */   private static final long serialVersionUID = -9122773175671182445L;
/*     */   
/*  67 */   private DateFormat dateFormat = DateFormat.getInstance();
/*     */   
/*  70 */   private String prefix = "index.html";
/*     */   
/*  73 */   private String seriesParameterName = "series";
/*     */   
/*  76 */   private String itemParameterName = "item";
/*     */   
/*     */   public TimeSeriesURLGenerator() {}
/*     */   
/*     */   public TimeSeriesURLGenerator(DateFormat dDateFormat, String sPrefix, String sSeriesParameterName, String sItemParameterName) {
/*  97 */     this.dateFormat = dDateFormat;
/*  98 */     this.prefix = sPrefix;
/*  99 */     this.seriesParameterName = sSeriesParameterName;
/* 100 */     this.itemParameterName = sItemParameterName;
/*     */   }
/*     */   
/*     */   public String generateURL(XYDataset dataset, int series, int item) {
/* 114 */     String result = this.prefix;
/* 115 */     boolean firstParameter = (result.indexOf("?") == -1);
/* 116 */     Comparable seriesKey = dataset.getSeriesKey(series);
/* 117 */     if (seriesKey != null) {
/* 118 */       result = result + (firstParameter ? "?" : "&amp;");
/* 119 */       result = result + this.seriesParameterName + "=" + seriesKey.toString();
/* 120 */       firstParameter = false;
/*     */     } 
/* 123 */     long x = dataset.getX(series, item).longValue();
/* 124 */     String xValue = this.dateFormat.format(new Date(x));
/* 125 */     result = result + (firstParameter ? "?" : "&amp;");
/* 126 */     result = result + this.itemParameterName + "=" + xValue;
/* 128 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\char\\urls\TimeSeriesURLGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */