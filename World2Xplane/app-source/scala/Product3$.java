/*    */ package scala;
/*    */ 
/*    */ public final class Product3$ {
/*    */   public static final Product3$ MODULE$;
/*    */   
/*    */   private Product3$() {
/* 12 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T1, T2, T3> Option<Product3<T1, T2, T3>> unapply(Product3<T1, T2, T3> x) {
/* 14 */     return new Some<Product3<T1, T2, T3>>(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Product3$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */