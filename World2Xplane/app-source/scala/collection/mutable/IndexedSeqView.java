/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenIterableViewLike;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSeqViewLike;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableViewLike;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.IndexedSeqLike;
/*     */ import scala.collection.IndexedSeqOptimized;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.IterableViewLike;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.SeqView;
/*     */ import scala.collection.SeqViewLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.TraversableViewLike;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParSeq;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\025daB\001\003!\003\r\t!\003\002\017\023:$W\r_3e'\026\fh+[3x\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\007))2e\005\004\001\027=qR%\013\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007c\001\t\022'5\t!!\003\002\023\005\tQ\021J\0343fq\026$7+Z9\021\005Q)B\002\001\003\006-\001\021\ra\006\002\002\003F\021\001d\007\t\003\031eI!A\007\004\003\0179{G\017[5oOB\021A\002H\005\003;\031\0211!\0218z!\021\001rdE\021\n\005\001\022!aE%oI\026DX\rZ*fc>\003H/[7ju\026$\007\003\002\t\001'\t\002\"\001F\022\005\r\021\002AQ1\001\030\005\021\031u\016\0347\021\t\031:3CI\007\002\t%\021\001\006\002\002\b'\026\fh+[3x!\0251#f\005\022\"\023\tYCAA\006TKF4\026.Z<MS.,\007\"B\027\001\t\003q\023A\002\023j]&$H\005F\0010!\ta\001'\003\0022\r\t!QK\\5u\013\031\031\004\001)A\005C\t!A\013[5t\021\025)\004A\"\0017\003\031)\b\017Z1uKR\031qf\016\037\t\013a\"\004\031A\035\002\007%$\007\020\005\002\ru%\0211H\002\002\004\023:$\b\"B\0375\001\004\031\022\001B3mK64qa\020\001\021\002\007\005\001IA\006Ue\006t7OZ8s[\026$WCA!E'\021q4B\021$\021\tA\0011I\t\t\003)\021#Q!\022 C\002]\021\021A\021\t\004\017\"\033U\"\001\001\n\005}R\003\"B\027?\t\003q\003\"B\033?\r\003YEcA\030M\033\")\001H\023a\001s!)QH\023a\001\007\")qJ\020C!!\006AAo\\*ue&tw\rF\001R!\t\021v+D\001T\025\t!V+\001\003mC:<'\"\001,\002\t)\fg/Y\005\0031N\023aa\025;sS:<gA\002.\001\003\003!1LA\nBEN$(/Y2u)J\fgn\0354pe6,G-\006\002]AN\031\021,X1\021\007\035sv,\003\002[UA\021A\003\031\003\006\013f\023\ra\006\t\004\017zz\006\"B2Z\t\003!\027A\002\037j]&$h\bF\001f!\r9\025l\030\004\bO\002\001\n1!\001i\005\031\031F.[2fIN!amC5l!\t9%.\003\002hUA\031qIP\n\t\01352G\021\001\030\t\01394G\021I8\002\r1,gn\032;i+\005I\004\"B\033g\t\003\tHcA\030sg\")\001\b\035a\001s!)Q\b\035a\001'\0319Q\017\001I\001\004\0031(\001\003$jYR,'/\0323\024\tQ\\qo\033\t\003\017bL!!\036\026\t\0135\"H\021\001\030\t\013U\"H\021A>\025\007=bX\020C\0039u\002\007\021\bC\003>u\002\0071C\002\005\000\001A\005\031\021AA\001\005)!\026m[3o/\"LG.Z\n\006}.\t\031a\033\t\004\017\006\025\021BA@+\021\025ic\020\"\001/\021\031)d\020\"\001\002\fQ)q&!\004\002\020!1\001(!\003A\002eBa!PA\005\001\004\031b!CA\n\001A\005\031\021AA\013\0051!%o\0349qK\022<\006.\0337f'\031\t\tbCA\fWB\031q)!\007\n\007\005M!\006\003\004.\003#!\tA\f\005\bk\005EA\021AA\020)\025y\023\021EA\022\021\031A\024Q\004a\001s!1Q(!\bA\002M1\021\"a\n\001!\003\r\t!!\013\003\021I+g/\032:tK\022\034b!!\n\f\003WY\007cA$\002.%\031\021q\005\026\t\r5\n)\003\"\001/\021\035)\024Q\005C\001\003g!RaLA\033\003oAa\001OA\031\001\004I\004BB\037\0022\001\0071\003C\004\002<\001!\t&!\020\002\0279,wOR5mi\026\024X\r\032\013\004W\006}\002\002CA!\003s\001\r!a\021\002\003A\004b\001DA#'\005%\023bAA$\r\tIa)\0368di&|g.\r\t\004\031\005-\023bAA'\r\t9!i\\8mK\006t\007bBA)\001\021E\0231K\001\n]\026<8\013\\5dK\022$2a[A+\021!\t9&a\024A\002\005e\023AC0f]\022\004x.\0338ugB!\0211LA1\033\t\tiFC\002\002`\021\tqaZ3oKJL7-\003\003\002d\005u#!D*mS\016,\027J\034;feZ\fG\016C\004\002h\001!\t&!\033\002\0379,w\017\022:paB,Gm\0265jY\026$2a[A6\021!\t\t%!\032A\002\005\r\003bBA8\001\021E\023\021O\001\016]\026<H+Y6f]^C\027\016\\3\025\007-\f\031\b\003\005\002B\0055\004\031AA\"\021\035\t9\b\001C)\003s\n1B\\3x%\0264XM]:fIV\t1\016C\004\002~\001!Y!a \002\r\005\034H\013[5t)\021\t\t)a!\021\005\035\023\004bBAC\003w\002\ra[\001\003qNDq!!#\001\t\003\nY)\001\004gS2$XM\035\013\005\003\003\013i\t\003\005\002B\005\035\005\031AA\"\021\035\t\t\n\001C!\003'\013A!\0338jiV\021\021\021\021\005\b\003/\003A\021IAM\003\021!'o\0349\025\t\005\005\0251\024\005\b\003;\013)\n1\001:\003\005q\007bBAQ\001\021\005\0231U\001\005i\006\\W\r\006\003\002\002\006\025\006bBAO\003?\003\r!\017\005\b\003S\003A\021IAV\003\025\031H.[2f)\031\t\t)!,\0022\"9\021qVAT\001\004I\024\001\0024s_6Dq!a-\002(\002\007\021(A\003v]RLG\016C\004\0028\002!\t%!/\002\023\021\024x\016],iS2,G\003BAA\003wC\001\"!\021\0026\002\007\0211\t\005\b\003\003A\021IAa\003%!\030m[3XQ&dW\r\006\003\002\002\006\r\007\002CA!\003{\003\r!a\021\t\017\005\035\007\001\"\021\002J\006!1\017]1o)\021\tY-!5\021\0171\ti-!!\002\002&\031\021q\032\004\003\rQ+\b\017\\33\021!\t\t%!2A\002\005\r\003bBAk\001\021\005\023q[\001\bgBd\027\016^!u)\021\tY-!7\t\017\005u\0251\033a\001s!9\021Q\034\001\005B\005M\025a\002:fm\026\0248/Z\004\b\003C\024\001\022AAr\0039Ie\016Z3yK\022\034V-\035,jK^\0042\001EAs\r\031\t!\001#\001\002hN\031\021Q]\006\t\017\r\f)\017\"\001\002lR\021\0211]\003\007I\005\025\b!a<1\r\005E\030q`A}!\0351\0231_A|\003{L1!!>\005\005=!&/\031<feN\f'\r\\3WS\026<\bc\001\013\002z\022Y\0211`Aw\003\003\005\tQ!\001\030\005\ryF%\r\t\004)\005}H\001\004B\001\003[\f\t\021!A\003\002\t\r!!A\"\022\007a\021)\001\r\003\003\b\t=\001#\002\t\003\n\t5\021b\001B\006\005\tYAK]1wKJ\034\030M\0317f!\r!\"q\002\003\f\005#\021\031\"!A\001\002\013\005qCA\002`II\"AB!\001\002n\006\005\t\021!B\001\005\007A\001Ba\006\002f\022\r!\021D\001\rG\006t')^5mI\032\023x.\\\013\005\0057\021I#\006\002\003\036AQ\0211\fB\020\005G\0219Ca\013\n\t\t\005\022Q\f\002\r\007\006t')^5mI\032\023x.\034\t\005\005K\ti/\004\002\002fB\031AC!\013\005\rY\021)B1\001\030!\0311sEa\n\003.A\"!q\006B\034!\025\001\"\021\007B\033\023\r\021\031D\001\002\004'\026\f\bc\001\013\0038\021Y!\021\bB\013\003\003\005\tQ!\001\030\005\ryFe\r\005\t\005{\t)\017b\001\003@\005y\021M\035:DC:\024U/\0337e\rJ|W.\006\003\003B\t}SC\001B\"!)\tYFa\b\003F\tu#\021\r\031\005\005\017\022Y\005E\004'\003g\024IEa\024\021\007Q\021Y\005B\006\003N\tm\022\021!A\001\006\0039\"aA0%kA\"!\021\013B-!\025a!1\013B,\023\r\021)F\002\002\006\003J\024\030-\037\t\004)\teCa\003B.\005w\t\t\021!A\003\002]\0211a\030\0237!\r!\"q\f\003\007-\tm\"\031A\f\021\r\031:#Q\fB2!\025a!1\013B/\001")
/*     */ public interface IndexedSeqView<A, Coll> extends IndexedSeq<A>, IndexedSeqOptimized<A, IndexedSeqView<A, Coll>>, SeqView<A, Coll>, SeqViewLike<A, Coll, IndexedSeqView<A, Coll>> {
/*     */   void update(int paramInt, A paramA);
/*     */   
/*     */   Transformed<A> newFiltered(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Transformed<A> newSliced(SliceInterval paramSliceInterval);
/*     */   
/*     */   Transformed<A> newDroppedWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Transformed<A> newTakenWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Transformed<A> newReversed();
/*     */   
/*     */   IndexedSeqView<A, Coll> filter(Function1<A, Object> paramFunction1);
/*     */   
/*     */   IndexedSeqView<A, Coll> init();
/*     */   
/*     */   IndexedSeqView<A, Coll> drop(int paramInt);
/*     */   
/*     */   IndexedSeqView<A, Coll> take(int paramInt);
/*     */   
/*     */   IndexedSeqView<A, Coll> slice(int paramInt1, int paramInt2);
/*     */   
/*     */   IndexedSeqView<A, Coll> dropWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   IndexedSeqView<A, Coll> takeWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Tuple2<IndexedSeqView<A, Coll>, IndexedSeqView<A, Coll>> span(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Tuple2<IndexedSeqView<A, Coll>, IndexedSeqView<A, Coll>> splitAt(int paramInt);
/*     */   
/*     */   IndexedSeqView<A, Coll> reverse();
/*     */   
/*     */   public abstract class Transformed$class {
/*     */     public static void $init$(IndexedSeqView.Transformed $this) {}
/*     */     
/*     */     public static String toString(IndexedSeqView.Transformed $this) {
/*  42 */       return $this.viewToString();
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class AbstractTransformed<B> extends SeqViewLike<A, Coll, IndexedSeqView<A, Coll>>.AbstractTransformed<B> implements Transformed<B> {
/*     */     public String toString() {
/*  46 */       return IndexedSeqView.Transformed$class.toString(this);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll>.Transformed<B> newFiltered(Function1 p) {
/*  46 */       return IndexedSeqView$class.newFiltered(this, p);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll>.Transformed<B> newSliced(SliceInterval _endpoints) {
/*  46 */       return IndexedSeqView$class.newSliced(this, _endpoints);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll>.Transformed<B> newDroppedWhile(Function1 p) {
/*  46 */       return IndexedSeqView$class.newDroppedWhile(this, p);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll>.Transformed<B> newTakenWhile(Function1 p) {
/*  46 */       return IndexedSeqView$class.newTakenWhile(this, p);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll>.Transformed<B> newReversed() {
/*  46 */       return IndexedSeqView$class.newReversed(this);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> filter(Function1 p) {
/*  46 */       return IndexedSeqView$class.filter(this, p);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> init() {
/*  46 */       return IndexedSeqView$class.init(this);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> drop(int n) {
/*  46 */       return IndexedSeqView$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> take(int n) {
/*  46 */       return IndexedSeqView$class.take(this, n);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> slice(int from, int until) {
/*  46 */       return IndexedSeqView$class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> dropWhile(Function1 p) {
/*  46 */       return IndexedSeqView$class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> takeWhile(Function1 p) {
/*  46 */       return IndexedSeqView$class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<IndexedSeqView<B, Coll>, IndexedSeqView<B, Coll>> span(Function1 p) {
/*  46 */       return IndexedSeqView$class.span(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<IndexedSeqView<B, Coll>, IndexedSeqView<B, Coll>> splitAt(int n) {
/*  46 */       return IndexedSeqView$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> reverse() {
/*  46 */       return IndexedSeqView$class.reverse(this);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
/*  46 */       return TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
/*  46 */       return IterableLike.class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
/*  46 */       return IterableViewLike.class.zip((IterableViewLike)this, that, bf);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$head() {
/*  46 */       return IterableLike.class.head(this);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$tail() {
/*  46 */       return TraversableLike.class.tail(this);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$last() {
/*  46 */       return TraversableLike.class.last(this);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$init() {
/*  46 */       return TraversableViewLike.class.init((TraversableViewLike)this);
/*     */     }
/*     */     
/*     */     public boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
/*  46 */       return IterableLike.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
/*  46 */       return SeqLike.class.endsWith(this, that);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  46 */       return IndexedSeqOptimized.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  46 */       IndexedSeqOptimized.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  46 */       return IndexedSeqOptimized.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  46 */       return IndexedSeqOptimized.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public Option<B> find(Function1 p) {
/*  46 */       return IndexedSeqOptimized.class.find(this, p);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  46 */       return (B)IndexedSeqOptimized.class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  46 */       return (B)IndexedSeqOptimized.class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  46 */       return (B)IndexedSeqOptimized.class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  46 */       return (B)IndexedSeqOptimized.class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  46 */       return (That)IndexedSeqOptimized.class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  46 */       return (That)IndexedSeqOptimized.class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public B head() {
/*  46 */       return (B)IndexedSeqOptimized.class.head(this);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> tail() {
/*  46 */       return (IndexedSeqView<B, Coll>)IndexedSeqOptimized.class.tail(this);
/*     */     }
/*     */     
/*     */     public B last() {
/*  46 */       return (B)IndexedSeqOptimized.class.last(this);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> takeRight(int n) {
/*  46 */       return (IndexedSeqView<B, Coll>)IndexedSeqOptimized.class.takeRight(this, n);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, Coll> dropRight(int n) {
/*  46 */       return (IndexedSeqView<B, Coll>)IndexedSeqOptimized.class.dropRight(this, n);
/*     */     }
/*     */     
/*     */     public <B> boolean sameElements(GenIterable that) {
/*  46 */       return IndexedSeqOptimized.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/*  46 */       IndexedSeqOptimized.class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public int lengthCompare(int len) {
/*  46 */       return IndexedSeqOptimized.class.lengthCompare(this, len);
/*     */     }
/*     */     
/*     */     public int segmentLength(Function1 p, int from) {
/*  46 */       return IndexedSeqOptimized.class.segmentLength(this, p, from);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p, int from) {
/*  46 */       return IndexedSeqOptimized.class.indexWhere(this, p, from);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p, int end) {
/*  46 */       return IndexedSeqOptimized.class.lastIndexWhere(this, p, end);
/*     */     }
/*     */     
/*     */     public Iterator<B> reverseIterator() {
/*  46 */       return IndexedSeqOptimized.class.reverseIterator(this);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that, int offset) {
/*  46 */       return IndexedSeqOptimized.class.startsWith(this, that, offset);
/*     */     }
/*     */     
/*     */     public <B> boolean endsWith(GenSeq that) {
/*  46 */       return IndexedSeqOptimized.class.endsWith(this, that);
/*     */     }
/*     */     
/*     */     public GenericCompanion<IndexedSeq> companion() {
/*  46 */       return IndexedSeq$class.companion(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> seq() {
/*  46 */       return IndexedSeq$class.seq(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> thisCollection() {
/*  46 */       return IndexedSeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> toCollection(Object repr) {
/*  46 */       return IndexedSeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Object view() {
/*  46 */       return IndexedSeqLike$class.view(this);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<B, IndexedSeqView<B, Coll>> view(int from, int until) {
/*  46 */       return IndexedSeqLike$class.view(this, from, until);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  46 */       return IndexedSeqLike.class.hashCode(this);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/*  46 */       return IndexedSeqLike.class.iterator(this);
/*     */     }
/*     */     
/*     */     public <A1> Buffer<A1> toBuffer() {
/*  46 */       return IndexedSeqLike.class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public Combiner<B, ParSeq<B>> parCombiner() {
/*  46 */       return SeqLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public SeqLike<B, Seq<B>> transform(Function1 f) {
/*  46 */       return SeqLike$class.transform(this, f);
/*     */     }
/*     */     
/*     */     public Object scala$collection$mutable$Cloneable$$super$clone() {
/*  46 */       return super.clone();
/*     */     }
/*     */     
/*     */     public Seq<B> clone() {
/*  46 */       return (Seq<B>)Cloneable$class.clone(this);
/*     */     }
/*     */     
/*     */     public AbstractTransformed(IndexedSeqView $outer) {
/*  46 */       super($outer);
/*  46 */       Traversable$class.$init$(this);
/*  46 */       Iterable$class.$init$(this);
/*  46 */       Cloneable$class.$init$(this);
/*  46 */       SeqLike$class.$init$(this);
/*  46 */       Seq$class.$init$(this);
/*  46 */       IndexedSeqLike.class.$init$(this);
/*  46 */       IndexedSeq.class.$init$(this);
/*  46 */       IndexedSeqLike$class.$init$(this);
/*  46 */       IndexedSeq$class.$init$(this);
/*  46 */       IndexedSeqOptimized.class.$init$(this);
/*  46 */       IndexedSeqView$class.$init$(this);
/*  46 */       IndexedSeqView.Transformed$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Sliced$class {
/*     */     public static void $init$(IndexedSeqView.Sliced $this) {}
/*     */     
/*     */     public static int length(IndexedSeqView.Sliced $this) {
/*  50 */       return $this.endpoints().width();
/*     */     }
/*     */     
/*     */     public static void update(IndexedSeqView.Sliced $this, int idx, Object elem) {
/*  52 */       if (idx + $this.from() < $this.until()) {
/*  52 */         $this.scala$collection$mutable$IndexedSeqView$Sliced$$$outer().update(idx + $this.from(), elem);
/*     */         return;
/*     */       } 
/*  53 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Filtered$class {
/*     */     public static void $init$(IndexedSeqView.Filtered $this) {}
/*     */     
/*     */     public static void update(IndexedSeqView.Filtered $this, int idx, Object elem) {
/*  57 */       $this.scala$collection$mutable$IndexedSeqView$Filtered$$$outer().update($this.index()[idx], elem);
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class TakenWhile$class {
/*     */     public static void $init$(IndexedSeqView.TakenWhile $this) {}
/*     */     
/*     */     public static void update(IndexedSeqView.TakenWhile $this, int idx, Object elem) {
/*  62 */       if (idx < $this.len()) {
/*  62 */         $this.scala$collection$mutable$IndexedSeqView$TakenWhile$$$outer().update(idx, elem);
/*     */         return;
/*     */       } 
/*  63 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class DroppedWhile$class {
/*     */     public static void $init$(IndexedSeqView.DroppedWhile $this) {}
/*     */     
/*     */     public static void update(IndexedSeqView.DroppedWhile $this, int idx, Object elem) {
/*  68 */       if (idx >= 0) {
/*  68 */         $this.scala$collection$mutable$IndexedSeqView$DroppedWhile$$$outer().update(idx + $this.start(), elem);
/*     */         return;
/*     */       } 
/*  69 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Reversed$class {
/*     */     public static void $init$(IndexedSeqView.Reversed $this) {}
/*     */     
/*     */     public static void update(IndexedSeqView.Reversed $this, int idx, Object elem) {
/*  73 */       $this.scala$collection$mutable$IndexedSeqView$Reversed$$$outer().update($this.scala$collection$mutable$IndexedSeqView$Reversed$$$outer().length() - 1 - idx, elem);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IndexedSeqView$$anon$1 extends AbstractTransformed<A> implements Filtered {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     private final int[] index;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public void update(int idx, Object elem) {
/*  79 */       IndexedSeqView.Filtered$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     private int[] index$lzycompute() {
/*  79 */       synchronized (this) {
/*  79 */         if (!this.bitmap$0) {
/*  79 */           this.index = GenSeqViewLike.Filtered.class.index((GenSeqViewLike.Filtered)this);
/*  79 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/IndexedSeqView$$anon$1}} */
/*  79 */         return this.index;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int[] index() {
/*  79 */       return this.bitmap$0 ? this.index : index$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  79 */       return GenSeqViewLike.Filtered.class.length((GenSeqViewLike.Filtered)this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  79 */       return GenSeqViewLike.Filtered.class.apply((GenSeqViewLike.Filtered)this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  79 */       return GenIterableViewLike.Filtered.class.iterator((GenIterableViewLike.Filtered)this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  79 */       GenTraversableViewLike.Filtered.class.foreach((GenTraversableViewLike.Filtered)this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  79 */       return GenTraversableViewLike.Filtered.class.viewIdentifier((GenTraversableViewLike.Filtered)this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  79 */       return this.pred;
/*     */     }
/*     */     
/*     */     public IndexedSeqView$$anon$1(IndexedSeqView<A, Coll> $outer, Function1<A, Object> p$1) {
/*  79 */       super($outer);
/*  79 */       GenTraversableViewLike.Filtered.class.$init$((GenTraversableViewLike.Filtered)this);
/*  79 */       GenIterableViewLike.Filtered.class.$init$((GenIterableViewLike.Filtered)this);
/*  79 */       GenSeqViewLike.Filtered.class.$init$((GenSeqViewLike.Filtered)this);
/*  79 */       IndexedSeqView.Filtered$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IndexedSeqView$$anon$2 extends AbstractTransformed<A> implements Sliced {
/*     */     private final SliceInterval endpoints;
/*     */     
/*     */     public int length() {
/*  80 */       return IndexedSeqView.Sliced$class.length(this);
/*     */     }
/*     */     
/*     */     public void update(int idx, Object elem) {
/*  80 */       IndexedSeqView.Sliced$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  80 */       return GenSeqViewLike.Sliced.class.apply((GenSeqViewLike.Sliced)this, idx);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  80 */       GenSeqViewLike.Sliced.class.foreach((GenSeqViewLike.Sliced)this, f);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  80 */       return GenSeqViewLike.Sliced.class.iterator((GenSeqViewLike.Sliced)this);
/*     */     }
/*     */     
/*     */     public int from() {
/*  80 */       return GenTraversableViewLike.Sliced.class.from((GenTraversableViewLike.Sliced)this);
/*     */     }
/*     */     
/*     */     public int until() {
/*  80 */       return GenTraversableViewLike.Sliced.class.until((GenTraversableViewLike.Sliced)this);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  80 */       return GenTraversableViewLike.Sliced.class.viewIdentifier((GenTraversableViewLike.Sliced)this);
/*     */     }
/*     */     
/*     */     public SliceInterval endpoints() {
/*  80 */       return this.endpoints;
/*     */     }
/*     */     
/*     */     public IndexedSeqView$$anon$2(IndexedSeqView<A, Coll> $outer, SliceInterval _endpoints$1) {
/*  80 */       super($outer);
/*  80 */       GenTraversableViewLike.Sliced.class.$init$((GenTraversableViewLike.Sliced)this);
/*  80 */       GenIterableViewLike.Sliced.class.$init$((GenIterableViewLike.Sliced)this);
/*  80 */       GenSeqViewLike.Sliced.class.$init$((GenSeqViewLike.Sliced)this);
/*  80 */       IndexedSeqView.Sliced$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IndexedSeqView$$anon$3 extends AbstractTransformed<A> implements DroppedWhile {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     private final int start;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public void update(int idx, Object elem) {
/*  81 */       IndexedSeqView.DroppedWhile$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     private int start$lzycompute() {
/*  81 */       synchronized (this) {
/*  81 */         if (!this.bitmap$0) {
/*  81 */           this.start = GenSeqViewLike.DroppedWhile.class.start((GenSeqViewLike.DroppedWhile)this);
/*  81 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/IndexedSeqView$$anon$3}} */
/*  81 */         return this.start;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int start() {
/*  81 */       return this.bitmap$0 ? this.start : start$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  81 */       return GenSeqViewLike.DroppedWhile.class.length((GenSeqViewLike.DroppedWhile)this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  81 */       return GenSeqViewLike.DroppedWhile.class.apply((GenSeqViewLike.DroppedWhile)this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  81 */       return GenIterableViewLike.DroppedWhile.class.iterator((GenIterableViewLike.DroppedWhile)this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  81 */       GenTraversableViewLike.DroppedWhile.class.foreach((GenTraversableViewLike.DroppedWhile)this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  81 */       return GenTraversableViewLike.DroppedWhile.class.viewIdentifier((GenTraversableViewLike.DroppedWhile)this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  81 */       return this.pred;
/*     */     }
/*     */     
/*     */     public IndexedSeqView$$anon$3(IndexedSeqView<A, Coll> $outer, Function1<A, Object> p$2) {
/*  81 */       super($outer);
/*  81 */       GenTraversableViewLike.DroppedWhile.class.$init$((GenTraversableViewLike.DroppedWhile)this);
/*  81 */       GenIterableViewLike.DroppedWhile.class.$init$((GenIterableViewLike.DroppedWhile)this);
/*  81 */       GenSeqViewLike.DroppedWhile.class.$init$((GenSeqViewLike.DroppedWhile)this);
/*  81 */       IndexedSeqView.DroppedWhile$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IndexedSeqView$$anon$4 extends AbstractTransformed<A> implements TakenWhile {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     private final int len;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public void update(int idx, Object elem) {
/*  82 */       IndexedSeqView.TakenWhile$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     private int len$lzycompute() {
/*  82 */       synchronized (this) {
/*  82 */         if (!this.bitmap$0) {
/*  82 */           this.len = GenSeqViewLike.TakenWhile.class.len((GenSeqViewLike.TakenWhile)this);
/*  82 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/IndexedSeqView$$anon$4}} */
/*  82 */         return this.len;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int len() {
/*  82 */       return this.bitmap$0 ? this.len : len$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  82 */       return GenSeqViewLike.TakenWhile.class.length((GenSeqViewLike.TakenWhile)this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  82 */       return GenSeqViewLike.TakenWhile.class.apply((GenSeqViewLike.TakenWhile)this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  82 */       return GenIterableViewLike.TakenWhile.class.iterator((GenIterableViewLike.TakenWhile)this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  82 */       GenTraversableViewLike.TakenWhile.class.foreach((GenTraversableViewLike.TakenWhile)this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  82 */       return GenTraversableViewLike.TakenWhile.class.viewIdentifier((GenTraversableViewLike.TakenWhile)this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  82 */       return this.pred;
/*     */     }
/*     */     
/*     */     public IndexedSeqView$$anon$4(IndexedSeqView<A, Coll> $outer, Function1<A, Object> p$3) {
/*  82 */       super($outer);
/*  82 */       GenTraversableViewLike.TakenWhile.class.$init$((GenTraversableViewLike.TakenWhile)this);
/*  82 */       GenIterableViewLike.TakenWhile.class.$init$((GenIterableViewLike.TakenWhile)this);
/*  82 */       GenSeqViewLike.TakenWhile.class.$init$((GenSeqViewLike.TakenWhile)this);
/*  82 */       IndexedSeqView.TakenWhile$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IndexedSeqView$$anon$5 extends AbstractTransformed<A> implements Reversed {
/*     */     public void update(int idx, Object elem) {
/*  83 */       IndexedSeqView.Reversed$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  83 */       return GenSeqViewLike.Reversed.class.iterator((GenSeqViewLike.Reversed)this);
/*     */     }
/*     */     
/*     */     public int length() {
/*  83 */       return GenSeqViewLike.Reversed.class.length((GenSeqViewLike.Reversed)this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  83 */       return GenSeqViewLike.Reversed.class.apply((GenSeqViewLike.Reversed)this, idx);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  83 */       return GenSeqViewLike.Reversed.class.viewIdentifier((GenSeqViewLike.Reversed)this);
/*     */     }
/*     */     
/*     */     public IndexedSeqView$$anon$5(IndexedSeqView<A, Coll> $outer) {
/*  83 */       super($outer);
/*  83 */       GenSeqViewLike.Reversed.class.$init$((GenSeqViewLike.Reversed)this);
/*  83 */       IndexedSeqView.Reversed$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class IndexedSeqView$$anon$6 implements CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, SeqView<A, Seq<?>>> {
/*     */     public TraversableView.NoBuilder<A> apply(TraversableView from) {
/* 111 */       return new TraversableView.NoBuilder();
/*     */     }
/*     */     
/*     */     public TraversableView.NoBuilder<A> apply() {
/* 112 */       return new TraversableView.NoBuilder();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class IndexedSeqView$$anon$7 implements CanBuildFrom<TraversableView<?, Object>, A, SeqView<A, Object>> {
/*     */     public TraversableView.NoBuilder<A> apply(TraversableView from) {
/* 116 */       return new TraversableView.NoBuilder();
/*     */     }
/*     */     
/*     */     public TraversableView.NoBuilder<A> apply() {
/* 117 */       return new TraversableView.NoBuilder();
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Sliced extends SeqViewLike<A, Coll, IndexedSeqView<A, Coll>>.Sliced, Transformed<A> {
/*     */     int length();
/*     */     
/*     */     void update(int param1Int, A param1A);
/*     */   }
/*     */   
/*     */   public interface Filtered extends SeqViewLike<A, Coll, IndexedSeqView<A, Coll>>.Filtered, Transformed<A> {
/*     */     void update(int param1Int, A param1A);
/*     */   }
/*     */   
/*     */   public interface Reversed extends SeqViewLike<A, Coll, IndexedSeqView<A, Coll>>.Reversed, Transformed<A> {
/*     */     void update(int param1Int, A param1A);
/*     */   }
/*     */   
/*     */   public interface TakenWhile extends SeqViewLike<A, Coll, IndexedSeqView<A, Coll>>.TakenWhile, Transformed<A> {
/*     */     void update(int param1Int, A param1A);
/*     */   }
/*     */   
/*     */   public interface Transformed<B> extends IndexedSeqView<B, Coll>, SeqViewLike<A, Coll, IndexedSeqView<A, Coll>>.Transformed<B> {
/*     */     void update(int param1Int, B param1B);
/*     */     
/*     */     String toString();
/*     */   }
/*     */   
/*     */   public interface DroppedWhile extends SeqViewLike<A, Coll, IndexedSeqView<A, Coll>>.DroppedWhile, Transformed<A> {
/*     */     void update(int param1Int, A param1A);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\IndexedSeqView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */