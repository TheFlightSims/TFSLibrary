/*     */ package gnu.trove.impl.sync;
/*     */ 
/*     */ import gnu.trove.TByteCollection;
/*     */ import gnu.trove.function.TByteFunction;
/*     */ import gnu.trove.iterator.TCharByteIterator;
/*     */ import gnu.trove.map.TCharByteMap;
/*     */ import gnu.trove.procedure.TByteProcedure;
/*     */ import gnu.trove.procedure.TCharByteProcedure;
/*     */ import gnu.trove.procedure.TCharProcedure;
/*     */ import gnu.trove.set.TCharSet;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TSynchronizedCharByteMap implements TCharByteMap, Serializable {
/*     */   private static final long serialVersionUID = 1978198479659022715L;
/*     */   
/*     */   private final TCharByteMap m;
/*     */   
/*     */   final Object mutex;
/*     */   
/*     */   public TSynchronizedCharByteMap(TCharByteMap m) {
/*  59 */     if (m == null)
/*  60 */       throw new NullPointerException(); 
/*  61 */     this.m = m;
/*  62 */     this.mutex = this;
/*     */   }
/*     */   
/*     */   public TSynchronizedCharByteMap(TCharByteMap m, Object mutex) {
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
/*     */   public boolean containsKey(char key) {
/*  77 */     synchronized (this.mutex) {
/*  77 */       return this.m.containsKey(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsValue(byte value) {
/*  80 */     synchronized (this.mutex) {
/*  80 */       return this.m.containsValue(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte get(char key) {
/*  83 */     synchronized (this.mutex) {
/*  83 */       return this.m.get(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte put(char key, byte value) {
/*  87 */     synchronized (this.mutex) {
/*  87 */       return this.m.put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte remove(char key) {
/*  90 */     synchronized (this.mutex) {
/*  90 */       return this.m.remove(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Character, ? extends Byte> map) {
/*  93 */     synchronized (this.mutex) {
/*  93 */       this.m.putAll(map);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putAll(TCharByteMap map) {
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
/* 102 */   private transient TCharSet keySet = null;
/*     */   
/* 103 */   private transient TByteCollection values = null;
/*     */   
/*     */   public TCharSet keySet() {
/* 106 */     synchronized (this.mutex) {
/* 107 */       if (this.keySet == null)
/* 108 */         this.keySet = new TSynchronizedCharSet(this.m.keySet(), this.mutex); 
/* 109 */       return this.keySet;
/*     */     } 
/*     */   }
/*     */   
/*     */   public char[] keys() {
/* 113 */     synchronized (this.mutex) {
/* 113 */       return this.m.keys();
/*     */     } 
/*     */   }
/*     */   
/*     */   public char[] keys(char[] array) {
/* 116 */     synchronized (this.mutex) {
/* 116 */       return this.m.keys(array);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TByteCollection valueCollection() {
/* 120 */     synchronized (this.mutex) {
/* 121 */       if (this.values == null)
/* 122 */         this.values = new TSynchronizedByteCollection(this.m.valueCollection(), this.mutex); 
/* 123 */       return this.values;
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] values() {
/* 127 */     synchronized (this.mutex) {
/* 127 */       return this.m.values();
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] values(byte[] array) {
/* 130 */     synchronized (this.mutex) {
/* 130 */       return this.m.values(array);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TCharByteIterator iterator() {
/* 134 */     return this.m.iterator();
/*     */   }
/*     */   
/*     */   public char getNoEntryKey() {
/* 138 */     return this.m.getNoEntryKey();
/*     */   }
/*     */   
/*     */   public byte getNoEntryValue() {
/* 139 */     return this.m.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public byte putIfAbsent(char key, byte value) {
/* 142 */     synchronized (this.mutex) {
/* 142 */       return this.m.putIfAbsent(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachKey(TCharProcedure procedure) {
/* 145 */     synchronized (this.mutex) {
/* 145 */       return this.m.forEachKey(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TByteProcedure procedure) {
/* 148 */     synchronized (this.mutex) {
/* 148 */       return this.m.forEachValue(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachEntry(TCharByteProcedure procedure) {
/* 151 */     synchronized (this.mutex) {
/* 151 */       return this.m.forEachEntry(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transformValues(TByteFunction function) {
/* 154 */     synchronized (this.mutex) {
/* 154 */       this.m.transformValues(function);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainEntries(TCharByteProcedure procedure) {
/* 157 */     synchronized (this.mutex) {
/* 157 */       return this.m.retainEntries(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean increment(char key) {
/* 160 */     synchronized (this.mutex) {
/* 160 */       return this.m.increment(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean adjustValue(char key, byte amount) {
/* 163 */     synchronized (this.mutex) {
/* 163 */       return this.m.adjustValue(key, amount);
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte adjustOrPutValue(char key, byte adjust_amount, byte put_amount) {
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedCharByteMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */