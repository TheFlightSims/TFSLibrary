/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class LocaleUtils {
/*     */   private static List cAvailableLocaleList;
/*     */   
/*     */   private static Set cAvailableLocaleSet;
/*     */   
/*  49 */   private static final Map cLanguagesByCountry = Collections.synchronizedMap(new HashMap());
/*     */   
/*  52 */   private static final Map cCountriesByLanguage = Collections.synchronizedMap(new HashMap());
/*     */   
/*     */   public static Locale toLocale(String str) {
/*  94 */     if (str == null)
/*  95 */       return null; 
/*  97 */     int len = str.length();
/*  98 */     if (len != 2 && len != 5 && len < 7)
/*  99 */       throw new IllegalArgumentException("Invalid locale format: " + str); 
/* 101 */     char ch0 = str.charAt(0);
/* 102 */     char ch1 = str.charAt(1);
/* 103 */     if (ch0 < 'a' || ch0 > 'z' || ch1 < 'a' || ch1 > 'z')
/* 104 */       throw new IllegalArgumentException("Invalid locale format: " + str); 
/* 106 */     if (len == 2)
/* 107 */       return new Locale(str, ""); 
/* 109 */     if (str.charAt(2) != '_')
/* 110 */       throw new IllegalArgumentException("Invalid locale format: " + str); 
/* 112 */     char ch3 = str.charAt(3);
/* 113 */     if (ch3 == '_')
/* 114 */       return new Locale(str.substring(0, 2), "", str.substring(4)); 
/* 116 */     char ch4 = str.charAt(4);
/* 117 */     if (ch3 < 'A' || ch3 > 'Z' || ch4 < 'A' || ch4 > 'Z')
/* 118 */       throw new IllegalArgumentException("Invalid locale format: " + str); 
/* 120 */     if (len == 5)
/* 121 */       return new Locale(str.substring(0, 2), str.substring(3, 5)); 
/* 123 */     if (str.charAt(5) != '_')
/* 124 */       throw new IllegalArgumentException("Invalid locale format: " + str); 
/* 126 */     return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
/*     */   }
/*     */   
/*     */   public static List localeLookupList(Locale locale) {
/* 145 */     return localeLookupList(locale, locale);
/*     */   }
/*     */   
/*     */   public static List localeLookupList(Locale locale, Locale defaultLocale) {
/* 167 */     List list = new ArrayList(4);
/* 168 */     if (locale != null) {
/* 169 */       list.add(locale);
/* 170 */       if (locale.getVariant().length() > 0)
/* 171 */         list.add(new Locale(locale.getLanguage(), locale.getCountry())); 
/* 173 */       if (locale.getCountry().length() > 0)
/* 174 */         list.add(new Locale(locale.getLanguage(), "")); 
/* 176 */       if (!list.contains(defaultLocale))
/* 177 */         list.add(defaultLocale); 
/*     */     } 
/* 180 */     return Collections.unmodifiableList(list);
/*     */   }
/*     */   
/*     */   public static List availableLocaleList() {
/* 194 */     if (cAvailableLocaleList == null)
/* 195 */       initAvailableLocaleList(); 
/* 197 */     return cAvailableLocaleList;
/*     */   }
/*     */   
/*     */   private static synchronized void initAvailableLocaleList() {
/* 206 */     if (cAvailableLocaleList == null) {
/* 207 */       List list = Arrays.asList(Locale.getAvailableLocales());
/* 208 */       cAvailableLocaleList = Collections.unmodifiableList(list);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Set availableLocaleSet() {
/* 223 */     if (cAvailableLocaleSet == null)
/* 224 */       initAvailableLocaleSet(); 
/* 226 */     return cAvailableLocaleSet;
/*     */   }
/*     */   
/*     */   private static synchronized void initAvailableLocaleSet() {
/* 235 */     if (cAvailableLocaleSet == null)
/* 236 */       cAvailableLocaleSet = Collections.unmodifiableSet(new HashSet(availableLocaleList())); 
/*     */   }
/*     */   
/*     */   public static boolean isAvailableLocale(Locale locale) {
/* 248 */     return availableLocaleList().contains(locale);
/*     */   }
/*     */   
/*     */   public static List languagesByCountry(String countryCode) {
/* 262 */     List langs = (List)cLanguagesByCountry.get(countryCode);
/* 263 */     if (langs == null) {
/* 264 */       if (countryCode != null) {
/* 265 */         langs = new ArrayList();
/* 266 */         List locales = availableLocaleList();
/* 267 */         for (int i = 0; i < locales.size(); i++) {
/* 268 */           Locale locale = locales.get(i);
/* 269 */           if (countryCode.equals(locale.getCountry()) && locale.getVariant().length() == 0)
/* 271 */             langs.add(locale); 
/*     */         } 
/* 274 */         langs = Collections.unmodifiableList(langs);
/*     */       } else {
/* 276 */         langs = Collections.EMPTY_LIST;
/*     */       } 
/* 278 */       cLanguagesByCountry.put(countryCode, langs);
/*     */     } 
/* 280 */     return langs;
/*     */   }
/*     */   
/*     */   public static List countriesByLanguage(String languageCode) {
/* 294 */     List countries = (List)cCountriesByLanguage.get(languageCode);
/* 295 */     if (countries == null) {
/* 296 */       if (languageCode != null) {
/* 297 */         countries = new ArrayList();
/* 298 */         List locales = availableLocaleList();
/* 299 */         for (int i = 0; i < locales.size(); i++) {
/* 300 */           Locale locale = locales.get(i);
/* 301 */           if (languageCode.equals(locale.getLanguage()) && locale.getCountry().length() != 0 && locale.getVariant().length() == 0)
/* 304 */             countries.add(locale); 
/*     */         } 
/* 307 */         countries = Collections.unmodifiableList(countries);
/*     */       } else {
/* 309 */         countries = Collections.EMPTY_LIST;
/*     */       } 
/* 311 */       cCountriesByLanguage.put(languageCode, countries);
/*     */     } 
/* 313 */     return countries;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\LocaleUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */