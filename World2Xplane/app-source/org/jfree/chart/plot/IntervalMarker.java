/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.LengthAdjustmentType;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class IntervalMarker extends Marker implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -1762344775267627916L;
/*     */   
/*     */   private double startValue;
/*     */   
/*     */   private double endValue;
/*     */   
/*     */   private GradientPaintTransformer gradientPaintTransformer;
/*     */   
/*     */   public IntervalMarker(double start, double end) {
/*  81 */     this(start, end, Color.gray, new BasicStroke(0.5F), Color.blue, new BasicStroke(0.5F), 0.8F);
/*     */   }
/*     */   
/*     */   public IntervalMarker(double start, double end, Paint paint, Stroke stroke, Paint outlinePaint, Stroke outlineStroke, float alpha) {
/* 103 */     super(paint, stroke, outlinePaint, outlineStroke, alpha);
/* 104 */     this.startValue = start;
/* 105 */     this.endValue = end;
/* 106 */     this.gradientPaintTransformer = null;
/* 107 */     setLabelOffsetType(LengthAdjustmentType.CONTRACT);
/*     */   }
/*     */   
/*     */   public double getStartValue() {
/* 117 */     return this.startValue;
/*     */   }
/*     */   
/*     */   public double getEndValue() {
/* 126 */     return this.endValue;
/*     */   }
/*     */   
/*     */   public GradientPaintTransformer getGradientPaintTransformer() {
/* 135 */     return this.gradientPaintTransformer;
/*     */   }
/*     */   
/*     */   public void setGradientPaintTransformer(GradientPaintTransformer transformer) {
/* 145 */     this.gradientPaintTransformer = transformer;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 156 */     if (obj == this)
/* 157 */       return true; 
/* 159 */     if (!(obj instanceof IntervalMarker))
/* 160 */       return false; 
/* 162 */     if (!super.equals(obj))
/* 163 */       return false; 
/* 165 */     IntervalMarker that = (IntervalMarker)obj;
/* 166 */     if (this.startValue != that.startValue)
/* 167 */       return false; 
/* 169 */     if (this.endValue != that.endValue)
/* 170 */       return false; 
/* 172 */     if (!ObjectUtilities.equal(this.gradientPaintTransformer, that.gradientPaintTransformer))
/* 174 */       return false; 
/* 176 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 188 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\IntervalMarker.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */