/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ public abstract class ParSetLike$class {
/*    */   public static void $init$(ParSetLike $this) {}
/*    */   
/*    */   public static ParSet $plus(ParSetLike $this, Object elem) {
/* 51 */     return (ParSet)((ParSetLike)$this.clone()).$plus$eq(elem);
/*    */   }
/*    */   
/*    */   public static ParSet $minus(ParSetLike $this, Object elem) {
/* 53 */     return (ParSet)((ParSetLike)$this.clone()).$minus$eq(elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParSetLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */