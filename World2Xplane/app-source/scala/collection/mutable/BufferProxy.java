/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Proxy;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
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
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParSeq;
/*     */ import scala.collection.script.Message;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]baB\001\003!\003\r\t!\003\002\f\005V4g-\032:Qe>D\030P\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\026'\021\0011b\004\020\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rE\002\021#Mi\021AA\005\003%\t\021aAQ;gM\026\024\bC\001\013\026\031\001!QA\006\001C\002]\021\021!Q\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020\005\002\r?%\021\001E\002\002\006!J|\0070\037\005\006E\001!\taI\001\007I%t\027\016\036\023\025\003\021\002\"\001D\023\n\005\0312!\001B+oSRDQ\001\013\001\007\002%\nAa]3mMV\tq\002C\003,\001\021\005A&\001\004mK:<G\017[\013\002[A\021ABL\005\003_\031\0211!\0238u\021\025\t\004\001\"\0213\003!IG/\032:bi>\024X#A\032\021\007Q*4#D\001\005\023\t1DA\001\005Ji\026\024\030\r^8s\021\025A\004\001\"\001:\003\025\t\007\017\0357z)\t\031\"\bC\003<o\001\007Q&A\001o\021\025i\004\001\"\001?\003!!\003\017\\;tI\025\fHCA A\033\005\001\001\"B!=\001\004\031\022\001B3mK6DQa\021\001\005B\021\013\001B]3bI>sG._\013\002\013B\031AGR\n\n\005\035#!aA*fc\")\021\n\001C!\025\006iA\005\0357vg\022\002H.^:%KF$\"aP&\t\0131C\005\031A'\002\005a\034\bc\001\033O'%\021q\n\002\002\020)J\fg/\032:tC\ndWm\0248dK\")\021\013\001C!%\0061\021\r\0359f]\022$\"\001J*\t\013Q\003\006\031A+\002\013\025dW-\\:\021\007116#\003\002X\r\tQAH]3qK\006$X\r\032 \t\013e\003A\021\t.\002\023\005\004\b/\0328e\0032dGC\001\023\\\021\025a\005\f1\001N\021\025i\006\001\"\001_\0039!\003\017\\;tI\025\fHeY8m_:$\"aP0\t\013\005c\006\031A\n\t\013\005\004A\021\t2\002'\021\002H.^:%a2,8\017J3rI\r|Gn\0348\025\005}\032\007\"\002'a\001\004i\005\"B3\001\t\0032\027a\0029sKB,g\016\032\013\003I\035DQ\001\0263A\002UCQ!\033\001\005B)\f!\002\035:fa\026tG-\0217m)\t!3\016C\003MQ\002\007Q\nC\003n\001\021\005c.\001\004j]N,'\017\036\013\004I=\004\b\"B\036m\001\004i\003\"\002+m\001\004)\006\"\002:\001\t\003\031\030!C5og\026\024H/\0217m)\r!C/\036\005\006wE\004\r!\f\005\006mF\004\ra^\001\005SR,'\017E\0025qNI!!\037\003\003\021%#XM]1cY\026DQA\035\001\005Bm$2\001\n?~\021\025Y$\0201\001.\021\0251(\0201\001!\r!tpE\005\004\003\003!!a\003+sCZ,'o]1cY\026Dq!!\002\001\t\003\t9!\001\004va\022\fG/\032\013\006I\005%\0211\002\005\007w\005\r\001\031A\027\t\017\0055\0211\001a\001'\0059a.Z<fY\026l\007bBA\t\001\021\005\0211C\001\007e\026lwN^3\025\007M\t)\002\003\004<\003\037\001\r!\f\005\007\0033\001A\021A\022\002\013\rdW-\031:\t\017\005u\001\001\"\021\002 \005QA\005\\3tg\022bWm]:\025\007\021\n\t\003\003\005\002$\005m\001\031AA\023\003\r\031W\016\032\t\006\003O\ticE\007\003\003SQ1!a\013\005\003\031\0318M]5qi&!\021qFA\025\005\035iUm]:bO\026Dq!a\r\001\t\003\n)$A\003dY>tW\rF\001\020\001")
/*     */ public interface BufferProxy<A> extends Buffer<A>, Proxy {
/*     */   Buffer<A> self();
/*     */   
/*     */   int length();
/*     */   
/*     */   Iterator<A> iterator();
/*     */   
/*     */   A apply(int paramInt);
/*     */   
/*     */   BufferProxy<A> $plus$eq(A paramA);
/*     */   
/*     */   Seq<A> readOnly();
/*     */   
/*     */   BufferProxy<A> $plus$plus$eq(TraversableOnce<A> paramTraversableOnce);
/*     */   
/*     */   void append(Seq<A> paramSeq);
/*     */   
/*     */   void appendAll(TraversableOnce<A> paramTraversableOnce);
/*     */   
/*     */   BufferProxy<A> $plus$eq$colon(A paramA);
/*     */   
/*     */   BufferProxy<A> $plus$plus$eq$colon(TraversableOnce<A> paramTraversableOnce);
/*     */   
/*     */   void prepend(Seq<A> paramSeq);
/*     */   
/*     */   void prependAll(TraversableOnce<A> paramTraversableOnce);
/*     */   
/*     */   void insert(int paramInt, Seq<A> paramSeq);
/*     */   
/*     */   void insertAll(int paramInt, Iterable<A> paramIterable);
/*     */   
/*     */   void insertAll(int paramInt, Traversable<A> paramTraversable);
/*     */   
/*     */   void update(int paramInt, A paramA);
/*     */   
/*     */   A remove(int paramInt);
/*     */   
/*     */   void clear();
/*     */   
/*     */   void $less$less(Message<A> paramMessage);
/*     */   
/*     */   Buffer<A> clone();
/*     */   
/*     */   public class BufferProxy$$anon$1 implements BufferProxy<A> {
/*     */     public int length() {
/* 142 */       return BufferProxy$class.length(this);
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/* 142 */       return BufferProxy$class.iterator(this);
/*     */     }
/*     */     
/*     */     public A apply(int n) {
/* 142 */       return (A)BufferProxy$class.apply(this, n);
/*     */     }
/*     */     
/*     */     public BufferProxy<A> $plus$eq(Object elem) {
/* 142 */       return BufferProxy$class.$plus$eq(this, elem);
/*     */     }
/*     */     
/*     */     public Seq<A> readOnly() {
/* 142 */       return BufferProxy$class.readOnly(this);
/*     */     }
/*     */     
/*     */     public BufferProxy<A> $plus$plus$eq(TraversableOnce xs) {
/* 142 */       return BufferProxy$class.$plus$plus$eq(this, xs);
/*     */     }
/*     */     
/*     */     public void append(Seq elems) {
/* 142 */       BufferProxy$class.append(this, elems);
/*     */     }
/*     */     
/*     */     public void appendAll(TraversableOnce xs) {
/* 142 */       BufferProxy$class.appendAll(this, xs);
/*     */     }
/*     */     
/*     */     public BufferProxy<A> $plus$eq$colon(Object elem) {
/* 142 */       return BufferProxy$class.$plus$eq$colon(this, elem);
/*     */     }
/*     */     
/*     */     public BufferProxy<A> $plus$plus$eq$colon(TraversableOnce xs) {
/* 142 */       return BufferProxy$class.$plus$plus$eq$colon(this, xs);
/*     */     }
/*     */     
/*     */     public void prepend(Seq elems) {
/* 142 */       BufferProxy$class.prepend(this, elems);
/*     */     }
/*     */     
/*     */     public void prependAll(TraversableOnce xs) {
/* 142 */       BufferProxy$class.prependAll(this, xs);
/*     */     }
/*     */     
/*     */     public void insert(int n, Seq elems) {
/* 142 */       BufferProxy$class.insert(this, n, elems);
/*     */     }
/*     */     
/*     */     public void insertAll(int n, Iterable iter) {
/* 142 */       BufferProxy$class.insertAll(this, n, iter);
/*     */     }
/*     */     
/*     */     public void insertAll(int n, Traversable iter) {
/* 142 */       BufferProxy$class.insertAll(this, n, iter);
/*     */     }
/*     */     
/*     */     public void update(int n, Object newelem) {
/* 142 */       BufferProxy$class.update(this, n, newelem);
/*     */     }
/*     */     
/*     */     public A remove(int n) {
/* 142 */       return (A)BufferProxy$class.remove(this, n);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 142 */       BufferProxy$class.clear(this);
/*     */     }
/*     */     
/*     */     public void $less$less(Message cmd) {
/* 142 */       BufferProxy$class.$less$less(this, cmd);
/*     */     }
/*     */     
/*     */     public Buffer<A> clone() {
/* 142 */       return BufferProxy$class.clone(this);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 142 */       return Proxy.class.hashCode(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 142 */       return Proxy.class.equals(this, that);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 142 */       return Proxy.class.toString(this);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Buffer> companion() {
/* 142 */       return Buffer$class.companion(this);
/*     */     }
/*     */     
/*     */     public void remove(int n, int count) {
/* 142 */       BufferLike$class.remove(this, n, count);
/*     */     }
/*     */     
/*     */     public BufferLike<A, Buffer<A>> $minus$eq(Object x) {
/* 142 */       return BufferLike$class.$minus$eq(this, x);
/*     */     }
/*     */     
/*     */     public void trimStart(int n) {
/* 142 */       BufferLike$class.trimStart(this, n);
/*     */     }
/*     */     
/*     */     public void trimEnd(int n) {
/* 142 */       BufferLike$class.trimEnd(this, n);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/* 142 */       return BufferLike$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public Buffer<A> $plus$plus(GenTraversableOnce xs) {
/* 142 */       return BufferLike$class.$plus$plus(this, xs);
/*     */     }
/*     */     
/*     */     public Buffer<A> $minus(Object elem) {
/* 142 */       return BufferLike$class.$minus(this, elem);
/*     */     }
/*     */     
/*     */     public Buffer<A> $minus(Object elem1, Object elem2, Seq elems) {
/* 142 */       return BufferLike$class.$minus(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public Buffer<A> $minus$minus(GenTraversableOnce xs) {
/* 142 */       return BufferLike$class.$minus$minus(this, xs);
/*     */     }
/*     */     
/*     */     public Shrinkable<A> $minus$eq(Object elem1, Object elem2, Seq elems) {
/* 142 */       return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public Shrinkable<A> $minus$minus$eq(TraversableOnce xs) {
/* 142 */       return Shrinkable.class.$minus$minus$eq(this, xs);
/*     */     }
/*     */     
/*     */     public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 142 */       return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public Seq<A> seq() {
/* 142 */       return Seq$class.seq(this);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParSeq<A>> parCombiner() {
/* 142 */       return SeqLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public SeqLike<A, Buffer<A>> transform(Function1 f) {
/* 142 */       return SeqLike$class.transform(this, f);
/*     */     }
/*     */     
/*     */     public Object scala$collection$mutable$Cloneable$$super$clone() {
/* 142 */       return super.clone();
/*     */     }
/*     */     
/*     */     public Seq<A> thisCollection() {
/* 142 */       return SeqLike.class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public Seq<A> toCollection(Object repr) {
/* 142 */       return SeqLike.class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public int lengthCompare(int len) {
/* 142 */       return SeqLike.class.lengthCompare(this, len);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 142 */       return SeqLike.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 142 */       return SeqLike.class.size(this);
/*     */     }
/*     */     
/*     */     public int segmentLength(Function1 p, int from) {
/* 142 */       return SeqLike.class.segmentLength(this, p, from);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p, int from) {
/* 142 */       return SeqLike.class.indexWhere(this, p, from);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p, int end) {
/* 142 */       return SeqLike.class.lastIndexWhere(this, p, end);
/*     */     }
/*     */     
/*     */     public Iterator<Buffer<A>> permutations() {
/* 142 */       return SeqLike.class.permutations(this);
/*     */     }
/*     */     
/*     */     public Iterator<Buffer<A>> combinations(int n) {
/* 142 */       return SeqLike.class.combinations(this, n);
/*     */     }
/*     */     
/*     */     public Buffer<A> reverse() {
/* 142 */       return (Buffer<A>)SeqLike.class.reverse(this);
/*     */     }
/*     */     
/*     */     public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/* 142 */       return (That)SeqLike.class.reverseMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public Iterator<A> reverseIterator() {
/* 142 */       return SeqLike.class.reverseIterator(this);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that, int offset) {
/* 142 */       return SeqLike.class.startsWith(this, that, offset);
/*     */     }
/*     */     
/*     */     public <B> boolean endsWith(GenSeq that) {
/* 142 */       return SeqLike.class.endsWith(this, that);
/*     */     }
/*     */     
/*     */     public <B> int indexOfSlice(GenSeq that) {
/* 142 */       return SeqLike.class.indexOfSlice(this, that);
/*     */     }
/*     */     
/*     */     public <B> int indexOfSlice(GenSeq that, int from) {
/* 142 */       return SeqLike.class.indexOfSlice(this, that, from);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOfSlice(GenSeq that) {
/* 142 */       return SeqLike.class.lastIndexOfSlice(this, that);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOfSlice(GenSeq that, int end) {
/* 142 */       return SeqLike.class.lastIndexOfSlice(this, that, end);
/*     */     }
/*     */     
/*     */     public <B> boolean containsSlice(GenSeq that) {
/* 142 */       return SeqLike.class.containsSlice(this, that);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 142 */       return SeqLike.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/* 142 */       return (That)SeqLike.class.union(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B> Buffer<A> diff(GenSeq that) {
/* 142 */       return (Buffer<A>)SeqLike.class.diff(this, that);
/*     */     }
/*     */     
/*     */     public <B> Buffer<A> intersect(GenSeq that) {
/* 142 */       return (Buffer<A>)SeqLike.class.intersect(this, that);
/*     */     }
/*     */     
/*     */     public Buffer<A> distinct() {
/* 142 */       return (Buffer<A>)SeqLike.class.distinct(this);
/*     */     }
/*     */     
/*     */     public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 142 */       return (That)SeqLike.class.patch(this, from, patch, replaced, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/* 142 */       return (That)SeqLike.class.updated(this, index, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/* 142 */       return (That)SeqLike.class.$plus$colon(this, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/* 142 */       return (That)SeqLike.class.$colon$plus(this, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/* 142 */       return (That)SeqLike.class.padTo(this, len, elem, bf);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenSeq that, Function2 p) {
/* 142 */       return SeqLike.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Buffer<A> sortWith(Function2 lt) {
/* 142 */       return (Buffer<A>)SeqLike.class.sortWith(this, lt);
/*     */     }
/*     */     
/*     */     public <B> Buffer<A> sortBy(Function1 f, Ordering ord) {
/* 142 */       return (Buffer<A>)SeqLike.class.sortBy(this, f, ord);
/*     */     }
/*     */     
/*     */     public <B> Buffer<A> sorted(Ordering ord) {
/* 142 */       return (Buffer<A>)SeqLike.class.sorted(this, ord);
/*     */     }
/*     */     
/*     */     public Seq<A> toSeq() {
/* 142 */       return SeqLike.class.toSeq(this);
/*     */     }
/*     */     
/*     */     public Range indices() {
/* 142 */       return SeqLike.class.indices(this);
/*     */     }
/*     */     
/*     */     public Object view() {
/* 142 */       return SeqLike.class.view(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, Buffer<A>> view(int from, int until) {
/* 142 */       return SeqLike.class.view(this, from, until);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(int idx) {
/* 142 */       return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 p) {
/* 142 */       return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/* 142 */       return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 142 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem, int from) {
/* 142 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOf(Object elem) {
/* 142 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOf(Object elem, int end) {
/* 142 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p) {
/* 142 */       return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that) {
/* 142 */       return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*     */     }
/*     */     
/*     */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 142 */       return PartialFunction.class.orElse((PartialFunction)this, that);
/*     */     }
/*     */     
/*     */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/* 142 */       return PartialFunction.class.andThen((PartialFunction)this, k);
/*     */     }
/*     */     
/*     */     public Function1<Object, Option<A>> lift() {
/* 142 */       return PartialFunction.class.lift((PartialFunction)this);
/*     */     }
/*     */     
/*     */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/* 142 */       return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*     */     }
/*     */     
/*     */     public <U> Function1<Object, Object> runWith(Function1 action) {
/* 142 */       return PartialFunction.class.runWith((PartialFunction)this, action);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/* 142 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/* 142 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/* 142 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/* 142 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/* 142 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/* 142 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/* 142 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/* 142 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/* 142 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/* 142 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/* 142 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/* 142 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/* 142 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/* 142 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/* 142 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/* 142 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/* 142 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/* 142 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/* 142 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/* 142 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/* 142 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/* 142 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/* 142 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/* 142 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, A> compose(Function1 g) {
/* 142 */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 142 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 142 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 142 */       IterableLike.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 142 */       return IterableLike.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 142 */       return IterableLike.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public Option<A> find(Function1 p) {
/* 142 */       return IterableLike.class.find(this, p);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 142 */       return (B)IterableLike.class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 142 */       return (B)IterableLike.class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public Iterable<A> toIterable() {
/* 142 */       return IterableLike.class.toIterable(this);
/*     */     }
/*     */     
/*     */     public Iterator<A> toIterator() {
/* 142 */       return IterableLike.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public A head() {
/* 142 */       return (A)IterableLike.class.head(this);
/*     */     }
/*     */     
/*     */     public Buffer<A> slice(int from, int until) {
/* 142 */       return (Buffer<A>)IterableLike.class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public Buffer<A> take(int n) {
/* 142 */       return (Buffer<A>)IterableLike.class.take(this, n);
/*     */     }
/*     */     
/*     */     public Buffer<A> drop(int n) {
/* 142 */       return (Buffer<A>)IterableLike.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public Buffer<A> takeWhile(Function1 p) {
/* 142 */       return (Buffer<A>)IterableLike.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Buffer<A>> grouped(int size) {
/* 142 */       return IterableLike.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public Iterator<Buffer<A>> sliding(int size) {
/* 142 */       return IterableLike.class.sliding(this, size);
/*     */     }
/*     */     
/*     */     public Iterator<Buffer<A>> sliding(int size, int step) {
/* 142 */       return IterableLike.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public Buffer<A> takeRight(int n) {
/* 142 */       return (Buffer<A>)IterableLike.class.takeRight(this, n);
/*     */     }
/*     */     
/*     */     public Buffer<A> dropRight(int n) {
/* 142 */       return (Buffer<A>)IterableLike.class.dropRight(this, n);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/* 142 */       IterableLike.class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 142 */       return (That)IterableLike.class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 142 */       return (That)IterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 142 */       return (That)IterableLike.class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public <B> boolean sameElements(GenIterable that) {
/* 142 */       return IterableLike.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Stream<A> toStream() {
/* 142 */       return IterableLike.class.toStream(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 142 */       return IterableLike.class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public Builder<A, Buffer<A>> newBuilder() {
/* 142 */       return GenericTraversableTemplate.class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Builder<B, Buffer<B>> genericBuilder() {
/* 142 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<Buffer<A1>, Buffer<A2>> unzip(Function1 asPair) {
/* 142 */       return GenericTraversableTemplate.class.unzip(this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<Buffer<A1>, Buffer<A2>, Buffer<A3>> unzip3(Function1 asTriple) {
/* 142 */       return GenericTraversableTemplate.class.unzip3(this, asTriple);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> flatten(Function1 asTraversable) {
/* 142 */       return (Buffer<B>)GenericTraversableTemplate.class.flatten(this, asTraversable);
/*     */     }
/*     */     
/*     */     public <B> Buffer<Buffer<B>> transpose(Function1 asTraversable) {
/* 142 */       return (Buffer<Buffer<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */     }
/*     */     
/*     */     public Buffer<A> repr() {
/* 142 */       return (Buffer<A>)TraversableLike.class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/* 142 */       return TraversableLike.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 142 */       return TraversableLike.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 142 */       return (That)TraversableLike.class.$plus$plus(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 142 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 142 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 142 */       return (That)TraversableLike.class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 142 */       return (That)TraversableLike.class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public Buffer<A> filter(Function1 p) {
/* 142 */       return (Buffer<A>)TraversableLike.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public Buffer<A> filterNot(Function1 p) {
/* 142 */       return (Buffer<A>)TraversableLike.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 142 */       return (That)TraversableLike.class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public Tuple2<Buffer<A>, Buffer<A>> partition(Function1 p) {
/* 142 */       return TraversableLike.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public <K> Map<K, Buffer<A>> groupBy(Function1 f) {
/* 142 */       return TraversableLike.class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 142 */       return (That)TraversableLike.class.scan(this, z, op, cbf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 142 */       return (That)TraversableLike.class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 142 */       return (That)TraversableLike.class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public Option<A> headOption() {
/* 142 */       return TraversableLike.class.headOption(this);
/*     */     }
/*     */     
/*     */     public Buffer<A> tail() {
/* 142 */       return (Buffer<A>)TraversableLike.class.tail(this);
/*     */     }
/*     */     
/*     */     public A last() {
/* 142 */       return (A)TraversableLike.class.last(this);
/*     */     }
/*     */     
/*     */     public Option<A> lastOption() {
/* 142 */       return TraversableLike.class.lastOption(this);
/*     */     }
/*     */     
/*     */     public Buffer<A> init() {
/* 142 */       return (Buffer<A>)TraversableLike.class.init(this);
/*     */     }
/*     */     
/*     */     public Buffer<A> sliceWithKnownDelta(int from, int until, int delta) {
/* 142 */       return (Buffer<A>)TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
/*     */     }
/*     */     
/*     */     public Buffer<A> sliceWithKnownBound(int from, int until) {
/* 142 */       return (Buffer<A>)TraversableLike.class.sliceWithKnownBound(this, from, until);
/*     */     }
/*     */     
/*     */     public Buffer<A> dropWhile(Function1 p) {
/* 142 */       return (Buffer<A>)TraversableLike.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Buffer<A>, Buffer<A>> span(Function1 p) {
/* 142 */       return TraversableLike.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Buffer<A>, Buffer<A>> splitAt(int n) {
/* 142 */       return TraversableLike.class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public Iterator<Buffer<A>> tails() {
/* 142 */       return TraversableLike.class.tails(this);
/*     */     }
/*     */     
/*     */     public Iterator<Buffer<A>> inits() {
/* 142 */       return TraversableLike.class.inits(this);
/*     */     }
/*     */     
/*     */     public Traversable<A> toTraversable() {
/* 142 */       return TraversableLike.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 142 */       return (Col)TraversableLike.class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public FilterMonadic<A, Buffer<A>> withFilter(Function1 p) {
/* 142 */       return TraversableLike.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public ParSeq<A> par() {
/* 142 */       return (ParSeq<A>)Parallelizable.class.par(this);
/*     */     }
/*     */     
/*     */     public List<A> reversed() {
/* 142 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 142 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 142 */       return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 142 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 142 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 142 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 142 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 142 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 142 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 142 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/* 142 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 142 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/* 142 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 142 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/* 142 */       return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/* 142 */       return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */     }
/*     */     
/*     */     public <B> A min(Ordering cmp) {
/* 142 */       return (A)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */     }
/*     */     
/*     */     public <B> A max(Ordering cmp) {
/* 142 */       return (A)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */     }
/*     */     
/*     */     public <B> A maxBy(Function1 f, Ordering cmp) {
/* 142 */       return (A)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> A minBy(Function1 f, Ordering cmp) {
/* 142 */       return (A)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 142 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 142 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 142 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 142 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<A> toList() {
/* 142 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<A> toIndexedSeq() {
/* 142 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 142 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 142 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<A> toVector() {
/* 142 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 142 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 142 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 142 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 142 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 142 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 142 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 142 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 142 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public BufferProxy$$anon$1(BufferProxy $outer) {
/* 142 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 142 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 142 */       Parallelizable.class.$init$(this);
/* 142 */       TraversableLike.class.$init$(this);
/* 142 */       GenericTraversableTemplate.class.$init$(this);
/* 142 */       GenTraversable.class.$init$((GenTraversable)this);
/* 142 */       Traversable.class.$init$(this);
/* 142 */       Traversable$class.$init$(this);
/* 142 */       GenIterable.class.$init$((GenIterable)this);
/* 142 */       IterableLike.class.$init$(this);
/* 142 */       Iterable.class.$init$(this);
/* 142 */       Iterable$class.$init$(this);
/* 142 */       Function1.class.$init$((Function1)this);
/* 142 */       PartialFunction.class.$init$((PartialFunction)this);
/* 142 */       GenSeqLike.class.$init$((GenSeqLike)this);
/* 142 */       GenSeq.class.$init$((GenSeq)this);
/* 142 */       SeqLike.class.$init$(this);
/* 142 */       Seq.class.$init$(this);
/* 142 */       Cloneable$class.$init$(this);
/* 142 */       SeqLike$class.$init$(this);
/* 142 */       Seq$class.$init$(this);
/* 142 */       Growable.class.$init$(this);
/* 142 */       Shrinkable.class.$init$(this);
/* 142 */       Subtractable.class.$init$(this);
/* 142 */       BufferLike$class.$init$(this);
/* 142 */       Buffer$class.$init$(this);
/* 142 */       Proxy.class.$init$(this);
/* 142 */       BufferProxy$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Buffer<A> self() {
/* 143 */       return this.$outer.self().clone();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\BufferProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */