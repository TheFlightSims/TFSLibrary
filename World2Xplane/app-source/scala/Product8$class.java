/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class Product8$class {
/*    */   public static void $init$(Product8 $this) {}
/*    */   
/*    */   public static int productArity(Product8 $this) {
/* 24 */     return 8;
/*    */   }
/*    */   
/*    */   public static Object productElement(Product8 $this, int n) throws IndexOutOfBoundsException {
/* 36 */     switch (n) {
/*    */       default:
/* 45 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString());
/*    */       case 7:
/*    */       
/*    */       case 6:
/*    */       
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Product8$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */