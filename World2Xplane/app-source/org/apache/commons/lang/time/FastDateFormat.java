/*      */ package org.apache.commons.lang.time;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.text.DateFormat;
/*      */ import java.text.DateFormatSymbols;
/*      */ import java.text.FieldPosition;
/*      */ import java.text.Format;
/*      */ import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.TimeZone;
/*      */ import org.apache.commons.lang.Validate;
/*      */ import org.apache.commons.lang.text.StrBuilder;
/*      */ 
/*      */ public class FastDateFormat extends Format {
/*      */   private static final long serialVersionUID = 1L;
/*      */   
/*      */   public static final int FULL = 0;
/*      */   
/*      */   public static final int LONG = 1;
/*      */   
/*      */   public static final int MEDIUM = 2;
/*      */   
/*      */   public static final int SHORT = 3;
/*      */   
/*      */   private static String cDefaultPattern;
/*      */   
/*  111 */   private static final Map cInstanceCache = new HashMap(7);
/*      */   
/*  112 */   private static final Map cDateInstanceCache = new HashMap(7);
/*      */   
/*  113 */   private static final Map cTimeInstanceCache = new HashMap(7);
/*      */   
/*  114 */   private static final Map cDateTimeInstanceCache = new HashMap(7);
/*      */   
/*  115 */   private static final Map cTimeZoneDisplayCache = new HashMap(7);
/*      */   
/*      */   private final String mPattern;
/*      */   
/*      */   private final TimeZone mTimeZone;
/*      */   
/*      */   private final boolean mTimeZoneForced;
/*      */   
/*      */   private final Locale mLocale;
/*      */   
/*      */   private final boolean mLocaleForced;
/*      */   
/*      */   private transient Rule[] mRules;
/*      */   
/*      */   private transient int mMaxLengthEstimate;
/*      */   
/*      */   public static FastDateFormat getInstance() {
/*  154 */     return getInstance(getDefaultPattern(), null, null);
/*      */   }
/*      */   
/*      */   public static FastDateFormat getInstance(String pattern) {
/*  167 */     return getInstance(pattern, null, null);
/*      */   }
/*      */   
/*      */   public static FastDateFormat getInstance(String pattern, TimeZone timeZone) {
/*  182 */     return getInstance(pattern, timeZone, null);
/*      */   }
/*      */   
/*      */   public static FastDateFormat getInstance(String pattern, Locale locale) {
/*  196 */     return getInstance(pattern, null, locale);
/*      */   }
/*      */   
/*      */   public static synchronized FastDateFormat getInstance(String pattern, TimeZone timeZone, Locale locale) {
/*  213 */     FastDateFormat emptyFormat = new FastDateFormat(pattern, timeZone, locale);
/*  214 */     FastDateFormat format = (FastDateFormat)cInstanceCache.get(emptyFormat);
/*  215 */     if (format == null) {
/*  216 */       format = emptyFormat;
/*  217 */       format.init();
/*  218 */       cInstanceCache.put(format, format);
/*      */     } 
/*  220 */     return format;
/*      */   }
/*      */   
/*      */   public static FastDateFormat getDateInstance(int style) {
/*  235 */     return getDateInstance(style, null, null);
/*      */   }
/*      */   
/*      */   public static FastDateFormat getDateInstance(int style, Locale locale) {
/*  250 */     return getDateInstance(style, null, locale);
/*      */   }
/*      */   
/*      */   public static FastDateFormat getDateInstance(int style, TimeZone timeZone) {
/*  266 */     return getDateInstance(style, timeZone, null);
/*      */   }
/*      */   
/*      */   public static synchronized FastDateFormat getDateInstance(int style, TimeZone timeZone, Locale locale) {
/*  281 */     Object key = new Integer(style);
/*  282 */     if (timeZone != null)
/*  283 */       key = new Pair(key, timeZone); 
/*  286 */     if (locale == null)
/*  287 */       locale = Locale.getDefault(); 
/*  290 */     key = new Pair(key, locale);
/*  292 */     FastDateFormat format = (FastDateFormat)cDateInstanceCache.get(key);
/*  293 */     if (format == null)
/*      */       try {
/*  295 */         SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateInstance(style, locale);
/*  296 */         String pattern = formatter.toPattern();
/*  297 */         format = getInstance(pattern, timeZone, locale);
/*  298 */         cDateInstanceCache.put(key, format);
/*  300 */       } catch (ClassCastException ex) {
/*  301 */         throw new IllegalArgumentException("No date pattern for locale: " + locale);
/*      */       }  
/*  304 */     return format;
/*      */   }
/*      */   
/*      */   public static FastDateFormat getTimeInstance(int style) {
/*  319 */     return getTimeInstance(style, null, null);
/*      */   }
/*      */   
/*      */   public static FastDateFormat getTimeInstance(int style, Locale locale) {
/*  334 */     return getTimeInstance(style, null, locale);
/*      */   }
/*      */   
/*      */   public static FastDateFormat getTimeInstance(int style, TimeZone timeZone) {
/*  350 */     return getTimeInstance(style, timeZone, null);
/*      */   }
/*      */   
/*      */   public static synchronized FastDateFormat getTimeInstance(int style, TimeZone timeZone, Locale locale) {
/*  366 */     Object key = new Integer(style);
/*  367 */     if (timeZone != null)
/*  368 */       key = new Pair(key, timeZone); 
/*  370 */     if (locale != null)
/*  371 */       key = new Pair(key, locale); 
/*  374 */     FastDateFormat format = (FastDateFormat)cTimeInstanceCache.get(key);
/*  375 */     if (format == null) {
/*  376 */       if (locale == null)
/*  377 */         locale = Locale.getDefault(); 
/*      */       try {
/*  381 */         SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getTimeInstance(style, locale);
/*  382 */         String pattern = formatter.toPattern();
/*  383 */         format = getInstance(pattern, timeZone, locale);
/*  384 */         cTimeInstanceCache.put(key, format);
/*  386 */       } catch (ClassCastException ex) {
/*  387 */         throw new IllegalArgumentException("No date pattern for locale: " + locale);
/*      */       } 
/*      */     } 
/*  390 */     return format;
/*      */   }
/*      */   
/*      */   public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle) {
/*  407 */     return getDateTimeInstance(dateStyle, timeStyle, null, null);
/*      */   }
/*      */   
/*      */   public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, Locale locale) {
/*  424 */     return getDateTimeInstance(dateStyle, timeStyle, null, locale);
/*      */   }
/*      */   
/*      */   public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone) {
/*  442 */     return getDateTimeInstance(dateStyle, timeStyle, timeZone, null);
/*      */   }
/*      */   
/*      */   public static synchronized FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone, Locale locale) {
/*  460 */     Object key = new Pair(new Integer(dateStyle), new Integer(timeStyle));
/*  461 */     if (timeZone != null)
/*  462 */       key = new Pair(key, timeZone); 
/*  464 */     if (locale == null)
/*  465 */       locale = Locale.getDefault(); 
/*  467 */     key = new Pair(key, locale);
/*  469 */     FastDateFormat format = (FastDateFormat)cDateTimeInstanceCache.get(key);
/*  470 */     if (format == null)
/*      */       try {
/*  472 */         SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
/*  474 */         String pattern = formatter.toPattern();
/*  475 */         format = getInstance(pattern, timeZone, locale);
/*  476 */         cDateTimeInstanceCache.put(key, format);
/*  478 */       } catch (ClassCastException ex) {
/*  479 */         throw new IllegalArgumentException("No date time pattern for locale: " + locale);
/*      */       }  
/*  482 */     return format;
/*      */   }
/*      */   
/*      */   static synchronized String getTimeZoneDisplay(TimeZone tz, boolean daylight, int style, Locale locale) {
/*  497 */     Object key = new TimeZoneDisplayKey(tz, daylight, style, locale);
/*  498 */     String value = (String)cTimeZoneDisplayCache.get(key);
/*  499 */     if (value == null) {
/*  501 */       value = tz.getDisplayName(daylight, style, locale);
/*  502 */       cTimeZoneDisplayCache.put(key, value);
/*      */     } 
/*  504 */     return value;
/*      */   }
/*      */   
/*      */   private static synchronized String getDefaultPattern() {
/*  513 */     if (cDefaultPattern == null)
/*  514 */       cDefaultPattern = (new SimpleDateFormat()).toPattern(); 
/*  516 */     return cDefaultPattern;
/*      */   }
/*      */   
/*      */   protected FastDateFormat(String pattern, TimeZone timeZone, Locale locale) {
/*  536 */     if (pattern == null)
/*  537 */       throw new IllegalArgumentException("The pattern must not be null"); 
/*  539 */     this.mPattern = pattern;
/*  541 */     this.mTimeZoneForced = (timeZone != null);
/*  542 */     if (timeZone == null)
/*  543 */       timeZone = TimeZone.getDefault(); 
/*  545 */     this.mTimeZone = timeZone;
/*  547 */     this.mLocaleForced = (locale != null);
/*  548 */     if (locale == null)
/*  549 */       locale = Locale.getDefault(); 
/*  551 */     this.mLocale = locale;
/*      */   }
/*      */   
/*      */   protected void init() {
/*  558 */     List rulesList = parsePattern();
/*  559 */     this.mRules = (Rule[])rulesList.toArray((Object[])new Rule[rulesList.size()]);
/*  561 */     int len = 0;
/*  562 */     for (int i = this.mRules.length; --i >= 0;)
/*  563 */       len += this.mRules[i].estimateLength(); 
/*  566 */     this.mMaxLengthEstimate = len;
/*      */   }
/*      */   
/*      */   protected List parsePattern() {
/*  578 */     DateFormatSymbols symbols = new DateFormatSymbols(this.mLocale);
/*  579 */     List rules = new ArrayList();
/*  581 */     String[] ERAs = symbols.getEras();
/*  582 */     String[] months = symbols.getMonths();
/*  583 */     String[] shortMonths = symbols.getShortMonths();
/*  584 */     String[] weekdays = symbols.getWeekdays();
/*  585 */     String[] shortWeekdays = symbols.getShortWeekdays();
/*  586 */     String[] AmPmStrings = symbols.getAmPmStrings();
/*  588 */     int length = this.mPattern.length();
/*  589 */     int[] indexRef = new int[1];
/*  591 */     for (int i = 0; i < length; i++) {
/*      */       Rule rule;
/*      */       String sub;
/*  592 */       indexRef[0] = i;
/*  593 */       String token = parseToken(this.mPattern, indexRef);
/*  594 */       i = indexRef[0];
/*  596 */       int tokenLen = token.length();
/*  597 */       if (tokenLen == 0)
/*      */         break; 
/*  602 */       char c = token.charAt(0);
/*  604 */       switch (c) {
/*      */         case 'G':
/*  606 */           rule = new TextField(0, ERAs);
/*      */           break;
/*      */         case 'y':
/*  609 */           if (tokenLen >= 4) {
/*  610 */             rule = selectNumberRule(1, tokenLen);
/*      */             break;
/*      */           } 
/*  612 */           rule = TwoDigitYearField.INSTANCE;
/*      */           break;
/*      */         case 'M':
/*  616 */           if (tokenLen >= 4) {
/*  617 */             rule = new TextField(2, months);
/*      */             break;
/*      */           } 
/*  618 */           if (tokenLen == 3) {
/*  619 */             rule = new TextField(2, shortMonths);
/*      */             break;
/*      */           } 
/*  620 */           if (tokenLen == 2) {
/*  621 */             rule = TwoDigitMonthField.INSTANCE;
/*      */             break;
/*      */           } 
/*  623 */           rule = UnpaddedMonthField.INSTANCE;
/*      */           break;
/*      */         case 'd':
/*  627 */           rule = selectNumberRule(5, tokenLen);
/*      */           break;
/*      */         case 'h':
/*  630 */           rule = new TwelveHourField(selectNumberRule(10, tokenLen));
/*      */           break;
/*      */         case 'H':
/*  633 */           rule = selectNumberRule(11, tokenLen);
/*      */           break;
/*      */         case 'm':
/*  636 */           rule = selectNumberRule(12, tokenLen);
/*      */           break;
/*      */         case 's':
/*  639 */           rule = selectNumberRule(13, tokenLen);
/*      */           break;
/*      */         case 'S':
/*  642 */           rule = selectNumberRule(14, tokenLen);
/*      */           break;
/*      */         case 'E':
/*  645 */           rule = new TextField(7, (tokenLen < 4) ? shortWeekdays : weekdays);
/*      */           break;
/*      */         case 'D':
/*  648 */           rule = selectNumberRule(6, tokenLen);
/*      */           break;
/*      */         case 'F':
/*  651 */           rule = selectNumberRule(8, tokenLen);
/*      */           break;
/*      */         case 'w':
/*  654 */           rule = selectNumberRule(3, tokenLen);
/*      */           break;
/*      */         case 'W':
/*  657 */           rule = selectNumberRule(4, tokenLen);
/*      */           break;
/*      */         case 'a':
/*  660 */           rule = new TextField(9, AmPmStrings);
/*      */           break;
/*      */         case 'k':
/*  663 */           rule = new TwentyFourHourField(selectNumberRule(11, tokenLen));
/*      */           break;
/*      */         case 'K':
/*  666 */           rule = selectNumberRule(10, tokenLen);
/*      */           break;
/*      */         case 'z':
/*  669 */           if (tokenLen >= 4) {
/*  670 */             rule = new TimeZoneNameRule(this.mTimeZone, this.mTimeZoneForced, this.mLocale, 1);
/*      */             break;
/*      */           } 
/*  672 */           rule = new TimeZoneNameRule(this.mTimeZone, this.mTimeZoneForced, this.mLocale, 0);
/*      */           break;
/*      */         case 'Z':
/*  676 */           if (tokenLen == 1) {
/*  677 */             rule = TimeZoneNumberRule.INSTANCE_NO_COLON;
/*      */             break;
/*      */           } 
/*  679 */           rule = TimeZoneNumberRule.INSTANCE_COLON;
/*      */           break;
/*      */         case '\'':
/*  683 */           sub = token.substring(1);
/*  684 */           if (sub.length() == 1) {
/*  685 */             rule = new CharacterLiteral(sub.charAt(0));
/*      */             break;
/*      */           } 
/*  687 */           rule = new StringLiteral(sub);
/*      */           break;
/*      */         default:
/*  691 */           throw new IllegalArgumentException("Illegal pattern component: " + token);
/*      */       } 
/*  694 */       rules.add(rule);
/*      */     } 
/*  697 */     return rules;
/*      */   }
/*      */   
/*      */   protected String parseToken(String pattern, int[] indexRef) {
/*  708 */     StrBuilder buf = new StrBuilder();
/*  710 */     int i = indexRef[0];
/*  711 */     int length = pattern.length();
/*  713 */     char c = pattern.charAt(i);
/*  714 */     if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
/*  717 */       buf.append(c);
/*  719 */       while (i + 1 < length) {
/*  720 */         char peek = pattern.charAt(i + 1);
/*  721 */         if (peek == c) {
/*  722 */           buf.append(c);
/*  723 */           i++;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  730 */       buf.append('\'');
/*  732 */       boolean inLiteral = false;
/*  734 */       for (; i < length; i++) {
/*  735 */         c = pattern.charAt(i);
/*  737 */         if (c == '\'') {
/*  738 */           if (i + 1 < length && pattern.charAt(i + 1) == '\'') {
/*  740 */             i++;
/*  741 */             buf.append(c);
/*      */           } else {
/*  743 */             inLiteral = !inLiteral;
/*      */           } 
/*      */         } else {
/*  745 */           if (!inLiteral && ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
/*  747 */             i--;
/*      */             break;
/*      */           } 
/*  750 */           buf.append(c);
/*      */         } 
/*      */       } 
/*      */     } 
/*  755 */     indexRef[0] = i;
/*  756 */     return buf.toString();
/*      */   }
/*      */   
/*      */   protected NumberRule selectNumberRule(int field, int padding) {
/*  767 */     switch (padding) {
/*      */       case 1:
/*  769 */         return new UnpaddedNumberField(field);
/*      */       case 2:
/*  771 */         return new TwoDigitNumberField(field);
/*      */     } 
/*  773 */     return new PaddedNumberField(field, padding);
/*      */   }
/*      */   
/*      */   public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
/*  789 */     if (obj instanceof Date)
/*  790 */       return format((Date)obj, toAppendTo); 
/*  791 */     if (obj instanceof Calendar)
/*  792 */       return format((Calendar)obj, toAppendTo); 
/*  793 */     if (obj instanceof Long)
/*  794 */       return format(((Long)obj).longValue(), toAppendTo); 
/*  796 */     throw new IllegalArgumentException("Unknown class: " + ((obj == null) ? "<null>" : obj.getClass().getName()));
/*      */   }
/*      */   
/*      */   public String format(long millis) {
/*  809 */     return format(new Date(millis));
/*      */   }
/*      */   
/*      */   public String format(Date date) {
/*  819 */     Calendar c = new GregorianCalendar(this.mTimeZone, this.mLocale);
/*  820 */     c.setTime(date);
/*  821 */     return applyRules(c, new StringBuffer(this.mMaxLengthEstimate)).toString();
/*      */   }
/*      */   
/*      */   public String format(Calendar calendar) {
/*  831 */     return format(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
/*      */   }
/*      */   
/*      */   public StringBuffer format(long millis, StringBuffer buf) {
/*  844 */     return format(new Date(millis), buf);
/*      */   }
/*      */   
/*      */   public StringBuffer format(Date date, StringBuffer buf) {
/*  856 */     Calendar c = new GregorianCalendar(this.mTimeZone);
/*  857 */     c.setTime(date);
/*  858 */     return applyRules(c, buf);
/*      */   }
/*      */   
/*      */   public StringBuffer format(Calendar calendar, StringBuffer buf) {
/*  870 */     if (this.mTimeZoneForced) {
/*  871 */       calendar.getTime();
/*  872 */       calendar = (Calendar)calendar.clone();
/*  873 */       calendar.setTimeZone(this.mTimeZone);
/*      */     } 
/*  875 */     return applyRules(calendar, buf);
/*      */   }
/*      */   
/*      */   protected StringBuffer applyRules(Calendar calendar, StringBuffer buf) {
/*  887 */     Rule[] rules = this.mRules;
/*  888 */     int len = this.mRules.length;
/*  889 */     for (int i = 0; i < len; i++)
/*  890 */       rules[i].appendTo(buf, calendar); 
/*  892 */     return buf;
/*      */   }
/*      */   
/*      */   public Object parseObject(String source, ParsePosition pos) {
/*  905 */     pos.setIndex(0);
/*  906 */     pos.setErrorIndex(0);
/*  907 */     return null;
/*      */   }
/*      */   
/*      */   public String getPattern() {
/*  918 */     return this.mPattern;
/*      */   }
/*      */   
/*      */   public TimeZone getTimeZone() {
/*  932 */     return this.mTimeZone;
/*      */   }
/*      */   
/*      */   public boolean getTimeZoneOverridesCalendar() {
/*  943 */     return this.mTimeZoneForced;
/*      */   }
/*      */   
/*      */   public Locale getLocale() {
/*  952 */     return this.mLocale;
/*      */   }
/*      */   
/*      */   public int getMaxLengthEstimate() {
/*  965 */     return this.mMaxLengthEstimate;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/*  977 */     if (!(obj instanceof FastDateFormat))
/*  978 */       return false; 
/*  980 */     FastDateFormat other = (FastDateFormat)obj;
/*  981 */     if ((this.mPattern == other.mPattern || this.mPattern.equals(other.mPattern)) && (this.mTimeZone == other.mTimeZone || this.mTimeZone.equals(other.mTimeZone)) && (this.mLocale == other.mLocale || this.mLocale.equals(other.mLocale)) && this.mTimeZoneForced == other.mTimeZoneForced && this.mLocaleForced == other.mLocaleForced)
/*  988 */       return true; 
/*  990 */     return false;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  999 */     int total = 0;
/* 1000 */     total += this.mPattern.hashCode();
/* 1001 */     total += this.mTimeZone.hashCode();
/* 1002 */     total += this.mTimeZoneForced ? 1 : 0;
/* 1003 */     total += this.mLocale.hashCode();
/* 1004 */     total += this.mLocaleForced ? 1 : 0;
/* 1005 */     return total;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1014 */     return "FastDateFormat[" + this.mPattern + "]";
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1028 */     in.defaultReadObject();
/* 1029 */     init();
/*      */   }
/*      */   
/*      */   private static interface Rule {
/*      */     int estimateLength();
/*      */     
/*      */     void appendTo(StringBuffer param1StringBuffer, Calendar param1Calendar);
/*      */   }
/*      */   
/*      */   private static interface NumberRule extends Rule {
/*      */     void appendTo(StringBuffer param1StringBuffer, int param1Int);
/*      */   }
/*      */   
/*      */   private static class CharacterLiteral implements Rule {
/*      */     private final char mValue;
/*      */     
/*      */     CharacterLiteral(char value) {
/* 1080 */       this.mValue = value;
/*      */     }
/*      */     
/*      */     public int estimateLength() {
/* 1087 */       return 1;
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1094 */       buffer.append(this.mValue);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class StringLiteral implements Rule {
/*      */     private final String mValue;
/*      */     
/*      */     StringLiteral(String value) {
/* 1111 */       this.mValue = value;
/*      */     }
/*      */     
/*      */     public int estimateLength() {
/* 1118 */       return this.mValue.length();
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1125 */       buffer.append(this.mValue);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TextField implements Rule {
/*      */     private final int mField;
/*      */     
/*      */     private final String[] mValues;
/*      */     
/*      */     TextField(int field, String[] values) {
/* 1144 */       this.mField = field;
/* 1145 */       this.mValues = values;
/*      */     }
/*      */     
/*      */     public int estimateLength() {
/* 1152 */       int max = 0;
/* 1153 */       for (int i = this.mValues.length; --i >= 0; ) {
/* 1154 */         int len = this.mValues[i].length();
/* 1155 */         if (len > max)
/* 1156 */           max = len; 
/*      */       } 
/* 1159 */       return max;
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1166 */       buffer.append(this.mValues[calendar.get(this.mField)]);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class UnpaddedNumberField implements NumberRule {
/*      */     private final int mField;
/*      */     
/*      */     UnpaddedNumberField(int field) {
/* 1182 */       this.mField = field;
/*      */     }
/*      */     
/*      */     public int estimateLength() {
/* 1189 */       return 4;
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1196 */       appendTo(buffer, calendar.get(this.mField));
/*      */     }
/*      */     
/*      */     public final void appendTo(StringBuffer buffer, int value) {
/* 1203 */       if (value < 10) {
/* 1204 */         buffer.append((char)(value + 48));
/* 1205 */       } else if (value < 100) {
/* 1206 */         buffer.append((char)(value / 10 + 48));
/* 1207 */         buffer.append((char)(value % 10 + 48));
/*      */       } else {
/* 1209 */         buffer.append(Integer.toString(value));
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class UnpaddedMonthField implements NumberRule {
/* 1218 */     static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();
/*      */     
/*      */     public int estimateLength() {
/* 1232 */       return 2;
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1239 */       appendTo(buffer, calendar.get(2) + 1);
/*      */     }
/*      */     
/*      */     public final void appendTo(StringBuffer buffer, int value) {
/* 1246 */       if (value < 10) {
/* 1247 */         buffer.append((char)(value + 48));
/*      */       } else {
/* 1249 */         buffer.append((char)(value / 10 + 48));
/* 1250 */         buffer.append((char)(value % 10 + 48));
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class PaddedNumberField implements NumberRule {
/*      */     private final int mField;
/*      */     
/*      */     private final int mSize;
/*      */     
/*      */     PaddedNumberField(int field, int size) {
/* 1269 */       if (size < 3)
/* 1271 */         throw new IllegalArgumentException(); 
/* 1273 */       this.mField = field;
/* 1274 */       this.mSize = size;
/*      */     }
/*      */     
/*      */     public int estimateLength() {
/* 1281 */       return 4;
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1288 */       appendTo(buffer, calendar.get(this.mField));
/*      */     }
/*      */     
/*      */     public final void appendTo(StringBuffer buffer, int value) {
/* 1295 */       if (value < 100) {
/* 1296 */         for (int i = this.mSize; --i >= 2;)
/* 1297 */           buffer.append('0'); 
/* 1299 */         buffer.append((char)(value / 10 + 48));
/* 1300 */         buffer.append((char)(value % 10 + 48));
/*      */       } else {
/*      */         int digits;
/* 1303 */         if (value < 1000) {
/* 1304 */           digits = 3;
/*      */         } else {
/* 1306 */           Validate.isTrue((value > -1), "Negative values should not be possible", value);
/* 1307 */           digits = Integer.toString(value).length();
/*      */         } 
/* 1309 */         for (int i = this.mSize; --i >= digits;)
/* 1310 */           buffer.append('0'); 
/* 1312 */         buffer.append(Integer.toString(value));
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TwoDigitNumberField implements NumberRule {
/*      */     private final int mField;
/*      */     
/*      */     TwoDigitNumberField(int field) {
/* 1329 */       this.mField = field;
/*      */     }
/*      */     
/*      */     public int estimateLength() {
/* 1336 */       return 2;
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1343 */       appendTo(buffer, calendar.get(this.mField));
/*      */     }
/*      */     
/*      */     public final void appendTo(StringBuffer buffer, int value) {
/* 1350 */       if (value < 100) {
/* 1351 */         buffer.append((char)(value / 10 + 48));
/* 1352 */         buffer.append((char)(value % 10 + 48));
/*      */       } else {
/* 1354 */         buffer.append(Integer.toString(value));
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TwoDigitYearField implements NumberRule {
/* 1363 */     static final TwoDigitYearField INSTANCE = new TwoDigitYearField();
/*      */     
/*      */     public int estimateLength() {
/* 1376 */       return 2;
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1383 */       appendTo(buffer, calendar.get(1) % 100);
/*      */     }
/*      */     
/*      */     public final void appendTo(StringBuffer buffer, int value) {
/* 1390 */       buffer.append((char)(value / 10 + 48));
/* 1391 */       buffer.append((char)(value % 10 + 48));
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TwoDigitMonthField implements NumberRule {
/* 1399 */     static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();
/*      */     
/*      */     public int estimateLength() {
/* 1412 */       return 2;
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1419 */       appendTo(buffer, calendar.get(2) + 1);
/*      */     }
/*      */     
/*      */     public final void appendTo(StringBuffer buffer, int value) {
/* 1426 */       buffer.append((char)(value / 10 + 48));
/* 1427 */       buffer.append((char)(value % 10 + 48));
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TwelveHourField implements NumberRule {
/*      */     private final FastDateFormat.NumberRule mRule;
/*      */     
/*      */     TwelveHourField(FastDateFormat.NumberRule rule) {
/* 1444 */       this.mRule = rule;
/*      */     }
/*      */     
/*      */     public int estimateLength() {
/* 1451 */       return this.mRule.estimateLength();
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1458 */       int value = calendar.get(10);
/* 1459 */       if (value == 0)
/* 1460 */         value = calendar.getLeastMaximum(10) + 1; 
/* 1462 */       this.mRule.appendTo(buffer, value);
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, int value) {
/* 1469 */       this.mRule.appendTo(buffer, value);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TwentyFourHourField implements NumberRule {
/*      */     private final FastDateFormat.NumberRule mRule;
/*      */     
/*      */     TwentyFourHourField(FastDateFormat.NumberRule rule) {
/* 1486 */       this.mRule = rule;
/*      */     }
/*      */     
/*      */     public int estimateLength() {
/* 1493 */       return this.mRule.estimateLength();
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1500 */       int value = calendar.get(11);
/* 1501 */       if (value == 0)
/* 1502 */         value = calendar.getMaximum(11) + 1; 
/* 1504 */       this.mRule.appendTo(buffer, value);
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, int value) {
/* 1511 */       this.mRule.appendTo(buffer, value);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TimeZoneNameRule implements Rule {
/*      */     private final TimeZone mTimeZone;
/*      */     
/*      */     private final boolean mTimeZoneForced;
/*      */     
/*      */     private final Locale mLocale;
/*      */     
/*      */     private final int mStyle;
/*      */     
/*      */     private final String mStandard;
/*      */     
/*      */     private final String mDaylight;
/*      */     
/*      */     TimeZoneNameRule(TimeZone timeZone, boolean timeZoneForced, Locale locale, int style) {
/* 1535 */       this.mTimeZone = timeZone;
/* 1536 */       this.mTimeZoneForced = timeZoneForced;
/* 1537 */       this.mLocale = locale;
/* 1538 */       this.mStyle = style;
/* 1540 */       if (timeZoneForced) {
/* 1541 */         this.mStandard = FastDateFormat.getTimeZoneDisplay(timeZone, false, style, locale);
/* 1542 */         this.mDaylight = FastDateFormat.getTimeZoneDisplay(timeZone, true, style, locale);
/*      */       } else {
/* 1544 */         this.mStandard = null;
/* 1545 */         this.mDaylight = null;
/*      */       } 
/*      */     }
/*      */     
/*      */     public int estimateLength() {
/* 1553 */       if (this.mTimeZoneForced)
/* 1554 */         return Math.max(this.mStandard.length(), this.mDaylight.length()); 
/* 1555 */       if (this.mStyle == 0)
/* 1556 */         return 4; 
/* 1558 */       return 40;
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1566 */       if (this.mTimeZoneForced) {
/* 1567 */         if (this.mTimeZone.useDaylightTime() && calendar.get(16) != 0) {
/* 1568 */           buffer.append(this.mDaylight);
/*      */         } else {
/* 1570 */           buffer.append(this.mStandard);
/*      */         } 
/*      */       } else {
/* 1573 */         TimeZone timeZone = calendar.getTimeZone();
/* 1574 */         if (timeZone.useDaylightTime() && calendar.get(16) != 0) {
/* 1575 */           buffer.append(FastDateFormat.getTimeZoneDisplay(timeZone, true, this.mStyle, this.mLocale));
/*      */         } else {
/* 1577 */           buffer.append(FastDateFormat.getTimeZoneDisplay(timeZone, false, this.mStyle, this.mLocale));
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TimeZoneNumberRule implements Rule {
/* 1588 */     static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
/*      */     
/* 1589 */     static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
/*      */     
/*      */     final boolean mColon;
/*      */     
/*      */     TimeZoneNumberRule(boolean colon) {
/* 1599 */       this.mColon = colon;
/*      */     }
/*      */     
/*      */     public int estimateLength() {
/* 1606 */       return 5;
/*      */     }
/*      */     
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar) {
/* 1613 */       int offset = calendar.get(15) + calendar.get(16);
/* 1615 */       if (offset < 0) {
/* 1616 */         buffer.append('-');
/* 1617 */         offset = -offset;
/*      */       } else {
/* 1619 */         buffer.append('+');
/*      */       } 
/* 1622 */       int hours = offset / 3600000;
/* 1623 */       buffer.append((char)(hours / 10 + 48));
/* 1624 */       buffer.append((char)(hours % 10 + 48));
/* 1626 */       if (this.mColon)
/* 1627 */         buffer.append(':'); 
/* 1630 */       int minutes = offset / 60000 - 60 * hours;
/* 1631 */       buffer.append((char)(minutes / 10 + 48));
/* 1632 */       buffer.append((char)(minutes % 10 + 48));
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TimeZoneDisplayKey {
/*      */     private final TimeZone mTimeZone;
/*      */     
/*      */     private final int mStyle;
/*      */     
/*      */     private final Locale mLocale;
/*      */     
/*      */     TimeZoneDisplayKey(TimeZone timeZone, boolean daylight, int style, Locale locale) {
/* 1655 */       this.mTimeZone = timeZone;
/* 1656 */       if (daylight)
/* 1657 */         style |= Integer.MIN_VALUE; 
/* 1659 */       this.mStyle = style;
/* 1660 */       this.mLocale = locale;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1667 */       return this.mStyle * 31 + this.mLocale.hashCode();
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1674 */       if (this == obj)
/* 1675 */         return true; 
/* 1677 */       if (obj instanceof TimeZoneDisplayKey) {
/* 1678 */         TimeZoneDisplayKey other = (TimeZoneDisplayKey)obj;
/* 1679 */         return (this.mTimeZone.equals(other.mTimeZone) && this.mStyle == other.mStyle && this.mLocale.equals(other.mLocale));
/*      */       } 
/* 1684 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class Pair {
/*      */     private final Object mObj1;
/*      */     
/*      */     private final Object mObj2;
/*      */     
/*      */     public Pair(Object obj1, Object obj2) {
/* 1705 */       this.mObj1 = obj1;
/* 1706 */       this.mObj2 = obj2;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1713 */       if (this == obj)
/* 1714 */         return true; 
/* 1717 */       if (!(obj instanceof Pair))
/* 1718 */         return false; 
/* 1721 */       Pair key = (Pair)obj;
/* 1723 */       if ((this.mObj1 == null) ? (key.mObj1 == null) : this.mObj1.equals(key.mObj1))
/* 1723 */         if ((this.mObj2 == null) ? (key.mObj2 == null) : this.mObj2.equals(key.mObj2)); 
/* 1723 */       return false;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1734 */       return ((this.mObj1 == null) ? 0 : this.mObj1.hashCode()) + ((this.mObj2 == null) ? 0 : this.mObj2.hashCode());
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1743 */       return "[" + this.mObj1 + ':' + this.mObj2 + ']';
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\time\FastDateFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */