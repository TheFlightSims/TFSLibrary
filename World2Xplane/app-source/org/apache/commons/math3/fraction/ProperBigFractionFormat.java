/*     */ package org.apache.commons.math3.fraction;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class ProperBigFractionFormat extends BigFractionFormat {
/*     */   private static final long serialVersionUID = -6337346779577272307L;
/*     */   
/*     */   private NumberFormat wholeFormat;
/*     */   
/*     */   public ProperBigFractionFormat() {
/*  51 */     this(getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public ProperBigFractionFormat(NumberFormat format) {
/*  61 */     this(format, (NumberFormat)format.clone(), (NumberFormat)format.clone());
/*     */   }
/*     */   
/*     */   public ProperBigFractionFormat(NumberFormat wholeFormat, NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
/*  74 */     super(numeratorFormat, denominatorFormat);
/*  75 */     setWholeFormat(wholeFormat);
/*     */   }
/*     */   
/*     */   public StringBuffer format(BigFraction fraction, StringBuffer toAppendTo, FieldPosition pos) {
/*  92 */     pos.setBeginIndex(0);
/*  93 */     pos.setEndIndex(0);
/*  95 */     BigInteger num = fraction.getNumerator();
/*  96 */     BigInteger den = fraction.getDenominator();
/*  97 */     BigInteger whole = num.divide(den);
/*  98 */     num = num.remainder(den);
/* 100 */     if (!BigInteger.ZERO.equals(whole)) {
/* 101 */       getWholeFormat().format(whole, toAppendTo, pos);
/* 102 */       toAppendTo.append(' ');
/* 103 */       if (num.compareTo(BigInteger.ZERO) < 0)
/* 104 */         num = num.negate(); 
/*     */     } 
/* 107 */     getNumeratorFormat().format(num, toAppendTo, pos);
/* 108 */     toAppendTo.append(" / ");
/* 109 */     getDenominatorFormat().format(den, toAppendTo, pos);
/* 111 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   public NumberFormat getWholeFormat() {
/* 119 */     return this.wholeFormat;
/*     */   }
/*     */   
/*     */   public BigFraction parse(String source, ParsePosition pos) {
/* 137 */     BigFraction ret = super.parse(source, pos);
/* 138 */     if (ret != null)
/* 139 */       return ret; 
/* 142 */     int initialIndex = pos.getIndex();
/* 145 */     parseAndIgnoreWhitespace(source, pos);
/* 148 */     BigInteger whole = parseNextBigInteger(source, pos);
/* 149 */     if (whole == null) {
/* 153 */       pos.setIndex(initialIndex);
/* 154 */       return null;
/*     */     } 
/* 158 */     parseAndIgnoreWhitespace(source, pos);
/* 161 */     BigInteger num = parseNextBigInteger(source, pos);
/* 162 */     if (num == null) {
/* 166 */       pos.setIndex(initialIndex);
/* 167 */       return null;
/*     */     } 
/* 170 */     if (num.compareTo(BigInteger.ZERO) < 0) {
/* 172 */       pos.setIndex(initialIndex);
/* 173 */       return null;
/*     */     } 
/* 177 */     int startIndex = pos.getIndex();
/* 178 */     char c = parseNextCharacter(source, pos);
/* 179 */     switch (c) {
/*     */       case '\000':
/* 183 */         return new BigFraction(num);
/*     */       case '/':
/*     */         break;
/*     */       default:
/* 191 */         pos.setIndex(initialIndex);
/* 192 */         pos.setErrorIndex(startIndex);
/* 193 */         return null;
/*     */     } 
/* 197 */     parseAndIgnoreWhitespace(source, pos);
/* 200 */     BigInteger den = parseNextBigInteger(source, pos);
/* 201 */     if (den == null) {
/* 205 */       pos.setIndex(initialIndex);
/* 206 */       return null;
/*     */     } 
/* 209 */     if (den.compareTo(BigInteger.ZERO) < 0) {
/* 211 */       pos.setIndex(initialIndex);
/* 212 */       return null;
/*     */     } 
/* 215 */     boolean wholeIsNeg = (whole.compareTo(BigInteger.ZERO) < 0);
/* 216 */     if (wholeIsNeg)
/* 217 */       whole = whole.negate(); 
/* 219 */     num = whole.multiply(den).add(num);
/* 220 */     if (wholeIsNeg)
/* 221 */       num = num.negate(); 
/* 224 */     return new BigFraction(num, den);
/*     */   }
/*     */   
/*     */   public void setWholeFormat(NumberFormat format) {
/* 234 */     if (format == null)
/* 235 */       throw new NullArgumentException(LocalizedFormats.WHOLE_FORMAT, new Object[0]); 
/* 237 */     this.wholeFormat = format;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\fraction\ProperBigFractionFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */