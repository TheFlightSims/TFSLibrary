/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import java.util.regex.PatternSyntaxException;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSeqLike;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.IndexedSeq;
/*    */ import scala.collection.IndexedSeqLike;
/*    */ import scala.collection.IndexedSeqOptimized;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableLike;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Parallelizable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SeqLike;
/*    */ import scala.collection.SeqView;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.FilterMonadic;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.ParSeq;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordered;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.util.matching.Regex;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Ub\001B\001\003\005%\021\021b\025;sS:<w\n]:\013\005\r!\021!C5n[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001'\r\001!B\004\t\003\0271i\021AB\005\003\033\031\021a!\0218z-\006d\007cA\b\021%5\t!!\003\002\022\005\tQ1\013\036:j]\036d\025n[3\021\005M1bBA\006\025\023\t)b!\001\004Qe\026$WMZ\005\003/a\021aa\025;sS:<'BA\013\007\021!Q\002A!b\001\n\003Z\022\001\002:faJ,\022A\005\005\t;\001\021\t\021)A\005%\005)!/\0329sA!)q\004\001C\001A\0051A(\0338jiz\"\"!\t\022\021\005=\001\001\"\002\016\037\001\004\021\002B\002\023\001A\023ES%\001\buQ&\0348i\0347mK\016$\030n\0348\026\003\031\002\"aD\024\n\005!\022!!D,sCB\004X\rZ*ue&tw\r\003\004+\001\001&\tfK\001\ri>\034u\016\0347fGRLwN\034\013\003M1BQAG\025A\002IAaA\f\001!\n#z\023A\0038fo\n+\030\016\0343feV\t\001\007\005\0022i5\t!G\003\0024\t\0059Q.\036;bE2,\027BA\0333\0055\031FO]5oO\n+\030\016\0343fe\")q\007\001C!q\005)\021\r\0359msR\021\021\b\020\t\003\027iJ!a\017\004\003\t\rC\027M\035\005\006{Y\002\rAP\001\006S:$W\r\037\t\003\027}J!\001\021\004\003\007%sG\017C\003C\001\021\0053)A\003tY&\034W\rF\002\023\t\032CQ!R!A\002y\nAA\032:p[\")q)\021a\001}\005)QO\034;jY\")\021\n\001C!\025\006AAo\\*ue&tw\rF\001\023\021\025a\005\001\"\021N\003\031aWM\\4uQV\ta\bC\003P\001\021\005Q%A\002tKFDq!\025\001\002\002\023\005#+\001\005iCND7i\0343f)\005q\004b\002+\001\003\003%\t%V\001\007KF,\030\r\\:\025\005YK\006CA\006X\023\tAfAA\004C_>dW-\0318\t\017i\033\026\021!a\0017\006\031\001\020J\031\021\005-a\026BA/\007\005\r\te._\004\b?\n\t\t\021#\001a\003%\031FO]5oO>\0038\017\005\002\020C\0329\021AAA\001\022\003\0217CA1d!\tYA-\003\002f\r\t1\021I\\=SK\032DQaH1\005\002\035$\022\001\031\005\007S\006\004KQ\0016\0021QD\027n]\"pY2,7\r^5p]\022*\007\020^3og&|g\016\006\002'W\")A\016\033a\001C\005)A\005\0365jg\"1a.\031Q\005\006=\fa\003^8D_2dWm\031;j_:$S\r\037;f]NLwN\034\013\003aJ$\"AJ9\t\013ii\007\031\001\n\t\0131l\007\031A\021\t\rQ\f\007\025\"\002v\003QqWm\036\"vS2$WM\035\023fqR,gn]5p]R\021\001G\036\005\006YN\004\r!\t\005\006q\006$)!_\001\020CB\004H.\037\023fqR,gn]5p]R\021!\020 \013\003smDQ!P<A\002yBQ\001\\<A\002\005BQA`1\005\006}\fqb\0357jG\026$S\r\037;f]NLwN\034\013\005\003\003\t9\001F\003\023\003\007\t)\001C\003F{\002\007a\bC\003H{\002\007a\bC\003m{\002\007\021\005C\004\002\f\005$)!!\004\002%Q|7\013\036:j]\036$S\r\037;f]NLwN\034\013\004\025\006=\001B\0027\002\n\001\007\021\005C\004\002\024\005$)!!\006\002!1,gn\032;iI\025DH/\0328tS>tGc\001 \002\030!1A.!\005A\002\005Bq!a\007b\t\013\ti\"A\007tKF$S\r\037;f]NLwN\034\013\004M\005}\001B\0027\002\032\001\007\021\005C\005\002$\005\f\t\021\"\002\002&\005\021\002.Y:i\007>$W\rJ3yi\026t7/[8o)\r\021\026q\005\005\007Y\006\005\002\031A\021\t\023\005-\022-!A\005\006\0055\022\001E3rk\006d7\017J3yi\026t7/[8o)\021\ty#a\r\025\007Y\013\t\004\003\005[\003S\t\t\0211\001\\\021\031a\027\021\006a\001C\001")
/*    */ public final class StringOps implements StringLike<String> {
/*    */   private final String repr;
/*    */   
/*    */   public String mkString() {
/* 31 */     return StringLike$class.mkString(this);
/*    */   }
/*    */   
/*    */   public String $times(int n) {
/* 31 */     return StringLike$class.$times(this, n);
/*    */   }
/*    */   
/*    */   public int compare(String other) {
/* 31 */     return StringLike$class.compare(this, other);
/*    */   }
/*    */   
/*    */   public String stripLineEnd() {
/* 31 */     return StringLike$class.stripLineEnd(this);
/*    */   }
/*    */   
/*    */   public Iterator<String> linesWithSeparators() {
/* 31 */     return StringLike$class.linesWithSeparators(this);
/*    */   }
/*    */   
/*    */   public Iterator<String> lines() {
/* 31 */     return StringLike$class.lines(this);
/*    */   }
/*    */   
/*    */   public Iterator<String> linesIterator() {
/* 31 */     return StringLike$class.linesIterator(this);
/*    */   }
/*    */   
/*    */   public String capitalize() {
/* 31 */     return StringLike$class.capitalize(this);
/*    */   }
/*    */   
/*    */   public String stripPrefix(String prefix) {
/* 31 */     return StringLike$class.stripPrefix(this, prefix);
/*    */   }
/*    */   
/*    */   public String stripSuffix(String suffix) {
/* 31 */     return StringLike$class.stripSuffix(this, suffix);
/*    */   }
/*    */   
/*    */   public String replaceAllLiterally(String literal, String replacement) {
/* 31 */     return StringLike$class.replaceAllLiterally(this, literal, replacement);
/*    */   }
/*    */   
/*    */   public String stripMargin(char marginChar) {
/* 31 */     return StringLike$class.stripMargin(this, marginChar);
/*    */   }
/*    */   
/*    */   public String stripMargin() {
/* 31 */     return StringLike$class.stripMargin(this);
/*    */   }
/*    */   
/*    */   public String[] split(char separator) throws PatternSyntaxException {
/* 31 */     return StringLike$class.split(this, separator);
/*    */   }
/*    */   
/*    */   public String[] split(char[] separators) throws PatternSyntaxException {
/* 31 */     return StringLike$class.split(this, separators);
/*    */   }
/*    */   
/*    */   public Regex r() {
/* 31 */     return StringLike$class.r(this);
/*    */   }
/*    */   
/*    */   public Regex r(Seq groupNames) {
/* 31 */     return StringLike$class.r(this, groupNames);
/*    */   }
/*    */   
/*    */   public boolean toBoolean() {
/* 31 */     return StringLike$class.toBoolean(this);
/*    */   }
/*    */   
/*    */   public byte toByte() {
/* 31 */     return StringLike$class.toByte(this);
/*    */   }
/*    */   
/*    */   public short toShort() {
/* 31 */     return StringLike$class.toShort(this);
/*    */   }
/*    */   
/*    */   public int toInt() {
/* 31 */     return StringLike$class.toInt(this);
/*    */   }
/*    */   
/*    */   public long toLong() {
/* 31 */     return StringLike$class.toLong(this);
/*    */   }
/*    */   
/*    */   public float toFloat() {
/* 31 */     return StringLike$class.toFloat(this);
/*    */   }
/*    */   
/*    */   public double toDouble() {
/* 31 */     return StringLike$class.toDouble(this);
/*    */   }
/*    */   
/*    */   public <B> Object toArray(ClassTag evidence$1) {
/* 31 */     return StringLike$class.toArray(this, evidence$1);
/*    */   }
/*    */   
/*    */   public String format(Seq args) {
/* 31 */     return StringLike$class.format(this, args);
/*    */   }
/*    */   
/*    */   public String formatLocal(Locale l, Seq args) {
/* 31 */     return StringLike$class.formatLocal(this, l, args);
/*    */   }
/*    */   
/*    */   public boolean $less(Object that) {
/* 31 */     return Ordered.class.$less(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater(Object that) {
/* 31 */     return Ordered.class.$greater(this, that);
/*    */   }
/*    */   
/*    */   public boolean $less$eq(Object that) {
/* 31 */     return Ordered.class.$less$eq(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater$eq(Object that) {
/* 31 */     return Ordered.class.$greater$eq(this, that);
/*    */   }
/*    */   
/*    */   public int compareTo(Object that) {
/* 31 */     return Ordered.class.compareTo(this, that);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
/* 31 */     return TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
/* 31 */     return IterableLike.class.reduceRight((IterableLike)this, op);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
/* 31 */     return IterableLike.class.zip((IterableLike)this, that, bf);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$head() {
/* 31 */     return IterableLike.class.head((IterableLike)this);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$tail() {
/* 31 */     return TraversableLike.class.tail((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$last() {
/* 31 */     return TraversableLike.class.last((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$init() {
/* 31 */     return TraversableLike.class.init((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
/* 31 */     return IterableLike.class.sameElements((IterableLike)this, that);
/*    */   }
/*    */   
/*    */   public boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
/* 31 */     return SeqLike.class.endsWith((SeqLike)this, that);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 31 */     return IndexedSeqOptimized.class.isEmpty(this);
/*    */   }
/*    */   
/*    */   public <U> void foreach(Function1 f) {
/* 31 */     IndexedSeqOptimized.class.foreach(this, f);
/*    */   }
/*    */   
/*    */   public boolean forall(Function1 p) {
/* 31 */     return IndexedSeqOptimized.class.forall(this, p);
/*    */   }
/*    */   
/*    */   public boolean exists(Function1 p) {
/* 31 */     return IndexedSeqOptimized.class.exists(this, p);
/*    */   }
/*    */   
/*    */   public Option<Object> find(Function1 p) {
/* 31 */     return IndexedSeqOptimized.class.find(this, p);
/*    */   }
/*    */   
/*    */   public <B> B foldLeft(Object z, Function2 op) {
/* 31 */     return (B)IndexedSeqOptimized.class.foldLeft(this, z, op);
/*    */   }
/*    */   
/*    */   public <B> B foldRight(Object z, Function2 op) {
/* 31 */     return (B)IndexedSeqOptimized.class.foldRight(this, z, op);
/*    */   }
/*    */   
/*    */   public <B> B reduceLeft(Function2 op) {
/* 31 */     return (B)IndexedSeqOptimized.class.reduceLeft(this, op);
/*    */   }
/*    */   
/*    */   public <B> B reduceRight(Function2 op) {
/* 31 */     return (B)IndexedSeqOptimized.class.reduceRight(this, op);
/*    */   }
/*    */   
/*    */   public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 31 */     return (That)IndexedSeqOptimized.class.zip(this, that, bf);
/*    */   }
/*    */   
/*    */   public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 31 */     return (That)IndexedSeqOptimized.class.zipWithIndex(this, bf);
/*    */   }
/*    */   
/*    */   public Object head() {
/* 31 */     return IndexedSeqOptimized.class.head(this);
/*    */   }
/*    */   
/*    */   public Object tail() {
/* 31 */     return IndexedSeqOptimized.class.tail(this);
/*    */   }
/*    */   
/*    */   public Object last() {
/* 31 */     return IndexedSeqOptimized.class.last(this);
/*    */   }
/*    */   
/*    */   public Object init() {
/* 31 */     return IndexedSeqOptimized.class.init(this);
/*    */   }
/*    */   
/*    */   public Object take(int n) {
/* 31 */     return IndexedSeqOptimized.class.take(this, n);
/*    */   }
/*    */   
/*    */   public Object drop(int n) {
/* 31 */     return IndexedSeqOptimized.class.drop(this, n);
/*    */   }
/*    */   
/*    */   public Object takeRight(int n) {
/* 31 */     return IndexedSeqOptimized.class.takeRight(this, n);
/*    */   }
/*    */   
/*    */   public Object dropRight(int n) {
/* 31 */     return IndexedSeqOptimized.class.dropRight(this, n);
/*    */   }
/*    */   
/*    */   public Tuple2<String, String> splitAt(int n) {
/* 31 */     return IndexedSeqOptimized.class.splitAt(this, n);
/*    */   }
/*    */   
/*    */   public String takeWhile(Function1 p) {
/* 31 */     return (String)IndexedSeqOptimized.class.takeWhile(this, p);
/*    */   }
/*    */   
/*    */   public String dropWhile(Function1 p) {
/* 31 */     return (String)IndexedSeqOptimized.class.dropWhile(this, p);
/*    */   }
/*    */   
/*    */   public Tuple2<String, String> span(Function1 p) {
/* 31 */     return IndexedSeqOptimized.class.span(this, p);
/*    */   }
/*    */   
/*    */   public <B> boolean sameElements(GenIterable that) {
/* 31 */     return IndexedSeqOptimized.class.sameElements(this, that);
/*    */   }
/*    */   
/*    */   public <B> void copyToArray(Object xs, int start, int len) {
/* 31 */     IndexedSeqOptimized.class.copyToArray(this, xs, start, len);
/*    */   }
/*    */   
/*    */   public int lengthCompare(int len) {
/* 31 */     return IndexedSeqOptimized.class.lengthCompare(this, len);
/*    */   }
/*    */   
/*    */   public int segmentLength(Function1 p, int from) {
/* 31 */     return IndexedSeqOptimized.class.segmentLength(this, p, from);
/*    */   }
/*    */   
/*    */   public int indexWhere(Function1 p, int from) {
/* 31 */     return IndexedSeqOptimized.class.indexWhere(this, p, from);
/*    */   }
/*    */   
/*    */   public int lastIndexWhere(Function1 p, int end) {
/* 31 */     return IndexedSeqOptimized.class.lastIndexWhere(this, p, end);
/*    */   }
/*    */   
/*    */   public Object reverse() {
/* 31 */     return IndexedSeqOptimized.class.reverse(this);
/*    */   }
/*    */   
/*    */   public Iterator<Object> reverseIterator() {
/* 31 */     return IndexedSeqOptimized.class.reverseIterator(this);
/*    */   }
/*    */   
/*    */   public <B> boolean startsWith(GenSeq that, int offset) {
/* 31 */     return IndexedSeqOptimized.class.startsWith(this, that, offset);
/*    */   }
/*    */   
/*    */   public <B> boolean endsWith(GenSeq that) {
/* 31 */     return IndexedSeqOptimized.class.endsWith(this, that);
/*    */   }
/*    */   
/*    */   public Iterator<Object> iterator() {
/* 31 */     return IndexedSeqLike.class.iterator((IndexedSeqLike)this);
/*    */   }
/*    */   
/*    */   public <A1> Buffer<A1> toBuffer() {
/* 31 */     return IndexedSeqLike.class.toBuffer((IndexedSeqLike)this);
/*    */   }
/*    */   
/*    */   public Combiner<Object, ParSeq<Object>> parCombiner() {
/* 31 */     return SeqLike.class.parCombiner((SeqLike)this);
/*    */   }
/*    */   
/*    */   public int size() {
/* 31 */     return SeqLike.class.size((SeqLike)this);
/*    */   }
/*    */   
/*    */   public Iterator<String> permutations() {
/* 31 */     return SeqLike.class.permutations((SeqLike)this);
/*    */   }
/*    */   
/*    */   public Iterator<String> combinations(int n) {
/* 31 */     return SeqLike.class.combinations((SeqLike)this, n);
/*    */   }
/*    */   
/*    */   public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/* 31 */     return (That)SeqLike.class.reverseMap((SeqLike)this, f, bf);
/*    */   }
/*    */   
/*    */   public <B> int indexOfSlice(GenSeq that) {
/* 31 */     return SeqLike.class.indexOfSlice((SeqLike)this, that);
/*    */   }
/*    */   
/*    */   public <B> int indexOfSlice(GenSeq that, int from) {
/* 31 */     return SeqLike.class.indexOfSlice((SeqLike)this, that, from);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOfSlice(GenSeq that) {
/* 31 */     return SeqLike.class.lastIndexOfSlice((SeqLike)this, that);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOfSlice(GenSeq that, int end) {
/* 31 */     return SeqLike.class.lastIndexOfSlice((SeqLike)this, that, end);
/*    */   }
/*    */   
/*    */   public <B> boolean containsSlice(GenSeq that) {
/* 31 */     return SeqLike.class.containsSlice((SeqLike)this, that);
/*    */   }
/*    */   
/*    */   public boolean contains(Object elem) {
/* 31 */     return SeqLike.class.contains((SeqLike)this, elem);
/*    */   }
/*    */   
/*    */   public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/* 31 */     return (That)SeqLike.class.union((SeqLike)this, that, bf);
/*    */   }
/*    */   
/*    */   public <B> String diff(GenSeq that) {
/* 31 */     return (String)SeqLike.class.diff((SeqLike)this, that);
/*    */   }
/*    */   
/*    */   public <B> String intersect(GenSeq that) {
/* 31 */     return (String)SeqLike.class.intersect((SeqLike)this, that);
/*    */   }
/*    */   
/*    */   public Object distinct() {
/* 31 */     return SeqLike.class.distinct((SeqLike)this);
/*    */   }
/*    */   
/*    */   public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 31 */     return (That)SeqLike.class.patch((SeqLike)this, from, patch, replaced, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/* 31 */     return (That)SeqLike.class.updated((SeqLike)this, index, elem, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/* 31 */     return (That)SeqLike.class.$plus$colon((SeqLike)this, elem, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/* 31 */     return (That)SeqLike.class.$colon$plus((SeqLike)this, elem, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/* 31 */     return (That)SeqLike.class.padTo((SeqLike)this, len, elem, bf);
/*    */   }
/*    */   
/*    */   public <B> boolean corresponds(GenSeq that, Function2 p) {
/* 31 */     return SeqLike.class.corresponds((SeqLike)this, that, p);
/*    */   }
/*    */   
/*    */   public String sortWith(Function2 lt) {
/* 31 */     return (String)SeqLike.class.sortWith((SeqLike)this, lt);
/*    */   }
/*    */   
/*    */   public <B> String sortBy(Function1 f, Ordering ord) {
/* 31 */     return (String)SeqLike.class.sortBy((SeqLike)this, f, ord);
/*    */   }
/*    */   
/*    */   public <B> String sorted(Ordering ord) {
/* 31 */     return (String)SeqLike.class.sorted((SeqLike)this, ord);
/*    */   }
/*    */   
/*    */   public Seq<Object> toSeq() {
/* 31 */     return SeqLike.class.toSeq((SeqLike)this);
/*    */   }
/*    */   
/*    */   public Range indices() {
/* 31 */     return SeqLike.class.indices((SeqLike)this);
/*    */   }
/*    */   
/*    */   public Object view() {
/* 31 */     return SeqLike.class.view((SeqLike)this);
/*    */   }
/*    */   
/*    */   public SeqView<Object, String> view(int from, int until) {
/* 31 */     return SeqLike.class.view((SeqLike)this, from, until);
/*    */   }
/*    */   
/*    */   public boolean isDefinedAt(int idx) {
/* 31 */     return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*    */   }
/*    */   
/*    */   public int prefixLength(Function1 p) {
/* 31 */     return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*    */   }
/*    */   
/*    */   public int indexWhere(Function1 p) {
/* 31 */     return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*    */   }
/*    */   
/*    */   public <B> int indexOf(Object elem) {
/* 31 */     return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*    */   }
/*    */   
/*    */   public <B> int indexOf(Object elem, int from) {
/* 31 */     return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOf(Object elem) {
/* 31 */     return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOf(Object elem, int end) {
/* 31 */     return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*    */   }
/*    */   
/*    */   public int lastIndexWhere(Function1 p) {
/* 31 */     return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*    */   }
/*    */   
/*    */   public <B> boolean startsWith(GenSeq that) {
/* 31 */     return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*    */   }
/*    */   
/*    */   public Iterable<Object> toIterable() {
/* 31 */     return IterableLike.class.toIterable((IterableLike)this);
/*    */   }
/*    */   
/*    */   public Iterator<Object> toIterator() {
/* 31 */     return IterableLike.class.toIterator((IterableLike)this);
/*    */   }
/*    */   
/*    */   public Iterator<String> grouped(int size) {
/* 31 */     return IterableLike.class.grouped((IterableLike)this, size);
/*    */   }
/*    */   
/*    */   public Iterator<String> sliding(int size) {
/* 31 */     return IterableLike.class.sliding((IterableLike)this, size);
/*    */   }
/*    */   
/*    */   public Iterator<String> sliding(int size, int step) {
/* 31 */     return IterableLike.class.sliding((IterableLike)this, size, step);
/*    */   }
/*    */   
/*    */   public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 31 */     return (That)IterableLike.class.zipAll((IterableLike)this, that, thisElem, thatElem, bf);
/*    */   }
/*    */   
/*    */   public Stream<Object> toStream() {
/* 31 */     return IterableLike.class.toStream((IterableLike)this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object that) {
/* 31 */     return IterableLike.class.canEqual((IterableLike)this, that);
/*    */   }
/*    */   
/*    */   public final boolean isTraversableAgain() {
/* 31 */     return TraversableLike.class.isTraversableAgain((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public boolean hasDefiniteSize() {
/* 31 */     return TraversableLike.class.hasDefiniteSize((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 31 */     return (That)TraversableLike.class.$plus$plus((TraversableLike)this, that, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 31 */     return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 31 */     return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 31 */     return (That)TraversableLike.class.map((TraversableLike)this, f, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 31 */     return (That)TraversableLike.class.flatMap((TraversableLike)this, f, bf);
/*    */   }
/*    */   
/*    */   public String filter(Function1 p) {
/* 31 */     return (String)TraversableLike.class.filter((TraversableLike)this, p);
/*    */   }
/*    */   
/*    */   public String filterNot(Function1 p) {
/* 31 */     return (String)TraversableLike.class.filterNot((TraversableLike)this, p);
/*    */   }
/*    */   
/*    */   public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 31 */     return (That)TraversableLike.class.collect((TraversableLike)this, pf, bf);
/*    */   }
/*    */   
/*    */   public Tuple2<String, String> partition(Function1 p) {
/* 31 */     return TraversableLike.class.partition((TraversableLike)this, p);
/*    */   }
/*    */   
/*    */   public <K> Map<K, String> groupBy(Function1 f) {
/* 31 */     return TraversableLike.class.groupBy((TraversableLike)this, f);
/*    */   }
/*    */   
/*    */   public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 31 */     return (That)TraversableLike.class.scan((TraversableLike)this, z, op, cbf);
/*    */   }
/*    */   
/*    */   public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 31 */     return (That)TraversableLike.class.scanLeft((TraversableLike)this, z, op, bf);
/*    */   }
/*    */   
/*    */   public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 31 */     return (That)TraversableLike.class.scanRight((TraversableLike)this, z, op, bf);
/*    */   }
/*    */   
/*    */   public Option<Object> headOption() {
/* 31 */     return TraversableLike.class.headOption((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public Option<Object> lastOption() {
/* 31 */     return TraversableLike.class.lastOption((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public Object sliceWithKnownDelta(int from, int until, int delta) {
/* 31 */     return TraversableLike.class.sliceWithKnownDelta((TraversableLike)this, from, until, delta);
/*    */   }
/*    */   
/*    */   public Object sliceWithKnownBound(int from, int until) {
/* 31 */     return TraversableLike.class.sliceWithKnownBound((TraversableLike)this, from, until);
/*    */   }
/*    */   
/*    */   public Iterator<String> tails() {
/* 31 */     return TraversableLike.class.tails((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public Iterator<String> inits() {
/* 31 */     return TraversableLike.class.inits((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public Traversable<Object> toTraversable() {
/* 31 */     return TraversableLike.class.toTraversable((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public <Col> Col to(CanBuildFrom cbf) {
/* 31 */     return (Col)TraversableLike.class.to((TraversableLike)this, cbf);
/*    */   }
/*    */   
/*    */   public String stringPrefix() {
/* 31 */     return TraversableLike.class.stringPrefix((TraversableLike)this);
/*    */   }
/*    */   
/*    */   public FilterMonadic<Object, String> withFilter(Function1 p) {
/* 31 */     return TraversableLike.class.withFilter((TraversableLike)this, p);
/*    */   }
/*    */   
/*    */   public ParSeq<Object> par() {
/* 31 */     return (ParSeq<Object>)Parallelizable.class.par((Parallelizable)this);
/*    */   }
/*    */   
/*    */   public List<Object> reversed() {
/* 31 */     return TraversableOnce.class.reversed((TraversableOnce)this);
/*    */   }
/*    */   
/*    */   public boolean nonEmpty() {
/* 31 */     return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*    */   }
/*    */   
/*    */   public int count(Function1 p) {
/* 31 */     return TraversableOnce.class.count((TraversableOnce)this, p);
/*    */   }
/*    */   
/*    */   public <B> Option<B> collectFirst(PartialFunction pf) {
/* 31 */     return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*    */   }
/*    */   
/*    */   public <B> B $div$colon(Object z, Function2 op) {
/* 31 */     return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*    */   }
/*    */   
/*    */   public <B> B $colon$bslash(Object z, Function2 op) {
/* 31 */     return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*    */   }
/*    */   
/*    */   public <B> Option<B> reduceLeftOption(Function2 op) {
/* 31 */     return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*    */   }
/*    */   
/*    */   public <B> Option<B> reduceRightOption(Function2 op) {
/* 31 */     return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*    */   }
/*    */   
/*    */   public <A1> A1 reduce(Function2 op) {
/* 31 */     return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*    */   }
/*    */   
/*    */   public <A1> Option<A1> reduceOption(Function2 op) {
/* 31 */     return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*    */   }
/*    */   
/*    */   public <A1> A1 fold(Object z, Function2 op) {
/* 31 */     return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*    */   }
/*    */   
/*    */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 31 */     return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*    */   }
/*    */   
/*    */   public <B> B sum(Numeric num) {
/* 31 */     return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*    */   }
/*    */   
/*    */   public <B> B product(Numeric num) {
/* 31 */     return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*    */   }
/*    */   
/*    */   public <B> char min(Ordering cmp) {
/* 31 */     return TraversableOnce.class.min((TraversableOnce)this, cmp);
/*    */   }
/*    */   
/*    */   public <B> char max(Ordering cmp) {
/* 31 */     return TraversableOnce.class.max((TraversableOnce)this, cmp);
/*    */   }
/*    */   
/*    */   public <B> char maxBy(Function1 f, Ordering cmp) {
/* 31 */     return TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*    */   }
/*    */   
/*    */   public <B> char minBy(Function1 f, Ordering cmp) {
/* 31 */     return TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*    */   }
/*    */   
/*    */   public <B> void copyToBuffer(Buffer dest) {
/* 31 */     TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*    */   }
/*    */   
/*    */   public <B> void copyToArray(Object xs, int start) {
/* 31 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*    */   }
/*    */   
/*    */   public <B> void copyToArray(Object xs) {
/* 31 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*    */   }
/*    */   
/*    */   public List<Object> toList() {
/* 31 */     return TraversableOnce.class.toList((TraversableOnce)this);
/*    */   }
/*    */   
/*    */   public IndexedSeq<Object> toIndexedSeq() {
/* 31 */     return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*    */   }
/*    */   
/*    */   public <B> Set<B> toSet() {
/* 31 */     return TraversableOnce.class.toSet((TraversableOnce)this);
/*    */   }
/*    */   
/*    */   public Vector<Object> toVector() {
/* 31 */     return TraversableOnce.class.toVector((TraversableOnce)this);
/*    */   }
/*    */   
/*    */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 31 */     return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*    */   }
/*    */   
/*    */   public String mkString(String start, String sep, String end) {
/* 31 */     return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*    */   }
/*    */   
/*    */   public String mkString(String sep) {
/* 31 */     return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*    */   }
/*    */   
/*    */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 31 */     return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*    */   }
/*    */   
/*    */   public StringBuilder addString(StringBuilder b, String sep) {
/* 31 */     return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*    */   }
/*    */   
/*    */   public StringBuilder addString(StringBuilder b) {
/* 31 */     return TraversableOnce.class.addString((TraversableOnce)this, b);
/*    */   }
/*    */   
/*    */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 31 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */   }
/*    */   
/*    */   public String repr() {
/* 31 */     return this.repr;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 31 */     return StringOps$.MODULE$.hashCode$extension(repr());
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 31 */     return StringOps$.MODULE$.equals$extension(repr(), x$1);
/*    */   }
/*    */   
/*    */   public StringOps(String repr) {
/* 31 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 31 */     TraversableOnce.class.$init$((TraversableOnce)this);
/* 31 */     Parallelizable.class.$init$((Parallelizable)this);
/* 31 */     TraversableLike.class.$init$((TraversableLike)this);
/* 31 */     IterableLike.class.$init$((IterableLike)this);
/* 31 */     GenSeqLike.class.$init$((GenSeqLike)this);
/* 31 */     SeqLike.class.$init$((SeqLike)this);
/* 31 */     IndexedSeqLike.class.$init$((IndexedSeqLike)this);
/* 31 */     IndexedSeqOptimized.class.$init$(this);
/* 31 */     Ordered.class.$init$(this);
/* 31 */     StringLike$class.$init$(this);
/*    */   }
/*    */   
/*    */   public WrappedString thisCollection() {
/* 33 */     return StringOps$.MODULE$.thisCollection$extension(repr());
/*    */   }
/*    */   
/*    */   public WrappedString toCollection(String repr) {
/* 34 */     return StringOps$.MODULE$.toCollection$extension(repr(), repr);
/*    */   }
/*    */   
/*    */   public StringBuilder newBuilder() {
/* 37 */     return StringOps$.MODULE$.newBuilder$extension(repr());
/*    */   }
/*    */   
/*    */   public char apply(int index) {
/* 39 */     return StringOps$.MODULE$.apply$extension(repr(), index);
/*    */   }
/*    */   
/*    */   public String slice(int from, int until) {
/* 40 */     return StringOps$.MODULE$.slice$extension(repr(), from, until);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 48 */     return StringOps$.MODULE$.toString$extension(repr());
/*    */   }
/*    */   
/*    */   public int length() {
/* 49 */     return StringOps$.MODULE$.length$extension(repr());
/*    */   }
/*    */   
/*    */   public WrappedString seq() {
/* 51 */     return StringOps$.MODULE$.seq$extension(repr());
/*    */   }
/*    */   
/*    */   public static boolean equals$extension(String paramString, Object paramObject) {
/*    */     return StringOps$.MODULE$.equals$extension(paramString, paramObject);
/*    */   }
/*    */   
/*    */   public static int hashCode$extension(String paramString) {
/*    */     return StringOps$.MODULE$.hashCode$extension(paramString);
/*    */   }
/*    */   
/*    */   public static WrappedString seq$extension(String paramString) {
/*    */     return StringOps$.MODULE$.seq$extension(paramString);
/*    */   }
/*    */   
/*    */   public static int length$extension(String paramString) {
/*    */     return StringOps$.MODULE$.length$extension(paramString);
/*    */   }
/*    */   
/*    */   public static String toString$extension(String paramString) {
/*    */     return StringOps$.MODULE$.toString$extension(paramString);
/*    */   }
/*    */   
/*    */   public static String slice$extension(String paramString, int paramInt1, int paramInt2) {
/*    */     return StringOps$.MODULE$.slice$extension(paramString, paramInt1, paramInt2);
/*    */   }
/*    */   
/*    */   public static char apply$extension(String paramString, int paramInt) {
/*    */     return StringOps$.MODULE$.apply$extension(paramString, paramInt);
/*    */   }
/*    */   
/*    */   public static StringBuilder newBuilder$extension(String paramString) {
/*    */     return StringOps$.MODULE$.newBuilder$extension(paramString);
/*    */   }
/*    */   
/*    */   public static WrappedString toCollection$extension(String paramString1, String paramString2) {
/*    */     return StringOps$.MODULE$.toCollection$extension(paramString1, paramString2);
/*    */   }
/*    */   
/*    */   public static WrappedString thisCollection$extension(String paramString) {
/*    */     return StringOps$.MODULE$.thisCollection$extension(paramString);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\StringOps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */