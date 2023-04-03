/*     */ package gnu.trove.impl.sync;
/*     */ 
/*     */ import gnu.trove.TCharCollection;
/*     */ import gnu.trove.iterator.TCharIterator;
/*     */ import gnu.trove.procedure.TCharProcedure;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class TSynchronizedCharCollection implements TCharCollection, Serializable {
/*     */   private static final long serialVersionUID = 3053995032091335093L;
/*     */   
/*     */   final TCharCollection c;
/*     */   
/*     */   final Object mutex;
/*     */   
/*     */   public TSynchronizedCharCollection(TCharCollection c) {
/*  59 */     if (c == null)
/*  60 */       throw new NullPointerException(); 
/*  61 */     this.c = c;
/*  62 */     this.mutex = this;
/*     */   }
/*     */   
/*     */   public TSynchronizedCharCollection(TCharCollection c, Object mutex) {
/*  65 */     this.c = c;
/*  66 */     this.mutex = mutex;
/*     */   }
/*     */   
/*     */   public int size() {
/*  70 */     synchronized (this.mutex) {
/*  70 */       return this.c.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  73 */     synchronized (this.mutex) {
/*  73 */       return this.c.isEmpty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(char o) {
/*  76 */     synchronized (this.mutex) {
/*  76 */       return this.c.contains(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public char[] toArray() {
/*  79 */     synchronized (this.mutex) {
/*  79 */       return this.c.toArray();
/*     */     } 
/*     */   }
/*     */   
/*     */   public char[] toArray(char[] a) {
/*  82 */     synchronized (this.mutex) {
/*  82 */       return this.c.toArray(a);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TCharIterator iterator() {
/*  86 */     return this.c.iterator();
/*     */   }
/*     */   
/*     */   public boolean add(char e) {
/*  90 */     synchronized (this.mutex) {
/*  90 */       return this.c.add(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean remove(char o) {
/*  93 */     synchronized (this.mutex) {
/*  93 */       return this.c.remove(o);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> coll) {
/*  97 */     synchronized (this.mutex) {
/*  97 */       return this.c.containsAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(TCharCollection coll) {
/* 100 */     synchronized (this.mutex) {
/* 100 */       return this.c.containsAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(char[] array) {
/* 103 */     synchronized (this.mutex) {
/* 103 */       return this.c.containsAll(array);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends Character> coll) {
/* 107 */     synchronized (this.mutex) {
/* 107 */       return this.c.addAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(TCharCollection coll) {
/* 110 */     synchronized (this.mutex) {
/* 110 */       return this.c.addAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(char[] array) {
/* 113 */     synchronized (this.mutex) {
/* 113 */       return this.c.addAll(array);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/* 117 */     synchronized (this.mutex) {
/* 117 */       return this.c.removeAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removeAll(TCharCollection coll) {
/* 120 */     synchronized (this.mutex) {
/* 120 */       return this.c.removeAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removeAll(char[] array) {
/* 123 */     synchronized (this.mutex) {
/* 123 */       return this.c.removeAll(array);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/* 127 */     synchronized (this.mutex) {
/* 127 */       return this.c.retainAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(TCharCollection coll) {
/* 130 */     synchronized (this.mutex) {
/* 130 */       return this.c.retainAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(char[] array) {
/* 133 */     synchronized (this.mutex) {
/* 133 */       return this.c.retainAll(array);
/*     */     } 
/*     */   }
/*     */   
/*     */   public char getNoEntryValue() {
/* 136 */     return this.c.getNoEntryValue();
/*     */   }
/*     */   
/*     */   public boolean forEach(TCharProcedure procedure) {
/* 138 */     synchronized (this.mutex) {
/* 138 */       return this.c.forEach(procedure);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 142 */     synchronized (this.mutex) {
/* 142 */       this.c.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 145 */     synchronized (this.mutex) {
/* 145 */       return this.c.toString();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 148 */     synchronized (this.mutex) {
/* 148 */       s.defaultWriteObject();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedCharCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */