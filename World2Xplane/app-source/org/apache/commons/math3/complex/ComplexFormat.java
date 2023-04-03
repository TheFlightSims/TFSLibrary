/*     */ package org.apache.commons.math3.complex;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathInternalError;
/*     */ import org.apache.commons.math3.exception.MathParseException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.CompositeFormat;
/*     */ 
/*     */ public class ComplexFormat {
/*     */   private static final String DEFAULT_IMAGINARY_CHARACTER = "i";
/*     */   
/*     */   private final String imaginaryCharacter;
/*     */   
/*     */   private final NumberFormat imaginaryFormat;
/*     */   
/*     */   private final NumberFormat realFormat;
/*     */   
/*     */   public ComplexFormat() {
/*  56 */     this("i", CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public ComplexFormat(NumberFormat format) {
/*  65 */     this("i", format);
/*     */   }
/*     */   
/*     */   public ComplexFormat(NumberFormat realFormat, NumberFormat imaginaryFormat) {
/*  75 */     this("i", realFormat, imaginaryFormat);
/*     */   }
/*     */   
/*     */   public ComplexFormat(String imaginaryCharacter) {
/*  84 */     this(imaginaryCharacter, CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public ComplexFormat(String imaginaryCharacter, NumberFormat format) {
/*  94 */     this(imaginaryCharacter, format, format);
/*     */   }
/*     */   
/*     */   public ComplexFormat(String imaginaryCharacter, NumberFormat realFormat, NumberFormat imaginaryFormat) {
/* 115 */     if (imaginaryCharacter == null)
/* 116 */       throw new NullArgumentException(); 
/* 118 */     if (imaginaryCharacter.length() == 0)
/* 119 */       throw new NoDataException(); 
/* 121 */     if (imaginaryFormat == null)
/* 122 */       throw new NullArgumentException(LocalizedFormats.IMAGINARY_FORMAT, new Object[0]); 
/* 124 */     if (realFormat == null)
/* 125 */       throw new NullArgumentException(LocalizedFormats.REAL_FORMAT, new Object[0]); 
/* 128 */     this.imaginaryCharacter = imaginaryCharacter;
/* 129 */     this.imaginaryFormat = imaginaryFormat;
/* 130 */     this.realFormat = realFormat;
/*     */   }
/*     */   
/*     */   public static Locale[] getAvailableLocales() {
/* 139 */     return NumberFormat.getAvailableLocales();
/*     */   }
/*     */   
/*     */   public String format(Complex c) {
/* 149 */     return format(c, new StringBuffer(), new FieldPosition(0)).toString();
/*     */   }
/*     */   
/*     */   public String format(Double c) {
/* 159 */     return format(new Complex(c.doubleValue(), 0.0D), new StringBuffer(), new FieldPosition(0)).toString();
/*     */   }
/*     */   
/*     */   public StringBuffer format(Complex complex, StringBuffer toAppendTo, FieldPosition pos) {
/* 173 */     pos.setBeginIndex(0);
/* 174 */     pos.setEndIndex(0);
/* 177 */     double re = complex.getReal();
/* 178 */     CompositeFormat.formatDouble(re, getRealFormat(), toAppendTo, pos);
/* 181 */     double im = complex.getImaginary();
/* 183 */     if (im < 0.0D) {
/* 184 */       toAppendTo.append(" - ");
/* 185 */       StringBuffer imAppendTo = formatImaginary(-im, new StringBuffer(), pos);
/* 186 */       toAppendTo.append(imAppendTo);
/* 187 */       toAppendTo.append(getImaginaryCharacter());
/* 188 */     } else if (im > 0.0D || Double.isNaN(im)) {
/* 189 */       toAppendTo.append(" + ");
/* 190 */       StringBuffer imAppendTo = formatImaginary(im, new StringBuffer(), pos);
/* 191 */       toAppendTo.append(imAppendTo);
/* 192 */       toAppendTo.append(getImaginaryCharacter());
/*     */     } 
/* 195 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   private StringBuffer formatImaginary(double absIm, StringBuffer toAppendTo, FieldPosition pos) {
/* 211 */     if (absIm < 0.0D)
/* 212 */       throw new MathInternalError(); 
/* 215 */     pos.setBeginIndex(0);
/* 216 */     pos.setEndIndex(0);
/* 218 */     CompositeFormat.formatDouble(absIm, getImaginaryFormat(), toAppendTo, pos);
/* 219 */     if (toAppendTo.toString().equals("1"))
/* 221 */       toAppendTo.setLength(0); 
/* 224 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
/* 243 */     StringBuffer ret = null;
/* 245 */     if (obj instanceof Complex) {
/* 246 */       ret = format((Complex)obj, toAppendTo, pos);
/* 247 */     } else if (obj instanceof Number) {
/* 248 */       ret = format(new Complex(((Number)obj).doubleValue(), 0.0D), toAppendTo, pos);
/*     */     } else {
/* 251 */       throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_INSTANCE_AS_COMPLEX, new Object[] { obj.getClass().getName() });
/*     */     } 
/* 255 */     return ret;
/*     */   }
/*     */   
/*     */   public String getImaginaryCharacter() {
/* 263 */     return this.imaginaryCharacter;
/*     */   }
/*     */   
/*     */   public NumberFormat getImaginaryFormat() {
/* 271 */     return this.imaginaryFormat;
/*     */   }
/*     */   
/*     */   public static ComplexFormat getInstance() {
/* 279 */     return getInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static ComplexFormat getInstance(Locale locale) {
/* 288 */     NumberFormat f = CompositeFormat.getDefaultNumberFormat(locale);
/* 289 */     return new ComplexFormat(f);
/*     */   }
/*     */   
/*     */   public static ComplexFormat getInstance(String imaginaryCharacter, Locale locale) {
/* 300 */     NumberFormat f = CompositeFormat.getDefaultNumberFormat(locale);
/* 301 */     return new ComplexFormat(imaginaryCharacter, f);
/*     */   }
/*     */   
/*     */   public NumberFormat getRealFormat() {
/* 309 */     return this.realFormat;
/*     */   }
/*     */   
/*     */   public Complex parse(String source) {
/* 321 */     ParsePosition parsePosition = new ParsePosition(0);
/* 322 */     Complex result = parse(source, parsePosition);
/* 323 */     if (parsePosition.getIndex() == 0)
/* 324 */       throw new MathParseException(source, parsePosition.getErrorIndex(), Complex.class); 
/* 328 */     return result;
/*     */   }
/*     */   
/*     */   public Complex parse(String source, ParsePosition pos) {
/* 339 */     int initialIndex = pos.getIndex();
/* 342 */     CompositeFormat.parseAndIgnoreWhitespace(source, pos);
/* 345 */     Number re = CompositeFormat.parseNumber(source, getRealFormat(), pos);
/* 346 */     if (re == null) {
/* 349 */       pos.setIndex(initialIndex);
/* 350 */       return null;
/*     */     } 
/* 354 */     int startIndex = pos.getIndex();
/* 355 */     char c = CompositeFormat.parseNextCharacter(source, pos);
/* 356 */     int sign = 0;
/* 357 */     switch (c) {
/*     */       case '\000':
/* 361 */         return new Complex(re.doubleValue(), 0.0D);
/*     */       case '-':
/* 363 */         sign = -1;
/*     */         break;
/*     */       case '+':
/* 366 */         sign = 1;
/*     */         break;
/*     */       default:
/* 372 */         pos.setIndex(initialIndex);
/* 373 */         pos.setErrorIndex(startIndex);
/* 374 */         return null;
/*     */     } 
/* 378 */     CompositeFormat.parseAndIgnoreWhitespace(source, pos);
/* 381 */     Number im = CompositeFormat.parseNumber(source, getRealFormat(), pos);
/* 382 */     if (im == null) {
/* 385 */       pos.setIndex(initialIndex);
/* 386 */       return null;
/*     */     } 
/* 390 */     if (!CompositeFormat.parseFixedstring(source, getImaginaryCharacter(), pos))
/* 391 */       return null; 
/* 394 */     return new Complex(re.doubleValue(), im.doubleValue() * sign);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\complex\ComplexFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */