/*     */ package org.jfree.date;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class SpreadsheetDate extends SerialDate {
/*     */   private static final long serialVersionUID = -2039586705374454461L;
/*     */   
/*     */   private int serial;
/*     */   
/*     */   private int day;
/*     */   
/*     */   private int month;
/*     */   
/*     */   private int year;
/*     */   
/*     */   private String description;
/*     */   
/*     */   public SpreadsheetDate(int day, int month, int year) {
/* 112 */     if (year >= 1900 && year <= 9999) {
/* 113 */       this.year = year;
/*     */     } else {
/* 116 */       throw new IllegalArgumentException("The 'year' argument must be in range 1900 to 9999.");
/*     */     } 
/* 121 */     if (month >= 1 && month <= 12) {
/* 123 */       this.month = month;
/*     */     } else {
/* 126 */       throw new IllegalArgumentException("The 'month' argument must be in the range 1 to 12.");
/*     */     } 
/* 131 */     if (day >= 1 && day <= SerialDate.lastDayOfMonth(month, year)) {
/* 132 */       this.day = day;
/*     */     } else {
/* 135 */       throw new IllegalArgumentException("Invalid 'day' argument.");
/*     */     } 
/* 139 */     this.serial = calcSerial(day, month, year);
/* 141 */     this.description = null;
/*     */   }
/*     */   
/*     */   public SpreadsheetDate(int serial) {
/* 153 */     if (serial >= 2 && serial <= 2958465) {
/* 154 */       this.serial = serial;
/*     */     } else {
/* 157 */       throw new IllegalArgumentException("SpreadsheetDate: Serial must be in range 2 to 2958465.");
/*     */     } 
/* 162 */     calcDayMonthYear();
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 174 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/* 184 */     this.description = description;
/*     */   }
/*     */   
/*     */   public int toSerial() {
/* 195 */     return this.serial;
/*     */   }
/*     */   
/*     */   public Date toDate() {
/* 204 */     Calendar calendar = Calendar.getInstance();
/* 205 */     calendar.set(getYYYY(), getMonth() - 1, getDayOfMonth(), 0, 0, 0);
/* 206 */     return calendar.getTime();
/*     */   }
/*     */   
/*     */   public int getYYYY() {
/* 215 */     return this.year;
/*     */   }
/*     */   
/*     */   public int getMonth() {
/* 224 */     return this.month;
/*     */   }
/*     */   
/*     */   public int getDayOfMonth() {
/* 233 */     return this.day;
/*     */   }
/*     */   
/*     */   public int getDayOfWeek() {
/* 247 */     return (this.serial + 6) % 7 + 1;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 263 */     if (object instanceof SerialDate) {
/* 264 */       SerialDate s = (SerialDate)object;
/* 265 */       return (s.toSerial() == toSerial());
/*     */     } 
/* 268 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 279 */     return toSerial();
/*     */   }
/*     */   
/*     */   public int compare(SerialDate other) {
/* 292 */     return this.serial - other.toSerial();
/*     */   }
/*     */   
/*     */   public int compareTo(Object other) {
/* 304 */     return compare((SerialDate)other);
/*     */   }
/*     */   
/*     */   public boolean isOn(SerialDate other) {
/* 317 */     return (this.serial == other.toSerial());
/*     */   }
/*     */   
/*     */   public boolean isBefore(SerialDate other) {
/* 330 */     return (this.serial < other.toSerial());
/*     */   }
/*     */   
/*     */   public boolean isOnOrBefore(SerialDate other) {
/* 343 */     return (this.serial <= other.toSerial());
/*     */   }
/*     */   
/*     */   public boolean isAfter(SerialDate other) {
/* 356 */     return (this.serial > other.toSerial());
/*     */   }
/*     */   
/*     */   public boolean isOnOrAfter(SerialDate other) {
/* 369 */     return (this.serial >= other.toSerial());
/*     */   }
/*     */   
/*     */   public boolean isInRange(SerialDate d1, SerialDate d2) {
/* 383 */     return isInRange(d1, d2, 3);
/*     */   }
/*     */   
/*     */   public boolean isInRange(SerialDate d1, SerialDate d2, int include) {
/* 401 */     int s1 = d1.toSerial();
/* 402 */     int s2 = d2.toSerial();
/* 403 */     int start = Math.min(s1, s2);
/* 404 */     int end = Math.max(s1, s2);
/* 406 */     int s = toSerial();
/* 407 */     if (include == 3)
/* 408 */       return (s >= start && s <= end); 
/* 410 */     if (include == 1)
/* 411 */       return (s >= start && s < end); 
/* 413 */     if (include == 2)
/* 414 */       return (s > start && s <= end); 
/* 417 */     return (s > start && s < end);
/*     */   }
/*     */   
/*     */   private int calcSerial(int d, int m, int y) {
/* 433 */     int yy = (y - 1900) * 365 + SerialDate.leapYearCount(y - 1);
/* 434 */     int mm = SerialDate.AGGREGATE_DAYS_TO_END_OF_PRECEDING_MONTH[m];
/* 435 */     if (m > 2 && 
/* 436 */       SerialDate.isLeapYear(y))
/* 437 */       mm++; 
/* 440 */     int dd = d;
/* 441 */     return yy + mm + dd + 1;
/*     */   }
/*     */   
/*     */   private void calcDayMonthYear() {
/* 450 */     int days = this.serial - 2;
/* 452 */     int overestimatedYYYY = 1900 + days / 365;
/* 453 */     int leaps = SerialDate.leapYearCount(overestimatedYYYY);
/* 454 */     int nonleapdays = days - leaps;
/* 456 */     int underestimatedYYYY = 1900 + nonleapdays / 365;
/* 458 */     if (underestimatedYYYY == overestimatedYYYY) {
/* 459 */       this.year = underestimatedYYYY;
/*     */     } else {
/* 462 */       int ss1 = calcSerial(1, 1, underestimatedYYYY);
/* 463 */       while (ss1 <= this.serial) {
/* 464 */         underestimatedYYYY++;
/* 465 */         ss1 = calcSerial(1, 1, underestimatedYYYY);
/*     */       } 
/* 467 */       this.year = underestimatedYYYY - 1;
/*     */     } 
/* 470 */     int ss2 = calcSerial(1, 1, this.year);
/* 472 */     int[] daysToEndOfPrecedingMonth = AGGREGATE_DAYS_TO_END_OF_PRECEDING_MONTH;
/* 475 */     if (isLeapYear(this.year))
/* 476 */       daysToEndOfPrecedingMonth = LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_PRECEDING_MONTH; 
/* 481 */     int mm = 1;
/* 482 */     int sss = ss2 + daysToEndOfPrecedingMonth[mm] - 1;
/* 483 */     while (sss < this.serial) {
/* 484 */       mm++;
/* 485 */       sss = ss2 + daysToEndOfPrecedingMonth[mm] - 1;
/*     */     } 
/* 487 */     this.month = mm - 1;
/* 490 */     this.day = this.serial - ss2 - daysToEndOfPrecedingMonth[this.month] + 1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\date\SpreadsheetDate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */