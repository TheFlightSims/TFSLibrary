/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import java.sql.Time;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.temporal.CalendarDate;
/*     */ import org.opengis.temporal.DateAndTime;
/*     */ import org.opengis.temporal.OrdinalPosition;
/*     */ import org.opengis.temporal.Position;
/*     */ import org.opengis.temporal.TemporalCoordinate;
/*     */ import org.opengis.temporal.TemporalPosition;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultPosition implements Position {
/*     */   private final Object position;
/*     */   
/*     */   public DefaultPosition(Date date) {
/*  54 */     this.position = date;
/*     */   }
/*     */   
/*     */   public DefaultPosition(InternationalString datetime) throws ParseException {
/*  64 */     this.position = Utils.getDateFromString(datetime.toString());
/*     */   }
/*     */   
/*     */   public DefaultPosition(TemporalPosition anyOther) {
/*  72 */     this.position = anyOther;
/*     */   }
/*     */   
/*     */   public TemporalPosition anyOther() {
/*  82 */     return (this.position instanceof TemporalPosition) ? (TemporalPosition)this.position : null;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  93 */     if (this.position instanceof Date)
/*  94 */       return (Date)this.position; 
/*  96 */     if (this.position instanceof TemporalPosition) {
/*  97 */       if (this.position instanceof org.opengis.temporal.JulianDate)
/*  98 */         return Utils.JulianToDate((DefaultJulianDate)this.position); 
/* 100 */       if (this.position instanceof DateAndTime)
/* 101 */         return Utils.dateAndTimeToDate((DateAndTime)this.position); 
/* 103 */       if (this.position instanceof CalendarDate)
/* 104 */         return Utils.calendarDateToDate((CalendarDate)this.position); 
/* 106 */       if (this.position instanceof TemporalCoordinate)
/* 107 */         return Utils.temporalCoordToDate((TemporalCoordinate)this.position); 
/* 109 */       if (this.position instanceof OrdinalPosition)
/* 110 */         return Utils.ordinalToDate((OrdinalPosition)this.position); 
/*     */     } 
/* 113 */     return null;
/*     */   }
/*     */   
/*     */   public Time getTime() {
/* 122 */     return (this.position instanceof Time) ? (Time)this.position : null;
/*     */   }
/*     */   
/*     */   public InternationalString getDateTime() {
/* 131 */     if (this.position instanceof Date) {
/* 132 */       String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
/* 133 */       SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
/* 134 */       return (InternationalString)new SimpleInternationalString(dateFormat.format(this.position));
/*     */     } 
/* 136 */     return null;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 144 */     if (object == this)
/* 145 */       return true; 
/* 147 */     if (object instanceof DefaultPosition) {
/* 148 */       DefaultPosition that = (DefaultPosition)object;
/* 149 */       return Utilities.equals(this.position, that.position);
/*     */     } 
/* 151 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 156 */     int hash = 3;
/* 157 */     hash = 97 * hash + ((this.position != null) ? this.position.hashCode() : 0);
/* 158 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 163 */     StringBuilder s = (new StringBuilder("Position:")).append('\n');
/* 164 */     if (this.position != null)
/* 165 */       s.append("position:").append(this.position).append('\n'); 
/* 167 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */