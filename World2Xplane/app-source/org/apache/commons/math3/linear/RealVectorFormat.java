/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.math3.exception.MathParseException;
/*     */ import org.apache.commons.math3.util.CompositeFormat;
/*     */ 
/*     */ public class RealVectorFormat {
/*     */   private static final String DEFAULT_PREFIX = "{";
/*     */   
/*     */   private static final String DEFAULT_SUFFIX = "}";
/*     */   
/*     */   private static final String DEFAULT_SEPARATOR = "; ";
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
/*     */   public RealVectorFormat() {
/*  73 */     this("{", "}", "; ", CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public RealVectorFormat(NumberFormat format) {
/*  82 */     this("{", "}", "; ", format);
/*     */   }
/*     */   
/*     */   public RealVectorFormat(String prefix, String suffix, String separator) {
/*  93 */     this(prefix, suffix, separator, CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public RealVectorFormat(String prefix, String suffix, String separator, NumberFormat format) {
/* 107 */     this.prefix = prefix;
/* 108 */     this.suffix = suffix;
/* 109 */     this.separator = separator;
/* 110 */     this.trimmedPrefix = prefix.trim();
/* 111 */     this.trimmedSuffix = suffix.trim();
/* 112 */     this.trimmedSeparator = separator.trim();
/* 113 */     this.format = format;
/*     */   }
/*     */   
/*     */   public static Locale[] getAvailableLocales() {
/* 122 */     return NumberFormat.getAvailableLocales();
/*     */   }
/*     */   
/*     */   public String getPrefix() {
/* 130 */     return this.prefix;
/*     */   }
/*     */   
/*     */   public String getSuffix() {
/* 138 */     return this.suffix;
/*     */   }
/*     */   
/*     */   public String getSeparator() {
/* 146 */     return this.separator;
/*     */   }
/*     */   
/*     */   public NumberFormat getFormat() {
/* 154 */     return this.format;
/*     */   }
/*     */   
/*     */   public static RealVectorFormat getInstance() {
/* 162 */     return getInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static RealVectorFormat getInstance(Locale locale) {
/* 171 */     return new RealVectorFormat(CompositeFormat.getDefaultNumberFormat(locale));
/*     */   }
/*     */   
/*     */   public String format(RealVector v) {
/* 181 */     return format(v, new StringBuffer(), new FieldPosition(0)).toString();
/*     */   }
/*     */   
/*     */   public StringBuffer format(RealVector vector, StringBuffer toAppendTo, FieldPosition pos) {
/* 195 */     pos.setBeginIndex(0);
/* 196 */     pos.setEndIndex(0);
/* 199 */     toAppendTo.append(this.prefix);
/* 202 */     for (int i = 0; i < vector.getDimension(); i++) {
/* 203 */       if (i > 0)
/* 204 */         toAppendTo.append(this.separator); 
/* 206 */       CompositeFormat.formatDouble(vector.getEntry(i), this.format, toAppendTo, pos);
/*     */     } 
/* 210 */     toAppendTo.append(this.suffix);
/* 212 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   public ArrayRealVector parse(String source) {
/* 224 */     ParsePosition parsePosition = new ParsePosition(0);
/* 225 */     ArrayRealVector result = parse(source, parsePosition);
/* 226 */     if (parsePosition.getIndex() == 0)
/* 227 */       throw new MathParseException(source, parsePosition.getErrorIndex(), ArrayRealVector.class); 
/* 231 */     return result;
/*     */   }
/*     */   
/*     */   public ArrayRealVector parse(String source, ParsePosition pos) {
/* 242 */     int initialIndex = pos.getIndex();
/* 245 */     CompositeFormat.parseAndIgnoreWhitespace(source, pos);
/* 246 */     if (!CompositeFormat.parseFixedstring(source, this.trimmedPrefix, pos))
/* 247 */       return null; 
/* 251 */     List<Number> components = new ArrayList<Number>();
/* 252 */     for (boolean loop = true; loop; ) {
/* 254 */       if (!components.isEmpty()) {
/* 255 */         CompositeFormat.parseAndIgnoreWhitespace(source, pos);
/* 256 */         if (!CompositeFormat.parseFixedstring(source, this.trimmedSeparator, pos))
/* 257 */           loop = false; 
/*     */       } 
/* 261 */       if (loop) {
/* 262 */         CompositeFormat.parseAndIgnoreWhitespace(source, pos);
/* 263 */         Number component = CompositeFormat.parseNumber(source, this.format, pos);
/* 264 */         if (component != null) {
/* 265 */           components.add(component);
/*     */           continue;
/*     */         } 
/* 269 */         pos.setIndex(initialIndex);
/* 270 */         return null;
/*     */       } 
/*     */     } 
/* 277 */     CompositeFormat.parseAndIgnoreWhitespace(source, pos);
/* 278 */     if (!CompositeFormat.parseFixedstring(source, this.trimmedSuffix, pos))
/* 279 */       return null; 
/* 283 */     double[] data = new double[components.size()];
/* 284 */     for (int i = 0; i < data.length; i++)
/* 285 */       data[i] = ((Number)components.get(i)).doubleValue(); 
/* 287 */     return new ArrayRealVector(data, false);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\RealVectorFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */