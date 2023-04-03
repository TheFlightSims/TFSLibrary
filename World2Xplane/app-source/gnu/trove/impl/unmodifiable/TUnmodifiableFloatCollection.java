/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TFloatCollection;
/*     */ import gnu.trove.iterator.TFloatIterator;
/*     */ import gnu.trove.procedure.TFloatProcedure;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class TUnmodifiableFloatCollection implements TFloatCollection, Serializable {
/*     */   private static final long serialVersionUID = 1820017752578914078L;
/*     */   
/*     */   final TFloatCollection c;
/*     */   
/*     */   public TUnmodifiableFloatCollection(TFloatCollection c) {
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
/*     */   public boolean contains(float o) {
/*  65 */     return this.c.contains(o);
/*     */   }
/*     */   
/*     */   public float[] toArray() {
/*  66 */     return this.c.toArray();
/*     */   }
/*     */   
/*     */   public float[] toArray(float[] a) {
/*  67 */     return this.c.toArray(a);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  68 */     return this.c.toString();
/*     */   }
/*     */   
/*     */   public float getNoEntryValue() {
/*  69 */     return this.c.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEach(TFloatProcedure procedure) {
/*  70 */     return this.c.forEach(procedure);
/*     */   }
/*     */   
/*     */   public TFloatIterator iterator() {
/*  73 */     return new TFloatIterator() {
/*  74 */         TFloatIterator i = TUnmodifiableFloatCollection.this.c.iterator();
/*     */         
/*     */         public boolean hasNext() {
/*  76 */           return this.i.hasNext();
/*     */         }
/*     */         
/*     */         public float next() {
/*  77 */           return this.i.next();
/*     */         }
/*     */         
/*     */         public void remove() {
/*  78 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean add(float e) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(float o) {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> coll) {
/*  85 */     return this.c.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean containsAll(TFloatCollection coll) {
/*  86 */     return this.c.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean containsAll(float[] array) {
/*  87 */     return this.c.containsAll(array);
/*     */   }
/*     */   
/*     */   public boolean addAll(TFloatCollection coll) {
/*  89 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends Float> coll) {
/*  90 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(float[] array) {
/*  91 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/*  93 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(TFloatCollection coll) {
/*  94 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(float[] array) {
/*  95 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/*  97 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(TFloatCollection coll) {
/*  98 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(float[] array) {
/*  99 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 101 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableFloatCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */