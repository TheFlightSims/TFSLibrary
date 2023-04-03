/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcVL$sp<T1$sp> extends AbstractPartialFunction<T1$sp, BoxedUnit> {
/*    */   public void apply$mcVL$sp(Object x) {
/* 33 */     applyOrElse(x, (Function1)PartialFunction$.MODULE$.empty());
/*    */   }
/*    */   
/*    */   public void apply(Object x) {
/* 33 */     apply$mcVL$sp((T1$sp)x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcVL$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */