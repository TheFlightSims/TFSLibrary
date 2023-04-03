/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.ImmutableSetFactory;
/*     */ import scala.collection.mutable.Builder;
/*     */ 
/*     */ public final class ListSet$ extends ImmutableSetFactory<ListSet> implements Serializable {
/*     */   public static final ListSet$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  21 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ListSet$() {
/*  21 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<ListSet<?>, A, ListSet<A>> canBuildFrom() {
/*  23 */     return setCanBuildFrom();
/*     */   }
/*     */   
/*     */   public <A> ListSet<A> empty() {
/*  24 */     return ListSet.EmptyListSet$.MODULE$;
/*     */   }
/*     */   
/*     */   public <A> Builder<A, ListSet<A>> newBuilder() {
/*  25 */     return new ListSet.ListSetBuilder<A>();
/*     */   }
/*     */   
/*     */   public class ListSet$$anon$1 extends AbstractIterator<A> {
/*     */     private ListSet<A> that;
/*     */     
/*     */     public ListSet$$anon$1(ListSet<A> $outer) {
/* 115 */       this.that = $outer;
/*     */     }
/*     */     
/*     */     private ListSet<A> that() {
/* 115 */       return this.that;
/*     */     }
/*     */     
/*     */     private void that_$eq(ListSet<A> x$1) {
/* 115 */       this.that = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 116 */       return that().nonEmpty();
/*     */     }
/*     */     
/*     */     public A next() {
/* 119 */       Object res = that().head();
/* 120 */       that_$eq(that().tail());
/* 121 */       return hasNext() ? (A)res : 
/*     */         
/* 123 */         (A)scala.collection.Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\ListSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */