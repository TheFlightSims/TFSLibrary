/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class Product2$class {
/*    */   public static void $init$(Product2 $this) {}
/*    */   
/*    */   public static int productArity(Product2 $this) {
/* 24 */     return 2;
/*    */   }
/*    */   
/*    */   public static Object productElement(Product2 $this, int n) throws IndexOutOfBoundsException {
/* 36 */     switch (n) {
/*    */       default:
/* 39 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/*    */     return $this._1();
/*    */   }
/*    */   
/*    */   public static double _1$mcD$sp(Product2 $this) {
/* 45 */     return BoxesRunTime.unboxToDouble($this._1());
/*    */   }
/*    */   
/*    */   public static int _1$mcI$sp(Product2 $this) {
/* 45 */     return BoxesRunTime.unboxToInt($this._1());
/*    */   }
/*    */   
/*    */   public static long _1$mcJ$sp(Product2 $this) {
/* 45 */     return BoxesRunTime.unboxToLong($this._1());
/*    */   }
/*    */   
/*    */   public static double _2$mcD$sp(Product2 $this) {
/* 49 */     return BoxesRunTime.unboxToDouble($this._2());
/*    */   }
/*    */   
/*    */   public static int _2$mcI$sp(Product2 $this) {
/* 49 */     return BoxesRunTime.unboxToInt($this._2());
/*    */   }
/*    */   
/*    */   public static long _2$mcJ$sp(Product2 $this) {
/* 49 */     return BoxesRunTime.unboxToLong($this._2());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Product2$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */