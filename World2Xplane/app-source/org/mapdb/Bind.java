/*     */ package org.mapdb;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ 
/*     */ public final class Bind {
/*     */   public static <K, V> void size(MapWithModificationListener<K, V> map, final Atomic.Long sizeCounter) {
/* 136 */     if (sizeCounter.get() == 0L) {
/* 137 */       long size = map.sizeLong();
/* 138 */       if (sizeCounter.get() != size)
/* 139 */         sizeCounter.set(size); 
/*     */     } 
/* 142 */     map.modificationListenerAdd(new MapListener<K, V>() {
/*     */           public void update(K key, V oldVal, V newVal) {
/* 145 */             if (oldVal == null && newVal != null) {
/* 146 */               sizeCounter.incrementAndGet();
/* 147 */             } else if (oldVal != null && newVal == null) {
/* 148 */               sizeCounter.decrementAndGet();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static <K, V, V2> void secondaryValue(MapWithModificationListener<K, V> map, final Map<K, V2> secondary, final Fun.Function2<V2, K, V> fun) {
/* 181 */     if (secondary.isEmpty())
/* 182 */       for (Map.Entry<K, V> e : map.entrySet())
/* 183 */         secondary.put(e.getKey(), fun.run(e.getKey(), e.getValue()));  
/* 186 */     map.modificationListenerAdd(new MapListener<K, V>() {
/*     */           public void update(K key, V oldVal, V newVal) {
/* 189 */             if (newVal == null) {
/* 191 */               secondary.remove(key);
/*     */             } else {
/* 193 */               secondary.put(key, fun.run(key, newVal));
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static <K, V, V2> void secondaryValues(MapWithModificationListener<K, V> map, final Set<Fun.Tuple2<K, V2>> secondary, final Fun.Function2<V2[], K, V> fun) {
/* 224 */     if (secondary.isEmpty())
/* 225 */       for (Map.Entry<K, V> e : map.entrySet()) {
/* 226 */         V2[] v = fun.run(e.getKey(), e.getValue());
/* 227 */         if (v != null)
/* 228 */           for (V2 v2 : v)
/* 229 */             secondary.add(Fun.t2(e.getKey(), v2));  
/*     */       }  
/* 233 */     map.modificationListenerAdd(new MapListener<K, V>() {
/*     */           public void update(K key, V oldVal, V newVal) {
/* 236 */             if (newVal == null) {
/* 238 */               V2[] v = (V2[])fun.run(key, oldVal);
/* 239 */               if (v != null)
/* 240 */                 for (V2 v2 : v)
/* 241 */                   secondary.remove(Fun.t2(key, v2));  
/* 242 */             } else if (oldVal == null) {
/* 244 */               V2[] v = (V2[])fun.run(key, newVal);
/* 245 */               if (v != null)
/* 246 */                 for (V2 v2 : v)
/* 247 */                   secondary.add(Fun.t2(key, v2));  
/*     */             } else {
/* 250 */               V2[] oldv = (V2[])fun.run(key, oldVal);
/* 251 */               V2[] newv = (V2[])fun.run(key, newVal);
/* 252 */               if (oldv == null) {
/* 254 */                 if (newv != null)
/* 255 */                   for (V2 v : newv)
/* 256 */                     secondary.add(Fun.t2(key, v));  
/*     */                 return;
/*     */               } 
/* 259 */               if (newv == null) {
/* 261 */                 for (V2 v : oldv)
/* 262 */                   secondary.remove(Fun.t2(key, v)); 
/*     */                 return;
/*     */               } 
/* 266 */               Set<V2> hashes = new HashSet<V2>();
/* 267 */               Collections.addAll(hashes, oldv);
/* 270 */               for (V2 v : newv) {
/* 271 */                 if (!hashes.contains(v))
/* 272 */                   secondary.add(Fun.t2(key, v)); 
/*     */               } 
/* 276 */               for (V2 v : newv)
/* 277 */                 hashes.remove(v); 
/* 279 */               for (V2 v : hashes)
/* 280 */                 secondary.remove(Fun.t2(key, v)); 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static <K, V, K2> void secondaryKey(MapWithModificationListener<K, V> map, final Set<Fun.Tuple2<K2, K>> secondary, final Fun.Function2<K2, K, V> fun) {
/* 314 */     if (secondary.isEmpty())
/* 315 */       for (Map.Entry<K, V> e : map.entrySet())
/* 316 */         secondary.add(Fun.t2(fun.run(e.getKey(), e.getValue()), e.getKey()));  
/* 320 */     map.modificationListenerAdd(new MapListener<K, V>() {
/*     */           public void update(K key, V oldVal, V newVal) {
/* 323 */             if (newVal == null) {
/* 325 */               secondary.remove(Fun.t2(fun.run(key, oldVal), key));
/* 326 */             } else if (oldVal == null) {
/* 328 */               secondary.add(Fun.t2(fun.run(key, newVal), key));
/*     */             } else {
/* 331 */               K2 oldKey = fun.run(key, oldVal);
/* 332 */               K2 newKey = fun.run(key, newVal);
/* 333 */               if (oldKey == newKey || oldKey.equals(newKey))
/*     */                 return; 
/* 334 */               secondary.remove(Fun.t2(oldKey, key));
/* 335 */               secondary.add(Fun.t2(newKey, key));
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static <K, V, K2> void secondaryKey(MapWithModificationListener<K, V> map, final Map<K2, K> secondary, final Fun.Function2<K2, K, V> fun) {
/* 365 */     if (secondary.isEmpty())
/* 366 */       for (Map.Entry<K, V> e : map.entrySet())
/* 367 */         secondary.put(fun.run(e.getKey(), e.getValue()), e.getKey());  
/* 371 */     map.modificationListenerAdd(new MapListener<K, V>() {
/*     */           public void update(K key, V oldVal, V newVal) {
/* 374 */             if (newVal == null) {
/* 376 */               secondary.remove(fun.run(key, oldVal));
/* 377 */             } else if (oldVal == null) {
/* 379 */               secondary.put(fun.run(key, newVal), key);
/*     */             } else {
/* 382 */               K2 oldKey = fun.run(key, oldVal);
/* 383 */               K2 newKey = fun.run(key, newVal);
/* 384 */               if (oldKey == newKey || oldKey.equals(newKey))
/*     */                 return; 
/* 385 */               secondary.remove(oldKey);
/* 386 */               secondary.put(newKey, key);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static <K, V, K2> void secondaryKeys(MapWithModificationListener<K, V> map, final Set<Fun.Tuple2<K2, K>> secondary, final Fun.Function2<K2[], K, V> fun) {
/* 418 */     if (secondary.isEmpty())
/* 419 */       for (Map.Entry<K, V> e : map.entrySet()) {
/* 420 */         K2[] k2 = fun.run(e.getKey(), e.getValue());
/* 421 */         if (k2 != null)
/* 422 */           for (K2 k22 : k2)
/* 423 */             secondary.add(Fun.t2(k22, e.getKey()));  
/*     */       }  
/* 427 */     map.modificationListenerAdd(new MapListener<K, V>() {
/*     */           public void update(K key, V oldVal, V newVal) {
/* 430 */             if (newVal == null) {
/* 432 */               K2[] k2 = (K2[])fun.run(key, oldVal);
/* 433 */               if (k2 != null)
/* 434 */                 for (K2 k22 : k2)
/* 435 */                   secondary.remove(Fun.t2(k22, key));  
/* 436 */             } else if (oldVal == null) {
/* 438 */               K2[] k2 = (K2[])fun.run(key, newVal);
/* 439 */               if (k2 != null)
/* 440 */                 for (K2 k22 : k2)
/* 441 */                   secondary.add(Fun.t2(k22, key));  
/*     */             } else {
/* 444 */               K2[] oldk = (K2[])fun.run(key, oldVal);
/* 445 */               K2[] newk = (K2[])fun.run(key, newVal);
/* 446 */               if (oldk == null) {
/* 448 */                 if (newk != null)
/* 449 */                   for (K2 k22 : newk)
/* 450 */                     secondary.add(Fun.t2(k22, key));  
/*     */                 return;
/*     */               } 
/* 453 */               if (newk == null) {
/* 455 */                 for (K2 k22 : oldk)
/* 456 */                   secondary.remove(Fun.t2(k22, key)); 
/*     */                 return;
/*     */               } 
/* 460 */               Set<K2> hashes = new HashSet<K2>();
/* 461 */               Collections.addAll(hashes, oldk);
/* 464 */               for (K2 k2 : newk) {
/* 465 */                 if (!hashes.contains(k2))
/* 466 */                   secondary.add(Fun.t2(k2, key)); 
/*     */               } 
/* 470 */               for (K2 k2 : newk)
/* 471 */                 hashes.remove(k2); 
/* 473 */               for (K2 k2 : hashes)
/* 474 */                 secondary.remove(Fun.t2(k2, key)); 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static <K, V> void mapInverse(MapWithModificationListener<K, V> primary, Set<Fun.Tuple2<V, K>> inverse) {
/* 502 */     secondaryKey(primary, inverse, new Fun.Function2<V, K, V>() {
/*     */           public V run(K key, V value) {
/* 504 */             return value;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static <K, V> void mapInverse(MapWithModificationListener<K, V> primary, Map<V, K> inverse) {
/* 532 */     secondaryKey(primary, inverse, new Fun.Function2<V, K, V>() {
/*     */           public V run(K key, V value) {
/* 534 */             return value;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static <K, V, C> void histogram(MapWithModificationListener<K, V> primary, final ConcurrentMap<C, Long> histogram, final Fun.Function2<C, K, V> entryToCategory) {
/* 570 */     MapListener<K, V> listener = new MapListener<K, V>() {
/*     */         public void update(K key, V oldVal, V newVal) {
/* 572 */           if (newVal == null) {
/* 574 */             C category = entryToCategory.run(key, oldVal);
/* 575 */             incrementHistogram(category, -1L);
/* 576 */           } else if (oldVal == null) {
/* 578 */             C category = entryToCategory.run(key, newVal);
/* 579 */             incrementHistogram(category, 1L);
/*     */           } else {
/* 582 */             C oldCat = entryToCategory.run(key, oldVal);
/* 583 */             C newCat = entryToCategory.run(key, newVal);
/* 584 */             if (oldCat == newCat || oldCat.equals(newCat))
/*     */               return; 
/* 585 */             incrementHistogram(oldCat, -1L);
/* 586 */             incrementHistogram(oldCat, 1L);
/*     */           } 
/*     */         }
/*     */         
/*     */         private void incrementHistogram(C category, long i) {
/*     */           while (true) {
/* 594 */             Long oldCount = (Long)histogram.get(category);
/* 595 */             if (oldCount == null) {
/* 597 */               if (histogram.putIfAbsent(category, Long.valueOf(i)) == null)
/*     */                 return; 
/*     */               continue;
/*     */             } 
/* 601 */             Long newCount = Long.valueOf(oldCount.longValue() + i);
/* 602 */             if (histogram.replace(category, oldCount, newCount))
/*     */               break; 
/*     */           } 
/*     */         }
/*     */       };
/* 609 */     primary.modificationListenerAdd(listener);
/*     */   }
/*     */   
/*     */   public static interface MapListener<K, V> {
/*     */     void update(K param1K, V param1V1, V param1V2);
/*     */   }
/*     */   
/*     */   public static interface MapWithModificationListener<K, V> extends Map<K, V> {
/*     */     void modificationListenerAdd(Bind.MapListener<K, V> param1MapListener);
/*     */     
/*     */     void modificationListenerRemove(Bind.MapListener<K, V> param1MapListener);
/*     */     
/*     */     long sizeLong();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Bind.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */