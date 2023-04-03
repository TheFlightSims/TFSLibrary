/*     */ package org.jfree.date;
/*     */ 
/*     */ public class RelativeDayOfWeekRule extends AnnualDateRule {
/*     */   private AnnualDateRule subrule;
/*     */   
/*     */   private int dayOfWeek;
/*     */   
/*     */   private int relative;
/*     */   
/*     */   public RelativeDayOfWeekRule() {
/*  73 */     this(new DayAndMonthRule(), 2, 1);
/*     */   }
/*     */   
/*     */   public RelativeDayOfWeekRule(AnnualDateRule subrule, int dayOfWeek, int relative) {
/*  86 */     this.subrule = subrule;
/*  87 */     this.dayOfWeek = dayOfWeek;
/*  88 */     this.relative = relative;
/*     */   }
/*     */   
/*     */   public AnnualDateRule getSubrule() {
/*  98 */     return this.subrule;
/*     */   }
/*     */   
/*     */   public void setSubrule(AnnualDateRule subrule) {
/* 108 */     this.subrule = subrule;
/*     */   }
/*     */   
/*     */   public int getDayOfWeek() {
/* 117 */     return this.dayOfWeek;
/*     */   }
/*     */   
/*     */   public void setDayOfWeek(int dayOfWeek) {
/* 127 */     this.dayOfWeek = dayOfWeek;
/*     */   }
/*     */   
/*     */   public int getRelative() {
/* 138 */     return this.relative;
/*     */   }
/*     */   
/*     */   public void setRelative(int relative) {
/* 149 */     this.relative = relative;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 160 */     RelativeDayOfWeekRule duplicate = (RelativeDayOfWeekRule)super.clone();
/* 162 */     duplicate.subrule = (AnnualDateRule)duplicate.getSubrule().clone();
/* 163 */     return duplicate;
/*     */   }
/*     */   
/*     */   public SerialDate getDate(int year) {
/* 177 */     if (year < 1900 || year > 9999)
/* 179 */       throw new IllegalArgumentException("RelativeDayOfWeekRule.getDate(): year outside valid range."); 
/* 184 */     SerialDate result = null;
/* 185 */     SerialDate base = this.subrule.getDate(year);
/* 187 */     if (base != null)
/* 188 */       switch (this.relative) {
/*     */         case -1:
/* 190 */           result = SerialDate.getPreviousDayOfWeek(this.dayOfWeek, base);
/*     */           break;
/*     */         case 0:
/* 194 */           result = SerialDate.getNearestDayOfWeek(this.dayOfWeek, base);
/*     */           break;
/*     */         case 1:
/* 198 */           result = SerialDate.getFollowingDayOfWeek(this.dayOfWeek, base);
/*     */           break;
/*     */       }  
/* 205 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\date\RelativeDayOfWeekRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */