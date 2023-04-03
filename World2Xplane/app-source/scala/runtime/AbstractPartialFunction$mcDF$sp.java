/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcDF$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcDF.sp {
/*    */   public AbstractPartialFunction$mcDF$sp() {
/* 25 */     Function1.mcDF.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public double apply$mcDF$sp(float x) {
/* 33 */     return BoxesRunTime.unboxToDouble(applyOrElse(BoxesRunTime.boxToFloat(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public double apply(float x) {
/* 33 */     return apply$mcDF$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcDF$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */