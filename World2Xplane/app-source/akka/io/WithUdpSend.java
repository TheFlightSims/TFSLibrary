/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import java.nio.channels.DatagramChannel;
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\025a!C\001\003!\003\r\tA\001\004y\005-9\026\016\0365VIB\034VM\0343\013\005\r!\021AA5p\025\005)\021\001B1lW\006\034\"\001A\004\021\005!YQ\"A\005\013\003)\tQa]2bY\006L!\001D\005\003\r\005s\027PU3g\021\025q\001\001\"\001\021\003\031!\023N\\5uI\r\001A#A\t\021\005!\021\022BA\n\n\005\021)f.\033;\t\017U\001\001\031!C\005-\005Y\001/\0328eS:<7+\0328e+\0059\002C\001\r#\035\tI\002E\004\002\033?9\0211DH\007\0029)\021QdD\001\007yI|w\016\036 \n\003\025I!a\001\003\n\005\005\022\021aA+ea&\0211\005\n\002\005'\026tGM\003\002\"\005!9a\005\001a\001\n\0239\023a\0049f]\022LgnZ*f]\022|F%Z9\025\005EA\003bB\025&\003\003\005\raF\001\004q\022\n\004BB\026\001A\003&q#\001\007qK:$\027N\\4TK:$\007\005C\004.\001\001\007I\021\002\030\002!A,g\016Z5oO\016{W.\\1oI\026\024X#A\030\021\005A\032T\"A\031\013\005I\"\021!B1di>\024\030B\001\0332\005!\t5\r^8s%\0264\007b\002\034\001\001\004%IaN\001\025a\026tG-\0338h\007>lW.\0318eKJ|F%Z9\025\005EA\004bB\0256\003\003\005\ra\f\005\007u\001\001\013\025B\030\002#A,g\016Z5oO\016{W.\\1oI\026\024\b\005C\004=\001\001\007I\021B\037\002\027I,GO]5fIN+g\016Z\013\002}A\021\001bP\005\003\001&\021qAQ8pY\026\fg\016C\004C\001\001\007I\021B\"\002\037I,GO]5fIN+g\016Z0%KF$\"!\005#\t\017%\n\025\021!a\001}!1a\t\001Q!\ny\nAB]3ue&,GmU3oI\002BQ\001\023\001\005\nu\nq\002[1t/JLG/\032)f]\022Lgn\032\005\006\025\0021\taS\001\bG\"\fgN\\3m+\005a\005CA'U\033\005q%BA(Q\003!\031\007.\0318oK2\034(BA)S\003\rq\027n\034\006\002'\006!!.\031<b\023\t)fJA\bECR\fwM]1n\007\"\fgN\\3m\021\0259\006A\"\001Y\003\r)H\r]\013\0023B\021!lW\007\002\005%\021AL\001\002\007+\022\004X\t\037;\t\017y\003!\031!C\001?\006A1/\032;uS:<7/F\001a!\t\t'M\004\002[A%\0211\r\n\002\f+\022\0048+\032;uS:<7\017\003\004f\001\001\006I\001Y\001\ng\026$H/\0338hg\002BQa\032\001\005\002!\fAb]3oI\"\013g\016\0327feN$\"![8\021\005)\\W\"\001\001\n\0051l'a\002*fG\026Lg/Z\005\003]F\022Q!Q2u_JDQ\001\0354A\002E\fAB]3hSN$(/\031;j_:\004\"A\027:\n\005M\024!aE\"iC:tW\r\034*fO&\034HO]1uS>t\007\"B;\001\t\0231\030A\0023p'\026tG\r\006\002\022o\")\001\017\036a\001cJ\031\021p\037?\007\ti\004\001\001\037\002\ryI,g-\0338f[\026tGO\020\t\0035\002\0212! @\000\r\021Q\b\001\001?\021\005Aj\007c\001\031\002\002%\031\0211A\031\003\031\005\033Go\034:M_\036<\027N\\4")
/*    */ public interface WithUdpSend {
/*    */   void akka$io$WithUdpSend$_setter_$settings_$eq(Udp.UdpSettings paramUdpSettings);
/*    */   
/*    */   Udp.Send akka$io$WithUdpSend$$pendingSend();
/*    */   
/*    */   @TraitSetter
/*    */   void akka$io$WithUdpSend$$pendingSend_$eq(Udp.Send paramSend);
/*    */   
/*    */   ActorRef akka$io$WithUdpSend$$pendingCommander();
/*    */   
/*    */   @TraitSetter
/*    */   void akka$io$WithUdpSend$$pendingCommander_$eq(ActorRef paramActorRef);
/*    */   
/*    */   boolean akka$io$WithUdpSend$$retriedSend();
/*    */   
/*    */   @TraitSetter
/*    */   void akka$io$WithUdpSend$$retriedSend_$eq(boolean paramBoolean);
/*    */   
/*    */   DatagramChannel channel();
/*    */   
/*    */   UdpExt udp();
/*    */   
/*    */   Udp.UdpSettings settings();
/*    */   
/*    */   PartialFunction<Object, BoxedUnit> sendHandlers(ChannelRegistration paramChannelRegistration);
/*    */   
/*    */   public class WithUdpSend$$anonfun$sendHandlers$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final ChannelRegistration registration$1;
/*    */     
/*    */     public WithUdpSend$$anonfun$sendHandlers$1(WithUdpSend $outer, ChannelRegistration registration$1) {}
/*    */     
/*    */     public final boolean isDefinedAt(Object x1) {
/* 31 */       boolean bool2, bool1 = false;
/* 31 */       null;
/* 31 */       Udp.Send send = null;
/*    */       Object object = x1;
/* 31 */       if (object instanceof Udp.Send) {
/* 31 */         bool1 = true;
/* 31 */         send = (Udp.Send)object;
/* 31 */         if (WithUdpSend$class.akka$io$WithUdpSend$$hasWritePending(this.$outer))
/*    */           return true; 
/*    */       } 
/*    */       if (bool1)
/* 35 */         if (send.payload().isEmpty())
/*    */           return true;  
/*    */       if (bool1) {
/* 39 */         bool2 = true;
/*    */       } else {
/* 44 */         Object object1 = object;
/* 44 */         if (SelectionHandler.ChannelWritable$.MODULE$ == null) {
/* 44 */           if (object1 != null)
/*    */             boolean bool = false; 
/*    */         } else {
/* 44 */           if (SelectionHandler.ChannelWritable$.MODULE$.equals(object1))
/* 44 */             boolean bool3 = true; 
/*    */           boolean bool = false;
/*    */         } 
/* 44 */         bool2 = true;
/*    */       } 
/*    */       return bool2;
/*    */     }
/*    */     
/*    */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*    */       // Byte code:
/*    */       //   0: iconst_0
/*    */       //   1: istore_3
/*    */       //   2: aconst_null
/*    */       //   3: pop
/*    */       //   4: aconst_null
/*    */       //   5: astore #4
/*    */       //   7: aload_1
/*    */       //   8: astore #5
/*    */       //   10: aload #5
/*    */       //   12: instanceof akka/io/Udp$Send
/*    */       //   15: ifeq -> 123
/*    */       //   18: iconst_1
/*    */       //   19: istore_3
/*    */       //   20: aload #5
/*    */       //   22: checkcast akka/io/Udp$Send
/*    */       //   25: astore #4
/*    */       //   27: aload_0
/*    */       //   28: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   31: invokestatic akka$io$WithUdpSend$$hasWritePending : (Lakka/io/WithUdpSend;)Z
/*    */       //   34: ifeq -> 123
/*    */       //   37: aload_0
/*    */       //   38: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   41: invokeinterface settings : ()Lakka/io/Udp$UdpSettings;
/*    */       //   46: invokevirtual TraceLogging : ()Z
/*    */       //   49: ifeq -> 71
/*    */       //   52: aload_0
/*    */       //   53: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   56: checkcast akka/actor/ActorLogging
/*    */       //   59: invokeinterface log : ()Lakka/event/LoggingAdapter;
/*    */       //   64: ldc 'Dropping write because queue is full'
/*    */       //   66: invokeinterface debug : (Ljava/lang/String;)V
/*    */       //   71: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*    */       //   74: aload_0
/*    */       //   75: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   78: checkcast akka/actor/Actor
/*    */       //   81: invokeinterface sender : ()Lakka/actor/ActorRef;
/*    */       //   86: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*    */       //   89: new akka/io/Udp$CommandFailed
/*    */       //   92: dup
/*    */       //   93: aload #4
/*    */       //   95: invokespecial <init> : (Lakka/io/Udp$Command;)V
/*    */       //   98: aload_0
/*    */       //   99: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   102: checkcast akka/actor/Actor
/*    */       //   105: invokeinterface self : ()Lakka/actor/ActorRef;
/*    */       //   110: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*    */       //   115: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */       //   118: astore #6
/*    */       //   120: goto -> 327
/*    */       //   123: iload_3
/*    */       //   124: ifeq -> 200
/*    */       //   127: aload #4
/*    */       //   129: invokevirtual payload : ()Lakka/util/ByteString;
/*    */       //   132: invokevirtual isEmpty : ()Z
/*    */       //   135: ifeq -> 200
/*    */       //   138: aload #4
/*    */       //   140: invokevirtual wantsAck : ()Z
/*    */       //   143: ifeq -> 192
/*    */       //   146: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*    */       //   149: aload_0
/*    */       //   150: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   153: checkcast akka/actor/Actor
/*    */       //   156: invokeinterface sender : ()Lakka/actor/ActorRef;
/*    */       //   161: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*    */       //   164: aload #4
/*    */       //   166: invokevirtual ack : ()Lakka/io/Udp$Event;
/*    */       //   169: aload_0
/*    */       //   170: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   173: checkcast akka/actor/Actor
/*    */       //   176: invokeinterface self : ()Lakka/actor/ActorRef;
/*    */       //   181: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*    */       //   186: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */       //   189: goto -> 195
/*    */       //   192: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */       //   195: astore #6
/*    */       //   197: goto -> 327
/*    */       //   200: iload_3
/*    */       //   201: ifeq -> 255
/*    */       //   204: aload_0
/*    */       //   205: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   208: aload #4
/*    */       //   210: invokeinterface akka$io$WithUdpSend$$pendingSend_$eq : (Lakka/io/Udp$Send;)V
/*    */       //   215: aload_0
/*    */       //   216: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   219: aload_0
/*    */       //   220: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   223: checkcast akka/actor/Actor
/*    */       //   226: invokeinterface sender : ()Lakka/actor/ActorRef;
/*    */       //   231: invokeinterface akka$io$WithUdpSend$$pendingCommander_$eq : (Lakka/actor/ActorRef;)V
/*    */       //   236: aload_0
/*    */       //   237: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   240: aload_0
/*    */       //   241: getfield registration$1 : Lakka/io/ChannelRegistration;
/*    */       //   244: invokestatic akka$io$WithUdpSend$$doSend : (Lakka/io/WithUdpSend;Lakka/io/ChannelRegistration;)V
/*    */       //   247: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */       //   250: astore #6
/*    */       //   252: goto -> 327
/*    */       //   255: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
/*    */       //   258: aload #5
/*    */       //   260: astore #7
/*    */       //   262: dup
/*    */       //   263: ifnonnull -> 275
/*    */       //   266: pop
/*    */       //   267: aload #7
/*    */       //   269: ifnull -> 283
/*    */       //   272: goto -> 318
/*    */       //   275: aload #7
/*    */       //   277: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   280: ifeq -> 318
/*    */       //   283: aload_0
/*    */       //   284: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   287: invokestatic akka$io$WithUdpSend$$hasWritePending : (Lakka/io/WithUdpSend;)Z
/*    */       //   290: ifeq -> 310
/*    */       //   293: aload_0
/*    */       //   294: getfield $outer : Lakka/io/WithUdpSend;
/*    */       //   297: aload_0
/*    */       //   298: getfield registration$1 : Lakka/io/ChannelRegistration;
/*    */       //   301: invokestatic akka$io$WithUdpSend$$doSend : (Lakka/io/WithUdpSend;Lakka/io/ChannelRegistration;)V
/*    */       //   304: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */       //   307: goto -> 313
/*    */       //   310: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */       //   313: astore #6
/*    */       //   315: goto -> 327
/*    */       //   318: aload_2
/*    */       //   319: aload_1
/*    */       //   320: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */       //   325: astore #6
/*    */       //   327: aload #6
/*    */       //   329: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #31	-> 0
/*    */       //   #30	-> 7
/*    */       //   #31	-> 10
/*    */       //   #32	-> 37
/*    */       //   #33	-> 71
/*    */       //   #31	-> 118
/*    */       //   #30	-> 123
/*    */       //   #35	-> 127
/*    */       //   #36	-> 138
/*    */       //   #37	-> 146
/*    */       //   #36	-> 192
/*    */       //   #30	-> 200
/*    */       //   #40	-> 204
/*    */       //   #41	-> 215
/*    */       //   #42	-> 236
/*    */       //   #39	-> 250
/*    */       //   #44	-> 255
/*    */       //   #30	-> 318
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	330	0	this	Lakka/io/WithUdpSend$$anonfun$sendHandlers$1;
/*    */       //   0	330	1	x1	Ljava/lang/Object;
/*    */       //   0	330	2	default	Lscala/Function1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\WithUdpSend.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */