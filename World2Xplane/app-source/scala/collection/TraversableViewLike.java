/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
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
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
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
/*     */ import scala.runtime.NonLocalReturnControl;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021eaaB\001\003!\003\r\ta\002\002\024)J\fg/\032:tC\ndWMV5fo2K7.\032\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001A\013\005\021MQ\003e\005\004\001\0235aR\006\r\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007c\001\b\020#5\t!!\003\002\021\005\tYAK]1wKJ\034\030M\0317f!\t\0212\003\004\001\005\rQ\001AQ1\001\026\005\005\t\025C\001\f\032!\tQq#\003\002\031\t\t9aj\034;iS:<\007C\001\006\033\023\tYBAA\002B]f\004BAD\017\022?%\021aD\001\002\020)J\fg/\032:tC\ndW\rT5lKB\021!\003\t\003\007C\001!)\031\001\022\003\tQC\027n]\t\003-\r\0222\001\n\024-\r\021)\003\001A\022\003\031q\022XMZ5oK6,g\016\036 \021\t99\023#K\005\003Q\t\021q\002\026:bm\026\0248/\0312mKZKWm\036\t\003%)\"aa\013\001\005\006\004)\"\001B\"pY2\004RA\004\001\022S}\0012A\004\030\022\023\ty#A\001\007WS\026<Xj[*ue&tw\rE\003\017cEIs$\003\0023\005\t1r)\0328Ue\0064XM]:bE2,g+[3x\031&\\W\rC\0035\001\021\005Q'\001\004%S:LG\017\n\013\002mA\021!bN\005\003q\021\021A!\0268ji\"1!\b\001Q\005Rm\n!B\\3x\005VLG\016Z3s+\005a\004\003B\037A#}i\021A\020\006\003\t\tq!\\;uC\ndW-\003\002B}\t9!)^5mI\026\024\b\"B\"\001\r#!\025AC;oI\026\024H._5oOV\t\021\006\003\004G\001\001&\tbR\001\017m&,w/\0233f]RLg-[3s+\005A\005CA%M\035\tQ!*\003\002L\t\0051\001K]3eK\032L!!\024(\003\rM#(/\0338h\025\tYE\001\003\004Q\001\001&\tbR\001\rm&,w/\0233TiJLgn\032\005\006%\002!\teU\001\rgR\024\030N\\4Qe\0264\027\016_\013\002)B\021QKW\007\002-*\021q\013W\001\005Y\006twMC\001Z\003\021Q\027M^1\n\00553\006\"\002/\001\t\003i\026!\0024pe\016,Wc\0010lAR\021qL\031\t\003%\001$Q!Y.C\002U\021A\001\0265bi\")1m\027a\002I\006\021!M\032\t\006K\"L#nX\007\002M*\021qMA\001\bO\026tWM]5d\023\tIgM\001\007DC:\024U/\0337e\rJ|W\016\005\002\023W\022)An\027b\001[\n\t!)\005\002\0223\0319q\016\001I\001\004\003\001(a\003+sC:\034hm\034:nK\022,\"!\035;\024\t9L!/\036\t\005\035\035\032\030\006\005\002\023i\0221AN\034CC\002U\0012A^<t\033\005\001\021BA82\021\025!d\016\"\0016\021\025QhN\"\001|\003\0351wN]3bG\",2\001`A\004)\t1T\020C\003s\002\007q0A\001g!\031Q\021\021A:\002\006%\031\0211\001\003\003\023\031+hn\031;j_:\f\004c\001\n\002\b\0211\021\021B=C\002U\021\021!\026\005\b\003\033qG\021IA\b\003)AW-\0313PaRLwN\\\013\003\003#\001BACA\ng&\031\021Q\003\003\003\r=\003H/[8o\021\035\tIB\034C!\003\037\t!\002\\1ti>\003H/[8o\021\025\021f\016\"\021T\021\035\tyB\034C!\003C\t\001\002^8TiJLgn\032\013\002)\032A\021Q\005\001\002\002\t\t9CA\nBEN$(/Y2u)J\fgn\0354pe6,G-\006\003\002*\005=2cBA\022\023\005-\022\021\007\t\005\035=\ti\003E\002\023\003_!q\001\\A\022\t\013\007Q\003\005\003w]\0065\002\002CA\033\003G!\t!a\016\002\rqJg.\033;?)\t\tI\004E\003w\003G\tiCB\005\002>\001\001\n1%\001\002@\tIQ)\0349usZKWm^\n\b\003wI\021\021IA\"!\r1hN\006\t\004m\006\025\023bAA\037c\031I\021\021\n\001\021\002G\005\0211\n\002\007\r>\0248-\0323\026\t\0055\0231K\n\b\003\017J\021qJA+!\0211h.!\025\021\007I\t\031\006\002\004m\003\017\022\r!\006\t\006m\006]\023\021K\005\004\003\023\nd!CA.\001A\005\031\023AA/\005\031\031F.[2fIN9\021\021L\005\002`\005\005\004c\001<o#A\031a/a\031\n\007\005m\023GB\005\002h\001\001\n1%\001\002j\t1Q*\0319qK\022,B!a\033\002rM9\021QM\005\002n\005M\004\003\002<o\003_\0022AEA9\t\031a\027Q\rb\001+A)a/!\036\002p%\031\021qM\031\007\023\005e\004\001%A\022\002\005m$A\003$mCRl\025\r\0359fIV!\021QPAB'\035\t9(CA@\003\013\003BA\0368\002\002B\031!#a!\005\r1\f9H1\001\026!\0251\030qQAA\023\r\tI(\r\004\n\003\027\003\001\023aI\001\003\033\023\001\"\0219qK:$W\rZ\013\005\003\037\013)jE\004\002\n&\t\t*a&\021\tYt\0271\023\t\004%\005UEA\0027\002\n\n\007Q\016E\003w\0033\013\031*C\002\002\fF2\021\"!(\001!\003\r\n!a(\003\021\031KG\016^3sK\022\034r!a'\n\003?\n\t\013E\002w\003GK1!!(2\r%\t9\013\001I\001$\003\tIK\001\006UC.,gn\0265jY\026\034r!!*\n\003?\nY\013E\002w\003[K1!a*2\r%\t\t\f\001I\001$\003\t\031L\001\007Ee>\004\b/\0323XQ&dWmE\004\0020&\ty&!.\021\007Y\f9,C\002\0022FBq!a/\001\t\003\ni,\001\006%a2,8\017\n9mkN,b!a0\002N\006\025G\003BAa\003\037$B!a1\002HB\031!#!2\005\r\005\fIL1\001\026\021\035\031\027\021\030a\002\003\023\004r!\0325 \003\027\f\031\rE\002\023\003\033$a\001\\A]\005\004i\007\002CAi\003s\003\r!a5\002\005a\034\b#\002\b\002V\006-\027bAAl\005\t\021r)\0328Ue\0064XM]:bE2,wJ\\2f\021\035\tY\016\001C!\003;\f1!\\1q+\031\ty.!<\002fR!\021\021]Ax)\021\t\031/a:\021\007I\t)\017\002\004b\0033\024\r!\006\005\bG\006e\0079AAu!\035)\007nHAv\003G\0042AEAw\t\031a\027\021\034b\001+!9a0!7A\002\005E\bC\002\006\002\002E\tY\017C\004\002v\002!\t%a>\002\017\r|G\016\\3diV1\021\021 B\004\003$B!a?\003\nQ!\021Q B\001!\r\021\022q \003\007C\006M(\031A\013\t\017\r\f\031\020q\001\003\004A9Q\r[\020\003\006\005u\bc\001\n\003\b\0211A.a=C\002UA\001Ba\003\002t\002\007!QB\001\003a\032\004bA\003B\b#\t\025\021b\001B\t\t\ty\001+\031:uS\006dg)\0368di&|g\016C\004\003\026\001!\tEa\006\002\017\031d\027\r^'baV1!\021\004B\024\005?!BAa\007\003*Q!!Q\004B\021!\r\021\"q\004\003\007C\nM!\031A\013\t\017\r\024\031\002q\001\003$A9Q\r[\020\003&\tu\001c\001\n\003(\0211ANa\005C\002UAqA B\n\001\004\021Y\003\005\004\013\003\003\t\"Q\006\t\006\035\005U'Q\005\005\b\005c\001A\021\tB\032\003\0351G.\031;uK:,BA!\016\003<Q!!q\007B\037!\0211hN!\017\021\007I\021Y\004\002\004m\005_\021\r!\006\005\t\005\021y\003q\001\003B\005i\021m\035+sCZ,'o]1cY\026\004bACA\001#\t\r\003#\002\b\002V\ne\002\002\003B$\001\001&YA!\023\002\r\005\034H\013[5t)\ry\"1\n\005\t\003#\024)\0051\001\002`!9!q\n\001\005\022\tE\023!\0038fo\032{'oY3e+\021\021\031F!\027\025\t\tU#1\f\t\005m:\0249\006E\002\023\0053\"a\001\034B'\005\004)\002\"CAi\005\033\"\t\031\001B/!\025Q!q\fB2\023\r\021\t\007\002\002\ty\tLh.Y7f}A)aB!\032\003X%\031!q\r\002\003\r\035+gnU3r\021\035\021Y\007\001C\t\005[\n1B\\3x\003B\004XM\0343fIV!!q\016B;)\021\021\tHa\036\021\tYt'1\017\t\004%\tUDA\0027\003j\t\007Q\016\003\005\003z\t%\004\031\001B>\003\021!\b.\031;\021\0139\021iHa\035\n\007\t}$A\001\bHK:$&/\031<feN\f'\r\\3\t\017\t\r\005\001\"\005\003\006\006Ia.Z<NCB\004X\rZ\013\005\005\017\023i\t\006\003\003\n\n=\005\003\002<o\005\027\0032A\005BG\t\031a'\021\021b\001+!9aP!!A\002\tE\005C\002\006\002\002E\021Y\tC\004\003\026\002!\tBa&\002\0339,wO\0227bi6\013\007\017]3e+\021\021IJa(\025\t\tm%\021\025\t\005m:\024i\nE\002\023\005?#a\001\034BJ\005\004)\002b\002@\003\024\002\007!1\025\t\007\025\005\005\021C!*\021\0139\t)N!(\t\017\t%\006\001\"\005\003,\006Ya.Z<GS2$XM]3e)\021\tyF!,\t\021\t=&q\025a\001\005c\013\021\001\035\t\007\025\005\005\021Ca-\021\007)\021),C\002\0038\022\021qAQ8pY\026\fg\016C\004\003<\002!\tB!0\002\0239,wo\0257jG\026$G\003BA0\005C\001B!1\003:\002\007!1Y\001\013?\026tG\r]8j]R\034\bcA3\003F&\031!q\0314\003\033Mc\027nY3J]R,'O^1m\021\035\021Y\r\001C\t\005\033\fqB\\3x\tJ|\007\017]3e/\"LG.\032\013\005\003?\022y\r\003\005\0030\n%\007\031\001BY\021\035\021\031\016\001C\t\005+\fQB\\3x)\006\\WM\\,iS2,G\003BA0\005/D\001Ba,\003R\002\007!\021\027\005\b\0057\004A\021\003Bo\003!qWm\036+bW\026tG\003BA0\005?D\001B!9\003Z\002\007!1]\001\002]B\031!B!:\n\007\t\035HAA\002J]RDqAa;\001\t#\021i/\001\006oK^$%o\0349qK\022$B!a\030\003p\"A!\021\035Bu\001\004\021\031\017C\004\003t\002!\tE!>\002\r\031LG\016^3s)\ry\"q\037\005\t\005_\023\t\0201\001\0032\"9!1 \001\005B\tu\030AC<ji\"4\025\016\034;feR\031qDa@\t\021\t=&\021 a\001\005cCqaa\001\001\t\003\032)!A\005qCJ$\030\016^5p]R!1qAB\007!\025Q1\021B\020 \023\r\031Y\001\002\002\007)V\004H.\032\032\t\021\t=6\021\001a\001\005cCqa!\005\001\t\003\032\031\"\001\003j]&$X#A\020\t\017\r]\001\001\"\021\004\032\005!AM]8q)\ry21\004\005\t\005C\034)\0021\001\003d\"91q\004\001\005B\r\005\022\001\002;bW\026$2aHB\022\021!\021\to!\bA\002\t\r\bbBB\024\001\021\0053\021F\001\006g2L7-\032\013\006?\r-2q\006\005\t\007[\031)\0031\001\003d\006!aM]8n\021!\031\td!\nA\002\t\r\030!B;oi&d\007bBB\033\001\021\0053qG\001\nIJ|\007o\0265jY\026$2aHB\035\021!\021yka\rA\002\tE\006bBB\037\001\021\0053qH\001\ni\006\\Wm\0265jY\026$2aHB!\021!\021yka\017A\002\tE\006bBB#\001\021\0053qI\001\005gB\fg\016\006\003\004\b\r%\003\002\003BX\007\007\002\rA!-\t\017\r5\003\001\"\021\004P\00591\017\0357ji\006#H\003BB\004\007#B\001B!9\004L\001\007!1\035\005\b\007+\002A\021IB,\003!\0318-\0318MK\032$XCBB-\007S\032\t\007\006\003\004\\\rUD\003BB/\007W\"Baa\030\004dA\031!c!\031\005\r\005\034\031F1\001\026\021\035\03171\013a\002\007K\002r!\0325 \007O\032y\006E\002\023\007S\"a\001\\B*\005\004)\002\002CB7\007'\002\raa\034\002\005=\004\b\003\003\006\004r\r\035\024ca\032\n\007\rMDAA\005Gk:\034G/[8oe!A1qOB*\001\004\0319'A\001{\021\035\031Y\b\001C!\007{\n\021b]2b]JKw\r\033;\026\r\r}4qRBD)\021\031\ti!&\025\t\r\r5\021\023\013\005\007\013\033I\tE\002\023\007\017#a!YB=\005\004)\002bB2\004z\001\01711\022\t\bK\"|2QRBC!\r\0212q\022\003\007Y\016e$\031A\013\t\021\r54\021\020a\001\007'\003\002BCB9#\r55Q\022\005\t\007o\032I\b1\001\004\016\"B1\021PBM\007K\033I\013\005\003\004\034\016\005VBABO\025\r\031y\nB\001\013C:tw\016^1uS>t\027\002BBR\007;\023\021\"\\5he\006$\030n\0348\"\005\r\035\026\001\033+iK\002\022W\r[1wS>\024\be\0344!AN\034\027M\034*jO\"$\b\r\t5bg\002\032\007.\0318hK\022t\003\005\0265fAA\024XM^5pkN\004#-\0325bm&|'\017I2b]\002\022W\r\t:faJ|G-^2fI\002:\030\016\0365!g\016\fgNU5hQRt#/\032<feN,g&\t\002\004,\006)!GL\035/a!91q\026\001\005B\rE\026aB4s_V\004()_\013\005\007g\033\031\r\006\003\0046\016\035\007cBB\\\007{\033\tmH\007\003\007sS1aa/\003\003%IW.\\;uC\ndW-\003\003\004@\016e&aA'baB\031!ca1\005\017\r\0257Q\026b\001+\t\t1\nC\004\007[\003\ra!3\021\r)\t\t!EBa\021\035\031i\r\001C!\007\037\fQ!\0368{SB,ba!5\004Z\016\005H\003BBj\007K\004rACB\005\007+\034i\016\005\003w]\016]\007c\001\n\004Z\022911\\Bf\005\004)\"AA!2!\0211hna8\021\007I\031\t\017B\004\004d\016-'\031A\013\003\005\005\023\004\002CBt\007\027\004\035a!;\002\r\005\034\b+Y5s!\031Q\021\021A\t\004lB9!b!\003\004X\016}\007bBBx\001\021\0053\021_\001\007k:T\030\016]\032\026\021\rM8q C\003\t\027!Ba!>\005\020AI!ba>\004|\022\005AqA\005\004\007s$!A\002+va2,7\007\005\003w]\016u\bc\001\n\004\000\022911\\Bw\005\004)\002\003\002<o\t\007\0012A\005C\003\t\035\031\031o!<C\002U\001BA\0368\005\nA\031!\003b\003\005\017\02151Q\036b\001+\t\021\021i\r\005\t\t#\031i\017q\001\005\024\005A\021m\035+sSBdW\r\005\004\013\003\003\tBQ\003\t\n\025\r]8Q C\002\t\023Aq!a\b\001\t\003\n\t\003")
/*     */ public interface TraversableViewLike<A, Coll, This extends TraversableView<A, Coll> & TraversableViewLike<A, Coll, This>> extends Traversable<A>, TraversableLike<A, This>, ViewMkString<A>, GenTraversableViewLike<A, Coll, This> {
/*     */   Builder<A, This> newBuilder();
/*     */   
/*     */   Coll underlying();
/*     */   
/*     */   String viewIdentifier();
/*     */   
/*     */   String viewIdString();
/*     */   
/*     */   String stringPrefix();
/*     */   
/*     */   <B, That> That force(CanBuildFrom<Coll, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That $plus$plus(GenTraversableOnce<B> paramGenTraversableOnce, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That map(Function1<A, B> paramFunction1, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That collect(PartialFunction<A, B> paramPartialFunction, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> paramFunction1, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B> Transformed<B> flatten(Function1<A, GenTraversableOnce<B>> paramFunction1);
/*     */   
/*     */   <B> Transformed<B> newForced(Function0<GenSeq<B>> paramFunction0);
/*     */   
/*     */   <B> Transformed<B> newAppended(GenTraversable<B> paramGenTraversable);
/*     */   
/*     */   <B> Transformed<B> newMapped(Function1<A, B> paramFunction1);
/*     */   
/*     */   <B> Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> paramFunction1);
/*     */   
/*     */   Transformed<A> newFiltered(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Transformed<A> newSliced(SliceInterval paramSliceInterval);
/*     */   
/*     */   Transformed<A> newDroppedWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Transformed<A> newTakenWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Transformed<A> newTaken(int paramInt);
/*     */   
/*     */   Transformed<A> newDropped(int paramInt);
/*     */   
/*     */   This filter(Function1<A, Object> paramFunction1);
/*     */   
/*     */   This withFilter(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Tuple2<This, This> partition(Function1<A, Object> paramFunction1);
/*     */   
/*     */   This init();
/*     */   
/*     */   This drop(int paramInt);
/*     */   
/*     */   This take(int paramInt);
/*     */   
/*     */   This slice(int paramInt1, int paramInt2);
/*     */   
/*     */   This dropWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   This takeWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Tuple2<This, This> span(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Tuple2<This, This> splitAt(int paramInt);
/*     */   
/*     */   <B, That> That scanLeft(B paramB, Function2<B, A, B> paramFunction2, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That scanRight(B paramB, Function2<A, B, B> paramFunction2, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <K> Map<K, This> groupBy(Function1<A, K> paramFunction1);
/*     */   
/*     */   <A1, A2> Tuple2<Transformed<A1>, Transformed<A2>> unzip(Function1<A, Tuple2<A1, A2>> paramFunction1);
/*     */   
/*     */   <A1, A2, A3> Tuple3<Transformed<A1>, Transformed<A2>, Transformed<A3>> unzip3(Function1<A, Tuple3<A1, A2, A3>> paramFunction1);
/*     */   
/*     */   String toString();
/*     */   
/*     */   public abstract class Transformed$class {
/*     */     public static void $init$(TraversableViewLike.Transformed $this) {}
/*     */     
/*     */     public static Option headOption(TraversableViewLike.Transformed $this) {
/*  97 */       Object object = new Object();
/*     */       try {
/*  98 */         $this.foreach((Function1)new TraversableViewLike$Transformed$$anonfun$headOption$1($this, (TraversableViewLike<A, Coll, This>.Transformed<B>)object));
/*     */       } catch (NonLocalReturnControl nonLocalReturnControl2) {
/*     */         NonLocalReturnControl nonLocalReturnControl1;
/*     */         if ((nonLocalReturnControl1 = null).key() == object)
/*     */           return (Option)nonLocalReturnControl1.value(); 
/*     */       } 
/* 101 */       return (Option)None$.MODULE$;
/*     */     }
/*     */     
/*     */     public static Option lastOption(TraversableViewLike.Transformed $this) {
/* 105 */       BooleanRef empty = new BooleanRef(true);
/* 106 */       ObjectRef result = new ObjectRef(null);
/* 107 */       $this.foreach((Function1)new TraversableViewLike$Transformed$$anonfun$lastOption$1($this, empty, (TraversableViewLike<A, Coll, This>.Transformed<B>)result));
/* 111 */       return empty.elem ? (Option)None$.MODULE$ : (Option)new Some(result.elem);
/*     */     }
/*     */     
/*     */     public static String stringPrefix(TraversableViewLike.Transformed $this) {
/* 115 */       return $this.scala$collection$TraversableViewLike$Transformed$$$outer().stringPrefix();
/*     */     }
/*     */     
/*     */     public static String toString(TraversableViewLike.Transformed $this) {
/* 116 */       return $this.viewToString();
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Transformed<B> extends TraversableView<B, Coll>, GenTraversableViewLike<A, Coll, This>.Transformed<B> {
/*     */     <U> void foreach(Function1<B, U> param1Function1);
/*     */     
/*     */     Option<B> headOption();
/*     */     
/*     */     Option<B> lastOption();
/*     */     
/*     */     String stringPrefix();
/*     */     
/*     */     String toString();
/*     */     
/*     */     public class TraversableViewLike$Transformed$$anonfun$headOption$1 extends AbstractFunction1<B, Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object nonLocalReturnKey1$1;
/*     */       
/*     */       public TraversableViewLike$Transformed$$anonfun$headOption$1(TraversableViewLike.Transformed $outer, Object nonLocalReturnKey1$1) {}
/*     */       
/*     */       public final Nothing$ apply(Object x) {
/*     */         throw new NonLocalReturnControl(this.nonLocalReturnKey1$1, new Some(x));
/*     */       }
/*     */     }
/*     */     
/*     */     public class TraversableViewLike$Transformed$$anonfun$lastOption$1 extends AbstractFunction1<B, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final BooleanRef empty$1;
/*     */       
/*     */       private final ObjectRef result$1;
/*     */       
/*     */       public TraversableViewLike$Transformed$$anonfun$lastOption$1(TraversableViewLike.Transformed $outer, BooleanRef empty$1, ObjectRef result$1) {}
/*     */       
/*     */       public final void apply(Object x) {
/*     */         this.empty$1.elem = false;
/*     */         this.result$1.elem = x;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class AbstractTransformed<B> implements Traversable<B>, Transformed<B> {
/*     */     private final Object underlying;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public Option<B> headOption() {
/* 120 */       return TraversableViewLike.Transformed$class.headOption(this);
/*     */     }
/*     */     
/*     */     public Option<B> lastOption() {
/* 120 */       return TraversableViewLike.Transformed$class.lastOption(this);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/* 120 */       return TraversableViewLike.Transformed$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 120 */       return TraversableViewLike.Transformed$class.toString(this);
/*     */     }
/*     */     
/*     */     private Object underlying$lzycompute() {
/* 120 */       synchronized (this) {
/* 120 */         if (!this.bitmap$0) {
/* 120 */           this.underlying = GenTraversableViewLike.Transformed$class.underlying(this);
/* 120 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/TraversableViewLike}.Lscala/collection/TraversableViewLike$AbstractTransformed;}} */
/* 120 */         return this.underlying;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Coll underlying() {
/* 120 */       return this.bitmap$0 ? (Coll)this.underlying : (Coll)underlying$lzycompute();
/*     */     }
/*     */     
/*     */     public final String viewIdString() {
/* 120 */       return GenTraversableViewLike.Transformed$class.viewIdString(this);
/*     */     }
/*     */     
/*     */     public Builder<B, TraversableView<B, Coll>> newBuilder() {
/* 120 */       return TraversableViewLike$class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public String viewIdentifier() {
/* 120 */       return TraversableViewLike$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public <B, That> That force(CanBuildFrom bf) {
/* 120 */       return (That)TraversableViewLike$class.force(this, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 120 */       return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 120 */       return (That)TraversableViewLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 120 */       return (That)TraversableViewLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 120 */       return (That)TraversableViewLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> flatten(Function1 asTraversable) {
/* 120 */       return TraversableViewLike$class.flatten(this, asTraversable);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> newForced(Function0 xs) {
/* 120 */       return TraversableViewLike$class.newForced(this, xs);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> newAppended(GenTraversable that) {
/* 120 */       return TraversableViewLike$class.newAppended(this, that);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> newMapped(Function1 f) {
/* 120 */       return TraversableViewLike$class.newMapped(this, f);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> newFlatMapped(Function1 f) {
/* 120 */       return TraversableViewLike$class.newFlatMapped(this, f);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> newFiltered(Function1 p) {
/* 120 */       return TraversableViewLike$class.newFiltered(this, p);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> newSliced(SliceInterval _endpoints) {
/* 120 */       return TraversableViewLike$class.newSliced(this, _endpoints);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> newDroppedWhile(Function1 p) {
/* 120 */       return TraversableViewLike$class.newDroppedWhile(this, p);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> newTakenWhile(Function1 p) {
/* 120 */       return TraversableViewLike$class.newTakenWhile(this, p);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> newTaken(int n) {
/* 120 */       return TraversableViewLike$class.newTaken(this, n);
/*     */     }
/*     */     
/*     */     public TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<B> newDropped(int n) {
/* 120 */       return TraversableViewLike$class.newDropped(this, n);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> filter(Function1 p) {
/* 120 */       return TraversableViewLike$class.filter(this, p);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> withFilter(Function1 p) {
/* 120 */       return TraversableViewLike$class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<TraversableView<B, Coll>, TraversableView<B, Coll>> partition(Function1 p) {
/* 120 */       return TraversableViewLike$class.partition(this, p);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> init() {
/* 120 */       return TraversableViewLike$class.init(this);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> drop(int n) {
/* 120 */       return TraversableViewLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> take(int n) {
/* 120 */       return TraversableViewLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> slice(int from, int until) {
/* 120 */       return TraversableViewLike$class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> dropWhile(Function1 p) {
/* 120 */       return TraversableViewLike$class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> takeWhile(Function1 p) {
/* 120 */       return TraversableViewLike$class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<TraversableView<B, Coll>, TraversableView<B, Coll>> span(Function1 p) {
/* 120 */       return TraversableViewLike$class.span(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<TraversableView<B, Coll>, TraversableView<B, Coll>> splitAt(int n) {
/* 120 */       return TraversableViewLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 120 */       return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 120 */       return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <K> Map<K, TraversableView<B, Coll>> groupBy(Function1 f) {
/* 120 */       return TraversableViewLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<A1>, TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<A2>> unzip(Function1 asPair) {
/* 120 */       return TraversableViewLike$class.unzip(this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<A1>, TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<A2>, TraversableViewLike<B, Coll, TraversableView<B, Coll>>.Transformed<A3>> unzip3(Function1 asTriple) {
/* 120 */       return TraversableViewLike$class.unzip3(this, asTriple);
/*     */     }
/*     */     
/*     */     public String viewToString() {
/* 120 */       return GenTraversableViewLike$class.viewToString(this);
/*     */     }
/*     */     
/*     */     public Seq<B> thisSeq() {
/* 120 */       return ViewMkString$class.thisSeq(this);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 120 */       return ViewMkString$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 120 */       return ViewMkString$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 120 */       return ViewMkString$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 120 */       return ViewMkString$class.addString(this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Traversable> companion() {
/* 120 */       return Traversable$class.companion(this);
/*     */     }
/*     */     
/*     */     public Traversable<B> seq() {
/* 120 */       return Traversable$class.seq(this);
/*     */     }
/*     */     
/*     */     public <B> Builder<B, Traversable<B>> genericBuilder() {
/* 120 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Traversable<Traversable<B>> transpose(Function1 asTraversable) {
/* 120 */       return (Traversable<Traversable<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> repr() {
/* 120 */       return (TraversableView<B, Coll>)TraversableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/* 120 */       return TraversableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public Traversable<B> thisCollection() {
/* 120 */       return TraversableLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public Traversable<B> toCollection(Object repr) {
/* 120 */       return TraversableLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Combiner<B, ParIterable<B>> parCombiner() {
/* 120 */       return TraversableLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 120 */       return TraversableLike$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 120 */       return TraversableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 120 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 120 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> filterNot(Function1 p) {
/* 120 */       return (TraversableView<B, Coll>)TraversableLike$class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 120 */       return TraversableLike$class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 120 */       return TraversableLike$class.exists(this, p);
/*     */     }
/*     */     
/*     */     public Option<B> find(Function1 p) {
/* 120 */       return TraversableLike$class.find(this, p);
/*     */     }
/*     */     
/*     */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 120 */       return (That)TraversableLike$class.scan(this, z, op, cbf);
/*     */     }
/*     */     
/*     */     public B head() {
/* 120 */       return (B)TraversableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> tail() {
/* 120 */       return (TraversableView<B, Coll>)TraversableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public B last() {
/* 120 */       return (B)TraversableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> sliceWithKnownDelta(int from, int until, int delta) {
/* 120 */       return (TraversableView<B, Coll>)TraversableLike$class.sliceWithKnownDelta(this, from, until, delta);
/*     */     }
/*     */     
/*     */     public TraversableView<B, Coll> sliceWithKnownBound(int from, int until) {
/* 120 */       return (TraversableView<B, Coll>)TraversableLike$class.sliceWithKnownBound(this, from, until);
/*     */     }
/*     */     
/*     */     public Iterator<TraversableView<B, Coll>> tails() {
/* 120 */       return TraversableLike$class.tails(this);
/*     */     }
/*     */     
/*     */     public Iterator<TraversableView<B, Coll>> inits() {
/* 120 */       return TraversableLike$class.inits(this);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/* 120 */       TraversableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public Traversable<B> toTraversable() {
/* 120 */       return TraversableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<B> toIterator() {
/* 120 */       return TraversableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<B> toStream() {
/* 120 */       return TraversableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 120 */       return (Col)TraversableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public Object view() {
/* 120 */       return TraversableLike$class.view(this);
/*     */     }
/*     */     
/*     */     public TraversableView<B, TraversableView<B, Coll>> view(int from, int until) {
/* 120 */       return TraversableLike$class.view(this, from, until);
/*     */     }
/*     */     
/*     */     public ParIterable<B> par() {
/* 120 */       return (ParIterable<B>)Parallelizable$class.par(this);
/*     */     }
/*     */     
/*     */     public List<B> reversed() {
/* 120 */       return TraversableOnce$class.reversed(this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 120 */       return TraversableOnce$class.size(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 120 */       return TraversableOnce$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 120 */       return TraversableOnce$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 120 */       return TraversableOnce$class.collectFirst(this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 120 */       return (B)TraversableOnce$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 120 */       return (B)TraversableOnce$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 120 */       return (B)TraversableOnce$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 120 */       return (B)TraversableOnce$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 120 */       return (B)TraversableOnce$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 120 */       return (B)TraversableOnce$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 120 */       return TraversableOnce$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 120 */       return TraversableOnce$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/* 120 */       return (A1)TraversableOnce$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 120 */       return TraversableOnce$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/* 120 */       return (A1)TraversableOnce$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 120 */       return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/* 120 */       return (B)TraversableOnce$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/* 120 */       return (B)TraversableOnce$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <B> B min(Ordering cmp) {
/* 120 */       return (B)TraversableOnce$class.min(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> B max(Ordering cmp) {
/* 120 */       return (B)TraversableOnce$class.max(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> B maxBy(Function1 f, Ordering cmp) {
/* 120 */       return (B)TraversableOnce$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> B minBy(Function1 f, Ordering cmp) {
/* 120 */       return (B)TraversableOnce$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 120 */       TraversableOnce$class.copyToBuffer(this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 120 */       TraversableOnce$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 120 */       TraversableOnce$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 120 */       return TraversableOnce$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<B> toList() {
/* 120 */       return TraversableOnce$class.toList(this);
/*     */     }
/*     */     
/*     */     public Iterable<B> toIterable() {
/* 120 */       return TraversableOnce$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public Seq<B> toSeq() {
/* 120 */       return TraversableOnce$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> toIndexedSeq() {
/* 120 */       return TraversableOnce$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 120 */       return TraversableOnce$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 120 */       return TraversableOnce$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Vector<B> toVector() {
/* 120 */       return TraversableOnce$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 120 */       return TraversableOnce$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 120 */       return TraversableOnce$class.addString(this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 120 */       return TraversableOnce$class.addString(this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 120 */       return (A1)GenTraversableOnce$class.$div$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public AbstractTransformed(TraversableViewLike $outer) {
/* 120 */       GenTraversableOnce$class.$init$(this);
/* 120 */       TraversableOnce$class.$init$(this);
/* 120 */       Parallelizable$class.$init$(this);
/* 120 */       TraversableLike$class.$init$(this);
/* 120 */       GenericTraversableTemplate.class.$init$(this);
/* 120 */       GenTraversable$class.$init$(this);
/* 120 */       Traversable$class.$init$(this);
/* 120 */       ViewMkString$class.$init$(this);
/* 120 */       GenTraversableViewLike$class.$init$(this);
/* 120 */       TraversableViewLike$class.$init$(this);
/* 120 */       GenTraversableViewLike.Transformed$class.$init$(this);
/* 120 */       TraversableViewLike.Transformed$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anonfun$collect$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction pf$1;
/*     */     
/*     */     public final boolean apply(Object x) {
/* 157 */       return this.pf$1.isDefinedAt(x);
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anonfun$collect$1(TraversableViewLike $outer, PartialFunction pf$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anon$1 extends AbstractTransformed<B> implements Forced<B> {
/*     */     private final GenSeq<B> forced;
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 172 */       GenTraversableViewLike.Forced$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/* 172 */       return GenTraversableViewLike.Forced$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public GenSeq<B> forced() {
/* 172 */       return this.forced;
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anon$1(TraversableViewLike<A, Coll, This> $outer, Function0 xs$2) {
/* 172 */       super($outer);
/* 172 */       GenTraversableViewLike.Forced$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anon$2 extends AbstractTransformed<B> implements Appended<B> {
/*     */     private final GenTraversable<B> rest;
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 173 */       GenTraversableViewLike.Appended$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/* 173 */       return GenTraversableViewLike.Appended$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public GenTraversable<B> rest() {
/* 173 */       return this.rest;
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anon$2(TraversableViewLike<A, Coll, This> $outer, GenTraversable<B> that$1) {
/* 173 */       super($outer);
/* 173 */       GenTraversableViewLike.Appended$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anon$3 extends AbstractTransformed<B> implements Mapped<B> {
/*     */     private final Function1<A, B> mapping;
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 174 */       GenTraversableViewLike.Mapped$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/* 174 */       return GenTraversableViewLike.Mapped$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, B> mapping() {
/* 174 */       return this.mapping;
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anon$3(TraversableViewLike<A, Coll, This> $outer, Function1<A, B> f$1) {
/* 174 */       super($outer);
/* 174 */       GenTraversableViewLike.Mapped$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anon$4 extends AbstractTransformed<B> implements FlatMapped<B> {
/*     */     private final Function1<A, GenTraversableOnce<B>> mapping;
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 175 */       GenTraversableViewLike.FlatMapped$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/* 175 */       return GenTraversableViewLike.FlatMapped$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, GenTraversableOnce<B>> mapping() {
/* 175 */       return this.mapping;
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anon$4(TraversableViewLike<A, Coll, This> $outer, Function1<A, GenTraversableOnce<B>> f$2) {
/* 175 */       super($outer);
/* 175 */       GenTraversableViewLike.FlatMapped$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anon$5 extends AbstractTransformed<A> implements Filtered {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 176 */       GenTraversableViewLike.Filtered$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/* 176 */       return GenTraversableViewLike.Filtered$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/* 176 */       return this.pred;
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anon$5(TraversableViewLike<A, Coll, This> $outer, Function1<A, Object> p$1) {
/* 176 */       super($outer);
/* 176 */       GenTraversableViewLike.Filtered$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anon$6 extends AbstractTransformed<A> implements Sliced {
/*     */     private final SliceInterval endpoints;
/*     */     
/*     */     public int from() {
/* 177 */       return GenTraversableViewLike.Sliced$class.from(this);
/*     */     }
/*     */     
/*     */     public int until() {
/* 177 */       return GenTraversableViewLike.Sliced$class.until(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 177 */       GenTraversableViewLike.Sliced$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/* 177 */       return GenTraversableViewLike.Sliced$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public SliceInterval endpoints() {
/* 177 */       return this.endpoints;
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anon$6(TraversableViewLike<A, Coll, This> $outer, SliceInterval _endpoints$1) {
/* 177 */       super($outer);
/* 177 */       GenTraversableViewLike.Sliced$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anon$7 extends AbstractTransformed<A> implements DroppedWhile {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 178 */       GenTraversableViewLike.DroppedWhile$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/* 178 */       return GenTraversableViewLike.DroppedWhile$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/* 178 */       return this.pred;
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anon$7(TraversableViewLike<A, Coll, This> $outer, Function1<A, Object> p$2) {
/* 178 */       super($outer);
/* 178 */       GenTraversableViewLike.DroppedWhile$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anon$8 extends AbstractTransformed<A> implements TakenWhile {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 179 */       GenTraversableViewLike.TakenWhile$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/* 179 */       return GenTraversableViewLike.TakenWhile$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/* 179 */       return this.pred;
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anon$8(TraversableViewLike<A, Coll, This> $outer, Function1<A, Object> p$3) {
/* 179 */       super($outer);
/* 179 */       GenTraversableViewLike.TakenWhile$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anonfun$partition$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 p$4;
/*     */     
/*     */     public final boolean apply(Object x$1) {
/* 186 */       return !BoxesRunTime.unboxToBoolean(this.p$4.apply(x$1));
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anonfun$partition$1(TraversableViewLike $outer, Function1 p$4) {}
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anonfun$scanLeft$1 extends AbstractFunction0<Seq<B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object z$1;
/*     */     
/*     */     private final Function2 op$1;
/*     */     
/*     */     public final Seq<B> apply() {
/* 197 */       return (Seq<B>)this.$outer.thisSeq().scanLeft(this.z$1, this.op$1, Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anonfun$scanLeft$1(TraversableViewLike $outer, Object z$1, Function2 op$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anonfun$scanRight$1 extends AbstractFunction0<Seq<B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object z$2;
/*     */     
/*     */     private final Function2 op$2;
/*     */     
/*     */     public final Seq<B> apply() {
/* 201 */       return (Seq<B>)this.$outer.thisSeq().scanRight(this.z$2, this.op$2, Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anonfun$scanRight$1(TraversableViewLike $outer, Object z$2, Function2 op$2) {}
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anonfun$groupBy$1 extends AbstractFunction1<Seq<A>, This> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final This apply(Seq xs) {
/* 204 */       return (This)TraversableViewLike$class.scala$collection$TraversableViewLike$$asThis(this.$outer, this.$outer.newForced((Function0)new TraversableViewLike$$anonfun$groupBy$1$$anonfun$apply$1(this, ($anonfun$groupBy$1)xs)));
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anonfun$groupBy$1(TraversableViewLike $outer) {}
/*     */     
/*     */     public class TraversableViewLike$$anonfun$groupBy$1$$anonfun$apply$1 extends AbstractFunction0<Seq<A>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Seq xs$1;
/*     */       
/*     */       public final Seq<A> apply() {
/* 204 */         return this.xs$1;
/*     */       }
/*     */       
/*     */       public TraversableViewLike$$anonfun$groupBy$1$$anonfun$apply$1(TraversableViewLike$$anonfun$groupBy$1 $outer, Seq xs$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anonfun$unzip$1 extends AbstractFunction1<A, A1> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 asPair$1;
/*     */     
/*     */     public final A1 apply(Object x) {
/* 207 */       return (A1)((Tuple2)this.asPair$1.apply(x))._1();
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anonfun$unzip$1(TraversableViewLike $outer, Function1 asPair$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anonfun$unzip$2 extends AbstractFunction1<A, A2> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 asPair$1;
/*     */     
/*     */     public final A2 apply(Object x) {
/* 207 */       return (A2)((Tuple2)this.asPair$1.apply(x))._2();
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anonfun$unzip$2(TraversableViewLike $outer, Function1 asPair$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anonfun$unzip3$1 extends AbstractFunction1<A, A1> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 asTriple$1;
/*     */     
/*     */     public final A1 apply(Object x) {
/* 210 */       return (A1)((Tuple3)this.asTriple$1.apply(x))._1();
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anonfun$unzip3$1(TraversableViewLike $outer, Function1 asTriple$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anonfun$unzip3$2 extends AbstractFunction1<A, A2> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 asTriple$1;
/*     */     
/*     */     public final A2 apply(Object x) {
/* 210 */       return (A2)((Tuple3)this.asTriple$1.apply(x))._2();
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anonfun$unzip3$2(TraversableViewLike $outer, Function1 asTriple$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableViewLike$$anonfun$unzip3$3 extends AbstractFunction1<A, A3> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 asTriple$1;
/*     */     
/*     */     public final A3 apply(Object x) {
/* 210 */       return (A3)((Tuple3)this.asTriple$1.apply(x))._3();
/*     */     }
/*     */     
/*     */     public TraversableViewLike$$anonfun$unzip3$3(TraversableViewLike $outer, Function1 asTriple$1) {}
/*     */   }
/*     */   
/*     */   public interface Forced<B> extends Transformed<B>, GenTraversableViewLike<A, Coll, This>.Forced<B> {}
/*     */   
/*     */   public interface Sliced extends Transformed<A>, GenTraversableViewLike<A, Coll, This>.Sliced {}
/*     */   
/*     */   public interface Mapped<B> extends Transformed<B>, GenTraversableViewLike<A, Coll, This>.Mapped<B> {}
/*     */   
/*     */   public interface Appended<B> extends Transformed<B>, GenTraversableViewLike<A, Coll, This>.Appended<B> {}
/*     */   
/*     */   public interface Filtered extends Transformed<A>, GenTraversableViewLike<A, Coll, This>.Filtered {}
/*     */   
/*     */   public interface EmptyView extends Transformed<Nothing$>, GenTraversableViewLike<A, Coll, This>.EmptyView {}
/*     */   
/*     */   public interface FlatMapped<B> extends Transformed<B>, GenTraversableViewLike<A, Coll, This>.FlatMapped<B> {}
/*     */   
/*     */   public interface TakenWhile extends Transformed<A>, GenTraversableViewLike<A, Coll, This>.TakenWhile {}
/*     */   
/*     */   public interface DroppedWhile extends Transformed<A>, GenTraversableViewLike<A, Coll, This>.DroppedWhile {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\TraversableViewLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */