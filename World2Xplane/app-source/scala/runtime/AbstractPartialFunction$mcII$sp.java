/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcII$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcII.sp {
/*    */   public AbstractPartialFunction$mcII$sp() {
/* 25 */     Function1.mcII.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public int apply$mcII$sp(int x) {
/* 33 */     return BoxesRunTime.unboxToInt(applyOrElse(BoxesRunTime.boxToInteger(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public int apply(int x) {
/* 33 */     return apply$mcII$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcII$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */