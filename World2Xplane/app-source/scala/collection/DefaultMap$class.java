/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public abstract class DefaultMap$class {
/*    */   public static void $init$(DefaultMap $this) {}
/*    */   
/*    */   public static Map $plus(DefaultMap $this, Tuple2 kv) {
/* 34 */     Builder b = Map$.MODULE$.newBuilder();
/* 35 */     b.$plus$plus$eq($this);
/* 36 */     b.$plus$eq(new Tuple2(kv._1(), kv._2()));
/* 37 */     return (Map)b.result();
/*    */   }
/*    */   
/*    */   public static Map $minus(DefaultMap $this, Object key) {
/* 43 */     Builder b = $this.newBuilder();
/* 44 */     b.$plus$plus$eq($this.filter((Function1)new DefaultMap$$anonfun$$minus$1($this, (DefaultMap<A, B>)key)));
/* 45 */     return (Map)b.result();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\DefaultMap$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */