/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class FastHashMap extends HashMap {
/*  71 */   protected HashMap map = null;
/*     */   
/*     */   protected boolean fast = false;
/*     */   
/*     */   public FastHashMap() {
/*  86 */     this.map = new HashMap();
/*     */   }
/*     */   
/*     */   public FastHashMap(int capacity) {
/*  96 */     this.map = new HashMap(capacity);
/*     */   }
/*     */   
/*     */   public FastHashMap(int capacity, float factor) {
/* 107 */     this.map = new HashMap(capacity, factor);
/*     */   }
/*     */   
/*     */   public FastHashMap(Map map) {
/* 117 */     this.map = new HashMap(map);
/*     */   }
/*     */   
/*     */   public boolean getFast() {
/* 130 */     return this.fast;
/*     */   }
/*     */   
/*     */   public void setFast(boolean fast) {
/* 139 */     this.fast = fast;
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 158 */     if (this.fast)
/* 159 */       return this.map.get(key); 
/* 161 */     synchronized (this.map) {
/* 162 */       return this.map.get(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 173 */     if (this.fast)
/* 174 */       return this.map.size(); 
/* 176 */     synchronized (this.map) {
/* 177 */       return this.map.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 188 */     if (this.fast)
/* 189 */       return this.map.isEmpty(); 
/* 191 */     synchronized (this.map) {
/* 192 */       return this.map.isEmpty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 205 */     if (this.fast)
/* 206 */       return this.map.containsKey(key); 
/* 208 */     synchronized (this.map) {
/* 209 */       return this.map.containsKey(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 222 */     if (this.fast)
/* 223 */       return this.map.containsValue(value); 
/* 225 */     synchronized (this.map) {
/* 226 */       return this.map.containsValue(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 247 */     if (this.fast)
/* 248 */       synchronized (this) {
/* 249 */         HashMap temp = (HashMap)this.map.clone();
/* 250 */         Object result = temp.put(key, value);
/* 251 */         this.map = temp;
/* 252 */         return result;
/*     */       }  
/* 255 */     synchronized (this.map) {
/* 256 */       return this.map.put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(Map in) {
/* 268 */     if (this.fast) {
/* 269 */       synchronized (this) {
/* 270 */         HashMap temp = (HashMap)this.map.clone();
/* 271 */         temp.putAll(in);
/* 272 */         this.map = temp;
/*     */       } 
/*     */     } else {
/* 275 */       synchronized (this.map) {
/* 276 */         this.map.putAll(in);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 289 */     if (this.fast)
/* 290 */       synchronized (this) {
/* 291 */         HashMap temp = (HashMap)this.map.clone();
/* 292 */         Object result = temp.remove(key);
/* 293 */         this.map = temp;
/* 294 */         return result;
/*     */       }  
/* 297 */     synchronized (this.map) {
/* 298 */       return this.map.remove(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 307 */     if (this.fast) {
/* 308 */       synchronized (this) {
/* 309 */         this.map = new HashMap();
/*     */       } 
/*     */     } else {
/* 312 */       synchronized (this.map) {
/* 313 */         this.map.clear();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 332 */     if (o == this)
/* 333 */       return true; 
/* 334 */     if (!(o instanceof Map))
/* 335 */       return false; 
/* 337 */     Map mo = (Map)o;
/* 340 */     if (this.fast) {
/* 341 */       if (mo.size() != this.map.size())
/* 342 */         return false; 
/* 344 */       Iterator i = this.map.entrySet().iterator();
/* 345 */       while (i.hasNext()) {
/* 346 */         Map.Entry e = i.next();
/* 347 */         Object key = e.getKey();
/* 348 */         Object value = e.getValue();
/* 349 */         if (value == null) {
/* 350 */           if (mo.get(key) != null || !mo.containsKey(key))
/* 351 */             return false; 
/*     */           continue;
/*     */         } 
/* 354 */         if (!value.equals(mo.get(key)))
/* 355 */           return false; 
/*     */       } 
/* 359 */       return true;
/*     */     } 
/* 362 */     synchronized (this.map) {
/* 363 */       if (mo.size() != this.map.size())
/* 364 */         return false; 
/* 366 */       Iterator i = this.map.entrySet().iterator();
/* 367 */       while (i.hasNext()) {
/* 368 */         Map.Entry e = i.next();
/* 369 */         Object key = e.getKey();
/* 370 */         Object value = e.getValue();
/* 371 */         if (value == null) {
/* 372 */           if (mo.get(key) != null || !mo.containsKey(key))
/* 373 */             return false; 
/*     */           continue;
/*     */         } 
/* 376 */         if (!value.equals(mo.get(key)))
/* 377 */           return false; 
/*     */       } 
/* 381 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 394 */     if (this.fast) {
/* 395 */       int h = 0;
/* 396 */       Iterator i = this.map.entrySet().iterator();
/* 397 */       while (i.hasNext())
/* 398 */         h += i.next().hashCode(); 
/* 400 */       return h;
/*     */     } 
/* 402 */     synchronized (this.map) {
/* 403 */       int h = 0;
/* 404 */       Iterator i = this.map.entrySet().iterator();
/* 405 */       while (i.hasNext())
/* 406 */         h += i.next().hashCode(); 
/* 408 */       return h;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 420 */     FastHashMap results = null;
/* 421 */     if (this.fast) {
/* 422 */       results = new FastHashMap(this.map);
/*     */     } else {
/* 424 */       synchronized (this.map) {
/* 425 */         results = new FastHashMap(this.map);
/*     */       } 
/*     */     } 
/* 428 */     results.setFast(getFast());
/* 429 */     return results;
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 440 */     return new EntrySet();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 447 */     return new KeySet();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 454 */     return new Values();
/*     */   }
/*     */   
/*     */   private abstract class CollectionView implements Collection {
/*     */     private final FastHashMap this$0;
/*     */     
/*     */     public CollectionView(FastHashMap this$0) {
/* 465 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 473 */       if (this.this$0.fast) {
/* 474 */         synchronized (this.this$0) {
/* 475 */           this.this$0.map = new HashMap();
/*     */         } 
/*     */       } else {
/* 478 */         synchronized (this.this$0.map) {
/* 479 */           get(this.this$0.map).clear();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 485 */       if (this.this$0.fast)
/* 486 */         synchronized (this.this$0) {
/* 487 */           HashMap temp = (HashMap)this.this$0.map.clone();
/* 488 */           boolean r = get(temp).remove(o);
/* 489 */           this.this$0.map = temp;
/* 490 */           return r;
/*     */         }  
/* 493 */       synchronized (this.this$0.map) {
/* 494 */         return get(this.this$0.map).remove(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection o) {
/* 500 */       if (this.this$0.fast)
/* 501 */         synchronized (this.this$0) {
/* 502 */           HashMap temp = (HashMap)this.this$0.map.clone();
/* 503 */           boolean r = get(temp).removeAll(o);
/* 504 */           this.this$0.map = temp;
/* 505 */           return r;
/*     */         }  
/* 508 */       synchronized (this.this$0.map) {
/* 509 */         return get(this.this$0.map).removeAll(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection o) {
/* 515 */       if (this.this$0.fast)
/* 516 */         synchronized (this.this$0) {
/* 517 */           HashMap temp = (HashMap)this.this$0.map.clone();
/* 518 */           boolean r = get(temp).retainAll(o);
/* 519 */           this.this$0.map = temp;
/* 520 */           return r;
/*     */         }  
/* 523 */       synchronized (this.this$0.map) {
/* 524 */         return get(this.this$0.map).retainAll(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public int size() {
/* 530 */       if (this.this$0.fast)
/* 531 */         return get(this.this$0.map).size(); 
/* 533 */       synchronized (this.this$0.map) {
/* 534 */         return get(this.this$0.map).size();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 541 */       if (this.this$0.fast)
/* 542 */         return get(this.this$0.map).isEmpty(); 
/* 544 */       synchronized (this.this$0.map) {
/* 545 */         return get(this.this$0.map).isEmpty();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 551 */       if (this.this$0.fast)
/* 552 */         return get(this.this$0.map).contains(o); 
/* 554 */       synchronized (this.this$0.map) {
/* 555 */         return get(this.this$0.map).contains(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection o) {
/* 561 */       if (this.this$0.fast)
/* 562 */         return get(this.this$0.map).containsAll(o); 
/* 564 */       synchronized (this.this$0.map) {
/* 565 */         return get(this.this$0.map).containsAll(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object[] toArray(Object[] o) {
/* 571 */       if (this.this$0.fast)
/* 572 */         return get(this.this$0.map).toArray(o); 
/* 574 */       synchronized (this.this$0.map) {
/* 575 */         return get(this.this$0.map).toArray(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 581 */       if (this.this$0.fast)
/* 582 */         return get(this.this$0.map).toArray(); 
/* 584 */       synchronized (this.this$0.map) {
/* 585 */         return get(this.this$0.map).toArray();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 592 */       if (o == this)
/* 592 */         return true; 
/* 593 */       if (this.this$0.fast)
/* 594 */         return get(this.this$0.map).equals(o); 
/* 596 */       synchronized (this.this$0.map) {
/* 597 */         return get(this.this$0.map).equals(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 603 */       if (this.this$0.fast)
/* 604 */         return get(this.this$0.map).hashCode(); 
/* 606 */       synchronized (this.this$0.map) {
/* 607 */         return get(this.this$0.map).hashCode();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean add(Object o) {
/* 613 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection c) {
/* 617 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 621 */       return new CollectionViewIterator(this);
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
/*     */       private final FastHashMap.CollectionView this$1;
/*     */       
/*     */       public CollectionViewIterator(FastHashMap.CollectionView this$0) {
/* 630 */         this.this$1 = this$0;
/*     */         this.lastReturned = null;
/* 631 */         this.expected = this$0.this$0.map;
/* 632 */         this.iterator = this.expected.entrySet().iterator();
/*     */       }
/*     */       
/*     */       public boolean hasNext() {
/* 636 */         if (this.expected != this.this$1.this$0.map)
/* 637 */           throw new ConcurrentModificationException(); 
/* 639 */         return this.iterator.hasNext();
/*     */       }
/*     */       
/*     */       public Object next() {
/* 643 */         if (this.expected != this.this$1.this$0.map)
/* 644 */           throw new ConcurrentModificationException(); 
/* 646 */         this.lastReturned = this.iterator.next();
/* 647 */         return this.this$1.iteratorNext(this.lastReturned);
/*     */       }
/*     */       
/*     */       public void remove() {
/* 651 */         if (this.lastReturned == null)
/* 652 */           throw new IllegalStateException(); 
/* 654 */         if (this.this$1.this$0.fast) {
/* 655 */           synchronized (this.this$1.this$0) {
/* 656 */             if (this.expected != this.this$1.this$0.map)
/* 657 */               throw new ConcurrentModificationException(); 
/* 659 */             this.this$1.this$0.remove(this.lastReturned.getKey());
/* 660 */             this.lastReturned = null;
/* 661 */             this.expected = this.this$1.this$0.map;
/*     */           } 
/*     */         } else {
/* 664 */           this.iterator.remove();
/* 665 */           this.lastReturned = null;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeySet extends CollectionView implements Set {
/*     */     private final FastHashMap this$0;
/*     */     
/*     */     private KeySet(FastHashMap this$0) {
/* 674 */       FastHashMap.this = FastHashMap.this;
/*     */     }
/*     */     
/*     */     protected Collection get(Map map) {
/* 677 */       return map.keySet();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 681 */       return entry.getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private class Values extends CollectionView {
/*     */     private final FastHashMap this$0;
/*     */     
/*     */     private Values(FastHashMap this$0) {
/* 689 */       FastHashMap.this = FastHashMap.this;
/*     */     }
/*     */     
/*     */     protected Collection get(Map map) {
/* 692 */       return map.values();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 696 */       return entry.getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntrySet extends CollectionView implements Set {
/*     */     private final FastHashMap this$0;
/*     */     
/*     */     private EntrySet(FastHashMap this$0) {
/* 703 */       FastHashMap.this = FastHashMap.this;
/*     */     }
/*     */     
/*     */     protected Collection get(Map map) {
/* 706 */       return map.entrySet();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 710 */       return entry;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\FastHashMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */