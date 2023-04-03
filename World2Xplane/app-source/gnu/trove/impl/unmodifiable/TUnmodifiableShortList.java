/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TShortCollection;
/*     */ import gnu.trove.function.TShortFunction;
/*     */ import gnu.trove.list.TShortList;
/*     */ import gnu.trove.procedure.TShortProcedure;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class TUnmodifiableShortList extends TUnmodifiableShortCollection implements TShortList {
/*     */   static final long serialVersionUID = -283967356065247728L;
/*     */   
/*     */   final TShortList list;
/*     */   
/*     */   public TUnmodifiableShortList(TShortList list) {
/*  58 */     super((TShortCollection)list);
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
/*     */   public short get(int index) {
/*  65 */     return this.list.get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(short o) {
/*  66 */     return this.list.indexOf(o);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(short o) {
/*  67 */     return this.list.lastIndexOf(o);
/*     */   }
/*     */   
/*     */   public short[] toArray(int offset, int len) {
/*  70 */     return this.list.toArray(offset, len);
/*     */   }
/*     */   
/*     */   public short[] toArray(short[] dest, int offset, int len) {
/*  73 */     return this.list.toArray(dest, offset, len);
/*     */   }
/*     */   
/*     */   public short[] toArray(short[] dest, int source_pos, int dest_pos, int len) {
/*  76 */     return this.list.toArray(dest, source_pos, dest_pos, len);
/*     */   }
/*     */   
/*     */   public boolean forEachDescending(TShortProcedure procedure) {
/*  80 */     return this.list.forEachDescending(procedure);
/*     */   }
/*     */   
/*     */   public int binarySearch(short value) {
/*  83 */     return this.list.binarySearch(value);
/*     */   }
/*     */   
/*     */   public int binarySearch(short value, int fromIndex, int toIndex) {
/*  85 */     return this.list.binarySearch(value, fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public int indexOf(int offset, short value) {
/*  88 */     return this.list.indexOf(offset, value);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(int offset, short value) {
/*  89 */     return this.list.lastIndexOf(offset, value);
/*     */   }
/*     */   
/*     */   public TShortList grep(TShortProcedure condition) {
/*  90 */     return this.list.grep(condition);
/*     */   }
/*     */   
/*     */   public TShortList inverseGrep(TShortProcedure condition) {
/*  91 */     return this.list.inverseGrep(condition);
/*     */   }
/*     */   
/*     */   public short max() {
/*  93 */     return this.list.max();
/*     */   }
/*     */   
/*     */   public short min() {
/*  94 */     return this.list.min();
/*     */   }
/*     */   
/*     */   public short sum() {
/*  95 */     return this.list.sum();
/*     */   }
/*     */   
/*     */   public TShortList subList(int fromIndex, int toIndex) {
/*  98 */     return new TUnmodifiableShortList(this.list.subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 140 */     return (this.list instanceof java.util.RandomAccess) ? new TUnmodifiableRandomAccessShortList(this.list) : this;
/*     */   }
/*     */   
/*     */   public void add(short[] vals) {
/* 145 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void add(short[] vals, int offset, int length) {
/* 146 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public short removeAt(int offset) {
/* 148 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void remove(int offset, int length) {
/* 149 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, short value) {
/* 151 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, short[] values) {
/* 152 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, short[] values, int valOffset, int len) {
/* 153 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public short set(int offset, short val) {
/* 155 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void set(int offset, short[] values) {
/* 156 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void set(int offset, short[] values, int valOffset, int length) {
/* 157 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public short replace(int offset, short val) {
/* 159 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TShortFunction function) {
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
/*     */   public void fill(short val) {
/* 169 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void fill(int fromIndex, int toIndex, short val) {
/* 170 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableShortList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */