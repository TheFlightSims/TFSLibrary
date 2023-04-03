/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ 
/*     */ public class ValueMarker extends Marker {
/*     */   private double value;
/*     */   
/*     */   public ValueMarker(double value) {
/*  63 */     this.value = value;
/*     */   }
/*     */   
/*     */   public ValueMarker(double value, Paint paint, Stroke stroke) {
/*  74 */     this(value, paint, stroke, paint, stroke, 1.0F);
/*     */   }
/*     */   
/*     */   public ValueMarker(double value, Paint paint, Stroke stroke, Paint outlinePaint, Stroke outlineStroke, float alpha) {
/*  89 */     super(paint, stroke, paint, stroke, alpha);
/*  90 */     this.value = value;
/*     */   }
/*     */   
/*     */   public double getValue() {
/*  99 */     return this.value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 110 */     if (obj == this)
/* 111 */       return true; 
/* 113 */     if (!super.equals(obj))
/* 114 */       return false; 
/* 116 */     if (!(obj instanceof ValueMarker))
/* 117 */       return false; 
/* 119 */     ValueMarker that = (ValueMarker)obj;
/* 120 */     if (this.value != that.value)
/* 121 */       return false; 
/* 123 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\ValueMarker.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */