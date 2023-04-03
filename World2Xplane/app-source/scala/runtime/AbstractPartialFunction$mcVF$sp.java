/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcVF$sp extends AbstractPartialFunction<Object, BoxedUnit> implements Function1.mcVF.sp {
/*    */   public AbstractPartialFunction$mcVF$sp() {
/* 25 */     Function1.mcVF.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public void apply$mcVF$sp(float x) {
/* 33 */     applyOrElse(BoxesRunTime.boxToFloat(x), (Function1)PartialFunction$.MODULE$.empty());
/*    */   }
/*    */   
/*    */   public void apply(float x) {
/* 33 */     apply$mcVF$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcVF$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */