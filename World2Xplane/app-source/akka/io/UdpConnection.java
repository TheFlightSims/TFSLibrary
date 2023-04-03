/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorLogging;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.actor.package$;
/*     */ import akka.dispatch.RequiresMessageQueue;
/*     */ import akka.dispatch.UnboundedMessageQueueSemantics;
/*     */ import akka.event.LoggingAdapter;
/*     */ import akka.util.ByteString$;
/*     */ import java.net.DatagramSocket;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.DatagramChannel;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005b!B\001\003\001\t1!!D+ea\016{gN\\3di&|gN\003\002\004\t\005\021\021n\034\006\002\013\005!\021m[6b'\025\001q!D\n\027!\tA1\"D\001\n\025\005Q\021!B:dC2\f\027B\001\007\n\005\031\te.\037*fMB\021a\"E\007\002\037)\021\001\003B\001\006C\016$xN]\005\003%=\021Q!Q2u_J\004\"A\004\013\n\005Uy!\001D!di>\024Hj\\4hS:<\007cA\f\03395\t\001D\003\002\032\t\005AA-[:qCR\034\007.\003\002\0341\t!\"+Z9vSJ,7/T3tg\006<W-U;fk\026\004\"aF\017\n\005yA\"AH+oE>,h\016Z3e\033\026\0348/Y4f#V,W/Z*f[\006tG/[2t\021!\001\003A!A!\002\023\021\023aB;ea\016{gN\\\002\001!\t\031C%D\001\003\023\t)#AA\bVIB\034uN\0348fGR,G-\022=u\021!9\003A!A!\002\023A\023aD2iC:tW\r\034*fO&\034HO]=\021\005\rJ\023B\001\026\003\005=\031\005.\0318oK2\024VmZ5tiJL\b\002\003\027\001\005\003\005\013\021B\027\002\023\r|W.\\1oI\026\024\bC\001\b/\023\tysB\001\005BGR|'OU3g\021!\t\004A!A!\002\023\021\024aB2p]:,7\r\036\t\003gur!\001N\036\017\005URdB\001\034:\033\0059$B\001\035\"\003\031a$o\\8u}%\tQ!\003\002\004\t%\021AHA\001\r+\022\0048i\0348oK\016$X\rZ\005\003}}\022qaQ8o]\026\034GO\003\002=\005!)\021\t\001C\001\005\0061A(\0338jiz\"Ra\021#F\r\036\003\"a\t\001\t\013\001\002\005\031\001\022\t\013\035\002\005\031\001\025\t\0131\002\005\031A\027\t\013E\002\005\031\001\032\t\017%\003\001\031!C\001\025\006Y\001/\0328eS:<7+\0328e+\005Y\005\003\002\005M\0356J!!T\005\003\rQ+\b\017\\33!\t\031t*\003\002Q\t!1+\0328e\021\035\021\006\0011A\005\002M\013q\002]3oI&twmU3oI~#S-\035\013\003)^\003\"\001C+\n\005YK!\001B+oSRDq\001W)\002\002\003\0071*A\002yIEBaA\027\001!B\023Y\025\001\0049f]\022LgnZ*f]\022\004\003\"\002/\001\t\003i\026\001D<sSR,\007+\0328eS:<W#\0010\021\005!y\026B\0011\n\005\035\021un\0347fC:DqA\031\001C\002\023\0051-A\004dQ\006tg.\0327\026\003\021\004\"!\0327\016\003\031T!a\0325\002\021\rD\027M\0348fYNT!!\0336\002\0079LwNC\001l\003\021Q\027M^1\n\00554'a\004#bi\006<'/Y7DQ\006tg.\0327\t\r=\004\001\025!\003e\003!\031\007.\0318oK2\004\003\"B9\001\t\003\021\030a\002:fG\026Lg/Z\013\002gB!\001\002\036<U\023\t)\030BA\bQCJ$\030.\0317Gk:\034G/[8o!\tAq/\003\002y\023\t\031\021I\\=\t\013i\004A\021A>\002\023\r|gN\\3di\026$Gc\001?\002\002A\021QP`\007\002\001%\021q0\005\002\b%\026\034W-\033<f\021\035\t\031!\037a\001\003\013\tAB]3hSN$(/\031;j_:\0042aIA\004\023\r\tIA\001\002\024\007\"\fgN\\3m%\026<\027n\035;sCRLwN\034\005\b\003\033\001A\021AA\b\003\031!wNU3bIR)A+!\005\002\024!A\0211AA\006\001\004\t)\001C\004\002\026\005-\001\031A\027\002\017!\fg\016\0327fe\"9\021\021\004\001\005\006\005m\021a\0023p/JLG/\032\013\002)\"9\021q\004\001\005B\005m\021\001\0039pgR\034Fo\0349")
/*     */ public class UdpConnection implements Actor, ActorLogging, RequiresMessageQueue<UnboundedMessageQueueSemantics> {
/*     */   public final UdpConnectedExt akka$io$UdpConnection$$udpConn;
/*     */   
/*     */   public final ActorRef akka$io$UdpConnection$$commander;
/*     */   
/*     */   public final UdpConnected.Connect akka$io$UdpConnection$$connect;
/*     */   
/*     */   private Tuple2<UdpConnected.Send, ActorRef> pendingSend;
/*     */   
/*     */   private final DatagramChannel channel;
/*     */   
/*     */   private LoggingAdapter akka$actor$ActorLogging$$_log;
/*     */   
/*     */   private final ActorContext context;
/*     */   
/*     */   private final ActorRef self;
/*     */   
/*     */   public LoggingAdapter akka$actor$ActorLogging$$_log() {
/*  20 */     return this.akka$actor$ActorLogging$$_log;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/*  20 */     this.akka$actor$ActorLogging$$_log = x$1;
/*     */   }
/*     */   
/*     */   public LoggingAdapter log() {
/*  20 */     return ActorLogging.class.log(this);
/*     */   }
/*     */   
/*     */   public ActorContext context() {
/*  20 */     return this.context;
/*     */   }
/*     */   
/*     */   public final ActorRef self() {
/*  20 */     return this.self;
/*     */   }
/*     */   
/*     */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*  20 */     this.context = x$1;
/*     */   }
/*     */   
/*     */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*  20 */     this.self = x$1;
/*     */   }
/*     */   
/*     */   public final ActorRef sender() {
/*  20 */     return Actor.class.sender(this);
/*     */   }
/*     */   
/*     */   public void aroundReceive(PartialFunction receive, Object msg) {
/*  20 */     Actor.class.aroundReceive(this, receive, msg);
/*     */   }
/*     */   
/*     */   public void aroundPreStart() {
/*  20 */     Actor.class.aroundPreStart(this);
/*     */   }
/*     */   
/*     */   public void aroundPostStop() {
/*  20 */     Actor.class.aroundPostStop(this);
/*     */   }
/*     */   
/*     */   public void aroundPreRestart(Throwable reason, Option message) {
/*  20 */     Actor.class.aroundPreRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void aroundPostRestart(Throwable reason) {
/*  20 */     Actor.class.aroundPostRestart(this, reason);
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/*  20 */     return Actor.class.supervisorStrategy(this);
/*     */   }
/*     */   
/*     */   public void preStart() throws Exception {
/*  20 */     Actor.class.preStart(this);
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable reason, Option message) throws Exception {
/*  20 */     Actor.class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void postRestart(Throwable reason) throws Exception {
/*  20 */     Actor.class.postRestart(this, reason);
/*     */   }
/*     */   
/*     */   public void unhandled(Object message) {
/*  20 */     Actor.class.unhandled(this, message);
/*     */   }
/*     */   
/*     */   public UdpConnection(UdpConnectedExt udpConn, ChannelRegistry channelRegistry, ActorRef commander, UdpConnected.Connect connect) {
/*  20 */     Actor.class.$init$(this);
/*  20 */     ActorLogging.class.$init$(this);
/*  30 */     null;
/*  30 */     this.pendingSend = null;
/*  33 */     context().watch(connect.handler());
/*  35 */     DatagramChannel datagramChannel = DatagramChannel.open();
/*  36 */     datagramChannel.configureBlocking(false);
/*  37 */     DatagramSocket socket = datagramChannel.socket();
/*  38 */     connect.options().foreach((Function1)new $anonfun$1(this, socket));
/*  39 */     liftedTree1$1(datagramChannel, socket);
/*  49 */     this.channel = datagramChannel;
/*  51 */     channelRegistry.register(channel(), 1, self());
/*  52 */     log().debug("Successfully connected to [{}]", connect.remoteAddress());
/*     */   }
/*     */   
/*     */   public Tuple2<UdpConnected.Send, ActorRef> pendingSend() {
/*     */     return this.pendingSend;
/*     */   }
/*     */   
/*     */   public void pendingSend_$eq(Tuple2<UdpConnected.Send, ActorRef> x$1) {
/*     */     this.pendingSend = x$1;
/*     */   }
/*     */   
/*     */   public boolean writePending() {
/*     */     return (pendingSend() != null);
/*     */   }
/*     */   
/*     */   public DatagramChannel channel() {
/*     */     return this.channel;
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction1<Inet.SocketOption, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final DatagramSocket socket$1;
/*     */     
/*     */     public final void apply(Inet.SocketOption x$1) {
/*     */       x$1.beforeDatagramBind(this.socket$1);
/*     */     }
/*     */     
/*     */     public $anonfun$1(UdpConnection $outer, DatagramSocket socket$1) {}
/*     */   }
/*     */   
/*     */   private final Object liftedTree1$1(DatagramChannel datagramChannel$1, DatagramSocket socket$1) {
/*     */     try {
/*     */       this.akka$io$UdpConnection$$connect.localAddress().foreach((Function1)new UdpConnection$$anonfun$liftedTree1$1$1(this, socket$1));
/*     */     } finally {
/*     */       BoxedUnit boxedUnit;
/*     */       Exception exception1 = null, exception2 = exception1;
/*     */       Option option = NonFatal$.MODULE$.unapply(exception2);
/*     */       if (option.isEmpty())
/*     */         throw exception1; 
/*     */       Throwable e = (Throwable)option.get();
/*     */       log().debug("Failure while connecting UDP channel to remote address [{}] local address [{}]: {}", this.akka$io$UdpConnection$$connect.remoteAddress(), this.akka$io$UdpConnection$$connect.localAddress().getOrElse((Function0)new UdpConnection$$anonfun$liftedTree1$1$2(this)), e);
/*     */       package$.MODULE$.actorRef2Scala(this.akka$io$UdpConnection$$commander).$bang(new UdpConnected.CommandFailed(this.akka$io$UdpConnection$$connect), self());
/*     */       context().stop(self());
/*     */     } 
/*     */     return boxedUnit;
/*     */   }
/*     */   
/*     */   public class UdpConnection$$anonfun$liftedTree1$1$1 extends AbstractFunction1<SocketAddress, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final DatagramSocket socket$1;
/*     */     
/*     */     public final void apply(SocketAddress x$1) {
/*     */       this.socket$1.bind(x$1);
/*     */     }
/*     */     
/*     */     public UdpConnection$$anonfun$liftedTree1$1$1(UdpConnection $outer, DatagramSocket socket$1) {}
/*     */   }
/*     */   
/*     */   public class UdpConnection$$anonfun$liftedTree1$1$2 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/*     */       return "undefined";
/*     */     }
/*     */     
/*     */     public UdpConnection$$anonfun$liftedTree1$1$2(UdpConnection $outer) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> receive() {
/*  54 */     return (PartialFunction<Object, BoxedUnit>)new UdpConnection$$anonfun$receive$1(this);
/*     */   }
/*     */   
/*     */   public class UdpConnection$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*  54 */       Object object2, object1 = x1;
/*  55 */       if (object1 instanceof ChannelRegistration) {
/*  55 */         ChannelRegistration channelRegistration = (ChannelRegistration)object1;
/*  56 */         package$.MODULE$.actorRef2Scala(this.$outer.akka$io$UdpConnection$$commander).$bang(UdpConnected.Connected$.MODULE$, this.$outer.self());
/*  57 */         this.$outer.context().become(this.$outer.connected(channelRegistration), true);
/*  57 */         object2 = BoxedUnit.UNIT;
/*     */       } else {
/*     */         object2 = default.apply(x1);
/*     */       } 
/*     */       return (B1)object2;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/*     */       boolean bool;
/*     */       Object object = x1;
/*     */       if (object instanceof ChannelRegistration) {
/*     */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public UdpConnection$$anonfun$receive$1(UdpConnection $outer) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> connected(ChannelRegistration registration) {
/*  60 */     return (PartialFunction<Object, BoxedUnit>)new UdpConnection$$anonfun$connected$1(this, registration);
/*     */   }
/*     */   
/*     */   public class UdpConnection$$anonfun$connected$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ChannelRegistration registration$1;
/*     */     
/*     */     public UdpConnection$$anonfun$connected$1(UdpConnection $outer, ChannelRegistration registration$1) {}
/*     */     
/*     */     public final boolean isDefinedAt(Object x2) {
/*     */       // Byte code:
/*     */       //   0: iconst_0
/*     */       //   1: istore_2
/*     */       //   2: aconst_null
/*     */       //   3: pop
/*     */       //   4: aconst_null
/*     */       //   5: astore_3
/*     */       //   6: aload_1
/*     */       //   7: astore #4
/*     */       //   9: getstatic akka/io/UdpConnected$SuspendReading$.MODULE$ : Lakka/io/UdpConnected$SuspendReading$;
/*     */       //   12: aload #4
/*     */       //   14: astore #5
/*     */       //   16: dup
/*     */       //   17: ifnonnull -> 29
/*     */       //   20: pop
/*     */       //   21: aload #5
/*     */       //   23: ifnull -> 37
/*     */       //   26: goto -> 43
/*     */       //   29: aload #5
/*     */       //   31: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   34: ifeq -> 43
/*     */       //   37: iconst_1
/*     */       //   38: istore #6
/*     */       //   40: goto -> 244
/*     */       //   43: getstatic akka/io/UdpConnected$ResumeReading$.MODULE$ : Lakka/io/UdpConnected$ResumeReading$;
/*     */       //   46: aload #4
/*     */       //   48: astore #7
/*     */       //   50: dup
/*     */       //   51: ifnonnull -> 63
/*     */       //   54: pop
/*     */       //   55: aload #7
/*     */       //   57: ifnull -> 71
/*     */       //   60: goto -> 77
/*     */       //   63: aload #7
/*     */       //   65: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   68: ifeq -> 77
/*     */       //   71: iconst_1
/*     */       //   72: istore #6
/*     */       //   74: goto -> 244
/*     */       //   77: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   80: aload #4
/*     */       //   82: astore #8
/*     */       //   84: dup
/*     */       //   85: ifnonnull -> 97
/*     */       //   88: pop
/*     */       //   89: aload #8
/*     */       //   91: ifnull -> 105
/*     */       //   94: goto -> 111
/*     */       //   97: aload #8
/*     */       //   99: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   102: ifeq -> 111
/*     */       //   105: iconst_1
/*     */       //   106: istore #6
/*     */       //   108: goto -> 244
/*     */       //   111: getstatic akka/io/UdpConnected$Disconnect$.MODULE$ : Lakka/io/UdpConnected$Disconnect$;
/*     */       //   114: aload #4
/*     */       //   116: astore #9
/*     */       //   118: dup
/*     */       //   119: ifnonnull -> 131
/*     */       //   122: pop
/*     */       //   123: aload #9
/*     */       //   125: ifnull -> 139
/*     */       //   128: goto -> 145
/*     */       //   131: aload #9
/*     */       //   133: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   136: ifeq -> 145
/*     */       //   139: iconst_1
/*     */       //   140: istore #6
/*     */       //   142: goto -> 244
/*     */       //   145: aload #4
/*     */       //   147: instanceof akka/io/UdpConnected$Send
/*     */       //   150: ifeq -> 177
/*     */       //   153: iconst_1
/*     */       //   154: istore_2
/*     */       //   155: aload #4
/*     */       //   157: checkcast akka/io/UdpConnected$Send
/*     */       //   160: astore_3
/*     */       //   161: aload_0
/*     */       //   162: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   165: invokevirtual writePending : ()Z
/*     */       //   168: ifeq -> 177
/*     */       //   171: iconst_1
/*     */       //   172: istore #6
/*     */       //   174: goto -> 244
/*     */       //   177: iload_2
/*     */       //   178: ifeq -> 197
/*     */       //   181: aload_3
/*     */       //   182: invokevirtual payload : ()Lakka/util/ByteString;
/*     */       //   185: invokevirtual isEmpty : ()Z
/*     */       //   188: ifeq -> 197
/*     */       //   191: iconst_1
/*     */       //   192: istore #6
/*     */       //   194: goto -> 244
/*     */       //   197: iload_2
/*     */       //   198: ifeq -> 207
/*     */       //   201: iconst_1
/*     */       //   202: istore #6
/*     */       //   204: goto -> 244
/*     */       //   207: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
/*     */       //   210: aload #4
/*     */       //   212: astore #10
/*     */       //   214: dup
/*     */       //   215: ifnonnull -> 227
/*     */       //   218: pop
/*     */       //   219: aload #10
/*     */       //   221: ifnull -> 235
/*     */       //   224: goto -> 241
/*     */       //   227: aload #10
/*     */       //   229: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   232: ifeq -> 241
/*     */       //   235: iconst_1
/*     */       //   236: istore #6
/*     */       //   238: goto -> 244
/*     */       //   241: iconst_0
/*     */       //   242: istore #6
/*     */       //   244: iload #6
/*     */       //   246: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #72	-> 0
/*     */       //   #60	-> 6
/*     */       //   #61	-> 9
/*     */       //   #62	-> 43
/*     */       //   #63	-> 77
/*     */       //   #65	-> 111
/*     */       //   #72	-> 145
/*     */       //   #60	-> 177
/*     */       //   #76	-> 181
/*     */       //   #77	-> 191
/*     */       //   #60	-> 197
/*     */       //   #80	-> 201
/*     */       //   #84	-> 207
/*     */       //   #60	-> 241
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	247	0	this	Lakka/io/UdpConnection$$anonfun$connected$1;
/*     */       //   0	247	1	x2	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: iconst_0
/*     */       //   1: istore_3
/*     */       //   2: aconst_null
/*     */       //   3: pop
/*     */       //   4: aconst_null
/*     */       //   5: astore #4
/*     */       //   7: aload_1
/*     */       //   8: astore #5
/*     */       //   10: getstatic akka/io/UdpConnected$SuspendReading$.MODULE$ : Lakka/io/UdpConnected$SuspendReading$;
/*     */       //   13: aload #5
/*     */       //   15: astore #6
/*     */       //   17: dup
/*     */       //   18: ifnonnull -> 30
/*     */       //   21: pop
/*     */       //   22: aload #6
/*     */       //   24: ifnull -> 38
/*     */       //   27: goto -> 56
/*     */       //   30: aload #6
/*     */       //   32: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   35: ifeq -> 56
/*     */       //   38: aload_0
/*     */       //   39: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   42: iconst_1
/*     */       //   43: invokeinterface disableInterest : (I)V
/*     */       //   48: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   51: astore #7
/*     */       //   53: goto -> 563
/*     */       //   56: getstatic akka/io/UdpConnected$ResumeReading$.MODULE$ : Lakka/io/UdpConnected$ResumeReading$;
/*     */       //   59: aload #5
/*     */       //   61: astore #8
/*     */       //   63: dup
/*     */       //   64: ifnonnull -> 76
/*     */       //   67: pop
/*     */       //   68: aload #8
/*     */       //   70: ifnull -> 84
/*     */       //   73: goto -> 102
/*     */       //   76: aload #8
/*     */       //   78: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   81: ifeq -> 102
/*     */       //   84: aload_0
/*     */       //   85: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   88: iconst_1
/*     */       //   89: invokeinterface enableInterest : (I)V
/*     */       //   94: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   97: astore #7
/*     */       //   99: goto -> 563
/*     */       //   102: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   105: aload #5
/*     */       //   107: astore #9
/*     */       //   109: dup
/*     */       //   110: ifnonnull -> 122
/*     */       //   113: pop
/*     */       //   114: aload #9
/*     */       //   116: ifnull -> 130
/*     */       //   119: goto -> 159
/*     */       //   122: aload #9
/*     */       //   124: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   127: ifeq -> 159
/*     */       //   130: aload_0
/*     */       //   131: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   134: aload_0
/*     */       //   135: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   138: aload_0
/*     */       //   139: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   142: getfield akka$io$UdpConnection$$connect : Lakka/io/UdpConnected$Connect;
/*     */       //   145: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   148: invokevirtual doRead : (Lakka/io/ChannelRegistration;Lakka/actor/ActorRef;)V
/*     */       //   151: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   154: astore #7
/*     */       //   156: goto -> 563
/*     */       //   159: getstatic akka/io/UdpConnected$Disconnect$.MODULE$ : Lakka/io/UdpConnected$Disconnect$;
/*     */       //   162: aload #5
/*     */       //   164: astore #10
/*     */       //   166: dup
/*     */       //   167: ifnonnull -> 179
/*     */       //   170: pop
/*     */       //   171: aload #10
/*     */       //   173: ifnull -> 187
/*     */       //   176: goto -> 300
/*     */       //   179: aload #10
/*     */       //   181: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   184: ifeq -> 300
/*     */       //   187: aload_0
/*     */       //   188: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   191: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   194: ldc 'Closing UDP connection to [{}]'
/*     */       //   196: aload_0
/*     */       //   197: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   200: getfield akka$io$UdpConnection$$connect : Lakka/io/UdpConnected$Connect;
/*     */       //   203: invokevirtual remoteAddress : ()Ljava/net/InetSocketAddress;
/*     */       //   206: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   211: aload_0
/*     */       //   212: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   215: invokevirtual channel : ()Ljava/nio/channels/DatagramChannel;
/*     */       //   218: invokevirtual close : ()V
/*     */       //   221: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   224: aload_0
/*     */       //   225: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   228: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   231: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   234: getstatic akka/io/UdpConnected$Disconnected$.MODULE$ : Lakka/io/UdpConnected$Disconnected$;
/*     */       //   237: aload_0
/*     */       //   238: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   241: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   244: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   249: aload_0
/*     */       //   250: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   253: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   256: ldc 'Connection closed to [{}], stopping listener'
/*     */       //   258: aload_0
/*     */       //   259: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   262: getfield akka$io$UdpConnection$$connect : Lakka/io/UdpConnected$Connect;
/*     */       //   265: invokevirtual remoteAddress : ()Ljava/net/InetSocketAddress;
/*     */       //   268: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   273: aload_0
/*     */       //   274: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   277: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   280: aload_0
/*     */       //   281: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   284: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   287: invokeinterface stop : (Lakka/actor/ActorRef;)V
/*     */       //   292: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   295: astore #7
/*     */       //   297: goto -> 563
/*     */       //   300: aload #5
/*     */       //   302: instanceof akka/io/UdpConnected$Send
/*     */       //   305: ifeq -> 399
/*     */       //   308: iconst_1
/*     */       //   309: istore_3
/*     */       //   310: aload #5
/*     */       //   312: checkcast akka/io/UdpConnected$Send
/*     */       //   315: astore #4
/*     */       //   317: aload_0
/*     */       //   318: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   321: invokevirtual writePending : ()Z
/*     */       //   324: ifeq -> 399
/*     */       //   327: aload_0
/*     */       //   328: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   331: getfield akka$io$UdpConnection$$udpConn : Lakka/io/UdpConnectedExt;
/*     */       //   334: invokevirtual settings : ()Lakka/io/Udp$UdpSettings;
/*     */       //   337: invokevirtual TraceLogging : ()Z
/*     */       //   340: ifeq -> 357
/*     */       //   343: aload_0
/*     */       //   344: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   347: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   350: ldc 'Dropping write because queue is full'
/*     */       //   352: invokeinterface debug : (Ljava/lang/String;)V
/*     */       //   357: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   360: aload_0
/*     */       //   361: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   364: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   367: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   370: new akka/io/UdpConnected$CommandFailed
/*     */       //   373: dup
/*     */       //   374: aload #4
/*     */       //   376: invokespecial <init> : (Lakka/io/UdpConnected$Command;)V
/*     */       //   379: aload_0
/*     */       //   380: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   383: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   386: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   391: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   394: astore #7
/*     */       //   396: goto -> 563
/*     */       //   399: iload_3
/*     */       //   400: ifeq -> 466
/*     */       //   403: aload #4
/*     */       //   405: invokevirtual payload : ()Lakka/util/ByteString;
/*     */       //   408: invokevirtual isEmpty : ()Z
/*     */       //   411: ifeq -> 466
/*     */       //   414: aload #4
/*     */       //   416: invokevirtual wantsAck : ()Z
/*     */       //   419: ifeq -> 458
/*     */       //   422: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   425: aload_0
/*     */       //   426: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   429: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   432: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   435: aload #4
/*     */       //   437: invokevirtual ack : ()Ljava/lang/Object;
/*     */       //   440: aload_0
/*     */       //   441: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   444: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   447: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   452: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   455: goto -> 461
/*     */       //   458: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   461: astore #7
/*     */       //   463: goto -> 563
/*     */       //   466: iload_3
/*     */       //   467: ifeq -> 511
/*     */       //   470: aload_0
/*     */       //   471: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   474: new scala/Tuple2
/*     */       //   477: dup
/*     */       //   478: aload #4
/*     */       //   480: aload_0
/*     */       //   481: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   484: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   487: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */       //   490: invokevirtual pendingSend_$eq : (Lscala/Tuple2;)V
/*     */       //   493: aload_0
/*     */       //   494: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   497: iconst_4
/*     */       //   498: invokeinterface enableInterest : (I)V
/*     */       //   503: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   506: astore #7
/*     */       //   508: goto -> 563
/*     */       //   511: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
/*     */       //   514: aload #5
/*     */       //   516: astore #11
/*     */       //   518: dup
/*     */       //   519: ifnonnull -> 531
/*     */       //   522: pop
/*     */       //   523: aload #11
/*     */       //   525: ifnull -> 539
/*     */       //   528: goto -> 554
/*     */       //   531: aload #11
/*     */       //   533: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   536: ifeq -> 554
/*     */       //   539: aload_0
/*     */       //   540: getfield $outer : Lakka/io/UdpConnection;
/*     */       //   543: invokevirtual doWrite : ()V
/*     */       //   546: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   549: astore #7
/*     */       //   551: goto -> 563
/*     */       //   554: aload_2
/*     */       //   555: aload_1
/*     */       //   556: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   561: astore #7
/*     */       //   563: aload #7
/*     */       //   565: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #72	-> 0
/*     */       //   #60	-> 7
/*     */       //   #61	-> 10
/*     */       //   #62	-> 56
/*     */       //   #63	-> 102
/*     */       //   #65	-> 159
/*     */       //   #66	-> 187
/*     */       //   #67	-> 211
/*     */       //   #68	-> 221
/*     */       //   #69	-> 249
/*     */       //   #70	-> 273
/*     */       //   #65	-> 295
/*     */       //   #72	-> 300
/*     */       //   #73	-> 327
/*     */       //   #74	-> 357
/*     */       //   #72	-> 394
/*     */       //   #60	-> 399
/*     */       //   #76	-> 403
/*     */       //   #77	-> 414
/*     */       //   #78	-> 422
/*     */       //   #77	-> 458
/*     */       //   #60	-> 466
/*     */       //   #81	-> 470
/*     */       //   #82	-> 493
/*     */       //   #80	-> 506
/*     */       //   #84	-> 511
/*     */       //   #60	-> 554
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	566	0	this	Lakka/io/UdpConnection$$anonfun$connected$1;
/*     */       //   0	566	1	x2	Ljava/lang/Object;
/*     */       //   0	566	2	default	Lscala/Function1;
/*     */     }
/*     */   }
/*     */   
/*     */   private final void innerRead$1(int readsLeft, ByteBuffer buffer, ActorRef handler$1) {
/*     */     while (true) {
/*  89 */       buffer.clear();
/*  90 */       buffer.limit(this.akka$io$UdpConnection$$udpConn.settings().DirectBufferSize());
/*  92 */       if (channel().read(buffer) > 0) {
/*  93 */         buffer.flip();
/*  94 */         package$.MODULE$.actorRef2Scala(handler$1).$bang(new UdpConnected.Received(ByteString$.MODULE$.apply(buffer)), self());
/*  95 */         buffer = buffer;
/*  95 */         readsLeft--;
/*     */         continue;
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doRead(ChannelRegistration registration, ActorRef handler) {
/*  98 */     ByteBuffer buffer = this.akka$io$UdpConnection$$udpConn.bufferPool().acquire();
/*     */     try {
/*  99 */       innerRead$1(this.akka$io$UdpConnection$$udpConn.settings().BatchReceiveLimit(), buffer, handler);
/*     */       return;
/*     */     } finally {
/* 100 */       registration.enableInterest(1);
/* 101 */       this.akka$io$UdpConnection$$udpConn.bufferPool().release(buffer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void doWrite() {
/* 106 */     ByteBuffer buffer = this.akka$io$UdpConnection$$udpConn.bufferPool().acquire();
/*     */     try {
/* 108 */       Tuple2<UdpConnected.Send, ActorRef> tuple2 = pendingSend();
/* 108 */       if (tuple2 != null) {
/* 108 */         UdpConnected.Send send = (UdpConnected.Send)tuple2._1();
/* 108 */         ActorRef commander = (ActorRef)tuple2._2();
/* 108 */         Tuple2 tuple22 = new Tuple2(send, commander), tuple21 = tuple22;
/* 108 */         UdpConnected.Send send1 = (UdpConnected.Send)tuple21._1();
/* 108 */         ActorRef actorRef1 = (ActorRef)tuple21._2();
/* 109 */         buffer.clear();
/* 110 */         send1.payload().copyToBuffer(buffer);
/* 111 */         buffer.flip();
/* 112 */         int writtenBytes = channel().write(buffer);
/* 113 */         if (this.akka$io$UdpConnection$$udpConn.settings().TraceLogging())
/* 113 */           log().debug("Wrote [{}] bytes to channel", BoxesRunTime.boxToInteger(writtenBytes)); 
/* 116 */         if (writtenBytes == 0) {
/* 116 */           package$.MODULE$.actorRef2Scala(actorRef1).$bang(new UdpConnected.CommandFailed(send1), self());
/* 117 */         } else if (send1.wantsAck()) {
/* 117 */           package$.MODULE$.actorRef2Scala(actorRef1).$bang(send1.ack(), self());
/*     */         } 
/*     */         return;
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     } finally {
/* 119 */       this.akka$io$UdpConnection$$udpConn.bufferPool().release(buffer);
/* 120 */       null;
/* 120 */       pendingSend_$eq(null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void postStop() {
/* 125 */     if (channel().isOpen()) {
/* 126 */       log().debug("Closing DatagramChannel after being stopped");
/*     */       try {
/* 127 */         channel().close();
/*     */       } finally {
/*     */         BoxedUnit boxedUnit;
/* 127 */         Exception exception1 = null, exception2 = exception1;
/* 129 */         Option option = NonFatal$.MODULE$.unapply(exception2);
/* 129 */         if (option.isEmpty())
/*     */           throw exception1; 
/* 129 */         Throwable e = (Throwable)option.get();
/* 129 */         log().debug("Error closing DatagramChannel: {}", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */