/*    */ package org.openstreetmap.osmosis.core.time;
/*    */ 
/*    */ import java.util.Date;
/*    */ import java.util.GregorianCalendar;
/*    */ import java.util.TimeZone;
/*    */ 
/*    */ public class DateFormatter {
/* 24 */   private GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
/*    */   
/*    */   public String format(Date date) {
/* 44 */     this.calendar.setTime(date);
/* 46 */     StringBuilder result = new StringBuilder(20);
/* 48 */     int year = this.calendar.get(1);
/* 49 */     int month = this.calendar.get(2) + 1;
/* 50 */     int day = this.calendar.get(5);
/* 51 */     int hour = this.calendar.get(11);
/* 52 */     int minute = this.calendar.get(12);
/* 53 */     int second = this.calendar.get(13);
/* 55 */     result.append(year);
/* 56 */     result.append('-');
/* 57 */     if (month < 10)
/* 58 */       result.append('0'); 
/* 60 */     result.append(month);
/* 61 */     result.append('-');
/* 62 */     if (day < 10)
/* 63 */       result.append('0'); 
/* 65 */     result.append(day);
/* 66 */     result.append('T');
/* 67 */     if (hour < 10)
/* 68 */       result.append('0'); 
/* 70 */     result.append(hour);
/* 71 */     result.append(':');
/* 72 */     if (minute < 10)
/* 73 */       result.append('0'); 
/* 75 */     result.append(minute);
/* 76 */     result.append(':');
/* 77 */     if (second < 10)
/* 78 */       result.append('0'); 
/* 80 */     result.append(second);
/* 81 */     result.append('Z');
/* 83 */     return result.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\time\DateFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */