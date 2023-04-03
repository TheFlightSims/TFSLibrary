/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCharCollection;
/*     */ import gnu.trove.TCollections;
/*     */ import gnu.trove.function.TCharFunction;
/*     */ import gnu.trove.iterator.TObjectCharIterator;
/*     */ import gnu.trove.map.TObjectCharMap;
/*     */ import gnu.trove.procedure.TCharProcedure;
/*     */ import gnu.trove.procedure.TObjectCharProcedure;
/*     */ import gnu.trove.procedure.TObjectProcedure;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TUnmodifiableObjectCharMap<K> implements TObjectCharMap<K>, Serializable {
/*     */   private static final long serialVersionUID = -1034234728574286014L;
/*     */   
/*     */   private final TObjectCharMap<K> m;
/*     */   
/*     */   public TUnmodifiableObjectCharMap(TObjectCharMap<K> m) {
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
/*     */   public boolean containsValue(char val) {
/*  61 */     return this.m.containsValue(val);
/*     */   }
/*     */   
/*     */   public char get(Object key) {
/*  62 */     return this.m.get(key);
/*     */   }
/*     */   
/*     */   public char put(K key, char value) {
/*  64 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public char remove(Object key) {
/*  65 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(TObjectCharMap<? extends K> m) {
/*  66 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends Character> map) {
/*  67 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  68 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*  70 */   private transient Set<K> keySet = null;
/*     */   
/*  71 */   private transient TCharCollection values = null;
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
/*     */   public TCharCollection valueCollection() {
/*  82 */     if (this.values == null)
/*  83 */       this.values = TCollections.unmodifiableCollection(this.m.valueCollection()); 
/*  84 */     return this.values;
/*     */   }
/*     */   
/*     */   public char[] values() {
/*  86 */     return this.m.values();
/*     */   }
/*     */   
/*     */   public char[] values(char[] array) {
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
/*     */   public char getNoEntryValue() {
/*  92 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TObjectProcedure<? super K> procedure) {
/*  95 */     return this.m.forEachKey(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TCharProcedure procedure) {
/*  98 */     return this.m.forEachValue(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TObjectCharProcedure<? super K> procedure) {
/* 101 */     return this.m.forEachEntry(procedure);
/*     */   }
/*     */   
/*     */   public TObjectCharIterator<K> iterator() {
/* 105 */     return new TObjectCharIterator<K>() {
/* 106 */         TObjectCharIterator<K> iter = TUnmodifiableObjectCharMap.this.m.iterator();
/*     */         
/*     */         public K key() {
/* 108 */           return (K)this.iter.key();
/*     */         }
/*     */         
/*     */         public char value() {
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
/*     */         public char setValue(char val) {
/* 112 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 113 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public char putIfAbsent(K key, char value) {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TCharFunction function) {
/* 118 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TObjectCharProcedure<? super K> procedure) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean increment(K key) {
/* 120 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean adjustValue(K key, char amount) {
/* 121 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public char adjustOrPutValue(K key, char adjust_amount, char put_amount) {
/* 122 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableObjectCharMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */