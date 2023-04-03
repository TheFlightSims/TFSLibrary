/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigMergeable;
/*     */ import com.typesafe.config.ConfigObject;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigRenderOptions;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ final class ConfigDelayedMergeObject extends AbstractConfigObject implements Unmergeable, ReplaceableMergeStack {
/*     */   private final List<AbstractConfigValue> stack;
/*     */   
/*     */   ConfigDelayedMergeObject(ConfigOrigin origin, List<AbstractConfigValue> stack) {
/*  27 */     super(origin);
/*  28 */     this.stack = stack;
/*  30 */     if (stack.isEmpty())
/*  31 */       throw new ConfigException.BugOrBroken("creating empty delayed merge object"); 
/*  33 */     if (!(stack.get(0) instanceof AbstractConfigObject))
/*  34 */       throw new ConfigException.BugOrBroken("created a delayed merge object not guaranteed to be an object"); 
/*  37 */     for (AbstractConfigValue v : stack) {
/*  38 */       if (v instanceof ConfigDelayedMerge || v instanceof ConfigDelayedMergeObject)
/*  39 */         throw new ConfigException.BugOrBroken("placed nested DelayedMerge in a ConfigDelayedMergeObject, should have consolidated stack"); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ConfigDelayedMergeObject newCopy(ResolveStatus status, ConfigOrigin origin) {
/*  46 */     if (status != resolveStatus())
/*  47 */       throw new ConfigException.BugOrBroken("attempt to create resolved ConfigDelayedMergeObject"); 
/*  49 */     return new ConfigDelayedMergeObject(origin, this.stack);
/*     */   }
/*     */   
/*     */   AbstractConfigObject resolveSubstitutions(ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/*  55 */     AbstractConfigValue merged = ConfigDelayedMerge.resolveSubstitutions(this, this.stack, context);
/*  56 */     if (merged instanceof AbstractConfigObject)
/*  57 */       return (AbstractConfigObject)merged; 
/*  59 */     throw new ConfigException.BugOrBroken("somehow brokenly merged an object and didn't get an object, got " + merged);
/*     */   }
/*     */   
/*     */   public ResolveReplacer makeReplacer(final int skipping) {
/*  66 */     return new ResolveReplacer() {
/*     */         protected AbstractConfigValue makeReplacement(ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/*  70 */           return ConfigDelayedMerge.makeReplacement(context, ConfigDelayedMergeObject.this.stack, skipping);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   ResolveStatus resolveStatus() {
/*  77 */     return ResolveStatus.UNRESOLVED;
/*     */   }
/*     */   
/*     */   ConfigDelayedMergeObject relativized(Path prefix) {
/*  82 */     List<AbstractConfigValue> newStack = new ArrayList<AbstractConfigValue>();
/*  83 */     for (AbstractConfigValue o : this.stack)
/*  84 */       newStack.add(o.relativized(prefix)); 
/*  86 */     return new ConfigDelayedMergeObject(origin(), newStack);
/*     */   }
/*     */   
/*     */   protected boolean ignoresFallbacks() {
/*  91 */     return ConfigDelayedMerge.stackIgnoresFallbacks(this.stack);
/*     */   }
/*     */   
/*     */   protected final ConfigDelayedMergeObject mergedWithTheUnmergeable(Unmergeable fallback) {
/*  96 */     requireNotIgnoringFallbacks();
/*  98 */     return (ConfigDelayedMergeObject)mergedWithTheUnmergeable(this.stack, fallback);
/*     */   }
/*     */   
/*     */   protected final ConfigDelayedMergeObject mergedWithObject(AbstractConfigObject fallback) {
/* 103 */     return mergedWithNonObject(fallback);
/*     */   }
/*     */   
/*     */   protected final ConfigDelayedMergeObject mergedWithNonObject(AbstractConfigValue fallback) {
/* 108 */     requireNotIgnoringFallbacks();
/* 110 */     return (ConfigDelayedMergeObject)mergedWithNonObject(this.stack, fallback);
/*     */   }
/*     */   
/*     */   public ConfigDelayedMergeObject withFallback(ConfigMergeable mergeable) {
/* 115 */     return (ConfigDelayedMergeObject)super.withFallback(mergeable);
/*     */   }
/*     */   
/*     */   public ConfigDelayedMergeObject withOnlyKey(String key) {
/* 120 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public ConfigDelayedMergeObject withoutKey(String key) {
/* 125 */     throw notResolved();
/*     */   }
/*     */   
/*     */   protected AbstractConfigObject withOnlyPathOrNull(Path path) {
/* 130 */     throw notResolved();
/*     */   }
/*     */   
/*     */   AbstractConfigObject withOnlyPath(Path path) {
/* 135 */     throw notResolved();
/*     */   }
/*     */   
/*     */   AbstractConfigObject withoutPath(Path path) {
/* 140 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public ConfigDelayedMergeObject withValue(String key, ConfigValue value) {
/* 145 */     throw notResolved();
/*     */   }
/*     */   
/*     */   ConfigDelayedMergeObject withValue(Path path, ConfigValue value) {
/* 150 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public Collection<AbstractConfigValue> unmergedValues() {
/* 155 */     return this.stack;
/*     */   }
/*     */   
/*     */   protected boolean canEqual(Object other) {
/* 160 */     return other instanceof ConfigDelayedMergeObject;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 166 */     if (other instanceof ConfigDelayedMergeObject)
/* 167 */       return (canEqual(other) && this.stack.equals(((ConfigDelayedMergeObject)other).stack)); 
/* 171 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 178 */     return this.stack.hashCode();
/*     */   }
/*     */   
/*     */   protected void render(StringBuilder sb, int indent, boolean atRoot, String atKey, ConfigRenderOptions options) {
/* 183 */     ConfigDelayedMerge.render(this.stack, sb, indent, atRoot, atKey, options);
/*     */   }
/*     */   
/*     */   protected void render(StringBuilder sb, int indent, boolean atRoot, ConfigRenderOptions options) {
/* 188 */     render(sb, indent, atRoot, (String)null, options);
/*     */   }
/*     */   
/*     */   private static ConfigException notResolved() {
/* 192 */     return (ConfigException)new ConfigException.NotResolved("need to Config#resolve() before using this object, see the API docs for Config#resolve()");
/*     */   }
/*     */   
/*     */   public Map<String, Object> unwrapped() {
/* 198 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public AbstractConfigValue get(Object key) {
/* 203 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 208 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 213 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<String, ConfigValue>> entrySet() {
/* 218 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 223 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public Set<String> keySet() {
/* 228 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public int size() {
/* 233 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public Collection<ConfigValue> values() {
/* 238 */     throw notResolved();
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue attemptPeekWithPartialResolve(String key) {
/* 255 */     for (AbstractConfigValue layer : this.stack) {
/* 256 */       if (layer instanceof AbstractConfigObject) {
/* 257 */         AbstractConfigObject objectLayer = (AbstractConfigObject)layer;
/* 258 */         AbstractConfigValue v = objectLayer.attemptPeekWithPartialResolve(key);
/* 259 */         if (v != null) {
/* 260 */           if (v.ignoresFallbacks())
/* 263 */             return v; 
/*     */           continue;
/*     */         } 
/* 272 */         if (layer instanceof Unmergeable)
/* 277 */           throw new ConfigException.BugOrBroken("should not be reached: unmergeable object returned null value"); 
/*     */         continue;
/*     */       } 
/* 285 */       if (layer instanceof Unmergeable)
/* 286 */         throw new ConfigException.NotResolved("Key '" + key + "' is not available at '" + origin().description() + "' because value at '" + layer.origin().description() + "' has not been resolved and may turn out to contain or hide '" + key + "'." + " Be sure to Config#resolve() before using a config object."); 
/* 292 */       if (layer.resolveStatus() == ResolveStatus.UNRESOLVED) {
/* 297 */         if (!(layer instanceof com.typesafe.config.ConfigList))
/* 298 */           throw new ConfigException.BugOrBroken("Expecting a list here, not " + layer); 
/* 301 */         return null;
/*     */       } 
/* 308 */       if (!layer.ignoresFallbacks())
/* 309 */         throw new ConfigException.BugOrBroken("resolved non-object should ignore fallbacks"); 
/* 312 */       return null;
/*     */     } 
/* 318 */     throw new ConfigException.BugOrBroken("Delayed merge stack does not contain any unmergeable values");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigDelayedMergeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */