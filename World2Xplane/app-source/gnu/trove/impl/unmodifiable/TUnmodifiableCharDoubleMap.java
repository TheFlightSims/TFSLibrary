/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCollections;
/*     */ import gnu.trove.TDoubleCollection;
/*     */ import gnu.trove.function.TDoubleFunction;
/*     */ import gnu.trove.iterator.TCharDoubleIterator;
/*     */ import gnu.trove.map.TCharDoubleMap;
/*     */ import gnu.trove.procedure.TCharDoubleProcedure;
/*     */ import gnu.trove.procedure.TCharProcedure;
/*     */ import gnu.trove.procedure.TDoubleProcedure;
/*     */ import gnu.trove.set.TCharSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TUnmodifiableCharDoubleMap implements TCharDoubleMap, Serializable {
/*     */   private static final long serialVersionUID = -1034234728574286014L;
/*     */   
/*     */   private final TCharDoubleMap m;
/*     */   
/*     */   public TUnmodifiableCharDoubleMap(TCharDoubleMap m) {
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
/*     */   public boolean containsKey(char key) {
/*  65 */     return this.m.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(double val) {
/*  66 */     return this.m.containsValue(val);
/*     */   }
/*     */   
/*     */   public double get(char key) {
/*  67 */     return this.m.get(key);
/*     */   }
/*     */   
/*     */   public double put(char key, double value) {
/*  69 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public double remove(char key) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(TCharDoubleMap m) {
/*  71 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Character, ? extends Double> map) {
/*  72 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  73 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*  75 */   private transient TCharSet keySet = null;
/*     */   
/*  76 */   private transient TDoubleCollection values = null;
/*     */   
/*     */   public TCharSet keySet() {
/*  79 */     if (this.keySet == null)
/*  80 */       this.keySet = TCollections.unmodifiableSet(this.m.keySet()); 
/*  81 */     return this.keySet;
/*     */   }
/*     */   
/*     */   public char[] keys() {
/*  83 */     return this.m.keys();
/*     */   }
/*     */   
/*     */   public char[] keys(char[] array) {
/*  84 */     return this.m.keys(array);
/*     */   }
/*     */   
/*     */   public TDoubleCollection valueCollection() {
/*  87 */     if (this.values == null)
/*  88 */       this.values = TCollections.unmodifiableCollection(this.m.valueCollection()); 
/*  89 */     return this.values;
/*     */   }
/*     */   
/*     */   public double[] values() {
/*  91 */     return this.m.values();
/*     */   }
/*     */   
/*     */   public double[] values(double[] array) {
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
/*     */   public char getNoEntryKey() {
/*  97 */     return this.m.getNoEntryKey();
/*     */   }
/*     */   
/*     */   public double getNoEntryValue() {
/*  98 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TCharProcedure procedure) {
/* 101 */     return this.m.forEachKey(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TDoubleProcedure procedure) {
/* 104 */     return this.m.forEachValue(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TCharDoubleProcedure procedure) {
/* 107 */     return this.m.forEachEntry(procedure);
/*     */   }
/*     */   
/*     */   public TCharDoubleIterator iterator() {
/* 111 */     return new TCharDoubleIterator() {
/* 112 */         TCharDoubleIterator iter = TUnmodifiableCharDoubleMap.this.m.iterator();
/*     */         
/*     */         public char key() {
/* 114 */           return this.iter.key();
/*     */         }
/*     */         
/*     */         public double value() {
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
/*     */         public double setValue(double val) {
/* 118 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 119 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public double putIfAbsent(char key, double value) {
/* 123 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TDoubleFunction function) {
/* 124 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TCharDoubleProcedure procedure) {
/* 125 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean increment(char key) {
/* 126 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean adjustValue(char key, double amount) {
/* 127 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public double adjustOrPutValue(char key, double adjust_amount, double put_amount) {
/* 128 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableCharDoubleMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */