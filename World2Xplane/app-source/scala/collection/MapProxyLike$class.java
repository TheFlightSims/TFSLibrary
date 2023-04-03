/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ 
/*    */ public abstract class MapProxyLike$class {
/*    */   public static void $init$(MapProxyLike $this) {}
/*    */   
/*    */   public static Option get(MapProxyLike $this, Object key) {
/* 26 */     return ((MapLike)$this.self()).get(key);
/*    */   }
/*    */   
/*    */   public static Iterator iterator(MapProxyLike $this) {
/* 27 */     return ((MapLike)$this.self()).iterator();
/*    */   }
/*    */   
/*    */   public static Map $plus(MapProxyLike $this, Tuple2 kv) {
/* 28 */     return ((MapLike)$this.self()).$plus(kv);
/*    */   }
/*    */   
/*    */   public static Map $minus(MapProxyLike $this, Object key) {
/* 29 */     return (Map)((MapLike)$this.self()).$minus(key);
/*    */   }
/*    */   
/*    */   public static boolean isEmpty(MapProxyLike $this) {
/* 30 */     return ((MapLike)$this.self()).isEmpty();
/*    */   }
/*    */   
/*    */   public static Object getOrElse(MapProxyLike $this, Object key, Function0 default) {
/* 31 */     return ((MapLike)$this.self()).getOrElse(key, default);
/*    */   }
/*    */   
/*    */   public static Object apply(MapProxyLike $this, Object key) {
/* 32 */     return ((MapLike)$this.self()).apply(key);
/*    */   }
/*    */   
/*    */   public static boolean contains(MapProxyLike $this, Object key) {
/* 33 */     return ((MapLike)$this.self()).contains(key);
/*    */   }
/*    */   
/*    */   public static boolean isDefinedAt(MapProxyLike $this, Object key) {
/* 34 */     return ((MapLike)$this.self()).isDefinedAt(key);
/*    */   }
/*    */   
/*    */   public static Set keySet(MapProxyLike $this) {
/* 35 */     return ((MapLike)$this.self()).keySet();
/*    */   }
/*    */   
/*    */   public static Iterator keysIterator(MapProxyLike $this) {
/* 36 */     return ((MapLike)$this.self()).keysIterator();
/*    */   }
/*    */   
/*    */   public static Iterable keys(MapProxyLike $this) {
/* 37 */     return ((MapLike)$this.self()).keys();
/*    */   }
/*    */   
/*    */   public static Iterable values(MapProxyLike $this) {
/* 38 */     return ((MapLike)$this.self()).values();
/*    */   }
/*    */   
/*    */   public static Iterator valuesIterator(MapProxyLike $this) {
/* 39 */     return ((MapLike)$this.self()).valuesIterator();
/*    */   }
/*    */   
/*    */   public static Object default(MapProxyLike $this, Object key) {
/* 40 */     return ((MapLike)$this.self()).default(key);
/*    */   }
/*    */   
/*    */   public static Map filterKeys(MapProxyLike $this, Function1 p) {
/* 41 */     return ((MapLike)$this.self()).filterKeys(p);
/*    */   }
/*    */   
/*    */   public static Map mapValues(MapProxyLike $this, Function1 f) {
/* 42 */     return ((MapLike)$this.self()).mapValues(f);
/*    */   }
/*    */   
/*    */   public static Map updated(MapProxyLike $this, Object key, Object value) {
/* 43 */     return ((MapLike)$this.self()).updated(key, value);
/*    */   }
/*    */   
/*    */   public static Map $plus(MapProxyLike $this, Tuple2 kv1, Tuple2 kv2, Seq kvs) {
/* 44 */     return ((MapLike)$this.self()).$plus(kv1, kv2, kvs);
/*    */   }
/*    */   
/*    */   public static Map $plus$plus(MapProxyLike $this, GenTraversableOnce xs) {
/* 45 */     return ((MapLike)$this.self()).$plus$plus(xs);
/*    */   }
/*    */   
/*    */   public static Map filterNot(MapProxyLike $this, Function1 p) {
/* 46 */     return (Map)((MapLike)$this.self()).filterNot(p);
/*    */   }
/*    */   
/*    */   public static StringBuilder addString(MapProxyLike $this, StringBuilder b, String start, String sep, String end) {
/* 49 */     return ((MapLike)$this.self()).addString(b, start, sep, end);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\MapProxyLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */