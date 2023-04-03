/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Seq;
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
/*    */ public abstract class TraversableForwarder$class {
/*    */   public static void $init$(TraversableForwarder $this) {}
/*    */   
/*    */   public static void foreach(TraversableForwarder<A> $this, Function1 f) {
/* 32 */     $this.underlying().foreach(f);
/*    */   }
/*    */   
/*    */   public static boolean isEmpty(TraversableForwarder<A> $this) {
/* 33 */     return $this.underlying().isEmpty();
/*    */   }
/*    */   
/*    */   public static boolean nonEmpty(TraversableForwarder<A> $this) {
/* 34 */     return $this.underlying().nonEmpty();
/*    */   }
/*    */   
/*    */   public static int size(TraversableForwarder<A> $this) {
/* 35 */     return $this.underlying().size();
/*    */   }
/*    */   
/*    */   public static boolean hasDefiniteSize(TraversableForwarder<A> $this) {
/* 36 */     return $this.underlying().hasDefiniteSize();
/*    */   }
/*    */   
/*    */   public static boolean forall(TraversableForwarder<A> $this, Function1 p) {
/* 37 */     return $this.underlying().forall(p);
/*    */   }
/*    */   
/*    */   public static boolean exists(TraversableForwarder<A> $this, Function1 p) {
/* 38 */     return $this.underlying().exists(p);
/*    */   }
/*    */   
/*    */   public static int count(TraversableForwarder<A> $this, Function1 p) {
/* 39 */     return $this.underlying().count(p);
/*    */   }
/*    */   
/*    */   public static Option find(TraversableForwarder<A> $this, Function1 p) {
/* 40 */     return $this.underlying().find(p);
/*    */   }
/*    */   
/*    */   public static Object foldLeft(TraversableForwarder<A> $this, Object z, Function2 op) {
/* 41 */     return $this.underlying().foldLeft(z, op);
/*    */   }
/*    */   
/*    */   public static Object $div$colon(TraversableForwarder<A> $this, Object z, Function2 op) {
/* 42 */     return $this.underlying().$div$colon(z, op);
/*    */   }
/*    */   
/*    */   public static Object foldRight(TraversableForwarder<A> $this, Object z, Function2 op) {
/* 43 */     return $this.underlying().foldRight(z, op);
/*    */   }
/*    */   
/*    */   public static Object $colon$bslash(TraversableForwarder<A> $this, Object z, Function2 op) {
/* 44 */     return $this.underlying().$colon$bslash(z, op);
/*    */   }
/*    */   
/*    */   public static Object reduceLeft(TraversableForwarder<A> $this, Function2 op) {
/* 45 */     return $this.underlying().reduceLeft(op);
/*    */   }
/*    */   
/*    */   public static Option reduceLeftOption(TraversableForwarder<A> $this, Function2 op) {
/* 46 */     return $this.underlying().reduceLeftOption(op);
/*    */   }
/*    */   
/*    */   public static Object reduceRight(TraversableForwarder<A> $this, Function2 op) {
/* 47 */     return $this.underlying().reduceRight(op);
/*    */   }
/*    */   
/*    */   public static Option reduceRightOption(TraversableForwarder<A> $this, Function2 op) {
/* 48 */     return $this.underlying().reduceRightOption(op);
/*    */   }
/*    */   
/*    */   public static Object sum(TraversableForwarder<A> $this, Numeric num) {
/* 49 */     return $this.underlying().sum(num);
/*    */   }
/*    */   
/*    */   public static Object product(TraversableForwarder<A> $this, Numeric num) {
/* 50 */     return $this.underlying().product(num);
/*    */   }
/*    */   
/*    */   public static Object min(TraversableForwarder<A> $this, Ordering cmp) {
/* 51 */     return $this.underlying().min(cmp);
/*    */   }
/*    */   
/*    */   public static Object max(TraversableForwarder<A> $this, Ordering cmp) {
/* 52 */     return $this.underlying().max(cmp);
/*    */   }
/*    */   
/*    */   public static Object head(TraversableForwarder<A> $this) {
/* 53 */     return $this.underlying().head();
/*    */   }
/*    */   
/*    */   public static Option headOption(TraversableForwarder<A> $this) {
/* 54 */     return $this.underlying().headOption();
/*    */   }
/*    */   
/*    */   public static Object last(TraversableForwarder<A> $this) {
/* 55 */     return $this.underlying().last();
/*    */   }
/*    */   
/*    */   public static Option lastOption(TraversableForwarder<A> $this) {
/* 56 */     return $this.underlying().lastOption();
/*    */   }
/*    */   
/*    */   public static void copyToBuffer(TraversableForwarder<A> $this, Buffer dest) {
/* 57 */     $this.underlying().copyToBuffer(dest);
/*    */   }
/*    */   
/*    */   public static void copyToArray(TraversableForwarder<A> $this, Object xs, int start, int len) {
/* 58 */     $this.underlying().copyToArray(xs, start, len);
/*    */   }
/*    */   
/*    */   public static void copyToArray(TraversableForwarder<A> $this, Object xs, int start) {
/* 59 */     $this.underlying().copyToArray(xs, start);
/*    */   }
/*    */   
/*    */   public static void copyToArray(TraversableForwarder<A> $this, Object xs) {
/* 60 */     $this.underlying().copyToArray(xs);
/*    */   }
/*    */   
/*    */   public static Object toArray(TraversableForwarder<A> $this, ClassTag evidence$1) {
/* 61 */     return $this.underlying().toArray(evidence$1);
/*    */   }
/*    */   
/*    */   public static List toList(TraversableForwarder<A> $this) {
/* 62 */     return $this.underlying().toList();
/*    */   }
/*    */   
/*    */   public static Iterable toIterable(TraversableForwarder<A> $this) {
/* 63 */     return $this.underlying().toIterable();
/*    */   }
/*    */   
/*    */   public static Seq toSeq(TraversableForwarder<A> $this) {
/* 64 */     return $this.underlying().toSeq();
/*    */   }
/*    */   
/*    */   public static IndexedSeq toIndexedSeq(TraversableForwarder<A> $this) {
/* 65 */     return $this.underlying().toIndexedSeq();
/*    */   }
/*    */   
/*    */   public static Buffer toBuffer(TraversableForwarder<A> $this) {
/* 66 */     return $this.underlying().toBuffer();
/*    */   }
/*    */   
/*    */   public static Stream toStream(TraversableForwarder<A> $this) {
/* 67 */     return $this.underlying().toStream();
/*    */   }
/*    */   
/*    */   public static Set toSet(TraversableForwarder<A> $this) {
/* 68 */     return $this.underlying().toSet();
/*    */   }
/*    */   
/*    */   public static Map toMap(TraversableForwarder<A> $this, Predef$.less.colon.less ev) {
/* 69 */     return $this.underlying().toMap(ev);
/*    */   }
/*    */   
/*    */   public static String mkString(TraversableForwarder<A> $this, String start, String sep, String end) {
/* 70 */     return $this.underlying().mkString(start, sep, end);
/*    */   }
/*    */   
/*    */   public static String mkString(TraversableForwarder<A> $this, String sep) {
/* 71 */     return $this.underlying().mkString(sep);
/*    */   }
/*    */   
/*    */   public static String mkString(TraversableForwarder<A> $this) {
/* 72 */     return $this.underlying().mkString();
/*    */   }
/*    */   
/*    */   public static StringBuilder addString(TraversableForwarder<A> $this, StringBuilder b, String start, String sep, String end) {
/* 73 */     return $this.underlying().addString(b, start, sep, end);
/*    */   }
/*    */   
/*    */   public static StringBuilder addString(TraversableForwarder<A> $this, StringBuilder b, String sep) {
/* 74 */     return $this.underlying().addString(b, sep);
/*    */   }
/*    */   
/*    */   public static StringBuilder addString(TraversableForwarder<A> $this, StringBuilder b) {
/* 75 */     return $this.underlying().addString(b);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\TraversableForwarder$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */