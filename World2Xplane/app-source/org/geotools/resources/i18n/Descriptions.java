/*     */ package org.geotools.resources.i18n;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import org.geotools.resources.IndexedResourceBundle;
/*     */ 
/*     */ public class Descriptions extends IndexedResourceBundle {
/*     */   public static Descriptions getResources(Locale locale) throws MissingResourceException {
/*  45 */     if (locale == null)
/*  46 */       locale = Locale.getDefault(); 
/*  48 */     return (Descriptions)getBundle(Descriptions.class.getName(), locale);
/*     */   }
/*     */   
/*     */   public static String format(int key) throws MissingResourceException {
/*  62 */     return getResources((Locale)null).getString(key);
/*     */   }
/*     */   
/*     */   public static String format(int key, Object arg0) throws MissingResourceException {
/*  77 */     return getResources((Locale)null).getString(key, arg0);
/*     */   }
/*     */   
/*     */   public static String format(int key, Object arg0, Object arg1) throws MissingResourceException {
/*  94 */     return getResources((Locale)null).getString(key, arg0, arg1);
/*     */   }
/*     */   
/*     */   public static String format(int key, Object arg0, Object arg1, Object arg2) throws MissingResourceException {
/* 113 */     return getResources((Locale)null).getString(key, arg0, arg1, arg2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\i18n\Descriptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */