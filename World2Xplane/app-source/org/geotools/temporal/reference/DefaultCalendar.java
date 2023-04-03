/*     */ package org.geotools.temporal.reference;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.GregorianCalendar;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.temporal.object.DefaultCalendarDate;
/*     */ import org.geotools.temporal.object.DefaultDateAndTime;
/*     */ import org.geotools.temporal.object.DefaultJulianDate;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.temporal.Calendar;
/*     */ import org.opengis.temporal.CalendarDate;
/*     */ import org.opengis.temporal.CalendarEra;
/*     */ import org.opengis.temporal.Clock;
/*     */ import org.opengis.temporal.ClockTime;
/*     */ import org.opengis.temporal.DateAndTime;
/*     */ import org.opengis.temporal.JulianDate;
/*     */ import org.opengis.temporal.TemporalCoordinateSystem;
/*     */ import org.opengis.temporal.TemporalReferenceSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultCalendar extends DefaultTemporalReferenceSystem implements Calendar {
/*     */   private Collection<CalendarEra> basis;
/*     */   
/*     */   private Clock timeBasis;
/*     */   
/*     */   public DefaultCalendar(ReferenceIdentifier name, Extent domainOfValidity) {
/*  64 */     super(name, domainOfValidity);
/*     */   }
/*     */   
/*     */   public JulianDate dateTrans(CalendarDate calDate, ClockTime time) {
/*  75 */     if (calDate != null && time != null) {
/*  76 */       DefaultDateAndTime defaultDateAndTime = new DefaultDateAndTime(this, calDate.getIndeterminatePosition(), calDate.getCalendarEraName(), calDate.getCalendarDate(), time.getClockTime());
/*  77 */       return dateTrans((DateAndTime)defaultDateAndTime);
/*     */     } 
/*  79 */     GregorianCalendar gc = new GregorianCalendar(-4713, 1, 1);
/*  80 */     gc.set(0, 0);
/*  81 */     int julianGre = 588829;
/*  82 */     Number coordinateValue = Integer.valueOf(0);
/*  83 */     TemporalCoordinateSystem refSystem = new DefaultTemporalCoordinateSystem((ReferenceIdentifier)new NamedIdentifier(Citations.CRS, (InternationalString)new SimpleInternationalString("Julian calendar")), null, gc.getTime(), (InternationalString)new SimpleInternationalString("day"));
/*  85 */     if (calDate != null) {
/*  86 */       int[] cal = calDate.getCalendarDate();
/*  87 */       int year = 0;
/*  88 */       int month = 0;
/*  89 */       int day = 0;
/*  90 */       if (cal.length > 3)
/*  91 */         throw new IllegalArgumentException("The CalendarDate integer array is malformed ! see ISO 8601 format."); 
/*  93 */       year = cal[0];
/*  94 */       if (cal.length > 0)
/*  95 */         month = cal[1]; 
/*  97 */       if (cal.length > 1)
/*  98 */         day = cal[2]; 
/* 100 */       int julianYear = year;
/* 101 */       if (year < 0)
/* 102 */         julianYear++; 
/* 104 */       int julianMonth = month;
/* 105 */       if (month > 2) {
/* 106 */         julianMonth++;
/*     */       } else {
/* 108 */         julianYear--;
/* 109 */         julianMonth += 13;
/*     */       } 
/* 111 */       double julian = Math.floor(365.25D * julianYear) + Math.floor(30.6001D * julianMonth) + day + 1720995.0D;
/* 112 */       if (day + 31 * (month + 12 * year) >= 588829) {
/* 114 */         int ja = (int)(0.01D * julianYear);
/* 115 */         julian += (2 - ja) + 0.25D * ja;
/*     */       } 
/* 117 */       coordinateValue = Double.valueOf(Math.floor(julian));
/* 118 */       return (JulianDate)new DefaultJulianDate((TemporalReferenceSystem)refSystem, null, coordinateValue);
/*     */     } 
/* 121 */     if (time != null) {
/* 122 */       Number[] clk = time.getClockTime();
/* 123 */       Number hour = Integer.valueOf(0);
/* 124 */       Number minute = Integer.valueOf(0);
/* 125 */       Number second = Integer.valueOf(0);
/* 126 */       if (clk.length > 3)
/* 127 */         throw new IllegalArgumentException("The ClockTime Number array is malformed ! see ISO 8601 format."); 
/* 129 */       hour = clk[0];
/* 130 */       if (clk.length > 0)
/* 131 */         minute = clk[1]; 
/* 133 */       if (clk.length > 1)
/* 134 */         second = clk[2]; 
/* 136 */       double julian = (hour.doubleValue() - 12.0D) / 24.0D + minute.doubleValue() / 1440.0D + second.doubleValue() / 86400.0D;
/* 137 */       coordinateValue = Double.valueOf(julian);
/* 138 */       return (JulianDate)new DefaultJulianDate((TemporalReferenceSystem)refSystem, null, coordinateValue);
/*     */     } 
/* 142 */     throw new IllegalArgumentException("the both CalendarDate and ClockTime cannot be null !");
/*     */   }
/*     */   
/*     */   public JulianDate dateTrans(DateAndTime dateAndTime) {
/* 153 */     GregorianCalendar gc = new GregorianCalendar(-4713, 1, 1);
/* 154 */     gc.set(0, 0);
/* 155 */     int julianGre = 588829;
/* 156 */     TemporalCoordinateSystem refSystem = new DefaultTemporalCoordinateSystem((ReferenceIdentifier)new NamedIdentifier(Citations.CRS, (InternationalString)new SimpleInternationalString("Julian calendar")), null, gc.getTime(), (InternationalString)new SimpleInternationalString("day"));
/* 158 */     Number coordinateValue = Integer.valueOf(0);
/* 159 */     int year = 0, month = 0, day = 0;
/* 160 */     Number hour = Integer.valueOf(0), minute = Integer.valueOf(0), second = Integer.valueOf(0);
/* 161 */     if (dateAndTime == null)
/* 162 */       throw new IllegalArgumentException("The DateAndTime cannot be null ! "); 
/* 164 */     if (dateAndTime.getCalendarDate() != null) {
/* 165 */       int[] cal = dateAndTime.getCalendarDate();
/* 166 */       if (cal.length > 3)
/* 167 */         throw new IllegalArgumentException("The CalendarDate integer array is malformed ! see ISO 8601 format."); 
/* 169 */       year = cal[0];
/* 170 */       if (cal.length > 0)
/* 171 */         month = cal[1]; 
/* 173 */       if (cal.length > 1)
/* 174 */         day = cal[2]; 
/* 176 */       int julianYear = year;
/* 177 */       if (year < 0)
/* 178 */         julianYear++; 
/* 180 */       int julianMonth = month;
/* 181 */       if (month > 2) {
/* 182 */         julianMonth++;
/*     */       } else {
/* 184 */         julianYear--;
/* 185 */         julianMonth += 13;
/*     */       } 
/* 187 */       double julian = Math.floor(365.25D * julianYear) + Math.floor(30.6001D * julianMonth) + day + 1720995.0D;
/* 188 */       if (day + 31 * (month + 12 * year) >= 588829) {
/* 189 */         int ja = (int)(0.01D * julianYear);
/* 190 */         julian += (2 - ja) + 0.25D * ja;
/*     */       } 
/* 192 */       coordinateValue = Double.valueOf(Math.floor(julian));
/*     */     } 
/* 195 */     if (dateAndTime.getClockTime() != null) {
/* 196 */       Number[] clk = dateAndTime.getClockTime();
/* 197 */       if (clk.length > 3)
/* 198 */         throw new IllegalArgumentException("The ClockTime Number array is malformed ! see ISO 8601 format."); 
/* 200 */       hour = clk[0];
/* 201 */       if (clk.length > 0)
/* 202 */         minute = clk[1]; 
/* 204 */       if (clk.length > 1)
/* 205 */         second = clk[2]; 
/* 207 */       double julian = (hour.doubleValue() - 12.0D) / 24.0D + minute.doubleValue() / 1440.0D + second.doubleValue() / 86400.0D;
/* 208 */       coordinateValue = Double.valueOf(coordinateValue.doubleValue() + julian);
/*     */     } 
/* 211 */     return (JulianDate)new DefaultJulianDate((TemporalReferenceSystem)refSystem, null, coordinateValue);
/*     */   }
/*     */   
/*     */   public CalendarDate julTrans(JulianDate jdt) {
/* 221 */     if (jdt == null)
/* 222 */       return null; 
/* 224 */     CalendarDate response = null;
/* 226 */     int JGREG = 588829;
/* 228 */     int ja = jdt.getCoordinateValue().intValue();
/* 229 */     if (ja >= JGREG) {
/* 230 */       int jalpha = (int)(((ja - 1867216) - 0.25D) / 36524.25D);
/* 231 */       ja = ja + 1 + jalpha - jalpha / 4;
/*     */     } 
/* 234 */     int jb = ja + 1524;
/* 235 */     int jc = (int)(6680.0D + ((jb - 2439870) - 122.1D) / 365.25D);
/* 236 */     int jd = 365 * jc + jc / 4;
/* 237 */     int je = (int)((jb - jd) / 30.6001D);
/* 238 */     int day = jb - jd - (int)(30.6001D * je);
/* 239 */     int month = je - 1;
/* 240 */     if (month > 12)
/* 241 */       month -= 12; 
/* 243 */     int year = jc - 4715;
/* 244 */     if (month > 2)
/* 245 */       year--; 
/* 247 */     if (year <= 0)
/* 248 */       year--; 
/* 250 */     int[] calendarDate = { year, month, day };
/* 251 */     return (CalendarDate)new DefaultCalendarDate(this, null, null, calendarDate);
/*     */   }
/*     */   
/*     */   public Collection<CalendarEra> getBasis() {
/* 256 */     return this.basis;
/*     */   }
/*     */   
/*     */   public Clock getClock() {
/* 260 */     return this.timeBasis;
/*     */   }
/*     */   
/*     */   public void setBasis(Collection<CalendarEra> basis) {
/* 264 */     this.basis = basis;
/*     */   }
/*     */   
/*     */   public void setClock(Clock clock) {
/* 268 */     this.timeBasis = clock;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 273 */     if (object == this)
/* 274 */       return true; 
/* 276 */     if (object instanceof DefaultCalendar && super.equals(object)) {
/* 277 */       DefaultCalendar that = (DefaultCalendar)object;
/* 279 */       return (Utilities.equals(this.basis, that.basis) && Utilities.equals(this.timeBasis, that.timeBasis));
/*     */     } 
/* 282 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 287 */     int hash = super.hashCode();
/* 288 */     hash = 37 * hash + ((this.timeBasis != null) ? this.timeBasis.hashCode() : 0);
/* 289 */     hash = 37 * hash + ((this.basis != null) ? this.basis.hashCode() : 0);
/* 290 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 295 */     StringBuilder s = (new StringBuilder("Calendar:")).append('\n');
/* 296 */     if (this.timeBasis != null)
/* 297 */       s.append("clock:").append(this.timeBasis).append('\n'); 
/* 299 */     if (this.basis != null)
/* 300 */       s.append("basis:").append(this.basis).append('\n'); 
/* 302 */     return super.toString().concat("\n").concat(s.toString());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\reference\DefaultCalendar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */