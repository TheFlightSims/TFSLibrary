/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParMap$;
/*     */ 
/*     */ public abstract class MapLike$class {
/*     */   public static void $init$(MapLike $this) {}
/*     */   
/*     */   public static Combiner parCombiner(MapLike $this) {
/*  54 */     return ParMap$.MODULE$.newCombiner();
/*     */   }
/*     */   
/*     */   public static Map updated(MapLike $this, Object key, Object value) {
/*  61 */     return $this.$plus(new Tuple2(key, value));
/*     */   }
/*     */   
/*     */   public static Map $plus(MapLike $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
/*  78 */     return $this.$plus(elem1).$plus(elem2).$plus$plus((GenTraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static Map $plus$plus(MapLike<A, B, This> $this, GenTraversableOnce xs) {
/*  87 */     Map map = (Map)$this.repr();
/*  87 */     return (Map)xs.seq().$div$colon(map, (Function2)new MapLike$$anonfun$$plus$plus$1($this));
/*     */   }
/*     */   
/*     */   public static Map filterKeys(MapLike $this, Function1 p) {
/*  94 */     return new MapLike$$anon$1($this, (MapLike<A, B, This>)p);
/*     */   }
/*     */   
/*     */   public static Map mapValues(MapLike $this, Function1 f) {
/* 101 */     return new MapLike$$anon$2($this, (MapLike<A, B, This>)f);
/*     */   }
/*     */   
/*     */   public static Set keySet(MapLike<A, B, This> $this) {
/* 106 */     return new MapLike.ImmutableDefaultKeySet($this);
/*     */   }
/*     */   
/*     */   public static Object transform(MapLike<A, B, This> $this, Function2 f, CanBuildFrom bf) {
/* 124 */     Builder b = bf.apply($this.repr());
/* 125 */     $this.withFilter((Function1)new MapLike$$anonfun$transform$1($this)).foreach((Function1)new MapLike$$anonfun$transform$2($this, b, (MapLike<A, B, This>)f));
/* 126 */     return b.result();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\MapLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */