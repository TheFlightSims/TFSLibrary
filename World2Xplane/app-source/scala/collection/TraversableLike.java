/*     */ package scala.collection;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.HasNewBuilder;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParIterable;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021eaaB\001\003!\003\r\ta\002\002\020)J\fg/\032:tC\ndW\rT5lK*\0211\001B\001\013G>dG.Z2uS>t'\"A\003\002\013M\034\027\r\\1\004\001U\031\001\"\006\017\024\017\001IQBH\021&QA\021!bC\007\002\t%\021A\002\002\002\004\003:L\b\003\002\b\022'mi\021a\004\006\003!\t\tqaZ3oKJL7-\003\002\023\037\ti\001*Y:OK^\024U/\0337eKJ\004\"\001F\013\r\001\0211a\003\001CC\002]\021\021!Q\t\0031%\001\"AC\r\n\005i!!a\002(pi\"Lgn\032\t\003)q!a!\b\001\005\006\0049\"\001\002*faJ\004BAD\020\0247%\021\001e\004\002\016\r&dG/\032:N_:\fG-[2\021\007\t\0323#D\001\003\023\t!#AA\bUe\0064XM]:bE2,wJ\\2f!\021\021ceE\016\n\005\035\022!AE$f]R\023\030M^3sg\006\024G.\032'jW\026\004BAI\025\024W%\021!F\001\002\017!\006\024\030\r\0347fY&T\030M\0317f!\rasfE\007\002[)\021aFA\001\ta\006\024\030\r\0347fY&\021\001'\f\002\f!\006\024\030\n^3sC\ndW\rC\0033\001\021\0051'\001\004%S:LG\017\n\013\002iA\021!\"N\005\003m\021\021A!\0268ji\0261\001\b\001Q\001\022m\021AaU3mM\")!\b\001C\001w\005!!/\0329s+\005Y\002\"B\037\001\t\013q\024AE5t)J\fg/\032:tC\ndW-Q4bS:,\022a\020\t\003\025\001K!!\021\003\003\017\t{w\016\\3b]\"11\t\001Q\005\022\021\013a\002\0365jg\016{G\016\\3di&|g.F\001F!\r\021ciE\005\003\017\n\0211\002\026:bm\026\0248/\0312mK\"1\021\n\001Q\005\022)\013A\002^8D_2dWm\031;j_:$\"!R&\t\013iB\005\031A\016\t\r5\003\001U\"\005O\003)qWm\036\"vS2$WM]\013\002\037B!\001kU\n\034\033\005\t&B\001*\003\003\035iW\017^1cY\026L!\001V)\003\017\t+\030\016\0343fe\"1a\013\001Q\005\022]\0131\002]1s\007>l'-\0338feV\t\001\f\005\003-3NY\023B\001..\005!\031u.\0342j]\026\024\b\"\002/\001\r\003i\026a\0024pe\026\f7\r[\013\003=\026$\"\001N0\t\013\001\\\006\031A1\002\003\031\004BA\0032\024I&\0211\r\002\002\n\rVt7\r^5p]F\002\"\001F3\005\013\031\\&\031A\f\003\003UCQ\001\033\001\005\002y\nq![:F[B$\030\020C\003k\001\021\005a(A\biCN$UMZ5oSR,7+\033>f\021\025a\007\001\"\001n\003)!\003\017\\;tIAdWo]\013\004]f\fHCA8})\t\0018\017\005\002\025c\022)!o\033b\001/\t!A\013[1u\021\025!8\016q\001v\003\t\021g\rE\003\017mnA\b/\003\002x\037\ta1)\0318Ck&dGM\022:p[B\021A#\037\003\006u.\024\ra\037\002\002\005F\0211#\003\005\006{.\004\rA`\001\005i\"\fG\017E\002#bL1!!\001\003\005I9UM\034+sCZ,'o]1cY\026|enY3\t\017\005\025\001\001\"\001\002\b\005\001B\005\0357vg\022\002H.^:%G>dwN\\\013\007\003\023\t9\"a\004\025\t\005-\021\021\004\013\005\003\033\t\t\002E\002\025\003\037!aA]A\002\005\0049\002b\002;\002\004\001\017\0211\003\t\b\035Y\\\022QCA\007!\r!\022q\003\003\007u\006\r!\031A>\t\017u\f\031\0011\001\002\034A!!eIA\013\021\035\t)\001\001C\001\003?)b!!\t\0020\005\035B\003BA\022\003c!B!!\n\002*A\031A#a\n\005\rI\fiB1\001\030\021\035!\030Q\004a\002\003W\001rA\004<\034\003[\t)\003E\002\025\003_!aA_A\017\005\004Y\bbB?\002\036\001\007\0211\007\t\005E\031\013i\003C\004\0028\001!\t!!\017\002\0075\f\007/\006\004\002<\005%\023\021\t\013\005\003{\tY\005\006\003\002@\005\r\003c\001\013\002B\0211!/!\016C\002]Aq\001^A\033\001\b\t)\005E\004\017mn\t9%a\020\021\007Q\tI\005\002\004{\003k\021\ra\006\005\bA\006U\002\031AA'!\025Q!mEA$\021\035\t\t\006\001C\001\003'\nqA\0327bi6\013\007/\006\004\002V\005\r\0241\f\013\005\003/\n)\007\006\003\002Z\005u\003c\001\013\002\\\0211!/a\024C\002]Aq\001^A(\001\b\ty\006E\004\017mn\t\t'!\027\021\007Q\t\031\007\002\004{\003\037\022\ra\006\005\bA\006=\003\031AA4!\025Q!mEA5!\021\021s0!\031\t\017\0055\004\001\"\001\002p\0051a-\0337uKJ$2aGA9\021!\t\031(a\033A\002\005U\024!\0019\021\t)\0217c\020\005\b\003s\002A\021AA>\003%1\027\016\034;fe:{G\017F\002\034\003{B\001\"a\035\002x\001\007\021Q\017\005\b\003\003\003A\021AAB\003\035\031w\016\0347fGR,b!!\"\002\024\006-E\003BAD\003+#B!!#\002\016B\031A#a#\005\rI\fyH1\001\030\021\035!\030q\020a\002\003\037\003rA\004<\034\003#\013I\tE\002\025\003'#aA_A@\005\0049\002\002CAL\003\002\r!!'\002\005A4\007C\002\006\002\034N\t\t*C\002\002\036\022\021q\002U1si&\fGNR;oGRLwN\034\005\b\003C\003A\021AAR\003%\001\030M\035;ji&|g\016\006\003\002&\006-\006#\002\006\002(nY\022bAAU\t\t1A+\0369mKJB\001\"a\035\002 \002\007\021Q\017\005\b\003_\003A\021AAY\003\0359'o\\;q\005f,B!a-\002DR!\021QWAd!\035\t9,!0\002Bni!!!/\013\007\005m&!A\005j[6,H/\0312mK&!\021qXA]\005\ri\025\r\035\t\004)\005\rGaBAc\003[\023\ra\006\002\002\027\"9\001-!,A\002\005%\007#\002\006c'\005\005\007bBAg\001\021\005\021qZ\001\007M>\024\030\r\0347\025\007}\n\t\016\003\005\002t\005-\007\031AA;\021\035\t)\016\001C\001\003/\fa!\032=jgR\034HcA \002Z\"A\0211OAj\001\004\t)\bC\004\002^\002!\t!a8\002\t\031Lg\016\032\013\005\003C\f9\017\005\003\013\003G\034\022bAAs\t\t1q\n\035;j_:D\001\"a\035\002\\\002\007\021Q\017\005\b\003W\004A\021AAw\003\021\0318-\0318\026\r\005=(\021AA|)\021\t\tP!\004\025\t\005M(1\001\013\005\003k\fI\020E\002\025\003o$aA]Au\005\0049\002\002CA~\003S\004\035!!@\002\007\r\024g\rE\004\017mn\ty0!>\021\007Q\021\t\001\002\004{\003S\024\ra\037\005\t\005\013\tI\0171\001\003\b\005\021q\016\035\t\n\025\t%\021q`A\000\003L1Aa\003\005\005%1UO\\2uS>t'\007\003\005\003\020\005%\b\031AA\000\003\005Q\bb\002B\n\001\021\005!QC\001\tg\016\fg\016T3giV1!q\003B\024\005?!BA!\007\003.Q!!1\004B\025)\021\021iB!\t\021\007Q\021y\002\002\004s\005#\021\ra\006\005\bi\nE\0019\001B\022!\035qao\007B\023\005;\0012\001\006B\024\t\031Q(\021\003b\001/!A!Q\001B\t\001\004\021Y\003\005\005\013\005\023\021)c\005B\023\021!\021yA!\005A\002\t\025\002b\002B\031\001\021\005!1G\001\ng\016\fgNU5hQR,bA!\016\003F\tuB\003\002B\034\005\027\"BA!\017\003HQ!!1\bB !\r!\"Q\b\003\007e\n=\"\031A\f\t\017Q\024y\003q\001\003BA9aB^\016\003D\tm\002c\001\013\003F\0211!Pa\fC\002]A\001B!\002\0030\001\007!\021\n\t\t\025\t%1Ca\021\003D!A!q\002B\030\001\004\021\031\005\013\005\0030\t=#1\fB0!\021\021\tFa\026\016\005\tM#b\001B+\t\005Q\021M\0348pi\006$\030n\0348\n\t\te#1\013\002\n[&<'/\031;j_:\f#A!\030\002QRCW\r\t2fQ\0064\030n\034:!_\032\004\003m]2b]JKw\r\033;aA!\f7\017I2iC:<W\r\032\030!)\",\007\005\035:fm&|Wo\035\021cK\"\fg/[8sA\r\fg\016\t2fAI,\007O]8ek\016,G\rI<ji\"\0043oY1o%&<\007\016\036\030sKZ,'o]3/C\t\021\t'A\0033]er\003\007C\004\003f\001!\tAa\032\002\t!,\027\rZ\013\002'!9!1\016\001\005\002\t5\024A\0035fC\022|\005\017^5p]V\021\021\021\035\005\007\005c\002A\021I\036\002\tQ\f\027\016\034\005\b\005k\002A\021\001B4\003\021a\027m\035;\t\017\te\004\001\"\001\003n\005QA.Y:u\037B$\030n\0348\t\r\tu\004\001\"\001<\003\021Ig.\033;\t\017\t\005\005\001\"\001\003\004\006!A/Y6f)\rY\"Q\021\005\t\005\017\023y\b1\001\003\n\006\ta\016E\002\013\005\027K1A!$\005\005\rIe\016\036\005\b\005#\003A\021\001BJ\003\021!'o\0349\025\007m\021)\n\003\005\003\b\n=\005\031\001BE\021\035\021I\n\001C\001\0057\013Qa\0357jG\026$Ra\007BO\005CC\001Ba(\003\030\002\007!\021R\001\005MJ|W\016\003\005\003$\n]\005\031\001BE\003\025)h\016^5m\021!\0219\013\001Q\005\n\t%\026!D:mS\016,\027J\034;fe:\fG\016F\004\034\005W\023iKa,\t\021\t}%Q\025a\001\005\023C\001Ba)\003&\002\007!\021\022\005\b\005c\023)\0131\001P\003\005\021\007\002\003B[\001\021\005AAa.\002'Md\027nY3XSRD7J\\8x]\022+G\016^1\025\017m\021ILa/\003>\"A!q\024BZ\001\004\021I\t\003\005\003$\nM\006\031\001BE\021!\021yLa-A\002\t%\025!\0023fYR\f\007\002\003Bb\001\021\005AA!2\002'Md\027nY3XSRD7J\\8x]\n{WO\0343\025\013m\0219M!3\t\021\t}%\021\031a\001\005\023C\001Ba)\003B\002\007!\021\022\005\b\005\033\004A\021\001Bh\003%!\030m[3XQ&dW\rF\002\034\005#D\001\"a\035\003L\002\007\021Q\017\005\b\005+\004A\021\001Bl\003%!'o\0349XQ&dW\rF\002\034\0053D\001\"a\035\003T\002\007\021Q\017\005\b\005;\004A\021\001Bp\003\021\031\b/\0318\025\t\005\025&\021\035\005\t\003g\022Y\0161\001\002v!9!Q\035\001\005\002\t\035\030aB:qY&$\030\t\036\013\005\003K\023I\017\003\005\003\b\n\r\b\031\001BE\021\035\021i\017\001C\001\005_\fQ\001^1jYN,\"A!=\021\t\t\022\031pG\005\004\005k\024!\001C%uKJ\fGo\034:\t\017\te\b\001\"\001\003p\006)\021N\\5ug\"9!Q \001\005\002\t}\030aC2paf$v.\021:sCf,Ba!\001\004\020Q9Aga\001\004\022\rU\001\002CB\003\005w\004\raa\002\002\005a\034\b#\002\006\004\n\r5\021bAB\006\t\t)\021I\035:bsB\031Aca\004\005\ri\024YP1\001|\021!\031\031Ba?A\002\t%\025!B:uCJ$\b\002CB\f\005w\004\rA!#\002\0071,g\016\003\004\004\034\001!\t\001R\001\016i>$&/\031<feN\f'\r\\3\t\017\r}\001\001\"\001\004\"\005QAo\\%uKJ\fGo\034:\026\005\r\r\002\003\002\022\003tNAqaa\n\001\t\003\031I#\001\005u_N#(/Z1n+\t\031Y\003E\003\004.\ru2C\004\003\0040\reb\002BB\031\007oi!aa\r\013\007\rUb!\001\004=e>|GOP\005\002\013%\03111\b\003\002\017A\f7m[1hK&!1qHB!\005\031\031FO]3b[*\03111\b\003\t\017\r\025\003\001\"\021\004H\005\021Ao\\\013\005\007\023\032i\005\006\003\004L\r%\004#\002\013\004N\reC\001CB(\007\007\022\ra!\025\003\007\r{G.F\002\030\007'\"qa!\026\004X\t\007qCA\001`\t!\031yea\021C\002\rE#fA\n\004\\-\0221Q\f\t\005\007?\032)'\004\002\004b)!11\rB*\003%)hn\0315fG.,G-\003\003\004h\r\005$!E;oG\",7m[3e-\006\024\030.\0318dK\"A\0211`B\"\001\b\031Y\007\005\004\017mb\03121\n\005\b\007_\002A\021IB9\003!!xn\025;sS:<GCAB:!\021\031)ha\037\017\007)\0319(C\002\004z\021\ta\001\025:fI\0264\027\002BB?\007\022aa\025;sS:<'bAB=\t!911\021\001\005\002\r\025\025\001D:ue&tw\r\025:fM&DXCAB:\021\035\031I\t\001C\001\007\027\013AA^5foV\0211Q\022\n\007\007\037\033\031j!'\007\017\rE5q\021\001\004\016\naAH]3gS:,W.\0328u}A\031!b!&\n\007\r]EA\001\004B]f\024VM\032\t\006E\rm5cG\005\004\007;\023!a\004+sCZ,'o]1cY\0264\026.Z<\t\017\r%\005\001\"\001\004\"R11\021TBR\007KC\001Ba(\004 \002\007!\021\022\005\t\005G\033y\n1\001\003\n\"91\021\026\001\005\002\r-\026AC<ji\"4\025\016\034;feR\031ad!,\t\021\005M4q\025a\001\003k2aa!-\001\001\rM&AC,ji\"4\025\016\034;feN)1qVBJ=!Y\0211OBX\005\003\005\013\021BA;\021!\031Ila,\005\002\rm\026A\002\037j]&$h\b\006\003\004>\016\005\007\003BB`\007_k\021\001\001\005\t\003g\0329\f1\001\002v!A\021qGBX\t\003\031)-\006\004\004H\016U7Q\032\013\005\007\023\0349\016\006\003\004L\016=\007c\001\013\004N\0221!oa1C\002]Aq\001^Bb\001\b\031\t\016E\004\017mn\031\031na3\021\007Q\031)\016\002\004{\007\007\024\ra\006\005\bA\016\r\007\031ABm!\025Q!mEBj\021!\t\tfa,\005\002\ruWCBBp\007[\034)\017\006\003\004b\016=H\003BBr\007O\0042\001FBs\t\031\02181\034b\001/!9Aoa7A\004\r%\bc\002\bw7\r-81\035\t\004)\r5HA\002>\004\\\n\007q\003C\004a\0077\004\ra!=\021\013)\0217ca=\021\t\tz81\036\005\b9\016=F\021AB|+\021\031I\020\"\001\025\007Q\032Y\020C\004a\007k\004\ra!@\021\013)\0217ca@\021\007Q!\t\001\002\004g\007k\024\ra\006\005\t\007S\033y\013\"\001\005\006Q!1Q\030C\004\021!!I\001b\001A\002\005U\024!A9\t\017\0215\001\001\"\003\005\020\005\t\022\016^3sCR,WK\034;jY\026k\007\017^=\025\t\tEH\021\003\005\bA\022-\001\031\001C\n!\031Q!\r\"\006\005\026A!!ERB-!\021\021\003aE\016")
/*     */ public interface TraversableLike<A, Repr> extends HasNewBuilder<A, Repr>, FilterMonadic<A, Repr>, TraversableOnce<A>, GenTraversableLike<A, Repr>, Parallelizable<A, ParIterable<A>> {
/*     */   Repr repr();
/*     */   
/*     */   boolean isTraversableAgain();
/*     */   
/*     */   Traversable<A> thisCollection();
/*     */   
/*     */   Traversable<A> toCollection(Repr paramRepr);
/*     */   
/*     */   Builder<A, Repr> newBuilder();
/*     */   
/*     */   Combiner<A, ParIterable<A>> parCombiner();
/*     */   
/*     */   <U> void foreach(Function1<A, U> paramFunction1);
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   boolean hasDefiniteSize();
/*     */   
/*     */   <B, That> That $plus$plus(GenTraversableOnce<B> paramGenTraversableOnce, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That $plus$plus$colon(TraversableOnce<B> paramTraversableOnce, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That $plus$plus$colon(Traversable<B> paramTraversable, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That map(Function1<A, B> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   Repr filter(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Repr filterNot(Function1<A, Object> paramFunction1);
/*     */   
/*     */   <B, That> That collect(PartialFunction<A, B> paramPartialFunction, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   Tuple2<Repr, Repr> partition(Function1<A, Object> paramFunction1);
/*     */   
/*     */   <K> Map<K, Repr> groupBy(Function1<A, K> paramFunction1);
/*     */   
/*     */   boolean forall(Function1<A, Object> paramFunction1);
/*     */   
/*     */   boolean exists(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Option<A> find(Function1<A, Object> paramFunction1);
/*     */   
/*     */   <B, That> That scan(B paramB, Function2<B, B, B> paramFunction2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That scanLeft(B paramB, Function2<B, A, B> paramFunction2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That scanRight(B paramB, Function2<A, B, B> paramFunction2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   A head();
/*     */   
/*     */   Option<A> headOption();
/*     */   
/*     */   Repr tail();
/*     */   
/*     */   A last();
/*     */   
/*     */   Option<A> lastOption();
/*     */   
/*     */   Repr init();
/*     */   
/*     */   Repr take(int paramInt);
/*     */   
/*     */   Repr drop(int paramInt);
/*     */   
/*     */   Repr slice(int paramInt1, int paramInt2);
/*     */   
/*     */   Repr sliceWithKnownDelta(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   Repr sliceWithKnownBound(int paramInt1, int paramInt2);
/*     */   
/*     */   Repr takeWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Repr dropWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Tuple2<Repr, Repr> span(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Tuple2<Repr, Repr> splitAt(int paramInt);
/*     */   
/*     */   Iterator<Repr> tails();
/*     */   
/*     */   Iterator<Repr> inits();
/*     */   
/*     */   <B> void copyToArray(Object paramObject, int paramInt1, int paramInt2);
/*     */   
/*     */   Traversable<A> toTraversable();
/*     */   
/*     */   Iterator<A> toIterator();
/*     */   
/*     */   Stream<A> toStream();
/*     */   
/*     */   <Col> Col to(CanBuildFrom<Nothing$, A, Col> paramCanBuildFrom);
/*     */   
/*     */   String toString();
/*     */   
/*     */   String stringPrefix();
/*     */   
/*     */   Object view();
/*     */   
/*     */   TraversableView<A, Repr> view(int paramInt1, int paramInt2);
/*     */   
/*     */   FilterMonadic<A, Repr> withFilter(Function1<A, Object> paramFunction1);
/*     */   
/*     */   public class TraversableLike$$anonfun$isEmpty$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final BooleanRef result$1;
/*     */     
/*     */     public final void apply() {
/* 132 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$isEmpty$1$$anonfun$apply$mcV$sp$1(this));
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 132 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$isEmpty$1$$anonfun$apply$mcV$sp$1(this));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$isEmpty$1(TraversableLike $outer, BooleanRef result$1) {}
/*     */     
/*     */     public class TraversableLike$$anonfun$isEmpty$1$$anonfun$apply$mcV$sp$1 extends AbstractFunction1<A, Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public TraversableLike$$anonfun$isEmpty$1$$anonfun$apply$mcV$sp$1(TraversableLike$$anonfun$isEmpty$1 $outer) {}
/*     */       
/*     */       public final Nothing$ apply(Object x) {
/* 133 */         this.$outer.result$1.elem = false;
/* 134 */         return Traversable$.MODULE$.breaks().break();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$map$1 extends AbstractFunction1<A, Builder<B, That>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$1;
/*     */     
/*     */     private final Function1 f$4;
/*     */     
/*     */     public final Builder<B, That> apply(Object x) {
/* 244 */       return this.b$1.$plus$eq(this.f$4.apply(x));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$map$1(TraversableLike $outer, Builder b$1, Function1 f$4) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$flatMap$1 extends AbstractFunction1<A, Builder<B, That>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$2;
/*     */     
/*     */     private final Function1 f$5;
/*     */     
/*     */     public final Builder<B, That> apply(Object x) {
/* 251 */       return (Builder<B, That>)this.b$2.$plus$plus$eq(((GenTraversableOnce)this.f$5.apply(x)).seq());
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$flatMap$1(TraversableLike $outer, Builder b$2, Function1 f$5) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$filter$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$3;
/*     */     
/*     */     private final Function1 p$1;
/*     */     
/*     */     public TraversableLike$$anonfun$filter$1(TraversableLike $outer, Builder b$3, Function1 p$1) {}
/*     */     
/*     */     public final Object apply(Object x) {
/* 264 */       return BoxesRunTime.unboxToBoolean(this.p$1.apply(x)) ? this.b$3.$plus$eq(x) : BoxedUnit.UNIT;
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$filterNot$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 p$2;
/*     */     
/*     */     public final boolean apply(Object x$1) {
/* 274 */       return !BoxesRunTime.unboxToBoolean(this.p$2.apply(x$1));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$filterNot$1(TraversableLike $outer, Function1 p$2) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$collect$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$4;
/*     */     
/*     */     private final PartialFunction pf$1;
/*     */     
/*     */     public final Object apply(Object x) {
/* 278 */       return this.pf$1.isDefinedAt(x) ? this.b$4.$plus$eq(this.pf$1.apply(x)) : BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$collect$1(TraversableLike $outer, Builder b$4, PartialFunction pf$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$partition$1 extends AbstractFunction1<A, Builder<A, Repr>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder l$1;
/*     */     
/*     */     private final Builder r$1;
/*     */     
/*     */     private final Function1 p$3;
/*     */     
/*     */     public final Builder<A, Repr> apply(Object x) {
/* 321 */       return (BoxesRunTime.unboxToBoolean(this.p$3.apply(x)) ? this.l$1 : this.r$1).$plus$eq(x);
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$partition$1(TraversableLike $outer, Builder l$1, Builder r$1, Function1 p$3) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$groupBy$1 extends AbstractFunction1<A, Builder<A, Repr>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Map m$1;
/*     */     
/*     */     private final Function1 f$6;
/*     */     
/*     */     public TraversableLike$$anonfun$groupBy$1(TraversableLike $outer, Map m$1, Function1 f$6) {}
/*     */     
/*     */     public final Builder<A, Repr> apply(Object elem) {
/* 328 */       Object key = this.f$6.apply(elem);
/* 329 */       Builder bldr = (Builder)this.m$1.getOrElseUpdate(key, (Function0)new TraversableLike$$anonfun$groupBy$1$$anonfun$1(this));
/* 330 */       return bldr.$plus$eq(elem);
/*     */     }
/*     */     
/*     */     public class TraversableLike$$anonfun$groupBy$1$$anonfun$1 extends AbstractFunction0<Builder<A, Repr>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Builder<A, Repr> apply() {
/*     */         return this.$outer.$outer.newBuilder();
/*     */       }
/*     */       
/*     */       public TraversableLike$$anonfun$groupBy$1$$anonfun$1(TraversableLike$$anonfun$groupBy$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$groupBy$2 extends AbstractFunction1<Tuple2<K, Builder<A, Repr>>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Tuple2 check$ifrefutable$1) {
/*     */       boolean bool;
/* 333 */       if (check$ifrefutable$1 != null) {
/* 333 */         bool = true;
/*     */       } else {
/* 333 */         bool = false;
/*     */       } 
/* 333 */       return bool;
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$groupBy$2(TraversableLike $outer) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$groupBy$3 extends AbstractFunction1<Tuple2<K, Builder<A, Repr>>, Builder<Tuple2<K, Repr>, Map<K, Repr>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$5;
/*     */     
/*     */     public final Builder<Tuple2<K, Repr>, Map<K, Repr>> apply(Tuple2 x$2) {
/* 333 */       if (x$2 != null)
/* 333 */         return 
/* 334 */           this.b$5.$plus$eq(new Tuple2(x$2._1(), ((Builder)x$2._2()).result())); 
/*     */       throw new MatchError(x$2);
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$groupBy$3(TraversableLike $outer, Builder b$5) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$forall$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final BooleanRef result$2;
/*     */     
/*     */     public final Function1 p$4;
/*     */     
/*     */     public final void apply() {
/* 350 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$forall$1$$anonfun$apply$mcV$sp$2(this));
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 350 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$forall$1$$anonfun$apply$mcV$sp$2(this));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$forall$1(TraversableLike $outer, BooleanRef result$2, Function1 p$4) {}
/*     */     
/*     */     public class TraversableLike$$anonfun$forall$1$$anonfun$apply$mcV$sp$2 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public TraversableLike$$anonfun$forall$1$$anonfun$apply$mcV$sp$2(TraversableLike$$anonfun$forall$1 $outer) {}
/*     */       
/*     */       public final void apply(Object x) {
/* 351 */         if (BoxesRunTime.unboxToBoolean(this.$outer.p$4.apply(x)))
/*     */           return; 
/* 351 */         this.$outer.result$2.elem = false;
/* 351 */         throw Traversable$.MODULE$.breaks().break();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$exists$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final BooleanRef result$3;
/*     */     
/*     */     public final Function1 p$5;
/*     */     
/*     */     public final void apply() {
/* 367 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$exists$1$$anonfun$apply$mcV$sp$3(this));
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 367 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$exists$1$$anonfun$apply$mcV$sp$3(this));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$exists$1(TraversableLike $outer, BooleanRef result$3, Function1 p$5) {}
/*     */     
/*     */     public class TraversableLike$$anonfun$exists$1$$anonfun$apply$mcV$sp$3 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public TraversableLike$$anonfun$exists$1$$anonfun$apply$mcV$sp$3(TraversableLike$$anonfun$exists$1 $outer) {}
/*     */       
/*     */       public final void apply(Object x) {
/* 368 */         if (BoxesRunTime.unboxToBoolean(this.$outer.p$5.apply(x))) {
/* 368 */           this.$outer.result$3.elem = true;
/* 368 */           throw Traversable$.MODULE$.breaks().break();
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$find$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ObjectRef result$4;
/*     */     
/*     */     public final Function1 p$6;
/*     */     
/*     */     public final void apply() {
/* 385 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$find$1$$anonfun$apply$mcV$sp$4(this));
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 385 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$find$1$$anonfun$apply$mcV$sp$4(this));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$find$1(TraversableLike $outer, ObjectRef result$4, Function1 p$6) {}
/*     */     
/*     */     public class TraversableLike$$anonfun$find$1$$anonfun$apply$mcV$sp$4 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public TraversableLike$$anonfun$find$1$$anonfun$apply$mcV$sp$4(TraversableLike$$anonfun$find$1 $outer) {}
/*     */       
/*     */       public final void apply(Object x) {
/* 386 */         if (BoxesRunTime.unboxToBoolean(this.$outer.p$6.apply(x))) {
/* 386 */           this.$outer.result$4.elem = new Some(x);
/* 386 */           throw Traversable$.MODULE$.breaks().break();
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$scanLeft$1 extends AbstractFunction1<A, Builder<B, That>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$6;
/*     */     
/*     */     private final ObjectRef acc$1;
/*     */     
/*     */     private final Function2 op$1;
/*     */     
/*     */     public final Builder<B, That> apply(Object x) {
/* 398 */       this.acc$1.elem = this.op$1.apply(this.acc$1.elem, x);
/* 398 */       return this.b$6.$plus$eq(this.acc$1.elem);
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$scanLeft$1(TraversableLike $outer, Builder b$6, ObjectRef acc$1, Function2 op$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$2 extends AbstractFunction0<Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Nothing$ apply() {
/* 421 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$2(TraversableLike $outer) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$head$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ObjectRef result$5;
/*     */     
/*     */     public final void apply() {
/* 423 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$head$1$$anonfun$apply$mcV$sp$5(this));
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 423 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$head$1$$anonfun$apply$mcV$sp$5(this));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$head$1(TraversableLike $outer, ObjectRef result$5) {}
/*     */     
/*     */     public class TraversableLike$$anonfun$head$1$$anonfun$apply$mcV$sp$5 extends AbstractFunction1<A, Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public TraversableLike$$anonfun$head$1$$anonfun$apply$mcV$sp$5(TraversableLike$$anonfun$head$1 $outer) {}
/*     */       
/*     */       public final Nothing$ apply(Object x) {
/* 424 */         this.$outer.result$5.elem = new TraversableLike$$anonfun$head$1$$anonfun$apply$mcV$sp$5$$anonfun$apply$1(this, ($anonfun$head$1$$anonfun$apply$mcV$sp$5)x);
/* 425 */         return Traversable$.MODULE$.breaks().break();
/*     */       }
/*     */       
/*     */       public class TraversableLike$$anonfun$head$1$$anonfun$apply$mcV$sp$5$$anonfun$apply$1 extends AbstractFunction0<A> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final Object x$5;
/*     */         
/*     */         public final A apply() {
/*     */           return (A)this.x$5;
/*     */         }
/*     */         
/*     */         public TraversableLike$$anonfun$head$1$$anonfun$apply$mcV$sp$5$$anonfun$apply$1(TraversableLike$$anonfun$head$1$$anonfun$apply$mcV$sp$5 $outer, Object x$5) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$last$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef lst$1;
/*     */     
/*     */     public TraversableLike$$anonfun$last$1(TraversableLike $outer, ObjectRef lst$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 457 */       this.lst$1.elem = x;
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$init$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef lst$2;
/*     */     
/*     */     private final BooleanRef follow$1;
/*     */     
/*     */     private final Builder b$8;
/*     */     
/*     */     public TraversableLike$$anonfun$init$1(TraversableLike $outer, ObjectRef lst$2, BooleanRef follow$1, Builder b$8) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 482 */       this.follow$1.elem = true;
/* 482 */       this.follow$1.elem ? this.b$8.$plus$eq(this.lst$2.elem) : BoxedUnit.UNIT;
/* 483 */       this.lst$2.elem = x;
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$scala$collection$TraversableLike$$sliceInternal$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int from$1;
/*     */     
/*     */     public final int until$1;
/*     */     
/*     */     public final Builder b$9;
/*     */     
/*     */     public final IntRef i$1;
/*     */     
/*     */     public final void apply() {
/* 505 */       this.$outer.seq().foreach((Function1)new TraversableLike$$anonfun$scala$collection$TraversableLike$$sliceInternal$1$$anonfun$apply$mcV$sp$6(this));
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 505 */       this.$outer.seq().foreach((Function1)new TraversableLike$$anonfun$scala$collection$TraversableLike$$sliceInternal$1$$anonfun$apply$mcV$sp$6(this));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$scala$collection$TraversableLike$$sliceInternal$1(TraversableLike $outer, int from$1, int until$1, Builder b$9, IntRef i$1) {}
/*     */     
/*     */     public class TraversableLike$$anonfun$scala$collection$TraversableLike$$sliceInternal$1$$anonfun$apply$mcV$sp$6 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public TraversableLike$$anonfun$scala$collection$TraversableLike$$sliceInternal$1$$anonfun$apply$mcV$sp$6(TraversableLike$$anonfun$scala$collection$TraversableLike$$sliceInternal$1 $outer) {}
/*     */       
/*     */       public final void apply(Object x) {
/* 506 */         (this.$outer.i$1.elem >= this.$outer.from$1) ? this.$outer.b$9.$plus$eq(x) : BoxedUnit.UNIT;
/* 507 */         this.$outer.i$1.elem++;
/* 508 */         if (this.$outer.i$1.elem >= this.$outer.until$1)
/* 508 */           throw Traversable$.MODULE$.breaks().break(); 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$takeWhile$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Builder b$10;
/*     */     
/*     */     public final Function1 p$7;
/*     */     
/*     */     public final void apply() {
/* 535 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$takeWhile$1$$anonfun$apply$mcV$sp$7(this));
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 535 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$takeWhile$1$$anonfun$apply$mcV$sp$7(this));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$takeWhile$1(TraversableLike $outer, Builder b$10, Function1 p$7) {}
/*     */     
/*     */     public class TraversableLike$$anonfun$takeWhile$1$$anonfun$apply$mcV$sp$7 extends AbstractFunction1<A, Builder<A, Repr>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public TraversableLike$$anonfun$takeWhile$1$$anonfun$apply$mcV$sp$7(TraversableLike$$anonfun$takeWhile$1 $outer) {}
/*     */       
/*     */       public final Builder<A, Repr> apply(Object x) {
/* 536 */         if (BoxesRunTime.unboxToBoolean(this.$outer.p$7.apply(x)))
/* 537 */           return this.$outer.b$10.$plus$eq(x); 
/*     */         throw Traversable$.MODULE$.breaks().break();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$dropWhile$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$11;
/*     */     
/*     */     private final BooleanRef go$1;
/*     */     
/*     */     private final Function1 p$8;
/*     */     
/*     */     public TraversableLike$$anonfun$dropWhile$1(TraversableLike $outer, Builder b$11, BooleanRef go$1, Function1 p$8) {}
/*     */     
/*     */     public final Object apply(Object x) {
/* 547 */       if (!this.go$1.elem && !BoxesRunTime.unboxToBoolean(this.p$8.apply(x)))
/* 547 */         this.go$1.elem = true; 
/* 548 */       return this.go$1.elem ? this.b$11.$plus$eq(x) : BoxedUnit.UNIT;
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$span$1 extends AbstractFunction1<A, Builder<A, Repr>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder l$2;
/*     */     
/*     */     private final Builder r$2;
/*     */     
/*     */     private final BooleanRef toLeft$1;
/*     */     
/*     */     private final Function1 p$9;
/*     */     
/*     */     public TraversableLike$$anonfun$span$1(TraversableLike $outer, Builder l$2, Builder r$2, BooleanRef toLeft$1, Function1 p$9) {}
/*     */     
/*     */     public final Builder<A, Repr> apply(Object x) {
/* 557 */       this.toLeft$1.elem = (this.toLeft$1.elem && BoxesRunTime.unboxToBoolean(this.p$9.apply(x)));
/* 558 */       return (this.toLeft$1.elem ? this.l$2 : this.r$2).$plus$eq(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$splitAt$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder l$3;
/*     */     
/*     */     private final Builder r$3;
/*     */     
/*     */     private final IntRef i$2;
/*     */     
/*     */     private final int n$1;
/*     */     
/*     */     public TraversableLike$$anonfun$splitAt$1(TraversableLike $outer, Builder l$3, Builder r$3, IntRef i$2, int n$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 569 */       ((this.i$2.elem < this.n$1) ? this.l$3 : this.r$3).$plus$eq(x);
/* 570 */       this.i$2.elem++;
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$tails$1 extends AbstractFunction1<Traversable<A>, Traversable<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Traversable<A> apply(Traversable<A> x$3) {
/* 582 */       return x$3.tail();
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$tails$1(TraversableLike $outer) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$inits$1 extends AbstractFunction1<Traversable<A>, Traversable<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Traversable<A> apply(Traversable<A> x$4) {
/* 591 */       return x$4.init();
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$inits$1(TraversableLike $outer) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$copyToArray$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final IntRef i$3;
/*     */     
/*     */     public final int end$1;
/*     */     
/*     */     public final Object xs$1;
/*     */     
/*     */     public final void apply() {
/* 614 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$copyToArray$1$$anonfun$apply$mcV$sp$8(this));
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 614 */       this.$outer.foreach((Function1)new TraversableLike$$anonfun$copyToArray$1$$anonfun$apply$mcV$sp$8(this));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$copyToArray$1(TraversableLike $outer, IntRef i$3, int end$1, Object xs$1) {}
/*     */     
/*     */     public class TraversableLike$$anonfun$copyToArray$1$$anonfun$apply$mcV$sp$8 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public TraversableLike$$anonfun$copyToArray$1$$anonfun$apply$mcV$sp$8(TraversableLike$$anonfun$copyToArray$1 $outer) {}
/*     */       
/*     */       public final void apply(Object x) {
/* 615 */         if (this.$outer.i$3.elem >= this.$outer.end$1)
/* 615 */           throw Traversable$.MODULE$.breaks().break(); 
/* 616 */         ScalaRunTime$.MODULE$.array_update(this.$outer.xs$1, this.$outer.i$3.elem, x);
/* 617 */         this.$outer.i$3.elem++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anon$1 implements TraversableView<A, Repr> {
/*     */     private Repr underlying;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public Builder<A, TraversableView<A, Repr>> newBuilder() {
/* 660 */       return TraversableViewLike$class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public String viewIdentifier() {
/* 660 */       return TraversableViewLike$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public String viewIdString() {
/* 660 */       return TraversableViewLike$class.viewIdString(this);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/* 660 */       return TraversableViewLike$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public <B, That> That force(CanBuildFrom bf) {
/* 660 */       return (That)TraversableViewLike$class.force(this, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 660 */       return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 660 */       return (That)TraversableViewLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 660 */       return (That)TraversableViewLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 660 */       return (That)TraversableViewLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<B> flatten(Function1 asTraversable) {
/* 660 */       return TraversableViewLike$class.flatten(this, asTraversable);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<B> newForced(Function0 xs) {
/* 660 */       return TraversableViewLike$class.newForced(this, xs);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<B> newAppended(GenTraversable that) {
/* 660 */       return TraversableViewLike$class.newAppended(this, that);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<B> newMapped(Function1 f) {
/* 660 */       return TraversableViewLike$class.newMapped(this, f);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<B> newFlatMapped(Function1 f) {
/* 660 */       return TraversableViewLike$class.newFlatMapped(this, f);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A> newFiltered(Function1 p) {
/* 660 */       return TraversableViewLike$class.newFiltered(this, p);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A> newSliced(SliceInterval _endpoints) {
/* 660 */       return TraversableViewLike$class.newSliced(this, _endpoints);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A> newDroppedWhile(Function1 p) {
/* 660 */       return TraversableViewLike$class.newDroppedWhile(this, p);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A> newTakenWhile(Function1 p) {
/* 660 */       return TraversableViewLike$class.newTakenWhile(this, p);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A> newTaken(int n) {
/* 660 */       return TraversableViewLike$class.newTaken(this, n);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A> newDropped(int n) {
/* 660 */       return TraversableViewLike$class.newDropped(this, n);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> filter(Function1 p) {
/* 660 */       return TraversableViewLike$class.filter(this, p);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> withFilter(Function1 p) {
/* 660 */       return TraversableViewLike$class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<TraversableView<A, Repr>, TraversableView<A, Repr>> partition(Function1 p) {
/* 660 */       return TraversableViewLike$class.partition(this, p);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> init() {
/* 660 */       return TraversableViewLike$class.init(this);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> drop(int n) {
/* 660 */       return TraversableViewLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> take(int n) {
/* 660 */       return TraversableViewLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> slice(int from, int until) {
/* 660 */       return TraversableViewLike$class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> dropWhile(Function1 p) {
/* 660 */       return TraversableViewLike$class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> takeWhile(Function1 p) {
/* 660 */       return TraversableViewLike$class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<TraversableView<A, Repr>, TraversableView<A, Repr>> span(Function1 p) {
/* 660 */       return TraversableViewLike$class.span(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<TraversableView<A, Repr>, TraversableView<A, Repr>> splitAt(int n) {
/* 660 */       return TraversableViewLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 660 */       return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 660 */       return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <K> Map<K, TraversableView<A, Repr>> groupBy(Function1 f) {
/* 660 */       return TraversableViewLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A1>, TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A2>> unzip(Function1 asPair) {
/* 660 */       return TraversableViewLike$class.unzip(this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A1>, TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A2>, TraversableViewLike<A, Repr, TraversableView<A, Repr>>.Transformed<A3>> unzip3(Function1 asTriple) {
/* 660 */       return TraversableViewLike$class.unzip3(this, asTriple);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 660 */       return TraversableViewLike$class.toString(this);
/*     */     }
/*     */     
/*     */     public String viewToString() {
/* 660 */       return GenTraversableViewLike$class.viewToString(this);
/*     */     }
/*     */     
/*     */     public Seq<A> thisSeq() {
/* 660 */       return ViewMkString$class.thisSeq(this);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 660 */       return ViewMkString$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 660 */       return ViewMkString$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 660 */       return ViewMkString$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 660 */       return ViewMkString$class.addString(this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Traversable> companion() {
/* 660 */       return Traversable$class.companion(this);
/*     */     }
/*     */     
/*     */     public Traversable<A> seq() {
/* 660 */       return Traversable$class.seq(this);
/*     */     }
/*     */     
/*     */     public <B> Builder<B, Traversable<B>> genericBuilder() {
/* 660 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Traversable<Traversable<B>> transpose(Function1 asTraversable) {
/* 660 */       return (Traversable<Traversable<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> repr() {
/* 660 */       return (TraversableView<A, Repr>)TraversableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/* 660 */       return TraversableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public Traversable<A> thisCollection() {
/* 660 */       return TraversableLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public Traversable<A> toCollection(Object repr) {
/* 660 */       return TraversableLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParIterable<A>> parCombiner() {
/* 660 */       return TraversableLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 660 */       return TraversableLike$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 660 */       return TraversableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 660 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 660 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> filterNot(Function1 p) {
/* 660 */       return (TraversableView<A, Repr>)TraversableLike$class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 660 */       return TraversableLike$class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 660 */       return TraversableLike$class.exists(this, p);
/*     */     }
/*     */     
/*     */     public Option<A> find(Function1 p) {
/* 660 */       return TraversableLike$class.find(this, p);
/*     */     }
/*     */     
/*     */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 660 */       return (That)TraversableLike$class.scan(this, z, op, cbf);
/*     */     }
/*     */     
/*     */     public A head() {
/* 660 */       return (A)TraversableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public Option<A> headOption() {
/* 660 */       return TraversableLike$class.headOption(this);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> tail() {
/* 660 */       return (TraversableView<A, Repr>)TraversableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public A last() {
/* 660 */       return (A)TraversableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public Option<A> lastOption() {
/* 660 */       return TraversableLike$class.lastOption(this);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> sliceWithKnownDelta(int from, int until, int delta) {
/* 660 */       return (TraversableView<A, Repr>)TraversableLike$class.sliceWithKnownDelta(this, from, until, delta);
/*     */     }
/*     */     
/*     */     public TraversableView<A, Repr> sliceWithKnownBound(int from, int until) {
/* 660 */       return (TraversableView<A, Repr>)TraversableLike$class.sliceWithKnownBound(this, from, until);
/*     */     }
/*     */     
/*     */     public Iterator<TraversableView<A, Repr>> tails() {
/* 660 */       return TraversableLike$class.tails(this);
/*     */     }
/*     */     
/*     */     public Iterator<TraversableView<A, Repr>> inits() {
/* 660 */       return TraversableLike$class.inits(this);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/* 660 */       TraversableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public Traversable<A> toTraversable() {
/* 660 */       return TraversableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<A> toIterator() {
/* 660 */       return TraversableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<A> toStream() {
/* 660 */       return TraversableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 660 */       return (Col)TraversableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public Object view() {
/* 660 */       return TraversableLike$class.view(this);
/*     */     }
/*     */     
/*     */     public TraversableView<A, TraversableView<A, Repr>> view(int from, int until) {
/* 660 */       return TraversableLike$class.view(this, from, until);
/*     */     }
/*     */     
/*     */     public ParIterable<A> par() {
/* 660 */       return (ParIterable<A>)Parallelizable$class.par(this);
/*     */     }
/*     */     
/*     */     public List<A> reversed() {
/* 660 */       return TraversableOnce$class.reversed(this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 660 */       return TraversableOnce$class.size(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 660 */       return TraversableOnce$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 660 */       return TraversableOnce$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 660 */       return TraversableOnce$class.collectFirst(this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 660 */       return (B)TraversableOnce$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 660 */       return (B)TraversableOnce$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 660 */       return (B)TraversableOnce$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 660 */       return (B)TraversableOnce$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 660 */       return (B)TraversableOnce$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 660 */       return (B)TraversableOnce$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 660 */       return TraversableOnce$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 660 */       return TraversableOnce$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/* 660 */       return (A1)TraversableOnce$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 660 */       return TraversableOnce$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/* 660 */       return (A1)TraversableOnce$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 660 */       return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/* 660 */       return (B)TraversableOnce$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/* 660 */       return (B)TraversableOnce$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <B> A min(Ordering cmp) {
/* 660 */       return (A)TraversableOnce$class.min(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> A max(Ordering cmp) {
/* 660 */       return (A)TraversableOnce$class.max(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> A maxBy(Function1 f, Ordering cmp) {
/* 660 */       return (A)TraversableOnce$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> A minBy(Function1 f, Ordering cmp) {
/* 660 */       return (A)TraversableOnce$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 660 */       TraversableOnce$class.copyToBuffer(this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 660 */       TraversableOnce$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 660 */       TraversableOnce$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 660 */       return TraversableOnce$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<A> toList() {
/* 660 */       return TraversableOnce$class.toList(this);
/*     */     }
/*     */     
/*     */     public Iterable<A> toIterable() {
/* 660 */       return TraversableOnce$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public Seq<A> toSeq() {
/* 660 */       return TraversableOnce$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<A> toIndexedSeq() {
/* 660 */       return TraversableOnce$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 660 */       return TraversableOnce$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 660 */       return TraversableOnce$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Vector<A> toVector() {
/* 660 */       return TraversableOnce$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 660 */       return TraversableOnce$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 660 */       return TraversableOnce$class.addString(this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 660 */       return TraversableOnce$class.addString(this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 660 */       return (A1)GenTraversableOnce$class.$div$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public TraversableLike$$anon$1(TraversableLike $outer) {
/* 660 */       GenTraversableOnce$class.$init$(this);
/* 660 */       TraversableOnce$class.$init$(this);
/* 660 */       Parallelizable$class.$init$(this);
/* 660 */       TraversableLike$class.$init$(this);
/* 660 */       GenericTraversableTemplate.class.$init$(this);
/* 660 */       GenTraversable$class.$init$(this);
/* 660 */       Traversable$class.$init$(this);
/* 660 */       ViewMkString$class.$init$(this);
/* 660 */       GenTraversableViewLike$class.$init$(this);
/* 660 */       TraversableViewLike$class.$init$(this);
/*     */     }
/*     */     
/*     */     private Object underlying$lzycompute() {
/* 661 */       synchronized (this) {
/* 661 */         if (!this.bitmap$0) {
/* 661 */           this.underlying = (Repr)this.$outer.repr();
/* 661 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/TraversableLike$$anon$1}} */
/* 661 */         return this.underlying;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Repr underlying() {
/* 661 */       return this.bitmap$0 ? this.underlying : (Repr)underlying$lzycompute();
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 662 */       this.$outer.foreach(f);
/*     */     }
/*     */   }
/*     */   
/*     */   public class WithFilter implements FilterMonadic<A, Repr> {
/*     */     public final Function1<A, Object> scala$collection$TraversableLike$WithFilter$$p;
/*     */     
/*     */     public WithFilter(TraversableLike $outer, Function1<A, Object> p) {}
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 720 */       Builder b = bf.apply(scala$collection$TraversableLike$WithFilter$$$outer().repr());
/* 721 */       scala$collection$TraversableLike$WithFilter$$$outer().foreach((Function1)new TraversableLike$WithFilter$$anonfun$map$2(this, f, (WithFilter)b));
/* 723 */       return (That)b.result();
/*     */     }
/*     */     
/*     */     public class TraversableLike$WithFilter$$anonfun$map$2 extends AbstractFunction1<A, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$1;
/*     */       
/*     */       private final Builder b$12;
/*     */       
/*     */       public TraversableLike$WithFilter$$anonfun$map$2(TraversableLike.WithFilter $outer, Function1 f$1, Builder b$12) {}
/*     */       
/*     */       public final Object apply(Object x) {
/*     */         return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$TraversableLike$WithFilter$$p.apply(x)) ? this.b$12.$plus$eq(this.f$1.apply(x)) : BoxedUnit.UNIT;
/*     */       }
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 751 */       Builder b = bf.apply(scala$collection$TraversableLike$WithFilter$$$outer().repr());
/* 752 */       scala$collection$TraversableLike$WithFilter$$$outer().foreach((Function1)new TraversableLike$WithFilter$$anonfun$flatMap$2(this, f, (WithFilter)b));
/* 754 */       return (That)b.result();
/*     */     }
/*     */     
/*     */     public class TraversableLike$WithFilter$$anonfun$flatMap$2 extends AbstractFunction1<A, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$2;
/*     */       
/*     */       private final Builder b$13;
/*     */       
/*     */       public TraversableLike$WithFilter$$anonfun$flatMap$2(TraversableLike.WithFilter $outer, Function1 f$2, Builder b$13) {}
/*     */       
/*     */       public final Object apply(Object x) {
/*     */         return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$TraversableLike$WithFilter$$p.apply(x)) ? this.b$13.$plus$plus$eq(((GenTraversableOnce)this.f$2.apply(x)).seq()) : BoxedUnit.UNIT;
/*     */       }
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 771 */       scala$collection$TraversableLike$WithFilter$$$outer().foreach((Function1)new TraversableLike$WithFilter$$anonfun$foreach$1(this, (WithFilter)f));
/*     */     }
/*     */     
/*     */     public class TraversableLike$WithFilter$$anonfun$foreach$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$3;
/*     */       
/*     */       public TraversableLike$WithFilter$$anonfun$foreach$1(TraversableLike.WithFilter $outer, Function1 f$3) {}
/*     */       
/*     */       public final Object apply(Object x) {
/* 772 */         return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$TraversableLike$WithFilter$$p.apply(x)) ? this.f$3.apply(x) : BoxedUnit.UNIT;
/*     */       }
/*     */     }
/*     */     
/*     */     public WithFilter withFilter(Function1 q) {
/* 783 */       return new WithFilter(scala$collection$TraversableLike$WithFilter$$$outer(), (Function1<A, Object>)new TraversableLike$WithFilter$$anonfun$withFilter$1(this, (WithFilter)q));
/*     */     }
/*     */     
/*     */     public class TraversableLike$WithFilter$$anonfun$withFilter$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 q$1;
/*     */       
/*     */       public final boolean apply(Object x) {
/* 783 */         return (BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$TraversableLike$WithFilter$$p.apply(x)) && BoxesRunTime.unboxToBoolean(this.q$1.apply(x)));
/*     */       }
/*     */       
/*     */       public TraversableLike$WithFilter$$anonfun$withFilter$1(TraversableLike.WithFilter $outer, Function1 q$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$3 extends AbstractFunction1<Traversable<A>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Traversable x) {
/* 788 */       return !x.isEmpty();
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$3(TraversableLike $outer) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$iterateUntilEmpty$1 extends AbstractFunction0<Iterator<Nil$>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Iterator<Nil$> apply() {
/* 789 */       (new Nil$[1])[0] = Nil$.MODULE$;
/* 789 */       return Iterator$.MODULE$.apply((Seq<Nil$>)Predef$.MODULE$.wrapRefArray((Object[])new Nil$[1]));
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$iterateUntilEmpty$1(TraversableLike $outer) {}
/*     */   }
/*     */   
/*     */   public class TraversableLike$$anonfun$iterateUntilEmpty$2 extends AbstractFunction1<Traversable<A>, Repr> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Repr apply(Traversable x) {
/* 789 */       return (Repr)((Builder)this.$outer.newBuilder().$plus$plus$eq(x)).result();
/*     */     }
/*     */     
/*     */     public TraversableLike$$anonfun$iterateUntilEmpty$2(TraversableLike $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\TraversableLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */