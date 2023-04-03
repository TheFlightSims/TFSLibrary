/*     */ package gnu.trove.impl.sync;
/*     */ 
/*     */ import gnu.trove.TDoubleCollection;
/*     */ import gnu.trove.function.TDoubleFunction;
/*     */ import gnu.trove.list.TDoubleList;
/*     */ import gnu.trove.procedure.TDoubleProcedure;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class TSynchronizedDoubleList extends TSynchronizedDoubleCollection implements TDoubleList {
/*     */   static final long serialVersionUID = -7754090372962971524L;
/*     */   
/*     */   final TDoubleList list;
/*     */   
/*     */   public TSynchronizedDoubleList(TDoubleList list) {
/*  60 */     super((TDoubleCollection)list);
/*  61 */     this.list = list;
/*     */   }
/*     */   
/*     */   public TSynchronizedDoubleList(TDoubleList list, Object mutex) {
/*  64 */     super((TDoubleCollection)list, mutex);
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
/*     */   public double get(int index) {
/*  76 */     synchronized (this.mutex) {
/*  76 */       return this.list.get(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double set(int index, double element) {
/*  79 */     synchronized (this.mutex) {
/*  79 */       return this.list.set(index, element);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(int offset, double[] values) {
/*  82 */     synchronized (this.mutex) {
/*  82 */       this.list.set(offset, values);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(int offset, double[] values, int valOffset, int length) {
/*  85 */     synchronized (this.mutex) {
/*  85 */       this.list.set(offset, values, valOffset, length);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double replace(int offset, double val) {
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
/*     */   public double removeAt(int offset) {
/*  95 */     synchronized (this.mutex) {
/*  95 */       return this.list.removeAt(offset);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(double[] vals) {
/*  99 */     synchronized (this.mutex) {
/*  99 */       this.list.add(vals);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(double[] vals, int offset, int length) {
/* 102 */     synchronized (this.mutex) {
/* 102 */       this.list.add(vals, offset, length);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void insert(int offset, double value) {
/* 106 */     synchronized (this.mutex) {
/* 106 */       this.list.insert(offset, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void insert(int offset, double[] values) {
/* 109 */     synchronized (this.mutex) {
/* 109 */       this.list.insert(offset, values);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void insert(int offset, double[] values, int valOffset, int len) {
/* 112 */     synchronized (this.mutex) {
/* 112 */       this.list.insert(offset, values, valOffset, len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int indexOf(double o) {
/* 116 */     synchronized (this.mutex) {
/* 116 */       return this.list.indexOf(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int lastIndexOf(double o) {
/* 119 */     synchronized (this.mutex) {
/* 119 */       return this.list.lastIndexOf(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TDoubleList subList(int fromIndex, int toIndex) {
/* 131 */     synchronized (this.mutex) {
/* 132 */       return new TSynchronizedDoubleList(this.list.subList(fromIndex, toIndex), this.mutex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] toArray(int offset, int len) {
/* 138 */     synchronized (this.mutex) {
/* 138 */       return this.list.toArray(offset, len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] toArray(double[] dest, int offset, int len) {
/* 141 */     synchronized (this.mutex) {
/* 141 */       return this.list.toArray(dest, offset, len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] toArray(double[] dest, int source_pos, int dest_pos, int len) {
/* 144 */     synchronized (this.mutex) {
/* 144 */       return this.list.toArray(dest, source_pos, dest_pos, len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int indexOf(int offset, double value) {
/* 148 */     synchronized (this.mutex) {
/* 148 */       return this.list.indexOf(offset, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int lastIndexOf(int offset, double value) {
/* 151 */     synchronized (this.mutex) {
/* 151 */       return this.list.lastIndexOf(offset, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fill(double val) {
/* 155 */     synchronized (this.mutex) {
/* 155 */       this.list.fill(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fill(int fromIndex, int toIndex, double val) {
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
/*     */   public int binarySearch(double value) {
/* 180 */     synchronized (this.mutex) {
/* 180 */       return this.list.binarySearch(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int binarySearch(double value, int fromIndex, int toIndex) {
/* 183 */     synchronized (this.mutex) {
/* 183 */       return this.list.binarySearch(value, fromIndex, toIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TDoubleList grep(TDoubleProcedure condition) {
/* 187 */     synchronized (this.mutex) {
/* 187 */       return this.list.grep(condition);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TDoubleList inverseGrep(TDoubleProcedure condition) {
/* 190 */     synchronized (this.mutex) {
/* 190 */       return this.list.inverseGrep(condition);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double max() {
/* 193 */     synchronized (this.mutex) {
/* 193 */       return this.list.max();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double min() {
/* 194 */     synchronized (this.mutex) {
/* 194 */       return this.list.min();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double sum() {
/* 195 */     synchronized (this.mutex) {
/* 195 */       return this.list.sum();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachDescending(TDoubleProcedure procedure) {
/* 198 */     synchronized (this.mutex) {
/* 198 */       return this.list.forEachDescending(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transformValues(TDoubleFunction function) {
/* 202 */     synchronized (this.mutex) {
/* 202 */       this.list.transformValues(function);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 218 */     return (this.list instanceof java.util.RandomAccess) ? new TSynchronizedRandomAccessDoubleList(this.list) : this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedDoubleList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */