/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TDoubleCollection;
/*     */ import gnu.trove.function.TDoubleFunction;
/*     */ import gnu.trove.list.TDoubleList;
/*     */ import gnu.trove.procedure.TDoubleProcedure;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class TUnmodifiableDoubleList extends TUnmodifiableDoubleCollection implements TDoubleList {
/*     */   static final long serialVersionUID = -283967356065247728L;
/*     */   
/*     */   final TDoubleList list;
/*     */   
/*     */   public TUnmodifiableDoubleList(TDoubleList list) {
/*  58 */     super((TDoubleCollection)list);
/*  59 */     this.list = list;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  62 */     return (o == this || this.list.equals(o));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  63 */     return this.list.hashCode();
/*     */   }
/*     */   
/*     */   public double get(int index) {
/*  65 */     return this.list.get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(double o) {
/*  66 */     return this.list.indexOf(o);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(double o) {
/*  67 */     return this.list.lastIndexOf(o);
/*     */   }
/*     */   
/*     */   public double[] toArray(int offset, int len) {
/*  70 */     return this.list.toArray(offset, len);
/*     */   }
/*     */   
/*     */   public double[] toArray(double[] dest, int offset, int len) {
/*  73 */     return this.list.toArray(dest, offset, len);
/*     */   }
/*     */   
/*     */   public double[] toArray(double[] dest, int source_pos, int dest_pos, int len) {
/*  76 */     return this.list.toArray(dest, source_pos, dest_pos, len);
/*     */   }
/*     */   
/*     */   public boolean forEachDescending(TDoubleProcedure procedure) {
/*  80 */     return this.list.forEachDescending(procedure);
/*     */   }
/*     */   
/*     */   public int binarySearch(double value) {
/*  83 */     return this.list.binarySearch(value);
/*     */   }
/*     */   
/*     */   public int binarySearch(double value, int fromIndex, int toIndex) {
/*  85 */     return this.list.binarySearch(value, fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public int indexOf(int offset, double value) {
/*  88 */     return this.list.indexOf(offset, value);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(int offset, double value) {
/*  89 */     return this.list.lastIndexOf(offset, value);
/*     */   }
/*     */   
/*     */   public TDoubleList grep(TDoubleProcedure condition) {
/*  90 */     return this.list.grep(condition);
/*     */   }
/*     */   
/*     */   public TDoubleList inverseGrep(TDoubleProcedure condition) {
/*  91 */     return this.list.inverseGrep(condition);
/*     */   }
/*     */   
/*     */   public double max() {
/*  93 */     return this.list.max();
/*     */   }
/*     */   
/*     */   public double min() {
/*  94 */     return this.list.min();
/*     */   }
/*     */   
/*     */   public double sum() {
/*  95 */     return this.list.sum();
/*     */   }
/*     */   
/*     */   public TDoubleList subList(int fromIndex, int toIndex) {
/*  98 */     return new TUnmodifiableDoubleList(this.list.subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 140 */     return (this.list instanceof java.util.RandomAccess) ? new TUnmodifiableRandomAccessDoubleList(this.list) : this;
/*     */   }
/*     */   
/*     */   public void add(double[] vals) {
/* 145 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void add(double[] vals, int offset, int length) {
/* 146 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public double removeAt(int offset) {
/* 148 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void remove(int offset, int length) {
/* 149 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, double value) {
/* 151 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, double[] values) {
/* 152 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, double[] values, int valOffset, int len) {
/* 153 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public double set(int offset, double val) {
/* 155 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void set(int offset, double[] values) {
/* 156 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void set(int offset, double[] values, int valOffset, int length) {
/* 157 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public double replace(int offset, double val) {
/* 159 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TDoubleFunction function) {
/* 161 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void reverse() {
/* 163 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void reverse(int from, int to) {
/* 164 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void shuffle(Random rand) {
/* 165 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void sort() {
/* 167 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void sort(int fromIndex, int toIndex) {
/* 168 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void fill(double val) {
/* 169 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void fill(int fromIndex, int toIndex, double val) {
/* 170 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableDoubleList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */