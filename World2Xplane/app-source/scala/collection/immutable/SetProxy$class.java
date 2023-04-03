/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.SetLike;
/*    */ import scala.collection.generic.GenericSetTemplate;
/*    */ 
/*    */ public abstract class SetProxy$class {
/*    */   public static void $init$(SetProxy $this) {}
/*    */   
/*    */   public static SetProxy repr(SetProxy $this) {
/* 25 */     return $this;
/*    */   }
/*    */   
/*    */   private static SetProxy newProxy(SetProxy $this, Set newSelf) {
/* 27 */     return new SetProxy$$anon$1($this, (SetProxy$class)newSelf);
/*    */   }
/*    */   
/*    */   public static SetProxy empty(SetProxy $this) {
/* 29 */     return newProxy($this, (Set)((GenericSetTemplate)$this.self()).empty());
/*    */   }
/*    */   
/*    */   public static SetProxy $plus(SetProxy $this, Object elem) {
/* 30 */     return newProxy($this, (Set)((SetLike)$this.self()).$plus(elem));
/*    */   }
/*    */   
/*    */   public static SetProxy $minus(SetProxy $this, Object elem) {
/* 31 */     return newProxy($this, (Set)((SetLike)$this.self()).$minus(elem));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\SetProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */