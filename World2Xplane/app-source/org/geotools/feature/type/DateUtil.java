/*      */ package org.geotools.feature.type;
/*      */ 
/*      */ import java.sql.Date;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import java.util.TimeZone;
/*      */ 
/*      */ public abstract class DateUtil {
/*      */   private static final int MSPERMINUTE = 60000;
/*      */   
/*      */   private static final int MSPERHOUR = 3600000;
/*      */   
/*      */   private static final int MSPERDAY = 86400000;
/*      */   
/*      */   private static final long LMSPERDAY = 86400000L;
/*      */   
/*      */   private static final long MSPERYEAR = 31536000000L;
/*      */   
/*      */   private static final long MSPERAVGYEAR = 31557600000L;
/*      */   
/*      */   private static final long MSPERCENTURY = 3155695200000L;
/*      */   
/*      */   private static final long TIME_BASE = 62135596800000L;
/*      */   
/*   75 */   private static final int[] MONTHS_NONLEAP = new int[] { 
/*   75 */       0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 
/*   75 */       304, 334, 365 };
/*      */   
/*   80 */   private static final int[] MONTHS_LEAP = new int[] { 
/*   80 */       0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 
/*   80 */       305, 335, 366 };
/*      */   
/*   85 */   private static final long[] BIAS_MONTHMS = new long[] { 
/*   85 */       0L, 0L, 0L, 0L, 2678400000L, 5270400000L, 7948800000L, 10540800000L, 13219200000L, 15897600000L, 
/*   85 */       18489600000L, 21168000000L, 23760000000L, 26438400000L, 29116800000L };
/*      */   
/*      */   private static final char PAD_CHAR = '=';
/*      */   
/*   96 */   private static final char[] s_base64Chars = new char[] { 
/*   96 */       'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
/*   96 */       'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
/*   96 */       'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
/*   96 */       'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
/*   96 */       'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
/*   96 */       'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', 
/*   96 */       '8', '9', '+', '/' };
/*      */   
/*  105 */   private static final byte[] s_base64Values = new byte[128];
/*      */   
/*      */   static {
/*      */     int i;
/*  108 */     for (i = 0; i < s_base64Values.length; i++)
/*  109 */       s_base64Values[i] = -1; 
/*  112 */     s_base64Values[61] = 0;
/*  114 */     for (i = 0; i < s_base64Chars.length; i++)
/*  115 */       s_base64Values[s_base64Chars[i]] = (byte)i; 
/*      */   }
/*      */   
/*      */   private static int parseDigits(String text, int offset, int length) throws IllegalArgumentException {
/*  135 */     int value = 0;
/*  137 */     if (length > 9) {
/*      */       try {
/*  140 */         value = Integer.parseInt(text.substring(offset, offset + length));
/*  141 */       } catch (NumberFormatException ex) {
/*  142 */         throw new IllegalArgumentException(ex.getMessage());
/*      */       } 
/*      */     } else {
/*  146 */       int limit = offset + length;
/*  148 */       while (offset < limit) {
/*  149 */         char chr = text.charAt(offset++);
/*  151 */         if (chr >= '0' && chr <= '9') {
/*  152 */           value = value * 10 + chr - 48;
/*      */           continue;
/*      */         } 
/*  154 */         throw new IllegalArgumentException("Non-digit in number value");
/*      */       } 
/*      */     } 
/*  160 */     return value;
/*      */   }
/*      */   
/*      */   public static int parseInt(String text) throws IllegalArgumentException {
/*  175 */     text = text.trim();
/*  177 */     int offset = 0;
/*  178 */     int limit = text.length();
/*  180 */     if (limit == 0)
/*  181 */       throw new IllegalArgumentException("Empty number value"); 
/*  185 */     boolean negate = false;
/*  186 */     char chr = text.charAt(0);
/*  188 */     if (chr == '-') {
/*  189 */       if (limit > 9)
/*      */         try {
/*  192 */           return Integer.parseInt(text);
/*  193 */         } catch (NumberFormatException ex) {
/*  194 */           throw new IllegalArgumentException(ex.getMessage());
/*      */         }  
/*  197 */       negate = true;
/*  198 */       offset++;
/*  200 */     } else if (chr == '+') {
/*  201 */       offset++;
/*      */     } 
/*  204 */     if (offset >= limit)
/*  205 */       throw new IllegalArgumentException("Invalid number format"); 
/*  209 */     int value = parseDigits(text, offset, limit - offset);
/*  211 */     if (negate)
/*  212 */       return -value; 
/*  214 */     return value;
/*      */   }
/*      */   
/*      */   public static String serializeInt(int value) {
/*  226 */     return Integer.toString(value);
/*      */   }
/*      */   
/*      */   public static long parseLong(String text) throws IllegalArgumentException {
/*  241 */     text = text.trim();
/*  243 */     int offset = 0;
/*  244 */     int limit = text.length();
/*  246 */     if (limit == 0)
/*  247 */       throw new IllegalArgumentException("Empty number value"); 
/*  251 */     boolean negate = false;
/*  252 */     char chr = text.charAt(0);
/*  254 */     if (chr == '-') {
/*  255 */       negate = true;
/*  256 */       offset++;
/*  257 */     } else if (chr == '+') {
/*  258 */       offset++;
/*      */     } 
/*  261 */     if (offset >= limit)
/*  262 */       throw new IllegalArgumentException("Invalid number format"); 
/*  266 */     long value = 0L;
/*  268 */     if (limit - offset > 18) {
/*  270 */       if (chr == '+')
/*  271 */         text = text.substring(1); 
/*      */       try {
/*  275 */         value = Long.parseLong(text);
/*  276 */       } catch (NumberFormatException ex) {
/*  277 */         throw new IllegalArgumentException(ex.getMessage());
/*      */       } 
/*      */     } else {
/*  281 */       while (offset < limit) {
/*  282 */         chr = text.charAt(offset++);
/*  284 */         if (chr >= '0' && chr <= '9') {
/*  285 */           value = value * 10L + (chr - 48);
/*      */           continue;
/*      */         } 
/*  287 */         throw new IllegalArgumentException("Non-digit in number value");
/*      */       } 
/*  292 */       if (negate)
/*  293 */         value = -value; 
/*      */     } 
/*  297 */     return value;
/*      */   }
/*      */   
/*      */   public static String serializeLong(long value) {
/*  308 */     return Long.toString(value);
/*      */   }
/*      */   
/*      */   public static short parseShort(String text) throws IllegalArgumentException {
/*  322 */     int value = parseInt(text);
/*  324 */     if (value < -32768 || value > 32767)
/*  325 */       throw new IllegalArgumentException("Value out of range"); 
/*  328 */     return (short)value;
/*      */   }
/*      */   
/*      */   public static String serializeShort(short value) {
/*  339 */     return Short.toString(value);
/*      */   }
/*      */   
/*      */   public static byte parseByte(String text) throws IllegalArgumentException {
/*  353 */     int value = parseInt(text);
/*  355 */     if (value < -128 || value > 127)
/*  356 */       throw new IllegalArgumentException("Value out of range"); 
/*  359 */     return (byte)value;
/*      */   }
/*      */   
/*      */   public static String serializeByte(byte value) {
/*  370 */     return Byte.toString(value);
/*      */   }
/*      */   
/*      */   public static boolean parseBoolean(String text) throws IllegalArgumentException {
/*  385 */     text = text.trim();
/*  387 */     if ("true".equals(text) || "1".equals(text))
/*  388 */       return true; 
/*  389 */     if ("false".equals(text) || "0".equals(text))
/*  390 */       return false; 
/*  392 */     throw new IllegalArgumentException("Invalid boolean value");
/*      */   }
/*      */   
/*      */   public static String serializeBoolean(boolean value) {
/*  405 */     return value ? "true" : "false";
/*      */   }
/*      */   
/*      */   public static char parseChar(String text) throws IllegalArgumentException {
/*  420 */     int value = parseInt(text);
/*  422 */     if (value < 0 || value > 65535)
/*  423 */       throw new IllegalArgumentException("Value out of range"); 
/*  426 */     return (char)value;
/*      */   }
/*      */   
/*      */   public static String serializeChar(char value) {
/*  437 */     return Integer.toString(value);
/*      */   }
/*      */   
/*      */   public static char parseCharString(String text) throws IllegalArgumentException {
/*  452 */     if (text.length() == 1)
/*  453 */       return text.charAt(0); 
/*  455 */     throw new IllegalArgumentException("Input must be a single character");
/*      */   }
/*      */   
/*      */   public static char deserializeCharString(String text) throws IllegalArgumentException {
/*  472 */     if (text == null)
/*  473 */       return Character.MIN_VALUE; 
/*  475 */     return parseCharString(text);
/*      */   }
/*      */   
/*      */   public static String serializeCharString(char value) {
/*  487 */     return String.valueOf(value);
/*      */   }
/*      */   
/*      */   public static float parseFloat(String text) throws IllegalArgumentException {
/*  503 */     text = text.trim();
/*  505 */     if ("-INF".equals(text))
/*  506 */       return Float.NEGATIVE_INFINITY; 
/*  507 */     if ("INF".equals(text))
/*  508 */       return Float.POSITIVE_INFINITY; 
/*      */     try {
/*  511 */       return Float.parseFloat(text);
/*  512 */     } catch (NumberFormatException ex) {
/*  513 */       throw new IllegalArgumentException(ex.getMessage());
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String serializeFloat(float value) {
/*  526 */     if (Float.isInfinite(value))
/*  527 */       return (value < 0.0F) ? "-INF" : "INF"; 
/*  529 */     return Float.toString(value);
/*      */   }
/*      */   
/*      */   public static double parseDouble(String text) throws IllegalArgumentException {
/*  547 */     text = text.trim();
/*  549 */     if ("-INF".equals(text))
/*  550 */       return Double.NEGATIVE_INFINITY; 
/*  551 */     if ("INF".equals(text))
/*  552 */       return Double.POSITIVE_INFINITY; 
/*      */     try {
/*  555 */       return Double.parseDouble(text);
/*  556 */     } catch (NumberFormatException ex) {
/*  557 */       throw new IllegalArgumentException(ex.getMessage());
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String serializeDouble(double value) {
/*  570 */     if (Double.isInfinite(value))
/*  571 */       return (value < 0.0D) ? "-INF" : "INF"; 
/*  573 */     return Double.toString(value);
/*      */   }
/*      */   
/*      */   public static long parseYear(String text) throws IllegalArgumentException {
/*  589 */     text = text.trim();
/*  591 */     boolean valid = true;
/*  592 */     int minc = 4;
/*  593 */     char chr = text.charAt(0);
/*  595 */     if (chr == '-') {
/*  596 */       minc = 5;
/*  597 */     } else if (chr == '+') {
/*  598 */       valid = false;
/*      */     } 
/*  601 */     if (text.length() < minc)
/*  602 */       valid = false; 
/*  605 */     if (!valid)
/*  606 */       throw new IllegalArgumentException("Invalid year format"); 
/*  610 */     int year = parseInt(text);
/*  612 */     if (year == 0)
/*  613 */       throw new IllegalArgumentException("Year value 0 is not allowed"); 
/*  616 */     if (year > 0)
/*  617 */       year--; 
/*  620 */     long day = year * 365L + (year / 4) - (year / 100) + (year / 400);
/*  623 */     return day * 86400000L - 62135596800000L;
/*      */   }
/*      */   
/*      */   public static long parseYearMonth(String text) throws IllegalArgumentException {
/*  639 */     text = text.trim();
/*  641 */     boolean valid = true;
/*  642 */     int minc = 7;
/*  643 */     char chr = text.charAt(0);
/*  645 */     if (chr == '-') {
/*  646 */       minc = 8;
/*  647 */     } else if (chr == '+') {
/*  648 */       valid = false;
/*      */     } 
/*  651 */     int split = text.length() - 3;
/*  653 */     if (text.length() < minc) {
/*  654 */       valid = false;
/*  656 */     } else if (text.charAt(split) != '-') {
/*  657 */       valid = false;
/*      */     } 
/*  661 */     if (!valid)
/*  662 */       throw new IllegalArgumentException("Invalid date format"); 
/*  666 */     int year = parseInt(text.substring(0, split));
/*  668 */     if (year == 0)
/*  669 */       throw new IllegalArgumentException("Year value 0 is not allowed"); 
/*  672 */     int month = parseDigits(text, split + 1, 2) - 1;
/*  674 */     if (month < 0 || month > 11)
/*  675 */       throw new IllegalArgumentException("Month value out of range"); 
/*  678 */     boolean leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
/*  681 */     if (year > 0)
/*  682 */       year--; 
/*  685 */     long day = year * 365L + (year / 4) - (year / 100) + (year / 400) + (leap ? MONTHS_LEAP : MONTHS_NONLEAP)[month];
/*  688 */     return day * 86400000L - 62135596800000L;
/*      */   }
/*      */   
/*      */   public static long parseDate(String text) throws IllegalArgumentException {
/*  704 */     boolean valid = true;
/*  705 */     int minc = 10;
/*  706 */     char chr = text.charAt(0);
/*  708 */     if (chr == '-') {
/*  709 */       minc = 11;
/*  710 */     } else if (chr == '+') {
/*  711 */       valid = false;
/*      */     } 
/*  714 */     int split = text.length() - 6;
/*  716 */     if (text.length() < minc) {
/*  717 */       valid = false;
/*  719 */     } else if (text.charAt(split) != '-' || text.charAt(split + 3) != '-') {
/*  720 */       valid = false;
/*      */     } 
/*  724 */     if (!valid)
/*  725 */       throw new IllegalArgumentException("Invalid date format"); 
/*  729 */     int year = parseInt(text.substring(0, split));
/*  731 */     if (year == 0)
/*  732 */       throw new IllegalArgumentException("Year value 0 is not allowed"); 
/*  735 */     int month = parseDigits(text, split + 1, 2) - 1;
/*  737 */     if (month < 0 || month > 11)
/*  738 */       throw new IllegalArgumentException("Month value out of range"); 
/*  741 */     long day = (parseDigits(text, split + 4, 2) - 1);
/*  742 */     boolean leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
/*  744 */     int[] starts = leap ? MONTHS_LEAP : MONTHS_NONLEAP;
/*  746 */     if (day < 0L || day >= (starts[month + 1] - starts[month]))
/*  747 */       throw new IllegalArgumentException("Day value out of range"); 
/*  750 */     if (year > 0)
/*  751 */       year--; 
/*  754 */     day += year * 365L + (year / 4) - (year / 100) + (year / 400) + starts[month];
/*  757 */     return day * 86400000L - 62135596800000L;
/*      */   }
/*      */   
/*      */   public static Date deserializeDate(String text) throws IllegalArgumentException {
/*  775 */     if (text == null)
/*  776 */       return null; 
/*  778 */     return new Date(parseDate(text));
/*      */   }
/*      */   
/*      */   public static Date deserializeSqlDate(String text) throws IllegalArgumentException {
/*  797 */     if (text == null)
/*  798 */       return null; 
/*  801 */     long time = parseDate(text);
/*  802 */     time += (TimeZone.getDefault().getRawOffset() + 7200000);
/*  804 */     return new Date(time);
/*      */   }
/*      */   
/*      */   public static long parseTime(String text, int start, int length) throws IllegalArgumentException {
/*  824 */     long milli = 0L;
/*  825 */     boolean valid = (length > start + 7 && text.charAt(start + 2) == ':' && text.charAt(start + 5) == ':');
/*  829 */     if (valid) {
/*  830 */       int hour = parseDigits(text, start, 2);
/*  831 */       int minute = parseDigits(text, start + 3, 2);
/*  832 */       int second = parseDigits(text, start + 6, 2);
/*  834 */       if (hour > 23 || minute > 59 || second > 60) {
/*  835 */         valid = false;
/*      */       } else {
/*  838 */         milli = (((hour * 60 + minute) * 60 + second) * 1000);
/*  839 */         start += 8;
/*  841 */         if (length > start) {
/*  843 */           if (text.charAt(length - 1) == 'Z') {
/*  844 */             length--;
/*      */           } else {
/*  846 */             char chr = text.charAt(length - 6);
/*  848 */             if (chr == '-' || chr == '+') {
/*  849 */               hour = parseDigits(text, length - 5, 2);
/*  850 */               minute = parseDigits(text, length - 2, 2);
/*  852 */               if (hour > 23 || minute > 59) {
/*  853 */                 valid = false;
/*      */               } else {
/*  855 */                 int offset = (hour * 60 + minute) * 60 * 1000;
/*  857 */                 if (chr == '-') {
/*  858 */                   milli += offset;
/*      */                 } else {
/*  860 */                   milli -= offset;
/*      */                 } 
/*      */               } 
/*  864 */               length -= 6;
/*      */             } 
/*      */           } 
/*  869 */           if (text.charAt(start) == '.') {
/*  870 */             double fraction = Double.parseDouble(text.substring(start, length));
/*  872 */             milli = (long)(milli + fraction * 1000.0D);
/*  873 */           } else if (length > start) {
/*  874 */             valid = false;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  881 */     if (valid)
/*  882 */       return milli; 
/*  884 */     throw new IllegalArgumentException("Invalid dateTime format");
/*      */   }
/*      */   
/*      */   public static long parseDateTime(String text) throws IllegalArgumentException {
/*  902 */     int split = text.indexOf('T');
/*  904 */     if (split < 0)
/*  905 */       throw new IllegalArgumentException("Missing 'T' separator in dateTime"); 
/*  909 */     return parseDate(text.substring(0, split)) + parseTime(text, split + 1, text.length());
/*      */   }
/*      */   
/*      */   public static Date deserializeDateTime(String text) throws IllegalArgumentException {
/*  929 */     if (text == null)
/*  930 */       return null; 
/*  932 */     return new Date(parseDateTime(text));
/*      */   }
/*      */   
/*      */   public static Timestamp deserializeTimestamp(String text) throws IllegalArgumentException {
/*  952 */     if (text == null)
/*  953 */       return null; 
/*  956 */     int split = text.indexOf('.');
/*  957 */     int nano = 0;
/*  959 */     if (split > 0) {
/*  961 */       if (text.indexOf('.', split) > 0)
/*  962 */         throw new IllegalArgumentException("Not a valid dateTime value"); 
/*  967 */       int limit = text.length();
/*  968 */       int scan = split;
/*  970 */       while (++scan < limit) {
/*  971 */         char chr = text.charAt(scan);
/*  973 */         if (chr < '0' || chr > '9')
/*      */           break; 
/*      */       } 
/*  979 */       int length = scan - split - 1;
/*  981 */       if (length > 9)
/*  982 */         length = 9; 
/*  985 */       nano = parseDigits(text, split + 1, length);
/*  988 */       while (length < 9) {
/*  989 */         nano *= 10;
/*  990 */         length++;
/*      */       } 
/*  994 */       if (scan < limit) {
/*  995 */         text = text.substring(0, split) + text.substring(scan);
/*      */       } else {
/*  997 */         text = text.substring(0, split);
/*      */       } 
/*      */     } 
/* 1002 */     Timestamp stamp = new Timestamp(parseDateTime(text));
/* 1003 */     stamp.setNanos(nano);
/* 1005 */     return stamp;
/*      */   }
/*      */   
/*      */   public static Time deserializeSqlTime(String text) throws IllegalArgumentException {
/* 1023 */     if (text == null)
/* 1024 */       return null; 
/* 1026 */     return new Time(parseTime(text, 0, text.length()));
/*      */   }
/*      */   
/*      */   protected static void formatYearNumber(long year, StringBuffer buff) {
/* 1040 */     if (year <= 0L) {
/* 1041 */       buff.append('-');
/* 1042 */       year = -(year - 1L);
/*      */     } 
/* 1046 */     if (year < 1000L) {
/* 1047 */       buff.append('0');
/* 1049 */       if (year < 100L) {
/* 1050 */         buff.append('0');
/* 1052 */         if (year < 10L)
/* 1053 */           buff.append('0'); 
/*      */       } 
/*      */     } 
/* 1059 */     buff.append(year);
/*      */   }
/*      */   
/*      */   protected static void formatTwoDigits(int value, StringBuffer buff) {
/* 1070 */     if (value < 10)
/* 1071 */       buff.append('0'); 
/* 1074 */     buff.append(value);
/*      */   }
/*      */   
/*      */   protected static void formatYear(long value, StringBuffer buff) {
/* 1092 */     long time = value + 26438400000L + 64800000L;
/* 1093 */     long century = time / 3155695200000L;
/* 1094 */     long adjusted = time + (century - century / 4L) * 86400000L;
/* 1095 */     int year = (int)(adjusted / 31557600000L);
/* 1097 */     if (adjusted < 0L)
/* 1098 */       year--; 
/* 1101 */     long yms = adjusted + 21600000L - (year * 365 + year / 4) * 86400000L;
/* 1103 */     int yday = (int)(yms / 86400000L);
/* 1104 */     int month = (5 * yday + 456) / 153;
/* 1106 */     if (month > 12)
/* 1107 */       year++; 
/* 1111 */     formatYearNumber(year, buff);
/*      */   }
/*      */   
/*      */   protected static long formatYearMonth(long value, StringBuffer buff) {
/* 1131 */     long time = value + 26438400000L + 64800000L;
/* 1132 */     long century = time / 3155695200000L;
/* 1133 */     long adjusted = time + (century - century / 4L) * 86400000L;
/* 1134 */     int year = (int)(adjusted / 31557600000L);
/* 1136 */     if (adjusted < 0L)
/* 1137 */       year--; 
/* 1140 */     long yms = adjusted + 21600000L - (year * 365 + year / 4) * 86400000L;
/* 1142 */     int yday = (int)(yms / 86400000L);
/* 1144 */     if (yday == 0) {
/* 1146 */       boolean bce = (year < 0);
/* 1148 */       if (bce)
/* 1149 */         year--; 
/* 1152 */       int dcnt = (year % 4 == 0) ? 366 : 365;
/* 1154 */       if (!bce)
/* 1155 */         year--; 
/* 1158 */       yms += dcnt * 86400000L;
/* 1159 */       yday += dcnt;
/*      */     } 
/* 1162 */     int month = (5 * yday + 456) / 153;
/* 1163 */     long rem = yms - BIAS_MONTHMS[month] - 86400000L;
/* 1165 */     if (month > 12) {
/* 1166 */       year++;
/* 1167 */       month -= 12;
/*      */     } 
/* 1171 */     formatYearNumber(year, buff);
/* 1172 */     buff.append('-');
/* 1173 */     formatTwoDigits(month, buff);
/* 1176 */     return rem;
/*      */   }
/*      */   
/*      */   protected static int formatYearMonthDay(long value, StringBuffer buff) {
/* 1192 */     long extra = formatYearMonth(value, buff);
/* 1195 */     int day = (int)(extra / 86400000L) + 1;
/* 1196 */     buff.append('-');
/* 1197 */     formatTwoDigits(day, buff);
/* 1200 */     return (int)(extra % 86400000L);
/*      */   }
/*      */   
/*      */   public static String serializeYear(long time) throws IllegalArgumentException {
/* 1216 */     StringBuffer buff = new StringBuffer(6);
/* 1217 */     formatYear(time + 62135596800000L, buff);
/* 1219 */     return buff.toString();
/*      */   }
/*      */   
/*      */   public static String serializeYear(Date date) throws IllegalArgumentException {
/* 1235 */     return serializeYear(date.getTime());
/*      */   }
/*      */   
/*      */   public static String serializeYearMonth(long time) throws IllegalArgumentException {
/* 1251 */     StringBuffer buff = new StringBuffer(12);
/* 1252 */     formatYearMonth(time + 62135596800000L, buff);
/* 1254 */     return buff.toString();
/*      */   }
/*      */   
/*      */   public static String serializeYearMonth(Date date) throws IllegalArgumentException {
/* 1270 */     return serializeYearMonth(date.getTime());
/*      */   }
/*      */   
/*      */   public static String serializeDate(long time) throws IllegalArgumentException {
/* 1286 */     StringBuffer buff = new StringBuffer(12);
/* 1287 */     formatYearMonthDay(time + 62135596800000L, buff);
/* 1289 */     return buff.toString();
/*      */   }
/*      */   
/*      */   public static String serializeDate(Date date) throws IllegalArgumentException {
/* 1305 */     long time = date.getTime();
/* 1306 */     time += TimeZone.getDefault().getOffset(time);
/* 1307 */     return serializeDate(time);
/*      */   }
/*      */   
/*      */   public static String serializeSqlDate(Date date) throws IllegalArgumentException {
/* 1323 */     return serializeDate(date);
/*      */   }
/*      */   
/*      */   public static void serializeTime(int time, StringBuffer buff) throws IllegalArgumentException {
/* 1340 */     formatTwoDigits(time / 3600000, buff);
/* 1341 */     time %= 3600000;
/* 1342 */     buff.append(':');
/* 1343 */     formatTwoDigits(time / 60000, buff);
/* 1344 */     time %= 60000;
/* 1345 */     buff.append(':');
/* 1346 */     formatTwoDigits(time / 1000, buff);
/* 1347 */     time %= 1000;
/* 1350 */     if (time > 0) {
/* 1351 */       buff.append('.');
/* 1352 */       buff.append(time / 100);
/* 1353 */       time %= 100;
/* 1355 */       if (time > 0) {
/* 1356 */         buff.append(time / 10);
/* 1357 */         time %= 10;
/* 1359 */         if (time > 0)
/* 1360 */           buff.append(time); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String serializeDateTime(long time, boolean zone) throws IllegalArgumentException {
/* 1381 */     StringBuffer buff = new StringBuffer(25);
/* 1382 */     int extra = formatYearMonthDay(time + 62135596800000L, buff);
/* 1385 */     buff.append('T');
/* 1386 */     serializeTime(extra, buff);
/* 1389 */     if (zone)
/* 1390 */       buff.append('Z'); 
/* 1393 */     return buff.toString();
/*      */   }
/*      */   
/*      */   public static String serializeDateTime(long time) throws IllegalArgumentException {
/* 1409 */     return serializeDateTime(time, false);
/*      */   }
/*      */   
/*      */   public static String serializeDateTime(Date date) throws IllegalArgumentException {
/* 1425 */     long time = date.getTime();
/* 1426 */     time += TimeZone.getDefault().getOffset(time);
/* 1427 */     return serializeDateTime(time, false);
/*      */   }
/*      */   
/*      */   public static String serializeTimestamp(Timestamp stamp) throws IllegalArgumentException {
/* 1444 */     int nano = stamp.getNanos();
/* 1446 */     if (nano > 0) {
/* 1448 */       String value = serializeInt(nano);
/* 1451 */       StringBuffer digits = new StringBuffer(9);
/* 1453 */       if (value.length() < 9) {
/* 1454 */         int lead = 9 - value.length();
/* 1456 */         for (int i = 0; i < lead; i++)
/* 1457 */           digits.append('0'); 
/*      */       } 
/* 1461 */       digits.append(value);
/* 1464 */       int last = 9;
/*      */       do {
/*      */       
/* 1466 */       } while (--last > 0 && 
/* 1467 */         digits.charAt(last) == '0');
/* 1472 */       digits.setLength(last);
/* 1475 */       return serializeDateTime(stamp.getTime(), false) + '.' + digits + 'Z';
/*      */     } 
/* 1478 */     return serializeDateTime(stamp.getTime(), true);
/*      */   }
/*      */   
/*      */   public static String serializeSqlTime(Time time) throws IllegalArgumentException {
/* 1496 */     StringBuffer buff = new StringBuffer(12);
/* 1497 */     long t = time.getTime();
/* 1498 */     t += TimeZone.getDefault().getOffset(t);
/* 1499 */     int extra = formatYearMonthDay(t + 62135596800000L, buff);
/* 1500 */     buff.delete(0, buff.length());
/* 1501 */     serializeTime(extra, buff);
/* 1503 */     return buff.toString();
/*      */   }
/*      */   
/*      */   public static boolean isEqual(Object a, Object b) {
/* 1518 */     return (a == null) ? ((b == null)) : a.equals(b);
/*      */   }
/*      */   
/*      */   public static int enumValue(String target, String[] enums, int[] vals) throws IllegalArgumentException {
/* 1539 */     int base = 0;
/* 1540 */     int limit = enums.length - 1;
/* 1542 */     while (base <= limit) {
/* 1543 */       int cur = base + limit >> 1;
/* 1544 */       int diff = target.compareTo(enums[cur]);
/* 1546 */       if (diff < 0) {
/* 1547 */         limit = cur - 1;
/*      */         continue;
/*      */       } 
/* 1548 */       if (diff > 0) {
/* 1549 */         base = cur + 1;
/*      */         continue;
/*      */       } 
/* 1550 */       if (vals != null)
/* 1551 */         return vals[cur]; 
/* 1553 */       return cur;
/*      */     } 
/* 1557 */     throw new IllegalArgumentException("Target value \"" + target + "\" not found in enumeration");
/*      */   }
/*      */   
/*      */   private static int decodeChunk(int base, char[] chrs, int fill, byte[] byts) throws IllegalArgumentException {
/* 1580 */     int length = 3;
/* 1582 */     if (chrs[base + 3] == '=') {
/* 1583 */       length = 2;
/* 1585 */       if (chrs[base + 2] == '=')
/* 1586 */         length = 1; 
/*      */     } 
/* 1591 */     int v0 = s_base64Values[chrs[base + 0]];
/* 1592 */     int v1 = s_base64Values[chrs[base + 1]];
/* 1593 */     int v2 = s_base64Values[chrs[base + 2]];
/* 1594 */     int v3 = s_base64Values[chrs[base + 3]];
/* 1597 */     switch (length) {
/*      */       case 3:
/* 1599 */         byts[fill + 2] = (byte)(v2 << 6 | v3);
/*      */       case 2:
/* 1602 */         byts[fill + 1] = (byte)(v1 << 4 | v2 >> 2);
/*      */       case 1:
/* 1605 */         byts[fill] = (byte)(v0 << 2 | v1 >> 4);
/*      */         break;
/*      */     } 
/* 1610 */     return length;
/*      */   }
/*      */   
/*      */   public static byte[] parseBase64(String text) throws IllegalArgumentException {
/* 1627 */     char[] chrs = new char[text.length()];
/* 1628 */     int length = 0;
/* 1630 */     for (int i = 0; i < text.length(); i++) {
/* 1631 */       char chr = text.charAt(i);
/* 1633 */       if (chr < '' && s_base64Values[chr] >= 0)
/* 1634 */         chrs[length++] = chr; 
/*      */     } 
/* 1639 */     if (length % 4 != 0)
/* 1640 */       throw new IllegalArgumentException("Text length for base64 must be a multiple of 4"); 
/* 1642 */     if (length == 0)
/* 1643 */       return new byte[0]; 
/* 1647 */     int blength = length / 4 * 3;
/* 1649 */     if (chrs[length - 1] == '=') {
/* 1650 */       blength--;
/* 1652 */       if (chrs[length - 2] == '=')
/* 1653 */         blength--; 
/*      */     } 
/* 1658 */     byte[] byts = new byte[blength];
/* 1659 */     int fill = 0;
/* 1661 */     for (int j = 0; j < length; j += 4)
/* 1662 */       fill += decodeChunk(j, chrs, fill, byts); 
/* 1665 */     if (fill != blength)
/* 1666 */       throw new IllegalArgumentException("Embedded padding characters in byte64 text"); 
/* 1670 */     return byts;
/*      */   }
/*      */   
/*      */   public static byte[] deserializeBase64(String text) throws IllegalArgumentException {
/* 1686 */     if (text == null)
/* 1687 */       return null; 
/* 1689 */     return parseBase64(text);
/*      */   }
/*      */   
/*      */   public static void encodeChunk(int base, byte[] byts, StringBuffer buff) {
/* 1704 */     int length = 3;
/* 1706 */     if (base + length > byts.length)
/* 1707 */       length = byts.length - base; 
/* 1711 */     int b0 = byts[base];
/* 1712 */     int value = b0 >> 2 & 0x3F;
/* 1713 */     buff.append(s_base64Chars[value]);
/* 1715 */     if (length > 1) {
/* 1716 */       int b1 = byts[base + 1];
/* 1717 */       value = ((b0 & 0x3) << 4) + (b1 >> 4 & 0xF);
/* 1718 */       buff.append(s_base64Chars[value]);
/* 1720 */       if (length > 2) {
/* 1721 */         int b2 = byts[base + 2];
/* 1722 */         value = ((b1 & 0xF) << 2) + (b2 >> 6 & 0x3);
/* 1723 */         buff.append(s_base64Chars[value]);
/* 1724 */         value = b2 & 0x3F;
/* 1725 */         buff.append(s_base64Chars[value]);
/*      */       } else {
/* 1727 */         value = (b1 & 0xF) << 2;
/* 1728 */         buff.append(s_base64Chars[value]);
/* 1729 */         buff.append('=');
/*      */       } 
/*      */     } else {
/* 1732 */       value = (b0 & 0x3) << 4;
/* 1733 */       buff.append(s_base64Chars[value]);
/* 1734 */       buff.append('=');
/* 1735 */       buff.append('=');
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String serializeBase64(byte[] byts) {
/* 1749 */     StringBuffer buff = new StringBuffer((byts.length + 2) / 3 * 4);
/* 1751 */     for (int i = 0; i < byts.length; i += 3) {
/* 1752 */       encodeChunk(i, byts, buff);
/* 1754 */       if (i > 0 && i % 57 == 0 && i + 3 < byts.length)
/* 1755 */         buff.append("\r\n"); 
/*      */     } 
/* 1759 */     return buff.toString();
/*      */   }
/*      */   
/*      */   public static List arrayListFactory() {
/* 1769 */     return new ArrayList();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\DateUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */