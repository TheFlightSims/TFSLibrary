/*    */ package akka.actor;
/*    */ 
/*    */ import akka.actor.dsl.Creators;
/*    */ import akka.actor.dsl.Inbox;
/*    */ import scala.Function0;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ 
/*    */ public final class ActorDSL$ implements Inbox, Creators {
/*    */   public static final ActorDSL$ MODULE$;
/*    */   
/*    */   private final Ordering<Inbox.Query> akka$actor$dsl$Inbox$$deadlineOrder;
/*    */   
/*    */   private final FiniteDuration akka$actor$dsl$Inbox$$extraTime;
/*    */   
/*    */   public <T extends Actor> ActorRef actor(Function0 ctor, ClassTag evidence$1, ActorRefFactory factory) {
/* 75 */     return Creators.class.actor(this, ctor, evidence$1, factory);
/*    */   }
/*    */   
/*    */   public <T extends Actor> ActorRef actor(String name, Function0 ctor, ClassTag evidence$2, ActorRefFactory factory) {
/* 75 */     return Creators.class.actor(this, name, ctor, evidence$2, factory);
/*    */   }
/*    */   
/*    */   public <T extends Actor> ActorRef actor(ActorRefFactory factory, String name, Function0 ctor, ClassTag evidence$3) {
/* 75 */     return Creators.class.actor(this, factory, name, ctor, evidence$3);
/*    */   }
/*    */   
/*    */   public <T extends Actor> ActorRef actor(ActorRefFactory factory, Function0 ctor, ClassTag evidence$4) {
/* 75 */     return Creators.class.actor(this, factory, ctor, evidence$4);
/*    */   }
/*    */   
/*    */   public Ordering<Inbox.Query> akka$actor$dsl$Inbox$$deadlineOrder() {
/* 75 */     return this.akka$actor$dsl$Inbox$$deadlineOrder;
/*    */   }
/*    */   
/*    */   public FiniteDuration akka$actor$dsl$Inbox$$extraTime() {
/* 75 */     return this.akka$actor$dsl$Inbox$$extraTime;
/*    */   }
/*    */   
/*    */   public void akka$actor$dsl$Inbox$_setter_$akka$actor$dsl$Inbox$$deadlineOrder_$eq(Ordering<Inbox.Query> x$1) {
/* 75 */     this.akka$actor$dsl$Inbox$$deadlineOrder = x$1;
/*    */   }
/*    */   
/*    */   public void akka$actor$dsl$Inbox$_setter_$akka$actor$dsl$Inbox$$extraTime_$eq(FiniteDuration x$1) {
/* 75 */     this.akka$actor$dsl$Inbox$$extraTime = x$1;
/*    */   }
/*    */   
/*    */   public Inbox.Inbox inbox(ActorSystem system) {
/* 75 */     return Inbox.class.inbox(this, system);
/*    */   }
/*    */   
/*    */   public ActorRef senderFromInbox(Inbox.Inbox inbox) {
/* 75 */     return Inbox.class.senderFromInbox(this, inbox);
/*    */   }
/*    */   
/*    */   private ActorDSL$() {
/* 75 */     MODULE$ = this;
/* 75 */     Inbox.class.$init$(this);
/* 75 */     Creators.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorDSL$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */