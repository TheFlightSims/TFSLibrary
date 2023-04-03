/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.generic.SliceInterval$;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.runtime.StringAdd$;
/*     */ 
/*     */ public abstract class TraversableViewLike$class {
/*     */   public static void $init$(TraversableViewLike $this) {}
/*     */   
/*     */   public static Builder newBuilder(TraversableViewLike $this) {
/*  78 */     throw new UnsupportedOperationException(StringAdd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd($this), ".newBuilder"));
/*     */   }
/*     */   
/*     */   public static String viewIdentifier(TraversableViewLike $this) {
/*  81 */     return "";
/*     */   }
/*     */   
/*     */   public static String viewIdString(TraversableViewLike $this) {
/*  82 */     return "";
/*     */   }
/*     */   
/*     */   public static String stringPrefix(TraversableViewLike $this) {
/*  83 */     return "TraversableView";
/*     */   }
/*     */   
/*     */   public static Object force(TraversableViewLike $this, CanBuildFrom bf) {
/*  86 */     Builder b = bf.apply($this.underlying());
/*  87 */     b.$plus$plus$eq($this);
/*  88 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object $plus$plus(TraversableViewLike $this, GenTraversableOnce xs, CanBuildFrom bf) {
/* 144 */     return $this.newAppended(xs.seq().toTraversable());
/*     */   }
/*     */   
/*     */   public static Object map(TraversableViewLike $this, Function1 f, CanBuildFrom bf) {
/* 150 */     return $this.newMapped(f);
/*     */   }
/*     */   
/*     */   public static Object collect(TraversableViewLike $this, PartialFunction pf, CanBuildFrom bf) {
/* 157 */     return $this.filter((Function1)new TraversableViewLike$$anonfun$collect$1($this, (TraversableViewLike<A, Coll, This>)pf)).map((Function1)pf, bf);
/*     */   }
/*     */   
/*     */   public static Object flatMap(TraversableViewLike $this, Function1 f, CanBuildFrom bf) {
/* 160 */     return $this.newFlatMapped(f);
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed flatten(TraversableViewLike $this, Function1 asTraversable) {
/* 166 */     return $this.newFlatMapped(asTraversable);
/*     */   }
/*     */   
/*     */   public static TraversableView scala$collection$TraversableViewLike$$asThis(TraversableViewLike $this, TraversableViewLike.Transformed xs) {
/* 167 */     return xs;
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed newForced(TraversableViewLike $this, Function0 xs) {
/* 172 */     return new TraversableViewLike$$anon$1($this, (TraversableViewLike<A, Coll, This>)xs);
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed newAppended(TraversableViewLike $this, GenTraversable that) {
/* 173 */     return new TraversableViewLike$$anon$2($this, (TraversableViewLike<A, Coll, This>)that);
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed newMapped(TraversableViewLike $this, Function1 f) {
/* 174 */     return new TraversableViewLike$$anon$3($this, (TraversableViewLike<A, Coll, This>)f);
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed newFlatMapped(TraversableViewLike $this, Function1 f) {
/* 175 */     return new TraversableViewLike$$anon$4($this, (TraversableViewLike<A, Coll, This>)f);
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed newFiltered(TraversableViewLike $this, Function1 p) {
/* 176 */     return new TraversableViewLike$$anon$5($this, (TraversableViewLike<A, Coll, This>)p);
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed newSliced(TraversableViewLike $this, SliceInterval _endpoints) {
/* 177 */     return new TraversableViewLike$$anon$6($this, (TraversableViewLike<A, Coll, This>)_endpoints);
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed newDroppedWhile(TraversableViewLike $this, Function1 p) {
/* 178 */     return new TraversableViewLike$$anon$7($this, (TraversableViewLike<A, Coll, This>)p);
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed newTakenWhile(TraversableViewLike $this, Function1 p) {
/* 179 */     return new TraversableViewLike$$anon$8($this, (TraversableViewLike<A, Coll, This>)p);
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed newTaken(TraversableViewLike $this, int n) {
/* 181 */     return $this.newSliced(SliceInterval$.MODULE$.apply(0, n));
/*     */   }
/*     */   
/*     */   public static TraversableViewLike.Transformed newDropped(TraversableViewLike $this, int n) {
/* 182 */     return $this.newSliced(SliceInterval$.MODULE$.apply(n, 2147483647));
/*     */   }
/*     */   
/*     */   public static TraversableView filter(TraversableViewLike $this, Function1 p) {
/* 184 */     return scala$collection$TraversableViewLike$$asThis($this, $this.newFiltered(p));
/*     */   }
/*     */   
/*     */   public static TraversableView withFilter(TraversableViewLike $this, Function1 p) {
/* 185 */     return scala$collection$TraversableViewLike$$asThis($this, $this.newFiltered(p));
/*     */   }
/*     */   
/*     */   public static Tuple2 partition(TraversableViewLike $this, Function1 p) {
/* 186 */     return new Tuple2(scala$collection$TraversableViewLike$$asThis($this, $this.newFiltered(p)), scala$collection$TraversableViewLike$$asThis($this, $this.newFiltered((Function1)new TraversableViewLike$$anonfun$partition$1($this, (TraversableViewLike<A, Coll, This>)p))));
/*     */   }
/*     */   
/*     */   public static TraversableView init(TraversableViewLike $this) {
/* 187 */     return scala$collection$TraversableViewLike$$asThis($this, $this.newSliced(SliceInterval$.MODULE$.apply(0, $this.size() - 1)));
/*     */   }
/*     */   
/*     */   public static TraversableView drop(TraversableViewLike $this, int n) {
/* 188 */     return scala$collection$TraversableViewLike$$asThis($this, $this.newDropped(n));
/*     */   }
/*     */   
/*     */   public static TraversableView take(TraversableViewLike $this, int n) {
/* 189 */     return scala$collection$TraversableViewLike$$asThis($this, $this.newTaken(n));
/*     */   }
/*     */   
/*     */   public static TraversableView slice(TraversableViewLike $this, int from, int until) {
/* 190 */     return scala$collection$TraversableViewLike$$asThis($this, $this.newSliced(SliceInterval$.MODULE$.apply(from, until)));
/*     */   }
/*     */   
/*     */   public static TraversableView dropWhile(TraversableViewLike $this, Function1 p) {
/* 191 */     return scala$collection$TraversableViewLike$$asThis($this, $this.newDroppedWhile(p));
/*     */   }
/*     */   
/*     */   public static TraversableView takeWhile(TraversableViewLike $this, Function1 p) {
/* 192 */     return scala$collection$TraversableViewLike$$asThis($this, $this.newTakenWhile(p));
/*     */   }
/*     */   
/*     */   public static Tuple2 span(TraversableViewLike $this, Function1 p) {
/* 193 */     return new Tuple2(scala$collection$TraversableViewLike$$asThis($this, $this.newTakenWhile(p)), scala$collection$TraversableViewLike$$asThis($this, $this.newDroppedWhile(p)));
/*     */   }
/*     */   
/*     */   public static Tuple2 splitAt(TraversableViewLike $this, int n) {
/* 194 */     return new Tuple2(scala$collection$TraversableViewLike$$asThis($this, $this.newTaken(n)), scala$collection$TraversableViewLike$$asThis($this, $this.newDropped(n)));
/*     */   }
/*     */   
/*     */   public static Object scanLeft(TraversableViewLike $this, Object z, Function2 op, CanBuildFrom bf) {
/* 197 */     return $this.newForced((Function0)new TraversableViewLike$$anonfun$scanLeft$1($this, z, (TraversableViewLike<A, Coll, This>)op));
/*     */   }
/*     */   
/*     */   public static Object scanRight(TraversableViewLike $this, Object z, Function2 op, CanBuildFrom bf) {
/* 201 */     return $this.newForced((Function0)new TraversableViewLike$$anonfun$scanRight$1($this, z, (TraversableViewLike<A, Coll, This>)op));
/*     */   }
/*     */   
/*     */   public static Map groupBy(TraversableViewLike<A, Coll, This> $this, Function1 f) {
/* 204 */     return $this.thisSeq().groupBy(f).mapValues((Function1)new TraversableViewLike$$anonfun$groupBy$1($this));
/*     */   }
/*     */   
/*     */   public static Tuple2 unzip(TraversableViewLike $this, Function1 asPair) {
/* 207 */     return new Tuple2($this.newMapped((Function1)new TraversableViewLike$$anonfun$unzip$1($this, (TraversableViewLike<A, Coll, This>)asPair)), $this.newMapped((Function1)new TraversableViewLike$$anonfun$unzip$2($this, (TraversableViewLike<A, Coll, This>)asPair)));
/*     */   }
/*     */   
/*     */   public static Tuple3 unzip3(TraversableViewLike $this, Function1 asTriple) {
/* 210 */     return new Tuple3($this.newMapped((Function1)new TraversableViewLike$$anonfun$unzip3$1($this, (TraversableViewLike<A, Coll, This>)asTriple)), $this.newMapped((Function1)new TraversableViewLike$$anonfun$unzip3$2($this, (TraversableViewLike<A, Coll, This>)asTriple)), $this.newMapped((Function1)new TraversableViewLike$$anonfun$unzip3$3($this, (TraversableViewLike<A, Coll, This>)asTriple)));
/*     */   }
/*     */   
/*     */   public static String toString(TraversableViewLike $this) {
/* 212 */     return $this.viewToString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\TraversableViewLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */