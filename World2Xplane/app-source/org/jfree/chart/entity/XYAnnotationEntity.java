/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class XYAnnotationEntity extends ChartEntity implements Serializable {
/*     */   private static final long serialVersionUID = 2340334068383660799L;
/*     */   
/*     */   private int rendererIndex;
/*     */   
/*     */   public XYAnnotationEntity(Shape hotspot, int rendererIndex, String toolTipText, String urlText) {
/*  71 */     super(hotspot, toolTipText, urlText);
/*  72 */     this.rendererIndex = rendererIndex;
/*     */   }
/*     */   
/*     */   public int getRendererIndex() {
/*  81 */     return this.rendererIndex;
/*     */   }
/*     */   
/*     */   public void setRendererIndex(int index) {
/*  90 */     this.rendererIndex = index;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 101 */     if (obj == this)
/* 102 */       return true; 
/* 104 */     if (!super.equals(obj))
/* 105 */       return false; 
/* 107 */     if (!(obj instanceof XYAnnotationEntity))
/* 108 */       return false; 
/* 110 */     XYAnnotationEntity that = (XYAnnotationEntity)obj;
/* 111 */     if (this.rendererIndex != that.rendererIndex)
/* 112 */       return false; 
/* 114 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\entity\XYAnnotationEntity.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */