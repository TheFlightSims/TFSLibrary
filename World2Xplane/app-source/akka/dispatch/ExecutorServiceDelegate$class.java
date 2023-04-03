/*     */ package akka.dispatch;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ public abstract class ExecutorServiceDelegate$class {
/*     */   public static void $init$(ExecutorServiceDelegate $this) {}
/*     */   
/*     */   public static void execute(ExecutorServiceDelegate $this, Runnable command) {
/* 212 */     $this.executor().execute(command);
/*     */   }
/*     */   
/*     */   public static void shutdown(ExecutorServiceDelegate $this) {
/* 214 */     $this.executor().shutdown();
/*     */   }
/*     */   
/*     */   public static List shutdownNow(ExecutorServiceDelegate $this) {
/* 216 */     return $this.executor().shutdownNow();
/*     */   }
/*     */   
/*     */   public static boolean isShutdown(ExecutorServiceDelegate $this) {
/* 218 */     return $this.executor().isShutdown();
/*     */   }
/*     */   
/*     */   public static boolean isTerminated(ExecutorServiceDelegate $this) {
/* 220 */     return $this.executor().isTerminated();
/*     */   }
/*     */   
/*     */   public static boolean awaitTermination(ExecutorServiceDelegate $this, long l, TimeUnit timeUnit) {
/* 222 */     return $this.executor().awaitTermination(l, timeUnit);
/*     */   }
/*     */   
/*     */   public static Future submit(ExecutorServiceDelegate $this, Callable<?> callable) {
/* 224 */     return $this.executor().submit(callable);
/*     */   }
/*     */   
/*     */   public static Future submit(ExecutorServiceDelegate $this, Runnable runnable, Object t) {
/* 226 */     return $this.executor().submit(runnable, t);
/*     */   }
/*     */   
/*     */   public static Future submit(ExecutorServiceDelegate $this, Runnable runnable) {
/* 228 */     return $this.executor().submit(runnable);
/*     */   }
/*     */   
/*     */   public static List invokeAll(ExecutorServiceDelegate $this, Collection<? extends Callable<?>> callables) {
/* 230 */     return $this.executor().invokeAll(callables);
/*     */   }
/*     */   
/*     */   public static List invokeAll(ExecutorServiceDelegate $this, Collection<? extends Callable<?>> callables, long l, TimeUnit timeUnit) {
/* 232 */     return $this.executor().invokeAll(callables, l, timeUnit);
/*     */   }
/*     */   
/*     */   public static Object invokeAny(ExecutorServiceDelegate $this, Collection<? extends Callable<?>> callables) {
/* 234 */     return $this.executor().invokeAny(callables);
/*     */   }
/*     */   
/*     */   public static Object invokeAny(ExecutorServiceDelegate $this, Collection<? extends Callable<?>> callables, long l, TimeUnit timeUnit) {
/* 236 */     return $this.executor().invokeAny(callables, l, timeUnit);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ExecutorServiceDelegate$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */