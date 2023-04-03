/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class BoundedMailbox$ implements Serializable {
/*     */   public static final BoundedMailbox$ MODULE$;
/*     */   
/*     */   public BoundedMailbox apply(int capacity, FiniteDuration pushTimeOut) {
/* 579 */     return new BoundedMailbox(capacity, pushTimeOut);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Object, FiniteDuration>> unapply(BoundedMailbox x$0) {
/* 579 */     return (x$0 == null) ? (Option<Tuple2<Object, FiniteDuration>>)scala.None$.MODULE$ : (Option<Tuple2<Object, FiniteDuration>>)new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.capacity()), x$0.pushTimeOut()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 592 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private BoundedMailbox$() {
/* 592 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BoundedMailbox$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */