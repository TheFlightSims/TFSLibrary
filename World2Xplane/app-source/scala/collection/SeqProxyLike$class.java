/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.math.Ordering;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class SeqProxyLike$class {
/*    */   public static void $init$(SeqProxyLike $this) {}
/*    */   
/*    */   public static int size(SeqProxyLike $this) {
/* 26 */     return ((SeqLike)$this.self()).size();
/*    */   }
/*    */   
/*    */   public static Seq toSeq(SeqProxyLike $this) {
/* 27 */     return ((SeqLike)$this.self()).toSeq();
/*    */   }
/*    */   
/*    */   public static int length(SeqProxyLike $this) {
/* 28 */     return ((SeqLike)$this.self()).length();
/*    */   }
/*    */   
/*    */   public static Object apply(SeqProxyLike $this, int idx) {
/* 29 */     return ((Function1)$this.self()).apply(BoxesRunTime.boxToInteger(idx));
/*    */   }
/*    */   
/*    */   public static int lengthCompare(SeqProxyLike $this, int len) {
/* 30 */     return ((SeqLike)$this.self()).lengthCompare(len);
/*    */   }
/*    */   
/*    */   public static boolean isDefinedAt(SeqProxyLike $this, int x) {
/* 31 */     return ((GenSeqLike)$this.self()).isDefinedAt(x);
/*    */   }
/*    */   
/*    */   public static int segmentLength(SeqProxyLike $this, Function1 p, int from) {
/* 32 */     return ((SeqLike)$this.self()).segmentLength(p, from);
/*    */   }
/*    */   
/*    */   public static int prefixLength(SeqProxyLike $this, Function1 p) {
/* 33 */     return ((GenSeqLike)$this.self()).prefixLength(p);
/*    */   }
/*    */   
/*    */   public static int indexWhere(SeqProxyLike $this, Function1 p) {
/* 34 */     return ((GenSeqLike)$this.self()).indexWhere(p);
/*    */   }
/*    */   
/*    */   public static int indexWhere(SeqProxyLike $this, Function1 p, int from) {
/* 35 */     return ((SeqLike)$this.self()).indexWhere(p, from);
/*    */   }
/*    */   
/*    */   public static int indexOf(SeqProxyLike $this, Object elem) {
/* 36 */     return ((GenSeqLike)$this.self()).indexOf(elem);
/*    */   }
/*    */   
/*    */   public static int indexOf(SeqProxyLike $this, Object elem, int from) {
/* 37 */     return ((GenSeqLike)$this.self()).indexOf(elem, from);
/*    */   }
/*    */   
/*    */   public static int lastIndexOf(SeqProxyLike $this, Object elem) {
/* 38 */     return ((GenSeqLike)$this.self()).lastIndexOf(elem);
/*    */   }
/*    */   
/*    */   public static int lastIndexOf(SeqProxyLike $this, Object elem, int end) {
/* 39 */     return ((SeqLike)$this.self()).lastIndexWhere((Function1)new SeqProxyLike$$anonfun$lastIndexOf$1($this, (SeqProxyLike<A, Repr>)elem), end);
/*    */   }
/*    */   
/*    */   public static int lastIndexWhere(SeqProxyLike $this, Function1 p) {
/* 40 */     return ((SeqLike)$this.self()).lastIndexWhere(p, $this.length() - 1);
/*    */   }
/*    */   
/*    */   public static int lastIndexWhere(SeqProxyLike $this, Function1 p, int end) {
/* 41 */     return ((GenSeqLike)$this.self()).lastIndexWhere(p);
/*    */   }
/*    */   
/*    */   public static Seq reverse(SeqProxyLike $this) {
/* 42 */     return (Seq)((SeqLike)$this.self()).reverse();
/*    */   }
/*    */   
/*    */   public static Object reverseMap(SeqProxyLike $this, Function1 f, CanBuildFrom bf) {
/* 43 */     return ((SeqLike)$this.self()).reverseMap(f, bf);
/*    */   }
/*    */   
/*    */   public static Iterator reverseIterator(SeqProxyLike $this) {
/* 44 */     return ((SeqLike)$this.self()).reverseIterator();
/*    */   }
/*    */   
/*    */   public static boolean startsWith(SeqProxyLike $this, GenSeq that, int offset) {
/* 45 */     return ((SeqLike)$this.self()).startsWith(that, offset);
/*    */   }
/*    */   
/*    */   public static boolean startsWith(SeqProxyLike $this, GenSeq that) {
/* 46 */     return ((GenSeqLike)$this.self()).startsWith(that);
/*    */   }
/*    */   
/*    */   public static boolean endsWith(SeqProxyLike $this, GenSeq that) {
/* 47 */     return ((SeqLike)$this.self()).endsWith(that);
/*    */   }
/*    */   
/*    */   public static int indexOfSlice(SeqProxyLike $this, GenSeq that) {
/* 48 */     return ((SeqLike)$this.self()).indexOfSlice(that);
/*    */   }
/*    */   
/*    */   public static int indexOfSlice(SeqProxyLike $this, GenSeq that, int from) {
/* 49 */     return ((SeqLike)$this.self()).indexOfSlice(that);
/*    */   }
/*    */   
/*    */   public static int lastIndexOfSlice(SeqProxyLike $this, GenSeq that) {
/* 50 */     return ((SeqLike)$this.self()).lastIndexOfSlice(that);
/*    */   }
/*    */   
/*    */   public static int lastIndexOfSlice(SeqProxyLike $this, GenSeq that, int end) {
/* 51 */     return ((SeqLike)$this.self()).lastIndexOfSlice(that, end);
/*    */   }
/*    */   
/*    */   public static boolean containsSlice(SeqProxyLike $this, GenSeq that) {
/* 52 */     return (((SeqLike)$this.self()).indexOfSlice(that) != -1);
/*    */   }
/*    */   
/*    */   public static boolean contains(SeqProxyLike $this, Object elem) {
/* 53 */     return ((SeqLike)$this.self()).contains(elem);
/*    */   }
/*    */   
/*    */   public static Object union(SeqProxyLike $this, GenSeq that, CanBuildFrom bf) {
/* 54 */     return ((SeqLike)$this.self()).union(that, bf);
/*    */   }
/*    */   
/*    */   public static Seq diff(SeqProxyLike $this, GenSeq that) {
/* 55 */     return (Seq)((SeqLike)$this.self()).diff(that);
/*    */   }
/*    */   
/*    */   public static Seq intersect(SeqProxyLike $this, GenSeq that) {
/* 56 */     return (Seq)((SeqLike)$this.self()).intersect(that);
/*    */   }
/*    */   
/*    */   public static Seq distinct(SeqProxyLike $this) {
/* 57 */     return (Seq)((SeqLike)$this.self()).distinct();
/*    */   }
/*    */   
/*    */   public static Object patch(SeqProxyLike $this, int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 58 */     return ((SeqLike)$this.self()).patch(from, patch, replaced, bf);
/*    */   }
/*    */   
/*    */   public static Object updated(SeqProxyLike $this, int index, Object elem, CanBuildFrom bf) {
/* 59 */     return ((SeqLike)$this.self()).updated(index, elem, bf);
/*    */   }
/*    */   
/*    */   public static Object $plus$colon(SeqProxyLike $this, Object elem, CanBuildFrom bf) {
/* 60 */     return ((SeqLike)$this.self()).$plus$colon(elem, bf);
/*    */   }
/*    */   
/*    */   public static Object $colon$plus(SeqProxyLike $this, Object elem, CanBuildFrom bf) {
/* 61 */     return ((SeqLike)$this.self()).$colon$plus(elem, bf);
/*    */   }
/*    */   
/*    */   public static Object padTo(SeqProxyLike $this, int len, Object elem, CanBuildFrom bf) {
/* 62 */     return ((SeqLike)$this.self()).padTo(len, elem, bf);
/*    */   }
/*    */   
/*    */   public static boolean corresponds(SeqProxyLike $this, GenSeq that, Function2 p) {
/* 63 */     return ((SeqLike)$this.self()).corresponds(that, p);
/*    */   }
/*    */   
/*    */   public static Seq sortWith(SeqProxyLike $this, Function2 lt) {
/* 64 */     return (Seq)((SeqLike)$this.self()).sortWith(lt);
/*    */   }
/*    */   
/*    */   public static Seq sortBy(SeqProxyLike $this, Function1 f, Ordering ord) {
/* 65 */     return (Seq)((SeqLike)$this.self()).sortBy(f, ord);
/*    */   }
/*    */   
/*    */   public static Seq sorted(SeqProxyLike $this, Ordering ord) {
/* 66 */     return (Seq)((SeqLike)$this.self()).sorted(ord);
/*    */   }
/*    */   
/*    */   public static Range indices(SeqProxyLike $this) {
/* 67 */     return ((SeqLike)$this.self()).indices();
/*    */   }
/*    */   
/*    */   public static SeqView view(SeqProxyLike $this) {
/* 68 */     return (SeqView)((SeqLike)$this.self()).view();
/*    */   }
/*    */   
/*    */   public static SeqView view(SeqProxyLike $this, int from, int until) {
/* 69 */     return ((SeqLike)$this.self()).view(from, until);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SeqProxyLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */