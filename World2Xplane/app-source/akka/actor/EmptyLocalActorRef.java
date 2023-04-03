/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.event.EventStream;
/*     */ import java.io.ObjectStreamException;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001M4Q!\001\002\001\t\031\021!#R7qifdunY1m\003\016$xN\035*fM*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b'\r\001qa\003\t\003\021%i\021AA\005\003\025\t\021\001#\0238uKJt\027\r\\!di>\024(+\0324\021\005!a\021BA\007\003\005=i\025N\\5nC2\f5\r^8s%\0264\007\002C\b\001\005\013\007I\021I\t\002\021A\024xN^5eKJ\034\001!F\001\023!\tA1#\003\002\025\005\t\001\022i\031;peJ+g\r\025:pm&$WM\035\005\t-\001\021\t\021)A\005%\005I\001O]8wS\022,'\017\t\005\t1\001\021)\031!C!3\005!\001/\031;i+\005Q\002C\001\005\034\023\ta\"AA\005BGR|'\017U1uQ\"Aa\004\001B\001B\003%!$A\003qCRD\007\005\003\005!\001\t\025\r\021\"\001\"\003-)g/\0328u'R\024X-Y7\026\003\t\002\"a\t\024\016\003\021R!!\n\003\002\013\0254XM\034;\n\005\035\"#aC#wK:$8\013\036:fC6D\001\"\013\001\003\002\003\006IAI\001\rKZ,g\016^*ue\026\fW\016\t\005\006W\001!\t\001L\001\007y%t\027\016\036 \025\t5rs\006\r\t\003\021\001AQa\004\026A\002IAQ\001\007\026A\002iAQ\001\t\026A\002\tBQA\r\001\005BM\nA\"[:UKJl\027N\\1uK\022,\022\001\016\t\003kaj\021A\016\006\002o\005)1oY1mC&\021\021H\016\002\b\005>|G.Z1oQ\021\t4H\020!\021\005Ub\024BA\0377\005)!W\r\035:fG\006$X\rZ\021\002\0051Tk]3!G>tG/\032=u]]\fGo\0315)C\016$xN]\025!C:$\007E]3dK&4X\r\t+fe6Lg.\031;fI\"\n7\r^8sS\005\n\021)A\0023]IBQa\021\001\005B\021\013\021c]3oINK8\017^3n\033\026\0348/Y4f)\t)\005\n\005\0026\r&\021qI\016\002\005+:LG\017C\003J\005\002\007!*A\004nKN\034\030mZ3\021\005-\003V\"\001'\013\0055s\025AB:zg6\034xM\003\002P\t\005AA-[:qCR\034\007.\003\002R\031\ni1+_:uK6lUm]:bO\026DQa\025\001\005BQ\013Q\001\n2b]\036$\"!V.\025\005\0253\006bB,S!\003\005\035\001W\001\007g\026tG-\032:\021\005!I\026B\001.\003\005!\t5\r^8s%\0264\007\"B%S\001\004a\006CA\033^\023\tqfGA\002B]fDQ\001\031\001\005\022\005\fQb\0359fG&\fG\016S1oI2,Gc\001\033cI\")1m\030a\0019\006\031Qn]4\t\013]{\006\031\001-\t\017\031\004\021\023!C!O\006yAEY1oO\022\"WMZ1vYR$#\007\006\002ie*\022\001,[\026\002UB\0211\016]\007\002Y*\021QN\\\001\nk:\034\007.Z2lK\022T!a\034\034\002\025\005tgn\034;bi&|g.\003\002rY\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\013%+\007\031\001/")
/*     */ public class EmptyLocalActorRef extends InternalActorRef implements MinimalActorRef {
/*     */   private final ActorRefProvider provider;
/*     */   
/*     */   private final ActorPath path;
/*     */   
/*     */   private final EventStream eventStream;
/*     */   
/*     */   public InternalActorRef getParent() {
/* 475 */     return MinimalActorRef$class.getParent(this);
/*     */   }
/*     */   
/*     */   public InternalActorRef getChild(Iterator names) {
/* 475 */     return MinimalActorRef$class.getChild(this, names);
/*     */   }
/*     */   
/*     */   public void start() {
/* 475 */     MinimalActorRef$class.start(this);
/*     */   }
/*     */   
/*     */   public void suspend() {
/* 475 */     MinimalActorRef$class.suspend(this);
/*     */   }
/*     */   
/*     */   public void resume(Throwable causedByFailure) {
/* 475 */     MinimalActorRef$class.resume(this, causedByFailure);
/*     */   }
/*     */   
/*     */   public void stop() {
/* 475 */     MinimalActorRef$class.stop(this);
/*     */   }
/*     */   
/*     */   public void restart(Throwable cause) {
/* 475 */     MinimalActorRef$class.restart(this, cause);
/*     */   }
/*     */   
/*     */   public Object writeReplace() throws ObjectStreamException {
/* 475 */     return MinimalActorRef$class.writeReplace(this);
/*     */   }
/*     */   
/*     */   public final boolean isLocal() {
/* 475 */     return LocalRef$class.isLocal(this);
/*     */   }
/*     */   
/*     */   public ActorRefProvider provider() {
/* 475 */     return this.provider;
/*     */   }
/*     */   
/*     */   public EmptyLocalActorRef(ActorRefProvider provider, ActorPath path, EventStream eventStream) {
/* 475 */     LocalRef$class.$init$(this);
/* 475 */     MinimalActorRef$class.$init$(this);
/*     */   }
/*     */   
/*     */   public ActorPath path() {
/* 476 */     return this.path;
/*     */   }
/*     */   
/*     */   public EventStream eventStream() {
/* 477 */     return this.eventStream;
/*     */   }
/*     */   
/*     */   public boolean isTerminated() {
/* 479 */     return true;
/*     */   }
/*     */   
/*     */   public void sendSystemMessage(SystemMessage message) {
/* 483 */     specialHandle(message, provider().deadLetters());
/*     */   }
/*     */   
/*     */   public void $bang(Object message, ActorRef sender) {
/* 486 */     Object object = message;
/* 487 */     if (object == null)
/* 487 */       throw new InvalidMessageException("Message is null"); 
/* 488 */     if (object instanceof DeadLetter) {
/* 488 */       DeadLetter deadLetter = (DeadLetter)object;
/* 489 */       specialHandle(deadLetter.message(), deadLetter.sender());
/* 489 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/* 490 */     } else if (specialHandle(message, sender)) {
/* 492 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/*     */       eventStream().publish(new DeadLetter(message, (sender == Actor$.MODULE$.noSender()) ? provider().deadLetters() : sender, this));
/*     */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ActorRef $bang$default$2(Object message) {
/*     */     return Actor$.MODULE$.noSender();
/*     */   }
/*     */   
/*     */   public boolean specialHandle(Object msg, ActorRef sender) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_3
/*     */     //   2: aload_3
/*     */     //   3: instanceof akka/dispatch/sysmsg/Watch
/*     */     //   6: ifeq -> 101
/*     */     //   9: aload_3
/*     */     //   10: checkcast akka/dispatch/sysmsg/Watch
/*     */     //   13: astore #4
/*     */     //   15: aload #4
/*     */     //   17: invokevirtual watchee : ()Lakka/actor/InternalActorRef;
/*     */     //   20: aload_0
/*     */     //   21: astore #6
/*     */     //   23: dup
/*     */     //   24: ifnonnull -> 36
/*     */     //   27: pop
/*     */     //   28: aload #6
/*     */     //   30: ifnull -> 44
/*     */     //   33: goto -> 95
/*     */     //   36: aload #6
/*     */     //   38: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   41: ifeq -> 95
/*     */     //   44: aload #4
/*     */     //   46: invokevirtual watcher : ()Lakka/actor/InternalActorRef;
/*     */     //   49: aload_0
/*     */     //   50: astore #7
/*     */     //   52: dup
/*     */     //   53: ifnonnull -> 65
/*     */     //   56: pop
/*     */     //   57: aload #7
/*     */     //   59: ifnull -> 95
/*     */     //   62: goto -> 73
/*     */     //   65: aload #7
/*     */     //   67: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   70: ifne -> 95
/*     */     //   73: aload #4
/*     */     //   75: invokevirtual watcher : ()Lakka/actor/InternalActorRef;
/*     */     //   78: new akka/dispatch/sysmsg/DeathWatchNotification
/*     */     //   81: dup
/*     */     //   82: aload #4
/*     */     //   84: invokevirtual watchee : ()Lakka/actor/InternalActorRef;
/*     */     //   87: iconst_0
/*     */     //   88: iconst_0
/*     */     //   89: invokespecial <init> : (Lakka/actor/ActorRef;ZZ)V
/*     */     //   92: invokevirtual sendSystemMessage : (Lakka/dispatch/sysmsg/SystemMessage;)V
/*     */     //   95: iconst_1
/*     */     //   96: istore #5
/*     */     //   98: goto -> 381
/*     */     //   101: aload_3
/*     */     //   102: instanceof akka/dispatch/sysmsg/Unwatch
/*     */     //   105: ifeq -> 114
/*     */     //   108: iconst_1
/*     */     //   109: istore #5
/*     */     //   111: goto -> 381
/*     */     //   114: aload_3
/*     */     //   115: instanceof akka/actor/Identify
/*     */     //   118: ifeq -> 185
/*     */     //   121: aload_3
/*     */     //   122: checkcast akka/actor/Identify
/*     */     //   125: astore #8
/*     */     //   127: aload #8
/*     */     //   129: invokevirtual messageId : ()Ljava/lang/Object;
/*     */     //   132: astore #9
/*     */     //   134: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */     //   137: aload_2
/*     */     //   138: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */     //   141: astore #10
/*     */     //   143: new akka/actor/ActorIdentity
/*     */     //   146: dup
/*     */     //   147: aload #9
/*     */     //   149: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   152: invokespecial <init> : (Ljava/lang/Object;Lscala/Option;)V
/*     */     //   155: astore #11
/*     */     //   157: aload #10
/*     */     //   159: aload #11
/*     */     //   161: invokeinterface $bang$default$2 : (Ljava/lang/Object;)Lakka/actor/ActorRef;
/*     */     //   166: astore #12
/*     */     //   168: aload #10
/*     */     //   170: aload #11
/*     */     //   172: aload #12
/*     */     //   174: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   179: iconst_1
/*     */     //   180: istore #5
/*     */     //   182: goto -> 381
/*     */     //   185: aload_3
/*     */     //   186: instanceof akka/actor/ActorSelectionMessage
/*     */     //   189: ifeq -> 378
/*     */     //   192: aload_3
/*     */     //   193: checkcast akka/actor/ActorSelectionMessage
/*     */     //   196: astore #13
/*     */     //   198: aload #13
/*     */     //   200: invokevirtual identifyRequest : ()Lscala/Option;
/*     */     //   203: astore #14
/*     */     //   205: aload #14
/*     */     //   207: instanceof scala/Some
/*     */     //   210: ifeq -> 286
/*     */     //   213: aload #14
/*     */     //   215: checkcast scala/Some
/*     */     //   218: astore #15
/*     */     //   220: aload #15
/*     */     //   222: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   225: checkcast akka/actor/Identify
/*     */     //   228: astore #16
/*     */     //   230: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */     //   233: aload_2
/*     */     //   234: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */     //   237: astore #18
/*     */     //   239: new akka/actor/ActorIdentity
/*     */     //   242: dup
/*     */     //   243: aload #16
/*     */     //   245: invokevirtual messageId : ()Ljava/lang/Object;
/*     */     //   248: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   251: invokespecial <init> : (Ljava/lang/Object;Lscala/Option;)V
/*     */     //   254: astore #19
/*     */     //   256: aload #18
/*     */     //   258: aload #19
/*     */     //   260: invokeinterface $bang$default$2 : (Ljava/lang/Object;)Lakka/actor/ActorRef;
/*     */     //   265: astore #20
/*     */     //   267: aload #18
/*     */     //   269: aload #19
/*     */     //   271: aload #20
/*     */     //   273: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   278: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   281: astore #17
/*     */     //   283: goto -> 362
/*     */     //   286: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   289: aload #14
/*     */     //   291: astore #21
/*     */     //   293: dup
/*     */     //   294: ifnonnull -> 306
/*     */     //   297: pop
/*     */     //   298: aload #21
/*     */     //   300: ifnull -> 314
/*     */     //   303: goto -> 368
/*     */     //   306: aload #21
/*     */     //   308: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   311: ifeq -> 368
/*     */     //   314: aload_0
/*     */     //   315: invokevirtual eventStream : ()Lakka/event/EventStream;
/*     */     //   318: new akka/actor/DeadLetter
/*     */     //   321: dup
/*     */     //   322: aload #13
/*     */     //   324: invokevirtual msg : ()Ljava/lang/Object;
/*     */     //   327: aload_2
/*     */     //   328: getstatic akka/actor/Actor$.MODULE$ : Lakka/actor/Actor$;
/*     */     //   331: invokevirtual noSender : ()Lakka/actor/ActorRef;
/*     */     //   334: if_acmpne -> 349
/*     */     //   337: aload_0
/*     */     //   338: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   341: invokeinterface deadLetters : ()Lakka/actor/ActorRef;
/*     */     //   346: goto -> 350
/*     */     //   349: aload_2
/*     */     //   350: aload_0
/*     */     //   351: invokespecial <init> : (Ljava/lang/Object;Lakka/actor/ActorRef;Lakka/actor/ActorRef;)V
/*     */     //   354: invokevirtual publish : (Ljava/lang/Object;)V
/*     */     //   357: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   360: astore #17
/*     */     //   362: iconst_1
/*     */     //   363: istore #5
/*     */     //   365: goto -> 381
/*     */     //   368: new scala/MatchError
/*     */     //   371: dup
/*     */     //   372: aload #14
/*     */     //   374: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   377: athrow
/*     */     //   378: iconst_0
/*     */     //   379: istore #5
/*     */     //   381: iload #5
/*     */     //   383: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #495	-> 0
/*     */     //   #496	-> 2
/*     */     //   #497	-> 15
/*     */     //   #498	-> 73
/*     */     //   #499	-> 78
/*     */     //   #498	-> 92
/*     */     //   #500	-> 95
/*     */     //   #496	-> 96
/*     */     //   #501	-> 101
/*     */     //   #502	-> 114
/*     */     //   #503	-> 134
/*     */     //   #504	-> 179
/*     */     //   #502	-> 180
/*     */     //   #505	-> 185
/*     */     //   #506	-> 198
/*     */     //   #507	-> 205
/*     */     //   #508	-> 286
/*     */     //   #509	-> 314
/*     */     //   #511	-> 362
/*     */     //   #505	-> 363
/*     */     //   #506	-> 368
/*     */     //   #512	-> 378
/*     */     //   #495	-> 381
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	384	0	this	Lakka/actor/EmptyLocalActorRef;
/*     */     //   0	384	1	msg	Ljava/lang/Object;
/*     */     //   0	384	2	sender	Lakka/actor/ActorRef;
/*     */     //   134	250	9	messageId	Ljava/lang/Object;
/*     */     //   143	36	10	qual$1	Lakka/actor/ScalaActorRef;
/*     */     //   157	22	11	x$2	Lakka/actor/ActorIdentity;
/*     */     //   168	11	12	x$3	Lakka/actor/ActorRef;
/*     */     //   230	154	16	identify	Lakka/actor/Identify;
/*     */     //   239	42	18	qual$2	Lakka/actor/ScalaActorRef;
/*     */     //   256	25	19	x$4	Lakka/actor/ActorIdentity;
/*     */     //   267	14	20	x$5	Lakka/actor/ActorRef;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\EmptyLocalActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */