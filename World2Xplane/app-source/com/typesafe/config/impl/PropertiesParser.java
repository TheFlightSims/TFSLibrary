/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ 
/*     */ final class PropertiesParser {
/*     */   static AbstractConfigObject parse(Reader reader, ConfigOrigin origin) throws IOException {
/*  24 */     Properties props = new Properties();
/*  25 */     props.load(reader);
/*  26 */     return fromProperties(origin, props);
/*     */   }
/*     */   
/*     */   static String lastElement(String path) {
/*  30 */     int i = path.lastIndexOf('.');
/*  31 */     if (i < 0)
/*  32 */       return path; 
/*  34 */     return path.substring(i + 1);
/*     */   }
/*     */   
/*     */   static String exceptLastElement(String path) {
/*  38 */     int i = path.lastIndexOf('.');
/*  39 */     if (i < 0)
/*  40 */       return null; 
/*  42 */     return path.substring(0, i);
/*     */   }
/*     */   
/*     */   static Path pathFromPropertyKey(String key) {
/*  46 */     String last = lastElement(key);
/*  47 */     String exceptLast = exceptLastElement(key);
/*  48 */     Path path = new Path(last, null);
/*  49 */     while (exceptLast != null) {
/*  50 */       last = lastElement(exceptLast);
/*  51 */       exceptLast = exceptLastElement(exceptLast);
/*  52 */       path = new Path(last, path);
/*     */     } 
/*  54 */     return path;
/*     */   }
/*     */   
/*     */   static AbstractConfigObject fromProperties(ConfigOrigin origin, Properties props) {
/*  59 */     Map<Path, Object> pathMap = new HashMap<Path, Object>();
/*  60 */     for (Map.Entry<Object, Object> entry : props.entrySet()) {
/*  61 */       Object key = entry.getKey();
/*  62 */       if (key instanceof String) {
/*  63 */         Path path = pathFromPropertyKey((String)key);
/*  64 */         pathMap.put(path, entry.getValue());
/*     */       } 
/*     */     } 
/*  67 */     return fromPathMap(origin, pathMap, true);
/*     */   }
/*     */   
/*     */   static AbstractConfigObject fromPathMap(ConfigOrigin origin, Map<?, ?> pathExpressionMap) {
/*  72 */     Map<Path, Object> pathMap = new HashMap<Path, Object>();
/*  73 */     for (Map.Entry<?, ?> entry : pathExpressionMap.entrySet()) {
/*  74 */       Object keyObj = entry.getKey();
/*  75 */       if (!(keyObj instanceof String))
/*  76 */         throw new ConfigException.BugOrBroken("Map has a non-string as a key, expecting a path expression as a String"); 
/*  79 */       Path path = Path.newPath((String)keyObj);
/*  80 */       pathMap.put(path, entry.getValue());
/*     */     } 
/*  82 */     return fromPathMap(origin, pathMap, false);
/*     */   }
/*     */   
/*     */   private static AbstractConfigObject fromPathMap(ConfigOrigin origin, Map<Path, Object> pathMap, boolean convertedFromProperties) {
/*  91 */     Set<Path> scopePaths = new HashSet<Path>();
/*  92 */     Set<Path> valuePaths = new HashSet<Path>();
/*  93 */     for (Path path : pathMap.keySet()) {
/*  95 */       valuePaths.add(path);
/*  98 */       Path next = path.parent();
/*  99 */       while (next != null) {
/* 100 */         scopePaths.add(next);
/* 101 */         next = next.parent();
/*     */       } 
/*     */     } 
/* 105 */     if (convertedFromProperties) {
/* 110 */       valuePaths.removeAll(scopePaths);
/*     */     } else {
/* 113 */       for (Path path : valuePaths) {
/* 114 */         if (scopePaths.contains(path))
/* 115 */           throw new ConfigException.BugOrBroken("In the map, path '" + path.render() + "' occurs as both the parent object of a value and as a value. " + "Because Map has no defined ordering, this is a broken situation."); 
/*     */       } 
/*     */     } 
/* 127 */     Map<String, AbstractConfigValue> root = new HashMap<String, AbstractConfigValue>();
/* 128 */     Map<Path, Map<String, AbstractConfigValue>> scopes = new HashMap<Path, Map<String, AbstractConfigValue>>();
/* 130 */     for (Path path : scopePaths) {
/* 131 */       Map<String, AbstractConfigValue> scope = new HashMap<String, AbstractConfigValue>();
/* 132 */       scopes.put(path, scope);
/*     */     } 
/* 136 */     for (Path path : valuePaths) {
/*     */       AbstractConfigValue value;
/* 137 */       Path parentPath = path.parent();
/* 138 */       Map<String, AbstractConfigValue> parent = (parentPath != null) ? scopes.get(parentPath) : root;
/* 141 */       String last = path.last();
/* 142 */       Object rawValue = pathMap.get(path);
/* 144 */       if (convertedFromProperties) {
/* 145 */         if (rawValue instanceof String) {
/* 146 */           value = new ConfigString(origin, (String)rawValue);
/*     */         } else {
/* 149 */           value = null;
/*     */         } 
/*     */       } else {
/* 152 */         value = ConfigImpl.fromAnyRef(pathMap.get(path), origin, FromMapMode.KEYS_ARE_PATHS);
/*     */       } 
/* 155 */       if (value != null)
/* 156 */         parent.put(last, value); 
/*     */     } 
/* 163 */     List<Path> sortedScopePaths = new ArrayList<Path>();
/* 164 */     sortedScopePaths.addAll(scopePaths);
/* 166 */     Collections.sort(sortedScopePaths, new Comparator<Path>() {
/*     */           public int compare(Path a, Path b) {
/* 172 */             return b.length() - a.length();
/*     */           }
/*     */         });
/* 181 */     for (Path scopePath : sortedScopePaths) {
/* 182 */       Map<String, AbstractConfigValue> scope = scopes.get(scopePath);
/* 184 */       Path parentPath = scopePath.parent();
/* 185 */       Map<String, AbstractConfigValue> parent = (parentPath != null) ? scopes.get(parentPath) : root;
/* 188 */       AbstractConfigObject o = new SimpleConfigObject(origin, scope, ResolveStatus.RESOLVED, false);
/* 190 */       parent.put(scopePath.last(), o);
/*     */     } 
/* 194 */     return new SimpleConfigObject(origin, root, ResolveStatus.RESOLVED, false);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\PropertiesParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */