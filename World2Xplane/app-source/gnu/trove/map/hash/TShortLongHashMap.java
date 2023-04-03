/*      */ package gnu.trove.map.hash;
/*      */ 
/*      */ import gnu.trove.TLongCollection;
/*      */ import gnu.trove.TShortCollection;
/*      */ import gnu.trove.function.TLongFunction;
/*      */ import gnu.trove.impl.HashFunctions;
/*      */ import gnu.trove.impl.hash.THashPrimitiveIterator;
/*      */ import gnu.trove.impl.hash.TPrimitiveHash;
/*      */ import gnu.trove.impl.hash.TShortLongHash;
/*      */ import gnu.trove.iterator.TLongIterator;
/*      */ import gnu.trove.iterator.TShortIterator;
/*      */ import gnu.trove.iterator.TShortLongIterator;
/*      */ import gnu.trove.map.TShortLongMap;
/*      */ import gnu.trove.procedure.TLongProcedure;
/*      */ import gnu.trove.procedure.TShortLongProcedure;
/*      */ import gnu.trove.procedure.TShortProcedure;
/*      */ import gnu.trove.set.TShortSet;
/*      */ import java.io.Externalizable;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInput;
/*      */ import java.io.ObjectOutput;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Map;
/*      */ 
/*      */ public class TShortLongHashMap extends TShortLongHash implements TShortLongMap, Externalizable {
/*      */   static final long serialVersionUID = 1L;
/*      */   
/*      */   protected transient long[] _values;
/*      */   
/*      */   public TShortLongHashMap() {}
/*      */   
/*      */   public TShortLongHashMap(int initialCapacity) {
/*   73 */     super(initialCapacity);
/*      */   }
/*      */   
/*      */   public TShortLongHashMap(int initialCapacity, float loadFactor) {
/*   86 */     super(initialCapacity, loadFactor);
/*      */   }
/*      */   
/*      */   public TShortLongHashMap(int initialCapacity, float loadFactor, short noEntryKey, long noEntryValue) {
/*  104 */     super(initialCapacity, loadFactor, noEntryKey, noEntryValue);
/*      */   }
/*      */   
/*      */   public TShortLongHashMap(short[] keys, long[] values) {
/*  116 */     super(Math.max(keys.length, values.length));
/*  118 */     int size = Math.min(keys.length, values.length);
/*  119 */     for (int i = 0; i < size; i++)
/*  120 */       put(keys[i], values[i]); 
/*      */   }
/*      */   
/*      */   public TShortLongHashMap(TShortLongMap map) {
/*  132 */     super(map.size());
/*  133 */     if (map instanceof TShortLongHashMap) {
/*  134 */       TShortLongHashMap hashmap = (TShortLongHashMap)map;
/*  135 */       this._loadFactor = hashmap._loadFactor;
/*  136 */       this.no_entry_key = hashmap.no_entry_key;
/*  137 */       this.no_entry_value = hashmap.no_entry_value;
/*  139 */       if (this.no_entry_key != 0)
/*  140 */         Arrays.fill(this._set, this.no_entry_key); 
/*  143 */       if (this.no_entry_value != 0L)
/*  144 */         Arrays.fill(this._values, this.no_entry_value); 
/*  146 */       setUp((int)Math.ceil((10.0F / this._loadFactor)));
/*      */     } 
/*  148 */     putAll(map);
/*      */   }
/*      */   
/*      */   protected int setUp(int initialCapacity) {
/*  162 */     int capacity = super.setUp(initialCapacity);
/*  163 */     this._values = new long[capacity];
/*  164 */     return capacity;
/*      */   }
/*      */   
/*      */   protected void rehash(int newCapacity) {
/*  175 */     int oldCapacity = this._set.length;
/*  177 */     short[] oldKeys = this._set;
/*  178 */     long[] oldVals = this._values;
/*  179 */     byte[] oldStates = this._states;
/*  181 */     this._set = new short[newCapacity];
/*  182 */     this._values = new long[newCapacity];
/*  183 */     this._states = new byte[newCapacity];
/*  185 */     for (int i = oldCapacity; i-- > 0;) {
/*  186 */       if (oldStates[i] == 1) {
/*  187 */         short o = oldKeys[i];
/*  188 */         int index = insertKey(o);
/*  189 */         this._values[index] = oldVals[i];
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public long put(short key, long value) {
/*  197 */     int index = insertKey(key);
/*  198 */     return doPut(key, value, index);
/*      */   }
/*      */   
/*      */   public long putIfAbsent(short key, long value) {
/*  204 */     int index = insertKey(key);
/*  205 */     if (index < 0)
/*  206 */       return this._values[-index - 1]; 
/*  207 */     return doPut(key, value, index);
/*      */   }
/*      */   
/*      */   private long doPut(short key, long value, int index) {
/*  212 */     long previous = this.no_entry_value;
/*  213 */     boolean isNewMapping = true;
/*  214 */     if (index < 0) {
/*  215 */       index = -index - 1;
/*  216 */       previous = this._values[index];
/*  217 */       isNewMapping = false;
/*      */     } 
/*  219 */     this._values[index] = value;
/*  221 */     if (isNewMapping)
/*  222 */       postInsertHook(this.consumeFreeSlot); 
/*  225 */     return previous;
/*      */   }
/*      */   
/*      */   public void putAll(Map<? extends Short, ? extends Long> map) {
/*  231 */     ensureCapacity(map.size());
/*  233 */     for (Map.Entry<? extends Short, ? extends Long> entry : map.entrySet())
/*  234 */       put(((Short)entry.getKey()).shortValue(), ((Long)entry.getValue()).longValue()); 
/*      */   }
/*      */   
/*      */   public void putAll(TShortLongMap map) {
/*  241 */     ensureCapacity(map.size());
/*  242 */     TShortLongIterator iter = map.iterator();
/*  243 */     while (iter.hasNext()) {
/*  244 */       iter.advance();
/*  245 */       put(iter.key(), iter.value());
/*      */     } 
/*      */   }
/*      */   
/*      */   public long get(short key) {
/*  252 */     int index = index(key);
/*  253 */     return (index < 0) ? this.no_entry_value : this._values[index];
/*      */   }
/*      */   
/*      */   public void clear() {
/*  259 */     super.clear();
/*  260 */     Arrays.fill(this._set, 0, this._set.length, this.no_entry_key);
/*  261 */     Arrays.fill(this._values, 0, this._values.length, this.no_entry_value);
/*  262 */     Arrays.fill(this._states, 0, this._states.length, (byte)0);
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  268 */     return (0 == this._size);
/*      */   }
/*      */   
/*      */   public long remove(short key) {
/*  274 */     long prev = this.no_entry_value;
/*  275 */     int index = index(key);
/*  276 */     if (index >= 0) {
/*  277 */       prev = this._values[index];
/*  278 */       removeAt(index);
/*      */     } 
/*  280 */     return prev;
/*      */   }
/*      */   
/*      */   protected void removeAt(int index) {
/*  286 */     this._values[index] = this.no_entry_value;
/*  287 */     super.removeAt(index);
/*      */   }
/*      */   
/*      */   public TShortSet keySet() {
/*  293 */     return new TKeyView();
/*      */   }
/*      */   
/*      */   public short[] keys() {
/*  299 */     short[] keys = new short[size()];
/*  300 */     short[] k = this._set;
/*  301 */     byte[] states = this._states;
/*  303 */     for (int i = k.length, j = 0; i-- > 0;) {
/*  304 */       if (states[i] == 1)
/*  305 */         keys[j++] = k[i]; 
/*      */     } 
/*  308 */     return keys;
/*      */   }
/*      */   
/*      */   public short[] keys(short[] array) {
/*  314 */     int size = size();
/*  315 */     if (array.length < size)
/*  316 */       array = new short[size]; 
/*  319 */     short[] keys = this._set;
/*  320 */     byte[] states = this._states;
/*  322 */     for (int i = keys.length, j = 0; i-- > 0;) {
/*  323 */       if (states[i] == 1)
/*  324 */         array[j++] = keys[i]; 
/*      */     } 
/*  327 */     return array;
/*      */   }
/*      */   
/*      */   public TLongCollection valueCollection() {
/*  333 */     return new TValueView();
/*      */   }
/*      */   
/*      */   public long[] values() {
/*  339 */     long[] vals = new long[size()];
/*  340 */     long[] v = this._values;
/*  341 */     byte[] states = this._states;
/*  343 */     for (int i = v.length, j = 0; i-- > 0;) {
/*  344 */       if (states[i] == 1)
/*  345 */         vals[j++] = v[i]; 
/*      */     } 
/*  348 */     return vals;
/*      */   }
/*      */   
/*      */   public long[] values(long[] array) {
/*  354 */     int size = size();
/*  355 */     if (array.length < size)
/*  356 */       array = new long[size]; 
/*  359 */     long[] v = this._values;
/*  360 */     byte[] states = this._states;
/*  362 */     for (int i = v.length, j = 0; i-- > 0;) {
/*  363 */       if (states[i] == 1)
/*  364 */         array[j++] = v[i]; 
/*      */     } 
/*  367 */     return array;
/*      */   }
/*      */   
/*      */   public boolean containsValue(long val) {
/*  373 */     byte[] states = this._states;
/*  374 */     long[] vals = this._values;
/*  376 */     for (int i = vals.length; i-- > 0;) {
/*  377 */       if (states[i] == 1 && val == vals[i])
/*  378 */         return true; 
/*      */     } 
/*  381 */     return false;
/*      */   }
/*      */   
/*      */   public boolean containsKey(short key) {
/*  387 */     return contains(key);
/*      */   }
/*      */   
/*      */   public TShortLongIterator iterator() {
/*  393 */     return new TShortLongHashIterator(this);
/*      */   }
/*      */   
/*      */   public boolean forEachKey(TShortProcedure procedure) {
/*  399 */     return forEach(procedure);
/*      */   }
/*      */   
/*      */   public boolean forEachValue(TLongProcedure procedure) {
/*  405 */     byte[] states = this._states;
/*  406 */     long[] values = this._values;
/*  407 */     for (int i = values.length; i-- > 0;) {
/*  408 */       if (states[i] == 1 && !procedure.execute(values[i]))
/*  409 */         return false; 
/*      */     } 
/*  412 */     return true;
/*      */   }
/*      */   
/*      */   public boolean forEachEntry(TShortLongProcedure procedure) {
/*  418 */     byte[] states = this._states;
/*  419 */     short[] keys = this._set;
/*  420 */     long[] values = this._values;
/*  421 */     for (int i = keys.length; i-- > 0;) {
/*  422 */       if (states[i] == 1 && !procedure.execute(keys[i], values[i]))
/*  423 */         return false; 
/*      */     } 
/*  426 */     return true;
/*      */   }
/*      */   
/*      */   public void transformValues(TLongFunction function) {
/*  432 */     byte[] states = this._states;
/*  433 */     long[] values = this._values;
/*  434 */     for (int i = values.length; i-- > 0;) {
/*  435 */       if (states[i] == 1)
/*  436 */         values[i] = function.execute(values[i]); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean retainEntries(TShortLongProcedure procedure) {
/*  444 */     boolean modified = false;
/*  445 */     byte[] states = this._states;
/*  446 */     short[] keys = this._set;
/*  447 */     long[] values = this._values;
/*  451 */     tempDisableAutoCompaction();
/*      */     try {
/*  453 */       for (int i = keys.length; i-- > 0;) {
/*  454 */         if (states[i] == 1 && !procedure.execute(keys[i], values[i])) {
/*  455 */           removeAt(i);
/*  456 */           modified = true;
/*      */         } 
/*      */       } 
/*      */     } finally {
/*  461 */       reenableAutoCompaction(true);
/*      */     } 
/*  464 */     return modified;
/*      */   }
/*      */   
/*      */   public boolean increment(short key) {
/*  470 */     return adjustValue(key, 1L);
/*      */   }
/*      */   
/*      */   public boolean adjustValue(short key, long amount) {
/*  476 */     int index = index(key);
/*  477 */     if (index < 0)
/*  478 */       return false; 
/*  480 */     this._values[index] = this._values[index] + amount;
/*  481 */     return true;
/*      */   }
/*      */   
/*      */   public long adjustOrPutValue(short key, long adjust_amount, long put_amount) {
/*  488 */     int index = insertKey(key);
/*  492 */     index = -index - 1;
/*  493 */     long newValue = this._values[index] = this._values[index] + adjust_amount;
/*  494 */     boolean isNewMapping = false;
/*  496 */     newValue = this._values[index] = put_amount;
/*  497 */     isNewMapping = true;
/*  500 */     byte previousState = this._states[index];
/*  502 */     if (isNewMapping)
/*  503 */       postInsertHook(this.consumeFreeSlot); 
/*  506 */     return newValue;
/*      */   }
/*      */   
/*      */   protected class TKeyView implements TShortSet {
/*      */     public TShortIterator iterator() {
/*  515 */       return new TShortLongHashMap.TShortLongKeyHashIterator((TPrimitiveHash)TShortLongHashMap.this);
/*      */     }
/*      */     
/*      */     public short getNoEntryValue() {
/*  521 */       return TShortLongHashMap.this.no_entry_key;
/*      */     }
/*      */     
/*      */     public int size() {
/*  527 */       return TShortLongHashMap.this._size;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  533 */       return (0 == TShortLongHashMap.this._size);
/*      */     }
/*      */     
/*      */     public boolean contains(short entry) {
/*  539 */       return TShortLongHashMap.this.contains(entry);
/*      */     }
/*      */     
/*      */     public short[] toArray() {
/*  545 */       return TShortLongHashMap.this.keys();
/*      */     }
/*      */     
/*      */     public short[] toArray(short[] dest) {
/*  551 */       return TShortLongHashMap.this.keys(dest);
/*      */     }
/*      */     
/*      */     public boolean add(short entry) {
/*  561 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean remove(short entry) {
/*  567 */       return (TShortLongHashMap.this.no_entry_value != TShortLongHashMap.this.remove(entry));
/*      */     }
/*      */     
/*      */     public boolean containsAll(Collection<?> collection) {
/*  573 */       for (Object element : collection) {
/*  574 */         if (element instanceof Short) {
/*  575 */           short ele = ((Short)element).shortValue();
/*  576 */           if (!TShortLongHashMap.this.containsKey(ele))
/*  577 */             return false; 
/*      */           continue;
/*      */         } 
/*  580 */         return false;
/*      */       } 
/*  583 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(TShortCollection collection) {
/*  589 */       TShortIterator iter = collection.iterator();
/*  590 */       while (iter.hasNext()) {
/*  591 */         if (!TShortLongHashMap.this.containsKey(iter.next()))
/*  592 */           return false; 
/*      */       } 
/*  595 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(short[] array) {
/*  601 */       for (short element : array) {
/*  602 */         if (!TShortLongHashMap.this.contains(element))
/*  603 */           return false; 
/*      */       } 
/*  606 */       return true;
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends Short> collection) {
/*  616 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(TShortCollection collection) {
/*  626 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(short[] array) {
/*  636 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  643 */       boolean modified = false;
/*  644 */       TShortIterator iter = iterator();
/*  645 */       while (iter.hasNext()) {
/*  646 */         if (!collection.contains(Short.valueOf(iter.next()))) {
/*  647 */           iter.remove();
/*  648 */           modified = true;
/*      */         } 
/*      */       } 
/*  651 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(TShortCollection collection) {
/*  657 */       if (this == collection)
/*  658 */         return false; 
/*  660 */       boolean modified = false;
/*  661 */       TShortIterator iter = iterator();
/*  662 */       while (iter.hasNext()) {
/*  663 */         if (!collection.contains(iter.next())) {
/*  664 */           iter.remove();
/*  665 */           modified = true;
/*      */         } 
/*      */       } 
/*  668 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(short[] array) {
/*  674 */       boolean changed = false;
/*  675 */       Arrays.sort(array);
/*  676 */       short[] set = TShortLongHashMap.this._set;
/*  677 */       byte[] states = TShortLongHashMap.this._states;
/*  679 */       for (int i = set.length; i-- > 0;) {
/*  680 */         if (states[i] == 1 && Arrays.binarySearch(array, set[i]) < 0) {
/*  681 */           TShortLongHashMap.this.removeAt(i);
/*  682 */           changed = true;
/*      */         } 
/*      */       } 
/*  685 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(Collection<?> collection) {
/*  691 */       boolean changed = false;
/*  692 */       for (Object element : collection) {
/*  693 */         if (element instanceof Short) {
/*  694 */           short c = ((Short)element).shortValue();
/*  695 */           if (remove(c))
/*  696 */             changed = true; 
/*      */         } 
/*      */       } 
/*  700 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(TShortCollection collection) {
/*  706 */       if (this == collection) {
/*  707 */         clear();
/*  708 */         return true;
/*      */       } 
/*  710 */       boolean changed = false;
/*  711 */       TShortIterator iter = collection.iterator();
/*  712 */       while (iter.hasNext()) {
/*  713 */         short element = iter.next();
/*  714 */         if (remove(element))
/*  715 */           changed = true; 
/*      */       } 
/*  718 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(short[] array) {
/*  724 */       boolean changed = false;
/*  725 */       for (int i = array.length; i-- > 0;) {
/*  726 */         if (remove(array[i]))
/*  727 */           changed = true; 
/*      */       } 
/*  730 */       return changed;
/*      */     }
/*      */     
/*      */     public void clear() {
/*  736 */       TShortLongHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public boolean forEach(TShortProcedure procedure) {
/*  742 */       return TShortLongHashMap.this.forEachKey(procedure);
/*      */     }
/*      */     
/*      */     public boolean equals(Object other) {
/*  748 */       if (!(other instanceof TShortSet))
/*  749 */         return false; 
/*  751 */       TShortSet that = (TShortSet)other;
/*  752 */       if (that.size() != size())
/*  753 */         return false; 
/*  755 */       for (int i = TShortLongHashMap.this._states.length; i-- > 0;) {
/*  756 */         if (TShortLongHashMap.this._states[i] == 1 && 
/*  757 */           !that.contains(TShortLongHashMap.this._set[i]))
/*  758 */           return false; 
/*      */       } 
/*  762 */       return true;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  768 */       int hashcode = 0;
/*  769 */       for (int i = TShortLongHashMap.this._states.length; i-- > 0;) {
/*  770 */         if (TShortLongHashMap.this._states[i] == 1)
/*  771 */           hashcode += HashFunctions.hash(TShortLongHashMap.this._set[i]); 
/*      */       } 
/*  774 */       return hashcode;
/*      */     }
/*      */     
/*      */     public String toString() {
/*  780 */       final StringBuilder buf = new StringBuilder("{");
/*  781 */       TShortLongHashMap.this.forEachKey(new TShortProcedure() {
/*      */             private boolean first = true;
/*      */             
/*      */             public boolean execute(short key) {
/*  786 */               if (this.first) {
/*  787 */                 this.first = false;
/*      */               } else {
/*  789 */                 buf.append(", ");
/*      */               } 
/*  792 */               buf.append(key);
/*  793 */               return true;
/*      */             }
/*      */           });
/*  796 */       buf.append("}");
/*  797 */       return buf.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   protected class TValueView implements TLongCollection {
/*      */     public TLongIterator iterator() {
/*  807 */       return new TShortLongHashMap.TShortLongValueHashIterator((TPrimitiveHash)TShortLongHashMap.this);
/*      */     }
/*      */     
/*      */     public long getNoEntryValue() {
/*  813 */       return TShortLongHashMap.this.no_entry_value;
/*      */     }
/*      */     
/*      */     public int size() {
/*  819 */       return TShortLongHashMap.this._size;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  825 */       return (0 == TShortLongHashMap.this._size);
/*      */     }
/*      */     
/*      */     public boolean contains(long entry) {
/*  831 */       return TShortLongHashMap.this.containsValue(entry);
/*      */     }
/*      */     
/*      */     public long[] toArray() {
/*  837 */       return TShortLongHashMap.this.values();
/*      */     }
/*      */     
/*      */     public long[] toArray(long[] dest) {
/*  843 */       return TShortLongHashMap.this.values(dest);
/*      */     }
/*      */     
/*      */     public boolean add(long entry) {
/*  849 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean remove(long entry) {
/*  855 */       long[] values = TShortLongHashMap.this._values;
/*  856 */       short[] set = TShortLongHashMap.this._set;
/*  858 */       for (int i = values.length; i-- > 0;) {
/*  859 */         if (set[i] != 0 && set[i] != 2 && entry == values[i]) {
/*  860 */           TShortLongHashMap.this.removeAt(i);
/*  861 */           return true;
/*      */         } 
/*      */       } 
/*  864 */       return false;
/*      */     }
/*      */     
/*      */     public boolean containsAll(Collection<?> collection) {
/*  870 */       for (Object element : collection) {
/*  871 */         if (element instanceof Long) {
/*  872 */           long ele = ((Long)element).longValue();
/*  873 */           if (!TShortLongHashMap.this.containsValue(ele))
/*  874 */             return false; 
/*      */           continue;
/*      */         } 
/*  877 */         return false;
/*      */       } 
/*  880 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(TLongCollection collection) {
/*  886 */       TLongIterator iter = collection.iterator();
/*  887 */       while (iter.hasNext()) {
/*  888 */         if (!TShortLongHashMap.this.containsValue(iter.next()))
/*  889 */           return false; 
/*      */       } 
/*  892 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(long[] array) {
/*  898 */       for (long element : array) {
/*  899 */         if (!TShortLongHashMap.this.containsValue(element))
/*  900 */           return false; 
/*      */       } 
/*  903 */       return true;
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends Long> collection) {
/*  909 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(TLongCollection collection) {
/*  915 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(long[] array) {
/*  921 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  928 */       boolean modified = false;
/*  929 */       TLongIterator iter = iterator();
/*  930 */       while (iter.hasNext()) {
/*  931 */         if (!collection.contains(Long.valueOf(iter.next()))) {
/*  932 */           iter.remove();
/*  933 */           modified = true;
/*      */         } 
/*      */       } 
/*  936 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(TLongCollection collection) {
/*  942 */       if (this == collection)
/*  943 */         return false; 
/*  945 */       boolean modified = false;
/*  946 */       TLongIterator iter = iterator();
/*  947 */       while (iter.hasNext()) {
/*  948 */         if (!collection.contains(iter.next())) {
/*  949 */           iter.remove();
/*  950 */           modified = true;
/*      */         } 
/*      */       } 
/*  953 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(long[] array) {
/*  959 */       boolean changed = false;
/*  960 */       Arrays.sort(array);
/*  961 */       long[] values = TShortLongHashMap.this._values;
/*  962 */       byte[] states = TShortLongHashMap.this._states;
/*  964 */       for (int i = values.length; i-- > 0;) {
/*  965 */         if (states[i] == 1 && Arrays.binarySearch(array, values[i]) < 0) {
/*  966 */           TShortLongHashMap.this.removeAt(i);
/*  967 */           changed = true;
/*      */         } 
/*      */       } 
/*  970 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(Collection<?> collection) {
/*  976 */       boolean changed = false;
/*  977 */       for (Object element : collection) {
/*  978 */         if (element instanceof Long) {
/*  979 */           long c = ((Long)element).longValue();
/*  980 */           if (remove(c))
/*  981 */             changed = true; 
/*      */         } 
/*      */       } 
/*  985 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(TLongCollection collection) {
/*  991 */       if (this == collection) {
/*  992 */         clear();
/*  993 */         return true;
/*      */       } 
/*  995 */       boolean changed = false;
/*  996 */       TLongIterator iter = collection.iterator();
/*  997 */       while (iter.hasNext()) {
/*  998 */         long element = iter.next();
/*  999 */         if (remove(element))
/* 1000 */           changed = true; 
/*      */       } 
/* 1003 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(long[] array) {
/* 1009 */       boolean changed = false;
/* 1010 */       for (int i = array.length; i-- > 0;) {
/* 1011 */         if (remove(array[i]))
/* 1012 */           changed = true; 
/*      */       } 
/* 1015 */       return changed;
/*      */     }
/*      */     
/*      */     public void clear() {
/* 1021 */       TShortLongHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public boolean forEach(TLongProcedure procedure) {
/* 1027 */       return TShortLongHashMap.this.forEachValue(procedure);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1034 */       final StringBuilder buf = new StringBuilder("{");
/* 1035 */       TShortLongHashMap.this.forEachValue(new TLongProcedure() {
/*      */             private boolean first = true;
/*      */             
/*      */             public boolean execute(long value) {
/* 1039 */               if (this.first) {
/* 1040 */                 this.first = false;
/*      */               } else {
/* 1042 */                 buf.append(", ");
/*      */               } 
/* 1045 */               buf.append(value);
/* 1046 */               return true;
/*      */             }
/*      */           });
/* 1049 */       buf.append("}");
/* 1050 */       return buf.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   class TShortLongKeyHashIterator extends THashPrimitiveIterator implements TShortIterator {
/*      */     TShortLongKeyHashIterator(TPrimitiveHash hash) {
/* 1063 */       super(hash);
/*      */     }
/*      */     
/*      */     public short next() {
/* 1068 */       moveToNextIndex();
/* 1069 */       return TShortLongHashMap.this._set[this._index];
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1074 */       if (this._expectedSize != this._hash.size())
/* 1075 */         throw new ConcurrentModificationException(); 
/*      */       try {
/* 1080 */         this._hash.tempDisableAutoCompaction();
/* 1081 */         TShortLongHashMap.this.removeAt(this._index);
/*      */       } finally {
/* 1084 */         this._hash.reenableAutoCompaction(false);
/*      */       } 
/* 1087 */       this._expectedSize--;
/*      */     }
/*      */   }
/*      */   
/*      */   class TShortLongValueHashIterator extends THashPrimitiveIterator implements TLongIterator {
/*      */     TShortLongValueHashIterator(TPrimitiveHash hash) {
/* 1101 */       super(hash);
/*      */     }
/*      */     
/*      */     public long next() {
/* 1106 */       moveToNextIndex();
/* 1107 */       return TShortLongHashMap.this._values[this._index];
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1112 */       if (this._expectedSize != this._hash.size())
/* 1113 */         throw new ConcurrentModificationException(); 
/*      */       try {
/* 1118 */         this._hash.tempDisableAutoCompaction();
/* 1119 */         TShortLongHashMap.this.removeAt(this._index);
/*      */       } finally {
/* 1122 */         this._hash.reenableAutoCompaction(false);
/*      */       } 
/* 1125 */       this._expectedSize--;
/*      */     }
/*      */   }
/*      */   
/*      */   class TShortLongHashIterator extends THashPrimitiveIterator implements TShortLongIterator {
/*      */     TShortLongHashIterator(TShortLongHashMap map) {
/* 1138 */       super((TPrimitiveHash)map);
/*      */     }
/*      */     
/*      */     public void advance() {
/* 1143 */       moveToNextIndex();
/*      */     }
/*      */     
/*      */     public short key() {
/* 1148 */       return TShortLongHashMap.this._set[this._index];
/*      */     }
/*      */     
/*      */     public long value() {
/* 1153 */       return TShortLongHashMap.this._values[this._index];
/*      */     }
/*      */     
/*      */     public long setValue(long val) {
/* 1158 */       long old = value();
/* 1159 */       TShortLongHashMap.this._values[this._index] = val;
/* 1160 */       return old;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1165 */       if (this._expectedSize != this._hash.size())
/* 1166 */         throw new ConcurrentModificationException(); 
/*      */       try {
/* 1170 */         this._hash.tempDisableAutoCompaction();
/* 1171 */         TShortLongHashMap.this.removeAt(this._index);
/*      */       } finally {
/* 1174 */         this._hash.reenableAutoCompaction(false);
/*      */       } 
/* 1176 */       this._expectedSize--;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean equals(Object other) {
/* 1184 */     if (!(other instanceof TShortLongMap))
/* 1185 */       return false; 
/* 1187 */     TShortLongMap that = (TShortLongMap)other;
/* 1188 */     if (that.size() != size())
/* 1189 */       return false; 
/* 1191 */     long[] values = this._values;
/* 1192 */     byte[] states = this._states;
/* 1193 */     long this_no_entry_value = getNoEntryValue();
/* 1194 */     long that_no_entry_value = that.getNoEntryValue();
/* 1195 */     for (int i = values.length; i-- > 0;) {
/* 1196 */       if (states[i] == 1) {
/* 1197 */         short key = this._set[i];
/* 1198 */         long that_value = that.get(key);
/* 1199 */         long this_value = values[i];
/* 1200 */         if (this_value != that_value && this_value != this_no_entry_value && that_value != that_no_entry_value)
/* 1203 */           return false; 
/*      */       } 
/*      */     } 
/* 1207 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1214 */     int hashcode = 0;
/* 1215 */     byte[] states = this._states;
/* 1216 */     for (int i = this._values.length; i-- > 0;) {
/* 1217 */       if (states[i] == 1)
/* 1218 */         hashcode += HashFunctions.hash(this._set[i]) ^ HashFunctions.hash(this._values[i]); 
/*      */     } 
/* 1222 */     return hashcode;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1229 */     final StringBuilder buf = new StringBuilder("{");
/* 1230 */     forEachEntry(new TShortLongProcedure() {
/*      */           private boolean first = true;
/*      */           
/*      */           public boolean execute(short key, long value) {
/* 1233 */             if (this.first) {
/* 1233 */               this.first = false;
/*      */             } else {
/* 1234 */               buf.append(", ");
/*      */             } 
/* 1236 */             buf.append(key);
/* 1237 */             buf.append("=");
/* 1238 */             buf.append(value);
/* 1239 */             return true;
/*      */           }
/*      */         });
/* 1242 */     buf.append("}");
/* 1243 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public void writeExternal(ObjectOutput out) throws IOException {
/* 1250 */     out.writeByte(0);
/* 1253 */     super.writeExternal(out);
/* 1256 */     out.writeInt(this._size);
/* 1259 */     for (int i = this._states.length; i-- > 0;) {
/* 1260 */       if (this._states[i] == 1) {
/* 1261 */         out.writeShort(this._set[i]);
/* 1262 */         out.writeLong(this._values[i]);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 1271 */     in.readByte();
/* 1274 */     super.readExternal(in);
/* 1277 */     int size = in.readInt();
/* 1278 */     setUp(size);
/* 1281 */     while (size-- > 0) {
/* 1282 */       short key = in.readShort();
/* 1283 */       long val = in.readLong();
/* 1284 */       put(key, val);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\hash\TShortLongHashMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */