/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.temporal.DateAndTime;
/*     */ import org.opengis.temporal.IndeterminateValue;
/*     */ import org.opengis.temporal.TemporalReferenceSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultDateAndTime extends DefaultTemporalPosition implements DateAndTime {
/*     */   private InternationalString calendarEraName;
/*     */   
/*     */   private int[] calendarDate;
/*     */   
/*     */   private Number[] clockTime;
/*     */   
/*     */   public DefaultDateAndTime(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition, InternationalString calendarEraName, int[] calendarDate, Number[] clockTime) {
/*  54 */     super(frame, indeterminatePosition);
/*  55 */     this.calendarDate = calendarDate;
/*  56 */     this.calendarEraName = calendarEraName;
/*  57 */     this.clockTime = clockTime;
/*     */   }
/*     */   
/*     */   public Number[] getClockTime() {
/*  69 */     return this.clockTime;
/*     */   }
/*     */   
/*     */   public InternationalString getCalendarEraName() {
/*  73 */     return this.calendarEraName;
/*     */   }
/*     */   
/*     */   public int[] getCalendarDate() {
/*  85 */     return this.calendarDate;
/*     */   }
/*     */   
/*     */   public void setCalendarEraName(InternationalString calendarEraName) {
/*  89 */     this.calendarEraName = calendarEraName;
/*     */   }
/*     */   
/*     */   public void setCalendarDate(int[] calendarDate) {
/*  93 */     this.calendarDate = calendarDate;
/*     */   }
/*     */   
/*     */   public void setClockTime(Number[] clockTime) {
/*  97 */     this.clockTime = clockTime;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 102 */     if (object == this)
/* 103 */       return true; 
/* 105 */     if (object instanceof DefaultDateAndTime && super.equals(object)) {
/* 106 */       DefaultDateAndTime that = (DefaultDateAndTime)object;
/* 108 */       return (Utilities.equals(this.calendarDate, that.calendarDate) && Utilities.equals(this.calendarEraName, that.calendarEraName) && Utilities.equals(this.clockTime, that.clockTime));
/*     */     } 
/* 112 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 117 */     int hash = 5;
/* 118 */     hash = 37 * hash + ((this.calendarDate != null) ? this.calendarDate.hashCode() : 0);
/* 119 */     hash = 37 * hash + ((this.calendarEraName != null) ? this.calendarEraName.hashCode() : 0);
/* 120 */     hash = 37 * hash + ((this.clockTime != null) ? this.clockTime.hashCode() : 0);
/* 121 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 126 */     StringBuilder s = (new StringBuilder("DateAndTime:")).append('\n');
/* 127 */     if (this.calendarEraName != null)
/* 128 */       s.append("calendarEraName:").append((CharSequence)this.calendarEraName).append('\n'); 
/* 130 */     if (this.calendarDate != null)
/* 131 */       s.append("calendarDate:").append(this.calendarDate).append('\n'); 
/* 133 */     if (this.clockTime != null)
/* 134 */       s.append("clockTime:").append(this.clockTime).append('\n'); 
/* 136 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultDateAndTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */