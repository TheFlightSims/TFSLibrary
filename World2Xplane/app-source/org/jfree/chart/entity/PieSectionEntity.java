/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ 
/*     */ public class PieSectionEntity extends ChartEntity implements Serializable {
/*     */   private static final long serialVersionUID = 9199892576531984162L;
/*     */   
/*     */   private PieDataset dataset;
/*     */   
/*     */   private int pieIndex;
/*     */   
/*     */   private int sectionIndex;
/*     */   
/*     */   private Comparable sectionKey;
/*     */   
/*     */   public PieSectionEntity(Shape area, PieDataset dataset, int pieIndex, int sectionIndex, Comparable sectionKey, String toolTipText, String urlText) {
/* 101 */     super(area, toolTipText, urlText);
/* 102 */     this.dataset = dataset;
/* 103 */     this.pieIndex = pieIndex;
/* 104 */     this.sectionIndex = sectionIndex;
/* 105 */     this.sectionKey = sectionKey;
/*     */   }
/*     */   
/*     */   public PieDataset getDataset() {
/* 115 */     return this.dataset;
/*     */   }
/*     */   
/*     */   public void setDataset(PieDataset dataset) {
/* 124 */     this.dataset = dataset;
/*     */   }
/*     */   
/*     */   public int getPieIndex() {
/* 135 */     return this.pieIndex;
/*     */   }
/*     */   
/*     */   public void setPieIndex(int index) {
/* 144 */     this.pieIndex = index;
/*     */   }
/*     */   
/*     */   public int getSectionIndex() {
/* 153 */     return this.sectionIndex;
/*     */   }
/*     */   
/*     */   public void setSectionIndex(int index) {
/* 162 */     this.sectionIndex = index;
/*     */   }
/*     */   
/*     */   public Comparable getSectionKey() {
/* 171 */     return this.sectionKey;
/*     */   }
/*     */   
/*     */   public void setSectionKey(Comparable key) {
/* 180 */     this.sectionKey = key;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 189 */     return "PieSection: " + this.pieIndex + ", " + this.sectionIndex + "(" + this.sectionKey.toString() + ")";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\entity\PieSectionEntity.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */