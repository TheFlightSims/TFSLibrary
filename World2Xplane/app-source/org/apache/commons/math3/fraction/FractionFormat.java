/*     */ package org.apache.commons.math3.fraction;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathParseException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class FractionFormat extends AbstractFormat {
/*     */   private static final long serialVersionUID = 3008655719530972611L;
/*     */   
/*     */   public FractionFormat() {}
/*     */   
/*     */   public FractionFormat(NumberFormat format) {
/*  55 */     super(format);
/*     */   }
/*     */   
/*     */   public FractionFormat(NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
/*  66 */     super(numeratorFormat, denominatorFormat);
/*     */   }
/*     */   
/*     */   public static Locale[] getAvailableLocales() {
/*  75 */     return NumberFormat.getAvailableLocales();
/*     */   }
/*     */   
/*     */   public static String formatFraction(Fraction f) {
/*  86 */     return getImproperInstance().format(f);
/*     */   }
/*     */   
/*     */   public static FractionFormat getImproperInstance() {
/*  94 */     return getImproperInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static FractionFormat getImproperInstance(Locale locale) {
/* 103 */     return new FractionFormat(getDefaultNumberFormat(locale));
/*     */   }
/*     */   
/*     */   public static FractionFormat getProperInstance() {
/* 111 */     return getProperInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static FractionFormat getProperInstance(Locale locale) {
/* 120 */     return new ProperFractionFormat(getDefaultNumberFormat(locale));
/*     */   }
/*     */   
/*     */   protected static NumberFormat getDefaultNumberFormat() {
/* 130 */     return getDefaultNumberFormat(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public StringBuffer format(Fraction fraction, StringBuffer toAppendTo, FieldPosition pos) {
/* 146 */     pos.setBeginIndex(0);
/* 147 */     pos.setEndIndex(0);
/* 149 */     getNumeratorFormat().format(fraction.getNumerator(), toAppendTo, pos);
/* 150 */     toAppendTo.append(" / ");
/* 151 */     getDenominatorFormat().format(fraction.getDenominator(), toAppendTo, pos);
/* 154 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) throws FractionConversionException, MathIllegalArgumentException {
/* 175 */     StringBuffer ret = null;
/* 177 */     if (obj instanceof Fraction) {
/* 178 */       ret = format((Fraction)obj, toAppendTo, pos);
/* 179 */     } else if (obj instanceof Number) {
/* 180 */       ret = format(new Fraction(((Number)obj).doubleValue()), toAppendTo, pos);
/*     */     } else {
/* 182 */       throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_OBJECT_TO_FRACTION, new Object[0]);
/*     */     } 
/* 185 */     return ret;
/*     */   }
/*     */   
/*     */   public Fraction parse(String source) throws MathParseException {
/* 197 */     ParsePosition parsePosition = new ParsePosition(0);
/* 198 */     Fraction result = parse(source, parsePosition);
/* 199 */     if (parsePosition.getIndex() == 0)
/* 200 */       throw new MathParseException(source, parsePosition.getErrorIndex(), Fraction.class); 
/* 202 */     return result;
/*     */   }
/*     */   
/*     */   public Fraction parse(String source, ParsePosition pos) {
/* 214 */     int initialIndex = pos.getIndex();
/* 217 */     parseAndIgnoreWhitespace(source, pos);
/* 220 */     Number num = getNumeratorFormat().parse(source, pos);
/* 221 */     if (num == null) {
/* 225 */       pos.setIndex(initialIndex);
/* 226 */       return null;
/*     */     } 
/* 230 */     int startIndex = pos.getIndex();
/* 231 */     char c = parseNextCharacter(source, pos);
/* 232 */     switch (c) {
/*     */       case '\000':
/* 236 */         return new Fraction(num.intValue(), 1);
/*     */       case '/':
/*     */         break;
/*     */       default:
/* 244 */         pos.setIndex(initialIndex);
/* 245 */         pos.setErrorIndex(startIndex);
/* 246 */         return null;
/*     */     } 
/* 250 */     parseAndIgnoreWhitespace(source, pos);
/* 253 */     Number den = getDenominatorFormat().parse(source, pos);
/* 254 */     if (den == null) {
/* 258 */       pos.setIndex(initialIndex);
/* 259 */       return null;
/*     */     } 
/* 262 */     return new Fraction(num.intValue(), den.intValue());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\fraction\FractionFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */