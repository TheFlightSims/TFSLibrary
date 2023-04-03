/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.Envelope;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.immutable.Vector$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.package$;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public abstract class StashSupport$class {
/*     */   public static void $init$(StashSupport $this) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getstatic scala/package$.MODULE$ : Lscala/package$;
/*     */     //   4: invokevirtual Vector : ()Lscala/collection/immutable/Vector$;
/*     */     //   7: invokevirtual empty : ()Lscala/collection/immutable/Vector;
/*     */     //   10: invokeinterface akka$actor$StashSupport$$theStash_$eq : (Lscala/collection/immutable/Vector;)V
/*     */     //   15: aload_0
/*     */     //   16: aload_0
/*     */     //   17: invokeinterface context : ()Lakka/actor/ActorContext;
/*     */     //   22: invokeinterface system : ()Lakka/actor/ActorSystem;
/*     */     //   27: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   30: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */     //   33: aload_0
/*     */     //   34: invokeinterface context : ()Lakka/actor/ActorContext;
/*     */     //   39: invokeinterface props : ()Lakka/actor/Props;
/*     */     //   44: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   47: invokeinterface getConfig : (Ljava/lang/String;)Lcom/typesafe/config/Config;
/*     */     //   52: astore_1
/*     */     //   53: aload_1
/*     */     //   54: aload_0
/*     */     //   55: invokeinterface context : ()Lakka/actor/ActorContext;
/*     */     //   60: invokeinterface system : ()Lakka/actor/ActorSystem;
/*     */     //   65: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   68: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */     //   71: ldc_w 'akka.actor.default-mailbox'
/*     */     //   74: invokeinterface getConfig : (Ljava/lang/String;)Lcom/typesafe/config/Config;
/*     */     //   79: invokeinterface withFallback : (Lcom/typesafe/config/ConfigMergeable;)Lcom/typesafe/config/Config;
/*     */     //   84: astore_2
/*     */     //   85: aload_0
/*     */     //   86: invokeinterface context : ()Lakka/actor/ActorContext;
/*     */     //   91: invokeinterface props : ()Lakka/actor/Props;
/*     */     //   96: invokevirtual mailbox : ()Ljava/lang/String;
/*     */     //   99: ldc_w 'akka.actor.default-mailbox'
/*     */     //   102: astore #4
/*     */     //   104: dup
/*     */     //   105: ifnonnull -> 117
/*     */     //   108: pop
/*     */     //   109: aload #4
/*     */     //   111: ifnull -> 125
/*     */     //   114: goto -> 129
/*     */     //   117: aload #4
/*     */     //   119: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   122: ifeq -> 129
/*     */     //   125: aload_2
/*     */     //   126: goto -> 171
/*     */     //   129: aload_0
/*     */     //   130: invokeinterface context : ()Lakka/actor/ActorContext;
/*     */     //   135: invokeinterface system : ()Lakka/actor/ActorSystem;
/*     */     //   140: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   143: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */     //   146: aload_0
/*     */     //   147: invokeinterface context : ()Lakka/actor/ActorContext;
/*     */     //   152: invokeinterface props : ()Lakka/actor/Props;
/*     */     //   157: invokevirtual mailbox : ()Ljava/lang/String;
/*     */     //   160: invokeinterface getConfig : (Ljava/lang/String;)Lcom/typesafe/config/Config;
/*     */     //   165: aload_2
/*     */     //   166: invokeinterface withFallback : (Lcom/typesafe/config/ConfigMergeable;)Lcom/typesafe/config/Config;
/*     */     //   171: astore_3
/*     */     //   172: aload_3
/*     */     //   173: ldc_w 'stash-capacity'
/*     */     //   176: invokeinterface getInt : (Ljava/lang/String;)I
/*     */     //   181: invokeinterface akka$actor$StashSupport$_setter_$akka$actor$StashSupport$$capacity_$eq : (I)V
/*     */     //   186: aload_0
/*     */     //   187: aload_0
/*     */     //   188: invokestatic actorCell : (Lakka/actor/StashSupport;)Lakka/actor/ActorCell;
/*     */     //   191: invokevirtual mailbox : ()Lakka/dispatch/Mailbox;
/*     */     //   194: invokevirtual messageQueue : ()Lakka/dispatch/MessageQueue;
/*     */     //   197: astore #5
/*     */     //   199: aload #5
/*     */     //   201: instanceof akka/dispatch/DequeBasedMessageQueueSemantics
/*     */     //   204: ifeq -> 226
/*     */     //   207: aload #5
/*     */     //   209: astore #6
/*     */     //   211: aload #6
/*     */     //   213: checkcast akka/dispatch/DequeBasedMessageQueueSemantics
/*     */     //   216: astore #7
/*     */     //   218: aload #7
/*     */     //   220: invokeinterface akka$actor$StashSupport$_setter_$mailbox_$eq : (Lakka/dispatch/DequeBasedMessageQueueSemantics;)V
/*     */     //   225: return
/*     */     //   226: getstatic akka/actor/ActorInitializationException$.MODULE$ : Lakka/actor/ActorInitializationException$;
/*     */     //   229: aload_0
/*     */     //   230: invokeinterface self : ()Lakka/actor/ActorRef;
/*     */     //   235: new scala/collection/mutable/StringBuilder
/*     */     //   238: dup
/*     */     //   239: invokespecial <init> : ()V
/*     */     //   242: new scala/StringContext
/*     */     //   245: dup
/*     */     //   246: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   249: iconst_2
/*     */     //   250: anewarray java/lang/String
/*     */     //   253: dup
/*     */     //   254: iconst_0
/*     */     //   255: ldc_w 'DequeBasedMailbox required, got: '
/*     */     //   258: aastore
/*     */     //   259: dup
/*     */     //   260: iconst_1
/*     */     //   261: ldc_w '\n'
/*     */     //   264: aastore
/*     */     //   265: checkcast [Ljava/lang/Object;
/*     */     //   268: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   271: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   274: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   277: iconst_1
/*     */     //   278: anewarray java/lang/Object
/*     */     //   281: dup
/*     */     //   282: iconst_0
/*     */     //   283: aload #5
/*     */     //   285: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   288: invokevirtual getName : ()Ljava/lang/String;
/*     */     //   291: aastore
/*     */     //   292: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   295: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   298: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   301: new scala/collection/immutable/StringOps
/*     */     //   304: dup
/*     */     //   305: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   308: ldc_w 'An (unbounded) deque-based mailbox can be configured as follows:\\n          |  my-custom-mailbox {\\n          |    mailbox-type = "akka.dispatch.UnboundedDequeBasedMailbox"\\n          |  }\\n          |'
/*     */     //   311: invokevirtual augmentString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   314: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   317: invokevirtual stripMargin : ()Ljava/lang/String;
/*     */     //   320: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   323: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   326: getstatic akka/actor/ActorInitializationException$.MODULE$ : Lakka/actor/ActorInitializationException$;
/*     */     //   329: invokevirtual apply$default$3 : ()Ljava/lang/Throwable;
/*     */     //   332: invokevirtual apply : (Lakka/actor/ActorRef;Ljava/lang/String;Ljava/lang/Throwable;)Lakka/actor/ActorInitializationException;
/*     */     //   335: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #120	-> 0
/*     */     //   #126	-> 15
/*     */     //   #127	-> 16
/*     */     //   #128	-> 53
/*     */     //   #130	-> 85
/*     */     //   #131	-> 129
/*     */     //   #129	-> 171
/*     */     //   #132	-> 172
/*     */     //   #126	-> 181
/*     */     //   #141	-> 186
/*     */     //   #142	-> 187
/*     */     //   #143	-> 199
/*     */     //   #142	-> 218
/*     */     //   #141	-> 220
/*     */     //   #144	-> 226
/*     */     //   #145	-> 301
/*     */     //   #149	-> 317
/*     */     //   #144	-> 323
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	336	0	$this	Lakka/actor/StashSupport;
/*     */     //   53	128	1	dispatcher	Lcom/typesafe/config/Config;
/*     */     //   85	96	2	fallback	Lcom/typesafe/config/Config;
/*     */     //   172	9	3	config	Lcom/typesafe/config/Config;
/*     */   }
/*     */   
/*     */   private static ActorCell actorCell(StashSupport $this) {
/* 122 */     return (ActorCell)$this.context();
/*     */   }
/*     */   
/*     */   public static void stash(StashSupport $this) {
/* 161 */     Envelope currMsg = actorCell($this).currentMessage();
/* 162 */     if ($this.akka$actor$StashSupport$$theStash().nonEmpty() && currMsg == $this.akka$actor$StashSupport$$theStash().last())
/* 163 */       throw new IllegalStateException((new StringBuilder()).append("Can't stash the same message ").append(currMsg).append(" more than once").toString()); 
/* 164 */     if ($this.akka$actor$StashSupport$$capacity() <= 0 || $this.akka$actor$StashSupport$$theStash().size() < $this.akka$actor$StashSupport$$capacity()) {
/* 164 */       $this.akka$actor$StashSupport$$theStash_$eq((Vector<Envelope>)$this.akka$actor$StashSupport$$theStash().$colon$plus(currMsg, Vector$.MODULE$.canBuildFrom()));
/*     */       return;
/*     */     } 
/* 165 */     throw new StashOverflowException((new StringBuilder()).append("Couldn't enqueue message ").append(currMsg).append(" to stash of ").append($this.self()).toString(), StashOverflowException$.MODULE$.$lessinit$greater$default$2());
/*     */   }
/*     */   
/*     */   public static void prepend(StashSupport $this, Seq others) {
/* 173 */     $this.akka$actor$StashSupport$$theStash_$eq((Vector<Envelope>)others.foldRight($this.akka$actor$StashSupport$$theStash(), (Function2)new StashSupport$$anonfun$prepend$1($this)));
/*     */   }
/*     */   
/*     */   public static void unstash(StashSupport $this) {
/* 186 */     if ($this.akka$actor$StashSupport$$theStash().nonEmpty())
/*     */       try {
/* 187 */         enqueueFirst($this, (Envelope)$this.akka$actor$StashSupport$$theStash().head());
/*     */         return;
/*     */       } finally {
/* 189 */         $this.akka$actor$StashSupport$$theStash_$eq($this.akka$actor$StashSupport$$theStash().tail());
/*     */       }  
/*     */   }
/*     */   
/*     */   public static void unstashAll(StashSupport $this) {
/* 201 */     $this.unstashAll((Function1<Object, Object>)new StashSupport$$anonfun$unstashAll$1($this));
/*     */   }
/*     */   
/*     */   public static void unstashAll(StashSupport $this, Function1 filterPredicate) {
/*     */     try {
/* 220 */       Iterator i = $this.akka$actor$StashSupport$$theStash().reverseIterator().filter((Function1)new StashSupport$$anonfun$1($this, filterPredicate));
/* 221 */       for (; i.hasNext(); enqueueFirst($this, (Envelope)i.next()));
/*     */       return;
/*     */     } finally {
/* 223 */       $this.akka$actor$StashSupport$$theStash_$eq(package$.MODULE$.Vector().empty());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Vector clearStash(StashSupport $this) {
/* 233 */     Vector<Envelope> stashed = $this.akka$actor$StashSupport$$theStash();
/* 234 */     $this.akka$actor$StashSupport$$theStash_$eq(package$.MODULE$.Vector().empty());
/* 235 */     return stashed;
/*     */   }
/*     */   
/*     */   private static void enqueueFirst(StashSupport $this, Envelope envelope) {
/* 244 */     $this.mailbox().enqueueFirst($this.self(), envelope);
/* 245 */     Object object = envelope.message();
/* 246 */     if (object instanceof Terminated) {
/* 246 */       Terminated terminated = (Terminated)object;
/* 246 */       ActorRef ref = terminated.actor();
/* 246 */       actorCell($this).terminatedQueuedFor(ref);
/* 246 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/* 247 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\StashSupport$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */