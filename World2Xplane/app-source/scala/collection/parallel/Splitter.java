/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.BufferedIterator;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Iterator$;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Seq$;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.immutable.Stream;
/*    */ import scala.collection.immutable.Vector;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001U2q!\001\002\021\002G\005\021B\001\005Ta2LG\017^3s\025\t\031A!\001\005qCJ\fG\016\\3m\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQQcE\002\001\027=\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\r\001\022cE\007\002\t%\021!\003\002\002\t\023R,'/\031;peB\021A#\006\007\001\t\0311\002\001\"b\001/\t\tA+\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\b\"B\020\001\r\003\001\023!B:qY&$X#A\021\021\007A\021C%\003\002$\t\t\0311+Z9\021\007\025\0021#D\001\003\017\0259#\001#\001)\003!\031\006\017\\5ui\026\024\bCA\023*\r\025\t!\001#\001+'\tI3\002C\003-S\021\005Q&\001\004=S:LGO\020\013\002Q!)q&\013C\001a\005)Q-\0349usV\021\021\007N\013\002eA\031Q\005A\032\021\005Q!D!\002\f/\005\0049\002")
/*    */ public interface Splitter<T> extends Iterator<T> {
/*    */   Seq<Splitter<T>> split();
/*    */   
/*    */   public static class Splitter$$anon$1 implements Splitter<T> {
/*    */     public Iterator<T> seq() {
/* 53 */       return Iterator.class.seq(this);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 53 */       return Iterator.class.isEmpty(this);
/*    */     }
/*    */     
/*    */     public boolean isTraversableAgain() {
/* 53 */       return Iterator.class.isTraversableAgain(this);
/*    */     }
/*    */     
/*    */     public boolean hasDefiniteSize() {
/* 53 */       return Iterator.class.hasDefiniteSize(this);
/*    */     }
/*    */     
/*    */     public Iterator<T> take(int n) {
/* 53 */       return Iterator.class.take(this, n);
/*    */     }
/*    */     
/*    */     public Iterator<T> drop(int n) {
/* 53 */       return Iterator.class.drop(this, n);
/*    */     }
/*    */     
/*    */     public Iterator<T> slice(int from, int until) {
/* 53 */       return Iterator.class.slice(this, from, until);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> map(Function1 f) {
/* 53 */       return Iterator.class.map(this, f);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> $plus$plus(Function0 that) {
/* 53 */       return Iterator.class.$plus$plus(this, that);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> flatMap(Function1 f) {
/* 53 */       return Iterator.class.flatMap(this, f);
/*    */     }
/*    */     
/*    */     public Iterator<T> filter(Function1 p) {
/* 53 */       return Iterator.class.filter(this, p);
/*    */     }
/*    */     
/*    */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 53 */       return Iterator.class.corresponds(this, that, p);
/*    */     }
/*    */     
/*    */     public Iterator<T> withFilter(Function1 p) {
/* 53 */       return Iterator.class.withFilter(this, p);
/*    */     }
/*    */     
/*    */     public Iterator<T> filterNot(Function1 p) {
/* 53 */       return Iterator.class.filterNot(this, p);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> collect(PartialFunction pf) {
/* 53 */       return Iterator.class.collect(this, pf);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 53 */       return Iterator.class.scanLeft(this, z, op);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 53 */       return Iterator.class.scanRight(this, z, op);
/*    */     }
/*    */     
/*    */     public Iterator<T> takeWhile(Function1 p) {
/* 53 */       return Iterator.class.takeWhile(this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Iterator<T>, Iterator<T>> partition(Function1 p) {
/* 53 */       return Iterator.class.partition(this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Iterator<T>, Iterator<T>> span(Function1 p) {
/* 53 */       return Iterator.class.span(this, p);
/*    */     }
/*    */     
/*    */     public Iterator<T> dropWhile(Function1 p) {
/* 53 */       return Iterator.class.dropWhile(this, p);
/*    */     }
/*    */     
/*    */     public <B> Iterator<Tuple2<T, B>> zip(Iterator that) {
/* 53 */       return Iterator.class.zip(this, that);
/*    */     }
/*    */     
/*    */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 53 */       return Iterator.class.padTo(this, len, elem);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<T, Object>> zipWithIndex() {
/* 53 */       return Iterator.class.zipWithIndex(this);
/*    */     }
/*    */     
/*    */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 53 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 53 */       Iterator.class.foreach(this, f);
/*    */     }
/*    */     
/*    */     public boolean forall(Function1 p) {
/* 53 */       return Iterator.class.forall(this, p);
/*    */     }
/*    */     
/*    */     public boolean exists(Function1 p) {
/* 53 */       return Iterator.class.exists(this, p);
/*    */     }
/*    */     
/*    */     public boolean contains(Object elem) {
/* 53 */       return Iterator.class.contains(this, elem);
/*    */     }
/*    */     
/*    */     public Option<T> find(Function1 p) {
/* 53 */       return Iterator.class.find(this, p);
/*    */     }
/*    */     
/*    */     public int indexWhere(Function1 p) {
/* 53 */       return Iterator.class.indexWhere(this, p);
/*    */     }
/*    */     
/*    */     public <B> int indexOf(Object elem) {
/* 53 */       return Iterator.class.indexOf(this, elem);
/*    */     }
/*    */     
/*    */     public BufferedIterator<T> buffered() {
/* 53 */       return Iterator.class.buffered(this);
/*    */     }
/*    */     
/*    */     public <B> Iterator<T>.GroupedIterator<B> grouped(int size) {
/* 53 */       return Iterator.class.grouped(this, size);
/*    */     }
/*    */     
/*    */     public <B> Iterator<T>.GroupedIterator<B> sliding(int size, int step) {
/* 53 */       return Iterator.class.sliding(this, size, step);
/*    */     }
/*    */     
/*    */     public int length() {
/* 53 */       return Iterator.class.length(this);
/*    */     }
/*    */     
/*    */     public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
/* 53 */       return Iterator.class.duplicate(this);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 53 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start, int len) {
/* 53 */       Iterator.class.copyToArray(this, xs, start, len);
/*    */     }
/*    */     
/*    */     public boolean sameElements(Iterator that) {
/* 53 */       return Iterator.class.sameElements(this, that);
/*    */     }
/*    */     
/*    */     public Traversable<T> toTraversable() {
/* 53 */       return Iterator.class.toTraversable(this);
/*    */     }
/*    */     
/*    */     public Iterator<T> toIterator() {
/* 53 */       return Iterator.class.toIterator(this);
/*    */     }
/*    */     
/*    */     public Stream<T> toStream() {
/* 53 */       return Iterator.class.toStream(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 53 */       return Iterator.class.toString(this);
/*    */     }
/*    */     
/*    */     public <B> int sliding$default$2() {
/* 53 */       return Iterator.class.sliding$default$2(this);
/*    */     }
/*    */     
/*    */     public List<T> reversed() {
/* 53 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public int size() {
/* 53 */       return TraversableOnce.class.size((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public boolean nonEmpty() {
/* 53 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/* 53 */       return TraversableOnce.class.count((TraversableOnce)this, p);
/*    */     }
/*    */     
/*    */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 53 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*    */     }
/*    */     
/*    */     public <B> B $div$colon(Object z, Function2 op) {
/* 53 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 53 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldLeft(Object z, Function2 op) {
/* 53 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldRight(Object z, Function2 op) {
/* 53 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceLeft(Function2 op) {
/* 53 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceRight(Function2 op) {
/* 53 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 53 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 53 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 reduce(Function2 op) {
/* 53 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 53 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 fold(Object z, Function2 op) {
/* 53 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 53 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*    */     }
/*    */     
/*    */     public <B> B sum(Numeric num) {
/* 53 */       return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*    */     }
/*    */     
/*    */     public <B> B product(Numeric num) {
/* 53 */       return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*    */     }
/*    */     
/*    */     public <B> T min(Ordering cmp) {
/* 53 */       return (T)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> T max(Ordering cmp) {
/* 53 */       return (T)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> T maxBy(Function1 f, Ordering cmp) {
/* 53 */       return (T)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> T minBy(Function1 f, Ordering cmp) {
/* 53 */       return (T)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> void copyToBuffer(Buffer dest) {
/* 53 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start) {
/* 53 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs) {
/* 53 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*    */     }
/*    */     
/*    */     public <B> Object toArray(ClassTag evidence$1) {
/* 53 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*    */     }
/*    */     
/*    */     public List<T> toList() {
/* 53 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public Iterable<T> toIterable() {
/* 53 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public Seq<T> toSeq() {
/* 53 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<T> toIndexedSeq() {
/* 53 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Buffer<B> toBuffer() {
/* 53 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Set<B> toSet() {
/* 53 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public Vector<T> toVector() {
/* 53 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <Col> Col to(CanBuildFrom cbf) {
/* 53 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*    */     }
/*    */     
/*    */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 53 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*    */     }
/*    */     
/*    */     public String mkString(String start, String sep, String end) {
/* 53 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*    */     }
/*    */     
/*    */     public String mkString(String sep) {
/* 53 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*    */     }
/*    */     
/*    */     public String mkString() {
/* 53 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 53 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String sep) {
/* 53 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b) {
/* 53 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*    */     }
/*    */     
/*    */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 53 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public Splitter$$anon$1() {
/* 53 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 53 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 53 */       Iterator.class.$init$(this);
/*    */     }
/*    */     
/*    */     public boolean hasNext() {
/* 54 */       return false;
/*    */     }
/*    */     
/*    */     public Nothing$ next() {
/* 55 */       return (Nothing$)Iterator$.MODULE$.empty().next();
/*    */     }
/*    */     
/*    */     public Seq<Object> split() {
/* 56 */       (new Splitter[1])[0] = this;
/* 56 */       return (Seq<Object>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Splitter[1]));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\Splitter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */