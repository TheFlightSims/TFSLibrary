package scala.xml;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.IterableView;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;
import scala.runtime.Null$;

@ScalaSignature(bytes = "\006\001\005-v!B\001\003\021\003;\021\001\002(vY2T!a\001\003\002\007alGNC\001\006\003\025\0318-\0317b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002.\021AAT;mYN!\021\002D\b\024!\tAQ\"\003\002\017\005\tAQ*\032;b\t\006$\030\r\005\002\021#5\tA!\003\002\023\t\t9\001K]8ek\016$\bC\001\t\025\023\t)BA\001\007TKJL\027\r\\5{C\ndW\rC\003\030\023\021\005\001$\001\004=S:LGO\020\013\002\017!)!$\003C!7\005A\021\016^3sCR|'/F\001\035!\ri\002EI\007\002=)\021q\004B\001\013G>dG.Z2uS>t\027BA\021\037\005!IE/\032:bi>\024\bC\001\t$\023\t!CAA\004O_RD\027N\\4\t\013\031JA\021I\024\002\tML'0Z\013\002QA\021\001#K\005\003U\021\0211!\0238u\021\025a\023\002\"\021.\003\031\t\007\017]3oIR\031AB\f\031\t\013=Z\003\031\001\007\002\0035Dq!M\026\021\002\003\007!'A\003tG>\004X\r\005\002\tg%\021AG\001\002\021\035\006lWm\0359bG\026\024\025N\0343j]\036DQAN\005\005B]\naAZ5mi\026\024HC\001\0079\021\025IT\0071\001;\003\0051\007\003\002\t<\031uJ!\001\020\003\003\023\031+hn\031;j_:\f\004C\001\t?\023\tyDAA\004C_>dW-\0318\t\013\005KA\021\001\"\002\t\r|\007/\037\013\003\031\rCQ\001\022!A\0021\tAA\\3yi\")a)\003C\001\017\006aq-\032;OC6,7\017]1dKR\021\001J\023\t\003!%K!A\003\003\t\013-+\005\031\001'\002\013=<h.\032:\021\005!i\025B\001(\003\005\021qu\016Z3\t\013AKA\021I)\002\017!\f7OT3yiV\tQ\bC\003E\023\021\0051+F\001I\021\025)\026\002\"\001T\003\rYW-\037\005\006/&!\taU\001\006m\006dW/\032\005\0063&!\t!U\001\013SN\004&/\0324jq\026$\007\"B.\n\t\003:\023A\0027f]\036$\b\016C\003\\\023\021\005S\f\006\002)=\")q\f\030a\001Q\005\t\021\016C\003b\023\021\005#-A\007tiJL7\r^0%KF$S-\035\013\003{\rDQ\001\0321A\002\025\fQa\034;iKJ\004\"\001\0034\n\005\035\024!\001C#rk\006d\027\016^=\t\013%LA\021\0136\002!\t\f7/[:G_JD\025m\0355D_\022,W#A6\021\0071$xO\004\002ne:\021a.]\007\002_*\021\001OB\001\007yI|w\016\036 \n\003\025I!a\035\003\002\017A\f7m[1hK&\021QO\036\002\004'\026\f(BA:\005!\t\001\0020\003\002z\t\t\031\021I\\=\t\013mLA\021\001?\002\013\005\004\b\017\\=\025\r!k\030QBA\b\021\025q(\0201\001\000\003%q\027-\\3ta\006\034W\r\005\003\002\002\005\035ab\001\t\002\004%\031\021Q\001\003\002\rA\023X\rZ3g\023\021\tI!a\003\003\rM#(/\0338h\025\r\t)\001\002\005\006ci\004\rA\r\005\006+j\004\ra \005\007w&!\t!a\005\025\t\005U\021q\003\t\004YRd\005BB+\002\022\001\007q\020C\004\002\034%!\t\"!\b\002\023Q|7\013\036:j]\036\fD\003BA\020\003K\0012\001EA\021\023\r\t\031\003\002\002\005+:LG\017\003\005\002(\005e\001\031AA\025\003\t\031(\rE\002m\003WI1!!\fw\0055\031FO]5oO\n+\030\016\0343fe\"9\0211D\005\005R\005EB#A@\t\017\005U\022\002\"\021\0022\005AAo\\*ue&tw\rC\004\002:%!\t%a\017\002\027\t,\030\016\0343TiJLgn\032\013\005\003S\ti\004\003\005\002(\005]\002\031AA\025\021\035\t\t%\003C!\003\007\n!b^3mY\032|'/\\3e)\ri\024Q\t\005\007c\005}\002\031\001\032\t\017\005%\023\002\"\001\002L\0051!/Z7pm\026$B!!\024\002P9\021\001\002\001\005\007+\006\035\003\031A@\t\017\005%\023\002\"\001\002TQA\021QJA+\003/\nI\006\003\004\003#\002\ra \005\007c\005E\003\031\001\032\t\rU\013\t\0061\001\000\021%\ti&CI\001\n\003\ny&\001\tbaB,g\016\032\023eK\032\fW\017\034;%eU\021\021\021\r\026\004e\005\r4FAA3!\021\t9'!\035\016\005\005%$\002BA6\003[\n\021\"\0368dQ\026\0347.\0323\013\007\005=D!\001\006b]:|G/\031;j_:LA!a\035\002j\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\023\005]\024\"!A\005B\005e\024!\0049s_\022,8\r\036)sK\032L\0070\006\002\002|A!\021QPAD\033\t\tyH\003\003\002\002\006\r\025\001\0027b]\036T!!!\"\002\t)\fg/Y\005\005\003\023\ty\b\003\005\002\f&\t\t\021\"\001(\0031\001(o\0343vGR\f%/\033;z\021%\ty)CA\001\n\003\t\t*\001\bqe>$Wo\031;FY\026lWM\034;\025\007]\f\031\nC\005\002\026\0065\025\021!a\001Q\005\031\001\020J\031\t\023\005e\025\"!A\005B\005m\025a\0049s_\022,8\r^%uKJ\fGo\034:\026\005\005u\005cA\017!o\"I\021\021U\005\002\002\023%\0211U\001\fe\026\fGMU3t_24X\r\006\002\002&B!\021QPAT\023\021\tI+a \003\r=\023'.Z2u\001")
public final class Null {
  public static Iterator<Object> productIterator() {
    return Null$.MODULE$.productIterator();
  }
  
  public static Object productElement(int paramInt) {
    return Null$.MODULE$.productElement(paramInt);
  }
  
  public static int productArity() {
    return Null$.MODULE$.productArity();
  }
  
  public static String productPrefix() {
    return Null$.MODULE$.productPrefix();
  }
  
  public static NamespaceBinding append$default$2() {
    return Null$.MODULE$.append$default$2();
  }
  
  public static Null$ remove(String paramString1, NamespaceBinding paramNamespaceBinding, String paramString2) {
    return Null$.MODULE$.remove(paramString1, paramNamespaceBinding, paramString2);
  }
  
  public static Null$ remove(String paramString) {
    return Null$.MODULE$.remove(paramString);
  }
  
  public static boolean wellformed(NamespaceBinding paramNamespaceBinding) {
    return Null$.MODULE$.wellformed(paramNamespaceBinding);
  }
  
  public static StringBuilder buildString(StringBuilder paramStringBuilder) {
    return Null$.MODULE$.buildString(paramStringBuilder);
  }
  
  public static String toString() {
    return Null$.MODULE$.toString();
  }
  
  public static Seq<Node> apply(String paramString) {
    return Null$.MODULE$.apply(paramString);
  }
  
  public static Null$ apply(String paramString1, NamespaceBinding paramNamespaceBinding, String paramString2) {
    return Null$.MODULE$.apply(paramString1, paramNamespaceBinding, paramString2);
  }
  
  public static boolean strict_$eq$eq(Equality paramEquality) {
    return Null$.MODULE$.strict_$eq$eq(paramEquality);
  }
  
  public static int length(int paramInt) {
    return Null$.MODULE$.length(paramInt);
  }
  
  public static int length() {
    return Null$.MODULE$.length();
  }
  
  public static boolean isPrefixed() {
    return Null$.MODULE$.isPrefixed();
  }
  
  public static Null$ value() {
    return Null$.MODULE$.value();
  }
  
  public static Null$ key() {
    return Null$.MODULE$.key();
  }
  
  public static Null$ next() {
    return Null$.MODULE$.next();
  }
  
  public static boolean hasNext() {
    return Null$.MODULE$.hasNext();
  }
  
  public static Null$ getNamespace(Node paramNode) {
    return Null$.MODULE$.getNamespace(paramNode);
  }
  
  public static MetaData copy(MetaData paramMetaData) {
    return Null$.MODULE$.copy(paramMetaData);
  }
  
  public static MetaData filter(Function1<MetaData, Object> paramFunction1) {
    return Null$.MODULE$.filter(paramFunction1);
  }
  
  public static MetaData append(MetaData paramMetaData, NamespaceBinding paramNamespaceBinding) {
    return Null$.MODULE$.append(paramMetaData, paramNamespaceBinding);
  }
  
  public static int size() {
    return Null$.MODULE$.size();
  }
  
  public static Iterator<Nothing$> iterator() {
    return Null$.MODULE$.iterator();
  }
  
  public static boolean xml_$bang$eq(Object paramObject) {
    return Null$.MODULE$.xml_$bang$eq(paramObject);
  }
  
  public static boolean xml_$eq$eq(Object paramObject) {
    return Null$.MODULE$.xml_$eq$eq(paramObject);
  }
  
  public static boolean equals(Object paramObject) {
    return Null$.MODULE$.equals(paramObject);
  }
  
  public static int hashCode() {
    return Null$.MODULE$.hashCode();
  }
  
  public static boolean strict_$bang$eq(Equality paramEquality) {
    return Null$.MODULE$.strict_$bang$eq(paramEquality);
  }
  
  public static MetaData remove(String paramString1, Node paramNode, String paramString2) {
    return Null$.MODULE$.remove(paramString1, paramNode, paramString2);
  }
  
  public static Option<Seq<Node>> get(String paramString1, NamespaceBinding paramNamespaceBinding, String paramString2) {
    return Null$.MODULE$.get(paramString1, paramNamespaceBinding, paramString2);
  }
  
  public static Option<Seq<Node>> get(String paramString1, Node paramNode, String paramString2) {
    return Null$.MODULE$.get(paramString1, paramNode, paramString2);
  }
  
  public static Option<Seq<Node>> get(String paramString) {
    return Null$.MODULE$.get(paramString);
  }
  
  public static Map<String, String> asAttrMap() {
    return Null$.MODULE$.asAttrMap();
  }
  
  public static String prefixedKey() {
    return Null$.MODULE$.prefixedKey();
  }
  
  public static boolean canEqual(Object paramObject) {
    return Null$.MODULE$.canEqual(paramObject);
  }
  
  public static Seq<Node> apply(String paramString1, Node paramNode, String paramString2) {
    return Null$.MODULE$.apply(paramString1, paramNode, paramString2);
  }
  
  public static IterableView<MetaData, Iterable<MetaData>> view(int paramInt1, int paramInt2) {
    return Null$.MODULE$.view(paramInt1, paramInt2);
  }
  
  public static Object view() {
    return Null$.MODULE$.view();
  }
  
  public static Stream<MetaData> toStream() {
    return Null$.MODULE$.toStream();
  }
  
  public static <B> boolean sameElements(GenIterable<Object> paramGenIterable) {
    return Null$.MODULE$.sameElements(paramGenIterable);
  }
  
  public static <A1, That> Object zipWithIndex(CanBuildFrom<Iterable<MetaData>, Tuple2<Object, Object>, Object> paramCanBuildFrom) {
    return Null$.MODULE$.zipWithIndex(paramCanBuildFrom);
  }
  
  public static <B, A1, That> Object zipAll(GenIterable<Object> paramGenIterable, Object paramObject1, Object paramObject2, CanBuildFrom<Iterable<MetaData>, Tuple2<Object, Object>, Object> paramCanBuildFrom) {
    return Null$.MODULE$.zipAll(paramGenIterable, paramObject1, paramObject2, paramCanBuildFrom);
  }
  
  public static <A1, B, That> Object zip(GenIterable<Object> paramGenIterable, CanBuildFrom<Iterable<MetaData>, Tuple2<Object, Object>, Object> paramCanBuildFrom) {
    return Null$.MODULE$.zip(paramGenIterable, paramCanBuildFrom);
  }
  
  public static <B> void copyToArray(Object paramObject, int paramInt1, int paramInt2) {
    Null$.MODULE$.copyToArray(paramObject, paramInt1, paramInt2);
  }
  
  public static Iterable<MetaData> dropRight(int paramInt) {
    return (Iterable<MetaData>)Null$.MODULE$.dropRight(paramInt);
  }
  
  public static Iterable<MetaData> takeRight(int paramInt) {
    return (Iterable<MetaData>)Null$.MODULE$.takeRight(paramInt);
  }
  
  public static Iterator<Iterable<MetaData>> sliding(int paramInt1, int paramInt2) {
    return Null$.MODULE$.sliding(paramInt1, paramInt2);
  }
  
  public static Iterator<Iterable<MetaData>> sliding(int paramInt) {
    return Null$.MODULE$.sliding(paramInt);
  }
  
  public static Iterator<Iterable<MetaData>> grouped(int paramInt) {
    return Null$.MODULE$.grouped(paramInt);
  }
  
  public static Iterable<MetaData> takeWhile(Function1<MetaData, Object> paramFunction1) {
    return (Iterable<MetaData>)Null$.MODULE$.takeWhile(paramFunction1);
  }
  
  public static Iterable<MetaData> drop(int paramInt) {
    return (Iterable<MetaData>)Null$.MODULE$.drop(paramInt);
  }
  
  public static Iterable<MetaData> take(int paramInt) {
    return (Iterable<MetaData>)Null$.MODULE$.take(paramInt);
  }
  
  public static Iterable<MetaData> slice(int paramInt1, int paramInt2) {
    return (Iterable<MetaData>)Null$.MODULE$.slice(paramInt1, paramInt2);
  }
  
  public static Object head() {
    return Null$.MODULE$.head();
  }
  
  public static Iterator<MetaData> toIterator() {
    return Null$.MODULE$.toIterator();
  }
  
  public static Iterable<MetaData> toIterable() {
    return Null$.MODULE$.toIterable();
  }
  
  public static <B> Object reduceRight(Function2<MetaData, Object, Object> paramFunction2) {
    return Null$.MODULE$.reduceRight(paramFunction2);
  }
  
  public static <B> B foldRight(B paramB, Function2<MetaData, B, B> paramFunction2) {
    return (B)Null$.MODULE$.foldRight(paramB, paramFunction2);
  }
  
  public static boolean isEmpty() {
    return Null$.MODULE$.isEmpty();
  }
  
  public static Option<MetaData> find(Function1<MetaData, Object> paramFunction1) {
    return Null$.MODULE$.find(paramFunction1);
  }
  
  public static boolean exists(Function1<MetaData, Object> paramFunction1) {
    return Null$.MODULE$.exists(paramFunction1);
  }
  
  public static boolean forall(Function1<MetaData, Object> paramFunction1) {
    return Null$.MODULE$.forall(paramFunction1);
  }
  
  public static <U> void foreach(Function1<MetaData, U> paramFunction1) {
    Null$.MODULE$.foreach(paramFunction1);
  }
  
  public static Iterable<MetaData> toCollection(Iterable<MetaData> paramIterable) {
    return Null$.MODULE$.toCollection(paramIterable);
  }
  
  public static Iterable<MetaData> thisCollection() {
    return Null$.MODULE$.thisCollection();
  }
  
  public static Iterable<MetaData> seq() {
    return Null$.MODULE$.seq();
  }
  
  public static GenericCompanion<Iterable> companion() {
    return Null$.MODULE$.companion();
  }
  
  public static <A1> Object $div$colon$bslash(Object paramObject, Function2<Object, Object, Object> paramFunction2) {
    return Null$.MODULE$.$div$colon$bslash(paramObject, paramFunction2);
  }
  
  public static StringBuilder addString(StringBuilder paramStringBuilder) {
    return Null$.MODULE$.addString(paramStringBuilder);
  }
  
  public static StringBuilder addString(StringBuilder paramStringBuilder, String paramString) {
    return Null$.MODULE$.addString(paramStringBuilder, paramString);
  }
  
  public static StringBuilder addString(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3) {
    return Null$.MODULE$.addString(paramStringBuilder, paramString1, paramString2, paramString3);
  }
  
  public static String mkString() {
    return Null$.MODULE$.mkString();
  }
  
  public static String mkString(String paramString) {
    return Null$.MODULE$.mkString(paramString);
  }
  
  public static String mkString(String paramString1, String paramString2, String paramString3) {
    return Null$.MODULE$.mkString(paramString1, paramString2, paramString3);
  }
  
  public static <T, U> Map<T, U> toMap(Predef$.less.colon.less<MetaData, Tuple2<T, U>> paramless) {
    return Null$.MODULE$.toMap(paramless);
  }
  
  public static Vector<MetaData> toVector() {
    return Null$.MODULE$.toVector();
  }
  
  public static <B> Set<Object> toSet() {
    return Null$.MODULE$.toSet();
  }
  
  public static <B> Buffer<Object> toBuffer() {
    return Null$.MODULE$.toBuffer();
  }
  
  public static IndexedSeq<MetaData> toIndexedSeq() {
    return Null$.MODULE$.toIndexedSeq();
  }
  
  public static Seq<MetaData> toSeq() {
    return Null$.MODULE$.toSeq();
  }
  
  public static List<MetaData> toList() {
    return Null$.MODULE$.toList();
  }
  
  public static <B> Object toArray(ClassTag<Object> paramClassTag) {
    return Null$.MODULE$.toArray(paramClassTag);
  }
  
  public static <B> void copyToArray(Object paramObject) {
    Null$.MODULE$.copyToArray(paramObject);
  }
  
  public static <B> void copyToArray(Object paramObject, int paramInt) {
    Null$.MODULE$.copyToArray(paramObject, paramInt);
  }
  
  public static <B> void copyToBuffer(Buffer<Object> paramBuffer) {
    Null$.MODULE$.copyToBuffer(paramBuffer);
  }
  
  public static <B> MetaData minBy(Function1<MetaData, B> paramFunction1, Ordering<B> paramOrdering) {
    return (MetaData)Null$.MODULE$.minBy(paramFunction1, paramOrdering);
  }
  
  public static <B> MetaData maxBy(Function1<MetaData, B> paramFunction1, Ordering<B> paramOrdering) {
    return (MetaData)Null$.MODULE$.maxBy(paramFunction1, paramOrdering);
  }
  
  public static <B> MetaData max(Ordering<Object> paramOrdering) {
    return (MetaData)Null$.MODULE$.max(paramOrdering);
  }
  
  public static <B> MetaData min(Ordering<Object> paramOrdering) {
    return (MetaData)Null$.MODULE$.min(paramOrdering);
  }
  
  public static <B> Object product(Numeric<Object> paramNumeric) {
    return Null$.MODULE$.product(paramNumeric);
  }
  
  public static <B> Object sum(Numeric<Object> paramNumeric) {
    return Null$.MODULE$.sum(paramNumeric);
  }
  
  public static <B> B aggregate(B paramB, Function2<B, MetaData, B> paramFunction2, Function2<B, B, B> paramFunction21) {
    return (B)Null$.MODULE$.aggregate(paramB, paramFunction2, paramFunction21);
  }
  
  public static <A1> Object fold(Object paramObject, Function2<Object, Object, Object> paramFunction2) {
    return Null$.MODULE$.fold(paramObject, paramFunction2);
  }
  
  public static <A1> Option<Object> reduceOption(Function2<Object, Object, Object> paramFunction2) {
    return Null$.MODULE$.reduceOption(paramFunction2);
  }
  
  public static <A1> Object reduce(Function2<Object, Object, Object> paramFunction2) {
    return Null$.MODULE$.reduce(paramFunction2);
  }
  
  public static <B> Option<Object> reduceRightOption(Function2<MetaData, Object, Object> paramFunction2) {
    return Null$.MODULE$.reduceRightOption(paramFunction2);
  }
  
  public static <B> Option<Object> reduceLeftOption(Function2<Object, MetaData, Object> paramFunction2) {
    return Null$.MODULE$.reduceLeftOption(paramFunction2);
  }
  
  public static <B> Object reduceLeft(Function2<Object, MetaData, Object> paramFunction2) {
    return Null$.MODULE$.reduceLeft(paramFunction2);
  }
  
  public static <B> B foldLeft(B paramB, Function2<B, MetaData, B> paramFunction2) {
    return (B)Null$.MODULE$.foldLeft(paramB, paramFunction2);
  }
  
  public static <B> B $colon$bslash(B paramB, Function2<MetaData, B, B> paramFunction2) {
    return (B)Null$.MODULE$.$colon$bslash(paramB, paramFunction2);
  }
  
  public static <B> B $div$colon(B paramB, Function2<B, MetaData, B> paramFunction2) {
    return (B)Null$.MODULE$.$div$colon(paramB, paramFunction2);
  }
  
  public static <B> Option<B> collectFirst(PartialFunction<MetaData, B> paramPartialFunction) {
    return Null$.MODULE$.collectFirst(paramPartialFunction);
  }
  
  public static int count(Function1<MetaData, Object> paramFunction1) {
    return Null$.MODULE$.count(paramFunction1);
  }
  
  public static boolean nonEmpty() {
    return Null$.MODULE$.nonEmpty();
  }
  
  public static List<MetaData> reversed() {
    return Null$.MODULE$.reversed();
  }
  
  public static ParIterable<MetaData> par() {
    return (ParIterable<MetaData>)Null$.MODULE$.par();
  }
  
  public static FilterMonadic<MetaData, Traversable<MetaData>> withFilter(Function1<MetaData, Object> paramFunction1) {
    return Null$.MODULE$.withFilter(paramFunction1);
  }
  
  public static TraversableView<MetaData, Traversable<MetaData>> view(int paramInt1, int paramInt2) {
    return Null$.MODULE$.view(paramInt1, paramInt2);
  }
  
  public static Object view() {
    return Null$.MODULE$.view();
  }
  
  public static String stringPrefix() {
    return Null$.MODULE$.stringPrefix();
  }
  
  public static <Col> Col to(CanBuildFrom<Nothing$, MetaData, Col> paramCanBuildFrom) {
    return (Col)Null$.MODULE$.to(paramCanBuildFrom);
  }
  
  public static Traversable<MetaData> toTraversable() {
    return Null$.MODULE$.toTraversable();
  }
  
  public static Iterator<Traversable<MetaData>> inits() {
    return Null$.MODULE$.inits();
  }
  
  public static Iterator<Traversable<MetaData>> tails() {
    return Null$.MODULE$.tails();
  }
  
  public static Tuple2<Traversable<MetaData>, Traversable<MetaData>> splitAt(int paramInt) {
    return Null$.MODULE$.splitAt(paramInt);
  }
  
  public static Tuple2<Traversable<MetaData>, Traversable<MetaData>> span(Function1<MetaData, Object> paramFunction1) {
    return Null$.MODULE$.span(paramFunction1);
  }
  
  public static Traversable<MetaData> dropWhile(Function1<MetaData, Object> paramFunction1) {
    return (Traversable<MetaData>)Null$.MODULE$.dropWhile(paramFunction1);
  }
  
  public static Traversable<MetaData> init() {
    return (Traversable<MetaData>)Null$.MODULE$.init();
  }
  
  public static Option<MetaData> lastOption() {
    return Null$.MODULE$.lastOption();
  }
  
  public static Object last() {
    return Null$.MODULE$.last();
  }
  
  public static Traversable<MetaData> tail() {
    return (Traversable<MetaData>)Null$.MODULE$.tail();
  }
  
  public static Option<MetaData> headOption() {
    return Null$.MODULE$.headOption();
  }
  
  public static <B, That> That scanRight(B paramB, Function2<MetaData, B, B> paramFunction2, CanBuildFrom<Traversable<MetaData>, B, That> paramCanBuildFrom) {
    return (That)Null$.MODULE$.scanRight(paramB, paramFunction2, paramCanBuildFrom);
  }
  
  public static <B, That> That scanLeft(B paramB, Function2<B, MetaData, B> paramFunction2, CanBuildFrom<Traversable<MetaData>, B, That> paramCanBuildFrom) {
    return (That)Null$.MODULE$.scanLeft(paramB, paramFunction2, paramCanBuildFrom);
  }
  
  public static <B, That> Object scan(Object paramObject, Function2<Object, Object, Object> paramFunction2, CanBuildFrom<Traversable<MetaData>, Object, Object> paramCanBuildFrom) {
    return Null$.MODULE$.scan(paramObject, paramFunction2, paramCanBuildFrom);
  }
  
  public static <K> Map<K, Traversable<MetaData>> groupBy(Function1<MetaData, K> paramFunction1) {
    return Null$.MODULE$.groupBy(paramFunction1);
  }
  
  public static Tuple2<Traversable<MetaData>, Traversable<MetaData>> partition(Function1<MetaData, Object> paramFunction1) {
    return Null$.MODULE$.partition(paramFunction1);
  }
  
  public static <B, That> That collect(PartialFunction<MetaData, B> paramPartialFunction, CanBuildFrom<Traversable<MetaData>, B, That> paramCanBuildFrom) {
    return (That)Null$.MODULE$.collect(paramPartialFunction, paramCanBuildFrom);
  }
  
  public static Traversable<MetaData> filterNot(Function1<MetaData, Object> paramFunction1) {
    return (Traversable<MetaData>)Null$.MODULE$.filterNot(paramFunction1);
  }
  
  public static Traversable<MetaData> filter(Function1<MetaData, Object> paramFunction1) {
    return (Traversable<MetaData>)Null$.MODULE$.filter(paramFunction1);
  }
  
  public static <B, That> That flatMap(Function1<MetaData, GenTraversableOnce<B>> paramFunction1, CanBuildFrom<Traversable<MetaData>, B, That> paramCanBuildFrom) {
    return (That)Null$.MODULE$.flatMap(paramFunction1, paramCanBuildFrom);
  }
  
  public static <B, That> That map(Function1<MetaData, B> paramFunction1, CanBuildFrom<Traversable<MetaData>, B, That> paramCanBuildFrom) {
    return (That)Null$.MODULE$.map(paramFunction1, paramCanBuildFrom);
  }
  
  public static <B, That> Object $plus$plus$colon(Traversable<Object> paramTraversable, CanBuildFrom<Traversable<MetaData>, Object, Object> paramCanBuildFrom) {
    return Null$.MODULE$.$plus$plus$colon(paramTraversable, paramCanBuildFrom);
  }
  
  public static <B, That> Object $plus$plus$colon(TraversableOnce<Object> paramTraversableOnce, CanBuildFrom<Traversable<MetaData>, Object, Object> paramCanBuildFrom) {
    return Null$.MODULE$.$plus$plus$colon(paramTraversableOnce, paramCanBuildFrom);
  }
  
  public static <B, That> Object $plus$plus(GenTraversableOnce<Object> paramGenTraversableOnce, CanBuildFrom<Traversable<MetaData>, Object, Object> paramCanBuildFrom) {
    return Null$.MODULE$.$plus$plus(paramGenTraversableOnce, paramCanBuildFrom);
  }
  
  public static boolean hasDefiniteSize() {
    return Null$.MODULE$.hasDefiniteSize();
  }
  
  public static Combiner<MetaData, ParIterable<MetaData>> parCombiner() {
    return Null$.MODULE$.parCombiner();
  }
  
  public static Traversable<MetaData> toCollection(Traversable<MetaData> paramTraversable) {
    return Null$.MODULE$.toCollection(paramTraversable);
  }
  
  public static Traversable<MetaData> thisCollection() {
    return Null$.MODULE$.thisCollection();
  }
  
  public static boolean isTraversableAgain() {
    return Null$.MODULE$.isTraversableAgain();
  }
  
  public static Traversable<MetaData> repr() {
    return (Traversable<MetaData>)Null$.MODULE$.repr();
  }
  
  public static <B> Traversable<Traversable<B>> transpose(Function1<MetaData, GenTraversableOnce<B>> paramFunction1) {
    return (Traversable<Traversable<B>>)Null$.MODULE$.transpose(paramFunction1);
  }
  
  public static <B> Traversable<B> flatten(Function1<MetaData, GenTraversableOnce<B>> paramFunction1) {
    return (Traversable<B>)Null$.MODULE$.flatten(paramFunction1);
  }
  
  public static <A1, A2, A3> Tuple3<Traversable<A1>, Traversable<A2>, Traversable<A3>> unzip3(Function1<MetaData, Tuple3<A1, A2, A3>> paramFunction1) {
    return Null$.MODULE$.unzip3(paramFunction1);
  }
  
  public static <A1, A2> Tuple2<Traversable<A1>, Traversable<A2>> unzip(Function1<MetaData, Tuple2<A1, A2>> paramFunction1) {
    return Null$.MODULE$.unzip(paramFunction1);
  }
  
  public static <B> Builder<B, Traversable<B>> genericBuilder() {
    return Null$.MODULE$.genericBuilder();
  }
  
  public static Builder<MetaData, Traversable<MetaData>> newBuilder() {
    return Null$.MODULE$.newBuilder();
  }
  
  public static Traversable<MetaData> seq() {
    return Null$.MODULE$.seq();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Null.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */