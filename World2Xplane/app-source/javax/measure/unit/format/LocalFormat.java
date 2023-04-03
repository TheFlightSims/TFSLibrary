/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.math.BigInteger;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.measure.converter.AddConverter;
/*     */ import javax.measure.converter.ExpConverter;
/*     */ import javax.measure.converter.LogConverter;
/*     */ import javax.measure.converter.MultiplyConverter;
/*     */ import javax.measure.converter.RationalConverter;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.unit.AlternateUnit;
/*     */ import javax.measure.unit.AnnotatedUnit;
/*     */ import javax.measure.unit.BaseUnit;
/*     */ import javax.measure.unit.ProductUnit;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.TransformedUnit;
/*     */ import javax.measure.unit.Unit;
/*     */ import javax.measure.unit.UnitFormat;
/*     */ 
/*     */ public class LocalFormat extends UnitFormat {
/* 136 */   private static LocalFormat DEFAULT_INSTANCE = new LocalFormat(new SymbolMap(ResourceBundle.getBundle("javax.measure.unit.format.LocalFormat")));
/*     */   
/*     */   private static final char MIDDLE_DOT = '·';
/*     */   
/*     */   private static final int ADDITION_PRECEDENCE = 0;
/*     */   
/*     */   private static final int PRODUCT_PRECEDENCE = 2;
/*     */   
/*     */   private static final int EXPONENT_PRECEDENCE = 4;
/*     */   
/*     */   private static final int NOOP_PRECEDENCE = 2147483647;
/*     */   
/*     */   private SymbolMap _symbolMap;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public static LocalFormat getInstance() {
/* 158 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */   
/*     */   public static LocalFormat getInstance(Locale locale) {
/* 166 */     return new LocalFormat(new SymbolMap(ResourceBundle.getBundle("javax.measure.unit.format.LocalFormat", locale)));
/*     */   }
/*     */   
/*     */   public static LocalFormat getInstance(SymbolMap symbols) {
/* 171 */     return new LocalFormat(symbols);
/*     */   }
/*     */   
/*     */   private LocalFormat(SymbolMap symbols) {
/* 191 */     this._symbolMap = symbols;
/*     */   }
/*     */   
/*     */   public SymbolMap getSymbols() {
/* 203 */     return this._symbolMap;
/*     */   }
/*     */   
/*     */   public Appendable format(Unit<?> unit, Appendable appendable) throws IOException {
/* 211 */     formatInternal(unit, appendable);
/* 212 */     return appendable;
/*     */   }
/*     */   
/*     */   public Unit<?> parse(CharSequence csq, ParsePosition cursor) throws IllegalArgumentException {
/* 218 */     int start = cursor.getIndex();
/* 219 */     int end = csq.length();
/* 220 */     if (end <= start)
/* 221 */       return Unit.ONE; 
/* 223 */     String source = csq.subSequence(start, end).toString().trim();
/* 224 */     if (source.length() == 0)
/* 225 */       return Unit.ONE; 
/*     */     try {
/* 228 */       UnitParser parser = new UnitParser(this._symbolMap, new StringReader(source));
/* 229 */       Unit<?> result = parser.parseUnit();
/* 230 */       cursor.setIndex(end);
/* 231 */       return result;
/* 232 */     } catch (ParseException e) {
/* 233 */       if (e.currentToken != null) {
/* 234 */         cursor.setErrorIndex(start + e.currentToken.endColumn);
/*     */       } else {
/* 236 */         cursor.setErrorIndex(start);
/*     */       } 
/* 238 */       throw new IllegalArgumentException(e.getMessage());
/* 239 */     } catch (TokenMgrError e) {
/* 240 */       cursor.setErrorIndex(start);
/* 241 */       throw new IllegalArgumentException(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   private int formatInternal(Unit<?> unit, Appendable buffer) throws IOException {
/* 256 */     if (unit instanceof AnnotatedUnit)
/* 257 */       unit = ((AnnotatedUnit)unit).getRealUnit(); 
/* 259 */     String symbol = this._symbolMap.getSymbol(unit);
/* 260 */     if (symbol != null) {
/* 261 */       buffer.append(symbol);
/* 262 */       return Integer.MAX_VALUE;
/*     */     } 
/* 263 */     if (unit instanceof ProductUnit) {
/* 264 */       ProductUnit<?> productUnit = (ProductUnit)unit;
/* 265 */       int negativeExponentCount = 0;
/* 267 */       boolean start = true;
/*     */       int i;
/* 268 */       for (i = 0; i < productUnit.getUnitCount(); i++) {
/* 269 */         int pow = productUnit.getUnitPow(i);
/* 270 */         if (pow >= 0) {
/* 271 */           formatExponent(productUnit.getUnit(i), pow, productUnit.getUnitRoot(i), !start, buffer);
/* 272 */           start = false;
/*     */         } else {
/* 274 */           negativeExponentCount++;
/*     */         } 
/*     */       } 
/* 278 */       if (negativeExponentCount > 0) {
/* 279 */         if (start)
/* 280 */           buffer.append('1'); 
/* 282 */         buffer.append('/');
/* 283 */         if (negativeExponentCount > 1)
/* 284 */           buffer.append('('); 
/* 286 */         start = true;
/* 287 */         for (i = 0; i < productUnit.getUnitCount(); i++) {
/* 288 */           int pow = productUnit.getUnitPow(i);
/* 289 */           if (pow < 0) {
/* 290 */             formatExponent(productUnit.getUnit(i), -pow, productUnit.getUnitRoot(i), !start, buffer);
/* 291 */             start = false;
/*     */           } 
/*     */         } 
/* 294 */         if (negativeExponentCount > 1)
/* 295 */           buffer.append(')'); 
/*     */       } 
/* 298 */       return 2;
/*     */     } 
/* 299 */     if (unit instanceof TransformedUnit || unit.equals(SI.KILOGRAM)) {
/* 300 */       UnitConverter converter = null;
/* 301 */       boolean printSeparator = false;
/* 302 */       StringBuffer temp = new StringBuffer();
/* 303 */       int unitPrecedence = Integer.MAX_VALUE;
/* 304 */       if (unit.equals(SI.KILOGRAM)) {
/* 307 */         converter = Prefix.KILO.getConverter();
/* 308 */         unitPrecedence = formatInternal(SI.GRAM, temp);
/* 309 */         printSeparator = true;
/*     */       } else {
/* 311 */         TransformedUnit<?> transformedUnit = (TransformedUnit)unit;
/* 312 */         Unit<?> parentUnits = transformedUnit.getParentUnit();
/* 313 */         converter = transformedUnit.toParentUnit();
/* 314 */         if (parentUnits.equals(SI.KILOGRAM)) {
/* 317 */           parentUnits = SI.GRAM;
/* 318 */           converter = converter.concatenate(Prefix.KILO.getConverter());
/*     */         } 
/* 320 */         unitPrecedence = formatInternal(parentUnits, temp);
/* 321 */         printSeparator = !parentUnits.equals(Unit.ONE);
/*     */       } 
/* 323 */       int result = formatConverter(converter, printSeparator, unitPrecedence, temp);
/* 324 */       buffer.append(temp);
/* 325 */       return result;
/*     */     } 
/* 326 */     if (unit instanceof AlternateUnit) {
/* 327 */       buffer.append(((AlternateUnit)unit).getSymbol());
/* 328 */       return Integer.MAX_VALUE;
/*     */     } 
/* 329 */     if (unit instanceof BaseUnit) {
/* 330 */       buffer.append(((BaseUnit)unit).getSymbol());
/* 331 */       return Integer.MAX_VALUE;
/*     */     } 
/* 333 */     throw new IllegalArgumentException("Cannot format the given Object as a Unit (unsupported unit type " + unit.getClass().getName() + ")");
/*     */   }
/*     */   
/*     */   private void formatExponent(Unit<?> unit, int pow, int root, boolean continued, Appendable buffer) throws IOException {
/* 350 */     if (continued)
/* 351 */       buffer.append('·'); 
/* 353 */     StringBuffer temp = new StringBuffer();
/* 354 */     int unitPrecedence = formatInternal(unit, temp);
/* 355 */     if (unitPrecedence < 2) {
/* 356 */       temp.insert(0, '(');
/* 357 */       temp.append(')');
/*     */     } 
/* 359 */     buffer.append(temp);
/* 360 */     if (root != 1 || pow != 1)
/* 362 */       if (root == 1 && pow > 1) {
/* 363 */         String powStr = Integer.toString(pow);
/* 364 */         for (int i = 0; i < powStr.length(); i++) {
/* 365 */           char c = powStr.charAt(i);
/* 366 */           switch (c) {
/*     */             case '0':
/* 368 */               buffer.append('⁰');
/*     */               break;
/*     */             case '1':
/* 371 */               buffer.append('¹');
/*     */               break;
/*     */             case '2':
/* 374 */               buffer.append('²');
/*     */               break;
/*     */             case '3':
/* 377 */               buffer.append('³');
/*     */               break;
/*     */             case '4':
/* 380 */               buffer.append('⁴');
/*     */               break;
/*     */             case '5':
/* 383 */               buffer.append('⁵');
/*     */               break;
/*     */             case '6':
/* 386 */               buffer.append('⁶');
/*     */               break;
/*     */             case '7':
/* 389 */               buffer.append('⁷');
/*     */               break;
/*     */             case '8':
/* 392 */               buffer.append('⁸');
/*     */               break;
/*     */             case '9':
/* 395 */               buffer.append('⁹');
/*     */               break;
/*     */           } 
/*     */         } 
/* 399 */       } else if (root == 1) {
/* 400 */         buffer.append("^");
/* 401 */         buffer.append(String.valueOf(pow));
/*     */       } else {
/* 403 */         buffer.append("^(");
/* 404 */         buffer.append(String.valueOf(pow));
/* 405 */         buffer.append('/');
/* 406 */         buffer.append(String.valueOf(root));
/* 407 */         buffer.append(')');
/*     */       }  
/*     */   }
/*     */   
/*     */   private int formatConverter(UnitConverter converter, boolean continued, int unitPrecedence, StringBuffer buffer) {
/* 430 */     Prefix prefix = this._symbolMap.getPrefix(converter);
/* 431 */     if (prefix != null && unitPrecedence == Integer.MAX_VALUE) {
/* 432 */       buffer.insert(0, this._symbolMap.getSymbol(prefix));
/* 433 */       return Integer.MAX_VALUE;
/*     */     } 
/* 434 */     if (converter instanceof AddConverter) {
/* 435 */       if (unitPrecedence < 0) {
/* 436 */         buffer.insert(0, '(');
/* 437 */         buffer.append(')');
/*     */       } 
/* 439 */       double offset = ((AddConverter)converter).getOffset();
/* 440 */       if (offset < 0.0D) {
/* 441 */         buffer.append("-");
/* 442 */         offset = -offset;
/* 443 */       } else if (continued) {
/* 444 */         buffer.append("+");
/*     */       } 
/* 446 */       long lOffset = (long)offset;
/* 447 */       if (lOffset == offset) {
/* 448 */         buffer.append(lOffset);
/*     */       } else {
/* 450 */         buffer.append(offset);
/*     */       } 
/* 452 */       return 0;
/*     */     } 
/* 453 */     if (converter instanceof LogConverter) {
/* 454 */       double base = ((LogConverter)converter).getBase();
/* 455 */       StringBuffer expr = new StringBuffer();
/* 456 */       if (base == Math.E) {
/* 457 */         expr.append("ln");
/*     */       } else {
/* 459 */         expr.append("log");
/* 460 */         if (base != 10.0D)
/* 461 */           expr.append((int)base); 
/*     */       } 
/* 464 */       expr.append("(");
/* 465 */       buffer.insert(0, expr);
/* 466 */       buffer.append(")");
/* 467 */       return 4;
/*     */     } 
/* 468 */     if (converter instanceof ExpConverter) {
/* 469 */       if (unitPrecedence < 4) {
/* 470 */         buffer.insert(0, '(');
/* 471 */         buffer.append(')');
/*     */       } 
/* 473 */       StringBuffer expr = new StringBuffer();
/* 474 */       double base = ((ExpConverter)converter).getBase();
/* 475 */       if (base == Math.E) {
/* 476 */         expr.append('e');
/*     */       } else {
/* 478 */         expr.append((int)base);
/*     */       } 
/* 480 */       expr.append('^');
/* 481 */       buffer.insert(0, expr);
/* 482 */       return 4;
/*     */     } 
/* 483 */     if (converter instanceof MultiplyConverter) {
/* 484 */       if (unitPrecedence < 2) {
/* 485 */         buffer.insert(0, '(');
/* 486 */         buffer.append(')');
/*     */       } 
/* 488 */       if (continued)
/* 489 */         buffer.append('·'); 
/* 491 */       double factor = ((MultiplyConverter)converter).getFactor();
/* 492 */       long lFactor = (long)factor;
/* 493 */       if (lFactor == factor) {
/* 494 */         buffer.append(lFactor);
/*     */       } else {
/* 496 */         buffer.append(factor);
/*     */       } 
/* 498 */       return 2;
/*     */     } 
/* 499 */     if (converter instanceof RationalConverter) {
/* 500 */       if (unitPrecedence < 2) {
/* 501 */         buffer.insert(0, '(');
/* 502 */         buffer.append(')');
/*     */       } 
/* 504 */       RationalConverter rationalConverter = (RationalConverter)converter;
/* 505 */       if (!rationalConverter.getDividend().equals(BigInteger.ONE)) {
/* 506 */         if (continued)
/* 507 */           buffer.append('·'); 
/* 509 */         buffer.append(rationalConverter.getDividend());
/*     */       } 
/* 511 */       if (!rationalConverter.getDivisor().equals(BigInteger.ONE)) {
/* 512 */         buffer.append('/');
/* 513 */         buffer.append(rationalConverter.getDivisor());
/*     */       } 
/* 515 */       return 2;
/*     */     } 
/* 517 */     throw new IllegalArgumentException("Unable to format the given UnitConverter: " + converter.getClass());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\LocalFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */