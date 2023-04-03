/*      */ package gnu.trove.map.hash;
/*      */ 
/*      */ import gnu.trove.TByteCollection;
/*      */ import gnu.trove.function.TObjectFunction;
/*      */ import gnu.trove.impl.Constants;
/*      */ import gnu.trove.impl.HashFunctions;
/*      */ import gnu.trove.impl.hash.TByteHash;
/*      */ import gnu.trove.impl.hash.THashPrimitiveIterator;
/*      */ import gnu.trove.impl.hash.TPrimitiveHash;
/*      */ import gnu.trove.iterator.TByteIterator;
/*      */ import gnu.trove.iterator.TByteObjectIterator;
/*      */ import gnu.trove.map.TByteObjectMap;
/*      */ import gnu.trove.procedure.TByteObjectProcedure;
/*      */ import gnu.trove.procedure.TByteProcedure;
/*      */ import gnu.trove.procedure.TObjectProcedure;
/*      */ import gnu.trove.set.TByteSet;
/*      */ import java.io.Externalizable;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInput;
/*      */ import java.io.ObjectOutput;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ 
/*      */ public class TByteObjectHashMap<V> extends TByteHash implements TByteObjectMap<V>, Externalizable {
/*      */   static final long serialVersionUID = 1L;
/*      */   
/*   60 */   private final TByteObjectProcedure<V> PUT_ALL_PROC = new TByteObjectProcedure<V>() {
/*      */       public boolean execute(byte key, V value) {
/*   62 */         TByteObjectHashMap.this.put(key, value);
/*   63 */         return true;
/*      */       }
/*      */     };
/*      */   
/*      */   protected transient V[] _values;
/*      */   
/*      */   protected byte no_entry_key;
/*      */   
/*      */   public TByteObjectHashMap(int initialCapacity) {
/*   91 */     super(initialCapacity);
/*   92 */     this.no_entry_key = Constants.DEFAULT_BYTE_NO_ENTRY_VALUE;
/*      */   }
/*      */   
/*      */   public TByteObjectHashMap(int initialCapacity, float loadFactor) {
/*  105 */     super(initialCapacity, loadFactor);
/*  106 */     this.no_entry_key = Constants.DEFAULT_BYTE_NO_ENTRY_VALUE;
/*      */   }
/*      */   
/*      */   public TByteObjectHashMap(int initialCapacity, float loadFactor, byte noEntryKey) {
/*  120 */     super(initialCapacity, loadFactor);
/*  121 */     this.no_entry_key = noEntryKey;
/*      */   }
/*      */   
/*      */   public TByteObjectHashMap(TByteObjectMap<? extends V> map) {
/*  132 */     this(map.size(), 0.5F, map.getNoEntryKey());
/*  133 */     putAll(map);
/*      */   }
/*      */   
/*      */   protected int setUp(int initialCapacity) {
/*  142 */     int capacity = super.setUp(initialCapacity);
/*  143 */     this._values = (V[])new Object[capacity];
/*  144 */     return capacity;
/*      */   }
/*      */   
/*      */   protected void rehash(int newCapacity) {
/*  151 */     int oldCapacity = this._set.length;
/*  153 */     byte[] oldKeys = this._set;
/*  154 */     V[] oldVals = this._values;
/*  155 */     byte[] oldStates = this._states;
/*  157 */     this._set = new byte[newCapacity];
/*  158 */     this._values = (V[])new Object[newCapacity];
/*  159 */     this._states = new byte[newCapacity];
/*  161 */     for (int i = oldCapacity; i-- > 0;) {
/*  162 */       if (oldStates[i] == 1) {
/*  163 */         byte o = oldKeys[i];
/*  164 */         int index = insertKey(o);
/*  165 */         this._values[index] = oldVals[i];
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public byte getNoEntryKey() {
/*  175 */     return this.no_entry_key;
/*      */   }
/*      */   
/*      */   public boolean containsKey(byte key) {
/*  181 */     return contains(key);
/*      */   }
/*      */   
/*      */   public boolean containsValue(Object val) {
/*  187 */     byte[] states = this._states;
/*  188 */     V[] vals = this._values;
/*  192 */     if (null == val) {
/*  193 */       for (int i = vals.length; i-- > 0;) {
/*  194 */         if (states[i] == 1 && null == vals[i])
/*  195 */           return true; 
/*      */       } 
/*      */     } else {
/*  199 */       for (int i = vals.length; i-- > 0;) {
/*  200 */         if (states[i] == 1 && (val == vals[i] || val.equals(vals[i])))
/*  202 */           return true; 
/*      */       } 
/*      */     } 
/*  206 */     return false;
/*      */   }
/*      */   
/*      */   public V get(byte key) {
/*  212 */     int index = index(key);
/*  213 */     return (index < 0) ? null : this._values[index];
/*      */   }
/*      */   
/*      */   public V put(byte key, V value) {
/*  221 */     int index = insertKey(key);
/*  222 */     return doPut(value, index);
/*      */   }
/*      */   
/*      */   public V putIfAbsent(byte key, V value) {
/*  228 */     int index = insertKey(key);
/*  229 */     if (index < 0)
/*  230 */       return this._values[-index - 1]; 
/*  231 */     return doPut(value, index);
/*      */   }
/*      */   
/*      */   private V doPut(V value, int index) {
/*  237 */     V previous = null;
/*  238 */     boolean isNewMapping = true;
/*  239 */     if (index < 0) {
/*  240 */       index = -index - 1;
/*  241 */       previous = this._values[index];
/*  242 */       isNewMapping = false;
/*      */     } 
/*  245 */     this._values[index] = value;
/*  247 */     if (isNewMapping)
/*  248 */       postInsertHook(this.consumeFreeSlot); 
/*  251 */     return previous;
/*      */   }
/*      */   
/*      */   public V remove(byte key) {
/*  257 */     V prev = null;
/*  258 */     int index = index(key);
/*  259 */     if (index >= 0) {
/*  260 */       prev = this._values[index];
/*  261 */       removeAt(index);
/*      */     } 
/*  263 */     return prev;
/*      */   }
/*      */   
/*      */   protected void removeAt(int index) {
/*  269 */     this._values[index] = null;
/*  270 */     super.removeAt(index);
/*      */   }
/*      */   
/*      */   public void putAll(Map<? extends Byte, ? extends V> map) {
/*  278 */     Set<? extends Map.Entry<? extends Byte, ? extends V>> set = map.entrySet();
/*  279 */     for (Map.Entry<? extends Byte, ? extends V> entry : set)
/*  280 */       put(((Byte)entry.getKey()).byteValue(), entry.getValue()); 
/*      */   }
/*      */   
/*      */   public void putAll(TByteObjectMap<? extends V> map) {
/*  287 */     map.forEachEntry(this.PUT_ALL_PROC);
/*      */   }
/*      */   
/*      */   public void clear() {
/*  293 */     super.clear();
/*  294 */     Arrays.fill(this._set, 0, this._set.length, this.no_entry_key);
/*  295 */     Arrays.fill(this._states, 0, this._states.length, (byte)0);
/*  296 */     Arrays.fill((Object[])this._values, 0, this._values.length, (Object)null);
/*      */   }
/*      */   
/*      */   public TByteSet keySet() {
/*  304 */     return new KeyView();
/*      */   }
/*      */   
/*      */   public byte[] keys() {
/*  311 */     byte[] keys = new byte[size()];
/*  312 */     byte[] k = this._set;
/*  313 */     byte[] states = this._states;
/*  315 */     for (int i = k.length, j = 0; i-- > 0;) {
/*  316 */       if (states[i] == 1)
/*  317 */         keys[j++] = k[i]; 
/*      */     } 
/*  320 */     return keys;
/*      */   }
/*      */   
/*      */   public byte[] keys(byte[] dest) {
/*  327 */     if (dest.length < this._size)
/*  328 */       dest = new byte[this._size]; 
/*  331 */     byte[] k = this._set;
/*  332 */     byte[] states = this._states;
/*  334 */     for (int i = k.length, j = 0; i-- > 0;) {
/*  335 */       if (states[i] == 1)
/*  336 */         dest[j++] = k[i]; 
/*      */     } 
/*  339 */     return dest;
/*      */   }
/*      */   
/*      */   public Collection<V> valueCollection() {
/*  345 */     return new ValueView();
/*      */   }
/*      */   
/*      */   public Object[] values() {
/*  352 */     Object[] vals = new Object[size()];
/*  353 */     V[] v = this._values;
/*  354 */     byte[] states = this._states;
/*  356 */     for (int i = v.length, j = 0; i-- > 0;) {
/*  357 */       if (states[i] == 1)
/*  358 */         vals[j++] = v[i]; 
/*      */     } 
/*  361 */     return vals;
/*      */   }
/*      */   
/*      */   public V[] values(V[] dest) {
/*  368 */     if (dest.length < this._size)
/*  369 */       dest = (V[])Array.newInstance(dest.getClass().getComponentType(), this._size); 
/*  373 */     V[] v = this._values;
/*  374 */     byte[] states = this._states;
/*  376 */     for (int i = v.length, j = 0; i-- > 0;) {
/*  377 */       if (states[i] == 1)
/*  378 */         dest[j++] = v[i]; 
/*      */     } 
/*  381 */     return dest;
/*      */   }
/*      */   
/*      */   public TByteObjectIterator<V> iterator() {
/*  387 */     return new TByteObjectHashIterator<V>(this);
/*      */   }
/*      */   
/*      */   public boolean forEachKey(TByteProcedure procedure) {
/*  393 */     return forEach(procedure);
/*      */   }
/*      */   
/*      */   public boolean forEachValue(TObjectProcedure<? super V> procedure) {
/*  399 */     byte[] states = this._states;
/*  400 */     V[] values = this._values;
/*  401 */     for (int i = values.length; i-- > 0;) {
/*  402 */       if (states[i] == 1 && !procedure.execute(values[i]))
/*  403 */         return false; 
/*      */     } 
/*  406 */     return true;
/*      */   }
/*      */   
/*      */   public boolean forEachEntry(TByteObjectProcedure<? super V> procedure) {
/*  413 */     byte[] states = this._states;
/*  414 */     byte[] keys = this._set;
/*  415 */     V[] values = this._values;
/*  416 */     for (int i = keys.length; i-- > 0;) {
/*  417 */       if (states[i] == 1 && !procedure.execute(keys[i], values[i]))
/*  418 */         return false; 
/*      */     } 
/*  421 */     return true;
/*      */   }
/*      */   
/*      */   public boolean retainEntries(TByteObjectProcedure<? super V> procedure) {
/*  428 */     boolean modified = false;
/*  429 */     byte[] states = this._states;
/*  430 */     byte[] keys = this._set;
/*  431 */     V[] values = this._values;
/*  434 */     tempDisableAutoCompaction();
/*      */     try {
/*  436 */       for (int i = keys.length; i-- > 0;) {
/*  437 */         if (states[i] == 1 && !procedure.execute(keys[i], values[i])) {
/*  438 */           removeAt(i);
/*  439 */           modified = true;
/*      */         } 
/*      */       } 
/*      */     } finally {
/*  444 */       reenableAutoCompaction(true);
/*      */     } 
/*  447 */     return modified;
/*      */   }
/*      */   
/*      */   public void transformValues(TObjectFunction<V, V> function) {
/*  453 */     byte[] states = this._states;
/*  454 */     V[] values = this._values;
/*  455 */     for (int i = values.length; i-- > 0;) {
/*  456 */       if (states[i] == 1)
/*  457 */         values[i] = (V)function.execute(values[i]); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean equals(Object other) {
/*  467 */     if (!(other instanceof TByteObjectMap))
/*  468 */       return false; 
/*  470 */     TByteObjectMap that = (TByteObjectMap)other;
/*  471 */     if (that.size() != size())
/*  472 */       return false; 
/*      */     try {
/*  475 */       TByteObjectIterator<V> iter = iterator();
/*  476 */       while (iter.hasNext()) {
/*  477 */         iter.advance();
/*  478 */         byte key = iter.key();
/*  479 */         Object value = iter.value();
/*  480 */         if (value == null) {
/*  481 */           if (that.get(key) != null || !that.containsKey(key))
/*  482 */             return false; 
/*      */           continue;
/*      */         } 
/*  485 */         if (!value.equals(that.get(key)))
/*  486 */           return false; 
/*      */       } 
/*  490 */     } catch (ClassCastException ex) {}
/*  493 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  499 */     int hashcode = 0;
/*  500 */     V[] values = this._values;
/*  501 */     byte[] states = this._states;
/*  502 */     for (int i = values.length; i-- > 0;) {
/*  503 */       if (states[i] == 1)
/*  504 */         hashcode += HashFunctions.hash(this._set[i]) ^ ((values[i] == null) ? 0 : values[i].hashCode()); 
/*      */     } 
/*  508 */     return hashcode;
/*      */   }
/*      */   
/*      */   class KeyView implements TByteSet {
/*      */     public byte getNoEntryValue() {
/*  516 */       return TByteObjectHashMap.this.no_entry_key;
/*      */     }
/*      */     
/*      */     public int size() {
/*  521 */       return TByteObjectHashMap.this._size;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  526 */       return (TByteObjectHashMap.this._size == 0);
/*      */     }
/*      */     
/*      */     public boolean contains(byte entry) {
/*  531 */       return TByteObjectHashMap.this.containsKey(entry);
/*      */     }
/*      */     
/*      */     public TByteIterator iterator() {
/*  536 */       return new TByteHashIterator(TByteObjectHashMap.this);
/*      */     }
/*      */     
/*      */     public byte[] toArray() {
/*  541 */       return TByteObjectHashMap.this.keys();
/*      */     }
/*      */     
/*      */     public byte[] toArray(byte[] dest) {
/*  546 */       return TByteObjectHashMap.this.keys(dest);
/*      */     }
/*      */     
/*      */     public boolean add(byte entry) {
/*  551 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean remove(byte entry) {
/*  556 */       return (null != TByteObjectHashMap.this.remove(entry));
/*      */     }
/*      */     
/*      */     public boolean containsAll(Collection<?> collection) {
/*  561 */       for (Object element : collection) {
/*  562 */         if (!TByteObjectHashMap.this.containsKey(((Byte)element).byteValue()))
/*  565 */           return false; 
/*      */       } 
/*  568 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(TByteCollection collection) {
/*  573 */       if (collection == this)
/*  574 */         return true; 
/*  576 */       TByteIterator iter = collection.iterator();
/*  577 */       while (iter.hasNext()) {
/*  578 */         if (!TByteObjectHashMap.this.containsKey(iter.next()))
/*  579 */           return false; 
/*      */       } 
/*  582 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(byte[] array) {
/*  587 */       for (byte element : array) {
/*  588 */         if (!TByteObjectHashMap.this.containsKey(element))
/*  589 */           return false; 
/*      */       } 
/*  592 */       return true;
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends Byte> collection) {
/*  597 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(TByteCollection collection) {
/*  602 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(byte[] array) {
/*  607 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  612 */       boolean modified = false;
/*  613 */       TByteIterator iter = iterator();
/*  614 */       while (iter.hasNext()) {
/*  616 */         if (!collection.contains(Byte.valueOf(iter.next()))) {
/*  617 */           iter.remove();
/*  618 */           modified = true;
/*      */         } 
/*      */       } 
/*  621 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(TByteCollection collection) {
/*  626 */       if (this == collection)
/*  627 */         return false; 
/*  629 */       boolean modified = false;
/*  630 */       TByteIterator iter = iterator();
/*  631 */       while (iter.hasNext()) {
/*  632 */         if (!collection.contains(iter.next())) {
/*  633 */           iter.remove();
/*  634 */           modified = true;
/*      */         } 
/*      */       } 
/*  637 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(byte[] array) {
/*  642 */       boolean changed = false;
/*  643 */       Arrays.sort(array);
/*  644 */       byte[] set = TByteObjectHashMap.this._set;
/*  645 */       byte[] states = TByteObjectHashMap.this._states;
/*  647 */       for (int i = set.length; i-- > 0;) {
/*  648 */         if (states[i] == 1 && Arrays.binarySearch(array, set[i]) < 0) {
/*  649 */           TByteObjectHashMap.this.removeAt(i);
/*  650 */           changed = true;
/*      */         } 
/*      */       } 
/*  653 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(Collection<?> collection) {
/*  658 */       boolean changed = false;
/*  659 */       for (Object element : collection) {
/*  660 */         if (element instanceof Byte) {
/*  661 */           byte c = ((Byte)element).byteValue();
/*  662 */           if (remove(c))
/*  663 */             changed = true; 
/*      */         } 
/*      */       } 
/*  667 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(TByteCollection collection) {
/*  672 */       if (collection == this) {
/*  673 */         clear();
/*  674 */         return true;
/*      */       } 
/*  676 */       boolean changed = false;
/*  677 */       TByteIterator iter = collection.iterator();
/*  678 */       while (iter.hasNext()) {
/*  679 */         byte element = iter.next();
/*  680 */         if (remove(element))
/*  681 */           changed = true; 
/*      */       } 
/*  684 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(byte[] array) {
/*  689 */       boolean changed = false;
/*  690 */       for (int i = array.length; i-- > 0;) {
/*  691 */         if (remove(array[i]))
/*  692 */           changed = true; 
/*      */       } 
/*  695 */       return changed;
/*      */     }
/*      */     
/*      */     public void clear() {
/*  700 */       TByteObjectHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public boolean forEach(TByteProcedure procedure) {
/*  705 */       return TByteObjectHashMap.this.forEachKey(procedure);
/*      */     }
/*      */     
/*      */     public boolean equals(Object other) {
/*  710 */       if (!(other instanceof TByteSet))
/*  711 */         return false; 
/*  713 */       TByteSet that = (TByteSet)other;
/*  714 */       if (that.size() != size())
/*  715 */         return false; 
/*  717 */       for (int i = TByteObjectHashMap.this._states.length; i-- > 0;) {
/*  718 */         if (TByteObjectHashMap.this._states[i] == 1 && 
/*  719 */           !that.contains(TByteObjectHashMap.this._set[i]))
/*  720 */           return false; 
/*      */       } 
/*  724 */       return true;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  729 */       int hashcode = 0;
/*  730 */       for (int i = TByteObjectHashMap.this._states.length; i-- > 0;) {
/*  731 */         if (TByteObjectHashMap.this._states[i] == 1)
/*  732 */           hashcode += HashFunctions.hash(TByteObjectHashMap.this._set[i]); 
/*      */       } 
/*  735 */       return hashcode;
/*      */     }
/*      */     
/*      */     public String toString() {
/*  740 */       StringBuilder buf = new StringBuilder("{");
/*  741 */       boolean first = true;
/*  742 */       for (int i = TByteObjectHashMap.this._states.length; i-- > 0;) {
/*  743 */         if (TByteObjectHashMap.this._states[i] == 1) {
/*  744 */           if (first) {
/*  744 */             first = false;
/*      */           } else {
/*  745 */             buf.append(",");
/*      */           } 
/*  746 */           buf.append(TByteObjectHashMap.this._set[i]);
/*      */         } 
/*      */       } 
/*  749 */       return buf.toString();
/*      */     }
/*      */     
/*      */     class TByteHashIterator extends THashPrimitiveIterator implements TByteIterator {
/*      */       private final TByteHash _hash;
/*      */       
/*      */       public TByteHashIterator(TByteHash hash) {
/*  760 */         super((TPrimitiveHash)hash);
/*  761 */         this._hash = hash;
/*      */       }
/*      */       
/*      */       public byte next() {
/*  766 */         moveToNextIndex();
/*  767 */         return this._hash._set[this._index];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected class ValueView extends MapBackedView<V> {
/*      */     public Iterator<V> iterator() {
/*  778 */       return new TByteObjectValueHashIterator(TByteObjectHashMap.this) {
/*      */           protected V objectAtIndex(int index) {
/*  780 */             return TByteObjectHashMap.this._values[index];
/*      */           }
/*      */         };
/*      */     }
/*      */     
/*      */     public boolean containsElement(V value) {
/*  786 */       return TByteObjectHashMap.this.containsValue(value);
/*      */     }
/*      */     
/*      */     public boolean removeElement(V value) {
/*  790 */       V[] values = TByteObjectHashMap.this._values;
/*  791 */       byte[] states = TByteObjectHashMap.this._states;
/*  793 */       for (int i = values.length; i-- > 0;) {
/*  794 */         if (states[i] == 1 && (
/*  795 */           value == values[i] || (null != values[i] && values[i].equals(value)))) {
/*  797 */           TByteObjectHashMap.this.removeAt(i);
/*  798 */           return true;
/*      */         } 
/*      */       } 
/*  802 */       return false;
/*      */     }
/*      */     
/*      */     class TByteObjectValueHashIterator extends THashPrimitiveIterator implements Iterator<V> {
/*      */       protected final TByteObjectHashMap _map;
/*      */       
/*      */       public TByteObjectValueHashIterator(TByteObjectHashMap map) {
/*  811 */         super((TPrimitiveHash)map);
/*  812 */         this._map = map;
/*      */       }
/*      */       
/*      */       protected V objectAtIndex(int index) {
/*  817 */         byte[] states = TByteObjectHashMap.this._states;
/*  818 */         Object value = this._map._values[index];
/*  819 */         if (states[index] != 1)
/*  820 */           return null; 
/*  822 */         return (V)value;
/*      */       }
/*      */       
/*      */       public V next() {
/*  828 */         moveToNextIndex();
/*  829 */         return this._map._values[this._index];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private abstract class MapBackedView<E> extends AbstractSet<E> implements Set<E>, Iterable<E> {
/*      */     private MapBackedView() {}
/*      */     
/*      */     public boolean contains(Object key) {
/*  846 */       return containsElement((E)key);
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/*  851 */       return removeElement((E)o);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  855 */       TByteObjectHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public boolean add(E obj) {
/*  859 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public int size() {
/*  863 */       return TByteObjectHashMap.this.size();
/*      */     }
/*      */     
/*      */     public Object[] toArray() {
/*  867 */       Object[] result = new Object[size()];
/*  868 */       Iterator<E> e = iterator();
/*  869 */       for (int i = 0; e.hasNext(); i++)
/*  870 */         result[i] = e.next(); 
/*  872 */       return result;
/*      */     }
/*      */     
/*      */     public <T> T[] toArray(T[] a) {
/*  877 */       int size = size();
/*  878 */       if (a.length < size)
/*  879 */         a = (T[])Array.newInstance(a.getClass().getComponentType(), size); 
/*  883 */       Iterator<E> it = iterator();
/*  884 */       T[] arrayOfT = a;
/*  885 */       for (int i = 0; i < size; i++)
/*  886 */         arrayOfT[i] = (T)it.next(); 
/*  889 */       if (a.length > size)
/*  890 */         a[size] = null; 
/*  893 */       return a;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  897 */       return TByteObjectHashMap.this.isEmpty();
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends E> collection) {
/*  901 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  906 */       boolean changed = false;
/*  907 */       Iterator<E> i = iterator();
/*  908 */       while (i.hasNext()) {
/*  909 */         if (!collection.contains(i.next())) {
/*  910 */           i.remove();
/*  911 */           changed = true;
/*      */         } 
/*      */       } 
/*  914 */       return changed;
/*      */     }
/*      */     
/*      */     public abstract Iterator<E> iterator();
/*      */     
/*      */     public abstract boolean removeElement(E param1E);
/*      */     
/*      */     public abstract boolean containsElement(E param1E);
/*      */   }
/*      */   
/*      */   class TByteObjectHashIterator<V> extends THashPrimitiveIterator implements TByteObjectIterator<V> {
/*      */     private final TByteObjectHashMap<V> _map;
/*      */     
/*      */     public TByteObjectHashIterator(TByteObjectHashMap<V> map) {
/*  931 */       super((TPrimitiveHash)map);
/*  932 */       this._map = map;
/*      */     }
/*      */     
/*      */     public void advance() {
/*  937 */       moveToNextIndex();
/*      */     }
/*      */     
/*      */     public byte key() {
/*  942 */       return this._map._set[this._index];
/*      */     }
/*      */     
/*      */     public V value() {
/*  947 */       return this._map._values[this._index];
/*      */     }
/*      */     
/*      */     public V setValue(V val) {
/*  952 */       V old = value();
/*  953 */       this._map._values[this._index] = val;
/*  954 */       return old;
/*      */     }
/*      */   }
/*      */   
/*      */   public void writeExternal(ObjectOutput out) throws IOException {
/*  961 */     out.writeByte(0);
/*  964 */     super.writeExternal(out);
/*  967 */     out.writeByte(this.no_entry_key);
/*  970 */     out.writeInt(this._size);
/*  973 */     for (int i = this._states.length; i-- > 0;) {
/*  974 */       if (this._states[i] == 1) {
/*  975 */         out.writeByte(this._set[i]);
/*  976 */         out.writeObject(this._values[i]);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*  987 */     in.readByte();
/*  990 */     super.readExternal(in);
/*  993 */     this.no_entry_key = in.readByte();
/*  996 */     int size = in.readInt();
/*  997 */     setUp(size);
/* 1000 */     while (size-- > 0) {
/* 1001 */       byte key = in.readByte();
/* 1002 */       V val = (V)in.readObject();
/* 1003 */       put(key, val);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1009 */     final StringBuilder buf = new StringBuilder("{");
/* 1010 */     forEachEntry(new TByteObjectProcedure<V>() {
/*      */           private boolean first = true;
/*      */           
/*      */           public boolean execute(byte key, Object value) {
/* 1013 */             if (this.first) {
/* 1013 */               this.first = false;
/*      */             } else {
/* 1014 */               buf.append(",");
/*      */             } 
/* 1016 */             buf.append(key);
/* 1017 */             buf.append("=");
/* 1018 */             buf.append(value);
/* 1019 */             return true;
/*      */           }
/*      */         });
/* 1022 */     buf.append("}");
/* 1023 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public TByteObjectHashMap() {}
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\hash\TByteObjectHashMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */