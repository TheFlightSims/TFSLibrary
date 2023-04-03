/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.immutable.SortedMap$;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public abstract class SortedMap$class {
/*    */   public static void $init$(SortedMap $this) {}
/*    */   
/*    */   public static SortedMap empty(SortedMap $this) {
/* 23 */     return SortedMap$.MODULE$.empty($this.ordering());
/*    */   }
/*    */   
/*    */   public static Builder newBuilder(SortedMap $this) {
/* 26 */     return SortedMap$.MODULE$.newBuilder($this.ordering());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SortedMap$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */