/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCollections;
/*     */ import gnu.trove.function.TObjectFunction;
/*     */ import gnu.trove.iterator.TIntObjectIterator;
/*     */ import gnu.trove.map.TIntObjectMap;
/*     */ import gnu.trove.procedure.TIntObjectProcedure;
/*     */ import gnu.trove.procedure.TIntProcedure;
/*     */ import gnu.trove.procedure.TObjectProcedure;
/*     */ import gnu.trove.set.TIntSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TUnmodifiableIntObjectMap<V> implements TIntObjectMap<V>, Serializable {
/*     */   private static final long serialVersionUID = -1034234728574286014L;
/*     */   
/*     */   private final TIntObjectMap<V> m;
/*     */   
/*     */   public TUnmodifiableIntObjectMap(TIntObjectMap<V> m) {
/*  53 */     if (m == null)
/*  54 */       throw new NullPointerException(); 
/*  55 */     this.m = m;
/*     */   }
/*     */   
/*     */   public int size() {
/*  58 */     return this.m.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  59 */     return this.m.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(int key) {
/*  60 */     return this.m.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/*  61 */     return this.m.containsValue(val);
/*     */   }
/*     */   
/*     */   public V get(int key) {
/*  62 */     return (V)this.m.get(key);
/*     */   }
/*     */   
/*     */   public V put(int key, V value) {
/*  64 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V remove(int key) {
/*  65 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(TIntObjectMap<? extends V> m) {
/*  66 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Integer, ? extends V> map) {
/*  67 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  68 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*  70 */   private transient TIntSet keySet = null;
/*     */   
/*  71 */   private transient Collection<V> values = null;
/*     */   
/*     */   public TIntSet keySet() {
/*  74 */     if (this.keySet == null)
/*  75 */       this.keySet = TCollections.unmodifiableSet(this.m.keySet()); 
/*  76 */     return this.keySet;
/*     */   }
/*     */   
/*     */   public int[] keys() {
/*  78 */     return this.m.keys();
/*     */   }
/*     */   
/*     */   public int[] keys(int[] array) {
/*  79 */     return this.m.keys(array);
/*     */   }
/*     */   
/*     */   public Collection<V> valueCollection() {
/*  82 */     if (this.values == null)
/*  83 */       this.values = Collections.unmodifiableCollection(this.m.valueCollection()); 
/*  84 */     return this.values;
/*     */   }
/*     */   
/*     */   public Object[] values() {
/*  86 */     return this.m.values();
/*     */   }
/*     */   
/*     */   public V[] values(V[] array) {
/*  87 */     return (V[])this.m.values((Object[])array);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  89 */     return (o == this || this.m.equals(o));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  90 */     return this.m.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/*  91 */     return this.m.toString();
/*     */   }
/*     */   
/*     */   public int getNoEntryKey() {
/*  92 */     return this.m.getNoEntryKey();
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TIntProcedure procedure) {
/*  95 */     return this.m.forEachKey(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TObjectProcedure<? super V> procedure) {
/*  98 */     return this.m.forEachValue(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TIntObjectProcedure<? super V> procedure) {
/* 101 */     return this.m.forEachEntry(procedure);
/*     */   }
/*     */   
/*     */   public TIntObjectIterator<V> iterator() {
/* 105 */     return new TIntObjectIterator<V>() {
/* 106 */         TIntObjectIterator<V> iter = TUnmodifiableIntObjectMap.this.m.iterator();
/*     */         
/*     */         public int key() {
/* 108 */           return this.iter.key();
/*     */         }
/*     */         
/*     */         public V value() {
/* 109 */           return (V)this.iter.value();
/*     */         }
/*     */         
/*     */         public void advance() {
/* 110 */           this.iter.advance();
/*     */         }
/*     */         
/*     */         public boolean hasNext() {
/* 111 */           return this.iter.hasNext();
/*     */         }
/*     */         
/*     */         public V setValue(V val) {
/* 112 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 113 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public V putIfAbsent(int key, V value) {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TObjectFunction<V, V> function) {
/* 118 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TIntObjectProcedure<? super V> procedure) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableIntObjectMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */