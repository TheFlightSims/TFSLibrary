/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorLogging;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.NoSerializationVerificationNeeded;
/*     */ import akka.actor.OneForOneStrategy;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.ScalaActorRef;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.dispatch.MessageDispatcher;
/*     */ import akka.dispatch.RequiresMessageQueue;
/*     */ import akka.dispatch.UnboundedMessageQueueSemantics;
/*     */ import akka.event.Logging;
/*     */ import akka.event.LoggingAdapter;
/*     */ import akka.routing.RandomRouter;
/*     */ import java.nio.channels.CancelledKeyException;
/*     */ import java.nio.channels.SelectableChannel;
/*     */ import java.nio.channels.SelectionKey;
/*     */ import java.nio.channels.spi.AbstractSelector;
/*     */ import java.nio.channels.spi.SelectorProvider;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Statics;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021MrAB\001\003\021\003\021a!\001\tTK2,7\r^5p]\"\013g\016\0327fe*\0211\001B\001\003S>T\021!B\001\005C.\\\027\r\005\002\b\0215\t!A\002\004\n\005!\005!A\003\002\021'\026dWm\031;j_:D\025M\0343mKJ\034\"\001C\006\021\0051yQ\"A\007\013\0039\tQa]2bY\006L!\001E\007\003\r\005s\027PU3g\021\025\021\002\002\"\001\025\003\031a\024N\\5u}\r\001A#\001\004\007\017YA\001\023aI\001/\t\t\002*Y:GC&dWO]3NKN\034\030mZ3\024\005UY\001\"B\r\026\r\003Q\022A\0044bS2,(/Z'fgN\fw-Z\013\0027A\021A\002H\005\003;5\0211!\0218z\r\021y\002\002\021\021\003!]{'o[3s\r>\0248i\\7nC:$7#\002\020\fC\035R\003C\001\022&\033\005\031#B\001\023\005\003\025\t7\r^8s\023\t13EA\021O_N+'/[1mSj\fG/[8o-\026\024\030NZ5dCRLwN\034(fK\022,G\r\005\002\rQ%\021\021&\004\002\b!J|G-^2u!\ta1&\003\002-\033\ta1+\032:jC2L'0\0312mK\"AaF\bBK\002\023\005q&\001\006ba&\034u.\\7b]\022,\022\001\r\t\003cUi\021\001\003\005\tgy\021\t\022)A\005a\005Y\021\r]5D_6l\027M\0343!\021!)dD!f\001\n\0031\024!C2p[6\fg\016Z3s+\0059\004C\001\0229\023\tI4E\001\005BGR|'OU3g\021!YdD!E!\002\0239\024AC2p[6\fg\016Z3sA!AQH\bBK\002\023\005a(\001\006dQ&dG\r\025:paN,\022a\020\t\005\031\001\023U)\003\002B\033\tIa)\0368di&|g.\r\t\003\017\rK!\001\022\002\003\037\rC\027M\0348fYJ+w-[:uef\004\"A\t$\n\005\035\033#!\002)s_B\034\b\002C%\037\005#\005\013\021B \002\027\rD\027\016\0343Qe>\0048\017\t\005\006%y!\ta\023\013\005\0316su\n\005\0022=!)aF\023a\001a!)QG\023a\001o!)QH\023a\001!9\021KHA\001\n\003\021\026\001B2paf$B\001T*U+\"9a\006\025I\001\002\004\001\004bB\033Q!\003\005\ra\016\005\b{A\003\n\0211\001@\021\0359f$%A\005\002a\013abY8qs\022\"WMZ1vYR$\023'F\001ZU\t\001$lK\001\\!\ta\026-D\001^\025\tqv,A\005v]\016DWmY6fI*\021\001-D\001\013C:tw\016^1uS>t\027B\0012^\005E)hn\0315fG.,GMV1sS\006t7-\032\005\bIz\t\n\021\"\001f\0039\031w\016]=%I\0264\027-\0367uII*\022A\032\026\003oiCq\001\033\020\022\002\023\005\021.\001\bd_BLH\005Z3gCVdG\017J\032\026\003)T#a\020.\t\0171t\022\021!C![\006i\001O]8ek\016$\bK]3gSb,\022A\034\t\003_Rl\021\001\035\006\003cJ\fA\001\\1oO*\t1/\001\003kCZ\f\027BA;q\005\031\031FO]5oO\"9qOHA\001\n\003A\030\001\0049s_\022,8\r^!sSRLX#A=\021\0051Q\030BA>\016\005\rIe\016\036\005\b{z\t\t\021\"\001\0039\001(o\0343vGR,E.Z7f]R$\"aG@\t\021\005\005A0!AA\002e\f1\001\037\0232\021%\t)AHA\001\n\003\n9!A\bqe>$Wo\031;Ji\026\024\030\r^8s+\t\tI\001E\003\002\f\005E1$\004\002\002\016)\031\021qB\007\002\025\r|G\016\\3di&|g.\003\003\002\024\0055!\001C%uKJ\fGo\034:\t\023\005]a$!A\005\002\005e\021\001C2b]\026\013X/\0317\025\t\005m\021\021\005\t\004\031\005u\021bAA\020\033\t9!i\\8mK\006t\007\"CA\001\003+\t\t\0211\001\034\021%\t)CHA\001\n\003\n9#\001\005iCND7i\0343f)\005I\b\"CA\026=\005\005I\021IA\027\003!!xn\025;sS:<G#\0018\t\023\005Eb$!A\005B\005M\022AB3rk\006d7\017\006\003\002\034\005U\002\"CA\001\003_\t\t\0211\001\034\017%\tI\004CA\001\022\003\tY$\001\tX_J\\WM\035$pe\016{W.\\1oIB\031\021'!\020\007\021}A\021\021!E\001\003\031R!!\020\002B)\002\002\"a\021\002JA:t\bT\007\003\003\013R1!a\022\016\003\035\021XO\034;j[\026LA!a\023\002F\t\t\022IY:ue\006\034GOR;oGRLwN\\\032\t\017I\ti\004\"\001\002PQ\021\0211\b\005\013\003W\ti$!A\005F\0055\002BCA+\003{\t\t\021\"!\002X\005)\021\r\0359msR9A*!\027\002\\\005u\003B\002\030\002T\001\007\001\007\003\0046\003'\002\ra\016\005\007{\005M\003\031A \t\025\005\005\024QHA\001\n\003\013\031'A\004v]\006\004\b\017\\=\025\t\005\025\024\021\017\t\006\031\005\035\0241N\005\004\003Sj!AB(qi&|g\016\005\004\r\003[\002tgP\005\004\003_j!A\002+va2,7\007C\005\002t\005}\023\021!a\001\031\006\031\001\020\n\031\t\025\005]\024QHA\001\n\023\tI(A\006sK\006$'+Z:pYZ,GCAA>!\ry\027QP\005\004\003\002(AB(cU\026\034GO\002\004\002\004\"\001\025Q\021\002\006%\026$(/_\n\007\003\003[\021e\n\026\t\027\005%\025\021\021BK\002\023\005\0211R\001\bG>lW.\0318e+\005a\005BCAH\003\003\023\t\022)A\005\031\006A1m\\7nC:$\007\005\003\006\002\024\006\005%Q3A\005\002a\f1B]3ue&,7\017T3gi\"Q\021qSAA\005#\005\013\021B=\002\031I,GO]5fg2+g\r\036\021\t\017I\t\t\t\"\001\002\034R1\021QTAP\003C\0032!MAA\021\035\tI)!'A\0021Cq!a%\002\032\002\007\021\020C\005R\003\003\013\t\021\"\001\002&R1\021QTAT\003SC\021\"!#\002$B\005\t\031\001'\t\023\005M\0251\025I\001\002\004I\b\"C,\002\002F\005I\021AAW+\t\tyK\013\002M5\"IA-!!\022\002\023\005\0211W\013\003\003kS#!\037.\t\0211\f\t)!A\005B5D\001b^AA\003\003%\t\001\037\005\n{\006\005\025\021!C\001\003{#2aGA`\021%\t\t!a/\002\002\003\007\021\020\003\006\002\006\005\005\025\021!C!\003\017A!\"a\006\002\002\006\005I\021AAc)\021\tY\"a2\t\023\005\005\0211YA\001\002\004Y\002BCA\023\003\003\013\t\021\"\021\002(!Q\0211FAA\003\003%\t%!\f\t\025\005E\022\021QA\001\n\003\ny\r\006\003\002\034\005E\007\"CA\001\003\033\f\t\0211\001\034\017%\t)\016CA\001\022\003\t9.A\003SKR\024\030\020E\0022\00334\021\"a!\t\003\003E\t!a7\024\013\005e\027Q\034\026\021\021\005\r\023q\034'z\003;KA!!9\002F\t\t\022IY:ue\006\034GOR;oGRLwN\034\032\t\017I\tI\016\"\001\002fR\021\021q\033\005\013\003W\tI.!A\005F\0055\002BCA+\0033\f\t\021\"!\002lR1\021QTAw\003_Dq!!#\002j\002\007A\nC\004\002\024\006%\b\031A=\t\025\005\005\024\021\\A\001\n\003\013\031\020\006\003\002v\006u\b#\002\007\002h\005]\b#\002\007\002z2K\030bAA~\033\t1A+\0369mKJB!\"a\035\002r\006\005\t\031AAO\021)\t9(!7\002\002\023%\021\021P\004\b\005\007A\001\022\021B\003\003I\031\005.\0318oK2\034uN\0348fGR\f'\r\\3\021\007E\0229AB\004\003\n!A\tIa\003\003%\rC\027M\0348fY\016{gN\\3di\006\024G.Z\n\006\005\017YqE\013\005\b%\t\035A\021\001B\b)\t\021)\001\003\005m\005\017\t\t\021\"\021n\021!9(qAA\001\n\003A\b\"C?\003\b\005\005I\021\001B\f)\rY\"\021\004\005\n\003\003\021)\"!AA\002eD!\"!\002\003\b\005\005I\021IA\004\021)\t9Ba\002\002\002\023\005!q\004\013\005\0037\021\t\003C\005\002\002\tu\021\021!a\0017!Q\021Q\005B\004\003\003%\t%a\n\t\025\005-\"qAA\001\n\003\ni\003\003\006\002x\t\035\021\021!C\005\003s:qAa\013\t\021\003\023i#A\tDQ\006tg.\0327BG\016,\007\017^1cY\026\0042!\rB\030\r\035\021\t\004\003EA\005g\021\021c\0215b]:,G.Q2dKB$\030M\0317f'\025\021ycC\024+\021\035\021\"q\006C\001\005o!\"A!\f\t\0211\024y#!A\005B5D\001b\036B\030\003\003%\t\001\037\005\n{\n=\022\021!C\001\005!2a\007B!\021%\t\tA!\020\002\002\003\007\021\020\003\006\002\006\t=\022\021!C!\003\017A!\"a\006\0030\005\005I\021\001B$)\021\tYB!\023\t\023\005\005!QIA\001\002\004Y\002BCA\023\005_\t\t\021\"\021\002(!Q\0211\006B\030\003\003%\t%!\f\t\025\005]$qFA\001\n\023\tIhB\004\003T!A\tI!\026\002\037\rC\027M\0348fYJ+\027\rZ1cY\026\0042!\rB,\r\035\021I\006\003EA\0057\022qb\0215b]:,GNU3bI\006\024G.Z\n\006\005/ZqE\013\005\b%\t]C\021\001B0)\t\021)\006\003\005m\005/\n\t\021\"\021n\021!9(qKA\001\n\003A\b\"C?\003X\005\005I\021\001B4)\rY\"\021\016\005\n\003\003\021)'!AA\002eD!\"!\002\003X\005\005I\021IA\004\021)\t9Ba\026\002\002\023\005!q\016\013\005\0037\021\t\bC\005\002\002\t5\024\021!a\0017!Q\021Q\005B,\003\003%\t%a\n\t\025\005-\"qKA\001\n\003\ni\003\003\006\002x\t]\023\021!C\005\003s:qAa\037\t\021\003\023i(A\bDQ\006tg.\0327Xe&$\030M\0317f!\r\t$q\020\004\b\005\003C\001\022\021BB\005=\031\005.\0318oK2<&/\033;bE2,7#\002B@\027\035R\003b\002\n\003\000\021\005!q\021\013\003\005{B\001\002\034B@\003\003%\t%\034\005\to\n}\024\021!C\001q\"IQPa \002\002\023\005!q\022\013\0047\tE\005\"CA\001\005\033\013\t\0211\001z\021)\t)Aa \002\002\023\005\023q\001\005\013\003/\021y(!A\005\002\t]E\003BA\016\0053C\021\"!\001\003\026\006\005\t\031A\016\t\025\005\025\"qPA\001\n\003\n9\003\003\006\002,\t}\024\021!C!\003[A!\"a\036\003\000\005\005I\021BA=\r!\021\031\013CA\001\005\t\025&\001F*fY\026\034Go\034:CCN,G-T1oC\036,'oE\003\003\".\0219\013E\002#\005SK1Aa+$\005\025\t5\r^8s\021-\021yK!)\003\002\003\006IA!-\002!M,G.Z2u_J\034V\r\036;j]\036\034\bcA\004\0034&\031!Q\027\002\0031M+G.Z2uS>t\007*\0318eY\026\0248+\032;uS:<7\017\003\006\003:\n\005&\021!Q\001\ne\fQB\034:PMN+G.Z2u_J\034\bb\002\n\003\"\022\005!Q\030\013\007\005\023\tMa1\021\007E\022\t\013\003\005\0030\nm\006\031\001BY\021\035\021ILa/A\002eD\001Ba2\003\"\022\005#\021Z\001\023gV\004XM\035<jg>\0248\013\036:bi\026<\0270\006\002\003LB\031!E!4\n\007\t=7E\001\nTkB,'O^5t_J\034FO]1uK\036L\b\"\003Bj\005C\023\r\021\"\0017\0031\031X\r\\3di>\024\bk\\8m\021!\0219N!)!\002\0239\024!D:fY\026\034Go\034:Q_>d\007\005\003\005\003\\\n\005FQ\001Bo\003]9xN]6fe\032{'oQ8n[\006tG\rS1oI2,'\017\006\003\003`\n\035\b\003\002Bq\005Gl!A!)\n\t\t\025(\021\026\002\b%\026\034W-\033<f\021!\021IO!7A\002\t-\030A\0019g!\025a!Q\036\031@\023\r\021y/\004\002\020!\006\024H/[1m\rVt7\r^5p]\"Q!1\037\005C\002\023\025!A!3\0029\r|gN\\3di&|gnU;qKJ4\030n]8s'R\024\030\r^3hs\"A!q\037\005!\002\033\021Y-A\017d_:tWm\031;j_:\034V\017]3sm&\034xN]*ue\006$XmZ=!\r\031\021Y\020\003\003\003~\n\0312\t[1o]\026d'+Z4jgR\024\0300S7qYN!!\021`\006C\021-\031\tA!?\003\002\003\006Iaa\001\002!\025DXmY;uS>t7i\0348uKb$\b\003BB\003\007\027i!aa\002\013\007\r%Q\"\001\006d_:\034WO\035:f]RLAa!\004\004\b\t\001R\t_3dkRLwN\\\"p]R,\007\020\036\005\f\007#\021IP!A!\002\023\031\031\"A\002m_\036\004Ba!\006\004\0345\0211q\003\006\004\0073!\021!B3wK:$\030\002BB\017\007/\021a\002T8hO&tw-\0213baR,'\017C\004\023\005s$\ta!\t\025\r\r\r2QEB\024!\r\t$\021 \005\t\007\003\031y\0021\001\004\004!A1\021CB\020\001\004\031\031\002C\005\004,\te\b\025!\003\004.\005A1/\0327fGR|'\017\005\003\0040\ruRBAB\031\025\021\031\031d!\016\002\007M\004\030N\003\003\0048\re\022\001C2iC:tW\r\\:\013\007\rm\"/A\002oS>LAaa\020\0042\t\001\022IY:ue\006\034GoU3mK\016$xN\035\005\n\007\007\022I\020)A\005\007\013\naa^1lKV\003\b\003BB$\007'j!a!\023\013\t\r-3QJ\001\007CR|W.[2\013\t\r%1q\n\006\004\007#\022\030\001B;uS2LAa!\026\004J\ti\021\t^8nS\016\024un\0347fC:D!b!\027\003z\n\007IQAB.\003Ey\005k\030*F\003\022{\026I\024#`/JKE+R\013\003\007;z!aa\030\036\003\025A\021ba\031\003z\002\006ia!\030\002%=\003vLU#B\t~\013e\nR0X%&#V\t\t\005\n\007O\022I\020)A\005\007S\naa]3mK\016$\b\003BB6\007[j!A!?\007\021\r=$\021`A\005\007c\022A\001V1tWN11QNA>\007g\0022a\\B;\023\r\0319\b\035\002\t%Vtg.\0312mK\"9!c!\034\005\002\rmDCAB5\021!\031yh!\034\007\002\r\005\025A\002;ssJ+h\016\006\002\004\004B\031Ab!\"\n\007\r\035UB\001\003V]&$\b\002CBF\007[\"\ta!!\002\007I,h\016\003\005\004\020\neH\021ABI\003!\021XmZ5ti\026\024HCBBJ\0073\033)\013\006\003\004\004\016U\005bBBL\007\033\003\035aN\001\rG\"\fgN\\3m\003\016$xN\035\005\t\0077\033i\t1\001\004\036\00691\r[1o]\026d\007\003BBP\007Ck!a!\016\n\t\r\r6Q\007\002\022'\026dWm\031;bE2,7\t[1o]\026d\007bBBT\007\033\003\r!_\001\013S:LG/[1m\037B\034\b\002CBV\005s$\ta!!\002\021MDW\017\0363po:D\001ba,\003z\022%1\021W\001\022K:\f'\r\\3J]R,'/Z:u\037B\034HCBBB\007g\033i\f\003\005\0046\0165\006\031AB\\\003\rYW-\037\t\005\007?\033I,\003\003\004<\016U\"\001D*fY\026\034G/[8o\027\026L\bbBB`\007[\003\r!_\001\004_B\034\b\002CBb\005s$Ia!2\002%\021L7/\0312mK&sG/\032:fgR|\005o\035\013\007\007\007\0339m!3\t\021\rU6\021\031a\001\007oCqaa0\004B\002\007\021\020\003\005\004N\neH\021BBh\003\035)\0070Z2vi\026$Baa!\004R\"A11[Bf\001\004\031I'\001\003uCN\\gAB\005\003\001\t\0319nE\005\004V.\0219k!7\004`B\031!ea7\n\007\ru7E\001\007BGR|'\017T8hO&tw\r\005\004\004b\016\03581^\007\003\007GT1a!:\005\003!!\027n\0359bi\016D\027\002BBu\007G\024ACU3rk&\024Xm]'fgN\fw-Z)vKV,\007\003BBq\007[LAaa<\004d\nqRK\0342pk:$W\rZ'fgN\fw-Z)vKV,7+Z7b]RL7m\035\005\f\007g\034)N!A!\002\023\021\t,\001\005tKR$\030N\\4t\021\035\0212Q\033C\001\007o$Ba!?\004|B\031qa!6\t\021\rM8Q\037a\001\005cC\001ba@\004V\002\006K!_\001\017g\026\fX/\0328dK:+XNY3s\021!!\031a!6!B\023I\030AC2iS2$7i\\;oi\"IAqABkA\003%A\021B\001\te\026<\027n\035;ssB!A1\002B}\035\t9\001\001\003\005\005\020\rUG\021\001C\t\003\035\021XmY3jm\026,\"\001b\005\021\t\021U!1]\007\003\007+D\001\002\"\007\004V\022\0053\021Q\001\ta>\034Ho\025;pa\"A!qYBk\t\003\"i\"\006\002\005 A\031!\005\"\t\n\007\021\r2EA\tP]\0264uN](oKN#(/\031;fOfD\001\002b\n\004V\022\005A\021F\001!gB\fwO\\\"iS2$w+\033;i\007\006\004\030mY5usB\023x\016^3di&|g\016\006\004\004\004\022-B\021\007\005\t\t[!)\0031\001\0050\005\0311-\0343\021\007\021-a\004C\004\002\024\022\025\002\031A=")
/*     */ public class SelectionHandler implements Actor, ActorLogging, RequiresMessageQueue<UnboundedMessageQueueSemantics> {
/*     */   public final SelectionHandlerSettings akka$io$SelectionHandler$$settings;
/*     */   
/*     */   private int sequenceNumber;
/*     */   
/*     */   public int akka$io$SelectionHandler$$childCount;
/*     */   
/*     */   private final ChannelRegistryImpl registry;
/*     */   
/*     */   private LoggingAdapter akka$actor$ActorLogging$$_log;
/*     */   
/*     */   private final ActorContext context;
/*     */   
/*     */   private final ActorRef self;
/*     */   
/*     */   public static class WorkerForCommand implements NoSerializationVerificationNeeded, Product, Serializable {
/*     */     private final SelectionHandler.HasFailureMessage apiCommand;
/*     */     
/*     */     private final ActorRef commander;
/*     */     
/*     */     private final Function1<ChannelRegistry, Props> childProps;
/*     */     
/*     */     public SelectionHandler.HasFailureMessage apiCommand() {
/*  68 */       return this.apiCommand;
/*     */     }
/*     */     
/*     */     public ActorRef commander() {
/*  68 */       return this.commander;
/*     */     }
/*     */     
/*     */     public Function1<ChannelRegistry, Props> childProps() {
/*  68 */       return this.childProps;
/*     */     }
/*     */     
/*     */     public WorkerForCommand copy(SelectionHandler.HasFailureMessage apiCommand, ActorRef commander, Function1<ChannelRegistry, Props> childProps) {
/*  68 */       return new WorkerForCommand(apiCommand, commander, childProps);
/*     */     }
/*     */     
/*     */     public SelectionHandler.HasFailureMessage copy$default$1() {
/*  68 */       return apiCommand();
/*     */     }
/*     */     
/*     */     public ActorRef copy$default$2() {
/*  68 */       return commander();
/*     */     }
/*     */     
/*     */     public Function1<ChannelRegistry, Props> copy$default$3() {
/*  68 */       return childProps();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  68 */       return "WorkerForCommand";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  68 */       return 3;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  68 */       int i = x$1;
/*  68 */       switch (i) {
/*     */         default:
/*  68 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 2:
/*     */         
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  68 */       return apiCommand();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  68 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  68 */       return x$1 instanceof WorkerForCommand;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  68 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  68 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 144
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/io/SelectionHandler$WorkerForCommand
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 148
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/SelectionHandler$WorkerForCommand
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual apiCommand : ()Lakka/io/SelectionHandler$HasFailureMessage;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual apiCommand : ()Lakka/io/SelectionHandler$HasFailureMessage;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 140
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 140
/*     */       //   63: aload_0
/*     */       //   64: invokevirtual commander : ()Lakka/actor/ActorRef;
/*     */       //   67: aload #4
/*     */       //   69: invokevirtual commander : ()Lakka/actor/ActorRef;
/*     */       //   72: astore #6
/*     */       //   74: dup
/*     */       //   75: ifnonnull -> 87
/*     */       //   78: pop
/*     */       //   79: aload #6
/*     */       //   81: ifnull -> 95
/*     */       //   84: goto -> 140
/*     */       //   87: aload #6
/*     */       //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   92: ifeq -> 140
/*     */       //   95: aload_0
/*     */       //   96: invokevirtual childProps : ()Lscala/Function1;
/*     */       //   99: aload #4
/*     */       //   101: invokevirtual childProps : ()Lscala/Function1;
/*     */       //   104: astore #7
/*     */       //   106: dup
/*     */       //   107: ifnonnull -> 119
/*     */       //   110: pop
/*     */       //   111: aload #7
/*     */       //   113: ifnull -> 127
/*     */       //   116: goto -> 140
/*     */       //   119: aload #7
/*     */       //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   124: ifeq -> 140
/*     */       //   127: aload #4
/*     */       //   129: aload_0
/*     */       //   130: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   133: ifeq -> 140
/*     */       //   136: iconst_1
/*     */       //   137: goto -> 141
/*     */       //   140: iconst_0
/*     */       //   141: ifeq -> 148
/*     */       //   144: iconst_1
/*     */       //   145: goto -> 149
/*     */       //   148: iconst_0
/*     */       //   149: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #68	-> 0
/*     */       //   #63	-> 14
/*     */       //   #68	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	150	0	this	Lakka/io/SelectionHandler$WorkerForCommand;
/*     */       //   0	150	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public WorkerForCommand(SelectionHandler.HasFailureMessage apiCommand, ActorRef commander, Function1<ChannelRegistry, Props> childProps) {
/*  68 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WorkerForCommand$ extends AbstractFunction3<HasFailureMessage, ActorRef, Function1<ChannelRegistry, Props>, WorkerForCommand> implements Serializable {
/*     */     public static final WorkerForCommand$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*  68 */       return "WorkerForCommand";
/*     */     }
/*     */     
/*     */     public SelectionHandler.WorkerForCommand apply(SelectionHandler.HasFailureMessage apiCommand, ActorRef commander, Function1<ChannelRegistry, Props> childProps) {
/*  68 */       return new SelectionHandler.WorkerForCommand(apiCommand, commander, childProps);
/*     */     }
/*     */     
/*     */     public Option<Tuple3<SelectionHandler.HasFailureMessage, ActorRef, Function1<ChannelRegistry, Props>>> unapply(SelectionHandler.WorkerForCommand x$0) {
/*  68 */       return (x$0 == null) ? (Option<Tuple3<SelectionHandler.HasFailureMessage, ActorRef, Function1<ChannelRegistry, Props>>>)scala.None$.MODULE$ : (Option<Tuple3<SelectionHandler.HasFailureMessage, ActorRef, Function1<ChannelRegistry, Props>>>)new Some(new Tuple3(x$0.apiCommand(), x$0.commander(), x$0.childProps()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  68 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public WorkerForCommand$() {
/*  68 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Retry implements NoSerializationVerificationNeeded, Product, Serializable {
/*     */     private final SelectionHandler.WorkerForCommand command;
/*     */     
/*     */     private final int retriesLeft;
/*     */     
/*     */     public SelectionHandler.WorkerForCommand command() {
/*  71 */       return this.command;
/*     */     }
/*     */     
/*     */     public int retriesLeft() {
/*  71 */       return this.retriesLeft;
/*     */     }
/*     */     
/*     */     public Retry copy(SelectionHandler.WorkerForCommand command, int retriesLeft) {
/*  71 */       return new Retry(command, retriesLeft);
/*     */     }
/*     */     
/*     */     public SelectionHandler.WorkerForCommand copy$default$1() {
/*  71 */       return command();
/*     */     }
/*     */     
/*     */     public int copy$default$2() {
/*  71 */       return retriesLeft();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  71 */       return "Retry";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  71 */       return 2;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  71 */       int i = x$1;
/*  71 */       switch (i) {
/*     */         default:
/*  71 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  71 */       return command();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  71 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  71 */       return x$1 instanceof Retry;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  71 */       int i = -889275714;
/*  71 */       i = Statics.mix(i, Statics.anyHash(command()));
/*  71 */       i = Statics.mix(i, retriesLeft());
/*  71 */       return Statics.finalizeHash(i, 2);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  71 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 92
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/io/SelectionHandler$Retry
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 96
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/SelectionHandler$Retry
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual command : ()Lakka/io/SelectionHandler$WorkerForCommand;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual command : ()Lakka/io/SelectionHandler$WorkerForCommand;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 88
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 88
/*     */       //   63: aload_0
/*     */       //   64: invokevirtual retriesLeft : ()I
/*     */       //   67: aload #4
/*     */       //   69: invokevirtual retriesLeft : ()I
/*     */       //   72: if_icmpne -> 88
/*     */       //   75: aload #4
/*     */       //   77: aload_0
/*     */       //   78: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   81: ifeq -> 88
/*     */       //   84: iconst_1
/*     */       //   85: goto -> 89
/*     */       //   88: iconst_0
/*     */       //   89: ifeq -> 96
/*     */       //   92: iconst_1
/*     */       //   93: goto -> 97
/*     */       //   96: iconst_0
/*     */       //   97: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #71	-> 0
/*     */       //   #63	-> 14
/*     */       //   #71	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	98	0	this	Lakka/io/SelectionHandler$Retry;
/*     */       //   0	98	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Retry(SelectionHandler.WorkerForCommand command, int retriesLeft) {
/*  71 */       Product.class.$init$(this);
/*  71 */       scala.Predef$.MODULE$.require((retriesLeft >= 0));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Retry$ extends AbstractFunction2<WorkerForCommand, Object, Retry> implements Serializable {
/*     */     public static final Retry$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*  71 */       return "Retry";
/*     */     }
/*     */     
/*     */     public SelectionHandler.Retry apply(SelectionHandler.WorkerForCommand command, int retriesLeft) {
/*  71 */       return new SelectionHandler.Retry(command, retriesLeft);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<SelectionHandler.WorkerForCommand, Object>> unapply(SelectionHandler.Retry x$0) {
/*  71 */       return (x$0 == null) ? (Option<Tuple2<SelectionHandler.WorkerForCommand, Object>>)scala.None$.MODULE$ : (Option<Tuple2<SelectionHandler.WorkerForCommand, Object>>)new Some(new Tuple2(x$0.command(), BoxesRunTime.boxToInteger(x$0.retriesLeft())));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  71 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Retry$() {
/*  71 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ChannelConnectable$ implements Product, Serializable {
/*     */     public static final ChannelConnectable$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/*  73 */       return "ChannelConnectable";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  73 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  73 */       int i = x$1;
/*  73 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  73 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  73 */       return x$1 instanceof ChannelConnectable$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  73 */       return 2078163041;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  73 */       return "ChannelConnectable";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  73 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public ChannelConnectable$() {
/*  74 */       MODULE$ = this;
/*  74 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ChannelAcceptable$ implements Product, Serializable {
/*     */     public static final ChannelAcceptable$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/*  74 */       return "ChannelAcceptable";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  74 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  74 */       int i = x$1;
/*  74 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  74 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  74 */       return x$1 instanceof ChannelAcceptable$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  74 */       return 1555756965;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  74 */       return "ChannelAcceptable";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  74 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public ChannelAcceptable$() {
/*  75 */       MODULE$ = this;
/*  75 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ChannelReadable$ implements Product, Serializable {
/*     */     public static final ChannelReadable$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/*  75 */       return "ChannelReadable";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  75 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  75 */       int i = x$1;
/*  75 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  75 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  75 */       return x$1 instanceof ChannelReadable$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  75 */       return -1396106765;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  75 */       return "ChannelReadable";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  75 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public ChannelReadable$() {
/*  76 */       MODULE$ = this;
/*  76 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ChannelWritable$ implements Product, Serializable {
/*     */     public static final ChannelWritable$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/*  76 */       return "ChannelWritable";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  76 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  76 */       int i = x$1;
/*  76 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  76 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  76 */       return x$1 instanceof ChannelWritable$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  76 */       return 1919433123;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  76 */       return "ChannelWritable";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  76 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public ChannelWritable$() {
/*  78 */       MODULE$ = this;
/*  78 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class SelectorBasedManager implements Actor {
/*     */     private final ActorRef selectorPool;
/*     */     
/*     */     private final ActorContext context;
/*     */     
/*     */     private final ActorRef self;
/*     */     
/*     */     public ActorContext context() {
/*  78 */       return this.context;
/*     */     }
/*     */     
/*     */     public final ActorRef self() {
/*  78 */       return this.self;
/*     */     }
/*     */     
/*     */     public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*  78 */       this.context = x$1;
/*     */     }
/*     */     
/*     */     public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*  78 */       this.self = x$1;
/*     */     }
/*     */     
/*     */     public final ActorRef sender() {
/*  78 */       return Actor.class.sender(this);
/*     */     }
/*     */     
/*     */     public void aroundReceive(PartialFunction receive, Object msg) {
/*  78 */       Actor.class.aroundReceive(this, receive, msg);
/*     */     }
/*     */     
/*     */     public void aroundPreStart() {
/*  78 */       Actor.class.aroundPreStart(this);
/*     */     }
/*     */     
/*     */     public void aroundPostStop() {
/*  78 */       Actor.class.aroundPostStop(this);
/*     */     }
/*     */     
/*     */     public void aroundPreRestart(Throwable reason, Option message) {
/*  78 */       Actor.class.aroundPreRestart(this, reason, message);
/*     */     }
/*     */     
/*     */     public void aroundPostRestart(Throwable reason) {
/*  78 */       Actor.class.aroundPostRestart(this, reason);
/*     */     }
/*     */     
/*     */     public void preStart() throws Exception {
/*  78 */       Actor.class.preStart(this);
/*     */     }
/*     */     
/*     */     public void postStop() throws Exception {
/*  78 */       Actor.class.postStop(this);
/*     */     }
/*     */     
/*     */     public void preRestart(Throwable reason, Option message) throws Exception {
/*  78 */       Actor.class.preRestart(this, reason, message);
/*     */     }
/*     */     
/*     */     public void postRestart(Throwable reason) throws Exception {
/*  78 */       Actor.class.postRestart(this, reason);
/*     */     }
/*     */     
/*     */     public void unhandled(Object message) {
/*  78 */       Actor.class.unhandled(this, message);
/*     */     }
/*     */     
/*     */     public SelectorBasedManager(SelectionHandlerSettings selectorSettings, int nrOfSelectors) {
/*  78 */       Actor.class.$init$(this);
/*  82 */       this.selectorPool = context().actorOf((
/*  83 */           new RandomRouter(nrOfSelectors, akka.routing.RandomRouter$.MODULE$.apply$default$2(), akka.routing.RandomRouter$.MODULE$.apply$default$3(), akka.routing.RandomRouter$.MODULE$.apply$default$4(), akka.routing.RandomRouter$.MODULE$.apply$default$5())).props(akka.actor.Props$.MODULE$.apply(SelectionHandler.class, (Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { selectorSettings }))).withDeploy(akka.actor.Deploy$.MODULE$.local()), 
/*  84 */           "selectors");
/*     */     }
/*     */     
/*     */     public SupervisorStrategy supervisorStrategy() {
/*     */       return SelectionHandler$.MODULE$.connectionSupervisorStrategy();
/*     */     }
/*     */     
/*     */     public ActorRef selectorPool() {
/*     */       return this.selectorPool;
/*     */     }
/*     */     
/*     */     public final PartialFunction<Object, BoxedUnit> workerForCommandHandler(PartialFunction pf) {
/*  86 */       return (PartialFunction<Object, BoxedUnit>)new SelectionHandler$SelectorBasedManager$$anonfun$workerForCommandHandler$1(this, pf);
/*     */     }
/*     */     
/*     */     public class SelectionHandler$SelectorBasedManager$$anonfun$workerForCommandHandler$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final PartialFunction pf$1;
/*     */       
/*     */       public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*  86 */         Object object = x1;
/*  87 */         if (object instanceof SelectionHandler.HasFailureMessage) {
/*  87 */           SelectionHandler.HasFailureMessage hasFailureMessage = (SelectionHandler.HasFailureMessage)object;
/*  87 */           if (this.pf$1.isDefinedAt(hasFailureMessage)) {
/*  87 */             akka.actor.package$.MODULE$.actorRef2Scala(this.$outer.selectorPool()).$bang(new SelectionHandler.WorkerForCommand(hasFailureMessage, this.$outer.sender(), (Function1<ChannelRegistry, Props>)this.pf$1.apply(hasFailureMessage)), this.$outer.self());
/*  87 */             return (B1)BoxedUnit.UNIT;
/*     */           } 
/*     */         } 
/*     */         return (B1)default.apply(x1);
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Object x1) {
/*     */         Object object = x1;
/*  87 */         if (object instanceof SelectionHandler.HasFailureMessage) {
/*  87 */           SelectionHandler.HasFailureMessage hasFailureMessage = (SelectionHandler.HasFailureMessage)object;
/*  87 */           if (this.pf$1.isDefinedAt(hasFailureMessage))
/*     */             return true; 
/*     */         } 
/*     */         return false;
/*     */       }
/*     */       
/*     */       public SelectionHandler$SelectorBasedManager$$anonfun$workerForCommandHandler$1(SelectionHandler.SelectorBasedManager $outer, PartialFunction pf$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$1 extends OneForOneStrategy {
/*     */     public $anon$1() {
/*  96 */       super(akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$1(), akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$2(), akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$3(), akka.actor.SupervisorStrategy$.MODULE$.stoppingStrategy().decider());
/*     */     }
/*     */     
/*     */     public void logFailure(ActorContext context, ActorRef child, Throwable cause, SupervisorStrategy.Directive decision) {
/*  99 */       if (cause instanceof akka.actor.DeathPactException) {
/*     */         try {
/* 100 */           context.system().eventStream().publish(
/* 101 */               new Logging.Debug(child.path().toString(), getClass(), "Closed after handler termination"));
/*     */         } finally {
/*     */           BoxedUnit boxedUnit;
/*     */           Exception exception1 = null, exception2 = exception1;
/* 102 */           Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/* 102 */           if (option.isEmpty())
/*     */             throw exception1; 
/*     */         } 
/*     */       } else {
/* 103 */         super.logFailure(context, child, cause, decision);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ChannelRegistryImpl implements ChannelRegistry {
/*     */     public final ExecutionContext akka$io$SelectionHandler$ChannelRegistryImpl$$executionContext;
/*     */     
/*     */     public final LoggingAdapter akka$io$SelectionHandler$ChannelRegistryImpl$$log;
/*     */     
/*     */     public final AbstractSelector akka$io$SelectionHandler$ChannelRegistryImpl$$selector;
/*     */     
/*     */     public final AtomicBoolean akka$io$SelectionHandler$ChannelRegistryImpl$$wakeUp;
/*     */     
/*     */     private final int OP_READ_AND_WRITE;
/*     */     
/*     */     private final Task select;
/*     */     
/*     */     public ChannelRegistryImpl(ExecutionContext executionContext, LoggingAdapter log) {
/* 107 */       this.akka$io$SelectionHandler$ChannelRegistryImpl$$selector = SelectorProvider.provider().openSelector();
/* 108 */       this.akka$io$SelectionHandler$ChannelRegistryImpl$$wakeUp = new AtomicBoolean(false);
/* 112 */       this.select = new $anon$3(this);
/* 151 */       executionContext.execute(this.select);
/*     */     }
/*     */     
/*     */     public final int OP_READ_AND_WRITE() {
/*     */       return 5;
/*     */     }
/*     */     
/*     */     public class $anon$3 extends Task {
/*     */       public $anon$3(SelectionHandler.ChannelRegistryImpl $outer) {
/*     */         super($outer);
/*     */       }
/*     */       
/*     */       public void tryRun() {
/*     */         if (this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$selector.select() > 0) {
/*     */           Set<SelectionKey> keys = this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$selector.selectedKeys();
/*     */           Iterator<SelectionKey> iterator = keys.iterator();
/*     */           while (iterator.hasNext()) {
/*     */             SelectionKey key = iterator.next();
/*     */             if (key.isValid()) {
/*     */               ActorRef connection;
/*     */               try {
/*     */                 ScalaActorRef qual$2;
/*     */                 SelectionHandler.ChannelWritable$ x$5;
/*     */                 ActorRef x$6;
/*     */                 ScalaActorRef qual$3;
/*     */                 SelectionHandler.ChannelWritable$ x$7;
/*     */                 ActorRef x$8;
/*     */                 ScalaActorRef qual$4;
/*     */                 SelectionHandler.ChannelReadable$ x$9;
/*     */                 ActorRef x$10;
/*     */                 int readyOps = key.readyOps();
/*     */                 key.interestOps(key.interestOps() & (readyOps ^ 0xFFFFFFFF));
/*     */                 connection = (ActorRef)key.attachment();
/*     */                 int i = readyOps;
/*     */                 switch (i) {
/*     */                   default:
/*     */                     if ((i & 0x10) > 0) {
/*     */                       ScalaActorRef qual$5 = akka.actor.package$.MODULE$.actorRef2Scala(connection);
/*     */                       SelectionHandler.ChannelAcceptable$ x$11 = SelectionHandler.ChannelAcceptable$.MODULE$;
/*     */                       ActorRef x$12 = qual$5.$bang$default$2(x$11);
/*     */                       qual$5.$bang(x$11, x$12);
/*     */                       continue;
/*     */                     } 
/*     */                     if ((i & 0x8) > 0) {
/*     */                       ScalaActorRef qual$6 = akka.actor.package$.MODULE$.actorRef2Scala(connection);
/*     */                       SelectionHandler.ChannelConnectable$ x$13 = SelectionHandler.ChannelConnectable$.MODULE$;
/*     */                       ActorRef x$14 = qual$6.$bang$default$2(x$13);
/*     */                       qual$6.$bang(x$13, x$14);
/*     */                       continue;
/*     */                     } 
/*     */                     this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$log.warning("Invalid readyOps: [{}]", BoxesRunTime.boxToInteger(i));
/*     */                     continue;
/*     */                   case 5:
/*     */                     qual$3 = akka.actor.package$.MODULE$.actorRef2Scala(connection);
/*     */                     x$7 = SelectionHandler.ChannelWritable$.MODULE$;
/*     */                     x$8 = qual$3.$bang$default$2(x$7);
/*     */                     qual$3.$bang(x$7, x$8);
/*     */                     qual$4 = akka.actor.package$.MODULE$.actorRef2Scala(connection);
/*     */                     x$9 = SelectionHandler.ChannelReadable$.MODULE$;
/*     */                     x$10 = qual$4.$bang$default$2(x$9);
/*     */                     qual$4.$bang(x$9, x$10);
/*     */                     continue;
/*     */                   case 4:
/*     */                     qual$2 = akka.actor.package$.MODULE$.actorRef2Scala(connection);
/*     */                     x$5 = SelectionHandler.ChannelWritable$.MODULE$;
/*     */                     x$6 = qual$2.$bang$default$2(x$5);
/*     */                     qual$2.$bang(x$5, x$6);
/*     */                     continue;
/*     */                   case 1:
/*     */                     break;
/*     */                 } 
/*     */               } catch (CancelledKeyException cancelledKeyException) {
/*     */                 continue;
/*     */               } 
/*     */               ScalaActorRef qual$1 = akka.actor.package$.MODULE$.actorRef2Scala(connection);
/*     */               SelectionHandler.ChannelReadable$ x$3 = SelectionHandler.ChannelReadable$.MODULE$;
/*     */               ActorRef x$4 = qual$1.$bang$default$2(x$3);
/*     */               qual$1.$bang(x$3, x$4);
/*     */             } 
/*     */           } 
/*     */           keys.clear();
/*     */         } 
/*     */         this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$wakeUp.set(false);
/*     */       }
/*     */       
/*     */       public void run() {
/*     */         if (this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$selector.isOpen())
/*     */           try {
/*     */             super.run();
/*     */             return;
/*     */           } finally {
/*     */             this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$executionContext.execute(this);
/*     */           }  
/*     */       }
/*     */     }
/*     */     
/*     */     public void register(SelectableChannel channel, int initialOps, ActorRef channelActor) {
/* 154 */       execute(
/* 155 */           new SelectionHandler$ChannelRegistryImpl$$anon$4(this, channel, initialOps, channelActor));
/*     */     }
/*     */     
/*     */     public class SelectionHandler$ChannelRegistryImpl$$anon$4 extends Task {
/*     */       private final SelectableChannel channel$1;
/*     */       
/*     */       private final int initialOps$1;
/*     */       
/*     */       private final ActorRef channelActor$1;
/*     */       
/*     */       public SelectionHandler$ChannelRegistryImpl$$anon$4(SelectionHandler.ChannelRegistryImpl $outer, SelectableChannel channel$1, int initialOps$1, ActorRef channelActor$1) {
/* 155 */         super($outer);
/*     */       }
/*     */       
/*     */       public void tryRun() {
/* 157 */         SelectionKey key = this.channel$1.register(this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$selector, this.initialOps$1, this.channelActor$1);
/* 158 */         akka.actor.package$.MODULE$.actorRef2Scala(this.channelActor$1).$bang(new SelectionHandler$ChannelRegistryImpl$$anon$4$$anon$5(this, key), this.channelActor$1);
/*     */       }
/*     */       
/*     */       public class SelectionHandler$ChannelRegistryImpl$$anon$4$$anon$5 implements ChannelRegistration {
/*     */         private final SelectionKey key$1;
/*     */         
/*     */         public SelectionHandler$ChannelRegistryImpl$$anon$4$$anon$5(SelectionHandler$ChannelRegistryImpl$$anon$4 $outer, SelectionKey key$1) {}
/*     */         
/*     */         public void enableInterest(int ops) {
/* 159 */           this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$anon$$$outer().akka$io$SelectionHandler$ChannelRegistryImpl$$enableInterestOps(this.key$1, ops);
/*     */         }
/*     */         
/*     */         public void disableInterest(int ops) {
/* 160 */           this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$anon$$$outer().akka$io$SelectionHandler$ChannelRegistryImpl$$disableInterestOps(this.key$1, ops);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void shutdown() {
/* 167 */       execute(
/* 168 */           new SelectionHandler$ChannelRegistryImpl$$anon$6(this));
/*     */     }
/*     */     
/*     */     public class SelectionHandler$ChannelRegistryImpl$$anon$6 extends Task {
/*     */       public SelectionHandler$ChannelRegistryImpl$$anon$6(SelectionHandler.ChannelRegistryImpl $outer) {
/* 168 */         super($outer);
/*     */       }
/*     */       
/*     */       private final void closeNextChannel$1(Iterator<SelectionKey> it) {
/* 171 */         while (it.hasNext()) {
/*     */           try {
/*     */           
/*     */           } finally {
/* 172 */             Exception exception1 = null, exception2 = exception1;
/* 172 */             Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/* 172 */             if (option.isEmpty())
/* 172 */               throw exception1; 
/* 172 */             Throwable e = (Throwable)option.get();
/* 172 */             this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$log.debug("Error closing channel: {}", e);
/* 172 */             BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/*     */       public void tryRun() {
/*     */         try {
/* 175 */           closeNextChannel$1(this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$selector.keys().iterator());
/*     */           return;
/*     */         } finally {
/* 176 */           this.$outer.akka$io$SelectionHandler$ChannelRegistryImpl$$selector.close();
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     public void akka$io$SelectionHandler$ChannelRegistryImpl$$enableInterestOps(SelectionKey key, int ops) {
/* 184 */       execute(
/* 185 */           new SelectionHandler$ChannelRegistryImpl$$anon$7(this, key, ops));
/*     */     }
/*     */     
/*     */     public class SelectionHandler$ChannelRegistryImpl$$anon$7 extends Task {
/*     */       private final SelectionKey key$2;
/*     */       
/*     */       private final int ops$1;
/*     */       
/*     */       public SelectionHandler$ChannelRegistryImpl$$anon$7(SelectionHandler.ChannelRegistryImpl $outer, SelectionKey key$2, int ops$1) {
/* 185 */         super($outer);
/*     */       }
/*     */       
/*     */       public void tryRun() {
/* 187 */         int currentOps = this.key$2.interestOps();
/* 188 */         int newOps = currentOps | this.ops$1;
/* 189 */         if (newOps != currentOps)
/* 189 */           this.key$2.interestOps(newOps); 
/*     */       }
/*     */     }
/*     */     
/*     */     public void akka$io$SelectionHandler$ChannelRegistryImpl$$disableInterestOps(SelectionKey key, int ops) {
/* 195 */       execute(
/* 196 */           new SelectionHandler$ChannelRegistryImpl$$anon$8(this, key, ops));
/*     */     }
/*     */     
/*     */     public class SelectionHandler$ChannelRegistryImpl$$anon$8 extends Task {
/*     */       private final SelectionKey key$3;
/*     */       
/*     */       private final int ops$2;
/*     */       
/*     */       public SelectionHandler$ChannelRegistryImpl$$anon$8(SelectionHandler.ChannelRegistryImpl $outer, SelectionKey key$3, int ops$2) {
/* 196 */         super($outer);
/*     */       }
/*     */       
/*     */       public void tryRun() {
/* 198 */         int currentOps = this.key$3.interestOps();
/* 199 */         int newOps = currentOps & (this.ops$2 ^ 0xFFFFFFFF);
/* 200 */         if (newOps != currentOps)
/* 200 */           this.key$3.interestOps(newOps); 
/*     */       }
/*     */     }
/*     */     
/*     */     private void execute(Task task) {
/* 206 */       this.akka$io$SelectionHandler$ChannelRegistryImpl$$executionContext.execute(task);
/* 207 */       if (this.akka$io$SelectionHandler$ChannelRegistryImpl$$wakeUp.compareAndSet(false, true))
/* 208 */         this.akka$io$SelectionHandler$ChannelRegistryImpl$$selector.wakeup(); 
/*     */     }
/*     */     
/*     */     public abstract class Task implements Runnable {
/*     */       public Task(SelectionHandler.ChannelRegistryImpl $outer) {}
/*     */       
/*     */       public void run() {
/*     */         try {
/* 215 */           tryRun();
/*     */         } finally {
/* 215 */           Exception exception1 = null, exception2 = exception1;
/*     */         } 
/*     */       }
/*     */       
/*     */       public abstract void tryRun();
/*     */     }
/*     */   }
/*     */   
/*     */   public LoggingAdapter akka$actor$ActorLogging$$_log() {
/* 225 */     return this.akka$actor$ActorLogging$$_log;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/* 225 */     this.akka$actor$ActorLogging$$_log = x$1;
/*     */   }
/*     */   
/*     */   public LoggingAdapter log() {
/* 225 */     return ActorLogging.class.log(this);
/*     */   }
/*     */   
/*     */   public ActorContext context() {
/* 225 */     return this.context;
/*     */   }
/*     */   
/*     */   public final ActorRef self() {
/* 225 */     return this.self;
/*     */   }
/*     */   
/*     */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/* 225 */     this.context = x$1;
/*     */   }
/*     */   
/*     */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/* 225 */     this.self = x$1;
/*     */   }
/*     */   
/*     */   public final ActorRef sender() {
/* 225 */     return Actor.class.sender(this);
/*     */   }
/*     */   
/*     */   public void aroundReceive(PartialFunction receive, Object msg) {
/* 225 */     Actor.class.aroundReceive(this, receive, msg);
/*     */   }
/*     */   
/*     */   public void aroundPreStart() {
/* 225 */     Actor.class.aroundPreStart(this);
/*     */   }
/*     */   
/*     */   public void aroundPostStop() {
/* 225 */     Actor.class.aroundPostStop(this);
/*     */   }
/*     */   
/*     */   public void aroundPreRestart(Throwable reason, Option message) {
/* 225 */     Actor.class.aroundPreRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void aroundPostRestart(Throwable reason) {
/* 225 */     Actor.class.aroundPostRestart(this, reason);
/*     */   }
/*     */   
/*     */   public void preStart() throws Exception {
/* 225 */     Actor.class.preStart(this);
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable reason, Option message) throws Exception {
/* 225 */     Actor.class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void postRestart(Throwable reason) throws Exception {
/* 225 */     Actor.class.postRestart(this, reason);
/*     */   }
/*     */   
/*     */   public void unhandled(Object message) {
/* 225 */     Actor.class.unhandled(this, message);
/*     */   }
/*     */   
/*     */   public SelectionHandler(SelectionHandlerSettings settings) {
/* 225 */     Actor.class.$init$(this);
/* 225 */     ActorLogging.class.$init$(this);
/* 230 */     this.sequenceNumber = 0;
/* 231 */     this.akka$io$SelectionHandler$$childCount = 0;
/* 233 */     MessageDispatcher dispatcher = context().system().dispatchers().lookup(settings.SelectorDispatcher());
/* 234 */     this.registry = new ChannelRegistryImpl((ExecutionContext)akka.util.SerializedSuspendableExecutionContext$.MODULE$.apply(dispatcher.throughput(), (ExecutionContext)dispatcher), log());
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> receive() {
/* 237 */     return (PartialFunction<Object, BoxedUnit>)new SelectionHandler$$anonfun$receive$1(this);
/*     */   }
/*     */   
/*     */   public class SelectionHandler$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/* 237 */       Object object2, object1 = x2;
/* 238 */       if (object1 instanceof SelectionHandler.WorkerForCommand) {
/* 238 */         SelectionHandler.WorkerForCommand workerForCommand = (SelectionHandler.WorkerForCommand)object1;
/* 238 */         this.$outer.spawnChildWithCapacityProtection(workerForCommand, this.$outer.akka$io$SelectionHandler$$settings.SelectorAssociationRetries());
/* 238 */         object2 = BoxedUnit.UNIT;
/* 240 */       } else if (object1 instanceof SelectionHandler.Retry) {
/* 240 */         SelectionHandler.Retry retry = (SelectionHandler.Retry)object1;
/* 240 */         SelectionHandler.WorkerForCommand cmd = retry.command();
/* 240 */         int retriesLeft = retry.retriesLeft();
/* 240 */         this.$outer.spawnChildWithCapacityProtection(cmd, retriesLeft);
/* 240 */         object2 = BoxedUnit.UNIT;
/* 244 */       } else if (object1 instanceof akka.actor.Terminated) {
/* 244 */         this.$outer.akka$io$SelectionHandler$$childCount--;
/* 244 */         object2 = BoxedUnit.UNIT;
/*     */       } else {
/*     */         object2 = default.apply(x2);
/*     */       } 
/*     */       return (B1)object2;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x2) {
/*     */       boolean bool;
/*     */       Object object = x2;
/*     */       if (object instanceof SelectionHandler.WorkerForCommand) {
/*     */         bool = true;
/*     */       } else if (object instanceof SelectionHandler.Retry) {
/*     */         bool = true;
/* 244 */       } else if (object instanceof akka.actor.Terminated) {
/* 244 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public SelectionHandler$$anonfun$receive$1(SelectionHandler $outer) {}
/*     */   }
/*     */   
/*     */   public void postStop() {
/* 247 */     this.registry.shutdown();
/*     */   }
/*     */   
/*     */   public final PartialFunction akka$io$SelectionHandler$$stoppingDecider$1() {
/* 252 */     return (PartialFunction)new SelectionHandler$$anonfun$akka$io$SelectionHandler$$stoppingDecider$1$1(this);
/*     */   }
/*     */   
/*     */   public class SelectionHandler$$anonfun$akka$io$SelectionHandler$$stoppingDecider$1$1 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x3, Function1 default) {
/*     */       Object object;
/* 252 */       Throwable throwable = x3;
/* 253 */       if (throwable instanceof Exception) {
/* 253 */         object = akka.actor.SupervisorStrategy$Stop$.MODULE$;
/*     */       } else {
/*     */         object = default.apply(x3);
/*     */       } 
/*     */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x3) {
/*     */       boolean bool;
/*     */       Throwable throwable = x3;
/* 253 */       if (throwable instanceof Exception) {
/* 253 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public SelectionHandler$$anonfun$akka$io$SelectionHandler$$stoppingDecider$1$1(SelectionHandler $outer) {}
/*     */   }
/*     */   
/*     */   public OneForOneStrategy supervisorStrategy() {
/* 255 */     return new SelectionHandler$$anon$2(this);
/*     */   }
/*     */   
/*     */   public class SelectionHandler$$anon$2 extends OneForOneStrategy {
/*     */     public SelectionHandler$$anon$2(SelectionHandler $outer) {
/* 255 */       super(akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$1(), akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$2(), akka.actor.OneForOneStrategy$.MODULE$.$lessinit$greater$default$3(), $outer.akka$io$SelectionHandler$$stoppingDecider$1());
/*     */     }
/*     */     
/*     */     public void logFailure(ActorContext context, ActorRef child, Throwable cause, SupervisorStrategy.Directive decision) {
/*     */       // Byte code:
/*     */       //   0: aload_3
/*     */       //   1: astore #10
/*     */       //   3: aload #10
/*     */       //   5: instanceof akka/actor/ActorInitializationException
/*     */       //   8: ifeq -> 39
/*     */       //   11: aload #10
/*     */       //   13: checkcast akka/actor/ActorInitializationException
/*     */       //   16: astore #11
/*     */       //   18: aload #11
/*     */       //   20: invokevirtual getCause : ()Ljava/lang/Throwable;
/*     */       //   23: ifnull -> 39
/*     */       //   26: aload #11
/*     */       //   28: invokevirtual getCause : ()Ljava/lang/Throwable;
/*     */       //   31: invokevirtual getMessage : ()Ljava/lang/String;
/*     */       //   34: astore #12
/*     */       //   36: goto -> 46
/*     */       //   39: aload #10
/*     */       //   41: invokevirtual getMessage : ()Ljava/lang/String;
/*     */       //   44: astore #12
/*     */       //   46: aload #12
/*     */       //   48: astore #9
/*     */       //   50: aload_1
/*     */       //   51: invokeinterface system : ()Lakka/actor/ActorSystem;
/*     */       //   56: invokevirtual eventStream : ()Lakka/event/EventStream;
/*     */       //   59: new akka/event/Logging$Debug
/*     */       //   62: dup
/*     */       //   63: aload_2
/*     */       //   64: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */       //   67: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   70: ldc akka/io/SelectionHandler
/*     */       //   72: aload #9
/*     */       //   74: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)V
/*     */       //   77: invokevirtual publish : (Ljava/lang/Object;)V
/*     */       //   80: goto -> 115
/*     */       //   83: astore #5
/*     */       //   85: aload #5
/*     */       //   87: astore #6
/*     */       //   89: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */       //   92: aload #6
/*     */       //   94: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */       //   97: astore #7
/*     */       //   99: aload #7
/*     */       //   101: invokevirtual isEmpty : ()Z
/*     */       //   104: ifeq -> 110
/*     */       //   107: aload #5
/*     */       //   109: athrow
/*     */       //   110: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   113: astore #8
/*     */       //   115: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #259	-> 0
/*     */       //   #260	-> 3
/*     */       //   #261	-> 39
/*     */       //   #259	-> 46
/*     */       //   #263	-> 50
/*     */       //   #264	-> 59
/*     */       //   #263	-> 77
/*     */       //   #258	-> 83
/*     */       //   #265	-> 89
/*     */       //   #258	-> 107
/*     */       //   #265	-> 110
/*     */       //   #258	-> 115
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	116	0	this	Lakka/io/SelectionHandler$$anon$2;
/*     */       //   0	116	1	context	Lakka/actor/ActorContext;
/*     */       //   0	116	2	child	Lakka/actor/ActorRef;
/*     */       //   0	116	3	cause	Ljava/lang/Throwable;
/*     */       //   0	116	4	decision	Lakka/actor/SupervisorStrategy$Directive;
/*     */       //   50	30	9	logMessage	Ljava/lang/String;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   0	83	83	finally
/*     */     }
/*     */   }
/*     */   
/*     */   public void spawnChildWithCapacityProtection(WorkerForCommand cmd, int retriesLeft) {
/* 270 */     if (this.akka$io$SelectionHandler$$settings.TraceLogging())
/* 270 */       log().debug("Executing [{}]", cmd); 
/* 271 */     if (this.akka$io$SelectionHandler$$settings.MaxChannelsPerSelector() == -1 || this.akka$io$SelectionHandler$$childCount < this.akka$io$SelectionHandler$$settings.MaxChannelsPerSelector()) {
/* 272 */       String newName = BoxesRunTime.boxToInteger(this.sequenceNumber).toString();
/* 273 */       this.sequenceNumber++;
/* 274 */       ActorRef child = context().actorOf(((Props)cmd.childProps().apply(this.registry)).withDispatcher(this.akka$io$SelectionHandler$$settings.WorkerDispatcher()).withDeploy(akka.actor.Deploy$.MODULE$.local()), newName);
/* 275 */       this.akka$io$SelectionHandler$$childCount++;
/* 276 */       if (this.akka$io$SelectionHandler$$settings.MaxChannelsPerSelector() > 0)
/* 276 */         context().watch(child); 
/* 278 */     } else if (retriesLeft >= 1) {
/* 279 */       log().debug("Rejecting [{}] with [{}] retries left, retrying...", cmd, BoxesRunTime.boxToInteger(retriesLeft));
/* 280 */       context().parent().forward(new Retry(cmd, retriesLeft - 1), context());
/*     */     } else {
/* 282 */       log().warning("Rejecting [{}] with no retries left, aborting...", cmd);
/* 283 */       akka.actor.package$.MODULE$.actorRef2Scala(cmd.commander()).$bang(cmd.apiCommand().failureMessage(), self());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface HasFailureMessage {
/*     */     Object failureMessage();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\SelectionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */