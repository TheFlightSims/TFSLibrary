/*     */ package org.geotools.resources.i18n;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Locale;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.XArray;
/*     */ 
/*     */ public final class Locales {
/*     */   public static Locale[] getAvailableLanguages() {
/*  51 */     return new Locale[] { Locale.ENGLISH, Locale.FRENCH, Locale.GERMAN };
/*     */   }
/*     */   
/*     */   public static Locale[] getAvailableLocales() {
/*  65 */     Locale[] languages = getAvailableLanguages();
/*  66 */     Locale[] locales = Locale.getAvailableLocales();
/*  67 */     int count = 0;
/*  68 */     for (int i = 0; i < locales.length; i++) {
/*  69 */       Locale locale = locales[i];
/*  70 */       if (containsLanguage(languages, locale))
/*  71 */         locales[count++] = locale; 
/*     */     } 
/*  74 */     locales = (Locale[])XArray.resize((Object[])locales, count);
/*  75 */     return locales;
/*     */   }
/*     */   
/*     */   private static boolean containsLanguage(Locale[] locales, Locale language) {
/*  83 */     String code = language.getLanguage();
/*  84 */     for (int i = 0; i < locales.length; i++) {
/*  85 */       if (code.equals(locales[i].getLanguage()))
/*  86 */         return true; 
/*     */     } 
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public static String[] getAvailableLocales(Locale locale) {
/*  99 */     Locale[] locales = getAvailableLocales();
/* 100 */     String[] display = new String[locales.length];
/* 101 */     for (int i = 0; i < locales.length; i++)
/* 102 */       display[i] = locales[i].getDisplayName(locale); 
/* 104 */     Arrays.sort((Object[])display);
/* 105 */     return display;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 114 */     Arguments arguments = new Arguments(args);
/* 115 */     args = arguments.getRemainingArguments(0);
/* 116 */     String[] locales = getAvailableLocales(arguments.locale);
/* 117 */     for (int i = 0; i < locales.length; i++)
/* 118 */       arguments.out.println(locales[i]); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\i18n\Locales.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */