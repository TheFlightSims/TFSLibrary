/*    */ package akka.japi.pf;
/*    */ 
/*    */ import akka.actor.SupervisorStrategy;
/*    */ 
/*    */ public class DeciderBuilder {
/*    */   public static <P extends Throwable> PFBuilder<Throwable, SupervisorStrategy.Directive> match(Class<P> paramClass, FI.Apply<P, SupervisorStrategy.Directive> paramApply) {
/* 44 */     return Match.match(paramClass, paramApply);
/*    */   }
/*    */   
/*    */   public static <P extends Throwable> PFBuilder<Throwable, SupervisorStrategy.Directive> match(Class<P> paramClass, FI.TypedPredicate<P> paramTypedPredicate, FI.Apply<P, SupervisorStrategy.Directive> paramApply) {
/* 58 */     return Match.match(paramClass, paramTypedPredicate, paramApply);
/*    */   }
/*    */   
/*    */   public static PFBuilder<Throwable, SupervisorStrategy.Directive> matchAny(FI.Apply<Object, SupervisorStrategy.Directive> paramApply) {
/* 68 */     return Match.matchAny(paramApply);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\DeciderBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */