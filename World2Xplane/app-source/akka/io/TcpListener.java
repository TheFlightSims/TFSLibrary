/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorLogging;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.NoSerializationVerificationNeeded;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.dispatch.RequiresMessageQueue;
/*     */ import akka.dispatch.UnboundedMessageQueueSemantics;
/*     */ import akka.event.LoggingAdapter;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.channels.ServerSocketChannel;
/*     */ import java.nio.channels.SocketChannel;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\025uAB\001\003\021\003\021a!A\006UGBd\025n\035;f]\026\024(BA\002\005\003\tIwNC\001\006\003\021\t7n[1\021\005\035AQ\"\001\002\007\r%\021\001\022\001\002\013\005-!6\r\035'jgR,g.\032:\024\005!Y\001C\001\007\020\033\005i!\"\001\b\002\013M\034\027\r\\1\n\005Ai!AB!osJ+g\rC\003\023\021\021\005A#\001\004=S:LGOP\002\001)\0051a\001\002\f\t\001^\021\001CU3hSN$XM]%oG>l\027N\\4\024\rUY\001D\n\0270!\tI2E\004\002\033C9\0211\004\t\b\0039}i\021!\b\006\003=M\ta\001\020:p_Rt\024\"A\003\n\005\r!\021B\001\022\003\003A\031V\r\\3di&|g\016S1oI2,'/\003\002%K\t\t\002*Y:GC&dWO]3NKN\034\030mZ3\013\005\t\022\001CA\024+\033\005A#BA\025\005\003\025\t7\r^8s\023\tY\003FA\021O_N+'/[1mSj\fG/[8o-\026\024\030NZ5dCRLwN\034(fK\022,G\r\005\002\r[%\021a&\004\002\b!J|G-^2u!\ta\001'\003\0022\033\ta1+\032:jC2L'0\0312mK\"A1'\006BK\002\023\005A'A\004dQ\006tg.\0327\026\003U\002\"AN\037\016\003]R!\001O\035\002\021\rD\027M\0348fYNT!AO\036\002\0079LwNC\001=\003\021Q\027M^1\n\005y:$!D*pG.,Go\0215b]:,G\016\003\005A+\tE\t\025!\0036\003!\031\007.\0318oK2\004\003\"\002\n\026\t\003\021ECA\"F!\t!U#D\001\t\021\025\031\024\t1\0016\021\0259U\003\"\001I\00391\027-\0337ve\026lUm]:bO\026,\022!\023\t\003\t*3Aa\023\005A\031\n1b)Y5mK\022\024VmZ5ti\026\024\030J\\2p[&twmE\003K\027\031bs\006\003\0054\025\nU\r\021\"\0015\021!\001%J!E!\002\023)\004\"\002\nK\t\003\001FCA%R\021\025\031t\n1\0016\021\035\031&*!A\005\002Q\013AaY8qsR\021\021*\026\005\bgI\003\n\0211\0016\021\0359&*%A\005\002a\013abY8qs\022\"WMZ1vYR$\023'F\001ZU\t)$lK\001\\!\ta\026-D\001^\025\tqv,A\005v]\016DWmY6fI*\021\001-D\001\013C:tw\016^1uS>t\027B\0012^\005E)hn\0315fG.,GMV1sS\006t7-\032\005\bI*\013\t\021\"\021f\0035\001(o\0343vGR\004&/\0324jqV\ta\r\005\002hU6\t\001N\003\002jw\005!A.\0318h\023\tY\007N\001\004TiJLgn\032\005\b[*\013\t\021\"\001o\0031\001(o\0343vGR\f%/\033;z+\005y\007C\001\007q\023\t\tXBA\002J]RDqa\035&\002\002\023\005A/\001\bqe>$Wo\031;FY\026lWM\034;\025\005UD\bC\001\007w\023\t9XBA\002B]fDq!\037:\002\002\003\007q.A\002yIEBqa\037&\002\002\023\005C0A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005i\b\003\002@\002\004Ul\021a \006\004\003\003i\021AC2pY2,7\r^5p]&\031\021QA@\003\021%#XM]1u_JD\021\"!\003K\003\003%\t!a\003\002\021\r\fg.R9vC2$B!!\004\002\024A\031A\"a\004\n\007\005EQBA\004C_>dW-\0318\t\021e\f9!!AA\002UD\021\"a\006K\003\003%\t%!\007\002\021!\f7\017[\"pI\026$\022a\034\005\n\003;Q\025\021!C!\003?\t\001\002^8TiJLgn\032\013\002M\"I\0211\005&\002\002\023\005\023QE\001\007KF,\030\r\\:\025\t\0055\021q\005\005\ts\006\005\022\021!a\001k\"A1+FA\001\n\003\tY\003F\002D\003[A\001bMA\025!\003\005\r!\016\005\b/V\t\n\021\"\001Y\021\035!W#!A\005B\025Dq!\\\013\002\002\023\005a\016\003\005t+\005\005I\021AA\034)\r)\030\021\b\005\ts\006U\022\021!a\001_\"910FA\001\n\003b\b\"CA\005+\005\005I\021AA )\021\ti!!\021\t\021e\fi$!AA\002UD\021\"a\006\026\003\003%\t%!\007\t\023\005uQ#!A\005B\005}\001\"CA\022+\005\005I\021IA%)\021\ti!a\023\t\021e\f9%!AA\002U<\021\"a\024\t\003\003E\t!!\025\002!I+w-[:uKJLenY8nS:<\007c\001#\002T\031Aa\003CA\001\022\003\t)fE\003\002T\005]s\006\005\004\002Z\005}SgQ\007\003\0037R1!!\030\016\003\035\021XO\034;j[\026LA!!\031\002\\\t\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\017I\t\031\006\"\001\002fQ\021\021\021\013\005\013\003;\t\031&!A\005F\005}\001BCA6\003'\n\t\021\"!\002n\005)\021\r\0359msR\0311)a\034\t\rM\nI\0071\0016\021)\t\031(a\025\002\002\023\005\025QO\001\bk:\f\007\017\0357z)\021\t9(! \021\t1\tI(N\005\004\003wj!AB(qi&|g\016C\005\002\000\005E\024\021!a\001\007\006\031\001\020\n\031\t\025\005\r\0251KA\001\n\023\t))A\006sK\006$'+Z:pYZ,GCAAD!\r9\027\021R\005\004\003\027C'AB(cU\026\034GoB\005\002\020\"\t\t\021#\001\002\022\0061b)Y5mK\022\024VmZ5ti\026\024\030J\\2p[&tw\rE\002E\003'3\001b\023\005\002\002#\005\021QS\n\006\003'\0139j\f\t\007\0033\ny&N%\t\017I\t\031\n\"\001\002\034R\021\021\021\023\005\013\003;\t\031*!A\005F\005}\001BCA6\003'\013\t\021\"!\002\"R\031\021*a)\t\rM\ny\n1\0016\021)\t\031(a%\002\002\023\005\025q\025\013\005\003o\nI\013C\005\002\000\005\025\026\021!a\001\023\"Q\0211QAJ\003\003%I!!\"\007\r%\021\001AAAX'%\tikCAY\003o\013i\fE\002(\003gK1!!.)\005\025\t5\r^8s!\r9\023\021X\005\004\003wC#\001D!di>\024Hj\\4hS:<\007CBA`\003\013\fI-\004\002\002B*\031\0211\031\003\002\021\021L7\017]1uG\"LA!a2\002B\n!\"+Z9vSJ,7/T3tg\006<W-U;fk\026\004B!a0\002L&!\021QZAa\005y)fNY8v]\022,G-T3tg\006<W-U;fk\026\034V-\\1oi&\0347\017C\006\002R\0065&\021!Q\001\n\005M\027AD:fY\026\034Go\034:S_V$XM\035\t\004O\005U\027bAAlQ\tA\021i\031;peJ+g\rC\006\002\\\0065&\021!Q\001\n\005u\027a\001;daB\031q!a8\n\007\005\005(A\001\004UGB,\005\020\036\005\f\003K\fiK!A!\002\023\t9/A\bdQ\006tg.\0327SK\036L7\017\036:z!\r9\021\021^\005\004\003W\024!aD\"iC:tW\r\034*fO&\034HO]=\t\027\005=\030Q\026B\001B\003%\0211[\001\016E&tGmQ8n[\006tG-\032:\t\027\005M\030Q\026B\001B\003%\021Q_\001\005E&tG\r\005\003\002x\006uhb\001\016\002z&\031\0211 \002\002\007Q\033\007/\003\003\002\000\n\005!\001\002\"j]\022T1!a?\003\021\035\021\022Q\026C\001\005\013!BBa\002\003\n\t-!Q\002B\b\005#\0012aBAW\021!\t\tNa\001A\002\005M\007\002CAn\005\007\001\r!!8\t\021\005\025(1\001a\001\003OD\001\"a<\003\004\001\007\0211\033\005\t\003g\024\031\0011\001\002v\"I1'!,C\002\023\005!QC\013\003\005/\0012A\016B\r\023\r\021Yb\016\002\024'\026\024h/\032:T_\016\\W\r^\"iC:tW\r\034\005\t\001\0065\006\025!\003\003\030!I!\021EAW\001\004%\tA\\\001\fC\016\034W\r\035;MS6LG\017\003\006\003&\0055\006\031!C\001\005O\tq\"Y2dKB$H*[7ji~#S-\035\013\005\005S\021y\003E\002\r\005WI1A!\f\016\005\021)f.\033;\t\021e\024\031#!AA\002=D\001Ba\r\002.\002\006Ka\\\001\rC\016\034W\r\035;MS6LG\017\t\005\013\005o\tiK1A\005\002\te\022\001\0047pG\006d\027\t\0323sKN\034X#A;\t\021\tu\022Q\026Q\001\nU\fQ\002\\8dC2\fE\r\032:fgN\004\003\002\003B!\003[#\tEa\021\002%M,\b/\032:wSN|'o\025;sCR,w-_\013\003\005\013\0022a\nB$\023\r\021I\005\013\002\023'V\004XM\035<jg>\0248\013\036:bi\026<\027\020\003\005\003N\0055F\021\001B(\003\035\021XmY3jm\026,\"A!\025\021\t\tM#QK\007\003\003[KAAa\026\0024\n9!+Z2fSZ,\007\002\003B.\003[#\tA!\030\002\013\t|WO\0343\025\t\tE#q\f\005\t\005C\022I\0061\001\003d\005a!/Z4jgR\024\030\r^5p]B\031qA!\032\n\007\t\035$AA\nDQ\006tg.\0327SK\036L7\017\036:bi&|g\016\003\005\003l\0055FQ\001B7\003A\t7mY3qi\006cG\016U3oI&tw\rF\003p\005_\022\t\b\003\005\003b\t%\004\031\001B2\021\035\021\031H!\033A\002=\fQ\001\\5nSRDCA!\033\003xA!!\021\020B>\033\005y\026b\001B??\n9A/Y5me\026\034\007\002\003BA\003[#\tEa!\002\021A|7\017^*u_B$\"A!\013")
/*     */ public class TcpListener implements Actor, ActorLogging, RequiresMessageQueue<UnboundedMessageQueueSemantics> {
/*     */   private final ActorRef selectorRouter;
/*     */   
/*     */   private final TcpExt tcp;
/*     */   
/*     */   private final ChannelRegistry channelRegistry;
/*     */   
/*     */   public final ActorRef akka$io$TcpListener$$bindCommander;
/*     */   
/*     */   private final Tcp.Bind bind;
/*     */   
/*     */   private final ServerSocketChannel channel;
/*     */   
/*     */   private int acceptLimit;
/*     */   
/*     */   private final Object localAddress;
/*     */   
/*     */   private LoggingAdapter akka$actor$ActorLogging$$_log;
/*     */   
/*     */   private final ActorContext context;
/*     */   
/*     */   private final ActorRef self;
/*     */   
/*     */   public static class RegisterIncoming implements SelectionHandler.HasFailureMessage, NoSerializationVerificationNeeded, Product, Serializable {
/*     */     private final SocketChannel channel;
/*     */     
/*     */     public SocketChannel channel() {
/*  21 */       return this.channel;
/*     */     }
/*     */     
/*     */     public RegisterIncoming copy(SocketChannel channel) {
/*  21 */       return new RegisterIncoming(channel);
/*     */     }
/*     */     
/*     */     public SocketChannel copy$default$1() {
/*  21 */       return channel();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  21 */       return "RegisterIncoming";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  21 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  21 */       int i = x$1;
/*  21 */       switch (i) {
/*     */         default:
/*  21 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  21 */       return channel();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  21 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  21 */       return x$1 instanceof RegisterIncoming;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  21 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  21 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 80
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/io/TcpListener$RegisterIncoming
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 84
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/TcpListener$RegisterIncoming
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual channel : ()Ljava/nio/channels/SocketChannel;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual channel : ()Ljava/nio/channels/SocketChannel;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 76
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 76
/*     */       //   63: aload #4
/*     */       //   65: aload_0
/*     */       //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   69: ifeq -> 76
/*     */       //   72: iconst_1
/*     */       //   73: goto -> 77
/*     */       //   76: iconst_0
/*     */       //   77: ifeq -> 84
/*     */       //   80: iconst_1
/*     */       //   81: goto -> 85
/*     */       //   84: iconst_0
/*     */       //   85: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #21	-> 0
/*     */       //   #63	-> 14
/*     */       //   #21	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lakka/io/TcpListener$RegisterIncoming;
/*     */       //   0	86	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public RegisterIncoming(SocketChannel channel) {
/*  21 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public TcpListener.FailedRegisterIncoming failureMessage() {
/*  22 */       return new TcpListener.FailedRegisterIncoming(channel());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class RegisterIncoming$ extends AbstractFunction1<SocketChannel, RegisterIncoming> implements Serializable {
/*     */     public static final RegisterIncoming$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "RegisterIncoming";
/*     */     }
/*     */     
/*     */     public TcpListener.RegisterIncoming apply(SocketChannel channel) {
/*     */       return new TcpListener.RegisterIncoming(channel);
/*     */     }
/*     */     
/*     */     public Option<SocketChannel> unapply(TcpListener.RegisterIncoming x$0) {
/*     */       return (x$0 == null) ? (Option<SocketChannel>)scala.None$.MODULE$ : (Option<SocketChannel>)new Some(x$0.channel());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public RegisterIncoming$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class FailedRegisterIncoming implements NoSerializationVerificationNeeded, Product, Serializable {
/*     */     private final SocketChannel channel;
/*     */     
/*     */     public SocketChannel channel() {
/*  25 */       return this.channel;
/*     */     }
/*     */     
/*     */     public FailedRegisterIncoming copy(SocketChannel channel) {
/*  25 */       return new FailedRegisterIncoming(channel);
/*     */     }
/*     */     
/*     */     public SocketChannel copy$default$1() {
/*  25 */       return channel();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  25 */       return "FailedRegisterIncoming";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  25 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  25 */       int i = x$1;
/*  25 */       switch (i) {
/*     */         default:
/*  25 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  25 */       return channel();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  25 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  25 */       return x$1 instanceof FailedRegisterIncoming;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  25 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  25 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 80
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/io/TcpListener$FailedRegisterIncoming
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 84
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/TcpListener$FailedRegisterIncoming
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual channel : ()Ljava/nio/channels/SocketChannel;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual channel : ()Ljava/nio/channels/SocketChannel;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 76
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 76
/*     */       //   63: aload #4
/*     */       //   65: aload_0
/*     */       //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   69: ifeq -> 76
/*     */       //   72: iconst_1
/*     */       //   73: goto -> 77
/*     */       //   76: iconst_0
/*     */       //   77: ifeq -> 84
/*     */       //   80: iconst_1
/*     */       //   81: goto -> 85
/*     */       //   84: iconst_0
/*     */       //   85: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #25	-> 0
/*     */       //   #63	-> 14
/*     */       //   #25	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lakka/io/TcpListener$FailedRegisterIncoming;
/*     */       //   0	86	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public FailedRegisterIncoming(SocketChannel channel) {
/*  25 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class FailedRegisterIncoming$ extends AbstractFunction1<SocketChannel, FailedRegisterIncoming> implements Serializable {
/*     */     public static final FailedRegisterIncoming$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*  25 */       return "FailedRegisterIncoming";
/*     */     }
/*     */     
/*     */     public TcpListener.FailedRegisterIncoming apply(SocketChannel channel) {
/*  25 */       return new TcpListener.FailedRegisterIncoming(channel);
/*     */     }
/*     */     
/*     */     public Option<SocketChannel> unapply(TcpListener.FailedRegisterIncoming x$0) {
/*  25 */       return (x$0 == null) ? (Option<SocketChannel>)scala.None$.MODULE$ : (Option<SocketChannel>)new Some(x$0.channel());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  25 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public FailedRegisterIncoming$() {
/*  25 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public LoggingAdapter akka$actor$ActorLogging$$_log() {
/*  32 */     return this.akka$actor$ActorLogging$$_log;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/*  32 */     this.akka$actor$ActorLogging$$_log = x$1;
/*     */   }
/*     */   
/*     */   public LoggingAdapter log() {
/*  32 */     return ActorLogging.class.log(this);
/*     */   }
/*     */   
/*     */   public ActorContext context() {
/*  32 */     return this.context;
/*     */   }
/*     */   
/*     */   public final ActorRef self() {
/*  32 */     return this.self;
/*     */   }
/*     */   
/*     */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*  32 */     this.context = x$1;
/*     */   }
/*     */   
/*     */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*  32 */     this.self = x$1;
/*     */   }
/*     */   
/*     */   public final ActorRef sender() {
/*  32 */     return Actor.class.sender(this);
/*     */   }
/*     */   
/*     */   public void aroundReceive(PartialFunction receive, Object msg) {
/*  32 */     Actor.class.aroundReceive(this, receive, msg);
/*     */   }
/*     */   
/*     */   public void aroundPreStart() {
/*  32 */     Actor.class.aroundPreStart(this);
/*     */   }
/*     */   
/*     */   public void aroundPostStop() {
/*  32 */     Actor.class.aroundPostStop(this);
/*     */   }
/*     */   
/*     */   public void aroundPreRestart(Throwable reason, Option message) {
/*  32 */     Actor.class.aroundPreRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void aroundPostRestart(Throwable reason) {
/*  32 */     Actor.class.aroundPostRestart(this, reason);
/*     */   }
/*     */   
/*     */   public void preStart() throws Exception {
/*  32 */     Actor.class.preStart(this);
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable reason, Option message) throws Exception {
/*  32 */     Actor.class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void postRestart(Throwable reason) throws Exception {
/*  32 */     Actor.class.postRestart(this, reason);
/*     */   }
/*     */   
/*     */   public void unhandled(Object message) {
/*  32 */     Actor.class.unhandled(this, message);
/*     */   }
/*     */   
/*     */   public TcpListener(ActorRef selectorRouter, TcpExt tcp, ChannelRegistry channelRegistry, ActorRef bindCommander, Tcp.Bind bind) {
/*  32 */     Actor.class.$init$(this);
/*  32 */     ActorLogging.class.$init$(this);
/*  42 */     context().watch(bind.handler());
/*  44 */     this.channel = ServerSocketChannel.open();
/*  45 */     channel().configureBlocking(false);
/*  47 */     this.acceptLimit = bind.pullMode() ? 0 : tcp.Settings().BatchAcceptLimit();
/*  49 */     this.localAddress = 
/*  50 */       liftedTree1$1();
/*     */   }
/*     */   
/*     */   public ServerSocketChannel channel() {
/*     */     return this.channel;
/*     */   }
/*     */   
/*     */   public int acceptLimit() {
/*     */     return this.acceptLimit;
/*     */   }
/*     */   
/*     */   public void acceptLimit_$eq(int x$1) {
/*     */     this.acceptLimit = x$1;
/*     */   }
/*     */   
/*     */   public Object localAddress() {
/*     */     return this.localAddress;
/*     */   }
/*     */   
/*     */   private final Object liftedTree1$1() {
/*     */     try {
/*  51 */       ServerSocket socket = channel().socket();
/*  52 */       this.bind.options().foreach((Function1)new TcpListener$$anonfun$liftedTree1$1$1(this, socket));
/*  53 */       socket.bind(this.bind.localAddress(), this.bind.backlog());
/*  54 */       SocketAddress socketAddress = socket.getLocalSocketAddress();
/*  55 */       if (socketAddress instanceof InetSocketAddress) {
/*  55 */         InetSocketAddress inetSocketAddress1 = (InetSocketAddress)socketAddress, inetSocketAddress2 = inetSocketAddress1;
/*     */         InetSocketAddress ret = inetSocketAddress2;
/*     */       } 
/*  56 */       (new String[2])[0] = "bound to unknown SocketAddress [";
/*  56 */       (new String[2])[1] = "]";
/*  56 */       throw new IllegalArgumentException((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { socketAddress })));
/*     */     } finally {
/*     */       BoxedUnit boxedUnit;
/*     */       Exception exception1 = null, exception2 = exception1;
/*  62 */       Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/*  62 */       if (option.isEmpty())
/*     */         throw exception1; 
/*  62 */       Throwable e = (Throwable)option.get();
/*  63 */       akka.actor.package$.MODULE$.actorRef2Scala(this.akka$io$TcpListener$$bindCommander).$bang(this.bind.failureMessage(), self());
/*  64 */       log().debug("Bind failed for TCP channel on endpoint [{}]: {}", this.bind.localAddress(), e);
/*  65 */       context().stop(self());
/*     */     } 
/*     */     return boxedUnit;
/*     */   }
/*     */   
/*     */   public class TcpListener$$anonfun$liftedTree1$1$1 extends AbstractFunction1<Inet.SocketOption, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ServerSocket socket$1;
/*     */     
/*     */     public final void apply(Inet.SocketOption x$1) {
/*     */       x$1.beforeServerSocketBind(this.socket$1);
/*     */     }
/*     */     
/*     */     public TcpListener$$anonfun$liftedTree1$1$1(TcpListener $outer, ServerSocket socket$1) {}
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/*  68 */     return SelectionHandler$.MODULE$.connectionSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> receive() {
/*  70 */     return (PartialFunction<Object, BoxedUnit>)new TcpListener$$anonfun$receive$1(this);
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
/*     */   public PartialFunction<Object, BoxedUnit> bound(ChannelRegistration registration) {
/*  76 */     return (PartialFunction<Object, BoxedUnit>)new TcpListener$$anonfun$bound$1(this, registration);
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
/*     */   public final int acceptAllPending(ChannelRegistration registration, int limit) {
/*     */     // Byte code:
/*     */     //   0: iload_2
/*     */     //   1: iconst_0
/*     */     //   2: if_icmpgt -> 173
/*     */     //   5: goto -> 183
/*     */     //   8: astore #5
/*     */     //   10: aload #5
/*     */     //   12: astore #6
/*     */     //   14: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */     //   17: aload #6
/*     */     //   19: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */     //   22: astore #7
/*     */     //   24: aload #7
/*     */     //   26: invokevirtual isEmpty : ()Z
/*     */     //   29: ifeq -> 35
/*     */     //   32: aload #5
/*     */     //   34: athrow
/*     */     //   35: aload #7
/*     */     //   37: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   40: checkcast java/lang/Throwable
/*     */     //   43: astore #8
/*     */     //   45: aload_0
/*     */     //   46: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   49: aload #8
/*     */     //   51: ldc 'Accept error: could not accept new connection'
/*     */     //   53: invokeinterface error : (Ljava/lang/Throwable;Ljava/lang/String;)V
/*     */     //   58: aconst_null
/*     */     //   59: pop
/*     */     //   60: aconst_null
/*     */     //   61: astore #9
/*     */     //   63: aload #9
/*     */     //   65: astore #4
/*     */     //   67: aload #4
/*     */     //   69: ifnonnull -> 97
/*     */     //   72: aload_0
/*     */     //   73: getfield bind : Lakka/io/Tcp$Bind;
/*     */     //   76: invokevirtual pullMode : ()Z
/*     */     //   79: ifeq -> 86
/*     */     //   82: iload_2
/*     */     //   83: goto -> 96
/*     */     //   86: aload_0
/*     */     //   87: getfield tcp : Lakka/io/TcpExt;
/*     */     //   90: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */     //   93: invokevirtual BatchAcceptLimit : ()I
/*     */     //   96: ireturn
/*     */     //   97: aload_0
/*     */     //   98: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   101: ldc 'New connection accepted'
/*     */     //   103: invokeinterface debug : (Ljava/lang/String;)V
/*     */     //   108: aload #4
/*     */     //   110: iconst_0
/*     */     //   111: invokevirtual configureBlocking : (Z)Ljava/nio/channels/SelectableChannel;
/*     */     //   114: pop
/*     */     //   115: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */     //   118: aload_0
/*     */     //   119: getfield selectorRouter : Lakka/actor/ActorRef;
/*     */     //   122: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */     //   125: new akka/io/SelectionHandler$WorkerForCommand
/*     */     //   128: dup
/*     */     //   129: new akka/io/TcpListener$RegisterIncoming
/*     */     //   132: dup
/*     */     //   133: aload #4
/*     */     //   135: invokespecial <init> : (Ljava/nio/channels/SocketChannel;)V
/*     */     //   138: aload_0
/*     */     //   139: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */     //   142: new akka/io/TcpListener$$anonfun$acceptAllPending$1
/*     */     //   145: dup
/*     */     //   146: aload_0
/*     */     //   147: aload #4
/*     */     //   149: invokespecial <init> : (Lakka/io/TcpListener;Ljava/nio/channels/SocketChannel;)V
/*     */     //   152: invokespecial <init> : (Lakka/io/SelectionHandler$HasFailureMessage;Lakka/actor/ActorRef;Lscala/Function1;)V
/*     */     //   155: aload_0
/*     */     //   156: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */     //   159: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   164: aload_1
/*     */     //   165: iload_2
/*     */     //   166: iconst_1
/*     */     //   167: isub
/*     */     //   168: istore_2
/*     */     //   169: astore_1
/*     */     //   170: goto -> 0
/*     */     //   173: aload_0
/*     */     //   174: invokevirtual channel : ()Ljava/nio/channels/ServerSocketChannel;
/*     */     //   177: invokevirtual accept : ()Ljava/nio/channels/SocketChannel;
/*     */     //   180: goto -> 65
/*     */     //   183: aconst_null
/*     */     //   184: pop
/*     */     //   185: aconst_null
/*     */     //   186: goto -> 65
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #102	-> 0
/*     */     //   #103	-> 8
/*     */     //   #105	-> 14
/*     */     //   #103	-> 32
/*     */     //   #105	-> 37
/*     */     //   #103	-> 63
/*     */     //   #101	-> 65
/*     */     //   #108	-> 67
/*     */     //   #115	-> 72
/*     */     //   #100	-> 96
/*     */     //   #109	-> 97
/*     */     //   #110	-> 108
/*     */     //   #113	-> 115
/*     */     //   #114	-> 164
/*     */     //   #103	-> 173
/*     */     //   #107	-> 183
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	189	0	this	Lakka/io/TcpListener;
/*     */     //   0	189	1	registration	Lakka/io/ChannelRegistration;
/*     */     //   0	189	2	limit	I
/*     */     //   45	144	8	e	Ljava/lang/Throwable;
/*     */     //   67	122	4	socketChannel	Ljava/nio/channels/SocketChannel;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   173	183	8	finally
/*     */   }
/*     */   
/*     */   public final Props akka$io$TcpListener$$props$1(ChannelRegistry registry, SocketChannel socketChannel$1) {
/* 112 */     return akka.actor.Props$.MODULE$.apply(TcpIncomingConnection.class, (Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.tcp, socketChannel$1, registry, this.bind.handler(), this.bind.options(), BoxesRunTime.boxToBoolean(this.bind.pullMode()) }));
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
/*     */   
/*     */   public void postStop() {
/*     */     try {
/* 120 */       if (channel().isOpen()) {
/* 121 */         log().debug("Closing serverSocketChannel after being stopped");
/* 122 */         channel().close();
/*     */       } 
/*     */     } finally {
/*     */       BoxedUnit boxedUnit;
/*     */       Exception exception1 = null, exception2 = exception1;
/* 125 */       Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/* 125 */       if (option.isEmpty())
/*     */         throw exception1; 
/* 125 */       Throwable e = (Throwable)option.get();
/* 125 */       log().debug("Error closing ServerSocketChannel: {}", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */