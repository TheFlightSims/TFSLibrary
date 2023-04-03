/*    */ package scala.collection.mutable;
/*    */ 
/*    */ public abstract class SetProxy$class {
/*    */   public static void $init$(SetProxy $this) {}
/*    */   
/*    */   public static SetProxy repr(SetProxy $this) {
/* 21 */     return $this;
/*    */   }
/*    */   
/*    */   public static SetProxy empty(SetProxy<A> $this) {
/* 22 */     return new SetProxy$$anon$1($this);
/*    */   }
/*    */   
/*    */   public static SetProxy $plus(SetProxy $this, Object elem) {
/* 23 */     ((SetLike)$this.self()).$plus$eq(elem);
/* 23 */     return $this;
/*    */   }
/*    */   
/*    */   public static SetProxy $minus(SetProxy $this, Object elem) {
/* 24 */     ((SetLike)$this.self()).$minus$eq(elem);
/* 24 */     return $this;
/*    */   }
/*    */   
/*    */   public static SetProxy $plus$eq(SetProxy $this, Object elem) {
/* 26 */     ((SetLike)$this.self()).$plus$eq(elem);
/* 26 */     return $this;
/*    */   }
/*    */   
/*    */   public static SetProxy $minus$eq(SetProxy $this, Object elem) {
/* 27 */     ((SetLike)$this.self()).$minus$eq(elem);
/* 27 */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SetProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */