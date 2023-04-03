/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigFactory;
/*     */ import com.typesafe.config.ConfigIncludeContext;
/*     */ import com.typesafe.config.ConfigIncluder;
/*     */ import com.typesafe.config.ConfigIncluderClasspath;
/*     */ import com.typesafe.config.ConfigIncluderFile;
/*     */ import com.typesafe.config.ConfigIncluderURL;
/*     */ import com.typesafe.config.ConfigMergeable;
/*     */ import com.typesafe.config.ConfigObject;
/*     */ import com.typesafe.config.ConfigParseOptions;
/*     */ import com.typesafe.config.ConfigParseable;
/*     */ import com.typesafe.config.ConfigSyntax;
/*     */ import java.io.File;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ class SimpleIncluder implements FullIncluder {
/*     */   private ConfigIncluder fallback;
/*     */   
/*     */   SimpleIncluder(ConfigIncluder fallback) {
/*  29 */     this.fallback = fallback;
/*     */   }
/*     */   
/*     */   static ConfigParseOptions clearForInclude(ConfigParseOptions options) {
/*  36 */     return options.setSyntax(null).setOriginDescription(null).setAllowMissing(true);
/*     */   }
/*     */   
/*     */   public ConfigObject include(ConfigIncludeContext context, String name) {
/*  42 */     ConfigObject obj = includeWithoutFallback(context, name);
/*  46 */     if (this.fallback != null)
/*  47 */       return obj.withFallback((ConfigMergeable)this.fallback.include(context, name)); 
/*  49 */     return obj;
/*     */   }
/*     */   
/*     */   static ConfigObject includeWithoutFallback(ConfigIncludeContext context, String name) {
/*     */     URL uRL;
/*     */     try {
/*  60 */       uRL = new URL(name);
/*  61 */     } catch (MalformedURLException e) {
/*  62 */       uRL = null;
/*     */     } 
/*  65 */     if (uRL != null)
/*  66 */       return includeURLWithoutFallback(context, uRL); 
/*  68 */     NameSource source = new RelativeNameSource(context);
/*  69 */     return fromBasename(source, name, context.parseOptions());
/*     */   }
/*     */   
/*     */   public ConfigObject includeURL(ConfigIncludeContext context, URL url) {
/*  75 */     ConfigObject obj = includeURLWithoutFallback(context, url);
/*  79 */     if (this.fallback != null && this.fallback instanceof ConfigIncluderURL)
/*  80 */       return obj.withFallback((ConfigMergeable)((ConfigIncluderURL)this.fallback).includeURL(context, url)); 
/*  82 */     return obj;
/*     */   }
/*     */   
/*     */   static ConfigObject includeURLWithoutFallback(ConfigIncludeContext context, URL url) {
/*  87 */     return ConfigFactory.parseURL(url, context.parseOptions()).root();
/*     */   }
/*     */   
/*     */   public ConfigObject includeFile(ConfigIncludeContext context, File file) {
/*  92 */     ConfigObject obj = includeFileWithoutFallback(context, file);
/*  96 */     if (this.fallback != null && this.fallback instanceof ConfigIncluderFile)
/*  97 */       return obj.withFallback((ConfigMergeable)((ConfigIncluderFile)this.fallback).includeFile(context, file)); 
/*  99 */     return obj;
/*     */   }
/*     */   
/*     */   static ConfigObject includeFileWithoutFallback(ConfigIncludeContext context, File file) {
/* 104 */     return ConfigFactory.parseFileAnySyntax(file, context.parseOptions()).root();
/*     */   }
/*     */   
/*     */   public ConfigObject includeResources(ConfigIncludeContext context, String resource) {
/* 109 */     ConfigObject obj = includeResourceWithoutFallback(context, resource);
/* 113 */     if (this.fallback != null && this.fallback instanceof ConfigIncluderClasspath)
/* 114 */       return obj.withFallback((ConfigMergeable)((ConfigIncluderClasspath)this.fallback).includeResources(context, resource)); 
/* 117 */     return obj;
/*     */   }
/*     */   
/*     */   static ConfigObject includeResourceWithoutFallback(ConfigIncludeContext context, String resource) {
/* 123 */     return ConfigFactory.parseResourcesAnySyntax(resource, context.parseOptions()).root();
/*     */   }
/*     */   
/*     */   public ConfigIncluder withFallback(ConfigIncluder fallback) {
/* 128 */     if (this == fallback)
/* 129 */       throw new ConfigException.BugOrBroken("trying to create includer cycle"); 
/* 130 */     if (this.fallback == fallback)
/* 131 */       return this; 
/* 132 */     if (this.fallback != null)
/* 133 */       return new SimpleIncluder(this.fallback.withFallback(fallback)); 
/* 135 */     return new SimpleIncluder(fallback);
/*     */   }
/*     */   
/*     */   static interface NameSource {
/*     */     ConfigParseable nameToParseable(String param1String, ConfigParseOptions param1ConfigParseOptions);
/*     */   }
/*     */   
/*     */   private static class RelativeNameSource implements NameSource {
/*     */     private final ConfigIncludeContext context;
/*     */     
/*     */     RelativeNameSource(ConfigIncludeContext context) {
/* 147 */       this.context = context;
/*     */     }
/*     */     
/*     */     public ConfigParseable nameToParseable(String name, ConfigParseOptions options) {
/* 152 */       ConfigParseable p = this.context.relativeTo(name);
/* 153 */       if (p == null)
/* 155 */         return Parseable.newNotFound(name, "include was not found: '" + name + "'", options); 
/* 158 */       return p;
/*     */     }
/*     */   }
/*     */   
/*     */   static ConfigObject fromBasename(NameSource source, String name, ConfigParseOptions options) {
/*     */     ConfigObject obj;
/* 169 */     if (name.endsWith(".conf") || name.endsWith(".json") || name.endsWith(".properties")) {
/* 170 */       ConfigParseable p = source.nameToParseable(name, options);
/* 172 */       obj = p.parse(p.options().setAllowMissing(options.getAllowMissing()));
/*     */     } else {
/* 174 */       ConfigParseable confHandle = source.nameToParseable(name + ".conf", options);
/* 175 */       ConfigParseable jsonHandle = source.nameToParseable(name + ".json", options);
/* 176 */       ConfigParseable propsHandle = source.nameToParseable(name + ".properties", options);
/* 177 */       boolean gotSomething = false;
/* 178 */       List<ConfigException.IO> fails = new ArrayList<ConfigException.IO>();
/* 180 */       ConfigSyntax syntax = options.getSyntax();
/* 182 */       obj = SimpleConfigObject.empty(SimpleConfigOrigin.newSimple(name));
/* 183 */       if (syntax == null || syntax == ConfigSyntax.CONF)
/*     */         try {
/* 185 */           obj = confHandle.parse(confHandle.options().setAllowMissing(false).setSyntax(ConfigSyntax.CONF));
/* 187 */           gotSomething = true;
/* 188 */         } catch (com.typesafe.config.ConfigException.IO e) {
/* 189 */           fails.add(e);
/*     */         }  
/* 193 */       if (syntax == null || syntax == ConfigSyntax.JSON)
/*     */         try {
/* 195 */           ConfigObject parsed = jsonHandle.parse(jsonHandle.options().setAllowMissing(false).setSyntax(ConfigSyntax.JSON));
/* 197 */           obj = obj.withFallback((ConfigMergeable)parsed);
/* 198 */           gotSomething = true;
/* 199 */         } catch (com.typesafe.config.ConfigException.IO e) {
/* 200 */           fails.add(e);
/*     */         }  
/* 204 */       if (syntax == null || syntax == ConfigSyntax.PROPERTIES)
/*     */         try {
/* 206 */           ConfigObject parsed = propsHandle.parse(propsHandle.options().setAllowMissing(false).setSyntax(ConfigSyntax.PROPERTIES));
/* 208 */           obj = obj.withFallback((ConfigMergeable)parsed);
/* 209 */           gotSomething = true;
/* 210 */         } catch (com.typesafe.config.ConfigException.IO e) {
/* 211 */           fails.add(e);
/*     */         }  
/* 215 */       if (!options.getAllowMissing() && !gotSomething) {
/* 216 */         if (ConfigImpl.traceLoadsEnabled())
/* 219 */           ConfigImpl.trace("Did not find '" + name + "' with any extension (.conf, .json, .properties); " + "exceptions should have been logged above."); 
/* 224 */         if (fails.isEmpty())
/* 226 */           throw new ConfigException.BugOrBroken("should not be reached: nothing found but no exceptions thrown"); 
/* 229 */         StringBuilder sb = new StringBuilder();
/* 230 */         for (ConfigException.IO iO : fails) {
/* 231 */           sb.append(iO.getMessage());
/* 232 */           sb.append(", ");
/*     */         } 
/* 234 */         sb.setLength(sb.length() - 2);
/* 235 */         throw new ConfigException.IO(SimpleConfigOrigin.newSimple(name), sb.toString(), (Throwable)fails.get(0));
/*     */       } 
/* 238 */       if (!gotSomething && 
/* 239 */         ConfigImpl.traceLoadsEnabled())
/* 240 */         ConfigImpl.trace("Did not find '" + name + "' with any extension (.conf, .json, .properties); but '" + name + "' is allowed to be missing. Exceptions from load attempts should have been logged above."); 
/*     */     } 
/* 247 */     return obj;
/*     */   }
/*     */   
/*     */   private static class Proxy implements FullIncluder {
/*     */     final ConfigIncluder delegate;
/*     */     
/*     */     Proxy(ConfigIncluder delegate) {
/* 257 */       this.delegate = delegate;
/*     */     }
/*     */     
/*     */     public ConfigIncluder withFallback(ConfigIncluder fallback) {
/* 263 */       return this;
/*     */     }
/*     */     
/*     */     public ConfigObject include(ConfigIncludeContext context, String what) {
/* 268 */       return this.delegate.include(context, what);
/*     */     }
/*     */     
/*     */     public ConfigObject includeResources(ConfigIncludeContext context, String what) {
/* 273 */       if (this.delegate instanceof ConfigIncluderClasspath)
/* 274 */         return ((ConfigIncluderClasspath)this.delegate).includeResources(context, what); 
/* 276 */       return SimpleIncluder.includeResourceWithoutFallback(context, what);
/*     */     }
/*     */     
/*     */     public ConfigObject includeURL(ConfigIncludeContext context, URL what) {
/* 281 */       if (this.delegate instanceof ConfigIncluderURL)
/* 282 */         return ((ConfigIncluderURL)this.delegate).includeURL(context, what); 
/* 284 */       return SimpleIncluder.includeURLWithoutFallback(context, what);
/*     */     }
/*     */     
/*     */     public ConfigObject includeFile(ConfigIncludeContext context, File what) {
/* 289 */       if (this.delegate instanceof ConfigIncluderFile)
/* 290 */         return ((ConfigIncluderFile)this.delegate).includeFile(context, what); 
/* 292 */       return SimpleIncluder.includeFileWithoutFallback(context, what);
/*     */     }
/*     */   }
/*     */   
/*     */   static FullIncluder makeFull(ConfigIncluder includer) {
/* 297 */     if (includer instanceof FullIncluder)
/* 298 */       return (FullIncluder)includer; 
/* 300 */     return new Proxy(includer);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\SimpleIncluder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */