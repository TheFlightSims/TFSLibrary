/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.generic.SliceInterval$;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.collection.parallel.immutable.ParMap$;
/*     */ import scala.collection.parallel.immutable.ParRange$;
/*     */ import scala.collection.parallel.mutable.ParArray$;
/*     */ 
/*     */ public abstract class ParSeqViewLike$class {
/*     */   public static void $init$(ParSeqViewLike $this) {}
/*     */   
/*     */   public static ParSeqViewLike.Transformed newPrepended(ParSeqViewLike $this, Object elem) {
/* 105 */     throw package$.MODULE$.unsupported();
/*     */   }
/*     */   
/*     */   public static ParSeqViewLike.Transformed newSliced(ParSeqViewLike $this, SliceInterval _endpoints) {
/* 109 */     return new ParSeqViewLike$$anon$1($this, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)_endpoints);
/*     */   }
/*     */   
/*     */   public static ParSeqViewLike.Transformed newAppended(ParSeqViewLike $this, GenTraversable that) {
/* 112 */     return package$.MODULE$.traversable2ops((GenTraversableOnce<?>)that).isParSeq() ? new ParSeqViewLike$$anon$2($this, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)that) : 
/* 113 */       $this.newForced((Function0)new ParSeqViewLike$$anonfun$newAppended$1($this, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)that));
/*     */   }
/*     */   
/*     */   public static ParSeqViewLike.Transformed newForced(ParSeqViewLike $this, Function0 xs) {
/* 116 */     return package$.MODULE$.traversable2ops((GenTraversableOnce)xs.apply()).isParSeq() ? new ParSeqViewLike$$anon$3($this, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)xs) : 
/* 117 */       new ParSeqViewLike$$anon$4($this, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)xs);
/*     */   }
/*     */   
/*     */   public static ParSeqViewLike.Transformed newMapped(ParSeqViewLike $this, Function1 f) {
/* 119 */     return new ParSeqViewLike$$anon$5($this, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)f);
/*     */   }
/*     */   
/*     */   public static ParSeqViewLike.Transformed newZipped(ParSeqViewLike $this, GenIterable that) {
/* 120 */     return new ParSeqViewLike$$anon$6($this, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)that);
/*     */   }
/*     */   
/*     */   public static ParSeqViewLike.Transformed newZippedAll(ParSeqViewLike $this, GenIterable that, Object _thisElem, Object _thatElem) {
/* 121 */     return new ParSeqViewLike$$anon$7($this, that, _thisElem, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)_thatElem);
/*     */   }
/*     */   
/*     */   public static ParSeqViewLike.Transformed newReversed(ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq> $this) {
/* 126 */     return new ParSeqViewLike$$anon$8($this);
/*     */   }
/*     */   
/*     */   public static ParSeqViewLike.Transformed newPatched(ParSeqViewLike $this, int _from, GenSeq _patch, int _replaced) {
/* 127 */     return new ParSeqViewLike$$anon$9($this, _from, _patch, _replaced);
/*     */   }
/*     */   
/*     */   public static ParSeqView slice(ParSeqViewLike $this, int from, int until) {
/* 136 */     return $this.newSliced(SliceInterval$.MODULE$.apply(from, until));
/*     */   }
/*     */   
/*     */   public static ParSeqView take(ParSeqViewLike $this, int n) {
/* 137 */     return $this.newSliced(SliceInterval$.MODULE$.apply(0, n));
/*     */   }
/*     */   
/*     */   public static ParSeqView drop(ParSeqViewLike $this, int n) {
/* 138 */     return $this.newSliced(SliceInterval$.MODULE$.apply(n, $this.length()));
/*     */   }
/*     */   
/*     */   public static Tuple2 splitAt(ParSeqViewLike $this, int n) {
/* 139 */     return new Tuple2($this.take(n), $this.drop(n));
/*     */   }
/*     */   
/*     */   public static Object $plus$plus(ParSeqViewLike $this, GenTraversableOnce xs, CanBuildFrom bf) {
/* 142 */     return $this.newAppended(xs.toTraversable());
/*     */   }
/*     */   
/*     */   public static Object $colon$plus(ParSeqViewLike $this, Object elem, CanBuildFrom bf) {
/* 143 */     return $this.$plus$plus((GenTraversableOnce)Iterator$.MODULE$.single(elem), bf);
/*     */   }
/*     */   
/*     */   public static Object map(ParSeqViewLike $this, Function1 f, CanBuildFrom bf) {
/* 147 */     return $this.newMapped(f);
/*     */   }
/*     */   
/*     */   public static Object zip(ParSeqViewLike $this, GenIterable that, CanBuildFrom bf) {
/* 148 */     return $this.newZippedTryParSeq(that);
/*     */   }
/*     */   
/*     */   public static Object zipWithIndex(ParSeqViewLike $this, CanBuildFrom bf) {
/* 150 */     return $this.newZipped((GenIterable)ParRange$.MODULE$.apply(0, $this.splitter().remaining(), 1, false));
/*     */   }
/*     */   
/*     */   public static ParSeqView reverse(ParSeqViewLike $this) {
/* 151 */     return $this.newReversed();
/*     */   }
/*     */   
/*     */   public static Object reverseMap(ParSeqViewLike $this, Function1 f, CanBuildFrom bf) {
/* 152 */     return $this.reverse().map(f, bf);
/*     */   }
/*     */   
/*     */   public static Object updated(ParSeqViewLike $this, int index, Object elem, CanBuildFrom bf) {
/* 156 */     Predef$.MODULE$.require((0 <= index && index < $this.length()));
/* 157 */     return $this.patch(index, (GenSeq)List$.MODULE$.apply((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { elem }, )), 1, bf);
/*     */   }
/*     */   
/*     */   public static Object padTo(ParSeqViewLike $this, int len, Object elem, CanBuildFrom bf) {
/* 159 */     return $this.patch($this.length(), (GenSeq)Seq$.MODULE$.fill(len - $this.length(), (Function0)new ParSeqViewLike$$anonfun$padTo$1($this, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)elem)), 0, bf);
/*     */   }
/*     */   
/*     */   public static Object $plus$colon(ParSeqViewLike $this, Object elem, CanBuildFrom bf) {
/* 160 */     return $this.patch(0, (GenSeq)ParArray$.MODULE$.fromTraversables((Seq)Predef$.MODULE$.genericWrapArray(new GenTraversableOnce[] { (GenTraversableOnce)Iterator$.MODULE$.single(elem) }, )), 0, bf);
/*     */   }
/*     */   
/*     */   public static Object patch(ParSeqViewLike $this, int from, GenSeq patch, int replace, CanBuildFrom bf) {
/* 161 */     return $this.newPatched(from, patch, replace);
/*     */   }
/*     */   
/*     */   public static Object collect(ParSeqViewLike $this, PartialFunction pf, CanBuildFrom bf) {
/* 167 */     return ((ParSeqViewLike)$this.filter((Function1)new ParSeqViewLike$$anonfun$collect$1($this, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)pf))).map((Function1)pf, bf);
/*     */   }
/*     */   
/*     */   public static Object scanLeft(ParSeqViewLike $this, Object z, Function2 op, CanBuildFrom bf) {
/* 168 */     return $this.newForced((Function0)new ParSeqViewLike$$anonfun$scanLeft$1($this, z, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)op));
/*     */   }
/*     */   
/*     */   public static Object scanRight(ParSeqViewLike $this, Object z, Function2 op, CanBuildFrom bf) {
/* 169 */     return $this.newForced((Function0)new ParSeqViewLike$$anonfun$scanRight$1($this, z, (ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq>)op));
/*     */   }
/*     */   
/*     */   public static ParMap groupBy(ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq> $this, Function1 f) {
/* 170 */     return (ParMap)$this.thisParSeq().groupBy(f).map((Function1)new ParSeqViewLike$$anonfun$groupBy$1($this), (CanBuildFrom)ParMap$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public static Object force(ParSeqViewLike<T, Coll, CollSeq, This, ThisSeq> $this, CanBuildFrom<?, ?, ?> bf) {
/*     */     // Byte code:
/*     */     //   0: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */     //   3: aload_1
/*     */     //   4: invokevirtual factory2ops : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/parallel/FactoryOps;
/*     */     //   7: new scala/collection/parallel/ParSeqViewLike$$anonfun$force$1
/*     */     //   10: dup
/*     */     //   11: aload_0
/*     */     //   12: invokespecial <init> : (Lscala/collection/parallel/ParSeqViewLike;)V
/*     */     //   15: invokeinterface ifParallel : (Lscala/Function1;)Lscala/collection/parallel/FactoryOps$Otherwise;
/*     */     //   20: new scala/collection/parallel/ParSeqViewLike$$anonfun$force$2
/*     */     //   23: dup
/*     */     //   24: aload_0
/*     */     //   25: aload_1
/*     */     //   26: invokespecial <init> : (Lscala/collection/parallel/ParSeqViewLike;Lscala/collection/generic/CanBuildFrom;)V
/*     */     //   29: invokeinterface otherwise : (Lscala/Function0;)Ljava/lang/Object;
/*     */     //   34: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #171	-> 0
/*     */     //   #173	-> 20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	35	0	$this	Lscala/collection/parallel/ParSeqViewLike;
/*     */     //   0	35	1	bf	Lscala/collection/generic/CanBuildFrom;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSeqViewLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */