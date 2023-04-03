/*     */ package org.postgresql.jdbc2;
/*     */ 
/*     */ import java.sql.Date;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.SimpleTimeZone;
/*     */ import java.util.TimeZone;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class TimestampUtils {
/*  29 */   private StringBuffer sbuf = new StringBuffer();
/*     */   
/*  31 */   private Calendar defaultCal = new GregorianCalendar();
/*     */   
/*     */   private Calendar calCache;
/*     */   
/*     */   private int calCacheZone;
/*     */   
/*     */   private final boolean min74;
/*     */   
/*     */   private final boolean min82;
/*     */   
/*     */   TimestampUtils(boolean min74, boolean min82) {
/*  40 */     this.min74 = min74;
/*  41 */     this.min82 = min82;
/*     */   }
/*     */   
/*     */   private Calendar getCalendar(int sign, int hr, int min, int sec) {
/*  45 */     int rawOffset = sign * ((hr * 60 + min) * 60 + sec) * 1000;
/*  46 */     if (this.calCache != null && this.calCacheZone == rawOffset)
/*  47 */       return this.calCache; 
/*  49 */     StringBuffer zoneID = new StringBuffer("GMT");
/*  50 */     zoneID.append((sign < 0) ? 45 : 43);
/*  51 */     if (hr < 10)
/*  51 */       zoneID.append('0'); 
/*  52 */     zoneID.append(hr);
/*  53 */     if (min < 10)
/*  53 */       zoneID.append('0'); 
/*  54 */     zoneID.append(min);
/*  55 */     if (sec < 10)
/*  55 */       zoneID.append('0'); 
/*  56 */     zoneID.append(sec);
/*  58 */     TimeZone syntheticTZ = new SimpleTimeZone(rawOffset, zoneID.toString());
/*  59 */     this.calCache = new GregorianCalendar(syntheticTZ);
/*  60 */     this.calCacheZone = rawOffset;
/*  61 */     return this.calCache;
/*     */   }
/*     */   
/*     */   private static class ParsedTimestamp {
/*     */     private ParsedTimestamp() {}
/*     */     
/*     */     boolean hasDate = false;
/*     */     
/*  66 */     int era = 1;
/*     */     
/*  67 */     int year = 1970;
/*     */     
/*  68 */     int month = 1;
/*     */     
/*     */     boolean hasTime = false;
/*     */     
/*  71 */     int day = 1;
/*     */     
/*  72 */     int hour = 0;
/*     */     
/*  73 */     int minute = 0;
/*     */     
/*  74 */     int second = 0;
/*     */     
/*  75 */     int nanos = 0;
/*     */     
/*  77 */     Calendar tz = null;
/*     */   }
/*     */   
/*     */   private ParsedTimestamp loadCalendar(Calendar defaultTz, String str, String type) throws SQLException {
/*  85 */     char[] s = str.toCharArray();
/*  86 */     int slen = s.length;
/*  89 */     ParsedTimestamp result = new ParsedTimestamp();
/*     */     try {
/* 107 */       int start = skipWhitespace(s, 0);
/* 108 */       int end = firstNonDigit(s, start);
/* 113 */       if (charAt(s, end) == '-') {
/* 117 */         result.hasDate = true;
/* 120 */         result.year = number(s, start, end);
/* 121 */         start = end + 1;
/* 124 */         end = firstNonDigit(s, start);
/* 125 */         result.month = number(s, start, end);
/* 127 */         char c = charAt(s, end);
/* 128 */         if (c != '-')
/* 129 */           throw new NumberFormatException("Expected date to be dash-separated, got '" + c + "'"); 
/* 131 */         start = end + 1;
/* 134 */         end = firstNonDigit(s, start);
/* 135 */         result.day = number(s, start, end);
/* 137 */         start = skipWhitespace(s, end);
/*     */       } 
/* 141 */       if (Character.isDigit(charAt(s, start))) {
/* 146 */         result.hasTime = true;
/* 150 */         end = firstNonDigit(s, start);
/* 151 */         result.hour = number(s, start, end);
/* 153 */         char c = charAt(s, end);
/* 154 */         if (c != ':')
/* 155 */           throw new NumberFormatException("Expected time to be colon-separated, got '" + c + "'"); 
/* 157 */         start = end + 1;
/* 161 */         end = firstNonDigit(s, start);
/* 162 */         result.minute = number(s, start, end);
/* 164 */         c = charAt(s, end);
/* 165 */         if (c != ':')
/* 166 */           throw new NumberFormatException("Expected time to be colon-separated, got '" + c + "'"); 
/* 168 */         start = end + 1;
/* 172 */         end = firstNonDigit(s, start);
/* 173 */         result.second = number(s, start, end);
/* 174 */         start = end;
/* 177 */         if (charAt(s, start) == '.') {
/* 178 */           end = firstNonDigit(s, start + 1);
/* 179 */           int num = number(s, start + 1, end);
/* 181 */           for (int numlength = end - start + 1; numlength < 9; numlength++)
/* 182 */             num *= 10; 
/* 184 */           result.nanos = num;
/* 185 */           start = end;
/*     */         } 
/* 188 */         start = skipWhitespace(s, start);
/*     */       } 
/* 192 */       char sep = charAt(s, start);
/* 193 */       if (sep == '-' || sep == '+') {
/* 194 */         int tzmin, tzsign = (sep == '-') ? -1 : 1;
/* 197 */         end = firstNonDigit(s, start + 1);
/* 198 */         int tzhr = number(s, start + 1, end);
/* 199 */         start = end;
/* 201 */         sep = charAt(s, start);
/* 202 */         if (sep == ':') {
/* 203 */           end = firstNonDigit(s, start + 1);
/* 204 */           tzmin = number(s, start + 1, end);
/* 205 */           start = end;
/*     */         } else {
/* 207 */           tzmin = 0;
/*     */         } 
/* 210 */         int tzsec = 0;
/* 211 */         if (this.min82) {
/* 212 */           sep = charAt(s, start);
/* 213 */           if (sep == ':') {
/* 214 */             end = firstNonDigit(s, start + 1);
/* 215 */             tzsec = number(s, start + 1, end);
/* 216 */             start = end;
/*     */           } 
/*     */         } 
/* 223 */         result.tz = getCalendar(tzsign, tzhr, tzmin, tzsec);
/* 225 */         start = skipWhitespace(s, start);
/*     */       } 
/* 228 */       if (result.hasDate && start < slen) {
/* 229 */         String eraString = new String(s, start, slen - start);
/* 230 */         if (eraString.startsWith("AD")) {
/* 231 */           result.era = 1;
/* 232 */           start += 2;
/* 233 */         } else if (eraString.startsWith("BC")) {
/* 234 */           result.era = 0;
/* 235 */           start += 2;
/*     */         } 
/*     */       } 
/* 239 */       if (start < slen)
/* 240 */         throw new NumberFormatException("Trailing junk on timestamp: '" + new String(s, start, slen - start) + "'"); 
/* 242 */       if (!result.hasTime && !result.hasDate)
/* 243 */         throw new NumberFormatException("Timestamp has neither date nor time"); 
/* 245 */     } catch (NumberFormatException nfe) {
/* 246 */       throw new PSQLException(GT.tr("Bad value for type {0} : {1}", new Object[] { type, str }), PSQLState.BAD_DATETIME_FORMAT, nfe);
/*     */     } 
/* 249 */     return result;
/*     */   }
/*     */   
/*     */   private static void showParse(String type, String what, Calendar cal, Date result, Calendar resultCal) {}
/*     */   
/*     */   private static void showString(String type, Calendar cal, Date value, String result) {}
/*     */   
/*     */   public synchronized Timestamp toTimestamp(Calendar cal, String s) throws SQLException {
/* 304 */     if (s == null)
/* 305 */       return null; 
/* 307 */     int slen = s.length();
/* 310 */     if (slen == 8 && s.equals("infinity"))
/* 311 */       return new Timestamp(9223372036825200000L); 
/* 314 */     if (slen == 9 && s.equals("-infinity"))
/* 315 */       return new Timestamp(-9223372036832400000L); 
/* 318 */     if (cal == null)
/* 319 */       cal = this.defaultCal; 
/* 321 */     ParsedTimestamp ts = loadCalendar(cal, s, "timestamp");
/* 322 */     Calendar useCal = (ts.tz == null) ? cal : ts.tz;
/* 323 */     useCal.set(0, ts.era);
/* 324 */     useCal.set(1, ts.year);
/* 325 */     useCal.set(2, ts.month - 1);
/* 326 */     useCal.set(5, ts.day);
/* 327 */     useCal.set(11, ts.hour);
/* 328 */     useCal.set(12, ts.minute);
/* 329 */     useCal.set(13, ts.second);
/* 330 */     useCal.set(14, 0);
/* 332 */     Timestamp result = new Timestamp(useCal.getTime().getTime());
/* 333 */     result.setNanos(ts.nanos);
/* 334 */     showParse("timestamp", s, cal, result, useCal);
/* 335 */     return result;
/*     */   }
/*     */   
/*     */   public synchronized Time toTime(Calendar cal, String s) throws SQLException {
/* 340 */     if (s == null)
/* 341 */       return null; 
/* 343 */     int slen = s.length();
/* 347 */     if ((slen == 8 && s.equals("infinity")) || (slen == 9 && s.equals("-infinity")))
/* 348 */       throw new PSQLException(GT.tr("Infinite value found for timestamp/date. This cannot be represented as time."), PSQLState.DATETIME_OVERFLOW); 
/* 352 */     if (cal == null)
/* 353 */       cal = this.defaultCal; 
/* 355 */     ParsedTimestamp ts = loadCalendar(cal, s, "time");
/* 357 */     Calendar useCal = (ts.tz == null) ? cal : ts.tz;
/* 358 */     useCal.set(11, ts.hour);
/* 359 */     useCal.set(12, ts.minute);
/* 360 */     useCal.set(13, ts.second);
/* 361 */     useCal.set(14, (ts.nanos + 500000) / 1000000);
/* 363 */     if (ts.hasDate) {
/* 365 */       useCal.set(0, ts.era);
/* 366 */       useCal.set(1, ts.year);
/* 367 */       useCal.set(2, ts.month - 1);
/* 368 */       useCal.set(5, ts.day);
/* 369 */       cal.setTime(new Date(useCal.getTime().getTime()));
/* 370 */       useCal = cal;
/*     */     } 
/* 373 */     useCal.set(0, 1);
/* 374 */     useCal.set(1, 1970);
/* 375 */     useCal.set(2, 0);
/* 376 */     useCal.set(5, 1);
/* 378 */     Time result = new Time(useCal.getTime().getTime());
/* 379 */     showParse("time", s, cal, result, useCal);
/* 380 */     return result;
/*     */   }
/*     */   
/*     */   public synchronized Date toDate(Calendar cal, String s) throws SQLException {
/* 385 */     if (s == null)
/* 386 */       return null; 
/* 388 */     int slen = s.length();
/* 391 */     if (slen == 8 && s.equals("infinity"))
/* 392 */       return new Date(9223372036825200000L); 
/* 395 */     if (slen == 9 && s.equals("-infinity"))
/* 396 */       return new Date(-9223372036832400000L); 
/* 399 */     if (cal == null)
/* 400 */       cal = this.defaultCal; 
/* 402 */     ParsedTimestamp ts = loadCalendar(cal, s, "date");
/* 403 */     Calendar useCal = (ts.tz == null) ? cal : ts.tz;
/* 405 */     useCal.set(0, ts.era);
/* 406 */     useCal.set(1, ts.year);
/* 407 */     useCal.set(2, ts.month - 1);
/* 408 */     useCal.set(5, ts.day);
/* 410 */     if (ts.hasTime) {
/* 412 */       useCal.set(11, ts.hour);
/* 413 */       useCal.set(12, ts.minute);
/* 414 */       useCal.set(13, ts.second);
/* 415 */       useCal.set(14, (ts.nanos + 500000) / 1000000);
/* 416 */       cal.setTime(new Date(useCal.getTime().getTime()));
/* 417 */       useCal = cal;
/*     */     } 
/* 420 */     useCal.set(11, 0);
/* 421 */     useCal.set(12, 0);
/* 422 */     useCal.set(13, 0);
/* 423 */     useCal.set(14, 0);
/* 425 */     Date result = new Date(useCal.getTime().getTime());
/* 426 */     showParse("date", s, cal, result, useCal);
/* 427 */     return result;
/*     */   }
/*     */   
/*     */   public synchronized String toString(Calendar cal, Timestamp x) {
/* 431 */     if (cal == null)
/* 432 */       cal = this.defaultCal; 
/* 434 */     cal.setTime(x);
/* 435 */     this.sbuf.setLength(0);
/* 437 */     if (x.getTime() == 9223372036825200000L) {
/* 438 */       this.sbuf.append("infinity");
/* 439 */     } else if (x.getTime() == -9223372036832400000L) {
/* 440 */       this.sbuf.append("-infinity");
/*     */     } else {
/* 442 */       appendDate(this.sbuf, cal);
/* 443 */       this.sbuf.append(' ');
/* 444 */       appendTime(this.sbuf, cal, x.getNanos());
/* 445 */       appendTimeZone(this.sbuf, cal);
/* 446 */       appendEra(this.sbuf, cal);
/*     */     } 
/* 449 */     showString("timestamp", cal, x, this.sbuf.toString());
/* 450 */     return this.sbuf.toString();
/*     */   }
/*     */   
/*     */   public synchronized String toString(Calendar cal, Date x) {
/* 454 */     if (cal == null)
/* 455 */       cal = this.defaultCal; 
/* 457 */     cal.setTime(x);
/* 458 */     this.sbuf.setLength(0);
/* 460 */     if (x.getTime() == 9223372036825200000L) {
/* 461 */       this.sbuf.append("infinity");
/* 462 */     } else if (x.getTime() == -9223372036832400000L) {
/* 463 */       this.sbuf.append("-infinity");
/*     */     } else {
/* 465 */       appendDate(this.sbuf, cal);
/* 466 */       appendEra(this.sbuf, cal);
/* 467 */       appendTimeZone(this.sbuf, cal);
/*     */     } 
/* 470 */     showString("date", cal, x, this.sbuf.toString());
/* 472 */     return this.sbuf.toString();
/*     */   }
/*     */   
/*     */   public synchronized String toString(Calendar cal, Time x) {
/* 476 */     if (cal == null)
/* 477 */       cal = this.defaultCal; 
/* 479 */     cal.setTime(x);
/* 480 */     this.sbuf.setLength(0);
/* 482 */     appendTime(this.sbuf, cal, cal.get(14) * 1000000);
/* 485 */     if (this.min74)
/* 486 */       appendTimeZone(this.sbuf, cal); 
/* 488 */     showString("time", cal, x, this.sbuf.toString());
/* 490 */     return this.sbuf.toString();
/*     */   }
/*     */   
/*     */   private static void appendDate(StringBuffer sb, Calendar cal) {
/* 495 */     int l_year = cal.get(1);
/* 499 */     int l_yearlen = String.valueOf(l_year).length();
/* 500 */     for (int i = 4; i > l_yearlen; i--)
/* 502 */       sb.append("0"); 
/* 505 */     sb.append(l_year);
/* 506 */     sb.append('-');
/* 507 */     int l_month = cal.get(2) + 1;
/* 508 */     if (l_month < 10)
/* 509 */       sb.append('0'); 
/* 510 */     sb.append(l_month);
/* 511 */     sb.append('-');
/* 512 */     int l_day = cal.get(5);
/* 513 */     if (l_day < 10)
/* 514 */       sb.append('0'); 
/* 515 */     sb.append(l_day);
/*     */   }
/*     */   
/*     */   private static void appendTime(StringBuffer sb, Calendar cal, int nanos) {
/* 520 */     int hours = cal.get(11);
/* 521 */     if (hours < 10)
/* 522 */       sb.append('0'); 
/* 523 */     sb.append(hours);
/* 525 */     sb.append(':');
/* 526 */     int minutes = cal.get(12);
/* 527 */     if (minutes < 10)
/* 528 */       sb.append('0'); 
/* 529 */     sb.append(minutes);
/* 531 */     sb.append(':');
/* 532 */     int seconds = cal.get(13);
/* 533 */     if (seconds < 10)
/* 534 */       sb.append('0'); 
/* 535 */     sb.append(seconds);
/* 542 */     char[] decimalStr = { '0', '0', '0', '0', '0', '0', '0', '0', '0' };
/* 543 */     char[] nanoStr = Integer.toString(nanos).toCharArray();
/* 544 */     System.arraycopy(nanoStr, 0, decimalStr, decimalStr.length - nanoStr.length, nanoStr.length);
/* 545 */     sb.append('.');
/* 546 */     sb.append(decimalStr, 0, 6);
/*     */   }
/*     */   
/*     */   private void appendTimeZone(StringBuffer sb, Calendar cal) {
/* 551 */     int offset = (cal.get(15) + cal.get(16)) / 1000;
/* 553 */     int absoff = Math.abs(offset);
/* 554 */     int hours = absoff / 60 / 60;
/* 555 */     int mins = (absoff - hours * 60 * 60) / 60;
/* 556 */     int secs = absoff - hours * 60 * 60 - mins * 60;
/* 558 */     sb.append((offset >= 0) ? " +" : " -");
/* 560 */     if (hours < 10)
/* 561 */       sb.append('0'); 
/* 562 */     sb.append(hours);
/* 564 */     sb.append(':');
/* 566 */     if (mins < 10)
/* 567 */       sb.append('0'); 
/* 568 */     sb.append(mins);
/* 570 */     if (this.min82) {
/* 571 */       sb.append(':');
/* 572 */       if (secs < 10)
/* 573 */         sb.append('0'); 
/* 574 */       sb.append(secs);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void appendEra(StringBuffer sb, Calendar cal) {
/* 580 */     if (cal.get(0) == 0)
/* 581 */       sb.append(" BC"); 
/*     */   }
/*     */   
/*     */   private static int skipWhitespace(char[] s, int start) {
/* 587 */     int slen = s.length;
/* 588 */     for (int i = start; i < slen; i++) {
/* 589 */       if (!Character.isSpace(s[i]))
/* 590 */         return i; 
/*     */     } 
/* 592 */     return slen;
/*     */   }
/*     */   
/*     */   private static int firstNonDigit(char[] s, int start) {
/* 597 */     int slen = s.length;
/* 598 */     for (int i = start; i < slen; i++) {
/* 599 */       if (!Character.isDigit(s[i]))
/* 600 */         return i; 
/*     */     } 
/* 603 */     return slen;
/*     */   }
/*     */   
/*     */   private static int number(char[] s, int start, int end) {
/* 607 */     if (start >= end)
/* 608 */       throw new NumberFormatException(); 
/* 610 */     int n = 0;
/* 611 */     for (int i = start; i < end; i++)
/* 613 */       n = 10 * n + s[i] - 48; 
/* 615 */     return n;
/*     */   }
/*     */   
/*     */   private static char charAt(char[] s, int pos) {
/* 619 */     if (pos >= 0 && pos < s.length)
/* 620 */       return s[pos]; 
/* 622 */     return Character.MIN_VALUE;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc2\TimestampUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */