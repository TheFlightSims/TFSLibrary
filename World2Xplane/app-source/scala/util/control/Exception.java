/*     */ package scala.util.control;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ import scala.util.Either;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Left;
/*     */ import scala.util.Right;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021ms!B\001\003\021\003I\021!C#yG\026\004H/[8o\025\t\031A!A\004d_:$(o\0347\013\005\0251\021\001B;uS2T\021aB\001\006g\016\fG.Y\002\001!\tQ1\"D\001\003\r\025a!\001#\001\016\005%)\005pY3qi&|gn\005\002\f\035A\021q\002E\007\002\r%\021\021C\002\002\007\003:L(+\0324\t\013MYA\021\001\013\002\rqJg.\033;?)\005IQ\001\002\f\f\001]\021qaQ1uG\",'/\006\002\031SA!q\"G\016(\023\tQbAA\bQCJ$\030.\0317Gk:\034G/[8o!\taBE\004\002\036E9\021a$I\007\002?)\021\001\005C\001\007yI|w\016\036 \n\003\035I!a\t\004\002\017A\f7m[1hK&\021QE\n\002\n)\"\024xn^1cY\026T!a\t\004\021\005!JC\002\001\003\007UU!)\031A\026\003\003Q\013\"\001L\030\021\005=i\023B\001\030\007\005\035qu\016\0365j]\036\004\"a\004\031\n\005E2!aA!os\")1g\003C\001i\005IQn[\"bi\016DWM]\013\004k\035kDc\001\034K%R\021qG\020\n\004q9Qd\001B\0353\001]\022A\002\020:fM&tW-\\3oiz\0022aO\013=\033\005Y\001C\001\025>\t\025Q#G1\001,\021\035y$'!AA\004\001\013!\"\032<jI\026t7-\032\0232!\r\tEIR\007\002\005*\0211IB\001\be\0264G.Z2u\023\t)%I\001\005DY\006\0348\017V1h!\tAs\tB\003Ie\t\007\021J\001\002FqF\021Af\007\005\006\027J\002\r\001T\001\006SN$UM\032\t\005\03753u*\003\002O\r\tIa)\0368di&|g.\r\t\003\037AK!!\025\004\003\017\t{w\016\\3b]\")1K\ra\001)\006\ta\r\005\003\020\033\032c\004\"\002,\f\t\0039\026AE7l)\"\024xn^1cY\026\034\025\r^2iKJ,\"\001W/\025\007es\006ME\002[\035m3A!\017\032\0013B\0311(\006/\021\005!jF!\002\026V\005\004Y\003\"B&V\001\004y\006\003B\bN7=CQaU+A\002\005\004BaD'\0349\")1m\003C\002I\006IB\017\033:po\006\024G.Z*vERL\b/\032+p\007\006$8\r[3s+\r)\007o\033\013\003MF$\"a\0327\023\007!t\021N\002\003:e\0019\007cA\036\026UB\021\001f\033\003\006U\t\024\ra\013\005\b[\n\f\t\021q\001o\003))g/\0333f]\016,GE\r\t\004\003\022{\007C\001\025q\t\025A%M1\001J\021\025\021(\r1\001t\003\t\001h\r\005\003\0203=T\007\"B;\f\t\0031\030!D:i_VdGMU3uQJ|w\017\006\002Po\")\001\020\036a\0017\005\t\001PB\004{\027A\005\031\021A>\003\023\021+7o\031:jE\026$7CA=\017\021\025i\030\020\"\001\003\031!\023N\\5uIQ\tq\020E\002\020\003\003I1!a\001\007\005\021)f.\033;\t\023\005\035\021P1A\007\022\005%\021\001\0028b[\026,\"!a\003\021\t\0055\0211\003\b\004\037\005=\021bAA\t\r\0051\001K]3eK\032LA!!\006\002\030\t11\013\036:j]\036T1!!\005\007\021%\tY\"\037a\001\n\023\tI!A\003`I\026\0348\rC\005\002 e\004\r\021\"\003\002\"\005Iq\fZ3tG~#S-\035\013\004\006\r\002BCA\023\003;\t\t\0211\001\002\f\005\031\001\020J\031\t\021\005%\022\020)Q\005\003\027\taa\0303fg\016\004\003bBA\027s\022\005\021\021B\001\005I\026\0348\rC\004\0022e$\t!a\r\002\021]LG\017\033#fg\016$B!!\016\00285\t\021\020\003\005\002:\005=\002\031AA\006\003\005\031\bbBA\037s\022\005\023qH\001\ti>\034FO]5oOR\021\021\021\t\t\005\003\007\ni%\004\002\002F)!\021qIA%\003\021a\027M\\4\013\005\005-\023\001\0026bm\006LA!!\006\002F\0311\021\021K\006\001\003'\022qAR5oC2d\027pE\003\002P9\t)\006\005\002<s\"Y\021\021LA(\005\003%\013\021BA.\003\021\021w\016Z=\021\t=\tif`\005\004\003?2!\001\003\037cs:\fW.\032 \t\021M\ty\005\"\001\f\003G\"B!!\032\002hA\0311(a\024\t\023\005e\023\021\rCA\002\005m\003BCA\004\003\037\022\r\021\"\005\002lU\021\021\021\t\005\n\003_\ny\005)A\005\003\003\nQA\\1nK\002B\001\"a\035\002P\021\005\021QO\001\004C:$G\003BA3\003oB\021\"!\037\002r\021\005\r!a\027\002\013=$\b.\032:\t\017\005u\024q\nC\001}\0061\021N\034<pW\0264a!!!\f\001\005\r%!B\"bi\016DW\003BAC\003\037\033R!a \017\003+B!B]A@\005\013\007I\021AAE+\t\tY\t\005\003<+\0055\005c\001\025\002\020\0229!&a \005\006\004Y\003bCAJ\003\022\t\021)A\005\003\027\0131\001\0354!\021-\t9*a \003\006\004%\t!!'\002\007\031Lg.\006\002\002\034B)q\"!(\002f%\031\021q\024\004\003\r=\003H/[8o\021-\t\031+a \003\002\003\006I!a'\002\t\031Lg\016\t\005\f\003O\013yH!b\001\n\003\tI+A\004sKRD'o\\<\026\003}C!\"!,\002\000\t\005\t\025!\003`\003!\021X\r\0365s_^\004\003bB\n\002\000\021\005\021\021\027\013\t\003g\013),a.\002:B)1(a \002\016\"9!/a,A\002\005-\005BCAL\003_\003\n\0211\001\002\034\"I\021qUAX!\003\005\ra\030\005\013\003\017\tyH1A\005\022\005-\004\"CA8\003\002\013\021BA!\021!\t\t-a \005\002\005\r\027AA8s+\021\t)-a3\025\t\005\035\027\021\033\t\006w\005}\024\021\032\t\004Q\005-G\001CAg\003\023\r!a4\003\003U\0132!!$0\021!\t\031.a0A\002\005U\027a\0019geA!1(FAe\021!\t\t-a \005\002\005eW\003BAn\003C$B!!8\002dB)1(a \002`B\031\001&!9\005\021\0055\027q\033b\001\003\037D\001\"!\037\002X\002\007\021Q\034\005\t\003O\fy\b\"\001\002j\006)\021\r\0359msV!\0211^Ax)\021\ti/!=\021\007!\ny\017\002\005\002N\006\025(\031AAh\021%\tI&!:\005\002\004\t\031\020E\003\020\003;\ni\017\003\005\002x\006}D\021AA}\003)\tg\016\032$j]\006dG.\037\013\005\003g\013Y\020C\005\002Z\005UH\0211\001\002\\!A\021q`A@\t\003\021\t!A\002paR,BAa\001\003\nQ!!Q\001B\006!\025y\021Q\024B\004!\rA#\021\002\003\t\003\033\fiP1\001\002P\"I\021\021LA\t\003\007!Q\002\t\006\037\005u#q\001\005\t\005#\ty\b\"\001\003\024\0051Q-\033;iKJ,BA!\006\003\"Q!!q\003B\022!\035\021IBa\007\034\005?i\021\001B\005\004\005;!!AB#ji\",'\017E\002)\005C!\001\"!4\003\020\t\007\021q\032\005\n\0033\022y\001\"a\001\005K\001RaDA/\005?A\001B!\013\002\000\021\005!1F\001\bo&$\b\016\026:z+\021\021iCa\016\025\t\t=\"\021\b\t\007\0053\021\tD!\016\n\007\tMBAA\002Uef\0042\001\013B\034\t!\tiMa\nC\002\005=\007\"CA-\005O!\t\031\001B\036!\025y\021Q\fB\033\021!\021y$a \005\002\t\005\023!C<ji\"\f\005\017\0357z+\021\021\031E!\023\025\t\t\025#1\n\t\006w\005}$q\t\t\004Q\t%CaBAg\005{\021\ra\013\005\b'\nu\002\031\001B'!\025yQj\007B$\021!\021\t&a \005\002\tM\023\001\003;p\037B$\030n\0348\026\005\tU\003#B\036\002\000\t]\003#B\b\002\036\0065\005\002\003B.\003\"\tA!\030\002\021Q|W)\033;iKJ,\"Aa\030\021\013m\nyH!\031\021\017\te!1D\016\002\016\"A!QMA@\t\003\0219'A\003u_R\023\0300\006\002\003jA)1(a \003lA1!\021\004B\031\003\033;\021Ba\034\f\003\003E\tA!\035\002\013\r\013Go\0315\021\007m\022\031HB\005\002\002.\t\t\021#\001\003vM\031!1\017\b\t\017M\021\031\b\"\001\003zQ\021!\021\017\005\013\005{\022\031(%A\005\002\t}\024a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$#'\006\003\003\002\n]UC\001BBU\021\tYJ!\",\005\t\035\005\003\002BE\005'k!Aa#\013\t\t5%qR\001\nk:\034\007.Z2lK\022T1A!%\007\003)\tgN\\8uCRLwN\\\005\005\005+\023YIA\tv]\016DWmY6fIZ\013'/[1oG\026$aA\013B>\005\004Y\003B\003BN\005g\n\n\021\"\001\003\036\006YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIM*BAa(\003$V\021!\021\025\026\004?\n\025EA\002\026\003\032\n\0071\006C\005\003(.\021\r\021\"\002\003*\006qan\034;iS:<7)\031;dQ\026\024XC\001BV!\rYT\003\f\005\t\005_[\001\025!\004\003,\006yan\034;iS:<7)\031;dQ\026\024\b\005C\004\0034.!)A!.\002\0379|gNR1uC2\034\025\r^2iKJ,BAa.\003>V\021!\021\030\t\005wU\021Y\fE\002)\005{#aA\013BY\005\004Y\003b\002Ba\027\021\025!1Y\001\013C2d7)\031;dQ\026\024X\003\002Bc\005\027,\"Aa2\021\tm*\"\021\032\t\004Q\t-GA\002\026\003@\n\0071\006C\005\003P.\021\r\021\"\002\003R\0069an\\\"bi\016DWC\001Bj!\021Y\024q\020\027\t\021\t]7\002)A\007\005'\f\001B\\8DCR\034\007\016\t\005\b\0057\\AQ\001Bo\003!\tG\016\\\"bi\016DW\003\002Bp\005K,\"A!9\021\013m\nyHa9\021\007!\022)\017\002\004+\0053\024\ra\013\005\b\005S\\AQ\001Bv\0035qwN\034$bi\006d7)\031;dQV!!Q\036Bz+\t\021y\017E\003<\003\022\t\020E\002)\005g$aA\013Bt\005\004Y\003b\002B|\027\021\005!\021`\001\tG\006$8\r[5oOV!!1`B\001)\021\021ipa\001\021\013m\nyHa@\021\007!\032\t\001\002\004+\005k\024\ra\013\005\t\007\013\021)\0201\001\004\b\005QQ\r_2faRLwN\\:\021\013=\031Ia!\004\n\007\r-aA\001\006=e\026\004X-\031;fIz\002Daa\004\004\030A1\021QBB\t\007+IAaa\005\002\030\t)1\t\\1tgB\031\001fa\006\005\027\re11AA\001\002\003\025\ta\013\002\004?\022\n\004b\002B|\027\021\0051QD\013\005\007?\031)\003\006\003\004\"\r\035\002#B\036\002\000\r\r\002c\001\025\004&\0211!fa\007C\002-B\001b!\013\004\034\001\00711F\001\002GB!1(FB\022\021\035\031yc\003C\001\007c\tQcY1uG\"Lgn\032)s_6L7oY;pkNd\0270\006\003\0044\reB\003BB\033\007w\001RaOA@\007o\0012\001KB\035\t\031Q3Q\006b\001W!A1QAB\027\001\004\031i\004E\003\020\007\023\031y\004\r\003\004B\r\025\003CBA\007\007#\031\031\005E\002)\007\013\"1ba\022\004<\005\005\t\021!B\001W\t\031q\f\n\032\t\017\r=2\002\"\001\004LU!1QJB*)\021\031ye!\026\021\013m\nyh!\025\021\007!\032\031\006\002\004+\007\023\022\ra\013\005\t\007S\031I\0051\001\004XA!1(FB)\021\035\031Yf\003C\001\007;\n\001\"[4o_JLgn\032\013\005\007?\032\t\007\005\003<\003z\b\002CB\003\0073\002\raa\031\021\013=\031Ia!\0321\t\r\03541\016\t\007\003\033\031\tb!\033\021\007!\032Y\007B\006\004n\r\005\024\021!A\001\006\003Y#aA0%g!91\021O\006\005\002\rM\024a\0024bS2LgnZ\013\005\007k\032i\b\006\003\004x\r}\004#B\036\002\000\re\004#B\b\002\036\016m\004c\001\025\004~\0211!fa\034C\002-B\001b!\002\004p\001\0071\021\021\t\006\037\r%11\021\031\005\007\013\033I\t\005\004\002\016\rE1q\021\t\004Q\r%EaCBF\007\n\t\021!A\003\002-\0221a\030\0235\021\035\031yi\003C\001\007#\0131BZ1jY\006\033h+\0317vKV!11SBN)\021\031)ja)\025\t\r]5Q\024\t\006w\005}4\021\024\t\004Q\rmEA\002\026\004\016\n\0071\006C\005\004 \0165E\0211\001\004\"\006)a/\0317vKB)q\"!\030\004\032\"A1QABG\001\004\031)\013E\003\020\007\023\0319\013\r\003\004*\0165\006CBA\007\007#\031Y\013E\002)\007[#1ba,\004$\006\005\t\021!B\001W\t\031q\fJ\033\007\r\rM6\002AB[\005\t\021\0250\006\004\0048\016}61Y\n\004\007cs\001BC*\0042\n\005\t\025!\003\004<B1q\"TB_\007\003\0042\001KB`\t\031Q3\021\027b\001WA\031\001fa1\005\017\r\0257\021\027b\001W\t\t!\013C\004\024\007c#\ta!3\025\t\r-7Q\032\t\bw\rE6QXBa\021\035\0316q\031a\001\007wC\001b!5\0042\022\00511[\001\003Ef$Ba!1\004V\"9\001pa4A\002\ru\006bBBm\027\021\00511\\\001\tQ\006tG\r\\5oOV!1Q\\Bs)\021\031yn!;\021\017m\032\tl!9\004hB)q\"T\016\004dB\031\001f!:\005\r)\0329N1\001,!\025Y\024qPBr\021!\031)aa6A\002\r-\b#B\b\004\n\r5\b\007BBx\007g\004b!!\004\004\022\rE\bc\001\025\004t\022Y1Q_Bu\003\003\005\tQ!\001,\005\ryFE\016\005\b\007s\\A\021AB~\003))H\016^5nCR,G._\013\005\007{$\031\001\006\003\004\000\022\025\001#B\036\002\000\021\005\001c\001\025\005\004\0211!fa>C\002-B\021\"!\027\004x\022\005\r!a\027\t\017\021%1\002\"\001\005\f\005QQO\\<sCB\004\030N\\4\026\t\0215A1\003\013\005\t\037!)\002E\003<\003\"\t\002E\002)\t'!aA\013C\004\005\004Y\003\002CB\003\t\017\001\r\001b\006\021\013=\031I\001\"\0071\t\021mAq\004\t\007\003\033\031\t\002\"\b\021\007!\"y\002B\006\005\"\021U\021\021!A\001\006\003Y#aA0%o!9AQE\006\005\n\021\035\022AC<pk2$W*\031;dQR)q\n\"\013\005,!1\001\020b\tA\002mA\001\002\"\f\005$\001\007AqF\001\bG2\f7o]3t!\031!\t\004b\016\005<5\021A1\007\006\004\tk1\021AC2pY2,7\r^5p]&!A\021\bC\032\005\r\031V-\035\031\005\t{!\t\005\005\004\002\016\rEAq\b\t\004Q\021\005Ca\003C\"\tW\t\t\021!A\003\002-\0221a\030\0239\021\035!9e\003C\005\t\023\n\001\003\0354Ge>lW\t_2faRLwN\\:\025\t\021-CQ\n\t\005\037eYB\006\003\005\004\006\021\025\003\031\001C(!\025y1\021\002C)a\021!\031\006b\026\021\r\00551\021\003C+!\rACq\013\003\f\t3\"i%!A\001\002\013\0051FA\002`Ie\002")
/*     */ public final class Exception {
/*     */   public static <T> Catch<T> unwrapping(Seq<Class<?>> paramSeq) {
/*     */     return Exception$.MODULE$.unwrapping(paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> Catch<T> ultimately(Function0<BoxedUnit> paramFunction0) {
/*     */     return Exception$.MODULE$.ultimately(paramFunction0);
/*     */   }
/*     */   
/*     */   public static <T> By<Function1<Throwable, T>, Catch<T>> handling(Seq<Class<?>> paramSeq) {
/*     */     return Exception$.MODULE$.handling(paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> Catch<T> failAsValue(Seq<Class<?>> paramSeq, Function0<T> paramFunction0) {
/*     */     return Exception$.MODULE$.failAsValue(paramSeq, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <T> Catch<Option<T>> failing(Seq<Class<?>> paramSeq) {
/*     */     return Exception$.MODULE$.failing(paramSeq);
/*     */   }
/*     */   
/*     */   public static Catch<BoxedUnit> ignoring(Seq<Class<?>> paramSeq) {
/*     */     return Exception$.MODULE$.ignoring(paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> Catch<T> catchingPromiscuously(PartialFunction<Throwable, T> paramPartialFunction) {
/*     */     return Exception$.MODULE$.catchingPromiscuously(paramPartialFunction);
/*     */   }
/*     */   
/*     */   public static <T> Catch<T> catchingPromiscuously(Seq<Class<?>> paramSeq) {
/*     */     return Exception$.MODULE$.catchingPromiscuously(paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> Catch<T> catching(PartialFunction<Throwable, T> paramPartialFunction) {
/*     */     return Exception$.MODULE$.catching(paramPartialFunction);
/*     */   }
/*     */   
/*     */   public static <T> Catch<T> catching(Seq<Class<?>> paramSeq) {
/*     */     return Exception$.MODULE$.catching(paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> Catch<T> nonFatalCatch() {
/*     */     return Exception$.MODULE$.nonFatalCatch();
/*     */   }
/*     */   
/*     */   public static <T> Catch<T> allCatch() {
/*     */     return Exception$.MODULE$.allCatch();
/*     */   }
/*     */   
/*     */   public static Catch<scala.runtime.Nothing$> noCatch() {
/*     */     return Exception$.MODULE$.noCatch();
/*     */   }
/*     */   
/*     */   public static <T> PartialFunction<Throwable, T> allCatcher() {
/*     */     return Exception$.MODULE$.allCatcher();
/*     */   }
/*     */   
/*     */   public static <T> PartialFunction<Throwable, T> nonFatalCatcher() {
/*     */     return Exception$.MODULE$.nonFatalCatcher();
/*     */   }
/*     */   
/*     */   public static PartialFunction<Throwable, scala.runtime.Nothing$> nothingCatcher() {
/*     */     return Exception$.MODULE$.nothingCatcher();
/*     */   }
/*     */   
/*     */   public static boolean shouldRethrow(Throwable paramThrowable) {
/*     */     return Exception$.MODULE$.shouldRethrow(paramThrowable);
/*     */   }
/*     */   
/*     */   public static <Ex extends Throwable, T> Object throwableSubtypeToCatcher(PartialFunction<Ex, T> paramPartialFunction, ClassTag<Ex> paramClassTag) {
/*     */     return Exception$.MODULE$.throwableSubtypeToCatcher(paramPartialFunction, paramClassTag);
/*     */   }
/*     */   
/*     */   public static <T> Object mkThrowableCatcher(Function1<Throwable, Object> paramFunction1, Function1<Throwable, T> paramFunction11) {
/*     */     return Exception$.MODULE$.mkThrowableCatcher(paramFunction1, paramFunction11);
/*     */   }
/*     */   
/*     */   public static <Ex extends Throwable, T> Object mkCatcher(Function1<Ex, Object> paramFunction1, Function1<Ex, T> paramFunction11, ClassTag<Ex> paramClassTag) {
/*     */     return Exception$.MODULE$.mkCatcher(paramFunction1, paramFunction11, paramClassTag);
/*     */   }
/*     */   
/*     */   public static class Exception$$anon$1 implements PartialFunction<Throwable, T> {
/*     */     private final Function1 isDef$1;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     private final ClassTag evidence$1$1;
/*     */     
/*     */     public <A1 extends Throwable, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/*  39 */       return PartialFunction.class.orElse(this, that);
/*     */     }
/*     */     
/*     */     public <C> PartialFunction<Throwable, C> andThen(Function1 k) {
/*  39 */       return PartialFunction.class.andThen(this, k);
/*     */     }
/*     */     
/*     */     public Function1<Throwable, Option<T>> lift() {
/*  39 */       return PartialFunction.class.lift(this);
/*     */     }
/*     */     
/*     */     public <A1 extends Throwable, B1> B1 applyOrElse(Object x, Function1 default) {
/*  39 */       return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*     */     }
/*     */     
/*     */     public <U> Function1<Throwable, Object> runWith(Function1 action) {
/*  39 */       return PartialFunction.class.runWith(this, action);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/*  39 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/*  39 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/*  39 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/*  39 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/*  39 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/*  39 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/*  39 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/*  39 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/*  39 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/*  39 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/*  39 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/*  39 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/*  39 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/*  39 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/*  39 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/*  39 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/*  39 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/*  39 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/*  39 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/*  39 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/*  39 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/*  39 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/*  39 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/*  39 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, T> compose(Function1 g) {
/*  39 */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  39 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  39 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  39 */       return Function1.class.toString((Function1)this);
/*     */     }
/*     */     
/*     */     public Exception$$anon$1(Function1 isDef$1, Function1 f$1, ClassTag evidence$1$1) {
/*  39 */       Function1.class.$init$((Function1)this);
/*  39 */       PartialFunction.class.$init$(this);
/*     */     }
/*     */     
/*     */     private Option<Ex> downcast(Throwable x) {
/*  41 */       return scala.reflect.package$.MODULE$.classTag(this.evidence$1$1).runtimeClass().isAssignableFrom(x.getClass()) ? (Option<Ex>)new Some(x) : 
/*  42 */         (Option<Ex>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(Throwable x) {
/*  44 */       Function1 function1 = this.isDef$1;
/*     */       Option<Ex> option;
/*  44 */       return (!(option = downcast(x)).isEmpty() && BoxesRunTime.unboxToBoolean(function1.apply(option.get())));
/*     */     }
/*     */     
/*     */     public T apply(Throwable x) {
/*  45 */       return (T)this.f$1.apply(downcast(x).get());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$throwableSubtypeToCatcher$1 extends AbstractFunction1<Ex, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction pf$1;
/*     */     
/*     */     public final boolean apply(Throwable x) {
/*  51 */       return this.pf$1.isDefinedAt(x);
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$throwableSubtypeToCatcher$1(PartialFunction pf$1) {}
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$throwableSubtypeToCatcher$2 extends AbstractFunction1<Ex, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction pf$1;
/*     */     
/*     */     public final T apply(Throwable v1) {
/*  51 */       return (T)this.pf$1.apply(v1);
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$throwableSubtypeToCatcher$2(PartialFunction pf$1) {}
/*     */   }
/*     */   
/*     */   public static abstract class Described$class {
/*     */     public static void $init$(Exception.Described $this) {
/*  65 */       $this.scala$util$control$Exception$Described$$_desc_$eq("");
/*     */     }
/*     */     
/*     */     public static String desc(Exception.Described $this) {
/*  66 */       return $this.scala$util$control$Exception$Described$$_desc();
/*     */     }
/*     */     
/*     */     public static Exception.Described withDesc(Exception.Described $this, String s) {
/*  68 */       $this.scala$util$control$Exception$Described$$_desc_$eq(s);
/*  69 */       return $this;
/*     */     }
/*     */     
/*     */     public static String toString(Exception.Described $this) {
/*  71 */       return (new StringBuilder()).append($this.name()).append("(").append($this.desc()).append(")").toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Finally implements Described {
/*     */     public final Function0<BoxedUnit> scala$util$control$Exception$Finally$$body;
/*     */     
/*     */     private final String name;
/*     */     
/*     */     private String scala$util$control$Exception$Described$$_desc;
/*     */     
/*     */     public String scala$util$control$Exception$Described$$_desc() {
/*  75 */       return this.scala$util$control$Exception$Described$$_desc;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void scala$util$control$Exception$Described$$_desc_$eq(String x$1) {
/*  75 */       this.scala$util$control$Exception$Described$$_desc = x$1;
/*     */     }
/*     */     
/*     */     public String desc() {
/*  75 */       return Exception.Described$class.desc(this);
/*     */     }
/*     */     
/*     */     public Exception.Described withDesc(String s) {
/*  75 */       return Exception.Described$class.withDesc(this, s);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  75 */       return Exception.Described$class.toString(this);
/*     */     }
/*     */     
/*     */     public Finally(Function0<BoxedUnit> body) {
/*  75 */       Exception.Described$class.$init$(this);
/*  76 */       this.name = "Finally";
/*     */     }
/*     */     
/*     */     public String name() {
/*  76 */       return this.name;
/*     */     }
/*     */     
/*     */     public Finally and(Function0 other) {
/*  78 */       return new Finally((Function0<BoxedUnit>)new Exception$Finally$$anonfun$and$1(this, other));
/*     */     }
/*     */     
/*     */     public class Exception$Finally$$anonfun$and$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Function0 other$1;
/*     */       
/*     */       public final void apply() {
/*  78 */         this.$outer.scala$util$control$Exception$Finally$$body.apply$mcV$sp();
/*  78 */         this.other$1.apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*  78 */         this.$outer.scala$util$control$Exception$Finally$$body.apply$mcV$sp();
/*  78 */         this.other$1.apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public Exception$Finally$$anonfun$and$1(Exception.Finally $outer, Function0 other$1) {}
/*     */     }
/*     */     
/*     */     public void invoke() {
/*  79 */       this.scala$util$control$Exception$Finally$$body.apply$mcV$sp();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Catch<T> implements Described {
/*     */     private final PartialFunction<Throwable, T> pf;
/*     */     
/*     */     private final Option<Exception.Finally> fin;
/*     */     
/*     */     private final Function1<Throwable, Object> rethrow;
/*     */     
/*     */     private final String name;
/*     */     
/*     */     private String scala$util$control$Exception$Described$$_desc;
/*     */     
/*     */     public String scala$util$control$Exception$Described$$_desc() {
/*  88 */       return this.scala$util$control$Exception$Described$$_desc;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void scala$util$control$Exception$Described$$_desc_$eq(String x$1) {
/*  88 */       this.scala$util$control$Exception$Described$$_desc = x$1;
/*     */     }
/*     */     
/*     */     public String desc() {
/*  88 */       return Exception.Described$class.desc(this);
/*     */     }
/*     */     
/*     */     public Exception.Described withDesc(String s) {
/*  88 */       return Exception.Described$class.withDesc(this, s);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  88 */       return Exception.Described$class.toString(this);
/*     */     }
/*     */     
/*     */     public PartialFunction<Throwable, T> pf() {
/*  89 */       return this.pf;
/*     */     }
/*     */     
/*     */     public Catch(PartialFunction<Throwable, T> pf, Option<Exception.Finally> fin, Function1<Throwable, Object> rethrow) {
/*     */       Exception.Described$class.$init$(this);
/*  94 */       this.name = "Catch";
/*     */     }
/*     */     
/*     */     public Option<Exception.Finally> fin() {
/*     */       return this.fin;
/*     */     }
/*     */     
/*     */     public Function1<Throwable, Object> rethrow() {
/*     */       return this.rethrow;
/*     */     }
/*     */     
/*     */     public static class Exception$Catch$$anonfun$$lessinit$greater$default$3$1 extends AbstractFunction1<Throwable, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Throwable x) {
/*     */         return Exception$.MODULE$.shouldRethrow(x);
/*     */       }
/*     */     }
/*     */     
/*     */     public String name() {
/*  94 */       return this.name;
/*     */     }
/*     */     
/*     */     public <U> Catch<U> or(PartialFunction pf2) {
/*  97 */       return new Catch(pf().orElse(pf2), fin(), rethrow());
/*     */     }
/*     */     
/*     */     public <U> Catch<U> or(Catch<U> other) {
/*  98 */       return or(other.pf());
/*     */     }
/*     */     
/*     */     public <U> U apply(Function0 body) {
/*     */       java.lang.Exception exception;
/*     */       try {
/* 107 */         ((Exception.Finally)option1.get()).invoke();
/*     */         Option<Exception.Finally> option1;
/* 107 */         (option1 = fin()).isEmpty() ? scala.None$.MODULE$ : new Some(BoxedUnit.UNIT);
/*     */         return (U)body.apply();
/*     */       } finally {
/*     */         Option<Exception.Finally> option1;
/*     */       } 
/*     */       Option<Exception.Finally> option;
/* 107 */       if ((option = fin()).isEmpty());
/* 107 */       ((Exception.Finally)option.get()).invoke();
/* 107 */       new Some(BoxedUnit.UNIT);
/* 107 */       throw exception;
/*     */     }
/*     */     
/*     */     public Catch<T> andFinally(Function0<BoxedUnit> body) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokevirtual fin : ()Lscala/Option;
/*     */       //   4: astore #4
/*     */       //   6: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   9: dup
/*     */       //   10: ifnonnull -> 22
/*     */       //   13: pop
/*     */       //   14: aload #4
/*     */       //   16: ifnull -> 30
/*     */       //   19: goto -> 64
/*     */       //   22: aload #4
/*     */       //   24: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   27: ifeq -> 64
/*     */       //   30: new scala/util/control/Exception$Catch
/*     */       //   33: dup
/*     */       //   34: aload_0
/*     */       //   35: invokevirtual pf : ()Lscala/PartialFunction;
/*     */       //   38: new scala/Some
/*     */       //   41: dup
/*     */       //   42: new scala/util/control/Exception$Finally
/*     */       //   45: dup
/*     */       //   46: aload_1
/*     */       //   47: invokespecial <init> : (Lscala/Function0;)V
/*     */       //   50: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   53: aload_0
/*     */       //   54: invokevirtual rethrow : ()Lscala/Function1;
/*     */       //   57: invokespecial <init> : (Lscala/PartialFunction;Lscala/Option;Lscala/Function1;)V
/*     */       //   60: astore_3
/*     */       //   61: goto -> 112
/*     */       //   64: aload #4
/*     */       //   66: instanceof scala/Some
/*     */       //   69: ifeq -> 114
/*     */       //   72: aload #4
/*     */       //   74: checkcast scala/Some
/*     */       //   77: astore_2
/*     */       //   78: new scala/util/control/Exception$Catch
/*     */       //   81: dup
/*     */       //   82: aload_0
/*     */       //   83: invokevirtual pf : ()Lscala/PartialFunction;
/*     */       //   86: new scala/Some
/*     */       //   89: dup
/*     */       //   90: aload_2
/*     */       //   91: invokevirtual x : ()Ljava/lang/Object;
/*     */       //   94: checkcast scala/util/control/Exception$Finally
/*     */       //   97: aload_1
/*     */       //   98: invokevirtual and : (Lscala/Function0;)Lscala/util/control/Exception$Finally;
/*     */       //   101: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   104: aload_0
/*     */       //   105: invokevirtual rethrow : ()Lscala/Function1;
/*     */       //   108: invokespecial <init> : (Lscala/PartialFunction;Lscala/Option;Lscala/Function1;)V
/*     */       //   111: astore_3
/*     */       //   112: aload_3
/*     */       //   113: areturn
/*     */       //   114: new scala/MatchError
/*     */       //   117: dup
/*     */       //   118: aload #4
/*     */       //   120: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   123: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #110	-> 0
/*     */       //   #111	-> 6
/*     */       //   #112	-> 64
/*     */       //   #110	-> 90
/*     */       //   #112	-> 91
/*     */       //   #110	-> 112
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	124	0	this	Lscala/util/control/Exception$Catch;
/*     */       //   0	124	1	body	Lscala/Function0;
/*     */     }
/*     */     
/*     */     public <U> Option<U> opt(Function0 body) {
/* 118 */       return toOption().<Option<U>>apply((Function0<Option<U>>)new Exception$Catch$$anonfun$opt$1(this, (Catch<T>)body));
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$opt$1 extends AbstractFunction0<Some<U>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function0 body$2;
/*     */       
/*     */       public final Some<U> apply() {
/* 118 */         return new Some(this.body$2.apply());
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$opt$1(Exception.Catch $outer, Function0 body$2) {}
/*     */     }
/*     */     
/*     */     public <U> Either<Throwable, U> either(Function0 body) {
/* 124 */       return toEither().<Either<Throwable, U>>apply((Function0<Either<Throwable, U>>)new Exception$Catch$$anonfun$either$1(this, (Catch<T>)body));
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$either$1 extends AbstractFunction0<Right<scala.runtime.Nothing$, U>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function0 body$1;
/*     */       
/*     */       public final Right<scala.runtime.Nothing$, U> apply() {
/* 124 */         return new Right(this.body$1.apply());
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$either$1(Exception.Catch $outer, Function0 body$1) {}
/*     */     }
/*     */     
/*     */     public <U> Try<U> withTry(Function0 body) {
/* 129 */       return toTry().<Try<U>>apply((Function0<Try<U>>)new Exception$Catch$$anonfun$withTry$1(this, (Catch<T>)body));
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$withTry$1 extends AbstractFunction0<Success<U>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function0 body$3;
/*     */       
/*     */       public final Success<U> apply() {
/* 129 */         return new Success(this.body$3.apply());
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$withTry$1(Exception.Catch $outer, Function0 body$3) {}
/*     */     }
/*     */     
/*     */     public <U> Catch<U> withApply(Function1 f) {
/* 134 */       PartialFunction<Throwable, T> pf2 = new Exception$Catch$$anon$2(this, (Catch<T>)f);
/* 138 */       return new Catch(pf2, fin(), rethrow());
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anon$2 implements PartialFunction<Throwable, U> {
/*     */       private final Function1 f$2;
/*     */       
/*     */       public <A1 extends Throwable, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/*     */         return PartialFunction.class.orElse(this, that);
/*     */       }
/*     */       
/*     */       public <C> PartialFunction<Throwable, C> andThen(Function1 k) {
/*     */         return PartialFunction.class.andThen(this, k);
/*     */       }
/*     */       
/*     */       public Function1<Throwable, Option<U>> lift() {
/*     */         return PartialFunction.class.lift(this);
/*     */       }
/*     */       
/*     */       public <A1 extends Throwable, B1> B1 applyOrElse(Object x, Function1 default) {
/*     */         return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*     */       }
/*     */       
/*     */       public <U> Function1<Throwable, Object> runWith(Function1 action) {
/*     */         return PartialFunction.class.runWith(this, action);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZD$sp(double v1) {
/*     */         return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public double apply$mcDD$sp(double v1) {
/*     */         return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public float apply$mcFD$sp(double v1) {
/*     */         return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public int apply$mcID$sp(double v1) {
/*     */         return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public long apply$mcJD$sp(double v1) {
/*     */         return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public void apply$mcVD$sp(double v1) {
/*     */         Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZF$sp(float v1) {
/*     */         return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public double apply$mcDF$sp(float v1) {
/*     */         return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public float apply$mcFF$sp(float v1) {
/*     */         return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public int apply$mcIF$sp(float v1) {
/*     */         return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public long apply$mcJF$sp(float v1) {
/*     */         return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public void apply$mcVF$sp(float v1) {
/*     */         Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZI$sp(int v1) {
/*     */         return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public double apply$mcDI$sp(int v1) {
/*     */         return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public float apply$mcFI$sp(int v1) {
/*     */         return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public int apply$mcII$sp(int v1) {
/*     */         return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public long apply$mcJI$sp(int v1) {
/*     */         return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public void apply$mcVI$sp(int v1) {
/*     */         Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZJ$sp(long v1) {
/*     */         return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public double apply$mcDJ$sp(long v1) {
/*     */         return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public float apply$mcFJ$sp(long v1) {
/*     */         return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public int apply$mcIJ$sp(long v1) {
/*     */         return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public long apply$mcJJ$sp(long v1) {
/*     */         return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public void apply$mcVJ$sp(long v1) {
/*     */         Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, U> compose(Function1 g) {
/*     */         return Function1.class.compose((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*     */         return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*     */         return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*     */         return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*     */         return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*     */         return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*     */         return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*     */         return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*     */         return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*     */         return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*     */         return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*     */         return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*     */         return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public String toString() {
/*     */         return Function1.class.toString((Function1)this);
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anon$2(Exception.Catch $outer, Function1 f$2) {
/*     */         Function1.class.$init$((Function1)this);
/*     */         PartialFunction.class.$init$(this);
/*     */       }
/*     */       
/*     */       public boolean isDefinedAt(Throwable x) {
/*     */         return this.$outer.pf().isDefinedAt(x);
/*     */       }
/*     */       
/*     */       public U apply(Throwable x) {
/*     */         return (U)this.f$2.apply(x);
/*     */       }
/*     */     }
/*     */     
/*     */     public Catch<Option<T>> toOption() {
/* 142 */       return withApply((Function1<Throwable, Option<T>>)new Exception$Catch$$anonfun$toOption$1(this));
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$toOption$1 extends AbstractFunction1<Throwable, scala.None$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final scala.None$ apply(Throwable x$2) {
/* 142 */         return scala.None$.MODULE$;
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$toOption$1(Exception.Catch $outer) {}
/*     */     }
/*     */     
/*     */     public Catch<Either<Throwable, T>> toEither() {
/* 143 */       return withApply((Function1<Throwable, Either<Throwable, T>>)new Exception$Catch$$anonfun$toEither$1(this));
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$toEither$1 extends AbstractFunction1<Throwable, Left<Throwable, scala.runtime.Nothing$>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Left<Throwable, scala.runtime.Nothing$> apply(Throwable x$3) {
/* 143 */         return new Left(x$3);
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$toEither$1(Exception.Catch $outer) {}
/*     */     }
/*     */     
/*     */     public Catch<Try<T>> toTry() {
/* 144 */       return withApply((Function1<Throwable, Try<T>>)new Exception$Catch$$anonfun$toTry$1(this));
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$toTry$1 extends AbstractFunction1<Throwable, Failure<scala.runtime.Nothing$>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Failure<scala.runtime.Nothing$> apply(Throwable x) {
/* 144 */         return new Failure(x);
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$toTry$1(Exception.Catch $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Catch$ {
/*     */     public static final Catch$ MODULE$;
/*     */     
/*     */     public Catch$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public <T> Option<Exception.Finally> $lessinit$greater$default$2() {
/*     */       return (Option<Exception.Finally>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public <T> Function1<Throwable, Object> $lessinit$greater$default$3() {
/*     */       return (Function1<Throwable, Object>)new Exception$Catch$$anonfun$$lessinit$greater$default$3$1();
/*     */     }
/*     */     
/*     */     public static class Exception$Catch$$anonfun$$lessinit$greater$default$3$1 extends AbstractFunction1<Throwable, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Throwable x) {
/*     */         return Exception$.MODULE$.shouldRethrow(x);
/*     */       }
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$opt$1 extends AbstractFunction0<Some<U>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function0 body$2;
/*     */       
/*     */       public final Some<U> apply() {
/*     */         return new Some(this.body$2.apply());
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$opt$1(Exception.Catch $outer, Function0 body$2) {}
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$either$1 extends AbstractFunction0<Right<scala.runtime.Nothing$, U>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function0 body$1;
/*     */       
/*     */       public final Right<scala.runtime.Nothing$, U> apply() {
/*     */         return new Right(this.body$1.apply());
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$either$1(Exception.Catch $outer, Function0 body$1) {}
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$withTry$1 extends AbstractFunction0<Success<U>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function0 body$3;
/*     */       
/*     */       public final Success<U> apply() {
/*     */         return new Success(this.body$3.apply());
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$withTry$1(Exception.Catch $outer, Function0 body$3) {}
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anon$2 implements PartialFunction<Throwable, U> {
/*     */       private final Function1 f$2;
/*     */       
/*     */       public <A1 extends Throwable, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/*     */         return PartialFunction.class.orElse(this, that);
/*     */       }
/*     */       
/*     */       public <C> PartialFunction<Throwable, C> andThen(Function1 k) {
/*     */         return PartialFunction.class.andThen(this, k);
/*     */       }
/*     */       
/*     */       public Function1<Throwable, Option<U>> lift() {
/*     */         return PartialFunction.class.lift(this);
/*     */       }
/*     */       
/*     */       public <A1 extends Throwable, B1> B1 applyOrElse(Object x, Function1 default) {
/*     */         return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*     */       }
/*     */       
/*     */       public <U> Function1<Throwable, Object> runWith(Function1 action) {
/*     */         return PartialFunction.class.runWith(this, action);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZD$sp(double v1) {
/*     */         return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public double apply$mcDD$sp(double v1) {
/*     */         return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public float apply$mcFD$sp(double v1) {
/*     */         return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public int apply$mcID$sp(double v1) {
/*     */         return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public long apply$mcJD$sp(double v1) {
/*     */         return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public void apply$mcVD$sp(double v1) {
/*     */         Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZF$sp(float v1) {
/*     */         return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public double apply$mcDF$sp(float v1) {
/*     */         return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public float apply$mcFF$sp(float v1) {
/*     */         return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public int apply$mcIF$sp(float v1) {
/*     */         return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public long apply$mcJF$sp(float v1) {
/*     */         return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public void apply$mcVF$sp(float v1) {
/*     */         Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZI$sp(int v1) {
/*     */         return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public double apply$mcDI$sp(int v1) {
/*     */         return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public float apply$mcFI$sp(int v1) {
/*     */         return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public int apply$mcII$sp(int v1) {
/*     */         return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public long apply$mcJI$sp(int v1) {
/*     */         return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public void apply$mcVI$sp(int v1) {
/*     */         Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZJ$sp(long v1) {
/*     */         return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public double apply$mcDJ$sp(long v1) {
/*     */         return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public float apply$mcFJ$sp(long v1) {
/*     */         return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public int apply$mcIJ$sp(long v1) {
/*     */         return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public long apply$mcJJ$sp(long v1) {
/*     */         return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public void apply$mcVJ$sp(long v1) {
/*     */         Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, U> compose(Function1 g) {
/*     */         return Function1.class.compose((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*     */         return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*     */         return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*     */         return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*     */         return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*     */         return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*     */         return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*     */         return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*     */         return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*     */         return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*     */         return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*     */         return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*     */         return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*     */         return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*     */         return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*     */         return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */       }
/*     */       
/*     */       public String toString() {
/*     */         return Function1.class.toString((Function1)this);
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anon$2(Exception.Catch $outer, Function1 f$2) {
/*     */         Function1.class.$init$((Function1)this);
/*     */         PartialFunction.class.$init$(this);
/*     */       }
/*     */       
/*     */       public boolean isDefinedAt(Throwable x) {
/*     */         return this.$outer.pf().isDefinedAt(x);
/*     */       }
/*     */       
/*     */       public U apply(Throwable x) {
/*     */         return (U)this.f$2.apply(x);
/*     */       }
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$toOption$1 extends AbstractFunction1<Throwable, scala.None$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final scala.None$ apply(Throwable x$2) {
/*     */         return scala.None$.MODULE$;
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$toOption$1(Exception.Catch $outer) {}
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$toEither$1 extends AbstractFunction1<Throwable, Left<Throwable, scala.runtime.Nothing$>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Left<Throwable, scala.runtime.Nothing$> apply(Throwable x$3) {
/*     */         return new Left(x$3);
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$toEither$1(Exception.Catch $outer) {}
/*     */     }
/*     */     
/*     */     public class Exception$Catch$$anonfun$toTry$1 extends AbstractFunction1<Throwable, Failure<scala.runtime.Nothing$>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Failure<scala.runtime.Nothing$> apply(Throwable x) {
/* 144 */         return new Failure(x);
/*     */       }
/*     */       
/*     */       public Exception$Catch$$anonfun$toTry$1(Exception.Catch $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractFunction1<Throwable, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Throwable x$4) {
/* 147 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anonfun$2 extends AbstractFunction1<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(Throwable x$5) {
/* 147 */       throw x$5;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$nonFatalCatcher$1 extends AbstractFunction1<Throwable, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Throwable x0$1) {
/*     */       boolean bool;
/* 148 */       Option<Throwable> option = NonFatal$.MODULE$.unapply(x0$1);
/* 148 */       if (option.isEmpty()) {
/* 148 */         bool = false;
/*     */       } else {
/* 148 */         bool = true;
/*     */       } 
/* 148 */       return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$nonFatalCatcher$2 extends AbstractFunction1<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(Throwable x$6) {
/* 148 */       throw x$6;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$allCatcher$1 extends AbstractFunction1<Throwable, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Throwable x$7) {
/* 149 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$allCatcher$2 extends AbstractFunction1<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(Throwable x$8) {
/* 149 */       throw x$8;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$catching$1 extends AbstractFunction1<Class<?>, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Class x$9) {
/* 170 */       return x$9.getName();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$catchingPromiscuously$1 extends AbstractFunction1<Throwable, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Throwable x$10) {
/* 180 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$ignoring$1 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Throwable x$11) {}
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$failing$1 extends AbstractFunction1<Throwable, scala.None$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.None$ apply(Throwable x$12) {
/* 188 */       return scala.None$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$failAsValue$1 extends AbstractFunction1<Throwable, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function0 value$1;
/*     */     
/*     */     public final T apply(Throwable x$13) {
/* 192 */       return (T)this.value$1.apply();
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$failAsValue$1(Function0 value$1) {}
/*     */   }
/*     */   
/*     */   public static class By<T, R> {
/*     */     private final Function1<T, R> f;
/*     */     
/*     */     public By(Function1<T, R> f) {}
/*     */     
/*     */     public R by(Object x) {
/* 201 */       return (R)this.f.apply(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$handling$1 extends AbstractFunction1<Function1<Throwable, T>, Catch<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq exceptions$2;
/*     */     
/*     */     public final Exception.Catch<T> apply(Function1 f) {
/* 205 */       return Exception$.MODULE$.scala$util$control$Exception$$fun$1(f, this.exceptions$2);
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$handling$1(Seq exceptions$2) {}
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$unwrapping$1 extends AbstractFunction1<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq exceptions$3;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(Throwable x) {
/* 217 */       throw Exception$.MODULE$.scala$util$control$Exception$$unwrap$1(x, this.exceptions$3);
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$unwrapping$1(Seq exceptions$3) {}
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$scala$util$control$Exception$$wouldMatch$1 extends AbstractFunction1<Class<?>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Throwable x$15;
/*     */     
/*     */     public final boolean apply(Class x$14) {
/* 222 */       return x$14.isAssignableFrom(this.x$15.getClass());
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$scala$util$control$Exception$$wouldMatch$1(Throwable x$15) {}
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$pfFromExceptions$1 extends AbstractPartialFunction<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq exceptions$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/* 225 */       if (Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$1))
/* 225 */         throw x1; 
/* 225 */       return (B1)default.apply(x1);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       boolean bool;
/* 225 */       if (Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$1)) {
/* 225 */         bool = true;
/*     */       } else {
/* 225 */         bool = false;
/*     */       } 
/* 225 */       return bool;
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$pfFromExceptions$1(Seq exceptions$1) {}
/*     */   }
/*     */   
/*     */   public static interface Described {
/*     */     String name();
/*     */     
/*     */     String scala$util$control$Exception$Described$$_desc();
/*     */     
/*     */     @TraitSetter
/*     */     void scala$util$control$Exception$Described$$_desc_$eq(String param1String);
/*     */     
/*     */     String desc();
/*     */     
/*     */     Described withDesc(String param1String);
/*     */     
/*     */     String toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\control\Exception.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */