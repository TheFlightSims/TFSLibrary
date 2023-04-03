/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCollections;
/*     */ import gnu.trove.TIntCollection;
/*     */ import gnu.trove.function.TIntFunction;
/*     */ import gnu.trove.iterator.TDoubleIntIterator;
/*     */ import gnu.trove.map.TDoubleIntMap;
/*     */ import gnu.trove.procedure.TDoubleIntProcedure;
/*     */ import gnu.trove.procedure.TDoubleProcedure;
/*     */ import gnu.trove.procedure.TIntProcedure;
/*     */ import gnu.trove.set.TDoubleSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TUnmodifiableDoubleIntMap implements TDoubleIntMap, Serializable {
/*     */   private static final long serialVersionUID = -1034234728574286014L;
/*     */   
/*     */   private final TDoubleIntMap m;
/*     */   
/*     */   public TUnmodifiableDoubleIntMap(TDoubleIntMap m) {
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
/*     */   public boolean containsKey(double key) {
/*  65 */     return this.m.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(int val) {
/*  66 */     return this.m.containsValue(val);
/*     */   }
/*     */   
/*     */   public int get(double key) {
/*  67 */     return this.m.get(key);
/*     */   }
/*     */   
/*     */   public int put(double key, int value) {
/*  69 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int remove(double key) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(TDoubleIntMap m) {
/*  71 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Double, ? extends Integer> map) {
/*  72 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  73 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*  75 */   private transient TDoubleSet keySet = null;
/*     */   
/*  76 */   private transient TIntCollection values = null;
/*     */   
/*     */   public TDoubleSet keySet() {
/*  79 */     if (this.keySet == null)
/*  80 */       this.keySet = TCollections.unmodifiableSet(this.m.keySet()); 
/*  81 */     return this.keySet;
/*     */   }
/*     */   
/*     */   public double[] keys() {
/*  83 */     return this.m.keys();
/*     */   }
/*     */   
/*     */   public double[] keys(double[] array) {
/*  84 */     return this.m.keys(array);
/*     */   }
/*     */   
/*     */   public TIntCollection valueCollection() {
/*  87 */     if (this.values == null)
/*  88 */       this.values = TCollections.unmodifiableCollection(this.m.valueCollection()); 
/*  89 */     return this.values;
/*     */   }
/*     */   
/*     */   public int[] values() {
/*  91 */     return this.m.values();
/*     */   }
/*     */   
/*     */   public int[] values(int[] array) {
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
/*     */   public double getNoEntryKey() {
/*  97 */     return this.m.getNoEntryKey();
/*     */   }
/*     */   
/*     */   public int getNoEntryValue() {
/*  98 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TDoubleProcedure procedure) {
/* 101 */     return this.m.forEachKey(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TIntProcedure procedure) {
/* 104 */     return this.m.forEachValue(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TDoubleIntProcedure procedure) {
/* 107 */     return this.m.forEachEntry(procedure);
/*     */   }
/*     */   
/*     */   public TDoubleIntIterator iterator() {
/* 111 */     return new TDoubleIntIterator() {
/* 112 */         TDoubleIntIterator iter = TUnmodifiableDoubleIntMap.this.m.iterator();
/*     */         
/*     */         public double key() {
/* 114 */           return this.iter.key();
/*     */         }
/*     */         
/*     */         public int value() {
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
/*     */         public int setValue(int val) {
/* 118 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 119 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public int putIfAbsent(double key, int value) {
/* 123 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TIntFunction function) {
/* 124 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TDoubleIntProcedure procedure) {
/* 125 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean increment(double key) {
/* 126 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean adjustValue(double key, int amount) {
/* 127 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int adjustOrPutValue(double key, int adjust_amount, int put_amount) {
/* 128 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableDoubleIntMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */