/*     */ package org.geotools.referencing.wkt;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.logging.LoggedFormat;
/*     */ 
/*     */ public final class Element {
/*     */   private final int offset;
/*     */   
/*     */   public final String keyword;
/*     */   
/*     */   private final List<Object> list;
/*     */   
/*     */   Element(Element singleton) {
/*  77 */     this.offset = 0;
/*  78 */     this.keyword = null;
/*  79 */     this.list = new LinkedList();
/*  80 */     this.list.add(singleton);
/*     */   }
/*     */   
/*     */   Element(AbstractParser parser, String text, ParsePosition position) throws ParseException {
/*  97 */     int lower = position.getIndex();
/*  98 */     int length = text.length();
/*  99 */     while (lower < length && Character.isWhitespace(text.charAt(lower)))
/* 100 */       lower++; 
/* 102 */     this.offset = lower;
/* 103 */     int upper = lower;
/* 104 */     while (upper < length && Character.isUnicodeIdentifierPart(text.charAt(upper)))
/* 105 */       upper++; 
/* 107 */     if (upper <= lower) {
/* 108 */       position.setErrorIndex(lower);
/* 109 */       throw unparsableString(text, position);
/*     */     } 
/* 111 */     this.keyword = text.substring(lower, upper).toUpperCase(parser.symbols.locale);
/* 112 */     position.setIndex(upper);
/* 119 */     int bracketIndex = -1;
/*     */     while (true) {
/* 121 */       if (++bracketIndex >= parser.symbols.openingBrackets.length) {
/* 122 */         this.list = null;
/*     */         return;
/*     */       } 
/* 126 */       if (parseOptionalSeparator(text, position, parser.symbols.openingBrackets[bracketIndex])) {
/* 127 */         this.list = new LinkedList();
/*     */         while (true) {
/* 138 */           if (position.getIndex() >= length)
/* 139 */             throw missingCharacter(parser.symbols.close, length); 
/* 145 */           parser.symbols.getClass();
/* 145 */           if (parseOptionalSeparator(text, position, '"')) {
/* 146 */             lower = position.getIndex();
/* 147 */             parser.symbols.getClass();
/* 147 */             upper = text.indexOf('"', lower);
/* 148 */             if (upper < lower) {
/* 149 */               position.setErrorIndex(++lower);
/* 150 */               parser.symbols.getClass();
/* 150 */               throw missingCharacter('"', lower);
/*     */             } 
/* 152 */             this.list.add(text.substring(lower, upper).trim());
/* 153 */             position.setIndex(upper + 1);
/*     */           } else {
/* 160 */             lower = position.getIndex();
/* 161 */             if (!Character.isUnicodeIdentifierStart(text.charAt(lower))) {
/* 162 */               Number number = parser.parseNumber(text, position);
/* 163 */               if (number == null)
/* 165 */                 throw unparsableString(text, position); 
/* 167 */               this.list.add(number);
/*     */             } else {
/* 171 */               this.list.add(new Element(parser, text, position));
/*     */             } 
/*     */           } 
/* 172 */           if (!parseOptionalSeparator(text, position, parser.symbols.separator)) {
/* 173 */             parseSeparator(text, position, parser.symbols.closingBrackets[bracketIndex]);
/*     */             return;
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean parseOptionalSeparator(String text, ParsePosition position, char separator) {
/* 193 */     int length = text.length();
/* 194 */     int index = position.getIndex();
/* 195 */     while (index < length) {
/* 196 */       char c = text.charAt(index);
/* 197 */       if (Character.isWhitespace(c)) {
/* 198 */         index++;
/*     */         continue;
/*     */       } 
/* 201 */       if (c == separator) {
/* 202 */         position.setIndex(++index);
/* 203 */         return true;
/*     */       } 
/*     */     } 
/* 207 */     position.setIndex(index);
/* 208 */     return false;
/*     */   }
/*     */   
/*     */   private void parseSeparator(String text, ParsePosition position, char separator) throws ParseException {
/* 227 */     if (!parseOptionalSeparator(text, position, separator)) {
/* 228 */       position.setErrorIndex(position.getIndex());
/* 229 */       throw unparsableString(text, position);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParseException parseFailed(Exception cause, String message) {
/* 252 */     if (message == null)
/* 253 */       message = cause.getLocalizedMessage(); 
/* 255 */     ParseException exception = new ParseException(complete(message), this.offset);
/* 256 */     exception = trim("parseFailed", exception);
/* 257 */     exception.initCause(cause);
/* 258 */     return exception;
/*     */   }
/*     */   
/*     */   private ParseException unparsableString(String text, ParsePosition position) {
/* 272 */     int errorIndex = position.getErrorIndex();
/* 273 */     String message = LoggedFormat.formatUnparsable(text, position.getIndex(), errorIndex, null);
/* 274 */     message = complete(message);
/* 275 */     return trim("unparsableString", new ParseException(message, errorIndex));
/*     */   }
/*     */   
/*     */   private ParseException missingCharacter(char c, int position) {
/* 285 */     return trim("missingCharacter", new ParseException(complete(Errors.format(97, Character.valueOf(c))), position));
/*     */   }
/*     */   
/*     */   private ParseException missingParameter(String key) {
/* 295 */     int error = this.offset;
/* 296 */     if (this.keyword != null)
/* 297 */       error += this.keyword.length(); 
/* 299 */     return trim("missingParameter", new ParseException(complete(Errors.format(99, key)), error));
/*     */   }
/*     */   
/*     */   private String complete(String message) {
/* 310 */     if (this.keyword != null)
/* 311 */       message = Errors.format(84, this.keyword) + ' ' + message; 
/* 313 */     return message;
/*     */   }
/*     */   
/*     */   private static ParseException trim(String factory, ParseException exception) {
/* 326 */     StackTraceElement[] trace = exception.getStackTrace();
/* 327 */     if (trace != null && trace.length != 0 && 
/* 328 */       factory.equals(trace[0].getMethodName())) {
/* 329 */       trace = (StackTraceElement[])XArray.remove((Object[])trace, 0, 1);
/* 330 */       exception.setStackTrace(trace);
/*     */     } 
/* 333 */     return exception;
/*     */   }
/*     */   
/*     */   public boolean isRoot() {
/* 346 */     return (this.offset == 0);
/*     */   }
/*     */   
/*     */   public double pullDouble(String key) throws ParseException {
/* 366 */     Iterator iterator = this.list.iterator();
/* 367 */     while (iterator.hasNext()) {
/* 368 */       Object object = iterator.next();
/* 369 */       if (object instanceof Number) {
/* 370 */         iterator.remove();
/* 371 */         return ((Number)object).doubleValue();
/*     */       } 
/*     */     } 
/* 374 */     throw missingParameter(key);
/*     */   }
/*     */   
/*     */   public int pullInteger(String key) throws ParseException {
/* 388 */     Iterator iterator = this.list.iterator();
/* 389 */     while (iterator.hasNext()) {
/* 390 */       Object object = iterator.next();
/* 391 */       if (object instanceof Number) {
/* 392 */         iterator.remove();
/* 393 */         Number number = (Number)object;
/* 394 */         if (number instanceof Float || number instanceof Double)
/* 395 */           throw new ParseException(complete(Errors.format(58, key, number)), this.offset); 
/* 398 */         return number.intValue();
/*     */       } 
/*     */     } 
/* 401 */     throw missingParameter(key);
/*     */   }
/*     */   
/*     */   public String pullString(String key) throws ParseException {
/* 413 */     String optionalString = pullOptionalString(key);
/* 414 */     if (optionalString != null)
/* 415 */       return optionalString; 
/* 417 */     throw missingParameter(key);
/*     */   }
/*     */   
/*     */   public String pullOptionalString(String key) {
/* 428 */     Iterator iterator = this.list.iterator();
/* 429 */     while (iterator.hasNext()) {
/* 430 */       Object object = iterator.next();
/* 431 */       if (object instanceof String) {
/* 432 */         iterator.remove();
/* 433 */         return (String)object;
/*     */       } 
/*     */     } 
/* 436 */     return null;
/*     */   }
/*     */   
/*     */   public Element pullElement(String key) throws ParseException {
/* 447 */     Element element = pullOptionalElement(key);
/* 448 */     if (element != null)
/* 449 */       return element; 
/* 451 */     throw missingParameter(key);
/*     */   }
/*     */   
/*     */   public Element pullOptionalElement(String key) {
/* 462 */     key = key.toUpperCase();
/* 463 */     Iterator iterator = this.list.iterator();
/* 464 */     while (iterator.hasNext()) {
/* 465 */       Object object = iterator.next();
/* 466 */       if (object instanceof Element) {
/* 467 */         Element element = (Element)object;
/* 468 */         if (element.list != null && element.keyword.equals(key)) {
/* 469 */           iterator.remove();
/* 470 */           return element;
/*     */         } 
/*     */       } 
/*     */     } 
/* 474 */     return null;
/*     */   }
/*     */   
/*     */   public Element pullVoidElement(String key) throws ParseException {
/* 486 */     Iterator iterator = this.list.iterator();
/* 487 */     while (iterator.hasNext()) {
/* 488 */       Object object = iterator.next();
/* 489 */       if (object instanceof Element) {
/* 490 */         Element element = (Element)object;
/* 491 */         if (element.list == null) {
/* 492 */           iterator.remove();
/* 493 */           return element;
/*     */         } 
/*     */       } 
/*     */     } 
/* 497 */     throw missingParameter(key);
/*     */   }
/*     */   
/*     */   public Element pullOptionalVoidElement() throws ParseException {
/* 508 */     Iterator iterator = this.list.iterator();
/* 509 */     while (iterator.hasNext()) {
/* 510 */       Object object = iterator.next();
/* 511 */       if (object instanceof Element) {
/* 512 */         Element element = (Element)object;
/* 513 */         if (element.list == null) {
/* 514 */           iterator.remove();
/* 515 */           return element;
/*     */         } 
/*     */       } 
/*     */     } 
/* 519 */     return null;
/*     */   }
/*     */   
/*     */   public Object peek() {
/* 529 */     return this.list.isEmpty() ? null : this.list.get(0);
/*     */   }
/*     */   
/*     */   public void close() throws ParseException {
/* 538 */     if (this.list != null && !this.list.isEmpty())
/* 539 */       throw new ParseException(complete(Errors.format(176, this.list.get(0))), this.offset + this.keyword.length()); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 550 */     return this.keyword;
/*     */   }
/*     */   
/*     */   public void print(PrintWriter out, int level) {
/* 561 */     int tabWidth = 4;
/* 562 */     out.print(Utilities.spaces(4 * level));
/* 563 */     out.println(this.keyword);
/* 564 */     if (this.list == null)
/*     */       return; 
/* 567 */     int size = this.list.size();
/* 568 */     for (int j = 0; j < size; j++) {
/* 569 */       Object object = this.list.get(j);
/* 570 */       if (object instanceof Element) {
/* 571 */         ((Element)object).print(out, level + 1);
/*     */       } else {
/* 573 */         out.print(Utilities.spaces(4 * (level + 1)));
/* 574 */         out.println(object);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\Element.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */