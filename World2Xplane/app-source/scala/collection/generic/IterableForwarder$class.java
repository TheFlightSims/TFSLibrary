/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.Iterator;
/*    */ 
/*    */ public abstract class IterableForwarder$class {
/*    */   public static void $init$(IterableForwarder $this) {}
/*    */   
/*    */   public static Iterator iterator(IterableForwarder<A> $this) {
/* 38 */     return $this.underlying().iterator();
/*    */   }
/*    */   
/*    */   public static boolean sameElements(IterableForwarder<A> $this, GenIterable that) {
/* 39 */     return $this.underlying().sameElements(that);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\IterableForwarder$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */