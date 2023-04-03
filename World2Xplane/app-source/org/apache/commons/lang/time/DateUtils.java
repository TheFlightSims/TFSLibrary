/*      */ package org.apache.commons.lang.time;
/*      */ 
/*      */ import java.text.ParseException;
/*      */ import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.TimeZone;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ 
/*      */ public class DateUtils {
/*   61 */   public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
/*      */   
/*      */   public static final long MILLIS_PER_SECOND = 1000L;
/*      */   
/*      */   public static final long MILLIS_PER_MINUTE = 60000L;
/*      */   
/*      */   public static final long MILLIS_PER_HOUR = 3600000L;
/*      */   
/*      */   public static final long MILLIS_PER_DAY = 86400000L;
/*      */   
/*      */   public static final int SEMI_MONTH = 1001;
/*      */   
/*   89 */   private static final int[][] fields = new int[][] { { 14 }, { 13 }, { 12 }, { 11, 10 }, { 5, 5, 9 }, { 2, 1001 }, { 1 }, { 0 } };
/*      */   
/*      */   public static final int RANGE_WEEK_SUNDAY = 1;
/*      */   
/*      */   public static final int RANGE_WEEK_MONDAY = 2;
/*      */   
/*      */   public static final int RANGE_WEEK_RELATIVE = 3;
/*      */   
/*      */   public static final int RANGE_WEEK_CENTER = 4;
/*      */   
/*      */   public static final int RANGE_MONTH_SUNDAY = 5;
/*      */   
/*      */   public static final int RANGE_MONTH_MONDAY = 6;
/*      */   
/*      */   private static final int MODIFY_TRUNCATE = 0;
/*      */   
/*      */   private static final int MODIFY_ROUND = 1;
/*      */   
/*      */   private static final int MODIFY_CEILING = 2;
/*      */   
/*      */   public static final int MILLIS_IN_SECOND = 1000;
/*      */   
/*      */   public static final int MILLIS_IN_MINUTE = 60000;
/*      */   
/*      */   public static final int MILLIS_IN_HOUR = 3600000;
/*      */   
/*      */   public static final int MILLIS_IN_DAY = 86400000;
/*      */   
/*      */   public static boolean isSameDay(Date date1, Date date2) {
/*  173 */     if (date1 == null || date2 == null)
/*  174 */       throw new IllegalArgumentException("The date must not be null"); 
/*  176 */     Calendar cal1 = Calendar.getInstance();
/*  177 */     cal1.setTime(date1);
/*  178 */     Calendar cal2 = Calendar.getInstance();
/*  179 */     cal2.setTime(date2);
/*  180 */     return isSameDay(cal1, cal2);
/*      */   }
/*      */   
/*      */   public static boolean isSameDay(Calendar cal1, Calendar cal2) {
/*  197 */     if (cal1 == null || cal2 == null)
/*  198 */       throw new IllegalArgumentException("The date must not be null"); 
/*  200 */     return (cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6));
/*      */   }
/*      */   
/*      */   public static boolean isSameInstant(Date date1, Date date2) {
/*  218 */     if (date1 == null || date2 == null)
/*  219 */       throw new IllegalArgumentException("The date must not be null"); 
/*  221 */     return (date1.getTime() == date2.getTime());
/*      */   }
/*      */   
/*      */   public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
/*  236 */     if (cal1 == null || cal2 == null)
/*  237 */       throw new IllegalArgumentException("The date must not be null"); 
/*  239 */     return (cal1.getTime().getTime() == cal2.getTime().getTime());
/*      */   }
/*      */   
/*      */   public static boolean isSameLocalTime(Calendar cal1, Calendar cal2) {
/*  256 */     if (cal1 == null || cal2 == null)
/*  257 */       throw new IllegalArgumentException("The date must not be null"); 
/*  259 */     return (cal1.get(14) == cal2.get(14) && cal1.get(13) == cal2.get(13) && cal1.get(12) == cal2.get(12) && cal1.get(10) == cal2.get(10) && cal1.get(6) == cal2.get(6) && cal1.get(1) == cal2.get(1) && cal1.get(0) == cal2.get(0) && cal1.getClass() == cal2.getClass());
/*      */   }
/*      */   
/*      */   public static Date parseDate(String str, String[] parsePatterns) throws ParseException {
/*  285 */     return parseDateWithLeniency(str, parsePatterns, true);
/*      */   }
/*      */   
/*      */   public static Date parseDateStrictly(String str, String[] parsePatterns) throws ParseException {
/*  305 */     return parseDateWithLeniency(str, parsePatterns, false);
/*      */   }
/*      */   
/*      */   private static Date parseDateWithLeniency(String str, String[] parsePatterns, boolean lenient) throws ParseException {
/*  325 */     if (str == null || parsePatterns == null)
/*  326 */       throw new IllegalArgumentException("Date and Patterns must not be null"); 
/*  329 */     SimpleDateFormat parser = new SimpleDateFormat();
/*  330 */     parser.setLenient(lenient);
/*  331 */     ParsePosition pos = new ParsePosition(0);
/*  332 */     for (int i = 0; i < parsePatterns.length; i++) {
/*  334 */       String pattern = parsePatterns[i];
/*  337 */       if (parsePatterns[i].endsWith("ZZ"))
/*  338 */         pattern = pattern.substring(0, pattern.length() - 1); 
/*  341 */       parser.applyPattern(pattern);
/*  342 */       pos.setIndex(0);
/*  344 */       String str2 = str;
/*  346 */       if (parsePatterns[i].endsWith("ZZ")) {
/*  347 */         int signIdx = indexOfSignChars(str2, 0);
/*  348 */         while (signIdx >= 0) {
/*  349 */           str2 = reformatTimezone(str2, signIdx);
/*  350 */           signIdx = indexOfSignChars(str2, ++signIdx);
/*      */         } 
/*      */       } 
/*  354 */       Date date = parser.parse(str2, pos);
/*  355 */       if (date != null && pos.getIndex() == str2.length())
/*  356 */         return date; 
/*      */     } 
/*  359 */     throw new ParseException("Unable to parse the date: " + str, -1);
/*      */   }
/*      */   
/*      */   private static int indexOfSignChars(String str, int startPos) {
/*  370 */     int idx = StringUtils.indexOf(str, '+', startPos);
/*  371 */     if (idx < 0)
/*  372 */       idx = StringUtils.indexOf(str, '-', startPos); 
/*  374 */     return idx;
/*      */   }
/*      */   
/*      */   private static String reformatTimezone(String str, int signIdx) {
/*  385 */     String str2 = str;
/*  386 */     if (signIdx >= 0 && signIdx + 5 < str.length() && Character.isDigit(str.charAt(signIdx + 1)) && Character.isDigit(str.charAt(signIdx + 2)) && str.charAt(signIdx + 3) == ':' && Character.isDigit(str.charAt(signIdx + 4)) && Character.isDigit(str.charAt(signIdx + 5)))
/*  393 */       str2 = str.substring(0, signIdx + 3) + str.substring(signIdx + 4); 
/*  395 */     return str2;
/*      */   }
/*      */   
/*      */   public static Date addYears(Date date, int amount) {
/*  409 */     return add(date, 1, amount);
/*      */   }
/*      */   
/*      */   public static Date addMonths(Date date, int amount) {
/*  423 */     return add(date, 2, amount);
/*      */   }
/*      */   
/*      */   public static Date addWeeks(Date date, int amount) {
/*  437 */     return add(date, 3, amount);
/*      */   }
/*      */   
/*      */   public static Date addDays(Date date, int amount) {
/*  451 */     return add(date, 5, amount);
/*      */   }
/*      */   
/*      */   public static Date addHours(Date date, int amount) {
/*  465 */     return add(date, 11, amount);
/*      */   }
/*      */   
/*      */   public static Date addMinutes(Date date, int amount) {
/*  479 */     return add(date, 12, amount);
/*      */   }
/*      */   
/*      */   public static Date addSeconds(Date date, int amount) {
/*  493 */     return add(date, 13, amount);
/*      */   }
/*      */   
/*      */   public static Date addMilliseconds(Date date, int amount) {
/*  507 */     return add(date, 14, amount);
/*      */   }
/*      */   
/*      */   public static Date add(Date date, int calendarField, int amount) {
/*  523 */     if (date == null)
/*  524 */       throw new IllegalArgumentException("The date must not be null"); 
/*  526 */     Calendar c = Calendar.getInstance();
/*  527 */     c.setTime(date);
/*  528 */     c.add(calendarField, amount);
/*  529 */     return c.getTime();
/*      */   }
/*      */   
/*      */   public static Date setYears(Date date, int amount) {
/*  544 */     return set(date, 1, amount);
/*      */   }
/*      */   
/*      */   public static Date setMonths(Date date, int amount) {
/*  559 */     return set(date, 2, amount);
/*      */   }
/*      */   
/*      */   public static Date setDays(Date date, int amount) {
/*  574 */     return set(date, 5, amount);
/*      */   }
/*      */   
/*      */   public static Date setHours(Date date, int amount) {
/*  590 */     return set(date, 11, amount);
/*      */   }
/*      */   
/*      */   public static Date setMinutes(Date date, int amount) {
/*  605 */     return set(date, 12, amount);
/*      */   }
/*      */   
/*      */   public static Date setSeconds(Date date, int amount) {
/*  620 */     return set(date, 13, amount);
/*      */   }
/*      */   
/*      */   public static Date setMilliseconds(Date date, int amount) {
/*  635 */     return set(date, 14, amount);
/*      */   }
/*      */   
/*      */   private static Date set(Date date, int calendarField, int amount) {
/*  652 */     if (date == null)
/*  653 */       throw new IllegalArgumentException("The date must not be null"); 
/*  656 */     Calendar c = Calendar.getInstance();
/*  657 */     c.setLenient(false);
/*  658 */     c.setTime(date);
/*  659 */     c.set(calendarField, amount);
/*  660 */     return c.getTime();
/*      */   }
/*      */   
/*      */   public static Calendar toCalendar(Date date) {
/*  673 */     Calendar c = Calendar.getInstance();
/*  674 */     c.setTime(date);
/*  675 */     return c;
/*      */   }
/*      */   
/*      */   public static Date round(Date date, int field) {
/*  708 */     if (date == null)
/*  709 */       throw new IllegalArgumentException("The date must not be null"); 
/*  711 */     Calendar gval = Calendar.getInstance();
/*  712 */     gval.setTime(date);
/*  713 */     modify(gval, field, 1);
/*  714 */     return gval.getTime();
/*      */   }
/*      */   
/*      */   public static Calendar round(Calendar date, int field) {
/*  746 */     if (date == null)
/*  747 */       throw new IllegalArgumentException("The date must not be null"); 
/*  749 */     Calendar rounded = (Calendar)date.clone();
/*  750 */     modify(rounded, field, 1);
/*  751 */     return rounded;
/*      */   }
/*      */   
/*      */   public static Date round(Object date, int field) {
/*  785 */     if (date == null)
/*  786 */       throw new IllegalArgumentException("The date must not be null"); 
/*  788 */     if (date instanceof Date)
/*  789 */       return round((Date)date, field); 
/*  790 */     if (date instanceof Calendar)
/*  791 */       return round((Calendar)date, field).getTime(); 
/*  793 */     throw new ClassCastException("Could not round " + date);
/*      */   }
/*      */   
/*      */   public static Date truncate(Date date, int field) {
/*  815 */     if (date == null)
/*  816 */       throw new IllegalArgumentException("The date must not be null"); 
/*  818 */     Calendar gval = Calendar.getInstance();
/*  819 */     gval.setTime(date);
/*  820 */     modify(gval, field, 0);
/*  821 */     return gval.getTime();
/*      */   }
/*      */   
/*      */   public static Calendar truncate(Calendar date, int field) {
/*  841 */     if (date == null)
/*  842 */       throw new IllegalArgumentException("The date must not be null"); 
/*  844 */     Calendar truncated = (Calendar)date.clone();
/*  845 */     modify(truncated, field, 0);
/*  846 */     return truncated;
/*      */   }
/*      */   
/*      */   public static Date truncate(Object date, int field) {
/*  870 */     if (date == null)
/*  871 */       throw new IllegalArgumentException("The date must not be null"); 
/*  873 */     if (date instanceof Date)
/*  874 */       return truncate((Date)date, field); 
/*  875 */     if (date instanceof Calendar)
/*  876 */       return truncate((Calendar)date, field).getTime(); 
/*  878 */     throw new ClassCastException("Could not truncate " + date);
/*      */   }
/*      */   
/*      */   public static Date ceiling(Date date, int field) {
/*  901 */     if (date == null)
/*  902 */       throw new IllegalArgumentException("The date must not be null"); 
/*  904 */     Calendar gval = Calendar.getInstance();
/*  905 */     gval.setTime(date);
/*  906 */     modify(gval, field, 2);
/*  907 */     return gval.getTime();
/*      */   }
/*      */   
/*      */   public static Calendar ceiling(Calendar date, int field) {
/*  928 */     if (date == null)
/*  929 */       throw new IllegalArgumentException("The date must not be null"); 
/*  931 */     Calendar ceiled = (Calendar)date.clone();
/*  932 */     modify(ceiled, field, 2);
/*  933 */     return ceiled;
/*      */   }
/*      */   
/*      */   public static Date ceiling(Object date, int field) {
/*  958 */     if (date == null)
/*  959 */       throw new IllegalArgumentException("The date must not be null"); 
/*  961 */     if (date instanceof Date)
/*  962 */       return ceiling((Date)date, field); 
/*  963 */     if (date instanceof Calendar)
/*  964 */       return ceiling((Calendar)date, field).getTime(); 
/*  966 */     throw new ClassCastException("Could not find ceiling of for type: " + date.getClass());
/*      */   }
/*      */   
/*      */   private static void modify(Calendar val, int field, int modType) {
/*  980 */     if (val.get(1) > 280000000)
/*  981 */       throw new ArithmeticException("Calendar value too large for accurate calculations"); 
/*  984 */     if (field == 14)
/*      */       return; 
/*  994 */     Date date = val.getTime();
/*  995 */     long time = date.getTime();
/*  996 */     boolean done = false;
/*  999 */     int millisecs = val.get(14);
/* 1000 */     if (0 == modType || millisecs < 500)
/* 1001 */       time -= millisecs; 
/* 1003 */     if (field == 13)
/* 1004 */       done = true; 
/* 1008 */     int seconds = val.get(13);
/* 1009 */     if (!done && (0 == modType || seconds < 30))
/* 1010 */       time -= seconds * 1000L; 
/* 1012 */     if (field == 12)
/* 1013 */       done = true; 
/* 1017 */     int minutes = val.get(12);
/* 1018 */     if (!done && (0 == modType || minutes < 30))
/* 1019 */       time -= minutes * 60000L; 
/* 1023 */     if (date.getTime() != time) {
/* 1024 */       date.setTime(time);
/* 1025 */       val.setTime(date);
/*      */     } 
/* 1029 */     boolean roundUp = false;
/* 1030 */     for (int i = 0; i < fields.length; i++) {
/* 1031 */       for (int j = 0; j < (fields[i]).length; j++) {
/* 1032 */         if (fields[i][j] == field) {
/* 1034 */           if (modType == 2 || (modType == 1 && roundUp))
/* 1035 */             if (field == 1001) {
/* 1039 */               if (val.get(5) == 1) {
/* 1040 */                 val.add(5, 15);
/*      */               } else {
/* 1042 */                 val.add(5, -15);
/* 1043 */                 val.add(2, 1);
/*      */               } 
/* 1046 */             } else if (field == 9) {
/* 1050 */               if (val.get(11) == 0) {
/* 1051 */                 val.add(11, 12);
/*      */               } else {
/* 1053 */                 val.add(11, -12);
/* 1054 */                 val.add(5, 1);
/*      */               } 
/*      */             } else {
/* 1060 */               val.add(fields[i][0], 1);
/*      */             }  
/*      */           return;
/*      */         } 
/*      */       } 
/* 1067 */       int offset = 0;
/* 1068 */       boolean offsetSet = false;
/* 1070 */       switch (field) {
/*      */         case 1001:
/* 1072 */           if (fields[i][0] == 5) {
/* 1076 */             offset = val.get(5) - 1;
/* 1079 */             if (offset >= 15)
/* 1080 */               offset -= 15; 
/* 1083 */             roundUp = (offset > 7);
/* 1084 */             offsetSet = true;
/*      */           } 
/*      */           break;
/*      */         case 9:
/* 1088 */           if (fields[i][0] == 11) {
/* 1091 */             offset = val.get(11);
/* 1092 */             if (offset >= 12)
/* 1093 */               offset -= 12; 
/* 1095 */             roundUp = (offset >= 6);
/* 1096 */             offsetSet = true;
/*      */           } 
/*      */           break;
/*      */       } 
/* 1100 */       if (!offsetSet) {
/* 1101 */         int min = val.getActualMinimum(fields[i][0]);
/* 1102 */         int max = val.getActualMaximum(fields[i][0]);
/* 1104 */         offset = val.get(fields[i][0]) - min;
/* 1106 */         roundUp = (offset > (max - min) / 2);
/*      */       } 
/* 1109 */       if (offset != 0)
/* 1110 */         val.set(fields[i][0], val.get(fields[i][0]) - offset); 
/*      */     } 
/* 1113 */     throw new IllegalArgumentException("The field " + field + " is not supported");
/*      */   }
/*      */   
/*      */   public static Iterator iterator(Date focus, int rangeStyle) {
/* 1143 */     if (focus == null)
/* 1144 */       throw new IllegalArgumentException("The date must not be null"); 
/* 1146 */     Calendar gval = Calendar.getInstance();
/* 1147 */     gval.setTime(focus);
/* 1148 */     return iterator(gval, rangeStyle);
/*      */   }
/*      */   
/*      */   public static Iterator iterator(Calendar focus, int rangeStyle) {
/* 1176 */     if (focus == null)
/* 1177 */       throw new IllegalArgumentException("The date must not be null"); 
/* 1179 */     Calendar start = null;
/* 1180 */     Calendar end = null;
/* 1181 */     int startCutoff = 1;
/* 1182 */     int endCutoff = 7;
/* 1183 */     switch (rangeStyle) {
/*      */       case 5:
/*      */       case 6:
/* 1187 */         start = truncate(focus, 2);
/* 1189 */         end = (Calendar)start.clone();
/* 1190 */         end.add(2, 1);
/* 1191 */         end.add(5, -1);
/* 1193 */         if (rangeStyle == 6) {
/* 1194 */           startCutoff = 2;
/* 1195 */           endCutoff = 1;
/*      */         } 
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/* 1203 */         start = truncate(focus, 5);
/* 1204 */         end = truncate(focus, 5);
/* 1205 */         switch (rangeStyle) {
/*      */           case 2:
/* 1210 */             startCutoff = 2;
/* 1211 */             endCutoff = 1;
/*      */             break;
/*      */           case 3:
/* 1214 */             startCutoff = focus.get(7);
/* 1215 */             endCutoff = startCutoff - 1;
/*      */             break;
/*      */           case 4:
/* 1218 */             startCutoff = focus.get(7) - 3;
/* 1219 */             endCutoff = focus.get(7) + 3;
/*      */             break;
/*      */         } 
/*      */         break;
/*      */       default:
/* 1224 */         throw new IllegalArgumentException("The range style " + rangeStyle + " is not valid.");
/*      */     } 
/* 1226 */     if (startCutoff < 1)
/* 1227 */       startCutoff += 7; 
/* 1229 */     if (startCutoff > 7)
/* 1230 */       startCutoff -= 7; 
/* 1232 */     if (endCutoff < 1)
/* 1233 */       endCutoff += 7; 
/* 1235 */     if (endCutoff > 7)
/* 1236 */       endCutoff -= 7; 
/* 1238 */     while (start.get(7) != startCutoff)
/* 1239 */       start.add(5, -1); 
/* 1241 */     while (end.get(7) != endCutoff)
/* 1242 */       end.add(5, 1); 
/* 1244 */     return new DateIterator(start, end);
/*      */   }
/*      */   
/*      */   public static Iterator iterator(Object focus, int rangeStyle) {
/* 1267 */     if (focus == null)
/* 1268 */       throw new IllegalArgumentException("The date must not be null"); 
/* 1270 */     if (focus instanceof Date)
/* 1271 */       return iterator((Date)focus, rangeStyle); 
/* 1272 */     if (focus instanceof Calendar)
/* 1273 */       return iterator((Calendar)focus, rangeStyle); 
/* 1275 */     throw new ClassCastException("Could not iterate based on " + focus);
/*      */   }
/*      */   
/*      */   public static long getFragmentInMilliseconds(Date date, int fragment) {
/* 1313 */     return getFragment(date, fragment, 14);
/*      */   }
/*      */   
/*      */   public static long getFragmentInSeconds(Date date, int fragment) {
/* 1353 */     return getFragment(date, fragment, 13);
/*      */   }
/*      */   
/*      */   public static long getFragmentInMinutes(Date date, int fragment) {
/* 1393 */     return getFragment(date, fragment, 12);
/*      */   }
/*      */   
/*      */   public static long getFragmentInHours(Date date, int fragment) {
/* 1433 */     return getFragment(date, fragment, 11);
/*      */   }
/*      */   
/*      */   public static long getFragmentInDays(Date date, int fragment) {
/* 1473 */     return getFragment(date, fragment, 6);
/*      */   }
/*      */   
/*      */   public static long getFragmentInMilliseconds(Calendar calendar, int fragment) {
/* 1513 */     return getFragment(calendar, fragment, 14);
/*      */   }
/*      */   
/*      */   public static long getFragmentInSeconds(Calendar calendar, int fragment) {
/* 1552 */     return getFragment(calendar, fragment, 13);
/*      */   }
/*      */   
/*      */   public static long getFragmentInMinutes(Calendar calendar, int fragment) {
/* 1592 */     return getFragment(calendar, fragment, 12);
/*      */   }
/*      */   
/*      */   public static long getFragmentInHours(Calendar calendar, int fragment) {
/* 1632 */     return getFragment(calendar, fragment, 11);
/*      */   }
/*      */   
/*      */   public static long getFragmentInDays(Calendar calendar, int fragment) {
/* 1674 */     return getFragment(calendar, fragment, 6);
/*      */   }
/*      */   
/*      */   private static long getFragment(Date date, int fragment, int unit) {
/* 1689 */     if (date == null)
/* 1690 */       throw new IllegalArgumentException("The date must not be null"); 
/* 1692 */     Calendar calendar = Calendar.getInstance();
/* 1693 */     calendar.setTime(date);
/* 1694 */     return getFragment(calendar, fragment, unit);
/*      */   }
/*      */   
/*      */   private static long getFragment(Calendar calendar, int fragment, int unit) {
/* 1709 */     if (calendar == null)
/* 1710 */       throw new IllegalArgumentException("The date must not be null"); 
/* 1712 */     long millisPerUnit = getMillisPerUnit(unit);
/* 1713 */     long result = 0L;
/* 1716 */     switch (fragment) {
/*      */       case 1:
/* 1718 */         result += calendar.get(6) * 86400000L / millisPerUnit;
/*      */         break;
/*      */       case 2:
/* 1721 */         result += calendar.get(5) * 86400000L / millisPerUnit;
/*      */         break;
/*      */     } 
/* 1725 */     switch (fragment) {
/*      */       case 1:
/*      */       case 2:
/*      */       case 5:
/*      */       case 6:
/* 1733 */         result += calendar.get(11) * 3600000L / millisPerUnit;
/*      */       case 11:
/* 1736 */         result += calendar.get(12) * 60000L / millisPerUnit;
/*      */       case 12:
/* 1739 */         result += calendar.get(13) * 1000L / millisPerUnit;
/*      */       case 13:
/* 1742 */         result += (calendar.get(14) * 1) / millisPerUnit;
/*      */       case 14:
/* 1749 */         return result;
/*      */     } 
/*      */     throw new IllegalArgumentException("The fragment " + fragment + " is not supported");
/*      */   }
/*      */   
/*      */   public static boolean truncatedEquals(Calendar cal1, Calendar cal2, int field) {
/* 1766 */     return (truncatedCompareTo(cal1, cal2, field) == 0);
/*      */   }
/*      */   
/*      */   public static boolean truncatedEquals(Date date1, Date date2, int field) {
/* 1783 */     return (truncatedCompareTo(date1, date2, field) == 0);
/*      */   }
/*      */   
/*      */   public static int truncatedCompareTo(Calendar cal1, Calendar cal2, int field) {
/* 1801 */     Calendar truncatedCal1 = truncate(cal1, field);
/* 1802 */     Calendar truncatedCal2 = truncate(cal2, field);
/* 1803 */     return truncatedCal1.getTime().compareTo(truncatedCal2.getTime());
/*      */   }
/*      */   
/*      */   public static int truncatedCompareTo(Date date1, Date date2, int field) {
/* 1821 */     Date truncatedDate1 = truncate(date1, field);
/* 1822 */     Date truncatedDate2 = truncate(date2, field);
/* 1823 */     return truncatedDate1.compareTo(truncatedDate2);
/*      */   }
/*      */   
/*      */   private static long getMillisPerUnit(int unit) {
/* 1835 */     long result = Long.MAX_VALUE;
/* 1836 */     switch (unit) {
/*      */       case 5:
/*      */       case 6:
/* 1839 */         result = 86400000L;
/* 1855 */         return result;
/*      */       case 11:
/*      */         result = 3600000L;
/* 1855 */         return result;
/*      */       case 12:
/*      */         result = 60000L;
/* 1855 */         return result;
/*      */       case 13:
/*      */         result = 1000L;
/* 1855 */         return result;
/*      */       case 14:
/*      */         result = 1L;
/* 1855 */         return result;
/*      */     } 
/*      */     throw new IllegalArgumentException("The unit " + unit + " cannot be represented is milleseconds");
/*      */   }
/*      */   
/*      */   static class DateIterator implements Iterator {
/*      */     private final Calendar endFinal;
/*      */     
/*      */     private final Calendar spot;
/*      */     
/*      */     DateIterator(Calendar startFinal, Calendar endFinal) {
/* 1873 */       this.endFinal = endFinal;
/* 1874 */       this.spot = startFinal;
/* 1875 */       this.spot.add(5, -1);
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1884 */       return this.spot.before(this.endFinal);
/*      */     }
/*      */     
/*      */     public Object next() {
/* 1893 */       if (this.spot.equals(this.endFinal))
/* 1894 */         throw new NoSuchElementException(); 
/* 1896 */       this.spot.add(5, 1);
/* 1897 */       return this.spot.clone();
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1907 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\time\DateUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */