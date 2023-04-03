/*     */ package akka.actor.dungeon;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.ChildRestartStats;
/*     */ import akka.dispatch.Envelope$;
/*     */ import akka.dispatch.sysmsg.DeathWatchNotification;
/*     */ import akka.dispatch.sysmsg.Failed;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.event.Logging;
/*     */ import akka.event.Logging$Error$;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.Duration$;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class FaultHandling$class {
/*     */   private static void suspendNonRecursive(ActorCell $this) {
/*  36 */     $this.dispatcher().suspend($this);
/*     */   }
/*     */   
/*     */   private static void resumeNonRecursive(ActorCell $this) {
/*  38 */     $this.dispatcher().resume($this);
/*     */   }
/*     */   
/*     */   public static void $init$(ActorCell $this) {
/*  46 */     null;
/*  46 */     $this.akka$actor$dungeon$FaultHandling$$_failed_$eq(null);
/*     */   }
/*     */   
/*     */   private static boolean isFailed(ActorCell $this) {
/*  47 */     return !($this.akka$actor$dungeon$FaultHandling$$_failed() == null);
/*     */   }
/*     */   
/*     */   private static void setFailed(ActorCell $this, ActorRef perpetrator) {
/*  48 */     $this.akka$actor$dungeon$FaultHandling$$_failed_$eq(perpetrator);
/*     */   }
/*     */   
/*     */   private static void clearFailed(ActorCell $this) {
/*  49 */     null;
/*  49 */     $this.akka$actor$dungeon$FaultHandling$$_failed_$eq(null);
/*     */   }
/*     */   
/*     */   private static ActorRef perpetrator(ActorCell $this) {
/*  50 */     return $this.akka$actor$dungeon$FaultHandling$$_failed();
/*     */   }
/*     */   
/*     */   public static void faultRecreate(ActorCell $this, Throwable cause) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   4: ifnonnull -> 67
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual system : ()Lakka/actor/ActorSystemImpl;
/*     */     //   11: invokevirtual eventStream : ()Lakka/event/EventStream;
/*     */     //   14: getstatic akka/event/Logging$Error$.MODULE$ : Lakka/event/Logging$Error$;
/*     */     //   17: aload_0
/*     */     //   18: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   21: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   24: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   27: aload_0
/*     */     //   28: aload_0
/*     */     //   29: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   32: invokevirtual clazz : (Ljava/lang/Object;)Ljava/lang/Class;
/*     */     //   35: new scala/collection/mutable/StringBuilder
/*     */     //   38: dup
/*     */     //   39: invokespecial <init> : ()V
/*     */     //   42: ldc 'changing Recreate into Create after '
/*     */     //   44: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   47: aload_1
/*     */     //   48: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   51: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   54: invokevirtual apply : (Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)Lakka/event/Logging$Error;
/*     */     //   57: invokevirtual publish : (Ljava/lang/Object;)V
/*     */     //   60: aload_0
/*     */     //   61: invokevirtual faultCreate : ()V
/*     */     //   64: goto -> 284
/*     */     //   67: aload_0
/*     */     //   68: invokevirtual isNormal : ()Z
/*     */     //   71: ifeq -> 186
/*     */     //   74: aload_0
/*     */     //   75: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   78: astore_2
/*     */     //   79: aload_0
/*     */     //   80: invokevirtual system : ()Lakka/actor/ActorSystemImpl;
/*     */     //   83: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   86: invokevirtual DebugLifecycle : ()Z
/*     */     //   89: ifeq -> 120
/*     */     //   92: aload_0
/*     */     //   93: new akka/event/Logging$Debug
/*     */     //   96: dup
/*     */     //   97: aload_0
/*     */     //   98: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   101: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   104: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   107: aload_0
/*     */     //   108: aload_2
/*     */     //   109: invokevirtual clazz : (Ljava/lang/Object;)Ljava/lang/Class;
/*     */     //   112: ldc 'restarting'
/*     */     //   114: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)V
/*     */     //   117: invokevirtual publish : (Lakka/event/Logging$LogEvent;)V
/*     */     //   120: aload_2
/*     */     //   121: ifnull -> 180
/*     */     //   124: aload_0
/*     */     //   125: invokevirtual currentMessage : ()Lakka/dispatch/Envelope;
/*     */     //   128: ifnull -> 148
/*     */     //   131: new scala/Some
/*     */     //   134: dup
/*     */     //   135: aload_0
/*     */     //   136: invokevirtual currentMessage : ()Lakka/dispatch/Envelope;
/*     */     //   139: invokevirtual message : ()Ljava/lang/Object;
/*     */     //   142: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   145: goto -> 151
/*     */     //   148: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   151: astore_3
/*     */     //   152: aload_2
/*     */     //   153: invokeinterface context : ()Lakka/actor/ActorContext;
/*     */     //   158: ifnull -> 169
/*     */     //   161: aload_2
/*     */     //   162: aload_1
/*     */     //   163: aload_3
/*     */     //   164: invokeinterface aroundPreRestart : (Ljava/lang/Throwable;Lscala/Option;)V
/*     */     //   169: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   172: aload_0
/*     */     //   173: aload_2
/*     */     //   174: invokevirtual clearActorFields : (Lakka/actor/Actor;)V
/*     */     //   177: goto -> 241
/*     */     //   180: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   183: goto -> 241
/*     */     //   186: aload_0
/*     */     //   187: aconst_null
/*     */     //   188: pop
/*     */     //   189: aconst_null
/*     */     //   190: invokevirtual faultResume : (Ljava/lang/Throwable;)V
/*     */     //   193: goto -> 284
/*     */     //   196: astore #4
/*     */     //   198: aload_0
/*     */     //   199: new akka/actor/dungeon/FaultHandling$$anonfun$1
/*     */     //   202: dup
/*     */     //   203: aload_0
/*     */     //   204: aload_2
/*     */     //   205: aload_3
/*     */     //   206: aload_1
/*     */     //   207: invokespecial <init> : (Lakka/actor/ActorCell;Lakka/actor/Actor;Lscala/Option;Ljava/lang/Throwable;)V
/*     */     //   210: invokevirtual handleNonFatalOrInterruptedException : (Lscala/Function1;)Lscala/PartialFunction;
/*     */     //   213: astore #6
/*     */     //   215: aload #6
/*     */     //   217: aload #4
/*     */     //   219: invokeinterface isDefinedAt : (Ljava/lang/Object;)Z
/*     */     //   224: ifeq -> 285
/*     */     //   227: aload #6
/*     */     //   229: aload #4
/*     */     //   231: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   236: aload_0
/*     */     //   237: aload_2
/*     */     //   238: invokevirtual clearActorFields : (Lakka/actor/Actor;)V
/*     */     //   241: pop
/*     */     //   242: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   245: aload_0
/*     */     //   246: invokevirtual mailbox : ()Lakka/dispatch/Mailbox;
/*     */     //   249: invokevirtual isSuspended : ()Z
/*     */     //   252: new akka/actor/dungeon/FaultHandling$$anonfun$faultRecreate$1
/*     */     //   255: dup
/*     */     //   256: aload_0
/*     */     //   257: invokespecial <init> : (Lakka/actor/ActorCell;)V
/*     */     //   260: invokevirtual assert : (ZLscala/Function0;)V
/*     */     //   263: aload_0
/*     */     //   264: new akka/actor/dungeon/ChildrenContainer$Recreation
/*     */     //   267: dup
/*     */     //   268: aload_1
/*     */     //   269: invokespecial <init> : (Ljava/lang/Throwable;)V
/*     */     //   272: invokevirtual setChildrenTerminationReason : (Lakka/actor/dungeon/ChildrenContainer$SuspendReason;)Z
/*     */     //   275: ifne -> 284
/*     */     //   278: aload_0
/*     */     //   279: aload_1
/*     */     //   280: aload_2
/*     */     //   281: invokestatic finishRecreate : (Lakka/actor/ActorCell;Ljava/lang/Throwable;Lakka/actor/Actor;)V
/*     */     //   284: return
/*     */     //   285: aload #4
/*     */     //   287: athrow
/*     */     //   288: astore #5
/*     */     //   290: aload_0
/*     */     //   291: aload_2
/*     */     //   292: invokevirtual clearActorFields : (Lakka/actor/Actor;)V
/*     */     //   295: aload #5
/*     */     //   297: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #56	-> 0
/*     */     //   #57	-> 7
/*     */     //   #58	-> 35
/*     */     //   #57	-> 54
/*     */     //   #59	-> 60
/*     */     //   #60	-> 67
/*     */     //   #61	-> 74
/*     */     //   #62	-> 79
/*     */     //   #63	-> 120
/*     */     //   #64	-> 124
/*     */     //   #67	-> 152
/*     */     //   #72	-> 172
/*     */     //   #63	-> 180
/*     */     //   #79	-> 186
/*     */     //   #65	-> 196
/*     */     //   #68	-> 198
/*     */     //   #72	-> 236
/*     */     //   #63	-> 241
/*     */     //   #75	-> 242
/*     */     //   #76	-> 263
/*     */     //   #56	-> 284
/*     */     //   #68	-> 285
/*     */     //   #72	-> 288
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	298	0	$this	Lakka/actor/ActorCell;
/*     */     //   0	298	1	cause	Ljava/lang/Throwable;
/*     */     //   79	219	2	failedActor	Lakka/actor/Actor;
/*     */     //   152	146	3	optionalMessage	Lscala/Option;
/*     */     //   215	83	6	catchExpr1	Lscala/PartialFunction;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   152	172	196	finally
/*     */     //   152	172	288	finally
/*     */     //   196	236	288	finally
/*     */     //   285	288	288	finally
/*     */   }
/*     */   
/*     */   public static void faultSuspend(ActorCell $this) {
/*  88 */     suspendNonRecursive($this);
/*  89 */     $this.suspendChildren($this.suspendChildren$default$1());
/*     */   }
/*     */   
/*     */   public static void faultResume(ActorCell $this, Throwable causedByFailure) {
/*  99 */     if ($this.actor() == null) {
/* 100 */       $this.system().eventStream().publish(Logging$Error$.MODULE$.apply($this.self().path().toString(), $this.clazz($this.actor()), (
/* 101 */             new StringBuilder()).append("changing Resume into Create after ").append(causedByFailure).toString()));
/* 102 */       $this.faultCreate();
/* 103 */     } else if ($this.actor().context() == null && causedByFailure != null) {
/* 104 */       $this.system().eventStream().publish(Logging$Error$.MODULE$.apply($this.self().path().toString(), $this.clazz($this.actor()), (
/* 105 */             new StringBuilder()).append("changing Resume into Restart after ").append(causedByFailure).toString()));
/* 106 */       $this.faultRecreate(causedByFailure);
/*     */     } else {
/* 108 */       ActorRef perp = perpetrator($this);
/*     */       try {
/* 111 */         resumeNonRecursive($this);
/* 112 */         if (causedByFailure != null)
/* 112 */           clearFailed($this); 
/*     */         return;
/*     */       } finally {
/* 112 */         if (causedByFailure != null)
/* 112 */           clearFailed($this); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void faultCreate(ActorCell $this) {
/*     */     // Byte code:
/*     */     //   0: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   3: aload_0
/*     */     //   4: invokevirtual mailbox : ()Lakka/dispatch/Mailbox;
/*     */     //   7: invokevirtual isSuspended : ()Z
/*     */     //   10: new akka/actor/dungeon/FaultHandling$$anonfun$faultCreate$1
/*     */     //   13: dup
/*     */     //   14: aload_0
/*     */     //   15: invokespecial <init> : (Lakka/actor/ActorCell;)V
/*     */     //   18: invokevirtual assert : (ZLscala/Function0;)V
/*     */     //   21: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   24: aload_0
/*     */     //   25: invokestatic perpetrator : (Lakka/actor/ActorCell;)Lakka/actor/ActorRef;
/*     */     //   28: aload_0
/*     */     //   29: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   32: astore_1
/*     */     //   33: dup
/*     */     //   34: ifnonnull -> 45
/*     */     //   37: pop
/*     */     //   38: aload_1
/*     */     //   39: ifnull -> 52
/*     */     //   42: goto -> 56
/*     */     //   45: aload_1
/*     */     //   46: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   49: ifeq -> 56
/*     */     //   52: iconst_1
/*     */     //   53: goto -> 57
/*     */     //   56: iconst_0
/*     */     //   57: invokevirtual assert : (Z)V
/*     */     //   60: aload_0
/*     */     //   61: getstatic scala/concurrent/duration/Duration$.MODULE$ : Lscala/concurrent/duration/Duration$;
/*     */     //   64: invokevirtual Undefined : ()Lscala/concurrent/duration/Duration$Infinite;
/*     */     //   67: invokevirtual setReceiveTimeout : (Lscala/concurrent/duration/Duration;)V
/*     */     //   70: aload_0
/*     */     //   71: invokevirtual cancelReceiveTimeout : ()V
/*     */     //   74: aload_0
/*     */     //   75: invokevirtual children : ()Lscala/collection/immutable/Iterable;
/*     */     //   78: new akka/actor/dungeon/FaultHandling$$anonfun$faultCreate$2
/*     */     //   81: dup
/*     */     //   82: aload_0
/*     */     //   83: invokespecial <init> : (Lakka/actor/ActorCell;)V
/*     */     //   86: invokeinterface foreach : (Lscala/Function1;)V
/*     */     //   91: aload_0
/*     */     //   92: new akka/actor/dungeon/ChildrenContainer$Creation
/*     */     //   95: dup
/*     */     //   96: invokespecial <init> : ()V
/*     */     //   99: invokevirtual setChildrenTerminationReason : (Lakka/actor/dungeon/ChildrenContainer$SuspendReason;)Z
/*     */     //   102: ifne -> 109
/*     */     //   105: aload_0
/*     */     //   106: invokestatic finishCreate : (Lakka/actor/ActorCell;)V
/*     */     //   109: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #121	-> 0
/*     */     //   #122	-> 21
/*     */     //   #124	-> 60
/*     */     //   #125	-> 70
/*     */     //   #128	-> 74
/*     */     //   #130	-> 91
/*     */     //   #120	-> 109
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	110	0	$this	Lakka/actor/ActorCell;
/*     */   }
/*     */   
/*     */   private static void finishCreate(ActorCell $this) {
/*     */     try {
/* 134 */       resumeNonRecursive($this);
/* 135 */       clearFailed($this);
/*     */     } finally {
/* 135 */       clearFailed($this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void terminate(ActorCell $this) {
/* 143 */     $this.setReceiveTimeout((Duration)Duration$.MODULE$.Undefined());
/* 144 */     $this.cancelReceiveTimeout();
/* 147 */     $this.unwatchWatchedActors($this.actor());
/* 150 */     $this.children().foreach((Function1)new FaultHandling$$anonfun$terminate$1($this));
/* 152 */     if ($this.systemImpl().aborting())
/* 154 */       $this.children().foreach((Function1)new FaultHandling$$anonfun$terminate$2($this)); 
/* 160 */     boolean wasTerminating = $this.isTerminating();
/* 162 */     if ($this.setChildrenTerminationReason(ChildrenContainer.Termination$.MODULE$)) {
/* 163 */       if (!wasTerminating) {
/* 165 */         suspendNonRecursive($this);
/* 167 */         setFailed($this, (ActorRef)$this.self());
/* 168 */         if ($this.system().settings().DebugLifecycle())
/* 168 */           $this.publish((Logging.LogEvent)new Logging.Debug($this.self().path().toString(), $this.clazz($this.actor()), "stopping")); 
/*     */       } 
/*     */     } else {
/* 171 */       $this.setTerminated();
/* 172 */       akka$actor$dungeon$FaultHandling$$finishTerminate($this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final void handleInvokeFailure(ActorCell $this, Iterable childrenNotToSuspend, Throwable t) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokestatic isFailed : (Lakka/actor/ActorCell;)Z
/*     */     //   4: ifne -> 239
/*     */     //   7: aload_0
/*     */     //   8: invokestatic suspendNonRecursive : (Lakka/actor/ActorCell;)V
/*     */     //   11: aload_0
/*     */     //   12: invokevirtual currentMessage : ()Lakka/dispatch/Envelope;
/*     */     //   15: astore #6
/*     */     //   17: aload #6
/*     */     //   19: ifnull -> 85
/*     */     //   22: aload #6
/*     */     //   24: invokevirtual message : ()Ljava/lang/Object;
/*     */     //   27: astore #7
/*     */     //   29: aload #6
/*     */     //   31: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */     //   34: astore #8
/*     */     //   36: aload #7
/*     */     //   38: instanceof akka/dispatch/sysmsg/Failed
/*     */     //   41: ifeq -> 85
/*     */     //   44: aload_0
/*     */     //   45: aload #8
/*     */     //   47: invokestatic setFailed : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;)V
/*     */     //   50: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   53: invokevirtual Set : ()Lscala/collection/immutable/Set$;
/*     */     //   56: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   59: iconst_1
/*     */     //   60: anewarray akka/actor/ActorRef
/*     */     //   63: dup
/*     */     //   64: iconst_0
/*     */     //   65: aload #8
/*     */     //   67: aastore
/*     */     //   68: checkcast [Ljava/lang/Object;
/*     */     //   71: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   74: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/GenTraversable;
/*     */     //   77: checkcast scala/collection/immutable/Set
/*     */     //   80: astore #9
/*     */     //   82: goto -> 104
/*     */     //   85: aload_0
/*     */     //   86: aload_0
/*     */     //   87: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   90: invokestatic setFailed : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;)V
/*     */     //   93: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   96: invokevirtual Set : ()Lscala/collection/immutable/Set$;
/*     */     //   99: invokevirtual empty : ()Lscala/collection/immutable/Set;
/*     */     //   102: astore #9
/*     */     //   104: aload #9
/*     */     //   106: astore #5
/*     */     //   108: aload_0
/*     */     //   109: aload #5
/*     */     //   111: aload_1
/*     */     //   112: invokeinterface $plus$plus : (Lscala/collection/GenTraversableOnce;)Lscala/collection/Set;
/*     */     //   117: checkcast scala/collection/immutable/Set
/*     */     //   120: invokevirtual suspendChildren : (Lscala/collection/immutable/Set;)V
/*     */     //   123: aload_2
/*     */     //   124: astore #10
/*     */     //   126: aload #10
/*     */     //   128: instanceof java/lang/InterruptedException
/*     */     //   131: ifeq -> 172
/*     */     //   134: aload_0
/*     */     //   135: invokevirtual parent : ()Lakka/actor/InternalActorRef;
/*     */     //   138: new akka/dispatch/sysmsg/Failed
/*     */     //   141: dup
/*     */     //   142: aload_0
/*     */     //   143: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   146: new akka/actor/ActorInterruptedException
/*     */     //   149: dup
/*     */     //   150: aload_2
/*     */     //   151: invokespecial <init> : (Ljava/lang/Throwable;)V
/*     */     //   154: aload_0
/*     */     //   155: invokevirtual uid : ()I
/*     */     //   158: invokespecial <init> : (Lakka/actor/ActorRef;Ljava/lang/Throwable;I)V
/*     */     //   161: invokevirtual sendSystemMessage : (Lakka/dispatch/sysmsg/SystemMessage;)V
/*     */     //   164: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   167: astore #11
/*     */     //   169: goto -> 239
/*     */     //   172: aload_0
/*     */     //   173: invokevirtual parent : ()Lakka/actor/InternalActorRef;
/*     */     //   176: new akka/dispatch/sysmsg/Failed
/*     */     //   179: dup
/*     */     //   180: aload_0
/*     */     //   181: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   184: aload_2
/*     */     //   185: aload_0
/*     */     //   186: invokevirtual uid : ()I
/*     */     //   189: invokespecial <init> : (Lakka/actor/ActorRef;Ljava/lang/Throwable;I)V
/*     */     //   192: invokevirtual sendSystemMessage : (Lakka/dispatch/sysmsg/SystemMessage;)V
/*     */     //   195: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   198: astore #11
/*     */     //   200: goto -> 239
/*     */     //   203: astore_3
/*     */     //   204: aload_0
/*     */     //   205: new akka/actor/dungeon/FaultHandling$$anonfun$3
/*     */     //   208: dup
/*     */     //   209: aload_0
/*     */     //   210: aload_2
/*     */     //   211: invokespecial <init> : (Lakka/actor/ActorCell;Ljava/lang/Throwable;)V
/*     */     //   214: invokevirtual handleNonFatalOrInterruptedException : (Lscala/Function1;)Lscala/PartialFunction;
/*     */     //   217: astore #4
/*     */     //   219: aload #4
/*     */     //   221: aload_3
/*     */     //   222: invokeinterface isDefinedAt : (Ljava/lang/Object;)Z
/*     */     //   227: ifeq -> 240
/*     */     //   230: aload #4
/*     */     //   232: aload_3
/*     */     //   233: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   238: pop
/*     */     //   239: return
/*     */     //   240: aload_3
/*     */     //   241: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #178	-> 0
/*     */     //   #179	-> 7
/*     */     //   #181	-> 11
/*     */     //   #182	-> 22
/*     */     //   #183	-> 85
/*     */     //   #181	-> 104
/*     */     //   #185	-> 108
/*     */     //   #186	-> 123
/*     */     //   #188	-> 126
/*     */     //   #190	-> 134
/*     */     //   #193	-> 172
/*     */     //   #178	-> 203
/*     */     //   #195	-> 204
/*     */     //   #178	-> 239
/*     */     //   #195	-> 240
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	242	0	$this	Lakka/actor/ActorCell;
/*     */     //   0	242	1	childrenNotToSuspend	Lscala/collection/immutable/Iterable;
/*     */     //   0	242	2	t	Ljava/lang/Throwable;
/*     */     //   36	206	8	child	Lakka/actor/ActorRef;
/*     */     //   108	134	5	skip	Lscala/collection/immutable/Set;
/*     */     //   219	23	4	catchExpr3	Lscala/PartialFunction;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	203	203	finally
/*     */   }
/*     */   
/*     */   public static void akka$actor$dungeon$FaultHandling$$finishTerminate(ActorCell $this) {
/*     */     Exception exception1, exception2;
/* 204 */     Actor a = $this.actor();
/*     */     try {
/* 210 */       if (a != null)
/* 210 */         a.aroundPostStop(); 
/*     */       try {
/* 212 */         $this.dispatcher().detach($this);
/*     */         try {
/* 213 */           $this.parent().sendSystemMessage((SystemMessage)new DeathWatchNotification((ActorRef)$this.self(), true, false));
/*     */           try {
/* 214 */             $this.tellWatchersWeDied();
/*     */             try {
/* 215 */               $this.unwatchWatchedActors(a);
/* 217 */               if ($this.system().settings().DebugLifecycle())
/* 218 */                 $this.publish((Logging.LogEvent)new Logging.Debug($this.self().path().toString(), $this.clazz(a), "stopped")); 
/* 220 */               $this.clearActorFields(a);
/* 221 */               $this.clearActorCellFields($this);
/* 222 */               null;
/* 222 */               $this.actor_$eq(null);
/*     */               return;
/*     */             } finally {
/*     */               Exception exception;
/*     */             } 
/*     */           } finally {
/*     */             Exception exception;
/*     */           } 
/*     */         } finally {
/*     */           Exception exception;
/*     */         } 
/*     */       } finally {}
/*     */     } finally {
/*     */       Exception exception;
/*     */     } 
/*     */     try {
/*     */     
/*     */     } finally {
/*     */       Exception exception4, exception3 = null;
/*     */       try {
/*     */         $this.tellWatchersWeDied();
/*     */       } finally {
/*     */         Exception exception = null;
/*     */       } 
/*     */       if ($this.system().settings().DebugLifecycle())
/*     */         $this.publish((Logging.LogEvent)new Logging.Debug($this.self().path().toString(), $this.clazz(a), "stopped")); 
/*     */       $this.clearActorFields(a);
/*     */       $this.clearActorCellFields($this);
/* 222 */       null;
/* 222 */       $this.actor_$eq(null);
/*     */     } 
/*     */     try {
/*     */       $this.unwatchWatchedActors(a);
/*     */       if ($this.system().settings().DebugLifecycle())
/*     */         $this.publish((Logging.LogEvent)new Logging.Debug($this.self().path().toString(), $this.clazz(a), "stopped")); 
/*     */       $this.clearActorFields(a);
/*     */       $this.clearActorCellFields($this);
/* 222 */       null;
/* 222 */       $this.actor_$eq(null);
/* 222 */       throw exception2;
/*     */     } finally {
/*     */       if ($this.system().settings().DebugLifecycle())
/*     */         $this.publish((Logging.LogEvent)new Logging.Debug($this.self().path().toString(), $this.clazz(a), "stopped")); 
/*     */       $this.clearActorFields(a);
/*     */       $this.clearActorCellFields($this);
/* 222 */       null;
/* 222 */       $this.actor_$eq(null);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void finishRecreate(ActorCell $this, Throwable cause, Actor failedActor) {
/* 228 */     Iterable survivors = $this.children();
/*     */   }
/*     */   
/*     */   public static final void handleFailure(ActorCell $this, Failed f) {
/* 254 */     $this.currentMessage_$eq(Envelope$.MODULE$.apply(f, f.child(), (ActorSystem)$this.system()));
/* 261 */     boolean bool = false;
/* 261 */     null;
/* 261 */     Some some = null;
/*     */     Option option = $this.getChildByRef(f.child());
/* 261 */     if (option instanceof Some) {
/* 261 */       bool = true;
/* 261 */       some = (Some)option;
/* 261 */       ChildRestartStats stats = (ChildRestartStats)some.x();
/* 261 */       if (stats.uid() == f.uid()) {
/* 262 */         if ($this.actor().supervisorStrategy().handleFailure((ActorContext)$this, f.child(), f.cause(), stats, (Iterable)$this.getAllChildStats())) {
/* 262 */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/* 262 */           throw f.cause();
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     if (bool) {
/* 263 */       ChildRestartStats stats = (ChildRestartStats)some.x();
/* 264 */       $this.publish((Logging.LogEvent)new Logging.Debug($this.self().path().toString(), $this.clazz($this.actor()), (
/* 265 */             new StringBuilder()).append("dropping Failed(").append(f.cause()).append(") from old child ").append(f.child()).append(" (uid=").append(BoxesRunTime.boxToInteger(stats.uid())).append(" != ").append(BoxesRunTime.boxToInteger(f.uid())).append(")").toString()));
/*     */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/* 266 */       Option option1 = option;
/* 266 */       if (None$.MODULE$ == null) {
/* 266 */         if (option1 != null)
/*     */           throw new MatchError(option); 
/* 266 */       } else if (!None$.MODULE$.equals(option1)) {
/*     */         throw new MatchError(option);
/*     */       } 
/* 267 */       $this.publish((Logging.LogEvent)new Logging.Debug($this.self().path().toString(), $this.clazz($this.actor()), (new StringBuilder()).append("dropping Failed(").append(f.cause()).append(") from unknown child ").append(f.child()).toString()));
/* 267 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final void handleChildTerminated(ActorCell $this, ActorRef child) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: invokevirtual removeChildAndGetStateChange : (Lakka/actor/ActorRef;)Lscala/Option;
/*     */     //   5: astore_2
/*     */     //   6: aload_0
/*     */     //   7: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   10: ifnonnull -> 19
/*     */     //   13: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   16: goto -> 77
/*     */     //   19: aload_0
/*     */     //   20: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   23: invokeinterface supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   28: aload_0
/*     */     //   29: aload_1
/*     */     //   30: aload_0
/*     */     //   31: invokevirtual children : ()Lscala/collection/immutable/Iterable;
/*     */     //   34: invokevirtual handleChildTerminated : (Lakka/actor/ActorContext;Lakka/actor/ActorRef;Lscala/collection/Iterable;)V
/*     */     //   37: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   40: goto -> 77
/*     */     //   43: astore_3
/*     */     //   44: aload_0
/*     */     //   45: new akka/actor/dungeon/FaultHandling$$anonfun$7
/*     */     //   48: dup
/*     */     //   49: aload_0
/*     */     //   50: invokespecial <init> : (Lakka/actor/ActorCell;)V
/*     */     //   53: invokevirtual handleNonFatalOrInterruptedException : (Lscala/Function1;)Lscala/PartialFunction;
/*     */     //   56: astore #4
/*     */     //   58: aload #4
/*     */     //   60: aload_3
/*     */     //   61: invokeinterface isDefinedAt : (Ljava/lang/Object;)Z
/*     */     //   66: ifeq -> 253
/*     */     //   69: aload #4
/*     */     //   71: aload_3
/*     */     //   72: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   77: pop
/*     */     //   78: iconst_0
/*     */     //   79: istore #5
/*     */     //   81: aconst_null
/*     */     //   82: pop
/*     */     //   83: aconst_null
/*     */     //   84: astore #6
/*     */     //   86: aload_2
/*     */     //   87: astore #7
/*     */     //   89: aload #7
/*     */     //   91: instanceof scala/Some
/*     */     //   94: ifeq -> 157
/*     */     //   97: iconst_1
/*     */     //   98: istore #5
/*     */     //   100: aload #7
/*     */     //   102: checkcast scala/Some
/*     */     //   105: astore #6
/*     */     //   107: aload #6
/*     */     //   109: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   112: checkcast akka/actor/dungeon/ChildrenContainer$SuspendReason
/*     */     //   115: astore #8
/*     */     //   117: aload #8
/*     */     //   119: instanceof akka/actor/dungeon/ChildrenContainer$Recreation
/*     */     //   122: ifeq -> 157
/*     */     //   125: aload #8
/*     */     //   127: checkcast akka/actor/dungeon/ChildrenContainer$Recreation
/*     */     //   130: astore #9
/*     */     //   132: aload #9
/*     */     //   134: invokevirtual cause : ()Ljava/lang/Throwable;
/*     */     //   137: astore #10
/*     */     //   139: aload_0
/*     */     //   140: aload #10
/*     */     //   142: aload_0
/*     */     //   143: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   146: invokestatic finishRecreate : (Lakka/actor/ActorCell;Ljava/lang/Throwable;Lakka/actor/Actor;)V
/*     */     //   149: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   152: astore #11
/*     */     //   154: goto -> 252
/*     */     //   157: iload #5
/*     */     //   159: ifeq -> 192
/*     */     //   162: aload #6
/*     */     //   164: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   167: checkcast akka/actor/dungeon/ChildrenContainer$SuspendReason
/*     */     //   170: astore #12
/*     */     //   172: aload #12
/*     */     //   174: instanceof akka/actor/dungeon/ChildrenContainer$Creation
/*     */     //   177: ifeq -> 192
/*     */     //   180: aload_0
/*     */     //   181: invokestatic finishCreate : (Lakka/actor/ActorCell;)V
/*     */     //   184: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   187: astore #11
/*     */     //   189: goto -> 252
/*     */     //   192: iload #5
/*     */     //   194: ifeq -> 247
/*     */     //   197: aload #6
/*     */     //   199: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   202: checkcast akka/actor/dungeon/ChildrenContainer$SuspendReason
/*     */     //   205: astore #13
/*     */     //   207: getstatic akka/actor/dungeon/ChildrenContainer$Termination$.MODULE$ : Lakka/actor/dungeon/ChildrenContainer$Termination$;
/*     */     //   210: aload #13
/*     */     //   212: astore #14
/*     */     //   214: dup
/*     */     //   215: ifnonnull -> 227
/*     */     //   218: pop
/*     */     //   219: aload #14
/*     */     //   221: ifnull -> 235
/*     */     //   224: goto -> 247
/*     */     //   227: aload #14
/*     */     //   229: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   232: ifeq -> 247
/*     */     //   235: aload_0
/*     */     //   236: invokestatic akka$actor$dungeon$FaultHandling$$finishTerminate : (Lakka/actor/ActorCell;)V
/*     */     //   239: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   242: astore #11
/*     */     //   244: goto -> 252
/*     */     //   247: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   250: astore #11
/*     */     //   252: return
/*     */     //   253: aload_3
/*     */     //   254: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #272	-> 0
/*     */     //   #278	-> 6
/*     */     //   #279	-> 19
/*     */     //   #280	-> 44
/*     */     //   #278	-> 77
/*     */     //   #290	-> 78
/*     */     //   #289	-> 86
/*     */     //   #290	-> 89
/*     */     //   #289	-> 157
/*     */     //   #291	-> 162
/*     */     //   #289	-> 192
/*     */     //   #292	-> 197
/*     */     //   #293	-> 247
/*     */     //   #271	-> 252
/*     */     //   #280	-> 253
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	255	0	$this	Lakka/actor/ActorCell;
/*     */     //   0	255	1	child	Lakka/actor/ActorRef;
/*     */     //   6	249	2	status	Lscala/Option;
/*     */     //   58	197	4	catchExpr7	Lscala/PartialFunction;
/*     */     //   117	138	8	c	Lakka/actor/dungeon/ChildrenContainer$SuspendReason;
/*     */     //   139	116	10	cause	Ljava/lang/Throwable;
/*     */     //   172	83	12	c	Lakka/actor/dungeon/ChildrenContainer$SuspendReason;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   19	43	43	finally
/*     */   }
/*     */   
/*     */   public static final PartialFunction handleNonFatalOrInterruptedException(ActorCell $this, Function1 thunk) {
/* 297 */     return (PartialFunction)new FaultHandling$$anonfun$handleNonFatalOrInterruptedException$1($this, thunk);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\FaultHandling$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */