/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.iterators.EmptyIterator;
/*     */ 
/*     */ public class MultiHashMap extends HashMap implements MultiMap {
/*  72 */   private transient Collection values = null;
/*     */   
/*     */   private static final long serialVersionUID = 1943563828307035349L;
/*     */   
/*     */   public MultiHashMap() {}
/*     */   
/*     */   public MultiHashMap(int initialCapacity) {
/*  90 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */   public MultiHashMap(int initialCapacity, float loadFactor) {
/* 100 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   public MultiHashMap(Map mapToCopy) {
/* 120 */     super((int)(mapToCopy.size() * 1.4F));
/* 121 */     putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 132 */     s.defaultReadObject();
/* 135 */     String version = "1.2";
/*     */     try {
/* 137 */       version = System.getProperty("java.version");
/* 138 */     } catch (SecurityException ex) {}
/* 142 */     if (version.startsWith("1.2") || version.startsWith("1.3"))
/* 143 */       for (Iterator iterator = entrySet().iterator(); iterator.hasNext(); ) {
/* 144 */         Map.Entry entry = iterator.next();
/* 146 */         super.put(entry.getKey(), ((Collection)entry.getValue()).iterator().next());
/*     */       }  
/*     */   }
/*     */   
/*     */   public int totalSize() {
/* 159 */     int total = 0;
/* 160 */     Collection values = super.values();
/* 161 */     for (Iterator it = values.iterator(); it.hasNext(); ) {
/* 162 */       Collection coll = (Collection)it.next();
/* 163 */       total += coll.size();
/*     */     } 
/* 165 */     return total;
/*     */   }
/*     */   
/*     */   public Collection getCollection(Object key) {
/* 177 */     return (Collection)get(key);
/*     */   }
/*     */   
/*     */   public int size(Object key) {
/* 188 */     Collection coll = getCollection(key);
/* 189 */     if (coll == null)
/* 190 */       return 0; 
/* 192 */     return coll.size();
/*     */   }
/*     */   
/*     */   public Iterator iterator(Object key) {
/* 203 */     Collection coll = getCollection(key);
/* 204 */     if (coll == null)
/* 205 */       return EmptyIterator.INSTANCE; 
/* 207 */     return coll.iterator();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 223 */     Collection coll = getCollection(key);
/* 224 */     if (coll == null) {
/* 225 */       coll = createCollection((Collection)null);
/* 226 */       super.put(key, coll);
/*     */     } 
/* 228 */     boolean results = coll.add(value);
/* 229 */     return results ? value : null;
/*     */   }
/*     */   
/*     */   public void putAll(Map map) {
/* 242 */     if (map instanceof MultiMap) {
/* 243 */       for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 244 */         Map.Entry entry = it.next();
/* 245 */         Collection coll = (Collection)entry.getValue();
/* 246 */         putAll(entry.getKey(), coll);
/*     */       } 
/*     */     } else {
/* 249 */       for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 250 */         Map.Entry entry = it.next();
/* 251 */         put(entry.getKey(), entry.getValue());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean putAll(Object key, Collection values) {
/* 265 */     if (values == null || values.size() == 0)
/* 266 */       return false; 
/* 268 */     Collection coll = getCollection(key);
/* 269 */     if (coll == null) {
/* 270 */       coll = createCollection(values);
/* 271 */       if (coll.size() == 0)
/* 272 */         return false; 
/* 274 */       super.put(key, coll);
/* 275 */       return true;
/*     */     } 
/* 277 */     return coll.addAll(values);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 290 */     Set pairs = entrySet();
/* 292 */     if (pairs == null)
/* 293 */       return false; 
/* 295 */     Iterator pairsIterator = pairs.iterator();
/* 296 */     while (pairsIterator.hasNext()) {
/* 297 */       Map.Entry keyValuePair = pairsIterator.next();
/* 298 */       Collection coll = (Collection)keyValuePair.getValue();
/* 299 */       if (coll.contains(value))
/* 300 */         return true; 
/*     */     } 
/* 303 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object key, Object value) {
/* 314 */     Collection coll = getCollection(key);
/* 315 */     if (coll == null)
/* 316 */       return false; 
/* 318 */     return coll.contains(value);
/*     */   }
/*     */   
/*     */   public Object remove(Object key, Object item) {
/* 335 */     Collection valuesForKey = getCollection(key);
/* 336 */     if (valuesForKey == null)
/* 337 */       return null; 
/* 339 */     boolean removed = valuesForKey.remove(item);
/* 340 */     if (!removed)
/* 341 */       return null; 
/* 345 */     if (valuesForKey.isEmpty())
/* 346 */       remove(key); 
/* 348 */     return item;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 358 */     Set pairs = entrySet();
/* 359 */     Iterator pairsIterator = pairs.iterator();
/* 360 */     while (pairsIterator.hasNext()) {
/* 361 */       Map.Entry keyValuePair = pairsIterator.next();
/* 362 */       Collection coll = (Collection)keyValuePair.getValue();
/* 363 */       coll.clear();
/*     */     } 
/* 365 */     super.clear();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 376 */     Collection vs = this.values;
/* 377 */     return (vs != null) ? vs : (this.values = new Values());
/*     */   }
/*     */   
/*     */   Iterator superValuesIterator() {
/* 386 */     return super.values().iterator();
/*     */   }
/*     */   
/*     */   private class Values extends AbstractCollection {
/*     */     private final MultiHashMap this$0;
/*     */     
/*     */     private Values(MultiHashMap this$0) {
/* 393 */       MultiHashMap.this = MultiHashMap.this;
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 396 */       return new MultiHashMap.ValueIterator();
/*     */     }
/*     */     
/*     */     public int size() {
/* 400 */       int compt = 0;
/* 401 */       Iterator it = iterator();
/* 402 */       while (it.hasNext()) {
/* 403 */         it.next();
/* 404 */         compt++;
/*     */       } 
/* 406 */       return compt;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 410 */       MultiHashMap.this.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   private class ValueIterator implements Iterator {
/*     */     private Iterator backedIterator;
/*     */     
/*     */     private Iterator tempIterator;
/*     */     
/*     */     private final MultiHashMap this$0;
/*     */     
/*     */     private ValueIterator(MultiHashMap this$0) {
/* 422 */       MultiHashMap.this = MultiHashMap.this;
/* 423 */       this.backedIterator = MultiHashMap.this.superValuesIterator();
/*     */     }
/*     */     
/*     */     private boolean searchNextIterator() {
/* 427 */       while (this.tempIterator == null || !this.tempIterator.hasNext()) {
/* 428 */         if (!this.backedIterator.hasNext())
/* 429 */           return false; 
/* 431 */         this.tempIterator = ((Collection)this.backedIterator.next()).iterator();
/*     */       } 
/* 433 */       return true;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 437 */       return searchNextIterator();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 441 */       if (!searchNextIterator())
/* 442 */         throw new NoSuchElementException(); 
/* 444 */       return this.tempIterator.next();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 448 */       if (this.tempIterator == null)
/* 449 */         throw new IllegalStateException(); 
/* 451 */       this.tempIterator.remove();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 465 */     MultiHashMap cloned = (MultiHashMap)super.clone();
/* 468 */     for (Iterator it = cloned.entrySet().iterator(); it.hasNext(); ) {
/* 469 */       Map.Entry entry = it.next();
/* 470 */       Collection coll = (Collection)entry.getValue();
/* 471 */       Collection newColl = createCollection(coll);
/* 472 */       entry.setValue(newColl);
/*     */     } 
/* 474 */     return cloned;
/*     */   }
/*     */   
/*     */   protected Collection createCollection(Collection coll) {
/* 486 */     if (coll == null)
/* 487 */       return new ArrayList(); 
/* 489 */     return new ArrayList(coll);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\MultiHashMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */