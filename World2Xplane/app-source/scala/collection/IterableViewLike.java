/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
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
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r\rcaB\001\003!\003\r\ta\002\002\021\023R,'/\0312mKZKWm\036'jW\026T!a\001\003\002\025\r|G\016\\3di&|gNC\001\006\003\025\0318-\0317b\007\001)B\001C\n+AM9\001!C\007\035[A\032\004C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB\031abD\t\016\003\tI!\001\005\002\003\021%#XM]1cY\026\004\"AE\n\r\001\0211A\003\001CC\002U\021\021!Q\t\003-e\001\"AC\f\n\005a!!a\002(pi\"Lgn\032\t\003\025iI!a\007\003\003\007\005s\027\020\005\003\017;Ey\022B\001\020\003\0051IE/\032:bE2,G*[6f!\t\021\002\005\002\004\"\001\021\025\rA\t\002\005)\"L7/\005\002\027GI\031AE\n\027\007\t\025\002\001a\t\002\ryI,g-\0338f[\026tGO\020\t\005\035\035\n\022&\003\002)\005\ta\021\n^3sC\ndWMV5foB\021!C\013\003\007W\001!)\031A\013\003\t\r{G\016\034\t\006\035\001\t\022f\b\t\005\0359\n\022&\003\0020\005\tyAK]1wKJ\034\030M\0317f-&,w\017E\003\017cEIs$\003\0023\005\t\031BK]1wKJ\034\030M\0317f-&,w\017T5lKB)a\002N\t*?%\021QG\001\002\024\017\026t\027\n^3sC\ndWMV5fo2K7.\032\005\006o\001!\t\001O\001\007I%t\027\016\036\023\025\003e\002\"A\003\036\n\005m\"!\001B+oSR4q!\020\001\021\002\007\005aHA\006Ue\006t7OZ8s[\026$WCA C'\025a\024\002\021#H!\021qq%Q\025\021\005I\021EAB\"=\t\013\007QCA\001C!\r)e)Q\007\002\001%\021Q(\r\t\004\013\"\013\025BA\0375\021\0259D\b\"\0019\021\025YEH\"\001M\003!IG/\032:bi>\024X#A'\021\0079q\025)\003\002P\005\tA\021\n^3sCR|'\017C\003Ry\021\005#+A\004g_J,\027m\0315\026\005MSFCA\035U\021\025)\006\0131\001W\003\0051\007\003\002\006X\003fK!\001\027\003\003\023\031+hn\031;j_:\f\004C\001\n[\t\025Y\006K1\001\026\005\005)\006\"B/=\t\003r\026\001\003;p'R\024\030N\\4\025\003}\003\"\001Y3\016\003\005T!AY2\002\t1\fgn\032\006\002I\006!!.\031<b\023\t1\027M\001\004TiJLgn\032\004\007Q\002\t\tAA5\003'\005\0237\017\036:bGR$&/\0318tM>\024X.\0323\026\005)l7#B4\nW:|\007c\001\b\020YB\021!#\034\003\007\007\036$)\031A\013\021\007\0253E\016E\002Fy1DQ!]4\005\002I\fa\001P5oSRtD#A:\021\007\025;GNB\004v\001A\005\031\023\001<\003\023\025k\007\017^=WS\026<8#\002;\nobT\bcA#=-A\021Q)_\005\003kF\002\"!R>\n\005U$daB?\001!\003\r\nA \002\007\r>\0248-\0323\026\007}\f9a\005\005}\023\005\005\021\021BA\007!\025)\0251AA\003\023\ti\030\007E\002\023\003\017!Qa\021?C\002U\001R!RA\006\003\013I!! \033\021\t\025c\024Q\001\004\n\003#\001\001\023aI\001\003'\021aa\0257jG\026$7#CA\b\023\005U\021\021DA\017!\r)\025qC\005\004\003#\t\004cA#\002\034%\031\021\021\003\033\021\007\025c\024CB\005\002\"\001\001\n1%\001\002$\t1Q*\0319qK\022,B!!\n\002.MI\021qD\005\002(\005=\0221\007\t\006\013\006%\0221F\005\004\003C\t\004c\001\n\002.\02111)a\bC\002U\001R!RA\031\003WI1!!\t5!\021)E(a\013\007\023\005]\002\001%A\022\002\005e\"A\003$mCRl\025\r\0359fIV!\0211HA\"'%\t)$CA\037\003\013\nI\005E\003F\003\t\t%C\002\0028E\0022AEA\"\t\031\031\025Q\007b\001+A)Q)a\022\002B%\031\021q\007\033\021\t\025c\024\021\t\004\n\003\033\002\001\023aI\001\003\037\022\001\"\0219qK:$W\rZ\013\005\003#\nIfE\005\002L%\t\031&!\030\002bA)Q)!\026\002X%\031\021QJ\031\021\007I\tI\006B\004D\003\027\022\r!a\027\022\005EI\002#B#\002`\005]\023bAA'iA!Q\tPA,\r%\t)\007\001I\001$\003\t9G\001\005GS2$XM]3e'%\t\031'CA5\003[\ni\002E\002F\003WJ1!!\0322!\r)\025qN\005\004\003K\"d!CA:\001A\005\031\023AA;\005)!\026m[3o/\"LG.Z\n\n\003cJ\021qOA>\003;\0012!RA=\023\r\t\031(\r\t\004\013\006u\024bAA:i\031I\021\021\021\001\021\002G\005\0211\021\002\r\tJ|\007\017]3e/\"LG.Z\n\n\003J\021QQAE\003;\0012!RAD\023\r\t\t)\r\t\004\013\006-\025bAAAi\031I\021q\022\001\021\002G\005\021\021\023\002\0075&\004\b/\0323\026\t\005M\025qT\n\b\003\033K\021QSAQ!\021)E(a&\021\r)\tI*EAO\023\r\tY\n\002\002\007)V\004H.\032\032\021\007I\ty\n\002\004D\003\033\023\r!\006\t\006\013\006\r\026QT\005\004\003\037#d!CAT\001A\005\031\023AAU\005%Q\026\016\0359fI\006cG.\006\004\002,\006M\026\021X\n\b\003KK\021QVA^!\021)E(a,\021\017)\tI*!-\0028B\031!#a-\005\021\005U\026Q\025b\001\0037\022!!Q\031\021\007I\tI\f\002\004D\003K\023\r!\006\t\b\013\006u\026\021WA\\\023\r\t9\013\016\005\t\003\003\004\001\025b\003\002D\0061\021m\035+iSN$2aHAc\021!\t9-a0A\002\005u\021A\001=t\021\035\tY\r\001C\t\003\033\f\021B\\3x5&\004\b/\0323\026\t\005=\027q\033\013\005\003#\fI\016\005\003Fy\005M\007C\002\006\002\032F\t)\016E\002\023\003/$aaQAe\005\004)\002\002CAn\003\023\004\r!!8\002\tQD\027\r\036\t\006\035\005}\027Q[\005\004\003C\024!aC$f]&#XM]1cY\026Dq!!:\001\t#\t9/\001\007oK^T\026\016\0359fI\006cG.\006\004\002j\006E\030Q\037\013\t\003W\f90a?\002\000B!Q\tPAw!\035Q\021\021TAx\003g\0042AEAy\t!\t),a9C\002\005m\003c\001\n\002v\02211)a9C\002UA\001\"a7\002d\002\007\021\021 \t\006\035\005}\0271\037\005\t\003{\f\031\0171\001\002p\006Iq\f\0365jg\026cW-\034\005\t\005\003\t\031\0171\001\002t\006Iq\f\0365bi\026cW-\034\005\b\005\013\001A\021\013B\004\003%qWm\036$pe\016,G-\006\003\003\n\t=A\003\002B\006\005#\001B!\022\037\003\016A\031!Ca\004\005\r\r\023\031A1\001\026\021%\t9Ma\001\005\002\004\021\031\002E\003\013\005+\021I\"C\002\003\030\021\021\001\002\0202z]\006lWM\020\t\006\035\tm!QB\005\004\005;\021!AB$f]N+\027\017C\004\003\"\001!\tFa\t\002\0279,w/\0219qK:$W\rZ\013\005\005K\021Y\003\006\003\003(\t5\002\003B#=\005S\0012A\005B\026\t\035\031%q\004b\001\0037B\001\"a7\003 \001\007!q\006\t\006\035\tE\"\021F\005\004\005g\021!AD$f]R\023\030M^3sg\006\024G.\032\005\b\005o\001A\021\013B\035\003%qWm^'baB,G-\006\003\003<\t\005C\003\002B\037\005\007\002B!\022\037\003@A\031!C!\021\005\r\r\023)D1\001\026\021\035)&Q\007a\001\005\013\002RAC,\022\005AqA!\023\001\t#\022Y%A\007oK^4E.\031;NCB\004X\rZ\013\005\005\033\022\031\006\006\003\003P\tU\003\003B#=\005#\0022A\005B*\t\031\031%q\tb\001+!9QKa\022A\002\t]\003#\002\006X#\te\003#\002\b\003\\\tE\023b\001B/\005\t\021r)\0328Ue\0064XM]:bE2,wJ\\2f\021\035\021\t\007\001C)\005G\n1B\\3x\r&dG/\032:fIR!\021Q\004B3\021!\0219Ga\030A\002\t%\024!\0019\021\013)9\026Ca\033\021\007)\021i'C\002\003p\021\021qAQ8pY\026\fg\016C\004\003t\001!\tF!\036\002\0239,wo\0257jG\026$G\003BA\017\005oB\001B!\037\003r\001\007!1P\001\013?\026tG\r]8j]R\034\b\003\002B?\005\007k!Aa \013\007\t\005%!A\004hK:,'/[2\n\t\t\025%q\020\002\016'2L7-Z%oi\026\024h/\0317\t\017\t%\005\001\"\025\003\f\006ya.Z<Ee>\004\b/\0323XQ&dW\r\006\003\002\036\t5\005\002\003B4\005\017\003\rA!\033\t\017\tE\005\001\"\025\003\024\006ia.Z<UC.,gn\0265jY\026$B!!\b\003\026\"A!q\rBH\001\004\021I\007C\004\003\032\002!\tFa'\002\0219,w\017V1lK:$B!!\b\003\036\"A!q\024BL\001\004\021\t+A\001o!\rQ!1U\005\004\005K#!aA%oi\"9!\021\026\001\005R\t-\026A\0038fo\022\023x\016\0359fIR!\021Q\004BW\021!\021yJa*A\002\t\005\006b\002BY\001\021\005#1W\001\005IJ|\007\017F\002 \005kC\001Ba(\0030\002\007!\021\025\005\b\005s\003A\021\tB^\003\021!\030m[3\025\007}\021i\f\003\005\003 \n]\006\031\001BQ\021\035\021\t\r\001C!\005\007\f1A_5q+!\021)M!8\003b\n-G\003\002Bd\005G$BA!3\003PB\031!Ca3\005\017\t5'q\030b\001+\t!A\013[1u\021!\021\tNa0A\004\tM\027A\0012g!%\021iH!6 \0053\024I-\003\003\003X\n}$\001D\"b]\n+\030\016\0343Ge>l\007c\002\006\002\032\nm'q\034\t\004%\tuG\001CA[\005\023\r!a\027\021\007I\021\t\017\002\004D\005\023\r!\006\005\t\0037\024y\f1\001\003fB)a\"a8\003`\"9!\021\036\001\005B\t-\030\001\004>ja^KG\017[%oI\026DXC\002Bw\005w\024\t\020\006\003\003p\nM\bc\001\n\003r\0229!Q\032Bt\005\004)\002\002\003Bi\005O\004\035A!>\021\023\tu$Q[\020\003x\n=\bc\002\006\002\032\ne(\021\025\t\004%\tmH\001CA[\005O\024\r!a\027\t\017\t}\b\001\"\021\004\002\0051!0\0339BY2,\002ba\001\004\030\rM1\021\002\013\t\007\013\031Ib!\b\004\"Q!1qAB\006!\r\0212\021\002\003\b\005\033\024iP1\001\026\021!\021\tN!@A\004\r5\001#\003B?\005+|2qBB\004!\035Q\021\021TB\t\007+\0012AEB\n\t!\t)L!@C\002\005m\003c\001\n\004\030\02111I!@C\002UA\001\"a7\003~\002\00711\004\t\006\035\005}7Q\003\005\t\007?\021i\0201\001\004\022\005AA\017[5t\0132,W\016\003\005\004$\tu\b\031AB\013\003!!\b.\031;FY\026l\007bBB\024\001\021\0053\021F\001\bOJ|W\017]3e)\021\031Yc!\f\021\0079qu\004\003\005\0040\r\025\002\031\001BQ\003\021\031\030N_3\t\017\rM\002\001\"\021\0046\00591\017\\5eS:<GCBB\026\007o\031I\004\003\005\0040\rE\002\031\001BQ\021!\031Yd!\rA\002\t\005\026\001B:uKBDqaa\020\001\t\003\032\t%\001\007tiJLgn\032)sK\032L\0070F\001`\001")
/*     */ public interface IterableViewLike<A, Coll, This extends IterableView<A, Coll> & IterableViewLike<A, Coll, This>> extends Iterable<A>, IterableLike<A, This>, TraversableView<A, Coll>, TraversableViewLike<A, Coll, This>, GenIterableViewLike<A, Coll, This> {
/*     */   <B> Transformed<Tuple2<A, B>> newZipped(GenIterable<B> paramGenIterable);
/*     */   
/*     */   <A1, B> Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> paramGenIterable, A1 paramA1, B paramB);
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
/*     */   This drop(int paramInt);
/*     */   
/*     */   This take(int paramInt);
/*     */   
/*     */   <A1, B, That> That zip(GenIterable<B> paramGenIterable, CanBuildFrom<This, Tuple2<A1, B>, That> paramCanBuildFrom);
/*     */   
/*     */   <A1, That> That zipWithIndex(CanBuildFrom<This, Tuple2<A1, Object>, That> paramCanBuildFrom);
/*     */   
/*     */   <B, A1, That> That zipAll(GenIterable<B> paramGenIterable, A1 paramA1, B paramB, CanBuildFrom<This, Tuple2<A1, B>, That> paramCanBuildFrom);
/*     */   
/*     */   Iterator<This> grouped(int paramInt);
/*     */   
/*     */   Iterator<This> sliding(int paramInt1, int paramInt2);
/*     */   
/*     */   String stringPrefix();
/*     */   
/*     */   public abstract class Transformed$class {
/*     */     public static void $init$(IterableViewLike.Transformed $this) {}
/*     */     
/*     */     public static void foreach(IterableViewLike.Transformed $this, Function1 f) {
/*  42 */       $this.iterator().foreach(f);
/*     */     }
/*     */     
/*     */     public static String toString(IterableViewLike.Transformed $this) {
/*  43 */       return $this.viewToString();
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class AbstractTransformed<B> implements Iterable<B>, TraversableViewLike<A, Coll, This>.Transformed<B>, Transformed<B> {
/*     */     private final Object underlying;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  47 */       IterableViewLike.Transformed$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  47 */       return IterableViewLike.Transformed$class.toString(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  47 */       return GenIterableViewLike.Transformed$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public <B> IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<Tuple2<B, B>> newZipped(GenIterable that) {
/*  47 */       return IterableViewLike$class.newZipped(this, that);
/*     */     }
/*     */     
/*     */     public <A1, B> IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable that, Object _thisElem, Object _thatElem) {
/*  47 */       return IterableViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
/*     */     }
/*     */     
/*     */     public <B> IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> newForced(Function0 xs) {
/*  47 */       return IterableViewLike$class.newForced(this, xs);
/*     */     }
/*     */     
/*     */     public <B> IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> newAppended(GenTraversable that) {
/*  47 */       return IterableViewLike$class.newAppended(this, that);
/*     */     }
/*     */     
/*     */     public <B> IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> newMapped(Function1 f) {
/*  47 */       return IterableViewLike$class.newMapped(this, f);
/*     */     }
/*     */     
/*     */     public <B> IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> newFlatMapped(Function1 f) {
/*  47 */       return IterableViewLike$class.newFlatMapped(this, f);
/*     */     }
/*     */     
/*     */     public IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> newFiltered(Function1 p) {
/*  47 */       return IterableViewLike$class.newFiltered(this, p);
/*     */     }
/*     */     
/*     */     public IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> newSliced(SliceInterval _endpoints) {
/*  47 */       return IterableViewLike$class.newSliced(this, _endpoints);
/*     */     }
/*     */     
/*     */     public IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> newDroppedWhile(Function1 p) {
/*  47 */       return IterableViewLike$class.newDroppedWhile(this, p);
/*     */     }
/*     */     
/*     */     public IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> newTakenWhile(Function1 p) {
/*  47 */       return IterableViewLike$class.newTakenWhile(this, p);
/*     */     }
/*     */     
/*     */     public IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> newTaken(int n) {
/*  47 */       return IterableViewLike$class.newTaken(this, n);
/*     */     }
/*     */     
/*     */     public IterableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> newDropped(int n) {
/*  47 */       return IterableViewLike$class.newDropped(this, n);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> drop(int n) {
/*  47 */       return IterableViewLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> take(int n) {
/*  47 */       return IterableViewLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  47 */       return (That)IterableViewLike$class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  47 */       return (That)IterableViewLike$class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  47 */       return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public Iterator<IterableView<B, Coll>> grouped(int size) {
/*  47 */       return IterableViewLike$class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public Iterator<IterableView<B, Coll>> sliding(int size, int step) {
/*  47 */       return IterableViewLike$class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/*  47 */       return IterableViewLike$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public Option<B> headOption() {
/*  47 */       return TraversableViewLike.Transformed$class.headOption(this);
/*     */     }
/*     */     
/*     */     public Option<B> lastOption() {
/*  47 */       return TraversableViewLike.Transformed$class.lastOption(this);
/*     */     }
/*     */     
/*     */     private Object underlying$lzycompute() {
/*  47 */       synchronized (this) {
/*  47 */         if (!this.bitmap$0) {
/*  47 */           this.underlying = GenTraversableViewLike.Transformed$class.underlying(this);
/*  47 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/IterableViewLike}.Lscala/collection/IterableViewLike$AbstractTransformed;}} */
/*  47 */         return this.underlying;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Coll underlying() {
/*  47 */       return this.bitmap$0 ? (Coll)this.underlying : (Coll)underlying$lzycompute();
/*     */     }
/*     */     
/*     */     public final String viewIdString() {
/*  47 */       return GenTraversableViewLike.Transformed$class.viewIdString(this);
/*     */     }
/*     */     
/*     */     public Builder<B, IterableView<B, Coll>> newBuilder() {
/*  47 */       return TraversableViewLike$class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public String viewIdentifier() {
/*  47 */       return TraversableViewLike$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public <B, That> That force(CanBuildFrom bf) {
/*  47 */       return (That)TraversableViewLike$class.force(this, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/*  47 */       return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  47 */       return (That)TraversableViewLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  47 */       return (That)TraversableViewLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  47 */       return (That)TraversableViewLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<B> flatten(Function1 asTraversable) {
/*  47 */       return TraversableViewLike$class.flatten(this, asTraversable);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> filter(Function1 p) {
/*  47 */       return (IterableView<B, Coll>)TraversableViewLike$class.filter(this, p);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> withFilter(Function1 p) {
/*  47 */       return (IterableView<B, Coll>)TraversableViewLike$class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<IterableView<B, Coll>, IterableView<B, Coll>> partition(Function1 p) {
/*  47 */       return TraversableViewLike$class.partition(this, p);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> init() {
/*  47 */       return (IterableView<B, Coll>)TraversableViewLike$class.init(this);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> slice(int from, int until) {
/*  47 */       return (IterableView<B, Coll>)TraversableViewLike$class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> dropWhile(Function1 p) {
/*  47 */       return (IterableView<B, Coll>)TraversableViewLike$class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> takeWhile(Function1 p) {
/*  47 */       return (IterableView<B, Coll>)TraversableViewLike$class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<IterableView<B, Coll>, IterableView<B, Coll>> span(Function1 p) {
/*  47 */       return TraversableViewLike$class.span(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<IterableView<B, Coll>, IterableView<B, Coll>> splitAt(int n) {
/*  47 */       return TraversableViewLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  47 */       return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  47 */       return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <K> Map<K, IterableView<B, Coll>> groupBy(Function1 f) {
/*  47 */       return TraversableViewLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<TraversableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<A1>, TraversableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<A2>> unzip(Function1 asPair) {
/*  47 */       return TraversableViewLike$class.unzip(this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<TraversableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<A1>, TraversableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<A2>, TraversableViewLike<B, Coll, IterableView<B, Coll>>.Transformed<A3>> unzip3(Function1 asTriple) {
/*  47 */       return TraversableViewLike$class.unzip3(this, asTriple);
/*     */     }
/*     */     
/*     */     public String viewToString() {
/*  47 */       return GenTraversableViewLike$class.viewToString(this);
/*     */     }
/*     */     
/*     */     public Seq<B> thisSeq() {
/*  47 */       return ViewMkString$class.thisSeq(this);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  47 */       return ViewMkString$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  47 */       return ViewMkString$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  47 */       return ViewMkString$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  47 */       return ViewMkString$class.addString(this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Iterable> companion() {
/*  47 */       return Iterable$class.companion(this);
/*     */     }
/*     */     
/*     */     public Iterable<B> seq() {
/*  47 */       return Iterable$class.seq(this);
/*     */     }
/*     */     
/*     */     public Iterable<B> thisCollection() {
/*  47 */       return IterableLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public Iterable<B> toCollection(Object repr) {
/*  47 */       return IterableLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  47 */       return IterableLike$class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  47 */       return IterableLike$class.exists(this, p);
/*     */     }
/*     */     
/*     */     public Option<B> find(Function1 p) {
/*  47 */       return IterableLike$class.find(this, p);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  47 */       return (B)IterableLike$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  47 */       return (B)IterableLike$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public Iterable<B> toIterable() {
/*  47 */       return IterableLike$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public Iterator<B> toIterator() {
/*  47 */       return IterableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public B head() {
/*  47 */       return (B)IterableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public Iterator<IterableView<B, Coll>> sliding(int size) {
/*  47 */       return IterableLike$class.sliding(this, size);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> takeRight(int n) {
/*  47 */       return (IterableView<B, Coll>)IterableLike$class.takeRight(this, n);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> dropRight(int n) {
/*  47 */       return (IterableView<B, Coll>)IterableLike$class.dropRight(this, n);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/*  47 */       IterableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <B> boolean sameElements(GenIterable that) {
/*  47 */       return IterableLike$class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Stream<B> toStream() {
/*  47 */       return IterableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/*  47 */       return IterableLike$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public Object view() {
/*  47 */       return IterableLike$class.view(this);
/*     */     }
/*     */     
/*     */     public IterableView<B, IterableView<B, Coll>> view(int from, int until) {
/*  47 */       return IterableLike$class.view(this, from, until);
/*     */     }
/*     */     
/*     */     public <B> Builder<B, Iterable<B>> genericBuilder() {
/*  47 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Iterable<Iterable<B>> transpose(Function1 asTraversable) {
/*  47 */       return (Iterable<Iterable<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> repr() {
/*  47 */       return (IterableView<B, Coll>)TraversableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/*  47 */       return TraversableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public Combiner<B, ParIterable<B>> parCombiner() {
/*  47 */       return TraversableLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  47 */       return TraversableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/*  47 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/*  47 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> filterNot(Function1 p) {
/*  47 */       return (IterableView<B, Coll>)TraversableLike$class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/*  47 */       return (That)TraversableLike$class.scan(this, z, op, cbf);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> tail() {
/*  47 */       return (IterableView<B, Coll>)TraversableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public B last() {
/*  47 */       return (B)TraversableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> sliceWithKnownDelta(int from, int until, int delta) {
/*  47 */       return (IterableView<B, Coll>)TraversableLike$class.sliceWithKnownDelta(this, from, until, delta);
/*     */     }
/*     */     
/*     */     public IterableView<B, Coll> sliceWithKnownBound(int from, int until) {
/*  47 */       return (IterableView<B, Coll>)TraversableLike$class.sliceWithKnownBound(this, from, until);
/*     */     }
/*     */     
/*     */     public Iterator<IterableView<B, Coll>> tails() {
/*  47 */       return TraversableLike$class.tails(this);
/*     */     }
/*     */     
/*     */     public Iterator<IterableView<B, Coll>> inits() {
/*  47 */       return TraversableLike$class.inits(this);
/*     */     }
/*     */     
/*     */     public Traversable<B> toTraversable() {
/*  47 */       return TraversableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  47 */       return (Col)TraversableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public ParIterable<B> par() {
/*  47 */       return (ParIterable<B>)Parallelizable$class.par(this);
/*     */     }
/*     */     
/*     */     public List<B> reversed() {
/*  47 */       return TraversableOnce$class.reversed(this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  47 */       return TraversableOnce$class.size(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  47 */       return TraversableOnce$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  47 */       return TraversableOnce$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  47 */       return TraversableOnce$class.collectFirst(this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*  47 */       return (B)TraversableOnce$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  47 */       return (B)TraversableOnce$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  47 */       return (B)TraversableOnce$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  47 */       return (B)TraversableOnce$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  47 */       return TraversableOnce$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  47 */       return TraversableOnce$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/*  47 */       return (A1)TraversableOnce$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  47 */       return TraversableOnce$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/*  47 */       return (A1)TraversableOnce$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  47 */       return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/*  47 */       return (B)TraversableOnce$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/*  47 */       return (B)TraversableOnce$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <B> B min(Ordering cmp) {
/*  47 */       return (B)TraversableOnce$class.min(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> B max(Ordering cmp) {
/*  47 */       return (B)TraversableOnce$class.max(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> B maxBy(Function1 f, Ordering cmp) {
/*  47 */       return (B)TraversableOnce$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> B minBy(Function1 f, Ordering cmp) {
/*  47 */       return (B)TraversableOnce$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*  47 */       TraversableOnce$class.copyToBuffer(this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*  47 */       TraversableOnce$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*  47 */       TraversableOnce$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*  47 */       return TraversableOnce$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<B> toList() {
/*  47 */       return TraversableOnce$class.toList(this);
/*     */     }
/*     */     
/*     */     public Seq<B> toSeq() {
/*  47 */       return TraversableOnce$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> toIndexedSeq() {
/*  47 */       return TraversableOnce$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*  47 */       return TraversableOnce$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  47 */       return TraversableOnce$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Vector<B> toVector() {
/*  47 */       return TraversableOnce$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  47 */       return TraversableOnce$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*  47 */       return TraversableOnce$class.addString(this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*  47 */       return TraversableOnce$class.addString(this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  47 */       return (A1)GenTraversableOnce$class.$div$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public AbstractTransformed(IterableViewLike $outer) {
/*  47 */       GenTraversableOnce$class.$init$(this);
/*  47 */       TraversableOnce$class.$init$(this);
/*  47 */       Parallelizable$class.$init$(this);
/*  47 */       TraversableLike$class.$init$(this);
/*  47 */       GenericTraversableTemplate.class.$init$(this);
/*  47 */       GenTraversable$class.$init$(this);
/*  47 */       Traversable$class.$init$(this);
/*  47 */       GenIterable$class.$init$(this);
/*  47 */       IterableLike$class.$init$(this);
/*  47 */       Iterable$class.$init$(this);
/*  47 */       ViewMkString$class.$init$(this);
/*  47 */       GenTraversableViewLike$class.$init$(this);
/*  47 */       TraversableViewLike$class.$init$(this);
/*  47 */       GenTraversableViewLike.Transformed$class.$init$(this);
/*  47 */       TraversableViewLike.Transformed$class.$init$(this);
/*  47 */       GenIterableViewLike$class.$init$(this);
/*  47 */       IterableViewLike$class.$init$(this);
/*  47 */       GenIterableViewLike.Transformed$class.$init$(this);
/*  47 */       IterableViewLike.Transformed$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anon$9 extends AbstractTransformed<Tuple2<A, B>> implements Zipped<B> {
/*     */     private final GenIterable<B> other;
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/*  76 */       return GenIterableViewLike.Zipped$class.iterator(this);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  76 */       return GenIterableViewLike.Zipped$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public GenIterable<B> other() {
/*  76 */       return this.other;
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anon$9(IterableViewLike<A, Coll, This> $outer, GenIterable<B> that$1) {
/*  76 */       super($outer);
/*  76 */       GenIterableViewLike.Zipped$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anon$10 extends AbstractTransformed<Tuple2<A1, B>> implements ZippedAll<A1, B> {
/*     */     private final GenIterable<B> other;
/*     */     
/*     */     private final A1 thisElem;
/*     */     
/*     */     private final B thatElem;
/*     */     
/*     */     public final String viewIdentifier() {
/*  77 */       return GenIterableViewLike.ZippedAll$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A1, B>> iterator() {
/*  77 */       return GenIterableViewLike.ZippedAll$class.iterator(this);
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anon$10(IterableViewLike<A, Coll, This> $outer, GenIterable<B> that$2, Object _thisElem$1, Object _thatElem$1) {
/*  77 */       super($outer);
/*  77 */       GenIterableViewLike.ZippedAll$class.$init$(this);
/*     */     }
/*     */     
/*     */     public GenIterable<B> other() {
/*  78 */       return this.other;
/*     */     }
/*     */     
/*     */     public A1 thisElem() {
/*  79 */       return this.thisElem;
/*     */     }
/*     */     
/*     */     public B thatElem() {
/*  80 */       return this.thatElem;
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anon$1 extends AbstractTransformed<B> implements Forced<B> {
/*     */     private final GenSeq<B> forced;
/*     */     
/*     */     public Iterator<B> iterator() {
/*  82 */       return GenIterableViewLike.Forced$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  82 */       GenTraversableViewLike.Forced$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  82 */       return GenTraversableViewLike.Forced$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public GenSeq<B> forced() {
/*  82 */       return this.forced;
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anon$1(IterableViewLike<A, Coll, This> $outer, Function0 xs$1) {
/*  82 */       super($outer);
/*  82 */       GenTraversableViewLike.Forced$class.$init$(this);
/*  82 */       GenIterableViewLike.Forced$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anon$2 extends AbstractTransformed<B> implements Appended<B> {
/*     */     private final GenTraversable<B> rest;
/*     */     
/*     */     public Iterator<B> iterator() {
/*  83 */       return GenIterableViewLike.Appended$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  83 */       GenTraversableViewLike.Appended$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  83 */       return GenTraversableViewLike.Appended$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public GenTraversable<B> rest() {
/*  83 */       return this.rest;
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anon$2(IterableViewLike<A, Coll, This> $outer, GenTraversable<B> that$3) {
/*  83 */       super($outer);
/*  83 */       GenTraversableViewLike.Appended$class.$init$(this);
/*  83 */       GenIterableViewLike.Appended$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anon$3 extends AbstractTransformed<B> implements Mapped<B> {
/*     */     private final Function1<A, B> mapping;
/*     */     
/*     */     public Iterator<B> iterator() {
/*  84 */       return GenIterableViewLike.Mapped$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  84 */       GenTraversableViewLike.Mapped$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  84 */       return GenTraversableViewLike.Mapped$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, B> mapping() {
/*  84 */       return this.mapping;
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anon$3(IterableViewLike<A, Coll, This> $outer, Function1<A, B> f$1) {
/*  84 */       super($outer);
/*  84 */       GenTraversableViewLike.Mapped$class.$init$(this);
/*  84 */       GenIterableViewLike.Mapped$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anon$4 extends AbstractTransformed<B> implements FlatMapped<B> {
/*     */     private final Function1<A, GenTraversableOnce<B>> mapping;
/*     */     
/*     */     public Iterator<B> iterator() {
/*  85 */       return GenIterableViewLike.FlatMapped$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  85 */       GenTraversableViewLike.FlatMapped$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  85 */       return GenTraversableViewLike.FlatMapped$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, GenTraversableOnce<B>> mapping() {
/*  85 */       return this.mapping;
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anon$4(IterableViewLike<A, Coll, This> $outer, Function1<A, GenTraversableOnce<B>> f$2) {
/*  85 */       super($outer);
/*  85 */       GenTraversableViewLike.FlatMapped$class.$init$(this);
/*  85 */       GenIterableViewLike.FlatMapped$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anon$5 extends AbstractTransformed<A> implements Filtered {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  86 */       return GenIterableViewLike.Filtered$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  86 */       GenTraversableViewLike.Filtered$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  86 */       return GenTraversableViewLike.Filtered$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  86 */       return this.pred;
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anon$5(IterableViewLike<A, Coll, This> $outer, Function1<A, Object> p$1) {
/*  86 */       super($outer);
/*  86 */       GenTraversableViewLike.Filtered$class.$init$(this);
/*  86 */       GenIterableViewLike.Filtered$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anon$6 extends AbstractTransformed<A> implements Sliced {
/*     */     private final SliceInterval endpoints;
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  87 */       return GenIterableViewLike.Sliced$class.iterator(this);
/*     */     }
/*     */     
/*     */     public int from() {
/*  87 */       return GenTraversableViewLike.Sliced$class.from(this);
/*     */     }
/*     */     
/*     */     public int until() {
/*  87 */       return GenTraversableViewLike.Sliced$class.until(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  87 */       GenTraversableViewLike.Sliced$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  87 */       return GenTraversableViewLike.Sliced$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public SliceInterval endpoints() {
/*  87 */       return this.endpoints;
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anon$6(IterableViewLike<A, Coll, This> $outer, SliceInterval _endpoints$1) {
/*  87 */       super($outer);
/*  87 */       GenTraversableViewLike.Sliced$class.$init$(this);
/*  87 */       GenIterableViewLike.Sliced$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anon$7 extends AbstractTransformed<A> implements DroppedWhile {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  88 */       return GenIterableViewLike.DroppedWhile$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  88 */       GenTraversableViewLike.DroppedWhile$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  88 */       return GenTraversableViewLike.DroppedWhile$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  88 */       return this.pred;
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anon$7(IterableViewLike<A, Coll, This> $outer, Function1<A, Object> p$2) {
/*  88 */       super($outer);
/*  88 */       GenTraversableViewLike.DroppedWhile$class.$init$(this);
/*  88 */       GenIterableViewLike.DroppedWhile$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anon$8 extends AbstractTransformed<A> implements TakenWhile {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  89 */       return GenIterableViewLike.TakenWhile$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  89 */       GenTraversableViewLike.TakenWhile$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  89 */       return GenTraversableViewLike.TakenWhile$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  89 */       return this.pred;
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anon$8(IterableViewLike<A, Coll, This> $outer, Function1<A, Object> p$3) {
/*  89 */       super($outer);
/*  89 */       GenTraversableViewLike.TakenWhile$class.$init$(this);
/*  89 */       GenIterableViewLike.TakenWhile$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anonfun$grouped$1 extends AbstractFunction1<Seq<A>, This> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final This apply(Seq x) {
/* 115 */       return (This)this.$outer.newForced((Function0)new IterableViewLike$$anonfun$grouped$1$$anonfun$apply$1(this, ($anonfun$grouped$1)x));
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anonfun$grouped$1(IterableViewLike $outer) {}
/*     */     
/*     */     public class IterableViewLike$$anonfun$grouped$1$$anonfun$apply$1 extends AbstractFunction0<Seq<A>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Seq x$1;
/*     */       
/*     */       public final Seq<A> apply() {
/* 115 */         return this.x$1;
/*     */       }
/*     */       
/*     */       public IterableViewLike$$anonfun$grouped$1$$anonfun$apply$1(IterableViewLike$$anonfun$grouped$1 $outer, Seq x$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableViewLike$$anonfun$sliding$1 extends AbstractFunction1<Seq<A>, This> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final This apply(Seq x) {
/* 118 */       return (This)this.$outer.newForced((Function0)new IterableViewLike$$anonfun$sliding$1$$anonfun$apply$2(this, ($anonfun$sliding$1)x));
/*     */     }
/*     */     
/*     */     public IterableViewLike$$anonfun$sliding$1(IterableViewLike $outer) {}
/*     */     
/*     */     public class IterableViewLike$$anonfun$sliding$1$$anonfun$apply$2 extends AbstractFunction0<Seq<A>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Seq x$2;
/*     */       
/*     */       public final Seq<A> apply() {
/* 118 */         return this.x$2;
/*     */       }
/*     */       
/*     */       public IterableViewLike$$anonfun$sliding$1$$anonfun$apply$2(IterableViewLike$$anonfun$sliding$1 $outer, Seq x$2) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Forced<B> extends TraversableViewLike<A, Coll, This>.Forced<B>, GenIterableViewLike<A, Coll, This>.Forced<B>, Transformed<B> {}
/*     */   
/*     */   public interface Sliced extends TraversableViewLike<A, Coll, This>.Sliced, GenIterableViewLike<A, Coll, This>.Sliced, Transformed<A> {}
/*     */   
/*     */   public interface Mapped<B> extends TraversableViewLike<A, Coll, This>.Mapped<B>, GenIterableViewLike<A, Coll, This>.Mapped<B>, Transformed<B> {}
/*     */   
/*     */   public interface Zipped<B> extends Transformed<Tuple2<A, B>>, GenIterableViewLike<A, Coll, This>.Zipped<B> {}
/*     */   
/*     */   public interface Appended<B> extends TraversableViewLike<A, Coll, This>.Appended<B>, GenIterableViewLike<A, Coll, This>.Appended<B>, Transformed<B> {}
/*     */   
/*     */   public interface Filtered extends TraversableViewLike<A, Coll, This>.Filtered, GenIterableViewLike<A, Coll, This>.Filtered, Transformed<A> {}
/*     */   
/*     */   public interface EmptyView extends Transformed<Nothing$>, TraversableViewLike<A, Coll, This>.EmptyView, GenIterableViewLike<A, Coll, This>.EmptyView {}
/*     */   
/*     */   public interface ZippedAll<A1, B> extends Transformed<Tuple2<A1, B>>, GenIterableViewLike<A, Coll, This>.ZippedAll<A1, B> {}
/*     */   
/*     */   public interface FlatMapped<B> extends TraversableViewLike<A, Coll, This>.FlatMapped<B>, GenIterableViewLike<A, Coll, This>.FlatMapped<B>, Transformed<B> {}
/*     */   
/*     */   public interface TakenWhile extends TraversableViewLike<A, Coll, This>.TakenWhile, GenIterableViewLike<A, Coll, This>.TakenWhile, Transformed<A> {}
/*     */   
/*     */   public interface Transformed<B> extends IterableView<B, Coll>, TraversableViewLike<A, Coll, This>.Transformed<B>, GenIterableViewLike<A, Coll, This>.Transformed<B> {
/*     */     Iterator<B> iterator();
/*     */     
/*     */     <U> void foreach(Function1<B, U> param1Function1);
/*     */     
/*     */     String toString();
/*     */   }
/*     */   
/*     */   public interface DroppedWhile extends TraversableViewLike<A, Coll, This>.DroppedWhile, GenIterableViewLike<A, Coll, This>.DroppedWhile, Transformed<A> {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IterableViewLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */