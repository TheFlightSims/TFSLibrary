/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class DateTick extends ValueTick {
/*     */   private Date date;
/*     */   
/*     */   public DateTick(Date date, String label, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle) {
/*  73 */     super(date.getTime(), label, textAnchor, rotationAnchor, angle);
/*  74 */     this.date = date;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  84 */     return this.date;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  95 */     if (obj == this)
/*  96 */       return true; 
/*  98 */     if (obj instanceof DateTick && super.equals(obj)) {
/*  99 */       DateTick dt = (DateTick)obj;
/* 100 */       if (!ObjectUtilities.equal(this.date, dt.date))
/* 101 */         return false; 
/* 103 */       return true;
/*     */     } 
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 114 */     return this.date.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\DateTick.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */