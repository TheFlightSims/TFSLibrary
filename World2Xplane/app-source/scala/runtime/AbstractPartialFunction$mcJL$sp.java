/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcJL$sp<T1$sp> extends AbstractPartialFunction<T1$sp, Object> {
/*    */   public long apply$mcJL$sp(Object x) {
/* 33 */     return BoxesRunTime.unboxToLong(applyOrElse(x, (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public long apply(Object x) {
/* 33 */     return apply$mcJL$sp((T1$sp)x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcJL$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */