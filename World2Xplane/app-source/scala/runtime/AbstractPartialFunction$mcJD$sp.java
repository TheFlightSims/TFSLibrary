/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcJD$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcJD.sp {
/*    */   public AbstractPartialFunction$mcJD$sp() {
/* 25 */     Function1.mcJD.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public long apply$mcJD$sp(double x) {
/* 33 */     return BoxesRunTime.unboxToLong(applyOrElse(BoxesRunTime.boxToDouble(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public long apply(double x) {
/* 33 */     return apply$mcJD$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcJD$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */