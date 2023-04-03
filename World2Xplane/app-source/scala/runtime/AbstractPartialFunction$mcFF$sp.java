/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcFF$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcFF.sp {
/*    */   public AbstractPartialFunction$mcFF$sp() {
/* 25 */     Function1.mcFF.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public float apply$mcFF$sp(float x) {
/* 33 */     return BoxesRunTime.unboxToFloat(applyOrElse(BoxesRunTime.boxToFloat(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public float apply(float x) {
/* 33 */     return apply$mcFF$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcFF$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */