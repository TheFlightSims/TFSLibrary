/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ 
/*    */ public abstract class IterableProxyLike$class {
/*    */   public static void $init$(IterableProxyLike $this) {}
/*    */   
/*    */   public static Iterator iterator(IterableProxyLike $this) {
/* 28 */     return ((IterableLike)$this.self()).iterator();
/*    */   }
/*    */   
/*    */   public static Iterator grouped(IterableProxyLike $this, int size) {
/* 29 */     return ((IterableLike)$this.self()).grouped(size);
/*    */   }
/*    */   
/*    */   public static Iterator sliding(IterableProxyLike $this, int size) {
/* 30 */     return ((IterableLike)$this.self()).sliding(size);
/*    */   }
/*    */   
/*    */   public static Iterator sliding(IterableProxyLike $this, int size, int step) {
/* 31 */     return ((IterableLike)$this.self()).sliding(size, step);
/*    */   }
/*    */   
/*    */   public static Iterable takeRight(IterableProxyLike $this, int n) {
/* 32 */     return (Iterable)((IterableLike)$this.self()).takeRight(n);
/*    */   }
/*    */   
/*    */   public static Iterable dropRight(IterableProxyLike $this, int n) {
/* 33 */     return (Iterable)((IterableLike)$this.self()).dropRight(n);
/*    */   }
/*    */   
/*    */   public static Object zip(IterableProxyLike $this, GenIterable that, CanBuildFrom bf) {
/* 34 */     return ((IterableLike)$this.self()).zip(that, bf);
/*    */   }
/*    */   
/*    */   public static Object zipAll(IterableProxyLike $this, GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 35 */     return ((IterableLike)$this.self()).zipAll(that, thisElem, thatElem, bf);
/*    */   }
/*    */   
/*    */   public static Object zipWithIndex(IterableProxyLike $this, CanBuildFrom bf) {
/* 36 */     return ((IterableLike)$this.self()).zipWithIndex(bf);
/*    */   }
/*    */   
/*    */   public static boolean sameElements(IterableProxyLike $this, GenIterable that) {
/* 37 */     return ((IterableLike)$this.self()).sameElements(that);
/*    */   }
/*    */   
/*    */   public static IterableView view(IterableProxyLike $this) {
/* 38 */     return (IterableView)((IterableLike)$this.self()).view();
/*    */   }
/*    */   
/*    */   public static IterableView view(IterableProxyLike $this, int from, int until) {
/* 39 */     return ((IterableLike)$this.self()).view(from, until);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IterableProxyLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */