/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.xy.OHLCDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class HighLowItemLabelGenerator implements XYItemLabelGenerator, XYToolTipGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 5617111754832211830L;
/*     */   
/*     */   private DateFormat dateFormatter;
/*     */   
/*     */   private NumberFormat numberFormatter;
/*     */   
/*     */   public HighLowItemLabelGenerator() {
/*  89 */     this(DateFormat.getInstance(), NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */   public HighLowItemLabelGenerator(DateFormat dateFormatter, NumberFormat numberFormatter) {
/* 102 */     if (dateFormatter == null)
/* 103 */       throw new IllegalArgumentException("Null 'dateFormatter' argument."); 
/* 107 */     if (numberFormatter == null)
/* 108 */       throw new IllegalArgumentException("Null 'numberFormatter' argument."); 
/* 112 */     this.dateFormatter = dateFormatter;
/* 113 */     this.numberFormatter = numberFormatter;
/*     */   }
/*     */   
/*     */   public String generateToolTip(XYDataset dataset, int series, int item) {
/* 127 */     String result = null;
/* 129 */     if (dataset instanceof OHLCDataset) {
/* 130 */       OHLCDataset d = (OHLCDataset)dataset;
/* 131 */       Number high = d.getHigh(series, item);
/* 132 */       Number low = d.getLow(series, item);
/* 133 */       Number open = d.getOpen(series, item);
/* 134 */       Number close = d.getClose(series, item);
/* 135 */       Number x = d.getX(series, item);
/* 137 */       result = d.getSeriesKey(series).toString();
/* 139 */       if (x != null) {
/* 140 */         Date date = new Date(x.longValue());
/* 141 */         result = result + "--> Date=" + this.dateFormatter.format(date);
/* 142 */         if (high != null)
/* 143 */           result = result + " High=" + this.numberFormatter.format(high.doubleValue()); 
/* 146 */         if (low != null)
/* 147 */           result = result + " Low=" + this.numberFormatter.format(low.doubleValue()); 
/* 150 */         if (open != null)
/* 151 */           result = result + " Open=" + this.numberFormatter.format(open.doubleValue()); 
/* 154 */         if (close != null)
/* 155 */           result = result + " Close=" + this.numberFormatter.format(close.doubleValue()); 
/*     */       } 
/*     */     } 
/* 162 */     return result;
/*     */   }
/*     */   
/*     */   public String generateLabel(XYDataset dataset, int series, int category) {
/* 177 */     return null;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 189 */     HighLowItemLabelGenerator clone = (HighLowItemLabelGenerator)super.clone();
/* 192 */     if (this.dateFormatter != null)
/* 193 */       clone.dateFormatter = (DateFormat)this.dateFormatter.clone(); 
/* 195 */     if (this.numberFormatter != null)
/* 196 */       clone.numberFormatter = (NumberFormat)this.numberFormatter.clone(); 
/* 199 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 211 */     if (obj == this)
/* 212 */       return true; 
/* 214 */     if (!(obj instanceof HighLowItemLabelGenerator))
/* 215 */       return false; 
/* 217 */     HighLowItemLabelGenerator generator = (HighLowItemLabelGenerator)obj;
/* 218 */     if (!this.dateFormatter.equals(generator.dateFormatter))
/* 219 */       return false; 
/* 221 */     if (!this.numberFormatter.equals(generator.numberFormatter))
/* 222 */       return false; 
/* 224 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\HighLowItemLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */