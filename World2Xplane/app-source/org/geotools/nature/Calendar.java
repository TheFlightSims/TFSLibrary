/*     */ package org.geotools.nature;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public final class Calendar {
/*  60 */   private static double MILLIS_IN_DAY = 8.64E7D;
/*     */   
/*  70 */   private static double JULIAN_DAY_1970 = 2440587.5D;
/*     */   
/*     */   public static double julianDay(Date time) {
/*  85 */     return julianDay(time.getTime());
/*     */   }
/*     */   
/*     */   static double julianDay(long time) {
/*  94 */     return time / MILLIS_IN_DAY + JULIAN_DAY_1970;
/*     */   }
/*     */   
/*     */   static double julianCentury(Date time) {
/* 104 */     return (time.getTime() / MILLIS_IN_DAY + JULIAN_DAY_1970 - 2451545.0D) / 36525.0D;
/*     */   }
/*     */   
/*     */   public static double tropicalYearLength(Date time) {
/* 117 */     double T = julianCentury(time);
/* 118 */     return 365.2421896698D + T * (-6.15359E-6D + T * (-7.29E-10D + T * 2.64E-10D));
/*     */   }
/*     */   
/*     */   public static double synodicMonthLength(Date time) {
/* 128 */     double T = julianCentury(time);
/* 129 */     return 29.5305888531D + T * (2.1621E-7D + T * -3.64E-10D);
/*     */   }
/*     */   
/*     */   public static final void main(String[] args) throws ParseException {
/* 143 */     DateFormat format = DateFormat.getDateInstance(3);
/* 144 */     format.setTimeZone(TimeZone.getTimeZone("UTC"));
/* 145 */     Date time = (args.length != 0) ? format.parse(args[0]) : new Date();
/* 146 */     System.out.print("Date (UTC)   : ");
/* 146 */     System.out.println(format.format(time));
/* 147 */     System.out.print("Tropical year: ");
/* 147 */     System.out.println(tropicalYearLength(time));
/* 148 */     System.out.print("Synodic month: ");
/* 148 */     System.out.println(synodicMonthLength(time));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\nature\Calendar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */