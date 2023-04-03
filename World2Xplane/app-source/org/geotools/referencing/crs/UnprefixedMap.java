/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.geotools.util.DerivedMap;
/*     */ 
/*     */ final class UnprefixedMap extends DerivedMap<String, String, Object> {
/*     */   private final String prefix;
/*     */   
/*     */   private final boolean hasName;
/*     */   
/*     */   private final boolean hasAlias;
/*     */   
/*     */   public UnprefixedMap(Map<String, ?> base, String prefix) {
/*  56 */     super(base, String.class);
/*  57 */     this.prefix = prefix.trim();
/*  58 */     String nameKey = this.prefix + "name";
/*  59 */     String aliasKey = this.prefix + "alias";
/*  60 */     boolean hasName = false;
/*  61 */     boolean hasAlias = false;
/*  62 */     for (String value : base.keySet()) {
/*  63 */       String candidate = value.toString().trim();
/*  64 */       if (keyMatches(nameKey, candidate)) {
/*  65 */         hasName = true;
/*  66 */         if (hasAlias)
/*     */           break; 
/*     */         continue;
/*     */       } 
/*  68 */       if (keyMatches(aliasKey, candidate)) {
/*  69 */         hasAlias = true;
/*  70 */         if (hasName)
/*     */           break; 
/*     */       } 
/*     */     } 
/*  73 */     this.hasName = hasName;
/*  74 */     this.hasAlias = hasAlias;
/*     */   }
/*     */   
/*     */   protected String baseToDerived(String key) {
/*  86 */     int length = this.prefix.length();
/*  87 */     String textualKey = key.trim();
/*  88 */     if (textualKey.regionMatches(true, 0, this.prefix, 0, length))
/*  89 */       return textualKey.substring(length).trim(); 
/*  91 */     if (isPlainKey(textualKey))
/*  92 */       return textualKey; 
/*  94 */     return null;
/*     */   }
/*     */   
/*     */   protected String derivedToBase(String key) {
/* 104 */     String textualKey = key.trim();
/* 105 */     if (isPlainKey(textualKey))
/* 106 */       return textualKey; 
/* 108 */     return this.prefix + textualKey;
/*     */   }
/*     */   
/*     */   private boolean isPlainKey(String key) {
/* 117 */     return ((!this.hasName && keyMatches("name", key)) || (!this.hasAlias && keyMatches("alias", key)));
/*     */   }
/*     */   
/*     */   private static boolean keyMatches(String key, String candidate) {
/* 126 */     int length = key.length();
/* 127 */     return (candidate.regionMatches(true, 0, key, 0, length) && (candidate.length() == length || candidate.charAt(length) == '_'));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\UnprefixedMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */