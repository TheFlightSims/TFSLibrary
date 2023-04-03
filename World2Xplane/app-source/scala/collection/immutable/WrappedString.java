/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import java.util.regex.PatternSyntaxException;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.AbstractSeq;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.IndexedSeq;
/*    */ import scala.collection.IndexedSeqLike;
/*    */ import scala.collection.IndexedSeqOptimized;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableLike;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SeqLike;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.immutable.ParSeq;
/*    */ import scala.math.Ordered;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.util.matching.Regex;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001}3A!\001\002\001\023\tiqK]1qa\026$7\013\036:j]\036T!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\0011\003\002\001\013%Y\0012a\003\007\017\033\005!\021BA\007\005\005-\t%m\035;sC\016$8+Z9\021\005=\001R\"\001\004\n\005E1!\001B\"iCJ\0042a\005\013\017\033\005\021\021BA\013\003\005)Ie\016Z3yK\022\034V-\035\t\004']I\022B\001\r\003\005)\031FO]5oO2K7.\032\t\003'\001A\001b\007\001\003\006\004%\t\001H\001\005g\026dg-F\001\036!\tq\022E\004\002\020?%\021\001EB\001\007!J,G-\0324\n\005\t\032#AB*ue&twM\003\002!\r!AQ\005\001B\001B\003%Q$A\003tK24\007\005C\003(\001\021\005\001&\001\004=S:LGO\020\013\0033%BQa\007\024A\002uAaa\013\001!\n#b\023A\004;iSN\034u\016\0347fGRLwN\\\013\0023!1a\006\001Q\005R=\nA\002^8D_2dWm\031;j_:$\"!\007\031\t\013Ej\003\031A\r\002\tI,\007O\035\005\007g\001\001K\021\013\033\002\0259,wOQ;jY\022,'/F\0016!\0211\024HD\r\016\003]R!\001\017\003\002\0175,H/\0312mK&\021!h\016\002\b\005VLG\016Z3s\021\025a\004\001\"\021>\003\025\031H.[2f)\rIbh\021\005\006m\002\r\001Q\001\005MJ|W\016\005\002\020\003&\021!I\002\002\004\023:$\b\"\002#<\001\004\001\025!B;oi&d\007\"\002$\001\t\003:\025A\0027f]\036$\b.F\001A\021\025I\005\001\"\021K\003!!xn\025;sS:<G#A\017\b\0131\023\001\022A'\002\033]\023\030\r\0359fIN#(/\0338h!\t\031bJB\003\002\005!\005qj\005\002O!B\021q\"U\005\003%\032\021a!\0218z%\0264\007\"B\024O\t\003!F#A'\t\013YsE1A,\002\031\r\fgNQ;jY\0224%o\\7\026\003a\003R!\027/\032\035ei\021A\027\006\0037\022\tqaZ3oKJL7-\003\002^5\na1)\0318Ck&dGM\022:p[\")1G\024C\001i\001")
/*    */ public class WrappedString extends AbstractSeq<Object> implements IndexedSeq<Object>, StringLike<WrappedString> {
/*    */   private final String self;
/*    */   
/*    */   public char apply(int n) {
/* 31 */     return StringLike$class.apply(this, n);
/*    */   }
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
/* 31 */     return IterableLike.class.reduceRight(this, op);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
/* 31 */     return IterableLike.class.zip(this, that, bf);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$head() {
/* 31 */     return IterableLike.class.head(this);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$tail() {
/* 31 */     return TraversableLike.class.tail(this);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$last() {
/* 31 */     return TraversableLike.class.last(this);
/*    */   }
/*    */   
/*    */   public Object scala$collection$IndexedSeqOptimized$$super$init() {
/* 31 */     return TraversableLike.class.init(this);
/*    */   }
/*    */   
/*    */   public boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
/* 31 */     return IterableLike.class.sameElements(this, that);
/*    */   }
/*    */   
/*    */   public boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
/* 31 */     return SeqLike.class.endsWith(this, that);
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
/*    */   public Tuple2<WrappedString, WrappedString> splitAt(int n) {
/* 31 */     return IndexedSeqOptimized.class.splitAt(this, n);
/*    */   }
/*    */   
/*    */   public WrappedString takeWhile(Function1 p) {
/* 31 */     return (WrappedString)IndexedSeqOptimized.class.takeWhile(this, p);
/*    */   }
/*    */   
/*    */   public WrappedString dropWhile(Function1 p) {
/* 31 */     return (WrappedString)IndexedSeqOptimized.class.dropWhile(this, p);
/*    */   }
/*    */   
/*    */   public Tuple2<WrappedString, WrappedString> span(Function1 p) {
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
/*    */   public GenericCompanion<IndexedSeq> companion() {
/* 31 */     return IndexedSeq$class.companion(this);
/*    */   }
/*    */   
/*    */   public IndexedSeq<Object> toIndexedSeq() {
/* 31 */     return IndexedSeq$class.toIndexedSeq(this);
/*    */   }
/*    */   
/*    */   public IndexedSeq<Object> seq() {
/* 31 */     return IndexedSeq$class.seq(this);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 31 */     return IndexedSeqLike.class.hashCode(this);
/*    */   }
/*    */   
/*    */   public Iterator<Object> iterator() {
/* 31 */     return IndexedSeqLike.class.iterator(this);
/*    */   }
/*    */   
/*    */   public <A1> Buffer<A1> toBuffer() {
/* 31 */     return IndexedSeqLike.class.toBuffer(this);
/*    */   }
/*    */   
/*    */   public Seq<Object> toSeq() {
/* 31 */     return Seq$class.toSeq(this);
/*    */   }
/*    */   
/*    */   public Combiner<Object, ParSeq<Object>> parCombiner() {
/* 31 */     return Seq$class.parCombiner(this);
/*    */   }
/*    */   
/*    */   public String self() {
/* 31 */     return this.self;
/*    */   }
/*    */   
/*    */   public WrappedString(String self) {
/* 31 */     Traversable$class.$init$(this);
/* 31 */     Iterable$class.$init$(this);
/* 31 */     Seq$class.$init$(this);
/* 31 */     IndexedSeqLike.class.$init$(this);
/* 31 */     IndexedSeq.class.$init$(this);
/* 31 */     IndexedSeq$class.$init$(this);
/* 31 */     IndexedSeqOptimized.class.$init$(this);
/* 31 */     Ordered.class.$init$(this);
/* 31 */     StringLike$class.$init$(this);
/*    */   }
/*    */   
/*    */   public WrappedString thisCollection() {
/* 33 */     return this;
/*    */   }
/*    */   
/*    */   public WrappedString toCollection(WrappedString repr) {
/* 34 */     return repr;
/*    */   }
/*    */   
/*    */   public Builder<Object, WrappedString> newBuilder() {
/* 37 */     return WrappedString$.MODULE$.newBuilder();
/*    */   }
/*    */   
/*    */   public WrappedString slice(int from, int until) {
/* 40 */     int start = (from < 0) ? 0 : from;
/* 41 */     if (until <= start || start >= ((WrappedString)repr()).length())
/* 42 */       return new WrappedString(""); 
/* 44 */     int end = (until > length()) ? length() : until;
/* 45 */     return new WrappedString(Predef$.MODULE$.unwrapString((WrappedString)repr()).substring(start, end));
/*    */   }
/*    */   
/*    */   public int length() {
/* 47 */     return self().length();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 48 */     return self();
/*    */   }
/*    */   
/*    */   public static CanBuildFrom<WrappedString, Object, WrappedString> canBuildFrom() {
/*    */     return WrappedString$.MODULE$.canBuildFrom();
/*    */   }
/*    */   
/*    */   public static class WrappedString$$anon$1 implements CanBuildFrom<WrappedString, Object, WrappedString> {
/*    */     public Builder<Object, WrappedString> apply(WrappedString from) {
/* 57 */       return WrappedString$.MODULE$.newBuilder();
/*    */     }
/*    */     
/*    */     public Builder<Object, WrappedString> apply() {
/* 58 */       return WrappedString$.MODULE$.newBuilder();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class WrappedString$$anonfun$newBuilder$1 extends AbstractFunction1<String, WrappedString> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final WrappedString apply(String x) {
/* 61 */       return new WrappedString(x);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\WrappedString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */