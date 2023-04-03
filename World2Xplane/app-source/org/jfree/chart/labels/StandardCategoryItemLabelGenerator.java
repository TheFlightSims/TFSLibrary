/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StandardCategoryItemLabelGenerator extends AbstractCategoryItemLabelGenerator implements CategoryItemLabelGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 3499701401211412882L;
/*     */   
/*     */   public static final String DEFAULT_LABEL_FORMAT_STRING = "{2}";
/*     */   
/*     */   public StandardCategoryItemLabelGenerator() {
/*  72 */     super("{2}", NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */   public StandardCategoryItemLabelGenerator(String labelFormat, NumberFormat formatter) {
/*  84 */     super(labelFormat, formatter);
/*     */   }
/*     */   
/*     */   public StandardCategoryItemLabelGenerator(String labelFormat, DateFormat formatter) {
/*  96 */     super(labelFormat, formatter);
/*     */   }
/*     */   
/*     */   public String generateLabel(CategoryDataset dataset, int row, int column) {
/* 111 */     return generateLabelString(dataset, row, column);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\StandardCategoryItemLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */