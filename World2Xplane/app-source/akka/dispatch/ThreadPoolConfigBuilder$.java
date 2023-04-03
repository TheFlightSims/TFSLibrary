/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class ThreadPoolConfigBuilder$ extends AbstractFunction1<ThreadPoolConfig, ThreadPoolConfigBuilder> implements Serializable {
/*     */   public static final ThreadPoolConfigBuilder$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 105 */     return "ThreadPoolConfigBuilder";
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder apply(ThreadPoolConfig config) {
/* 105 */     return new ThreadPoolConfigBuilder(config);
/*     */   }
/*     */   
/*     */   public Option<ThreadPoolConfig> unapply(ThreadPoolConfigBuilder x$0) {
/* 105 */     return (x$0 == null) ? (Option<ThreadPoolConfig>)scala.None$.MODULE$ : (Option<ThreadPoolConfig>)new Some(x$0.config());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 105 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ThreadPoolConfigBuilder$() {
/* 105 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class ThreadPoolConfigBuilder$$anonfun$configure$1 extends AbstractFunction2<ThreadPoolConfigBuilder, Option<Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder>>, ThreadPoolConfigBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ThreadPoolConfigBuilder apply(ThreadPoolConfigBuilder c, Option f) {
/* 157 */       return (ThreadPoolConfigBuilder)f.map((Function1)new ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$1(this, c)).getOrElse((Function0)new ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$2(this, c));
/*     */     }
/*     */     
/*     */     public ThreadPoolConfigBuilder$$anonfun$configure$1(ThreadPoolConfigBuilder $outer) {}
/*     */     
/*     */     public class ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$1 extends AbstractFunction1<Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder>, ThreadPoolConfigBuilder> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ThreadPoolConfigBuilder c$1;
/*     */       
/*     */       public final ThreadPoolConfigBuilder apply(Function1 x$1) {
/* 157 */         return (ThreadPoolConfigBuilder)x$1.apply(this.c$1);
/*     */       }
/*     */       
/*     */       public ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$1(ThreadPoolConfigBuilder$$anonfun$configure$1 $outer, ThreadPoolConfigBuilder c$1) {}
/*     */     }
/*     */     
/*     */     public class ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$2 extends AbstractFunction0<ThreadPoolConfigBuilder> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ThreadPoolConfigBuilder c$1;
/*     */       
/*     */       public final ThreadPoolConfigBuilder apply() {
/* 157 */         return this.c$1;
/*     */       }
/*     */       
/*     */       public ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$2(ThreadPoolConfigBuilder$$anonfun$configure$1 $outer, ThreadPoolConfigBuilder c$1) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ThreadPoolConfigBuilder$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */