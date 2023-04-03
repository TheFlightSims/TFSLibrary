/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcDI$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcDI.sp {
/*    */   public AbstractPartialFunction$mcDI$sp() {
/* 25 */     Function1.mcDI.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public double apply$mcDI$sp(int x) {
/* 33 */     return BoxesRunTime.unboxToDouble(applyOrElse(BoxesRunTime.boxToInteger(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public double apply(int x) {
/* 33 */     return apply$mcDI$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcDI$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */