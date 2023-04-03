/*     */ package javax.measure.converter;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class UnitConverter implements Serializable {
/*  33 */   public static final UnitConverter IDENTITY = new Identity();
/*     */   
/*     */   public abstract UnitConverter inverse();
/*     */   
/*     */   public abstract double convert(double paramDouble) throws ConversionException;
/*     */   
/*     */   public abstract boolean isLinear();
/*     */   
/*     */   public boolean equals(Object cvtr) {
/*  83 */     if (!(cvtr instanceof UnitConverter))
/*  83 */       return false; 
/*  84 */     return (concatenate(((UnitConverter)cvtr).inverse()) == IDENTITY);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  95 */     return Float.floatToIntBits((float)convert(1.0D));
/*     */   }
/*     */   
/*     */   public UnitConverter concatenate(UnitConverter converter) {
/* 111 */     return (converter == IDENTITY) ? this : new Compound(converter, this);
/*     */   }
/*     */   
/*     */   private static final class Identity extends UnitConverter {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private Identity() {}
/*     */     
/*     */     public UnitConverter inverse() {
/* 121 */       return this;
/*     */     }
/*     */     
/*     */     public double convert(double x) {
/* 126 */       return x;
/*     */     }
/*     */     
/*     */     public boolean isLinear() {
/* 131 */       return true;
/*     */     }
/*     */     
/*     */     public UnitConverter concatenate(UnitConverter converter) {
/* 136 */       return converter;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Compound extends UnitConverter {
/*     */     private final UnitConverter _first;
/*     */     
/*     */     private final UnitConverter _second;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private Compound(UnitConverter first, UnitConverter second) {
/* 166 */       this._first = first;
/* 167 */       this._second = second;
/*     */     }
/*     */     
/*     */     public UnitConverter inverse() {
/* 172 */       return new Compound(this._second.inverse(), this._first.inverse());
/*     */     }
/*     */     
/*     */     public double convert(double x) {
/* 177 */       return this._second.convert(this._first.convert(x));
/*     */     }
/*     */     
/*     */     public boolean isLinear() {
/* 182 */       return (this._first.isLinear() && this._second.isLinear());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\converter\UnitConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */