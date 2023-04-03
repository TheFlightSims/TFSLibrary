/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.contour.ContourDataset;
/*     */ 
/*     */ public class StandardContourToolTipGenerator implements ContourToolTipGenerator, Serializable {
/*     */   private static final long serialVersionUID = -1881659351247502711L;
/*     */   
/*  68 */   private DecimalFormat valueForm = new DecimalFormat("##.###");
/*     */   
/*     */   public String generateToolTip(ContourDataset data, int item) {
/*  80 */     double x = data.getXValue(0, item);
/*  81 */     double y = data.getYValue(0, item);
/*  82 */     double z = data.getZValue(0, item);
/*  83 */     String xString = null;
/*  85 */     if (data.isDateAxis(0)) {
/*  86 */       SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
/*  88 */       StringBuffer strbuf = new StringBuffer();
/*  89 */       strbuf = formatter.format(new Date((long)x), strbuf, new FieldPosition(0));
/*  92 */       xString = strbuf.toString();
/*     */     } else {
/*  95 */       xString = this.valueForm.format(x);
/*     */     } 
/*  97 */     if (!Double.isNaN(z))
/*  98 */       return "X: " + xString + ", Y: " + this.valueForm.format(y) + ", Z: " + this.valueForm.format(z); 
/* 103 */     return "X: " + xString + ", Y: " + this.valueForm.format(y) + ", Z: no data";
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 119 */     if (obj == this)
/* 120 */       return true; 
/* 123 */     if (!(obj instanceof StandardContourToolTipGenerator))
/* 124 */       return false; 
/* 126 */     StandardContourToolTipGenerator that = (StandardContourToolTipGenerator)obj;
/* 128 */     if (this.valueForm != null)
/* 129 */       return this.valueForm.equals(that.valueForm); 
/* 131 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\StandardContourToolTipGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */