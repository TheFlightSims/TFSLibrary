/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.AttributedString;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.util.ObjectList;
/*     */ 
/*     */ public class StandardPieSectionLabelGenerator extends AbstractPieItemLabelGenerator implements PieSectionLabelGenerator, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 3064190563760203668L;
/*     */   
/*     */   public static final String DEFAULT_SECTION_LABEL_FORMAT = "{0} = {1}";
/*     */   
/*     */   private ObjectList attributedLabels;
/*     */   
/*     */   public StandardPieSectionLabelGenerator() {
/*  84 */     this("{0} = {1}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
/*     */   }
/*     */   
/*     */   public StandardPieSectionLabelGenerator(String labelFormat) {
/*  94 */     this(labelFormat, NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
/*     */   }
/*     */   
/*     */   public StandardPieSectionLabelGenerator(String labelFormat, NumberFormat numberFormat, NumberFormat percentFormat) {
/* 112 */     super(labelFormat, numberFormat, percentFormat);
/* 113 */     this.attributedLabels = new ObjectList();
/*     */   }
/*     */   
/*     */   public AttributedString getAttributedLabel(int section) {
/* 126 */     return (AttributedString)this.attributedLabels.get(section);
/*     */   }
/*     */   
/*     */   public void setAttributedLabel(int section, AttributedString label) {
/* 136 */     this.attributedLabels.set(section, label);
/*     */   }
/*     */   
/*     */   public String generateSectionLabel(PieDataset dataset, Comparable key) {
/* 148 */     return super.generateSectionLabel(dataset, key);
/*     */   }
/*     */   
/*     */   public AttributedString generateAttributedSectionLabel(PieDataset dataset, Comparable key) {
/* 180 */     return getAttributedLabel(dataset.getIndex(key));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 191 */     if (obj == this)
/* 192 */       return true; 
/* 194 */     if (!(obj instanceof StandardPieSectionLabelGenerator))
/* 195 */       return false; 
/* 197 */     if (!super.equals(obj))
/* 198 */       return false; 
/* 200 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 211 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\StandardPieSectionLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */