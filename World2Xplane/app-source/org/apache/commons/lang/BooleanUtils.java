/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import org.apache.commons.lang.math.NumberUtils;
/*     */ 
/*     */ public class BooleanUtils {
/*     */   public static Boolean negate(Boolean bool) {
/*  65 */     if (bool == null)
/*  66 */       return null; 
/*  68 */     return bool.booleanValue() ? Boolean.FALSE : Boolean.TRUE;
/*     */   }
/*     */   
/*     */   public static boolean isTrue(Boolean bool) {
/*  88 */     if (bool == null)
/*  89 */       return false; 
/*  91 */     return bool.booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean isNotTrue(Boolean bool) {
/* 109 */     return !isTrue(bool);
/*     */   }
/*     */   
/*     */   public static boolean isFalse(Boolean bool) {
/* 127 */     if (bool == null)
/* 128 */       return false; 
/* 130 */     return !bool.booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean isNotFalse(Boolean bool) {
/* 148 */     return !isFalse(bool);
/*     */   }
/*     */   
/*     */   public static Boolean toBooleanObject(boolean bool) {
/* 166 */     return bool ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */   
/*     */   public static boolean toBoolean(Boolean bool) {
/* 184 */     if (bool == null)
/* 185 */       return false; 
/* 187 */     return bool.booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull) {
/* 204 */     if (bool == null)
/* 205 */       return valueIfNull; 
/* 207 */     return bool.booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean toBoolean(int value) {
/* 227 */     return !(value == 0);
/*     */   }
/*     */   
/*     */   public static Boolean toBooleanObject(int value) {
/* 245 */     return (value == 0) ? Boolean.FALSE : Boolean.TRUE;
/*     */   }
/*     */   
/*     */   public static Boolean toBooleanObject(Integer value) {
/* 265 */     if (value == null)
/* 266 */       return null; 
/* 268 */     return (value.intValue() == 0) ? Boolean.FALSE : Boolean.TRUE;
/*     */   }
/*     */   
/*     */   public static boolean toBoolean(int value, int trueValue, int falseValue) {
/* 288 */     if (value == trueValue)
/* 289 */       return true; 
/* 290 */     if (value == falseValue)
/* 291 */       return false; 
/* 294 */     throw new IllegalArgumentException("The Integer did not match either specified value");
/*     */   }
/*     */   
/*     */   public static boolean toBoolean(Integer value, Integer trueValue, Integer falseValue) {
/* 317 */     if (value == null) {
/* 318 */       if (trueValue == null)
/* 319 */         return true; 
/* 320 */       if (falseValue == null)
/* 321 */         return false; 
/*     */     } else {
/* 323 */       if (value.equals(trueValue))
/* 324 */         return true; 
/* 325 */       if (value.equals(falseValue))
/* 326 */         return false; 
/*     */     } 
/* 329 */     throw new IllegalArgumentException("The Integer did not match either specified value");
/*     */   }
/*     */   
/*     */   public static Boolean toBooleanObject(int value, int trueValue, int falseValue, int nullValue) {
/* 349 */     if (value == trueValue)
/* 350 */       return Boolean.TRUE; 
/* 351 */     if (value == falseValue)
/* 352 */       return Boolean.FALSE; 
/* 353 */     if (value == nullValue)
/* 354 */       return null; 
/* 357 */     throw new IllegalArgumentException("The Integer did not match any specified value");
/*     */   }
/*     */   
/*     */   public static Boolean toBooleanObject(Integer value, Integer trueValue, Integer falseValue, Integer nullValue) {
/* 380 */     if (value == null) {
/* 381 */       if (trueValue == null)
/* 382 */         return Boolean.TRUE; 
/* 383 */       if (falseValue == null)
/* 384 */         return Boolean.FALSE; 
/* 385 */       if (nullValue == null)
/* 386 */         return null; 
/*     */     } else {
/* 388 */       if (value.equals(trueValue))
/* 389 */         return Boolean.TRUE; 
/* 390 */       if (value.equals(falseValue))
/* 391 */         return Boolean.FALSE; 
/* 392 */       if (value.equals(nullValue))
/* 393 */         return null; 
/*     */     } 
/* 396 */     throw new IllegalArgumentException("The Integer did not match any specified value");
/*     */   }
/*     */   
/*     */   public static int toInteger(boolean bool) {
/* 414 */     return bool ? 1 : 0;
/*     */   }
/*     */   
/*     */   public static Integer toIntegerObject(boolean bool) {
/* 430 */     return bool ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
/*     */   }
/*     */   
/*     */   public static Integer toIntegerObject(Boolean bool) {
/* 448 */     if (bool == null)
/* 449 */       return null; 
/* 451 */     return bool.booleanValue() ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
/*     */   }
/*     */   
/*     */   public static int toInteger(boolean bool, int trueValue, int falseValue) {
/* 468 */     return bool ? trueValue : falseValue;
/*     */   }
/*     */   
/*     */   public static int toInteger(Boolean bool, int trueValue, int falseValue, int nullValue) {
/* 487 */     if (bool == null)
/* 488 */       return nullValue; 
/* 490 */     return bool.booleanValue() ? trueValue : falseValue;
/*     */   }
/*     */   
/*     */   public static Integer toIntegerObject(boolean bool, Integer trueValue, Integer falseValue) {
/* 509 */     return bool ? trueValue : falseValue;
/*     */   }
/*     */   
/*     */   public static Integer toIntegerObject(Boolean bool, Integer trueValue, Integer falseValue, Integer nullValue) {
/* 531 */     if (bool == null)
/* 532 */       return nullValue; 
/* 534 */     return bool.booleanValue() ? trueValue : falseValue;
/*     */   }
/*     */   
/*     */   public static Boolean toBooleanObject(String str) {
/*     */     char ch0;
/*     */     char ch1;
/*     */     char ch2;
/*     */     char ch3;
/*     */     char ch4;
/* 570 */     if (str == "true")
/* 571 */       return Boolean.TRUE; 
/* 573 */     if (str == null)
/* 574 */       return null; 
/* 576 */     switch (str.length()) {
/*     */       case 1:
/* 578 */         ch0 = str.charAt(0);
/* 579 */         if (ch0 == 'y' || ch0 == 'Y' || ch0 == 't' || ch0 == 'T')
/* 582 */           return Boolean.TRUE; 
/* 584 */         if (ch0 == 'n' || ch0 == 'N' || ch0 == 'f' || ch0 == 'F')
/* 587 */           return Boolean.FALSE; 
/*     */         break;
/*     */       case 2:
/* 592 */         ch0 = str.charAt(0);
/* 593 */         ch1 = str.charAt(1);
/* 594 */         if ((ch0 == 'o' || ch0 == 'O') && (ch1 == 'n' || ch1 == 'N'))
/* 597 */           return Boolean.TRUE; 
/* 599 */         if ((ch0 == 'n' || ch0 == 'N') && (ch1 == 'o' || ch1 == 'O'))
/* 602 */           return Boolean.FALSE; 
/*     */         break;
/*     */       case 3:
/* 607 */         ch0 = str.charAt(0);
/* 608 */         ch1 = str.charAt(1);
/* 609 */         ch2 = str.charAt(2);
/* 610 */         if ((ch0 == 'y' || ch0 == 'Y') && (ch1 == 'e' || ch1 == 'E') && (ch2 == 's' || ch2 == 'S'))
/* 614 */           return Boolean.TRUE; 
/* 616 */         if ((ch0 == 'o' || ch0 == 'O') && (ch1 == 'f' || ch1 == 'F') && (ch2 == 'f' || ch2 == 'F'))
/* 620 */           return Boolean.FALSE; 
/*     */         break;
/*     */       case 4:
/* 625 */         ch0 = str.charAt(0);
/* 626 */         ch1 = str.charAt(1);
/* 627 */         ch2 = str.charAt(2);
/* 628 */         ch3 = str.charAt(3);
/* 629 */         if ((ch0 == 't' || ch0 == 'T') && (ch1 == 'r' || ch1 == 'R') && (ch2 == 'u' || ch2 == 'U') && (ch3 == 'e' || ch3 == 'E'))
/* 634 */           return Boolean.TRUE; 
/*     */         break;
/*     */       case 5:
/* 639 */         ch0 = str.charAt(0);
/* 640 */         ch1 = str.charAt(1);
/* 641 */         ch2 = str.charAt(2);
/* 642 */         ch3 = str.charAt(3);
/* 643 */         ch4 = str.charAt(4);
/* 644 */         if ((ch0 == 'f' || ch0 == 'F') && (ch1 == 'a' || ch1 == 'A') && (ch2 == 'l' || ch2 == 'L') && (ch3 == 's' || ch3 == 'S') && (ch4 == 'e' || ch4 == 'E'))
/* 650 */           return Boolean.FALSE; 
/*     */         break;
/*     */     } 
/* 656 */     return null;
/*     */   }
/*     */   
/*     */   public static Boolean toBooleanObject(String str, String trueString, String falseString, String nullString) {
/* 682 */     if (str == null) {
/* 683 */       if (trueString == null)
/* 684 */         return Boolean.TRUE; 
/* 685 */       if (falseString == null)
/* 686 */         return Boolean.FALSE; 
/* 687 */       if (nullString == null)
/* 688 */         return null; 
/*     */     } else {
/* 690 */       if (str.equals(trueString))
/* 691 */         return Boolean.TRUE; 
/* 692 */       if (str.equals(falseString))
/* 693 */         return Boolean.FALSE; 
/* 694 */       if (str.equals(nullString))
/* 695 */         return null; 
/*     */     } 
/* 698 */     throw new IllegalArgumentException("The String did not match any specified value");
/*     */   }
/*     */   
/*     */   public static boolean toBoolean(String str) {
/* 729 */     return toBoolean(toBooleanObject(str));
/*     */   }
/*     */   
/*     */   public static boolean toBoolean(String str, String trueString, String falseString) {
/* 751 */     if (str == null) {
/* 752 */       if (trueString == null)
/* 753 */         return true; 
/* 754 */       if (falseString == null)
/* 755 */         return false; 
/*     */     } else {
/* 757 */       if (str.equals(trueString))
/* 758 */         return true; 
/* 759 */       if (str.equals(falseString))
/* 760 */         return false; 
/*     */     } 
/* 763 */     throw new IllegalArgumentException("The String did not match either specified value");
/*     */   }
/*     */   
/*     */   public static String toStringTrueFalse(Boolean bool) {
/* 783 */     return toString(bool, "true", "false", null);
/*     */   }
/*     */   
/*     */   public static String toStringOnOff(Boolean bool) {
/* 801 */     return toString(bool, "on", "off", null);
/*     */   }
/*     */   
/*     */   public static String toStringYesNo(Boolean bool) {
/* 819 */     return toString(bool, "yes", "no", null);
/*     */   }
/*     */   
/*     */   public static String toString(Boolean bool, String trueString, String falseString, String nullString) {
/* 841 */     if (bool == null)
/* 842 */       return nullString; 
/* 844 */     return bool.booleanValue() ? trueString : falseString;
/*     */   }
/*     */   
/*     */   public static String toStringTrueFalse(boolean bool) {
/* 863 */     return toString(bool, "true", "false");
/*     */   }
/*     */   
/*     */   public static String toStringOnOff(boolean bool) {
/* 880 */     return toString(bool, "on", "off");
/*     */   }
/*     */   
/*     */   public static String toStringYesNo(boolean bool) {
/* 897 */     return toString(bool, "yes", "no");
/*     */   }
/*     */   
/*     */   public static String toString(boolean bool, String trueString, String falseString) {
/* 916 */     return bool ? trueString : falseString;
/*     */   }
/*     */   
/*     */   public static boolean xor(boolean[] array) {
/* 937 */     if (array == null)
/* 938 */       throw new IllegalArgumentException("The Array must not be null"); 
/* 939 */     if (array.length == 0)
/* 940 */       throw new IllegalArgumentException("Array is empty"); 
/* 944 */     int trueCount = 0;
/* 945 */     for (int i = 0; i < array.length; i++) {
/* 948 */       if (array[i])
/* 949 */         if (trueCount < 1) {
/* 950 */           trueCount++;
/*     */         } else {
/* 952 */           return false;
/*     */         }  
/*     */     } 
/* 958 */     return (trueCount == 1);
/*     */   }
/*     */   
/*     */   public static Boolean xor(Boolean[] array) {
/* 977 */     if (array == null)
/* 978 */       throw new IllegalArgumentException("The Array must not be null"); 
/* 979 */     if (array.length == 0)
/* 980 */       throw new IllegalArgumentException("Array is empty"); 
/* 982 */     boolean[] primitive = null;
/*     */     try {
/* 984 */       primitive = ArrayUtils.toPrimitive(array);
/* 985 */     } catch (NullPointerException ex) {
/* 986 */       throw new IllegalArgumentException("The array must not contain any null elements");
/*     */     } 
/* 988 */     return xor(primitive) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\BooleanUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */