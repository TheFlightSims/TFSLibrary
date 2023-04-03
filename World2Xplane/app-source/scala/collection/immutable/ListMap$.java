/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenMapFactory;
/*     */ import scala.collection.generic.ImmutableMapFactory;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class ListMap$ extends ImmutableMapFactory<ListMap> implements Serializable {
/*     */   public static final ListMap$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  25 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ListMap$() {
/*  25 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A, B> CanBuildFrom<ListMap<?, ?>, Tuple2<A, B>, ListMap<A, B>> canBuildFrom() {
/*  28 */     return (CanBuildFrom<ListMap<?, ?>, Tuple2<A, B>, ListMap<A, B>>)new GenMapFactory.MapCanBuildFrom((GenMapFactory)this);
/*     */   }
/*     */   
/*     */   public <A, B> ListMap<A, B> empty() {
/*  29 */     return ListMap.EmptyListMap$.MODULE$;
/*     */   }
/*     */   
/*     */   public class ListMap$$anonfun$$plus$plus$1 extends AbstractFunction2<ListMap<A, B1>, Tuple2<A, B1>, ListMap<A, B1>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ListMap<A, B1> apply(ListMap x$2, Tuple2 x$3) {
/* 106 */       return x$2.$plus(x$3);
/*     */     }
/*     */     
/*     */     public ListMap$$anonfun$$plus$plus$1(ListMap $outer) {}
/*     */   }
/*     */   
/*     */   public class ListMap$$anon$1 extends AbstractIterator<Tuple2<A, B>> {
/*     */     private ListMap<A, B> self;
/*     */     
/*     */     public ListMap$$anon$1(ListMap<A, B> $outer) {
/* 120 */       this.self = $outer;
/*     */     }
/*     */     
/*     */     public ListMap<A, B> self() {
/* 120 */       return this.self;
/*     */     }
/*     */     
/*     */     public void self_$eq(ListMap<A, B> x$1) {
/* 120 */       this.self = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 121 */       return !self().isEmpty();
/*     */     }
/*     */     
/*     */     public Tuple2<A, B> next() {
/* 123 */       if (hasNext()) {
/* 124 */         Tuple2<A, B> res = new Tuple2(self().key(), self().value());
/* 124 */         self_$eq(self().tail());
/* 124 */         return res;
/*     */       } 
/*     */       throw new NoSuchElementException("next on empty iterator");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\ListMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */