/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.Actor;
/*    */ import akka.actor.ActorContext;
/*    */ import akka.actor.ActorLogging;
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.SupervisorStrategy;
/*    */ import akka.actor.package$;
/*    */ import akka.dispatch.RequiresMessageQueue;
/*    */ import akka.dispatch.UnboundedMessageQueueSemantics;
/*    */ import akka.event.LoggingAdapter;
/*    */ import java.net.DatagramSocket;
/*    */ import java.nio.channels.DatagramChannel;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Serializable;
/*    */ import scala.collection.immutable.Traversable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.TraitSetter;
/*    */ import scala.util.control.NonFatal$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001I4Q!\001\002\001\005\031\021\021\"\0263q'\026tG-\032:\013\005\r!\021AA5p\025\005)\021\001B1lW\006\034b\001A\004\016'YQ\002C\001\005\f\033\005I!\"\001\006\002\013M\034\027\r\\1\n\0051I!AB!osJ+g\r\005\002\017#5\tqB\003\002\021\t\005)\021m\031;pe&\021!c\004\002\006\003\016$xN\035\t\003\035QI!!F\b\003\031\005\033Go\034:M_\036<\027N\\4\021\005]AR\"\001\002\n\005e\021!aC,ji\",F\r]*f]\022\0042a\007\020!\033\005a\"BA\017\005\003!!\027n\0359bi\016D\027BA\020\035\005Q\021V-];je\026\034X*Z:tC\036,\027+^3vKB\0211$I\005\003Eq\021a$\0268c_VtG-\0323NKN\034\030mZ3Rk\026,XmU3nC:$\030nY:\t\021\021\002!Q1A\005\002\031\n1!\0363q\007\001)\022a\n\t\003/!J!!\013\002\003\rU#\007/\022=u\021!Y\003A!A!\002\0239\023\001B;ea\002B\001\"\f\001\003\002\003\006IAL\001\020G\"\fgN\\3m%\026<\027n\035;ssB\021qcL\005\003a\t\021qb\0215b]:,GNU3hSN$(/\037\005\te\001\021\t\021)A\005g\005I1m\\7nC:$WM\035\t\003\035QJ!!N\b\003\021\005\033Go\034:SK\032D\001b\016\001\003\002\003\006I\001O\001\b_B$\030n\0348t!\rId\bQ\007\002u)\0211\bP\001\nS6lW\017^1cY\026T!!P\005\002\025\r|G\016\\3di&|g.\003\002@u\tYAK]1wKJ\034\030M\0317f!\t\t5J\004\002C\023:\0211\t\023\b\003\t\036k\021!\022\006\003\r\026\na\001\020:p_Rt\024\"A\003\n\005\r!\021B\001&\003\003\021Ie.\032;\n\0051k%\001D*pG.,Go\0249uS>t'B\001&\003\021\025y\005\001\"\001Q\003\031a\024N\\5u}Q)\021KU*U+B\021q\003\001\005\006I9\003\ra\n\005\006[9\003\rA\f\005\006e9\003\ra\r\005\006o9\003\r\001\017\005\b/\002\021\r\021\"\001Y\003\035\031\007.\0318oK2,\022!\027\t\0035\006l\021a\027\006\0039v\013\001b\0315b]:,Gn\035\006\003=~\0131A\\5p\025\005\001\027\001\0026bm\006L!AY.\003\037\021\013G/Y4sC6\034\005.\0318oK2Da\001\032\001!\002\023I\026\001C2iC:tW\r\034\021\t\013\031\004A\021A4\002\017I,7-Z5wKV\t\001\016\005\002jU6\t\001!\003\002l#\t9!+Z2fSZ,\007\"B7\001\t\003r\027\001\0039pgR\034Fo\0349\025\003=\004\"\001\0039\n\005EL!\001B+oSR\004")
/*    */ public class UdpSender implements Actor, ActorLogging, WithUdpSend, RequiresMessageQueue<UnboundedMessageQueueSemantics> {
/*    */   private final UdpExt udp;
/*    */   
/*    */   public final ActorRef akka$io$UdpSender$$commander;
/*    */   
/*    */   private final DatagramChannel channel;
/*    */   
/*    */   private Udp.Send akka$io$WithUdpSend$$pendingSend;
/*    */   
/*    */   private ActorRef akka$io$WithUdpSend$$pendingCommander;
/*    */   
/*    */   private boolean akka$io$WithUdpSend$$retriedSend;
/*    */   
/*    */   private final Udp.UdpSettings settings;
/*    */   
/*    */   private LoggingAdapter akka$actor$ActorLogging$$_log;
/*    */   
/*    */   private final ActorContext context;
/*    */   
/*    */   private final ActorRef self;
/*    */   
/*    */   public Udp.Send akka$io$WithUdpSend$$pendingSend() {
/* 17 */     return this.akka$io$WithUdpSend$$pendingSend;
/*    */   }
/*    */   
/*    */   public void akka$io$WithUdpSend$$pendingSend_$eq(Udp.Send x$1) {
/* 17 */     this.akka$io$WithUdpSend$$pendingSend = x$1;
/*    */   }
/*    */   
/*    */   public ActorRef akka$io$WithUdpSend$$pendingCommander() {
/* 17 */     return this.akka$io$WithUdpSend$$pendingCommander;
/*    */   }
/*    */   
/*    */   public void akka$io$WithUdpSend$$pendingCommander_$eq(ActorRef x$1) {
/* 17 */     this.akka$io$WithUdpSend$$pendingCommander = x$1;
/*    */   }
/*    */   
/*    */   public boolean akka$io$WithUdpSend$$retriedSend() {
/* 17 */     return this.akka$io$WithUdpSend$$retriedSend;
/*    */   }
/*    */   
/*    */   public void akka$io$WithUdpSend$$retriedSend_$eq(boolean x$1) {
/* 17 */     this.akka$io$WithUdpSend$$retriedSend = x$1;
/*    */   }
/*    */   
/*    */   public Udp.UdpSettings settings() {
/* 17 */     return this.settings;
/*    */   }
/*    */   
/*    */   public void akka$io$WithUdpSend$_setter_$settings_$eq(Udp.UdpSettings x$1) {
/* 17 */     this.settings = x$1;
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> sendHandlers(ChannelRegistration registration) {
/* 17 */     return WithUdpSend$class.sendHandlers(this, registration);
/*    */   }
/*    */   
/*    */   public LoggingAdapter akka$actor$ActorLogging$$_log() {
/* 17 */     return this.akka$actor$ActorLogging$$_log;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/* 17 */     this.akka$actor$ActorLogging$$_log = x$1;
/*    */   }
/*    */   
/*    */   public LoggingAdapter log() {
/* 17 */     return ActorLogging.class.log(this);
/*    */   }
/*    */   
/*    */   public ActorContext context() {
/* 17 */     return this.context;
/*    */   }
/*    */   
/*    */   public final ActorRef self() {
/* 17 */     return this.self;
/*    */   }
/*    */   
/*    */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/* 17 */     this.context = x$1;
/*    */   }
/*    */   
/*    */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/* 17 */     this.self = x$1;
/*    */   }
/*    */   
/*    */   public final ActorRef sender() {
/* 17 */     return Actor.class.sender(this);
/*    */   }
/*    */   
/*    */   public void aroundReceive(PartialFunction receive, Object msg) {
/* 17 */     Actor.class.aroundReceive(this, receive, msg);
/*    */   }
/*    */   
/*    */   public void aroundPreStart() {
/* 17 */     Actor.class.aroundPreStart(this);
/*    */   }
/*    */   
/*    */   public void aroundPostStop() {
/* 17 */     Actor.class.aroundPostStop(this);
/*    */   }
/*    */   
/*    */   public void aroundPreRestart(Throwable reason, Option message) {
/* 17 */     Actor.class.aroundPreRestart(this, reason, message);
/*    */   }
/*    */   
/*    */   public void aroundPostRestart(Throwable reason) {
/* 17 */     Actor.class.aroundPostRestart(this, reason);
/*    */   }
/*    */   
/*    */   public SupervisorStrategy supervisorStrategy() {
/* 17 */     return Actor.class.supervisorStrategy(this);
/*    */   }
/*    */   
/*    */   public void preStart() throws Exception {
/* 17 */     Actor.class.preStart(this);
/*    */   }
/*    */   
/*    */   public void preRestart(Throwable reason, Option message) throws Exception {
/* 17 */     Actor.class.preRestart(this, reason, message);
/*    */   }
/*    */   
/*    */   public void postRestart(Throwable reason) throws Exception {
/* 17 */     Actor.class.postRestart(this, reason);
/*    */   }
/*    */   
/*    */   public void unhandled(Object message) {
/* 17 */     Actor.class.unhandled(this, message);
/*    */   }
/*    */   
/*    */   public UdpExt udp() {
/* 17 */     return this.udp;
/*    */   }
/*    */   
/*    */   public UdpSender(UdpExt udp, ChannelRegistry channelRegistry, ActorRef commander, Traversable options) {
/* 17 */     Actor.class.$init$(this);
/* 17 */     ActorLogging.class.$init$(this);
/* 17 */     WithUdpSend$class.$init$(this);
/* 24 */     DatagramChannel datagramChannel = DatagramChannel.open();
/* 25 */     datagramChannel.configureBlocking(false);
/* 26 */     DatagramSocket socket = datagramChannel.socket();
/* 28 */     options.foreach((Function1)new $anonfun$1(this, socket));
/* 30 */     this.channel = datagramChannel;
/* 32 */     channelRegistry.register(channel(), 0, self());
/*    */   }
/*    */   
/*    */   public DatagramChannel channel() {
/*    */     return this.channel;
/*    */   }
/*    */   
/*    */   public class $anonfun$1 extends AbstractFunction1<Inet.SocketOption, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final DatagramSocket socket$1;
/*    */     
/*    */     public final void apply(Inet.SocketOption x$1) {
/*    */       x$1.beforeDatagramBind(this.socket$1);
/*    */     }
/*    */     
/*    */     public $anonfun$1(UdpSender $outer, DatagramSocket socket$1) {}
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> receive() {
/* 34 */     return (PartialFunction<Object, BoxedUnit>)new UdpSender$$anonfun$receive$1(this);
/*    */   }
/*    */   
/*    */   public class UdpSender$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/* 34 */       Object object2, object1 = x1;
/* 35 */       if (object1 instanceof ChannelRegistration) {
/* 35 */         ChannelRegistration channelRegistration = (ChannelRegistration)object1;
/* 36 */         package$.MODULE$.actorRef2Scala(this.$outer.akka$io$UdpSender$$commander).$bang(Udp.SimpleSenderReady$.MODULE$, this.$outer.self());
/* 37 */         this.$outer.context().become(this.$outer.sendHandlers(channelRegistration));
/* 37 */         object2 = BoxedUnit.UNIT;
/*    */       } else {
/*    */         object2 = default.apply(x1);
/*    */       } 
/*    */       return (B1)object2;
/*    */     }
/*    */     
/*    */     public final boolean isDefinedAt(Object x1) {
/*    */       boolean bool;
/*    */       Object object = x1;
/*    */       if (object instanceof ChannelRegistration) {
/*    */         bool = true;
/*    */       } else {
/*    */         bool = false;
/*    */       } 
/*    */       return bool;
/*    */     }
/*    */     
/*    */     public UdpSender$$anonfun$receive$1(UdpSender $outer) {}
/*    */   }
/*    */   
/*    */   public void postStop() {
/* 40 */     if (channel().isOpen()) {
/* 41 */       log().debug("Closing DatagramChannel after being stopped");
/*    */       try {
/* 42 */         channel().close();
/*    */       } finally {
/*    */         BoxedUnit boxedUnit;
/* 42 */         Exception exception1 = null, exception2 = exception1;
/* 44 */         Option option = NonFatal$.MODULE$.unapply(exception2);
/* 44 */         if (option.isEmpty())
/*    */           throw exception1; 
/* 44 */         Throwable e = (Throwable)option.get();
/* 44 */         log().debug("Error closing DatagramChannel: {}", e);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */