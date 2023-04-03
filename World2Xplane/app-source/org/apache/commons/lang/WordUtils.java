/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ public class WordUtils {
/*     */   public static String wrap(String str, int wrapLength) {
/* 142 */     return wrap(str, wrapLength, null, false);
/*     */   }
/*     */   
/*     */   public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords) {
/* 164 */     if (str == null)
/* 165 */       return null; 
/* 167 */     if (newLineStr == null)
/* 168 */       newLineStr = SystemUtils.LINE_SEPARATOR; 
/* 170 */     if (wrapLength < 1)
/* 171 */       wrapLength = 1; 
/* 173 */     int inputLineLength = str.length();
/* 174 */     int offset = 0;
/* 175 */     StringBuffer wrappedLine = new StringBuffer(inputLineLength + 32);
/* 177 */     while (inputLineLength - offset > wrapLength) {
/* 178 */       if (str.charAt(offset) == ' ') {
/* 179 */         offset++;
/*     */         continue;
/*     */       } 
/* 182 */       int spaceToWrapAt = str.lastIndexOf(' ', wrapLength + offset);
/* 184 */       if (spaceToWrapAt >= offset) {
/* 186 */         wrappedLine.append(str.substring(offset, spaceToWrapAt));
/* 187 */         wrappedLine.append(newLineStr);
/* 188 */         offset = spaceToWrapAt + 1;
/*     */         continue;
/*     */       } 
/* 192 */       if (wrapLongWords) {
/* 194 */         wrappedLine.append(str.substring(offset, wrapLength + offset));
/* 195 */         wrappedLine.append(newLineStr);
/* 196 */         offset += wrapLength;
/*     */         continue;
/*     */       } 
/* 199 */       spaceToWrapAt = str.indexOf(' ', wrapLength + offset);
/* 200 */       if (spaceToWrapAt >= 0) {
/* 201 */         wrappedLine.append(str.substring(offset, spaceToWrapAt));
/* 202 */         wrappedLine.append(newLineStr);
/* 203 */         offset = spaceToWrapAt + 1;
/*     */         continue;
/*     */       } 
/* 205 */       wrappedLine.append(str.substring(offset));
/* 206 */       offset = inputLineLength;
/*     */     } 
/* 213 */     wrappedLine.append(str.substring(offset));
/* 215 */     return wrappedLine.toString();
/*     */   }
/*     */   
/*     */   public static String capitalize(String str) {
/* 243 */     return capitalize(str, null);
/*     */   }
/*     */   
/*     */   public static String capitalize(String str, char[] delimiters) {
/* 276 */     int delimLen = (delimiters == null) ? -1 : delimiters.length;
/* 277 */     if (str == null || str.length() == 0 || delimLen == 0)
/* 278 */       return str; 
/* 280 */     int strLen = str.length();
/* 281 */     StringBuffer buffer = new StringBuffer(strLen);
/* 282 */     boolean capitalizeNext = true;
/* 283 */     for (int i = 0; i < strLen; i++) {
/* 284 */       char ch = str.charAt(i);
/* 286 */       if (isDelimiter(ch, delimiters)) {
/* 287 */         buffer.append(ch);
/* 288 */         capitalizeNext = true;
/* 289 */       } else if (capitalizeNext) {
/* 290 */         buffer.append(Character.toTitleCase(ch));
/* 291 */         capitalizeNext = false;
/*     */       } else {
/* 293 */         buffer.append(ch);
/*     */       } 
/*     */     } 
/* 296 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public static String capitalizeFully(String str) {
/* 320 */     return capitalizeFully(str, null);
/*     */   }
/*     */   
/*     */   public static String capitalizeFully(String str, char[] delimiters) {
/* 350 */     int delimLen = (delimiters == null) ? -1 : delimiters.length;
/* 351 */     if (str == null || str.length() == 0 || delimLen == 0)
/* 352 */       return str; 
/* 354 */     str = str.toLowerCase();
/* 355 */     return capitalize(str, delimiters);
/*     */   }
/*     */   
/*     */   public static String uncapitalize(String str) {
/* 377 */     return uncapitalize(str, null);
/*     */   }
/*     */   
/*     */   public static String uncapitalize(String str, char[] delimiters) {
/* 406 */     int delimLen = (delimiters == null) ? -1 : delimiters.length;
/* 407 */     if (str == null || str.length() == 0 || delimLen == 0)
/* 408 */       return str; 
/* 410 */     int strLen = str.length();
/* 411 */     StringBuffer buffer = new StringBuffer(strLen);
/* 412 */     boolean uncapitalizeNext = true;
/* 413 */     for (int i = 0; i < strLen; i++) {
/* 414 */       char ch = str.charAt(i);
/* 416 */       if (isDelimiter(ch, delimiters)) {
/* 417 */         buffer.append(ch);
/* 418 */         uncapitalizeNext = true;
/* 419 */       } else if (uncapitalizeNext) {
/* 420 */         buffer.append(Character.toLowerCase(ch));
/* 421 */         uncapitalizeNext = false;
/*     */       } else {
/* 423 */         buffer.append(ch);
/*     */       } 
/*     */     } 
/* 426 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public static String swapCase(String str) {
/*     */     int strLen;
/* 454 */     if (str == null || (strLen = str.length()) == 0)
/* 455 */       return str; 
/* 457 */     StringBuffer buffer = new StringBuffer(strLen);
/* 459 */     boolean whitespace = true;
/* 460 */     char ch = Character.MIN_VALUE;
/* 461 */     char tmp = Character.MIN_VALUE;
/* 463 */     for (int i = 0; i < strLen; i++) {
/* 464 */       ch = str.charAt(i);
/* 465 */       if (Character.isUpperCase(ch)) {
/* 466 */         tmp = Character.toLowerCase(ch);
/* 467 */       } else if (Character.isTitleCase(ch)) {
/* 468 */         tmp = Character.toLowerCase(ch);
/* 469 */       } else if (Character.isLowerCase(ch)) {
/* 470 */         if (whitespace) {
/* 471 */           tmp = Character.toTitleCase(ch);
/*     */         } else {
/* 473 */           tmp = Character.toUpperCase(ch);
/*     */         } 
/*     */       } else {
/* 476 */         tmp = ch;
/*     */       } 
/* 478 */       buffer.append(tmp);
/* 479 */       whitespace = Character.isWhitespace(ch);
/*     */     } 
/* 481 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public static String initials(String str) {
/* 508 */     return initials(str, null);
/*     */   }
/*     */   
/*     */   public static String initials(String str, char[] delimiters) {
/* 539 */     if (str == null || str.length() == 0)
/* 540 */       return str; 
/* 542 */     if (delimiters != null && delimiters.length == 0)
/* 543 */       return ""; 
/* 545 */     int strLen = str.length();
/* 546 */     char[] buf = new char[strLen / 2 + 1];
/* 547 */     int count = 0;
/* 548 */     boolean lastWasGap = true;
/* 549 */     for (int i = 0; i < strLen; i++) {
/* 550 */       char ch = str.charAt(i);
/* 552 */       if (isDelimiter(ch, delimiters)) {
/* 553 */         lastWasGap = true;
/* 554 */       } else if (lastWasGap) {
/* 555 */         buf[count++] = ch;
/* 556 */         lastWasGap = false;
/*     */       } 
/*     */     } 
/* 561 */     return new String(buf, 0, count);
/*     */   }
/*     */   
/*     */   private static boolean isDelimiter(char ch, char[] delimiters) {
/* 573 */     if (delimiters == null)
/* 574 */       return Character.isWhitespace(ch); 
/* 576 */     for (int i = 0, isize = delimiters.length; i < isize; i++) {
/* 577 */       if (ch == delimiters[i])
/* 578 */         return true; 
/*     */     } 
/* 581 */     return false;
/*     */   }
/*     */   
/*     */   public static String abbreviate(String str, int lower, int upper, String appendToEnd) {
/* 607 */     if (str == null)
/* 608 */       return null; 
/* 610 */     if (str.length() == 0)
/* 611 */       return ""; 
/* 616 */     if (lower > str.length())
/* 617 */       lower = str.length(); 
/* 621 */     if (upper == -1 || upper > str.length())
/* 622 */       upper = str.length(); 
/* 625 */     if (upper < lower)
/* 626 */       upper = lower; 
/* 629 */     StringBuffer result = new StringBuffer();
/* 630 */     int index = StringUtils.indexOf(str, " ", lower);
/* 631 */     if (index == -1) {
/* 632 */       result.append(str.substring(0, upper));
/* 634 */       if (upper != str.length())
/* 635 */         result.append(StringUtils.defaultString(appendToEnd)); 
/* 637 */     } else if (index > upper) {
/* 638 */       result.append(str.substring(0, upper));
/* 639 */       result.append(StringUtils.defaultString(appendToEnd));
/*     */     } else {
/* 641 */       result.append(str.substring(0, index));
/* 642 */       result.append(StringUtils.defaultString(appendToEnd));
/*     */     } 
/* 644 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\WordUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */