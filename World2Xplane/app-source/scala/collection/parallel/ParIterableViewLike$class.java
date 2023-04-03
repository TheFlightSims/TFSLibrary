/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.generic.SliceInterval$;
/*     */ import scala.collection.parallel.immutable.ParRange$;
/*     */ import scala.collection.parallel.mutable.ParArray$;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.StringAdd$;
/*     */ 
/*     */ public abstract class ParIterableViewLike$class {
/*     */   public static void $init$(ParIterableViewLike $this) {}
/*     */   
/*     */   public static void foreach(ParIterableViewLike $this, Function1 f) {
/*  52 */     ParIterableLike$class.foreach($this, f);
/*     */   }
/*     */   
/*     */   public static Combiner newCombiner(ParIterableViewLike $this) {
/*  53 */     throw new UnsupportedOperationException(StringAdd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd($this), ".newCombiner"));
/*     */   }
/*     */   
/*     */   public static ParSeq thisParSeq(ParIterableViewLike $this) {
/* 105 */     return (ParSeq)ParArray$.MODULE$.fromTraversables((Seq)Predef$.MODULE$.genericWrapArray(new GenTraversableOnce[] { (GenTraversableOnce)$this.iterator() }));
/*     */   }
/*     */   
/*     */   public static ParIterableView scala$collection$parallel$ParIterableViewLike$$asThis(ParIterableViewLike $this, ParIterableViewLike.Transformed xs) {
/* 106 */     return xs;
/*     */   }
/*     */   
/*     */   public static ParIterableView take(ParIterableViewLike $this, int n) {
/* 110 */     return scala$collection$parallel$ParIterableViewLike$$asThis($this, $this.newSliced(SliceInterval$.MODULE$.apply(0, n)));
/*     */   }
/*     */   
/*     */   public static ParIterableView drop(ParIterableViewLike $this, int n) {
/* 111 */     return scala$collection$parallel$ParIterableViewLike$$asThis($this, $this.newSliced(SliceInterval$.MODULE$.apply(n, $this.splitter().remaining())));
/*     */   }
/*     */   
/*     */   public static Tuple2 splitAt(ParIterableViewLike $this, int n) {
/* 112 */     return new Tuple2($this.take(n), $this.drop(n));
/*     */   }
/*     */   
/*     */   public static ParIterableView slice(ParIterableViewLike $this, int from, int until) {
/* 113 */     return scala$collection$parallel$ParIterableViewLike$$asThis($this, $this.newSliced(SliceInterval$.MODULE$.apply(from, until)));
/*     */   }
/*     */   
/*     */   public static Object map(ParIterableViewLike $this, Function1 f, CanBuildFrom bf) {
/* 114 */     return $this.newMapped(f);
/*     */   }
/*     */   
/*     */   public static Object $plus$plus(ParIterableViewLike $this, GenTraversableOnce xs, CanBuildFrom bf) {
/* 115 */     return $this.newAppendedTryParIterable(xs.toTraversable());
/*     */   }
/*     */   
/*     */   public static ParIterableView filter(ParIterableViewLike $this, Function1 p) {
/* 117 */     return $this.newForced((Function0)new ParIterableViewLike$$anonfun$filter$1($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)p));
/*     */   }
/*     */   
/*     */   public static ParIterableView filterNot(ParIterableViewLike $this, Function1 p) {
/* 118 */     return $this.newForced((Function0)new ParIterableViewLike$$anonfun$filterNot$1($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)p));
/*     */   }
/*     */   
/*     */   public static Tuple2 partition(ParIterableViewLike $this, Function1 p) {
/* 120 */     Tuple2 tuple2 = $this.thisParSeq().partition(p);
/* 120 */     if (tuple2 != null) {
/* 120 */       Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 120 */       ParSeq t = (ParSeq)tuple21._1(), f = (ParSeq)tuple21._2();
/* 121 */       return new Tuple2($this.newForced((Function0)new ParIterableViewLike$$anonfun$partition$1($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)t)), $this.newForced((Function0)new ParIterableViewLike$$anonfun$partition$2($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)f)));
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public static ParIterableView takeWhile(ParIterableViewLike $this, Function1 p) {
/* 123 */     return $this.newForced((Function0)new ParIterableViewLike$$anonfun$takeWhile$1($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)p));
/*     */   }
/*     */   
/*     */   public static ParIterableView dropWhile(ParIterableViewLike $this, Function1 p) {
/* 124 */     return $this.newForced((Function0)new ParIterableViewLike$$anonfun$dropWhile$1($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)p));
/*     */   }
/*     */   
/*     */   public static Tuple2 span(ParIterableViewLike $this, Function1 p) {
/* 126 */     Tuple2 tuple2 = $this.thisParSeq().span(p);
/* 126 */     if (tuple2 != null) {
/* 126 */       Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 126 */       ParSeq pref = (ParSeq)tuple21._1(), suff = (ParSeq)tuple21._2();
/* 127 */       return new Tuple2($this.newForced((Function0)new ParIterableViewLike$$anonfun$span$1($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)pref)), $this.newForced((Function0)new ParIterableViewLike$$anonfun$span$2($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)suff)));
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public static Object flatMap(ParIterableViewLike $this, Function1 f, CanBuildFrom bf) {
/* 129 */     return $this.newForced((Function0)new ParIterableViewLike$$anonfun$flatMap$1($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)f));
/*     */   }
/*     */   
/*     */   public static Object zip(ParIterableViewLike $this, GenIterable that, CanBuildFrom bf) {
/* 131 */     return $this.newZippedTryParSeq(that);
/*     */   }
/*     */   
/*     */   public static Object zipWithIndex(ParIterableViewLike $this, CanBuildFrom bf) {
/* 133 */     return $this.newZipped((GenIterable)ParRange$.MODULE$.apply(0, $this.splitter().remaining(), 1, false));
/*     */   }
/*     */   
/*     */   public static Object zipAll(ParIterableViewLike $this, GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 135 */     return $this.newZippedAllTryParSeq(that, thisElem, thatElem);
/*     */   }
/*     */   
/*     */   public static Object force(ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq> $this, CanBuildFrom<?, ?, ?> bf) {
/*     */     // Byte code:
/*     */     //   0: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */     //   3: aload_1
/*     */     //   4: invokevirtual factory2ops : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/parallel/FactoryOps;
/*     */     //   7: new scala/collection/parallel/ParIterableViewLike$$anonfun$force$1
/*     */     //   10: dup
/*     */     //   11: aload_0
/*     */     //   12: invokespecial <init> : (Lscala/collection/parallel/ParIterableViewLike;)V
/*     */     //   15: invokeinterface ifParallel : (Lscala/Function1;)Lscala/collection/parallel/FactoryOps$Otherwise;
/*     */     //   20: new scala/collection/parallel/ParIterableViewLike$$anonfun$force$2
/*     */     //   23: dup
/*     */     //   24: aload_0
/*     */     //   25: aload_1
/*     */     //   26: invokespecial <init> : (Lscala/collection/parallel/ParIterableViewLike;Lscala/collection/generic/CanBuildFrom;)V
/*     */     //   29: invokeinterface otherwise : (Lscala/Function0;)Ljava/lang/Object;
/*     */     //   34: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #137	-> 0
/*     */     //   #139	-> 20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	35	0	$this	Lscala/collection/parallel/ParIterableViewLike;
/*     */     //   0	35	1	bf	Lscala/collection/generic/CanBuildFrom;
/*     */   }
/*     */   
/*     */   public static ParIterableViewLike.Transformed newSliced(ParIterableViewLike $this, SliceInterval _endpoints) {
/* 147 */     return new ParIterableViewLike$$anon$1($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)_endpoints);
/*     */   }
/*     */   
/*     */   public static ParIterableViewLike.Transformed newMapped(ParIterableViewLike $this, Function1 f) {
/* 148 */     return new ParIterableViewLike$$anon$2($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)f);
/*     */   }
/*     */   
/*     */   public static ParIterableViewLike.Transformed newForced(ParIterableViewLike $this, Function0 xs) {
/* 149 */     return new ParIterableViewLike$$anon$3($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)xs);
/*     */   }
/*     */   
/*     */   public static ParIterableViewLike.Transformed newAppended(ParIterableViewLike $this, GenTraversable that) {
/* 150 */     return new ParIterableViewLike$$anon$4($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)that);
/*     */   }
/*     */   
/*     */   public static Nothing$ newDroppedWhile(ParIterableViewLike $this, Function1 p) {
/* 151 */     return package$.MODULE$.unsupported();
/*     */   }
/*     */   
/*     */   public static Nothing$ newTakenWhile(ParIterableViewLike $this, Function1 p) {
/* 152 */     return package$.MODULE$.unsupported();
/*     */   }
/*     */   
/*     */   public static Nothing$ newFlatMapped(ParIterableViewLike $this, Function1 f) {
/* 153 */     return package$.MODULE$.unsupported();
/*     */   }
/*     */   
/*     */   public static Nothing$ newFiltered(ParIterableViewLike $this, Function1 p) {
/* 154 */     return package$.MODULE$.unsupported();
/*     */   }
/*     */   
/*     */   public static ParIterableViewLike.Transformed newZipped(ParIterableViewLike $this, GenIterable that) {
/* 155 */     return new ParIterableViewLike$$anon$5($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)that);
/*     */   }
/*     */   
/*     */   public static ParIterableViewLike.Transformed newZippedAll(ParIterableViewLike $this, GenIterable that, Object _thisElem, Object _thatElem) {
/* 156 */     return new ParIterableViewLike$$anon$6($this, that, _thisElem, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)_thatElem);
/*     */   }
/*     */   
/*     */   public static ParIterableViewLike.Transformed newForcedTryParIterable(ParIterableViewLike $this, Function0 xs) {
/* 165 */     return package$.MODULE$.traversable2ops((GenTraversableOnce)xs.apply()).isParIterable() ? $this.newForced(xs) : 
/* 166 */       $this.newForced((Function0)new ParIterableViewLike$$anonfun$newForcedTryParIterable$1($this, (ParIterableViewLike<T, Coll, CollSeq, This, ThisSeq>)xs));
/*     */   }
/*     */   
/*     */   public static ParIterableViewLike.Transformed newAppendedTryParIterable(ParIterableViewLike $this, GenTraversable that) {
/* 170 */     return package$.MODULE$.traversable2ops((GenTraversableOnce<?>)that).isParIterable() ? $this.newAppended(that) : 
/* 171 */       $this.newAppended((GenTraversable)ParArray$.MODULE$.fromTraversables((Seq)Predef$.MODULE$.genericWrapArray(new GenTraversableOnce[] { (GenTraversableOnce)that })));
/*     */   }
/*     */   
/*     */   public static ParIterableViewLike.Transformed newZippedTryParSeq(ParIterableViewLike $this, GenIterable that) {
/* 174 */     return package$.MODULE$.traversable2ops((GenTraversableOnce<?>)that).isParSeq() ? $this.newZipped(that) : 
/* 175 */       $this.newZipped((GenIterable)ParArray$.MODULE$.fromTraversables((Seq)Predef$.MODULE$.genericWrapArray(new GenTraversableOnce[] { (GenTraversableOnce)that })));
/*     */   }
/*     */   
/*     */   public static ParIterableViewLike.Transformed newZippedAllTryParSeq(ParIterableViewLike $this, GenIterable that, Object thisElem, Object thatElem) {
/* 178 */     return package$.MODULE$.traversable2ops((GenTraversableOnce<?>)that).isParSeq() ? $this.newZippedAll(that, thisElem, thatElem) : 
/* 179 */       $this.newZippedAll((GenIterable)ParArray$.MODULE$.fromTraversables((Seq)Predef$.MODULE$.genericWrapArray(new GenTraversableOnce[] { (GenTraversableOnce)that }, )), thisElem, thatElem);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParIterableViewLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */