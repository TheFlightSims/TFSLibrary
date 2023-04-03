/*      */ package scala.collection;
/*      */ 
/*      */ import scala.Function0;
/*      */ import scala.Function1;
/*      */ import scala.Function2;
/*      */ import scala.None$;
/*      */ import scala.Option;
/*      */ import scala.PartialFunction;
/*      */ import scala.Predef$;
/*      */ import scala.Some;
/*      */ import scala.StringContext;
/*      */ import scala.Tuple2;
/*      */ import scala.collection.immutable.Stream;
/*      */ import scala.collection.immutable.Stream$;
/*      */ import scala.collection.immutable.Stream$Empty$;
/*      */ import scala.collection.immutable.Stream$cons$;
/*      */ import scala.collection.mutable.Buffer$;
/*      */ import scala.collection.mutable.Queue;
/*      */ import scala.collection.mutable.StringBuilder;
/*      */ import scala.math.package$;
/*      */ import scala.runtime.BoxesRunTime;
/*      */ import scala.runtime.ObjectRef;
/*      */ import scala.runtime.RichInt$;
/*      */ import scala.runtime.ScalaRunTime$;
/*      */ 
/*      */ public abstract class Iterator$class {
/*      */   public static void $init$(Iterator $this) {}
/*      */   
/*      */   public static Iterator seq(Iterator $this) {
/*  233 */     return $this;
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(Iterator $this) {
/*  256 */     return !$this.hasNext();
/*      */   }
/*      */   
/*      */   public static boolean isTraversableAgain(Iterator $this) {
/*  263 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean hasDefiniteSize(Iterator $this) {
/*  270 */     return $this.isEmpty();
/*      */   }
/*      */   
/*      */   public static Iterator take(Iterator $this, int n) {
/*  279 */     return $this.slice(0, n);
/*      */   }
/*      */   
/*      */   public static Iterator drop(Iterator $this, int n) {
/*  288 */     return $this.slice(n, 2147483647);
/*      */   }
/*      */   
/*      */   public static Iterator slice(Iterator $this, int from, int until) {
/*  299 */     Predef$ predef$ = Predef$.MODULE$;
/*  299 */     int lo = RichInt$.MODULE$.max$extension(from, 0);
/*  300 */     int toDrop = lo;
/*  301 */     while (toDrop > 0 && $this.hasNext()) {
/*  302 */       $this.next();
/*  303 */       toDrop--;
/*      */     } 
/*  306 */     return new Iterator$$anon$10($this, lo, until);
/*      */   }
/*      */   
/*      */   public static Iterator map(Iterator $this, Function1 f) {
/*  326 */     return new Iterator$$anon$11($this, (Iterator<A>)f);
/*      */   }
/*      */   
/*      */   public static Iterator $plus$plus(Iterator $this, Function0 that) {
/*  341 */     return new Iterator$$anon$12($this, (Iterator<A>)that);
/*      */   }
/*      */   
/*      */   public static Iterator flatMap(Iterator $this, Function1 f) {
/*  368 */     return new Iterator$$anon$13($this, (Iterator<A>)f);
/*      */   }
/*      */   
/*      */   public static Iterator filter(Iterator $this, Function1 p) {
/*  382 */     return new Iterator$$anon$14($this, (Iterator<A>)p);
/*      */   }
/*      */   
/*      */   public static boolean corresponds(Iterator $this, GenTraversableOnce that, Function2 p) {
/*  409 */     Iterator that0 = that.toIterator();
/*  410 */     while ($this.hasNext() && that0.hasNext()) {
/*  411 */       if (!BoxesRunTime.unboxToBoolean(p.apply($this.next(), that0.next())))
/*  411 */         return false; 
/*      */     } 
/*  413 */     return ($this.hasNext() == that0.hasNext());
/*      */   }
/*      */   
/*      */   public static Iterator withFilter(Iterator $this, Function1 p) {
/*  427 */     return $this.filter(p);
/*      */   }
/*      */   
/*      */   public static Iterator filterNot(Iterator $this, Function1 p) {
/*  436 */     return $this.filter((Function1)new Iterator$$anonfun$filterNot$1($this, (Iterator<A>)p));
/*      */   }
/*      */   
/*      */   public static Iterator collect(Iterator $this, PartialFunction pf) {
/*  449 */     BufferedIterator self = $this.buffered();
/*  450 */     return new Iterator$$anon$15($this, self, (Iterator<A>)pf);
/*      */   }
/*      */   
/*      */   public static Iterator scanLeft(Iterator $this, Object z, Function2 op) {
/*  469 */     return new Iterator$$anon$16($this, z, (Iterator<A>)op);
/*      */   }
/*      */   
/*      */   public static Iterator scanRight(Iterator $this, Object z, Function2 op) {
/*  495 */     return ((IterableLike)$this.toBuffer().scanRight(z, op, Buffer$.MODULE$.canBuildFrom())).iterator();
/*      */   }
/*      */   
/*      */   public static Iterator takeWhile(Iterator $this, Function1 p) {
/*  505 */     return new Iterator$$anon$17($this, (Iterator<A>)p);
/*      */   }
/*      */   
/*      */   public static Tuple2 partition(Iterator $this, Function1 p) {
/*  529 */     BufferedIterator self = $this.buffered();
/*  541 */     Iterator$PartitionIterator$1 l = new Iterator$PartitionIterator$1($this, (Iterator<A>)p, (Function1<A, Object>)self);
/*  542 */     Iterator$PartitionIterator$1 r = new Iterator$PartitionIterator$1($this, (Iterator<A>)new Iterator$$anonfun$1($this, (Iterator<A>)p), (Function1<A, Object>)self);
/*  543 */     l.other_$eq(r);
/*  544 */     r.other_$eq(l);
/*  545 */     return new Tuple2(l, r);
/*      */   }
/*      */   
/*      */   public static Tuple2 span(Iterator $this, Function1 p) {
/*  556 */     BufferedIterator self = $this.buffered();
/*  585 */     Iterator$Leading$1 leading = new Iterator$Leading$1($this, self, (Iterator<A>)p);
/*  586 */     AbstractIterator trailing = new Iterator$$anon$18($this, self, leading);
/*  596 */     return new Tuple2(leading, trailing);
/*      */   }
/*      */   
/*      */   public static Iterator dropWhile(Iterator $this, Function1 p) {
/*  607 */     BufferedIterator self = $this.buffered();
/*  608 */     return new Iterator$$anon$19($this, self, (Iterator<A>)p);
/*      */   }
/*      */   
/*      */   public static Iterator zip(Iterator $this, Iterator<A> that) {
/*  633 */     return new Iterator$$anon$20($this, that);
/*      */   }
/*      */   
/*      */   public static Iterator padTo(Iterator $this, int len, Object elem) {
/*  650 */     return new Iterator$$anon$21($this, len, (Iterator<A>)elem);
/*      */   }
/*      */   
/*      */   public static Iterator zipWithIndex(Iterator<A> $this) {
/*  668 */     return new Iterator$$anon$22($this);
/*      */   }
/*      */   
/*      */   public static Iterator zipAll(Iterator $this, Iterator that, Object thisElem, Object thatElem) {
/*  701 */     return new Iterator$$anon$23($this, that, thisElem, (Iterator<A>)thatElem);
/*      */   }
/*      */   
/*      */   public static void foreach(Iterator $this, Function1 f) {
/*  727 */     for (; $this.hasNext(); f.apply($this.next()));
/*      */   }
/*      */   
/*      */   public static boolean forall(Iterator $this, Function1 p) {
/*  738 */     boolean res = true;
/*  739 */     for (; res && $this.hasNext(); res = BoxesRunTime.unboxToBoolean(p.apply($this.next())));
/*  740 */     return res;
/*      */   }
/*      */   
/*      */   public static boolean exists(Iterator $this, Function1 p) {
/*  752 */     boolean res = false;
/*  753 */     for (; !res && $this.hasNext(); res = BoxesRunTime.unboxToBoolean(p.apply($this.next())));
/*  754 */     return res;
/*      */   }
/*      */   
/*      */   public static boolean contains(Iterator $this, Object elem) {
/*  765 */     return $this.exists((Function1)new Iterator$$anonfun$contains$1($this, (Iterator<A>)elem));
/*      */   }
/*      */   
/*      */   public static Option find(Iterator $this, Function1 p) {
/*      */     Some some;
/*  777 */     None$ none$ = None$.MODULE$;
/*  778 */     while (none$.isEmpty() && $this.hasNext()) {
/*  779 */       Object e = $this.next();
/*  780 */       if (BoxesRunTime.unboxToBoolean(p.apply(e)))
/*  780 */         some = new Some(e); 
/*      */     } 
/*  782 */     return (Option)some;
/*      */   }
/*      */   
/*      */   public static int indexWhere(Iterator $this, Function1 p) {
/*  794 */     int i = 0;
/*  795 */     boolean found = false;
/*  796 */     while (!found && $this.hasNext()) {
/*  797 */       if (BoxesRunTime.unboxToBoolean(p.apply($this.next()))) {
/*  798 */         found = true;
/*      */         continue;
/*      */       } 
/*  800 */       i++;
/*      */     } 
/*  803 */     return found ? i : -1;
/*      */   }
/*      */   
/*      */   public static int indexOf(Iterator<Object> $this, Object elem) {
/*  816 */     int i = 0;
/*  817 */     boolean found = false;
/*  818 */     while (!found && $this.hasNext()) {
/*      */       Number number;
/*  819 */       if (((number = (Number)$this.next()) == elem) ? true : ((number == null) ? false : ((number instanceof Number) ? BoxesRunTime.equalsNumObject(number, elem) : ((number instanceof Character) ? BoxesRunTime.equalsCharObject((Character)number, elem) : number.equals(elem))))) {
/*  820 */         found = true;
/*      */         continue;
/*      */       } 
/*  822 */       i++;
/*      */     } 
/*  825 */     return found ? i : -1;
/*      */   }
/*      */   
/*      */   public static BufferedIterator buffered(Iterator<A> $this) {
/*  834 */     return new Iterator$$anon$1($this);
/*      */   }
/*      */   
/*      */   public static Iterator.GroupedIterator grouped(Iterator $this, int size) {
/* 1000 */     return new Iterator.GroupedIterator($this, $this, size, size);
/*      */   }
/*      */   
/*      */   public static int sliding$default$2(Iterator $this) {
/* 1021 */     return 1;
/*      */   }
/*      */   
/*      */   public static Iterator.GroupedIterator sliding(Iterator $this, int size, int step) {
/* 1022 */     return new Iterator.GroupedIterator($this, $this, size, step);
/*      */   }
/*      */   
/*      */   public static int length(Iterator $this) {
/* 1029 */     return $this.size();
/*      */   }
/*      */   
/*      */   public static Tuple2 duplicate(Iterator $this) {
/* 1045 */     Queue gap = new Queue();
/* 1046 */     ObjectRef ahead = new ObjectRef(null);
/* 1068 */     return new Tuple2(new Iterator$Partner$1($this, gap, (Iterator<A>)ahead), new Iterator$Partner$1($this, gap, (Iterator<A>)ahead));
/*      */   }
/*      */   
/*      */   public static Iterator patch(Iterator $this, int from, Iterator patchElems, int replaced) {
/* 1078 */     return new Iterator$$anon$24($this, from, patchElems, replaced);
/*      */   }
/*      */   
/*      */   public static void copyToArray(Iterator $this, Object xs, int start, int len) {
/* 1114 */     boolean bool = (start >= 0 && (start < ScalaRunTime$.MODULE$.array_length(xs) || ScalaRunTime$.MODULE$.array_length(xs) == 0)) ? true : false;
/* 1114 */     Predef$ predef$ = Predef$.MODULE$;
/* 1114 */     if (bool) {
/* 1115 */       int i = start;
/* 1116 */       int j = ScalaRunTime$.MODULE$.array_length(xs) - start;
/* 1116 */       package$ package$ = package$.MODULE$;
/* 1116 */       int end = start + Math.min(len, j);
/* 1117 */       while (i < end && $this.hasNext()) {
/* 1118 */         ScalaRunTime$.MODULE$.array_update(xs, i, $this.next());
/* 1119 */         i++;
/*      */       } 
/*      */       return;
/*      */     } 
/*      */     (new String[3])[0] = "start ";
/*      */     (new String[3])[1] = " out of range ";
/*      */     (new String[3])[2] = "";
/*      */     throw new IllegalArgumentException((new StringBuilder()).append("requirement failed: ").append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(start), BoxesRunTime.boxToInteger(ScalaRunTime$.MODULE$.array_length(xs)) }))).toString());
/*      */   }
/*      */   
/*      */   public static boolean sameElements(Iterator<Object> $this, Iterator<Object> that) {
/* 1133 */     while ($this.hasNext() && that.hasNext()) {
/* 1134 */       Object object = that.next();
/*      */       Number number;
/* 1134 */       if (!(((number = (Number)$this.next()) == object) ? 1 : ((number == null) ? 0 : ((number instanceof Number) ? BoxesRunTime.equalsNumObject(number, object) : ((number instanceof Character) ? BoxesRunTime.equalsCharObject((Character)number, object) : number.equals(object))))))
/* 1135 */         return false; 
/*      */     } 
/* 1137 */     return !($this.hasNext() || that.hasNext());
/*      */   }
/*      */   
/*      */   public static Traversable toTraversable(Iterator $this) {
/* 1140 */     return (Traversable)$this.toStream();
/*      */   }
/*      */   
/*      */   public static Iterator toIterator(Iterator $this) {
/* 1141 */     return $this;
/*      */   }
/*      */   
/*      */   public static Stream toStream(Iterator<A> $this) {
/* 1143 */     Iterator$$anonfun$toStream$1 iterator$$anonfun$toStream$1 = new Iterator$$anonfun$toStream$1($this);
/* 1143 */     A a = $this.next();
/* 1143 */     Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
/* 1144 */     Stream$ stream$ = Stream$.MODULE$;
/* 1144 */     return $this.hasNext() ? (Stream)new Stream.Cons(a, (Function0)iterator$$anonfun$toStream$1) : (Stream)Stream$Empty$.MODULE$;
/*      */   }
/*      */   
/*      */   public static String toString(Iterator $this) {
/* 1153 */     return (new StringBuilder()).append($this.hasNext() ? "non-empty" : "empty").append(" iterator").toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Iterator$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */