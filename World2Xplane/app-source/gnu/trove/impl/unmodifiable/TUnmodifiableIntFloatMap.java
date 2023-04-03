/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCollections;
/*     */ import gnu.trove.TFloatCollection;
/*     */ import gnu.trove.function.TFloatFunction;
/*     */ import gnu.trove.iterator.TIntFloatIterator;
/*     */ import gnu.trove.map.TIntFloatMap;
/*     */ import gnu.trove.procedure.TFloatProcedure;
/*     */ import gnu.trove.procedure.TIntFloatProcedure;
/*     */ import gnu.trove.procedure.TIntProcedure;
/*     */ import gnu.trove.set.TIntSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TUnmodifiableIntFloatMap implements TIntFloatMap, Serializable {
/*     */   private static final long serialVersionUID = -1034234728574286014L;
/*     */   
/*     */   private final TIntFloatMap m;
/*     */   
/*     */   public TUnmodifiableIntFloatMap(TIntFloatMap m) {
/*  58 */     if (m == null)
/*  59 */       throw new NullPointerException(); 
/*  60 */     this.m = m;
/*     */   }
/*     */   
/*     */   public int size() {
/*  63 */     return this.m.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  64 */     return this.m.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(int key) {
/*  65 */     return this.m.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(float val) {
/*  66 */     return this.m.containsValue(val);
/*     */   }
/*     */   
/*     */   public float get(int key) {
/*  67 */     return this.m.get(key);
/*     */   }
/*     */   
/*     */   public float put(int key, float value) {
/*  69 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public float remove(int key) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(TIntFloatMap m) {
/*  71 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Integer, ? extends Float> map) {
/*  72 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  73 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*  75 */   private transient TIntSet keySet = null;
/*     */   
/*  76 */   private transient TFloatCollection values = null;
/*     */   
/*     */   public TIntSet keySet() {
/*  79 */     if (this.keySet == null)
/*  80 */       this.keySet = TCollections.unmodifiableSet(this.m.keySet()); 
/*  81 */     return this.keySet;
/*     */   }
/*     */   
/*     */   public int[] keys() {
/*  83 */     return this.m.keys();
/*     */   }
/*     */   
/*     */   public int[] keys(int[] array) {
/*  84 */     return this.m.keys(array);
/*     */   }
/*     */   
/*     */   public TFloatCollection valueCollection() {
/*  87 */     if (this.values == null)
/*  88 */       this.values = TCollections.unmodifiableCollection(this.m.valueCollection()); 
/*  89 */     return this.values;
/*     */   }
/*     */   
/*     */   public float[] values() {
/*  91 */     return this.m.values();
/*     */   }
/*     */   
/*     */   public float[] values(float[] array) {
/*  92 */     return this.m.values(array);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  94 */     return (o == this || this.m.equals(o));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  95 */     return this.m.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/*  96 */     return this.m.toString();
/*     */   }
/*     */   
/*     */   public int getNoEntryKey() {
/*  97 */     return this.m.getNoEntryKey();
/*     */   }
/*     */   
/*     */   public float getNoEntryValue() {
/*  98 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TIntProcedure procedure) {
/* 101 */     return this.m.forEachKey(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TFloatProcedure procedure) {
/* 104 */     return this.m.forEachValue(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TIntFloatProcedure procedure) {
/* 107 */     return this.m.forEachEntry(procedure);
/*     */   }
/*     */   
/*     */   public TIntFloatIterator iterator() {
/* 111 */     return new TIntFloatIterator() {
/* 112 */         TIntFloatIterator iter = TUnmodifiableIntFloatMap.this.m.iterator();
/*     */         
/*     */         public int key() {
/* 114 */           return this.iter.key();
/*     */         }
/*     */         
/*     */         public float value() {
/* 115 */           return this.iter.value();
/*     */         }
/*     */         
/*     */         public void advance() {
/* 116 */           this.iter.advance();
/*     */         }
/*     */         
/*     */         public boolean hasNext() {
/* 117 */           return this.iter.hasNext();
/*     */         }
/*     */         
/*     */         public float setValue(float val) {
/* 118 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 119 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public float putIfAbsent(int key, float value) {
/* 123 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TFloatFunction function) {
/* 124 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TIntFloatProcedure procedure) {
/* 125 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean increment(int key) {
/* 126 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean adjustValue(int key, float amount) {
/* 127 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public float adjustOrPutValue(int key, float adjust_amount, float put_amount) {
/* 128 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableIntFloatMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */