/*    */ package org.jfree.date;
/*    */ 
/*    */ public class EasterSundayRule extends AnnualDateRule {
/*    */   public SerialDate getDate(int year) {
/* 78 */     int g = year % 19;
/* 79 */     int c = year / 100;
/* 80 */     int h = (c - c / 4 - (8 * c + 13) / 25 + 19 * g + 15) % 30;
/* 81 */     int i = h - h / 28 * (1 - h / 28 * 29 / (h + 1) * (21 - g) / 11);
/* 82 */     int j = (year + year / 4 + i + 2 - c + c / 4) % 7;
/* 83 */     int l = i - j;
/* 84 */     int month = 3 + (l + 40) / 44;
/* 85 */     int day = l + 28 - 31 * month / 4;
/* 86 */     return SerialDate.createInstance(day, month, year);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\date\EasterSundayRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */