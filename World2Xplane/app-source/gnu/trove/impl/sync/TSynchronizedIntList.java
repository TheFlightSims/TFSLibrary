/*     */ package gnu.trove.impl.sync;
/*     */ 
/*     */ import gnu.trove.TIntCollection;
/*     */ import gnu.trove.function.TIntFunction;
/*     */ import gnu.trove.list.TIntList;
/*     */ import gnu.trove.procedure.TIntProcedure;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class TSynchronizedIntList extends TSynchronizedIntCollection implements TIntList {
/*     */   static final long serialVersionUID = -7754090372962971524L;
/*     */   
/*     */   final TIntList list;
/*     */   
/*     */   public TSynchronizedIntList(TIntList list) {
/*  60 */     super((TIntCollection)list);
/*  61 */     this.list = list;
/*     */   }
/*     */   
/*     */   public TSynchronizedIntList(TIntList list, Object mutex) {
/*  64 */     super((TIntCollection)list, mutex);
/*  65 */     this.list = list;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  69 */     synchronized (this.mutex) {
/*  69 */       return this.list.equals(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  72 */     synchronized (this.mutex) {
/*  72 */       return this.list.hashCode();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int get(int index) {
/*  76 */     synchronized (this.mutex) {
/*  76 */       return this.list.get(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int set(int index, int element) {
/*  79 */     synchronized (this.mutex) {
/*  79 */       return this.list.set(index, element);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(int offset, int[] values) {
/*  82 */     synchronized (this.mutex) {
/*  82 */       this.list.set(offset, values);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(int offset, int[] values, int valOffset, int length) {
/*  85 */     synchronized (this.mutex) {
/*  85 */       this.list.set(offset, values, valOffset, length);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int replace(int offset, int val) {
/*  89 */     synchronized (this.mutex) {
/*  89 */       return this.list.replace(offset, val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove(int offset, int length) {
/*  92 */     synchronized (this.mutex) {
/*  92 */       this.list.remove(offset, length);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int removeAt(int offset) {
/*  95 */     synchronized (this.mutex) {
/*  95 */       return this.list.removeAt(offset);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(int[] vals) {
/*  99 */     synchronized (this.mutex) {
/*  99 */       this.list.add(vals);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(int[] vals, int offset, int length) {
/* 102 */     synchronized (this.mutex) {
/* 102 */       this.list.add(vals, offset, length);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void insert(int offset, int value) {
/* 106 */     synchronized (this.mutex) {
/* 106 */       this.list.insert(offset, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void insert(int offset, int[] values) {
/* 109 */     synchronized (this.mutex) {
/* 109 */       this.list.insert(offset, values);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void insert(int offset, int[] values, int valOffset, int len) {
/* 112 */     synchronized (this.mutex) {
/* 112 */       this.list.insert(offset, values, valOffset, len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int indexOf(int o) {
/* 116 */     synchronized (this.mutex) {
/* 116 */       return this.list.indexOf(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int lastIndexOf(int o) {
/* 119 */     synchronized (this.mutex) {
/* 119 */       return this.list.lastIndexOf(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TIntList subList(int fromIndex, int toIndex) {
/* 131 */     synchronized (this.mutex) {
/* 132 */       return new TSynchronizedIntList(this.list.subList(fromIndex, toIndex), this.mutex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] toArray(int offset, int len) {
/* 138 */     synchronized (this.mutex) {
/* 138 */       return this.list.toArray(offset, len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] toArray(int[] dest, int offset, int len) {
/* 141 */     synchronized (this.mutex) {
/* 141 */       return this.list.toArray(dest, offset, len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] toArray(int[] dest, int source_pos, int dest_pos, int len) {
/* 144 */     synchronized (this.mutex) {
/* 144 */       return this.list.toArray(dest, source_pos, dest_pos, len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int indexOf(int offset, int value) {
/* 148 */     synchronized (this.mutex) {
/* 148 */       return this.list.indexOf(offset, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int lastIndexOf(int offset, int value) {
/* 151 */     synchronized (this.mutex) {
/* 151 */       return this.list.lastIndexOf(offset, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fill(int val) {
/* 155 */     synchronized (this.mutex) {
/* 155 */       this.list.fill(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fill(int fromIndex, int toIndex, int val) {
/* 158 */     synchronized (this.mutex) {
/* 158 */       this.list.fill(fromIndex, toIndex, val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reverse() {
/* 162 */     synchronized (this.mutex) {
/* 162 */       this.list.reverse();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reverse(int from, int to) {
/* 165 */     synchronized (this.mutex) {
/* 165 */       this.list.reverse(from, to);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void shuffle(Random rand) {
/* 169 */     synchronized (this.mutex) {
/* 169 */       this.list.shuffle(rand);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sort() {
/* 173 */     synchronized (this.mutex) {
/* 173 */       this.list.sort();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sort(int fromIndex, int toIndex) {
/* 176 */     synchronized (this.mutex) {
/* 176 */       this.list.sort(fromIndex, toIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int binarySearch(int value) {
/* 180 */     synchronized (this.mutex) {
/* 180 */       return this.list.binarySearch(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int binarySearch(int value, int fromIndex, int toIndex) {
/* 183 */     synchronized (this.mutex) {
/* 183 */       return this.list.binarySearch(value, fromIndex, toIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TIntList grep(TIntProcedure condition) {
/* 187 */     synchronized (this.mutex) {
/* 187 */       return this.list.grep(condition);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TIntList inverseGrep(TIntProcedure condition) {
/* 190 */     synchronized (this.mutex) {
/* 190 */       return this.list.inverseGrep(condition);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int max() {
/* 193 */     synchronized (this.mutex) {
/* 193 */       return this.list.max();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int min() {
/* 194 */     synchronized (this.mutex) {
/* 194 */       return this.list.min();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int sum() {
/* 195 */     synchronized (this.mutex) {
/* 195 */       return this.list.sum();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachDescending(TIntProcedure procedure) {
/* 198 */     synchronized (this.mutex) {
/* 198 */       return this.list.forEachDescending(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transformValues(TIntFunction function) {
/* 202 */     synchronized (this.mutex) {
/* 202 */       this.list.transformValues(function);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 218 */     return (this.list instanceof java.util.RandomAccess) ? new TSynchronizedRandomAccessIntList(this.list) : this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedIntList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */