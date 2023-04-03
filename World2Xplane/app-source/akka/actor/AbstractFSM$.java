/*     */ package akka.actor;
/*     */ 
/*     */ import scala.PartialFunction;
/*     */ 
/*     */ public final class AbstractFSM$ {
/*     */   public static final AbstractFSM$ MODULE$;
/*     */   
/*     */   private AbstractFSM$() {
/* 762 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <S, D> PartialFunction<S, D> NullFunction() {
/* 771 */     return FSM.NullFunction$.MODULE$;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractFSM$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */