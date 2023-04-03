/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.Serializable;
/*     */ 
/*     */ public final class UnboundedMailbox$ implements Serializable {
/*     */   public static final UnboundedMailbox$ MODULE$;
/*     */   
/*     */   public UnboundedMailbox apply() {
/* 550 */     return new UnboundedMailbox();
/*     */   }
/*     */   
/*     */   public boolean unapply(UnboundedMailbox x$0) {
/* 550 */     return !(x$0 == null);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 558 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private UnboundedMailbox$() {
/* 558 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\UnboundedMailbox$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */