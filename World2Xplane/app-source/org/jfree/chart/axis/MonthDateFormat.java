/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.DateFormatSymbols;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.data.time.Month;
/*     */ 
/*     */ public class MonthDateFormat extends DateFormat {
/*     */   private String[] months;
/*     */   
/*     */   private boolean[] showYear;
/*     */   
/*     */   private DateFormat yearFormatter;
/*     */   
/*     */   public MonthDateFormat() {
/*  79 */     this(TimeZone.getDefault());
/*     */   }
/*     */   
/*     */   public MonthDateFormat(TimeZone zone) {
/*  88 */     this(zone, Locale.getDefault(), 1, true, false);
/*     */   }
/*     */   
/*     */   public MonthDateFormat(Locale locale) {
/*  98 */     this(TimeZone.getDefault(), locale, 1, true, false);
/*     */   }
/*     */   
/*     */   public MonthDateFormat(TimeZone zone, int chars) {
/* 111 */     this(zone, Locale.getDefault(), chars, true, false);
/*     */   }
/*     */   
/*     */   public MonthDateFormat(Locale locale, int chars) {
/* 124 */     this(TimeZone.getDefault(), locale, chars, true, false);
/*     */   }
/*     */   
/*     */   public MonthDateFormat(TimeZone zone, Locale locale, int chars, boolean showYearForJan, boolean showYearForDec) {
/* 146 */     this(zone, locale, chars, new boolean[] { 
/* 146 */           showYearForJan, false, false, false, false, false, false, false, false, false, 
/* 146 */           false, false, showYearForDec }, new SimpleDateFormat("yy"));
/*     */   }
/*     */   
/*     */   public MonthDateFormat(TimeZone zone, Locale locale, int chars, boolean[] showYear, DateFormat yearFormatter) {
/* 169 */     if (locale == null)
/* 170 */       throw new IllegalArgumentException("Null 'locale' argument."); 
/* 172 */     DateFormatSymbols dfs = new DateFormatSymbols(locale);
/* 173 */     String[] monthsFromLocale = dfs.getMonths();
/* 174 */     this.months = new String[12];
/* 175 */     for (int i = 0; i < 12; i++) {
/* 176 */       if (chars > 0) {
/* 177 */         this.months[i] = monthsFromLocale[i].substring(0, Math.min(chars, monthsFromLocale[i].length()));
/*     */       } else {
/* 182 */         this.months[i] = monthsFromLocale[i];
/*     */       } 
/*     */     } 
/* 185 */     this.calendar = new GregorianCalendar(zone);
/* 186 */     this.showYear = showYear;
/* 187 */     this.yearFormatter = yearFormatter;
/* 192 */     this.numberFormat = NumberFormat.getNumberInstance();
/*     */   }
/*     */   
/*     */   public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
/* 206 */     this.calendar.setTime(date);
/* 207 */     int month = this.calendar.get(2);
/* 208 */     toAppendTo.append(this.months[month]);
/* 209 */     if (this.showYear[month])
/* 210 */       toAppendTo.append(this.yearFormatter.format(date)); 
/* 212 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   public Date parse(String source, ParsePosition pos) {
/* 224 */     return null;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 235 */     if (obj == this)
/* 236 */       return true; 
/* 238 */     if (!(obj instanceof MonthDateFormat))
/* 239 */       return false; 
/* 241 */     if (!super.equals(obj))
/* 242 */       return false; 
/* 244 */     MonthDateFormat that = (MonthDateFormat)obj;
/* 245 */     if (!Arrays.equals((Object[])this.months, (Object[])that.months))
/* 246 */       return false; 
/* 248 */     if (!Arrays.equals(this.showYear, that.showYear))
/* 249 */       return false; 
/* 251 */     if (!this.yearFormatter.equals(that.yearFormatter))
/* 252 */       return false; 
/* 254 */     return true;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 263 */     MonthDateFormat mdf = new MonthDateFormat(Locale.UK, 2);
/* 264 */     System.out.println("UK:");
/* 265 */     System.out.println(mdf.format((new Month(1, 2005)).getStart()));
/* 266 */     System.out.println(mdf.format((new Month(2, 2005)).getStart()));
/* 267 */     System.out.println(mdf.format((new Month(3, 2005)).getStart()));
/* 268 */     System.out.println(mdf.format((new Month(4, 2005)).getStart()));
/* 269 */     System.out.println(mdf.format((new Month(5, 2005)).getStart()));
/* 270 */     System.out.println(mdf.format((new Month(6, 2005)).getStart()));
/* 271 */     System.out.println(mdf.format((new Month(7, 2005)).getStart()));
/* 272 */     System.out.println(mdf.format((new Month(8, 2005)).getStart()));
/* 273 */     System.out.println(mdf.format((new Month(9, 2005)).getStart()));
/* 274 */     System.out.println(mdf.format((new Month(10, 2005)).getStart()));
/* 275 */     System.out.println(mdf.format((new Month(11, 2005)).getStart()));
/* 276 */     System.out.println(mdf.format((new Month(12, 2005)).getStart()));
/* 277 */     System.out.println();
/* 279 */     mdf = new MonthDateFormat(Locale.GERMANY, 2);
/* 280 */     System.out.println("GERMANY:");
/* 281 */     System.out.println(mdf.format((new Month(1, 2005)).getStart()));
/* 282 */     System.out.println(mdf.format((new Month(2, 2005)).getStart()));
/* 283 */     System.out.println(mdf.format((new Month(3, 2005)).getStart()));
/* 284 */     System.out.println(mdf.format((new Month(4, 2005)).getStart()));
/* 285 */     System.out.println(mdf.format((new Month(5, 2005)).getStart()));
/* 286 */     System.out.println(mdf.format((new Month(6, 2005)).getStart()));
/* 287 */     System.out.println(mdf.format((new Month(7, 2005)).getStart()));
/* 288 */     System.out.println(mdf.format((new Month(8, 2005)).getStart()));
/* 289 */     System.out.println(mdf.format((new Month(9, 2005)).getStart()));
/* 290 */     System.out.println(mdf.format((new Month(10, 2005)).getStart()));
/* 291 */     System.out.println(mdf.format((new Month(11, 2005)).getStart()));
/* 292 */     System.out.println(mdf.format((new Month(12, 2005)).getStart()));
/* 293 */     System.out.println();
/* 295 */     mdf = new MonthDateFormat(Locale.FRANCE, 2);
/* 296 */     System.out.println("FRANCE:");
/* 297 */     System.out.println(mdf.format((new Month(1, 2005)).getStart()));
/* 298 */     System.out.println(mdf.format((new Month(2, 2005)).getStart()));
/* 299 */     System.out.println(mdf.format((new Month(3, 2005)).getStart()));
/* 300 */     System.out.println(mdf.format((new Month(4, 2005)).getStart()));
/* 301 */     System.out.println(mdf.format((new Month(5, 2005)).getStart()));
/* 302 */     System.out.println(mdf.format((new Month(6, 2005)).getStart()));
/* 303 */     System.out.println(mdf.format((new Month(7, 2005)).getStart()));
/* 304 */     System.out.println(mdf.format((new Month(8, 2005)).getStart()));
/* 305 */     System.out.println(mdf.format((new Month(9, 2005)).getStart()));
/* 306 */     System.out.println(mdf.format((new Month(10, 2005)).getStart()));
/* 307 */     System.out.println(mdf.format((new Month(11, 2005)).getStart()));
/* 308 */     System.out.println(mdf.format((new Month(12, 2005)).getStart()));
/* 309 */     System.out.println();
/* 311 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
/* 312 */     sdf.setNumberFormat(null);
/* 313 */     System.out.println(sdf.equals("X"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\MonthDateFormat.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */