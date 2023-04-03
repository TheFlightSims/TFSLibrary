/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcJI$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcJI.sp {
/*    */   public AbstractPartialFunction$mcJI$sp() {
/* 25 */     Function1.mcJI.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public long apply$mcJI$sp(int x) {
/* 33 */     return BoxesRunTime.unboxToLong(applyOrElse(BoxesRunTime.boxToInteger(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public long apply(int x) {
/* 33 */     return apply$mcJI$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcJI$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */