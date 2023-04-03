/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcLF$sp<R$sp> extends AbstractPartialFunction<Object, R$sp> {
/*    */   public R$sp apply$mcLF$sp(float x) {
/* 33 */     return (R$sp)applyOrElse(BoxesRunTime.boxToFloat(x), (Function1)PartialFunction$.MODULE$.empty());
/*    */   }
/*    */   
/*    */   public R$sp apply(float x) {
/* 33 */     return apply$mcLF$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcLF$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */