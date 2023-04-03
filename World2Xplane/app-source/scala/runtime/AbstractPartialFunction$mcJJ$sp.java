/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcJJ$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcJJ.sp {
/*    */   public AbstractPartialFunction$mcJJ$sp() {
/* 25 */     Function1.mcJJ.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public long apply$mcJJ$sp(long x) {
/* 33 */     return BoxesRunTime.unboxToLong(applyOrElse(BoxesRunTime.boxToLong(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public long apply(long x) {
/* 33 */     return apply$mcJJ$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcJJ$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */