/*    */ package scala.util.hashing;
/*    */ 
/*    */ import scala.Serializable;
/*    */ 
/*    */ public final class ByteswapHashing$ implements Serializable {
/*    */   public static final ByteswapHashing$ MODULE$;
/*    */   
/*    */   private ByteswapHashing$() {
/* 25 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 25 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public <T> Hashing<T> chain(Hashing<T> h) {
/* 33 */     return new ByteswapHashing.Chained<T>(h);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\hashing\ByteswapHashing$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */