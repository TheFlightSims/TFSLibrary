/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntryDecorator;
/*     */ 
/*     */ public abstract class AbstractDualBidiMap implements BidiMap {
/*  51 */   protected final transient Map[] maps = new Map[2];
/*     */   
/*  55 */   protected transient BidiMap inverseBidiMap = null;
/*     */   
/*  59 */   protected transient Set keySet = null;
/*     */   
/*  63 */   protected transient Collection values = null;
/*     */   
/*  67 */   protected transient Set entrySet = null;
/*     */   
/*     */   protected AbstractDualBidiMap() {
/*  78 */     this.maps[0] = createMap();
/*  79 */     this.maps[1] = createMap();
/*     */   }
/*     */   
/*     */   protected AbstractDualBidiMap(Map normalMap, Map reverseMap) {
/*  98 */     this.maps[0] = normalMap;
/*  99 */     this.maps[1] = reverseMap;
/*     */   }
/*     */   
/*     */   protected AbstractDualBidiMap(Map normalMap, Map reverseMap, BidiMap inverseBidiMap) {
/* 112 */     this.maps[0] = normalMap;
/* 113 */     this.maps[1] = reverseMap;
/* 114 */     this.inverseBidiMap = inverseBidiMap;
/*     */   }
/*     */   
/*     */   protected Map createMap() {
/* 128 */     return null;
/*     */   }
/*     */   
/*     */   protected abstract BidiMap createBidiMap(Map paramMap1, Map paramMap2, BidiMap paramBidiMap);
/*     */   
/*     */   public Object get(Object key) {
/* 144 */     return this.maps[0].get(key);
/*     */   }
/*     */   
/*     */   public int size() {
/* 148 */     return this.maps[0].size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 152 */     return this.maps[0].isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 156 */     return this.maps[0].containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 160 */     return this.maps[0].equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 164 */     return this.maps[0].hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 168 */     return this.maps[0].toString();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 174 */     if (this.maps[0].containsKey(key))
/* 175 */       this.maps[1].remove(this.maps[0].get(key)); 
/* 177 */     if (this.maps[1].containsKey(value))
/* 178 */       this.maps[0].remove(this.maps[1].get(value)); 
/* 180 */     Object obj = this.maps[0].put(key, value);
/* 181 */     this.maps[1].put(value, key);
/* 182 */     return obj;
/*     */   }
/*     */   
/*     */   public void putAll(Map map) {
/* 186 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 187 */       Map.Entry entry = it.next();
/* 188 */       put(entry.getKey(), entry.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 193 */     Object value = null;
/* 194 */     if (this.maps[0].containsKey(key)) {
/* 195 */       value = this.maps[0].remove(key);
/* 196 */       this.maps[1].remove(value);
/*     */     } 
/* 198 */     return value;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 202 */     this.maps[0].clear();
/* 203 */     this.maps[1].clear();
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 207 */     return this.maps[1].containsKey(value);
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 224 */     return new BidiMapIterator(this);
/*     */   }
/*     */   
/*     */   public Object getKey(Object value) {
/* 228 */     return this.maps[1].get(value);
/*     */   }
/*     */   
/*     */   public Object removeValue(Object value) {
/* 232 */     Object key = null;
/* 233 */     if (this.maps[1].containsKey(value)) {
/* 234 */       key = this.maps[1].remove(value);
/* 235 */       this.maps[0].remove(key);
/*     */     } 
/* 237 */     return key;
/*     */   }
/*     */   
/*     */   public BidiMap inverseBidiMap() {
/* 241 */     if (this.inverseBidiMap == null)
/* 242 */       this.inverseBidiMap = createBidiMap(this.maps[1], this.maps[0], this); 
/* 244 */     return this.inverseBidiMap;
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 257 */     if (this.keySet == null)
/* 258 */       this.keySet = new KeySet(this); 
/* 260 */     return this.keySet;
/*     */   }
/*     */   
/*     */   protected Iterator createKeySetIterator(Iterator iterator) {
/* 271 */     return (Iterator)new KeySetIterator(iterator, this);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 282 */     if (this.values == null)
/* 283 */       this.values = new Values(this); 
/* 285 */     return this.values;
/*     */   }
/*     */   
/*     */   protected Iterator createValuesIterator(Iterator iterator) {
/* 296 */     return (Iterator)new ValuesIterator(iterator, this);
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 311 */     if (this.entrySet == null)
/* 312 */       this.entrySet = new EntrySet(this); 
/* 314 */     return this.entrySet;
/*     */   }
/*     */   
/*     */   protected Iterator createEntrySetIterator(Iterator iterator) {
/* 325 */     return (Iterator)new EntrySetIterator(iterator, this);
/*     */   }
/*     */   
/*     */   protected static abstract class View extends AbstractCollectionDecorator {
/*     */     protected final AbstractDualBidiMap parent;
/*     */     
/*     */     protected View(Collection coll, AbstractDualBidiMap parent) {
/* 344 */       super(coll);
/* 345 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection coll) {
/* 349 */       if (this.parent.isEmpty() || coll.isEmpty())
/* 350 */         return false; 
/* 352 */       boolean modified = false;
/* 353 */       Iterator it = iterator();
/* 354 */       while (it.hasNext()) {
/* 355 */         if (coll.contains(it.next())) {
/* 356 */           it.remove();
/* 357 */           modified = true;
/*     */         } 
/*     */       } 
/* 360 */       return modified;
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection coll) {
/* 364 */       if (this.parent.isEmpty())
/* 365 */         return false; 
/* 367 */       if (coll.isEmpty()) {
/* 368 */         this.parent.clear();
/* 369 */         return true;
/*     */       } 
/* 371 */       boolean modified = false;
/* 372 */       Iterator it = iterator();
/* 373 */       while (it.hasNext()) {
/* 374 */         if (!coll.contains(it.next())) {
/* 375 */           it.remove();
/* 376 */           modified = true;
/*     */         } 
/*     */       } 
/* 379 */       return modified;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 383 */       this.parent.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class KeySet extends View implements Set {
/*     */     protected KeySet(AbstractDualBidiMap parent) {
/* 399 */       super(parent.maps[0].keySet(), parent);
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 403 */       return this.parent.createKeySetIterator(super.iterator());
/*     */     }
/*     */     
/*     */     public boolean contains(Object key) {
/* 407 */       return this.parent.maps[0].containsKey(key);
/*     */     }
/*     */     
/*     */     public boolean remove(Object key) {
/* 411 */       if (this.parent.maps[0].containsKey(key)) {
/* 412 */         Object value = this.parent.maps[0].remove(key);
/* 413 */         this.parent.maps[1].remove(value);
/* 414 */         return true;
/*     */       } 
/* 416 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class KeySetIterator extends AbstractIteratorDecorator {
/*     */     protected final AbstractDualBidiMap parent;
/*     */     
/* 428 */     protected Object lastKey = null;
/*     */     
/*     */     protected boolean canRemove = false;
/*     */     
/*     */     protected KeySetIterator(Iterator iterator, AbstractDualBidiMap parent) {
/* 438 */       super(iterator);
/* 439 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 443 */       this.lastKey = super.next();
/* 444 */       this.canRemove = true;
/* 445 */       return this.lastKey;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 449 */       if (!this.canRemove)
/* 450 */         throw new IllegalStateException("Iterator remove() can only be called once after next()"); 
/* 452 */       Object value = this.parent.maps[0].get(this.lastKey);
/* 453 */       super.remove();
/* 454 */       this.parent.maps[1].remove(value);
/* 455 */       this.lastKey = null;
/* 456 */       this.canRemove = false;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class Values extends View implements Set {
/*     */     protected Values(AbstractDualBidiMap parent) {
/* 472 */       super(parent.maps[0].values(), parent);
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 476 */       return this.parent.createValuesIterator(super.iterator());
/*     */     }
/*     */     
/*     */     public boolean contains(Object value) {
/* 480 */       return this.parent.maps[1].containsKey(value);
/*     */     }
/*     */     
/*     */     public boolean remove(Object value) {
/* 484 */       if (this.parent.maps[1].containsKey(value)) {
/* 485 */         Object key = this.parent.maps[1].remove(value);
/* 486 */         this.parent.maps[0].remove(key);
/* 487 */         return true;
/*     */       } 
/* 489 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class ValuesIterator extends AbstractIteratorDecorator {
/*     */     protected final AbstractDualBidiMap parent;
/*     */     
/* 501 */     protected Object lastValue = null;
/*     */     
/*     */     protected boolean canRemove = false;
/*     */     
/*     */     protected ValuesIterator(Iterator iterator, AbstractDualBidiMap parent) {
/* 511 */       super(iterator);
/* 512 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 516 */       this.lastValue = super.next();
/* 517 */       this.canRemove = true;
/* 518 */       return this.lastValue;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 522 */       if (!this.canRemove)
/* 523 */         throw new IllegalStateException("Iterator remove() can only be called once after next()"); 
/* 525 */       super.remove();
/* 526 */       this.parent.maps[1].remove(this.lastValue);
/* 527 */       this.lastValue = null;
/* 528 */       this.canRemove = false;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class EntrySet extends View implements Set {
/*     */     protected EntrySet(AbstractDualBidiMap parent) {
/* 544 */       super(parent.maps[0].entrySet(), parent);
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 548 */       return this.parent.createEntrySetIterator(super.iterator());
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 552 */       if (!(obj instanceof Map.Entry))
/* 553 */         return false; 
/* 555 */       Map.Entry entry = (Map.Entry)obj;
/* 556 */       Object key = entry.getKey();
/* 557 */       if (this.parent.containsKey(key)) {
/* 558 */         Object value = this.parent.maps[0].get(key);
/* 559 */         if ((value == null) ? (entry.getValue() == null) : value.equals(entry.getValue())) {
/* 560 */           this.parent.maps[0].remove(key);
/* 561 */           this.parent.maps[1].remove(value);
/* 562 */           return true;
/*     */         } 
/*     */       } 
/* 565 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class EntrySetIterator extends AbstractIteratorDecorator {
/*     */     protected final AbstractDualBidiMap parent;
/*     */     
/* 577 */     protected Map.Entry last = null;
/*     */     
/*     */     protected boolean canRemove = false;
/*     */     
/*     */     protected EntrySetIterator(Iterator iterator, AbstractDualBidiMap parent) {
/* 587 */       super(iterator);
/* 588 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 592 */       this.last = (Map.Entry)new AbstractDualBidiMap.MapEntry((Map.Entry)super.next(), this.parent);
/* 593 */       this.canRemove = true;
/* 594 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 598 */       if (!this.canRemove)
/* 599 */         throw new IllegalStateException("Iterator remove() can only be called once after next()"); 
/* 602 */       Object value = this.last.getValue();
/* 603 */       super.remove();
/* 604 */       this.parent.maps[1].remove(value);
/* 605 */       this.last = null;
/* 606 */       this.canRemove = false;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class MapEntry extends AbstractMapEntryDecorator {
/*     */     protected final AbstractDualBidiMap parent;
/*     */     
/*     */     protected MapEntry(Map.Entry entry, AbstractDualBidiMap parent) {
/* 624 */       super(entry);
/* 625 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 629 */       Object key = getKey();
/* 630 */       if (this.parent.maps[1].containsKey(value) && this.parent.maps[1].get(value) != key)
/* 632 */         throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map"); 
/* 634 */       this.parent.put(key, value);
/* 635 */       Object oldValue = super.setValue(value);
/* 636 */       return oldValue;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class BidiMapIterator implements MapIterator, ResettableIterator {
/*     */     protected final AbstractDualBidiMap parent;
/*     */     
/*     */     protected Iterator iterator;
/*     */     
/* 650 */     protected Map.Entry last = null;
/*     */     
/*     */     protected boolean canRemove = false;
/*     */     
/*     */     protected BidiMapIterator(AbstractDualBidiMap parent) {
/* 660 */       this.parent = parent;
/* 661 */       this.iterator = parent.maps[0].entrySet().iterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 665 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 669 */       this.last = this.iterator.next();
/* 670 */       this.canRemove = true;
/* 671 */       return this.last.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 675 */       if (!this.canRemove)
/* 676 */         throw new IllegalStateException("Iterator remove() can only be called once after next()"); 
/* 679 */       Object value = this.last.getValue();
/* 680 */       this.iterator.remove();
/* 681 */       this.parent.maps[1].remove(value);
/* 682 */       this.last = null;
/* 683 */       this.canRemove = false;
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 687 */       if (this.last == null)
/* 688 */         throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()"); 
/* 690 */       return this.last.getKey();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 694 */       if (this.last == null)
/* 695 */         throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()"); 
/* 697 */       return this.last.getValue();
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 701 */       if (this.last == null)
/* 702 */         throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()"); 
/* 704 */       if (this.parent.maps[1].containsKey(value) && this.parent.maps[1].get(value) != this.last.getKey())
/* 706 */         throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map"); 
/* 708 */       return this.parent.put(this.last.getKey(), value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 712 */       this.iterator = this.parent.maps[0].entrySet().iterator();
/* 713 */       this.last = null;
/* 714 */       this.canRemove = false;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 718 */       if (this.last != null)
/* 719 */         return "MapIterator[" + getKey() + "=" + getValue() + "]"; 
/* 721 */       return "MapIterator[]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bidimap\AbstractDualBidiMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */