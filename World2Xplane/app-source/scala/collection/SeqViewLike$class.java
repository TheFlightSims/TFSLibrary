/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.generic.SliceInterval$;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.math.Ordering;
/*     */ 
/*     */ public abstract class SeqViewLike$class {
/*     */   public static void $init$(SeqViewLike $this) {}
/*     */   
/*     */   public static SeqViewLike.Transformed newForced(SeqViewLike $this, Function0 xs) {
/*  76 */     return new SeqViewLike$$anon$1($this, (SeqViewLike<A, Coll, This>)xs);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newAppended(SeqViewLike $this, GenTraversable that) {
/*  77 */     return new SeqViewLike$$anon$2($this, (SeqViewLike<A, Coll, This>)that);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newMapped(SeqViewLike $this, Function1 f) {
/*  78 */     return new SeqViewLike$$anon$3($this, (SeqViewLike<A, Coll, This>)f);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newFlatMapped(SeqViewLike $this, Function1 f) {
/*  79 */     return new SeqViewLike$$anon$4($this, (SeqViewLike<A, Coll, This>)f);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newFiltered(SeqViewLike $this, Function1 p) {
/*  80 */     return new SeqViewLike$$anon$5($this, (SeqViewLike<A, Coll, This>)p);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newSliced(SeqViewLike $this, SliceInterval _endpoints) {
/*  81 */     return new SeqViewLike$$anon$6($this, (SeqViewLike<A, Coll, This>)_endpoints);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newDroppedWhile(SeqViewLike $this, Function1 p) {
/*  82 */     return new SeqViewLike$$anon$7($this, (SeqViewLike<A, Coll, This>)p);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newTakenWhile(SeqViewLike $this, Function1 p) {
/*  83 */     return new SeqViewLike$$anon$8($this, (SeqViewLike<A, Coll, This>)p);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newZipped(SeqViewLike $this, GenIterable that) {
/*  84 */     return new SeqViewLike$$anon$9($this, (SeqViewLike<A, Coll, This>)that);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newZippedAll(SeqViewLike $this, GenIterable that, Object _thisElem, Object _thatElem) {
/*  85 */     return new SeqViewLike$$anon$10($this, that, _thisElem, (SeqViewLike<A, Coll, This>)_thatElem);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newReversed(SeqViewLike<A, Coll, This> $this) {
/*  90 */     return new SeqViewLike$$anon$11($this);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newPatched(SeqViewLike $this, int _from, GenSeq _patch, int _replaced) {
/*  91 */     return new SeqViewLike$$anon$12($this, _from, _patch, _replaced);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newPrepended(SeqViewLike $this, Object elem) {
/*  96 */     return new SeqViewLike$$anon$13($this, (SeqViewLike<A, Coll, This>)elem);
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newTaken(SeqViewLike $this, int n) {
/*  99 */     return $this.newSliced(SliceInterval$.MODULE$.apply(0, n));
/*     */   }
/*     */   
/*     */   public static SeqViewLike.Transformed newDropped(SeqViewLike $this, int n) {
/* 100 */     return $this.newSliced(SliceInterval$.MODULE$.apply(n, 2147483647));
/*     */   }
/*     */   
/*     */   public static SeqView reverse(SeqViewLike $this) {
/* 102 */     return $this.newReversed();
/*     */   }
/*     */   
/*     */   public static Object patch(SeqViewLike $this, int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 105 */     return $this.newPatched(from, patch, replaced);
/*     */   }
/*     */   
/*     */   public static Object padTo(SeqViewLike $this, int len, Object elem, CanBuildFrom bf) {
/* 112 */     return $this.patch($this.length(), (GenSeq)Seq$.MODULE$.fill(len - $this.length(), (Function0)new SeqViewLike$$anonfun$padTo$1($this, (SeqViewLike<A, Coll, This>)elem)), 0, bf);
/*     */   }
/*     */   
/*     */   public static Object reverseMap(SeqViewLike $this, Function1 f, CanBuildFrom bf) {
/* 115 */     return $this.reverse().map(f, bf);
/*     */   }
/*     */   
/*     */   public static Object updated(SeqViewLike $this, int index, Object elem, CanBuildFrom bf) {
/* 118 */     Predef$.MODULE$.require((0 <= index && index < $this.length()));
/* 119 */     return $this.patch(index, (GenSeq)List$.MODULE$.apply((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { elem }, )), 1, bf);
/*     */   }
/*     */   
/*     */   public static Object $plus$colon(SeqViewLike $this, Object elem, CanBuildFrom bf) {
/* 123 */     return $this.newPrepended(elem);
/*     */   }
/*     */   
/*     */   public static Object $colon$plus(SeqViewLike $this, Object elem, CanBuildFrom bf) {
/* 126 */     return $this.$plus$plus(Iterator$.MODULE$.single(elem), bf);
/*     */   }
/*     */   
/*     */   public static Object union(SeqViewLike $this, GenSeq that, CanBuildFrom bf) {
/* 129 */     return $this.newForced((Function0)new SeqViewLike$$anonfun$union$1($this, (SeqViewLike<A, Coll, This>)that));
/*     */   }
/*     */   
/*     */   public static SeqView diff(SeqViewLike $this, GenSeq that) {
/* 132 */     return $this.newForced((Function0)new SeqViewLike$$anonfun$diff$1($this, (SeqViewLike<A, Coll, This>)that));
/*     */   }
/*     */   
/*     */   public static SeqView intersect(SeqViewLike $this, GenSeq that) {
/* 135 */     return $this.newForced((Function0)new SeqViewLike$$anonfun$intersect$1($this, (SeqViewLike<A, Coll, This>)that));
/*     */   }
/*     */   
/*     */   public static SeqView sorted(SeqViewLike $this, Ordering ord) {
/* 138 */     return $this.newForced((Function0)new SeqViewLike$$anonfun$sorted$1($this, (SeqViewLike<A, Coll, This>)ord));
/*     */   }
/*     */   
/*     */   public static String stringPrefix(SeqViewLike $this) {
/* 140 */     return "SeqView";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SeqViewLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */