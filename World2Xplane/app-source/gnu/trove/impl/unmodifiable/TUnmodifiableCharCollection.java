/*     */ package gnu.trove.impl.unmodifiable;
/*     */ 
/*     */ import gnu.trove.TCharCollection;
/*     */ import gnu.trove.iterator.TCharIterator;
/*     */ import gnu.trove.procedure.TCharProcedure;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class TUnmodifiableCharCollection implements TCharCollection, Serializable {
/*     */   private static final long serialVersionUID = 1820017752578914078L;
/*     */   
/*     */   final TCharCollection c;
/*     */   
/*     */   public TUnmodifiableCharCollection(TCharCollection c) {
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
/*     */   public boolean contains(char o) {
/*  65 */     return this.c.contains(o);
/*     */   }
/*     */   
/*     */   public char[] toArray() {
/*  66 */     return this.c.toArray();
/*     */   }
/*     */   
/*     */   public char[] toArray(char[] a) {
/*  67 */     return this.c.toArray(a);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  68 */     return this.c.toString();
/*     */   }
/*     */   
/*     */   public char getNoEntryValue() {
/*  69 */     return this.c.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEach(TCharProcedure procedure) {
/*  70 */     return this.c.forEach(procedure);
/*     */   }
/*     */   
/*     */   public TCharIterator iterator() {
/*  73 */     return new TCharIterator() {
/*  74 */         TCharIterator i = TUnmodifiableCharCollection.this.c.iterator();
/*     */         
/*     */         public boolean hasNext() {
/*  76 */           return this.i.hasNext();
/*     */         }
/*     */         
/*     */         public char next() {
/*  77 */           return this.i.next();
/*     */         }
/*     */         
/*     */         public void remove() {
/*  78 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean add(char e) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(char o) {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> coll) {
/*  85 */     return this.c.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean containsAll(TCharCollection coll) {
/*  86 */     return this.c.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean containsAll(char[] array) {
/*  87 */     return this.c.containsAll(array);
/*     */   }
/*     */   
/*     */   public boolean addAll(TCharCollection coll) {
/*  89 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends Character> coll) {
/*  90 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(char[] array) {
/*  91 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/*  93 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(TCharCollection coll) {
/*  94 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(char[] array) {
/*  95 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/*  97 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(TCharCollection coll) {
/*  98 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(char[] array) {
/*  99 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 101 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableCharCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */