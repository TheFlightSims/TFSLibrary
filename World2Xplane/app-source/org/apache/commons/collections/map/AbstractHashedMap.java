/*      */ package org.apache.commons.collections.map;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.IterableMap;
/*      */ import org.apache.commons.collections.KeyValue;
/*      */ import org.apache.commons.collections.MapIterator;
/*      */ import org.apache.commons.collections.iterators.EmptyIterator;
/*      */ import org.apache.commons.collections.iterators.EmptyMapIterator;
/*      */ 
/*      */ public class AbstractHashedMap extends AbstractMap implements IterableMap {
/*      */   protected static final String NO_NEXT_ENTRY = "No next() entry in the iteration";
/*      */   
/*      */   protected static final String NO_PREVIOUS_ENTRY = "No previous() entry in the iteration";
/*      */   
/*      */   protected static final String REMOVE_INVALID = "remove() can only be called once after next()";
/*      */   
/*      */   protected static final String GETKEY_INVALID = "getKey() can only be called after next() and before remove()";
/*      */   
/*      */   protected static final String GETVALUE_INVALID = "getValue() can only be called after next() and before remove()";
/*      */   
/*      */   protected static final String SETVALUE_INVALID = "setValue() can only be called after next() and before remove()";
/*      */   
/*      */   protected static final int DEFAULT_CAPACITY = 16;
/*      */   
/*      */   protected static final int DEFAULT_THRESHOLD = 12;
/*      */   
/*      */   protected static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   
/*      */   protected static final int MAXIMUM_CAPACITY = 1073741824;
/*      */   
/*   80 */   protected static final Object NULL = new Object();
/*      */   
/*      */   protected transient float loadFactor;
/*      */   
/*      */   protected transient int size;
/*      */   
/*      */   protected transient HashEntry[] data;
/*      */   
/*      */   protected transient int threshold;
/*      */   
/*      */   protected transient int modCount;
/*      */   
/*      */   protected transient EntrySet entrySet;
/*      */   
/*      */   protected transient KeySet keySet;
/*      */   
/*      */   protected transient Values values;
/*      */   
/*      */   protected AbstractHashedMap() {}
/*      */   
/*      */   protected AbstractHashedMap(int initialCapacity, float loadFactor, int threshold) {
/*  115 */     this.loadFactor = loadFactor;
/*  116 */     this.data = new HashEntry[initialCapacity];
/*  117 */     this.threshold = threshold;
/*  118 */     init();
/*      */   }
/*      */   
/*      */   protected AbstractHashedMap(int initialCapacity) {
/*  129 */     this(initialCapacity, 0.75F);
/*      */   }
/*      */   
/*      */   protected AbstractHashedMap(int initialCapacity, float loadFactor) {
/*  143 */     if (initialCapacity < 1)
/*  144 */       throw new IllegalArgumentException("Initial capacity must be greater than 0"); 
/*  146 */     if (loadFactor <= 0.0F || Float.isNaN(loadFactor))
/*  147 */       throw new IllegalArgumentException("Load factor must be greater than 0"); 
/*  149 */     this.loadFactor = loadFactor;
/*  150 */     initialCapacity = calculateNewCapacity(initialCapacity);
/*  151 */     this.threshold = calculateThreshold(initialCapacity, loadFactor);
/*  152 */     this.data = new HashEntry[initialCapacity];
/*  153 */     init();
/*      */   }
/*      */   
/*      */   protected AbstractHashedMap(Map map) {
/*  163 */     this(Math.max(2 * map.size(), 16), 0.75F);
/*  164 */     putAll(map);
/*      */   }
/*      */   
/*      */   protected void init() {}
/*      */   
/*      */   public Object get(Object key) {
/*  181 */     key = convertKey(key);
/*  182 */     int hashCode = hash(key);
/*  183 */     HashEntry entry = this.data[hashIndex(hashCode, this.data.length)];
/*  184 */     while (entry != null) {
/*  185 */       if (entry.hashCode == hashCode && isEqualKey(key, entry.key))
/*  186 */         return entry.getValue(); 
/*  188 */       entry = entry.next;
/*      */     } 
/*  190 */     return null;
/*      */   }
/*      */   
/*      */   public int size() {
/*  199 */     return this.size;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  208 */     return (this.size == 0);
/*      */   }
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  219 */     key = convertKey(key);
/*  220 */     int hashCode = hash(key);
/*  221 */     HashEntry entry = this.data[hashIndex(hashCode, this.data.length)];
/*  222 */     while (entry != null) {
/*  223 */       if (entry.hashCode == hashCode && isEqualKey(key, entry.key))
/*  224 */         return true; 
/*  226 */       entry = entry.next;
/*      */     } 
/*  228 */     return false;
/*      */   }
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  238 */     if (value == null) {
/*  239 */       for (int i = 0, isize = this.data.length; i < isize; i++) {
/*  240 */         HashEntry entry = this.data[i];
/*  241 */         while (entry != null) {
/*  242 */           if (entry.getValue() == null)
/*  243 */             return true; 
/*  245 */           entry = entry.next;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  249 */       for (int i = 0, isize = this.data.length; i < isize; i++) {
/*  250 */         HashEntry entry = this.data[i];
/*  251 */         while (entry != null) {
/*  252 */           if (isEqualValue(value, entry.getValue()))
/*  253 */             return true; 
/*  255 */           entry = entry.next;
/*      */         } 
/*      */       } 
/*      */     } 
/*  259 */     return false;
/*      */   }
/*      */   
/*      */   public Object put(Object key, Object value) {
/*  271 */     key = convertKey(key);
/*  272 */     int hashCode = hash(key);
/*  273 */     int index = hashIndex(hashCode, this.data.length);
/*  274 */     HashEntry entry = this.data[index];
/*  275 */     while (entry != null) {
/*  276 */       if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
/*  277 */         Object oldValue = entry.getValue();
/*  278 */         updateEntry(entry, value);
/*  279 */         return oldValue;
/*      */       } 
/*  281 */       entry = entry.next;
/*      */     } 
/*  284 */     addMapping(index, hashCode, key, value);
/*  285 */     return null;
/*      */   }
/*      */   
/*      */   public void putAll(Map map) {
/*  298 */     int mapSize = map.size();
/*  299 */     if (mapSize == 0)
/*      */       return; 
/*  302 */     int newSize = (int)((this.size + mapSize) / this.loadFactor + 1.0F);
/*  303 */     ensureCapacity(calculateNewCapacity(newSize));
/*  304 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/*  305 */       Map.Entry entry = it.next();
/*  306 */       put(entry.getKey(), entry.getValue());
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object remove(Object key) {
/*  317 */     key = convertKey(key);
/*  318 */     int hashCode = hash(key);
/*  319 */     int index = hashIndex(hashCode, this.data.length);
/*  320 */     HashEntry entry = this.data[index];
/*  321 */     HashEntry previous = null;
/*  322 */     while (entry != null) {
/*  323 */       if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
/*  324 */         Object oldValue = entry.getValue();
/*  325 */         removeMapping(entry, index, previous);
/*  326 */         return oldValue;
/*      */       } 
/*  328 */       previous = entry;
/*  329 */       entry = entry.next;
/*      */     } 
/*  331 */     return null;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  339 */     this.modCount++;
/*  340 */     HashEntry[] data = this.data;
/*  341 */     for (int i = data.length - 1; i >= 0; i--)
/*  342 */       data[i] = null; 
/*  344 */     this.size = 0;
/*      */   }
/*      */   
/*      */   protected Object convertKey(Object key) {
/*  360 */     return (key == null) ? NULL : key;
/*      */   }
/*      */   
/*      */   protected int hash(Object key) {
/*  373 */     int h = key.hashCode();
/*  374 */     h += h << 9 ^ 0xFFFFFFFF;
/*  375 */     h ^= h >>> 14;
/*  376 */     h += h << 4;
/*  377 */     h ^= h >>> 10;
/*  378 */     return h;
/*      */   }
/*      */   
/*      */   protected boolean isEqualKey(Object key1, Object key2) {
/*  391 */     return (key1 == key2 || key1.equals(key2));
/*      */   }
/*      */   
/*      */   protected boolean isEqualValue(Object value1, Object value2) {
/*  404 */     return (value1 == value2 || value1.equals(value2));
/*      */   }
/*      */   
/*      */   protected int hashIndex(int hashCode, int dataSize) {
/*  417 */     return hashCode & dataSize - 1;
/*      */   }
/*      */   
/*      */   protected HashEntry getEntry(Object key) {
/*  432 */     key = convertKey(key);
/*  433 */     int hashCode = hash(key);
/*  434 */     HashEntry entry = this.data[hashIndex(hashCode, this.data.length)];
/*  435 */     while (entry != null) {
/*  436 */       if (entry.hashCode == hashCode && isEqualKey(key, entry.key))
/*  437 */         return entry; 
/*  439 */       entry = entry.next;
/*      */     } 
/*  441 */     return null;
/*      */   }
/*      */   
/*      */   protected void updateEntry(HashEntry entry, Object newValue) {
/*  455 */     entry.setValue(newValue);
/*      */   }
/*      */   
/*      */   protected void reuseEntry(HashEntry entry, int hashIndex, int hashCode, Object key, Object value) {
/*  471 */     entry.next = this.data[hashIndex];
/*  472 */     entry.hashCode = hashCode;
/*  473 */     entry.key = key;
/*  474 */     entry.value = value;
/*      */   }
/*      */   
/*      */   protected void addMapping(int hashIndex, int hashCode, Object key, Object value) {
/*  492 */     this.modCount++;
/*  493 */     HashEntry entry = createEntry(this.data[hashIndex], hashCode, key, value);
/*  494 */     addEntry(entry, hashIndex);
/*  495 */     this.size++;
/*  496 */     checkCapacity();
/*      */   }
/*      */   
/*      */   protected HashEntry createEntry(HashEntry next, int hashCode, Object key, Object value) {
/*  513 */     return new HashEntry(next, hashCode, key, value);
/*      */   }
/*      */   
/*      */   protected void addEntry(HashEntry entry, int hashIndex) {
/*  526 */     this.data[hashIndex] = entry;
/*      */   }
/*      */   
/*      */   protected void removeMapping(HashEntry entry, int hashIndex, HashEntry previous) {
/*  542 */     this.modCount++;
/*  543 */     removeEntry(entry, hashIndex, previous);
/*  544 */     this.size--;
/*  545 */     destroyEntry(entry);
/*      */   }
/*      */   
/*      */   protected void removeEntry(HashEntry entry, int hashIndex, HashEntry previous) {
/*  560 */     if (previous == null) {
/*  561 */       this.data[hashIndex] = entry.next;
/*      */     } else {
/*  563 */       previous.next = entry.next;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void destroyEntry(HashEntry entry) {
/*  576 */     entry.next = null;
/*  577 */     entry.key = null;
/*  578 */     entry.value = null;
/*      */   }
/*      */   
/*      */   protected void checkCapacity() {
/*  588 */     if (this.size >= this.threshold) {
/*  589 */       int newCapacity = this.data.length * 2;
/*  590 */       if (newCapacity <= 1073741824)
/*  591 */         ensureCapacity(newCapacity); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void ensureCapacity(int newCapacity) {
/*  602 */     int oldCapacity = this.data.length;
/*  603 */     if (newCapacity <= oldCapacity)
/*      */       return; 
/*  606 */     if (this.size == 0) {
/*  607 */       this.threshold = calculateThreshold(newCapacity, this.loadFactor);
/*  608 */       this.data = new HashEntry[newCapacity];
/*      */     } else {
/*  610 */       HashEntry[] oldEntries = this.data;
/*  611 */       HashEntry[] newEntries = new HashEntry[newCapacity];
/*  613 */       this.modCount++;
/*  614 */       for (int i = oldCapacity - 1; i >= 0; i--) {
/*  615 */         HashEntry entry = oldEntries[i];
/*  616 */         if (entry != null) {
/*  617 */           oldEntries[i] = null;
/*      */           do {
/*  619 */             HashEntry next = entry.next;
/*  620 */             int index = hashIndex(entry.hashCode, newCapacity);
/*  621 */             entry.next = newEntries[index];
/*  622 */             newEntries[index] = entry;
/*  623 */             entry = next;
/*  624 */           } while (entry != null);
/*      */         } 
/*      */       } 
/*  627 */       this.threshold = calculateThreshold(newCapacity, this.loadFactor);
/*  628 */       this.data = newEntries;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected int calculateNewCapacity(int proposedCapacity) {
/*  640 */     int newCapacity = 1;
/*  641 */     if (proposedCapacity > 1073741824) {
/*  642 */       newCapacity = 1073741824;
/*      */     } else {
/*  644 */       while (newCapacity < proposedCapacity)
/*  645 */         newCapacity <<= 1; 
/*  647 */       if (newCapacity > 1073741824)
/*  648 */         newCapacity = 1073741824; 
/*      */     } 
/*  651 */     return newCapacity;
/*      */   }
/*      */   
/*      */   protected int calculateThreshold(int newCapacity, float factor) {
/*  663 */     return (int)(newCapacity * factor);
/*      */   }
/*      */   
/*      */   protected HashEntry entryNext(HashEntry entry) {
/*  677 */     return entry.next;
/*      */   }
/*      */   
/*      */   protected int entryHashCode(HashEntry entry) {
/*  690 */     return entry.hashCode;
/*      */   }
/*      */   
/*      */   protected Object entryKey(HashEntry entry) {
/*  703 */     return entry.key;
/*      */   }
/*      */   
/*      */   protected Object entryValue(HashEntry entry) {
/*  716 */     return entry.value;
/*      */   }
/*      */   
/*      */   public MapIterator mapIterator() {
/*  732 */     if (this.size == 0)
/*  733 */       return EmptyMapIterator.INSTANCE; 
/*  735 */     return new HashMapIterator(this);
/*      */   }
/*      */   
/*      */   protected static class HashMapIterator extends HashIterator implements MapIterator {
/*      */     protected HashMapIterator(AbstractHashedMap parent) {
/*  744 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  748 */       return nextEntry().getKey();
/*      */     }
/*      */     
/*      */     public Object getKey() {
/*  752 */       AbstractHashedMap.HashEntry current = currentEntry();
/*  753 */       if (current == null)
/*  754 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()"); 
/*  756 */       return current.getKey();
/*      */     }
/*      */     
/*      */     public Object getValue() {
/*  760 */       AbstractHashedMap.HashEntry current = currentEntry();
/*  761 */       if (current == null)
/*  762 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()"); 
/*  764 */       return current.getValue();
/*      */     }
/*      */     
/*      */     public Object setValue(Object value) {
/*  768 */       AbstractHashedMap.HashEntry current = currentEntry();
/*  769 */       if (current == null)
/*  770 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()"); 
/*  772 */       return current.setValue(value);
/*      */     }
/*      */   }
/*      */   
/*      */   public Set entrySet() {
/*  785 */     if (this.entrySet == null)
/*  786 */       this.entrySet = new EntrySet(this); 
/*  788 */     return this.entrySet;
/*      */   }
/*      */   
/*      */   protected Iterator createEntrySetIterator() {
/*  798 */     if (size() == 0)
/*  799 */       return EmptyIterator.INSTANCE; 
/*  801 */     return new EntrySetIterator(this);
/*      */   }
/*      */   
/*      */   protected static class EntrySet extends AbstractSet {
/*      */     protected final AbstractHashedMap parent;
/*      */     
/*      */     protected EntrySet(AbstractHashedMap parent) {
/*  813 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  817 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  821 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object entry) {
/*  825 */       if (entry instanceof Map.Entry) {
/*  826 */         Map.Entry e = (Map.Entry)entry;
/*  827 */         Map.Entry match = this.parent.getEntry(e.getKey());
/*  828 */         return (match != null && match.equals(e));
/*      */       } 
/*  830 */       return false;
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/*  834 */       if (!(obj instanceof Map.Entry))
/*  835 */         return false; 
/*  837 */       if (!contains(obj))
/*  838 */         return false; 
/*  840 */       Map.Entry entry = (Map.Entry)obj;
/*  841 */       Object key = entry.getKey();
/*  842 */       this.parent.remove(key);
/*  843 */       return true;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  847 */       return this.parent.createEntrySetIterator();
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class EntrySetIterator extends HashIterator {
/*      */     protected EntrySetIterator(AbstractHashedMap parent) {
/*  857 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  861 */       return nextEntry();
/*      */     }
/*      */   }
/*      */   
/*      */   public Set keySet() {
/*  874 */     if (this.keySet == null)
/*  875 */       this.keySet = new KeySet(this); 
/*  877 */     return this.keySet;
/*      */   }
/*      */   
/*      */   protected Iterator createKeySetIterator() {
/*  887 */     if (size() == 0)
/*  888 */       return EmptyIterator.INSTANCE; 
/*  890 */     return new KeySetIterator(this);
/*      */   }
/*      */   
/*      */   protected static class KeySet extends AbstractSet {
/*      */     protected final AbstractHashedMap parent;
/*      */     
/*      */     protected KeySet(AbstractHashedMap parent) {
/*  902 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  906 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  910 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object key) {
/*  914 */       return this.parent.containsKey(key);
/*      */     }
/*      */     
/*      */     public boolean remove(Object key) {
/*  918 */       boolean result = this.parent.containsKey(key);
/*  919 */       this.parent.remove(key);
/*  920 */       return result;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  924 */       return this.parent.createKeySetIterator();
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class KeySetIterator extends EntrySetIterator {
/*      */     protected KeySetIterator(AbstractHashedMap parent) {
/*  934 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  938 */       return nextEntry().getKey();
/*      */     }
/*      */   }
/*      */   
/*      */   public Collection values() {
/*  951 */     if (this.values == null)
/*  952 */       this.values = new Values(this); 
/*  954 */     return this.values;
/*      */   }
/*      */   
/*      */   protected Iterator createValuesIterator() {
/*  964 */     if (size() == 0)
/*  965 */       return EmptyIterator.INSTANCE; 
/*  967 */     return new ValuesIterator(this);
/*      */   }
/*      */   
/*      */   protected static class Values extends AbstractCollection {
/*      */     protected final AbstractHashedMap parent;
/*      */     
/*      */     protected Values(AbstractHashedMap parent) {
/*  979 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  983 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  987 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object value) {
/*  991 */       return this.parent.containsValue(value);
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  995 */       return this.parent.createValuesIterator();
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class ValuesIterator extends HashIterator {
/*      */     protected ValuesIterator(AbstractHashedMap parent) {
/* 1005 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/* 1009 */       return nextEntry().getValue();
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class HashEntry implements Map.Entry, KeyValue {
/*      */     protected HashEntry next;
/*      */     
/*      */     protected int hashCode;
/*      */     
/*      */     protected Object key;
/*      */     
/*      */     protected Object value;
/*      */     
/*      */     protected HashEntry(HashEntry next, int hashCode, Object key, Object value) {
/* 1034 */       this.next = next;
/* 1035 */       this.hashCode = hashCode;
/* 1036 */       this.key = key;
/* 1037 */       this.value = value;
/*      */     }
/*      */     
/*      */     public Object getKey() {
/* 1041 */       return (this.key == AbstractHashedMap.NULL) ? null : this.key;
/*      */     }
/*      */     
/*      */     public Object getValue() {
/* 1045 */       return this.value;
/*      */     }
/*      */     
/*      */     public Object setValue(Object value) {
/* 1049 */       Object old = this.value;
/* 1050 */       this.value = value;
/* 1051 */       return old;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1055 */       if (obj == this)
/* 1056 */         return true; 
/* 1058 */       if (!(obj instanceof Map.Entry))
/* 1059 */         return false; 
/* 1061 */       Map.Entry other = (Map.Entry)obj;
/* 1062 */       return (((getKey() == null) ? (other.getKey() == null) : getKey().equals(other.getKey())) && ((getValue() == null) ? (other.getValue() == null) : getValue().equals(other.getValue())));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1068 */       return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1073 */       return getKey() + '=' + getValue();
/*      */     }
/*      */   }
/*      */   
/*      */   protected static abstract class HashIterator implements Iterator {
/*      */     protected final AbstractHashedMap parent;
/*      */     
/*      */     protected int hashIndex;
/*      */     
/*      */     protected AbstractHashedMap.HashEntry last;
/*      */     
/*      */     protected AbstractHashedMap.HashEntry next;
/*      */     
/*      */     protected int expectedModCount;
/*      */     
/*      */     protected HashIterator(AbstractHashedMap parent) {
/* 1095 */       this.parent = parent;
/* 1096 */       AbstractHashedMap.HashEntry[] data = parent.data;
/* 1097 */       int i = data.length;
/* 1098 */       AbstractHashedMap.HashEntry next = null;
/* 1099 */       while (i > 0 && next == null)
/* 1100 */         next = data[--i]; 
/* 1102 */       this.next = next;
/* 1103 */       this.hashIndex = i;
/* 1104 */       this.expectedModCount = parent.modCount;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1108 */       return (this.next != null);
/*      */     }
/*      */     
/*      */     protected AbstractHashedMap.HashEntry nextEntry() {
/* 1112 */       if (this.parent.modCount != this.expectedModCount)
/* 1113 */         throw new ConcurrentModificationException(); 
/* 1115 */       AbstractHashedMap.HashEntry newCurrent = this.next;
/* 1116 */       if (newCurrent == null)
/* 1117 */         throw new NoSuchElementException("No next() entry in the iteration"); 
/* 1119 */       AbstractHashedMap.HashEntry[] data = this.parent.data;
/* 1120 */       int i = this.hashIndex;
/* 1121 */       AbstractHashedMap.HashEntry n = newCurrent.next;
/* 1122 */       while (n == null && i > 0)
/* 1123 */         n = data[--i]; 
/* 1125 */       this.next = n;
/* 1126 */       this.hashIndex = i;
/* 1127 */       this.last = newCurrent;
/* 1128 */       return newCurrent;
/*      */     }
/*      */     
/*      */     protected AbstractHashedMap.HashEntry currentEntry() {
/* 1132 */       return this.last;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1136 */       if (this.last == null)
/* 1137 */         throw new IllegalStateException("remove() can only be called once after next()"); 
/* 1139 */       if (this.parent.modCount != this.expectedModCount)
/* 1140 */         throw new ConcurrentModificationException(); 
/* 1142 */       this.parent.remove(this.last.getKey());
/* 1143 */       this.last = null;
/* 1144 */       this.expectedModCount = this.parent.modCount;
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1148 */       if (this.last != null)
/* 1149 */         return "Iterator[" + this.last.getKey() + "=" + this.last.getValue() + "]"; 
/* 1151 */       return "Iterator[]";
/*      */     }
/*      */   }
/*      */   
/*      */   protected void doWriteObject(ObjectOutputStream out) throws IOException {
/* 1177 */     out.writeFloat(this.loadFactor);
/* 1178 */     out.writeInt(this.data.length);
/* 1179 */     out.writeInt(this.size);
/* 1180 */     for (MapIterator it = mapIterator(); it.hasNext(); ) {
/* 1181 */       out.writeObject(it.next());
/* 1182 */       out.writeObject(it.getValue());
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1205 */     this.loadFactor = in.readFloat();
/* 1206 */     int capacity = in.readInt();
/* 1207 */     int size = in.readInt();
/* 1208 */     init();
/* 1209 */     this.threshold = calculateThreshold(capacity, this.loadFactor);
/* 1210 */     this.data = new HashEntry[capacity];
/* 1211 */     for (int i = 0; i < size; i++) {
/* 1212 */       Object key = in.readObject();
/* 1213 */       Object value = in.readObject();
/* 1214 */       put(key, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Object clone() {
/*      */     try {
/* 1229 */       AbstractHashedMap cloned = (AbstractHashedMap)super.clone();
/* 1230 */       cloned.data = new HashEntry[this.data.length];
/* 1231 */       cloned.entrySet = null;
/* 1232 */       cloned.keySet = null;
/* 1233 */       cloned.values = null;
/* 1234 */       cloned.modCount = 0;
/* 1235 */       cloned.size = 0;
/* 1236 */       cloned.init();
/* 1237 */       cloned.putAll(this);
/* 1238 */       return cloned;
/* 1240 */     } catch (CloneNotSupportedException ex) {
/* 1241 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1252 */     if (obj == this)
/* 1253 */       return true; 
/* 1255 */     if (!(obj instanceof Map))
/* 1256 */       return false; 
/* 1258 */     Map map = (Map)obj;
/* 1259 */     if (map.size() != size())
/* 1260 */       return false; 
/* 1262 */     MapIterator it = mapIterator();
/*      */     try {
/* 1264 */       while (it.hasNext()) {
/* 1265 */         Object key = it.next();
/* 1266 */         Object value = it.getValue();
/* 1267 */         if (value == null) {
/* 1268 */           if (map.get(key) != null || !map.containsKey(key))
/* 1269 */             return false; 
/*      */           continue;
/*      */         } 
/* 1272 */         if (!value.equals(map.get(key)))
/* 1273 */           return false; 
/*      */       } 
/* 1277 */     } catch (ClassCastException ignored) {
/* 1278 */       return false;
/* 1279 */     } catch (NullPointerException ignored) {
/* 1280 */       return false;
/*      */     } 
/* 1282 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1291 */     int total = 0;
/* 1292 */     Iterator it = createEntrySetIterator();
/* 1293 */     while (it.hasNext())
/* 1294 */       total += it.next().hashCode(); 
/* 1296 */     return total;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1305 */     if (size() == 0)
/* 1306 */       return "{}"; 
/* 1308 */     StringBuffer buf = new StringBuffer(32 * size());
/* 1309 */     buf.append('{');
/* 1311 */     MapIterator it = mapIterator();
/* 1312 */     boolean hasNext = it.hasNext();
/* 1313 */     while (hasNext) {
/* 1314 */       Object key = it.next();
/* 1315 */       Object value = it.getValue();
/* 1316 */       buf.append((key == this) ? "(this Map)" : key).append('=').append((value == this) ? "(this Map)" : value);
/* 1320 */       hasNext = it.hasNext();
/* 1321 */       if (hasNext)
/* 1322 */         buf.append(',').append(' '); 
/*      */     } 
/* 1326 */     buf.append('}');
/* 1327 */     return buf.toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\AbstractHashedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */