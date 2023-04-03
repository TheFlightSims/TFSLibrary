/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Set;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.parallel.mutable.ParArray;
/*    */ import scala.collection.parallel.mutable.ResizableParArrayCombiner;
/*    */ import scala.collection.parallel.mutable.package$;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class package$ {
/*    */   public static final package$ MODULE$;
/*    */   
/*    */   private final int MIN_FOR_COPY;
/*    */   
/*    */   private final int CHECK_RATE;
/*    */   
/*    */   private final double SQRT2;
/*    */   
/*    */   private final int availableProcessors;
/*    */   
/*    */   private final TaskSupport defaultTaskSupport;
/*    */   
/*    */   private package$() {
/* 21 */     MODULE$ = this;
/* 23 */     this.MIN_FOR_COPY = 512;
/* 24 */     this.CHECK_RATE = 512;
/* 25 */     this.SQRT2 = scala.math.package$.MODULE$.sqrt(2.0D);
/* 26 */     this.availableProcessors = Runtime.getRuntime().availableProcessors();
/* 48 */     this.defaultTaskSupport = getTaskSupport();
/*    */   }
/*    */   
/*    */   public int MIN_FOR_COPY() {
/*    */     return this.MIN_FOR_COPY;
/*    */   }
/*    */   
/*    */   public int CHECK_RATE() {
/*    */     return this.CHECK_RATE;
/*    */   }
/*    */   
/*    */   public double SQRT2() {
/*    */     return this.SQRT2;
/*    */   }
/*    */   
/*    */   public int availableProcessors() {
/*    */     return this.availableProcessors;
/*    */   }
/*    */   
/*    */   public int thresholdFromSize(int sz, int parallelismLevel) {
/*    */     return (parallelismLevel > 1) ? (1 + sz / 8 * parallelismLevel) : sz;
/*    */   }
/*    */   
/*    */   public scala.runtime.Nothing$ unsupported() {
/*    */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public scala.runtime.Nothing$ unsupportedop(String msg) {
/*    */     throw new UnsupportedOperationException(msg);
/*    */   }
/*    */   
/*    */   public scala.runtime.Nothing$ outofbounds(int idx) {
/*    */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*    */   }
/*    */   
/*    */   public TaskSupport getTaskSupport() {
/*    */     return scala.util.Properties$.MODULE$.isJavaAtLeast("1.6") ? new ForkJoinTaskSupport(ForkJoinTaskSupport$.MODULE$.$lessinit$greater$default$1()) : new ThreadPoolTaskSupport(ThreadPoolTaskSupport$.MODULE$.$lessinit$greater$default$1());
/*    */   }
/*    */   
/*    */   public TaskSupport defaultTaskSupport() {
/* 48 */     return this.defaultTaskSupport;
/*    */   }
/*    */   
/*    */   public <Coll> Coll setTaskSupport(Object c, TaskSupport t) {
/* 51 */     if (c instanceof ParIterableLike)
/* 51 */       ((ParIterableLike)c).tasksupport_$eq(t); 
/* 55 */     return (Coll)c;
/*    */   }
/*    */   
/*    */   public <From, Elem, To> Object factory2ops(CanBuildFrom bf) {
/* 60 */     return new package$$anon$4(bf);
/*    */   }
/*    */   
/*    */   public static class package$$anon$4 implements FactoryOps<From, Elem, To> {
/*    */     private final CanBuildFrom bf$1;
/*    */     
/*    */     public package$$anon$4(CanBuildFrom bf$1) {
/* 60 */       FactoryOps$class.$init$(this);
/*    */     }
/*    */     
/*    */     public boolean isParallel() {
/* 61 */       return this.bf$1 instanceof scala.collection.Parallel;
/*    */     }
/*    */     
/*    */     public CanCombineFrom<From, Elem, To> asParallel() {
/* 62 */       return (CanCombineFrom<From, Elem, To>)this.bf$1;
/*    */     }
/*    */     
/*    */     public <R> Object ifParallel(Function1 isbody) {
/* 63 */       return new package$$anon$4$$anon$5(this, isbody);
/*    */     }
/*    */     
/*    */     public class package$$anon$4$$anon$5 implements FactoryOps<From, Elem, To>.Otherwise<R> {
/*    */       private final Function1 isbody$2;
/*    */       
/*    */       public package$$anon$4$$anon$5(package$$anon$4 $outer, Function1 isbody$2) {}
/*    */       
/*    */       public R otherwise(Function0 notbody) {
/* 64 */         return this.$outer.isParallel() ? (R)this.isbody$2.apply(this.$outer.asParallel()) : (R)notbody.apply();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public <T> Object traversable2ops(GenTraversableOnce t) {
/* 67 */     return new package$$anon$2(t);
/*    */   }
/*    */   
/*    */   public static class package$$anon$2 implements TraversableOps<T> {
/*    */     private final GenTraversableOnce t$1;
/*    */     
/*    */     public package$$anon$2(GenTraversableOnce t$1) {
/* 67 */       TraversableOps$class.$init$(this);
/*    */     }
/*    */     
/*    */     public boolean isParallel() {
/* 68 */       return this.t$1 instanceof scala.collection.Parallel;
/*    */     }
/*    */     
/*    */     public boolean isParIterable() {
/* 69 */       return this.t$1 instanceof ParIterable;
/*    */     }
/*    */     
/*    */     public ParIterable<T> asParIterable() {
/* 70 */       return (ParIterable<T>)this.t$1;
/*    */     }
/*    */     
/*    */     public boolean isParSeq() {
/* 71 */       return this.t$1 instanceof ParSeq;
/*    */     }
/*    */     
/*    */     public ParSeq<T> asParSeq() {
/* 72 */       return (ParSeq<T>)this.t$1;
/*    */     }
/*    */     
/*    */     public <R> Object ifParSeq(Function1 isbody) {
/* 73 */       return new package$$anon$2$$anon$3(this, isbody);
/*    */     }
/*    */     
/*    */     public class package$$anon$2$$anon$3 implements TraversableOps<T>.Otherwise<R> {
/*    */       private final Function1 isbody$1;
/*    */       
/*    */       public package$$anon$2$$anon$3(package$$anon$2 $outer, Function1 isbody$1) {}
/*    */       
/*    */       public R otherwise(Function0 notbody) {
/* 74 */         return this.$outer.isParallel() ? (R)this.isbody$1.apply(this.$outer.asParSeq()) : (R)notbody.apply();
/*    */       }
/*    */     }
/*    */     
/*    */     public ParArray<T> toParArray() {
/* 77 */       Iterator it = this.t$1.toIterator();
/* 78 */       ResizableParArrayCombiner cb = package$.MODULE$.ParArrayCombiner().apply();
/* 79 */       for (; it.hasNext(); cb.$plus$eq(it.next()));
/* 80 */       return (this.t$1 instanceof ParArray) ? (ParArray<T>)this.t$1 : (ParArray<T>)cb.result();
/*    */     }
/*    */   }
/*    */   
/*    */   public ThrowableOps throwable2ops(Throwable self) {
/* 83 */     return new package$$anon$1(self);
/*    */   }
/*    */   
/*    */   public static class package$$anon$1 implements ThrowableOps {
/*    */     private final Throwable self$1;
/*    */     
/*    */     public package$$anon$1(Throwable self$1) {}
/*    */     
/*    */     public Throwable alongWith(Throwable that) {
/*    */       CompositeThrowable compositeThrowable;
/* 84 */       Tuple2 tuple2 = new Tuple2(this.self$1, that);
/* 84 */       if (tuple2 != null && tuple2
/* 85 */         ._1() instanceof CompositeThrowable) {
/* 85 */         CompositeThrowable compositeThrowable1 = (CompositeThrowable)tuple2._1();
/* 85 */         if (tuple2._2() instanceof CompositeThrowable) {
/* 85 */           CompositeThrowable compositeThrowable2 = (CompositeThrowable)tuple2._2();
/* 85 */           return new CompositeThrowable(compositeThrowable1.throwables().$plus$plus((GenTraversableOnce)compositeThrowable2.throwables()));
/*    */         } 
/*    */       } 
/* 86 */       if (tuple2 != null && tuple2._1() instanceof CompositeThrowable) {
/* 86 */         CompositeThrowable compositeThrowable1 = (CompositeThrowable)tuple2._1();
/* 86 */         compositeThrowable = new CompositeThrowable(compositeThrowable1.throwables().$plus(that));
/* 87 */       } else if (tuple2 != null && tuple2._2() instanceof CompositeThrowable) {
/* 87 */         CompositeThrowable compositeThrowable1 = (CompositeThrowable)tuple2._2();
/* 87 */         compositeThrowable = new CompositeThrowable(compositeThrowable1.throwables().$plus(this.self$1));
/*    */       } else {
/* 88 */         (new Throwable[2])[0] = this.self$1;
/* 88 */         (new Throwable[2])[1] = that;
/* 88 */         compositeThrowable = new CompositeThrowable((Set<Throwable>)scala.collection.Set$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Throwable[2])));
/*    */       } 
/*    */       return compositeThrowable;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */