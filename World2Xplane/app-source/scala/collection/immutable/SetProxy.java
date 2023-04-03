/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Proxy;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.AbstractSet;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableProxyLike;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Set;
/*    */ import scala.collection.SetProxyLike;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableProxyLike;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.immutable.ParSet;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\r3q!\001\002\021\002\007\005\021B\001\005TKR\004&o\034=z\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"F\n\005\001-ya\004\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032\0042\001E\t\024\033\005\021\021B\001\n\003\005\r\031V\r\036\t\003)Ua\001\001B\003\027\001\t\007qCA\001B#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=\021\t}\0013cD\007\002\t%\021\021\005\002\002\r'\026$\bK]8ys2K7.\032\005\006G\001!\t\001J\001\007I%t\027\016\036\023\025\003\025\002\"\001\004\024\n\005\0352!\001B+oSRDQ!\013\001\005B)\nAA]3qeV\t1\006E\002\021\001MAQ!\f\001\005\n9\n\001B\\3x!J|\0070_\013\003_I\"\"\001M\033\021\007A\001\021\007\005\002\025e\021)1\007\fb\001i\t\t!)\005\002\0247!)a\007\fa\001o\0059a.Z<TK24\007c\001\t\022c!)\021\b\001C!U\005)Q-\0349us\")1\b\001C!y\005)A\005\0357vgR\0211&\020\005\006}i\002\raE\001\005K2,W\016C\003A\001\021\005\023)\001\004%[&tWo\035\013\003W\tCQAP A\002M\001")
/*    */ public interface SetProxy<A> extends Set<A>, SetProxyLike<A, Set<A>> {
/*    */   SetProxy<A> repr();
/*    */   
/*    */   SetProxy<A> empty();
/*    */   
/*    */   SetProxy<A> $plus(A paramA);
/*    */   
/*    */   SetProxy<A> $minus(A paramA);
/*    */   
/*    */   public class SetProxy$$anon$1 extends AbstractSet<Object> implements SetProxy<Object> {
/*    */     private final Set<Object> self;
/*    */     
/*    */     public SetProxy<Object> repr() {
/* 27 */       return SetProxy$class.repr(this);
/*    */     }
/*    */     
/*    */     public SetProxy<Object> empty() {
/* 27 */       return SetProxy$class.empty(this);
/*    */     }
/*    */     
/*    */     public SetProxy<Object> $plus(Object elem) {
/* 27 */       return SetProxy$class.$plus(this, elem);
/*    */     }
/*    */     
/*    */     public SetProxy<Object> $minus(Object elem) {
/* 27 */       return SetProxy$class.$minus(this, elem);
/*    */     }
/*    */     
/*    */     public boolean contains(Object elem) {
/* 27 */       return SetProxyLike.class.contains(this, elem);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 27 */       return SetProxyLike.class.isEmpty(this);
/*    */     }
/*    */     
/*    */     public boolean apply(Object elem) {
/* 27 */       return SetProxyLike.class.apply(this, elem);
/*    */     }
/*    */     
/*    */     public Set<Object> intersect(GenSet that) {
/* 27 */       return (Set<Object>)SetProxyLike.class.intersect(this, that);
/*    */     }
/*    */     
/*    */     public Set<Object> $amp(GenSet that) {
/* 27 */       return (Set<Object>)SetProxyLike.class.$amp(this, that);
/*    */     }
/*    */     
/*    */     public Set<Object> union(GenSet that) {
/* 27 */       return (Set<Object>)SetProxyLike.class.union(this, that);
/*    */     }
/*    */     
/*    */     public Set<Object> $bar(GenSet that) {
/* 27 */       return (Set<Object>)SetProxyLike.class.$bar(this, that);
/*    */     }
/*    */     
/*    */     public Set<Object> diff(GenSet that) {
/* 27 */       return (Set<Object>)SetProxyLike.class.diff(this, that);
/*    */     }
/*    */     
/*    */     public Set<Object> $amp$tilde(GenSet that) {
/* 27 */       return (Set<Object>)SetProxyLike.class.$amp$tilde(this, that);
/*    */     }
/*    */     
/*    */     public boolean subsetOf(GenSet that) {
/* 27 */       return SetProxyLike.class.subsetOf(this, that);
/*    */     }
/*    */     
/*    */     public Iterator<Object> iterator() {
/* 27 */       return IterableProxyLike.class.iterator((IterableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<Set<Object>> grouped(int size) {
/* 27 */       return IterableProxyLike.class.grouped((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Set<Object>> sliding(int size) {
/* 27 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Set<Object>> sliding(int size, int step) {
/* 27 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size, step);
/*    */     }
/*    */     
/*    */     public Set<Object> takeRight(int n) {
/* 27 */       return (Set<Object>)IterableProxyLike.class.takeRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Set<Object> dropRight(int n) {
/* 27 */       return (Set<Object>)IterableProxyLike.class.dropRight((IterableProxyLike)this, n);
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
/*    */     public IterableView<Object, Set<Object>> view(int from, int until) {
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
/*    */     public Set<Object> filter(Function1 p) {
/* 27 */       return (Set<Object>)TraversableProxyLike.class.filter((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Set<Object> filterNot(Function1 p) {
/* 27 */       return (Set<Object>)TraversableProxyLike.class.filterNot((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 27 */       return (That)TraversableProxyLike.class.collect((TraversableProxyLike)this, pf, bf);
/*    */     }
/*    */     
/*    */     public Tuple2<Set<Object>, Set<Object>> partition(Function1 p) {
/* 27 */       return TraversableProxyLike.class.partition((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <K> Map<K, Set<Object>> groupBy(Function1 f) {
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
/*    */     public Option<Object> find(Function1 p) {
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
/*    */     public <B> Object min(Ordering cmp) {
/* 27 */       return TraversableProxyLike.class.min((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> Object max(Ordering cmp) {
/* 27 */       return TraversableProxyLike.class.max((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public Object head() {
/* 27 */       return TraversableProxyLike.class.head((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<Object> headOption() {
/* 27 */       return TraversableProxyLike.class.headOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Set<Object> tail() {
/* 27 */       return (Set<Object>)TraversableProxyLike.class.tail((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Object last() {
/* 27 */       return TraversableProxyLike.class.last((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<Object> lastOption() {
/* 27 */       return TraversableProxyLike.class.lastOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Set<Object> init() {
/* 27 */       return (Set<Object>)TraversableProxyLike.class.init((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Set<Object> take(int n) {
/* 27 */       return (Set<Object>)TraversableProxyLike.class.take((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Set<Object> drop(int n) {
/* 27 */       return (Set<Object>)TraversableProxyLike.class.drop((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Set<Object> slice(int from, int until) {
/* 27 */       return (Set<Object>)TraversableProxyLike.class.slice((TraversableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public Set<Object> takeWhile(Function1 p) {
/* 27 */       return (Set<Object>)TraversableProxyLike.class.takeWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Set<Object> dropWhile(Function1 p) {
/* 27 */       return (Set<Object>)TraversableProxyLike.class.dropWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Set<Object>, Set<Object>> span(Function1 p) {
/* 27 */       return TraversableProxyLike.class.span((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Set<Object>, Set<Object>> splitAt(int n) {
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
/*    */     public List<Object> toList() {
/* 27 */       return TraversableProxyLike.class.toList((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterable<Object> toIterable() {
/* 27 */       return TraversableProxyLike.class.toIterable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Seq<Object> toSeq() {
/* 27 */       return TraversableProxyLike.class.toSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<Object> toIndexedSeq() {
/* 27 */       return TraversableProxyLike.class.toIndexedSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Buffer<B> toBuffer() {
/* 27 */       return TraversableProxyLike.class.toBuffer((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Stream<Object> toStream() {
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
/*    */     public Traversable<Object> toTraversable() {
/* 27 */       return TraversableProxyLike.class.toTraversable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<Object> toIterator() {
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
/*    */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 27 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b, start, sep, end);
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
/*    */     public GenericCompanion<Set> companion() {
/* 27 */       return Set$class.companion(this);
/*    */     }
/*    */     
/*    */     public Set<Object> seq() {
/* 27 */       return Set$class.seq(this);
/*    */     }
/*    */     
/*    */     public Combiner<Object, ParSet<Object>> parCombiner() {
/* 27 */       return Set$class.parCombiner(this);
/*    */     }
/*    */     
/*    */     public Set<Object> self() {
/* 27 */       return this.self;
/*    */     }
/*    */     
/*    */     public SetProxy$$anon$1(SetProxy $outer, Set<Object> newSelf$1) {
/* 27 */       Traversable$class.$init$(this);
/* 27 */       Iterable$class.$init$(this);
/* 27 */       Set$class.$init$(this);
/* 27 */       Proxy.class.$init$((Proxy)this);
/* 27 */       TraversableProxyLike.class.$init$((TraversableProxyLike)this);
/* 27 */       IterableProxyLike.class.$init$((IterableProxyLike)this);
/* 27 */       SetProxyLike.class.$init$(this);
/* 27 */       SetProxy$class.$init$(this);
/* 27 */       this.self = newSelf$1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\SetProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */