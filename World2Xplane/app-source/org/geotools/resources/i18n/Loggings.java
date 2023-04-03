/*     */ package org.geotools.resources.i18n;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import org.geotools.resources.IndexedResourceBundle;
/*     */ 
/*     */ public class Loggings extends IndexedResourceBundle {
/*     */   public static Loggings getResources(Locale locale) throws MissingResourceException {
/*  47 */     if (locale == null)
/*  48 */       locale = Locale.getDefault(); 
/*  50 */     return (Loggings)getBundle(Loggings.class.getName(), locale);
/*     */   }
/*     */   
/*     */   public static LogRecord format(Level level, int key) throws MissingResourceException {
/*  67 */     return getResources((Locale)null).getLogRecord(level, key);
/*     */   }
/*     */   
/*     */   public static LogRecord format(Level level, int key, Object arg0) throws MissingResourceException {
/*  84 */     return getResources((Locale)null).getLogRecord(level, key, arg0);
/*     */   }
/*     */   
/*     */   public static LogRecord format(Level level, int key, Object arg0, Object arg1) throws MissingResourceException {
/* 103 */     return getResources((Locale)null).getLogRecord(level, key, arg0, arg1);
/*     */   }
/*     */   
/*     */   public static LogRecord format(Level level, int key, Object arg0, Object arg1, Object arg2) throws MissingResourceException {
/* 124 */     return getResources((Locale)null).getLogRecord(level, key, arg0, arg1, arg2);
/*     */   }
/*     */   
/*     */   public static LogRecord format(Level level, int key, Object arg0, Object arg1, Object arg2, Object arg3) throws MissingResourceException {
/* 147 */     return getResources((Locale)null).getLogRecord(level, key, arg0, arg1, arg2, arg3);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\i18n\Loggings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */