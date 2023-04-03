/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.ConfigurationException;
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.DeadLetter;
/*     */ import akka.actor.DynamicAccess;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.ScalaActorRef;
/*     */ import akka.actor.package$;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.dispatch.sysmsg.SystemMessageList$;
/*     */ import akka.event.EventStream;
/*     */ import akka.util.Reflect$;
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigFactory;
/*     */ import com.typesafe.config.ConfigMergeable;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Predef$ArrowAssoc$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.JavaConverters$;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.Null$;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.VolatileByteRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tEr!B\001\003\021\0039\021!C'bS2\024w\016_3t\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051BA\005NC&d'm\034=fgN\021\021\002\004\t\003\033Ai\021A\004\006\002\037\005)1oY1mC&\021\021C\004\002\007\003:L(+\0324\t\013MIA\021\001\013\002\rqJg.\033;?)\0059\001b\002\f\n\005\004%)aF\001\021\t\0264\027-\0367u\033\006LGNY8y\023\022,\022\001G\b\0023\005\n!$\001\016bW.\fg&Y2u_JtC-\0324bk2$X&\\1jY\n|\007\020\003\004\035\023\001\006i\001G\001\022\t\0264\027-\0367u\033\006LGNY8y\023\022\004\003b\002\020\n\005\004%)aH\001\025\035>l\025-\0337c_b\024V-];je\026lWM\034;\026\003\001z\021!I\021\002E\005\001\001B\002\023\nA\0035\001%A\013O_6\013\027\016\0342pqJ+\027/^5sK6,g\016\036\021\007\013)\021\001\001\002\024\024\005\025b\001\002\003\025&\005\013\007I\021A\025\002\021M,G\017^5oON,\022A\013\t\003WEr!\001L\030\016\0035R!A\f\003\002\013\005\034Go\034:\n\005Aj\023aC!di>\0248+_:uK6L!AM\032\003\021M+G\017^5oONT!\001M\027\t\021U*#\021!Q\001\n)\n\021b]3ui&twm\035\021\t\021]*#Q1A\005\002a\n1\"\032<f]R\034FO]3b[V\t\021\b\005\002;{5\t1H\003\002=\t\005)QM^3oi&\021ah\017\002\f\013Z,g\016^*ue\026\fW\016\003\005AK\t\005\t\025!\003:\0031)g/\0328u'R\024X-Y7!\021!\021UE!A!\002\023\031\025!\0043z]\006l\027nY!dG\026\0348\017\005\002-\t&\021Q)\f\002\016\tft\027-\\5d\003\016\034Wm]:\t\021\035+#\021!Q\001\n!\0131\002Z3bI2+G\017^3sgB\021A&S\005\003\0256\022\001\"Q2u_J\024VM\032\005\006'\025\"\t\001\024\013\006\033:{\005+\025\t\003\021\025BQ\001K&A\002)BQaN&A\002eBQAQ&A\002\rCQaR&A\002!CqaU\023C\002\023\005A+A\teK\006$G*\032;uKJl\025-\0337c_b,\022!\026\t\003\021YK!a\026\002\003\0175\013\027\016\0342pq\"1\021,\nQ\001\nU\013!\003Z3bI2+G\017^3s\033\006LGNY8yA!91,\nb\001\n\023a\026\001G7bS2\024w\016\037+za\026\034uN\0344jOV\024\030\r^8sgV\tQ\f\005\003_K\036tW\"A0\013\005\001\f\027AC2p]\016,(O]3oi*\021!mY\001\005kRLGNC\001e\003\021Q\027M^1\n\005\031|&!E\"p]\016,(O]3oi\"\0137\017['baB\021\001n\033\b\003\033%L!A\033\b\002\rA\023X\rZ3g\023\taWN\001\004TiJLgn\032\006\003U:\001\"\001C8\n\005A\024!aC'bS2\024w\016\037+za\026DaA]\023!\002\023i\026!G7bS2\024w\016\037+za\026\034uN\0344jOV\024\030\r^8sg\002Bq\001^\023C\002\023%Q/A\bnC&d'm\034=CS:$\027N\\4t+\0051\b\003\0025xs\036L!\001_7\003\0075\013\007\017\r\002{B\031\001n_?\n\005ql'!B\"mCN\034\bC\001@\000\031\001!A\"!\001\002\004\005\005\t\021!B\001\003#\0211a\030\0232\021!\t)!\nQ\001\n\005\035\021\001E7bS2\024w\016\037\"j]\022LgnZ:!!\025Aw/!\003ha\021\tY!a\004\021\t!\\\030Q\002\t\004}\006=A\001DA\001\003\007\t\t\021!A\003\002\005E\021\003BA\n\0033\0012!DA\013\023\r\t9B\004\002\b\035>$\b.\0338h!\ri\0211D\005\004\003;q!aA!os\"9\021\021E\023\005\002\005\r\022A\0027p_.,\b\017F\002o\003KAq!a\n\002 \001\007q-\001\002jI\"9\0211F\023\005\002\0055\022!\0057p_.,\bOQ=Rk\026,X\rV=qKR\031a.a\f\t\021\005E\022\021\006a\001\003g\t\021\"];fk\026$\026\020]31\t\005U\022\021\b\t\005Qn\f9\004E\002\003s!A\"a\017\0020\005\005\t\021!B\001\003#\0211a\030\0234\021%\ty$\nb\001\n\033\t\t%\001\005s[F\034E.Y:t+\t\t\031e\004\002\002F\r\022\021q\t\031\005\003\023\n\t\006E\003\t\003\027\ny%C\002\002N\t\021ACU3rk&\024Xm]'fgN\fw-Z)vKV,\007c\001@\002R\021a\0211KA+\003\003\005\tQ!\001\002\022\t\031q\f\n\033\t\021\005]S\005)A\007\003\007\n\021B]7r\0072\f7o\035\021\t\017\005mS\005\"\001\002^\005yq-\032;SKF,\030N]3e)f\004X\r\006\003\002`\005%\004\007BA1\003K\002B\001[>\002dA\031a0!\032\005\031\005\035\024\021LA\001\002\003\025\t!!\005\003\007}#c\007\003\005\002l\005e\003\031AA7\003)\t7\r^8s\0072\f7o\035\031\005\003_\n\031\b\005\003iw\006E\004c\001@\002t\021a\021QOA5\003\003\005\tQ!\001\002x\t\031q\fJ\033\022\t\005M\021\021\020\t\004Y\005m\024bAA?[\t)\021i\031;pe\"I\021\021Q\023A\002\023%\0211Q\001\031[\006LGNY8y'&TXmV1s]&tw-S:tk\026$WCAAC!\ri\021qQ\005\004\003\023s!a\002\"p_2,\027M\034\005\n\003\033+\003\031!C\005\003\037\013A$\\1jY\n|\007pU5{K^\013'O\\5oO&\0338/^3e?\022*\027\017\006\003\002\022\006]\005cA\007\002\024&\031\021Q\023\b\003\tUs\027\016\036\005\013\0033\013Y)!AA\002\005\025\025a\001=%c!A\021QT\023!B\023\t))A\rnC&d'm\034=TSj,w+\031:oS:<\027j]:vK\022\004\003bBAQK\021\005\0211U\001\026O\026$X*Y5mE>D(+Z9vSJ,W.\0328u)\021\t)+a.1\t\005\035\0261\027\t\007\003S\013y+!-\016\005\005-&bAAWG\006!A.\0318h\023\ra\0301\026\t\004}\006MF\001DA\036\003?\013\t\021!A\003\002\005U\026cAA\n\031!A\021\021XAP\001\004\tY,\001\004d_:4\027n\032\t\005\003{\013I-\004\002\002@*!\021\021XAa\025\021\t\031-!2\002\021QL\b/Z:bM\026T!!a2\002\007\r|W.\003\003\002L\006}&AB\"p]\032Lw\rC\004\002P\026\"\t!!5\0027\035,G\017\025:pIV\034W\rZ'fgN\fw-Z)vKV,G+\0379f)\021\t\031.!81\t\005U\027\021\034\t\005Qn\f9\016E\002\0033$A\"a7\002N\006\005\t\021!B\001\003#\0211a\030\0238\021\035\ty.!4A\0029\f1\"\\1jY\n|\007\020V=qK\"A\0211]\023\005\022\021\t)/\001\bhKRl\025-\0337c_b$\026\020]3\025\0139\f9/!=\t\021\005%\030\021\035a\001\003W\fQ\001\035:paN\0042\001LAw\023\r\ty/\f\002\006!J|\007o\035\005\t\003g\f\t\0171\001\002<\006\001B-[:qCR\034\007.\032:D_:4\027n\032\005\b\003o,C\021AA}\003=A\027m\035*fcVL'/\0323UsB,G\003BAC\003wD\001\"a\033\002v\002\007\021Q \031\005\003\024\031\001\005\003iw\n\005\001c\001@\003\004\021a!QAA~\003\003\005\tQ!\001\002x\t!q\fJ\0312\021\035\021I!\nC\005\005\027\t\001\002\\8pWV\004\030\n\032\013\004O\n5\001\002CA\031\005\017\001\rAa\0041\t\tE!Q\003\t\005Qn\024\031\002E\002\005+!ABa\006\003\016\005\005\t\021!B\001\003#\021Aa\030\0232e!9!1D\023\005\n\tu\021A\0057p_.,\boQ8oM&<WO]1u_J$2A\034B\020\021\035\t9C!\007A\002\035D\021Ba\t&\005\004%IA!\n\002)\021,g-Y;mi6\013\027\016\0342pq\016{gNZ5h+\t\tY\f\003\005\003*\025\002\013\021BA^\003U!WMZ1vYRl\025-\0337c_b\034uN\0344jO\002Bq!!/&\t\023\021i\003\006\003\002<\n=\002bBA\024\005W\001\ra\032")
/*     */ public class Mailboxes {
/*     */   private final ActorSystem.Settings settings;
/*     */   
/*     */   private final EventStream eventStream;
/*     */   
/*     */   public final DynamicAccess akka$dispatch$Mailboxes$$dynamicAccess;
/*     */   
/*     */   public final ActorRef akka$dispatch$Mailboxes$$deadLetters;
/*     */   
/*     */   private final Mailbox deadLetterMailbox;
/*     */   
/*     */   private final ConcurrentHashMap<String, MailboxType> mailboxTypeConfigurators;
/*     */   
/*     */   private final Map<Class<?>, String> mailboxBindings;
/*     */   
/*     */   private final Class<RequiresMessageQueue<?>> rmqClass;
/*     */   
/*     */   private boolean mailboxSizeWarningIssued;
/*     */   
/*     */   private final Config defaultMailboxConfig;
/*     */   
/*     */   public ActorSystem.Settings settings() {
/*  34 */     return this.settings;
/*     */   }
/*     */   
/*     */   public Mailboxes(ActorSystem.Settings settings, EventStream eventStream, DynamicAccess dynamicAccess, ActorRef deadLetters) {
/*  41 */     this.deadLetterMailbox = new $anon$1(this);
/*  58 */     this.mailboxTypeConfigurators = new ConcurrentHashMap<String, MailboxType>();
/*  60 */     this.mailboxBindings = 
/*     */       
/*  62 */       (Map<Class<?>, String>)((TraversableOnce)JavaConverters$.MODULE$.mapAsScalaMapConverter(settings.config().getConfig("akka.actor.mailbox.requirements").root().unwrapped()).asScala())
/*  63 */       .toMap(Predef$.MODULE$.conforms()).foldLeft(Predef$.MODULE$.Map().empty(), (Function2)new $anonfun$2(this));
/*  98 */     this.mailboxSizeWarningIssued = false;
/* 214 */     this.defaultMailboxConfig = settings.config().getConfig("akka.actor.default-mailbox");
/*     */   }
/*     */   
/*     */   public EventStream eventStream() {
/*     */     return this.eventStream;
/*     */   }
/*     */   
/*     */   public Mailbox deadLetterMailbox() {
/*     */     return this.deadLetterMailbox;
/*     */   }
/*     */   
/*     */   public class $anon$1 extends Mailbox {
/*     */     public $anon$1(Mailboxes $outer) {
/*     */       super(new Mailboxes$$anon$1$$anon$2($outer));
/*     */       becomeClosed();
/*     */     }
/*     */     
/*     */     public class Mailboxes$$anon$1$$anon$2 implements MessageQueue {
/*     */       public Mailboxes$$anon$1$$anon$2(Mailboxes $outer) {}
/*     */       
/*     */       public void enqueue(ActorRef receiver, Envelope envelope) {
/*     */         Object object = envelope.message();
/*     */         if (object instanceof DeadLetter) {
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/*     */           this.$outer.akka$dispatch$Mailboxes$$deadLetters.tell(new DeadLetter(object, envelope.sender(), receiver), envelope.sender());
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } 
/*     */       }
/*     */       
/*     */       public Null$ dequeue() {
/*     */         return null;
/*     */       }
/*     */       
/*     */       public boolean hasMessages() {
/*     */         return false;
/*     */       }
/*     */       
/*     */       public int numberOfMessages() {
/*     */         return 0;
/*     */       }
/*     */       
/*     */       public void cleanUp(ActorRef owner, MessageQueue deadLetters) {}
/*     */     }
/*     */     
/*     */     public void systemEnqueue(ActorRef receiver, SystemMessage handle) {
/*     */       ScalaActorRef qual$1 = package$.MODULE$.actorRef2Scala(this.$outer.akka$dispatch$Mailboxes$$deadLetters);
/*     */       DeadLetter x$1 = new DeadLetter(handle, receiver, receiver);
/*     */       ActorRef x$2 = qual$1.$bang$default$2(x$1);
/*     */       qual$1.$bang(x$1, x$2);
/*     */     }
/*     */     
/*     */     public SystemMessage systemDrain(SystemMessage newContents) {
/*     */       return SystemMessageList$.MODULE$.ENil();
/*     */     }
/*     */     
/*     */     public boolean hasSystemMessages() {
/*     */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private ConcurrentHashMap<String, MailboxType> mailboxTypeConfigurators() {
/*     */     return this.mailboxTypeConfigurators;
/*     */   }
/*     */   
/*     */   private Map<Class<?>, String> mailboxBindings() {
/*     */     return this.mailboxBindings;
/*     */   }
/*     */   
/*     */   public class $anonfun$2 extends AbstractFunction2<Map<Class<?>, String>, Tuple2<String, Object>, Map<Class<?>, String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Map<Class<?>, String> apply(Map x0$1, Tuple2 x1$1) {
/*     */       Tuple2 tuple2 = new Tuple2(x0$1, x1$1);
/*     */       if (tuple2 != null) {
/*     */         Map m = (Map)tuple2._1();
/*     */         Tuple2 tuple21 = (Tuple2)tuple2._2();
/*     */         if (tuple21 != null) {
/*     */           String k = (String)tuple21._1();
/*     */           Object v = tuple21._2();
/*     */           return (Map)this.$outer.akka$dispatch$Mailboxes$$dynamicAccess.getClassFor(k, ClassTag$.MODULE$.Any()).map((Function1)new Mailboxes$$anonfun$2$$anonfun$apply$2(this, m, v)).recover((PartialFunction)new $anonfun$apply$1(this, k, v)).get();
/*     */         } 
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public $anonfun$2(Mailboxes $outer) {}
/*     */     
/*     */     public class Mailboxes$$anonfun$2$$anonfun$apply$2 extends AbstractFunction1<Class<?>, Map<Class<?>, String>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Map m$1;
/*     */       
/*     */       private final Object v$1;
/*     */       
/*     */       public final Map<Class<?>, String> apply(Class x0$2) {
/*     */         Class clazz = x0$2;
/*     */         return this.m$1.updated(clazz, this.v$1.toString());
/*     */       }
/*     */       
/*     */       public Mailboxes$$anonfun$2$$anonfun$apply$2(Mailboxes.$anonfun$2 $outer, Map m$1, Object v$1) {}
/*     */     }
/*     */     
/*     */     public class $anonfun$apply$1 extends AbstractPartialFunction<Throwable, Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String k$1;
/*     */       
/*     */       private final Object v$1;
/*     */       
/*     */       public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/*     */         Throwable throwable = x1;
/*     */         (new String[2])[0] = "Type [";
/*     */         (new String[2])[1] = "] specified as akka.actor.mailbox.requirement ";
/*     */         (new String[3])[0] = "[";
/*     */         (new String[3])[1] = "] in config can't be loaded due to [";
/*     */         (new String[3])[2] = "]";
/*     */         throw new ConfigurationException((new StringBuilder()).append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { this.k$1 }, ))).append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { this.v$1, throwable.getMessage() }, ))).toString(), throwable);
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Throwable x1) {
/*     */         Throwable throwable = x1;
/*     */         return true;
/*     */       }
/*     */       
/*     */       public $anonfun$apply$1(Mailboxes.$anonfun$2 $outer, String k$1, Object v$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public MailboxType lookup(String id) {
/*     */     return lookupConfigurator(id);
/*     */   }
/*     */   
/*     */   public MailboxType lookupByQueueType(Class<?> queueType) {
/*     */     return lookup(lookupId(queueType));
/*     */   }
/*     */   
/*     */   private final Class<RequiresMessageQueue<?>> rmqClass() {
/*     */     return (Class)RequiresMessageQueue.class;
/*     */   }
/*     */   
/*     */   public Class<?> getRequiredType(Class actorClass) {
/*     */     Type type = Reflect$.MODULE$.findMarker(actorClass, RequiresMessageQueue.class);
/*     */     if (type instanceof ParameterizedType) {
/*     */       ParameterizedType parameterizedType = (ParameterizedType)type;
/*     */       Type type1 = (Type)Predef$.MODULE$.refArrayOps((Object[])parameterizedType.getActualTypeArguments()).head();
/*     */       if (type1 instanceof Class) {
/*     */         Class clazz2 = (Class)type1, clazz3 = clazz2;
/*     */         Class clazz1 = clazz3;
/*     */       } 
/*     */       (new String[2])[0] = "no wildcard type allowed in RequireMessageQueue argument (was [";
/*     */       (new String[2])[1] = "])";
/*     */       throw new IllegalArgumentException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { type1 })));
/*     */     } 
/*     */     throw new MatchError(type);
/*     */   }
/*     */   
/*     */   private boolean mailboxSizeWarningIssued() {
/*     */     return this.mailboxSizeWarningIssued;
/*     */   }
/*     */   
/*     */   private void mailboxSizeWarningIssued_$eq(boolean x$1) {
/*     */     this.mailboxSizeWarningIssued = x$1;
/*     */   }
/*     */   
/*     */   public Class<?> getMailboxRequirement(Config config) {
/*     */     String str1 = config.getString("mailbox-requirement");
/*     */     String str2 = str1;
/*     */     if ("" == null) {
/*     */       "";
/*     */       if (str2 != null)
/*     */         Class clazz = (Class)this.akka$dispatch$Mailboxes$$dynamicAccess.getClassFor(str1, ClassTag$.MODULE$.AnyRef()).get(); 
/*     */     } else {
/*     */       if ("".equals(str2))
/*     */         return MessageQueue.class; 
/*     */       Class clazz = (Class)this.akka$dispatch$Mailboxes$$dynamicAccess.getClassFor(str1, ClassTag$.MODULE$.AnyRef()).get();
/*     */     } 
/*     */     return MessageQueue.class;
/*     */   }
/*     */   
/*     */   public Class<?> getProducedMessageQueueType(MailboxType mailboxType) {
/*     */     Class<ProducesMessageQueue> pmqClass = ProducesMessageQueue.class;
/*     */     if (pmqClass.isAssignableFrom(mailboxType.getClass())) {
/*     */       Type type = Reflect$.MODULE$.findMarker(mailboxType.getClass(), pmqClass);
/*     */       if (type instanceof ParameterizedType) {
/*     */         ParameterizedType parameterizedType = (ParameterizedType)type;
/*     */         Type type1 = (Type)Predef$.MODULE$.refArrayOps((Object[])parameterizedType.getActualTypeArguments()).head();
/*     */         if (type1 instanceof Class) {
/*     */           Class clazz2 = (Class)type1, clazz3 = clazz2;
/*     */           Class clazz1 = clazz3;
/*     */         } 
/*     */         (new String[2])[0] = "no wildcard type allowed in ProducesMessageQueue argument (was [";
/*     */         (new String[2])[1] = "])";
/*     */         throw new IllegalArgumentException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { type1 })));
/*     */       } 
/*     */       throw new MatchError(type);
/*     */     } 
/*     */     return MessageQueue.class;
/*     */   }
/*     */   
/*     */   public MailboxType getMailboxType(Props props, Config dispatcherConfig) {
/*     */     // Byte code:
/*     */     //   0: new scala/runtime/ObjectRef
/*     */     //   3: dup
/*     */     //   4: aconst_null
/*     */     //   5: pop
/*     */     //   6: aconst_null
/*     */     //   7: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   10: astore_3
/*     */     //   11: new scala/runtime/VolatileByteRef
/*     */     //   14: dup
/*     */     //   15: iconst_0
/*     */     //   16: invokespecial <init> : (B)V
/*     */     //   19: astore #4
/*     */     //   21: aload_2
/*     */     //   22: ldc 'id'
/*     */     //   24: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   29: astore #5
/*     */     //   31: aload_1
/*     */     //   32: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   35: astore #6
/*     */     //   37: aload_1
/*     */     //   38: invokevirtual actorClass : ()Ljava/lang/Class;
/*     */     //   41: astore #7
/*     */     //   43: aload_0
/*     */     //   44: aload_2
/*     */     //   45: invokevirtual getMailboxRequirement : (Lcom/typesafe/config/Config;)Ljava/lang/Class;
/*     */     //   48: astore #8
/*     */     //   50: aload #8
/*     */     //   52: ldc akka/dispatch/MessageQueue
/*     */     //   54: astore #10
/*     */     //   56: dup
/*     */     //   57: ifnonnull -> 69
/*     */     //   60: pop
/*     */     //   61: aload #10
/*     */     //   63: ifnull -> 77
/*     */     //   66: goto -> 81
/*     */     //   69: aload #10
/*     */     //   71: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   74: ifeq -> 81
/*     */     //   77: iconst_0
/*     */     //   78: goto -> 82
/*     */     //   81: iconst_1
/*     */     //   82: istore #9
/*     */     //   84: aload_2
/*     */     //   85: ldc 'mailbox-type'
/*     */     //   87: invokeinterface hasPath : (Ljava/lang/String;)Z
/*     */     //   92: ifeq -> 132
/*     */     //   95: aload_2
/*     */     //   96: ldc 'mailbox-type'
/*     */     //   98: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   103: ldc ''
/*     */     //   105: astore #12
/*     */     //   107: dup
/*     */     //   108: ifnonnull -> 120
/*     */     //   111: pop
/*     */     //   112: aload #12
/*     */     //   114: ifnull -> 132
/*     */     //   117: goto -> 128
/*     */     //   120: aload #12
/*     */     //   122: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   125: ifne -> 132
/*     */     //   128: iconst_1
/*     */     //   129: goto -> 133
/*     */     //   132: iconst_0
/*     */     //   133: istore #11
/*     */     //   135: iload #11
/*     */     //   137: ifne -> 186
/*     */     //   140: aload_0
/*     */     //   141: invokespecial mailboxSizeWarningIssued : ()Z
/*     */     //   144: ifne -> 186
/*     */     //   147: aload_2
/*     */     //   148: ldc 'mailbox-size'
/*     */     //   150: invokeinterface hasPath : (Ljava/lang/String;)Z
/*     */     //   155: ifeq -> 186
/*     */     //   158: aload_0
/*     */     //   159: invokevirtual eventStream : ()Lakka/event/EventStream;
/*     */     //   162: new akka/event/Logging$Warning
/*     */     //   165: dup
/*     */     //   166: ldc 'mailboxes'
/*     */     //   168: aload_0
/*     */     //   169: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   172: ldc_w 'ignoring setting 'mailbox-size' for dispatcher [$id], you need to specify 'mailbox-type=bounded''
/*     */     //   175: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)V
/*     */     //   178: invokevirtual publish : (Ljava/lang/Object;)V
/*     */     //   181: aload_0
/*     */     //   182: iconst_1
/*     */     //   183: invokespecial mailboxSizeWarningIssued_$eq : (Z)V
/*     */     //   186: aload #6
/*     */     //   188: invokevirtual mailbox : ()Ljava/lang/String;
/*     */     //   191: ldc ''
/*     */     //   193: astore #13
/*     */     //   195: dup
/*     */     //   196: ifnonnull -> 208
/*     */     //   199: pop
/*     */     //   200: aload #13
/*     */     //   202: ifnull -> 216
/*     */     //   205: goto -> 372
/*     */     //   208: aload #13
/*     */     //   210: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   213: ifeq -> 372
/*     */     //   216: aload #6
/*     */     //   218: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   221: ldc ''
/*     */     //   223: astore #14
/*     */     //   225: dup
/*     */     //   226: ifnonnull -> 238
/*     */     //   229: pop
/*     */     //   230: aload #14
/*     */     //   232: ifnull -> 281
/*     */     //   235: goto -> 246
/*     */     //   238: aload #14
/*     */     //   240: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   243: ifne -> 281
/*     */     //   246: iload #11
/*     */     //   248: ifeq -> 281
/*     */     //   251: aload_0
/*     */     //   252: aload_0
/*     */     //   253: aload_2
/*     */     //   254: ldc 'id'
/*     */     //   256: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   261: invokevirtual lookup : (Ljava/lang/String;)Lakka/dispatch/MailboxType;
/*     */     //   264: aload #5
/*     */     //   266: aload #7
/*     */     //   268: aload_3
/*     */     //   269: aload #8
/*     */     //   271: iload #9
/*     */     //   273: aload #4
/*     */     //   275: invokespecial verifyRequirements$1 : (Lakka/dispatch/MailboxType;Ljava/lang/String;Ljava/lang/Class;Lscala/runtime/ObjectRef;Ljava/lang/Class;ZLscala/runtime/VolatileByteRef;)Lakka/dispatch/MailboxType;
/*     */     //   278: goto -> 453
/*     */     //   281: aload_0
/*     */     //   282: aload #7
/*     */     //   284: invokevirtual hasRequiredType : (Ljava/lang/Class;)Z
/*     */     //   287: ifeq -> 318
/*     */     //   290: aload_0
/*     */     //   291: aload_0
/*     */     //   292: aload_0
/*     */     //   293: aload #7
/*     */     //   295: invokevirtual getRequiredType : (Ljava/lang/Class;)Ljava/lang/Class;
/*     */     //   298: invokevirtual lookupByQueueType : (Ljava/lang/Class;)Lakka/dispatch/MailboxType;
/*     */     //   301: aload #5
/*     */     //   303: aload #7
/*     */     //   305: aload_3
/*     */     //   306: aload #8
/*     */     //   308: iload #9
/*     */     //   310: aload #4
/*     */     //   312: invokespecial verifyRequirements$1 : (Lakka/dispatch/MailboxType;Ljava/lang/String;Ljava/lang/Class;Lscala/runtime/ObjectRef;Ljava/lang/Class;ZLscala/runtime/VolatileByteRef;)Lakka/dispatch/MailboxType;
/*     */     //   315: goto -> 453
/*     */     //   318: iload #9
/*     */     //   320: ifeq -> 347
/*     */     //   323: aload_0
/*     */     //   324: aload_0
/*     */     //   325: aload #8
/*     */     //   327: invokevirtual lookupByQueueType : (Ljava/lang/Class;)Lakka/dispatch/MailboxType;
/*     */     //   330: aload #5
/*     */     //   332: aload #7
/*     */     //   334: aload_3
/*     */     //   335: aload #8
/*     */     //   337: iload #9
/*     */     //   339: aload #4
/*     */     //   341: invokespecial verifyRequirements$1 : (Lakka/dispatch/MailboxType;Ljava/lang/String;Ljava/lang/Class;Lscala/runtime/ObjectRef;Ljava/lang/Class;ZLscala/runtime/VolatileByteRef;)Lakka/dispatch/MailboxType;
/*     */     //   344: goto -> 453
/*     */     //   347: aload_0
/*     */     //   348: aload_0
/*     */     //   349: ldc_w 'akka.actor.default-mailbox'
/*     */     //   352: invokevirtual lookup : (Ljava/lang/String;)Lakka/dispatch/MailboxType;
/*     */     //   355: aload #5
/*     */     //   357: aload #7
/*     */     //   359: aload_3
/*     */     //   360: aload #8
/*     */     //   362: iload #9
/*     */     //   364: aload #4
/*     */     //   366: invokespecial verifyRequirements$1 : (Lakka/dispatch/MailboxType;Ljava/lang/String;Ljava/lang/Class;Lscala/runtime/ObjectRef;Ljava/lang/Class;ZLscala/runtime/VolatileByteRef;)Lakka/dispatch/MailboxType;
/*     */     //   369: goto -> 453
/*     */     //   372: aload_0
/*     */     //   373: aload_0
/*     */     //   374: aload #6
/*     */     //   376: invokevirtual mailbox : ()Ljava/lang/String;
/*     */     //   379: invokevirtual lookup : (Ljava/lang/String;)Lakka/dispatch/MailboxType;
/*     */     //   382: aload #5
/*     */     //   384: aload #7
/*     */     //   386: aload_3
/*     */     //   387: aload #8
/*     */     //   389: iload #9
/*     */     //   391: aload #4
/*     */     //   393: invokespecial verifyRequirements$1 : (Lakka/dispatch/MailboxType;Ljava/lang/String;Ljava/lang/Class;Lscala/runtime/ObjectRef;Ljava/lang/Class;ZLscala/runtime/VolatileByteRef;)Lakka/dispatch/MailboxType;
/*     */     //   396: goto -> 453
/*     */     //   399: astore #15
/*     */     //   401: aload #15
/*     */     //   403: astore #16
/*     */     //   405: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */     //   408: aload #16
/*     */     //   410: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */     //   413: astore #17
/*     */     //   415: aload #17
/*     */     //   417: invokevirtual isEmpty : ()Z
/*     */     //   420: ifne -> 454
/*     */     //   423: iload #9
/*     */     //   425: ifeq -> 454
/*     */     //   428: aload_0
/*     */     //   429: aload_0
/*     */     //   430: aload #8
/*     */     //   432: invokevirtual lookupByQueueType : (Ljava/lang/Class;)Lakka/dispatch/MailboxType;
/*     */     //   435: aload #5
/*     */     //   437: aload #7
/*     */     //   439: aload_3
/*     */     //   440: aload #8
/*     */     //   442: iload #9
/*     */     //   444: aload #4
/*     */     //   446: invokespecial verifyRequirements$1 : (Lakka/dispatch/MailboxType;Ljava/lang/String;Ljava/lang/Class;Lscala/runtime/ObjectRef;Ljava/lang/Class;ZLscala/runtime/VolatileByteRef;)Lakka/dispatch/MailboxType;
/*     */     //   449: astore #18
/*     */     //   451: aload #18
/*     */     //   453: areturn
/*     */     //   454: aload #15
/*     */     //   456: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #125	-> 10
/*     */     //   #121	-> 19
/*     */     //   #122	-> 21
/*     */     //   #123	-> 31
/*     */     //   #124	-> 37
/*     */     //   #127	-> 43
/*     */     //   #129	-> 50
/*     */     //   #132	-> 84
/*     */     //   #133	-> 95
/*     */     //   #132	-> 128
/*     */     //   #131	-> 133
/*     */     //   #136	-> 135
/*     */     //   #137	-> 158
/*     */     //   #138	-> 172
/*     */     //   #137	-> 175
/*     */     //   #139	-> 181
/*     */     //   #153	-> 186
/*     */     //   #155	-> 216
/*     */     //   #156	-> 251
/*     */     //   #157	-> 281
/*     */     //   #158	-> 290
/*     */     //   #162	-> 318
/*     */     //   #163	-> 323
/*     */     //   #165	-> 347
/*     */     //   #154	-> 372
/*     */     //   #158	-> 399
/*     */     //   #160	-> 405
/*     */     //   #158	-> 451
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	457	0	this	Lakka/dispatch/Mailboxes;
/*     */     //   0	457	1	props	Lakka/actor/Props;
/*     */     //   0	457	2	dispatcherConfig	Lcom/typesafe/config/Config;
/*     */     //   11	446	3	actorRequirement$lzy	Lscala/runtime/ObjectRef;
/*     */     //   21	436	4	bitmap$0	Lscala/runtime/VolatileByteRef;
/*     */     //   31	426	5	id	Ljava/lang/String;
/*     */     //   37	420	6	deploy	Lakka/actor/Deploy;
/*     */     //   43	414	7	actorClass	Ljava/lang/Class;
/*     */     //   50	407	8	mailboxRequirement	Ljava/lang/Class;
/*     */     //   84	373	9	hasMailboxRequirement	Z
/*     */     //   135	322	11	hasMailboxType	Z
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   290	318	399	finally
/*     */   }
/*     */   
/*     */   private final Class actorRequirement$lzycompute$1(Class<? extends Actor> actorClass$1, ObjectRef actorRequirement$lzy$1, VolatileByteRef bitmap$0$1) {
/*     */     synchronized (this) {
/*     */       if ((byte)(bitmap$0$1.elem & 0x1) == 0) {
/*     */         actorRequirement$lzy$1.elem = getRequiredType(actorClass$1);
/*     */         bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 0x1);
/*     */       } 
/*     */       return (Class)actorRequirement$lzy$1.elem;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Class actorRequirement$1(Class actorClass$1, ObjectRef actorRequirement$lzy$1, VolatileByteRef bitmap$0$1) {
/*     */     return ((byte)(bitmap$0$1.elem & 0x1) == 0) ? actorRequirement$lzycompute$1(actorClass$1, actorRequirement$lzy$1, bitmap$0$1) : (Class)actorRequirement$lzy$1.elem;
/*     */   }
/*     */   
/*     */   private final Class mqType$lzycompute$1(MailboxType mailboxType$1, ObjectRef mqType$lzy$1, VolatileByteRef bitmap$0$2) {
/*     */     synchronized (this) {
/*     */       if ((byte)(bitmap$0$2.elem & 0x1) == 0) {
/*     */         mqType$lzy$1.elem = getProducedMessageQueueType(mailboxType$1);
/*     */         bitmap$0$2.elem = (byte)(bitmap$0$2.elem | 0x1);
/*     */       } 
/*     */       return (Class)mqType$lzy$1.elem;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Class mqType$1(MailboxType mailboxType$1, ObjectRef mqType$lzy$1, VolatileByteRef bitmap$0$2) {
/*     */     return ((byte)(bitmap$0$2.elem & 0x1) == 0) ? mqType$lzycompute$1(mailboxType$1, mqType$lzy$1, bitmap$0$2) : (Class)mqType$lzy$1.elem;
/*     */   }
/*     */   
/*     */   private final MailboxType verifyRequirements$1(MailboxType mailboxType, String id$2, Class<? extends Actor> actorClass$1, ObjectRef actorRequirement$lzy$1, Class mailboxRequirement$1, boolean hasMailboxRequirement$1, VolatileByteRef bitmap$0$1) {
/*     */     null;
/*     */     ObjectRef mqType$lzy = new ObjectRef(null);
/*     */     VolatileByteRef bitmap$0 = new VolatileByteRef((byte)0);
/*     */     if (hasMailboxRequirement$1 && !mailboxRequirement$1.isAssignableFrom(mqType$1(mailboxType, mqType$lzy, bitmap$0))) {
/*     */       (new String[3])[0] = "produced message queue type [";
/*     */       (new String[3])[1] = "] does not fulfill requirement for dispatcher [";
/*     */       (new String[3])[2] = "]";
/*     */       throw new IllegalArgumentException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { mqType$1(mailboxType, mqType$lzy, bitmap$0), id$2 })));
/*     */     } 
/*     */     if (hasRequiredType(actorClass$1) && !actorRequirement$1(actorClass$1, actorRequirement$lzy$1, bitmap$0$1).isAssignableFrom(mqType$1(mailboxType, mqType$lzy, bitmap$0))) {
/*     */       (new String[3])[0] = "produced message queue type [";
/*     */       (new String[3])[1] = "] does not fulfill requirement for actor class [";
/*     */       (new String[3])[2] = "]";
/*     */       throw new IllegalArgumentException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { mqType$1(mailboxType, mqType$lzy, bitmap$0), actorClass$1 })));
/*     */     } 
/*     */     return mailboxType;
/*     */   }
/*     */   
/*     */   public boolean hasRequiredType(Class<?> actorClass) {
/*     */     return RequiresMessageQueue.class.isAssignableFrom(actorClass);
/*     */   }
/*     */   
/*     */   private String lookupId(Class queueType) {
/*     */     Option option1 = mailboxBindings().get(queueType);
/*     */     Option option2 = option1;
/*     */     if (None$.MODULE$ == null) {
/*     */       if (option2 != null)
/*     */         if (option1 instanceof Some) {
/*     */           Some some = (Some)option1;
/*     */           String s;
/*     */           return s = (String)some.x();
/*     */         }  
/*     */     } else {
/*     */       if (None$.MODULE$.equals(option2)) {
/*     */         (new String[2])[0] = "Mailbox Mapping for [";
/*     */         (new String[2])[1] = "] not configured";
/*     */         throw new ConfigurationException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { queueType })));
/*     */       } 
/*     */       if (option1 instanceof Some) {
/*     */         Some some = (Some)option1;
/*     */         String str;
/*     */         return str = (String)some.x();
/*     */       } 
/*     */     } 
/*     */     (new String[2])[0] = "Mailbox Mapping for [";
/*     */     (new String[2])[1] = "] not configured";
/*     */     throw new ConfigurationException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { queueType })));
/*     */   }
/*     */   
/*     */   private MailboxType lookupConfigurator(String id) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial mailboxTypeConfigurators : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   4: aload_1
/*     */     //   5: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   8: checkcast akka/dispatch/MailboxType
/*     */     //   11: astore_2
/*     */     //   12: aload_2
/*     */     //   13: ifnonnull -> 431
/*     */     //   16: aload_1
/*     */     //   17: astore #5
/*     */     //   19: ldc_w 'unbounded'
/*     */     //   22: aload #5
/*     */     //   24: astore #6
/*     */     //   26: dup
/*     */     //   27: ifnonnull -> 39
/*     */     //   30: pop
/*     */     //   31: aload #6
/*     */     //   33: ifnull -> 47
/*     */     //   36: goto -> 59
/*     */     //   39: aload #6
/*     */     //   41: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   44: ifeq -> 59
/*     */     //   47: new akka/dispatch/UnboundedMailbox
/*     */     //   50: dup
/*     */     //   51: invokespecial <init> : ()V
/*     */     //   54: astore #7
/*     */     //   56: goto -> 333
/*     */     //   59: ldc_w 'bounded'
/*     */     //   62: aload #5
/*     */     //   64: astore #8
/*     */     //   66: dup
/*     */     //   67: ifnonnull -> 79
/*     */     //   70: pop
/*     */     //   71: aload #8
/*     */     //   73: ifnull -> 87
/*     */     //   76: goto -> 108
/*     */     //   79: aload #8
/*     */     //   81: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   84: ifeq -> 108
/*     */     //   87: new akka/dispatch/BoundedMailbox
/*     */     //   90: dup
/*     */     //   91: aload_0
/*     */     //   92: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   95: aload_0
/*     */     //   96: aload_1
/*     */     //   97: invokespecial config : (Ljava/lang/String;)Lcom/typesafe/config/Config;
/*     */     //   100: invokespecial <init> : (Lakka/actor/ActorSystem$Settings;Lcom/typesafe/config/Config;)V
/*     */     //   103: astore #7
/*     */     //   105: goto -> 333
/*     */     //   108: aload_0
/*     */     //   109: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   112: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */     //   115: aload_1
/*     */     //   116: invokeinterface hasPath : (Ljava/lang/String;)Z
/*     */     //   121: ifeq -> 374
/*     */     //   124: aload_0
/*     */     //   125: aload_1
/*     */     //   126: invokespecial config : (Ljava/lang/String;)Lcom/typesafe/config/Config;
/*     */     //   129: astore #9
/*     */     //   131: aload #9
/*     */     //   133: ldc 'mailbox-type'
/*     */     //   135: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   140: astore #10
/*     */     //   142: ldc ''
/*     */     //   144: aload #10
/*     */     //   146: astore #11
/*     */     //   148: dup
/*     */     //   149: ifnonnull -> 161
/*     */     //   152: pop
/*     */     //   153: aload #11
/*     */     //   155: ifnull -> 169
/*     */     //   158: goto -> 226
/*     */     //   161: aload #11
/*     */     //   163: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   166: ifeq -> 226
/*     */     //   169: new akka/ConfigurationException
/*     */     //   172: dup
/*     */     //   173: new scala/StringContext
/*     */     //   176: dup
/*     */     //   177: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   180: iconst_2
/*     */     //   181: anewarray java/lang/String
/*     */     //   184: dup
/*     */     //   185: iconst_0
/*     */     //   186: ldc_w 'The setting mailbox-type, defined in ['
/*     */     //   189: aastore
/*     */     //   190: dup
/*     */     //   191: iconst_1
/*     */     //   192: ldc_w '] is empty'
/*     */     //   195: aastore
/*     */     //   196: checkcast [Ljava/lang/Object;
/*     */     //   199: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   202: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   205: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   208: iconst_1
/*     */     //   209: anewarray java/lang/Object
/*     */     //   212: dup
/*     */     //   213: iconst_0
/*     */     //   214: aload_1
/*     */     //   215: aastore
/*     */     //   216: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   219: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   222: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   225: athrow
/*     */     //   226: getstatic scala/collection/immutable/List$.MODULE$ : Lscala/collection/immutable/List$;
/*     */     //   229: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   232: iconst_2
/*     */     //   233: anewarray scala/Tuple2
/*     */     //   236: dup
/*     */     //   237: iconst_0
/*     */     //   238: getstatic scala/Predef$ArrowAssoc$.MODULE$ : Lscala/Predef$ArrowAssoc$;
/*     */     //   241: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   244: ldc_w akka/actor/ActorSystem$Settings
/*     */     //   247: invokevirtual any2ArrowAssoc : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   250: aload_0
/*     */     //   251: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   254: invokevirtual $minus$greater$extension : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/Tuple2;
/*     */     //   257: aastore
/*     */     //   258: dup
/*     */     //   259: iconst_1
/*     */     //   260: getstatic scala/Predef$ArrowAssoc$.MODULE$ : Lscala/Predef$ArrowAssoc$;
/*     */     //   263: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   266: ldc com/typesafe/config/Config
/*     */     //   268: invokevirtual any2ArrowAssoc : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   271: aload #9
/*     */     //   273: invokevirtual $minus$greater$extension : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/Tuple2;
/*     */     //   276: aastore
/*     */     //   277: checkcast [Ljava/lang/Object;
/*     */     //   280: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   283: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/immutable/List;
/*     */     //   286: astore #13
/*     */     //   288: aload_0
/*     */     //   289: getfield akka$dispatch$Mailboxes$$dynamicAccess : Lakka/actor/DynamicAccess;
/*     */     //   292: aload #10
/*     */     //   294: aload #13
/*     */     //   296: getstatic scala/reflect/ClassTag$.MODULE$ : Lscala/reflect/ClassTag$;
/*     */     //   299: ldc akka/dispatch/MailboxType
/*     */     //   301: invokevirtual apply : (Ljava/lang/Class;)Lscala/reflect/ClassTag;
/*     */     //   304: invokevirtual createInstanceFor : (Ljava/lang/String;Lscala/collection/immutable/Seq;Lscala/reflect/ClassTag;)Lscala/util/Try;
/*     */     //   307: new akka/dispatch/Mailboxes$$anonfun$1
/*     */     //   310: dup
/*     */     //   311: aload_0
/*     */     //   312: aload_1
/*     */     //   313: aload #10
/*     */     //   315: invokespecial <init> : (Lakka/dispatch/Mailboxes;Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   318: invokevirtual recover : (Lscala/PartialFunction;)Lscala/util/Try;
/*     */     //   321: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   324: checkcast akka/dispatch/MailboxType
/*     */     //   327: astore #12
/*     */     //   329: aload #12
/*     */     //   331: astore #7
/*     */     //   333: aload #7
/*     */     //   335: astore #4
/*     */     //   337: aload_0
/*     */     //   338: invokespecial mailboxTypeConfigurators : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   341: aload_1
/*     */     //   342: aload #4
/*     */     //   344: invokevirtual putIfAbsent : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   347: checkcast akka/dispatch/MailboxType
/*     */     //   350: astore #14
/*     */     //   352: aload #14
/*     */     //   354: ifnonnull -> 364
/*     */     //   357: aload #4
/*     */     //   359: astore #15
/*     */     //   361: goto -> 368
/*     */     //   364: aload #14
/*     */     //   366: astore #15
/*     */     //   368: aload #15
/*     */     //   370: astore_3
/*     */     //   371: goto -> 433
/*     */     //   374: new akka/ConfigurationException
/*     */     //   377: dup
/*     */     //   378: new scala/StringContext
/*     */     //   381: dup
/*     */     //   382: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   385: iconst_2
/*     */     //   386: anewarray java/lang/String
/*     */     //   389: dup
/*     */     //   390: iconst_0
/*     */     //   391: ldc_w 'Mailbox Type ['
/*     */     //   394: aastore
/*     */     //   395: dup
/*     */     //   396: iconst_1
/*     */     //   397: ldc_w '] not configured'
/*     */     //   400: aastore
/*     */     //   401: checkcast [Ljava/lang/Object;
/*     */     //   404: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   407: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   410: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   413: iconst_1
/*     */     //   414: anewarray java/lang/Object
/*     */     //   417: dup
/*     */     //   418: iconst_0
/*     */     //   419: aload_1
/*     */     //   420: aastore
/*     */     //   421: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   424: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   427: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   430: athrow
/*     */     //   431: aload_2
/*     */     //   432: astore_3
/*     */     //   433: aload_3
/*     */     //   434: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #181	-> 0
/*     */     //   #182	-> 12
/*     */     //   #184	-> 16
/*     */     //   #186	-> 19
/*     */     //   #187	-> 59
/*     */     //   #189	-> 108
/*     */     //   #190	-> 124
/*     */     //   #191	-> 131
/*     */     //   #192	-> 142
/*     */     //   #194	-> 226
/*     */     //   #195	-> 288
/*     */     //   #201	-> 321
/*     */     //   #193	-> 327
/*     */     //   #191	-> 329
/*     */     //   #188	-> 331
/*     */     //   #184	-> 333
/*     */     //   #205	-> 337
/*     */     //   #206	-> 352
/*     */     //   #207	-> 364
/*     */     //   #205	-> 368
/*     */     //   #182	-> 370
/*     */     //   #189	-> 374
/*     */     //   #210	-> 431
/*     */     //   #181	-> 433
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	435	0	this	Lakka/dispatch/Mailboxes;
/*     */     //   0	435	1	id	Ljava/lang/String;
/*     */     //   131	200	9	conf	Lcom/typesafe/config/Config;
/*     */     //   288	39	13	args	Lscala/collection/immutable/List;
/*     */     //   337	33	4	newConfigurator	Lakka/dispatch/MailboxType;
/*     */   }
/*     */   
/*     */   public class Mailboxes$$anonfun$1 extends AbstractPartialFunction<Throwable, Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String id$1;
/*     */     
/*     */     private final String x1$2;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x2, Function1 default) {
/*     */       Throwable throwable = x2;
/*     */       (new String[3])[0] = "Cannot instantiate MailboxType [";
/*     */       (new String[3])[1] = "], defined in [";
/*     */       (new String[3])[2] = "], make sure it has a public";
/*     */       throw new IllegalArgumentException((new StringBuilder()).append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { this.x1$2, this.id$1 }, ))).append(" constructor with [akka.actor.ActorSystem.Settings, com.typesafe.config.Config] parameters").toString(), throwable);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x2) {
/*     */       Throwable throwable = x2;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public Mailboxes$$anonfun$1(Mailboxes $outer, String id$1, String x1$2) {}
/*     */   }
/*     */   
/*     */   private Config defaultMailboxConfig() {
/* 214 */     return this.defaultMailboxConfig;
/*     */   }
/*     */   
/*     */   private Config config(String id) {
/* 219 */     (new Tuple2[1])[0] = Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc("id"), id);
/* 221 */     return ConfigFactory.parseMap((Map)JavaConverters$.MODULE$.mapAsJavaMapConverter((Map)Predef$.MODULE$.Map().apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]))).asJava()).withFallback((ConfigMergeable)settings().config().getConfig(id)).withFallback((ConfigMergeable)defaultMailboxConfig());
/*     */   }
/*     */   
/*     */   public static String NoMailboxRequirement() {
/*     */     return Mailboxes$.MODULE$.NoMailboxRequirement();
/*     */   }
/*     */   
/*     */   public static String DefaultMailboxId() {
/*     */     return Mailboxes$.MODULE$.DefaultMailboxId();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Mailboxes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */