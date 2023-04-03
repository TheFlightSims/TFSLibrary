/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcFL$sp<T1$sp> extends AbstractPartialFunction<T1$sp, Object> {
/*    */   public float apply$mcFL$sp(Object x) {
/* 33 */     return BoxesRunTime.unboxToFloat(applyOrElse(x, (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public float apply(Object x) {
/* 33 */     return apply$mcFL$sp((T1$sp)x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcFL$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */