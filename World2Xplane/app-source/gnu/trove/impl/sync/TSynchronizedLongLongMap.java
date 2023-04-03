/*     */ package gnu.trove.impl.sync;
/*     */ 
/*     */ import gnu.trove.TLongCollection;
/*     */ import gnu.trove.function.TLongFunction;
/*     */ import gnu.trove.iterator.TLongLongIterator;
/*     */ import gnu.trove.map.TLongLongMap;
/*     */ import gnu.trove.procedure.TLongLongProcedure;
/*     */ import gnu.trove.procedure.TLongProcedure;
/*     */ import gnu.trove.set.TLongSet;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TSynchronizedLongLongMap implements TLongLongMap, Serializable {
/*     */   private static final long serialVersionUID = 1978198479659022715L;
/*     */   
/*     */   private final TLongLongMap m;
/*     */   
/*     */   final Object mutex;
/*     */   
/*     */   public TSynchronizedLongLongMap(TLongLongMap m) {
/*  59 */     if (m == null)
/*  60 */       throw new NullPointerException(); 
/*  61 */     this.m = m;
/*  62 */     this.mutex = this;
/*     */   }
/*     */   
/*     */   public TSynchronizedLongLongMap(TLongLongMap m, Object mutex) {
/*  66 */     this.m = m;
/*  67 */     this.mutex = mutex;
/*     */   }
/*     */   
/*     */   public int size() {
/*  71 */     synchronized (this.mutex) {
/*  71 */       return this.m.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  74 */     synchronized (this.mutex) {
/*  74 */       return this.m.isEmpty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(long key) {
/*  77 */     synchronized (this.mutex) {
/*  77 */       return this.m.containsKey(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsValue(long value) {
/*  80 */     synchronized (this.mutex) {
/*  80 */       return this.m.containsValue(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long get(long key) {
/*  83 */     synchronized (this.mutex) {
/*  83 */       return this.m.get(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long put(long key, long value) {
/*  87 */     synchronized (this.mutex) {
/*  87 */       return this.m.put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long remove(long key) {
/*  90 */     synchronized (this.mutex) {
/*  90 */       return this.m.remove(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Long, ? extends Long> map) {
/*  93 */     synchronized (this.mutex) {
/*  93 */       this.m.putAll(map);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(TLongLongMap map) {
/*  96 */     synchronized (this.mutex) {
/*  96 */       this.m.putAll(map);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/*  99 */     synchronized (this.mutex) {
/*  99 */       this.m.clear();
/*     */     } 
/*     */   }
/*     */   
/* 102 */   private transient TLongSet keySet = null;
/*     */   
/* 103 */   private transient TLongCollection values = null;
/*     */   
/*     */   public TLongSet keySet() {
/* 106 */     synchronized (this.mutex) {
/* 107 */       if (this.keySet == null)
/* 108 */         this.keySet = new TSynchronizedLongSet(this.m.keySet(), this.mutex); 
/* 109 */       return this.keySet;
/*     */     } 
/*     */   }
/*     */   
/*     */   public long[] keys() {
/* 113 */     synchronized (this.mutex) {
/* 113 */       return this.m.keys();
/*     */     } 
/*     */   }
/*     */   
/*     */   public long[] keys(long[] array) {
/* 116 */     synchronized (this.mutex) {
/* 116 */       return this.m.keys(array);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TLongCollection valueCollection() {
/* 120 */     synchronized (this.mutex) {
/* 121 */       if (this.values == null)
/* 122 */         this.values = new TSynchronizedLongCollection(this.m.valueCollection(), this.mutex); 
/* 123 */       return this.values;
/*     */     } 
/*     */   }
/*     */   
/*     */   public long[] values() {
/* 127 */     synchronized (this.mutex) {
/* 127 */       return this.m.values();
/*     */     } 
/*     */   }
/*     */   
/*     */   public long[] values(long[] array) {
/* 130 */     synchronized (this.mutex) {
/* 130 */       return this.m.values(array);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TLongLongIterator iterator() {
/* 134 */     return this.m.iterator();
/*     */   }
/*     */   
/*     */   public long getNoEntryKey() {
/* 138 */     return this.m.getNoEntryKey();
/*     */   }
/*     */   
/*     */   public long getNoEntryValue() {
/* 139 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public long putIfAbsent(long key, long value) {
/* 142 */     synchronized (this.mutex) {
/* 142 */       return this.m.putIfAbsent(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TLongProcedure procedure) {
/* 145 */     synchronized (this.mutex) {
/* 145 */       return this.m.forEachKey(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TLongProcedure procedure) {
/* 148 */     synchronized (this.mutex) {
/* 148 */       return this.m.forEachValue(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TLongLongProcedure procedure) {
/* 151 */     synchronized (this.mutex) {
/* 151 */       return this.m.forEachEntry(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transformValues(TLongFunction function) {
/* 154 */     synchronized (this.mutex) {
/* 154 */       this.m.transformValues(function);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TLongLongProcedure procedure) {
/* 157 */     synchronized (this.mutex) {
/* 157 */       return this.m.retainEntries(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean increment(long key) {
/* 160 */     synchronized (this.mutex) {
/* 160 */       return this.m.increment(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean adjustValue(long key, long amount) {
/* 163 */     synchronized (this.mutex) {
/* 163 */       return this.m.adjustValue(key, amount);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long adjustOrPutValue(long key, long adjust_amount, long put_amount) {
/* 166 */     synchronized (this.mutex) {
/* 166 */       return this.m.adjustOrPutValue(key, adjust_amount, put_amount);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 170 */     synchronized (this.mutex) {
/* 170 */       return this.m.equals(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 173 */     synchronized (this.mutex) {
/* 173 */       return this.m.hashCode();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 176 */     synchronized (this.mutex) {
/* 176 */       return this.m.toString();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 179 */     synchronized (this.mutex) {
/* 179 */       s.defaultWriteObject();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedLongLongMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */