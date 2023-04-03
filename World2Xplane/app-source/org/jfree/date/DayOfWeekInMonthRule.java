/*     */ package org.jfree.date;
/*     */ 
/*     */ public class DayOfWeekInMonthRule extends AnnualDateRule {
/*     */   private int count;
/*     */   
/*     */   private int dayOfWeek;
/*     */   
/*     */   private int month;
/*     */   
/*     */   public DayOfWeekInMonthRule() {
/*  68 */     this(1, 2, 1);
/*     */   }
/*     */   
/*     */   public DayOfWeekInMonthRule(int count, int dayOfWeek, int month) {
/*  79 */     this.count = count;
/*  80 */     this.dayOfWeek = dayOfWeek;
/*  81 */     this.month = month;
/*     */   }
/*     */   
/*     */   public int getCount() {
/*  90 */     return this.count;
/*     */   }
/*     */   
/*     */   public void setCount(int count) {
/*  99 */     this.count = count;
/*     */   }
/*     */   
/*     */   public int getDayOfWeek() {
/* 108 */     return this.dayOfWeek;
/*     */   }
/*     */   
/*     */   public void setDayOfWeek(int dayOfWeek) {
/* 117 */     this.dayOfWeek = dayOfWeek;
/*     */   }
/*     */   
/*     */   public int getMonth() {
/* 126 */     return this.month;
/*     */   }
/*     */   
/*     */   public void setMonth(int month) {
/* 135 */     this.month = month;
/*     */   }
/*     */   
/*     */   public SerialDate getDate(int year) {
/*     */     SerialDate result;
/* 147 */     if (this.count != 0) {
/* 149 */       result = SerialDate.createInstance(1, this.month, year);
/* 150 */       while (result.getDayOfWeek() != this.dayOfWeek)
/* 151 */         result = SerialDate.addDays(1, result); 
/* 153 */       result = SerialDate.addDays(7 * (this.count - 1), result);
/*     */     } else {
/* 158 */       result = SerialDate.createInstance(1, this.month, year);
/* 159 */       result = result.getEndOfCurrentMonth(result);
/* 160 */       while (result.getDayOfWeek() != this.dayOfWeek)
/* 161 */         result = SerialDate.addDays(-1, result); 
/*     */     } 
/* 165 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\date\DayOfWeekInMonthRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */