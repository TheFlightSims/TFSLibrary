/*     */ package org.apache.commons.math3.fraction;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class ProperFractionFormat extends FractionFormat {
/*     */   private static final long serialVersionUID = 760934726031766749L;
/*     */   
/*     */   private NumberFormat wholeFormat;
/*     */   
/*     */   public ProperFractionFormat() {
/*  51 */     this(getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public ProperFractionFormat(NumberFormat format) {
/*  61 */     this(format, (NumberFormat)format.clone(), (NumberFormat)format.clone());
/*     */   }
/*     */   
/*     */   public ProperFractionFormat(NumberFormat wholeFormat, NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
/*  75 */     super(numeratorFormat, denominatorFormat);
/*  76 */     setWholeFormat(wholeFormat);
/*     */   }
/*     */   
/*     */   public StringBuffer format(Fraction fraction, StringBuffer toAppendTo, FieldPosition pos) {
/*  93 */     pos.setBeginIndex(0);
/*  94 */     pos.setEndIndex(0);
/*  96 */     int num = fraction.getNumerator();
/*  97 */     int den = fraction.getDenominator();
/*  98 */     int whole = num / den;
/*  99 */     num %= den;
/* 101 */     if (whole != 0) {
/* 102 */       getWholeFormat().format(whole, toAppendTo, pos);
/* 103 */       toAppendTo.append(' ');
/* 104 */       num = Math.abs(num);
/*     */     } 
/* 106 */     getNumeratorFormat().format(num, toAppendTo, pos);
/* 107 */     toAppendTo.append(" / ");
/* 108 */     getDenominatorFormat().format(den, toAppendTo, pos);
/* 111 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   public NumberFormat getWholeFormat() {
/* 119 */     return this.wholeFormat;
/*     */   }
/*     */   
/*     */   public Fraction parse(String source, ParsePosition pos) {
/* 137 */     Fraction ret = super.parse(source, pos);
/* 138 */     if (ret != null)
/* 139 */       return ret; 
/* 142 */     int initialIndex = pos.getIndex();
/* 145 */     parseAndIgnoreWhitespace(source, pos);
/* 148 */     Number whole = getWholeFormat().parse(source, pos);
/* 149 */     if (whole == null) {
/* 153 */       pos.setIndex(initialIndex);
/* 154 */       return null;
/*     */     } 
/* 158 */     parseAndIgnoreWhitespace(source, pos);
/* 161 */     Number num = getNumeratorFormat().parse(source, pos);
/* 162 */     if (num == null) {
/* 166 */       pos.setIndex(initialIndex);
/* 167 */       return null;
/*     */     } 
/* 170 */     if (num.intValue() < 0) {
/* 172 */       pos.setIndex(initialIndex);
/* 173 */       return null;
/*     */     } 
/* 177 */     int startIndex = pos.getIndex();
/* 178 */     char c = parseNextCharacter(source, pos);
/* 179 */     switch (c) {
/*     */       case '\000':
/* 183 */         return new Fraction(num.intValue(), 1);
/*     */       case '/':
/*     */         break;
/*     */       default:
/* 191 */         pos.setIndex(initialIndex);
/* 192 */         pos.setErrorIndex(startIndex);
/* 193 */         return null;
/*     */     } 
/* 197 */     parseAndIgnoreWhitespace(source, pos);
/* 200 */     Number den = getDenominatorFormat().parse(source, pos);
/* 201 */     if (den == null) {
/* 205 */       pos.setIndex(initialIndex);
/* 206 */       return null;
/*     */     } 
/* 209 */     if (den.intValue() < 0) {
/* 211 */       pos.setIndex(initialIndex);
/* 212 */       return null;
/*     */     } 
/* 215 */     int w = whole.intValue();
/* 216 */     int n = num.intValue();
/* 217 */     int d = den.intValue();
/* 218 */     return new Fraction((Math.abs(w) * d + n) * MathUtils.copySign(1, w), d);
/*     */   }
/*     */   
/*     */   public void setWholeFormat(NumberFormat format) {
/* 227 */     if (format == null)
/* 228 */       throw new NullArgumentException(LocalizedFormats.WHOLE_FORMAT, new Object[0]); 
/* 230 */     this.wholeFormat = format;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\fraction\ProperFractionFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */