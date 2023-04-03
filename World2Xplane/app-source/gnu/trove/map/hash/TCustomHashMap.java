/*     */ package gnu.trove.map.hash;
/*     */ 
/*     */ import gnu.trove.function.TObjectFunction;
/*     */ import gnu.trove.impl.HashFunctions;
/*     */ import gnu.trove.impl.hash.TCustomObjectHash;
/*     */ import gnu.trove.impl.hash.TObjectHash;
/*     */ import gnu.trove.iterator.hash.TObjectHashIterator;
/*     */ import gnu.trove.map.TMap;
/*     */ import gnu.trove.procedure.TObjectObjectProcedure;
/*     */ import gnu.trove.procedure.TObjectProcedure;
/*     */ import gnu.trove.strategy.HashingStrategy;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TCustomHashMap<K, V> extends TCustomObjectHash<K> implements TMap<K, V>, Externalizable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected transient V[] _values;
/*     */   
/*     */   public TCustomHashMap() {}
/*     */   
/*     */   public TCustomHashMap(HashingStrategy<? super K> strategy) {
/*  63 */     super(strategy);
/*     */   }
/*     */   
/*     */   public TCustomHashMap(HashingStrategy<? super K> strategy, int initialCapacity) {
/*  75 */     super(strategy, initialCapacity);
/*     */   }
/*     */   
/*     */   public TCustomHashMap(HashingStrategy<? super K> strategy, int initialCapacity, float loadFactor) {
/*  90 */     super(strategy, initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   public TCustomHashMap(HashingStrategy<? super K> strategy, Map<? extends K, ? extends V> map) {
/* 103 */     this(strategy, map.size());
/* 104 */     putAll(map);
/*     */   }
/*     */   
/*     */   public TCustomHashMap(HashingStrategy<? super K> strategy, TCustomHashMap<? extends K, ? extends V> map) {
/* 117 */     this(strategy, map.size());
/* 118 */     putAll((Map<? extends K, ? extends V>)map);
/*     */   }
/*     */   
/*     */   public int setUp(int initialCapacity) {
/* 131 */     int capacity = super.setUp(initialCapacity);
/* 133 */     this._values = (V[])new Object[capacity];
/* 134 */     return capacity;
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 147 */     int index = insertKey(key);
/* 148 */     return doPut(value, index);
/*     */   }
/*     */   
/*     */   public V putIfAbsent(K key, V value) {
/* 162 */     int index = insertKey(key);
/* 163 */     if (index < 0)
/* 164 */       return this._values[-index - 1]; 
/* 166 */     return doPut(value, index);
/*     */   }
/*     */   
/*     */   private V doPut(V value, int index) {
/* 171 */     V previous = null;
/* 172 */     boolean isNewMapping = true;
/* 173 */     if (index < 0) {
/* 174 */       index = -index - 1;
/* 175 */       previous = this._values[index];
/* 176 */       isNewMapping = false;
/*     */     } 
/* 179 */     this._values[index] = value;
/* 181 */     if (isNewMapping)
/* 182 */       postInsertHook(this.consumeFreeSlot); 
/* 185 */     return previous;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 198 */     if (!(other instanceof Map))
/* 199 */       return false; 
/* 201 */     Map<K, V> that = (Map<K, V>)other;
/* 202 */     if (that.size() != size())
/* 203 */       return false; 
/* 205 */     return forEachEntry(new EqProcedure<K, V>(that));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 210 */     HashProcedure p = new HashProcedure();
/* 211 */     forEachEntry(p);
/* 212 */     return p.getHashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 217 */     final StringBuilder buf = new StringBuilder("{");
/* 218 */     forEachEntry(new TObjectObjectProcedure<K, V>() {
/*     */           private boolean first = true;
/*     */           
/*     */           public boolean execute(K key, V value) {
/* 223 */             if (this.first) {
/* 224 */               this.first = false;
/*     */             } else {
/* 226 */               buf.append(", ");
/*     */             } 
/* 229 */             buf.append(key);
/* 230 */             buf.append("=");
/* 231 */             buf.append(value);
/* 232 */             return true;
/*     */           }
/*     */         });
/* 235 */     buf.append("}");
/* 236 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private final class HashProcedure implements TObjectObjectProcedure<K, V> {
/* 241 */     private int h = 0;
/*     */     
/*     */     public int getHashCode() {
/* 244 */       return this.h;
/*     */     }
/*     */     
/*     */     public final boolean execute(K key, V value) {
/* 248 */       this.h += HashFunctions.hash(key) ^ ((value == null) ? 0 : value.hashCode());
/* 249 */       return true;
/*     */     }
/*     */     
/*     */     private HashProcedure() {}
/*     */   }
/*     */   
/*     */   private static final class EqProcedure<K, V> implements TObjectObjectProcedure<K, V> {
/*     */     private final Map<K, V> _otherMap;
/*     */     
/*     */     EqProcedure(Map<K, V> otherMap) {
/* 258 */       this._otherMap = otherMap;
/*     */     }
/*     */     
/*     */     public final boolean execute(K key, V value) {
/* 266 */       if (value == null && !this._otherMap.containsKey(key))
/* 267 */         return false; 
/* 270 */       V oValue = this._otherMap.get(key);
/* 271 */       return (oValue == value || (oValue != null && oValue.equals(value)));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TObjectProcedure<? super K> procedure) {
/* 284 */     return forEach(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TObjectProcedure<? super V> procedure) {
/* 296 */     V[] values = this._values;
/* 297 */     Object[] set = this._set;
/* 298 */     for (int i = values.length; i-- > 0;) {
/* 299 */       if (set[i] != FREE && set[i] != REMOVED && !procedure.execute(values[i]))
/* 302 */         return false; 
/*     */     } 
/* 305 */     return true;
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TObjectObjectProcedure<? super K, ? super V> procedure) {
/* 319 */     Object[] keys = this._set;
/* 320 */     V[] values = this._values;
/* 321 */     for (int i = keys.length; i-- > 0;) {
/* 322 */       if (keys[i] != FREE && keys[i] != REMOVED && !procedure.execute(keys[i], values[i]))
/* 325 */         return false; 
/*     */     } 
/* 328 */     return true;
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TObjectObjectProcedure<? super K, ? super V> procedure) {
/* 341 */     boolean modified = false;
/* 342 */     Object[] keys = this._set;
/* 343 */     V[] values = this._values;
/* 346 */     tempDisableAutoCompaction();
/*     */     try {
/* 348 */       for (int i = keys.length; i-- > 0;) {
/* 349 */         if (keys[i] != FREE && keys[i] != REMOVED && !procedure.execute(keys[i], values[i])) {
/* 352 */           removeAt(i);
/* 353 */           modified = true;
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 358 */       reenableAutoCompaction(true);
/*     */     } 
/* 361 */     return modified;
/*     */   }
/*     */   
/*     */   public void transformValues(TObjectFunction<V, V> function) {
/* 371 */     V[] values = this._values;
/* 372 */     Object[] set = this._set;
/* 373 */     for (int i = values.length; i-- > 0;) {
/* 374 */       if (set[i] != FREE && set[i] != REMOVED)
/* 375 */         values[i] = (V)function.execute(values[i]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void rehash(int newCapacity) {
/* 388 */     int oldCapacity = this._set.length;
/* 389 */     int oldSize = size();
/* 390 */     Object[] oldKeys = this._set;
/* 391 */     V[] oldVals = this._values;
/* 393 */     this._set = new Object[newCapacity];
/* 394 */     Arrays.fill(this._set, FREE);
/* 395 */     this._values = (V[])new Object[newCapacity];
/* 399 */     for (int i = oldCapacity; i-- > 0; ) {
/* 400 */       Object o = oldKeys[i];
/* 401 */       if (o == FREE || o == REMOVED)
/*     */         continue; 
/* 403 */       int index = insertKey(o);
/* 404 */       if (index < 0)
/* 405 */         throwObjectContractViolation(this._set[-index - 1], o, size(), oldSize, oldKeys); 
/* 407 */       this._values[index] = oldVals[i];
/*     */     } 
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/* 420 */     int index = index(key);
/* 421 */     if (index < 0 || !this.strategy.equals(this._set[index], key))
/* 422 */       return null; 
/* 424 */     return this._values[index];
/*     */   }
/*     */   
/*     */   public void clear() {
/* 430 */     if (size() == 0)
/*     */       return; 
/* 434 */     super.clear();
/* 436 */     Arrays.fill(this._set, 0, this._set.length, FREE);
/* 437 */     Arrays.fill((Object[])this._values, 0, this._values.length, (Object)null);
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 449 */     V prev = null;
/* 450 */     int index = index(key);
/* 451 */     if (index >= 0) {
/* 452 */       prev = this._values[index];
/* 453 */       removeAt(index);
/*     */     } 
/* 455 */     return prev;
/*     */   }
/*     */   
/*     */   public void removeAt(int index) {
/* 465 */     this._values[index] = null;
/* 466 */     super.removeAt(index);
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 476 */     return new ValueView();
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 486 */     return new KeyView();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 496 */     return new EntryView();
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 507 */     Object[] set = this._set;
/* 508 */     V[] vals = this._values;
/* 512 */     if (null == val) {
/* 513 */       for (int i = vals.length; i-- > 0;) {
/* 514 */         if (set[i] != FREE && set[i] != REMOVED && val == vals[i])
/* 516 */           return true; 
/*     */       } 
/*     */     } else {
/* 520 */       for (int i = vals.length; i-- > 0;) {
/* 521 */         if (set[i] != FREE && set[i] != REMOVED && (val == vals[i] || this.strategy.equals(val, vals[i])))
/* 523 */           return true; 
/*     */       } 
/*     */     } 
/* 527 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 538 */     return contains(key);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> map) {
/* 548 */     ensureCapacity(map.size());
/* 550 */     for (Map.Entry<? extends K, ? extends V> e : map.entrySet())
/* 551 */       put(e.getKey(), e.getValue()); 
/*     */   }
/*     */   
/*     */   protected class ValueView extends MapBackedView<V> {
/*     */     public Iterator<V> iterator() {
/* 561 */       return (Iterator<V>)new TObjectHashIterator((TObjectHash)TCustomHashMap.this) {
/*     */           protected V objectAtIndex(int index) {
/* 563 */             return TCustomHashMap.this._values[index];
/*     */           }
/*     */         };
/*     */     }
/*     */     
/*     */     public boolean containsElement(V value) {
/* 570 */       return TCustomHashMap.this.containsValue(value);
/*     */     }
/*     */     
/*     */     public boolean removeElement(V value) {
/* 575 */       V[] arrayOfV = TCustomHashMap.this._values;
/* 576 */       Object[] set = TCustomHashMap.this._set;
/* 578 */       for (int i = arrayOfV.length; i-- > 0;) {
/* 579 */         if ((set[i] != TObjectHash.FREE && set[i] != TObjectHash.REMOVED && value == arrayOfV[i]) || (null != arrayOfV[i] && TCustomHashMap.this.strategy.equals(arrayOfV[i], value))) {
/* 583 */           TCustomHashMap.this.removeAt(i);
/* 584 */           return true;
/*     */         } 
/*     */       } 
/* 588 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   protected class EntryView extends MapBackedView<Map.Entry<K, V>> {
/*     */     private final class EntryIterator extends TObjectHashIterator {
/*     */       EntryIterator(TCustomHashMap<K, V> map) {
/* 598 */         super((TObjectHash)map);
/*     */       }
/*     */       
/*     */       public TCustomHashMap<K, V>.Entry objectAtIndex(int index) {
/* 604 */         return new TCustomHashMap.Entry((K)TCustomHashMap.this._set[index], TCustomHashMap.this._values[index], index);
/*     */       }
/*     */     }
/*     */     
/*     */     public Iterator<Map.Entry<K, V>> iterator() {
/* 611 */       return (Iterator<Map.Entry<K, V>>)new EntryIterator(TCustomHashMap.this);
/*     */     }
/*     */     
/*     */     public boolean removeElement(Map.Entry<K, V> entry) {
/* 628 */       K key = keyForEntry(entry);
/* 629 */       int index = TCustomHashMap.this.index(key);
/* 630 */       if (index >= 0) {
/* 631 */         Object val = valueForEntry(entry);
/* 632 */         if (val == TCustomHashMap.this._values[index] || (null != val && TCustomHashMap.this.strategy.equals(val, TCustomHashMap.this._values[index]))) {
/* 634 */           TCustomHashMap.this.removeAt(index);
/* 635 */           return true;
/*     */         } 
/*     */       } 
/* 638 */       return false;
/*     */     }
/*     */     
/*     */     public boolean containsElement(Map.Entry<K, V> entry) {
/* 643 */       Object val = TCustomHashMap.this.get(keyForEntry(entry));
/* 644 */       Object entryValue = entry.getValue();
/* 645 */       return (entryValue == val || (null != val && TCustomHashMap.this.strategy.equals(val, entryValue)));
/*     */     }
/*     */     
/*     */     protected V valueForEntry(Map.Entry<K, V> entry) {
/* 651 */       return entry.getValue();
/*     */     }
/*     */     
/*     */     protected K keyForEntry(Map.Entry<K, V> entry) {
/* 656 */       return entry.getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private abstract class MapBackedView<E> extends AbstractSet<E> implements Set<E>, Iterable<E> {
/*     */     private MapBackedView() {}
/*     */     
/*     */     public boolean contains(Object key) {
/* 674 */       return containsElement((E)key);
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 680 */       return removeElement((E)o);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 695 */       TCustomHashMap.this.clear();
/*     */     }
/*     */     
/*     */     public boolean add(E obj) {
/* 700 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public int size() {
/* 705 */       return TCustomHashMap.this.size();
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 710 */       Object[] result = new Object[size()];
/* 711 */       Iterator<E> e = iterator();
/* 712 */       for (int i = 0; e.hasNext(); i++)
/* 713 */         result[i] = e.next(); 
/* 715 */       return result;
/*     */     }
/*     */     
/*     */     public <T> T[] toArray(T[] a) {
/* 721 */       int size = size();
/* 722 */       if (a.length < size)
/* 723 */         a = (T[])Array.newInstance(a.getClass().getComponentType(), size); 
/* 726 */       Iterator<E> it = iterator();
/* 727 */       T[] arrayOfT = a;
/* 728 */       for (int i = 0; i < size; i++)
/* 729 */         arrayOfT[i] = (T)it.next(); 
/* 732 */       if (a.length > size)
/* 733 */         a[size] = null; 
/* 736 */       return a;
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 741 */       return TCustomHashMap.this.isEmpty();
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection<? extends E> collection) {
/* 746 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection<?> collection) {
/* 752 */       boolean changed = false;
/* 753 */       Iterator<E> i = iterator();
/* 754 */       while (i.hasNext()) {
/* 755 */         if (!collection.contains(i.next())) {
/* 756 */           i.remove();
/* 757 */           changed = true;
/*     */         } 
/*     */       } 
/* 760 */       return changed;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 764 */       Iterator<E> i = iterator();
/* 765 */       if (!i.hasNext())
/* 765 */         return "{}"; 
/* 767 */       StringBuilder sb = new StringBuilder();
/* 768 */       sb.append('{');
/*     */       while (true) {
/* 770 */         E e = i.next();
/* 771 */         sb.append((e == this) ? "(this Collection)" : e);
/* 772 */         if (!i.hasNext())
/* 772 */           return sb.append('}').toString(); 
/* 773 */         sb.append(", ");
/*     */       } 
/*     */     }
/*     */     
/*     */     public abstract Iterator<E> iterator();
/*     */     
/*     */     public abstract boolean removeElement(E param1E);
/*     */     
/*     */     public abstract boolean containsElement(E param1E);
/*     */   }
/*     */   
/*     */   protected class KeyView extends MapBackedView<K> {
/*     */     public Iterator<K> iterator() {
/* 783 */       return (Iterator<K>)new TObjectHashIterator((TObjectHash)TCustomHashMap.this);
/*     */     }
/*     */     
/*     */     public boolean removeElement(K key) {
/* 788 */       return (null != TCustomHashMap.this.remove(key));
/*     */     }
/*     */     
/*     */     public boolean containsElement(K key) {
/* 793 */       return TCustomHashMap.this.contains(key);
/*     */     }
/*     */   }
/*     */   
/*     */   final class Entry implements Map.Entry<K, V> {
/*     */     private K key;
/*     */     
/*     */     private V val;
/*     */     
/*     */     private final int index;
/*     */     
/*     */     Entry(K key, V value, int index) {
/* 805 */       this.key = key;
/* 806 */       this.val = value;
/* 807 */       this.index = index;
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 812 */       return this.key;
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 817 */       return this.val;
/*     */     }
/*     */     
/*     */     public V setValue(V o) {
/* 822 */       if (TCustomHashMap.this._values[this.index] != this.val)
/* 823 */         throw new ConcurrentModificationException(); 
/* 826 */       V retval = this.val;
/* 828 */       TCustomHashMap.this._values[this.index] = o;
/* 829 */       this.val = o;
/* 830 */       return retval;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 835 */       if (o instanceof Map.Entry) {
/* 836 */         Map.Entry<K, V> e1 = this;
/* 837 */         Map.Entry e2 = (Map.Entry)o;
/* 838 */         return (((e1.getKey() == null) ? (e2.getKey() == null) : TCustomHashMap.this.strategy.equals(e1.getKey(), e2.getKey())) && ((e1.getValue() == null) ? (e2.getValue() == null) : e1.getValue().equals(e2.getValue())));
/*     */       } 
/* 843 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 848 */       return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*     */     }
/*     */     
/*     */     public String toString() {
/* 854 */       return (new StringBuilder()).append(this.key).append("=").append(this.val).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 861 */     out.writeByte(1);
/* 864 */     super.writeExternal(out);
/* 867 */     out.writeInt(this._size);
/* 870 */     for (int i = this._set.length; i-- > 0;) {
/* 871 */       if (this._set[i] != REMOVED && this._set[i] != FREE) {
/* 872 */         out.writeObject(this._set[i]);
/* 873 */         out.writeObject(this._values[i]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 883 */     byte version = in.readByte();
/* 886 */     if (version != 0)
/* 887 */       super.readExternal(in); 
/* 891 */     int size = in.readInt();
/* 892 */     setUp(size);
/* 895 */     while (size-- > 0) {
/* 897 */       K key = (K)in.readObject();
/* 899 */       V val = (V)in.readObject();
/* 900 */       put(key, val);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\hash\TCustomHashMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */