/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.keyvalue.DefaultMapEntry;
/*     */ 
/*     */ public abstract class AbstractReferenceMap extends AbstractHashedMap {
/*     */   public static final int HARD = 0;
/*     */   
/*     */   public static final int SOFT = 1;
/*     */   
/*     */   public static final int WEAK = 2;
/*     */   
/*     */   protected int keyType;
/*     */   
/*     */   protected int valueType;
/*     */   
/*     */   protected boolean purgeValues;
/*     */   
/*     */   private transient ReferenceQueue queue;
/*     */   
/*     */   protected AbstractReferenceMap() {}
/*     */   
/*     */   protected AbstractReferenceMap(int keyType, int valueType, int capacity, float loadFactor, boolean purgeValues) {
/* 143 */     super(capacity, loadFactor);
/* 144 */     verify("keyType", keyType);
/* 145 */     verify("valueType", valueType);
/* 146 */     this.keyType = keyType;
/* 147 */     this.valueType = valueType;
/* 148 */     this.purgeValues = purgeValues;
/*     */   }
/*     */   
/*     */   protected void init() {
/* 155 */     this.queue = new ReferenceQueue();
/*     */   }
/*     */   
/*     */   private static void verify(String name, int type) {
/* 167 */     if (type < 0 || type > 2)
/* 168 */       throw new IllegalArgumentException(name + " must be HARD, SOFT, WEAK."); 
/*     */   }
/*     */   
/*     */   public int size() {
/* 179 */     purgeBeforeRead();
/* 180 */     return super.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 189 */     purgeBeforeRead();
/* 190 */     return super.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 200 */     purgeBeforeRead();
/* 201 */     Map.Entry entry = getEntry(key);
/* 202 */     if (entry == null)
/* 203 */       return false; 
/* 205 */     return (entry.getValue() != null);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 215 */     purgeBeforeRead();
/* 216 */     if (value == null)
/* 217 */       return false; 
/* 219 */     return super.containsValue(value);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 229 */     purgeBeforeRead();
/* 230 */     Map.Entry entry = getEntry(key);
/* 231 */     if (entry == null)
/* 232 */       return null; 
/* 234 */     return entry.getValue();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 248 */     if (key == null)
/* 249 */       throw new NullPointerException("null keys not allowed"); 
/* 251 */     if (value == null)
/* 252 */       throw new NullPointerException("null values not allowed"); 
/* 255 */     purgeBeforeWrite();
/* 256 */     return super.put(key, value);
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 266 */     if (key == null)
/* 267 */       return null; 
/* 269 */     purgeBeforeWrite();
/* 270 */     return super.remove(key);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 277 */     super.clear();
/* 278 */     while (this.queue.poll() != null);
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 289 */     return new ReferenceMapIterator(this);
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 300 */     if (this.entrySet == null)
/* 301 */       this.entrySet = new ReferenceEntrySet(this); 
/* 303 */     return this.entrySet;
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 312 */     if (this.keySet == null)
/* 313 */       this.keySet = new ReferenceKeySet(this); 
/* 315 */     return this.keySet;
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 324 */     if (this.values == null)
/* 325 */       this.values = new ReferenceValues(this); 
/* 327 */     return this.values;
/*     */   }
/*     */   
/*     */   protected void purgeBeforeRead() {
/* 337 */     purge();
/*     */   }
/*     */   
/*     */   protected void purgeBeforeWrite() {
/* 346 */     purge();
/*     */   }
/*     */   
/*     */   protected void purge() {
/* 358 */     Reference ref = this.queue.poll();
/* 359 */     while (ref != null) {
/* 360 */       purge(ref);
/* 361 */       ref = this.queue.poll();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void purge(Reference ref) {
/* 374 */     int hash = ref.hashCode();
/* 375 */     int index = hashIndex(hash, this.data.length);
/* 376 */     AbstractHashedMap.HashEntry previous = null;
/* 377 */     AbstractHashedMap.HashEntry entry = this.data[index];
/* 378 */     while (entry != null) {
/* 379 */       if (((ReferenceEntry)entry).purge(ref)) {
/* 380 */         if (previous == null) {
/* 381 */           this.data[index] = entry.next;
/*     */         } else {
/* 383 */           previous.next = entry.next;
/*     */         } 
/* 385 */         this.size--;
/*     */         return;
/*     */       } 
/* 388 */       previous = entry;
/* 389 */       entry = entry.next;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected AbstractHashedMap.HashEntry getEntry(Object key) {
/* 402 */     if (key == null)
/* 403 */       return null; 
/* 405 */     return super.getEntry(key);
/*     */   }
/*     */   
/*     */   protected int hashEntry(Object key, Object value) {
/* 418 */     return ((key == null) ? 0 : key.hashCode()) ^ ((value == null) ? 0 : value.hashCode());
/*     */   }
/*     */   
/*     */   protected boolean isEqualKey(Object key1, Object key2) {
/* 433 */     key2 = (this.keyType > 0) ? ((Reference)key2).get() : key2;
/* 434 */     return (key1 == key2 || key1.equals(key2));
/*     */   }
/*     */   
/*     */   protected AbstractHashedMap.HashEntry createEntry(AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
/* 447 */     return new ReferenceEntry(this, next, hashCode, key, value);
/*     */   }
/*     */   
/*     */   protected Iterator createEntrySetIterator() {
/* 456 */     return new ReferenceEntrySetIterator(this);
/*     */   }
/*     */   
/*     */   protected Iterator createKeySetIterator() {
/* 465 */     return new ReferenceKeySetIterator(this);
/*     */   }
/*     */   
/*     */   protected Iterator createValuesIterator() {
/* 474 */     return new ReferenceValuesIterator(this);
/*     */   }
/*     */   
/*     */   static class ReferenceEntrySet extends AbstractHashedMap.EntrySet {
/*     */     protected ReferenceEntrySet(AbstractHashedMap parent) {
/* 484 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 488 */       return toArray(new Object[0]);
/*     */     }
/*     */     
/*     */     public Object[] toArray(Object[] arr) {
/* 493 */       ArrayList list = new ArrayList();
/* 494 */       Iterator iterator = iterator();
/* 495 */       while (iterator.hasNext()) {
/* 496 */         Map.Entry e = iterator.next();
/* 497 */         list.add(new DefaultMapEntry(e.getKey(), e.getValue()));
/*     */       } 
/* 499 */       return list.toArray(arr);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ReferenceKeySet extends AbstractHashedMap.KeySet {
/*     */     protected ReferenceKeySet(AbstractHashedMap parent) {
/* 510 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 514 */       return toArray(new Object[0]);
/*     */     }
/*     */     
/*     */     public Object[] toArray(Object[] arr) {
/* 519 */       List list = new ArrayList(this.parent.size());
/* 520 */       for (Iterator it = iterator(); it.hasNext();)
/* 521 */         list.add(it.next()); 
/* 523 */       return list.toArray(arr);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ReferenceValues extends AbstractHashedMap.Values {
/*     */     protected ReferenceValues(AbstractHashedMap parent) {
/* 534 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 538 */       return toArray(new Object[0]);
/*     */     }
/*     */     
/*     */     public Object[] toArray(Object[] arr) {
/* 543 */       List list = new ArrayList(this.parent.size());
/* 544 */       for (Iterator it = iterator(); it.hasNext();)
/* 545 */         list.add(it.next()); 
/* 547 */       return list.toArray(arr);
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class ReferenceEntry extends AbstractHashedMap.HashEntry {
/*     */     protected final AbstractReferenceMap parent;
/*     */     
/*     */     public ReferenceEntry(AbstractReferenceMap parent, AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
/* 574 */       super(next, hashCode, null, null);
/* 575 */       this.parent = parent;
/* 576 */       this.key = toReference(parent.keyType, key, hashCode);
/* 577 */       this.value = toReference(parent.valueType, value, hashCode);
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 587 */       return (this.parent.keyType > 0) ? ((Reference)this.key).get() : this.key;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 597 */       return (this.parent.valueType > 0) ? ((Reference)this.value).get() : this.value;
/*     */     }
/*     */     
/*     */     public Object setValue(Object obj) {
/* 607 */       Object old = getValue();
/* 608 */       if (this.parent.valueType > 0)
/* 609 */         ((Reference)this.value).clear(); 
/* 611 */       this.value = toReference(this.parent.valueType, obj, this.hashCode);
/* 612 */       return old;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 625 */       if (obj == this)
/* 626 */         return true; 
/* 628 */       if (!(obj instanceof Map.Entry))
/* 629 */         return false; 
/* 632 */       Map.Entry entry = (Map.Entry)obj;
/* 633 */       Object entryKey = entry.getKey();
/* 634 */       Object entryValue = entry.getValue();
/* 635 */       if (entryKey == null || entryValue == null)
/* 636 */         return false; 
/* 640 */       return (this.parent.isEqualKey(entryKey, this.key) && this.parent.isEqualValue(entryValue, getValue()));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 652 */       return this.parent.hashEntry(getKey(), getValue());
/*     */     }
/*     */     
/*     */     protected Object toReference(int type, Object referent, int hash) {
/* 666 */       switch (type) {
/*     */         case 0:
/* 667 */           return referent;
/*     */         case 1:
/* 668 */           return new AbstractReferenceMap.SoftRef(hash, referent, this.parent.queue);
/*     */         case 2:
/* 669 */           return new AbstractReferenceMap.WeakRef(hash, referent, this.parent.queue);
/*     */       } 
/* 670 */       throw new Error();
/*     */     }
/*     */     
/*     */     boolean purge(Reference ref) {
/* 680 */       boolean r = (this.parent.keyType > 0 && this.key == ref);
/* 681 */       r = (r || (this.parent.valueType > 0 && this.value == ref));
/* 682 */       if (r) {
/* 683 */         if (this.parent.keyType > 0)
/* 684 */           ((Reference)this.key).clear(); 
/* 686 */         if (this.parent.valueType > 0) {
/* 687 */           ((Reference)this.value).clear();
/* 688 */         } else if (this.parent.purgeValues) {
/* 689 */           this.value = null;
/*     */         } 
/*     */       } 
/* 692 */       return r;
/*     */     }
/*     */     
/*     */     protected ReferenceEntry next() {
/* 701 */       return (ReferenceEntry)this.next;
/*     */     }
/*     */   }
/*     */   
/*     */   static class ReferenceEntrySetIterator implements Iterator {
/*     */     final AbstractReferenceMap parent;
/*     */     
/*     */     int index;
/*     */     
/*     */     AbstractReferenceMap.ReferenceEntry entry;
/*     */     
/*     */     AbstractReferenceMap.ReferenceEntry previous;
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
/*     */     public ReferenceEntrySetIterator(AbstractReferenceMap parent) {
/* 728 */       this.parent = parent;
/* 729 */       this.index = (parent.size() != 0) ? parent.data.length : 0;
/* 732 */       this.expectedModCount = parent.modCount;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 736 */       checkMod();
/* 737 */       while (nextNull()) {
/* 738 */         AbstractReferenceMap.ReferenceEntry e = this.entry;
/* 739 */         int i = this.index;
/* 740 */         while (e == null && i > 0) {
/* 741 */           i--;
/* 742 */           e = (AbstractReferenceMap.ReferenceEntry)this.parent.data[i];
/*     */         } 
/* 744 */         this.entry = e;
/* 745 */         this.index = i;
/* 746 */         if (e == null) {
/* 747 */           this.currentKey = null;
/* 748 */           this.currentValue = null;
/* 749 */           return false;
/*     */         } 
/* 751 */         this.nextKey = e.getKey();
/* 752 */         this.nextValue = e.getValue();
/* 753 */         if (nextNull())
/* 754 */           this.entry = this.entry.next(); 
/*     */       } 
/* 757 */       return true;
/*     */     }
/*     */     
/*     */     private void checkMod() {
/* 761 */       if (this.parent.modCount != this.expectedModCount)
/* 762 */         throw new ConcurrentModificationException(); 
/*     */     }
/*     */     
/*     */     private boolean nextNull() {
/* 767 */       return (this.nextKey == null || this.nextValue == null);
/*     */     }
/*     */     
/*     */     protected AbstractReferenceMap.ReferenceEntry nextEntry() {
/* 771 */       checkMod();
/* 772 */       if (nextNull() && !hasNext())
/* 773 */         throw new NoSuchElementException(); 
/* 775 */       this.previous = this.entry;
/* 776 */       this.entry = this.entry.next();
/* 777 */       this.currentKey = this.nextKey;
/* 778 */       this.currentValue = this.nextValue;
/* 779 */       this.nextKey = null;
/* 780 */       this.nextValue = null;
/* 781 */       return this.previous;
/*     */     }
/*     */     
/*     */     protected AbstractReferenceMap.ReferenceEntry currentEntry() {
/* 785 */       checkMod();
/* 786 */       return this.previous;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 790 */       return nextEntry();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 794 */       checkMod();
/* 795 */       if (this.previous == null)
/* 796 */         throw new IllegalStateException(); 
/* 798 */       this.parent.remove(this.currentKey);
/* 799 */       this.previous = null;
/* 800 */       this.currentKey = null;
/* 801 */       this.currentValue = null;
/* 802 */       this.expectedModCount = this.parent.modCount;
/*     */     }
/*     */   }
/*     */   
/*     */   static class ReferenceKeySetIterator extends ReferenceEntrySetIterator {
/*     */     ReferenceKeySetIterator(AbstractReferenceMap parent) {
/* 812 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 816 */       return nextEntry().getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   static class ReferenceValuesIterator extends ReferenceEntrySetIterator {
/*     */     ReferenceValuesIterator(AbstractReferenceMap parent) {
/* 826 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 830 */       return nextEntry().getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   static class ReferenceMapIterator extends ReferenceEntrySetIterator implements MapIterator {
/*     */     protected ReferenceMapIterator(AbstractReferenceMap parent) {
/* 840 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 844 */       return nextEntry().getKey();
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 848 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 849 */       if (current == null)
/* 850 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()"); 
/* 852 */       return current.getKey();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 856 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 857 */       if (current == null)
/* 858 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()"); 
/* 860 */       return current.getValue();
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 864 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 865 */       if (current == null)
/* 866 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()"); 
/* 868 */       return current.setValue(value);
/*     */     }
/*     */   }
/*     */   
/*     */   static class SoftRef extends SoftReference {
/*     */     private int hash;
/*     */     
/*     */     public SoftRef(int hash, Object r, ReferenceQueue q) {
/* 885 */       super((T)r, q);
/* 886 */       this.hash = hash;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 890 */       return this.hash;
/*     */     }
/*     */   }
/*     */   
/*     */   static class WeakRef extends WeakReference {
/*     */     private int hash;
/*     */     
/*     */     public WeakRef(int hash, Object r, ReferenceQueue q) {
/* 902 */       super((T)r, q);
/* 903 */       this.hash = hash;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 907 */       return this.hash;
/*     */     }
/*     */   }
/*     */   
/*     */   protected void doWriteObject(ObjectOutputStream out) throws IOException {
/* 931 */     out.writeInt(this.keyType);
/* 932 */     out.writeInt(this.valueType);
/* 933 */     out.writeBoolean(this.purgeValues);
/* 934 */     out.writeFloat(this.loadFactor);
/* 935 */     out.writeInt(this.data.length);
/* 936 */     for (MapIterator it = mapIterator(); it.hasNext(); ) {
/* 937 */       out.writeObject(it.next());
/* 938 */       out.writeObject(it.getValue());
/*     */     } 
/* 940 */     out.writeObject(null);
/*     */   }
/*     */   
/*     */   protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 962 */     this.keyType = in.readInt();
/* 963 */     this.valueType = in.readInt();
/* 964 */     this.purgeValues = in.readBoolean();
/* 965 */     this.loadFactor = in.readFloat();
/* 966 */     int capacity = in.readInt();
/* 967 */     init();
/* 968 */     this.data = new AbstractHashedMap.HashEntry[capacity];
/*     */     while (true) {
/* 970 */       Object key = in.readObject();
/* 971 */       if (key == null)
/*     */         break; 
/* 974 */       Object value = in.readObject();
/* 975 */       put(key, value);
/*     */     } 
/* 977 */     this.threshold = calculateThreshold(this.data.length, this.loadFactor);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\AbstractReferenceMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */