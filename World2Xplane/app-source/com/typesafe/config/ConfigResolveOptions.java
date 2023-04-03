/*     */ package com.typesafe.config;
/*     */ 
/*     */ public final class ConfigResolveOptions {
/*     */   private final boolean useSystemEnvironment;
/*     */   
/*     */   private final boolean allowUnresolved;
/*     */   
/*     */   private ConfigResolveOptions(boolean useSystemEnvironment, boolean allowUnresolved) {
/*  34 */     this.useSystemEnvironment = useSystemEnvironment;
/*  35 */     this.allowUnresolved = allowUnresolved;
/*     */   }
/*     */   
/*     */   public static ConfigResolveOptions defaults() {
/*  45 */     return new ConfigResolveOptions(true, false);
/*     */   }
/*     */   
/*     */   public static ConfigResolveOptions noSystem() {
/*  55 */     return defaults().setUseSystemEnvironment(false);
/*     */   }
/*     */   
/*     */   public ConfigResolveOptions setUseSystemEnvironment(boolean value) {
/*  67 */     return new ConfigResolveOptions(value, this.allowUnresolved);
/*     */   }
/*     */   
/*     */   public boolean getUseSystemEnvironment() {
/*  78 */     return this.useSystemEnvironment;
/*     */   }
/*     */   
/*     */   public ConfigResolveOptions setAllowUnresolved(boolean value) {
/*  94 */     return new ConfigResolveOptions(this.useSystemEnvironment, value);
/*     */   }
/*     */   
/*     */   public boolean getAllowUnresolved() {
/* 105 */     return this.allowUnresolved;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigResolveOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */