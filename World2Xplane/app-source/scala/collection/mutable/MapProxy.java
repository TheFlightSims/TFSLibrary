/*    */ package scala.collection.mutable;
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
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableProxyLike;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.FilterMonadic;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.immutable.Stream;
/*    */ import scala.collection.immutable.Vector;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.mutable.ParMap;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001}4q!\001\002\021\002\007\005\021B\001\005NCB\004&o\034=z\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\007))rd\005\003\001\027=\t\003C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fMB!\001#E\n\037\033\005\021\021B\001\n\003\005\ri\025\r\035\t\003)Ua\001\001B\003\027\001\t\007qCA\001B#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=\021\005QyB!\002\021\001\005\0049\"!\001\"\021\013\t\0323CH\b\016\003\021I!\001\n\003\003\0315\013\007\017\025:pqfd\025n[3\t\013\031\002A\021A\024\002\r\021Jg.\033;%)\005A\003C\001\007*\023\tQcA\001\003V]&$\b\"\002\027\001\t\023i\023\001\0038foB\023x\016_=\026\0059\nDCA\0305!\021\001\002a\005\031\021\005Q\tD!\002\032,\005\004\031$A\001\"2#\tq2\004C\0036W\001\007a'A\004oK^\034V\r\0344\021\tA\t2\003\r\005\006q\001!\t%O\001\005e\026\004(/F\001;!\021\001\002a\005\020\t\013q\002A\021I\035\002\013\025l\007\017^=\t\013y\002A\021I \002\017U\004H-\031;fIV\021\001i\021\013\004\003\0223\005\003\002\t\001'\t\003\"\001F\"\005\013Ij$\031A\032\t\013\025k\004\031A\n\002\007-,\027\020C\003H{\001\007!)A\003wC2,X\rC\003J\001\021\005#*A\003%a2,8/\006\002L\035R\021Aj\024\t\005!E\031R\n\005\002\025\035\022)!\007\023b\001g!)\001\013\023a\001#\006\0211N\036\t\005\031I\033R*\003\002T\r\t1A+\0369mKJBQ!\023\001\005BU+\"AV-\025\t]SVl\030\t\005!\001\031\002\f\005\002\0253\022)!\007\026b\001g!)1\f\026a\0019\006)Q\r\\3ncA!ABU\nY\021\025qF\0131\001]\003\025)G.Z73\021\025\001G\0131\001b\003\025)G.Z7t!\ra!\rX\005\003G\032\021!\002\020:fa\026\fG/\0323?\021\025)\007\001\"\021g\003)!\003\017\\;tIAdWo]\013\003O*$\"\001[6\021\tA\0011#\033\t\003))$QA\r3C\002MBQ\001\0343A\0025\f!\001_:\021\007\tr\007/\003\002p\t\t\021r)\0328Ue\0064XM]:bE2,wJ\\2f!\021a!kE5\t\013I\004A\021I:\002\r\021j\027N\\;t)\tQD\017C\003Fc\002\0071\003C\003w\001\021\005s/\001\005%a2,8\017J3r)\tA\0300D\001\001\021\025\001V\0171\001{!\021a!k\005\020\t\013q\004A\021I?\002\023\021j\027N\\;tI\025\fHC\001=\021\025)5\0201\001\024\001")
/*    */ public interface MapProxy<A, B> extends Map<A, B>, MapProxyLike<A, B, Map<A, B>> {
/*    */   MapProxy<A, B> repr();
/*    */   
/*    */   MapProxy<A, B> empty();
/*    */   
/*    */   <B1> MapProxy<A, B1> updated(A paramA, B1 paramB1);
/*    */   
/*    */   <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple2);
/*    */   
/*    */   <B1> MapProxy<A, B1> $plus(Tuple2<A, B1> paramTuple21, Tuple2<A, B1> paramTuple22, Seq<Tuple2<A, B1>> paramSeq);
/*    */   
/*    */   <B1> MapProxy<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> paramGenTraversableOnce);
/*    */   
/*    */   MapProxy<A, B> $minus(A paramA);
/*    */   
/*    */   MapProxy<A, B> $plus$eq(Tuple2<A, B> paramTuple2);
/*    */   
/*    */   MapProxy<A, B> $minus$eq(A paramA);
/*    */   
/*    */   public class MapProxy$$anon$1 implements MapProxy<A, Object> {
/*    */     private final Map<A, Object> self;
/*    */     
/*    */     public MapProxy<A, Object> repr() {
/* 24 */       return MapProxy$class.repr(this);
/*    */     }
/*    */     
/*    */     public MapProxy<A, Object> empty() {
/* 24 */       return MapProxy$class.empty(this);
/*    */     }
/*    */     
/*    */     public <B1> MapProxy<A, B1> updated(Object key, Object value) {
/* 24 */       return MapProxy$class.updated(this, key, value);
/*    */     }
/*    */     
/*    */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 24 */       return MapProxy$class.$plus(this, kv);
/*    */     }
/*    */     
/*    */     public <B1> MapProxy<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 24 */       return MapProxy$class.$plus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public <B1> MapProxy<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 24 */       return MapProxy$class.$plus$plus(this, xs);
/*    */     }
/*    */     
/*    */     public MapProxy<A, Object> $minus(Object key) {
/* 24 */       return MapProxy$class.$minus(this, key);
/*    */     }
/*    */     
/*    */     public MapProxy<A, Object> $plus$eq(Tuple2 kv) {
/* 24 */       return MapProxy$class.$plus$eq(this, kv);
/*    */     }
/*    */     
/*    */     public MapProxy<A, Object> $minus$eq(Object key) {
/* 24 */       return MapProxy$class.$minus$eq(this, key);
/*    */     }
/*    */     
/*    */     public Option<Object> get(Object key) {
/* 24 */       return MapProxyLike.class.get(this, key);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<A, Object>> iterator() {
/* 24 */       return MapProxyLike.class.iterator(this);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 24 */       return MapProxyLike.class.isEmpty(this);
/*    */     }
/*    */     
/*    */     public <B1> B1 getOrElse(Object key, Function0 default) {
/* 24 */       return (B1)MapProxyLike.class.getOrElse(this, key, default);
/*    */     }
/*    */     
/*    */     public Object apply(Object key) {
/* 24 */       return MapProxyLike.class.apply(this, key);
/*    */     }
/*    */     
/*    */     public boolean contains(Object key) {
/* 24 */       return MapProxyLike.class.contains(this, key);
/*    */     }
/*    */     
/*    */     public boolean isDefinedAt(Object key) {
/* 24 */       return MapProxyLike.class.isDefinedAt(this, key);
/*    */     }
/*    */     
/*    */     public Set<A> keySet() {
/* 24 */       return MapProxyLike.class.keySet(this);
/*    */     }
/*    */     
/*    */     public Iterator<A> keysIterator() {
/* 24 */       return MapProxyLike.class.keysIterator(this);
/*    */     }
/*    */     
/*    */     public Iterable<A> keys() {
/* 24 */       return MapProxyLike.class.keys(this);
/*    */     }
/*    */     
/*    */     public Iterable<Object> values() {
/* 24 */       return MapProxyLike.class.values(this);
/*    */     }
/*    */     
/*    */     public Iterator<Object> valuesIterator() {
/* 24 */       return MapProxyLike.class.valuesIterator(this);
/*    */     }
/*    */     
/*    */     public Object default(Object key) {
/* 24 */       return MapProxyLike.class.default(this, key);
/*    */     }
/*    */     
/*    */     public Map<A, Object> filterKeys(Function1 p) {
/* 24 */       return MapProxyLike.class.filterKeys(this, p);
/*    */     }
/*    */     
/*    */     public <C> Map<A, C> mapValues(Function1 f) {
/* 24 */       return MapProxyLike.class.mapValues(this, f);
/*    */     }
/*    */     
/*    */     public Map<A, Object> filterNot(Function1 p) {
/* 24 */       return (Map<A, Object>)MapProxyLike.class.filterNot(this, p);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 24 */       return MapProxyLike.class.addString(this, b, start, sep, end);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, Object>> grouped(int size) {
/* 24 */       return IterableProxyLike.class.grouped((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, Object>> sliding(int size) {
/* 24 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, Object>> sliding(int size, int step) {
/* 24 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size, step);
/*    */     }
/*    */     
/*    */     public Map<A, Object> takeRight(int n) {
/* 24 */       return (Map<A, Object>)IterableProxyLike.class.takeRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Map<A, Object> dropRight(int n) {
/* 24 */       return (Map<A, Object>)IterableProxyLike.class.dropRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 24 */       return (That)IterableProxyLike.class.zip((IterableProxyLike)this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 24 */       return (That)IterableProxyLike.class.zipAll((IterableProxyLike)this, that, thisElem, thatElem, bf);
/*    */     }
/*    */     
/*    */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 24 */       return (That)IterableProxyLike.class.zipWithIndex((IterableProxyLike)this, bf);
/*    */     }
/*    */     
/*    */     public <B> boolean sameElements(GenIterable that) {
/* 24 */       return IterableProxyLike.class.sameElements((IterableProxyLike)this, that);
/*    */     }
/*    */     
/*    */     public Object view() {
/* 24 */       return IterableProxyLike.class.view((IterableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IterableView<Tuple2<A, Object>, Map<A, Object>> view(int from, int until) {
/* 24 */       return IterableProxyLike.class.view((IterableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public <B> void foreach(Function1 f) {
/* 24 */       TraversableProxyLike.class.foreach((TraversableProxyLike)this, f);
/*    */     }
/*    */     
/*    */     public boolean nonEmpty() {
/* 24 */       return TraversableProxyLike.class.nonEmpty((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public int size() {
/* 24 */       return TraversableProxyLike.class.size((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public boolean hasDefiniteSize() {
/* 24 */       return TraversableProxyLike.class.hasDefiniteSize((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 24 */       return (That)TraversableProxyLike.class.$plus$plus((TraversableProxyLike)this, xs, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 24 */       return (That)TraversableProxyLike.class.map((TraversableProxyLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 24 */       return (That)TraversableProxyLike.class.flatMap((TraversableProxyLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public Map<A, Object> filter(Function1 p) {
/* 24 */       return (Map<A, Object>)TraversableProxyLike.class.filter((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 24 */       return (That)TraversableProxyLike.class.collect((TraversableProxyLike)this, pf, bf);
/*    */     }
/*    */     
/*    */     public Tuple2<Map<A, Object>, Map<A, Object>> partition(Function1 p) {
/* 24 */       return TraversableProxyLike.class.partition((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <K> Map<K, Map<A, Object>> groupBy(Function1 f) {
/* 24 */       return TraversableProxyLike.class.groupBy((TraversableProxyLike)this, f);
/*    */     }
/*    */     
/*    */     public boolean forall(Function1 p) {
/* 24 */       return TraversableProxyLike.class.forall((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public boolean exists(Function1 p) {
/* 24 */       return TraversableProxyLike.class.exists((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/* 24 */       return TraversableProxyLike.class.count((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, Object>> find(Function1 p) {
/* 24 */       return TraversableProxyLike.class.find((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B> B foldLeft(Object z, Function2 op) {
/* 24 */       return (B)TraversableProxyLike.class.foldLeft((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $div$colon(Object z, Function2 op) {
/* 24 */       return (B)TraversableProxyLike.class.$div$colon((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldRight(Object z, Function2 op) {
/* 24 */       return (B)TraversableProxyLike.class.foldRight((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 24 */       return (B)TraversableProxyLike.class.$colon$bslash((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceLeft(Function2 op) {
/* 24 */       return (B)TraversableProxyLike.class.reduceLeft((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 24 */       return TraversableProxyLike.class.reduceLeftOption((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceRight(Function2 op) {
/* 24 */       return (B)TraversableProxyLike.class.reduceRight((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 24 */       return TraversableProxyLike.class.reduceRightOption((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 24 */       return (That)TraversableProxyLike.class.scanLeft((TraversableProxyLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 24 */       return (That)TraversableProxyLike.class.scanRight((TraversableProxyLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B> B sum(Numeric num) {
/* 24 */       return (B)TraversableProxyLike.class.sum((TraversableProxyLike)this, num);
/*    */     }
/*    */     
/*    */     public <B> B product(Numeric num) {
/* 24 */       return (B)TraversableProxyLike.class.product((TraversableProxyLike)this, num);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, Object> min(Ordering cmp) {
/* 24 */       return (Tuple2<A, Object>)TraversableProxyLike.class.min((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, Object> max(Ordering cmp) {
/* 24 */       return (Tuple2<A, Object>)TraversableProxyLike.class.max((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public Tuple2<A, Object> head() {
/* 24 */       return (Tuple2<A, Object>)TraversableProxyLike.class.head((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, Object>> headOption() {
/* 24 */       return TraversableProxyLike.class.headOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> tail() {
/* 24 */       return (Map<A, Object>)TraversableProxyLike.class.tail((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Tuple2<A, Object> last() {
/* 24 */       return (Tuple2<A, Object>)TraversableProxyLike.class.last((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, Object>> lastOption() {
/* 24 */       return TraversableProxyLike.class.lastOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> init() {
/* 24 */       return (Map<A, Object>)TraversableProxyLike.class.init((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> take(int n) {
/* 24 */       return (Map<A, Object>)TraversableProxyLike.class.take((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Map<A, Object> drop(int n) {
/* 24 */       return (Map<A, Object>)TraversableProxyLike.class.drop((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Map<A, Object> slice(int from, int until) {
/* 24 */       return (Map<A, Object>)TraversableProxyLike.class.slice((TraversableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public Map<A, Object> takeWhile(Function1 p) {
/* 24 */       return (Map<A, Object>)TraversableProxyLike.class.takeWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Map<A, Object> dropWhile(Function1 p) {
/* 24 */       return (Map<A, Object>)TraversableProxyLike.class.dropWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Map<A, Object>, Map<A, Object>> span(Function1 p) {
/* 24 */       return TraversableProxyLike.class.span((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Map<A, Object>, Map<A, Object>> splitAt(int n) {
/* 24 */       return TraversableProxyLike.class.splitAt((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public <B> void copyToBuffer(Buffer dest) {
/* 24 */       TraversableProxyLike.class.copyToBuffer((TraversableProxyLike)this, dest);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start, int len) {
/* 24 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs, start, len);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start) {
/* 24 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs, start);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs) {
/* 24 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs);
/*    */     }
/*    */     
/*    */     public <B> Object toArray(ClassTag evidence$1) {
/* 24 */       return TraversableProxyLike.class.toArray((TraversableProxyLike)this, evidence$1);
/*    */     }
/*    */     
/*    */     public List<Tuple2<A, Object>> toList() {
/* 24 */       return TraversableProxyLike.class.toList((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterable<Tuple2<A, Object>> toIterable() {
/* 24 */       return TraversableProxyLike.class.toIterable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Seq<Tuple2<A, Object>> toSeq() {
/* 24 */       return TraversableProxyLike.class.toSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<Tuple2<A, Object>> toIndexedSeq() {
/* 24 */       return TraversableProxyLike.class.toIndexedSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Buffer<B> toBuffer() {
/* 24 */       return TraversableProxyLike.class.toBuffer((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Stream<Tuple2<A, Object>> toStream() {
/* 24 */       return TraversableProxyLike.class.toStream((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Set<B> toSet() {
/* 24 */       return TraversableProxyLike.class.toSet((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 24 */       return TraversableProxyLike.class.toMap((TraversableProxyLike)this, ev);
/*    */     }
/*    */     
/*    */     public Traversable<Tuple2<A, Object>> toTraversable() {
/* 24 */       return TraversableProxyLike.class.toTraversable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<A, Object>> toIterator() {
/* 24 */       return TraversableProxyLike.class.toIterator((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public String mkString(String start, String sep, String end) {
/* 24 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this, start, sep, end);
/*    */     }
/*    */     
/*    */     public String mkString(String sep) {
/* 24 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this, sep);
/*    */     }
/*    */     
/*    */     public String mkString() {
/* 24 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String sep) {
/* 24 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b, sep);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b) {
/* 24 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b);
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 24 */       return TraversableProxyLike.class.stringPrefix((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 24 */       return Proxy.class.hashCode((Proxy)this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object that) {
/* 24 */       return Proxy.class.equals((Proxy)this, that);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 24 */       return Proxy.class.toString((Proxy)this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> seq() {
/* 24 */       return Map$class.seq(this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> withDefault(Function1 d) {
/* 24 */       return Map$class.withDefault(this, d);
/*    */     }
/*    */     
/*    */     public Map<A, Object> withDefaultValue(Object d) {
/* 24 */       return Map$class.withDefaultValue(this, d);
/*    */     }
/*    */     
/*    */     public Builder<Tuple2<A, Object>, Map<A, Object>> newBuilder() {
/* 24 */       return MapLike$class.newBuilder(this);
/*    */     }
/*    */     
/*    */     public Combiner<Tuple2<A, Object>, ParMap<A, Object>> parCombiner() {
/* 24 */       return MapLike$class.parCombiner(this);
/*    */     }
/*    */     
/*    */     public Option<Object> put(Object key, Object value) {
/* 24 */       return MapLike$class.put(this, key, value);
/*    */     }
/*    */     
/*    */     public void update(Object key, Object value) {
/* 24 */       MapLike$class.update(this, key, value);
/*    */     }
/*    */     
/*    */     public Option<Object> remove(Object key) {
/* 24 */       return MapLike$class.remove(this, key);
/*    */     }
/*    */     
/*    */     public void clear() {
/* 24 */       MapLike$class.clear(this);
/*    */     }
/*    */     
/*    */     public Object getOrElseUpdate(Object key, Function0 op) {
/* 24 */       return MapLike$class.getOrElseUpdate(this, key, op);
/*    */     }
/*    */     
/*    */     public MapLike<A, Object, Map<A, Object>> transform(Function2 f) {
/* 24 */       return MapLike$class.transform(this, f);
/*    */     }
/*    */     
/*    */     public MapLike<A, Object, Map<A, Object>> retain(Function2 p) {
/* 24 */       return MapLike$class.retain(this, p);
/*    */     }
/*    */     
/*    */     public Map<A, Object> clone() {
/* 24 */       return MapLike$class.clone(this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> result() {
/* 24 */       return MapLike$class.result(this);
/*    */     }
/*    */     
/*    */     public Map<A, Object> $minus(Object elem1, Object elem2, Seq elems) {
/* 24 */       return MapLike$class.$minus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Map<A, Object> $minus$minus(GenTraversableOnce xs) {
/* 24 */       return MapLike$class.$minus$minus(this, xs);
/*    */     }
/*    */     
/*    */     public Object scala$collection$mutable$Cloneable$$super$clone() {
/* 24 */       return super.clone();
/*    */     }
/*    */     
/*    */     public Shrinkable<A> $minus$eq(Object elem1, Object elem2, Seq elems) {
/* 24 */       return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Shrinkable<A> $minus$minus$eq(TraversableOnce xs) {
/* 24 */       return Shrinkable.class.$minus$minus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public void sizeHint(int size) {
/* 24 */       Builder$class.sizeHint(this, size);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll) {
/* 24 */       Builder$class.sizeHint(this, coll);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll, int delta) {
/* 24 */       Builder$class.sizeHint(this, coll, delta);
/*    */     }
/*    */     
/*    */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 24 */       Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */     }
/*    */     
/*    */     public <NewTo> Builder<Tuple2<A, Object>, NewTo> mapResult(Function1 f) {
/* 24 */       return Builder$class.mapResult(this, f);
/*    */     }
/*    */     
/*    */     public Growable<Tuple2<A, Object>> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 24 */       return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<Tuple2<A, Object>> $plus$plus$eq(TraversableOnce xs) {
/* 24 */       return Growable.class.$plus$plus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 24 */       return PartialFunction.class.orElse((PartialFunction)this, that);
/*    */     }
/*    */     
/*    */     public <C> PartialFunction<A, C> andThen(Function1 k) {
/* 24 */       return PartialFunction.class.andThen((PartialFunction)this, k);
/*    */     }
/*    */     
/*    */     public Function1<A, Option<Object>> lift() {
/* 24 */       return PartialFunction.class.lift((PartialFunction)this);
/*    */     }
/*    */     
/*    */     public <A1 extends A, B1> B1 applyOrElse(Object x, Function1 default) {
/* 24 */       return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*    */     }
/*    */     
/*    */     public <U> Function1<A, Object> runWith(Function1 action) {
/* 24 */       return PartialFunction.class.runWith((PartialFunction)this, action);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZD$sp(double v1) {
/* 24 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDD$sp(double v1) {
/* 24 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFD$sp(double v1) {
/* 24 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcID$sp(double v1) {
/* 24 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJD$sp(double v1) {
/* 24 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVD$sp(double v1) {
/* 24 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZF$sp(float v1) {
/* 24 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDF$sp(float v1) {
/* 24 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFF$sp(float v1) {
/* 24 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIF$sp(float v1) {
/* 24 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJF$sp(float v1) {
/* 24 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVF$sp(float v1) {
/* 24 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int v1) {
/* 24 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDI$sp(int v1) {
/* 24 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFI$sp(int v1) {
/* 24 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcII$sp(int v1) {
/* 24 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJI$sp(int v1) {
/* 24 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVI$sp(int v1) {
/* 24 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZJ$sp(long v1) {
/* 24 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDJ$sp(long v1) {
/* 24 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFJ$sp(long v1) {
/* 24 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIJ$sp(long v1) {
/* 24 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJJ$sp(long v1) {
/* 24 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVJ$sp(long v1) {
/* 24 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose(Function1 g) {
/* 24 */       return Function1.class.compose((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 24 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 24 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public GenericCompanion<Iterable> companion() {
/* 24 */       return Iterable$class.companion(this);
/*    */     }
/*    */     
/*    */     public Iterable<Tuple2<A, Object>> thisCollection() {
/* 24 */       return IterableLike.class.thisCollection(this);
/*    */     }
/*    */     
/*    */     public Iterable<Tuple2<A, Object>> toCollection(Object repr) {
/* 24 */       return IterableLike.class.toCollection(this, repr);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object that) {
/* 24 */       return IterableLike.class.canEqual(this, that);
/*    */     }
/*    */     
/*    */     public <B> Builder<B, Iterable<B>> genericBuilder() {
/* 24 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*    */     }
/*    */     
/*    */     public <A1, A2> Tuple2<Iterable<A1>, Iterable<A2>> unzip(Function1 asPair) {
/* 24 */       return GenericTraversableTemplate.class.unzip(this, asPair);
/*    */     }
/*    */     
/*    */     public <A1, A2, A3> Tuple3<Iterable<A1>, Iterable<A2>, Iterable<A3>> unzip3(Function1 asTriple) {
/* 24 */       return GenericTraversableTemplate.class.unzip3(this, asTriple);
/*    */     }
/*    */     
/*    */     public <B> Iterable<B> flatten(Function1 asTraversable) {
/* 24 */       return (Iterable<B>)GenericTraversableTemplate.class.flatten(this, asTraversable);
/*    */     }
/*    */     
/*    */     public <B> Iterable<Iterable<B>> transpose(Function1 asTraversable) {
/* 24 */       return (Iterable<Iterable<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*    */     }
/*    */     
/*    */     public final boolean isTraversableAgain() {
/* 24 */       return TraversableLike.class.isTraversableAgain(this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 24 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 24 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 24 */       return (That)TraversableLike.class.scan(this, z, op, cbf);
/*    */     }
/*    */     
/*    */     public Map<A, Object> sliceWithKnownDelta(int from, int until, int delta) {
/* 24 */       return (Map<A, Object>)TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
/*    */     }
/*    */     
/*    */     public Map<A, Object> sliceWithKnownBound(int from, int until) {
/* 24 */       return (Map<A, Object>)TraversableLike.class.sliceWithKnownBound(this, from, until);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, Object>> tails() {
/* 24 */       return TraversableLike.class.tails(this);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, Object>> inits() {
/* 24 */       return TraversableLike.class.inits(this);
/*    */     }
/*    */     
/*    */     public <Col> Col to(CanBuildFrom cbf) {
/* 24 */       return (Col)TraversableLike.class.to(this, cbf);
/*    */     }
/*    */     
/*    */     public FilterMonadic<Tuple2<A, Object>, Map<A, Object>> withFilter(Function1 p) {
/* 24 */       return TraversableLike.class.withFilter(this, p);
/*    */     }
/*    */     
/*    */     public ParMap<A, Object> par() {
/* 24 */       return (ParMap<A, Object>)Parallelizable.class.par(this);
/*    */     }
/*    */     
/*    */     public List<Tuple2<A, Object>> reversed() {
/* 24 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 24 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*    */     }
/*    */     
/*    */     public <A1> A1 reduce(Function2 op) {
/* 24 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 24 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 fold(Object z, Function2 op) {
/* 24 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 24 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, Object> maxBy(Function1 f, Ordering cmp) {
/* 24 */       return (Tuple2<A, Object>)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, Object> minBy(Function1 f, Ordering cmp) {
/* 24 */       return (Tuple2<A, Object>)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public Vector<Tuple2<A, Object>> toVector() {
/* 24 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 24 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public Map<A, Object> self() {
/* 24 */       return this.self;
/*    */     }
/*    */     
/*    */     public MapProxy$$anon$1(MapProxy $outer, Map<A, Object> newSelf$1) {
/* 24 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 24 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 24 */       Parallelizable.class.$init$(this);
/* 24 */       TraversableLike.class.$init$(this);
/* 24 */       GenericTraversableTemplate.class.$init$(this);
/* 24 */       GenTraversable.class.$init$((GenTraversable)this);
/* 24 */       Traversable.class.$init$(this);
/* 24 */       Traversable$class.$init$(this);
/* 24 */       GenIterable.class.$init$((GenIterable)this);
/* 24 */       IterableLike.class.$init$(this);
/* 24 */       Iterable.class.$init$(this);
/* 24 */       Iterable$class.$init$(this);
/* 24 */       GenMapLike.class.$init$((GenMapLike)this);
/* 24 */       Function1.class.$init$((Function1)this);
/* 24 */       PartialFunction.class.$init$((PartialFunction)this);
/* 24 */       Subtractable.class.$init$((Subtractable)this);
/* 24 */       MapLike.class.$init$(this);
/* 24 */       Map.class.$init$(this);
/* 24 */       Growable.class.$init$(this);
/* 24 */       Builder$class.$init$(this);
/* 24 */       Shrinkable.class.$init$(this);
/* 24 */       Cloneable$class.$init$(this);
/* 24 */       MapLike$class.$init$(this);
/* 24 */       Map$class.$init$(this);
/* 24 */       Proxy.class.$init$((Proxy)this);
/* 24 */       TraversableProxyLike.class.$init$((TraversableProxyLike)this);
/* 24 */       IterableProxyLike.class.$init$((IterableProxyLike)this);
/* 24 */       MapProxyLike.class.$init$(this);
/* 24 */       MapProxy$class.$init$(this);
/* 24 */       this.self = newSelf$1;
/*    */     }
/*    */   }
/*    */   
/*    */   public class MapProxy$$anon$2 implements MapProxy<A, B> {
/*    */     private final Map<A, B> self;
/*    */     
/*    */     public MapProxy<A, B> repr() {
/* 27 */       return MapProxy$class.repr(this);
/*    */     }
/*    */     
/*    */     public MapProxy<A, B> empty() {
/* 27 */       return MapProxy$class.empty(this);
/*    */     }
/*    */     
/*    */     public <B1> MapProxy<A, B1> updated(Object key, Object value) {
/* 27 */       return MapProxy$class.updated(this, key, value);
/*    */     }
/*    */     
/*    */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 27 */       return MapProxy$class.$plus(this, kv);
/*    */     }
/*    */     
/*    */     public <B1> MapProxy<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 27 */       return MapProxy$class.$plus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public <B1> MapProxy<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 27 */       return MapProxy$class.$plus$plus(this, xs);
/*    */     }
/*    */     
/*    */     public MapProxy<A, B> $minus(Object key) {
/* 27 */       return MapProxy$class.$minus(this, key);
/*    */     }
/*    */     
/*    */     public MapProxy<A, B> $plus$eq(Tuple2 kv) {
/* 27 */       return MapProxy$class.$plus$eq(this, kv);
/*    */     }
/*    */     
/*    */     public MapProxy<A, B> $minus$eq(Object key) {
/* 27 */       return MapProxy$class.$minus$eq(this, key);
/*    */     }
/*    */     
/*    */     public Option<B> get(Object key) {
/* 27 */       return MapProxyLike.class.get(this, key);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<A, B>> iterator() {
/* 27 */       return MapProxyLike.class.iterator(this);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 27 */       return MapProxyLike.class.isEmpty(this);
/*    */     }
/*    */     
/*    */     public <B1> B1 getOrElse(Object key, Function0 default) {
/* 27 */       return (B1)MapProxyLike.class.getOrElse(this, key, default);
/*    */     }
/*    */     
/*    */     public B apply(Object key) {
/* 27 */       return (B)MapProxyLike.class.apply(this, key);
/*    */     }
/*    */     
/*    */     public boolean contains(Object key) {
/* 27 */       return MapProxyLike.class.contains(this, key);
/*    */     }
/*    */     
/*    */     public boolean isDefinedAt(Object key) {
/* 27 */       return MapProxyLike.class.isDefinedAt(this, key);
/*    */     }
/*    */     
/*    */     public Set<A> keySet() {
/* 27 */       return MapProxyLike.class.keySet(this);
/*    */     }
/*    */     
/*    */     public Iterator<A> keysIterator() {
/* 27 */       return MapProxyLike.class.keysIterator(this);
/*    */     }
/*    */     
/*    */     public Iterable<A> keys() {
/* 27 */       return MapProxyLike.class.keys(this);
/*    */     }
/*    */     
/*    */     public Iterable<B> values() {
/* 27 */       return MapProxyLike.class.values(this);
/*    */     }
/*    */     
/*    */     public Iterator<B> valuesIterator() {
/* 27 */       return MapProxyLike.class.valuesIterator(this);
/*    */     }
/*    */     
/*    */     public B default(Object key) {
/* 27 */       return (B)MapProxyLike.class.default(this, key);
/*    */     }
/*    */     
/*    */     public Map<A, B> filterKeys(Function1 p) {
/* 27 */       return MapProxyLike.class.filterKeys(this, p);
/*    */     }
/*    */     
/*    */     public <C> Map<A, C> mapValues(Function1 f) {
/* 27 */       return MapProxyLike.class.mapValues(this, f);
/*    */     }
/*    */     
/*    */     public Map<A, B> filterNot(Function1 p) {
/* 27 */       return (Map<A, B>)MapProxyLike.class.filterNot(this, p);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 27 */       return MapProxyLike.class.addString(this, b, start, sep, end);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, B>> grouped(int size) {
/* 27 */       return IterableProxyLike.class.grouped((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, B>> sliding(int size) {
/* 27 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, B>> sliding(int size, int step) {
/* 27 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size, step);
/*    */     }
/*    */     
/*    */     public Map<A, B> takeRight(int n) {
/* 27 */       return (Map<A, B>)IterableProxyLike.class.takeRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Map<A, B> dropRight(int n) {
/* 27 */       return (Map<A, B>)IterableProxyLike.class.dropRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 27 */       return (That)IterableProxyLike.class.zip((IterableProxyLike)this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 27 */       return (That)IterableProxyLike.class.zipAll((IterableProxyLike)this, that, thisElem, thatElem, bf);
/*    */     }
/*    */     
/*    */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 27 */       return (That)IterableProxyLike.class.zipWithIndex((IterableProxyLike)this, bf);
/*    */     }
/*    */     
/*    */     public <B> boolean sameElements(GenIterable that) {
/* 27 */       return IterableProxyLike.class.sameElements((IterableProxyLike)this, that);
/*    */     }
/*    */     
/*    */     public Object view() {
/* 27 */       return IterableProxyLike.class.view((IterableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IterableView<Tuple2<A, B>, Map<A, B>> view(int from, int until) {
/* 27 */       return IterableProxyLike.class.view((IterableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public <B> void foreach(Function1 f) {
/* 27 */       TraversableProxyLike.class.foreach((TraversableProxyLike)this, f);
/*    */     }
/*    */     
/*    */     public boolean nonEmpty() {
/* 27 */       return TraversableProxyLike.class.nonEmpty((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public int size() {
/* 27 */       return TraversableProxyLike.class.size((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public boolean hasDefiniteSize() {
/* 27 */       return TraversableProxyLike.class.hasDefiniteSize((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 27 */       return (That)TraversableProxyLike.class.$plus$plus((TraversableProxyLike)this, xs, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 27 */       return (That)TraversableProxyLike.class.map((TraversableProxyLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 27 */       return (That)TraversableProxyLike.class.flatMap((TraversableProxyLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public Map<A, B> filter(Function1 p) {
/* 27 */       return (Map<A, B>)TraversableProxyLike.class.filter((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 27 */       return (That)TraversableProxyLike.class.collect((TraversableProxyLike)this, pf, bf);
/*    */     }
/*    */     
/*    */     public Tuple2<Map<A, B>, Map<A, B>> partition(Function1 p) {
/* 27 */       return TraversableProxyLike.class.partition((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <K> Map<K, Map<A, B>> groupBy(Function1 f) {
/* 27 */       return TraversableProxyLike.class.groupBy((TraversableProxyLike)this, f);
/*    */     }
/*    */     
/*    */     public boolean forall(Function1 p) {
/* 27 */       return TraversableProxyLike.class.forall((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public boolean exists(Function1 p) {
/* 27 */       return TraversableProxyLike.class.exists((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/* 27 */       return TraversableProxyLike.class.count((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, B>> find(Function1 p) {
/* 27 */       return TraversableProxyLike.class.find((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B> B foldLeft(Object z, Function2 op) {
/* 27 */       return (B)TraversableProxyLike.class.foldLeft((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $div$colon(Object z, Function2 op) {
/* 27 */       return (B)TraversableProxyLike.class.$div$colon((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldRight(Object z, Function2 op) {
/* 27 */       return (B)TraversableProxyLike.class.foldRight((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 27 */       return (B)TraversableProxyLike.class.$colon$bslash((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceLeft(Function2 op) {
/* 27 */       return (B)TraversableProxyLike.class.reduceLeft((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 27 */       return TraversableProxyLike.class.reduceLeftOption((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceRight(Function2 op) {
/* 27 */       return (B)TraversableProxyLike.class.reduceRight((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 27 */       return TraversableProxyLike.class.reduceRightOption((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 27 */       return (That)TraversableProxyLike.class.scanLeft((TraversableProxyLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 27 */       return (That)TraversableProxyLike.class.scanRight((TraversableProxyLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B> B sum(Numeric num) {
/* 27 */       return (B)TraversableProxyLike.class.sum((TraversableProxyLike)this, num);
/*    */     }
/*    */     
/*    */     public <B> B product(Numeric num) {
/* 27 */       return (B)TraversableProxyLike.class.product((TraversableProxyLike)this, num);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, B> min(Ordering cmp) {
/* 27 */       return (Tuple2<A, B>)TraversableProxyLike.class.min((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, B> max(Ordering cmp) {
/* 27 */       return (Tuple2<A, B>)TraversableProxyLike.class.max((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public Tuple2<A, B> head() {
/* 27 */       return (Tuple2<A, B>)TraversableProxyLike.class.head((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, B>> headOption() {
/* 27 */       return TraversableProxyLike.class.headOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Map<A, B> tail() {
/* 27 */       return (Map<A, B>)TraversableProxyLike.class.tail((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Tuple2<A, B> last() {
/* 27 */       return (Tuple2<A, B>)TraversableProxyLike.class.last((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, B>> lastOption() {
/* 27 */       return TraversableProxyLike.class.lastOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Map<A, B> init() {
/* 27 */       return (Map<A, B>)TraversableProxyLike.class.init((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Map<A, B> take(int n) {
/* 27 */       return (Map<A, B>)TraversableProxyLike.class.take((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Map<A, B> drop(int n) {
/* 27 */       return (Map<A, B>)TraversableProxyLike.class.drop((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Map<A, B> slice(int from, int until) {
/* 27 */       return (Map<A, B>)TraversableProxyLike.class.slice((TraversableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public Map<A, B> takeWhile(Function1 p) {
/* 27 */       return (Map<A, B>)TraversableProxyLike.class.takeWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Map<A, B> dropWhile(Function1 p) {
/* 27 */       return (Map<A, B>)TraversableProxyLike.class.dropWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Map<A, B>, Map<A, B>> span(Function1 p) {
/* 27 */       return TraversableProxyLike.class.span((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Map<A, B>, Map<A, B>> splitAt(int n) {
/* 27 */       return TraversableProxyLike.class.splitAt((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public <B> void copyToBuffer(Buffer dest) {
/* 27 */       TraversableProxyLike.class.copyToBuffer((TraversableProxyLike)this, dest);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start, int len) {
/* 27 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs, start, len);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start) {
/* 27 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs, start);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs) {
/* 27 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs);
/*    */     }
/*    */     
/*    */     public <B> Object toArray(ClassTag evidence$1) {
/* 27 */       return TraversableProxyLike.class.toArray((TraversableProxyLike)this, evidence$1);
/*    */     }
/*    */     
/*    */     public List<Tuple2<A, B>> toList() {
/* 27 */       return TraversableProxyLike.class.toList((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterable<Tuple2<A, B>> toIterable() {
/* 27 */       return TraversableProxyLike.class.toIterable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Seq<Tuple2<A, B>> toSeq() {
/* 27 */       return TraversableProxyLike.class.toSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<Tuple2<A, B>> toIndexedSeq() {
/* 27 */       return TraversableProxyLike.class.toIndexedSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Buffer<B> toBuffer() {
/* 27 */       return TraversableProxyLike.class.toBuffer((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Stream<Tuple2<A, B>> toStream() {
/* 27 */       return TraversableProxyLike.class.toStream((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Set<B> toSet() {
/* 27 */       return TraversableProxyLike.class.toSet((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 27 */       return TraversableProxyLike.class.toMap((TraversableProxyLike)this, ev);
/*    */     }
/*    */     
/*    */     public Traversable<Tuple2<A, B>> toTraversable() {
/* 27 */       return TraversableProxyLike.class.toTraversable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<A, B>> toIterator() {
/* 27 */       return TraversableProxyLike.class.toIterator((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public String mkString(String start, String sep, String end) {
/* 27 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this, start, sep, end);
/*    */     }
/*    */     
/*    */     public String mkString(String sep) {
/* 27 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this, sep);
/*    */     }
/*    */     
/*    */     public String mkString() {
/* 27 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String sep) {
/* 27 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b, sep);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b) {
/* 27 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b);
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 27 */       return TraversableProxyLike.class.stringPrefix((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 27 */       return Proxy.class.hashCode((Proxy)this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object that) {
/* 27 */       return Proxy.class.equals((Proxy)this, that);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 27 */       return Proxy.class.toString((Proxy)this);
/*    */     }
/*    */     
/*    */     public Map<A, B> seq() {
/* 27 */       return Map$class.seq(this);
/*    */     }
/*    */     
/*    */     public Map<A, B> withDefault(Function1 d) {
/* 27 */       return Map$class.withDefault(this, d);
/*    */     }
/*    */     
/*    */     public Map<A, B> withDefaultValue(Object d) {
/* 27 */       return Map$class.withDefaultValue(this, d);
/*    */     }
/*    */     
/*    */     public Builder<Tuple2<A, B>, Map<A, B>> newBuilder() {
/* 27 */       return MapLike$class.newBuilder(this);
/*    */     }
/*    */     
/*    */     public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
/* 27 */       return MapLike$class.parCombiner(this);
/*    */     }
/*    */     
/*    */     public Option<B> put(Object key, Object value) {
/* 27 */       return MapLike$class.put(this, key, value);
/*    */     }
/*    */     
/*    */     public void update(Object key, Object value) {
/* 27 */       MapLike$class.update(this, key, value);
/*    */     }
/*    */     
/*    */     public Option<B> remove(Object key) {
/* 27 */       return MapLike$class.remove(this, key);
/*    */     }
/*    */     
/*    */     public void clear() {
/* 27 */       MapLike$class.clear(this);
/*    */     }
/*    */     
/*    */     public B getOrElseUpdate(Object key, Function0 op) {
/* 27 */       return (B)MapLike$class.getOrElseUpdate(this, key, op);
/*    */     }
/*    */     
/*    */     public MapLike<A, B, Map<A, B>> transform(Function2 f) {
/* 27 */       return MapLike$class.transform(this, f);
/*    */     }
/*    */     
/*    */     public MapLike<A, B, Map<A, B>> retain(Function2 p) {
/* 27 */       return MapLike$class.retain(this, p);
/*    */     }
/*    */     
/*    */     public Map<A, B> clone() {
/* 27 */       return MapLike$class.clone(this);
/*    */     }
/*    */     
/*    */     public Map<A, B> result() {
/* 27 */       return MapLike$class.result(this);
/*    */     }
/*    */     
/*    */     public Map<A, B> $minus(Object elem1, Object elem2, Seq elems) {
/* 27 */       return MapLike$class.$minus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Map<A, B> $minus$minus(GenTraversableOnce xs) {
/* 27 */       return MapLike$class.$minus$minus(this, xs);
/*    */     }
/*    */     
/*    */     public Object scala$collection$mutable$Cloneable$$super$clone() {
/* 27 */       return super.clone();
/*    */     }
/*    */     
/*    */     public Shrinkable<A> $minus$eq(Object elem1, Object elem2, Seq elems) {
/* 27 */       return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Shrinkable<A> $minus$minus$eq(TraversableOnce xs) {
/* 27 */       return Shrinkable.class.$minus$minus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public void sizeHint(int size) {
/* 27 */       Builder$class.sizeHint(this, size);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll) {
/* 27 */       Builder$class.sizeHint(this, coll);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll, int delta) {
/* 27 */       Builder$class.sizeHint(this, coll, delta);
/*    */     }
/*    */     
/*    */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 27 */       Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */     }
/*    */     
/*    */     public <NewTo> Builder<Tuple2<A, B>, NewTo> mapResult(Function1 f) {
/* 27 */       return Builder$class.mapResult(this, f);
/*    */     }
/*    */     
/*    */     public Growable<Tuple2<A, B>> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 27 */       return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<Tuple2<A, B>> $plus$plus$eq(TraversableOnce xs) {
/* 27 */       return Growable.class.$plus$plus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 27 */       return PartialFunction.class.orElse((PartialFunction)this, that);
/*    */     }
/*    */     
/*    */     public <C> PartialFunction<A, C> andThen(Function1 k) {
/* 27 */       return PartialFunction.class.andThen((PartialFunction)this, k);
/*    */     }
/*    */     
/*    */     public Function1<A, Option<B>> lift() {
/* 27 */       return PartialFunction.class.lift((PartialFunction)this);
/*    */     }
/*    */     
/*    */     public <A1 extends A, B1> B1 applyOrElse(Object x, Function1 default) {
/* 27 */       return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*    */     }
/*    */     
/*    */     public <U> Function1<A, Object> runWith(Function1 action) {
/* 27 */       return PartialFunction.class.runWith((PartialFunction)this, action);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZD$sp(double v1) {
/* 27 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDD$sp(double v1) {
/* 27 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFD$sp(double v1) {
/* 27 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcID$sp(double v1) {
/* 27 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJD$sp(double v1) {
/* 27 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVD$sp(double v1) {
/* 27 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZF$sp(float v1) {
/* 27 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDF$sp(float v1) {
/* 27 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFF$sp(float v1) {
/* 27 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIF$sp(float v1) {
/* 27 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJF$sp(float v1) {
/* 27 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVF$sp(float v1) {
/* 27 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int v1) {
/* 27 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDI$sp(int v1) {
/* 27 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFI$sp(int v1) {
/* 27 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcII$sp(int v1) {
/* 27 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJI$sp(int v1) {
/* 27 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVI$sp(int v1) {
/* 27 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZJ$sp(long v1) {
/* 27 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDJ$sp(long v1) {
/* 27 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFJ$sp(long v1) {
/* 27 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIJ$sp(long v1) {
/* 27 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJJ$sp(long v1) {
/* 27 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVJ$sp(long v1) {
/* 27 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, B> compose(Function1 g) {
/* 27 */       return Function1.class.compose((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 27 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 27 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public GenericCompanion<Iterable> companion() {
/* 27 */       return Iterable$class.companion(this);
/*    */     }
/*    */     
/*    */     public Iterable<Tuple2<A, B>> thisCollection() {
/* 27 */       return IterableLike.class.thisCollection(this);
/*    */     }
/*    */     
/*    */     public Iterable<Tuple2<A, B>> toCollection(Object repr) {
/* 27 */       return IterableLike.class.toCollection(this, repr);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object that) {
/* 27 */       return IterableLike.class.canEqual(this, that);
/*    */     }
/*    */     
/*    */     public <B> Builder<B, Iterable<B>> genericBuilder() {
/* 27 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*    */     }
/*    */     
/*    */     public <A1, A2> Tuple2<Iterable<A1>, Iterable<A2>> unzip(Function1 asPair) {
/* 27 */       return GenericTraversableTemplate.class.unzip(this, asPair);
/*    */     }
/*    */     
/*    */     public <A1, A2, A3> Tuple3<Iterable<A1>, Iterable<A2>, Iterable<A3>> unzip3(Function1 asTriple) {
/* 27 */       return GenericTraversableTemplate.class.unzip3(this, asTriple);
/*    */     }
/*    */     
/*    */     public <B> Iterable<B> flatten(Function1 asTraversable) {
/* 27 */       return (Iterable<B>)GenericTraversableTemplate.class.flatten(this, asTraversable);
/*    */     }
/*    */     
/*    */     public <B> Iterable<Iterable<B>> transpose(Function1 asTraversable) {
/* 27 */       return (Iterable<Iterable<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*    */     }
/*    */     
/*    */     public final boolean isTraversableAgain() {
/* 27 */       return TraversableLike.class.isTraversableAgain(this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 27 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 27 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 27 */       return (That)TraversableLike.class.scan(this, z, op, cbf);
/*    */     }
/*    */     
/*    */     public Map<A, B> sliceWithKnownDelta(int from, int until, int delta) {
/* 27 */       return (Map<A, B>)TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
/*    */     }
/*    */     
/*    */     public Map<A, B> sliceWithKnownBound(int from, int until) {
/* 27 */       return (Map<A, B>)TraversableLike.class.sliceWithKnownBound(this, from, until);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, B>> tails() {
/* 27 */       return TraversableLike.class.tails(this);
/*    */     }
/*    */     
/*    */     public Iterator<Map<A, B>> inits() {
/* 27 */       return TraversableLike.class.inits(this);
/*    */     }
/*    */     
/*    */     public <Col> Col to(CanBuildFrom cbf) {
/* 27 */       return (Col)TraversableLike.class.to(this, cbf);
/*    */     }
/*    */     
/*    */     public FilterMonadic<Tuple2<A, B>, Map<A, B>> withFilter(Function1 p) {
/* 27 */       return TraversableLike.class.withFilter(this, p);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> par() {
/* 27 */       return (ParMap<A, B>)Parallelizable.class.par(this);
/*    */     }
/*    */     
/*    */     public List<Tuple2<A, B>> reversed() {
/* 27 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 27 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*    */     }
/*    */     
/*    */     public <A1> A1 reduce(Function2 op) {
/* 27 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 27 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 fold(Object z, Function2 op) {
/* 27 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 27 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, B> maxBy(Function1 f, Ordering cmp) {
/* 27 */       return (Tuple2<A, B>)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> Tuple2<A, B> minBy(Function1 f, Ordering cmp) {
/* 27 */       return (Tuple2<A, B>)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public Vector<Tuple2<A, B>> toVector() {
/* 27 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 27 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public Map<A, B> self() {
/* 27 */       return this.self;
/*    */     }
/*    */     
/*    */     public MapProxy$$anon$2(MapProxy $outer) {
/* 27 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 27 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 27 */       Parallelizable.class.$init$(this);
/* 27 */       TraversableLike.class.$init$(this);
/* 27 */       GenericTraversableTemplate.class.$init$(this);
/* 27 */       GenTraversable.class.$init$((GenTraversable)this);
/* 27 */       Traversable.class.$init$(this);
/* 27 */       Traversable$class.$init$(this);
/* 27 */       GenIterable.class.$init$((GenIterable)this);
/* 27 */       IterableLike.class.$init$(this);
/* 27 */       Iterable.class.$init$(this);
/* 27 */       Iterable$class.$init$(this);
/* 27 */       GenMapLike.class.$init$((GenMapLike)this);
/* 27 */       Function1.class.$init$((Function1)this);
/* 27 */       PartialFunction.class.$init$((PartialFunction)this);
/* 27 */       Subtractable.class.$init$((Subtractable)this);
/* 27 */       MapLike.class.$init$(this);
/* 27 */       Map.class.$init$(this);
/* 27 */       Growable.class.$init$(this);
/* 27 */       Builder$class.$init$(this);
/* 27 */       Shrinkable.class.$init$(this);
/* 27 */       Cloneable$class.$init$(this);
/* 27 */       MapLike$class.$init$(this);
/* 27 */       Map$class.$init$(this);
/* 27 */       Proxy.class.$init$((Proxy)this);
/* 27 */       TraversableProxyLike.class.$init$((TraversableProxyLike)this);
/* 27 */       IterableProxyLike.class.$init$((IterableProxyLike)this);
/* 27 */       MapProxyLike.class.$init$(this);
/* 27 */       MapProxy$class.$init$(this);
/* 27 */       this.self = ((Map<A, B>)$outer.self()).empty();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\MapProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */