/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class StandardXYURLGenerator implements XYURLGenerator, Serializable {
/*     */   private static final long serialVersionUID = -1771624523496595382L;
/*     */   
/*     */   public static final String DEFAULT_PREFIX = "index.html";
/*     */   
/*     */   public static final String DEFAULT_SERIES_PARAMETER = "series";
/*     */   
/*     */   public static final String DEFAULT_ITEM_PARAMETER = "item";
/*     */   
/*     */   private String prefix;
/*     */   
/*     */   private String seriesParameterName;
/*     */   
/*     */   private String itemParameterName;
/*     */   
/*     */   public StandardXYURLGenerator() {
/*  90 */     this("index.html", "series", "item");
/*     */   }
/*     */   
/*     */   public StandardXYURLGenerator(String prefix) {
/* 101 */     this(prefix, "series", "item");
/*     */   }
/*     */   
/*     */   public StandardXYURLGenerator(String prefix, String seriesParameterName, String itemParameterName) {
/* 116 */     if (prefix == null)
/* 117 */       throw new IllegalArgumentException("Null 'prefix' argument."); 
/* 119 */     if (seriesParameterName == null)
/* 120 */       throw new IllegalArgumentException("Null 'seriesParameterName' argument."); 
/* 124 */     if (itemParameterName == null)
/* 125 */       throw new IllegalArgumentException("Null 'itemParameterName' argument."); 
/* 129 */     this.prefix = prefix;
/* 130 */     this.seriesParameterName = seriesParameterName;
/* 131 */     this.itemParameterName = itemParameterName;
/*     */   }
/*     */   
/*     */   public String generateURL(XYDataset dataset, int series, int item) {
/* 144 */     String url = this.prefix;
/* 145 */     boolean firstParameter = (url.indexOf("?") == -1);
/* 146 */     url = url + (firstParameter ? "?" : "&amp;");
/* 147 */     url = url + this.seriesParameterName + "=" + series + "&amp;" + this.itemParameterName + "=" + item;
/* 149 */     return url;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 160 */     if (obj == this)
/* 161 */       return true; 
/* 163 */     if (!(obj instanceof StandardXYURLGenerator))
/* 164 */       return false; 
/* 166 */     StandardXYURLGenerator that = (StandardXYURLGenerator)obj;
/* 167 */     if (!ObjectUtilities.equal(that.prefix, this.prefix))
/* 168 */       return false; 
/* 170 */     if (!ObjectUtilities.equal(that.seriesParameterName, this.seriesParameterName))
/* 172 */       return false; 
/* 174 */     if (!ObjectUtilities.equal(that.itemParameterName, this.itemParameterName))
/* 176 */       return false; 
/* 178 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\char\\urls\StandardXYURLGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */