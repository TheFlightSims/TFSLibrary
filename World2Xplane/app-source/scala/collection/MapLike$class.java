/*     */ package scala.collection;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.MapBuilder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParMap$;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ public abstract class MapLike$class {
/*     */   public static void $init$(MapLike $this) {}
/*     */   
/*     */   public static Builder newBuilder(MapLike $this) {
/*  74 */     return (Builder)new MapBuilder((GenMap)$this.empty());
/*     */   }
/*     */   
/*     */   public static boolean isEmpty(MapLike $this) {
/* 113 */     return ($this.size() == 0);
/*     */   }
/*     */   
/*     */   public static Object getOrElse(MapLike $this, Object key, Function0 default) {
/* 126 */     Option option = $this.get(key);
/* 127 */     if (option instanceof Some) {
/* 127 */       Some some = (Some)option;
/* 127 */       Object object = some.x();
/*     */     } 
/* 128 */     if (None$.MODULE$ == null) {
/* 128 */       if (option != null)
/*     */         throw new MatchError(option); 
/* 128 */     } else if (!None$.MODULE$.equals(option)) {
/*     */       throw new MatchError(option);
/*     */     } 
/* 128 */     return default.apply();
/*     */   }
/*     */   
/*     */   public static Object apply(MapLike $this, Object key) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   7: astore #4
/*     */     //   9: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   12: dup
/*     */     //   13: ifnonnull -> 25
/*     */     //   16: pop
/*     */     //   17: aload #4
/*     */     //   19: ifnull -> 33
/*     */     //   22: goto -> 44
/*     */     //   25: aload #4
/*     */     //   27: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   30: ifeq -> 44
/*     */     //   33: aload_0
/*     */     //   34: aload_1
/*     */     //   35: invokeinterface default : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   40: astore_3
/*     */     //   41: goto -> 63
/*     */     //   44: aload #4
/*     */     //   46: instanceof scala/Some
/*     */     //   49: ifeq -> 65
/*     */     //   52: aload #4
/*     */     //   54: checkcast scala/Some
/*     */     //   57: astore_2
/*     */     //   58: aload_2
/*     */     //   59: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   62: astore_3
/*     */     //   63: aload_3
/*     */     //   64: areturn
/*     */     //   65: new scala/MatchError
/*     */     //   68: dup
/*     */     //   69: aload #4
/*     */     //   71: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   74: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #140	-> 0
/*     */     //   #141	-> 9
/*     */     //   #142	-> 44
/*     */     //   #140	-> 58
/*     */     //   #142	-> 59
/*     */     //   #140	-> 63
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	75	0	$this	Lscala/collection/MapLike;
/*     */     //   0	75	1	key	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public static boolean contains(MapLike $this, Object key) {
/* 150 */     return $this.get(key).isDefined();
/*     */   }
/*     */   
/*     */   public static boolean isDefinedAt(MapLike $this, Object key) {
/* 159 */     return $this.contains(key);
/*     */   }
/*     */   
/*     */   public static Set keySet(MapLike<A, B, This> $this) {
/* 164 */     return new MapLike.DefaultKeySet($this);
/*     */   }
/*     */   
/*     */   public static Iterator keysIterator(MapLike<A, B, This> $this) {
/* 181 */     return new MapLike$$anon$1($this);
/*     */   }
/*     */   
/*     */   public static Iterable keys(MapLike $this) {
/* 192 */     return $this.keySet();
/*     */   }
/*     */   
/*     */   public static Iterable values(MapLike<A, B, This> $this) {
/* 199 */     return new MapLike.DefaultValuesIterable($this);
/*     */   }
/*     */   
/*     */   public static Iterator valuesIterator(MapLike<A, B, This> $this) {
/* 213 */     return new MapLike$$anon$2($this);
/*     */   }
/*     */   
/*     */   public static Object default(MapLike $this, Object key) {
/* 228 */     throw new NoSuchElementException((new StringBuilder()).append("key not found: ").append(key).toString());
/*     */   }
/*     */   
/*     */   public static Map filterKeys(MapLike<A, B, This> $this, Function1<A, Object> p) {
/* 242 */     return new MapLike.FilteredKeys($this, p);
/*     */   }
/*     */   
/*     */   public static Map mapValues(MapLike $this, Function1 f) {
/* 257 */     return new MapLike.MappedValues($this, f);
/*     */   }
/*     */   
/*     */   public static Map updated(MapLike $this, Object key, Object value) {
/* 272 */     return $this.$plus(new Tuple2(key, value));
/*     */   }
/*     */   
/*     */   public static Map $plus(MapLike $this, Tuple2 kv1, Tuple2 kv2, Seq kvs) {
/* 290 */     return $this.$plus(kv1).$plus(kv2).$plus$plus(kvs);
/*     */   }
/*     */   
/*     */   public static Map $plus$plus(MapLike<A, B, This> $this, GenTraversableOnce xs) {
/* 302 */     Map map = (Map)$this.repr();
/* 302 */     return (Map)xs.seq().$div$colon(map, (Function2)new MapLike$$anonfun$$plus$plus$1($this));
/*     */   }
/*     */   
/*     */   public static Map filterNot(MapLike $this, Function1 p) {
/* 316 */     ObjectRef res = new ObjectRef($this.repr());
/* 317 */     $this.foreach((Function1)new MapLike$$anonfun$filterNot$1($this, res, (MapLike<A, B, This>)p));
/* 319 */     return (Map)res.elem;
/*     */   }
/*     */   
/*     */   public static Seq toSeq(MapLike $this) {
/* 323 */     return (Seq)$this.toBuffer();
/*     */   }
/*     */   
/*     */   public static Buffer toBuffer(MapLike $this) {
/* 325 */     ArrayBuffer result = new ArrayBuffer($this.size());
/* 326 */     $this.copyToBuffer((Buffer)result);
/* 327 */     return (Buffer)result;
/*     */   }
/*     */   
/*     */   public static Combiner parCombiner(MapLike $this) {
/* 330 */     return ParMap$.MODULE$.newCombiner();
/*     */   }
/*     */   
/*     */   public static StringBuilder addString(MapLike<A, B, This> $this, StringBuilder b, String start, String sep, String end) {
/* 344 */     return $this.iterator().map((Function1)new MapLike$$anonfun$addString$1($this)).addString(b, start, sep, end);
/*     */   }
/*     */   
/*     */   public static String stringPrefix(MapLike $this) {
/* 350 */     return "Map";
/*     */   }
/*     */   
/*     */   public static String toString(MapLike $this) {
/* 353 */     return TraversableLike$class.toString($this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\MapLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */