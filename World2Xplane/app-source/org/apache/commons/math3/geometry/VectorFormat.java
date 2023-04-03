/*     */ package org.apache.commons.math3.geometry;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.math3.exception.MathParseException;
/*     */ import org.apache.commons.math3.util.CompositeFormat;
/*     */ 
/*     */ public abstract class VectorFormat<S extends Space> {
/*     */   public static final String DEFAULT_PREFIX = "{";
/*     */   
/*     */   public static final String DEFAULT_SUFFIX = "}";
/*     */   
/*     */   public static final String DEFAULT_SEPARATOR = "; ";
/*     */   
/*     */   private final String prefix;
/*     */   
/*     */   private final String suffix;
/*     */   
/*     */   private final String separator;
/*     */   
/*     */   private final String trimmedPrefix;
/*     */   
/*     */   private final String trimmedSuffix;
/*     */   
/*     */   private final String trimmedSeparator;
/*     */   
/*     */   private final NumberFormat format;
/*     */   
/*     */   protected VectorFormat() {
/*  81 */     this("{", "}", "; ", CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   protected VectorFormat(NumberFormat format) {
/*  90 */     this("{", "}", "; ", format);
/*     */   }
/*     */   
/*     */   protected VectorFormat(String prefix, String suffix, String separator) {
/* 101 */     this(prefix, suffix, separator, CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   protected VectorFormat(String prefix, String suffix, String separator, NumberFormat format) {
/* 114 */     this.prefix = prefix;
/* 115 */     this.suffix = suffix;
/* 116 */     this.separator = separator;
/* 117 */     this.trimmedPrefix = prefix.trim();
/* 118 */     this.trimmedSuffix = suffix.trim();
/* 119 */     this.trimmedSeparator = separator.trim();
/* 120 */     this.format = format;
/*     */   }
/*     */   
/*     */   public static Locale[] getAvailableLocales() {
/* 129 */     return NumberFormat.getAvailableLocales();
/*     */   }
/*     */   
/*     */   public String getPrefix() {
/* 137 */     return this.prefix;
/*     */   }
/*     */   
/*     */   public String getSuffix() {
/* 145 */     return this.suffix;
/*     */   }
/*     */   
/*     */   public String getSeparator() {
/* 153 */     return this.separator;
/*     */   }
/*     */   
/*     */   public NumberFormat getFormat() {
/* 161 */     return this.format;
/*     */   }
/*     */   
/*     */   public String format(Vector<S> vector) {
/* 170 */     return format(vector, new StringBuffer(), new FieldPosition(0)).toString();
/*     */   }
/*     */   
/*     */   public abstract StringBuffer format(Vector<S> paramVector, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition);
/*     */   
/*     */   protected StringBuffer format(StringBuffer toAppendTo, FieldPosition pos, double... coordinates) {
/* 195 */     pos.setBeginIndex(0);
/* 196 */     pos.setEndIndex(0);
/* 199 */     toAppendTo.append(this.prefix);
/* 202 */     for (int i = 0; i < coordinates.length; i++) {
/* 203 */       if (i > 0)
/* 204 */         toAppendTo.append(this.separator); 
/* 206 */       CompositeFormat.formatDouble(coordinates[i], this.format, toAppendTo, pos);
/*     */     } 
/* 210 */     toAppendTo.append(this.suffix);
/* 212 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   public abstract Vector<S> parse(String paramString) throws MathParseException;
/*     */   
/*     */   public abstract Vector<S> parse(String paramString, ParsePosition paramParsePosition);
/*     */   
/*     */   protected double[] parseCoordinates(int dimension, String source, ParsePosition pos) {
/* 242 */     int initialIndex = pos.getIndex();
/* 243 */     double[] coordinates = new double[dimension];
/* 246 */     CompositeFormat.parseAndIgnoreWhitespace(source, pos);
/* 247 */     if (!CompositeFormat.parseFixedstring(source, this.trimmedPrefix, pos))
/* 248 */       return null; 
/* 251 */     for (int i = 0; i < dimension; i++) {
/* 254 */       CompositeFormat.parseAndIgnoreWhitespace(source, pos);
/* 257 */       if (i > 0 && 
/* 258 */         !CompositeFormat.parseFixedstring(source, this.trimmedSeparator, pos))
/* 259 */         return null; 
/* 264 */       CompositeFormat.parseAndIgnoreWhitespace(source, pos);
/* 267 */       Number c = CompositeFormat.parseNumber(source, this.format, pos);
/* 268 */       if (c == null) {
/* 271 */         pos.setIndex(initialIndex);
/* 272 */         return null;
/*     */       } 
/* 276 */       coordinates[i] = c.doubleValue();
/*     */     } 
/* 281 */     CompositeFormat.parseAndIgnoreWhitespace(source, pos);
/* 282 */     if (!CompositeFormat.parseFixedstring(source, this.trimmedSuffix, pos))
/* 283 */       return null; 
/* 286 */     return coordinates;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\VectorFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */