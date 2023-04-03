/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XYZDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class StandardXYZToolTipGenerator extends StandardXYToolTipGenerator implements XYZToolTipGenerator, Serializable {
/*     */   private static final long serialVersionUID = -2961577421889473503L;
/*     */   
/*     */   public static final String DEFAULT_TOOL_TIP_FORMAT = "{0}: ({1}, {2}, {3})";
/*     */   
/*     */   private NumberFormat zFormat;
/*     */   
/*     */   private DateFormat zDateFormat;
/*     */   
/*     */   public StandardXYZToolTipGenerator() {
/*  86 */     this("{0}: ({1}, {2}, {3})", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
/*     */   }
/*     */   
/*     */   public StandardXYZToolTipGenerator(String formatString, NumberFormat xFormat, NumberFormat yFormat, NumberFormat zFormat) {
/* 110 */     super(formatString, xFormat, yFormat);
/* 111 */     if (zFormat == null)
/* 112 */       throw new IllegalArgumentException("Null 'zFormat' argument."); 
/* 114 */     this.zFormat = zFormat;
/*     */   }
/*     */   
/*     */   public StandardXYZToolTipGenerator(String formatString, DateFormat xFormat, DateFormat yFormat, DateFormat zFormat) {
/* 132 */     super(formatString, xFormat, yFormat);
/* 133 */     if (zFormat == null)
/* 134 */       throw new IllegalArgumentException("Null 'zFormat' argument."); 
/* 136 */     this.zDateFormat = zFormat;
/*     */   }
/*     */   
/*     */   public NumberFormat getZFormat() {
/* 147 */     return this.zFormat;
/*     */   }
/*     */   
/*     */   public DateFormat getZDateFormat() {
/* 156 */     return this.zDateFormat;
/*     */   }
/*     */   
/*     */   public String generateToolTip(XYZDataset dataset, int series, int item) {
/* 169 */     return generateLabelString((XYDataset)dataset, series, item);
/*     */   }
/*     */   
/*     */   public String generateLabelString(XYDataset dataset, int series, int item) {
/* 182 */     String result = null;
/* 183 */     Object[] items = createItemArray((XYZDataset)dataset, series, item);
/* 184 */     result = MessageFormat.format(getFormatString(), items);
/* 185 */     return result;
/*     */   }
/*     */   
/*     */   protected Object[] createItemArray(XYZDataset dataset, int series, int item) {
/* 201 */     Object[] result = new Object[4];
/* 202 */     result[0] = dataset.getSeriesKey(series).toString();
/* 204 */     Number x = dataset.getX(series, item);
/* 205 */     DateFormat xf = getXDateFormat();
/* 206 */     if (xf != null) {
/* 207 */       result[1] = xf.format(x);
/*     */     } else {
/* 210 */       result[1] = getXFormat().format(x);
/*     */     } 
/* 213 */     Number y = dataset.getY(series, item);
/* 214 */     DateFormat yf = getYDateFormat();
/* 215 */     if (yf != null) {
/* 216 */       result[2] = yf.format(y);
/*     */     } else {
/* 219 */       result[2] = getYFormat().format(y);
/*     */     } 
/* 222 */     Number z = dataset.getZ(series, item);
/* 223 */     if (this.zDateFormat != null) {
/* 224 */       result[3] = this.zDateFormat.format(z);
/*     */     } else {
/* 227 */       result[3] = this.zFormat.format(z);
/*     */     } 
/* 230 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 242 */     if (obj == this)
/* 243 */       return true; 
/* 245 */     if (!(obj instanceof StandardXYZToolTipGenerator))
/* 246 */       return false; 
/* 248 */     if (!super.equals(obj))
/* 249 */       return false; 
/* 251 */     StandardXYZToolTipGenerator that = (StandardXYZToolTipGenerator)obj;
/* 252 */     if (!ObjectUtilities.equal(this.zFormat, that.zFormat))
/* 253 */       return false; 
/* 255 */     if (!ObjectUtilities.equal(this.zDateFormat, that.zDateFormat))
/* 256 */       return false; 
/* 258 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\StandardXYZToolTipGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */