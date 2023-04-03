/*      */ package scala.collection.concurrent;
/*      */ 
/*      */ import scala.Array$;
/*      */ import scala.Function0;
/*      */ import scala.Function1;
/*      */ import scala.Function2;
/*      */ import scala.MatchError;
/*      */ import scala.Option;
/*      */ import scala.PartialFunction;
/*      */ import scala.Predef$;
/*      */ import scala.Tuple2;
/*      */ import scala.collection.BufferedIterator;
/*      */ import scala.collection.GenIterable;
/*      */ import scala.collection.GenMap;
/*      */ import scala.collection.GenSeq;
/*      */ import scala.collection.GenSet;
/*      */ import scala.collection.GenTraversable;
/*      */ import scala.collection.GenTraversableOnce;
/*      */ import scala.collection.Iterable;
/*      */ import scala.collection.Iterator;
/*      */ import scala.collection.Iterator$;
/*      */ import scala.collection.Seq;
/*      */ import scala.collection.Seq$;
/*      */ import scala.collection.Traversable;
/*      */ import scala.collection.TraversableOnce;
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
/*      */ import scala.runtime.BoxesRunTime;
/*      */ 
/*      */ @ScalaSignature(bytes = "\006\001\0055e!B\001\003\001\021A!a\004+sS\026l\025\r]%uKJ\fGo\034:\013\005\r!\021AC2p]\016,(O]3oi*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\026\007%9\"eE\002\001\0259\001\"a\003\007\016\003\031I!!\004\004\003\r\005s\027PU3g!\ry\001CE\007\002\t%\021\021\003\002\002\t\023R,'/\031;peB!1bE\013\"\023\t!bA\001\004UkBdWM\r\t\003-]a\001\001B\003\031\001\t\007!DA\001L\007\001\t\"a\007\020\021\005-a\022BA\017\007\005\035qu\016\0365j]\036\004\"aC\020\n\005\0012!aA!osB\021aC\t\003\006G\001\021\rA\007\002\002-\"AQ\005\001BA\002\023\005a%A\003mKZ,G.F\001(!\tY\001&\003\002*\r\t\031\021J\034;\t\021-\002!\0211A\005\0021\n\021\002\\3wK2|F%Z9\025\0055\002\004CA\006/\023\tycA\001\003V]&$\bbB\031+\003\003\005\raJ\001\004q\022\n\004\002C\032\001\005\003\005\013\025B\024\002\r1,g/\0327!\021!)\004A!a\001\n\0231\024AA2u+\0059\004\003\002\035:+\005j\021AA\005\003u\t\021q\001\026:jK6\013\007\017\003\005=\001\t\005\r\021\"\003>\003\031\031Go\030\023fcR\021QF\020\005\bcm\n\t\0211\0018\021!\001\005A!A!B\0239\024aA2uA!A!\t\001B\001B\003%1)\001\005nkN$\030J\\5u!\tYA)\003\002F\r\t9!i\\8mK\006t\007\"B$\001\t\003A\025A\002\037j]&$h\b\006\003J\025.c\005\003\002\035\001+\005BQ!\n$A\002\035BQ!\016$A\002]BqA\021$\021\002\003\0071\tC\004O\001\001\007I\021B(\002\013M$\030mY6\026\003A\0032aC)T\023\t\021fAA\003BeJ\f\027\020E\002\f#R\003\"\001O+\n\005Y\023!!\003\"bg&\034gj\0343f\021\035A\006\0011A\005\ne\013\021b\035;bG.|F%Z9\025\0055R\006bB\031X\003\003\005\r\001\025\005\0079\002\001\013\025\002)\002\rM$\030mY6!\021\035q\006\0011A\005\n}\013\001b\035;bG.\004xn]\013\002AB\0311\"U\024\t\017\t\004\001\031!C\005G\006a1\017^1dWB|7o\030\023fcR\021Q\006\032\005\bc\005\f\t\0211\001a\021\0311\007\001)Q\005A\006I1\017^1dWB|7\017\t\005\bQ\002\001\r\021\"\003'\003\025!W\r\035;i\021\035Q\007\0011A\005\n-\f\021\002Z3qi\"|F%Z9\025\0055b\007bB\031j\003\003\005\ra\n\005\007]\002\001\013\025B\024\002\r\021,\007\017\0365!\021\035\001\b\0011A\005\nE\fqa];cSR,'/F\001\017\021\035\031\b\0011A\005\nQ\f1b];cSR,'o\030\023fcR\021Q&\036\005\bcI\f\t\0211\001\017\021\0319\b\001)Q\005\035\005A1/\0362ji\026\024\b\005C\004z\001\001\007I\021\002>\002\017\r,(O]3oiV\t1\020\005\0039yV\t\023BA?\003\005\031YeKT8eK\"Aq\020\001a\001\n\023\t\t!A\006dkJ\024XM\034;`I\025\fHcA\027\002\004!9\021G`A\001\002\004Y\bbBA\004\001\001\006Ka_\001\tGV\024(/\0328uA!9\0211\002\001\005\002\0055\021a\0025bg:+\007\020^\013\002\007\"9\021\021\003\001\005\002\005M\021\001\0028fqR$\022A\005\005\b\003/\001A\021BA\r\003\031\021X-\0313j]R\031Q&a\007\t\021\005u\021Q\003a\001\003?\t!!\0338\021\013a\n\t#F\021\n\007\005\r\"AA\003J\035>$W\rC\004\002(\001!I!!\013\002\031\rDWmY6Tk\nLG/\032:\025\0035Bq!!\f\001\t\023\tI#\001\006j]&$\030.\0317ju\026Dq!!\r\001\t\003\tI#A\004bIZ\fgnY3\t\017\005U\002\001\"\005\0028\005Ya.Z<Ji\026\024\030\r^8s)\035I\025\021HA\037\003\003Bq!a\017\0024\001\007q%\001\003`Y\0264\bbBA \003g\001\raN\001\004?\016$\bbBA\"\003g\001\raQ\001\n?6,8\017^%oSRDq!a\022\001\t#\tI%A\003ekB$v\016F\002.\003\027Bq!!\024\002F\001\007\021*\001\002ji\"9\021\021\013\001\005\022\005M\023!C:vE\022Lg/\0333f)\t\t)\006\005\003\020\003/r\021bAA-\t\t\0311+Z9\t\017\005u\003\001\"\001\002*\005Q\001O]5oi\022+'-^4\b\025\005\005$!!A\t\002\021\t\031'A\bUe&,W*\0319Ji\026\024\030\r^8s!\rA\024Q\r\004\n\003\t\t\t\021#\001\005\003O\0322!!\032\013\021\0359\025Q\rC\001\003W\"\"!a\031\t\025\005=\024QMI\001\n\003\t\t(A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$HeM\013\007\003g\nI)a#\026\005\005U$fA\"\002x-\022\021\021\020\t\005\003w\n))\004\002\002~)!\021qPAA\003%)hn\0315fG.,GMC\002\002\004\032\t!\"\0318o_R\fG/[8o\023\021\t9)! \003#Ut7\r[3dW\026$g+\031:jC:\034W\r\002\004\031\003[\022\rA\007\003\007G\0055$\031\001\016")
/*      */ public class TrieMapIterator<K, V> implements Iterator<Tuple2<K, V>> {
/*      */   private int level;
/*      */   
/*      */   private TrieMap<K, V> ct;
/*      */   
/*      */   private BasicNode[][] stack;
/*      */   
/*      */   private int[] stackpos;
/*      */   
/*      */   private int depth;
/*      */   
/*      */   private Iterator<Tuple2<K, V>> subiter;
/*      */   
/*      */   private KVNode<K, V> current;
/*      */   
/*      */   public Iterator<Tuple2<K, V>> seq() {
/*  922 */     return Iterator.class.seq(this);
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  922 */     return Iterator.class.isEmpty(this);
/*      */   }
/*      */   
/*      */   public boolean isTraversableAgain() {
/*  922 */     return Iterator.class.isTraversableAgain(this);
/*      */   }
/*      */   
/*      */   public boolean hasDefiniteSize() {
/*  922 */     return Iterator.class.hasDefiniteSize(this);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<K, V>> take(int n) {
/*  922 */     return Iterator.class.take(this, n);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<K, V>> drop(int n) {
/*  922 */     return Iterator.class.drop(this, n);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<K, V>> slice(int from, int until) {
/*  922 */     return Iterator.class.slice(this, from, until);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> map(Function1 f) {
/*  922 */     return Iterator.class.map(this, f);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> $plus$plus(Function0 that) {
/*  922 */     return Iterator.class.$plus$plus(this, that);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> flatMap(Function1 f) {
/*  922 */     return Iterator.class.flatMap(this, f);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<K, V>> filter(Function1 p) {
/*  922 */     return Iterator.class.filter(this, p);
/*      */   }
/*      */   
/*      */   public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*  922 */     return Iterator.class.corresponds(this, that, p);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<K, V>> withFilter(Function1 p) {
/*  922 */     return Iterator.class.withFilter(this, p);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<K, V>> filterNot(Function1 p) {
/*  922 */     return Iterator.class.filterNot(this, p);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> collect(PartialFunction pf) {
/*  922 */     return Iterator.class.collect(this, pf);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*  922 */     return Iterator.class.scanLeft(this, z, op);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*  922 */     return Iterator.class.scanRight(this, z, op);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<K, V>> takeWhile(Function1 p) {
/*  922 */     return Iterator.class.takeWhile(this, p);
/*      */   }
/*      */   
/*      */   public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> partition(Function1 p) {
/*  922 */     return Iterator.class.partition(this, p);
/*      */   }
/*      */   
/*      */   public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> span(Function1 p) {
/*  922 */     return Iterator.class.span(this, p);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<K, V>> dropWhile(Function1 p) {
/*  922 */     return Iterator.class.dropWhile(this, p);
/*      */   }
/*      */   
/*      */   public <B> Iterator<Tuple2<Tuple2<K, V>, B>> zip(Iterator that) {
/*  922 */     return Iterator.class.zip(this, that);
/*      */   }
/*      */   
/*      */   public <A1> Iterator<A1> padTo(int len, Object elem) {
/*  922 */     return Iterator.class.padTo(this, len, elem);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<Tuple2<K, V>, Object>> zipWithIndex() {
/*  922 */     return Iterator.class.zipWithIndex(this);
/*      */   }
/*      */   
/*      */   public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*  922 */     return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*      */   }
/*      */   
/*      */   public <U> void foreach(Function1 f) {
/*  922 */     Iterator.class.foreach(this, f);
/*      */   }
/*      */   
/*      */   public boolean forall(Function1 p) {
/*  922 */     return Iterator.class.forall(this, p);
/*      */   }
/*      */   
/*      */   public boolean exists(Function1 p) {
/*  922 */     return Iterator.class.exists(this, p);
/*      */   }
/*      */   
/*      */   public boolean contains(Object elem) {
/*  922 */     return Iterator.class.contains(this, elem);
/*      */   }
/*      */   
/*      */   public Option<Tuple2<K, V>> find(Function1 p) {
/*  922 */     return Iterator.class.find(this, p);
/*      */   }
/*      */   
/*      */   public int indexWhere(Function1 p) {
/*  922 */     return Iterator.class.indexWhere(this, p);
/*      */   }
/*      */   
/*      */   public <B> int indexOf(Object elem) {
/*  922 */     return Iterator.class.indexOf(this, elem);
/*      */   }
/*      */   
/*      */   public BufferedIterator<Tuple2<K, V>> buffered() {
/*  922 */     return Iterator.class.buffered(this);
/*      */   }
/*      */   
/*      */   public <B> Iterator<Tuple2<K, V>>.GroupedIterator<B> grouped(int size) {
/*  922 */     return Iterator.class.grouped(this, size);
/*      */   }
/*      */   
/*      */   public <B> Iterator<Tuple2<K, V>>.GroupedIterator<B> sliding(int size, int step) {
/*  922 */     return Iterator.class.sliding(this, size, step);
/*      */   }
/*      */   
/*      */   public int length() {
/*  922 */     return Iterator.class.length(this);
/*      */   }
/*      */   
/*      */   public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> duplicate() {
/*  922 */     return Iterator.class.duplicate(this);
/*      */   }
/*      */   
/*      */   public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*  922 */     return Iterator.class.patch(this, from, patchElems, replaced);
/*      */   }
/*      */   
/*      */   public <B> void copyToArray(Object xs, int start, int len) {
/*  922 */     Iterator.class.copyToArray(this, xs, start, len);
/*      */   }
/*      */   
/*      */   public boolean sameElements(Iterator that) {
/*  922 */     return Iterator.class.sameElements(this, that);
/*      */   }
/*      */   
/*      */   public Traversable<Tuple2<K, V>> toTraversable() {
/*  922 */     return Iterator.class.toTraversable(this);
/*      */   }
/*      */   
/*      */   public Iterator<Tuple2<K, V>> toIterator() {
/*  922 */     return Iterator.class.toIterator(this);
/*      */   }
/*      */   
/*      */   public Stream<Tuple2<K, V>> toStream() {
/*  922 */     return Iterator.class.toStream(this);
/*      */   }
/*      */   
/*      */   public String toString() {
/*  922 */     return Iterator.class.toString(this);
/*      */   }
/*      */   
/*      */   public <B> int sliding$default$2() {
/*  922 */     return Iterator.class.sliding$default$2(this);
/*      */   }
/*      */   
/*      */   public List<Tuple2<K, V>> reversed() {
/*  922 */     return TraversableOnce.class.reversed((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public int size() {
/*  922 */     return TraversableOnce.class.size((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public boolean nonEmpty() {
/*  922 */     return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public int count(Function1 p) {
/*  922 */     return TraversableOnce.class.count((TraversableOnce)this, p);
/*      */   }
/*      */   
/*      */   public <B> Option<B> collectFirst(PartialFunction pf) {
/*  922 */     return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*      */   }
/*      */   
/*      */   public <B> B $div$colon(Object z, Function2 op) {
/*  922 */     return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*      */   }
/*      */   
/*      */   public <B> B $colon$bslash(Object z, Function2 op) {
/*  922 */     return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*      */   }
/*      */   
/*      */   public <B> B foldLeft(Object z, Function2 op) {
/*  922 */     return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*      */   }
/*      */   
/*      */   public <B> B foldRight(Object z, Function2 op) {
/*  922 */     return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*      */   }
/*      */   
/*      */   public <B> B reduceLeft(Function2 op) {
/*  922 */     return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*      */   }
/*      */   
/*      */   public <B> B reduceRight(Function2 op) {
/*  922 */     return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*      */   }
/*      */   
/*      */   public <B> Option<B> reduceLeftOption(Function2 op) {
/*  922 */     return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*      */   }
/*      */   
/*      */   public <B> Option<B> reduceRightOption(Function2 op) {
/*  922 */     return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*      */   }
/*      */   
/*      */   public <A1> A1 reduce(Function2 op) {
/*  922 */     return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*      */   }
/*      */   
/*      */   public <A1> Option<A1> reduceOption(Function2 op) {
/*  922 */     return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*      */   }
/*      */   
/*      */   public <A1> A1 fold(Object z, Function2 op) {
/*  922 */     return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*      */   }
/*      */   
/*      */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  922 */     return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*      */   }
/*      */   
/*      */   public <B> B sum(Numeric num) {
/*  922 */     return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*      */   }
/*      */   
/*      */   public <B> B product(Numeric num) {
/*  922 */     return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*      */   }
/*      */   
/*      */   public <B> Tuple2<K, V> min(Ordering cmp) {
/*  922 */     return (Tuple2<K, V>)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*      */   }
/*      */   
/*      */   public <B> Tuple2<K, V> max(Ordering cmp) {
/*  922 */     return (Tuple2<K, V>)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*      */   }
/*      */   
/*      */   public <B> Tuple2<K, V> maxBy(Function1 f, Ordering cmp) {
/*  922 */     return (Tuple2<K, V>)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*      */   }
/*      */   
/*      */   public <B> Tuple2<K, V> minBy(Function1 f, Ordering cmp) {
/*  922 */     return (Tuple2<K, V>)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*      */   }
/*      */   
/*      */   public <B> void copyToBuffer(Buffer dest) {
/*  922 */     TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*      */   }
/*      */   
/*      */   public <B> void copyToArray(Object xs, int start) {
/*  922 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*      */   }
/*      */   
/*      */   public <B> void copyToArray(Object xs) {
/*  922 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*      */   }
/*      */   
/*      */   public <B> Object toArray(ClassTag evidence$1) {
/*  922 */     return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*      */   }
/*      */   
/*      */   public List<Tuple2<K, V>> toList() {
/*  922 */     return TraversableOnce.class.toList((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public Iterable<Tuple2<K, V>> toIterable() {
/*  922 */     return TraversableOnce.class.toIterable((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public Seq<Tuple2<K, V>> toSeq() {
/*  922 */     return TraversableOnce.class.toSeq((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
/*  922 */     return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public <B> Buffer<B> toBuffer() {
/*  922 */     return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public <B> Set<B> toSet() {
/*  922 */     return TraversableOnce.class.toSet((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public Vector<Tuple2<K, V>> toVector() {
/*  922 */     return TraversableOnce.class.toVector((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public <Col> Col to(CanBuildFrom cbf) {
/*  922 */     return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*      */   }
/*      */   
/*      */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  922 */     return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*      */   }
/*      */   
/*      */   public String mkString(String start, String sep, String end) {
/*  922 */     return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*      */   }
/*      */   
/*      */   public String mkString(String sep) {
/*  922 */     return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*      */   }
/*      */   
/*      */   public String mkString() {
/*  922 */     return TraversableOnce.class.mkString((TraversableOnce)this);
/*      */   }
/*      */   
/*      */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  922 */     return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*      */   }
/*      */   
/*      */   public StringBuilder addString(StringBuilder b, String sep) {
/*  922 */     return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*      */   }
/*      */   
/*      */   public StringBuilder addString(StringBuilder b) {
/*  922 */     return TraversableOnce.class.addString((TraversableOnce)this, b);
/*      */   }
/*      */   
/*      */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  922 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*      */   }
/*      */   
/*      */   public int level() {
/*  922 */     return this.level;
/*      */   }
/*      */   
/*      */   public void level_$eq(int x$1) {
/*  922 */     this.level = x$1;
/*      */   }
/*      */   
/*      */   private TrieMap<K, V> ct() {
/*  922 */     return this.ct;
/*      */   }
/*      */   
/*      */   private void ct_$eq(TrieMap<K, V> x$1) {
/*  922 */     this.ct = x$1;
/*      */   }
/*      */   
/*      */   public TrieMapIterator(int level, TrieMap<K, V> ct, boolean mustInit) {
/*  922 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  922 */     TraversableOnce.class.$init$((TraversableOnce)this);
/*  922 */     Iterator.class.$init$(this);
/*  923 */     this.stack = new BasicNode[7][];
/*  924 */     this.stackpos = new int[7];
/*  925 */     this.depth = -1;
/*  926 */     this.subiter = null;
/*  927 */     this.current = null;
/*  929 */     if (mustInit)
/*  929 */       initialize(); 
/*      */   }
/*      */   
/*      */   private BasicNode[][] stack() {
/*      */     return this.stack;
/*      */   }
/*      */   
/*      */   private void stack_$eq(BasicNode[][] x$1) {
/*      */     this.stack = x$1;
/*      */   }
/*      */   
/*      */   private int[] stackpos() {
/*      */     return this.stackpos;
/*      */   }
/*      */   
/*      */   private void stackpos_$eq(int[] x$1) {
/*      */     this.stackpos = x$1;
/*      */   }
/*      */   
/*      */   private int depth() {
/*      */     return this.depth;
/*      */   }
/*      */   
/*      */   private void depth_$eq(int x$1) {
/*      */     this.depth = x$1;
/*      */   }
/*      */   
/*      */   private Iterator<Tuple2<K, V>> subiter() {
/*      */     return this.subiter;
/*      */   }
/*      */   
/*      */   private void subiter_$eq(Iterator<Tuple2<K, V>> x$1) {
/*      */     this.subiter = x$1;
/*      */   }
/*      */   
/*      */   private KVNode<K, V> current() {
/*      */     return this.current;
/*      */   }
/*      */   
/*      */   private void current_$eq(KVNode<K, V> x$1) {
/*      */     this.current = x$1;
/*      */   }
/*      */   
/*      */   public boolean hasNext() {
/*  931 */     return (current() != null || subiter() != null);
/*      */   }
/*      */   
/*      */   public Tuple2<K, V> next() {
/*      */     Tuple2<K, V> tuple2;
/*  934 */     if (subiter() != null) {
/*  936 */       tuple2 = (Tuple2)subiter().next();
/*  937 */       checkSubiter();
/*      */     } else {
/*  939 */       tuple2 = current().kvPair();
/*  940 */       advance();
/*      */     } 
/*  942 */     return hasNext() ? tuple2 : 
/*  943 */       (Tuple2<K, V>)Iterator$.MODULE$.empty().next();
/*      */   }
/*      */   
/*      */   private void readin(INode<K, V> in) {
/*  945 */     MainNode<K, V> mainNode = in.gcasRead(ct());
/*  946 */     if (mainNode instanceof CNode) {
/*  946 */       CNode cNode = (CNode)mainNode;
/*  947 */       depth_$eq(depth() + 1);
/*  948 */       stack()[depth()] = cNode.array();
/*  949 */       stackpos()[depth()] = -1;
/*  950 */       advance();
/*  951 */     } else if (mainNode instanceof TNode) {
/*  951 */       TNode<K, V> tNode = (TNode)mainNode;
/*  952 */       current_$eq(tNode);
/*  953 */     } else if (mainNode instanceof LNode) {
/*  953 */       LNode<K, V> lNode = (LNode)mainNode;
/*  954 */       subiter_$eq(lNode.listmap().iterator());
/*  955 */       checkSubiter();
/*      */     } else {
/*  956 */       if (mainNode == null) {
/*  957 */         current_$eq(null);
/*      */         return;
/*      */       } 
/*      */       throw new MatchError(mainNode);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void checkSubiter() {
/*  960 */     if (!subiter().hasNext()) {
/*  961 */       subiter_$eq(null);
/*  962 */       advance();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void initialize() {
/*  966 */     Predef$.MODULE$.assert(ct().isReadOnly());
/*  968 */     TrieMap<K, V> qual$1 = ct();
/*  968 */     boolean x$6 = qual$1.RDCSS_READ_ROOT$default$1();
/*  968 */     INode<K, V> r = qual$1.RDCSS_READ_ROOT(x$6);
/*  969 */     readin(r);
/*      */   }
/*      */   
/*      */   public void advance() {
/*  972 */     if (depth() >= 0) {
/*  973 */       int npos = stackpos()[depth()] + 1;
/*  974 */       if (npos < (stack()[depth()]).length) {
/*  975 */         stackpos()[depth()] = npos;
/*  976 */         BasicNode basicNode = stack()[depth()][npos];
/*  977 */         if (basicNode instanceof SNode) {
/*  977 */           SNode<K, V> sNode = (SNode)basicNode;
/*  978 */           current_$eq(sNode);
/*  979 */         } else if (basicNode instanceof INode) {
/*  979 */           INode<K, V> iNode = (INode)basicNode;
/*  980 */           readin(iNode);
/*      */         } else {
/*      */           throw new MatchError(basicNode);
/*      */         } 
/*      */       } else {
/*  983 */         depth_$eq(depth() - 1);
/*  984 */         advance();
/*      */       } 
/*      */     } else {
/*  986 */       current_$eq(null);
/*      */     } 
/*      */   }
/*      */   
/*      */   public TrieMapIterator<K, V> newIterator(int _lev, TrieMap<K, V> _ct, boolean _mustInit) {
/*  988 */     return new TrieMapIterator(_lev, _ct, _mustInit);
/*      */   }
/*      */   
/*      */   public void dupTo(TrieMapIterator<K, V> it) {
/*  991 */     it.level_$eq(level());
/*  992 */     it.ct_$eq(ct());
/*  993 */     it.depth_$eq(depth());
/*  994 */     it.current_$eq(current());
/*  997 */     Array$.MODULE$.copy(stack(), 0, it.stack(), 0, 7);
/*  998 */     Array$.MODULE$.copy(stackpos(), 0, it.stackpos(), 0, 7);
/* 1001 */     if (subiter() == null) {
/* 1001 */       it.subiter_$eq(null);
/*      */     } else {
/* 1003 */       List lst = subiter().toList();
/* 1004 */       subiter_$eq(lst.iterator());
/* 1005 */       it.subiter_$eq(lst.iterator());
/*      */     } 
/*      */   }
/*      */   
/*      */   public Seq<Iterator<Tuple2<K, V>>> subdivide() {
/* 1014 */     TrieMapIterator<K, V> it = newIterator(level() + 1, ct(), false);
/* 1015 */     it.depth_$eq(-1);
/* 1016 */     it.subiter_$eq(subiter());
/* 1017 */     it.current_$eq(null);
/* 1018 */     subiter_$eq(null);
/* 1019 */     advance();
/* 1020 */     level_$eq(level() + 1);
/* 1021 */     (new TrieMapIterator[2])[0] = it;
/* 1021 */     (new TrieMapIterator[2])[1] = this;
/* 1023 */     level_$eq(level() + 1);
/* 1024 */     (new TrieMapIterator[1])[0] = this;
/* 1026 */     int d = 0;
/* 1027 */     while (d <= depth()) {
/* 1028 */       int rem = (stack()[d]).length - 1 - stackpos()[d];
/* 1029 */       if (rem > 0) {
/* 1030 */         Tuple2 tuple2 = Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])stack()[d]).drop(stackpos()[d] + 1)).splitAt(rem / 2);
/* 1030 */         if (tuple2 != null) {
/* 1030 */           Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 1030 */           BasicNode[] arr1 = (BasicNode[])tuple21._1(), arr2 = (BasicNode[])tuple21._2();
/* 1031 */           stack()[d] = arr1;
/* 1032 */           stackpos()[d] = -1;
/* 1033 */           TrieMapIterator<K, V> trieMapIterator = newIterator(level() + 1, ct(), false);
/* 1034 */           trieMapIterator.stack()[0] = arr2;
/* 1035 */           trieMapIterator.stackpos()[0] = -1;
/* 1036 */           trieMapIterator.depth_$eq(0);
/* 1037 */           trieMapIterator.advance();
/* 1038 */           level_$eq(level() + 1);
/* 1039 */           (new TrieMapIterator[2])[0] = this;
/* 1039 */           (new TrieMapIterator[2])[1] = trieMapIterator;
/* 1039 */           return (Seq<Iterator<Tuple2<K, V>>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new TrieMapIterator[2]));
/*      */         } 
/*      */         throw new MatchError(tuple2);
/*      */       } 
/* 1041 */       d++;
/*      */     } 
/* 1043 */     level_$eq(level() + 1);
/* 1044 */     (new TrieMapIterator[1])[0] = this;
/* 1044 */     return (subiter() != null) ? (Seq<Iterator<Tuple2<K, V>>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new TrieMapIterator[2])) : ((depth() == -1) ? (Seq<Iterator<Tuple2<K, V>>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new TrieMapIterator[1])) : (Seq<Iterator<Tuple2<K, V>>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new TrieMapIterator[1])));
/*      */   }
/*      */   
/*      */   public void printDebug() {
/* 1048 */     Predef$.MODULE$.println("ctrie iterator");
/* 1049 */     Predef$.MODULE$.println(Predef$.MODULE$.intArrayOps(stackpos()).mkString(","));
/* 1050 */     Predef$.MODULE$.println((new StringBuilder()).append("depth: ").append(BoxesRunTime.boxToInteger(depth())).toString());
/* 1051 */     Predef$.MODULE$.println((new StringBuilder()).append("curr.: ").append(current()).toString());
/* 1052 */     Predef$.MODULE$.println(Predef$.MODULE$.refArrayOps((Object[])stack()).mkString("\n"));
/*      */   }
/*      */   
/*      */   public static <K, V> boolean $lessinit$greater$default$3() {
/*      */     return TrieMapIterator$.MODULE$.$lessinit$greater$default$3();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\TrieMapIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */