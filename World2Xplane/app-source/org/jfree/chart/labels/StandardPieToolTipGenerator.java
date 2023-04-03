/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StandardPieToolTipGenerator extends AbstractPieItemLabelGenerator implements PieToolTipGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 2995304200445733779L;
/*     */   
/*     */   public static final String DEFAULT_TOOLTIP_FORMAT = "{0}: ({1}, {2})";
/*     */   
/*     */   public static final String DEFAULT_SECTION_LABEL_FORMAT = "{0} = {1}";
/*     */   
/*     */   public StandardPieToolTipGenerator() {
/*  95 */     this("{0} = {1}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
/*     */   }
/*     */   
/*     */   public StandardPieToolTipGenerator(String labelFormat) {
/* 107 */     this(labelFormat, NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
/*     */   }
/*     */   
/*     */   public StandardPieToolTipGenerator(String labelFormat, NumberFormat numberFormat, NumberFormat percentFormat) {
/* 127 */     super(labelFormat, numberFormat, percentFormat);
/*     */   }
/*     */   
/*     */   public String generateToolTip(PieDataset dataset, Comparable key) {
/* 140 */     return generateSectionLabel(dataset, key);
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 151 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\StandardPieToolTipGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */