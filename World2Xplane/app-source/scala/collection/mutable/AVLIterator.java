/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
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
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t3A!\001\002\005\023\tY\021I\026'Ji\026\024\030\r^8s\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005))2c\001\001\f\037A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\007A\t2#D\001\005\023\t\021BA\001\005Ji\026\024\030\r^8s!\t!R\003\004\001\005\013Y\001!\031A\f\003\003\005\013\"\001G\016\021\0051I\022B\001\016\007\005\035qu\016\0365j]\036\004\"\001\004\017\n\005u1!aA!os\"Aq\004\001B\001B\003%\001%\001\003s_>$\bcA\021#'5\t!!\003\002$\005\t!aj\0343f\021\025)\003\001\"\001'\003\031a\024N\\5u}Q\021q\005\013\t\004C\001\031\002\"B\020%\001\004\001\003b\002\026\001\005\004%\taK\001\006gR\f7m[\013\002YA\031\021%\f\021\n\0059\022!AC!se\006L8\013^1dW\"1\001\007\001Q\001\n1\naa\035;bG.\004\003\"\002\032\001\t\023\031\024\001\0033jm\026dUM\032;\025\003Q\002\"\001D\033\n\005Y2!\001B+oSRDQ\001\017\001\005\nM\n1\"\0328hC\036,'+[4ii\")!\b\001C!w\0059\001.Y:OKb$X#\001\037\021\0051i\024B\001 \007\005\035\021un\0347fC:DQ\001\021\001\005B\005\013AA\\3yiR\t1\003")
/*     */ public class AVLIterator<A> implements Iterator<A> {
/*     */   private final ArrayStack<Node<A>> stack;
/*     */   
/*     */   public Iterator<A> seq() {
/* 208 */     return Iterator.class.seq(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 208 */     return Iterator.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean isTraversableAgain() {
/* 208 */     return Iterator.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/* 208 */     return Iterator.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> take(int n) {
/* 208 */     return Iterator.class.take(this, n);
/*     */   }
/*     */   
/*     */   public Iterator<A> drop(int n) {
/* 208 */     return Iterator.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public Iterator<A> slice(int from, int until) {
/* 208 */     return Iterator.class.slice(this, from, until);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> map(Function1 f) {
/* 208 */     return Iterator.class.map(this, f);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> $plus$plus(Function0 that) {
/* 208 */     return Iterator.class.$plus$plus(this, that);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> flatMap(Function1 f) {
/* 208 */     return Iterator.class.flatMap(this, f);
/*     */   }
/*     */   
/*     */   public Iterator<A> filter(Function1 p) {
/* 208 */     return Iterator.class.filter(this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 208 */     return Iterator.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public Iterator<A> withFilter(Function1 p) {
/* 208 */     return Iterator.class.withFilter(this, p);
/*     */   }
/*     */   
/*     */   public Iterator<A> filterNot(Function1 p) {
/* 208 */     return Iterator.class.filterNot(this, p);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> collect(PartialFunction pf) {
/* 208 */     return Iterator.class.collect(this, pf);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 208 */     return Iterator.class.scanLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 208 */     return Iterator.class.scanRight(this, z, op);
/*     */   }
/*     */   
/*     */   public Iterator<A> takeWhile(Function1 p) {
/* 208 */     return Iterator.class.takeWhile(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<Iterator<A>, Iterator<A>> partition(Function1 p) {
/* 208 */     return Iterator.class.partition(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<Iterator<A>, Iterator<A>> span(Function1 p) {
/* 208 */     return Iterator.class.span(this, p);
/*     */   }
/*     */   
/*     */   public Iterator<A> dropWhile(Function1 p) {
/* 208 */     return Iterator.class.dropWhile(this, p);
/*     */   }
/*     */   
/*     */   public <B> Iterator<Tuple2<A, B>> zip(Iterator that) {
/* 208 */     return Iterator.class.zip(this, that);
/*     */   }
/*     */   
/*     */   public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 208 */     return Iterator.class.padTo(this, len, elem);
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<A, Object>> zipWithIndex() {
/* 208 */     return Iterator.class.zipWithIndex(this);
/*     */   }
/*     */   
/*     */   public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 208 */     return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/* 208 */     Iterator.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/* 208 */     return Iterator.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/* 208 */     return Iterator.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/* 208 */     return Iterator.class.contains(this, elem);
/*     */   }
/*     */   
/*     */   public Option<A> find(Function1 p) {
/* 208 */     return Iterator.class.find(this, p);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p) {
/* 208 */     return Iterator.class.indexWhere(this, p);
/*     */   }
/*     */   
/*     */   public <B> int indexOf(Object elem) {
/* 208 */     return Iterator.class.indexOf(this, elem);
/*     */   }
/*     */   
/*     */   public BufferedIterator<A> buffered() {
/* 208 */     return Iterator.class.buffered(this);
/*     */   }
/*     */   
/*     */   public <B> Iterator<A>.GroupedIterator<B> grouped(int size) {
/* 208 */     return Iterator.class.grouped(this, size);
/*     */   }
/*     */   
/*     */   public <B> Iterator<A>.GroupedIterator<B> sliding(int size, int step) {
/* 208 */     return Iterator.class.sliding(this, size, step);
/*     */   }
/*     */   
/*     */   public int length() {
/* 208 */     return Iterator.class.length(this);
/*     */   }
/*     */   
/*     */   public Tuple2<Iterator<A>, Iterator<A>> duplicate() {
/* 208 */     return Iterator.class.duplicate(this);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 208 */     return Iterator.class.patch(this, from, patchElems, replaced);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/* 208 */     Iterator.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public boolean sameElements(Iterator that) {
/* 208 */     return Iterator.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public Traversable<A> toTraversable() {
/* 208 */     return Iterator.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> toIterator() {
/* 208 */     return Iterator.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public Stream<A> toStream() {
/* 208 */     return Iterator.class.toStream(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 208 */     return Iterator.class.toString(this);
/*     */   }
/*     */   
/*     */   public <B> int sliding$default$2() {
/* 208 */     return Iterator.class.sliding$default$2(this);
/*     */   }
/*     */   
/*     */   public List<A> reversed() {
/* 208 */     return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public int size() {
/* 208 */     return TraversableOnce.class.size((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/* 208 */     return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/* 208 */     return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */   }
/*     */   
/*     */   public <B> Option<B> collectFirst(PartialFunction pf) {
/* 208 */     return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */   }
/*     */   
/*     */   public <B> B $div$colon(Object z, Function2 op) {
/* 208 */     return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B $colon$bslash(Object z, Function2 op) {
/* 208 */     return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/* 208 */     return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/* 208 */     return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/* 208 */     return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/* 208 */     return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceLeftOption(Function2 op) {
/* 208 */     return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceRightOption(Function2 op) {
/* 208 */     return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 reduce(Function2 op) {
/* 208 */     return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> Option<A1> reduceOption(Function2 op) {
/* 208 */     return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 fold(Object z, Function2 op) {
/* 208 */     return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 208 */     return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <B> B sum(Numeric num) {
/* 208 */     return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> B product(Numeric num) {
/* 208 */     return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> A min(Ordering cmp) {
/* 208 */     return (A)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> A max(Ordering cmp) {
/* 208 */     return (A)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> A maxBy(Function1 f, Ordering cmp) {
/* 208 */     return (A)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> A minBy(Function1 f, Ordering cmp) {
/* 208 */     return (A)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> void copyToBuffer(Buffer dest) {
/* 208 */     TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start) {
/* 208 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs) {
/* 208 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */   }
/*     */   
/*     */   public <B> Object toArray(ClassTag evidence$1) {
/* 208 */     return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/* 208 */     return TraversableOnce.class.toList((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Iterable<A> toIterable() {
/* 208 */     return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Seq<A> toSeq() {
/* 208 */     return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> toIndexedSeq() {
/* 208 */     return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <B> Buffer<B> toBuffer() {
/* 208 */     return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/* 208 */     return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Vector<A> toVector() {
/* 208 */     return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/* 208 */     return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */   }
/*     */   
/*     */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 208 */     return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/* 208 */     return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/* 208 */     return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/* 208 */     return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 208 */     return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String sep) {
/* 208 */     return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b) {
/* 208 */     return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 208 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public AVLIterator(Node root) {
/* 208 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 208 */     TraversableOnce.class.$init$((TraversableOnce)this);
/* 208 */     Iterator.class.$init$(this);
/* 209 */     (new Node[1])[0] = root;
/* 209 */     this.stack = ArrayStack$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Node[1]), ClassTag$.MODULE$.apply(Node.class));
/* 210 */     diveLeft();
/*     */   }
/*     */   
/*     */   public ArrayStack<Node<A>> stack() {
/*     */     return this.stack;
/*     */   }
/*     */   
/*     */   private void diveLeft() {
/*     */     while (true) {
/* 213 */       AVLTree aVLTree = ((Node)stack().head()).left();
/* 213 */       if (Leaf$.MODULE$ == null) {
/* 213 */         if (aVLTree == null)
/*     */           return; 
/* 213 */       } else if (Leaf$.MODULE$.equals(aVLTree)) {
/*     */         return;
/*     */       } 
/* 214 */       Node<A> left = (Node)((Node)stack().head()).left();
/* 215 */       stack().push(left);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void engageRight() {
/* 221 */     AVLTree aVLTree = ((Node)stack().head()).right();
/* 221 */     if (Leaf$.MODULE$ == null) {
/* 221 */       if (aVLTree != null) {
/* 222 */         Node<A> right = (Node)((Node)stack().head()).right();
/* 223 */         stack().pop();
/* 224 */         stack().push(right);
/* 225 */         diveLeft();
/*     */       } 
/*     */     } else {
/*     */       if (Leaf$.MODULE$.equals(aVLTree)) {
/* 227 */         stack().pop();
/*     */         return;
/*     */       } 
/*     */       Node<A> node = (Node)((Node)stack().head()).right();
/*     */       stack().pop();
/*     */       stack().push(node);
/*     */       diveLeft();
/*     */     } 
/* 227 */     stack().pop();
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 230 */     return !stack().isEmpty();
/*     */   }
/*     */   
/*     */   public A next() {
/* 233 */     if (stack().isEmpty())
/* 234 */       throw new NoSuchElementException(); 
/* 236 */     Object result = ((Node)stack().head()).data();
/* 238 */     engageRight();
/* 239 */     return (A)result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\AVLIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */