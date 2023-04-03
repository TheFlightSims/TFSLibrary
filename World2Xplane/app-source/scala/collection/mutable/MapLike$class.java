/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParMap$;
/*     */ 
/*     */ public abstract class MapLike$class {
/*     */   public static void $init$(MapLike $this) {}
/*     */   
/*     */   public static Builder newBuilder(MapLike $this) {
/*  60 */     return (Builder)$this.empty();
/*     */   }
/*     */   
/*     */   public static Combiner parCombiner(MapLike $this) {
/*  62 */     return ParMap$.MODULE$.newCombiner();
/*     */   }
/*     */   
/*     */   public static Option put(MapLike $this, Object key, Object value) {
/*  75 */     Option r = $this.get(key);
/*  76 */     $this.update(key, value);
/*  77 */     return r;
/*     */   }
/*     */   
/*     */   public static void update(MapLike $this, Object key, Object value) {
/*  87 */     $this.$plus$eq(new Tuple2(key, value));
/*     */   }
/*     */   
/*     */   public static Map updated(MapLike $this, Object key, Object value) {
/* 105 */     return $this.$plus(new Tuple2(key, value));
/*     */   }
/*     */   
/*     */   public static Map $plus(MapLike $this, Tuple2 kv) {
/* 116 */     return (Map)$this.clone().$plus$eq(kv);
/*     */   }
/*     */   
/*     */   public static Map $plus(MapLike $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 130 */     return (Map)$this.clone().$plus$eq(elem1).$plus$eq(elem2).$plus$plus$eq((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static Map $plus$plus(MapLike $this, GenTraversableOnce xs) {
/* 142 */     return (Map)$this.clone().$plus$plus$eq(xs.seq());
/*     */   }
/*     */   
/*     */   public static Option remove(MapLike $this, Object key) {
/* 151 */     Option r = $this.get(key);
/* 152 */     $this.$minus$eq(key);
/* 153 */     return r;
/*     */   }
/*     */   
/*     */   public static Map $minus(MapLike $this, Object key) {
/* 169 */     return (Map)$this.clone().$minus$eq(key);
/*     */   }
/*     */   
/*     */   public static void clear(MapLike<A, B, This> $this) {
/* 174 */     $this.keysIterator().foreach((Function1)new MapLike$$anonfun$clear$1($this));
/*     */   }
/*     */   
/*     */   public static Object getOrElseUpdate(MapLike $this, Object key, Function0 op) {
/* 187 */     Option option = $this.get(key);
/* 188 */     if (option instanceof Some) {
/* 188 */       Some some = (Some)option;
/* 188 */       Object object = some.x();
/*     */     } 
/* 189 */     if (None$.MODULE$ == null) {
/* 189 */       if (option != null)
/*     */         throw new MatchError(option); 
/* 189 */     } else if (!None$.MODULE$.equals(option)) {
/*     */       throw new MatchError(option);
/*     */     } 
/* 189 */     Object d = op.apply();
/* 189 */     $this.update(key, d);
/* 189 */     return d;
/*     */   }
/*     */   
/*     */   public static MapLike transform(MapLike $this, Function2 f) {
/* 200 */     $this.iterator().foreach((Function1)new MapLike$$anonfun$transform$1($this, (MapLike<A, B, This>)f));
/* 203 */     return $this;
/*     */   }
/*     */   
/*     */   public static MapLike retain(MapLike<A, B, This> $this, Function2 p) {
/* 212 */     $this.toList().withFilter((Function1)new MapLike$$anonfun$retain$1($this)).foreach((Function1)new MapLike$$anonfun$retain$2($this, (MapLike<A, B, This>)p));
/* 215 */     return $this;
/*     */   }
/*     */   
/*     */   public static Map clone(MapLike $this) {
/* 218 */     return (Map)((Growable)$this.empty()).$plus$plus$eq((TraversableOnce)$this.repr());
/*     */   }
/*     */   
/*     */   public static Map result(MapLike $this) {
/* 223 */     return (Map)$this.repr();
/*     */   }
/*     */   
/*     */   public static Map $minus(MapLike $this, Object elem1, Object elem2, Seq elems) {
/* 236 */     return (Map)$this.clone().$minus$eq(elem1).$minus$eq(elem2).$minus$minus$eq((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static Map $minus$minus(MapLike $this, GenTraversableOnce xs) {
/* 246 */     return (Map)$this.clone().$minus$minus$eq(xs.seq());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\MapLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */