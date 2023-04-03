/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\rUu!B\001\003\021\0039\021aF\"p]NL7\017^3oi\"\0137\017[5oOJ{W\017^3s\025\t\031A!A\004s_V$\030N\\4\013\003\025\tA!Y6lC\016\001\001C\001\005\n\033\005\021a!\002\006\003\021\003Y!aF\"p]NL7\017^3oi\"\0137\017[5oOJ{W\017^3s'\rIAB\005\t\003\033Ai\021A\004\006\002\037\005)1oY1mC&\021\021C\004\002\007\003:L(+\0324\021\0055\031\022B\001\013\017\0051\031VM]5bY&T\030M\0317f\021\0251\022\002\"\001\030\003\031a\024N\\5u}Q\tqAB\004\032\023A\005\031\023\001\016\003%\r{gn]5ti\026tG\017S1tQ\006\024G.Z\n\00311AQ\001\b\r\007\002u\t\021cY8og&\034H/\0328u\021\006\034\bnS3z+\005q\002CA\007 \023\t\001cBA\002B]f4AAI\005CG\tQ2i\0348tSN$XM\034;ICND\027M\0317f\013:4X\r\\8qKN1\021\005\004\023'SI\001\"!\n\r\016\003%\001\"\001C\024\n\005!\022!A\004*pkR,'/\0228wK2|\007/\032\t\003\033)J!a\013\b\003\017A\023x\016Z;di\"AQ&\tBK\002\023\005Q$A\004nKN\034\030mZ3\t\021=\n#\021#Q\001\ny\t\001\"\\3tg\006<W\r\t\005\tc\005\022)\032!C\001;\0059\001.Y:i\027\026L\b\002C\032\"\005#\005\013\021\002\020\002\021!\f7\017[&fs\002BQAF\021\005\002U\"2AN\0349!\t)\023\005C\003.i\001\007a\004C\0032i\001\007a\004C\003\035C\021\005S\004C\004<C\005\005I\021\001\037\002\t\r|\007/\037\013\004mur\004bB\027;!\003\005\rA\b\005\bci\002\n\0211\001\037\021\035\001\025%%A\005\002\005\013abY8qs\022\"WMZ1vYR$\023'F\001CU\tq2iK\001E!\t)%*D\001G\025\t9\005*A\005v]\016DWmY6fI*\021\021JD\001\013C:tw\016^1uS>t\027BA&G\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b\033\006\n\n\021\"\001B\0039\031w\016]=%I\0264\027-\0367uIIBqaT\021\002\002\023\005\003+A\007qe>$Wo\031;Qe\0264\027\016_\013\002#B\021!kV\007\002'*\021A+V\001\005Y\006twMC\001W\003\021Q\027M^1\n\005a\033&AB*ue&tw\rC\004[C\005\005I\021A.\002\031A\024x\016Z;di\006\023\030\016^=\026\003q\003\"!D/\n\005ys!aA%oi\"9\001-IA\001\n\003\t\027A\0049s_\022,8\r^#mK6,g\016\036\013\003=\tDqaY0\002\002\003\007A,A\002yIEBq!Z\021\002\002\023\005c-A\bqe>$Wo\031;Ji\026\024\030\r^8s+\0059\007c\0015l=5\t\021N\003\002k\035\005Q1m\0347mK\016$\030n\0348\n\0051L'\001C%uKJ\fGo\034:\t\0179\f\023\021!C\001_\006A1-\0318FcV\fG\016\006\002qgB\021Q\"]\005\003e:\021qAQ8pY\026\fg\016C\004d[\006\005\t\031\001\020\t\017U\f\023\021!C!m\006A\001.Y:i\007>$W\rF\001]\021\035A\030%!A\005Be\f\001\002^8TiJLgn\032\013\002#\"910IA\001\n\003b\030AB3rk\006d7\017\006\002q{\"91M_A\001\002\004q\002\006B\021\000\003\013\0012!DA\001\023\r\t\031A\004\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\n\003\023I\021\021!E\001\003\027\t!dQ8og&\034H/\0328u\021\006\034\b.\0312mK\026sg/\0327pa\026\0042!JA\007\r!\021\023\"!A\t\002\005=1#BA\007\003#\021\002cBA\n\0033qbDN\007\003\003+Q1!a\006\017\003\035\021XO\034;j[\026LA!a\007\002\026\t\t\022IY:ue\006\034GOR;oGRLwN\034\032\t\017Y\ti\001\"\001\002 Q\021\0211\002\005\tq\0065\021\021!C#s\"Q\021QEA\007\003\003%\t)a\n\002\013\005\004\b\017\\=\025\013Y\nI#a\013\t\r5\n\031\0031\001\037\021\031\t\0241\005a\001=!Q\021qFA\007\003\003%\t)!\r\002\017Ut\027\r\0359msR!\0211GA !\025i\021QGA\035\023\r\t9D\004\002\007\037B$\030n\0348\021\0135\tYD\b\020\n\007\005ubB\001\004UkBdWM\r\005\n\003\003\ni#!AA\002Y\n1\001\037\0231\021)\t)%!\004\002\002\023%\021qI\001\fe\026\fGMU3t_24X\r\006\002\002JA\031!+a\023\n\007\00553K\001\004PE*,7\r^\003\007\003#J\001!a\025\003+\r{gn]5ti\026tG\017S1tQ6\013\007\017]5oOB)Q\"!\026\037=%\031\021q\013\b\003\037A\013'\017^5bY\032+hn\031;j_:<q!a\027\n\021\003\ti&\001\016f[B$\030pQ8og&\034H/\0328u\021\006\034\b.T1qa&tw\rE\002&\003?2q!!\031\n\021\003\t\031G\001\016f[B$\030pQ8og&\034H/\0328u\021\006\034\b.T1qa&twmE\003\002`1\t)\007E\002&\003\037BqAFA0\t\003\tI\007\006\002\002^!A\021QNA0\t\003\ty'A\006jg\022+g-\0338fI\006#Hc\0019\002r!9\0211OA6\001\004q\022!\001=\t\021\005\025\022q\fC\001\003o\"B!!\037\002\000A\031Q\"a\037\n\007\005udBA\004O_RD\027N\\4\t\017\005M\024Q\017a\001=!*\021qL@\002\006!*\021\021L@\002\006\031I\021qQ\005\021\002G\005\021\021\022\002\025\007>t7/[:uK:$\b*Y:i\033\006\004\b/\032:\024\007\005\025E\002C\0042\003\0133\t!!$\025\007y\ty\t\003\004.\003\027\003\rA\b\005\t\003'KA\021\001\003\002\026\006\021\002.Y:i\033\006\004\b/\0338h\003\022\f\007\017^3s)\021\t)'a&\t\021\005e\025\021\023a\001\0037\013a!\\1qa\026\024\bcA\023\002\006\"9\021QE\005\005\002\005}E\003BAQ\007;\0012\001CAR\r\025Q!\001QAS'%\t\031\013DAT\003[K#\003E\002\t\003SK1!a+\003\005Y!U\r\035:fG\006$X\r\032*pkR,'oQ8oM&<\007#\002\005\0020\006\005\026bAAY\005\t9\002k\\8m\037Z,'O]5eKVs7/\032;D_:4\027n\032\005\013\003k\013\031K!f\001\n\003Y\026!\0048s\037\032Len\035;b]\016,7\017\003\006\002:\006\r&\021#Q\001\nq\013aB\034:PM&s7\017^1oG\026\034\b\005C\006\002>\006\r&Q3A\005\002\005}\026a\002:pkR,Wm]\013\003\003\003\004b!a1\002J\0065WBAAc\025\r\t9-[\001\nS6lW\017^1cY\026LA!a3\002F\nA\021\n^3sC\ndW\r\005\003\002P\006UgbA\007\002R&\031\0211\033\b\002\rA\023X\rZ3g\023\rA\026q\033\006\004\003't\001bCAn\003G\023\t\022)A\005\003\003\f\001B]8vi\026,7\017\t\005\f\003?\f\031K!f\001\n\003\n\t/A\004sKNL'0\032:\026\005\005\r\b#B\007\0026\005\025\bc\001\005\002h&\031\021\021\036\002\003\017I+7/\033>fe\"Y\021Q^AR\005#\005\013\021BAr\003!\021Xm]5{KJ\004\003bCAy\003G\023)\032!C\001\003g\f\001C]8vi\026\024H)[:qCR\034\007.\032:\026\005\0055\007bCA|\003G\023\t\022)A\005\003\033\f\021C]8vi\026\024H)[:qCR\034\007.\032:!\021-\tY0a)\003\026\004%\t!!@\002%M,\b/\032:wSN|'o\025;sCR,w-_\013\003\003\004BA!\001\003\b5\021!1\001\006\004\005\013!\021!B1di>\024\030\002\002B\005\005\007\021!cU;qKJ4\030n]8s'R\024\030\r^3hs\"Y!QBAR\005#\005\013\021BA\000\003M\031X\017]3sm&\034xN]*ue\006$XmZ=!\021)\021\t\"a)\003\026\004%\taW\001\023m&\024H/^1m\035>$Wm\035$bGR|'\017\003\006\003\026\005\r&\021#Q\001\nq\0131C^5siV\fGNT8eKN4\025m\031;pe\002B1B!\007\002$\nU\r\021\"\001\003\034\005Y\001.Y:i\033\006\004\b/\0338h+\t\021i\002\005\003\003 \005=cB\001\005\001\021-\021\031#a)\003\022\003\006IA!\b\002\031!\f7\017['baBLgn\032\021\t\017Y\t\031\013\"\001\003(Q\001\022\021\025B\025\005W\021iCa\f\0032\tM\"Q\007\005\n\003k\023)\003%AA\002qC!\"!0\003&A\005\t\031AAa\021)\tyN!\n\021\002\003\007\0211\035\005\013\003c\024)\003%AA\002\0055\007BCA~\005K\001\n\0211\001\002\000\"I!\021\003B\023!\003\005\r\001\030\005\013\0053\021)\003%AA\002\tu\001b\002\f\002$\022\005!\021\b\013\005\003C\023Y\004C\004\003>\t]\002\031\001/\002\0059\024\bb\002\f\002$\022\005!\021\t\013\005\003C\023\031\005\003\005\003F\t}\002\031\001B$\003-\021x.\036;fKB\013G\017[:\021\013I\023I%!4\n\007\005-7\013C\004\027\003G#\tA!\024\025\t\005\005&q\n\005\t\003?\024Y\0051\001\002f\"A!1KAR\t\003\ny,A\003qCRD7\017\003\005\003X\005\rF\021\001B-\00399\030\016\0365ESN\004\030\r^2iKJ$B!!)\003\\!A!Q\fB+\001\004\ti-\001\007eSN\004\030\r^2iKJLE\r\003\005\003b\005\rF\021\001B2\003Y9\030\016\0365TkB,'O^5t_J\034FO]1uK\036LH\003BAQ\005KB\001Ba\032\003`\001\007\021q`\001\tgR\024\030\r^3hs\"A!1NAR\t\003\021i'A\006xSRD'+Z:ju\026\024H\003BAQ\005_B\001\"a8\003j\001\007\021Q\035\005\t\005g\n\031\013\"\001\003v\0051r/\033;i-&\024H/^1m\035>$Wm\035$bGR|'\017\006\003\002\"\n]\004b\002B=\005c\002\r\001X\001\007m:|G-Z:\t\021\tu\0241\025C\001\005\nab^5uQ\"\0137\017['baB,'\017\006\003\002\"\n\005\005\002\003BB\005w\002\rA!\"\002\0175\f\007\017]5oOB!!qDAC\021!\021I)a)\005B\t-\025\001D<ji\"4\025\r\0347cC\016\\G\003\002BG\005'\0032\001\003BH\023\r\021\tJ\001\002\r%>,H/\032:D_:4\027n\032\005\t\005+\0239\t1\001\003\016\006)q\016\0365fe\"A!\021TAR\t\003\022Y*\001\007de\026\fG/\032*pkR,'\017\006\003\003\036\n\r\006c\001\005\003 &\031!\021\025\002\003\rI{W\017^3s\021!\021)Ka&A\002\t\035\026AB:zgR,W\016\005\003\003\002\t%\026\002\002BV\005\007\0211\"Q2u_J\034\026p\035;f[\"I1(a)\002\002\023\005!q\026\013\021\003C\023\tLa-\0036\n]&\021\030B^\005{C\021\"!.\003.B\005\t\031\001/\t\025\005u&Q\026I\001\002\004\t\t\r\003\006\002`\n5\006\023!a\001\003GD!\"!=\003.B\005\t\031AAg\021)\tYP!,\021\002\003\007\021q \005\n\005#\021i\013%AA\002qC!B!\007\003.B\005\t\031\001B\017\021%\001\0251UI\001\n\003\021\t-\006\002\003D*\022Al\021\005\n\033\006\r\026\023!C\001\005\017,\"A!3+\007\005\0057\t\003\006\003N\006\r\026\023!C\001\005\037\fabY8qs\022\"WMZ1vYR$3'\006\002\003R*\032\0211]\"\t\025\tU\0271UI\001\n\003\0219.\001\bd_BLH\005Z3gCVdG\017\n\033\026\005\te'fAAg\007\"Q!Q\\AR#\003%\tAa8\002\035\r|\007/\037\023eK\032\fW\017\034;%kU\021!\021\035\026\004\003\034\005B\003Bs\003G\013\n\021\"\001\003B\006q1m\0349zI\021,g-Y;mi\0222\004B\003Bu\003G\013\n\021\"\001\003l\006q1m\0349zI\021,g-Y;mi\022:TC\001BwU\r\021ib\021\005\t\037\006\r\026\021!C!!\"A!,a)\002\002\023\0051\fC\005a\003G\013\t\021\"\001\003vR\031aDa>\t\021\r\024\0310!AA\002qC\001\"ZAR\003\003%\tE\032\005\n]\006\r\026\021!C\001\005{$2\001\035B\000\021!\031'1`A\001\002\004q\002\002C;\002$\006\005I\021\t<\t\021a\f\031+!A\005BeD\021b_AR\003\003%\tea\002\025\007A\034I\001\003\005d\007\013\t\t\0211\001\037Q!\t\031k!\004\004\024\r]\001cA\007\004\020%\0311\021\003\b\003\025\021,\007O]3dCR,G-\t\002\004\026\005\031Tk]3!\007>t7/[:uK:$\b*Y:iS:<\007k\\8mA=\024\beQ8og&\034H/\0328u\021\006\034\b.\0338h\017J|W\017]\021\003\0073\t1A\r\0304Q\025\t\031k`A\003\021!\ti,!(A\002\r}\001CBAb\003\023\034\t\003\005\003\003\002\r\r\022\002BB\023\005\007\021\001\"Q2u_J\024VM\032\025\t\003;\033ia!\013\004\030\005\02211F\001\033+N,\007eQ8og&\034H/\0328u\021\006\034\b.\0338h\017J|W\017\035\005\b\007_IA\021AB\031\003\031\031'/Z1uKR!\021\021UB\032\021!\til!\fA\002\rU\002#\002*\003J\r\005\002\006CB\027\007\033\031Ica\006\t\023\005\025\022\"!A\005\002\016mB\003EAQ\007{\031yd!\021\004D\r\0253qIB%\021%\t)l!\017\021\002\003\007A\f\003\006\002>\016e\002\023!a\001\003\003D!\"a8\004:A\005\t\031AAr\021)\t\tp!\017\021\002\003\007\021Q\032\005\013\003w\034I\004%AA\002\005}\b\"\003B\t\007s\001\n\0211\001]\021)\021Ib!\017\021\002\003\007!Q\004\005\n\003_I\021\021!CA\007\033\"Baa\024\004XA)Q\"!\016\004RAyQba\025]\003\003\f\031/!4\002\000r\023i\"C\002\004V9\021a\001V;qY\026<\004BCA!\007\027\n\t\0211\001\002\"\"I11L\005\022\002\023\005!\021Y\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017J\031\t\023\r}\023\"%A\005\002\t\035\027a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$#\007C\005\004d%\t\n\021\"\001\003P\006YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIMB\021ba\032\n#\003%\tAa6\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0235\021%\031Y'CI\001\n\003\021y.A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$H%\016\005\n\007_J\021\023!C\001\005\003\f1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\0222\004\"CB:\023E\005I\021\001Bv\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%o!I1qO\005\022\002\023\005!\021Y\001\020CB\004H.\037\023eK\032\fW\017\034;%c!I11P\005\022\002\023\005!qY\001\020CB\004H.\037\023eK\032\fW\017\034;%e!I1qP\005\022\002\023\005!qZ\001\020CB\004H.\037\023eK\032\fW\017\034;%g!I11Q\005\022\002\023\005!q[\001\020CB\004H.\037\023eK\032\fW\017\034;%i!I1qQ\005\022\002\023\005!q\\\001\020CB\004H.\037\023eK\032\fW\017\034;%k!I11R\005\022\002\023\005!\021Y\001\020CB\004H.\037\023eK\032\fW\017\034;%m!I1qR\005\022\002\023\005!1^\001\020CB\004H.\037\023eK\032\fW\017\034;%o!I\021QI\005\002\002\023%\021q\t\025\b\023\r511CB\f\001")
/*     */ public class ConsistentHashingRouter implements DeprecatedRouterConfig, PoolOverrideUnsetConfig<ConsistentHashingRouter>, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final int nrOfInstances;
/*     */   
/*     */   private final Iterable<String> routees;
/*     */   
/*     */   private final Option<Resizer> resizer;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   private final SupervisorStrategy supervisorStrategy;
/*     */   
/*     */   private final int virtualNodesFactor;
/*     */   
/*     */   private final PartialFunction<Object, Object> hashMapping;
/*     */   
/*     */   public static class ConsistentHashableEnvelope implements ConsistentHashable, RouterEnvelope, Product, Serializable {
/*     */     public static final long serialVersionUID = 1L;
/*     */     
/*     */     private final Object message;
/*     */     
/*     */     private final Object hashKey;
/*     */     
/*     */     public Object message() {
/*  54 */       return this.message;
/*     */     }
/*     */     
/*     */     public Object hashKey() {
/*  54 */       return this.hashKey;
/*     */     }
/*     */     
/*     */     public ConsistentHashableEnvelope copy(Object message, Object hashKey) {
/*  54 */       return new ConsistentHashableEnvelope(message, hashKey);
/*     */     }
/*     */     
/*     */     public Object copy$default$1() {
/*  54 */       return message();
/*     */     }
/*     */     
/*     */     public Object copy$default$2() {
/*  54 */       return hashKey();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  54 */       return "ConsistentHashableEnvelope";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  54 */       return 2;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  54 */       int i = x$1;
/*  54 */       switch (i) {
/*     */         default:
/*  54 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  54 */       return message();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  54 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  54 */       return x$1 instanceof ConsistentHashableEnvelope;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  54 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  54 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*  54 */       if (this != x$1) {
/*     */         boolean bool;
/*  54 */         Object object = x$1;
/*  54 */         if (object instanceof ConsistentHashableEnvelope) {
/*  63 */           bool = true;
/*     */         } else {
/*  63 */           bool = false;
/*     */         } 
/*     */         if (bool) {
/*     */           ConsistentHashableEnvelope consistentHashableEnvelope = (ConsistentHashableEnvelope)x$1;
/*     */           if ((BoxesRunTime.equals(message(), consistentHashableEnvelope.message()) && BoxesRunTime.equals(hashKey(), consistentHashableEnvelope.hashKey())));
/*     */         } 
/*     */         return false;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ConsistentHashableEnvelope(Object message, Object hashKey) {
/*     */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public Object consistentHashKey() {
/*     */       return hashKey();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ConsistentHashableEnvelope$ extends AbstractFunction2<Object, Object, ConsistentHashableEnvelope> implements Serializable {
/*     */     public static final ConsistentHashableEnvelope$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "ConsistentHashableEnvelope";
/*     */     }
/*     */     
/*     */     public ConsistentHashingRouter.ConsistentHashableEnvelope apply(Object message, Object hashKey) {
/*     */       return new ConsistentHashingRouter.ConsistentHashableEnvelope(message, hashKey);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<Object, Object>> unapply(ConsistentHashingRouter.ConsistentHashableEnvelope x$0) {
/*     */       return (x$0 == null) ? (Option<Tuple2<Object, Object>>)scala.None$.MODULE$ : (Option<Tuple2<Object, Object>>)new Some(new Tuple2(x$0.message(), x$0.hashKey()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public ConsistentHashableEnvelope$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class emptyConsistentHashMapping$ implements PartialFunction<Object, Object> {
/*     */     public static final emptyConsistentHashMapping$ MODULE$;
/*     */     
/*     */     public static final long serialVersionUID = 1L;
/*     */     
/*     */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/*  71 */       return PartialFunction.class.orElse(this, that);
/*     */     }
/*     */     
/*     */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/*  71 */       return PartialFunction.class.andThen(this, k);
/*     */     }
/*     */     
/*     */     public Function1<Object, Option<Object>> lift() {
/*  71 */       return PartialFunction.class.lift(this);
/*     */     }
/*     */     
/*     */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/*  71 */       return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*     */     }
/*     */     
/*     */     public <U> Function1<Object, Object> runWith(Function1 action) {
/*  71 */       return PartialFunction.class.runWith(this, action);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/*  71 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/*  71 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/*  71 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/*  71 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/*  71 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/*  71 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/*  71 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/*  71 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/*  71 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/*  71 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/*  71 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/*  71 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/*  71 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/*  71 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/*  71 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/*  71 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/*  71 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/*  71 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/*  71 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/*  71 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/*  71 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/*  71 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/*  71 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/*  71 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose(Function1 g) {
/*  71 */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  71 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  71 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  71 */       return Function1.class.toString((Function1)this);
/*     */     }
/*     */     
/*     */     public emptyConsistentHashMapping$() {
/*  71 */       MODULE$ = this;
/*  71 */       Function1.class.$init$((Function1)this);
/*  71 */       PartialFunction.class.$init$(this);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(Object x) {
/*  72 */       return false;
/*     */     }
/*     */     
/*     */     public scala.runtime.Nothing$ apply(Object x) {
/*  73 */       throw new UnsupportedOperationException("Empty ConsistentHashMapping apply()");
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ConsistentHashingRouter$$anonfun$hashMappingAdapter$1 extends AbstractPartialFunction<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ConsistentHashingRouter.ConsistentHashMapper mapper$1;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*  96 */       Object object2, object1 = x1;
/*  97 */       if (this.mapper$1.hashKey(object1) != null) {
/*  98 */         object2 = this.mapper$1.hashKey(object1);
/*     */       } else {
/*     */         object2 = default.apply(x1);
/*     */       } 
/*     */       return (B1)object2;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/*     */       boolean bool;
/*     */       Object object = x1;
/*     */       if (this.mapper$1.hashKey(object) != null) {
/*  98 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public ConsistentHashingRouter$$anonfun$hashMappingAdapter$1(ConsistentHashingRouter.ConsistentHashMapper mapper$1) {}
/*     */   }
/*     */   
/*     */   public static class ConsistentHashingRouter$$anonfun$1 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$1) {
/* 106 */       return x$1.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public final RouterConfig overrideUnsetConfig(RouterConfig other) {
/* 484 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public boolean usePoolDispatcher() {
/* 484 */     return Pool$class.usePoolDispatcher(this);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/* 484 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/* 484 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 484 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 484 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 484 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Props props() {
/* 484 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 484 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 484 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 484 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 484 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter copy(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping) {
/* 484 */     return new ConsistentHashingRouter(
/* 485 */         nrOfInstances, routees, resizer, 
/* 486 */         routerDispatcher, 
/* 487 */         supervisorStrategy, 
/* 488 */         virtualNodesFactor, 
/* 489 */         hashMapping);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "ConsistentHashingRouter";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 7;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 6:
/*     */       
/*     */       case 5:
/*     */       
/*     */       case 4:
/*     */       
/*     */       case 3:
/*     */       
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     return BoxesRunTime.boxToInteger(nrOfInstances());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof ConsistentHashingRouter;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, nrOfInstances());
/*     */     i = Statics.mix(i, Statics.anyHash(routees()));
/*     */     i = Statics.mix(i, Statics.anyHash(resizer()));
/*     */     i = Statics.mix(i, Statics.anyHash(routerDispatcher()));
/*     */     i = Statics.mix(i, Statics.anyHash(supervisorStrategy()));
/*     */     i = Statics.mix(i, virtualNodesFactor());
/*     */     i = Statics.mix(i, Statics.anyHash(hashMapping()));
/*     */     return Statics.finalizeHash(i, 7);
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 232
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/ConsistentHashingRouter
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 236
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/ConsistentHashingRouter
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual nrOfInstances : ()I
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual nrOfInstances : ()I
/*     */     //   40: if_icmpne -> 228
/*     */     //   43: aload_0
/*     */     //   44: invokevirtual routees : ()Lscala/collection/immutable/Iterable;
/*     */     //   47: aload #4
/*     */     //   49: invokevirtual routees : ()Lscala/collection/immutable/Iterable;
/*     */     //   52: astore #5
/*     */     //   54: dup
/*     */     //   55: ifnonnull -> 67
/*     */     //   58: pop
/*     */     //   59: aload #5
/*     */     //   61: ifnull -> 75
/*     */     //   64: goto -> 228
/*     */     //   67: aload #5
/*     */     //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   72: ifeq -> 228
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual resizer : ()Lscala/Option;
/*     */     //   79: aload #4
/*     */     //   81: invokevirtual resizer : ()Lscala/Option;
/*     */     //   84: astore #6
/*     */     //   86: dup
/*     */     //   87: ifnonnull -> 99
/*     */     //   90: pop
/*     */     //   91: aload #6
/*     */     //   93: ifnull -> 107
/*     */     //   96: goto -> 228
/*     */     //   99: aload #6
/*     */     //   101: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   104: ifeq -> 228
/*     */     //   107: aload_0
/*     */     //   108: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   111: aload #4
/*     */     //   113: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   116: astore #7
/*     */     //   118: dup
/*     */     //   119: ifnonnull -> 131
/*     */     //   122: pop
/*     */     //   123: aload #7
/*     */     //   125: ifnull -> 139
/*     */     //   128: goto -> 228
/*     */     //   131: aload #7
/*     */     //   133: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   136: ifeq -> 228
/*     */     //   139: aload_0
/*     */     //   140: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   143: aload #4
/*     */     //   145: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   148: astore #8
/*     */     //   150: dup
/*     */     //   151: ifnonnull -> 163
/*     */     //   154: pop
/*     */     //   155: aload #8
/*     */     //   157: ifnull -> 171
/*     */     //   160: goto -> 228
/*     */     //   163: aload #8
/*     */     //   165: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   168: ifeq -> 228
/*     */     //   171: aload_0
/*     */     //   172: invokevirtual virtualNodesFactor : ()I
/*     */     //   175: aload #4
/*     */     //   177: invokevirtual virtualNodesFactor : ()I
/*     */     //   180: if_icmpne -> 228
/*     */     //   183: aload_0
/*     */     //   184: invokevirtual hashMapping : ()Lscala/PartialFunction;
/*     */     //   187: aload #4
/*     */     //   189: invokevirtual hashMapping : ()Lscala/PartialFunction;
/*     */     //   192: astore #9
/*     */     //   194: dup
/*     */     //   195: ifnonnull -> 207
/*     */     //   198: pop
/*     */     //   199: aload #9
/*     */     //   201: ifnull -> 215
/*     */     //   204: goto -> 228
/*     */     //   207: aload #9
/*     */     //   209: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   212: ifeq -> 228
/*     */     //   215: aload #4
/*     */     //   217: aload_0
/*     */     //   218: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   221: ifeq -> 228
/*     */     //   224: iconst_1
/*     */     //   225: goto -> 229
/*     */     //   228: iconst_0
/*     */     //   229: ifeq -> 236
/*     */     //   232: iconst_1
/*     */     //   233: goto -> 237
/*     */     //   236: iconst_0
/*     */     //   237: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #484	-> 0
/*     */     //   #63	-> 14
/*     */     //   #484	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	238	0	this	Lakka/routing/ConsistentHashingRouter;
/*     */     //   0	238	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public int nrOfInstances() {
/*     */     return this.nrOfInstances;
/*     */   }
/*     */   
/*     */   public Iterable<String> routees() {
/*     */     return this.routees;
/*     */   }
/*     */   
/*     */   public Option<Resizer> resizer() {
/*     */     return this.resizer;
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/*     */     return nrOfInstances();
/*     */   }
/*     */   
/*     */   public Iterable<String> copy$default$2() {
/*     */     return routees();
/*     */   }
/*     */   
/*     */   public Option<Resizer> copy$default$3() {
/*     */     return resizer();
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Group$class.$init$(this);
/*     */     Pool$class.$init$(this);
/*     */     PoolOverrideUnsetConfig$class.$init$(this);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/*     */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public String copy$default$4() {
/*     */     return routerDispatcher();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/*     */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy copy$default$5() {
/*     */     return supervisorStrategy();
/*     */   }
/*     */   
/*     */   public int virtualNodesFactor() {
/*     */     return this.virtualNodesFactor;
/*     */   }
/*     */   
/*     */   public int copy$default$6() {
/*     */     return virtualNodesFactor();
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> hashMapping() {
/* 489 */     return this.hashMapping;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> copy$default$7() {
/* 489 */     return hashMapping();
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter(int nr) {
/* 495 */     this(nr, ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$2(), ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$3(), ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$4(), ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$5(), ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$6(), ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$7());
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter(Iterable routeePaths) {
/* 503 */     this(x$90, (Iterable<String>)x$89, x$91, x$92, x$93, x$94, x$95);
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter(Resizer resizer) {
/* 508 */     this(x$97, x$98, (Option<Resizer>)x$96, x$99, x$100, x$101, x$102);
/*     */   }
/*     */   
/*     */   public Iterable<String> paths() {
/* 510 */     return routees();
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter withDispatcher(String dispatcherId) {
/* 515 */     String x$103 = dispatcherId;
/* 515 */     int x$104 = copy$default$1();
/* 515 */     Iterable<String> x$105 = copy$default$2();
/* 515 */     Option<Resizer> x$106 = copy$default$3();
/* 515 */     SupervisorStrategy x$107 = copy$default$5();
/* 515 */     int x$108 = copy$default$6();
/* 515 */     PartialFunction<Object, Object> x$109 = copy$default$7();
/* 515 */     return copy(x$104, x$105, x$106, x$103, x$107, x$108, x$109);
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter withSupervisorStrategy(SupervisorStrategy strategy) {
/* 521 */     SupervisorStrategy x$110 = strategy;
/* 521 */     int x$111 = copy$default$1();
/* 521 */     Iterable<String> x$112 = copy$default$2();
/* 521 */     Option<Resizer> x$113 = copy$default$3();
/* 521 */     String x$114 = copy$default$4();
/* 521 */     int x$115 = copy$default$6();
/* 521 */     PartialFunction<Object, Object> x$116 = copy$default$7();
/* 521 */     return copy(x$111, x$112, x$113, x$114, x$110, x$115, x$116);
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter withResizer(Resizer resizer) {
/* 526 */     Some x$117 = new Some(resizer);
/* 526 */     int x$118 = copy$default$1();
/* 526 */     Iterable<String> x$119 = copy$default$2();
/* 526 */     String x$120 = copy$default$4();
/* 526 */     SupervisorStrategy x$121 = copy$default$5();
/* 526 */     int x$122 = copy$default$6();
/* 526 */     PartialFunction<Object, Object> x$123 = copy$default$7();
/* 526 */     return copy(x$118, x$119, (Option<Resizer>)x$117, x$120, x$121, x$122, x$123);
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter withVirtualNodesFactor(int vnodes) {
/* 531 */     int x$124 = vnodes, x$125 = copy$default$1();
/* 531 */     Iterable<String> x$126 = copy$default$2();
/* 531 */     Option<Resizer> x$127 = copy$default$3();
/* 531 */     String x$128 = copy$default$4();
/* 531 */     SupervisorStrategy x$129 = copy$default$5();
/* 531 */     PartialFunction<Object, Object> x$130 = copy$default$7();
/* 531 */     return copy(x$125, x$126, x$127, x$128, x$129, x$124, x$130);
/*     */   }
/*     */   
/*     */   public ConsistentHashingRouter withHashMapper(ConsistentHashMapper mapping) {
/* 537 */     PartialFunction<Object, Object> x$131 = ConsistentHashingRouter$.MODULE$.hashMappingAdapter(mapping);
/* 537 */     int x$132 = copy$default$1();
/* 537 */     Iterable<String> x$133 = copy$default$2();
/* 537 */     Option<Resizer> x$134 = copy$default$3();
/* 537 */     String x$135 = copy$default$4();
/* 537 */     SupervisorStrategy x$136 = copy$default$5();
/* 537 */     int x$137 = copy$default$6();
/* 537 */     return copy(x$132, x$133, x$134, x$135, x$136, x$137, x$131);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/*     */     boolean bool;
/* 545 */     RouterConfig routerConfig = other;
/* 546 */     if (routerConfig instanceof FromConfig) {
/* 546 */       bool = true;
/* 546 */     } else if (routerConfig instanceof NoRouter) {
/* 546 */       bool = true;
/*     */     } else {
/* 546 */       bool = false;
/*     */     } 
/* 546 */     if (bool) {
/* 546 */       RouterConfig routerConfig1 = overrideUnsetConfig(other);
/*     */     } else {
/* 547 */       if (routerConfig instanceof ConsistentHashingRouter) {
/* 547 */         ConsistentHashingRouter consistentHashingRouter = (ConsistentHashingRouter)routerConfig;
/* 547 */         PartialFunction<Object, Object> x$138 = consistentHashingRouter.hashMapping();
/* 547 */         int x$139 = copy$default$1();
/* 547 */         Iterable<String> x$140 = copy$default$2();
/* 547 */         Option<Resizer> x$141 = copy$default$3();
/* 547 */         String x$142 = copy$default$4();
/* 547 */         SupervisorStrategy x$143 = copy$default$5();
/* 547 */         int x$144 = copy$default$6();
/* 547 */         return copy(x$139, x$140, x$141, x$142, x$143, x$144, x$138).overrideUnsetConfig(other);
/*     */       } 
/* 548 */       throw new IllegalArgumentException((new StringOps(scala.Predef$.MODULE$.augmentString("Expected ConsistentHashingRouter, got [%s]"))).format(scala.Predef$.MODULE$.genericWrapArray(new Object[] { other })));
/*     */     } 
/*     */     return (RouterConfig)SYNTHETIC_LOCAL_VARIABLE_4;
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 552 */     return new Router(new ConsistentHashingRoutingLogic(system, virtualNodesFactor(), hashMapping()));
/*     */   }
/*     */   
/*     */   public static PartialFunction<Object, Object> apply$default$7() {
/*     */     return ConsistentHashingRouter$.MODULE$.apply$default$7();
/*     */   }
/*     */   
/*     */   public static int apply$default$6() {
/*     */     return ConsistentHashingRouter$.MODULE$.apply$default$6();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$5() {
/*     */     return ConsistentHashingRouter$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static String apply$default$4() {
/*     */     return ConsistentHashingRouter$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$3() {
/*     */     return ConsistentHashingRouter$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Iterable<String> apply$default$2() {
/*     */     return ConsistentHashingRouter$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static int apply$default$1() {
/*     */     return ConsistentHashingRouter$.MODULE$.apply$default$1();
/*     */   }
/*     */   
/*     */   public static PartialFunction<Object, Object> $lessinit$greater$default$7() {
/*     */     return ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$7();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$6() {
/*     */     return ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$6();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$5() {
/*     */     return ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$4() {
/*     */     return ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$3() {
/*     */     return ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Iterable<String> $lessinit$greater$default$2() {
/*     */     return ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$1() {
/*     */     return ConsistentHashingRouter$.MODULE$.$lessinit$greater$default$1();
/*     */   }
/*     */   
/*     */   public static ConsistentHashingRouter create(Iterable<ActorRef> paramIterable) {
/*     */     return ConsistentHashingRouter$.MODULE$.create(paramIterable);
/*     */   }
/*     */   
/*     */   public static ConsistentHashingRouter apply(Iterable<ActorRef> paramIterable) {
/*     */     return ConsistentHashingRouter$.MODULE$.apply(paramIterable);
/*     */   }
/*     */   
/*     */   public static interface ConsistentHashable {
/*     */     Object consistentHashKey();
/*     */   }
/*     */   
/*     */   public static interface ConsistentHashMapper {
/*     */     Object hashKey(Object param1Object);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentHashingRouter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */