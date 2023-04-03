/*    */ package scala.util.hashing;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Hashing$ implements Serializable {
/*    */   public static final Hashing$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 29 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Hashing$() {
/* 29 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> Hashing.Default<T> default() {
/* 34 */     return new Hashing.Default<T>();
/*    */   }
/*    */   
/*    */   public <T> Object fromFunction(Function1 f) {
/* 36 */     return new Hashing$$anon$1(f);
/*    */   }
/*    */   
/*    */   public static class Hashing$$anon$1 implements Hashing<T> {
/*    */     private final Function1 f$1;
/*    */     
/*    */     public Hashing$$anon$1(Function1 f$1) {}
/*    */     
/*    */     public int hash(Object x) {
/* 37 */       return BoxesRunTime.unboxToInt(this.f$1.apply(x));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\hashing\Hashing$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */