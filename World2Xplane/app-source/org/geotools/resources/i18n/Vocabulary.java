/*     */ package org.geotools.resources.i18n;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import org.geotools.resources.IndexedResourceBundle;
/*     */ import org.geotools.util.ResourceInternationalString;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class Vocabulary extends IndexedResourceBundle {
/*     */   public static Vocabulary getResources(Locale locale) throws MissingResourceException {
/*  47 */     if (locale == null)
/*  48 */       locale = Locale.getDefault(); 
/*  50 */     return (Vocabulary)getBundle(Vocabulary.class.getName(), locale);
/*     */   }
/*     */   
/*     */   public static InternationalString formatInternational(int key) {
/*  65 */     return (InternationalString)new ResourceInternationalString(Vocabulary.class.getName(), String.valueOf(key));
/*     */   }
/*     */   
/*     */   public static InternationalString formatInternational(int key, Object arg0) {
/*  83 */     return (InternationalString)new SimpleInternationalString(format(key, arg0));
/*     */   }
/*     */   
/*     */   public static InternationalString formatInternational(int key, Object arg0, Object arg1) {
/* 103 */     return (InternationalString)new SimpleInternationalString(format(key, arg0, arg1));
/*     */   }
/*     */   
/*     */   public static InternationalString formatInternational(int key, Object arg0, Object arg1, Object arg2) {
/* 125 */     return (InternationalString)new SimpleInternationalString(format(key, arg0, arg1, arg2));
/*     */   }
/*     */   
/*     */   public static String format(int key) throws MissingResourceException {
/* 136 */     return getResources((Locale)null).getString(key);
/*     */   }
/*     */   
/*     */   public static String format(int key, Object arg0) throws MissingResourceException {
/* 151 */     return getResources((Locale)null).getString(key, arg0);
/*     */   }
/*     */   
/*     */   public static String format(int key, Object arg0, Object arg1) throws MissingResourceException {
/* 168 */     return getResources((Locale)null).getString(key, arg0, arg1);
/*     */   }
/*     */   
/*     */   public static String format(int key, Object arg0, Object arg1, Object arg2) throws MissingResourceException {
/* 187 */     return getResources((Locale)null).getString(key, arg0, arg1, arg2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\i18n\Vocabulary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */