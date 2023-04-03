/*     */ package org.apache.commons.math3.fraction;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigInteger;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathParseException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class BigFractionFormat extends AbstractFormat implements Serializable {
/*     */   private static final long serialVersionUID = -2932167925527338976L;
/*     */   
/*     */   public BigFractionFormat() {}
/*     */   
/*     */   public BigFractionFormat(NumberFormat format) {
/*  59 */     super(format);
/*     */   }
/*     */   
/*     */   public BigFractionFormat(NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
/*  70 */     super(numeratorFormat, denominatorFormat);
/*     */   }
/*     */   
/*     */   public static Locale[] getAvailableLocales() {
/*  79 */     return NumberFormat.getAvailableLocales();
/*     */   }
/*     */   
/*     */   public static String formatBigFraction(BigFraction f) {
/*  90 */     return getImproperInstance().format(f);
/*     */   }
/*     */   
/*     */   public static BigFractionFormat getImproperInstance() {
/*  98 */     return getImproperInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static BigFractionFormat getImproperInstance(Locale locale) {
/* 107 */     return new BigFractionFormat(getDefaultNumberFormat(locale));
/*     */   }
/*     */   
/*     */   public static BigFractionFormat getProperInstance() {
/* 115 */     return getProperInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static BigFractionFormat getProperInstance(Locale locale) {
/* 124 */     return new ProperBigFractionFormat(getDefaultNumberFormat(locale));
/*     */   }
/*     */   
/*     */   public StringBuffer format(BigFraction bigFraction, StringBuffer toAppendTo, FieldPosition pos) {
/* 140 */     pos.setBeginIndex(0);
/* 141 */     pos.setEndIndex(0);
/* 143 */     getNumeratorFormat().format(bigFraction.getNumerator(), toAppendTo, pos);
/* 144 */     toAppendTo.append(" / ");
/* 145 */     getDenominatorFormat().format(bigFraction.getDenominator(), toAppendTo, pos);
/* 147 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
/*     */     StringBuffer ret;
/* 169 */     if (obj instanceof BigFraction) {
/* 170 */       ret = format((BigFraction)obj, toAppendTo, pos);
/* 171 */     } else if (obj instanceof BigInteger) {
/* 172 */       ret = format(new BigFraction((BigInteger)obj), toAppendTo, pos);
/* 173 */     } else if (obj instanceof Number) {
/* 174 */       ret = format(new BigFraction(((Number)obj).doubleValue()), toAppendTo, pos);
/*     */     } else {
/* 177 */       throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_OBJECT_TO_FRACTION, new Object[0]);
/*     */     } 
/* 180 */     return ret;
/*     */   }
/*     */   
/*     */   public BigFraction parse(String source) throws MathParseException {
/* 192 */     ParsePosition parsePosition = new ParsePosition(0);
/* 193 */     BigFraction result = parse(source, parsePosition);
/* 194 */     if (parsePosition.getIndex() == 0)
/* 195 */       throw new MathParseException(source, parsePosition.getErrorIndex(), BigFraction.class); 
/* 197 */     return result;
/*     */   }
/*     */   
/*     */   public BigFraction parse(String source, ParsePosition pos) {
/* 209 */     int initialIndex = pos.getIndex();
/* 212 */     parseAndIgnoreWhitespace(source, pos);
/* 215 */     BigInteger num = parseNextBigInteger(source, pos);
/* 216 */     if (num == null) {
/* 220 */       pos.setIndex(initialIndex);
/* 221 */       return null;
/*     */     } 
/* 225 */     int startIndex = pos.getIndex();
/* 226 */     char c = parseNextCharacter(source, pos);
/* 227 */     switch (c) {
/*     */       case '\000':
/* 231 */         return new BigFraction(num);
/*     */       case '/':
/*     */         break;
/*     */       default:
/* 239 */         pos.setIndex(initialIndex);
/* 240 */         pos.setErrorIndex(startIndex);
/* 241 */         return null;
/*     */     } 
/* 245 */     parseAndIgnoreWhitespace(source, pos);
/* 248 */     BigInteger den = parseNextBigInteger(source, pos);
/* 249 */     if (den == null) {
/* 253 */       pos.setIndex(initialIndex);
/* 254 */       return null;
/*     */     } 
/* 257 */     return new BigFraction(num, den);
/*     */   }
/*     */   
/*     */   protected BigInteger parseNextBigInteger(String source, ParsePosition pos) {
/* 270 */     int start = pos.getIndex();
/* 271 */     int end = (source.charAt(start) == '-') ? (start + 1) : start;
/* 272 */     while (end < source.length() && Character.isDigit(source.charAt(end)))
/* 274 */       end++; 
/*     */     try {
/* 278 */       BigInteger n = new BigInteger(source.substring(start, end));
/* 279 */       pos.setIndex(end);
/* 280 */       return n;
/* 281 */     } catch (NumberFormatException nfe) {
/* 282 */       pos.setErrorIndex(start);
/* 283 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\fraction\BigFractionFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */