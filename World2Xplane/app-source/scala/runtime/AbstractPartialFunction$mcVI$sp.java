/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcVI$sp extends AbstractPartialFunction<Object, BoxedUnit> implements Function1.mcVI.sp {
/*    */   public AbstractPartialFunction$mcVI$sp() {
/* 25 */     Function1.mcVI.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public void apply$mcVI$sp(int x) {
/* 33 */     applyOrElse(BoxesRunTime.boxToInteger(x), (Function1)PartialFunction$.MODULE$.empty());
/*    */   }
/*    */   
/*    */   public void apply(int x) {
/* 33 */     apply$mcVI$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcVI$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */