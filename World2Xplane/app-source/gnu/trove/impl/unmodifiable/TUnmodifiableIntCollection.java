/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TIntCollection;
/*     */ import gnu.trove.iterator.TIntIterator;
/*     */ import gnu.trove.procedure.TIntProcedure;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class TUnmodifiableIntCollection implements TIntCollection, Serializable {
/*     */   private static final long serialVersionUID = 1820017752578914078L;
/*     */   
/*     */   final TIntCollection c;
/*     */   
/*     */   public TUnmodifiableIntCollection(TIntCollection c) {
/*  58 */     if (c == null)
/*  59 */       throw new NullPointerException(); 
/*  60 */     this.c = c;
/*     */   }
/*     */   
/*     */   public int size() {
/*  63 */     return this.c.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  64 */     return this.c.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean contains(int o) {
/*  65 */     return this.c.contains(o);
/*     */   }
/*     */   
/*     */   public int[] toArray() {
/*  66 */     return this.c.toArray();
/*     */   }
/*     */   
/*     */   public int[] toArray(int[] a) {
/*  67 */     return this.c.toArray(a);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  68 */     return this.c.toString();
/*     */   }
/*     */   
/*     */   public int getNoEntryValue() {
/*  69 */     return this.c.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEach(TIntProcedure procedure) {
/*  70 */     return this.c.forEach(procedure);
/*     */   }
/*     */   
/*     */   public TIntIterator iterator() {
/*  73 */     return new TIntIterator() {
/*  74 */         TIntIterator i = TUnmodifiableIntCollection.this.c.iterator();
/*     */         
/*     */         public boolean hasNext() {
/*  76 */           return this.i.hasNext();
/*     */         }
/*     */         
/*     */         public int next() {
/*  77 */           return this.i.next();
/*     */         }
/*     */         
/*     */         public void remove() {
/*  78 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean add(int e) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(int o) {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> coll) {
/*  85 */     return this.c.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean containsAll(TIntCollection coll) {
/*  86 */     return this.c.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean containsAll(int[] array) {
/*  87 */     return this.c.containsAll(array);
/*     */   }
/*     */   
/*     */   public boolean addAll(TIntCollection coll) {
/*  89 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends Integer> coll) {
/*  90 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(int[] array) {
/*  91 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/*  93 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(TIntCollection coll) {
/*  94 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(int[] array) {
/*  95 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/*  97 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(TIntCollection coll) {
/*  98 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(int[] array) {
/*  99 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 101 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableIntCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */