/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCollections;
/*     */ import gnu.trove.TShortCollection;
/*     */ import gnu.trove.function.TShortFunction;
/*     */ import gnu.trove.iterator.TCharShortIterator;
/*     */ import gnu.trove.map.TCharShortMap;
/*     */ import gnu.trove.procedure.TCharProcedure;
/*     */ import gnu.trove.procedure.TCharShortProcedure;
/*     */ import gnu.trove.procedure.TShortProcedure;
/*     */ import gnu.trove.set.TCharSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TUnmodifiableCharShortMap implements TCharShortMap, Serializable {
/*     */   private static final long serialVersionUID = -1034234728574286014L;
/*     */   
/*     */   private final TCharShortMap m;
/*     */   
/*     */   public TUnmodifiableCharShortMap(TCharShortMap m) {
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
/*     */   public boolean containsValue(short val) {
/*  66 */     return this.m.containsValue(val);
/*     */   }
/*     */   
/*     */   public short get(char key) {
/*  67 */     return this.m.get(key);
/*     */   }
/*     */   
/*     */   public short put(char key, short value) {
/*  69 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public short remove(char key) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(TCharShortMap m) {
/*  71 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Character, ? extends Short> map) {
/*  72 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  73 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*  75 */   private transient TCharSet keySet = null;
/*     */   
/*  76 */   private transient TShortCollection values = null;
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
/*     */   public TShortCollection valueCollection() {
/*  87 */     if (this.values == null)
/*  88 */       this.values = TCollections.unmodifiableCollection(this.m.valueCollection()); 
/*  89 */     return this.values;
/*     */   }
/*     */   
/*     */   public short[] values() {
/*  91 */     return this.m.values();
/*     */   }
/*     */   
/*     */   public short[] values(short[] array) {
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
/*     */   public short getNoEntryValue() {
/*  98 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TCharProcedure procedure) {
/* 101 */     return this.m.forEachKey(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TShortProcedure procedure) {
/* 104 */     return this.m.forEachValue(procedure);
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TCharShortProcedure procedure) {
/* 107 */     return this.m.forEachEntry(procedure);
/*     */   }
/*     */   
/*     */   public TCharShortIterator iterator() {
/* 111 */     return new TCharShortIterator() {
/* 112 */         TCharShortIterator iter = TUnmodifiableCharShortMap.this.m.iterator();
/*     */         
/*     */         public char key() {
/* 114 */           return this.iter.key();
/*     */         }
/*     */         
/*     */         public short value() {
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
/*     */         public short setValue(short val) {
/* 118 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 119 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public short putIfAbsent(char key, short value) {
/* 123 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TShortFunction function) {
/* 124 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TCharShortProcedure procedure) {
/* 125 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean increment(char key) {
/* 126 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean adjustValue(char key, short amount) {
/* 127 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public short adjustOrPutValue(char key, short adjust_amount, short put_amount) {
/* 128 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableCharShortMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */