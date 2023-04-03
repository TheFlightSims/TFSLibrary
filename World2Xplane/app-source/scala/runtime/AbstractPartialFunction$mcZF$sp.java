/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcZF$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcZF.sp {
/*    */   public AbstractPartialFunction$mcZF$sp() {
/* 25 */     Function1.mcZF.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZF$sp(float x) {
/* 33 */     return BoxesRunTime.unboxToBoolean(applyOrElse(BoxesRunTime.boxToFloat(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public boolean apply(float x) {
/* 33 */     return apply$mcZF$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcZF$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */