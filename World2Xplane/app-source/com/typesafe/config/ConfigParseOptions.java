/*     */ package com.typesafe.config;
/*     */ 
/*     */ public final class ConfigParseOptions {
/*     */   final ConfigSyntax syntax;
/*     */   
/*     */   final String originDescription;
/*     */   
/*     */   final boolean allowMissing;
/*     */   
/*     */   final ConfigIncluder includer;
/*     */   
/*     */   final ClassLoader classLoader;
/*     */   
/*     */   private ConfigParseOptions(ConfigSyntax syntax, String originDescription, boolean allowMissing, ConfigIncluder includer, ClassLoader classLoader) {
/*  32 */     this.syntax = syntax;
/*  33 */     this.originDescription = originDescription;
/*  34 */     this.allowMissing = allowMissing;
/*  35 */     this.includer = includer;
/*  36 */     this.classLoader = classLoader;
/*     */   }
/*     */   
/*     */   public static ConfigParseOptions defaults() {
/*  40 */     return new ConfigParseOptions(null, null, true, null, null);
/*     */   }
/*     */   
/*     */   public ConfigParseOptions setSyntax(ConfigSyntax syntax) {
/*  52 */     if (this.syntax == syntax)
/*  53 */       return this; 
/*  55 */     return new ConfigParseOptions(syntax, this.originDescription, this.allowMissing, this.includer, this.classLoader);
/*     */   }
/*     */   
/*     */   public ConfigSyntax getSyntax() {
/*  60 */     return this.syntax;
/*     */   }
/*     */   
/*     */   public ConfigParseOptions setOriginDescription(String originDescription) {
/*  75 */     if (this.originDescription == originDescription)
/*  76 */       return this; 
/*  77 */     if (this.originDescription != null && originDescription != null && this.originDescription.equals(originDescription))
/*  79 */       return this; 
/*  81 */     return new ConfigParseOptions(this.syntax, originDescription, this.allowMissing, this.includer, this.classLoader);
/*     */   }
/*     */   
/*     */   public String getOriginDescription() {
/*  86 */     return this.originDescription;
/*     */   }
/*     */   
/*     */   ConfigParseOptions withFallbackOriginDescription(String originDescription) {
/*  91 */     if (this.originDescription == null)
/*  92 */       return setOriginDescription(originDescription); 
/*  94 */     return this;
/*     */   }
/*     */   
/*     */   public ConfigParseOptions setAllowMissing(boolean allowMissing) {
/* 106 */     if (this.allowMissing == allowMissing)
/* 107 */       return this; 
/* 109 */     return new ConfigParseOptions(this.syntax, this.originDescription, allowMissing, this.includer, this.classLoader);
/*     */   }
/*     */   
/*     */   public boolean getAllowMissing() {
/* 114 */     return this.allowMissing;
/*     */   }
/*     */   
/*     */   public ConfigParseOptions setIncluder(ConfigIncluder includer) {
/* 124 */     if (this.includer == includer)
/* 125 */       return this; 
/* 127 */     return new ConfigParseOptions(this.syntax, this.originDescription, this.allowMissing, includer, this.classLoader);
/*     */   }
/*     */   
/*     */   public ConfigParseOptions prependIncluder(ConfigIncluder includer) {
/* 132 */     if (this.includer == includer)
/* 133 */       return this; 
/* 134 */     if (this.includer != null)
/* 135 */       return setIncluder(includer.withFallback(this.includer)); 
/* 137 */     return setIncluder(includer);
/*     */   }
/*     */   
/*     */   public ConfigParseOptions appendIncluder(ConfigIncluder includer) {
/* 141 */     if (this.includer == includer)
/* 142 */       return this; 
/* 143 */     if (this.includer != null)
/* 144 */       return setIncluder(this.includer.withFallback(includer)); 
/* 146 */     return setIncluder(includer);
/*     */   }
/*     */   
/*     */   public ConfigIncluder getIncluder() {
/* 150 */     return this.includer;
/*     */   }
/*     */   
/*     */   public ConfigParseOptions setClassLoader(ClassLoader loader) {
/* 163 */     if (this.classLoader == loader)
/* 164 */       return this; 
/* 166 */     return new ConfigParseOptions(this.syntax, this.originDescription, this.allowMissing, this.includer, loader);
/*     */   }
/*     */   
/*     */   public ClassLoader getClassLoader() {
/* 178 */     if (this.classLoader == null)
/* 179 */       return Thread.currentThread().getContextClassLoader(); 
/* 181 */     return this.classLoader;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigParseOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */