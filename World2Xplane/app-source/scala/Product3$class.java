/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class Product3$class {
/*    */   public static void $init$(Product3 $this) {}
/*    */   
/*    */   public static int productArity(Product3 $this) {
/* 24 */     return 3;
/*    */   }
/*    */   
/*    */   public static Object productElement(Product3 $this, int n) throws IndexOutOfBoundsException {
/* 36 */     switch (n) {
/*    */       default:
/* 40 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString());
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/*    */     return $this._1();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Product3$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */