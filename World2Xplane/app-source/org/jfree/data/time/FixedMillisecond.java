/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class FixedMillisecond extends RegularTimePeriod implements Serializable {
/*     */   private static final long serialVersionUID = 7867521484545646931L;
/*     */   
/*     */   private Date time;
/*     */   
/*     */   public FixedMillisecond() {
/*  73 */     this(new Date());
/*     */   }
/*     */   
/*     */   public FixedMillisecond(long millisecond) {
/*  82 */     this(new Date(millisecond));
/*     */   }
/*     */   
/*     */   public FixedMillisecond(Date time) {
/*  91 */     this.time = time;
/*     */   }
/*     */   
/*     */   public Date getTime() {
/* 100 */     return this.time;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod previous() {
/* 109 */     RegularTimePeriod result = null;
/* 110 */     long t = this.time.getTime();
/* 111 */     if (t != Long.MIN_VALUE)
/* 112 */       result = new FixedMillisecond(t - 1L); 
/* 114 */     return result;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod next() {
/* 123 */     RegularTimePeriod result = null;
/* 124 */     long t = this.time.getTime();
/* 125 */     if (t != Long.MAX_VALUE)
/* 126 */       result = new FixedMillisecond(t + 1L); 
/* 128 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 139 */     if (object instanceof FixedMillisecond) {
/* 140 */       FixedMillisecond m = (FixedMillisecond)object;
/* 141 */       return this.time.equals(m.getTime());
/*     */     } 
/* 144 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 155 */     return this.time.hashCode();
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 174 */     if (o1 instanceof FixedMillisecond) {
/* 175 */       FixedMillisecond t1 = (FixedMillisecond)o1;
/* 176 */       long difference = this.time.getTime() - t1.time.getTime();
/* 177 */       if (difference > 0L) {
/* 178 */         result = 1;
/* 181 */       } else if (difference < 0L) {
/* 182 */         result = -1;
/*     */       } else {
/* 185 */         result = 0;
/*     */       } 
/* 192 */     } else if (o1 instanceof RegularTimePeriod) {
/* 194 */       result = 0;
/*     */     } else {
/* 201 */       result = 1;
/*     */     } 
/* 204 */     return result;
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond() {
/* 214 */     return this.time.getTime();
/*     */   }
/*     */   
/*     */   public long getFirstMillisecond(Calendar calendar) {
/* 226 */     return this.time.getTime();
/*     */   }
/*     */   
/*     */   public long getLastMillisecond() {
/* 235 */     return this.time.getTime();
/*     */   }
/*     */   
/*     */   public long getLastMillisecond(Calendar calendar) {
/* 246 */     return this.time.getTime();
/*     */   }
/*     */   
/*     */   public long getMiddleMillisecond() {
/* 255 */     return this.time.getTime();
/*     */   }
/*     */   
/*     */   public long getMiddleMillisecond(Calendar calendar) {
/* 266 */     return this.time.getTime();
/*     */   }
/*     */   
/*     */   public long getSerialIndex() {
/* 275 */     return this.time.getTime();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\FixedMillisecond.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */