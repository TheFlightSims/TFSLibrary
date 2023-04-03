/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.util.Index;
/*     */ import scala.Function2;
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class MessageDispatcher$ {
/*     */   public static final MessageDispatcher$ MODULE$;
/*     */   
/*     */   private final int UNSCHEDULED;
/*     */   
/*     */   private final int SCHEDULED;
/*     */   
/*     */   private final int RESCHEDULED;
/*     */   
/*     */   private final boolean debug;
/*     */   
/*     */   private Index<MessageDispatcher, ActorRef> actors;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   private MessageDispatcher$() {
/*  56 */     MODULE$ = this;
/*  57 */     this.UNSCHEDULED = 0;
/*  58 */     this.SCHEDULED = 1;
/*  59 */     this.RESCHEDULED = 2;
/*     */   }
/*     */   
/*     */   public int UNSCHEDULED() {
/*     */     return this.UNSCHEDULED;
/*     */   }
/*     */   
/*     */   public int SCHEDULED() {
/*     */     return this.SCHEDULED;
/*     */   }
/*     */   
/*     */   public int RESCHEDULED() {
/*  59 */     return this.RESCHEDULED;
/*     */   }
/*     */   
/*     */   public final boolean debug() {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   private Index actors$lzycompute() {
/*  64 */     synchronized (this) {
/*  64 */       if (!this.bitmap$0) {
/*  64 */         this.actors = new Index(16, (Function2)new MessageDispatcher$$anonfun$actors$1());
/*  64 */         this.bitmap$0 = true;
/*     */       } 
/*  64 */       return this.actors;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Index<MessageDispatcher, ActorRef> actors() {
/*  64 */     return this.bitmap$0 ? this.actors : actors$lzycompute();
/*     */   }
/*     */   
/*     */   public static class MessageDispatcher$$anonfun$actors$1 extends AbstractFunction2<ActorRef, ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(ActorRef x$1, ActorRef x$2) {
/*  64 */       return x$1.compareTo(x$2);
/*     */     }
/*     */   }
/*     */   
/*     */   public void printActors() {}
/*     */   
/*     */   public class MessageDispatcher$$anon$3 implements ExecutionContext {
/*     */     public ExecutionContext prepare() {
/* 173 */       return ExecutionContext.class.prepare(this);
/*     */     }
/*     */     
/*     */     public MessageDispatcher$$anon$3(MessageDispatcher $outer) {
/* 173 */       ExecutionContext.class.$init$(this);
/*     */     }
/*     */     
/*     */     public void execute(Runnable runnable) {
/* 174 */       runnable.run();
/*     */     }
/*     */     
/*     */     public void reportFailure(Throwable t) {
/* 175 */       this.$outer.reportFailure(t);
/*     */     }
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 181 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 181 */       if (this.$outer.akka$dispatch$MessageDispatcher$$addInhabitants(-1L) == 0L)
/* 181 */         this.$outer.akka$dispatch$MessageDispatcher$$ifSensibleToDoSoThenScheduleShutdown(); 
/*     */     }
/*     */     
/*     */     public $anonfun$1(MessageDispatcher $outer) {}
/*     */   }
/*     */   
/*     */   public class $anon$2 implements Runnable {
/*     */     public $anon$2(MessageDispatcher $outer) {}
/*     */     
/*     */     public final void run() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   4: invokevirtual akka$dispatch$MessageDispatcher$$shutdownSchedule : ()I
/*     */       //   7: istore_2
/*     */       //   8: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   11: invokevirtual SCHEDULED : ()I
/*     */       //   14: iload_2
/*     */       //   15: if_icmpne -> 73
/*     */       //   18: aload_0
/*     */       //   19: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   22: invokevirtual inhabitants : ()J
/*     */       //   25: lconst_0
/*     */       //   26: lcmp
/*     */       //   27: ifne -> 43
/*     */       //   30: aload_0
/*     */       //   31: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   34: invokevirtual shutdown : ()V
/*     */       //   37: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   40: goto -> 46
/*     */       //   43: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   46: aload_0
/*     */       //   47: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   50: aload_0
/*     */       //   51: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   54: invokevirtual akka$dispatch$MessageDispatcher$$shutdownSchedule : ()I
/*     */       //   57: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   60: invokevirtual UNSCHEDULED : ()I
/*     */       //   63: invokevirtual akka$dispatch$MessageDispatcher$$updateShutdownSchedule : (II)Z
/*     */       //   66: ifeq -> 46
/*     */       //   69: astore_3
/*     */       //   70: goto -> 133
/*     */       //   73: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   76: invokevirtual RESCHEDULED : ()I
/*     */       //   79: iload_2
/*     */       //   80: if_icmpne -> 119
/*     */       //   83: aload_0
/*     */       //   84: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   87: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   90: invokevirtual RESCHEDULED : ()I
/*     */       //   93: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   96: invokevirtual SCHEDULED : ()I
/*     */       //   99: invokevirtual akka$dispatch$MessageDispatcher$$updateShutdownSchedule : (II)Z
/*     */       //   102: ifeq -> 0
/*     */       //   105: aload_0
/*     */       //   106: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   109: invokevirtual akka$dispatch$MessageDispatcher$$scheduleShutdownAction : ()V
/*     */       //   112: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   115: astore_3
/*     */       //   116: goto -> 133
/*     */       //   119: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   122: invokevirtual UNSCHEDULED : ()I
/*     */       //   125: iload_2
/*     */       //   126: if_icmpne -> 138
/*     */       //   129: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   132: astore_3
/*     */       //   133: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   136: pop
/*     */       //   137: return
/*     */       //   138: new scala/MatchError
/*     */       //   141: dup
/*     */       //   142: iload_2
/*     */       //   143: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */       //   146: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   149: athrow
/*     */       //   150: astore #4
/*     */       //   152: aload_0
/*     */       //   153: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   156: aload_0
/*     */       //   157: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   160: invokevirtual akka$dispatch$MessageDispatcher$$shutdownSchedule : ()I
/*     */       //   163: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   166: invokevirtual UNSCHEDULED : ()I
/*     */       //   169: invokevirtual akka$dispatch$MessageDispatcher$$updateShutdownSchedule : (II)Z
/*     */       //   172: ifeq -> 152
/*     */       //   175: aload #4
/*     */       //   177: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #209	-> 0
/*     */       //   #210	-> 8
/*     */       //   #212	-> 18
/*     */       //   #214	-> 46
/*     */       //   #211	-> 69
/*     */       //   #216	-> 73
/*     */       //   #217	-> 83
/*     */       //   #219	-> 119
/*     */       //   #209	-> 133
/*     */       //   #214	-> 150
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	178	0	this	Lakka/dispatch/MessageDispatcher$$anon$2;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   18	46	150	finally
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\MessageDispatcher$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */