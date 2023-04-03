/*    */ package akka.pattern;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.InternalActorRef;
/*    */ import akka.dispatch.sysmsg.SystemMessage;
/*    */ import akka.dispatch.sysmsg.Unwatch;
/*    */ import scala.Serializable;
/*    */ import scala.concurrent.Future;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t3q!\001\002\021\002\007\005qAA\nHe\006\034WMZ;m'R|\007oU;qa>\024HO\003\002\004\t\0059\001/\031;uKJt'\"A\003\002\t\005\\7.Y\002\001'\t\001\001\002\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\005\006\037\001!\t\001E\001\007I%t\027\016\036\023\025\003E\001\"!\003\n\n\005MQ!\001B+oSRDQ!\006\001\005\002Y\tAb\032:bG\0264W\017\\*u_B$Ba\006\021)aA\031\001dG\017\016\003eQ!A\007\006\002\025\r|gnY;se\026tG/\003\002\0353\t1a)\036;ve\026\004\"!\003\020\n\005}Q!a\002\"p_2,\027M\034\005\006CQ\001\rAI\001\007i\006\024x-\032;\021\005\r2S\"\001\023\013\005\025\"\021!B1di>\024\030BA\024%\005!\t5\r^8s%\0264\007\"B\025\025\001\004Q\023a\002;j[\026|W\017\036\t\003W9j\021\001\f\006\003[e\t\001\002Z;sCRLwN\\\005\003_1\022aBR5oSR,G)\036:bi&|g\016C\0042)A\005\t\031\001\032\002\027M$x\016]'fgN\fw-\032\t\003\023MJ!\001\016\006\003\007\005s\027\020C\0047\001E\005I\021A\034\002-\035\024\030mY3gk2\034Fo\0349%I\0264\027-\0367uIM*\022\001\017\026\003eeZ\023A\017\t\003w\001k\021\001\020\006\003{y\n\021\"\0368dQ\026\0347.\0323\013\005}R\021AC1o]>$\030\r^5p]&\021\021\t\020\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007")
/*    */ public interface GracefulStopSupport {
/*    */   Future<Object> gracefulStop(ActorRef paramActorRef, FiniteDuration paramFiniteDuration, Object paramObject);
/*    */   
/*    */   Object gracefulStop$default$3();
/*    */   
/*    */   public class GracefulStopSupport$$anonfun$gracefulStop$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final InternalActorRef internalTarget$1;
/*    */     
/*    */     private final PromiseActorRef ref$1;
/*    */     
/*    */     private final ActorRef target$1;
/*    */     
/*    */     public final boolean apply(Object x0$1) {
/*    */       // Byte code:
/*    */       //   0: aload_1
/*    */       //   1: astore_2
/*    */       //   2: aload_2
/*    */       //   3: instanceof akka/actor/Terminated
/*    */       //   6: ifeq -> 61
/*    */       //   9: aload_2
/*    */       //   10: checkcast akka/actor/Terminated
/*    */       //   13: astore_3
/*    */       //   14: aload_3
/*    */       //   15: invokevirtual actor : ()Lakka/actor/ActorRef;
/*    */       //   18: astore #4
/*    */       //   20: aload #4
/*    */       //   22: invokevirtual path : ()Lakka/actor/ActorPath;
/*    */       //   25: aload_0
/*    */       //   26: getfield target$1 : Lakka/actor/ActorRef;
/*    */       //   29: invokevirtual path : ()Lakka/actor/ActorPath;
/*    */       //   32: astore #5
/*    */       //   34: dup
/*    */       //   35: ifnonnull -> 47
/*    */       //   38: pop
/*    */       //   39: aload #5
/*    */       //   41: ifnull -> 55
/*    */       //   44: goto -> 61
/*    */       //   47: aload #5
/*    */       //   49: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   52: ifeq -> 61
/*    */       //   55: iconst_1
/*    */       //   56: istore #6
/*    */       //   58: goto -> 86
/*    */       //   61: aload_0
/*    */       //   62: getfield internalTarget$1 : Lakka/actor/InternalActorRef;
/*    */       //   65: new akka/dispatch/sysmsg/Unwatch
/*    */       //   68: dup
/*    */       //   69: aload_0
/*    */       //   70: getfield target$1 : Lakka/actor/ActorRef;
/*    */       //   73: aload_0
/*    */       //   74: getfield ref$1 : Lakka/pattern/PromiseActorRef;
/*    */       //   77: invokespecial <init> : (Lakka/actor/ActorRef;Lakka/actor/ActorRef;)V
/*    */       //   80: invokevirtual sendSystemMessage : (Lakka/dispatch/sysmsg/SystemMessage;)V
/*    */       //   83: iconst_0
/*    */       //   84: istore #6
/*    */       //   86: iload #6
/*    */       //   88: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #56	-> 0
/*    */       //   #57	-> 2
/*    */       //   #58	-> 61
/*    */       //   #56	-> 86
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	89	0	this	Lakka/pattern/GracefulStopSupport$$anonfun$gracefulStop$1;
/*    */       //   0	89	1	x0$1	Ljava/lang/Object;
/*    */       //   20	69	4	t	Lakka/actor/ActorRef;
/*    */     }
/*    */     
/*    */     public GracefulStopSupport$$anonfun$gracefulStop$1(GracefulStopSupport $outer, InternalActorRef internalTarget$1, PromiseActorRef ref$1, ActorRef target$1) {}
/*    */   }
/*    */   
/*    */   public class GracefulStopSupport$$anonfun$gracefulStop$2 extends AbstractFunction1<Throwable, Throwable> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final InternalActorRef internalTarget$1;
/*    */     
/*    */     private final PromiseActorRef ref$1;
/*    */     
/*    */     private final ActorRef target$1;
/*    */     
/*    */     public final Throwable apply(Throwable t) {
/* 60 */       this.internalTarget$1.sendSystemMessage((SystemMessage)new Unwatch(this.target$1, (ActorRef)this.ref$1));
/* 60 */       return t;
/*    */     }
/*    */     
/*    */     public GracefulStopSupport$$anonfun$gracefulStop$2(GracefulStopSupport $outer, InternalActorRef internalTarget$1, PromiseActorRef ref$1, ActorRef target$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\GracefulStopSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */