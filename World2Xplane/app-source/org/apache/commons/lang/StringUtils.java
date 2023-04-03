/*      */ package org.apache.commons.lang;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import org.apache.commons.lang.text.StrBuilder;
/*      */ 
/*      */ public class StringUtils {
/*      */   public static final String EMPTY = "";
/*      */   
/*      */   public static final int INDEX_NOT_FOUND = -1;
/*      */   
/*      */   private static final int PAD_LIMIT = 8192;
/*      */   
/*      */   public static boolean isEmpty(String str) {
/*  195 */     return (str == null || str.length() == 0);
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(String str) {
/*  213 */     return !isEmpty(str);
/*      */   }
/*      */   
/*      */   public static boolean isBlank(String str) {
/*      */     int strLen;
/*  233 */     if (str == null || (strLen = str.length()) == 0)
/*  234 */       return true; 
/*  236 */     for (int i = 0; i < strLen; i++) {
/*  237 */       if (!Character.isWhitespace(str.charAt(i)))
/*  238 */         return false; 
/*      */     } 
/*  241 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isNotBlank(String str) {
/*  261 */     return !isBlank(str);
/*      */   }
/*      */   
/*      */   public static String clean(String str) {
/*  286 */     return (str == null) ? "" : str.trim();
/*      */   }
/*      */   
/*      */   public static String trim(String str) {
/*  313 */     return (str == null) ? null : str.trim();
/*      */   }
/*      */   
/*      */   public static String trimToNull(String str) {
/*  339 */     String ts = trim(str);
/*  340 */     return isEmpty(ts) ? null : ts;
/*      */   }
/*      */   
/*      */   public static String trimToEmpty(String str) {
/*  365 */     return (str == null) ? "" : str.trim();
/*      */   }
/*      */   
/*      */   public static String strip(String str) {
/*  393 */     return strip(str, null);
/*      */   }
/*      */   
/*      */   public static String stripToNull(String str) {
/*  420 */     if (str == null)
/*  421 */       return null; 
/*  423 */     str = strip(str, null);
/*  424 */     return (str.length() == 0) ? null : str;
/*      */   }
/*      */   
/*      */   public static String stripToEmpty(String str) {
/*  450 */     return (str == null) ? "" : strip(str, null);
/*      */   }
/*      */   
/*      */   public static String strip(String str, String stripChars) {
/*  480 */     if (isEmpty(str))
/*  481 */       return str; 
/*  483 */     str = stripStart(str, stripChars);
/*  484 */     return stripEnd(str, stripChars);
/*      */   }
/*      */   
/*      */   public static String stripStart(String str, String stripChars) {
/*      */     int strLen;
/*  513 */     if (str == null || (strLen = str.length()) == 0)
/*  514 */       return str; 
/*  516 */     int start = 0;
/*  517 */     if (stripChars == null) {
/*  518 */       while (start != strLen && Character.isWhitespace(str.charAt(start)))
/*  519 */         start++; 
/*      */     } else {
/*  521 */       if (stripChars.length() == 0)
/*  522 */         return str; 
/*  524 */       while (start != strLen && stripChars.indexOf(str.charAt(start)) != -1)
/*  525 */         start++; 
/*      */     } 
/*  528 */     return str.substring(start);
/*      */   }
/*      */   
/*      */   public static String stripEnd(String str, String stripChars) {
/*      */     int end;
/*  558 */     if (str == null || (end = str.length()) == 0)
/*  559 */       return str; 
/*  562 */     if (stripChars == null) {
/*  563 */       while (end != 0 && Character.isWhitespace(str.charAt(end - 1)))
/*  564 */         end--; 
/*      */     } else {
/*  566 */       if (stripChars.length() == 0)
/*  567 */         return str; 
/*  569 */       while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != -1)
/*  570 */         end--; 
/*      */     } 
/*  573 */     return str.substring(0, end);
/*      */   }
/*      */   
/*      */   public static String[] stripAll(String[] strs) {
/*  598 */     return stripAll(strs, null);
/*      */   }
/*      */   
/*      */   public static String[] stripAll(String[] strs, String stripChars) {
/*      */     int strsLen;
/*  628 */     if (strs == null || (strsLen = strs.length) == 0)
/*  629 */       return strs; 
/*  631 */     String[] newArr = new String[strsLen];
/*  632 */     for (int i = 0; i < strsLen; i++)
/*  633 */       newArr[i] = strip(strs[i], stripChars); 
/*  635 */     return newArr;
/*      */   }
/*      */   
/*      */   public static boolean equals(String str1, String str2) {
/*  661 */     return (str1 == null) ? ((str2 == null)) : str1.equals(str2);
/*      */   }
/*      */   
/*      */   public static boolean equalsIgnoreCase(String str1, String str2) {
/*  686 */     return (str1 == null) ? ((str2 == null)) : str1.equalsIgnoreCase(str2);
/*      */   }
/*      */   
/*      */   public static int indexOf(String str, char searchChar) {
/*  711 */     if (isEmpty(str))
/*  712 */       return -1; 
/*  714 */     return str.indexOf(searchChar);
/*      */   }
/*      */   
/*      */   public static int indexOf(String str, char searchChar, int startPos) {
/*  743 */     if (isEmpty(str))
/*  744 */       return -1; 
/*  746 */     return str.indexOf(searchChar, startPos);
/*      */   }
/*      */   
/*      */   public static int indexOf(String str, String searchStr) {
/*  773 */     if (str == null || searchStr == null)
/*  774 */       return -1; 
/*  776 */     return str.indexOf(searchStr);
/*      */   }
/*      */   
/*      */   public static int ordinalIndexOf(String str, String searchStr, int ordinal) {
/*  813 */     return ordinalIndexOf(str, searchStr, ordinal, false);
/*      */   }
/*      */   
/*      */   private static int ordinalIndexOf(String str, String searchStr, int ordinal, boolean lastIndex) {
/*  831 */     if (str == null || searchStr == null || ordinal <= 0)
/*  832 */       return -1; 
/*  834 */     if (searchStr.length() == 0)
/*  835 */       return lastIndex ? str.length() : 0; 
/*  837 */     int found = 0;
/*  838 */     int index = lastIndex ? str.length() : -1;
/*      */     while (true) {
/*  840 */       if (lastIndex) {
/*  841 */         index = str.lastIndexOf(searchStr, index - 1);
/*      */       } else {
/*  843 */         index = str.indexOf(searchStr, index + 1);
/*      */       } 
/*  845 */       if (index < 0)
/*  846 */         return index; 
/*  848 */       found++;
/*  849 */       if (found >= ordinal)
/*  850 */         return index; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int indexOf(String str, String searchStr, int startPos) {
/*  886 */     if (str == null || searchStr == null)
/*  887 */       return -1; 
/*  890 */     if (searchStr.length() == 0 && startPos >= str.length())
/*  891 */       return str.length(); 
/*  893 */     return str.indexOf(searchStr, startPos);
/*      */   }
/*      */   
/*      */   public static int indexOfIgnoreCase(String str, String searchStr) {
/*  921 */     return indexOfIgnoreCase(str, searchStr, 0);
/*      */   }
/*      */   
/*      */   public static int indexOfIgnoreCase(String str, String searchStr, int startPos) {
/*  956 */     if (str == null || searchStr == null)
/*  957 */       return -1; 
/*  959 */     if (startPos < 0)
/*  960 */       startPos = 0; 
/*  962 */     int endLimit = str.length() - searchStr.length() + 1;
/*  963 */     if (startPos > endLimit)
/*  964 */       return -1; 
/*  966 */     if (searchStr.length() == 0)
/*  967 */       return startPos; 
/*  969 */     for (int i = startPos; i < endLimit; i++) {
/*  970 */       if (str.regionMatches(true, i, searchStr, 0, searchStr.length()))
/*  971 */         return i; 
/*      */     } 
/*  974 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(String str, char searchChar) {
/*  999 */     if (isEmpty(str))
/* 1000 */       return -1; 
/* 1002 */     return str.lastIndexOf(searchChar);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(String str, char searchChar, int startPos) {
/* 1033 */     if (isEmpty(str))
/* 1034 */       return -1; 
/* 1036 */     return str.lastIndexOf(searchChar, startPos);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(String str, String searchStr) {
/* 1062 */     if (str == null || searchStr == null)
/* 1063 */       return -1; 
/* 1065 */     return str.lastIndexOf(searchStr);
/*      */   }
/*      */   
/*      */   public static int lastOrdinalIndexOf(String str, String searchStr, int ordinal) {
/* 1102 */     return ordinalIndexOf(str, searchStr, ordinal, true);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(String str, String searchStr, int startPos) {
/* 1134 */     if (str == null || searchStr == null)
/* 1135 */       return -1; 
/* 1137 */     return str.lastIndexOf(searchStr, startPos);
/*      */   }
/*      */   
/*      */   public static int lastIndexOfIgnoreCase(String str, String searchStr) {
/* 1163 */     if (str == null || searchStr == null)
/* 1164 */       return -1; 
/* 1166 */     return lastIndexOfIgnoreCase(str, searchStr, str.length());
/*      */   }
/*      */   
/*      */   public static int lastIndexOfIgnoreCase(String str, String searchStr, int startPos) {
/* 1198 */     if (str == null || searchStr == null)
/* 1199 */       return -1; 
/* 1201 */     if (startPos > str.length() - searchStr.length())
/* 1202 */       startPos = str.length() - searchStr.length(); 
/* 1204 */     if (startPos < 0)
/* 1205 */       return -1; 
/* 1207 */     if (searchStr.length() == 0)
/* 1208 */       return startPos; 
/* 1211 */     for (int i = startPos; i >= 0; i--) {
/* 1212 */       if (str.regionMatches(true, i, searchStr, 0, searchStr.length()))
/* 1213 */         return i; 
/*      */     } 
/* 1216 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean contains(String str, char searchChar) {
/* 1241 */     if (isEmpty(str))
/* 1242 */       return false; 
/* 1244 */     return (str.indexOf(searchChar) >= 0);
/*      */   }
/*      */   
/*      */   public static boolean contains(String str, String searchStr) {
/* 1269 */     if (str == null || searchStr == null)
/* 1270 */       return false; 
/* 1272 */     return (str.indexOf(searchStr) >= 0);
/*      */   }
/*      */   
/*      */   public static boolean containsIgnoreCase(String str, String searchStr) {
/* 1299 */     if (str == null || searchStr == null)
/* 1300 */       return false; 
/* 1302 */     int len = searchStr.length();
/* 1303 */     int max = str.length() - len;
/* 1304 */     for (int i = 0; i <= max; i++) {
/* 1305 */       if (str.regionMatches(true, i, searchStr, 0, len))
/* 1306 */         return true; 
/*      */     } 
/* 1309 */     return false;
/*      */   }
/*      */   
/*      */   public static int indexOfAny(String str, char[] searchChars) {
/* 1337 */     if (isEmpty(str) || ArrayUtils.isEmpty(searchChars))
/* 1338 */       return -1; 
/* 1340 */     int csLen = str.length();
/* 1341 */     int csLast = csLen - 1;
/* 1342 */     int searchLen = searchChars.length;
/* 1343 */     int searchLast = searchLen - 1;
/* 1344 */     for (int i = 0; i < csLen; i++) {
/* 1345 */       char ch = str.charAt(i);
/* 1346 */       for (int j = 0; j < searchLen; j++) {
/* 1347 */         if (searchChars[j] == ch)
/* 1348 */           if (i < csLast && j < searchLast && CharUtils.isHighSurrogate(ch)) {
/* 1350 */             if (searchChars[j + 1] == str.charAt(i + 1))
/* 1351 */               return i; 
/*      */           } else {
/* 1354 */             return i;
/*      */           }  
/*      */       } 
/*      */     } 
/* 1359 */     return -1;
/*      */   }
/*      */   
/*      */   public static int indexOfAny(String str, String searchChars) {
/* 1385 */     if (isEmpty(str) || isEmpty(searchChars))
/* 1386 */       return -1; 
/* 1388 */     return indexOfAny(str, searchChars.toCharArray());
/*      */   }
/*      */   
/*      */   public static boolean containsAny(String str, char[] searchChars) {
/* 1417 */     if (isEmpty(str) || ArrayUtils.isEmpty(searchChars))
/* 1418 */       return false; 
/* 1420 */     int csLength = str.length();
/* 1421 */     int searchLength = searchChars.length;
/* 1422 */     int csLast = csLength - 1;
/* 1423 */     int searchLast = searchLength - 1;
/* 1424 */     for (int i = 0; i < csLength; i++) {
/* 1425 */       char ch = str.charAt(i);
/* 1426 */       for (int j = 0; j < searchLength; j++) {
/* 1427 */         if (searchChars[j] == ch)
/* 1428 */           if (CharUtils.isHighSurrogate(ch)) {
/* 1429 */             if (j == searchLast)
/* 1431 */               return true; 
/* 1433 */             if (i < csLast && searchChars[j + 1] == str.charAt(i + 1))
/* 1434 */               return true; 
/*      */           } else {
/* 1438 */             return true;
/*      */           }  
/*      */       } 
/*      */     } 
/* 1443 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean containsAny(String str, String searchChars) {
/* 1474 */     if (searchChars == null)
/* 1475 */       return false; 
/* 1477 */     return containsAny(str, searchChars.toCharArray());
/*      */   }
/*      */   
/*      */   public static int indexOfAnyBut(String str, char[] searchChars) {
/* 1505 */     if (isEmpty(str) || ArrayUtils.isEmpty(searchChars))
/* 1506 */       return -1; 
/* 1508 */     int csLen = str.length();
/* 1509 */     int csLast = csLen - 1;
/* 1510 */     int searchLen = searchChars.length;
/* 1511 */     int searchLast = searchLen - 1;
/* 1513 */     for (int i = 0; i < csLen; i++) {
/* 1514 */       char ch = str.charAt(i);
/* 1515 */       int j = 0;
/*      */       while (true) {
/* 1515 */         if (j < searchLen) {
/* 1516 */           if (searchChars[j] == ch && (
/* 1517 */             i >= csLast || j >= searchLast || !CharUtils.isHighSurrogate(ch) || 
/* 1518 */             searchChars[j + 1] == str.charAt(i + 1)))
/*      */             break; 
/*      */           j++;
/*      */           continue;
/*      */         } 
/* 1526 */         return i;
/*      */       } 
/*      */     } 
/* 1528 */     return -1;
/*      */   }
/*      */   
/*      */   public static int indexOfAnyBut(String str, String searchChars) {
/* 1554 */     if (isEmpty(str) || isEmpty(searchChars))
/* 1555 */       return -1; 
/* 1557 */     int strLen = str.length();
/* 1558 */     for (int i = 0; i < strLen; i++) {
/* 1559 */       char ch = str.charAt(i);
/* 1560 */       boolean chFound = (searchChars.indexOf(ch) >= 0);
/* 1561 */       if (i + 1 < strLen && CharUtils.isHighSurrogate(ch)) {
/* 1562 */         char ch2 = str.charAt(i + 1);
/* 1563 */         if (chFound && searchChars.indexOf(ch2) < 0)
/* 1564 */           return i; 
/* 1567 */       } else if (!chFound) {
/* 1568 */         return i;
/*      */       } 
/*      */     } 
/* 1572 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean containsOnly(String str, char[] valid) {
/* 1600 */     if (valid == null || str == null)
/* 1601 */       return false; 
/* 1603 */     if (str.length() == 0)
/* 1604 */       return true; 
/* 1606 */     if (valid.length == 0)
/* 1607 */       return false; 
/* 1609 */     return (indexOfAnyBut(str, valid) == -1);
/*      */   }
/*      */   
/*      */   public static boolean containsOnly(String str, String validChars) {
/* 1635 */     if (str == null || validChars == null)
/* 1636 */       return false; 
/* 1638 */     return containsOnly(str, validChars.toCharArray());
/*      */   }
/*      */   
/*      */   public static boolean containsNone(String str, char[] searchChars) {
/* 1666 */     if (str == null || searchChars == null)
/* 1667 */       return true; 
/* 1669 */     int csLen = str.length();
/* 1670 */     int csLast = csLen - 1;
/* 1671 */     int searchLen = searchChars.length;
/* 1672 */     int searchLast = searchLen - 1;
/* 1673 */     for (int i = 0; i < csLen; i++) {
/* 1674 */       char ch = str.charAt(i);
/* 1675 */       for (int j = 0; j < searchLen; j++) {
/* 1676 */         if (searchChars[j] == ch)
/* 1677 */           if (CharUtils.isHighSurrogate(ch)) {
/* 1678 */             if (j == searchLast)
/* 1680 */               return false; 
/* 1682 */             if (i < csLast && searchChars[j + 1] == str.charAt(i + 1))
/* 1683 */               return false; 
/*      */           } else {
/* 1687 */             return false;
/*      */           }  
/*      */       } 
/*      */     } 
/* 1692 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean containsNone(String str, String invalidChars) {
/* 1718 */     if (str == null || invalidChars == null)
/* 1719 */       return true; 
/* 1721 */     return containsNone(str, invalidChars.toCharArray());
/*      */   }
/*      */   
/*      */   public static int indexOfAny(String str, String[] searchStrs) {
/* 1753 */     if (str == null || searchStrs == null)
/* 1754 */       return -1; 
/* 1756 */     int sz = searchStrs.length;
/* 1759 */     int ret = Integer.MAX_VALUE;
/* 1761 */     int tmp = 0;
/* 1762 */     for (int i = 0; i < sz; i++) {
/* 1763 */       String search = searchStrs[i];
/* 1764 */       if (search != null) {
/* 1767 */         tmp = str.indexOf(search);
/* 1768 */         if (tmp != -1)
/* 1772 */           if (tmp < ret)
/* 1773 */             ret = tmp;  
/*      */       } 
/*      */     } 
/* 1777 */     return (ret == Integer.MAX_VALUE) ? -1 : ret;
/*      */   }
/*      */   
/*      */   public static int lastIndexOfAny(String str, String[] searchStrs) {
/* 1806 */     if (str == null || searchStrs == null)
/* 1807 */       return -1; 
/* 1809 */     int sz = searchStrs.length;
/* 1810 */     int ret = -1;
/* 1811 */     int tmp = 0;
/* 1812 */     for (int i = 0; i < sz; i++) {
/* 1813 */       String search = searchStrs[i];
/* 1814 */       if (search != null) {
/* 1817 */         tmp = str.lastIndexOf(search);
/* 1818 */         if (tmp > ret)
/* 1819 */           ret = tmp; 
/*      */       } 
/*      */     } 
/* 1822 */     return ret;
/*      */   }
/*      */   
/*      */   public static String substring(String str, int start) {
/* 1852 */     if (str == null)
/* 1853 */       return null; 
/* 1857 */     if (start < 0)
/* 1858 */       start = str.length() + start; 
/* 1861 */     if (start < 0)
/* 1862 */       start = 0; 
/* 1864 */     if (start > str.length())
/* 1865 */       return ""; 
/* 1868 */     return str.substring(start);
/*      */   }
/*      */   
/*      */   public static String substring(String str, int start, int end) {
/* 1907 */     if (str == null)
/* 1908 */       return null; 
/* 1912 */     if (end < 0)
/* 1913 */       end = str.length() + end; 
/* 1915 */     if (start < 0)
/* 1916 */       start = str.length() + start; 
/* 1920 */     if (end > str.length())
/* 1921 */       end = str.length(); 
/* 1925 */     if (start > end)
/* 1926 */       return ""; 
/* 1929 */     if (start < 0)
/* 1930 */       start = 0; 
/* 1932 */     if (end < 0)
/* 1933 */       end = 0; 
/* 1936 */     return str.substring(start, end);
/*      */   }
/*      */   
/*      */   public static String left(String str, int len) {
/* 1962 */     if (str == null)
/* 1963 */       return null; 
/* 1965 */     if (len < 0)
/* 1966 */       return ""; 
/* 1968 */     if (str.length() <= len)
/* 1969 */       return str; 
/* 1971 */     return str.substring(0, len);
/*      */   }
/*      */   
/*      */   public static String right(String str, int len) {
/* 1995 */     if (str == null)
/* 1996 */       return null; 
/* 1998 */     if (len < 0)
/* 1999 */       return ""; 
/* 2001 */     if (str.length() <= len)
/* 2002 */       return str; 
/* 2004 */     return str.substring(str.length() - len);
/*      */   }
/*      */   
/*      */   public static String mid(String str, int pos, int len) {
/* 2033 */     if (str == null)
/* 2034 */       return null; 
/* 2036 */     if (len < 0 || pos > str.length())
/* 2037 */       return ""; 
/* 2039 */     if (pos < 0)
/* 2040 */       pos = 0; 
/* 2042 */     if (str.length() <= pos + len)
/* 2043 */       return str.substring(pos); 
/* 2045 */     return str.substring(pos, pos + len);
/*      */   }
/*      */   
/*      */   public static String substringBefore(String str, String separator) {
/* 2078 */     if (isEmpty(str) || separator == null)
/* 2079 */       return str; 
/* 2081 */     if (separator.length() == 0)
/* 2082 */       return ""; 
/* 2084 */     int pos = str.indexOf(separator);
/* 2085 */     if (pos == -1)
/* 2086 */       return str; 
/* 2088 */     return str.substring(0, pos);
/*      */   }
/*      */   
/*      */   public static String substringAfter(String str, String separator) {
/* 2120 */     if (isEmpty(str))
/* 2121 */       return str; 
/* 2123 */     if (separator == null)
/* 2124 */       return ""; 
/* 2126 */     int pos = str.indexOf(separator);
/* 2127 */     if (pos == -1)
/* 2128 */       return ""; 
/* 2130 */     return str.substring(pos + separator.length());
/*      */   }
/*      */   
/*      */   public static String substringBeforeLast(String str, String separator) {
/* 2161 */     if (isEmpty(str) || isEmpty(separator))
/* 2162 */       return str; 
/* 2164 */     int pos = str.lastIndexOf(separator);
/* 2165 */     if (pos == -1)
/* 2166 */       return str; 
/* 2168 */     return str.substring(0, pos);
/*      */   }
/*      */   
/*      */   public static String substringAfterLast(String str, String separator) {
/* 2201 */     if (isEmpty(str))
/* 2202 */       return str; 
/* 2204 */     if (isEmpty(separator))
/* 2205 */       return ""; 
/* 2207 */     int pos = str.lastIndexOf(separator);
/* 2208 */     if (pos == -1 || pos == str.length() - separator.length())
/* 2209 */       return ""; 
/* 2211 */     return str.substring(pos + separator.length());
/*      */   }
/*      */   
/*      */   public static String substringBetween(String str, String tag) {
/* 2238 */     return substringBetween(str, tag, tag);
/*      */   }
/*      */   
/*      */   public static String substringBetween(String str, String open, String close) {
/* 2269 */     if (str == null || open == null || close == null)
/* 2270 */       return null; 
/* 2272 */     int start = str.indexOf(open);
/* 2273 */     if (start != -1) {
/* 2274 */       int end = str.indexOf(close, start + open.length());
/* 2275 */       if (end != -1)
/* 2276 */         return str.substring(start + open.length(), end); 
/*      */     } 
/* 2279 */     return null;
/*      */   }
/*      */   
/*      */   public static String[] substringsBetween(String str, String open, String close) {
/* 2305 */     if (str == null || isEmpty(open) || isEmpty(close))
/* 2306 */       return null; 
/* 2308 */     int strLen = str.length();
/* 2309 */     if (strLen == 0)
/* 2310 */       return ArrayUtils.EMPTY_STRING_ARRAY; 
/* 2312 */     int closeLen = close.length();
/* 2313 */     int openLen = open.length();
/* 2314 */     List list = new ArrayList();
/* 2315 */     int pos = 0;
/* 2316 */     while (pos < strLen - closeLen) {
/* 2317 */       int start = str.indexOf(open, pos);
/* 2318 */       if (start < 0)
/*      */         break; 
/* 2321 */       start += openLen;
/* 2322 */       int end = str.indexOf(close, start);
/* 2323 */       if (end < 0)
/*      */         break; 
/* 2326 */       list.add(str.substring(start, end));
/* 2327 */       pos = end + closeLen;
/*      */     } 
/* 2329 */     if (list.isEmpty())
/* 2330 */       return null; 
/* 2332 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */   
/*      */   public static String getNestedString(String str, String tag) {
/* 2360 */     return substringBetween(str, tag, tag);
/*      */   }
/*      */   
/*      */   public static String getNestedString(String str, String open, String close) {
/* 2390 */     return substringBetween(str, open, close);
/*      */   }
/*      */   
/*      */   public static String[] split(String str) {
/* 2418 */     return split(str, null, -1);
/*      */   }
/*      */   
/*      */   public static String[] split(String str, char separatorChar) {
/* 2446 */     return splitWorker(str, separatorChar, false);
/*      */   }
/*      */   
/*      */   public static String[] split(String str, String separatorChars) {
/* 2475 */     return splitWorker(str, separatorChars, -1, false);
/*      */   }
/*      */   
/*      */   public static String[] split(String str, String separatorChars, int max) {
/* 2509 */     return splitWorker(str, separatorChars, max, false);
/*      */   }
/*      */   
/*      */   public static String[] splitByWholeSeparator(String str, String separator) {
/* 2536 */     return splitByWholeSeparatorWorker(str, separator, -1, false);
/*      */   }
/*      */   
/*      */   public static String[] splitByWholeSeparator(String str, String separator, int max) {
/* 2567 */     return splitByWholeSeparatorWorker(str, separator, max, false);
/*      */   }
/*      */   
/*      */   public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator) {
/* 2596 */     return splitByWholeSeparatorWorker(str, separator, -1, true);
/*      */   }
/*      */   
/*      */   public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max) {
/* 2629 */     return splitByWholeSeparatorWorker(str, separator, max, true);
/*      */   }
/*      */   
/*      */   private static String[] splitByWholeSeparatorWorker(String str, String separator, int max, boolean preserveAllTokens) {
/* 2649 */     if (str == null)
/* 2650 */       return null; 
/* 2653 */     int len = str.length();
/* 2655 */     if (len == 0)
/* 2656 */       return ArrayUtils.EMPTY_STRING_ARRAY; 
/* 2659 */     if (separator == null || "".equals(separator))
/* 2661 */       return splitWorker(str, null, max, preserveAllTokens); 
/* 2664 */     int separatorLength = separator.length();
/* 2666 */     ArrayList substrings = new ArrayList();
/* 2667 */     int numberOfSubstrings = 0;
/* 2668 */     int beg = 0;
/* 2669 */     int end = 0;
/* 2670 */     while (end < len) {
/* 2671 */       end = str.indexOf(separator, beg);
/* 2673 */       if (end > -1) {
/* 2674 */         if (end > beg) {
/* 2675 */           numberOfSubstrings++;
/* 2677 */           if (numberOfSubstrings == max) {
/* 2678 */             end = len;
/* 2679 */             substrings.add(str.substring(beg));
/*      */             continue;
/*      */           } 
/* 2683 */           substrings.add(str.substring(beg, end));
/* 2688 */           beg = end + separatorLength;
/*      */           continue;
/*      */         } 
/* 2692 */         if (preserveAllTokens) {
/* 2693 */           numberOfSubstrings++;
/* 2694 */           if (numberOfSubstrings == max) {
/* 2695 */             end = len;
/* 2696 */             substrings.add(str.substring(beg));
/*      */           } else {
/* 2698 */             substrings.add("");
/*      */           } 
/*      */         } 
/* 2701 */         beg = end + separatorLength;
/*      */         continue;
/*      */       } 
/* 2705 */       substrings.add(str.substring(beg));
/* 2706 */       end = len;
/*      */     } 
/* 2710 */     return substrings.<String>toArray(new String[substrings.size()]);
/*      */   }
/*      */   
/*      */   public static String[] splitPreserveAllTokens(String str) {
/* 2739 */     return splitWorker(str, null, -1, true);
/*      */   }
/*      */   
/*      */   public static String[] splitPreserveAllTokens(String str, char separatorChar) {
/* 2775 */     return splitWorker(str, separatorChar, true);
/*      */   }
/*      */   
/*      */   private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
/* 2793 */     if (str == null)
/* 2794 */       return null; 
/* 2796 */     int len = str.length();
/* 2797 */     if (len == 0)
/* 2798 */       return ArrayUtils.EMPTY_STRING_ARRAY; 
/* 2800 */     List list = new ArrayList();
/* 2801 */     int i = 0, start = 0;
/* 2802 */     boolean match = false;
/* 2803 */     boolean lastMatch = false;
/* 2804 */     while (i < len) {
/* 2805 */       if (str.charAt(i) == separatorChar) {
/* 2806 */         if (match || preserveAllTokens) {
/* 2807 */           list.add(str.substring(start, i));
/* 2808 */           match = false;
/* 2809 */           lastMatch = true;
/*      */         } 
/* 2811 */         start = ++i;
/*      */         continue;
/*      */       } 
/* 2814 */       lastMatch = false;
/* 2815 */       match = true;
/* 2816 */       i++;
/*      */     } 
/* 2818 */     if (match || (preserveAllTokens && lastMatch))
/* 2819 */       list.add(str.substring(start, i)); 
/* 2821 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */   
/*      */   public static String[] splitPreserveAllTokens(String str, String separatorChars) {
/* 2858 */     return splitWorker(str, separatorChars, -1, true);
/*      */   }
/*      */   
/*      */   public static String[] splitPreserveAllTokens(String str, String separatorChars, int max) {
/* 2898 */     return splitWorker(str, separatorChars, max, true);
/*      */   }
/*      */   
/*      */   private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
/* 2920 */     if (str == null)
/* 2921 */       return null; 
/* 2923 */     int len = str.length();
/* 2924 */     if (len == 0)
/* 2925 */       return ArrayUtils.EMPTY_STRING_ARRAY; 
/* 2927 */     List list = new ArrayList();
/* 2928 */     int sizePlus1 = 1;
/* 2929 */     int i = 0, start = 0;
/* 2930 */     boolean match = false;
/* 2931 */     boolean lastMatch = false;
/* 2932 */     if (separatorChars == null) {
/* 2934 */       while (i < len) {
/* 2935 */         if (Character.isWhitespace(str.charAt(i))) {
/* 2936 */           if (match || preserveAllTokens) {
/* 2937 */             lastMatch = true;
/* 2938 */             if (sizePlus1++ == max) {
/* 2939 */               i = len;
/* 2940 */               lastMatch = false;
/*      */             } 
/* 2942 */             list.add(str.substring(start, i));
/* 2943 */             match = false;
/*      */           } 
/* 2945 */           start = ++i;
/*      */           continue;
/*      */         } 
/* 2948 */         lastMatch = false;
/* 2949 */         match = true;
/* 2950 */         i++;
/*      */       } 
/* 2952 */     } else if (separatorChars.length() == 1) {
/* 2954 */       char sep = separatorChars.charAt(0);
/* 2955 */       while (i < len) {
/* 2956 */         if (str.charAt(i) == sep) {
/* 2957 */           if (match || preserveAllTokens) {
/* 2958 */             lastMatch = true;
/* 2959 */             if (sizePlus1++ == max) {
/* 2960 */               i = len;
/* 2961 */               lastMatch = false;
/*      */             } 
/* 2963 */             list.add(str.substring(start, i));
/* 2964 */             match = false;
/*      */           } 
/* 2966 */           start = ++i;
/*      */           continue;
/*      */         } 
/* 2969 */         lastMatch = false;
/* 2970 */         match = true;
/* 2971 */         i++;
/*      */       } 
/*      */     } else {
/* 2975 */       while (i < len) {
/* 2976 */         if (separatorChars.indexOf(str.charAt(i)) >= 0) {
/* 2977 */           if (match || preserveAllTokens) {
/* 2978 */             lastMatch = true;
/* 2979 */             if (sizePlus1++ == max) {
/* 2980 */               i = len;
/* 2981 */               lastMatch = false;
/*      */             } 
/* 2983 */             list.add(str.substring(start, i));
/* 2984 */             match = false;
/*      */           } 
/* 2986 */           start = ++i;
/*      */           continue;
/*      */         } 
/* 2989 */         lastMatch = false;
/* 2990 */         match = true;
/* 2991 */         i++;
/*      */       } 
/*      */     } 
/* 2994 */     if (match || (preserveAllTokens && lastMatch))
/* 2995 */       list.add(str.substring(start, i)); 
/* 2997 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */   
/*      */   public static String[] splitByCharacterType(String str) {
/* 3020 */     return splitByCharacterType(str, false);
/*      */   }
/*      */   
/*      */   public static String[] splitByCharacterTypeCamelCase(String str) {
/* 3048 */     return splitByCharacterType(str, true);
/*      */   }
/*      */   
/*      */   private static String[] splitByCharacterType(String str, boolean camelCase) {
/* 3066 */     if (str == null)
/* 3067 */       return null; 
/* 3069 */     if (str.length() == 0)
/* 3070 */       return ArrayUtils.EMPTY_STRING_ARRAY; 
/* 3072 */     char[] c = str.toCharArray();
/* 3073 */     List list = new ArrayList();
/* 3074 */     int tokenStart = 0;
/* 3075 */     int currentType = Character.getType(c[tokenStart]);
/* 3076 */     for (int pos = tokenStart + 1; pos < c.length; pos++) {
/* 3077 */       int type = Character.getType(c[pos]);
/* 3078 */       if (type != currentType) {
/* 3081 */         if (camelCase && type == 2 && currentType == 1) {
/* 3082 */           int newTokenStart = pos - 1;
/* 3083 */           if (newTokenStart != tokenStart) {
/* 3084 */             list.add(new String(c, tokenStart, newTokenStart - tokenStart));
/* 3085 */             tokenStart = newTokenStart;
/*      */           } 
/*      */         } else {
/* 3088 */           list.add(new String(c, tokenStart, pos - tokenStart));
/* 3089 */           tokenStart = pos;
/*      */         } 
/* 3091 */         currentType = type;
/*      */       } 
/*      */     } 
/* 3093 */     list.add(new String(c, tokenStart, c.length - tokenStart));
/* 3094 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */   
/*      */   public static String concatenate(Object[] array) {
/* 3120 */     return join(array, (String)null);
/*      */   }
/*      */   
/*      */   public static String join(Object[] array) {
/* 3144 */     return join(array, (String)null);
/*      */   }
/*      */   
/*      */   public static String join(Object[] array, char separator) {
/* 3170 */     if (array == null)
/* 3171 */       return null; 
/* 3174 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */   
/*      */   public static String join(Object[] array, char separator, int startIndex, int endIndex) {
/* 3204 */     if (array == null)
/* 3205 */       return null; 
/* 3207 */     int bufSize = endIndex - startIndex;
/* 3208 */     if (bufSize <= 0)
/* 3209 */       return ""; 
/* 3212 */     bufSize *= ((array[startIndex] == null) ? 16 : array[startIndex].toString().length()) + 1;
/* 3213 */     StrBuilder buf = new StrBuilder(bufSize);
/* 3215 */     for (int i = startIndex; i < endIndex; i++) {
/* 3216 */       if (i > startIndex)
/* 3217 */         buf.append(separator); 
/* 3219 */       if (array[i] != null)
/* 3220 */         buf.append(array[i]); 
/*      */     } 
/* 3223 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public static String join(Object[] array, String separator) {
/* 3251 */     if (array == null)
/* 3252 */       return null; 
/* 3254 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */   
/*      */   public static String join(Object[] array, String separator, int startIndex, int endIndex) {
/* 3285 */     if (array == null)
/* 3286 */       return null; 
/* 3288 */     if (separator == null)
/* 3289 */       separator = ""; 
/* 3294 */     int bufSize = endIndex - startIndex;
/* 3295 */     if (bufSize <= 0)
/* 3296 */       return ""; 
/* 3299 */     bufSize *= ((array[startIndex] == null) ? 16 : array[startIndex].toString().length()) + separator.length();
/* 3302 */     StrBuilder buf = new StrBuilder(bufSize);
/* 3304 */     for (int i = startIndex; i < endIndex; i++) {
/* 3305 */       if (i > startIndex)
/* 3306 */         buf.append(separator); 
/* 3308 */       if (array[i] != null)
/* 3309 */         buf.append(array[i]); 
/*      */     } 
/* 3312 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public static String join(Iterator iterator, char separator) {
/* 3332 */     if (iterator == null)
/* 3333 */       return null; 
/* 3335 */     if (!iterator.hasNext())
/* 3336 */       return ""; 
/* 3338 */     Object first = iterator.next();
/* 3339 */     if (!iterator.hasNext())
/* 3340 */       return ObjectUtils.toString(first); 
/* 3344 */     StrBuilder buf = new StrBuilder(256);
/* 3345 */     if (first != null)
/* 3346 */       buf.append(first); 
/* 3349 */     while (iterator.hasNext()) {
/* 3350 */       buf.append(separator);
/* 3351 */       Object obj = iterator.next();
/* 3352 */       if (obj != null)
/* 3353 */         buf.append(obj); 
/*      */     } 
/* 3357 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public static String join(Iterator iterator, String separator) {
/* 3376 */     if (iterator == null)
/* 3377 */       return null; 
/* 3379 */     if (!iterator.hasNext())
/* 3380 */       return ""; 
/* 3382 */     Object first = iterator.next();
/* 3383 */     if (!iterator.hasNext())
/* 3384 */       return ObjectUtils.toString(first); 
/* 3388 */     StrBuilder buf = new StrBuilder(256);
/* 3389 */     if (first != null)
/* 3390 */       buf.append(first); 
/* 3393 */     while (iterator.hasNext()) {
/* 3394 */       if (separator != null)
/* 3395 */         buf.append(separator); 
/* 3397 */       Object obj = iterator.next();
/* 3398 */       if (obj != null)
/* 3399 */         buf.append(obj); 
/*      */     } 
/* 3402 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public static String join(Collection collection, char separator) {
/* 3420 */     if (collection == null)
/* 3421 */       return null; 
/* 3423 */     return join(collection.iterator(), separator);
/*      */   }
/*      */   
/*      */   public static String join(Collection collection, String separator) {
/* 3441 */     if (collection == null)
/* 3442 */       return null; 
/* 3444 */     return join(collection.iterator(), separator);
/*      */   }
/*      */   
/*      */   public static String deleteSpaces(String str) {
/* 3476 */     if (str == null)
/* 3477 */       return null; 
/* 3479 */     return CharSetUtils.delete(str, " \t\r\n\b");
/*      */   }
/*      */   
/*      */   public static String deleteWhitespace(String str) {
/* 3497 */     if (isEmpty(str))
/* 3498 */       return str; 
/* 3500 */     int sz = str.length();
/* 3501 */     char[] chs = new char[sz];
/* 3502 */     int count = 0;
/* 3503 */     for (int i = 0; i < sz; i++) {
/* 3504 */       if (!Character.isWhitespace(str.charAt(i)))
/* 3505 */         chs[count++] = str.charAt(i); 
/*      */     } 
/* 3508 */     if (count == sz)
/* 3509 */       return str; 
/* 3511 */     return new String(chs, 0, count);
/*      */   }
/*      */   
/*      */   public static String removeStart(String str, String remove) {
/* 3541 */     if (isEmpty(str) || isEmpty(remove))
/* 3542 */       return str; 
/* 3544 */     if (str.startsWith(remove))
/* 3545 */       return str.substring(remove.length()); 
/* 3547 */     return str;
/*      */   }
/*      */   
/*      */   public static String removeStartIgnoreCase(String str, String remove) {
/* 3576 */     if (isEmpty(str) || isEmpty(remove))
/* 3577 */       return str; 
/* 3579 */     if (startsWithIgnoreCase(str, remove))
/* 3580 */       return str.substring(remove.length()); 
/* 3582 */     return str;
/*      */   }
/*      */   
/*      */   public static String removeEnd(String str, String remove) {
/* 3610 */     if (isEmpty(str) || isEmpty(remove))
/* 3611 */       return str; 
/* 3613 */     if (str.endsWith(remove))
/* 3614 */       return str.substring(0, str.length() - remove.length()); 
/* 3616 */     return str;
/*      */   }
/*      */   
/*      */   public static String removeEndIgnoreCase(String str, String remove) {
/* 3646 */     if (isEmpty(str) || isEmpty(remove))
/* 3647 */       return str; 
/* 3649 */     if (endsWithIgnoreCase(str, remove))
/* 3650 */       return str.substring(0, str.length() - remove.length()); 
/* 3652 */     return str;
/*      */   }
/*      */   
/*      */   public static String remove(String str, String remove) {
/* 3679 */     if (isEmpty(str) || isEmpty(remove))
/* 3680 */       return str; 
/* 3682 */     return replace(str, remove, "", -1);
/*      */   }
/*      */   
/*      */   public static String remove(String str, char remove) {
/* 3705 */     if (isEmpty(str) || str.indexOf(remove) == -1)
/* 3706 */       return str; 
/* 3708 */     char[] chars = str.toCharArray();
/* 3709 */     int pos = 0;
/* 3710 */     for (int i = 0; i < chars.length; i++) {
/* 3711 */       if (chars[i] != remove)
/* 3712 */         chars[pos++] = chars[i]; 
/*      */     } 
/* 3715 */     return new String(chars, 0, pos);
/*      */   }
/*      */   
/*      */   public static String replaceOnce(String text, String searchString, String replacement) {
/* 3744 */     return replace(text, searchString, replacement, 1);
/*      */   }
/*      */   
/*      */   public static String replace(String text, String searchString, String replacement) {
/* 3771 */     return replace(text, searchString, replacement, -1);
/*      */   }
/*      */   
/*      */   public static String replace(String text, String searchString, String replacement, int max) {
/* 3803 */     if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0)
/* 3804 */       return text; 
/* 3806 */     int start = 0;
/* 3807 */     int end = text.indexOf(searchString, start);
/* 3808 */     if (end == -1)
/* 3809 */       return text; 
/* 3811 */     int replLength = searchString.length();
/* 3812 */     int increase = replacement.length() - replLength;
/* 3813 */     increase = (increase < 0) ? 0 : increase;
/* 3814 */     increase *= (max < 0) ? 16 : ((max > 64) ? 64 : max);
/* 3815 */     StrBuilder buf = new StrBuilder(text.length() + increase);
/* 3816 */     while (end != -1) {
/* 3817 */       buf.append(text.substring(start, end)).append(replacement);
/* 3818 */       start = end + replLength;
/* 3819 */       if (--max == 0)
/*      */         break; 
/* 3822 */       end = text.indexOf(searchString, start);
/*      */     } 
/* 3824 */     buf.append(text.substring(start));
/* 3825 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public static String replaceEach(String text, String[] searchList, String[] replacementList) {
/* 3868 */     return replaceEach(text, searchList, replacementList, false, 0);
/*      */   }
/*      */   
/*      */   public static String replaceEachRepeatedly(String text, String[] searchList, String[] replacementList) {
/* 3919 */     int timeToLive = (searchList == null) ? 0 : searchList.length;
/* 3920 */     return replaceEach(text, searchList, replacementList, true, timeToLive);
/*      */   }
/*      */   
/*      */   private static String replaceEach(String text, String[] searchList, String[] replacementList, boolean repeat, int timeToLive) {
/* 3978 */     if (text == null || text.length() == 0 || searchList == null || searchList.length == 0 || replacementList == null || replacementList.length == 0)
/* 3981 */       return text; 
/* 3985 */     if (timeToLive < 0)
/* 3986 */       throw new IllegalStateException("TimeToLive of " + timeToLive + " is less than 0: " + text); 
/* 3989 */     int searchLength = searchList.length;
/* 3990 */     int replacementLength = replacementList.length;
/* 3993 */     if (searchLength != replacementLength)
/* 3994 */       throw new IllegalArgumentException("Search and Replace array lengths don't match: " + searchLength + " vs " + replacementLength); 
/* 4001 */     boolean[] noMoreMatchesForReplIndex = new boolean[searchLength];
/* 4004 */     int textIndex = -1;
/* 4005 */     int replaceIndex = -1;
/* 4006 */     int tempIndex = -1;
/* 4010 */     for (int i = 0; i < searchLength; i++) {
/* 4011 */       if (!noMoreMatchesForReplIndex[i] && searchList[i] != null && searchList[i].length() != 0 && replacementList[i] != null) {
/* 4016 */         tempIndex = text.indexOf(searchList[i]);
/* 4019 */         if (tempIndex == -1) {
/* 4020 */           noMoreMatchesForReplIndex[i] = true;
/* 4022 */         } else if (textIndex == -1 || tempIndex < textIndex) {
/* 4023 */           textIndex = tempIndex;
/* 4024 */           replaceIndex = i;
/*      */         } 
/*      */       } 
/*      */     } 
/* 4031 */     if (textIndex == -1)
/* 4032 */       return text; 
/* 4035 */     int start = 0;
/* 4038 */     int increase = 0;
/* 4041 */     for (int j = 0; j < searchList.length; j++) {
/* 4042 */       if (searchList[j] != null && replacementList[j] != null) {
/* 4045 */         int greater = replacementList[j].length() - searchList[j].length();
/* 4046 */         if (greater > 0)
/* 4047 */           increase += 3 * greater; 
/*      */       } 
/*      */     } 
/* 4051 */     increase = Math.min(increase, text.length() / 5);
/* 4053 */     StrBuilder buf = new StrBuilder(text.length() + increase);
/* 4055 */     while (textIndex != -1) {
/*      */       int m;
/* 4057 */       for (m = start; m < textIndex; m++)
/* 4058 */         buf.append(text.charAt(m)); 
/* 4060 */       buf.append(replacementList[replaceIndex]);
/* 4062 */       start = textIndex + searchList[replaceIndex].length();
/* 4064 */       textIndex = -1;
/* 4065 */       replaceIndex = -1;
/* 4066 */       tempIndex = -1;
/* 4069 */       for (m = 0; m < searchLength; m++) {
/* 4070 */         if (!noMoreMatchesForReplIndex[m] && searchList[m] != null && searchList[m].length() != 0 && replacementList[m] != null) {
/* 4075 */           tempIndex = text.indexOf(searchList[m], start);
/* 4078 */           if (tempIndex == -1) {
/* 4079 */             noMoreMatchesForReplIndex[m] = true;
/* 4081 */           } else if (textIndex == -1 || tempIndex < textIndex) {
/* 4082 */             textIndex = tempIndex;
/* 4083 */             replaceIndex = m;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 4090 */     int textLength = text.length();
/* 4091 */     for (int k = start; k < textLength; k++)
/* 4092 */       buf.append(text.charAt(k)); 
/* 4094 */     String result = buf.toString();
/* 4095 */     if (!repeat)
/* 4096 */       return result; 
/* 4099 */     return replaceEach(result, searchList, replacementList, repeat, timeToLive - 1);
/*      */   }
/*      */   
/*      */   public static String replaceChars(String str, char searchChar, char replaceChar) {
/* 4125 */     if (str == null)
/* 4126 */       return null; 
/* 4128 */     return str.replace(searchChar, replaceChar);
/*      */   }
/*      */   
/*      */   public static String replaceChars(String str, String searchChars, String replaceChars) {
/* 4168 */     if (isEmpty(str) || isEmpty(searchChars))
/* 4169 */       return str; 
/* 4171 */     if (replaceChars == null)
/* 4172 */       replaceChars = ""; 
/* 4174 */     boolean modified = false;
/* 4175 */     int replaceCharsLength = replaceChars.length();
/* 4176 */     int strLength = str.length();
/* 4177 */     StrBuilder buf = new StrBuilder(strLength);
/* 4178 */     for (int i = 0; i < strLength; i++) {
/* 4179 */       char ch = str.charAt(i);
/* 4180 */       int index = searchChars.indexOf(ch);
/* 4181 */       if (index >= 0) {
/* 4182 */         modified = true;
/* 4183 */         if (index < replaceCharsLength)
/* 4184 */           buf.append(replaceChars.charAt(index)); 
/*      */       } else {
/* 4187 */         buf.append(ch);
/*      */       } 
/*      */     } 
/* 4190 */     if (modified)
/* 4191 */       return buf.toString(); 
/* 4193 */     return str;
/*      */   }
/*      */   
/*      */   public static String overlayString(String text, String overlay, int start, int end) {
/* 4224 */     return (new StrBuilder(start + overlay.length() + text.length() - end + 1)).append(text.substring(0, start)).append(overlay).append(text.substring(end)).toString();
/*      */   }
/*      */   
/*      */   public static String overlay(String str, String overlay, int start, int end) {
/* 4261 */     if (str == null)
/* 4262 */       return null; 
/* 4264 */     if (overlay == null)
/* 4265 */       overlay = ""; 
/* 4267 */     int len = str.length();
/* 4268 */     if (start < 0)
/* 4269 */       start = 0; 
/* 4271 */     if (start > len)
/* 4272 */       start = len; 
/* 4274 */     if (end < 0)
/* 4275 */       end = 0; 
/* 4277 */     if (end > len)
/* 4278 */       end = len; 
/* 4280 */     if (start > end) {
/* 4281 */       int temp = start;
/* 4282 */       start = end;
/* 4283 */       end = temp;
/*      */     } 
/* 4285 */     return (new StrBuilder(len + start - end + overlay.length() + 1)).append(str.substring(0, start)).append(overlay).append(str.substring(end)).toString();
/*      */   }
/*      */   
/*      */   public static String chomp(String str) {
/* 4320 */     if (isEmpty(str))
/* 4321 */       return str; 
/* 4324 */     if (str.length() == 1) {
/* 4325 */       char ch = str.charAt(0);
/* 4326 */       if (ch == '\r' || ch == '\n')
/* 4327 */         return ""; 
/* 4329 */       return str;
/*      */     } 
/* 4332 */     int lastIdx = str.length() - 1;
/* 4333 */     char last = str.charAt(lastIdx);
/* 4335 */     if (last == '\n') {
/* 4336 */       if (str.charAt(lastIdx - 1) == '\r')
/* 4337 */         lastIdx--; 
/* 4339 */     } else if (last != '\r') {
/* 4340 */       lastIdx++;
/*      */     } 
/* 4342 */     return str.substring(0, lastIdx);
/*      */   }
/*      */   
/*      */   public static String chomp(String str, String separator) {
/* 4372 */     if (isEmpty(str) || separator == null)
/* 4373 */       return str; 
/* 4375 */     if (str.endsWith(separator))
/* 4376 */       return str.substring(0, str.length() - separator.length()); 
/* 4378 */     return str;
/*      */   }
/*      */   
/*      */   public static String chompLast(String str) {
/* 4392 */     return chompLast(str, "\n");
/*      */   }
/*      */   
/*      */   public static String chompLast(String str, String sep) {
/* 4406 */     if (str.length() == 0)
/* 4407 */       return str; 
/* 4409 */     String sub = str.substring(str.length() - sep.length());
/* 4410 */     if (sep.equals(sub))
/* 4411 */       return str.substring(0, str.length() - sep.length()); 
/* 4413 */     return str;
/*      */   }
/*      */   
/*      */   public static String getChomp(String str, String sep) {
/* 4429 */     int idx = str.lastIndexOf(sep);
/* 4430 */     if (idx == str.length() - sep.length())
/* 4431 */       return sep; 
/* 4432 */     if (idx != -1)
/* 4433 */       return str.substring(idx); 
/* 4435 */     return "";
/*      */   }
/*      */   
/*      */   public static String prechomp(String str, String sep) {
/* 4451 */     int idx = str.indexOf(sep);
/* 4452 */     if (idx == -1)
/* 4453 */       return str; 
/* 4455 */     return str.substring(idx + sep.length());
/*      */   }
/*      */   
/*      */   public static String getPrechomp(String str, String sep) {
/* 4471 */     int idx = str.indexOf(sep);
/* 4472 */     if (idx == -1)
/* 4473 */       return ""; 
/* 4475 */     return str.substring(0, idx + sep.length());
/*      */   }
/*      */   
/*      */   public static String chop(String str) {
/* 4504 */     if (str == null)
/* 4505 */       return null; 
/* 4507 */     int strLen = str.length();
/* 4508 */     if (strLen < 2)
/* 4509 */       return ""; 
/* 4511 */     int lastIdx = strLen - 1;
/* 4512 */     String ret = str.substring(0, lastIdx);
/* 4513 */     char last = str.charAt(lastIdx);
/* 4514 */     if (last == '\n' && 
/* 4515 */       ret.charAt(lastIdx - 1) == '\r')
/* 4516 */       return ret.substring(0, lastIdx - 1); 
/* 4519 */     return ret;
/*      */   }
/*      */   
/*      */   public static String chopNewline(String str) {
/* 4533 */     int lastIdx = str.length() - 1;
/* 4534 */     if (lastIdx <= 0)
/* 4535 */       return ""; 
/* 4537 */     char last = str.charAt(lastIdx);
/* 4538 */     if (last == '\n') {
/* 4539 */       if (str.charAt(lastIdx - 1) == '\r')
/* 4540 */         lastIdx--; 
/*      */     } else {
/* 4543 */       lastIdx++;
/*      */     } 
/* 4545 */     return str.substring(0, lastIdx);
/*      */   }
/*      */   
/*      */   public static String escape(String str) {
/* 4567 */     return StringEscapeUtils.escapeJava(str);
/*      */   }
/*      */   
/*      */   public static String repeat(String str, int repeat) {
/*      */     char ch, output1[];
/*      */     int i;
/*      */     char ch0, ch1, output2[];
/*      */     int j;
/* 4593 */     if (str == null)
/* 4594 */       return null; 
/* 4596 */     if (repeat <= 0)
/* 4597 */       return ""; 
/* 4599 */     int inputLength = str.length();
/* 4600 */     if (repeat == 1 || inputLength == 0)
/* 4601 */       return str; 
/* 4603 */     if (inputLength == 1 && repeat <= 8192)
/* 4604 */       return padding(repeat, str.charAt(0)); 
/* 4607 */     int outputLength = inputLength * repeat;
/* 4608 */     switch (inputLength) {
/*      */       case 1:
/* 4610 */         ch = str.charAt(0);
/* 4611 */         output1 = new char[outputLength];
/* 4612 */         for (i = repeat - 1; i >= 0; i--)
/* 4613 */           output1[i] = ch; 
/* 4615 */         return new String(output1);
/*      */       case 2:
/* 4617 */         ch0 = str.charAt(0);
/* 4618 */         ch1 = str.charAt(1);
/* 4619 */         output2 = new char[outputLength];
/* 4620 */         for (j = repeat * 2 - 2; j >= 0; j--, j--) {
/* 4621 */           output2[j] = ch0;
/* 4622 */           output2[j + 1] = ch1;
/*      */         } 
/* 4624 */         return new String(output2);
/*      */     } 
/* 4626 */     StrBuilder buf = new StrBuilder(outputLength);
/* 4627 */     for (int k = 0; k < repeat; k++)
/* 4628 */       buf.append(str); 
/* 4630 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public static String repeat(String str, String separator, int repeat) {
/* 4655 */     if (str == null || separator == null)
/* 4656 */       return repeat(str, repeat); 
/* 4659 */     String result = repeat(str + separator, repeat);
/* 4660 */     return removeEnd(result, separator);
/*      */   }
/*      */   
/*      */   private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
/* 4688 */     if (repeat < 0)
/* 4689 */       throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat); 
/* 4691 */     char[] buf = new char[repeat];
/* 4692 */     for (int i = 0; i < buf.length; i++)
/* 4693 */       buf[i] = padChar; 
/* 4695 */     return new String(buf);
/*      */   }
/*      */   
/*      */   public static String rightPad(String str, int size) {
/* 4718 */     return rightPad(str, size, ' ');
/*      */   }
/*      */   
/*      */   public static String rightPad(String str, int size, char padChar) {
/* 4743 */     if (str == null)
/* 4744 */       return null; 
/* 4746 */     int pads = size - str.length();
/* 4747 */     if (pads <= 0)
/* 4748 */       return str; 
/* 4750 */     if (pads > 8192)
/* 4751 */       return rightPad(str, size, String.valueOf(padChar)); 
/* 4753 */     return str.concat(padding(pads, padChar));
/*      */   }
/*      */   
/*      */   public static String rightPad(String str, int size, String padStr) {
/* 4780 */     if (str == null)
/* 4781 */       return null; 
/* 4783 */     if (isEmpty(padStr))
/* 4784 */       padStr = " "; 
/* 4786 */     int padLen = padStr.length();
/* 4787 */     int strLen = str.length();
/* 4788 */     int pads = size - strLen;
/* 4789 */     if (pads <= 0)
/* 4790 */       return str; 
/* 4792 */     if (padLen == 1 && pads <= 8192)
/* 4793 */       return rightPad(str, size, padStr.charAt(0)); 
/* 4796 */     if (pads == padLen)
/* 4797 */       return str.concat(padStr); 
/* 4798 */     if (pads < padLen)
/* 4799 */       return str.concat(padStr.substring(0, pads)); 
/* 4801 */     char[] padding = new char[pads];
/* 4802 */     char[] padChars = padStr.toCharArray();
/* 4803 */     for (int i = 0; i < pads; i++)
/* 4804 */       padding[i] = padChars[i % padLen]; 
/* 4806 */     return str.concat(new String(padding));
/*      */   }
/*      */   
/*      */   public static String leftPad(String str, int size) {
/* 4830 */     return leftPad(str, size, ' ');
/*      */   }
/*      */   
/*      */   public static String leftPad(String str, int size, char padChar) {
/* 4855 */     if (str == null)
/* 4856 */       return null; 
/* 4858 */     int pads = size - str.length();
/* 4859 */     if (pads <= 0)
/* 4860 */       return str; 
/* 4862 */     if (pads > 8192)
/* 4863 */       return leftPad(str, size, String.valueOf(padChar)); 
/* 4865 */     return padding(pads, padChar).concat(str);
/*      */   }
/*      */   
/*      */   public static String leftPad(String str, int size, String padStr) {
/* 4892 */     if (str == null)
/* 4893 */       return null; 
/* 4895 */     if (isEmpty(padStr))
/* 4896 */       padStr = " "; 
/* 4898 */     int padLen = padStr.length();
/* 4899 */     int strLen = str.length();
/* 4900 */     int pads = size - strLen;
/* 4901 */     if (pads <= 0)
/* 4902 */       return str; 
/* 4904 */     if (padLen == 1 && pads <= 8192)
/* 4905 */       return leftPad(str, size, padStr.charAt(0)); 
/* 4908 */     if (pads == padLen)
/* 4909 */       return padStr.concat(str); 
/* 4910 */     if (pads < padLen)
/* 4911 */       return padStr.substring(0, pads).concat(str); 
/* 4913 */     char[] padding = new char[pads];
/* 4914 */     char[] padChars = padStr.toCharArray();
/* 4915 */     for (int i = 0; i < pads; i++)
/* 4916 */       padding[i] = padChars[i % padLen]; 
/* 4918 */     return (new String(padding)).concat(str);
/*      */   }
/*      */   
/*      */   public static int length(String str) {
/* 4931 */     return (str == null) ? 0 : str.length();
/*      */   }
/*      */   
/*      */   public static String center(String str, int size) {
/* 4960 */     return center(str, size, ' ');
/*      */   }
/*      */   
/*      */   public static String center(String str, int size, char padChar) {
/* 4988 */     if (str == null || size <= 0)
/* 4989 */       return str; 
/* 4991 */     int strLen = str.length();
/* 4992 */     int pads = size - strLen;
/* 4993 */     if (pads <= 0)
/* 4994 */       return str; 
/* 4996 */     str = leftPad(str, strLen + pads / 2, padChar);
/* 4997 */     str = rightPad(str, size, padChar);
/* 4998 */     return str;
/*      */   }
/*      */   
/*      */   public static String center(String str, int size, String padStr) {
/* 5028 */     if (str == null || size <= 0)
/* 5029 */       return str; 
/* 5031 */     if (isEmpty(padStr))
/* 5032 */       padStr = " "; 
/* 5034 */     int strLen = str.length();
/* 5035 */     int pads = size - strLen;
/* 5036 */     if (pads <= 0)
/* 5037 */       return str; 
/* 5039 */     str = leftPad(str, strLen + pads / 2, padStr);
/* 5040 */     str = rightPad(str, size, padStr);
/* 5041 */     return str;
/*      */   }
/*      */   
/*      */   public static String upperCase(String str) {
/* 5066 */     if (str == null)
/* 5067 */       return null; 
/* 5069 */     return str.toUpperCase();
/*      */   }
/*      */   
/*      */   public static String upperCase(String str, Locale locale) {
/* 5089 */     if (str == null)
/* 5090 */       return null; 
/* 5092 */     return str.toUpperCase(locale);
/*      */   }
/*      */   
/*      */   public static String lowerCase(String str) {
/* 5115 */     if (str == null)
/* 5116 */       return null; 
/* 5118 */     return str.toLowerCase();
/*      */   }
/*      */   
/*      */   public static String lowerCase(String str, Locale locale) {
/* 5138 */     if (str == null)
/* 5139 */       return null; 
/* 5141 */     return str.toLowerCase(locale);
/*      */   }
/*      */   
/*      */   public static String capitalize(String str) {
/*      */     int strLen;
/* 5166 */     if (str == null || (strLen = str.length()) == 0)
/* 5167 */       return str; 
/* 5169 */     return (new StrBuilder(strLen)).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
/*      */   }
/*      */   
/*      */   public static String capitalise(String str) {
/* 5185 */     return capitalize(str);
/*      */   }
/*      */   
/*      */   public static String uncapitalize(String str) {
/*      */     int strLen;
/* 5210 */     if (str == null || (strLen = str.length()) == 0)
/* 5211 */       return str; 
/* 5213 */     return (new StrBuilder(strLen)).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
/*      */   }
/*      */   
/*      */   public static String uncapitalise(String str) {
/* 5229 */     return uncapitalize(str);
/*      */   }
/*      */   
/*      */   public static String swapCase(String str) {
/*      */     int strLen;
/* 5261 */     if (str == null || (strLen = str.length()) == 0)
/* 5262 */       return str; 
/* 5264 */     StrBuilder buffer = new StrBuilder(strLen);
/* 5266 */     char ch = Character.MIN_VALUE;
/* 5267 */     for (int i = 0; i < strLen; i++) {
/* 5268 */       ch = str.charAt(i);
/* 5269 */       if (Character.isUpperCase(ch)) {
/* 5270 */         ch = Character.toLowerCase(ch);
/* 5271 */       } else if (Character.isTitleCase(ch)) {
/* 5272 */         ch = Character.toLowerCase(ch);
/* 5273 */       } else if (Character.isLowerCase(ch)) {
/* 5274 */         ch = Character.toUpperCase(ch);
/*      */       } 
/* 5276 */       buffer.append(ch);
/*      */     } 
/* 5278 */     return buffer.toString();
/*      */   }
/*      */   
/*      */   public static String capitaliseAllWords(String str) {
/* 5294 */     return WordUtils.capitalize(str);
/*      */   }
/*      */   
/*      */   public static int countMatches(String str, String sub) {
/* 5319 */     if (isEmpty(str) || isEmpty(sub))
/* 5320 */       return 0; 
/* 5322 */     int count = 0;
/* 5323 */     int idx = 0;
/* 5324 */     while ((idx = str.indexOf(sub, idx)) != -1) {
/* 5325 */       count++;
/* 5326 */       idx += sub.length();
/*      */     } 
/* 5328 */     return count;
/*      */   }
/*      */   
/*      */   public static boolean isAlpha(String str) {
/* 5352 */     if (str == null)
/* 5353 */       return false; 
/* 5355 */     int sz = str.length();
/* 5356 */     for (int i = 0; i < sz; i++) {
/* 5357 */       if (!Character.isLetter(str.charAt(i)))
/* 5358 */         return false; 
/*      */     } 
/* 5361 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isAlphaSpace(String str) {
/* 5386 */     if (str == null)
/* 5387 */       return false; 
/* 5389 */     int sz = str.length();
/* 5390 */     for (int i = 0; i < sz; i++) {
/* 5391 */       if (!Character.isLetter(str.charAt(i)) && str.charAt(i) != ' ')
/* 5392 */         return false; 
/*      */     } 
/* 5395 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isAlphanumeric(String str) {
/* 5419 */     if (str == null)
/* 5420 */       return false; 
/* 5422 */     int sz = str.length();
/* 5423 */     for (int i = 0; i < sz; i++) {
/* 5424 */       if (!Character.isLetterOrDigit(str.charAt(i)))
/* 5425 */         return false; 
/*      */     } 
/* 5428 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isAlphanumericSpace(String str) {
/* 5453 */     if (str == null)
/* 5454 */       return false; 
/* 5456 */     int sz = str.length();
/* 5457 */     for (int i = 0; i < sz; i++) {
/* 5458 */       if (!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i) != ' ')
/* 5459 */         return false; 
/*      */     } 
/* 5462 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isAsciiPrintable(String str) {
/* 5491 */     if (str == null)
/* 5492 */       return false; 
/* 5494 */     int sz = str.length();
/* 5495 */     for (int i = 0; i < sz; i++) {
/* 5496 */       if (!CharUtils.isAsciiPrintable(str.charAt(i)))
/* 5497 */         return false; 
/*      */     } 
/* 5500 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isNumeric(String str) {
/* 5525 */     if (str == null)
/* 5526 */       return false; 
/* 5528 */     int sz = str.length();
/* 5529 */     for (int i = 0; i < sz; i++) {
/* 5530 */       if (!Character.isDigit(str.charAt(i)))
/* 5531 */         return false; 
/*      */     } 
/* 5534 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isNumericSpace(String str) {
/* 5561 */     if (str == null)
/* 5562 */       return false; 
/* 5564 */     int sz = str.length();
/* 5565 */     for (int i = 0; i < sz; i++) {
/* 5566 */       if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != ' ')
/* 5567 */         return false; 
/*      */     } 
/* 5570 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isWhitespace(String str) {
/* 5593 */     if (str == null)
/* 5594 */       return false; 
/* 5596 */     int sz = str.length();
/* 5597 */     for (int i = 0; i < sz; i++) {
/* 5598 */       if (!Character.isWhitespace(str.charAt(i)))
/* 5599 */         return false; 
/*      */     } 
/* 5602 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isAllLowerCase(String str) {
/* 5624 */     if (str == null || isEmpty(str))
/* 5625 */       return false; 
/* 5627 */     int sz = str.length();
/* 5628 */     for (int i = 0; i < sz; i++) {
/* 5629 */       if (!Character.isLowerCase(str.charAt(i)))
/* 5630 */         return false; 
/*      */     } 
/* 5633 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isAllUpperCase(String str) {
/* 5655 */     if (str == null || isEmpty(str))
/* 5656 */       return false; 
/* 5658 */     int sz = str.length();
/* 5659 */     for (int i = 0; i < sz; i++) {
/* 5660 */       if (!Character.isUpperCase(str.charAt(i)))
/* 5661 */         return false; 
/*      */     } 
/* 5664 */     return true;
/*      */   }
/*      */   
/*      */   public static String defaultString(String str) {
/* 5686 */     return (str == null) ? "" : str;
/*      */   }
/*      */   
/*      */   public static String defaultString(String str, String defaultStr) {
/* 5707 */     return (str == null) ? defaultStr : str;
/*      */   }
/*      */   
/*      */   public static String defaultIfBlank(String str, String defaultStr) {
/* 5729 */     return isBlank(str) ? defaultStr : str;
/*      */   }
/*      */   
/*      */   public static String defaultIfEmpty(String str, String defaultStr) {
/* 5750 */     return isEmpty(str) ? defaultStr : str;
/*      */   }
/*      */   
/*      */   public static String reverse(String str) {
/* 5770 */     if (str == null)
/* 5771 */       return null; 
/* 5773 */     return (new StrBuilder(str)).reverse().toString();
/*      */   }
/*      */   
/*      */   public static String reverseDelimited(String str, char separatorChar) {
/* 5796 */     if (str == null)
/* 5797 */       return null; 
/* 5801 */     String[] strs = split(str, separatorChar);
/* 5802 */     ArrayUtils.reverse((Object[])strs);
/* 5803 */     return join((Object[])strs, separatorChar);
/*      */   }
/*      */   
/*      */   public static String reverseDelimitedString(String str, String separatorChars) {
/* 5829 */     if (str == null)
/* 5830 */       return null; 
/* 5834 */     String[] strs = split(str, separatorChars);
/* 5835 */     ArrayUtils.reverse((Object[])strs);
/* 5836 */     if (separatorChars == null)
/* 5837 */       return join((Object[])strs, ' '); 
/* 5839 */     return join((Object[])strs, separatorChars);
/*      */   }
/*      */   
/*      */   public static String abbreviate(String str, int maxWidth) {
/* 5877 */     return abbreviate(str, 0, maxWidth);
/*      */   }
/*      */   
/*      */   public static String abbreviate(String str, int offset, int maxWidth) {
/* 5916 */     if (str == null)
/* 5917 */       return null; 
/* 5919 */     if (maxWidth < 4)
/* 5920 */       throw new IllegalArgumentException("Minimum abbreviation width is 4"); 
/* 5922 */     if (str.length() <= maxWidth)
/* 5923 */       return str; 
/* 5925 */     if (offset > str.length())
/* 5926 */       offset = str.length(); 
/* 5928 */     if (str.length() - offset < maxWidth - 3)
/* 5929 */       offset = str.length() - maxWidth - 3; 
/* 5931 */     if (offset <= 4)
/* 5932 */       return str.substring(0, maxWidth - 3) + "..."; 
/* 5934 */     if (maxWidth < 7)
/* 5935 */       throw new IllegalArgumentException("Minimum abbreviation width with offset is 7"); 
/* 5937 */     if (offset + maxWidth - 3 < str.length())
/* 5938 */       return "..." + abbreviate(str.substring(offset), maxWidth - 3); 
/* 5940 */     return "..." + str.substring(str.length() - maxWidth - 3);
/*      */   }
/*      */   
/*      */   public static String abbreviateMiddle(String str, String middle, int length) {
/* 5973 */     if (isEmpty(str) || isEmpty(middle))
/* 5974 */       return str; 
/* 5977 */     if (length >= str.length() || length < middle.length() + 2)
/* 5978 */       return str; 
/* 5981 */     int targetSting = length - middle.length();
/* 5982 */     int startOffset = targetSting / 2 + targetSting % 2;
/* 5983 */     int endOffset = str.length() - targetSting / 2;
/* 5985 */     StrBuilder builder = new StrBuilder(length);
/* 5986 */     builder.append(str.substring(0, startOffset));
/* 5987 */     builder.append(middle);
/* 5988 */     builder.append(str.substring(endOffset));
/* 5990 */     return builder.toString();
/*      */   }
/*      */   
/*      */   public static String difference(String str1, String str2) {
/* 6021 */     if (str1 == null)
/* 6022 */       return str2; 
/* 6024 */     if (str2 == null)
/* 6025 */       return str1; 
/* 6027 */     int at = indexOfDifference(str1, str2);
/* 6028 */     if (at == -1)
/* 6029 */       return ""; 
/* 6031 */     return str2.substring(at);
/*      */   }
/*      */   
/*      */   public static int indexOfDifference(String str1, String str2) {
/* 6058 */     if (str1 == str2)
/* 6059 */       return -1; 
/* 6061 */     if (str1 == null || str2 == null)
/* 6062 */       return 0; 
/*      */     int i;
/* 6065 */     for (i = 0; i < str1.length() && i < str2.length() && 
/* 6066 */       str1.charAt(i) == str2.charAt(i); i++);
/* 6070 */     if (i < str2.length() || i < str1.length())
/* 6071 */       return i; 
/* 6073 */     return -1;
/*      */   }
/*      */   
/*      */   public static int indexOfDifference(String[] strs) {
/* 6108 */     if (strs == null || strs.length <= 1)
/* 6109 */       return -1; 
/* 6111 */     boolean anyStringNull = false;
/* 6112 */     boolean allStringsNull = true;
/* 6113 */     int arrayLen = strs.length;
/* 6114 */     int shortestStrLen = Integer.MAX_VALUE;
/* 6115 */     int longestStrLen = 0;
/* 6120 */     for (int i = 0; i < arrayLen; i++) {
/* 6121 */       if (strs[i] == null) {
/* 6122 */         anyStringNull = true;
/* 6123 */         shortestStrLen = 0;
/*      */       } else {
/* 6125 */         allStringsNull = false;
/* 6126 */         shortestStrLen = Math.min(strs[i].length(), shortestStrLen);
/* 6127 */         longestStrLen = Math.max(strs[i].length(), longestStrLen);
/*      */       } 
/*      */     } 
/* 6132 */     if (allStringsNull || (longestStrLen == 0 && !anyStringNull))
/* 6133 */       return -1; 
/* 6137 */     if (shortestStrLen == 0)
/* 6138 */       return 0; 
/* 6142 */     int firstDiff = -1;
/* 6143 */     for (int stringPos = 0; stringPos < shortestStrLen; stringPos++) {
/* 6144 */       char comparisonChar = strs[0].charAt(stringPos);
/* 6145 */       for (int arrayPos = 1; arrayPos < arrayLen; arrayPos++) {
/* 6146 */         if (strs[arrayPos].charAt(stringPos) != comparisonChar) {
/* 6147 */           firstDiff = stringPos;
/*      */           break;
/*      */         } 
/*      */       } 
/* 6151 */       if (firstDiff != -1)
/*      */         break; 
/*      */     } 
/* 6156 */     if (firstDiff == -1 && shortestStrLen != longestStrLen)
/* 6160 */       return shortestStrLen; 
/* 6162 */     return firstDiff;
/*      */   }
/*      */   
/*      */   public static String getCommonPrefix(String[] strs) {
/* 6199 */     if (strs == null || strs.length == 0)
/* 6200 */       return ""; 
/* 6202 */     int smallestIndexOfDiff = indexOfDifference(strs);
/* 6203 */     if (smallestIndexOfDiff == -1) {
/* 6205 */       if (strs[0] == null)
/* 6206 */         return ""; 
/* 6208 */       return strs[0];
/*      */     } 
/* 6209 */     if (smallestIndexOfDiff == 0)
/* 6211 */       return ""; 
/* 6214 */     return strs[0].substring(0, smallestIndexOfDiff);
/*      */   }
/*      */   
/*      */   public static int getLevenshteinDistance(String s, String t) {
/* 6255 */     if (s == null || t == null)
/* 6256 */       throw new IllegalArgumentException("Strings must not be null"); 
/* 6276 */     int n = s.length();
/* 6277 */     int m = t.length();
/* 6279 */     if (n == 0)
/* 6280 */       return m; 
/* 6281 */     if (m == 0)
/* 6282 */       return n; 
/* 6285 */     if (n > m) {
/* 6287 */       String tmp = s;
/* 6288 */       s = t;
/* 6289 */       t = tmp;
/* 6290 */       n = m;
/* 6291 */       m = t.length();
/*      */     } 
/* 6294 */     int[] p = new int[n + 1];
/* 6295 */     int[] d = new int[n + 1];
/*      */     int i;
/* 6306 */     for (i = 0; i <= n; i++)
/* 6307 */       p[i] = i; 
/* 6310 */     for (int j = 1; j <= m; j++) {
/* 6311 */       char t_j = t.charAt(j - 1);
/* 6312 */       d[0] = j;
/* 6314 */       for (i = 1; i <= n; i++) {
/* 6315 */         int cost = (s.charAt(i - 1) == t_j) ? 0 : 1;
/* 6317 */         d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
/*      */       } 
/* 6321 */       int[] _d = p;
/* 6322 */       p = d;
/* 6323 */       d = _d;
/*      */     } 
/* 6328 */     return p[n];
/*      */   }
/*      */   
/*      */   public static boolean startsWith(String str, String prefix) {
/* 6356 */     return startsWith(str, prefix, false);
/*      */   }
/*      */   
/*      */   public static boolean startsWithIgnoreCase(String str, String prefix) {
/* 6381 */     return startsWith(str, prefix, true);
/*      */   }
/*      */   
/*      */   private static boolean startsWith(String str, String prefix, boolean ignoreCase) {
/* 6396 */     if (str == null || prefix == null)
/* 6397 */       return (str == null && prefix == null); 
/* 6399 */     if (prefix.length() > str.length())
/* 6400 */       return false; 
/* 6402 */     return str.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
/*      */   }
/*      */   
/*      */   public static boolean startsWithAny(String string, String[] searchStrings) {
/* 6425 */     if (isEmpty(string) || ArrayUtils.isEmpty((Object[])searchStrings))
/* 6426 */       return false; 
/* 6428 */     for (int i = 0; i < searchStrings.length; i++) {
/* 6429 */       String searchString = searchStrings[i];
/* 6430 */       if (startsWith(string, searchString))
/* 6431 */         return true; 
/*      */     } 
/* 6434 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean endsWith(String str, String suffix) {
/* 6463 */     return endsWith(str, suffix, false);
/*      */   }
/*      */   
/*      */   public static boolean endsWithIgnoreCase(String str, String suffix) {
/* 6489 */     return endsWith(str, suffix, true);
/*      */   }
/*      */   
/*      */   private static boolean endsWith(String str, String suffix, boolean ignoreCase) {
/* 6504 */     if (str == null || suffix == null)
/* 6505 */       return (str == null && suffix == null); 
/* 6507 */     if (suffix.length() > str.length())
/* 6508 */       return false; 
/* 6510 */     int strOffset = str.length() - suffix.length();
/* 6511 */     return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
/*      */   }
/*      */   
/*      */   public static String normalizeSpace(String str) {
/* 6545 */     str = strip(str);
/* 6546 */     if (str == null || str.length() <= 2)
/* 6547 */       return str; 
/* 6549 */     StrBuilder b = new StrBuilder(str.length());
/* 6550 */     for (int i = 0; i < str.length(); i++) {
/* 6551 */       char c = str.charAt(i);
/* 6552 */       if (Character.isWhitespace(c)) {
/* 6553 */         if (i > 0 && !Character.isWhitespace(str.charAt(i - 1)))
/* 6554 */           b.append(' '); 
/*      */       } else {
/* 6557 */         b.append(c);
/*      */       } 
/*      */     } 
/* 6560 */     return b.toString();
/*      */   }
/*      */   
/*      */   public static boolean endsWithAny(String string, String[] searchStrings) {
/* 6582 */     if (isEmpty(string) || ArrayUtils.isEmpty((Object[])searchStrings))
/* 6583 */       return false; 
/* 6585 */     for (int i = 0; i < searchStrings.length; i++) {
/* 6586 */       String searchString = searchStrings[i];
/* 6587 */       if (endsWith(string, searchString))
/* 6588 */         return true; 
/*      */     } 
/* 6591 */     return false;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\StringUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */