/*     */ package org.apache.commons.math3.fraction;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public abstract class AbstractFormat extends NumberFormat implements Serializable {
/*     */   private static final long serialVersionUID = -6981118387974191891L;
/*     */   
/*     */   private NumberFormat denominatorFormat;
/*     */   
/*     */   private NumberFormat numeratorFormat;
/*     */   
/*     */   protected AbstractFormat() {
/*  50 */     this(getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   protected AbstractFormat(NumberFormat format) {
/*  59 */     this(format, (NumberFormat)format.clone());
/*     */   }
/*     */   
/*     */   protected AbstractFormat(NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
/*  70 */     this.numeratorFormat = numeratorFormat;
/*  71 */     this.denominatorFormat = denominatorFormat;
/*     */   }
/*     */   
/*     */   protected static NumberFormat getDefaultNumberFormat() {
/*  81 */     return getDefaultNumberFormat(Locale.getDefault());
/*     */   }
/*     */   
/*     */   protected static NumberFormat getDefaultNumberFormat(Locale locale) {
/*  92 */     NumberFormat nf = NumberFormat.getNumberInstance(locale);
/*  93 */     nf.setMaximumFractionDigits(0);
/*  94 */     nf.setParseIntegerOnly(true);
/*  95 */     return nf;
/*     */   }
/*     */   
/*     */   public NumberFormat getDenominatorFormat() {
/* 103 */     return this.denominatorFormat;
/*     */   }
/*     */   
/*     */   public NumberFormat getNumeratorFormat() {
/* 111 */     return this.numeratorFormat;
/*     */   }
/*     */   
/*     */   public void setDenominatorFormat(NumberFormat format) {
/* 120 */     if (format == null)
/* 121 */       throw new NullArgumentException(LocalizedFormats.DENOMINATOR_FORMAT, new Object[0]); 
/* 123 */     this.denominatorFormat = format;
/*     */   }
/*     */   
/*     */   public void setNumeratorFormat(NumberFormat format) {
/* 132 */     if (format == null)
/* 133 */       throw new NullArgumentException(LocalizedFormats.NUMERATOR_FORMAT, new Object[0]); 
/* 135 */     this.numeratorFormat = format;
/*     */   }
/*     */   
/*     */   protected static void parseAndIgnoreWhitespace(String source, ParsePosition pos) {
/* 146 */     parseNextCharacter(source, pos);
/* 147 */     pos.setIndex(pos.getIndex() - 1);
/*     */   }
/*     */   
/*     */   protected static char parseNextCharacter(String source, ParsePosition pos) {
/* 158 */     int index = pos.getIndex();
/* 159 */     int n = source.length();
/* 160 */     char ret = Character.MIN_VALUE;
/* 162 */     if (index < n) {
/*     */       char c;
/*     */       do {
/* 165 */         c = source.charAt(index++);
/* 166 */       } while (Character.isWhitespace(c) && index < n);
/* 167 */       pos.setIndex(index);
/* 169 */       if (index < n)
/* 170 */         ret = c; 
/*     */     } 
/* 174 */     return ret;
/*     */   }
/*     */   
/*     */   public StringBuffer format(double value, StringBuffer buffer, FieldPosition position) {
/* 190 */     return format(Double.valueOf(value), buffer, position);
/*     */   }
/*     */   
/*     */   public StringBuffer format(long value, StringBuffer buffer, FieldPosition position) {
/* 207 */     return format(Long.valueOf(value), buffer, position);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\fraction\AbstractFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */