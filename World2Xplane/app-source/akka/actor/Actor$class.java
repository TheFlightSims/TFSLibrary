/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.StringContext;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public abstract class Actor$class {
/*     */   public static void $init$(Actor $this) {
/* 419 */     List contextStack = ActorCell$.MODULE$.contextStack().get();
/* 420 */     if (contextStack.isEmpty() || contextStack.head() == null) {
/* 422 */       (new String[2])[0] = "You cannot create an instance of [";
/* 422 */       (new String[2])[1] = "] explicitly using the constructor (new). ";
/* 422 */       throw ActorInitializationException$.MODULE$.apply((new StringBuilder()).append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { $this.getClass().getName() }))).append("You have to use one of the 'actorOf' factory methods to create a new actor. See the documentation.").toString());
/*     */     } 
/* 424 */     ActorContext c = (ActorContext)contextStack.head();
/* 425 */     Object object = null;
/* 425 */     ActorCell$.MODULE$.contextStack().set(contextStack.$colon$colon(null));
/* 426 */     $this.akka$actor$Actor$_setter_$context_$eq(c);
/* 437 */     $this.akka$actor$Actor$_setter_$self_$eq($this.context().self());
/*     */   }
/*     */   
/*     */   public static final ActorRef sender(Actor $this) {
/* 447 */     return $this.context().sender();
/*     */   }
/*     */   
/*     */   public static void aroundReceive(Actor $this, PartialFunction receive, Object msg) {
/* 465 */     receive.applyOrElse(msg, (Function1)new Actor$$anonfun$aroundReceive$1($this));
/*     */   }
/*     */   
/*     */   public static void aroundPreStart(Actor $this) {
/* 470 */     $this.preStart();
/*     */   }
/*     */   
/*     */   public static void aroundPostStop(Actor $this) {
/* 475 */     $this.postStop();
/*     */   }
/*     */   
/*     */   public static void aroundPreRestart(Actor $this, Throwable reason, Option<Object> message) {
/* 480 */     $this.preRestart(reason, message);
/*     */   }
/*     */   
/*     */   public static void aroundPostRestart(Actor $this, Throwable reason) {
/* 485 */     $this.postRestart(reason);
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy supervisorStrategy(Actor $this) {
/* 491 */     return SupervisorStrategy$.MODULE$.defaultStrategy();
/*     */   }
/*     */   
/*     */   public static void preStart(Actor $this) throws Exception {}
/*     */   
/*     */   public static void postStop(Actor $this) throws Exception {}
/*     */   
/*     */   public static void preRestart(Actor $this, Throwable reason, Option message) throws Exception {
/* 529 */     $this.context().children().foreach((Function1)new Actor$$anonfun$preRestart$1($this));
/* 533 */     $this.postStop();
/*     */   }
/*     */   
/*     */   public static void postRestart(Actor $this, Throwable reason) throws Exception {
/* 547 */     $this.preStart();
/*     */   }
/*     */   
/*     */   public static void unhandled(Actor $this, Object message) {
/* 560 */     Object object = message;
/* 561 */     if (object instanceof Terminated) {
/* 561 */       Terminated terminated = (Terminated)object;
/* 561 */       ActorRef dead = terminated.actor();
/* 561 */       throw new DeathPactException(dead);
/*     */     } 
/* 562 */     $this.context().system().eventStream().publish(new UnhandledMessage(message, $this.sender(), $this.self()));
/* 562 */     BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Actor$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */