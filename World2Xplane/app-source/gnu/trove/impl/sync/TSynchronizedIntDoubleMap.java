/*     */ package gnu.trove.impl.sync;
/*     */ 
/*     */ import gnu.trove.TDoubleCollection;
/*     */ import gnu.trove.function.TDoubleFunction;
/*     */ import gnu.trove.iterator.TIntDoubleIterator;
/*     */ import gnu.trove.map.TIntDoubleMap;
/*     */ import gnu.trove.procedure.TDoubleProcedure;
/*     */ import gnu.trove.procedure.TIntDoubleProcedure;
/*     */ import gnu.trove.procedure.TIntProcedure;
/*     */ import gnu.trove.set.TIntSet;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TSynchronizedIntDoubleMap implements TIntDoubleMap, Serializable {
/*     */   private static final long serialVersionUID = 1978198479659022715L;
/*     */   
/*     */   private final TIntDoubleMap m;
/*     */   
/*     */   final Object mutex;
/*     */   
/*     */   public TSynchronizedIntDoubleMap(TIntDoubleMap m) {
/*  59 */     if (m == null)
/*  60 */       throw new NullPointerException(); 
/*  61 */     this.m = m;
/*  62 */     this.mutex = this;
/*     */   }
/*     */   
/*     */   public TSynchronizedIntDoubleMap(TIntDoubleMap m, Object mutex) {
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
/*     */   public boolean containsKey(int key) {
/*  77 */     synchronized (this.mutex) {
/*  77 */       return this.m.containsKey(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsValue(double value) {
/*  80 */     synchronized (this.mutex) {
/*  80 */       return this.m.containsValue(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double get(int key) {
/*  83 */     synchronized (this.mutex) {
/*  83 */       return this.m.get(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double put(int key, double value) {
/*  87 */     synchronized (this.mutex) {
/*  87 */       return this.m.put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double remove(int key) {
/*  90 */     synchronized (this.mutex) {
/*  90 */       return this.m.remove(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Integer, ? extends Double> map) {
/*  93 */     synchronized (this.mutex) {
/*  93 */       this.m.putAll(map);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(TIntDoubleMap map) {
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
/* 102 */   private transient TIntSet keySet = null;
/*     */   
/* 103 */   private transient TDoubleCollection values = null;
/*     */   
/*     */   public TIntSet keySet() {
/* 106 */     synchronized (this.mutex) {
/* 107 */       if (this.keySet == null)
/* 108 */         this.keySet = new TSynchronizedIntSet(this.m.keySet(), this.mutex); 
/* 109 */       return this.keySet;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] keys() {
/* 113 */     synchronized (this.mutex) {
/* 113 */       return this.m.keys();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] keys(int[] array) {
/* 116 */     synchronized (this.mutex) {
/* 116 */       return this.m.keys(array);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TDoubleCollection valueCollection() {
/* 120 */     synchronized (this.mutex) {
/* 121 */       if (this.values == null)
/* 122 */         this.values = new TSynchronizedDoubleCollection(this.m.valueCollection(), this.mutex); 
/* 123 */       return this.values;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] values() {
/* 127 */     synchronized (this.mutex) {
/* 127 */       return this.m.values();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] values(double[] array) {
/* 130 */     synchronized (this.mutex) {
/* 130 */       return this.m.values(array);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TIntDoubleIterator iterator() {
/* 134 */     return this.m.iterator();
/*     */   }
/*     */   
/*     */   public int getNoEntryKey() {
/* 138 */     return this.m.getNoEntryKey();
/*     */   }
/*     */   
/*     */   public double getNoEntryValue() {
/* 139 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public double putIfAbsent(int key, double value) {
/* 142 */     synchronized (this.mutex) {
/* 142 */       return this.m.putIfAbsent(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TIntProcedure procedure) {
/* 145 */     synchronized (this.mutex) {
/* 145 */       return this.m.forEachKey(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TDoubleProcedure procedure) {
/* 148 */     synchronized (this.mutex) {
/* 148 */       return this.m.forEachValue(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TIntDoubleProcedure procedure) {
/* 151 */     synchronized (this.mutex) {
/* 151 */       return this.m.forEachEntry(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transformValues(TDoubleFunction function) {
/* 154 */     synchronized (this.mutex) {
/* 154 */       this.m.transformValues(function);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TIntDoubleProcedure procedure) {
/* 157 */     synchronized (this.mutex) {
/* 157 */       return this.m.retainEntries(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean increment(int key) {
/* 160 */     synchronized (this.mutex) {
/* 160 */       return this.m.increment(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean adjustValue(int key, double amount) {
/* 163 */     synchronized (this.mutex) {
/* 163 */       return this.m.adjustValue(key, amount);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double adjustOrPutValue(int key, double adjust_amount, double put_amount) {
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedIntDoubleMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */