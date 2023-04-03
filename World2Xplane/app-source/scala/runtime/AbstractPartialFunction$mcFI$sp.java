/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcFI$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcFI.sp {
/*    */   public AbstractPartialFunction$mcFI$sp() {
/* 25 */     Function1.mcFI.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public float apply$mcFI$sp(int x) {
/* 33 */     return BoxesRunTime.unboxToFloat(applyOrElse(BoxesRunTime.boxToInteger(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public float apply(int x) {
/* 33 */     return apply$mcFI$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcFI$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */