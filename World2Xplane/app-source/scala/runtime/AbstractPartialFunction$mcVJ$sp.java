/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcVJ$sp extends AbstractPartialFunction<Object, BoxedUnit> implements Function1.mcVJ.sp {
/*    */   public AbstractPartialFunction$mcVJ$sp() {
/* 25 */     Function1.mcVJ.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public void apply$mcVJ$sp(long x) {
/* 33 */     applyOrElse(BoxesRunTime.boxToLong(x), (Function1)PartialFunction$.MODULE$.empty());
/*    */   }
/*    */   
/*    */   public void apply(long x) {
/* 33 */     apply$mcVJ$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcVJ$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */