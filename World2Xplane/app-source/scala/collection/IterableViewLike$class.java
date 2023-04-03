/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.generic.SliceInterval$;
/*     */ import scala.collection.immutable.Stream$;
/*     */ 
/*     */ public abstract class IterableViewLike$class {
/*     */   public static void $init$(IterableViewLike $this) {}
/*     */   
/*     */   public static IterableView scala$collection$IterableViewLike$$asThis(IterableViewLike $this, IterableViewLike.Transformed xs) {
/*  71 */     return xs;
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newZipped(IterableViewLike $this, GenIterable that) {
/*  76 */     return new IterableViewLike$$anon$9($this, (IterableViewLike<A, Coll, This>)that);
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newZippedAll(IterableViewLike $this, GenIterable that, Object _thisElem, Object _thatElem) {
/*  77 */     return new IterableViewLike$$anon$10($this, that, _thisElem, (IterableViewLike<A, Coll, This>)_thatElem);
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newForced(IterableViewLike $this, Function0 xs) {
/*  82 */     return new IterableViewLike$$anon$1($this, (IterableViewLike<A, Coll, This>)xs);
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newAppended(IterableViewLike $this, GenTraversable that) {
/*  83 */     return new IterableViewLike$$anon$2($this, (IterableViewLike<A, Coll, This>)that);
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newMapped(IterableViewLike $this, Function1 f) {
/*  84 */     return new IterableViewLike$$anon$3($this, (IterableViewLike<A, Coll, This>)f);
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newFlatMapped(IterableViewLike $this, Function1 f) {
/*  85 */     return new IterableViewLike$$anon$4($this, (IterableViewLike<A, Coll, This>)f);
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newFiltered(IterableViewLike $this, Function1 p) {
/*  86 */     return new IterableViewLike$$anon$5($this, (IterableViewLike<A, Coll, This>)p);
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newSliced(IterableViewLike $this, SliceInterval _endpoints) {
/*  87 */     return new IterableViewLike$$anon$6($this, (IterableViewLike<A, Coll, This>)_endpoints);
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newDroppedWhile(IterableViewLike $this, Function1 p) {
/*  88 */     return new IterableViewLike$$anon$7($this, (IterableViewLike<A, Coll, This>)p);
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newTakenWhile(IterableViewLike $this, Function1 p) {
/*  89 */     return new IterableViewLike$$anon$8($this, (IterableViewLike<A, Coll, This>)p);
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newTaken(IterableViewLike $this, int n) {
/*  96 */     return $this.newSliced(SliceInterval$.MODULE$.apply(0, n));
/*     */   }
/*     */   
/*     */   public static IterableViewLike.Transformed newDropped(IterableViewLike $this, int n) {
/*  97 */     return $this.newSliced(SliceInterval$.MODULE$.apply(n, 2147483647));
/*     */   }
/*     */   
/*     */   public static IterableView drop(IterableViewLike $this, int n) {
/*  98 */     return scala$collection$IterableViewLike$$asThis($this, $this.newDropped(n));
/*     */   }
/*     */   
/*     */   public static IterableView take(IterableViewLike $this, int n) {
/*  99 */     return scala$collection$IterableViewLike$$asThis($this, $this.newTaken(n));
/*     */   }
/*     */   
/*     */   public static Object zip(IterableViewLike $this, GenIterable that, CanBuildFrom bf) {
/* 102 */     return $this.newZipped(that);
/*     */   }
/*     */   
/*     */   public static Object zipWithIndex(IterableViewLike $this, CanBuildFrom bf) {
/* 109 */     return $this.zip((GenIterable)Stream$.MODULE$.from(0), bf);
/*     */   }
/*     */   
/*     */   public static Object zipAll(IterableViewLike $this, GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 112 */     return $this.newZippedAll(that, thisElem, thatElem);
/*     */   }
/*     */   
/*     */   public static Iterator grouped(IterableViewLike<A, Coll, This> $this, int size) {
/* 115 */     return $this.iterator().grouped(size).map((Function1)new IterableViewLike$$anonfun$grouped$1($this));
/*     */   }
/*     */   
/*     */   public static Iterator sliding(IterableViewLike<A, Coll, This> $this, int size, int step) {
/* 118 */     return $this.iterator().sliding(size, step).map((Function1)new IterableViewLike$$anonfun$sliding$1($this));
/*     */   }
/*     */   
/*     */   public static String stringPrefix(IterableViewLike $this) {
/* 120 */     return "IterableView";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IterableViewLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */