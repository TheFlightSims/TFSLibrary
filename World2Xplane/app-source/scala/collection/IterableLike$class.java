/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.math.package$;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ public abstract class IterableLike$class {
/*     */   public static void $init$(IterableLike $this) {}
/*     */   
/*     */   public static Iterable thisCollection(IterableLike $this) {
/*  54 */     return (Iterable)$this;
/*     */   }
/*     */   
/*     */   public static Iterable toCollection(IterableLike $this, Object repr) {
/*  55 */     return (Iterable)repr;
/*     */   }
/*     */   
/*     */   public static void foreach(IterableLike $this, Function1 f) {
/*  72 */     $this.iterator().foreach(f);
/*     */   }
/*     */   
/*     */   public static boolean forall(IterableLike $this, Function1 p) {
/*  75 */     return $this.iterator().forall(p);
/*     */   }
/*     */   
/*     */   public static boolean exists(IterableLike $this, Function1 p) {
/*  77 */     return $this.iterator().exists(p);
/*     */   }
/*     */   
/*     */   public static Option find(IterableLike $this, Function1 p) {
/*  79 */     return $this.iterator().find(p);
/*     */   }
/*     */   
/*     */   public static boolean isEmpty(IterableLike $this) {
/*  81 */     return !$this.iterator().hasNext();
/*     */   }
/*     */   
/*     */   public static Object foldRight(IterableLike $this, Object z, Function2 op) {
/*  83 */     return $this.iterator().foldRight(z, op);
/*     */   }
/*     */   
/*     */   public static Object reduceRight(IterableLike $this, Function2 op) {
/*  85 */     return $this.iterator().reduceRight(op);
/*     */   }
/*     */   
/*     */   public static Iterable toIterable(IterableLike $this) {
/*  87 */     return $this.thisCollection();
/*     */   }
/*     */   
/*     */   public static Iterator toIterator(IterableLike $this) {
/*  89 */     return $this.iterator();
/*     */   }
/*     */   
/*     */   public static Object head(IterableLike $this) {
/*  91 */     return $this.iterator().next();
/*     */   }
/*     */   
/*     */   public static Object slice(IterableLike $this, int from, int until) {
/*  94 */     int lo = package$.MODULE$.max(from, 0);
/*  95 */     int elems = until - lo;
/*  96 */     Builder b = $this.newBuilder();
/*  99 */     b.sizeHintBounded(elems, $this);
/* 100 */     int i = 0;
/* 101 */     Iterator it = $this.iterator().drop(lo);
/* 102 */     while (i < elems && it.hasNext()) {
/* 103 */       b.$plus$eq(it.next());
/* 104 */       i++;
/*     */     } 
/* 106 */     return (elems <= 0) ? b.result() : b.result();
/*     */   }
/*     */   
/*     */   public static Object take(IterableLike $this, int n) {
/* 111 */     Builder b = $this.newBuilder();
/* 115 */     b.sizeHintBounded(n, $this);
/* 116 */     int i = 0;
/* 117 */     Iterator it = $this.iterator();
/* 118 */     while (i < n && it.hasNext()) {
/* 119 */       b.$plus$eq(it.next());
/* 120 */       i++;
/*     */     } 
/* 122 */     return (n <= 0) ? b.result() : b.result();
/*     */   }
/*     */   
/*     */   public static Object drop(IterableLike $this, int n) {
/* 127 */     Builder b = $this.newBuilder();
/* 128 */     int lo = package$.MODULE$.max(0, n);
/* 129 */     b.sizeHint($this, -lo);
/* 130 */     int i = 0;
/* 131 */     Iterator it = $this.iterator();
/* 132 */     while (i < n && it.hasNext()) {
/* 133 */       it.next();
/* 134 */       i++;
/*     */     } 
/* 136 */     return ((Builder)b.$plus$plus$eq(it)).result();
/*     */   }
/*     */   
/*     */   public static Object takeWhile(IterableLike $this, Function1 p) {
/* 140 */     Builder b = $this.newBuilder();
/* 141 */     Iterator it = $this.iterator();
/* 142 */     while (it.hasNext()) {
/* 143 */       Object x = it.next();
/* 144 */       if (BoxesRunTime.unboxToBoolean(p.apply(x))) {
/* 145 */         b.$plus$eq(x);
/*     */         continue;
/*     */       } 
/*     */       return b.result();
/*     */     } 
/* 147 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Iterator grouped(IterableLike<A, Repr> $this, int size) {
/* 158 */     return $this.iterator().grouped(size).map((Function1)new IterableLike$$anonfun$grouped$1($this));
/*     */   }
/*     */   
/*     */   public static Iterator sliding(IterableLike $this, int size) {
/* 173 */     return $this.sliding(size, 1);
/*     */   }
/*     */   
/*     */   public static Iterator sliding(IterableLike<A, Repr> $this, int size, int step) {
/* 187 */     return $this.iterator().sliding(size, step).map((Function1)new IterableLike$$anonfun$sliding$1($this));
/*     */   }
/*     */   
/*     */   public static Object takeRight(IterableLike $this, int n) {
/* 201 */     Builder b = $this.newBuilder();
/* 202 */     b.sizeHintBounded(n, $this);
/* 203 */     Iterator lead = $this.iterator().drop(n);
/* 204 */     BooleanRef go = new BooleanRef(false);
/* 205 */     $this.seq().foreach((Function1)new IterableLike$$anonfun$takeRight$1($this, b, lead, (IterableLike<A, Repr>)go));
/* 210 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object dropRight(IterableLike $this, int n) {
/* 221 */     Builder b = $this.newBuilder();
/* 222 */     if (n >= 0)
/* 222 */       b.sizeHint($this, -n); 
/* 223 */     Iterator lead = $this.iterator().drop(n);
/* 224 */     Iterator it = $this.iterator();
/* 225 */     while (lead.hasNext()) {
/* 226 */       b.$plus$eq(it.next());
/* 227 */       lead.next();
/*     */     } 
/* 229 */     return b.result();
/*     */   }
/*     */   
/*     */   public static void copyToArray(IterableLike $this, Object xs, int start, int len) {
/* 233 */     int i = start;
/* 234 */     int j = start + len;
/* 234 */     Predef$ predef$ = Predef$.MODULE$;
/* 234 */     int end = RichInt$.MODULE$.min$extension(j, ScalaRunTime$.MODULE$.array_length(xs));
/* 235 */     Iterator it = $this.iterator();
/* 236 */     while (i < end && it.hasNext()) {
/* 237 */       ScalaRunTime$.MODULE$.array_update(xs, i, it.next());
/* 238 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object zip(IterableLike $this, GenIterable that, CanBuildFrom bf) {
/* 243 */     Builder b = bf.apply($this.repr());
/* 244 */     Iterator these = $this.iterator();
/* 245 */     Iterator those = that.iterator();
/* 246 */     while (these.hasNext() && those.hasNext())
/* 247 */       b.$plus$eq(new Tuple2(these.next(), those.next())); 
/* 248 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object zipAll(IterableLike $this, GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 252 */     Builder b = bf.apply($this.repr());
/* 253 */     Iterator these = $this.iterator();
/* 254 */     Iterator those = that.iterator();
/* 255 */     while (these.hasNext() && those.hasNext())
/* 256 */       b.$plus$eq(new Tuple2(these.next(), those.next())); 
/* 257 */     while (these.hasNext())
/* 258 */       b.$plus$eq(new Tuple2(these.next(), thatElem)); 
/* 259 */     while (those.hasNext())
/* 260 */       b.$plus$eq(new Tuple2(thisElem, those.next())); 
/* 261 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Object zipWithIndex(IterableLike $this, CanBuildFrom bf) {
/* 265 */     Builder b = bf.apply($this.repr());
/* 266 */     IntRef i = new IntRef(0);
/* 267 */     $this.foreach((Function1)new IterableLike$$anonfun$zipWithIndex$1($this, b, (IterableLike<A, Repr>)i));
/* 271 */     return b.result();
/*     */   }
/*     */   
/*     */   public static boolean sameElements(IterableLike $this, GenIterable that) {
/* 275 */     Iterator<Object> these = $this.iterator();
/* 276 */     Iterator<Object> those = that.iterator();
/* 277 */     while (these.hasNext() && those.hasNext()) {
/* 278 */       Object object = those.next();
/*     */       Number number;
/* 278 */       if (!(((number = (Number)these.next()) == object) ? 1 : ((number == null) ? 0 : ((number instanceof Number) ? BoxesRunTime.equalsNumObject(number, object) : ((number instanceof Character) ? BoxesRunTime.equalsCharObject((Character)number, object) : number.equals(object))))))
/* 279 */         return false; 
/*     */     } 
/* 281 */     return !(these.hasNext() || those.hasNext());
/*     */   }
/*     */   
/*     */   public static Stream toStream(IterableLike $this) {
/* 284 */     return $this.iterator().toStream();
/*     */   }
/*     */   
/*     */   public static boolean canEqual(IterableLike $this, Object that) {
/* 292 */     return true;
/*     */   }
/*     */   
/*     */   public static IterableView view(IterableLike<A, Repr> $this) {
/* 294 */     return new IterableLike$$anon$1($this);
/*     */   }
/*     */   
/*     */   public static IterableView view(IterableLike $this, int from, int until) {
/* 299 */     return (IterableView)$this.view().slice(from, until);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IterableLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */