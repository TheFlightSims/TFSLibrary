/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigMergeable;
/*     */ import com.typesafe.config.ConfigObject;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigRenderOptions;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import com.typesafe.config.ConfigValueType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ abstract class AbstractConfigObject extends AbstractConfigValue implements ConfigObject {
/*     */   private final SimpleConfig config;
/*     */   
/*     */   protected AbstractConfigObject(ConfigOrigin origin) {
/*  25 */     super(origin);
/*  26 */     this.config = new SimpleConfig(this);
/*     */   }
/*     */   
/*     */   public SimpleConfig toConfig() {
/*  31 */     return this.config;
/*     */   }
/*     */   
/*     */   public AbstractConfigObject toFallbackValue() {
/*  36 */     return this;
/*     */   }
/*     */   
/*     */   protected final AbstractConfigValue peekAssumingResolved(String key, Path originalPath) {
/*     */     try {
/*  66 */       return attemptPeekWithPartialResolve(key);
/*  67 */     } catch (com.typesafe.config.ConfigException.NotResolved e) {
/*  68 */       throw ConfigImpl.improveNotResolved(originalPath, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue peekPath(Path path, ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/*  95 */     return peekPath(this, path, context);
/*     */   }
/*     */   
/*     */   AbstractConfigValue peekPath(Path path) {
/*     */     try {
/* 104 */       return peekPath(this, path, null);
/* 105 */     } catch (NotPossibleToResolve e) {
/* 106 */       throw new ConfigException.BugOrBroken("NotPossibleToResolve happened though we had no ResolveContext in peekPath");
/*     */     } 
/*     */   }
/*     */   
/*     */   private static AbstractConfigValue peekPath(AbstractConfigObject self, Path path, ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/*     */     try {
/* 117 */       if (context != null) {
/* 121 */         AbstractConfigValue partiallyResolved = context.restrict(path).resolve(self);
/* 122 */         if (partiallyResolved instanceof AbstractConfigObject)
/* 123 */           return peekPath((AbstractConfigObject)partiallyResolved, path, null); 
/* 125 */         throw new ConfigException.BugOrBroken("resolved object to non-object " + self + " to " + partiallyResolved);
/*     */       } 
/* 131 */       Path next = path.remainder();
/* 132 */       AbstractConfigValue v = self.attemptPeekWithPartialResolve(path.first());
/* 134 */       if (next == null)
/* 135 */         return v; 
/* 137 */       if (v instanceof AbstractConfigObject)
/* 138 */         return peekPath((AbstractConfigObject)v, next, null); 
/* 140 */       return null;
/* 144 */     } catch (com.typesafe.config.ConfigException.NotResolved e) {
/* 145 */       throw ConfigImpl.improveNotResolved(path, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ConfigValueType valueType() {
/* 151 */     return ConfigValueType.OBJECT;
/*     */   }
/*     */   
/*     */   protected AbstractConfigObject newCopy(ConfigOrigin origin) {
/* 158 */     return newCopy(resolveStatus(), origin);
/*     */   }
/*     */   
/*     */   protected AbstractConfigObject constructDelayedMerge(ConfigOrigin origin, List<AbstractConfigValue> stack) {
/* 164 */     return new ConfigDelayedMergeObject(origin, stack);
/*     */   }
/*     */   
/*     */   public AbstractConfigObject withFallback(ConfigMergeable mergeable) {
/* 172 */     return (AbstractConfigObject)super.withFallback(mergeable);
/*     */   }
/*     */   
/*     */   static ConfigOrigin mergeOrigins(Collection<? extends AbstractConfigValue> stack) {
/* 177 */     if (stack.isEmpty())
/* 178 */       throw new ConfigException.BugOrBroken("can't merge origins on empty list"); 
/* 180 */     List<ConfigOrigin> origins = new ArrayList<ConfigOrigin>();
/* 181 */     ConfigOrigin firstOrigin = null;
/* 182 */     int numMerged = 0;
/* 183 */     for (AbstractConfigValue v : stack) {
/* 184 */       if (firstOrigin == null)
/* 185 */         firstOrigin = v.origin(); 
/* 187 */       if (v instanceof AbstractConfigObject && ((AbstractConfigObject)v).resolveStatus() == ResolveStatus.RESOLVED && ((ConfigObject)v).isEmpty())
/*     */         continue; 
/* 194 */       origins.add(v.origin());
/* 195 */       numMerged++;
/*     */     } 
/* 199 */     if (numMerged == 0)
/* 201 */       origins.add(firstOrigin); 
/* 204 */     return SimpleConfigOrigin.mergeOrigins(origins);
/*     */   }
/*     */   
/*     */   static ConfigOrigin mergeOrigins(AbstractConfigObject... stack) {
/* 208 */     return mergeOrigins(Arrays.asList((AbstractConfigValue[])stack));
/*     */   }
/*     */   
/*     */   private static UnsupportedOperationException weAreImmutable(String method) {
/* 224 */     return new UnsupportedOperationException("ConfigObject is immutable, you can't call Map." + method);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 230 */     throw weAreImmutable("clear");
/*     */   }
/*     */   
/*     */   public ConfigValue put(String arg0, ConfigValue arg1) {
/* 235 */     throw weAreImmutable("put");
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends String, ? extends ConfigValue> arg0) {
/* 240 */     throw weAreImmutable("putAll");
/*     */   }
/*     */   
/*     */   public ConfigValue remove(Object arg0) {
/* 245 */     throw weAreImmutable("remove");
/*     */   }
/*     */   
/*     */   public abstract AbstractConfigObject withOnlyKey(String paramString);
/*     */   
/*     */   public abstract AbstractConfigObject withoutKey(String paramString);
/*     */   
/*     */   public abstract AbstractConfigObject withValue(String paramString, ConfigValue paramConfigValue);
/*     */   
/*     */   protected abstract AbstractConfigObject withOnlyPathOrNull(Path paramPath);
/*     */   
/*     */   abstract AbstractConfigObject withOnlyPath(Path paramPath);
/*     */   
/*     */   abstract AbstractConfigObject withoutPath(Path paramPath);
/*     */   
/*     */   abstract AbstractConfigObject withValue(Path paramPath, ConfigValue paramConfigValue);
/*     */   
/*     */   protected abstract AbstractConfigValue attemptPeekWithPartialResolve(String paramString);
/*     */   
/*     */   protected abstract AbstractConfigObject newCopy(ResolveStatus paramResolveStatus, ConfigOrigin paramConfigOrigin);
/*     */   
/*     */   protected abstract AbstractConfigObject mergedWithObject(AbstractConfigObject paramAbstractConfigObject);
/*     */   
/*     */   abstract AbstractConfigObject resolveSubstitutions(ResolveContext paramResolveContext) throws AbstractConfigValue.NotPossibleToResolve;
/*     */   
/*     */   abstract AbstractConfigObject relativized(Path paramPath);
/*     */   
/*     */   public abstract AbstractConfigValue get(Object paramObject);
/*     */   
/*     */   protected abstract void render(StringBuilder paramStringBuilder, int paramInt, boolean paramBoolean, ConfigRenderOptions paramConfigRenderOptions);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\AbstractConfigObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */