/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ 
/*    */ public abstract class ParMapLike$class {
/*    */   public static void $init$(ParMapLike $this) {}
/*    */   
/*    */   public static ParMap $plus(ParMapLike $this, Tuple2 kv) {
/* 51 */     return (ParMap)((ParMap)$this.clone()).$plus$eq(kv);
/*    */   }
/*    */   
/*    */   public static ParMap $minus(ParMapLike $this, Object key) {
/* 53 */     return (ParMap)((ParMapLike)$this.clone()).$minus$eq(key);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParMapLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */