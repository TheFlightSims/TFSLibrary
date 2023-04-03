/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCharCollection;
/*     */ import gnu.trove.TCollections;
/*     */ import gnu.trove.function.TCharFunction;
/*     */ import gnu.trove.iterator.TFloatCharIterator;
/*     */ import gnu.trove.map.TFloatCharMap;
/*     */ import gnu.trove.procedure.TCharProcedure;
/*     */ import gnu.trove.procedure.TFloatCharProcedure;
/*     */ import gnu.trove.procedure.TFloatProcedure;
/*     */ import gnu.trove.set.TFloatSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TUnmodifiableFloatCharMap implements TFloatCharMap, Serializable {
/*     */   private static final long serialVersionUID = -1034234728574286014L;
/*     */   
/*     */   private final TFloatCharMap m;
/*     */   
/*     */   public TUnmodifiableFloatCharMap(TFloatCharMap m) {
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
/*     */   public boolean containsKey(float key) {
/*  65 */     return this.m.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(char val) {
/*  66 */     return this.m.containsValue(val);
/*     */   }
/*     */   
/*     */   public char get(float key) {
/*  67 */     return this.m.get(key);
/*     */   }
/*     */   
/*     */   public char put(float key, char value) {
/*  69 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public char remove(float key) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(TFloatCharMap m) {
/*  71 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Float, ? extends Character> map) {
/*  72 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  73 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*  75 */   private transient TFloatSet keySet = null;
/*     */   
/*  76 */   private transient TCharCollection values = null;
/*     */   
/*     */   public TFloatSet keySet() {
/*  79 */     if (this.keySet == null)
/*  80 */       this.keySet = TCollections.unmodifiableSet(this.m.keySet()); 
/*  81 */     return this.keySet;
/*     */   }
/*     */   
/*     */   public float[] keys() {
/*  83 */     return this.m.keys();
/*     */   }
/*     */   
/*     */   public float[] keys(float[] array) {
/*  84 */     return this.m.keys(array);
/*     */   }
/*     */   
/*     */   public TCharCollection valueCollection() {
/*  87 */     if (this.values == null)
/*  88 */       this.values = TCollections.unmodifiableCollection(this.m.valueCollection()); 
/*  89 */     return this.values;
/*     */   }
/*     */   
/*     */   public char[] values() {
/*  91 */     return this.m.values();
/*     */   }
/*     */   
/*     */   public char[] values(char[] array) {
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
/*     */   public float getNoEntryKey() {
/*  97 */     return this.m.getNoEntryKey();
/*     */   }
/*     */   
/*     */   public char getNoEntryValue() {
/*  98 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TFloatProcedure procedure) {
/* 101 */     return this.m.forEachKey(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TCharProcedure procedure) {
/* 104 */     return this.m.forEachValue(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TFloatCharProcedure procedure) {
/* 107 */     return this.m.forEachEntry(procedure);
/*     */   }
/*     */   
/*     */   public TFloatCharIterator iterator() {
/* 111 */     return new TFloatCharIterator() {
/* 112 */         TFloatCharIterator iter = TUnmodifiableFloatCharMap.this.m.iterator();
/*     */         
/*     */         public float key() {
/* 114 */           return this.iter.key();
/*     */         }
/*     */         
/*     */         public char value() {
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
/*     */         public char setValue(char val) {
/* 118 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 119 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public char putIfAbsent(float key, char value) {
/* 123 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TCharFunction function) {
/* 124 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TFloatCharProcedure procedure) {
/* 125 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean increment(float key) {
/* 126 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean adjustValue(float key, char amount) {
/* 127 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public char adjustOrPutValue(float key, char adjust_amount, char put_amount) {
/* 128 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableFloatCharMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */