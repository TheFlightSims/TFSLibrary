/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.Deploy$;
/*     */ import akka.actor.ExtendedActorSystem;
/*     */ import akka.actor.Props$;
/*     */ import akka.dispatch.MessageDispatcher;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Function0;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Seq;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025c\001B\001\003\001\035\021a\001V2q\013b$(BA\002\005\003\tIwNC\001\006\003\021\t7n[1\004\001M\031\001\001\003\b\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty1C\004\002\021#5\t!!\003\002\023\005\005\021\021jT\005\003)U\021\021\"\022=uK:\034\030n\0348\013\005I\021\001\002C\f\001\005\003\005\013\021\002\r\002\rML8\017^3n!\tIB$D\001\033\025\tYB!A\003bGR|'/\003\002\0365\t\031R\t\037;f]\022,G-Q2u_J\034\026p\035;f[\")q\004\001C\001A\0051A(\0338jiz\"\"!\t\022\021\005A\001\001\"B\f\037\001\004A\002b\002\023\001\005\004%\t!J\001\t'\026$H/\0338hgV\ta\005\005\002(Q5\t\001A\002\003*\001\001Q#\001C*fiRLgnZ:\024\005!Z\003C\001\t-\023\ti#A\001\rTK2,7\r^5p]\"\013g\016\0327feN+G\017^5oOND\001b\f\025\003\002\003\006I\001M\001\b?\016|gNZ5h!\t\t\004(D\0013\025\t\031D'\001\004d_:4\027n\032\006\003kY\n\001\002^=qKN\fg-\032\006\002o\005\0311m\\7\n\005e\022$AB\"p]\032Lw\r\003\004 Q\021\005\001a\017\013\003MqBQa\f\036A\002ABqA\020\025C\002\023\005q(A\007Oe>37+\0327fGR|'o]\013\002\001B\021\021\"Q\005\003\005*\0211!\0238u\021\031!\005\006)A\005\001\006qaJ](g'\026dWm\031;peN\004\003b\002$)\005\004%\taP\001\021\005\006$8\r[!dG\026\004H\017T5nSRDa\001\023\025!\002\023\001\025!\005\"bi\016D\027iY2faRd\025.\\5uA!9!\n\013b\001\n\003y\024\001\005#je\026\034GOQ;gM\026\0248+\033>f\021\031a\005\006)A\005\001\006\tB)\033:fGR\024UO\0324feNK'0\032\021\t\0179C#\031!C\001\0059R*\031=ESJ,7\r\036\"vM\032,'\017U8pYNK'0\032\005\007!\"\002\013\021\002!\00215\013\007\020R5sK\016$()\0364gKJ\004vn\0347TSj,\007\005C\004SQ\t\007I\021A*\002\037I+w-[:uKJ$\026.\\3pkR,\022\001\026\t\003+jk\021A\026\006\003/b\013\001\002Z;sCRLwN\034\006\0033*\t!bY8oGV\024(/\0328u\023\tYfK\001\005EkJ\fG/[8o\021\031i\006\006)A\005)\006\001\"+Z4jgR,'\017V5nK>,H\017\t\005\b?\"\022\r\021\"\001@\003a\021VmY3jm\026$W*Z:tC\036,7+\033>f\031&l\027\016\036\005\007C\"\002\013\021\002!\0023I+7-Z5wK\022lUm]:bO\026\034\026N_3MS6LG\017\t\005\bG\"\022\r\021\"\001e\003Qi\025M\\1hK6,g\016\036#jgB\fGo\0315feV\tQ\r\005\002gS:\021\021bZ\005\003Q*\ta\001\025:fI\0264\027B\0016l\005\031\031FO]5oO*\021\001N\003\005\007[\"\002\013\021B3\002+5\013g.Y4f[\026tG\017R5ta\006$8\r[3sA!9q\016\013b\001\n\003!\027\001\005$jY\026Lu\nR5ta\006$8\r[3s\021\031\t\b\006)A\005K\006\tb)\0337f\023>#\025n\0359bi\016DWM\035\021\t\017MD#\031!C\001\005yAK]1og\032,'\017V8MS6LG\017\003\004vQ\001\006I\001Q\001\021)J\fgn\0354feR{G*[7ji\002Bqa\036\025C\002\023\005q(\001\fNCb\034\005.\0318oK2\034\b+\032:TK2,7\r^8s\021\031I\b\006)A\005\001\0069R*\031=DQ\006tg.\0327t!\026\0248+\0327fGR|'\017\t\005\bw\"\022\r\021\"\001@\003Q1\025N\\5tQ\016{gN\\3diJ+GO]5fg\"1Q\020\013Q\001\n\001\013QCR5oSND7i\0348oK\016$(+\032;sS\026\034\b\005C\004\000Q\001&I!!\001\002\027\035,G/\0238u\005f$Xm\035\013\004\001\006\r\001BBA\003}\002\007Q-\001\003qCRD\007bBA\005\001\001\006IAJ\001\n'\026$H/\0338hg\002B\021\"!\004\001\005\004%\t!a\004\002\0175\fg.Y4feV\021\021\021\003\t\0043\005M\021bAA\0135\tA\021i\031;peJ+g\r\003\005\002\032\001\001\013\021BA\t\003!i\027M\\1hKJ\004\003bBA\017\001\021\005\021qB\001\013O\026$X*\0318bO\026\024\b\"CA\021\001\t\007I\021AA\022\003)\021WO\0324feB{w\016\\\013\003\003K\0012\001EA\024\023\r\tIC\001\002\013\005V4g-\032:Q_>d\007\002CA\027\001\001\006I!!\n\002\027\t,hMZ3s!>|G\016\t\005\n\003c\001!\031!C\001\003g\t\001CZ5mK&{G)[:qCR\034\007.\032:\026\005\005U\002\003BA\034\003{i!!!\017\013\007\005mB!\001\005eSN\004\030\r^2i\023\021\ty$!\017\003#5+7o]1hK\022K7\017]1uG\",'\017\003\005\002D\001\001\013\021BA\033\003E1\027\016\\3J_\022K7\017]1uG\",'\017\t")
/*     */ public class TcpExt implements IO.Extension {
/*     */   private final Settings Settings;
/*     */   
/*     */   private final ActorRef manager;
/*     */   
/*     */   private final BufferPool bufferPool;
/*     */   
/*     */   private final MessageDispatcher fileIoDispatcher;
/*     */   
/*     */   public TcpExt(ExtendedActorSystem system) {
/* 518 */     this.Settings = new Settings(this, system.settings().config().getConfig("akka.io.tcp"));
/* 558 */     this.manager = 
/* 559 */       system.systemActorOf(
/* 560 */         Props$.MODULE$.apply(TcpManager.class, (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this })).withDispatcher(Settings().ManagementDispatcher()).withDeploy(Deploy$.MODULE$.local()), 
/* 561 */         "IO-TCP");
/* 569 */     this.bufferPool = new DirectByteBufferPool(Settings().DirectBufferSize(), Settings().MaxDirectBufferPoolSize());
/* 570 */     this.fileIoDispatcher = system.dispatchers().lookup(Settings().FileIODispatcher());
/*     */   }
/*     */   
/*     */   public Settings Settings() {
/*     */     return this.Settings;
/*     */   }
/*     */   
/*     */   public class Settings extends SelectionHandlerSettings {
/*     */     private final Config _config;
/*     */     
/*     */     private final int NrOfSelectors;
/*     */     
/*     */     private final int BatchAcceptLimit;
/*     */     
/*     */     private final int DirectBufferSize;
/*     */     
/*     */     private final int MaxDirectBufferPoolSize;
/*     */     
/*     */     private final Duration RegisterTimeout;
/*     */     
/*     */     private final int ReceivedMessageSizeLimit;
/*     */     
/*     */     private final String ManagementDispatcher;
/*     */     
/*     */     private final String FileIODispatcher;
/*     */     
/*     */     private final int TransferToLimit;
/*     */     
/*     */     private final int MaxChannelsPerSelector;
/*     */     
/*     */     private final int FinishConnectRetries;
/*     */     
/*     */     public Settings(TcpExt $outer, Config _config) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_2
/*     */       //   2: putfield _config : Lcom/typesafe/config/Config;
/*     */       //   5: aload_1
/*     */       //   6: ifnonnull -> 17
/*     */       //   9: new java/lang/NullPointerException
/*     */       //   12: dup
/*     */       //   13: invokespecial <init> : ()V
/*     */       //   16: athrow
/*     */       //   17: aload_0
/*     */       //   18: aload_1
/*     */       //   19: putfield $outer : Lakka/io/TcpExt;
/*     */       //   22: aload_0
/*     */       //   23: aload_2
/*     */       //   24: invokespecial <init> : (Lcom/typesafe/config/Config;)V
/*     */       //   27: aload_0
/*     */       //   28: getstatic akka/util/Helpers$Requiring$.MODULE$ : Lakka/util/Helpers$Requiring$;
/*     */       //   31: getstatic akka/util/Helpers$.MODULE$ : Lakka/util/Helpers$;
/*     */       //   34: aload_2
/*     */       //   35: ldc 'nr-of-selectors'
/*     */       //   37: invokeinterface getInt : (Ljava/lang/String;)I
/*     */       //   42: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */       //   45: invokevirtual Requiring : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   48: new akka/io/TcpExt$Settings$$anonfun$1
/*     */       //   51: dup
/*     */       //   52: aload_0
/*     */       //   53: invokespecial <init> : (Lakka/io/TcpExt$Settings;)V
/*     */       //   56: new akka/io/TcpExt$Settings$$anonfun$7
/*     */       //   59: dup
/*     */       //   60: aload_0
/*     */       //   61: invokespecial <init> : (Lakka/io/TcpExt$Settings;)V
/*     */       //   64: invokevirtual requiring$extension1 : (Ljava/lang/Object;Lscala/Function1;Lscala/Function0;)Ljava/lang/Object;
/*     */       //   67: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*     */       //   70: putfield NrOfSelectors : I
/*     */       //   73: aload_0
/*     */       //   74: getstatic akka/util/Helpers$Requiring$.MODULE$ : Lakka/util/Helpers$Requiring$;
/*     */       //   77: getstatic akka/util/Helpers$.MODULE$ : Lakka/util/Helpers$;
/*     */       //   80: aload_2
/*     */       //   81: ldc 'batch-accept-limit'
/*     */       //   83: invokeinterface getInt : (Ljava/lang/String;)I
/*     */       //   88: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */       //   91: invokevirtual Requiring : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   94: new akka/io/TcpExt$Settings$$anonfun$2
/*     */       //   97: dup
/*     */       //   98: aload_0
/*     */       //   99: invokespecial <init> : (Lakka/io/TcpExt$Settings;)V
/*     */       //   102: new akka/io/TcpExt$Settings$$anonfun$8
/*     */       //   105: dup
/*     */       //   106: aload_0
/*     */       //   107: invokespecial <init> : (Lakka/io/TcpExt$Settings;)V
/*     */       //   110: invokevirtual requiring$extension1 : (Ljava/lang/Object;Lscala/Function1;Lscala/Function0;)Ljava/lang/Object;
/*     */       //   113: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*     */       //   116: putfield BatchAcceptLimit : I
/*     */       //   119: aload_0
/*     */       //   120: aload_0
/*     */       //   121: ldc 'direct-buffer-size'
/*     */       //   123: invokespecial getIntBytes : (Ljava/lang/String;)I
/*     */       //   126: putfield DirectBufferSize : I
/*     */       //   129: aload_0
/*     */       //   130: aload_2
/*     */       //   131: ldc 'direct-buffer-pool-limit'
/*     */       //   133: invokeinterface getInt : (Ljava/lang/String;)I
/*     */       //   138: putfield MaxDirectBufferPoolSize : I
/*     */       //   141: aload_0
/*     */       //   142: aload_2
/*     */       //   143: ldc 'register-timeout'
/*     */       //   145: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */       //   150: astore_3
/*     */       //   151: ldc 'infinite'
/*     */       //   153: aload_3
/*     */       //   154: astore #4
/*     */       //   156: dup
/*     */       //   157: ifnonnull -> 169
/*     */       //   160: pop
/*     */       //   161: aload #4
/*     */       //   163: ifnull -> 177
/*     */       //   166: goto -> 188
/*     */       //   169: aload #4
/*     */       //   171: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   174: ifeq -> 188
/*     */       //   177: getstatic scala/concurrent/duration/Duration$.MODULE$ : Lscala/concurrent/duration/Duration$;
/*     */       //   180: invokevirtual Undefined : ()Lscala/concurrent/duration/Duration$Infinite;
/*     */       //   183: astore #5
/*     */       //   185: goto -> 205
/*     */       //   188: getstatic akka/util/Helpers$ConfigOps$.MODULE$ : Lakka/util/Helpers$ConfigOps$;
/*     */       //   191: getstatic akka/util/Helpers$.MODULE$ : Lakka/util/Helpers$;
/*     */       //   194: aload_2
/*     */       //   195: invokevirtual ConfigOps : (Lcom/typesafe/config/Config;)Lcom/typesafe/config/Config;
/*     */       //   198: ldc 'register-timeout'
/*     */       //   200: invokevirtual getMillisDuration$extension : (Lcom/typesafe/config/Config;Ljava/lang/String;)Lscala/concurrent/duration/FiniteDuration;
/*     */       //   203: astore #5
/*     */       //   205: aload #5
/*     */       //   207: putfield RegisterTimeout : Lscala/concurrent/duration/Duration;
/*     */       //   210: aload_0
/*     */       //   211: aload_2
/*     */       //   212: ldc 'max-received-message-size'
/*     */       //   214: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */       //   219: astore #6
/*     */       //   221: ldc 'unlimited'
/*     */       //   223: aload #6
/*     */       //   225: astore #7
/*     */       //   227: dup
/*     */       //   228: ifnonnull -> 240
/*     */       //   231: pop
/*     */       //   232: aload #7
/*     */       //   234: ifnull -> 248
/*     */       //   237: goto -> 255
/*     */       //   240: aload #7
/*     */       //   242: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   245: ifeq -> 255
/*     */       //   248: ldc 2147483647
/*     */       //   250: istore #8
/*     */       //   252: goto -> 263
/*     */       //   255: aload_0
/*     */       //   256: ldc 'received-message-size-limit'
/*     */       //   258: invokespecial getIntBytes : (Ljava/lang/String;)I
/*     */       //   261: istore #8
/*     */       //   263: iload #8
/*     */       //   265: putfield ReceivedMessageSizeLimit : I
/*     */       //   268: aload_0
/*     */       //   269: aload_2
/*     */       //   270: ldc 'management-dispatcher'
/*     */       //   272: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */       //   277: putfield ManagementDispatcher : Ljava/lang/String;
/*     */       //   280: aload_0
/*     */       //   281: aload_2
/*     */       //   282: ldc 'file-io-dispatcher'
/*     */       //   284: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */       //   289: putfield FileIODispatcher : Ljava/lang/String;
/*     */       //   292: aload_0
/*     */       //   293: aload_2
/*     */       //   294: ldc 'file-io-transferTo-limit'
/*     */       //   296: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */       //   301: astore #9
/*     */       //   303: ldc 'unlimited'
/*     */       //   305: aload #9
/*     */       //   307: astore #10
/*     */       //   309: dup
/*     */       //   310: ifnonnull -> 322
/*     */       //   313: pop
/*     */       //   314: aload #10
/*     */       //   316: ifnull -> 330
/*     */       //   319: goto -> 337
/*     */       //   322: aload #10
/*     */       //   324: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   327: ifeq -> 337
/*     */       //   330: ldc 2147483647
/*     */       //   332: istore #11
/*     */       //   334: goto -> 345
/*     */       //   337: aload_0
/*     */       //   338: ldc 'file-io-transferTo-limit'
/*     */       //   340: invokespecial getIntBytes : (Ljava/lang/String;)I
/*     */       //   343: istore #11
/*     */       //   345: iload #11
/*     */       //   347: putfield TransferToLimit : I
/*     */       //   350: aload_0
/*     */       //   351: aload_0
/*     */       //   352: invokevirtual MaxChannels : ()I
/*     */       //   355: iconst_m1
/*     */       //   356: if_icmpne -> 363
/*     */       //   359: iconst_m1
/*     */       //   360: goto -> 379
/*     */       //   363: getstatic scala/math/package$.MODULE$ : Lscala/math/package$;
/*     */       //   366: aload_0
/*     */       //   367: invokevirtual MaxChannels : ()I
/*     */       //   370: aload_0
/*     */       //   371: invokevirtual NrOfSelectors : ()I
/*     */       //   374: idiv
/*     */       //   375: iconst_1
/*     */       //   376: invokevirtual max : (II)I
/*     */       //   379: putfield MaxChannelsPerSelector : I
/*     */       //   382: aload_0
/*     */       //   383: getstatic akka/util/Helpers$Requiring$.MODULE$ : Lakka/util/Helpers$Requiring$;
/*     */       //   386: getstatic akka/util/Helpers$.MODULE$ : Lakka/util/Helpers$;
/*     */       //   389: aload_2
/*     */       //   390: ldc 'finish-connect-retries'
/*     */       //   392: invokeinterface getInt : (Ljava/lang/String;)I
/*     */       //   397: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */       //   400: invokevirtual Requiring : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   403: new akka/io/TcpExt$Settings$$anonfun$3
/*     */       //   406: dup
/*     */       //   407: aload_0
/*     */       //   408: invokespecial <init> : (Lakka/io/TcpExt$Settings;)V
/*     */       //   411: new akka/io/TcpExt$Settings$$anonfun$9
/*     */       //   414: dup
/*     */       //   415: aload_0
/*     */       //   416: invokespecial <init> : (Lakka/io/TcpExt$Settings;)V
/*     */       //   419: invokevirtual requiring$extension1 : (Ljava/lang/Object;Lscala/Function1;Lscala/Function0;)Ljava/lang/Object;
/*     */       //   422: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*     */       //   425: putfield FinishConnectRetries : I
/*     */       //   428: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #519	-> 0
/*     */       //   #523	-> 27
/*     */       //   #525	-> 73
/*     */       //   #526	-> 119
/*     */       //   #527	-> 129
/*     */       //   #528	-> 141
/*     */       //   #529	-> 151
/*     */       //   #530	-> 191
/*     */       //   #528	-> 205
/*     */       //   #532	-> 210
/*     */       //   #533	-> 221
/*     */       //   #534	-> 255
/*     */       //   #532	-> 263
/*     */       //   #536	-> 268
/*     */       //   #537	-> 280
/*     */       //   #538	-> 292
/*     */       //   #539	-> 303
/*     */       //   #540	-> 337
/*     */       //   #538	-> 345
/*     */       //   #543	-> 350
/*     */       //   #544	-> 382
/*     */       //   #545	-> 411
/*     */       //   #544	-> 419
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	429	0	this	Lakka/io/TcpExt$Settings;
/*     */       //   0	429	1	$outer	Lakka/io/TcpExt;
/*     */       //   0	429	2	_config	Lcom/typesafe/config/Config;
/*     */     }
/*     */     
/*     */     public int NrOfSelectors() {
/*     */       return this.NrOfSelectors;
/*     */     }
/*     */     
/*     */     public class $anonfun$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(int x$7) {
/*     */         return apply$mcZI$sp(x$7);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZI$sp(int x$7) {
/*     */         return (x$7 > 0);
/*     */       }
/*     */       
/*     */       public $anonfun$1(TcpExt.Settings $outer) {}
/*     */     }
/*     */     
/*     */     public class $anonfun$7 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*     */         return "nr-of-selectors must be > 0";
/*     */       }
/*     */       
/*     */       public $anonfun$7(TcpExt.Settings $outer) {}
/*     */     }
/*     */     
/*     */     public int BatchAcceptLimit() {
/*     */       return this.BatchAcceptLimit;
/*     */     }
/*     */     
/*     */     public class $anonfun$2 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(int x$8) {
/*     */         return apply$mcZI$sp(x$8);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZI$sp(int x$8) {
/*     */         return (x$8 > 0);
/*     */       }
/*     */       
/*     */       public $anonfun$2(TcpExt.Settings $outer) {}
/*     */     }
/*     */     
/*     */     public class $anonfun$8 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*     */         return "batch-accept-limit must be > 0";
/*     */       }
/*     */       
/*     */       public $anonfun$8(TcpExt.Settings $outer) {}
/*     */     }
/*     */     
/*     */     public int DirectBufferSize() {
/*     */       return this.DirectBufferSize;
/*     */     }
/*     */     
/*     */     public int MaxDirectBufferPoolSize() {
/*     */       return this.MaxDirectBufferPoolSize;
/*     */     }
/*     */     
/*     */     public Duration RegisterTimeout() {
/*     */       return this.RegisterTimeout;
/*     */     }
/*     */     
/*     */     public int ReceivedMessageSizeLimit() {
/*     */       return this.ReceivedMessageSizeLimit;
/*     */     }
/*     */     
/*     */     public String ManagementDispatcher() {
/*     */       return this.ManagementDispatcher;
/*     */     }
/*     */     
/*     */     public String FileIODispatcher() {
/*     */       return this.FileIODispatcher;
/*     */     }
/*     */     
/*     */     public int TransferToLimit() {
/*     */       return this.TransferToLimit;
/*     */     }
/*     */     
/*     */     public int MaxChannelsPerSelector() {
/*     */       return this.MaxChannelsPerSelector;
/*     */     }
/*     */     
/*     */     public int FinishConnectRetries() {
/*     */       return this.FinishConnectRetries;
/*     */     }
/*     */     
/*     */     public class $anonfun$3 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(int x$9) {
/*     */         return apply$mcZI$sp(x$9);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZI$sp(int x$9) {
/*     */         return (x$9 > 0);
/*     */       }
/*     */       
/*     */       public $anonfun$3(TcpExt.Settings $outer) {}
/*     */     }
/*     */     
/*     */     public class $anonfun$9 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*     */         return "finish-connect-retries must be > 0";
/*     */       }
/*     */       
/*     */       public $anonfun$9(TcpExt.Settings $outer) {}
/*     */     }
/*     */     
/*     */     private int getIntBytes(String path) {
/*     */       Long size = this._config.getBytes(path);
/*     */       Predef$.MODULE$.require((Predef$.MODULE$.Long2long(size) < 2147483647L), (Function0)new TcpExt$Settings$$anonfun$getIntBytes$1(this, path));
/*     */       Predef$.MODULE$.require((Predef$.MODULE$.Long2long(size) >= 0L), (Function0)new TcpExt$Settings$$anonfun$getIntBytes$2(this, path));
/*     */       return (int)Predef$.MODULE$.Long2long(size);
/*     */     }
/*     */     
/*     */     public class TcpExt$Settings$$anonfun$getIntBytes$1 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String path$1;
/*     */       
/*     */       public final String apply() {
/*     */         (new String[2])[0] = "";
/*     */         (new String[2])[1] = " must be < 2 GiB";
/*     */         return (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this.path$1 }));
/*     */       }
/*     */       
/*     */       public TcpExt$Settings$$anonfun$getIntBytes$1(TcpExt.Settings $outer, String path$1) {}
/*     */     }
/*     */     
/*     */     public class TcpExt$Settings$$anonfun$getIntBytes$2 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String path$1;
/*     */       
/*     */       public final String apply() {
/*     */         (new String[2])[0] = "";
/*     */         (new String[2])[1] = " must be non-negative";
/*     */         return (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this.path$1 }));
/*     */       }
/*     */       
/*     */       public TcpExt$Settings$$anonfun$getIntBytes$2(TcpExt.Settings $outer, String path$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public ActorRef manager() {
/*     */     return this.manager;
/*     */   }
/*     */   
/*     */   public ActorRef getManager() {
/*     */     return manager();
/*     */   }
/*     */   
/*     */   public BufferPool bufferPool() {
/*     */     return this.bufferPool;
/*     */   }
/*     */   
/*     */   public MessageDispatcher fileIoDispatcher() {
/* 570 */     return this.fileIoDispatcher;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpExt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */