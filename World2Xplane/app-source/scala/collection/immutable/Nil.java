package scala.collection.immutable;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.LinearSeq;
import scala.collection.Seq;
import scala.collection.SeqView;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.immutable.ParSeq;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes = "\006\001m;Q!\001\002\t\002&\t1AT5m\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001A\021!bC\007\002\005\031)AB\001EA\033\t\031a*\0337\024\t-qQ\003\007\t\004\025=\t\022B\001\t\003\005\021a\025n\035;\021\005I\031R\"\001\004\n\005Q1!a\002(pi\"Lgn\032\t\003%YI!a\006\004\003\017A\023x\016Z;diB\021!#G\005\0035\031\021AbU3sS\006d\027N_1cY\026DQ\001H\006\005\002u\ta\001P5oSRtD#A\005\t\013}YA\021\t\021\002\017%\034X)\0349usV\t\021\005\005\002\023E%\0211E\002\002\b\005>|G.Z1o\021\025)3\002\"\021'\003\021AW-\0313\026\003EAQ\001K\006\005B%\nA\001^1jYV\ta\002C\003,\027\021\005C&\001\004fcV\fGn\035\013\003C5BQA\f\026A\002=\nA\001\0365biB\021!\003M\005\003c\031\0211!\0218z\021\035\0314\"!A\005BQ\nQ\002\035:pIV\034G\017\025:fM&DX#A\033\021\005YZT\"A\034\013\005aJ\024\001\0027b]\036T\021AO\001\005U\0064\030-\003\002=o\t11\013\036:j]\036DqAP\006\002\002\023\005q(\001\007qe>$Wo\031;Be&$\0300F\001A!\t\021\022)\003\002C\r\t\031\021J\034;\t\017\021[\021\021!C\001\013\006q\001O]8ek\016$X\t\\3nK:$HCA\030G\021\03595)!AA\002\001\0131\001\037\0232\021\035I5\"!A\005B)\013q\002\035:pIV\034G/\023;fe\006$xN]\013\002\027B\031A*T\030\016\003\021I!A\024\003\003\021%#XM]1u_JDq\001U\006\002\002\023%\021+A\006sK\006$'+Z:pYZ,G#\001*\021\005Y\032\026B\001+8\005\031y%M[3di\"\0321BV-\021\005I9\026B\001-\007\005A\031VM]5bYZ+'o]5p]VKEI\b\005\016T\0020 hc$\006Q\r\001a+\027")
public final class Nil {
  public static Iterator<Object> productIterator() {
    return Nil$.MODULE$.productIterator();
  }
  
  public static Object productElement(int paramInt) {
    return Nil$.MODULE$.productElement(paramInt);
  }
  
  public static int productArity() {
    return Nil$.MODULE$.productArity();
  }
  
  public static String productPrefix() {
    return Nil$.MODULE$.productPrefix();
  }
  
  public static boolean equals(Object paramObject) {
    return Nil$.MODULE$.equals(paramObject);
  }
  
  public static List<Nothing$> tail() {
    return Nil$.MODULE$.tail();
  }
  
  public static Nothing$ head() {
    return Nil$.MODULE$.head();
  }
  
  public static boolean isEmpty() {
    return Nil$.MODULE$.isEmpty();
  }
  
  public static Combiner<Nothing$, ParSeq<Nothing$>> parCombiner() {
    return Nil$.MODULE$.parCombiner();
  }
  
  public static Seq<Nothing$> toSeq() {
    return Nil$.MODULE$.toSeq();
  }
  
  public static <B> boolean corresponds(GenSeq<B> paramGenSeq, Function2<Nothing$, B, Object> paramFunction2) {
    return Nil$.MODULE$.corresponds(paramGenSeq, paramFunction2);
  }
  
  public static Iterator<Nothing$> iterator() {
    return Nil$.MODULE$.iterator();
  }
  
  public static int hashCode() {
    return Nil$.MODULE$.hashCode();
  }
  
  public static LinearSeq<Nothing$> toCollection(List<Nothing$> paramList) {
    return Nil$.MODULE$.toCollection(paramList);
  }
  
  public static LinearSeq<Nothing$> thisCollection() {
    return Nil$.MODULE$.thisCollection();
  }
  
  public static LinearSeq<Nothing$> seq() {
    return Nil$.MODULE$.seq();
  }
  
  public static int lastIndexWhere(Function1<Nothing$, Object> paramFunction1, int paramInt) {
    return Nil$.MODULE$.lastIndexWhere(paramFunction1, paramInt);
  }
  
  public static int indexWhere(Function1<Nothing$, Object> paramFunction1, int paramInt) {
    return Nil$.MODULE$.indexWhere(paramFunction1, paramInt);
  }
  
  public static int segmentLength(Function1<Nothing$, Object> paramFunction1, int paramInt) {
    return Nil$.MODULE$.segmentLength(paramFunction1, paramInt);
  }
  
  public static boolean isDefinedAt(int paramInt) {
    return Nil$.MODULE$.isDefinedAt(paramInt);
  }
  
  public static int lengthCompare(int paramInt) {
    return Nil$.MODULE$.lengthCompare(paramInt);
  }
  
  public static <B> boolean sameElements(GenIterable<Object> paramGenIterable) {
    return Nil$.MODULE$.sameElements(paramGenIterable);
  }
  
  public static List<Nothing$> dropRight(int paramInt) {
    return Nil$.MODULE$.dropRight(paramInt);
  }
  
  public static Object last() {
    return Nil$.MODULE$.last();
  }
  
  public static <B> Object reduceRight(Function2<Nothing$, Object, Object> paramFunction2) {
    return Nil$.MODULE$.reduceRight(paramFunction2);
  }
  
  public static <B> Object reduceLeft(Function2<Object, Nothing$, Object> paramFunction2) {
    return Nil$.MODULE$.reduceLeft(paramFunction2);
  }
  
  public static <B> B foldLeft(B paramB, Function2<B, Nothing$, B> paramFunction2) {
    return (B)Nil$.MODULE$.foldLeft(paramB, paramFunction2);
  }
  
  public static Option<Nothing$> find(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.find(paramFunction1);
  }
  
  public static boolean contains(Object paramObject) {
    return Nil$.MODULE$.contains(paramObject);
  }
  
  public static boolean exists(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.exists(paramFunction1);
  }
  
  public static boolean forall(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.forall(paramFunction1);
  }
  
  public static Object apply(int paramInt) {
    return Nil$.MODULE$.apply(paramInt);
  }
  
  public static int length() {
    return Nil$.MODULE$.length();
  }
  
  public static List<Nothing$> removeDuplicates() {
    return Nil$.MODULE$.removeDuplicates();
  }
  
  public static <B> void foreach(Function1<Nothing$, B> paramFunction1) {
    Nil$.MODULE$.foreach(paramFunction1);
  }
  
  public static Stream<Nothing$> toStream() {
    return Nil$.MODULE$.toStream();
  }
  
  public static String stringPrefix() {
    return Nil$.MODULE$.stringPrefix();
  }
  
  public static <B> B foldRight(B paramB, Function2<Nothing$, B, B> paramFunction2) {
    return (B)Nil$.MODULE$.foldRight(paramB, paramFunction2);
  }
  
  public static List<Nothing$> reverse() {
    return Nil$.MODULE$.reverse();
  }
  
  public static Tuple2<List<Nothing$>, List<Nothing$>> span(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.span(paramFunction1);
  }
  
  public static List<Nothing$> dropWhile(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.dropWhile(paramFunction1);
  }
  
  public static List<Nothing$> takeWhile(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.takeWhile(paramFunction1);
  }
  
  public static Tuple2<List<Nothing$>, List<Nothing$>> splitAt(int paramInt) {
    return Nil$.MODULE$.splitAt(paramInt);
  }
  
  public static List<Nothing$> takeRight(int paramInt) {
    return Nil$.MODULE$.takeRight(paramInt);
  }
  
  public static List<Nothing$> slice(int paramInt1, int paramInt2) {
    return Nil$.MODULE$.slice(paramInt1, paramInt2);
  }
  
  public static List<Nothing$> drop(int paramInt) {
    return Nil$.MODULE$.drop(paramInt);
  }
  
  public static List<Nothing$> take(int paramInt) {
    return Nil$.MODULE$.take(paramInt);
  }
  
  public static List<Nothing$> toList() {
    return Nil$.MODULE$.toList();
  }
  
  public static <B, That> That $plus$colon(B paramB, CanBuildFrom<List<Nothing$>, B, That> paramCanBuildFrom) {
    return (That)Nil$.MODULE$.$plus$colon(paramB, paramCanBuildFrom);
  }
  
  public static <B, That> That $plus$plus(GenTraversableOnce<B> paramGenTraversableOnce, CanBuildFrom<List<Nothing$>, B, That> paramCanBuildFrom) {
    return (That)Nil$.MODULE$.$plus$plus(paramGenTraversableOnce, paramCanBuildFrom);
  }
  
  public static <B> List<B> mapConserve(Function1<Nothing$, B> paramFunction1) {
    return Nil$.MODULE$.mapConserve(paramFunction1);
  }
  
  public static <B> List<B> reverse_$colon$colon$colon(List<B> paramList) {
    return Nil$.MODULE$.reverse_$colon$colon$colon(paramList);
  }
  
  public static <B> List<B> $colon$colon$colon(List<B> paramList) {
    return Nil$.MODULE$.$colon$colon$colon(paramList);
  }
  
  public static <B> List<B> $colon$colon(B paramB) {
    return Nil$.MODULE$.$colon$colon(paramB);
  }
  
  public static GenericCompanion<List> companion() {
    return Nil$.MODULE$.companion();
  }
  
  public static <A> Function1<A, Nothing$> compose(Function1<A, Object> paramFunction1) {
    return Nil$.MODULE$.compose(paramFunction1);
  }
  
  public static <U> Function1<Object, Object> runWith(Function1<Nothing$, U> paramFunction1) {
    return Nil$.MODULE$.runWith(paramFunction1);
  }
  
  public static <A1, B1> Object applyOrElse(int paramInt, Function1<Object, Object> paramFunction1) {
    return Nil$.MODULE$.applyOrElse(paramInt, paramFunction1);
  }
  
  public static Function1<Object, Option<Nothing$>> lift() {
    return Nil$.MODULE$.lift();
  }
  
  public static <C> PartialFunction<Object, C> andThen(Function1<Nothing$, C> paramFunction1) {
    return Nil$.MODULE$.andThen(paramFunction1);
  }
  
  public static <A1, B1> PartialFunction<Object, Object> orElse(PartialFunction<Object, Object> paramPartialFunction) {
    return Nil$.MODULE$.orElse(paramPartialFunction);
  }
  
  public static <B> boolean startsWith(GenSeq<B> paramGenSeq) {
    return Nil$.MODULE$.startsWith(paramGenSeq);
  }
  
  public static int lastIndexWhere(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.lastIndexWhere(paramFunction1);
  }
  
  public static <B> int lastIndexOf(Object paramObject, int paramInt) {
    return Nil$.MODULE$.lastIndexOf(paramObject, paramInt);
  }
  
  public static <B> int lastIndexOf(Object paramObject) {
    return Nil$.MODULE$.lastIndexOf(paramObject);
  }
  
  public static <B> int indexOf(Object paramObject, int paramInt) {
    return Nil$.MODULE$.indexOf(paramObject, paramInt);
  }
  
  public static <B> int indexOf(Object paramObject) {
    return Nil$.MODULE$.indexOf(paramObject);
  }
  
  public static int indexWhere(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.indexWhere(paramFunction1);
  }
  
  public static int prefixLength(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.prefixLength(paramFunction1);
  }
  
  public static String toString() {
    return Nil$.MODULE$.toString();
  }
  
  public static SeqView<Nothing$, Seq<Nothing$>> view(int paramInt1, int paramInt2) {
    return Nil$.MODULE$.view(paramInt1, paramInt2);
  }
  
  public static Object view() {
    return Nil$.MODULE$.view();
  }
  
  public static Range indices() {
    return Nil$.MODULE$.indices();
  }
  
  public static Seq<Nothing$> toSeq() {
    return Nil$.MODULE$.toSeq();
  }
  
  public static <B> Seq<Nothing$> sorted(Ordering<Object> paramOrdering) {
    return (Seq<Nothing$>)Nil$.MODULE$.sorted(paramOrdering);
  }
  
  public static <B> Seq<Nothing$> sortBy(Function1<Nothing$, B> paramFunction1, Ordering<B> paramOrdering) {
    return (Seq<Nothing$>)Nil$.MODULE$.sortBy(paramFunction1, paramOrdering);
  }
  
  public static Seq<Nothing$> sortWith(Function2<Nothing$, Nothing$, Object> paramFunction2) {
    return (Seq<Nothing$>)Nil$.MODULE$.sortWith(paramFunction2);
  }
  
  public static <B, That> Object padTo(int paramInt, Object paramObject, CanBuildFrom<Seq<Nothing$>, Object, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.padTo(paramInt, paramObject, paramCanBuildFrom);
  }
  
  public static <B, That> Object $colon$plus(Object paramObject, CanBuildFrom<Seq<Nothing$>, Object, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.$colon$plus(paramObject, paramCanBuildFrom);
  }
  
  public static <B, That> Object updated(int paramInt, Object paramObject, CanBuildFrom<Seq<Nothing$>, Object, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.updated(paramInt, paramObject, paramCanBuildFrom);
  }
  
  public static <B, That> Object patch(int paramInt1, GenSeq<Object> paramGenSeq, int paramInt2, CanBuildFrom<Seq<Nothing$>, Object, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.patch(paramInt1, paramGenSeq, paramInt2, paramCanBuildFrom);
  }
  
  public static Seq<Nothing$> distinct() {
    return (Seq<Nothing$>)Nil$.MODULE$.distinct();
  }
  
  public static <B> Seq<Nothing$> intersect(GenSeq<Object> paramGenSeq) {
    return (Seq<Nothing$>)Nil$.MODULE$.intersect(paramGenSeq);
  }
  
  public static <B> Seq<Nothing$> diff(GenSeq<Object> paramGenSeq) {
    return (Seq<Nothing$>)Nil$.MODULE$.diff(paramGenSeq);
  }
  
  public static <B, That> Object union(GenSeq<Object> paramGenSeq, CanBuildFrom<Seq<Nothing$>, Object, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.union(paramGenSeq, paramCanBuildFrom);
  }
  
  public static <B> boolean containsSlice(GenSeq<B> paramGenSeq) {
    return Nil$.MODULE$.containsSlice(paramGenSeq);
  }
  
  public static <B> int lastIndexOfSlice(GenSeq<Object> paramGenSeq, int paramInt) {
    return Nil$.MODULE$.lastIndexOfSlice(paramGenSeq, paramInt);
  }
  
  public static <B> int lastIndexOfSlice(GenSeq<Object> paramGenSeq) {
    return Nil$.MODULE$.lastIndexOfSlice(paramGenSeq);
  }
  
  public static <B> int indexOfSlice(GenSeq<Object> paramGenSeq, int paramInt) {
    return Nil$.MODULE$.indexOfSlice(paramGenSeq, paramInt);
  }
  
  public static <B> int indexOfSlice(GenSeq<Object> paramGenSeq) {
    return Nil$.MODULE$.indexOfSlice(paramGenSeq);
  }
  
  public static <B> boolean endsWith(GenSeq<B> paramGenSeq) {
    return Nil$.MODULE$.endsWith(paramGenSeq);
  }
  
  public static <B> boolean startsWith(GenSeq<B> paramGenSeq, int paramInt) {
    return Nil$.MODULE$.startsWith(paramGenSeq, paramInt);
  }
  
  public static Iterator<Nothing$> reverseIterator() {
    return Nil$.MODULE$.reverseIterator();
  }
  
  public static <B, That> That reverseMap(Function1<Nothing$, B> paramFunction1, CanBuildFrom<Seq<Nothing$>, B, That> paramCanBuildFrom) {
    return (That)Nil$.MODULE$.reverseMap(paramFunction1, paramCanBuildFrom);
  }
  
  public static Seq<Nothing$> reverse() {
    return (Seq<Nothing$>)Nil$.MODULE$.reverse();
  }
  
  public static Iterator<Seq<Nothing$>> combinations(int paramInt) {
    return Nil$.MODULE$.combinations(paramInt);
  }
  
  public static Iterator<Seq<Nothing$>> permutations() {
    return Nil$.MODULE$.permutations();
  }
  
  public static int size() {
    return Nil$.MODULE$.size();
  }
  
  public static Seq<Nothing$> toCollection(Seq<Nothing$> paramSeq) {
    return Nil$.MODULE$.toCollection(paramSeq);
  }
  
  public static Seq<Nothing$> thisCollection() {
    return Nil$.MODULE$.thisCollection();
  }
  
  public static Seq<Nothing$> seq() {
    return Nil$.MODULE$.seq();
  }
  
  public static IterableView<Nothing$, Iterable<Nothing$>> view(int paramInt1, int paramInt2) {
    return Nil$.MODULE$.view(paramInt1, paramInt2);
  }
  
  public static Object view() {
    return Nil$.MODULE$.view();
  }
  
  public static boolean canEqual(Object paramObject) {
    return Nil$.MODULE$.canEqual(paramObject);
  }
  
  public static <A1, That> Object zipWithIndex(CanBuildFrom<Iterable<Nothing$>, Tuple2<Object, Object>, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.zipWithIndex(paramCanBuildFrom);
  }
  
  public static <B, A1, That> Object zipAll(GenIterable<Object> paramGenIterable, Object paramObject1, Object paramObject2, CanBuildFrom<Iterable<Nothing$>, Tuple2<Object, Object>, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.zipAll(paramGenIterable, paramObject1, paramObject2, paramCanBuildFrom);
  }
  
  public static <A1, B, That> Object zip(GenIterable<Object> paramGenIterable, CanBuildFrom<Iterable<Nothing$>, Tuple2<Object, Object>, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.zip(paramGenIterable, paramCanBuildFrom);
  }
  
  public static <B> void copyToArray(Object paramObject, int paramInt1, int paramInt2) {
    Nil$.MODULE$.copyToArray(paramObject, paramInt1, paramInt2);
  }
  
  public static Iterable<Nothing$> dropRight(int paramInt) {
    return (Iterable<Nothing$>)Nil$.MODULE$.dropRight(paramInt);
  }
  
  public static Iterable<Nothing$> takeRight(int paramInt) {
    return (Iterable<Nothing$>)Nil$.MODULE$.takeRight(paramInt);
  }
  
  public static Iterator<Iterable<Nothing$>> sliding(int paramInt1, int paramInt2) {
    return Nil$.MODULE$.sliding(paramInt1, paramInt2);
  }
  
  public static Iterator<Iterable<Nothing$>> sliding(int paramInt) {
    return Nil$.MODULE$.sliding(paramInt);
  }
  
  public static Iterator<Iterable<Nothing$>> grouped(int paramInt) {
    return Nil$.MODULE$.grouped(paramInt);
  }
  
  public static Iterable<Nothing$> takeWhile(Function1<Nothing$, Object> paramFunction1) {
    return (Iterable<Nothing$>)Nil$.MODULE$.takeWhile(paramFunction1);
  }
  
  public static Iterable<Nothing$> drop(int paramInt) {
    return (Iterable<Nothing$>)Nil$.MODULE$.drop(paramInt);
  }
  
  public static Iterable<Nothing$> take(int paramInt) {
    return (Iterable<Nothing$>)Nil$.MODULE$.take(paramInt);
  }
  
  public static Iterable<Nothing$> slice(int paramInt1, int paramInt2) {
    return (Iterable<Nothing$>)Nil$.MODULE$.slice(paramInt1, paramInt2);
  }
  
  public static Object head() {
    return Nil$.MODULE$.head();
  }
  
  public static Iterator<Nothing$> toIterator() {
    return Nil$.MODULE$.toIterator();
  }
  
  public static Iterable<Nothing$> toIterable() {
    return Nil$.MODULE$.toIterable();
  }
  
  public static Iterable<Nothing$> toCollection(Iterable<Nothing$> paramIterable) {
    return Nil$.MODULE$.toCollection(paramIterable);
  }
  
  public static Iterable<Nothing$> thisCollection() {
    return Nil$.MODULE$.thisCollection();
  }
  
  public static Iterable<Nothing$> seq() {
    return Nil$.MODULE$.seq();
  }
  
  public static <A1> Object $div$colon$bslash(Object paramObject, Function2<Object, Object, Object> paramFunction2) {
    return Nil$.MODULE$.$div$colon$bslash(paramObject, paramFunction2);
  }
  
  public static StringBuilder addString(StringBuilder paramStringBuilder) {
    return Nil$.MODULE$.addString(paramStringBuilder);
  }
  
  public static StringBuilder addString(StringBuilder paramStringBuilder, String paramString) {
    return Nil$.MODULE$.addString(paramStringBuilder, paramString);
  }
  
  public static StringBuilder addString(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3) {
    return Nil$.MODULE$.addString(paramStringBuilder, paramString1, paramString2, paramString3);
  }
  
  public static String mkString() {
    return Nil$.MODULE$.mkString();
  }
  
  public static String mkString(String paramString) {
    return Nil$.MODULE$.mkString(paramString);
  }
  
  public static String mkString(String paramString1, String paramString2, String paramString3) {
    return Nil$.MODULE$.mkString(paramString1, paramString2, paramString3);
  }
  
  public static <T, U> Map<T, U> toMap(Predef$.less.colon.less<Nothing$, Tuple2<T, U>> paramless) {
    return Nil$.MODULE$.toMap(paramless);
  }
  
  public static Vector<Nothing$> toVector() {
    return Nil$.MODULE$.toVector();
  }
  
  public static <B> Set<Object> toSet() {
    return Nil$.MODULE$.toSet();
  }
  
  public static <B> Buffer<Object> toBuffer() {
    return Nil$.MODULE$.toBuffer();
  }
  
  public static IndexedSeq<Nothing$> toIndexedSeq() {
    return Nil$.MODULE$.toIndexedSeq();
  }
  
  public static <B> Object toArray(ClassTag<Object> paramClassTag) {
    return Nil$.MODULE$.toArray(paramClassTag);
  }
  
  public static <B> void copyToArray(Object paramObject) {
    Nil$.MODULE$.copyToArray(paramObject);
  }
  
  public static <B> void copyToArray(Object paramObject, int paramInt) {
    Nil$.MODULE$.copyToArray(paramObject, paramInt);
  }
  
  public static <B> void copyToBuffer(Buffer<Object> paramBuffer) {
    Nil$.MODULE$.copyToBuffer(paramBuffer);
  }
  
  public static <B> Nothing$ minBy(Function1<Nothing$, B> paramFunction1, Ordering<B> paramOrdering) {
    return (Nothing$)Nil$.MODULE$.minBy(paramFunction1, paramOrdering);
  }
  
  public static <B> Nothing$ maxBy(Function1<Nothing$, B> paramFunction1, Ordering<B> paramOrdering) {
    return (Nothing$)Nil$.MODULE$.maxBy(paramFunction1, paramOrdering);
  }
  
  public static <B> Nothing$ max(Ordering<Object> paramOrdering) {
    return (Nothing$)Nil$.MODULE$.max(paramOrdering);
  }
  
  public static <B> Nothing$ min(Ordering<Object> paramOrdering) {
    return (Nothing$)Nil$.MODULE$.min(paramOrdering);
  }
  
  public static <B> Object product(Numeric<Object> paramNumeric) {
    return Nil$.MODULE$.product(paramNumeric);
  }
  
  public static <B> Object sum(Numeric<Object> paramNumeric) {
    return Nil$.MODULE$.sum(paramNumeric);
  }
  
  public static <B> B aggregate(B paramB, Function2<B, Nothing$, B> paramFunction2, Function2<B, B, B> paramFunction21) {
    return (B)Nil$.MODULE$.aggregate(paramB, paramFunction2, paramFunction21);
  }
  
  public static <A1> Object fold(Object paramObject, Function2<Object, Object, Object> paramFunction2) {
    return Nil$.MODULE$.fold(paramObject, paramFunction2);
  }
  
  public static <A1> Option<Object> reduceOption(Function2<Object, Object, Object> paramFunction2) {
    return Nil$.MODULE$.reduceOption(paramFunction2);
  }
  
  public static <A1> Object reduce(Function2<Object, Object, Object> paramFunction2) {
    return Nil$.MODULE$.reduce(paramFunction2);
  }
  
  public static <B> Option<Object> reduceRightOption(Function2<Nothing$, Object, Object> paramFunction2) {
    return Nil$.MODULE$.reduceRightOption(paramFunction2);
  }
  
  public static <B> Option<Object> reduceLeftOption(Function2<Object, Nothing$, Object> paramFunction2) {
    return Nil$.MODULE$.reduceLeftOption(paramFunction2);
  }
  
  public static <B> B $colon$bslash(B paramB, Function2<Nothing$, B, B> paramFunction2) {
    return (B)Nil$.MODULE$.$colon$bslash(paramB, paramFunction2);
  }
  
  public static <B> B $div$colon(B paramB, Function2<B, Nothing$, B> paramFunction2) {
    return (B)Nil$.MODULE$.$div$colon(paramB, paramFunction2);
  }
  
  public static <B> Option<B> collectFirst(PartialFunction<Nothing$, B> paramPartialFunction) {
    return Nil$.MODULE$.collectFirst(paramPartialFunction);
  }
  
  public static int count(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.count(paramFunction1);
  }
  
  public static boolean nonEmpty() {
    return Nil$.MODULE$.nonEmpty();
  }
  
  public static List<Nothing$> reversed() {
    return Nil$.MODULE$.reversed();
  }
  
  public static ParIterable<Nothing$> par() {
    return (ParIterable<Nothing$>)Nil$.MODULE$.par();
  }
  
  public static FilterMonadic<Nothing$, Traversable<Nothing$>> withFilter(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.withFilter(paramFunction1);
  }
  
  public static TraversableView<Nothing$, Traversable<Nothing$>> view(int paramInt1, int paramInt2) {
    return Nil$.MODULE$.view(paramInt1, paramInt2);
  }
  
  public static Object view() {
    return Nil$.MODULE$.view();
  }
  
  public static <Col> Col to(CanBuildFrom<Nothing$, Nothing$, Col> paramCanBuildFrom) {
    return (Col)Nil$.MODULE$.to(paramCanBuildFrom);
  }
  
  public static Traversable<Nothing$> toTraversable() {
    return Nil$.MODULE$.toTraversable();
  }
  
  public static Iterator<Traversable<Nothing$>> inits() {
    return Nil$.MODULE$.inits();
  }
  
  public static Iterator<Traversable<Nothing$>> tails() {
    return Nil$.MODULE$.tails();
  }
  
  public static Traversable<Nothing$> dropWhile(Function1<Nothing$, Object> paramFunction1) {
    return (Traversable<Nothing$>)Nil$.MODULE$.dropWhile(paramFunction1);
  }
  
  public static Traversable<Nothing$> init() {
    return (Traversable<Nothing$>)Nil$.MODULE$.init();
  }
  
  public static Option<Nothing$> lastOption() {
    return Nil$.MODULE$.lastOption();
  }
  
  public static Traversable<Nothing$> tail() {
    return (Traversable<Nothing$>)Nil$.MODULE$.tail();
  }
  
  public static Option<Nothing$> headOption() {
    return Nil$.MODULE$.headOption();
  }
  
  public static <B, That> That scanRight(B paramB, Function2<Nothing$, B, B> paramFunction2, CanBuildFrom<Traversable<Nothing$>, B, That> paramCanBuildFrom) {
    return (That)Nil$.MODULE$.scanRight(paramB, paramFunction2, paramCanBuildFrom);
  }
  
  public static <B, That> That scanLeft(B paramB, Function2<B, Nothing$, B> paramFunction2, CanBuildFrom<Traversable<Nothing$>, B, That> paramCanBuildFrom) {
    return (That)Nil$.MODULE$.scanLeft(paramB, paramFunction2, paramCanBuildFrom);
  }
  
  public static <B, That> Object scan(Object paramObject, Function2<Object, Object, Object> paramFunction2, CanBuildFrom<Traversable<Nothing$>, Object, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.scan(paramObject, paramFunction2, paramCanBuildFrom);
  }
  
  public static <K> Map<K, Traversable<Nothing$>> groupBy(Function1<Nothing$, K> paramFunction1) {
    return Nil$.MODULE$.groupBy(paramFunction1);
  }
  
  public static Tuple2<Traversable<Nothing$>, Traversable<Nothing$>> partition(Function1<Nothing$, Object> paramFunction1) {
    return Nil$.MODULE$.partition(paramFunction1);
  }
  
  public static <B, That> That collect(PartialFunction<Nothing$, B> paramPartialFunction, CanBuildFrom<Traversable<Nothing$>, B, That> paramCanBuildFrom) {
    return (That)Nil$.MODULE$.collect(paramPartialFunction, paramCanBuildFrom);
  }
  
  public static Traversable<Nothing$> filterNot(Function1<Nothing$, Object> paramFunction1) {
    return (Traversable<Nothing$>)Nil$.MODULE$.filterNot(paramFunction1);
  }
  
  public static Traversable<Nothing$> filter(Function1<Nothing$, Object> paramFunction1) {
    return (Traversable<Nothing$>)Nil$.MODULE$.filter(paramFunction1);
  }
  
  public static <B, That> That flatMap(Function1<Nothing$, GenTraversableOnce<B>> paramFunction1, CanBuildFrom<Traversable<Nothing$>, B, That> paramCanBuildFrom) {
    return (That)Nil$.MODULE$.flatMap(paramFunction1, paramCanBuildFrom);
  }
  
  public static <B, That> That map(Function1<Nothing$, B> paramFunction1, CanBuildFrom<Traversable<Nothing$>, B, That> paramCanBuildFrom) {
    return (That)Nil$.MODULE$.map(paramFunction1, paramCanBuildFrom);
  }
  
  public static <B, That> Object $plus$plus$colon(Traversable<Object> paramTraversable, CanBuildFrom<Traversable<Nothing$>, Object, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.$plus$plus$colon(paramTraversable, paramCanBuildFrom);
  }
  
  public static <B, That> Object $plus$plus$colon(TraversableOnce<Object> paramTraversableOnce, CanBuildFrom<Traversable<Nothing$>, Object, Object> paramCanBuildFrom) {
    return Nil$.MODULE$.$plus$plus$colon(paramTraversableOnce, paramCanBuildFrom);
  }
  
  public static boolean hasDefiniteSize() {
    return Nil$.MODULE$.hasDefiniteSize();
  }
  
  public static Traversable<Nothing$> toCollection(Traversable<Nothing$> paramTraversable) {
    return Nil$.MODULE$.toCollection(paramTraversable);
  }
  
  public static Traversable<Nothing$> thisCollection() {
    return Nil$.MODULE$.thisCollection();
  }
  
  public static boolean isTraversableAgain() {
    return Nil$.MODULE$.isTraversableAgain();
  }
  
  public static Traversable<Nothing$> repr() {
    return (Traversable<Nothing$>)Nil$.MODULE$.repr();
  }
  
  public static <B> Traversable<Traversable<B>> transpose(Function1<Nothing$, GenTraversableOnce<B>> paramFunction1) {
    return (Traversable<Traversable<B>>)Nil$.MODULE$.transpose(paramFunction1);
  }
  
  public static <B> Traversable<B> flatten(Function1<Nothing$, GenTraversableOnce<B>> paramFunction1) {
    return (Traversable<B>)Nil$.MODULE$.flatten(paramFunction1);
  }
  
  public static <A1, A2, A3> Tuple3<Traversable<A1>, Traversable<A2>, Traversable<A3>> unzip3(Function1<Nothing$, Tuple3<A1, A2, A3>> paramFunction1) {
    return Nil$.MODULE$.unzip3(paramFunction1);
  }
  
  public static <A1, A2> Tuple2<Traversable<A1>, Traversable<A2>> unzip(Function1<Nothing$, Tuple2<A1, A2>> paramFunction1) {
    return Nil$.MODULE$.unzip(paramFunction1);
  }
  
  public static <B> Builder<B, Traversable<B>> genericBuilder() {
    return Nil$.MODULE$.genericBuilder();
  }
  
  public static Builder<Nothing$, Traversable<Nothing$>> newBuilder() {
    return Nil$.MODULE$.newBuilder();
  }
  
  public static Traversable<Nothing$> seq() {
    return Nil$.MODULE$.seq();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Nil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */