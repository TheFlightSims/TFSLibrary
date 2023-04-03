/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigIncluder;
/*     */ import com.typesafe.config.ConfigMergeable;
/*     */ import com.typesafe.config.ConfigObject;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigParseOptions;
/*     */ import com.typesafe.config.ConfigParseable;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.concurrent.Callable;
/*     */ 
/*     */ public class ConfigImpl {
/*     */   private static class LoaderCache {
/*  35 */     private Config currentSystemProperties = null;
/*     */     
/*  36 */     private ClassLoader currentLoader = null;
/*     */     
/*  37 */     private Map<String, Config> cache = new HashMap<String, Config>();
/*     */     
/*     */     synchronized Config getOrElseUpdate(ClassLoader loader, String key, Callable<Config> updater) {
/*  43 */       if (loader != this.currentLoader) {
/*  45 */         this.cache.clear();
/*  46 */         this.currentLoader = loader;
/*     */       } 
/*  49 */       Config systemProperties = ConfigImpl.systemPropertiesAsConfig();
/*  50 */       if (systemProperties != this.currentSystemProperties) {
/*  51 */         this.cache.clear();
/*  52 */         this.currentSystemProperties = systemProperties;
/*     */       } 
/*  55 */       Config config = this.cache.get(key);
/*  56 */       if (config == null) {
/*     */         try {
/*  58 */           config = updater.call();
/*  59 */         } catch (RuntimeException e) {
/*  60 */           throw e;
/*  61 */         } catch (Exception e) {
/*  62 */           throw new ConfigException.Generic(e.getMessage(), e);
/*     */         } 
/*  64 */         if (config == null)
/*  65 */           throw new ConfigException.BugOrBroken("null config from cache updater"); 
/*  66 */         this.cache.put(key, config);
/*     */       } 
/*  69 */       return config;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class LoaderCacheHolder {
/*  74 */     static final ConfigImpl.LoaderCache cache = new ConfigImpl.LoaderCache();
/*     */   }
/*     */   
/*     */   public static Config computeCachedConfig(ClassLoader loader, String key, Callable<Config> updater) {
/*     */     LoaderCache cache;
/*     */     try {
/*  82 */       cache = LoaderCacheHolder.cache;
/*  83 */     } catch (ExceptionInInitializerError e) {
/*  84 */       throw ConfigImplUtil.extractInitializerError(e);
/*     */     } 
/*  86 */     return cache.getOrElseUpdate(loader, key, updater);
/*     */   }
/*     */   
/*     */   static class FileNameSource implements SimpleIncluder.NameSource {
/*     */     public ConfigParseable nameToParseable(String name, ConfigParseOptions parseOptions) {
/*  93 */       return Parseable.newFile(new File(name), parseOptions);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ClasspathNameSource implements SimpleIncluder.NameSource {
/*     */     public ConfigParseable nameToParseable(String name, ConfigParseOptions parseOptions) {
/* 100 */       return Parseable.newResources(name, parseOptions);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ClasspathNameSourceWithClass implements SimpleIncluder.NameSource {
/*     */     private final Class<?> klass;
/*     */     
/*     */     public ClasspathNameSourceWithClass(Class<?> klass) {
/* 108 */       this.klass = klass;
/*     */     }
/*     */     
/*     */     public ConfigParseable nameToParseable(String name, ConfigParseOptions parseOptions) {
/* 113 */       return Parseable.newResources(this.klass, name, parseOptions);
/*     */     }
/*     */   }
/*     */   
/*     */   public static ConfigObject parseResourcesAnySyntax(Class<?> klass, String resourceBasename, ConfigParseOptions baseOptions) {
/* 120 */     SimpleIncluder.NameSource source = new ClasspathNameSourceWithClass(klass);
/* 121 */     return SimpleIncluder.fromBasename(source, resourceBasename, baseOptions);
/*     */   }
/*     */   
/*     */   public static ConfigObject parseResourcesAnySyntax(String resourceBasename, ConfigParseOptions baseOptions) {
/* 127 */     SimpleIncluder.NameSource source = new ClasspathNameSource();
/* 128 */     return SimpleIncluder.fromBasename(source, resourceBasename, baseOptions);
/*     */   }
/*     */   
/*     */   public static ConfigObject parseFileAnySyntax(File basename, ConfigParseOptions baseOptions) {
/* 133 */     SimpleIncluder.NameSource source = new FileNameSource();
/* 134 */     return SimpleIncluder.fromBasename(source, basename.getPath(), baseOptions);
/*     */   }
/*     */   
/*     */   static AbstractConfigObject emptyObject(String originDescription) {
/* 138 */     ConfigOrigin origin = (originDescription != null) ? SimpleConfigOrigin.newSimple(originDescription) : null;
/* 140 */     return emptyObject(origin);
/*     */   }
/*     */   
/*     */   public static Config emptyConfig(String originDescription) {
/* 145 */     return emptyObject(originDescription).toConfig();
/*     */   }
/*     */   
/*     */   static AbstractConfigObject empty(ConfigOrigin origin) {
/* 149 */     return emptyObject(origin);
/*     */   }
/*     */   
/* 153 */   private static final ConfigOrigin defaultValueOrigin = SimpleConfigOrigin.newSimple("hardcoded value");
/*     */   
/* 155 */   private static final ConfigBoolean defaultTrueValue = new ConfigBoolean(defaultValueOrigin, true);
/*     */   
/* 157 */   private static final ConfigBoolean defaultFalseValue = new ConfigBoolean(defaultValueOrigin, false);
/*     */   
/* 159 */   private static final ConfigNull defaultNullValue = new ConfigNull(defaultValueOrigin);
/*     */   
/* 161 */   private static final SimpleConfigList defaultEmptyList = new SimpleConfigList(defaultValueOrigin, Collections.emptyList());
/*     */   
/* 163 */   private static final SimpleConfigObject defaultEmptyObject = SimpleConfigObject.empty(defaultValueOrigin);
/*     */   
/*     */   private static SimpleConfigList emptyList(ConfigOrigin origin) {
/* 167 */     if (origin == null || origin == defaultValueOrigin)
/* 168 */       return defaultEmptyList; 
/* 170 */     return new SimpleConfigList(origin, Collections.emptyList());
/*     */   }
/*     */   
/*     */   private static AbstractConfigObject emptyObject(ConfigOrigin origin) {
/* 177 */     if (origin == defaultValueOrigin)
/* 178 */       return defaultEmptyObject; 
/* 180 */     return SimpleConfigObject.empty(origin);
/*     */   }
/*     */   
/*     */   private static ConfigOrigin valueOrigin(String originDescription) {
/* 184 */     if (originDescription == null)
/* 185 */       return defaultValueOrigin; 
/* 187 */     return SimpleConfigOrigin.newSimple(originDescription);
/*     */   }
/*     */   
/*     */   public static ConfigValue fromAnyRef(Object object, String originDescription) {
/* 192 */     ConfigOrigin origin = valueOrigin(originDescription);
/* 193 */     return fromAnyRef(object, origin, FromMapMode.KEYS_ARE_KEYS);
/*     */   }
/*     */   
/*     */   public static ConfigObject fromPathMap(Map<String, ? extends Object> pathMap, String originDescription) {
/* 199 */     ConfigOrigin origin = valueOrigin(originDescription);
/* 200 */     return (ConfigObject)fromAnyRef(pathMap, origin, FromMapMode.KEYS_ARE_PATHS);
/*     */   }
/*     */   
/*     */   static AbstractConfigValue fromAnyRef(Object object, ConfigOrigin origin, FromMapMode mapMode) {
/* 206 */     if (origin == null)
/* 207 */       throw new ConfigException.BugOrBroken("origin not supposed to be null"); 
/* 210 */     if (object == null) {
/* 211 */       if (origin != defaultValueOrigin)
/* 212 */         return new ConfigNull(origin); 
/* 214 */       return defaultNullValue;
/*     */     } 
/* 215 */     if (object instanceof Boolean) {
/* 216 */       if (origin != defaultValueOrigin)
/* 217 */         return new ConfigBoolean(origin, ((Boolean)object).booleanValue()); 
/* 218 */       if (((Boolean)object).booleanValue())
/* 219 */         return defaultTrueValue; 
/* 221 */       return defaultFalseValue;
/*     */     } 
/* 223 */     if (object instanceof String)
/* 224 */       return new ConfigString(origin, (String)object); 
/* 225 */     if (object instanceof Number) {
/* 231 */       if (object instanceof Double)
/* 232 */         return new ConfigDouble(origin, ((Double)object).doubleValue(), null); 
/* 233 */       if (object instanceof Integer)
/* 234 */         return new ConfigInt(origin, ((Integer)object).intValue(), null); 
/* 235 */       if (object instanceof Long)
/* 236 */         return new ConfigLong(origin, ((Long)object).longValue(), null); 
/* 238 */       return ConfigNumber.newNumber(origin, ((Number)object).doubleValue(), (String)null);
/*     */     } 
/* 241 */     if (object instanceof Map) {
/* 242 */       if (((Map)object).isEmpty())
/* 243 */         return emptyObject(origin); 
/* 245 */       if (mapMode == FromMapMode.KEYS_ARE_KEYS) {
/* 246 */         Map<String, AbstractConfigValue> values = new HashMap<String, AbstractConfigValue>();
/* 247 */         for (Map.Entry<?, ?> entry : (Iterable<Map.Entry<?, ?>>)((Map)object).entrySet()) {
/* 248 */           Object key = entry.getKey();
/* 249 */           if (!(key instanceof String))
/* 250 */             throw new ConfigException.BugOrBroken("bug in method caller: not valid to create ConfigObject from map with non-String key: " + key); 
/* 253 */           AbstractConfigValue value = fromAnyRef(entry.getValue(), origin, mapMode);
/* 255 */           values.put((String)key, value);
/*     */         } 
/* 258 */         return new SimpleConfigObject(origin, values);
/*     */       } 
/* 260 */       return PropertiesParser.fromPathMap(origin, (Map<?, ?>)object);
/*     */     } 
/* 262 */     if (object instanceof Iterable) {
/* 263 */       Iterator<?> i = ((Iterable)object).iterator();
/* 264 */       if (!i.hasNext())
/* 265 */         return emptyList(origin); 
/* 267 */       List<AbstractConfigValue> values = new ArrayList<AbstractConfigValue>();
/* 268 */       while (i.hasNext()) {
/* 269 */         AbstractConfigValue v = fromAnyRef(i.next(), origin, mapMode);
/* 270 */         values.add(v);
/*     */       } 
/* 273 */       return new SimpleConfigList(origin, values);
/*     */     } 
/* 275 */     throw new ConfigException.BugOrBroken("bug in method caller: not valid to create ConfigValue from: " + object);
/*     */   }
/*     */   
/*     */   private static class DefaultIncluderHolder {
/* 282 */     static final ConfigIncluder defaultIncluder = new SimpleIncluder(null);
/*     */   }
/*     */   
/*     */   static ConfigIncluder defaultIncluder() {
/*     */     try {
/* 287 */       return DefaultIncluderHolder.defaultIncluder;
/* 288 */     } catch (ExceptionInInitializerError e) {
/* 289 */       throw ConfigImplUtil.extractInitializerError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Properties getSystemProperties() {
/* 295 */     Properties systemProperties = System.getProperties();
/* 296 */     Properties systemPropertiesCopy = new Properties();
/* 297 */     synchronized (systemProperties) {
/* 298 */       systemPropertiesCopy.putAll(systemProperties);
/*     */     } 
/* 300 */     return systemPropertiesCopy;
/*     */   }
/*     */   
/*     */   private static AbstractConfigObject loadSystemProperties() {
/* 304 */     return (AbstractConfigObject)Parseable.newProperties(getSystemProperties(), ConfigParseOptions.defaults().setOriginDescription("system properties")).parse();
/*     */   }
/*     */   
/*     */   private static class SystemPropertiesHolder {
/* 310 */     static volatile AbstractConfigObject systemProperties = ConfigImpl.loadSystemProperties();
/*     */   }
/*     */   
/*     */   static AbstractConfigObject systemPropertiesAsConfigObject() {
/*     */     try {
/* 315 */       return SystemPropertiesHolder.systemProperties;
/* 316 */     } catch (ExceptionInInitializerError e) {
/* 317 */       throw ConfigImplUtil.extractInitializerError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Config systemPropertiesAsConfig() {
/* 323 */     return systemPropertiesAsConfigObject().toConfig();
/*     */   }
/*     */   
/*     */   public static void reloadSystemPropertiesConfig() {
/* 330 */     SystemPropertiesHolder.systemProperties = loadSystemProperties();
/*     */   }
/*     */   
/*     */   private static AbstractConfigObject loadEnvVariables() {
/* 334 */     Map<String, String> env = System.getenv();
/* 335 */     Map<String, AbstractConfigValue> m = new HashMap<String, AbstractConfigValue>();
/* 336 */     for (Map.Entry<String, String> entry : env.entrySet()) {
/* 337 */       String key = entry.getKey();
/* 338 */       m.put(key, new ConfigString(SimpleConfigOrigin.newSimple("env var " + key), entry.getValue()));
/*     */     } 
/* 342 */     return new SimpleConfigObject(SimpleConfigOrigin.newSimple("env variables"), m, ResolveStatus.RESOLVED, false);
/*     */   }
/*     */   
/*     */   private static class EnvVariablesHolder {
/* 347 */     static final AbstractConfigObject envVariables = ConfigImpl.loadEnvVariables();
/*     */   }
/*     */   
/*     */   static AbstractConfigObject envVariablesAsConfigObject() {
/*     */     try {
/* 352 */       return EnvVariablesHolder.envVariables;
/* 353 */     } catch (ExceptionInInitializerError e) {
/* 354 */       throw ConfigImplUtil.extractInitializerError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Config envVariablesAsConfig() {
/* 360 */     return envVariablesAsConfigObject().toConfig();
/*     */   }
/*     */   
/*     */   public static Config defaultReference(final ClassLoader loader) {
/* 365 */     return computeCachedConfig(loader, "defaultReference", new Callable<Config>() {
/*     */           public Config call() {
/* 368 */             Config unresolvedResources = Parseable.newResources("reference.conf", ConfigParseOptions.defaults().setClassLoader(loader)).parse().toConfig();
/* 372 */             return ConfigImpl.systemPropertiesAsConfig().withFallback((ConfigMergeable)unresolvedResources).resolve();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private static class DebugHolder {
/* 378 */     private static String LOADS = "loads";
/*     */     
/*     */     private static Map<String, Boolean> loadDiagnostics() {
/* 381 */       Map<String, Boolean> result = new HashMap<String, Boolean>();
/* 382 */       result.put(LOADS, Boolean.valueOf(false));
/* 385 */       String s = System.getProperty("config.trace");
/* 386 */       if (s == null)
/* 387 */         return result; 
/* 389 */       String[] keys = s.split(",");
/* 390 */       for (String k : keys) {
/* 391 */         if (k.equals(LOADS)) {
/* 392 */           result.put(LOADS, Boolean.valueOf(true));
/*     */         } else {
/* 394 */           System.err.println("config.trace property contains unknown trace topic '" + k + "'");
/*     */         } 
/*     */       } 
/* 398 */       return result;
/*     */     }
/*     */     
/* 402 */     private static final Map<String, Boolean> diagnostics = loadDiagnostics();
/*     */     
/* 404 */     private static final boolean traceLoadsEnabled = ((Boolean)diagnostics.get(LOADS)).booleanValue();
/*     */     
/*     */     static boolean traceLoadsEnabled() {
/* 407 */       return traceLoadsEnabled;
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean traceLoadsEnabled() {
/*     */     try {
/* 414 */       return DebugHolder.traceLoadsEnabled();
/* 415 */     } catch (ExceptionInInitializerError e) {
/* 416 */       throw ConfigImplUtil.extractInitializerError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void trace(String message) {
/* 421 */     System.err.println(message);
/*     */   }
/*     */   
/*     */   static ConfigException.NotResolved improveNotResolved(Path what, ConfigException.NotResolved original) {
/* 430 */     String newMessage = what.render() + " has not been resolved, you need to call Config#resolve()," + " see API docs for Config#resolve()";
/* 433 */     if (newMessage.equals(original.getMessage()))
/* 434 */       return original; 
/* 436 */     return new ConfigException.NotResolved(newMessage, (Throwable)original);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */