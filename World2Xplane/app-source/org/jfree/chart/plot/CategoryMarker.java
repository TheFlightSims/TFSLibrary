/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.ui.LengthAdjustmentType;
/*     */ 
/*     */ public class CategoryMarker extends Marker implements Cloneable, Serializable {
/*     */   private Comparable key;
/*     */   
/*     */   private boolean drawAsLine = false;
/*     */   
/*     */   public CategoryMarker(Comparable key) {
/*  75 */     this(key, Color.gray, new BasicStroke(1.0F));
/*     */   }
/*     */   
/*     */   public CategoryMarker(Comparable key, Paint paint, Stroke stroke) {
/*  86 */     this(key, paint, stroke, paint, stroke, 0.5F);
/*     */   }
/*     */   
/*     */   public CategoryMarker(Comparable key, Paint paint, Stroke stroke, Paint outlinePaint, Stroke outlineStroke, float alpha) {
/* 102 */     super(paint, stroke, outlinePaint, outlineStroke, alpha);
/* 103 */     this.key = key;
/* 104 */     setLabelOffsetType(LengthAdjustmentType.EXPAND);
/*     */   }
/*     */   
/*     */   public Comparable getKey() {
/* 113 */     return this.key;
/*     */   }
/*     */   
/*     */   public boolean getDrawAsLine() {
/* 123 */     return this.drawAsLine;
/*     */   }
/*     */   
/*     */   public void setDrawAsLine(boolean drawAsLine) {
/* 133 */     this.drawAsLine = drawAsLine;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 144 */     if (obj == null)
/* 145 */       return false; 
/* 147 */     if (!(obj instanceof CategoryMarker))
/* 148 */       return false; 
/* 150 */     if (!super.equals(obj))
/* 151 */       return false; 
/* 153 */     CategoryMarker that = (CategoryMarker)obj;
/* 154 */     if (!this.key.equals(that.key))
/* 155 */       return false; 
/* 157 */     if (this.drawAsLine != that.drawAsLine)
/* 158 */       return false; 
/* 160 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\CategoryMarker.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */