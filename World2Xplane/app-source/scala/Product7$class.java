/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class Product7$class {
/*    */   public static void $init$(Product7 $this) {}
/*    */   
/*    */   public static int productArity(Product7 $this) {
/* 24 */     return 7;
/*    */   }
/*    */   
/*    */   public static Object productElement(Product7 $this, int n) throws IndexOutOfBoundsException {
/* 36 */     switch (n) {
/*    */       default:
/* 44 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString());
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Product7$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */