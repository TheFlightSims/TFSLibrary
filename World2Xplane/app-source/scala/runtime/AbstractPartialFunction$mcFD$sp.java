/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcFD$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcFD.sp {
/*    */   public AbstractPartialFunction$mcFD$sp() {
/* 25 */     Function1.mcFD.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public float apply$mcFD$sp(double x) {
/* 33 */     return BoxesRunTime.unboxToFloat(applyOrElse(BoxesRunTime.boxToDouble(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public float apply(double x) {
/* 33 */     return apply$mcFD$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcFD$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */