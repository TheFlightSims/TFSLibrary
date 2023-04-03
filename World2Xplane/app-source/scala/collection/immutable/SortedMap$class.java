/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public abstract class SortedMap$class {
/*    */   public static void $init$(SortedMap $this) {}
/*    */   
/*    */   public static Builder newBuilder(SortedMap $this) {
/* 38 */     return SortedMap$.MODULE$.newBuilder($this.ordering());
/*    */   }
/*    */   
/*    */   public static SortedMap empty(SortedMap $this) {
/* 40 */     return SortedMap$.MODULE$.empty($this.ordering());
/*    */   }
/*    */   
/*    */   public static SortedMap updated(SortedMap $this, Object key, Object value) {
/* 41 */     return $this.$plus(new Tuple2(key, value));
/*    */   }
/*    */   
/*    */   public static SortedSet keySet(SortedMap<A, B> $this) {
/* 42 */     return new SortedMap.DefaultKeySortedSet($this);
/*    */   }
/*    */   
/*    */   public static SortedMap $plus(SortedMap $this, Tuple2 kv) {
/* 62 */     throw new AbstractMethodError("SortedMap.+");
/*    */   }
/*    */   
/*    */   public static SortedMap $plus(SortedMap $this, Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 72 */     return $this.$plus(elem1).$plus(elem2).$plus$plus((GenTraversableOnce)elems);
/*    */   }
/*    */   
/*    */   public static SortedMap $plus$plus(SortedMap<A, B> $this, GenTraversableOnce xs) {
/* 80 */     SortedMap sortedMap = (SortedMap)$this.repr();
/* 80 */     return (SortedMap)xs.seq().$div$colon(sortedMap, (Function2)new SortedMap$$anonfun$$plus$plus$1($this));
/*    */   }
/*    */   
/*    */   public static SortedMap filterKeys(SortedMap $this, Function1 p) {
/* 82 */     return new SortedMap$$anon$1($this, (SortedMap<A, B>)p);
/*    */   }
/*    */   
/*    */   public static SortedMap mapValues(SortedMap $this, Function1 f) {
/* 87 */     return new SortedMap$$anon$2($this, (SortedMap<A, B>)f);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\SortedMap$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */