/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.temporal.CalendarDate;
/*     */ import org.opengis.temporal.IndeterminateValue;
/*     */ import org.opengis.temporal.TemporalReferenceSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultCalendarDate extends DefaultTemporalPosition implements CalendarDate {
/*     */   private InternationalString calendarEraName;
/*     */   
/*     */   private int[] calendarDate;
/*     */   
/*     */   public DefaultCalendarDate(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition, InternationalString calendarEraName, int[] calendarDate) {
/*  49 */     super(frame, indeterminatePosition);
/*  50 */     this.calendarDate = calendarDate;
/*  51 */     this.calendarEraName = calendarEraName;
/*     */   }
/*     */   
/*     */   public InternationalString getCalendarEraName() {
/*  59 */     return this.calendarEraName;
/*     */   }
/*     */   
/*     */   public int[] getCalendarDate() {
/*  71 */     return this.calendarDate;
/*     */   }
/*     */   
/*     */   public void setCalendarEraName(InternationalString calendarEraName) {
/*  75 */     this.calendarEraName = calendarEraName;
/*     */   }
/*     */   
/*     */   public void setCalendarDate(int[] calendarDate) {
/*  79 */     this.calendarDate = calendarDate;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  84 */     if (object == this)
/*  85 */       return true; 
/*  87 */     if (object instanceof DefaultCalendarDate && super.equals(object)) {
/*  88 */       DefaultCalendarDate that = (DefaultCalendarDate)object;
/*  90 */       return (Utilities.equals(this.calendarDate, that.calendarDate) && Utilities.equals(this.calendarEraName, that.calendarEraName));
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  98 */     int hash = 5;
/*  99 */     hash = 37 * hash + ((this.calendarDate != null) ? this.calendarDate.hashCode() : 0);
/* 100 */     hash = 37 * hash + ((this.calendarEraName != null) ? this.calendarEraName.hashCode() : 0);
/* 101 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 106 */     StringBuilder s = (new StringBuilder("CalendarDate:")).append('\n');
/* 107 */     if (this.calendarEraName != null)
/* 108 */       s.append("calendarEraName:").append((CharSequence)this.calendarEraName).append('\n'); 
/* 110 */     if (this.calendarDate != null)
/* 111 */       s.append("calendarDate:").append(this.calendarDate).append('\n'); 
/* 113 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultCalendarDate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */