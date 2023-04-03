/*     */ package org.apache.commons.lang.text;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class StrLookup {
/*  49 */   private static final StrLookup NONE_LOOKUP = new MapStrLookup(null);
/*     */   
/*     */   private static final StrLookup SYSTEM_PROPERTIES_LOOKUP;
/*     */   
/*     */   static {
/*  50 */     StrLookup lookup = null;
/*     */     try {
/*  52 */       lookup = new MapStrLookup(System.getProperties());
/*  53 */     } catch (SecurityException ex) {
/*  54 */       lookup = NONE_LOOKUP;
/*     */     } 
/*  56 */     SYSTEM_PROPERTIES_LOOKUP = lookup;
/*     */   }
/*     */   
/*     */   public static StrLookup noneLookup() {
/*  66 */     return NONE_LOOKUP;
/*     */   }
/*     */   
/*     */   public static StrLookup systemPropertiesLookup() {
/*  81 */     return SYSTEM_PROPERTIES_LOOKUP;
/*     */   }
/*     */   
/*     */   public static StrLookup mapLookup(Map map) {
/*  94 */     return new MapStrLookup(map);
/*     */   }
/*     */   
/*     */   public abstract String lookup(String paramString);
/*     */   
/*     */   static class MapStrLookup extends StrLookup {
/*     */     private final Map map;
/*     */     
/*     */     MapStrLookup(Map map) {
/* 145 */       this.map = map;
/*     */     }
/*     */     
/*     */     public String lookup(String key) {
/* 158 */       if (this.map == null)
/* 159 */         return null; 
/* 161 */       Object obj = this.map.get(key);
/* 162 */       if (obj == null)
/* 163 */         return null; 
/* 165 */       return obj.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\text\StrLookup.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */