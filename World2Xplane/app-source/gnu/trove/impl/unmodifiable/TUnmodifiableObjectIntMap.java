/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCollections;
/*     */ import gnu.trove.TIntCollection;
/*     */ import gnu.trove.function.TIntFunction;
/*     */ import gnu.trove.iterator.TObjectIntIterator;
/*     */ import gnu.trove.map.TObjectIntMap;
/*     */ import gnu.trove.procedure.TIntProcedure;
/*     */ import gnu.trove.procedure.TObjectIntProcedure;
/*     */ import gnu.trove.procedure.TObjectProcedure;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TUnmodifiableObjectIntMap<K> implements TObjectIntMap<K>, Serializable {
/*     */   private static final long serialVersionUID = -1034234728574286014L;
/*     */   
/*     */   private final TObjectIntMap<K> m;
/*     */   
/*     */   public TUnmodifiableObjectIntMap(TObjectIntMap<K> m) {
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
/*     */   public boolean containsKey(Object key) {
/*  60 */     return this.m.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(int val) {
/*  61 */     return this.m.containsValue(val);
/*     */   }
/*     */   
/*     */   public int get(Object key) {
/*  62 */     return this.m.get(key);
/*     */   }
/*     */   
/*     */   public int put(K key, int value) {
/*  64 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int remove(Object key) {
/*  65 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(TObjectIntMap<? extends K> m) {
/*  66 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends Integer> map) {
/*  67 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  68 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*  70 */   private transient Set<K> keySet = null;
/*     */   
/*  71 */   private transient TIntCollection values = null;
/*     */   
/*     */   public Set<K> keySet() {
/*  74 */     if (this.keySet == null)
/*  75 */       this.keySet = Collections.unmodifiableSet(this.m.keySet()); 
/*  76 */     return this.keySet;
/*     */   }
/*     */   
/*     */   public Object[] keys() {
/*  78 */     return this.m.keys();
/*     */   }
/*     */   
/*     */   public K[] keys(K[] array) {
/*  79 */     return (K[])this.m.keys((Object[])array);
/*     */   }
/*     */   
/*     */   public TIntCollection valueCollection() {
/*  82 */     if (this.values == null)
/*  83 */       this.values = TCollections.unmodifiableCollection(this.m.valueCollection()); 
/*  84 */     return this.values;
/*     */   }
/*     */   
/*     */   public int[] values() {
/*  86 */     return this.m.values();
/*     */   }
/*     */   
/*     */   public int[] values(int[] array) {
/*  87 */     return this.m.values(array);
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
/*     */   public int getNoEntryValue() {
/*  92 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TObjectProcedure<? super K> procedure) {
/*  95 */     return this.m.forEachKey(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TIntProcedure procedure) {
/*  98 */     return this.m.forEachValue(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TObjectIntProcedure<? super K> procedure) {
/* 101 */     return this.m.forEachEntry(procedure);
/*     */   }
/*     */   
/*     */   public TObjectIntIterator<K> iterator() {
/* 105 */     return new TObjectIntIterator<K>() {
/* 106 */         TObjectIntIterator<K> iter = TUnmodifiableObjectIntMap.this.m.iterator();
/*     */         
/*     */         public K key() {
/* 108 */           return (K)this.iter.key();
/*     */         }
/*     */         
/*     */         public int value() {
/* 109 */           return this.iter.value();
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
/*     */         public int setValue(int val) {
/* 112 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 113 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public int putIfAbsent(K key, int value) {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TIntFunction function) {
/* 118 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TObjectIntProcedure<? super K> procedure) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean increment(K key) {
/* 120 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean adjustValue(K key, int amount) {
/* 121 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int adjustOrPutValue(K key, int adjust_amount, int put_amount) {
/* 122 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableObjectIntMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */