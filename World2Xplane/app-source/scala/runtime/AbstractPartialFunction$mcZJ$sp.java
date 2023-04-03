/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcZJ$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcZJ.sp {
/*    */   public AbstractPartialFunction$mcZJ$sp() {
/* 25 */     Function1.mcZJ.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZJ$sp(long x) {
/* 33 */     return BoxesRunTime.unboxToBoolean(applyOrElse(BoxesRunTime.boxToLong(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public boolean apply(long x) {
/* 33 */     return apply$mcZJ$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcZJ$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */