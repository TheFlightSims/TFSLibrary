/*     */ package org.apache.commons.lang.time;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.lang.text.StrBuilder;
/*     */ 
/*     */ public class DurationFormatUtils {
/*     */   public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'";
/*     */   
/*     */   public static String formatDurationHMS(long durationMillis) {
/*  82 */     return formatDuration(durationMillis, "H:mm:ss.SSS");
/*     */   }
/*     */   
/*     */   public static String formatDurationISO(long durationMillis) {
/*  97 */     return formatDuration(durationMillis, "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'", false);
/*     */   }
/*     */   
/*     */   public static String formatDuration(long durationMillis, String format) {
/* 112 */     return formatDuration(durationMillis, format, true);
/*     */   }
/*     */   
/*     */   public static String formatDuration(long durationMillis, String format, boolean padWithZeros) {
/* 130 */     Token[] tokens = lexx(format);
/* 132 */     int days = 0;
/* 133 */     int hours = 0;
/* 134 */     int minutes = 0;
/* 135 */     int seconds = 0;
/* 136 */     int milliseconds = 0;
/* 138 */     if (Token.containsTokenWithValue(tokens, d)) {
/* 139 */       days = (int)(durationMillis / 86400000L);
/* 140 */       durationMillis -= days * 86400000L;
/*     */     } 
/* 142 */     if (Token.containsTokenWithValue(tokens, H)) {
/* 143 */       hours = (int)(durationMillis / 3600000L);
/* 144 */       durationMillis -= hours * 3600000L;
/*     */     } 
/* 146 */     if (Token.containsTokenWithValue(tokens, m)) {
/* 147 */       minutes = (int)(durationMillis / 60000L);
/* 148 */       durationMillis -= minutes * 60000L;
/*     */     } 
/* 150 */     if (Token.containsTokenWithValue(tokens, s)) {
/* 151 */       seconds = (int)(durationMillis / 1000L);
/* 152 */       durationMillis -= seconds * 1000L;
/*     */     } 
/* 154 */     if (Token.containsTokenWithValue(tokens, S))
/* 155 */       milliseconds = (int)durationMillis; 
/* 158 */     return format(tokens, 0, 0, days, hours, minutes, seconds, milliseconds, padWithZeros);
/*     */   }
/*     */   
/*     */   public static String formatDurationWords(long durationMillis, boolean suppressLeadingZeroElements, boolean suppressTrailingZeroElements) {
/* 180 */     String duration = formatDuration(durationMillis, "d' days 'H' hours 'm' minutes 's' seconds'");
/* 181 */     if (suppressLeadingZeroElements) {
/* 183 */       duration = " " + duration;
/* 184 */       String tmp = StringUtils.replaceOnce(duration, " 0 days", "");
/* 185 */       if (tmp.length() != duration.length()) {
/* 186 */         duration = tmp;
/* 187 */         tmp = StringUtils.replaceOnce(duration, " 0 hours", "");
/* 188 */         if (tmp.length() != duration.length()) {
/* 189 */           duration = tmp;
/* 190 */           tmp = StringUtils.replaceOnce(duration, " 0 minutes", "");
/* 191 */           duration = tmp;
/* 192 */           if (tmp.length() != duration.length())
/* 193 */             duration = StringUtils.replaceOnce(tmp, " 0 seconds", ""); 
/*     */         } 
/*     */       } 
/* 197 */       if (duration.length() != 0)
/* 199 */         duration = duration.substring(1); 
/*     */     } 
/* 202 */     if (suppressTrailingZeroElements) {
/* 203 */       String tmp = StringUtils.replaceOnce(duration, " 0 seconds", "");
/* 204 */       if (tmp.length() != duration.length()) {
/* 205 */         duration = tmp;
/* 206 */         tmp = StringUtils.replaceOnce(duration, " 0 minutes", "");
/* 207 */         if (tmp.length() != duration.length()) {
/* 208 */           duration = tmp;
/* 209 */           tmp = StringUtils.replaceOnce(duration, " 0 hours", "");
/* 210 */           if (tmp.length() != duration.length())
/* 211 */             duration = StringUtils.replaceOnce(tmp, " 0 days", ""); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 217 */     duration = " " + duration;
/* 218 */     duration = StringUtils.replaceOnce(duration, " 1 seconds", " 1 second");
/* 219 */     duration = StringUtils.replaceOnce(duration, " 1 minutes", " 1 minute");
/* 220 */     duration = StringUtils.replaceOnce(duration, " 1 hours", " 1 hour");
/* 221 */     duration = StringUtils.replaceOnce(duration, " 1 days", " 1 day");
/* 222 */     return duration.trim();
/*     */   }
/*     */   
/*     */   public static String formatPeriodISO(long startMillis, long endMillis) {
/* 236 */     return formatPeriod(startMillis, endMillis, "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'", false, TimeZone.getDefault());
/*     */   }
/*     */   
/*     */   public static String formatPeriod(long startMillis, long endMillis, String format) {
/* 249 */     return formatPeriod(startMillis, endMillis, format, true, TimeZone.getDefault());
/*     */   }
/*     */   
/*     */   public static String formatPeriod(long startMillis, long endMillis, String format, boolean padWithZeros, TimeZone timezone) {
/* 284 */     Token[] tokens = lexx(format);
/* 288 */     Calendar start = Calendar.getInstance(timezone);
/* 289 */     start.setTime(new Date(startMillis));
/* 290 */     Calendar end = Calendar.getInstance(timezone);
/* 291 */     end.setTime(new Date(endMillis));
/* 294 */     int milliseconds = end.get(14) - start.get(14);
/* 295 */     int seconds = end.get(13) - start.get(13);
/* 296 */     int minutes = end.get(12) - start.get(12);
/* 297 */     int hours = end.get(11) - start.get(11);
/* 298 */     int days = end.get(5) - start.get(5);
/* 299 */     int months = end.get(2) - start.get(2);
/* 300 */     int years = end.get(1) - start.get(1);
/* 303 */     while (milliseconds < 0) {
/* 304 */       milliseconds += 1000;
/* 305 */       seconds--;
/*     */     } 
/* 307 */     while (seconds < 0) {
/* 308 */       seconds += 60;
/* 309 */       minutes--;
/*     */     } 
/* 311 */     while (minutes < 0) {
/* 312 */       minutes += 60;
/* 313 */       hours--;
/*     */     } 
/* 315 */     while (hours < 0) {
/* 316 */       hours += 24;
/* 317 */       days--;
/*     */     } 
/* 320 */     if (Token.containsTokenWithValue(tokens, M)) {
/* 321 */       while (days < 0) {
/* 322 */         days += start.getActualMaximum(5);
/* 323 */         months--;
/* 324 */         start.add(2, 1);
/*     */       } 
/* 327 */       while (months < 0) {
/* 328 */         months += 12;
/* 329 */         years--;
/*     */       } 
/* 332 */       if (!Token.containsTokenWithValue(tokens, y) && years != 0)
/* 333 */         while (years != 0) {
/* 334 */           months += 12 * years;
/* 335 */           years = 0;
/*     */         }  
/*     */     } else {
/* 341 */       if (!Token.containsTokenWithValue(tokens, y)) {
/* 342 */         int target = end.get(1);
/* 343 */         if (months < 0)
/* 345 */           target--; 
/* 348 */         while (start.get(1) != target) {
/* 349 */           days += start.getActualMaximum(6) - start.get(6);
/* 352 */           if (start instanceof java.util.GregorianCalendar && 
/* 353 */             start.get(2) == 1 && start.get(5) == 29)
/* 356 */             days++; 
/* 360 */           start.add(1, 1);
/* 362 */           days += start.get(6);
/*     */         } 
/* 365 */         years = 0;
/*     */       } 
/* 368 */       while (start.get(2) != end.get(2)) {
/* 369 */         days += start.getActualMaximum(5);
/* 370 */         start.add(2, 1);
/*     */       } 
/* 373 */       months = 0;
/* 375 */       while (days < 0) {
/* 376 */         days += start.getActualMaximum(5);
/* 377 */         months--;
/* 378 */         start.add(2, 1);
/*     */       } 
/*     */     } 
/* 387 */     if (!Token.containsTokenWithValue(tokens, d)) {
/* 388 */       hours += 24 * days;
/* 389 */       days = 0;
/*     */     } 
/* 391 */     if (!Token.containsTokenWithValue(tokens, H)) {
/* 392 */       minutes += 60 * hours;
/* 393 */       hours = 0;
/*     */     } 
/* 395 */     if (!Token.containsTokenWithValue(tokens, m)) {
/* 396 */       seconds += 60 * minutes;
/* 397 */       minutes = 0;
/*     */     } 
/* 399 */     if (!Token.containsTokenWithValue(tokens, s)) {
/* 400 */       milliseconds += 1000 * seconds;
/* 401 */       seconds = 0;
/*     */     } 
/* 404 */     return format(tokens, years, months, days, hours, minutes, seconds, milliseconds, padWithZeros);
/*     */   }
/*     */   
/*     */   static String format(Token[] tokens, int years, int months, int days, int hours, int minutes, int seconds, int milliseconds, boolean padWithZeros) {
/* 424 */     StrBuilder buffer = new StrBuilder();
/* 425 */     boolean lastOutputSeconds = false;
/* 426 */     int sz = tokens.length;
/* 427 */     for (int i = 0; i < sz; i++) {
/* 428 */       Token token = tokens[i];
/* 429 */       Object value = token.getValue();
/* 430 */       int count = token.getCount();
/* 431 */       if (value instanceof StringBuffer) {
/* 432 */         buffer.append(value.toString());
/* 434 */       } else if (value == y) {
/* 435 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(years), count, '0') : Integer.toString(years));
/* 437 */         lastOutputSeconds = false;
/* 438 */       } else if (value == M) {
/* 439 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(months), count, '0') : Integer.toString(months));
/* 441 */         lastOutputSeconds = false;
/* 442 */       } else if (value == d) {
/* 443 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(days), count, '0') : Integer.toString(days));
/* 445 */         lastOutputSeconds = false;
/* 446 */       } else if (value == H) {
/* 447 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(hours), count, '0') : Integer.toString(hours));
/* 449 */         lastOutputSeconds = false;
/* 450 */       } else if (value == m) {
/* 451 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(minutes), count, '0') : Integer.toString(minutes));
/* 453 */         lastOutputSeconds = false;
/* 454 */       } else if (value == s) {
/* 455 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(seconds), count, '0') : Integer.toString(seconds));
/* 457 */         lastOutputSeconds = true;
/* 458 */       } else if (value == S) {
/* 459 */         if (lastOutputSeconds) {
/* 460 */           milliseconds += 1000;
/* 461 */           String str = padWithZeros ? StringUtils.leftPad(Integer.toString(milliseconds), count, '0') : Integer.toString(milliseconds);
/* 464 */           buffer.append(str.substring(1));
/*     */         } else {
/* 466 */           buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(milliseconds), count, '0') : Integer.toString(milliseconds));
/*     */         } 
/* 470 */         lastOutputSeconds = false;
/*     */       } 
/*     */     } 
/* 474 */     return buffer.toString();
/*     */   }
/*     */   
/* 477 */   static final Object y = "y";
/*     */   
/* 478 */   static final Object M = "M";
/*     */   
/* 479 */   static final Object d = "d";
/*     */   
/* 480 */   static final Object H = "H";
/*     */   
/* 481 */   static final Object m = "m";
/*     */   
/* 482 */   static final Object s = "s";
/*     */   
/* 483 */   static final Object S = "S";
/*     */   
/*     */   static Token[] lexx(String format) {
/* 492 */     char[] array = format.toCharArray();
/* 493 */     ArrayList list = new ArrayList(array.length);
/* 495 */     boolean inLiteral = false;
/* 496 */     StringBuffer buffer = null;
/* 497 */     Token previous = null;
/* 498 */     int sz = array.length;
/* 499 */     for (int i = 0; i < sz; i++) {
/* 500 */       char ch = array[i];
/* 501 */       if (inLiteral && ch != '\'') {
/* 502 */         buffer.append(ch);
/*     */       } else {
/* 505 */         Object value = null;
/* 506 */         switch (ch) {
/*     */           case '\'':
/* 509 */             if (inLiteral) {
/* 510 */               buffer = null;
/* 511 */               inLiteral = false;
/*     */               break;
/*     */             } 
/* 513 */             buffer = new StringBuffer();
/* 514 */             list.add(new Token(buffer));
/* 515 */             inLiteral = true;
/*     */             break;
/*     */           case 'y':
/* 518 */             value = y;
/*     */             break;
/*     */           case 'M':
/* 519 */             value = M;
/*     */             break;
/*     */           case 'd':
/* 520 */             value = d;
/*     */             break;
/*     */           case 'H':
/* 521 */             value = H;
/*     */             break;
/*     */           case 'm':
/* 522 */             value = m;
/*     */             break;
/*     */           case 's':
/* 523 */             value = s;
/*     */             break;
/*     */           case 'S':
/* 524 */             value = S;
/*     */             break;
/*     */           default:
/* 526 */             if (buffer == null) {
/* 527 */               buffer = new StringBuffer();
/* 528 */               list.add(new Token(buffer));
/*     */             } 
/* 530 */             buffer.append(ch);
/*     */             break;
/*     */         } 
/* 533 */         if (value != null) {
/* 534 */           if (previous != null && previous.getValue() == value) {
/* 535 */             previous.increment();
/*     */           } else {
/* 537 */             Token token = new Token(value);
/* 538 */             list.add(token);
/* 539 */             previous = token;
/*     */           } 
/* 541 */           buffer = null;
/*     */         } 
/*     */       } 
/*     */     } 
/* 544 */     return list.<Token>toArray(new Token[list.size()]);
/*     */   }
/*     */   
/*     */   static class Token {
/*     */     private Object value;
/*     */     
/*     */     private int count;
/*     */     
/*     */     static boolean containsTokenWithValue(Token[] tokens, Object value) {
/* 560 */       int sz = tokens.length;
/* 561 */       for (int i = 0; i < sz; i++) {
/* 562 */         if (tokens[i].getValue() == value)
/* 563 */           return true; 
/*     */       } 
/* 566 */       return false;
/*     */     }
/*     */     
/*     */     Token(Object value) {
/* 578 */       this.value = value;
/* 579 */       this.count = 1;
/*     */     }
/*     */     
/*     */     Token(Object value, int count) {
/* 590 */       this.value = value;
/* 591 */       this.count = count;
/*     */     }
/*     */     
/*     */     void increment() {
/* 598 */       this.count++;
/*     */     }
/*     */     
/*     */     int getCount() {
/* 607 */       return this.count;
/*     */     }
/*     */     
/*     */     Object getValue() {
/* 616 */       return this.value;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj2) {
/* 626 */       if (obj2 instanceof Token) {
/* 627 */         Token tok2 = (Token)obj2;
/* 628 */         if (this.value.getClass() != tok2.value.getClass())
/* 629 */           return false; 
/* 631 */         if (this.count != tok2.count)
/* 632 */           return false; 
/* 634 */         if (this.value instanceof StringBuffer)
/* 635 */           return this.value.toString().equals(tok2.value.toString()); 
/* 636 */         if (this.value instanceof Number)
/* 637 */           return this.value.equals(tok2.value); 
/* 639 */         return (this.value == tok2.value);
/*     */       } 
/* 642 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 653 */       return this.value.hashCode();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 662 */       return StringUtils.repeat(this.value.toString(), this.count);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\time\DurationFormatUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */