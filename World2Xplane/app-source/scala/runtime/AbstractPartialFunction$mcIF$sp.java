/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcIF$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcIF.sp {
/*    */   public AbstractPartialFunction$mcIF$sp() {
/* 25 */     Function1.mcIF.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public int apply$mcIF$sp(float x) {
/* 33 */     return BoxesRunTime.unboxToInt(applyOrElse(BoxesRunTime.boxToFloat(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public int apply(float x) {
/* 33 */     return apply$mcIF$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcIF$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */