/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class GrowableInternationalString extends AbstractInternationalString implements Serializable {
/*     */   private static final long serialVersionUID = 5760033376627376937L;
/*     */   
/*  62 */   private static final Map<Locale, Locale> LOCALES = new HashMap<Locale, Locale>();
/*     */   
/*     */   private Map<Locale, String> localMap;
/*     */   
/*     */   private transient Set<Locale> localSet;
/*     */   
/*     */   public GrowableInternationalString() {
/*  81 */     this.localMap = Collections.emptyMap();
/*     */   }
/*     */   
/*     */   public GrowableInternationalString(String string) {
/*  94 */     if (string != null) {
/*  95 */       this.localMap = Collections.singletonMap(null, string);
/*     */     } else {
/*  97 */       this.localMap = Collections.emptyMap();
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void add(Locale locale, String string) throws IllegalArgumentException {
/* 112 */     if (string != null) {
/* 113 */       switch (this.localMap.size()) {
/*     */         case 0:
/* 115 */           this.localMap = Collections.singletonMap(locale, string);
/* 116 */           this.defaultValue = null;
/*     */           return;
/*     */         case 1:
/* 120 */           this.localMap = new HashMap<Locale, String>(this.localMap);
/*     */           break;
/*     */       } 
/* 124 */       String old = this.localMap.get(locale);
/* 125 */       if (old != null) {
/* 126 */         if (string.equals(old))
/*     */           return; 
/* 130 */         throw new IllegalArgumentException();
/*     */       } 
/* 132 */       this.localMap.put(locale, string);
/* 133 */       this.defaultValue = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean add(String prefix, String key, String string) throws IllegalArgumentException {
/* 165 */     if (!key.startsWith(prefix))
/* 166 */       return false; 
/* 168 */     int position = prefix.length();
/* 169 */     int length = key.length();
/* 170 */     String[] parts = { "", "", "" };
/* 171 */     for (int i = 0;; i++) {
/* 172 */       if (position == length) {
/* 173 */         Locale locale = (i == 0) ? (Locale)null : unique(new Locale(parts[0], parts[1], parts[2]));
/* 177 */         add(locale, string);
/* 178 */         return true;
/*     */       } 
/* 180 */       if (key.charAt(position) != '_' || i == parts.length)
/*     */         break; 
/* 184 */       int next = key.indexOf('_', ++position);
/* 185 */       if (next < 0) {
/* 186 */         next = length;
/* 187 */       } else if (next == position) {
/*     */         break;
/*     */       } 
/* 191 */       parts[i] = key.substring(position, position = next);
/*     */     } 
/* 193 */     throw new IllegalArgumentException(Errors.format(58, "locale", key.substring(prefix.length())));
/*     */   }
/*     */   
/*     */   private static synchronized Locale unique(Locale locale) {
/* 208 */     if (LOCALES.isEmpty())
/*     */       try {
/* 209 */         Field[] fields = Locale.class.getFields();
/* 210 */         for (int i = 0; i < fields.length; i++) {
/* 211 */           Field field = fields[i];
/* 212 */           if (Modifier.isStatic(field.getModifiers()) && 
/* 213 */             Locale.class.isAssignableFrom(field.getType())) {
/* 214 */             Locale toAdd = (Locale)field.get(null);
/* 215 */             LOCALES.put(toAdd, toAdd);
/*     */           } 
/*     */         } 
/* 219 */       } catch (Exception exception) {
/* 224 */         Logging.unexpectedException(GrowableInternationalString.class, "unique", exception);
/*     */       }  
/* 229 */     Locale candidate = LOCALES.get(locale);
/* 230 */     if (candidate != null)
/* 231 */       return candidate; 
/* 233 */     LOCALES.put(locale, locale);
/* 234 */     return locale;
/*     */   }
/*     */   
/*     */   public synchronized Set<Locale> getLocales() {
/* 243 */     if (this.localSet == null)
/* 244 */       this.localSet = Collections.unmodifiableSet(this.localMap.keySet()); 
/* 246 */     return this.localSet;
/*     */   }
/*     */   
/*     */   public synchronized String toString(Locale locale) {
/* 263 */     while (locale != null) {
/* 264 */       String str1 = this.localMap.get(locale);
/* 265 */       if (str1 != null)
/* 266 */         return str1; 
/* 268 */       String language = locale.getLanguage();
/* 269 */       String country = locale.getCountry();
/* 270 */       String variant = locale.getVariant();
/* 271 */       if (variant.length() != 0) {
/* 272 */         locale = new Locale(language, country);
/*     */         continue;
/*     */       } 
/* 275 */       if (country.length() != 0)
/* 276 */         locale = new Locale(language); 
/*     */     } 
/* 283 */     String text = this.localMap.get(null);
/* 284 */     if (text == null) {
/* 286 */       Iterator<String> it = this.localMap.values().iterator();
/* 287 */       if (it.hasNext())
/* 288 */         return it.next(); 
/*     */     } 
/* 291 */     return text;
/*     */   }
/*     */   
/*     */   public boolean isSubsetOf(Object candidate) {
/* 324 */     if (candidate instanceof InternationalString) {
/* 325 */       InternationalString string = (InternationalString)candidate;
/* 326 */       for (Map.Entry<Locale, String> entry : this.localMap.entrySet()) {
/* 327 */         Locale locale = entry.getKey();
/* 328 */         String text = entry.getValue();
/* 329 */         if (!text.equals(string.toString(locale)))
/* 330 */           return false; 
/*     */       } 
/* 333 */     } else if (candidate instanceof CharSequence) {
/* 334 */       String string = candidate.toString();
/* 335 */       for (String text : this.localMap.values()) {
/* 336 */         if (!text.equals(string))
/* 337 */           return false; 
/*     */       } 
/*     */     } else {
/* 340 */       if (candidate instanceof Map) {
/* 341 */         Map<?, ?> map = (Map<?, ?>)candidate;
/* 342 */         return map.entrySet().containsAll(this.localMap.entrySet());
/*     */       } 
/* 344 */       return false;
/*     */     } 
/* 346 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 357 */     if (object != null && object.getClass().equals(getClass())) {
/* 358 */       GrowableInternationalString that = (GrowableInternationalString)object;
/* 359 */       return Utilities.equals(this.localMap, that.localMap);
/*     */     } 
/* 361 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 369 */     return 0x8C44C329 ^ this.localMap.hashCode();
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 376 */     in.defaultReadObject();
/* 377 */     int size = this.localMap.size();
/* 378 */     if (size == 0)
/*     */       return; 
/* 382 */     Map.Entry[] arrayOfEntry = new Map.Entry[size];
/* 383 */     arrayOfEntry = (Map.Entry[])this.localMap.entrySet().toArray((Object[])arrayOfEntry);
/* 384 */     if (size == 1) {
/* 385 */       Map.Entry<Locale, String> entry = arrayOfEntry[0];
/* 386 */       this.localMap = Collections.singletonMap(unique(entry.getKey()), entry.getValue());
/*     */     } else {
/* 388 */       this.localMap.clear();
/* 389 */       for (int i = 0; i < arrayOfEntry.length; i++) {
/* 390 */         Map.Entry<Locale, String> entry = arrayOfEntry[i];
/* 391 */         this.localMap.put(unique(entry.getKey()), entry.getValue());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\GrowableInternationalString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */