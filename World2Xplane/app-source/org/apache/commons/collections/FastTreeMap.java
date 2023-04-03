/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class FastTreeMap extends TreeMap {
/*  73 */   protected TreeMap map = null;
/*     */   
/*     */   protected boolean fast = false;
/*     */   
/*     */   public FastTreeMap() {
/*  89 */     this.map = new TreeMap();
/*     */   }
/*     */   
/*     */   public FastTreeMap(Comparator comparator) {
/*  99 */     this.map = new TreeMap(comparator);
/*     */   }
/*     */   
/*     */   public FastTreeMap(Map map) {
/* 110 */     this.map = new TreeMap(map);
/*     */   }
/*     */   
/*     */   public FastTreeMap(SortedMap map) {
/* 121 */     this.map = new TreeMap(map);
/*     */   }
/*     */   
/*     */   public boolean getFast() {
/* 134 */     return this.fast;
/*     */   }
/*     */   
/*     */   public void setFast(boolean fast) {
/* 143 */     this.fast = fast;
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 162 */     if (this.fast)
/* 163 */       return this.map.get(key); 
/* 165 */     synchronized (this.map) {
/* 166 */       return this.map.get(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 177 */     if (this.fast)
/* 178 */       return this.map.size(); 
/* 180 */     synchronized (this.map) {
/* 181 */       return this.map.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 192 */     if (this.fast)
/* 193 */       return this.map.isEmpty(); 
/* 195 */     synchronized (this.map) {
/* 196 */       return this.map.isEmpty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 209 */     if (this.fast)
/* 210 */       return this.map.containsKey(key); 
/* 212 */     synchronized (this.map) {
/* 213 */       return this.map.containsKey(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 226 */     if (this.fast)
/* 227 */       return this.map.containsValue(value); 
/* 229 */     synchronized (this.map) {
/* 230 */       return this.map.containsValue(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 242 */     if (this.fast)
/* 243 */       return this.map.comparator(); 
/* 245 */     synchronized (this.map) {
/* 246 */       return this.map.comparator();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/* 257 */     if (this.fast)
/* 258 */       return this.map.firstKey(); 
/* 260 */     synchronized (this.map) {
/* 261 */       return this.map.firstKey();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 272 */     if (this.fast)
/* 273 */       return this.map.lastKey(); 
/* 275 */     synchronized (this.map) {
/* 276 */       return this.map.lastKey();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 298 */     if (this.fast)
/* 299 */       synchronized (this) {
/* 300 */         TreeMap temp = (TreeMap)this.map.clone();
/* 301 */         Object result = temp.put(key, value);
/* 302 */         this.map = temp;
/* 303 */         return result;
/*     */       }  
/* 306 */     synchronized (this.map) {
/* 307 */       return this.map.put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(Map in) {
/* 319 */     if (this.fast) {
/* 320 */       synchronized (this) {
/* 321 */         TreeMap temp = (TreeMap)this.map.clone();
/* 322 */         temp.putAll(in);
/* 323 */         this.map = temp;
/*     */       } 
/*     */     } else {
/* 326 */       synchronized (this.map) {
/* 327 */         this.map.putAll(in);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 340 */     if (this.fast)
/* 341 */       synchronized (this) {
/* 342 */         TreeMap temp = (TreeMap)this.map.clone();
/* 343 */         Object result = temp.remove(key);
/* 344 */         this.map = temp;
/* 345 */         return result;
/*     */       }  
/* 348 */     synchronized (this.map) {
/* 349 */       return this.map.remove(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 358 */     if (this.fast) {
/* 359 */       synchronized (this) {
/* 360 */         this.map = new TreeMap();
/*     */       } 
/*     */     } else {
/* 363 */       synchronized (this.map) {
/* 364 */         this.map.clear();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 384 */     if (o == this)
/* 385 */       return true; 
/* 386 */     if (!(o instanceof Map))
/* 387 */       return false; 
/* 389 */     Map mo = (Map)o;
/* 392 */     if (this.fast) {
/* 393 */       if (mo.size() != this.map.size())
/* 394 */         return false; 
/* 396 */       Iterator i = this.map.entrySet().iterator();
/* 397 */       while (i.hasNext()) {
/* 398 */         Map.Entry e = i.next();
/* 399 */         Object key = e.getKey();
/* 400 */         Object value = e.getValue();
/* 401 */         if (value == null) {
/* 402 */           if (mo.get(key) != null || !mo.containsKey(key))
/* 403 */             return false; 
/*     */           continue;
/*     */         } 
/* 406 */         if (!value.equals(mo.get(key)))
/* 407 */           return false; 
/*     */       } 
/* 411 */       return true;
/*     */     } 
/* 413 */     synchronized (this.map) {
/* 414 */       if (mo.size() != this.map.size())
/* 415 */         return false; 
/* 417 */       Iterator i = this.map.entrySet().iterator();
/* 418 */       while (i.hasNext()) {
/* 419 */         Map.Entry e = i.next();
/* 420 */         Object key = e.getKey();
/* 421 */         Object value = e.getValue();
/* 422 */         if (value == null) {
/* 423 */           if (mo.get(key) != null || !mo.containsKey(key))
/* 424 */             return false; 
/*     */           continue;
/*     */         } 
/* 427 */         if (!value.equals(mo.get(key)))
/* 428 */           return false; 
/*     */       } 
/* 432 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 445 */     if (this.fast) {
/* 446 */       int h = 0;
/* 447 */       Iterator i = this.map.entrySet().iterator();
/* 448 */       while (i.hasNext())
/* 449 */         h += i.next().hashCode(); 
/* 451 */       return h;
/*     */     } 
/* 453 */     synchronized (this.map) {
/* 454 */       int h = 0;
/* 455 */       Iterator i = this.map.entrySet().iterator();
/* 456 */       while (i.hasNext())
/* 457 */         h += i.next().hashCode(); 
/* 459 */       return h;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 471 */     FastTreeMap results = null;
/* 472 */     if (this.fast) {
/* 473 */       results = new FastTreeMap(this.map);
/*     */     } else {
/* 475 */       synchronized (this.map) {
/* 476 */         results = new FastTreeMap(this.map);
/*     */       } 
/*     */     } 
/* 479 */     results.setFast(getFast());
/* 480 */     return results;
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object key) {
/* 495 */     if (this.fast)
/* 496 */       return this.map.headMap(key); 
/* 498 */     synchronized (this.map) {
/* 499 */       return this.map.headMap(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 513 */     if (this.fast)
/* 514 */       return this.map.subMap(fromKey, toKey); 
/* 516 */     synchronized (this.map) {
/* 517 */       return this.map.subMap(fromKey, toKey);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object key) {
/* 530 */     if (this.fast)
/* 531 */       return this.map.tailMap(key); 
/* 533 */     synchronized (this.map) {
/* 534 */       return this.map.tailMap(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 548 */     return new EntrySet();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 555 */     return new KeySet();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 562 */     return new Values();
/*     */   }
/*     */   
/*     */   private abstract class CollectionView implements Collection {
/*     */     private final FastTreeMap this$0;
/*     */     
/*     */     public CollectionView(FastTreeMap this$0) {
/* 573 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 581 */       if (this.this$0.fast) {
/* 582 */         synchronized (this.this$0) {
/* 583 */           this.this$0.map = new TreeMap();
/*     */         } 
/*     */       } else {
/* 586 */         synchronized (this.this$0.map) {
/* 587 */           get(this.this$0.map).clear();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 593 */       if (this.this$0.fast)
/* 594 */         synchronized (this.this$0) {
/* 595 */           TreeMap temp = (TreeMap)this.this$0.map.clone();
/* 596 */           boolean r = get(temp).remove(o);
/* 597 */           this.this$0.map = temp;
/* 598 */           return r;
/*     */         }  
/* 601 */       synchronized (this.this$0.map) {
/* 602 */         return get(this.this$0.map).remove(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection o) {
/* 608 */       if (this.this$0.fast)
/* 609 */         synchronized (this.this$0) {
/* 610 */           TreeMap temp = (TreeMap)this.this$0.map.clone();
/* 611 */           boolean r = get(temp).removeAll(o);
/* 612 */           this.this$0.map = temp;
/* 613 */           return r;
/*     */         }  
/* 616 */       synchronized (this.this$0.map) {
/* 617 */         return get(this.this$0.map).removeAll(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection o) {
/* 623 */       if (this.this$0.fast)
/* 624 */         synchronized (this.this$0) {
/* 625 */           TreeMap temp = (TreeMap)this.this$0.map.clone();
/* 626 */           boolean r = get(temp).retainAll(o);
/* 627 */           this.this$0.map = temp;
/* 628 */           return r;
/*     */         }  
/* 631 */       synchronized (this.this$0.map) {
/* 632 */         return get(this.this$0.map).retainAll(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public int size() {
/* 638 */       if (this.this$0.fast)
/* 639 */         return get(this.this$0.map).size(); 
/* 641 */       synchronized (this.this$0.map) {
/* 642 */         return get(this.this$0.map).size();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 649 */       if (this.this$0.fast)
/* 650 */         return get(this.this$0.map).isEmpty(); 
/* 652 */       synchronized (this.this$0.map) {
/* 653 */         return get(this.this$0.map).isEmpty();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 659 */       if (this.this$0.fast)
/* 660 */         return get(this.this$0.map).contains(o); 
/* 662 */       synchronized (this.this$0.map) {
/* 663 */         return get(this.this$0.map).contains(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection o) {
/* 669 */       if (this.this$0.fast)
/* 670 */         return get(this.this$0.map).containsAll(o); 
/* 672 */       synchronized (this.this$0.map) {
/* 673 */         return get(this.this$0.map).containsAll(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object[] toArray(Object[] o) {
/* 679 */       if (this.this$0.fast)
/* 680 */         return get(this.this$0.map).toArray(o); 
/* 682 */       synchronized (this.this$0.map) {
/* 683 */         return get(this.this$0.map).toArray(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 689 */       if (this.this$0.fast)
/* 690 */         return get(this.this$0.map).toArray(); 
/* 692 */       synchronized (this.this$0.map) {
/* 693 */         return get(this.this$0.map).toArray();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 700 */       if (o == this)
/* 700 */         return true; 
/* 701 */       if (this.this$0.fast)
/* 702 */         return get(this.this$0.map).equals(o); 
/* 704 */       synchronized (this.this$0.map) {
/* 705 */         return get(this.this$0.map).equals(o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 711 */       if (this.this$0.fast)
/* 712 */         return get(this.this$0.map).hashCode(); 
/* 714 */       synchronized (this.this$0.map) {
/* 715 */         return get(this.this$0.map).hashCode();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean add(Object o) {
/* 721 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection c) {
/* 725 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 729 */       return new CollectionViewIterator(this);
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
/*     */       private final FastTreeMap.CollectionView this$1;
/*     */       
/*     */       public CollectionViewIterator(FastTreeMap.CollectionView this$0) {
/* 738 */         this.this$1 = this$0;
/*     */         this.lastReturned = null;
/* 739 */         this.expected = this$0.this$0.map;
/* 740 */         this.iterator = this.expected.entrySet().iterator();
/*     */       }
/*     */       
/*     */       public boolean hasNext() {
/* 744 */         if (this.expected != this.this$1.this$0.map)
/* 745 */           throw new ConcurrentModificationException(); 
/* 747 */         return this.iterator.hasNext();
/*     */       }
/*     */       
/*     */       public Object next() {
/* 751 */         if (this.expected != this.this$1.this$0.map)
/* 752 */           throw new ConcurrentModificationException(); 
/* 754 */         this.lastReturned = this.iterator.next();
/* 755 */         return this.this$1.iteratorNext(this.lastReturned);
/*     */       }
/*     */       
/*     */       public void remove() {
/* 759 */         if (this.lastReturned == null)
/* 760 */           throw new IllegalStateException(); 
/* 762 */         if (this.this$1.this$0.fast) {
/* 763 */           synchronized (this.this$1.this$0) {
/* 764 */             if (this.expected != this.this$1.this$0.map)
/* 765 */               throw new ConcurrentModificationException(); 
/* 767 */             this.this$1.this$0.remove(this.lastReturned.getKey());
/* 768 */             this.lastReturned = null;
/* 769 */             this.expected = this.this$1.this$0.map;
/*     */           } 
/*     */         } else {
/* 772 */           this.iterator.remove();
/* 773 */           this.lastReturned = null;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeySet extends CollectionView implements Set {
/*     */     private final FastTreeMap this$0;
/*     */     
/*     */     private KeySet(FastTreeMap this$0) {
/* 782 */       FastTreeMap.this = FastTreeMap.this;
/*     */     }
/*     */     
/*     */     protected Collection get(Map map) {
/* 785 */       return map.keySet();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 789 */       return entry.getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private class Values extends CollectionView {
/*     */     private final FastTreeMap this$0;
/*     */     
/*     */     private Values(FastTreeMap this$0) {
/* 797 */       FastTreeMap.this = FastTreeMap.this;
/*     */     }
/*     */     
/*     */     protected Collection get(Map map) {
/* 800 */       return map.values();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 804 */       return entry.getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntrySet extends CollectionView implements Set {
/*     */     private final FastTreeMap this$0;
/*     */     
/*     */     private EntrySet(FastTreeMap this$0) {
/* 811 */       FastTreeMap.this = FastTreeMap.this;
/*     */     }
/*     */     
/*     */     protected Collection get(Map map) {
/* 814 */       return map.entrySet();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 819 */       return entry;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\FastTreeMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */