/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.immutable.Stream;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ 
/*    */ public abstract class TraversableProxyLike$class {
/*    */   public static void $init$(TraversableProxyLike $this) {}
/*    */   
/*    */   public static void foreach(TraversableProxyLike $this, Function1 f) {
/* 29 */     $this.self().foreach(f);
/*    */   }
/*    */   
/*    */   public static boolean isEmpty(TraversableProxyLike $this) {
/* 30 */     return $this.self().isEmpty();
/*    */   }
/*    */   
/*    */   public static boolean nonEmpty(TraversableProxyLike $this) {
/* 31 */     return $this.self().nonEmpty();
/*    */   }
/*    */   
/*    */   public static int size(TraversableProxyLike $this) {
/* 32 */     return $this.self().size();
/*    */   }
/*    */   
/*    */   public static boolean hasDefiniteSize(TraversableProxyLike $this) {
/* 33 */     return $this.self().hasDefiniteSize();
/*    */   }
/*    */   
/*    */   public static Object $plus$plus(TraversableProxyLike $this, GenTraversableOnce xs, CanBuildFrom bf) {
/* 34 */     return $this.self().$plus$plus(xs, bf);
/*    */   }
/*    */   
/*    */   public static Object map(TraversableProxyLike $this, Function1 f, CanBuildFrom bf) {
/* 35 */     return $this.self().map(f, bf);
/*    */   }
/*    */   
/*    */   public static Object flatMap(TraversableProxyLike $this, Function1 f, CanBuildFrom bf) {
/* 36 */     return $this.self().flatMap(f, bf);
/*    */   }
/*    */   
/*    */   public static Traversable filter(TraversableProxyLike $this, Function1 p) {
/* 37 */     return $this.self().filter(p);
/*    */   }
/*    */   
/*    */   public static Traversable filterNot(TraversableProxyLike $this, Function1 p) {
/* 38 */     return $this.self().filterNot(p);
/*    */   }
/*    */   
/*    */   public static Object collect(TraversableProxyLike $this, PartialFunction pf, CanBuildFrom bf) {
/* 39 */     return $this.self().collect(pf, bf);
/*    */   }
/*    */   
/*    */   public static Tuple2 partition(TraversableProxyLike $this, Function1 p) {
/* 40 */     return $this.self().partition(p);
/*    */   }
/*    */   
/*    */   public static Map groupBy(TraversableProxyLike $this, Function1 f) {
/* 41 */     return $this.self().groupBy(f);
/*    */   }
/*    */   
/*    */   public static boolean forall(TraversableProxyLike $this, Function1 p) {
/* 42 */     return $this.self().forall(p);
/*    */   }
/*    */   
/*    */   public static boolean exists(TraversableProxyLike $this, Function1 p) {
/* 43 */     return $this.self().exists(p);
/*    */   }
/*    */   
/*    */   public static int count(TraversableProxyLike $this, Function1 p) {
/* 44 */     return $this.self().count(p);
/*    */   }
/*    */   
/*    */   public static Option find(TraversableProxyLike $this, Function1 p) {
/* 45 */     return $this.self().find(p);
/*    */   }
/*    */   
/*    */   public static Object foldLeft(TraversableProxyLike $this, Object z, Function2 op) {
/* 46 */     return $this.self().foldLeft(z, op);
/*    */   }
/*    */   
/*    */   public static Object $div$colon(TraversableProxyLike $this, Object z, Function2 op) {
/* 47 */     return $this.self().$div$colon(z, op);
/*    */   }
/*    */   
/*    */   public static Object foldRight(TraversableProxyLike $this, Object z, Function2 op) {
/* 48 */     return $this.self().foldRight(z, op);
/*    */   }
/*    */   
/*    */   public static Object $colon$bslash(TraversableProxyLike $this, Object z, Function2 op) {
/* 49 */     return $this.self().$colon$bslash(z, op);
/*    */   }
/*    */   
/*    */   public static Object reduceLeft(TraversableProxyLike $this, Function2 op) {
/* 50 */     return $this.self().reduceLeft(op);
/*    */   }
/*    */   
/*    */   public static Option reduceLeftOption(TraversableProxyLike $this, Function2 op) {
/* 51 */     return $this.self().reduceLeftOption(op);
/*    */   }
/*    */   
/*    */   public static Object reduceRight(TraversableProxyLike $this, Function2 op) {
/* 52 */     return $this.self().reduceRight(op);
/*    */   }
/*    */   
/*    */   public static Option reduceRightOption(TraversableProxyLike $this, Function2 op) {
/* 53 */     return $this.self().reduceRightOption(op);
/*    */   }
/*    */   
/*    */   public static Object scanLeft(TraversableProxyLike $this, Object z, Function2 op, CanBuildFrom bf) {
/* 54 */     return $this.self().scanLeft(z, op, bf);
/*    */   }
/*    */   
/*    */   public static Object scanRight(TraversableProxyLike $this, Object z, Function2 op, CanBuildFrom bf) {
/* 55 */     return $this.self().scanRight(z, op, bf);
/*    */   }
/*    */   
/*    */   public static Object sum(TraversableProxyLike $this, Numeric num) {
/* 56 */     return $this.self().sum(num);
/*    */   }
/*    */   
/*    */   public static Object product(TraversableProxyLike $this, Numeric num) {
/* 57 */     return $this.self().product(num);
/*    */   }
/*    */   
/*    */   public static Object min(TraversableProxyLike $this, Ordering cmp) {
/* 58 */     return $this.self().min(cmp);
/*    */   }
/*    */   
/*    */   public static Object max(TraversableProxyLike $this, Ordering cmp) {
/* 59 */     return $this.self().max(cmp);
/*    */   }
/*    */   
/*    */   public static Object head(TraversableProxyLike $this) {
/* 60 */     return $this.self().head();
/*    */   }
/*    */   
/*    */   public static Option headOption(TraversableProxyLike $this) {
/* 61 */     return $this.self().headOption();
/*    */   }
/*    */   
/*    */   public static Traversable tail(TraversableProxyLike $this) {
/* 62 */     return $this.self().tail();
/*    */   }
/*    */   
/*    */   public static Object last(TraversableProxyLike $this) {
/* 63 */     return $this.self().last();
/*    */   }
/*    */   
/*    */   public static Option lastOption(TraversableProxyLike $this) {
/* 64 */     return $this.self().lastOption();
/*    */   }
/*    */   
/*    */   public static Traversable init(TraversableProxyLike $this) {
/* 65 */     return $this.self().init();
/*    */   }
/*    */   
/*    */   public static Traversable take(TraversableProxyLike $this, int n) {
/* 66 */     return $this.self().take(n);
/*    */   }
/*    */   
/*    */   public static Traversable drop(TraversableProxyLike $this, int n) {
/* 67 */     return $this.self().drop(n);
/*    */   }
/*    */   
/*    */   public static Traversable slice(TraversableProxyLike $this, int from, int until) {
/* 68 */     return $this.self().slice(from, until);
/*    */   }
/*    */   
/*    */   public static Traversable takeWhile(TraversableProxyLike $this, Function1 p) {
/* 69 */     return $this.self().takeWhile(p);
/*    */   }
/*    */   
/*    */   public static Traversable dropWhile(TraversableProxyLike $this, Function1 p) {
/* 70 */     return $this.self().dropWhile(p);
/*    */   }
/*    */   
/*    */   public static Tuple2 span(TraversableProxyLike $this, Function1 p) {
/* 71 */     return $this.self().span(p);
/*    */   }
/*    */   
/*    */   public static Tuple2 splitAt(TraversableProxyLike $this, int n) {
/* 72 */     return $this.self().splitAt(n);
/*    */   }
/*    */   
/*    */   public static void copyToBuffer(TraversableProxyLike $this, Buffer dest) {
/* 73 */     $this.self().copyToBuffer(dest);
/*    */   }
/*    */   
/*    */   public static void copyToArray(TraversableProxyLike $this, Object xs, int start, int len) {
/* 74 */     $this.self().copyToArray(xs, start, len);
/*    */   }
/*    */   
/*    */   public static void copyToArray(TraversableProxyLike $this, Object xs, int start) {
/* 75 */     $this.self().copyToArray(xs, start);
/*    */   }
/*    */   
/*    */   public static void copyToArray(TraversableProxyLike $this, Object xs) {
/* 76 */     $this.self().copyToArray(xs);
/*    */   }
/*    */   
/*    */   public static Object toArray(TraversableProxyLike $this, ClassTag evidence$1) {
/* 77 */     return $this.self().toArray(evidence$1);
/*    */   }
/*    */   
/*    */   public static List toList(TraversableProxyLike $this) {
/* 78 */     return $this.self().toList();
/*    */   }
/*    */   
/*    */   public static Iterable toIterable(TraversableProxyLike $this) {
/* 79 */     return $this.self().toIterable();
/*    */   }
/*    */   
/*    */   public static Seq toSeq(TraversableProxyLike $this) {
/* 80 */     return $this.self().toSeq();
/*    */   }
/*    */   
/*    */   public static IndexedSeq toIndexedSeq(TraversableProxyLike $this) {
/* 81 */     return $this.self().toIndexedSeq();
/*    */   }
/*    */   
/*    */   public static Buffer toBuffer(TraversableProxyLike $this) {
/* 82 */     return $this.self().toBuffer();
/*    */   }
/*    */   
/*    */   public static Stream toStream(TraversableProxyLike $this) {
/* 83 */     return $this.self().toStream();
/*    */   }
/*    */   
/*    */   public static Set toSet(TraversableProxyLike $this) {
/* 84 */     return $this.self().toSet();
/*    */   }
/*    */   
/*    */   public static Map toMap(TraversableProxyLike $this, Predef$.less.colon.less ev) {
/* 85 */     return $this.self().toMap(ev);
/*    */   }
/*    */   
/*    */   public static Traversable toTraversable(TraversableProxyLike $this) {
/* 86 */     return $this.self().toTraversable();
/*    */   }
/*    */   
/*    */   public static Iterator toIterator(TraversableProxyLike $this) {
/* 87 */     return $this.self().toIterator();
/*    */   }
/*    */   
/*    */   public static String mkString(TraversableProxyLike $this, String start, String sep, String end) {
/* 88 */     return $this.self().mkString(start, sep, end);
/*    */   }
/*    */   
/*    */   public static String mkString(TraversableProxyLike $this, String sep) {
/* 89 */     return $this.self().mkString(sep);
/*    */   }
/*    */   
/*    */   public static String mkString(TraversableProxyLike $this) {
/* 90 */     return $this.self().mkString();
/*    */   }
/*    */   
/*    */   public static StringBuilder addString(TraversableProxyLike $this, StringBuilder b, String start, String sep, String end) {
/* 91 */     return $this.self().addString(b, start, sep, end);
/*    */   }
/*    */   
/*    */   public static StringBuilder addString(TraversableProxyLike $this, StringBuilder b, String sep) {
/* 92 */     return $this.self().addString(b, sep);
/*    */   }
/*    */   
/*    */   public static StringBuilder addString(TraversableProxyLike $this, StringBuilder b) {
/* 93 */     return $this.self().addString(b);
/*    */   }
/*    */   
/*    */   public static String stringPrefix(TraversableProxyLike $this) {
/* 94 */     return $this.self().stringPrefix();
/*    */   }
/*    */   
/*    */   public static TraversableView view(TraversableProxyLike $this) {
/* 95 */     return (TraversableView)$this.self().view();
/*    */   }
/*    */   
/*    */   public static TraversableView view(TraversableProxyLike $this, int from, int until) {
/* 96 */     return $this.self().view(from, until);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\TraversableProxyLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */