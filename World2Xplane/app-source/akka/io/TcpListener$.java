/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.Props;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.nio.channels.SocketChannel;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class TcpListener$ {
/*     */   public static final TcpListener$ MODULE$;
/*     */   
/*     */   private TcpListener$() {
/*  19 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class TcpListener$$anonfun$liftedTree1$1$1 extends AbstractFunction1<Inet.SocketOption, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ServerSocket socket$1;
/*     */     
/*     */     public final void apply(Inet.SocketOption x$1) {
/*  52 */       x$1.beforeServerSocketBind(this.socket$1);
/*     */     }
/*     */     
/*     */     public TcpListener$$anonfun$liftedTree1$1$1(TcpListener $outer, ServerSocket socket$1) {}
/*     */   }
/*     */   
/*     */   public class TcpListener$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*  70 */       Object object2, object1 = x1;
/*  71 */       if (object1 instanceof ChannelRegistration) {
/*  71 */         ChannelRegistration channelRegistration = (ChannelRegistration)object1;
/*  72 */         akka.actor.package$.MODULE$.actorRef2Scala(this.$outer.akka$io$TcpListener$$bindCommander).$bang(new Tcp.Bound((InetSocketAddress)this.$outer.channel().socket().getLocalSocketAddress()), this.$outer.self());
/*  73 */         this.$outer.context().become(this.$outer.bound(channelRegistration));
/*  73 */         object2 = BoxedUnit.UNIT;
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
/*     */     public TcpListener$$anonfun$receive$1(TcpListener $outer) {}
/*     */   }
/*     */   
/*     */   public class TcpListener$$anonfun$bound$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ChannelRegistration registration$1;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/io/SelectionHandler$ChannelAcceptable$.MODULE$ : Lakka/io/SelectionHandler$ChannelAcceptable$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 90
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 90
/*     */       //   29: aload_0
/*     */       //   30: getfield $outer : Lakka/io/TcpListener;
/*     */       //   33: aload_0
/*     */       //   34: getfield $outer : Lakka/io/TcpListener;
/*     */       //   37: aload_0
/*     */       //   38: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   41: aload_0
/*     */       //   42: getfield $outer : Lakka/io/TcpListener;
/*     */       //   45: invokevirtual acceptLimit : ()I
/*     */       //   48: invokevirtual acceptAllPending : (Lakka/io/ChannelRegistration;I)I
/*     */       //   51: invokevirtual acceptLimit_$eq : (I)V
/*     */       //   54: aload_0
/*     */       //   55: getfield $outer : Lakka/io/TcpListener;
/*     */       //   58: invokevirtual acceptLimit : ()I
/*     */       //   61: iconst_0
/*     */       //   62: if_icmple -> 82
/*     */       //   65: aload_0
/*     */       //   66: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   69: bipush #16
/*     */       //   71: invokeinterface enableInterest : (I)V
/*     */       //   76: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   79: goto -> 85
/*     */       //   82: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   85: astore #5
/*     */       //   87: goto -> 392
/*     */       //   90: aload_3
/*     */       //   91: instanceof akka/io/Tcp$ResumeAccepting
/*     */       //   94: ifeq -> 138
/*     */       //   97: aload_3
/*     */       //   98: checkcast akka/io/Tcp$ResumeAccepting
/*     */       //   101: astore #6
/*     */       //   103: aload #6
/*     */       //   105: invokevirtual batchSize : ()I
/*     */       //   108: istore #7
/*     */       //   110: aload_0
/*     */       //   111: getfield $outer : Lakka/io/TcpListener;
/*     */       //   114: iload #7
/*     */       //   116: invokevirtual acceptLimit_$eq : (I)V
/*     */       //   119: aload_0
/*     */       //   120: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   123: bipush #16
/*     */       //   125: invokeinterface enableInterest : (I)V
/*     */       //   130: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   133: astore #5
/*     */       //   135: goto -> 392
/*     */       //   138: aload_3
/*     */       //   139: instanceof akka/io/TcpListener$FailedRegisterIncoming
/*     */       //   142: ifeq -> 183
/*     */       //   145: aload_3
/*     */       //   146: checkcast akka/io/TcpListener$FailedRegisterIncoming
/*     */       //   149: astore #8
/*     */       //   151: aload #8
/*     */       //   153: invokevirtual channel : ()Ljava/nio/channels/SocketChannel;
/*     */       //   156: astore #9
/*     */       //   158: aload_0
/*     */       //   159: getfield $outer : Lakka/io/TcpListener;
/*     */       //   162: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   165: ldc 'Could not register incoming connection since selector capacity limit is reached, closing connection'
/*     */       //   167: invokeinterface warning : (Ljava/lang/String;)V
/*     */       //   172: aload #9
/*     */       //   174: invokevirtual close : ()V
/*     */       //   177: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   180: goto -> 390
/*     */       //   183: getstatic akka/io/Tcp$Unbind$.MODULE$ : Lakka/io/Tcp$Unbind$;
/*     */       //   186: aload_3
/*     */       //   187: astore #15
/*     */       //   189: dup
/*     */       //   190: ifnonnull -> 202
/*     */       //   193: pop
/*     */       //   194: aload #15
/*     */       //   196: ifnull -> 210
/*     */       //   199: goto -> 317
/*     */       //   202: aload #15
/*     */       //   204: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   207: ifeq -> 317
/*     */       //   210: aload_0
/*     */       //   211: getfield $outer : Lakka/io/TcpListener;
/*     */       //   214: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   217: ldc 'Unbinding endpoint {}'
/*     */       //   219: aload_0
/*     */       //   220: getfield $outer : Lakka/io/TcpListener;
/*     */       //   223: invokevirtual localAddress : ()Ljava/lang/Object;
/*     */       //   226: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   231: aload_0
/*     */       //   232: getfield $outer : Lakka/io/TcpListener;
/*     */       //   235: invokevirtual channel : ()Ljava/nio/channels/ServerSocketChannel;
/*     */       //   238: invokevirtual close : ()V
/*     */       //   241: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   244: aload_0
/*     */       //   245: getfield $outer : Lakka/io/TcpListener;
/*     */       //   248: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   251: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   254: getstatic akka/io/Tcp$Unbound$.MODULE$ : Lakka/io/Tcp$Unbound$;
/*     */       //   257: aload_0
/*     */       //   258: getfield $outer : Lakka/io/TcpListener;
/*     */       //   261: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   264: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   269: aload_0
/*     */       //   270: getfield $outer : Lakka/io/TcpListener;
/*     */       //   273: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   276: ldc 'Unbound endpoint {}, stopping listener'
/*     */       //   278: aload_0
/*     */       //   279: getfield $outer : Lakka/io/TcpListener;
/*     */       //   282: invokevirtual localAddress : ()Ljava/lang/Object;
/*     */       //   285: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   290: aload_0
/*     */       //   291: getfield $outer : Lakka/io/TcpListener;
/*     */       //   294: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   297: aload_0
/*     */       //   298: getfield $outer : Lakka/io/TcpListener;
/*     */       //   301: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   304: invokeinterface stop : (Lakka/actor/ActorRef;)V
/*     */       //   309: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   312: astore #5
/*     */       //   314: goto -> 392
/*     */       //   317: aload_2
/*     */       //   318: aload_1
/*     */       //   319: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   324: astore #5
/*     */       //   326: goto -> 392
/*     */       //   329: astore #10
/*     */       //   331: aload #10
/*     */       //   333: astore #11
/*     */       //   335: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */       //   338: aload #11
/*     */       //   340: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */       //   343: astore #12
/*     */       //   345: aload #12
/*     */       //   347: invokevirtual isEmpty : ()Z
/*     */       //   350: ifeq -> 356
/*     */       //   353: aload #10
/*     */       //   355: athrow
/*     */       //   356: aload #12
/*     */       //   358: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   361: checkcast java/lang/Throwable
/*     */       //   364: astore #13
/*     */       //   366: aload_0
/*     */       //   367: getfield $outer : Lakka/io/TcpListener;
/*     */       //   370: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   373: ldc 'Error closing socket channel: {}'
/*     */       //   375: aload #13
/*     */       //   377: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   382: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   385: astore #14
/*     */       //   387: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   390: astore #5
/*     */       //   392: aload #5
/*     */       //   394: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #76	-> 0
/*     */       //   #77	-> 2
/*     */       //   #78	-> 29
/*     */       //   #79	-> 54
/*     */       //   #77	-> 85
/*     */       //   #81	-> 90
/*     */       //   #82	-> 110
/*     */       //   #83	-> 119
/*     */       //   #81	-> 133
/*     */       //   #85	-> 138
/*     */       //   #86	-> 158
/*     */       //   #87	-> 172
/*     */       //   #92	-> 183
/*     */       //   #93	-> 210
/*     */       //   #94	-> 231
/*     */       //   #95	-> 241
/*     */       //   #96	-> 269
/*     */       //   #97	-> 290
/*     */       //   #92	-> 312
/*     */       //   #76	-> 317
/*     */       //   #87	-> 329
/*     */       //   #89	-> 335
/*     */       //   #87	-> 353
/*     */       //   #76	-> 356
/*     */       //   #89	-> 358
/*     */       //   #87	-> 387
/*     */       //   #85	-> 390
/*     */       //   #76	-> 392
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	395	0	this	Lakka/io/TcpListener$$anonfun$bound$1;
/*     */       //   0	395	1	x2	Ljava/lang/Object;
/*     */       //   0	395	2	default	Lscala/Function1;
/*     */       //   110	285	7	batchSize	I
/*     */       //   158	237	9	socketChannel	Ljava/nio/channels/SocketChannel;
/*     */       //   366	29	13	e	Ljava/lang/Throwable;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   172	183	329	finally
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x2) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/io/SelectionHandler$ChannelAcceptable$.MODULE$ : Lakka/io/SelectionHandler$ChannelAcceptable$;
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
/*     */       //   29: goto -> 94
/*     */       //   32: aload_2
/*     */       //   33: instanceof akka/io/Tcp$ResumeAccepting
/*     */       //   36: ifeq -> 45
/*     */       //   39: iconst_1
/*     */       //   40: istore #4
/*     */       //   42: goto -> 94
/*     */       //   45: aload_2
/*     */       //   46: instanceof akka/io/TcpListener$FailedRegisterIncoming
/*     */       //   49: ifeq -> 58
/*     */       //   52: iconst_1
/*     */       //   53: istore #4
/*     */       //   55: goto -> 94
/*     */       //   58: getstatic akka/io/Tcp$Unbind$.MODULE$ : Lakka/io/Tcp$Unbind$;
/*     */       //   61: aload_2
/*     */       //   62: astore #5
/*     */       //   64: dup
/*     */       //   65: ifnonnull -> 77
/*     */       //   68: pop
/*     */       //   69: aload #5
/*     */       //   71: ifnull -> 85
/*     */       //   74: goto -> 91
/*     */       //   77: aload #5
/*     */       //   79: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   82: ifeq -> 91
/*     */       //   85: iconst_1
/*     */       //   86: istore #4
/*     */       //   88: goto -> 94
/*     */       //   91: iconst_0
/*     */       //   92: istore #4
/*     */       //   94: iload #4
/*     */       //   96: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #76	-> 0
/*     */       //   #77	-> 2
/*     */       //   #81	-> 32
/*     */       //   #85	-> 45
/*     */       //   #92	-> 58
/*     */       //   #76	-> 91
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	97	0	this	Lakka/io/TcpListener$$anonfun$bound$1;
/*     */       //   0	97	1	x2	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public TcpListener$$anonfun$bound$1(TcpListener $outer, ChannelRegistration registration$1) {}
/*     */   }
/*     */   
/*     */   public class TcpListener$$anonfun$acceptAllPending$1 extends AbstractFunction1<ChannelRegistry, Props> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final SocketChannel socketChannel$1;
/*     */     
/*     */     public final Props apply(ChannelRegistry registry) {
/* 113 */       return this.$outer.akka$io$TcpListener$$props$1(registry, this.socketChannel$1);
/*     */     }
/*     */     
/*     */     public TcpListener$$anonfun$acceptAllPending$1(TcpListener $outer, SocketChannel socketChannel$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpListener$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */