package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Proxy;
import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\021%eaB\001\003!\003\r\ta\002\002\025)J\fg/\032:tC\ndW\r\025:pqfd\025n[3\013\005\r!\021AC2pY2,7\r^5p]*\tQ!A\003tG\006d\027m\001\001\026\007!\031Rd\005\003\001\02351\003C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB!abD\t\035\033\005\021\021B\001\t\003\005=!&/\031<feN\f'\r\\3MS.,\007C\001\n\024\031\001!a\001\006\001\005\006\004)\"!A!\022\005YI\002C\001\006\030\023\tABAA\004O_RD\027N\\4\021\005)Q\022BA\016\005\005\r\te.\037\t\003%u!aA\b\001\005\006\004y\"\001\002*faJ\f\"A\006\021\023\007\005j1E\002\003#\001\001\001#\001\004\037sK\032Lg.Z7f]Rt\004c\001\b%#%\021QE\001\002\f)J\fg/\032:tC\ndW\r\005\002\013O%\021\001\006\002\002\006!J|\0070\037\005\006U\001!\taK\001\007I%t\027\016\036\023\025\0031\002\"AC\027\n\0059\"!\001B+oSRDQ\001\r\001\007\002E\nAa]3mMV\tA\004C\0034\001\021\005C'A\004g_J,\027m\0315\026\005UbDC\001\0277\021\0259$\0071\0019\003\0051\007\003\002\006:#mJ!A\017\003\003\023\031+hn\031;j_:\f\004C\001\n=\t\025i$G1\001\026\005\005\021\005\"B \001\t\003\002\025aB5t\0136\004H/_\013\002\003B\021!BQ\005\003\007\022\021qAQ8pY\026\fg\016C\003F\001\021\005\003)\001\005o_:,U\016\035;z\021\0259\005\001\"\021I\003\021\031\030N_3\026\003%\003\"A\003&\n\005-#!aA%oi\")Q\n\001C!\001\006y\001.Y:EK\032Lg.\033;f'&TX\rC\003P\001\021\005\003+\001\006%a2,8\017\n9mkN,2!U0U)\t\021\026\r\006\002T-B\021!\003\026\003\006+:\023\r!\006\002\005)\"\fG\017C\003X\035\002\017\001,\001\002cMB)\021\f\030\017_'6\t!L\003\002\\\005\0059q-\0328fe&\034\027BA/[\0051\031\025M\034\"vS2$gI]8n!\t\021r\fB\003>\035\n\007\001-\005\002\0223!)!M\024a\001G\006\021\001p\035\t\004\035\021t\026BA3\003\005I9UM\034+sCZ,'o]1cY\026|enY3\t\013\035\004A\021\t5\002\0075\f\007/F\002ja2$\"A[9\025\005-l\007C\001\nm\t\025)fM1\001\026\021\0259f\rq\001o!\025IF\fH8l!\t\021\002\017B\003>M\n\007Q\003C\0038M\002\007!\017\005\003\013sEy\007\"\002;\001\t\003*\030a\0024mCRl\025\r]\013\004mvLHCA<)\tA(\020\005\002\023s\022)Qk\035b\001+!)qk\035a\002wB)\021\f\030\017}qB\021!# \003\006{M\024\r!\006\005\006oM\004\ra \t\006\025e\n\022\021\001\t\004\035\021d\bbBA\003\001\021\005\023qA\001\007M&dG/\032:\025\007q\tI\001\003\005\002\f\005\r\001\031AA\007\003\005\001\b\003\002\006:#\005Cq!!\005\001\t\003\n\031\"A\005gS2$XM\035(piR\031A$!\006\t\021\005-\021q\002a\001\003\033Aq!!\007\001\t\003\nY\"A\004d_2dWm\031;\026\r\005u\0211FA\022)\021\ty\"!\f\025\t\005\005\022Q\005\t\004%\005\rBAB+\002\030\t\007Q\003C\004X\003/\001\035!a\n\021\017ecF$!\013\002\"A\031!#a\013\005\ru\n9B1\001\026\021!\ty#a\006A\002\005E\022A\0019g!\031Q\0211G\t\002*%\031\021Q\007\003\003\037A\013'\017^5bY\032+hn\031;j_:Dq!!\017\001\t\003\nY$A\005qCJ$\030\016^5p]R!\021QHA\"!\025Q\021q\b\017\035\023\r\t\t\005\002\002\007)V\004H.\032\032\t\021\005-\021q\007a\001\003\033Aq!a\022\001\t\003\nI%A\004he>,\bOQ=\026\t\005-\0231\f\013\005\003\033\ny\006E\004\002P\005U\023\021\f\017\016\005\005E#bAA*\005\005I\021.\\7vi\006\024G.Z\005\005\003/\n\tFA\002NCB\0042AEA.\t\035\ti&!\022C\002U\021\021a\023\005\bo\005\025\003\031AA1!\025Q\021(EA-\021\035\t)\007\001C!\003O\naAZ8sC2dGcA!\002j!A\0211BA2\001\004\ti\001C\004\002n\001!\t%a\034\002\r\025D\030n\035;t)\r\t\025\021\017\005\t\003\027\tY\0071\001\002\016!9\021Q\017\001\005B\005]\024!B2pk:$HcA%\002z!A\0211BA:\001\004\ti\001C\004\002~\001!\t%a \002\t\031Lg\016\032\013\005\003\003\0139\t\005\003\013\003\007\013\022bAAC\t\t1q\n\035;j_:D\001\"a\003\002|\001\007\021Q\002\005\b\003\027\003A\021IAG\003!1w\016\0343MK\032$X\003BAH\003+#B!!%\002\"R!\0211SAL!\r\021\022Q\023\003\007{\005%%\031A\013\t\021\005e\025\021\022a\001\0037\013!a\0349\021\021)\ti*a%\022\003'K1!a(\005\005%1UO\\2uS>t'\007\003\005\002$\006%\005\031AAJ\003\005Q\bbBAT\001\021\005\023\021V\001\013I\021Lg\017J2pY>tW\003BAV\003c#B!!,\0028R!\021qVAZ!\r\021\022\021\027\003\007{\005\025&\031A\013\t\021\005e\025Q\025a\001\003k\003\002BCAO\003_\013\022q\026\005\t\003G\013)\0131\001\0020\"9\0211\030\001\005B\005u\026!\0034pY\022\024\026n\0325u+\021\ty,!2\025\t\005\005\0271\032\013\005\003\007\f9\rE\002\023\003\013$a!PA]\005\004)\002\002CAM\003s\003\r!!3\021\021)\ti*EAb\003\007D\001\"a)\002:\002\007\0211\031\005\b\003\037\004A\021IAi\0035!3m\0347p]\022\0227\017\\1tQV!\0211[Am)\021\t).a8\025\t\005]\0271\034\t\004%\005eGAB\037\002N\n\007Q\003\003\005\002\032\0065\007\031AAo!!Q\021QT\t\002X\006]\007\002CAR\003\033\004\r!a6\t\017\005\r\b\001\"\021\002f\006Q!/\0323vG\026dUM\032;\026\t\005\035\0301\036\013\005\003S\fi\017E\002\023\003W$a!PAq\005\004\001\007\002CAM\003C\004\r!a<\021\021)\ti*!;\022\003SDq!a=\001\t\003\n)0\001\tsK\022,8-\032'fMR|\005\017^5p]V!\021q_A)\021\tI0a@\021\013)\t\031)a?\021\007I\ti\020\002\004>\003c\024\r\001\031\005\t\0033\013\t\0201\001\003\002AA!\"!(\002|F\tY\020C\004\003\006\001!\tEa\002\002\027I,G-^2f%&<\007\016^\013\005\005\023\021i\001\006\003\003\f\t=\001c\001\n\003\016\0211QHa\001C\002\001D\001\"!'\003\004\001\007!\021\003\t\t\025\005u\025Ca\003\003\f!9!Q\003\001\005B\t]\021!\005:fIV\034WMU5hQR|\005\017^5p]V!!\021\004B\020)\021\021YB!\t\021\013)\t\031I!\b\021\007I\021y\002\002\004>\005'\021\r\001\031\005\t\0033\023\031\0021\001\003$AA!\"!(\022\005;\021i\002C\004\003(\001!\tE!\013\002\021M\034\027M\034'fMR,bAa\013\003<\tMB\003\002B\027\005\003\"BAa\f\003>Q!!\021\007B\033!\r\021\"1\007\003\007+\n\025\"\031A\013\t\017]\023)\003q\001\0038A9\021\f\030\017\003:\tE\002c\001\n\003<\0211QH!\nC\002UA\001\"!'\003&\001\007!q\b\t\t\025\005u%\021H\t\003:!A\0211\025B\023\001\004\021I\004C\004\003F\001!\tEa\022\002\023M\034\027M\034*jO\"$XC\002B%\0053\022\t\006\006\003\003L\t}C\003\002B'\0057\"BAa\024\003TA\031!C!\025\005\rU\023\031E1\001\026\021\0359&1\ta\002\005+\002r!\027/\035\005/\022y\005E\002\023\0053\"a!\020B\"\005\004)\002\002CAM\005\007\002\rA!\030\021\021)\ti*\005B,\005/B\001\"a)\003D\001\007!q\013\005\b\005G\002A\021\tB3\003\r\031X/\\\013\005\005O\022Y\007\006\003\003j\t5\004c\001\n\003l\0211QH!\031C\002\001D\001Ba\034\003b\001\017!\021O\001\004]Vl\007C\002B:\005\007\023IG\004\003\003v\t}d\002\002B<\005{j!A!\037\013\007\tmd!\001\004=e>|GOP\005\002\013%\031!\021\021\003\002\017A\f7m[1hK&!!Q\021BD\005\035qU/\\3sS\016T1A!!\005\021\035\021Y\t\001C!\005\033\013q\001\035:pIV\034G/\006\003\003\020\nME\003\002BI\005+\0032A\005BJ\t\031i$\021\022b\001A\"A!q\016BE\001\b\0219\n\005\004\003t\t\r%\021\023\005\b\0057\003A\021\tBO\003\ri\027N\\\013\005\005?\023i\013F\002\022\005CC\001Ba)\003\032\002\017!QU\001\004G6\004\bC\002B:\005O\023Y+\003\003\003*\n\035%\001C(sI\026\024\030N\\4\021\007I\021i\013\002\004>\0053\023\r\001\031\005\b\005c\003A\021\tBZ\003\ri\027\r_\013\005\005k\023i\fF\002\022\005oC\001Ba)\0030\002\017!\021\030\t\007\005g\0229Ka/\021\007I\021i\f\002\004>\005_\023\r\001\031\005\b\005\003\004A\021\tBb\003\021AW-\0313\026\003EAqAa2\001\t\003\022I-\001\006iK\006$w\n\035;j_:,\"!!!\t\r\t5\007\001\"\0212\003\021!\030-\0337\t\017\tE\007\001\"\021\003D\006!A.Y:u\021\035\021)\016\001C!\005\023\f!\002\\1ti>\003H/[8o\021\031\021I\016\001C!c\005!\021N\\5u\021\035\021i\016\001C!\005?\fA\001^1lKR\031AD!9\t\017\t\r(1\034a\001\023\006\ta\016C\004\003h\002!\tE!;\002\t\021\024x\016\035\013\0049\t-\bb\002Br\005K\004\r!\023\005\b\005_\004A\021\tBy\003\025\031H.[2f)\025a\"1\037B|\021\035\021)P!<A\002%\013AA\032:p[\"9!\021 Bw\001\004I\025!B;oi&d\007b\002B\001\021\005#q`\001\ni\006\\Wm\0265jY\026$2\001HB\001\021!\tYAa?A\002\0055\001bBB\003\001\021\0053qA\001\nIJ|\007o\0265jY\026$2\001HB\005\021!\tYaa\001A\002\0055\001bBB\007\001\021\0053qB\001\005gB\fg\016\006\003\002>\rE\001\002CA\006\007\027\001\r!!\004\t\017\rU\001\001\"\021\004\030\00591\017\0357ji\006#H\003BA\037\0073AqAa9\004\024\001\007\021\nC\004\004\036\001!\tea\b\002\031\r|\007/\037+p\005V4g-\032:\026\t\r\0052Q\007\013\004Y\r\r\002\002CB\023\0077\001\raa\n\002\t\021,7\017\036\t\007\007S\031yca\r\016\005\r-\"bAB\027\005\0059Q.\036;bE2,\027\002BB\031\007W\021aAQ;gM\026\024\bc\001\n\0046\0211Qha\007C\002\001Dqa!\017\001\t\003\032Y$A\006d_BLHk\\!se\006LX\003BB\037\007\023\"r\001LB \007\027\032y\005C\004c\007o\001\ra!\021\021\013)\031\031ea\022\n\007\r\025CAA\003BeJ\f\027\020E\002\023\007\023\"a!PB\034\005\004\001\007bBB'\007o\001\r!S\001\006gR\f'\017\036\005\b\007#\0329\0041\001J\003\raWM\034\005\b\007s\001A\021IB++\021\0319fa\030\025\0131\032If!\031\t\017\t\034\031\0061\001\004\\A)!ba\021\004^A\031!ca\030\005\ru\032\031F1\001a\021\035\031iea\025A\002%Cqa!\017\001\t\003\032)'\006\003\004h\r=Dc\001\027\004j!9!ma\031A\002\r-\004#\002\006\004D\r5\004c\001\n\004p\0211Qha\031C\002\001Dqaa\035\001\t\003\032)(A\004u_\006\023(/Y=\026\t\r]4Q\020\013\005\007s\032y\bE\003\013\007\007\032Y\bE\002\023\007{\"a!PB9\005\004\001\007BCBA\007c\n\t\021q\001\004\004\006QQM^5eK:\034W\rJ\031\021\r\r\02551RB>\033\t\0319IC\002\004\n\022\tqA]3gY\026\034G/\003\003\004\016\016\035%\001C\"mCN\034H+Y4\t\017\rE\005\001\"\021\004\024\0061Ao\034'jgR,\"a!&\021\013\tM4qS\t\n\t\re%q\021\002\005\031&\034H\017C\004\004\036\002!\tea(\002\025Q|\027\n^3sC\ndW-\006\002\004\"B!aba)\022\023\r\031)K\001\002\t\023R,'/\0312mK\"91\021\026\001\005B\r-\026!\002;p'\026\fXCABW!\021q1qV\t\n\007\rE&AA\002TKFDqa!.\001\t\003\0329,\001\007u_&sG-\032=fIN+\027/\006\002\004:B)\021qJB^#%!1QXA)\005)Ie\016Z3yK\022\034V-\035\005\b\007\003\004A\021IBb\003!!xNQ;gM\026\024X\003BBc\007\027,\"aa2\021\r\r%2qFBe!\r\02121\032\003\007{\r}&\031\0011\t\017\r=\007\001\"\021\004R\006AAo\\*ue\026\fW.\006\002\004TB)!1OBk#%!1q\033BD\005\031\031FO]3b[\"911\034\001\005B\ru\027!\002;p'\026$X\003BBp\007S,\"a!9\021\r\005=31]Bt\023\021\031)/!\025\003\007M+G\017E\002\023\007S$a!PBm\005\004\001\007bBBw\001\021\0053q^\001\006i>l\025\r]\013\007\007c\0349p!@\025\t\rMH\021\001\t\t\003\037\n)f!>\004|B\031!ca>\005\017\re81\036b\001+\t\tA\013E\002\023\007{$qaa@\004l\n\007QCA\001V\021!!\031aa;A\004\021\025\021AA3w!\035!9\001\"\004\022\t'q1A\003C\005\023\r!Y\001B\001\007!J,G-\0324\n\t\021=A\021\003\002\021I1,7o\035\023d_2|g\016\n7fgNT1\001b\003\005!\035Q\021qHB{\007wDq\001b\006\001\t\003\"I\"A\007u_R\023\030M^3sg\006\024G.Z\013\002G!9AQ\004\001\005B\021}\021A\003;p\023R,'/\031;peV\021A\021\005\t\005\035\021\r\022#C\002\005&\t\021\001\"\023;fe\006$xN\035\005\b\tS\001A\021\tC\026\003!i7n\025;sS:<G\003\003C\027\tg!)\004\"\017\021\t\021\035AqF\005\005\tc!\tB\001\004TiJLgn\032\005\t\007\033\"9\0031\001\005.!AAq\007C\024\001\004!i#A\002tKBD\001\002b\017\005(\001\007AQF\001\004K:$\007b\002C\025\001\021\005Cq\b\013\005\t[!\t\005\003\005\0058\021u\002\031\001C\027\021\035!I\003\001C!\t\013*\"\001\"\f\t\017\021%\003\001\"\021\005L\005I\021\r\0323TiJLgn\032\013\013\t\033\"\031\006b\026\005Z\021m\003\003BB\025\t\037JA\001\"\025\004,\ti1\013\036:j]\036\024U/\0337eKJD\001\002\"\026\005H\001\007AQJ\001\002E\"A1Q\nC$\001\004!i\003\003\005\0058\021\035\003\031\001C\027\021!!Y\004b\022A\002\0215\002b\002C%\001\021\005Cq\f\013\007\t\033\"\t\007b\031\t\021\021UCQ\fa\001\t\033B\001\002b\016\005^\001\007AQ\006\005\b\t\023\002A\021\tC4)\021!i\005\"\033\t\021\021UCQ\ra\001\t\033Bq\001\"\034\001\t\003\")%\001\007tiJLgn\032)sK\032L\007\020C\004\005r\001!\t\005b\035\002\tYLWm^\013\003\tk\022R\001b\036\n\tw2QA\t\001\001\tkJ1\001\"\035\020!\025qAQP\t\035\023\r!yH\001\002\020)J\fg/\032:tC\ndWMV5fo\"9A\021\017\001\005B\021\rEC\002C>\t\013#9\tC\004\003v\022\005\005\031A%\t\017\teH\021\021a\001\023\002")
public interface TraversableProxyLike<A, Repr extends TraversableLike<A, Repr> & Traversable<A>> extends TraversableLike<A, Repr>, Proxy {
  Repr self();
  
  <B> void foreach(Function1<A, B> paramFunction1);
  
  boolean isEmpty();
  
  boolean nonEmpty();
  
  int size();
  
  boolean hasDefiniteSize();
  
  <B, That> That $plus$plus(GenTraversableOnce<B> paramGenTraversableOnce, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <B, That> That map(Function1<A, B> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  Repr filter(Function1<A, Object> paramFunction1);
  
  Repr filterNot(Function1<A, Object> paramFunction1);
  
  <B, That> That collect(PartialFunction<A, B> paramPartialFunction, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  Tuple2<Repr, Repr> partition(Function1<A, Object> paramFunction1);
  
  <K> Map<K, Repr> groupBy(Function1<A, K> paramFunction1);
  
  boolean forall(Function1<A, Object> paramFunction1);
  
  boolean exists(Function1<A, Object> paramFunction1);
  
  int count(Function1<A, Object> paramFunction1);
  
  Option<A> find(Function1<A, Object> paramFunction1);
  
  <B> B foldLeft(B paramB, Function2<B, A, B> paramFunction2);
  
  <B> B $div$colon(B paramB, Function2<B, A, B> paramFunction2);
  
  <B> B foldRight(B paramB, Function2<A, B, B> paramFunction2);
  
  <B> B $colon$bslash(B paramB, Function2<A, B, B> paramFunction2);
  
  <B> B reduceLeft(Function2<B, A, B> paramFunction2);
  
  <B> Option<B> reduceLeftOption(Function2<B, A, B> paramFunction2);
  
  <B> B reduceRight(Function2<A, B, B> paramFunction2);
  
  <B> Option<B> reduceRightOption(Function2<A, B, B> paramFunction2);
  
  <B, That> That scanLeft(B paramB, Function2<B, A, B> paramFunction2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <B, That> That scanRight(B paramB, Function2<A, B, B> paramFunction2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <B> B sum(Numeric<B> paramNumeric);
  
  <B> B product(Numeric<B> paramNumeric);
  
  <B> A min(Ordering<B> paramOrdering);
  
  <B> A max(Ordering<B> paramOrdering);
  
  A head();
  
  Option<A> headOption();
  
  Repr tail();
  
  A last();
  
  Option<A> lastOption();
  
  Repr init();
  
  Repr take(int paramInt);
  
  Repr drop(int paramInt);
  
  Repr slice(int paramInt1, int paramInt2);
  
  Repr takeWhile(Function1<A, Object> paramFunction1);
  
  Repr dropWhile(Function1<A, Object> paramFunction1);
  
  Tuple2<Repr, Repr> span(Function1<A, Object> paramFunction1);
  
  Tuple2<Repr, Repr> splitAt(int paramInt);
  
  <B> void copyToBuffer(Buffer<B> paramBuffer);
  
  <B> void copyToArray(Object paramObject, int paramInt1, int paramInt2);
  
  <B> void copyToArray(Object paramObject, int paramInt);
  
  <B> void copyToArray(Object paramObject);
  
  <B> Object toArray(ClassTag<B> paramClassTag);
  
  List<A> toList();
  
  Iterable<A> toIterable();
  
  Seq<A> toSeq();
  
  IndexedSeq<A> toIndexedSeq();
  
  <B> Buffer<B> toBuffer();
  
  Stream<A> toStream();
  
  <B> Set<B> toSet();
  
  <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> paramless);
  
  Traversable<A> toTraversable();
  
  Iterator<A> toIterator();
  
  String mkString(String paramString1, String paramString2, String paramString3);
  
  String mkString(String paramString);
  
  String mkString();
  
  StringBuilder addString(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3);
  
  StringBuilder addString(StringBuilder paramStringBuilder, String paramString);
  
  StringBuilder addString(StringBuilder paramStringBuilder);
  
  String stringPrefix();
  
  Object view();
  
  TraversableView<A, Repr> view(int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\TraversableProxyLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */