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
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.DatagramChannel;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.StringContext;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.TraitSetter;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\035a!B\001\003\001\t1!aC+ea2K7\017^3oKJT!a\001\003\002\005%|'\"A\003\002\t\005\\7.Y\n\007\001\035i1C\006\016\021\005!YQ\"A\005\013\003)\tQa]2bY\006L!\001D\005\003\r\005s\027PU3g!\tq\021#D\001\020\025\t\001B!A\003bGR|'/\003\002\023\037\t)\021i\031;peB\021a\002F\005\003+=\021A\"Q2u_JdunZ4j]\036\004\"a\006\r\016\003\tI!!\007\002\003\027]KG\017[+eaN+g\016\032\t\0047y\001S\"\001\017\013\005u!\021\001\0033jgB\fGo\0315\n\005}a\"\001\006*fcVL'/Z:NKN\034\030mZ3Rk\026,X\r\005\002\034C%\021!\005\b\002\037+:\024w.\0368eK\022lUm]:bO\026\fV/Z;f'\026l\027M\034;jGND\001\002\n\001\003\006\004%\tAJ\001\004k\022\0048\001A\013\002OA\021q\003K\005\003S\t\021a!\0263q\013b$\b\002C\026\001\005\003\005\013\021B\024\002\tU$\007\017\t\005\t[\001\021\t\021)A\005]\005y1\r[1o]\026d'+Z4jgR\024\030\020\005\002\030_%\021\001G\001\002\020\007\"\fgN\\3m%\026<\027n\035;ss\"A!\007\001B\001B\003%1'A\007cS:$7i\\7nC:$WM\035\t\003\035QJ!!N\b\003\021\005\033Go\034:SK\032D\001b\016\001\003\002\003\006I\001O\001\005E&tG\r\005\002:\007:\021!(\021\b\003w\001s!\001P \016\003uR!AP\023\002\rq\022xn\034;?\023\005)\021BA\002\005\023\t\021%!A\002VIBL!\001R#\003\t\tKg\016\032\006\003\005\nAQa\022\001\005\002!\013a\001P5oSRtD#B%K\0272k\005CA\f\001\021\025!c\t1\001(\021\025ic\t1\001/\021\025\021d\t1\0014\021\0259d\t1\0019\021\025y\005\001\"\001Q\003!\031X\r\\3di>\024X#A\032\t\017I\003!\031!C\001'\00691\r[1o]\026dW#\001+\021\005UcV\"\001,\013\005]C\026\001C2iC:tW\r\\:\013\005eS\026a\0018j_*\t1,\001\003kCZ\f\027BA/W\005=!\025\r^1he\006l7\t[1o]\026d\007BB0\001A\003%A+\001\005dQ\006tg.\0327!\021\035\t\007A1A\005\002\t\fA\002\\8dC2\fE\r\032:fgN,\022a\031\t\003\021\021L!!Z\005\003\007\005s\027\020\003\004h\001\001\006IaY\001\016Y>\034\027\r\\!eIJ,7o\035\021\t\013%\004A\021\0016\002\017I,7-Z5wKV\t1\016\005\002m[6\t\001!\003\002o#\t9!+Z2fSZ,\007\"\0029\001\t\003\t\030\001\004:fC\022D\025M\0343mKJ\034HCA6s\021\025\031x\0161\001u\0031\021XmZ5tiJ\fG/[8o!\t9R/\003\002w\005\t\0312\t[1o]\026d'+Z4jgR\024\030\r^5p]\")\001\020\001C\001s\006IAm\034*fG\026Lg/\032\013\004uvt\bC\001\005|\023\ta\030B\001\003V]&$\b\"B:x\001\004!\b\"B@x\001\004\031\024a\0025b]\022dWM\035\005\b\003\007\001A\021IA\003\003!\001xn\035;Ti>\004H#\001>")
/*     */ public class UdpListener implements Actor, ActorLogging, WithUdpSend, RequiresMessageQueue<UnboundedMessageQueueSemantics> {
/*     */   private final UdpExt udp;
/*     */   
/*     */   private final ChannelRegistry channelRegistry;
/*     */   
/*     */   public final ActorRef akka$io$UdpListener$$bindCommander;
/*     */   
/*     */   public final Udp.Bind akka$io$UdpListener$$bind;
/*     */   
/*     */   private final DatagramChannel channel;
/*     */   
/*     */   private final Object localAddress;
/*     */   
/*     */   private Udp.Send akka$io$WithUdpSend$$pendingSend;
/*     */   
/*     */   private ActorRef akka$io$WithUdpSend$$pendingCommander;
/*     */   
/*     */   private boolean akka$io$WithUdpSend$$retriedSend;
/*     */   
/*     */   private final Udp.UdpSettings settings;
/*     */   
/*     */   private LoggingAdapter akka$actor$ActorLogging$$_log;
/*     */   
/*     */   private final ActorContext context;
/*     */   
/*     */   private final ActorRef self;
/*     */   
/*     */   public Udp.Send akka$io$WithUdpSend$$pendingSend() {
/*  21 */     return this.akka$io$WithUdpSend$$pendingSend;
/*     */   }
/*     */   
/*     */   public void akka$io$WithUdpSend$$pendingSend_$eq(Udp.Send x$1) {
/*  21 */     this.akka$io$WithUdpSend$$pendingSend = x$1;
/*     */   }
/*     */   
/*     */   public ActorRef akka$io$WithUdpSend$$pendingCommander() {
/*  21 */     return this.akka$io$WithUdpSend$$pendingCommander;
/*     */   }
/*     */   
/*     */   public void akka$io$WithUdpSend$$pendingCommander_$eq(ActorRef x$1) {
/*  21 */     this.akka$io$WithUdpSend$$pendingCommander = x$1;
/*     */   }
/*     */   
/*     */   public boolean akka$io$WithUdpSend$$retriedSend() {
/*  21 */     return this.akka$io$WithUdpSend$$retriedSend;
/*     */   }
/*     */   
/*     */   public void akka$io$WithUdpSend$$retriedSend_$eq(boolean x$1) {
/*  21 */     this.akka$io$WithUdpSend$$retriedSend = x$1;
/*     */   }
/*     */   
/*     */   public Udp.UdpSettings settings() {
/*  21 */     return this.settings;
/*     */   }
/*     */   
/*     */   public void akka$io$WithUdpSend$_setter_$settings_$eq(Udp.UdpSettings x$1) {
/*  21 */     this.settings = x$1;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> sendHandlers(ChannelRegistration registration) {
/*  21 */     return WithUdpSend$class.sendHandlers(this, registration);
/*     */   }
/*     */   
/*     */   public LoggingAdapter akka$actor$ActorLogging$$_log() {
/*  21 */     return this.akka$actor$ActorLogging$$_log;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/*  21 */     this.akka$actor$ActorLogging$$_log = x$1;
/*     */   }
/*     */   
/*     */   public LoggingAdapter log() {
/*  21 */     return ActorLogging.class.log(this);
/*     */   }
/*     */   
/*     */   public ActorContext context() {
/*  21 */     return this.context;
/*     */   }
/*     */   
/*     */   public final ActorRef self() {
/*  21 */     return this.self;
/*     */   }
/*     */   
/*     */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*  21 */     this.context = x$1;
/*     */   }
/*     */   
/*     */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*  21 */     this.self = x$1;
/*     */   }
/*     */   
/*     */   public final ActorRef sender() {
/*  21 */     return Actor.class.sender(this);
/*     */   }
/*     */   
/*     */   public void aroundReceive(PartialFunction receive, Object msg) {
/*  21 */     Actor.class.aroundReceive(this, receive, msg);
/*     */   }
/*     */   
/*     */   public void aroundPreStart() {
/*  21 */     Actor.class.aroundPreStart(this);
/*     */   }
/*     */   
/*     */   public void aroundPostStop() {
/*  21 */     Actor.class.aroundPostStop(this);
/*     */   }
/*     */   
/*     */   public void aroundPreRestart(Throwable reason, Option message) {
/*  21 */     Actor.class.aroundPreRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void aroundPostRestart(Throwable reason) {
/*  21 */     Actor.class.aroundPostRestart(this, reason);
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/*  21 */     return Actor.class.supervisorStrategy(this);
/*     */   }
/*     */   
/*     */   public void preStart() throws Exception {
/*  21 */     Actor.class.preStart(this);
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable reason, Option message) throws Exception {
/*  21 */     Actor.class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void postRestart(Throwable reason) throws Exception {
/*  21 */     Actor.class.postRestart(this, reason);
/*     */   }
/*     */   
/*     */   public void unhandled(Object message) {
/*  21 */     Actor.class.unhandled(this, message);
/*     */   }
/*     */   
/*     */   public UdpExt udp() {
/*  21 */     return this.udp;
/*     */   }
/*     */   
/*     */   public UdpListener(UdpExt udp, ChannelRegistry channelRegistry, ActorRef bindCommander, Udp.Bind bind) {
/*  21 */     Actor.class.$init$(this);
/*  21 */     ActorLogging.class.$init$(this);
/*  21 */     WithUdpSend$class.$init$(this);
/*  32 */     context().watch(bind.handler());
/*  34 */     this.channel = DatagramChannel.open();
/*  35 */     channel().configureBlocking(false);
/*  37 */     this.localAddress = 
/*  38 */       liftedTree1$1();
/*     */   }
/*     */   
/*     */   public ActorRef selector() {
/*     */     return context().parent();
/*     */   }
/*     */   
/*     */   public DatagramChannel channel() {
/*     */     return this.channel;
/*     */   }
/*     */   
/*     */   public Object localAddress() {
/*     */     return this.localAddress;
/*     */   }
/*     */   
/*     */   private final Object liftedTree1$1() {
/*     */     try {
/*  39 */       DatagramSocket socket = channel().socket();
/*  40 */       this.akka$io$UdpListener$$bind.options().foreach((Function1)new UdpListener$$anonfun$liftedTree1$1$1(this, socket));
/*  41 */       socket.bind(this.akka$io$UdpListener$$bind.localAddress());
/*  42 */       SocketAddress socketAddress = socket.getLocalSocketAddress();
/*  43 */       if (socketAddress instanceof InetSocketAddress) {
/*  43 */         InetSocketAddress inetSocketAddress1 = (InetSocketAddress)socketAddress, inetSocketAddress2 = inetSocketAddress1;
/*     */         InetSocketAddress ret = inetSocketAddress2;
/*  46 */         this.channelRegistry.register(channel(), 1, self());
/*  47 */         log().debug("Successfully bound to [{}]", ret);
/*     */       } 
/*     */       (new String[2])[0] = "bound to unknown SocketAddress [";
/*     */       (new String[2])[1] = "]";
/*     */       throw new IllegalArgumentException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { socketAddress })));
/*     */     } finally {
/*     */       BoxedUnit boxedUnit;
/*     */       Exception exception1 = null, exception2 = exception1;
/*  50 */       Option option = NonFatal$.MODULE$.unapply(exception2);
/*  50 */       if (option.isEmpty())
/*     */         throw exception1; 
/*  50 */       Throwable e = (Throwable)option.get();
/*  51 */       package$.MODULE$.actorRef2Scala(this.akka$io$UdpListener$$bindCommander).$bang(new Udp.CommandFailed(this.akka$io$UdpListener$$bind), self());
/*  52 */       log().debug("Failed to bind UDP channel to endpoint [{}]: {}", this.akka$io$UdpListener$$bind.localAddress(), e);
/*  53 */       context().stop(self());
/*     */     } 
/*     */     return boxedUnit;
/*     */   }
/*     */   
/*     */   public class UdpListener$$anonfun$liftedTree1$1$1 extends AbstractFunction1<Inet.SocketOption, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final DatagramSocket socket$1;
/*     */     
/*     */     public final void apply(Inet.SocketOption x$1) {
/*     */       x$1.beforeDatagramBind(this.socket$1);
/*     */     }
/*     */     
/*     */     public UdpListener$$anonfun$liftedTree1$1$1(UdpListener $outer, DatagramSocket socket$1) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> receive() {
/*  56 */     return (PartialFunction<Object, BoxedUnit>)new UdpListener$$anonfun$receive$1(this);
/*     */   }
/*     */   
/*     */   public class UdpListener$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*  56 */       Object object2, object1 = x1;
/*  57 */       if (object1 instanceof ChannelRegistration) {
/*  57 */         ChannelRegistration channelRegistration = (ChannelRegistration)object1;
/*  58 */         package$.MODULE$.actorRef2Scala(this.$outer.akka$io$UdpListener$$bindCommander).$bang(new Udp.Bound((InetSocketAddress)this.$outer.channel().socket().getLocalSocketAddress()), this.$outer.self());
/*  59 */         this.$outer.context().become(this.$outer.readHandlers(channelRegistration).orElse(this.$outer.sendHandlers(channelRegistration)), true);
/*  59 */         object2 = BoxedUnit.UNIT;
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
/*     */     public UdpListener$$anonfun$receive$1(UdpListener $outer) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> readHandlers(ChannelRegistration registration) {
/*  62 */     return (PartialFunction<Object, BoxedUnit>)new UdpListener$$anonfun$readHandlers$1(this, registration);
/*     */   }
/*     */   
/*     */   public class UdpListener$$anonfun$readHandlers$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ChannelRegistration registration$1;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/io/Udp$SuspendReading$.MODULE$ : Lakka/io/Udp$SuspendReading$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 47
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 47
/*     */       //   29: aload_0
/*     */       //   30: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   33: iconst_1
/*     */       //   34: invokeinterface disableInterest : (I)V
/*     */       //   39: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   42: astore #5
/*     */       //   44: goto -> 297
/*     */       //   47: getstatic akka/io/Udp$ResumeReading$.MODULE$ : Lakka/io/Udp$ResumeReading$;
/*     */       //   50: aload_3
/*     */       //   51: astore #6
/*     */       //   53: dup
/*     */       //   54: ifnonnull -> 66
/*     */       //   57: pop
/*     */       //   58: aload #6
/*     */       //   60: ifnull -> 74
/*     */       //   63: goto -> 92
/*     */       //   66: aload #6
/*     */       //   68: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   71: ifeq -> 92
/*     */       //   74: aload_0
/*     */       //   75: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   78: iconst_1
/*     */       //   79: invokeinterface enableInterest : (I)V
/*     */       //   84: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   87: astore #5
/*     */       //   89: goto -> 297
/*     */       //   92: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   95: aload_3
/*     */       //   96: astore #7
/*     */       //   98: dup
/*     */       //   99: ifnonnull -> 111
/*     */       //   102: pop
/*     */       //   103: aload #7
/*     */       //   105: ifnull -> 119
/*     */       //   108: goto -> 148
/*     */       //   111: aload #7
/*     */       //   113: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   116: ifeq -> 148
/*     */       //   119: aload_0
/*     */       //   120: getfield $outer : Lakka/io/UdpListener;
/*     */       //   123: aload_0
/*     */       //   124: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   127: aload_0
/*     */       //   128: getfield $outer : Lakka/io/UdpListener;
/*     */       //   131: getfield akka$io$UdpListener$$bind : Lakka/io/Udp$Bind;
/*     */       //   134: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   137: invokevirtual doReceive : (Lakka/io/ChannelRegistration;Lakka/actor/ActorRef;)V
/*     */       //   140: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   143: astore #5
/*     */       //   145: goto -> 297
/*     */       //   148: getstatic akka/io/Udp$Unbind$.MODULE$ : Lakka/io/Udp$Unbind$;
/*     */       //   151: aload_3
/*     */       //   152: astore #8
/*     */       //   154: dup
/*     */       //   155: ifnonnull -> 167
/*     */       //   158: pop
/*     */       //   159: aload #8
/*     */       //   161: ifnull -> 175
/*     */       //   164: goto -> 288
/*     */       //   167: aload #8
/*     */       //   169: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   172: ifeq -> 288
/*     */       //   175: aload_0
/*     */       //   176: getfield $outer : Lakka/io/UdpListener;
/*     */       //   179: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   182: ldc 'Unbinding endpoint [{}]'
/*     */       //   184: aload_0
/*     */       //   185: getfield $outer : Lakka/io/UdpListener;
/*     */       //   188: getfield akka$io$UdpListener$$bind : Lakka/io/Udp$Bind;
/*     */       //   191: invokevirtual localAddress : ()Ljava/net/InetSocketAddress;
/*     */       //   194: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   199: aload_0
/*     */       //   200: getfield $outer : Lakka/io/UdpListener;
/*     */       //   203: invokevirtual channel : ()Ljava/nio/channels/DatagramChannel;
/*     */       //   206: invokevirtual close : ()V
/*     */       //   209: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   212: aload_0
/*     */       //   213: getfield $outer : Lakka/io/UdpListener;
/*     */       //   216: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   219: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   222: getstatic akka/io/Udp$Unbound$.MODULE$ : Lakka/io/Udp$Unbound$;
/*     */       //   225: aload_0
/*     */       //   226: getfield $outer : Lakka/io/UdpListener;
/*     */       //   229: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   232: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   237: aload_0
/*     */       //   238: getfield $outer : Lakka/io/UdpListener;
/*     */       //   241: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   244: ldc 'Unbound endpoint [{}], stopping listener'
/*     */       //   246: aload_0
/*     */       //   247: getfield $outer : Lakka/io/UdpListener;
/*     */       //   250: getfield akka$io$UdpListener$$bind : Lakka/io/Udp$Bind;
/*     */       //   253: invokevirtual localAddress : ()Ljava/net/InetSocketAddress;
/*     */       //   256: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   261: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   264: aload_0
/*     */       //   265: getfield $outer : Lakka/io/UdpListener;
/*     */       //   268: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   271: aload_0
/*     */       //   272: getfield $outer : Lakka/io/UdpListener;
/*     */       //   275: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   278: invokeinterface stop : (Lakka/actor/ActorRef;)V
/*     */       //   283: astore #5
/*     */       //   285: goto -> 297
/*     */       //   288: aload_2
/*     */       //   289: aload_1
/*     */       //   290: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   295: astore #5
/*     */       //   297: aload #5
/*     */       //   299: areturn
/*     */       //   300: astore #9
/*     */       //   302: aload_0
/*     */       //   303: getfield $outer : Lakka/io/UdpListener;
/*     */       //   306: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   309: aload_0
/*     */       //   310: getfield $outer : Lakka/io/UdpListener;
/*     */       //   313: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   316: invokeinterface stop : (Lakka/actor/ActorRef;)V
/*     */       //   321: aload #9
/*     */       //   323: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #62	-> 0
/*     */       //   #63	-> 2
/*     */       //   #64	-> 47
/*     */       //   #65	-> 92
/*     */       //   #67	-> 148
/*     */       //   #68	-> 175
/*     */       //   #70	-> 199
/*     */       //   #71	-> 209
/*     */       //   #72	-> 237
/*     */       //   #73	-> 264
/*     */       //   #67	-> 283
/*     */       //   #62	-> 288
/*     */       //   #73	-> 300
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	324	0	this	Lakka/io/UdpListener$$anonfun$readHandlers$1;
/*     */       //   0	324	1	x2	Ljava/lang/Object;
/*     */       //   0	324	2	default	Lscala/Function1;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   199	264	300	finally
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x2) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/io/Udp$SuspendReading$.MODULE$ : Lakka/io/Udp$SuspendReading$;
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
/*     */       //   29: goto -> 134
/*     */       //   32: getstatic akka/io/Udp$ResumeReading$.MODULE$ : Lakka/io/Udp$ResumeReading$;
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
/*     */       //   62: goto -> 134
/*     */       //   65: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   68: aload_2
/*     */       //   69: astore #6
/*     */       //   71: dup
/*     */       //   72: ifnonnull -> 84
/*     */       //   75: pop
/*     */       //   76: aload #6
/*     */       //   78: ifnull -> 92
/*     */       //   81: goto -> 98
/*     */       //   84: aload #6
/*     */       //   86: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   89: ifeq -> 98
/*     */       //   92: iconst_1
/*     */       //   93: istore #4
/*     */       //   95: goto -> 134
/*     */       //   98: getstatic akka/io/Udp$Unbind$.MODULE$ : Lakka/io/Udp$Unbind$;
/*     */       //   101: aload_2
/*     */       //   102: astore #7
/*     */       //   104: dup
/*     */       //   105: ifnonnull -> 117
/*     */       //   108: pop
/*     */       //   109: aload #7
/*     */       //   111: ifnull -> 125
/*     */       //   114: goto -> 131
/*     */       //   117: aload #7
/*     */       //   119: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   122: ifeq -> 131
/*     */       //   125: iconst_1
/*     */       //   126: istore #4
/*     */       //   128: goto -> 134
/*     */       //   131: iconst_0
/*     */       //   132: istore #4
/*     */       //   134: iload #4
/*     */       //   136: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #62	-> 0
/*     */       //   #63	-> 2
/*     */       //   #64	-> 32
/*     */       //   #65	-> 65
/*     */       //   #67	-> 98
/*     */       //   #62	-> 131
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	137	0	this	Lakka/io/UdpListener$$anonfun$readHandlers$1;
/*     */       //   0	137	1	x2	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public UdpListener$$anonfun$readHandlers$1(UdpListener $outer, ChannelRegistration registration$1) {}
/*     */   }
/*     */   
/*     */   private final void innerReceive$1(int readsLeft, ByteBuffer buffer, ActorRef handler$1) {
/*     */     SocketAddress socketAddress;
/*     */     while (true) {
/*  78 */       buffer.clear();
/*  79 */       buffer.limit(udp().settings().DirectBufferSize());
/*  81 */       socketAddress = channel().receive(buffer);
/*  82 */       if (socketAddress instanceof InetSocketAddress) {
/*  82 */         InetSocketAddress inetSocketAddress = (InetSocketAddress)socketAddress;
/*  83 */         buffer.flip();
/*  84 */         package$.MODULE$.actorRef2Scala(handler$1).$bang(new Udp.Received(ByteString$.MODULE$.apply(buffer), inetSocketAddress), self());
/*  85 */         if (readsLeft > 0) {
/*  85 */           buffer = buffer;
/*  85 */           readsLeft--;
/*     */           continue;
/*     */         } 
/*  85 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } else {
/*     */         break;
/*     */       } 
/*     */       return;
/*     */     } 
/*  86 */     if (socketAddress == null) {
/*  86 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/*     */       throw new MatchError(socketAddress);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doReceive(ChannelRegistration registration, ActorRef handler) {
/*  90 */     ByteBuffer buffer = udp().bufferPool().acquire();
/*     */     try {
/*  91 */       innerReceive$1(udp().settings().BatchReceiveLimit(), buffer, handler);
/*     */       return;
/*     */     } finally {
/*  92 */       udp().bufferPool().release(buffer);
/*  93 */       registration.enableInterest(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void postStop() {
/*  98 */     if (channel().isOpen()) {
/*  99 */       log().debug("Closing DatagramChannel after being stopped");
/*     */       try {
/* 100 */         channel().close();
/*     */       } finally {
/*     */         BoxedUnit boxedUnit;
/* 100 */         Exception exception1 = null, exception2 = exception1;
/* 102 */         Option option = NonFatal$.MODULE$.unapply(exception2);
/* 102 */         if (option.isEmpty())
/*     */           throw exception1; 
/* 102 */         Throwable e = (Throwable)option.get();
/* 102 */         log().debug("Error closing DatagramChannel: {}", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */