/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.MapLike;
/*    */ import scala.collection.Seq;
/*    */ 
/*    */ public abstract class MapProxy$class {
/*    */   public static void $init$(MapProxy $this) {}
/*    */   
/*    */   public static MapProxy repr(MapProxy $this) {
/* 26 */     return $this;
/*    */   }
/*    */   
/*    */   private static MapProxy newProxy(MapProxy $this, Map newSelf) {
/* 28 */     return new MapProxy$$anon$1($this, (MapProxy<A, B>)newSelf);
/*    */   }
/*    */   
/*    */   public static MapProxy empty(MapProxy $this) {
/* 30 */     return newProxy($this, ((Map)$this.self()).empty());
/*    */   }
/*    */   
/*    */   public static MapProxy updated(MapProxy $this, Object key, Object value) {
/* 31 */     return newProxy($this, ((MapLike)$this.self()).updated(key, value));
/*    */   }
/*    */   
/*    */   public static MapProxy $minus(MapProxy $this, Object key) {
/* 33 */     return newProxy($this, (Map)((MapLike)$this.self()).$minus(key));
/*    */   }
/*    */   
/*    */   public static Map $plus(MapProxy $this, Tuple2 kv) {
/* 34 */     return newProxy($this, ((Map)$this.self()).$plus(kv));
/*    */   }
/*    */   
/*    */   public static MapProxy $plus(MapProxy $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 35 */     return newProxy($this, ((MapLike)$this.self()).$plus(elem1, elem2, elems));
/*    */   }
/*    */   
/*    */   public static MapProxy $plus$plus(MapProxy $this, GenTraversableOnce xs) {
/* 36 */     return newProxy($this, ((MapLike)$this.self()).$plus$plus((GenTraversableOnce)xs.seq()));
/*    */   }
/*    */   
/*    */   public static Set keySet(MapProxy<A, B> $this) {
/* 38 */     return new MapProxy$$anon$2($this);
/*    */   }
/*    */   
/*    */   public static Map filterKeys(MapProxy $this, Function1 p) {
/* 39 */     return ((MapLike)$this.self()).filterKeys(p);
/*    */   }
/*    */   
/*    */   public static Map mapValues(MapProxy $this, Function1 f) {
/* 40 */     return ((MapLike)$this.self()).mapValues(f);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\MapProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */