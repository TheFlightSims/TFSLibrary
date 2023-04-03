/*     */ package org.geotools.measure;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import javax.measure.converter.ConversionException;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ 
/*     */ class SexagesimalConverter extends UnitConverter {
/*     */   private static final long serialVersionUID = 3873494343412121773L;
/*     */   
/*     */   private static final double EPS = 1.0E-8D;
/*     */   
/*  52 */   static final SexagesimalConverter INTEGER = new SexagesimalConverter(1);
/*     */   
/*  57 */   static final SexagesimalConverter FRACTIONAL = new SexagesimalConverter(10000);
/*     */   
/*     */   final int divider;
/*     */   
/*     */   private final UnitConverter inverse;
/*     */   
/*     */   private SexagesimalConverter(int divider) {
/*  79 */     this.divider = divider;
/*  80 */     this.inverse = new Inverse(this);
/*     */   }
/*     */   
/*     */   private SexagesimalConverter(int divider, UnitConverter inverse) {
/*  88 */     this.divider = divider;
/*  89 */     this.inverse = inverse;
/*     */   }
/*     */   
/*     */   public final UnitConverter inverse() {
/*  96 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public double convert(double value) throws ConversionException {
/* 103 */     int deg = (int)value;
/* 104 */     value = (value - deg) * 60.0D;
/* 104 */     int min = (int)value;
/* 105 */     value = (value - min) * 60.0D;
/* 105 */     int sec = (int)value;
/* 106 */     value -= sec;
/* 107 */     return (((deg * 100 + min) * 100 + sec) + value) / this.divider;
/*     */   }
/*     */   
/*     */   public final double derivative(double x) {
/* 114 */     return 1.0D;
/*     */   }
/*     */   
/*     */   public final boolean isLinear() {
/* 121 */     return false;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object object) {
/* 129 */     return (object != null && object.getClass().equals(getClass()) && ((SexagesimalConverter)object).divider == this.divider);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 138 */     return 714113197 + this.divider;
/*     */   }
/*     */   
/*     */   protected Object readResolve() throws ObjectStreamException {
/* 145 */     UnitConverter candidate = INTEGER;
/* 146 */     for (int i = 0; i <= 3; i++) {
/* 147 */       switch (i) {
/*     */         case 0:
/*     */           break;
/*     */         case 2:
/* 149 */           candidate = FRACTIONAL;
/*     */           break;
/*     */         default:
/* 150 */           candidate = candidate.inverse();
/*     */           break;
/*     */       } 
/* 152 */       if (equals(candidate))
/* 153 */         return candidate; 
/*     */     } 
/* 156 */     return this;
/*     */   }
/*     */   
/*     */   private static final class Inverse extends SexagesimalConverter {
/*     */     private static final long serialVersionUID = -7171869900634417819L;
/*     */     
/*     */     public Inverse(SexagesimalConverter inverse) {
/* 172 */       super(inverse.divider, inverse);
/*     */     }
/*     */     
/*     */     public double convert(double value) throws ConversionException {
/* 180 */       value *= this.divider;
/* 182 */       int deg = (int)(value / 10000.0D);
/* 182 */       value -= (10000 * deg);
/* 183 */       int min = (int)(value / 100.0D);
/* 183 */       value -= (100 * min);
/* 184 */       if (min <= -60 || min >= 60)
/* 185 */         if (Math.abs(Math.abs(min) - 100) <= 1.0E-8D) {
/* 186 */           if (min >= 0) {
/* 186 */             deg++;
/*     */           } else {
/* 186 */             deg--;
/*     */           } 
/* 187 */           min = 0;
/*     */         } else {
/* 189 */           throw new ConversionException("Invalid minutes: " + min);
/*     */         }  
/* 192 */       if (value <= -60.0D || value >= 60.0D)
/* 193 */         if (Math.abs(Math.abs(value) - 100.0D) <= 1.0E-8D) {
/* 194 */           if (value >= 0.0D) {
/* 194 */             min++;
/*     */           } else {
/* 194 */             min--;
/*     */           } 
/* 195 */           value = 0.0D;
/*     */         } else {
/* 197 */           throw new ConversionException("Invalid secondes: " + value);
/*     */         }  
/* 200 */       value = (value / 60.0D + min) / 60.0D + deg;
/* 201 */       return value;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 209 */       return -715221659 + this.divider;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\measure\SexagesimalConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */