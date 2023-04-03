/*     */ package akka.actor.dungeon;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorCell$;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefScope;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Address;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.Terminated;
/*     */ import akka.dispatch.sysmsg.DeathWatchNotification;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.event.AddressTerminatedTopic;
/*     */ import akka.event.AddressTerminatedTopic$;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.collection.immutable.Set;
/*     */ 
/*     */ public abstract class DeathWatch$class {
/*     */   public static void $init$(ActorCell $this) {
/*  16 */     $this.akka$actor$dungeon$DeathWatch$$watching_$eq(ActorCell$.MODULE$.emptyActorRefSet());
/*  17 */     $this.akka$actor$dungeon$DeathWatch$$watchedBy_$eq(ActorCell$.MODULE$.emptyActorRefSet());
/*  18 */     $this.akka$actor$dungeon$DeathWatch$$terminatedQueued_$eq(ActorCell$.MODULE$.emptyActorRefSet());
/*     */   }
/*     */   
/*     */   public static final ActorRef watch(ActorCell $this, ActorRef subject) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_2
/*     */     //   2: aload_2
/*     */     //   3: instanceof akka/actor/InternalActorRef
/*     */     //   6: ifeq -> 77
/*     */     //   9: aload_2
/*     */     //   10: checkcast akka/actor/InternalActorRef
/*     */     //   13: astore_3
/*     */     //   14: aload_3
/*     */     //   15: aload_0
/*     */     //   16: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   19: astore #5
/*     */     //   21: dup
/*     */     //   22: ifnonnull -> 34
/*     */     //   25: pop
/*     */     //   26: aload #5
/*     */     //   28: ifnull -> 50
/*     */     //   31: goto -> 42
/*     */     //   34: aload #5
/*     */     //   36: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   39: ifne -> 50
/*     */     //   42: aload_0
/*     */     //   43: aload_3
/*     */     //   44: invokestatic watchingContains : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;)Z
/*     */     //   47: ifeq -> 56
/*     */     //   50: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   53: goto -> 70
/*     */     //   56: aload_0
/*     */     //   57: aload_3
/*     */     //   58: new akka/actor/dungeon/DeathWatch$$anonfun$watch$1
/*     */     //   61: dup
/*     */     //   62: aload_0
/*     */     //   63: aload_3
/*     */     //   64: invokespecial <init> : (Lakka/actor/ActorCell;Lakka/actor/InternalActorRef;)V
/*     */     //   67: invokestatic maintainAddressTerminatedSubscription : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;Lscala/Function0;)Ljava/lang/Object;
/*     */     //   70: pop
/*     */     //   71: aload_3
/*     */     //   72: astore #4
/*     */     //   74: aload #4
/*     */     //   76: areturn
/*     */     //   77: new scala/MatchError
/*     */     //   80: dup
/*     */     //   81: aload_2
/*     */     //   82: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   85: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #20	-> 0
/*     */     //   #21	-> 2
/*     */     //   #22	-> 14
/*     */     //   #23	-> 56
/*     */     //   #22	-> 70
/*     */     //   #28	-> 71
/*     */     //   #21	-> 72
/*     */     //   #20	-> 74
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	$this	Lakka/actor/ActorCell;
/*     */     //   0	86	1	subject	Lakka/actor/ActorRef;
/*     */   }
/*     */   
/*     */   public static final ActorRef unwatch(ActorCell $this, ActorRef subject) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_2
/*     */     //   2: aload_2
/*     */     //   3: instanceof akka/actor/InternalActorRef
/*     */     //   6: ifeq -> 106
/*     */     //   9: aload_2
/*     */     //   10: checkcast akka/actor/InternalActorRef
/*     */     //   13: astore_3
/*     */     //   14: aload_3
/*     */     //   15: aload_0
/*     */     //   16: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   19: astore #5
/*     */     //   21: dup
/*     */     //   22: ifnonnull -> 34
/*     */     //   25: pop
/*     */     //   26: aload #5
/*     */     //   28: ifnull -> 83
/*     */     //   31: goto -> 42
/*     */     //   34: aload #5
/*     */     //   36: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   39: ifne -> 83
/*     */     //   42: aload_0
/*     */     //   43: aload_3
/*     */     //   44: invokestatic watchingContains : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;)Z
/*     */     //   47: ifeq -> 83
/*     */     //   50: aload_3
/*     */     //   51: new akka/dispatch/sysmsg/Unwatch
/*     */     //   54: dup
/*     */     //   55: aload_3
/*     */     //   56: aload_0
/*     */     //   57: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   60: invokespecial <init> : (Lakka/actor/ActorRef;Lakka/actor/ActorRef;)V
/*     */     //   63: invokevirtual sendSystemMessage : (Lakka/dispatch/sysmsg/SystemMessage;)V
/*     */     //   66: aload_0
/*     */     //   67: aload_3
/*     */     //   68: new akka/actor/dungeon/DeathWatch$$anonfun$unwatch$1
/*     */     //   71: dup
/*     */     //   72: aload_0
/*     */     //   73: aload_3
/*     */     //   74: invokespecial <init> : (Lakka/actor/ActorCell;Lakka/actor/InternalActorRef;)V
/*     */     //   77: invokestatic maintainAddressTerminatedSubscription : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;Lscala/Function0;)Ljava/lang/Object;
/*     */     //   80: goto -> 86
/*     */     //   83: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   86: pop
/*     */     //   87: aload_0
/*     */     //   88: aload_0
/*     */     //   89: aload_3
/*     */     //   90: aload_0
/*     */     //   91: invokevirtual akka$actor$dungeon$DeathWatch$$terminatedQueued : ()Lscala/collection/immutable/Set;
/*     */     //   94: invokestatic akka$actor$dungeon$DeathWatch$$removeFromSet : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;Lscala/collection/immutable/Set;)Lscala/collection/immutable/Set;
/*     */     //   97: invokevirtual akka$actor$dungeon$DeathWatch$$terminatedQueued_$eq : (Lscala/collection/immutable/Set;)V
/*     */     //   100: aload_3
/*     */     //   101: astore #4
/*     */     //   103: aload #4
/*     */     //   105: areturn
/*     */     //   106: new scala/MatchError
/*     */     //   109: dup
/*     */     //   110: aload_2
/*     */     //   111: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   114: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #31	-> 0
/*     */     //   #32	-> 2
/*     */     //   #33	-> 14
/*     */     //   #34	-> 50
/*     */     //   #35	-> 66
/*     */     //   #36	-> 68
/*     */     //   #35	-> 77
/*     */     //   #33	-> 83
/*     */     //   #39	-> 87
/*     */     //   #40	-> 100
/*     */     //   #32	-> 101
/*     */     //   #31	-> 103
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	115	0	$this	Lakka/actor/ActorCell;
/*     */     //   0	115	1	subject	Lakka/actor/ActorRef;
/*     */   }
/*     */   
/*     */   public static void receivedTerminated(ActorCell $this, Terminated t) {
/*  44 */     if ($this.akka$actor$dungeon$DeathWatch$$terminatedQueued().apply(t.actor())) {
/*  45 */       $this.akka$actor$dungeon$DeathWatch$$terminatedQueued_$eq((Set)$this.akka$actor$dungeon$DeathWatch$$terminatedQueued().$minus(t.actor()));
/*  46 */       $this.receiveMessage(t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void watchedActorTerminated(ActorCell $this, ActorRef actor, boolean existenceConfirmed, boolean addressTerminated) {
/*  54 */     if (watchingContains($this, actor)) {
/*  55 */       maintainAddressTerminatedSubscription($this, actor, 
/*  56 */           (Function0)new DeathWatch$$anonfun$watchedActorTerminated$1($this, actor));
/*  58 */       if (!$this.isTerminating()) {
/*  59 */         $this.self().tell(new Terminated(actor, existenceConfirmed, addressTerminated), actor);
/*  60 */         $this.terminatedQueuedFor(actor);
/*     */       } 
/*     */     } 
/*  63 */     if ($this.childrenRefs().getByRef(actor).isDefined())
/*  63 */       $this.handleChildTerminated(actor); 
/*     */   }
/*     */   
/*     */   public static void terminatedQueuedFor(ActorCell $this, ActorRef subject) {
/*  67 */     $this.akka$actor$dungeon$DeathWatch$$terminatedQueued_$eq((Set)$this.akka$actor$dungeon$DeathWatch$$terminatedQueued().$plus(subject));
/*     */   }
/*     */   
/*     */   private static boolean watchingContains(ActorCell $this, ActorRef subject) {
/*  72 */     return ($this.akka$actor$dungeon$DeathWatch$$watching().contains(subject) || (subject.path().uid() != 0 && 
/*  73 */       $this.akka$actor$dungeon$DeathWatch$$watching().contains(new UndefinedUidActorRef(subject))));
/*     */   }
/*     */   
/*     */   public static Set akka$actor$dungeon$DeathWatch$$removeFromSet(ActorCell $this, ActorRef subject, Set set) {
/*  78 */     return (subject.path().uid() != 0) ? (Set)set.$minus(subject).$minus(new UndefinedUidActorRef(subject)) : 
/*  79 */       (Set)set.filterNot((Function1)new DeathWatch$$anonfun$akka$actor$dungeon$DeathWatch$$removeFromSet$1($this, subject));
/*     */   }
/*     */   
/*     */   public static void tellWatchersWeDied(ActorCell $this) {
/*  82 */     if (!$this.akka$actor$dungeon$DeathWatch$$watchedBy().isEmpty())
/*     */       try {
/* 102 */         $this.akka$actor$dungeon$DeathWatch$$watchedBy().foreach((Function1)new DeathWatch$$anonfun$tellWatchersWeDied$1($this));
/* 103 */         $this.akka$actor$dungeon$DeathWatch$$watchedBy().foreach((Function1)new DeathWatch$$anonfun$tellWatchersWeDied$2($this));
/*     */         return;
/*     */       } finally {
/* 104 */         $this.akka$actor$dungeon$DeathWatch$$watchedBy_$eq(ActorCell$.MODULE$.emptyActorRefSet());
/*     */       }  
/*     */   }
/*     */   
/*     */   public static final void sendTerminated$1(ActorCell $this, boolean ifLocal, ActorRef watcher) {
/*     */     if (((ActorRefScope)watcher).isLocal() == ifLocal) {
/*     */       InternalActorRef internalActorRef = $this.parent();
/*     */       if (watcher == null) {
/*     */         if (internalActorRef != null) {
/*     */           ((InternalActorRef)watcher).sendSystemMessage((SystemMessage)new DeathWatchNotification((ActorRef)$this.self(), true, false));
/*     */           return;
/*     */         } 
/*     */       } else if (!watcher.equals(internalActorRef)) {
/*     */         ((InternalActorRef)watcher).sendSystemMessage((SystemMessage)new DeathWatchNotification((ActorRef)$this.self(), true, false));
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void unwatchWatchedActors(ActorCell $this, Actor actor) {
/* 108 */     if (!$this.akka$actor$dungeon$DeathWatch$$watching().isEmpty())
/* 109 */       maintainAddressTerminatedSubscription($this, maintainAddressTerminatedSubscription$default$1($this), 
/* 110 */           (Function0)new DeathWatch$$anonfun$unwatchWatchedActors$1($this)); 
/*     */   }
/*     */   
/*     */   public static void addWatcher(ActorCell $this, ActorRef watchee, ActorRef watcher) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: aload_0
/*     */     //   2: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   5: astore #4
/*     */     //   7: dup
/*     */     //   8: ifnonnull -> 20
/*     */     //   11: pop
/*     */     //   12: aload #4
/*     */     //   14: ifnull -> 28
/*     */     //   17: goto -> 32
/*     */     //   20: aload #4
/*     */     //   22: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   25: ifeq -> 32
/*     */     //   28: iconst_1
/*     */     //   29: goto -> 33
/*     */     //   32: iconst_0
/*     */     //   33: istore_3
/*     */     //   34: aload_2
/*     */     //   35: aload_0
/*     */     //   36: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   39: astore #6
/*     */     //   41: dup
/*     */     //   42: ifnonnull -> 54
/*     */     //   45: pop
/*     */     //   46: aload #6
/*     */     //   48: ifnull -> 62
/*     */     //   51: goto -> 66
/*     */     //   54: aload #6
/*     */     //   56: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   59: ifeq -> 66
/*     */     //   62: iconst_1
/*     */     //   63: goto -> 67
/*     */     //   66: iconst_0
/*     */     //   67: istore #5
/*     */     //   69: iload_3
/*     */     //   70: ifeq -> 109
/*     */     //   73: iload #5
/*     */     //   75: ifne -> 109
/*     */     //   78: aload_0
/*     */     //   79: invokevirtual akka$actor$dungeon$DeathWatch$$watchedBy : ()Lscala/collection/immutable/Set;
/*     */     //   82: aload_2
/*     */     //   83: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   88: ifne -> 199
/*     */     //   91: aload_0
/*     */     //   92: aload_2
/*     */     //   93: new akka/actor/dungeon/DeathWatch$$anonfun$addWatcher$1
/*     */     //   96: dup
/*     */     //   97: aload_0
/*     */     //   98: aload_2
/*     */     //   99: invokespecial <init> : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;)V
/*     */     //   102: invokestatic maintainAddressTerminatedSubscription : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;Lscala/Function0;)Ljava/lang/Object;
/*     */     //   105: pop
/*     */     //   106: goto -> 199
/*     */     //   109: iload_3
/*     */     //   110: ifne -> 127
/*     */     //   113: iload #5
/*     */     //   115: ifeq -> 127
/*     */     //   118: aload_0
/*     */     //   119: aload_1
/*     */     //   120: invokevirtual watch : (Lakka/actor/ActorRef;)Lakka/actor/ActorRef;
/*     */     //   123: pop
/*     */     //   124: goto -> 199
/*     */     //   127: aload_0
/*     */     //   128: new akka/event/Logging$Warning
/*     */     //   131: dup
/*     */     //   132: aload_0
/*     */     //   133: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   136: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   139: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   142: aload_0
/*     */     //   143: aload_0
/*     */     //   144: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   147: invokevirtual clazz : (Ljava/lang/Object;)Ljava/lang/Class;
/*     */     //   150: new scala/collection/immutable/StringOps
/*     */     //   153: dup
/*     */     //   154: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   157: ldc 'BUG: illegal Watch(%s,%s) for %s'
/*     */     //   159: invokevirtual augmentString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   162: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   165: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   168: iconst_3
/*     */     //   169: anewarray java/lang/Object
/*     */     //   172: dup
/*     */     //   173: iconst_0
/*     */     //   174: aload_1
/*     */     //   175: aastore
/*     */     //   176: dup
/*     */     //   177: iconst_1
/*     */     //   178: aload_2
/*     */     //   179: aastore
/*     */     //   180: dup
/*     */     //   181: iconst_2
/*     */     //   182: aload_0
/*     */     //   183: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   186: aastore
/*     */     //   187: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   190: invokevirtual format : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   193: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)V
/*     */     //   196: invokevirtual publish : (Lakka/event/Logging$LogEvent;)V
/*     */     //   199: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #122	-> 0
/*     */     //   #123	-> 34
/*     */     //   #125	-> 69
/*     */     //   #126	-> 78
/*     */     //   #130	-> 109
/*     */     //   #131	-> 118
/*     */     //   #133	-> 127
/*     */     //   #121	-> 199
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	200	0	$this	Lakka/actor/ActorCell;
/*     */     //   0	200	1	watchee	Lakka/actor/ActorRef;
/*     */     //   0	200	2	watcher	Lakka/actor/ActorRef;
/*     */     //   34	166	3	watcheeSelf	Z
/*     */     //   69	131	5	watcherSelf	Z
/*     */   }
/*     */   
/*     */   public static void remWatcher(ActorCell $this, ActorRef watchee, ActorRef watcher) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: aload_0
/*     */     //   2: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   5: astore #4
/*     */     //   7: dup
/*     */     //   8: ifnonnull -> 20
/*     */     //   11: pop
/*     */     //   12: aload #4
/*     */     //   14: ifnull -> 28
/*     */     //   17: goto -> 32
/*     */     //   20: aload #4
/*     */     //   22: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   25: ifeq -> 32
/*     */     //   28: iconst_1
/*     */     //   29: goto -> 33
/*     */     //   32: iconst_0
/*     */     //   33: istore_3
/*     */     //   34: aload_2
/*     */     //   35: aload_0
/*     */     //   36: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   39: astore #6
/*     */     //   41: dup
/*     */     //   42: ifnonnull -> 54
/*     */     //   45: pop
/*     */     //   46: aload #6
/*     */     //   48: ifnull -> 62
/*     */     //   51: goto -> 66
/*     */     //   54: aload #6
/*     */     //   56: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   59: ifeq -> 66
/*     */     //   62: iconst_1
/*     */     //   63: goto -> 67
/*     */     //   66: iconst_0
/*     */     //   67: istore #5
/*     */     //   69: iload_3
/*     */     //   70: ifeq -> 109
/*     */     //   73: iload #5
/*     */     //   75: ifne -> 109
/*     */     //   78: aload_0
/*     */     //   79: invokevirtual akka$actor$dungeon$DeathWatch$$watchedBy : ()Lscala/collection/immutable/Set;
/*     */     //   82: aload_2
/*     */     //   83: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   88: ifeq -> 200
/*     */     //   91: aload_0
/*     */     //   92: aload_2
/*     */     //   93: new akka/actor/dungeon/DeathWatch$$anonfun$remWatcher$1
/*     */     //   96: dup
/*     */     //   97: aload_0
/*     */     //   98: aload_2
/*     */     //   99: invokespecial <init> : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;)V
/*     */     //   102: invokestatic maintainAddressTerminatedSubscription : (Lakka/actor/ActorCell;Lakka/actor/ActorRef;Lscala/Function0;)Ljava/lang/Object;
/*     */     //   105: pop
/*     */     //   106: goto -> 200
/*     */     //   109: iload_3
/*     */     //   110: ifne -> 127
/*     */     //   113: iload #5
/*     */     //   115: ifeq -> 127
/*     */     //   118: aload_0
/*     */     //   119: aload_1
/*     */     //   120: invokevirtual unwatch : (Lakka/actor/ActorRef;)Lakka/actor/ActorRef;
/*     */     //   123: pop
/*     */     //   124: goto -> 200
/*     */     //   127: aload_0
/*     */     //   128: new akka/event/Logging$Warning
/*     */     //   131: dup
/*     */     //   132: aload_0
/*     */     //   133: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   136: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   139: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   142: aload_0
/*     */     //   143: aload_0
/*     */     //   144: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   147: invokevirtual clazz : (Ljava/lang/Object;)Ljava/lang/Class;
/*     */     //   150: new scala/collection/immutable/StringOps
/*     */     //   153: dup
/*     */     //   154: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   157: ldc_w 'BUG: illegal Unwatch(%s,%s) for %s'
/*     */     //   160: invokevirtual augmentString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   163: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   166: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   169: iconst_3
/*     */     //   170: anewarray java/lang/Object
/*     */     //   173: dup
/*     */     //   174: iconst_0
/*     */     //   175: aload_1
/*     */     //   176: aastore
/*     */     //   177: dup
/*     */     //   178: iconst_1
/*     */     //   179: aload_2
/*     */     //   180: aastore
/*     */     //   181: dup
/*     */     //   182: iconst_2
/*     */     //   183: aload_0
/*     */     //   184: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   187: aastore
/*     */     //   188: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   191: invokevirtual format : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   194: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)V
/*     */     //   197: invokevirtual publish : (Lakka/event/Logging$LogEvent;)V
/*     */     //   200: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #138	-> 0
/*     */     //   #139	-> 34
/*     */     //   #141	-> 69
/*     */     //   #142	-> 78
/*     */     //   #146	-> 109
/*     */     //   #147	-> 118
/*     */     //   #149	-> 127
/*     */     //   #137	-> 200
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	201	0	$this	Lakka/actor/ActorCell;
/*     */     //   0	201	1	watchee	Lakka/actor/ActorRef;
/*     */     //   0	201	2	watcher	Lakka/actor/ActorRef;
/*     */     //   34	167	3	watcheeSelf	Z
/*     */     //   69	132	5	watcherSelf	Z
/*     */   }
/*     */   
/*     */   public static void addressTerminated(ActorCell $this, Address address) {
/* 155 */     maintainAddressTerminatedSubscription($this, maintainAddressTerminatedSubscription$default$1($this), 
/* 156 */         (Function0)new DeathWatch$$anonfun$addressTerminated$1($this, address));
/* 165 */     $this.akka$actor$dungeon$DeathWatch$$watching().withFilter((Function1)new DeathWatch$$anonfun$addressTerminated$2($this, address)).foreach((Function1)new DeathWatch$$anonfun$addressTerminated$3($this));
/*     */   }
/*     */   
/*     */   private static ActorRef maintainAddressTerminatedSubscription$default$1(ActorCell $this) {
/* 176 */     null;
/* 176 */     return null;
/*     */   }
/*     */   
/*     */   public static final boolean isNonLocal$1(ActorCell $this, ActorRef ref) {
/*     */     boolean bool;
/* 177 */     ActorRef actorRef = ref;
/* 178 */     if (actorRef == null) {
/* 178 */       bool = true;
/*     */     } else {
/* 179 */       if (actorRef instanceof InternalActorRef) {
/* 179 */         InternalActorRef internalActorRef = (InternalActorRef)actorRef;
/* 179 */         if (!internalActorRef.isLocal())
/*     */           return true; 
/*     */       } 
/* 180 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   private static Object maintainAddressTerminatedSubscription(ActorCell $this, ActorRef change, Function0 block) {
/* 185 */     boolean had = hasNonLocalAddress$1($this);
/* 186 */     Object result = block.apply();
/* 187 */     boolean has = hasNonLocalAddress$1($this);
/* 188 */     if (had && !has) {
/* 188 */       unsubscribeAddressTerminated($this);
/* 189 */     } else if (!had && has) {
/* 189 */       subscribeAddressTerminated($this);
/*     */     } 
/* 190 */     return isNonLocal$1($this, change) ? result : 
/*     */       
/* 192 */       block.apply();
/*     */   }
/*     */   
/*     */   private static final boolean hasNonLocalAddress$1(ActorCell $this) {
/*     */     return ($this.akka$actor$dungeon$DeathWatch$$watching().exists((Function1)new DeathWatch$$anonfun$hasNonLocalAddress$1$1($this)) || $this.akka$actor$dungeon$DeathWatch$$watchedBy().exists((Function1)new DeathWatch$$anonfun$hasNonLocalAddress$1$2($this)));
/*     */   }
/*     */   
/*     */   private static void unsubscribeAddressTerminated(ActorCell $this) {
/* 196 */     ((AddressTerminatedTopic)AddressTerminatedTopic$.MODULE$.apply((ActorSystem)$this.system())).unsubscribe((ActorRef)$this.self());
/*     */   }
/*     */   
/*     */   private static void subscribeAddressTerminated(ActorCell $this) {
/* 198 */     ((AddressTerminatedTopic)AddressTerminatedTopic$.MODULE$.apply((ActorSystem)$this.system())).subscribe((ActorRef)$this.self());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\DeathWatch$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */