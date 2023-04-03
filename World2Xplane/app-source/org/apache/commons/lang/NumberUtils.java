/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ 
/*     */ public final class NumberUtils {
/*     */   public static int stringToInt(String str) {
/*  61 */     return stringToInt(str, 0);
/*     */   }
/*     */   
/*     */   public static int stringToInt(String str, int defaultValue) {
/*     */     try {
/*  74 */       return Integer.parseInt(str);
/*  75 */     } catch (NumberFormatException nfe) {
/*  76 */       return defaultValue;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Number createNumber(String val) throws NumberFormatException {
/*     */     String mant, dec, exp;
/* 139 */     if (val == null)
/* 140 */       return null; 
/* 142 */     if (val.length() == 0)
/* 143 */       throw new NumberFormatException("\"\" is not a valid number."); 
/* 145 */     if (val.length() == 1 && !Character.isDigit(val.charAt(0)))
/* 146 */       throw new NumberFormatException(val + " is not a valid number."); 
/* 148 */     if (val.startsWith("--"))
/* 153 */       return null; 
/* 155 */     if (val.startsWith("0x") || val.startsWith("-0x"))
/* 156 */       return createInteger(val); 
/* 158 */     char lastChar = val.charAt(val.length() - 1);
/* 162 */     int decPos = val.indexOf('.');
/* 163 */     int expPos = val.indexOf('e') + val.indexOf('E') + 1;
/* 165 */     if (decPos > -1) {
/* 167 */       if (expPos > -1) {
/* 168 */         if (expPos < decPos)
/* 169 */           throw new NumberFormatException(val + " is not a valid number."); 
/* 171 */         dec = val.substring(decPos + 1, expPos);
/*     */       } else {
/* 173 */         dec = val.substring(decPos + 1);
/*     */       } 
/* 175 */       mant = val.substring(0, decPos);
/*     */     } else {
/* 177 */       if (expPos > -1) {
/* 178 */         mant = val.substring(0, expPos);
/*     */       } else {
/* 180 */         mant = val;
/*     */       } 
/* 182 */       dec = null;
/*     */     } 
/* 184 */     if (!Character.isDigit(lastChar)) {
/* 185 */       if (expPos > -1 && expPos < val.length() - 1) {
/* 186 */         exp = val.substring(expPos + 1, val.length() - 1);
/*     */       } else {
/* 188 */         exp = null;
/*     */       } 
/* 191 */       String numeric = val.substring(0, val.length() - 1);
/* 192 */       boolean bool = (isAllZeros(mant) && isAllZeros(exp));
/* 193 */       switch (lastChar) {
/*     */         case 'L':
/*     */         case 'l':
/* 196 */           if (dec == null && exp == null && ((numeric.charAt(0) == '-' && isDigits(numeric.substring(1))) || isDigits(numeric)))
/*     */             try {
/* 200 */               return createLong(numeric);
/* 201 */             } catch (NumberFormatException nfe) {
/* 204 */               return createBigInteger(numeric);
/*     */             }  
/* 207 */           throw new NumberFormatException(val + " is not a valid number.");
/*     */         case 'F':
/*     */         case 'f':
/*     */           try {
/* 211 */             Float f = createFloat(numeric);
/* 212 */             if (!f.isInfinite() && (f.floatValue() != 0.0F || bool))
/* 215 */               return f; 
/* 218 */           } catch (NumberFormatException e) {}
/*     */         case 'D':
/*     */         case 'd':
/*     */           try {
/* 225 */             Double d = createDouble(numeric);
/* 226 */             if (!d.isInfinite() && (d.floatValue() != 0.0D || bool))
/* 227 */               return d; 
/* 229 */           } catch (NumberFormatException nfe) {}
/*     */           try {
/* 233 */             return createBigDecimal(numeric);
/* 234 */           } catch (NumberFormatException e) {
/*     */             break;
/*     */           } 
/*     */       } 
/* 239 */       throw new NumberFormatException(val + " is not a valid number.");
/*     */     } 
/* 245 */     if (expPos > -1 && expPos < val.length() - 1) {
/* 246 */       exp = val.substring(expPos + 1, val.length());
/*     */     } else {
/* 248 */       exp = null;
/*     */     } 
/* 250 */     if (dec == null && exp == null)
/*     */       try {
/* 253 */         return createInteger(val);
/* 254 */       } catch (NumberFormatException nfe) {
/*     */         try {
/* 258 */           return createLong(val);
/* 259 */         } catch (NumberFormatException numberFormatException) {
/* 262 */           return createBigInteger(val);
/*     */         } 
/*     */       }  
/* 266 */     boolean allZeros = (isAllZeros(mant) && isAllZeros(exp));
/*     */     try {
/* 268 */       Float f = createFloat(val);
/* 269 */       if (!f.isInfinite() && (f.floatValue() != 0.0F || allZeros))
/* 270 */         return f; 
/* 272 */     } catch (NumberFormatException nfe) {}
/*     */     try {
/* 276 */       Double d = createDouble(val);
/* 277 */       if (!d.isInfinite() && (d.doubleValue() != 0.0D || allZeros))
/* 278 */         return d; 
/* 280 */     } catch (NumberFormatException nfe) {}
/* 284 */     return createBigDecimal(val);
/*     */   }
/*     */   
/*     */   private static boolean isAllZeros(String s) {
/* 300 */     if (s == null)
/* 301 */       return true; 
/* 303 */     for (int i = s.length() - 1; i >= 0; i--) {
/* 304 */       if (s.charAt(i) != '0')
/* 305 */         return false; 
/*     */     } 
/* 308 */     return (s.length() > 0);
/*     */   }
/*     */   
/*     */   public static Float createFloat(String val) {
/* 321 */     return Float.valueOf(val);
/*     */   }
/*     */   
/*     */   public static Double createDouble(String val) {
/* 332 */     return Double.valueOf(val);
/*     */   }
/*     */   
/*     */   public static Integer createInteger(String val) {
/* 345 */     return Integer.decode(val);
/*     */   }
/*     */   
/*     */   public static Long createLong(String val) {
/* 356 */     return Long.valueOf(val);
/*     */   }
/*     */   
/*     */   public static BigInteger createBigInteger(String val) {
/* 367 */     BigInteger bi = new BigInteger(val);
/* 368 */     return bi;
/*     */   }
/*     */   
/*     */   public static BigDecimal createBigDecimal(String val) {
/* 379 */     BigDecimal bd = new BigDecimal(val);
/* 380 */     return bd;
/*     */   }
/*     */   
/*     */   public static long minimum(long a, long b, long c) {
/* 394 */     if (b < a)
/* 395 */       a = b; 
/* 397 */     if (c < a)
/* 398 */       a = c; 
/* 400 */     return a;
/*     */   }
/*     */   
/*     */   public static int minimum(int a, int b, int c) {
/* 412 */     if (b < a)
/* 413 */       a = b; 
/* 415 */     if (c < a)
/* 416 */       a = c; 
/* 418 */     return a;
/*     */   }
/*     */   
/*     */   public static long maximum(long a, long b, long c) {
/* 430 */     if (b > a)
/* 431 */       a = b; 
/* 433 */     if (c > a)
/* 434 */       a = c; 
/* 436 */     return a;
/*     */   }
/*     */   
/*     */   public static int maximum(int a, int b, int c) {
/* 448 */     if (b > a)
/* 449 */       a = b; 
/* 451 */     if (c > a)
/* 452 */       a = c; 
/* 454 */     return a;
/*     */   }
/*     */   
/*     */   public static int compare(double lhs, double rhs) {
/* 494 */     if (lhs < rhs)
/* 495 */       return -1; 
/* 497 */     if (lhs > rhs)
/* 498 */       return 1; 
/* 504 */     long lhsBits = Double.doubleToLongBits(lhs);
/* 505 */     long rhsBits = Double.doubleToLongBits(rhs);
/* 506 */     if (lhsBits == rhsBits)
/* 507 */       return 0; 
/* 515 */     if (lhsBits < rhsBits)
/* 516 */       return -1; 
/* 518 */     return 1;
/*     */   }
/*     */   
/*     */   public static int compare(float lhs, float rhs) {
/* 555 */     if (lhs < rhs)
/* 556 */       return -1; 
/* 558 */     if (lhs > rhs)
/* 559 */       return 1; 
/* 565 */     int lhsBits = Float.floatToIntBits(lhs);
/* 566 */     int rhsBits = Float.floatToIntBits(rhs);
/* 567 */     if (lhsBits == rhsBits)
/* 568 */       return 0; 
/* 576 */     if (lhsBits < rhsBits)
/* 577 */       return -1; 
/* 579 */     return 1;
/*     */   }
/*     */   
/*     */   public static boolean isDigits(String str) {
/* 596 */     if (str == null || str.length() == 0)
/* 597 */       return false; 
/* 599 */     for (int i = 0; i < str.length(); i++) {
/* 600 */       if (!Character.isDigit(str.charAt(i)))
/* 601 */         return false; 
/*     */     } 
/* 604 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isNumber(String str) {
/* 621 */     if (StringUtils.isEmpty(str))
/* 622 */       return false; 
/* 624 */     char[] chars = str.toCharArray();
/* 625 */     int sz = chars.length;
/* 626 */     boolean hasExp = false;
/* 627 */     boolean hasDecPoint = false;
/* 628 */     boolean allowSigns = false;
/* 629 */     boolean foundDigit = false;
/* 631 */     int start = (chars[0] == '-') ? 1 : 0;
/* 632 */     if (sz > start + 1 && 
/* 633 */       chars[start] == '0' && chars[start + 1] == 'x') {
/* 634 */       int j = start + 2;
/* 635 */       if (j == sz)
/* 636 */         return false; 
/* 639 */       for (; j < chars.length; j++) {
/* 640 */         if ((chars[j] < '0' || chars[j] > '9') && (chars[j] < 'a' || chars[j] > 'f') && (chars[j] < 'A' || chars[j] > 'F'))
/* 643 */           return false; 
/*     */       } 
/* 646 */       return true;
/*     */     } 
/* 649 */     sz--;
/* 651 */     int i = start;
/* 654 */     while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
/* 655 */       if (chars[i] >= '0' && chars[i] <= '9') {
/* 656 */         foundDigit = true;
/* 657 */         allowSigns = false;
/* 659 */       } else if (chars[i] == '.') {
/* 660 */         if (hasDecPoint || hasExp)
/* 662 */           return false; 
/* 664 */         hasDecPoint = true;
/* 665 */       } else if (chars[i] == 'e' || chars[i] == 'E') {
/* 667 */         if (hasExp)
/* 669 */           return false; 
/* 671 */         if (!foundDigit)
/* 672 */           return false; 
/* 674 */         hasExp = true;
/* 675 */         allowSigns = true;
/* 676 */       } else if (chars[i] == '+' || chars[i] == '-') {
/* 677 */         if (!allowSigns)
/* 678 */           return false; 
/* 680 */         allowSigns = false;
/* 681 */         foundDigit = false;
/*     */       } else {
/* 683 */         return false;
/*     */       } 
/* 685 */       i++;
/*     */     } 
/* 687 */     if (i < chars.length) {
/* 688 */       if (chars[i] >= '0' && chars[i] <= '9')
/* 690 */         return true; 
/* 692 */       if (chars[i] == 'e' || chars[i] == 'E')
/* 694 */         return false; 
/* 696 */       if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F'))
/* 701 */         return foundDigit; 
/* 703 */       if (chars[i] == 'l' || chars[i] == 'L')
/* 706 */         return (foundDigit && !hasExp); 
/* 709 */       return false;
/*     */     } 
/* 713 */     return (!allowSigns && foundDigit);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\NumberUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */