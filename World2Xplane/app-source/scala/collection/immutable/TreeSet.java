/*     */ package scala.collection.immutable;
/*     */ 
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
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenSetLike;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.SetLike;
/*     */ import scala.collection.SortedSet;
/*     */ import scala.collection.SortedSetLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericSetTemplate;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Sorted;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParSet;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t}s!B\001\003\021\003I\021a\002+sK\026\034V\r\036\006\003\007\021\t\021\"[7nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\021\005)YQ\"\001\002\007\0131\021\001\022A\007\003\017Q\023X-Z*fiN\0311BD\030\021\007=\021B#D\001\021\025\t\tB!A\004hK:,'/[2\n\005M\001\"!G%n[V$\030M\0317f'>\024H/\0323TKR4\025m\031;pef\004\"AC\013\007\t1\021\001AF\013\003/\005\032R!\006\r\035U=\002\"!\007\016\016\003\031I!a\007\004\003\r\005s\027PU3g!\rQQdH\005\003=\t\021\021bU8si\026$7+\032;\021\005\001\nC\002\001\003\006EU\021\ra\t\002\002\003F\021Ae\n\t\0033\025J!A\n\004\003\0179{G\017[5oOB\021\021\004K\005\003S\031\0211!\0218z!\021YCf\b\030\016\003\021I!!\f\003\003\033M{'\017^3e'\026$H*[6f!\rQQc\b\t\0033AJ!!\r\004\003\031M+'/[1mSj\f'\r\\3\t\021M*\"\021!Q\001\nQ\nA\001\036:fKB!Q\007O\020<\035\tQa'\003\0028\005\005a!+\0323CY\006\0347\016\026:fK&\021\021H\017\002\005)J,WM\003\0028\005A\021\021\004P\005\003{\031\021A!\0268ji\"Aq(\006BC\002\023\r\001)\001\005pe\022,'/\0338h+\005\t\005c\001\"K?9\0211\t\023\b\003\t\036k\021!\022\006\003\r\"\ta\001\020:p_Rt\024\"A\004\n\005%3\021a\0029bG.\fw-Z\005\003\0272\023\001b\024:eKJLgn\032\006\003\023\032A\001BT\013\003\002\003\006I!Q\001\n_J$WM]5oO\002BQ\001U\013\005\nE\013a\001P5oSRtDC\001*U)\tq3\013C\003@\037\002\017\021\tC\0034\037\002\007A\007C\003W+\021\005s+\001\007tiJLgn\032)sK\032L\0070F\001Y!\tIf,D\001[\025\tYF,\001\003mC:<'\"A/\002\t)\fg/Y\005\003?j\023aa\025;sS:<\007\"B1\026\t\003\022\027\001B:ju\026,\022a\031\t\0033\021L!!\032\004\003\007%sG\017C\003h+\021\005\003.\001\003iK\006$W#A\020\t\013),B\021I6\002\025!,\027\rZ(qi&|g.F\001m!\rIRnH\005\003]\032\021aa\0249uS>t\007\"\0029\026\t\003B\027\001\0027bgRDQA]\013\005B-\f!\002\\1ti>\003H/[8o\021\025!X\003\"\021v\003\021!\030-\0337\026\0039BQa^\013\005BU\fA!\0338ji\")\0210\006C!u\006!AM]8q)\tq3\020C\003}q\002\0071-A\001o\021\025qX\003\"\021\000\003\021!\030m[3\025\0079\n\t\001C\003}{\002\0071\rC\004\002\006U!\t%a\002\002\013Md\027nY3\025\0139\nI!!\004\t\017\005-\0211\001a\001G\006!aM]8n\021\035\ty!a\001A\002\r\fQ!\0368uS2Dq!a\005\026\t\003\n)\"A\005ee>\004(+[4iiR\031a&a\006\t\rq\f\t\0021\001d\021\035\tY\"\006C!\003;\t\021\002^1lKJKw\r\033;\025\0079\ny\002\003\004}\0033\001\ra\031\005\b\003G)B\021IA\023\003\035\031\b\017\\5u\003R$B!a\n\002.A)\021$!\013/]%\031\0211\006\004\003\rQ+\b\017\\33\021\031a\030\021\005a\001G\"A\021\021G\013!\n\023\t\031$\001\006d_VtGo\0265jY\026$2aYA\033\021!\t9$a\fA\002\005e\022!\0019\021\re\tYdHA \023\r\tiD\002\002\n\rVt7\r^5p]F\0022!GA!\023\r\t\031E\002\002\b\005>|G.Z1o\021\035\t9%\006C!\003\023\n\021\002\032:pa^C\027\016\\3\025\0079\nY\005\003\005\0028\005\025\003\031AA\035\021\035\ty%\006C!\003#\n\021\002^1lK^C\027\016\\3\025\0079\n\031\006\003\005\0028\0055\003\031AA\035\021\035\t9&\006C!\0033\nAa\0359b]R!\021qEA.\021!\t9$!\026A\002\005e\002bBA0+\021\005\021\021M\001\nSN\034V.\0317mKJ$b!a\020\002d\005\035\004bBA3\003;\002\raH\001\002q\"9\021\021NA/\001\004y\022!A=)\021\005u\023QNA:\003o\0022!GA8\023\r\t\tH\002\002\013I\026\004(/Z2bi\026$\027EAA;\003e)8/\032\021a_J$WM]5oO:bG\017\031\021j]N$X-\0313\"\005\005e\024A\002\032/cAr\003\007\003\004Q+\021\005\021Q\020\013\003\003\"2ALAA\021\031y\0241\020a\002\003\"9\021QQ\013\005\n\005\035\025A\0028foN+G\017F\002/\003\023Cq!a#\002\004\002\007A'A\001u\021\031\ty)\006C!k\006)Q-\0349us\"9\0211S\013\005\002\005U\025!\002\023qYV\034Hc\001\030\002\030\"9\021\021TAI\001\004y\022\001B3mK6Dq!!(\026\t\003\ty*\001\004j]N,'\017\036\013\004]\005\005\006bBAM\0037\003\ra\b\005\b\003K+B\021AAT\003\031!S.\0338vgR\031a&!+\t\017\005e\0251\025a\001?!9\021QV\013\005\002\005=\026\001C2p]R\f\027N\\:\025\t\005}\022\021\027\005\b\0033\013Y\0131\001 \021\035\t),\006C\001\003o\013\001\"\033;fe\006$xN]\013\003\003s\003BaKA^?%\031\021Q\030\003\003\021%#XM]1u_JDq!!1\026\t\003\n\031-A\004g_J,\027m\0315\026\t\005\025\027q\032\013\004w\005\035\007\002CAe\003\003\r!a3\002\003\031\004b!GA\036?\0055\007c\001\021\002P\0229\021\021[A`\005\004\031#!A+\t\017\005UW\003\"\021\002X\006I!/\0318hK&k\007\017\034\013\006]\005e\0271\034\005\b\003\027\t\031\0161\001m\021\035\ty!a5A\0021Dq!a8\026\t\003\n\t/A\003sC:<W\rF\003/\003G\f)\017C\004\002\f\005u\007\031A\020\t\017\005=\021Q\034a\001?!9\0211B\013\005B\005%Hc\001\030\002l\"9\0211BAt\001\004y\002bBAx+\021\005\023\021_\001\003i>$2ALAz\021\035\ty/!<A\002}Aq!a\004\026\t\003\n9\020F\002/\003sDq!a\004\002v\002\007q\004\003\004\002~V!\t\005[\001\tM&\0248\017^&fs\"1!\021A\013\005B!\fq\001\\1ti.+\027\020K\003\026\005\013\021Y\001E\002\032\005\017I1A!\003\007\005A\031VM]5bYZ+'o]5p]VKEI\b\00520U\003\003h7-\f\021\031\0016\002\"\001\003\020Q\t\021\002C\004\003\024-!\031A!\006\002\037%l\007\017\\5dSR\024U/\0337eKJ,BAa\006\003(Q!!\021\004B\026!!\021YB!\t\003&\t%RB\001B\017\025\r\021y\002B\001\b[V$\030M\0317f\023\021\021\031C!\b\003\017\t+\030\016\0343feB\031\001Ea\n\005\r\t\022\tB1\001$!\021QQC!\n\t\017}\022\t\002q\001\003.A!!I\023B\023\021\035\021\td\003C!\005g\t!B\\3x\005VLG\016Z3s+\021\021)Da\017\025\t\t]\"q\b\t\t\0057\021\tC!\017\003>A\031\001Ea\017\005\r\t\022yC1\001$!\021QQC!\017\t\017}\022y\003q\001\003BA!!I\023B\035\021\035\tyi\003C\001\005\013*BAa\022\003NQ!!\021\nB(!\021QQCa\023\021\007\001\022i\005\002\004#\005\007\022\ra\t\005\b\t\r\0039\001B)!\021\021%Ja\023\t\023\tU3\"!A\005\n\t]\023a\003:fC\022\024Vm]8mm\026$\"A!\027\021\007e\023Y&C\002\003^i\023aa\0242kK\016$\b")
/*     */ public class TreeSet<A> implements SortedSet<A>, SortedSetLike<A, TreeSet<A>>, Serializable {
/*     */   public static final long serialVersionUID = -5685982407650748405L;
/*     */   
/*     */   private final RedBlackTree.Tree<A, BoxedUnit> tree;
/*     */   
/*     */   private final Ordering<A> ordering;
/*     */   
/*     */   public boolean scala$collection$SortedSetLike$$super$subsetOf(GenSet that) {
/*  51 */     return GenSetLike.class.subsetOf((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public TreeSet<A> keySet() {
/*  51 */     return (TreeSet<A>)SortedSetLike.class.keySet(this);
/*     */   }
/*     */   
/*     */   public boolean subsetOf(GenSet that) {
/*  51 */     return SortedSetLike.class.subsetOf(this, that);
/*     */   }
/*     */   
/*     */   public int compare(Object k0, Object k1) {
/*  51 */     return Sorted.class.compare((Sorted)this, k0, k1);
/*     */   }
/*     */   
/*     */   public boolean hasAll(Iterator j) {
/*  51 */     return Sorted.class.hasAll((Sorted)this, j);
/*     */   }
/*     */   
/*     */   public GenericCompanion<Set> companion() {
/*  51 */     return Set$class.companion(this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/*  51 */     return Set$class.toSet(this);
/*     */   }
/*     */   
/*     */   public Set<A> seq() {
/*  51 */     return Set$class.seq(this);
/*     */   }
/*     */   
/*     */   public Combiner<A, ParSet<A>> parCombiner() {
/*  51 */     return Set$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
/*  51 */     return TraversableLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public Builder<A, TreeSet<A>> newBuilder() {
/*  51 */     return SetLike.class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public Seq<A> toSeq() {
/*  51 */     return SetLike.class.toSeq(this);
/*     */   }
/*     */   
/*     */   public <A1> Buffer<A1> toBuffer() {
/*  51 */     return SetLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  51 */     return (That)SetLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $plus(Object elem1, Object elem2, Seq elems) {
/*  51 */     return (TreeSet<A>)SetLike.class.$plus(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $plus$plus(GenTraversableOnce elems) {
/*  51 */     return (TreeSet<A>)SetLike.class.$plus$plus(this, elems);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  51 */     return SetLike.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public TreeSet<A> union(GenSet that) {
/*  51 */     return (TreeSet<A>)SetLike.class.union(this, that);
/*     */   }
/*     */   
/*     */   public TreeSet<A> diff(GenSet that) {
/*  51 */     return (TreeSet<A>)SetLike.class.diff(this, that);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> subsets(int len) {
/*  51 */     return SetLike.class.subsets(this, len);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> subsets() {
/*  51 */     return SetLike.class.subsets(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  51 */     return SetLike.class.toString(this);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $minus(Object elem1, Object elem2, Seq elems) {
/*  51 */     return (TreeSet<A>)Subtractable.class.$minus((Subtractable)this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $minus$minus(GenTraversableOnce xs) {
/*  51 */     return (TreeSet<A>)Subtractable.class.$minus$minus((Subtractable)this, xs);
/*     */   }
/*     */   
/*     */   public boolean apply(Object elem) {
/*  51 */     return GenSetLike.class.apply((GenSetLike)this, elem);
/*     */   }
/*     */   
/*     */   public TreeSet<A> intersect(GenSet that) {
/*  51 */     return (TreeSet<A>)GenSetLike.class.intersect((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $amp(GenSet that) {
/*  51 */     return (TreeSet<A>)GenSetLike.class.$amp((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $bar(GenSet that) {
/*  51 */     return (TreeSet<A>)GenSetLike.class.$bar((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public TreeSet<A> $amp$tilde(GenSet that) {
/*  51 */     return (TreeSet<A>)GenSetLike.class.$amp$tilde((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  51 */     return GenSetLike.class.equals((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  51 */     return GenSetLike.class.hashCode((GenSetLike)this);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZD$sp(double v1) {
/*  51 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDD$sp(double v1) {
/*  51 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFD$sp(double v1) {
/*  51 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcID$sp(double v1) {
/*  51 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJD$sp(double v1) {
/*  51 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVD$sp(double v1) {
/*  51 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZF$sp(float v1) {
/*  51 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDF$sp(float v1) {
/*  51 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFF$sp(float v1) {
/*  51 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIF$sp(float v1) {
/*  51 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJF$sp(float v1) {
/*  51 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVF$sp(float v1) {
/*  51 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZI$sp(int v1) {
/*  51 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDI$sp(int v1) {
/*  51 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFI$sp(int v1) {
/*  51 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcII$sp(int v1) {
/*  51 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJI$sp(int v1) {
/*  51 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVI$sp(int v1) {
/*  51 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZJ$sp(long v1) {
/*  51 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDJ$sp(long v1) {
/*  51 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFJ$sp(long v1) {
/*  51 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIJ$sp(long v1) {
/*  51 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJJ$sp(long v1) {
/*  51 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVJ$sp(long v1) {
/*  51 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose(Function1 g) {
/*  51 */     return Function1.class.compose((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  51 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, A> andThen(Function1 g) {
/*  51 */     return Function1.class.andThen((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  51 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public Iterable<A> thisCollection() {
/*  51 */     return IterableLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public Iterable<A> toCollection(Object repr) {
/*  51 */     return IterableLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  51 */     return IterableLike.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  51 */     return IterableLike.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public Option<A> find(Function1 p) {
/*  51 */     return IterableLike.class.find(this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/*  51 */     return (B)IterableLike.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  51 */     return (B)IterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public Iterable<A> toIterable() {
/*  51 */     return IterableLike.class.toIterable(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> toIterator() {
/*  51 */     return IterableLike.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> grouped(int size) {
/*  51 */     return IterableLike.class.grouped(this, size);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> sliding(int size) {
/*  51 */     return IterableLike.class.sliding(this, size);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> sliding(int size, int step) {
/*  51 */     return IterableLike.class.sliding(this, size, step);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/*  51 */     IterableLike.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  51 */     return (That)IterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  51 */     return (That)IterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  51 */     return (That)IterableLike.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  51 */     return IterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public Stream<A> toStream() {
/*  51 */     return IterableLike.class.toStream(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object that) {
/*  51 */     return IterableLike.class.canEqual(this, that);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  51 */     return IterableLike.class.view(this);
/*     */   }
/*     */   
/*     */   public IterableView<A, TreeSet<A>> view(int from, int until) {
/*  51 */     return IterableLike.class.view(this, from, until);
/*     */   }
/*     */   
/*     */   public <B> Builder<B, Set<B>> genericBuilder() {
/*  51 */     return GenericTraversableTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<Set<A1>, Set<A2>> unzip(Function1 asPair) {
/*  51 */     return GenericTraversableTemplate.class.unzip(this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<Set<A1>, Set<A2>, Set<A3>> unzip3(Function1 asTriple) {
/*  51 */     return GenericTraversableTemplate.class.unzip3(this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> Set<B> flatten(Function1 asTraversable) {
/*  51 */     return (Set<B>)GenericTraversableTemplate.class.flatten(this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> Set<Set<B>> transpose(Function1 asTraversable) {
/*  51 */     return (Set<Set<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */   }
/*     */   
/*     */   public TreeSet<A> repr() {
/*  51 */     return (TreeSet<A>)TraversableLike.class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/*  51 */     return TraversableLike.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  51 */     return TraversableLike.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  51 */     return (That)TraversableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/*  51 */     return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/*  51 */     return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  51 */     return (That)TraversableLike.class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public TreeSet<A> filter(Function1 p) {
/*  51 */     return (TreeSet<A>)TraversableLike.class.filter(this, p);
/*     */   }
/*     */   
/*     */   public TreeSet<A> filterNot(Function1 p) {
/*  51 */     return (TreeSet<A>)TraversableLike.class.filterNot(this, p);
/*     */   }
/*     */   
/*     */   public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  51 */     return (That)TraversableLike.class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<TreeSet<A>, TreeSet<A>> partition(Function1 p) {
/*  51 */     return TraversableLike.class.partition(this, p);
/*     */   }
/*     */   
/*     */   public <K> Map<K, TreeSet<A>> groupBy(Function1 f) {
/*  51 */     return TraversableLike.class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/*  51 */     return (That)TraversableLike.class.scan(this, z, op, cbf);
/*     */   }
/*     */   
/*     */   public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  51 */     return (That)TraversableLike.class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  51 */     return (That)TraversableLike.class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public TreeSet<A> sliceWithKnownDelta(int from, int until, int delta) {
/*  51 */     return (TreeSet<A>)TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
/*     */   }
/*     */   
/*     */   public TreeSet<A> sliceWithKnownBound(int from, int until) {
/*  51 */     return (TreeSet<A>)TraversableLike.class.sliceWithKnownBound(this, from, until);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> tails() {
/*  51 */     return TraversableLike.class.tails(this);
/*     */   }
/*     */   
/*     */   public Iterator<TreeSet<A>> inits() {
/*  51 */     return TraversableLike.class.inits(this);
/*     */   }
/*     */   
/*     */   public Traversable<A> toTraversable() {
/*  51 */     return TraversableLike.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/*  51 */     return (Col)TraversableLike.class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public FilterMonadic<A, TreeSet<A>> withFilter(Function1 p) {
/*  51 */     return TraversableLike.class.withFilter(this, p);
/*     */   }
/*     */   
/*     */   public ParSet<A> par() {
/*  51 */     return (ParSet<A>)Parallelizable.class.par(this);
/*     */   }
/*     */   
/*     */   public List<A> reversed() {
/*  51 */     return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  51 */     return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  51 */     return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */   }
/*     */   
/*     */   public <B> Option<B> collectFirst(PartialFunction pf) {
/*  51 */     return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */   }
/*     */   
/*     */   public <B> B $div$colon(Object z, Function2 op) {
/*  51 */     return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B $colon$bslash(Object z, Function2 op) {
/*  51 */     return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/*  51 */     return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/*  51 */     return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceLeftOption(Function2 op) {
/*  51 */     return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceRightOption(Function2 op) {
/*  51 */     return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 reduce(Function2 op) {
/*  51 */     return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> Option<A1> reduceOption(Function2 op) {
/*  51 */     return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 fold(Object z, Function2 op) {
/*  51 */     return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  51 */     return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <B> B sum(Numeric num) {
/*  51 */     return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> B product(Numeric num) {
/*  51 */     return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> A min(Ordering cmp) {
/*  51 */     return (A)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> A max(Ordering cmp) {
/*  51 */     return (A)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> A maxBy(Function1 f, Ordering cmp) {
/*  51 */     return (A)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> A minBy(Function1 f, Ordering cmp) {
/*  51 */     return (A)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> void copyToBuffer(Buffer dest) {
/*  51 */     TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start) {
/*  51 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs) {
/*  51 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */   }
/*     */   
/*     */   public <B> Object toArray(ClassTag evidence$1) {
/*  51 */     return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/*  51 */     return TraversableOnce.class.toList((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> toIndexedSeq() {
/*  51 */     return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Vector<A> toVector() {
/*  51 */     return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  51 */     return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  51 */     return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  51 */     return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  51 */     return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  51 */     return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String sep) {
/*  51 */     return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b) {
/*  51 */     return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  51 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public Ordering<A> ordering() {
/*  51 */     return this.ordering;
/*     */   }
/*     */   
/*     */   private TreeSet(RedBlackTree.Tree<A, BoxedUnit> tree, Ordering<A> ordering) {
/*  51 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  51 */     TraversableOnce.class.$init$((TraversableOnce)this);
/*  51 */     Parallelizable.class.$init$(this);
/*  51 */     TraversableLike.class.$init$(this);
/*  51 */     GenericTraversableTemplate.class.$init$(this);
/*  51 */     GenTraversable.class.$init$((GenTraversable)this);
/*  51 */     Traversable.class.$init$(this);
/*  51 */     Traversable$class.$init$(this);
/*  51 */     GenIterable.class.$init$((GenIterable)this);
/*  51 */     IterableLike.class.$init$(this);
/*  51 */     Iterable.class.$init$(this);
/*  51 */     Iterable$class.$init$(this);
/*  51 */     Function1.class.$init$((Function1)this);
/*  51 */     GenSetLike.class.$init$((GenSetLike)this);
/*  51 */     GenericSetTemplate.class.$init$(this);
/*  51 */     GenSet.class.$init$((GenSet)this);
/*  51 */     Subtractable.class.$init$((Subtractable)this);
/*  51 */     SetLike.class.$init$(this);
/*  51 */     Set.class.$init$(this);
/*  51 */     Set$class.$init$(this);
/*  51 */     Sorted.class.$init$((Sorted)this);
/*  51 */     SortedSetLike.class.$init$(this);
/*  51 */     SortedSet.class.$init$(this);
/*  51 */     SortedSet$class.$init$(this);
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  54 */     return "TreeSet";
/*     */   }
/*     */   
/*     */   public int size() {
/*  56 */     return RedBlackTree$.MODULE$.count(this.tree);
/*     */   }
/*     */   
/*     */   public A head() {
/*  58 */     return (A)RedBlackTree$.MODULE$.<A, BoxedUnit>smallest(this.tree).key();
/*     */   }
/*     */   
/*     */   public Option<A> headOption() {
/*  59 */     return RedBlackTree$.MODULE$.isEmpty(this.tree) ? (Option<A>)None$.MODULE$ : (Option<A>)new Some(head());
/*     */   }
/*     */   
/*     */   public A last() {
/*  60 */     return (A)RedBlackTree$.MODULE$.<A, BoxedUnit>greatest(this.tree).key();
/*     */   }
/*     */   
/*     */   public Option<A> lastOption() {
/*  61 */     return RedBlackTree$.MODULE$.isEmpty(this.tree) ? (Option<A>)None$.MODULE$ : (Option<A>)new Some(last());
/*     */   }
/*     */   
/*     */   public TreeSet<A> tail() {
/*  63 */     return new TreeSet(RedBlackTree$.MODULE$.delete(this.tree, firstKey(), ordering()), ordering());
/*     */   }
/*     */   
/*     */   public TreeSet<A> init() {
/*  64 */     return new TreeSet(RedBlackTree$.MODULE$.delete(this.tree, lastKey(), ordering()), ordering());
/*     */   }
/*     */   
/*     */   public TreeSet<A> drop(int n) {
/*  67 */     return (n <= 0) ? this : (
/*  68 */       (n >= size()) ? empty() : 
/*  69 */       newSet(RedBlackTree$.MODULE$.drop(this.tree, n, ordering())));
/*     */   }
/*     */   
/*     */   public TreeSet<A> take(int n) {
/*  73 */     return (n <= 0) ? empty() : (
/*  74 */       (n >= size()) ? this : 
/*  75 */       newSet(RedBlackTree$.MODULE$.take(this.tree, n, ordering())));
/*     */   }
/*     */   
/*     */   public TreeSet<A> slice(int from, int until) {
/*  79 */     return (until <= from) ? empty() : (
/*  80 */       (from <= 0) ? take(until) : (
/*  81 */       (until >= size()) ? drop(from) : 
/*  82 */       newSet(RedBlackTree$.MODULE$.slice(this.tree, from, until, ordering()))));
/*     */   }
/*     */   
/*     */   public TreeSet<A> dropRight(int n) {
/*  85 */     return take(size() - n);
/*     */   }
/*     */   
/*     */   public TreeSet<A> takeRight(int n) {
/*  86 */     return drop(size() - n);
/*     */   }
/*     */   
/*     */   public Tuple2<TreeSet<A>, TreeSet<A>> splitAt(int n) {
/*  87 */     return new Tuple2(take(n), drop(n));
/*     */   }
/*     */   
/*     */   private int countWhile(Function1 p) {
/*  90 */     int result = 0;
/*  91 */     Iterator<A> it = iterator();
/*  92 */     for (; it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(it.next())); result++);
/*  93 */     return result;
/*     */   }
/*     */   
/*     */   public TreeSet<A> dropWhile(Function1<A, Object> p) {
/*  95 */     return drop(countWhile(p));
/*     */   }
/*     */   
/*     */   public TreeSet<A> takeWhile(Function1<A, Object> p) {
/*  96 */     return take(countWhile(p));
/*     */   }
/*     */   
/*     */   public Tuple2<TreeSet<A>, TreeSet<A>> span(Function1<A, Object> p) {
/*  97 */     return splitAt(countWhile(p));
/*     */   }
/*     */   
/*     */   public boolean isSmaller(Object x, Object y) {
/* 100 */     return (compare((A)x, (A)y) < 0);
/*     */   }
/*     */   
/*     */   public TreeSet(Ordering<A> ordering) {
/* 102 */     this(null, ordering);
/*     */   }
/*     */   
/*     */   private TreeSet<A> newSet(RedBlackTree.Tree<A, BoxedUnit> t) {
/* 104 */     return new TreeSet(t, ordering());
/*     */   }
/*     */   
/*     */   public TreeSet<A> empty() {
/* 108 */     return TreeSet$.MODULE$.empty(ordering());
/*     */   }
/*     */   
/*     */   public TreeSet<A> $plus(Object elem) {
/* 115 */     return newSet(RedBlackTree$.MODULE$.update(this.tree, (A)elem, BoxedUnit.UNIT, false, ordering()));
/*     */   }
/*     */   
/*     */   public TreeSet<A> insert(Object elem) {
/* 124 */     Predef$.MODULE$.assert(!RedBlackTree$.MODULE$.contains(this.tree, (A)elem, ordering()));
/* 125 */     return newSet(RedBlackTree$.MODULE$.update(this.tree, (A)elem, BoxedUnit.UNIT, false, ordering()));
/*     */   }
/*     */   
/*     */   public TreeSet<A> $minus(Object elem) {
/* 134 */     return RedBlackTree$.MODULE$.contains(this.tree, (A)elem, ordering()) ? 
/* 135 */       newSet(RedBlackTree$.MODULE$.delete(this.tree, (A)elem, ordering())) : this;
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/* 142 */     return RedBlackTree$.MODULE$.contains(this.tree, (A)elem, ordering());
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/* 149 */     return RedBlackTree$.MODULE$.keysIterator(this.tree);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1<A, ?> f) {
/* 151 */     RedBlackTree$.MODULE$.foreachKey(this.tree, f);
/*     */   }
/*     */   
/*     */   public TreeSet<A> rangeImpl(Option<A> from, Option<A> until) {
/* 153 */     return newSet(RedBlackTree$.MODULE$.rangeImpl(this.tree, from, until, ordering()));
/*     */   }
/*     */   
/*     */   public TreeSet<A> range(Object from, Object until) {
/* 154 */     return newSet(RedBlackTree$.MODULE$.range(this.tree, (A)from, (A)until, ordering()));
/*     */   }
/*     */   
/*     */   public TreeSet<A> from(Object from) {
/* 155 */     return newSet(RedBlackTree$.MODULE$.from(this.tree, (A)from, ordering()));
/*     */   }
/*     */   
/*     */   public TreeSet<A> to(Object to) {
/* 156 */     return newSet(RedBlackTree$.MODULE$.to(this.tree, (A)to, ordering()));
/*     */   }
/*     */   
/*     */   public TreeSet<A> until(Object until) {
/* 157 */     return newSet(RedBlackTree$.MODULE$.until(this.tree, (A)until, ordering()));
/*     */   }
/*     */   
/*     */   public A firstKey() {
/* 159 */     return head();
/*     */   }
/*     */   
/*     */   public A lastKey() {
/* 160 */     return last();
/*     */   }
/*     */   
/*     */   public static <A> Builder<A, TreeSet<A>> implicitBuilder(Ordering<A> paramOrdering) {
/*     */     return TreeSet$.MODULE$.implicitBuilder(paramOrdering);
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<TreeSet<?>, A, TreeSet<A>> newCanBuildFrom(Ordering<A> paramOrdering) {
/*     */     return TreeSet$.MODULE$.newCanBuildFrom(paramOrdering);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\TreeSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */