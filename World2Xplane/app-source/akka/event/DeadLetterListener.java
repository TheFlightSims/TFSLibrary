/*    */ package akka.event;
/*    */ 
/*    */ import akka.actor.Actor;
/*    */ import akka.actor.ActorContext;
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.DeadLetter;
/*    */ import akka.actor.SupervisorStrategy;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.StringContext;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001u3A!\001\002\001\017\t\021B)Z1e\031\026$H/\032:MSN$XM\\3s\025\t\031A!A\003fm\026tGOC\001\006\003\021\t7n[1\004\001M\031\001\001\003\b\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty!#D\001\021\025\t\tB!A\003bGR|'/\003\002\024!\t)\021i\031;pe\")Q\003\001C\001-\0051A(\0338jiz\"\022a\006\t\0031\001i\021A\001\005\b5\001\021\r\021\"\001\034\003-)g/\0328u'R\024X-Y7\026\003q\001\"\001G\017\n\005y\021!aC#wK:$8\013\036:fC6Da\001\t\001!\002\023a\022\001D3wK:$8\013\036:fC6\004\003b\002\022\001\005\004%\taI\001\t[\006D8i\\;oiV\tA\005\005\002\nK%\021aE\003\002\004\023:$\bB\002\025\001A\003%A%A\005nCb\034u.\0368uA!9!\006\001a\001\n\003\031\023!B2pk:$\bb\002\027\001\001\004%\t!L\001\nG>,h\016^0%KF$\"AL\031\021\005%y\023B\001\031\013\005\021)f.\033;\t\017IZ\023\021!a\001I\005\031\001\020J\031\t\rQ\002\001\025)\003%\003\031\031w.\0368uA!)a\007\001C!o\005A\001O]3Ti\006\024H\017F\001/\021\025I\004\001\"\021;\003-\001xn\035;SKN$\030M\035;\025\0059Z\004\"\002\0379\001\004i\024A\002:fCN|g\016\005\002?\r:\021q\b\022\b\003\001\016k\021!\021\006\003\005\032\ta\001\020:p_Rt\024\"A\006\n\005\025S\021a\0029bG.\fw-Z\005\003\017\"\023\021\002\0265s_^\f'\r\\3\013\005\025S\001\"\002&\001\t\003Z\025A\0039sKJ+7\017^1siR\031a\006T'\t\013qJ\005\031A\037\t\0139K\005\031A(\002\0175,7o]1hKB\031\021\002\025*\n\005ES!AB(qi&|g\016\005\002\n'&\021AK\003\002\004\003:L\b\"\002,\001\t\003:\024\001\0039pgR\034Fo\0349\t\013a\003A\021A-\002\017I,7-Z5wKV\t!\f\005\003\n7Js\023B\001/\013\005=\001\026M\035;jC24UO\\2uS>t\007")
/*    */ public class DeadLetterListener implements Actor {
/*    */   private final EventStream eventStream;
/*    */   
/*    */   private final int maxCount;
/*    */   
/*    */   private int count;
/*    */   
/*    */   private final ActorContext context;
/*    */   
/*    */   private final ActorRef self;
/*    */   
/*    */   public ActorContext context() {
/* 10 */     return this.context;
/*    */   }
/*    */   
/*    */   public final ActorRef self() {
/* 10 */     return this.self;
/*    */   }
/*    */   
/*    */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/* 10 */     this.context = x$1;
/*    */   }
/*    */   
/*    */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/* 10 */     this.self = x$1;
/*    */   }
/*    */   
/*    */   public final ActorRef sender() {
/* 10 */     return Actor.class.sender(this);
/*    */   }
/*    */   
/*    */   public void aroundReceive(PartialFunction receive, Object msg) {
/* 10 */     Actor.class.aroundReceive(this, receive, msg);
/*    */   }
/*    */   
/*    */   public void aroundPreStart() {
/* 10 */     Actor.class.aroundPreStart(this);
/*    */   }
/*    */   
/*    */   public void aroundPostStop() {
/* 10 */     Actor.class.aroundPostStop(this);
/*    */   }
/*    */   
/*    */   public void aroundPreRestart(Throwable reason, Option message) {
/* 10 */     Actor.class.aroundPreRestart(this, reason, message);
/*    */   }
/*    */   
/*    */   public void aroundPostRestart(Throwable reason) {
/* 10 */     Actor.class.aroundPostRestart(this, reason);
/*    */   }
/*    */   
/*    */   public SupervisorStrategy supervisorStrategy() {
/* 10 */     return Actor.class.supervisorStrategy(this);
/*    */   }
/*    */   
/*    */   public void unhandled(Object message) {
/* 10 */     Actor.class.unhandled(this, message);
/*    */   }
/*    */   
/*    */   public DeadLetterListener() {
/* 10 */     Actor.class.$init$(this);
/* 12 */     this.eventStream = context().system().eventStream();
/* 13 */     this.maxCount = context().system().settings().LogDeadLetters();
/* 14 */     this.count = 0;
/*    */   }
/*    */   
/*    */   public EventStream eventStream() {
/*    */     return this.eventStream;
/*    */   }
/*    */   
/*    */   public int maxCount() {
/*    */     return this.maxCount;
/*    */   }
/*    */   
/*    */   public int count() {
/* 14 */     return this.count;
/*    */   }
/*    */   
/*    */   public void count_$eq(int x$1) {
/* 14 */     this.count = x$1;
/*    */   }
/*    */   
/*    */   public void preStart() {
/* 17 */     eventStream().subscribe(self(), DeadLetter.class);
/*    */   }
/*    */   
/*    */   public void postRestart(Throwable reason) {}
/*    */   
/*    */   public void preRestart(Throwable reason, Option message) {}
/*    */   
/*    */   public void postStop() {
/* 26 */     eventStream().unsubscribe(self());
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> receive() {
/* 28 */     return (PartialFunction<Object, BoxedUnit>)new DeadLetterListener$$anonfun$receive$1(this);
/*    */   }
/*    */   
/*    */   public class DeadLetterListener$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/* 28 */       Object object2, object1 = x1;
/* 29 */       if (object1 instanceof DeadLetter) {
/* 29 */         DeadLetter deadLetter = (DeadLetter)object1;
/* 29 */         Object message = deadLetter.message();
/* 29 */         ActorRef snd = deadLetter.sender(), rcp = deadLetter.recipient();
/* 30 */         this.$outer.count_$eq(this.$outer.count() + 1);
/* 31 */         boolean done = (this.$outer.maxCount() != Integer.MAX_VALUE && this.$outer.count() >= this.$outer.maxCount());
/* 32 */         String doneMsg = done ? ", no more dead letters will be logged" : "";
/* 34 */         (new String[6])[0] = "Message [";
/* 34 */         (new String[6])[1] = "] from ";
/* 34 */         (new String[6])[2] = " to ";
/* 34 */         (new String[6])[3] = " was not delivered. [";
/* 34 */         (new String[6])[4] = "] dead letters encountered";
/* 34 */         (new String[6])[5] = ". ";
/* 35 */         this.$outer.eventStream().publish(new Logging.Info(rcp.path().toString(), rcp.getClass(), (new StringBuilder()).append((new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[6]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { message.getClass().getName(), snd, rcp, BoxesRunTime.boxToInteger(this.$outer.count()), doneMsg }))).append("This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' ")
/* 36 */               .append("and 'akka.log-dead-letters-during-shutdown'.").toString()));
/* 37 */         this.$outer.context().stop(this.$outer.self());
/* 37 */         object2 = done ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*    */       } else {
/*    */         object2 = default.apply(x1);
/*    */       } 
/*    */       return (B1)object2;
/*    */     }
/*    */     
/*    */     public final boolean isDefinedAt(Object x1) {
/*    */       boolean bool;
/*    */       Object object = x1;
/*    */       if (object instanceof DeadLetter) {
/*    */         bool = true;
/*    */       } else {
/*    */         bool = false;
/*    */       } 
/*    */       return bool;
/*    */     }
/*    */     
/*    */     public DeadLetterListener$$anonfun$receive$1(DeadLetterListener $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\DeadLetterListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */