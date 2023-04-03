/*    */ package scala.collection.mutable;
/*    */ 
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
/*    */ import scala.collection.parallel.mutable.ParSet;
/*    */ import scala.collection.script.Message;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0353q!\001\002\021\002\007\005\021B\001\005TKR\004&o\034=z\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005))2\003\002\001\f\037y\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\r\001\022cE\007\002\005%\021!C\001\002\004'\026$\bC\001\013\026\031\001!QA\006\001C\002]\021\021!Q\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020\005\003 AMyQ\"\001\003\n\005\005\"!\001D*fiB\023x\016_=MS.,\007\"B\022\001\t\003!\023A\002\023j]&$H\005F\001&!\taa%\003\002(\r\t!QK\\5u\021\025I\003\001\"\021+\003\021\021X\r\035:\026\003-\0022\001\005\001\024\021\025i\003\001\"\021/\003\025)W\016\035;z+\005y#c\001\031\fW\031!\021\007\f\0010\0051a$/\0324j]\026lWM\034;?\021\035\031\004G1A\005\002Q\nAa]3mMV\tq\002C\0037\001\021\005s'A\003%a2,8\017\006\002,q!)\021(\016a\001'\005!Q\r\\3n\021\025Y\004\001\"\021=\003\031!S.\0338vgR\0211&\020\005\006si\002\ra\005\005\006\001!\t\001Q\001\tIAdWo\035\023fcR\021\021IQ\007\002\001!)\021H\020a\001'!)A\t\001C\001\013\006IA%\\5okN$S-\035\013\003\003\032CQ!O\"A\002M\001")
/*    */ public interface SetProxy<A> extends Set<A>, SetProxyLike<A, Set<A>> {
/*    */   SetProxy<A> repr();
/*    */   
/*    */   Object empty();
/*    */   
/*    */   SetProxy<A> $plus(A paramA);
/*    */   
/*    */   SetProxy<A> $minus(A paramA);
/*    */   
/*    */   SetProxy<A> $plus$eq(A paramA);
/*    */   
/*    */   SetProxy<A> $minus$eq(A paramA);
/*    */   
/*    */   public class SetProxy$$anon$1 implements SetProxy<A> {
/*    */     private final Set<A> self;
/*    */     
/*    */     public SetProxy<A> repr() {
/* 22 */       return SetProxy$class.repr(this);
/*    */     }
/*    */     
/*    */     public Object empty() {
/* 22 */       return SetProxy$class.empty(this);
/*    */     }
/*    */     
/*    */     public SetProxy<A> $plus(Object elem) {
/* 22 */       return SetProxy$class.$plus(this, elem);
/*    */     }
/*    */     
/*    */     public SetProxy<A> $minus(Object elem) {
/* 22 */       return SetProxy$class.$minus(this, elem);
/*    */     }
/*    */     
/*    */     public SetProxy<A> $plus$eq(Object elem) {
/* 22 */       return SetProxy$class.$plus$eq(this, elem);
/*    */     }
/*    */     
/*    */     public SetProxy<A> $minus$eq(Object elem) {
/* 22 */       return SetProxy$class.$minus$eq(this, elem);
/*    */     }
/*    */     
/*    */     public boolean contains(Object elem) {
/* 22 */       return SetProxyLike.class.contains(this, elem);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 22 */       return SetProxyLike.class.isEmpty(this);
/*    */     }
/*    */     
/*    */     public boolean apply(Object elem) {
/* 22 */       return SetProxyLike.class.apply(this, elem);
/*    */     }
/*    */     
/*    */     public Set<A> intersect(GenSet that) {
/* 22 */       return (Set<A>)SetProxyLike.class.intersect(this, that);
/*    */     }
/*    */     
/*    */     public Set<A> $amp(GenSet that) {
/* 22 */       return (Set<A>)SetProxyLike.class.$amp(this, that);
/*    */     }
/*    */     
/*    */     public Set<A> union(GenSet that) {
/* 22 */       return (Set<A>)SetProxyLike.class.union(this, that);
/*    */     }
/*    */     
/*    */     public Set<A> $bar(GenSet that) {
/* 22 */       return (Set<A>)SetProxyLike.class.$bar(this, that);
/*    */     }
/*    */     
/*    */     public Set<A> diff(GenSet that) {
/* 22 */       return (Set<A>)SetProxyLike.class.diff(this, that);
/*    */     }
/*    */     
/*    */     public Set<A> $amp$tilde(GenSet that) {
/* 22 */       return (Set<A>)SetProxyLike.class.$amp$tilde(this, that);
/*    */     }
/*    */     
/*    */     public boolean subsetOf(GenSet that) {
/* 22 */       return SetProxyLike.class.subsetOf(this, that);
/*    */     }
/*    */     
/*    */     public Iterator<A> iterator() {
/* 22 */       return IterableProxyLike.class.iterator((IterableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> grouped(int size) {
/* 22 */       return IterableProxyLike.class.grouped((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> sliding(int size) {
/* 22 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> sliding(int size, int step) {
/* 22 */       return IterableProxyLike.class.sliding((IterableProxyLike)this, size, step);
/*    */     }
/*    */     
/*    */     public Set<A> takeRight(int n) {
/* 22 */       return (Set<A>)IterableProxyLike.class.takeRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Set<A> dropRight(int n) {
/* 22 */       return (Set<A>)IterableProxyLike.class.dropRight((IterableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 22 */       return (That)IterableProxyLike.class.zip((IterableProxyLike)this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 22 */       return (That)IterableProxyLike.class.zipAll((IterableProxyLike)this, that, thisElem, thatElem, bf);
/*    */     }
/*    */     
/*    */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 22 */       return (That)IterableProxyLike.class.zipWithIndex((IterableProxyLike)this, bf);
/*    */     }
/*    */     
/*    */     public <B> boolean sameElements(GenIterable that) {
/* 22 */       return IterableProxyLike.class.sameElements((IterableProxyLike)this, that);
/*    */     }
/*    */     
/*    */     public Object view() {
/* 22 */       return IterableProxyLike.class.view((IterableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IterableView<A, Set<A>> view(int from, int until) {
/* 22 */       return IterableProxyLike.class.view((IterableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public <B> void foreach(Function1 f) {
/* 22 */       TraversableProxyLike.class.foreach((TraversableProxyLike)this, f);
/*    */     }
/*    */     
/*    */     public boolean nonEmpty() {
/* 22 */       return TraversableProxyLike.class.nonEmpty((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public int size() {
/* 22 */       return TraversableProxyLike.class.size((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public boolean hasDefiniteSize() {
/* 22 */       return TraversableProxyLike.class.hasDefiniteSize((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 22 */       return (That)TraversableProxyLike.class.$plus$plus((TraversableProxyLike)this, xs, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 22 */       return (That)TraversableProxyLike.class.map((TraversableProxyLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 22 */       return (That)TraversableProxyLike.class.flatMap((TraversableProxyLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public Set<A> filter(Function1 p) {
/* 22 */       return (Set<A>)TraversableProxyLike.class.filter((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Set<A> filterNot(Function1 p) {
/* 22 */       return (Set<A>)TraversableProxyLike.class.filterNot((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 22 */       return (That)TraversableProxyLike.class.collect((TraversableProxyLike)this, pf, bf);
/*    */     }
/*    */     
/*    */     public Tuple2<Set<A>, Set<A>> partition(Function1 p) {
/* 22 */       return TraversableProxyLike.class.partition((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <K> Map<K, Set<A>> groupBy(Function1 f) {
/* 22 */       return TraversableProxyLike.class.groupBy((TraversableProxyLike)this, f);
/*    */     }
/*    */     
/*    */     public boolean forall(Function1 p) {
/* 22 */       return TraversableProxyLike.class.forall((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public boolean exists(Function1 p) {
/* 22 */       return TraversableProxyLike.class.exists((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/* 22 */       return TraversableProxyLike.class.count((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Option<A> find(Function1 p) {
/* 22 */       return TraversableProxyLike.class.find((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public <B> B foldLeft(Object z, Function2 op) {
/* 22 */       return (B)TraversableProxyLike.class.foldLeft((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $div$colon(Object z, Function2 op) {
/* 22 */       return (B)TraversableProxyLike.class.$div$colon((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldRight(Object z, Function2 op) {
/* 22 */       return (B)TraversableProxyLike.class.foldRight((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 22 */       return (B)TraversableProxyLike.class.$colon$bslash((TraversableProxyLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceLeft(Function2 op) {
/* 22 */       return (B)TraversableProxyLike.class.reduceLeft((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 22 */       return TraversableProxyLike.class.reduceLeftOption((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceRight(Function2 op) {
/* 22 */       return (B)TraversableProxyLike.class.reduceRight((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 22 */       return TraversableProxyLike.class.reduceRightOption((TraversableProxyLike)this, op);
/*    */     }
/*    */     
/*    */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 22 */       return (That)TraversableProxyLike.class.scanLeft((TraversableProxyLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 22 */       return (That)TraversableProxyLike.class.scanRight((TraversableProxyLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B> B sum(Numeric num) {
/* 22 */       return (B)TraversableProxyLike.class.sum((TraversableProxyLike)this, num);
/*    */     }
/*    */     
/*    */     public <B> B product(Numeric num) {
/* 22 */       return (B)TraversableProxyLike.class.product((TraversableProxyLike)this, num);
/*    */     }
/*    */     
/*    */     public <B> A min(Ordering cmp) {
/* 22 */       return (A)TraversableProxyLike.class.min((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> A max(Ordering cmp) {
/* 22 */       return (A)TraversableProxyLike.class.max((TraversableProxyLike)this, cmp);
/*    */     }
/*    */     
/*    */     public A head() {
/* 22 */       return (A)TraversableProxyLike.class.head((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<A> headOption() {
/* 22 */       return TraversableProxyLike.class.headOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Set<A> tail() {
/* 22 */       return (Set<A>)TraversableProxyLike.class.tail((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public A last() {
/* 22 */       return (A)TraversableProxyLike.class.last((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Option<A> lastOption() {
/* 22 */       return TraversableProxyLike.class.lastOption((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Set<A> init() {
/* 22 */       return (Set<A>)TraversableProxyLike.class.init((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Set<A> take(int n) {
/* 22 */       return (Set<A>)TraversableProxyLike.class.take((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Set<A> drop(int n) {
/* 22 */       return (Set<A>)TraversableProxyLike.class.drop((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public Set<A> slice(int from, int until) {
/* 22 */       return (Set<A>)TraversableProxyLike.class.slice((TraversableProxyLike)this, from, until);
/*    */     }
/*    */     
/*    */     public Set<A> takeWhile(Function1 p) {
/* 22 */       return (Set<A>)TraversableProxyLike.class.takeWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Set<A> dropWhile(Function1 p) {
/* 22 */       return (Set<A>)TraversableProxyLike.class.dropWhile((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Set<A>, Set<A>> span(Function1 p) {
/* 22 */       return TraversableProxyLike.class.span((TraversableProxyLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Set<A>, Set<A>> splitAt(int n) {
/* 22 */       return TraversableProxyLike.class.splitAt((TraversableProxyLike)this, n);
/*    */     }
/*    */     
/*    */     public <B> void copyToBuffer(Buffer dest) {
/* 22 */       TraversableProxyLike.class.copyToBuffer((TraversableProxyLike)this, dest);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start, int len) {
/* 22 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs, start, len);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start) {
/* 22 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs, start);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs) {
/* 22 */       TraversableProxyLike.class.copyToArray((TraversableProxyLike)this, xs);
/*    */     }
/*    */     
/*    */     public <B> Object toArray(ClassTag evidence$1) {
/* 22 */       return TraversableProxyLike.class.toArray((TraversableProxyLike)this, evidence$1);
/*    */     }
/*    */     
/*    */     public List<A> toList() {
/* 22 */       return TraversableProxyLike.class.toList((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterable<A> toIterable() {
/* 22 */       return TraversableProxyLike.class.toIterable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Seq<A> toSeq() {
/* 22 */       return TraversableProxyLike.class.toSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> toIndexedSeq() {
/* 22 */       return TraversableProxyLike.class.toIndexedSeq((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Buffer<B> toBuffer() {
/* 22 */       return TraversableProxyLike.class.toBuffer((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Stream<A> toStream() {
/* 22 */       return TraversableProxyLike.class.toStream((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <B> Set<B> toSet() {
/* 22 */       return TraversableProxyLike.class.toSet((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 22 */       return TraversableProxyLike.class.toMap((TraversableProxyLike)this, ev);
/*    */     }
/*    */     
/*    */     public Traversable<A> toTraversable() {
/* 22 */       return TraversableProxyLike.class.toTraversable((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<A> toIterator() {
/* 22 */       return TraversableProxyLike.class.toIterator((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public String mkString(String start, String sep, String end) {
/* 22 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this, start, sep, end);
/*    */     }
/*    */     
/*    */     public String mkString(String sep) {
/* 22 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this, sep);
/*    */     }
/*    */     
/*    */     public String mkString() {
/* 22 */       return TraversableProxyLike.class.mkString((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 22 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b, start, sep, end);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String sep) {
/* 22 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b, sep);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b) {
/* 22 */       return TraversableProxyLike.class.addString((TraversableProxyLike)this, b);
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 22 */       return TraversableProxyLike.class.stringPrefix((TraversableProxyLike)this);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 22 */       return Proxy.class.hashCode((Proxy)this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object that) {
/* 22 */       return Proxy.class.equals((Proxy)this, that);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 22 */       return Proxy.class.toString((Proxy)this);
/*    */     }
/*    */     
/*    */     public GenericCompanion<Set> companion() {
/* 22 */       return Set$class.companion(this);
/*    */     }
/*    */     
/*    */     public Set<A> seq() {
/* 22 */       return Set$class.seq(this);
/*    */     }
/*    */     
/*    */     public Builder<A, Set<A>> newBuilder() {
/* 22 */       return SetLike$class.newBuilder(this);
/*    */     }
/*    */     
/*    */     public Combiner<A, ParSet<A>> parCombiner() {
/* 22 */       return SetLike$class.parCombiner(this);
/*    */     }
/*    */     
/*    */     public boolean add(Object elem) {
/* 22 */       return SetLike$class.add(this, elem);
/*    */     }
/*    */     
/*    */     public boolean remove(Object elem) {
/* 22 */       return SetLike$class.remove(this, elem);
/*    */     }
/*    */     
/*    */     public void update(Object elem, boolean included) {
/* 22 */       SetLike$class.update(this, elem, included);
/*    */     }
/*    */     
/*    */     public void retain(Function1 p) {
/* 22 */       SetLike$class.retain(this, p);
/*    */     }
/*    */     
/*    */     public void clear() {
/* 22 */       SetLike$class.clear(this);
/*    */     }
/*    */     
/*    */     public Set<A> clone() {
/* 22 */       return SetLike$class.clone(this);
/*    */     }
/*    */     
/*    */     public Set<A> result() {
/* 22 */       return SetLike$class.result(this);
/*    */     }
/*    */     
/*    */     public Set<A> $plus(Object elem1, Object elem2, Seq elems) {
/* 22 */       return SetLike$class.$plus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Set<A> $plus$plus(GenTraversableOnce xs) {
/* 22 */       return SetLike$class.$plus$plus(this, xs);
/*    */     }
/*    */     
/*    */     public Set<A> $minus(Object elem1, Object elem2, Seq elems) {
/* 22 */       return SetLike$class.$minus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Set<A> $minus$minus(GenTraversableOnce xs) {
/* 22 */       return SetLike$class.$minus$minus(this, xs);
/*    */     }
/*    */     
/*    */     public void $less$less(Message cmd) {
/* 22 */       SetLike$class.$less$less(this, cmd);
/*    */     }
/*    */     
/*    */     public Object scala$collection$mutable$Cloneable$$super$clone() {
/* 22 */       return super.clone();
/*    */     }
/*    */     
/*    */     public Shrinkable<A> $minus$eq(Object elem1, Object elem2, Seq elems) {
/* 22 */       return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Shrinkable<A> $minus$minus$eq(TraversableOnce xs) {
/* 22 */       return Shrinkable.class.$minus$minus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public void sizeHint(int size) {
/* 22 */       Builder$class.sizeHint(this, size);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll) {
/* 22 */       Builder$class.sizeHint(this, coll);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll, int delta) {
/* 22 */       Builder$class.sizeHint(this, coll, delta);
/*    */     }
/*    */     
/*    */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 22 */       Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */     }
/*    */     
/*    */     public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/* 22 */       return Builder$class.mapResult(this, f);
/*    */     }
/*    */     
/*    */     public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 22 */       return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/* 22 */       return Growable.class.$plus$plus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public Object scala$collection$SetLike$$super$map(Function1 f, CanBuildFrom bf) {
/* 22 */       return TraversableLike.class.map(this, f, bf);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> subsets(int len) {
/* 22 */       return SetLike.class.subsets(this, len);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> subsets() {
/* 22 */       return SetLike.class.subsets(this);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZD$sp(double v1) {
/* 22 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDD$sp(double v1) {
/* 22 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFD$sp(double v1) {
/* 22 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcID$sp(double v1) {
/* 22 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJD$sp(double v1) {
/* 22 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVD$sp(double v1) {
/* 22 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZF$sp(float v1) {
/* 22 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDF$sp(float v1) {
/* 22 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFF$sp(float v1) {
/* 22 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIF$sp(float v1) {
/* 22 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJF$sp(float v1) {
/* 22 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVF$sp(float v1) {
/* 22 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int v1) {
/* 22 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDI$sp(int v1) {
/* 22 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFI$sp(int v1) {
/* 22 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcII$sp(int v1) {
/* 22 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJI$sp(int v1) {
/* 22 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVI$sp(int v1) {
/* 22 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZJ$sp(long v1) {
/* 22 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDJ$sp(long v1) {
/* 22 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFJ$sp(long v1) {
/* 22 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIJ$sp(long v1) {
/* 22 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJJ$sp(long v1) {
/* 22 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVJ$sp(long v1) {
/* 22 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose(Function1 g) {
/* 22 */       return Function1.class.compose((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 22 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, A> andThen(Function1 g) {
/* 22 */       return Function1.class.andThen((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 22 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public Iterable<A> thisCollection() {
/* 22 */       return IterableLike.class.thisCollection(this);
/*    */     }
/*    */     
/*    */     public Iterable<A> toCollection(Object repr) {
/* 22 */       return IterableLike.class.toCollection(this, repr);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object that) {
/* 22 */       return IterableLike.class.canEqual(this, that);
/*    */     }
/*    */     
/*    */     public <B> Builder<B, Set<B>> genericBuilder() {
/* 22 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*    */     }
/*    */     
/*    */     public <A1, A2> Tuple2<Set<A1>, Set<A2>> unzip(Function1 asPair) {
/* 22 */       return GenericTraversableTemplate.class.unzip(this, asPair);
/*    */     }
/*    */     
/*    */     public <A1, A2, A3> Tuple3<Set<A1>, Set<A2>, Set<A3>> unzip3(Function1 asTriple) {
/* 22 */       return GenericTraversableTemplate.class.unzip3(this, asTriple);
/*    */     }
/*    */     
/*    */     public <B> Set<B> flatten(Function1 asTraversable) {
/* 22 */       return (Set<B>)GenericTraversableTemplate.class.flatten(this, asTraversable);
/*    */     }
/*    */     
/*    */     public <B> Set<Set<B>> transpose(Function1 asTraversable) {
/* 22 */       return (Set<Set<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*    */     }
/*    */     
/*    */     public final boolean isTraversableAgain() {
/* 22 */       return TraversableLike.class.isTraversableAgain(this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 22 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 22 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 22 */       return (That)TraversableLike.class.scan(this, z, op, cbf);
/*    */     }
/*    */     
/*    */     public Set<A> sliceWithKnownDelta(int from, int until, int delta) {
/* 22 */       return (Set<A>)TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
/*    */     }
/*    */     
/*    */     public Set<A> sliceWithKnownBound(int from, int until) {
/* 22 */       return (Set<A>)TraversableLike.class.sliceWithKnownBound(this, from, until);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> tails() {
/* 22 */       return TraversableLike.class.tails(this);
/*    */     }
/*    */     
/*    */     public Iterator<Set<A>> inits() {
/* 22 */       return TraversableLike.class.inits(this);
/*    */     }
/*    */     
/*    */     public <Col> Col to(CanBuildFrom cbf) {
/* 22 */       return (Col)TraversableLike.class.to(this, cbf);
/*    */     }
/*    */     
/*    */     public FilterMonadic<A, Set<A>> withFilter(Function1 p) {
/* 22 */       return TraversableLike.class.withFilter(this, p);
/*    */     }
/*    */     
/*    */     public ParSet<A> par() {
/* 22 */       return (ParSet<A>)Parallelizable.class.par(this);
/*    */     }
/*    */     
/*    */     public List<A> reversed() {
/* 22 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 22 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*    */     }
/*    */     
/*    */     public <A1> A1 reduce(Function2 op) {
/* 22 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 22 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 fold(Object z, Function2 op) {
/* 22 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 22 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*    */     }
/*    */     
/*    */     public <B> A maxBy(Function1 f, Ordering cmp) {
/* 22 */       return (A)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> A minBy(Function1 f, Ordering cmp) {
/* 22 */       return (A)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public Vector<A> toVector() {
/* 22 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 22 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public Set<A> self() {
/* 22 */       return this.self;
/*    */     }
/*    */     
/*    */     public SetProxy$$anon$1(SetProxy $outer) {
/* 22 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 22 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 22 */       Parallelizable.class.$init$(this);
/* 22 */       TraversableLike.class.$init$(this);
/* 22 */       GenericTraversableTemplate.class.$init$(this);
/* 22 */       GenTraversable.class.$init$((GenTraversable)this);
/* 22 */       Traversable.class.$init$(this);
/* 22 */       Traversable$class.$init$(this);
/* 22 */       GenIterable.class.$init$((GenIterable)this);
/* 22 */       IterableLike.class.$init$(this);
/* 22 */       Iterable.class.$init$(this);
/* 22 */       Iterable$class.$init$(this);
/* 22 */       Function1.class.$init$((Function1)this);
/* 22 */       GenSetLike.class.$init$((GenSetLike)this);
/* 22 */       GenericSetTemplate.class.$init$(this);
/* 22 */       GenSet.class.$init$((GenSet)this);
/* 22 */       Subtractable.class.$init$((Subtractable)this);
/* 22 */       SetLike.class.$init$(this);
/* 22 */       Set.class.$init$(this);
/* 22 */       Growable.class.$init$(this);
/* 22 */       Builder$class.$init$(this);
/* 22 */       Shrinkable.class.$init$(this);
/* 22 */       Cloneable$class.$init$(this);
/* 22 */       SetLike$class.$init$(this);
/* 22 */       Set$class.$init$(this);
/* 22 */       Proxy.class.$init$((Proxy)this);
/* 22 */       TraversableProxyLike.class.$init$((TraversableProxyLike)this);
/* 22 */       IterableProxyLike.class.$init$((IterableProxyLike)this);
/* 22 */       SetProxyLike.class.$init$(this);
/* 22 */       SetProxy$class.$init$(this);
/* 22 */       this.self = (Set<A>)((GenericSetTemplate)$outer.self()).empty();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SetProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */