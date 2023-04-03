/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class Product6$class {
/*    */   public static void $init$(Product6 $this) {}
/*    */   
/*    */   public static int productArity(Product6 $this) {
/* 24 */     return 6;
/*    */   }
/*    */   
/*    */   public static Object productElement(Product6 $this, int n) throws IndexOutOfBoundsException {
/* 36 */     switch (n) {
/*    */       default:
/* 43 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString());
/*    */       case 5:
/*    */       
/*    */       case 4:
/*    */       
/*    */       case 3:
/*    */       
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Product6$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */