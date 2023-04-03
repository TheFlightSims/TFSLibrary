/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.GenSet;
/*    */ 
/*    */ public abstract class ParSetLike$class {
/*    */   public static void $init$(ParSetLike $this) {}
/*    */   
/*    */   public static ParSet union(ParSetLike $this, GenSet that) {
/* 48 */     return (ParSet)$this.sequentially(
/* 49 */         (Function1)new ParSetLike$$anonfun$union$1($this, (ParSetLike<T, Repr, Sequential>)that));
/*    */   }
/*    */   
/*    */   public static ParSet diff(ParSetLike $this, GenSet that) {
/* 52 */     return (ParSet)$this.sequentially(
/* 53 */         (Function1)new ParSetLike$$anonfun$diff$1($this, (ParSetLike<T, Repr, Sequential>)that));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSetLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */