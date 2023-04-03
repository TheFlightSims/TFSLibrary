/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ContourEntity extends ChartEntity implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 1249570520505992847L;
/*     */   
/*  62 */   private int index = -1;
/*     */   
/*     */   public ContourEntity(Shape area, String toolTipText) {
/*  71 */     super(area, toolTipText);
/*     */   }
/*     */   
/*     */   public ContourEntity(Shape area, String toolTipText, String urlText) {
/*  82 */     super(area, toolTipText, urlText);
/*     */   }
/*     */   
/*     */   public int getIndex() {
/*  91 */     return this.index;
/*     */   }
/*     */   
/*     */   public void setIndex(int index) {
/* 100 */     this.index = index;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 111 */     if (obj == this)
/* 112 */       return true; 
/* 114 */     if (obj instanceof ContourEntity && super.equals(obj)) {
/* 115 */       ContourEntity ce = (ContourEntity)obj;
/* 116 */       if (this.index != ce.index)
/* 117 */         return false; 
/* 119 */       return true;
/*     */     } 
/* 121 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 132 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\entity\ContourEntity.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */