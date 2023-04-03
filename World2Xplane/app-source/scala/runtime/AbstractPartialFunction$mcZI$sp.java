/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcZI$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcZI.sp {
/*    */   public AbstractPartialFunction$mcZI$sp() {
/* 25 */     Function1.mcZI.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZI$sp(int x) {
/* 33 */     return BoxesRunTime.unboxToBoolean(applyOrElse(BoxesRunTime.boxToInteger(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public boolean apply(int x) {
/* 33 */     return apply$mcZI$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcZI$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */