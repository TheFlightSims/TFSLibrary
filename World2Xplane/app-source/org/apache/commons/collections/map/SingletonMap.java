/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.BoundedMap;
/*     */ import org.apache.commons.collections.KeyValue;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ import org.apache.commons.collections.iterators.SingletonIterator;
/*     */ import org.apache.commons.collections.keyvalue.TiedMapEntry;
/*     */ 
/*     */ public class SingletonMap implements OrderedMap, BoundedMap, KeyValue, Serializable, Cloneable {
/*     */   private static final long serialVersionUID = -8931271118676803261L;
/*     */   
/*     */   private final Object key;
/*     */   
/*     */   private Object value;
/*     */   
/*     */   public SingletonMap() {
/*  78 */     this.key = null;
/*     */   }
/*     */   
/*     */   public SingletonMap(Object key, Object value) {
/*  89 */     this.key = key;
/*  90 */     this.value = value;
/*     */   }
/*     */   
/*     */   public SingletonMap(KeyValue keyValue) {
/* 100 */     this.key = keyValue.getKey();
/* 101 */     this.value = keyValue.getValue();
/*     */   }
/*     */   
/*     */   public SingletonMap(Map.Entry mapEntry) {
/* 111 */     this.key = mapEntry.getKey();
/* 112 */     this.value = mapEntry.getValue();
/*     */   }
/*     */   
/*     */   public SingletonMap(Map map) {
/* 124 */     if (map.size() != 1)
/* 125 */       throw new IllegalArgumentException("The map size must be 1"); 
/* 127 */     Map.Entry entry = map.entrySet().iterator().next();
/* 128 */     this.key = entry.getKey();
/* 129 */     this.value = entry.getValue();
/*     */   }
/*     */   
/*     */   public Object getKey() {
/* 140 */     return this.key;
/*     */   }
/*     */   
/*     */   public Object getValue() {
/* 149 */     return this.value;
/*     */   }
/*     */   
/*     */   public Object setValue(Object value) {
/* 159 */     Object old = this.value;
/* 160 */     this.value = value;
/* 161 */     return old;
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 172 */     return true;
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 181 */     return 1;
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 193 */     if (isEqualKey(key))
/* 194 */       return this.value; 
/* 196 */     return null;
/*     */   }
/*     */   
/*     */   public int size() {
/* 205 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 214 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 225 */     return isEqualKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 235 */     return isEqualValue(value);
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 251 */     if (isEqualKey(key))
/* 252 */       return setValue(value); 
/* 254 */     throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size singleton");
/*     */   }
/*     */   
/*     */   public void putAll(Map map) {
/*     */     Map.Entry entry;
/* 269 */     switch (map.size()) {
/*     */       case 0:
/*     */         return;
/*     */       case 1:
/* 274 */         entry = map.entrySet().iterator().next();
/* 275 */         put(entry.getKey(), entry.getValue());
/*     */         return;
/*     */     } 
/* 279 */     throw new IllegalArgumentException("The map size must be 0 or 1");
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 291 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 298 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 310 */     TiedMapEntry tiedMapEntry = new TiedMapEntry((Map)this, getKey());
/* 311 */     return Collections.singleton(tiedMapEntry);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 322 */     return Collections.singleton(this.key);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 333 */     return new SingletonValues(this);
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 349 */     return (MapIterator)new SingletonMapIterator(this);
/*     */   }
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 363 */     return new SingletonMapIterator(this);
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/* 372 */     return getKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 381 */     return getKey();
/*     */   }
/*     */   
/*     */   public Object nextKey(Object key) {
/* 391 */     return null;
/*     */   }
/*     */   
/*     */   public Object previousKey(Object key) {
/* 401 */     return null;
/*     */   }
/*     */   
/*     */   protected boolean isEqualKey(Object key) {
/* 412 */     return (key == null) ? ((getKey() == null)) : key.equals(getKey());
/*     */   }
/*     */   
/*     */   protected boolean isEqualValue(Object value) {
/* 422 */     return (value == null) ? ((getValue() == null)) : value.equals(getValue());
/*     */   }
/*     */   
/*     */   static class SingletonMapIterator implements OrderedMapIterator, ResettableIterator {
/*     */     private final SingletonMap parent;
/*     */     
/*     */     private boolean hasNext = true;
/*     */     
/*     */     private boolean canGetSet = false;
/*     */     
/*     */     SingletonMapIterator(SingletonMap parent) {
/* 436 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 440 */       return this.hasNext;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 444 */       if (!this.hasNext)
/* 445 */         throw new NoSuchElementException("No next() entry in the iteration"); 
/* 447 */       this.hasNext = false;
/* 448 */       this.canGetSet = true;
/* 449 */       return this.parent.getKey();
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 453 */       return !this.hasNext;
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 457 */       if (this.hasNext == true)
/* 458 */         throw new NoSuchElementException("No previous() entry in the iteration"); 
/* 460 */       this.hasNext = true;
/* 461 */       return this.parent.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 465 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 469 */       if (!this.canGetSet)
/* 470 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()"); 
/* 472 */       return this.parent.getKey();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 476 */       if (!this.canGetSet)
/* 477 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()"); 
/* 479 */       return this.parent.getValue();
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 483 */       if (!this.canGetSet)
/* 484 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()"); 
/* 486 */       return this.parent.setValue(value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 490 */       this.hasNext = true;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 494 */       if (this.hasNext)
/* 495 */         return "Iterator[]"; 
/* 497 */       return "Iterator[" + getKey() + "=" + getValue() + "]";
/*     */     }
/*     */   }
/*     */   
/*     */   static class SingletonValues extends AbstractSet implements Serializable {
/*     */     private static final long serialVersionUID = -3689524741863047872L;
/*     */     
/*     */     private final SingletonMap parent;
/*     */     
/*     */     SingletonValues(SingletonMap parent) {
/* 512 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 516 */       return 1;
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 519 */       return false;
/*     */     }
/*     */     
/*     */     public boolean contains(Object object) {
/* 522 */       return this.parent.containsValue(object);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 525 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 528 */       return (Iterator)new SingletonIterator(this.parent.getValue(), false);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 540 */       SingletonMap cloned = (SingletonMap)super.clone();
/* 541 */       return cloned;
/* 542 */     } catch (CloneNotSupportedException ex) {
/* 543 */       throw new InternalError();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 554 */     if (obj == this)
/* 555 */       return true; 
/* 557 */     if (!(obj instanceof Map))
/* 558 */       return false; 
/* 560 */     Map other = (Map)obj;
/* 561 */     if (other.size() != 1)
/* 562 */       return false; 
/* 564 */     Map.Entry entry = other.entrySet().iterator().next();
/* 565 */     return (isEqualKey(entry.getKey()) && isEqualValue(entry.getValue()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 574 */     return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 584 */     return (new StringBuffer(128)).append('{').append((getKey() == this) ? "(this Map)" : getKey()).append('=').append((getValue() == this) ? "(this Map)" : getValue()).append('}').toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\SingletonMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */