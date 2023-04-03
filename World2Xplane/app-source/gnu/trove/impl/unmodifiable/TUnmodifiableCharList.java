/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCharCollection;
/*     */ import gnu.trove.function.TCharFunction;
/*     */ import gnu.trove.list.TCharList;
/*     */ import gnu.trove.procedure.TCharProcedure;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class TUnmodifiableCharList extends TUnmodifiableCharCollection implements TCharList {
/*     */   static final long serialVersionUID = -283967356065247728L;
/*     */   
/*     */   final TCharList list;
/*     */   
/*     */   public TUnmodifiableCharList(TCharList list) {
/*  58 */     super((TCharCollection)list);
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
/*     */   public char get(int index) {
/*  65 */     return this.list.get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(char o) {
/*  66 */     return this.list.indexOf(o);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(char o) {
/*  67 */     return this.list.lastIndexOf(o);
/*     */   }
/*     */   
/*     */   public char[] toArray(int offset, int len) {
/*  70 */     return this.list.toArray(offset, len);
/*     */   }
/*     */   
/*     */   public char[] toArray(char[] dest, int offset, int len) {
/*  73 */     return this.list.toArray(dest, offset, len);
/*     */   }
/*     */   
/*     */   public char[] toArray(char[] dest, int source_pos, int dest_pos, int len) {
/*  76 */     return this.list.toArray(dest, source_pos, dest_pos, len);
/*     */   }
/*     */   
/*     */   public boolean forEachDescending(TCharProcedure procedure) {
/*  80 */     return this.list.forEachDescending(procedure);
/*     */   }
/*     */   
/*     */   public int binarySearch(char value) {
/*  83 */     return this.list.binarySearch(value);
/*     */   }
/*     */   
/*     */   public int binarySearch(char value, int fromIndex, int toIndex) {
/*  85 */     return this.list.binarySearch(value, fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public int indexOf(int offset, char value) {
/*  88 */     return this.list.indexOf(offset, value);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(int offset, char value) {
/*  89 */     return this.list.lastIndexOf(offset, value);
/*     */   }
/*     */   
/*     */   public TCharList grep(TCharProcedure condition) {
/*  90 */     return this.list.grep(condition);
/*     */   }
/*     */   
/*     */   public TCharList inverseGrep(TCharProcedure condition) {
/*  91 */     return this.list.inverseGrep(condition);
/*     */   }
/*     */   
/*     */   public char max() {
/*  93 */     return this.list.max();
/*     */   }
/*     */   
/*     */   public char min() {
/*  94 */     return this.list.min();
/*     */   }
/*     */   
/*     */   public char sum() {
/*  95 */     return this.list.sum();
/*     */   }
/*     */   
/*     */   public TCharList subList(int fromIndex, int toIndex) {
/*  98 */     return new TUnmodifiableCharList(this.list.subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 140 */     return (this.list instanceof java.util.RandomAccess) ? new TUnmodifiableRandomAccessCharList(this.list) : this;
/*     */   }
/*     */   
/*     */   public void add(char[] vals) {
/* 145 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void add(char[] vals, int offset, int length) {
/* 146 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public char removeAt(int offset) {
/* 148 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void remove(int offset, int length) {
/* 149 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, char value) {
/* 151 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, char[] values) {
/* 152 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void insert(int offset, char[] values, int valOffset, int len) {
/* 153 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public char set(int offset, char val) {
/* 155 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void set(int offset, char[] values) {
/* 156 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void set(int offset, char[] values, int valOffset, int length) {
/* 157 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public char replace(int offset, char val) {
/* 159 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void transformValues(TCharFunction function) {
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
/*     */   public void fill(char val) {
/* 169 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void fill(int fromIndex, int toIndex, char val) {
/* 170 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableCharList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */