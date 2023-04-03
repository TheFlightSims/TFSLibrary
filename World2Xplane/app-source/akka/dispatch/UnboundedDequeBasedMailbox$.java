/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.Serializable;
/*     */ 
/*     */ public final class UnboundedDequeBasedMailbox$ implements Serializable {
/*     */   public static final UnboundedDequeBasedMailbox$ MODULE$;
/*     */   
/*     */   public UnboundedDequeBasedMailbox apply() {
/* 642 */     return new UnboundedDequeBasedMailbox();
/*     */   }
/*     */   
/*     */   public boolean unapply(UnboundedDequeBasedMailbox x$0) {
/* 642 */     return !(x$0 == null);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 650 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private UnboundedDequeBasedMailbox$() {
/* 650 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\UnboundedDequeBasedMailbox$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */