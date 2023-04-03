/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.WeakHashMap;
/*     */ 
/*     */ class WeakFastHashMap extends HashMap {
/*  72 */   private Map map = null;
/*     */   
/*     */   private boolean fast = false;
/*     */   
/*     */   public WeakFastHashMap() {
/*  87 */     this.map = createMap();
/*     */   }
/*     */   
/*     */   public WeakFastHashMap(int capacity) {
/*  97 */     this.map = createMap(capacity);
/*     */   }
/*     */   
/*     */   public WeakFastHashMap(int capacity, float factor) {
/* 108 */     this.map = createMap(capacity, factor);
/*     */   }
/*     */   
/*     */   public WeakFastHashMap(Map map) {
/* 118 */     this.map = createMap(map);
/*     */   }
/*     */   
/*     */   public boolean getFast() {
/* 131 */     return this.fast;
/*     */   }
/*     */   
/*     */   public void setFast(boolean fast) {
/* 140 */     this.fast = fast;
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 159 */     if (this.fast)
/* 160 */       return this.map.get(key); 
/* 162 */     synchronized (this.map) {
/* 163 */       return this.map.get(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 174 */     if (this.fast)
/* 175 */       return this.map.size(); 
/* 177 */     synchronized (this.map) {
/* 178 */       return this.map.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 189 */     if (this.fast)
/* 190 */       return this.map.isEmpty(); 
/* 192 */     synchronized (this.map) {
/* 193 */       return this.map.isEmpty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 206 */     if (this.fast)
/* 207 */       return this.map.containsKey(key); 
/* 209 */     synchronized (this.map) {
/* 210 */       return this.map.containsKey(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 223 */     if (this.fast)
/* 224 */       return this.map.containsValue(value); 
/* 226 */     synchronized (this.map) {
/* 227 */       return this.map.containsValue(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 248 */     if (this.fast)
/* 249 */       synchronized (this) {
/* 250 */         Map temp = cloneMap(this.map);
/* 251 */         Object result = temp.put(key, value);
/* 252 */         this.map = temp;
/* 253 */         return result;
/*     */       }  
/* 256 */     synchronized (this.map) {
/* 257 */       return this.map.put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(Map in) {
/* 269 */     if (this.fast) {
/* 270 */       synchronized (this) {
/* 271 */         Map temp = cloneMap(this.map);
/* 272 */         temp.putAll(in);
/* 273 */         this.map = temp;
/*     */       } 
/*     */     } else {
/* 276 */       synchronized (this.map) {
/* 277 */         this.map.putAll(in);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 290 */     if (this.fast)
/* 291 */       synchronized (this) {
/* 292 */         Map temp = cloneMap(this.map);
/* 293 */         Object result = temp.remove(key);
/* 294 */         this.map = temp;
/* 295 */         return result;
/*     */       }  
/* 298 */     synchronized (this.map) {
/* 299 */       return this.map.remove(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 308 */     if (this.fast) {
/* 309 */       synchronized (this) {
/* 310 */         this.map = createMap();
/*     */       } 
/*     */     } else {
/* 313 */       synchronized (this.map) {
/* 314 */         this.map.clear();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 333 */     if (o == this)
/* 334 */       return true; 
/* 335 */     if (!(o instanceof Map))
/* 336 */       return false; 
/* 338 */     Map mo = (Map)o;
/* 341 */     if (this.fast) {
/* 342 */       if (mo.size() != this.map.size())
/* 343 */         return false; 
/* 345 */       Iterator i = this.map.entrySet().iterator();
/* 346 */       while (i.hasNext()) {
/* 347 */         Map.Entry e = i.next();
/* 348 */         Object key = e.getKey();
/* 349 */         Object value = e.getValue();
/* 350 */         if (value == null) {
/* 351 */           if (mo.get(key) != null || !mo.containsKey(key))
/* 352 */             return false; 
/*     */           continue;
/*     */         } 
/* 355 */         if (!value.equals(mo.get(key)))
/* 356 */           return false; 
/*     */       } 
/* 360 */       return true;
/*     */     } 
/* 363 */     synchronized (this.map) {
/* 364 */       if (mo.size() != this.map.size())
/* 365 */         return false; 
/* 367 */       Iterator i = this.map.entrySet().iterator();
/* 368 */       while (i.hasNext()) {
/* 369 */         Map.Entry e = i.next();
/* 370 */         Object key = e.getKey();
/* 371 */         Object value = e.getValue();
/* 372 */         if (value == null) {
/* 373 */           if (mo.get(key) != null || !mo.containsKey(key))
/* 374 */             return false; 
/*     */           continue;
/*     */         } 
/* 377 */         if (!value.equals(mo.get(key)))
/* 378 */           return false; 
/*     */       } 
/* 382 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 395 */     if (this.fast) {
/* 396 */       int h = 0;
/* 397 */       Iterator i = this.map.entrySet().iterator();
/* 398 */       while (i.hasNext())
/* 399 */         h += i.next().hashCode(); 
/* 401 */       return h;
/*     */     } 
/* 403 */     synchronized (this.map) {
/* 404 */       int h = 0;
/* 405 */       Iterator i = this.map.entrySet().iterator();
/* 406 */       while (i.hasNext())
/* 407 */         h += i.next().hashCode(); 
/* 409 */       return h;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 421 */     WeakFastHashMap results = null;
/* 422 */     if (this.fast) {
/* 423 */       results = new WeakFastHashMap(this.map);
/*     */     } else {
/* 425 */       synchronized (this.map) {
/* 426 */         results = new WeakFastHashMap(this.map);
/*     */       } 
/*     */     } 
/* 429 */     results.setFast(getFast());
/* 430 */     return results;
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 442 */     return new EntrySet();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 450 */     return new KeySet();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 458 */     return new Values();
/*     */   }
/*     */   
/*     */   protected Map createMap() {
/* 465 */     return new WeakHashMap();
/*     */   }
/*     */   
/*     */   protected Map createMap(int capacity) {
/* 469 */     return new WeakHashMap(capacity);
/*     */   }
/*     */   
/*     */   protected Map createMap(int capacity, float factor) {
/* 473 */     return new WeakHashMap(capacity, factor);
/*     */   }
/*     */   
/*     */   protected Map createMap(Map map) {
/* 477 */     return new WeakHashMap(map);
/*     */   }
/*     */   
/*     */   protected Map cloneMap(Map map) {
/* 481 */     return createMap(map);
/*     */   }
/*     */   
/*     */   private abstract class CollectionView implements Collection {
/*     */     private final WeakFastHashMap this$0;
/*     */     
/*     */     public CollectionView(WeakFastHashMap this$0) {
/* 492 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 500 */       if (this.this$0.fast) {
/* 501 */         synchronized (this.this$0) {
/* 502 */           this.this$0.map = this.this$0.createMap();
/*     */         } 
/*     */       } else {
/* 505 */         synchronized (this.this$0.map) {
/* 506 */           get(this.this$0.map).clear();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 512 */       if (this.this$0.fast)
/* 513 */         synchronized (this.this$0) {
/* 514 */           Map temp = this.this$0.cloneMap(this.this$0.map);
/* 515 */           boolean r = get(temp).remove(o);
/* 516 */           this.this$0.map = temp;
/* 517 */           return r;
/*     */         }  
/* 520 */       synchronized (this.this$0.map) {
/* 521 */         return get(this.this$0.map).remove(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection o) {
/* 527 */       if (this.this$0.fast)
/* 528 */         synchronized (this.this$0) {
/* 529 */           Map temp = this.this$0.cloneMap(this.this$0.map);
/* 530 */           boolean r = get(temp).removeAll(o);
/* 531 */           this.this$0.map = temp;
/* 532 */           return r;
/*     */         }  
/* 535 */       synchronized (this.this$0.map) {
/* 536 */         return get(this.this$0.map).removeAll(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection o) {
/* 542 */       if (this.this$0.fast)
/* 543 */         synchronized (this.this$0) {
/* 544 */           Map temp = this.this$0.cloneMap(this.this$0.map);
/* 545 */           boolean r = get(temp).retainAll(o);
/* 546 */           this.this$0.map = temp;
/* 547 */           return r;
/*     */         }  
/* 550 */       synchronized (this.this$0.map) {
/* 551 */         return get(this.this$0.map).retainAll(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public int size() {
/* 557 */       if (this.this$0.fast)
/* 558 */         return get(this.this$0.map).size(); 
/* 560 */       synchronized (this.this$0.map) {
/* 561 */         return get(this.this$0.map).size();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 568 */       if (this.this$0.fast)
/* 569 */         return get(this.this$0.map).isEmpty(); 
/* 571 */       synchronized (this.this$0.map) {
/* 572 */         return get(this.this$0.map).isEmpty();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 578 */       if (this.this$0.fast)
/* 579 */         return get(this.this$0.map).contains(o); 
/* 581 */       synchronized (this.this$0.map) {
/* 582 */         return get(this.this$0.map).contains(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection o) {
/* 588 */       if (this.this$0.fast)
/* 589 */         return get(this.this$0.map).containsAll(o); 
/* 591 */       synchronized (this.this$0.map) {
/* 592 */         return get(this.this$0.map).containsAll(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object[] toArray(Object[] o) {
/* 598 */       if (this.this$0.fast)
/* 599 */         return get(this.this$0.map).toArray(o); 
/* 601 */       synchronized (this.this$0.map) {
/* 602 */         return get(this.this$0.map).toArray(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 608 */       if (this.this$0.fast)
/* 609 */         return get(this.this$0.map).toArray(); 
/* 611 */       synchronized (this.this$0.map) {
/* 612 */         return get(this.this$0.map).toArray();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 619 */       if (o == this)
/* 620 */         return true; 
/* 622 */       if (this.this$0.fast)
/* 623 */         return get(this.this$0.map).equals(o); 
/* 625 */       synchronized (this.this$0.map) {
/* 626 */         return get(this.this$0.map).equals(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 632 */       if (this.this$0.fast)
/* 633 */         return get(this.this$0.map).hashCode(); 
/* 635 */       synchronized (this.this$0.map) {
/* 636 */         return get(this.this$0.map).hashCode();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean add(Object o) {
/* 642 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection c) {
/* 646 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 650 */       return new CollectionViewIterator(this);
/*     */     }
/*     */     
/*     */     protected abstract Collection get(Map param1Map);
/*     */     
/*     */     protected abstract Object iteratorNext(Map.Entry param1Entry);
/*     */     
/*     */     private class CollectionViewIterator implements Iterator {
/*     */       private Map expected;
/*     */       
/*     */       private Map.Entry lastReturned;
/*     */       
/*     */       private Iterator iterator;
/*     */       
/*     */       private final WeakFastHashMap.CollectionView this$1;
/*     */       
/*     */       public CollectionViewIterator(WeakFastHashMap.CollectionView this$0) {
/* 659 */         this.this$1 = this$0;
/*     */         this.lastReturned = null;
/* 660 */         this.expected = this$0.this$0.map;
/* 661 */         this.iterator = this.expected.entrySet().iterator();
/*     */       }
/*     */       
/*     */       public boolean hasNext() {
/* 665 */         if (this.expected != this.this$1.this$0.map)
/* 666 */           throw new ConcurrentModificationException(); 
/* 668 */         return this.iterator.hasNext();
/*     */       }
/*     */       
/*     */       public Object next() {
/* 672 */         if (this.expected != this.this$1.this$0.map)
/* 673 */           throw new ConcurrentModificationException(); 
/* 675 */         this.lastReturned = this.iterator.next();
/* 676 */         return this.this$1.iteratorNext(this.lastReturned);
/*     */       }
/*     */       
/*     */       public void remove() {
/* 680 */         if (this.lastReturned == null)
/* 681 */           throw new IllegalStateException(); 
/* 683 */         if (this.this$1.this$0.fast) {
/* 684 */           synchronized (this.this$1.this$0) {
/* 685 */             if (this.expected != this.this$1.this$0.map)
/* 686 */               throw new ConcurrentModificationException(); 
/* 688 */             this.this$1.this$0.remove(this.lastReturned.getKey());
/* 689 */             this.lastReturned = null;
/* 690 */             this.expected = this.this$1.this$0.map;
/*     */           } 
/*     */         } else {
/* 693 */           this.iterator.remove();
/* 694 */           this.lastReturned = null;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeySet extends CollectionView implements Set {
/*     */     private final WeakFastHashMap this$0;
/*     */     
/*     */     private KeySet(WeakFastHashMap this$0) {
/* 703 */       WeakFastHashMap.this = WeakFastHashMap.this;
/*     */     }
/*     */     
/*     */     protected Collection get(Map map) {
/* 706 */       return map.keySet();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 710 */       return entry.getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private class Values extends CollectionView {
/*     */     private final WeakFastHashMap this$0;
/*     */     
/*     */     private Values(WeakFastHashMap this$0) {
/* 718 */       WeakFastHashMap.this = WeakFastHashMap.this;
/*     */     }
/*     */     
/*     */     protected Collection get(Map map) {
/* 721 */       return map.values();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 725 */       return entry.getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntrySet extends CollectionView implements Set {
/*     */     private final WeakFastHashMap this$0;
/*     */     
/*     */     private EntrySet(WeakFastHashMap this$0) {
/* 732 */       WeakFastHashMap.this = WeakFastHashMap.this;
/*     */     }
/*     */     
/*     */     protected Collection get(Map map) {
/* 735 */       return map.entrySet();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 739 */       return entry;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\WeakFastHashMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */