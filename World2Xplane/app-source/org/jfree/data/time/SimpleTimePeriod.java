/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class SimpleTimePeriod implements TimePeriod, Comparable, Serializable {
/*     */   private static final long serialVersionUID = 8684672361131829554L;
/*     */   
/*     */   private Date start;
/*     */   
/*     */   private Date end;
/*     */   
/*     */   public SimpleTimePeriod(long start, long end) {
/*  78 */     this(new Date(start), new Date(end));
/*     */   }
/*     */   
/*     */   public SimpleTimePeriod(Date start, Date end) {
/*  88 */     if (start.getTime() > end.getTime())
/*  89 */       throw new IllegalArgumentException("Requires end >= start."); 
/*  91 */     this.start = start;
/*  92 */     this.end = end;
/*     */   }
/*     */   
/*     */   public Date getStart() {
/* 101 */     return this.start;
/*     */   }
/*     */   
/*     */   public Date getEnd() {
/* 110 */     return this.end;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 123 */     if (obj == this)
/* 124 */       return true; 
/* 126 */     if (!(obj instanceof TimePeriod))
/* 127 */       return false; 
/* 129 */     TimePeriod that = (TimePeriod)obj;
/* 130 */     if (!this.start.equals(that.getStart()))
/* 131 */       return false; 
/* 133 */     if (!this.end.equals(that.getEnd()))
/* 134 */       return false; 
/* 136 */     return true;
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 151 */     TimePeriod that = (TimePeriod)obj;
/* 152 */     long t0 = getStart().getTime();
/* 153 */     long t1 = getEnd().getTime();
/* 154 */     long m0 = t0 + (t1 - t0) / 2L;
/* 155 */     long t2 = that.getStart().getTime();
/* 156 */     long t3 = that.getEnd().getTime();
/* 157 */     long m1 = t2 + (t3 - t2) / 2L;
/* 158 */     if (m0 < m1)
/* 159 */       return -1; 
/* 161 */     if (m0 > m1)
/* 162 */       return 1; 
/* 165 */     if (t0 < t2)
/* 166 */       return -1; 
/* 168 */     if (t0 > t2)
/* 169 */       return 1; 
/* 172 */     if (t1 < t3)
/* 173 */       return -1; 
/* 175 */     if (t1 > t3)
/* 176 */       return 1; 
/* 179 */     return 0;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 195 */     int result = 17;
/* 196 */     result = 37 * result + this.start.hashCode();
/* 197 */     result = 37 * result + this.end.hashCode();
/* 198 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\SimpleTimePeriod.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */