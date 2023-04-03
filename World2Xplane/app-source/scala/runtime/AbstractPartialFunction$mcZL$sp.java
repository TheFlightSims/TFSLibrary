/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcZL$sp<T1$sp> extends AbstractPartialFunction<T1$sp, Object> {
/*    */   public boolean apply$mcZL$sp(Object x) {
/* 33 */     return BoxesRunTime.unboxToBoolean(applyOrElse(x, (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public boolean apply(Object x) {
/* 33 */     return apply$mcZL$sp((T1$sp)x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcZL$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */