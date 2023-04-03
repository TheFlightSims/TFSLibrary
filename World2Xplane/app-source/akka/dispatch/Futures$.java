/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.japi.Function;
/*     */ import akka.japi.Function2;
/*     */ import akka.japi.Option;
/*     */ import java.util.LinkedList;
/*     */ import java.util.concurrent.Callable;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.Promise;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Futures$ {
/*     */   public static final Futures$ MODULE$;
/*     */   
/*     */   private Futures$() {
/*  83 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> Future<T> future(Callable body, ExecutionContext executor) {
/*  94 */     return scala.concurrent.Future$.MODULE$.apply((Function0)new Futures$$anonfun$future$1(body), executor);
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$future$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Callable body$1;
/*     */     
/*     */     public final T apply() {
/*  94 */       return this.body$1.call();
/*     */     }
/*     */     
/*     */     public Futures$$anonfun$future$1(Callable body$1) {}
/*     */   }
/*     */   
/*     */   public <T> Promise<T> promise() {
/* 101 */     return scala.concurrent.Promise$.MODULE$.apply();
/*     */   }
/*     */   
/*     */   public <T> Future<T> failed(Throwable exception) {
/* 106 */     return scala.concurrent.Future$.MODULE$.failed(exception);
/*     */   }
/*     */   
/*     */   public <T> Future<T> successful(Object result) {
/* 111 */     return scala.concurrent.Future$.MODULE$.successful(result);
/*     */   }
/*     */   
/*     */   public <T> Future<Option<T>> find(Iterable futures, Function predicate, ExecutionContext executor) {
/* 117 */     ExecutionContext ec = executor;
/* 118 */     return scala.concurrent.Future$.MODULE$.find((TraversableOnce)scala.collection.JavaConverters$.MODULE$.iterableAsScalaIterableConverter(futures).asScala(), (Function1)new Futures$$anonfun$find$1(predicate), executor).map((Function1)new Futures$$anonfun$find$2(), ec);
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$find$1 extends AbstractFunction1<T, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function predicate$1;
/*     */     
/*     */     public final boolean apply(Object x$1) {
/* 118 */       return scala.Predef$.MODULE$.Boolean2boolean((Boolean)this.predicate$1.apply(x$1));
/*     */     }
/*     */     
/*     */     public Futures$$anonfun$find$1(Function predicate$1) {}
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$find$2 extends AbstractFunction1<Option<T>, Option<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Option<T> apply(Option scalaOption) {
/* 118 */       return akka.japi.Option$.MODULE$.fromScalaOption(scalaOption);
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Future<T> firstCompletedOf(Iterable futures, ExecutionContext executor) {
/* 125 */     return scala.concurrent.Future$.MODULE$.firstCompletedOf((TraversableOnce)scala.collection.JavaConverters$.MODULE$.iterableAsScalaIterableConverter(futures).asScala(), executor);
/*     */   }
/*     */   
/*     */   public <T, R> Future<R> fold(Object zero, Iterable futures, Function2 fun, ExecutionContext executor) {
/* 134 */     return scala.concurrent.Future$.MODULE$.fold((TraversableOnce)scala.collection.JavaConverters$.MODULE$.iterableAsScalaIterableConverter(futures).asScala(), zero, (Function2)new Futures$$anonfun$fold$1(fun), executor);
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$fold$1 extends AbstractFunction2<R, T, R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 fun$1;
/*     */     
/*     */     public final R apply(Object arg1, Object arg2) {
/* 134 */       return (R)this.fun$1.apply(arg1, arg2);
/*     */     }
/*     */     
/*     */     public Futures$$anonfun$fold$1(Function2 fun$1) {}
/*     */   }
/*     */   
/*     */   public <T, R> Future<R> reduce(Iterable futures, Function2 fun, ExecutionContext executor) {
/* 140 */     return scala.concurrent.Future$.MODULE$.reduce((TraversableOnce)scala.collection.JavaConverters$.MODULE$.iterableAsScalaIterableConverter(futures).asScala(), (Function2)new Futures$$anonfun$reduce$1(fun), executor);
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$reduce$1 extends AbstractFunction2<R, T, R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 fun$2;
/*     */     
/*     */     public final R apply(Object arg1, Object arg2) {
/* 140 */       return (R)this.fun$2.apply(arg1, arg2);
/*     */     }
/*     */     
/*     */     public Futures$$anonfun$reduce$1(Function2 fun$2) {}
/*     */   }
/*     */   
/*     */   public <A> Future<Iterable<A>> sequence(Iterable in, ExecutionContext executor) {
/* 147 */     ExecutionContext d = executor;
/* 148 */     return (Future<Iterable<A>>)((TraversableOnce)scala.collection.JavaConverters$.MODULE$.iterableAsScalaIterableConverter(in).asScala()).foldLeft(scala.concurrent.Future$.MODULE$.apply((Function0)new Futures$$anonfun$sequence$1(), d), (Function2)new Futures$$anonfun$sequence$2(d));
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$sequence$1 extends AbstractFunction0<LinkedList<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final LinkedList<A> apply() {
/* 148 */       return new LinkedList<A>();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$sequence$2 extends AbstractFunction2<Future<LinkedList<A>>, Future<A>, Future<LinkedList<A>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ExecutionContext d$1;
/*     */     
/*     */     public final Future<LinkedList<A>> apply(Future fr, Future fa) {
/* 148 */       return fr.flatMap((Function1)new Futures$$anonfun$sequence$2$$anonfun$apply$1(this, fa), this.d$1);
/*     */     }
/*     */     
/*     */     public Futures$$anonfun$sequence$2(ExecutionContext d$1) {}
/*     */     
/*     */     public class Futures$$anonfun$sequence$2$$anonfun$apply$1 extends AbstractFunction1<LinkedList<A>, Future<LinkedList<A>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Future fa$1;
/*     */       
/*     */       public final Future<LinkedList<A>> apply(LinkedList r) {
/* 148 */         return this.fa$1.map((Function1)new Futures$$anonfun$sequence$2$$anonfun$apply$1$$anonfun$apply$2(this, r), this.$outer.d$1);
/*     */       }
/*     */       
/*     */       public Futures$$anonfun$sequence$2$$anonfun$apply$1(Futures$$anonfun$sequence$2 $outer, Future fa$1) {}
/*     */       
/*     */       public class Futures$$anonfun$sequence$2$$anonfun$apply$1$$anonfun$apply$2 extends AbstractFunction1<A, LinkedList<A>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final LinkedList r$1;
/*     */         
/*     */         public final LinkedList<A> apply(Object a) {
/* 148 */           this.r$1.add(a);
/* 148 */           return this.r$1;
/*     */         }
/*     */         
/*     */         public Futures$$anonfun$sequence$2$$anonfun$apply$1$$anonfun$apply$2(Futures$$anonfun$sequence$2$$anonfun$apply$1 $outer, LinkedList r$1) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public <A, B> Future<Iterable<B>> traverse(Iterable in, Function fn, ExecutionContext executor) {
/* 157 */     ExecutionContext d = executor;
/* 158 */     return (Future<Iterable<B>>)((TraversableOnce)scala.collection.JavaConverters$.MODULE$.iterableAsScalaIterableConverter(in).asScala()).foldLeft(scala.concurrent.Future$.MODULE$.apply((Function0)new Futures$$anonfun$traverse$1(), d), (Function2)new Futures$$anonfun$traverse$2(fn, d));
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$traverse$1 extends AbstractFunction0<LinkedList<B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final LinkedList<B> apply() {
/* 158 */       return new LinkedList<B>();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$traverse$2 extends AbstractFunction2<Future<LinkedList<B>>, A, Future<LinkedList<B>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function fn$1;
/*     */     
/*     */     public final ExecutionContext d$2;
/*     */     
/*     */     public Futures$$anonfun$traverse$2(Function fn$1, ExecutionContext d$2) {}
/*     */     
/*     */     public final Future<LinkedList<B>> apply(Future fr, Object a) {
/* 159 */       Future fb = (Future)this.fn$1.apply(a);
/* 160 */       return fr.flatMap((Function1)new Futures$$anonfun$traverse$2$$anonfun$apply$3(this, fb), this.d$2);
/*     */     }
/*     */     
/*     */     public class Futures$$anonfun$traverse$2$$anonfun$apply$3 extends AbstractFunction1<LinkedList<B>, Future<LinkedList<B>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Future fb$1;
/*     */       
/*     */       public final Future<LinkedList<B>> apply(LinkedList r) {
/* 160 */         return this.fb$1.map((Function1)new Futures$$anonfun$traverse$2$$anonfun$apply$3$$anonfun$apply$4(this, r), this.$outer.d$2);
/*     */       }
/*     */       
/*     */       public Futures$$anonfun$traverse$2$$anonfun$apply$3(Futures$$anonfun$traverse$2 $outer, Future fb$1) {}
/*     */       
/*     */       public class Futures$$anonfun$traverse$2$$anonfun$apply$3$$anonfun$apply$4 extends AbstractFunction1<B, LinkedList<B>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final LinkedList r$2;
/*     */         
/*     */         public final LinkedList<B> apply(Object b) {
/* 160 */           this.r$2.add(b);
/* 160 */           return this.r$2;
/*     */         }
/*     */         
/*     */         public Futures$$anonfun$traverse$2$$anonfun$apply$3$$anonfun$apply$4(Futures$$anonfun$traverse$2$$anonfun$apply$3 $outer, LinkedList r$2) {}
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Futures$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */