/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class Product1$class {
/*    */   public static void $init$(Product1 $this) {}
/*    */   
/*    */   public static int productArity(Product1 $this) {
/* 24 */     return 1;
/*    */   }
/*    */   
/*    */   public static Object productElement(Product1 $this, int n) throws IndexOutOfBoundsException {
/* 36 */     switch (n) {
/*    */       default:
/* 38 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/*    */     return $this._1();
/*    */   }
/*    */   
/*    */   public static double _1$mcD$sp(Product1 $this) {
/* 44 */     return BoxesRunTime.unboxToDouble($this._1());
/*    */   }
/*    */   
/*    */   public static int _1$mcI$sp(Product1 $this) {
/* 44 */     return BoxesRunTime.unboxToInt($this._1());
/*    */   }
/*    */   
/*    */   public static long _1$mcJ$sp(Product1 $this) {
/* 44 */     return BoxesRunTime.unboxToLong($this._1());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Product1$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */