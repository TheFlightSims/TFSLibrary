/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class ActorIdentity$ extends AbstractFunction2<Object, Option<ActorRef>, ActorIdentity> implements Serializable {
/*    */   public static final ActorIdentity$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 73 */     return "ActorIdentity";
/*    */   }
/*    */   
/*    */   public ActorIdentity apply(Object correlationId, Option<ActorRef> ref) {
/* 73 */     return new ActorIdentity(correlationId, ref);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Option<ActorRef>>> unapply(ActorIdentity x$0) {
/* 73 */     return (x$0 == null) ? (Option<Tuple2<Object, Option<ActorRef>>>)scala.None$.MODULE$ : (Option<Tuple2<Object, Option<ActorRef>>>)new Some(new Tuple2(x$0.correlationId(), x$0.ref()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 73 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ActorIdentity$() {
/* 73 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorIdentity$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */