/*     */ package akka.util;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSeqLike;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.SeqView;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Traversable;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParIterable;
/*     */ import scala.collection.parallel.immutable.ParSeq;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\rsAB\001\003\021\003!a!A\006D_2dWm\031;j_:\034(BA\002\005\003\021)H/\0337\013\003\025\tA!Y6lCB\021q\001C\007\002\005\0311\021B\001E\001\t)\0211bQ8mY\026\034G/[8ogN\021\001b\003\t\003\031=i\021!\004\006\002\035\005)1oY1mC&\021\001#\004\002\007\003:L(+\0324\t\013IAA\021\001\013\002\rqJg.\033;?\007\001!\022AB\004\006-!A\tiF\001\022\0136\004H/_%n[V$\030M\0317f'\026\f\bC\001\r\032\033\005Aa!\002\016\t\021\003[\"!E#naRL\030*\\7vi\006\024G.Z*fcN)\021d\003\017(UA\031QD\t\023\016\003yQ!a\b\021\002\023%lW.\036;bE2,'BA\021\016\003)\031w\016\0347fGRLwN\\\005\003Gy\0211aU3r!\taQ%\003\002'\033\t9aj\034;iS:<\007C\001\007)\023\tISBA\004Qe>$Wo\031;\021\0051Y\023B\001\027\016\0051\031VM]5bY&T\030M\0317f\021\025\021\022\004\"\001/)\0059\002\"\002\031\032\t\013\n\024\001C5uKJ\fGo\034:\026\003I\0022a\r\033%\033\005\001\023BA\033!\005!IE/\032:bi>\024\b\"B\034\032\t\013B\024!B1qa2LHC\001\023:\021\025Qd\0071\001<\003\rIG\r\037\t\003\031qJ!!P\007\003\007%sG\017C\003@3\021\025\003)\001\004mK:<G\017[\013\002w!9!)GA\001\n\003\032\025!\0049s_\022,8\r\036)sK\032L\0070F\001E!\t)%*D\001G\025\t9\005*\001\003mC:<'\"A%\002\t)\fg/Y\005\003\027\032\023aa\025;sS:<\007bB'\032\003\003%\t\001Q\001\raJ|G-^2u\003JLG/\037\005\b\037f\t\t\021\"\001Q\0039\001(o\0343vGR,E.Z7f]R$\"!\025+\021\0051\021\026BA*\016\005\r\te.\037\005\b+:\013\t\0211\001<\003\rAH%\r\005\b/f\t\t\021\"\021Y\003=\001(o\0343vGRLE/\032:bi>\024X#A-\021\007M\"\024\013C\004\\3\005\005I\021\002/\002\027I,\027\r\032*fg>dg/\032\013\002;B\021QIX\005\003?\032\023aa\0242kK\016$h!B1\t\003\003\021'A\b)beRL\027\r\\%n[V$\030M\0317f-\006dW/Z:Ji\026\024\030M\0317f+\r\031\007/[\n\004A.!\007cA\017fO&\021aM\b\002\t\023R,'/\0312mKB\021\001.\033\007\001\t\025Q\007M1\001l\005\t!v.\005\002%#\")!\003\031C\001[R\ta\016\005\003\031A><\007C\0015q\t\025\t\bM1\001l\005\0211%o\\7\t\013M\004g\021\001;\002\027%\034H)\0324j]\026$\027\t\036\013\003kb\004\"\001\004<\n\005]l!a\002\"p_2,\027M\034\005\006sJ\004\ra\\\001\005MJ|W\016C\0038A\032\0051\020\006\002hy\")\021P\037a\001_\")a\020\031D\001\006qa/\0317vKNLE/\032:bi>\024XCAA\001!\025\t\031!a\005p\035\021\t)!a\004\017\t\005\035\021QB\007\003\003\023Q1!a\003\024\003\031a$o\\8u}%\ta\"C\002\002\0225\tq\001]1dW\006<W-C\0026\003+Q1!!\005\016\021\031\001\004\r\"\002\002\032U\021\0211\004\t\006\003\007\t\031b\032\005\n\003?\001\007R1A\005B\001\013Aa]5{K\"I\0211\0051\t\002\003\006KaO\001\006g&TX\r\t\005\b\003O\001G\021IA\025\003\0351wN]3bG\",B!a\013\002@Q!\021QFA\032!\ra\021qF\005\004\003ci!\001B+oSRD\001\"!\016\002&\001\007\021qG\001\002MB1A\"!\017h\003{I1!a\017\016\005%1UO\\2uS>t\027\007E\002i\003!q!!\021\002&\t\0071NA\001D\001")
/*     */ public final class Collections {
/*     */   public static class EmptyImmutableSeq$ implements Seq<scala.runtime.Nothing$>, Product, Serializable {
/*     */     public static final EmptyImmutableSeq$ MODULE$;
/*     */     
/*     */     public GenericCompanion<Seq> companion() {
/*  15 */       return Seq.class.companion(this);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> toSeq() {
/*  15 */       return Seq.class.toSeq(this);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> seq() {
/*  15 */       return Seq.class.seq(this);
/*     */     }
/*     */     
/*     */     public Combiner<scala.runtime.Nothing$, ParSeq<scala.runtime.Nothing$>> parCombiner() {
/*  15 */       return Seq.class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> thisCollection() {
/*  15 */       return SeqLike.class.thisCollection((SeqLike)this);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> toCollection(Object repr) {
/*  15 */       return SeqLike.class.toCollection((SeqLike)this, repr);
/*     */     }
/*     */     
/*     */     public int lengthCompare(int len) {
/*  15 */       return SeqLike.class.lengthCompare((SeqLike)this, len);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  15 */       return SeqLike.class.isEmpty((SeqLike)this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  15 */       return SeqLike.class.size((SeqLike)this);
/*     */     }
/*     */     
/*     */     public int segmentLength(Function1 p, int from) {
/*  15 */       return SeqLike.class.segmentLength((SeqLike)this, p, from);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p, int from) {
/*  15 */       return SeqLike.class.indexWhere((SeqLike)this, p, from);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p, int end) {
/*  15 */       return SeqLike.class.lastIndexWhere((SeqLike)this, p, end);
/*     */     }
/*     */     
/*     */     public Iterator<Seq<scala.runtime.Nothing$>> permutations() {
/*  15 */       return SeqLike.class.permutations((SeqLike)this);
/*     */     }
/*     */     
/*     */     public Iterator<Seq<scala.runtime.Nothing$>> combinations(int n) {
/*  15 */       return SeqLike.class.combinations((SeqLike)this, n);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> reverse() {
/*  15 */       return (Seq<scala.runtime.Nothing$>)SeqLike.class.reverse((SeqLike)this);
/*     */     }
/*     */     
/*     */     public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/*  15 */       return (That)SeqLike.class.reverseMap((SeqLike)this, f, bf);
/*     */     }
/*     */     
/*     */     public Iterator<scala.runtime.Nothing$> reverseIterator() {
/*  15 */       return SeqLike.class.reverseIterator((SeqLike)this);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that, int offset) {
/*  15 */       return SeqLike.class.startsWith((SeqLike)this, that, offset);
/*     */     }
/*     */     
/*     */     public <B> boolean endsWith(GenSeq that) {
/*  15 */       return SeqLike.class.endsWith((SeqLike)this, that);
/*     */     }
/*     */     
/*     */     public <B> int indexOfSlice(GenSeq that) {
/*  15 */       return SeqLike.class.indexOfSlice((SeqLike)this, that);
/*     */     }
/*     */     
/*     */     public <B> int indexOfSlice(GenSeq that, int from) {
/*  15 */       return SeqLike.class.indexOfSlice((SeqLike)this, that, from);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOfSlice(GenSeq that) {
/*  15 */       return SeqLike.class.lastIndexOfSlice((SeqLike)this, that);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOfSlice(GenSeq that, int end) {
/*  15 */       return SeqLike.class.lastIndexOfSlice((SeqLike)this, that, end);
/*     */     }
/*     */     
/*     */     public <B> boolean containsSlice(GenSeq that) {
/*  15 */       return SeqLike.class.containsSlice((SeqLike)this, that);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  15 */       return SeqLike.class.contains((SeqLike)this, elem);
/*     */     }
/*     */     
/*     */     public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/*  15 */       return (That)SeqLike.class.union((SeqLike)this, that, bf);
/*     */     }
/*     */     
/*     */     public <B> Seq<scala.runtime.Nothing$> diff(GenSeq that) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)SeqLike.class.diff((SeqLike)this, that);
/*     */     }
/*     */     
/*     */     public <B> Seq<scala.runtime.Nothing$> intersect(GenSeq that) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)SeqLike.class.intersect((SeqLike)this, that);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> distinct() {
/*  15 */       return (Seq<scala.runtime.Nothing$>)SeqLike.class.distinct((SeqLike)this);
/*     */     }
/*     */     
/*     */     public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/*  15 */       return (That)SeqLike.class.patch((SeqLike)this, from, patch, replaced, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/*  15 */       return (That)SeqLike.class.updated((SeqLike)this, index, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/*  15 */       return (That)SeqLike.class.$plus$colon((SeqLike)this, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/*  15 */       return (That)SeqLike.class.$colon$plus((SeqLike)this, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/*  15 */       return (That)SeqLike.class.padTo((SeqLike)this, len, elem, bf);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenSeq that, Function2 p) {
/*  15 */       return SeqLike.class.corresponds((SeqLike)this, that, p);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> sortWith(Function2 lt) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)SeqLike.class.sortWith((SeqLike)this, lt);
/*     */     }
/*     */     
/*     */     public <B> Seq<scala.runtime.Nothing$> sortBy(Function1 f, Ordering ord) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)SeqLike.class.sortBy((SeqLike)this, f, ord);
/*     */     }
/*     */     
/*     */     public <B> Seq<scala.runtime.Nothing$> sorted(Ordering ord) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)SeqLike.class.sorted((SeqLike)this, ord);
/*     */     }
/*     */     
/*     */     public Range indices() {
/*  15 */       return SeqLike.class.indices((SeqLike)this);
/*     */     }
/*     */     
/*     */     public Object view() {
/*  15 */       return SeqLike.class.view((SeqLike)this);
/*     */     }
/*     */     
/*     */     public SeqView<scala.runtime.Nothing$, Seq<scala.runtime.Nothing$>> view(int from, int until) {
/*  15 */       return SeqLike.class.view((SeqLike)this, from, until);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  15 */       return SeqLike.class.toString((SeqLike)this);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(int idx) {
/*  15 */       return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 p) {
/*  15 */       return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/*  15 */       return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/*  15 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem, int from) {
/*  15 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOf(Object elem) {
/*  15 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOf(Object elem, int end) {
/*  15 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p) {
/*  15 */       return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that) {
/*  15 */       return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  15 */       return GenSeqLike.class.hashCode((GenSeqLike)this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/*  15 */       return GenSeqLike.class.equals((GenSeqLike)this, that);
/*     */     }
/*     */     
/*     */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/*  15 */       return PartialFunction.class.orElse((PartialFunction)this, that);
/*     */     }
/*     */     
/*     */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/*  15 */       return PartialFunction.class.andThen((PartialFunction)this, k);
/*     */     }
/*     */     
/*     */     public Function1<Object, Option<scala.runtime.Nothing$>> lift() {
/*  15 */       return PartialFunction.class.lift((PartialFunction)this);
/*     */     }
/*     */     
/*     */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/*  15 */       return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*     */     }
/*     */     
/*     */     public <U> Function1<Object, Object> runWith(Function1 action) {
/*  15 */       return PartialFunction.class.runWith((PartialFunction)this, action);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/*  15 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/*  15 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/*  15 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/*  15 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/*  15 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/*  15 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/*  15 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/*  15 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/*  15 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/*  15 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/*  15 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/*  15 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/*  15 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/*  15 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/*  15 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/*  15 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/*  15 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/*  15 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/*  15 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/*  15 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/*  15 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/*  15 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/*  15 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/*  15 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, scala.runtime.Nothing$> compose(Function1 g) {
/*  15 */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  15 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  15 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  15 */       IterableLike.class.foreach((IterableLike)this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  15 */       return IterableLike.class.forall((IterableLike)this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  15 */       return IterableLike.class.exists((IterableLike)this, p);
/*     */     }
/*     */     
/*     */     public Option<scala.runtime.Nothing$> find(Function1 p) {
/*  15 */       return IterableLike.class.find((IterableLike)this, p);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  15 */       return (B)IterableLike.class.foldRight((IterableLike)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  15 */       return (B)IterableLike.class.reduceRight((IterableLike)this, op);
/*     */     }
/*     */     
/*     */     public Iterable<scala.runtime.Nothing$> toIterable() {
/*  15 */       return IterableLike.class.toIterable((IterableLike)this);
/*     */     }
/*     */     
/*     */     public Iterator<scala.runtime.Nothing$> toIterator() {
/*  15 */       return IterableLike.class.toIterator((IterableLike)this);
/*     */     }
/*     */     
/*     */     public Object head() {
/*  15 */       return IterableLike.class.head((IterableLike)this);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> slice(int from, int until) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)IterableLike.class.slice((IterableLike)this, from, until);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> take(int n) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)IterableLike.class.take((IterableLike)this, n);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> drop(int n) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)IterableLike.class.drop((IterableLike)this, n);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> takeWhile(Function1 p) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)IterableLike.class.takeWhile((IterableLike)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Seq<scala.runtime.Nothing$>> grouped(int size) {
/*  15 */       return IterableLike.class.grouped((IterableLike)this, size);
/*     */     }
/*     */     
/*     */     public Iterator<Seq<scala.runtime.Nothing$>> sliding(int size) {
/*  15 */       return IterableLike.class.sliding((IterableLike)this, size);
/*     */     }
/*     */     
/*     */     public Iterator<Seq<scala.runtime.Nothing$>> sliding(int size, int step) {
/*  15 */       return IterableLike.class.sliding((IterableLike)this, size, step);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> takeRight(int n) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)IterableLike.class.takeRight((IterableLike)this, n);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> dropRight(int n) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)IterableLike.class.dropRight((IterableLike)this, n);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/*  15 */       IterableLike.class.copyToArray((IterableLike)this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  15 */       return (That)IterableLike.class.zip((IterableLike)this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  15 */       return (That)IterableLike.class.zipAll((IterableLike)this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  15 */       return (That)IterableLike.class.zipWithIndex((IterableLike)this, bf);
/*     */     }
/*     */     
/*     */     public <B> boolean sameElements(GenIterable that) {
/*  15 */       return IterableLike.class.sameElements((IterableLike)this, that);
/*     */     }
/*     */     
/*     */     public Stream<scala.runtime.Nothing$> toStream() {
/*  15 */       return IterableLike.class.toStream((IterableLike)this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/*  15 */       return IterableLike.class.canEqual((IterableLike)this, that);
/*     */     }
/*     */     
/*     */     public Builder<scala.runtime.Nothing$, Seq<scala.runtime.Nothing$>> newBuilder() {
/*  15 */       return GenericTraversableTemplate.class.newBuilder((GenericTraversableTemplate)this);
/*     */     }
/*     */     
/*     */     public <B> Builder<B, Seq<B>> genericBuilder() {
/*  15 */       return GenericTraversableTemplate.class.genericBuilder((GenericTraversableTemplate)this);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<Seq<A1>, Seq<A2>> unzip(Function1 asPair) {
/*  15 */       return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<Seq<A1>, Seq<A2>, Seq<A3>> unzip3(Function1 asTriple) {
/*  15 */       return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */     }
/*     */     
/*     */     public <B> Seq<B> flatten(Function1 asTraversable) {
/*  15 */       return (Seq<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public <B> Seq<Seq<B>> transpose(Function1 asTraversable) {
/*  15 */       return (Seq<Seq<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> repr() {
/*  15 */       return (Seq<scala.runtime.Nothing$>)TraversableLike.class.repr((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/*  15 */       return TraversableLike.class.isTraversableAgain((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  15 */       return TraversableLike.class.hasDefiniteSize((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  15 */       return (That)TraversableLike.class.$plus$plus((TraversableLike)this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/*  15 */       return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/*  15 */       return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  15 */       return (That)TraversableLike.class.map((TraversableLike)this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  15 */       return (That)TraversableLike.class.flatMap((TraversableLike)this, f, bf);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> filter(Function1 p) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)TraversableLike.class.filter((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> filterNot(Function1 p) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)TraversableLike.class.filterNot((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  15 */       return (That)TraversableLike.class.collect((TraversableLike)this, pf, bf);
/*     */     }
/*     */     
/*     */     public Tuple2<Seq<scala.runtime.Nothing$>, Seq<scala.runtime.Nothing$>> partition(Function1 p) {
/*  15 */       return TraversableLike.class.partition((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public <K> Map<K, Seq<scala.runtime.Nothing$>> groupBy(Function1 f) {
/*  15 */       return TraversableLike.class.groupBy((TraversableLike)this, f);
/*     */     }
/*     */     
/*     */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/*  15 */       return (That)TraversableLike.class.scan((TraversableLike)this, z, op, cbf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  15 */       return (That)TraversableLike.class.scanLeft((TraversableLike)this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  15 */       return (That)TraversableLike.class.scanRight((TraversableLike)this, z, op, bf);
/*     */     }
/*     */     
/*     */     public Option<scala.runtime.Nothing$> headOption() {
/*  15 */       return TraversableLike.class.headOption((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> tail() {
/*  15 */       return (Seq<scala.runtime.Nothing$>)TraversableLike.class.tail((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Object last() {
/*  15 */       return TraversableLike.class.last((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Option<scala.runtime.Nothing$> lastOption() {
/*  15 */       return TraversableLike.class.lastOption((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> init() {
/*  15 */       return (Seq<scala.runtime.Nothing$>)TraversableLike.class.init((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> sliceWithKnownDelta(int from, int until, int delta) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)TraversableLike.class.sliceWithKnownDelta((TraversableLike)this, from, until, delta);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> sliceWithKnownBound(int from, int until) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)TraversableLike.class.sliceWithKnownBound((TraversableLike)this, from, until);
/*     */     }
/*     */     
/*     */     public Seq<scala.runtime.Nothing$> dropWhile(Function1 p) {
/*  15 */       return (Seq<scala.runtime.Nothing$>)TraversableLike.class.dropWhile((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Seq<scala.runtime.Nothing$>, Seq<scala.runtime.Nothing$>> span(Function1 p) {
/*  15 */       return TraversableLike.class.span((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Seq<scala.runtime.Nothing$>, Seq<scala.runtime.Nothing$>> splitAt(int n) {
/*  15 */       return TraversableLike.class.splitAt((TraversableLike)this, n);
/*     */     }
/*     */     
/*     */     public Iterator<Seq<scala.runtime.Nothing$>> tails() {
/*  15 */       return TraversableLike.class.tails((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Iterator<Seq<scala.runtime.Nothing$>> inits() {
/*  15 */       return TraversableLike.class.inits((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Traversable<scala.runtime.Nothing$> toTraversable() {
/*  15 */       return TraversableLike.class.toTraversable((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  15 */       return (Col)TraversableLike.class.to((TraversableLike)this, cbf);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/*  15 */       return TraversableLike.class.stringPrefix((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public FilterMonadic<scala.runtime.Nothing$, Seq<scala.runtime.Nothing$>> withFilter(Function1 p) {
/*  15 */       return TraversableLike.class.withFilter((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public ParSeq<scala.runtime.Nothing$> par() {
/*  15 */       return (ParSeq<scala.runtime.Nothing$>)Parallelizable.class.par((Parallelizable)this);
/*     */     }
/*     */     
/*     */     public List<scala.runtime.Nothing$> reversed() {
/*  15 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  15 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  15 */       return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  15 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*  15 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  15 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  15 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  15 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  15 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  15 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/*  15 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  15 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/*  15 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  15 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/*  15 */       return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/*  15 */       return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */     }
/*     */     
/*     */     public <B> scala.runtime.Nothing$ min(Ordering cmp) {
/*  15 */       return (scala.runtime.Nothing$)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */     }
/*     */     
/*     */     public <B> scala.runtime.Nothing$ max(Ordering cmp) {
/*  15 */       return (scala.runtime.Nothing$)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */     }
/*     */     
/*     */     public <B> scala.runtime.Nothing$ maxBy(Function1 f, Ordering cmp) {
/*  15 */       return (scala.runtime.Nothing$)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> scala.runtime.Nothing$ minBy(Function1 f, Ordering cmp) {
/*  15 */       return (scala.runtime.Nothing$)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*  15 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*  15 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*  15 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*  15 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<scala.runtime.Nothing$> toList() {
/*  15 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<scala.runtime.Nothing$> toIndexedSeq() {
/*  15 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*  15 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  15 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<scala.runtime.Nothing$> toVector() {
/*  15 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/*  15 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  15 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  15 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  15 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  15 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*  15 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*  15 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  15 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  15 */       return "EmptyImmutableSeq";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  15 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  15 */       int i = x$1;
/*  15 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  15 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  15 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public EmptyImmutableSeq$() {
/*  15 */       MODULE$ = this;
/*  15 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  15 */       TraversableOnce.class.$init$((TraversableOnce)this);
/*  15 */       Parallelizable.class.$init$((Parallelizable)this);
/*  15 */       TraversableLike.class.$init$((TraversableLike)this);
/*  15 */       GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  15 */       GenTraversable.class.$init$((GenTraversable)this);
/*  15 */       Traversable.class.$init$((Traversable)this);
/*  15 */       Traversable.class.$init$((Traversable)this);
/*  15 */       GenIterable.class.$init$((GenIterable)this);
/*  15 */       IterableLike.class.$init$((IterableLike)this);
/*  15 */       Iterable.class.$init$((Iterable)this);
/*  15 */       Iterable.class.$init$((Iterable)this);
/*  15 */       Function1.class.$init$((Function1)this);
/*  15 */       PartialFunction.class.$init$((PartialFunction)this);
/*  15 */       GenSeqLike.class.$init$((GenSeqLike)this);
/*  15 */       GenSeq.class.$init$((GenSeq)this);
/*  15 */       SeqLike.class.$init$((SeqLike)this);
/*  15 */       Seq.class.$init$((Seq)this);
/*  15 */       Seq.class.$init$(this);
/*  15 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public final Iterator<scala.runtime.Nothing$> iterator() {
/*  16 */       return scala.package$.MODULE$.Iterator().empty();
/*     */     }
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(int idx) {
/*  17 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */     }
/*     */     
/*     */     public final int length() {
/*  18 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class PartialImmutableValuesIterable<From, To> implements Iterable<To> {
/*     */     private int size;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public GenericCompanion<Iterable> companion() {
/*  21 */       return Iterable.class.companion(this);
/*     */     }
/*     */     
/*     */     public Combiner<To, ParIterable<To>> parCombiner() {
/*  21 */       return Iterable.class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public Iterable<To> seq() {
/*  21 */       return Iterable.class.seq(this);
/*     */     }
/*     */     
/*     */     public Iterable<To> thisCollection() {
/*  21 */       return IterableLike.class.thisCollection((IterableLike)this);
/*     */     }
/*     */     
/*     */     public Iterable<To> toCollection(Object repr) {
/*  21 */       return IterableLike.class.toCollection((IterableLike)this, repr);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  21 */       return IterableLike.class.forall((IterableLike)this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  21 */       return IterableLike.class.exists((IterableLike)this, p);
/*     */     }
/*     */     
/*     */     public Option<To> find(Function1 p) {
/*  21 */       return IterableLike.class.find((IterableLike)this, p);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  21 */       return IterableLike.class.isEmpty((IterableLike)this);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  21 */       return (B)IterableLike.class.foldRight((IterableLike)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  21 */       return (B)IterableLike.class.reduceRight((IterableLike)this, op);
/*     */     }
/*     */     
/*     */     public Iterable<To> toIterable() {
/*  21 */       return IterableLike.class.toIterable((IterableLike)this);
/*     */     }
/*     */     
/*     */     public Iterator<To> toIterator() {
/*  21 */       return IterableLike.class.toIterator((IterableLike)this);
/*     */     }
/*     */     
/*     */     public To head() {
/*  21 */       return (To)IterableLike.class.head((IterableLike)this);
/*     */     }
/*     */     
/*     */     public Iterable<To> slice(int from, int until) {
/*  21 */       return (Iterable<To>)IterableLike.class.slice((IterableLike)this, from, until);
/*     */     }
/*     */     
/*     */     public Iterable<To> take(int n) {
/*  21 */       return (Iterable<To>)IterableLike.class.take((IterableLike)this, n);
/*     */     }
/*     */     
/*     */     public Iterable<To> drop(int n) {
/*  21 */       return (Iterable<To>)IterableLike.class.drop((IterableLike)this, n);
/*     */     }
/*     */     
/*     */     public Iterable<To> takeWhile(Function1 p) {
/*  21 */       return (Iterable<To>)IterableLike.class.takeWhile((IterableLike)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Iterable<To>> grouped(int size) {
/*  21 */       return IterableLike.class.grouped((IterableLike)this, size);
/*     */     }
/*     */     
/*     */     public Iterator<Iterable<To>> sliding(int size) {
/*  21 */       return IterableLike.class.sliding((IterableLike)this, size);
/*     */     }
/*     */     
/*     */     public Iterator<Iterable<To>> sliding(int size, int step) {
/*  21 */       return IterableLike.class.sliding((IterableLike)this, size, step);
/*     */     }
/*     */     
/*     */     public Iterable<To> takeRight(int n) {
/*  21 */       return (Iterable<To>)IterableLike.class.takeRight((IterableLike)this, n);
/*     */     }
/*     */     
/*     */     public Iterable<To> dropRight(int n) {
/*  21 */       return (Iterable<To>)IterableLike.class.dropRight((IterableLike)this, n);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/*  21 */       IterableLike.class.copyToArray((IterableLike)this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  21 */       return (That)IterableLike.class.zip((IterableLike)this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  21 */       return (That)IterableLike.class.zipAll((IterableLike)this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  21 */       return (That)IterableLike.class.zipWithIndex((IterableLike)this, bf);
/*     */     }
/*     */     
/*     */     public <B> boolean sameElements(GenIterable that) {
/*  21 */       return IterableLike.class.sameElements((IterableLike)this, that);
/*     */     }
/*     */     
/*     */     public Stream<To> toStream() {
/*  21 */       return IterableLike.class.toStream((IterableLike)this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/*  21 */       return IterableLike.class.canEqual((IterableLike)this, that);
/*     */     }
/*     */     
/*     */     public Object view() {
/*  21 */       return IterableLike.class.view((IterableLike)this);
/*     */     }
/*     */     
/*     */     public IterableView<To, Iterable<To>> view(int from, int until) {
/*  21 */       return IterableLike.class.view((IterableLike)this, from, until);
/*     */     }
/*     */     
/*     */     public Builder<To, Iterable<To>> newBuilder() {
/*  21 */       return GenericTraversableTemplate.class.newBuilder((GenericTraversableTemplate)this);
/*     */     }
/*     */     
/*     */     public <B> Builder<B, Iterable<B>> genericBuilder() {
/*  21 */       return GenericTraversableTemplate.class.genericBuilder((GenericTraversableTemplate)this);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<Iterable<A1>, Iterable<A2>> unzip(Function1 asPair) {
/*  21 */       return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<Iterable<A1>, Iterable<A2>, Iterable<A3>> unzip3(Function1 asTriple) {
/*  21 */       return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */     }
/*     */     
/*     */     public <B> Iterable<B> flatten(Function1 asTraversable) {
/*  21 */       return (Iterable<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public <B> Iterable<Iterable<B>> transpose(Function1 asTraversable) {
/*  21 */       return (Iterable<Iterable<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public Iterable<To> repr() {
/*  21 */       return (Iterable<To>)TraversableLike.class.repr((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/*  21 */       return TraversableLike.class.isTraversableAgain((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  21 */       return TraversableLike.class.hasDefiniteSize((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  21 */       return (That)TraversableLike.class.$plus$plus((TraversableLike)this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/*  21 */       return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/*  21 */       return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  21 */       return (That)TraversableLike.class.map((TraversableLike)this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  21 */       return (That)TraversableLike.class.flatMap((TraversableLike)this, f, bf);
/*     */     }
/*     */     
/*     */     public Iterable<To> filter(Function1 p) {
/*  21 */       return (Iterable<To>)TraversableLike.class.filter((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public Iterable<To> filterNot(Function1 p) {
/*  21 */       return (Iterable<To>)TraversableLike.class.filterNot((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  21 */       return (That)TraversableLike.class.collect((TraversableLike)this, pf, bf);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterable<To>, Iterable<To>> partition(Function1 p) {
/*  21 */       return TraversableLike.class.partition((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public <K> Map<K, Iterable<To>> groupBy(Function1 f) {
/*  21 */       return TraversableLike.class.groupBy((TraversableLike)this, f);
/*     */     }
/*     */     
/*     */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/*  21 */       return (That)TraversableLike.class.scan((TraversableLike)this, z, op, cbf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  21 */       return (That)TraversableLike.class.scanLeft((TraversableLike)this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  21 */       return (That)TraversableLike.class.scanRight((TraversableLike)this, z, op, bf);
/*     */     }
/*     */     
/*     */     public Option<To> headOption() {
/*  21 */       return TraversableLike.class.headOption((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Iterable<To> tail() {
/*  21 */       return (Iterable<To>)TraversableLike.class.tail((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public To last() {
/*  21 */       return (To)TraversableLike.class.last((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Option<To> lastOption() {
/*  21 */       return TraversableLike.class.lastOption((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Iterable<To> init() {
/*  21 */       return (Iterable<To>)TraversableLike.class.init((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Iterable<To> sliceWithKnownDelta(int from, int until, int delta) {
/*  21 */       return (Iterable<To>)TraversableLike.class.sliceWithKnownDelta((TraversableLike)this, from, until, delta);
/*     */     }
/*     */     
/*     */     public Iterable<To> sliceWithKnownBound(int from, int until) {
/*  21 */       return (Iterable<To>)TraversableLike.class.sliceWithKnownBound((TraversableLike)this, from, until);
/*     */     }
/*     */     
/*     */     public Iterable<To> dropWhile(Function1 p) {
/*  21 */       return (Iterable<To>)TraversableLike.class.dropWhile((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterable<To>, Iterable<To>> span(Function1 p) {
/*  21 */       return TraversableLike.class.span((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterable<To>, Iterable<To>> splitAt(int n) {
/*  21 */       return TraversableLike.class.splitAt((TraversableLike)this, n);
/*     */     }
/*     */     
/*     */     public Iterator<Iterable<To>> tails() {
/*  21 */       return TraversableLike.class.tails((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Iterator<Iterable<To>> inits() {
/*  21 */       return TraversableLike.class.inits((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public Traversable<To> toTraversable() {
/*  21 */       return TraversableLike.class.toTraversable((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  21 */       return (Col)TraversableLike.class.to((TraversableLike)this, cbf);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  21 */       return TraversableLike.class.toString((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/*  21 */       return TraversableLike.class.stringPrefix((TraversableLike)this);
/*     */     }
/*     */     
/*     */     public FilterMonadic<To, Iterable<To>> withFilter(Function1 p) {
/*  21 */       return TraversableLike.class.withFilter((TraversableLike)this, p);
/*     */     }
/*     */     
/*     */     public ParIterable<To> par() {
/*  21 */       return (ParIterable<To>)Parallelizable.class.par((Parallelizable)this);
/*     */     }
/*     */     
/*     */     public List<To> reversed() {
/*  21 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  21 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  21 */       return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  21 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*  21 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  21 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  21 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  21 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  21 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  21 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/*  21 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  21 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/*  21 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  21 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/*  21 */       return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/*  21 */       return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */     }
/*     */     
/*     */     public <B> To min(Ordering cmp) {
/*  21 */       return (To)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */     }
/*     */     
/*     */     public <B> To max(Ordering cmp) {
/*  21 */       return (To)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */     }
/*     */     
/*     */     public <B> To maxBy(Function1 f, Ordering cmp) {
/*  21 */       return (To)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> To minBy(Function1 f, Ordering cmp) {
/*  21 */       return (To)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*  21 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*  21 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*  21 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*  21 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<To> toList() {
/*  21 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<To> toSeq() {
/*  21 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<To> toIndexedSeq() {
/*  21 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*  21 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  21 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<To> toVector() {
/*  21 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/*  21 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  21 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  21 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  21 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  21 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*  21 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*  21 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  21 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public PartialImmutableValuesIterable() {
/*  21 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  21 */       TraversableOnce.class.$init$((TraversableOnce)this);
/*  21 */       Parallelizable.class.$init$((Parallelizable)this);
/*  21 */       TraversableLike.class.$init$((TraversableLike)this);
/*  21 */       GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  21 */       GenTraversable.class.$init$((GenTraversable)this);
/*  21 */       Traversable.class.$init$((Traversable)this);
/*  21 */       Traversable.class.$init$((Traversable)this);
/*  21 */       GenIterable.class.$init$((GenIterable)this);
/*  21 */       IterableLike.class.$init$((IterableLike)this);
/*  21 */       Iterable.class.$init$((Iterable)this);
/*  21 */       Iterable.class.$init$(this);
/*     */     }
/*     */     
/*     */     public final Iterator<To> iterator() {
/*  26 */       Iterator<From> superIterator = valuesIterator();
/*  27 */       return new Collections$PartialImmutableValuesIterable$$anon$1(this, (PartialImmutableValuesIterable)superIterator);
/*     */     }
/*     */     
/*     */     public class Collections$PartialImmutableValuesIterable$$anon$1 implements Iterator<To> {
/*     */       private To _next;
/*     */       
/*     */       private boolean _hasNext;
/*     */       
/*     */       private final Iterator superIterator$1;
/*     */       
/*     */       public Iterator<To> seq() {
/*  27 */         return Iterator.class.seq(this);
/*     */       }
/*     */       
/*     */       public boolean isEmpty() {
/*  27 */         return Iterator.class.isEmpty(this);
/*     */       }
/*     */       
/*     */       public boolean isTraversableAgain() {
/*  27 */         return Iterator.class.isTraversableAgain(this);
/*     */       }
/*     */       
/*     */       public boolean hasDefiniteSize() {
/*  27 */         return Iterator.class.hasDefiniteSize(this);
/*     */       }
/*     */       
/*     */       public Iterator<To> take(int n) {
/*  27 */         return Iterator.class.take(this, n);
/*     */       }
/*     */       
/*     */       public Iterator<To> drop(int n) {
/*  27 */         return Iterator.class.drop(this, n);
/*     */       }
/*     */       
/*     */       public Iterator<To> slice(int from, int until) {
/*  27 */         return Iterator.class.slice(this, from, until);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> map(Function1 f) {
/*  27 */         return Iterator.class.map(this, f);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> $plus$plus(Function0 that) {
/*  27 */         return Iterator.class.$plus$plus(this, that);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> flatMap(Function1 f) {
/*  27 */         return Iterator.class.flatMap(this, f);
/*     */       }
/*     */       
/*     */       public Iterator<To> filter(Function1 p) {
/*  27 */         return Iterator.class.filter(this, p);
/*     */       }
/*     */       
/*     */       public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*  27 */         return Iterator.class.corresponds(this, that, p);
/*     */       }
/*     */       
/*     */       public Iterator<To> withFilter(Function1 p) {
/*  27 */         return Iterator.class.withFilter(this, p);
/*     */       }
/*     */       
/*     */       public Iterator<To> filterNot(Function1 p) {
/*  27 */         return Iterator.class.filterNot(this, p);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> collect(PartialFunction pf) {
/*  27 */         return Iterator.class.collect(this, pf);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*  27 */         return Iterator.class.scanLeft(this, z, op);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*  27 */         return Iterator.class.scanRight(this, z, op);
/*     */       }
/*     */       
/*     */       public Iterator<To> takeWhile(Function1 p) {
/*  27 */         return Iterator.class.takeWhile(this, p);
/*     */       }
/*     */       
/*     */       public Tuple2<Iterator<To>, Iterator<To>> partition(Function1 p) {
/*  27 */         return Iterator.class.partition(this, p);
/*     */       }
/*     */       
/*     */       public Tuple2<Iterator<To>, Iterator<To>> span(Function1 p) {
/*  27 */         return Iterator.class.span(this, p);
/*     */       }
/*     */       
/*     */       public Iterator<To> dropWhile(Function1 p) {
/*  27 */         return Iterator.class.dropWhile(this, p);
/*     */       }
/*     */       
/*     */       public <B> Iterator<Tuple2<To, B>> zip(Iterator that) {
/*  27 */         return Iterator.class.zip(this, that);
/*     */       }
/*     */       
/*     */       public <A1> Iterator<A1> padTo(int len, Object elem) {
/*  27 */         return Iterator.class.padTo(this, len, elem);
/*     */       }
/*     */       
/*     */       public Iterator<Tuple2<To, Object>> zipWithIndex() {
/*  27 */         return Iterator.class.zipWithIndex(this);
/*     */       }
/*     */       
/*     */       public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*  27 */         return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */       }
/*     */       
/*     */       public <U> void foreach(Function1 f) {
/*  27 */         Iterator.class.foreach(this, f);
/*     */       }
/*     */       
/*     */       public boolean forall(Function1 p) {
/*  27 */         return Iterator.class.forall(this, p);
/*     */       }
/*     */       
/*     */       public boolean exists(Function1 p) {
/*  27 */         return Iterator.class.exists(this, p);
/*     */       }
/*     */       
/*     */       public boolean contains(Object elem) {
/*  27 */         return Iterator.class.contains(this, elem);
/*     */       }
/*     */       
/*     */       public Option<To> find(Function1 p) {
/*  27 */         return Iterator.class.find(this, p);
/*     */       }
/*     */       
/*     */       public int indexWhere(Function1 p) {
/*  27 */         return Iterator.class.indexWhere(this, p);
/*     */       }
/*     */       
/*     */       public <B> int indexOf(Object elem) {
/*  27 */         return Iterator.class.indexOf(this, elem);
/*     */       }
/*     */       
/*     */       public BufferedIterator<To> buffered() {
/*  27 */         return Iterator.class.buffered(this);
/*     */       }
/*     */       
/*     */       public <B> Iterator<To>.GroupedIterator<B> grouped(int size) {
/*  27 */         return Iterator.class.grouped(this, size);
/*     */       }
/*     */       
/*     */       public <B> Iterator<To>.GroupedIterator<B> sliding(int size, int step) {
/*  27 */         return Iterator.class.sliding(this, size, step);
/*     */       }
/*     */       
/*     */       public int length() {
/*  27 */         return Iterator.class.length(this);
/*     */       }
/*     */       
/*     */       public Tuple2<Iterator<To>, Iterator<To>> duplicate() {
/*  27 */         return Iterator.class.duplicate(this);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*  27 */         return Iterator.class.patch(this, from, patchElems, replaced);
/*     */       }
/*     */       
/*     */       public <B> void copyToArray(Object xs, int start, int len) {
/*  27 */         Iterator.class.copyToArray(this, xs, start, len);
/*     */       }
/*     */       
/*     */       public boolean sameElements(Iterator that) {
/*  27 */         return Iterator.class.sameElements(this, that);
/*     */       }
/*     */       
/*     */       public Traversable<To> toTraversable() {
/*  27 */         return Iterator.class.toTraversable(this);
/*     */       }
/*     */       
/*     */       public Iterator<To> toIterator() {
/*  27 */         return Iterator.class.toIterator(this);
/*     */       }
/*     */       
/*     */       public Stream<To> toStream() {
/*  27 */         return Iterator.class.toStream(this);
/*     */       }
/*     */       
/*     */       public String toString() {
/*  27 */         return Iterator.class.toString(this);
/*     */       }
/*     */       
/*     */       public <B> int sliding$default$2() {
/*  27 */         return Iterator.class.sliding$default$2(this);
/*     */       }
/*     */       
/*     */       public List<To> reversed() {
/*  27 */         return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public int size() {
/*  27 */         return TraversableOnce.class.size((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public boolean nonEmpty() {
/*  27 */         return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public int count(Function1 p) {
/*  27 */         return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */       }
/*     */       
/*     */       public <B> Option<B> collectFirst(PartialFunction pf) {
/*  27 */         return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */       }
/*     */       
/*     */       public <B> B $div$colon(Object z, Function2 op) {
/*  27 */         return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public <B> B $colon$bslash(Object z, Function2 op) {
/*  27 */         return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public <B> B foldLeft(Object z, Function2 op) {
/*  27 */         return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public <B> B foldRight(Object z, Function2 op) {
/*  27 */         return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public <B> B reduceLeft(Function2 op) {
/*  27 */         return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <B> B reduceRight(Function2 op) {
/*  27 */         return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <B> Option<B> reduceLeftOption(Function2 op) {
/*  27 */         return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <B> Option<B> reduceRightOption(Function2 op) {
/*  27 */         return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <A1> A1 reduce(Function2 op) {
/*  27 */         return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <A1> Option<A1> reduceOption(Function2 op) {
/*  27 */         return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <A1> A1 fold(Object z, Function2 op) {
/*  27 */         return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  27 */         return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */       }
/*     */       
/*     */       public <B> B sum(Numeric num) {
/*  27 */         return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */       }
/*     */       
/*     */       public <B> B product(Numeric num) {
/*  27 */         return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */       }
/*     */       
/*     */       public <B> To min(Ordering cmp) {
/*  27 */         return (To)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */       }
/*     */       
/*     */       public <B> To max(Ordering cmp) {
/*  27 */         return (To)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */       }
/*     */       
/*     */       public <B> To maxBy(Function1 f, Ordering cmp) {
/*  27 */         return (To)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */       }
/*     */       
/*     */       public <B> To minBy(Function1 f, Ordering cmp) {
/*  27 */         return (To)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */       }
/*     */       
/*     */       public <B> void copyToBuffer(Buffer dest) {
/*  27 */         TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */       }
/*     */       
/*     */       public <B> void copyToArray(Object xs, int start) {
/*  27 */         TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */       }
/*     */       
/*     */       public <B> void copyToArray(Object xs) {
/*  27 */         TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */       }
/*     */       
/*     */       public <B> Object toArray(ClassTag evidence$1) {
/*  27 */         return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */       }
/*     */       
/*     */       public List<To> toList() {
/*  27 */         return TraversableOnce.class.toList((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public Iterable<To> toIterable() {
/*  27 */         return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public Seq<To> toSeq() {
/*  27 */         return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public IndexedSeq<To> toIndexedSeq() {
/*  27 */         return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public <B> Buffer<B> toBuffer() {
/*  27 */         return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public <B> Set<B> toSet() {
/*  27 */         return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public Vector<To> toVector() {
/*  27 */         return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public <Col> Col to(CanBuildFrom cbf) {
/*  27 */         return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */       }
/*     */       
/*     */       public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/*  27 */         return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */       }
/*     */       
/*     */       public String mkString(String start, String sep, String end) {
/*  27 */         return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */       }
/*     */       
/*     */       public String mkString(String sep) {
/*  27 */         return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */       }
/*     */       
/*     */       public String mkString() {
/*  27 */         return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  27 */         return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */       }
/*     */       
/*     */       public StringBuilder addString(StringBuilder b, String sep) {
/*  27 */         return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */       }
/*     */       
/*     */       public StringBuilder addString(StringBuilder b) {
/*  27 */         return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */       }
/*     */       
/*     */       public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  27 */         return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public Collections$PartialImmutableValuesIterable$$anon$1(Collections.PartialImmutableValuesIterable $outer, Iterator superIterator$1) {
/*  27 */         GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  27 */         TraversableOnce.class.$init$((TraversableOnce)this);
/*  27 */         Iterator.class.$init$(this);
/*  29 */         this._hasNext = false;
/*     */       }
/*     */       
/*     */       public final boolean hasNext() {
/*     */         // Byte code:
/*     */         //   0: aload_0
/*     */         //   1: getfield _hasNext : Z
/*     */         //   4: ifne -> 61
/*     */         //   7: aload_0
/*     */         //   8: getfield superIterator$1 : Lscala/collection/Iterator;
/*     */         //   11: invokeinterface hasNext : ()Z
/*     */         //   16: ifeq -> 61
/*     */         //   19: aload_0
/*     */         //   20: getfield superIterator$1 : Lscala/collection/Iterator;
/*     */         //   23: invokeinterface next : ()Ljava/lang/Object;
/*     */         //   28: astore_2
/*     */         //   29: aload_0
/*     */         //   30: getfield $outer : Lakka/util/Collections$PartialImmutableValuesIterable;
/*     */         //   33: aload_2
/*     */         //   34: invokevirtual isDefinedAt : (Ljava/lang/Object;)Z
/*     */         //   37: ifeq -> 0
/*     */         //   40: aload_0
/*     */         //   41: aload_0
/*     */         //   42: getfield $outer : Lakka/util/Collections$PartialImmutableValuesIterable;
/*     */         //   45: aload_2
/*     */         //   46: invokevirtual apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */         //   49: putfield _next : Ljava/lang/Object;
/*     */         //   52: aload_0
/*     */         //   53: iconst_1
/*     */         //   54: putfield _hasNext : Z
/*     */         //   57: iconst_1
/*     */         //   58: goto -> 65
/*     */         //   61: aload_0
/*     */         //   62: getfield _hasNext : Z
/*     */         //   65: ireturn
/*     */         // Line number table:
/*     */         //   Java source line number -> byte code offset
/*     */         //   #32	-> 0
/*     */         //   #33	-> 19
/*     */         //   #34	-> 29
/*     */         //   #35	-> 40
/*     */         //   #36	-> 52
/*     */         //   #37	-> 57
/*     */         //   #39	-> 61
/*     */         //   #31	-> 65
/*     */         // Local variable table:
/*     */         //   start	length	slot	name	descriptor
/*     */         //   0	66	0	this	Lakka/util/Collections$PartialImmutableValuesIterable$$anon$1;
/*     */         //   29	37	2	potentiallyNext	Ljava/lang/Object;
/*     */       }
/*     */       
/*     */       public final To next() {
/*  42 */         if (hasNext()) {
/*  43 */           Object ret = this._next;
/*  44 */           null;
/*  44 */           this._next = null;
/*  45 */           this._hasNext = false;
/*  46 */           return (To)ret;
/*     */         } 
/*  47 */         throw new NoSuchElementException("next");
/*     */       }
/*     */     }
/*     */     
/*     */     private int size$lzycompute() {
/*  51 */       synchronized (this) {
/*  51 */         if (!this.bitmap$0) {
/*  51 */           this.size = iterator().size();
/*  51 */           this.bitmap$0 = true;
/*     */         } 
/*  51 */         return this.size;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int size() {
/*  51 */       return this.bitmap$0 ? this.size : size$lzycompute();
/*     */     }
/*     */     
/*     */     public <C> void foreach(Function1 f) {
/*  52 */       iterator().foreach(f);
/*     */     }
/*     */     
/*     */     public abstract boolean isDefinedAt(From param1From);
/*     */     
/*     */     public abstract To apply(From param1From);
/*     */     
/*     */     public abstract Iterator<From> valuesIterator();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\Collections.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */