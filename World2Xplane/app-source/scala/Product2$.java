/*    */ package scala;
/*    */ 
/*    */ public final class Product2$ {
/*    */   public static final Product2$ MODULE$;
/*    */   
/*    */   private Product2$() {
/* 12 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T1, T2> Option<Product2<T1, T2>> unapply(Product2<T1, T2> x) {
/* 14 */     return new Some<Product2<T1, T2>>(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Product2$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */