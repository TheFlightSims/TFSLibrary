/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class DateTickUnit extends TickUnit implements Serializable {
/*     */   private static final long serialVersionUID = -7289292157229621901L;
/*     */   
/*     */   public static final int YEAR = 0;
/*     */   
/*     */   public static final int MONTH = 1;
/*     */   
/*     */   public static final int DAY = 2;
/*     */   
/*     */   public static final int HOUR = 3;
/*     */   
/*     */   public static final int MINUTE = 4;
/*     */   
/*     */   public static final int SECOND = 5;
/*     */   
/*     */   public static final int MILLISECOND = 6;
/*     */   
/*     */   private int unit;
/*     */   
/*     */   private int count;
/*     */   
/*     */   private int rollUnit;
/*     */   
/*     */   private int rollCount;
/*     */   
/*     */   private DateFormat formatter;
/*     */   
/*     */   public DateTickUnit(int unit, int count) {
/* 114 */     this(unit, count, null);
/*     */   }
/*     */   
/*     */   public DateTickUnit(int unit, int count, DateFormat formatter) {
/* 128 */     this(unit, count, unit, count, formatter);
/*     */   }
/*     */   
/*     */   public DateTickUnit(int unit, int count, int rollUnit, int rollCount, DateFormat formatter) {
/* 143 */     super(getMillisecondCount(unit, count));
/* 144 */     this.unit = unit;
/* 145 */     this.count = count;
/* 146 */     this.rollUnit = rollUnit;
/* 147 */     this.rollCount = rollCount;
/* 148 */     this.formatter = formatter;
/* 149 */     if (formatter == null)
/* 150 */       this.formatter = DateFormat.getDateInstance(3); 
/*     */   }
/*     */   
/*     */   public int getUnit() {
/* 165 */     return this.unit;
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 174 */     return this.count;
/*     */   }
/*     */   
/*     */   public int getRollUnit() {
/* 186 */     return this.rollUnit;
/*     */   }
/*     */   
/*     */   public int getRollCount() {
/* 195 */     return this.rollCount;
/*     */   }
/*     */   
/*     */   public String valueToString(double milliseconds) {
/* 206 */     return this.formatter.format(new Date((long)milliseconds));
/*     */   }
/*     */   
/*     */   public String dateToString(Date date) {
/* 217 */     return this.formatter.format(date);
/*     */   }
/*     */   
/*     */   public Date addToDate(Date base) {
/* 229 */     Calendar calendar = Calendar.getInstance();
/* 230 */     calendar.setTime(base);
/* 231 */     calendar.add(getCalendarField(this.unit), this.count);
/* 232 */     return calendar.getTime();
/*     */   }
/*     */   
/*     */   public Date rollDate(Date base) {
/* 245 */     Calendar calendar = Calendar.getInstance();
/* 246 */     calendar.setTime(base);
/* 247 */     calendar.add(getCalendarField(this.rollUnit), this.rollCount);
/* 248 */     return calendar.getTime();
/*     */   }
/*     */   
/*     */   public int getCalendarField() {
/* 258 */     return getCalendarField(this.unit);
/*     */   }
/*     */   
/*     */   private int getCalendarField(int tickUnit) {
/* 273 */     switch (tickUnit) {
/*     */       case 0:
/* 275 */         return 1;
/*     */       case 1:
/* 277 */         return 2;
/*     */       case 2:
/* 279 */         return 5;
/*     */       case 3:
/* 281 */         return 11;
/*     */       case 4:
/* 283 */         return 12;
/*     */       case 5:
/* 285 */         return 13;
/*     */       case 6:
/* 287 */         return 14;
/*     */     } 
/* 289 */     return 14;
/*     */   }
/*     */   
/*     */   private static long getMillisecondCount(int unit, int count) {
/* 308 */     switch (unit) {
/*     */       case 0:
/* 310 */         return 31536000000L * count;
/*     */       case 1:
/* 312 */         return 2678400000L * count;
/*     */       case 2:
/* 314 */         return 86400000L * count;
/*     */       case 3:
/* 316 */         return 3600000L * count;
/*     */       case 4:
/* 318 */         return 60000L * count;
/*     */       case 5:
/* 320 */         return 1000L * count;
/*     */       case 6:
/* 322 */         return count;
/*     */     } 
/* 324 */     throw new IllegalArgumentException("DateTickUnit.getMillisecondCount() : unit must be one of the constants YEAR, MONTH, DAY, HOUR, MINUTE, SECOND or MILLISECOND defined in the DateTickUnit class. Do *not* use the constants defined in java.util.Calendar.");
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 343 */     if (obj == this)
/* 344 */       return true; 
/* 346 */     if (!(obj instanceof DateTickUnit))
/* 347 */       return false; 
/* 349 */     if (!super.equals(obj))
/* 350 */       return false; 
/* 352 */     DateTickUnit that = (DateTickUnit)obj;
/* 353 */     if (this.unit != that.unit)
/* 354 */       return false; 
/* 356 */     if (this.count != that.count)
/* 357 */       return false; 
/* 359 */     if (!ObjectUtilities.equal(this.formatter, that.formatter))
/* 360 */       return false; 
/* 362 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 371 */     int result = 19;
/* 372 */     result = 37 * result + this.unit;
/* 373 */     result = 37 * result + this.count;
/* 374 */     result = 37 * result + this.formatter.hashCode();
/* 375 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\DateTickUnit.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */