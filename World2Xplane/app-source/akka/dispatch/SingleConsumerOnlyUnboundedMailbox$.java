/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class SingleConsumerOnlyUnboundedMailbox$ extends AbstractFunction0<SingleConsumerOnlyUnboundedMailbox> implements Serializable {
/*     */   public static final SingleConsumerOnlyUnboundedMailbox$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 569 */     return "SingleConsumerOnlyUnboundedMailbox";
/*     */   }
/*     */   
/*     */   public SingleConsumerOnlyUnboundedMailbox apply() {
/* 569 */     return new SingleConsumerOnlyUnboundedMailbox();
/*     */   }
/*     */   
/*     */   public boolean unapply(SingleConsumerOnlyUnboundedMailbox x$0) {
/* 569 */     return !(x$0 == null);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 569 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private SingleConsumerOnlyUnboundedMailbox$() {
/* 569 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\SingleConsumerOnlyUnboundedMailbox$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */