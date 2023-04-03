/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.temporal.IntervalLength;
/*     */ 
/*     */ public class DefaultIntervalLength extends DefaultDuration implements IntervalLength {
/*     */   private Unit unit;
/*     */   
/*     */   private int radix;
/*     */   
/*     */   private int factor;
/*     */   
/*     */   private int value;
/*     */   
/*     */   public DefaultIntervalLength(Unit unit, int radix, int factor, int value) {
/*  60 */     this.unit = unit;
/*  61 */     this.radix = radix;
/*  62 */     this.factor = factor;
/*  63 */     this.value = value;
/*     */   }
/*     */   
/*     */   public Unit getUnit() {
/*  70 */     return this.unit;
/*     */   }
/*     */   
/*     */   public int getRadix() {
/*  77 */     return this.radix;
/*     */   }
/*     */   
/*     */   public int getFactor() {
/*  84 */     return this.factor;
/*     */   }
/*     */   
/*     */   public int getValue() {
/*  88 */     return this.value;
/*     */   }
/*     */   
/*     */   public long getTimeInMillis() {
/*  93 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  98 */     if (object == this)
/*  99 */       return true; 
/* 101 */     if (object instanceof DefaultIntervalLength) {
/* 102 */       DefaultIntervalLength that = (DefaultIntervalLength)object;
/* 104 */       return (Utilities.equals(this.factor, that.factor) && Utilities.equals(this.radix, that.radix) && Utilities.equals(this.unit, that.unit) && Utilities.equals(Integer.valueOf(this.value), that.unit));
/*     */     } 
/* 109 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 114 */     int hash = 5;
/* 115 */     hash = 37 * hash + ((this.unit != null) ? this.unit.hashCode() : 0);
/* 116 */     hash = 37 * hash + this.factor;
/* 117 */     hash = 37 * hash + this.radix;
/* 118 */     hash = 37 * hash + this.value;
/* 119 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 124 */     StringBuilder s = (new StringBuilder("IntervalLength:")).append('\n');
/* 125 */     if (this.unit != null)
/* 126 */       s.append("unit:").append(this.unit).append('\n'); 
/* 128 */     s.append("radix:").append(this.radix).append('\n');
/* 129 */     s.append("factor:").append(this.factor).append('\n');
/* 130 */     s.append("value:").append(this.value).append('\n');
/* 132 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultIntervalLength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */