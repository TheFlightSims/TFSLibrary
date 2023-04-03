/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.OneForOneStrategy;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.event.Logging;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class SelectionHandler$ {
/*     */   public static final SelectionHandler$ MODULE$;
/*     */   
/*     */   private final SupervisorStrategy connectionSupervisorStrategy;
/*     */   
/*     */   private SelectionHandler$() {
/*  62 */     MODULE$ = this;
/*  95 */     this.connectionSupervisorStrategy = 
/*  96 */       (SupervisorStrategy)new SelectionHandler.$anon$1();
/*     */   }
/*     */   
/*     */   public final SupervisorStrategy connectionSupervisorStrategy() {
/*     */     return this.connectionSupervisorStrategy;
/*     */   }
/*     */   
/*     */   public static class $anon$1 extends OneForOneStrategy {
/*     */     public $anon$1() {
/*  96 */       super(akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$1(), akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$2(), akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$3(), akka.actor.SupervisorStrategy$.MODULE$.stoppingStrategy().decider());
/*     */     }
/*     */     
/*     */     public void logFailure(ActorContext context, ActorRef child, Throwable cause, SupervisorStrategy.Directive decision) {
/*  99 */       if (cause instanceof akka.actor.DeathPactException) {
/*     */         try {
/* 100 */           context.system().eventStream().publish(
/* 101 */               new Logging.Debug(child.path().toString(), getClass(), "Closed after handler termination"));
/*     */         } finally {
/*     */           BoxedUnit boxedUnit;
/*     */           Exception exception1 = null, exception2 = exception1;
/* 102 */           Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/* 102 */           if (option.isEmpty())
/*     */             throw exception1; 
/*     */         } 
/*     */       } else {
/* 103 */         super.logFailure(context, child, cause, decision);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class SelectionHandler$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/* 237 */       Object object2, object1 = x2;
/* 238 */       if (object1 instanceof SelectionHandler.WorkerForCommand) {
/* 238 */         SelectionHandler.WorkerForCommand workerForCommand = (SelectionHandler.WorkerForCommand)object1;
/* 238 */         this.$outer.spawnChildWithCapacityProtection(workerForCommand, this.$outer.akka$io$SelectionHandler$$settings.SelectorAssociationRetries());
/* 238 */         object2 = BoxedUnit.UNIT;
/* 240 */       } else if (object1 instanceof SelectionHandler.Retry) {
/* 240 */         SelectionHandler.Retry retry = (SelectionHandler.Retry)object1;
/* 240 */         SelectionHandler.WorkerForCommand cmd = retry.command();
/* 240 */         int retriesLeft = retry.retriesLeft();
/* 240 */         this.$outer.spawnChildWithCapacityProtection(cmd, retriesLeft);
/* 240 */         object2 = BoxedUnit.UNIT;
/* 244 */       } else if (object1 instanceof akka.actor.Terminated) {
/* 244 */         this.$outer.akka$io$SelectionHandler$$childCount--;
/* 244 */         object2 = BoxedUnit.UNIT;
/*     */       } else {
/*     */         object2 = default.apply(x2);
/*     */       } 
/*     */       return (B1)object2;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x2) {
/*     */       boolean bool;
/*     */       Object object = x2;
/*     */       if (object instanceof SelectionHandler.WorkerForCommand) {
/*     */         bool = true;
/*     */       } else if (object instanceof SelectionHandler.Retry) {
/*     */         bool = true;
/* 244 */       } else if (object instanceof akka.actor.Terminated) {
/* 244 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public SelectionHandler$$anonfun$receive$1(SelectionHandler $outer) {}
/*     */   }
/*     */   
/*     */   public class SelectionHandler$$anonfun$akka$io$SelectionHandler$$stoppingDecider$1$1 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x3, Function1 default) {
/*     */       Object object;
/* 252 */       Throwable throwable = x3;
/* 253 */       if (throwable instanceof Exception) {
/* 253 */         object = akka.actor.SupervisorStrategy$Stop$.MODULE$;
/*     */       } else {
/*     */         object = default.apply(x3);
/*     */       } 
/*     */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x3) {
/*     */       boolean bool;
/*     */       Throwable throwable = x3;
/* 253 */       if (throwable instanceof Exception) {
/* 253 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public SelectionHandler$$anonfun$akka$io$SelectionHandler$$stoppingDecider$1$1(SelectionHandler $outer) {}
/*     */   }
/*     */   
/*     */   public class SelectionHandler$$anon$2 extends OneForOneStrategy {
/*     */     public SelectionHandler$$anon$2(SelectionHandler $outer) {
/* 255 */       super(akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$1(), akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$2(), akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$3(), $outer.akka$io$SelectionHandler$$stoppingDecider$1());
/*     */     }
/*     */     
/*     */     public void logFailure(ActorContext context, ActorRef child, Throwable cause, SupervisorStrategy.Directive decision) {
/*     */       // Byte code:
/*     */       //   0: aload_3
/*     */       //   1: astore #10
/*     */       //   3: aload #10
/*     */       //   5: instanceof akka/actor/ActorInitializationException
/*     */       //   8: ifeq -> 39
/*     */       //   11: aload #10
/*     */       //   13: checkcast akka/actor/ActorInitializationException
/*     */       //   16: astore #11
/*     */       //   18: aload #11
/*     */       //   20: invokevirtual getCause : ()Ljava/lang/Throwable;
/*     */       //   23: ifnull -> 39
/*     */       //   26: aload #11
/*     */       //   28: invokevirtual getCause : ()Ljava/lang/Throwable;
/*     */       //   31: invokevirtual getMessage : ()Ljava/lang/String;
/*     */       //   34: astore #12
/*     */       //   36: goto -> 46
/*     */       //   39: aload #10
/*     */       //   41: invokevirtual getMessage : ()Ljava/lang/String;
/*     */       //   44: astore #12
/*     */       //   46: aload #12
/*     */       //   48: astore #9
/*     */       //   50: aload_1
/*     */       //   51: invokeinterface system : ()Lakka/actor/ActorSystem;
/*     */       //   56: invokevirtual eventStream : ()Lakka/event/EventStream;
/*     */       //   59: new akka/event/Logging$Debug
/*     */       //   62: dup
/*     */       //   63: aload_2
/*     */       //   64: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */       //   67: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   70: ldc akka/io/SelectionHandler
/*     */       //   72: aload #9
/*     */       //   74: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)V
/*     */       //   77: invokevirtual publish : (Ljava/lang/Object;)V
/*     */       //   80: goto -> 115
/*     */       //   83: astore #5
/*     */       //   85: aload #5
/*     */       //   87: astore #6
/*     */       //   89: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */       //   92: aload #6
/*     */       //   94: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */       //   97: astore #7
/*     */       //   99: aload #7
/*     */       //   101: invokevirtual isEmpty : ()Z
/*     */       //   104: ifeq -> 110
/*     */       //   107: aload #5
/*     */       //   109: athrow
/*     */       //   110: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   113: astore #8
/*     */       //   115: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #259	-> 0
/*     */       //   #260	-> 3
/*     */       //   #261	-> 39
/*     */       //   #259	-> 46
/*     */       //   #263	-> 50
/*     */       //   #264	-> 59
/*     */       //   #263	-> 77
/*     */       //   #258	-> 83
/*     */       //   #265	-> 89
/*     */       //   #258	-> 107
/*     */       //   #265	-> 110
/*     */       //   #258	-> 115
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	116	0	this	Lakka/io/SelectionHandler$$anon$2;
/*     */       //   0	116	1	context	Lakka/actor/ActorContext;
/*     */       //   0	116	2	child	Lakka/actor/ActorRef;
/*     */       //   0	116	3	cause	Ljava/lang/Throwable;
/*     */       //   0	116	4	decision	Lakka/actor/SupervisorStrategy$Directive;
/*     */       //   50	30	9	logMessage	Ljava/lang/String;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   0	83	83	finally
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\SelectionHandler$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */