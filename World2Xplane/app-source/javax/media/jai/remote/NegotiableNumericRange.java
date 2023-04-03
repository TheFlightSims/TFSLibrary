/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import javax.media.jai.util.Range;
/*     */ 
/*     */ public class NegotiableNumericRange implements Negotiable {
/*     */   private Range range;
/*     */   
/*     */   public NegotiableNumericRange(Range range) {
/*  41 */     if (range == null)
/*  42 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableNumericRange0")); 
/*  48 */     if (!Number.class.isAssignableFrom(range.getElementClass()))
/*  49 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableNumericRange1")); 
/*  53 */     this.range = range;
/*     */   }
/*     */   
/*     */   public Range getRange() {
/*  61 */     if (this.range.isEmpty())
/*  62 */       return null; 
/*  63 */     return this.range;
/*     */   }
/*     */   
/*     */   public Negotiable negotiate(Negotiable other) {
/*  79 */     if (other == null)
/*  80 */       return null; 
/*  84 */     if (!(other instanceof NegotiableNumericRange))
/*  85 */       return null; 
/*  87 */     NegotiableNumericRange otherNNRange = (NegotiableNumericRange)other;
/*  88 */     Range otherRange = otherNNRange.getRange();
/*  92 */     if (otherRange == null)
/*  93 */       return null; 
/*  96 */     if (otherRange.getElementClass() != this.range.getElementClass())
/*  97 */       return null; 
/*  99 */     Range result = this.range.intersect(otherRange);
/* 102 */     if (result.isEmpty())
/* 103 */       return null; 
/* 105 */     return new NegotiableNumericRange(result);
/*     */   }
/*     */   
/*     */   public Object getNegotiatedValue() {
/* 121 */     if (this.range.isEmpty())
/* 122 */       return null; 
/* 124 */     Number minValue = (Number)this.range.getMinValue();
/* 126 */     if (minValue == null) {
/* 127 */       Number maxValue = (Number)this.range.getMaxValue();
/* 129 */       if (maxValue == null) {
/* 131 */         Class elementClass = this.range.getElementClass();
/* 134 */         if (elementClass == Byte.class)
/* 135 */           return new Byte((byte)0); 
/* 136 */         if (elementClass == Short.class)
/* 137 */           return new Short((short)0); 
/* 138 */         if (elementClass == Integer.class)
/* 139 */           return new Integer(0); 
/* 140 */         if (elementClass == Long.class)
/* 141 */           return new Long(0L); 
/* 142 */         if (elementClass == Float.class)
/* 143 */           return new Float(0.0F); 
/* 144 */         if (elementClass == Double.class)
/* 145 */           return new Double(0.0D); 
/* 146 */         if (elementClass == BigInteger.class)
/* 147 */           return BigInteger.ZERO; 
/* 148 */         if (elementClass == BigDecimal.class)
/* 149 */           return new BigDecimal(BigInteger.ZERO); 
/*     */       } else {
/* 152 */         return maxValue;
/*     */       } 
/*     */     } 
/* 156 */     return minValue;
/*     */   }
/*     */   
/*     */   public Class getNegotiatedValueClass() {
/* 164 */     return this.range.getElementClass();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\NegotiableNumericRange.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */