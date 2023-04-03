/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Proxy;
/*    */ import scala.Tuple2;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenMapLike;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenSetLike;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableLike;
/*    */ import scala.collection.IterableProxyLike;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.MapLike;
/*    */ import scala.collection.MapProxyLike;
/*    */ import scala.collection.Parallelizable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Set;
/*    */ import scala.collection.SetLike;
/*    */ import scala.collection.SetProxyLike;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableProxyLike;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.FilterMonadic;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericSetTemplate;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.immutable.ParMap;
/*    */ import scala.collection.parallel.immutable.ParSet;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\rbaB\001\003!\003\r\t!\003\002\t\033\006\004\bK]8ys*\0211\001B\001\nS6lW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)2AC\013 '\021\0011bD\021\021\0051iQ\"\001\004\n\00591!AB!osJ+g\r\005\003\021#MqR\"\001\002\n\005I\021!aA'baB\021A#\006\007\001\t\0251\002A1\001\030\005\005\t\025C\001\r\034!\ta\021$\003\002\033\r\t9aj\034;iS:<\007C\001\007\035\023\tibAA\002B]f\004\"\001F\020\005\r\001\002AQ1\001\030\005\005\021\005#\002\022$'yyQ\"\001\003\n\005\021\"!\001D'baB\023x\016_=MS.,\007\"\002\024\001\t\0039\023A\002\023j]&$H\005F\001)!\ta\021&\003\002+\r\t!QK\\5u\021\025a\003\001\"\021.\003\021\021X\r\035:\026\0039\002B\001\005\001\024=!)\001\007\001C\005c\005Aa.Z<Qe>D\0300\006\0023kQ\0211\007\017\t\005!\001\031B\007\005\002\025k\021)ag\fb\001o\t\021!)M\t\003=mAQ!O\030A\002i\nqA\\3x'\026dg\r\005\003\021#M!\004\"\002\037\001\t\003j\023!B3naRL\b\"\002 \001\t\003z\024aB;qI\006$X\rZ\013\003\001\016#2!\021#G!\021\001\002a\005\"\021\005Q\031E!\002\034>\005\0049\004\"B#>\001\004\031\022aA6fs\")q)\020a\001\005\006)a/\0317vK\")\021\n\001C!\025\0061A%\\5okN$\"AL&\t\013\025C\005\031A\n\t\0135\003A\021\t(\002\013\021\002H.^:\026\005=\023FC\001)T!\021\001\022cE)\021\005Q\021F!\002\034M\005\0049\004\"\002+M\001\004)\026AA6w!\021aakE)\n\005]3!A\002+va2,'\007C\003N\001\021\005\023,\006\002[;R!1LX1d!\021\001\002a\005/\021\005QiF!\002\034Y\005\0049\004\"B0Y\001\004\001\027!B3mK6\f\004\003\002\007W'qCQA\031-A\002\001\fQ!\0327f[JBQ\001\032-A\002\025\fQ!\0327f[N\0042\001\0044a\023\t9gA\001\006=e\026\004X-\031;fIzBQ!\033\001\005B)\f!\002\n9mkN$\003\017\\;t+\tYg\016\006\002m_B!\001\003A\nn!\t!b\016B\0037Q\n\007q\007C\003qQ\002\007\021/\001\002ygB\031!E\035;\n\005M$!AE$f]R\023\030M^3sg\006\024G.Z(oG\026\004B\001\004,\024[\")a\017\001C!o\00611.Z=TKR,\022\001\037\t\004!e\034\022B\001>\003\005\r\031V\r\036\005\006y\002!\t%`\001\013M&dG/\032:LKf\034HCA\b\021\031y8\0201\001\002\002\005\t\001\017\005\004\r\003\007\031\022qA\005\004\003\0131!!\003$v]\016$\030n\03482!\ra\021\021B\005\004\003\0271!a\002\"p_2,\027M\034\005\b\003\037\001A\021IA\t\003%i\027\r\035,bYV,7/\006\003\002\024\005eA\003BA\013\003;\001R\001E\t\024\003/\0012\001FA\r\t\035\tY\"!\004C\002]\021\021a\021\005\t\003?\ti\0011\001\002\"\005\ta\r\005\004\r\003\007q\022q\003")
/*    */ public interface MapProxy<A, B> extends Map<A, B>, MapProxyLike<A, B, Map<A, B>> {
/*    */   MapProxy<A, B> repr();
/*    */   
/*    */   MapProxy<A, B> empty();
/*    */   
/*    */   <B1> MapProxy<A, B1> updated(A paramA, B1 paramB1);
/*    */   
/*    */   MapProxy<A, B> $minus(A paramA);
/*    */   
/*    */   <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple2);
/*    */   
/*    */   <B1> MapProxy<A, B1> $plus(Tuple2<A, B1> paramTuple21, Tuple2<A, B1> paramTuple22, Seq<Tuple2<A, B1>> paramSeq);
/*    */   
/*    */   <B1> MapProxy<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> paramGenTraversableOnce);
/*    */   
/*    */   Set<A> keySet();
/*    */   
/*    */   Map<A, B> filterKeys(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <C> Map<A, C> mapValues(Function1<B, C> paramFunction1);
/*    */   
/*    */   public class MapProxy$$anon$1 implements MapProxy<A, Object> {
/*    */     private final Map<A, Object> self;
/*    */     
/*    */     public MapProxy<A, Object> repr() {
/* 28 */       return MapProxy$class.repr(this);
/*    */     }
/*    */     
/*    */     public MapProxy<A, Object> empty() {
/* 28 */       return MapProxy$class.empty(this);
/*    */     }
/*    */     
/*    */     public <B1> MapProxy<A, B1> updated(Object key, Object value) {
/* 28 */       return MapProxy$class.updated(this, key, value);
/*    */     }
/*    */     
/*    */     public MapProxy<A, Object> $minus(Object key) {
/* 28 */       return MapProxy$class.$minus(this, key);
/*    */     }
/*    */     
/*    */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 28 */       return MapProxy$class.$plus(this, kv);
/*    */     }
/*    */     
/*    */     public <B1> MapProxy<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 28 */       return MapProxy$class.$plus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public <B1> MapProxy<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 28 */       return MapProxy$class.$plus$plus(this, xs);
/*    */     }
/*    */     
/*    */     public Set<A> keySet() {
/* 28 */       return MapProxy$class.keySet(this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> filterKeys(Function1 p) {
/* 28 */       return MapProxy$class.filterKeys(this, p);
/*    */     }
/*    */     
/*    */     public <C> Map<A, C> mapValues(Function1 f) {
/* 28 */       return MapProxy$class.mapValues(this, f);
/*    */     }
/*    */     
/*    */     public Option<Object> get(Object key) {
/* 28 */       return MapProxyLike.class.get(this, key);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<A, Object>> iterator() {
/* 28 */       return MapProxyLike.class.iterator(this);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 28 */       return MapProxyLike.class.isEmpty(this);
/*    */     }
/*    */     
/*    */     public <B1> B1 getOrElse(Object key, Function0 default) {
/* 28 */       return (B1)MapProxyLike.class.getOrElse(this, key, default);
/*    */     }
/*    */     
/*    */     public Object apply(Object key) {
/* 28 */       return MapProxyLike.class.apply(this, key);
/*    */     }
/*    */     
/*    */     public boolean contains(Object key) {
/* 28 */       return MapProxyLike.class.contains(this, key);
/*    */     }
/*    */     
/*    */     public boolean isDefinedAt(Object key) {
/* 28 */       return MapProxyLike.class.isDefinedAt(this, key);
/*    */     }
/*    */     
/*    */     public Iterator<A> keysIterator() {
/* 28 */       return MapProxyLike.class.keysIterator(this);
/*    */     }
/*    */     
/*    */     public Iterable<A> keys() {
/* 28 */       return MapProxyLike.class.keys(this);
/*    */     }
/*    */     
/*    */     public Iterable<Object> values() {
/* 28 */       return MapProxyLike.class.values(this);
/*    */     }
/*    */     
/*    */     public Iterator<Object> valuesIterator() {
/* 28 */       return MapProxyLike.class.valuesIterator(this);
/*    */     }
/*    */     
/*    */     public Object default(Object key) {
/* 28 */       return MapProxyLike.class.default(this, key);
/*    */     }
/*    */     
/*    */     public Map<A, Object> filterNot(Function1 p) {
/* 28 */       return (Map<A, Object>)MapProxyLike.class.filterNot(this, p);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 28 */       return MapProxyLike.class.addString(this, b, start, sep, end);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, Object>> grouped(int size) {
/* 28 */       return IterableProxyLike.class.grouped((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, Object>> sliding(int size) {
/* 28 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, Object>> sliding(int size, int step) {
/* 28 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size, step);
/*    */     }
/*    */     
/*    */     public Map<A, Object> takeRight(int n) {
/* 28 */       return (Map<A, Object>)IterableProxyLike.class.takeRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Map<A, Object> dropRight(int n) {
/* 28 */       return (Map<A, Object>)IterableProxyLike.class.dropRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 28 */       return (That)IterableProxyLike.class.zip((IterableProxyLike)this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 28 */       return (That)IterableProxyLike.class.zipAll((IterableProxyLike)this, that, thisElem, thatElem, bf);
/*    */     }
/*    */     
/*    */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 28 */       return (That)IterableProxyLike.class.zipWithIndex((IterableProxyLike)this, bf);
/*    */     }
/*    */     
/*    */     public <B> boolean sameElements(GenIterable that) {
/* 28 */       return IterableProxyLike.class.sameElements((IterableProxyLike)this, that);
/*    */     }
/*    */     
/*    */     public Object view() {
/* 28 */       return IterableProxyLike.class.view((IterableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IterableView<Tuple2<A, Object>, Map<A, Object>> view(int from, int until) {
/* 28 */       return IterableProxyLike.class.view((IterableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public <B> void foreach(Function1 f) {
/* 28 */       TraversableProxyLike.class.foreach((TraversableProxyLike)this, f);
/*    */     }
/*    */     
/*    */     public boolean nonEmpty() {
/* 28 */       return TraversableProxyLike.class.nonEmpty((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public int size() {
/* 28 */       return TraversableProxyLike.class.size((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public boolean hasDefiniteSize() {
/* 28 */       return TraversableProxyLike.class.hasDefiniteSize((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 28 */       return (That)TraversableProxyLike.class.$plus$plus((TraversableProxyLike)this, xs, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 28 */       return (That)TraversableProxyLike.class.map((TraversableProxyLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 28 */       return (That)TraversableProxyLike.class.flatMap((TraversableProxyLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public Map<A, Object> filter(Function1 p) {
/* 28 */       return (Map<A, Object>)TraversableProxyLike.class.filter((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 28 */       return (That)TraversableProxyLike.class.collect((TraversableProxyLike)this, pf, bf);
/*    */     }
/*    */     
/*    */     public Tuple2<Map<A, Object>, Map<A, Object>> partition(Function1 p) {
/* 28 */       return TraversableProxyLike.class.partition((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <K> Map<K, Map<A, Object>> groupBy(Function1 f) {
/* 28 */       return TraversableProxyLike.class.groupBy((TraversableProxyLike)this, f);
/*    */     }
/*    */     
/*    */     public boolean forall(Function1 p) {
/* 28 */       return TraversableProxyLike.class.forall((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public boolean exists(Function1 p) {
/* 28 */       return TraversableProxyLike.class.exists((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/* 28 */       return TraversableProxyLike.class.count((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, Object>> find(Function1 p) {
/* 28 */       return TraversableProxyLike.class.find((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B> B foldLeft(Object z, Function2 op) {
/* 28 */       return (B)TraversableProxyLike.class.foldLeft((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $div$colon(Object z, Function2 op) {
/* 28 */       return (B)TraversableProxyLike.class.$div$colon((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldRight(Object z, Function2 op) {
/* 28 */       return (B)TraversableProxyLike.class.foldRight((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 28 */       return (B)TraversableProxyLike.class.$colon$bslash((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceLeft(Function2 op) {
/* 28 */       return (B)TraversableProxyLike.class.reduceLeft((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 28 */       return TraversableProxyLike.class.reduceLeftOption((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceRight(Function2 op) {
/* 28 */       return (B)TraversableProxyLike.class.reduceRight((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 28 */       return TraversableProxyLike.class.reduceRightOption((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 28 */       return (That)TraversableProxyLike.class.scanLeft((TraversableProxyLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 28 */       return (That)TraversableProxyLike.class.scanRight((TraversableProxyLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B> B sum(Numeric num) {
/* 28 */       return (B)TraversableProxyLike.class.sum((TraversableProxyLike)this, num);
/*    */     }
/*    */     
/*    */     public <B> B product(Numeric num) {
/* 28 */       return (B)TraversableProxyLike.class.product((TraversableProxyLike)this, num);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, Object> min(Ordering cmp) {
/* 28 */       return (Tuple2<A, Object>)TraversableProxyLike.class.min((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, Object> max(Ordering cmp) {
/* 28 */       return (Tuple2<A, Object>)TraversableProxyLike.class.max((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public Tuple2<A, Object> head() {
/* 28 */       return (Tuple2<A, Object>)TraversableProxyLike.class.head((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, Object>> headOption() {
/* 28 */       return TraversableProxyLike.class.headOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> tail() {
/* 28 */       return (Map<A, Object>)TraversableProxyLike.class.tail((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Tuple2<A, Object> last() {
/* 28 */       return (Tuple2<A, Object>)TraversableProxyLike.class.last((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, Object>> lastOption() {
/* 28 */       return TraversableProxyLike.class.lastOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> init() {
/* 28 */       return (Map<A, Object>)TraversableProxyLike.class.init((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> take(int n) {
/* 28 */       return (Map<A, Object>)TraversableProxyLike.class.take((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Map<A, Object> drop(int n) {
/* 28 */       return (Map<A, Object>)TraversableProxyLike.class.drop((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Map<A, Object> slice(int from, int until) {
/* 28 */       return (Map<A, Object>)TraversableProxyLike.class.slice((TraversableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public Map<A, Object> takeWhile(Function1 p) {
/* 28 */       return (Map<A, Object>)TraversableProxyLike.class.takeWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Map<A, Object> dropWhile(Function1 p) {
/* 28 */       return (Map<A, Object>)TraversableProxyLike.class.dropWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Map<A, Object>, Map<A, Object>> span(Function1 p) {
/* 28 */       return TraversableProxyLike.class.span((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Map<A, Object>, Map<A, Object>> splitAt(int n) {
/* 28 */       return TraversableProxyLike.class.splitAt((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public <B> void copyToBuffer(Buffer dest) {
/* 28 */       TraversableProxyLike.class.copyToBuffer((TraversableProxyLike)this, dest);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start, int len) {
/* 28 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs, start, len);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start) {
/* 28 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs, start);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs) {
/* 28 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs);
/*    */     }
/*    */     
/*    */     public <B> Object toArray(ClassTag evidence$1) {
/* 28 */       return TraversableProxyLike.class.toArray((TraversableProxyLike)this, evidence$1);
/*    */     }
/*    */     
/*    */     public List<Tuple2<A, Object>> toList() {
/* 28 */       return TraversableProxyLike.class.toList((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterable<Tuple2<A, Object>> toIterable() {
/* 28 */       return TraversableProxyLike.class.toIterable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Seq<Tuple2<A, Object>> toSeq() {
/* 28 */       return TraversableProxyLike.class.toSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<Tuple2<A, Object>> toIndexedSeq() {
/* 28 */       return TraversableProxyLike.class.toIndexedSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Buffer<B> toBuffer() {
/* 28 */       return TraversableProxyLike.class.toBuffer((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Stream<Tuple2<A, Object>> toStream() {
/* 28 */       return TraversableProxyLike.class.toStream((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Set<B> toSet() {
/* 28 */       return TraversableProxyLike.class.toSet((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 28 */       return TraversableProxyLike.class.toMap((TraversableProxyLike)this, ev);
/*    */     }
/*    */     
/*    */     public Traversable<Tuple2<A, Object>> toTraversable() {
/* 28 */       return TraversableProxyLike.class.toTraversable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<A, Object>> toIterator() {
/* 28 */       return TraversableProxyLike.class.toIterator((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public String mkString(String start, String sep, String end) {
/* 28 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this, start, sep, end);
/*    */     }
/*    */     
/*    */     public String mkString(String sep) {
/* 28 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this, sep);
/*    */     }
/*    */     
/*    */     public String mkString() {
/* 28 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String sep) {
/* 28 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b, sep);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b) {
/* 28 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b);
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 28 */       return TraversableProxyLike.class.stringPrefix((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 28 */       return Proxy.class.hashCode((Proxy)this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object that) {
/* 28 */       return Proxy.class.equals((Proxy)this, that);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 28 */       return Proxy.class.toString((Proxy)this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> seq() {
/* 28 */       return Map$class.seq(this);
/*    */     }
/*    */     
/*    */     public <B1> Map<A, B1> withDefault(Function1 d) {
/* 28 */       return Map$class.withDefault(this, d);
/*    */     }
/*    */     
/*    */     public <B1> Map<A, B1> withDefaultValue(Object d) {
/* 28 */       return Map$class.withDefaultValue(this, d);
/*    */     }
/*    */     
/*    */     public Combiner<Tuple2<A, Object>, ParMap<A, Object>> parCombiner() {
/* 28 */       return MapLike$class.parCombiner(this);
/*    */     }
/*    */     
/*    */     public <C, That> That transform(Function2 f, CanBuildFrom bf) {
/* 28 */       return (That)MapLike$class.transform(this, f, bf);
/*    */     }
/*    */     
/*    */     public Builder<Tuple2<A, Object>, Map<A, Object>> newBuilder() {
/* 28 */       return MapLike.class.newBuilder(this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> $minus(Object elem1, Object elem2, Seq elems) {
/* 28 */       return (Map<A, Object>)Subtractable.class.$minus((Subtractable)this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Map<A, Object> $minus$minus(GenTraversableOnce xs) {
/* 28 */       return (Map<A, Object>)Subtractable.class.$minus$minus((Subtractable)this, xs);
/*    */     }
/*    */     
/*    */     public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 28 */       return PartialFunction.class.orElse((PartialFunction)this, that);
/*    */     }
/*    */     
/*    */     public <C> PartialFunction<A, C> andThen(Function1 k) {
/* 28 */       return PartialFunction.class.andThen((PartialFunction)this, k);
/*    */     }
/*    */     
/*    */     public Function1<A, Option<Object>> lift() {
/* 28 */       return PartialFunction.class.lift((PartialFunction)this);
/*    */     }
/*    */     
/*    */     public <A1 extends A, B1> B1 applyOrElse(Object x, Function1 default) {
/* 28 */       return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*    */     }
/*    */     
/*    */     public <U> Function1<A, Object> runWith(Function1 action) {
/* 28 */       return PartialFunction.class.runWith((PartialFunction)this, action);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZD$sp(double v1) {
/* 28 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDD$sp(double v1) {
/* 28 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFD$sp(double v1) {
/* 28 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcID$sp(double v1) {
/* 28 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJD$sp(double v1) {
/* 28 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVD$sp(double v1) {
/* 28 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZF$sp(float v1) {
/* 28 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDF$sp(float v1) {
/* 28 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFF$sp(float v1) {
/* 28 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIF$sp(float v1) {
/* 28 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJF$sp(float v1) {
/* 28 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVF$sp(float v1) {
/* 28 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int v1) {
/* 28 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDI$sp(int v1) {
/* 28 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFI$sp(int v1) {
/* 28 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcII$sp(int v1) {
/* 28 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJI$sp(int v1) {
/* 28 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVI$sp(int v1) {
/* 28 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZJ$sp(long v1) {
/* 28 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDJ$sp(long v1) {
/* 28 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFJ$sp(long v1) {
/* 28 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIJ$sp(long v1) {
/* 28 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJJ$sp(long v1) {
/* 28 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVJ$sp(long v1) {
/* 28 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose(Function1 g) {
/* 28 */       return Function1.class.compose((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 28 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 28 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public GenericCompanion<Iterable> companion() {
/* 28 */       return Iterable$class.companion(this);
/*    */     }
/*    */     
/*    */     public Iterable<Tuple2<A, Object>> thisCollection() {
/* 28 */       return IterableLike.class.thisCollection(this);
/*    */     }
/*    */     
/*    */     public Iterable<Tuple2<A, Object>> toCollection(Object repr) {
/* 28 */       return IterableLike.class.toCollection(this, repr);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object that) {
/* 28 */       return IterableLike.class.canEqual(this, that);
/*    */     }
/*    */     
/*    */     public <B> Builder<B, Iterable<B>> genericBuilder() {
/* 28 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*    */     }
/*    */     
/*    */     public <A1, A2> Tuple2<Iterable<A1>, Iterable<A2>> unzip(Function1 asPair) {
/* 28 */       return GenericTraversableTemplate.class.unzip(this, asPair);
/*    */     }
/*    */     
/*    */     public <A1, A2, A3> Tuple3<Iterable<A1>, Iterable<A2>, Iterable<A3>> unzip3(Function1 asTriple) {
/* 28 */       return GenericTraversableTemplate.class.unzip3(this, asTriple);
/*    */     }
/*    */     
/*    */     public <B> Iterable<B> flatten(Function1 asTraversable) {
/* 28 */       return (Iterable<B>)GenericTraversableTemplate.class.flatten(this, asTraversable);
/*    */     }
/*    */     
/*    */     public <B> Iterable<Iterable<B>> transpose(Function1 asTraversable) {
/* 28 */       return (Iterable<Iterable<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*    */     }
/*    */     
/*    */     public final boolean isTraversableAgain() {
/* 28 */       return TraversableLike.class.isTraversableAgain(this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 28 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 28 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 28 */       return (That)TraversableLike.class.scan(this, z, op, cbf);
/*    */     }
/*    */     
/*    */     public Map<A, Object> sliceWithKnownDelta(int from, int until, int delta) {
/* 28 */       return (Map<A, Object>)TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
/*    */     }
/*    */     
/*    */     public Map<A, Object> sliceWithKnownBound(int from, int until) {
/* 28 */       return (Map<A, Object>)TraversableLike.class.sliceWithKnownBound(this, from, until);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, Object>> tails() {
/* 28 */       return TraversableLike.class.tails(this);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, Object>> inits() {
/* 28 */       return TraversableLike.class.inits(this);
/*    */     }
/*    */     
/*    */     public <Col> Col to(CanBuildFrom cbf) {
/* 28 */       return (Col)TraversableLike.class.to(this, cbf);
/*    */     }
/*    */     
/*    */     public FilterMonadic<Tuple2<A, Object>, Map<A, Object>> withFilter(Function1 p) {
/* 28 */       return TraversableLike.class.withFilter(this, p);
/*    */     }
/*    */     
/*    */     public ParMap<A, Object> par() {
/* 28 */       return (ParMap<A, Object>)Parallelizable.class.par(this);
/*    */     }
/*    */     
/*    */     public List<Tuple2<A, Object>> reversed() {
/* 28 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 28 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*    */     }
/*    */     
/*    */     public <A1> A1 reduce(Function2 op) {
/* 28 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 28 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 fold(Object z, Function2 op) {
/* 28 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 28 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, Object> maxBy(Function1 f, Ordering cmp) {
/* 28 */       return (Tuple2<A, Object>)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, Object> minBy(Function1 f, Ordering cmp) {
/* 28 */       return (Tuple2<A, Object>)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public Vector<Tuple2<A, Object>> toVector() {
/* 28 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 28 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public Map<A, Object> self() {
/* 28 */       return this.self;
/*    */     }
/*    */     
/*    */     public MapProxy$$anon$1(MapProxy $outer, Map<A, Object> newSelf$1) {
/* 28 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 28 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 28 */       Parallelizable.class.$init$(this);
/* 28 */       TraversableLike.class.$init$(this);
/* 28 */       GenericTraversableTemplate.class.$init$(this);
/* 28 */       GenTraversable.class.$init$((GenTraversable)this);
/* 28 */       Traversable.class.$init$(this);
/* 28 */       Traversable$class.$init$(this);
/* 28 */       GenIterable.class.$init$((GenIterable)this);
/* 28 */       IterableLike.class.$init$(this);
/* 28 */       Iterable.class.$init$(this);
/* 28 */       Iterable$class.$init$(this);
/* 28 */       GenMapLike.class.$init$((GenMapLike)this);
/* 28 */       Function1.class.$init$((Function1)this);
/* 28 */       PartialFunction.class.$init$((PartialFunction)this);
/* 28 */       Subtractable.class.$init$((Subtractable)this);
/* 28 */       MapLike.class.$init$(this);
/* 28 */       Map.class.$init$(this);
/* 28 */       MapLike$class.$init$(this);
/* 28 */       Map$class.$init$(this);
/* 28 */       Proxy.class.$init$((Proxy)this);
/* 28 */       TraversableProxyLike.class.$init$((TraversableProxyLike)this);
/* 28 */       IterableProxyLike.class.$init$((IterableProxyLike)this);
/* 28 */       MapProxyLike.class.$init$(this);
/* 28 */       MapProxy$class.$init$(this);
/* 28 */       this.self = newSelf$1;
/*    */     }
/*    */   }
/*    */   
/*    */   public class MapProxy$$anon$2 implements SetProxy<A> {
/*    */     private final Set<A> self;
/*    */     
/*    */     public SetProxy<A> repr() {
/* 38 */       return SetProxy$class.repr(this);
/*    */     }
/*    */     
/*    */     public SetProxy<A> empty() {
/* 38 */       return SetProxy$class.empty(this);
/*    */     }
/*    */     
/*    */     public SetProxy<A> $plus(Object elem) {
/* 38 */       return SetProxy$class.$plus(this, elem);
/*    */     }
/*    */     
/*    */     public SetProxy<A> $minus(Object elem) {
/* 38 */       return SetProxy$class.$minus(this, elem);
/*    */     }
/*    */     
/*    */     public boolean contains(Object elem) {
/* 38 */       return SetProxyLike.class.contains(this, elem);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 38 */       return SetProxyLike.class.isEmpty(this);
/*    */     }
/*    */     
/*    */     public boolean apply(Object elem) {
/* 38 */       return SetProxyLike.class.apply(this, elem);
/*    */     }
/*    */     
/*    */     public Set<A> intersect(GenSet that) {
/* 38 */       return (Set<A>)SetProxyLike.class.intersect(this, that);
/*    */     }
/*    */     
/*    */     public Set<A> $amp(GenSet that) {
/* 38 */       return (Set<A>)SetProxyLike.class.$amp(this, that);
/*    */     }
/*    */     
/*    */     public Set<A> union(GenSet that) {
/* 38 */       return (Set<A>)SetProxyLike.class.union(this, that);
/*    */     }
/*    */     
/*    */     public Set<A> $bar(GenSet that) {
/* 38 */       return (Set<A>)SetProxyLike.class.$bar(this, that);
/*    */     }
/*    */     
/*    */     public Set<A> diff(GenSet that) {
/* 38 */       return (Set<A>)SetProxyLike.class.diff(this, that);
/*    */     }
/*    */     
/*    */     public Set<A> $amp$tilde(GenSet that) {
/* 38 */       return (Set<A>)SetProxyLike.class.$amp$tilde(this, that);
/*    */     }
/*    */     
/*    */     public boolean subsetOf(GenSet that) {
/* 38 */       return SetProxyLike.class.subsetOf(this, that);
/*    */     }
/*    */     
/*    */     public Iterator<A> iterator() {
/* 38 */       return IterableProxyLike.class.iterator((IterableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> grouped(int size) {
/* 38 */       return IterableProxyLike.class.grouped((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> sliding(int size) {
/* 38 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> sliding(int size, int step) {
/* 38 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size, step);
/*    */     }
/*    */     
/*    */     public Set<A> takeRight(int n) {
/* 38 */       return (Set<A>)IterableProxyLike.class.takeRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Set<A> dropRight(int n) {
/* 38 */       return (Set<A>)IterableProxyLike.class.dropRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 38 */       return (That)IterableProxyLike.class.zip((IterableProxyLike)this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 38 */       return (That)IterableProxyLike.class.zipAll((IterableProxyLike)this, that, thisElem, thatElem, bf);
/*    */     }
/*    */     
/*    */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 38 */       return (That)IterableProxyLike.class.zipWithIndex((IterableProxyLike)this, bf);
/*    */     }
/*    */     
/*    */     public <B> boolean sameElements(GenIterable that) {
/* 38 */       return IterableProxyLike.class.sameElements((IterableProxyLike)this, that);
/*    */     }
/*    */     
/*    */     public Object view() {
/* 38 */       return IterableProxyLike.class.view((IterableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IterableView<A, Set<A>> view(int from, int until) {
/* 38 */       return IterableProxyLike.class.view((IterableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public <B> void foreach(Function1 f) {
/* 38 */       TraversableProxyLike.class.foreach((TraversableProxyLike)this, f);
/*    */     }
/*    */     
/*    */     public boolean nonEmpty() {
/* 38 */       return TraversableProxyLike.class.nonEmpty((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public int size() {
/* 38 */       return TraversableProxyLike.class.size((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public boolean hasDefiniteSize() {
/* 38 */       return TraversableProxyLike.class.hasDefiniteSize((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 38 */       return (That)TraversableProxyLike.class.$plus$plus((TraversableProxyLike)this, xs, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 38 */       return (That)TraversableProxyLike.class.map((TraversableProxyLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 38 */       return (That)TraversableProxyLike.class.flatMap((TraversableProxyLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public Set<A> filter(Function1 p) {
/* 38 */       return (Set<A>)TraversableProxyLike.class.filter((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Set<A> filterNot(Function1 p) {
/* 38 */       return (Set<A>)TraversableProxyLike.class.filterNot((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 38 */       return (That)TraversableProxyLike.class.collect((TraversableProxyLike)this, pf, bf);
/*    */     }
/*    */     
/*    */     public Tuple2<Set<A>, Set<A>> partition(Function1 p) {
/* 38 */       return TraversableProxyLike.class.partition((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <K> Map<K, Set<A>> groupBy(Function1 f) {
/* 38 */       return TraversableProxyLike.class.groupBy((TraversableProxyLike)this, f);
/*    */     }
/*    */     
/*    */     public boolean forall(Function1 p) {
/* 38 */       return TraversableProxyLike.class.forall((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public boolean exists(Function1 p) {
/* 38 */       return TraversableProxyLike.class.exists((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/* 38 */       return TraversableProxyLike.class.count((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Option<A> find(Function1 p) {
/* 38 */       return TraversableProxyLike.class.find((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B> B foldLeft(Object z, Function2 op) {
/* 38 */       return (B)TraversableProxyLike.class.foldLeft((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $div$colon(Object z, Function2 op) {
/* 38 */       return (B)TraversableProxyLike.class.$div$colon((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldRight(Object z, Function2 op) {
/* 38 */       return (B)TraversableProxyLike.class.foldRight((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 38 */       return (B)TraversableProxyLike.class.$colon$bslash((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceLeft(Function2 op) {
/* 38 */       return (B)TraversableProxyLike.class.reduceLeft((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 38 */       return TraversableProxyLike.class.reduceLeftOption((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceRight(Function2 op) {
/* 38 */       return (B)TraversableProxyLike.class.reduceRight((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 38 */       return TraversableProxyLike.class.reduceRightOption((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 38 */       return (That)TraversableProxyLike.class.scanLeft((TraversableProxyLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 38 */       return (That)TraversableProxyLike.class.scanRight((TraversableProxyLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B> B sum(Numeric num) {
/* 38 */       return (B)TraversableProxyLike.class.sum((TraversableProxyLike)this, num);
/*    */     }
/*    */     
/*    */     public <B> B product(Numeric num) {
/* 38 */       return (B)TraversableProxyLike.class.product((TraversableProxyLike)this, num);
/*    */     }
/*    */     
/*    */     public <B> A min(Ordering cmp) {
/* 38 */       return (A)TraversableProxyLike.class.min((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> A max(Ordering cmp) {
/* 38 */       return (A)TraversableProxyLike.class.max((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public A head() {
/* 38 */       return (A)TraversableProxyLike.class.head((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<A> headOption() {
/* 38 */       return TraversableProxyLike.class.headOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Set<A> tail() {
/* 38 */       return (Set<A>)TraversableProxyLike.class.tail((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public A last() {
/* 38 */       return (A)TraversableProxyLike.class.last((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<A> lastOption() {
/* 38 */       return TraversableProxyLike.class.lastOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Set<A> init() {
/* 38 */       return (Set<A>)TraversableProxyLike.class.init((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Set<A> take(int n) {
/* 38 */       return (Set<A>)TraversableProxyLike.class.take((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Set<A> drop(int n) {
/* 38 */       return (Set<A>)TraversableProxyLike.class.drop((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Set<A> slice(int from, int until) {
/* 38 */       return (Set<A>)TraversableProxyLike.class.slice((TraversableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public Set<A> takeWhile(Function1 p) {
/* 38 */       return (Set<A>)TraversableProxyLike.class.takeWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Set<A> dropWhile(Function1 p) {
/* 38 */       return (Set<A>)TraversableProxyLike.class.dropWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Set<A>, Set<A>> span(Function1 p) {
/* 38 */       return TraversableProxyLike.class.span((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Set<A>, Set<A>> splitAt(int n) {
/* 38 */       return TraversableProxyLike.class.splitAt((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public <B> void copyToBuffer(Buffer dest) {
/* 38 */       TraversableProxyLike.class.copyToBuffer((TraversableProxyLike)this, dest);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start, int len) {
/* 38 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs, start, len);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start) {
/* 38 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs, start);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs) {
/* 38 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs);
/*    */     }
/*    */     
/*    */     public <B> Object toArray(ClassTag evidence$1) {
/* 38 */       return TraversableProxyLike.class.toArray((TraversableProxyLike)this, evidence$1);
/*    */     }
/*    */     
/*    */     public List<A> toList() {
/* 38 */       return TraversableProxyLike.class.toList((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterable<A> toIterable() {
/* 38 */       return TraversableProxyLike.class.toIterable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Seq<A> toSeq() {
/* 38 */       return TraversableProxyLike.class.toSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> toIndexedSeq() {
/* 38 */       return TraversableProxyLike.class.toIndexedSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Buffer<B> toBuffer() {
/* 38 */       return TraversableProxyLike.class.toBuffer((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Stream<A> toStream() {
/* 38 */       return TraversableProxyLike.class.toStream((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Set<B> toSet() {
/* 38 */       return TraversableProxyLike.class.toSet((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 38 */       return TraversableProxyLike.class.toMap((TraversableProxyLike)this, ev);
/*    */     }
/*    */     
/*    */     public Traversable<A> toTraversable() {
/* 38 */       return TraversableProxyLike.class.toTraversable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<A> toIterator() {
/* 38 */       return TraversableProxyLike.class.toIterator((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public String mkString(String start, String sep, String end) {
/* 38 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this, start, sep, end);
/*    */     }
/*    */     
/*    */     public String mkString(String sep) {
/* 38 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this, sep);
/*    */     }
/*    */     
/*    */     public String mkString() {
/* 38 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 38 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b, start, sep, end);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String sep) {
/* 38 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b, sep);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b) {
/* 38 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b);
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 38 */       return TraversableProxyLike.class.stringPrefix((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 38 */       return Proxy.class.hashCode((Proxy)this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object that) {
/* 38 */       return Proxy.class.equals((Proxy)this, that);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 38 */       return Proxy.class.toString((Proxy)this);
/*    */     }
/*    */     
/*    */     public GenericCompanion<Set> companion() {
/* 38 */       return Set$class.companion(this);
/*    */     }
/*    */     
/*    */     public Set<A> seq() {
/* 38 */       return Set$class.seq(this);
/*    */     }
/*    */     
/*    */     public Combiner<A, ParSet<A>> parCombiner() {
/* 38 */       return Set$class.parCombiner(this);
/*    */     }
/*    */     
/*    */     public Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
/* 38 */       return TraversableLike.class.map(this, f, bf);
/*    */     }
/*    */     
/*    */     public Builder<A, Set<A>> newBuilder() {
/* 38 */       return SetLike.class.newBuilder(this);
/*    */     }
/*    */     
/*    */     public Set<A> $plus(Object elem1, Object elem2, Seq elems) {
/* 38 */       return (Set<A>)SetLike.class.$plus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Set<A> $plus$plus(GenTraversableOnce elems) {
/* 38 */       return (Set<A>)SetLike.class.$plus$plus(this, elems);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> subsets(int len) {
/* 38 */       return SetLike.class.subsets(this, len);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> subsets() {
/* 38 */       return SetLike.class.subsets(this);
/*    */     }
/*    */     
/*    */     public Set<A> $minus(Object elem1, Object elem2, Seq elems) {
/* 38 */       return (Set<A>)Subtractable.class.$minus((Subtractable)this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Set<A> $minus$minus(GenTraversableOnce xs) {
/* 38 */       return (Set<A>)Subtractable.class.$minus$minus((Subtractable)this, xs);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZD$sp(double v1) {
/* 38 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDD$sp(double v1) {
/* 38 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFD$sp(double v1) {
/* 38 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcID$sp(double v1) {
/* 38 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJD$sp(double v1) {
/* 38 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVD$sp(double v1) {
/* 38 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZF$sp(float v1) {
/* 38 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDF$sp(float v1) {
/* 38 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFF$sp(float v1) {
/* 38 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIF$sp(float v1) {
/* 38 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJF$sp(float v1) {
/* 38 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVF$sp(float v1) {
/* 38 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int v1) {
/* 38 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDI$sp(int v1) {
/* 38 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFI$sp(int v1) {
/* 38 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcII$sp(int v1) {
/* 38 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJI$sp(int v1) {
/* 38 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVI$sp(int v1) {
/* 38 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZJ$sp(long v1) {
/* 38 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDJ$sp(long v1) {
/* 38 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFJ$sp(long v1) {
/* 38 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIJ$sp(long v1) {
/* 38 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJJ$sp(long v1) {
/* 38 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVJ$sp(long v1) {
/* 38 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose(Function1 g) {
/* 38 */       return Function1.class.compose((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 38 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, A> andThen(Function1 g) {
/* 38 */       return Function1.class.andThen((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 38 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public Iterable<A> thisCollection() {
/* 38 */       return IterableLike.class.thisCollection(this);
/*    */     }
/*    */     
/*    */     public Iterable<A> toCollection(Object repr) {
/* 38 */       return IterableLike.class.toCollection(this, repr);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object that) {
/* 38 */       return IterableLike.class.canEqual(this, that);
/*    */     }
/*    */     
/*    */     public <B> Builder<B, Set<B>> genericBuilder() {
/* 38 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*    */     }
/*    */     
/*    */     public <A1, A2> Tuple2<Set<A1>, Set<A2>> unzip(Function1 asPair) {
/* 38 */       return GenericTraversableTemplate.class.unzip(this, asPair);
/*    */     }
/*    */     
/*    */     public <A1, A2, A3> Tuple3<Set<A1>, Set<A2>, Set<A3>> unzip3(Function1 asTriple) {
/* 38 */       return GenericTraversableTemplate.class.unzip3(this, asTriple);
/*    */     }
/*    */     
/*    */     public <B> Set<B> flatten(Function1 asTraversable) {
/* 38 */       return (Set<B>)GenericTraversableTemplate.class.flatten(this, asTraversable);
/*    */     }
/*    */     
/*    */     public <B> Set<Set<B>> transpose(Function1 asTraversable) {
/* 38 */       return (Set<Set<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*    */     }
/*    */     
/*    */     public final boolean isTraversableAgain() {
/* 38 */       return TraversableLike.class.isTraversableAgain(this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 38 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 38 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 38 */       return (That)TraversableLike.class.scan(this, z, op, cbf);
/*    */     }
/*    */     
/*    */     public Set<A> sliceWithKnownDelta(int from, int until, int delta) {
/* 38 */       return (Set<A>)TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
/*    */     }
/*    */     
/*    */     public Set<A> sliceWithKnownBound(int from, int until) {
/* 38 */       return (Set<A>)TraversableLike.class.sliceWithKnownBound(this, from, until);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> tails() {
/* 38 */       return TraversableLike.class.tails(this);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> inits() {
/* 38 */       return TraversableLike.class.inits(this);
/*    */     }
/*    */     
/*    */     public <Col> Col to(CanBuildFrom cbf) {
/* 38 */       return (Col)TraversableLike.class.to(this, cbf);
/*    */     }
/*    */     
/*    */     public FilterMonadic<A, Set<A>> withFilter(Function1 p) {
/* 38 */       return TraversableLike.class.withFilter(this, p);
/*    */     }
/*    */     
/*    */     public ParSet<A> par() {
/* 38 */       return (ParSet<A>)Parallelizable.class.par(this);
/*    */     }
/*    */     
/*    */     public List<A> reversed() {
/* 38 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 38 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*    */     }
/*    */     
/*    */     public <A1> A1 reduce(Function2 op) {
/* 38 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 38 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 fold(Object z, Function2 op) {
/* 38 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 38 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*    */     }
/*    */     
/*    */     public <B> A maxBy(Function1 f, Ordering cmp) {
/* 38 */       return (A)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> A minBy(Function1 f, Ordering cmp) {
/* 38 */       return (A)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public Vector<A> toVector() {
/* 38 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 38 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public Set<A> self() {
/* 38 */       return this.self;
/*    */     }
/*    */     
/*    */     public MapProxy$$anon$2(MapProxy $outer) {
/* 38 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 38 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 38 */       Parallelizable.class.$init$(this);
/* 38 */       TraversableLike.class.$init$(this);
/* 38 */       GenericTraversableTemplate.class.$init$(this);
/* 38 */       GenTraversable.class.$init$((GenTraversable)this);
/* 38 */       Traversable.class.$init$(this);
/* 38 */       Traversable$class.$init$(this);
/* 38 */       GenIterable.class.$init$((GenIterable)this);
/* 38 */       IterableLike.class.$init$(this);
/* 38 */       Iterable.class.$init$(this);
/* 38 */       Iterable$class.$init$(this);
/* 38 */       Function1.class.$init$((Function1)this);
/* 38 */       GenSetLike.class.$init$((GenSetLike)this);
/* 38 */       GenericSetTemplate.class.$init$(this);
/* 38 */       GenSet.class.$init$((GenSet)this);
/* 38 */       Subtractable.class.$init$((Subtractable)this);
/* 38 */       SetLike.class.$init$(this);
/* 38 */       Set.class.$init$(this);
/* 38 */       Set$class.$init$(this);
/* 38 */       Proxy.class.$init$((Proxy)this);
/* 38 */       TraversableProxyLike.class.$init$((TraversableProxyLike)this);
/* 38 */       IterableProxyLike.class.$init$((IterableProxyLike)this);
/* 38 */       SetProxyLike.class.$init$(this);
/* 38 */       SetProxy$class.$init$(this);
/* 38 */       this.self = ((MapLike)$outer.self()).keySet();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\MapProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */