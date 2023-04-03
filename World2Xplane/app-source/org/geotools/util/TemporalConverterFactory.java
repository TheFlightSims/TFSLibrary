/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.sql.Date;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ import javax.xml.datatype.DatatypeFactory;
/*     */ import javax.xml.datatype.XMLGregorianCalendar;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.opengis.temporal.Instant;
/*     */ 
/*     */ public class TemporalConverterFactory implements ConverterFactory {
/*     */   public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
/*  77 */     boolean isSafeOnly = false;
/*  79 */     if (hints != null) {
/*  80 */       Object safe = hints.get(ConverterFactory.SAFE_CONVERSION);
/*  81 */       if (safe instanceof Boolean && ((Boolean)safe).booleanValue())
/*  82 */         isSafeOnly = true; 
/*     */     } 
/*  86 */     if (Date.class.isAssignableFrom(source)) {
/*  89 */       if (Calendar.class.isAssignableFrom(target)) {
/*  90 */         if (isSafeOnly && Timestamp.class.isAssignableFrom(source))
/*  92 */           return null; 
/*  95 */         return new Converter() {
/*     */             public Object convert(Object source, Class target) throws Exception {
/*  97 */               Calendar calendar = Calendar.getInstance();
/*  98 */               calendar.setTime((Date)source);
/* 100 */               return calendar;
/*     */             }
/*     */           };
/*     */       } 
/* 106 */       if (Timestamp.class.isAssignableFrom(target) || Time.class.isAssignableFrom(target) || Date.class.isAssignableFrom(target)) {
/* 109 */         if (isSafeOnly && Time.class.isAssignableFrom(target))
/* 111 */           return null; 
/* 114 */         return new Converter() {
/*     */             public Object convert(Object source, Class target) throws Exception {
/* 117 */               Date date = (Date)source;
/* 118 */               return TemporalConverterFactory.this.timeMillisToDate(date.getTime(), target);
/*     */             }
/*     */           };
/*     */       } 
/* 124 */       if (XMLGregorianCalendar.class.isAssignableFrom(target))
/* 125 */         return new Converter() {
/*     */             public <T> T convert(Object source, Class<T> target) throws Exception {
/* 127 */               Date date = (Date)source;
/* 128 */               Calendar calendar = TemporalConverterFactory.this.createConverter(Date.class, Calendar.class, null).<Calendar>convert(date, Calendar.class);
/* 131 */               return TemporalConverterFactory.this.createConverter(Calendar.class, XMLGregorianCalendar.class, null).convert(calendar, (Class)XMLGregorianCalendar.class);
/*     */             }
/*     */           }; 
/*     */     } 
/* 157 */     if (Calendar.class.isAssignableFrom(source)) {
/* 158 */       if (Date.class.isAssignableFrom(target)) {
/* 159 */         if (isSafeOnly && Time.class.isAssignableFrom(target))
/* 161 */           return null; 
/* 163 */         Class<?> fTarget = target;
/* 164 */         return new Converter() {
/*     */             public Object convert(Object source, Class target) throws Exception {
/* 166 */               Calendar calendar = (Calendar)source;
/* 168 */               return TemporalConverterFactory.this.timeMillisToDate(calendar.getTimeInMillis(), target);
/*     */             }
/*     */           };
/*     */       } 
/* 172 */       if (XMLGregorianCalendar.class.isAssignableFrom(target))
/* 173 */         return new Converter() {
/*     */             public <T> T convert(Object source, Class<T> target) throws Exception {
/* 175 */               if (source instanceof GregorianCalendar)
/* 176 */                 return (T)DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar)source); 
/* 180 */               return null;
/*     */             }
/*     */           }; 
/*     */     } 
/* 203 */     if (XMLGregorianCalendar.class.isAssignableFrom(source)) {
/* 204 */       if (Calendar.class.isAssignableFrom(target))
/* 205 */         return new Converter() {
/*     */             public <T> T convert(Object source, Class<T> target) throws Exception {
/* 207 */               XMLGregorianCalendar calendar = (XMLGregorianCalendar)source;
/* 208 */               return (T)calendar.toGregorianCalendar();
/*     */             }
/*     */           }; 
/* 212 */       if (Date.class.isAssignableFrom(target))
/* 213 */         return new Converter() {
/*     */             public <T> T convert(Object source, Class<T> target) throws Exception {
/* 215 */               Calendar calendar = TemporalConverterFactory.this.createConverter(XMLGregorianCalendar.class, Calendar.class, null).<Calendar>convert(source, Calendar.class);
/* 217 */               if (calendar != null)
/* 218 */                 return TemporalConverterFactory.this.createConverter(Calendar.class, Date.class, null).convert(calendar, (Class)Date.class); 
/* 221 */               return null;
/*     */             }
/*     */           }; 
/*     */     } 
/* 227 */     if (TimeZone.class.isAssignableFrom(source) && 
/* 228 */       String.class == target)
/* 229 */       return new Converter() {
/*     */           public <T> T convert(Object source, Class<T> target) throws Exception {
/* 231 */             if (source == null)
/* 232 */               return null; 
/* 234 */             return target.cast(((TimeZone)source).getID());
/*     */           }
/*     */         }; 
/* 240 */     if (Instant.class.isAssignableFrom(source) && 
/* 241 */       Date.class == target)
/* 242 */       return new Converter() {
/*     */           public <T> T convert(Object source, Class<T> target) throws Exception {
/* 246 */             Instant instant = (Instant)source;
/* 247 */             return (T)instant.getPosition().getDate();
/*     */           }
/*     */         }; 
/* 254 */     return null;
/*     */   }
/*     */   
/*     */   Date timeMillisToDate(long time, Class<?> target) {
/* 265 */     if (Timestamp.class.isAssignableFrom(target))
/* 266 */       return new Timestamp(time); 
/* 267 */     if (Date.class.isAssignableFrom(target)) {
/* 268 */       Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
/* 269 */       cal.setTimeInMillis(time);
/* 270 */       cal.set(11, 0);
/* 271 */       cal.set(12, 0);
/* 272 */       cal.set(13, 0);
/* 273 */       cal.set(14, 0);
/* 274 */       return new Date(cal.getTimeInMillis());
/*     */     } 
/* 275 */     if (Time.class.isAssignableFrom(target)) {
/* 276 */       Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
/* 277 */       cal.setTimeInMillis(time);
/* 278 */       cal.set(1, 0);
/* 279 */       cal.set(2, 0);
/* 280 */       cal.set(5, 0);
/* 281 */       return new Time(cal.getTimeInMillis());
/*     */     } 
/* 282 */     if (Date.class.isAssignableFrom(target))
/* 283 */       return new Date(time); 
/* 285 */     throw new IllegalArgumentException("Unsupported target type " + target);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\TemporalConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */