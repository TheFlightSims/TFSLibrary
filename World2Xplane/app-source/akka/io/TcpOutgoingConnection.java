/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import java.net.Socket;
/*    */ import java.net.SocketAddress;
/*    */ import java.nio.channels.SocketChannel;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.duration.Duration;
/*    */ import scala.concurrent.duration.Duration$;
/*    */ import scala.concurrent.duration.package;
/*    */ import scala.concurrent.duration.package$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.util.control.NonFatal$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001}3Q!\001\002\001\005\031\021Q\003V2q\037V$xm\\5oO\016{gN\\3di&|gN\003\002\004\t\005\021\021n\034\006\002\013\005!\021m[6b'\t\001q\001\005\002\t\0235\t!!\003\002\013\005\tiAk\0319D_:tWm\031;j_:D\021\002\004\001\003\002\003\006IAD\t\002\t}#8\r]\002\001!\tAq\"\003\002\021\005\t1Ak\0319FqRL!AE\005\002\007Q\034\007\017\003\005\025\001\t\005\t\025!\003\026\003=\031\007.\0318oK2\024VmZ5tiJL\bC\001\005\027\023\t9\"AA\bDQ\006tg.\0327SK\036L7\017\036:z\021!I\002A!A!\002\023Q\022!C2p[6\fg\016Z3s!\tYb$D\001\035\025\tiB!A\003bGR|'/\003\002 9\tA\021i\031;peJ+g\r\003\005\"\001\t\005\t\025!\003#\003\035\031wN\0348fGR\004\"aI\027\017\005\021ZcBA\023+\035\t1\023&D\001(\025\tAS\"\001\004=e>|GOP\005\002\013%\0211\001B\005\003Y\t\t1\001V2q\023\tqsFA\004D_:tWm\031;\013\0051\022\001\"B\031\001\t\003\021\024A\002\037j]&$h\bF\0034iU2t\007\005\002\t\001!)A\002\ra\001\035!)A\003\ra\001+!)\021\004\ra\0015!)\021\005\ra\001E!)\021\b\001C\005u\005!1\017^8q)\005Y\004C\001\037@\033\005i$\"\001 \002\013M\034\027\r\\1\n\005\001k$\001B+oSRDQA\021\001\005\n\r\013AC]3q_J$8i\0348oK\016$h)Y5mkJ,GCA\036E\021\031)\025\t\"a\001\r\006)A\017[;oWB\031AhR\036\n\005!k$\001\003\037cs:\fW.\032 \t\013)\003A\021A&\002\017I,7-Z5wKV\tA\n\005\002N\0356\t\001!\003\002P!\n9!+Z2fSZ,\027BA)\035\005\025\t5\r^8s\021\025\031\006\001\"\001U\003)\031wN\0348fGRLgn\032\013\004\031VS\006\"\002,S\001\0049\026\001\004:fO&\034HO]1uS>t\007C\001\005Y\023\tI&AA\nDQ\006tg.\0327SK\036L7\017\036:bi&|g\016C\003\\%\002\007A,A\017sK6\f\027N\\5oO\032Kg.[:i\007>tg.Z2u%\026$(/[3t!\taT,\003\002_{\t\031\021J\034;")
/*    */ public class TcpOutgoingConnection extends TcpConnection {
/*    */   public final ChannelRegistry akka$io$TcpOutgoingConnection$$channelRegistry;
/*    */   
/*    */   public final ActorRef akka$io$TcpOutgoingConnection$$commander;
/*    */   
/*    */   public final Tcp.Connect akka$io$TcpOutgoingConnection$$connect;
/*    */   
/*    */   public TcpOutgoingConnection(TcpExt _tcp, ChannelRegistry channelRegistry, ActorRef commander, Tcp.Connect connect) {
/* 23 */     super(
/*    */         
/* 27 */         _tcp, (SocketChannel)SocketChannel.open().configureBlocking(false), connect.pullMode());
/* 31 */     context().watch(commander);
/* 33 */     connect.options().foreach((Function1)new TcpOutgoingConnection$$anonfun$1(this));
/* 34 */     Socket socket = channel().socket();
/* 34 */     connect.localAddress().foreach((Function1)new TcpOutgoingConnection$$anonfun$2(this, socket));
/* 35 */     channelRegistry.register(channel(), 0, self());
/* 36 */     connect.timeout().foreach((Function1)new TcpOutgoingConnection$$anonfun$3(this));
/*    */   }
/*    */   
/*    */   public class TcpOutgoingConnection$$anonfun$1 extends AbstractFunction1<Inet.SocketOption, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(Inet.SocketOption x$1) {
/*    */       x$1.beforeConnect(this.$outer.channel().socket());
/*    */     }
/*    */     
/*    */     public TcpOutgoingConnection$$anonfun$1(TcpOutgoingConnection $outer) {}
/*    */   }
/*    */   
/*    */   public class TcpOutgoingConnection$$anonfun$2 extends AbstractFunction1<SocketAddress, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Socket eta$0$1$1;
/*    */     
/*    */     public final void apply(SocketAddress x$1) {
/*    */       this.eta$0$1$1.bind(x$1);
/*    */     }
/*    */     
/*    */     public TcpOutgoingConnection$$anonfun$2(TcpOutgoingConnection $outer, Socket eta$0$1$1) {}
/*    */   }
/*    */   
/*    */   public class TcpOutgoingConnection$$anonfun$3 extends AbstractFunction1<Duration, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(Duration timeout) {
/* 36 */       this.$outer.context().setReceiveTimeout(timeout);
/*    */     }
/*    */     
/*    */     public TcpOutgoingConnection$$anonfun$3(TcpOutgoingConnection $outer) {}
/*    */   }
/*    */   
/*    */   public void akka$io$TcpOutgoingConnection$$stop() {
/* 38 */     (new ActorRef[1])[0] = this.akka$io$TcpOutgoingConnection$$commander;
/* 38 */     stopWith(new TcpConnection.CloseInformation((Set<ActorRef>)Predef$.MODULE$.Set().apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new ActorRef[1])), this.akka$io$TcpOutgoingConnection$$connect.failureMessage()));
/*    */   }
/*    */   
/*    */   public void akka$io$TcpOutgoingConnection$$reportConnectFailure(Function0 thunk) {
/*    */     try {
/* 42 */       thunk.apply$mcV$sp();
/*    */     } finally {
/*    */       BoxedUnit boxedUnit;
/*    */       Exception exception1 = null, exception2 = exception1;
/* 44 */       Option option = NonFatal$.MODULE$.unapply(exception2);
/* 44 */       if (option.isEmpty())
/*    */         throw exception1; 
/* 44 */       Throwable e = (Throwable)option.get();
/* 45 */       log().debug("Could not establish connection to [{}] due to {}", this.akka$io$TcpOutgoingConnection$$connect.remoteAddress(), e);
/* 46 */       akka$io$TcpOutgoingConnection$$stop();
/*    */     } 
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> receive() {
/* 50 */     return (PartialFunction<Object, BoxedUnit>)new TcpOutgoingConnection$$anonfun$receive$1(this);
/*    */   }
/*    */   
/*    */   public class TcpOutgoingConnection$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/* 50 */       Object object2, object1 = x1;
/* 51 */       if (object1 instanceof ChannelRegistration) {
/* 51 */         ChannelRegistration channelRegistration = (ChannelRegistration)object1;
/* 52 */         this.$outer.log().debug("Attempting connection to [{}]", this.$outer.akka$io$TcpOutgoingConnection$$connect.remoteAddress());
/* 53 */         this.$outer.akka$io$TcpOutgoingConnection$$reportConnectFailure(
/* 54 */             (Function0<BoxedUnit>)new TcpOutgoingConnection$$anonfun$receive$1$$anonfun$applyOrElse$1(this, channelRegistration));
/*    */         object2 = BoxedUnit.UNIT;
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
/*    */     public TcpOutgoingConnection$$anonfun$receive$1(TcpOutgoingConnection $outer) {}
/*    */     
/*    */     public class TcpOutgoingConnection$$anonfun$receive$1$$anonfun$applyOrElse$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final ChannelRegistration x2$1;
/*    */       
/*    */       public final void apply() {
/* 54 */         apply$mcV$sp();
/*    */       }
/*    */       
/*    */       public void apply$mcV$sp() {
/* 54 */         if (this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().channel().connect((this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer()).akka$io$TcpOutgoingConnection$$connect.remoteAddress())) {
/* 55 */           this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().completeConnect(this.x2$1, (this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer()).akka$io$TcpOutgoingConnection$$commander, (this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer()).akka$io$TcpOutgoingConnection$$connect.options());
/*    */         } else {
/* 57 */           this.x2$1.enableInterest(8);
/* 58 */           this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().context().become(this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().connecting(this.x2$1, this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().tcp().Settings().FinishConnectRetries()));
/*    */         } 
/*    */       }
/*    */       
/*    */       public TcpOutgoingConnection$$anonfun$receive$1$$anonfun$applyOrElse$1(TcpOutgoingConnection$$anonfun$receive$1 $outer, ChannelRegistration x2$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> connecting(ChannelRegistration registration, int remainingFinishConnectRetries) {
/* 64 */     return (PartialFunction<Object, BoxedUnit>)new TcpOutgoingConnection$$anonfun$connecting$1(this, registration, remainingFinishConnectRetries);
/*    */   }
/*    */   
/*    */   public class TcpOutgoingConnection$$anonfun$connecting$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final ChannelRegistration registration$1;
/*    */     
/*    */     public final int remainingFinishConnectRetries$1;
/*    */     
/*    */     public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/*    */       // Byte code:
/*    */       //   0: aload_1
/*    */       //   1: astore_3
/*    */       //   2: getstatic akka/io/SelectionHandler$ChannelConnectable$.MODULE$ : Lakka/io/SelectionHandler$ChannelConnectable$;
/*    */       //   5: aload_3
/*    */       //   6: astore #4
/*    */       //   8: dup
/*    */       //   9: ifnonnull -> 21
/*    */       //   12: pop
/*    */       //   13: aload #4
/*    */       //   15: ifnull -> 29
/*    */       //   18: goto -> 52
/*    */       //   21: aload #4
/*    */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   26: ifeq -> 52
/*    */       //   29: aload_0
/*    */       //   30: getfield $outer : Lakka/io/TcpOutgoingConnection;
/*    */       //   33: new akka/io/TcpOutgoingConnection$$anonfun$connecting$1$$anonfun$applyOrElse$2
/*    */       //   36: dup
/*    */       //   37: aload_0
/*    */       //   38: invokespecial <init> : (Lakka/io/TcpOutgoingConnection$$anonfun$connecting$1;)V
/*    */       //   41: invokevirtual akka$io$TcpOutgoingConnection$$reportConnectFailure : (Lscala/Function0;)V
/*    */       //   44: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */       //   47: astore #5
/*    */       //   49: goto -> 161
/*    */       //   52: getstatic akka/actor/ReceiveTimeout$.MODULE$ : Lakka/actor/ReceiveTimeout$;
/*    */       //   55: aload_3
/*    */       //   56: astore #6
/*    */       //   58: dup
/*    */       //   59: ifnonnull -> 71
/*    */       //   62: pop
/*    */       //   63: aload #6
/*    */       //   65: ifnull -> 79
/*    */       //   68: goto -> 152
/*    */       //   71: aload #6
/*    */       //   73: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   76: ifeq -> 152
/*    */       //   79: aload_0
/*    */       //   80: getfield $outer : Lakka/io/TcpOutgoingConnection;
/*    */       //   83: getfield akka$io$TcpOutgoingConnection$$connect : Lakka/io/Tcp$Connect;
/*    */       //   86: invokevirtual timeout : ()Lscala/Option;
/*    */       //   89: invokevirtual isDefined : ()Z
/*    */       //   92: ifeq -> 113
/*    */       //   95: aload_0
/*    */       //   96: getfield $outer : Lakka/io/TcpOutgoingConnection;
/*    */       //   99: invokevirtual context : ()Lakka/actor/ActorContext;
/*    */       //   102: getstatic scala/concurrent/duration/Duration$.MODULE$ : Lscala/concurrent/duration/Duration$;
/*    */       //   105: invokevirtual Undefined : ()Lscala/concurrent/duration/Duration$Infinite;
/*    */       //   108: invokeinterface setReceiveTimeout : (Lscala/concurrent/duration/Duration;)V
/*    */       //   113: aload_0
/*    */       //   114: getfield $outer : Lakka/io/TcpOutgoingConnection;
/*    */       //   117: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*    */       //   120: ldc 'Connect timeout expired, could not establish connection to [{}]'
/*    */       //   122: aload_0
/*    */       //   123: getfield $outer : Lakka/io/TcpOutgoingConnection;
/*    */       //   126: getfield akka$io$TcpOutgoingConnection$$connect : Lakka/io/Tcp$Connect;
/*    */       //   129: invokevirtual remoteAddress : ()Ljava/net/InetSocketAddress;
/*    */       //   132: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*    */       //   137: aload_0
/*    */       //   138: getfield $outer : Lakka/io/TcpOutgoingConnection;
/*    */       //   141: invokevirtual akka$io$TcpOutgoingConnection$$stop : ()V
/*    */       //   144: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */       //   147: astore #5
/*    */       //   149: goto -> 161
/*    */       //   152: aload_2
/*    */       //   153: aload_1
/*    */       //   154: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */       //   159: astore #5
/*    */       //   161: aload #5
/*    */       //   163: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #64	-> 0
/*    */       //   #65	-> 2
/*    */       //   #66	-> 29
/*    */       //   #67	-> 33
/*    */       //   #66	-> 41
/*    */       //   #85	-> 52
/*    */       //   #86	-> 79
/*    */       //   #87	-> 113
/*    */       //   #88	-> 137
/*    */       //   #85	-> 147
/*    */       //   #64	-> 152
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	164	0	this	Lakka/io/TcpOutgoingConnection$$anonfun$connecting$1;
/*    */       //   0	164	1	x2	Ljava/lang/Object;
/*    */       //   0	164	2	default	Lscala/Function1;
/*    */     }
/*    */     
/*    */     public final boolean isDefinedAt(Object x2) {
/*    */       // Byte code:
/*    */       //   0: aload_1
/*    */       //   1: astore_2
/*    */       //   2: getstatic akka/io/SelectionHandler$ChannelConnectable$.MODULE$ : Lakka/io/SelectionHandler$ChannelConnectable$;
/*    */       //   5: aload_2
/*    */       //   6: astore_3
/*    */       //   7: dup
/*    */       //   8: ifnonnull -> 19
/*    */       //   11: pop
/*    */       //   12: aload_3
/*    */       //   13: ifnull -> 26
/*    */       //   16: goto -> 32
/*    */       //   19: aload_3
/*    */       //   20: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   23: ifeq -> 32
/*    */       //   26: iconst_1
/*    */       //   27: istore #4
/*    */       //   29: goto -> 68
/*    */       //   32: getstatic akka/actor/ReceiveTimeout$.MODULE$ : Lakka/actor/ReceiveTimeout$;
/*    */       //   35: aload_2
/*    */       //   36: astore #5
/*    */       //   38: dup
/*    */       //   39: ifnonnull -> 51
/*    */       //   42: pop
/*    */       //   43: aload #5
/*    */       //   45: ifnull -> 59
/*    */       //   48: goto -> 65
/*    */       //   51: aload #5
/*    */       //   53: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   56: ifeq -> 65
/*    */       //   59: iconst_1
/*    */       //   60: istore #4
/*    */       //   62: goto -> 68
/*    */       //   65: iconst_0
/*    */       //   66: istore #4
/*    */       //   68: iload #4
/*    */       //   70: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #64	-> 0
/*    */       //   #65	-> 2
/*    */       //   #66	-> 26
/*    */       //   #85	-> 32
/*    */       //   #64	-> 65
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	71	0	this	Lakka/io/TcpOutgoingConnection$$anonfun$connecting$1;
/*    */       //   0	71	1	x2	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public TcpOutgoingConnection$$anonfun$connecting$1(TcpOutgoingConnection $outer, ChannelRegistration registration$1, int remainingFinishConnectRetries$1) {}
/*    */     
/*    */     public class TcpOutgoingConnection$$anonfun$connecting$1$$anonfun$applyOrElse$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final void apply() {
/* 67 */         apply$mcV$sp();
/*    */       }
/*    */       
/*    */       public void apply$mcV$sp() {
/* 67 */         if (this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().channel().finishConnect()) {
/* 68 */           if ((this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer()).akka$io$TcpOutgoingConnection$$connect.timeout().isDefined())
/* 68 */             this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().context().setReceiveTimeout((Duration)Duration$.MODULE$.Undefined()); 
/* 69 */           this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().log().debug("Connection established to [{}]", (this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer()).akka$io$TcpOutgoingConnection$$connect.remoteAddress());
/* 70 */           this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().completeConnect(this.$outer.registration$1, (this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer()).akka$io$TcpOutgoingConnection$$commander, (this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer()).akka$io$TcpOutgoingConnection$$connect.options());
/* 72 */         } else if (this.$outer.remainingFinishConnectRetries$1 > 0) {
/* 73 */           this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().context().system().scheduler()
/*    */             
/* 75 */             .scheduleOnce((new package.DurationInt(package$.MODULE$.DurationInt(1))).millisecond(), (Function0)new TcpOutgoingConnection$$anonfun$connecting$1$$anonfun$applyOrElse$2$$anonfun$apply$mcV$sp$1(this), (ExecutionContext)this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().context().dispatcher());
/* 76 */           this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().context().become(this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().connecting(this.$outer.registration$1, this.$outer.remainingFinishConnectRetries$1 - 1));
/*    */         } else {
/* 78 */           this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().log().debug("Could not establish connection because finishConnect never returned true (consider increasing akka.io.tcp.finish-connect-retries)");
/* 80 */           this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$$outer().akka$io$TcpOutgoingConnection$$stop();
/*    */         } 
/*    */       }
/*    */       
/*    */       public TcpOutgoingConnection$$anonfun$connecting$1$$anonfun$applyOrElse$2(TcpOutgoingConnection$$anonfun$connecting$1 $outer) {}
/*    */       
/*    */       public class TcpOutgoingConnection$$anonfun$connecting$1$$anonfun$applyOrElse$2$$anonfun$apply$mcV$sp$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final void apply() {
/*    */           apply$mcV$sp();
/*    */         }
/*    */         
/*    */         public void apply$mcV$sp() {
/*    */           (this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$anonfun$$$outer().akka$io$TcpOutgoingConnection$$anonfun$$$outer()).akka$io$TcpOutgoingConnection$$channelRegistry.register(this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$anonfun$$$outer().akka$io$TcpOutgoingConnection$$anonfun$$$outer().channel(), 8, this.$outer.akka$io$TcpOutgoingConnection$$anonfun$$anonfun$$$outer().akka$io$TcpOutgoingConnection$$anonfun$$$outer().self());
/*    */         }
/*    */         
/*    */         public TcpOutgoingConnection$$anonfun$connecting$1$$anonfun$applyOrElse$2$$anonfun$apply$mcV$sp$1(TcpOutgoingConnection$$anonfun$connecting$1$$anonfun$applyOrElse$2 $outer) {}
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpOutgoingConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */