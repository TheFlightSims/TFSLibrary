/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Map$;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.Map$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.WrappedArray;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParIterable$;
/*     */ import scala.math.package$;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ public abstract class TraversableLike$class {
/*     */   public static void $init$(TraversableLike $this) {}
/*     */   
/*     */   public static Object repr(TraversableLike $this) {
/*  86 */     return $this;
/*     */   }
/*     */   
/*     */   public static final boolean isTraversableAgain(TraversableLike $this) {
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   public static Traversable thisCollection(TraversableLike $this) {
/*  94 */     return (Traversable)$this;
/*     */   }
/*     */   
/*     */   public static Traversable toCollection(TraversableLike $this, Object repr) {
/*  99 */     return (Traversable)repr;
/*     */   }
/*     */   
/*     */   public static Combiner parCombiner(TraversableLike $this) {
/* 105 */     return ParIterable$.MODULE$.newCombiner();
/*     */   }
/*     */   
/*     */   public static boolean isEmpty(TraversableLike $this) {
/* 130 */     BooleanRef result = new BooleanRef(true);
/* 131 */     Traversable$.MODULE$.breaks().breakable(
/* 132 */         (Function0)new TraversableLike$$anonfun$isEmpty$1($this, (TraversableLike<A, Repr>)result));
/* 137 */     return result.elem;
/*     */   }
/*     */   
/*     */   public static boolean hasDefiniteSize(TraversableLike $this) {
/* 151 */     return true;
/*     */   }
/*     */   
/*     */   public static Object $plus$plus(TraversableLike $this, GenTraversableOnce that, CanBuildFrom bf) {
/* 154 */     Builder b = bf.apply($this.repr());
/* 155 */     if (that instanceof IndexedSeqLike)
/* 155 */       b.sizeHint($this, that.seq().size()); 
/* 156 */     b.$plus$plus$eq($this.thisCollection());
/* 157 */     b.$plus$plus$eq(that.seq());
/* 158 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object $plus$plus$colon(TraversableLike $this, TraversableOnce that, CanBuildFrom bf) {
/* 194 */     Builder b = bf.apply($this.repr());
/* 195 */     if (that instanceof IndexedSeqLike)
/* 195 */       b.sizeHint($this, that.size()); 
/* 196 */     b.$plus$plus$eq(that);
/* 197 */     b.$plus$plus$eq($this.thisCollection());
/* 198 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object $plus$plus$colon(TraversableLike $this, Traversable that, CanBuildFrom<Nothing$, ?, ?> bf) {
/* 235 */     return that.$plus$plus($this.seq(), package$.MODULE$.breakOut(bf));
/*     */   }
/*     */   
/*     */   private static final Builder builder$1(TraversableLike $this, CanBuildFrom bf$1) {
/* 239 */     Builder b = bf$1.apply($this.repr());
/* 240 */     b.sizeHint($this);
/* 241 */     return b;
/*     */   }
/*     */   
/*     */   public static Object map(TraversableLike $this, Function1 f, CanBuildFrom bf) {
/* 243 */     Builder b = builder$1($this, bf);
/* 244 */     $this.foreach((Function1)new TraversableLike$$anonfun$map$1($this, b, (TraversableLike<A, Repr>)f));
/* 245 */     return b.result();
/*     */   }
/*     */   
/*     */   private static final Builder builder$2(TraversableLike $this, CanBuildFrom bf$2) {
/* 249 */     return bf$2.apply($this.repr());
/*     */   }
/*     */   
/*     */   public static Object flatMap(TraversableLike $this, Function1 f, CanBuildFrom bf) {
/* 250 */     Builder b = builder$2($this, bf);
/* 251 */     $this.foreach((Function1)new TraversableLike$$anonfun$flatMap$1($this, b, (TraversableLike<A, Repr>)f));
/* 252 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object filter(TraversableLike $this, Function1 p) {
/* 262 */     Builder b = $this.newBuilder();
/* 263 */     $this.foreach((Function1)new TraversableLike$$anonfun$filter$1($this, b, (TraversableLike<A, Repr>)p));
/* 265 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object filterNot(TraversableLike $this, Function1 p) {
/* 274 */     return $this.filter((Function1)new TraversableLike$$anonfun$filterNot$1($this, (TraversableLike<A, Repr>)p));
/*     */   }
/*     */   
/*     */   public static Object collect(TraversableLike $this, PartialFunction pf, CanBuildFrom bf) {
/* 277 */     Builder b = bf.apply($this.repr());
/* 278 */     $this.foreach((Function1)new TraversableLike$$anonfun$collect$1($this, b, (TraversableLike<A, Repr>)pf));
/* 279 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Tuple2 partition(TraversableLike $this, Function1 p) {
/* 320 */     Builder l = $this.newBuilder(), r = $this.newBuilder();
/* 321 */     $this.foreach((Function1)new TraversableLike$$anonfun$partition$1($this, l, r, (TraversableLike<A, Repr>)p));
/* 322 */     return new Tuple2(l.result(), r.result());
/*     */   }
/*     */   
/*     */   public static Map groupBy(TraversableLike<A, Repr> $this, Function1 f) {
/* 326 */     Map m = Map$.MODULE$.empty();
/* 327 */     $this.foreach((Function1)new TraversableLike$$anonfun$groupBy$1($this, m, (TraversableLike<A, Repr>)f));
/* 332 */     Builder b = Map$.MODULE$.newBuilder();
/* 333 */     m.withFilter((Function1)new TraversableLike$$anonfun$groupBy$2($this)).foreach((Function1)new TraversableLike$$anonfun$groupBy$3($this, (TraversableLike<A, Repr>)b));
/* 336 */     return (Map)b.result();
/*     */   }
/*     */   
/*     */   public static boolean forall(TraversableLike $this, Function1 p) {
/* 348 */     BooleanRef result = new BooleanRef(true);
/* 349 */     Traversable$.MODULE$.breaks().breakable(
/* 350 */         (Function0)new TraversableLike$$anonfun$forall$1($this, result, (TraversableLike<A, Repr>)p));
/* 353 */     return result.elem;
/*     */   }
/*     */   
/*     */   public static boolean exists(TraversableLike $this, Function1 p) {
/* 365 */     BooleanRef result = new BooleanRef(false);
/* 366 */     Traversable$.MODULE$.breaks().breakable(
/* 367 */         (Function0)new TraversableLike$$anonfun$exists$1($this, result, (TraversableLike<A, Repr>)p));
/* 370 */     return result.elem;
/*     */   }
/*     */   
/*     */   public static Option find(TraversableLike $this, Function1 p) {
/* 383 */     ObjectRef result = new ObjectRef(None$.MODULE$);
/* 384 */     Traversable$.MODULE$.breaks().breakable(
/* 385 */         (Function0)new TraversableLike$$anonfun$find$1($this, result, (TraversableLike<A, Repr>)p));
/* 388 */     return (Option)result.elem;
/*     */   }
/*     */   
/*     */   public static Object scan(TraversableLike $this, Object z, Function2 op, CanBuildFrom cbf) {
/* 391 */     return $this.scanLeft(z, op, cbf);
/*     */   }
/*     */   
/*     */   public static Object scanLeft(TraversableLike $this, Object z, Function2 op, CanBuildFrom bf) {
/* 394 */     Builder b = bf.apply($this.repr());
/* 395 */     b.sizeHint($this, 1);
/* 396 */     ObjectRef acc = new ObjectRef(z);
/* 397 */     b.$plus$eq(acc.elem);
/* 398 */     $this.foreach((Function1)new TraversableLike$$anonfun$scanLeft$1($this, b, acc, (TraversableLike<A, Repr>)op));
/* 399 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object scanRight(TraversableLike $this, Object z, Function2 op, CanBuildFrom bf) {
/* 404 */     WrappedArray wrappedArray = Predef$.MODULE$.genericWrapArray(new Object[] { z });
/* 404 */     List$ list$ = List$.MODULE$;
/* 404 */     ObjectRef scanned = new ObjectRef(wrappedArray.toList());
/* 405 */     ObjectRef acc = new ObjectRef(z);
/* 406 */     List these1 = $this.reversed();
/*     */     while (true) {
/* 406 */       if (these1.isEmpty()) {
/* 410 */         Builder b = bf.apply($this.repr());
/* 411 */         List these2 = (List)scanned.elem;
/*     */         while (true) {
/* 411 */           if (these2.isEmpty())
/* 412 */             return b.result(); 
/*     */           Object object1 = these2.head();
/*     */           b.$plus$eq(object1);
/*     */           these2 = (List)these2.tail();
/*     */         } 
/*     */         break;
/*     */       } 
/*     */       Object object = these1.head();
/*     */       acc.elem = op.apply(object, acc.elem);
/*     */       scanned.elem = ((List)scanned.elem).$colon$colon(acc.elem);
/*     */       these1 = (List)these1.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object head(TraversableLike<A, Repr> $this) {
/* 421 */     TraversableLike$$anonfun$2 traversableLike$$anonfun$2 = new TraversableLike$$anonfun$2($this);
/* 421 */     ObjectRef result = new ObjectRef(traversableLike$$anonfun$2);
/* 422 */     Traversable$.MODULE$.breaks().breakable(
/* 423 */         (Function0)new TraversableLike$$anonfun$head$1($this, (TraversableLike<A, Repr>)result));
/* 428 */     return ((Function0)result.elem).apply();
/*     */   }
/*     */   
/*     */   public static Option headOption(TraversableLike $this) {
/* 436 */     return $this.isEmpty() ? (Option)None$.MODULE$ : (Option)new Some($this.head());
/*     */   }
/*     */   
/*     */   public static Object tail(TraversableLike $this) {
/* 445 */     if ($this.isEmpty())
/* 445 */       throw new UnsupportedOperationException("empty.tail"); 
/* 446 */     return $this.drop(1);
/*     */   }
/*     */   
/*     */   public static Object last(TraversableLike $this) {
/* 455 */     ObjectRef lst = new ObjectRef($this.head());
/* 456 */     $this.foreach((Function1)new TraversableLike$$anonfun$last$1($this, (TraversableLike<A, Repr>)lst));
/* 458 */     return lst.elem;
/*     */   }
/*     */   
/*     */   public static Option lastOption(TraversableLike $this) {
/* 466 */     return $this.isEmpty() ? (Option)None$.MODULE$ : (Option)new Some($this.last());
/*     */   }
/*     */   
/*     */   public static Object init(TraversableLike $this) {
/* 475 */     if ($this.isEmpty())
/* 475 */       throw new UnsupportedOperationException("empty.init"); 
/* 476 */     ObjectRef lst = new ObjectRef($this.head());
/* 477 */     BooleanRef follow = new BooleanRef(false);
/* 478 */     Builder b = $this.newBuilder();
/* 479 */     b.sizeHint($this, -1);
/* 480 */     $this.seq().foreach((Function1)new TraversableLike$$anonfun$init$1($this, lst, follow, (TraversableLike<A, Repr>)b));
/* 485 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object take(TraversableLike $this, int n) {
/* 488 */     return $this.slice(0, n);
/*     */   }
/*     */   
/*     */   public static Object drop(TraversableLike $this, int n) {
/* 492 */     Builder b = $this.newBuilder();
/* 493 */     b.sizeHint($this);
/* 494 */     return (n <= 0) ? ((Builder)b.$plus$plus$eq($this.thisCollection())).result() : 
/*     */       
/* 496 */       $this.sliceWithKnownDelta(n, 2147483647, -n);
/*     */   }
/*     */   
/*     */   public static Object slice(TraversableLike $this, int from, int until) {
/* 499 */     return $this.sliceWithKnownBound(package$.MODULE$.max(from, 0), until);
/*     */   }
/*     */   
/*     */   public static Object scala$collection$TraversableLike$$sliceInternal(TraversableLike $this, int from, int until, Builder b) {
/* 503 */     IntRef i = new IntRef(0);
/* 504 */     Traversable$.MODULE$.breaks().breakable(
/* 505 */         (Function0)new TraversableLike$$anonfun$scala$collection$TraversableLike$$sliceInternal$1($this, from, until, b, (TraversableLike)i));
/* 511 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object sliceWithKnownDelta(TraversableLike $this, int from, int until, int delta) {
/* 515 */     Builder b = $this.newBuilder();
/* 518 */     b.sizeHint($this, delta);
/* 519 */     return (until <= from) ? b.result() : scala$collection$TraversableLike$$sliceInternal($this, from, until, b);
/*     */   }
/*     */   
/*     */   public static Object sliceWithKnownBound(TraversableLike $this, int from, int until) {
/* 524 */     Builder b = $this.newBuilder();
/* 527 */     b.sizeHintBounded(until - from, $this);
/* 528 */     return (until <= from) ? b.result() : scala$collection$TraversableLike$$sliceInternal($this, from, until, b);
/*     */   }
/*     */   
/*     */   public static Object takeWhile(TraversableLike $this, Function1 p) {
/* 533 */     Builder b = $this.newBuilder();
/* 534 */     Traversable$.MODULE$.breaks().breakable(
/* 535 */         (Function0)new TraversableLike$$anonfun$takeWhile$1($this, b, (TraversableLike<A, Repr>)p));
/* 540 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object dropWhile(TraversableLike $this, Function1 p) {
/* 544 */     Builder b = $this.newBuilder();
/* 545 */     BooleanRef go = new BooleanRef(false);
/* 546 */     $this.foreach((Function1)new TraversableLike$$anonfun$dropWhile$1($this, b, go, (TraversableLike<A, Repr>)p));
/* 550 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Tuple2 span(TraversableLike $this, Function1 p) {
/* 554 */     Builder l = $this.newBuilder(), r = $this.newBuilder();
/* 555 */     BooleanRef toLeft = new BooleanRef(true);
/* 556 */     $this.foreach((Function1)new TraversableLike$$anonfun$span$1($this, l, r, toLeft, (TraversableLike<A, Repr>)p));
/* 560 */     return new Tuple2(l.result(), r.result());
/*     */   }
/*     */   
/*     */   public static Tuple2 splitAt(TraversableLike $this, int n) {
/* 564 */     Builder l = $this.newBuilder(), r = $this.newBuilder();
/* 565 */     l.sizeHintBounded(n, $this);
/* 566 */     if (n >= 0)
/* 566 */       r.sizeHint($this, -n); 
/* 567 */     IntRef i = new IntRef(0);
/* 568 */     $this.foreach((Function1)new TraversableLike$$anonfun$splitAt$1($this, l, r, i, n));
/* 572 */     return new Tuple2(l.result(), r.result());
/*     */   }
/*     */   
/*     */   public static Iterator tails(TraversableLike<A, Repr> $this) {
/* 582 */     return iterateUntilEmpty($this, (Function1)new TraversableLike$$anonfun$tails$1($this));
/*     */   }
/*     */   
/*     */   public static Iterator inits(TraversableLike<A, Repr> $this) {
/* 591 */     return iterateUntilEmpty($this, (Function1)new TraversableLike$$anonfun$inits$1($this));
/*     */   }
/*     */   
/*     */   public static void copyToArray(TraversableLike $this, Object xs, int start, int len) {
/* 611 */     IntRef i = new IntRef(start);
/* 612 */     int j = start + len;
/* 612 */     Predef$ predef$ = Predef$.MODULE$;
/* 612 */     int end = RichInt$.MODULE$.min$extension(j, ScalaRunTime$.MODULE$.array_length(xs));
/* 613 */     Traversable$.MODULE$.breaks().breakable(
/* 614 */         (Function0)new TraversableLike$$anonfun$copyToArray$1($this, i, end, (TraversableLike<A, Repr>)xs));
/*     */   }
/*     */   
/*     */   public static Traversable toTraversable(TraversableLike $this) {
/* 622 */     return $this.thisCollection();
/*     */   }
/*     */   
/*     */   public static Iterator toIterator(TraversableLike $this) {
/* 623 */     return $this.toStream().iterator();
/*     */   }
/*     */   
/*     */   public static Stream toStream(TraversableLike $this) {
/* 624 */     return $this.toBuffer().toStream();
/*     */   }
/*     */   
/*     */   public static Object to(TraversableLike $this, CanBuildFrom cbf) {
/* 627 */     Builder b = cbf.apply();
/* 628 */     b.sizeHint($this);
/* 629 */     b.$plus$plus$eq($this.thisCollection());
/* 630 */     return b.result();
/*     */   }
/*     */   
/*     */   public static String toString(TraversableLike $this) {
/* 639 */     return $this.mkString((new StringBuilder()).append($this.stringPrefix()).append("(").toString(), ", ", ")");
/*     */   }
/*     */   
/*     */   public static String stringPrefix(TraversableLike $this) {
/* 648 */     String string = $this.repr().getClass().getName();
/* 649 */     int idx1 = string.lastIndexOf('.');
/* 650 */     if (idx1 != -1)
/* 650 */       string = string.substring(idx1 + 1); 
/* 651 */     int idx2 = string.indexOf('$');
/* 652 */     if (idx2 != -1)
/* 652 */       string = string.substring(0, idx2); 
/* 653 */     return string;
/*     */   }
/*     */   
/*     */   public static TraversableView view(TraversableLike<A, Repr> $this) {
/* 660 */     return new TraversableLike$$anon$1($this);
/*     */   }
/*     */   
/*     */   public static TraversableView view(TraversableLike $this, int from, int until) {
/* 678 */     return $this.view().slice(from, until);
/*     */   }
/*     */   
/*     */   public static FilterMonadic withFilter(TraversableLike<A, Repr> $this, Function1<A, Object> p) {
/* 694 */     return new TraversableLike.WithFilter($this, p);
/*     */   }
/*     */   
/*     */   private static Iterator iterateUntilEmpty(TraversableLike $this, Function1 f) {
/* 788 */     Traversable traversable = $this.thisCollection();
/* 788 */     Iterator$ iterator$ = Iterator$.MODULE$;
/* 788 */     Iterator<?> it = (new Iterator$$anon$7(traversable, f)).takeWhile((Function1<?, Object>)new TraversableLike$$anonfun$3($this));
/* 789 */     return it.$plus$plus((Function0<GenTraversableOnce<?>>)new TraversableLike$$anonfun$iterateUntilEmpty$1($this)).map((Function1)new TraversableLike$$anonfun$iterateUntilEmpty$2($this));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\TraversableLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */