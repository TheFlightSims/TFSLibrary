/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TFloatCollection;
/*     */ import gnu.trove.function.TFloatFunction;
/*     */ import gnu.trove.list.TFloatList;
/*     */ import gnu.trove.procedure.TFloatProcedure;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class TUnmodifiableFloatList extends TUnmodifiableFloatCollection implements TFloatList {
/*     */   static final long serialVersionUID = -283967356065247728L;
/*     */   
/*     */   final TFloatList list;
/*     */   
/*     */   public TUnmodifiableFloatList(TFloatList list) {
/*  58 */     super((TFloatCollection)list);
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
/*     */   public float get(int index) {
/*  65 */     return this.list.get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(float o) {
/*  66 */     return this.list.indexOf(o);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(float o) {
/*  67 */     return this.list.lastIndexOf(o);
/*     */   }
/*     */   
/*     */   public float[] toArray(int offset, int len) {
/*  70 */     return this.list.toArray(offset, len);
/*     */   }
/*     */   
/*     */   public float[] toArray(float[] dest, int offset, int len) {
/*  73 */     return this.list.toArray(dest, offset, len);
/*     */   }
/*     */   
/*     */   public float[] toArray(float[] dest, int source_pos, int dest_pos, int len) {
/*  76 */     return this.list.toArray(dest, source_pos, dest_pos, len);
/*     */   }
/*     */   
/*     */   public boolean forEachDescending(TFloatProcedure procedure) {
/*  80 */     return this.list.forEachDescending(procedure);
/*     */   }
/*     */   
/*     */   public int binarySearch(float value) {
/*  83 */     return this.list.binarySearch(value);
/*     */   }
/*     */   
/*     */   public int binarySearch(float value, int fromIndex, int toIndex) {
/*  85 */     return this.list.binarySearch(value, fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public int indexOf(int offset, float value) {
/*  88 */     return this.list.indexOf(offset, value);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(int offset, float value) {
/*  89 */     return this.list.lastIndexOf(offset, value);
/*     */   }
/*     */   
/*     */   public TFloatList grep(TFloatProcedure condition) {
/*  90 */     return this.list.grep(condition);
/*     */   }
/*     */   
/*     */   public TFloatList inverseGrep(TFloatProcedure condition) {
/*  91 */     return this.list.inverseGrep(condition);
/*     */   }
/*     */   
/*     */   public float max() {
/*  93 */     return this.list.max();
/*     */   }
/*     */   
/*     */   public float min() {
/*  94 */     return this.list.min();
/*     */   }
/*     */   
/*     */   public float sum() {
/*  95 */     return this.list.sum();
/*     */   }
/*     */   
/*     */   public TFloatList subList(int fromIndex, int toIndex) {
/*  98 */     return new TUnmodifiableFloatList(this.list.subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 140 */     return (this.list instanceof java.util.RandomAccess) ? new TUnmodifiableRandomAccessFloatList(this.list) : this;
/*     */   }
/*     */   
/*     */   public void add(float[] vals) {
/* 145 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void add(float[] vals, int offset, int length) {
/* 146 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public float removeAt(int offset) {
/* 148 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void remove(int offset, int length) {
/* 149 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, float value) {
/* 151 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, float[] values) {
/* 152 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, float[] values, int valOffset, int len) {
/* 153 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public float set(int offset, float val) {
/* 155 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void set(int offset, float[] values) {
/* 156 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void set(int offset, float[] values, int valOffset, int length) {
/* 157 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public float replace(int offset, float val) {
/* 159 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TFloatFunction function) {
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
/*     */   public void fill(float val) {
/* 169 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void fill(int fromIndex, int toIndex, float val) {
/* 170 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableFloatList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */