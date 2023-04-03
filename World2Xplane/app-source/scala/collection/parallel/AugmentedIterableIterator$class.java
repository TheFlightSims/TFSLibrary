/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.package$;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ public abstract class AugmentedIterableIterator$class {
/*     */   public static void $init$(AugmentedIterableIterator $this) {}
/*     */   
/*     */   public static int count(AugmentedIterableIterator $this, Function1 p) {
/*  49 */     int i = 0;
/*  50 */     while ($this.hasNext()) {
/*  50 */       if (BoxesRunTime.unboxToBoolean(p.apply($this.next())))
/*  50 */         i++; 
/*     */     } 
/*  51 */     return i;
/*     */   }
/*     */   
/*     */   public static Object reduce(AugmentedIterableIterator $this, Function2 op) {
/*  55 */     Object r = $this.next();
/*  56 */     for (; $this.hasNext(); r = op.apply(r, $this.next()));
/*  57 */     return r;
/*     */   }
/*     */   
/*     */   public static Object fold(AugmentedIterableIterator $this, Object z, Function2 op) {
/*  61 */     Object r = z;
/*  62 */     for (; $this.hasNext(); r = op.apply(r, $this.next()));
/*  63 */     return r;
/*     */   }
/*     */   
/*     */   public static Object sum(AugmentedIterableIterator $this, Numeric num) {
/*  67 */     Object r = num.zero();
/*  68 */     for (; $this.hasNext(); r = num.plus(r, $this.next()));
/*  69 */     return r;
/*     */   }
/*     */   
/*     */   public static Object product(AugmentedIterableIterator $this, Numeric num) {
/*  73 */     Object r = num.one();
/*  74 */     for (; $this.hasNext(); r = num.times(r, $this.next()));
/*  75 */     return r;
/*     */   }
/*     */   
/*     */   public static Object min(AugmentedIterableIterator $this, Ordering ord) {
/*  79 */     Object r = $this.next();
/*  80 */     while ($this.hasNext()) {
/*  81 */       Object curr = $this.next();
/*  82 */       if (ord.lteq(curr, r))
/*  82 */         r = curr; 
/*     */     } 
/*  84 */     return r;
/*     */   }
/*     */   
/*     */   public static Object max(AugmentedIterableIterator $this, Ordering ord) {
/*  88 */     Object r = $this.next();
/*  89 */     while ($this.hasNext()) {
/*  90 */       Object curr = $this.next();
/*  91 */       if (ord.gteq(curr, r))
/*  91 */         r = curr; 
/*     */     } 
/*  93 */     return r;
/*     */   }
/*     */   
/*     */   public static void copyToArray(AugmentedIterableIterator $this, Object array, int from, int len) {
/*  97 */     int i = from;
/*  98 */     int until = from + len;
/*  99 */     while (i < until && $this.hasNext()) {
/* 100 */       ScalaRunTime$.MODULE$.array_update(array, i, $this.next());
/* 101 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object reduceLeft(AugmentedIterableIterator $this, int howmany, Function2 op) {
/* 106 */     int i = howmany - 1;
/* 107 */     Object u = $this.next();
/* 108 */     while (i > 0 && $this.hasNext()) {
/* 109 */       u = op.apply(u, $this.next());
/* 110 */       i--;
/*     */     } 
/* 112 */     return u;
/*     */   }
/*     */   
/*     */   public static Combiner map2combiner(AugmentedIterableIterator $this, Function1 f, Combiner cb) {
/* 119 */     if ($this.isRemainingCheap())
/* 119 */       cb.sizeHint($this.remaining()); 
/* 120 */     for (; $this.hasNext(); cb.$plus$eq(f.apply($this.next())));
/* 121 */     return cb;
/*     */   }
/*     */   
/*     */   public static Combiner collect2combiner(AugmentedIterableIterator $this, PartialFunction pf, Combiner cb) {
/* 126 */     while ($this.hasNext()) {
/* 127 */       Object curr = $this.next();
/* 128 */       pf.isDefinedAt(curr) ? cb.$plus$eq(pf.apply(curr)) : BoxedUnit.UNIT;
/*     */     } 
/* 130 */     return cb;
/*     */   }
/*     */   
/*     */   public static Combiner flatmap2combiner(AugmentedIterableIterator $this, Function1 f, Combiner cb) {
/* 135 */     while ($this.hasNext()) {
/* 136 */       TraversableOnce traversable = ((GenTraversableOnce)f.apply($this.next())).seq();
/* 137 */       (traversable instanceof Iterable) ? cb.$plus$plus$eq((TraversableOnce)((Iterable)traversable).iterator()) : 
/* 138 */         cb.$plus$plus$eq(traversable);
/*     */     } 
/* 140 */     return cb;
/*     */   }
/*     */   
/*     */   public static Builder copy2builder(AugmentedIterableIterator $this, Builder b) {
/* 144 */     if ($this.isRemainingCheap())
/* 144 */       b.sizeHint($this.remaining()); 
/* 145 */     for (; $this.hasNext(); b.$plus$eq($this.next()));
/* 146 */     return b;
/*     */   }
/*     */   
/*     */   public static Combiner filter2combiner(AugmentedIterableIterator $this, Function1 pred, Combiner cb) {
/* 150 */     while ($this.hasNext()) {
/* 151 */       Object curr = $this.next();
/* 152 */       BoxesRunTime.unboxToBoolean(pred.apply(curr)) ? cb.$plus$eq(curr) : BoxedUnit.UNIT;
/*     */     } 
/* 154 */     return cb;
/*     */   }
/*     */   
/*     */   public static Combiner filterNot2combiner(AugmentedIterableIterator $this, Function1 pred, Combiner cb) {
/* 158 */     while ($this.hasNext()) {
/* 159 */       Object curr = $this.next();
/* 160 */       BoxesRunTime.unboxToBoolean(pred.apply(curr)) ? BoxedUnit.UNIT : cb.$plus$eq(curr);
/*     */     } 
/* 162 */     return cb;
/*     */   }
/*     */   
/*     */   public static Tuple2 partition2combiners(AugmentedIterableIterator $this, Function1 pred, Combiner btrue, Combiner bfalse) {
/* 166 */     while ($this.hasNext()) {
/* 167 */       Object curr = $this.next();
/* 168 */       BoxesRunTime.unboxToBoolean(pred.apply(curr)) ? btrue.$plus$eq(curr) : 
/* 169 */         bfalse.$plus$eq(curr);
/*     */     } 
/* 171 */     return new Tuple2(btrue, bfalse);
/*     */   }
/*     */   
/*     */   public static Combiner take2combiner(AugmentedIterableIterator $this, int n, Combiner cb) {
/* 175 */     cb.sizeHint(n);
/* 176 */     int left = n;
/* 177 */     while (left > 0) {
/* 178 */       cb.$plus$eq($this.next());
/* 179 */       left--;
/*     */     } 
/* 181 */     return cb;
/*     */   }
/*     */   
/*     */   public static Combiner drop2combiner(AugmentedIterableIterator $this, int n, Combiner cb) {
/* 185 */     $this.drop(n);
/* 186 */     if ($this.isRemainingCheap())
/* 186 */       cb.sizeHint($this.remaining()); 
/* 187 */     for (; $this.hasNext(); cb.$plus$eq($this.next()));
/* 188 */     return cb;
/*     */   }
/*     */   
/*     */   public static Combiner slice2combiner(AugmentedIterableIterator $this, int from, int until, Combiner cb) {
/* 192 */     $this.drop(from);
/* 193 */     int left = package$.MODULE$.max(until - from, 0);
/* 194 */     cb.sizeHint(left);
/* 195 */     while (left > 0) {
/* 196 */       cb.$plus$eq($this.next());
/* 197 */       left--;
/*     */     } 
/* 199 */     return cb;
/*     */   }
/*     */   
/*     */   public static Tuple2 splitAt2combiners(AugmentedIterableIterator $this, int at, Combiner before, Combiner after) {
/* 203 */     before.sizeHint(at);
/* 204 */     if ($this.isRemainingCheap())
/* 204 */       after.sizeHint($this.remaining() - at); 
/* 205 */     int left = at;
/* 206 */     while (left > 0) {
/* 207 */       before.$plus$eq($this.next());
/* 208 */       left--;
/*     */     } 
/* 210 */     for (; $this.hasNext(); after.$plus$eq($this.next()));
/* 211 */     return new Tuple2(before, after);
/*     */   }
/*     */   
/*     */   public static Tuple2 takeWhile2combiner(AugmentedIterableIterator $this, Function1 p, Combiner cb) {
/* 215 */     boolean loop = true;
/* 216 */     while ($this.hasNext() && loop) {
/* 217 */       Object curr = $this.next();
/* 219 */       loop = false;
/* 219 */       BoxesRunTime.unboxToBoolean(p.apply(curr)) ? cb.$plus$eq(curr) : BoxedUnit.UNIT;
/*     */     } 
/* 221 */     return new Tuple2(cb, BoxesRunTime.boxToBoolean(loop));
/*     */   }
/*     */   
/*     */   public static Tuple2 span2combiners(AugmentedIterableIterator $this, Function1 p, Combiner before, Combiner after) {
/* 225 */     boolean isBefore = true;
/* 226 */     while ($this.hasNext() && isBefore) {
/* 227 */       Object curr = $this.next();
/* 230 */       if ($this.isRemainingCheap())
/* 230 */         after.sizeHint($this.remaining() + 1); 
/* 231 */       after.$plus$eq(curr);
/* 232 */       isBefore = false;
/*     */       BoxesRunTime.unboxToBoolean(p.apply(curr)) ? before.$plus$eq(curr) : BoxedUnit.UNIT;
/*     */     } 
/* 235 */     for (; $this.hasNext(); after.$plus$eq($this.next()));
/* 236 */     return new Tuple2(before, after);
/*     */   }
/*     */   
/*     */   public static void scanToArray(AugmentedIterableIterator $this, Object z, Function2 op, Object array, int from) {
/* 240 */     Object last = z;
/* 241 */     int i = from;
/* 242 */     while ($this.hasNext()) {
/* 243 */       last = op.apply(last, $this.next());
/* 244 */       ScalaRunTime$.MODULE$.array_update(array, i, last);
/* 245 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Combiner scanToCombiner(AugmentedIterableIterator $this, Object startValue, Function2 op, Combiner cb) {
/* 250 */     Object curr = startValue;
/* 251 */     while ($this.hasNext()) {
/* 252 */       curr = op.apply(curr, $this.next());
/* 253 */       cb.$plus$eq(curr);
/*     */     } 
/* 255 */     return cb;
/*     */   }
/*     */   
/*     */   public static Combiner scanToCombiner(AugmentedIterableIterator $this, int howmany, Object startValue, Function2 op, Combiner cb) {
/* 259 */     Object curr = startValue;
/* 260 */     int left = howmany;
/* 261 */     while (left > 0) {
/* 262 */       curr = op.apply(curr, $this.next());
/* 263 */       cb.$plus$eq(curr);
/* 264 */       left--;
/*     */     } 
/* 266 */     return cb;
/*     */   }
/*     */   
/*     */   public static Combiner zip2combiner(AugmentedIterableIterator $this, RemainsIterator otherpit, Combiner cb) {
/* 270 */     if ($this.isRemainingCheap() && otherpit.isRemainingCheap()) {
/* 270 */       int i = $this.remaining();
/* 270 */       Predef$ predef$ = Predef$.MODULE$;
/* 270 */       cb.sizeHint(RichInt$.MODULE$.min$extension(i, otherpit.remaining()));
/*     */     } 
/* 271 */     while ($this.hasNext() && otherpit.hasNext())
/* 272 */       cb.$plus$eq(new Tuple2($this.next(), otherpit.next())); 
/* 274 */     return cb;
/*     */   }
/*     */   
/*     */   public static Combiner zipAll2combiner(AugmentedIterableIterator $this, RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/* 278 */     if ($this.isRemainingCheap() && that.isRemainingCheap()) {
/* 278 */       int i = $this.remaining();
/* 278 */       Predef$ predef$ = Predef$.MODULE$;
/* 278 */       cb.sizeHint(RichInt$.MODULE$.max$extension(i, that.remaining()));
/*     */     } 
/* 279 */     for (; $this.hasNext() && that.hasNext(); cb.$plus$eq(new Tuple2($this.next(), that.next())));
/* 280 */     for (; $this.hasNext(); cb.$plus$eq(new Tuple2($this.next(), thatelem)));
/* 281 */     for (; that.hasNext(); cb.$plus$eq(new Tuple2(thiselem, that.next())));
/* 282 */     return cb;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\AugmentedIterableIterator$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */