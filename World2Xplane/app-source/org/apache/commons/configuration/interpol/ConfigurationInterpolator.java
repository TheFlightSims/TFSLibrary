/*     */ package org.apache.commons.configuration.interpol;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.text.StrLookup;
/*     */ 
/*     */ public class ConfigurationInterpolator extends StrLookup {
/*     */   public static final String PREFIX_SYSPROPERTIES = "sys";
/*     */   
/*     */   public static final String PREFIX_CONSTANTS = "const";
/*     */   
/*     */   public static final String PREFIX_ENVIRONMENT = "env";
/*     */   
/*     */   private static final char PREFIX_SEPARATOR = ':';
/*     */   
/*     */   public ConfigurationInterpolator() {
/* 146 */     synchronized (globalLookups) {
/* 148 */       this.localLookups = new HashMap(globalLookups);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void registerGlobalLookup(String prefix, StrLookup lookup) {
/* 166 */     if (prefix == null)
/* 168 */       throw new IllegalArgumentException("Prefix for lookup object must not be null!"); 
/* 171 */     if (lookup == null)
/* 173 */       throw new IllegalArgumentException("Lookup object must not be null!"); 
/* 176 */     synchronized (globalLookups) {
/* 178 */       globalLookups.put(prefix, lookup);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean deregisterGlobalLookup(String prefix) {
/* 194 */     synchronized (globalLookups) {
/* 196 */       return (globalLookups.remove(prefix) != null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void registerLookup(String prefix, StrLookup lookup) {
/* 211 */     if (prefix == null)
/* 213 */       throw new IllegalArgumentException("Prefix for lookup object must not be null!"); 
/* 216 */     if (lookup == null)
/* 218 */       throw new IllegalArgumentException("Lookup object must not be null!"); 
/* 221 */     this.localLookups.put(prefix, lookup);
/*     */   }
/*     */   
/*     */   public boolean deregisterLookup(String prefix) {
/* 234 */     return (this.localLookups.remove(prefix) != null);
/*     */   }
/*     */   
/*     */   public Set prefixSet() {
/* 246 */     return this.localLookups.keySet();
/*     */   }
/*     */   
/*     */   public StrLookup getDefaultLookup() {
/* 256 */     return this.defaultLookup;
/*     */   }
/*     */   
/*     */   public void setDefaultLookup(StrLookup defaultLookup) {
/* 268 */     this.defaultLookup = defaultLookup;
/*     */   }
/*     */   
/*     */   public String lookup(String var) {
/* 285 */     if (var == null)
/* 287 */       return null; 
/* 290 */     int prefixPos = var.indexOf(':');
/* 291 */     if (prefixPos >= 0) {
/* 293 */       String prefix = var.substring(0, prefixPos);
/* 294 */       String name = var.substring(prefixPos + 1);
/* 295 */       String str1 = fetchLookupForPrefix(prefix).lookup(name);
/* 296 */       if (str1 == null && getParentInterpolator() != null)
/* 298 */         str1 = getParentInterpolator().fetchLookupForPrefix(prefix).lookup(name); 
/* 300 */       if (str1 != null)
/* 302 */         return str1; 
/*     */     } 
/* 305 */     String value = fetchNoPrefixLookup().lookup(var);
/* 306 */     if (value == null && getParentInterpolator() != null)
/* 308 */       value = getParentInterpolator().fetchNoPrefixLookup().lookup(var); 
/* 310 */     return value;
/*     */   }
/*     */   
/*     */   protected StrLookup fetchNoPrefixLookup() {
/* 323 */     return (getDefaultLookup() != null) ? getDefaultLookup() : StrLookup.noneLookup();
/*     */   }
/*     */   
/*     */   protected StrLookup fetchLookupForPrefix(String prefix) {
/* 337 */     StrLookup lookup = (StrLookup)this.localLookups.get(prefix);
/* 338 */     if (lookup == null)
/* 340 */       lookup = StrLookup.noneLookup(); 
/* 342 */     return lookup;
/*     */   }
/*     */   
/*     */   public void registerLocalLookups(ConfigurationInterpolator interpolator) {
/* 353 */     interpolator.localLookups.putAll(this.localLookups);
/*     */   }
/*     */   
/*     */   public void setParentInterpolator(ConfigurationInterpolator parentInterpolator) {
/* 365 */     this.parentInterpolator = parentInterpolator;
/*     */   }
/*     */   
/*     */   public ConfigurationInterpolator getParentInterpolator() {
/* 377 */     return this.parentInterpolator;
/*     */   }
/*     */   
/* 383 */   private static Map globalLookups = new HashMap();
/*     */   
/*     */   private Map localLookups;
/*     */   
/*     */   private StrLookup defaultLookup;
/*     */   
/*     */   private ConfigurationInterpolator parentInterpolator;
/*     */   
/*     */   static {
/* 384 */     globalLookups.put("sys", StrLookup.systemPropertiesLookup());
/* 385 */     globalLookups.put("const", new ConstantLookup());
/* 386 */     globalLookups.put("env", new EnvironmentLookup());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\interpol\ConfigurationInterpolator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */