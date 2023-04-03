/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.MutableSetFactory;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class LinkedHashSet$ extends MutableSetFactory<LinkedHashSet> implements Serializable {
/*     */   public static final LinkedHashSet$ MODULE$;
/*     */   
/*     */   public class LinkedHashSet$$anon$1 extends AbstractIterator<A> {
/*     */     private LinkedHashSet.Entry<A> cur;
/*     */     
/*     */     public LinkedHashSet$$anon$1(LinkedHashSet<A> $outer) {
/*  77 */       this.cur = $outer.firstEntry();
/*     */     }
/*     */     
/*     */     private LinkedHashSet.Entry<A> cur() {
/*  77 */       return this.cur;
/*     */     }
/*     */     
/*     */     private void cur_$eq(LinkedHashSet.Entry<A> x$1) {
/*  77 */       this.cur = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  78 */       return (cur() != null);
/*     */     }
/*     */     
/*     */     public A next() {
/*  80 */       Object res = cur().key();
/*  80 */       cur_$eq(cur().later());
/*  80 */       return hasNext() ? (A)res : 
/*  81 */         (A)scala.collection.Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public class LinkedHashSet$$anonfun$writeObject$1 extends AbstractFunction1<LinkedHashSet.Entry<A>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public final void apply(LinkedHashSet.Entry e) {
/* 114 */       this.out$1.writeObject(e.key());
/*     */     }
/*     */     
/*     */     public LinkedHashSet$$anonfun$writeObject$1(LinkedHashSet $outer, ObjectOutputStream out$1) {}
/*     */   }
/*     */   
/*     */   public class LinkedHashSet$$anonfun$readObject$1 extends AbstractFunction0<LinkedHashSet.Entry<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectInputStream in$1;
/*     */     
/*     */     public final LinkedHashSet.Entry<A> apply() {
/* 120 */       return this.$outer.createNewEntry(this.in$1.readObject(), (Object)null);
/*     */     }
/*     */     
/*     */     public LinkedHashSet$$anonfun$readObject$1(LinkedHashSet $outer, ObjectInputStream in$1) {}
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 128 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private LinkedHashSet$() {
/* 128 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<LinkedHashSet<?>, A, LinkedHashSet<A>> canBuildFrom() {
/* 129 */     return setCanBuildFrom();
/*     */   }
/*     */   
/*     */   public <A> LinkedHashSet<A> empty() {
/* 130 */     return new LinkedHashSet<A>();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinkedHashSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */