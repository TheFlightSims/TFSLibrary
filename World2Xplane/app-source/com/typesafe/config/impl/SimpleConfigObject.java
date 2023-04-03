/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigObject;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigRenderOptions;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ final class SimpleConfigObject extends AbstractConfigObject implements Serializable {
/*     */   private static final long serialVersionUID = 2L;
/*     */   
/*     */   private final Map<String, AbstractConfigValue> value;
/*     */   
/*     */   private final boolean resolved;
/*     */   
/*     */   private final boolean ignoresFallbacks;
/*     */   
/*     */   private static final String EMPTY_NAME = "empty config";
/*     */   
/*     */   SimpleConfigObject(ConfigOrigin origin, Map<String, AbstractConfigValue> value, ResolveStatus status, boolean ignoresFallbacks) {
/*  37 */     super(origin);
/*  38 */     if (value == null)
/*  39 */       throw new ConfigException.BugOrBroken("creating config object with null map"); 
/*  41 */     this.value = value;
/*  42 */     this.resolved = (status == ResolveStatus.RESOLVED);
/*  43 */     this.ignoresFallbacks = ignoresFallbacks;
/*  46 */     if (status != ResolveStatus.fromValues(value.values()))
/*  47 */       throw new ConfigException.BugOrBroken("Wrong resolved status on " + this); 
/*     */   }
/*     */   
/*     */   SimpleConfigObject(ConfigOrigin origin, Map<String, AbstractConfigValue> value) {
/*  52 */     this(origin, value, ResolveStatus.fromValues(value.values()), false);
/*     */   }
/*     */   
/*     */   public SimpleConfigObject withOnlyKey(String key) {
/*  57 */     return withOnlyPath(Path.newKey(key));
/*     */   }
/*     */   
/*     */   public SimpleConfigObject withoutKey(String key) {
/*  62 */     return withoutPath(Path.newKey(key));
/*     */   }
/*     */   
/*     */   protected SimpleConfigObject withOnlyPathOrNull(Path path) {
/*  72 */     String key = path.first();
/*  73 */     Path next = path.remainder();
/*  74 */     AbstractConfigValue v = this.value.get(key);
/*  76 */     if (next != null)
/*  77 */       if (v != null && v instanceof AbstractConfigObject) {
/*  78 */         v = ((AbstractConfigObject)v).withOnlyPathOrNull(next);
/*     */       } else {
/*  82 */         v = null;
/*     */       }  
/*  86 */     if (v == null)
/*  87 */       return null; 
/*  89 */     return new SimpleConfigObject(origin(), Collections.singletonMap(key, v), v.resolveStatus(), this.ignoresFallbacks);
/*     */   }
/*     */   
/*     */   SimpleConfigObject withOnlyPath(Path path) {
/*  96 */     SimpleConfigObject o = withOnlyPathOrNull(path);
/*  97 */     if (o == null)
/*  98 */       return new SimpleConfigObject(origin(), Collections.emptyMap(), ResolveStatus.RESOLVED, this.ignoresFallbacks); 
/* 102 */     return o;
/*     */   }
/*     */   
/*     */   SimpleConfigObject withoutPath(Path path) {
/* 108 */     String key = path.first();
/* 109 */     Path next = path.remainder();
/* 110 */     AbstractConfigValue v = this.value.get(key);
/* 112 */     if (v != null && next != null && v instanceof AbstractConfigObject) {
/* 113 */       v = ((AbstractConfigObject)v).withoutPath(next);
/* 114 */       Map<String, AbstractConfigValue> updated = new HashMap<String, AbstractConfigValue>(this.value);
/* 116 */       updated.put(key, v);
/* 117 */       return new SimpleConfigObject(origin(), updated, ResolveStatus.fromValues(updated.values()), this.ignoresFallbacks);
/*     */     } 
/* 119 */     if (next != null || v == null)
/* 121 */       return this; 
/* 123 */     Map<String, AbstractConfigValue> smaller = new HashMap<String, AbstractConfigValue>(this.value.size() - 1);
/* 125 */     for (Map.Entry<String, AbstractConfigValue> old : this.value.entrySet()) {
/* 126 */       if (!((String)old.getKey()).equals(key))
/* 127 */         smaller.put(old.getKey(), old.getValue()); 
/*     */     } 
/* 129 */     return new SimpleConfigObject(origin(), smaller, ResolveStatus.fromValues(smaller.values()), this.ignoresFallbacks);
/*     */   }
/*     */   
/*     */   public SimpleConfigObject withValue(String key, ConfigValue v) {
/*     */     Map<String, AbstractConfigValue> newMap;
/* 136 */     if (v == null)
/* 137 */       throw new ConfigException.BugOrBroken("Trying to store null ConfigValue in a ConfigObject"); 
/* 141 */     if (this.value.isEmpty()) {
/* 142 */       newMap = Collections.singletonMap(key, (AbstractConfigValue)v);
/*     */     } else {
/* 144 */       newMap = new HashMap<String, AbstractConfigValue>(this.value);
/* 145 */       newMap.put(key, (AbstractConfigValue)v);
/*     */     } 
/* 148 */     return new SimpleConfigObject(origin(), newMap, ResolveStatus.fromValues(newMap.values()), this.ignoresFallbacks);
/*     */   }
/*     */   
/*     */   SimpleConfigObject withValue(Path path, ConfigValue v) {
/* 154 */     String key = path.first();
/* 155 */     Path next = path.remainder();
/* 157 */     if (next == null)
/* 158 */       return withValue(key, v); 
/* 160 */     AbstractConfigValue child = this.value.get(key);
/* 161 */     if (child != null && child instanceof AbstractConfigObject)
/* 163 */       return withValue(key, ((AbstractConfigObject)child).withValue(next, v)); 
/* 166 */     SimpleConfig subtree = ((AbstractConfigValue)v).atPath(SimpleConfigOrigin.newSimple("withValue(" + next.render() + ")"), next);
/* 168 */     return withValue(key, subtree.root());
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue attemptPeekWithPartialResolve(String key) {
/* 175 */     return this.value.get(key);
/*     */   }
/*     */   
/*     */   private SimpleConfigObject newCopy(ResolveStatus newStatus, ConfigOrigin newOrigin, boolean newIgnoresFallbacks) {
/* 180 */     return new SimpleConfigObject(newOrigin, this.value, newStatus, newIgnoresFallbacks);
/*     */   }
/*     */   
/*     */   protected SimpleConfigObject newCopy(ResolveStatus newStatus, ConfigOrigin newOrigin) {
/* 185 */     return newCopy(newStatus, newOrigin, this.ignoresFallbacks);
/*     */   }
/*     */   
/*     */   protected SimpleConfigObject withFallbacksIgnored() {
/* 190 */     if (this.ignoresFallbacks)
/* 191 */       return this; 
/* 193 */     return newCopy(resolveStatus(), origin(), true);
/*     */   }
/*     */   
/*     */   ResolveStatus resolveStatus() {
/* 198 */     return ResolveStatus.fromBoolean(this.resolved);
/*     */   }
/*     */   
/*     */   protected boolean ignoresFallbacks() {
/* 203 */     return this.ignoresFallbacks;
/*     */   }
/*     */   
/*     */   public Map<String, Object> unwrapped() {
/* 208 */     Map<String, Object> m = new HashMap<String, Object>();
/* 209 */     for (Map.Entry<String, AbstractConfigValue> e : this.value.entrySet())
/* 210 */       m.put(e.getKey(), ((AbstractConfigValue)e.getValue()).unwrapped()); 
/* 212 */     return m;
/*     */   }
/*     */   
/*     */   protected SimpleConfigObject mergedWithObject(AbstractConfigObject abstractFallback) {
/* 217 */     requireNotIgnoringFallbacks();
/* 219 */     if (!(abstractFallback instanceof SimpleConfigObject))
/* 220 */       throw new ConfigException.BugOrBroken("should not be reached (merging non-SimpleConfigObject)"); 
/* 224 */     SimpleConfigObject fallback = (SimpleConfigObject)abstractFallback;
/* 226 */     boolean changed = false;
/* 227 */     boolean allResolved = true;
/* 228 */     Map<String, AbstractConfigValue> merged = new HashMap<String, AbstractConfigValue>();
/* 229 */     Set<String> allKeys = new HashSet<String>();
/* 230 */     allKeys.addAll(keySet());
/* 231 */     allKeys.addAll(fallback.keySet());
/* 232 */     for (String key : allKeys) {
/* 233 */       AbstractConfigValue kept, first = this.value.get(key);
/* 234 */       AbstractConfigValue second = fallback.value.get(key);
/* 236 */       if (first == null) {
/* 237 */         kept = second;
/* 238 */       } else if (second == null) {
/* 239 */         kept = first;
/*     */       } else {
/* 241 */         kept = first.withFallback(second);
/*     */       } 
/* 243 */       merged.put(key, kept);
/* 245 */       if (first != kept)
/* 246 */         changed = true; 
/* 248 */       if (kept.resolveStatus() == ResolveStatus.UNRESOLVED)
/* 249 */         allResolved = false; 
/*     */     } 
/* 252 */     ResolveStatus newResolveStatus = ResolveStatus.fromBoolean(allResolved);
/* 253 */     boolean newIgnoresFallbacks = fallback.ignoresFallbacks();
/* 255 */     if (changed)
/* 256 */       return new SimpleConfigObject(mergeOrigins(new AbstractConfigObject[] { this, fallback }, ), merged, newResolveStatus, newIgnoresFallbacks); 
/* 258 */     if (newResolveStatus != resolveStatus() || newIgnoresFallbacks != ignoresFallbacks())
/* 259 */       return newCopy(newResolveStatus, origin(), newIgnoresFallbacks); 
/* 261 */     return this;
/*     */   }
/*     */   
/*     */   private SimpleConfigObject modify(AbstractConfigValue.NoExceptionsModifier modifier) {
/*     */     try {
/* 266 */       return modifyMayThrow(modifier);
/* 267 */     } catch (RuntimeException e) {
/* 268 */       throw e;
/* 269 */     } catch (Exception e) {
/* 270 */       throw new ConfigException.BugOrBroken("unexpected checked exception", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private SimpleConfigObject modifyMayThrow(AbstractConfigValue.Modifier modifier) throws Exception {
/* 275 */     Map<String, AbstractConfigValue> changes = null;
/* 276 */     for (String k : keySet()) {
/* 277 */       AbstractConfigValue v = this.value.get(k);
/* 280 */       AbstractConfigValue abstractConfigValue1 = modifier.modifyChildMayThrow(k, v);
/* 281 */       if (abstractConfigValue1 != v) {
/* 282 */         if (changes == null)
/* 283 */           changes = new HashMap<String, AbstractConfigValue>(); 
/* 284 */         changes.put(k, abstractConfigValue1);
/*     */       } 
/*     */     } 
/* 287 */     if (changes == null)
/* 288 */       return this; 
/* 290 */     Map<String, AbstractConfigValue> modified = new HashMap<String, AbstractConfigValue>();
/* 291 */     boolean sawUnresolved = false;
/* 292 */     for (String k : keySet()) {
/* 293 */       if (changes.containsKey(k)) {
/* 294 */         AbstractConfigValue abstractConfigValue = changes.get(k);
/* 295 */         if (abstractConfigValue != null) {
/* 296 */           modified.put(k, abstractConfigValue);
/* 297 */           if (abstractConfigValue.resolveStatus() == ResolveStatus.UNRESOLVED)
/* 298 */             sawUnresolved = true; 
/*     */         } 
/*     */         continue;
/*     */       } 
/* 303 */       AbstractConfigValue newValue = this.value.get(k);
/* 304 */       modified.put(k, newValue);
/* 305 */       if (newValue.resolveStatus() == ResolveStatus.UNRESOLVED)
/* 306 */         sawUnresolved = true; 
/*     */     } 
/* 309 */     return new SimpleConfigObject(origin(), modified, sawUnresolved ? ResolveStatus.UNRESOLVED : ResolveStatus.RESOLVED, ignoresFallbacks());
/*     */   }
/*     */   
/*     */   AbstractConfigObject resolveSubstitutions(final ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/* 317 */     if (resolveStatus() == ResolveStatus.RESOLVED)
/* 318 */       return this; 
/*     */     try {
/* 321 */       return modifyMayThrow(new AbstractConfigValue.Modifier() {
/*     */             public AbstractConfigValue modifyChildMayThrow(String key, AbstractConfigValue v) throws AbstractConfigValue.NotPossibleToResolve {
/* 326 */               if (context.isRestrictedToChild()) {
/* 327 */                 if (key.equals(context.restrictToChild().first())) {
/* 328 */                   Path remainder = context.restrictToChild().remainder();
/* 329 */                   if (remainder != null)
/* 330 */                     return context.restrict(remainder).resolve(v); 
/* 333 */                   return v;
/*     */                 } 
/* 337 */                 return v;
/*     */               } 
/* 341 */               return context.unrestricted().resolve(v);
/*     */             }
/*     */           });
/* 346 */     } catch (NotPossibleToResolve e) {
/* 347 */       throw e;
/* 348 */     } catch (RuntimeException e) {
/* 349 */       throw e;
/* 350 */     } catch (Exception e) {
/* 351 */       throw new ConfigException.BugOrBroken("unexpected checked exception", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   SimpleConfigObject relativized(final Path prefix) {
/* 357 */     return modify(new AbstractConfigValue.NoExceptionsModifier() {
/*     */           public AbstractConfigValue modifyChild(String key, AbstractConfigValue v) {
/* 361 */             return v.relativized(prefix);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   protected void render(StringBuilder sb, int indent, boolean atRoot, ConfigRenderOptions options) {
/* 369 */     if (isEmpty()) {
/* 370 */       sb.append("{}");
/*     */     } else {
/*     */       int innerIndent;
/* 372 */       boolean outerBraces = (options.getJson() || !atRoot);
/* 375 */       if (outerBraces) {
/* 376 */         innerIndent = indent + 1;
/* 377 */         sb.append("{");
/* 379 */         if (options.getFormatted())
/* 380 */           sb.append('\n'); 
/*     */       } else {
/* 382 */         innerIndent = indent;
/*     */       } 
/* 385 */       int separatorCount = 0;
/* 386 */       String[] keys = keySet().<String>toArray(new String[size()]);
/* 387 */       Arrays.sort((Object[])keys);
/* 388 */       for (String k : keys) {
/* 390 */         AbstractConfigValue v = this.value.get(k);
/* 392 */         if (options.getOriginComments()) {
/* 393 */           indent(sb, innerIndent, options);
/* 394 */           sb.append("# ");
/* 395 */           sb.append(v.origin().description());
/* 396 */           sb.append("\n");
/*     */         } 
/* 398 */         if (options.getComments())
/* 399 */           for (String comment : v.origin().comments()) {
/* 400 */             indent(sb, innerIndent, options);
/* 401 */             sb.append("#");
/* 402 */             if (!comment.startsWith(" "))
/* 403 */               sb.append(' '); 
/* 404 */             sb.append(comment);
/* 405 */             sb.append("\n");
/*     */           }  
/* 408 */         indent(sb, innerIndent, options);
/* 409 */         v.render(sb, innerIndent, false, k, options);
/* 411 */         if (options.getFormatted()) {
/* 412 */           if (options.getJson()) {
/* 413 */             sb.append(",");
/* 414 */             separatorCount = 2;
/*     */           } else {
/* 416 */             separatorCount = 1;
/*     */           } 
/* 418 */           sb.append('\n');
/*     */         } else {
/* 420 */           sb.append(",");
/* 421 */           separatorCount = 1;
/*     */         } 
/*     */       } 
/* 425 */       sb.setLength(sb.length() - separatorCount);
/* 427 */       if (outerBraces) {
/* 428 */         if (options.getFormatted()) {
/* 429 */           sb.append('\n');
/* 430 */           if (outerBraces)
/* 431 */             indent(sb, indent, options); 
/*     */         } 
/* 433 */         sb.append("}");
/*     */       } 
/*     */     } 
/* 436 */     if (atRoot && options.getFormatted())
/* 437 */       sb.append('\n'); 
/*     */   }
/*     */   
/*     */   public AbstractConfigValue get(Object key) {
/* 442 */     return this.value.get(key);
/*     */   }
/*     */   
/*     */   private static boolean mapEquals(Map<String, ConfigValue> a, Map<String, ConfigValue> b) {
/* 446 */     Set<String> aKeys = a.keySet();
/* 447 */     Set<String> bKeys = b.keySet();
/* 449 */     if (!aKeys.equals(bKeys))
/* 450 */       return false; 
/* 452 */     for (String key : aKeys) {
/* 453 */       if (!((ConfigValue)a.get(key)).equals(b.get(key)))
/* 454 */         return false; 
/*     */     } 
/* 456 */     return true;
/*     */   }
/*     */   
/*     */   private static int mapHash(Map<String, ConfigValue> m) {
/* 462 */     List<String> keys = new ArrayList<String>();
/* 463 */     keys.addAll(m.keySet());
/* 464 */     Collections.sort(keys);
/* 466 */     int valuesHash = 0;
/* 467 */     for (String k : keys)
/* 468 */       valuesHash += ((ConfigValue)m.get(k)).hashCode(); 
/* 470 */     return 41 * (41 + keys.hashCode()) + valuesHash;
/*     */   }
/*     */   
/*     */   protected boolean canEqual(Object other) {
/* 475 */     return other instanceof ConfigObject;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 482 */     if (other instanceof ConfigObject)
/* 485 */       return (canEqual(other) && mapEquals((Map<String, ConfigValue>)this, (Map<String, ConfigValue>)other)); 
/* 487 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 495 */     return mapHash((Map<String, ConfigValue>)this);
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 500 */     return this.value.containsKey(key);
/*     */   }
/*     */   
/*     */   public Set<String> keySet() {
/* 505 */     return this.value.keySet();
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object v) {
/* 510 */     return this.value.containsValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<String, ConfigValue>> entrySet() {
/* 517 */     HashSet<Map.Entry<String, ConfigValue>> entries = new HashSet<Map.Entry<String, ConfigValue>>();
/* 518 */     for (Map.Entry<String, AbstractConfigValue> e : this.value.entrySet())
/* 519 */       entries.add(new AbstractMap.SimpleImmutableEntry<String, ConfigValue>(e.getKey(), e.getValue())); 
/* 523 */     return entries;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 528 */     return this.value.isEmpty();
/*     */   }
/*     */   
/*     */   public int size() {
/* 533 */     return this.value.size();
/*     */   }
/*     */   
/*     */   public Collection<ConfigValue> values() {
/* 538 */     return new HashSet<ConfigValue>(this.value.values());
/*     */   }
/*     */   
/* 542 */   private static final SimpleConfigObject emptyInstance = empty(SimpleConfigOrigin.newSimple("empty config"));
/*     */   
/*     */   static final SimpleConfigObject empty() {
/* 546 */     return emptyInstance;
/*     */   }
/*     */   
/*     */   static final SimpleConfigObject empty(ConfigOrigin origin) {
/* 550 */     if (origin == null)
/* 551 */       return empty(); 
/* 553 */     return new SimpleConfigObject(origin, Collections.emptyMap());
/*     */   }
/*     */   
/*     */   static final SimpleConfigObject emptyMissing(ConfigOrigin baseOrigin) {
/* 558 */     return new SimpleConfigObject(SimpleConfigOrigin.newSimple(baseOrigin.description() + " (not found)"), Collections.emptyMap());
/*     */   }
/*     */   
/*     */   private Object writeReplace() throws ObjectStreamException {
/* 565 */     return new SerializedConfigValue(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\SimpleConfigObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */