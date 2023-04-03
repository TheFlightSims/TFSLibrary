/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TByteCollection;
/*     */ import gnu.trove.iterator.TByteIterator;
/*     */ import gnu.trove.procedure.TByteProcedure;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class TUnmodifiableByteCollection implements TByteCollection, Serializable {
/*     */   private static final long serialVersionUID = 1820017752578914078L;
/*     */   
/*     */   final TByteCollection c;
/*     */   
/*     */   public TUnmodifiableByteCollection(TByteCollection c) {
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
/*     */   public boolean contains(byte o) {
/*  65 */     return this.c.contains(o);
/*     */   }
/*     */   
/*     */   public byte[] toArray() {
/*  66 */     return this.c.toArray();
/*     */   }
/*     */   
/*     */   public byte[] toArray(byte[] a) {
/*  67 */     return this.c.toArray(a);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  68 */     return this.c.toString();
/*     */   }
/*     */   
/*     */   public byte getNoEntryValue() {
/*  69 */     return this.c.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEach(TByteProcedure procedure) {
/*  70 */     return this.c.forEach(procedure);
/*     */   }
/*     */   
/*     */   public TByteIterator iterator() {
/*  73 */     return new TByteIterator() {
/*  74 */         TByteIterator i = TUnmodifiableByteCollection.this.c.iterator();
/*     */         
/*     */         public boolean hasNext() {
/*  76 */           return this.i.hasNext();
/*     */         }
/*     */         
/*     */         public byte next() {
/*  77 */           return this.i.next();
/*     */         }
/*     */         
/*     */         public void remove() {
/*  78 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean add(byte e) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(byte o) {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> coll) {
/*  85 */     return this.c.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean containsAll(TByteCollection coll) {
/*  86 */     return this.c.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean containsAll(byte[] array) {
/*  87 */     return this.c.containsAll(array);
/*     */   }
/*     */   
/*     */   public boolean addAll(TByteCollection coll) {
/*  89 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends Byte> coll) {
/*  90 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(byte[] array) {
/*  91 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/*  93 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(TByteCollection coll) {
/*  94 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(byte[] array) {
/*  95 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/*  97 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(TByteCollection coll) {
/*  98 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(byte[] array) {
/*  99 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 101 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableByteCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */