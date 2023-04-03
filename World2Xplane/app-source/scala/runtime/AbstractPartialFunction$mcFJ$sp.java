/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcFJ$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcFJ.sp {
/*    */   public AbstractPartialFunction$mcFJ$sp() {
/* 25 */     Function1.mcFJ.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public float apply$mcFJ$sp(long x) {
/* 33 */     return BoxesRunTime.unboxToFloat(applyOrElse(BoxesRunTime.boxToLong(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public float apply(long x) {
/* 33 */     return apply$mcFJ$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcFJ$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */