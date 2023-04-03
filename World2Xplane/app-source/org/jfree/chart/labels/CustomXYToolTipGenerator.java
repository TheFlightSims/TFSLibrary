/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class CustomXYToolTipGenerator implements XYToolTipGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 8636030004670141362L;
/*     */   
/*  71 */   private List toolTipSeries = new ArrayList();
/*     */   
/*     */   public int getListCount() {
/*  86 */     return this.toolTipSeries.size();
/*     */   }
/*     */   
/*     */   public int getToolTipCount(int list) {
/*  98 */     int result = 0;
/*  99 */     List tooltips = this.toolTipSeries.get(list);
/* 100 */     if (tooltips != null)
/* 101 */       result = tooltips.size(); 
/* 103 */     return result;
/*     */   }
/*     */   
/*     */   public String getToolTipText(int series, int item) {
/* 116 */     String result = null;
/* 118 */     if (series < getListCount()) {
/* 119 */       List tooltips = this.toolTipSeries.get(series);
/* 120 */       if (tooltips != null && 
/* 121 */         item < tooltips.size())
/* 122 */         result = tooltips.get(item); 
/*     */     } 
/* 127 */     return result;
/*     */   }
/*     */   
/*     */   public void addToolTipSeries(List toolTips) {
/* 136 */     this.toolTipSeries.add(toolTips);
/*     */   }
/*     */   
/*     */   public String generateToolTip(XYDataset data, int series, int item) {
/* 150 */     return getToolTipText(series, item);
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 163 */     CustomXYToolTipGenerator clone = (CustomXYToolTipGenerator)super.clone();
/* 165 */     if (this.toolTipSeries != null)
/* 166 */       clone.toolTipSeries = new ArrayList(this.toolTipSeries); 
/* 168 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 180 */     if (obj == this)
/* 181 */       return true; 
/* 184 */     if (obj instanceof CustomXYToolTipGenerator) {
/* 185 */       CustomXYToolTipGenerator generator = (CustomXYToolTipGenerator)obj;
/* 186 */       boolean result = true;
/* 187 */       for (int series = 0; series < getListCount(); series++) {
/* 188 */         for (int item = 0; item < getToolTipCount(series); item++) {
/* 189 */           String t1 = getToolTipText(series, item);
/* 190 */           String t2 = generator.getToolTipText(series, item);
/* 191 */           if (t1 != null) {
/* 192 */             result = (result && t1.equals(t2));
/*     */           } else {
/* 195 */             result = (result && t2 == null);
/*     */           } 
/*     */         } 
/*     */       } 
/* 199 */       return result;
/*     */     } 
/* 202 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\CustomXYToolTipGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */