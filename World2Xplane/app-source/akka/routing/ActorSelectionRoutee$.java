/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorSelection;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class ActorSelectionRoutee$ extends AbstractFunction1<ActorSelection, ActorSelectionRoutee> implements Serializable {
/*    */   public static final ActorSelectionRoutee$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 51 */     return "ActorSelectionRoutee";
/*    */   }
/*    */   
/*    */   public ActorSelectionRoutee apply(ActorSelection selection) {
/* 51 */     return new ActorSelectionRoutee(selection);
/*    */   }
/*    */   
/*    */   public Option<ActorSelection> unapply(ActorSelectionRoutee x$0) {
/* 51 */     return (x$0 == null) ? (Option<ActorSelection>)scala.None$.MODULE$ : (Option<ActorSelection>)new Some(x$0.selection());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 51 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ActorSelectionRoutee$() {
/* 51 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ActorSelectionRoutee$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */