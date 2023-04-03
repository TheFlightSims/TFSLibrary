/*    */ package akka;
/*    */ 
/*    */ import akka.actor.Actor;
/*    */ import akka.actor.ActorContext;
/*    */ import akka.actor.ActorLogging;
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.SupervisorStrategy;
/*    */ import akka.event.LoggingAdapter;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\021;Q!\001\002\t\002\025\tA!T1j]*\t1!\001\003bW.\f7\001\001\t\003\r\035i\021A\001\004\006\021\tA\t!\003\002\005\033\006Lgn\005\002\b\025A\0211BD\007\002\031)\tQ\"A\003tG\006d\027-\003\002\020\031\t1\021I\\=SK\032DQ!E\004\005\002I\ta\001P5oSRtD#A\003\t\013Q9A\021A\013\002\t5\f\027N\034\013\003-e\001\"aC\f\n\005aa!\001B+oSRDQAG\nA\002m\tA!\031:hgB\0311\002\b\020\n\005ua!!B!se\006L\bCA\020#\035\tY\001%\003\002\"\031\0051\001K]3eK\032L!a\t\023\003\rM#(/\0338h\025\t\tCB\002\003'\017\0019#A\003+fe6Lg.\031;peN!QE\003\025/!\tIC&D\001+\025\tY#!A\003bGR|'/\003\002.U\t)\021i\031;peB\021\021fL\005\003a)\022A\"Q2u_JdunZ4j]\036D\001BM\023\003\002\003\006IaM\001\004CB\004\bCA\0255\023\t)$F\001\005BGR|'OU3g\021\025\tR\005\"\0018)\tA$\b\005\002:K5\tq\001C\0033m\001\0071\007C\003=K\021\005Q(A\004sK\016,\027N^3\026\003y\002BaC B-%\021\001\t\004\002\020!\006\024H/[1m\rVt7\r^5p]B\0211BQ\005\003\0072\0211!\0218z\001")
/*    */ public final class Main {
/*    */   public static void main(String[] paramArrayOfString) {
/*    */     Main$.MODULE$.main(paramArrayOfString);
/*    */   }
/*    */   
/*    */   public static class Terminator implements Actor, ActorLogging {
/*    */     private LoggingAdapter akka$actor$ActorLogging$$_log;
/*    */     
/*    */     private final ActorContext context;
/*    */     
/*    */     private final ActorRef self;
/*    */     
/*    */     public LoggingAdapter akka$actor$ActorLogging$$_log() {
/* 41 */       return this.akka$actor$ActorLogging$$_log;
/*    */     }
/*    */     
/*    */     public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/* 41 */       this.akka$actor$ActorLogging$$_log = x$1;
/*    */     }
/*    */     
/*    */     public LoggingAdapter log() {
/* 41 */       return ActorLogging.class.log(this);
/*    */     }
/*    */     
/*    */     public ActorContext context() {
/* 41 */       return this.context;
/*    */     }
/*    */     
/*    */     public final ActorRef self() {
/* 41 */       return this.self;
/*    */     }
/*    */     
/*    */     public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/* 41 */       this.context = x$1;
/*    */     }
/*    */     
/*    */     public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/* 41 */       this.self = x$1;
/*    */     }
/*    */     
/*    */     public final ActorRef sender() {
/* 41 */       return Actor.class.sender(this);
/*    */     }
/*    */     
/*    */     public void aroundReceive(PartialFunction receive, Object msg) {
/* 41 */       Actor.class.aroundReceive(this, receive, msg);
/*    */     }
/*    */     
/*    */     public void aroundPreStart() {
/* 41 */       Actor.class.aroundPreStart(this);
/*    */     }
/*    */     
/*    */     public void aroundPostStop() {
/* 41 */       Actor.class.aroundPostStop(this);
/*    */     }
/*    */     
/*    */     public void aroundPreRestart(Throwable reason, Option message) {
/* 41 */       Actor.class.aroundPreRestart(this, reason, message);
/*    */     }
/*    */     
/*    */     public void aroundPostRestart(Throwable reason) {
/* 41 */       Actor.class.aroundPostRestart(this, reason);
/*    */     }
/*    */     
/*    */     public SupervisorStrategy supervisorStrategy() {
/* 41 */       return Actor.class.supervisorStrategy(this);
/*    */     }
/*    */     
/*    */     public void preStart() throws Exception {
/* 41 */       Actor.class.preStart(this);
/*    */     }
/*    */     
/*    */     public void postStop() throws Exception {
/* 41 */       Actor.class.postStop(this);
/*    */     }
/*    */     
/*    */     public void preRestart(Throwable reason, Option message) throws Exception {
/* 41 */       Actor.class.preRestart(this, reason, message);
/*    */     }
/*    */     
/*    */     public void postRestart(Throwable reason) throws Exception {
/* 41 */       Actor.class.postRestart(this, reason);
/*    */     }
/*    */     
/*    */     public void unhandled(Object message) {
/* 41 */       Actor.class.unhandled(this, message);
/*    */     }
/*    */     
/*    */     public Terminator(ActorRef app) {
/* 41 */       Actor.class.$init$(this);
/* 41 */       ActorLogging.class.$init$(this);
/* 42 */       context().watch(app);
/*    */     }
/*    */     
/*    */     public PartialFunction<Object, BoxedUnit> receive() {
/* 43 */       return (PartialFunction<Object, BoxedUnit>)new Main$Terminator$$anonfun$receive$1(this);
/*    */     }
/*    */     
/*    */     public class Main$Terminator$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/* 43 */         Object object2, object1 = x1;
/* 44 */         if (object1 instanceof akka.actor.Terminated) {
/* 45 */           this.$outer.log().info("application supervisor has terminated, shutting down");
/* 46 */           this.$outer.context().system().shutdown();
/* 46 */           object2 = BoxedUnit.UNIT;
/*    */         } else {
/*    */           object2 = default.apply(x1);
/*    */         } 
/*    */         return (B1)object2;
/*    */       }
/*    */       
/*    */       public final boolean isDefinedAt(Object x1) {
/*    */         boolean bool;
/*    */         Object object = x1;
/*    */         if (object instanceof akka.actor.Terminated) {
/*    */           bool = true;
/*    */         } else {
/*    */           bool = false;
/*    */         } 
/*    */         return bool;
/*    */       }
/*    */       
/*    */       public Main$Terminator$$anonfun$receive$1(Main.Terminator $outer) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */