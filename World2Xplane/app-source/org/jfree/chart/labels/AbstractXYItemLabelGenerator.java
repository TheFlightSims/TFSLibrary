/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class AbstractXYItemLabelGenerator implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 5869744396278660636L;
/*     */   
/*     */   private String formatString;
/*     */   
/*     */   private NumberFormat xFormat;
/*     */   
/*     */   private DateFormat xDateFormat;
/*     */   
/*     */   private NumberFormat yFormat;
/*     */   
/*     */   private DateFormat yDateFormat;
/*     */   
/*  85 */   private String nullXString = "null";
/*     */   
/*  88 */   private String nullYString = "null";
/*     */   
/*     */   protected AbstractXYItemLabelGenerator() {
/*  94 */     this("{2}", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
/*     */   }
/*     */   
/*     */   protected AbstractXYItemLabelGenerator(String formatString, NumberFormat xFormat, NumberFormat yFormat) {
/* 114 */     if (formatString == null)
/* 115 */       throw new IllegalArgumentException("Null 'formatString' argument."); 
/* 117 */     if (xFormat == null)
/* 118 */       throw new IllegalArgumentException("Null 'xFormat' argument."); 
/* 120 */     if (yFormat == null)
/* 121 */       throw new IllegalArgumentException("Null 'yFormat' argument."); 
/* 123 */     this.formatString = formatString;
/* 124 */     this.xFormat = xFormat;
/* 125 */     this.yFormat = yFormat;
/*     */   }
/*     */   
/*     */   protected AbstractXYItemLabelGenerator(String formatString, DateFormat xFormat, NumberFormat yFormat) {
/* 143 */     this(formatString, NumberFormat.getInstance(), yFormat);
/* 144 */     this.xDateFormat = xFormat;
/*     */   }
/*     */   
/*     */   protected AbstractXYItemLabelGenerator(String formatString, DateFormat xFormat, DateFormat yFormat) {
/* 162 */     this(formatString, NumberFormat.getInstance(), NumberFormat.getInstance());
/* 166 */     this.xDateFormat = xFormat;
/* 167 */     this.yDateFormat = yFormat;
/*     */   }
/*     */   
/*     */   public String getFormatString() {
/* 178 */     return this.formatString;
/*     */   }
/*     */   
/*     */   public NumberFormat getXFormat() {
/* 187 */     return this.xFormat;
/*     */   }
/*     */   
/*     */   public DateFormat getXDateFormat() {
/* 196 */     return this.xDateFormat;
/*     */   }
/*     */   
/*     */   public NumberFormat getYFormat() {
/* 205 */     return this.yFormat;
/*     */   }
/*     */   
/*     */   public DateFormat getYDateFormat() {
/* 214 */     return this.yDateFormat;
/*     */   }
/*     */   
/*     */   public String generateLabelString(XYDataset dataset, int series, int item) {
/* 227 */     String result = null;
/* 228 */     Object[] items = createItemArray(dataset, series, item);
/* 229 */     result = MessageFormat.format(this.formatString, items);
/* 230 */     return result;
/*     */   }
/*     */   
/*     */   protected Object[] createItemArray(XYDataset dataset, int series, int item) {
/* 245 */     Object[] result = new Object[3];
/* 246 */     result[0] = dataset.getSeriesKey(series).toString();
/* 248 */     double x = dataset.getXValue(series, item);
/* 249 */     if (Double.isNaN(x) && dataset.getX(series, item) == null) {
/* 250 */       result[1] = this.nullXString;
/* 253 */     } else if (this.xDateFormat != null) {
/* 254 */       result[1] = this.xDateFormat.format(new Date((long)x));
/*     */     } else {
/* 257 */       result[1] = this.xFormat.format(x);
/*     */     } 
/* 261 */     double y = dataset.getYValue(series, item);
/* 262 */     if (Double.isNaN(y) && dataset.getY(series, item) == null) {
/* 263 */       result[2] = this.nullYString;
/* 266 */     } else if (this.yDateFormat != null) {
/* 267 */       result[2] = this.yDateFormat.format(new Date((long)y));
/*     */     } else {
/* 270 */       result[2] = this.yFormat.format(y);
/*     */     } 
/* 273 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 284 */     if (obj == this)
/* 285 */       return true; 
/* 287 */     if (!(obj instanceof AbstractXYItemLabelGenerator))
/* 288 */       return false; 
/* 290 */     AbstractXYItemLabelGenerator that = (AbstractXYItemLabelGenerator)obj;
/* 291 */     if (!this.formatString.equals(that.formatString))
/* 292 */       return false; 
/* 294 */     if (!ObjectUtilities.equal(this.xFormat, that.xFormat))
/* 295 */       return false; 
/* 297 */     if (!ObjectUtilities.equal(this.xDateFormat, that.xDateFormat))
/* 298 */       return false; 
/* 300 */     if (!ObjectUtilities.equal(this.yFormat, that.yFormat))
/* 301 */       return false; 
/* 303 */     if (!ObjectUtilities.equal(this.yDateFormat, that.yDateFormat))
/* 304 */       return false; 
/* 306 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 319 */     AbstractXYItemLabelGenerator clone = (AbstractXYItemLabelGenerator)super.clone();
/* 322 */     if (this.xFormat != null)
/* 323 */       clone.xFormat = (NumberFormat)this.xFormat.clone(); 
/* 326 */     if (this.yFormat != null)
/* 327 */       clone.yFormat = (NumberFormat)this.yFormat.clone(); 
/* 330 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\AbstractXYItemLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */