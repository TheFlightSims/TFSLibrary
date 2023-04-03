/*      */ package org.jfree.date;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.text.DateFormatSymbols;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ 
/*      */ public abstract class SerialDate implements Comparable, Serializable, MonthConstants {
/*      */   private static final long serialVersionUID = -293716040467423637L;
/*      */   
/*   95 */   public static final DateFormatSymbols DATE_FORMAT_SYMBOLS = (new SimpleDateFormat()).getDateFormatSymbols();
/*      */   
/*      */   public static final int SERIAL_LOWER_BOUND = 2;
/*      */   
/*      */   public static final int SERIAL_UPPER_BOUND = 2958465;
/*      */   
/*      */   public static final int MINIMUM_YEAR_SUPPORTED = 1900;
/*      */   
/*      */   public static final int MAXIMUM_YEAR_SUPPORTED = 9999;
/*      */   
/*      */   public static final int MONDAY = 2;
/*      */   
/*      */   public static final int TUESDAY = 3;
/*      */   
/*      */   public static final int WEDNESDAY = 4;
/*      */   
/*      */   public static final int THURSDAY = 5;
/*      */   
/*      */   public static final int FRIDAY = 6;
/*      */   
/*      */   public static final int SATURDAY = 7;
/*      */   
/*      */   public static final int SUNDAY = 1;
/*      */   
/*  140 */   static final int[] LAST_DAY_OF_MONTH = new int[] { 
/*  140 */       0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 
/*  140 */       31, 30, 31 };
/*      */   
/*  144 */   static final int[] AGGREGATE_DAYS_TO_END_OF_MONTH = new int[] { 
/*  144 */       0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 
/*  144 */       304, 334, 365 };
/*      */   
/*  148 */   static final int[] AGGREGATE_DAYS_TO_END_OF_PRECEDING_MONTH = new int[] { 
/*  148 */       0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 
/*  148 */       273, 304, 334, 365 };
/*      */   
/*  152 */   static final int[] LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_MONTH = new int[] { 
/*  152 */       0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 
/*  152 */       305, 335, 366 };
/*      */   
/*  159 */   static final int[] LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_PRECEDING_MONTH = new int[] { 
/*  159 */       0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 
/*  159 */       274, 305, 335, 366 };
/*      */   
/*      */   public static final int FIRST_WEEK_IN_MONTH = 1;
/*      */   
/*      */   public static final int SECOND_WEEK_IN_MONTH = 2;
/*      */   
/*      */   public static final int THIRD_WEEK_IN_MONTH = 3;
/*      */   
/*      */   public static final int FOURTH_WEEK_IN_MONTH = 4;
/*      */   
/*      */   public static final int LAST_WEEK_IN_MONTH = 0;
/*      */   
/*      */   public static final int INCLUDE_NONE = 0;
/*      */   
/*      */   public static final int INCLUDE_FIRST = 1;
/*      */   
/*      */   public static final int INCLUDE_SECOND = 2;
/*      */   
/*      */   public static final int INCLUDE_BOTH = 3;
/*      */   
/*      */   public static final int PRECEDING = -1;
/*      */   
/*      */   public static final int NEAREST = 0;
/*      */   
/*      */   public static final int FOLLOWING = 1;
/*      */   
/*      */   private String description;
/*      */   
/*      */   public static boolean isValidWeekdayCode(int code) {
/*  227 */     switch (code) {
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*  235 */         return true;
/*      */     } 
/*  237 */     return false;
/*      */   }
/*      */   
/*      */   public static int stringToWeekdayCode(String s) {
/*  252 */     String[] shortWeekdayNames = DATE_FORMAT_SYMBOLS.getShortWeekdays();
/*  254 */     String[] weekDayNames = DATE_FORMAT_SYMBOLS.getWeekdays();
/*  256 */     int result = -1;
/*  257 */     s = s.trim();
/*  258 */     for (int i = 0; i < weekDayNames.length; i++) {
/*  259 */       if (s.equals(shortWeekdayNames[i])) {
/*  260 */         result = i;
/*      */         break;
/*      */       } 
/*  263 */       if (s.equals(weekDayNames[i])) {
/*  264 */         result = i;
/*      */         break;
/*      */       } 
/*      */     } 
/*  268 */     return result;
/*      */   }
/*      */   
/*      */   public static String weekdayCodeToString(int weekday) {
/*  283 */     String[] weekdays = DATE_FORMAT_SYMBOLS.getWeekdays();
/*  284 */     return weekdays[weekday];
/*      */   }
/*      */   
/*      */   public static String[] getMonths() {
/*  295 */     return getMonths(false);
/*      */   }
/*      */   
/*      */   public static String[] getMonths(boolean shortened) {
/*  309 */     if (shortened)
/*  310 */       return DATE_FORMAT_SYMBOLS.getShortMonths(); 
/*  313 */     return DATE_FORMAT_SYMBOLS.getMonths();
/*      */   }
/*      */   
/*      */   public static boolean isValidMonthCode(int code) {
/*  328 */     switch (code) {
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*      */       case 9:
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*  341 */         return true;
/*      */     } 
/*  343 */     return false;
/*      */   }
/*      */   
/*      */   public static int monthCodeToQuarter(int code) {
/*  357 */     switch (code) {
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*  360 */         return 1;
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*  363 */         return 2;
/*      */       case 7:
/*      */       case 8:
/*      */       case 9:
/*  366 */         return 3;
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*  369 */         return 4;
/*      */     } 
/*  370 */     throw new IllegalArgumentException("SerialDate.monthCodeToQuarter: invalid month code.");
/*      */   }
/*      */   
/*      */   public static String monthCodeToString(int month) {
/*  388 */     return monthCodeToString(month, false);
/*      */   }
/*      */   
/*      */   public static String monthCodeToString(int month, boolean shortened) {
/*      */     String[] months;
/*  408 */     if (!isValidMonthCode(month))
/*  409 */       throw new IllegalArgumentException("SerialDate.monthCodeToString: month outside valid range."); 
/*  415 */     if (shortened) {
/*  416 */       months = DATE_FORMAT_SYMBOLS.getShortMonths();
/*      */     } else {
/*  419 */       months = DATE_FORMAT_SYMBOLS.getMonths();
/*      */     } 
/*  422 */     return months[month - 1];
/*      */   }
/*      */   
/*      */   public static int stringToMonthCode(String s) {
/*  440 */     String[] shortMonthNames = DATE_FORMAT_SYMBOLS.getShortMonths();
/*  441 */     String[] monthNames = DATE_FORMAT_SYMBOLS.getMonths();
/*  443 */     int result = -1;
/*  444 */     s = s.trim();
/*      */     try {
/*  448 */       result = Integer.parseInt(s);
/*  450 */     } catch (NumberFormatException e) {}
/*  455 */     if (result < 1 || result > 12)
/*  456 */       for (int i = 0; i < monthNames.length; i++) {
/*  457 */         if (s.equals(shortMonthNames[i])) {
/*  458 */           result = i + 1;
/*      */           break;
/*      */         } 
/*  461 */         if (s.equals(monthNames[i])) {
/*  462 */           result = i + 1;
/*      */           break;
/*      */         } 
/*      */       }  
/*  468 */     return result;
/*      */   }
/*      */   
/*      */   public static boolean isValidWeekInMonthCode(int code) {
/*  482 */     switch (code) {
/*      */       case 0:
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*  487 */         return true;
/*      */     } 
/*  488 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isLeapYear(int yyyy) {
/*  502 */     if (yyyy % 4 != 0)
/*  503 */       return false; 
/*  505 */     if (yyyy % 400 == 0)
/*  506 */       return true; 
/*  508 */     if (yyyy % 100 == 0)
/*  509 */       return false; 
/*  512 */     return true;
/*      */   }
/*      */   
/*      */   public static int leapYearCount(int yyyy) {
/*  529 */     int leap4 = (yyyy - 1896) / 4;
/*  530 */     int leap100 = (yyyy - 1800) / 100;
/*  531 */     int leap400 = (yyyy - 1600) / 400;
/*  532 */     return leap4 - leap100 + leap400;
/*      */   }
/*      */   
/*      */   public static int lastDayOfMonth(int month, int yyyy) {
/*  547 */     int result = LAST_DAY_OF_MONTH[month];
/*  548 */     if (month != 2)
/*  549 */       return result; 
/*  551 */     if (isLeapYear(yyyy))
/*  552 */       return result + 1; 
/*  555 */     return result;
/*      */   }
/*      */   
/*      */   public static SerialDate addDays(int days, SerialDate base) {
/*  571 */     int serialDayNumber = base.toSerial() + days;
/*  572 */     return createInstance(serialDayNumber);
/*      */   }
/*      */   
/*      */   public static SerialDate addMonths(int months, SerialDate base) {
/*  591 */     int yy = (12 * base.getYYYY() + base.getMonth() + months - 1) / 12;
/*  593 */     int mm = (12 * base.getYYYY() + base.getMonth() + months - 1) % 12 + 1;
/*  595 */     int dd = Math.min(base.getDayOfMonth(), lastDayOfMonth(mm, yy));
/*  598 */     return createInstance(dd, mm, yy);
/*      */   }
/*      */   
/*      */   public static SerialDate addYears(int years, SerialDate base) {
/*  613 */     int baseY = base.getYYYY();
/*  614 */     int baseM = base.getMonth();
/*  615 */     int baseD = base.getDayOfMonth();
/*  617 */     int targetY = baseY + years;
/*  618 */     int targetD = Math.min(baseD, lastDayOfMonth(baseM, targetY));
/*  622 */     return createInstance(targetD, baseM, targetY);
/*      */   }
/*      */   
/*      */   public static SerialDate getPreviousDayOfWeek(int targetWeekday, SerialDate base) {
/*      */     int adjust;
/*  640 */     if (!isValidWeekdayCode(targetWeekday))
/*  641 */       throw new IllegalArgumentException("Invalid day-of-the-week code."); 
/*  648 */     int baseDOW = base.getDayOfWeek();
/*  649 */     if (baseDOW > targetWeekday) {
/*  650 */       adjust = Math.min(0, targetWeekday - baseDOW);
/*      */     } else {
/*  653 */       adjust = -7 + Math.max(0, targetWeekday - baseDOW);
/*      */     } 
/*  656 */     return addDays(adjust, base);
/*      */   }
/*      */   
/*      */   public static SerialDate getFollowingDayOfWeek(int targetWeekday, SerialDate base) {
/*      */     int adjust;
/*  674 */     if (!isValidWeekdayCode(targetWeekday))
/*  675 */       throw new IllegalArgumentException("Invalid day-of-the-week code."); 
/*  682 */     int baseDOW = base.getDayOfWeek();
/*  683 */     if (baseDOW > targetWeekday) {
/*  684 */       adjust = 7 + Math.min(0, targetWeekday - baseDOW);
/*      */     } else {
/*  687 */       adjust = Math.max(0, targetWeekday - baseDOW);
/*      */     } 
/*  690 */     return addDays(adjust, base);
/*      */   }
/*      */   
/*      */   public static SerialDate getNearestDayOfWeek(int targetDOW, SerialDate base) {
/*  707 */     if (!isValidWeekdayCode(targetDOW))
/*  708 */       throw new IllegalArgumentException("Invalid day-of-the-week code."); 
/*  714 */     int baseDOW = base.getDayOfWeek();
/*  715 */     int adjust = -Math.abs(targetDOW - baseDOW);
/*  716 */     if (adjust >= 4)
/*  717 */       adjust = 7 - adjust; 
/*  719 */     if (adjust <= -4)
/*  720 */       adjust = 7 + adjust; 
/*  722 */     return addDays(adjust, base);
/*      */   }
/*      */   
/*      */   public SerialDate getEndOfCurrentMonth(SerialDate base) {
/*  734 */     int last = lastDayOfMonth(base.getMonth(), base.getYYYY());
/*  737 */     return createInstance(last, base.getMonth(), base.getYYYY());
/*      */   }
/*      */   
/*      */   public static String weekInMonthToString(int count) {
/*  751 */     switch (count) {
/*      */       case 1:
/*  752 */         return "First";
/*      */       case 2:
/*  753 */         return "Second";
/*      */       case 3:
/*  754 */         return "Third";
/*      */       case 4:
/*  755 */         return "Fourth";
/*      */       case 0:
/*  756 */         return "Last";
/*      */     } 
/*  758 */     return "SerialDate.weekInMonthToString(): invalid code.";
/*      */   }
/*      */   
/*      */   public static String relativeToString(int relative) {
/*  774 */     switch (relative) {
/*      */       case -1:
/*  775 */         return "Preceding";
/*      */       case 0:
/*  776 */         return "Nearest";
/*      */       case 1:
/*  777 */         return "Following";
/*      */     } 
/*  778 */     return "ERROR : Relative To String";
/*      */   }
/*      */   
/*      */   public static SerialDate createInstance(int day, int month, int yyyy) {
/*  795 */     return new SpreadsheetDate(day, month, yyyy);
/*      */   }
/*      */   
/*      */   public static SerialDate createInstance(int serial) {
/*  807 */     return new SpreadsheetDate(serial);
/*      */   }
/*      */   
/*      */   public static SerialDate createInstance(Date date) {
/*  819 */     GregorianCalendar calendar = new GregorianCalendar();
/*  820 */     calendar.setTime(date);
/*  821 */     return new SpreadsheetDate(calendar.get(5), calendar.get(2) + 1, calendar.get(1));
/*      */   }
/*      */   
/*      */   public abstract int toSerial();
/*      */   
/*      */   public abstract Date toDate();
/*      */   
/*      */   public String getDescription() {
/*  850 */     return this.description;
/*      */   }
/*      */   
/*      */   public void setDescription(String description) {
/*  859 */     this.description = description;
/*      */   }
/*      */   
/*      */   public String toString() {
/*  868 */     return getDayOfMonth() + "-" + monthCodeToString(getMonth()) + "-" + getYYYY();
/*      */   }
/*      */   
/*      */   public abstract int getYYYY();
/*      */   
/*      */   public abstract int getMonth();
/*      */   
/*      */   public abstract int getDayOfMonth();
/*      */   
/*      */   public abstract int getDayOfWeek();
/*      */   
/*      */   public abstract int compare(SerialDate paramSerialDate);
/*      */   
/*      */   public abstract boolean isOn(SerialDate paramSerialDate);
/*      */   
/*      */   public abstract boolean isBefore(SerialDate paramSerialDate);
/*      */   
/*      */   public abstract boolean isOnOrBefore(SerialDate paramSerialDate);
/*      */   
/*      */   public abstract boolean isAfter(SerialDate paramSerialDate);
/*      */   
/*      */   public abstract boolean isOnOrAfter(SerialDate paramSerialDate);
/*      */   
/*      */   public abstract boolean isInRange(SerialDate paramSerialDate1, SerialDate paramSerialDate2);
/*      */   
/*      */   public abstract boolean isInRange(SerialDate paramSerialDate1, SerialDate paramSerialDate2, int paramInt);
/*      */   
/*      */   public SerialDate getPreviousDayOfWeek(int targetDOW) {
/* 1005 */     return getPreviousDayOfWeek(targetDOW, this);
/*      */   }
/*      */   
/*      */   public SerialDate getFollowingDayOfWeek(int targetDOW) {
/* 1018 */     return getFollowingDayOfWeek(targetDOW, this);
/*      */   }
/*      */   
/*      */   public SerialDate getNearestDayOfWeek(int targetDOW) {
/* 1029 */     return getNearestDayOfWeek(targetDOW, this);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\date\SerialDate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */