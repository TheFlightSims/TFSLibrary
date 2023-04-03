/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcID$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcID.sp {
/*    */   public AbstractPartialFunction$mcID$sp() {
/* 25 */     Function1.mcID.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public int apply$mcID$sp(double x) {
/* 33 */     return BoxesRunTime.unboxToInt(applyOrElse(BoxesRunTime.boxToDouble(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public int apply(double x) {
/* 33 */     return apply$mcID$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcID$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */