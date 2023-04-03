/*    */ package akka.event;
/*    */ 
/*    */ import akka.actor.Actor;
/*    */ import akka.actor.ActorCell;
/*    */ import akka.actor.ActorContext;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Serializable;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class LoggingReceive$ {
/*    */   public static final LoggingReceive$ MODULE$;
/*    */   
/*    */   private LoggingReceive$() {
/* 13 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> apply(PartialFunction<Object, BoxedUnit> r, ActorContext context) {
/* 29 */     null;
/* 29 */     return withLabel(null, r, context);
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> create(PartialFunction<Object, BoxedUnit> r, ActorContext context) {
/* 35 */     return apply(r, context);
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> withLabel(String label, PartialFunction<Object, BoxedUnit> r, ActorContext context) {
/* 40 */     PartialFunction<Object, BoxedUnit> partialFunction2, partialFunction1 = r;
/* 41 */     if (partialFunction1 instanceof LoggingReceive) {
/* 41 */       partialFunction2 = r;
/*    */     } else {
/* 42 */       partialFunction2 = context.system().settings().AddLoggingReceive() ? new LoggingReceive((Option<Object>)scala.None$.MODULE$, r, scala.Option$.MODULE$.apply(label), context) : r;
/*    */     } 
/*    */     return partialFunction2;
/*    */   }
/*    */   
/*    */   public class $anonfun$1 extends AbstractFunction0<Actor> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Actor apply() {
/* 54 */       return ((ActorCell)this.$outer.akka$event$LoggingReceive$$context).actor();
/*    */     }
/*    */     
/*    */     public $anonfun$1(LoggingReceive $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\LoggingReceive$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */