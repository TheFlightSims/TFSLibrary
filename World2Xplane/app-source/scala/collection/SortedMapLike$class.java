/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.ObjectRef;
/*    */ 
/*    */ public abstract class SortedMapLike$class {
/*    */   public static void $init$(SortedMapLike $this) {}
/*    */   
/*    */   public static Object firstKey(SortedMapLike $this) {
/* 27 */     return $this.head()._1();
/*    */   }
/*    */   
/*    */   public static Object lastKey(SortedMapLike $this) {
/* 28 */     return $this.last()._1();
/*    */   }
/*    */   
/*    */   public static SortedSet keySet(SortedMapLike<A, B, This> $this) {
/* 35 */     return new SortedMapLike.DefaultKeySortedSet($this);
/*    */   }
/*    */   
/*    */   public static SortedMap updated(SortedMapLike $this, Object key, Object value) {
/* 52 */     return $this.$plus(new Tuple2(key, value));
/*    */   }
/*    */   
/*    */   public static SortedMap $plus(SortedMapLike $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 71 */     ObjectRef m = new ObjectRef($this.$plus(elem1).$plus(elem2));
/* 72 */     elems.foreach((Function1)new SortedMapLike$$anonfun$$plus$1($this, (SortedMapLike<A, B, This>)m));
/* 73 */     return (SortedMap)m.elem;
/*    */   }
/*    */   
/*    */   public static SortedMap filterKeys(SortedMapLike $this, Function1 p) {
/* 76 */     return new SortedMapLike$$anon$1($this, (SortedMapLike<A, B, This>)p);
/*    */   }
/*    */   
/*    */   public static SortedMap mapValues(SortedMapLike $this, Function1 f) {
/* 81 */     return new SortedMapLike$$anon$2($this, (SortedMapLike<A, B, This>)f);
/*    */   }
/*    */   
/*    */   public static SortedMap $plus$plus(SortedMapLike<A, B, This> $this, GenTraversableOnce xs) {
/* 92 */     SortedMap sortedMap = (SortedMap)$this.repr();
/* 92 */     return (SortedMap)xs.seq().$div$colon(sortedMap, (Function2)new SortedMapLike$$anonfun$$plus$plus$1($this));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SortedMapLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */