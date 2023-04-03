/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.Range;
/*     */ 
/*     */ public class DateRange extends Range implements Serializable {
/*     */   private static final long serialVersionUID = -4705682568375418157L;
/*     */   
/*     */   private Date lowerDate;
/*     */   
/*     */   private Date upperDate;
/*     */   
/*     */   public DateRange() {
/*  72 */     this(new Date(0L), new Date(1L));
/*     */   }
/*     */   
/*     */   public DateRange(Date lower, Date upper) {
/*  83 */     super(lower.getTime(), upper.getTime());
/*  84 */     this.lowerDate = lower;
/*  85 */     this.upperDate = upper;
/*     */   }
/*     */   
/*     */   public DateRange(double lower, double upper) {
/*  97 */     super(lower, upper);
/*  98 */     this.lowerDate = new Date((long)lower);
/*  99 */     this.upperDate = new Date((long)upper);
/*     */   }
/*     */   
/*     */   public DateRange(Range other) {
/* 111 */     this(other.getLowerBound(), other.getUpperBound());
/*     */   }
/*     */   
/*     */   public Date getLowerDate() {
/* 120 */     return this.lowerDate;
/*     */   }
/*     */   
/*     */   public Date getUpperDate() {
/* 129 */     return this.upperDate;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 138 */     DateFormat df = DateFormat.getDateTimeInstance();
/* 139 */     return "[" + df.format(this.lowerDate) + " --> " + df.format(this.upperDate) + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\DateRange.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */