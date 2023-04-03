/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TLongCollection;
/*     */ import gnu.trove.iterator.TLongIterator;
/*     */ import gnu.trove.procedure.TLongProcedure;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class TUnmodifiableLongCollection implements TLongCollection, Serializable {
/*     */   private static final long serialVersionUID = 1820017752578914078L;
/*     */   
/*     */   final TLongCollection c;
/*     */   
/*     */   public TUnmodifiableLongCollection(TLongCollection c) {
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
/*     */   public boolean contains(long o) {
/*  65 */     return this.c.contains(o);
/*     */   }
/*     */   
/*     */   public long[] toArray() {
/*  66 */     return this.c.toArray();
/*     */   }
/*     */   
/*     */   public long[] toArray(long[] a) {
/*  67 */     return this.c.toArray(a);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  68 */     return this.c.toString();
/*     */   }
/*     */   
/*     */   public long getNoEntryValue() {
/*  69 */     return this.c.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEach(TLongProcedure procedure) {
/*  70 */     return this.c.forEach(procedure);
/*     */   }
/*     */   
/*     */   public TLongIterator iterator() {
/*  73 */     return new TLongIterator() {
/*  74 */         TLongIterator i = TUnmodifiableLongCollection.this.c.iterator();
/*     */         
/*     */         public boolean hasNext() {
/*  76 */           return this.i.hasNext();
/*     */         }
/*     */         
/*     */         public long next() {
/*  77 */           return this.i.next();
/*     */         }
/*     */         
/*     */         public void remove() {
/*  78 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean add(long e) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(long o) {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> coll) {
/*  85 */     return this.c.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean containsAll(TLongCollection coll) {
/*  86 */     return this.c.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean containsAll(long[] array) {
/*  87 */     return this.c.containsAll(array);
/*     */   }
/*     */   
/*     */   public boolean addAll(TLongCollection coll) {
/*  89 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends Long> coll) {
/*  90 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(long[] array) {
/*  91 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/*  93 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(TLongCollection coll) {
/*  94 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(long[] array) {
/*  95 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/*  97 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(TLongCollection coll) {
/*  98 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(long[] array) {
/*  99 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 101 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableLongCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */