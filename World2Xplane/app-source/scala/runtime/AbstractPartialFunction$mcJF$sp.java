/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcJF$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcJF.sp {
/*    */   public AbstractPartialFunction$mcJF$sp() {
/* 25 */     Function1.mcJF.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public long apply$mcJF$sp(float x) {
/* 33 */     return BoxesRunTime.unboxToLong(applyOrElse(BoxesRunTime.boxToFloat(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public long apply(float x) {
/* 33 */     return apply$mcJF$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcJF$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */