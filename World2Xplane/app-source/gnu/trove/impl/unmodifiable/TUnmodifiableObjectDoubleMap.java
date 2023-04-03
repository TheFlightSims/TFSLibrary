/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCollections;
/*     */ import gnu.trove.TDoubleCollection;
/*     */ import gnu.trove.function.TDoubleFunction;
/*     */ import gnu.trove.iterator.TObjectDoubleIterator;
/*     */ import gnu.trove.map.TObjectDoubleMap;
/*     */ import gnu.trove.procedure.TDoubleProcedure;
/*     */ import gnu.trove.procedure.TObjectDoubleProcedure;
/*     */ import gnu.trove.procedure.TObjectProcedure;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TUnmodifiableObjectDoubleMap<K> implements TObjectDoubleMap<K>, Serializable {
/*     */   private static final long serialVersionUID = -1034234728574286014L;
/*     */   
/*     */   private final TObjectDoubleMap<K> m;
/*     */   
/*     */   public TUnmodifiableObjectDoubleMap(TObjectDoubleMap<K> m) {
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
/*     */   public boolean containsValue(double val) {
/*  61 */     return this.m.containsValue(val);
/*     */   }
/*     */   
/*     */   public double get(Object key) {
/*  62 */     return this.m.get(key);
/*     */   }
/*     */   
/*     */   public double put(K key, double value) {
/*  64 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public double remove(Object key) {
/*  65 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(TObjectDoubleMap<? extends K> m) {
/*  66 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends Double> map) {
/*  67 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  68 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*  70 */   private transient Set<K> keySet = null;
/*     */   
/*  71 */   private transient TDoubleCollection values = null;
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
/*     */   public TDoubleCollection valueCollection() {
/*  82 */     if (this.values == null)
/*  83 */       this.values = TCollections.unmodifiableCollection(this.m.valueCollection()); 
/*  84 */     return this.values;
/*     */   }
/*     */   
/*     */   public double[] values() {
/*  86 */     return this.m.values();
/*     */   }
/*     */   
/*     */   public double[] values(double[] array) {
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
/*     */   public double getNoEntryValue() {
/*  92 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TObjectProcedure<? super K> procedure) {
/*  95 */     return this.m.forEachKey(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TDoubleProcedure procedure) {
/*  98 */     return this.m.forEachValue(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TObjectDoubleProcedure<? super K> procedure) {
/* 101 */     return this.m.forEachEntry(procedure);
/*     */   }
/*     */   
/*     */   public TObjectDoubleIterator<K> iterator() {
/* 105 */     return new TObjectDoubleIterator<K>() {
/* 106 */         TObjectDoubleIterator<K> iter = TUnmodifiableObjectDoubleMap.this.m.iterator();
/*     */         
/*     */         public K key() {
/* 108 */           return (K)this.iter.key();
/*     */         }
/*     */         
/*     */         public double value() {
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
/*     */         public double setValue(double val) {
/* 112 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 113 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public double putIfAbsent(K key, double value) {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TDoubleFunction function) {
/* 118 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TObjectDoubleProcedure<? super K> procedure) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean increment(K key) {
/* 120 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean adjustValue(K key, double amount) {
/* 121 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public double adjustOrPutValue(K key, double adjust_amount, double put_amount) {
/* 122 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableObjectDoubleMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */