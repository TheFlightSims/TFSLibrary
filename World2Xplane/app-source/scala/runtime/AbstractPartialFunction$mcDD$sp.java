/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcDD$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcDD.sp {
/*    */   public AbstractPartialFunction$mcDD$sp() {
/* 25 */     Function1.mcDD.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public double apply$mcDD$sp(double x) {
/* 33 */     return BoxesRunTime.unboxToDouble(applyOrElse(BoxesRunTime.boxToDouble(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public double apply(double x) {
/* 33 */     return apply$mcDD$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcDD$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */