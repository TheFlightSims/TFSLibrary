/*      */ package scala.collection;
/*      */ 
/*      */ import scala.Function0;
/*      */ import scala.Function1;
/*      */ import scala.Function2;
/*      */ import scala.Option;
/*      */ import scala.PartialFunction;
/*      */ import scala.Predef$;
/*      */ import scala.Tuple2;
/*      */ import scala.collection.generic.CanBuildFrom;
/*      */ import scala.collection.immutable.IndexedSeq;
/*      */ import scala.collection.immutable.List;
/*      */ import scala.collection.immutable.Map;
/*      */ import scala.collection.immutable.Set;
/*      */ import scala.collection.immutable.Stream;
/*      */ import scala.collection.immutable.Vector;
/*      */ import scala.collection.mutable.Buffer;
/*      */ import scala.collection.mutable.StringBuilder;
/*      */ import scala.math.Numeric;
/*      */ import scala.math.Ordering;
/*      */ import scala.reflect.ClassTag;
/*      */ import scala.reflect.ScalaSignature;
/*      */ 
/*      */ @ScalaSignature(bytes = "\006\001\0012a!\001\002\002\002\0211!\001E!cgR\024\030m\031;Ji\026\024\030\r^8s\025\t\031A!\001\006d_2dWm\031;j_:T\021!B\001\006g\016\fG.Y\013\003\017I\0312\001\001\005\r!\tI!\"D\001\005\023\tYAA\001\004B]f\024VM\032\t\004\0339\001R\"\001\002\n\005=\021!\001C%uKJ\fGo\034:\021\005E\021B\002\001\003\007'\001!)\031A\013\003\003\005\033\001!\005\002\0273A\021\021bF\005\0031\021\021qAT8uQ&tw\r\005\002\n5%\0211\004\002\002\004\003:L\b\"B\017\001\t\003q\022A\002\037j]&$h\bF\001 !\ri\001\001\005")
/*      */ public abstract class AbstractIterator<A> implements Iterator<A> {
/*      */   public Iterator<A> seq() {
/* 1157 */     return Iterator$class.seq(this);
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/* 1157 */     return Iterator$class.isEmpty(this);
/*      */   }
/*      */   
/*      */   public boolean isTraversableAgain() {
/* 1157 */     return Iterator$class.isTraversableAgain(this);
/*      */   }
/*      */   
/*      */   public boolean hasDefiniteSize() {
/* 1157 */     return Iterator$class.hasDefiniteSize(this);
/*      */   }
/*      */   
/*      */   public Iterator<A> take(int n) {
/* 1157 */     return Iterator$class.take(this, n);
/*      */   }
/*      */   
/*      */   public Iterator<A> drop(int n) {
/* 1157 */     return Iterator$class.drop(this, n);
/*      */   }
/*      */   
/*      */   public Iterator<A> slice(int from, int until) {
/* 1157 */     return Iterator$class.slice(this, from, until);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> map(Function1 f) {
/* 1157 */     return Iterator$class.map(this, f);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> $plus$plus(Function0 that) {
/* 1157 */     return Iterator$class.$plus$plus(this, that);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> flatMap(Function1 f) {
/* 1157 */     return Iterator$class.flatMap(this, f);
/*      */   }
/*      */   
/*      */   public Iterator<A> filter(Function1 p) {
/* 1157 */     return Iterator$class.filter(this, p);
/*      */   }
/*      */   
/*      */   public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 1157 */     return Iterator$class.corresponds(this, that, p);
/*      */   }
/*      */   
/*      */   public Iterator<A> withFilter(Function1 p) {
/* 1157 */     return Iterator$class.withFilter(this, p);
/*      */   }
/*      */   
/*      */   public Iterator<A> filterNot(Function1 p) {
/* 1157 */     return Iterator$class.filterNot(this, p);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> collect(PartialFunction pf) {
/* 1157 */     return Iterator$class.collect(this, pf);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 1157 */     return Iterator$class.scanLeft(this, z, op);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 1157 */     return Iterator$class.scanRight(this, z, op);
/*      */   }
/*      */   
/*      */   public Iterator<A> takeWhile(Function1 p) {
/* 1157 */     return Iterator$class.takeWhile(this, p);
/*      */   }
/*      */   
/*      */   public Tuple2<Iterator<A>, Iterator<A>> partition(Function1 p) {
/* 1157 */     return Iterator$class.partition(this, p);
/*      */   }
/*      */   
/*      */   public Tuple2<Iterator<A>, Iterator<A>> span(Function1 p) {
/* 1157 */     return Iterator$class.span(this, p);
/*      */   }
/*      */   
/*      */   public Iterator<A> dropWhile(Function1 p) {
/* 1157 */     return Iterator$class.dropWhile(this, p);
/*      */   }
/*      */   
/*      */   public <B> Iterator<Tuple2<A, B>> zip(Iterator that) {
/* 1157 */     return Iterator$class.zip(this, that);
/*      */   }
/*      */   
/*      */   public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 1157 */     return Iterator$class.padTo(this, len, elem);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<A, Object>> zipWithIndex() {
/* 1157 */     return Iterator$class.zipWithIndex(this);
/*      */   }
/*      */   
/*      */   public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 1157 */     return Iterator$class.zipAll(this, that, thisElem, thatElem);
/*      */   }
/*      */   
/*      */   public <U> void foreach(Function1 f) {
/* 1157 */     Iterator$class.foreach(this, f);
/*      */   }
/*      */   
/*      */   public boolean forall(Function1 p) {
/* 1157 */     return Iterator$class.forall(this, p);
/*      */   }
/*      */   
/*      */   public boolean exists(Function1 p) {
/* 1157 */     return Iterator$class.exists(this, p);
/*      */   }
/*      */   
/*      */   public boolean contains(Object elem) {
/* 1157 */     return Iterator$class.contains(this, elem);
/*      */   }
/*      */   
/*      */   public Option<A> find(Function1 p) {
/* 1157 */     return Iterator$class.find(this, p);
/*      */   }
/*      */   
/*      */   public int indexWhere(Function1 p) {
/* 1157 */     return Iterator$class.indexWhere(this, p);
/*      */   }
/*      */   
/*      */   public <B> int indexOf(Object elem) {
/* 1157 */     return Iterator$class.indexOf(this, elem);
/*      */   }
/*      */   
/*      */   public BufferedIterator<A> buffered() {
/* 1157 */     return Iterator$class.buffered(this);
/*      */   }
/*      */   
/*      */   public <B> Iterator<A>.GroupedIterator<B> grouped(int size) {
/* 1157 */     return Iterator$class.grouped(this, size);
/*      */   }
/*      */   
/*      */   public <B> Iterator<A>.GroupedIterator<B> sliding(int size, int step) {
/* 1157 */     return Iterator$class.sliding(this, size, step);
/*      */   }
/*      */   
/*      */   public int length() {
/* 1157 */     return Iterator$class.length(this);
/*      */   }
/*      */   
/*      */   public Tuple2<Iterator<A>, Iterator<A>> duplicate() {
/* 1157 */     return Iterator$class.duplicate(this);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 1157 */     return Iterator$class.patch(this, from, patchElems, replaced);
/*      */   }
/*      */   
/*      */   public <B> void copyToArray(Object xs, int start, int len) {
/* 1157 */     Iterator$class.copyToArray(this, xs, start, len);
/*      */   }
/*      */   
/*      */   public boolean sameElements(Iterator that) {
/* 1157 */     return Iterator$class.sameElements(this, that);
/*      */   }
/*      */   
/*      */   public Traversable<A> toTraversable() {
/* 1157 */     return Iterator$class.toTraversable(this);
/*      */   }
/*      */   
/*      */   public Iterator<A> toIterator() {
/* 1157 */     return Iterator$class.toIterator(this);
/*      */   }
/*      */   
/*      */   public Stream<A> toStream() {
/* 1157 */     return Iterator$class.toStream(this);
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1157 */     return Iterator$class.toString(this);
/*      */   }
/*      */   
/*      */   public <B> int sliding$default$2() {
/* 1157 */     return Iterator$class.sliding$default$2(this);
/*      */   }
/*      */   
/*      */   public List<A> reversed() {
/* 1157 */     return TraversableOnce$class.reversed(this);
/*      */   }
/*      */   
/*      */   public int size() {
/* 1157 */     return TraversableOnce$class.size(this);
/*      */   }
/*      */   
/*      */   public boolean nonEmpty() {
/* 1157 */     return TraversableOnce$class.nonEmpty(this);
/*      */   }
/*      */   
/*      */   public int count(Function1 p) {
/* 1157 */     return TraversableOnce$class.count(this, p);
/*      */   }
/*      */   
/*      */   public <B> Option<B> collectFirst(PartialFunction pf) {
/* 1157 */     return TraversableOnce$class.collectFirst(this, pf);
/*      */   }
/*      */   
/*      */   public <B> B $div$colon(Object z, Function2 op) {
/* 1157 */     return (B)TraversableOnce$class.$div$colon(this, z, op);
/*      */   }
/*      */   
/*      */   public <B> B $colon$bslash(Object z, Function2 op) {
/* 1157 */     return (B)TraversableOnce$class.$colon$bslash(this, z, op);
/*      */   }
/*      */   
/*      */   public <B> B foldLeft(Object z, Function2 op) {
/* 1157 */     return (B)TraversableOnce$class.foldLeft(this, z, op);
/*      */   }
/*      */   
/*      */   public <B> B foldRight(Object z, Function2 op) {
/* 1157 */     return (B)TraversableOnce$class.foldRight(this, z, op);
/*      */   }
/*      */   
/*      */   public <B> B reduceLeft(Function2 op) {
/* 1157 */     return (B)TraversableOnce$class.reduceLeft(this, op);
/*      */   }
/*      */   
/*      */   public <B> B reduceRight(Function2 op) {
/* 1157 */     return (B)TraversableOnce$class.reduceRight(this, op);
/*      */   }
/*      */   
/*      */   public <B> Option<B> reduceLeftOption(Function2 op) {
/* 1157 */     return TraversableOnce$class.reduceLeftOption(this, op);
/*      */   }
/*      */   
/*      */   public <B> Option<B> reduceRightOption(Function2 op) {
/* 1157 */     return TraversableOnce$class.reduceRightOption(this, op);
/*      */   }
/*      */   
/*      */   public <A1> A1 reduce(Function2 op) {
/* 1157 */     return (A1)TraversableOnce$class.reduce(this, op);
/*      */   }
/*      */   
/*      */   public <A1> Option<A1> reduceOption(Function2 op) {
/* 1157 */     return TraversableOnce$class.reduceOption(this, op);
/*      */   }
/*      */   
/*      */   public <A1> A1 fold(Object z, Function2 op) {
/* 1157 */     return (A1)TraversableOnce$class.fold(this, z, op);
/*      */   }
/*      */   
/*      */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 1157 */     return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
/*      */   }
/*      */   
/*      */   public <B> B sum(Numeric num) {
/* 1157 */     return (B)TraversableOnce$class.sum(this, num);
/*      */   }
/*      */   
/*      */   public <B> B product(Numeric num) {
/* 1157 */     return (B)TraversableOnce$class.product(this, num);
/*      */   }
/*      */   
/*      */   public <B> A min(Ordering cmp) {
/* 1157 */     return (A)TraversableOnce$class.min(this, cmp);
/*      */   }
/*      */   
/*      */   public <B> A max(Ordering cmp) {
/* 1157 */     return (A)TraversableOnce$class.max(this, cmp);
/*      */   }
/*      */   
/*      */   public <B> A maxBy(Function1 f, Ordering cmp) {
/* 1157 */     return (A)TraversableOnce$class.maxBy(this, f, cmp);
/*      */   }
/*      */   
/*      */   public <B> A minBy(Function1 f, Ordering cmp) {
/* 1157 */     return (A)TraversableOnce$class.minBy(this, f, cmp);
/*      */   }
/*      */   
/*      */   public <B> void copyToBuffer(Buffer dest) {
/* 1157 */     TraversableOnce$class.copyToBuffer(this, dest);
/*      */   }
/*      */   
/*      */   public <B> void copyToArray(Object xs, int start) {
/* 1157 */     TraversableOnce$class.copyToArray(this, xs, start);
/*      */   }
/*      */   
/*      */   public <B> void copyToArray(Object xs) {
/* 1157 */     TraversableOnce$class.copyToArray(this, xs);
/*      */   }
/*      */   
/*      */   public <B> Object toArray(ClassTag evidence$1) {
/* 1157 */     return TraversableOnce$class.toArray(this, evidence$1);
/*      */   }
/*      */   
/*      */   public List<A> toList() {
/* 1157 */     return TraversableOnce$class.toList(this);
/*      */   }
/*      */   
/*      */   public Iterable<A> toIterable() {
/* 1157 */     return TraversableOnce$class.toIterable(this);
/*      */   }
/*      */   
/*      */   public Seq<A> toSeq() {
/* 1157 */     return TraversableOnce$class.toSeq(this);
/*      */   }
/*      */   
/*      */   public IndexedSeq<A> toIndexedSeq() {
/* 1157 */     return TraversableOnce$class.toIndexedSeq(this);
/*      */   }
/*      */   
/*      */   public <B> Buffer<B> toBuffer() {
/* 1157 */     return TraversableOnce$class.toBuffer(this);
/*      */   }
/*      */   
/*      */   public <B> Set<B> toSet() {
/* 1157 */     return TraversableOnce$class.toSet(this);
/*      */   }
/*      */   
/*      */   public Vector<A> toVector() {
/* 1157 */     return TraversableOnce$class.toVector(this);
/*      */   }
/*      */   
/*      */   public <Col> Col to(CanBuildFrom cbf) {
/* 1157 */     return (Col)TraversableOnce$class.to(this, cbf);
/*      */   }
/*      */   
/*      */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 1157 */     return TraversableOnce$class.toMap(this, ev);
/*      */   }
/*      */   
/*      */   public String mkString(String start, String sep, String end) {
/* 1157 */     return TraversableOnce$class.mkString(this, start, sep, end);
/*      */   }
/*      */   
/*      */   public String mkString(String sep) {
/* 1157 */     return TraversableOnce$class.mkString(this, sep);
/*      */   }
/*      */   
/*      */   public String mkString() {
/* 1157 */     return TraversableOnce$class.mkString(this);
/*      */   }
/*      */   
/*      */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 1157 */     return TraversableOnce$class.addString(this, b, start, sep, end);
/*      */   }
/*      */   
/*      */   public StringBuilder addString(StringBuilder b, String sep) {
/* 1157 */     return TraversableOnce$class.addString(this, b, sep);
/*      */   }
/*      */   
/*      */   public StringBuilder addString(StringBuilder b) {
/* 1157 */     return TraversableOnce$class.addString(this, b);
/*      */   }
/*      */   
/*      */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 1157 */     return (A1)GenTraversableOnce$class.$div$colon$bslash(this, z, op);
/*      */   }
/*      */   
/*      */   public AbstractIterator() {
/* 1157 */     GenTraversableOnce$class.$init$(this);
/* 1157 */     TraversableOnce$class.$init$(this);
/* 1157 */     Iterator$class.$init$(this);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\AbstractIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */