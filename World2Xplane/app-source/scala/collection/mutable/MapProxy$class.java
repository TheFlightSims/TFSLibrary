/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Seq;
/*    */ 
/*    */ public abstract class MapProxy$class {
/*    */   public static void $init$(MapProxy $this) {}
/*    */   
/*    */   private static MapProxy newProxy(MapProxy $this, Map newSelf) {
/* 24 */     return new MapProxy$$anon$1($this, (MapProxy<A, B>)newSelf);
/*    */   }
/*    */   
/*    */   public static MapProxy repr(MapProxy $this) {
/* 26 */     return $this;
/*    */   }
/*    */   
/*    */   public static MapProxy empty(MapProxy<A, B> $this) {
/* 27 */     return new MapProxy$$anon$2($this);
/*    */   }
/*    */   
/*    */   public static MapProxy updated(MapProxy $this, Object key, Object value) {
/* 28 */     return newProxy($this, ((MapLike)$this.self()).updated(key, value));
/*    */   }
/*    */   
/*    */   public static Map $plus(MapProxy $this, Tuple2 kv) {
/* 30 */     return newProxy($this, ((MapLike)$this.self()).$plus(kv));
/*    */   }
/*    */   
/*    */   public static MapProxy $plus(MapProxy $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 31 */     return newProxy($this, ((MapLike)$this.self()).$plus(elem1, elem2, elems));
/*    */   }
/*    */   
/*    */   public static MapProxy $plus$plus(MapProxy $this, GenTraversableOnce xs) {
/* 32 */     return newProxy($this, ((MapLike)$this.self()).$plus$plus((GenTraversableOnce)xs.seq()));
/*    */   }
/*    */   
/*    */   public static MapProxy $minus(MapProxy $this, Object key) {
/* 33 */     return newProxy($this, (Map)((MapLike)$this.self()).$minus(key));
/*    */   }
/*    */   
/*    */   public static MapProxy $plus$eq(MapProxy $this, Tuple2 kv) {
/* 35 */     ((MapLike)$this.self()).$plus$eq(kv);
/* 35 */     return $this;
/*    */   }
/*    */   
/*    */   public static MapProxy $minus$eq(MapProxy $this, Object key) {
/* 36 */     ((MapLike)$this.self()).$minus$eq(key);
/* 36 */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\MapProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */