/*     */ package scala.collection;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.mutable.ArraySeq;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.HashMap;
/*     */ import scala.collection.mutable.HashSet;
/*     */ import scala.collection.mutable.HashSet$;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParSeq$;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.Ordering$;
/*     */ import scala.math.package$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ public abstract class SeqLike$class {
/*     */   public static void $init$(SeqLike $this) {}
/*     */   
/*     */   public static Seq thisCollection(SeqLike $this) {
/*  64 */     return (Seq)$this;
/*     */   }
/*     */   
/*     */   public static Seq toCollection(SeqLike $this, Object repr) {
/*  65 */     return (Seq)repr;
/*     */   }
/*     */   
/*     */   public static Combiner parCombiner(SeqLike $this) {
/*  71 */     return ParSeq$.MODULE$.newCombiner();
/*     */   }
/*     */   
/*     */   public static int lengthCompare(SeqLike $this, int len) {
/*  89 */     int i = 0;
/*  90 */     Iterator it = $this.iterator();
/*  91 */     while (it.hasNext()) {
/*  92 */       if (i == len)
/*  92 */         return it.hasNext() ? 1 : 0; 
/*  93 */       it.next();
/*  94 */       i++;
/*     */     } 
/*  96 */     return (len < 0) ? 1 : (i - len);
/*     */   }
/*     */   
/*     */   public static boolean isEmpty(SeqLike $this) {
/* 100 */     return ($this.lengthCompare(0) == 0);
/*     */   }
/*     */   
/*     */   public static int size(SeqLike $this) {
/* 106 */     return $this.length();
/*     */   }
/*     */   
/*     */   public static int segmentLength(SeqLike $this, Function1 p, int from) {
/* 109 */     int i = 0;
/* 110 */     Iterator it = $this.iterator().drop(from);
/* 111 */     while (it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(it.next())))
/* 112 */       i++; 
/* 113 */     return i;
/*     */   }
/*     */   
/*     */   public static int indexWhere(SeqLike $this, Function1 p, int from) {
/* 117 */     int i = from;
/* 118 */     Iterator it = $this.iterator().drop(from);
/* 119 */     while (it.hasNext()) {
/* 120 */       if (BoxesRunTime.unboxToBoolean(p.apply(it.next())))
/* 120 */         return i; 
/* 121 */       i++;
/*     */     } 
/* 124 */     return -1;
/*     */   }
/*     */   
/*     */   public static int lastIndexWhere(SeqLike $this, Function1 p, int end) {
/* 128 */     int i = $this.length() - 1;
/* 129 */     Iterator it = $this.reverseIterator();
/* 130 */     while (it.hasNext()) {
/* 130 */       Object elem = it.next();
/* 130 */       if (!(i <= end && BoxesRunTime.unboxToBoolean(p.apply(elem))))
/* 130 */         i--; 
/*     */     } 
/* 131 */     return i;
/*     */   }
/*     */   
/*     */   public static Iterator permutations(SeqLike<A, Repr> $this) {
/* 140 */     return $this.isEmpty() ? Iterator$.MODULE$.apply((Seq<?>)Predef$.MODULE$.genericWrapArray(new Object[] { $this.repr() })) : new SeqLike.PermutationsItr($this);
/*     */   }
/*     */   
/*     */   public static Iterator combinations(SeqLike<A, Repr> $this, int n) {
/* 149 */     return (n < 0 || n > $this.size()) ? Iterator$.MODULE$.empty() : 
/* 150 */       new SeqLike.CombinationsItr($this, n);
/*     */   }
/*     */   
/*     */   public static Object reverse(SeqLike $this) {
/* 264 */     ObjectRef xs = new ObjectRef(Nil$.MODULE$);
/* 265 */     $this.foreach((Function1)new SeqLike$$anonfun$reverse$1($this, (SeqLike<A, Repr>)xs));
/* 267 */     Builder b = $this.newBuilder();
/* 268 */     b.sizeHint($this);
/* 269 */     List these1 = (List)xs.elem;
/*     */     while (true) {
/* 269 */       if (these1.isEmpty())
/* 271 */         return b.result(); 
/*     */       Object object = these1.head();
/*     */       b.$plus$eq(object);
/*     */       these1 = (List)these1.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object reverseMap(SeqLike $this, Function1 f, CanBuildFrom bf) {
/* 275 */     ObjectRef xs = new ObjectRef(Nil$.MODULE$);
/* 276 */     $this.seq().foreach((Function1)new SeqLike$$anonfun$reverseMap$1($this, (SeqLike<A, Repr>)xs));
/* 278 */     Builder b = bf.apply($this.repr());
/* 279 */     List these1 = (List)xs.elem;
/*     */     while (true) {
/* 279 */       if (these1.isEmpty())
/* 282 */         return b.result(); 
/*     */       Object object = these1.head();
/*     */       b.$plus$eq(f.apply(object));
/*     */       these1 = (List)these1.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Iterator reverseIterator(SeqLike $this) {
/* 293 */     return $this.toCollection($this.reverse()).iterator();
/*     */   }
/*     */   
/*     */   public static boolean startsWith(SeqLike $this, GenSeq that, int offset) {
/* 296 */     Iterator<Object> i = $this.iterator().drop(offset);
/* 297 */     Iterator<Object> j = that.iterator();
/* 298 */     while (j.hasNext() && i.hasNext()) {
/* 299 */       Object object = j.next();
/*     */       Number number;
/* 299 */       if (!(((number = (Number)i.next()) == object) ? 1 : ((number == null) ? 0 : ((number instanceof Number) ? BoxesRunTime.equalsNumObject(number, object) : ((number instanceof Character) ? BoxesRunTime.equalsCharObject((Character)number, object) : number.equals(object))))))
/* 300 */         return false; 
/*     */     } 
/* 302 */     return !j.hasNext();
/*     */   }
/*     */   
/*     */   public static boolean endsWith(SeqLike $this, GenSeq that) {
/* 306 */     Iterator<Object> i = $this.iterator().drop($this.length() - that.length());
/* 307 */     Iterator<Object> j = that.iterator();
/* 308 */     while (i.hasNext() && j.hasNext()) {
/* 309 */       Object object = j.next();
/*     */       Number number;
/* 309 */       if (!(((number = (Number)i.next()) == object) ? 1 : ((number == null) ? 0 : ((number instanceof Number) ? BoxesRunTime.equalsNumObject(number, object) : ((number instanceof Character) ? BoxesRunTime.equalsCharObject((Character)number, object) : number.equals(object))))))
/* 310 */         return false; 
/*     */     } 
/* 312 */     return !j.hasNext();
/*     */   }
/*     */   
/*     */   public static int indexOfSlice(SeqLike $this, GenSeq that) {
/* 321 */     return $this.indexOfSlice(that, 0);
/*     */   }
/*     */   
/*     */   public static int indexOfSlice(SeqLike $this, GenSeq that, int from) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokeinterface hasDefiniteSize : ()Z
/*     */     //   6: ifeq -> 101
/*     */     //   9: aload_1
/*     */     //   10: invokeinterface hasDefiniteSize : ()Z
/*     */     //   15: ifeq -> 101
/*     */     //   18: aload_0
/*     */     //   19: invokeinterface length : ()I
/*     */     //   24: istore_3
/*     */     //   25: aload_1
/*     */     //   26: invokeinterface length : ()I
/*     */     //   31: istore #4
/*     */     //   33: getstatic scala/math/package$.MODULE$ : Lscala/math/package$;
/*     */     //   36: iconst_0
/*     */     //   37: iload_2
/*     */     //   38: invokevirtual max : (II)I
/*     */     //   41: istore #5
/*     */     //   43: iload_2
/*     */     //   44: iload_3
/*     */     //   45: if_icmple -> 52
/*     */     //   48: iconst_m1
/*     */     //   49: goto -> 132
/*     */     //   52: iload #4
/*     */     //   54: iconst_1
/*     */     //   55: if_icmpge -> 63
/*     */     //   58: iload #5
/*     */     //   60: goto -> 132
/*     */     //   63: iload_3
/*     */     //   64: iload #4
/*     */     //   66: if_icmpge -> 73
/*     */     //   69: iconst_m1
/*     */     //   70: goto -> 132
/*     */     //   73: getstatic scala/collection/SeqLike$.MODULE$ : Lscala/collection/SeqLike$;
/*     */     //   76: aload_0
/*     */     //   77: invokeinterface thisCollection : ()Lscala/collection/Seq;
/*     */     //   82: iload #5
/*     */     //   84: iload_3
/*     */     //   85: aload_1
/*     */     //   86: invokeinterface seq : ()Lscala/collection/Seq;
/*     */     //   91: iconst_0
/*     */     //   92: iload #4
/*     */     //   94: iconst_1
/*     */     //   95: invokevirtual scala$collection$SeqLike$$kmpSearch : (Lscala/collection/Seq;IILscala/collection/Seq;IIZ)I
/*     */     //   98: goto -> 132
/*     */     //   101: iload_2
/*     */     //   102: istore #6
/*     */     //   104: aload_0
/*     */     //   105: invokeinterface thisCollection : ()Lscala/collection/Seq;
/*     */     //   110: iload_2
/*     */     //   111: invokeinterface drop : (I)Ljava/lang/Object;
/*     */     //   116: checkcast scala/collection/Seq
/*     */     //   119: astore #7
/*     */     //   121: aload #7
/*     */     //   123: invokeinterface isEmpty : ()Z
/*     */     //   128: ifeq -> 133
/*     */     //   131: iconst_m1
/*     */     //   132: ireturn
/*     */     //   133: aload #7
/*     */     //   135: aload_1
/*     */     //   136: invokeinterface startsWith : (Lscala/collection/GenSeq;)Z
/*     */     //   141: ifeq -> 147
/*     */     //   144: iload #6
/*     */     //   146: ireturn
/*     */     //   147: iload #6
/*     */     //   149: iconst_1
/*     */     //   150: iadd
/*     */     //   151: istore #6
/*     */     //   153: aload #7
/*     */     //   155: invokeinterface tail : ()Ljava/lang/Object;
/*     */     //   160: checkcast scala/collection/Seq
/*     */     //   163: astore #7
/*     */     //   165: goto -> 121
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #331	-> 0
/*     */     //   #332	-> 18
/*     */     //   #333	-> 25
/*     */     //   #334	-> 33
/*     */     //   #335	-> 43
/*     */     //   #336	-> 52
/*     */     //   #337	-> 63
/*     */     //   #338	-> 73
/*     */     //   #341	-> 101
/*     */     //   #342	-> 104
/*     */     //   #343	-> 121
/*     */     //   #350	-> 131
/*     */     //   #331	-> 132
/*     */     //   #344	-> 133
/*     */     //   #345	-> 144
/*     */     //   #347	-> 147
/*     */     //   #348	-> 153
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	168	0	$this	Lscala/collection/SeqLike;
/*     */     //   0	168	1	that	Lscala/collection/GenSeq;
/*     */     //   0	168	2	from	I
/*     */     //   25	143	3	l	I
/*     */     //   33	135	4	tl	I
/*     */     //   43	125	5	clippedFrom	I
/*     */     //   104	28	6	i	I
/*     */     //   121	11	7	s	Lscala/collection/Seq;
/*     */   }
/*     */   
/*     */   public static int lastIndexOfSlice(SeqLike $this, GenSeq that) {
/* 359 */     return $this.lastIndexOfSlice(that, $this.length());
/*     */   }
/*     */   
/*     */   public static int lastIndexOfSlice(SeqLike $this, GenSeq<?> that, int end) {
/* 368 */     int l = $this.length();
/* 369 */     int tl = that.length();
/* 370 */     int clippedL = package$.MODULE$.min(l - tl, end);
/* 372 */     return (end < 0) ? -1 : (
/* 373 */       (tl < 1) ? clippedL : (
/* 374 */       (l < tl) ? -1 : 
/* 375 */       SeqLike$.MODULE$.scala$collection$SeqLike$$kmpSearch($this.thisCollection(), 0, clippedL + tl, that.seq(), 0, tl, false)));
/*     */   }
/*     */   
/*     */   public static boolean containsSlice(SeqLike $this, GenSeq that) {
/* 384 */     return ($this.indexOfSlice(that) != -1);
/*     */   }
/*     */   
/*     */   public static boolean contains(SeqLike $this, Object elem) {
/* 393 */     return $this.exists((Function1)new SeqLike$$anonfun$contains$1($this, (SeqLike<A, Repr>)elem));
/*     */   }
/*     */   
/*     */   public static Object union(SeqLike $this, GenSeq that, CanBuildFrom bf) {
/* 417 */     return $this.$plus$plus(that, bf);
/*     */   }
/*     */   
/*     */   public static Object diff(SeqLike $this, GenSeq that) {
/* 440 */     Map occ = occCounts($this, that.seq());
/* 441 */     Builder b = $this.newBuilder();
/* 442 */     $this.foreach((Function1)new SeqLike$$anonfun$diff$1($this, occ, (SeqLike<A, Repr>)b));
/* 445 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object intersect(SeqLike $this, GenSeq that) {
/* 469 */     Map occ = occCounts($this, that.seq());
/* 470 */     Builder b = $this.newBuilder();
/* 471 */     $this.foreach((Function1)new SeqLike$$anonfun$intersect$1($this, occ, (SeqLike<A, Repr>)b));
/* 476 */     return b.result();
/*     */   }
/*     */   
/*     */   private static Map occCounts(SeqLike $this, Seq sq) {
/* 480 */     HashMap occ = new SeqLike$$anon$1($this);
/* 481 */     sq.seq().foreach((Function1)new SeqLike$$anonfun$occCounts$1($this, (SeqLike)occ));
/* 482 */     return (Map)occ;
/*     */   }
/*     */   
/*     */   public static Object distinct(SeqLike $this) {
/* 491 */     Builder b = $this.newBuilder();
/* 492 */     HashSet seen = (HashSet)HashSet$.MODULE$.apply((Seq)Nil$.MODULE$);
/* 493 */     $this.foreach((Function1)new SeqLike$$anonfun$distinct$1($this, b, (SeqLike<A, Repr>)seen));
/* 499 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object patch(SeqLike $this, int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 503 */     Builder b = bf.apply($this.repr());
/* 504 */     Tuple2 tuple2 = $this.splitAt(from);
/* 504 */     if (tuple2 != null) {
/* 504 */       Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 504 */       Object prefix = tuple21._1(), rest = tuple21._2();
/* 505 */       b.$plus$plus$eq($this.toCollection(prefix));
/* 506 */       b.$plus$plus$eq(patch.seq());
/* 507 */       b.$plus$plus$eq($this.toCollection(rest).view().drop(replaced));
/* 508 */       return b.result();
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public static Object updated(SeqLike $this, int index, Object elem, CanBuildFrom bf) {
/* 512 */     Builder b = bf.apply($this.repr());
/* 513 */     Tuple2 tuple2 = $this.splitAt(index);
/* 513 */     if (tuple2 != null) {
/* 513 */       Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 513 */       Object prefix = tuple21._1(), rest = tuple21._2();
/* 514 */       b.$plus$plus$eq($this.toCollection(prefix));
/* 515 */       b.$plus$eq(elem);
/* 516 */       b.$plus$plus$eq($this.toCollection(rest).view().tail());
/* 517 */       return b.result();
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public static Object $plus$colon(SeqLike $this, Object elem, CanBuildFrom bf) {
/* 521 */     Builder b = bf.apply($this.repr());
/* 522 */     b.$plus$eq(elem);
/* 523 */     b.$plus$plus$eq($this.thisCollection());
/* 524 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object $colon$plus(SeqLike $this, Object elem, CanBuildFrom bf) {
/* 528 */     Builder b = bf.apply($this.repr());
/* 529 */     b.$plus$plus$eq($this.thisCollection());
/* 530 */     b.$plus$eq(elem);
/* 531 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object padTo(SeqLike $this, int len, Object elem, CanBuildFrom bf) {
/* 535 */     Builder b = bf.apply($this.repr());
/* 536 */     int i = $this.length();
/* 536 */     Predef$ predef$ = Predef$.MODULE$;
/* 536 */     b.sizeHint(RichInt$.MODULE$.max$extension(i, len));
/* 537 */     int diff = len - $this.length();
/* 538 */     b.$plus$plus$eq($this.thisCollection());
/* 539 */     while (diff > 0) {
/* 540 */       b.$plus$eq(elem);
/* 541 */       diff--;
/*     */     } 
/* 543 */     return b.result();
/*     */   }
/*     */   
/*     */   public static boolean corresponds(SeqLike $this, GenSeq that, Function2 p) {
/* 547 */     Iterator i = $this.iterator();
/* 548 */     Iterator j = that.iterator();
/* 549 */     while (i.hasNext() && j.hasNext()) {
/* 550 */       if (!BoxesRunTime.unboxToBoolean(p.apply(i.next(), j.next())))
/* 551 */         return false; 
/*     */     } 
/* 553 */     return !(i.hasNext() || j.hasNext());
/*     */   }
/*     */   
/*     */   public static Object sortWith(SeqLike $this, Function2 lt) {
/* 572 */     Ordering$ ordering$ = Ordering$.MODULE$;
/* 572 */     return $this.sorted((Ordering)new Object(lt));
/*     */   }
/*     */   
/*     */   public static Object sortBy(SeqLike $this, Function1 f, Ordering ord) {
/* 594 */     return $this.sorted(ord.on(f));
/*     */   }
/*     */   
/*     */   public static Object sorted(SeqLike $this, Ordering ord) {
/* 608 */     int len = $this.length();
/* 609 */     ArraySeq arr = new ArraySeq(len);
/* 610 */     IntRef i = new IntRef(0);
/* 611 */     $this.seq().foreach((Function1)new SeqLike$$anonfun$sorted$1($this, arr, (SeqLike<A, Repr>)i));
/* 615 */     Arrays.sort(arr.array(), (Comparator<? super Object>)ord);
/* 616 */     Builder b = $this.newBuilder();
/* 617 */     b.sizeHint(len);
/* 618 */     arr.foreach((Function1)new SeqLike$$anonfun$sorted$2($this, (SeqLike<A, Repr>)b));
/* 619 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Seq toSeq(SeqLike $this) {
/* 627 */     return $this.thisCollection();
/*     */   }
/*     */   
/*     */   public static Range indices(SeqLike $this) {
/* 633 */     Predef$ predef$ = Predef$.MODULE$;
/* 633 */     return RichInt$.MODULE$.until$extension0(0, $this.length());
/*     */   }
/*     */   
/*     */   public static SeqView view(SeqLike<A, Repr> $this) {
/* 635 */     return new SeqLike$$anon$2($this);
/*     */   }
/*     */   
/*     */   public static SeqView view(SeqLike $this, int from, int until) {
/* 642 */     return (SeqView)$this.view().slice(from, until);
/*     */   }
/*     */   
/*     */   public static String toString(SeqLike $this) {
/* 646 */     return TraversableLike$class.toString($this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SeqLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */