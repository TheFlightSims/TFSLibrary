/*    */ package org.jfree.date;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DateUtilities {
/* 63 */   private static final Calendar CALENDAR = Calendar.getInstance();
/*    */   
/*    */   public static synchronized Date createDate(int yyyy, int month, int day) {
/* 75 */     CALENDAR.clear();
/* 76 */     CALENDAR.set(yyyy, month - 1, day);
/* 77 */     return CALENDAR.getTime();
/*    */   }
/*    */   
/*    */   public static synchronized Date createDate(int yyyy, int month, int day, int hour, int min) {
/* 93 */     CALENDAR.clear();
/* 94 */     CALENDAR.set(yyyy, month - 1, day, hour, min);
/* 95 */     return CALENDAR.getTime();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\date\DateUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */