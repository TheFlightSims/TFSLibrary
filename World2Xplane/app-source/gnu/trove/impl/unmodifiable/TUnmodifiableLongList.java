/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TLongCollection;
/*     */ import gnu.trove.function.TLongFunction;
/*     */ import gnu.trove.list.TLongList;
/*     */ import gnu.trove.procedure.TLongProcedure;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class TUnmodifiableLongList extends TUnmodifiableLongCollection implements TLongList {
/*     */   static final long serialVersionUID = -283967356065247728L;
/*     */   
/*     */   final TLongList list;
/*     */   
/*     */   public TUnmodifiableLongList(TLongList list) {
/*  58 */     super((TLongCollection)list);
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
/*     */   public long get(int index) {
/*  65 */     return this.list.get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(long o) {
/*  66 */     return this.list.indexOf(o);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(long o) {
/*  67 */     return this.list.lastIndexOf(o);
/*     */   }
/*     */   
/*     */   public long[] toArray(int offset, int len) {
/*  70 */     return this.list.toArray(offset, len);
/*     */   }
/*     */   
/*     */   public long[] toArray(long[] dest, int offset, int len) {
/*  73 */     return this.list.toArray(dest, offset, len);
/*     */   }
/*     */   
/*     */   public long[] toArray(long[] dest, int source_pos, int dest_pos, int len) {
/*  76 */     return this.list.toArray(dest, source_pos, dest_pos, len);
/*     */   }
/*     */   
/*     */   public boolean forEachDescending(TLongProcedure procedure) {
/*  80 */     return this.list.forEachDescending(procedure);
/*     */   }
/*     */   
/*     */   public int binarySearch(long value) {
/*  83 */     return this.list.binarySearch(value);
/*     */   }
/*     */   
/*     */   public int binarySearch(long value, int fromIndex, int toIndex) {
/*  85 */     return this.list.binarySearch(value, fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public int indexOf(int offset, long value) {
/*  88 */     return this.list.indexOf(offset, value);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(int offset, long value) {
/*  89 */     return this.list.lastIndexOf(offset, value);
/*     */   }
/*     */   
/*     */   public TLongList grep(TLongProcedure condition) {
/*  90 */     return this.list.grep(condition);
/*     */   }
/*     */   
/*     */   public TLongList inverseGrep(TLongProcedure condition) {
/*  91 */     return this.list.inverseGrep(condition);
/*     */   }
/*     */   
/*     */   public long max() {
/*  93 */     return this.list.max();
/*     */   }
/*     */   
/*     */   public long min() {
/*  94 */     return this.list.min();
/*     */   }
/*     */   
/*     */   public long sum() {
/*  95 */     return this.list.sum();
/*     */   }
/*     */   
/*     */   public TLongList subList(int fromIndex, int toIndex) {
/*  98 */     return new TUnmodifiableLongList(this.list.subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 140 */     return (this.list instanceof java.util.RandomAccess) ? new TUnmodifiableRandomAccessLongList(this.list) : this;
/*     */   }
/*     */   
/*     */   public void add(long[] vals) {
/* 145 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void add(long[] vals, int offset, int length) {
/* 146 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public long removeAt(int offset) {
/* 148 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void remove(int offset, int length) {
/* 149 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, long value) {
/* 151 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, long[] values) {
/* 152 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, long[] values, int valOffset, int len) {
/* 153 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public long set(int offset, long val) {
/* 155 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void set(int offset, long[] values) {
/* 156 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void set(int offset, long[] values, int valOffset, int length) {
/* 157 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public long replace(int offset, long val) {
/* 159 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TLongFunction function) {
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
/*     */   public void fill(long val) {
/* 169 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void fill(int fromIndex, int toIndex, long val) {
/* 170 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableLongList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */