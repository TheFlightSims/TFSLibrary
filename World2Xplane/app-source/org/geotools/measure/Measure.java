/*     */ package org.geotools.measure;
/*     */ 
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.util.Utilities;
/*     */ 
/*     */ public final class Measure extends Number {
/*     */   private static final long serialVersionUID = 6917234039472328164L;
/*     */   
/*     */   private final double value;
/*     */   
/*     */   private final Unit<?> unit;
/*     */   
/*     */   public Measure(double value, Unit<?> unit) {
/*  56 */     this.value = value;
/*  57 */     this.unit = unit;
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/*  60 */     return this.value;
/*     */   }
/*     */   
/*     */   public float floatValue() {
/*  61 */     return (float)this.value;
/*     */   }
/*     */   
/*     */   public long longValue() {
/*  62 */     return (long)this.value;
/*     */   }
/*     */   
/*     */   public int intValue() {
/*  63 */     return (int)this.value;
/*     */   }
/*     */   
/*     */   public short shortValue() {
/*  64 */     return (short)(int)this.value;
/*     */   }
/*     */   
/*     */   public byte byteValue() {
/*  65 */     return (byte)(int)this.value;
/*     */   }
/*     */   
/*     */   public Unit<?> getUnit() {
/*  73 */     return this.unit;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  81 */     long code = Double.doubleToLongBits(this.value);
/*  82 */     return (int)code ^ (int)(code >>> 32L) ^ this.unit.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  93 */     if (object instanceof Measure) {
/*  94 */       Measure that = (Measure)object;
/*  95 */       return (Utilities.equals(this.value, that.value) && Utilities.equals(this.unit, that.unit));
/*     */     } 
/*  98 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 106 */     StringBuilder buffer = new StringBuilder();
/* 107 */     buffer.append(this.value);
/* 108 */     buffer.append(' ');
/* 109 */     buffer.append(this.unit);
/* 110 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\measure\Measure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */