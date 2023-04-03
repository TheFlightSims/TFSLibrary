/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Serializable;
/*    */ 
/*    */ public final class ActorRef$ implements Serializable {
/*    */   public static final ActorRef$ MODULE$;
/*    */   
/*    */   private final ActorRef noSender;
/*    */   
/*    */   private ActorRef$() {
/* 17 */     MODULE$ = this;
/* 23 */     this.noSender = Actor$.MODULE$.noSender();
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/*    */     return MODULE$;
/*    */   }
/*    */   
/*    */   public final ActorRef noSender() {
/* 23 */     return this.noSender;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorRef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */