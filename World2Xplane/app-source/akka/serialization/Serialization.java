/*     */ package akka.serialization;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefProvider;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Address;
/*     */ import akka.actor.ExtendedActorSystem;
/*     */ import akka.actor.Extension;
/*     */ import akka.event.LoggingAdapter;
/*     */ import com.typesafe.config.Config;
/*     */ import java.io.NotSerializableException;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Try;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\rmr!B\001\003\021\0039\021!D*fe&\fG.\033>bi&|gN\003\002\004\t\005i1/\032:jC2L'0\031;j_:T\021!B\001\005C.\\\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003\033M+'/[1mSj\fG/[8o'\tIA\002\005\002\016!5\taBC\001\020\003\025\0318-\0317b\023\t\tbB\001\004B]f\024VM\032\005\006'%!\t\001F\001\007y%t\027\016\036 \025\003\035)AAF\005\001/\ty1\t\\1tgN+'/[1mSj,'\017\005\003\0161ii\023BA\r\017\005\031!V\017\0357feA\0221\004\n\t\0049}\021cBA\007\036\023\tqb\"\001\004Qe\026$WMZ\005\003A\005\022Qa\0217bgNT!A\b\b\021\005\r\"C\002\001\003\nKU\t\t\021!A\003\002\031\0221a\030\0232#\t9#\006\005\002\016Q%\021\021F\004\002\b\035>$\b.\0338h!\ti1&\003\002-\035\t\031\021I\\=\021\005!q\023BA\030\003\005)\031VM]5bY&TXM\035\005\tc%\021\r\021\"\001\005e\005Y2-\036:sK:$HK]1ogB|'\017^%oM>\024X.\031;j_:,\022a\r\t\004i]JT\"A\033\013\005Yr\021\001B;uS2L!\001O\033\003\037\021Kh.Y7jGZ\013'/[1cY\026\004\"AO\036\016\003%1Q\001P\005A\tu\0221\"\0238g_Jl\027\r^5p]N!1\b\004 B!\tiq(\003\002A\035\t9\001K]8ek\016$\bCA\007C\023\t\031eB\001\007TKJL\027\r\\5{C\ndW\r\003\005Fw\tU\r\021\"\001G\003\035\tG\r\032:fgN,\022a\022\t\003\021.k\021!\023\006\003\025\022\tQ!Y2u_JL!\001T%\003\017\005#GM]3tg\"Aaj\017B\tB\003%q)\001\005bI\022\024Xm]:!\021!\0016H!f\001\n\003\t\026AB:zgR,W.F\001S!\tA5+\003\002U\023\nY\021i\031;peNK8\017^3n\021!16H!E!\002\023\021\026aB:zgR,W\016\t\005\006'm\"\t\001\027\013\004seS\006\"B#X\001\0049\005\"\002)X\001\004\021\006b\002/<\003\003%\t!X\001\005G>\004\030\020F\002:=~Cq!R.\021\002\003\007q\tC\004Q7B\005\t\031\001*\t\017\005\\\024\023!C\001E\006q1m\0349zI\021,g-Y;mi\022\nT#A2+\005\035#7&A3\021\005\031\\W\"A4\013\005!L\027!C;oG\",7m[3e\025\tQg\"\001\006b]:|G/\031;j_:L!\001\\4\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004owE\005I\021A8\002\035\r|\007/\037\023eK\032\fW\017\034;%eU\t\001O\013\002SI\"9!oOA\001\n\003\032\030!\0049s_\022,8\r\036)sK\032L\0070F\001u!\t)(0D\001w\025\t9\b0\001\003mC:<'\"A=\002\t)\fg/Y\005\003wZ\024aa\025;sS:<\007bB?<\003\003%\tA`\001\raJ|G-^2u\003JLG/_\013\002B\031Q\"!\001\n\007\005\raBA\002J]RD\021\"a\002<\003\003%\t!!\003\002\035A\024x\016Z;di\026cW-\\3oiR\031!&a\003\t\023\0055\021QAA\001\002\004y\030a\001=%c!I\021\021C\036\002\002\023\005\0231C\001\020aJ|G-^2u\023R,'/\031;peV\021\021Q\003\t\006\003/\tiBK\007\003\0033Q1!a\007\017\003)\031w\016\0347fGRLwN\\\005\005\003?\tIB\001\005Ji\026\024\030\r^8s\021%\t\031cOA\001\n\003\t)#\001\005dC:,\025/^1m)\021\t9#!\f\021\0075\tI#C\002\002,9\021qAQ8pY\026\fg\016C\005\002\016\005\005\022\021!a\001U!I\021\021G\036\002\002\023\005\0231G\001\tQ\006\034\bnQ8eKR\tq\020C\005\0028m\n\t\021\"\021\002:\005AAo\\*ue&tw\rF\001u\021%\tidOA\001\n\003\ny$\001\004fcV\fGn\035\013\005\003O\t\t\005C\005\002\016\005m\022\021!a\001U!9\021QI\005!\002\023\031\024\001H2veJ,g\016\036+sC:\034\bo\034:u\023:4wN]7bi&|g\016\t\004\007\003\023J\001!a\023\003\021M+G\017^5oON\0342!a\022\r\021-\ty%a\022\003\006\004%\t!!\025\002\r\r|gNZ5h+\t\t\031\006\005\003\002V\005\005TBAA,\025\021\ty%!\027\013\t\005m\023QL\001\tif\004Xm]1gK*\021\021qL\001\004G>l\027\002BA2\003/\022aaQ8oM&<\007bCA4\003\017\022\t\021)A\005\003'\nqaY8oM&<\007\005C\004\024\003\017\"\t!a\033\025\t\0055\024q\016\t\004u\005\035\003\002CA(\003S\002\r!a\025\t\025\005M\024q\tb\001\n\003\t)(A\006TKJL\027\r\\5{KJ\034XCAA<!\035a\022\021PA?\003{J1!a\037\"\005\ri\025\r\035\t\0049\005}\024BA>\"\021%\t\031)a\022!\002\023\t9(\001\007TKJL\027\r\\5{KJ\034\b\005\003\006\002\b\006\035#\031!C\001\003k\nQcU3sS\006d\027N_1uS>t')\0338eS:<7\017C\005\002\f\006\035\003\025!\003\002x\00512+\032:jC2L'0\031;j_:\024\025N\0343j]\036\034\b\005\003\005\002\020\006\035CQBAI\003-\031wN\0344jOR{W*\0319\025\t\005]\0241\023\005\t\003+\013i\t1\001\002~\005!\001/\031;i\017)\tI*CA\001\022\003!\0211T\001\f\023:4wN]7bi&|g\016E\002;\003;3\021\002P\005\002\002#\005A!a(\024\013\005u\025\021U!\021\017\005\r\026\021V$Ss5\021\021Q\025\006\004\003Os\021a\002:v]RLW.Z\005\005\003W\013)KA\tBEN$(/Y2u\rVt7\r^5p]JBqaEAO\t\003\ty\013\006\002\002\034\"Q\021qGAO\003\003%)%!\017\t\025\005U\026QTA\001\n\003\0139,A\003baBd\027\020F\003:\003s\013Y\f\003\004F\003g\003\ra\022\005\007!\006M\006\031\001*\t\025\005}\026QTA\001\n\003\013\t-A\004v]\006\004\b\017\\=\025\t\005\r\0271\032\t\006\033\005\025\027\021Z\005\004\003\017t!AB(qi&|g\016\005\003\0161\035\023\006\"CAg\003{\013\t\0211\001:\003\rAH\005\r\005\013\003#\fi*!A\005\n\005M\027a\003:fC\022\024Vm]8mm\026$\"!!6\021\007U\f9.C\002\002ZZ\024aa\0242kK\016$\bbBAo\023\021\005\021q\\\001\024g\026\024\030.\0317ju\026$\027i\031;peB\013G\017\033\013\005\003{\n\t\017\003\005\002d\006m\007\031AAs\003!\t7\r^8s%\0264\007c\001%\002h&\031\021\021^%\003\021\005\033Go\034:SK\0324QA\003\002\001\003[\034R!a;\r\003_\0042\001SAy\023\r\t\0310\023\002\n\013b$XM\\:j_:D!\002UAv\005\013\007I\021AA|+\t\tI\020E\002I\003wL1!!@J\005M)\005\020^3oI\026$\027i\031;peNK8\017^3n\021)1\0261\036B\001B\003%\021\021 \005\b'\005-H\021\001B\002)\021\021)Aa\002\021\007!\tY\017C\004Q\005\003\001\r!!?\t\025\t-\0211\036b\001\n\003\021i!\001\005tKR$\030N\\4t+\t\021y\001\005\003\003\022\005\035cB\001\005\001\021%\021)\"a;!\002\023\021y!A\005tKR$\030N\\4tA!Q!\021DAv\005\004%\tAa\007\002\0071|w-\006\002\003\036A!!q\004B\023\033\t\021\tCC\002\003$\021\tQ!\032<f]RLAAa\n\003\"\tqAj\\4hS:<\027\tZ1qi\026\024\b\"\003B\026\003W\004\013\021\002B\017\003\021awn\032\021\t\021\t=\0221\036C\001\005c\t\021b]3sS\006d\027N_3\025\t\tM\"Q\t\t\006i\tU\"\021H\005\004\005o)$a\001+ssB)QBa\017\003@%\031!Q\b\b\003\013\005\023(/Y=\021\0075\021\t%C\002\003D9\021AAQ=uK\"9!q\tB\027\001\004a\021!A8\t\021\t-\0231\036C\001\005\033\n1\002Z3tKJL\027\r\\5{KV!!q\nB+)!\021\tF!\027\003^\t\005\004#\002\033\0036\tM\003cA\022\003V\0219!q\013B%\005\0041#!\001+\t\021\tm#\021\na\001\005s\tQAY=uKNDqAa\030\003J\001\007q0\001\007tKJL\027\r\\5{KJLE\r\003\005\003d\t%\003\031\001B3\003\025\031G.\031>{!\025i\021Q\031B4a\021\021IG!\034\021\tqy\"1\016\t\004G\t5D\001\004B8\005c\n\t\021!A\003\002\t\r%aA0%e!A!1\rB%\001\004\021\031\bE\003\016\003\013\024)\b\r\003\003x\tm\004\003\002\017 \005s\0022a\tB>\t1\021yG!\035\002\002\003\005)\021\001B?#\r9#q\020\t\004G\t\005Ea\002B,\005\023\022\rAJ\t\004O\tM\003\002\003B&\003W$\tAa\"\026\t\t%%q\022\013\007\005\027\023\tJa%\021\013Q\022)D!$\021\007\r\022y\tB\004\003X\t\025%\031\001\024\t\021\tm#Q\021a\001\005sA\001Ba\031\003\006\002\007!Q\023\t\0059}\021i\t\003\005\003\032\006-H\021\001BN\003E1\027N\0343TKJL\027\r\\5{KJ4uN\035\013\004[\tu\005b\002B$\005/\003\r\001\004\005\t\005C\013Y\017\"\001\003$\006i1/\032:jC2L'0\032:G_J$2!\fBS\021!\021\031Ga(A\002\t\035\006\007\002BU\005[\003B\001H\020\003,B\0311E!,\005\027\t=&QUA\001\002\003\025\tA\n\002\004?\022\032\004\002\003BZ\003W$\tA!.\002\031M,'/[1mSj,'o\0244\025\t\t]&\021\030\t\005i\tUR\006\003\005\003<\nE\006\031AA?\0035\031XM]5bY&TXM\035$R\035\"Q!qXAv\005\004%IA!1\002\027M,'/[1mSj,'o]\013\003\005\007\004b\001HA=\003{j\003\"\003Bd\003W\004\013\021\002Bb\0031\031XM]5bY&TXM]:!\021-\021Y-a;C\002\023\005AA!4\002\021\tLg\016Z5oON,\"Aa4\021\r\tE'q\033Bn\033\t\021\031N\003\003\003V\006e\021!C5n[V$\030M\0317f\023\021\021INa5\003\007M+\027\017E\002\003\022UA\021Ba8\002l\002\006IAa4\002\023\tLg\016Z5oON\004\003\002\003Br\003W$IA!:\002\tM|'\017\036\013\005\005\037\0249\017\003\005\003j\n\005\b\031\001Bv\003\tIg\016\005\004\003n\nu(1\034\b\005\005_\024IP\004\003\003r\n]XB\001Bz\025\r\021)PB\001\007yI|w\016\036 \n\003=I1Aa?\017\003\035\001\030mY6bO\026LAAa@\004\002\tA\021\n^3sC\ndWMC\002\003|:A!b!\002\002l\n\007I\021BB\004\0035\031XM]5bY&TXM]'baV\0211\021\002\t\b\007\027\031\031ba\006.\033\t\031iA\003\003\004\020\rE\021AC2p]\016,(O]3oi*\021a\007_\005\005\007+\031iAA\tD_:\034WO\035:f]RD\025m\0355NCB\004Da!\007\004\036A!AdHB\016!\r\0313Q\004\003\f\007?\031\t#!A\001\002\013\005aEA\002`IUB\021ba\t\002l\002\006Ia!\n\002\035M,'/[1mSj,'/T1qAA911BB\n\007Oi\003\007BB\025\007[\001B\001H\020\004,A\0311e!\f\005\027\r}1\021EA\001\002\003\025\tA\n\005\013\007c\tYO1A\005\002\rM\022\001F:fe&\fG.\033>fe\nK\030\nZ3oi&$\0300\006\002\0046A)A$!\037\000[!I1\021HAvA\003%1QG\001\026g\026\024\030.\0317ju\026\024()_%eK:$\030\016^=!\001")
/*     */ public class Serialization implements Extension {
/*     */   private final ExtendedActorSystem system;
/*     */   
/*     */   private final Settings settings;
/*     */   
/*     */   private final LoggingAdapter log;
/*     */   
/*     */   private final Map<String, Serializer> akka$serialization$Serialization$$serializers;
/*     */   
/*     */   private final Seq<Tuple2<Class<?>, Serializer>> bindings;
/*     */   
/*     */   private final ConcurrentHashMap<Class<?>, Serializer> serializerMap;
/*     */   
/*     */   private final Map<Object, Serializer> serializerByIdentity;
/*     */   
/*     */   public static class Settings {
/*     */     private final Config config;
/*     */     
/*     */     public Config config() {
/*  31 */       return this.config;
/*     */     }
/*     */     
/*  32 */     private final Map<String, String> Serializers = configToMap("akka.actor.serializers");
/*     */     
/*     */     public Map<String, String> Serializers() {
/*  32 */       return this.Serializers;
/*     */     }
/*     */     
/*  33 */     private final Map<String, String> SerializationBindings = configToMap("akka.actor.serialization-bindings");
/*     */     
/*     */     public Map<String, String> SerializationBindings() {
/*  33 */       return this.SerializationBindings;
/*     */     }
/*     */     
/*     */     private final Map<String, String> configToMap(String path) {
/*  37 */       return (Map<String, String>)((TraversableOnce)scala.collection.JavaConverters$.MODULE$.mapAsScalaMapConverter(config().getConfig(path).root().unwrapped()).asScala()).toMap(scala.Predef$.MODULE$.conforms()).map((Function1)new Serialization$Settings$$anonfun$configToMap$1(this), scala.collection.immutable.Map$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public Settings(Config config) {}
/*     */     
/*     */     public class Serialization$Settings$$anonfun$configToMap$1 extends AbstractFunction1<Tuple2<String, Object>, Tuple2<String, String>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Tuple2<String, String> apply(Tuple2 x0$1) {
/*  37 */         Tuple2 tuple2 = x0$1;
/*  37 */         if (tuple2 != null) {
/*  37 */           String k = (String)tuple2._1();
/*  37 */           Object v = tuple2._2();
/*  37 */           return scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(k), v.toString());
/*     */         } 
/*  37 */         throw new MatchError(tuple2);
/*     */       }
/*     */       
/*     */       public Serialization$Settings$$anonfun$configToMap$1(Serialization.Settings $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Information implements Product, Serializable {
/*     */     private final Address address;
/*     */     
/*     */     private final ActorSystem system;
/*     */     
/*     */     public Address address() {
/*  45 */       return this.address;
/*     */     }
/*     */     
/*     */     public ActorSystem system() {
/*  45 */       return this.system;
/*     */     }
/*     */     
/*     */     public Information copy(Address address, ActorSystem system) {
/*  45 */       return new Information(address, system);
/*     */     }
/*     */     
/*     */     public Address copy$default$1() {
/*  45 */       return address();
/*     */     }
/*     */     
/*     */     public ActorSystem copy$default$2() {
/*  45 */       return system();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  45 */       return "Information";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  45 */       return 2;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  45 */       int i = x$1;
/*  45 */       switch (i) {
/*     */         default:
/*  45 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  45 */       return address();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  45 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  45 */       return x$1 instanceof Information;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  45 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  45 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 112
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/serialization/Serialization$Information
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 116
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/serialization/Serialization$Information
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual address : ()Lakka/actor/Address;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual address : ()Lakka/actor/Address;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 108
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 108
/*     */       //   63: aload_0
/*     */       //   64: invokevirtual system : ()Lakka/actor/ActorSystem;
/*     */       //   67: aload #4
/*     */       //   69: invokevirtual system : ()Lakka/actor/ActorSystem;
/*     */       //   72: astore #6
/*     */       //   74: dup
/*     */       //   75: ifnonnull -> 87
/*     */       //   78: pop
/*     */       //   79: aload #6
/*     */       //   81: ifnull -> 95
/*     */       //   84: goto -> 108
/*     */       //   87: aload #6
/*     */       //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   92: ifeq -> 108
/*     */       //   95: aload #4
/*     */       //   97: aload_0
/*     */       //   98: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   101: ifeq -> 108
/*     */       //   104: iconst_1
/*     */       //   105: goto -> 109
/*     */       //   108: iconst_0
/*     */       //   109: ifeq -> 116
/*     */       //   112: iconst_1
/*     */       //   113: goto -> 117
/*     */       //   116: iconst_0
/*     */       //   117: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #45	-> 0
/*     */       //   #63	-> 14
/*     */       //   #45	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	118	0	this	Lakka/serialization/Serialization$Information;
/*     */       //   0	118	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Information(Address address, ActorSystem system) {
/*  45 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Information$ extends AbstractFunction2<Address, ActorSystem, Information> implements Serializable {
/*     */     public static final Information$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*  45 */       return "Information";
/*     */     }
/*     */     
/*     */     public Serialization.Information apply(Address address, ActorSystem system) {
/*  45 */       return new Serialization.Information(address, system);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<Address, ActorSystem>> unapply(Serialization.Information x$0) {
/*  45 */       return (x$0 == null) ? (Option<Tuple2<Address, ActorSystem>>)scala.None$.MODULE$ : (Option<Tuple2<Address, ActorSystem>>)new Some(new Tuple2(x$0.address(), x$0.system()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  45 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Information$() {
/*  45 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Serialization$$anonfun$serializedActorPath$1 extends AbstractFunction0<Address> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorRefProvider provider$1;
/*     */     
/*     */     public final Address apply() {
/*  70 */       return this.provider$1.getDefaultAddress();
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$serializedActorPath$1(ActorRefProvider provider$1) {}
/*     */   }
/*     */   
/*     */   public ExtendedActorSystem system() {
/*  80 */     return this.system;
/*     */   }
/*     */   
/*     */   public Serialization(ExtendedActorSystem system) {
/*  83 */     this.settings = new Settings(system.settings().config());
/*  84 */     this.log = akka.event.Logging$.MODULE$.apply((ActorSystem)system, getClass().getName(), akka.event.LogSource$.MODULE$.fromString());
/* 173 */     this.akka$serialization$Serialization$$serializers = 
/* 174 */       (Map<String, Serializer>)settings().Serializers().withFilter((Function1)new $anonfun$2(this)).map((Function1)new $anonfun$3(this), scala.collection.immutable.Map$.MODULE$.canBuildFrom());
/* 180 */     this.bindings = 
/* 181 */       (Seq<Tuple2<Class<?>, Serializer>>)sort((Iterable<Tuple2<Class<?>, Serializer>>)settings().SerializationBindings().withFilter((Function1)new $anonfun$4(this)).withFilter((Function1)new $anonfun$5(this)).map((Function1)new $anonfun$6(this), scala.collection.immutable.Iterable$.MODULE$.canBuildFrom())).to(scala.Predef$.MODULE$.fallbackStringCanBuildFrom());
/* 201 */     ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<Object, Object>();
/* 201 */     this.serializerMap = (ConcurrentHashMap<Class<?>, Serializer>)bindings().$div$colon(concurrentHashMap, (Function2)new $anonfun$8(this));
/* 207 */     (new Tuple2[1])[0] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(BoxesRunTime.boxToInteger(NullSerializer$.MODULE$.identifier())), NullSerializer$.MODULE$);
/* 207 */     this.serializerByIdentity = (Map<Object, Serializer>)((TraversableLike)((TraversableLike)scala.Predef$.MODULE$.Map().apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]))).$plus$plus((GenTraversableOnce)akka$serialization$Serialization$$serializers(), scala.collection.immutable.Map$.MODULE$.canBuildFrom())).map((Function1)new $anonfun$9(this), scala.collection.immutable.Map$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public Settings settings() {
/*     */     return this.settings;
/*     */   }
/*     */   
/*     */   public LoggingAdapter log() {
/*     */     return this.log;
/*     */   }
/*     */   
/*     */   public Try<byte[]> serialize(Object o) {
/*     */     return scala.util.Try$.MODULE$.apply((Function0)new Serialization$$anonfun$serialize$1(this, o));
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$serialize$1 extends AbstractFunction0<byte[]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object o$1;
/*     */     
/*     */     public final byte[] apply() {
/*     */       return this.$outer.findSerializerFor(this.o$1).toBinary(this.o$1);
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$serialize$1(Serialization $outer, Object o$1) {}
/*     */   }
/*     */   
/*     */   public <T> Try<T> deserialize(byte[] bytes, int serializerId, Option clazz) {
/*     */     return scala.util.Try$.MODULE$.apply((Function0)new Serialization$$anonfun$deserialize$1(this, bytes, serializerId, clazz));
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$deserialize$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final byte[] bytes$2;
/*     */     
/*     */     private final int serializerId$1;
/*     */     
/*     */     private final Option clazz$2;
/*     */     
/*     */     public Serialization$$anonfun$deserialize$1(Serialization $outer, byte[] bytes$2, int serializerId$1, Option clazz$2) {}
/*     */     
/*     */     public final T apply() {
/*     */       try {
/*     */         Serializer serializer = (Serializer)this.$outer.serializerByIdentity().apply(BoxesRunTime.boxToInteger(this.serializerId$1));
/*     */         return (T)serializer.fromBinary(this.bytes$2, this.clazz$2);
/*     */       } catch (NoSuchElementException noSuchElementException) {
/*     */         (new String[2])[0] = "Cannot find serializer with id [";
/*     */         (new String[2])[1] = "]. The most probable reason is that the configuration entry ";
/*     */         throw new NotSerializableException((new StringBuilder()).append((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(this.serializerId$1) }))).append("akka.actor.serializers is not in synch between the two systems.").toString());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Try<T> deserialize(byte[] bytes, Class clazz) {
/*     */     return scala.util.Try$.MODULE$.apply((Function0)new Serialization$$anonfun$deserialize$2(this, bytes, clazz));
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$deserialize$2 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final byte[] bytes$1;
/*     */     
/*     */     private final Class clazz$1;
/*     */     
/*     */     public final T apply() {
/*     */       return (T)this.$outer.serializerFor(this.clazz$1).fromBinary(this.bytes$1, (Option<Class<?>>)new Some(this.clazz$1));
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$deserialize$2(Serialization $outer, byte[] bytes$1, Class clazz$1) {}
/*     */   }
/*     */   
/*     */   public Serializer findSerializerFor(Object o) {
/*     */     Serializer serializer;
/*     */     Object object = o;
/*     */     if (object == null) {
/*     */       serializer = NullSerializer$.MODULE$;
/*     */     } else {
/*     */       serializer = serializerFor(object.getClass());
/*     */     } 
/*     */     return serializer;
/*     */   }
/*     */   
/*     */   public Serializer serializerFor(Class<?> clazz) {
/*     */     Serializer serializer2, serializer1 = serializerMap().get(clazz);
/*     */     if (serializer1 == null) {
/*     */       Serializer serializer5;
/*     */       Seq seq = (Seq)bindings().filter((Function1)new $anonfun$1(this, clazz));
/*     */       Some some = scala.collection.Seq$.MODULE$.unapplySeq(seq);
/*     */       if (!some.isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(0) == 0)
/*     */         throw new NotSerializableException((new StringOps(scala.Predef$.MODULE$.augmentString("No configured serialization-bindings for class [%s]"))).format(scala.Predef$.MODULE$.genericWrapArray(new Object[] { clazz.getName() }))); 
/*     */       if (!unique$1((Seq)seq))
/*     */         log().warning((new StringBuilder()).append("Multiple serializers found for ").append(clazz).append(", choosing first: ").append(seq).toString()); 
/*     */       Serializer serializer3 = (Serializer)((Tuple2)seq.apply(0))._2();
/*     */       Serializer ser = serializer3;
/*     */       Serializer serializer4 = serializerMap().putIfAbsent(clazz, ser);
/*     */       if (serializer4 == null) {
/*     */         log().debug("Using serializer[{}] for message [{}]", ser.getClass().getName(), clazz.getName());
/*     */         serializer5 = ser;
/*     */       } else {
/*     */         serializer5 = serializer4;
/*     */       } 
/*     */       serializer2 = serializer5;
/*     */     } else {
/*     */       serializer2 = serializer1;
/*     */     } 
/*     */     return serializer2;
/*     */   }
/*     */   
/*     */   private final boolean unique$1(Seq possibilities) {
/*     */     return (possibilities.size() == 1 || possibilities.forall((Function1)new Serialization$$anonfun$unique$1$1(this, possibilities)) || possibilities.forall((Function1)new Serialization$$anonfun$unique$1$2(this, possibilities)));
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$unique$1$1 extends AbstractFunction1<Tuple2<Class<?>, Serializer>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq possibilities$1;
/*     */     
/*     */     public final boolean apply(Tuple2 x$1) {
/*     */       return ((Class)x$1._1()).isAssignableFrom((Class)((Tuple2)this.possibilities$1.apply(0))._1());
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$unique$1$1(Serialization $outer, Seq possibilities$1) {}
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$unique$1$2 extends AbstractFunction1<Tuple2<Class<?>, Serializer>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq possibilities$1;
/*     */     
/*     */     public final boolean apply(Tuple2 x$2) {
/*     */       return BoxesRunTime.equals(x$2._2(), ((Tuple2)this.possibilities$1.apply(0))._2());
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$unique$1$2(Serialization $outer, Seq possibilities$1) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction1<Tuple2<Class<?>, Serializer>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class clazz$3;
/*     */     
/*     */     public final boolean apply(Tuple2 x$3) {
/*     */       return ((Class)x$3._1()).isAssignableFrom(this.clazz$3);
/*     */     }
/*     */     
/*     */     public $anonfun$1(Serialization $outer, Class clazz$3) {}
/*     */   }
/*     */   
/*     */   public Try<Serializer> serializerOf(String serializerFQN) {
/*     */     (new Tuple2[1])[0] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(ExtendedActorSystem.class), system());
/*     */     return system().dynamicAccess().createInstanceFor(serializerFQN, (Seq)scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1])), scala.reflect.ClassTag$.MODULE$.apply(Serializer.class)).recoverWith((PartialFunction)new Serialization$$anonfun$serializerOf$1(this, serializerFQN));
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$serializerOf$1 extends AbstractPartialFunction<Throwable, Try<Serializer>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String serializerFQN$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/*     */       Object object;
/*     */       Throwable throwable = x1;
/*     */       if (throwable instanceof NoSuchMethodException) {
/*     */         object = this.$outer.system().dynamicAccess().createInstanceFor(this.serializerFQN$1, (Seq)scala.collection.immutable.Nil$.MODULE$, scala.reflect.ClassTag$.MODULE$.apply(Serializer.class));
/*     */       } else {
/*     */         object = default.apply(x1);
/*     */       } 
/*     */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       boolean bool;
/*     */       Throwable throwable = x1;
/*     */       if (throwable instanceof NoSuchMethodException) {
/*     */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$serializerOf$1(Serialization $outer, String serializerFQN$1) {}
/*     */   }
/*     */   
/*     */   public Map<String, Serializer> akka$serialization$Serialization$$serializers() {
/*     */     return this.akka$serialization$Serialization$$serializers;
/*     */   }
/*     */   
/*     */   public class $anonfun$2 extends AbstractFunction1<Tuple2<String, String>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Tuple2 check$ifrefutable$1) {
/*     */       Tuple2 tuple2 = check$ifrefutable$1;
/*     */       if (tuple2 != null) {
/*     */         String k = (String)tuple2._1(), v = (String)tuple2._2();
/*     */         if (k != null && v != null)
/*     */           return true; 
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public $anonfun$2(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$3 extends AbstractFunction1<Tuple2<String, String>, Tuple2<String, Serializer>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<String, Serializer> apply(Tuple2 x$4) {
/*     */       Tuple2 tuple2 = x$4;
/*     */       if (tuple2 != null) {
/*     */         String k = (String)tuple2._1(), v = (String)tuple2._2();
/*     */         if (k != null) {
/*     */           String str = k;
/*     */           if (v != null) {
/*     */             String str1 = v;
/*     */             return scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(str), this.$outer.serializerOf(str1).get());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public $anonfun$3(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<Class<?>, Serializer>> bindings() {
/*     */     return this.bindings;
/*     */   }
/*     */   
/*     */   public class $anonfun$4 extends AbstractFunction1<Tuple2<String, String>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Tuple2 check$ifrefutable$2) {
/*     */       Tuple2 tuple2 = check$ifrefutable$2;
/*     */       if (tuple2 != null) {
/*     */         String k = (String)tuple2._1(), v = (String)tuple2._2();
/*     */         if (k != null && v != null)
/*     */           return true; 
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public $anonfun$4(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$5 extends AbstractFunction1<Tuple2<String, String>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Tuple2 x$5) {
/*     */       Tuple2 tuple2 = x$5;
/*     */       if (tuple2 != null) {
/*     */         String k = (String)tuple2._1(), v = (String)tuple2._2();
/*     */         if (k != null && v != null) {
/*     */           String str1 = v, str2 = "none";
/*     */           if (str1 == null) {
/*     */             if (str2 != null);
/*     */           } else if (str1.equals(str2)) {
/*     */           
/*     */           } 
/*     */         } else {
/*     */           throw new MatchError(tuple2);
/*     */         } 
/*     */       } else {
/*     */         throw new MatchError(tuple2);
/*     */       } 
/*     */     }
/*     */     
/*     */     public $anonfun$5(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$6 extends AbstractFunction1<Tuple2<String, String>, Tuple2<Class<Object>, Serializer>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<Class<Object>, Serializer> apply(Tuple2 x$6) {
/*     */       Tuple2 tuple2 = x$6;
/*     */       if (tuple2 != null) {
/*     */         String k = (String)tuple2._1(), v = (String)tuple2._2();
/*     */         if (k != null) {
/*     */           String str = k;
/*     */           if (v != null) {
/*     */             String str1 = v;
/*     */             return new Tuple2(this.$outer.system().dynamicAccess().getClassFor(str, scala.reflect.ClassTag$.MODULE$.Any()).get(), this.$outer.akka$serialization$Serialization$$serializers().apply(str1));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public $anonfun$6(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   private Seq<Tuple2<Class<?>, Serializer>> sort(Iterable in) {
/*     */     ArrayBuffer arrayBuffer = new ArrayBuffer(in.size());
/*     */     return (Seq<Tuple2<Class<?>, Serializer>>)((TraversableLike)in.$div$colon(arrayBuffer, (Function2)new Serialization$$anonfun$sort$1(this))).to(scala.Predef$.MODULE$.fallbackStringCanBuildFrom());
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$sort$1 extends AbstractFunction2<ArrayBuffer<Tuple2<Class<?>, Serializer>>, Tuple2<Class<?>, Serializer>, ArrayBuffer<Tuple2<Class<?>, Serializer>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public Serialization$$anonfun$sort$1(Serialization $outer) {}
/*     */     
/*     */     public final ArrayBuffer<Tuple2<Class<?>, Serializer>> apply(ArrayBuffer<Tuple2<Class<?>, Serializer>> buf, Tuple2 ca) {
/*     */       int i = buf.indexWhere((Function1)new $anonfun$7(this, ca));
/*     */       switch (i) {
/*     */         default:
/*     */           (new Tuple2[1])[0] = ca;
/*     */           buf.insert(i, (Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/*     */           return buf;
/*     */         case -1:
/*     */           break;
/*     */       } 
/*     */       (new Tuple2[1])[0] = ca;
/*     */       buf.append((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/*     */       return buf;
/*     */     }
/*     */     
/*     */     public class $anonfun$7 extends AbstractFunction1<Tuple2<Class<?>, Serializer>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Tuple2 ca$1;
/*     */       
/*     */       public final boolean apply(Tuple2 x$8) {
/*     */         return ((Class)x$8._1()).isAssignableFrom((Class)this.ca$1._1());
/*     */       }
/*     */       
/*     */       public $anonfun$7(Serialization$$anonfun$sort$1 $outer, Tuple2 ca$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private ConcurrentHashMap<Class<?>, Serializer> serializerMap() {
/*     */     return this.serializerMap;
/*     */   }
/*     */   
/*     */   public class $anonfun$8 extends AbstractFunction2<ConcurrentHashMap<Class<?>, Serializer>, Tuple2<Class<?>, Serializer>, ConcurrentHashMap<Class<?>, Serializer>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ConcurrentHashMap<Class<?>, Serializer> apply(ConcurrentHashMap x0$2, Tuple2 x1$1) {
/*     */       Tuple2 tuple2 = new Tuple2(x0$2, x1$1);
/*     */       if (tuple2 != null) {
/*     */         ConcurrentHashMap<Class<?>, Serializer> map = (ConcurrentHashMap)tuple2._1();
/*     */         Tuple2 tuple21 = (Tuple2)tuple2._2();
/*     */         if (tuple21 != null) {
/*     */           Class<?> c = (Class)tuple21._1();
/*     */           Serializer s = (Serializer)tuple21._2();
/*     */           map.put(c, s);
/*     */           return map;
/*     */         } 
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public $anonfun$8(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public Map<Object, Serializer> serializerByIdentity() {
/*     */     return this.serializerByIdentity;
/*     */   }
/*     */   
/*     */   public static String serializedActorPath(ActorRef paramActorRef) {
/*     */     return Serialization$.MODULE$.serializedActorPath(paramActorRef);
/*     */   }
/*     */   
/*     */   public class $anonfun$9 extends AbstractFunction1<Tuple2<Object, Serializer>, Tuple2<Object, Serializer>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<Object, Serializer> apply(Tuple2 x0$3) {
/* 207 */       Tuple2 tuple2 = x0$3;
/* 207 */       if (tuple2 != null) {
/* 207 */         Serializer v = (Serializer)tuple2._2();
/* 207 */         return new Tuple2(BoxesRunTime.boxToInteger(v.identifier()), v);
/*     */       } 
/* 207 */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public $anonfun$9(Serialization $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\Serialization.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */