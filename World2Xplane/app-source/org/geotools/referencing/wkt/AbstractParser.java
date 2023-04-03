/*     */ package org.geotools.referencing.wkt;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Writer;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterValueException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public abstract class AbstractParser extends Format {
/*     */   private static final boolean SCIENTIFIC_NOTATION = true;
/*     */   
/*     */   private transient Formatter formatter;
/*     */   
/*     */   final Symbols symbols;
/*     */   
/*     */   private final NumberFormat numberFormat;
/*     */   
/*     */   public AbstractParser(Symbols symbols) {
/*  87 */     this.symbols = symbols;
/*  88 */     this.numberFormat = (NumberFormat)symbols.numberFormat.clone();
/*  89 */     if (this.numberFormat instanceof DecimalFormat) {
/*  90 */       DecimalFormat numberFormat = (DecimalFormat)this.numberFormat;
/*  91 */       String pattern = numberFormat.toPattern();
/*  92 */       if (pattern.indexOf("E0") < 0) {
/*  93 */         int split = pattern.indexOf(';');
/*  94 */         if (split >= 0)
/*  95 */           pattern = pattern.substring(0, split) + "E0" + pattern.substring(split); 
/*  97 */         pattern = pattern + "E0";
/*  98 */         numberFormat.applyPattern(pattern);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 111 */     return getFormatter().getAuthority();
/*     */   }
/*     */   
/*     */   public void setAuthority(Citation authority) {
/* 122 */     if (authority == null)
/* 123 */       throw new IllegalArgumentException(Errors.format(143, "authority")); 
/* 126 */     getFormatter().setAuthority(authority);
/*     */   }
/*     */   
/*     */   public boolean isColorEnabled() {
/* 138 */     return (getFormatter()).colorEnabled;
/*     */   }
/*     */   
/*     */   public void setColorEnabled(boolean enabled) {
/* 152 */     (getFormatter()).colorEnabled = enabled;
/*     */   }
/*     */   
/*     */   public final Object parseObject(String text) throws ParseException {
/* 164 */     Element element = getTree(text, new ParsePosition(0));
/* 165 */     Object object = parse(element);
/* 166 */     element.close();
/* 167 */     return object;
/*     */   }
/*     */   
/*     */   public final Object parseObject(String text, ParsePosition position) {
/* 178 */     int origin = position.getIndex();
/*     */     try {
/* 180 */       return parse(getTree(text, position));
/* 181 */     } catch (ParseException exception) {
/* 182 */       position.setIndex(origin);
/* 183 */       if (position.getErrorIndex() < origin)
/* 184 */         position.setErrorIndex(exception.getErrorOffset()); 
/* 186 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   final Number parseNumber(String text, ParsePosition position) {
/* 199 */     int base = position.getIndex();
/* 200 */     Number number = this.numberFormat.parse(text, position);
/* 201 */     if (number != null) {
/* 202 */       int i = position.getIndex();
/* 203 */       if (i < text.length() && text.charAt(i) == 'e') {
/* 204 */         StringBuilder buffer = new StringBuilder(text);
/* 205 */         buffer.setCharAt(i, 'E');
/* 206 */         text = buffer.toString();
/* 207 */         position.setIndex(base);
/* 208 */         number = this.numberFormat.parse(text, position);
/*     */       } 
/*     */     } 
/* 211 */     return number;
/*     */   }
/*     */   
/*     */   protected abstract Object parse(Element paramElement) throws ParseException;
/*     */   
/*     */   protected final Element getTree(String text, ParsePosition position) throws ParseException {
/* 238 */     return new Element(new Element(this, text, position));
/*     */   }
/*     */   
/*     */   private Formatter getFormatter() {
/* 245 */     if (this.formatter == null)
/* 250 */       this.formatter = new Formatter(this.symbols, (NumberFormat)this.symbols.numberFormat.clone()); 
/* 255 */     return this.formatter;
/*     */   }
/*     */   
/*     */   public StringBuffer format(Object object, StringBuffer toAppendTo, FieldPosition pos) {
/* 272 */     Formatter formatter = getFormatter();
/*     */     try {
/* 274 */       formatter.clear();
/* 275 */       formatter.buffer = toAppendTo;
/* 276 */       formatter.bufferBase = toAppendTo.length();
/* 277 */       if (object instanceof MathTransform) {
/* 278 */         formatter.append((MathTransform)object);
/* 279 */       } else if (object instanceof GeneralParameterValue) {
/* 283 */         formatter.append((GeneralParameterValue)object);
/*     */       } else {
/* 285 */         formatter.append((IdentifiedObject)object);
/*     */       } 
/* 287 */       return toAppendTo;
/*     */     } finally {
/* 289 */       formatter.buffer = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reformat(BufferedReader in, Writer out, PrintWriter err) throws IOException {
/* 308 */     String lineSeparator = System.getProperty("line.separator", "\n");
/* 309 */     String line = null;
/*     */     try {
/* 311 */       while ((line = in.readLine()) != null) {
/* 312 */         if ((line = line.trim()).length() != 0) {
/* 313 */           out.write(lineSeparator);
/* 314 */           out.write(format(parseObject(line)));
/* 315 */           out.write(lineSeparator);
/* 316 */           out.write(lineSeparator);
/* 317 */           out.flush();
/*     */         } 
/*     */       } 
/* 320 */     } catch (ParseException exception) {
/* 321 */       err.println(exception.getLocalizedMessage());
/* 322 */       if (line != null)
/* 323 */         reportError(err, line, exception.getErrorOffset()); 
/* 325 */     } catch (InvalidParameterValueException exception) {
/* 326 */       err.print(Errors.format(84, exception.getParameterName()));
/* 327 */       err.print(' ');
/* 328 */       err.println(exception.getLocalizedMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getWarning() {
/* 342 */     if (this.formatter != null && this.formatter.isInvalidWKT()) {
/* 343 */       if (this.formatter.warning != null)
/* 344 */         return this.formatter.warning; 
/* 346 */       return Errors.format(83, this.formatter.getUnformattableClass());
/*     */     } 
/* 348 */     return null;
/*     */   }
/*     */   
/*     */   static void reportError(PrintWriter err, String line, int errorOffset) {
/* 360 */     line = line.replace('\r', ' ').replace('\n', ' ');
/* 361 */     int WINDOW_WIDTH = 80;
/* 362 */     int stop = line.length();
/* 363 */     int base = errorOffset - 40;
/* 364 */     int baseMax = stop - 80;
/* 365 */     boolean hasTrailing = (Math.max(base, 0) < baseMax);
/* 366 */     if (!hasTrailing)
/* 367 */       base = baseMax; 
/* 369 */     if (base < 0)
/* 370 */       base = 0; 
/* 372 */     stop = Math.min(stop, base + 80);
/* 373 */     if (hasTrailing)
/* 374 */       stop -= 3; 
/* 376 */     if (base != 0) {
/* 377 */       err.print("...");
/* 378 */       errorOffset += 3;
/* 379 */       base += 3;
/*     */     } 
/* 381 */     err.print(line.substring(base, stop));
/* 382 */     if (hasTrailing) {
/* 383 */       err.println("...");
/*     */     } else {
/* 385 */       err.println();
/*     */     } 
/* 387 */     err.print(Utilities.spaces(errorOffset - base));
/* 388 */     err.println('^');
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\AbstractParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */