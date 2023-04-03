/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.CollectionUtils;
/*     */ import org.apache.commons.collections.collection.CompositeCollection;
/*     */ import org.apache.commons.collections.set.CompositeSet;
/*     */ 
/*     */ public class CompositeMap implements Map {
/*     */   private Map[] composite;
/*     */   
/*     */   private MapMutator mutator;
/*     */   
/*     */   public CompositeMap() {
/*  58 */     this(new Map[0], (MapMutator)null);
/*     */   }
/*     */   
/*     */   public CompositeMap(Map one, Map two) {
/*  69 */     this(new Map[] { one, two }, (MapMutator)null);
/*     */   }
/*     */   
/*     */   public CompositeMap(Map one, Map two, MapMutator mutator) {
/*  80 */     this(new Map[] { one, two }, mutator);
/*     */   }
/*     */   
/*     */   public CompositeMap(Map[] composite) {
/*  91 */     this(composite, (MapMutator)null);
/*     */   }
/*     */   
/*     */   public CompositeMap(Map[] composite, MapMutator mutator) {
/* 102 */     this.mutator = mutator;
/* 103 */     this.composite = new Map[0];
/* 104 */     for (int i = composite.length - 1; i >= 0; i--)
/* 105 */       addComposited(composite[i]); 
/*     */   }
/*     */   
/*     */   public void setMutator(MapMutator mutator) {
/* 116 */     this.mutator = mutator;
/*     */   }
/*     */   
/*     */   public synchronized void addComposited(Map map) throws IllegalArgumentException {
/* 127 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 128 */       Collection intersect = CollectionUtils.intersection(this.composite[i].keySet(), map.keySet());
/* 129 */       if (intersect.size() != 0) {
/* 130 */         if (this.mutator == null)
/* 131 */           throw new IllegalArgumentException("Key collision adding Map to CompositeMap"); 
/* 134 */         this.mutator.resolveCollision(this, this.composite[i], map, intersect);
/*     */       } 
/*     */     } 
/* 138 */     Map[] temp = new Map[this.composite.length + 1];
/* 139 */     System.arraycopy(this.composite, 0, temp, 0, this.composite.length);
/* 140 */     temp[temp.length - 1] = map;
/* 141 */     this.composite = temp;
/*     */   }
/*     */   
/*     */   public synchronized Map removeComposited(Map map) {
/* 151 */     int size = this.composite.length;
/* 152 */     for (int i = 0; i < size; i++) {
/* 153 */       if (this.composite[i].equals(map)) {
/* 154 */         Map[] temp = new Map[size - 1];
/* 155 */         System.arraycopy(this.composite, 0, temp, 0, i);
/* 156 */         System.arraycopy(this.composite, i + 1, temp, i, size - i - 1);
/* 157 */         this.composite = temp;
/* 158 */         return map;
/*     */       } 
/*     */     } 
/* 161 */     return null;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 171 */     for (int i = this.composite.length - 1; i >= 0; i--)
/* 172 */       this.composite[i].clear(); 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 193 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 194 */       if (this.composite[i].containsKey(key))
/* 195 */         return true; 
/*     */     } 
/* 198 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 218 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 219 */       if (this.composite[i].containsValue(value))
/* 220 */         return true; 
/*     */     } 
/* 223 */     return false;
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 244 */     CompositeSet entries = new CompositeSet();
/* 245 */     for (int i = this.composite.length - 1; i >= 0; i--)
/* 246 */       entries.addComposited(this.composite[i].entrySet()); 
/* 248 */     return (Set)entries;
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 276 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 277 */       if (this.composite[i].containsKey(key))
/* 278 */         return this.composite[i].get(key); 
/*     */     } 
/* 281 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 290 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 291 */       if (!this.composite[i].isEmpty())
/* 292 */         return false; 
/*     */     } 
/* 295 */     return true;
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 314 */     CompositeSet keys = new CompositeSet();
/* 315 */     for (int i = this.composite.length - 1; i >= 0; i--)
/* 316 */       keys.addComposited(this.composite[i].keySet()); 
/* 318 */     return (Set)keys;
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 347 */     if (this.mutator == null)
/* 348 */       throw new UnsupportedOperationException("No mutator specified"); 
/* 350 */     return this.mutator.put(this, this.composite, key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map map) {
/* 376 */     if (this.mutator == null)
/* 377 */       throw new UnsupportedOperationException("No mutator specified"); 
/* 379 */     this.mutator.putAll(this, this.composite, map);
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 408 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 409 */       if (this.composite[i].containsKey(key))
/* 410 */         return this.composite[i].remove(key); 
/*     */     } 
/* 413 */     return null;
/*     */   }
/*     */   
/*     */   public int size() {
/* 424 */     int size = 0;
/* 425 */     for (int i = this.composite.length - 1; i >= 0; i--)
/* 426 */       size += this.composite[i].size(); 
/* 428 */     return size;
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 445 */     CompositeCollection keys = new CompositeCollection();
/* 446 */     for (int i = this.composite.length - 1; i >= 0; i--)
/* 447 */       keys.addComposited(this.composite[i].values()); 
/* 449 */     return (Collection)keys;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 459 */     if (obj instanceof Map) {
/* 460 */       Map map = (Map)obj;
/* 461 */       return entrySet().equals(map.entrySet());
/*     */     } 
/* 463 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 470 */     int code = 0;
/* 471 */     for (Iterator i = entrySet().iterator(); i.hasNext();)
/* 472 */       code += i.next().hashCode(); 
/* 474 */     return code;
/*     */   }
/*     */   
/*     */   public static interface MapMutator {
/*     */     void resolveCollision(CompositeMap param1CompositeMap, Map param1Map1, Map param1Map2, Collection param1Collection);
/*     */     
/*     */     Object put(CompositeMap param1CompositeMap, Map[] param1ArrayOfMap, Object param1Object1, Object param1Object2);
/*     */     
/*     */     void putAll(CompositeMap param1CompositeMap, Map[] param1ArrayOfMap, Map param1Map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\CompositeMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */