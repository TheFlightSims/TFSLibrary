/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractList;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntry;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ 
/*     */ public class ListOrderedMap extends AbstractMapDecorator implements OrderedMap, Serializable {
/*     */   private static final long serialVersionUID = 2728177751851003750L;
/*     */   
/*  79 */   protected final List insertOrder = new ArrayList();
/*     */   
/*     */   public static OrderedMap decorate(Map map) {
/*  90 */     return new ListOrderedMap(map);
/*     */   }
/*     */   
/*     */   public ListOrderedMap() {
/* 101 */     this(new HashMap());
/*     */   }
/*     */   
/*     */   protected ListOrderedMap(Map map) {
/* 111 */     super(map);
/* 112 */     this.insertOrder.addAll(getMap().keySet());
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 124 */     out.defaultWriteObject();
/* 125 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 137 */     in.defaultReadObject();
/* 138 */     this.map = (Map)in.readObject();
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 144 */     return (MapIterator)orderedMapIterator();
/*     */   }
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 148 */     return new ListOrderedMapIterator(this);
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/* 158 */     if (size() == 0)
/* 159 */       throw new NoSuchElementException("Map is empty"); 
/* 161 */     return this.insertOrder.get(0);
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 171 */     if (size() == 0)
/* 172 */       throw new NoSuchElementException("Map is empty"); 
/* 174 */     return this.insertOrder.get(size() - 1);
/*     */   }
/*     */   
/*     */   public Object nextKey(Object key) {
/* 185 */     int index = this.insertOrder.indexOf(key);
/* 186 */     if (index >= 0 && index < size() - 1)
/* 187 */       return this.insertOrder.get(index + 1); 
/* 189 */     return null;
/*     */   }
/*     */   
/*     */   public Object previousKey(Object key) {
/* 200 */     int index = this.insertOrder.indexOf(key);
/* 201 */     if (index > 0)
/* 202 */       return this.insertOrder.get(index - 1); 
/* 204 */     return null;
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 209 */     if (getMap().containsKey(key))
/* 211 */       return getMap().put(key, value); 
/* 214 */     Object result = getMap().put(key, value);
/* 215 */     this.insertOrder.add(key);
/* 216 */     return result;
/*     */   }
/*     */   
/*     */   public void putAll(Map map) {
/* 221 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 222 */       Map.Entry entry = it.next();
/* 223 */       put(entry.getKey(), entry.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 228 */     Object result = getMap().remove(key);
/* 229 */     this.insertOrder.remove(key);
/* 230 */     return result;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 234 */     getMap().clear();
/* 235 */     this.insertOrder.clear();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 248 */     return new KeySetView(this);
/*     */   }
/*     */   
/*     */   public List keyList() {
/* 262 */     return UnmodifiableList.decorate(this.insertOrder);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 277 */     return new ValuesView(this);
/*     */   }
/*     */   
/*     */   public List valueList() {
/* 291 */     return new ValuesView(this);
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 302 */     return new EntrySetView(this, this.insertOrder);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 312 */     if (isEmpty())
/* 313 */       return "{}"; 
/* 315 */     StringBuffer buf = new StringBuffer();
/* 316 */     buf.append('{');
/* 317 */     boolean first = true;
/* 318 */     Iterator it = entrySet().iterator();
/* 319 */     while (it.hasNext()) {
/* 320 */       Map.Entry entry = it.next();
/* 321 */       Object key = entry.getKey();
/* 322 */       Object value = entry.getValue();
/* 323 */       if (first) {
/* 324 */         first = false;
/*     */       } else {
/* 326 */         buf.append(", ");
/*     */       } 
/* 328 */       buf.append((key == this) ? "(this Map)" : key);
/* 329 */       buf.append('=');
/* 330 */       buf.append((value == this) ? "(this Map)" : value);
/*     */     } 
/* 332 */     buf.append('}');
/* 333 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/* 345 */     return this.insertOrder.get(index);
/*     */   }
/*     */   
/*     */   public Object getValue(int index) {
/* 356 */     return get(this.insertOrder.get(index));
/*     */   }
/*     */   
/*     */   public int indexOf(Object key) {
/* 366 */     return this.insertOrder.indexOf(key);
/*     */   }
/*     */   
/*     */   public Object setValue(int index, Object value) {
/* 378 */     Object key = this.insertOrder.get(index);
/* 379 */     return put(key, value);
/*     */   }
/*     */   
/*     */   public Object put(int index, Object key, Object value) {
/* 402 */     Map m = getMap();
/* 403 */     if (m.containsKey(key)) {
/* 404 */       Object result = m.remove(key);
/* 405 */       int pos = this.insertOrder.indexOf(key);
/* 406 */       this.insertOrder.remove(pos);
/* 407 */       if (pos < index)
/* 408 */         index--; 
/* 410 */       this.insertOrder.add(index, key);
/* 411 */       m.put(key, value);
/* 412 */       return result;
/*     */     } 
/* 414 */     this.insertOrder.add(index, key);
/* 415 */     m.put(key, value);
/* 416 */     return null;
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 428 */     return remove(get(index));
/*     */   }
/*     */   
/*     */   public List asList() {
/* 449 */     return keyList();
/*     */   }
/*     */   
/*     */   static class ValuesView extends AbstractList {
/*     */     private final ListOrderedMap parent;
/*     */     
/*     */     ValuesView(ListOrderedMap parent) {
/* 458 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 462 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public boolean contains(Object value) {
/* 466 */       return this.parent.containsValue(value);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 470 */       this.parent.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 474 */       return (Iterator)new AbstractIteratorDecorator(this, this.parent.entrySet().iterator()) {
/*     */           private final ListOrderedMap.ValuesView this$0;
/*     */           
/*     */           public Object next() {
/* 476 */             return ((Map.Entry)this.iterator.next()).getValue();
/*     */           }
/*     */         };
/*     */     }
/*     */     
/*     */     public Object get(int index) {
/* 482 */       return this.parent.getValue(index);
/*     */     }
/*     */     
/*     */     public Object set(int index, Object value) {
/* 486 */       return this.parent.setValue(index, value);
/*     */     }
/*     */     
/*     */     public Object remove(int index) {
/* 490 */       return this.parent.remove(index);
/*     */     }
/*     */   }
/*     */   
/*     */   static class KeySetView extends AbstractSet {
/*     */     private final ListOrderedMap parent;
/*     */     
/*     */     KeySetView(ListOrderedMap parent) {
/* 500 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 504 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public boolean contains(Object value) {
/* 508 */       return this.parent.containsKey(value);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 512 */       this.parent.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 516 */       return (Iterator)new AbstractIteratorDecorator(this, this.parent.entrySet().iterator()) {
/*     */           private final ListOrderedMap.KeySetView this$0;
/*     */           
/*     */           public Object next() {
/* 518 */             return ((Map.Entry)super.next()).getKey();
/*     */           }
/*     */         };
/*     */     }
/*     */   }
/*     */   
/*     */   static class EntrySetView extends AbstractSet {
/*     */     private final ListOrderedMap parent;
/*     */     
/*     */     private final List insertOrder;
/*     */     
/*     */     private Set entrySet;
/*     */     
/*     */     public EntrySetView(ListOrderedMap parent, List insertOrder) {
/* 532 */       this.parent = parent;
/* 533 */       this.insertOrder = insertOrder;
/*     */     }
/*     */     
/*     */     private Set getEntrySet() {
/* 537 */       if (this.entrySet == null)
/* 538 */         this.entrySet = this.parent.getMap().entrySet(); 
/* 540 */       return this.entrySet;
/*     */     }
/*     */     
/*     */     public int size() {
/* 544 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 547 */       return this.parent.isEmpty();
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 551 */       return getEntrySet().contains(obj);
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection coll) {
/* 555 */       return getEntrySet().containsAll(coll);
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 559 */       if (!(obj instanceof Map.Entry))
/* 560 */         return false; 
/* 562 */       if (getEntrySet().contains(obj)) {
/* 563 */         Object key = ((Map.Entry)obj).getKey();
/* 564 */         this.parent.remove(key);
/* 565 */         return true;
/*     */       } 
/* 567 */       return false;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 571 */       this.parent.clear();
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 575 */       if (obj == this)
/* 576 */         return true; 
/* 578 */       return getEntrySet().equals(obj);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 582 */       return getEntrySet().hashCode();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 586 */       return getEntrySet().toString();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 590 */       return (Iterator)new ListOrderedMap.ListOrderedIterator(this.parent, this.insertOrder);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ListOrderedIterator extends AbstractIteratorDecorator {
/*     */     private final ListOrderedMap parent;
/*     */     
/* 597 */     private Object last = null;
/*     */     
/*     */     ListOrderedIterator(ListOrderedMap parent, List insertOrder) {
/* 600 */       super(insertOrder.iterator());
/* 601 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 605 */       this.last = super.next();
/* 606 */       return new ListOrderedMap.ListOrderedMapEntry(this.parent, this.last);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 610 */       super.remove();
/* 611 */       this.parent.getMap().remove(this.last);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ListOrderedMapEntry extends AbstractMapEntry {
/*     */     private final ListOrderedMap parent;
/*     */     
/*     */     ListOrderedMapEntry(ListOrderedMap parent, Object key) {
/* 620 */       super(key, null);
/* 621 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 625 */       return this.parent.get(this.key);
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 629 */       return this.parent.getMap().put(this.key, value);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ListOrderedMapIterator implements OrderedMapIterator, ResettableIterator {
/*     */     private final ListOrderedMap parent;
/*     */     
/*     */     private ListIterator iterator;
/*     */     
/* 637 */     private Object last = null;
/*     */     
/*     */     private boolean readable = false;
/*     */     
/*     */     ListOrderedMapIterator(ListOrderedMap parent) {
/* 642 */       this.parent = parent;
/* 643 */       this.iterator = parent.insertOrder.listIterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 647 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 651 */       this.last = this.iterator.next();
/* 652 */       this.readable = true;
/* 653 */       return this.last;
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 657 */       return this.iterator.hasPrevious();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 661 */       this.last = this.iterator.previous();
/* 662 */       this.readable = true;
/* 663 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 667 */       if (!this.readable)
/* 668 */         throw new IllegalStateException("remove() can only be called once after next()"); 
/* 670 */       this.iterator.remove();
/* 671 */       this.parent.map.remove(this.last);
/* 672 */       this.readable = false;
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 676 */       if (!this.readable)
/* 677 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()"); 
/* 679 */       return this.last;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 683 */       if (!this.readable)
/* 684 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()"); 
/* 686 */       return this.parent.get(this.last);
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 690 */       if (!this.readable)
/* 691 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()"); 
/* 693 */       return this.parent.map.put(this.last, value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 697 */       this.iterator = this.parent.insertOrder.listIterator();
/* 698 */       this.last = null;
/* 699 */       this.readable = false;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 703 */       if (this.readable == true)
/* 704 */         return "Iterator[" + getKey() + "=" + getValue() + "]"; 
/* 706 */       return "Iterator[]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\ListOrderedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */