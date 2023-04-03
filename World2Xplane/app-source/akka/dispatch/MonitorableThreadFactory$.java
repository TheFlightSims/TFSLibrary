/*     */ package akka.dispatch;
/*     */ 
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple5;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class MonitorableThreadFactory$ implements Serializable {
/*     */   public static final MonitorableThreadFactory$ MODULE$;
/*     */   
/*     */   private final Thread.UncaughtExceptionHandler doNothing;
/*     */   
/*     */   private Object readResolve() {
/* 160 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private MonitorableThreadFactory$() {
/* 160 */     MODULE$ = this;
/* 161 */     this.doNothing = 
/* 162 */       new MonitorableThreadFactory.$anon$2();
/*     */   }
/*     */   
/*     */   public Thread.UncaughtExceptionHandler doNothing() {
/*     */     return this.doNothing;
/*     */   }
/*     */   
/*     */   public static class $anon$2 implements Thread.UncaughtExceptionHandler {
/*     */     public void uncaughtException(Thread thread, Throwable cause) {}
/*     */   }
/*     */   
/*     */   public MonitorableThreadFactory apply(String name, boolean daemonic, Option<ClassLoader> contextClassLoader, Thread.UncaughtExceptionHandler exceptionHandler, AtomicLong counter) {
/* 179 */     return new MonitorableThreadFactory(name, daemonic, contextClassLoader, exceptionHandler, counter);
/*     */   }
/*     */   
/*     */   public Option<Tuple5<String, Object, Option<ClassLoader>, Thread.UncaughtExceptionHandler, AtomicLong>> unapply(MonitorableThreadFactory x$0) {
/* 179 */     return (x$0 == null) ? (Option<Tuple5<String, Object, Option<ClassLoader>, Thread.UncaughtExceptionHandler, AtomicLong>>)scala.None$.MODULE$ : (Option<Tuple5<String, Object, Option<ClassLoader>, Thread.UncaughtExceptionHandler, AtomicLong>>)new Some(new Tuple5(x$0.name(), BoxesRunTime.boxToBoolean(x$0.daemonic()), x$0.contextClassLoader(), x$0.exceptionHandler(), x$0.counter()));
/*     */   }
/*     */   
/*     */   public Thread.UncaughtExceptionHandler apply$default$4() {
/* 182 */     return doNothing();
/*     */   }
/*     */   
/*     */   public Thread.UncaughtExceptionHandler $lessinit$greater$default$4() {
/* 182 */     return doNothing();
/*     */   }
/*     */   
/*     */   public AtomicLong apply$default$5() {
/* 183 */     return new AtomicLong();
/*     */   }
/*     */   
/*     */   public AtomicLong $lessinit$greater$default$5() {
/* 183 */     return new AtomicLong();
/*     */   }
/*     */   
/*     */   public class MonitorableThreadFactory$$anonfun$wire$1 extends AbstractFunction1<ClassLoader, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Thread t$1;
/*     */     
/*     */     public final void apply(ClassLoader x$1) {
/* 200 */       this.t$1.setContextClassLoader(x$1);
/*     */     }
/*     */     
/*     */     public MonitorableThreadFactory$$anonfun$wire$1(MonitorableThreadFactory $outer, Thread t$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\MonitorableThreadFactory$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */