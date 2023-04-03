/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorInitializationException$;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001=3Q!\001\002\001\t\031\0211BU8vi\026\024\030i\031;pe*\0211\001B\001\be>,H/\0338h\025\005)\021\001B1lW\006\0342\001A\004\016!\tA1\"D\001\n\025\005Q\021!B:dC2\f\027B\001\007\n\005\031\te.\037*fMB\021a\"E\007\002\037)\021\001\003B\001\006C\016$xN]\005\003%=\021Q!Q2u_JDQ\001\006\001\005\002Y\ta\001P5oSRt4\001\001\013\002/A\021\001\004A\007\002\005!9!\004\001b\001\n\003Y\022\001B2fY2,\022\001\b\t\0031uI!A\b\002\003\037I{W\017^3e\003\016$xN]\"fY2Da\001\t\001!\002\023a\022!B2fY2\004\003b\002\022\001\005\004%\taI\001\027e>,H/\0338h\031><\027nY\"p]R\024x\016\0347feV\tA\005E\002\tK\035J!AJ\005\003\r=\003H/[8o!\tq\001&\003\002*\037\tA\021i\031;peJ+g\r\003\004,\001\001\006I\001J\001\030e>,H/\0338h\031><\027nY\"p]R\024x\016\0347fe\002BQ!\f\001\005\0029\nqA]3dK&4X-F\0010!\021A\001GM\033\n\005EJ!a\004)beRL\027\r\034$v]\016$\030n\0348\021\005!\031\024B\001\033\n\005\r\te.\037\t\003\021YJ!aN\005\003\tUs\027\016\036\005\006s\001!\tAO\001\030gR|\007/\0234BY2\024v.\036;fKN\024V-\\8wK\022$\022!\016\005\006y\001!\t%P\001\013aJ,'+Z:uCJ$HcA\033?\031\")qh\017a\001\001\006)1-Y;tKB\021\021)\023\b\003\005\036s!a\021$\016\003\021S!!R\013\002\rq\022xn\034;?\023\005Q\021B\001%\n\003\035\001\030mY6bO\026L!AS&\003\023QC'o\\<bE2,'B\001%\n\021\025i5\b1\001O\003\ri7o\032\t\004\021\025\022\004")
/*     */ public class RouterActor implements Actor {
/*     */   private final RoutedActorCell cell;
/*     */   
/*     */   private final Option<ActorRef> routingLogicController;
/*     */   
/*     */   private final ActorContext context;
/*     */   
/*     */   private final ActorRef self;
/*     */   
/*     */   public ActorContext context() {
/* 155 */     return this.context;
/*     */   }
/*     */   
/*     */   public final ActorRef self() {
/* 155 */     return this.self;
/*     */   }
/*     */   
/*     */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/* 155 */     this.context = x$1;
/*     */   }
/*     */   
/*     */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/* 155 */     this.self = x$1;
/*     */   }
/*     */   
/*     */   public final ActorRef sender() {
/* 155 */     return Actor.class.sender(this);
/*     */   }
/*     */   
/*     */   public void aroundReceive(PartialFunction receive, Object msg) {
/* 155 */     Actor.class.aroundReceive(this, receive, msg);
/*     */   }
/*     */   
/*     */   public void aroundPreStart() {
/* 155 */     Actor.class.aroundPreStart(this);
/*     */   }
/*     */   
/*     */   public void aroundPostStop() {
/* 155 */     Actor.class.aroundPostStop(this);
/*     */   }
/*     */   
/*     */   public void aroundPreRestart(Throwable reason, Option message) {
/* 155 */     Actor.class.aroundPreRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void aroundPostRestart(Throwable reason) {
/* 155 */     Actor.class.aroundPostRestart(this, reason);
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/* 155 */     return Actor.class.supervisorStrategy(this);
/*     */   }
/*     */   
/*     */   public void preStart() throws Exception {
/* 155 */     Actor.class.preStart(this);
/*     */   }
/*     */   
/*     */   public void postStop() throws Exception {
/* 155 */     Actor.class.postStop(this);
/*     */   }
/*     */   
/*     */   public void postRestart(Throwable reason) throws Exception {
/* 155 */     Actor.class.postRestart(this, reason);
/*     */   }
/*     */   
/*     */   public void unhandled(Object message) {
/* 155 */     Actor.class.unhandled(this, message);
/*     */   }
/*     */   
/*     */   public RouterActor() {
/* 155 */     Actor.class.$init$(this);
/* 157 */     ActorContext actorContext = context();
/* 158 */     if (actorContext instanceof RoutedActorCell) {
/* 158 */       RoutedActorCell routedActorCell1 = (RoutedActorCell)actorContext, routedActorCell2 = routedActorCell1;
/*     */       this.cell = routedActorCell2;
/* 163 */       this.routingLogicController = cell().routerConfig().routingLogicController(
/* 164 */           cell().router().logic()).map((Function1)new $anonfun$2(this));
/*     */       return;
/*     */     } 
/*     */     throw ActorInitializationException$.MODULE$.apply((new StringBuilder()).append("Router actor can only be used in RoutedActorRef, not in ").append(context().getClass()).toString());
/*     */   }
/*     */   
/*     */   public RoutedActorCell cell() {
/*     */     return this.cell;
/*     */   }
/*     */   
/*     */   public Option<ActorRef> routingLogicController() {
/*     */     return this.routingLogicController;
/*     */   }
/*     */   
/*     */   public class $anonfun$2 extends AbstractFunction1<Props, ActorRef> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ActorRef apply(Props props) {
/* 164 */       return this.$outer.context().actorOf(props.withDispatcher(this.$outer.context().props().dispatcher()), 
/* 165 */           "routingLogicController");
/*     */     }
/*     */     
/*     */     public $anonfun$2(RouterActor $outer) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> receive() {
/* 167 */     return (PartialFunction<Object, BoxedUnit>)new RouterActor$$anonfun$receive$1(this);
/*     */   }
/*     */   
/*     */   public class RouterActor$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/routing/GetRoutees$.MODULE$ : Lakka/routing/GetRoutees$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 82
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 82
/*     */       //   29: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   32: aload_0
/*     */       //   33: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   36: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   39: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   42: new akka/routing/Routees
/*     */       //   45: dup
/*     */       //   46: aload_0
/*     */       //   47: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   50: invokevirtual cell : ()Lakka/routing/RoutedActorCell;
/*     */       //   53: invokevirtual router : ()Lakka/routing/Router;
/*     */       //   56: invokevirtual routees : ()Lscala/collection/immutable/IndexedSeq;
/*     */       //   59: invokespecial <init> : (Lscala/collection/immutable/IndexedSeq;)V
/*     */       //   62: aload_0
/*     */       //   63: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   66: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   69: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   74: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   77: astore #5
/*     */       //   79: goto -> 311
/*     */       //   82: getstatic akka/routing/CurrentRoutees$.MODULE$ : Lakka/routing/CurrentRoutees$;
/*     */       //   85: aload_3
/*     */       //   86: astore #6
/*     */       //   88: dup
/*     */       //   89: ifnonnull -> 101
/*     */       //   92: pop
/*     */       //   93: aload #6
/*     */       //   95: ifnull -> 109
/*     */       //   98: goto -> 174
/*     */       //   101: aload #6
/*     */       //   103: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   106: ifeq -> 174
/*     */       //   109: aload_0
/*     */       //   110: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   113: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   116: getstatic akka/actor/Props$.MODULE$ : Lakka/actor/Props$;
/*     */       //   119: ldc akka/routing/CollectRouteeRefs
/*     */       //   121: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   124: iconst_2
/*     */       //   125: anewarray java/lang/Object
/*     */       //   128: dup
/*     */       //   129: iconst_0
/*     */       //   130: aload_0
/*     */       //   131: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   134: invokevirtual cell : ()Lakka/routing/RoutedActorCell;
/*     */       //   137: invokevirtual router : ()Lakka/routing/Router;
/*     */       //   140: invokevirtual routees : ()Lscala/collection/immutable/IndexedSeq;
/*     */       //   143: aastore
/*     */       //   144: dup
/*     */       //   145: iconst_1
/*     */       //   146: aload_0
/*     */       //   147: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   150: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   153: aastore
/*     */       //   154: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */       //   157: invokevirtual apply : (Ljava/lang/Class;Lscala/collection/Seq;)Lakka/actor/Props;
/*     */       //   160: invokeinterface actorOf : (Lakka/actor/Props;)Lakka/actor/ActorRef;
/*     */       //   165: pop
/*     */       //   166: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   169: astore #5
/*     */       //   171: goto -> 311
/*     */       //   174: aload_3
/*     */       //   175: instanceof akka/routing/AddRoutee
/*     */       //   178: ifeq -> 214
/*     */       //   181: aload_3
/*     */       //   182: checkcast akka/routing/AddRoutee
/*     */       //   185: astore #7
/*     */       //   187: aload #7
/*     */       //   189: invokevirtual routee : ()Lakka/routing/Routee;
/*     */       //   192: astore #8
/*     */       //   194: aload_0
/*     */       //   195: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   198: invokevirtual cell : ()Lakka/routing/RoutedActorCell;
/*     */       //   201: aload #8
/*     */       //   203: invokevirtual addRoutee : (Lakka/routing/Routee;)V
/*     */       //   206: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   209: astore #5
/*     */       //   211: goto -> 311
/*     */       //   214: aload_3
/*     */       //   215: instanceof akka/routing/RemoveRoutee
/*     */       //   218: ifeq -> 262
/*     */       //   221: aload_3
/*     */       //   222: checkcast akka/routing/RemoveRoutee
/*     */       //   225: astore #9
/*     */       //   227: aload #9
/*     */       //   229: invokevirtual routee : ()Lakka/routing/Routee;
/*     */       //   232: astore #10
/*     */       //   234: aload_0
/*     */       //   235: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   238: invokevirtual cell : ()Lakka/routing/RoutedActorCell;
/*     */       //   241: aload #10
/*     */       //   243: iconst_1
/*     */       //   244: invokevirtual removeRoutee : (Lakka/routing/Routee;Z)V
/*     */       //   247: aload_0
/*     */       //   248: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   251: invokevirtual stopIfAllRouteesRemoved : ()V
/*     */       //   254: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   257: astore #5
/*     */       //   259: goto -> 311
/*     */       //   262: aload_0
/*     */       //   263: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   266: invokevirtual routingLogicController : ()Lscala/Option;
/*     */       //   269: invokevirtual isDefined : ()Z
/*     */       //   272: ifeq -> 302
/*     */       //   275: aload_0
/*     */       //   276: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   279: invokevirtual routingLogicController : ()Lscala/Option;
/*     */       //   282: new akka/routing/RouterActor$$anonfun$receive$1$$anonfun$applyOrElse$1
/*     */       //   285: dup
/*     */       //   286: aload_0
/*     */       //   287: aload_3
/*     */       //   288: invokespecial <init> : (Lakka/routing/RouterActor$$anonfun$receive$1;Ljava/lang/Object;)V
/*     */       //   291: invokevirtual foreach : (Lscala/Function1;)V
/*     */       //   294: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   297: astore #5
/*     */       //   299: goto -> 311
/*     */       //   302: aload_2
/*     */       //   303: aload_1
/*     */       //   304: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   309: astore #5
/*     */       //   311: aload #5
/*     */       //   313: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #167	-> 0
/*     */       //   #168	-> 2
/*     */       //   #169	-> 29
/*     */       //   #170	-> 82
/*     */       //   #171	-> 109
/*     */       //   #172	-> 174
/*     */       //   #173	-> 194
/*     */       //   #174	-> 214
/*     */       //   #175	-> 234
/*     */       //   #176	-> 247
/*     */       //   #174	-> 257
/*     */       //   #177	-> 262
/*     */       //   #178	-> 275
/*     */       //   #167	-> 302
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	314	0	this	Lakka/routing/RouterActor$$anonfun$receive$1;
/*     */       //   0	314	1	x1	Ljava/lang/Object;
/*     */       //   0	314	2	default	Lscala/Function1;
/*     */       //   194	120	8	routee	Lakka/routing/Routee;
/*     */       //   234	80	10	routee	Lakka/routing/Routee;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/routing/GetRoutees$.MODULE$ : Lakka/routing/GetRoutees$;
/*     */       //   5: aload_2
/*     */       //   6: astore_3
/*     */       //   7: dup
/*     */       //   8: ifnonnull -> 19
/*     */       //   11: pop
/*     */       //   12: aload_3
/*     */       //   13: ifnull -> 26
/*     */       //   16: goto -> 32
/*     */       //   19: aload_3
/*     */       //   20: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   23: ifeq -> 32
/*     */       //   26: iconst_1
/*     */       //   27: istore #4
/*     */       //   29: goto -> 113
/*     */       //   32: getstatic akka/routing/CurrentRoutees$.MODULE$ : Lakka/routing/CurrentRoutees$;
/*     */       //   35: aload_2
/*     */       //   36: astore #5
/*     */       //   38: dup
/*     */       //   39: ifnonnull -> 51
/*     */       //   42: pop
/*     */       //   43: aload #5
/*     */       //   45: ifnull -> 59
/*     */       //   48: goto -> 65
/*     */       //   51: aload #5
/*     */       //   53: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   56: ifeq -> 65
/*     */       //   59: iconst_1
/*     */       //   60: istore #4
/*     */       //   62: goto -> 113
/*     */       //   65: aload_2
/*     */       //   66: instanceof akka/routing/AddRoutee
/*     */       //   69: ifeq -> 78
/*     */       //   72: iconst_1
/*     */       //   73: istore #4
/*     */       //   75: goto -> 113
/*     */       //   78: aload_2
/*     */       //   79: instanceof akka/routing/RemoveRoutee
/*     */       //   82: ifeq -> 91
/*     */       //   85: iconst_1
/*     */       //   86: istore #4
/*     */       //   88: goto -> 113
/*     */       //   91: aload_0
/*     */       //   92: getfield $outer : Lakka/routing/RouterActor;
/*     */       //   95: invokevirtual routingLogicController : ()Lscala/Option;
/*     */       //   98: invokevirtual isDefined : ()Z
/*     */       //   101: ifeq -> 110
/*     */       //   104: iconst_1
/*     */       //   105: istore #4
/*     */       //   107: goto -> 113
/*     */       //   110: iconst_0
/*     */       //   111: istore #4
/*     */       //   113: iload #4
/*     */       //   115: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #167	-> 0
/*     */       //   #168	-> 2
/*     */       //   #169	-> 26
/*     */       //   #170	-> 32
/*     */       //   #171	-> 59
/*     */       //   #172	-> 65
/*     */       //   #173	-> 72
/*     */       //   #174	-> 78
/*     */       //   #177	-> 91
/*     */       //   #178	-> 104
/*     */       //   #167	-> 110
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	116	0	this	Lakka/routing/RouterActor$$anonfun$receive$1;
/*     */       //   0	116	1	x1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public RouterActor$$anonfun$receive$1(RouterActor $outer) {}
/*     */     
/*     */     public class RouterActor$$anonfun$receive$1$$anonfun$applyOrElse$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object x1$1;
/*     */       
/*     */       public final void apply(ActorRef x$2) {
/* 178 */         x$2.forward(this.x1$1, this.$outer.akka$routing$RouterActor$$anonfun$$$outer().context());
/*     */       }
/*     */       
/*     */       public RouterActor$$anonfun$receive$1$$anonfun$applyOrElse$1(RouterActor$$anonfun$receive$1 $outer, Object x1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public void stopIfAllRouteesRemoved() {
/* 182 */     if (cell().router().routees().isEmpty() && cell().routerConfig().stopRouterWhenAllRouteesRemoved())
/* 183 */       context().stop(self()); 
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable cause, Option msg) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RouterActor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */