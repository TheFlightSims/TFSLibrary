/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.OrderedTraversableFactory;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.PartialOrdering;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class PriorityQueue$ extends OrderedTraversableFactory<PriorityQueue> implements Serializable {
/*     */   public static final PriorityQueue$ MODULE$;
/*     */   
/*     */   public class PriorityQueue$$anon$2 extends AbstractIterator<A> {
/* 173 */     private int i = 1;
/*     */     
/*     */     private int i() {
/* 173 */       return this.i;
/*     */     }
/*     */     
/*     */     private void i_$eq(int x$1) {
/* 173 */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 174 */       return (i() < this.$outer.scala$collection$mutable$PriorityQueue$$resarr().p_size0());
/*     */     }
/*     */     
/*     */     public A next() {
/* 176 */       Object n = this.$outer.scala$collection$mutable$PriorityQueue$$resarr().p_array()[i()];
/* 177 */       i_$eq(i() + 1);
/* 178 */       return this.$outer.scala$collection$mutable$PriorityQueue$$toA(n);
/*     */     }
/*     */     
/*     */     public PriorityQueue$$anon$2(PriorityQueue $outer) {}
/*     */   }
/*     */   
/*     */   public class PriorityQueue$$anon$1 implements Ordering<A> {
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 196 */       return Ordering.class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 196 */       return Ordering.class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 196 */       return Ordering.class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 196 */       return Ordering.class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 196 */       return Ordering.class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 196 */       return Ordering.class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public A max(Object x, Object y) {
/* 196 */       return (A)Ordering.class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public A min(Object x, Object y) {
/* 196 */       return (A)Ordering.class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<A> reverse() {
/* 196 */       return Ordering.class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 196 */       return Ordering.class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<A>.Ops mkOrderingOps(Object lhs) {
/* 196 */       return Ordering.class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public PriorityQueue$$anon$1(PriorityQueue $outer) {
/* 196 */       PartialOrdering.class.$init$((PartialOrdering)this);
/* 196 */       Ordering.class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Object x, Object y) {
/* 197 */       return this.$outer.ord().compare(y, x);
/*     */     }
/*     */   }
/*     */   
/*     */   public class PriorityQueue$$anonfun$reverse$1 extends AbstractFunction1<Object, PriorityQueue<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final PriorityQueue revq$1;
/*     */     
/*     */     public final PriorityQueue<A> apply(int i) {
/* 199 */       return this.revq$1.$plus$eq(this.$outer.scala$collection$mutable$PriorityQueue$$resarr().apply(i));
/*     */     }
/*     */     
/*     */     public PriorityQueue$$anonfun$reverse$1(PriorityQueue $outer, PriorityQueue revq$1) {}
/*     */   }
/*     */   
/*     */   public class PriorityQueue$$anon$3 extends AbstractIterator<A> {
/*     */     private int i;
/*     */     
/*     */     public PriorityQueue$$anon$3(PriorityQueue $outer) {
/* 211 */       this.i = $outer.scala$collection$mutable$PriorityQueue$$resarr().p_size0() - 1;
/*     */     }
/*     */     
/*     */     private int i() {
/* 211 */       return this.i;
/*     */     }
/*     */     
/*     */     private void i_$eq(int x$1) {
/* 211 */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 212 */       return (i() >= 1);
/*     */     }
/*     */     
/*     */     public A next() {
/* 214 */       Object n = this.$outer.scala$collection$mutable$PriorityQueue$$resarr().p_array()[i()];
/* 215 */       i_$eq(i() - 1);
/* 216 */       return this.$outer.scala$collection$mutable$PriorityQueue$$toA(n);
/*     */     }
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 263 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private PriorityQueue$() {
/* 263 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> PriorityQueue<A> newBuilder(Ordering<A> ord) {
/* 264 */     return new PriorityQueue<A>(ord);
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<PriorityQueue<?>, A, PriorityQueue<A>> canBuildFrom(Ordering ord) {
/* 265 */     return (CanBuildFrom<PriorityQueue<?>, A, PriorityQueue<A>>)new OrderedTraversableFactory.GenericCanBuildFrom(this, ord);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\PriorityQueue$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */