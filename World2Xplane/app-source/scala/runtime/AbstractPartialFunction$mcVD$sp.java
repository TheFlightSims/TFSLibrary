/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcVD$sp extends AbstractPartialFunction<Object, BoxedUnit> implements Function1.mcVD.sp {
/*    */   public AbstractPartialFunction$mcVD$sp() {
/* 25 */     Function1.mcVD.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public void apply$mcVD$sp(double x) {
/* 33 */     applyOrElse(BoxesRunTime.boxToDouble(x), (Function1)PartialFunction$.MODULE$.empty());
/*    */   }
/*    */   
/*    */   public void apply(double x) {
/* 33 */     apply$mcVD$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcVD$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */