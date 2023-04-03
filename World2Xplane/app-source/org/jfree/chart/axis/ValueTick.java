/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ public abstract class ValueTick extends Tick {
/*     */   private double value;
/*     */   
/*     */   public ValueTick(double value, String label, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle) {
/*  68 */     super(label, textAnchor, rotationAnchor, angle);
/*  69 */     this.value = value;
/*     */   }
/*     */   
/*     */   public double getValue() {
/*  79 */     return this.value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  90 */     if (obj == this)
/*  91 */       return true; 
/*  93 */     if (obj instanceof ValueTick && super.equals(obj)) {
/*  94 */       ValueTick vt = (ValueTick)obj;
/*  95 */       if (this.value != vt.value)
/*  96 */         return false; 
/*  98 */       return true;
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\ValueTick.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */