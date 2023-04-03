/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public abstract class DefaultMap$class {
/*    */   public static void $init$(DefaultMap $this) {}
/*    */   
/*    */   public static Map $plus(DefaultMap $this, Tuple2 kv) {
/* 42 */     Builder b = Map$.MODULE$.newBuilder();
/* 43 */     b.$plus$plus$eq((TraversableOnce)$this);
/* 44 */     b.$plus$eq(new Tuple2(kv._1(), kv._2()));
/* 45 */     return (Map)b.result();
/*    */   }
/*    */   
/*    */   public static Map $minus(DefaultMap $this, Object key) {
/* 51 */     Builder b = $this.newBuilder();
/* 52 */     $this.seq().withFilter((Function1)new DefaultMap$$anonfun$$minus$1($this, (DefaultMap<A, B>)key)).foreach((Function1)new DefaultMap$$anonfun$$minus$2($this, (DefaultMap<A, B>)b));
/* 53 */     return (Map)b.result();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\DefaultMap$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */