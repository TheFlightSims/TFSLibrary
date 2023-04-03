/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ 
/*    */ public abstract class GenTraversableViewLike$class {
/*    */   public static void $init$(GenTraversableViewLike $this) {}
/*    */   
/*    */   public static String viewToString(GenTraversableViewLike $this) {
/* 28 */     return (new StringBuilder()).append($this.stringPrefix()).append($this.viewIdString()).append("(...)").toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenTraversableViewLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */