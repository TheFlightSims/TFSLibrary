/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class TcpConnection$ {
/*     */   public static final TcpConnection$ MODULE$;
/*     */   
/*     */   public class TcpConnection$$anonfun$waitingForRegistration$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ChannelRegistration registration$1;
/*     */     
/*     */     private final ActorRef commander$1;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: aload_3
/*     */       //   3: instanceof akka/io/Tcp$Register
/*     */       //   6: ifeq -> 223
/*     */       //   9: aload_3
/*     */       //   10: checkcast akka/io/Tcp$Register
/*     */       //   13: astore #4
/*     */       //   15: aload #4
/*     */       //   17: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   20: astore #5
/*     */       //   22: aload #4
/*     */       //   24: invokevirtual keepOpenOnPeerClosed : ()Z
/*     */       //   27: istore #6
/*     */       //   29: aload #4
/*     */       //   31: invokevirtual useResumeWriting : ()Z
/*     */       //   34: istore #7
/*     */       //   36: aload #5
/*     */       //   38: aload_0
/*     */       //   39: getfield commander$1 : Lakka/actor/ActorRef;
/*     */       //   42: astore #9
/*     */       //   44: dup
/*     */       //   45: ifnonnull -> 57
/*     */       //   48: pop
/*     */       //   49: aload #9
/*     */       //   51: ifnull -> 65
/*     */       //   54: goto -> 71
/*     */       //   57: aload #9
/*     */       //   59: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   62: ifeq -> 71
/*     */       //   65: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   68: goto -> 102
/*     */       //   71: aload_0
/*     */       //   72: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   75: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   78: aload_0
/*     */       //   79: getfield commander$1 : Lakka/actor/ActorRef;
/*     */       //   82: invokeinterface unwatch : (Lakka/actor/ActorRef;)Lakka/actor/ActorRef;
/*     */       //   87: pop
/*     */       //   88: aload_0
/*     */       //   89: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   92: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   95: aload #5
/*     */       //   97: invokeinterface watch : (Lakka/actor/ActorRef;)Lakka/actor/ActorRef;
/*     */       //   102: pop
/*     */       //   103: aload_0
/*     */       //   104: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   107: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */       //   110: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */       //   113: invokevirtual TraceLogging : ()Z
/*     */       //   116: ifeq -> 135
/*     */       //   119: aload_0
/*     */       //   120: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   123: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   126: ldc '[{}] registered as connection handler'
/*     */       //   128: aload #5
/*     */       //   130: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   135: new akka/io/TcpConnection$ConnectionInfo
/*     */       //   138: dup
/*     */       //   139: aload_0
/*     */       //   140: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   143: aload #5
/*     */       //   145: iload #6
/*     */       //   147: iload #7
/*     */       //   149: invokespecial <init> : (Lakka/io/ChannelRegistration;Lakka/actor/ActorRef;ZZ)V
/*     */       //   152: astore #10
/*     */       //   154: aload_0
/*     */       //   155: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   158: invokevirtual pullMode : ()Z
/*     */       //   161: ifne -> 176
/*     */       //   164: aload_0
/*     */       //   165: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   168: aload #10
/*     */       //   170: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   173: invokevirtual doRead : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;)V
/*     */       //   176: aload_0
/*     */       //   177: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   180: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   183: getstatic scala/concurrent/duration/Duration$.MODULE$ : Lscala/concurrent/duration/Duration$;
/*     */       //   186: invokevirtual Undefined : ()Lscala/concurrent/duration/Duration$Infinite;
/*     */       //   189: invokeinterface setReceiveTimeout : (Lscala/concurrent/duration/Duration;)V
/*     */       //   194: aload_0
/*     */       //   195: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   198: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   201: aload_0
/*     */       //   202: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   205: aload #10
/*     */       //   207: invokevirtual connected : (Lakka/io/TcpConnection$ConnectionInfo;)Lscala/PartialFunction;
/*     */       //   210: invokeinterface become : (Lscala/PartialFunction;)V
/*     */       //   215: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   218: astore #8
/*     */       //   220: goto -> 383
/*     */       //   223: aload_3
/*     */       //   224: instanceof akka/io/Tcp$CloseCommand
/*     */       //   227: ifeq -> 293
/*     */       //   230: aload_3
/*     */       //   231: checkcast akka/io/Tcp$CloseCommand
/*     */       //   234: astore #11
/*     */       //   236: new akka/io/TcpConnection$ConnectionInfo
/*     */       //   239: dup
/*     */       //   240: aload_0
/*     */       //   241: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   244: aload_0
/*     */       //   245: getfield commander$1 : Lakka/actor/ActorRef;
/*     */       //   248: iconst_0
/*     */       //   249: iconst_0
/*     */       //   250: invokespecial <init> : (Lakka/io/ChannelRegistration;Lakka/actor/ActorRef;ZZ)V
/*     */       //   253: astore #12
/*     */       //   255: aload_0
/*     */       //   256: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   259: aload #12
/*     */       //   261: new scala/Some
/*     */       //   264: dup
/*     */       //   265: aload_0
/*     */       //   266: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   269: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   272: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   275: aload #11
/*     */       //   277: invokeinterface event : ()Lakka/io/Tcp$ConnectionClosed;
/*     */       //   282: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   285: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   288: astore #8
/*     */       //   290: goto -> 383
/*     */       //   293: getstatic akka/actor/ReceiveTimeout$.MODULE$ : Lakka/actor/ReceiveTimeout$;
/*     */       //   296: aload_3
/*     */       //   297: astore #13
/*     */       //   299: dup
/*     */       //   300: ifnonnull -> 312
/*     */       //   303: pop
/*     */       //   304: aload #13
/*     */       //   306: ifnull -> 320
/*     */       //   309: goto -> 374
/*     */       //   312: aload #13
/*     */       //   314: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   317: ifeq -> 374
/*     */       //   320: aload_0
/*     */       //   321: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   324: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   327: ldc 'Configured registration timeout of [{}] expired, stopping'
/*     */       //   329: aload_0
/*     */       //   330: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   333: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */       //   336: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */       //   339: invokevirtual RegisterTimeout : ()Lscala/concurrent/duration/Duration;
/*     */       //   342: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   347: aload_0
/*     */       //   348: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   351: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   354: aload_0
/*     */       //   355: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   358: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   361: invokeinterface stop : (Lakka/actor/ActorRef;)V
/*     */       //   366: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   369: astore #8
/*     */       //   371: goto -> 383
/*     */       //   374: aload_2
/*     */       //   375: aload_1
/*     */       //   376: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   381: astore #8
/*     */       //   383: aload #8
/*     */       //   385: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #47	-> 0
/*     */       //   #48	-> 2
/*     */       //   #51	-> 36
/*     */       //   #52	-> 71
/*     */       //   #53	-> 88
/*     */       //   #51	-> 102
/*     */       //   #55	-> 103
/*     */       //   #57	-> 135
/*     */       //   #58	-> 154
/*     */       //   #59	-> 176
/*     */       //   #60	-> 194
/*     */       //   #48	-> 218
/*     */       //   #62	-> 223
/*     */       //   #63	-> 236
/*     */       //   #64	-> 255
/*     */       //   #62	-> 288
/*     */       //   #66	-> 293
/*     */       //   #69	-> 320
/*     */       //   #70	-> 347
/*     */       //   #66	-> 369
/*     */       //   #47	-> 374
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	386	0	this	Lakka/io/TcpConnection$$anonfun$waitingForRegistration$1;
/*     */       //   0	386	1	x1	Ljava/lang/Object;
/*     */       //   0	386	2	default	Lscala/Function1;
/*     */       //   22	364	5	handler	Lakka/actor/ActorRef;
/*     */       //   29	357	6	keepOpenOnPeerClosed	Z
/*     */       //   36	350	7	useResumeWriting	Z
/*     */       //   154	64	10	info	Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   255	33	12	info	Lakka/io/TcpConnection$ConnectionInfo;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/*     */       boolean bool;
/*  47 */       Object object = x1;
/*  48 */       if (object instanceof Tcp.Register) {
/*  48 */         bool = true;
/*  62 */       } else if (object instanceof Tcp.CloseCommand) {
/*  62 */         bool = true;
/*     */       } else {
/*  66 */         Object object1 = object;
/*  66 */         if (akka.actor.ReceiveTimeout$.MODULE$ == null) {
/*  66 */           if (object1 != null)
/*     */             boolean bool1 = false; 
/*     */         } else {
/*  66 */           if (akka.actor.ReceiveTimeout$.MODULE$.equals(object1))
/*  66 */             boolean bool2 = true; 
/*     */           boolean bool1 = false;
/*     */         } 
/*  66 */         bool = true;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$waitingForRegistration$1(TcpConnection $outer, ChannelRegistration registration$1, ActorRef commander$1) {}
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$connected$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TcpConnection.ConnectionInfo info$2;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 48
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 48
/*     */       //   29: aload_0
/*     */       //   30: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   33: aload_0
/*     */       //   34: getfield info$2 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   37: invokevirtual suspendReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   40: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   43: astore #5
/*     */       //   45: goto -> 205
/*     */       //   48: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
/*     */       //   51: aload_3
/*     */       //   52: astore #6
/*     */       //   54: dup
/*     */       //   55: ifnonnull -> 67
/*     */       //   58: pop
/*     */       //   59: aload #6
/*     */       //   61: ifnull -> 75
/*     */       //   64: goto -> 94
/*     */       //   67: aload #6
/*     */       //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   72: ifeq -> 94
/*     */       //   75: aload_0
/*     */       //   76: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   79: aload_0
/*     */       //   80: getfield info$2 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   83: invokevirtual resumeReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   86: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   89: astore #5
/*     */       //   91: goto -> 205
/*     */       //   94: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   97: aload_3
/*     */       //   98: astore #7
/*     */       //   100: dup
/*     */       //   101: ifnonnull -> 113
/*     */       //   104: pop
/*     */       //   105: aload #7
/*     */       //   107: ifnull -> 121
/*     */       //   110: goto -> 143
/*     */       //   113: aload #7
/*     */       //   115: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   118: ifeq -> 143
/*     */       //   121: aload_0
/*     */       //   122: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   125: aload_0
/*     */       //   126: getfield info$2 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   129: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   132: invokevirtual doRead : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;)V
/*     */       //   135: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   138: astore #5
/*     */       //   140: goto -> 205
/*     */       //   143: aload_3
/*     */       //   144: instanceof akka/io/Tcp$CloseCommand
/*     */       //   147: ifeq -> 196
/*     */       //   150: aload_3
/*     */       //   151: checkcast akka/io/Tcp$CloseCommand
/*     */       //   154: astore #8
/*     */       //   156: aload_0
/*     */       //   157: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   160: aload_0
/*     */       //   161: getfield info$2 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   164: new scala/Some
/*     */       //   167: dup
/*     */       //   168: aload_0
/*     */       //   169: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   172: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   175: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   178: aload #8
/*     */       //   180: invokeinterface event : ()Lakka/io/Tcp$ConnectionClosed;
/*     */       //   185: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   188: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   191: astore #5
/*     */       //   193: goto -> 205
/*     */       //   196: aload_2
/*     */       //   197: aload_1
/*     */       //   198: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   203: astore #5
/*     */       //   205: aload #5
/*     */       //   207: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #75	-> 0
/*     */       //   #76	-> 2
/*     */       //   #77	-> 48
/*     */       //   #78	-> 94
/*     */       //   #79	-> 143
/*     */       //   #75	-> 196
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	208	0	this	Lakka/io/TcpConnection$$anonfun$connected$1;
/*     */       //   0	208	1	x2	Ljava/lang/Object;
/*     */       //   0	208	2	default	Lscala/Function1;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x2) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
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
/*     */       //   29: goto -> 114
/*     */       //   32: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
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
/*     */       //   62: goto -> 114
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
/*     */       //   95: goto -> 114
/*     */       //   98: aload_2
/*     */       //   99: instanceof akka/io/Tcp$CloseCommand
/*     */       //   102: ifeq -> 111
/*     */       //   105: iconst_1
/*     */       //   106: istore #4
/*     */       //   108: goto -> 114
/*     */       //   111: iconst_0
/*     */       //   112: istore #4
/*     */       //   114: iload #4
/*     */       //   116: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #75	-> 0
/*     */       //   #76	-> 2
/*     */       //   #77	-> 32
/*     */       //   #78	-> 65
/*     */       //   #79	-> 98
/*     */       //   #75	-> 111
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	117	0	this	Lakka/io/TcpConnection$$anonfun$connected$1;
/*     */       //   0	117	1	x2	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$connected$1(TcpConnection $outer, TcpConnection.ConnectionInfo info$2) {}
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$peerSentEOF$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TcpConnection.ConnectionInfo info$4;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x3, Function1 default) {
/*  84 */       Object object2, object1 = x3;
/*  85 */       if (object1 instanceof Tcp.CloseCommand) {
/*  85 */         Tcp.CloseCommand closeCommand = (Tcp.CloseCommand)object1;
/*  85 */         this.$outer.handleClose(this.info$4, (Option<ActorRef>)new Some(this.$outer.sender()), closeCommand.event());
/*  85 */         object2 = BoxedUnit.UNIT;
/*     */       } else {
/*     */         object2 = default.apply(x3);
/*     */       } 
/*     */       return (B1)object2;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x3) {
/*     */       boolean bool;
/*     */       Object object = x3;
/*  85 */       if (object instanceof Tcp.CloseCommand) {
/*  85 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$peerSentEOF$1(TcpConnection $outer, TcpConnection.ConnectionInfo info$4) {}
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$closingWithPendingWrite$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TcpConnection.ConnectionInfo info$5;
/*     */     
/*     */     private final Option closeCommander$1;
/*     */     
/*     */     private final Tcp.ConnectionClosed closedEvent$1;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x4, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 48
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 48
/*     */       //   29: aload_0
/*     */       //   30: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   33: aload_0
/*     */       //   34: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   37: invokevirtual suspendReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   40: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   43: astore #5
/*     */       //   45: goto -> 426
/*     */       //   48: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
/*     */       //   51: aload_3
/*     */       //   52: astore #6
/*     */       //   54: dup
/*     */       //   55: ifnonnull -> 67
/*     */       //   58: pop
/*     */       //   59: aload #6
/*     */       //   61: ifnull -> 75
/*     */       //   64: goto -> 94
/*     */       //   67: aload #6
/*     */       //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   72: ifeq -> 94
/*     */       //   75: aload_0
/*     */       //   76: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   79: aload_0
/*     */       //   80: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   83: invokevirtual resumeReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   86: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   89: astore #5
/*     */       //   91: goto -> 426
/*     */       //   94: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   97: aload_3
/*     */       //   98: astore #7
/*     */       //   100: dup
/*     */       //   101: ifnonnull -> 113
/*     */       //   104: pop
/*     */       //   105: aload #7
/*     */       //   107: ifnull -> 121
/*     */       //   110: goto -> 144
/*     */       //   113: aload #7
/*     */       //   115: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   118: ifeq -> 144
/*     */       //   121: aload_0
/*     */       //   122: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   125: aload_0
/*     */       //   126: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   129: aload_0
/*     */       //   130: getfield closeCommander$1 : Lscala/Option;
/*     */       //   133: invokevirtual doRead : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;)V
/*     */       //   136: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   139: astore #5
/*     */       //   141: goto -> 426
/*     */       //   144: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
/*     */       //   147: aload_3
/*     */       //   148: astore #8
/*     */       //   150: dup
/*     */       //   151: ifnonnull -> 163
/*     */       //   154: pop
/*     */       //   155: aload #8
/*     */       //   157: ifnull -> 171
/*     */       //   160: goto -> 225
/*     */       //   163: aload #8
/*     */       //   165: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   168: ifeq -> 225
/*     */       //   171: aload_0
/*     */       //   172: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   175: aload_0
/*     */       //   176: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   179: invokevirtual doWrite : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   182: aload_0
/*     */       //   183: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   186: invokevirtual writePending : ()Z
/*     */       //   189: ifeq -> 198
/*     */       //   192: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   195: goto -> 220
/*     */       //   198: aload_0
/*     */       //   199: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   202: aload_0
/*     */       //   203: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   206: aload_0
/*     */       //   207: getfield closeCommander$1 : Lscala/Option;
/*     */       //   210: aload_0
/*     */       //   211: getfield closedEvent$1 : Lakka/io/Tcp$ConnectionClosed;
/*     */       //   214: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   217: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   220: astore #5
/*     */       //   222: goto -> 426
/*     */       //   225: aload_3
/*     */       //   226: instanceof akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   229: ifeq -> 310
/*     */       //   232: aload_3
/*     */       //   233: checkcast akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   236: astore #9
/*     */       //   238: aload #9
/*     */       //   240: invokevirtual remainingWrite : ()Lakka/io/TcpConnection$PendingWrite;
/*     */       //   243: astore #10
/*     */       //   245: aload_0
/*     */       //   246: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   249: aload #10
/*     */       //   251: putfield akka$io$TcpConnection$$pendingWrite : Lakka/io/TcpConnection$PendingWrite;
/*     */       //   254: aload_0
/*     */       //   255: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   258: invokevirtual writePending : ()Z
/*     */       //   261: ifeq -> 283
/*     */       //   264: aload_0
/*     */       //   265: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   268: invokevirtual registration : ()Lakka/io/ChannelRegistration;
/*     */       //   271: iconst_4
/*     */       //   272: invokeinterface enableInterest : (I)V
/*     */       //   277: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   280: goto -> 305
/*     */       //   283: aload_0
/*     */       //   284: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   287: aload_0
/*     */       //   288: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   291: aload_0
/*     */       //   292: getfield closeCommander$1 : Lscala/Option;
/*     */       //   295: aload_0
/*     */       //   296: getfield closedEvent$1 : Lakka/io/Tcp$ConnectionClosed;
/*     */       //   299: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   302: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   305: astore #5
/*     */       //   307: goto -> 426
/*     */       //   310: aload_3
/*     */       //   311: instanceof akka/io/TcpConnection$WriteFileFailed
/*     */       //   314: ifeq -> 354
/*     */       //   317: aload_3
/*     */       //   318: checkcast akka/io/TcpConnection$WriteFileFailed
/*     */       //   321: astore #11
/*     */       //   323: aload #11
/*     */       //   325: invokevirtual e : ()Ljava/io/IOException;
/*     */       //   328: astore #12
/*     */       //   330: aload_0
/*     */       //   331: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   334: aload_0
/*     */       //   335: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   338: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   341: aload #12
/*     */       //   343: invokevirtual handleError : (Lakka/actor/ActorRef;Ljava/io/IOException;)V
/*     */       //   346: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   349: astore #5
/*     */       //   351: goto -> 426
/*     */       //   354: getstatic akka/io/Tcp$Abort$.MODULE$ : Lakka/io/Tcp$Abort$;
/*     */       //   357: aload_3
/*     */       //   358: astore #13
/*     */       //   360: dup
/*     */       //   361: ifnonnull -> 373
/*     */       //   364: pop
/*     */       //   365: aload #13
/*     */       //   367: ifnull -> 381
/*     */       //   370: goto -> 417
/*     */       //   373: aload #13
/*     */       //   375: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   378: ifeq -> 417
/*     */       //   381: aload_0
/*     */       //   382: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   385: aload_0
/*     */       //   386: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   389: new scala/Some
/*     */       //   392: dup
/*     */       //   393: aload_0
/*     */       //   394: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   397: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   400: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   403: getstatic akka/io/Tcp$Aborted$.MODULE$ : Lakka/io/Tcp$Aborted$;
/*     */       //   406: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   409: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   412: astore #5
/*     */       //   414: goto -> 426
/*     */       //   417: aload_2
/*     */       //   418: aload_1
/*     */       //   419: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   424: astore #5
/*     */       //   426: aload #5
/*     */       //   428: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #90	-> 0
/*     */       //   #91	-> 2
/*     */       //   #92	-> 48
/*     */       //   #93	-> 94
/*     */       //   #95	-> 144
/*     */       //   #96	-> 171
/*     */       //   #97	-> 182
/*     */       //   #98	-> 198
/*     */       //   #95	-> 220
/*     */       //   #100	-> 225
/*     */       //   #101	-> 245
/*     */       //   #102	-> 254
/*     */       //   #103	-> 283
/*     */       //   #100	-> 305
/*     */       //   #105	-> 310
/*     */       //   #107	-> 354
/*     */       //   #90	-> 417
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	429	0	this	Lakka/io/TcpConnection$$anonfun$closingWithPendingWrite$1;
/*     */       //   0	429	1	x4	Ljava/lang/Object;
/*     */       //   0	429	2	default	Lscala/Function1;
/*     */       //   245	184	10	remaining	Lakka/io/TcpConnection$PendingWrite;
/*     */       //   330	99	12	e	Ljava/io/IOException;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x4) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
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
/*     */       //   29: goto -> 193
/*     */       //   32: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
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
/*     */       //   62: goto -> 193
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
/*     */       //   95: goto -> 193
/*     */       //   98: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
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
/*     */       //   128: goto -> 193
/*     */       //   131: aload_2
/*     */       //   132: instanceof akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   135: ifeq -> 144
/*     */       //   138: iconst_1
/*     */       //   139: istore #4
/*     */       //   141: goto -> 193
/*     */       //   144: aload_2
/*     */       //   145: instanceof akka/io/TcpConnection$WriteFileFailed
/*     */       //   148: ifeq -> 157
/*     */       //   151: iconst_1
/*     */       //   152: istore #4
/*     */       //   154: goto -> 193
/*     */       //   157: getstatic akka/io/Tcp$Abort$.MODULE$ : Lakka/io/Tcp$Abort$;
/*     */       //   160: aload_2
/*     */       //   161: astore #8
/*     */       //   163: dup
/*     */       //   164: ifnonnull -> 176
/*     */       //   167: pop
/*     */       //   168: aload #8
/*     */       //   170: ifnull -> 184
/*     */       //   173: goto -> 190
/*     */       //   176: aload #8
/*     */       //   178: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   181: ifeq -> 190
/*     */       //   184: iconst_1
/*     */       //   185: istore #4
/*     */       //   187: goto -> 193
/*     */       //   190: iconst_0
/*     */       //   191: istore #4
/*     */       //   193: iload #4
/*     */       //   195: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #90	-> 0
/*     */       //   #91	-> 2
/*     */       //   #92	-> 32
/*     */       //   #93	-> 65
/*     */       //   #95	-> 98
/*     */       //   #100	-> 131
/*     */       //   #105	-> 144
/*     */       //   #107	-> 157
/*     */       //   #90	-> 190
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	196	0	this	Lakka/io/TcpConnection$$anonfun$closingWithPendingWrite$1;
/*     */       //   0	196	1	x4	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$closingWithPendingWrite$1(TcpConnection $outer, TcpConnection.ConnectionInfo info$5, Option closeCommander$1, Tcp.ConnectionClosed closedEvent$1) {}
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$closing$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TcpConnection.ConnectionInfo info$6;
/*     */     
/*     */     private final Option closeCommander$2;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x5, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 48
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 48
/*     */       //   29: aload_0
/*     */       //   30: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   33: aload_0
/*     */       //   34: getfield info$6 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   37: invokevirtual suspendReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   40: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   43: astore #5
/*     */       //   45: goto -> 216
/*     */       //   48: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
/*     */       //   51: aload_3
/*     */       //   52: astore #6
/*     */       //   54: dup
/*     */       //   55: ifnonnull -> 67
/*     */       //   58: pop
/*     */       //   59: aload #6
/*     */       //   61: ifnull -> 75
/*     */       //   64: goto -> 94
/*     */       //   67: aload #6
/*     */       //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   72: ifeq -> 94
/*     */       //   75: aload_0
/*     */       //   76: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   79: aload_0
/*     */       //   80: getfield info$6 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   83: invokevirtual resumeReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   86: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   89: astore #5
/*     */       //   91: goto -> 216
/*     */       //   94: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   97: aload_3
/*     */       //   98: astore #7
/*     */       //   100: dup
/*     */       //   101: ifnonnull -> 113
/*     */       //   104: pop
/*     */       //   105: aload #7
/*     */       //   107: ifnull -> 121
/*     */       //   110: goto -> 144
/*     */       //   113: aload #7
/*     */       //   115: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   118: ifeq -> 144
/*     */       //   121: aload_0
/*     */       //   122: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   125: aload_0
/*     */       //   126: getfield info$6 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   129: aload_0
/*     */       //   130: getfield closeCommander$2 : Lscala/Option;
/*     */       //   133: invokevirtual doRead : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;)V
/*     */       //   136: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   139: astore #5
/*     */       //   141: goto -> 216
/*     */       //   144: getstatic akka/io/Tcp$Abort$.MODULE$ : Lakka/io/Tcp$Abort$;
/*     */       //   147: aload_3
/*     */       //   148: astore #8
/*     */       //   150: dup
/*     */       //   151: ifnonnull -> 163
/*     */       //   154: pop
/*     */       //   155: aload #8
/*     */       //   157: ifnull -> 171
/*     */       //   160: goto -> 207
/*     */       //   163: aload #8
/*     */       //   165: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   168: ifeq -> 207
/*     */       //   171: aload_0
/*     */       //   172: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   175: aload_0
/*     */       //   176: getfield info$6 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   179: new scala/Some
/*     */       //   182: dup
/*     */       //   183: aload_0
/*     */       //   184: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   187: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   190: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   193: getstatic akka/io/Tcp$Aborted$.MODULE$ : Lakka/io/Tcp$Aborted$;
/*     */       //   196: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   199: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   202: astore #5
/*     */       //   204: goto -> 216
/*     */       //   207: aload_2
/*     */       //   208: aload_1
/*     */       //   209: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   214: astore #5
/*     */       //   216: aload #5
/*     */       //   218: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #111	-> 0
/*     */       //   #112	-> 2
/*     */       //   #113	-> 48
/*     */       //   #114	-> 94
/*     */       //   #115	-> 144
/*     */       //   #111	-> 207
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	219	0	this	Lakka/io/TcpConnection$$anonfun$closing$1;
/*     */       //   0	219	1	x5	Ljava/lang/Object;
/*     */       //   0	219	2	default	Lscala/Function1;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x5) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
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
/*     */       //   32: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
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
/*     */       //   98: getstatic akka/io/Tcp$Abort$.MODULE$ : Lakka/io/Tcp$Abort$;
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
/*     */       //   #111	-> 0
/*     */       //   #112	-> 2
/*     */       //   #113	-> 32
/*     */       //   #114	-> 65
/*     */       //   #115	-> 98
/*     */       //   #111	-> 131
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	137	0	this	Lakka/io/TcpConnection$$anonfun$closing$1;
/*     */       //   0	137	1	x5	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$closing$1(TcpConnection $outer, TcpConnection.ConnectionInfo info$6, Option closeCommander$2) {}
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$handleWriteMessages$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TcpConnection.ConnectionInfo info$3;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x6, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 137
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 137
/*     */       //   29: aload_0
/*     */       //   30: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   33: invokevirtual writePending : ()Z
/*     */       //   36: ifeq -> 129
/*     */       //   39: aload_0
/*     */       //   40: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   43: aload_0
/*     */       //   44: getfield info$3 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   47: invokevirtual doWrite : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   50: aload_0
/*     */       //   51: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   54: invokevirtual writePending : ()Z
/*     */       //   57: ifne -> 123
/*     */       //   60: aload_0
/*     */       //   61: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   64: getfield akka$io$TcpConnection$$interestedInResume : Lscala/Option;
/*     */       //   67: invokevirtual nonEmpty : ()Z
/*     */       //   70: ifeq -> 123
/*     */       //   73: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   76: aload_0
/*     */       //   77: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   80: getfield akka$io$TcpConnection$$interestedInResume : Lscala/Option;
/*     */       //   83: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   86: checkcast akka/actor/ActorRef
/*     */       //   89: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   92: getstatic akka/io/Tcp$WritingResumed$.MODULE$ : Lakka/io/Tcp$WritingResumed$;
/*     */       //   95: aload_0
/*     */       //   96: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   99: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   102: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   107: aload_0
/*     */       //   108: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   111: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   114: putfield akka$io$TcpConnection$$interestedInResume : Lscala/Option;
/*     */       //   117: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   120: goto -> 132
/*     */       //   123: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   126: goto -> 132
/*     */       //   129: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   132: astore #5
/*     */       //   134: goto -> 665
/*     */       //   137: aload_3
/*     */       //   138: instanceof akka/io/Tcp$WriteCommand
/*     */       //   141: ifeq -> 384
/*     */       //   144: aload_3
/*     */       //   145: checkcast akka/io/Tcp$WriteCommand
/*     */       //   148: astore #6
/*     */       //   150: aload_0
/*     */       //   151: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   154: getfield akka$io$TcpConnection$$writingSuspended : Z
/*     */       //   157: ifeq -> 226
/*     */       //   160: aload_0
/*     */       //   161: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   164: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */       //   167: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */       //   170: invokevirtual TraceLogging : ()Z
/*     */       //   173: ifeq -> 190
/*     */       //   176: aload_0
/*     */       //   177: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   180: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   183: ldc 'Dropping write because writing is suspended'
/*     */       //   185: invokeinterface debug : (Ljava/lang/String;)V
/*     */       //   190: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   193: aload_0
/*     */       //   194: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   197: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   200: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   203: aload #6
/*     */       //   205: invokevirtual failureMessage : ()Lakka/io/Tcp$CommandFailed;
/*     */       //   208: aload_0
/*     */       //   209: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   212: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   215: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   220: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   223: goto -> 379
/*     */       //   226: aload_0
/*     */       //   227: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   230: invokevirtual writePending : ()Z
/*     */       //   233: ifeq -> 326
/*     */       //   236: aload_0
/*     */       //   237: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   240: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */       //   243: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */       //   246: invokevirtual TraceLogging : ()Z
/*     */       //   249: ifeq -> 266
/*     */       //   252: aload_0
/*     */       //   253: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   256: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   259: ldc 'Dropping write because queue is full'
/*     */       //   261: invokeinterface debug : (Ljava/lang/String;)V
/*     */       //   266: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   269: aload_0
/*     */       //   270: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   273: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   276: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   279: aload #6
/*     */       //   281: invokevirtual failureMessage : ()Lakka/io/Tcp$CommandFailed;
/*     */       //   284: aload_0
/*     */       //   285: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   288: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   291: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   296: aload_0
/*     */       //   297: getfield info$3 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   300: invokevirtual useResumeWriting : ()Z
/*     */       //   303: ifeq -> 320
/*     */       //   306: aload_0
/*     */       //   307: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   310: iconst_1
/*     */       //   311: putfield akka$io$TcpConnection$$writingSuspended : Z
/*     */       //   314: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   317: goto -> 379
/*     */       //   320: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   323: goto -> 379
/*     */       //   326: aload_0
/*     */       //   327: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   330: aload_0
/*     */       //   331: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   334: aload_0
/*     */       //   335: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   338: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   341: aload #6
/*     */       //   343: invokevirtual PendingWrite : (Lakka/actor/ActorRef;Lakka/io/Tcp$WriteCommand;)Lakka/io/TcpConnection$PendingWrite;
/*     */       //   346: putfield akka$io$TcpConnection$$pendingWrite : Lakka/io/TcpConnection$PendingWrite;
/*     */       //   349: aload_0
/*     */       //   350: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   353: invokevirtual writePending : ()Z
/*     */       //   356: ifeq -> 376
/*     */       //   359: aload_0
/*     */       //   360: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   363: aload_0
/*     */       //   364: getfield info$3 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   367: invokevirtual doWrite : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   370: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   373: goto -> 379
/*     */       //   376: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   379: astore #5
/*     */       //   381: goto -> 665
/*     */       //   384: getstatic akka/io/Tcp$ResumeWriting$.MODULE$ : Lakka/io/Tcp$ResumeWriting$;
/*     */       //   387: aload_3
/*     */       //   388: astore #7
/*     */       //   390: dup
/*     */       //   391: ifnonnull -> 403
/*     */       //   394: pop
/*     */       //   395: aload #7
/*     */       //   397: ifnull -> 411
/*     */       //   400: goto -> 546
/*     */       //   403: aload #7
/*     */       //   405: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   408: ifeq -> 546
/*     */       //   411: aload_0
/*     */       //   412: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   415: iconst_0
/*     */       //   416: putfield akka$io$TcpConnection$$writingSuspended : Z
/*     */       //   419: aload_0
/*     */       //   420: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   423: invokevirtual writePending : ()Z
/*     */       //   426: ifeq -> 510
/*     */       //   429: aload_0
/*     */       //   430: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   433: getfield akka$io$TcpConnection$$interestedInResume : Lscala/Option;
/*     */       //   436: invokevirtual isEmpty : ()Z
/*     */       //   439: ifeq -> 469
/*     */       //   442: aload_0
/*     */       //   443: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   446: new scala/Some
/*     */       //   449: dup
/*     */       //   450: aload_0
/*     */       //   451: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   454: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   457: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   460: putfield akka$io$TcpConnection$$interestedInResume : Lscala/Option;
/*     */       //   463: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   466: goto -> 541
/*     */       //   469: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   472: aload_0
/*     */       //   473: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   476: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   479: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   482: new akka/io/Tcp$CommandFailed
/*     */       //   485: dup
/*     */       //   486: getstatic akka/io/Tcp$ResumeWriting$.MODULE$ : Lakka/io/Tcp$ResumeWriting$;
/*     */       //   489: invokespecial <init> : (Lakka/io/Tcp$Command;)V
/*     */       //   492: aload_0
/*     */       //   493: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   496: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   499: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   504: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   507: goto -> 541
/*     */       //   510: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   513: aload_0
/*     */       //   514: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   517: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   520: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   523: getstatic akka/io/Tcp$WritingResumed$.MODULE$ : Lakka/io/Tcp$WritingResumed$;
/*     */       //   526: aload_0
/*     */       //   527: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   530: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   533: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   538: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   541: astore #5
/*     */       //   543: goto -> 665
/*     */       //   546: aload_3
/*     */       //   547: instanceof akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   550: ifeq -> 612
/*     */       //   553: aload_3
/*     */       //   554: checkcast akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   557: astore #8
/*     */       //   559: aload #8
/*     */       //   561: invokevirtual remainingWrite : ()Lakka/io/TcpConnection$PendingWrite;
/*     */       //   564: astore #9
/*     */       //   566: aload_0
/*     */       //   567: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   570: aload #9
/*     */       //   572: putfield akka$io$TcpConnection$$pendingWrite : Lakka/io/TcpConnection$PendingWrite;
/*     */       //   575: aload_0
/*     */       //   576: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   579: invokevirtual writePending : ()Z
/*     */       //   582: ifeq -> 604
/*     */       //   585: aload_0
/*     */       //   586: getfield info$3 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   589: invokevirtual registration : ()Lakka/io/ChannelRegistration;
/*     */       //   592: iconst_4
/*     */       //   593: invokeinterface enableInterest : (I)V
/*     */       //   598: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   601: goto -> 607
/*     */       //   604: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   607: astore #5
/*     */       //   609: goto -> 665
/*     */       //   612: aload_3
/*     */       //   613: instanceof akka/io/TcpConnection$WriteFileFailed
/*     */       //   616: ifeq -> 656
/*     */       //   619: aload_3
/*     */       //   620: checkcast akka/io/TcpConnection$WriteFileFailed
/*     */       //   623: astore #10
/*     */       //   625: aload #10
/*     */       //   627: invokevirtual e : ()Ljava/io/IOException;
/*     */       //   630: astore #11
/*     */       //   632: aload_0
/*     */       //   633: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   636: aload_0
/*     */       //   637: getfield info$3 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   640: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   643: aload #11
/*     */       //   645: invokevirtual handleError : (Lakka/actor/ActorRef;Ljava/io/IOException;)V
/*     */       //   648: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   651: astore #5
/*     */       //   653: goto -> 665
/*     */       //   656: aload_2
/*     */       //   657: aload_1
/*     */       //   658: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   663: astore #5
/*     */       //   665: aload #5
/*     */       //   667: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #118	-> 0
/*     */       //   #119	-> 2
/*     */       //   #120	-> 29
/*     */       //   #121	-> 39
/*     */       //   #122	-> 50
/*     */       //   #123	-> 73
/*     */       //   #124	-> 107
/*     */       //   #122	-> 123
/*     */       //   #120	-> 129
/*     */       //   #128	-> 137
/*     */       //   #129	-> 150
/*     */       //   #130	-> 160
/*     */       //   #131	-> 190
/*     */       //   #133	-> 226
/*     */       //   #134	-> 236
/*     */       //   #135	-> 266
/*     */       //   #136	-> 296
/*     */       //   #139	-> 326
/*     */       //   #140	-> 349
/*     */       //   #129	-> 379
/*     */       //   #143	-> 384
/*     */       //   #154	-> 411
/*     */       //   #155	-> 419
/*     */       //   #156	-> 429
/*     */       //   #157	-> 469
/*     */       //   #158	-> 510
/*     */       //   #143	-> 541
/*     */       //   #160	-> 546
/*     */       //   #161	-> 566
/*     */       //   #162	-> 575
/*     */       //   #160	-> 607
/*     */       //   #164	-> 612
/*     */       //   #118	-> 656
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	668	0	this	Lakka/io/TcpConnection$$anonfun$handleWriteMessages$1;
/*     */       //   0	668	1	x6	Ljava/lang/Object;
/*     */       //   0	668	2	default	Lscala/Function1;
/*     */       //   566	102	9	remaining	Lakka/io/TcpConnection$PendingWrite;
/*     */       //   632	36	11	e	Ljava/io/IOException;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x6) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
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
/*     */       //   29: goto -> 107
/*     */       //   32: aload_2
/*     */       //   33: instanceof akka/io/Tcp$WriteCommand
/*     */       //   36: ifeq -> 45
/*     */       //   39: iconst_1
/*     */       //   40: istore #4
/*     */       //   42: goto -> 107
/*     */       //   45: getstatic akka/io/Tcp$ResumeWriting$.MODULE$ : Lakka/io/Tcp$ResumeWriting$;
/*     */       //   48: aload_2
/*     */       //   49: astore #5
/*     */       //   51: dup
/*     */       //   52: ifnonnull -> 64
/*     */       //   55: pop
/*     */       //   56: aload #5
/*     */       //   58: ifnull -> 72
/*     */       //   61: goto -> 78
/*     */       //   64: aload #5
/*     */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   69: ifeq -> 78
/*     */       //   72: iconst_1
/*     */       //   73: istore #4
/*     */       //   75: goto -> 107
/*     */       //   78: aload_2
/*     */       //   79: instanceof akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   82: ifeq -> 91
/*     */       //   85: iconst_1
/*     */       //   86: istore #4
/*     */       //   88: goto -> 107
/*     */       //   91: aload_2
/*     */       //   92: instanceof akka/io/TcpConnection$WriteFileFailed
/*     */       //   95: ifeq -> 104
/*     */       //   98: iconst_1
/*     */       //   99: istore #4
/*     */       //   101: goto -> 107
/*     */       //   104: iconst_0
/*     */       //   105: istore #4
/*     */       //   107: iload #4
/*     */       //   109: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #118	-> 0
/*     */       //   #119	-> 2
/*     */       //   #120	-> 26
/*     */       //   #128	-> 32
/*     */       //   #129	-> 39
/*     */       //   #143	-> 45
/*     */       //   #160	-> 78
/*     */       //   #164	-> 91
/*     */       //   #118	-> 104
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	110	0	this	Lakka/io/TcpConnection$$anonfun$handleWriteMessages$1;
/*     */       //   0	110	1	x6	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$handleWriteMessages$1(TcpConnection $outer, TcpConnection.ConnectionInfo info$3) {}
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$completeConnect$1 extends AbstractFunction1<Inet.SocketOption, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Inet.SocketOption x$1) {
/* 174 */       x$1.afterConnect(this.$outer.channel().socket());
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$completeConnect$1(TcpConnection $outer) {}
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$postStop$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(ActorRef x$2) {
/* 322 */       akka.actor.package$.MODULE$.actorRef2Scala(x$2).$bang(this.$outer.closedMessage().closedEvent(), this.$outer.self());
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$postStop$1(TcpConnection $outer) {}
/*     */   }
/*     */   
/*     */   private TcpConnection$() {
/* 436 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpConnection$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */