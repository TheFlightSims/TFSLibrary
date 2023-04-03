/*     */ package org.apache.commons.lang.text;
/*     */ 
/*     */ import java.text.Format;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.ObjectUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ 
/*     */ public class ExtendedMessageFormat extends MessageFormat {
/*     */   private static final long serialVersionUID = -2362048321261811743L;
/*     */   
/*     */   private static final int HASH_SEED = 31;
/*     */   
/*     */   private static final String DUMMY_PATTERN = "";
/*     */   
/*     */   private static final String ESCAPED_QUOTE = "''";
/*     */   
/*     */   private static final char START_FMT = ',';
/*     */   
/*     */   private static final char END_FE = '}';
/*     */   
/*     */   private static final char START_FE = '{';
/*     */   
/*     */   private static final char QUOTE = '\'';
/*     */   
/*     */   private String toPattern;
/*     */   
/*     */   private final Map registry;
/*     */   
/*     */   public ExtendedMessageFormat(String pattern) {
/*  92 */     this(pattern, Locale.getDefault());
/*     */   }
/*     */   
/*     */   public ExtendedMessageFormat(String pattern, Locale locale) {
/* 103 */     this(pattern, locale, (Map)null);
/*     */   }
/*     */   
/*     */   public ExtendedMessageFormat(String pattern, Map registry) {
/* 114 */     this(pattern, Locale.getDefault(), registry);
/*     */   }
/*     */   
/*     */   public ExtendedMessageFormat(String pattern, Locale locale, Map registry) {
/* 126 */     super("");
/* 127 */     setLocale(locale);
/* 128 */     this.registry = registry;
/* 129 */     applyPattern(pattern);
/*     */   }
/*     */   
/*     */   public String toPattern() {
/* 136 */     return this.toPattern;
/*     */   }
/*     */   
/*     */   public final void applyPattern(String pattern) {
/* 145 */     if (this.registry == null) {
/* 146 */       super.applyPattern(pattern);
/* 147 */       this.toPattern = super.toPattern();
/*     */       return;
/*     */     } 
/* 150 */     ArrayList foundFormats = new ArrayList();
/* 151 */     ArrayList foundDescriptions = new ArrayList();
/* 152 */     StrBuilder stripCustom = new StrBuilder(pattern.length());
/* 154 */     ParsePosition pos = new ParsePosition(0);
/* 155 */     char[] c = pattern.toCharArray();
/* 156 */     int fmtCount = 0;
/* 157 */     while (pos.getIndex() < pattern.length()) {
/*     */       int start, index;
/*     */       Format format;
/*     */       String formatDescription;
/* 158 */       switch (c[pos.getIndex()]) {
/*     */         case '\'':
/* 160 */           appendQuotedString(pattern, pos, stripCustom, true);
/*     */           continue;
/*     */         case '{':
/* 163 */           fmtCount++;
/* 164 */           seekNonWs(pattern, pos);
/* 165 */           start = pos.getIndex();
/* 166 */           index = readArgumentIndex(pattern, next(pos));
/* 167 */           stripCustom.append('{').append(index);
/* 168 */           seekNonWs(pattern, pos);
/* 169 */           format = null;
/* 170 */           formatDescription = null;
/* 171 */           if (c[pos.getIndex()] == ',') {
/* 172 */             formatDescription = parseFormatDescription(pattern, next(pos));
/* 174 */             format = getFormat(formatDescription);
/* 175 */             if (format == null)
/* 176 */               stripCustom.append(',').append(formatDescription); 
/*     */           } 
/* 179 */           foundFormats.add(format);
/* 180 */           foundDescriptions.add((format == null) ? null : formatDescription);
/* 181 */           Validate.isTrue((foundFormats.size() == fmtCount));
/* 182 */           Validate.isTrue((foundDescriptions.size() == fmtCount));
/* 183 */           if (c[pos.getIndex()] != '}')
/* 184 */             throw new IllegalArgumentException("Unreadable format element at position " + start); 
/*     */           break;
/*     */       } 
/* 189 */       stripCustom.append(c[pos.getIndex()]);
/* 190 */       next(pos);
/*     */     } 
/* 193 */     super.applyPattern(stripCustom.toString());
/* 194 */     this.toPattern = insertFormats(super.toPattern(), foundDescriptions);
/* 195 */     if (containsElements(foundFormats)) {
/* 196 */       Format[] origFormats = getFormats();
/* 199 */       int i = 0;
/* 200 */       for (Iterator it = foundFormats.iterator(); it.hasNext(); i++) {
/* 201 */         Format f = it.next();
/* 202 */         if (f != null)
/* 203 */           origFormats[i] = f; 
/*     */       } 
/* 206 */       super.setFormats(origFormats);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFormat(int formatElementIndex, Format newFormat) {
/* 218 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void setFormatByArgumentIndex(int argumentIndex, Format newFormat) {
/* 229 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void setFormats(Format[] newFormats) {
/* 239 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void setFormatsByArgumentIndex(Format[] newFormats) {
/* 249 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 260 */     if (obj == this)
/* 261 */       return true; 
/* 263 */     if (obj == null)
/* 264 */       return false; 
/* 266 */     if (!super.equals(obj))
/* 267 */       return false; 
/* 269 */     if (ObjectUtils.notEqual(getClass(), obj.getClass()))
/* 270 */       return false; 
/* 272 */     ExtendedMessageFormat rhs = (ExtendedMessageFormat)obj;
/* 273 */     if (ObjectUtils.notEqual(this.toPattern, rhs.toPattern))
/* 274 */       return false; 
/* 276 */     if (ObjectUtils.notEqual(this.registry, rhs.registry))
/* 277 */       return false; 
/* 279 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 289 */     int result = super.hashCode();
/* 290 */     result = 31 * result + ObjectUtils.hashCode(this.registry);
/* 291 */     result = 31 * result + ObjectUtils.hashCode(this.toPattern);
/* 292 */     return result;
/*     */   }
/*     */   
/*     */   private Format getFormat(String desc) {
/* 302 */     if (this.registry != null) {
/* 303 */       String name = desc;
/* 304 */       String args = null;
/* 305 */       int i = desc.indexOf(',');
/* 306 */       if (i > 0) {
/* 307 */         name = desc.substring(0, i).trim();
/* 308 */         args = desc.substring(i + 1).trim();
/*     */       } 
/* 310 */       FormatFactory factory = (FormatFactory)this.registry.get(name);
/* 311 */       if (factory != null)
/* 312 */         return factory.getFormat(name, args, getLocale()); 
/*     */     } 
/* 315 */     return null;
/*     */   }
/*     */   
/*     */   private int readArgumentIndex(String pattern, ParsePosition pos) {
/* 326 */     int start = pos.getIndex();
/* 327 */     seekNonWs(pattern, pos);
/* 328 */     StrBuilder result = new StrBuilder();
/* 329 */     boolean error = false;
/* 330 */     for (; !error && pos.getIndex() < pattern.length(); next(pos)) {
/* 331 */       char c = pattern.charAt(pos.getIndex());
/* 332 */       if (Character.isWhitespace(c)) {
/* 333 */         seekNonWs(pattern, pos);
/* 334 */         c = pattern.charAt(pos.getIndex());
/* 335 */         if (c != ',' && c != '}') {
/* 336 */           error = true;
/*     */           continue;
/*     */         } 
/*     */       } 
/* 340 */       if ((c == ',' || c == '}') && result.length() > 0)
/*     */         try {
/* 342 */           return Integer.parseInt(result.toString());
/* 343 */         } catch (NumberFormatException e) {} 
/* 348 */       error = !Character.isDigit(c);
/* 349 */       result.append(c);
/*     */       continue;
/*     */     } 
/* 351 */     if (error)
/* 352 */       throw new IllegalArgumentException("Invalid format argument index at position " + start + ": " + pattern.substring(start, pos.getIndex())); 
/* 356 */     throw new IllegalArgumentException("Unterminated format element at position " + start);
/*     */   }
/*     */   
/*     */   private String parseFormatDescription(String pattern, ParsePosition pos) {
/* 368 */     int start = pos.getIndex();
/* 369 */     seekNonWs(pattern, pos);
/* 370 */     int text = pos.getIndex();
/* 371 */     int depth = 1;
/* 372 */     for (; pos.getIndex() < pattern.length(); next(pos)) {
/* 373 */       switch (pattern.charAt(pos.getIndex())) {
/*     */         case '{':
/* 375 */           depth++;
/*     */           break;
/*     */         case '}':
/* 378 */           depth--;
/* 379 */           if (depth == 0)
/* 380 */             return pattern.substring(text, pos.getIndex()); 
/*     */           break;
/*     */         case '\'':
/* 384 */           getQuotedString(pattern, pos, false);
/*     */           break;
/*     */       } 
/*     */     } 
/* 388 */     throw new IllegalArgumentException("Unterminated format element at position " + start);
/*     */   }
/*     */   
/*     */   private String insertFormats(String pattern, ArrayList customPatterns) {
/* 400 */     if (!containsElements(customPatterns))
/* 401 */       return pattern; 
/* 403 */     StrBuilder sb = new StrBuilder(pattern.length() * 2);
/* 404 */     ParsePosition pos = new ParsePosition(0);
/* 405 */     int fe = -1;
/* 406 */     int depth = 0;
/* 407 */     while (pos.getIndex() < pattern.length()) {
/* 408 */       char c = pattern.charAt(pos.getIndex());
/* 409 */       switch (c) {
/*     */         case '\'':
/* 411 */           appendQuotedString(pattern, pos, sb, false);
/*     */           continue;
/*     */         case '{':
/* 414 */           depth++;
/* 415 */           if (depth == 1) {
/* 416 */             fe++;
/* 417 */             sb.append('{').append(readArgumentIndex(pattern, next(pos)));
/* 419 */             String customPattern = customPatterns.get(fe);
/* 420 */             if (customPattern != null)
/* 421 */               sb.append(',').append(customPattern); 
/*     */           } 
/*     */           continue;
/*     */         case '}':
/* 426 */           depth--;
/*     */           break;
/*     */       } 
/* 429 */       sb.append(c);
/* 430 */       next(pos);
/*     */     } 
/* 433 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private void seekNonWs(String pattern, ParsePosition pos) {
/* 443 */     int len = 0;
/* 444 */     char[] buffer = pattern.toCharArray();
/*     */     do {
/* 446 */       len = StrMatcher.splitMatcher().isMatch(buffer, pos.getIndex());
/* 447 */       pos.setIndex(pos.getIndex() + len);
/* 448 */     } while (len > 0 && pos.getIndex() < pattern.length());
/*     */   }
/*     */   
/*     */   private ParsePosition next(ParsePosition pos) {
/* 458 */     pos.setIndex(pos.getIndex() + 1);
/* 459 */     return pos;
/*     */   }
/*     */   
/*     */   private StrBuilder appendQuotedString(String pattern, ParsePosition pos, StrBuilder appendTo, boolean escapingOn) {
/* 474 */     int start = pos.getIndex();
/* 475 */     char[] c = pattern.toCharArray();
/* 476 */     if (escapingOn && c[start] == '\'') {
/* 477 */       next(pos);
/* 478 */       return (appendTo == null) ? null : appendTo.append('\'');
/*     */     } 
/* 480 */     int lastHold = start;
/* 481 */     for (int i = pos.getIndex(); i < pattern.length(); i++) {
/* 482 */       if (escapingOn && pattern.substring(i).startsWith("''")) {
/* 483 */         appendTo.append(c, lastHold, pos.getIndex() - lastHold).append('\'');
/* 485 */         pos.setIndex(i + "''".length());
/* 486 */         lastHold = pos.getIndex();
/*     */       } else {
/* 489 */         switch (c[pos.getIndex()]) {
/*     */           case '\'':
/* 491 */             next(pos);
/* 492 */             return (appendTo == null) ? null : appendTo.append(c, lastHold, pos.getIndex() - lastHold);
/*     */         } 
/* 495 */         next(pos);
/*     */       } 
/*     */     } 
/* 498 */     throw new IllegalArgumentException("Unterminated quoted string at position " + start);
/*     */   }
/*     */   
/*     */   private void getQuotedString(String pattern, ParsePosition pos, boolean escapingOn) {
/* 511 */     appendQuotedString(pattern, pos, (StrBuilder)null, escapingOn);
/*     */   }
/*     */   
/*     */   private boolean containsElements(Collection coll) {
/* 520 */     if (coll == null || coll.size() == 0)
/* 521 */       return false; 
/* 523 */     for (Iterator iter = coll.iterator(); iter.hasNext();) {
/* 524 */       if (iter.next() != null)
/* 525 */         return true; 
/*     */     } 
/* 528 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\text\ExtendedMessageFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */