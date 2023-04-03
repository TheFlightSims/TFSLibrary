/*     */ package scala.util.matching;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Regex$ implements Serializable {
/*     */   public static final Regex$ MODULE$;
/*     */   
/*     */   public class Regex$$anonfun$unapplySeq$1 extends AbstractFunction1<Object, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Matcher m$1;
/*     */     
/*     */     public final String apply(int x$1) {
/* 185 */       return this.m$1.group(x$1);
/*     */     }
/*     */     
/*     */     public Regex$$anonfun$unapplySeq$1(Regex $outer, Matcher m$1) {}
/*     */   }
/*     */   
/*     */   public class Regex$$anon$4 implements Iterator<Regex.Match> {
/*     */     private final Regex.MatchIterator matchIterator$1;
/*     */     
/*     */     public Iterator<Regex.Match> seq() {
/* 215 */       return Iterator.class.seq(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 215 */       return Iterator.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/* 215 */       return Iterator.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 215 */       return Iterator.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> take(int n) {
/* 215 */       return Iterator.class.take(this, n);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> drop(int n) {
/* 215 */       return Iterator.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> slice(int from, int until) {
/* 215 */       return Iterator.class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> map(Function1 f) {
/* 215 */       return Iterator.class.map(this, f);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/* 215 */       return Iterator.class.$plus$plus(this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/* 215 */       return Iterator.class.flatMap(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> filter(Function1 p) {
/* 215 */       return Iterator.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 215 */       return Iterator.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> withFilter(Function1 p) {
/* 215 */       return Iterator.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> filterNot(Function1 p) {
/* 215 */       return Iterator.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/* 215 */       return Iterator.class.collect(this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 215 */       return Iterator.class.scanLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 215 */       return Iterator.class.scanRight(this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> takeWhile(Function1 p) {
/* 215 */       return Iterator.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Regex.Match>, Iterator<Regex.Match>> partition(Function1 p) {
/* 215 */       return Iterator.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Regex.Match>, Iterator<Regex.Match>> span(Function1 p) {
/* 215 */       return Iterator.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> dropWhile(Function1 p) {
/* 215 */       return Iterator.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<Regex.Match, B>> zip(Iterator that) {
/* 215 */       return Iterator.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 215 */       return Iterator.class.padTo(this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<Regex.Match, Object>> zipWithIndex() {
/* 215 */       return Iterator.class.zipWithIndex(this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 215 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 215 */       Iterator.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 215 */       return Iterator.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 215 */       return Iterator.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 215 */       return Iterator.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public Option<Regex.Match> find(Function1 p) {
/* 215 */       return Iterator.class.find(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/* 215 */       return Iterator.class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 215 */       return Iterator.class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<Regex.Match> buffered() {
/* 215 */       return Iterator.class.buffered(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Regex.Match>.GroupedIterator<B> grouped(int size) {
/* 215 */       return Iterator.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Regex.Match>.GroupedIterator<B> sliding(int size, int step) {
/* 215 */       return Iterator.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/* 215 */       return Iterator.class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Regex.Match>, Iterator<Regex.Match>> duplicate() {
/* 215 */       return Iterator.class.duplicate(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 215 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/* 215 */       Iterator.class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/* 215 */       return Iterator.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Traversable<Regex.Match> toTraversable() {
/* 215 */       return Iterator.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> toIterator() {
/* 215 */       return Iterator.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<Regex.Match> toStream() {
/* 215 */       return Iterator.class.toStream(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 215 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/* 215 */       return Iterator.class.sliding$default$2(this);
/*     */     }
/*     */     
/*     */     public List<Regex.Match> reversed() {
/* 215 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 215 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 215 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 215 */       return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 215 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 215 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 215 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 215 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 215 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 215 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 215 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 215 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 215 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/* 215 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 215 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/* 215 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 215 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/* 215 */       return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/* 215 */       return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */     }
/*     */     
/*     */     public <B> Regex.Match min(Ordering cmp) {
/* 215 */       return (Regex.Match)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */     }
/*     */     
/*     */     public <B> Regex.Match max(Ordering cmp) {
/* 215 */       return (Regex.Match)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */     }
/*     */     
/*     */     public <B> Regex.Match maxBy(Function1 f, Ordering cmp) {
/* 215 */       return (Regex.Match)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> Regex.Match minBy(Function1 f, Ordering cmp) {
/* 215 */       return (Regex.Match)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 215 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 215 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 215 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 215 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<Regex.Match> toList() {
/* 215 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<Regex.Match> toIterable() {
/* 215 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<Regex.Match> toSeq() {
/* 215 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<Regex.Match> toIndexedSeq() {
/* 215 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 215 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 215 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<Regex.Match> toVector() {
/* 215 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 215 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/* 215 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 215 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 215 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 215 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 215 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 215 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 215 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 215 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public Regex$$anon$4(Regex $outer, Regex.MatchIterator matchIterator$1) {
/* 215 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 215 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 215 */       Iterator.class.$init$(this);
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 216 */       return this.matchIterator$1.hasNext();
/*     */     }
/*     */     
/*     */     public Regex.Match next() {
/* 218 */       this.matchIterator$1.next();
/* 219 */       return (new Regex.Match(this.matchIterator$1.source(), this.matchIterator$1.matcher(), this.matchIterator$1.groupNames())).force();
/*     */     }
/*     */   }
/*     */   
/*     */   public class Regex$$anonfun$replaceAllIn$1 extends AbstractFunction1<Regex.Match, Matcher> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 replacer$1;
/*     */     
/*     */     private final AbstractIterator it$1;
/*     */     
/*     */     public final Matcher apply(Regex.Match md) {
/* 321 */       return ((Regex.Replacement)this.it$1).replace((String)this.replacer$1.apply(md));
/*     */     }
/*     */     
/*     */     public Regex$$anonfun$replaceAllIn$1(Regex $outer, Function1 replacer$1, AbstractIterator it$1) {}
/*     */   }
/*     */   
/*     */   public class Regex$$anonfun$replaceSomeIn$1 extends AbstractFunction1<Regex.Match, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 replacer$2;
/*     */     
/*     */     public final AbstractIterator it$2;
/*     */     
/*     */     public final void apply(Regex.Match matchdata) {
/*     */       Option option;
/* 348 */       if (!(option = (Option)this.replacer$2.apply(matchdata)).isEmpty()) {
/* 348 */         String str = (String)option.get();
/* 348 */         ((Regex.Replacement)this.it$2).replace(str);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Regex$$anonfun$replaceSomeIn$1(Regex $outer, Function1 replacer$2, AbstractIterator it$2) {}
/*     */   }
/*     */   
/*     */   public class Regex$$anon$2 extends Regex implements UnanchoredRegex {
/*     */     public boolean runMatcher(Matcher m) {
/* 393 */       return UnanchoredRegex$class.runMatcher(this, m);
/*     */     }
/*     */     
/*     */     public UnanchoredRegex unanchored() {
/* 393 */       return UnanchoredRegex$class.unanchored(this);
/*     */     }
/*     */     
/*     */     public Regex anchored() {
/* 393 */       return this.$outer;
/*     */     }
/*     */     
/*     */     public Regex$$anon$2(Regex $outer) {
/* 393 */       super($outer.scala$util$matching$Regex$$regex, $outer.scala$util$matching$Regex$$groupNames);
/* 393 */       UnanchoredRegex$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   private Regex$() {
/* 416 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 416 */     return MODULE$;
/*     */   }
/*     */   
/*     */   public String quoteReplacement(String text) {
/* 646 */     return Matcher.quoteReplacement(text);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\matching\Regex$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */