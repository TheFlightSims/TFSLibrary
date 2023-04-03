/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.keyvalue.DefaultMapEntry;
/*     */ 
/*     */ public class ReferenceMap extends AbstractMap {
/*     */   private static final long serialVersionUID = -3370601314380922368L;
/*     */   
/*     */   public static final int HARD = 0;
/*     */   
/*     */   public static final int SOFT = 1;
/*     */   
/*     */   public static final int WEAK = 2;
/*     */   
/*     */   private int keyType;
/*     */   
/*     */   private int valueType;
/*     */   
/*     */   private float loadFactor;
/*     */   
/*     */   private boolean purgeValues = false;
/*     */   
/* 153 */   private transient ReferenceQueue queue = new ReferenceQueue();
/*     */   
/*     */   private transient Entry[] table;
/*     */   
/*     */   private transient int size;
/*     */   
/*     */   private transient int threshold;
/*     */   
/*     */   private volatile transient int modCount;
/*     */   
/*     */   private transient Set keySet;
/*     */   
/*     */   private transient Set entrySet;
/*     */   
/*     */   private transient Collection values;
/*     */   
/*     */   public ReferenceMap() {
/* 204 */     this(0, 1);
/*     */   }
/*     */   
/*     */   public ReferenceMap(int keyType, int valueType, boolean purgeValues) {
/* 219 */     this(keyType, valueType);
/* 220 */     this.purgeValues = purgeValues;
/*     */   }
/*     */   
/*     */   public ReferenceMap(int keyType, int valueType) {
/* 233 */     this(keyType, valueType, 16, 0.75F);
/*     */   }
/*     */   
/*     */   public ReferenceMap(int keyType, int valueType, int capacity, float loadFactor, boolean purgeValues) {
/* 256 */     this(keyType, valueType, capacity, loadFactor);
/* 257 */     this.purgeValues = purgeValues;
/*     */   }
/*     */   
/*     */   public ReferenceMap(int keyType, int valueType, int capacity, float loadFactor) {
/* 275 */     verify("keyType", keyType);
/* 276 */     verify("valueType", valueType);
/* 278 */     if (capacity <= 0)
/* 279 */       throw new IllegalArgumentException("capacity must be positive"); 
/* 281 */     if (loadFactor <= 0.0F || loadFactor >= 1.0F)
/* 282 */       throw new IllegalArgumentException("Load factor must be greater than 0 and less than 1."); 
/* 285 */     this.keyType = keyType;
/* 286 */     this.valueType = valueType;
/* 288 */     int v = 1;
/* 289 */     for (; v < capacity; v *= 2);
/* 291 */     this.table = new Entry[v];
/* 292 */     this.loadFactor = loadFactor;
/* 293 */     this.threshold = (int)(v * loadFactor);
/*     */   }
/*     */   
/*     */   private static void verify(String name, int type) {
/* 299 */     if (type < 0 || type > 2)
/* 300 */       throw new IllegalArgumentException(name + " must be HARD, SOFT, WEAK."); 
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 313 */     out.defaultWriteObject();
/* 314 */     out.writeInt(this.table.length);
/* 319 */     for (Iterator iter = entrySet().iterator(); iter.hasNext(); ) {
/* 320 */       Map.Entry entry = iter.next();
/* 321 */       out.writeObject(entry.getKey());
/* 322 */       out.writeObject(entry.getValue());
/*     */     } 
/* 324 */     out.writeObject(null);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream inp) throws IOException, ClassNotFoundException {
/* 336 */     inp.defaultReadObject();
/* 337 */     this.table = new Entry[inp.readInt()];
/* 338 */     this.threshold = (int)(this.table.length * this.loadFactor);
/* 339 */     this.queue = new ReferenceQueue();
/* 340 */     Object key = inp.readObject();
/* 341 */     while (key != null) {
/* 342 */       Object value = inp.readObject();
/* 343 */       put(key, value);
/* 344 */       key = inp.readObject();
/*     */     } 
/*     */   }
/*     */   
/*     */   private Object toReference(int type, Object referent, int hash) {
/* 361 */     switch (type) {
/*     */       case 0:
/* 362 */         return referent;
/*     */       case 1:
/* 363 */         return new SoftRef(hash, referent, this.queue);
/*     */       case 2:
/* 364 */         return new WeakRef(hash, referent, this.queue);
/*     */     } 
/* 365 */     throw new Error();
/*     */   }
/*     */   
/*     */   private Entry getEntry(Object key) {
/* 378 */     if (key == null)
/* 378 */       return null; 
/* 379 */     int hash = key.hashCode();
/* 380 */     int index = indexFor(hash);
/* 381 */     for (Entry entry = this.table[index]; entry != null; entry = entry.next) {
/* 382 */       if (entry.hash == hash && key.equals(entry.getKey()))
/* 383 */         return entry; 
/*     */     } 
/* 386 */     return null;
/*     */   }
/*     */   
/*     */   private int indexFor(int hash) {
/* 396 */     hash += hash << 15 ^ 0xFFFFFFFF;
/* 397 */     hash ^= hash >>> 10;
/* 398 */     hash += hash << 3;
/* 399 */     hash ^= hash >>> 6;
/* 400 */     hash += hash << 11 ^ 0xFFFFFFFF;
/* 401 */     hash ^= hash >>> 16;
/* 402 */     return hash & this.table.length - 1;
/*     */   }
/*     */   
/*     */   private void resize() {
/* 414 */     Entry[] old = this.table;
/* 415 */     this.table = new Entry[old.length * 2];
/* 417 */     for (int i = 0; i < old.length; i++) {
/* 418 */       Entry next = old[i];
/* 419 */       while (next != null) {
/* 420 */         Entry entry = next;
/* 421 */         next = next.next;
/* 422 */         int index = indexFor(entry.hash);
/* 423 */         entry.next = this.table[index];
/* 424 */         this.table[index] = entry;
/*     */       } 
/* 426 */       old[i] = null;
/*     */     } 
/* 428 */     this.threshold = (int)(this.table.length * this.loadFactor);
/*     */   }
/*     */   
/*     */   private void purge() {
/* 446 */     Reference ref = this.queue.poll();
/* 447 */     while (ref != null) {
/* 448 */       purge(ref);
/* 449 */       ref = this.queue.poll();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void purge(Reference ref) {
/* 458 */     int hash = ref.hashCode();
/* 459 */     int index = indexFor(hash);
/* 460 */     Entry previous = null;
/* 461 */     Entry entry = this.table[index];
/* 462 */     while (entry != null) {
/* 463 */       if (entry.purge(ref)) {
/* 464 */         if (previous == null) {
/* 464 */           this.table[index] = entry.next;
/*     */         } else {
/* 465 */           previous.next = entry.next;
/*     */         } 
/* 466 */         this.size--;
/*     */         return;
/*     */       } 
/* 469 */       previous = entry;
/* 470 */       entry = entry.next;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 482 */     purge();
/* 483 */     return this.size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 493 */     purge();
/* 494 */     return (this.size == 0);
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 504 */     purge();
/* 505 */     Entry entry = getEntry(key);
/* 506 */     if (entry == null)
/* 506 */       return false; 
/* 507 */     return (entry.getValue() != null);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 518 */     purge();
/* 519 */     Entry entry = getEntry(key);
/* 520 */     if (entry == null)
/* 520 */       return null; 
/* 521 */     return entry.getValue();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 537 */     if (key == null)
/* 537 */       throw new NullPointerException("null keys not allowed"); 
/* 538 */     if (value == null)
/* 538 */       throw new NullPointerException("null values not allowed"); 
/* 540 */     purge();
/* 541 */     if (this.size + 1 > this.threshold)
/* 541 */       resize(); 
/* 543 */     int hash = key.hashCode();
/* 544 */     int index = indexFor(hash);
/* 545 */     Entry entry = this.table[index];
/* 546 */     while (entry != null) {
/* 547 */       if (hash == entry.hash && key.equals(entry.getKey())) {
/* 548 */         Object result = entry.getValue();
/* 549 */         entry.setValue(value);
/* 550 */         return result;
/*     */       } 
/* 552 */       entry = entry.next;
/*     */     } 
/* 554 */     this.size++;
/* 555 */     this.modCount++;
/* 556 */     key = toReference(this.keyType, key, hash);
/* 557 */     value = toReference(this.valueType, value, hash);
/* 558 */     this.table[index] = new Entry(this, key, hash, value, this.table[index]);
/* 559 */     return null;
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 571 */     if (key == null)
/* 571 */       return null; 
/* 572 */     purge();
/* 573 */     int hash = key.hashCode();
/* 574 */     int index = indexFor(hash);
/* 575 */     Entry previous = null;
/* 576 */     Entry entry = this.table[index];
/* 577 */     while (entry != null) {
/* 578 */       if (hash == entry.hash && key.equals(entry.getKey())) {
/* 579 */         if (previous == null) {
/* 579 */           this.table[index] = entry.next;
/*     */         } else {
/* 580 */           previous.next = entry.next;
/*     */         } 
/* 581 */         this.size--;
/* 582 */         this.modCount++;
/* 583 */         return entry.getValue();
/*     */       } 
/* 585 */       previous = entry;
/* 586 */       entry = entry.next;
/*     */     } 
/* 588 */     return null;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 596 */     Arrays.fill((Object[])this.table, (Object)null);
/* 597 */     this.size = 0;
/* 598 */     while (this.queue.poll() != null);
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 608 */     if (this.entrySet != null)
/* 609 */       return this.entrySet; 
/* 611 */     this.entrySet = new AbstractSet(this) {
/*     */         private final ReferenceMap this$0;
/*     */         
/*     */         public int size() {
/* 613 */           return this.this$0.size();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 617 */           this.this$0.clear();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 621 */           if (o == null)
/* 621 */             return false; 
/* 622 */           if (!(o instanceof Map.Entry))
/* 622 */             return false; 
/* 623 */           Map.Entry e = (Map.Entry)o;
/* 624 */           ReferenceMap.Entry e2 = this.this$0.getEntry(e.getKey());
/* 625 */           return (e2 != null && e.equals(e2));
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 629 */           boolean r = contains(o);
/* 630 */           if (r) {
/* 631 */             Map.Entry e = (Map.Entry)o;
/* 632 */             this.this$0.remove(e.getKey());
/*     */           } 
/* 634 */           return r;
/*     */         }
/*     */         
/*     */         public Iterator iterator() {
/* 638 */           return new ReferenceMap.EntryIterator(this.this$0);
/*     */         }
/*     */         
/*     */         public Object[] toArray() {
/* 642 */           return toArray(new Object[0]);
/*     */         }
/*     */         
/*     */         public Object[] toArray(Object[] arr) {
/* 646 */           ArrayList list = new ArrayList();
/* 647 */           Iterator iterator = iterator();
/* 648 */           while (iterator.hasNext()) {
/* 649 */             ReferenceMap.Entry e = iterator.next();
/* 650 */             list.add(new DefaultMapEntry(e.getKey(), e.getValue()));
/*     */           } 
/* 652 */           return list.toArray(arr);
/*     */         }
/*     */       };
/* 655 */     return this.entrySet;
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 665 */     if (this.keySet != null)
/* 665 */       return this.keySet; 
/* 666 */     this.keySet = new AbstractSet(this) {
/*     */         private final ReferenceMap this$0;
/*     */         
/*     */         public int size() {
/* 668 */           return this.this$0.size();
/*     */         }
/*     */         
/*     */         public Iterator iterator() {
/* 672 */           return new ReferenceMap.KeyIterator();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 676 */           return this.this$0.containsKey(o);
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 681 */           Object r = this.this$0.remove(o);
/* 682 */           return (r != null);
/*     */         }
/*     */         
/*     */         public void clear() {
/* 686 */           this.this$0.clear();
/*     */         }
/*     */         
/*     */         public Object[] toArray() {
/* 690 */           return toArray(new Object[0]);
/*     */         }
/*     */         
/*     */         public Object[] toArray(Object[] array) {
/* 694 */           Collection c = new ArrayList(size());
/* 695 */           for (Iterator it = iterator(); it.hasNext();)
/* 696 */             c.add(it.next()); 
/* 698 */           return c.toArray(array);
/*     */         }
/*     */       };
/* 701 */     return this.keySet;
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 711 */     if (this.values != null)
/* 711 */       return this.values; 
/* 712 */     this.values = new AbstractCollection(this) {
/*     */         private final ReferenceMap this$0;
/*     */         
/*     */         public int size() {
/* 714 */           return this.this$0.size();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 718 */           this.this$0.clear();
/*     */         }
/*     */         
/*     */         public Iterator iterator() {
/* 722 */           return new ReferenceMap.ValueIterator();
/*     */         }
/*     */         
/*     */         public Object[] toArray() {
/* 726 */           return toArray(new Object[0]);
/*     */         }
/*     */         
/*     */         public Object[] toArray(Object[] array) {
/* 730 */           Collection c = new ArrayList(size());
/* 731 */           for (Iterator it = iterator(); it.hasNext();)
/* 732 */             c.add(it.next()); 
/* 734 */           return c.toArray(array);
/*     */         }
/*     */       };
/* 737 */     return this.values;
/*     */   }
/*     */   
/*     */   private class Entry implements Map.Entry, KeyValue {
/*     */     Object key;
/*     */     
/*     */     Object value;
/*     */     
/*     */     int hash;
/*     */     
/*     */     Entry next;
/*     */     
/*     */     private final ReferenceMap this$0;
/*     */     
/*     */     public Entry(ReferenceMap this$0, Object key, int hash, Object value, Entry next) {
/* 751 */       this.this$0 = this$0;
/* 752 */       this.key = key;
/* 753 */       this.hash = hash;
/* 754 */       this.value = value;
/* 755 */       this.next = next;
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 760 */       return (this.this$0.keyType > 0) ? ((Reference)this.key).get() : this.key;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 765 */       return (this.this$0.valueType > 0) ? ((Reference)this.value).get() : this.value;
/*     */     }
/*     */     
/*     */     public Object setValue(Object object) {
/* 770 */       Object old = getValue();
/* 771 */       if (this.this$0.valueType > 0)
/* 771 */         ((Reference)this.value).clear(); 
/* 772 */       this.value = this.this$0.toReference(this.this$0.valueType, object, this.hash);
/* 773 */       return old;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 778 */       if (o == null)
/* 778 */         return false; 
/* 779 */       if (o == this)
/* 779 */         return true; 
/* 780 */       if (!(o instanceof Map.Entry))
/* 780 */         return false; 
/* 782 */       Map.Entry entry = (Map.Entry)o;
/* 783 */       Object key = entry.getKey();
/* 784 */       Object value = entry.getValue();
/* 785 */       if (key == null || value == null)
/* 785 */         return false; 
/* 786 */       return (key.equals(getKey()) && value.equals(getValue()));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 791 */       Object v = getValue();
/* 792 */       return this.hash ^ ((v == null) ? 0 : v.hashCode());
/*     */     }
/*     */     
/*     */     public String toString() {
/* 797 */       return getKey() + "=" + getValue();
/*     */     }
/*     */     
/*     */     boolean purge(Reference ref) {
/* 802 */       boolean r = (this.this$0.keyType > 0 && this.key == ref);
/* 803 */       r = (r || (this.this$0.valueType > 0 && this.value == ref));
/* 804 */       if (r) {
/* 805 */         if (this.this$0.keyType > 0)
/* 805 */           ((Reference)this.key).clear(); 
/* 806 */         if (this.this$0.valueType > 0) {
/* 807 */           ((Reference)this.value).clear();
/* 808 */         } else if (this.this$0.purgeValues) {
/* 809 */           this.value = null;
/*     */         } 
/*     */       } 
/* 812 */       return r;
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntryIterator implements Iterator {
/*     */     int index;
/*     */     
/*     */     ReferenceMap.Entry entry;
/*     */     
/*     */     ReferenceMap.Entry previous;
/*     */     
/*     */     Object nextKey;
/*     */     
/*     */     Object nextValue;
/*     */     
/*     */     Object currentKey;
/*     */     
/*     */     Object currentValue;
/*     */     
/*     */     int expectedModCount;
/*     */     
/*     */     private final ReferenceMap this$0;
/*     */     
/*     */     public EntryIterator(ReferenceMap this$0) {
/* 832 */       this.this$0 = this$0;
/* 833 */       this.index = (this$0.size() != 0) ? this$0.table.length : 0;
/* 836 */       this.expectedModCount = this$0.modCount;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 841 */       checkMod();
/* 842 */       while (nextNull()) {
/* 843 */         ReferenceMap.Entry e = this.entry;
/* 844 */         int i = this.index;
/* 845 */         while (e == null && i > 0) {
/* 846 */           i--;
/* 847 */           e = this.this$0.table[i];
/*     */         } 
/* 849 */         this.entry = e;
/* 850 */         this.index = i;
/* 851 */         if (e == null) {
/* 852 */           this.currentKey = null;
/* 853 */           this.currentValue = null;
/* 854 */           return false;
/*     */         } 
/* 856 */         this.nextKey = e.getKey();
/* 857 */         this.nextValue = e.getValue();
/* 858 */         if (nextNull())
/* 858 */           this.entry = this.entry.next; 
/*     */       } 
/* 860 */       return true;
/*     */     }
/*     */     
/*     */     private void checkMod() {
/* 865 */       if (this.this$0.modCount != this.expectedModCount)
/* 866 */         throw new ConcurrentModificationException(); 
/*     */     }
/*     */     
/*     */     private boolean nextNull() {
/* 872 */       return (this.nextKey == null || this.nextValue == null);
/*     */     }
/*     */     
/*     */     protected ReferenceMap.Entry nextEntry() {
/* 876 */       checkMod();
/* 877 */       if (nextNull() && !hasNext())
/* 877 */         throw new NoSuchElementException(); 
/* 878 */       this.previous = this.entry;
/* 879 */       this.entry = this.entry.next;
/* 880 */       this.currentKey = this.nextKey;
/* 881 */       this.currentValue = this.nextValue;
/* 882 */       this.nextKey = null;
/* 883 */       this.nextValue = null;
/* 884 */       return this.previous;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 889 */       return nextEntry();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 894 */       checkMod();
/* 895 */       if (this.previous == null)
/* 895 */         throw new IllegalStateException(); 
/* 896 */       this.this$0.remove(this.currentKey);
/* 897 */       this.previous = null;
/* 898 */       this.currentKey = null;
/* 899 */       this.currentValue = null;
/* 900 */       this.expectedModCount = this.this$0.modCount;
/*     */     }
/*     */   }
/*     */   
/*     */   private class ValueIterator extends EntryIterator {
/*     */     private final ReferenceMap this$0;
/*     */     
/*     */     private ValueIterator(ReferenceMap this$0) {
/* 906 */       ReferenceMap.this = ReferenceMap.this;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 908 */       return nextEntry().getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeyIterator extends EntryIterator {
/*     */     private final ReferenceMap this$0;
/*     */     
/*     */     private KeyIterator(ReferenceMap this$0) {
/* 913 */       ReferenceMap.this = ReferenceMap.this;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 915 */       return nextEntry().getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class SoftRef extends SoftReference {
/*     */     private int hash;
/*     */     
/*     */     public SoftRef(int hash, Object r, ReferenceQueue q) {
/* 931 */       super((T)r, q);
/* 932 */       this.hash = hash;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 937 */       return this.hash;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class WeakRef extends WeakReference {
/*     */     private int hash;
/*     */     
/*     */     public WeakRef(int hash, Object r, ReferenceQueue q) {
/* 947 */       super((T)r, q);
/* 948 */       this.hash = hash;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 953 */       return this.hash;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\ReferenceMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */