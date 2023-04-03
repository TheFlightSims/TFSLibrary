/*      */ package gnu.trove.map.hash;
/*      */ 
/*      */ import gnu.trove.TCharCollection;
/*      */ import gnu.trove.TLongCollection;
/*      */ import gnu.trove.function.TCharFunction;
/*      */ import gnu.trove.impl.HashFunctions;
/*      */ import gnu.trove.impl.hash.THashPrimitiveIterator;
/*      */ import gnu.trove.impl.hash.TLongCharHash;
/*      */ import gnu.trove.impl.hash.TPrimitiveHash;
/*      */ import gnu.trove.iterator.TCharIterator;
/*      */ import gnu.trove.iterator.TLongCharIterator;
/*      */ import gnu.trove.iterator.TLongIterator;
/*      */ import gnu.trove.map.TLongCharMap;
/*      */ import gnu.trove.procedure.TCharProcedure;
/*      */ import gnu.trove.procedure.TLongCharProcedure;
/*      */ import gnu.trove.procedure.TLongProcedure;
/*      */ import gnu.trove.set.TLongSet;
/*      */ import java.io.Externalizable;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInput;
/*      */ import java.io.ObjectOutput;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Map;
/*      */ 
/*      */ public class TLongCharHashMap extends TLongCharHash implements TLongCharMap, Externalizable {
/*      */   static final long serialVersionUID = 1L;
/*      */   
/*      */   protected transient char[] _values;
/*      */   
/*      */   public TLongCharHashMap() {}
/*      */   
/*      */   public TLongCharHashMap(int initialCapacity) {
/*   73 */     super(initialCapacity);
/*      */   }
/*      */   
/*      */   public TLongCharHashMap(int initialCapacity, float loadFactor) {
/*   86 */     super(initialCapacity, loadFactor);
/*      */   }
/*      */   
/*      */   public TLongCharHashMap(int initialCapacity, float loadFactor, long noEntryKey, char noEntryValue) {
/*  104 */     super(initialCapacity, loadFactor, noEntryKey, noEntryValue);
/*      */   }
/*      */   
/*      */   public TLongCharHashMap(long[] keys, char[] values) {
/*  116 */     super(Math.max(keys.length, values.length));
/*  118 */     int size = Math.min(keys.length, values.length);
/*  119 */     for (int i = 0; i < size; i++)
/*  120 */       put(keys[i], values[i]); 
/*      */   }
/*      */   
/*      */   public TLongCharHashMap(TLongCharMap map) {
/*  132 */     super(map.size());
/*  133 */     if (map instanceof TLongCharHashMap) {
/*  134 */       TLongCharHashMap hashmap = (TLongCharHashMap)map;
/*  135 */       this._loadFactor = hashmap._loadFactor;
/*  136 */       this.no_entry_key = hashmap.no_entry_key;
/*  137 */       this.no_entry_value = hashmap.no_entry_value;
/*  139 */       if (this.no_entry_key != 0L)
/*  140 */         Arrays.fill(this._set, this.no_entry_key); 
/*  143 */       if (this.no_entry_value != '\000')
/*  144 */         Arrays.fill(this._values, this.no_entry_value); 
/*  146 */       setUp((int)Math.ceil((10.0F / this._loadFactor)));
/*      */     } 
/*  148 */     putAll(map);
/*      */   }
/*      */   
/*      */   protected int setUp(int initialCapacity) {
/*  162 */     int capacity = super.setUp(initialCapacity);
/*  163 */     this._values = new char[capacity];
/*  164 */     return capacity;
/*      */   }
/*      */   
/*      */   protected void rehash(int newCapacity) {
/*  175 */     int oldCapacity = this._set.length;
/*  177 */     long[] oldKeys = this._set;
/*  178 */     char[] oldVals = this._values;
/*  179 */     byte[] oldStates = this._states;
/*  181 */     this._set = new long[newCapacity];
/*  182 */     this._values = new char[newCapacity];
/*  183 */     this._states = new byte[newCapacity];
/*  185 */     for (int i = oldCapacity; i-- > 0;) {
/*  186 */       if (oldStates[i] == 1) {
/*  187 */         long o = oldKeys[i];
/*  188 */         int index = insertKey(o);
/*  189 */         this._values[index] = oldVals[i];
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public char put(long key, char value) {
/*  197 */     int index = insertKey(key);
/*  198 */     return doPut(key, value, index);
/*      */   }
/*      */   
/*      */   public char putIfAbsent(long key, char value) {
/*  204 */     int index = insertKey(key);
/*  205 */     if (index < 0)
/*  206 */       return this._values[-index - 1]; 
/*  207 */     return doPut(key, value, index);
/*      */   }
/*      */   
/*      */   private char doPut(long key, char value, int index) {
/*  212 */     char previous = this.no_entry_value;
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
/*      */   public void putAll(Map<? extends Long, ? extends Character> map) {
/*  231 */     ensureCapacity(map.size());
/*  233 */     for (Map.Entry<? extends Long, ? extends Character> entry : map.entrySet())
/*  234 */       put(((Long)entry.getKey()).longValue(), ((Character)entry.getValue()).charValue()); 
/*      */   }
/*      */   
/*      */   public void putAll(TLongCharMap map) {
/*  241 */     ensureCapacity(map.size());
/*  242 */     TLongCharIterator iter = map.iterator();
/*  243 */     while (iter.hasNext()) {
/*  244 */       iter.advance();
/*  245 */       put(iter.key(), iter.value());
/*      */     } 
/*      */   }
/*      */   
/*      */   public char get(long key) {
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
/*      */   public char remove(long key) {
/*  274 */     char prev = this.no_entry_value;
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
/*      */   public TLongSet keySet() {
/*  293 */     return new TKeyView();
/*      */   }
/*      */   
/*      */   public long[] keys() {
/*  299 */     long[] keys = new long[size()];
/*  300 */     long[] k = this._set;
/*  301 */     byte[] states = this._states;
/*  303 */     for (int i = k.length, j = 0; i-- > 0;) {
/*  304 */       if (states[i] == 1)
/*  305 */         keys[j++] = k[i]; 
/*      */     } 
/*  308 */     return keys;
/*      */   }
/*      */   
/*      */   public long[] keys(long[] array) {
/*  314 */     int size = size();
/*  315 */     if (array.length < size)
/*  316 */       array = new long[size]; 
/*  319 */     long[] keys = this._set;
/*  320 */     byte[] states = this._states;
/*  322 */     for (int i = keys.length, j = 0; i-- > 0;) {
/*  323 */       if (states[i] == 1)
/*  324 */         array[j++] = keys[i]; 
/*      */     } 
/*  327 */     return array;
/*      */   }
/*      */   
/*      */   public TCharCollection valueCollection() {
/*  333 */     return new TValueView();
/*      */   }
/*      */   
/*      */   public char[] values() {
/*  339 */     char[] vals = new char[size()];
/*  340 */     char[] v = this._values;
/*  341 */     byte[] states = this._states;
/*  343 */     for (int i = v.length, j = 0; i-- > 0;) {
/*  344 */       if (states[i] == 1)
/*  345 */         vals[j++] = v[i]; 
/*      */     } 
/*  348 */     return vals;
/*      */   }
/*      */   
/*      */   public char[] values(char[] array) {
/*  354 */     int size = size();
/*  355 */     if (array.length < size)
/*  356 */       array = new char[size]; 
/*  359 */     char[] v = this._values;
/*  360 */     byte[] states = this._states;
/*  362 */     for (int i = v.length, j = 0; i-- > 0;) {
/*  363 */       if (states[i] == 1)
/*  364 */         array[j++] = v[i]; 
/*      */     } 
/*  367 */     return array;
/*      */   }
/*      */   
/*      */   public boolean containsValue(char val) {
/*  373 */     byte[] states = this._states;
/*  374 */     char[] vals = this._values;
/*  376 */     for (int i = vals.length; i-- > 0;) {
/*  377 */       if (states[i] == 1 && val == vals[i])
/*  378 */         return true; 
/*      */     } 
/*  381 */     return false;
/*      */   }
/*      */   
/*      */   public boolean containsKey(long key) {
/*  387 */     return contains(key);
/*      */   }
/*      */   
/*      */   public TLongCharIterator iterator() {
/*  393 */     return new TLongCharHashIterator(this);
/*      */   }
/*      */   
/*      */   public boolean forEachKey(TLongProcedure procedure) {
/*  399 */     return forEach(procedure);
/*      */   }
/*      */   
/*      */   public boolean forEachValue(TCharProcedure procedure) {
/*  405 */     byte[] states = this._states;
/*  406 */     char[] values = this._values;
/*  407 */     for (int i = values.length; i-- > 0;) {
/*  408 */       if (states[i] == 1 && !procedure.execute(values[i]))
/*  409 */         return false; 
/*      */     } 
/*  412 */     return true;
/*      */   }
/*      */   
/*      */   public boolean forEachEntry(TLongCharProcedure procedure) {
/*  418 */     byte[] states = this._states;
/*  419 */     long[] keys = this._set;
/*  420 */     char[] values = this._values;
/*  421 */     for (int i = keys.length; i-- > 0;) {
/*  422 */       if (states[i] == 1 && !procedure.execute(keys[i], values[i]))
/*  423 */         return false; 
/*      */     } 
/*  426 */     return true;
/*      */   }
/*      */   
/*      */   public void transformValues(TCharFunction function) {
/*  432 */     byte[] states = this._states;
/*  433 */     char[] values = this._values;
/*  434 */     for (int i = values.length; i-- > 0;) {
/*  435 */       if (states[i] == 1)
/*  436 */         values[i] = function.execute(values[i]); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean retainEntries(TLongCharProcedure procedure) {
/*  444 */     boolean modified = false;
/*  445 */     byte[] states = this._states;
/*  446 */     long[] keys = this._set;
/*  447 */     char[] values = this._values;
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
/*      */   public boolean increment(long key) {
/*  470 */     return adjustValue(key, '\001');
/*      */   }
/*      */   
/*      */   public boolean adjustValue(long key, char amount) {
/*  476 */     int index = index(key);
/*  477 */     if (index < 0)
/*  478 */       return false; 
/*  480 */     this._values[index] = (char)(this._values[index] + amount);
/*  481 */     return true;
/*      */   }
/*      */   
/*      */   public char adjustOrPutValue(long key, char adjust_amount, char put_amount) {
/*      */     boolean isNewMapping;
/*      */     char newValue;
/*  488 */     int index = insertKey(key);
/*  491 */     if (index < 0) {
/*  492 */       index = -index - 1;
/*  493 */       newValue = this._values[index] = (char)(this._values[index] + adjust_amount);
/*  494 */       isNewMapping = false;
/*      */     } else {
/*  496 */       newValue = this._values[index] = put_amount;
/*  497 */       isNewMapping = true;
/*      */     } 
/*  500 */     byte previousState = this._states[index];
/*  502 */     if (isNewMapping)
/*  503 */       postInsertHook(this.consumeFreeSlot); 
/*  506 */     return newValue;
/*      */   }
/*      */   
/*      */   protected class TKeyView implements TLongSet {
/*      */     public TLongIterator iterator() {
/*  515 */       return new TLongCharHashMap.TLongCharKeyHashIterator((TPrimitiveHash)TLongCharHashMap.this);
/*      */     }
/*      */     
/*      */     public long getNoEntryValue() {
/*  521 */       return TLongCharHashMap.this.no_entry_key;
/*      */     }
/*      */     
/*      */     public int size() {
/*  527 */       return TLongCharHashMap.this._size;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  533 */       return (0 == TLongCharHashMap.this._size);
/*      */     }
/*      */     
/*      */     public boolean contains(long entry) {
/*  539 */       return TLongCharHashMap.this.contains(entry);
/*      */     }
/*      */     
/*      */     public long[] toArray() {
/*  545 */       return TLongCharHashMap.this.keys();
/*      */     }
/*      */     
/*      */     public long[] toArray(long[] dest) {
/*  551 */       return TLongCharHashMap.this.keys(dest);
/*      */     }
/*      */     
/*      */     public boolean add(long entry) {
/*  561 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean remove(long entry) {
/*  567 */       return (TLongCharHashMap.this.no_entry_value != TLongCharHashMap.this.remove(entry));
/*      */     }
/*      */     
/*      */     public boolean containsAll(Collection<?> collection) {
/*  573 */       for (Object element : collection) {
/*  574 */         if (element instanceof Long) {
/*  575 */           long ele = ((Long)element).longValue();
/*  576 */           if (!TLongCharHashMap.this.containsKey(ele))
/*  577 */             return false; 
/*      */           continue;
/*      */         } 
/*  580 */         return false;
/*      */       } 
/*  583 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(TLongCollection collection) {
/*  589 */       TLongIterator iter = collection.iterator();
/*  590 */       while (iter.hasNext()) {
/*  591 */         if (!TLongCharHashMap.this.containsKey(iter.next()))
/*  592 */           return false; 
/*      */       } 
/*  595 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(long[] array) {
/*  601 */       for (long element : array) {
/*  602 */         if (!TLongCharHashMap.this.contains(element))
/*  603 */           return false; 
/*      */       } 
/*  606 */       return true;
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends Long> collection) {
/*  616 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(TLongCollection collection) {
/*  626 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(long[] array) {
/*  636 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  643 */       boolean modified = false;
/*  644 */       TLongIterator iter = iterator();
/*  645 */       while (iter.hasNext()) {
/*  646 */         if (!collection.contains(Long.valueOf(iter.next()))) {
/*  647 */           iter.remove();
/*  648 */           modified = true;
/*      */         } 
/*      */       } 
/*  651 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(TLongCollection collection) {
/*  657 */       if (this == collection)
/*  658 */         return false; 
/*  660 */       boolean modified = false;
/*  661 */       TLongIterator iter = iterator();
/*  662 */       while (iter.hasNext()) {
/*  663 */         if (!collection.contains(iter.next())) {
/*  664 */           iter.remove();
/*  665 */           modified = true;
/*      */         } 
/*      */       } 
/*  668 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(long[] array) {
/*  674 */       boolean changed = false;
/*  675 */       Arrays.sort(array);
/*  676 */       long[] set = TLongCharHashMap.this._set;
/*  677 */       byte[] states = TLongCharHashMap.this._states;
/*  679 */       for (int i = set.length; i-- > 0;) {
/*  680 */         if (states[i] == 1 && Arrays.binarySearch(array, set[i]) < 0) {
/*  681 */           TLongCharHashMap.this.removeAt(i);
/*  682 */           changed = true;
/*      */         } 
/*      */       } 
/*  685 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(Collection<?> collection) {
/*  691 */       boolean changed = false;
/*  692 */       for (Object element : collection) {
/*  693 */         if (element instanceof Long) {
/*  694 */           long c = ((Long)element).longValue();
/*  695 */           if (remove(c))
/*  696 */             changed = true; 
/*      */         } 
/*      */       } 
/*  700 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(TLongCollection collection) {
/*  706 */       if (this == collection) {
/*  707 */         clear();
/*  708 */         return true;
/*      */       } 
/*  710 */       boolean changed = false;
/*  711 */       TLongIterator iter = collection.iterator();
/*  712 */       while (iter.hasNext()) {
/*  713 */         long element = iter.next();
/*  714 */         if (remove(element))
/*  715 */           changed = true; 
/*      */       } 
/*  718 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(long[] array) {
/*  724 */       boolean changed = false;
/*  725 */       for (int i = array.length; i-- > 0;) {
/*  726 */         if (remove(array[i]))
/*  727 */           changed = true; 
/*      */       } 
/*  730 */       return changed;
/*      */     }
/*      */     
/*      */     public void clear() {
/*  736 */       TLongCharHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public boolean forEach(TLongProcedure procedure) {
/*  742 */       return TLongCharHashMap.this.forEachKey(procedure);
/*      */     }
/*      */     
/*      */     public boolean equals(Object other) {
/*  748 */       if (!(other instanceof TLongSet))
/*  749 */         return false; 
/*  751 */       TLongSet that = (TLongSet)other;
/*  752 */       if (that.size() != size())
/*  753 */         return false; 
/*  755 */       for (int i = TLongCharHashMap.this._states.length; i-- > 0;) {
/*  756 */         if (TLongCharHashMap.this._states[i] == 1 && 
/*  757 */           !that.contains(TLongCharHashMap.this._set[i]))
/*  758 */           return false; 
/*      */       } 
/*  762 */       return true;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  768 */       int hashcode = 0;
/*  769 */       for (int i = TLongCharHashMap.this._states.length; i-- > 0;) {
/*  770 */         if (TLongCharHashMap.this._states[i] == 1)
/*  771 */           hashcode += HashFunctions.hash(TLongCharHashMap.this._set[i]); 
/*      */       } 
/*  774 */       return hashcode;
/*      */     }
/*      */     
/*      */     public String toString() {
/*  780 */       final StringBuilder buf = new StringBuilder("{");
/*  781 */       TLongCharHashMap.this.forEachKey(new TLongProcedure() {
/*      */             private boolean first = true;
/*      */             
/*      */             public boolean execute(long key) {
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
/*      */   protected class TValueView implements TCharCollection {
/*      */     public TCharIterator iterator() {
/*  807 */       return new TLongCharHashMap.TLongCharValueHashIterator((TPrimitiveHash)TLongCharHashMap.this);
/*      */     }
/*      */     
/*      */     public char getNoEntryValue() {
/*  813 */       return TLongCharHashMap.this.no_entry_value;
/*      */     }
/*      */     
/*      */     public int size() {
/*  819 */       return TLongCharHashMap.this._size;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  825 */       return (0 == TLongCharHashMap.this._size);
/*      */     }
/*      */     
/*      */     public boolean contains(char entry) {
/*  831 */       return TLongCharHashMap.this.containsValue(entry);
/*      */     }
/*      */     
/*      */     public char[] toArray() {
/*  837 */       return TLongCharHashMap.this.values();
/*      */     }
/*      */     
/*      */     public char[] toArray(char[] dest) {
/*  843 */       return TLongCharHashMap.this.values(dest);
/*      */     }
/*      */     
/*      */     public boolean add(char entry) {
/*  849 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean remove(char entry) {
/*  855 */       char[] values = TLongCharHashMap.this._values;
/*  856 */       long[] set = TLongCharHashMap.this._set;
/*  858 */       for (int i = values.length; i-- > 0;) {
/*  859 */         if (set[i] != 0L && set[i] != 2L && entry == values[i]) {
/*  860 */           TLongCharHashMap.this.removeAt(i);
/*  861 */           return true;
/*      */         } 
/*      */       } 
/*  864 */       return false;
/*      */     }
/*      */     
/*      */     public boolean containsAll(Collection<?> collection) {
/*  870 */       for (Object element : collection) {
/*  871 */         if (element instanceof Character) {
/*  872 */           char ele = ((Character)element).charValue();
/*  873 */           if (!TLongCharHashMap.this.containsValue(ele))
/*  874 */             return false; 
/*      */           continue;
/*      */         } 
/*  877 */         return false;
/*      */       } 
/*  880 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(TCharCollection collection) {
/*  886 */       TCharIterator iter = collection.iterator();
/*  887 */       while (iter.hasNext()) {
/*  888 */         if (!TLongCharHashMap.this.containsValue(iter.next()))
/*  889 */           return false; 
/*      */       } 
/*  892 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(char[] array) {
/*  898 */       for (char element : array) {
/*  899 */         if (!TLongCharHashMap.this.containsValue(element))
/*  900 */           return false; 
/*      */       } 
/*  903 */       return true;
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends Character> collection) {
/*  909 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(TCharCollection collection) {
/*  915 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(char[] array) {
/*  921 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  928 */       boolean modified = false;
/*  929 */       TCharIterator iter = iterator();
/*  930 */       while (iter.hasNext()) {
/*  931 */         if (!collection.contains(Character.valueOf(iter.next()))) {
/*  932 */           iter.remove();
/*  933 */           modified = true;
/*      */         } 
/*      */       } 
/*  936 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(TCharCollection collection) {
/*  942 */       if (this == collection)
/*  943 */         return false; 
/*  945 */       boolean modified = false;
/*  946 */       TCharIterator iter = iterator();
/*  947 */       while (iter.hasNext()) {
/*  948 */         if (!collection.contains(iter.next())) {
/*  949 */           iter.remove();
/*  950 */           modified = true;
/*      */         } 
/*      */       } 
/*  953 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(char[] array) {
/*  959 */       boolean changed = false;
/*  960 */       Arrays.sort(array);
/*  961 */       char[] values = TLongCharHashMap.this._values;
/*  962 */       byte[] states = TLongCharHashMap.this._states;
/*  964 */       for (int i = values.length; i-- > 0;) {
/*  965 */         if (states[i] == 1 && Arrays.binarySearch(array, values[i]) < 0) {
/*  966 */           TLongCharHashMap.this.removeAt(i);
/*  967 */           changed = true;
/*      */         } 
/*      */       } 
/*  970 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(Collection<?> collection) {
/*  976 */       boolean changed = false;
/*  977 */       for (Object element : collection) {
/*  978 */         if (element instanceof Character) {
/*  979 */           char c = ((Character)element).charValue();
/*  980 */           if (remove(c))
/*  981 */             changed = true; 
/*      */         } 
/*      */       } 
/*  985 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(TCharCollection collection) {
/*  991 */       if (this == collection) {
/*  992 */         clear();
/*  993 */         return true;
/*      */       } 
/*  995 */       boolean changed = false;
/*  996 */       TCharIterator iter = collection.iterator();
/*  997 */       while (iter.hasNext()) {
/*  998 */         char element = iter.next();
/*  999 */         if (remove(element))
/* 1000 */           changed = true; 
/*      */       } 
/* 1003 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(char[] array) {
/* 1009 */       boolean changed = false;
/* 1010 */       for (int i = array.length; i-- > 0;) {
/* 1011 */         if (remove(array[i]))
/* 1012 */           changed = true; 
/*      */       } 
/* 1015 */       return changed;
/*      */     }
/*      */     
/*      */     public void clear() {
/* 1021 */       TLongCharHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public boolean forEach(TCharProcedure procedure) {
/* 1027 */       return TLongCharHashMap.this.forEachValue(procedure);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1034 */       final StringBuilder buf = new StringBuilder("{");
/* 1035 */       TLongCharHashMap.this.forEachValue(new TCharProcedure() {
/*      */             private boolean first = true;
/*      */             
/*      */             public boolean execute(char value) {
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
/*      */   class TLongCharKeyHashIterator extends THashPrimitiveIterator implements TLongIterator {
/*      */     TLongCharKeyHashIterator(TPrimitiveHash hash) {
/* 1063 */       super(hash);
/*      */     }
/*      */     
/*      */     public long next() {
/* 1068 */       moveToNextIndex();
/* 1069 */       return TLongCharHashMap.this._set[this._index];
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1074 */       if (this._expectedSize != this._hash.size())
/* 1075 */         throw new ConcurrentModificationException(); 
/*      */       try {
/* 1080 */         this._hash.tempDisableAutoCompaction();
/* 1081 */         TLongCharHashMap.this.removeAt(this._index);
/*      */       } finally {
/* 1084 */         this._hash.reenableAutoCompaction(false);
/*      */       } 
/* 1087 */       this._expectedSize--;
/*      */     }
/*      */   }
/*      */   
/*      */   class TLongCharValueHashIterator extends THashPrimitiveIterator implements TCharIterator {
/*      */     TLongCharValueHashIterator(TPrimitiveHash hash) {
/* 1101 */       super(hash);
/*      */     }
/*      */     
/*      */     public char next() {
/* 1106 */       moveToNextIndex();
/* 1107 */       return TLongCharHashMap.this._values[this._index];
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1112 */       if (this._expectedSize != this._hash.size())
/* 1113 */         throw new ConcurrentModificationException(); 
/*      */       try {
/* 1118 */         this._hash.tempDisableAutoCompaction();
/* 1119 */         TLongCharHashMap.this.removeAt(this._index);
/*      */       } finally {
/* 1122 */         this._hash.reenableAutoCompaction(false);
/*      */       } 
/* 1125 */       this._expectedSize--;
/*      */     }
/*      */   }
/*      */   
/*      */   class TLongCharHashIterator extends THashPrimitiveIterator implements TLongCharIterator {
/*      */     TLongCharHashIterator(TLongCharHashMap map) {
/* 1138 */       super((TPrimitiveHash)map);
/*      */     }
/*      */     
/*      */     public void advance() {
/* 1143 */       moveToNextIndex();
/*      */     }
/*      */     
/*      */     public long key() {
/* 1148 */       return TLongCharHashMap.this._set[this._index];
/*      */     }
/*      */     
/*      */     public char value() {
/* 1153 */       return TLongCharHashMap.this._values[this._index];
/*      */     }
/*      */     
/*      */     public char setValue(char val) {
/* 1158 */       char old = value();
/* 1159 */       TLongCharHashMap.this._values[this._index] = val;
/* 1160 */       return old;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1165 */       if (this._expectedSize != this._hash.size())
/* 1166 */         throw new ConcurrentModificationException(); 
/*      */       try {
/* 1170 */         this._hash.tempDisableAutoCompaction();
/* 1171 */         TLongCharHashMap.this.removeAt(this._index);
/*      */       } finally {
/* 1174 */         this._hash.reenableAutoCompaction(false);
/*      */       } 
/* 1176 */       this._expectedSize--;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean equals(Object other) {
/* 1184 */     if (!(other instanceof TLongCharMap))
/* 1185 */       return false; 
/* 1187 */     TLongCharMap that = (TLongCharMap)other;
/* 1188 */     if (that.size() != size())
/* 1189 */       return false; 
/* 1191 */     char[] values = this._values;
/* 1192 */     byte[] states = this._states;
/* 1193 */     char this_no_entry_value = getNoEntryValue();
/* 1194 */     char that_no_entry_value = that.getNoEntryValue();
/* 1195 */     for (int i = values.length; i-- > 0;) {
/* 1196 */       if (states[i] == 1) {
/* 1197 */         long key = this._set[i];
/* 1198 */         char that_value = that.get(key);
/* 1199 */         char this_value = values[i];
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
/* 1230 */     forEachEntry(new TLongCharProcedure() {
/*      */           private boolean first = true;
/*      */           
/*      */           public boolean execute(long key, char value) {
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
/* 1261 */         out.writeLong(this._set[i]);
/* 1262 */         out.writeChar(this._values[i]);
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
/* 1282 */       long key = in.readLong();
/* 1283 */       char val = in.readChar();
/* 1284 */       put(key, val);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\hash\TLongCharHashMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */