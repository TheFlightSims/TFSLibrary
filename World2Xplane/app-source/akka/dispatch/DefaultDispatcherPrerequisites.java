/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.DynamicAccess;
/*    */ import akka.actor.Scheduler;
/*    */ import akka.event.EventStream;
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple7;
/*    */ import scala.collection.Iterator;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Eh!B\001\003\001\0221!A\b#fM\006,H\016\036#jgB\fGo\0315feB\023XM]3rk&\034\030\016^3t\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034R\001A\004\016#Q\001\"\001C\006\016\003%Q\021AC\001\006g\016\fG.Y\005\003\031%\021a!\0218z%\0264\007C\001\b\020\033\005\021\021B\001\t\003\005]!\025n\0359bi\016DWM\035)sKJ,\027/^5tSR,7\017\005\002\t%%\0211#\003\002\b!J|G-^2u!\tAQ#\003\002\027\023\ta1+\032:jC2L'0\0312mK\"A\001\004\001BK\002\023\005!$A\007uQJ,\027\r\032$bGR|'/_\002\001+\005Y\002C\001\017$\033\005i\"B\001\020 \003)\031wN\\2veJ,g\016\036\006\003A\005\nA!\036;jY*\t!%\001\003kCZ\f\027B\001\023\036\0055!\006N]3bI\032\0137\r^8ss\"Aa\005\001B\tB\003%1$\001\buQJ,\027\r\032$bGR|'/\037\021\t\021!\002!Q3A\005\002%\n1\"\032<f]R\034FO]3b[V\t!\006\005\002,]5\tAF\003\002.\t\005)QM^3oi&\021q\006\f\002\f\013Z,g\016^*ue\026\fW\016\003\0052\001\tE\t\025!\003+\0031)g/\0328u'R\024X-Y7!\021!\031\004A!f\001\n\003!\024!C:dQ\026$W\017\\3s+\005)\004C\001\034:\033\0059$B\001\035\005\003\025\t7\r^8s\023\tQtGA\005TG\",G-\0367fe\"AA\b\001B\tB\003%Q'\001\006tG\",G-\0367fe\002B\001B\020\001\003\026\004%\taP\001\016Ift\027-\\5d\003\016\034Wm]:\026\003\001\003\"AN!\n\005\t;$!\004#z]\006l\027nY!dG\026\0348\017\003\005E\001\tE\t\025!\003A\0039!\027P\\1nS\016\f5mY3tg\002B\001B\022\001\003\026\004%\taR\001\tg\026$H/\0338hgV\t\001\n\005\002J\031:\021aGS\005\003\027^\n1\"Q2u_J\034\026p\035;f[&\021QJ\024\002\t'\026$H/\0338hg*\0211j\016\005\t!\002\021\t\022)A\005\021\006I1/\032;uS:<7\017\t\005\t%\002\021)\032!C\001'\006IQ.Y5mE>DXm]\013\002)B\021a\"V\005\003-\n\021\021\"T1jY\n|\0070Z:\t\021a\003!\021#Q\001\nQ\013!\"\\1jY\n|\0070Z:!\021!Q\006A!f\001\n\003Y\026a\0063fM\006,H\016^#yK\016,H/[8o\007>tG/\032=u+\005a\006c\001\005^?&\021a,\003\002\007\037B$\030n\0348\021\005\001\024W\"A1\013\005yI\021BA2b\005A)\0050Z2vi&|gnQ8oi\026DH\017\003\005f\001\tE\t\025!\003]\003a!WMZ1vYR,\0050Z2vi&|gnQ8oi\026DH\017\t\005\006O\002!\t\001[\001\007y%t\027\016\036 \025\021%T7\016\\7o_B\004\"A\004\001\t\013a1\007\031A\016\t\013!2\007\031\001\026\t\013M2\007\031A\033\t\013y2\007\031\001!\t\013\0313\007\031\001%\t\013I3\007\031\001+\t\013i3\007\031\001/\t\017I\004\021\021!C\001g\006!1m\0349z)!IG/\036<xqfT\bb\002\rr!\003\005\ra\007\005\bQE\004\n\0211\001+\021\035\031\024\017%AA\002UBqAP9\021\002\003\007\001\tC\004GcB\005\t\031\001%\t\017I\013\b\023!a\001)\"9!,\035I\001\002\004a\006b\002?\001#\003%\t!`\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005q(FA\016\000W\t\t\t\001\005\003\002\004\0055QBAA\003\025\021\t9!!\003\002\023Ut7\r[3dW\026$'bAA\006\023\005Q\021M\0348pi\006$\030n\0348\n\t\005=\021Q\001\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007\"CA\n\001E\005I\021AA\013\0039\031w\016]=%I\0264\027-\0367uII*\"!a\006+\005)z\b\"CA\016\001E\005I\021AA\017\0039\031w\016]=%I\0264\027-\0367uIM*\"!a\b+\005Uz\b\"CA\022\001E\005I\021AA\023\0039\031w\016]=%I\0264\027-\0367uIQ*\"!a\n+\005\001{\b\"CA\026\001E\005I\021AA\027\0039\031w\016]=%I\0264\027-\0367uIU*\"!a\f+\005!{\b\"CA\032\001E\005I\021AA\033\0039\031w\016]=%I\0264\027-\0367uIY*\"!a\016+\005Q{\b\"CA\036\001E\005I\021AA\037\0039\031w\016]=%I\0264\027-\0367uI]*\"!a\020+\005q{\b\"CA\"\001\005\005I\021IA#\0035\001(o\0343vGR\004&/\0324jqV\021\021q\t\t\005\003\023\ny%\004\002\002L)\031\021QJ\021\002\t1\fgnZ\005\005\003#\nYE\001\004TiJLgn\032\005\n\003+\002\021\021!C\001\003/\nA\002\035:pIV\034G/\021:jif,\"!!\027\021\007!\tY&C\002\002^%\0211!\0238u\021%\t\t\007AA\001\n\003\t\031'\001\bqe>$Wo\031;FY\026lWM\034;\025\t\005\025\0241\016\t\004\021\005\035\024bAA5\023\t\031\021I\\=\t\025\0055\024qLA\001\002\004\tI&A\002yIEB\021\"!\035\001\003\003%\t%a\035\002\037A\024x\016Z;di&#XM]1u_J,\"!!\036\021\r\005]\024QPA3\033\t\tIHC\002\002|%\t!bY8mY\026\034G/[8o\023\021\ty(!\037\003\021%#XM]1u_JD\021\"a!\001\003\003%\t!!\"\002\021\r\fg.R9vC2$B!a\"\002\016B\031\001\"!#\n\007\005-\025BA\004C_>dW-\0318\t\025\0055\024\021QA\001\002\004\t)\007C\005\002\022\002\t\t\021\"\021\002\024\006A\001.Y:i\007>$W\r\006\002\002Z!I\021q\023\001\002\002\023\005\023\021T\001\ti>\034FO]5oOR\021\021q\t\005\n\003;\003\021\021!C!\003?\013a!Z9vC2\034H\003BAD\003CC!\"!\034\002\034\006\005\t\031AA3\017)\t)KAA\001\022\003!\021qU\001\037\t\0264\027-\0367u\t&\034\b/\031;dQ\026\024\bK]3sKF,\030n]5uKN\0042ADAU\r%\t!!!A\t\002\021\tYkE\003\002*\0065F\003\005\007\0020\006U6DK\033A\021Rc\026.\004\002\0022*\031\0211W\005\002\017I,h\016^5nK&!\021qWAY\005E\t%m\035;sC\016$h)\0368di&|gn\016\005\bO\006%F\021AA^)\t\t9\013\003\006\002\030\006%\026\021!C#\0033C!\"!1\002*\006\005I\021QAb\003\025\t\007\017\0357z)=I\027QYAd\003\023\fY-!4\002P\006E\007B\002\r\002@\002\0071\004\003\004)\003\003\rA\013\005\007g\005}\006\031A\033\t\ry\ny\f1\001A\021\0311\025q\030a\001\021\"1!+a0A\002QCaAWA`\001\004a\006BCAk\003S\013\t\021\"!\002X\0069QO\\1qa2LH\003BAm\003C\004B\001C/\002\\BQ\001\"!8\034UU\002\005\n\026/\n\007\005}\027B\001\004UkBdWm\016\005\n\003G\f\031.!AA\002%\f1\001\037\0231\021)\t9/!+\002\002\023%\021\021^\001\fe\026\fGMU3t_24X\r\006\002\002lB!\021\021JAw\023\021\ty/a\023\003\r=\023'.Z2u\001")
/*    */ public class DefaultDispatcherPrerequisites implements DispatcherPrerequisites, Product, Serializable {
/*    */   private final ThreadFactory threadFactory;
/*    */   
/*    */   private final EventStream eventStream;
/*    */   
/*    */   private final Scheduler scheduler;
/*    */   
/*    */   private final DynamicAccess dynamicAccess;
/*    */   
/*    */   private final ActorSystem.Settings settings;
/*    */   
/*    */   private final Mailboxes mailboxes;
/*    */   
/*    */   private final Option<ExecutionContext> defaultExecutionContext;
/*    */   
/*    */   public DefaultDispatcherPrerequisites copy(ThreadFactory threadFactory, EventStream eventStream, Scheduler scheduler, DynamicAccess dynamicAccess, ActorSystem.Settings settings, Mailboxes mailboxes, Option<ExecutionContext> defaultExecutionContext) {
/* 34 */     return new DefaultDispatcherPrerequisites(
/* 35 */         threadFactory, 
/* 36 */         eventStream, 
/* 37 */         scheduler, 
/* 38 */         dynamicAccess, 
/* 39 */         settings, 
/* 40 */         mailboxes, 
/* 41 */         defaultExecutionContext);
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/*    */     return "DefaultDispatcherPrerequisites";
/*    */   }
/*    */   
/*    */   public int productArity() {
/*    */     return 7;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/*    */     int i = x$1;
/*    */     switch (i) {
/*    */       default:
/*    */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 6:
/*    */       
/*    */       case 5:
/*    */       
/*    */       case 4:
/*    */       
/*    */       case 3:
/*    */       
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/*    */     return threadFactory();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/*    */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/*    */     return x$1 instanceof DefaultDispatcherPrerequisites;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/*    */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/*    */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 272
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/dispatch/DefaultDispatcherPrerequisites
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 276
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/dispatch/DefaultDispatcherPrerequisites
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual threadFactory : ()Ljava/util/concurrent/ThreadFactory;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual threadFactory : ()Ljava/util/concurrent/ThreadFactory;
/*    */     //   40: astore #5
/*    */     //   42: dup
/*    */     //   43: ifnonnull -> 55
/*    */     //   46: pop
/*    */     //   47: aload #5
/*    */     //   49: ifnull -> 63
/*    */     //   52: goto -> 268
/*    */     //   55: aload #5
/*    */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   60: ifeq -> 268
/*    */     //   63: aload_0
/*    */     //   64: invokevirtual eventStream : ()Lakka/event/EventStream;
/*    */     //   67: aload #4
/*    */     //   69: invokevirtual eventStream : ()Lakka/event/EventStream;
/*    */     //   72: astore #6
/*    */     //   74: dup
/*    */     //   75: ifnonnull -> 87
/*    */     //   78: pop
/*    */     //   79: aload #6
/*    */     //   81: ifnull -> 95
/*    */     //   84: goto -> 268
/*    */     //   87: aload #6
/*    */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   92: ifeq -> 268
/*    */     //   95: aload_0
/*    */     //   96: invokevirtual scheduler : ()Lakka/actor/Scheduler;
/*    */     //   99: aload #4
/*    */     //   101: invokevirtual scheduler : ()Lakka/actor/Scheduler;
/*    */     //   104: astore #7
/*    */     //   106: dup
/*    */     //   107: ifnonnull -> 119
/*    */     //   110: pop
/*    */     //   111: aload #7
/*    */     //   113: ifnull -> 127
/*    */     //   116: goto -> 268
/*    */     //   119: aload #7
/*    */     //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   124: ifeq -> 268
/*    */     //   127: aload_0
/*    */     //   128: invokevirtual dynamicAccess : ()Lakka/actor/DynamicAccess;
/*    */     //   131: aload #4
/*    */     //   133: invokevirtual dynamicAccess : ()Lakka/actor/DynamicAccess;
/*    */     //   136: astore #8
/*    */     //   138: dup
/*    */     //   139: ifnonnull -> 151
/*    */     //   142: pop
/*    */     //   143: aload #8
/*    */     //   145: ifnull -> 159
/*    */     //   148: goto -> 268
/*    */     //   151: aload #8
/*    */     //   153: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   156: ifeq -> 268
/*    */     //   159: aload_0
/*    */     //   160: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*    */     //   163: aload #4
/*    */     //   165: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*    */     //   168: astore #9
/*    */     //   170: dup
/*    */     //   171: ifnonnull -> 183
/*    */     //   174: pop
/*    */     //   175: aload #9
/*    */     //   177: ifnull -> 191
/*    */     //   180: goto -> 268
/*    */     //   183: aload #9
/*    */     //   185: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   188: ifeq -> 268
/*    */     //   191: aload_0
/*    */     //   192: invokevirtual mailboxes : ()Lakka/dispatch/Mailboxes;
/*    */     //   195: aload #4
/*    */     //   197: invokevirtual mailboxes : ()Lakka/dispatch/Mailboxes;
/*    */     //   200: astore #10
/*    */     //   202: dup
/*    */     //   203: ifnonnull -> 215
/*    */     //   206: pop
/*    */     //   207: aload #10
/*    */     //   209: ifnull -> 223
/*    */     //   212: goto -> 268
/*    */     //   215: aload #10
/*    */     //   217: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   220: ifeq -> 268
/*    */     //   223: aload_0
/*    */     //   224: invokevirtual defaultExecutionContext : ()Lscala/Option;
/*    */     //   227: aload #4
/*    */     //   229: invokevirtual defaultExecutionContext : ()Lscala/Option;
/*    */     //   232: astore #11
/*    */     //   234: dup
/*    */     //   235: ifnonnull -> 247
/*    */     //   238: pop
/*    */     //   239: aload #11
/*    */     //   241: ifnull -> 255
/*    */     //   244: goto -> 268
/*    */     //   247: aload #11
/*    */     //   249: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   252: ifeq -> 268
/*    */     //   255: aload #4
/*    */     //   257: aload_0
/*    */     //   258: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   261: ifeq -> 268
/*    */     //   264: iconst_1
/*    */     //   265: goto -> 269
/*    */     //   268: iconst_0
/*    */     //   269: ifeq -> 276
/*    */     //   272: iconst_1
/*    */     //   273: goto -> 277
/*    */     //   276: iconst_0
/*    */     //   277: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #34	-> 0
/*    */     //   #63	-> 14
/*    */     //   #34	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	278	0	this	Lakka/dispatch/DefaultDispatcherPrerequisites;
/*    */     //   0	278	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ThreadFactory threadFactory() {
/*    */     return this.threadFactory;
/*    */   }
/*    */   
/*    */   public ThreadFactory copy$default$1() {
/*    */     return threadFactory();
/*    */   }
/*    */   
/*    */   public DefaultDispatcherPrerequisites(ThreadFactory threadFactory, EventStream eventStream, Scheduler scheduler, DynamicAccess dynamicAccess, ActorSystem.Settings settings, Mailboxes mailboxes, Option<ExecutionContext> defaultExecutionContext) {
/*    */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public EventStream eventStream() {
/*    */     return this.eventStream;
/*    */   }
/*    */   
/*    */   public EventStream copy$default$2() {
/*    */     return eventStream();
/*    */   }
/*    */   
/*    */   public Scheduler scheduler() {
/*    */     return this.scheduler;
/*    */   }
/*    */   
/*    */   public Scheduler copy$default$3() {
/*    */     return scheduler();
/*    */   }
/*    */   
/*    */   public DynamicAccess dynamicAccess() {
/*    */     return this.dynamicAccess;
/*    */   }
/*    */   
/*    */   public DynamicAccess copy$default$4() {
/*    */     return dynamicAccess();
/*    */   }
/*    */   
/*    */   public ActorSystem.Settings settings() {
/*    */     return this.settings;
/*    */   }
/*    */   
/*    */   public ActorSystem.Settings copy$default$5() {
/*    */     return settings();
/*    */   }
/*    */   
/*    */   public Mailboxes mailboxes() {
/*    */     return this.mailboxes;
/*    */   }
/*    */   
/*    */   public Mailboxes copy$default$6() {
/*    */     return mailboxes();
/*    */   }
/*    */   
/*    */   public Option<ExecutionContext> defaultExecutionContext() {
/* 41 */     return this.defaultExecutionContext;
/*    */   }
/*    */   
/*    */   public Option<ExecutionContext> copy$default$7() {
/* 41 */     return defaultExecutionContext();
/*    */   }
/*    */   
/*    */   public static Function1<Tuple7<ThreadFactory, EventStream, Scheduler, DynamicAccess, ActorSystem.Settings, Mailboxes, Option<ExecutionContext>>, DefaultDispatcherPrerequisites> tupled() {
/*    */     return DefaultDispatcherPrerequisites$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<ThreadFactory, Function1<EventStream, Function1<Scheduler, Function1<DynamicAccess, Function1<ActorSystem.Settings, Function1<Mailboxes, Function1<Option<ExecutionContext>, DefaultDispatcherPrerequisites>>>>>>> curried() {
/*    */     return DefaultDispatcherPrerequisites$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\DefaultDispatcherPrerequisites.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */