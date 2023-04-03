/*     */ package org.jfree.date;
/*     */ 
/*     */ import java.text.DateFormatSymbols;
/*     */ 
/*     */ public class SerialDateUtilities {
/*  83 */   private DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
/*     */   
/*  84 */   private String[] weekdays = this.dateFormatSymbols.getWeekdays();
/*     */   
/*  85 */   private String[] months = this.dateFormatSymbols.getMonths();
/*     */   
/*     */   public String[] getWeekdays() {
/*  94 */     return this.weekdays;
/*     */   }
/*     */   
/*     */   public String[] getMonths() {
/* 103 */     return this.months;
/*     */   }
/*     */   
/*     */   public int stringToWeekday(String s) {
/* 115 */     if (s.equals(this.weekdays[7]))
/* 116 */       return 7; 
/* 118 */     if (s.equals(this.weekdays[1]))
/* 119 */       return 1; 
/* 121 */     if (s.equals(this.weekdays[2]))
/* 122 */       return 2; 
/* 124 */     if (s.equals(this.weekdays[3]))
/* 125 */       return 3; 
/* 127 */     if (s.equals(this.weekdays[4]))
/* 128 */       return 4; 
/* 130 */     if (s.equals(this.weekdays[5]))
/* 131 */       return 5; 
/* 134 */     return 6;
/*     */   }
/*     */   
/*     */   public static int dayCountActual(SerialDate start, SerialDate end) {
/* 148 */     return end.compare(start);
/*     */   }
/*     */   
/*     */   public static int dayCount30(SerialDate start, SerialDate end) {
/* 171 */     if (start.isBefore(end)) {
/* 172 */       int d1 = start.getDayOfMonth();
/* 173 */       int m1 = start.getMonth();
/* 174 */       int y1 = start.getYYYY();
/* 175 */       int d2 = end.getDayOfMonth();
/* 176 */       int m2 = end.getMonth();
/* 177 */       int y2 = end.getYYYY();
/* 178 */       return 360 * (y2 - y1) + 30 * (m2 - m1) + d2 - d1;
/*     */     } 
/* 181 */     return -dayCount30(end, start);
/*     */   }
/*     */   
/*     */   public static int dayCount30ISDA(SerialDate start, SerialDate end) {
/* 207 */     if (start.isBefore(end)) {
/* 208 */       int d1 = start.getDayOfMonth();
/* 209 */       int m1 = start.getMonth();
/* 210 */       int y1 = start.getYYYY();
/* 211 */       if (d1 == 31)
/* 212 */         d1 = 30; 
/* 214 */       int d2 = end.getDayOfMonth();
/* 215 */       int m2 = end.getMonth();
/* 216 */       int y2 = end.getYYYY();
/* 217 */       if (d2 == 31 && d1 == 30)
/* 218 */         d2 = 30; 
/* 220 */       return 360 * (y2 - y1) + 30 * (m2 - m1) + d2 - d1;
/*     */     } 
/* 222 */     if (start.isAfter(end))
/* 223 */       return -dayCount30ISDA(end, start); 
/* 226 */     return 0;
/*     */   }
/*     */   
/*     */   public static int dayCount30PSA(SerialDate start, SerialDate end) {
/* 251 */     if (start.isOnOrBefore(end)) {
/* 252 */       int d1 = start.getDayOfMonth();
/* 253 */       int m1 = start.getMonth();
/* 254 */       int y1 = start.getYYYY();
/* 256 */       if (isLastDayOfFebruary(start))
/* 257 */         d1 = 30; 
/* 259 */       if (d1 == 31 || isLastDayOfFebruary(start))
/* 261 */         d1 = 30; 
/* 263 */       int d2 = end.getDayOfMonth();
/* 264 */       int m2 = end.getMonth();
/* 265 */       int y2 = end.getYYYY();
/* 266 */       if (d2 == 31 && d1 == 30)
/* 267 */         d2 = 30; 
/* 269 */       return 360 * (y2 - y1) + 30 * (m2 - m1) + d2 - d1;
/*     */     } 
/* 272 */     return -dayCount30PSA(end, start);
/*     */   }
/*     */   
/*     */   public static int dayCount30E(SerialDate start, SerialDate end) {
/* 298 */     if (start.isBefore(end)) {
/* 299 */       int d1 = start.getDayOfMonth();
/* 300 */       int m1 = start.getMonth();
/* 301 */       int y1 = start.getYYYY();
/* 302 */       if (d1 == 31)
/* 303 */         d1 = 30; 
/* 305 */       int d2 = end.getDayOfMonth();
/* 306 */       int m2 = end.getMonth();
/* 307 */       int y2 = end.getYYYY();
/* 308 */       if (d2 == 31)
/* 309 */         d2 = 30; 
/* 311 */       return 360 * (y2 - y1) + 30 * (m2 - m1) + d2 - d1;
/*     */     } 
/* 313 */     if (start.isAfter(end))
/* 314 */       return -dayCount30E(end, start); 
/* 317 */     return 0;
/*     */   }
/*     */   
/*     */   public static boolean isLastDayOfFebruary(SerialDate d) {
/* 333 */     if (d.getMonth() == 2) {
/* 334 */       int dom = d.getDayOfMonth();
/* 335 */       if (SerialDate.isLeapYear(d.getYYYY()))
/* 336 */         return (dom == 29); 
/* 339 */       return (dom == 28);
/*     */     } 
/* 343 */     return false;
/*     */   }
/*     */   
/*     */   public static int countFeb29s(SerialDate start, SerialDate end) {
/* 362 */     int count = 0;
/* 369 */     if (start.isBefore(end)) {
/* 371 */       int y1 = start.getYYYY();
/* 372 */       int y2 = end.getYYYY();
/* 373 */       for (int year = y1; year == y2; year++) {
/* 374 */         if (SerialDate.isLeapYear(year)) {
/* 375 */           SerialDate feb29 = SerialDate.createInstance(29, 2, year);
/* 376 */           if (feb29.isInRange(start, end, 2))
/* 377 */             count++; 
/*     */         } 
/*     */       } 
/* 381 */       return count;
/*     */     } 
/* 384 */     return countFeb29s(end, start);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\date\SerialDateUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */