/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class package$ {
/*    */   public static final package$ MODULE$;
/*    */   
/*    */   private package$() {
/* 78 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <From, T, To> CanBuildFrom<From, T, To> breakOut(CanBuildFrom b) {
/* 86 */     return new package$$anon$1(b);
/*    */   }
/*    */   
/*    */   public static class package$$anon$1 implements CanBuildFrom<From, T, To> {
/*    */     private final CanBuildFrom b$1;
/*    */     
/*    */     public package$$anon$1(CanBuildFrom b$1) {}
/*    */     
/*    */     public Builder<T, To> apply(Object from) {
/* 87 */       return this.b$1.apply();
/*    */     }
/*    */     
/*    */     public Builder<T, To> apply() {
/* 88 */       return this.b$1.apply();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */