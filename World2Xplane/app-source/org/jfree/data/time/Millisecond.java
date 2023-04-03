/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class Millisecond extends RegularTimePeriod implements Serializable {
/*     */   static final long serialVersionUID = -5316836467277638485L;
/*     */   
/*     */   public static final int FIRST_MILLISECOND_IN_SECOND = 0;
/*     */   
/*     */   public static final int LAST_MILLISECOND_IN_SECOND = 999;
/*     */   
/*     */   private int millisecond;
/*     */   
/*     */   private Second second;
/*     */   
/*     */   public Millisecond() {
/*  84 */     this(new Date());
/*     */   }
/*     */   
/*     */   public Millisecond(int millisecond, Second second) {
/*  94 */     this.millisecond = millisecond;
/*  95 */     this.second = second;
/*     */   }
/*     */   
/*     */   public Millisecond(int millisecond, int second, int minute, int hour, int day, int month, int year) {
/* 112 */     this(millisecond, new Second(second, minute, hour, day, month, year));
/*     */   }
/*     */   
/*     */   public Millisecond(Date time) {
/* 122 */     this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
/*     */   }
/*     */   
/*     */   public Millisecond(Date time, TimeZone zone) {
/* 133 */     this.second = new Second(time, zone);
/* 134 */     Calendar calendar = Calendar.getInstance(zone);
/* 135 */     calendar.setTime(time);
/* 136 */     this.millisecond = calendar.get(14);
/*     */   }
/*     */   
/*     */   public Second getSecond() {
/* 146 */     return this.second;
/*     */   }
/*     */   
/*     */   public long getMillisecond() {
/* 155 */     return this.millisecond;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod previous() {
/* 165 */     RegularTimePeriod result = null;
/* 167 */     if (this.millisecond != 0) {
/* 168 */       result = new Millisecond(this.millisecond - 1, this.second);
/*     */     } else {
/* 171 */       Second previous = (Second)this.second.previous();
/* 172 */       if (previous != null)
/* 173 */         result = new Millisecond(999, previous); 
/*     */     } 
/* 176 */     return result;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod next() {
/* 187 */     RegularTimePeriod result = null;
/* 188 */     if (this.millisecond != 999) {
/* 189 */       result = new Millisecond(this.millisecond + 1, this.second);
/*     */     } else {
/* 192 */       Second next = (Second)this.second.next();
/* 193 */       if (next != null)
/* 194 */         result = new Millisecond(0, next); 
/*     */     } 
/* 197 */     return result;
/*     */   }
/*     */   
/*     */   public long getSerialIndex() {
/* 207 */     return this.second.getSerialIndex() * 1000L + this.millisecond;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 223 */     if (obj instanceof Millisecond) {
/* 224 */       Millisecond m = (Millisecond)obj;
/* 225 */       return (this.millisecond == m.getMillisecond() && this.second.equals(m.getSecond()));
/*     */     } 
/* 229 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 244 */     int result = 17;
/* 245 */     result = 37 * result + this.millisecond;
/* 246 */     result = 37 * result + this.second.hashCode();
/* 247 */     return result;
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/*     */     int result;
/* 267 */     if (obj instanceof Millisecond) {
/* 268 */       Millisecond ms = (Millisecond)obj;
/* 269 */       long difference = getFirstMillisecond() - ms.getFirstMillisecond();
/* 270 */       if (difference > 0L) {
/* 271 */         result = 1;
/* 274 */       } else if (difference < 0L) {
/* 275 */         result = -1;
/*     */       } else {
/* 278 */         result = 0;
/*     */       } 
/* 285 */     } else if (obj instanceof RegularTimePeriod) {
/* 287 */       result = 0;
/*     */     } else {
/* 294 */       result = 1;
/*     */     } 
/* 297 */     return result;
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond() {
/* 307 */     return this.second.getFirstMillisecond() + this.millisecond;
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(Calendar calendar) {
/* 318 */     return this.second.getFirstMillisecond(calendar) + this.millisecond;
/*     */   }
/*     */   
/*     */   public long getLastMillisecond() {
/* 327 */     return this.second.getFirstMillisecond() + this.millisecond;
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(Calendar calendar) {
/* 338 */     return this.second.getFirstMillisecond(calendar) + this.millisecond;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\Millisecond.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */