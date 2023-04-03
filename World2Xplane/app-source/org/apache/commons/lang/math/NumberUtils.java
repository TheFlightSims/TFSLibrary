/*      */ package org.apache.commons.lang.math;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ 
/*      */ public class NumberUtils {
/*   41 */   public static final Long LONG_ZERO = new Long(0L);
/*      */   
/*   43 */   public static final Long LONG_ONE = new Long(1L);
/*      */   
/*   45 */   public static final Long LONG_MINUS_ONE = new Long(-1L);
/*      */   
/*   47 */   public static final Integer INTEGER_ZERO = new Integer(0);
/*      */   
/*   49 */   public static final Integer INTEGER_ONE = new Integer(1);
/*      */   
/*   51 */   public static final Integer INTEGER_MINUS_ONE = new Integer(-1);
/*      */   
/*   53 */   public static final Short SHORT_ZERO = new Short((short)0);
/*      */   
/*   55 */   public static final Short SHORT_ONE = new Short((short)1);
/*      */   
/*   57 */   public static final Short SHORT_MINUS_ONE = new Short((short)-1);
/*      */   
/*   59 */   public static final Byte BYTE_ZERO = new Byte((byte)0);
/*      */   
/*   61 */   public static final Byte BYTE_ONE = new Byte((byte)1);
/*      */   
/*   63 */   public static final Byte BYTE_MINUS_ONE = new Byte((byte)-1);
/*      */   
/*   65 */   public static final Double DOUBLE_ZERO = new Double(0.0D);
/*      */   
/*   67 */   public static final Double DOUBLE_ONE = new Double(1.0D);
/*      */   
/*   69 */   public static final Double DOUBLE_MINUS_ONE = new Double(-1.0D);
/*      */   
/*   71 */   public static final Float FLOAT_ZERO = new Float(0.0F);
/*      */   
/*   73 */   public static final Float FLOAT_ONE = new Float(1.0F);
/*      */   
/*   75 */   public static final Float FLOAT_MINUS_ONE = new Float(-1.0F);
/*      */   
/*      */   public static int stringToInt(String str) {
/*  108 */     return toInt(str);
/*      */   }
/*      */   
/*      */   public static int toInt(String str) {
/*  129 */     return toInt(str, 0);
/*      */   }
/*      */   
/*      */   public static int stringToInt(String str, int defaultValue) {
/*  151 */     return toInt(str, defaultValue);
/*      */   }
/*      */   
/*      */   public static int toInt(String str, int defaultValue) {
/*  172 */     if (str == null)
/*  173 */       return defaultValue; 
/*      */     try {
/*  176 */       return Integer.parseInt(str);
/*  177 */     } catch (NumberFormatException nfe) {
/*  178 */       return defaultValue;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static long toLong(String str) {
/*  200 */     return toLong(str, 0L);
/*      */   }
/*      */   
/*      */   public static long toLong(String str, long defaultValue) {
/*  221 */     if (str == null)
/*  222 */       return defaultValue; 
/*      */     try {
/*  225 */       return Long.parseLong(str);
/*  226 */     } catch (NumberFormatException nfe) {
/*  227 */       return defaultValue;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static float toFloat(String str) {
/*  250 */     return toFloat(str, 0.0F);
/*      */   }
/*      */   
/*      */   public static float toFloat(String str, float defaultValue) {
/*  273 */     if (str == null)
/*  274 */       return defaultValue; 
/*      */     try {
/*  277 */       return Float.parseFloat(str);
/*  278 */     } catch (NumberFormatException nfe) {
/*  279 */       return defaultValue;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static double toDouble(String str) {
/*  302 */     return toDouble(str, 0.0D);
/*      */   }
/*      */   
/*      */   public static double toDouble(String str, double defaultValue) {
/*  325 */     if (str == null)
/*  326 */       return defaultValue; 
/*      */     try {
/*  329 */       return Double.parseDouble(str);
/*  330 */     } catch (NumberFormatException nfe) {
/*  331 */       return defaultValue;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static byte toByte(String str) {
/*  354 */     return toByte(str, (byte)0);
/*      */   }
/*      */   
/*      */   public static byte toByte(String str, byte defaultValue) {
/*  375 */     if (str == null)
/*  376 */       return defaultValue; 
/*      */     try {
/*  379 */       return Byte.parseByte(str);
/*  380 */     } catch (NumberFormatException nfe) {
/*  381 */       return defaultValue;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static short toShort(String str) {
/*  403 */     return toShort(str, (short)0);
/*      */   }
/*      */   
/*      */   public static short toShort(String str, short defaultValue) {
/*  424 */     if (str == null)
/*  425 */       return defaultValue; 
/*      */     try {
/*  428 */       return Short.parseShort(str);
/*  429 */     } catch (NumberFormatException nfe) {
/*  430 */       return defaultValue;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Number createNumber(String str) throws NumberFormatException {
/*      */     String mant, dec, exp;
/*  497 */     if (str == null)
/*  498 */       return null; 
/*  500 */     if (StringUtils.isBlank(str))
/*  501 */       throw new NumberFormatException("A blank string is not a valid number"); 
/*  503 */     if (str.startsWith("--"))
/*  508 */       return null; 
/*  510 */     if (str.startsWith("0x") || str.startsWith("-0x"))
/*  511 */       return createInteger(str); 
/*  513 */     char lastChar = str.charAt(str.length() - 1);
/*  517 */     int decPos = str.indexOf('.');
/*  518 */     int expPos = str.indexOf('e') + str.indexOf('E') + 1;
/*  520 */     if (decPos > -1) {
/*  522 */       if (expPos > -1) {
/*  523 */         if (expPos < decPos || expPos > str.length())
/*  524 */           throw new NumberFormatException(str + " is not a valid number."); 
/*  526 */         dec = str.substring(decPos + 1, expPos);
/*      */       } else {
/*  528 */         dec = str.substring(decPos + 1);
/*      */       } 
/*  530 */       mant = str.substring(0, decPos);
/*      */     } else {
/*  532 */       if (expPos > -1) {
/*  533 */         if (expPos > str.length())
/*  534 */           throw new NumberFormatException(str + " is not a valid number."); 
/*  536 */         mant = str.substring(0, expPos);
/*      */       } else {
/*  538 */         mant = str;
/*      */       } 
/*  540 */       dec = null;
/*      */     } 
/*  542 */     if (!Character.isDigit(lastChar) && lastChar != '.') {
/*  543 */       if (expPos > -1 && expPos < str.length() - 1) {
/*  544 */         exp = str.substring(expPos + 1, str.length() - 1);
/*      */       } else {
/*  546 */         exp = null;
/*      */       } 
/*  549 */       String numeric = str.substring(0, str.length() - 1);
/*  550 */       boolean bool = (isAllZeros(mant) && isAllZeros(exp));
/*  551 */       switch (lastChar) {
/*      */         case 'L':
/*      */         case 'l':
/*  554 */           if (dec == null && exp == null && ((numeric.charAt(0) == '-' && isDigits(numeric.substring(1))) || isDigits(numeric)))
/*      */             try {
/*  558 */               return createLong(numeric);
/*  559 */             } catch (NumberFormatException nfe) {
/*  562 */               return createBigInteger(numeric);
/*      */             }  
/*  565 */           throw new NumberFormatException(str + " is not a valid number.");
/*      */         case 'F':
/*      */         case 'f':
/*      */           try {
/*  569 */             Float f = createFloat(numeric);
/*  570 */             if (!f.isInfinite() && (f.floatValue() != 0.0F || bool))
/*  573 */               return f; 
/*  576 */           } catch (NumberFormatException nfe) {}
/*      */         case 'D':
/*      */         case 'd':
/*      */           try {
/*  583 */             Double d = createDouble(numeric);
/*  584 */             if (!d.isInfinite() && (d.floatValue() != 0.0D || bool))
/*  585 */               return d; 
/*  587 */           } catch (NumberFormatException nfe) {}
/*      */           try {
/*  591 */             return createBigDecimal(numeric);
/*  592 */           } catch (NumberFormatException e) {
/*      */             break;
/*      */           } 
/*      */       } 
/*  597 */       throw new NumberFormatException(str + " is not a valid number.");
/*      */     } 
/*  603 */     if (expPos > -1 && expPos < str.length() - 1) {
/*  604 */       exp = str.substring(expPos + 1, str.length());
/*      */     } else {
/*  606 */       exp = null;
/*      */     } 
/*  608 */     if (dec == null && exp == null)
/*      */       try {
/*  611 */         return createInteger(str);
/*  612 */       } catch (NumberFormatException nfe) {
/*      */         try {
/*  616 */           return createLong(str);
/*  617 */         } catch (NumberFormatException numberFormatException) {
/*  620 */           return createBigInteger(str);
/*      */         } 
/*      */       }  
/*  624 */     boolean allZeros = (isAllZeros(mant) && isAllZeros(exp));
/*      */     try {
/*  626 */       Float f = createFloat(str);
/*  627 */       if (!f.isInfinite() && (f.floatValue() != 0.0F || allZeros))
/*  628 */         return f; 
/*  630 */     } catch (NumberFormatException nfe) {}
/*      */     try {
/*  634 */       Double d = createDouble(str);
/*  635 */       if (!d.isInfinite() && (d.doubleValue() != 0.0D || allZeros))
/*  636 */         return d; 
/*  638 */     } catch (NumberFormatException nfe) {}
/*  642 */     return createBigDecimal(str);
/*      */   }
/*      */   
/*      */   private static boolean isAllZeros(String str) {
/*  657 */     if (str == null)
/*  658 */       return true; 
/*  660 */     for (int i = str.length() - 1; i >= 0; i--) {
/*  661 */       if (str.charAt(i) != '0')
/*  662 */         return false; 
/*      */     } 
/*  665 */     return (str.length() > 0);
/*      */   }
/*      */   
/*      */   public static Float createFloat(String str) {
/*  679 */     if (str == null)
/*  680 */       return null; 
/*  682 */     return Float.valueOf(str);
/*      */   }
/*      */   
/*      */   public static Double createDouble(String str) {
/*  695 */     if (str == null)
/*  696 */       return null; 
/*  698 */     return Double.valueOf(str);
/*      */   }
/*      */   
/*      */   public static Integer createInteger(String str) {
/*  712 */     if (str == null)
/*  713 */       return null; 
/*  716 */     return Integer.decode(str);
/*      */   }
/*      */   
/*      */   public static Long createLong(String str) {
/*  729 */     if (str == null)
/*  730 */       return null; 
/*  732 */     return Long.valueOf(str);
/*      */   }
/*      */   
/*      */   public static BigInteger createBigInteger(String str) {
/*  745 */     if (str == null)
/*  746 */       return null; 
/*  748 */     return new BigInteger(str);
/*      */   }
/*      */   
/*      */   public static BigDecimal createBigDecimal(String str) {
/*  761 */     if (str == null)
/*  762 */       return null; 
/*  765 */     if (StringUtils.isBlank(str))
/*  766 */       throw new NumberFormatException("A blank string is not a valid number"); 
/*  768 */     return new BigDecimal(str);
/*      */   }
/*      */   
/*      */   public static long min(long[] array) {
/*  783 */     if (array == null)
/*  784 */       throw new IllegalArgumentException("The Array must not be null"); 
/*  785 */     if (array.length == 0)
/*  786 */       throw new IllegalArgumentException("Array cannot be empty."); 
/*  790 */     long min = array[0];
/*  791 */     for (int i = 1; i < array.length; i++) {
/*  792 */       if (array[i] < min)
/*  793 */         min = array[i]; 
/*      */     } 
/*  797 */     return min;
/*      */   }
/*      */   
/*      */   public static int min(int[] array) {
/*  810 */     if (array == null)
/*  811 */       throw new IllegalArgumentException("The Array must not be null"); 
/*  812 */     if (array.length == 0)
/*  813 */       throw new IllegalArgumentException("Array cannot be empty."); 
/*  817 */     int min = array[0];
/*  818 */     for (int j = 1; j < array.length; j++) {
/*  819 */       if (array[j] < min)
/*  820 */         min = array[j]; 
/*      */     } 
/*  824 */     return min;
/*      */   }
/*      */   
/*      */   public static short min(short[] array) {
/*  837 */     if (array == null)
/*  838 */       throw new IllegalArgumentException("The Array must not be null"); 
/*  839 */     if (array.length == 0)
/*  840 */       throw new IllegalArgumentException("Array cannot be empty."); 
/*  844 */     short min = array[0];
/*  845 */     for (int i = 1; i < array.length; i++) {
/*  846 */       if (array[i] < min)
/*  847 */         min = array[i]; 
/*      */     } 
/*  851 */     return min;
/*      */   }
/*      */   
/*      */   public static byte min(byte[] array) {
/*  864 */     if (array == null)
/*  865 */       throw new IllegalArgumentException("The Array must not be null"); 
/*  866 */     if (array.length == 0)
/*  867 */       throw new IllegalArgumentException("Array cannot be empty."); 
/*  871 */     byte min = array[0];
/*  872 */     for (int i = 1; i < array.length; i++) {
/*  873 */       if (array[i] < min)
/*  874 */         min = array[i]; 
/*      */     } 
/*  878 */     return min;
/*      */   }
/*      */   
/*      */   public static double min(double[] array) {
/*  892 */     if (array == null)
/*  893 */       throw new IllegalArgumentException("The Array must not be null"); 
/*  894 */     if (array.length == 0)
/*  895 */       throw new IllegalArgumentException("Array cannot be empty."); 
/*  899 */     double min = array[0];
/*  900 */     for (int i = 1; i < array.length; i++) {
/*  901 */       if (Double.isNaN(array[i]))
/*  902 */         return Double.NaN; 
/*  904 */       if (array[i] < min)
/*  905 */         min = array[i]; 
/*      */     } 
/*  909 */     return min;
/*      */   }
/*      */   
/*      */   public static float min(float[] array) {
/*  923 */     if (array == null)
/*  924 */       throw new IllegalArgumentException("The Array must not be null"); 
/*  925 */     if (array.length == 0)
/*  926 */       throw new IllegalArgumentException("Array cannot be empty."); 
/*  930 */     float min = array[0];
/*  931 */     for (int i = 1; i < array.length; i++) {
/*  932 */       if (Float.isNaN(array[i]))
/*  933 */         return Float.NaN; 
/*  935 */       if (array[i] < min)
/*  936 */         min = array[i]; 
/*      */     } 
/*  940 */     return min;
/*      */   }
/*      */   
/*      */   public static long max(long[] array) {
/*  955 */     if (array == null)
/*  956 */       throw new IllegalArgumentException("The Array must not be null"); 
/*  957 */     if (array.length == 0)
/*  958 */       throw new IllegalArgumentException("Array cannot be empty."); 
/*  962 */     long max = array[0];
/*  963 */     for (int j = 1; j < array.length; j++) {
/*  964 */       if (array[j] > max)
/*  965 */         max = array[j]; 
/*      */     } 
/*  969 */     return max;
/*      */   }
/*      */   
/*      */   public static int max(int[] array) {
/*  982 */     if (array == null)
/*  983 */       throw new IllegalArgumentException("The Array must not be null"); 
/*  984 */     if (array.length == 0)
/*  985 */       throw new IllegalArgumentException("Array cannot be empty."); 
/*  989 */     int max = array[0];
/*  990 */     for (int j = 1; j < array.length; j++) {
/*  991 */       if (array[j] > max)
/*  992 */         max = array[j]; 
/*      */     } 
/*  996 */     return max;
/*      */   }
/*      */   
/*      */   public static short max(short[] array) {
/* 1009 */     if (array == null)
/* 1010 */       throw new IllegalArgumentException("The Array must not be null"); 
/* 1011 */     if (array.length == 0)
/* 1012 */       throw new IllegalArgumentException("Array cannot be empty."); 
/* 1016 */     short max = array[0];
/* 1017 */     for (int i = 1; i < array.length; i++) {
/* 1018 */       if (array[i] > max)
/* 1019 */         max = array[i]; 
/*      */     } 
/* 1023 */     return max;
/*      */   }
/*      */   
/*      */   public static byte max(byte[] array) {
/* 1036 */     if (array == null)
/* 1037 */       throw new IllegalArgumentException("The Array must not be null"); 
/* 1038 */     if (array.length == 0)
/* 1039 */       throw new IllegalArgumentException("Array cannot be empty."); 
/* 1043 */     byte max = array[0];
/* 1044 */     for (int i = 1; i < array.length; i++) {
/* 1045 */       if (array[i] > max)
/* 1046 */         max = array[i]; 
/*      */     } 
/* 1050 */     return max;
/*      */   }
/*      */   
/*      */   public static double max(double[] array) {
/* 1064 */     if (array == null)
/* 1065 */       throw new IllegalArgumentException("The Array must not be null"); 
/* 1066 */     if (array.length == 0)
/* 1067 */       throw new IllegalArgumentException("Array cannot be empty."); 
/* 1071 */     double max = array[0];
/* 1072 */     for (int j = 1; j < array.length; j++) {
/* 1073 */       if (Double.isNaN(array[j]))
/* 1074 */         return Double.NaN; 
/* 1076 */       if (array[j] > max)
/* 1077 */         max = array[j]; 
/*      */     } 
/* 1081 */     return max;
/*      */   }
/*      */   
/*      */   public static float max(float[] array) {
/* 1095 */     if (array == null)
/* 1096 */       throw new IllegalArgumentException("The Array must not be null"); 
/* 1097 */     if (array.length == 0)
/* 1098 */       throw new IllegalArgumentException("Array cannot be empty."); 
/* 1102 */     float max = array[0];
/* 1103 */     for (int j = 1; j < array.length; j++) {
/* 1104 */       if (Float.isNaN(array[j]))
/* 1105 */         return Float.NaN; 
/* 1107 */       if (array[j] > max)
/* 1108 */         max = array[j]; 
/*      */     } 
/* 1112 */     return max;
/*      */   }
/*      */   
/*      */   public static long min(long a, long b, long c) {
/* 1126 */     if (b < a)
/* 1127 */       a = b; 
/* 1129 */     if (c < a)
/* 1130 */       a = c; 
/* 1132 */     return a;
/*      */   }
/*      */   
/*      */   public static int min(int a, int b, int c) {
/* 1144 */     if (b < a)
/* 1145 */       a = b; 
/* 1147 */     if (c < a)
/* 1148 */       a = c; 
/* 1150 */     return a;
/*      */   }
/*      */   
/*      */   public static short min(short a, short b, short c) {
/* 1162 */     if (b < a)
/* 1163 */       a = b; 
/* 1165 */     if (c < a)
/* 1166 */       a = c; 
/* 1168 */     return a;
/*      */   }
/*      */   
/*      */   public static byte min(byte a, byte b, byte c) {
/* 1180 */     if (b < a)
/* 1181 */       a = b; 
/* 1183 */     if (c < a)
/* 1184 */       a = c; 
/* 1186 */     return a;
/*      */   }
/*      */   
/*      */   public static double min(double a, double b, double c) {
/* 1202 */     return Math.min(Math.min(a, b), c);
/*      */   }
/*      */   
/*      */   public static float min(float a, float b, float c) {
/* 1218 */     return Math.min(Math.min(a, b), c);
/*      */   }
/*      */   
/*      */   public static long max(long a, long b, long c) {
/* 1232 */     if (b > a)
/* 1233 */       a = b; 
/* 1235 */     if (c > a)
/* 1236 */       a = c; 
/* 1238 */     return a;
/*      */   }
/*      */   
/*      */   public static int max(int a, int b, int c) {
/* 1250 */     if (b > a)
/* 1251 */       a = b; 
/* 1253 */     if (c > a)
/* 1254 */       a = c; 
/* 1256 */     return a;
/*      */   }
/*      */   
/*      */   public static short max(short a, short b, short c) {
/* 1268 */     if (b > a)
/* 1269 */       a = b; 
/* 1271 */     if (c > a)
/* 1272 */       a = c; 
/* 1274 */     return a;
/*      */   }
/*      */   
/*      */   public static byte max(byte a, byte b, byte c) {
/* 1286 */     if (b > a)
/* 1287 */       a = b; 
/* 1289 */     if (c > a)
/* 1290 */       a = c; 
/* 1292 */     return a;
/*      */   }
/*      */   
/*      */   public static double max(double a, double b, double c) {
/* 1308 */     return Math.max(Math.max(a, b), c);
/*      */   }
/*      */   
/*      */   public static float max(float a, float b, float c) {
/* 1324 */     return Math.max(Math.max(a, b), c);
/*      */   }
/*      */   
/*      */   public static int compare(double lhs, double rhs) {
/* 1363 */     if (lhs < rhs)
/* 1364 */       return -1; 
/* 1366 */     if (lhs > rhs)
/* 1367 */       return 1; 
/* 1373 */     long lhsBits = Double.doubleToLongBits(lhs);
/* 1374 */     long rhsBits = Double.doubleToLongBits(rhs);
/* 1375 */     if (lhsBits == rhsBits)
/* 1376 */       return 0; 
/* 1384 */     if (lhsBits < rhsBits)
/* 1385 */       return -1; 
/* 1387 */     return 1;
/*      */   }
/*      */   
/*      */   public static int compare(float lhs, float rhs) {
/* 1424 */     if (lhs < rhs)
/* 1425 */       return -1; 
/* 1427 */     if (lhs > rhs)
/* 1428 */       return 1; 
/* 1434 */     int lhsBits = Float.floatToIntBits(lhs);
/* 1435 */     int rhsBits = Float.floatToIntBits(rhs);
/* 1436 */     if (lhsBits == rhsBits)
/* 1437 */       return 0; 
/* 1445 */     if (lhsBits < rhsBits)
/* 1446 */       return -1; 
/* 1448 */     return 1;
/*      */   }
/*      */   
/*      */   public static boolean isDigits(String str) {
/* 1464 */     if (StringUtils.isEmpty(str))
/* 1465 */       return false; 
/* 1467 */     for (int i = 0; i < str.length(); i++) {
/* 1468 */       if (!Character.isDigit(str.charAt(i)))
/* 1469 */         return false; 
/*      */     } 
/* 1472 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isNumber(String str) {
/* 1489 */     if (StringUtils.isEmpty(str))
/* 1490 */       return false; 
/* 1492 */     char[] chars = str.toCharArray();
/* 1493 */     int sz = chars.length;
/* 1494 */     boolean hasExp = false;
/* 1495 */     boolean hasDecPoint = false;
/* 1496 */     boolean allowSigns = false;
/* 1497 */     boolean foundDigit = false;
/* 1499 */     int start = (chars[0] == '-') ? 1 : 0;
/* 1500 */     if (sz > start + 1 && 
/* 1501 */       chars[start] == '0' && chars[start + 1] == 'x') {
/* 1502 */       int j = start + 2;
/* 1503 */       if (j == sz)
/* 1504 */         return false; 
/* 1507 */       for (; j < chars.length; j++) {
/* 1508 */         if ((chars[j] < '0' || chars[j] > '9') && (chars[j] < 'a' || chars[j] > 'f') && (chars[j] < 'A' || chars[j] > 'F'))
/* 1511 */           return false; 
/*      */       } 
/* 1514 */       return true;
/*      */     } 
/* 1517 */     sz--;
/* 1519 */     int i = start;
/* 1522 */     while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
/* 1523 */       if (chars[i] >= '0' && chars[i] <= '9') {
/* 1524 */         foundDigit = true;
/* 1525 */         allowSigns = false;
/* 1527 */       } else if (chars[i] == '.') {
/* 1528 */         if (hasDecPoint || hasExp)
/* 1530 */           return false; 
/* 1532 */         hasDecPoint = true;
/* 1533 */       } else if (chars[i] == 'e' || chars[i] == 'E') {
/* 1535 */         if (hasExp)
/* 1537 */           return false; 
/* 1539 */         if (!foundDigit)
/* 1540 */           return false; 
/* 1542 */         hasExp = true;
/* 1543 */         allowSigns = true;
/* 1544 */       } else if (chars[i] == '+' || chars[i] == '-') {
/* 1545 */         if (!allowSigns)
/* 1546 */           return false; 
/* 1548 */         allowSigns = false;
/* 1549 */         foundDigit = false;
/*      */       } else {
/* 1551 */         return false;
/*      */       } 
/* 1553 */       i++;
/*      */     } 
/* 1555 */     if (i < chars.length) {
/* 1556 */       if (chars[i] >= '0' && chars[i] <= '9')
/* 1558 */         return true; 
/* 1560 */       if (chars[i] == 'e' || chars[i] == 'E')
/* 1562 */         return false; 
/* 1564 */       if (chars[i] == '.') {
/* 1565 */         if (hasDecPoint || hasExp)
/* 1567 */           return false; 
/* 1570 */         return foundDigit;
/*      */       } 
/* 1572 */       if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F'))
/* 1577 */         return foundDigit; 
/* 1579 */       if (chars[i] == 'l' || chars[i] == 'L')
/* 1582 */         return (foundDigit && !hasExp); 
/* 1585 */       return false;
/*      */     } 
/* 1589 */     return (!allowSigns && foundDigit);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\NumberUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */