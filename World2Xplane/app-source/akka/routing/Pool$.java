/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.OneForOneStrategy;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Function1;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ 
/*     */ public final class Pool$ implements Serializable {
/*     */   public static final Pool$ MODULE$;
/*     */   
/*     */   private final SupervisorStrategy defaultSupervisorStrategy;
/*     */   
/*     */   private Pool$() {
/* 163 */     MODULE$ = this;
/* 164 */     this.defaultSupervisorStrategy = (SupervisorStrategy)new OneForOneStrategy(akka.actor.OneForOneStrategy$.MODULE$.apply$default$1(), akka.actor.OneForOneStrategy$.MODULE$.apply$default$2(), akka.actor.OneForOneStrategy$.MODULE$.apply$default$3(), (PartialFunction)new Pool.$anonfun$1());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*     */     return MODULE$;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy defaultSupervisorStrategy() {
/* 164 */     return this.defaultSupervisorStrategy;
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/* 164 */       Throwable throwable = x1;
/* 164 */       return 
/* 165 */         (B1)akka.actor.SupervisorStrategy$Escalate$.MODULE$;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       Throwable throwable = x1;
/*     */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Pool$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */