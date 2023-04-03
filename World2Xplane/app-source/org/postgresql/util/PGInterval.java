/*     */ package org.postgresql.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class PGInterval extends PGobject implements Serializable, Cloneable {
/*     */   private int years;
/*     */   
/*     */   private int months;
/*     */   
/*     */   private int days;
/*     */   
/*     */   private int hours;
/*     */   
/*     */   private int minutes;
/*     */   
/*     */   private double seconds;
/*     */   
/*  35 */   private static final DecimalFormat secondsFormat = new DecimalFormat("0.00####");
/*     */   
/*     */   static {
/*  36 */     DecimalFormatSymbols dfs = secondsFormat.getDecimalFormatSymbols();
/*  37 */     dfs.setDecimalSeparator('.');
/*  38 */     secondsFormat.setDecimalFormatSymbols(dfs);
/*     */   }
/*     */   
/*     */   public PGInterval() {
/*  47 */     setType("interval");
/*     */   }
/*     */   
/*     */   public PGInterval(String value) throws SQLException {
/*  60 */     this();
/*  61 */     setValue(value);
/*     */   }
/*     */   
/*     */   public PGInterval(int years, int months, int days, int hours, int minutes, double seconds) {
/*  71 */     this();
/*  72 */     setValue(years, months, days, hours, minutes, seconds);
/*     */   }
/*     */   
/*     */   public void setValue(String value) throws SQLException {
/*  86 */     boolean ISOFormat = !value.startsWith("@");
/*  89 */     if (!ISOFormat && value.length() == 3 && value.charAt(2) == '0') {
/*  91 */       setValue(0, 0, 0, 0, 0, 0.0D);
/*     */       return;
/*     */     } 
/*  95 */     int years = 0;
/*  96 */     int months = 0;
/*  97 */     int days = 0;
/*  98 */     int hours = 0;
/*  99 */     int minutes = 0;
/* 100 */     double seconds = 0.0D;
/*     */     try {
/* 104 */       String valueToken = null;
/* 106 */       value = value.replace('+', ' ').replace('@', ' ');
/* 107 */       StringTokenizer st = new StringTokenizer(value);
/* 108 */       for (int i = 1; st.hasMoreTokens(); i++) {
/* 110 */         String token = st.nextToken();
/* 112 */         if ((i & 0x1) == 1) {
/* 114 */           int endHours = token.indexOf(':');
/* 115 */           if (endHours == -1) {
/* 117 */             valueToken = token;
/*     */           } else {
/* 123 */             int offset = (token.charAt(0) == '-') ? 1 : 0;
/* 125 */             hours = nullSafeIntGet(token.substring(offset + 0, endHours));
/* 126 */             minutes = nullSafeIntGet(token.substring(endHours + 1, endHours + 3));
/* 130 */             int endMinutes = token.indexOf(':', endHours + 1);
/* 131 */             if (endMinutes != -1)
/* 132 */               seconds = nullSafeDoubleGet(token.substring(endMinutes + 1)); 
/* 134 */             if (offset == 1) {
/* 136 */               hours = -hours;
/* 137 */               minutes = -minutes;
/* 138 */               seconds = -seconds;
/*     */             } 
/* 141 */             valueToken = null;
/*     */           } 
/* 149 */         } else if (token.startsWith("year")) {
/* 150 */           years = nullSafeIntGet(valueToken);
/* 151 */         } else if (token.startsWith("mon")) {
/* 152 */           months = nullSafeIntGet(valueToken);
/* 153 */         } else if (token.startsWith("day")) {
/* 154 */           days = nullSafeIntGet(valueToken);
/* 155 */         } else if (token.startsWith("hour")) {
/* 156 */           hours = nullSafeIntGet(valueToken);
/* 157 */         } else if (token.startsWith("min")) {
/* 158 */           minutes = nullSafeIntGet(valueToken);
/* 159 */         } else if (token.startsWith("sec")) {
/* 160 */           seconds = nullSafeDoubleGet(valueToken);
/*     */         } 
/*     */       } 
/* 164 */     } catch (NumberFormatException e) {
/* 166 */       throw new PSQLException(GT.tr("Conversion of interval failed"), PSQLState.NUMERIC_CONSTANT_OUT_OF_RANGE, e);
/*     */     } 
/* 169 */     if (!ISOFormat && value.endsWith("ago")) {
/* 172 */       setValue(-years, -months, -days, -hours, -minutes, -seconds);
/*     */     } else {
/* 176 */       setValue(years, months, days, hours, minutes, seconds);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setValue(int years, int months, int days, int hours, int minutes, double seconds) {
/* 185 */     setYears(years);
/* 186 */     setMonths(months);
/* 187 */     setDays(days);
/* 188 */     setHours(hours);
/* 189 */     setMinutes(minutes);
/* 190 */     setSeconds(seconds);
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 200 */     return this.years + " years " + this.months + " mons " + this.days + " days " + this.hours + " hours " + this.minutes + " mins " + secondsFormat.format(this.seconds) + " secs";
/*     */   }
/*     */   
/*     */   public int getYears() {
/* 213 */     return this.years;
/*     */   }
/*     */   
/*     */   public void setYears(int years) {
/* 221 */     this.years = years;
/*     */   }
/*     */   
/*     */   public int getMonths() {
/* 229 */     return this.months;
/*     */   }
/*     */   
/*     */   public void setMonths(int months) {
/* 237 */     this.months = months;
/*     */   }
/*     */   
/*     */   public int getDays() {
/* 245 */     return this.days;
/*     */   }
/*     */   
/*     */   public void setDays(int days) {
/* 253 */     this.days = days;
/*     */   }
/*     */   
/*     */   public int getHours() {
/* 261 */     return this.hours;
/*     */   }
/*     */   
/*     */   public void setHours(int hours) {
/* 269 */     this.hours = hours;
/*     */   }
/*     */   
/*     */   public int getMinutes() {
/* 277 */     return this.minutes;
/*     */   }
/*     */   
/*     */   public void setMinutes(int minutes) {
/* 285 */     this.minutes = minutes;
/*     */   }
/*     */   
/*     */   public double getSeconds() {
/* 293 */     return this.seconds;
/*     */   }
/*     */   
/*     */   public void setSeconds(double seconds) {
/* 301 */     this.seconds = seconds;
/*     */   }
/*     */   
/*     */   public void add(Calendar cal) {
/* 313 */     int microseconds = (int)(getSeconds() * 1000000.0D);
/* 314 */     int milliseconds = (microseconds + ((microseconds < 0) ? -500 : 500)) / 1000;
/* 316 */     cal.add(14, milliseconds);
/* 317 */     cal.add(12, getMinutes());
/* 318 */     cal.add(10, getHours());
/* 319 */     cal.add(5, getDays());
/* 320 */     cal.add(2, getMonths());
/* 321 */     cal.add(1, getYears());
/*     */   }
/*     */   
/*     */   public void add(Date date) {
/* 331 */     Calendar cal = Calendar.getInstance();
/* 332 */     cal.setTime(date);
/* 333 */     add(cal);
/* 334 */     date.setTime(cal.getTime().getTime());
/*     */   }
/*     */   
/*     */   public void add(PGInterval interval) {
/* 344 */     interval.setYears(interval.getYears() + getYears());
/* 345 */     interval.setMonths(interval.getMonths() + getMonths());
/* 346 */     interval.setDays(interval.getDays() + getDays());
/* 347 */     interval.setHours(interval.getHours() + getHours());
/* 348 */     interval.setMinutes(interval.getMinutes() + getMinutes());
/* 349 */     interval.setSeconds(interval.getSeconds() + getSeconds());
/*     */   }
/*     */   
/*     */   public void scale(int factor) {
/* 362 */     setYears(factor * getYears());
/* 363 */     setMonths(factor * getMonths());
/* 364 */     setDays(factor * getDays());
/* 365 */     setHours(factor * getHours());
/* 366 */     setMinutes(factor * getMinutes());
/* 367 */     setSeconds(factor * getSeconds());
/*     */   }
/*     */   
/*     */   private int nullSafeIntGet(String value) throws NumberFormatException {
/* 380 */     return (value == null) ? 0 : Integer.parseInt(value);
/*     */   }
/*     */   
/*     */   private double nullSafeDoubleGet(String value) throws NumberFormatException {
/* 393 */     return (value == null) ? 0.0D : Double.parseDouble(value);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 404 */     if (obj == null)
/* 405 */       return false; 
/* 407 */     if (obj == this)
/* 408 */       return true; 
/* 410 */     if (!(obj instanceof PGInterval))
/* 411 */       return false; 
/* 413 */     PGInterval pgi = (PGInterval)obj;
/* 415 */     return (pgi.years == this.years && pgi.months == this.months && pgi.days == this.days && pgi.hours == this.hours && pgi.minutes == this.minutes && Double.doubleToLongBits(pgi.seconds) == Double.doubleToLongBits(this.seconds));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 431 */     return ((((((217 + (int)Double.doubleToLongBits(this.seconds)) * 31 + this.minutes) * 31 + this.hours) * 31 + this.days) * 31 + this.months) * 31 + this.years) * 31;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\PGInterval.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */