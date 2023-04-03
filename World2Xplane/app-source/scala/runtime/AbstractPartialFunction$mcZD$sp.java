/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcZD$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcZD.sp {
/*    */   public AbstractPartialFunction$mcZD$sp() {
/* 25 */     Function1.mcZD.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZD$sp(double x) {
/* 33 */     return BoxesRunTime.unboxToBoolean(applyOrElse(BoxesRunTime.boxToDouble(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public boolean apply(double x) {
/* 33 */     return apply$mcZD$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcZD$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */