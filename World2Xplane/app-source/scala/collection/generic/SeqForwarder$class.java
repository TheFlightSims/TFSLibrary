/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.immutable.Range;
/*    */ 
/*    */ public abstract class SeqForwarder$class {
/*    */   public static void $init$(SeqForwarder $this) {}
/*    */   
/*    */   public static int length(SeqForwarder<A> $this) {
/* 30 */     return $this.underlying().length();
/*    */   }
/*    */   
/*    */   public static Object apply(SeqForwarder<A> $this, int idx) {
/* 31 */     return $this.underlying().apply(idx);
/*    */   }
/*    */   
/*    */   public static int lengthCompare(SeqForwarder<A> $this, int len) {
/* 32 */     return $this.underlying().lengthCompare(len);
/*    */   }
/*    */   
/*    */   public static boolean isDefinedAt(SeqForwarder<A> $this, int x) {
/* 33 */     return $this.underlying().isDefinedAt(x);
/*    */   }
/*    */   
/*    */   public static int segmentLength(SeqForwarder<A> $this, Function1 p, int from) {
/* 34 */     return $this.underlying().segmentLength(p, from);
/*    */   }
/*    */   
/*    */   public static int prefixLength(SeqForwarder<A> $this, Function1 p) {
/* 35 */     return $this.underlying().prefixLength(p);
/*    */   }
/*    */   
/*    */   public static int indexWhere(SeqForwarder<A> $this, Function1 p) {
/* 36 */     return $this.underlying().indexWhere(p);
/*    */   }
/*    */   
/*    */   public static int indexWhere(SeqForwarder<A> $this, Function1 p, int from) {
/* 37 */     return $this.underlying().indexWhere(p, from);
/*    */   }
/*    */   
/*    */   public static int indexOf(SeqForwarder<A> $this, Object elem) {
/* 38 */     return $this.underlying().indexOf(elem);
/*    */   }
/*    */   
/*    */   public static int indexOf(SeqForwarder<A> $this, Object elem, int from) {
/* 39 */     return $this.underlying().indexOf(elem, from);
/*    */   }
/*    */   
/*    */   public static int lastIndexOf(SeqForwarder<A> $this, Object elem) {
/* 40 */     return $this.underlying().lastIndexOf(elem);
/*    */   }
/*    */   
/*    */   public static int lastIndexOf(SeqForwarder<A> $this, Object elem, int end) {
/* 41 */     return $this.underlying().lastIndexOf(elem, end);
/*    */   }
/*    */   
/*    */   public static int lastIndexWhere(SeqForwarder<A> $this, Function1 p) {
/* 42 */     return $this.underlying().lastIndexWhere(p);
/*    */   }
/*    */   
/*    */   public static int lastIndexWhere(SeqForwarder<A> $this, Function1 p, int end) {
/* 43 */     return $this.underlying().lastIndexWhere(p, end);
/*    */   }
/*    */   
/*    */   public static Iterator reverseIterator(SeqForwarder<A> $this) {
/* 44 */     return $this.underlying().reverseIterator();
/*    */   }
/*    */   
/*    */   public static boolean startsWith(SeqForwarder<A> $this, GenSeq that, int offset) {
/* 45 */     return $this.underlying().startsWith(that, offset);
/*    */   }
/*    */   
/*    */   public static boolean startsWith(SeqForwarder<A> $this, GenSeq that) {
/* 46 */     return $this.underlying().startsWith(that);
/*    */   }
/*    */   
/*    */   public static boolean endsWith(SeqForwarder<A> $this, GenSeq that) {
/* 47 */     return $this.underlying().endsWith(that);
/*    */   }
/*    */   
/*    */   public static int indexOfSlice(SeqForwarder<A> $this, GenSeq that) {
/* 48 */     return $this.underlying().indexOfSlice(that);
/*    */   }
/*    */   
/*    */   public static int indexOfSlice(SeqForwarder<A> $this, GenSeq that, int from) {
/* 49 */     return $this.underlying().indexOfSlice(that, from);
/*    */   }
/*    */   
/*    */   public static int lastIndexOfSlice(SeqForwarder<A> $this, GenSeq that) {
/* 50 */     return $this.underlying().lastIndexOfSlice(that);
/*    */   }
/*    */   
/*    */   public static int lastIndexOfSlice(SeqForwarder<A> $this, GenSeq that, int end) {
/* 51 */     return $this.underlying().lastIndexOfSlice(that, end);
/*    */   }
/*    */   
/*    */   public static boolean containsSlice(SeqForwarder<A> $this, GenSeq that) {
/* 52 */     return $this.underlying().containsSlice(that);
/*    */   }
/*    */   
/*    */   public static boolean contains(SeqForwarder<A> $this, Object elem) {
/* 53 */     return $this.underlying().contains(elem);
/*    */   }
/*    */   
/*    */   public static boolean corresponds(SeqForwarder<A> $this, GenSeq that, Function2 p) {
/* 54 */     return $this.underlying().corresponds(that, p);
/*    */   }
/*    */   
/*    */   public static Range indices(SeqForwarder<A> $this) {
/* 55 */     return $this.underlying().indices();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\SeqForwarder$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */