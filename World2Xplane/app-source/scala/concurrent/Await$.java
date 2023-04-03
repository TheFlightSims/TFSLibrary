/*     */ package scala.concurrent;
/*     */ 
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import scala.Function0;
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class Await$ {
/*     */   public static final Await$ MODULE$;
/*     */   
/*     */   private Await$() {
/*  65 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> Awaitable<T> ready(Awaitable awaitable, Duration atMost) throws TimeoutException, InterruptedException {
/*  86 */     Await$$anonfun$ready$1 await$$anonfun$ready$1 = new Await$$anonfun$ready$1(awaitable, atMost);
/*  86 */     package$ package$ = package$.MODULE$;
/*  86 */     return BlockContext$.MODULE$.current().<Awaitable<T>>blockOn((Function0<Awaitable<T>>)await$$anonfun$ready$1, AwaitPermission$.MODULE$);
/*     */   }
/*     */   
/*     */   public static class Await$$anonfun$ready$1 extends AbstractFunction0<Awaitable<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Awaitable awaitable$1;
/*     */     
/*     */     private final Duration atMost$1;
/*     */     
/*     */     public final Awaitable<T> apply() {
/*  86 */       return this.awaitable$1.ready(this.atMost$1, AwaitPermission$.MODULE$);
/*     */     }
/*     */     
/*     */     public Await$$anonfun$ready$1(Awaitable awaitable$1, Duration atMost$1) {}
/*     */   }
/*     */   
/*     */   public <T> T result(Awaitable awaitable, Duration atMost) throws Exception {
/* 107 */     Await$$anonfun$result$1 await$$anonfun$result$1 = new Await$$anonfun$result$1(awaitable, atMost);
/* 107 */     package$ package$ = package$.MODULE$;
/* 107 */     return BlockContext$.MODULE$.current().blockOn((Function0<T>)await$$anonfun$result$1, AwaitPermission$.MODULE$);
/*     */   }
/*     */   
/*     */   public static class Await$$anonfun$result$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Awaitable awaitable$2;
/*     */     
/*     */     private final Duration atMost$2;
/*     */     
/*     */     public final T apply() {
/* 107 */       return this.awaitable$2.result(this.atMost$2, AwaitPermission$.MODULE$);
/*     */     }
/*     */     
/*     */     public Await$$anonfun$result$1(Awaitable awaitable$2, Duration atMost$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Await$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */