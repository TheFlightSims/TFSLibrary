/*      */ package gnu.trove.map.hash;
/*      */ 
/*      */ import gnu.trove.TShortCollection;
/*      */ import gnu.trove.function.TObjectFunction;
/*      */ import gnu.trove.impl.Constants;
/*      */ import gnu.trove.impl.HashFunctions;
/*      */ import gnu.trove.impl.hash.THashPrimitiveIterator;
/*      */ import gnu.trove.impl.hash.TPrimitiveHash;
/*      */ import gnu.trove.impl.hash.TShortHash;
/*      */ import gnu.trove.iterator.TShortIterator;
/*      */ import gnu.trove.iterator.TShortObjectIterator;
/*      */ import gnu.trove.map.TShortObjectMap;
/*      */ import gnu.trove.procedure.TObjectProcedure;
/*      */ import gnu.trove.procedure.TShortObjectProcedure;
/*      */ import gnu.trove.procedure.TShortProcedure;
/*      */ import gnu.trove.set.TShortSet;
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
/*      */ public class TShortObjectHashMap<V> extends TShortHash implements TShortObjectMap<V>, Externalizable {
/*      */   static final long serialVersionUID = 1L;
/*      */   
/*   60 */   private final TShortObjectProcedure<V> PUT_ALL_PROC = new TShortObjectProcedure<V>() {
/*      */       public boolean execute(short key, V value) {
/*   62 */         TShortObjectHashMap.this.put(key, value);
/*   63 */         return true;
/*      */       }
/*      */     };
/*      */   
/*      */   protected transient V[] _values;
/*      */   
/*      */   protected short no_entry_key;
/*      */   
/*      */   public TShortObjectHashMap(int initialCapacity) {
/*   91 */     super(initialCapacity);
/*   92 */     this.no_entry_key = Constants.DEFAULT_SHORT_NO_ENTRY_VALUE;
/*      */   }
/*      */   
/*      */   public TShortObjectHashMap(int initialCapacity, float loadFactor) {
/*  105 */     super(initialCapacity, loadFactor);
/*  106 */     this.no_entry_key = Constants.DEFAULT_SHORT_NO_ENTRY_VALUE;
/*      */   }
/*      */   
/*      */   public TShortObjectHashMap(int initialCapacity, float loadFactor, short noEntryKey) {
/*  120 */     super(initialCapacity, loadFactor);
/*  121 */     this.no_entry_key = noEntryKey;
/*      */   }
/*      */   
/*      */   public TShortObjectHashMap(TShortObjectMap<? extends V> map) {
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
/*  153 */     short[] oldKeys = this._set;
/*  154 */     V[] oldVals = this._values;
/*  155 */     byte[] oldStates = this._states;
/*  157 */     this._set = new short[newCapacity];
/*  158 */     this._values = (V[])new Object[newCapacity];
/*  159 */     this._states = new byte[newCapacity];
/*  161 */     for (int i = oldCapacity; i-- > 0;) {
/*  162 */       if (oldStates[i] == 1) {
/*  163 */         short o = oldKeys[i];
/*  164 */         int index = insertKey(o);
/*  165 */         this._values[index] = oldVals[i];
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public short getNoEntryKey() {
/*  175 */     return this.no_entry_key;
/*      */   }
/*      */   
/*      */   public boolean containsKey(short key) {
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
/*      */   public V get(short key) {
/*  212 */     int index = index(key);
/*  213 */     return (index < 0) ? null : this._values[index];
/*      */   }
/*      */   
/*      */   public V put(short key, V value) {
/*  221 */     int index = insertKey(key);
/*  222 */     return doPut(value, index);
/*      */   }
/*      */   
/*      */   public V putIfAbsent(short key, V value) {
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
/*      */   public V remove(short key) {
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
/*      */   public void putAll(Map<? extends Short, ? extends V> map) {
/*  278 */     Set<? extends Map.Entry<? extends Short, ? extends V>> set = map.entrySet();
/*  279 */     for (Map.Entry<? extends Short, ? extends V> entry : set)
/*  280 */       put(((Short)entry.getKey()).shortValue(), entry.getValue()); 
/*      */   }
/*      */   
/*      */   public void putAll(TShortObjectMap<? extends V> map) {
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
/*      */   public TShortSet keySet() {
/*  304 */     return new KeyView();
/*      */   }
/*      */   
/*      */   public short[] keys() {
/*  311 */     short[] keys = new short[size()];
/*  312 */     short[] k = this._set;
/*  313 */     byte[] states = this._states;
/*  315 */     for (int i = k.length, j = 0; i-- > 0;) {
/*  316 */       if (states[i] == 1)
/*  317 */         keys[j++] = k[i]; 
/*      */     } 
/*  320 */     return keys;
/*      */   }
/*      */   
/*      */   public short[] keys(short[] dest) {
/*  327 */     if (dest.length < this._size)
/*  328 */       dest = new short[this._size]; 
/*  331 */     short[] k = this._set;
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
/*      */   public TShortObjectIterator<V> iterator() {
/*  387 */     return new TShortObjectHashIterator<V>(this);
/*      */   }
/*      */   
/*      */   public boolean forEachKey(TShortProcedure procedure) {
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
/*      */   public boolean forEachEntry(TShortObjectProcedure<? super V> procedure) {
/*  413 */     byte[] states = this._states;
/*  414 */     short[] keys = this._set;
/*  415 */     V[] values = this._values;
/*  416 */     for (int i = keys.length; i-- > 0;) {
/*  417 */       if (states[i] == 1 && !procedure.execute(keys[i], values[i]))
/*  418 */         return false; 
/*      */     } 
/*  421 */     return true;
/*      */   }
/*      */   
/*      */   public boolean retainEntries(TShortObjectProcedure<? super V> procedure) {
/*  428 */     boolean modified = false;
/*  429 */     byte[] states = this._states;
/*  430 */     short[] keys = this._set;
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
/*  467 */     if (!(other instanceof TShortObjectMap))
/*  468 */       return false; 
/*  470 */     TShortObjectMap that = (TShortObjectMap)other;
/*  471 */     if (that.size() != size())
/*  472 */       return false; 
/*      */     try {
/*  475 */       TShortObjectIterator<V> iter = iterator();
/*  476 */       while (iter.hasNext()) {
/*  477 */         iter.advance();
/*  478 */         short key = iter.key();
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
/*      */   class KeyView implements TShortSet {
/*      */     public short getNoEntryValue() {
/*  516 */       return TShortObjectHashMap.this.no_entry_key;
/*      */     }
/*      */     
/*      */     public int size() {
/*  521 */       return TShortObjectHashMap.this._size;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  526 */       return (TShortObjectHashMap.this._size == 0);
/*      */     }
/*      */     
/*      */     public boolean contains(short entry) {
/*  531 */       return TShortObjectHashMap.this.containsKey(entry);
/*      */     }
/*      */     
/*      */     public TShortIterator iterator() {
/*  536 */       return new TShortHashIterator(TShortObjectHashMap.this);
/*      */     }
/*      */     
/*      */     public short[] toArray() {
/*  541 */       return TShortObjectHashMap.this.keys();
/*      */     }
/*      */     
/*      */     public short[] toArray(short[] dest) {
/*  546 */       return TShortObjectHashMap.this.keys(dest);
/*      */     }
/*      */     
/*      */     public boolean add(short entry) {
/*  551 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean remove(short entry) {
/*  556 */       return (null != TShortObjectHashMap.this.remove(entry));
/*      */     }
/*      */     
/*      */     public boolean containsAll(Collection<?> collection) {
/*  561 */       for (Object element : collection) {
/*  562 */         if (!TShortObjectHashMap.this.containsKey(((Short)element).shortValue()))
/*  565 */           return false; 
/*      */       } 
/*  568 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(TShortCollection collection) {
/*  573 */       if (collection == this)
/*  574 */         return true; 
/*  576 */       TShortIterator iter = collection.iterator();
/*  577 */       while (iter.hasNext()) {
/*  578 */         if (!TShortObjectHashMap.this.containsKey(iter.next()))
/*  579 */           return false; 
/*      */       } 
/*  582 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(short[] array) {
/*  587 */       for (short element : array) {
/*  588 */         if (!TShortObjectHashMap.this.containsKey(element))
/*  589 */           return false; 
/*      */       } 
/*  592 */       return true;
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends Short> collection) {
/*  597 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(TShortCollection collection) {
/*  602 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(short[] array) {
/*  607 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  612 */       boolean modified = false;
/*  613 */       TShortIterator iter = iterator();
/*  614 */       while (iter.hasNext()) {
/*  616 */         if (!collection.contains(Short.valueOf(iter.next()))) {
/*  617 */           iter.remove();
/*  618 */           modified = true;
/*      */         } 
/*      */       } 
/*  621 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(TShortCollection collection) {
/*  626 */       if (this == collection)
/*  627 */         return false; 
/*  629 */       boolean modified = false;
/*  630 */       TShortIterator iter = iterator();
/*  631 */       while (iter.hasNext()) {
/*  632 */         if (!collection.contains(iter.next())) {
/*  633 */           iter.remove();
/*  634 */           modified = true;
/*      */         } 
/*      */       } 
/*  637 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(short[] array) {
/*  642 */       boolean changed = false;
/*  643 */       Arrays.sort(array);
/*  644 */       short[] set = TShortObjectHashMap.this._set;
/*  645 */       byte[] states = TShortObjectHashMap.this._states;
/*  647 */       for (int i = set.length; i-- > 0;) {
/*  648 */         if (states[i] == 1 && Arrays.binarySearch(array, set[i]) < 0) {
/*  649 */           TShortObjectHashMap.this.removeAt(i);
/*  650 */           changed = true;
/*      */         } 
/*      */       } 
/*  653 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(Collection<?> collection) {
/*  658 */       boolean changed = false;
/*  659 */       for (Object element : collection) {
/*  660 */         if (element instanceof Short) {
/*  661 */           short c = ((Short)element).shortValue();
/*  662 */           if (remove(c))
/*  663 */             changed = true; 
/*      */         } 
/*      */       } 
/*  667 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(TShortCollection collection) {
/*  672 */       if (collection == this) {
/*  673 */         clear();
/*  674 */         return true;
/*      */       } 
/*  676 */       boolean changed = false;
/*  677 */       TShortIterator iter = collection.iterator();
/*  678 */       while (iter.hasNext()) {
/*  679 */         short element = iter.next();
/*  680 */         if (remove(element))
/*  681 */           changed = true; 
/*      */       } 
/*  684 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(short[] array) {
/*  689 */       boolean changed = false;
/*  690 */       for (int i = array.length; i-- > 0;) {
/*  691 */         if (remove(array[i]))
/*  692 */           changed = true; 
/*      */       } 
/*  695 */       return changed;
/*      */     }
/*      */     
/*      */     public void clear() {
/*  700 */       TShortObjectHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public boolean forEach(TShortProcedure procedure) {
/*  705 */       return TShortObjectHashMap.this.forEachKey(procedure);
/*      */     }
/*      */     
/*      */     public boolean equals(Object other) {
/*  710 */       if (!(other instanceof TShortSet))
/*  711 */         return false; 
/*  713 */       TShortSet that = (TShortSet)other;
/*  714 */       if (that.size() != size())
/*  715 */         return false; 
/*  717 */       for (int i = TShortObjectHashMap.this._states.length; i-- > 0;) {
/*  718 */         if (TShortObjectHashMap.this._states[i] == 1 && 
/*  719 */           !that.contains(TShortObjectHashMap.this._set[i]))
/*  720 */           return false; 
/*      */       } 
/*  724 */       return true;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  729 */       int hashcode = 0;
/*  730 */       for (int i = TShortObjectHashMap.this._states.length; i-- > 0;) {
/*  731 */         if (TShortObjectHashMap.this._states[i] == 1)
/*  732 */           hashcode += HashFunctions.hash(TShortObjectHashMap.this._set[i]); 
/*      */       } 
/*  735 */       return hashcode;
/*      */     }
/*      */     
/*      */     public String toString() {
/*  740 */       StringBuilder buf = new StringBuilder("{");
/*  741 */       boolean first = true;
/*  742 */       for (int i = TShortObjectHashMap.this._states.length; i-- > 0;) {
/*  743 */         if (TShortObjectHashMap.this._states[i] == 1) {
/*  744 */           if (first) {
/*  744 */             first = false;
/*      */           } else {
/*  745 */             buf.append(",");
/*      */           } 
/*  746 */           buf.append(TShortObjectHashMap.this._set[i]);
/*      */         } 
/*      */       } 
/*  749 */       return buf.toString();
/*      */     }
/*      */     
/*      */     class TShortHashIterator extends THashPrimitiveIterator implements TShortIterator {
/*      */       private final TShortHash _hash;
/*      */       
/*      */       public TShortHashIterator(TShortHash hash) {
/*  760 */         super((TPrimitiveHash)hash);
/*  761 */         this._hash = hash;
/*      */       }
/*      */       
/*      */       public short next() {
/*  766 */         moveToNextIndex();
/*  767 */         return this._hash._set[this._index];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected class ValueView extends MapBackedView<V> {
/*      */     public Iterator<V> iterator() {
/*  778 */       return new TShortObjectValueHashIterator(TShortObjectHashMap.this) {
/*      */           protected V objectAtIndex(int index) {
/*  780 */             return TShortObjectHashMap.this._values[index];
/*      */           }
/*      */         };
/*      */     }
/*      */     
/*      */     public boolean containsElement(V value) {
/*  786 */       return TShortObjectHashMap.this.containsValue(value);
/*      */     }
/*      */     
/*      */     public boolean removeElement(V value) {
/*  790 */       V[] values = TShortObjectHashMap.this._values;
/*  791 */       byte[] states = TShortObjectHashMap.this._states;
/*  793 */       for (int i = values.length; i-- > 0;) {
/*  794 */         if (states[i] == 1 && (
/*  795 */           value == values[i] || (null != values[i] && values[i].equals(value)))) {
/*  797 */           TShortObjectHashMap.this.removeAt(i);
/*  798 */           return true;
/*      */         } 
/*      */       } 
/*  802 */       return false;
/*      */     }
/*      */     
/*      */     class TShortObjectValueHashIterator extends THashPrimitiveIterator implements Iterator<V> {
/*      */       protected final TShortObjectHashMap _map;
/*      */       
/*      */       public TShortObjectValueHashIterator(TShortObjectHashMap map) {
/*  811 */         super((TPrimitiveHash)map);
/*  812 */         this._map = map;
/*      */       }
/*      */       
/*      */       protected V objectAtIndex(int index) {
/*  817 */         byte[] states = TShortObjectHashMap.this._states;
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
/*  855 */       TShortObjectHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public boolean add(E obj) {
/*  859 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public int size() {
/*  863 */       return TShortObjectHashMap.this.size();
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
/*  897 */       return TShortObjectHashMap.this.isEmpty();
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
/*      */   class TShortObjectHashIterator<V> extends THashPrimitiveIterator implements TShortObjectIterator<V> {
/*      */     private final TShortObjectHashMap<V> _map;
/*      */     
/*      */     public TShortObjectHashIterator(TShortObjectHashMap<V> map) {
/*  931 */       super((TPrimitiveHash)map);
/*  932 */       this._map = map;
/*      */     }
/*      */     
/*      */     public void advance() {
/*  937 */       moveToNextIndex();
/*      */     }
/*      */     
/*      */     public short key() {
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
/*  967 */     out.writeShort(this.no_entry_key);
/*  970 */     out.writeInt(this._size);
/*  973 */     for (int i = this._states.length; i-- > 0;) {
/*  974 */       if (this._states[i] == 1) {
/*  975 */         out.writeShort(this._set[i]);
/*  976 */         out.writeObject(this._values[i]);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*  987 */     in.readByte();
/*  990 */     super.readExternal(in);
/*  993 */     this.no_entry_key = in.readShort();
/*  996 */     int size = in.readInt();
/*  997 */     setUp(size);
/* 1000 */     while (size-- > 0) {
/* 1001 */       short key = in.readShort();
/* 1002 */       V val = (V)in.readObject();
/* 1003 */       put(key, val);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1009 */     final StringBuilder buf = new StringBuilder("{");
/* 1010 */     forEachEntry(new TShortObjectProcedure<V>() {
/*      */           private boolean first = true;
/*      */           
/*      */           public boolean execute(short key, Object value) {
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
/*      */   public TShortObjectHashMap() {}
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\hash\TShortObjectHashMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */