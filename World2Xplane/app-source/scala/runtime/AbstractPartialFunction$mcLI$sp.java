/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcLI$sp<R$sp> extends AbstractPartialFunction<Object, R$sp> {
/*    */   public R$sp apply$mcLI$sp(int x) {
/* 33 */     return (R$sp)applyOrElse(BoxesRunTime.boxToInteger(x), (Function1)PartialFunction$.MODULE$.empty());
/*    */   }
/*    */   
/*    */   public R$sp apply(int x) {
/* 33 */     return apply$mcLI$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcLI$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */